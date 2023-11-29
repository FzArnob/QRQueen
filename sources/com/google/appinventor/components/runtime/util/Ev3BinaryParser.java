package com.google.appinventor.components.runtime.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import org.jose4j.lang.StringUtil;

public class Ev3BinaryParser {
    private static byte B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = 64;
    private static byte Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = 3;
    private static byte hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 63;
    private static byte hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Byte.MIN_VALUE;
    private static byte qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 2;
    private static byte vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = 31;
    private static byte vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = 1;
    private static byte wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = 32;

    static class a {
        public char hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        public int size;

        public a(char c, int i) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = c;
            this.size = i;
        }
    }

    public static byte[] pack(String str, Object... objArr) throws IllegalArgumentException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        Object[] objArr2 = objArr;
        String[] split = str.split("(?<=\\D)");
        int length = split.length;
        a[] aVarArr = new a[length];
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < split.length; i7++) {
            String str2 = split[i7];
            char charAt = str2.charAt(str2.length() - 1);
            if (str2.length() != 1) {
                i = Integer.parseInt(str2.substring(0, str2.length() - 1));
                if (i > 0) {
                    z = true;
                } else {
                    throw new IllegalArgumentException("Illegal format string");
                }
            } else {
                i = 1;
                z = false;
            }
            if (charAt != 'B') {
                if (charAt != 'F') {
                    if (charAt == 'L') {
                        i3 = i << 3;
                    } else if (charAt != 'S') {
                        if (charAt != 'b') {
                            if (charAt != 'f') {
                                if (charAt == 'l') {
                                    i4 = i << 3;
                                } else if (charAt != 's') {
                                    if (charAt == 'x') {
                                        i6 += i;
                                        aVarArr[i7] = new a(charAt, i);
                                    } else if (charAt == 'H') {
                                        i3 = i << 1;
                                    } else if (charAt != 'I') {
                                        if (charAt == 'h') {
                                            i4 = i << 1;
                                        } else if (charAt != 'i') {
                                            throw new IllegalArgumentException("Illegal format string");
                                        }
                                    }
                                } else if (i != ((String) objArr2[i5]).length()) {
                                    throw new IllegalArgumentException("Illegal format string");
                                }
                                i6 += i4;
                            }
                            i4 = i << 2;
                            i6 += i4;
                        } else {
                            i6 += i;
                        }
                        i5 += i;
                        aVarArr[i7] = new a(charAt, i);
                    } else if (!z) {
                        i2 = i6 + ((String) objArr2[i5]).length() + 1;
                        i5++;
                        aVarArr[i7] = new a(charAt, i);
                    } else {
                        throw new IllegalArgumentException("Illegal format string");
                    }
                    i2 = i6 + i3;
                    i5++;
                    aVarArr[i7] = new a(charAt, i);
                }
                i3 = i << 2;
                i2 = i6 + i3;
                i5++;
                aVarArr[i7] = new a(charAt, i);
            }
            i2 = i6 + i;
            i5++;
            aVarArr[i7] = new a(charAt, i);
        }
        if (i5 == objArr2.length) {
            ByteBuffer allocate = ByteBuffer.allocate(i6);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            int i8 = 0;
            for (int i9 = 0; i9 < length; i9++) {
                a aVar = aVarArr[i9];
                char c = aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                if (c == 'B') {
                    allocate.put((byte[]) objArr2[i8]);
                } else if (c == 'F') {
                    for (int i10 = 0; i10 < aVar.size; i10++) {
                        allocate.putFloat(((float[]) objArr2[i8])[i10]);
                    }
                } else if (c == 'L') {
                    for (int i11 = 0; i11 < aVar.size; i11++) {
                        allocate.putLong(((long[]) objArr2[i8])[i11]);
                    }
                } else if (c != 'S') {
                    if (c == 'b') {
                        for (int i12 = 0; i12 < aVar.size; i12++) {
                            allocate.put(((Byte) objArr2[i8]).byteValue());
                            i8++;
                        }
                    } else if (c == 'f') {
                        for (int i13 = 0; i13 < aVar.size; i13++) {
                            allocate.putFloat(((Float) objArr2[i8]).floatValue());
                            i8++;
                        }
                    } else if (c == 'l') {
                        for (int i14 = 0; i14 < aVar.size; i14++) {
                            allocate.putLong(((Long) objArr2[i8]).longValue());
                            i8++;
                        }
                    } else if (c == 's') {
                        try {
                            allocate.put(((String) objArr2[i8]).getBytes(StringUtil.US_ASCII));
                        } catch (UnsupportedEncodingException unused) {
                            throw new IllegalArgumentException();
                        }
                    } else if (c == 'x') {
                        for (int i15 = 0; i15 < aVar.size; i15++) {
                            allocate.put((byte) 0);
                        }
                    } else if (c == 'H') {
                        for (int i16 = 0; i16 < aVar.size; i16++) {
                            allocate.putShort(((short[]) objArr2[i8])[i16]);
                        }
                    } else if (c == 'I') {
                        for (int i17 = 0; i17 < aVar.size; i17++) {
                            allocate.putInt(((int[]) objArr2[i8])[i17]);
                        }
                    } else if (c == 'h') {
                        for (int i18 = 0; i18 < aVar.size; i18++) {
                            allocate.putShort(((Short) objArr2[i8]).shortValue());
                            i8++;
                        }
                    } else if (c == 'i') {
                        for (int i19 = 0; i19 < aVar.size; i19++) {
                            allocate.putInt(((Integer) objArr2[i8]).intValue());
                            i8++;
                        }
                    }
                } else {
                    try {
                        allocate.put(((String) objArr2[i8]).getBytes(StringUtil.US_ASCII));
                        allocate.put((byte) 0);
                    } catch (UnsupportedEncodingException unused2) {
                        throw new IllegalArgumentException();
                    }
                }
                i8++;
            }
            return allocate.array();
        }
        throw new IllegalArgumentException("Illegal format string");
    }

    public static Object[] unpack(String str, byte[] bArr) throws IllegalArgumentException {
        int i;
        String[] split = str.split("(?<=\\D)");
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        int length = split.length;
        int i2 = 0;
        while (i2 < length) {
            String str2 = split[i2];
            boolean z = true;
            char charAt = str2.charAt(str2.length() - 1);
            if (str2.length() > 1) {
                i = Integer.parseInt(str2.substring(0, str2.length() - 1));
                if (i <= 0) {
                    throw new IllegalArgumentException("Illegal format string");
                }
            } else {
                i = 1;
                z = false;
            }
            if (charAt != '$') {
                if (charAt == 'B') {
                    byte[] bArr2 = new byte[i];
                    wrap.get(bArr2, 0, i);
                    arrayList.add(bArr2);
                } else if (charAt == 'F') {
                    float[] fArr = new float[i];
                    for (int i3 = 0; i3 < i; i3++) {
                        fArr[i3] = wrap.getFloat();
                    }
                    arrayList.add(fArr);
                } else if (charAt == 'L') {
                    long[] jArr = new long[i];
                    for (int i4 = 0; i4 < i; i4++) {
                        jArr[i4] = wrap.getLong();
                    }
                    arrayList.add(jArr);
                } else if (charAt != 'S') {
                    if (charAt == 'b') {
                        for (int i5 = 0; i5 < i; i5++) {
                            arrayList.add(Byte.valueOf(wrap.get()));
                        }
                    } else if (charAt == 'f') {
                        for (int i6 = 0; i6 < i; i6++) {
                            arrayList.add(Float.valueOf(wrap.getFloat()));
                        }
                    } else if (charAt == 'l') {
                        for (int i7 = 0; i7 < i; i7++) {
                            arrayList.add(Long.valueOf(wrap.getLong()));
                        }
                    } else if (charAt == 's') {
                        byte[] bArr3 = new byte[i];
                        wrap.get(bArr3, 0, i);
                        try {
                            arrayList.add(new String(bArr3, StringUtil.US_ASCII));
                        } catch (UnsupportedEncodingException unused) {
                            throw new IllegalArgumentException();
                        }
                    } else if (charAt == 'x') {
                        for (int i8 = 0; i8 < i; i8++) {
                            wrap.get();
                        }
                    } else if (charAt == 'H') {
                        short[] sArr = new short[i];
                        for (short s = 0; s < i; s = (short) (s + 1)) {
                            sArr[s] = wrap.getShort();
                        }
                        arrayList.add(sArr);
                    } else if (charAt == 'I') {
                        int[] iArr = new int[i];
                        for (int i9 = 0; i9 < i; i9++) {
                            iArr[i9] = wrap.getInt();
                        }
                        arrayList.add(iArr);
                    } else if (charAt == 'h') {
                        for (int i10 = 0; i10 < i; i10++) {
                            arrayList.add(Short.valueOf(wrap.getShort()));
                        }
                    } else if (charAt == 'i') {
                        for (int i11 = 0; i11 < i; i11++) {
                            arrayList.add(Integer.valueOf(wrap.getInt()));
                        }
                    }
                } else if (!z) {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        byte b = wrap.get();
                        if (b == 0) {
                            break;
                        }
                        stringBuffer.append((char) b);
                    }
                    arrayList.add(stringBuffer.toString());
                } else {
                    throw new IllegalArgumentException("Illegal format string");
                }
                i2++;
            } else if (z) {
                throw new IllegalArgumentException("Illegal format string");
            } else if (wrap.hasRemaining()) {
                throw new IllegalArgumentException("Illegal format string");
            }
            throw new IllegalArgumentException("Illegal format string");
        }
        return arrayList.toArray();
    }

    public static byte[] encodeLC0(byte b) {
        if (b < -31 || b > 31) {
            throw new IllegalArgumentException("Encoded value must be in range [0, 127]");
        }
        return new byte[]{(byte) (b & hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO)};
    }

    public static byte[] encodeLC1(byte b) {
        return new byte[]{(byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R), (byte) b};
    }

    public static byte[] encodeLC2(short s) {
        return new byte[]{(byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE), (byte) s, (byte) (s >>> 8)};
    }

    public static byte[] encodeLC4(int i) {
        return new byte[]{(byte) (((byte) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB), (byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public static byte[] encodeLV0(int i) {
        return new byte[]{(byte) ((i & vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)};
    }

    public static byte[] encodeLV1(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R), (byte) i};
    }

    public static byte[] encodeLV2(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE), (byte) i, (byte) (i >>> 8)};
    }

    public static byte[] encodeLV4(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB), (byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public static byte[] encodeGV0(int i) {
        return new byte[]{(byte) ((i & vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou)};
    }

    public static byte[] encodeGV1(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R), (byte) i};
    }

    public static byte[] encodeGV2(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE), (byte) i, (byte) (i >>> 8)};
    }

    public static byte[] encodeGV4(int i) {
        return new byte[]{(byte) (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME | B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T | wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou | Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB), (byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public static byte[] encodeSystemCommand(byte b, boolean z, Object... objArr) {
        int length = objArr.length;
        int i = 2;
        int i2 = 0;
        while (true) {
            byte b2 = 1;
            if (i2 < length) {
                String str = objArr[i2];
                if (str instanceof Byte) {
                    i++;
                } else if (str instanceof Short) {
                    i += 2;
                } else if (str instanceof Integer) {
                    i += 4;
                } else if (str instanceof String) {
                    i += str.length() + 1;
                } else {
                    throw new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
                }
                i2++;
            } else {
                ByteBuffer allocate = ByteBuffer.allocate(i);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                if (!z) {
                    b2 = -127;
                }
                allocate.put(b2);
                allocate.put(b);
                for (Byte b3 : objArr) {
                    if (b3 instanceof Byte) {
                        allocate.put(b3.byteValue());
                    } else if (b3 instanceof Short) {
                        allocate.putShort(((Short) b3).shortValue());
                    } else if (b3 instanceof Integer) {
                        allocate.putInt(((Integer) b3).intValue());
                    } else if (b3 instanceof String) {
                        try {
                            allocate.put(((String) b3).getBytes(StringUtil.US_ASCII));
                            allocate.put((byte) 0);
                        } catch (UnsupportedEncodingException unused) {
                            throw new IllegalArgumentException("Non-ASCII string encoding is not supported");
                        }
                    } else {
                        throw new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
                    }
                }
                return allocate.array();
            }
        }
    }

    public static byte[] encodeDirectCommand(byte b, boolean z, int i, int i2, String str, Object... objArr) {
        if (i < 0 || i > 1023 || i2 < 0 || i2 > 63 || str.length() != objArr.length) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            String str2 = objArr[i3];
            if (charAt != 'c') {
                if (charAt != 'g') {
                    if (charAt != 'l') {
                        if (charAt != 's') {
                            throw new IllegalArgumentException("Illegal format string");
                        } else if (str2 instanceof String) {
                            try {
                                arrayList.add((str2 + 0).getBytes(StringUtil.US_ASCII));
                            } catch (UnsupportedEncodingException unused) {
                                throw new IllegalArgumentException();
                            }
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else if (str2 instanceof Byte) {
                        Byte b2 = (Byte) str2;
                        if (b2.byteValue() > 31 || b2.byteValue() < -31) {
                            arrayList.add(encodeLV1(b2.byteValue()));
                        } else {
                            arrayList.add(encodeLV0(b2.byteValue()));
                        }
                    } else if (str2 instanceof Short) {
                        arrayList.add(encodeLV2(((Short) str2).shortValue()));
                    } else if (str2 instanceof Integer) {
                        arrayList.add(encodeLV4(((Integer) str2).intValue()));
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else if (str2 instanceof Byte) {
                    Byte b3 = (Byte) str2;
                    if (b3.byteValue() > 31 || b3.byteValue() < -31) {
                        arrayList.add(encodeGV1(b3.byteValue()));
                    } else {
                        arrayList.add(encodeGV0(b3.byteValue()));
                    }
                } else if (str2 instanceof Short) {
                    arrayList.add(encodeGV2(((Short) str2).shortValue()));
                } else if (str2 instanceof Integer) {
                    arrayList.add(encodeGV4(((Integer) str2).intValue()));
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (str2 instanceof Byte) {
                Byte b4 = (Byte) str2;
                if (b4.byteValue() > 31 || b4.byteValue() < -31) {
                    arrayList.add(encodeLC1(b4.byteValue()));
                } else {
                    arrayList.add(encodeLC0(b4.byteValue()));
                }
            } else if (str2 instanceof Short) {
                arrayList.add(encodeLC2(((Short) str2).shortValue()));
            } else if (str2 instanceof Integer) {
                arrayList.add(encodeLC4(((Integer) str2).intValue()));
            } else {
                throw new IllegalArgumentException();
            }
        }
        int i4 = 4;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            i4 += ((byte[]) it.next()).length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.put(z ? 0 : Byte.MIN_VALUE);
        allocate.put(new byte[]{(byte) i, (byte) ((i2 << 2) | ((i >>> 8) & 3))});
        allocate.put(b);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            allocate.put((byte[]) it2.next());
        }
        return allocate.array();
    }
}
