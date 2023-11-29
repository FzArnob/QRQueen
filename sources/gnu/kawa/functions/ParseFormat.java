package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Procedure1;
import gnu.text.LineBufferedReader;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.ParseException;

public class ParseFormat extends Procedure1 {
    public static final int PARAM_FROM_LIST = -1610612736;
    public static final int PARAM_UNSPECIFIED = -1073741824;
    public static final int SEEN_HASH = 16;
    public static final int SEEN_MINUS = 1;
    public static final int SEEN_PLUS = 2;
    public static final int SEEN_SPACE = 4;
    public static final int SEEN_ZERO = 8;
    public static final ParseFormat parseFormat = new ParseFormat(false);
    boolean emacsStyle;

    public ParseFormat(boolean z) {
        this.emacsStyle = z;
    }

    public ReportFormat parseFormat(LineBufferedReader lineBufferedReader) throws ParseException, IOException {
        return parseFormat(lineBufferedReader, this.emacsStyle ? '?' : '~');
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.text.ReportFormat parseFormat(gnu.text.LineBufferedReader r22, char r23) throws java.text.ParseException, java.io.IOException {
        /*
            r0 = r23
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r2 = 100
            r1.<init>(r2)
            java.util.Vector r3 = new java.util.Vector
            r3.<init>()
        L_0x000e:
            int r4 = r22.read()
            if (r4 < 0) goto L_0x0026
            if (r4 == r0) goto L_0x001b
            char r4 = (char) r4
            r1.append(r4)
            goto L_0x000e
        L_0x001b:
            int r4 = r22.read()
            if (r4 != r0) goto L_0x0026
            char r4 = (char) r4
            r1.append(r4)
            goto L_0x000e
        L_0x0026:
            int r5 = r1.length()
            r6 = 0
            if (r5 <= 0) goto L_0x003d
            char[] r7 = new char[r5]
            r1.getChars(r6, r5, r7, r6)
            r1.setLength(r6)
            gnu.text.LiteralFormat r5 = new gnu.text.LiteralFormat
            r5.<init>((char[]) r7)
            r3.addElement(r5)
        L_0x003d:
            r5 = 1
            if (r4 >= 0) goto L_0x005c
            int r0 = r3.size()
            if (r0 != r5) goto L_0x0051
            java.lang.Object r1 = r3.elementAt(r6)
            boolean r2 = r1 instanceof gnu.text.ReportFormat
            if (r2 == 0) goto L_0x0051
            gnu.text.ReportFormat r1 = (gnu.text.ReportFormat) r1
            return r1
        L_0x0051:
            java.text.Format[] r0 = new java.text.Format[r0]
            r3.copyInto(r0)
            gnu.text.CompoundFormat r1 = new gnu.text.CompoundFormat
            r1.<init>(r0)
            return r1
        L_0x005c:
            r7 = 36
            r8 = -1
            r9 = 10
            if (r4 != r7) goto L_0x0082
            int r4 = r22.read()
            char r4 = (char) r4
            int r4 = java.lang.Character.digit(r4, r9)
            if (r4 < 0) goto L_0x007a
        L_0x006e:
            int r4 = r22.read()
            char r7 = (char) r4
            int r7 = java.lang.Character.digit(r7, r9)
            if (r7 >= 0) goto L_0x006e
            goto L_0x0082
        L_0x007a:
            java.text.ParseException r0 = new java.text.ParseException
            java.lang.String r1 = "missing number (position) after '%$'"
            r0.<init>(r1, r8)
            throw r0
        L_0x0082:
            r7 = 0
        L_0x0083:
            char r10 = (char) r4
            r11 = 32
            if (r10 == r11) goto L_0x01a9
            r12 = 35
            if (r10 == r12) goto L_0x01a6
            r12 = 43
            if (r10 == r12) goto L_0x01a3
            r12 = 45
            if (r10 == r12) goto L_0x01a0
            r12 = 48
            if (r10 == r12) goto L_0x019d
            int r10 = java.lang.Character.digit(r10, r9)
            r13 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r14 = 42
            r15 = -1073741824(0xffffffffc0000000, float:-2.0)
            if (r10 < 0) goto L_0x00b5
        L_0x00a4:
            int r4 = r22.read()
            char r5 = (char) r4
            int r5 = java.lang.Character.digit(r5, r9)
            if (r5 >= 0) goto L_0x00b0
            goto L_0x00bc
        L_0x00b0:
            int r10 = r10 * 10
            int r10 = r10 + r5
            r5 = 1
            goto L_0x00a4
        L_0x00b5:
            if (r4 != r14) goto L_0x00ba
            r10 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            goto L_0x00bc
        L_0x00ba:
            r10 = -1073741824(0xffffffffc0000000, float:-2.0)
        L_0x00bc:
            r5 = 46
            if (r4 != r5) goto L_0x00d4
            if (r4 != r14) goto L_0x00c3
            goto L_0x00d6
        L_0x00c3:
            r13 = 0
        L_0x00c4:
            int r4 = r22.read()
            char r5 = (char) r4
            int r5 = java.lang.Character.digit(r5, r9)
            if (r5 >= 0) goto L_0x00d0
            goto L_0x00d6
        L_0x00d0:
            int r13 = r13 * 10
            int r13 = r13 + r5
            goto L_0x00c4
        L_0x00d4:
            r13 = -1073741824(0xffffffffc0000000, float:-2.0)
        L_0x00d6:
            r5 = 83
            if (r4 == r5) goto L_0x0170
            r14 = 111(0x6f, float:1.56E-43)
            r9 = 105(0x69, float:1.47E-43)
            r11 = 88
            if (r4 == r11) goto L_0x0114
            if (r4 == r9) goto L_0x0114
            if (r4 == r14) goto L_0x0114
            r12 = 115(0x73, float:1.61E-43)
            if (r4 == r12) goto L_0x0170
            r5 = 120(0x78, float:1.68E-43)
            if (r4 == r5) goto L_0x0114
            switch(r4) {
                case 100: goto L_0x0114;
                case 101: goto L_0x010d;
                case 102: goto L_0x010d;
                case 103: goto L_0x010d;
                default: goto L_0x00f1;
            }
        L_0x00f1:
            java.text.ParseException r0 = new java.text.ParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown format character '"
            r1.append(r2)
            r1.append(r4)
            java.lang.String r2 = "'"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1, r8)
            throw r0
        L_0x010d:
            gnu.kawa.functions.ObjectFormat r4 = new gnu.kawa.functions.ObjectFormat
            r4.<init>(r6)
            goto L_0x017b
        L_0x0114:
            r5 = 16
            r12 = 8
            if (r4 == r2) goto L_0x012c
            if (r4 != r9) goto L_0x011d
            goto L_0x012c
        L_0x011d:
            if (r4 != r14) goto L_0x0123
            r4 = 0
            r16 = 8
            goto L_0x012f
        L_0x0123:
            if (r4 != r11) goto L_0x0128
            r4 = 32
            goto L_0x0129
        L_0x0128:
            r4 = 0
        L_0x0129:
            r16 = 16
            goto L_0x012f
        L_0x012c:
            r4 = 0
            r16 = 10
        L_0x012f:
            r5 = r7 & 9
            if (r5 != r12) goto L_0x0136
            r18 = 48
            goto L_0x0138
        L_0x0136:
            r18 = 32
        L_0x0138:
            r5 = r7 & 16
            if (r5 == 0) goto L_0x013e
            r4 = r4 | 8
        L_0x013e:
            r5 = r7 & 2
            if (r5 == 0) goto L_0x0144
            r4 = r4 | 2
        L_0x0144:
            r5 = r7 & 1
            if (r5 == 0) goto L_0x014a
            r4 = r4 | 16
        L_0x014a:
            r5 = r7 & 4
            if (r5 == 0) goto L_0x0150
            r4 = r4 | 4
        L_0x0150:
            r21 = r4
            if (r13 == r15) goto L_0x0165
            r7 = r7 & -9
            r21 = r21 | 64
            r18 = 48
            r19 = -1073741824(0xffffffffc0000000, float:-2.0)
            r20 = -1073741824(0xffffffffc0000000, float:-2.0)
            r17 = r13
            java.text.Format r4 = gnu.kawa.functions.IntegerFormat.getInstance(r16, r17, r18, r19, r20, r21)
            goto L_0x017b
        L_0x0165:
            r19 = -1073741824(0xffffffffc0000000, float:-2.0)
            r20 = -1073741824(0xffffffffc0000000, float:-2.0)
            r17 = r10
            java.text.Format r4 = gnu.kawa.functions.IntegerFormat.getInstance(r16, r17, r18, r19, r20, r21)
            goto L_0x017b
        L_0x0170:
            gnu.kawa.functions.ObjectFormat r9 = new gnu.kawa.functions.ObjectFormat
            if (r4 != r5) goto L_0x0176
            r5 = 1
            goto L_0x0177
        L_0x0176:
            r5 = 0
        L_0x0177:
            r9.<init>(r5, r13)
            r4 = r9
        L_0x017b:
            if (r10 <= 0) goto L_0x0198
            r5 = r7 & 8
            if (r5 == 0) goto L_0x0184
            r11 = 48
            goto L_0x0186
        L_0x0184:
            r11 = 32
        L_0x0186:
            r5 = r7 & 1
            if (r5 == 0) goto L_0x018d
            r6 = 100
            goto L_0x0192
        L_0x018d:
            r5 = 48
            if (r11 != r5) goto L_0x0192
            r6 = -1
        L_0x0192:
            gnu.text.PadFormat r5 = new gnu.text.PadFormat
            r5.<init>(r4, r10, r11, r6)
            r4 = r5
        L_0x0198:
            r3.addElement(r4)
            goto L_0x000e
        L_0x019d:
            r4 = r7 | 8
            goto L_0x01ab
        L_0x01a0:
            r4 = r7 | 1
            goto L_0x01ab
        L_0x01a3:
            r4 = r7 | 2
            goto L_0x01ab
        L_0x01a6:
            r4 = r7 | 16
            goto L_0x01ab
        L_0x01a9:
            r4 = r7 | 4
        L_0x01ab:
            r7 = r4
            int r4 = r22.read()
            r5 = 1
            r9 = 10
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.ParseFormat.parseFormat(gnu.text.LineBufferedReader, char):gnu.text.ReportFormat");
    }

    public Object apply1(Object obj) {
        return asFormat(obj, this.emacsStyle ? '?' : '~');
    }

    public static ReportFormat asFormat(Object obj, char c) {
        CharArrayInPort charArrayInPort;
        try {
            if (obj instanceof ReportFormat) {
                return (ReportFormat) obj;
            }
            if (c == '~') {
                return new LispFormat(obj.toString());
            }
            if (obj instanceof FString) {
                FString fString = (FString) obj;
                charArrayInPort = new CharArrayInPort(fString.data, fString.size);
            } else {
                charArrayInPort = new CharArrayInPort(obj.toString());
            }
            ReportFormat parseFormat2 = parseFormat(charArrayInPort, c);
            charArrayInPort.close();
            return parseFormat2;
        } catch (IOException e) {
            throw new RuntimeException("Error parsing format (" + e + ")");
        } catch (ParseException e2) {
            throw new RuntimeException("Invalid format (" + e2 + ")");
        } catch (IndexOutOfBoundsException unused) {
            throw new RuntimeException("End while parsing format");
        } catch (Throwable th) {
            charArrayInPort.close();
            throw th;
        }
    }
}
