package org.jose4j.base64url.internal.apache.commons.codec.binary;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.math.BigInteger;
import org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec;
import org.jose4j.lang.StringUtil;

public class Base64 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 6;
    private static final int BYTES_PER_ENCODED_BLOCK = 4;
    private static final int BYTES_PER_UNENCODED_BLOCK = 3;
    static final byte[] CHUNK_SEPARATOR = {13, 10};
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, Ev3Constants.Opcode.MOVEF_32, -1, Ev3Constants.Opcode.MOVEF_32, -1, Ev3Constants.Opcode.MOVEF_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.MOVE32_32, Ev3Constants.Opcode.MOVE32_F, Ev3Constants.Opcode.MOVEF_8, Ev3Constants.Opcode.MOVEF_16, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, Ev3Constants.Opcode.MOVEF_F, -1, 26, 27, 28, 29, 30, 31, 32, Ev3Constants.Opcode.OR16, Ev3Constants.Opcode.OR32, 35, Ev3Constants.Opcode.AND8, Ev3Constants.Opcode.AND16, Ev3Constants.Opcode.AND32, 39, Ev3Constants.Opcode.XOR8, Ev3Constants.Opcode.XOR16, Ev3Constants.Opcode.XOR32, 43, Ev3Constants.Opcode.RL8, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.RL32, Ev3Constants.Opcode.INIT_BYTES, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F};
    private static final int MASK_6BITS = 63;
    private static final byte[] STANDARD_ENCODE_TABLE = {Ev3Constants.Opcode.JR_FALSE, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.JR_NAN, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.CP_LT32, Ev3Constants.Opcode.CP_LTF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_GT32, Ev3Constants.Opcode.CP_GTF, Ev3Constants.Opcode.CP_EQ8, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ32, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ8, Ev3Constants.Opcode.CP_NEQ16, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_NEQF, Ev3Constants.Opcode.CP_LTEQ8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.CP_LTEQ32, Ev3Constants.Opcode.CP_LTEQF, Ev3Constants.Opcode.CP_GTEQ8, Ev3Constants.Opcode.CP_GTEQ16, Ev3Constants.Opcode.CP_GTEQ32, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_INPUT, Ev3Constants.Opcode.NOTE_TO_FREQ, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_GT32, Ev3Constants.Opcode.JR_GTF, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_NEQ16, Ev3Constants.Opcode.JR_NEQ32, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LTEQ32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GTEQ16, Ev3Constants.Opcode.JR_GTEQ32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, 43, Ev3Constants.Opcode.INIT_BYTES};
    private static final byte[] URL_SAFE_ENCODE_TABLE = {Ev3Constants.Opcode.JR_FALSE, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.JR_NAN, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LT16, Ev3Constants.Opcode.CP_LT32, Ev3Constants.Opcode.CP_LTF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.CP_GT16, Ev3Constants.Opcode.CP_GT32, Ev3Constants.Opcode.CP_GTF, Ev3Constants.Opcode.CP_EQ8, Ev3Constants.Opcode.CP_EQ16, Ev3Constants.Opcode.CP_EQ32, Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.CP_NEQ8, Ev3Constants.Opcode.CP_NEQ16, Ev3Constants.Opcode.CP_NEQ32, Ev3Constants.Opcode.CP_NEQF, Ev3Constants.Opcode.CP_LTEQ8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.CP_LTEQ32, Ev3Constants.Opcode.CP_LTEQF, Ev3Constants.Opcode.CP_GTEQ8, Ev3Constants.Opcode.CP_GTEQ16, Ev3Constants.Opcode.CP_GTEQ32, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_INPUT, Ev3Constants.Opcode.NOTE_TO_FREQ, Ev3Constants.Opcode.JR_LT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_GT32, Ev3Constants.Opcode.JR_GTF, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.JR_EQ16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_NEQ16, Ev3Constants.Opcode.JR_NEQ32, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_LTEQ32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_GTEQ16, Ev3Constants.Opcode.JR_GTEQ32, Ev3Constants.Opcode.MOVE8_8, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.MOVE8_32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.MOVE16_8, Ev3Constants.Opcode.MOVE16_16, Ev3Constants.Opcode.MOVE16_32, Ev3Constants.Opcode.MOVE16_F, Ev3Constants.Opcode.MOVE32_8, Ev3Constants.Opcode.MOVE32_16, Ev3Constants.Opcode.RL16, Ev3Constants.Opcode.SELECTF};
    private final int decodeSize;
    private final byte[] decodeTable;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;

    public Base64() {
        this(0);
    }

    public Base64(boolean z) {
        this(76, CHUNK_SEPARATOR, z);
    }

    public Base64(int i) {
        this(i, CHUNK_SEPARATOR);
    }

    public Base64(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Base64(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.decodeTable = DECODE_TABLE;
        if (bArr == null) {
            this.encodeSize = 4;
            this.lineSeparator = null;
        } else if (containsAlphabetOrPad(bArr)) {
            String newStringUtf8 = StringUtil.newStringUtf8(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + newStringUtf8 + "]");
        } else if (i > 0) {
            this.encodeSize = bArr.length + 4;
            byte[] bArr2 = new byte[bArr.length];
            this.lineSeparator = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            this.encodeSize = 4;
            this.lineSeparator = null;
        }
        this.decodeSize = this.encodeSize - 1;
        this.encodeTable = z ? URL_SAFE_ENCODE_TABLE : STANDARD_ENCODE_TABLE;
    }

    public boolean isUrlSafe() {
        return this.encodeTable == URL_SAFE_ENCODE_TABLE;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v27, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(byte[] r8, int r9, int r10, org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec.Context r11) {
        /*
            r7 = this;
            boolean r0 = r11.eof
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r10 >= 0) goto L_0x00e3
            r11.eof = r1
            int r8 = r11.modulus
            if (r8 != 0) goto L_0x0014
            int r8 = r7.lineLength
            if (r8 != 0) goto L_0x0014
            return
        L_0x0014:
            int r8 = r7.encodeSize
            byte[] r8 = r7.ensureBufferSize(r8, r11)
            int r9 = r11.pos
            int r10 = r11.modulus
            if (r10 == 0) goto L_0x00bf
            r2 = 61
            r3 = 2
            if (r10 == r1) goto L_0x0085
            if (r10 != r3) goto L_0x006c
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            byte[] r1 = r7.encodeTable
            int r4 = r11.ibitWorkArea
            int r4 = r4 >> 10
            r4 = r4 & 63
            byte r1 = r1[r4]
            r8[r10] = r1
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            byte[] r1 = r7.encodeTable
            int r4 = r11.ibitWorkArea
            int r4 = r4 >> 4
            r4 = r4 & 63
            byte r1 = r1[r4]
            r8[r10] = r1
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            byte[] r1 = r7.encodeTable
            int r4 = r11.ibitWorkArea
            int r3 = r4 << 2
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            byte[] r10 = r7.encodeTable
            byte[] r1 = STANDARD_ENCODE_TABLE
            if (r10 != r1) goto L_0x00bf
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            r8[r10] = r2
            goto L_0x00bf
        L_0x006c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Impossible modulus "
            r9.append(r10)
            int r10 = r11.modulus
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0085:
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            byte[] r1 = r7.encodeTable
            int r4 = r11.ibitWorkArea
            int r3 = r4 >> 2
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            byte[] r1 = r7.encodeTable
            int r3 = r11.ibitWorkArea
            int r3 = r3 << 4
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            byte[] r10 = r7.encodeTable
            byte[] r1 = STANDARD_ENCODE_TABLE
            if (r10 != r1) goto L_0x00bf
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            r8[r10] = r2
            int r10 = r11.pos
            int r1 = r10 + 1
            r11.pos = r1
            r8[r10] = r2
        L_0x00bf:
            int r10 = r11.currentLinePos
            int r1 = r11.pos
            int r1 = r1 - r9
            int r10 = r10 + r1
            r11.currentLinePos = r10
            int r9 = r7.lineLength
            if (r9 <= 0) goto L_0x0175
            int r9 = r11.currentLinePos
            if (r9 <= 0) goto L_0x0175
            byte[] r9 = r7.lineSeparator
            int r10 = r11.pos
            byte[] r1 = r7.lineSeparator
            int r1 = r1.length
            java.lang.System.arraycopy(r9, r0, r8, r10, r1)
            int r8 = r11.pos
            byte[] r9 = r7.lineSeparator
            int r9 = r9.length
            int r8 = r8 + r9
            r11.pos = r8
            goto L_0x0175
        L_0x00e3:
            r2 = 0
        L_0x00e4:
            if (r2 >= r10) goto L_0x0175
            int r3 = r7.encodeSize
            byte[] r3 = r7.ensureBufferSize(r3, r11)
            int r4 = r11.modulus
            int r4 = r4 + r1
            int r4 = r4 % 3
            r11.modulus = r4
            int r4 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x00fb
            int r9 = r9 + 256
        L_0x00fb:
            int r5 = r11.ibitWorkArea
            int r5 = r5 << 8
            int r5 = r5 + r9
            r11.ibitWorkArea = r5
            int r9 = r11.modulus
            if (r9 != 0) goto L_0x0170
            int r9 = r11.pos
            int r5 = r9 + 1
            r11.pos = r5
            byte[] r5 = r7.encodeTable
            int r6 = r11.ibitWorkArea
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.pos
            int r5 = r9 + 1
            r11.pos = r5
            byte[] r5 = r7.encodeTable
            int r6 = r11.ibitWorkArea
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.pos
            int r5 = r9 + 1
            r11.pos = r5
            byte[] r5 = r7.encodeTable
            int r6 = r11.ibitWorkArea
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.pos
            int r5 = r9 + 1
            r11.pos = r5
            byte[] r5 = r7.encodeTable
            int r6 = r11.ibitWorkArea
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.currentLinePos
            int r9 = r9 + 4
            r11.currentLinePos = r9
            int r9 = r7.lineLength
            if (r9 <= 0) goto L_0x0170
            int r9 = r7.lineLength
            int r5 = r11.currentLinePos
            if (r9 > r5) goto L_0x0170
            byte[] r9 = r7.lineSeparator
            int r5 = r11.pos
            byte[] r6 = r7.lineSeparator
            int r6 = r6.length
            java.lang.System.arraycopy(r9, r0, r3, r5, r6)
            int r9 = r11.pos
            byte[] r3 = r7.lineSeparator
            int r3 = r3.length
            int r9 = r9 + r3
            r11.pos = r9
            r11.currentLinePos = r0
        L_0x0170:
            int r2 = r2 + 1
            r9 = r4
            goto L_0x00e4
        L_0x0175:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.base64url.internal.apache.commons.codec.binary.Base64.encode(byte[], int, int, org.jose4j.base64url.internal.apache.commons.codec.binary.BaseNCodec$Context):void");
    }

    /* access modifiers changed from: package-private */
    public void decode(byte[] bArr, int i, int i2, BaseNCodec.Context context) {
        byte b;
        if (!context.eof) {
            if (i2 < 0) {
                context.eof = true;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                byte[] ensureBufferSize = ensureBufferSize(this.decodeSize, context);
                int i4 = i + 1;
                byte b2 = bArr[i];
                if (b2 == 61) {
                    context.eof = true;
                    break;
                }
                if (b2 >= 0) {
                    byte[] bArr2 = DECODE_TABLE;
                    if (b2 < bArr2.length && (b = bArr2[b2]) >= 0) {
                        context.modulus = (context.modulus + 1) % 4;
                        context.ibitWorkArea = (context.ibitWorkArea << 6) + b;
                        if (context.modulus == 0) {
                            int i5 = context.pos;
                            context.pos = i5 + 1;
                            ensureBufferSize[i5] = (byte) ((context.ibitWorkArea >> 16) & 255);
                            int i6 = context.pos;
                            context.pos = i6 + 1;
                            ensureBufferSize[i6] = (byte) ((context.ibitWorkArea >> 8) & 255);
                            int i7 = context.pos;
                            context.pos = i7 + 1;
                            ensureBufferSize[i7] = (byte) (context.ibitWorkArea & 255);
                        }
                    }
                }
                i3++;
                i = i4;
            }
            if (context.eof && context.modulus != 0) {
                byte[] ensureBufferSize2 = ensureBufferSize(this.decodeSize, context);
                int i8 = context.modulus;
                if (i8 == 1) {
                    return;
                }
                if (i8 == 2) {
                    context.ibitWorkArea >>= 4;
                    int i9 = context.pos;
                    context.pos = i9 + 1;
                    ensureBufferSize2[i9] = (byte) (context.ibitWorkArea & 255);
                } else if (i8 == 3) {
                    context.ibitWorkArea >>= 2;
                    int i10 = context.pos;
                    context.pos = i10 + 1;
                    ensureBufferSize2[i10] = (byte) ((context.ibitWorkArea >> 8) & 255);
                    int i11 = context.pos;
                    context.pos = i11 + 1;
                    ensureBufferSize2[i11] = (byte) (context.ibitWorkArea & 255);
                } else {
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
                }
            }
        }
    }

    @Deprecated
    public static boolean isArrayByteBase64(byte[] bArr) {
        return isBase64(bArr);
    }

    public static boolean isBase64(byte b) {
        if (b != 61) {
            if (b >= 0) {
                byte[] bArr = DECODE_TABLE;
                if (b >= bArr.length || bArr[b] == -1) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public static boolean isBase64(String str) {
        return isBase64(StringUtil.getBytesUtf8(str));
    }

    public static boolean isBase64(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!isBase64(bArr[i]) && !isWhiteSpace(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] bArr) {
        return encodeBase64(bArr, false);
    }

    public static String encodeBase64String(byte[] bArr) {
        return StringUtil.newStringUtf8(encodeBase64(bArr, false));
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return encodeBase64(bArr, false, true);
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        return StringUtil.newStringUtf8(encodeBase64(bArr, false, true));
    }

    public static byte[] encodeBase64Chunked(byte[] bArr) {
        return encodeBase64(bArr, true);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z) {
        return encodeBase64(bArr, z, false);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2) {
        return encodeBase64(bArr, z, z2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public static byte[] encodeBase64(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        Base64 base64 = z ? new Base64(z2) : new Base64(0, CHUNK_SEPARATOR, z2);
        long encodedLength = base64.getEncodedLength(bArr);
        if (encodedLength <= ((long) i)) {
            return base64.encode(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + encodedLength + ") than the specified maximum size of " + i);
    }

    public static byte[] decodeBase64(String str) {
        return new Base64().decode(str);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return new Base64().decode(bArr);
    }

    public static BigInteger decodeInteger(byte[] bArr) {
        return new BigInteger(1, decodeBase64(bArr));
    }

    public static byte[] encodeInteger(BigInteger bigInteger) {
        if (bigInteger != null) {
            return encodeBase64(toIntegerBytes(bigInteger), false);
        }
        throw new NullPointerException("encodeInteger called with null parameter");
    }

    static byte[] toIntegerBytes(BigInteger bigInteger) {
        int bitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        int i = 1;
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == bitLength / 8) {
            return byteArray;
        }
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
        } else {
            i = 0;
        }
        int i2 = bitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public boolean isInAlphabet(byte b) {
        if (b >= 0) {
            byte[] bArr = this.decodeTable;
            return b < bArr.length && bArr[b] != -1;
        }
    }
}
