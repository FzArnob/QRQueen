package gnu.lists;

import androidx.appcompat.widget.ActivityChooserView;
import gnu.kawa.lispexpr.LispReader;
import gnu.text.Char;
import java.io.PrintWriter;

public class TreeList extends AbstractSequence implements Appendable, XConsumer, PositionConsumer, Consumable {
    protected static final int BEGIN_ATTRIBUTE_LONG = 61705;
    public static final int BEGIN_ATTRIBUTE_LONG_SIZE = 5;
    protected static final int BEGIN_DOCUMENT = 61712;
    protected static final int BEGIN_ELEMENT_LONG = 61704;
    protected static final int BEGIN_ELEMENT_SHORT = 40960;
    protected static final int BEGIN_ELEMENT_SHORT_INDEX_MAX = 4095;
    public static final int BEGIN_ENTITY = 61714;
    public static final int BEGIN_ENTITY_SIZE = 5;
    static final char BOOL_FALSE = '';
    static final char BOOL_TRUE = '';
    static final int BYTE_PREFIX = 61440;
    static final int CDATA_SECTION = 61717;
    static final int CHAR_FOLLOWS = 61702;
    static final int COMMENT = 61719;
    protected static final int DOCUMENT_URI = 61720;
    static final int DOUBLE_FOLLOWS = 61701;
    static final int END_ATTRIBUTE = 61706;
    public static final int END_ATTRIBUTE_SIZE = 1;
    protected static final int END_DOCUMENT = 61713;
    protected static final int END_ELEMENT_LONG = 61708;
    protected static final int END_ELEMENT_SHORT = 61707;
    protected static final int END_ENTITY = 61715;
    static final int FLOAT_FOLLOWS = 61700;
    public static final int INT_FOLLOWS = 61698;
    static final int INT_SHORT_ZERO = 49152;
    static final int JOINER = 61718;
    static final int LONG_FOLLOWS = 61699;
    public static final int MAX_CHAR_SHORT = 40959;
    static final int MAX_INT_SHORT = 8191;
    static final int MIN_INT_SHORT = -4096;
    static final char OBJECT_REF_FOLLOWS = '';
    static final int OBJECT_REF_SHORT = 57344;
    static final int OBJECT_REF_SHORT_INDEX_MAX = 4095;
    protected static final char POSITION_PAIR_FOLLOWS = '';
    static final char POSITION_REF_FOLLOWS = '';
    protected static final int PROCESSING_INSTRUCTION = 61716;
    public int attrStart;
    int currentParent;
    public char[] data;
    public int docStart;
    public int gapEnd;
    public int gapStart;
    public Object[] objects;
    public int oindex;

    public boolean ignoring() {
        return false;
    }

    public TreeList() {
        this.currentParent = -1;
        resizeObjects();
        this.gapEnd = 200;
        this.data = new char[200];
    }

    public TreeList(TreeList treeList, int i, int i2) {
        this();
        treeList.consumeIRange(i, i2, this);
    }

    public TreeList(TreeList treeList) {
        this(treeList, 0, treeList.data.length);
    }

    public void clear() {
        this.gapStart = 0;
        int length = this.data.length;
        this.gapEnd = length;
        this.attrStart = 0;
        if (length > 1500) {
            this.gapEnd = 200;
            this.data = new char[200];
        }
        this.objects = null;
        this.oindex = 0;
        resizeObjects();
    }

    public void ensureSpace(int i) {
        int i2 = this.gapEnd;
        int i3 = this.gapStart;
        int i4 = i2 - i3;
        if (i > i4) {
            char[] cArr = this.data;
            int length = cArr.length;
            int i5 = (length - i4) + i;
            int i6 = length * 2;
            if (i6 >= i5) {
                i5 = i6;
            }
            char[] cArr2 = new char[i5];
            if (i3 > 0) {
                System.arraycopy(cArr, 0, cArr2, 0, i3);
            }
            int i7 = this.gapEnd;
            int i8 = length - i7;
            if (i8 > 0) {
                System.arraycopy(this.data, i7, cArr2, i5 - i8, i8);
            }
            this.gapEnd = i5 - i8;
            this.data = cArr2;
        }
    }

    public final void resizeObjects() {
        Object[] objArr;
        Object[] objArr2 = this.objects;
        if (objArr2 == null) {
            objArr = new Object[100];
        } else {
            int length = objArr2.length;
            Object[] objArr3 = new Object[(length * 2)];
            System.arraycopy(objArr2, 0, objArr3, 0, length);
            objArr = objArr3;
        }
        this.objects = objArr;
    }

    public int find(Object obj) {
        if (this.oindex == this.objects.length) {
            resizeObjects();
        }
        Object[] objArr = this.objects;
        int i = this.oindex;
        objArr[i] = obj;
        this.oindex = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public final int getIntN(int i) {
        char[] cArr = this.data;
        return (cArr[i + 1] & LispReader.TOKEN_ESCAPE_CHAR) | (cArr[i] << 16);
    }

    /* access modifiers changed from: protected */
    public final long getLongN(int i) {
        char[] cArr = this.data;
        return ((((long) cArr[i]) & 65535) << 48) | ((((long) cArr[i + 1]) & 65535) << 32) | ((((long) cArr[i + 2]) & 65535) << 16) | (65535 & ((long) cArr[i + 3]));
    }

    public final void setIntN(int i, int i2) {
        char[] cArr = this.data;
        cArr[i] = (char) (i2 >> 16);
        cArr[i + 1] = (char) i2;
    }

    public void consume(SeqPosition seqPosition) {
        ensureSpace(3);
        int find = find(seqPosition.copy());
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = POSITION_REF_FOLLOWS;
        setIntN(i2, find);
        this.gapStart += 2;
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        ensureSpace(5);
        this.data[this.gapStart] = POSITION_PAIR_FOLLOWS;
        setIntN(this.gapStart + 1, find(abstractSequence));
        setIntN(this.gapStart + 3, i);
        this.gapStart += 5;
    }

    public void writeObject(Object obj) {
        ensureSpace(3);
        int find = find(obj);
        if (find < 4096) {
            char[] cArr = this.data;
            int i = this.gapStart;
            this.gapStart = i + 1;
            cArr[i] = (char) (find | OBJECT_REF_SHORT);
            return;
        }
        char[] cArr2 = this.data;
        int i2 = this.gapStart;
        int i3 = i2 + 1;
        this.gapStart = i3;
        cArr2[i2] = OBJECT_REF_FOLLOWS;
        setIntN(i3, find);
        this.gapStart += 2;
    }

    public void writeDocumentUri(Object obj) {
        ensureSpace(3);
        int find = find(obj);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        this.gapStart = i2;
        cArr[i] = 61720;
        setIntN(i2, find);
        this.gapStart += 2;
    }

    public void writeComment(char[] cArr, int i, int i2) {
        ensureSpace(i2 + 3);
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.data[i3] = 61719;
        setIntN(i4, i2);
        int i5 = i4 + 2;
        System.arraycopy(cArr, i, this.data, i5, i2);
        this.gapStart = i5 + i2;
    }

    public void writeComment(String str, int i, int i2) {
        ensureSpace(i2 + 3);
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.data[i3] = 61719;
        setIntN(i4, i2);
        int i5 = i4 + 2;
        str.getChars(i, i + i2, this.data, i5);
        this.gapStart = i5 + i2;
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        ensureSpace(i2 + 5);
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.data[i3] = 61716;
        setIntN(i4, find(str));
        setIntN(i4 + 2, i2);
        int i5 = i4 + 4;
        System.arraycopy(cArr, i, this.data, i5, i2);
        this.gapStart = i5 + i2;
    }

    public void writeProcessingInstruction(String str, String str2, int i, int i2) {
        ensureSpace(i2 + 5);
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.data[i3] = 61716;
        setIntN(i4, find(str));
        setIntN(i4 + 2, i2);
        int i5 = i4 + 4;
        str2.getChars(i, i + i2, this.data, i5);
        this.gapStart = i5 + i2;
    }

    public void startElement(Object obj) {
        startElement(find(obj));
    }

    public void startDocument() {
        ensureSpace(6);
        int i = this.gapEnd - 1;
        this.gapEnd = i;
        int i2 = this.gapStart;
        char[] cArr = this.data;
        cArr[i2] = 61712;
        if (this.docStart == 0) {
            int i3 = i2 + 1;
            this.docStart = i3;
            setIntN(i3, i - cArr.length);
            int i4 = i2 + 3;
            int i5 = this.currentParent;
            int i6 = -1;
            if (i5 != -1) {
                i6 = i5 - i2;
            }
            setIntN(i4, i6);
            this.gapStart = i2 + 5;
            this.currentParent = i2;
            this.data[this.gapEnd] = 61713;
            return;
        }
        throw new Error("nested document");
    }

    public void endDocument() {
        int i;
        char[] cArr = this.data;
        int i2 = this.gapEnd;
        if (cArr[i2] == END_DOCUMENT && (i = this.docStart) > 0 && cArr[this.currentParent] == BEGIN_DOCUMENT) {
            this.gapEnd = i2 + 1;
            setIntN(i, (this.gapStart - i) + 1);
            this.docStart = 0;
            char[] cArr2 = this.data;
            int i3 = this.gapStart;
            this.gapStart = i3 + 1;
            cArr2[i3] = 61713;
            int intN = getIntN(this.currentParent + 3);
            if (intN < -1) {
                intN += this.currentParent;
            }
            this.currentParent = intN;
            return;
        }
        throw new Error("unexpected endDocument");
    }

    public void beginEntity(Object obj) {
        if (this.gapStart == 0) {
            ensureSpace(6);
            this.gapEnd--;
            int i = this.gapStart;
            this.data[i] = 61714;
            setIntN(i + 1, find(obj));
            int i2 = i + 3;
            int i3 = this.currentParent;
            int i4 = -1;
            if (i3 != -1) {
                i4 = i3 - i;
            }
            setIntN(i2, i4);
            this.gapStart = i + 5;
            this.currentParent = i;
            this.data[this.gapEnd] = 61715;
        }
    }

    public void endEntity() {
        int i = this.gapEnd;
        int i2 = i + 1;
        char[] cArr = this.data;
        if (i2 == cArr.length && cArr[i] == END_ENTITY) {
            int i3 = this.currentParent;
            if (cArr[i3] == 61714) {
                this.gapEnd = i + 1;
                int i4 = this.gapStart;
                this.gapStart = i4 + 1;
                cArr[i4] = 61715;
                int intN = getIntN(i3 + 3);
                if (intN < -1) {
                    intN += this.currentParent;
                }
                this.currentParent = intN;
                return;
            }
            throw new Error("unexpected endEntity");
        }
    }

    public void startElement(int i) {
        ensureSpace(10);
        int i2 = this.gapEnd - 7;
        this.gapEnd = i2;
        char[] cArr = this.data;
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.gapStart = i4;
        cArr[i3] = 61704;
        setIntN(i4, i2 - cArr.length);
        this.gapStart += 2;
        char[] cArr2 = this.data;
        int i5 = this.gapEnd;
        cArr2[i5] = 61708;
        setIntN(i5 + 1, i);
        setIntN(this.gapEnd + 3, this.gapStart - 3);
        setIntN(this.gapEnd + 5, this.currentParent);
        this.currentParent = this.gapStart - 3;
    }

    public void setElementName(int i, int i2) {
        if (this.data[i] == BEGIN_ELEMENT_LONG) {
            int intN = getIntN(i + 1);
            if (intN < 0) {
                i = this.data.length;
            }
            i += intN;
        }
        if (i >= this.gapEnd) {
            setIntN(i + 1, i2);
            return;
        }
        throw new Error("setElementName before gapEnd");
    }

    public void endElement() {
        char[] cArr = this.data;
        int i = this.gapEnd;
        if (cArr[i] == END_ELEMENT_LONG) {
            int intN = getIntN(i + 1);
            int intN2 = getIntN(this.gapEnd + 3);
            int intN3 = getIntN(this.gapEnd + 5);
            this.currentParent = intN3;
            this.gapEnd += 7;
            int i2 = this.gapStart;
            int i3 = i2 - intN2;
            int i4 = intN2 - intN3;
            if (intN >= 4095 || i3 >= 65536 || i4 >= 65536) {
                this.data[intN2] = 61704;
                setIntN(intN2 + 1, i3);
                char[] cArr2 = this.data;
                int i5 = this.gapStart;
                cArr2[i5] = 61708;
                setIntN(i5 + 1, intN);
                setIntN(this.gapStart + 3, -i3);
                int i6 = this.gapStart;
                if (intN3 >= i6 || intN2 <= i6) {
                    intN3 -= i6;
                }
                setIntN(i6 + 5, intN3);
                this.gapStart += 7;
                return;
            }
            char[] cArr3 = this.data;
            cArr3[intN2] = (char) (intN | BEGIN_ELEMENT_SHORT);
            char c = (char) i3;
            cArr3[intN2 + 1] = c;
            cArr3[intN2 + 2] = (char) i4;
            cArr3[i2] = 61707;
            cArr3[i2 + 1] = c;
            this.gapStart = i2 + 2;
            return;
        }
        throw new Error("unexpected endElement");
    }

    public void startAttribute(Object obj) {
        startAttribute(find(obj));
    }

    public void startAttribute(int i) {
        ensureSpace(6);
        this.gapEnd--;
        char[] cArr = this.data;
        int i2 = this.gapStart;
        int i3 = i2 + 1;
        this.gapStart = i3;
        cArr[i2] = 61705;
        if (this.attrStart == 0) {
            this.attrStart = i3;
            setIntN(i3, i);
            setIntN(this.gapStart + 2, this.gapEnd - this.data.length);
            this.gapStart += 4;
            this.data[this.gapEnd] = 61706;
            return;
        }
        throw new Error("nested attribute");
    }

    public void setAttributeName(int i, int i2) {
        setIntN(i + 1, i2);
    }

    public void endAttribute() {
        int i = this.attrStart;
        if (i > 0) {
            char[] cArr = this.data;
            int i2 = this.gapEnd;
            if (cArr[i2] == END_ATTRIBUTE) {
                this.gapEnd = i2 + 1;
                setIntN(i + 2, (this.gapStart - i) + 1);
                this.attrStart = 0;
                char[] cArr2 = this.data;
                int i3 = this.gapStart;
                this.gapStart = i3 + 1;
                cArr2[i3] = 61706;
                return;
            }
            throw new Error("unexpected endAttribute");
        }
    }

    public Consumer append(char c) {
        write((int) c);
        return this;
    }

    public void write(int i) {
        ensureSpace(3);
        if (i <= 40959) {
            char[] cArr = this.data;
            int i2 = this.gapStart;
            this.gapStart = i2 + 1;
            cArr[i2] = (char) i;
        } else if (i < 65536) {
            char[] cArr2 = this.data;
            int i3 = this.gapStart;
            int i4 = i3 + 1;
            cArr2[i3] = 61702;
            this.gapStart = i4 + 1;
            cArr2[i4] = (char) i;
        } else {
            Char.print(i, this);
        }
    }

    public void writeBoolean(boolean z) {
        ensureSpace(1);
        char[] cArr = this.data;
        int i = this.gapStart;
        this.gapStart = i + 1;
        cArr[i] = z ? BOOL_TRUE : BOOL_FALSE;
    }

    public void writeByte(int i) {
        ensureSpace(1);
        char[] cArr = this.data;
        int i2 = this.gapStart;
        this.gapStart = i2 + 1;
        cArr[i2] = (char) ((i & 255) + BYTE_PREFIX);
    }

    public void writeInt(int i) {
        ensureSpace(3);
        if (i < MIN_INT_SHORT || i > MAX_INT_SHORT) {
            char[] cArr = this.data;
            int i2 = this.gapStart;
            int i3 = i2 + 1;
            this.gapStart = i3;
            cArr[i2] = 61698;
            setIntN(i3, i);
            this.gapStart += 2;
            return;
        }
        char[] cArr2 = this.data;
        int i4 = this.gapStart;
        this.gapStart = i4 + 1;
        cArr2[i4] = (char) (i + INT_SHORT_ZERO);
    }

    public void writeLong(long j) {
        ensureSpace(5);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        cArr[i] = 61699;
        int i3 = i2 + 1;
        cArr[i2] = (char) ((int) (j >>> 48));
        int i4 = i3 + 1;
        cArr[i3] = (char) ((int) (j >>> 32));
        int i5 = i4 + 1;
        cArr[i4] = (char) ((int) (j >>> 16));
        this.gapStart = i5 + 1;
        cArr[i5] = (char) ((int) j);
    }

    public void writeFloat(float f) {
        ensureSpace(3);
        int floatToIntBits = Float.floatToIntBits(f);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        cArr[i] = 61700;
        int i3 = i2 + 1;
        cArr[i2] = (char) (floatToIntBits >>> 16);
        this.gapStart = i3 + 1;
        cArr[i3] = (char) floatToIntBits;
    }

    public void writeDouble(double d) {
        ensureSpace(5);
        long doubleToLongBits = Double.doubleToLongBits(d);
        char[] cArr = this.data;
        int i = this.gapStart;
        int i2 = i + 1;
        cArr[i] = 61701;
        int i3 = i2 + 1;
        cArr[i2] = (char) ((int) (doubleToLongBits >>> 48));
        int i4 = i3 + 1;
        cArr[i3] = (char) ((int) (doubleToLongBits >>> 32));
        int i5 = i4 + 1;
        cArr[i4] = (char) ((int) (doubleToLongBits >>> 16));
        this.gapStart = i5 + 1;
        cArr[i5] = (char) ((int) doubleToLongBits);
    }

    public void writeJoiner() {
        ensureSpace(1);
        char[] cArr = this.data;
        int i = this.gapStart;
        this.gapStart = i + 1;
        cArr[i] = 61718;
    }

    public void write(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            writeJoiner();
        }
        ensureSpace(i2);
        while (i2 > 0) {
            int i3 = i + 1;
            char c = cArr[i];
            i2--;
            if (c <= 40959) {
                char[] cArr2 = this.data;
                int i4 = this.gapStart;
                this.gapStart = i4 + 1;
                cArr2[i4] = c;
            } else {
                write((int) c);
                ensureSpace(i2);
            }
            i = i3;
        }
    }

