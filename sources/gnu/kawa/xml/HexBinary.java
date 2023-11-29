package gnu.kawa.xml;

public class HexBinary extends BinaryObject {
    static char forHexDigit(int i) {
        return (char) (i < 10 ? i + 48 : (i - 10) + 65);
    }

    public HexBinary(byte[] bArr) {
        this.data = bArr;
    }

    static HexBinary valueOf(String str) {
        return new HexBinary(parseHexBinary(str));
    }

    static byte[] parseHexBinary(String str) {
        String trim = str.trim();
        int length = trim.length();
        if ((length & 1) == 0) {
            int i = length >> 1;
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 * 2;
                int digit = Character.digit(trim.charAt(i3), 16);
                int i4 = i3 + 1;
                int digit2 = Character.digit(trim.charAt(i4), 16);
                if (digit >= 0) {
                    i3 = digit2 < 0 ? i4 : -1;
                }
                if (i3 < 0) {
                    bArr[i2] = (byte) ((digit * 16) + digit2);
                    i2++;
                } else {
                    throw new IllegalArgumentException("invalid hexBinary character at position " + i3);
                }
            }
            return bArr;
        }
        throw new IllegalArgumentException("hexBinary string length not a multiple of 2");
    }

    public StringBuffer toString(StringBuffer stringBuffer) {
        for (byte b : this.data) {
            stringBuffer.append(forHexDigit((b >> 4) & 15));
            stringBuffer.append(forHexDigit(b & 15));
        }
        return stringBuffer;
    }

    public String toString() {
        return toString(new StringBuffer()).toString();
    }
}
