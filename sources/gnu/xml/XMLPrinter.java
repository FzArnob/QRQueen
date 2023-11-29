package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.DFloNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

public class XMLPrinter extends OutPort implements PositionConsumer, XConsumer {
    private static final int COMMENT = -5;
    private static final int ELEMENT_END = -4;
    private static final int ELEMENT_START = -3;
    static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
    private static final int KEYWORD = -6;
    private static final int PROC_INST = -7;
    private static final int WORD = -2;
    public static final ThreadLocation doctypePublic = new ThreadLocation("doctype-public");
    public static final ThreadLocation doctypeSystem = new ThreadLocation("doctype-system");
    public static final ThreadLocation indentLoc = new ThreadLocation("xml-indent");
    boolean canonicalize = true;
    public boolean canonicalizeCDATA;
    Object[] elementNameStack = new Object[20];
    int elementNesting;
    public boolean escapeNonAscii = true;
    public boolean escapeText = true;
    boolean inAttribute = false;
    int inComment;
    boolean inDocument;
    boolean inStartTag = false;
    public boolean indentAttributes;
    boolean isHtml = false;
    boolean isHtmlOrXhtml = false;
    NamespaceBinding namespaceBindings = NamespaceBinding.predefinedXML;
    NamespaceBinding[] namespaceSaveStack = new NamespaceBinding[20];
    boolean needXMLdecl = false;
    int prev = 32;
    public int printIndent = -1;
    boolean printXMLdecl = false;
    char savedHighSurrogate;
    public boolean strict;
    Object style;
    boolean undeclareNamespaces = false;
    public int useEmptyElementTag = 2;

    public void beginEntity(Object obj) {
    }

    public void endEntity() {
    }

    public boolean ignoring() {
        return false;
    }

    public void writeBaseUri(Object obj) {
    }

    public void setPrintXMLdecl(boolean z) {
        this.printXMLdecl = z;
    }

    public XMLPrinter(OutPort outPort, boolean z) {
        super(outPort, z);
    }

    public XMLPrinter(Writer writer, boolean z) {
        super(writer, z);
    }

    public XMLPrinter(OutputStream outputStream, boolean z) {
        super((Writer) new OutputStreamWriter(outputStream), true, z);
    }

    public XMLPrinter(Writer writer) {
        super(writer);
    }

    public XMLPrinter(OutputStream outputStream) {
        super((Writer) new OutputStreamWriter(outputStream), false, false);
    }

    public XMLPrinter(OutputStream outputStream, Path path) {
        super(new OutputStreamWriter(outputStream), true, false, path);
    }

    public static XMLPrinter make(OutPort outPort, Object obj) {
        XMLPrinter xMLPrinter = new XMLPrinter(outPort, true);
        xMLPrinter.setStyle(obj);
        return xMLPrinter;
    }

    public static String toString(Object obj) {
        StringWriter stringWriter = new StringWriter();
        new XMLPrinter((Writer) stringWriter).writeObject(obj);
        return stringWriter.toString();
    }