    public void write(String str) {
        write((CharSequence) str, 0, str.length());
    }

    public void write(CharSequence charSequence, int i, int i2) {
        if (i2 == 0) {
            writeJoiner();
        }
        ensureSpace(i2);
        while (i2 > 0) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            i2--;
            if (charAt <= 40959) {
                char[] cArr = this.data;
                int i4 = this.gapStart;
                this.gapStart = i4 + 1;
                cArr[i4] = charAt;
            } else {
                write((int) charAt);
                ensureSpace(i2);
            }
            i = i3;
        }
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        ensureSpace(i2 + 3);
        int i3 = this.gapStart;
        int i4 = i3 + 1;
        this.data[i3] = 61717;
        setIntN(i4, i2);
        int i5 = i4 + 2;
        System.arraycopy(cArr, i, this.data, i5, i2);
        this.gapStart = i5 + i2;
    }

    public Consumer append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        return append(charSequence, 0, charSequence.length());
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        while (i < i2) {
            append(charSequence.charAt(i));
            i++;
        }
        return this;
    }

    public boolean isEmpty() {
        if ((this.gapStart == 0 ? this.gapEnd : 0) == this.data.length) {
            return true;
        }
        return false;
    }

    public int size() {
        int i = 0;
        int i2 = 0;
        while (true) {
            i = nextPos(i);
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public int createPos(int i, boolean z) {
        return createRelativePos(0, i, z);
    }

    public final int posToDataIndex(int i) {
        if (i == -1) {
            return this.data.length;
        }
        int i2 = i >>> 1;
        int i3 = i & 1;
        if (i3 != 0) {
            i2--;
        }
        int i4 = this.gapStart;
        if (i2 >= i4) {
            i2 += this.gapEnd - i4;
        }
        if (i3 == 0) {
            return i2;
        }
        int nextDataIndex = nextDataIndex(i2);
        if (nextDataIndex < 0) {
            return this.data.length;
        }
        int i5 = this.gapStart;
        return nextDataIndex == i5 ? nextDataIndex + (this.gapEnd - i5) : nextDataIndex;
    }

    public int firstChildPos(int i) {
        int gotoChildrenStart = gotoChildrenStart(posToDataIndex(i));
        if (gotoChildrenStart < 0) {
            return 0;
        }
        return gotoChildrenStart << 1;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0058 A[RETURN] */
    public final int gotoChildrenStart(int r4) {
        /*
            r3 = this;
            char[] r0 = r3.data
            int r1 = r0.length
            r2 = -1
            if (r4 != r1) goto L_0x0007
            return r2
        L_0x0007:
            char r0 = r0[r4]
            r1 = 40960(0xa000, float:5.7397E-41)
            if (r0 < r1) goto L_0x0013
            r1 = 45055(0xafff, float:6.3136E-41)
            if (r0 <= r1) goto L_0x0018
        L_0x0013:
            r1 = 61704(0xf108, float:8.6466E-41)
            if (r0 != r1) goto L_0x001b
        L_0x0018:
            int r4 = r4 + 3
            goto L_0x0029
        L_0x001b:
            r1 = 61712(0xf110, float:8.6477E-41)
            if (r0 == r1) goto L_0x0027
            r1 = 61714(0xf112, float:8.648E-41)
            if (r0 != r1) goto L_0x0026
            goto L_0x0027
        L_0x0026:
            return r2
        L_0x0027:
            int r4 = r4 + 5
        L_0x0029:
            int r0 = r3.gapStart
            if (r4 < r0) goto L_0x0031
            int r1 = r3.gapEnd
            int r1 = r1 - r0
            int r4 = r4 + r1
        L_0x0031:
            char[] r0 = r3.data
            char r0 = r0[r4]
            r1 = 61705(0xf109, float:8.6467E-41)
            if (r0 != r1) goto L_0x0047
            int r0 = r4 + 3
            int r0 = r3.getIntN(r0)
            if (r0 >= 0) goto L_0x0045
            char[] r4 = r3.data
            int r4 = r4.length
        L_0x0045:
            int r4 = r4 + r0
            goto L_0x0029
        L_0x0047:
            r1 = 61706(0xf10a, float:8.6469E-41)
            if (r0 == r1) goto L_0x0059
            r1 = 61718(0xf116, float:8.6485E-41)
            if (r0 != r1) goto L_0x0052
            goto L_0x0059
        L_0x0052:
            r1 = 61720(0xf118, float:8.6488E-41)
            if (r0 != r1) goto L_0x0058
            goto L_0x0018
        L_0x0058:
            return r4
        L_0x0059:
            int r4 = r4 + 1
            goto L_0x0029
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.gotoChildrenStart(int):int");
    }

    public int parentPos(int i) {
        int posToDataIndex = posToDataIndex(i);
        do {
            posToDataIndex = parentOrEntityI(posToDataIndex);
            if (posToDataIndex == -1) {
                return -1;
            }
        } while (this.data[posToDataIndex] == 61714);
        return posToDataIndex << 1;
    }

    public int parentOrEntityPos(int i) {
        int parentOrEntityI = parentOrEntityI(posToDataIndex(i));
        if (parentOrEntityI < 0) {
            return -1;
        }
        return parentOrEntityI << 1;
    }

    public int parentOrEntityI(int i) {
        char c;
        char[] cArr = this.data;
        if (i == cArr.length) {
            return -1;
        }
        char c2 = cArr[i];
        if (c2 == BEGIN_DOCUMENT || c2 == 61714) {
            int intN = getIntN(i + 3);
            return intN >= -1 ? intN : i + intN;
        } else if (c2 >= BEGIN_ELEMENT_SHORT && c2 <= 45055) {
            char c3 = cArr[i + 2];
            if (c3 == 0) {
                return -1;
            }
            return i - c3;
        } else if (c2 == BEGIN_ELEMENT_LONG) {
            int intN2 = getIntN(i + 1);
            if (intN2 < 0) {
                i = this.data.length;
            }
            int i2 = intN2 + i;
            int intN3 = getIntN(i2 + 5);
            if (intN3 == 0) {
                return -1;
            }
            return intN3 < 0 ? intN3 + i2 : intN3;
        } else {
            while (true) {
                if (i == this.gapStart) {
                    i = this.gapEnd;
                }
                char[] cArr2 = this.data;
                if (i != cArr2.length && (c = cArr2[i]) != END_DOCUMENT) {
                    switch (c) {
                        case END_ATTRIBUTE /*61706*/:
                            i++;
                            break;
                        case END_ELEMENT_SHORT /*61707*/:
                            return i - cArr2[i + 1];
                        case END_ELEMENT_LONG /*61708*/:
                            int intN4 = getIntN(i + 3);
                            return intN4 < 0 ? intN4 + i : intN4;
                        default:
                            i = nextDataIndex(i);
                            if (i >= 0) {
                                break;
                            } else {
                                return -1;
                            }
                    }
                } else {
                    return -1;
                }
            }
        }
    }

    public int getAttributeCount(int i) {
        int firstAttributePos = firstAttributePos(i);
        int i2 = 0;
        while (firstAttributePos != 0 && getNextKind(firstAttributePos) == 35) {
            i2++;
            firstAttributePos = nextPos(firstAttributePos);
        }
        return i2;
    }

    public boolean gotoAttributesStart(TreePosition treePosition) {
        int gotoAttributesStart = gotoAttributesStart(treePosition.ipos >> 1);
        if (gotoAttributesStart < 0) {
            return false;
        }
        treePosition.push(this, gotoAttributesStart << 1);
        return true;
    }

    public int firstAttributePos(int i) {
        int gotoAttributesStart = gotoAttributesStart(posToDataIndex(i));
        if (gotoAttributesStart < 0) {
            return 0;
        }
        return gotoAttributesStart << 1;
    }

    public int gotoAttributesStart(int i) {
        int i2 = this.gapStart;
        if (i >= i2) {
            i += this.gapEnd - i2;
        }
        char[] cArr = this.data;
        if (i == cArr.length) {
            return -1;
        }
        char c = cArr[i];
        if ((c < BEGIN_ELEMENT_SHORT || c > 45055) && c != BEGIN_ELEMENT_LONG) {
            return -1;
        }
        return i + 3;
    }

    public Object get(int i) {
        int i2 = 0;
        do {
            i--;
            if (i < 0) {
                return getPosNext(i2);
            }
            i2 = nextPos(i2);
        } while (i2 != 0);
        throw new IndexOutOfBoundsException();
    }

    public boolean consumeNext(int i, Consumer consumer) {
        if (!hasNext(i)) {
            return false;
        }
        int posToDataIndex = posToDataIndex(i);
        int nextNodeIndex = nextNodeIndex(posToDataIndex, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (nextNodeIndex == posToDataIndex) {
            nextNodeIndex = nextDataIndex(posToDataIndex);
        }
        if (nextNodeIndex < 0) {
            return true;
        }
        consumeIRange(posToDataIndex, nextNodeIndex, consumer);
        return true;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        consumeIRange(posToDataIndex(i), posToDataIndex(i2), consumer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b8, code lost:
        r6 = r6 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01dc, code lost:
        r6 = r2 + 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int consumeIRange(int r6, int r7, gnu.lists.Consumer r8) {
        /*
            r5 = this;
            int r0 = r5.gapStart
            if (r6 > r0) goto L_0x0007
            if (r7 <= r0) goto L_0x0007
            goto L_0x0008
        L_0x0007:
            r0 = r7
        L_0x0008:
            if (r6 < r0) goto L_0x0016
            int r0 = r5.gapStart
            if (r6 != r0) goto L_0x0015
            int r0 = r5.gapEnd
            if (r7 <= r0) goto L_0x0015
            r6 = r0
            r0 = r7
            goto L_0x0016
        L_0x0015:
            return r6
        L_0x0016:
            char[] r1 = r5.data
            int r2 = r6 + 1
            char r6 = r1[r6]
            r3 = 40959(0x9fff, float:5.7396E-41)
            if (r6 > r3) goto L_0x003c
            int r4 = r2 + -1
        L_0x0023:
            if (r2 < r0) goto L_0x0027
            r6 = r2
            goto L_0x0032
        L_0x0027:
            char[] r6 = r5.data
            int r1 = r2 + 1
            char r6 = r6[r2]
            if (r6 <= r3) goto L_0x003a
            int r1 = r1 + -1
            r6 = r1
        L_0x0032:
            char[] r1 = r5.data
            int r2 = r6 - r4
            r8.write((char[]) r1, (int) r4, (int) r2)
            goto L_0x0008
        L_0x003a:
            r2 = r1
            goto L_0x0023
        L_0x003c:
            r3 = 57344(0xe000, float:8.0356E-41)
            if (r6 < r3) goto L_0x0050
            r4 = 61439(0xefff, float:8.6094E-41)
            if (r6 > r4) goto L_0x0050
            java.lang.Object[] r1 = r5.objects
            int r6 = r6 - r3
            r6 = r1[r6]
            r8.writeObject(r6)
            goto L_0x01f3
        L_0x0050:
            r3 = 40960(0xa000, float:5.7397E-41)
            if (r6 < r3) goto L_0x0065
            r4 = 45055(0xafff, float:6.3136E-41)
            if (r6 > r4) goto L_0x0065
            int r6 = r6 - r3
            java.lang.Object[] r1 = r5.objects
            r6 = r1[r6]
            r8.startElement(r6)
        L_0x0062:
            int r6 = r2 + 2
            goto L_0x0008
        L_0x0065:
            r3 = 45056(0xb000, float:6.3137E-41)
            if (r6 < r3) goto L_0x0078
            r3 = 57343(0xdfff, float:8.0355E-41)
            if (r6 > r3) goto L_0x0078
            r1 = 49152(0xc000, float:6.8877E-41)
            int r6 = r6 - r1
            r8.writeInt(r6)
            goto L_0x01f3
        L_0x0078:
            r3 = 1
            switch(r6) {
                case 61696: goto L_0x01e9;
                case 61697: goto L_0x01e9;
                case 61698: goto L_0x01e0;
                case 61699: goto L_0x01d5;
                case 61700: goto L_0x01c8;
                case 61701: goto L_0x01bc;
                case 61702: goto L_0x01af;
                case 61703: goto L_0x007c;
                case 61704: goto L_0x0191;
                case 61705: goto L_0x0185;
                case 61706: goto L_0x0180;
                case 61707: goto L_0x0179;
                case 61708: goto L_0x016f;
                case 61709: goto L_0x0162;
                case 61710: goto L_0x014c;
                case 61711: goto L_0x0127;
                case 61712: goto L_0x0122;
                case 61713: goto L_0x011d;
                case 61714: goto L_0x0109;
                case 61715: goto L_0x00fd;
                case 61716: goto L_0x00db;
                case 61717: goto L_0x00c2;
                case 61718: goto L_0x00bb;
                case 61719: goto L_0x00a6;
                case 61720: goto L_0x0093;
                default: goto L_0x007c;
            }
        L_0x007c:
            java.lang.Error r7 = new java.lang.Error
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "unknown code:"
            r8.append(r0)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L_0x0093:
            boolean r6 = r8 instanceof gnu.lists.TreeList
            if (r6 == 0) goto L_0x0062
            r6 = r8
            gnu.lists.TreeList r6 = (gnu.lists.TreeList) r6
            java.lang.Object[] r1 = r5.objects
            int r3 = r5.getIntN(r2)
            r1 = r1[r3]
            r6.writeDocumentUri(r1)
            goto L_0x0062
        L_0x00a6:
            int r6 = r5.getIntN(r2)
            int r2 = r2 + 2
            boolean r1 = r8 instanceof gnu.lists.XConsumer
            if (r1 == 0) goto L_0x00b8
            r1 = r8
            gnu.lists.XConsumer r1 = (gnu.lists.XConsumer) r1
            char[] r3 = r5.data
            r1.writeComment(r3, r2, r6)
        L_0x00b8:
            int r6 = r6 + r2
            goto L_0x0008
        L_0x00bb:
            java.lang.String r6 = ""
            r8.write((java.lang.String) r6)
            goto L_0x01f3
        L_0x00c2:
            int r6 = r5.getIntN(r2)
            int r2 = r2 + 2
            boolean r1 = r8 instanceof gnu.lists.XConsumer
            if (r1 == 0) goto L_0x00d5
            r1 = r8
            gnu.lists.XConsumer r1 = (gnu.lists.XConsumer) r1
            char[] r3 = r5.data
            r1.writeCDATA(r3, r2, r6)
            goto L_0x00b8
        L_0x00d5:
            char[] r1 = r5.data
            r8.write((char[]) r1, (int) r2, (int) r6)
            goto L_0x00b8
        L_0x00db:
            java.lang.Object[] r6 = r5.objects
            int r1 = r5.getIntN(r2)
            r6 = r6[r1]
            java.lang.String r6 = (java.lang.String) r6
            int r1 = r2 + 2
            int r1 = r5.getIntN(r1)
            int r2 = r2 + 4
            boolean r3 = r8 instanceof gnu.lists.XConsumer
            if (r3 == 0) goto L_0x00f9
            r3 = r8
            gnu.lists.XConsumer r3 = (gnu.lists.XConsumer) r3
            char[] r4 = r5.data
            r3.writeProcessingInstruction(r6, r4, r2, r1)
        L_0x00f9:
            int r6 = r2 + r1
            goto L_0x0008
        L_0x00fd:
            boolean r6 = r8 instanceof gnu.lists.TreeList
            if (r6 == 0) goto L_0x01f3
            r6 = r8
            gnu.lists.TreeList r6 = (gnu.lists.TreeList) r6
            r6.endEntity()
            goto L_0x01f3
        L_0x0109:
            boolean r6 = r8 instanceof gnu.lists.TreeList
            if (r6 == 0) goto L_0x01dc
            r6 = r8
            gnu.lists.TreeList r6 = (gnu.lists.TreeList) r6
            java.lang.Object[] r1 = r5.objects
            int r3 = r5.getIntN(r2)
            r1 = r1[r3]
            r6.beginEntity(r1)
            goto L_0x01dc
        L_0x011d:
            r8.endDocument()
            goto L_0x01f3
        L_0x0122:
            r8.startDocument()
            goto L_0x01dc
        L_0x0127:
            java.lang.Object[] r6 = r5.objects
            int r1 = r5.getIntN(r2)
            r6 = r6[r1]
            gnu.lists.AbstractSequence r6 = (gnu.lists.AbstractSequence) r6
            int r1 = r2 + 2
            int r1 = r5.getIntN(r1)
            boolean r3 = r8 instanceof gnu.lists.PositionConsumer
            if (r3 == 0) goto L_0x0143
            r3 = r8
            gnu.lists.PositionConsumer r3 = (gnu.lists.PositionConsumer) r3
            r3.writePosition(r6, r1)
            goto L_0x01dc
        L_0x0143:
            gnu.lists.SeqPosition r6 = r6.getIteratorAtPos(r1)
            r8.writeObject(r6)
            goto L_0x01dc
        L_0x014c:
            boolean r6 = r8 instanceof gnu.lists.PositionConsumer
            if (r6 == 0) goto L_0x0162
            r6 = r8
            gnu.lists.PositionConsumer r6 = (gnu.lists.PositionConsumer) r6
            java.lang.Object[] r1 = r5.objects
            int r3 = r5.getIntN(r2)
            r1 = r1[r3]
            gnu.lists.SeqPosition r1 = (gnu.lists.SeqPosition) r1
            r6.consume(r1)
            goto L_0x0062
        L_0x0162:
            java.lang.Object[] r6 = r5.objects
            int r1 = r5.getIntN(r2)
            r6 = r6[r1]
            r8.writeObject(r6)
            goto L_0x0062
        L_0x016f:
            r5.getIntN(r2)
            r8.endElement()
            int r6 = r2 + 6
            goto L_0x0008
        L_0x0179:
            int r6 = r2 + 1
            r8.endElement()
            goto L_0x0008
        L_0x0180:
            r8.endAttribute()
            goto L_0x01f3
        L_0x0185:
            int r6 = r5.getIntN(r2)
            java.lang.Object[] r1 = r5.objects
            r6 = r1[r6]
            r8.startAttribute(r6)
            goto L_0x01dc
        L_0x0191:
            int r6 = r5.getIntN(r2)
            if (r6 < 0) goto L_0x019a
            int r1 = r2 + -1
            goto L_0x019d
        L_0x019a:
            char[] r1 = r5.data
            int r1 = r1.length
        L_0x019d:
            int r6 = r6 + r1
            int r1 = r2 + 2
            int r6 = r6 + r3
            int r6 = r5.getIntN(r6)
            java.lang.Object[] r2 = r5.objects
            r6 = r2[r6]
            r8.startElement(r6)
            r6 = r1
            goto L_0x0008
        L_0x01af:
            int r6 = r6 + 1
            r3 = 61702(0xf106, float:8.6463E-41)
            int r6 = r6 - r3
            r8.write((char[]) r1, (int) r2, (int) r6)
            int r6 = r2 + 1
            goto L_0x0008
        L_0x01bc:
            long r3 = r5.getLongN(r2)
            double r3 = java.lang.Double.longBitsToDouble(r3)
            r8.writeDouble(r3)
            goto L_0x01dc
        L_0x01c8:
            int r6 = r5.getIntN(r2)
            float r6 = java.lang.Float.intBitsToFloat(r6)
            r8.writeFloat(r6)
            goto L_0x0062
        L_0x01d5:
            long r3 = r5.getLongN(r2)
            r8.writeLong(r3)
        L_0x01dc:
            int r6 = r2 + 4
            goto L_0x0008
        L_0x01e0:
            int r6 = r5.getIntN(r2)
            r8.writeInt(r6)
            goto L_0x0062
        L_0x01e9:
            r1 = 61696(0xf100, float:8.6455E-41)
            if (r6 == r1) goto L_0x01ef
            goto L_0x01f0
        L_0x01ef:
            r3 = 0
        L_0x01f0:
            r8.writeBoolean(r3)
        L_0x01f3:
            r6 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.consumeIRange(int, int, gnu.lists.Consumer):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c9, code lost:
        r3 = r7 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0104, code lost:
        r3 = r3 + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x013b, code lost:
        r3 = r7 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01b6, code lost:
        r4 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toString(java.lang.String r13, java.lang.StringBuffer r14) {
        /*
            r12 = this;
            int r0 = r12.gapStart
            r1 = 1
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            r5 = 0
        L_0x0007:
            if (r3 < r0) goto L_0x0015
            int r0 = r12.gapStart
            if (r3 != r0) goto L_0x0014
            int r3 = r12.gapEnd
            char[] r0 = r12.data
            int r0 = r0.length
            if (r3 != r0) goto L_0x0015
        L_0x0014:
            return
        L_0x0015:
            char[] r6 = r12.data
            int r7 = r3 + 1
            char r3 = r6[r3]
            r8 = 40959(0x9fff, float:5.7396E-41)
            r9 = 62
            if (r3 > r8) goto L_0x0043
            int r10 = r7 + -1
        L_0x0024:
            if (r7 < r0) goto L_0x0028
            r3 = r7
            goto L_0x0033
        L_0x0028:
            char[] r3 = r12.data
            int r5 = r7 + 1
            char r3 = r3[r7]
            if (r3 <= r8) goto L_0x0041
            int r5 = r5 + -1
            r3 = r5
        L_0x0033:
            if (r4 == 0) goto L_0x0039
            r14.append(r9)
            r4 = 0
        L_0x0039:
            char[] r5 = r12.data
            int r6 = r3 - r10
            r14.append(r5, r10, r6)
            goto L_0x0006
        L_0x0041:
            r7 = r5
            goto L_0x0024
        L_0x0043:
            r8 = 57344(0xe000, float:8.0356E-41)
            if (r3 < r8) goto L_0x0064
            r10 = 61439(0xefff, float:8.6094E-41)
            if (r3 > r10) goto L_0x0064
            if (r4 == 0) goto L_0x0053
            r14.append(r9)
            r4 = 0
        L_0x0053:
            if (r5 == 0) goto L_0x0059
            r14.append(r13)
            goto L_0x005a
        L_0x0059:
            r5 = 1
        L_0x005a:
            java.lang.Object[] r6 = r12.objects
            int r3 = r3 - r8
            r3 = r6[r3]
            r14.append(r3)
            goto L_0x0138
        L_0x0064:
            r8 = 60
            r10 = 40960(0xa000, float:5.7397E-41)
            if (r3 < r10) goto L_0x008d
            r11 = 45055(0xafff, float:6.3136E-41)
            if (r3 > r11) goto L_0x008d
            if (r4 == 0) goto L_0x0075
            r14.append(r9)
        L_0x0075:
            int r3 = r3 - r10
            if (r5 == 0) goto L_0x007b
            r14.append(r13)
        L_0x007b:
            r14.append(r8)
            java.lang.Object[] r4 = r12.objects
            r3 = r4[r3]
            java.lang.String r3 = r3.toString()
            r14.append(r3)
            int r3 = r7 + 2
            goto L_0x01b6
        L_0x008d:
            r11 = 45056(0xb000, float:6.3137E-41)
            if (r3 < r11) goto L_0x00ad
            r11 = 57343(0xdfff, float:8.0355E-41)
            if (r3 > r11) goto L_0x00ad
            if (r4 == 0) goto L_0x009d
            r14.append(r9)
            r4 = 0
        L_0x009d:
            if (r5 == 0) goto L_0x00a3
            r14.append(r13)
            goto L_0x00a4
        L_0x00a3:
            r5 = 1
        L_0x00a4:
            r6 = 49152(0xc000, float:6.8877E-41)
            int r3 = r3 - r6
            r14.append(r3)
            goto L_0x0138
        L_0x00ad:
            r11 = 32
            switch(r3) {
                case 61696: goto L_0x0271;
                case 61697: goto L_0x0271;
                case 61698: goto L_0x025b;
                case 61699: goto L_0x0245;
                case 61700: goto L_0x022b;
                case 61701: goto L_0x0211;
                case 61702: goto L_0x01fc;
                case 61703: goto L_0x00b2;
                case 61704: goto L_0x01d1;
                case 61705: goto L_0x01b9;
                case 61706: goto L_0x01b0;
                case 61707: goto L_0x017e;
                case 61708: goto L_0x017e;
                case 61709: goto L_0x0164;
                case 61710: goto L_0x0164;
                case 61711: goto L_0x013f;
                case 61712: goto L_0x013b;
                case 61713: goto L_0x0138;
                case 61714: goto L_0x013b;
                case 61715: goto L_0x0138;
                case 61716: goto L_0x0107;
                case 61717: goto L_0x00e9;
                case 61718: goto L_0x0138;
                case 61719: goto L_0x00cd;
                case 61720: goto L_0x00c9;
                default: goto L_0x00b2;
            }
        L_0x00b2:
            java.lang.Error r13 = new java.lang.Error
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "unknown code:"
            r14.append(r0)
            r14.append(r3)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r13
        L_0x00c9:
            int r3 = r7 + 2
            goto L_0x0007
        L_0x00cd:
            if (r4 == 0) goto L_0x00d3
            r14.append(r9)
            r4 = 0
        L_0x00d3:
            int r3 = r12.getIntN(r7)
            int r7 = r7 + 2
            java.lang.String r6 = "<!--"
            r14.append(r6)
            char[] r6 = r12.data
            r14.append(r6, r7, r3)
            java.lang.String r6 = "-->"
            r14.append(r6)
            goto L_0x0104
        L_0x00e9:
            if (r4 == 0) goto L_0x00ef
            r14.append(r9)
            r4 = 0
        L_0x00ef:
            int r3 = r12.getIntN(r7)
            int r7 = r7 + 2
            java.lang.String r6 = "<![CDATA["
            r14.append(r6)
            char[] r6 = r12.data
            r14.append(r6, r7, r3)
            java.lang.String r6 = "]]>"
            r14.append(r6)
        L_0x0104:
            int r3 = r3 + r7
            goto L_0x0007
        L_0x0107:
            if (r4 == 0) goto L_0x010d
            r14.append(r9)
            r4 = 0
        L_0x010d:
            java.lang.String r3 = "<?"
            r14.append(r3)
            int r3 = r12.getIntN(r7)
            int r7 = r7 + 2
            java.lang.Object[] r6 = r12.objects
            r3 = r6[r3]
            r14.append(r3)
            int r3 = r12.getIntN(r7)
            int r7 = r7 + 2
            if (r3 <= 0) goto L_0x0130
            r14.append(r11)
            char[] r6 = r12.data
            r14.append(r6, r7, r3)
            int r7 = r7 + r3
        L_0x0130:
            r3 = r7
            java.lang.String r6 = "?>"
            r14.append(r6)
            goto L_0x0007
        L_0x0138:
            r3 = r7
            goto L_0x0007
        L_0x013b:
            int r3 = r7 + 4
            goto L_0x0007
        L_0x013f:
            if (r4 == 0) goto L_0x0145
            r14.append(r9)
            r4 = 0
        L_0x0145:
            if (r5 == 0) goto L_0x014b
            r14.append(r13)
            goto L_0x014c
        L_0x014b:
            r5 = 1
        L_0x014c:
            java.lang.Object[] r3 = r12.objects
            int r6 = r12.getIntN(r7)
            r3 = r3[r6]
            gnu.lists.AbstractSequence r3 = (gnu.lists.AbstractSequence) r3
            int r6 = r7 + 2
            int r6 = r12.getIntN(r6)
            gnu.lists.SeqPosition r3 = r3.getIteratorAtPos(r6)
            r14.append(r3)
            goto L_0x013b
        L_0x0164:
            if (r4 == 0) goto L_0x016a
            r14.append(r9)
            r4 = 0
        L_0x016a:
            if (r5 == 0) goto L_0x0170
            r14.append(r13)
            goto L_0x0171
        L_0x0170:
            r5 = 1
        L_0x0171:
            java.lang.Object[] r3 = r12.objects
            int r6 = r12.getIntN(r7)
            r3 = r3[r6]
            r14.append(r3)
            goto L_0x00c9
        L_0x017e:
            r5 = 61707(0xf10b, float:8.647E-41)
            if (r3 != r5) goto L_0x018e
            int r3 = r7 + 1
            char r5 = r6[r7]
            int r7 = r3 + -2
            int r7 = r7 - r5
            char r5 = r6[r7]
            int r5 = r5 - r10
            goto L_0x0195
        L_0x018e:
            int r5 = r12.getIntN(r7)
            int r7 = r7 + 6
            r3 = r7
        L_0x0195:
            if (r4 == 0) goto L_0x019d
            java.lang.String r4 = "/>"
            r14.append(r4)
            goto L_0x01ac
        L_0x019d:
            java.lang.String r4 = "</"
            r14.append(r4)
            java.lang.Object[] r4 = r12.objects
            r4 = r4[r5]
            r14.append(r4)
            r14.append(r9)
        L_0x01ac:
            r4 = 0
            r5 = 1
            goto L_0x0007
        L_0x01b0:
            r3 = 34
            r14.append(r3)
            r3 = r7
        L_0x01b6:
            r4 = 1
            goto L_0x0006
        L_0x01b9:
            int r3 = r12.getIntN(r7)
            r14.append(r11)
            java.lang.Object[] r4 = r12.objects
            r3 = r4[r3]
            r14.append(r3)
            java.lang.String r3 = "=\""
            r14.append(r3)
            int r3 = r7 + 4
            r4 = 0
            goto L_0x0007
        L_0x01d1:
            int r3 = r12.getIntN(r7)
            if (r3 < 0) goto L_0x01da
            int r6 = r7 + -1
            goto L_0x01dd
        L_0x01da:
            char[] r6 = r12.data
            int r6 = r6.length
        L_0x01dd:
            int r3 = r3 + r6
            int r6 = r7 + 2
            int r3 = r3 + r1
            int r3 = r12.getIntN(r3)
            if (r4 == 0) goto L_0x01eb
            r14.append(r9)
            goto L_0x01f0
        L_0x01eb:
            if (r5 == 0) goto L_0x01f0
            r14.append(r13)
        L_0x01f0:
            r14.append(r8)
            java.lang.Object[] r4 = r12.objects
            r3 = r4[r3]
            r14.append(r3)
            r3 = r6
            goto L_0x01b6
        L_0x01fc:
            if (r4 == 0) goto L_0x0202
            r14.append(r9)
            r4 = 0
        L_0x0202:
            char[] r5 = r12.data
            int r3 = r3 + 1
            r6 = 61702(0xf106, float:8.6463E-41)
            int r3 = r3 - r6
            r14.append(r5, r7, r3)
            int r3 = r7 + 1
            goto L_0x0006
        L_0x0211:
            if (r4 == 0) goto L_0x0217
            r14.append(r9)
            r4 = 0
        L_0x0217:
            if (r5 == 0) goto L_0x021d
            r14.append(r13)
            goto L_0x021e
        L_0x021d:
            r5 = 1
        L_0x021e:
            long r8 = r12.getLongN(r7)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r14.append(r8)
            goto L_0x013b
        L_0x022b:
            if (r4 == 0) goto L_0x0231
            r14.append(r9)
            r4 = 0
        L_0x0231:
            if (r5 == 0) goto L_0x0237
            r14.append(r13)
            goto L_0x0238
        L_0x0237:
            r5 = 1
        L_0x0238:
            int r3 = r12.getIntN(r7)
            float r3 = java.lang.Float.intBitsToFloat(r3)
            r14.append(r3)
            goto L_0x00c9
        L_0x0245:
            if (r4 == 0) goto L_0x024b
            r14.append(r9)
            r4 = 0
        L_0x024b:
            if (r5 == 0) goto L_0x0251
            r14.append(r13)
            goto L_0x0252
        L_0x0251:
            r5 = 1
        L_0x0252:
            long r8 = r12.getLongN(r7)
            r14.append(r8)
            goto L_0x013b
        L_0x025b:
            if (r4 == 0) goto L_0x0261
            r14.append(r9)
            r4 = 0
        L_0x0261:
            if (r5 == 0) goto L_0x0267
            r14.append(r13)
            goto L_0x0268
        L_0x0267:
            r5 = 1
        L_0x0268:
            int r3 = r12.getIntN(r7)
            r14.append(r3)
            goto L_0x00c9
        L_0x0271:
            if (r4 == 0) goto L_0x0277
            r14.append(r9)
            r4 = 0
        L_0x0277:
            if (r5 == 0) goto L_0x027d
            r14.append(r13)
            goto L_0x027e
        L_0x027d:
            r5 = 1
        L_0x027e:
            r6 = 61696(0xf100, float:8.6455E-41)
            if (r3 == r6) goto L_0x0285
            r3 = 1
            goto L_0x0286
        L_0x0285:
            r3 = 0
        L_0x0286:
            r14.append(r3)
            goto L_0x0138
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.toString(java.lang.String, java.lang.StringBuffer):void");
    }

    public boolean hasNext(int i) {
        char c;
        int posToDataIndex = posToDataIndex(i);
        char[] cArr = this.data;
        if (posToDataIndex == cArr.length || (c = cArr[posToDataIndex]) == END_ATTRIBUTE || c == END_ELEMENT_SHORT || c == END_ELEMENT_LONG || c == END_DOCUMENT) {
            return false;
        }
        return true;
    }

    public int getNextKind(int i) {
        return getNextKindI(posToDataIndex(i));
    }

    public int getNextKindI(int i) {
        char[] cArr = this.data;
        if (i == cArr.length) {
            return 0;
        }
        char c = cArr[i];
        if (c <= 40959) {
            return 29;
        }
        if (c >= OBJECT_REF_SHORT && c <= 61439) {
            return 32;
        }
        if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
            return 33;
        }
        if ((65280 & c) == BYTE_PREFIX) {
            return 28;
        }
        if (c >= 45056 && c <= 57343) {
            return 22;
        }
        switch (c) {
            case 61696:
            case 61697:
                return 27;
            case INT_FOLLOWS /*61698*/:
                return 22;
            case LONG_FOLLOWS /*61699*/:
                return 24;
            case FLOAT_FOLLOWS /*61700*/:
                return 25;
            case DOUBLE_FOLLOWS /*61701*/:
                return 26;
            case CHAR_FOLLOWS /*61702*/:
            case BEGIN_DOCUMENT /*61712*/:
                return 34;
            case BEGIN_ELEMENT_LONG /*61704*/:
                return 33;
            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                return 35;
            case END_ATTRIBUTE /*61706*/:
            case END_ELEMENT_SHORT /*61707*/:
            case END_ELEMENT_LONG /*61708*/:
            case END_DOCUMENT /*61713*/:
            case END_ENTITY /*61715*/:
                return 0;
            case BEGIN_ENTITY /*61714*/:
                return getNextKind((i + 5) << 1);
            case PROCESSING_INSTRUCTION /*61716*/:
                return 37;
            case CDATA_SECTION /*61717*/:
                return 31;
            case COMMENT /*61719*/:
                return 36;
            default:
                return 32;
        }
    }

    public Object getNextTypeObject(int i) {
        int i2;
        int posToDataIndex = posToDataIndex(i);
        while (true) {
            char[] cArr = this.data;
            if (posToDataIndex == cArr.length) {
                return null;
            }
            char c = cArr[posToDataIndex];
            if (c != 61714) {
                if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
                    i2 = c - BEGIN_ELEMENT_SHORT;
                } else if (c == BEGIN_ELEMENT_LONG) {
                    int intN = getIntN(posToDataIndex + 1);
                    if (intN < 0) {
                        posToDataIndex = this.data.length;
                    }
                    i2 = getIntN(intN + posToDataIndex + 1);
                } else if (c == BEGIN_ATTRIBUTE_LONG) {
                    i2 = getIntN(posToDataIndex + 1);
                } else if (c != PROCESSING_INSTRUCTION) {
                    return null;
                } else {
                    i2 = getIntN(posToDataIndex + 1);
                }
                if (i2 < 0) {
                    return null;
                }
                return this.objects[i2];
            }
            posToDataIndex += 5;
        }
    }

    public String getNextTypeName(int i) {
        Object nextTypeObject = getNextTypeObject(i);
        if (nextTypeObject == null) {
            return null;
        }
        return nextTypeObject.toString();
    }

    public Object getPosPrevious(int i) {
        if ((i & 1) == 0 || i == -1) {
            return super.getPosPrevious(i);
        }
        return getPosNext(i - 3);
    }

    private Object copyToList(int i, int i2) {
        return new TreeList(this, i, i2);
    }

    public int getPosNextInt(int i) {
        int posToDataIndex = posToDataIndex(i);
        char[] cArr = this.data;
        if (posToDataIndex < cArr.length) {
            char c = cArr[posToDataIndex];
            if (c >= 45056 && c <= 57343) {
                return c - INT_SHORT_ZERO;
            }
            if (c == 61698) {
                return getIntN(posToDataIndex + 1);
            }
        }
        return ((Number) getPosNext(i)).intValue();
    }

    public Object getPosNext(int i) {
        int posToDataIndex = posToDataIndex(i);
        char[] cArr = this.data;
        if (posToDataIndex == cArr.length) {
            return Sequence.eofValue;
        }
        char c = cArr[posToDataIndex];
        if (c <= 40959) {
            return Convert.toObject(c);
        }
        if (c >= OBJECT_REF_SHORT && c <= 61439) {
            return this.objects[c - OBJECT_REF_SHORT];
        }
        if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
            return copyToList(posToDataIndex, cArr[posToDataIndex + 1] + posToDataIndex + 2);
        }
        if (c >= 45056 && c <= 57343) {
            return Convert.toObject(c - INT_SHORT_ZERO);
        }
        boolean z = true;
        switch (c) {
            case 61696:
            case 61697:
                if (c == 61696) {
                    z = false;
                }
                return Convert.toObject(z);
            case INT_FOLLOWS /*61698*/:
                return Convert.toObject(getIntN(posToDataIndex + 1));
            case LONG_FOLLOWS /*61699*/:
                return Convert.toObject(getLongN(posToDataIndex + 1));
            case FLOAT_FOLLOWS /*61700*/:
                return Convert.toObject(Float.intBitsToFloat(getIntN(posToDataIndex + 1)));
            case DOUBLE_FOLLOWS /*61701*/:
                return Convert.toObject(Double.longBitsToDouble(getLongN(posToDataIndex + 1)));
            case CHAR_FOLLOWS /*61702*/:
                return Convert.toObject(cArr[posToDataIndex + 1]);
            case BEGIN_ELEMENT_LONG /*61704*/:
                int intN = getIntN(posToDataIndex + 1);
                return copyToList(posToDataIndex, intN + (intN < 0 ? this.data.length : posToDataIndex) + 7);
            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                int intN2 = getIntN(posToDataIndex + 3);
                return copyToList(posToDataIndex, intN2 + (intN2 < 0 ? this.data.length : posToDataIndex) + 1);
            case END_ATTRIBUTE /*61706*/:
            case END_ELEMENT_SHORT /*61707*/:
            case END_ELEMENT_LONG /*61708*/:
            case END_DOCUMENT /*61713*/:
                return Sequence.eofValue;
            case 61709:
            case 61710:
                return this.objects[getIntN(posToDataIndex + 1)];
            case 61711:
                return ((AbstractSequence) this.objects[getIntN(posToDataIndex + 1)]).getIteratorAtPos(getIntN(posToDataIndex + 3));
            case BEGIN_DOCUMENT /*61712*/:
                int intN3 = getIntN(posToDataIndex + 1);
                return copyToList(posToDataIndex, intN3 + (intN3 < 0 ? this.data.length : posToDataIndex) + 1);
            case JOINER /*61718*/:
                return "";
            default:
                throw unsupported("getPosNext, code=" + Integer.toHexString(c));
        }
    }

    public void stringValue(int i, int i2, StringBuffer stringBuffer) {
        while (i < i2 && i >= 0) {
            i = stringValue(false, i, stringBuffer);
        }
    }

    public int stringValue(int i, StringBuffer stringBuffer) {
        int nextNodeIndex = nextNodeIndex(i, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (nextNodeIndex <= i) {
            return stringValue(false, i, stringBuffer);
        }
        stringValue(i, nextNodeIndex, stringBuffer);
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008e, code lost:
        r0 = getIntN(r10);
        r10 = r10 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0094, code lost:
        if (r9 == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        if (r1 != CDATA_SECTION) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        r11.append(r8.data, r10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a1, code lost:
        return r10 + r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int stringValue(boolean r9, int r10, java.lang.StringBuffer r11) {
        /*
            r8 = this;
            int r0 = r8.gapStart
            if (r10 < r0) goto L_0x0008
            int r1 = r8.gapEnd
            int r1 = r1 - r0
            int r10 = r10 + r1
        L_0x0008:
            char[] r0 = r8.data
            int r1 = r0.length
            r2 = -1
            if (r10 != r1) goto L_0x000f
            return r2
        L_0x000f:
            char r1 = r0[r10]
            r3 = 1
            int r10 = r10 + r3
            r4 = 40959(0x9fff, float:5.7396E-41)
            if (r1 > r4) goto L_0x001c
            r11.append(r1)
            return r10
        L_0x001c:
            r4 = 57344(0xe000, float:8.0356E-41)
            r5 = 0
            r6 = 0
            if (r1 < r4) goto L_0x002f
            r7 = 61439(0xefff, float:8.6094E-41)
            if (r1 > r7) goto L_0x002f
            java.lang.Object[] r9 = r8.objects
            int r1 = r1 - r4
            r6 = r9[r1]
            goto L_0x00e8
        L_0x002f:
            r4 = 40960(0xa000, float:5.7397E-41)
            if (r1 < r4) goto L_0x0042
            r4 = 45055(0xafff, float:6.3136E-41)
            if (r1 > r4) goto L_0x0042
            int r5 = r10 + 2
            char r9 = r0[r10]
            int r9 = r9 + r10
            int r10 = r9 + 1
            goto L_0x00e8
        L_0x0042:
            r4 = 65280(0xff00, float:9.1477E-41)
            r4 = r4 & r1
            r7 = 61440(0xf000, float:8.6096E-41)
            if (r4 != r7) goto L_0x0051
            r9 = r1 & 255(0xff, float:3.57E-43)
            r11.append(r9)
            return r10
        L_0x0051:
            r4 = 45056(0xb000, float:6.3137E-41)
            if (r1 < r4) goto L_0x0063
            r4 = 57343(0xdfff, float:8.0355E-41)
            if (r1 > r4) goto L_0x0063
            r9 = 49152(0xc000, float:6.8877E-41)
            int r1 = r1 - r9
            r11.append(r1)
            return r10
        L_0x0063:
            switch(r1) {
                case 61696: goto L_0x012d;
                case 61697: goto L_0x012d;
                case 61698: goto L_0x0123;
                case 61699: goto L_0x0119;
                case 61700: goto L_0x010b;
                case 61701: goto L_0x00fd;
                case 61702: goto L_0x00f6;
                case 61703: goto L_0x0066;
                case 61704: goto L_0x00d7;
                case 61705: goto L_0x00c5;
                case 61706: goto L_0x00c4;
                case 61707: goto L_0x00c4;
                case 61708: goto L_0x00c4;
                case 61709: goto L_0x0066;
                case 61710: goto L_0x0066;
                case 61711: goto L_0x00ab;
                case 61712: goto L_0x00a2;
                case 61713: goto L_0x00c4;
                case 61714: goto L_0x00a2;
                case 61715: goto L_0x00c4;
                case 61716: goto L_0x008c;
                case 61717: goto L_0x008e;
                case 61718: goto L_0x00e8;
                case 61719: goto L_0x008e;
                case 61720: goto L_0x0089;
                default: goto L_0x0066;
            }
        L_0x0066:
            java.lang.Error r9 = new java.lang.Error
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "unimplemented: "
            r11.append(r0)
            java.lang.String r0 = java.lang.Integer.toHexString(r1)
            r11.append(r0)
            java.lang.String r0 = " at:"
            r11.append(r0)
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            r9.<init>(r10)
            throw r9
        L_0x0089:
            int r10 = r10 + 2
            return r10
        L_0x008c:
            int r10 = r10 + 2
        L_0x008e:
            int r0 = r8.getIntN(r10)
            int r10 = r10 + 2
            if (r9 == 0) goto L_0x009b
            r9 = 61717(0xf115, float:8.6484E-41)
            if (r1 != r9) goto L_0x00a0
        L_0x009b:
            char[] r9 = r8.data
            r11.append(r9, r10, r0)
        L_0x00a0:
            int r10 = r10 + r0
            return r10
        L_0x00a2:
            int r5 = r10 + 4
            int r10 = r10 + -1
            int r10 = r8.nextDataIndex(r10)
            goto L_0x00e8
        L_0x00ab:
            java.lang.Object[] r0 = r8.objects
            int r1 = r8.getIntN(r10)
            r0 = r0[r1]
            gnu.lists.AbstractSequence r0 = (gnu.lists.AbstractSequence) r0
            int r1 = r10 + 2
            int r1 = r8.getIntN(r1)
            gnu.lists.TreeList r0 = (gnu.lists.TreeList) r0
            int r1 = r1 >> r3
            r0.stringValue((boolean) r9, (int) r1, (java.lang.StringBuffer) r11)
            int r10 = r10 + 4
            goto L_0x00e8
        L_0x00c4:
            return r2
        L_0x00c5:
            if (r9 != 0) goto L_0x00c9
            int r5 = r10 + 4
        L_0x00c9:
            int r9 = r10 + 2
            int r9 = r8.getIntN(r9)
            if (r9 >= 0) goto L_0x00d5
            char[] r10 = r8.data
            int r10 = r10.length
            int r10 = r10 + r3
        L_0x00d5:
            int r10 = r10 + r9
            goto L_0x00e8
        L_0x00d7:
            int r5 = r10 + 2
            int r9 = r8.getIntN(r10)
            if (r9 >= 0) goto L_0x00e3
            char[] r10 = r8.data
            int r10 = r10.length
            goto L_0x00e5
        L_0x00e3:
            int r10 = r10 + -1
        L_0x00e5:
            int r9 = r9 + r10
            int r10 = r9 + 7
        L_0x00e8:
            if (r6 == 0) goto L_0x00ed
            r11.append(r6)
        L_0x00ed:
            if (r5 <= 0) goto L_0x00f5
        L_0x00ef:
            int r5 = r8.stringValue((boolean) r3, (int) r5, (java.lang.StringBuffer) r11)
            if (r5 >= 0) goto L_0x00ef
        L_0x00f5:
            return r10
        L_0x00f6:
            char r9 = r0[r10]
            r11.append(r9)
            int r10 = r10 + r3
            return r10
        L_0x00fd:
            long r0 = r8.getLongN(r10)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            r11.append(r0)
            int r10 = r10 + 4
            return r10
        L_0x010b:
            int r9 = r8.getIntN(r10)
            float r9 = java.lang.Float.intBitsToFloat(r9)
            r11.append(r9)
            int r10 = r10 + 2
            return r10
        L_0x0119:
            long r0 = r8.getLongN(r10)
            r11.append(r0)
            int r10 = r10 + 4
            return r10
        L_0x0123:
            int r9 = r8.getIntN(r10)
            r11.append(r9)
            int r10 = r10 + 2
            return r10
        L_0x012d:
            r9 = 61696(0xf100, float:8.6455E-41)
            if (r1 == r9) goto L_0x0133
            goto L_0x0134
        L_0x0133:
            r3 = 0
        L_0x0134:
            r11.append(r3)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.stringValue(boolean, int, java.lang.StringBuffer):int");
    }

    public int createRelativePos(int i, int i2, boolean z) {
        if (z) {
            if (i2 == 0) {
                if ((i & 1) != 0) {
                    return i;
                }
                if (i == 0) {
                    return 1;
                }
            }
            i2--;
        }
        if (i2 >= 0) {
            int posToDataIndex = posToDataIndex(i);
            do {
                i2--;
                if (i2 >= 0) {
                    posToDataIndex = nextDataIndex(posToDataIndex);
                } else {
                    int i3 = this.gapEnd;
                    if (posToDataIndex >= i3) {
                        posToDataIndex -= i3 - this.gapStart;
                    }
                    return z ? ((posToDataIndex + 1) << 1) | 1 : posToDataIndex << 1;
                }
            } while (posToDataIndex >= 0);
            throw new IndexOutOfBoundsException();
        }
        throw unsupported("backwards createRelativePos");
    }

    public final int nextNodeIndex(int i, int i2) {
        if ((Integer.MIN_VALUE | i2) == -1) {
            i2 = this.data.length;
        }
        while (true) {
            if (i == this.gapStart) {
                i = this.gapEnd;
            }
            if (i >= i2) {
                return i;
            }
            char c = this.data[i];
            if (c > 40959 && ((c < OBJECT_REF_SHORT || c > 61439) && ((c < 45056 || c > 57343) && (65280 & c) != BYTE_PREFIX))) {
                if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
                    return i;
                }
                switch (c) {
                    case BEGIN_ELEMENT_LONG /*61704*/:
                    case BEGIN_ATTRIBUTE_LONG /*61705*/:
                    case END_ATTRIBUTE /*61706*/:
                    case END_ELEMENT_SHORT /*61707*/:
                    case END_ELEMENT_LONG /*61708*/:
                    case BEGIN_DOCUMENT /*61712*/:
                    case END_DOCUMENT /*61713*/:
                    case END_ENTITY /*61715*/:
                    case PROCESSING_INSTRUCTION /*61716*/:
                    case COMMENT /*61719*/:
                        return i;
                    case BEGIN_ENTITY /*61714*/:
                        i += 5;
                        continue;
                    case JOINER /*61718*/:
                        break;
                    case DOCUMENT_URI /*61720*/:
                        i += 3;
                        continue;
                    default:
                        i = nextDataIndex(i);
                        continue;
                }
            }
            i++;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006d, code lost:
        r5 = r5 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0079, code lost:
        if (r1 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008f, code lost:
        if (r1 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00c4, code lost:
        if (r4 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c7, code lost:
        r0 = r0 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00cd, code lost:
        if (r1 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00d2, code lost:
        if (r1 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00d7, code lost:
        if (r1 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00da, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e4, code lost:
        if (r4 != false) goto L_0x00e6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x002f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextMatching(int r9, gnu.lists.ItemPredicate r10, int r11, boolean r12) {
        /*
            r8 = this;
            int r9 = r8.posToDataIndex(r9)
            int r11 = r8.posToDataIndex(r11)
            boolean r0 = r10 instanceof gnu.lists.NodePredicate
            if (r0 == 0) goto L_0x0011
            int r0 = r8.nextNodeIndex(r9, r11)
            goto L_0x0012
        L_0x0011:
            r0 = r9
        L_0x0012:
            boolean r1 = r10 instanceof gnu.lists.ElementPredicate
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x001b
            r1 = 0
        L_0x0019:
            r4 = 1
            goto L_0x0024
        L_0x001b:
            boolean r1 = r10 instanceof gnu.lists.AttributePredicate
            if (r1 == 0) goto L_0x0022
            r1 = 0
            r4 = 0
            goto L_0x0024
        L_0x0022:
            r1 = 1
            goto L_0x0019
        L_0x0024:
            int r5 = r8.gapStart
            if (r0 != r5) goto L_0x002a
            int r0 = r8.gapEnd
        L_0x002a:
            if (r0 < r11) goto L_0x0030
            r5 = -1
            if (r11 == r5) goto L_0x0030
            return r2
        L_0x0030:
            char[] r5 = r8.data
            char r6 = r5[r0]
            r7 = 40959(0x9fff, float:5.7396E-41)
            if (r6 <= r7) goto L_0x0112
            r7 = 57344(0xe000, float:8.0356E-41)
            if (r6 < r7) goto L_0x0043
            r7 = 61439(0xefff, float:8.6094E-41)
            if (r6 <= r7) goto L_0x0112
        L_0x0043:
            r7 = 45056(0xb000, float:6.3137E-41)
            if (r6 < r7) goto L_0x004f
            r7 = 57343(0xdfff, float:8.0355E-41)
            if (r6 > r7) goto L_0x004f
            goto L_0x0112
        L_0x004f:
            switch(r6) {
                case 61696: goto L_0x00d5;
                case 61697: goto L_0x00d5;
                case 61698: goto L_0x00d0;
                case 61699: goto L_0x00cb;
                case 61700: goto L_0x00d0;
                case 61701: goto L_0x00cb;
                case 61702: goto L_0x00c7;
                case 61703: goto L_0x0052;
                case 61704: goto L_0x00af;
                case 61705: goto L_0x009e;
                case 61706: goto L_0x009b;
                case 61707: goto L_0x0098;
                case 61708: goto L_0x0092;
                case 61709: goto L_0x00d0;
                case 61710: goto L_0x00d0;
                case 61711: goto L_0x008d;
                case 61712: goto L_0x0089;
                case 61713: goto L_0x009b;
                case 61714: goto L_0x0086;
                case 61715: goto L_0x0127;
                case 61716: goto L_0x007d;
                case 61717: goto L_0x0070;
                case 61718: goto L_0x0127;
                case 61719: goto L_0x0065;
                case 61720: goto L_0x0062;
                default: goto L_0x0052;
            }
        L_0x0052:
            r7 = 40960(0xa000, float:5.7397E-41)
            if (r6 < r7) goto L_0x00fb
            r7 = 45055(0xafff, float:6.3136E-41)
            if (r6 > r7) goto L_0x00fb
            if (r12 == 0) goto L_0x00dd
            int r5 = r0 + 3
            goto L_0x00e4
        L_0x0062:
            int r0 = r0 + 3
            goto L_0x0024
        L_0x0065:
            int r5 = r0 + 3
            int r6 = r0 + 1
            int r6 = r8.getIntN(r6)
        L_0x006d:
            int r5 = r5 + r6
            goto L_0x00e6
        L_0x0070:
            int r5 = r0 + 3
            int r6 = r0 + 1
            int r6 = r8.getIntN(r6)
            int r5 = r5 + r6
            if (r1 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x007d:
            int r5 = r0 + 5
            int r6 = r0 + 3
            int r6 = r8.getIntN(r6)
            goto L_0x006d
        L_0x0086:
            int r0 = r0 + 5
            goto L_0x0024
        L_0x0089:
            int r5 = r0 + 5
            goto L_0x00e6
        L_0x008d:
            int r5 = r0 + 5
            if (r1 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x0092:
            if (r12 != 0) goto L_0x0095
            return r2
        L_0x0095:
            int r0 = r0 + 7
            goto L_0x0024
        L_0x0098:
            if (r12 != 0) goto L_0x00c7
            return r2
        L_0x009b:
            if (r12 != 0) goto L_0x0127
            return r2
        L_0x009e:
            int r5 = r0 + 3
            int r5 = r8.getIntN(r5)
            int r6 = r5 + 1
            if (r5 >= 0) goto L_0x00ab
            char[] r0 = r8.data
            int r0 = r0.length
        L_0x00ab:
            int r6 = r6 + r0
            r0 = r6
            goto L_0x0024
        L_0x00af:
            if (r12 == 0) goto L_0x00b4
            int r5 = r0 + 3
            goto L_0x00c4
        L_0x00b4:
            int r5 = r0 + 1
            int r5 = r8.getIntN(r5)
            if (r5 >= 0) goto L_0x00c0
            char[] r6 = r8.data
            int r6 = r6.length
            goto L_0x00c1
        L_0x00c0:
            r6 = r0
        L_0x00c1:
            int r5 = r5 + r6
            int r5 = r5 + 7
        L_0x00c4:
            if (r4 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x00c7:
            int r0 = r0 + 2
            goto L_0x0024
        L_0x00cb:
            int r5 = r0 + 5
            if (r1 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x00d0:
            int r5 = r0 + 3
            if (r1 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x00d5:
            int r5 = r0 + 1
            if (r1 == 0) goto L_0x00da
            goto L_0x00e6
        L_0x00da:
            r0 = r5
            goto L_0x0024
        L_0x00dd:
            int r6 = r0 + 1
            char r5 = r5[r6]
            int r5 = r5 + r0
            int r5 = r5 + 2
        L_0x00e4:
            if (r4 == 0) goto L_0x00da
        L_0x00e6:
            if (r0 <= r9) goto L_0x00da
            int r6 = r0 << 1
            boolean r6 = r10.isInstancePos(r8, r6)
            if (r6 == 0) goto L_0x00da
            int r9 = r8.gapEnd
            if (r0 < r9) goto L_0x00f8
            int r10 = r8.gapStart
            int r9 = r9 - r10
            int r0 = r0 - r9
        L_0x00f8:
            int r9 = r0 << 1
            return r9
        L_0x00fb:
            java.lang.Error r9 = new java.lang.Error
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "unknown code:"
            r10.append(r11)
            r10.append(r6)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x0112:
            if (r1 == 0) goto L_0x0127
            int r5 = r0 << 1
            boolean r5 = r10.isInstancePos(r8, r5)
            if (r5 == 0) goto L_0x0127
            int r9 = r8.gapEnd
            if (r0 < r9) goto L_0x0124
            int r10 = r8.gapStart
            int r9 = r9 - r10
            int r0 = r0 - r9
        L_0x0124:
            int r9 = r0 << 1
            return r9
        L_0x0127:
            int r0 = r0 + 1
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.nextMatching(int, gnu.lists.ItemPredicate, int, boolean):int");
    }

    public int nextPos(int i) {
        int posToDataIndex = posToDataIndex(i);
        if (posToDataIndex == this.data.length) {
            return 0;
        }
        int i2 = this.gapEnd;
        if (posToDataIndex >= i2) {
            posToDataIndex -= i2 - this.gapStart;
        }
        return (posToDataIndex << 1) + 3;
    }

    public final int nextDataIndex(int i) {
        if (i == this.gapStart) {
            i = this.gapEnd;
        }
        char[] cArr = this.data;
        if (i == cArr.length) {
            return -1;
        }
        int i2 = i + 1;
        char c = cArr[i];
        if (c <= 40959) {
            return i2;
        }
        if (c >= OBJECT_REF_SHORT && c <= 61439) {
            return i2;
        }
        if (c >= 45056 && c <= 57343) {
            return i2;
        }
        if (c >= BEGIN_ELEMENT_SHORT && c <= 45055) {
            return cArr[i2] + i2 + 1;
        }
        switch (c) {
            case 61696:
            case 61697:
            case JOINER /*61718*/:
                return i2;
            case INT_FOLLOWS /*61698*/:
            case FLOAT_FOLLOWS /*61700*/:
            case 61709:
            case 61710:
                return i2 + 2;
            case LONG_FOLLOWS /*61699*/:
            case DOUBLE_FOLLOWS /*61701*/:
                return i2 + 4;
            case CHAR_FOLLOWS /*61702*/:
                return i2 + 1;
            case BEGIN_ELEMENT_LONG /*61704*/:
                int intN = getIntN(i2);
                return intN + (intN < 0 ? this.data.length : i2 - 1) + 7;
            case BEGIN_ATTRIBUTE_LONG /*61705*/:
                int intN2 = getIntN(i2 + 2);
                return intN2 + (intN2 < 0 ? this.data.length : i2 - 1) + 1;
            case END_ATTRIBUTE /*61706*/:
            case END_ELEMENT_SHORT /*61707*/:
            case END_ELEMENT_LONG /*61708*/:
            case END_DOCUMENT /*61713*/:
            case END_ENTITY /*61715*/:
                return -1;
            case 61711:
                return i2 + 4;
            case BEGIN_DOCUMENT /*61712*/:
                int intN3 = getIntN(i2);
                return intN3 + (intN3 < 0 ? this.data.length : i2 - 1) + 1;
            case BEGIN_ENTITY /*61714*/:
                int i3 = i2 + 4;
                while (true) {
                    if (i3 == this.gapStart) {
                        i3 = this.gapEnd;
                    }
                    char[] cArr2 = this.data;
                    if (i3 == cArr2.length) {
                        return -1;
                    }
                    if (cArr2[i3] == END_ENTITY) {
                        return i3 + 1;
                    }
                    i3 = nextDataIndex(i3);
                }
            case PROCESSING_INSTRUCTION /*61716*/:
                i2 += 2;
                break;
            case CDATA_SECTION /*61717*/:
            case COMMENT /*61719*/:
                break;
            default:
                throw new Error("unknown code:" + Integer.toHexString(c));
        }
        return i2 + 2 + getIntN(i2);
    }

    public Object documentUriOfPos(int i) {
        int posToDataIndex = posToDataIndex(i);
        char[] cArr = this.data;
        if (posToDataIndex != cArr.length && cArr[posToDataIndex] == BEGIN_DOCUMENT) {
            int i2 = posToDataIndex + 5;
            if (i2 == this.gapStart) {
                i2 = this.gapEnd;
            }
            if (i2 < cArr.length && cArr[i2] == DOCUMENT_URI) {
                return this.objects[getIntN(i2 + 1)];
            }
        }
        return null;
    }

    public int compare(int i, int i2) {
        int posToDataIndex = posToDataIndex(i);
        int posToDataIndex2 = posToDataIndex(i2);
        if (posToDataIndex < posToDataIndex2) {
            return -1;
        }
        return posToDataIndex > posToDataIndex2 ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public int getIndexDifference(int i, int i2) {
        boolean z;
        int posToDataIndex = posToDataIndex(i2);
        int posToDataIndex2 = posToDataIndex(i);
        int i3 = 0;
        if (posToDataIndex > posToDataIndex2) {
            z = true;
            int i4 = posToDataIndex;
            posToDataIndex = posToDataIndex2;
            posToDataIndex2 = i4;
        } else {
            z = false;
        }
        while (posToDataIndex < posToDataIndex2) {
            posToDataIndex = nextDataIndex(posToDataIndex);
            i3++;
        }
        return z ? -i3 : i3;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public void consume(Consumer consumer) {
        consumeIRange(0, this.data.length, consumer);
    }

    public void statistics() {
        PrintWriter printWriter = new PrintWriter(System.out);
        statistics(printWriter);
        printWriter.flush();
    }

    public void statistics(PrintWriter printWriter) {
        printWriter.print("data array length: ");
        printWriter.println(this.data.length);
        printWriter.print("data array gap: ");
        printWriter.println(this.gapEnd - this.gapStart);
        printWriter.print("object array length: ");
        printWriter.println(this.objects.length);
    }

    public void dump() {
        PrintWriter printWriter = new PrintWriter(System.out);
        dump(printWriter);
        printWriter.flush();
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println(getClass().getName() + " @" + Integer.toHexString(System.identityHashCode(this)) + " gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + " length:" + this.data.length);
        dump(printWriter, 0, this.data.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x019c, code lost:
        r5 = r5 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0391, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x03ed, code lost:
        r5 = 4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void dump(java.io.PrintWriter r17, int r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r5 = 0
        L_0x0009:
            if (r3 >= r4) goto L_0x0421
            int r6 = r0.gapStart
            if (r3 < r6) goto L_0x0017
            int r6 = r0.gapEnd
            if (r3 < r6) goto L_0x0014
            goto L_0x0017
        L_0x0014:
            r2 = 1
            goto L_0x041e
        L_0x0017:
            char[] r6 = r0.data
            char r6 = r6[r3]
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = ""
            r8.append(r9)
            r8.append(r3)
            java.lang.String r9 = ": 0x"
            r8.append(r9)
            java.lang.String r9 = java.lang.Integer.toHexString(r6)
            r8.append(r9)
            r9 = 61
            r8.append(r9)
            short r10 = (short) r6
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r1.print(r8)
            int r5 = r5 + -1
            r8 = 4
            r10 = 2
            if (r5 >= 0) goto L_0x0416
            r11 = 40959(0x9fff, float:5.7396E-41)
            if (r6 > r11) goto L_0x009b
            r8 = 32
            java.lang.String r9 = "'"
            if (r6 < r8) goto L_0x0073
            r8 = 127(0x7f, float:1.78E-43)
            if (r6 >= r8) goto L_0x0073
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "='"
            r8.append(r10)
            char r6 = (char) r6
            r8.append(r6)
            r8.append(r9)
            java.lang.String r6 = r8.toString()
            r1.print(r6)
            goto L_0x0416
        L_0x0073:
            r8 = 10
            if (r6 != r8) goto L_0x007e
            java.lang.String r6 = "='\\n'"
            r1.print(r6)
            goto L_0x0416
        L_0x007e:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r10 = "='\\u"
            r8.append(r10)
            java.lang.String r6 = java.lang.Integer.toHexString(r6)
            r8.append(r6)
            r8.append(r9)
            java.lang.String r6 = r8.toString()
            r1.print(r6)
            goto L_0x0416
        L_0x009b:
            r11 = 64
            r12 = 57344(0xe000, float:8.0356E-41)
            if (r6 < r12) goto L_0x00e6
            r13 = 61439(0xefff, float:8.6094E-41)
            if (r6 > r13) goto L_0x00e6
            int r6 = r6 - r12
            java.lang.Object[] r8 = r0.objects
            r8 = r8[r6]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "=Object#"
            r10.append(r12)
            r10.append(r6)
            r10.append(r9)
            r10.append(r8)
            r6 = 58
            r10.append(r6)
            java.lang.Class r6 = r8.getClass()
            java.lang.String r6 = r6.getName()
            r10.append(r6)
            r10.append(r11)
            int r6 = java.lang.System.identityHashCode(r8)
            java.lang.String r6 = java.lang.Integer.toHexString(r6)
            r10.append(r6)
            java.lang.String r6 = r10.toString()
            r1.print(r6)
            goto L_0x0416
        L_0x00e6:
            r12 = 40960(0xa000, float:5.7397E-41)
            r13 = 62
            java.lang.String r14 = "=<"
            if (r6 < r12) goto L_0x0128
            r15 = 45055(0xafff, float:6.3136E-41)
            if (r6 > r15) goto L_0x0128
            int r6 = r6 - r12
            char[] r5 = r0.data
            int r8 = r3 + 1
            char r5 = r5[r8]
            int r5 = r5 + r3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "=BEGIN_ELEMENT_SHORT end:"
            r8.append(r9)
            r8.append(r5)
            java.lang.String r5 = " index#"
            r8.append(r5)
            r8.append(r6)
            r8.append(r14)
            java.lang.Object[] r5 = r0.objects
            r5 = r5[r6]
            r8.append(r5)
            r8.append(r13)
            java.lang.String r5 = r8.toString()
            r1.print(r5)
        L_0x0125:
            r5 = 2
            goto L_0x0416
        L_0x0128:
            r15 = 45056(0xb000, float:6.3137E-41)
            if (r6 < r15) goto L_0x014c
            r15 = 57343(0xdfff, float:8.0355E-41)
            if (r6 > r15) goto L_0x014c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "= INT_SHORT:"
            r8.append(r9)
            r9 = 49152(0xc000, float:6.8877E-41)
            int r6 = r6 - r9
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r1.print(r6)
            goto L_0x0416
        L_0x014c:
            java.lang.String r15 = " -> #"
            r2 = 39
            java.lang.String r7 = " parent:"
            switch(r6) {
                case 61696: goto L_0x0411;
                case 61697: goto L_0x040b;
                case 61698: goto L_0x03ef;
                case 61699: goto L_0x03d3;
                case 61700: goto L_0x03b3;
                case 61701: goto L_0x0394;
                case 61702: goto L_0x038c;
                case 61703: goto L_0x0155;
                case 61704: goto L_0x033f;
                case 61705: goto L_0x02f5;
                case 61706: goto L_0x02ee;
                case 61707: goto L_0x02c4;
                case 61708: goto L_0x0261;
                case 61709: goto L_0x0125;
                case 61710: goto L_0x0125;
                case 61711: goto L_0x0217;
                case 61712: goto L_0x01f3;
                case 61713: goto L_0x01ec;
                case 61714: goto L_0x01d0;
                case 61715: goto L_0x01c9;
                case 61716: goto L_0x019f;
                case 61717: goto L_0x0187;
                case 61718: goto L_0x0180;
                case 61719: goto L_0x016a;
                case 61720: goto L_0x0157;
                default: goto L_0x0155;
            }
        L_0x0155:
            goto L_0x0416
        L_0x0157:
            java.lang.String r2 = "=DOCUMENT_URI: "
            r1.print(r2)
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.Object[] r5 = r0.objects
            r2 = r5[r2]
            r1.print(r2)
            goto L_0x0125
        L_0x016a:
            java.lang.String r5 = "=COMMENT: '"
            r1.print(r5)
            int r5 = r3 + 1
            int r5 = r0.getIntN(r5)
            char[] r6 = r0.data
            int r7 = r3 + 3
            r1.write(r6, r7, r5)
            r1.print(r2)
            goto L_0x019c
        L_0x0180:
            java.lang.String r2 = "= joiner"
            r1.print(r2)
            goto L_0x0416
        L_0x0187:
            java.lang.String r5 = "=CDATA: '"
            r1.print(r5)
            int r5 = r3 + 1
            int r5 = r0.getIntN(r5)
            char[] r6 = r0.data
            int r7 = r3 + 3
            r1.write(r6, r7, r5)
            r1.print(r2)
        L_0x019c:
            int r5 = r5 + r10
            goto L_0x0416
        L_0x019f:
            java.lang.String r5 = "=PROCESSING_INSTRUCTION: "
            r1.print(r5)
            int r5 = r3 + 1
            int r5 = r0.getIntN(r5)
            java.lang.Object[] r6 = r0.objects
            r5 = r6[r5]
            r1.print(r5)
            java.lang.String r5 = " '"
            r1.print(r5)
            int r5 = r3 + 3
            int r5 = r0.getIntN(r5)
            char[] r6 = r0.data
            int r7 = r3 + 5
            r1.write(r6, r7, r5)
            r1.print(r2)
            int r5 = r5 + r8
            goto L_0x0416
        L_0x01c9:
            java.lang.String r2 = "=END_ENTITY"
            r1.print(r2)
            goto L_0x0416
        L_0x01d0:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.String r5 = "=BEGIN_ENTITY base:"
            r1.print(r5)
            r1.print(r2)
            r1.print(r7)
            int r2 = r3 + 3
            int r2 = r0.getIntN(r2)
            r1.print(r2)
            goto L_0x03ed
        L_0x01ec:
            java.lang.String r2 = "=END_DOCUMENT"
            r1.print(r2)
            goto L_0x0416
        L_0x01f3:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            if (r2 >= 0) goto L_0x01ff
            char[] r5 = r0.data
            int r5 = r5.length
            goto L_0x0200
        L_0x01ff:
            r5 = r3
        L_0x0200:
            int r2 = r2 + r5
            java.lang.String r5 = "=BEGIN_DOCUMENT end:"
            r1.print(r5)
            r1.print(r2)
            r1.print(r7)
            int r2 = r3 + 3
            int r2 = r0.getIntN(r2)
            r1.print(r2)
            goto L_0x03ed
        L_0x0217:
            java.lang.String r2 = "=POSITION_PAIR_FOLLOWS seq:"
            r1.print(r2)
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            r1.print(r2)
            r1.print(r9)
            java.lang.Object[] r5 = r0.objects
            r2 = r5[r2]
            if (r2 != 0) goto L_0x0230
            r5 = 0
            goto L_0x0238
        L_0x0230:
            java.lang.Class r5 = r2.getClass()
            java.lang.String r5 = r5.getName()
        L_0x0238:
            r1.print(r5)
            r1.print(r11)
            if (r2 != 0) goto L_0x0246
            java.lang.String r2 = "null"
            r1.print(r2)
            goto L_0x0251
        L_0x0246:
            int r2 = java.lang.System.identityHashCode(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            r1.print(r2)
        L_0x0251:
            java.lang.String r2 = " ipos:"
            r1.print(r2)
            int r2 = r3 + 3
            int r2 = r0.getIntN(r2)
            r1.print(r2)
            goto L_0x03ed
        L_0x0261:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "=END_ELEMENT_LONG name:"
            r5.append(r6)
            r5.append(r2)
            r5.append(r14)
            java.lang.Object[] r6 = r0.objects
            r2 = r6[r2]
            r5.append(r2)
            r5.append(r13)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            int r2 = r3 + 3
            int r2 = r0.getIntN(r2)
            if (r2 >= 0) goto L_0x0291
            int r2 = r2 + r3
        L_0x0291:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = " begin:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            int r2 = r3 + 5
            int r2 = r0.getIntN(r2)
            if (r2 >= 0) goto L_0x02ae
            int r2 = r2 + r3
        L_0x02ae:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            r2 = 6
            r5 = 6
            goto L_0x0416
        L_0x02c4:
            java.lang.String r2 = "=END_ELEMENT_SHORT begin:"
            r1.print(r2)
            char[] r2 = r0.data
            int r5 = r3 + 1
            char r2 = r2[r5]
            int r2 = r3 - r2
            r1.print(r2)
            char[] r5 = r0.data
            char r2 = r5[r2]
            int r2 = r2 - r12
            r1.print(r15)
            r1.print(r2)
            r1.print(r14)
            java.lang.Object[] r5 = r0.objects
            r2 = r5[r2]
            r1.print(r2)
            r1.print(r13)
            goto L_0x0391
        L_0x02ee:
            java.lang.String r2 = "=END_ATTRIBUTE"
            r1.print(r2)
            goto L_0x0416
        L_0x02f5:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "=BEGIN_ATTRIBUTE name:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r6 = "="
            r5.append(r6)
            java.lang.Object[] r6 = r0.objects
            r2 = r6[r2]
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            int r2 = r3 + 3
            int r2 = r0.getIntN(r2)
            if (r2 >= 0) goto L_0x0327
            char[] r5 = r0.data
            int r5 = r5.length
            goto L_0x0328
        L_0x0327:
            r5 = r3
        L_0x0328:
            int r2 = r2 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = " end:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            goto L_0x03ed
        L_0x033f:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            if (r2 >= 0) goto L_0x034b
            char[] r5 = r0.data
            int r5 = r5.length
            goto L_0x034c
        L_0x034b:
            r5 = r3
        L_0x034c:
            int r2 = r2 + r5
            java.lang.String r5 = "=BEGIN_ELEMENT_LONG end:"
            r1.print(r5)
            r1.print(r2)
            r5 = 1
            int r2 = r2 + r5
            int r2 = r0.getIntN(r2)
            r1.print(r15)
            r1.print(r2)
            if (r2 < 0) goto L_0x0385
            int r5 = r2 + 1
            java.lang.Object[] r6 = r0.objects
            int r6 = r6.length
            if (r5 >= r6) goto L_0x0385
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r14)
            java.lang.Object[] r6 = r0.objects
            r2 = r6[r2]
            r5.append(r2)
            r5.append(r13)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            goto L_0x0125
        L_0x0385:
            java.lang.String r2 = "=<out-of-bounds>"
            r1.print(r2)
            goto L_0x0125
        L_0x038c:
            java.lang.String r2 = "=CHAR_FOLLOWS"
            r1.print(r2)
        L_0x0391:
            r5 = 1
            goto L_0x0416
        L_0x0394:
            int r2 = r3 + 1
            long r5 = r0.getLongN(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "=DOUBLE_FOLLOWS value:"
            r2.append(r7)
            double r5 = java.lang.Double.longBitsToDouble(r5)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.print(r2)
            goto L_0x03ed
        L_0x03b3:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "=FLOAT_FOLLOWS value:"
            r5.append(r6)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.write(r2)
            goto L_0x0125
        L_0x03d3:
            int r2 = r3 + 1
            long r5 = r0.getLongN(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "=LONG_FOLLOWS value:"
            r2.append(r7)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.print(r2)
        L_0x03ed:
            r5 = 4
            goto L_0x0416
        L_0x03ef:
            int r2 = r3 + 1
            int r2 = r0.getIntN(r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "=INT_FOLLOWS value:"
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.print(r2)
            goto L_0x0125
        L_0x040b:
            java.lang.String r2 = "= true"
            r1.print(r2)
            goto L_0x0416
        L_0x0411:
            java.lang.String r2 = "= false"
            r1.print(r2)
        L_0x0416:
            r17.println()
            if (r5 <= 0) goto L_0x0014
            int r3 = r3 + r5
            r2 = 1
            r5 = 0
        L_0x041e:
            int r3 = r3 + r2
            goto L_0x0009
        L_0x0421:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.TreeList.dump(java.io.PrintWriter, int, int):void");
    }
}
