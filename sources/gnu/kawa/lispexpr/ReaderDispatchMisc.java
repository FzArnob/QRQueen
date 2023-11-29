package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.regex.Pattern;

public class ReaderDispatchMisc extends ReadTableEntry {
    private static ReaderDispatchMisc instance = new ReaderDispatchMisc();
    protected int code;

    public static ReaderDispatchMisc getInstance() {
        return instance;
    }

    public ReaderDispatchMisc() {
        this.code = -1;
    }

    public ReaderDispatchMisc(int i) {
        this.code = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: char} */
    /* JADX WARNING: type inference failed for: r3v0, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object read(gnu.text.Lexer r9, int r10, int r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r8 = this;
            r0 = r9
            gnu.kawa.lispexpr.LispReader r0 = (gnu.kawa.lispexpr.LispReader) r0
            int r1 = r8.code
            if (r1 < 0) goto L_0x0008
            r10 = r1
        L_0x0008:
            r1 = 35
            r2 = 2
            r3 = 0
            switch(r10) {
                case 33: goto L_0x01d9;
                case 35: goto L_0x01be;
                case 44: goto L_0x0111;
                case 47: goto L_0x010c;
                case 58: goto L_0x00e8;
                case 59: goto L_0x00c3;
                case 61: goto L_0x00a8;
                case 66: goto L_0x00a3;
                case 68: goto L_0x009c;
                case 69: goto L_0x0091;
                case 70: goto L_0x007c;
                case 73: goto L_0x0091;
                case 79: goto L_0x0075;
                case 82: goto L_0x0051;
                case 83: goto L_0x004b;
                case 84: goto L_0x0048;
                case 85: goto L_0x004b;
                case 88: goto L_0x0041;
                case 92: goto L_0x003c;
                case 124: goto L_0x0017;
                default: goto L_0x000f;
            }
        L_0x000f:
            java.lang.String r10 = "An invalid #-construct was read."
            r9.error(r10)
            gnu.mapping.Values r9 = gnu.mapping.Values.empty
            return r9
        L_0x0017:
            gnu.text.LineBufferedReader r9 = r0.getPort()
            boolean r10 = r9 instanceof gnu.mapping.InPort
            r11 = 124(0x7c, float:1.74E-43)
            if (r10 == 0) goto L_0x0028
            r2 = r9
            gnu.mapping.InPort r2 = (gnu.mapping.InPort) r2
            char r3 = r2.readState
            r2.readState = r11
        L_0x0028:
            r0.readNestedComment(r1, r11)     // Catch:{ all -> 0x0034 }
            if (r10 == 0) goto L_0x0031
            gnu.mapping.InPort r9 = (gnu.mapping.InPort) r9
            r9.readState = r3
        L_0x0031:
            gnu.mapping.Values r9 = gnu.mapping.Values.empty
            return r9
        L_0x0034:
            r11 = move-exception
            if (r10 == 0) goto L_0x003b
            gnu.mapping.InPort r9 = (gnu.mapping.InPort) r9
            r9.readState = r3
        L_0x003b:
            throw r11
        L_0x003c:
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readCharacter(r0)
            return r9
        L_0x0041:
            r9 = 16
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r3, r0, r9)
            return r9
        L_0x0048:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            return r9
        L_0x004b:
            char r9 = (char) r10
            gnu.lists.SimpleVector r9 = gnu.kawa.lispexpr.LispReader.readSimpleVector(r0, r9)
            return r9
        L_0x0051:
            r10 = 36
            if (r11 <= r10) goto L_0x0070
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "the radix "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r11 = " is too big (max is 36)"
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r9.error(r11)
            r11 = 36
        L_0x0070:
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r3, r0, r11)
            return r9
        L_0x0075:
            r9 = 8
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r3, r0, r9)
            return r9
        L_0x007c:
            int r9 = r9.peek()
            char r9 = (char) r9
            boolean r9 = java.lang.Character.isDigit(r9)
            if (r9 == 0) goto L_0x008e
            r9 = 70
            gnu.lists.SimpleVector r9 = gnu.kawa.lispexpr.LispReader.readSimpleVector(r0, r9)
            return r9
        L_0x008e:
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            return r9
        L_0x0091:
            r0.tokenBufferAppend(r1)
            r0.tokenBufferAppend(r10)
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r2, r0, r3)
            return r9
        L_0x009c:
            r9 = 10
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r3, r0, r9)
            return r9
        L_0x00a3:
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readNumberWithRadix(r3, r0, r2)
            return r9
        L_0x00a8:
            java.lang.Object r10 = r0.readObject()
            boolean r9 = r9 instanceof gnu.kawa.lispexpr.LispReader
            if (r9 == 0) goto L_0x00c2
            gnu.kawa.util.GeneralHashTable<java.lang.Integer, java.lang.Object> r9 = r0.sharedStructureTable
            if (r9 != 0) goto L_0x00bb
            gnu.kawa.util.GeneralHashTable r9 = new gnu.kawa.util.GeneralHashTable
            r9.<init>()
            r0.sharedStructureTable = r9
        L_0x00bb:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r9.put(r11, r10)
        L_0x00c2:
            return r10
        L_0x00c3:
            gnu.text.LineBufferedReader r9 = r0.getPort()
            boolean r10 = r9 instanceof gnu.mapping.InPort
            if (r10 == 0) goto L_0x00d4
            r11 = r9
            gnu.mapping.InPort r11 = (gnu.mapping.InPort) r11
            char r3 = r11.readState
            r1 = 59
            r11.readState = r1
        L_0x00d4:
            r0.readObject()     // Catch:{ all -> 0x00e0 }
            if (r10 == 0) goto L_0x00dd
            gnu.mapping.InPort r9 = (gnu.mapping.InPort) r9
            r9.readState = r3
        L_0x00dd:
            gnu.mapping.Values r9 = gnu.mapping.Values.empty
            return r9
        L_0x00e0:
            r11 = move-exception
            if (r10 == 0) goto L_0x00e7
            gnu.mapping.InPort r9 = (gnu.mapping.InPort) r9
            r9.readState = r3
        L_0x00e7:
            throw r11
        L_0x00e8:
            int r9 = r0.tokenBufferLength
            int r10 = r0.read()
            r11 = 80
            gnu.kawa.lispexpr.ReadTable r1 = gnu.kawa.lispexpr.ReadTable.getCurrent()
            r0.readToken(r10, r11, r1)
            int r10 = r0.tokenBufferLength
            int r10 = r10 - r9
            java.lang.String r11 = new java.lang.String
            char[] r1 = r0.tokenBuffer
            r11.<init>(r1, r9, r10)
            r0.tokenBufferLength = r9
            java.lang.String r9 = r11.intern()
            gnu.expr.Keyword r9 = gnu.expr.Keyword.make(r9)
            return r9
        L_0x010c:
            java.util.regex.Pattern r9 = readRegex(r9, r10, r11)
            return r9
        L_0x0111:
            gnu.text.LineBufferedReader r10 = r0.getPort()
            int r10 = r10.peek()
            r11 = 40
            if (r10 != r11) goto L_0x01b6
            java.lang.Object r10 = r0.readObject()
            int r11 = gnu.lists.LList.listLength(r10, r3)
            if (r11 <= 0) goto L_0x01b6
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            java.lang.Object r0 = r10.getCar()
            boolean r0 = r0 instanceof gnu.mapping.Symbol
            if (r0 == 0) goto L_0x01b6
            java.lang.Object r0 = r10.getCar()
            java.lang.String r0 = r0.toString()
            gnu.kawa.lispexpr.ReadTable r1 = gnu.kawa.lispexpr.ReadTable.getCurrent()
            java.lang.Object r1 = r1.getReaderCtor(r0)
            if (r1 != 0) goto L_0x0158
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "unknown reader constructor "
            r10.append(r11)
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r9.error(r10)
            goto L_0x01bb
        L_0x0158:
            boolean r2 = r1 instanceof gnu.mapping.Procedure
            if (r2 != 0) goto L_0x0166
            boolean r2 = r1 instanceof gnu.bytecode.Type
            if (r2 != 0) goto L_0x0166
            java.lang.String r10 = "reader constructor must be procedure or type name"
            r9.error(r10)
            goto L_0x01bb
        L_0x0166:
            int r11 = r11 + -1
            boolean r2 = r1 instanceof gnu.bytecode.Type
            int r4 = r2 + r11
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Object r10 = r10.getCdr()
            r5 = 0
        L_0x0173:
            if (r5 >= r11) goto L_0x0186
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            int r6 = r2 + r5
            java.lang.Object r7 = r10.getCar()
            r4[r6] = r7
            java.lang.Object r10 = r10.getCdr()
            int r5 = r5 + 1
            goto L_0x0173
        L_0x0186:
            if (r2 <= 0) goto L_0x0191
            r4[r3] = r1     // Catch:{ all -> 0x0198 }
            gnu.kawa.reflect.Invoke r10 = gnu.kawa.reflect.Invoke.make     // Catch:{ all -> 0x0198 }
            java.lang.Object r9 = r10.applyN(r4)     // Catch:{ all -> 0x0198 }
            return r9
        L_0x0191:
            gnu.mapping.Procedure r1 = (gnu.mapping.Procedure) r1     // Catch:{ all -> 0x0198 }
            java.lang.Object r9 = r1.applyN(r4)     // Catch:{ all -> 0x0198 }
            return r9
        L_0x0198:
            r10 = move-exception
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r1 = "caught "
            r11.append(r1)
            r11.append(r10)
            java.lang.String r10 = " applying reader constructor "
            r11.append(r10)
            r11.append(r0)
            java.lang.String r10 = r11.toString()
            r9.error(r10)
            goto L_0x01bb
        L_0x01b6:
            java.lang.String r10 = "a non-empty list starting with a symbol must follow #,"
            r9.error(r10)
        L_0x01bb:
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            return r9
        L_0x01be:
            boolean r10 = r9 instanceof gnu.kawa.lispexpr.LispReader
            if (r10 == 0) goto L_0x01d1
            gnu.kawa.util.GeneralHashTable<java.lang.Integer, java.lang.Object> r10 = r0.sharedStructureTable
            if (r10 == 0) goto L_0x01d1
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r10 = r10.get(r11, r9)
            if (r10 == r9) goto L_0x01d1
            return r10
        L_0x01d1:
            java.lang.String r10 = "an unrecognized #n# back-reference was read"
            r9.error(r10)
            gnu.mapping.Values r9 = gnu.mapping.Values.empty
            return r9
        L_0x01d9:
            java.lang.Object r9 = gnu.kawa.lispexpr.LispReader.readSpecial(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderDispatchMisc.read(gnu.text.Lexer, int, int):java.lang.Object");
    }

    public static Pattern readRegex(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        char c;
        int i3 = lexer.tokenBufferLength;
        LineBufferedReader port = lexer.getPort();
        boolean z = port instanceof InPort;
        int i4 = 0;
        if (z) {
            InPort inPort = (InPort) port;
            c = inPort.readState;
            inPort.readState = '/';
        } else {
            c = 0;
        }
        while (true) {
            try {
                int read = port.read();
                if (read < 0) {
                    lexer.eofError("unexpected EOF in regex literal");
                }
                if (read == i) {
                    break;
                }
                if (read == 92) {
                    read = port.read();
                    if ((read != 32 && read != 9 && read != 13 && read != 10) || !(lexer instanceof LispReader) || (read = ((LispReader) lexer).readEscape(read)) != -2) {
                        if (read < 0) {
                            lexer.eofError("unexpected EOF in regex literal");
                        }
                        if (read != i) {
                            lexer.tokenBufferAppend(92);
                        }
                    }
                }
                lexer.tokenBufferAppend(read);
            } finally {
                lexer.tokenBufferLength = i3;
                if (z) {
                    ((InPort) port).readState = c;
                }
            }
        }
        String str = new String(lexer.tokenBuffer, i3, lexer.tokenBufferLength - i3);
        while (true) {
            int peek = lexer.peek();
            if (peek != 105) {
                if (peek != 73) {
                    if (peek != 115) {
                        if (peek != 83) {
                            if (peek != 109) {
                                if (peek != 77) {
                                    if (!Character.isLetter(peek)) {
                                        break;
                                    }
                                    lexer.error("unrecognized regex option '" + ((char) peek) + '\'');
                                    lexer.skip();
                                }
                            }
                            i4 |= 8;
                            lexer.skip();
                        }
                    }
                    i4 |= 32;
                    lexer.skip();
                }
            }
            i4 |= 66;
            lexer.skip();
        }
        return Pattern.compile(str, i4);
    }
}
