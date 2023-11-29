package gnu.ecmascript;

import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import gnu.expr.QuoteExp;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.text.Char;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

public class Lexer extends gnu.text.Lexer {
    public static final Char colonToken = Char.make(58);
    public static final Char commaToken = Char.make(44);
    public static final Char condToken = Char.make(63);
    public static final Char dotToken = Char.make(46);
    public static final Reserved elseToken = new Reserved("else", 38);
    public static final Object eofToken = Sequence.eofValue;
    public static final Object eolToken = Char.make(10);
    public static final Char equalToken = Char.make(61);
    public static final Char lbraceToken = Char.make(123);
    public static final Char lbracketToken = Char.make(91);
    public static final Char lparenToken = Char.make(40);
    public static final Reserved newToken = new Reserved("new", 39);
    public static final Char notToken = Char.make(33);
    public static final Char rbraceToken = Char.make(ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH);
    public static final Char rbracketToken = Char.make(93);
    static Hashtable reserved;
    public static final Char rparenToken = Char.make(41);
    public static final Char semicolonToken = Char.make(59);
    public static final Char tildeToken = Char.make(126);
    private boolean prevWasCR = false;

    public Lexer(InPort inPort) {
        super(inPort);
    }

    static synchronized void initReserved() {
        synchronized (Lexer.class) {
            if (reserved == null) {
                Hashtable hashtable = new Hashtable(20);
                reserved = hashtable;
                hashtable.put("null", new QuoteExp((Object) null));
                reserved.put("true", new QuoteExp(Boolean.TRUE));
                reserved.put("false", new QuoteExp(Boolean.FALSE));
                reserved.put("var", new Reserved("var", 30));
                reserved.put("if", new Reserved("if", 31));
                reserved.put("while", new Reserved("while", 32));
                reserved.put("for", new Reserved("for", 33));
                reserved.put("continue", new Reserved("continue", 34));
                reserved.put("break", new Reserved("break", 35));
                reserved.put("return", new Reserved("return", 36));
                reserved.put("with", new Reserved("with", 37));
                reserved.put("function", new Reserved("function", 41));
                reserved.put("this", new Reserved("this", 40));
                reserved.put("else", elseToken);
                reserved.put("new", newToken);
            }
        }
    }

