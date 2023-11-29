package gnu.xml;

import androidx.appcompat.widget.ActivityChooserView;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Symbol;
import java.io.Writer;

public class NodeTree extends TreeList {
    static int counter;
    int id;
    int idCount;
    String[] idNames;
    int[] idOffsets;

    public int nextPos(int i) {
        int posToDataIndex = posToDataIndex(i);
        int nextNodeIndex = nextNodeIndex(posToDataIndex, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (nextNodeIndex != posToDataIndex) {
            return nextNodeIndex << 1;
        }
        if (posToDataIndex == this.data.length) {
            return 0;
        }
        return (posToDataIndex << 1) + 3;
    }

    public static NodeTree make() {
        return new NodeTree();
    }

    public int getId() {
        if (this.id == 0) {
            int i = counter + 1;
            counter = i;
            this.id = i;
        }
        return this.id;
    }

    public int stableCompare(AbstractSequence abstractSequence) {
        int i = 0;
        if (this == abstractSequence) {
            return 0;
        }
        int stableCompare = super.stableCompare(abstractSequence);
        if (stableCompare != 0 || !(abstractSequence instanceof NodeTree)) {
            return stableCompare;
        }
        int id2 = getId();
        int id3 = ((NodeTree) abstractSequence).getId();
        if (id2 < id3) {
            i = -1;
        } else if (id2 > id3) {
            i = 1;
        }
        return i;
    }

    public SeqPosition getIteratorAtPos(int i) {
        return KNode.make(this, i);
    }

    public String posNamespaceURI(int i) {
        Object nextTypeObject = getNextTypeObject(i);
        if (nextTypeObject instanceof XName) {
            return ((XName) nextTypeObject).getNamespaceURI();
        }
        if (nextTypeObject instanceof Symbol) {
            return ((Symbol) nextTypeObject).getNamespaceURI();
        }
        return null;
    }

    public String posPrefix(int i) {
        int indexOf;
        String nextTypeName = getNextTypeName(i);
        if (nextTypeName != null && (indexOf = nextTypeName.indexOf(58)) >= 0) {
            return nextTypeName.substring(0, indexOf);
        }
        return null;
    }

    public String posLocalName(int i) {
        Object nextTypeObject = getNextTypeObject(i);
        if (nextTypeObject instanceof XName) {
            return ((XName) nextTypeObject).getLocalPart();
        }
        if (nextTypeObject instanceof Symbol) {
            return ((Symbol) nextTypeObject).getLocalName();
        }
        return getNextTypeName(i);
    }

    public boolean posIsDefaultNamespace(int i, String str) {
        throw new Error("posIsDefaultNamespace not implemented");
    }

    public String posLookupNamespaceURI(int i, String str) {
        if (getNextKind(i) == 33) {
            Object nextTypeObject = getNextTypeObject(i);
            if (nextTypeObject instanceof XName) {
                return ((XName) nextTypeObject).lookupNamespaceURI(str);
            }
            return null;
        }
        throw new IllegalArgumentException("argument must be an element");
    }

    public String posLookupPrefix(int i, String str) {
        throw new Error("posLookupPrefix not implemented");
    }

    public int posFirstChild(int i) {
        char c;
        int gotoChildrenStart = gotoChildrenStart(posToDataIndex(i));
        if (gotoChildrenStart < 0 || (c = this.data[gotoChildrenStart]) == 61707 || c == 61708 || c == 61713) {
            return -1;
        }
        return gotoChildrenStart << 1;
    }

    public boolean posHasAttributes(int i) {
        int gotoAttributesStart = gotoAttributesStart(posToDataIndex(i));
        if (gotoAttributesStart >= 0 && gotoAttributesStart >= 0 && this.data[gotoAttributesStart] == 61705) {
            return true;
        }
        return false;
    }

    public int getAttribute(int i, String str, String str2) {
        String str3 = null;
        String intern = str == null ? null : str.intern();
        if (str2 != null) {
            str3 = str2.intern();
        }
        return getAttributeI(i, intern, str3);
    }

    public int getAttributeI(int i, String str, String str2) {
        int firstAttributePos = firstAttributePos(i);
        while (firstAttributePos != 0 && getNextKind(firstAttributePos) == 35) {
            if ((str2 == null || posLocalName(firstAttributePos) == str2) && (str == null || posNamespaceURI(firstAttributePos) == str)) {
                return firstAttributePos;
            }
            firstAttributePos = nextPos(firstAttributePos);
        }
        return 0;
    }

    public Object typedValue(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringValue(posToDataIndex(i), stringBuffer);
        String stringBuffer2 = stringBuffer.toString();
        int nextKind = getNextKind(i);
        return (nextKind == 37 || nextKind == 36) ? stringBuffer2 : new UntypedAtomic(stringBuffer2);
    }

    public String posTarget(int i) {
        int posToDataIndex = posToDataIndex(i);
        if (this.data[posToDataIndex] == 61716) {
            return (String) this.objects[getIntN(posToDataIndex + 1)];
        }
        throw new ClassCastException("expected process-instruction");
    }

    public int ancestorAttribute(int i, String str, String str2) {
        while (i != -1) {
            int attributeI = getAttributeI(i, str, str2);
            if (attributeI != 0) {
                return attributeI;
            }
            i = parentPos(i);
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0066 A[LOOP:0: B:1:0x0006->B:30:0x0066, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0065 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.text.Path baseUriOfPos(int r6, boolean r7) {
        /*
            r5 = this;
            int r0 = r5.posToDataIndex(r6)
            r1 = 0
            r2 = r1
        L_0x0006:
            char[] r3 = r5.data
            int r3 = r3.length
            if (r0 != r3) goto L_0x000c
            return r1
        L_0x000c:
            char[] r3 = r5.data
            char r3 = r3[r0]
            r4 = 61714(0xf112, float:8.648E-41)
            if (r3 != r4) goto L_0x0026
            int r6 = r0 + 1
            int r6 = r5.getIntN(r6)
            if (r6 < 0) goto L_0x0048
            java.lang.Object[] r3 = r5.objects
            r6 = r3[r6]
            gnu.text.URIPath r6 = gnu.text.URIPath.makeURI(r6)
            goto L_0x0049
        L_0x0026:
            r4 = 40960(0xa000, float:5.7397E-41)
            if (r3 < r4) goto L_0x0030
            r4 = 45055(0xafff, float:6.3136E-41)
            if (r3 <= r4) goto L_0x0035
        L_0x0030:
            r4 = 61704(0xf108, float:8.6466E-41)
            if (r3 != r4) goto L_0x0048
        L_0x0035:
            java.lang.String r3 = "http://www.w3.org/XML/1998/namespace"
            java.lang.String r4 = "base"
            int r6 = r5.getAttributeI(r6, r3, r4)
            if (r6 == 0) goto L_0x0048
            java.lang.String r6 = gnu.kawa.xml.KNode.getNodeValue(r5, r6)
            gnu.text.URIPath r6 = gnu.text.URIPath.valueOf((java.lang.String) r6)
            goto L_0x0049
        L_0x0048:
            r6 = r1
        L_0x0049:
            if (r6 == 0) goto L_0x005e
            if (r2 == 0) goto L_0x0054
            if (r7 != 0) goto L_0x0050
            goto L_0x0054
        L_0x0050:
            gnu.text.Path r6 = r6.resolve((gnu.text.Path) r2)
        L_0x0054:
            r2 = r6
            boolean r6 = r2.isAbsolute()
            if (r6 != 0) goto L_0x005d
            if (r7 != 0) goto L_0x005e
        L_0x005d:
            return r2
        L_0x005e:
            int r0 = r5.parentOrEntityI(r0)
            r6 = -1
            if (r0 != r6) goto L_0x0066
            return r2
        L_0x0066:
            int r6 = r0 << 1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.NodeTree.baseUriOfPos(int, boolean):gnu.text.Path");
    }

    public String toString() {
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        consume((Consumer) new XMLPrinter((Writer) charArrayOutPort));
        charArrayOutPort.close();
        return charArrayOutPort.toString();
    }

    public void makeIDtableIfNeeded() {
        if (this.idNames == null) {
            this.idNames = new String[64];
            this.idOffsets = new int[64];
            int endPos = endPos();
            int i = 0;
            while (true) {
                i = nextMatching(i, ElementType.anyElement, endPos, true);
                if (i != 0) {
                    int attributeI = getAttributeI(i, NamespaceBinding.XML_NAMESPACE, CommonProperties.ID);
                    if (attributeI != 0) {
                        enterID(KNode.getNodeValue(this, attributeI), i);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void enterID(String str, int i) {
        int i2;
        String[] strArr = this.idNames;
        int[] iArr = this.idOffsets;
        if (strArr == null) {
            i2 = 64;
            this.idNames = new String[64];
            this.idOffsets = new int[64];
        } else {
            int i3 = this.idCount * 4;
            int length = strArr.length;
            if (i3 >= length * 3) {
                i2 = length * 2;
                this.idNames = new String[i2];
                this.idOffsets = new int[i2];
                this.idCount = 0;
                while (true) {
                    length--;
                    if (length < 0) {
                        break;
                    }
                    String str2 = strArr[length];
                    if (str2 != null) {
                        enterID(str2, iArr[length]);
                    }
                }
                strArr = this.idNames;
                iArr = this.idOffsets;
            } else {
                i2 = length;
            }
        }
        int hashCode = str.hashCode();
        int i4 = i2 - 1;
        int i5 = hashCode & i4;
        int i6 = ((~hashCode) << 1) | 1;
        while (true) {
            String str3 = strArr[i5];
            if (str3 == null) {
                strArr[i5] = str;
                iArr[i5] = i;
                this.idCount++;
                return;
            } else if (!str3.equals(str)) {
                i5 = (i5 + i6) & i4;
            } else {
                return;
            }
        }
    }

    public int lookupID(String str) {
        String[] strArr = this.idNames;
        int[] iArr = this.idOffsets;
        int length = strArr.length;
        int hashCode = str.hashCode();
        int i = length - 1;
        int i2 = hashCode & i;
        int i3 = ((~hashCode) << 1) | 1;
        while (true) {
            String str2 = strArr[i2];
            if (str2 == null) {
                return -1;
            }
            if (str2.equals(str)) {
                return iArr[i2];
            }
            i2 = (i2 + i3) & i;
        }
    }
}
