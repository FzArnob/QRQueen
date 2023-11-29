package gnu.kawa.xml;

import com.google.appinventor.components.runtime.util.Ev3Constants;

public class Base64Binary extends BinaryObject {
    public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    public Base64Binary(byte[] bArr) {
        this.data = bArr;
    }

    public static Base64Binary valueOf(String str) {
        return new Base64Binary(str);
    }

    public Base64Binary(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isWhitespace(charAt) && charAt != '=') {
                i2++;
            }
        }
        int i4 = (i2 * 3) / 4;
        byte[] bArr = new byte[i4];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            char charAt2 = str.charAt(i9);
            if (charAt2 >= 'A' && charAt2 <= 'Z') {
                i = charAt2 - 'A';
            } else if (charAt2 >= 'a' && charAt2 <= 'z') {
                i = (charAt2 - 'a') + 26;
            } else if (charAt2 >= '0' && charAt2 <= '9') {
                i = (charAt2 - '0') + 52;
            } else if (charAt2 == '+') {
                i = 62;
            } else if (charAt2 == '/') {
                i = 63;
            } else {
                if (Character.isWhitespace(charAt2)) {
                    continue;
                } else if (charAt2 == '=') {
                    i6++;
                } else {
                    i = -1;
                }
            }
            if (i < 0 || i6 > 0) {
                throw new IllegalArgumentException("illegal character in base64Binary string at position " + i9);
            }
            i8 = (i8 << 6) + i;
            i5++;
            if (i5 == 4) {
                int i10 = i7 + 1;
                bArr[i7] = (byte) (i8 >> 16);
                int i11 = i10 + 1;
                bArr[i10] = (byte) (i8 >> 8);
                bArr[i11] = (byte) i8;
                i7 = i11 + 1;
                i5 = 0;
            }
        }
        int i12 = i5 + i6;
        if (i12 <= 0 ? i7 != i4 : !(i12 == 4 && (((1 << i6) - 1) & i8) == 0 && (i7 + 3) - i6 == i4)) {
            throw new IllegalArgumentException();
        }
        if (i6 == 1) {
            bArr[i7] = (byte) (i8 << 10);
            bArr[i7 + 1] = (byte) (i8 >> 2);
        } else if (i6 == 2) {
            bArr[i7] = (byte) (i8 >> 4);
        }
        this.data = bArr;
    }

    public StringBuffer toString(StringBuffer stringBuffer) {
        byte[] bArr = this.data;
        int length = bArr.length;
        int i = 0;
        byte b = 0;
        while (i < length) {
            b = (b << 8) | (bArr[i] & Ev3Constants.Opcode.TST);
            i++;
            if (i % 3 == 0) {
                stringBuffer.append(ENCODING.charAt((b >> 18) & 63));
                stringBuffer.append(ENCODING.charAt((b >> 12) & 63));
                stringBuffer.append(ENCODING.charAt((b >> 6) & 63));
                stringBuffer.append(ENCODING.charAt(b & Ev3Constants.Opcode.MOVEF_F));
            }
        }
        int i2 = length % 3;
        if (i2 == 1) {
            stringBuffer.append(ENCODING.charAt((b >> 2) & 63));
            stringBuffer.append(ENCODING.charAt((b << 4) & 63));
            stringBuffer.append("==");
        } else if (i2 == 2) {
            stringBuffer.append(ENCODING.charAt((b >> 10) & 63));
            stringBuffer.append(ENCODING.charAt((b >> 4) & 63));
            stringBuffer.append(ENCODING.charAt((b << 2) & 63));
            stringBuffer.append('=');
        }
        return stringBuffer;
    }

    public String toString() {
        return toString(new StringBuffer()).toString();
    }
}
