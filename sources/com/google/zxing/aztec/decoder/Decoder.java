package com.google.zxing.aztec.decoder;

import androidx.core.view.PointerIconCompat;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.microsoft.appcenter.Constants;
import gnu.kawa.functions.GetNamedPart;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.slf4j.Marker;

public final class Decoder {
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", "a", "b", "c", "d", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "f", "g", "h", "i", "j", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "l", "m", RsaJsonWebKey.MODULUS_MEMBER_NAME, "o", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "r", "s", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "u", "v", "w", EllipticCurveJsonWebKey.X_MEMBER_NAME, EllipticCurveJsonWebKey.Y_MEMBER_NAME, "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", GetNamedPart.CAST_METHOD_NAME, "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final int[] NB_BITS = {0, 128, 288, 480, ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_READONLY, 960, 1248, 1568, 1920, 2304, 2720, 3168, 3648, 4160, 4704, 5280, 5888, 6528, 7200, 7904, 8640, 9408, 10208, 11040, 11904, 12800, 13728, 14688, 15680, 16704, 17760, 18848, 19968};
    private static final int[] NB_BITS_COMPACT = {0, 104, 240, ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, 608};
    private static final int[] NB_DATABLOCK = {0, 21, 48, 60, 88, 120, 156, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, 240, 230, 272, 316, 364, ErrorMessages.ERROR_NXT_UNABLE_TO_DOWNLOAD_FILE, 470, 528, 588, 652, 720, 790, 864, 940, PointerIconCompat.TYPE_GRAB, 920, 992, 1066, 1144, 1224, 1306, 1392, 1480, 1570, 1664};
    private static final int[] NB_DATABLOCK_COMPACT = {0, 17, 40, 51, 76};
    private static final String[] PUNCT_TABLE = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", Marker.ANY_MARKER, Marker.ANY_NON_NULL_MARKER, ",", "-", ".", InternalZipConstants.ZIP_FILE_SEPARATOR, Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private int codewordSize;
    private AztecDetectorResult ddata;
    private int invertedBitCount;
    private int numCodewords;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        BitMatrix bits = aztecDetectorResult.getBits();
        if (!this.ddata.isCompact()) {
            bits = removeDashedLines(this.ddata.getBits());
        }
        return new DecoderResult((byte[]) null, getEncodedData(correctBits(extractBits(bits))), (List<byte[]>) null, (String) null);
    }

    private String getEncodedData(boolean[] zArr) throws FormatException {
        boolean[] zArr2 = zArr;
        int nbDatablocks = (this.codewordSize * this.ddata.getNbDatablocks()) - this.invertedBitCount;
        if (nbDatablocks <= zArr2.length) {
            Table table = Table.UPPER;
            Table table2 = Table.UPPER;
            StringBuilder sb = new StringBuilder(20);
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            int i = 0;
            loop0:
            while (true) {
                boolean z4 = false;
                while (!z) {
                    if (z2) {
                        z4 = true;
                    } else {
                        table = table2;
                    }
                    if (z3) {
                        if (nbDatablocks - i < 5) {
                            break loop0;
                        }
                        int readCode = readCode(zArr2, i, 5);
                        i += 5;
                        if (readCode == 0) {
                            if (nbDatablocks - i < 11) {
                                break loop0;
                            }
                            readCode = readCode(zArr2, i, 11) + 31;
                            i += 11;
                        }
                        int i2 = 0;
                        while (true) {
                            if (i2 >= readCode) {
                                break;
                            } else if (nbDatablocks - i < 8) {
                                z = true;
                                break;
                            } else {
                                sb.append((char) readCode(zArr2, i, 8));
                                i += 8;
                                i2++;
                            }
                        }
                        z3 = false;
                        continue;
                    } else if (table2 != Table.BINARY) {
                        int i3 = table2 == Table.DIGIT ? 4 : 5;
                        if (nbDatablocks - i < i3) {
                            break loop0;
                        }
                        int readCode2 = readCode(zArr2, i, i3);
                        i += i3;
                        String character = getCharacter(table2, readCode2);
                        if (character.startsWith("CTRL_")) {
                            table2 = getTable(character.charAt(5));
                            if (character.charAt(6) != 'S') {
                                continue;
                            } else if (character.charAt(5) == 'B') {
                                z2 = true;
                                z3 = true;
                                continue;
                            } else {
                                z2 = true;
                                continue;
                            }
                        } else {
                            sb.append(character);
                            continue;
                        }
                    } else if (nbDatablocks - i < 8) {
                        break loop0;
                    } else {
                        int readCode3 = readCode(zArr2, i, 8);
                        i += 8;
                        sb.append((char) readCode3);
                        continue;
                    }
                    if (z4) {
                        table2 = table;
                        z2 = false;
                    }
                }
                break loop0;
            }
            return sb.toString();
        }
        throw FormatException.getFormatInstance();
    }

    private static Table getTable(char c) {
        if (c == 'B') {
            return Table.BINARY;
        }
        if (c == 'D') {
            return Table.DIGIT;
        }
        if (c == 'P') {
            return Table.PUNCT;
        }
        if (c == 'L') {
            return Table.LOWER;
        }
        if (c != 'M') {
            return Table.UPPER;
        }
        return Table.MIXED;
    }

    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.zxing.aztec.decoder.Decoder$Table[] r0 = com.google.zxing.aztec.decoder.Decoder.Table.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = r0
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.UPPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.LOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.MIXED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.PUNCT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.zxing.aztec.decoder.Decoder$Table r1 = com.google.zxing.aztec.decoder.Decoder.Table.DIGIT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.AnonymousClass1.<clinit>():void");
        }
    }

    private static String getCharacter(Table table, int i) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (i2 == 1) {
            return UPPER_TABLE[i];
        }
        if (i2 == 2) {
            return LOWER_TABLE[i];
        }
        if (i2 == 3) {
            return MIXED_TABLE[i];
        }
        if (i2 == 4) {
            return PUNCT_TABLE[i];
        }
        if (i2 != 5) {
            return "";
        }
        return DIGIT_TABLE[i];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f0, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean[] correctBits(boolean[] r14) throws com.google.zxing.FormatException {
        /*
            r13 = this;
            com.google.zxing.aztec.AztecDetectorResult r0 = r13.ddata
            int r0 = r0.getNbLayers()
            r1 = 2
            if (r0 > r1) goto L_0x000f
            r0 = 6
            r13.codewordSize = r0
            com.google.zxing.common.reedsolomon.GenericGF r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_6
            goto L_0x0035
        L_0x000f:
            com.google.zxing.aztec.AztecDetectorResult r0 = r13.ddata
            int r0 = r0.getNbLayers()
            r1 = 8
            if (r0 > r1) goto L_0x001e
            r13.codewordSize = r1
            com.google.zxing.common.reedsolomon.GenericGF r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_8
            goto L_0x0035
        L_0x001e:
            com.google.zxing.aztec.AztecDetectorResult r0 = r13.ddata
            int r0 = r0.getNbLayers()
            r1 = 22
            if (r0 > r1) goto L_0x002f
            r0 = 10
            r13.codewordSize = r0
            com.google.zxing.common.reedsolomon.GenericGF r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_10
            goto L_0x0035
        L_0x002f:
            r0 = 12
            r13.codewordSize = r0
            com.google.zxing.common.reedsolomon.GenericGF r0 = com.google.zxing.common.reedsolomon.GenericGF.AZTEC_DATA_12
        L_0x0035:
            com.google.zxing.aztec.AztecDetectorResult r1 = r13.ddata
            int r1 = r1.getNbDatablocks()
            com.google.zxing.aztec.AztecDetectorResult r2 = r13.ddata
            boolean r2 = r2.isCompact()
            if (r2 == 0) goto L_0x005f
            int[] r2 = NB_BITS_COMPACT
            com.google.zxing.aztec.AztecDetectorResult r3 = r13.ddata
            int r3 = r3.getNbLayers()
            r2 = r2[r3]
            int r3 = r13.numCodewords
            int r4 = r13.codewordSize
            int r3 = r3 * r4
            int r2 = r2 - r3
            int[] r3 = NB_DATABLOCK_COMPACT
            com.google.zxing.aztec.AztecDetectorResult r4 = r13.ddata
            int r4 = r4.getNbLayers()
            r3 = r3[r4]
            goto L_0x007a
        L_0x005f:
            int[] r2 = NB_BITS
            com.google.zxing.aztec.AztecDetectorResult r3 = r13.ddata
            int r3 = r3.getNbLayers()
            r2 = r2[r3]
            int r3 = r13.numCodewords
            int r4 = r13.codewordSize
            int r3 = r3 * r4
            int r2 = r2 - r3
            int[] r3 = NB_DATABLOCK
            com.google.zxing.aztec.AztecDetectorResult r4 = r13.ddata
            int r4 = r4.getNbLayers()
            r3 = r3[r4]
        L_0x007a:
            int r3 = r3 - r1
            int r4 = r13.numCodewords
            int[] r4 = new int[r4]
            r5 = 0
            r6 = 0
        L_0x0081:
            int r7 = r13.numCodewords
            r8 = 1
            if (r6 >= r7) goto L_0x00a1
            r7 = 1
        L_0x0087:
            int r9 = r13.codewordSize
            if (r8 > r9) goto L_0x009e
            int r10 = r9 * r6
            int r10 = r10 + r9
            int r10 = r10 - r8
            int r10 = r10 + r2
            boolean r9 = r14[r10]
            if (r9 == 0) goto L_0x0099
            r9 = r4[r6]
            int r9 = r9 + r7
            r4[r6] = r9
        L_0x0099:
            int r7 = r7 << 1
            int r8 = r8 + 1
            goto L_0x0087
        L_0x009e:
            int r6 = r6 + 1
            goto L_0x0081
        L_0x00a1:
            com.google.zxing.common.reedsolomon.ReedSolomonDecoder r14 = new com.google.zxing.common.reedsolomon.ReedSolomonDecoder     // Catch:{ ReedSolomonException -> 0x00f4 }
            r14.<init>(r0)     // Catch:{ ReedSolomonException -> 0x00f4 }
            r14.decode(r4, r3)     // Catch:{ ReedSolomonException -> 0x00f4 }
            r13.invertedBitCount = r5
            int r14 = r13.codewordSize
            int r14 = r14 * r1
            boolean[] r14 = new boolean[r14]
            r0 = 0
            r2 = 0
        L_0x00b3:
            if (r0 >= r1) goto L_0x00f3
            int r3 = r13.codewordSize
            int r3 = r3 - r8
            int r3 = r8 << r3
            r6 = 0
            r7 = 0
            r9 = 0
        L_0x00bd:
            int r10 = r13.codewordSize
            if (r6 >= r10) goto L_0x00f0
            r11 = r4[r0]
            r11 = r11 & r3
            if (r11 != r3) goto L_0x00c8
            r11 = 1
            goto L_0x00c9
        L_0x00c8:
            r11 = 0
        L_0x00c9:
            int r12 = r10 + -1
            if (r7 != r12) goto L_0x00de
            if (r11 == r9) goto L_0x00d9
            int r2 = r2 + 1
            int r7 = r13.invertedBitCount
            int r7 = r7 + r8
            r13.invertedBitCount = r7
            r7 = 0
            r9 = 0
            goto L_0x00eb
        L_0x00d9:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        L_0x00de:
            if (r9 != r11) goto L_0x00e3
            int r7 = r7 + 1
            goto L_0x00e5
        L_0x00e3:
            r9 = r11
            r7 = 1
        L_0x00e5:
            int r10 = r10 * r0
            int r10 = r10 + r6
            int r10 = r10 - r2
            r14[r10] = r11
        L_0x00eb:
            int r3 = r3 >>> 1
            int r6 = r6 + 1
            goto L_0x00bd
        L_0x00f0:
            int r0 = r0 + 1
            goto L_0x00b3
        L_0x00f3:
            return r14
        L_0x00f4:
            com.google.zxing.FormatException r14 = com.google.zxing.FormatException.getFormatInstance()
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.decoder.Decoder.correctBits(boolean[]):boolean[]");
    }

    private boolean[] extractBits(BitMatrix bitMatrix) throws FormatException {
        boolean[] zArr;
        int i;
        if (this.ddata.isCompact()) {
            int nbLayers = this.ddata.getNbLayers();
            int[] iArr = NB_BITS_COMPACT;
            if (nbLayers <= iArr.length) {
                zArr = new boolean[iArr[this.ddata.getNbLayers()]];
                this.numCodewords = NB_DATABLOCK_COMPACT[this.ddata.getNbLayers()];
            } else {
                throw FormatException.getFormatInstance();
            }
        } else {
            int nbLayers2 = this.ddata.getNbLayers();
            int[] iArr2 = NB_BITS;
            if (nbLayers2 <= iArr2.length) {
                zArr = new boolean[iArr2[this.ddata.getNbLayers()]];
                this.numCodewords = NB_DATABLOCK[this.ddata.getNbLayers()];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        int nbLayers3 = this.ddata.getNbLayers();
        int height = bitMatrix.getHeight();
        int i2 = 0;
        int i3 = 0;
        while (nbLayers3 != 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                i = height * 2;
                if (i4 >= i - 4) {
                    break;
                }
                int i6 = (i4 / 2) + i3;
                zArr[i2 + i4] = bitMatrix.get(i3 + i5, i6);
                zArr[((i + i2) - 4) + i4] = bitMatrix.get(i6, ((i3 + height) - 1) - i5);
                i5 = (i5 + 1) % 2;
                i4++;
            }
            int i7 = 0;
            for (int i8 = i + 1; i8 > 5; i8--) {
                int i9 = i - i8;
                int i10 = ((i8 / 2) + i3) - 1;
                zArr[(((height * 4) + i2) - 8) + i9 + 1] = bitMatrix.get(((i3 + height) - 1) - i7, i10);
                zArr[(((height * 6) + i2) - 12) + i9 + 1] = bitMatrix.get(i10, i3 + i7);
                i7 = (i7 + 1) % 2;
            }
            i3 += 2;
            i2 += (height * 8) - 16;
            nbLayers3--;
            height -= 4;
        }
        return zArr;
    }

    private static BitMatrix removeDashedLines(BitMatrix bitMatrix) {
        int width = ((((bitMatrix.getWidth() - 1) / 2) / 16) * 2) + 1;
        BitMatrix bitMatrix2 = new BitMatrix(bitMatrix.getWidth() - width, bitMatrix.getHeight() - width);
        int i = 0;
        for (int i2 = 0; i2 < bitMatrix.getWidth(); i2++) {
            if (((bitMatrix.getWidth() / 2) - i2) % 16 != 0) {
                int i3 = 0;
                for (int i4 = 0; i4 < bitMatrix.getHeight(); i4++) {
                    if (((bitMatrix.getWidth() / 2) - i4) % 16 != 0) {
                        if (bitMatrix.get(i2, i4)) {
                            bitMatrix2.set(i, i3);
                        }
                        i3++;
                    }
                }
                i++;
            }
        }
        return bitMatrix2;
    }

    private static int readCode(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3++;
            }
        }
        return i3;
    }
}
