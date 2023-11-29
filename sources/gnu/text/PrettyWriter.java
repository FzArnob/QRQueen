package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

public class PrettyWriter extends Writer {
    private static final int BLOCK_PER_LINE_PREFIX_END = -3;
    private static final int BLOCK_PREFIX_LENGTH = -4;
    private static final int BLOCK_SECTION_COLUMN = -2;
    private static final int BLOCK_SECTION_START_LINE = -6;
    private static final int BLOCK_START_COLUMN = -1;
    private static final int BLOCK_SUFFIX_LENGTH = -5;
    private static final int LOGICAL_BLOCK_LENGTH = 6;
    public static final int NEWLINE_FILL = 70;
    public static final int NEWLINE_LINEAR = 78;
    public static final int NEWLINE_LITERAL = 76;
    public static final int NEWLINE_MANDATORY = 82;
    public static final int NEWLINE_MISER = 77;
    public static final int NEWLINE_SPACE = 83;
    static final int QITEM_BASE_SIZE = 2;
    static final int QITEM_BLOCK_END_SIZE = 2;
    static final int QITEM_BLOCK_END_TYPE = 5;
    static final int QITEM_BLOCK_START_BLOCK_END = 4;
    static final int QITEM_BLOCK_START_PREFIX = 5;
    static final int QITEM_BLOCK_START_SIZE = 7;
    static final int QITEM_BLOCK_START_SUFFIX = 6;
    static final int QITEM_BLOCK_START_TYPE = 4;
    static final int QITEM_INDENTATION_AMOUNT = 3;
    static final char QITEM_INDENTATION_BLOCK = 'B';
    static final char QITEM_INDENTATION_CURRENT = 'C';
    static final int QITEM_INDENTATION_KIND = 2;
    static final int QITEM_INDENTATION_SIZE = 4;
    static final int QITEM_INDENTATION_TYPE = 3;
    static final int QITEM_NEWLINE_KIND = 4;
    static final int QITEM_NEWLINE_SIZE = 5;
    static final int QITEM_NEWLINE_TYPE = 2;
    static final int QITEM_NOP_TYPE = 0;
    static final int QITEM_POSN = 1;
    static final int QITEM_SECTION_START_DEPTH = 2;
    static final int QITEM_SECTION_START_SECTION_END = 3;
    static final int QITEM_SECTION_START_SIZE = 4;
    static final int QITEM_TAB_COLINC = 4;
    static final int QITEM_TAB_COLNUM = 3;
    static final int QITEM_TAB_FLAGS = 2;
    static final int QITEM_TAB_IS_RELATIVE = 2;
    static final int QITEM_TAB_IS_SECTION = 1;
    static final int QITEM_TAB_SIZE = 5;
    static final int QITEM_TAB_TYPE = 6;
    static final int QITEM_TYPE_AND_SIZE = 0;
    static final int QUEUE_INIT_ALLOC_SIZE = 300;
    public static ThreadLocation indentLoc = new ThreadLocation("indent");
    public static int initialBufferSize = 126;
    public static ThreadLocation lineLengthLoc = new ThreadLocation("line-length");
    public static ThreadLocation miserWidthLoc = new ThreadLocation("miser-width");
    int blockDepth;
    int[] blocks;
    public char[] buffer;
    public int bufferFillPointer;
    int bufferOffset;
    int bufferStartColumn;
    int currentBlock;
    int lineLength = 80;
    int lineNumber;
    int miserWidth = 40;
    protected Writer out;
    public int pendingBlocksCount;
    char[] prefix;
    int prettyPrintingMode;
    int[] queueInts;
    int queueSize;
    String[] queueStrings;
    int queueTail;
    char[] suffix;
    boolean wordEndSeen;

    private static int enoughSpace(int i, int i2) {
        int i3 = i * 2;
        int i4 = i + ((i2 * 5) >> 2);
        return i3 > i4 ? i3 : i4;
    }

    /* access modifiers changed from: package-private */
    public int getMaxLines() {
        return -1;
    }

    public void lineAbbreviationHappened() {
    }

    /* access modifiers changed from: package-private */
    public boolean printReadably() {
        return true;
    }

