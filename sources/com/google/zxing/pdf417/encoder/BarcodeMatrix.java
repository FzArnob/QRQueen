package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

final class BarcodeMatrix {
    private int currentRow;
    private final int height;
    private final BarcodeRow[] matrix;
    private final int width;

    BarcodeMatrix(int i, int i2) {
        int i3 = i + 2;
        BarcodeRow[] barcodeRowArr = new BarcodeRow[i3];
        this.matrix = barcodeRowArr;
        int length = barcodeRowArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            this.matrix[i4] = new BarcodeRow(((i2 + 4) * 17) + 1);
        }
        this.width = i2 * 17;
        this.height = i3;
        this.currentRow = 0;
    }

    /* access modifiers changed from: package-private */
    public void set(int i, int i2, byte b) {
        this.matrix[i2].set(i, b);
    }

    /* access modifiers changed from: package-private */
    public void setMatrix(int i, int i2, boolean z) {
        set(i, i2, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void startRow() {
        this.currentRow++;
    }

    /* access modifiers changed from: package-private */
    public BarcodeRow getCurrentRow() {
        return this.matrix[this.currentRow];
    }

    /* access modifiers changed from: package-private */
    public byte[][] getMatrix() {
        return getScaledMatrix(1, 1);
    }

    /* access modifiers changed from: package-private */
    public byte[][] getScaledMatrix(int i) {
        return getScaledMatrix(i, i);
    }

    /* access modifiers changed from: package-private */
    public byte[][] getScaledMatrix(int i, int i2) {
        int[] iArr = new int[2];
        iArr[1] = this.width * i;
        iArr[0] = this.height * i2;
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, iArr);
        int i3 = this.height * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[(i3 - i4) - 1] = this.matrix[i4 / i2].getScaledRow(i);
        }
        return bArr;
    }
}
