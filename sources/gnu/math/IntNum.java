package gnu.math;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.lingala.zip4j.util.InternalZipConstants;

public class IntNum extends RatNum implements Externalizable {
    static final int maxFixNum = 1024;
    static final int minFixNum = -100;
    static final int numFixNum = 1125;
    static final IntNum[] smallFixNums = new IntNum[numFixNum];
    public int ival;
    public int[] words;

    public static int shift(int i, int i2) {
        if (i2 >= 32) {
            return 0;
        }
        if (i2 >= 0) {
            return i << i2;
        }
        int i3 = -i2;
        return i3 >= 32 ? i < 0 ? -1 : 0 : i >> i3;
    }

    public static long shift(long j, int i) {
        if (i >= 32) {
            return 0;
        }
        if (i >= 0) {
            return j << i;
        }
        int i2 = -i;
        return i2 >= 32 ? j < 0 ? -1 : 0 : j >> i2;
    }

    public final IntNum numerator() {
        return this;
    }

    public IntNum toExactInt(int i) {
        return this;
    }

    public RealNum toInt(int i) {
        return this;
    }

    static {
        int i = numFixNum;
        while (true) {
            i--;
            if (i >= 0) {
                smallFixNums[i] = new IntNum(i + minFixNum);
            } else {
                return;
            }
        }
    }

    public IntNum() {
    }

    public IntNum(int i) {
        this.ival = i;
    }

    public static IntNum make(int i) {
        if (i < minFixNum || i > 1024) {
            return new IntNum(i);
        }
        return smallFixNums[i - minFixNum];
    }

    public static final IntNum zero() {
        return smallFixNums[100];
    }

    public static final IntNum one() {
        return smallFixNums[101];
    }

    public static final IntNum ten() {
        return smallFixNums[110];
    }

    public static IntNum minusOne() {
        return smallFixNums[99];
    }

    public static IntNum make(long j) {
        if (j >= -100 && j <= 1024) {
            return smallFixNums[((int) j) + 100];
        }
        int i = (int) j;
        if (((long) i) == j) {
            return new IntNum(i);
        }
        IntNum alloc = alloc(2);
        alloc.ival = 2;
        int[] iArr = alloc.words;
        iArr[0] = i;
        iArr[1] = (int) (j >> 32);
        return alloc;
    }