    public PrettyWriter(Writer writer) {
        int i = initialBufferSize;
        this.buffer = new char[i];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i];
        this.suffix = new char[i];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = writer;
        this.prettyPrintingMode = 1;
    }

    public PrettyWriter(Writer writer, int i) {
        int i2 = initialBufferSize;
        this.buffer = new char[i2];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i2];
        this.suffix = new char[i2];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = writer;
        this.lineLength = i;
        this.prettyPrintingMode = i <= 1 ? 0 : 1;
    }

    public PrettyWriter(Writer writer, boolean z) {
        int i = initialBufferSize;
        this.buffer = new char[i];
        this.blocks = new int[60];
        this.blockDepth = 6;
        this.prefix = new char[i];
        this.suffix = new char[i];
        this.queueInts = new int[QUEUE_INIT_ALLOC_SIZE];
        this.queueStrings = new String[QUEUE_INIT_ALLOC_SIZE];
        this.currentBlock = -1;
        this.out = writer;
        this.prettyPrintingMode = z ? 1 : 0;
    }

    public void setPrettyPrintingMode(int i) {
        this.prettyPrintingMode = i;
    }

    public int getPrettyPrintingMode() {
        return this.prettyPrintingMode;
    }

    public boolean isPrettyPrinting() {
        return this.prettyPrintingMode > 0;
    }

    public void setPrettyPrinting(boolean z) {
        this.prettyPrintingMode = z ^ true ? 1 : 0;
    }

    private int indexPosn(int i) {
        return i + this.bufferOffset;
    }

    private int posnIndex(int i) {
        return i - this.bufferOffset;
    }

    private int posnColumn(int i) {
        return indexColumn(posnIndex(i));
    }

    private int getQueueType(int i) {
        return this.queueInts[i] & 255;
    }

    private int getQueueSize(int i) {
        return this.queueInts[i] >> 16;
    }

    private int getSectionColumn() {
        return this.blocks[this.blockDepth - 2];
    }

    private int getStartColumn() {
        return this.blocks[this.blockDepth - 1];
    }

    private int getPerLinePrefixEnd() {
        return this.blocks[this.blockDepth - 3];
    }

    private int getPrefixLength() {
        return this.blocks[this.blockDepth - 4];
    }

    private int getSuffixLength() {
        return this.blocks[this.blockDepth - 5];
    }

    private int getSectionStartLine() {
        return this.blocks[this.blockDepth - 6];
    }

    public void writeWordEnd() {
        this.wordEndSeen = true;
    }

    public void writeWordStart() {
        if (this.wordEndSeen) {
            write(32);
        }
        this.wordEndSeen = false;
    }

    public void clearWordEnd() {
        this.wordEndSeen = false;
    }

    public void write(int i) {
        this.wordEndSeen = false;
        if (i != 10 || this.prettyPrintingMode <= 0) {
            ensureSpaceInBuffer(1);
            int i2 = this.bufferFillPointer;
            this.buffer[i2] = (char) i;
            this.bufferFillPointer = i2 + 1;
            if (i == 32 && this.prettyPrintingMode > 1 && this.currentBlock < 0) {
                enqueueNewline(83);
                return;
            }
            return;
        }
        enqueueNewline(76);
    }

    public void write(String str) {
        write(str, 0, str.length());
    }

    public void write(String str, int i, int i2) {
        int i3;
        this.wordEndSeen = false;
        while (i2 > 0) {
            int ensureSpaceInBuffer = ensureSpaceInBuffer(i2);
            if (i2 <= ensureSpaceInBuffer) {
                ensureSpaceInBuffer = i2;
            }
            int i4 = this.bufferFillPointer;
            i2 -= ensureSpaceInBuffer;
            while (true) {
                ensureSpaceInBuffer--;
                if (ensureSpaceInBuffer < 0) {
                    break;
                }
                int i5 = i + 1;
                char charAt = str.charAt(i);
                if (charAt != 10 || this.prettyPrintingMode <= 0) {
                    int i6 = i4 + 1;
                    this.buffer[i4] = charAt;
                    if (charAt != ' ' || this.prettyPrintingMode <= 1 || this.currentBlock >= 0) {
                        i4 = i6;
                        i = i5;
                    } else {
                        this.bufferFillPointer = i6;
                        enqueueNewline(83);
                        i3 = this.bufferFillPointer;
                    }
                } else {
                    this.bufferFillPointer = i4;
                    enqueueNewline(76);
                    i3 = this.bufferFillPointer;
                }
                i4 = i3;
                i = i5;
            }
            this.bufferFillPointer = i4;
        }
    }

    public void write(char[] cArr) {
        write(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) {
        char c;
        this.wordEndSeen = false;
        int i3 = i + i2;
        while (i2 > 0) {
            int i4 = i;
            while (true) {
                if (i4 >= i3) {
                    do {
                        int ensureSpaceInBuffer = ensureSpaceInBuffer(i2);
                        if (ensureSpaceInBuffer >= i2) {
                            ensureSpaceInBuffer = i2;
                        }
                        int i5 = this.bufferFillPointer;
                        int i6 = i5 + ensureSpaceInBuffer;
                        while (i5 < i6) {
                            this.buffer[i5] = cArr[i];
                            i5++;
                            i++;
                        }
                        this.bufferFillPointer = i6;
                        i2 -= ensureSpaceInBuffer;
                    } while (i2 != 0);
                } else if (this.prettyPrintingMode <= 0 || ((c = cArr[i4]) != 10 && (c != ' ' || this.currentBlock >= 0))) {
                    i4++;
                }
            }
            write(cArr, i, i4 - i);
            write((int) c);
            i = i4 + 1;
            i2 = i3 - i;
        }
    }

    private void pushLogicalBlock(int i, int i2, int i3, int i4, int i5) {
        int i6 = this.blockDepth;
        int i7 = i6 + 6;
        int[] iArr = this.blocks;
        if (i7 >= iArr.length) {
            int[] iArr2 = new int[(iArr.length * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i6);
            this.blocks = iArr2;
        }
        this.blockDepth = i7;
        int[] iArr3 = this.blocks;
        iArr3[i7 - 1] = i;
        iArr3[i7 - 2] = i;
        iArr3[i7 - 3] = i2;
        iArr3[i7 - 4] = i3;
        iArr3[i7 - 5] = i4;
        iArr3[i7 - 6] = i5;
    }

    /* access modifiers changed from: package-private */
    public void reallyStartLogicalBlock(int i, String str, String str2) {
        int perLinePrefixEnd = getPerLinePrefixEnd();
        int prefixLength = getPrefixLength();
        int suffixLength = getSuffixLength();
        pushLogicalBlock(i, perLinePrefixEnd, prefixLength, suffixLength, this.lineNumber);
        setIndentation(i);
        if (str != null) {
            this.blocks[this.blockDepth - 3] = i;
            int length = str.length();
            str.getChars(0, length, this.suffix, i - length);
        }
        if (str2 != null) {
            char[] cArr = this.suffix;
            int length2 = cArr.length;
            int length3 = str2.length();
            int i2 = suffixLength + length3;
            if (i2 > length2) {
                int enoughSpace = enoughSpace(length2, length3);
                char[] cArr2 = new char[enoughSpace];
                this.suffix = cArr2;
                System.arraycopy(cArr, length2 - suffixLength, cArr2, enoughSpace - suffixLength, suffixLength);
                length2 = enoughSpace;
            }
            str2.getChars(0, length3, cArr, length2 - i2);
            this.blocks[this.blockDepth - 5] = i2;
        }
    }

    /* access modifiers changed from: package-private */
    public int enqueueTab(int i, int i2, int i3) {
        int enqueue = enqueue(6, 5);
        int[] iArr = this.queueInts;
        iArr[enqueue + 2] = i;
        iArr[enqueue + 3] = i2;
        iArr[enqueue + 4] = i3;
        return enqueue;
    }

    public void setIndentation(int i) {
        char[] cArr = this.prefix;
        int length = cArr.length;
        int prefixLength = getPrefixLength();
        int perLinePrefixEnd = getPerLinePrefixEnd();
        if (perLinePrefixEnd > i) {
            i = perLinePrefixEnd;
        }
        if (i > length) {
            cArr = new char[enoughSpace(length, i - length)];
            System.arraycopy(this.prefix, 0, cArr, 0, prefixLength);
            this.prefix = cArr;
        }
        if (i > prefixLength) {
            while (prefixLength < i) {
                cArr[prefixLength] = ' ';
                prefixLength++;
            }
        }
        this.blocks[this.blockDepth - 4] = i;
    }

    /* access modifiers changed from: package-private */
    public void reallyEndLogicalBlock() {
        int prefixLength = getPrefixLength();
        this.blockDepth -= 6;
        int prefixLength2 = getPrefixLength();
        if (prefixLength2 > prefixLength) {
            while (prefixLength < prefixLength2) {
                this.prefix[prefixLength] = ' ';
                prefixLength++;
            }
        }
    }

    public int enqueue(int i, int i2) {
        int length = this.queueInts.length;
        int i3 = (length - this.queueTail) - this.queueSize;
        if (i3 > 0 && i2 > i3) {
            enqueue(0, i3);
        }
        if (this.queueSize + i2 > length) {
            int enoughSpace = enoughSpace(length, i2);
            int[] iArr = new int[enoughSpace];
            String[] strArr = new String[enoughSpace];
            int i4 = (this.queueTail + this.queueSize) - length;
            if (i4 > 0) {
                System.arraycopy(this.queueInts, 0, iArr, 0, i4);
                System.arraycopy(this.queueStrings, 0, strArr, 0, i4);
            }
            int i5 = this.queueTail;
            int i6 = length - i5;
            int i7 = enoughSpace - length;
            System.arraycopy(this.queueInts, i5, iArr, i5 + i7, i6);
            String[] strArr2 = this.queueStrings;
            int i8 = this.queueTail;
            System.arraycopy(strArr2, i8, strArr, i8 + i7, i6);
            this.queueInts = iArr;
            this.queueStrings = strArr;
            int i9 = this.currentBlock;
            int i10 = this.queueTail;
            if (i9 >= i10) {
                this.currentBlock = i9 + i7;
            }
            this.queueTail = i10 + i7;
        }
        int i11 = this.queueTail + this.queueSize;
        int[] iArr2 = this.queueInts;
        if (i11 >= iArr2.length) {
            i11 -= iArr2.length;
        }
        iArr2[i11 + 0] = i | (i2 << 16);
        if (i2 > 1) {
            iArr2[i11 + 1] = indexPosn(this.bufferFillPointer);
        }
        this.queueSize += i2;
        return i11;
    }

    public void enqueueNewline(int i) {
        this.wordEndSeen = false;
        int i2 = this.pendingBlocksCount;
        int enqueue = enqueue(2, 5);
        int[] iArr = this.queueInts;
        iArr[enqueue + 4] = i;
        iArr[enqueue + 2] = this.pendingBlocksCount;
        iArr[enqueue + 3] = 0;
        int i3 = this.queueTail;
        int i4 = this.queueSize;
        while (i4 > 0) {
            if (i3 == this.queueInts.length) {
                i3 = 0;
            }
            if (i3 == enqueue) {
                break;
            }
            int queueType = getQueueType(i3);
            if (queueType == 2 || queueType == 4) {
                int[] iArr2 = this.queueInts;
                int i5 = i3 + 3;
                if (iArr2[i5] == 0 && i2 <= iArr2[i3 + 2]) {
                    int i6 = enqueue - i3;
                    if (i6 < 0) {
                        i6 += iArr2.length;
                    }
                    iArr2[i5] = i6;
                }
            }
            int queueSize2 = getQueueSize(i3);
            i4 -= queueSize2;
            i3 += queueSize2;
        }
        maybeOutput(i == 76 || i == 82, false);
    }

    public final void writeBreak(int i) {
        if (this.prettyPrintingMode > 0) {
            enqueueNewline(i);
        }
    }

    public int enqueueIndent(char c, int i) {
        int enqueue = enqueue(3, 4);
        int[] iArr = this.queueInts;
        iArr[enqueue + 2] = c;
        iArr[enqueue + 3] = i;
        return enqueue;
    }

    public void addIndentation(int i, boolean z) {
        if (this.prettyPrintingMode > 0) {
            enqueueIndent(z ? 'C' : QITEM_INDENTATION_BLOCK, i);
        }
    }

    public void startLogicalBlock(String str, boolean z, String str2) {
        int i;
        if (this.queueSize == 0 && this.bufferFillPointer == 0) {
            Object obj = lineLengthLoc.get((Object) null);
            if (obj == null) {
                this.lineLength = 80;
            } else {
                this.lineLength = Integer.parseInt(obj.toString());
            }
            Object obj2 = miserWidthLoc.get((Object) null);
            if (obj2 == null || obj2 == Boolean.FALSE || obj2 == LList.Empty) {
                this.miserWidth = -1;
            } else {
                this.miserWidth = Integer.parseInt(obj2.toString());
            }
            indentLoc.get((Object) null);
        }
        if (str != null) {
            write(str);
        }
        if (this.prettyPrintingMode != 0) {
            int enqueue = enqueue(4, 7);
            int[] iArr = this.queueInts;
            int i2 = this.pendingBlocksCount;
            iArr[enqueue + 2] = i2;
            String[] strArr = this.queueStrings;
            int i3 = enqueue + 5;
            if (!z) {
                str = null;
            }
            strArr[i3] = str;
            strArr[enqueue + 6] = str2;
            this.pendingBlocksCount = i2 + 1;
            int i4 = this.currentBlock;
            if (i4 < 0) {
                i = 0;
            } else {
                i = i4 - enqueue;
                if (i > 0) {
                    i -= iArr.length;
                }
            }
            iArr[enqueue + 4] = i;
            iArr[enqueue + 3] = 0;
            this.currentBlock = enqueue;
        }
    }

    public void endLogicalBlock() {
        int enqueue = enqueue(5, 2);
        this.pendingBlocksCount--;
        int i = this.currentBlock;
        if (i < 0) {
            int[] iArr = this.blocks;
            int i2 = this.blockDepth;
            int i3 = iArr[i2 - 5];
            int i4 = iArr[(i2 - 6) - 5];
            if (i3 > i4) {
                char[] cArr = this.suffix;
                write(cArr, cArr.length - i3, i3 - i4);
            }
            this.currentBlock = -1;
            return;
        }
        int[] iArr2 = this.queueInts;
        int i5 = i + 4;
        int i6 = iArr2[i5];
        if (i6 == 0) {
            this.currentBlock = -1;
        } else {
            int i7 = this.queueTail - i;
            if (i7 > 0) {
                i7 -= iArr2.length;
            }
            if (i6 < i7) {
                this.currentBlock = -1;
            } else {
                int i8 = i6 + i;
                if (i8 < 0) {
                    i8 += iArr2.length;
                }
                this.currentBlock = i8;
            }
        }
        String str = this.queueStrings[i + 6];
        if (str != null) {
            write(str);
        }
        int i9 = enqueue - i;
        if (i9 < 0) {
            i9 += this.queueInts.length;
        }
        this.queueInts[i5] = i9;
    }

    public void endLogicalBlock(String str) {
        if (this.prettyPrintingMode > 0) {
            endLogicalBlock();
        } else if (str != null) {
            write(str);
        }
    }

    /* access modifiers changed from: package-private */
    public int computeTabSize(int i, int i2, int i3) {
        int i4;
        int[] iArr = this.queueInts;
        int i5 = iArr[i + 2];
        boolean z = (i5 & 1) != 0;
        boolean z2 = (i5 & 2) != 0;
        if (!z) {
            i2 = 0;
        }
        int i6 = iArr[i + 3];
        int i7 = iArr[i + 4];
        if (z2) {
            return (i7 <= 1 || (i4 = (i3 + i6) % i7) == 0) ? i6 : i6 + i4;
        }
        if (i3 <= i6 + i2) {
            return (i2 + i3) - i3;
        }
        return i7 - ((i3 - i2) % i7);
    }

    /* access modifiers changed from: package-private */
    public int indexColumn(int i) {
        int i2 = this.bufferStartColumn;
        int sectionColumn = getSectionColumn();
        int indexPosn = indexPosn(i);
        int i3 = this.queueTail;
        int i4 = this.queueSize;
        while (i4 > 0) {
            if (i3 >= this.queueInts.length) {
                i3 = 0;
            }
            int queueType = getQueueType(i3);
            if (queueType != 0) {
                int i5 = this.queueInts[i3 + 1];
                if (i5 >= indexPosn) {
                    break;
                } else if (queueType == 6) {
                    i2 += computeTabSize(i3, sectionColumn, posnIndex(i5) + i2);
                } else if (queueType == 2 || queueType == 4) {
                    sectionColumn = posnIndex(i5) + i2;
                }
            }
            int queueSize2 = getQueueSize(i3);
            i4 -= queueSize2;
            i3 += queueSize2;
        }
        return i2 + i;
    }

    /* access modifiers changed from: package-private */
    public void expandTabs(int i) {
        char[] cArr;
        int i2;
        int i3;
        int i4 = this.bufferStartColumn;
        int sectionColumn = getSectionColumn();
        int i5 = this.queueTail;
        int i6 = this.queueSize;
        int i7 = 6;
        int i8 = this.pendingBlocksCount * 6;
        int i9 = 0;
        int i10 = 0;
        while (i6 > 0) {
            if (i5 == this.queueInts.length) {
                i2 = i;
                i5 = 0;
            } else {
                i2 = i;
            }
            if (i5 == i2) {
                break;
            }
            if (getQueueType(i5) == i7) {
                int posnIndex = posnIndex(this.queueInts[i5 + 1]);
                int computeTabSize = computeTabSize(i5, sectionColumn, i4 + posnIndex);
                if (computeTabSize != 0) {
                    int i11 = (i9 * 2) + i8;
                    int i12 = i11 + 1;
                    int[] iArr = this.blocks;
                    if (i12 >= iArr.length) {
                        int[] iArr2 = new int[(iArr.length * 2)];
                        i3 = sectionColumn;
                        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                        this.blocks = iArr2;
                    } else {
                        i3 = sectionColumn;
                    }
                    int[] iArr3 = this.blocks;
                    iArr3[i11] = posnIndex;
                    iArr3[i12] = computeTabSize;
                    i9++;
                    i10 += computeTabSize;
                    i4 += computeTabSize;
                } else {
                    i3 = sectionColumn;
                }
            } else {
                i3 = sectionColumn;
                if (i5 == 2 || i5 == 4) {
                    sectionColumn = posnIndex(this.queueInts[i5 + 1]) + i4;
                    int queueSize2 = getQueueSize(i5);
                    i6 -= queueSize2;
                    i5 += queueSize2;
                    i7 = 6;
                }
            }
            sectionColumn = i3;
            int queueSize22 = getQueueSize(i5);
            i6 -= queueSize22;
            i5 += queueSize22;
            i7 = 6;
        }
        if (i9 > 0) {
            int i13 = this.bufferFillPointer;
            int i14 = i13 + i10;
            char[] cArr2 = this.buffer;
            if (i14 > cArr2.length) {
                cArr = new char[enoughSpace(i13, i10)];
                this.buffer = cArr;
            } else {
                cArr = cArr2;
            }
            this.bufferFillPointer = i14;
            this.bufferOffset -= i10;
            while (true) {
                i9--;
                if (i9 < 0) {
                    break;
                }
                int[] iArr4 = this.blocks;
                int i15 = (i9 * 2) + i8;
                int i16 = iArr4[i15];
                int i17 = iArr4[i15 + 1];
                int i18 = i16 + i10;
                System.arraycopy(cArr2, i16, cArr, i18, i13 - i16);
                for (int i19 = i18 - i17; i19 < i18; i19++) {
                    cArr[i19] = ' ';
                }
                i10 -= i17;
                i13 = i16;
            }
            if (cArr != cArr2) {
                System.arraycopy(cArr2, 0, cArr, 0, i13);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int ensureSpaceInBuffer(int i) {
        char[] cArr = this.buffer;
        int length = cArr.length;
        int i2 = this.bufferFillPointer;
        int i3 = length - i2;
        if (i3 > 0) {
            return i3;
        }
        if (this.prettyPrintingMode <= 0 || i2 <= this.lineLength) {
            int enoughSpace = enoughSpace(length, i);
            char[] cArr2 = new char[enoughSpace];
            this.buffer = cArr2;
            int i4 = i2;
            while (true) {
                i4--;
                if (i4 < 0) {
                    return enoughSpace - i2;
                }
                cArr2[i4] = cArr[i4];
            }
        } else {
            if (!maybeOutput(false, false)) {
                outputPartialLine();
            }
            return ensureSpaceInBuffer(i);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b5, code lost:
        if (r3 != 83) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ca, code lost:
        if (r8.lineNumber <= getSectionStartLine()) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00cd, code lost:
        r3 = r8.queueInts;
        r4 = r3[r2 + 3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d3, code lost:
        if (r4 != 0) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d6, code lost:
        r5 = r2 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d9, code lost:
        if (r5 < r3.length) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00db, code lost:
        r5 = r5 - r3.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00dd, code lost:
        r5 = fitsOnLine(r5, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e1, code lost:
        if (r5 <= 0) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e3, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e5, code lost:
        if (r5 < 0) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e7, code lost:
        if (r10 == false) goto L_0x0110;
     */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0100 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean maybeOutput(boolean r9, boolean r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r8.queueSize
            if (r2 <= 0) goto L_0x0110
            int r2 = r8.queueTail
            int[] r3 = r8.queueInts
            int r3 = r3.length
            if (r2 < r3) goto L_0x000f
            r8.queueTail = r0
        L_0x000f:
            int r2 = r8.queueTail
            int r3 = r8.getQueueType(r2)
            r4 = 2
            r5 = -1
            r6 = 1
            if (r3 == r4) goto L_0x00a5
            r4 = 3
            if (r3 == r4) goto L_0x007f
            r4 = 4
            if (r3 == r4) goto L_0x0032
            r4 = 5
            if (r3 == r4) goto L_0x002d
            r4 = 6
            if (r3 == r4) goto L_0x0028
            goto L_0x0100
        L_0x0028:
            r8.expandTabs(r2)
            goto L_0x0100
        L_0x002d:
            r8.reallyEndLogicalBlock()
            goto L_0x0100
        L_0x0032:
            int[] r3 = r8.queueInts
            int r4 = r2 + 3
            r4 = r3[r4]
            if (r4 <= 0) goto L_0x003e
            int r4 = r4 + r2
            int r3 = r3.length
            int r4 = r4 % r3
            goto L_0x003f
        L_0x003e:
            r4 = -1
        L_0x003f:
            int r3 = r8.fitsOnLine(r4, r9)
            if (r3 <= 0) goto L_0x005a
            int[] r3 = r8.queueInts
            int r4 = r2 + 4
            r4 = r3[r4]
            int r6 = r4 + r2
            int r3 = r3.length
            int r6 = r6 % r3
            r8.expandTabs(r6)
            r8.queueTail = r6
            int r3 = r8.queueSize
            int r3 = r3 - r4
            r8.queueSize = r3
            goto L_0x0076
        L_0x005a:
            if (r3 < 0) goto L_0x005e
            if (r10 == 0) goto L_0x0110
        L_0x005e:
            java.lang.String[] r3 = r8.queueStrings
            int r4 = r2 + 5
            r4 = r3[r4]
            int r6 = r2 + 6
            r3 = r3[r6]
            int[] r6 = r8.queueInts
            int r7 = r2 + 1
            r6 = r6[r7]
            int r6 = r8.posnColumn(r6)
            r8.reallyStartLogicalBlock(r6, r4, r3)
            r6 = r2
        L_0x0076:
            int r3 = r8.currentBlock
            if (r3 != r2) goto L_0x007c
            r8.currentBlock = r5
        L_0x007c:
            r2 = r6
            goto L_0x0100
        L_0x007f:
            boolean r3 = r8.isMisering()
            if (r3 != 0) goto L_0x0100
            int[] r3 = r8.queueInts
            int r4 = r2 + 2
            r4 = r3[r4]
            int r5 = r2 + 3
            r5 = r3[r5]
            r6 = 66
            if (r4 != r6) goto L_0x0098
            int r3 = r8.getStartColumn()
            goto L_0x00a0
        L_0x0098:
            int r4 = r2 + 1
            r3 = r3[r4]
            int r3 = r8.posnColumn(r3)
        L_0x00a0:
            int r5 = r5 + r3
            r8.setIndentation(r5)
            goto L_0x0100
        L_0x00a5:
            int[] r3 = r8.queueInts
            int r4 = r2 + 4
            r3 = r3[r4]
            r4 = 70
            if (r3 == r4) goto L_0x00be
            r4 = 77
            if (r3 == r4) goto L_0x00b9
            r4 = 83
            if (r3 == r4) goto L_0x00cd
        L_0x00b7:
            r3 = 1
            goto L_0x00ea
        L_0x00b9:
            boolean r3 = r8.isMisering()
            goto L_0x00ea
        L_0x00be:
            boolean r3 = r8.isMisering()
            if (r3 != 0) goto L_0x00b7
            int r3 = r8.lineNumber
            int r4 = r8.getSectionStartLine()
            if (r3 <= r4) goto L_0x00cd
            goto L_0x00b7
        L_0x00cd:
            int[] r3 = r8.queueInts
            int r4 = r2 + 3
            r4 = r3[r4]
            if (r4 != 0) goto L_0x00d6
            goto L_0x00dd
        L_0x00d6:
            int r5 = r2 + r4
            int r4 = r3.length
            if (r5 < r4) goto L_0x00dd
            int r3 = r3.length
            int r5 = r5 - r3
        L_0x00dd:
            int r5 = r8.fitsOnLine(r5, r9)
            if (r5 <= 0) goto L_0x00e5
            r3 = 0
            goto L_0x00ea
        L_0x00e5:
            if (r5 < 0) goto L_0x00b7
            if (r10 == 0) goto L_0x0110
            goto L_0x00b7
        L_0x00ea:
            if (r3 == 0) goto L_0x0100
            if (r10 == 0) goto L_0x00f4
            if (r5 != 0) goto L_0x00f4
            r8.outputPartialLine()     // Catch:{ IOException -> 0x00f9 }
            goto L_0x00f7
        L_0x00f4:
            r8.outputLine(r2)     // Catch:{ IOException -> 0x00f9 }
        L_0x00f7:
            r1 = 1
            goto L_0x0100
        L_0x00f9:
            r9 = move-exception
            java.lang.RuntimeException r10 = new java.lang.RuntimeException
            r10.<init>(r9)
            throw r10
        L_0x0100:
            int r3 = r8.queueTail
            int r3 = r8.getQueueSize(r3)
            int r4 = r8.queueSize
            int r4 = r4 - r3
            r8.queueSize = r4
            int r2 = r2 + r3
            r8.queueTail = r2
            goto L_0x0002
        L_0x0110:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.PrettyWriter.maybeOutput(boolean, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public int getMiserWidth() {
        return this.miserWidth;
    }

    /* access modifiers changed from: package-private */
    public boolean isMisering() {
        int miserWidth2 = getMiserWidth();
        return miserWidth2 > 0 && this.lineLength - getStartColumn() <= miserWidth2;
    }

    /* access modifiers changed from: package-private */
    public int fitsOnLine(int i, boolean z) {
        int i2 = this.lineLength;
        if (!printReadably() && getMaxLines() == this.lineNumber) {
            i2 = (i2 - 3) - getSuffixLength();
        }
        if (i >= 0) {
            if (posnColumn(this.queueInts[i + 1]) <= i2) {
                return 1;
            }
            return -1;
        } else if (!z && indexColumn(this.bufferFillPointer) <= i2) {
            return 0;
        } else {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void outputLine(int i) throws IOException {
        int i2;
        char[] cArr;
        int maxLines;
        char[] cArr2 = this.buffer;
        int[] iArr = this.queueInts;
        boolean z = iArr[i + 4] == 76;
        int posnIndex = posnIndex(iArr[i + 1]);
        if (!z) {
            int i3 = posnIndex;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (cArr2[i3] != ' ') {
                        i2 = i3 + 1;
                        break;
                    }
                } else {
                    i2 = 0;
                    break;
                }
            }
        } else {
            i2 = posnIndex;
        }
        this.out.write(cArr2, 0, i2);
        int i4 = this.lineNumber + 1;
        if (!printReadably() && (maxLines = getMaxLines()) > 0 && i4 >= maxLines) {
            this.out.write(" ..");
            int suffixLength = getSuffixLength();
            if (suffixLength != 0) {
                char[] cArr3 = this.suffix;
                this.out.write(cArr3, cArr3.length - suffixLength, suffixLength);
            }
            lineAbbreviationHappened();
        }
        this.lineNumber = i4;
        this.out.write(10);
        this.bufferStartColumn = 0;
        int i5 = this.bufferFillPointer;
        int perLinePrefixEnd = z ? getPerLinePrefixEnd() : getPrefixLength();
        int i6 = posnIndex - perLinePrefixEnd;
        int i7 = i5 - i6;
        int length = cArr2.length;
        if (i7 > length) {
            cArr = new char[enoughSpace(length, i7 - length)];
            this.buffer = cArr;
        } else {
            cArr = cArr2;
        }
        System.arraycopy(cArr2, posnIndex, cArr, perLinePrefixEnd, i5 - posnIndex);
        System.arraycopy(this.prefix, 0, cArr, 0, perLinePrefixEnd);
        this.bufferFillPointer = i7;
        this.bufferOffset += i6;
        if (!z) {
            int[] iArr2 = this.blocks;
            int i8 = this.blockDepth;
            iArr2[i8 - 2] = perLinePrefixEnd;
            iArr2[i8 - 6] = i4;
        }
    }

    /* access modifiers changed from: package-private */
    public void outputPartialLine() {
        int i = this.queueTail;
        while (this.queueSize > 0 && getQueueType(i) == 0) {
            int queueSize2 = getQueueSize(i);
            this.queueSize -= queueSize2;
            i += queueSize2;
            if (i == this.queueInts.length) {
                i = 0;
            }
            this.queueTail = i;
        }
        int i2 = this.bufferFillPointer;
        int posnIndex = this.queueSize > 0 ? posnIndex(this.queueInts[i + 1]) : i2;
        int i3 = i2 - posnIndex;
        if (posnIndex > 0) {
            try {
                this.out.write(this.buffer, 0, posnIndex);
                this.bufferFillPointer = posnIndex;
                this.bufferStartColumn = getColumnNumber();
                char[] cArr = this.buffer;
                System.arraycopy(cArr, posnIndex, cArr, 0, i3);
                this.bufferFillPointer = i3;
                this.bufferOffset += posnIndex;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new Error("outputPartialLine called when nothing can be output.");
        }
    }

    public void forcePrettyOutput() throws IOException {
        maybeOutput(false, true);
        if (this.bufferFillPointer > 0) {
            outputPartialLine();
        }
        expandTabs(-1);
        this.bufferStartColumn = getColumnNumber();
        this.out.write(this.buffer, 0, this.bufferFillPointer);
        this.bufferFillPointer = 0;
        this.queueSize = 0;
    }

    public void flush() {
        if (this.out != null) {
            try {
                forcePrettyOutput();
                this.out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e.toString());
            }
        }
    }

    public void close() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out.close();
            this.out = null;
        }
        this.buffer = null;
    }

    public void closeThis() throws IOException {
        if (this.out != null) {
            forcePrettyOutput();
            this.out = null;
        }
        this.buffer = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0006 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getColumnNumber() {
        /*
            r3 = this;
            int r0 = r3.bufferFillPointer
        L_0x0002:
            int r0 = r0 + -1
            if (r0 >= 0) goto L_0x000c
            int r0 = r3.bufferStartColumn
            int r1 = r3.bufferFillPointer
            int r0 = r0 + r1
            return r0
        L_0x000c:
            char[] r1 = r3.buffer
            char r1 = r1[r0]
            r2 = 10
            if (r1 == r2) goto L_0x0018
            r2 = 13
            if (r1 != r2) goto L_0x0002
        L_0x0018:
            int r1 = r3.bufferFillPointer
            int r0 = r0 + 1
            int r1 = r1 - r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.PrettyWriter.getColumnNumber():int");
    }

    public void setColumnNumber(int i) {
        this.bufferStartColumn += i - getColumnNumber();
    }

    public void clearBuffer() {
        this.bufferStartColumn = 0;
        this.bufferFillPointer = 0;
        this.lineNumber = 0;
        this.bufferOffset = 0;
        this.blockDepth = 6;
        this.queueTail = 0;
        this.queueSize = 0;
        this.pendingBlocksCount = 0;
    }
}