    public void setStyle(Object obj) {
        this.style = obj;
        this.useEmptyElementTag = this.canonicalize ^ true ? 1 : 0;
        if ("html".equals(obj)) {
            this.isHtml = true;
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
            if (this.namespaceBindings == NamespaceBinding.predefinedXML) {
                this.namespaceBindings = XmlNamespace.HTML_BINDINGS;
            }
        } else if (this.namespaceBindings == XmlNamespace.HTML_BINDINGS) {
            this.namespaceBindings = NamespaceBinding.predefinedXML;
        }
        if ("xhtml".equals(obj)) {
            this.isHtmlOrXhtml = true;
            this.useEmptyElementTag = 2;
        }
        if ("plain".equals(obj)) {
            this.escapeText = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean mustHexEscape(int i) {
        return (i >= 127 && (i <= 159 || this.escapeNonAscii)) || i == 8232 || (i < 32 && (this.inAttribute || !(i == 9 || i == 10)));
    }

    public void write(int i) {
        closeTag();
        if (this.printIndent >= 0 && (i == 13 || i == 10)) {
            if (!(i == 10 && this.prev == 13)) {
                writeBreak(82);
            }
            if (this.inComment > 0) {
                this.inComment = 1;
            }
        } else if (!this.escapeText) {
            this.bout.write(i);
            this.prev = i;
        } else {
            int i2 = this.inComment;
            if (i2 > 0) {
                if (i != 45) {
                    this.inComment = 1;
                } else if (i2 == 1) {
                    this.inComment = 2;
                } else {
                    this.bout.write(32);
                }
                super.write(i);
                return;
            }
            this.prev = 59;
            if (i == 60 && (!this.isHtml || !this.inAttribute)) {
                this.bout.write("&lt;");
            } else if (i == 62) {
                this.bout.write("&gt;");
            } else if (i == 38) {
                this.bout.write("&amp;");
            } else if (i == 34 && this.inAttribute) {
                this.bout.write("&quot;");
            } else if (mustHexEscape(i)) {
                if (i >= 55296) {
                    if (i < 56320) {
                        this.savedHighSurrogate = (char) i;
                        return;
                    } else if (i < 57344) {
                        i = 65536 + ((this.savedHighSurrogate - 55296) * 1024) + (i - 56320);
                        this.savedHighSurrogate = 0;
                    }
                }
                PrettyWriter prettyWriter = this.bout;
                prettyWriter.write("&#x" + Integer.toHexString(i).toUpperCase() + ";");
            } else {
                this.bout.write(i);
                this.prev = i;
            }
        }
    }

    private void startWord() {
        closeTag();
        writeWordStart();
    }

    public void writeBoolean(boolean z) {
        startWord();
        super.print(z);
        writeWordEnd();
    }

    /* access modifiers changed from: protected */
    public void startNumber() {
        startWord();
    }

    /* access modifiers changed from: protected */
    public void endNumber() {
        writeWordEnd();
    }

    public void closeTag() {
        if (this.inStartTag && !this.inAttribute) {
            if (this.printIndent >= 0 && this.indentAttributes) {
                endLogicalBlock("");
            }
            this.bout.write(62);
            this.inStartTag = false;
            this.prev = -3;
        } else if (this.needXMLdecl) {
            this.bout.write("<?xml version=\"1.0\"?>\n");
            if (this.printIndent >= 0) {
                startLogicalBlock("", "", 2);
            }
            this.needXMLdecl = false;
            this.prev = 62;
        }
    }

    /* access modifiers changed from: package-private */
    public void setIndentMode() {
        String str = null;
        Object obj = indentLoc.get((Object) null);
        if (obj != null) {
            str = obj.toString();
        }
        if (str == null) {
            this.printIndent = -1;
        } else if (str.equals("pretty")) {
            this.printIndent = 0;
        } else if (str.equals("always") || str.equals("yes")) {
            this.printIndent = 1;
        } else {
            this.printIndent = -1;
        }
    }

    public void startDocument() {
        if (this.printXMLdecl) {
            this.needXMLdecl = true;
        }
        setIndentMode();
        this.inDocument = true;
        if (this.printIndent >= 0 && !this.needXMLdecl) {
            startLogicalBlock("", "", 2);
        }
    }

    public void endDocument() {
        this.inDocument = false;
        if (this.printIndent >= 0) {
            endLogicalBlock("");
        }
        freshLine();
    }

    /* access modifiers changed from: protected */
    public void writeQName(Object obj) {
        if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            String prefix = symbol.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                this.bout.write(prefix);
                this.bout.write(58);
            }
            this.bout.write(symbol.getLocalPart());
            return;
        }
        this.bout.write(obj == null ? "{null name}" : (String) obj);
    }

