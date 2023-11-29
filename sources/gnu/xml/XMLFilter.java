package gnu.xml;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.Keyword;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.AbstractSequence;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.util.List;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class XMLFilter implements DocumentHandler, ContentHandler, SourceLocator, XConsumer, PositionConsumer {
    public static final int COPY_NAMESPACES_INHERIT = 2;
    public static final int COPY_NAMESPACES_PRESERVE = 1;
    private static final int SAW_KEYWORD = 1;
    private static final int SAW_WORD = 2;
    int attrCount = -1;
    String attrLocalName;
    String attrPrefix;
    Consumer base;
    public transient int copyNamespacesMode = 1;
    String currentNamespacePrefix;
    protected int ignoringLevel;
    LineBufferedReader in;
    boolean inStartTag;
    SourceLocator locator;
    MappingInfo[] mappingTable;
    int mappingTableMask;
    private SourceMessages messages;
    boolean mismatchReported;
    NamespaceBinding namespaceBindings;
    public boolean namespacePrefixes = false;
    protected int nesting;
    public Consumer out;
    int previous = 0;
    int[] startIndexes = null;
    protected int stringizingElementNesting = -1;
    protected int stringizingLevel;
    TreeList tlist;
    Object[] workStack;

    public void emitDoctypeDecl(char[] cArr, int i, int i2, int i3, int i4) {
    }

    public String getPublicId() {
        return null;
    }

    public boolean isStableSourceLocation() {
        return false;
    }

    public void skippedEntity(String str) {
    }

    public void setSourceLocator(LineBufferedReader lineBufferedReader) {
        this.in = lineBufferedReader;
        this.locator = this;
    }

    public void setSourceLocator(SourceLocator sourceLocator) {
        this.locator = sourceLocator;
    }

    public void setMessages(SourceMessages sourceMessages) {
        this.messages = sourceMessages;
    }

    public NamespaceBinding findNamespaceBinding(String str, String str2, NamespaceBinding namespaceBinding) {
        NamespaceBinding namespaceBinding2;
        int hashCode = str2 == null ? 0 : str2.hashCode();
        if (str != null) {
            hashCode ^= str.hashCode();
        }
        int i = this.mappingTableMask & hashCode;
        for (MappingInfo mappingInfo = this.mappingTable[i]; mappingInfo != null; mappingInfo = mappingInfo.nextInBucket) {
            if (mappingInfo.tagHash == hashCode && mappingInfo.prefix == str && (namespaceBinding2 = mappingInfo.namespaces) != null && namespaceBinding2.getNext() == this.namespaceBindings && namespaceBinding2.getPrefix() == str && mappingInfo.uri == str2) {
                return mappingInfo.namespaces;
            }
        }
        MappingInfo mappingInfo2 = new MappingInfo();
        mappingInfo2.nextInBucket = this.mappingTable[i];
        this.mappingTable[i] = mappingInfo2;
        mappingInfo2.tagHash = hashCode;
        mappingInfo2.prefix = str;
        mappingInfo2.local = str2;
        mappingInfo2.uri = str2;
        if (str2 == "") {
            str2 = null;
        }
        mappingInfo2.namespaces = new NamespaceBinding(str, str2, namespaceBinding);
        return mappingInfo2.namespaces;
    }

    public MappingInfo lookupNamespaceBinding(String str, char[] cArr, int i, int i2, int i3, NamespaceBinding namespaceBinding) {
        NamespaceBinding namespaceBinding2;
        if (str != null) {
            i3 ^= str.hashCode();
        }
        int i4 = this.mappingTableMask & i3;
        for (MappingInfo mappingInfo = this.mappingTable[i4]; mappingInfo != null; mappingInfo = mappingInfo.nextInBucket) {
            if (mappingInfo.tagHash == i3 && mappingInfo.prefix == str && (namespaceBinding2 = mappingInfo.namespaces) != null && namespaceBinding2.getNext() == this.namespaceBindings && namespaceBinding2.getPrefix() == str && MappingInfo.equals(mappingInfo.uri, cArr, i, i2)) {
                return mappingInfo;
            }
        }
        MappingInfo mappingInfo2 = new MappingInfo();
        mappingInfo2.nextInBucket = this.mappingTable[i4];
        this.mappingTable[i4] = mappingInfo2;
        String intern = new String(cArr, i, i2).intern();
        mappingInfo2.tagHash = i3;
        mappingInfo2.prefix = str;
        mappingInfo2.local = intern;
        mappingInfo2.uri = intern;
        if (intern == "") {
            intern = null;
        }
        mappingInfo2.namespaces = new NamespaceBinding(str, intern, namespaceBinding);
        return mappingInfo2;
    }

    public void endAttribute() {
        int i;
        int i2;
        char[] cArr;
        int i3;
        String str = this.attrLocalName;
        if (str != null) {
            if (this.previous == 1) {
                this.previous = 0;
                return;
            }
            if (this.stringizingElementNesting >= 0) {
                this.ignoringLevel--;
            }
            int i4 = this.stringizingLevel - 1;
            this.stringizingLevel = i4;
            if (i4 == 0) {
                if (str == CommonProperties.ID && this.attrPrefix == "xml") {
                    int i5 = this.startIndexes[this.attrCount - 1] + 5;
                    int i6 = this.tlist.gapStart;
                    char[] cArr2 = this.tlist.data;
                    int i7 = i5;
                    while (true) {
                        if (i7 >= i6) {
                            break;
                        }
                        int i8 = i7 + 1;
                        char c = cArr2[i7];
                        if ((c & LispReader.TOKEN_ESCAPE_CHAR) > 40959 || c == 9 || c == 13 || c == 10 || (c == ' ' && (i8 == i6 || cArr2[i8] == ' '))) {
                            StringBuffer stringBuffer = new StringBuffer();
                            this.tlist.stringValue(i5, i6, stringBuffer);
                            this.tlist.gapStart = i5;
                            this.tlist.write(TextUtils.replaceWhitespace(stringBuffer.toString(), true));
                        } else {
                            i7 = i8;
                        }
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    this.tlist.stringValue(i5, i6, stringBuffer2);
                    this.tlist.gapStart = i5;
                    this.tlist.write(TextUtils.replaceWhitespace(stringBuffer2.toString(), true));
                }
                this.attrLocalName = null;
                this.attrPrefix = null;
                if (this.currentNamespacePrefix == null || this.namespacePrefixes) {
                    this.tlist.endAttribute();
                }
                if (this.currentNamespacePrefix != null) {
                    int i9 = this.startIndexes[this.attrCount - 1];
                    int i10 = this.tlist.gapStart;
                    int i11 = i10 - i9;
                    char[] cArr3 = this.tlist.data;
                    int i12 = i9;
                    int i13 = 0;
                    while (true) {
                        if (i12 >= i10) {
                            i = i9;
                            i2 = i11;
                            cArr = cArr3;
                            i3 = i13;
                            break;
                        }
                        char c2 = cArr3[i12];
                        if ((c2 & LispReader.TOKEN_ESCAPE_CHAR) > 40959) {
                            StringBuffer stringBuffer3 = new StringBuffer();
                            this.tlist.stringValue(i9, i10, stringBuffer3);
                            int hashCode = stringBuffer3.hashCode();
                            i2 = stringBuffer3.length();
                            char[] cArr4 = new char[stringBuffer3.length()];
                            stringBuffer3.getChars(0, i2, cArr4, 0);
                            cArr = cArr4;
                            i3 = hashCode;
                            i = 0;
                            break;
                        }
                        i13 = (i13 * 31) + c2;
                        i12++;
                    }
                    this.tlist.gapStart = i9;
                    String str2 = this.currentNamespacePrefix;
                    this.namespaceBindings = lookupNamespaceBinding(str2 == "" ? null : str2, cArr, i, i2, i3, this.namespaceBindings).namespaces;
                    this.currentNamespacePrefix = null;
                }
            }
        }
    }

    private String resolve(String str, boolean z) {
        if (z && str == null) {
            return "";
        }
        String resolve = this.namespaceBindings.resolve(str);
        if (resolve != null) {
            return resolve;
        }
        if (str != null) {
            error('e', "unknown namespace prefix '" + str + '\'');
        }
        return "";
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0191, code lost:
        if (r4 != null) goto L_0x019f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0193, code lost:
        r12.type = new gnu.xml.XName(r12.qname, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeStartTag() {
        /*
            r15 = this;
            int r0 = r15.attrCount
            if (r0 < 0) goto L_0x0295
            int r0 = r15.stringizingLevel
            if (r0 <= 0) goto L_0x000a
            goto L_0x0295
        L_0x000a:
            r0 = 0
            r15.inStartTag = r0
            r15.previous = r0
            java.lang.String r1 = r15.attrLocalName
            if (r1 == 0) goto L_0x0016
            r15.endAttribute()
        L_0x0016:
            int r1 = r15.nesting
            if (r1 != 0) goto L_0x001d
            gnu.xml.NamespaceBinding r1 = gnu.xml.NamespaceBinding.predefinedXML
            goto L_0x0025
        L_0x001d:
            java.lang.Object[] r2 = r15.workStack
            int r1 = r1 + -2
            r1 = r2[r1]
            gnu.xml.NamespaceBinding r1 = (gnu.xml.NamespaceBinding) r1
        L_0x0025:
            gnu.xml.NamespaceBinding r2 = r15.namespaceBindings
            r3 = 0
        L_0x0028:
            int r4 = r15.attrCount
            r5 = 0
            r6 = 1
            if (r3 > r4) goto L_0x00c6
            java.lang.Object[] r4 = r15.workStack
            int r7 = r15.nesting
            int r7 = r7 + r3
            int r7 = r7 - r6
            r4 = r4[r7]
            boolean r7 = r4 instanceof gnu.mapping.Symbol
            if (r7 == 0) goto L_0x00c2
            gnu.mapping.Symbol r4 = (gnu.mapping.Symbol) r4
            java.lang.String r7 = r4.getPrefix()
            java.lang.String r8 = ""
            if (r7 != r8) goto L_0x0045
            r7 = r5
        L_0x0045:
            java.lang.String r9 = r4.getNamespaceURI()
            if (r9 != r8) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r5 = r9
        L_0x004d:
            if (r3 <= 0) goto L_0x0055
            if (r7 != 0) goto L_0x0055
            if (r5 != 0) goto L_0x0055
            goto L_0x00c2
        L_0x0055:
            r9 = r2
            r10 = 0
        L_0x0057:
            if (r9 != r1) goto L_0x005a
            r10 = 1
        L_0x005a:
            if (r9 != 0) goto L_0x0065
            if (r7 != 0) goto L_0x0060
            if (r5 == 0) goto L_0x00c2
        L_0x0060:
            gnu.xml.NamespaceBinding r2 = r15.findNamespaceBinding(r7, r5, r2)
            goto L_0x00c2
        L_0x0065:
            java.lang.String r11 = r9.prefix
            if (r11 != r7) goto L_0x00bf
            java.lang.String r9 = r9.uri
            if (r9 == r5) goto L_0x00c2
            if (r10 == 0) goto L_0x0074
            gnu.xml.NamespaceBinding r2 = r15.findNamespaceBinding(r7, r5, r2)
            goto L_0x00c2
        L_0x0074:
            r7 = r2
        L_0x0075:
            if (r7 != 0) goto L_0x0097
            r7 = 1
        L_0x0078:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "_ns_"
            r9.append(r10)
            r9.append(r7)
            java.lang.String r9 = r9.toString()
            java.lang.String r9 = r9.intern()
            java.lang.String r10 = r2.resolve(r9)
            if (r10 != 0) goto L_0x0094
            goto L_0x00a3
        L_0x0094:
            int r7 = r7 + 1
            goto L_0x0078
        L_0x0097:
            java.lang.String r9 = r7.uri
            if (r9 != r5) goto L_0x00bc
            java.lang.String r9 = r7.prefix
            java.lang.String r10 = r2.resolve(r9)
            if (r10 != r5) goto L_0x00bc
        L_0x00a3:
            gnu.xml.NamespaceBinding r2 = r15.findNamespaceBinding(r9, r5, r2)
            java.lang.String r4 = r4.getLocalName()
            if (r5 != 0) goto L_0x00ae
            goto L_0x00af
        L_0x00ae:
            r8 = r5
        L_0x00af:
            java.lang.Object[] r5 = r15.workStack
            int r7 = r15.nesting
            int r7 = r7 + r3
            int r7 = r7 - r6
            gnu.mapping.Symbol r4 = gnu.mapping.Symbol.make(r8, r4, r9)
            r5[r7] = r4
            goto L_0x00c2
        L_0x00bc:
            gnu.xml.NamespaceBinding r7 = r7.next
            goto L_0x0075
        L_0x00bf:
            gnu.xml.NamespaceBinding r9 = r9.next
            goto L_0x0057
        L_0x00c2:
            int r3 = r3 + 1
            goto L_0x0028
        L_0x00c6:
            r1 = 0
        L_0x00c7:
            int r3 = r15.attrCount
            if (r1 > r3) goto L_0x026c
            java.lang.Object[] r3 = r15.workStack
            int r4 = r15.nesting
            int r4 = r4 + r1
            int r4 = r4 - r6
            r3 = r3[r4]
            boolean r4 = r3 instanceof gnu.xml.MappingInfo
            if (r4 != 0) goto L_0x00ed
            gnu.lists.Consumer r7 = r15.out
            gnu.lists.TreeList r8 = r15.tlist
            if (r7 != r8) goto L_0x00de
            goto L_0x00ed
        L_0x00de:
            r4 = r3
            gnu.mapping.Symbol r4 = (gnu.mapping.Symbol) r4
            java.lang.String r7 = r4.getNamespaceURI()
            java.lang.String r4 = r4.getLocalName()
            r12 = r5
            r10 = 0
            goto L_0x01a9
        L_0x00ed:
            if (r4 == 0) goto L_0x010f
            r4 = r3
            gnu.xml.MappingInfo r4 = (gnu.xml.MappingInfo) r4
            java.lang.String r7 = r4.prefix
            java.lang.String r8 = r4.local
            if (r1 <= 0) goto L_0x0104
            java.lang.String r9 = "xmlns"
            if (r7 != 0) goto L_0x00fe
            if (r8 == r9) goto L_0x0100
        L_0x00fe:
            if (r7 != r9) goto L_0x0104
        L_0x0100:
            java.lang.String r9 = "(namespace-node)"
            r10 = 1
            goto L_0x0124
        L_0x0104:
            if (r1 <= 0) goto L_0x0108
            r9 = 1
            goto L_0x0109
        L_0x0108:
            r9 = 0
        L_0x0109:
            java.lang.String r9 = r15.resolve(r7, r9)
            r10 = 0
            goto L_0x0124
        L_0x010f:
            r4 = r3
            gnu.mapping.Symbol r4 = (gnu.mapping.Symbol) r4
            gnu.xml.MappingInfo r7 = r15.lookupTag(r4)
            java.lang.String r8 = r7.prefix
            java.lang.String r9 = r7.local
            java.lang.String r4 = r4.getNamespaceURI()
            r10 = 0
            r14 = r9
            r9 = r4
            r4 = r7
            r7 = r8
            r8 = r14
        L_0x0124:
            int r4 = r4.tagHash
            int r11 = r15.mappingTableMask
            r11 = r11 & r4
            gnu.xml.MappingInfo[] r12 = r15.mappingTable
            r12 = r12[r11]
        L_0x012d:
            if (r12 != 0) goto L_0x015a
            gnu.xml.MappingInfo r12 = new gnu.xml.MappingInfo
            r12.<init>()
            r12.tagHash = r4
            r12.prefix = r7
            r12.local = r8
            gnu.xml.MappingInfo[] r4 = r15.mappingTable
            r4 = r4[r11]
            r12.nextInBucket = r4
            gnu.xml.MappingInfo[] r4 = r15.mappingTable
            r4[r11] = r12
            r12.uri = r9
            gnu.mapping.Symbol r4 = gnu.mapping.Symbol.make(r9, r8, r7)
            r12.qname = r4
            if (r1 != 0) goto L_0x019f
            gnu.xml.XName r4 = new gnu.xml.XName
            gnu.mapping.Symbol r7 = r12.qname
            r4.<init>(r7, r2)
            r12.type = r4
            r12.namespaces = r2
            goto L_0x019f
        L_0x015a:
            int r13 = r12.tagHash
            if (r13 != r4) goto L_0x0268
            java.lang.String r13 = r12.local
            if (r13 != r8) goto L_0x0268
            java.lang.String r13 = r12.prefix
            if (r13 != r7) goto L_0x0268
            java.lang.String r13 = r12.uri
            if (r13 != 0) goto L_0x0173
            r12.uri = r9
            gnu.mapping.Symbol r13 = gnu.mapping.Symbol.make(r9, r8, r7)
            r12.qname = r13
            goto L_0x0183
        L_0x0173:
            java.lang.String r13 = r12.uri
            if (r13 == r9) goto L_0x0179
            goto L_0x0268
        L_0x0179:
            gnu.mapping.Symbol r13 = r12.qname
            if (r13 != 0) goto L_0x0183
            gnu.mapping.Symbol r13 = gnu.mapping.Symbol.make(r9, r8, r7)
            r12.qname = r13
        L_0x0183:
            if (r1 != 0) goto L_0x019d
            gnu.xml.NamespaceBinding r13 = r12.namespaces
            if (r13 == r2) goto L_0x018d
            gnu.xml.NamespaceBinding r13 = r12.namespaces
            if (r13 != 0) goto L_0x0268
        L_0x018d:
            gnu.xml.XName r4 = r12.type
            r12.namespaces = r2
            if (r4 != 0) goto L_0x019f
            gnu.xml.XName r4 = new gnu.xml.XName
            gnu.mapping.Symbol r7 = r12.qname
            r4.<init>(r7, r2)
            r12.type = r4
            goto L_0x019f
        L_0x019d:
            gnu.mapping.Symbol r4 = r12.qname
        L_0x019f:
            java.lang.Object[] r4 = r15.workStack
            int r7 = r15.nesting
            int r7 = r7 + r1
            int r7 = r7 - r6
            r4[r7] = r12
            r4 = r8
            r7 = r9
        L_0x01a9:
            r8 = 1
        L_0x01aa:
            if (r8 >= r1) goto L_0x01ea
            java.lang.Object[] r9 = r15.workStack
            int r11 = r15.nesting
            int r11 = r11 + r8
            int r11 = r11 - r6
            r9 = r9[r11]
            boolean r11 = r9 instanceof gnu.mapping.Symbol
            if (r11 == 0) goto L_0x01bb
            gnu.mapping.Symbol r9 = (gnu.mapping.Symbol) r9
            goto L_0x01c3
        L_0x01bb:
            boolean r11 = r9 instanceof gnu.xml.MappingInfo
            if (r11 == 0) goto L_0x01e7
            gnu.xml.MappingInfo r9 = (gnu.xml.MappingInfo) r9
            gnu.mapping.Symbol r9 = r9.qname
        L_0x01c3:
            java.lang.String r11 = r9.getLocalPart()
            if (r4 != r11) goto L_0x01e7
            java.lang.String r11 = r9.getNamespaceURI()
            if (r7 != r11) goto L_0x01e7
            java.lang.Object[] r11 = r15.workStack
            int r13 = r15.nesting
            int r13 = r13 - r6
            r11 = r11[r13]
            boolean r13 = r11 instanceof gnu.xml.MappingInfo
            if (r13 == 0) goto L_0x01de
            gnu.xml.MappingInfo r11 = (gnu.xml.MappingInfo) r11
            gnu.mapping.Symbol r11 = r11.qname
        L_0x01de:
            r13 = 101(0x65, float:1.42E-43)
            java.lang.String r9 = duplicateAttributeMessage(r9, r11)
            r15.error(r13, r9)
        L_0x01e7:
            int r8 = r8 + 1
            goto L_0x01aa
        L_0x01ea:
            gnu.lists.Consumer r4 = r15.out
            gnu.lists.TreeList r7 = r15.tlist
            if (r4 != r7) goto L_0x0227
            if (r1 != 0) goto L_0x01f5
            gnu.xml.XName r3 = r12.type
            goto L_0x01f7
        L_0x01f5:
            gnu.mapping.Symbol r3 = r12.qname
        L_0x01f7:
            int r4 = r12.index
            if (r4 <= 0) goto L_0x0203
            gnu.lists.TreeList r7 = r15.tlist
            java.lang.Object[] r7 = r7.objects
            r7 = r7[r4]
            if (r7 == r3) goto L_0x020b
        L_0x0203:
            gnu.lists.TreeList r4 = r15.tlist
            int r4 = r4.find(r3)
            r12.index = r4
        L_0x020b:
            if (r1 != 0) goto L_0x0215
            gnu.lists.TreeList r3 = r15.tlist
            int r7 = r3.gapEnd
            r3.setElementName(r7, r4)
            goto L_0x0264
        L_0x0215:
            if (r10 == 0) goto L_0x021b
            boolean r3 = r15.namespacePrefixes
            if (r3 == 0) goto L_0x0264
        L_0x021b:
            gnu.lists.TreeList r3 = r15.tlist
            int[] r7 = r15.startIndexes
            int r8 = r1 + -1
            r7 = r7[r8]
            r3.setAttributeName(r7, r4)
            goto L_0x0264
        L_0x0227:
            if (r12 != 0) goto L_0x022a
            goto L_0x0231
        L_0x022a:
            if (r1 != 0) goto L_0x022f
            gnu.xml.XName r3 = r12.type
            goto L_0x0231
        L_0x022f:
            gnu.mapping.Symbol r3 = r12.qname
        L_0x0231:
            if (r1 != 0) goto L_0x0239
            gnu.lists.Consumer r4 = r15.out
            r4.startElement(r3)
            goto L_0x0264
        L_0x0239:
            if (r10 == 0) goto L_0x023f
            boolean r4 = r15.namespacePrefixes
            if (r4 == 0) goto L_0x0264
        L_0x023f:
            gnu.lists.Consumer r4 = r15.out
            r4.startAttribute(r3)
            int[] r3 = r15.startIndexes
            int r4 = r1 + -1
            r4 = r3[r4]
            int r7 = r15.attrCount
            if (r1 >= r7) goto L_0x0251
            r3 = r3[r1]
            goto L_0x0255
        L_0x0251:
            gnu.lists.TreeList r3 = r15.tlist
            int r3 = r3.gapStart
        L_0x0255:
            gnu.lists.TreeList r7 = r15.tlist
            int r4 = r4 + 5
            int r3 = r3 - r6
            gnu.lists.Consumer r8 = r15.out
            r7.consumeIRange(r4, r3, r8)
            gnu.lists.Consumer r3 = r15.out
            r3.endAttribute()
        L_0x0264:
            int r1 = r1 + 1
            goto L_0x00c7
        L_0x0268:
            gnu.xml.MappingInfo r12 = r12.nextInBucket
            goto L_0x012d
        L_0x026c:
            gnu.lists.Consumer r0 = r15.out
            boolean r1 = r0 instanceof gnu.kawa.sax.ContentConsumer
            if (r1 == 0) goto L_0x0277
            gnu.kawa.sax.ContentConsumer r0 = (gnu.kawa.sax.ContentConsumer) r0
            r0.endStartTag()
        L_0x0277:
            r0 = 1
        L_0x0278:
            int r1 = r15.attrCount
            if (r0 > r1) goto L_0x0287
            java.lang.Object[] r1 = r15.workStack
            int r2 = r15.nesting
            int r2 = r2 + r0
            int r2 = r2 - r6
            r1[r2] = r5
            int r0 = r0 + 1
            goto L_0x0278
        L_0x0287:
            gnu.lists.Consumer r0 = r15.out
            gnu.lists.TreeList r1 = r15.tlist
            if (r0 == r1) goto L_0x0292
            r15.base = r0
            r1.clear()
        L_0x0292:
            r0 = -1
            r15.attrCount = r0
        L_0x0295:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLFilter.closeStartTag():void");
    }

    /* access modifiers changed from: protected */
    public boolean checkWriteAtomic() {
        this.previous = 0;
        if (this.ignoringLevel > 0) {
            return false;
        }
        closeStartTag();
        return true;
    }

    public void write(int i) {
        if (checkWriteAtomic()) {
            this.base.write(i);
        }
    }

    public void writeBoolean(boolean z) {
        if (checkWriteAtomic()) {
            this.base.writeBoolean(z);
        }
    }

    public void writeFloat(float f) {
        if (checkWriteAtomic()) {
            this.base.writeFloat(f);
        }
    }

    public void writeDouble(double d) {
        if (checkWriteAtomic()) {
            this.base.writeDouble(d);
        }
    }

    public void writeInt(int i) {
        if (checkWriteAtomic()) {
            this.base.writeInt(i);
        }
    }

    public void writeLong(long j) {
        if (checkWriteAtomic()) {
            this.base.writeLong(j);
        }
    }

    public void writeDocumentUri(Object obj) {
        if (this.nesting == 2) {
            Consumer consumer = this.base;
            if (consumer instanceof TreeList) {
                ((TreeList) consumer).writeDocumentUri(obj);
            }
        }
    }

    public void consume(SeqPosition seqPosition) {
        writePosition(seqPosition.sequence, seqPosition.ipos);
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        if (this.ignoringLevel <= 0) {
            if (this.stringizingLevel > 0 && this.previous == 2) {
                if (this.stringizingElementNesting < 0) {
                    write(32);
                }
                this.previous = 0;
            }
            abstractSequence.consumeNext(i, this);
            if (this.stringizingLevel > 0 && this.stringizingElementNesting < 0) {
                this.previous = 2;
            }
        }
    }

    public void writeObject(Object obj) {
        if (this.ignoringLevel <= 0) {
            if (obj instanceof SeqPosition) {
                SeqPosition seqPosition = (SeqPosition) obj;
                writePosition(seqPosition.sequence, seqPosition.getPos());
            } else if (obj instanceof TreeList) {
                ((TreeList) obj).consume((Consumer) this);
            } else if ((obj instanceof List) && !(obj instanceof CharSeq)) {
                for (Object writeObject : (List) obj) {
                    writeObject(writeObject);
                }
            } else if (obj instanceof Keyword) {
                startAttribute(((Keyword) obj).asSymbol());
                this.previous = 1;
            } else {
                closeStartTag();
                if (obj instanceof UnescapedData) {
                    this.base.writeObject(obj);
                    this.previous = 0;
                    return;
                }
                if (this.previous == 2) {
                    write(32);
                }
                TextUtils.textValue(obj, this);
                this.previous = 2;
            }
        }
    }

    public XMLFilter(Consumer consumer) {
        MappingInfo[] mappingInfoArr = new MappingInfo[128];
        this.mappingTable = mappingInfoArr;
        this.mappingTableMask = mappingInfoArr.length - 1;
        this.base = consumer;
        this.out = consumer;
        if (consumer instanceof NodeTree) {
            this.tlist = (NodeTree) consumer;
        } else {
            this.tlist = new TreeList();
        }
        this.namespaceBindings = NamespaceBinding.predefinedXML;
    }

    public void write(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            writeJoiner();
        } else if (checkWriteAtomic()) {
            this.base.write(cArr, i, i2);
        }
    }

    public void write(String str) {
        write((CharSequence) str, 0, str.length());
    }

    public void write(CharSequence charSequence, int i, int i2) {
        if (i2 == 0) {
            writeJoiner();
        } else if (checkWriteAtomic()) {
            this.base.write(charSequence, i, i2);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean inElement() {
        int i = this.nesting;
        while (i > 0 && this.workStack[i - 1] == null) {
            i -= 2;
        }
        return i != 0;
    }

    public void linefeedFromParser() {
        if (inElement() && checkWriteAtomic()) {
            this.base.write(10);
        }
    }

    public void textFromParser(char[] cArr, int i, int i2) {
        if (!inElement()) {
            for (int i3 = 0; i3 != i2; i3++) {
                if (!Character.isWhitespace(cArr[i + i3])) {
                    error('e', "text at document level");
                    return;
                }
            }
        } else if (i2 > 0 && checkWriteAtomic()) {
            this.base.write(cArr, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void writeJoiner() {
        this.previous = 0;
        if (this.ignoringLevel == 0) {
            ((TreeList) this.base).writeJoiner();
        }
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        if (checkWriteAtomic()) {
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeCDATA(cArr, i, i2);
            } else {
                write(cArr, i, i2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void startElementCommon() {
        closeStartTag();
        if (this.stringizingLevel == 0) {
            ensureSpaceInWorkStack(this.nesting);
            this.workStack[this.nesting] = this.namespaceBindings;
            this.tlist.startElement(0);
            this.base = this.tlist;
            this.attrCount = 0;
        } else {
            if (this.previous == 2 && this.stringizingElementNesting < 0) {
                write(32);
            }
            this.previous = 0;
            if (this.stringizingElementNesting < 0) {
                this.stringizingElementNesting = this.nesting;
            }
        }
        this.nesting += 2;
    }

    public void emitStartElement(char[] cArr, int i, int i2) {
        closeStartTag();
        MappingInfo lookupTag = lookupTag(cArr, i, i2);
        startElementCommon();
        ensureSpaceInWorkStack(this.nesting - 1);
        this.workStack[this.nesting - 1] = lookupTag;
    }

    public void startElement(Object obj) {
        NamespaceBinding namespaceBinding;
        startElementCommon();
        if (this.stringizingLevel == 0) {
            ensureSpaceInWorkStack(this.nesting - 1);
            Object[] objArr = this.workStack;
            int i = this.nesting;
            objArr[i - 1] = obj;
            int i2 = this.copyNamespacesMode;
            if (i2 == 0) {
                this.namespaceBindings = NamespaceBinding.predefinedXML;
            } else if (i2 == 1 || i == 2) {
                this.namespaceBindings = obj instanceof XName ? ((XName) obj).getNamespaceNodes() : NamespaceBinding.predefinedXML;
            } else {
                int i3 = 2;
                while (true) {
                    if (i3 == this.nesting) {
                        namespaceBinding = null;
                        break;
                    }
                    Object[] objArr2 = this.workStack;
                    if (objArr2[i3 + 1] != null) {
                        namespaceBinding = (NamespaceBinding) objArr2[i3];
                        break;
                    }
                    i3 += 2;
                }
                if (namespaceBinding == null) {
                    this.namespaceBindings = obj instanceof XName ? ((XName) obj).getNamespaceNodes() : NamespaceBinding.predefinedXML;
                } else if (this.copyNamespacesMode == 2) {
                    this.namespaceBindings = namespaceBinding;
                } else if (obj instanceof XName) {
                    NamespaceBinding namespaceNodes = ((XName) obj).getNamespaceNodes();
                    if (NamespaceBinding.commonAncestor(namespaceBinding, namespaceNodes) == namespaceBinding) {
                        this.namespaceBindings = namespaceNodes;
                    } else {
                        this.namespaceBindings = mergeHelper(namespaceBinding, namespaceNodes);
                    }
                } else {
                    this.namespaceBindings = namespaceBinding;
                }
            }
        }
    }

    private NamespaceBinding mergeHelper(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        if (namespaceBinding2 == NamespaceBinding.predefinedXML) {
            return namespaceBinding;
        }
        NamespaceBinding mergeHelper = mergeHelper(namespaceBinding, namespaceBinding2.next);
        String str = namespaceBinding2.uri;
        if (mergeHelper == null) {
            if (str == null) {
                return mergeHelper;
            }
            mergeHelper = NamespaceBinding.predefinedXML;
        }
        String str2 = namespaceBinding2.prefix;
        String resolve = mergeHelper.resolve(str2);
        if (resolve != null ? !resolve.equals(str) : str != null) {
            return findNamespaceBinding(str2, str, mergeHelper);
        }
        return mergeHelper;
    }

    private boolean startAttributeCommon() {
        if (this.stringizingElementNesting >= 0) {
            this.ignoringLevel++;
        }
        int i = this.stringizingLevel;
        this.stringizingLevel = i + 1;
        if (i > 0) {
            return false;
        }
        if (this.attrCount < 0) {
            this.attrCount = 0;
        }
        ensureSpaceInWorkStack(this.nesting + this.attrCount);
        ensureSpaceInStartIndexes(this.attrCount);
        this.startIndexes[this.attrCount] = this.tlist.gapStart;
        this.attrCount++;
        return true;
    }

    public void startAttribute(Object obj) {
        this.previous = 0;
        if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            String localPart = symbol.getLocalPart();
            this.attrLocalName = localPart;
            this.attrPrefix = symbol.getPrefix();
            String namespaceURI = symbol.getNamespaceURI();
            if (namespaceURI == "http://www.w3.org/2000/xmlns/" || (namespaceURI == "" && localPart == "xmlns")) {
                error('e', "arttribute name cannot be 'xmlns' or in xmlns namespace");
            }
        }
        if (this.nesting == 2 && this.workStack[1] == null) {
            error('e', "attribute not allowed at document level");
        }
        if (this.attrCount < 0 && this.nesting > 0) {
            error('e', "attribute '" + obj + "' follows non-attribute content");
        }
        if (startAttributeCommon()) {
            Object[] objArr = this.workStack;
            int i = this.nesting;
            objArr[(this.attrCount + i) - 1] = obj;
            if (i == 0) {
                this.base.startAttribute(obj);
            } else {
                this.tlist.startAttribute(0);
            }
        }
    }

    public void emitStartAttribute(char[] cArr, int i, int i2) {
        if (this.attrLocalName != null) {
            endAttribute();
        }
        if (startAttributeCommon()) {
            MappingInfo lookupTag = lookupTag(cArr, i, i2);
            this.workStack[(this.nesting + this.attrCount) - 1] = lookupTag;
            String str = lookupTag.prefix;
            String str2 = lookupTag.local;
            this.attrLocalName = str2;
            this.attrPrefix = str;
            if (str != null) {
                if (str == "xmlns") {
                    this.currentNamespacePrefix = str2;
                }
            } else if (str2 == "xmlns" && str == null) {
                this.currentNamespacePrefix = "";
            }
            if (this.currentNamespacePrefix == null || this.namespacePrefixes) {
                this.tlist.startAttribute(0);
            }
        }
    }

    public void emitEndAttributes() {
        if (this.attrLocalName != null) {
            endAttribute();
        }
        closeStartTag();
    }

    public void emitEndElement(char[] cArr, int i, int i2) {
        if (this.attrLocalName != null) {
            error('e', "unclosed attribute");
            endAttribute();
        }
        if (!inElement()) {
            error('e', "unmatched end element");
            return;
        }
        if (cArr != null) {
            MappingInfo lookupTag = lookupTag(cArr, i, i2);
            Object obj = this.workStack[this.nesting - 1];
            if ((obj instanceof MappingInfo) && !this.mismatchReported) {
                MappingInfo mappingInfo = (MappingInfo) obj;
                if (!(lookupTag.local == mappingInfo.local && lookupTag.prefix == mappingInfo.prefix)) {
                    StringBuffer stringBuffer = new StringBuffer("</");
                    stringBuffer.append(cArr, i, i2);
                    stringBuffer.append("> matching <");
                    String str = mappingInfo.prefix;
                    if (str != null) {
                        stringBuffer.append(str);
                        stringBuffer.append(':');
                    }
                    stringBuffer.append(mappingInfo.local);
                    stringBuffer.append('>');
                    error('e', stringBuffer.toString());
                    this.mismatchReported = true;
                }
            }
        }
        closeStartTag();
        if (this.nesting > 0) {
            endElement();
        }
    }

    public void endElement() {
        closeStartTag();
        int i = this.nesting - 2;
        this.nesting = i;
        this.previous = 0;
        if (this.stringizingLevel == 0) {
            Object[] objArr = this.workStack;
            this.namespaceBindings = (NamespaceBinding) objArr[i];
            objArr[i] = null;
            objArr[i + 1] = null;
            this.base.endElement();
        } else if (this.stringizingElementNesting == i) {
            this.stringizingElementNesting = -1;
            this.previous = 2;
        }
    }

    public void emitEntityReference(char[] cArr, int i, int i2) {
        int i3;
        char c = cArr[i];
        if (i2 == 2 && cArr[i + 1] == 't') {
            if (c == 'l') {
                i3 = 60;
            } else if (c == 'g') {
                i3 = 62;
            }
            write(i3);
        } else if (i2 == 3) {
            if (c == 'a' && cArr[i + 1] == 'm' && cArr[i + 2] == 'p') {
                i3 = 38;
                write(i3);
            }
        } else if (i2 == 4) {
            char c2 = cArr[i + 1];
            char c3 = cArr[i + 2];
            char c4 = cArr[i + 3];
            if (c == 'q' && c2 == 'u' && c3 == 'o' && c4 == 't') {
                i3 = 34;
                write(i3);
            } else if (c == 'a' && c2 == 'p' && c3 == 'o' && c4 == 's') {
                i3 = 39;
                write(i3);
            }
        }
        i3 = 63;
        write(i3);
    }

    public void emitCharacterReference(int i, char[] cArr, int i2, int i3) {
        if (i >= 65536) {
            Char.print(i, this);
        } else {
            write(i);
        }
    }

    /* access modifiers changed from: protected */
    public void checkValidComment(char[] cArr, int i, int i2) {
        boolean z = true;
        while (true) {
            i2--;
            if (i2 >= 0) {
                boolean z2 = cArr[i + i2] == '-';
                if (!z || !z2) {
                    z = z2;
                } else {
                    error('e', "consecutive or final hyphen in XML comment");
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void writeComment(char[] cArr, int i, int i2) {
        checkValidComment(cArr, i, i2);
        commentFromParser(cArr, i, i2);
    }

    public void commentFromParser(char[] cArr, int i, int i2) {
        if (this.stringizingLevel == 0) {
            closeStartTag();
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeComment(cArr, i, i2);
            }
        } else if (this.stringizingElementNesting < 0) {
            this.base.write(cArr, i, i2);
        }
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        String replaceWhitespace = TextUtils.replaceWhitespace(str, true);
        int i3 = i + i2;
        while (true) {
            i3--;
            if (i3 < i) {
                break;
            }
            char c = cArr[i3];
            while (true) {
                if (c != '>' || i3 - 1 < i) {
                    break;
                }
                c = cArr[i3];
                if (c == '?') {
                    error('e', "'?>' is not allowed in a processing-instruction");
                    break;
                }
            }
        }
        if ("xml".equalsIgnoreCase(replaceWhitespace)) {
            error('e', "processing-instruction target may not be 'xml' (ignoring case)");
        }
        if (!XName.isNCName(replaceWhitespace)) {
            error('e', "processing-instruction target '" + replaceWhitespace + "' is not a valid Name");
        }
        processingInstructionCommon(replaceWhitespace, cArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void processingInstructionCommon(String str, char[] cArr, int i, int i2) {
        if (this.stringizingLevel == 0) {
            closeStartTag();
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeProcessingInstruction(str, cArr, i, i2);
            }
        } else if (this.stringizingElementNesting < 0) {
            this.base.write(cArr, i, i2);
        }
    }

    public void processingInstructionFromParser(char[] cArr, int i, int i2, int i3, int i4) {
        if (i2 != 3 || inElement() || cArr[i] != 'x' || cArr[i + 1] != 'm' || cArr[i + 2] != 'l') {
            processingInstructionCommon(new String(cArr, i, i2), cArr, i3, i4);
        }
    }

    public void startDocument() {
        closeStartTag();
        if (this.stringizingLevel > 0) {
            writeJoiner();
            return;
        }
        if (this.nesting == 0) {
            this.base.startDocument();
        } else {
            writeJoiner();
        }
        ensureSpaceInWorkStack(this.nesting);
        Object[] objArr = this.workStack;
        int i = this.nesting;
        objArr[i] = this.namespaceBindings;
        objArr[i + 1] = null;
        this.nesting = i + 2;
    }

    public void endDocument() {
        if (this.stringizingLevel > 0) {
            writeJoiner();
            return;
        }
        int i = this.nesting - 2;
        this.nesting = i;
        Object[] objArr = this.workStack;
        this.namespaceBindings = (NamespaceBinding) objArr[i];
        objArr[i] = null;
        objArr[i + 1] = null;
        if (i == 0) {
            this.base.endDocument();
        } else {
            writeJoiner();
        }
    }

    public void beginEntity(Object obj) {
        Consumer consumer = this.base;
        if (consumer instanceof XConsumer) {
            ((XConsumer) consumer).beginEntity(obj);
        }
    }

    public void endEntity() {
        Consumer consumer = this.base;
        if (consumer instanceof XConsumer) {
            ((XConsumer) consumer).endEntity();
        }
    }

    public XMLFilter append(char c) {
        write((int) c);
        return this;
    }

    public XMLFilter append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        append(charSequence, 0, charSequence.length());
        return this;
    }

    public XMLFilter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence, i, i2 - i);
        return this;
    }

    /* access modifiers changed from: package-private */
    public MappingInfo lookupTag(Symbol symbol) {
        String localPart = symbol.getLocalPart();
        String prefix = symbol.getPrefix();
        if (prefix == "") {
            prefix = null;
        }
        String namespaceURI = symbol.getNamespaceURI();
        int hash = MappingInfo.hash(prefix, localPart);
        int i = this.mappingTableMask & hash;
        MappingInfo mappingInfo = this.mappingTable[i];
        for (MappingInfo mappingInfo2 = mappingInfo; mappingInfo2 != null; mappingInfo2 = mappingInfo2.nextInBucket) {
            if (symbol == mappingInfo2.qname) {
                return mappingInfo2;
            }
            if (localPart == mappingInfo2.local && mappingInfo2.qname == null && ((namespaceURI == mappingInfo2.uri || mappingInfo2.uri == null) && prefix == mappingInfo2.prefix)) {
                mappingInfo2.uri = namespaceURI;
                mappingInfo2.qname = symbol;
                return mappingInfo2;
            }
        }
        MappingInfo mappingInfo3 = new MappingInfo();
        mappingInfo3.qname = symbol;
        mappingInfo3.prefix = prefix;
        mappingInfo3.uri = namespaceURI;
        mappingInfo3.local = localPart;
        mappingInfo3.tagHash = hash;
        mappingInfo3.nextInBucket = mappingInfo;
        this.mappingTable[i] = mappingInfo;
        return mappingInfo3;
    }

    /* access modifiers changed from: package-private */
    public MappingInfo lookupTag(char[] cArr, int i, int i2) {
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            char c = cArr[i + i6];
            if (c != ':' || i3 >= 0) {
                i5 = (i5 * 31) + c;
            } else {
                i3 = i6;
                i4 = i5;
                i5 = 0;
            }
        }
        int i7 = i4 ^ i5;
        int i8 = this.mappingTableMask & i7;
        MappingInfo mappingInfo = this.mappingTable[i8];
        for (MappingInfo mappingInfo2 = mappingInfo; mappingInfo2 != null; mappingInfo2 = mappingInfo2.nextInBucket) {
            if (i7 == mappingInfo2.tagHash && mappingInfo2.match(cArr, i, i2)) {
                return mappingInfo2;
            }
        }
        MappingInfo mappingInfo3 = new MappingInfo();
        mappingInfo3.tagHash = i7;
        if (i3 >= 0) {
            mappingInfo3.prefix = new String(cArr, i, i3).intern();
            int i9 = i3 + 1;
            mappingInfo3.local = new String(cArr, i + i9, i2 - i9).intern();
        } else {
            mappingInfo3.prefix = null;
            mappingInfo3.local = new String(cArr, i, i2).intern();
        }
        mappingInfo3.nextInBucket = mappingInfo;
        this.mappingTable[i8] = mappingInfo;
        return mappingInfo3;
    }

    private void ensureSpaceInWorkStack(int i) {
        Object[] objArr = this.workStack;
        if (objArr == null) {
            this.workStack = new Object[20];
        } else if (i >= objArr.length) {
            Object[] objArr2 = new Object[(objArr.length * 2)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            this.workStack = objArr2;
        }
    }

    private void ensureSpaceInStartIndexes(int i) {
        int[] iArr = this.startIndexes;
        if (iArr == null) {
            this.startIndexes = new int[20];
        } else if (i >= iArr.length) {
            int[] iArr2 = new int[(iArr.length * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            this.startIndexes = iArr2;
        }
    }

    public static String duplicateAttributeMessage(Symbol symbol, Object obj) {
        StringBuffer stringBuffer = new StringBuffer("duplicate attribute: ");
        String namespaceURI = symbol.getNamespaceURI();
        if (namespaceURI != null && namespaceURI.length() > 0) {
            stringBuffer.append('{');
            stringBuffer.append('}');
            stringBuffer.append(namespaceURI);
        }
        stringBuffer.append(symbol.getLocalPart());
        if (obj != null) {
            stringBuffer.append(" in <");
            stringBuffer.append(obj);
            stringBuffer.append('>');
        }
        return stringBuffer.toString();
    }

    public void error(char c, String str) {
        SourceMessages sourceMessages = this.messages;
        if (sourceMessages != null) {
            SourceLocator sourceLocator = this.locator;
            if (sourceLocator != null) {
                sourceMessages.error(c, sourceLocator, str);
            } else {
                sourceMessages.error(c, str);
            }
        } else {
            throw new RuntimeException(str);
        }
    }

    public boolean ignoring() {
        return this.ignoringLevel > 0;
    }

    public void setDocumentLocator(Locator locator2) {
        if (locator2 instanceof SourceLocator) {
            this.locator = (SourceLocator) locator2;
        }
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) {
        startElement(Symbol.make(str, str2));
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            startAttribute(Symbol.make(attributes.getURI(i), attributes.getLocalName(i)));
            write(attributes.getValue(i));
            endAttribute();
        }
    }

    public void endElement(String str, String str2, String str3) {
        endElement();
    }

    public void startElement(String str, AttributeList attributeList) {
        startElement(str.intern());
        int length = attributeList.getLength();
        for (int i = 0; i < length; i++) {
            String intern = attributeList.getName(i).intern();
            attributeList.getType(i);
            String value = attributeList.getValue(i);
            startAttribute(intern);
            write(value);
            endAttribute();
        }
    }

    public void endElement(String str) throws SAXException {
        endElement();
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        write(cArr, i, i2);
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
        write(cArr, i, i2);
    }

    public void processingInstruction(String str, String str2) {
        char[] charArray = str2.toCharArray();
        processingInstructionCommon(str, charArray, 0, charArray.length);
    }

    public void startPrefixMapping(String str, String str2) {
        this.namespaceBindings = findNamespaceBinding(str.intern(), str2.intern(), this.namespaceBindings);
    }

    public void endPrefixMapping(String str) {
        this.namespaceBindings = this.namespaceBindings.getNext();
    }

    public String getSystemId() {
        LineBufferedReader lineBufferedReader = this.in;
        if (lineBufferedReader == null) {
            return null;
        }
        return lineBufferedReader.getName();
    }

    public String getFileName() {
        LineBufferedReader lineBufferedReader = this.in;
        if (lineBufferedReader == null) {
            return null;
        }
        return lineBufferedReader.getName();
    }

    public int getLineNumber() {
        int lineNumber;
        LineBufferedReader lineBufferedReader = this.in;
        if (lineBufferedReader != null && (lineNumber = lineBufferedReader.getLineNumber()) >= 0) {
            return lineNumber + 1;
        }
        return -1;
    }

    public int getColumnNumber() {
        int columnNumber;
        LineBufferedReader lineBufferedReader = this.in;
        if (lineBufferedReader == null || (columnNumber = lineBufferedReader.getColumnNumber()) <= 0) {
            return -1;
        }
        return columnNumber;
    }
}
