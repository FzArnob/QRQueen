package com.google.appinventor.components.runtime.util.crypt;

import gnu.bytecode.Access;

public class KodularEnigma {
    public static final StringBuffer[] notches = {new StringBuffer("Q"), new StringBuffer("E"), new StringBuffer("V"), new StringBuffer("J"), new StringBuffer("Z"), new StringBuffer("Z"), new StringBuffer("Z"), new StringBuffer("Z")};
    public static final StringBuffer reflector0 = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final StringBuffer reflectorB = new StringBuffer("YRUHQSLDPXNGOKMIEBFZCWVJAT");
    public static final StringBuffer reflectorC = new StringBuffer("FVPJIAOYEDRZXWGCTKUQSBNMHL");
    public static final StringBuffer rotorI = new StringBuffer("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
    public static final StringBuffer rotorII = new StringBuffer("AJDKSIRUXBLHWTMCQGZNPYFVOE");
    public static final StringBuffer rotorIII = new StringBuffer("BDFHJLCPRTXVZNYEIWGAKMUSQO");
    public static final StringBuffer rotorIV = new StringBuffer("ESOVPZJAYQUIRHXLNFTGKDCMWB");
    public static final StringBuffer rotorV = new StringBuffer("VZBRGITYUPSDNHLXAWMJQOFECK");
    public static final StringBuffer rotorVI = new StringBuffer("JPGVOUMFYQBENHZRDKASXLICTW");
    public static final StringBuffer rotorVII = new StringBuffer("NZJHGRCXMYSWBOUFAIVLPEKQDT");
    public static final StringBuffer rotorVIII = new StringBuffer("JPGVOUMFYQBENHZRDKASXLICTW");
    private StringBuffer B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    public StringBuffer firstRotor;
    public StringBuffer firstRotorT;
    private StringBuffer hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    public char[] plugBoard = {'A', 'B', Access.CLASS_CONTEXT, 'D', 'E', Access.FIELD_CONTEXT, 'G', 'H', Access.INNERCLASS_CONTEXT, 'J', 'K', 'L', Access.METHOD_CONTEXT, 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public StringBuffer reflector;
    public StringBuffer secondRotor;
    public StringBuffer secondRotorT;
    public StringBuffer thirdRotor;
    public StringBuffer thirdRotorT;

    public KodularEnigma(String str, String str2, String str3, String str4) {
        StringBuffer stringBuffer = reflector0;
        this.firstRotorT = new StringBuffer(stringBuffer.toString());
        this.secondRotorT = new StringBuffer(stringBuffer.toString());
        this.thirdRotorT = new StringBuffer(stringBuffer.toString());
        this.firstRotor = getValue(str)[0];
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getValue(str)[1];
        this.secondRotor = getValue(str2)[0];
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = getValue(str2)[1];
        this.thirdRotor = getValue(str3)[0];
        this.reflector = getValue(str4)[0];
    }

    public void setFirstRotor(String str) {
        this.firstRotor = getValue(str)[0];
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = getValue(str)[1];
    }

    public void setSecondRotor(String str) {
        this.secondRotor = getValue(str)[0];
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = getValue(str)[1];
    }

    public void setThirdRotor(String str) {
        this.thirdRotor = getValue(str)[0];
    }

    public void initialSettings(String str, String str2, String str3) {
        int indexOf = this.firstRotorT.toString().indexOf(str);
        StringBuffer stringBuffer = this.firstRotorT;
        stringBuffer.append(stringBuffer.substring(0, indexOf));
        this.firstRotorT.delete(0, indexOf);
        int indexOf2 = this.secondRotorT.toString().indexOf(str2);
        StringBuffer stringBuffer2 = this.secondRotorT;
        stringBuffer2.append(stringBuffer2.substring(0, indexOf2));
        this.secondRotorT.delete(0, indexOf2);
        int indexOf3 = this.thirdRotorT.toString().indexOf(str3);
        StringBuffer stringBuffer3 = this.thirdRotorT;
        stringBuffer3.append(stringBuffer3.substring(0, indexOf3));
        this.thirdRotorT.delete(0, indexOf3);
    }

    public void setPlugBoard(char c, char c2) {
        int i = 0;
        while (true) {
            char[] cArr = this.plugBoard;
            if (i < cArr.length) {
                char c3 = cArr[i];
                if (c3 == c) {
                    cArr[i] = c2;
                } else if (c3 == c2) {
                    cArr[i] = c;
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setPlugBoard(java.lang.String r9) {
        /*
            r8 = this;
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = " "
            r0.<init>(r9, r1)
        L_0x0007:
            boolean r2 = r0.hasMoreTokens()
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0038
            java.lang.String r2 = r0.nextToken()
            int r6 = r2.length()
            if (r6 == r3) goto L_0x001b
            return r5
        L_0x001b:
            char r3 = r2.charAt(r5)
            r6 = 90
            if (r3 > r6) goto L_0x0037
            char r3 = r2.charAt(r5)
            r7 = 65
            if (r3 < r7) goto L_0x0037
            char r3 = r2.charAt(r4)
            if (r3 > r6) goto L_0x0037
            char r2 = r2.charAt(r4)
            if (r2 >= r7) goto L_0x0007
        L_0x0037:
            return r5
        L_0x0038:
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            r0.<init>(r9, r1)
        L_0x003d:
            boolean r9 = r0.hasMoreTokens()
            if (r9 == 0) goto L_0x005a
            java.lang.String r9 = r0.nextToken()
            int r1 = r9.length()
            if (r1 != r3) goto L_0x0059
            char r1 = r9.charAt(r5)
            char r9 = r9.charAt(r4)
            r8.setPlugBoard(r1, r9)
            goto L_0x003d
        L_0x0059:
            return r5
        L_0x005a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.crypt.KodularEnigma.setPlugBoard(java.lang.String):boolean");
    }

    public StringBuffer[] getValue(String str) {
        StringBuffer[] stringBufferArr = new StringBuffer[2];
        if (str.equals("RotorI") || str.equals("1")) {
            stringBufferArr[0] = rotorI;
            stringBufferArr[1] = notches[0];
            return stringBufferArr;
        } else if (str.equals("RotorII") || str.equals("2")) {
            stringBufferArr[0] = rotorII;
            stringBufferArr[1] = notches[1];
            return stringBufferArr;
        } else if (str.equals("RotorIII") || str.equals("3")) {
            stringBufferArr[0] = rotorIII;
            stringBufferArr[1] = notches[2];
            return stringBufferArr;
        } else if (str.equals("RotorIV") || str.equals("4")) {
            stringBufferArr[0] = rotorIV;
            stringBufferArr[1] = notches[3];
            return stringBufferArr;
        } else if (str.equals("RotorV") || str.equals("5")) {
            stringBufferArr[0] = rotorV;
            stringBufferArr[1] = notches[4];
            return stringBufferArr;
        } else if (str.equals("RotorVI") || str.equals("6")) {
            stringBufferArr[0] = rotorVI;
            stringBufferArr[1] = notches[5];
            return stringBufferArr;
        } else if (str.equals("RotorVII") || str.equals("7")) {
            stringBufferArr[0] = rotorVII;
            stringBufferArr[1] = notches[6];
            return stringBufferArr;
        } else if (str.equals("RotorVIII") || str.equals("8")) {
            stringBufferArr[0] = rotorVIII;
            stringBufferArr[1] = notches[7];
            return stringBufferArr;
        } else if (str.equals("ReflectorB") || str.equals("B")) {
            stringBufferArr[0] = reflectorB;
            stringBufferArr[1] = new StringBuffer("");
            return stringBufferArr;
        } else if (str.equals("ReflectorC") || str.equals("C")) {
            stringBufferArr[0] = reflectorC;
            stringBufferArr[1] = new StringBuffer("");
            return stringBufferArr;
        } else if (str.equals("No Reflector") || str.equals("0")) {
            stringBufferArr[0] = reflector0;
            stringBufferArr[1] = new StringBuffer("");
            return stringBufferArr;
        } else {
            return new StringBuffer[]{new StringBuffer("ERROR"), new StringBuffer("")};
        }
    }

    public char rotorOne(char c) {
        return this.firstRotor.charAt(this.firstRotorT.toString().indexOf(c));
    }

    public char rotorTwo(char c) {
        return this.secondRotor.charAt(this.secondRotorT.toString().indexOf(c));
    }

    public char rotorThree(char c) {
        return this.thirdRotor.charAt(this.thirdRotorT.toString().indexOf(c));
    }

    public char reflector(char c) {
        return this.reflector.charAt(c - 'A');
    }

    public char IrotorOne(char c) {
        return this.firstRotorT.charAt(this.firstRotor.toString().indexOf(c));
    }

    public char IrotorTwo(char c) {
        return this.secondRotorT.charAt(this.secondRotor.toString().indexOf(c));
    }

    public char IrotorThree(char c) {
        return this.thirdRotorT.charAt(this.thirdRotor.toString().indexOf(c));
    }

    public void rotate() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstRotorT.charAt(0));
        StringBuffer stringBuffer = new StringBuffer(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.secondRotorT.charAt(0));
        StringBuffer stringBuffer2 = new StringBuffer(sb2.toString());
        StringBuffer stringBuffer3 = this.firstRotorT;
        stringBuffer3.append(stringBuffer3.charAt(0));
        this.firstRotorT.delete(0, 1);
        if (stringBuffer.toString().equals(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toString())) {
            StringBuffer stringBuffer4 = this.secondRotorT;
            stringBuffer4.append(stringBuffer4.charAt(0));
            this.secondRotorT.delete(0, 1);
            if (stringBuffer2.toString().equals(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.toString())) {
                StringBuffer stringBuffer5 = this.thirdRotorT;
                stringBuffer5.append(stringBuffer5.charAt(0));
                this.thirdRotorT.delete(0, 1);
            }
        }
    }

    public char plugBoard(char c) {
        return this.plugBoard[c - 'A'];
    }

    public char getFRSetting() {
        return this.firstRotorT.charAt(0);
    }

    public char getSRSetting() {
        return this.secondRotorT.charAt(0);
    }

    public char getTRSetting() {
        return this.thirdRotorT.charAt(0);
    }

    public static String encrypt(String str, int i, int i2, int i3, String str2, String str3) {
        KodularEnigma kodularEnigma = new KodularEnigma(String.valueOf(i), String.valueOf(i2), String.valueOf(i3), str2);
        String str4 = "";
        if (str3 != str4) {
            kodularEnigma.setPlugBoard(str3);
        }
        String upperCase = str.toUpperCase();
        for (int i4 = 0; i4 < upperCase.length(); i4++) {
            char charAt = upperCase.charAt(i4);
            if (charAt <= 'Z' && charAt >= 'A') {
                kodularEnigma.rotate();
                str4 = str4 + kodularEnigma.plugBoard(kodularEnigma.IrotorOne(kodularEnigma.IrotorTwo(kodularEnigma.IrotorThree(kodularEnigma.reflector(kodularEnigma.rotorThree(kodularEnigma.rotorTwo(kodularEnigma.rotorOne(kodularEnigma.plugBoard(charAt)))))))));
            } else if (charAt != ' ') {
                return null;
            } else {
                str4 = str4 + charAt;
            }
        }
        return str4;
    }

    public static boolean pbParser(String str) {
        if (str.length() > 0 && !str.equals((Object) null) && str != null) {
            int i = 0;
            while (i < str.length() - 1) {
                if (str.charAt(i) > 'Z' || str.charAt(i) < 'A') {
                    i++;
                } else if (str.substring(i + 1).indexOf(str.charAt(i)) != -1) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }
}
