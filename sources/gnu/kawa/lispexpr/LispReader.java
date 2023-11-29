package gnu.kawa.lispexpr;

import gnu.bytecode.Access;
import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;

public class LispReader extends Lexer {
    static final int SCM_COMPLEX = 1;
    public static final int SCM_NUMBERS = 1;
    public static final char TOKEN_ESCAPE_CHAR = '￿';
    protected boolean seenEscapes;
    GeneralHashTable<Integer, Object> sharedStructureTable;

    public LispReader(LineBufferedReader lineBufferedReader) {
        super(lineBufferedReader);
    }

    public LispReader(LineBufferedReader lineBufferedReader, SourceMessages sourceMessages) {
        super(lineBufferedReader, sourceMessages);
    }

    public final void readNestedComment(char c, char c2) throws IOException, SyntaxException {
        int lineNumber = this.port.getLineNumber();
        int columnNumber = this.port.getColumnNumber();
        int i = 1;
        do {
            int read = read();
            if (read == 124) {
                read = read();
                if (read == c) {
                    i--;
                }
            } else if (read == c && (read = read()) == c2) {
                i++;
            }
            if (read < 0) {
                eofError("unexpected end-of-file in " + c + c2 + " comment starting here", lineNumber + 1, columnNumber - 1);
                return;
            }
        } while (i > 0);
    }

    static char getReadCase() {
        try {
            char charAt = Environment.getCurrent().get("symbol-read-case", (Object) "P").toString().charAt(0);
            if (charAt != 'P') {
                if (charAt == 'u') {
                    return 'U';
                }
                if (charAt == 'd' || charAt == 'l' || charAt == 'L') {
                    return 'D';
                }
                if (charAt == 'i') {
                    return Access.INNERCLASS_CONTEXT;
                }
            }
            return charAt;
        } catch (Exception unused) {
            return 'P';
        }
    }

    public Object readValues(int i, ReadTable readTable) throws IOException, SyntaxException {
        return readValues(i, readTable.lookup(i), readTable);
    }

    public Object readValues(int i, ReadTableEntry readTableEntry, ReadTable readTable) throws IOException, SyntaxException {
        int i2 = this.tokenBufferLength;
        this.seenEscapes = false;
        int kind = readTableEntry.getKind();
        if (kind == 0) {
            String str = "invalid character #\\" + ((char) i);
            if (this.interactive) {
                fatal(str);
            } else {
                error(str);
            }
            return Values.empty;
        } else if (kind == 1) {
            return Values.empty;
        } else {
            if (kind == 5 || kind == 6) {
                return readTableEntry.read(this, i, -1);
            }
            return readAndHandleToken(i, i2, readTable);
        }
    }

    /* access modifiers changed from: protected */
    public Object readAndHandleToken(int i, int i2, ReadTable readTable) throws IOException, SyntaxException {
        int i3;
        Object parseNumber;
        int i4 = i2;
        ReadTable readTable2 = readTable;
        readToken(i, getReadCase(), readTable2);
        int i5 = this.tokenBufferLength;
        if (!this.seenEscapes && (parseNumber = parseNumber(this.tokenBuffer, i2, i5 - i4, 0, 0, 1)) != null && !(parseNumber instanceof String)) {
            return parseNumber;
        }
        char readCase = getReadCase();
        char c = TOKEN_ESCAPE_CHAR;
        int i6 = 0;
        if (readCase == 'I') {
            int i7 = i4;
            int i8 = 0;
            int i9 = 0;
            while (i7 < i5) {
                char c2 = this.tokenBuffer[i7];
                if (c2 == 65535) {
                    i7++;
                } else if (Character.isLowerCase(c2)) {
                    i8++;
                } else if (Character.isUpperCase(c2)) {
                    i9++;
                }
                i7++;
            }
            readCase = i8 == 0 ? 'D' : i9 == 0 ? 'U' : 'P';
        }
        boolean z = i5 >= i4 + 2 && this.tokenBuffer[i5 + -1] == '}' && this.tokenBuffer[i5 + -2] != 65535 && peek() == 58;
        int i10 = i4;
        int i11 = i10;
        int i12 = -1;
        int i13 = -1;
        int i14 = -1;
        while (i10 < i5) {
            char c3 = this.tokenBuffer[i10];
            if (c3 == c) {
                i10++;
                if (i10 < i5) {
                    i3 = i11 + 1;
                    this.tokenBuffer[i11] = this.tokenBuffer[i10];
                }
                i10++;
                c = TOKEN_ESCAPE_CHAR;
            } else {
                if (z) {
                    if (c3 == '{') {
                        if (i14 < 0) {
                            i14 = i11;
                        }
                        i6++;
                    } else if (c3 == '}' && i6 - 1 >= 0 && i6 == 0 && i13 < 0) {
                        i13 = i11;
                    }
                }
                if (i6 <= 0) {
                    if (c3 == ':') {
                        i12 = i12 >= 0 ? -1 : i11;
                    } else if (readCase == 'U') {
                        c3 = Character.toUpperCase(c3);
                    } else if (readCase == 'D') {
                        c3 = Character.toLowerCase(c3);
                    }
                }
                i3 = i11 + 1;
                this.tokenBuffer[i11] = c3;
            }
            i11 = i3;
            i10++;
            c = TOKEN_ESCAPE_CHAR;
        }
        int i15 = i11 - i4;
        if (i14 >= 0 && i13 > i14) {
            String str = i14 > 0 ? new String(this.tokenBuffer, i4, i14 - i4) : null;
            int i16 = i14 + 1;
            String str2 = new String(this.tokenBuffer, i16, i13 - i16);
            read();
            int read = read();
            Object readValues = readValues(read, readTable2.lookup(read), readTable2);
            if (!(readValues instanceof SimpleSymbol)) {
                error("expected identifier in symbol after '{URI}:'");
            }
            return Symbol.valueOf(readValues.toString(), str2, str);
        } else if (readTable2.initialColonIsKeyword && i12 == i4 && i15 > 1) {
            int i17 = i4 + 1;
            return Keyword.make(new String(this.tokenBuffer, i17, i11 - i17).intern());
        } else if (!readTable2.finalColonIsKeyword || i12 != i11 - 1 || (i15 <= 1 && !this.seenEscapes)) {
            return readTable2.makeSymbol(new String(this.tokenBuffer, i4, i15));
        } else {
            return Keyword.make(new String(this.tokenBuffer, i4, i15 - 1).intern());
        }
    }