    public void startElement(Object obj) {
        int i;
        closeTag();
        if (this.elementNesting == 0) {
            if (!this.inDocument) {
                setIndentMode();
            }
            if (this.prev == -7) {
                write(10);
            }
            String str = null;
            Object obj2 = doctypeSystem.get((Object) null);
            if (obj2 != null) {
                String obj3 = obj2.toString();
                if (obj3.length() > 0) {
                    Object obj4 = doctypePublic.get((Object) null);
                    this.bout.write("<!DOCTYPE ");
                    this.bout.write(obj.toString());
                    if (obj4 != null) {
                        str = obj4.toString();
                    }
                    if (str == null || str.length() <= 0) {
                        this.bout.write(" SYSTEM \"");
                    } else {
                        this.bout.write(" PUBLIC \"");
                        this.bout.write(str);
                        this.bout.write("\" \"");
                    }
                    this.bout.write(obj3);
                    this.bout.write("\">");
                    println();
                }
            }
        }
        int i2 = this.printIndent;
        if (i2 >= 0) {
            int i3 = this.prev;
            if (i3 == -3 || i3 == -4 || i3 == -5) {
                writeBreak(i2 > 0 ? 82 : 78);
            }
            startLogicalBlock("", "", 2);
        }
        this.bout.write(60);
        writeQName(obj);
        if (this.printIndent >= 0 && this.indentAttributes) {
            startLogicalBlock("", "", 2);
        }
        Object[] objArr = this.elementNameStack;
        int i4 = this.elementNesting;
        objArr[i4] = obj;
        NamespaceBinding[] namespaceBindingArr = this.namespaceSaveStack;
        this.elementNesting = i4 + 1;
        namespaceBindingArr[i4] = this.namespaceBindings;
        if (obj instanceof XName) {
            NamespaceBinding namespaceBinding = ((XName) obj).namespaceNodes;
            NamespaceBinding commonAncestor = NamespaceBinding.commonAncestor(namespaceBinding, this.namespaceBindings);
            if (namespaceBinding == null) {
                i = 0;
            } else {
                i = namespaceBinding.count(commonAncestor);
            }
            NamespaceBinding[] namespaceBindingArr2 = new NamespaceBinding[i];
            boolean z = this.canonicalize;
            int i5 = 0;
            for (NamespaceBinding namespaceBinding2 = namespaceBinding; namespaceBinding2 != commonAncestor; namespaceBinding2 = namespaceBinding2.next) {
                namespaceBinding2.getUri();
                String prefix = namespaceBinding2.getPrefix();
                int i6 = i5;
                while (true) {
                    i6--;
                    if (i6 >= 0) {
                        NamespaceBinding namespaceBinding3 = namespaceBindingArr2[i6];
                        String prefix2 = namespaceBinding3.getPrefix();
                        if (prefix == prefix2) {
                            break;
                        } else if (z) {
                            if (prefix == null || (prefix2 != null && prefix.compareTo(prefix2) <= 0)) {
                                break;
                            }
                            namespaceBindingArr2[i6 + 1] = namespaceBinding3;
                        }
                    } else {
                        break;
                    }
                }
                namespaceBindingArr2[z ? i6 + 1 : i5] = namespaceBinding2;
                i5++;
            }
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                }
                NamespaceBinding namespaceBinding4 = namespaceBindingArr2[i5];
                String str2 = namespaceBinding4.prefix;
                String str3 = namespaceBinding4.uri;
                if (str3 != this.namespaceBindings.resolve(str2) && (str3 != null || str2 == null || this.undeclareNamespaces)) {
                    this.bout.write(32);
                    if (str2 == null) {
                        this.bout.write("xmlns");
                    } else {
                        this.bout.write("xmlns:");
                        this.bout.write(str2);
                    }
                    this.bout.write("=\"");
                    this.inAttribute = true;
                    if (str3 != null) {
                        write(str3);
                    }
                    this.inAttribute = false;
                    this.bout.write(34);
                }
            }
            if (this.undeclareNamespaces) {
                for (NamespaceBinding namespaceBinding5 = this.namespaceBindings; namespaceBinding5 != commonAncestor; namespaceBinding5 = namespaceBinding5.next) {
                    String str4 = namespaceBinding5.prefix;
                    if (namespaceBinding5.uri != null && namespaceBinding.resolve(str4) == null) {
                        this.bout.write(32);
                        if (str4 == null) {
                            this.bout.write("xmlns");
                        } else {
                            this.bout.write("xmlns:");
                            this.bout.write(str4);
                        }
                        this.bout.write("=\"\"");
                    }
                }
            }
            this.namespaceBindings = namespaceBinding;
        }
        int i7 = this.elementNesting;
        NamespaceBinding[] namespaceBindingArr3 = this.namespaceSaveStack;
        if (i7 >= namespaceBindingArr3.length) {
            NamespaceBinding[] namespaceBindingArr4 = new NamespaceBinding[(i7 * 2)];
            System.arraycopy(namespaceBindingArr3, 0, namespaceBindingArr4, 0, i7);
            this.namespaceSaveStack = namespaceBindingArr4;
            int i8 = this.elementNesting;
            Object[] objArr2 = new Object[(i8 * 2)];
            System.arraycopy(this.elementNameStack, 0, objArr2, 0, i8);
            this.elementNameStack = objArr2;
        }
        this.inStartTag = true;
        String htmlTag = getHtmlTag(obj);
        if ("script".equals(htmlTag) || "style".equals(htmlTag)) {
            this.escapeText = false;
        }
    }

    public static boolean isHtmlEmptyElementTag(String str) {
        int indexOf = HtmlEmptyTags.indexOf(str);
        return indexOf > 0 && HtmlEmptyTags.charAt(indexOf + -1) == '/' && HtmlEmptyTags.charAt(indexOf + str.length()) == '/';
    }

    /* access modifiers changed from: protected */
    public String getHtmlTag(Object obj) {
        if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            String namespaceURI = symbol.getNamespaceURI();
            if (namespaceURI == "http://www.w3.org/1999/xhtml" || (this.isHtmlOrXhtml && namespaceURI == "")) {
                return symbol.getLocalPart();
            }
            return null;
        } else if (this.isHtmlOrXhtml) {
            return obj.toString();
        } else {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0087 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void endElement() {
        /*
            r12 = this;
            int r0 = r12.useEmptyElementTag
            if (r0 != 0) goto L_0x0007
            r12.closeTag()
        L_0x0007:
            java.lang.Object[] r0 = r12.elementNameStack
            int r1 = r12.elementNesting
            r2 = 1
            int r1 = r1 - r2
            r0 = r0[r1]
            java.lang.String r1 = r12.getHtmlTag(r0)
            boolean r3 = r12.inStartTag
            r4 = -4
            r5 = 0
            java.lang.String r6 = ""
            r7 = 0
            java.lang.String r8 = ">"
            if (r3 == 0) goto L_0x00a1
            int r3 = r12.printIndent
            if (r3 < 0) goto L_0x0029
            boolean r3 = r12.indentAttributes
            if (r3 == 0) goto L_0x0029
            r12.endLogicalBlock(r6)
        L_0x0029:
            if (r1 == 0) goto L_0x0033
            boolean r3 = isHtmlEmptyElementTag(r1)
            if (r3 == 0) goto L_0x0033
            r3 = 1
            goto L_0x0034
        L_0x0033:
            r3 = 0
        L_0x0034:
            int r9 = r12.useEmptyElementTag
            if (r9 == 0) goto L_0x003c
            if (r1 == 0) goto L_0x0084
            if (r3 != 0) goto L_0x0084
        L_0x003c:
            boolean r9 = r0 instanceof gnu.mapping.Symbol
            if (r9 == 0) goto L_0x0084
            gnu.mapping.Symbol r0 = (gnu.mapping.Symbol) r0
            java.lang.String r9 = r0.getPrefix()
            java.lang.String r10 = r0.getNamespaceURI()
            java.lang.String r0 = r0.getLocalName()
            java.lang.String r11 = "></"
            if (r9 == r6) goto L_0x006d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r11)
            r10.append(r9)
            java.lang.String r9 = ":"
            r10.append(r9)
            r10.append(r0)
            r10.append(r8)
            java.lang.String r0 = r10.toString()
            goto L_0x0085
        L_0x006d:
            if (r10 == r6) goto L_0x0071
            if (r10 != 0) goto L_0x0084
        L_0x0071:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r11)
            r9.append(r0)
            r9.append(r8)
            java.lang.String r0 = r9.toString()
            goto L_0x0085
        L_0x0084:
            r0 = r5
        L_0x0085:
            if (r0 != 0) goto L_0x0099
            if (r3 == 0) goto L_0x008e
            boolean r0 = r12.isHtml
            if (r0 == 0) goto L_0x008e
            goto L_0x0098
        L_0x008e:
            int r0 = r12.useEmptyElementTag
            r3 = 2
            if (r0 != r3) goto L_0x0096
            java.lang.String r8 = " />"
            goto L_0x0098
        L_0x0096:
            java.lang.String r8 = "/>"
        L_0x0098:
            r0 = r8
        L_0x0099:
            gnu.text.PrettyWriter r3 = r12.bout
            r3.write((java.lang.String) r0)
            r12.inStartTag = r7
            goto L_0x00c7
        L_0x00a1:
            int r3 = r12.printIndent
            if (r3 < 0) goto L_0x00b8
            r12.setIndentation(r7, r7)
            int r3 = r12.prev
            if (r3 != r4) goto L_0x00b8
            int r3 = r12.printIndent
            if (r3 <= 0) goto L_0x00b3
            r3 = 82
            goto L_0x00b5
        L_0x00b3:
            r3 = 78
        L_0x00b5:
            r12.writeBreak(r3)
        L_0x00b8:
            gnu.text.PrettyWriter r3 = r12.bout
            java.lang.String r7 = "</"
            r3.write((java.lang.String) r7)
            r12.writeQName(r0)
            gnu.text.PrettyWriter r0 = r12.bout
            r0.write((java.lang.String) r8)
        L_0x00c7:
            int r0 = r12.printIndent
            if (r0 < 0) goto L_0x00ce
            r12.endLogicalBlock(r6)
        L_0x00ce:
            r12.prev = r4
            if (r1 == 0) goto L_0x00e8
            boolean r0 = r12.escapeText
            if (r0 != 0) goto L_0x00e8
            java.lang.String r0 = "script"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x00e6
            java.lang.String r0 = "style"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00e8
        L_0x00e6:
            r12.escapeText = r2
        L_0x00e8:
            gnu.xml.NamespaceBinding[] r0 = r12.namespaceSaveStack
            int r1 = r12.elementNesting
            int r1 = r1 - r2
            r12.elementNesting = r1
            r2 = r0[r1]
            r12.namespaceBindings = r2
            r0[r1] = r5
            java.lang.Object[] r0 = r12.elementNameStack
            r0[r1] = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.XMLPrinter.endElement():void");
    }

    public void startAttribute(Object obj) {
        if (!this.inStartTag && this.strict) {
            error("attribute not in element", "SENR0001");
        }
        if (this.inAttribute) {
            this.bout.write(34);
        }
        this.inAttribute = true;
        this.bout.write(32);
        if (this.printIndent >= 0) {
            writeBreakFill();
        }
        this.bout.write(obj.toString());
        this.bout.write("=\"");
        this.prev = 32;
    }

    public void endAttribute() {
        if (this.inAttribute) {
            if (this.prev != -6) {
                this.bout.write(34);
                this.inAttribute = false;
            }
            this.prev = 32;
        }
    }

    public void writeDouble(double d) {
        startWord();
        this.bout.write(formatDouble(d));
    }

    public void writeFloat(float f) {
        startWord();
        this.bout.write(formatFloat(f));
    }

    public static String formatDouble(double d) {
        if (Double.isNaN(d)) {
            return "NaN";
        }
        boolean z = d < 0.0d;
        if (Double.isInfinite(d)) {
            return z ? "-INF" : "INF";
        }
        double d2 = z ? -d : d;
        String d3 = Double.toString(d);
        if ((d2 >= 1000000.0d || d2 < 1.0E-6d) && d2 != 0.0d) {
            return RealNum.toStringScientific(d3);
        }
        return formatDecimal(RealNum.toStringDecimal(d3));
    }

    public static String formatFloat(float f) {
        if (Float.isNaN(f)) {
            return "NaN";
        }
        boolean z = f < 0.0f;
        if (Float.isInfinite(f)) {
            return z ? "-INF" : "INF";
        }
        float f2 = z ? -f : f;
        String f3 = Float.toString(f);
        if ((f2 >= 1000000.0f || ((double) f2) < 1.0E-6d) && ((double) f2) != 0.0d) {
            return RealNum.toStringScientific(f3);
        }
        return formatDecimal(RealNum.toStringDecimal(f3));
    }

    public static String formatDecimal(BigDecimal bigDecimal) {
        return formatDecimal(bigDecimal.toPlainString());
    }

    static String formatDecimal(String str) {
        char charAt;
        if (str.indexOf(46) < 0) {
            return str;
        }
        int length = str.length();
        int i = length;
        do {
            i--;
            charAt = str.charAt(i);
        } while (charAt == '0');
        if (charAt != '.') {
            i++;
        }
        return i == length ? str : str.substring(0, i);
    }

    public void print(Object obj) {
        String str;
        if (obj instanceof BigDecimal) {
            obj = formatDecimal((BigDecimal) obj);
        } else if ((obj instanceof Double) || (obj instanceof DFloNum)) {
            obj = formatDouble(((Number) obj).doubleValue());
        } else if (obj instanceof Float) {
            obj = formatFloat(((Float) obj).floatValue());
        }
        if (obj == null) {
            str = "(null)";
        } else {
            str = obj.toString();
        }
        write(str);
    }

    public void writeObject(Object obj) {
        if (obj instanceof SeqPosition) {
            this.bout.clearWordEnd();
            SeqPosition seqPosition = (SeqPosition) obj;
            seqPosition.sequence.consumeNext(seqPosition.ipos, this);
            if (seqPosition.sequence instanceof NodeTree) {
                this.prev = 45;
            }
        } else if ((obj instanceof Consumable) && !(obj instanceof UnescapedData)) {
            ((Consumable) obj).consume(this);
        } else if (obj instanceof Keyword) {
            startAttribute(((Keyword) obj).getName());
            this.prev = -6;
        } else {
            closeTag();
            if (obj instanceof UnescapedData) {
                this.bout.clearWordEnd();
                this.bout.write(((UnescapedData) obj).getData());
                this.prev = 45;
            } else if (obj instanceof Char) {
                Char.print(((Char) obj).intValue(), this);
            } else {
                startWord();
                this.prev = 32;
                print(obj);
                writeWordEnd();
                this.prev = -2;
            }
        }
    }

    public void write(String str, int i, int i2) {
        int i3;
        if (i2 > 0) {
            closeTag();
            int i4 = i2 + i;
            int i5 = 0;
            while (i < i4) {
                int i6 = i + 1;
                char charAt = str.charAt(i);
                if (mustHexEscape(charAt) || ((i3 = this.inComment) <= 0 ? charAt == '<' || charAt == '>' || charAt == '&' || (this.inAttribute && (charAt == '\"' || charAt < ' ')) : charAt == '-' || i3 == 2)) {
                    if (i5 > 0) {
                        this.bout.write(str, (i6 - 1) - i5, i5);
                    }
                    write(charAt);
                    i5 = 0;
                } else {
                    i5++;
                }
                i = i6;
            }
            if (i5 > 0) {
                this.bout.write(str, i4 - i5, i5);
            }
        }
        this.prev = 45;
    }

    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i2 > 0) {
            closeTag();
            int i4 = i2 + i;
            int i5 = 0;
            while (i < i4) {
                int i6 = i + 1;
                char c = cArr[i];
                if (mustHexEscape(c) || ((i3 = this.inComment) <= 0 ? c == '<' || c == '>' || c == '&' || (this.inAttribute && (c == '\"' || c < ' ')) : c == '-' || i3 == 2)) {
                    if (i5 > 0) {
                        this.bout.write(cArr, (i6 - 1) - i5, i5);
                    }
                    write(c);
                    i5 = 0;
                } else {
                    i5++;
                }
                i = i6;
            }
            if (i5 > 0) {
                this.bout.write(cArr, i4 - i5, i5);
            }
        }
        this.prev = 45;
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        abstractSequence.consumeNext(i, this);
    }

    public void beginComment() {
        int i;
        closeTag();
        int i2 = this.printIndent;
        if (i2 >= 0 && ((i = this.prev) == -3 || i == -4 || i == -5)) {
            writeBreak(i2 > 0 ? 82 : 78);
        }
        this.bout.write("<!--");
        this.inComment = 1;
    }

    public void endComment() {
        this.bout.write("-->");
        this.prev = -5;
        this.inComment = 0;
    }

    public void writeComment(String str) {
        beginComment();
        write(str);
        endComment();
    }

    public void writeComment(char[] cArr, int i, int i2) {
        beginComment();
        write(cArr, i, i2);
        endComment();
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        if (this.canonicalizeCDATA) {
            write(cArr, i, i2);
            return;
        }
        closeTag();
        this.bout.write("<![CDATA[");
        int i3 = i + i2;
        int i4 = i2;
        int i5 = i;
        while (i < i3 - 2) {
            if (cArr[i] == ']' && cArr[i + 1] == ']') {
                int i6 = i + 2;
                if (cArr[i6] == '>') {
                    if (i > i5) {
                        this.bout.write(cArr, i5, i - i5);
                    }
                    print("]]]><![CDATA[]>");
                    int i7 = i + 3;
                    i4 = i3 - i7;
                    i5 = i7;
                    i = i6;
                }
            }
            i++;
        }
        this.bout.write(cArr, i5, i4);
        this.bout.write("]]>");
        this.prev = 62;
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        if ("xml".equals(str)) {
            this.needXMLdecl = false;
        }
        closeTag();
        this.bout.write("<?");
        print(str);
        print(' ');
        this.bout.write(cArr, i, i2);
        this.bout.write("?>");
        this.prev = -7;
    }

    public void consume(SeqPosition seqPosition) {
        seqPosition.sequence.consumeNext(seqPosition.ipos, this);
    }

    public void error(String str, String str2) {
        throw new RuntimeException("serialization error: " + str + " [" + str2 + ']');
    }
}
