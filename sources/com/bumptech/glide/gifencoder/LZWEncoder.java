package com.bumptech.glide.gifencoder;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.OutputStream;

class LZWEncoder {
    static final int BITS = 12;
    private static final int EOF = -1;
    static final int HSIZE = 5003;
    int ClearCode;
    int EOFCode;
    int a_count;
    byte[] accum = new byte[256];
    boolean clear_flg = false;
    int[] codetab = new int[HSIZE];
    private int curPixel;
    int cur_accum = 0;
    int cur_bits = 0;
    int free_ent = 0;
    int g_init_bits;
    int hsize = HSIZE;
    int[] htab = new int[HSIZE];
    private int imgH;
    private int imgW;
    private int initCodeSize;
    int[] masks = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    int maxbits = 12;
    int maxcode;
    int maxmaxcode = 4096;
    int n_bits;
    private byte[] pixAry;
    private int remaining;

    /* access modifiers changed from: package-private */
    public final int MAXCODE(int i) {
        return (1 << i) - 1;
    }

    LZWEncoder(int i, int i2, byte[] bArr, int i3) {
        this.imgW = i;
        this.imgH = i2;
        this.pixAry = bArr;
        this.initCodeSize = Math.max(2, i3);
    }

    /* access modifiers changed from: package-private */
    public void char_out(byte b, OutputStream outputStream) throws IOException {
        byte[] bArr = this.accum;
        int i = this.a_count;
        int i2 = i + 1;
        this.a_count = i2;
        bArr[i] = b;
        if (i2 >= 254) {
            flush_char(outputStream);
        }
    }

    /* access modifiers changed from: package-private */
    public void cl_block(OutputStream outputStream) throws IOException {
        cl_hash(this.hsize);
        int i = this.ClearCode;
        this.free_ent = i + 2;
        this.clear_flg = true;
        output(i, outputStream);
    }

    /* access modifiers changed from: package-private */
    public void cl_hash(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.htab[i2] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compress(int r9, java.io.OutputStream r10) throws java.io.IOException {
        /*
            r8 = this;
            r8.g_init_bits = r9
            r0 = 0
            r8.clear_flg = r0
            r8.n_bits = r9
            int r1 = r8.MAXCODE(r9)
            r8.maxcode = r1
            r1 = 1
            int r9 = r9 - r1
            int r9 = r1 << r9
            r8.ClearCode = r9
            int r2 = r9 + 1
            r8.EOFCode = r2
            int r9 = r9 + 2
            r8.free_ent = r9
            r8.a_count = r0
            int r9 = r8.nextPixel()
            int r2 = r8.hsize
        L_0x0023:
            r3 = 65536(0x10000, float:9.18355E-41)
            if (r2 >= r3) goto L_0x002c
            int r0 = r0 + 1
            int r2 = r2 * 2
            goto L_0x0023
        L_0x002c:
            int r0 = 8 - r0
            int r2 = r8.hsize
            r8.cl_hash(r2)
            int r3 = r8.ClearCode
            r8.output(r3, r10)
        L_0x0038:
            int r3 = r8.nextPixel()
            r4 = -1
            if (r3 == r4) goto L_0x0085
            int r4 = r8.maxbits
            int r4 = r3 << r4
            int r4 = r4 + r9
            int r5 = r3 << r0
            r5 = r5 ^ r9
            int[] r6 = r8.htab
            r6 = r6[r5]
            if (r6 != r4) goto L_0x0052
            int[] r9 = r8.codetab
            r9 = r9[r5]
            goto L_0x0038
        L_0x0052:
            if (r6 < 0) goto L_0x006a
            int r6 = r2 - r5
            if (r5 != 0) goto L_0x0059
            r6 = 1
        L_0x0059:
            int r5 = r5 - r6
            if (r5 >= 0) goto L_0x005d
            int r5 = r5 + r2
        L_0x005d:
            int[] r7 = r8.htab
            r7 = r7[r5]
            if (r7 != r4) goto L_0x0068
            int[] r9 = r8.codetab
            r9 = r9[r5]
            goto L_0x0038
        L_0x0068:
            if (r7 >= 0) goto L_0x0059
        L_0x006a:
            r8.output(r9, r10)
            int r9 = r8.free_ent
            int r6 = r8.maxmaxcode
            if (r9 >= r6) goto L_0x0080
            int[] r6 = r8.codetab
            int r7 = r9 + 1
            r8.free_ent = r7
            r6[r5] = r9
            int[] r9 = r8.htab
            r9[r5] = r4
            goto L_0x0083
        L_0x0080:
            r8.cl_block(r10)
        L_0x0083:
            r9 = r3
            goto L_0x0038
        L_0x0085:
            r8.output(r9, r10)
            int r9 = r8.EOFCode
            r8.output(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifencoder.LZWEncoder.compress(int, java.io.OutputStream):void");
    }

    /* access modifiers changed from: package-private */
    public void encode(OutputStream outputStream) throws IOException {
        outputStream.write(this.initCodeSize);
        this.remaining = this.imgW * this.imgH;
        this.curPixel = 0;
        compress(this.initCodeSize + 1, outputStream);
        outputStream.write(0);
    }

    /* access modifiers changed from: package-private */
    public void flush_char(OutputStream outputStream) throws IOException {
        int i = this.a_count;
        if (i > 0) {
            outputStream.write(i);
            outputStream.write(this.accum, 0, this.a_count);
            this.a_count = 0;
        }
    }

    private int nextPixel() {
        int i = this.remaining;
        if (i == 0) {
            return -1;
        }
        this.remaining = i - 1;
        byte[] bArr = this.pixAry;
        int i2 = this.curPixel;
        this.curPixel = i2 + 1;
        return bArr[i2] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: package-private */
    public void output(int i, OutputStream outputStream) throws IOException {
        int i2 = this.cur_accum;
        int[] iArr = this.masks;
        int i3 = this.cur_bits;
        int i4 = i2 & iArr[i3];
        this.cur_accum = i4;
        if (i3 > 0) {
            this.cur_accum = i4 | (i << i3);
        } else {
            this.cur_accum = i;
        }
        this.cur_bits = i3 + this.n_bits;
        while (this.cur_bits >= 8) {
            char_out((byte) (this.cur_accum & 255), outputStream);
            this.cur_accum >>= 8;
            this.cur_bits -= 8;
        }
        if (this.free_ent > this.maxcode || this.clear_flg) {
            if (this.clear_flg) {
                int i5 = this.g_init_bits;
                this.n_bits = i5;
                this.maxcode = MAXCODE(i5);
                this.clear_flg = false;
            } else {
                int i6 = this.n_bits + 1;
                this.n_bits = i6;
                if (i6 == this.maxbits) {
                    this.maxcode = this.maxmaxcode;
                } else {
                    this.maxcode = MAXCODE(i6);
                }
            }
        }
        if (i == this.EOFCode) {
            while (this.cur_bits > 0) {
                char_out((byte) (this.cur_accum & 255), outputStream);
                this.cur_accum >>= 8;
                this.cur_bits -= 8;
            }
            flush_char(outputStream);
        }
    }
}
