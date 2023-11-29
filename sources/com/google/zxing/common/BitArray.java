package com.google.zxing.common;

public final class BitArray {
    private int[] bits;
    private int size;

    public BitArray() {
        this.size = 0;
        this.bits = new int[1];
    }

    public BitArray(int i) {
        this.size = i;
        this.bits = makeArray(i);
    }

    public int getSize() {
        return this.size;
    }

    public int getSizeInBytes() {
        return (this.size + 7) >> 3;
    }

    private void ensureCapacity(int i) {
        if (i > (this.bits.length << 5)) {
            int[] makeArray = makeArray(i);
            int[] iArr = this.bits;
            System.arraycopy(iArr, 0, makeArray, 0, iArr.length);
            this.bits = makeArray;
        }
    }

    public boolean get(int i) {
        return ((1 << (i & 31)) & this.bits[i >> 5]) != 0;
    }

    public void set(int i) {
        int[] iArr = this.bits;
        int i2 = i >> 5;
        iArr[i2] = (1 << (i & 31)) | iArr[i2];
    }

    public void flip(int i) {
        int[] iArr = this.bits;
        int i2 = i >> 5;
        iArr[i2] = (1 << (i & 31)) ^ iArr[i2];
    }

    public int getNextSet(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        int i3 = i >> 5;
        int i4 = (~((1 << (i & 31)) - 1)) & this.bits[i3];
        while (i4 == 0) {
            i3++;
            int[] iArr = this.bits;
            if (i3 == iArr.length) {
                return this.size;
            }
            i4 = iArr[i3];
        }
        int numberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.size;
        return numberOfTrailingZeros > i5 ? i5 : numberOfTrailingZeros;
    }

    public int getNextUnset(int i) {
        int i2 = this.size;
        if (i >= i2) {
            return i2;
        }
        int i3 = i >> 5;
        int i4 = (~((1 << (i & 31)) - 1)) & (~this.bits[i3]);
        while (i4 == 0) {
            i3++;
            int[] iArr = this.bits;
            if (i3 == iArr.length) {
                return this.size;
            }
            i4 = ~iArr[i3];
        }
        int numberOfTrailingZeros = (i3 << 5) + Integer.numberOfTrailingZeros(i4);
        int i5 = this.size;
        return numberOfTrailingZeros > i5 ? i5 : numberOfTrailingZeros;
    }

    public void setBulk(int i, int i2) {
        this.bits[i >> 5] = i2;
    }

    public void setRange(int i, int i2) {
        if (i2 < i) {
            throw new IllegalArgumentException();
        } else if (i2 != i) {
            int i3 = i2 - 1;
            int i4 = i >> 5;
            int i5 = i3 >> 5;
            int i6 = i4;
            while (i6 <= i5) {
                int i7 = 0;
                int i8 = i6 > i4 ? 0 : i & 31;
                int i9 = i6 < i5 ? 31 : i3 & 31;
                if (i8 == 0 && i9 == 31) {
                    i7 = -1;
                } else {
                    while (i8 <= i9) {
                        i7 |= 1 << i8;
                        i8++;
                    }
                }
                int[] iArr = this.bits;
                iArr[i6] = i7 | iArr[i6];
                i6++;
            }
        }
    }

    public void clear() {
        int length = this.bits.length;
        for (int i = 0; i < length; i++) {
            this.bits[i] = 0;
        }
    }

    public boolean isRange(int i, int i2, boolean z) {
        int i3;
        if (i2 < i) {
            throw new IllegalArgumentException();
        } else if (i2 == i) {
            return true;
        } else {
            int i4 = i2 - 1;
            int i5 = i >> 5;
            int i6 = i4 >> 5;
            int i7 = i5;
            while (i7 <= i6) {
                int i8 = i7 > i5 ? 0 : i & 31;
                int i9 = i7 < i6 ? 31 : i4 & 31;
                if (i8 == 0 && i9 == 31) {
                    i3 = -1;
                } else {
                    i3 = 0;
                    while (i8 <= i9) {
                        i3 |= 1 << i8;
                        i8++;
                    }
                }
                int i10 = this.bits[i7] & i3;
                if (!z) {
                    i3 = 0;
                }
                if (i10 != i3) {
                    return false;
                }
                i7++;
            }
            return true;
        }
    }

    public void appendBit(boolean z) {
        ensureCapacity(this.size + 1);
        if (z) {
            int[] iArr = this.bits;
            int i = this.size;
            int i2 = i >> 5;
            iArr[i2] = (1 << (i & 31)) | iArr[i2];
        }
        this.size++;
    }

    public void appendBits(int i, int i2) {
        if (i2 < 0 || i2 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        ensureCapacity(this.size + i2);
        while (i2 > 0) {
            boolean z = true;
            if (((i >> (i2 - 1)) & 1) != 1) {
                z = false;
            }
            appendBit(z);
            i2--;
        }
    }

    public void appendBitArray(BitArray bitArray) {
        int i = bitArray.size;
        ensureCapacity(this.size + i);
        for (int i2 = 0; i2 < i; i2++) {
            appendBit(bitArray.get(i2));
        }
    }

    public void xor(BitArray bitArray) {
        if (this.bits.length == bitArray.bits.length) {
            int i = 0;
            while (true) {
                int[] iArr = this.bits;
                if (i < iArr.length) {
                    iArr[i] = iArr[i] ^ bitArray.bits[i];
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Sizes don't match");
        }
    }

    public void toBytes(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = 0;
            for (int i6 = 0; i6 < 8; i6++) {
                if (get(i)) {
                    i5 |= 1 << (7 - i6);
                }
                i++;
            }
            bArr[i2 + i4] = (byte) i5;
        }
    }

    public int[] getBitArray() {
        return this.bits;
    }

    public void reverse() {
        int[] iArr = new int[this.bits.length];
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (get((i - i2) - 1)) {
                int i3 = i2 >> 5;
                iArr[i3] = (1 << (i2 & 31)) | iArr[i3];
            }
        }
        this.bits = iArr;
    }

    private static int[] makeArray(int i) {
        return new int[((i + 31) >> 5)];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.size);
        for (int i = 0; i < this.size; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(get(i) ? 'X' : '.');
        }
        return sb.toString();
    }
}
