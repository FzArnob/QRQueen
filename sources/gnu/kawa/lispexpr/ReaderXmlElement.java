package gnu.kawa.lispexpr;

import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.kawa.xml.CommentConstructor;
import gnu.kawa.xml.MakeCDATA;
import gnu.kawa.xml.MakeProcInst;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

public class ReaderXmlElement extends ReadTableEntry {
    static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        LispReader lispReader = (LispReader) lexer;
        return readXMLConstructor(lispReader, lispReader.readUnicodeChar(), false);
    }

    public static Pair quote(Object obj) {
        return LList.list2(Namespace.EmptyNamespace.getSymbol(LispLanguage.quote_sym), obj);
    }

    public static Object readQNameExpression(LispReader lispReader, int i, boolean z) throws IOException, SyntaxException {
        String str;
        lispReader.getName();
        int lineNumber = lispReader.getLineNumber() + 1;
        int columnNumber = lispReader.getColumnNumber();
        lispReader.tokenBufferLength = 0;
        if (XName.isNameStart(i)) {
            int i2 = -1;
            while (true) {
                lispReader.tokenBufferAppend(i);
                i = lispReader.readUnicodeChar();
                if (i == 58 && i2 < 0) {
                    i2 = lispReader.tokenBufferLength;
                } else if (!XName.isNamePart(i)) {
                    break;
                }
            }
            lispReader.unread(i);
            if (i2 < 0 && !z) {
                return quote(Namespace.getDefaultSymbol(lispReader.tokenBufferString().intern()));
            }
            String intern = new String(lispReader.tokenBuffer, i2 + 1, (lispReader.tokenBufferLength - i2) - 1).intern();
            if (i2 < 0) {
                str = DEFAULT_ELEMENT_NAMESPACE;
            } else {
                str = new String(lispReader.tokenBuffer, 0, i2).intern();
            }
            return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(Namespace.EmptyNamespace.getSymbol(str), new Pair(intern, LList.Empty), lispReader.getName(), lineNumber, columnNumber));
        } else if (i == 123 || i == 40) {
            return readEscapedExpression(lispReader, i);
        } else {
            lispReader.error("missing element name");
            return null;
        }
    }

    static Object readEscapedExpression(LispReader lispReader, int i) throws IOException, SyntaxException {
        if (i == 40) {
            lispReader.unread(i);
            return lispReader.readObject();
        }
        LineBufferedReader port = lispReader.getPort();
        char pushNesting = lispReader.pushNesting('{');
        int lineNumber = port.getLineNumber();
        int columnNumber = port.getColumnNumber();
        try {
            Pair makePair = lispReader.makePair(new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1)), lineNumber, columnNumber);
            ReadTable current = ReadTable.getCurrent();
            Pair pair = makePair;
            while (true) {
                int lineNumber2 = port.getLineNumber();
                int columnNumber2 = port.getColumnNumber();
                int read = port.read();
                if (read == 125) {
                    break;
                }
                if (read < 0) {
                    lispReader.eofError("unexpected EOF in list starting here", lineNumber + 1, columnNumber);
                }
                Object readValues = lispReader.readValues(read, current.lookup(read), current);
                if (readValues != Values.empty) {
                    Pair makePair2 = lispReader.makePair(lispReader.handlePostfix(readValues, current, lineNumber2, columnNumber2), lineNumber2, columnNumber2);
                    lispReader.setCdr(pair, makePair2);
                    pair = makePair2;
                }
            }
            lispReader.tokenBufferLength = 0;
            if (pair == makePair.getCdr()) {
                return pair.getCar();
            }
            lispReader.popNesting(pushNesting);
            return makePair;
        } finally {
            lispReader.popNesting(pushNesting);
        }
    }

    static Object readXMLConstructor(LispReader lispReader, int i, boolean z) throws IOException, SyntaxException {
        int lineNumber = lispReader.getLineNumber() + 1;
        int columnNumber = lispReader.getColumnNumber() - 2;
        if (i == 33) {
            int read = lispReader.read();
            if (read == 45 && (read = lispReader.peek()) == 45) {
                lispReader.skip();
                if (!lispReader.readDelimited("-->")) {
                    lispReader.error('f', lispReader.getName(), lineNumber, columnNumber, "unexpected end-of-file in XML comment starting here - expected \"-->\"");
                }
                return LList.list2(CommentConstructor.commentConstructor, lispReader.tokenBufferString());
            } else if (read == 91 && (read = lispReader.read()) == 67 && (read = lispReader.read()) == 68 && (read = lispReader.read()) == 65 && (read = lispReader.read()) == 84 && (read = lispReader.read()) == 65 && (read = lispReader.read()) == 91) {
                if (!lispReader.readDelimited("]]>")) {
                    lispReader.error('f', lispReader.getName(), lineNumber, columnNumber, "unexpected end-of-file in CDATA starting here - expected \"]]>\"");
                }
                return LList.list2(MakeCDATA.makeCDATA, lispReader.tokenBufferString());
            } else {
                lispReader.error('f', lispReader.getName(), lineNumber, columnNumber, "'<!' must be followed by '--' or '[CDATA['");
                while (read >= 0 && read != 62 && read != 10 && read != 13) {
                    read = lispReader.read();
                }
                return null;
            }
        } else if (i != 63) {
            return readElementConstructor(lispReader, i);
        } else {
            int readUnicodeChar = lispReader.readUnicodeChar();
            if (readUnicodeChar < 0 || !XName.isNameStart(readUnicodeChar)) {
                lispReader.error("missing target after '<?'");
            }
            do {
                lispReader.tokenBufferAppend(readUnicodeChar);
                readUnicodeChar = lispReader.readUnicodeChar();
            } while (XName.isNamePart(readUnicodeChar));
            String str = lispReader.tokenBufferString();
            int i2 = 0;
            while (readUnicodeChar >= 0 && Character.isWhitespace(readUnicodeChar)) {
                i2++;
                readUnicodeChar = lispReader.read();
            }
            lispReader.unread(readUnicodeChar);
            char pushNesting = lispReader.pushNesting('?');
            try {
                if (!lispReader.readDelimited("?>")) {
                    lispReader.error('f', lispReader.getName(), lineNumber, columnNumber, "unexpected end-of-file looking for \"?>\"");
                }
                if (i2 == 0 && lispReader.tokenBufferLength > 0) {
                    lispReader.error("target must be followed by space or '?>'");
                }
                return LList.list3(MakeProcInst.makeProcInst, str, lispReader.tokenBufferString());
            } finally {
                lispReader.popNesting(pushNesting);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object readElementConstructor(gnu.kawa.lispexpr.LispReader r16, int r17) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r6 = r16
            int r0 = r16.getLineNumber()
            r1 = 1
            int r7 = r0 + 1
            int r0 = r16.getColumnNumber()
            r2 = 2
            int r8 = r0 + -2
            r0 = r17
            java.lang.Object r0 = readQNameExpression(r6, r0, r1)
            int r3 = r6.tokenBufferLength
            r4 = 0
            if (r3 != 0) goto L_0x001d
            r3 = r4
            goto L_0x0021
        L_0x001d:
            java.lang.String r3 = r16.tokenBufferString()
        L_0x0021:
            gnu.lists.LList r5 = gnu.lists.LList.Empty
            java.lang.String r9 = r16.getName()
            gnu.lists.PairWithPosition r9 = gnu.lists.PairWithPosition.make(r0, r5, r9, r7, r8)
            gnu.lists.LList r0 = gnu.lists.LList.Empty
            r10 = r0
            r0 = r9
        L_0x002f:
            int r5 = r16.readUnicodeChar()
            r11 = 0
            r12 = 0
        L_0x0035:
            if (r5 < 0) goto L_0x0043
            boolean r13 = java.lang.Character.isWhitespace(r5)
            if (r13 == 0) goto L_0x0043
            int r5 = r16.read()
            r12 = 1
            goto L_0x0035
        L_0x0043:
            r13 = 58
            r14 = 47
            r15 = 62
            if (r5 < 0) goto L_0x00fe
            if (r5 == r15) goto L_0x00fe
            if (r5 != r14) goto L_0x0051
            goto L_0x00fe
        L_0x0051:
            if (r12 != 0) goto L_0x0058
            java.lang.String r12 = "missing space before attribute"
            r6.error(r12)
        L_0x0058:
            java.lang.Object r5 = readQNameExpression(r6, r5, r11)
            r16.getLineNumber()
            r16.getColumnNumber()
            int r12 = r6.tokenBufferLength
            int r12 = r6.tokenBufferLength
            r14 = 5
            if (r12 < r14) goto L_0x00ac
            char[] r12 = r6.tokenBuffer
            char r11 = r12[r11]
            r12 = 120(0x78, float:1.68E-43)
            if (r11 != r12) goto L_0x00ac
            char[] r11 = r6.tokenBuffer
            char r11 = r11[r1]
            r12 = 109(0x6d, float:1.53E-43)
            if (r11 != r12) goto L_0x00ac
            char[] r11 = r6.tokenBuffer
            char r11 = r11[r2]
            r12 = 108(0x6c, float:1.51E-43)
            if (r11 != r12) goto L_0x00ac
            char[] r11 = r6.tokenBuffer
            r12 = 3
            char r11 = r11[r12]
            r12 = 110(0x6e, float:1.54E-43)
            if (r11 != r12) goto L_0x00ac
            char[] r11 = r6.tokenBuffer
            r12 = 4
            char r11 = r11[r12]
            r12 = 115(0x73, float:1.61E-43)
            if (r11 != r12) goto L_0x00ac
            int r11 = r6.tokenBufferLength
            if (r11 != r14) goto L_0x009a
            java.lang.String r11 = ""
            goto L_0x00ad
        L_0x009a:
            char[] r11 = r6.tokenBuffer
            char r11 = r11[r14]
            if (r11 != r13) goto L_0x00ac
            java.lang.String r11 = new java.lang.String
            char[] r12 = r6.tokenBuffer
            int r13 = r6.tokenBufferLength
            r14 = 6
            int r13 = r13 - r14
            r11.<init>(r12, r14, r13)
            goto L_0x00ad
        L_0x00ac:
            r11 = r4
        L_0x00ad:
            r12 = 32
            int r13 = skipSpace(r6, r12)
            r14 = 61
            if (r13 == r14) goto L_0x00bc
            java.lang.String r13 = "missing '=' after attribute"
            r6.error(r13)
        L_0x00bc:
            int r12 = skipSpace(r6, r12)
            gnu.kawa.xml.MakeAttribute r13 = gnu.kawa.xml.MakeAttribute.makeAttribute
            gnu.lists.LList r14 = gnu.lists.LList.Empty
            java.lang.String r15 = r16.getName()
            gnu.lists.PairWithPosition r13 = gnu.lists.PairWithPosition.make(r13, r14, r15, r7, r8)
            gnu.lists.LList r14 = gnu.lists.LList.Empty
            java.lang.String r15 = r16.getName()
            gnu.lists.PairWithPosition r5 = gnu.lists.PairWithPosition.make(r5, r14, r15, r7, r8)
            r6.setCdr(r13, r5)
            char r12 = (char) r12
            readContent(r6, r12, r5)
            if (r11 == 0) goto L_0x00ef
            gnu.lists.PairWithPosition r12 = new gnu.lists.PairWithPosition
            java.lang.Object r13 = r5.getCdr()
            gnu.lists.Pair r11 = gnu.lists.Pair.make(r11, r13)
            r12.<init>(r5, r11, r10)
            r10 = r12
            goto L_0x002f
        L_0x00ef:
            java.lang.Object r5 = r16.makeNil()
            r11 = -1
            gnu.lists.PairWithPosition r5 = gnu.lists.PairWithPosition.make(r13, r5, r4, r11, r11)
            r0.setCdrBackdoor(r5)
            r0 = r5
            goto L_0x002f
        L_0x00fe:
            if (r5 != r14) goto L_0x010b
            int r5 = r16.read()
            if (r5 != r15) goto L_0x0108
            r2 = 1
            goto L_0x010c
        L_0x0108:
            r6.unread(r5)
        L_0x010b:
            r2 = 0
        L_0x010c:
            if (r2 != 0) goto L_0x01ac
            if (r5 == r15) goto L_0x0118
            java.lang.String r0 = "missing '>' after start element"
            r6.error(r0)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            return r0
        L_0x0118:
            r2 = 60
            readContent(r6, r2, r0)
            int r0 = r16.readUnicodeChar()
            boolean r2 = gnu.xml.XName.isNameStart(r0)
            if (r2 == 0) goto L_0x019f
            r6.tokenBufferLength = r11
        L_0x0129:
            r6.tokenBufferAppend(r0)
            int r12 = r16.readUnicodeChar()
            boolean r0 = gnu.xml.XName.isNamePart(r12)
            if (r0 != 0) goto L_0x019b
            if (r12 == r13) goto L_0x019b
            java.lang.String r0 = r16.tokenBufferString()
            if (r3 == 0) goto L_0x0144
            boolean r2 = r0.equals(r3)
            if (r2 != 0) goto L_0x0197
        L_0x0144:
            r2 = 101(0x65, float:1.42E-43)
            java.lang.String r4 = r16.getName()
            int r5 = r16.getLineNumber()
            int r5 = r5 + r1
            int r1 = r16.getColumnNumber()
            int r13 = r6.tokenBufferLength
            int r13 = r1 - r13
            java.lang.String r1 = ">'"
            if (r3 != 0) goto L_0x0170
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r14 = "computed start tag closed by '</"
            r3.append(r14)
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            goto L_0x018c
        L_0x0170:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "'<"
            r14.append(r15)
            r14.append(r3)
            java.lang.String r3 = ">' closed by '</"
            r14.append(r3)
            r14.append(r0)
            r14.append(r1)
            java.lang.String r0 = r14.toString()
        L_0x018c:
            r14 = r0
            r0 = r16
            r1 = r2
            r2 = r4
            r3 = r5
            r4 = r13
            r5 = r14
            r0.error(r1, r2, r3, r4, r5)
        L_0x0197:
            r6.tokenBufferLength = r11
            r0 = r12
            goto L_0x019f
        L_0x019b:
            r0 = r12
            r15 = 62
            goto L_0x0129
        L_0x019f:
            int r0 = skipSpace(r6, r0)
            r1 = 62
            if (r0 == r1) goto L_0x01ac
            java.lang.String r0 = "missing '>' after end element"
            r6.error(r0)
        L_0x01ac:
            gnu.lists.LList r0 = gnu.lists.LList.reverseInPlace(r10)
            gnu.kawa.lispexpr.MakeXmlElement r1 = gnu.kawa.lispexpr.MakeXmlElement.makeXml
            gnu.lists.Pair r0 = gnu.lists.Pair.make(r0, r9)
            java.lang.String r2 = r16.getName()
            gnu.lists.PairWithPosition r0 = gnu.lists.PairWithPosition.make(r1, r0, r2, r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderXmlElement.readElementConstructor(gnu.kawa.lispexpr.LispReader, int):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.lists.Pair readContent(gnu.kawa.lispexpr.LispReader r9, char r10, gnu.lists.PairWithPosition r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = 0
            r9.tokenBufferLength = r0
            r1 = 0
        L_0x0004:
            int r2 = r9.getLineNumber()
            r3 = 1
            int r2 = r2 + r3
            int r4 = r9.getColumnNumber()
            int r5 = r9.read()
            r6 = 0
            if (r5 >= 0) goto L_0x0021
            java.lang.String r3 = "unexpected end-of-file"
            r9.error(r3)
            java.lang.Object r3 = gnu.expr.Special.eof
            r5 = r3
            r3 = r1
        L_0x001e:
            r1 = r6
            goto L_0x00b0
        L_0x0021:
            r7 = 60
            if (r5 != r10) goto L_0x0056
            if (r10 != r7) goto L_0x0043
            int r1 = r9.tokenBufferLength
            if (r1 <= 0) goto L_0x0032
            java.lang.String r1 = r9.tokenBufferString()
            r9.tokenBufferLength = r0
            goto L_0x0033
        L_0x0032:
            r1 = r6
        L_0x0033:
            int r5 = r9.read()
            r7 = 47
            if (r5 != r7) goto L_0x003e
            java.lang.Object r3 = gnu.expr.Special.eof
            goto L_0x0053
        L_0x003e:
            java.lang.Object r3 = readXMLConstructor(r9, r5, r3)
            goto L_0x0053
        L_0x0043:
            boolean r1 = r9.checkNext(r10)
            if (r1 == 0) goto L_0x004f
            r9.tokenBufferAppend(r10)
            r1 = r6
            r3 = r1
            goto L_0x0053
        L_0x004f:
            java.lang.Object r1 = gnu.expr.Special.eof
            r3 = r1
            r1 = r6
        L_0x0053:
            r5 = r3
            r3 = 0
            goto L_0x00b0
        L_0x0056:
            r8 = 38
            if (r5 != r8) goto L_0x0090
            int r5 = r9.read()
            r7 = 35
            if (r5 != r7) goto L_0x0066
            readCharRef(r9)
            goto L_0x00ae
        L_0x0066:
            r7 = 40
            if (r5 == r7) goto L_0x007c
            r7 = 123(0x7b, float:1.72E-43)
            if (r5 != r7) goto L_0x006f
            goto L_0x007c
        L_0x006f:
            java.lang.Object r5 = readEntity(r9, r5)
            if (r1 == 0) goto L_0x001e
            int r1 = r9.tokenBufferLength
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = ""
            goto L_0x00b0
        L_0x007c:
            int r7 = r9.tokenBufferLength
            if (r7 > 0) goto L_0x0085
            if (r1 == 0) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r1 = r6
            goto L_0x0089
        L_0x0085:
            java.lang.String r1 = r9.tokenBufferString()
        L_0x0089:
            r9.tokenBufferLength = r0
            java.lang.Object r5 = readEscapedExpression(r9, r5)
            goto L_0x00b0
        L_0x0090:
            if (r10 == r7) goto L_0x00a0
            r3 = 9
            if (r5 == r3) goto L_0x009e
            r3 = 10
            if (r5 == r3) goto L_0x009e
            r3 = 13
            if (r5 != r3) goto L_0x00a0
        L_0x009e:
            r5 = 32
        L_0x00a0:
            if (r5 != r7) goto L_0x00a9
            r3 = 101(0x65, float:1.42E-43)
            java.lang.String r7 = "'<' must be quoted in a direct attribute value"
            r9.error(r3, r7)
        L_0x00a9:
            char r3 = (char) r5
            r9.tokenBufferAppend(r3)
            r3 = r1
        L_0x00ae:
            r1 = r6
            r5 = r1
        L_0x00b0:
            if (r5 == 0) goto L_0x00bc
            int r7 = r9.tokenBufferLength
            if (r7 <= 0) goto L_0x00bc
            java.lang.String r1 = r9.tokenBufferString()
            r9.tokenBufferLength = r0
        L_0x00bc:
            if (r1 == 0) goto L_0x00d1
            gnu.kawa.xml.MakeText r7 = gnu.kawa.xml.MakeText.makeText
            gnu.lists.Pair r1 = gnu.lists.Pair.list2(r7, r1)
            java.lang.Object r7 = r9.makeNil()
            r8 = -1
            gnu.lists.PairWithPosition r1 = gnu.lists.PairWithPosition.make(r1, r7, r6, r8, r8)
            r11.setCdrBackdoor(r1)
            r11 = r1
        L_0x00d1:
            java.lang.Object r1 = gnu.expr.Special.eof
            if (r5 != r1) goto L_0x00d6
            return r11
        L_0x00d6:
            if (r5 == 0) goto L_0x00e4
            java.lang.Object r1 = r9.makeNil()
            gnu.lists.PairWithPosition r1 = gnu.lists.PairWithPosition.make(r5, r1, r6, r2, r4)
            r11.setCdrBackdoor(r1)
            r11 = r1
        L_0x00e4:
            r1 = r3
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderXmlElement.readContent(gnu.kawa.lispexpr.LispReader, char, gnu.lists.Pair):gnu.lists.Pair");
    }

    static void readCharRef(LispReader lispReader) throws IOException, SyntaxException {
        int i;
        int i2;
        int read = lispReader.read();
        if (read == 120) {
            i2 = 16;
            i = lispReader.read();
        } else {
            i = read;
            i2 = 10;
        }
        int i3 = 0;
        while (i >= 0) {
            int digit = Character.digit((char) i, i2);
            if (digit < 0 || i3 >= 134217728) {
                break;
            }
            i3 = (i3 * i2) + digit;
            i = lispReader.read();
        }
        if (i != 59) {
            lispReader.unread(i);
            lispReader.error("invalid character reference");
        } else if ((i3 <= 0 || i3 > 55295) && ((i3 < 57344 || i3 > 65533) && (i3 < 65536 || i3 > 1114111))) {
            lispReader.error("invalid character value " + i3);
        } else {
            lispReader.tokenBufferAppend(i3);
        }
    }

    static Object readEntity(LispReader lispReader, int i) throws IOException, SyntaxException {
        int i2 = lispReader.tokenBufferLength;
        while (i >= 0) {
            char c = (char) i;
            if (!XName.isNamePart(c)) {
                break;
            }
            lispReader.tokenBufferAppend(c);
            i = lispReader.read();
        }
        if (i != 59) {
            lispReader.unread(i);
            lispReader.error("invalid entity reference");
            return "?";
        }
        String str = new String(lispReader.tokenBuffer, i2, lispReader.tokenBufferLength - i2);
        lispReader.tokenBufferLength = i2;
        namedEntity(lispReader, str);
        return null;
    }

    public static void namedEntity(LispReader lispReader, String str) {
        int i;
        String intern = str.intern();
        if (intern == "lt") {
            i = 60;
        } else if (intern == "gt") {
            i = 62;
        } else if (intern == "amp") {
            i = 38;
        } else if (intern == "quot") {
            i = 34;
        } else if (intern == "apos") {
            i = 39;
        } else {
            lispReader.error("unknown enity reference: '" + intern + "'");
            i = 63;
        }
        lispReader.tokenBufferAppend(i);
    }

    static int skipSpace(LispReader lispReader, int i) throws IOException, SyntaxException {
        while (i >= 0 && Character.isWhitespace(i)) {
            i = lispReader.readUnicodeChar();
        }
        return i;
    }
}