    /* access modifiers changed from: package-private */
    public void readToken(int i, char c, ReadTable readTable) throws IOException, SyntaxException {
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i < 0) {
                if (z) {
                    eofError("unexpected EOF between escapes");
                } else {
                    return;
                }
            }
            ReadTableEntry lookup = readTable.lookup(i);
            int kind = lookup.getKind();
            if (kind != 0) {
                if (i == readTable.postfixLookupOperator && !z) {
                    int peek = this.port.peek();
                    if (peek == readTable.postfixLookupOperator) {
                        unread(i);
                        return;
                    } else if (validPostfixLookupStart(peek, readTable)) {
                        kind = 5;
                    }
                }
                if (kind == 3) {
                    int read = read();
                    if (read < 0) {
                        eofError("unexpected EOF after single escape");
                    }
                    if (readTable.hexEscapeAfterBackslash && (read == 120 || read == 88)) {
                        read = readHexEscape();
                    }
                    tokenBufferAppend(65535);
                    tokenBufferAppend(read);
                    this.seenEscapes = true;
                } else if (kind == 4) {
                    this.seenEscapes = true;
                    z = !z;
                } else if (z) {
                    tokenBufferAppend(65535);
                    tokenBufferAppend(i);
                } else if (kind != 1) {
                    if (kind != 2) {
                        if (kind == 4) {
                            this.seenEscapes = true;
                            z = true;
                        } else if (kind == 5) {
                            unread(i);
                            return;
                        } else if (kind != 6) {
                        }
                    } else if (i == 123 && lookup == ReadTableEntry.brace) {
                        i2++;
                    }
                    tokenBufferAppend(i);
                } else {
                    unread(i);
                    return;
                }
            } else if (z) {
                tokenBufferAppend(65535);
                tokenBufferAppend(i);
            } else if (i != 125 || i2 - 1 < 0) {
                unread(i);
            } else {
                tokenBufferAppend(i);
            }
            i = read();
        }
        unread(i);
    }

    public Object readObject() throws IOException, SyntaxException {
        Object obj;
        char c = ((InPort) this.port).readState;
        int i = this.tokenBufferLength;
        ((InPort) this.port).readState = ' ';
        try {
            ReadTable current = ReadTable.getCurrent();
            while (true) {
                int lineNumber = this.port.getLineNumber();
                int columnNumber = this.port.getColumnNumber();
                int read = this.port.read();
                if (read >= 0) {
                    Object readValues = readValues(read, current);
                    if (readValues != Values.empty) {
                        obj = handlePostfix(readValues, current, lineNumber, columnNumber);
                        break;
                    }
                } else {
                    obj = Sequence.eofValue;
                    break;
                }
            }
            return obj;
        } finally {
            this.tokenBufferLength = i;
            ((InPort) this.port).readState = c;
        }
    }

    /* access modifiers changed from: protected */
    public boolean validPostfixLookupStart(int i, ReadTable readTable) throws IOException {
        int kind;
        if (i < 0 || i == 58 || i == readTable.postfixLookupOperator) {
            return false;
        }
        if (i == 44 || (kind = readTable.lookup(i).getKind()) == 2 || kind == 6 || kind == 4 || kind == 3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object handlePostfix(Object obj, ReadTable readTable, int i, int i2) throws IOException, SyntaxException {
        if (obj == QuoteExp.voidExp) {
            obj = Values.empty;
        }
        while (true) {
            int peek = this.port.peek();
            if (peek < 0 || peek != readTable.postfixLookupOperator) {
                break;
            }
            this.port.read();
            if (!validPostfixLookupStart(this.port.peek(), readTable)) {
                unread();
                break;
            }
            int read = this.port.read();
            obj = PairWithPosition.make(LispLanguage.lookup_sym, LList.list2(obj, LList.list2(readTable.makeSymbol(LispLanguage.quasiquote_sym), readValues(read, readTable.lookup(read), readTable))), this.port.getName(), i + 1, i2 + 1);
        }
        return obj;
    }

    private boolean isPotentialNumber(char[] cArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            char c = cArr[i4];
            if (Character.isDigit(c)) {
                i3++;
            } else if (c == '-' || c == '+') {
                if (i4 + 1 == i2) {
                    return false;
                }
            } else if (c == '#') {
                return true;
            } else {
                if (Character.isLetter(c) || c == '/' || c == '_' || c == '^') {
                    if (i4 == i) {
                        return false;
                    }
                } else if (c != '.') {
                    return false;
                }
            }
        }
        if (i3 > 0) {
            return true;
        }
        return false;
    }

    public static Object parseNumber(CharSequence charSequence, int i) {
        char[] cArr;
        if (charSequence instanceof FString) {
            cArr = ((FString) charSequence).data;
        } else {
            cArr = charSequence.toString().toCharArray();
        }
        return parseNumber(cArr, 0, charSequence.length(), 0, i, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:142:0x01ae, code lost:
        if (java.lang.Character.digit(r11, 10) < 0) goto L_0x0182;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x027a, code lost:
        if (r7[r0 + 2] == 'n') goto L_0x027e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x044a, code lost:
        if (r6 != 's') goto L_0x045d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x0280 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0281  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:335:0x045e A[LOOP:3: B:115:0x014d->B:335:0x045e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x00c5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x0233 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00c6 A[LOOP:0: B:4:0x0015->B:66:0x00c6, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object parseNumber(char[] r34, int r35, int r36, char r37, int r38, int r39) {
        /*
            r7 = r34
            r0 = r35
            int r8 = r0 + r36
            java.lang.String r9 = "no digits"
            if (r0 < r8) goto L_0x000b
            return r9
        L_0x000b:
            int r1 = r0 + 1
            char r2 = r7[r0]
            r3 = r1
            r4 = r2
            r1 = r37
            r2 = r38
        L_0x0015:
            r10 = 100
            r11 = 69
            r5 = 35
            r13 = 101(0x65, float:1.42E-43)
            r14 = 73
            r15 = 105(0x69, float:1.47E-43)
            r12 = 10
            if (r4 != r5) goto L_0x00cc
            if (r3 < r8) goto L_0x0028
            return r9
        L_0x0028:
            int r4 = r3 + 1
            char r3 = r7[r3]
            r6 = 66
            java.lang.String r16 = "duplicate radix specifier"
            r5 = 2
            if (r3 == r6) goto L_0x00bf
            if (r3 == r14) goto L_0x00b1
            r6 = 79
            if (r3 == r6) goto L_0x00ab
            r6 = 88
            if (r3 == r6) goto L_0x00a5
            r6 = 98
            if (r3 == r6) goto L_0x00bf
            if (r3 == r15) goto L_0x00b1
            r6 = 111(0x6f, float:1.56E-43)
            if (r3 == r6) goto L_0x00ab
            r6 = 120(0x78, float:1.68E-43)
            if (r3 == r6) goto L_0x00a5
            r6 = 68
            if (r3 == r6) goto L_0x009f
            if (r3 == r11) goto L_0x00b1
            if (r3 == r10) goto L_0x009f
            if (r3 == r13) goto L_0x00b1
            r6 = 0
        L_0x0056:
            int r10 = java.lang.Character.digit(r3, r12)
            if (r10 >= 0) goto L_0x008b
            r10 = 82
            if (r3 == r10) goto L_0x007c
            r10 = 114(0x72, float:1.6E-43)
            if (r3 != r10) goto L_0x0065
            goto L_0x007c
        L_0x0065:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unknown modifier '#"
            r0.append(r1)
            r0.append(r3)
            r1 = 39
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x007c:
            if (r2 == 0) goto L_0x007f
            return r16
        L_0x007f:
            if (r6 < r5) goto L_0x0088
            r11 = 35
            if (r6 <= r11) goto L_0x0086
            goto L_0x0088
        L_0x0086:
            r2 = r6
            goto L_0x00c3
        L_0x0088:
            java.lang.String r0 = "invalid radix specifier"
            return r0
        L_0x008b:
            r11 = 35
            int r6 = r6 * 10
            int r6 = r6 + r10
            if (r4 < r8) goto L_0x0095
            java.lang.String r0 = "missing letter after '#'"
            return r0
        L_0x0095:
            int r3 = r4 + 1
            char r4 = r7[r4]
            r32 = r4
            r4 = r3
            r3 = r32
            goto L_0x0056
        L_0x009f:
            if (r2 == 0) goto L_0x00a2
            return r16
        L_0x00a2:
            r2 = 10
            goto L_0x00c3
        L_0x00a5:
            if (r2 == 0) goto L_0x00a8
            return r16
        L_0x00a8:
            r2 = 16
            goto L_0x00c3
        L_0x00ab:
            if (r2 == 0) goto L_0x00ae
            return r16
        L_0x00ae:
            r2 = 8
            goto L_0x00c3
        L_0x00b1:
            if (r1 == 0) goto L_0x00bd
            r5 = 32
            if (r1 != r5) goto L_0x00ba
            java.lang.String r0 = "non-prefix exactness specifier"
            return r0
        L_0x00ba:
            java.lang.String r0 = "duplicate exactness specifier"
            return r0
        L_0x00bd:
            r1 = r3
            goto L_0x00c3
        L_0x00bf:
            if (r2 == 0) goto L_0x00c2
            return r16
        L_0x00c2:
            r2 = 2
        L_0x00c3:
            if (r4 < r8) goto L_0x00c6
            return r9
        L_0x00c6:
            int r3 = r4 + 1
            char r4 = r7[r4]
            goto L_0x0015
        L_0x00cc:
            if (r1 != 0) goto L_0x00d1
            r5 = 32
            goto L_0x00d2
        L_0x00d1:
            r5 = r1
        L_0x00d2:
            r6 = 46
            r16 = -1
            if (r2 != 0) goto L_0x00e8
            r1 = r36
        L_0x00da:
            int r1 = r1 + -1
            if (r1 >= 0) goto L_0x00e1
        L_0x00de:
            r2 = 10
            goto L_0x00e8
        L_0x00e1:
            int r2 = r0 + r1
            char r2 = r7[r2]
            if (r2 != r6) goto L_0x00da
            goto L_0x00de
        L_0x00e8:
            r1 = 45
            if (r4 != r1) goto L_0x00ef
            r18 = 1
            goto L_0x00f1
        L_0x00ef:
            r18 = 0
        L_0x00f1:
            r11 = 43
            if (r4 == r1) goto L_0x00fb
            if (r4 != r11) goto L_0x00f8
            goto L_0x00fb
        L_0x00f8:
            r20 = 0
            goto L_0x00fd
        L_0x00fb:
            r20 = 1
        L_0x00fd:
            if (r20 == 0) goto L_0x010d
            if (r3 < r8) goto L_0x0104
            java.lang.String r0 = "no digits following sign"
            return r0
        L_0x0104:
            int r4 = r3 + 1
            char r3 = r7[r3]
            r32 = r4
            r4 = r3
            r3 = r32
        L_0x010d:
            r12 = 0
            if (r4 == r15) goto L_0x0113
            if (r4 != r14) goto L_0x0142
        L_0x0113:
            if (r3 != r8) goto L_0x0142
            int r10 = r3 + -2
            if (r0 != r10) goto L_0x0142
            r10 = r39 & 1
            if (r10 == 0) goto L_0x0142
            char r0 = r7[r0]
            if (r0 == r11) goto L_0x0124
            if (r0 == r1) goto L_0x0124
            return r9
        L_0x0124:
            if (r5 == r15) goto L_0x0135
            if (r5 != r14) goto L_0x0129
            goto L_0x0135
        L_0x0129:
            if (r18 == 0) goto L_0x0130
            gnu.math.CComplex r0 = gnu.math.Complex.imMinusOne()
            goto L_0x0134
        L_0x0130:
            gnu.math.CComplex r0 = gnu.math.Complex.imOne()
        L_0x0134:
            return r0
        L_0x0135:
            gnu.math.DComplex r0 = new gnu.math.DComplex
            if (r18 == 0) goto L_0x013c
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            goto L_0x013e
        L_0x013c:
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x013e:
            r0.<init>(r12, r1)
            return r0
        L_0x0142:
            r0 = 0
            r23 = 0
            r10 = r3
            r25 = r18
            r26 = r23
            r3 = -1
            r28 = -1
        L_0x014d:
            int r12 = java.lang.Character.digit(r4, r2)
            if (r12 < 0) goto L_0x0166
            if (r3 >= 0) goto L_0x0157
            int r3 = r10 + -1
        L_0x0157:
            long r13 = (long) r2
            long r13 = r13 * r26
            long r11 = (long) r12
            long r26 = r13 + r11
            r12 = r2
            r14 = r5
            r1 = 10
            r11 = 46
            r13 = 0
            goto L_0x0231
        L_0x0166:
            if (r4 == r6) goto L_0x021e
            r11 = 47
            if (r4 == r11) goto L_0x01f1
            r11 = 76
            if (r4 == r11) goto L_0x0198
            r11 = 83
            if (r4 == r11) goto L_0x0198
            r11 = 108(0x6c, float:1.51E-43)
            if (r4 == r11) goto L_0x0198
            r11 = 115(0x73, float:1.61E-43)
            if (r4 == r11) goto L_0x0198
            switch(r4) {
                case 68: goto L_0x0198;
                case 69: goto L_0x0198;
                case 70: goto L_0x0198;
                default: goto L_0x017f;
            }
        L_0x017f:
            switch(r4) {
                case 100: goto L_0x0198;
                case 101: goto L_0x0198;
                case 102: goto L_0x0198;
                default: goto L_0x0182;
            }
        L_0x0182:
            int r10 = r10 + -1
            r12 = r2
            r1 = r3
            r14 = r5
            r4 = r25
            r5 = r26
            r3 = r28
            r2 = -1
            r11 = 46
            r13 = 0
        L_0x0191:
            r32 = r10
            r10 = r0
            r0 = r32
            goto L_0x023d
        L_0x0198:
            if (r10 == r8) goto L_0x0182
            r4 = 10
            if (r2 == r4) goto L_0x019f
            goto L_0x0182
        L_0x019f:
            char r11 = r7[r10]
            int r12 = r10 + -1
            r13 = 43
            if (r11 == r13) goto L_0x01b1
            if (r11 != r1) goto L_0x01aa
            goto L_0x01b1
        L_0x01aa:
            int r11 = java.lang.Character.digit(r11, r4)
            if (r11 >= 0) goto L_0x01be
            goto L_0x0182
        L_0x01b1:
            int r10 = r10 + 1
            if (r10 >= r8) goto L_0x01ee
            char r11 = r7[r10]
            int r11 = java.lang.Character.digit(r11, r4)
            if (r11 >= 0) goto L_0x01be
            goto L_0x01ee
        L_0x01be:
            if (r2 == r4) goto L_0x01c3
            java.lang.String r0 = "exponent in non-decimal number"
            return r0
        L_0x01c3:
            if (r3 >= 0) goto L_0x01c8
            java.lang.String r0 = "mantissa with no digits"
            return r0
        L_0x01c8:
            r11 = 1
            int r10 = r10 + r11
            if (r10 >= r8) goto L_0x01d8
            char r11 = r7[r10]
            int r11 = java.lang.Character.digit(r11, r4)
            if (r11 >= 0) goto L_0x01d5
            goto L_0x01d8
        L_0x01d5:
            r4 = 10
            goto L_0x01c8
        L_0x01d8:
            r1 = r3
            r14 = r5
            r4 = r25
            r5 = r26
            r3 = r28
            r11 = 46
            r13 = 0
            r32 = r10
            r10 = r0
            r0 = r32
            r33 = r12
            r12 = r2
            r2 = r33
            goto L_0x023d
        L_0x01ee:
            java.lang.String r0 = "missing exponent digits"
            return r0
        L_0x01f1:
            if (r0 == 0) goto L_0x01f6
            java.lang.String r0 = "multiple fraction symbol '/'"
            return r0
        L_0x01f6:
            if (r3 >= 0) goto L_0x01fb
            java.lang.String r0 = "no digits before fraction symbol '/'"
            return r0
        L_0x01fb:
            if (r28 < 0) goto L_0x0200
            java.lang.String r0 = "fraction symbol '/' following exponent or '.'"
            return r0
        L_0x0200:
            int r4 = r10 - r3
            r0 = r34
            r11 = 45
            r1 = r3
            r12 = r2
            r2 = r4
            r3 = r12
            r4 = r25
            r14 = r5
            r11 = 46
            r13 = 0
            r5 = r26
            gnu.math.IntNum r0 = valueOf(r0, r1, r2, r3, r4, r5)
            r26 = r23
            r1 = 10
            r3 = -1
            r25 = 0
            goto L_0x0231
        L_0x021e:
            r12 = r2
            r14 = r5
            r11 = 46
            r13 = 0
            if (r28 < 0) goto L_0x0228
            java.lang.String r0 = "duplicate '.' in number"
            return r0
        L_0x0228:
            r1 = 10
            if (r12 == r1) goto L_0x022f
            java.lang.String r0 = "'.' in non-decimal number"
            return r0
        L_0x022f:
            int r28 = r10 + -1
        L_0x0231:
            if (r10 != r8) goto L_0x045e
            r1 = r3
            r4 = r25
            r5 = r26
            r3 = r28
            r2 = -1
            goto L_0x0191
        L_0x023d:
            if (r1 >= 0) goto L_0x0285
            if (r20 == 0) goto L_0x027d
            int r13 = r0 + 4
            if (r13 >= r8) goto L_0x027d
            int r20 = r0 + 3
            char r15 = r7[r20]
            if (r15 != r11) goto L_0x027d
            char r11 = r7[r13]
            r13 = 48
            if (r11 != r13) goto L_0x027d
            char r11 = r7[r0]
            r13 = 105(0x69, float:1.47E-43)
            if (r11 != r13) goto L_0x026a
            int r13 = r0 + 1
            char r13 = r7[r13]
            r15 = 110(0x6e, float:1.54E-43)
            if (r13 != r15) goto L_0x026a
            int r13 = r0 + 2
            char r13 = r7[r13]
            r15 = 102(0x66, float:1.43E-43)
            if (r13 != r15) goto L_0x026a
            r13 = 105(0x69, float:1.47E-43)
            goto L_0x027e
        L_0x026a:
            r13 = 110(0x6e, float:1.54E-43)
            if (r11 != r13) goto L_0x027d
            int r11 = r0 + 1
            char r11 = r7[r11]
            r15 = 97
            if (r11 != r15) goto L_0x027d
            int r11 = r0 + 2
            char r11 = r7[r11]
            if (r11 != r13) goto L_0x027d
            goto L_0x027e
        L_0x027d:
            r13 = 0
        L_0x027e:
            if (r13 != 0) goto L_0x0281
            return r9
        L_0x0281:
            int r0 = r0 + 5
            r9 = r0
            goto L_0x0287
        L_0x0285:
            r9 = r0
            r13 = 0
        L_0x0287:
            r0 = 105(0x69, float:1.47E-43)
            if (r14 == r0) goto L_0x0293
            r11 = 73
            if (r14 == r11) goto L_0x0293
            r15 = 32
            r11 = 0
            goto L_0x0294
        L_0x0293:
            r11 = 1
        L_0x0294:
            if (r13 == 0) goto L_0x02a7
            if (r13 != r0) goto L_0x029b
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x029d
        L_0x029b:
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L_0x029d:
            gnu.math.DFloNum r2 = new gnu.math.DFloNum
            if (r4 == 0) goto L_0x02a2
            double r0 = -r0
        L_0x02a2:
            r2.<init>((double) r0)
            goto L_0x0304
        L_0x02a7:
            if (r2 >= 0) goto L_0x0309
            if (r3 < 0) goto L_0x02ad
            goto L_0x0309
        L_0x02ad:
            int r2 = r9 - r1
            r0 = r34
            r3 = r12
            gnu.math.IntNum r0 = valueOf(r0, r1, r2, r3, r4, r5)
            if (r10 != 0) goto L_0x02ba
        L_0x02b8:
            r2 = r0
            goto L_0x02e7
        L_0x02ba:
            boolean r1 = r0.isZero()
            if (r1 == 0) goto L_0x02e2
            boolean r1 = r10.isZero()
            if (r11 == 0) goto L_0x02d8
            gnu.math.DFloNum r0 = new gnu.math.DFloNum
            if (r1 == 0) goto L_0x02cd
            r1 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x02d4
        L_0x02cd:
            if (r18 == 0) goto L_0x02d2
            r1 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            goto L_0x02d4
        L_0x02d2:
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
        L_0x02d4:
            r0.<init>((double) r1)
            goto L_0x02b8
        L_0x02d8:
            if (r1 == 0) goto L_0x02dd
            java.lang.String r0 = "0/0 is undefined"
            return r0
        L_0x02dd:
            gnu.math.RatNum r0 = gnu.math.RatNum.make(r10, r0)
            goto L_0x02b8
        L_0x02e2:
            gnu.math.RatNum r0 = gnu.math.RatNum.make(r10, r0)
            goto L_0x02b8
        L_0x02e7:
            if (r11 == 0) goto L_0x0304
            boolean r0 = r2.isExact()
            if (r0 == 0) goto L_0x0304
            gnu.math.DFloNum r0 = new gnu.math.DFloNum
            if (r18 == 0) goto L_0x02fc
            boolean r1 = r2.isZero()
            if (r1 == 0) goto L_0x02fc
            r1 = -9223372036854775808
            goto L_0x0300
        L_0x02fc:
            double r1 = r2.doubleValue()
        L_0x0300:
            r0.<init>((double) r1)
            r2 = r0
        L_0x0304:
            r0 = 101(0x65, float:1.42E-43)
            r5 = 0
            r6 = 0
            goto L_0x0357
        L_0x0309:
            if (r1 <= r3) goto L_0x030e
            if (r3 < 0) goto L_0x030e
            r1 = r3
        L_0x030e:
            if (r10 == 0) goto L_0x0313
            java.lang.String r0 = "floating-point number after fraction symbol '/'"
            return r0
        L_0x0313:
            java.lang.String r0 = new java.lang.String
            int r3 = r9 - r1
            r0.<init>(r7, r1, r3)
            if (r2 < 0) goto L_0x0347
            char r3 = r7[r2]
            char r6 = java.lang.Character.toLowerCase(r3)
            r3 = 101(0x65, float:1.42E-43)
            if (r6 == r3) goto L_0x0345
            int r2 = r2 - r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r5 = 0
            java.lang.String r10 = r0.substring(r5, r2)
            r1.append(r10)
            r1.append(r3)
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r0 = r0.substring(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x0349
        L_0x0345:
            r5 = 0
            goto L_0x0349
        L_0x0347:
            r5 = 0
            r6 = 0
        L_0x0349:
            double r0 = gnu.lists.Convert.parseDouble(r0)
            gnu.math.DFloNum r2 = new gnu.math.DFloNum
            if (r4 == 0) goto L_0x0352
            double r0 = -r0
        L_0x0352:
            r2.<init>((double) r0)
            r0 = 101(0x65, float:1.42E-43)
        L_0x0357:
            if (r14 == r0) goto L_0x035d
            r4 = 69
            if (r14 != r4) goto L_0x0361
        L_0x035d:
            gnu.math.RatNum r2 = r2.toExact()
        L_0x0361:
            r10 = r2
            if (r9 >= r8) goto L_0x042e
            int r1 = r9 + 1
            char r0 = r7[r9]
            r2 = 64
            if (r0 != r2) goto L_0x03a0
            int r2 = r8 - r1
            r4 = 10
            r0 = r34
            r3 = r14
            r5 = r39
            java.lang.Object r0 = parseNumber(r0, r1, r2, r3, r4, r5)
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L_0x037e
            return r0
        L_0x037e:
            boolean r1 = r0 instanceof gnu.math.RealNum
            if (r1 != 0) goto L_0x0385
            java.lang.String r0 = "invalid complex polar constant"
            return r0
        L_0x0385:
            gnu.math.RealNum r0 = (gnu.math.RealNum) r0
            boolean r1 = r10.isZero()
            if (r1 == 0) goto L_0x039b
            boolean r1 = r0.isExact()
            if (r1 != 0) goto L_0x039b
            gnu.math.DFloNum r0 = new gnu.math.DFloNum
            r1 = 0
            r0.<init>((double) r1)
            return r0
        L_0x039b:
            gnu.math.DComplex r0 = gnu.math.Complex.polar((gnu.math.RealNum) r10, (gnu.math.RealNum) r0)
            return r0
        L_0x03a0:
            r2 = 45
            if (r0 == r2) goto L_0x03e7
            r6 = 43
            if (r0 != r6) goto L_0x03a9
            goto L_0x03e7
        L_0x03a9:
            r6 = 0
        L_0x03aa:
            boolean r0 = java.lang.Character.isLetter(r0)
            if (r0 != 0) goto L_0x03b4
            int r1 = r1 + -1
        L_0x03b2:
            r13 = 1
            goto L_0x03b9
        L_0x03b4:
            int r6 = r6 + 1
            if (r1 != r8) goto L_0x03d8
            goto L_0x03b2
        L_0x03b9:
            if (r6 != r13) goto L_0x03d5
            int r0 = r1 + -1
            char r0 = r7[r0]
            r2 = 105(0x69, float:1.47E-43)
            if (r0 == r2) goto L_0x03c7
            r3 = 73
            if (r0 != r3) goto L_0x03d5
        L_0x03c7:
            if (r1 >= r8) goto L_0x03cc
            java.lang.String r0 = "junk after imaginary suffix 'i'"
            return r0
        L_0x03cc:
            gnu.math.IntNum r0 = gnu.math.IntNum.zero()
            gnu.math.Complex r0 = gnu.math.Complex.make((gnu.math.RealNum) r0, (gnu.math.RealNum) r10)
            return r0
        L_0x03d5:
            java.lang.String r0 = "excess junk after number"
            return r0
        L_0x03d8:
            r2 = 105(0x69, float:1.47E-43)
            r3 = 73
            r13 = 1
            int r0 = r1 + 1
            char r1 = r7[r1]
            r32 = r1
            r1 = r0
            r0 = r32
            goto L_0x03aa
        L_0x03e7:
            int r1 = r1 + -1
            int r2 = r8 - r1
            r4 = 10
            r0 = r34
            r3 = r14
            r5 = r39
            java.lang.Object r0 = parseNumber(r0, r1, r2, r3, r4, r5)
            boolean r1 = r0 instanceof java.lang.String
            if (r1 == 0) goto L_0x03fb
            return r0
        L_0x03fb:
            boolean r1 = r0 instanceof gnu.math.Complex
            if (r1 != 0) goto L_0x0416
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "invalid numeric constant ("
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = ")"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            return r0
        L_0x0416:
            gnu.math.Complex r0 = (gnu.math.Complex) r0
            gnu.math.RealNum r1 = r0.re()
            boolean r1 = r1.isZero()
            if (r1 != 0) goto L_0x0425
            java.lang.String r0 = "invalid numeric constant"
            return r0
        L_0x0425:
            gnu.math.RealNum r0 = r0.im()
            gnu.math.Complex r0 = gnu.math.Complex.make((gnu.math.RealNum) r10, (gnu.math.RealNum) r0)
            return r0
        L_0x042e:
            boolean r0 = r10 instanceof gnu.math.DFloNum
            if (r0 == 0) goto L_0x045d
            if (r6 <= 0) goto L_0x045d
            r0 = 101(0x65, float:1.42E-43)
            if (r6 == r0) goto L_0x045d
            double r0 = r10.doubleValue()
            r2 = 100
            if (r6 == r2) goto L_0x0458
            r2 = 102(0x66, float:1.43E-43)
            if (r6 == r2) goto L_0x0452
            r2 = 108(0x6c, float:1.51E-43)
            if (r6 == r2) goto L_0x044d
            r2 = 115(0x73, float:1.61E-43)
            if (r6 == r2) goto L_0x0452
            goto L_0x045d
        L_0x044d:
            java.math.BigDecimal r0 = java.math.BigDecimal.valueOf(r0)
            return r0
        L_0x0452:
            float r0 = (float) r0
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            return r0
        L_0x0458:
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            return r0
        L_0x045d:
            return r10
        L_0x045e:
            r2 = 45
            r4 = 69
            r5 = 0
            r6 = 43
            r13 = 1
            r15 = 32
            r17 = 100
            r19 = 101(0x65, float:1.42E-43)
            r21 = 0
            r29 = 73
            r30 = 105(0x69, float:1.47E-43)
            int r31 = r10 + 1
            char r10 = r7[r10]
            r4 = r10
            r2 = r12
            r5 = r14
            r10 = r31
            r1 = 45
            r6 = 46
            r11 = 43
            r14 = 73
            r15 = 105(0x69, float:1.47E-43)
            goto L_0x014d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.parseNumber(char[], int, int, char, int, int):java.lang.Object");
    }

    private static IntNum valueOf(char[] cArr, int i, int i2, int i3, boolean z, long j) {
        if (i2 + i3 > 28) {
            return IntNum.valueOf(cArr, i, i2, i3, z);
        }
        if (z) {
            j = -j;
        }
        return IntNum.make(j);
    }

    public int readEscape() throws IOException, SyntaxException {
        int read = read();
        if (read >= 0) {
            return readEscape(read);
        }
        eofError("unexpected EOF in character literal");
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ad, code lost:
        r11 = read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b1, code lost:
        if (r11 != 92) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b3, code lost:
        r11 = readEscape();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b7, code lost:
        if (r11 != 63) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b9, code lost:
        return 127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00be, code lost:
        return r11 & 159;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int readEscape(int r11) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r10 = this;
            char r0 = (char) r11
            r1 = 13
            r2 = 8
            r3 = -1
            r4 = 92
            r5 = 9
            r6 = 10
            if (r0 == r5) goto L_0x00cd
            if (r0 == r6) goto L_0x00cd
            r7 = 97
            if (r0 == r7) goto L_0x00cb
            r7 = 98
            if (r0 == r7) goto L_0x00c8
            r7 = 101(0x65, float:1.42E-43)
            if (r0 == r7) goto L_0x00c5
            r7 = 102(0x66, float:1.43E-43)
            if (r0 == r7) goto L_0x00c2
            java.lang.String r7 = "Invalid escape character syntax"
            r8 = 45
            r9 = 63
            switch(r0) {
                case 13: goto L_0x00cd;
                case 32: goto L_0x00cd;
                case 34: goto L_0x00bf;
                case 67: goto L_0x00a3;
                case 77: goto L_0x008c;
                case 88: goto L_0x0087;
                case 92: goto L_0x0083;
                case 94: goto L_0x00ad;
                case 110: goto L_0x007f;
                case 114: goto L_0x007b;
                case 120: goto L_0x0087;
                default: goto L_0x0029;
            }
        L_0x0029:
            r1 = 0
            switch(r0) {
                case 48: goto L_0x005e;
                case 49: goto L_0x005e;
                case 50: goto L_0x005e;
                case 51: goto L_0x005e;
                case 52: goto L_0x005e;
                case 53: goto L_0x005e;
                case 54: goto L_0x005e;
                case 55: goto L_0x005e;
                default: goto L_0x002d;
            }
        L_0x002d:
            switch(r0) {
                case 116: goto L_0x005a;
                case 117: goto L_0x0036;
                case 118: goto L_0x0032;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x00f1
        L_0x0032:
            r11 = 11
            goto L_0x00f1
        L_0x0036:
            r11 = 4
        L_0x0037:
            int r11 = r11 + r3
            if (r11 < 0) goto L_0x0057
            int r0 = r10.read()
            if (r0 >= 0) goto L_0x0045
            java.lang.String r2 = "premature EOF in \\u escape"
            r10.eofError(r2)
        L_0x0045:
            char r0 = (char) r0
            r2 = 16
            int r0 = java.lang.Character.digit(r0, r2)
            if (r0 >= 0) goto L_0x0053
            java.lang.String r2 = "non-hex character following \\u"
            r10.error(r2)
        L_0x0053:
            int r1 = r1 * 16
            int r1 = r1 + r0
            goto L_0x0037
        L_0x0057:
            r11 = r1
            goto L_0x00f1
        L_0x005a:
            r11 = 9
            goto L_0x00f1
        L_0x005e:
            int r11 = r11 + -48
        L_0x0060:
            int r1 = r1 + 1
            r0 = 3
            if (r1 >= r0) goto L_0x00f1
            int r0 = r10.read()
            char r3 = (char) r0
            int r3 = java.lang.Character.digit(r3, r2)
            if (r3 < 0) goto L_0x0074
            int r11 = r11 << 3
            int r11 = r11 + r3
            goto L_0x0060
        L_0x0074:
            if (r0 < 0) goto L_0x00f1
            r10.unread(r0)
            goto L_0x00f1
        L_0x007b:
            r11 = 13
            goto L_0x00f1
        L_0x007f:
            r11 = 10
            goto L_0x00f1
        L_0x0083:
            r11 = 92
            goto L_0x00f1
        L_0x0087:
            int r11 = r10.readHexEscape()
            return r11
        L_0x008c:
            int r11 = r10.read()
            if (r11 == r8) goto L_0x0096
            r10.error(r7)
            return r9
        L_0x0096:
            int r11 = r10.read()
            if (r11 != r4) goto L_0x00a0
            int r11 = r10.readEscape()
        L_0x00a0:
            r11 = r11 | 128(0x80, float:1.794E-43)
            return r11
        L_0x00a3:
            int r11 = r10.read()
            if (r11 == r8) goto L_0x00ad
            r10.error(r7)
            return r9
        L_0x00ad:
            int r11 = r10.read()
            if (r11 != r4) goto L_0x00b7
            int r11 = r10.readEscape()
        L_0x00b7:
            if (r11 != r9) goto L_0x00bc
            r11 = 127(0x7f, float:1.78E-43)
            return r11
        L_0x00bc:
            r11 = r11 & 159(0x9f, float:2.23E-43)
            return r11
        L_0x00bf:
            r11 = 34
            goto L_0x00f1
        L_0x00c2:
            r11 = 12
            goto L_0x00f1
        L_0x00c5:
            r11 = 27
            goto L_0x00f1
        L_0x00c8:
            r11 = 8
            goto L_0x00f1
        L_0x00cb:
            r11 = 7
            goto L_0x00f1
        L_0x00cd:
            java.lang.String r0 = "unexpected EOF in literal"
            if (r11 >= 0) goto L_0x00d5
            r10.eofError(r0)
            return r3
        L_0x00d5:
            r2 = 32
            if (r11 != r6) goto L_0x00da
            goto L_0x00ef
        L_0x00da:
            if (r11 != r1) goto L_0x00e8
            int r11 = r10.peek()
            if (r11 != r6) goto L_0x00e5
            r10.skip()
        L_0x00e5:
            r11 = 10
            goto L_0x00ef
        L_0x00e8:
            if (r11 == r2) goto L_0x0105
            if (r11 == r5) goto L_0x0105
            r10.unread(r11)
        L_0x00ef:
            if (r11 == r6) goto L_0x00f2
        L_0x00f1:
            return r11
        L_0x00f2:
            int r11 = r10.read()
            if (r11 >= 0) goto L_0x00fc
            r10.eofError(r0)
            return r3
        L_0x00fc:
            if (r11 == r2) goto L_0x00f2
            if (r11 == r5) goto L_0x00f2
            r10.unread(r11)
            r11 = -2
            return r11
        L_0x0105:
            int r11 = r10.read()
            goto L_0x00cd
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readEscape(int):int");
    }

    public int readHexEscape() throws IOException, SyntaxException {
        int read;
        int i = 0;
        while (true) {
            read = read();
            int digit = Character.digit((char) read, 16);
            if (digit < 0) {
                break;
            }
            i = (i << 4) + digit;
        }
        if (read != 59 && read >= 0) {
            unread(read);
        }
        return i;
    }

    public final Object readObject(int i) throws IOException, SyntaxException {
        unread(i);
        return readObject();
    }

    public Object readCommand() throws IOException, SyntaxException {
        return readObject();
    }

    /* access modifiers changed from: protected */
    public Object makeNil() {
        return LList.Empty;
    }

    /* access modifiers changed from: protected */
    public Pair makePair(Object obj, int i, int i2) {
        return makePair(obj, LList.Empty, i, i2);
    }

    /* access modifiers changed from: protected */
    public Pair makePair(Object obj, Object obj2, int i, int i2) {
        String name = this.port.getName();
        if (name == null || i < 0) {
            return Pair.make(obj, obj2);
        }
        return PairWithPosition.make(obj, obj2, name, i + 1, i2 + 1);
    }

    /* access modifiers changed from: protected */
    public void setCdr(Object obj, Object obj2) {
        ((Pair) obj).setCdrBackdoor(obj2);
    }

    public static Object readNumberWithRadix(int i, LispReader lispReader, int i2) throws IOException, SyntaxException {
        int i3 = lispReader.tokenBufferLength - i;
        lispReader.readToken(lispReader.read(), 'P', ReadTable.getCurrent());
        int i4 = lispReader.tokenBufferLength;
        if (i3 == i4) {
            lispReader.error("missing numeric token");
            return IntNum.zero();
        }
        Object parseNumber = parseNumber(lispReader.tokenBuffer, i3, i4 - i3, 0, i2, 0);
        if (parseNumber instanceof String) {
            lispReader.error((String) parseNumber);
            return IntNum.zero();
        } else if (parseNumber != null) {
            return parseNumber;
        } else {
            lispReader.error("invalid numeric constant");
            return IntNum.zero();
        }
    }

    public static Object readCharacter(LispReader lispReader) throws IOException, SyntaxException {
        int read = lispReader.read();
        if (read < 0) {
            lispReader.eofError("unexpected EOF in character literal");
        }
        int i = lispReader.tokenBufferLength;
        lispReader.tokenBufferAppend(read);
        lispReader.readToken(lispReader.read(), 'D', ReadTable.getCurrent());
        char[] cArr = lispReader.tokenBuffer;
        int i2 = lispReader.tokenBufferLength - i;
        int i3 = 1;
        if (i2 == 1) {
            return Char.make(cArr[i]);
        }
        String str = new String(cArr, i, i2);
        int nameToChar = Char.nameToChar(str);
        if (nameToChar >= 0) {
            return Char.make(nameToChar);
        }
        char c = cArr[i];
        if (c == 'x' || c == 'X') {
            int i4 = 0;
            int i5 = 1;
            while (i5 != i2) {
                int digit = Character.digit(cArr[i + i5], 16);
                if (digit >= 0 && (i4 = (i4 * 16) + digit) <= 1114111) {
                    i5++;
                }
            }
            return Char.make(i4);
        }
        int digit2 = Character.digit(c, 8);
        if (digit2 >= 0) {
            while (i3 != i2) {
                int digit3 = Character.digit(cArr[i + i3], 8);
                if (digit3 >= 0) {
                    digit2 = (digit2 * 8) + digit3;
                    i3++;
                }
            }
            return Char.make(digit2);
        }
        lispReader.error("unknown character name: " + str);
        return Char.make(63);
    }

    public static Object readSpecial(LispReader lispReader) throws IOException, SyntaxException {
        int read = lispReader.read();
        if (read < 0) {
            lispReader.eofError("unexpected EOF in #! special form");
        }
        if (read == 47 && lispReader.getLineNumber() == 0 && lispReader.getColumnNumber() == 3) {
            ReaderIgnoreRestOfLine.getInstance().read(lispReader, 35, 1);
            return Values.empty;
        }
        int i = lispReader.tokenBufferLength;
        lispReader.tokenBufferAppend(read);
        lispReader.readToken(lispReader.read(), 'D', ReadTable.getCurrent());
        String str = new String(lispReader.tokenBuffer, i, lispReader.tokenBufferLength - i);
        if (str.equals("optional")) {
            return Special.optional;
        }
        if (str.equals("rest")) {
            return Special.rest;
        }
        if (str.equals("key")) {
            return Special.key;
        }
        if (str.equals("eof")) {
            return Special.eof;
        }
        if (str.equals("void")) {
            return QuoteExp.voidExp;
        }
        if (str.equals("default")) {
            return Special.dfault;
        }
        if (str.equals("undefined")) {
            return Special.undefined;
        }
        if (str.equals("abstract")) {
            return Special.abstractSpecial;
        }
        if (str.equals("null")) {
            return null;
        }
        lispReader.error("unknown named constant #!" + str);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
        if (r12 != 'U') goto L_0x006b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.lists.SimpleVector readSimpleVector(gnu.kawa.lispexpr.LispReader r11, char r12) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r11.read()
            if (r2 >= 0) goto L_0x000d
            java.lang.String r3 = "unexpected EOF reading uniform vector"
            r11.eofError(r3)
        L_0x000d:
            char r3 = (char) r2
            r4 = 10
            int r3 = java.lang.Character.digit(r3, r4)
            if (r3 >= 0) goto L_0x00a8
            r3 = 0
            r4 = 16
            r5 = 8
            r6 = 64
            r7 = 32
            if (r1 == r5) goto L_0x0027
            if (r1 == r4) goto L_0x0027
            if (r1 == r7) goto L_0x0027
            if (r1 != r6) goto L_0x0031
        L_0x0027:
            r8 = 70
            if (r12 != r8) goto L_0x002d
            if (r1 < r7) goto L_0x0031
        L_0x002d:
            r9 = 40
            if (r2 == r9) goto L_0x0037
        L_0x0031:
            java.lang.String r12 = "invalid uniform vector syntax"
            r11.error(r12)
            return r3
        L_0x0037:
            r2 = -1
            r10 = 41
            java.lang.Object r2 = gnu.kawa.lispexpr.ReaderParens.readList(r11, r9, r2, r10)
            int r0 = gnu.lists.LList.listLength(r2, r0)
            if (r0 >= 0) goto L_0x004a
            java.lang.String r12 = "invalid elements in uniform vector syntax"
            r11.error(r12)
            return r3
        L_0x004a:
            gnu.lists.Sequence r2 = (gnu.lists.Sequence) r2
            if (r12 == r8) goto L_0x0057
            r11 = 83
            if (r12 == r11) goto L_0x005b
            r11 = 85
            if (r12 == r11) goto L_0x0063
            goto L_0x006b
        L_0x0057:
            if (r1 == r7) goto L_0x00a2
            if (r1 == r6) goto L_0x009c
        L_0x005b:
            if (r1 == r5) goto L_0x0096
            if (r1 == r4) goto L_0x0090
            if (r1 == r7) goto L_0x008a
            if (r1 == r6) goto L_0x0084
        L_0x0063:
            if (r1 == r5) goto L_0x007e
            if (r1 == r4) goto L_0x0078
            if (r1 == r7) goto L_0x0072
            if (r1 == r6) goto L_0x006c
        L_0x006b:
            return r3
        L_0x006c:
            gnu.lists.U64Vector r11 = new gnu.lists.U64Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x0072:
            gnu.lists.U32Vector r11 = new gnu.lists.U32Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x0078:
            gnu.lists.U16Vector r11 = new gnu.lists.U16Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x007e:
            gnu.lists.U8Vector r11 = new gnu.lists.U8Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x0084:
            gnu.lists.S64Vector r11 = new gnu.lists.S64Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x008a:
            gnu.lists.S32Vector r11 = new gnu.lists.S32Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x0090:
            gnu.lists.S16Vector r11 = new gnu.lists.S16Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x0096:
            gnu.lists.S8Vector r11 = new gnu.lists.S8Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x009c:
            gnu.lists.F64Vector r11 = new gnu.lists.F64Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x00a2:
            gnu.lists.F32Vector r11 = new gnu.lists.F32Vector
            r11.<init>((gnu.lists.Sequence) r2)
            return r11
        L_0x00a8:
            int r1 = r1 * 10
            int r1 = r1 + r3
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispReader.readSimpleVector(gnu.kawa.lispexpr.LispReader, char):gnu.lists.SimpleVector");
    }
}