    public static IntNum asIntNumOrNull(Object obj) {
        if (obj instanceof IntNum) {
            return (IntNum) obj;
        }
        if (obj instanceof BigInteger) {
            return valueOf(obj.toString(), 10);
        }
        if (!(obj instanceof Number)) {
            return null;
        }
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof Byte)) {
            return make(((Number) obj).longValue());
        }
        return null;
    }

    public static IntNum makeU(long j) {
        if (j >= 0) {
            return make(j);
        }
        IntNum alloc = alloc(3);
        alloc.ival = 3;
        int[] iArr = alloc.words;
        iArr[0] = (int) j;
        iArr[1] = (int) (j >> 32);
        iArr[2] = 0;
        return alloc;
    }

    public static IntNum make(int[] iArr, int i) {
        if (iArr == null) {
            return make(i);
        }
        int wordsNeeded = wordsNeeded(iArr, i);
        if (wordsNeeded <= 1) {
            return wordsNeeded == 0 ? zero() : make(iArr[0]);
        }
        IntNum intNum = new IntNum();
        intNum.words = iArr;
        intNum.ival = wordsNeeded;
        return intNum;
    }

    public static IntNum make(int[] iArr) {
        return make(iArr, iArr.length);
    }

    public static IntNum alloc(int i) {
        if (i <= 1) {
            return new IntNum();
        }
        IntNum intNum = new IntNum();
        intNum.words = new int[i];
        return intNum;
    }

    public void realloc(int i) {
        if (i == 0) {
            int[] iArr = this.words;
            if (iArr != null) {
                if (this.ival > 0) {
                    this.ival = iArr[0];
                }
                this.words = null;
                return;
            }
            return;
        }
        int[] iArr2 = this.words;
        if (iArr2 == null || iArr2.length < i || iArr2.length > i + 2) {
            int[] iArr3 = new int[i];
            if (iArr2 == null) {
                iArr3[0] = this.ival;
                this.ival = 1;
            } else {
                if (i < this.ival) {
                    this.ival = i;
                }
                System.arraycopy(iArr2, 0, iArr3, 0, this.ival);
            }
            this.words = iArr3;
        }
    }

    public final IntNum denominator() {
        return one();
    }

    public final boolean isNegative() {
        int[] iArr = this.words;
        return (iArr == null ? this.ival : iArr[this.ival - 1]) < 0;
    }

    public int sign() {
        int i = this.ival;
        int[] iArr = this.words;
        if (iArr != null) {
            int i2 = i - 1;
            int i3 = iArr[i2];
            if (i3 > 0) {
                return 1;
            }
            if (i3 < 0) {
                return -1;
            }
            while (i2 != 0) {
                i2--;
                if (iArr[i2] != 0) {
                    return 1;
                }
            }
            return 0;
        } else if (i > 0) {
            return 1;
        } else {
            return i < 0 ? -1 : 0;
        }
    }

    public static int compare(IntNum intNum, IntNum intNum2) {
        boolean z = false;
        if (intNum.words == null && intNum2.words == null) {
            int i = intNum.ival;
            int i2 = intNum2.ival;
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            return 0;
        }
        boolean isNegative = intNum.isNegative();
        if (isNegative == intNum2.isNegative()) {
            int[] iArr = intNum.words;
            int i3 = iArr == null ? 1 : intNum.ival;
            int[] iArr2 = intNum2.words;
            int i4 = iArr2 == null ? 1 : intNum2.ival;
            if (i3 == i4) {
                return MPN.cmp(iArr, iArr2, i3);
            }
            if (i3 > i4) {
                z = true;
            }
            if (z != isNegative) {
                return 1;
            }
            return -1;
        } else if (isNegative) {
            return -1;
        } else {
            return 1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int compare(gnu.math.IntNum r7, long r8) {
        /*
            int[] r0 = r7.words
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x000b
            int r7 = r7.ival
        L_0x0009:
            long r4 = (long) r7
            goto L_0x0033
        L_0x000b:
            boolean r0 = r7.isNegative()
            r4 = 0
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0017
            r4 = 1
            goto L_0x0018
        L_0x0017:
            r4 = 0
        L_0x0018:
            if (r0 == r4) goto L_0x001f
            if (r0 == 0) goto L_0x001d
            goto L_0x001e
        L_0x001d:
            r1 = 1
        L_0x001e:
            return r1
        L_0x001f:
            int[] r4 = r7.words
            if (r4 != 0) goto L_0x0025
            r5 = 1
            goto L_0x0027
        L_0x0025:
            int r5 = r7.ival
        L_0x0027:
            if (r5 != r3) goto L_0x002c
            r7 = r4[r2]
            goto L_0x0009
        L_0x002c:
            r4 = 2
            if (r5 != r4) goto L_0x003e
            long r4 = r7.longValue()
        L_0x0033:
            int r7 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x0038
            goto L_0x003d
        L_0x0038:
            if (r7 <= 0) goto L_0x003c
            r1 = 1
            goto L_0x003d
        L_0x003c:
            r1 = 0
        L_0x003d:
            return r1
        L_0x003e:
            if (r0 == 0) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r1 = 1
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.IntNum.compare(gnu.math.IntNum, long):int");
    }

    public int compare(Object obj) {
        if (obj instanceof IntNum) {
            return compare(this, (IntNum) obj);
        }
        return ((RealNum) obj).compareReversed(this);
    }

    public final boolean isOdd() {
        int[] iArr = this.words;
        return ((iArr == null ? this.ival : iArr[0]) & 1) != 0;
    }

    public final boolean isZero() {
        return this.words == null && this.ival == 0;
    }

    public final boolean isOne() {
        return this.words == null && this.ival == 1;
    }

    public final boolean isMinusOne() {
        return this.words == null && this.ival == -1;
    }

    public static int wordsNeeded(int[] iArr, int i) {
        if (i > 0) {
            i--;
            int i2 = iArr[i];
            if (i2 != -1) {
                while (i2 == 0 && i > 0) {
                    i2 = iArr[i - 1];
                    if (i2 < 0) {
                        break;
                    }
                    i--;
                }
            } else {
                while (i > 0) {
                    int i3 = iArr[i - 1];
                    if (i3 >= 0) {
                        break;
                    }
                    i--;
                    if (i3 != -1) {
                        break;
                    }
                }
            }
        }
        return i + 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r0 = r3.ival;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.math.IntNum canonicalize() {
        /*
            r3 = this;
            int[] r0 = r3.words
            if (r0 == 0) goto L_0x001b
            int r1 = r3.ival
            int r0 = wordsNeeded(r0, r1)
            r3.ival = r0
            r1 = 1
            if (r0 > r1) goto L_0x001b
            if (r0 != r1) goto L_0x0018
            int[] r0 = r3.words
            r1 = 0
            r0 = r0[r1]
            r3.ival = r0
        L_0x0018:
            r0 = 0
            r3.words = r0
        L_0x001b:
            int[] r0 = r3.words
            if (r0 != 0) goto L_0x002f
            int r0 = r3.ival
            r1 = -100
            if (r0 < r1) goto L_0x002f
            r2 = 1024(0x400, float:1.435E-42)
            if (r0 > r2) goto L_0x002f
            gnu.math.IntNum[] r2 = smallFixNums
            int r0 = r0 - r1
            r0 = r2[r0]
            return r0
        L_0x002f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.IntNum.canonicalize():gnu.math.IntNum");
    }

    public static final IntNum add(int i, int i2) {
        return make(((long) i) + ((long) i2));
    }

    public static IntNum add(IntNum intNum, int i) {
        if (intNum.words == null) {
            return add(intNum.ival, i);
        }
        IntNum intNum2 = new IntNum(0);
        intNum2.setAdd(intNum, i);
        return intNum2.canonicalize();
    }

    public void setAdd(IntNum intNum, int i) {
        if (intNum.words == null) {
            set(((long) intNum.ival) + ((long) i));
            return;
        }
        int i2 = intNum.ival;
        int i3 = i2 + 1;
        realloc(i3);
        long j = (long) i;
        for (int i4 = 0; i4 < i2; i4++) {
            long j2 = j + (((long) intNum.words[i4]) & InternalZipConstants.ZIP_64_LIMIT);
            this.words[i4] = (int) j2;
            j = j2 >> 32;
        }
        if (intNum.words[i2 - 1] < 0) {
            j--;
        }
        int[] iArr = this.words;
        iArr[i2] = (int) j;
        this.ival = wordsNeeded(iArr, i3);
    }

    public final void setAdd(int i) {
        setAdd(this, i);
    }

    public final void set(int i) {
        this.words = null;
        this.ival = i;
    }

    public final void set(long j) {
        int i = (int) j;
        if (((long) i) == j) {
            this.ival = i;
            this.words = null;
            return;
        }
        realloc(2);
        int[] iArr = this.words;
        iArr[0] = i;
        iArr[1] = (int) (j >> 32);
        this.ival = 2;
    }

    public final void set(int[] iArr, int i) {
        this.ival = i;
        this.words = iArr;
    }

    public final void set(IntNum intNum) {
        if (intNum.words == null) {
            set(intNum.ival);
        } else if (this != intNum) {
            realloc(intNum.ival);
            System.arraycopy(intNum.words, 0, this.words, 0, intNum.ival);
            this.ival = intNum.ival;
        }
    }

    public static IntNum add(IntNum intNum, IntNum intNum2) {
        return add(intNum, intNum2, 1);
    }

    public static IntNum sub(IntNum intNum, IntNum intNum2) {
        return add(intNum, intNum2, -1);
    }

    public static IntNum add(IntNum intNum, IntNum intNum2, int i) {
        if (intNum.words == null && intNum2.words == null) {
            return make((((long) i) * ((long) intNum2.ival)) + ((long) intNum.ival));
        }
        if (i != 1) {
            if (i == -1) {
                intNum2 = neg(intNum2);
            } else {
                intNum2 = times(intNum2, make(i));
            }
        }
        if (intNum.words == null) {
            return add(intNum2, intNum.ival);
        }
        if (intNum2.words == null) {
            return add(intNum, intNum2.ival);
        }
        if (intNum2.ival > intNum.ival) {
            IntNum intNum3 = intNum2;
            intNum2 = intNum;
            intNum = intNum3;
        }
        IntNum alloc = alloc(intNum.ival + 1);
        int i2 = intNum2.ival;
        long add_n = (long) MPN.add_n(alloc.words, intNum.words, intNum2.words, i2);
        long j = intNum2.words[i2 + -1] < 0 ? 4294967295L : 0;
        while (i2 < intNum.ival) {
            long j2 = add_n + (((long) intNum.words[i2]) & InternalZipConstants.ZIP_64_LIMIT) + j;
            alloc.words[i2] = (int) j2;
            add_n = j2 >>> 32;
            i2++;
        }
        if (intNum.words[i2 - 1] < 0) {
            j--;
        }
        alloc.words[i2] = (int) (add_n + j);
        alloc.ival = i2 + 1;
        return alloc.canonicalize();
    }

    public static final IntNum times(int i, int i2) {
        return make(((long) i) * ((long) i2));
    }

    public static final IntNum times(IntNum intNum, int i) {
        if (i == 0) {
            return zero();
        }
        boolean z = true;
        if (i == 1) {
            return intNum;
        }
        int[] iArr = intNum.words;
        int i2 = intNum.ival;
        if (iArr == null) {
            return make(((long) i2) * ((long) i));
        }
        int i3 = i2 + 1;
        IntNum alloc = alloc(i3);
        if (iArr[i2 - 1] < 0) {
            negate(alloc.words, iArr, i2);
            iArr = alloc.words;
        } else {
            z = false;
        }
        if (i < 0) {
            z = !z;
            i = -i;
        }
        int[] iArr2 = alloc.words;
        iArr2[i2] = MPN.mul_1(iArr2, iArr, i2, i);
        alloc.ival = i3;
        if (z) {
            alloc.setNegative();
        }
        return alloc.canonicalize();
    }

    public static final IntNum times(IntNum intNum, IntNum intNum2) {
        boolean z;
        int[] iArr;
        int[] iArr2;
        if (intNum2.words == null) {
            return times(intNum, intNum2.ival);
        }
        if (intNum.words == null) {
            return times(intNum2, intNum.ival);
        }
        int i = intNum.ival;
        int i2 = intNum2.ival;
        if (intNum.isNegative()) {
            iArr = new int[i];
            negate(iArr, intNum.words, i);
            z = true;
        } else {
            iArr = intNum.words;
            z = false;
        }
        if (intNum2.isNegative()) {
            z = !z;
            iArr2 = new int[i2];
            negate(iArr2, intNum2.words, i2);
        } else {
            iArr2 = intNum2.words;
        }
        if (i < i2) {
            int i3 = i2;
            i2 = i;
            i = i3;
            int[] iArr3 = iArr2;
            iArr2 = iArr;
            iArr = iArr3;
        }
        int i4 = i + i2;
        IntNum alloc = alloc(i4);
        MPN.mul(alloc.words, iArr, i, iArr2, i2);
        alloc.ival = i4;
        if (z) {
            alloc.setNegative();
        }
        return alloc.canonicalize();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0074, code lost:
        if (r0 <= ((r2 - (r15 & 1)) >> 1)) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007c, code lost:
        if (r11 == (r10 == 1)) goto L_0x0080;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void divide(long r19, long r21, gnu.math.IntNum r23, gnu.math.IntNum r24, int r25) {
        /*
            r0 = r19
            r2 = r21
            r4 = r23
            r5 = r24
            r6 = 2
            r7 = 0
            r9 = 1
            r10 = 5
            r11 = r25
            if (r11 != r10) goto L_0x0019
            int r10 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r10 >= 0) goto L_0x0017
            r10 = 2
            goto L_0x001a
        L_0x0017:
            r10 = 1
            goto L_0x001a
        L_0x0019:
            r10 = r11
        L_0x001a:
            r11 = -9223372036854775808
            r13 = 0
            int r14 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r14 >= 0) goto L_0x0034
            int r14 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r14 != 0) goto L_0x0031
            gnu.math.IntNum r0 = make((long) r19)
            gnu.math.IntNum r1 = make((long) r21)
            divide((gnu.math.IntNum) r0, (gnu.math.IntNum) r1, (gnu.math.IntNum) r4, (gnu.math.IntNum) r5, (int) r10)
            return
        L_0x0031:
            long r0 = -r0
            r14 = 1
            goto L_0x0035
        L_0x0034:
            r14 = 0
        L_0x0035:
            int r15 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r15 >= 0) goto L_0x005a
            int r15 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r15 != 0) goto L_0x0057
            r6 = 3
            if (r10 != r6) goto L_0x004b
            if (r4 == 0) goto L_0x0045
            r4.set((int) r13)
        L_0x0045:
            if (r5 == 0) goto L_0x0056
            r5.set((long) r0)
            goto L_0x0056
        L_0x004b:
            gnu.math.IntNum r0 = make((long) r0)
            gnu.math.IntNum r1 = make((long) r21)
            divide((gnu.math.IntNum) r0, (gnu.math.IntNum) r1, (gnu.math.IntNum) r4, (gnu.math.IntNum) r5, (int) r10)
        L_0x0056:
            return
        L_0x0057:
            long r2 = -r2
            r11 = 1
            goto L_0x005b
        L_0x005a:
            r11 = 0
        L_0x005b:
            long r15 = r0 / r2
            long r0 = r0 % r2
            r11 = r11 ^ r14
            r17 = 1
            int r12 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x007f
            if (r10 == r9) goto L_0x0077
            if (r10 == r6) goto L_0x0077
            r6 = 4
            if (r10 == r6) goto L_0x006d
            goto L_0x007f
        L_0x006d:
            long r6 = r15 & r17
            long r6 = r2 - r6
            long r6 = r6 >> r9
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x007f
            goto L_0x0080
        L_0x0077:
            if (r10 != r9) goto L_0x007b
            r6 = 1
            goto L_0x007c
        L_0x007b:
            r6 = 0
        L_0x007c:
            if (r11 != r6) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            r9 = 0
        L_0x0080:
            if (r4 == 0) goto L_0x008d
            if (r9 == 0) goto L_0x0086
            long r15 = r15 + r17
        L_0x0086:
            r6 = r15
            if (r11 == 0) goto L_0x008a
            long r6 = -r6
        L_0x008a:
            r4.set((long) r6)
        L_0x008d:
            if (r5 == 0) goto L_0x009b
            if (r9 == 0) goto L_0x0095
            long r0 = r2 - r0
            r14 = r14 ^ 1
        L_0x0095:
            if (r14 == 0) goto L_0x0098
            long r0 = -r0
        L_0x0098:
            r5.set((long) r0)
        L_0x009b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.IntNum.divide(long, long, gnu.math.IntNum, gnu.math.IntNum, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:81:0x011e, code lost:
        if ((r12[0] & 1) == 0) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0126, code lost:
        if (r7 == (r14 == 1)) goto L_0x0128;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x012d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void divide(gnu.math.IntNum r17, gnu.math.IntNum r18, gnu.math.IntNum r19, gnu.math.IntNum r20, int r21) {
        /*
            r0 = r17
            r1 = r18
            r4 = r19
            r5 = r20
            int[] r2 = r0.words
            r3 = 2
            if (r2 == 0) goto L_0x0011
            int r2 = r0.ival
            if (r2 > r3) goto L_0x0037
        L_0x0011:
            int[] r2 = r1.words
            if (r2 == 0) goto L_0x0019
            int r2 = r1.ival
            if (r2 > r3) goto L_0x0037
        L_0x0019:
            long r6 = r17.longValue()
            long r8 = r18.longValue()
            r10 = -9223372036854775808
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0037
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0037
            r0 = r6
            r2 = r8
            r4 = r19
            r5 = r20
            r6 = r21
            divide((long) r0, (long) r2, (gnu.math.IntNum) r4, (gnu.math.IntNum) r5, (int) r6)
            return
        L_0x0037:
            boolean r2 = r17.isNegative()
            boolean r6 = r18.isNegative()
            r7 = r2 ^ r6
            int[] r8 = r1.words
            r9 = 1
            if (r8 != 0) goto L_0x0048
            r8 = 1
            goto L_0x004a
        L_0x0048:
            int r8 = r1.ival
        L_0x004a:
            int[] r10 = new int[r8]
            r1.getAbsolute(r10)
        L_0x004f:
            if (r8 <= r9) goto L_0x005a
            int r11 = r8 + -1
            r11 = r10[r11]
            if (r11 != 0) goto L_0x005a
            int r8 = r8 + -1
            goto L_0x004f
        L_0x005a:
            int[] r11 = r0.words
            if (r11 != 0) goto L_0x0060
            r11 = 1
            goto L_0x0062
        L_0x0060:
            int r11 = r0.ival
        L_0x0062:
            int r12 = r11 + 2
            int[] r12 = new int[r12]
            r0.getAbsolute(r12)
        L_0x0069:
            if (r11 <= r9) goto L_0x0074
            int r0 = r11 + -1
            r0 = r12[r0]
            if (r0 != 0) goto L_0x0074
            int r11 = r11 + -1
            goto L_0x0069
        L_0x0074:
            int r0 = gnu.math.MPN.cmp(r12, r11, r10, r8)
            r13 = 0
            if (r0 >= 0) goto L_0x0085
            r10[r13] = r13
            r8 = r11
            r11 = 1
            r16 = r12
            r12 = r10
            r10 = r16
            goto L_0x00cd
        L_0x0085:
            if (r0 != 0) goto L_0x008e
            r12[r13] = r9
            r10[r13] = r13
            r8 = 1
            r11 = 1
            goto L_0x00cd
        L_0x008e:
            if (r8 != r9) goto L_0x009a
            r0 = r10[r13]
            int r0 = gnu.math.MPN.divmod_1(r12, r12, r11, r0)
            r10[r13] = r0
            r8 = 1
            goto L_0x00cd
        L_0x009a:
            int r0 = r8 + -1
            r0 = r10[r0]
            int r0 = gnu.math.MPN.count_leading_zeros(r0)
            if (r0 == 0) goto L_0x00b0
            gnu.math.MPN.lshift(r10, r13, r10, r8, r0)
            int r14 = gnu.math.MPN.lshift(r12, r13, r12, r11, r0)
            int r15 = r11 + 1
            r12[r11] = r14
            r11 = r15
        L_0x00b0:
            if (r11 != r8) goto L_0x00b7
            int r14 = r11 + 1
            r12[r11] = r13
            r11 = r14
        L_0x00b7:
            gnu.math.MPN.divide(r12, r11, r10, r8)
            gnu.math.MPN.rshift0(r10, r12, r13, r8, r0)
            int r11 = r11 + r9
            int r11 = r11 - r8
            if (r4 == 0) goto L_0x00cd
            r0 = 0
        L_0x00c2:
            if (r0 >= r11) goto L_0x00cd
            int r14 = r0 + r8
            r14 = r12[r14]
            r12[r0] = r14
            int r0 = r0 + 1
            goto L_0x00c2
        L_0x00cd:
            if (r8 <= r9) goto L_0x00d8
            int r0 = r8 + -1
            r0 = r10[r0]
            if (r0 != 0) goto L_0x00d8
            int r8 = r8 + -1
            goto L_0x00cd
        L_0x00d8:
            int r0 = r8 + -1
            r0 = r10[r0]
            if (r0 >= 0) goto L_0x00e2
            r10[r8] = r13
            int r8 = r8 + 1
        L_0x00e2:
            if (r8 > r9) goto L_0x00e8
            r0 = r10[r13]
            if (r0 == 0) goto L_0x012a
        L_0x00e8:
            r0 = 5
            r14 = r21
            if (r14 != r0) goto L_0x00f3
            if (r6 == 0) goto L_0x00f1
            r0 = 2
            goto L_0x00f2
        L_0x00f1:
            r0 = 1
        L_0x00f2:
            r14 = r0
        L_0x00f3:
            if (r14 == r9) goto L_0x0121
            if (r14 == r3) goto L_0x0121
            r0 = 4
            if (r14 == r0) goto L_0x00fb
            goto L_0x012a
        L_0x00fb:
            if (r5 != 0) goto L_0x0103
            gnu.math.IntNum r0 = new gnu.math.IntNum
            r0.<init>()
            goto L_0x0104
        L_0x0103:
            r0 = r5
        L_0x0104:
            r0.set(r10, r8)
            gnu.math.IntNum r0 = shift((gnu.math.IntNum) r0, (int) r9)
            if (r6 == 0) goto L_0x0110
            r0.setNegative()
        L_0x0110:
            int r0 = compare((gnu.math.IntNum) r0, (gnu.math.IntNum) r1)
            if (r6 == 0) goto L_0x0117
            int r0 = -r0
        L_0x0117:
            if (r0 == r9) goto L_0x0128
            if (r0 != 0) goto L_0x012a
            r0 = r12[r13]
            r0 = r0 & r9
            if (r0 == 0) goto L_0x012a
            goto L_0x0128
        L_0x0121:
            if (r14 != r9) goto L_0x0125
            r0 = 1
            goto L_0x0126
        L_0x0125:
            r0 = 0
        L_0x0126:
            if (r7 != r0) goto L_0x012a
        L_0x0128:
            r0 = 1
            goto L_0x012b
        L_0x012a:
            r0 = 0
        L_0x012b:
            if (r4 == 0) goto L_0x014b
            int r3 = r11 + -1
            r3 = r12[r3]
            if (r3 >= 0) goto L_0x0137
            r12[r11] = r13
            int r11 = r11 + 1
        L_0x0137:
            r4.set(r12, r11)
            if (r7 == 0) goto L_0x0146
            if (r0 == 0) goto L_0x0142
            r19.setInvert()
            goto L_0x014b
        L_0x0142:
            r19.setNegative()
            goto L_0x014b
        L_0x0146:
            if (r0 == 0) goto L_0x014b
            r4.setAdd(r9)
        L_0x014b:
            if (r5 == 0) goto L_0x017f
            r5.set(r10, r8)
            if (r0 == 0) goto L_0x017a
            int[] r0 = r1.words
            if (r0 != 0) goto L_0x0168
            if (r6 == 0) goto L_0x015e
            r0 = r10[r13]
            int r1 = r1.ival
            int r0 = r0 + r1
            goto L_0x0163
        L_0x015e:
            r0 = r10[r13]
            int r1 = r1.ival
            int r0 = r0 - r1
        L_0x0163:
            r5.set((int) r0)
            r0 = r5
            goto L_0x0170
        L_0x0168:
            if (r6 == 0) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r9 = -1
        L_0x016c:
            gnu.math.IntNum r0 = add(r5, r1, r9)
        L_0x0170:
            if (r2 == 0) goto L_0x0176
            r5.setNegative(r0)
            goto L_0x017f
        L_0x0176:
            r5.set((gnu.math.IntNum) r0)
            goto L_0x017f
        L_0x017a:
            if (r2 == 0) goto L_0x017f
            r20.setNegative()
        L_0x017f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.IntNum.divide(gnu.math.IntNum, gnu.math.IntNum, gnu.math.IntNum, gnu.math.IntNum, int):void");
    }

    public static IntNum quotient(IntNum intNum, IntNum intNum2, int i) {
        IntNum intNum3 = new IntNum();
        divide(intNum, intNum2, intNum3, (IntNum) null, i);
        return intNum3.canonicalize();
    }

    public static IntNum quotient(IntNum intNum, IntNum intNum2) {
        return quotient(intNum, intNum2, 3);
    }

    public static IntNum remainder(IntNum intNum, IntNum intNum2, int i) {
        if (intNum2.isZero()) {
            return intNum;
        }
        IntNum intNum3 = new IntNum();
        divide(intNum, intNum2, (IntNum) null, intNum3, i);
        return intNum3.canonicalize();
    }

    public static IntNum remainder(IntNum intNum, IntNum intNum2) {
        return remainder(intNum, intNum2, 3);
    }

    public static IntNum modulo(IntNum intNum, IntNum intNum2) {
        return remainder(intNum, intNum2, 1);
    }

    public Numeric power(IntNum intNum) {
        int i;
        if (isOne()) {
            return this;
        }
        if (isMinusOne()) {
            return intNum.isOdd() ? this : one();
        }
        if (intNum.words == null && (i = intNum.ival) >= 0) {
            return power(this, i);
        }
        if (isZero()) {
            return intNum.isNegative() ? RatNum.infinity(-1) : this;
        }
        return super.power(intNum);
    }

    public static IntNum power(IntNum intNum, int i) {
        if (i <= 0) {
            if (i == 0) {
                return one();
            }
            throw new Error("negative exponent");
        } else if (intNum.isZero()) {
            return intNum;
        } else {
            int i2 = 1;
            int i3 = intNum.words == null ? 1 : intNum.ival;
            int intLength = ((intNum.intLength() * i) >> 5) + (i3 * 2);
            boolean z = intNum.isNegative() && (i & 1) != 0;
            int[] iArr = new int[intLength];
            int[] iArr2 = new int[intLength];
            int[] iArr3 = new int[intLength];
            intNum.getAbsolute(iArr);
            iArr2[0] = 1;
            while (true) {
                if ((i & 1) != 0) {
                    MPN.mul(iArr3, iArr, i3, iArr2, i2);
                    i2 += i3;
                    while (iArr3[i2 - 1] == 0) {
                        i2--;
                    }
                    int[] iArr4 = iArr2;
                    iArr2 = iArr3;
                    iArr3 = iArr4;
                }
                i >>= 1;
                if (i == 0) {
                    break;
                }
                MPN.mul(iArr3, iArr, i3, iArr, i3);
                int i4 = i3 * 2;
                while (iArr3[i3 - 1] == 0) {
                    i4 = i3 - 1;
                }
                int[] iArr5 = iArr;
                iArr = iArr3;
                iArr3 = iArr5;
            }
            if (iArr2[i2 - 1] < 0) {
                i2++;
            }
            if (z) {
                negate(iArr2, iArr2, i2);
            }
            return make(iArr2, i2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:0:0x0000, code lost:
        if (r3 > r2) goto L_0x0006;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0003, code lost:
        r1 = r3;
        r3 = r2;
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        if (r2 != 0) goto L_0x0009;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r2 != 1) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000d, code lost:
        r3 = r3 % r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int gcd(int r2, int r3) {
        /*
            if (r3 <= r2) goto L_0x0003
            goto L_0x0006
        L_0x0003:
            r1 = r3
            r3 = r2
            r2 = r1
        L_0x0006:
            if (r2 != 0) goto L_0x0009
            return r3
        L_0x0009:
            r0 = 1
            if (r2 != r0) goto L_0x000d
            return r2
        L_0x000d:
            int r3 = r3 % r2
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.IntNum.gcd(int, int):int");
    }

    public static IntNum gcd(IntNum intNum, IntNum intNum2) {
        int i = intNum.ival;
        int i2 = intNum2.ival;
        if (intNum.words == null) {
            if (i == 0) {
                return abs(intNum2);
            }
            if (intNum2.words != null || i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                i = 1;
            } else {
                if (i < 0) {
                    i = -i;
                }
                if (i2 < 0) {
                    i2 = -i2;
                }
                return make(gcd(i, i2));
            }
        }
        if (intNum2.words == null) {
            if (i2 == 0) {
                return abs(intNum);
            }
            i2 = 1;
        }
        if (i <= i2) {
            i = i2;
        }
        int[] iArr = new int[i];
        int[] iArr2 = new int[i];
        intNum.getAbsolute(iArr);
        intNum2.getAbsolute(iArr2);
        int gcd = MPN.gcd(iArr, iArr2, i);
        IntNum intNum3 = new IntNum(0);
        if (iArr[gcd - 1] < 0) {
            iArr[gcd] = 0;
            gcd++;
        }
        intNum3.ival = gcd;
        intNum3.words = iArr;
        return intNum3.canonicalize();
    }

    public static IntNum lcm(IntNum intNum, IntNum intNum2) {
        if (intNum.isZero() || intNum2.isZero()) {
            return zero();
        }
        IntNum abs = abs(intNum);
        IntNum abs2 = abs(intNum2);
        IntNum intNum3 = new IntNum();
        divide(times(abs, abs2), gcd(abs, abs2), intNum3, (IntNum) null, 3);
        return intNum3.canonicalize();
    }

    /* access modifiers changed from: package-private */
    public void setInvert() {
        if (this.words == null) {
            this.ival = ~this.ival;
            return;
        }
        int i = this.ival;
        while (true) {
            i--;
            if (i >= 0) {
                int[] iArr = this.words;
                iArr[i] = ~iArr[i];
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setShiftLeft(IntNum intNum, int i) {
        int[] iArr = intNum.words;
        int i2 = 1;
        if (iArr != null) {
            i2 = intNum.ival;
        } else if (i < 32) {
            set(((long) intNum.ival) << i);
            return;
        } else {
            iArr = new int[]{intNum.ival};
        }
        int i3 = i >> 5;
        int i4 = i & 31;
        int i5 = i2 + i3;
        if (i4 == 0) {
            realloc(i5);
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                this.words[i2 + i3] = iArr[i2];
            }
        } else {
            i5++;
            realloc(i5);
            int i6 = 32 - i4;
            this.words[i5 - 1] = (MPN.lshift(this.words, i3, iArr, i2, i4) << i6) >> i6;
        }
        this.ival = i5;
        while (true) {
            i3--;
            if (i3 >= 0) {
                this.words[i3] = 0;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setShiftRight(IntNum intNum, int i) {
        int i2 = -1;
        if (intNum.words == null) {
            int i3 = intNum.ival;
            if (i < 32) {
                i2 = i3 >> i;
            } else if (i3 >= 0) {
                i2 = 0;
            }
            set(i2);
        } else if (i == 0) {
            set(intNum);
        } else {
            boolean isNegative = intNum.isNegative();
            int i4 = i >> 5;
            int i5 = i & 31;
            int i6 = intNum.ival - i4;
            if (i6 <= 0) {
                if (!isNegative) {
                    i2 = 0;
                }
                set(i2);
                return;
            }
            int[] iArr = this.words;
            if (iArr == null || iArr.length < i6) {
                realloc(i6);
            }
            MPN.rshift0(this.words, intNum.words, i4, i6, i5);
            this.ival = i6;
            if (isNegative) {
                int[] iArr2 = this.words;
                int i7 = i6 - 1;
                iArr2[i7] = (-2 << (31 - i5)) | iArr2[i7];
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setShift(IntNum intNum, int i) {
        if (i > 0) {
            setShiftLeft(intNum, i);
        } else {
            setShiftRight(intNum, -i);
        }
    }

    public static IntNum shift(IntNum intNum, int i) {
        int i2 = 0;
        if (intNum.words == null) {
            if (i <= 0) {
                int i3 = intNum.ival;
                if (i > -32) {
                    i2 = i3 >> (-i);
                } else if (i3 < 0) {
                    i2 = -1;
                }
                return make(i2);
            } else if (i < 32) {
                return make(((long) intNum.ival) << i);
            }
        }
        if (i == 0) {
            return intNum;
        }
        IntNum intNum2 = new IntNum(0);
        intNum2.setShift(intNum, i);
        return intNum2.canonicalize();
    }

    public void format(int i, StringBuffer stringBuffer) {
        if (i == 10) {
            if (this.words == null) {
                stringBuffer.append(this.ival);
                return;
            } else if (this.ival <= 2) {
                stringBuffer.append(longValue());
                return;
            }
        }
        stringBuffer.append(toString(i));
    }

    public void format(int i, StringBuilder sb) {
        int[] iArr;
        int i2;
        if (this.words == null) {
            if (i == 10) {
                sb.append(this.ival);
            } else {
                sb.append(Integer.toString(this.ival, i));
            }
        } else if (this.ival <= 2) {
            long longValue = longValue();
            if (i == 10) {
                sb.append(longValue);
            } else {
                sb.append(Long.toString(longValue, i));
            }
        } else {
            boolean isNegative = isNegative();
            if (isNegative || i != 16) {
                iArr = new int[this.ival];
                getAbsolute(iArr);
            } else {
                iArr = this.words;
            }
            int i3 = this.ival;
            if (i == 16) {
                if (isNegative) {
                    sb.append('-');
                }
                int length = sb.length();
                while (true) {
                    i3--;
                    if (i3 >= 0) {
                        int i4 = iArr[i3];
                        int i5 = 8;
                        while (true) {
                            i5--;
                            if (i5 >= 0) {
                                int i6 = (i4 >> (i5 * 4)) & 15;
                                if (i6 > 0 || sb.length() > length) {
                                    sb.append(Character.forDigit(i6, 16));
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            } else {
                int chars_per_word = MPN.chars_per_word(i);
                int i7 = i;
                int i8 = chars_per_word;
                while (true) {
                    i8--;
                    if (i8 <= 0) {
                        break;
                    }
                    i7 *= i;
                }
                int length2 = sb.length();
                do {
                    int divmod_1 = MPN.divmod_1(iArr, iArr, i3, i7);
                    while (i3 > 0 && iArr[i3 - 1] == 0) {
                        i3--;
                    }
                    int i9 = chars_per_word;
                    while (true) {
                        i9--;
                        if (i9 >= 0 && (i3 != 0 || divmod_1 != 0)) {
                            if (divmod_1 < 0) {
                                i2 = (int) ((((long) divmod_1) & -1) % ((long) i));
                            } else {
                                i2 = divmod_1 % i;
                            }
                            divmod_1 /= i;
                            sb.append(Character.forDigit(i2, i));
                        }
                    }
                } while (i3 != 0);
                if (isNegative) {
                    sb.append('-');
                }
                for (int length3 = sb.length() - 1; length2 < length3; length3--) {
                    char charAt = sb.charAt(length2);
                    sb.setCharAt(length2, sb.charAt(length3));
                    sb.setCharAt(length3, charAt);
                    length2++;
                }
            }
        }
    }

    public String toString(int i) {
        if (this.words == null) {
            return Integer.toString(this.ival, i);
        }
        int i2 = this.ival;
        if (i2 <= 2) {
            return Long.toString(longValue(), i);
        }
        StringBuilder sb = new StringBuilder(i2 * (MPN.chars_per_word(i) + 1));
        format(i, sb);
        return sb.toString();
    }

    public int intValue() {
        int[] iArr = this.words;
        if (iArr == null) {
            return this.ival;
        }
        return iArr[0];
    }

    public static int intValue(Object obj) {
        IntNum intNum = (IntNum) obj;
        if (intNum.words == null) {
            return intNum.ival;
        }
        throw new ClassCastException("integer too large");
    }

    public long longValue() {
        int i;
        int[] iArr = this.words;
        if (iArr == null) {
            i = this.ival;
        } else if (this.ival != 1) {
            return (((long) iArr[1]) << 32) + (((long) iArr[0]) & InternalZipConstants.ZIP_64_LIMIT);
        } else {
            i = iArr[0];
        }
        return (long) i;
    }

    public int hashCode() {
        int[] iArr = this.words;
        if (iArr == null) {
            return this.ival;
        }
        return iArr[this.ival - 1] + iArr[0];
    }

    public static boolean equals(IntNum intNum, IntNum intNum2) {
        int i;
        int[] iArr = intNum.words;
        if (iArr == null && intNum2.words == null) {
            if (intNum.ival == intNum2.ival) {
                return true;
            }
            return false;
        } else if (iArr == null || intNum2.words == null || (i = intNum.ival) != intNum2.ival) {
            return false;
        } else {
            do {
                i--;
                if (i < 0) {
                    return true;
                }
            } while (intNum.words[i] == intNum2.words[i]);
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntNum)) {
            return false;
        }
        return equals(this, (IntNum) obj);
    }

    public static IntNum valueOf(char[] cArr, int i, int i2, int i3, boolean z) {
        byte[] bArr = new byte[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            char c = cArr[i + i5];
            if (c == '-') {
                z = true;
            } else if (!(c == '_' || (i4 == 0 && (c == ' ' || c == 9)))) {
                int digit = Character.digit(c, i3);
                if (digit < 0) {
                    break;
                }
                bArr[i4] = (byte) digit;
                i4++;
            }
        }
        return valueOf(bArr, i4, z, i3);
    }

    public static IntNum valueOf(String str, int i) throws NumberFormatException {
        int length = str.length();
        if (length + i <= 28) {
            if (length > 1 && str.charAt(0) == '+' && Character.digit(str.charAt(1), i) >= 0) {
                str = str.substring(1);
            }
            return make(Long.parseLong(str, i));
        }
        byte[] bArr = new byte[length];
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '-' && i3 == 0) {
                z = true;
            } else if (!((charAt == '+' && i3 == 0) || charAt == '_' || (i2 == 0 && (charAt == ' ' || charAt == 9)))) {
                int digit = Character.digit(charAt, i);
                if (digit >= 0) {
                    bArr[i2] = (byte) digit;
                    i2++;
                } else {
                    throw new NumberFormatException("For input string: \"" + str + '\"');
                }
            }
        }
        return valueOf(bArr, i2, z, i);
    }

    public static IntNum valueOf(byte[] bArr, int i, boolean z, int i2) {
        int[] iArr = new int[((i / MPN.chars_per_word(i2)) + 1)];
        int i3 = MPN.set_str(iArr, bArr, i, i2);
        if (i3 == 0) {
            return zero();
        }
        if (iArr[i3 - 1] < 0) {
            iArr[i3] = 0;
            i3++;
        }
        if (z) {
            negate(iArr, iArr, i3);
        }
        return make(iArr, i3);
    }

    public static IntNum valueOf(String str) throws NumberFormatException {
        return valueOf(str, 10);
    }

    public double doubleValue() {
        if (this.words == null) {
            return (double) this.ival;
        }
        if (this.ival <= 2) {
            return (double) longValue();
        }
        if (isNegative()) {
            return neg(this).roundToDouble(0, true, false);
        }
        return roundToDouble(0, false, false);
    }

    /* access modifiers changed from: package-private */
    public boolean checkBits(int i) {
        if (i <= 0) {
            return false;
        }
        if (this.words == null) {
            if (i <= 31) {
                if ((((1 << i) - 1) & this.ival) != 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        int i2 = 0;
        while (i2 < (i >> 5)) {
            if (this.words[i2] != 0) {
                return true;
            }
            i2++;
        }
        int i3 = i & 31;
        if (i3 == 0) {
            return false;
        }
        if ((((1 << i3) - 1) & this.words[i2]) != 0) {
            return true;
        }
        return false;
    }

    public double roundToDouble(int i, boolean z, boolean z2) {
        long j;
        int intLength = intLength();
        int i2 = i + (intLength - 1);
        if (i2 < -1075) {
            return z ? -0.0d : 0.0d;
        }
        if (i2 > 1023) {
            return z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        int i3 = i2 >= -1022 ? 53 : i2 + 53 + 1022;
        int i4 = intLength - (i3 + 1);
        if (i4 > 0) {
            int[] iArr = this.words;
            j = iArr == null ? (long) (this.ival >> i4) : MPN.rshift_long(iArr, this.ival, i4);
        } else {
            j = longValue() << (-i4);
        }
        if (i2 != 1023 || (j >> 1) != 9007199254740991L) {
            long j2 = 0;
            if ((j & 1) == 1 && ((j & 2) == 2 || z2 || checkBits(i4))) {
                j += 2;
                if ((18014398509481984L & j) != 0) {
                    i2++;
                    j >>= 1;
                } else if (i3 == 52 && (9007199254740992L & j) != 0) {
                    i2++;
                }
            }
            long j3 = j >> 1;
            long j4 = z ? Long.MIN_VALUE : 0;
            int i5 = i2 + 1023;
            if (i5 > 0) {
                j2 = ((long) i5) << 52;
            }
            return Double.longBitsToDouble((-4503599627370497L & j3) | j4 | j2);
        } else if (!z2 && !checkBits(intLength - i3)) {
            return z ? -1.7976931348623157E308d : Double.MAX_VALUE;
        } else {
            if (z) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof IntNum) {
            return add(this, (IntNum) obj, i);
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).addReversed(this, i);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object obj) {
        if (obj instanceof IntNum) {
            return times(this, (IntNum) obj);
        }
        if (obj instanceof Numeric) {
            return ((Numeric) obj).mulReversed(this);
        }
        throw new IllegalArgumentException();
    }

    public Numeric div(Object obj) {
        if (obj instanceof RatNum) {
            RatNum ratNum = (RatNum) obj;
            return RatNum.make(times(this, ratNum.denominator()), ratNum.numerator());
        } else if (obj instanceof Numeric) {
            return ((Numeric) obj).divReversed(this);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getAbsolute(int[] iArr) {
        int i;
        if (this.words != null) {
            i = this.ival;
            int i2 = i;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                iArr[i2] = this.words[i2];
            }
        } else {
            iArr[0] = this.ival;
            i = 1;
        }
        if (iArr[i - 1] < 0) {
            negate(iArr, iArr, i);
        }
        int length = iArr.length;
        while (true) {
            length--;
            if (length > i) {
                iArr[length] = 0;
            } else {
                return;
            }
        }
    }

    public static boolean negate(int[] iArr, int[] iArr2, int i) {
        int i2 = i - 1;
        boolean z = iArr2[i2] < 0;
        long j = 1;
        for (int i3 = 0; i3 < i; i3++) {
            long j2 = j + (((long) (~iArr2[i3])) & InternalZipConstants.ZIP_64_LIMIT);
            iArr[i3] = (int) j2;
            j = j2 >> 32;
        }
        if (!z || iArr[i2] >= 0) {
            return false;
        }
        return true;
    }

    public void setNegative(IntNum intNum) {
        int i = intNum.ival;
        if (intNum.words != null) {
            int i2 = i + 1;
            realloc(i2);
            if (negate(this.words, intNum.words, i)) {
                this.words[i] = 0;
                i = i2;
            }
            this.ival = i;
        } else if (i == Integer.MIN_VALUE) {
            set(-((long) i));
        } else {
            set(-i);
        }
    }

    public final void setNegative() {
        setNegative(this);
    }

    public static IntNum abs(IntNum intNum) {
        return intNum.isNegative() ? neg(intNum) : intNum;
    }

    public static IntNum neg(IntNum intNum) {
        int i;
        if (intNum.words == null && (i = intNum.ival) != Integer.MIN_VALUE) {
            return make(-i);
        }
        IntNum intNum2 = new IntNum(0);
        intNum2.setNegative(intNum);
        return intNum2.canonicalize();
    }

    public Numeric neg() {
        return neg(this);
    }

    public int intLength() {
        int[] iArr = this.words;
        if (iArr == null) {
            return MPN.intLength(this.ival);
        }
        return MPN.intLength(iArr, this.ival);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int[] iArr = this.words;
        int wordsNeeded = iArr == null ? 1 : wordsNeeded(iArr, this.ival);
        if (wordsNeeded <= 1) {
            int[] iArr2 = this.words;
            int i = 0;
            if (iArr2 == null) {
                i = this.ival;
            } else if (iArr2.length != 0) {
                i = iArr2[0];
            }
            if (i >= -1073741824) {
                objectOutput.writeInt(i);
                return;
            }
            objectOutput.writeInt(-2147483647);
            objectOutput.writeInt(i);
            return;
        }
        objectOutput.writeInt(Integer.MIN_VALUE | wordsNeeded);
        while (true) {
            wordsNeeded--;
            if (wordsNeeded >= 0) {
                objectOutput.writeInt(this.words[wordsNeeded]);
            } else {
                return;
            }
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        if (readInt <= -1073741824) {
            readInt &= ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            if (readInt == 1) {
                readInt = objectInput.readInt();
            } else {
                int[] iArr = new int[readInt];
                int i = readInt;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    iArr[i] = objectInput.readInt();
                }
                this.words = iArr;
            }
        }
        this.ival = readInt;
    }

    public Object readResolve() throws ObjectStreamException {
        return canonicalize();
    }

    public BigInteger asBigInteger() {
        if (this.words == null || this.ival <= 2) {
            return BigInteger.valueOf(longValue());
        }
        return new BigInteger(toString());
    }

    public BigDecimal asBigDecimal() {
        if (this.words == null) {
            return new BigDecimal(this.ival);
        }
        if (this.ival <= 2) {
            return BigDecimal.valueOf(longValue());
        }
        return new BigDecimal(toString());
    }

    public boolean inRange(long j, long j2) {
        return compare(this, j) >= 0 && compare(this, j2) <= 0;
    }

    public boolean inIntRange() {
        return inRange(-2147483648L, 2147483647L);
    }

    public boolean inLongRange() {
        return inRange(Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