    public static Object checkReserved(String str) {
        if (reserved == null) {
            initReserved();
        }
        return reserved.get(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0070  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getNumericLiteral(int r13) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 69
            r1 = 101(0x65, float:1.42E-43)
            r2 = 46
            r3 = 10
            r4 = 48
            if (r13 != r4) goto L_0x002a
            int r13 = r12.read()
            r4 = 120(0x78, float:1.68E-43)
            if (r13 == r4) goto L_0x0023
            r4 = 88
            if (r13 != r4) goto L_0x0019
            goto L_0x0023
        L_0x0019:
            if (r13 == r2) goto L_0x002a
            if (r13 == r1) goto L_0x002a
            if (r13 != r0) goto L_0x0020
            goto L_0x002a
        L_0x0020:
            r4 = 8
            goto L_0x002c
        L_0x0023:
            r4 = 16
            int r13 = r12.read()
            goto L_0x002c
        L_0x002a:
            r4 = 10
        L_0x002c:
            gnu.text.LineBufferedReader r5 = r12.port
            int r5 = r5.pos
            if (r13 < 0) goto L_0x0034
            int r5 = r5 + -1
        L_0x0034:
            gnu.text.LineBufferedReader r13 = r12.port
            r13.pos = r5
            gnu.text.LineBufferedReader r13 = r12.port
            long r6 = readDigitsInBuffer(r13, r4)
            gnu.text.LineBufferedReader r13 = r12.port
            int r13 = r13.pos
            r8 = 1
            r9 = 0
            if (r13 <= r5) goto L_0x0048
            r13 = 1
            goto L_0x0049
        L_0x0048:
            r13 = 0
        L_0x0049:
            if (r13 == 0) goto L_0x0087
            gnu.text.LineBufferedReader r10 = r12.port
            int r10 = r10.pos
            gnu.text.LineBufferedReader r11 = r12.port
            int r11 = r11.limit
            if (r10 >= r11) goto L_0x0087
            gnu.text.LineBufferedReader r10 = r12.port
            char[] r10 = r10.buffer
            gnu.text.LineBufferedReader r11 = r12.port
            int r11 = r11.pos
            char r10 = r10[r11]
            char r11 = (char) r10
            boolean r11 = java.lang.Character.isLetterOrDigit(r11)
            if (r11 != 0) goto L_0x0087
            if (r10 == r2) goto L_0x0087
            r0 = 0
            int r13 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r13 < 0) goto L_0x0070
            double r0 = (double) r6
            goto L_0x0081
        L_0x0070:
            gnu.text.LineBufferedReader r13 = r12.port
            char[] r13 = r13.buffer
            gnu.text.LineBufferedReader r0 = r12.port
            int r0 = r0.pos
            int r0 = r0 - r5
            gnu.math.IntNum r13 = gnu.math.IntNum.valueOf(r13, r5, r0, r4, r9)
            double r0 = r13.doubleValue()
        L_0x0081:
            java.lang.Double r13 = new java.lang.Double
            r13.<init>(r0)
            return r13
        L_0x0087:
            if (r4 == r3) goto L_0x008e
            java.lang.String r6 = "invalid character in non-decimal number"
            r12.error(r6)
        L_0x008e:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r7 = 20
            r6.<init>(r7)
            if (r13 == 0) goto L_0x00a3
            gnu.text.LineBufferedReader r7 = r12.port
            char[] r7 = r7.buffer
            gnu.text.LineBufferedReader r10 = r12.port
            int r10 = r10.pos
            int r10 = r10 - r5
            r6.append(r7, r5, r10)
        L_0x00a3:
            r5 = -1
        L_0x00a4:
            gnu.text.LineBufferedReader r7 = r12.port
            int r7 = r7.read()
            char r10 = (char) r7
            int r11 = java.lang.Character.digit(r10, r4)
            if (r11 < 0) goto L_0x00b6
            r6.append(r10)
            r13 = 1
            goto L_0x00a4
        L_0x00b6:
            if (r7 == r2) goto L_0x00fd
            if (r7 == r0) goto L_0x00bd
            if (r7 == r1) goto L_0x00bd
            goto L_0x00e4
        L_0x00bd:
            if (r4 != r3) goto L_0x00e4
            gnu.text.LineBufferedReader r0 = r12.port
            int r0 = r0.peek()
            r2 = 43
            if (r0 == r2) goto L_0x00d5
            r2 = 45
            if (r0 == r2) goto L_0x00d5
            char r0 = (char) r0
            int r0 = java.lang.Character.digit(r0, r3)
            if (r0 >= 0) goto L_0x00d5
            goto L_0x00e4
        L_0x00d5:
            if (r13 != 0) goto L_0x00dc
            java.lang.String r13 = "mantissa with no digits"
            r12.error(r13)
        L_0x00dc:
            int r9 = r12.readOptionalExponent()
            int r7 = r12.read()
        L_0x00e4:
            if (r7 < 0) goto L_0x00eb
            gnu.text.LineBufferedReader r13 = r12.port
            r13.unread()
        L_0x00eb:
            if (r9 == 0) goto L_0x00f3
            r6.append(r1)
            r6.append(r9)
        L_0x00f3:
            java.lang.Double r13 = new java.lang.Double
            java.lang.String r0 = r6.toString()
            r13.<init>(r0)
            return r13
        L_0x00fd:
            if (r5 < 0) goto L_0x0105
            java.lang.String r7 = "duplicate '.' in number"
            r12.error(r7)
            goto L_0x00a4
        L_0x0105:
            int r5 = r6.length()
            r6.append(r2)
            goto L_0x00a4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.ecmascript.Lexer.getNumericLiteral(int):java.lang.Double");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getStringLiteral(char r12) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
            gnu.text.LineBufferedReader r0 = r11.port
            int r0 = r0.pos
            gnu.text.LineBufferedReader r1 = r11.port
            int r1 = r1.limit
            gnu.text.LineBufferedReader r2 = r11.port
            char[] r2 = r2.buffer
            r3 = r0
        L_0x000d:
            r4 = 92
            r5 = 13
            r6 = 10
            if (r3 >= r1) goto L_0x0030
            char r7 = r2[r3]
            if (r7 != r12) goto L_0x0026
            gnu.text.LineBufferedReader r12 = r11.port
            int r1 = r3 + 1
            r12.pos = r1
            java.lang.String r12 = new java.lang.String
            int r3 = r3 - r0
            r12.<init>(r2, r0, r3)
            return r12
        L_0x0026:
            if (r7 == r4) goto L_0x0030
            if (r7 == r6) goto L_0x0030
            if (r7 != r5) goto L_0x002d
            goto L_0x0030
        L_0x002d:
            int r3 = r3 + 1
            goto L_0x000d
        L_0x0030:
            gnu.text.LineBufferedReader r1 = r11.port
            r1.pos = r3
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            int r3 = r3 - r0
            r1.append(r2, r0, r3)
        L_0x003d:
            gnu.text.LineBufferedReader r0 = r11.port
            int r0 = r0.read()
            if (r0 != r12) goto L_0x004a
            java.lang.String r12 = r1.toString()
            return r12
        L_0x004a:
            if (r0 >= 0) goto L_0x0051
            java.lang.String r2 = "unterminated string literal"
            r11.eofError(r2)
        L_0x0051:
            if (r0 == r6) goto L_0x0055
            if (r0 != r5) goto L_0x005a
        L_0x0055:
            java.lang.String r2 = "string literal not terminated before end of line"
            r11.fatal(r2)
        L_0x005a:
            r2 = 8
            if (r0 != r4) goto L_0x0105
            gnu.text.LineBufferedReader r0 = r11.port
            int r0 = r0.read()
            r3 = 0
            switch(r0) {
                case -1: goto L_0x00d6;
                case 10: goto L_0x00db;
                case 13: goto L_0x00db;
                case 34: goto L_0x0105;
                case 39: goto L_0x0105;
                case 92: goto L_0x0105;
                case 98: goto L_0x00d3;
                case 102: goto L_0x00d0;
                case 110: goto L_0x00cd;
                case 114: goto L_0x00ca;
                case 116: goto L_0x00c7;
                case 117: goto L_0x0072;
                case 120: goto L_0x0072;
                default: goto L_0x0068;
            }
        L_0x0068:
            r7 = 48
            if (r0 < r7) goto L_0x0105
            r7 = 55
            if (r0 <= r7) goto L_0x00e1
            goto L_0x0105
        L_0x0072:
            r2 = 120(0x78, float:1.68E-43)
            if (r0 != r2) goto L_0x0078
            r2 = 2
            goto L_0x0079
        L_0x0078:
            r2 = 4
        L_0x0079:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x0104
            gnu.text.LineBufferedReader r7 = r11.port
            int r7 = r7.read()
            java.lang.String r8 = "' in string"
            if (r7 >= 0) goto L_0x009f
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "eof following '\\"
            r9.append(r10)
            char r10 = (char) r0
            r9.append(r10)
            r9.append(r8)
            java.lang.String r9 = r9.toString()
            r11.eofError(r9)
        L_0x009f:
            char r7 = (char) r7
            r9 = 16
            char r7 = java.lang.Character.forDigit(r7, r9)
            if (r7 >= 0) goto L_0x00c3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "invalid char following '\\"
            r2.append(r3)
            char r0 = (char) r0
            r2.append(r0)
            r2.append(r8)
            java.lang.String r0 = r2.toString()
            r11.error(r0)
            r0 = 63
            goto L_0x0105
        L_0x00c3:
            int r3 = r3 * 16
            int r3 = r3 + r7
            goto L_0x0079
        L_0x00c7:
            r0 = 9
            goto L_0x0105
        L_0x00ca:
            r0 = 13
            goto L_0x0105
        L_0x00cd:
            r0 = 10
            goto L_0x0105
        L_0x00d0:
            r0 = 12
            goto L_0x0105
        L_0x00d3:
            r0 = 8
            goto L_0x0105
        L_0x00d6:
            java.lang.String r2 = "eof following '\\' in string"
            r11.eofError(r2)
        L_0x00db:
            java.lang.String r2 = "line terminator following '\\' in string"
            r11.fatal(r2)
            goto L_0x0105
        L_0x00e1:
            r0 = 3
        L_0x00e2:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x0104
            gnu.text.LineBufferedReader r7 = r11.port
            int r7 = r7.read()
            if (r7 >= 0) goto L_0x00f3
            java.lang.String r8 = "eof in octal escape in string literal"
            r11.eofError(r8)
        L_0x00f3:
            char r7 = (char) r7
            char r7 = java.lang.Character.forDigit(r7, r2)
            if (r7 >= 0) goto L_0x0100
            gnu.text.LineBufferedReader r0 = r11.port
            r0.unread_quick()
            goto L_0x0104
        L_0x0100:
            int r3 = r3 * 8
            int r3 = r3 + r7
            goto L_0x00e2
        L_0x0104:
            r0 = r3
        L_0x0105:
            char r0 = (char) r0
            r1.append(r0)
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.ecmascript.Lexer.getStringLiteral(char):java.lang.String");
    }

    public String getIdentifier(int i) throws IOException {
        int i2 = this.port.pos;
        int i3 = i2 - 1;
        int i4 = this.port.limit;
        char[] cArr = this.port.buffer;
        while (i2 < i4 && Character.isJavaIdentifierPart(cArr[i2])) {
            i2++;
        }
        this.port.pos = i2;
        if (i2 < i4) {
            return new String(cArr, i3, i2 - i3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(cArr, i3, i2 - i3);
        while (true) {
            int read = this.port.read();
            if (read >= 0) {
                char c = (char) read;
                if (!Character.isJavaIdentifierPart(c)) {
                    this.port.unread_quick();
                    break;
                }
                stringBuffer.append(c);
            } else {
                break;
            }
        }
        return stringBuffer.toString();
    }

    public Object maybeAssignment(Object obj) throws IOException, SyntaxException {
        int read = read();
        if (read == 61) {
            error("assignment operation not implemented");
        }
        if (read >= 0) {
            this.port.unread_quick();
        }
        return obj;
    }

    public Object getToken() throws IOException, SyntaxException {
        int read = read();
        while (read >= 0) {
            char c = (char) read;
            if (!Character.isWhitespace(c)) {
                if (read != 33) {
                    if (read != 34) {
                        if (read == 91) {
                            return lbracketToken;
                        }
                        if (read == 93) {
                            return rbracketToken;
                        }
                        if (read == 94) {
                            return maybeAssignment(Reserved.opBitXor);
                        }
                        switch (read) {
                            case 37:
                                return maybeAssignment(Reserved.opRemainder);
                            case 38:
                                if (this.port.peek() != 38) {
                                    return maybeAssignment(Reserved.opBitAnd);
                                }
                                this.port.skip_quick();
                                return maybeAssignment(Reserved.opBoolAnd);
                            case 39:
                                break;
                            case 40:
                                return lparenToken;
                            case 41:
                                return rparenToken;
                            case 42:
                                return maybeAssignment(Reserved.opTimes);
                            case 43:
                                if (this.port.peek() != 43) {
                                    return maybeAssignment(Reserved.opPlus);
                                }
                                this.port.skip_quick();
                                return maybeAssignment(Reserved.opPlusPlus);
                            case 44:
                                return commaToken;
                            case 45:
                                if (this.port.peek() != 45) {
                                    return maybeAssignment(Reserved.opMinus);
                                }
                                this.port.skip_quick();
                                return maybeAssignment(Reserved.opMinusMinus);
                            case 46:
                                int peek = this.port.peek();
                                if (peek < 48 || peek > 57) {
                                    return dotToken;
                                }
                                return new QuoteExp(getNumericLiteral(46));
                            case 47:
                                return maybeAssignment(Reserved.opDivide);
                            case 48:
                            case 49:
                            case 50:
                            case 51:
                            case 52:
                            case 53:
                            case 54:
                            case 55:
                            case 56:
                            case 57:
                                return new QuoteExp(getNumericLiteral(read));
                            case 58:
                                return colonToken;
                            case 59:
                                return semicolonToken;
                            case 60:
                                int peek2 = this.port.peek();
                                if (peek2 == 60) {
                                    this.port.skip_quick();
                                    return maybeAssignment(Reserved.opLshift);
                                } else if (peek2 != 61) {
                                    return Reserved.opLess;
                                } else {
                                    this.port.skip_quick();
                                    return Reserved.opLessEqual;
                                }
                            case 61:
                                if (this.port.peek() != 61) {
                                    return equalToken;
                                }
                                this.port.skip_quick();
                                return Reserved.opEqual;
                            case 62:
                                int peek3 = this.port.peek();
                                if (peek3 == 61) {
                                    this.port.skip_quick();
                                    return Reserved.opGreaterEqual;
                                } else if (peek3 != 62) {
                                    return Reserved.opGreater;
                                } else {
                                    this.port.skip_quick();
                                    if (this.port.peek() != 62) {
                                        return maybeAssignment(Reserved.opRshiftSigned);
                                    }
                                    this.port.skip_quick();
                                    return maybeAssignment(Reserved.opRshiftUnsigned);
                                }
                            case 63:
                                return condToken;
                            default:
                                switch (read) {
                                    case 123:
                                        return lbraceToken;
                                    case 124:
                                        if (this.port.peek() != 124) {
                                            return maybeAssignment(Reserved.opBitOr);
                                        }
                                        this.port.skip_quick();
                                        return maybeAssignment(Reserved.opBoolOr);
                                    case ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH /*125*/:
                                        return rbraceToken;
                                    case 126:
                                        return tildeToken;
                                    default:
                                        if (!Character.isJavaIdentifierStart(c)) {
                                            return Char.make(c);
                                        }
                                        String intern = getIdentifier(read).intern();
                                        Object checkReserved = checkReserved(intern);
                                        return checkReserved != null ? checkReserved : intern;
                                }
                        }
                    }
                    return new QuoteExp(getStringLiteral(c));
                } else if (this.port.peek() != 61) {
                    return notToken;
                } else {
                    this.port.skip_quick();
                    return Reserved.opNotEqual;
                }
            } else if (read == 13) {
                this.prevWasCR = true;
                return eolToken;
            } else if (read == 10 && !this.prevWasCR) {
                return eolToken;
            } else {
                this.prevWasCR = false;
                read = read();
            }
        }
        return eofToken;
    }

    public static Object getToken(InPort inPort) throws IOException, SyntaxException {
        return new Lexer(inPort).getToken();
    }

    public static void main(String[] strArr) {
        Object token;
        Lexer lexer = new Lexer(InPort.inDefault());
        do {
            try {
                token = lexer.getToken();
                OutPort outDefault = OutPort.outDefault();
                outDefault.print("token:");
                outDefault.print(token);
                outDefault.println(" [class:" + token.getClass() + "]");
            } catch (Exception e) {
                PrintStream printStream = System.err;
                printStream.println("caught exception:" + e);
                return;
            }
        } while (token != Sequence.eofValue);
    }
}
