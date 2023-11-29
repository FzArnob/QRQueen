package gnu.xquery.lang;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.Component;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.NameLookup;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.Convert;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.reflect.SingletonType;
import gnu.kawa.xml.DescendantOrSelfAxis;
import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeWithBaseUri;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.ParentAxis;
import gnu.kawa.xml.ProcessingInstructionType;
import gnu.kawa.xml.XDataType;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.Path;
import gnu.text.SourceError;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import gnu.xquery.util.CastableAs;
import gnu.xquery.util.NamedCollator;
import gnu.xquery.util.QNameUtils;
import gnu.xquery.util.RelativeStep;
import gnu.xquery.util.ValuesFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Stack;
import java.util.Vector;
import org.jose4j.jwt.ReservedClaimNames;
import org.slf4j.Marker;

public class XQParser extends Lexer {
    static final int ARROW_TOKEN = 82;
    static final int ATTRIBUTE_TOKEN = 252;
    static final int AXIS_ANCESTOR = 0;
    static final int AXIS_ANCESTOR_OR_SELF = 1;
    static final int AXIS_ATTRIBUTE = 2;
    static final int AXIS_CHILD = 3;
    static final int AXIS_DESCENDANT = 4;
    static final int AXIS_DESCENDANT_OR_SELF = 5;
    static final int AXIS_FOLLOWING = 6;
    static final int AXIS_FOLLOWING_SIBLING = 7;
    static final int AXIS_NAMESPACE = 8;
    static final int AXIS_PARENT = 9;
    static final int AXIS_PRECEDING = 10;
    static final int AXIS_PRECEDING_SIBLING = 11;
    static final int AXIS_SELF = 12;
    static final int CASE_DOLLAR_TOKEN = 247;
    static final int COLON_COLON_TOKEN = 88;
    static final int COLON_EQUAL_TOKEN = 76;
    static final int COMMENT_TOKEN = 254;
    static final int COUNT_OP_AXIS = 13;
    static final char DECIMAL_TOKEN = '1';
    static final int DECLARE_BASE_URI_TOKEN = 66;
    static final int DECLARE_BOUNDARY_SPACE_TOKEN = 83;
    static final int DECLARE_CONSTRUCTION_TOKEN = 75;
    static final int DECLARE_COPY_NAMESPACES_TOKEN = 76;
    static final int DECLARE_FUNCTION_TOKEN = 80;
    static final int DECLARE_NAMESPACE_TOKEN = 78;
    static final int DECLARE_OPTION_TOKEN = 111;
    static final int DECLARE_ORDERING_TOKEN = 85;
    static final int DECLARE_VARIABLE_TOKEN = 86;
    static final int DEFAULT_COLLATION_TOKEN = 71;
    static final int DEFAULT_ELEMENT_TOKEN = 69;
    static final int DEFAULT_FUNCTION_TOKEN = 79;
    static final int DEFAULT_ORDER_TOKEN = 72;
    static final int DEFINE_QNAME_TOKEN = 87;
    static final int DOCUMENT_TOKEN = 256;
    static final int DOTDOT_TOKEN = 51;
    static final Symbol DOT_VARNAME = Symbol.makeUninterned("$dot$");
    static final char DOUBLE_TOKEN = '2';
    static final int ELEMENT_TOKEN = 251;
    static final int EOF_TOKEN = -1;
    static final int EOL_TOKEN = 10;
    static final int EVERY_DOLLAR_TOKEN = 246;
    static final int FNAME_TOKEN = 70;
    static final int FOR_DOLLAR_TOKEN = 243;
    static final int IF_LPAREN_TOKEN = 241;
    static final int IMPORT_MODULE_TOKEN = 73;
    static final int IMPORT_SCHEMA_TOKEN = 84;
    static final char INTEGER_TOKEN = '0';
    static final Symbol LAST_VARNAME = Symbol.makeUninterned("$last$");
    static final int LET_DOLLAR_TOKEN = 244;
    static final int MODULE_NAMESPACE_TOKEN = 77;
    static final int NCNAME_COLON_TOKEN = 67;
    static final int NCNAME_TOKEN = 65;
    static final int OP_ADD = 413;
    static final int OP_AND = 401;
    static final int OP_ATTRIBUTE = 236;
    static final int OP_AXIS_FIRST = 100;
    static final int OP_BASE = 400;
    static final int OP_CASTABLE_AS = 424;
    static final int OP_CAST_AS = 425;
    static final int OP_COMMENT = 232;
    static final int OP_DIV = 416;
    static final int OP_DOCUMENT = 234;
    static final int OP_ELEMENT = 235;
    static final int OP_EMPTY_SEQUENCE = 238;
    static final int OP_EQ = 426;
    static final int OP_EQU = 402;
    static final int OP_EXCEPT = 421;
    static final int OP_GE = 431;
    static final int OP_GEQ = 407;
    static final int OP_GRT = 405;
    static final int OP_GRTGRT = 410;
    static final int OP_GT = 430;
    static final int OP_IDIV = 417;
    static final int OP_INSTANCEOF = 422;
    static final int OP_INTERSECT = 420;
    static final int OP_IS = 408;
    static final int OP_ISNOT = 409;
    static final int OP_ITEM = 237;
    static final int OP_LE = 429;
    static final int OP_LEQ = 406;
    static final int OP_LSS = 404;
    static final int OP_LSSLSS = 411;
    static final int OP_LT = 428;
    static final int OP_MOD = 418;
    static final int OP_MUL = 415;
    static final int OP_NE = 427;
    static final int OP_NEQ = 403;
    static final int OP_NODE = 230;
    static final int OP_OR = 400;
    static final int OP_PI = 233;
    static final int OP_RANGE_TO = 412;
    static final int OP_SCHEMA_ATTRIBUTE = 239;
    static final int OP_SCHEMA_ELEMENT = 240;
    static final int OP_SUB = 414;
    static final int OP_TEXT = 231;
    static final int OP_TREAT_AS = 423;
    static final int OP_UNION = 419;
    static final int OP_WHERE = 196;
    static final int ORDERED_LBRACE_TOKEN = 249;
    static final int PI_TOKEN = 255;
    static final Symbol POSITION_VARNAME = Symbol.makeUninterned("$position$");
    static final int PRAGMA_START_TOKEN = 197;
    static final int QNAME_TOKEN = 81;
    static final int SLASHSLASH_TOKEN = 68;
    static final int SOME_DOLLAR_TOKEN = 245;
    static final int STRING_TOKEN = 34;
    static final int TEXT_TOKEN = 253;
    static final int TYPESWITCH_LPAREN_TOKEN = 242;
    static final int UNORDERED_LBRACE_TOKEN = 250;
    static final int VALIDATE_LBRACE_TOKEN = 248;
    static final int XQUERY_VERSION_TOKEN = 89;
    public static final String[] axisNames;
    static NamespaceBinding builtinNamespaces = new NamespaceBinding("local", XQuery.LOCAL_NAMESPACE, new NamespaceBinding("qexo", XQuery.QEXO_FUNCTION_NAMESPACE, new NamespaceBinding("kawa", XQuery.KAWA_FUNCTION_NAMESPACE, new NamespaceBinding("html", "http://www.w3.org/1999/xhtml", new NamespaceBinding("fn", XQuery.XQUERY_FUNCTION_NAMESPACE, new NamespaceBinding("xsi", XQuery.SCHEMA_INSTANCE_NAMESPACE, new NamespaceBinding("xs", XQuery.SCHEMA_NAMESPACE, new NamespaceBinding("xml", NamespaceBinding.XML_NAMESPACE, NamespaceBinding.predefinedXML))))))));
    public static final CastableAs castableAs = CastableAs.castableAs;
    public static final QuoteExp getExternalFunction = QuoteExp.getInstance(new PrimProcedure("gnu.xquery.lang.XQuery", "getExternal", 2));
    public static final InstanceOf instanceOf = new InstanceOf(XQuery.getInstance(), "instance");
    static final Expression makeCDATA = makeFunctionExp("gnu.kawa.xml.MakeCDATA", "makeCDATA");
    public static QuoteExp makeChildAxisStep = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml.ChildAxis", "make", 1));
    public static QuoteExp makeDescendantAxisStep = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml.DescendantAxis", "make", 1));
    public static Expression makeText = makeFunctionExp("gnu.kawa.xml.MakeText", "makeText");
    static PrimProcedure proc_OccurrenceType_getInstance = new PrimProcedure(ClassType.make("gnu.kawa.reflect.OccurrenceType").getDeclaredMethod("getInstance", 3));
    public static final Convert treatAs = Convert.as;
    public static boolean warnHidePreviousDeclaration = false;
    public static boolean warnOldVersion = true;
    Path baseURI = null;
    boolean baseURIDeclarationSeen;
    boolean boundarySpaceDeclarationSeen;
    boolean boundarySpacePreserve;
    int commentCount;
    Compilation comp;
    boolean constructionModeDeclarationSeen;
    boolean constructionModeStrip;
    NamespaceBinding constructorNamespaces = NamespaceBinding.predefinedXML;
    boolean copyNamespacesDeclarationSeen;
    int copyNamespacesMode = 3;
    int curColumn;
    int curLine;
    int curToken;
    Object curValue;
    NamedCollator defaultCollator = null;
    String defaultElementNamespace = "";
    char defaultEmptyOrder = 'L';
    boolean emptyOrderDeclarationSeen;
    int enclosedExpressionsSeen;
    String errorIfComment;
    Declaration[] flworDecls;
    int flworDeclsCount;
    int flworDeclsFirst;
    public Namespace[] functionNamespacePath = XQuery.defaultFunctionNamespacePath;
    XQuery interpreter;
    String libraryModuleNamespace;
    boolean orderingModeSeen;
    boolean orderingModeUnordered;
    int parseContext;
    int parseCount;
    NamespaceBinding prologNamespaces;
    private int saveToken;
    private Object saveValue;
    boolean seenDeclaration;
    int seenLast;
    int seenPosition;
    private boolean warnedOldStyleKindTest;

    private static final int priority(int i) {
        switch (i) {
            case 400:
                return 1;
            case 401:
                return 2;
            case 402:
            case 403:
            case 404:
            case 405:
            case 406:
            case 407:
            case 408:
            case 409:
            case 410:
            case 411:
            case OP_EQ /*426*/:
            case OP_NE /*427*/:
            case OP_LT /*428*/:
            case OP_LE /*429*/:
            case OP_GT /*430*/:
            case OP_GE /*431*/:
                return 3;
            case 412:
                return 4;
            case 413:
            case 414:
                return 5;
            case 415:
            case 416:
            case 417:
            case 418:
                return 6;
            case 419:
                return 7;
            case 420:
            case 421:
                return 8;
            case 422:
                return 9;
            case 423:
                return 10;
            case OP_CASTABLE_AS /*424*/:
                return 11;
            case OP_CAST_AS /*425*/:
                return 12;
            default:
                return 0;
        }
    }

    public void handleOption(Symbol symbol, String str) {
    }

    static {
        String[] strArr = new String[13];
        axisNames = strArr;
        strArr[0] = "ancestor";
        strArr[1] = "ancestor-or-self";
        strArr[2] = "attribute";
        strArr[3] = "child";
        strArr[4] = "descendant";
        strArr[5] = "descendant-or-self";
        strArr[6] = "following";
        strArr[7] = "following-sibling";
        strArr[8] = "namespace";
        strArr[9] = "parent";
        strArr[10] = "preceding";
        strArr[11] = "preceding-sibling";
        strArr[12] = "self";
    }

    public void setStaticBaseUri(String str) {
        try {
            this.baseURI = fixupStaticBaseUri(URIPath.valueOf(str));
        } catch (Throwable th) {
            th = th;
            if (th instanceof WrappedException) {
                th = ((WrappedException) th).getCause();
            }
            error('e', "invalid URI: " + th.getMessage());
        }
    }

    static Path fixupStaticBaseUri(Path path) {
        Path absolute = path.getAbsolute();
        return absolute instanceof FilePath ? URIPath.valueOf(absolute.toURI()) : absolute;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if ((r1 instanceof gnu.mapping.CharArrayInPort) != false) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getStaticBaseUri() {
        /*
            r4 = this;
            gnu.text.Path r0 = r4.baseURI
            if (r0 != 0) goto L_0x0050
            gnu.mapping.Environment r1 = gnu.mapping.Environment.getCurrent()
            java.lang.String r2 = ""
            java.lang.String r3 = "base-uri"
            gnu.mapping.Symbol r2 = gnu.mapping.Symbol.make(r2, r3)
            r3 = 0
            java.lang.Object r1 = r1.get(r2, r3, r3)
            if (r1 == 0) goto L_0x0024
            boolean r2 = r1 instanceof gnu.text.Path
            if (r2 == 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            java.lang.String r0 = r1.toString()
            gnu.text.URIPath r0 = gnu.text.URIPath.valueOf((java.lang.String) r0)
        L_0x0024:
            if (r0 != 0) goto L_0x0043
            gnu.text.LineBufferedReader r1 = r4.getPort()
            if (r1 == 0) goto L_0x0043
            gnu.text.Path r0 = r1.getPath()
            boolean r2 = r0 instanceof gnu.text.FilePath
            if (r2 == 0) goto L_0x0043
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x0044
            boolean r2 = r1 instanceof gnu.mapping.TtyInPort
            if (r2 != 0) goto L_0x0044
            boolean r1 = r1 instanceof gnu.mapping.CharArrayInPort
            if (r1 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r3 = r0
        L_0x0044:
            if (r3 != 0) goto L_0x004a
            gnu.text.Path r3 = gnu.text.Path.currentPath()
        L_0x004a:
            gnu.text.Path r0 = fixupStaticBaseUri(r3)
            r4.baseURI = r0
        L_0x0050:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.getStaticBaseUri():java.lang.String");
    }

    public String resolveAgainstBaseUri(String str) {
        if (Path.uriSchemeSpecified(str)) {
            return str;
        }
        return Path.valueOf(getStaticBaseUri()).resolve(str).toString();
    }

    /* access modifiers changed from: package-private */
    public final int skipSpace() throws IOException, SyntaxException {
        return skipSpace(true);
    }

    /* access modifiers changed from: package-private */
    public final int skipSpace(boolean z) throws IOException, SyntaxException {
        int read;
        while (true) {
            read = read();
            if (read != 40) {
                if (read != 123) {
                    if (!z) {
                        if (!(read == 32 || read == 9)) {
                            break;
                        }
                    } else if (read < 0 || !Character.isWhitespace((char) read)) {
                        break;
                    }
                } else {
                    int read2 = read();
                    if (read2 != 45) {
                        unread(read2);
                        return 123;
                    }
                    int read3 = read();
                    if (read3 != 45) {
                        unread(read3);
                        unread(45);
                        return 123;
                    }
                    skipOldComment();
                }
            } else if (!checkNext(':')) {
                return 40;
            } else {
                skipComment();
            }
        }
        return read;
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    final void skipToSemicolon() throws java.io.IOException {
        /*
            r2 = this;
        L_0x0000:
            int r0 = r2.read()
            if (r0 < 0) goto L_0x000a
            r1 = 59
            if (r0 != r1) goto L_0x0000
        L_0x000a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.skipToSemicolon():void");
    }

    /* access modifiers changed from: package-private */
    public final void skipOldComment() throws IOException, SyntaxException {
        int lineNumber = getLineNumber() + 1;
        int columnNumber = getColumnNumber() - 2;
        warnOldVersion("use (: :) instead of old-style comment {-- --}");
        while (true) {
            int i = 0;
            while (true) {
                int read = read();
                if (read == 45) {
                    i++;
                } else if (read == 125 && i >= 2) {
                    return;
                } else {
                    if (read < 0) {
                        this.curLine = lineNumber;
                        this.curColumn = columnNumber;
                        eofError("non-terminated comment starting here");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void skipComment() throws IOException, SyntaxException {
        this.commentCount++;
        int lineNumber = getLineNumber() + 1;
        int columnNumber = getColumnNumber() - 1;
        String str = this.errorIfComment;
        if (str != null) {
            this.curLine = lineNumber;
            this.curColumn = columnNumber;
            error('e', str);
        }
        char pushNesting = pushNesting(':');
        int i = 0;
        int i2 = 0;
        while (true) {
            int read = read();
            if (read == 58) {
                if (i == 40) {
                    i2++;
                    i = 0;
                }
            } else if (read == 41 && i == 58) {
                if (i2 == 0) {
                    popNesting(pushNesting);
                    return;
                }
                i2--;
            } else if (read < 0) {
                this.curLine = lineNumber;
                this.curColumn = columnNumber;
                eofError("non-terminated comment starting here");
            }
            i = read;
        }
    }

    /* access modifiers changed from: package-private */
    public final int peekNonSpace(String str) throws IOException, SyntaxException {
        int skipSpace = skipSpace();
        if (skipSpace < 0) {
            eofError(str);
        }
        unread(skipSpace);
        return skipSpace;
    }

    public void mark() throws IOException {
        super.mark();
        this.saveToken = this.curToken;
        this.saveValue = this.curValue;
    }

    public void reset() throws IOException {
        this.curToken = this.saveToken;
        this.curValue = this.saveValue;
        super.reset();
    }

    private int setToken(int i, int i2) {
        this.curToken = i;
        this.curLine = this.port.getLineNumber() + 1;
        this.curColumn = (this.port.getColumnNumber() + 1) - i2;
        return i;
    }

    /* access modifiers changed from: package-private */
    public void checkSeparator(char c) {
        if (XName.isNameStart(c)) {
            error('e', "missing separator", "XPST0003");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0180 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0171  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getRawToken() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
        L_0x0000:
            int r0 = r11.readUnicodeChar()
            r1 = 0
            if (r0 >= 0) goto L_0x000d
            r0 = -1
            int r0 = r11.setToken(r0, r1)
            return r0
        L_0x000d:
            r2 = 10
            if (r0 == r2) goto L_0x026f
            r3 = 13
            if (r0 != r3) goto L_0x0017
            goto L_0x026f
        L_0x0017:
            r2 = 40
            r3 = 58
            r4 = 1
            if (r0 != r2) goto L_0x003d
            boolean r0 = r11.checkNext(r3)
            if (r0 == 0) goto L_0x0028
            r11.skipComment()
            goto L_0x0000
        L_0x0028:
            r0 = 35
            boolean r0 = r11.checkNext(r0)
            if (r0 == 0) goto L_0x0038
            r0 = 197(0xc5, float:2.76E-43)
            r1 = 2
            int r0 = r11.setToken(r0, r1)
            return r0
        L_0x0038:
            int r0 = r11.setToken(r2, r4)
            return r0
        L_0x003d:
            r2 = 45
            r5 = 123(0x7b, float:1.72E-43)
            if (r0 != r5) goto L_0x0063
            boolean r0 = r11.checkNext(r2)
            if (r0 != 0) goto L_0x004e
            int r0 = r11.setToken(r5, r4)
            return r0
        L_0x004e:
            int r0 = r11.read()
            if (r0 == r2) goto L_0x005f
            r11.unread()
            r11.unread()
            int r0 = r11.setToken(r5, r4)
            return r0
        L_0x005f:
            r11.skipOldComment()
            goto L_0x0000
        L_0x0063:
            r5 = 32
            if (r0 == r5) goto L_0x0000
            r6 = 9
            if (r0 == r6) goto L_0x0000
            r11.tokenBufferLength = r1
            gnu.text.LineBufferedReader r6 = r11.port
            int r6 = r6.getLineNumber()
            int r6 = r6 + r4
            r11.curLine = r6
            gnu.text.LineBufferedReader r6 = r11.port
            int r6 = r6.getColumnNumber()
            r11.curColumn = r6
            char r0 = (char) r0
            r6 = 33
            r7 = 34
            r8 = 65
            r9 = 61
            if (r0 == r6) goto L_0x0264
            if (r0 == r7) goto L_0x0237
            r6 = 36
            if (r0 == r6) goto L_0x026c
            r6 = 39
            if (r0 == r6) goto L_0x0237
            r7 = 47
            if (r0 == r7) goto L_0x022e
            r7 = 91
            if (r0 == r7) goto L_0x026c
            r7 = 93
            if (r0 == r7) goto L_0x026c
            r7 = 124(0x7c, float:1.74E-43)
            if (r0 == r7) goto L_0x022b
            r7 = 125(0x7d, float:1.75E-43)
            if (r0 == r7) goto L_0x026c
            switch(r0) {
                case 41: goto L_0x026c;
                case 42: goto L_0x0228;
                case 43: goto L_0x0225;
                case 44: goto L_0x026c;
                case 45: goto L_0x0222;
                default: goto L_0x00aa;
            }
        L_0x00aa:
            r7 = 62
            switch(r0) {
                case 58: goto L_0x0210;
                case 59: goto L_0x026c;
                case 60: goto L_0x01f7;
                case 61: goto L_0x01f0;
                case 62: goto L_0x01d8;
                case 63: goto L_0x026c;
                case 64: goto L_0x026c;
                default: goto L_0x00af;
            }
        L_0x00af:
            boolean r7 = java.lang.Character.isDigit(r0)
            r10 = 46
            if (r7 != 0) goto L_0x0163
            if (r0 != r10) goto L_0x00c6
            int r7 = r11.peek()
            char r7 = (char) r7
            boolean r7 = java.lang.Character.isDigit(r7)
            if (r7 == 0) goto L_0x00c6
            goto L_0x0163
        L_0x00c6:
            if (r0 != r10) goto L_0x00d2
            boolean r1 = r11.checkNext(r10)
            if (r1 == 0) goto L_0x026c
            r0 = 51
            goto L_0x026c
        L_0x00d2:
            boolean r1 = gnu.xml.XName.isNameStart(r0)
            if (r1 == 0) goto L_0x0127
        L_0x00d8:
            r11.tokenBufferAppend(r0)
            int r0 = r11.read()
            char r1 = (char) r0
            boolean r2 = gnu.xml.XName.isNamePart(r1)
            if (r2 != 0) goto L_0x0125
            if (r0 >= 0) goto L_0x00ec
            r0 = 65
            goto L_0x026c
        L_0x00ec:
            if (r0 == r3) goto L_0x00f1
        L_0x00ee:
            r1 = 65
            goto L_0x011f
        L_0x00f1:
            int r0 = r11.read()
            if (r0 >= 0) goto L_0x00fc
            java.lang.String r1 = "unexpected end-of-file after NAME ':'"
            r11.eofError(r1)
        L_0x00fc:
            char r1 = (char) r0
            boolean r2 = gnu.xml.XName.isNameStart(r1)
            if (r2 == 0) goto L_0x0117
            r11.tokenBufferAppend(r3)
        L_0x0106:
            r11.tokenBufferAppend(r1)
            int r0 = r11.read()
            char r1 = (char) r0
            boolean r2 = gnu.xml.XName.isNamePart(r1)
            if (r2 != 0) goto L_0x0106
            r1 = 81
            goto L_0x011f
        L_0x0117:
            if (r1 != r9) goto L_0x011d
            r11.unread(r1)
            goto L_0x00ee
        L_0x011d:
            r1 = 67
        L_0x011f:
            r11.unread(r0)
        L_0x0122:
            r0 = r1
            goto L_0x026c
        L_0x0125:
            r0 = r1
            goto L_0x00d8
        L_0x0127:
            if (r0 < r5) goto L_0x0146
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 >= r1) goto L_0x0146
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "invalid character '"
            r1.append(r2)
            r1.append(r0)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r11.syntaxError(r1)
            goto L_0x026c
        L_0x0146:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "invalid character '\\u"
            r1.append(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r0)
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r11.syntaxError(r1)
            goto L_0x026c
        L_0x0163:
            if (r0 != r10) goto L_0x0166
            goto L_0x0178
        L_0x0166:
            r3 = 0
        L_0x0167:
            r11.tokenBufferAppend(r0)
            int r0 = r11.read()
            if (r0 >= 0) goto L_0x0171
            goto L_0x0180
        L_0x0171:
            char r5 = (char) r0
            if (r5 != r10) goto L_0x017a
            if (r3 == 0) goto L_0x0177
            goto L_0x0180
        L_0x0177:
            r0 = r5
        L_0x0178:
            r3 = 1
            goto L_0x0167
        L_0x017a:
            boolean r6 = java.lang.Character.isDigit(r5)
            if (r6 != 0) goto L_0x01d6
        L_0x0180:
            r6 = 101(0x65, float:1.42E-43)
            if (r0 == r6) goto L_0x019a
            r4 = 69
            if (r0 != r4) goto L_0x0189
            goto L_0x019a
        L_0x0189:
            if (r3 == 0) goto L_0x018e
            r1 = 49
            goto L_0x0190
        L_0x018e:
            r1 = 48
        L_0x0190:
            if (r0 < 0) goto L_0x0122
            char r2 = (char) r0
            r11.checkSeparator(r2)
            r11.unread(r0)
            goto L_0x0122
        L_0x019a:
            char r0 = (char) r0
            r11.tokenBufferAppend(r0)
            int r0 = r11.read()
            r3 = 43
            if (r0 == r3) goto L_0x01a8
            if (r0 != r2) goto L_0x01af
        L_0x01a8:
            r11.tokenBufferAppend(r0)
            int r0 = r11.read()
        L_0x01af:
            if (r0 >= 0) goto L_0x01b2
            goto L_0x01bf
        L_0x01b2:
            char r0 = (char) r0
            boolean r2 = java.lang.Character.isDigit(r0)
            if (r2 != 0) goto L_0x01cc
            r11.checkSeparator(r0)
            r11.unread()
        L_0x01bf:
            if (r1 != 0) goto L_0x01c8
            java.lang.String r0 = "no digits following exponent"
            java.lang.String r1 = "XPST0003"
            r11.error(r6, r0, r1)
        L_0x01c8:
            r0 = 50
            goto L_0x026c
        L_0x01cc:
            r11.tokenBufferAppend(r0)
            int r0 = r11.read()
            int r1 = r1 + 1
            goto L_0x01af
        L_0x01d6:
            r0 = r5
            goto L_0x0167
        L_0x01d8:
            boolean r0 = r11.checkNext(r9)
            if (r0 == 0) goto L_0x01e2
            r0 = 407(0x197, float:5.7E-43)
            goto L_0x026c
        L_0x01e2:
            boolean r0 = r11.checkNext(r7)
            if (r0 == 0) goto L_0x01ec
            r0 = 410(0x19a, float:5.75E-43)
            goto L_0x026c
        L_0x01ec:
            r0 = 405(0x195, float:5.68E-43)
            goto L_0x026c
        L_0x01f0:
            r11.checkNext(r7)
            r0 = 402(0x192, float:5.63E-43)
            goto L_0x026c
        L_0x01f7:
            boolean r0 = r11.checkNext(r9)
            if (r0 == 0) goto L_0x0201
            r0 = 406(0x196, float:5.69E-43)
            goto L_0x026c
        L_0x0201:
            r0 = 60
            boolean r0 = r11.checkNext(r0)
            if (r0 == 0) goto L_0x020d
            r0 = 411(0x19b, float:5.76E-43)
            goto L_0x026c
        L_0x020d:
            r0 = 404(0x194, float:5.66E-43)
            goto L_0x026c
        L_0x0210:
            boolean r1 = r11.checkNext(r9)
            if (r1 == 0) goto L_0x0219
            r0 = 76
            goto L_0x026c
        L_0x0219:
            boolean r1 = r11.checkNext(r3)
            if (r1 == 0) goto L_0x026c
            r0 = 88
            goto L_0x026c
        L_0x0222:
            r0 = 414(0x19e, float:5.8E-43)
            goto L_0x026c
        L_0x0225:
            r0 = 413(0x19d, float:5.79E-43)
            goto L_0x026c
        L_0x0228:
            r0 = 415(0x19f, float:5.82E-43)
            goto L_0x026c
        L_0x022b:
            r0 = 419(0x1a3, float:5.87E-43)
            goto L_0x026c
        L_0x022e:
            boolean r1 = r11.checkNext(r7)
            if (r1 == 0) goto L_0x026c
            r0 = 68
            goto L_0x026c
        L_0x0237:
            char r1 = r11.pushNesting(r0)
        L_0x023b:
            int r2 = r11.readUnicodeChar()
            if (r2 >= 0) goto L_0x0246
            java.lang.String r3 = "unexpected end-of-file in string starting here"
            r11.eofError(r3)
        L_0x0246:
            r3 = 38
            if (r2 != r3) goto L_0x024e
            r11.parseEntityOrCharRef()
            goto L_0x023b
        L_0x024e:
            if (r0 != r2) goto L_0x0260
            int r2 = r11.peek()
            if (r0 == r2) goto L_0x025c
            r11.popNesting(r1)
            r0 = 34
            goto L_0x026c
        L_0x025c:
            int r2 = r11.read()
        L_0x0260:
            r11.tokenBufferAppend(r2)
            goto L_0x023b
        L_0x0264:
            boolean r1 = r11.checkNext(r9)
            if (r1 == 0) goto L_0x026c
            r0 = 403(0x193, float:5.65E-43)
        L_0x026c:
            r11.curToken = r0
            return r0
        L_0x026f:
            int r0 = r11.nesting
            if (r0 > 0) goto L_0x0000
            int r0 = r11.setToken(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.getRawToken():int");
    }

    public void getDelimited(String str) throws IOException, SyntaxException {
        if (!readDelimited(str)) {
            eofError("unexpected end-of-file looking for '" + str + '\'');
        }
    }

    public void appendNamedEntity(String str) {
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
            error("unknown enity reference: '" + intern + "'");
            i = 63;
        }
        tokenBufferAppend(i);
    }

    /* access modifiers changed from: package-private */
    public boolean match(String str, String str2, boolean z) throws IOException, SyntaxException {
        if (!match(str)) {
            return false;
        }
        mark();
        getRawToken();
        if (match(str2)) {
            reset();
            getRawToken();
            return true;
        }
        reset();
        if (!z) {
            return false;
        }
        error('e', "'" + str + "' must be followed by '" + str2 + "'", "XPST0003");
        return true;
    }

    /* access modifiers changed from: package-private */
    public int peekOperator() throws IOException, SyntaxException {
        while (true) {
            int i = this.curToken;
            if (i != 10) {
                if (i == 65) {
                    switch (this.tokenBufferLength) {
                        case 2:
                            char c = this.tokenBuffer[0];
                            char c2 = this.tokenBuffer[1];
                            if (c != 'o' || c2 != 'r') {
                                if (c != 't' || c2 != 'o') {
                                    if (c != 'i' || c2 != 's') {
                                        if (c != 'e' || c2 != 'q') {
                                            if (c != 'n' || c2 != 'e') {
                                                if (c != 'g') {
                                                    if (c == 'l') {
                                                        if (c2 != 'e') {
                                                            if (c2 == 't') {
                                                                this.curToken = OP_LT;
                                                                break;
                                                            }
                                                        } else {
                                                            this.curToken = OP_LE;
                                                            break;
                                                        }
                                                    }
                                                } else if (c2 != 'e') {
                                                    if (c2 == 't') {
                                                        this.curToken = OP_GT;
                                                        break;
                                                    }
                                                } else {
                                                    this.curToken = OP_GE;
                                                    break;
                                                }
                                            } else {
                                                this.curToken = OP_NE;
                                                break;
                                            }
                                        } else {
                                            this.curToken = OP_EQ;
                                            break;
                                        }
                                    } else {
                                        this.curToken = 408;
                                        break;
                                    }
                                } else {
                                    this.curToken = 412;
                                    break;
                                }
                            } else {
                                this.curToken = 400;
                                break;
                            }
                            break;
                        case 3:
                            char c3 = this.tokenBuffer[0];
                            char c4 = this.tokenBuffer[1];
                            char c5 = this.tokenBuffer[2];
                            if (c3 != 'a') {
                                if (c3 != 'm') {
                                    if (c3 == 'd' && c4 == 'i' && c5 == 'v') {
                                        this.curToken = 416;
                                        break;
                                    }
                                } else {
                                    if (c4 == 'u' && c5 == 'l') {
                                        this.curToken = 415;
                                    }
                                    if (c4 == 'o' && c5 == 'd') {
                                        this.curToken = 418;
                                        break;
                                    }
                                }
                            } else if (c4 == 'n' && c5 == 'd') {
                                this.curToken = 401;
                                break;
                            }
                        case 4:
                            if (!match("idiv")) {
                                if (match("cast", "as", true)) {
                                    this.curToken = OP_CAST_AS;
                                    break;
                                }
                            } else {
                                this.curToken = 417;
                                break;
                            }
                            break;
                        case 5:
                            if (!match("where")) {
                                if (!match("isnot")) {
                                    if (!match("union")) {
                                        if (match("treat", "as", true)) {
                                            this.curToken = 423;
                                            break;
                                        }
                                    } else {
                                        this.curToken = 419;
                                        break;
                                    }
                                } else {
                                    this.curToken = 409;
                                    break;
                                }
                            } else {
                                this.curToken = 196;
                                break;
                            }
                            break;
                        case 6:
                            if (match("except")) {
                                this.curToken = 421;
                                break;
                            }
                            break;
                        case 8:
                            if (!match("instance", "of", true)) {
                                if (match("castable", "as", true)) {
                                    this.curToken = OP_CASTABLE_AS;
                                    break;
                                }
                            } else {
                                this.curToken = 422;
                                break;
                            }
                            break;
                        case 9:
                            if (match("intersect")) {
                                this.curToken = 420;
                                break;
                            }
                            break;
                        case 10:
                            if (match("instanceof")) {
                                warnOldVersion("use 'instanceof of' (two words) instead of 'instanceof'");
                                this.curToken = 422;
                                break;
                            }
                            break;
                    }
                }
                return this.curToken;
            } else if (this.nesting == 0) {
                return 10;
            } else {
                getRawToken();
            }
        }
    }

    private boolean lookingAt(String str, String str2) throws IOException, SyntaxException {
        if (!str.equals(this.curValue)) {
            return false;
        }
        int length = str2.length();
        int i = 0;
        while (true) {
            int read = read();
            if (i != length) {
                if (read < 0) {
                    break;
                }
                int i2 = i + 1;
                if (read != str2.charAt(i)) {
                    i = i2;
                    break;
                }
                i = i2;
            } else if (read < 0) {
                return true;
            } else {
                if (!XName.isNamePart((char) read)) {
                    unread();
                    return true;
                }
                i++;
            }
        }
        this.port.skip(-i);
        return false;
    }

    /* access modifiers changed from: package-private */
    public int getAxis() {
        String intern = new String(this.tokenBuffer, 0, this.tokenBufferLength).intern();
        int i = 13;
        do {
            i--;
            if (i < 0 || axisNames[i] == intern) {
                if (i < 0 || i == 8) {
                    error('e', "unknown axis name '" + intern + '\'', "XPST0003");
                    i = 3;
                }
            }
            i--;
            break;
        } while (axisNames[i] == intern);
        error('e', "unknown axis name '" + intern + '\'', "XPST0003");
        i = 3;
        return (char) (i + 100);
    }

    /* access modifiers changed from: package-private */
    public int peekOperand() throws IOException, SyntaxException {
        int i;
        while (true) {
            i = this.curToken;
            if (i != 10) {
                break;
            }
            getRawToken();
        }
        if (i == 65 || i == 81) {
            int skipSpace = skipSpace(this.nesting != 0);
            switch (this.tokenBuffer[0]) {
                case 'a':
                    if (match("attribute")) {
                        if (skipSpace == 40) {
                            this.curToken = OP_ATTRIBUTE;
                            return OP_ATTRIBUTE;
                        } else if (skipSpace == 123 || XName.isNameStart((char) skipSpace)) {
                            unread();
                            this.curToken = 252;
                            return 252;
                        }
                    }
                    break;
                case 'c':
                    if (match("comment")) {
                        if (skipSpace == 40) {
                            this.curToken = OP_COMMENT;
                            return OP_COMMENT;
                        } else if (skipSpace == 123) {
                            unread();
                            this.curToken = 254;
                            return 254;
                        }
                    }
                    break;
                case 'd':
                    if (skipSpace == 123 && match("document")) {
                        unread();
                        this.curToken = 256;
                        return 256;
                    } else if (skipSpace == 40 && match("document-node")) {
                        this.curToken = OP_DOCUMENT;
                        return OP_DOCUMENT;
                    }
                    break;
                case 'e':
                    if (match("element")) {
                        if (skipSpace == 40) {
                            this.curToken = OP_ELEMENT;
                            return OP_ELEMENT;
                        } else if (skipSpace == 123 || XName.isNameStart((char) skipSpace)) {
                            unread();
                            this.curToken = 251;
                            return 251;
                        }
                    } else if (skipSpace == 40 && match("empty-sequence")) {
                        this.curToken = OP_EMPTY_SEQUENCE;
                        return OP_EMPTY_SEQUENCE;
                    } else if (skipSpace == 36 && match("every")) {
                        this.curToken = EVERY_DOLLAR_TOKEN;
                        return EVERY_DOLLAR_TOKEN;
                    }
                    break;
                case 'f':
                    if (skipSpace == 36 && match("for")) {
                        this.curToken = FOR_DOLLAR_TOKEN;
                        return FOR_DOLLAR_TOKEN;
                    }
                case 'i':
                    if (skipSpace == 40 && match("if")) {
                        this.curToken = 241;
                        return 241;
                    } else if (skipSpace == 40 && match("item")) {
                        this.curToken = OP_ITEM;
                        return OP_ITEM;
                    }
                    break;
                case 'l':
                    if (skipSpace == 36 && match("let")) {
                        this.curToken = LET_DOLLAR_TOKEN;
                        return LET_DOLLAR_TOKEN;
                    }
                case 'n':
                    if (skipSpace == 40 && match("node")) {
                        this.curToken = OP_NODE;
                        return OP_NODE;
                    }
                case 'o':
                    if (skipSpace == 123 && match("ordered")) {
                        this.curToken = ORDERED_LBRACE_TOKEN;
                        return ORDERED_LBRACE_TOKEN;
                    }
                case 'p':
                    if (match("processing-instruction")) {
                        if (skipSpace == 40) {
                            this.curToken = OP_PI;
                            return OP_PI;
                        } else if (skipSpace == 123 || XName.isNameStart((char) skipSpace)) {
                            unread();
                            this.curToken = 255;
                            return 255;
                        }
                    }
                    break;
                case 's':
                    if (skipSpace == 36 && match("some")) {
                        this.curToken = SOME_DOLLAR_TOKEN;
                        return SOME_DOLLAR_TOKEN;
                    } else if (skipSpace == 40 && match("schema-attribute")) {
                        this.curToken = OP_SCHEMA_ATTRIBUTE;
                        return OP_SCHEMA_ATTRIBUTE;
                    } else if (skipSpace == 40 && match("schema-element")) {
                        this.curToken = OP_SCHEMA_ELEMENT;
                        return OP_SCHEMA_ELEMENT;
                    }
                    break;
                case 't':
                    if (match(PropertyTypeConstants.PROPERTY_TYPE_TEXT)) {
                        if (skipSpace == 40) {
                            this.curToken = OP_TEXT;
                            return OP_TEXT;
                        } else if (skipSpace == 123) {
                            unread();
                            this.curToken = 253;
                            return 253;
                        }
                    }
                    if (skipSpace == 40 && match("typeswitch")) {
                        this.curToken = 242;
                        return 242;
                    }
                case 'u':
                    if (skipSpace == 123 && match("unordered")) {
                        this.curToken = 250;
                        return 250;
                    }
                case 'v':
                    if (skipSpace == 123 && match("validate")) {
                        this.curToken = 248;
                        return 248;
                    }
            }
            if (skipSpace == 40 && peek() != 58) {
                this.curToken = 70;
                return 70;
            } else if (skipSpace == 58 && peek() == 58) {
                int axis = getAxis();
                this.curToken = axis;
                return axis;
            } else {
                this.curValue = new String(this.tokenBuffer, 0, this.tokenBufferLength);
                if (skipSpace != 115) {
                    if (skipSpace != 118) {
                        if (skipSpace != 120) {
                            switch (skipSpace) {
                                case 98:
                                    if (lookingAt("declare", "ase-uri")) {
                                        this.curToken = 66;
                                        return 66;
                                    } else if (lookingAt("declare", "oundary-space")) {
                                        this.curToken = 83;
                                        return 83;
                                    }
                                    break;
                                case 99:
                                    if (lookingAt("declare", "onstruction")) {
                                        this.curToken = 75;
                                        return 75;
                                    } else if (lookingAt("declare", "opy-namespaces")) {
                                        this.curToken = 76;
                                        return 76;
                                    }
                                    break;
                                case 100:
                                    if (lookingAt("declare", "efault")) {
                                        getRawToken();
                                        if (match("function")) {
                                            this.curToken = 79;
                                            return 79;
                                        } else if (match("element")) {
                                            this.curToken = 69;
                                            return 69;
                                        } else if (match("collation")) {
                                            this.curToken = 71;
                                            return 71;
                                        } else if (match("order")) {
                                            this.curToken = 72;
                                            return 72;
                                        } else {
                                            error("unrecognized/unimplemented 'declare default'");
                                            skipToSemicolon();
                                            return peekOperand();
                                        }
                                    }
                                    break;
                                case 101:
                                    break;
                                case 102:
                                    if (lookingAt("declare", "unction")) {
                                        this.curToken = 80;
                                        return 80;
                                    } else if (lookingAt("define", "unction")) {
                                        warnOldVersion("replace 'define function' by 'declare function'");
                                        this.curToken = 80;
                                        return 80;
                                    } else if (lookingAt("default", "unction")) {
                                        warnOldVersion("replace 'default function' by 'declare default function namespace'");
                                        this.curToken = 79;
                                        return 79;
                                    }
                                    break;
                                default:
                                    switch (skipSpace) {
                                        case 109:
                                            if (lookingAt("import", "odule")) {
                                                this.curToken = 73;
                                                return 73;
                                            }
                                            break;
                                        case 110:
                                            if (lookingAt("declare", "amespace")) {
                                                this.curToken = 78;
                                                return 78;
                                            } else if (lookingAt("default", "amespace")) {
                                                warnOldVersion("replace 'default namespace' by 'declare default element namespace'");
                                                this.curToken = 69;
                                                return 69;
                                            } else if (lookingAt("module", "amespace")) {
                                                this.curToken = 77;
                                                return 77;
                                            }
                                            break;
                                        case 111:
                                            if (lookingAt("declare", "rdering")) {
                                                this.curToken = 85;
                                                return 85;
                                            } else if (lookingAt("declare", "ption")) {
                                                this.curToken = 111;
                                                return 111;
                                            }
                                            break;
                                    }
                            }
                            if (lookingAt("default", "lement")) {
                                warnOldVersion("replace 'default element' by 'declare default element namespace'");
                                this.curToken = 69;
                                return 69;
                            }
                        } else if (lookingAt("declare", "mlspace")) {
                            warnOldVersion("replace 'define xmlspace' by 'declare boundary-space'");
                            this.curToken = 83;
                            return 83;
                        }
                    } else if (lookingAt("declare", "ariable")) {
                        this.curToken = 86;
                        return 86;
                    } else if (lookingAt("define", "ariable")) {
                        warnOldVersion("replace 'define variable' by 'declare variable'");
                        this.curToken = 86;
                        return 86;
                    } else if (lookingAt("xquery", "ersion")) {
                        this.curToken = 89;
                        return 89;
                    }
                } else if (lookingAt("import", "chema")) {
                    this.curToken = 84;
                    return 84;
                }
                if (skipSpace >= 0) {
                    unread();
                    if (XName.isNameStart((char) skipSpace) && this.curValue.equals("define")) {
                        getRawToken();
                        this.curToken = 87;
                    }
                }
                return this.curToken;
            }
        } else {
            if (i == 67) {
                int read = read();
                if (read == 58) {
                    this.curToken = getAxis();
                } else {
                    unread(read);
                }
            }
            return this.curToken;
        }
    }

    /* access modifiers changed from: package-private */
    public void checkAllowedNamespaceDeclaration(String str, String str2, boolean z) {
        boolean equals = "xml".equals(str);
        if (NamespaceBinding.XML_NAMESPACE.equals(str2)) {
            if (!equals || !z) {
                error('e', "namespace uri cannot be the same as the prefined xml namespace", "XQST0070");
            }
        } else if (equals || "xmlns".equals(str)) {
            error('e', "namespace prefix cannot be 'xml' or 'xmlns'", "XQST0070");
        }
    }

    /* access modifiers changed from: package-private */
    public void pushNamespace(String str, String str2) {
        if (str2.length() == 0) {
            str2 = null;
        }
        this.prologNamespaces = new NamespaceBinding(str, str2, this.prologNamespaces);
    }

    public XQParser(InPort inPort, SourceMessages sourceMessages, XQuery xQuery) {
        super(inPort, sourceMessages);
        this.interpreter = xQuery;
        this.nesting = 1;
        this.prologNamespaces = builtinNamespaces;
    }

    public void setInteractive(boolean z) {
        if (this.interactive != z) {
            int i = this.nesting;
            this.nesting = z ? i - 1 : i + 1;
        }
        this.interactive = z;
    }

    static Expression makeBinary(Expression expression, Expression expression2, Expression expression3) {
        return new ApplyExp(expression, expression2, expression3);
    }

    static Expression makeExprSequence(Expression expression, Expression expression2) {
        return makeBinary(makeFunctionExp("gnu.kawa.functions.AppendValues", "appendValues"), expression, expression2);
    }

    /* access modifiers changed from: package-private */
    public Expression makeBinary(int i, Expression expression, Expression expression2) throws IOException, SyntaxException {
        Expression expression3;
        switch (i) {
            case 402:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "=");
                break;
            case 403:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "!=");
                break;
            case 404:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "<");
                break;
            case 405:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", ">");
                break;
            case 406:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "<=");
                break;
            case 407:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", ">=");
                break;
            case 408:
                expression3 = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Eq", "is");
                break;
            case 409:
                expression3 = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ne", "isnot");
                break;
            case 410:
                expression3 = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Gr", ">>");
                break;
            case 411:
                expression3 = makeFunctionExp("gnu.kawa.xml.NodeCompare", "$Ls", "<<");
                break;
            case 412:
                expression3 = makeFunctionExp("gnu.xquery.util.IntegerRange", "integerRange");
                break;
            case 413:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", Component.DEFAULT_VALUE_FAB_ICON_NAME, Marker.ANY_NON_NULL_MARKER);
                break;
            case 414:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", ReservedClaimNames.SUBJECT, "-");
                break;
            case 415:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", "mul", Marker.ANY_MARKER);
                break;
            case 416:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", "div", "div");
                break;
            case 417:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", "idiv", "idiv");
                break;
            case 418:
                expression3 = makeFunctionExp("gnu.xquery.util.ArithOp", "mod", "mod");
                break;
            case 419:
                expression3 = makeFunctionExp("gnu.kawa.xml.UnionNodes", "unionNodes");
                break;
            case 420:
                expression3 = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "intersectNodes");
                break;
            case 421:
                expression3 = makeFunctionExp("gnu.kawa.xml.IntersectNodes", "exceptNodes");
                break;
            case OP_EQ /*426*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valEq", "eq");
                break;
            case OP_NE /*427*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valNe", "ne");
                break;
            case OP_LT /*428*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valLt", "lt");
                break;
            case OP_LE /*429*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valLe", "le");
                break;
            case OP_GT /*430*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valGt", "gt");
                break;
            case OP_GE /*431*/:
                expression3 = makeFunctionExp("gnu.xquery.util.Compare", "valGe", "ge");
                break;
            default:
                return syntaxError("unimplemented binary op: " + i);
        }
        return makeBinary(expression3, expression, expression2);
    }

    private void parseSimpleKindType() throws IOException, SyntaxException {
        getRawToken();
        if (this.curToken == 41) {
            getRawToken();
        } else {
            error("expected ')'");
        }
    }

    public Expression parseNamedNodeType(boolean z) throws IOException, SyntaxException {
        Expression expression;
        getRawToken();
        int i = this.curToken;
        Expression expression2 = null;
        if (i == 41) {
            expression = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
            getRawToken();
        } else {
            if (i == 81 || i == 65) {
                expression = parseNameTest(z);
            } else {
                if (i != 415) {
                    syntaxError("expected QName or *");
                }
                expression = QuoteExp.getInstance(ElementType.MATCH_ANY_QNAME);
            }
            getRawToken();
            if (this.curToken == 44) {
                getRawToken();
                int i2 = this.curToken;
                if (i2 == 81 || i2 == 65) {
                    expression2 = parseDataType();
                } else {
                    syntaxError("expected QName");
                }
            }
            if (this.curToken == 41) {
                getRawToken();
            } else {
                error("expected ')' after element");
            }
        }
        return makeNamedNodeType(z, expression, expression2);
    }

    static Expression makeNamedNodeType(boolean z, Expression expression, Expression expression2) {
        ApplyExp applyExp = new ApplyExp(ClassType.make(z ? "gnu.kawa.xml.AttributeType" : "gnu.kawa.xml.ElementType").getDeclaredMethod("make", 1), expression);
        applyExp.setFlag(4);
        if (expression2 == null) {
            return applyExp;
        }
        return new BeginExp(expression2, applyExp);
    }

    private void warnOldStyleKindTest() {
        if (!this.warnedOldStyleKindTest) {
            error('w', "old-style KindTest - first one here");
            this.warnedOldStyleKindTest = true;
        }
    }

    public Expression parseOptionalTypeDeclaration() throws IOException, SyntaxException {
        if (!match("as")) {
            return null;
        }
        getRawToken();
        return parseDataType();
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseDataType() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r8 = this;
            gnu.expr.Expression r0 = r8.parseItemType()
            r1 = -1
            r2 = 415(0x19f, float:5.82E-43)
            r3 = 413(0x19d, float:5.79E-43)
            r4 = 63
            r5 = 0
            r6 = 1
            if (r0 != 0) goto L_0x003a
            int r0 = r8.curToken
            r1 = 238(0xee, float:3.34E-43)
            if (r0 == r1) goto L_0x001c
            java.lang.String r0 = "bad syntax - expected DataType"
            gnu.expr.Expression r0 = r8.syntaxError(r0)
            return r0
        L_0x001c:
            r8.parseSimpleKindType()
            int r0 = r8.curToken
            if (r0 == r4) goto L_0x0030
            if (r0 == r3) goto L_0x0030
            if (r0 != r2) goto L_0x0028
            goto L_0x0030
        L_0x0028:
            gnu.bytecode.Type r0 = gnu.kawa.reflect.OccurrenceType.emptySequenceType
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)
            r1 = 0
            goto L_0x003f
        L_0x0030:
            r8.getRawToken()
            java.lang.String r0 = "occurrence-indicator meaningless after empty-sequence()"
            gnu.expr.Expression r0 = r8.syntaxError(r0)
            return r0
        L_0x003a:
            int r7 = r8.curToken
            if (r7 != r4) goto L_0x0041
            r1 = 1
        L_0x003f:
            r2 = 0
            goto L_0x004a
        L_0x0041:
            if (r7 != r3) goto L_0x0045
        L_0x0043:
            r2 = 1
            goto L_0x004a
        L_0x0045:
            if (r7 != r2) goto L_0x0048
            goto L_0x003f
        L_0x0048:
            r1 = 1
            goto L_0x0043
        L_0x004a:
            int r3 = r8.parseContext
            r4 = 67
            if (r3 != r4) goto L_0x0059
            if (r1 == r6) goto L_0x0059
            java.lang.String r0 = "type to 'cast as' or 'castable as' must be a 'SingleType'"
            gnu.expr.Expression r0 = r8.syntaxError(r0)
            return r0
        L_0x0059:
            if (r2 == r1) goto L_0x0083
            r8.getRawToken()
            r3 = 3
            gnu.expr.Expression[] r3 = new gnu.expr.Expression[r3]
            r3[r5] = r0
            gnu.math.IntNum r0 = gnu.math.IntNum.make((int) r2)
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)
            r3[r6] = r0
            r0 = 2
            gnu.math.IntNum r1 = gnu.math.IntNum.make((int) r1)
            gnu.expr.QuoteExp r1 = gnu.expr.QuoteExp.getInstance(r1)
            r3[r0] = r1
            gnu.expr.ApplyExp r0 = new gnu.expr.ApplyExp
            gnu.expr.PrimProcedure r1 = proc_OccurrenceType_getInstance
            r0.<init>((gnu.mapping.Procedure) r1, (gnu.expr.Expression[]) r3)
            r1 = 4
            r0.setFlag(r1)
        L_0x0083:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseDataType():gnu.expr.Expression");
    }

    public Expression parseMaybeKindTest() throws IOException, SyntaxException {
        Object obj;
        int i = this.curToken;
        boolean z = false;
        String str = null;
        switch (i) {
            case OP_NODE /*230*/:
                parseSimpleKindType();
                obj = NodeType.anyNodeTest;
                break;
            case OP_TEXT /*231*/:
                parseSimpleKindType();
                obj = NodeType.textNodeTest;
                break;
            case OP_COMMENT /*232*/:
                parseSimpleKindType();
                obj = NodeType.commentNodeTest;
                break;
            case OP_PI /*233*/:
                getRawToken();
                int i2 = this.curToken;
                if (i2 == 65 || i2 == 34) {
                    str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
                    getRawToken();
                }
                if (this.curToken == 41) {
                    getRawToken();
                } else {
                    error("expected ')'");
                }
                obj = ProcessingInstructionType.getInstance(str);
                break;
            case OP_DOCUMENT /*234*/:
                parseSimpleKindType();
                obj = NodeType.documentNodeTest;
                break;
            case OP_ELEMENT /*235*/:
            case OP_ATTRIBUTE /*236*/:
                if (i == OP_ATTRIBUTE) {
                    z = true;
                }
                return parseNamedNodeType(z);
            default:
                return null;
        }
        return QuoteExp.getInstance(obj);
    }

    public Expression parseItemType() throws IOException, SyntaxException {
        Object obj;
        peekOperand();
        Expression parseMaybeKindTest = parseMaybeKindTest();
        if (parseMaybeKindTest == null) {
            int i = this.curToken;
            if (i == OP_ITEM) {
                parseSimpleKindType();
                obj = SingletonType.getInstance();
            } else if (i != 65 && i != 81) {
                return null;
            } else {
                ReferenceExp referenceExp = new ReferenceExp((Object) new String(this.tokenBuffer, 0, this.tokenBufferLength));
                referenceExp.setFlag(16);
                maybeSetLine((Expression) referenceExp, this.curLine, this.curColumn);
                getRawToken();
                return referenceExp;
            }
        } else if (this.parseContext != 67) {
            return parseMaybeKindTest;
        } else {
            obj = XDataType.anyAtomicType;
        }
        return QuoteExp.getInstance(obj);
    }

    /* access modifiers changed from: package-private */
    public Object parseURILiteral() throws IOException, SyntaxException {
        getRawToken();
        if (this.curToken != 34) {
            return declError("expected a URILiteral");
        }
        return TextUtils.replaceWhitespace(new String(this.tokenBuffer, 0, this.tokenBufferLength), true);
    }

    /* access modifiers changed from: package-private */
    public Expression parseExpr() throws IOException, SyntaxException {
        return parseExprSingle();
    }

    /* access modifiers changed from: package-private */
    public final Expression parseExprSingle() throws IOException, SyntaxException {
        peekOperand();
        switch (this.curToken) {
            case 241:
                return parseIfExpr();
            case 242:
                return parseTypeSwitch();
            case FOR_DOLLAR_TOKEN /*243*/:
                return parseFLWRExpression(true);
            case LET_DOLLAR_TOKEN /*244*/:
                return parseFLWRExpression(false);
            case SOME_DOLLAR_TOKEN /*245*/:
                return parseQuantifiedExpr(false);
            case EVERY_DOLLAR_TOKEN /*246*/:
                return parseQuantifiedExpr(true);
            default:
                return parseBinaryExpr(priority(400));
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseBinaryExpr(int i) throws IOException, SyntaxException {
        int priority;
        Expression expression;
        Expression expression2;
        Expression parseUnaryExpr = parseUnaryExpr();
        while (true) {
            int peekOperator = peekOperator();
            if (peekOperator == 10 || ((peekOperator == 404 && peek() == 47) || (priority = priority(peekOperator)) < i)) {
                return parseUnaryExpr;
            }
            char pushNesting = pushNesting('%');
            getRawToken();
            popNesting(pushNesting);
            if (peekOperator >= 422 && peekOperator <= OP_CAST_AS) {
                if (peekOperator == OP_CAST_AS || peekOperator == OP_CASTABLE_AS) {
                    this.parseContext = 67;
                }
                Expression parseDataType = parseDataType();
                this.parseContext = 0;
                Expression[] expressionArr = new Expression[2];
                switch (peekOperator) {
                    case 422:
                        expressionArr[0] = parseUnaryExpr;
                        expressionArr[1] = parseDataType;
                        expression2 = makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf");
                        break;
                    case 423:
                        expressionArr[0] = parseDataType;
                        expressionArr[1] = parseUnaryExpr;
                        expression2 = makeFunctionExp("gnu.xquery.lang.XQParser", "treatAs");
                        break;
                    case OP_CASTABLE_AS /*424*/:
                        expressionArr[0] = parseUnaryExpr;
                        expressionArr[1] = parseDataType;
                        expression2 = new ReferenceExp(XQResolveNames.castableAsDecl);
                        break;
                    default:
                        expressionArr[0] = parseDataType;
                        expressionArr[1] = parseUnaryExpr;
                        expression2 = new ReferenceExp(XQResolveNames.castAsDecl);
                        break;
                }
                expression = new ApplyExp(expression2, expressionArr);
            } else if (peekOperator == 422) {
                parseUnaryExpr = new ApplyExp(makeFunctionExp("gnu.xquery.lang.XQParser", "instanceOf"), parseUnaryExpr, parseDataType());
            } else {
                Expression parseBinaryExpr = parseBinaryExpr(priority + 1);
                if (peekOperator == 401) {
                    expression = new IfExp(booleanValue(parseUnaryExpr), booleanValue(parseBinaryExpr), XQuery.falseExp);
                } else if (peekOperator == 400) {
                    expression = new IfExp(booleanValue(parseUnaryExpr), XQuery.trueExp, booleanValue(parseBinaryExpr));
                } else {
                    parseUnaryExpr = makeBinary(peekOperator, parseUnaryExpr, parseBinaryExpr);
                }
            }
            parseUnaryExpr = expression;
        }
        return parseUnaryExpr;
    }

    /* access modifiers changed from: package-private */
    public Expression parseUnaryExpr() throws IOException, SyntaxException {
        int i = this.curToken;
        if (i != 414 && i != 413) {
            return parseUnionExpr();
        }
        getRawToken();
        return new ApplyExp(makeFunctionExp("gnu.xquery.util.ArithOp", i == 413 ? "plus" : "minus", i == 413 ? Marker.ANY_NON_NULL_MARKER : "-"), parseUnaryExpr());
    }

    /* access modifiers changed from: package-private */
    public Expression parseUnionExpr() throws IOException, SyntaxException {
        Expression parseIntersectExceptExpr = parseIntersectExceptExpr();
        while (true) {
            int peekOperator = peekOperator();
            if (peekOperator != 419) {
                return parseIntersectExceptExpr;
            }
            getRawToken();
            parseIntersectExceptExpr = makeBinary(peekOperator, parseIntersectExceptExpr, parseIntersectExceptExpr());
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseIntersectExceptExpr() throws IOException, SyntaxException {
        Expression parsePathExpr = parsePathExpr();
        while (true) {
            int peekOperator = peekOperator();
            if (peekOperator != 420 && peekOperator != 421) {
                return parsePathExpr;
            }
            getRawToken();
            parsePathExpr = makeBinary(peekOperator, parsePathExpr, parsePathExpr());
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parsePathExpr() throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        int i = this.curToken;
        if (i == 47 || i == 68) {
            NameLookup nameLookup = this.comp.lexical;
            Symbol symbol = DOT_VARNAME;
            boolean z = false;
            Declaration lookup = nameLookup.lookup((Object) symbol, false);
            if (lookup == null) {
                expression2 = syntaxError("context item is undefined", "XPDY0002");
            } else {
                expression2 = new ReferenceExp(symbol, lookup);
            }
            ApplyExp applyExp = new ApplyExp(ClassType.make("gnu.xquery.util.NodeUtils").getDeclaredMethod("rootDocument", 1), expression2);
            if (this.nesting != 0) {
                z = true;
            }
            int skipSpace = skipSpace(z);
            unread(skipSpace);
            if (skipSpace < 0 || skipSpace == 41 || skipSpace == 125) {
                getRawToken();
                return applyExp;
            }
            expression = applyExp;
        } else {
            expression = parseStepExpr();
        }
        return parseRelativePathExpr(expression);
    }

    /* access modifiers changed from: package-private */
    public Expression parseNameTest(boolean z) throws IOException, SyntaxException {
        String str;
        int i = this.curToken;
        String str2 = "";
        String str3 = null;
        if (i == 81) {
            int i2 = this.tokenBufferLength;
            do {
                i2--;
            } while (this.tokenBuffer[i2] != ':');
            str3 = new String(this.tokenBuffer, 0, i2);
            int i3 = i2 + 1;
            str = new String(this.tokenBuffer, i3, this.tokenBufferLength - i3);
        } else if (i == 415) {
            int read = read();
            if (read != 58) {
                unread(read);
            } else {
                int read2 = read();
                if (read2 < 0) {
                    eofError("unexpected end-of-file after '*:'");
                }
                if (XName.isNameStart((char) read2)) {
                    unread();
                    getRawToken();
                    if (this.curToken != 65) {
                        syntaxError("invalid name test");
                    } else {
                        str2 = new String(this.tokenBuffer, 0, this.tokenBufferLength).intern();
                    }
                } else if (read2 != 42) {
                    syntaxError("missing local-name after '*:'");
                }
            }
            return QuoteExp.getInstance(new Symbol((Namespace) null, str2));
        } else if (i == 65) {
            str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
            if (z) {
                return new QuoteExp(Namespace.EmptyNamespace.getSymbol(str.intern()));
            }
        } else if (i == 67) {
            str3 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
            if (read() != 42) {
                syntaxError("invalid characters after 'NCName:'");
            }
            str = str2;
        } else {
            str = null;
        }
        if (str3 != null) {
            str3 = str3.intern();
        }
        Expression[] expressionArr = new Expression[3];
        expressionArr[0] = new ApplyExp((Expression) new ReferenceExp(XQResolveNames.resolvePrefixDecl), QuoteExp.getInstance(str3));
        if (str != null) {
            str2 = str;
        }
        expressionArr[1] = new QuoteExp(str2);
        expressionArr[2] = new QuoteExp(str3);
        ApplyExp applyExp = new ApplyExp(Compilation.typeSymbol.getDeclaredMethod("make", 3), expressionArr);
        applyExp.setFlag(4);
        return applyExp;
    }

    /* access modifiers changed from: package-private */
    public Expression parseNodeTest(int i) throws IOException, SyntaxException {
        Expression expression;
        QuoteExp quoteExp;
        String str;
        peekOperand();
        Expression[] expressionArr = new Expression[1];
        Expression parseMaybeKindTest = parseMaybeKindTest();
        if (parseMaybeKindTest != null) {
            expressionArr[0] = parseMaybeKindTest;
        } else {
            int i2 = this.curToken;
            if (i2 == 65 || i2 == 81 || i2 == 67 || i2 == 415) {
                expressionArr[0] = makeNamedNodeType(i == 2, parseNameTest(i == 2), (Expression) null);
            } else if (i < 0) {
                return null;
            } else {
                return syntaxError("unsupported axis '" + axisNames[i] + "::'");
            }
        }
        NameLookup nameLookup = this.comp.lexical;
        Symbol symbol = DOT_VARNAME;
        Declaration lookup = nameLookup.lookup((Object) symbol, false);
        if (lookup == null) {
            expression = syntaxError("node test when context item is undefined", "XPDY0002");
        } else {
            expression = new ReferenceExp(symbol, lookup);
        }
        if (parseMaybeKindTest == null) {
            getRawToken();
        }
        if (i == 3 || i == -1) {
            quoteExp = makeChildAxisStep;
        } else if (i == 4) {
            quoteExp = makeDescendantAxisStep;
        } else {
            switch (i) {
                case 0:
                    str = "Ancestor";
                    break;
                case 1:
                    str = "AncestorOrSelf";
                    break;
                case 2:
                    str = "Attribute";
                    break;
                case 5:
                    str = "DescendantOrSelf";
                    break;
                case 6:
                    str = "Following";
                    break;
                case 7:
                    str = "FollowingSibling";
                    break;
                case 9:
                    str = "Parent";
                    break;
                case 10:
                    str = "Preceding";
                    break;
                case 11:
                    str = "PrecedingSibling";
                    break;
                case 12:
                    str = "Self";
                    break;
                default:
                    throw new Error();
            }
            quoteExp = QuoteExp.getInstance(new PrimProcedure("gnu.kawa.xml." + str + "Axis", "make", 1));
        }
        ApplyExp applyExp = new ApplyExp((Expression) quoteExp, expressionArr);
        applyExp.setFlag(4);
        return new ApplyExp((Expression) applyExp, expression);
    }

    /* access modifiers changed from: package-private */
    public Expression parseRelativePathExpr(Expression expression) throws IOException, SyntaxException {
        Expression expression2 = null;
        while (true) {
            int i = this.curToken;
            if (i != 47 && i != 68) {
                return expression;
            }
            boolean z = i == 68;
            LambdaExp lambdaExp = new LambdaExp(3);
            Symbol symbol = DOT_VARNAME;
            Declaration addDeclaration = lambdaExp.addDeclaration((Object) symbol);
            addDeclaration.setFlag(262144);
            addDeclaration.setType(NodeType.anyNodeTest);
            addDeclaration.noteValue((Expression) null);
            lambdaExp.addDeclaration(POSITION_VARNAME, LangPrimType.intType);
            lambdaExp.addDeclaration(LAST_VARNAME, LangPrimType.intType);
            this.comp.push((ScopeExp) lambdaExp);
            if (z) {
                this.curToken = 47;
                lambdaExp.body = new ApplyExp((Procedure) DescendantOrSelfAxis.anyNode, new ReferenceExp(symbol, addDeclaration));
                expression2 = expression;
            } else {
                getRawToken();
                Expression parseStepExpr = parseStepExpr();
                if (expression2 != null && (parseStepExpr instanceof ApplyExp)) {
                    Expression function = ((ApplyExp) parseStepExpr).getFunction();
                    if (function instanceof ApplyExp) {
                        ApplyExp applyExp = (ApplyExp) function;
                        if (applyExp.getFunction() == makeChildAxisStep) {
                            applyExp.setFunction(makeDescendantAxisStep);
                            expression = expression2;
                        }
                    }
                }
                lambdaExp.body = parseStepExpr;
                expression2 = null;
            }
            this.comp.pop(lambdaExp);
            expression = new ApplyExp((Procedure) RelativeStep.relativeStep, expression, lambdaExp);
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseStepExpr() throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        Expression parseNodeTest;
        int i = this.curToken;
        int i2 = -1;
        if (i == 46 || i == 51) {
            int i3 = i == 46 ? 12 : 9;
            getRawToken();
            NameLookup nameLookup = this.comp.lexical;
            Symbol symbol = DOT_VARNAME;
            Declaration lookup = nameLookup.lookup((Object) symbol, false);
            if (lookup == null) {
                expression = syntaxError("context item is undefined", "XPDY0002");
            } else {
                expression = new ReferenceExp(symbol, lookup);
            }
            if (i3 == 9) {
                expression = new ApplyExp((Procedure) ParentAxis.make(NodeType.anyNodeTest), expression);
            }
            if (i3 != 12) {
                i2 = i3;
            }
            return parseStepQualifiers(expression, i2);
        }
        int peekOperand = peekOperand() - 100;
        if (peekOperand < 0 || peekOperand >= 13) {
            int i4 = this.curToken;
            if (i4 == 64) {
                getRawToken();
                parseNodeTest = parseNodeTest(2);
            } else if (i4 == OP_ATTRIBUTE) {
                parseNodeTest = parseNodeTest(2);
            } else {
                expression2 = parseNodeTest(-1);
                if (expression2 != null) {
                    i2 = 3;
                } else {
                    expression2 = parsePrimaryExpr();
                }
            }
            expression2 = parseNodeTest;
            i2 = 2;
        } else {
            getRawToken();
            expression2 = parseNodeTest(peekOperand);
            i2 = peekOperand;
        }
        return parseStepQualifiers(expression2, i2);
    }

    /* access modifiers changed from: package-private */
    public Expression parseStepQualifiers(Expression expression, int i) throws IOException, SyntaxException {
        ValuesFilter valuesFilter;
        while (this.curToken == 91) {
            int lineNumber = getLineNumber() + 1;
            int columnNumber = getColumnNumber() + 1;
            getRawToken();
            LambdaExp lambdaExp = new LambdaExp(3);
            maybeSetLine((Expression) lambdaExp, lineNumber, columnNumber);
            Declaration addDeclaration = lambdaExp.addDeclaration((Object) DOT_VARNAME);
            if (i >= 0) {
                addDeclaration.setType(NodeType.anyNodeTest);
            } else {
                addDeclaration.setType(SingletonType.getInstance());
            }
            lambdaExp.addDeclaration(POSITION_VARNAME, Type.intType);
            lambdaExp.addDeclaration(LAST_VARNAME, Type.intType);
            this.comp.push((ScopeExp) lambdaExp);
            addDeclaration.noteValue((Expression) null);
            Expression parseExprSequence = parseExprSequence(93, false);
            if (this.curToken == -1) {
                eofError("missing ']' - unexpected end-of-file");
            }
            if (i < 0) {
                valuesFilter = ValuesFilter.exprFilter;
            } else if (i == 0 || i == 1 || i == 9 || i == 10 || i == 11) {
                valuesFilter = ValuesFilter.reverseFilter;
            } else {
                valuesFilter = ValuesFilter.forwardFilter;
            }
            maybeSetLine(parseExprSequence, lineNumber, columnNumber);
            this.comp.pop(lambdaExp);
            lambdaExp.body = parseExprSequence;
            getRawToken();
            expression = new ApplyExp((Procedure) valuesFilter, expression, lambdaExp);
        }
        return expression;
    }

    /* access modifiers changed from: package-private */
    public Expression parsePrimaryExpr() throws IOException, SyntaxException {
        Expression parseMaybePrimaryExpr = parseMaybePrimaryExpr();
        if (parseMaybePrimaryExpr == null) {
            parseMaybePrimaryExpr = syntaxError("missing expression");
            if (this.curToken != -1) {
                getRawToken();
            }
        }
        return parseMaybePrimaryExpr;
    }

    /* access modifiers changed from: package-private */
    public void parseEntityOrCharRef() throws IOException, SyntaxException {
        int i;
        int read = read();
        if (read == 35) {
            int read2 = read();
            if (read2 == 120) {
                read2 = read();
                i = 16;
            } else {
                i = 10;
            }
            int i2 = 0;
            while (read2 >= 0) {
                int digit = Character.digit((char) read2, i);
                if (digit < 0 || i2 >= 134217728) {
                    break;
                }
                i2 = (i2 * i) + digit;
                read2 = read();
            }
            if (read2 != 59) {
                unread();
                error("invalid character reference");
            } else if ((i2 <= 0 || i2 > 55295) && ((i2 < 57344 || i2 > 65533) && (i2 < 65536 || i2 > 1114111))) {
                error('e', "invalid character value " + i2, "XQST0090");
            } else {
                tokenBufferAppend(i2);
            }
        } else {
            int i3 = this.tokenBufferLength;
            while (read >= 0) {
                char c = (char) read;
                if (!XName.isNamePart(c)) {
                    break;
                }
                tokenBufferAppend(c);
                read = read();
            }
            if (read != 59) {
                unread();
                error("invalid entity reference");
                return;
            }
            String str = new String(this.tokenBuffer, i3, this.tokenBufferLength - i3);
            this.tokenBufferLength = i3;
            appendNamedEntity(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x012b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseContent(char r12, java.util.Vector r13) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r11 = this;
            r0 = 0
            r11.tokenBufferLength = r0
            int r1 = r13.size()
            r2 = 1
            int r1 = r1 - r2
            boolean r3 = r11.boundarySpacePreserve
            r4 = 60
            if (r3 != 0) goto L_0x0013
            if (r12 != r4) goto L_0x0013
            r3 = 1
            goto L_0x0014
        L_0x0013:
            r3 = 0
        L_0x0014:
            r5 = r3
        L_0x0015:
            int r6 = r11.read()
            if (r6 != r12) goto L_0x009b
            if (r12 != r4) goto L_0x0090
            int r6 = r11.read()
            r7 = 0
            int r8 = r11.tokenBufferLength
            if (r8 <= 0) goto L_0x003f
            java.lang.String r7 = new java.lang.String
            char[] r8 = r11.tokenBuffer
            int r9 = r11.tokenBufferLength
            r7.<init>(r8, r0, r9)
            gnu.expr.Expression[] r8 = new gnu.expr.Expression[r2]
            gnu.expr.QuoteExp r9 = new gnu.expr.QuoteExp
            r9.<init>(r7)
            r8[r0] = r9
            gnu.expr.ApplyExp r7 = new gnu.expr.ApplyExp
            gnu.expr.Expression r9 = makeText
            r7.<init>((gnu.expr.Expression) r9, (gnu.expr.Expression[]) r8)
        L_0x003f:
            r11.tokenBufferLength = r0
            r8 = 47
            if (r6 != r8) goto L_0x004e
            if (r7 == 0) goto L_0x012b
            if (r5 != 0) goto L_0x012b
            r13.addElement(r7)
            goto L_0x012b
        L_0x004e:
            gnu.expr.Expression r6 = r11.parseXMLConstructor(r6, r2)
            boolean r8 = r6 instanceof gnu.expr.ApplyExp
            if (r8 == 0) goto L_0x0078
            r8 = r6
            gnu.expr.ApplyExp r8 = (gnu.expr.ApplyExp) r8
            gnu.expr.Expression r9 = r8.getFunction()
            gnu.expr.Expression r10 = makeCDATA
            if (r9 != r10) goto L_0x0078
            gnu.expr.Expression r8 = r8.getArg(r0)
            java.lang.Object r8 = r8.valueIfConstant()
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x0075
            int r8 = r8.length()
            if (r8 != 0) goto L_0x0075
            r8 = 1
            goto L_0x0076
        L_0x0075:
            r8 = 0
        L_0x0076:
            r9 = 1
            goto L_0x007a
        L_0x0078:
            r8 = 0
            r9 = 0
        L_0x007a:
            if (r7 == 0) goto L_0x0083
            if (r5 == 0) goto L_0x0080
            if (r9 == 0) goto L_0x0083
        L_0x0080:
            r13.addElement(r7)
        L_0x0083:
            if (r9 == 0) goto L_0x0087
            r5 = 0
            goto L_0x0088
        L_0x0087:
            r5 = r3
        L_0x0088:
            if (r8 != 0) goto L_0x008d
            r13.addElement(r6)
        L_0x008d:
            r11.tokenBufferLength = r0
            goto L_0x0015
        L_0x0090:
            boolean r7 = r11.checkNext(r12)
            if (r7 == 0) goto L_0x009b
            r11.tokenBufferAppend(r12)
            goto L_0x0015
        L_0x009b:
            r7 = 123(0x7b, float:1.72E-43)
            if (r6 == r12) goto L_0x00ea
            if (r6 < 0) goto L_0x00ea
            if (r6 != r7) goto L_0x00a4
            goto L_0x00ea
        L_0x00a4:
            r7 = 125(0x7d, float:1.75E-43)
            if (r6 != r7) goto L_0x00bc
            int r6 = r11.read()
            if (r6 != r7) goto L_0x00b2
            r11.tokenBufferAppend(r7)
            goto L_0x00f7
        L_0x00b2:
            java.lang.String r7 = "unexpected '}' in element content"
            r11.error(r7)
            r11.unread(r6)
            goto L_0x0015
        L_0x00bc:
            r7 = 38
            if (r6 != r7) goto L_0x00c4
            r11.parseEntityOrCharRef()
            goto L_0x00f7
        L_0x00c4:
            if (r12 == r4) goto L_0x00d4
            r7 = 9
            if (r6 == r7) goto L_0x00d2
            r7 = 10
            if (r6 == r7) goto L_0x00d2
            r7 = 13
            if (r6 != r7) goto L_0x00d4
        L_0x00d2:
            r6 = 32
        L_0x00d4:
            if (r6 != r4) goto L_0x00dd
            r7 = 101(0x65, float:1.42E-43)
            java.lang.String r8 = "'<' must be quoted in a direct attribute value"
            r11.error(r7, r8)
        L_0x00dd:
            if (r5 == 0) goto L_0x00e4
            char r5 = (char) r6
            boolean r5 = java.lang.Character.isWhitespace(r5)
        L_0x00e4:
            char r6 = (char) r6
            r11.tokenBufferAppend(r6)
            goto L_0x0015
        L_0x00ea:
            if (r6 != r7) goto L_0x00f1
            int r8 = r11.read()
            goto L_0x00f2
        L_0x00f1:
            r8 = -1
        L_0x00f2:
            if (r8 != r7) goto L_0x00fa
            r11.tokenBufferAppend(r7)
        L_0x00f7:
            r5 = 0
            goto L_0x0015
        L_0x00fa:
            int r9 = r11.tokenBufferLength
            if (r9 <= 0) goto L_0x010a
            if (r5 != 0) goto L_0x010a
            java.lang.String r7 = new java.lang.String
            char[] r9 = r11.tokenBuffer
            int r10 = r11.tokenBufferLength
            r7.<init>(r9, r0, r10)
            goto L_0x0114
        L_0x010a:
            if (r6 != r7) goto L_0x0127
            int r7 = r13.size()
            if (r1 != r7) goto L_0x0127
            java.lang.String r7 = ""
        L_0x0114:
            gnu.expr.Expression[] r9 = new gnu.expr.Expression[r2]
            gnu.expr.QuoteExp r10 = new gnu.expr.QuoteExp
            r10.<init>(r7)
            r9[r0] = r10
            gnu.expr.ApplyExp r7 = new gnu.expr.ApplyExp
            gnu.expr.Expression r10 = makeText
            r7.<init>((gnu.expr.Expression) r10, (gnu.expr.Expression[]) r9)
            r13.addElement(r7)
        L_0x0127:
            r11.tokenBufferLength = r0
            if (r6 != r12) goto L_0x012c
        L_0x012b:
            return
        L_0x012c:
            if (r6 >= 0) goto L_0x0135
            java.lang.String r6 = "unexpected end-of-file"
            r11.eofError(r6)
            goto L_0x0015
        L_0x0135:
            r11.unread(r8)
            int r1 = r11.enclosedExpressionsSeen
            int r1 = r1 + r2
            r11.enclosedExpressionsSeen = r1
            gnu.expr.Expression r1 = r11.parseEnclosedExpr()
            r13.addElement(r1)
            r11.tokenBufferLength = r0
            int r1 = r13.size()
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseContent(char, java.util.Vector):void");
    }

    /* access modifiers changed from: package-private */
    public Expression parseEnclosedExpr() throws IOException, SyntaxException {
        String str = this.errorIfComment;
        this.errorIfComment = null;
        char pushNesting = pushNesting('{');
        peekNonSpace("unexpected end-of-file after '{'");
        int lineNumber = getLineNumber() + 1;
        int columnNumber = getColumnNumber() + 1;
        getRawToken();
        Expression parseExpr = parseExpr();
        while (true) {
            int i = this.curToken;
            if (i == 125) {
                break;
            } else if (i == -1 || i == 41 || i == 93) {
                parseExpr = syntaxError("missing '}'");
            } else {
                if (i != 44) {
                    parseExpr = syntaxError("missing '}' or ','");
                } else {
                    getRawToken();
                }
                parseExpr = makeExprSequence(parseExpr, parseExpr());
            }
        }
        parseExpr = syntaxError("missing '}'");
        maybeSetLine(parseExpr, lineNumber, columnNumber);
        popNesting(pushNesting);
        this.errorIfComment = str;
        return parseExpr;
    }

    public static Expression booleanValue(Expression expression) {
        return new ApplyExp(makeFunctionExp("gnu.xquery.util.BooleanValue", "booleanValue"), expression);
    }

    /* access modifiers changed from: package-private */
    public Expression parseXMLConstructor(int i, boolean z) throws IOException, SyntaxException {
        Expression expression;
        boolean z2;
        if (i == 33) {
            int read = read();
            if (read == 45 && peek() == 45) {
                skip();
                getDelimited("-->");
                int i2 = this.tokenBufferLength;
                boolean z3 = true;
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        z2 = false;
                        break;
                    }
                    boolean z4 = this.tokenBuffer[i2] == '-';
                    if (z3 && z4) {
                        z2 = true;
                        break;
                    }
                    z3 = z4;
                }
                if (z2) {
                    return syntaxError("consecutive or final hyphen in XML comment");
                }
                expression = new ApplyExp(makeFunctionExp("gnu.kawa.xml.CommentConstructor", "commentConstructor"), new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength)));
            } else if (read != 91 || read() != 67 || read() != 68 || read() != 65 || read() != 84 || read() != 65 || read() != 91) {
                return syntaxError("'<!' must be followed by '--' or '[CDATA['");
            } else {
                if (!z) {
                    error('e', "CDATA section must be in element content");
                }
                getDelimited("]]>");
                expression = new ApplyExp(makeCDATA, new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength)));
            }
        } else if (i == 63) {
            int peek = peek();
            if (peek < 0 || !XName.isNameStart((char) peek) || getRawToken() != 65) {
                syntaxError("missing target after '<?'");
            }
            String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
            int i3 = 0;
            while (true) {
                int read2 = read();
                if (read2 < 0) {
                    break;
                } else if (!Character.isWhitespace((char) read2)) {
                    unread();
                    break;
                } else {
                    i3++;
                }
            }
            getDelimited("?>");
            if (i3 == 0 && this.tokenBufferLength > 0) {
                syntaxError("target must be followed by space or '?>'");
            }
            return new ApplyExp(makeFunctionExp("gnu.kawa.xml.MakeProcInst", "makeProcInst"), new QuoteExp(str), new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength)));
        } else if (i < 0 || !XName.isNameStart((char) i)) {
            return syntaxError("expected QName after '<'");
        } else {
            unread(i);
            getRawToken();
            char pushNesting = pushNesting('<');
            Expression parseElementConstructor = parseElementConstructor();
            expression = !z ? wrapWithBaseUri(parseElementConstructor) : parseElementConstructor;
            popNesting(pushNesting);
        }
        return expression;
    }

    static ApplyExp castQName(Expression expression, boolean z) {
        return new ApplyExp((Expression) new ReferenceExp(z ? XQResolveNames.xsQNameDecl : XQResolveNames.xsQNameIgnoreDefaultDecl), expression);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x014c A[EDGE_INSN: B:108:0x014c->B:62:0x014c ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x00b2 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseElementConstructor() throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r15 = this;
            java.lang.String r0 = new java.lang.String
            char[] r1 = r15.tokenBuffer
            int r2 = r15.tokenBufferLength
            r3 = 0
            r0.<init>(r1, r3, r2)
            java.util.Vector r1 = new java.util.Vector
            r1.<init>()
            gnu.expr.QuoteExp r2 = new gnu.expr.QuoteExp
            r2.<init>(r0)
            r4 = 1
            gnu.expr.ApplyExp r2 = castQName(r2, r4)
            r1.addElement(r2)
            java.lang.String r2 = "comment not allowed in element start tag"
            r15.errorIfComment = r2
            r2 = 0
            r5 = r2
        L_0x0022:
            int r6 = r15.read()
            r7 = 0
        L_0x0027:
            if (r6 < 0) goto L_0x0036
            char r8 = (char) r6
            boolean r8 = java.lang.Character.isWhitespace(r8)
            if (r8 == 0) goto L_0x0036
            int r6 = r15.read()
            r7 = 1
            goto L_0x0027
        L_0x0036:
            r8 = 47
            r9 = 62
            if (r6 < 0) goto L_0x0178
            if (r6 == r9) goto L_0x0178
            if (r6 != r8) goto L_0x0042
            goto L_0x0178
        L_0x0042:
            if (r7 != 0) goto L_0x0049
            java.lang.String r7 = "missing space before attribute"
            r15.syntaxError(r7)
        L_0x0049:
            r15.unread(r6)
            r15.getRawToken()
            int r7 = r1.size()
            int r10 = r15.curToken
            r11 = 65
            if (r10 == r11) goto L_0x005f
            r12 = 81
            if (r10 == r12) goto L_0x005f
            goto L_0x0178
        L_0x005f:
            java.lang.String r6 = new java.lang.String
            char[] r8 = r15.tokenBuffer
            int r9 = r15.tokenBufferLength
            r6.<init>(r8, r3, r9)
            int r8 = r15.getLineNumber()
            int r8 = r8 + r4
            int r9 = r15.getColumnNumber()
            int r9 = r9 + r4
            int r10 = r15.tokenBufferLength
            int r9 = r9 - r10
            int r10 = r15.curToken
            java.lang.String r12 = ""
            if (r10 != r11) goto L_0x0085
            java.lang.String r10 = "xmlns"
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto L_0x0097
            r10 = r12
            goto L_0x0098
        L_0x0085:
            java.lang.String r10 = "xmlns:"
            boolean r10 = r6.startsWith(r10)
            if (r10 == 0) goto L_0x0097
            r10 = 6
            java.lang.String r10 = r6.substring(r10)
            java.lang.String r10 = r10.intern()
            goto L_0x0098
        L_0x0097:
            r10 = r2
        L_0x0098:
            if (r10 == 0) goto L_0x009c
            r11 = r2
            goto L_0x009e
        L_0x009c:
            gnu.expr.QuoteExp r11 = gnu.kawa.xml.MakeAttribute.makeAttributeExp
        L_0x009e:
            gnu.expr.QuoteExp r13 = new gnu.expr.QuoteExp
            r13.<init>(r6)
            gnu.expr.ApplyExp r6 = castQName(r13, r3)
            r1.addElement(r6)
            int r6 = r15.skipSpace()
            r13 = 61
            if (r6 == r13) goto L_0x00bb
            r15.errorIfComment = r2
            java.lang.String r0 = "missing '=' after attribute"
            gnu.expr.Expression r0 = r15.syntaxError(r0)
            return r0
        L_0x00bb:
            int r6 = r15.skipSpace()
            int r13 = r15.enclosedExpressionsSeen
            r14 = 123(0x7b, float:1.72E-43)
            if (r6 != r14) goto L_0x00d2
            java.lang.String r6 = "enclosed attribute value expression should be quoted"
            r15.warnOldVersion(r6)
            gnu.expr.Expression r6 = r15.parseEnclosedExpr()
            r1.addElement(r6)
            goto L_0x00d6
        L_0x00d2:
            char r6 = (char) r6
            r15.parseContent(r6, r1)
        L_0x00d6:
            int r6 = r1.size()
            int r6 = r6 - r7
            if (r10 == 0) goto L_0x0157
            if (r6 != r4) goto L_0x00e1
        L_0x00df:
            r6 = r12
            goto L_0x0112
        L_0x00e1:
            int r6 = r15.enclosedExpressionsSeen
            if (r6 <= r13) goto L_0x00eb
            java.lang.String r6 = "enclosed expression not allowed in namespace declaration"
            r15.syntaxError(r6)
            goto L_0x00df
        L_0x00eb:
            int r6 = r7 + 1
            java.lang.Object r6 = r1.elementAt(r6)
            boolean r8 = r6 instanceof gnu.expr.ApplyExp
            if (r8 == 0) goto L_0x0104
            r8 = r6
            gnu.expr.ApplyExp r8 = (gnu.expr.ApplyExp) r8
            gnu.expr.Expression r9 = r8.getFunction()
            gnu.expr.Expression r11 = makeText
            if (r9 != r11) goto L_0x0104
            gnu.expr.Expression r6 = r8.getArg(r3)
        L_0x0104:
            gnu.expr.QuoteExp r6 = (gnu.expr.QuoteExp) r6
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = r6.toString()
            java.lang.String r6 = r6.intern()
        L_0x0112:
            r1.setSize(r7)
            r15.checkAllowedNamespaceDeclaration(r10, r6, r4)
            if (r10 != r12) goto L_0x011b
            r10 = r2
        L_0x011b:
            r7 = r5
        L_0x011c:
            if (r7 == 0) goto L_0x014c
            java.lang.String r8 = r7.getPrefix()
            if (r8 != r10) goto L_0x0147
            r7 = 101(0x65, float:1.42E-43)
            if (r10 != 0) goto L_0x012b
            java.lang.String r8 = "duplicate default namespace declaration"
            goto L_0x0141
        L_0x012b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "duplicate namespace prefix '"
            r8.append(r9)
            r8.append(r10)
            r9 = 39
            r8.append(r9)
            java.lang.String r8 = r8.toString()
        L_0x0141:
            java.lang.String r9 = "XQST0071"
            r15.error(r7, r8, r9)
            goto L_0x014c
        L_0x0147:
            gnu.xml.NamespaceBinding r7 = r7.getNext()
            goto L_0x011c
        L_0x014c:
            gnu.xml.NamespaceBinding r7 = new gnu.xml.NamespaceBinding
            if (r6 != r12) goto L_0x0151
            r6 = r2
        L_0x0151:
            r7.<init>(r10, r6, r5)
            r5 = r7
            goto L_0x0022
        L_0x0157:
            gnu.expr.Expression[] r10 = new gnu.expr.Expression[r6]
        L_0x0159:
            int r6 = r6 + -1
            if (r6 < 0) goto L_0x0168
            int r12 = r7 + r6
            java.lang.Object r12 = r1.elementAt(r12)
            gnu.expr.Expression r12 = (gnu.expr.Expression) r12
            r10[r6] = r12
            goto L_0x0159
        L_0x0168:
            r1.setSize(r7)
            gnu.expr.ApplyExp r6 = new gnu.expr.ApplyExp
            r6.<init>((gnu.expr.Expression) r11, (gnu.expr.Expression[]) r10)
            r15.maybeSetLine((gnu.expr.Expression) r6, (int) r8, (int) r9)
            r1.addElement(r6)
            goto L_0x0022
        L_0x0178:
            r15.errorIfComment = r2
            if (r6 != r8) goto L_0x0186
            int r6 = r15.read()
            if (r6 != r9) goto L_0x0183
            goto L_0x0187
        L_0x0183:
            r15.unread(r6)
        L_0x0186:
            r4 = 0
        L_0x0187:
            if (r4 != 0) goto L_0x01f3
            if (r6 == r9) goto L_0x0192
            java.lang.String r0 = "missing '>' after start element"
            gnu.expr.Expression r0 = r15.syntaxError(r0)
            return r0
        L_0x0192:
            r4 = 60
            r15.parseContent(r4, r1)
            int r4 = r15.peek()
            if (r4 < 0) goto L_0x01ea
            char r4 = (char) r4
            boolean r4 = gnu.xml.XName.isNameStart(r4)
            if (r4 != 0) goto L_0x01ab
            java.lang.String r0 = "invalid tag syntax after '</'"
            gnu.expr.Expression r0 = r15.syntaxError(r0)
            return r0
        L_0x01ab:
            r15.getRawToken()
            java.lang.String r4 = new java.lang.String
            char[] r6 = r15.tokenBuffer
            int r7 = r15.tokenBufferLength
            r4.<init>(r6, r3, r7)
            boolean r3 = r4.equals(r0)
            if (r3 != 0) goto L_0x01e0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "'<"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = ">' closed by '</"
            r1.append(r0)
            r1.append(r4)
            java.lang.String r0 = ">'"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            gnu.expr.Expression r0 = r15.syntaxError(r0)
            return r0
        L_0x01e0:
            java.lang.String r0 = "comment not allowed in element end tag"
            r15.errorIfComment = r0
            int r4 = r15.skipSpace()
            r15.errorIfComment = r2
        L_0x01ea:
            if (r4 == r9) goto L_0x01f3
            java.lang.String r0 = "missing '>' after end element"
            gnu.expr.Expression r0 = r15.syntaxError(r0)
            return r0
        L_0x01f3:
            int r0 = r1.size()
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r1.copyInto(r0)
            gnu.kawa.xml.MakeElement r1 = new gnu.kawa.xml.MakeElement
            r1.<init>()
            int r2 = r15.copyNamespacesMode
            r1.copyNamespacesMode = r2
            r1.setNamespaceNodes(r5)
            gnu.expr.ApplyExp r2 = new gnu.expr.ApplyExp
            gnu.expr.QuoteExp r3 = new gnu.expr.QuoteExp
            r3.<init>(r1)
            r2.<init>((gnu.expr.Expression) r3, (gnu.expr.Expression[]) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseElementConstructor():gnu.expr.Expression");
    }

    /* access modifiers changed from: package-private */
    public Expression wrapWithBaseUri(Expression expression) {
        if (getStaticBaseUri() == null) {
            return expression;
        }
        return new ApplyExp((Procedure) MakeWithBaseUri.makeWithBaseUri, new ApplyExp((Expression) new ReferenceExp(XQResolveNames.staticBaseUriDecl), Expression.noExpressions), expression).setLine(expression);
    }

    /* access modifiers changed from: package-private */
    public Expression parseParenExpr() throws IOException, SyntaxException {
        getRawToken();
        char pushNesting = pushNesting('(');
        Expression parseExprSequence = parseExprSequence(41, true);
        popNesting(pushNesting);
        if (this.curToken == -1) {
            eofError("missing ')' - unexpected end-of-file");
        }
        return parseExprSequence;
    }

    /* access modifiers changed from: package-private */
    public Expression parseExprSequence(int i, boolean z) throws IOException, SyntaxException {
        int i2 = this.curToken;
        if (i2 == i || i2 == -1) {
            if (!z) {
                syntaxError("missing expression");
            }
            return QuoteExp.voidExp;
        }
        Expression expression = null;
        while (true) {
            Expression parseExprSingle = parseExprSingle();
            if (expression == null) {
                expression = parseExprSingle;
            } else {
                expression = makeExprSequence(expression, parseExprSingle);
            }
            int i3 = this.curToken;
            if (i3 == i || i3 == -1) {
                return expression;
            }
            if (this.nesting == 0 && this.curToken == 10) {
                return expression;
            }
            if (this.curToken != 44) {
                return syntaxError(i == 41 ? "expected ')'" : "confused by syntax error");
            }
            getRawToken();
        }
        return expression;
    }

    /* access modifiers changed from: package-private */
    public Expression parseTypeSwitch() throws IOException, SyntaxException {
        Declaration declaration;
        Declaration declaration2;
        char pushNesting = pushNesting('t');
        Expression parseParenExpr = parseParenExpr();
        getRawToken();
        Vector vector = new Vector();
        vector.addElement(parseParenExpr);
        while (true) {
            char c = 'e';
            if (match("case")) {
                pushNesting('c');
                getRawToken();
                if (this.curToken == 36) {
                    declaration2 = parseVariableDeclaration();
                    if (declaration2 == null) {
                        return syntaxError("missing Variable after '$'");
                    }
                    getRawToken();
                    if (match("as")) {
                        getRawToken();
                    } else {
                        error('e', "missing 'as'");
                    }
                } else {
                    declaration2 = new Declaration((Object) "(arg)");
                }
                declaration2.setTypeExp(parseDataType());
                popNesting('t');
                LambdaExp lambdaExp = new LambdaExp(1);
                lambdaExp.addDeclaration(declaration2);
                if (match("return")) {
                    getRawToken();
                } else {
                    error("missing 'return' after 'case'");
                }
                this.comp.push((ScopeExp) lambdaExp);
                pushNesting('r');
                lambdaExp.body = parseExpr();
                popNesting('t');
                this.comp.pop(lambdaExp);
                vector.addElement(lambdaExp);
            } else {
                if (match("default")) {
                    LambdaExp lambdaExp2 = new LambdaExp(1);
                    getRawToken();
                    if (this.curToken == 36) {
                        declaration = parseVariableDeclaration();
                        if (declaration == null) {
                            return syntaxError("missing Variable after '$'");
                        }
                        getRawToken();
                    } else {
                        declaration = new Declaration((Object) "(arg)");
                    }
                    lambdaExp2.addDeclaration(declaration);
                    if (match("return")) {
                        getRawToken();
                    } else {
                        error("missing 'return' after 'default'");
                    }
                    this.comp.push((ScopeExp) lambdaExp2);
                    lambdaExp2.body = parseExpr();
                    this.comp.pop(lambdaExp2);
                    vector.addElement(lambdaExp2);
                } else {
                    if (!this.comp.isPedantic()) {
                        c = 'w';
                    }
                    error(c, "no 'default' clause in 'typeswitch'", "XPST0003");
                }
                popNesting(pushNesting);
                Expression[] expressionArr = new Expression[vector.size()];
                vector.copyInto(expressionArr);
                return new ApplyExp(makeFunctionExp("gnu.kawa.reflect.TypeSwitch", "typeSwitch"), expressionArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseMaybePrimaryExpr() throws IOException, SyntaxException {
        Expression expression;
        Expression expression2;
        Object obj;
        int read;
        int i;
        String str;
        Object obj2;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        int i2 = this.curLine;
        int i3 = this.curColumn;
        int peekOperand = peekOperand();
        boolean z = false;
        if (peekOperand != 34) {
            if (peekOperand == 36) {
                Object parseVariable = parseVariable();
                if (parseVariable == null) {
                    return syntaxError("missing Variable");
                }
                expression = new ReferenceExp(parseVariable);
                maybeSetLine(expression, this.curLine, this.curColumn);
            } else if (peekOperand == 40) {
                expression = parseParenExpr();
            } else if (peekOperand == 70) {
                String str2 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
                char pushNesting = pushNesting('(');
                getRawToken();
                Vector vector = new Vector(10);
                if (this.curToken != 41) {
                    while (true) {
                        vector.addElement(parseExpr());
                        int i4 = this.curToken;
                        if (i4 == 41) {
                            break;
                        } else if (i4 != 44) {
                            return syntaxError("missing ')' after function call");
                        } else {
                            getRawToken();
                        }
                    }
                }
                Expression[] expressionArr = new Expression[vector.size()];
                vector.copyInto(expressionArr);
                ReferenceExp referenceExp = new ReferenceExp(str2, (Declaration) null);
                referenceExp.setProcedureName(true);
                Expression applyExp = new ApplyExp((Expression) referenceExp, expressionArr);
                maybeSetLine(applyExp, i2, i3);
                popNesting(pushNesting);
                expression = applyExp;
            } else if (peekOperand == 123) {
                expression = syntaxError("saw unexpected '{' - assume you meant '('");
                parseEnclosedExpr();
            } else if (peekOperand == PRAGMA_START_TOKEN) {
                Stack stack = new Stack();
                do {
                    getRawToken();
                    int i5 = this.curToken;
                    if (i5 == 81 || i5 == 65) {
                        obj = QuoteExp.getInstance(new String(this.tokenBuffer, 0, this.tokenBufferLength));
                    } else {
                        obj = syntaxError("missing pragma name");
                    }
                    stack.push(obj);
                    StringBuffer stringBuffer = new StringBuffer();
                    int i6 = -1;
                    do {
                        read = read();
                        i6++;
                        if (read < 0 || !Character.isWhitespace((char) read)) {
                            int i7 = i6;
                        }
                        read = read();
                        i6++;
                        break;
                    } while (!Character.isWhitespace((char) read));
                    int i72 = i6;
                    while (true) {
                        if (read == 35 && peek() == 41) {
                            break;
                        }
                        if (read < 0) {
                            eofError("pragma ended by end-of-file");
                        }
                        if (i72 == 0) {
                            error("missing space between pragma and extension content");
                        }
                        stringBuffer.append((char) read);
                        read = read();
                        i72 = 1;
                    }
                    read();
                    stack.push(QuoteExp.getInstance(stringBuffer.toString()));
                    getRawToken();
                    i = this.curToken;
                } while (i == PRAGMA_START_TOKEN);
                if (i == 123) {
                    getRawToken();
                    if (this.curToken != 125) {
                        char pushNesting2 = pushNesting('{');
                        stack.push(parseExprSequence(ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH, false));
                        popNesting(pushNesting2);
                        if (this.curToken == -1) {
                            eofError("missing '}' - unexpected end-of-file");
                        }
                    }
                    Expression[] expressionArr2 = new Expression[stack.size()];
                    stack.copyInto(expressionArr2);
                    expression = new ApplyExp((Expression) new ReferenceExp(XQResolveNames.handleExtensionDecl), expressionArr2);
                } else {
                    expression = syntaxError("missing '{' after pragma");
                }
            } else if (peekOperand != 404) {
                switch (peekOperand) {
                    case 48:
                        expression2 = new QuoteExp(IntNum.valueOf(this.tokenBuffer, 0, this.tokenBufferLength, 10, false));
                        break;
                    case 49:
                    case 50:
                        String str3 = new String(this.tokenBuffer, 0, this.tokenBufferLength);
                        if (peekOperand == 49) {
                            try {
                                obj2 = new BigDecimal(str3);
                            } catch (Throwable unused) {
                                expression = syntaxError("invalid decimal literal: '" + str3 + "'");
                                break;
                            }
                        } else {
                            obj2 = new Double(str3);
                        }
                        expression = new QuoteExp(obj2);
                        break;
                    default:
                        switch (peekOperand) {
                            case ORDERED_LBRACE_TOKEN /*249*/:
                            case 250:
                                getRawToken();
                                expression = parseExprSequence(ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH, false);
                                break;
                            case 251:
                            case 252:
                            case 253:
                            case 254:
                            case 255:
                            case 256:
                                getRawToken();
                                Vector vector2 = new Vector();
                                if (peekOperand == 251 || peekOperand == 252) {
                                    int i8 = this.curToken;
                                    if (i8 == 65 || i8 == 81) {
                                        expression4 = parseNameTest(peekOperand != 251);
                                    } else if (i8 != 123) {
                                        return syntaxError("missing element/attribute name");
                                    } else {
                                        expression4 = parseEnclosedExpr();
                                    }
                                    vector2.addElement(castQName(expression4, peekOperand == 251));
                                    if (peekOperand == 251) {
                                        MakeElement makeElement = new MakeElement();
                                        makeElement.copyNamespacesMode = this.copyNamespacesMode;
                                        expression3 = new QuoteExp(makeElement);
                                    } else {
                                        expression3 = MakeAttribute.makeAttributeExp;
                                    }
                                    getRawToken();
                                } else if (peekOperand == 256) {
                                    expression3 = makeFunctionExp("gnu.kawa.xml.DocumentConstructor", "documentConstructor");
                                } else if (peekOperand == 254) {
                                    expression3 = makeFunctionExp("gnu.kawa.xml.CommentConstructor", "commentConstructor");
                                } else if (peekOperand == 255) {
                                    int i9 = this.curToken;
                                    if (i9 == 65) {
                                        expression5 = new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength).intern());
                                    } else if (i9 == 123) {
                                        expression5 = parseEnclosedExpr();
                                    } else {
                                        expression5 = syntaxError("expected NCName or '{' after 'processing-instruction'");
                                        if (this.curToken != 81) {
                                            return expression5;
                                        }
                                    }
                                    vector2.addElement(expression5);
                                    expression3 = makeFunctionExp("gnu.kawa.xml.MakeProcInst", "makeProcInst");
                                    getRawToken();
                                } else {
                                    expression3 = makeFunctionExp("gnu.kawa.xml.MakeText", "makeText");
                                }
                                char pushNesting3 = pushNesting('{');
                                peekNonSpace("unexpected end-of-file after '{'");
                                if (this.curToken == 123) {
                                    getRawToken();
                                    if (peekOperand == 253 || peekOperand == 254 || peekOperand == 255) {
                                        if (peekOperand == 255) {
                                            z = true;
                                        }
                                        vector2.addElement(parseExprSequence(ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH, z));
                                    } else if (this.curToken != 125) {
                                        vector2.addElement(parseExpr());
                                        while (this.curToken == 44) {
                                            getRawToken();
                                            vector2.addElement(parseExpr());
                                        }
                                    }
                                    popNesting(pushNesting3);
                                    if (this.curToken == 125) {
                                        Expression[] expressionArr3 = new Expression[vector2.size()];
                                        vector2.copyInto(expressionArr3);
                                        expression = new ApplyExp(expression3, expressionArr3);
                                        maybeSetLine(expression, i2, i3);
                                        if (peekOperand == 256 || peekOperand == 251) {
                                            expression = wrapWithBaseUri(expression);
                                            break;
                                        }
                                    } else {
                                        return syntaxError("missing '}'");
                                    }
                                } else {
                                    return syntaxError("missing '{'");
                                }
                                break;
                            default:
                                return null;
                        }
                }
            } else {
                int read2 = read();
                if (read2 == 47) {
                    getRawToken();
                    int i10 = this.curToken;
                    if (i10 == 65 || i10 == 81 || i10 == 67) {
                        str = "saw end tag '</" + new String(this.tokenBuffer, 0, this.tokenBufferLength) + ">' not in an element constructor";
                    } else {
                        str = "saw end tag '</' not in an element constructor";
                    }
                    this.curLine = i2;
                    this.curColumn = i3;
                    Expression syntaxError = syntaxError(str);
                    while (true) {
                        int i11 = this.curToken;
                        if (i11 == 405 || i11 == -1 || i11 == 10) {
                            return syntaxError;
                        }
                        getRawToken();
                    }
                    return syntaxError;
                }
                expression = parseXMLConstructor(read2, false);
                maybeSetLine(expression, i2, i3);
            }
            getRawToken();
            return expression;
        }
        expression2 = new QuoteExp(new String(this.tokenBuffer, 0, this.tokenBufferLength).intern());
        expression = expression2;
        getRawToken();
        return expression;
    }

    public Expression parseIfExpr() throws IOException, SyntaxException {
        char pushNesting = pushNesting('i');
        getRawToken();
        char pushNesting2 = pushNesting('(');
        Expression parseExprSequence = parseExprSequence(41, false);
        popNesting(pushNesting2);
        if (this.curToken == -1) {
            eofError("missing ')' - unexpected end-of-file");
        }
        getRawToken();
        if (!match("then")) {
            syntaxError("missing 'then'");
        } else {
            getRawToken();
        }
        Expression parseExpr = parseExpr();
        if (!match("else")) {
            syntaxError("missing 'else'");
        } else {
            getRawToken();
        }
        popNesting(pushNesting);
        return new IfExp(booleanValue(parseExprSequence), parseExpr, parseExpr());
    }

    public boolean match(String str) {
        int length;
        if (this.curToken != 65 || this.tokenBufferLength != (length = str.length())) {
            return false;
        }
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (str.charAt(length) == this.tokenBuffer[length]);
        return false;
    }

    public Object parseVariable() throws IOException, SyntaxException {
        if (this.curToken == 36) {
            getRawToken();
        } else {
            syntaxError("missing '$' before variable name");
        }
        String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
        int i = this.curToken;
        if (i == 81) {
            return str;
        }
        if (i == 65) {
            return Namespace.EmptyNamespace.getSymbol(str.intern());
        }
        return null;
    }

    public Declaration parseVariableDeclaration() throws IOException, SyntaxException {
        Object parseVariable = parseVariable();
        if (parseVariable == null) {
            return null;
        }
        Declaration declaration = new Declaration(parseVariable);
        maybeSetLine(declaration, getLineNumber() + 1, (getColumnNumber() + 1) - this.tokenBufferLength);
        return declaration;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0187 A[LOOP:0: B:10:0x0036->B:59:0x0187, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011b A[EDGE_INSN: B:62:0x011b->B:46:0x011b ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parseFLWRExpression(boolean r10) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r9 = this;
            int r0 = r9.flworDeclsFirst
            int r1 = r9.flworDeclsCount
            r9.flworDeclsFirst = r1
            gnu.expr.Expression r1 = r9.parseFLWRInner(r10)
            java.lang.String r2 = "order"
            boolean r2 = r9.match(r2)
            if (r2 == 0) goto L_0x018c
            if (r10 == 0) goto L_0x0017
            r10 = 102(0x66, float:1.43E-43)
            goto L_0x0019
        L_0x0017:
            r10 = 108(0x6c, float:1.51E-43)
        L_0x0019:
            char r10 = r9.pushNesting(r10)
            r9.getRawToken()
            java.lang.String r0 = "by"
            boolean r0 = r9.match(r0)
            if (r0 == 0) goto L_0x002c
            r9.getRawToken()
            goto L_0x0031
        L_0x002c:
            java.lang.String r0 = "missing 'by' following 'order'"
            r9.error(r0)
        L_0x0031:
            java.util.Stack r2 = new java.util.Stack
            r2.<init>()
        L_0x0036:
            char r0 = r9.defaultEmptyOrder
            gnu.expr.LambdaExp r3 = new gnu.expr.LambdaExp
            int r4 = r9.flworDeclsCount
            int r5 = r9.flworDeclsFirst
            int r4 = r4 - r5
            r3.<init>((int) r4)
            int r4 = r9.flworDeclsFirst
        L_0x0044:
            int r5 = r9.flworDeclsCount
            if (r4 >= r5) goto L_0x0056
            gnu.expr.Declaration[] r5 = r9.flworDecls
            r5 = r5[r4]
            java.lang.Object r5 = r5.getSymbol()
            r3.addDeclaration((java.lang.Object) r5)
            int r4 = r4 + 1
            goto L_0x0044
        L_0x0056:
            gnu.expr.Compilation r4 = r9.comp
            r4.push((gnu.expr.ScopeExp) r3)
            gnu.expr.Expression r4 = r9.parseExprSingle()
            r3.body = r4
            gnu.expr.Compilation r4 = r9.comp
            r4.pop(r3)
            r2.push(r3)
            java.lang.String r3 = "ascending"
            boolean r3 = r9.match(r3)
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0077
            r9.getRawToken()
            goto L_0x0084
        L_0x0077:
            java.lang.String r3 = "descending"
            boolean r3 = r9.match(r3)
            if (r3 == 0) goto L_0x0084
            r9.getRawToken()
            r3 = 1
            goto L_0x0085
        L_0x0084:
            r3 = 0
        L_0x0085:
            java.lang.String r6 = "empty"
            boolean r6 = r9.match(r6)
            if (r6 == 0) goto L_0x00b1
            r9.getRawToken()
            java.lang.String r6 = "greatest"
            boolean r6 = r9.match(r6)
            if (r6 == 0) goto L_0x009e
            r9.getRawToken()
            r0 = 71
            goto L_0x00b1
        L_0x009e:
            java.lang.String r6 = "least"
            boolean r6 = r9.match(r6)
            if (r6 == 0) goto L_0x00ac
            r9.getRawToken()
            r0 = 76
            goto L_0x00b1
        L_0x00ac:
            java.lang.String r6 = "'empty' sequence order must be 'greatest' or 'least'"
            r9.error(r6)
        L_0x00b1:
            gnu.expr.QuoteExp r6 = new gnu.expr.QuoteExp
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            if (r3 == 0) goto L_0x00bd
            java.lang.String r3 = "D"
            goto L_0x00bf
        L_0x00bd:
            java.lang.String r3 = "A"
        L_0x00bf:
            r7.append(r3)
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r6.<init>(r0)
            r2.push(r6)
            gnu.xquery.util.NamedCollator r0 = r9.defaultCollator
            java.lang.String r3 = "collation"
            boolean r3 = r9.match(r3)
            if (r3 == 0) goto L_0x010d
            java.lang.Object r3 = r9.parseURILiteral()
            boolean r6 = r3 instanceof java.lang.String
            if (r6 == 0) goto L_0x010a
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Exception -> 0x00ed }
            java.lang.String r6 = r9.resolveAgainstBaseUri(r6)     // Catch:{ Exception -> 0x00ed }
            gnu.xquery.util.NamedCollator r0 = gnu.xquery.util.NamedCollator.make(r6)     // Catch:{ Exception -> 0x00ed }
            goto L_0x010a
        L_0x00ed:
            r6 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "unknown collation '"
            r7.append(r8)
            r7.append(r3)
            java.lang.String r3 = "'"
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            java.lang.String r7 = "XQST0076"
            r9.error(r6, r3, r7)
        L_0x010a:
            r9.getRawToken()
        L_0x010d:
            gnu.expr.QuoteExp r3 = new gnu.expr.QuoteExp
            r3.<init>(r0)
            r2.push(r3)
            int r0 = r9.curToken
            r3 = 44
            if (r0 == r3) goto L_0x0187
            java.lang.String r0 = "return"
            boolean r0 = r9.match(r0)
            if (r0 != 0) goto L_0x012a
            java.lang.String r10 = "expected 'return' clause"
            gnu.expr.Expression r10 = r9.syntaxError(r10)
            return r10
        L_0x012a:
            r9.getRawToken()
            gnu.expr.LambdaExp r0 = new gnu.expr.LambdaExp
            int r3 = r9.flworDeclsCount
            int r6 = r9.flworDeclsFirst
            int r3 = r3 - r6
            r0.<init>((int) r3)
            int r3 = r9.flworDeclsFirst
        L_0x0139:
            int r6 = r9.flworDeclsCount
            if (r3 >= r6) goto L_0x014b
            gnu.expr.Declaration[] r6 = r9.flworDecls
            r6 = r6[r3]
            java.lang.Object r6 = r6.getSymbol()
            r0.addDeclaration((java.lang.Object) r6)
            int r3 = r3 + 1
            goto L_0x0139
        L_0x014b:
            r9.popNesting(r10)
            gnu.expr.Compilation r10 = r9.comp
            r10.push((gnu.expr.ScopeExp) r0)
            gnu.expr.Expression r10 = r9.parseExprSingle()
            r0.body = r10
            gnu.expr.Compilation r10 = r9.comp
            r10.pop(r0)
            int r10 = r2.size()
            int r3 = r10 + 2
            gnu.expr.Expression[] r3 = new gnu.expr.Expression[r3]
            r3[r4] = r1
            r3[r5] = r0
        L_0x016a:
            if (r4 >= r10) goto L_0x0179
            int r0 = r4 + 2
            java.lang.Object r1 = r2.elementAt(r4)
            gnu.expr.Expression r1 = (gnu.expr.Expression) r1
            r3[r0] = r1
            int r4 = r4 + 1
            goto L_0x016a
        L_0x0179:
            gnu.expr.ApplyExp r10 = new gnu.expr.ApplyExp
            java.lang.String r0 = "gnu.xquery.util.OrderedMap"
            java.lang.String r1 = "orderedMap"
            gnu.expr.Expression r0 = makeFunctionExp(r0, r1)
            r10.<init>((gnu.expr.Expression) r0, (gnu.expr.Expression[]) r3)
            return r10
        L_0x0187:
            r9.getRawToken()
            goto L_0x0036
        L_0x018c:
            int r10 = r9.flworDeclsFirst
            r9.flworDeclsCount = r10
            r9.flworDeclsFirst = r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parseFLWRExpression(boolean):gnu.expr.Expression");
    }

    public Expression parseFLWRInner(boolean z) throws IOException, SyntaxException {
        ScopeExp scopeExp;
        Declaration declaration;
        Expression expression;
        IfExp ifExp;
        char pushNesting = pushNesting(z ? 'f' : 'l');
        this.curToken = 36;
        Declaration parseVariableDeclaration = parseVariableDeclaration();
        if (parseVariableDeclaration == null) {
            return syntaxError("missing Variable - saw " + tokenString());
        }
        Declaration[] declarationArr = this.flworDecls;
        if (declarationArr == null) {
            this.flworDecls = new Declaration[8];
        } else {
            int i = this.flworDeclsCount;
            if (i >= declarationArr.length) {
                Declaration[] declarationArr2 = new Declaration[(i * 2)];
                System.arraycopy(declarationArr, 0, declarationArr2, 0, i);
                this.flworDecls = declarationArr2;
            }
        }
        Declaration[] declarationArr3 = this.flworDecls;
        int i2 = this.flworDeclsCount;
        this.flworDeclsCount = i2 + 1;
        declarationArr3[i2] = parseVariableDeclaration;
        getRawToken();
        Expression parseOptionalTypeDeclaration = parseOptionalTypeDeclaration();
        Expression[] expressionArr = new Expression[1];
        Expression expression2 = null;
        if (z) {
            boolean match = match("at");
            scopeExp = new LambdaExp(match ? 2 : 1);
            if (match) {
                getRawToken();
                if (this.curToken == 36) {
                    declaration = parseVariableDeclaration();
                    getRawToken();
                } else {
                    declaration = null;
                }
                if (declaration == null) {
                    syntaxError("missing Variable after 'at'");
                }
            } else {
                declaration = null;
            }
            if (match("in")) {
                getRawToken();
            } else {
                if (this.curToken == 76) {
                    getRawToken();
                }
                syntaxError("missing 'in' in 'for' clause");
            }
        } else {
            if (this.curToken == 76) {
                getRawToken();
            } else {
                if (match("in")) {
                    getRawToken();
                }
                syntaxError("missing ':=' in 'let' clause");
            }
            scopeExp = new LetExp(expressionArr);
            declaration = null;
        }
        Expression parseExprSingle = parseExprSingle();
        expressionArr[0] = parseExprSingle;
        if (parseOptionalTypeDeclaration != null && !z) {
            expressionArr[0] = Compilation.makeCoercion(parseExprSingle, parseOptionalTypeDeclaration);
        }
        popNesting(pushNesting);
        this.comp.push(scopeExp);
        scopeExp.addDeclaration(parseVariableDeclaration);
        if (parseOptionalTypeDeclaration != null) {
            parseVariableDeclaration.setTypeExp(parseOptionalTypeDeclaration);
        }
        if (z) {
            parseVariableDeclaration.noteValue((Expression) null);
            parseVariableDeclaration.setFlag(262144);
        }
        if (declaration != null) {
            scopeExp.addDeclaration(declaration);
            declaration.setType(LangPrimType.intType);
            declaration.noteValue((Expression) null);
            declaration.setFlag(262144);
        }
        if (this.curToken == 44) {
            getRawToken();
            if (this.curToken != 36) {
                return syntaxError("missing $NAME after ','");
            }
            expression = parseFLWRInner(z);
        } else if (match("for")) {
            getRawToken();
            if (this.curToken != 36) {
                return syntaxError("missing $NAME after 'for'");
            }
            expression = parseFLWRInner(true);
        } else if (match("let")) {
            getRawToken();
            if (this.curToken != 36) {
                return syntaxError("missing $NAME after 'let'");
            }
            expression = parseFLWRInner(false);
        } else {
            char pushNesting2 = pushNesting('w');
            if (this.curToken == 196) {
                getRawToken();
                expression2 = parseExprSingle();
            } else if (match("where")) {
                expression2 = parseExprSingle();
            }
            popNesting(pushNesting2);
            if (match("stable")) {
                getRawToken();
            }
            boolean match2 = match("return");
            boolean match3 = match("order");
            if (!match2 && !match3 && !match("let") && !match("for")) {
                return syntaxError("missing 'return' clause");
            }
            if (!match3) {
                peekNonSpace("unexpected eof-of-file after 'return'");
            }
            int lineNumber = getLineNumber() + 1;
            int columnNumber = getColumnNumber() + 1;
            if (match2) {
                getRawToken();
            }
            if (match3) {
                int i3 = this.flworDeclsCount - this.flworDeclsFirst;
                Expression[] expressionArr2 = new Expression[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    expressionArr2[i4] = new ReferenceExp(this.flworDecls[this.flworDeclsFirst + i4]);
                }
                ifExp = new ApplyExp((Procedure) new PrimProcedure("gnu.xquery.util.OrderedMap", "makeTuple$V", 1), expressionArr2);
            } else {
                ifExp = parseExprSingle();
            }
            if (expression2 != null) {
                ifExp = new IfExp(booleanValue(expression2), ifExp, QuoteExp.voidExp);
            }
            maybeSetLine(ifExp, lineNumber, columnNumber);
            expression = ifExp;
        }
        this.comp.pop(scopeExp);
        if (z) {
            LambdaExp lambdaExp = (LambdaExp) scopeExp;
            lambdaExp.body = expression;
            return new ApplyExp(makeFunctionExp("gnu.kawa.functions.ValuesMap", lambdaExp.min_args == 1 ? "valuesMap" : "valuesMapWithPos"), scopeExp, expressionArr[0]);
        }
        ((LetExp) scopeExp).setBody(expression);
        return scopeExp;
    }

    public Expression parseQuantifiedExpr(boolean z) throws IOException, SyntaxException {
        Expression expression;
        char pushNesting = pushNesting(z ? 'e' : 's');
        this.curToken = 36;
        Declaration parseVariableDeclaration = parseVariableDeclaration();
        if (parseVariableDeclaration == null) {
            return syntaxError("missing Variable token:" + this.curToken);
        }
        getRawToken();
        LambdaExp lambdaExp = new LambdaExp(1);
        lambdaExp.addDeclaration(parseVariableDeclaration);
        parseVariableDeclaration.noteValue((Expression) null);
        parseVariableDeclaration.setFlag(262144);
        parseVariableDeclaration.setTypeExp(parseOptionalTypeDeclaration());
        if (match("in")) {
            getRawToken();
        } else {
            if (this.curToken == 76) {
                getRawToken();
            }
            syntaxError("missing 'in' in QuantifiedExpr");
        }
        Expression[] expressionArr = {parseExprSingle()};
        popNesting(pushNesting);
        this.comp.push((ScopeExp) lambdaExp);
        String str = "some";
        if (this.curToken == 44) {
            getRawToken();
            if (this.curToken != 36) {
                return syntaxError("missing $NAME after ','");
            }
            expression = parseQuantifiedExpr(z);
        } else {
            boolean match = match("satisfies");
            if (!match && !match("every") && !match(str)) {
                return syntaxError("missing 'satisfies' clause");
            }
            peekNonSpace("unexpected eof-of-file after 'satisfies'");
            int lineNumber = getLineNumber() + 1;
            int columnNumber = getColumnNumber() + 1;
            if (match) {
                getRawToken();
            }
            expression = parseExprSingle();
            maybeSetLine(expression, lineNumber, columnNumber);
        }
        this.comp.pop(lambdaExp);
        lambdaExp.body = expression;
        Expression[] expressionArr2 = {lambdaExp, expressionArr[0]};
        if (z) {
            str = "every";
        }
        return new ApplyExp(makeFunctionExp("gnu.xquery.util.ValuesEvery", str), expressionArr2);
    }

    public Expression parseFunctionDefinition(int i, int i2) throws IOException, SyntaxException {
        int i3;
        int i4 = this.curToken;
        if (i4 != 81 && i4 != 65) {
            return syntaxError("missing function name");
        }
        String str = new String(this.tokenBuffer, 0, this.tokenBufferLength);
        getMessages().setLine(this.port.getName(), this.curLine, this.curColumn);
        Symbol namespaceResolve = namespaceResolve(str, true);
        String namespaceURI = namespaceResolve.getNamespaceURI();
        char c = 'e';
        if (namespaceURI == NamespaceBinding.XML_NAMESPACE || namespaceURI == XQuery.SCHEMA_NAMESPACE || namespaceURI == XQuery.SCHEMA_INSTANCE_NAMESPACE || namespaceURI == XQuery.XQUERY_FUNCTION_NAMESPACE) {
            error('e', "cannot declare function in standard namespace '" + namespaceURI + '\'', "XQST0045");
        } else if (namespaceURI == "") {
            if (!this.comp.isPedantic()) {
                c = 'w';
            }
            error(c, "cannot declare function in empty namespace", "XQST0060");
        } else {
            String str2 = this.libraryModuleNamespace;
            if (!(str2 == null || namespaceURI == str2 || (XQuery.LOCAL_NAMESPACE.equals(namespaceURI) && !this.comp.isPedantic()))) {
                error('e', "function not in namespace of library module", "XQST0048");
            }
        }
        getRawToken();
        if (this.curToken != 40) {
            return syntaxError("missing parameter list:" + this.curToken);
        }
        getRawToken();
        LambdaExp lambdaExp = new LambdaExp();
        maybeSetLine((Expression) lambdaExp, i, i2);
        lambdaExp.setName(str);
        Declaration addDeclaration = this.comp.currentScope().addDeclaration((Object) namespaceResolve);
        if (this.comp.isStatic()) {
            addDeclaration.setFlag(2048);
        }
        lambdaExp.setFlag(2048);
        addDeclaration.setCanRead(true);
        addDeclaration.setProcedureDecl(true);
        maybeSetLine(addDeclaration, i, i2);
        this.comp.push((ScopeExp) lambdaExp);
        if (this.curToken != 41) {
            loop0:
            while (true) {
                Declaration parseVariableDeclaration = parseVariableDeclaration();
                if (parseVariableDeclaration == null) {
                    error("missing parameter name");
                } else {
                    lambdaExp.addDeclaration(parseVariableDeclaration);
                    getRawToken();
                    lambdaExp.min_args++;
                    lambdaExp.max_args++;
                    parseVariableDeclaration.setTypeExp(parseOptionalTypeDeclaration());
                }
                int i5 = this.curToken;
                if (i5 == 41) {
                    break;
                } else if (i5 != 44) {
                    Expression syntaxError = syntaxError("missing ',' in parameter list");
                    do {
                        getRawToken();
                        i3 = this.curToken;
                        if (i3 >= 0 && i3 != 59 && i3 != 59) {
                            if (i3 == 41) {
                                break loop0;
                            }
                        } else {
                            return syntaxError;
                        }
                    } while (i3 != 44);
                } else {
                    getRawToken();
                }
            }
        }
        getRawToken();
        Expression parseOptionalTypeDeclaration = parseOptionalTypeDeclaration();
        lambdaExp.body = parseEnclosedExpr();
        this.comp.pop(lambdaExp);
        if (parseOptionalTypeDeclaration != null) {
            lambdaExp.setCoercedReturnValue(parseOptionalTypeDeclaration, this.interpreter);
        }
        SetExp setExp = new SetExp(addDeclaration, (Expression) lambdaExp);
        setExp.setDefining(true);
        addDeclaration.noteValue(lambdaExp);
        return setExp;
    }

    public Object readObject() throws IOException, SyntaxException {
        return parse((Compilation) null);
    }

    /* access modifiers changed from: protected */
    public Symbol namespaceResolve(String str, boolean z) {
        int indexOf = str.indexOf(58);
        String intern = indexOf >= 0 ? str.substring(0, indexOf).intern() : z ? XQuery.DEFAULT_FUNCTION_PREFIX : XQuery.DEFAULT_ELEMENT_PREFIX;
        String lookupPrefix = QNameUtils.lookupPrefix(intern, this.constructorNamespaces, this.prologNamespaces);
        if (lookupPrefix == null) {
            if (indexOf < 0) {
                lookupPrefix = "";
            } else if (!this.comp.isPedantic()) {
                try {
                    Class.forName(intern);
                    lookupPrefix = "class:" + intern;
                } catch (Exception unused) {
                    lookupPrefix = null;
                }
            }
            if (lookupPrefix == null) {
                getMessages().error('e', "unknown namespace prefix '" + intern + "'", "XPST0081");
                lookupPrefix = "(unknown namespace)";
            }
        }
        if (indexOf >= 0) {
            str = str.substring(indexOf + 1);
        }
        return Symbol.make(lookupPrefix, str, intern);
    }

    /* access modifiers changed from: package-private */
    public void parseSeparator() throws IOException, SyntaxException {
        boolean z = true;
        int lineNumber = this.port.getLineNumber() + 1;
        int columnNumber = this.port.getColumnNumber() + 1;
        if (this.nesting == 0) {
            z = false;
        }
        int skipSpace = skipSpace(z);
        if (skipSpace != 59) {
            if (warnOldVersion && skipSpace != 10) {
                this.curLine = lineNumber;
                this.curColumn = columnNumber;
                error('w', "missing ';' after declaration");
            }
            if (skipSpace >= 0) {
                unread(skipSpace);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:184:0x039c, code lost:
        fatal("'import schema' not implemented", "XQST0009");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x044a, code lost:
        getRawToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0451, code lost:
        if (match("namespace") == false) goto L_0x047d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x0453, code lost:
        getRawToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x045a, code lost:
        if (r1.curToken == 65) goto L_0x0461;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0460, code lost:
        return syntaxError("missing namespace prefix");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0461, code lost:
        r2 = new java.lang.String(r1.tokenBuffer, 0, r1.tokenBufferLength);
        getRawToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0472, code lost:
        if (r1.curToken == 402) goto L_0x0479;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x0478, code lost:
        return syntaxError("missing '=' in namespace declaration");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x0479, code lost:
        getRawToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x047d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0482, code lost:
        if (r1.curToken == 34) goto L_0x0489;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0488, code lost:
        return syntaxError("missing uri in namespace declaration");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x048b, code lost:
        if (r1.tokenBufferLength != 0) goto L_0x0494;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0493, code lost:
        return syntaxError("zero-length target namespace", "XQST0088");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0494, code lost:
        r10 = new java.lang.String(r1.tokenBuffer, 0, r1.tokenBufferLength).intern();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x04a2, code lost:
        if (r2 == null) goto L_0x04ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x04a4, code lost:
        checkAllowedNamespaceDeclaration(r2, r10, false);
        pushNamespace(r2.intern(), r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x04ae, code lost:
        getRawToken();
        gnu.expr.ModuleManager.getInstance().find(r8);
        r11 = r19.getModule();
        r12 = new java.util.Vector();
        r0 = gnu.expr.Compilation.mangleURI(r10);
        r8.setLine(r1.port.getName(), r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x04d4, code lost:
        if (match("at") == false) goto L_0x0544;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x04d6, code lost:
        getRawToken();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x04dd, code lost:
        if (r1.curToken == 34) goto L_0x04e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x04e5, code lost:
        return syntaxError("missing module location");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x04e6, code lost:
        r0 = new java.lang.String(r1.tokenBuffer, 0, r1.tokenBufferLength);
        r2 = gnu.expr.Compilation.mangleURI(r10) + '.' + gnu.xquery.lang.XQuery.makeClassName(r0);
        r3 = kawa.standard.require.lookupModuleFromSourcePath(r0, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0510, code lost:
        if (r3 != null) goto L_0x0526;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0512, code lost:
        r8.error('e', "malformed URL: " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0526, code lost:
        kawa.standard.require.importDefinitions(r2, r3, (gnu.mapping.Procedure) null, r12, r11, r19);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0530, code lost:
        if (r1.nesting == 0) goto L_0x0534;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x0532, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0534, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0535, code lost:
        r0 = skipSpace(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x053b, code lost:
        if (r0 == 44) goto L_0x04d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x053d, code lost:
        unread(r0);
        parseSeparator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0544, code lost:
        r13 = gnu.expr.ModuleManager.getInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:?, code lost:
        r13.loadPackageInfo(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x054c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x054d, code lost:
        error('e', "error loading map for " + r10 + " - " + r0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:381:0x0772  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression parse(gnu.expr.Compilation r19) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r18 = this;
            r1 = r18
            r8 = r19
            r1.comp = r8
            int r0 = r18.skipSpace()
            r2 = 0
            if (r0 >= 0) goto L_0x000e
            return r2
        L_0x000e:
            int r3 = r1.parseCount
            r9 = 1
            int r3 = r3 + r9
            r1.parseCount = r3
            r1.unread(r0)
            int r3 = r18.getLineNumber()
            int r3 = r3 + r9
            int r4 = r18.getColumnNumber()
            int r4 = r4 + r9
            r5 = 35
            r6 = 10
            if (r0 != r5) goto L_0x0050
            if (r3 != r9) goto L_0x0050
            if (r4 != r9) goto L_0x0050
            r18.read()
            int r0 = r18.read()
            r5 = 33
            if (r0 != r5) goto L_0x003e
            int r0 = r18.read()
            r5 = 47
            if (r0 == r5) goto L_0x0043
        L_0x003e:
            java.lang.String r5 = "'#' is only allowed in initial '#!/PROGRAM'"
            r1.error(r5)
        L_0x0043:
            r5 = 13
            if (r0 == r5) goto L_0x0050
            if (r0 == r6) goto L_0x0050
            if (r0 < 0) goto L_0x0050
            int r0 = r18.read()
            goto L_0x0043
        L_0x0050:
            int r0 = r18.getRawToken()
            r5 = -1
            if (r0 != r5) goto L_0x0058
            return r2
        L_0x0058:
            r18.peekOperand()
            int r0 = r1.curToken
            r7 = 119(0x77, float:1.67E-43)
            java.lang.String r10 = "namespace"
            r11 = 65
            if (r0 != r11) goto L_0x007c
            java.lang.Object r0 = r1.curValue
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r10.equals(r0)
            if (r0 == 0) goto L_0x007c
            boolean r0 = warnOldVersion
            if (r0 == 0) goto L_0x0078
            java.lang.String r0 = "use 'declare namespace' instead of 'namespace'"
            r1.error(r7, r0)
        L_0x0078:
            r0 = 78
            r1.curToken = r0
        L_0x007c:
            int r0 = r1.curToken
            r12 = 66
            if (r0 == r12) goto L_0x0829
            r12 = 69
            r15 = 101(0x65, float:1.42E-43)
            if (r0 == r12) goto L_0x0791
            r12 = 89
            if (r0 == r12) goto L_0x06c8
            r7 = 111(0x6f, float:1.56E-43)
            if (r0 == r7) goto L_0x0662
            java.lang.String r7 = "missing '=' in namespace declaration"
            java.lang.String r12 = "XQST0088"
            java.lang.String r2 = "missing namespace prefix"
            java.lang.String r14 = "missing uri in namespace declaration"
            switch(r0) {
                case 71: goto L_0x0618;
                case 72: goto L_0x05cf;
                case 73: goto L_0x044a;
                default: goto L_0x009b;
            }
        L_0x009b:
            java.lang.String r13 = "strip"
            java.lang.String r11 = "preserve"
            switch(r0) {
                case 75: goto L_0x0417;
                case 76: goto L_0x03a5;
                case 77: goto L_0x0296;
                case 78: goto L_0x0296;
                case 79: goto L_0x0791;
                case 80: goto L_0x026e;
                default: goto L_0x00a2;
            }
        L_0x00a2:
            switch(r0) {
                case 83: goto L_0x021c;
                case 84: goto L_0x039c;
                case 85: goto L_0x01e5;
                case 86: goto L_0x00e8;
                case 87: goto L_0x00bf;
                default: goto L_0x00a5;
            }
        L_0x00a5:
            gnu.expr.Expression r0 = r1.parseExprSequence(r5, r9)
            int r2 = r1.curToken
            if (r2 != r6) goto L_0x00b0
            r1.unread(r6)
        L_0x00b0:
            r1.maybeSetLine((gnu.expr.Expression) r0, (int) r3, (int) r4)
            java.lang.String r2 = r1.libraryModuleNamespace
            if (r2 == 0) goto L_0x00be
            java.lang.String r2 = "query body in a library module"
            java.lang.String r3 = "XPST0003"
            r1.error(r15, r2, r3)
        L_0x00be:
            return r0
        L_0x00bf:
            int r0 = r18.getLineNumber()
            int r0 = r0 + r9
            int r2 = r18.getColumnNumber()
            int r2 = r2 + r9
            java.lang.String r3 = "unexpected end-of-file after 'define QName'"
            int r3 = r1.peekNonSpace(r3)
            r4 = 40
            if (r3 != r4) goto L_0x00e1
            java.lang.String r3 = "'missing 'function' after 'define'"
            r1.syntaxError(r3)
            r3 = 65
            r1.curToken = r3
            gnu.expr.Expression r0 = r1.parseFunctionDefinition(r0, r2)
            return r0
        L_0x00e1:
            java.lang.String r0 = "missing keyword after 'define'"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x00e8:
            r18.getRawToken()
            gnu.expr.Declaration r0 = r18.parseVariableDeclaration()
            if (r0 != 0) goto L_0x00f8
            java.lang.String r0 = "missing Variable"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x00f8:
            java.lang.Object r2 = r0.getSymbol()
            boolean r5 = r2 instanceof java.lang.String
            if (r5 == 0) goto L_0x011b
            gnu.text.SourceMessages r5 = r18.getMessages()
            gnu.text.LineBufferedReader r6 = r1.port
            java.lang.String r6 = r6.getName()
            int r7 = r1.curLine
            int r10 = r1.curColumn
            r5.setLine(r6, r7, r10)
            java.lang.String r2 = (java.lang.String) r2
            r5 = 0
            gnu.mapping.Symbol r2 = r1.namespaceResolve(r2, r5)
            r0.setSymbol(r2)
        L_0x011b:
            java.lang.String r2 = r1.libraryModuleNamespace
            if (r2 == 0) goto L_0x0142
            java.lang.Object r2 = r0.getSymbol()
            gnu.mapping.Symbol r2 = (gnu.mapping.Symbol) r2
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r5 = r1.libraryModuleNamespace
            if (r2 == r5) goto L_0x0142
            java.lang.String r5 = "http://www.w3.org/2005/xquery-local-functions"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x013b
            boolean r2 = r19.isPedantic()
            if (r2 == 0) goto L_0x0142
        L_0x013b:
            java.lang.String r2 = "variable not in namespace of library module"
            java.lang.String r5 = "XQST0048"
            r1.error(r15, r2, r5)
        L_0x0142:
            gnu.expr.ScopeExp r2 = r19.currentScope()
            r2.addDeclaration((gnu.expr.Declaration) r0)
            r18.getRawToken()
            gnu.expr.Expression r2 = r18.parseOptionalTypeDeclaration()
            r0.setCanRead(r9)
            r5 = 16384(0x4000, double:8.0948E-320)
            r0.setFlag(r5)
            int r5 = r1.curToken
            r6 = 402(0x192, float:5.63E-43)
            if (r5 == r6) goto L_0x0165
            r7 = 76
            if (r5 != r7) goto L_0x0163
            goto L_0x0165
        L_0x0163:
            r5 = 0
            goto L_0x0170
        L_0x0165:
            if (r5 != r6) goto L_0x016c
            java.lang.String r5 = "declare variable contains '=' instead of ':='"
            r1.error(r5)
        L_0x016c:
            r18.getRawToken()
            r5 = 1
        L_0x0170:
            int r6 = r1.curToken
            r7 = 123(0x7b, float:1.72E-43)
            if (r6 != r7) goto L_0x0183
            java.lang.String r5 = "obsolete '{' in variable declaration"
            r1.warnOldVersion(r5)
            gnu.expr.Expression r5 = r18.parseEnclosedExpr()
            r18.parseSeparator()
            goto L_0x01d2
        L_0x0183:
            java.lang.String r6 = "external"
            boolean r6 = r1.match(r6)
            if (r6 == 0) goto L_0x01b8
            r5 = 2
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r5]
            gnu.expr.QuoteExp r6 = new gnu.expr.QuoteExp
            java.lang.Object r7 = r0.getSymbol()
            r6.<init>(r7)
            r7 = 0
            gnu.expr.ApplyExp r6 = castQName(r6, r7)
            r5[r7] = r6
            if (r2 != 0) goto L_0x01a3
            gnu.expr.QuoteExp r6 = gnu.expr.QuoteExp.nullExp
            goto L_0x01a4
        L_0x01a3:
            r6 = r2
        L_0x01a4:
            r5[r9] = r6
            gnu.expr.ApplyExp r6 = new gnu.expr.ApplyExp
            gnu.expr.QuoteExp r7 = getExternalFunction
            r6.<init>((gnu.expr.Expression) r7, (gnu.expr.Expression[]) r5)
            int r5 = r1.curLine
            int r7 = r1.curColumn
            r1.maybeSetLine((gnu.expr.Expression) r6, (int) r5, (int) r7)
            r18.getRawToken()
            goto L_0x01d1
        L_0x01b8:
            gnu.expr.Expression r6 = r18.parseExpr()
            if (r5 == 0) goto L_0x01c4
            if (r6 != 0) goto L_0x01c1
            goto L_0x01c4
        L_0x01c1:
            r16 = 0
            goto L_0x01cc
        L_0x01c4:
            java.lang.String r5 = "expected ':= init' or 'external'"
            gnu.expr.Expression r5 = r1.syntaxError(r5)
            r16 = r5
        L_0x01cc:
            if (r6 != 0) goto L_0x01d1
            r5 = r16
            goto L_0x01d2
        L_0x01d1:
            r5 = r6
        L_0x01d2:
            if (r2 == 0) goto L_0x01d8
            gnu.expr.ApplyExp r5 = gnu.expr.Compilation.makeCoercion((gnu.expr.Expression) r5, (gnu.expr.Expression) r2)
        L_0x01d8:
            r0.noteValue(r5)
            gnu.expr.SetExp r0 = gnu.expr.SetExp.makeDefinition((gnu.expr.Declaration) r0, (gnu.expr.Expression) r5)
            r1.maybeSetLine((gnu.expr.Expression) r0, (int) r3, (int) r4)
            r1.seenDeclaration = r9
            return r0
        L_0x01e5:
            boolean r0 = r1.orderingModeSeen
            if (r0 == 0) goto L_0x01f4
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x01f4
            java.lang.String r0 = "duplicate 'declare ordering' seen"
            java.lang.String r2 = "XQST0065"
            r1.syntaxError(r0, r2)
        L_0x01f4:
            r1.orderingModeSeen = r9
            r18.getRawToken()
            java.lang.String r0 = "ordered"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0205
            r2 = 0
            r1.orderingModeUnordered = r2
            goto L_0x020f
        L_0x0205:
            java.lang.String r0 = "unordered"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0215
            r1.orderingModeUnordered = r9
        L_0x020f:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0215:
            java.lang.String r0 = "ordering declaration must be ordered or unordered"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x021c:
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 402(0x192, float:5.63E-43)
            if (r0 != r2) goto L_0x022d
            java.lang.String r0 = "obsolate '=' in boundary-space declaration"
            r1.warnOldVersion(r0)
            r18.getRawToken()
        L_0x022d:
            boolean r0 = r1.boundarySpaceDeclarationSeen
            if (r0 == 0) goto L_0x023c
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x023c
            java.lang.String r0 = "duplicate 'declare boundary-space' seen"
            java.lang.String r2 = "XQST0068"
            r1.syntaxError(r0, r2)
        L_0x023c:
            r1.boundarySpaceDeclarationSeen = r9
            boolean r0 = r1.match(r11)
            if (r0 == 0) goto L_0x0247
            r1.boundarySpacePreserve = r9
            goto L_0x0261
        L_0x0247:
            boolean r0 = r1.match(r13)
            if (r0 == 0) goto L_0x0251
            r2 = 0
            r1.boundarySpacePreserve = r2
            goto L_0x0261
        L_0x0251:
            r2 = 0
            java.lang.String r0 = "skip"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0267
            java.lang.String r0 = "update: declare boundary-space skip -> strip"
            r1.warnOldVersion(r0)
            r1.boundarySpacePreserve = r2
        L_0x0261:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0267:
            java.lang.String r0 = "boundary-space declaration must be preserve or strip"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x026e:
            int r0 = r18.getLineNumber()
            int r0 = r0 + r9
            int r2 = r18.getColumnNumber()
            int r2 = r2 + r9
            r18.getRawToken()
            java.lang.String r5 = "unexpected end-of-file after 'define function'"
            r1.peekNonSpace(r5)
            r5 = 100
            char r5 = r1.pushNesting(r5)
            gnu.expr.Expression r0 = r1.parseFunctionDefinition(r0, r2)
            r1.popNesting(r5)
            r18.parseSeparator()
            r1.maybeSetLine((gnu.expr.Expression) r0, (int) r3, (int) r4)
            r1.seenDeclaration = r9
            return r0
        L_0x0296:
            r5 = 77
            if (r0 != r5) goto L_0x02a4
            java.lang.String r6 = r1.libraryModuleNamespace
            if (r6 == 0) goto L_0x02a4
            java.lang.String r6 = "duplicate module declaration"
            r1.error(r15, r6)
            goto L_0x02b1
        L_0x02a4:
            boolean r6 = r1.seenDeclaration
            if (r6 == 0) goto L_0x02b1
            boolean r6 = r1.interactive
            if (r6 != 0) goto L_0x02b1
            java.lang.String r6 = "namespace declared after function/variable/option"
            r1.error(r15, r6)
        L_0x02b1:
            int r6 = r1.nesting
            if (r6 == 0) goto L_0x02b7
            r6 = 1
            goto L_0x02b8
        L_0x02b7:
            r6 = 0
        L_0x02b8:
            int r6 = r1.skipSpace(r6)
            if (r6 < 0) goto L_0x039c
            r18.unread()
            char r6 = (char) r6
            boolean r6 = gnu.xml.XName.isNameStart(r6)
            if (r6 == 0) goto L_0x039c
            r18.getRawToken()
            int r3 = r1.curToken
            r4 = 65
            if (r3 == r4) goto L_0x02d6
            gnu.expr.Expression r0 = r1.syntaxError(r2)
            return r0
        L_0x02d6:
            java.lang.String r2 = new java.lang.String
            char[] r3 = r1.tokenBuffer
            int r4 = r1.tokenBufferLength
            r6 = 0
            r2.<init>(r3, r6, r4)
            r18.getRawToken()
            int r3 = r1.curToken
            r4 = 402(0x192, float:5.63E-43)
            if (r3 == r4) goto L_0x02ee
            gnu.expr.Expression r0 = r1.syntaxError(r7)
            return r0
        L_0x02ee:
            r18.getRawToken()
            int r3 = r1.curToken
            r4 = 34
            if (r3 == r4) goto L_0x02fc
            gnu.expr.Expression r0 = r1.syntaxError(r14)
            return r0
        L_0x02fc:
            java.lang.String r3 = new java.lang.String
            char[] r4 = r1.tokenBuffer
            int r6 = r1.tokenBufferLength
            r7 = 0
            r3.<init>(r4, r7, r6)
            java.lang.String r3 = r3.intern()
            java.lang.String r2 = r2.intern()
            gnu.xml.NamespaceBinding r4 = r1.prologNamespaces
        L_0x0310:
            gnu.xml.NamespaceBinding r6 = builtinNamespaces
            if (r4 == r6) goto L_0x033b
            java.lang.String r6 = r4.getPrefix()
            if (r6 != r2) goto L_0x0336
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "duplicate declarations for the same namespace prefix '"
            r4.append(r6)
            r4.append(r2)
            java.lang.String r6 = "'"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.String r6 = "XQST0033"
            r1.error(r15, r4, r6)
            goto L_0x033b
        L_0x0336:
            gnu.xml.NamespaceBinding r4 = r4.getNext()
            goto L_0x0310
        L_0x033b:
            r1.pushNamespace(r2, r3)
            r4 = 0
            r1.checkAllowedNamespaceDeclaration(r2, r3, r4)
            r18.parseSeparator()
            if (r0 != r5) goto L_0x0399
            gnu.expr.ModuleExp r0 = r19.getModule()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = gnu.expr.Compilation.mangleURI(r3)
            r2.append(r4)
            r4 = 46
            r2.append(r4)
            java.lang.String r4 = r0.getFileName()
            java.lang.String r4 = gnu.xquery.lang.XQuery.makeClassName(r4)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.setName(r2)
            gnu.bytecode.ClassType r4 = new gnu.bytecode.ClassType
            r4.<init>(r2)
            r8.mainClass = r4
            gnu.bytecode.ClassType r2 = r8.mainClass
            r0.setType(r2)
            gnu.expr.ModuleManager r2 = gnu.expr.ModuleManager.getInstance()
            gnu.expr.ModuleInfo r2 = r2.find(r8)
            r2.setNamespaceUri(r3)
            gnu.bytecode.ClassType r2 = r8.mainClass
            r0.setType(r2)
            int r0 = r3.length()
            if (r0 != 0) goto L_0x0397
            java.lang.String r0 = "zero-length module namespace"
            gnu.expr.Expression r0 = r1.syntaxError(r0, r12)
            return r0
        L_0x0397:
            r1.libraryModuleNamespace = r3
        L_0x0399:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x039c:
            java.lang.String r0 = "'import schema' not implemented"
            java.lang.String r5 = "XQST0009"
            r1.fatal(r0, r5)
            goto L_0x044a
        L_0x03a5:
            r18.getRawToken()
            boolean r0 = r1.copyNamespacesDeclarationSeen
            if (r0 == 0) goto L_0x03b7
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x03b7
            java.lang.String r0 = "duplicate 'declare copy-namespaces' seen"
            java.lang.String r2 = "XQST0055"
            r1.syntaxError(r0, r2)
        L_0x03b7:
            r1.copyNamespacesDeclarationSeen = r9
            boolean r0 = r1.match(r11)
            if (r0 == 0) goto L_0x03c5
            int r0 = r1.copyNamespacesMode
            r0 = r0 | r9
            r1.copyNamespacesMode = r0
            goto L_0x03d3
        L_0x03c5:
            java.lang.String r0 = "no-preserve"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0410
            int r0 = r1.copyNamespacesMode
            r0 = r0 & -2
            r1.copyNamespacesMode = r0
        L_0x03d3:
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 44
            if (r0 == r2) goto L_0x03e3
            java.lang.String r0 = "missing ',' in copy-namespaces declaration"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x03e3:
            r18.getRawToken()
            java.lang.String r0 = "inherit"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x03f5
            int r0 = r1.copyNamespacesMode
            r0 = r0 | 2
            r1.copyNamespacesMode = r0
            goto L_0x0403
        L_0x03f5:
            java.lang.String r0 = "no-inherit"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0409
            int r0 = r1.copyNamespacesMode
            r0 = r0 & -3
            r1.copyNamespacesMode = r0
        L_0x0403:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0409:
            java.lang.String r0 = "expected 'inherit' or 'no-inherit' in copy-namespaces declaration"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x0410:
            java.lang.String r0 = "expected 'preserve' or 'no-preserve' after 'declare copy-namespaces'"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x0417:
            r18.getRawToken()
            boolean r0 = r1.constructionModeDeclarationSeen
            if (r0 == 0) goto L_0x0429
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x0429
            java.lang.String r0 = "duplicate 'declare construction' seen"
            java.lang.String r2 = "XQST0067"
            r1.syntaxError(r0, r2)
        L_0x0429:
            r1.constructionModeDeclarationSeen = r9
            boolean r0 = r1.match(r13)
            if (r0 == 0) goto L_0x0434
            r1.constructionModeStrip = r9
            goto L_0x043d
        L_0x0434:
            boolean r0 = r1.match(r11)
            if (r0 == 0) goto L_0x0443
            r2 = 0
            r1.constructionModeStrip = r2
        L_0x043d:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0443:
            java.lang.String r0 = "construction declaration must be strip or preserve"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x044a:
            r18.getRawToken()
            boolean r0 = r1.match(r10)
            if (r0 == 0) goto L_0x047d
            r18.getRawToken()
            int r0 = r1.curToken
            r5 = 65
            if (r0 == r5) goto L_0x0461
            gnu.expr.Expression r0 = r1.syntaxError(r2)
            return r0
        L_0x0461:
            java.lang.String r2 = new java.lang.String
            char[] r0 = r1.tokenBuffer
            int r5 = r1.tokenBufferLength
            r6 = 0
            r2.<init>(r0, r6, r5)
            r18.getRawToken()
            int r0 = r1.curToken
            r5 = 402(0x192, float:5.63E-43)
            if (r0 == r5) goto L_0x0479
            gnu.expr.Expression r0 = r1.syntaxError(r7)
            return r0
        L_0x0479:
            r18.getRawToken()
            goto L_0x047e
        L_0x047d:
            r2 = 0
        L_0x047e:
            int r0 = r1.curToken
            r5 = 34
            if (r0 == r5) goto L_0x0489
            gnu.expr.Expression r0 = r1.syntaxError(r14)
            return r0
        L_0x0489:
            int r0 = r1.tokenBufferLength
            if (r0 != 0) goto L_0x0494
            java.lang.String r0 = "zero-length target namespace"
            gnu.expr.Expression r0 = r1.syntaxError(r0, r12)
            return r0
        L_0x0494:
            java.lang.String r0 = new java.lang.String
            char[] r5 = r1.tokenBuffer
            int r6 = r1.tokenBufferLength
            r7 = 0
            r0.<init>(r5, r7, r6)
            java.lang.String r10 = r0.intern()
            if (r2 == 0) goto L_0x04ae
            r1.checkAllowedNamespaceDeclaration(r2, r10, r7)
            java.lang.String r0 = r2.intern()
            r1.pushNamespace(r0, r10)
        L_0x04ae:
            r18.getRawToken()
            gnu.expr.ModuleManager r0 = gnu.expr.ModuleManager.getInstance()
            r0.find(r8)
            gnu.expr.ModuleExp r11 = r19.getModule()
            java.util.Vector r12 = new java.util.Vector
            r12.<init>()
            java.lang.String r0 = gnu.expr.Compilation.mangleURI(r10)
            gnu.text.LineBufferedReader r2 = r1.port
            java.lang.String r2 = r2.getName()
            r8.setLine(r2, r3, r4)
            java.lang.String r2 = "at"
            boolean r2 = r1.match(r2)
            if (r2 == 0) goto L_0x0544
        L_0x04d6:
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 34
            if (r0 == r2) goto L_0x04e6
            java.lang.String r0 = "missing module location"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x04e6:
            java.lang.String r0 = new java.lang.String
            char[] r2 = r1.tokenBuffer
            int r3 = r1.tokenBufferLength
            r4 = 0
            r0.<init>(r2, r4, r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = gnu.expr.Compilation.mangleURI(r10)
            r2.append(r3)
            r3 = 46
            r2.append(r3)
            java.lang.String r3 = gnu.xquery.lang.XQuery.makeClassName(r0)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            gnu.expr.ModuleInfo r3 = kawa.standard.require.lookupModuleFromSourcePath(r0, r11)
            if (r3 != 0) goto L_0x0526
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "malformed URL: "
            r4.append(r5)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r8.error(r15, r0)
        L_0x0526:
            r4 = 0
            r5 = r12
            r6 = r11
            r7 = r19
            kawa.standard.require.importDefinitions(r2, r3, r4, r5, r6, r7)
            int r0 = r1.nesting
            if (r0 == 0) goto L_0x0534
            r0 = 1
            goto L_0x0535
        L_0x0534:
            r0 = 0
        L_0x0535:
            int r0 = r1.skipSpace(r0)
            r2 = 44
            if (r0 == r2) goto L_0x04d6
            r1.unread(r0)
            r18.parseSeparator()
            goto L_0x0592
        L_0x0544:
            gnu.expr.ModuleManager r13 = gnu.expr.ModuleManager.getInstance()
            r13.loadPackageInfo(r0)     // Catch:{ ClassNotFoundException -> 0x056a, all -> 0x054c }
            goto L_0x056a
        L_0x054c:
            r0 = move-exception
            r2 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "error loading map for "
            r0.append(r3)
            r0.append(r10)
            java.lang.String r3 = " - "
            r0.append(r3)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.error(r15, r0)
        L_0x056a:
            r0 = 0
            r17 = 0
        L_0x056d:
            gnu.expr.ModuleInfo r3 = r13.getModule(r0)
            if (r3 != 0) goto L_0x05b3
            if (r17 != 0) goto L_0x0589
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "no module found for "
            r0.append(r2)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            r1.error(r15, r0)
        L_0x0589:
            int r0 = r1.curToken
            r2 = 59
            if (r0 == r2) goto L_0x0592
            r18.parseSeparator()
        L_0x0592:
            java.util.Stack<java.lang.Object> r0 = r8.pendingImports
            if (r0 == 0) goto L_0x05a5
            java.util.Stack<java.lang.Object> r0 = r8.pendingImports
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x05a5
            java.lang.String r0 = "module import forms a cycle"
            java.lang.String r2 = "XQST0073"
            r1.error(r15, r0, r2)
        L_0x05a5:
            int r0 = r12.size()
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r12.toArray(r0)
            gnu.expr.Expression r0 = gnu.expr.BeginExp.canonicalize((gnu.expr.Expression[]) r0)
            return r0
        L_0x05b3:
            java.lang.String r2 = r3.getNamespaceUri()
            boolean r2 = r10.equals(r2)
            if (r2 != 0) goto L_0x05be
            goto L_0x05cc
        L_0x05be:
            int r17 = r17 + 1
            java.lang.String r2 = r3.getClassName()
            r4 = 0
            r5 = r12
            r6 = r11
            r7 = r19
            kawa.standard.require.importDefinitions(r2, r3, r4, r5, r6, r7)
        L_0x05cc:
            int r0 = r0 + 1
            goto L_0x056d
        L_0x05cf:
            r18.getRawToken()
            java.lang.String r0 = "empty"
            boolean r0 = r1.match(r0)
            boolean r2 = r1.emptyOrderDeclarationSeen
            if (r2 == 0) goto L_0x05e7
            boolean r2 = r1.interactive
            if (r2 != 0) goto L_0x05e7
            java.lang.String r2 = "duplicate 'declare default empty order' seen"
            java.lang.String r3 = "XQST0069"
            r1.syntaxError(r2, r3)
        L_0x05e7:
            r1.emptyOrderDeclarationSeen = r9
            java.lang.String r2 = "expected 'empty greatest' or 'empty least'"
            if (r0 == 0) goto L_0x05f1
            r18.getRawToken()
            goto L_0x05f4
        L_0x05f1:
            r1.syntaxError(r2)
        L_0x05f4:
            java.lang.String r0 = "greatest"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0601
            r0 = 71
            r1.defaultEmptyOrder = r0
            goto L_0x060d
        L_0x0601:
            java.lang.String r0 = "least"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x0613
            r0 = 76
            r1.defaultEmptyOrder = r0
        L_0x060d:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0613:
            gnu.expr.Expression r0 = r1.syntaxError(r2)
            return r0
        L_0x0618:
            gnu.xquery.util.NamedCollator r0 = r1.defaultCollator
            java.lang.String r2 = "XQST0038"
            if (r0 == 0) goto L_0x0627
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x0627
            java.lang.String r0 = "duplicate default collation declaration"
            r1.error(r15, r0, r2)
        L_0x0627:
            java.lang.Object r0 = r18.parseURILiteral()
            boolean r3 = r0 instanceof gnu.expr.Expression
            if (r3 == 0) goto L_0x0632
            gnu.expr.Expression r0 = (gnu.expr.Expression) r0
            return r0
        L_0x0632:
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r0 = r1.resolveAgainstBaseUri(r0)     // Catch:{ Exception -> 0x063f }
            gnu.xquery.util.NamedCollator r3 = gnu.xquery.util.NamedCollator.make(r0)     // Catch:{ Exception -> 0x063f }
            r1.defaultCollator = r3     // Catch:{ Exception -> 0x063f }
            goto L_0x065c
        L_0x063f:
            gnu.xquery.util.NamedCollator r3 = gnu.xquery.util.NamedCollator.codepointCollation
            r1.defaultCollator = r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "unknown collation '"
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = "'"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.error(r15, r0, r2)
        L_0x065c:
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0662:
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 81
            if (r0 == r2) goto L_0x0671
            java.lang.String r0 = "expected QName after 'declare option'"
            r1.syntaxError(r0)
            goto L_0x06c0
        L_0x0671:
            java.lang.String r0 = new java.lang.String
            char[] r2 = r1.tokenBuffer
            int r3 = r1.tokenBufferLength
            r4 = 0
            r0.<init>(r2, r4, r3)
            gnu.text.SourceMessages r2 = r18.getMessages()
            gnu.text.LineBufferedReader r3 = r1.port
            java.lang.String r3 = r3.getName()
            int r5 = r1.curLine
            int r6 = r1.curColumn
            r2.setLine(r3, r5, r6)
            gnu.mapping.Symbol r2 = r1.namespaceResolve(r0, r4)
            r18.getRawToken()
            int r3 = r1.curToken
            r4 = 34
            if (r3 == r4) goto L_0x06b3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "expected string literal after 'declare option "
            r2.append(r3)
            r2.append(r0)
            r0 = 39
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.syntaxError(r0)
            goto L_0x06c0
        L_0x06b3:
            java.lang.String r0 = new java.lang.String
            char[] r3 = r1.tokenBuffer
            int r4 = r1.tokenBufferLength
            r5 = 0
            r0.<init>(r3, r5, r4)
            r1.handleOption(r2, r0)
        L_0x06c0:
            r18.parseSeparator()
            r1.seenDeclaration = r9
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x06c8:
            int r0 = r1.parseCount
            if (r0 == r9) goto L_0x06d2
            java.lang.String r0 = "'xquery version' does not start module"
            r1.error(r15, r0)
            goto L_0x06db
        L_0x06d2:
            int r0 = r1.commentCount
            if (r0 <= 0) goto L_0x06db
            java.lang.String r0 = "comments should not precede 'xquery version'"
            r1.error(r7, r0)
        L_0x06db:
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 34
            if (r0 != r2) goto L_0x078a
            java.lang.String r0 = new java.lang.String
            char[] r2 = r1.tokenBuffer
            int r3 = r1.tokenBufferLength
            r4 = 0
            r0.<init>(r2, r4, r3)
            java.lang.String r2 = "1.0"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x070c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unrecognized xquery version "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "XQST0031"
            r1.error(r15, r0, r2)
        L_0x070c:
            r18.getRawToken()
            java.lang.String r0 = "encoding"
            boolean r0 = r1.match(r0)
            if (r0 == 0) goto L_0x077c
            r18.getRawToken()
            int r0 = r1.curToken
            r2 = 34
            if (r0 == r2) goto L_0x0727
            java.lang.String r0 = "invalid encoding specification"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x0727:
            java.lang.String r0 = new java.lang.String
            char[] r2 = r1.tokenBuffer
            int r3 = r1.tokenBufferLength
            r4 = 0
            r0.<init>(r2, r4, r3)
            int r0 = r1.tokenBufferLength
            if (r0 != 0) goto L_0x0736
            goto L_0x076e
        L_0x0736:
            r13 = 0
        L_0x0737:
            int r0 = r0 + r5
            if (r0 < 0) goto L_0x0770
            if (r13 != 0) goto L_0x0770
            char[] r2 = r1.tokenBuffer
            char r2 = r2[r0]
            r3 = 65
            if (r2 < r3) goto L_0x074c
            r4 = 90
            if (r2 <= r4) goto L_0x0749
            goto L_0x074c
        L_0x0749:
            r4 = 46
            goto L_0x0737
        L_0x074c:
            r4 = 97
            if (r2 < r4) goto L_0x0755
            r4 = 122(0x7a, float:1.71E-43)
            if (r2 > r4) goto L_0x0755
            goto L_0x0749
        L_0x0755:
            if (r0 == 0) goto L_0x076c
            r4 = 48
            if (r2 < r4) goto L_0x075f
            r4 = 57
            if (r2 <= r4) goto L_0x0749
        L_0x075f:
            r4 = 46
            if (r2 == r4) goto L_0x0737
            r6 = 95
            if (r2 == r6) goto L_0x0737
            r6 = 45
            if (r2 == r6) goto L_0x0737
            goto L_0x076e
        L_0x076c:
            r4 = 46
        L_0x076e:
            r13 = 1
            goto L_0x0737
        L_0x0770:
            if (r13 == 0) goto L_0x0779
            java.lang.String r0 = "invalid encoding name syntax"
            java.lang.String r2 = "XQST0087"
            r1.error(r15, r0, r2)
        L_0x0779:
            r18.getRawToken()
        L_0x077c:
            int r0 = r1.curToken
            r2 = 59
            if (r0 == r2) goto L_0x0787
            java.lang.String r0 = "missing ';'"
            r1.syntaxError(r0)
        L_0x0787:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x078a:
            java.lang.String r0 = "missing version string after 'xquery version'"
            gnu.expr.Expression r0 = r1.syntaxError(r0)
            return r0
        L_0x0791:
            r2 = 79
            if (r0 != r2) goto L_0x0797
            r5 = 1
            goto L_0x0798
        L_0x0797:
            r5 = 0
        L_0x0798:
            if (r5 == 0) goto L_0x079d
            java.lang.String r0 = "(functions)"
            goto L_0x079f
        L_0x079d:
            java.lang.String r0 = gnu.xquery.lang.XQuery.DEFAULT_ELEMENT_PREFIX
        L_0x079f:
            gnu.xml.NamespaceBinding r2 = r1.prologNamespaces
            gnu.xml.NamespaceBinding r3 = builtinNamespaces
            java.lang.String r2 = r2.resolve(r0, r3)
            if (r2 == 0) goto L_0x07b1
            java.lang.String r2 = "duplicate default namespace declaration"
            java.lang.String r3 = "XQST0066"
            r1.error(r15, r2, r3)
            goto L_0x07be
        L_0x07b1:
            boolean r2 = r1.seenDeclaration
            if (r2 == 0) goto L_0x07be
            boolean r2 = r1.interactive
            if (r2 != 0) goto L_0x07be
            java.lang.String r2 = "default namespace declared after function/variable/option"
            r1.error(r15, r2)
        L_0x07be:
            r18.getRawToken()
            boolean r2 = r1.match(r10)
            if (r2 == 0) goto L_0x07cd
            r18.getRawToken()
            r4 = 402(0x192, float:5.63E-43)
            goto L_0x07e3
        L_0x07cd:
            java.lang.String r2 = "expected 'namespace' keyword"
            int r3 = r1.curToken
            r4 = 34
            if (r3 == r4) goto L_0x07de
            r4 = 402(0x192, float:5.63E-43)
            if (r3 == r4) goto L_0x07e0
            gnu.expr.Expression r0 = r1.declError(r2)
            return r0
        L_0x07de:
            r4 = 402(0x192, float:5.63E-43)
        L_0x07e0:
            r1.warnOldVersion(r2)
        L_0x07e3:
            int r2 = r1.curToken
            if (r2 == r4) goto L_0x07eb
            r3 = 76
            if (r2 != r3) goto L_0x07f3
        L_0x07eb:
            java.lang.String r2 = "extra '=' in default namespace declaration"
            r1.warnOldVersion(r2)
            r18.getRawToken()
        L_0x07f3:
            int r2 = r1.curToken
            r3 = 34
            if (r2 == r3) goto L_0x0800
            java.lang.String r0 = "missing namespace uri"
            gnu.expr.Expression r0 = r1.declError(r0)
            return r0
        L_0x0800:
            java.lang.String r2 = new java.lang.String
            char[] r3 = r1.tokenBuffer
            int r4 = r1.tokenBufferLength
            r6 = 0
            r2.<init>(r3, r6, r4)
            java.lang.String r2 = r2.intern()
            if (r5 == 0) goto L_0x081b
            gnu.mapping.Namespace[] r3 = new gnu.mapping.Namespace[r9]
            r1.functionNamespacePath = r3
            gnu.mapping.Namespace r4 = gnu.mapping.Namespace.valueOf(r2)
            r3[r6] = r4
            goto L_0x081d
        L_0x081b:
            r1.defaultElementNamespace = r2
        L_0x081d:
            r1.pushNamespace(r0, r2)
            r1.checkAllowedNamespaceDeclaration(r0, r2, r6)
            r18.parseSeparator()
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        L_0x0829:
            boolean r0 = r1.baseURIDeclarationSeen
            if (r0 == 0) goto L_0x0838
            boolean r0 = r1.interactive
            if (r0 != 0) goto L_0x0838
            java.lang.String r0 = "duplicate 'declare base-uri' seen"
            java.lang.String r2 = "XQST0032"
            r1.syntaxError(r0, r2)
        L_0x0838:
            r1.baseURIDeclarationSeen = r9
            java.lang.Object r0 = r18.parseURILiteral()
            boolean r2 = r0 instanceof gnu.expr.Expression
            if (r2 == 0) goto L_0x0845
            gnu.expr.Expression r0 = (gnu.expr.Expression) r0
            return r0
        L_0x0845:
            r18.parseSeparator()
            java.lang.String r0 = (java.lang.String) r0
            r1.setStaticBaseUri(r0)
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.parse(gnu.expr.Compilation):gnu.expr.Expression");
    }

    public static Expression makeFunctionExp(String str, String str2) {
        return makeFunctionExp(str, Compilation.mangleNameIfNeeded(str2), str2);
    }

    public static Expression makeFunctionExp(String str, String str2, String str3) {
        return new ReferenceExp(str3, Declaration.getDeclarationValueFromStatic(str, str2, str3));
    }

    /* access modifiers changed from: package-private */
    public String tokenString() {
        int i = this.curToken;
        if (i == -1) {
            return "<EOF>";
        }
        if (i != 34) {
            if (i != 65) {
                if (i == 70) {
                    return new String(this.tokenBuffer, 0, this.tokenBufferLength) + " + '('";
                } else if (i != 81) {
                    if (i >= 100 && i - 100 < 13) {
                        return axisNames[this.curToken - 100] + "::-axis(" + this.curToken + ")";
                    } else if (i < 32 || i >= 127) {
                        return Integer.toString(i);
                    } else {
                        return Integer.toString(this.curToken) + "='" + ((char) this.curToken) + "'";
                    }
                }
            }
            return new String(this.tokenBuffer, 0, this.tokenBufferLength);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('\"');
        for (int i2 = 0; i2 < this.tokenBufferLength; i2++) {
            char c = this.tokenBuffer[i2];
            if (c == '\"') {
                stringBuffer.append('\"');
            }
            stringBuffer.append(c);
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    public void error(char c, String str, String str2) {
        SourceMessages messages = getMessages();
        SourceError sourceError = new SourceError(c, this.port.getName(), this.curLine, this.curColumn, str);
        sourceError.code = str2;
        messages.error(sourceError);
    }

    public void error(char c, String str) {
        error(c, str, (String) null);
    }

    public Expression declError(String str) throws IOException, SyntaxException {
        if (this.interactive) {
            return syntaxError(str);
        }
        error(str);
        while (true) {
            int i = this.curToken;
            if (i != 59 && i != -1) {
                getRawToken();
            }
        }
        return new ErrorExp(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        unread(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression syntaxError(java.lang.String r2, java.lang.String r3) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r1 = this;
            r0 = 101(0x65, float:1.42E-43)
            r1.error(r0, r2, r3)
            boolean r3 = r1.interactive
            if (r3 == 0) goto L_0x0035
            r2 = 0
            r1.curToken = r2
            r3 = 0
            r1.curValue = r3
            r1.nesting = r2
            gnu.text.LineBufferedReader r2 = r1.getPort()
            gnu.mapping.InPort r2 = (gnu.mapping.InPort) r2
            r3 = 10
            r2.readState = r3
        L_0x001b:
            int r2 = r1.read()
            if (r2 < 0) goto L_0x002b
            r0 = 13
            if (r2 == r0) goto L_0x0028
            if (r2 == r3) goto L_0x0028
            goto L_0x001b
        L_0x0028:
            r1.unread(r2)
        L_0x002b:
            gnu.text.SyntaxException r2 = new gnu.text.SyntaxException
            gnu.text.SourceMessages r3 = r1.getMessages()
            r2.<init>(r3)
            throw r2
        L_0x0035:
            gnu.expr.ErrorExp r3 = new gnu.expr.ErrorExp
            r3.<init>(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQParser.syntaxError(java.lang.String, java.lang.String):gnu.expr.Expression");
    }

    public Expression syntaxError(String str) throws IOException, SyntaxException {
        return syntaxError(str, "XPST0003");
    }

    public void eofError(String str) throws SyntaxException {
        fatal(str, "XPST0003");
    }

    public void fatal(String str, String str2) throws SyntaxException {
        SourceMessages messages = getMessages();
        SourceError sourceError = new SourceError('f', this.port.getName(), this.curLine, this.curColumn, str);
        sourceError.code = str2;
        messages.error(sourceError);
        throw new SyntaxException(messages);
    }

    /* access modifiers changed from: package-private */
    public void warnOldVersion(String str) {
        if (warnOldVersion || this.comp.isPedantic()) {
            error(this.comp.isPedantic() ? 'e' : 'w', str);
        }
    }

    public void maybeSetLine(Expression expression, int i, int i2) {
        String name = getName();
        if (name != null && expression.getFileName() == null && !(expression instanceof QuoteExp)) {
            expression.setFile(name);
            expression.setLine(i, i2);
        }
    }

    public void maybeSetLine(Declaration declaration, int i, int i2) {
        String name = getName();
        if (name != null) {
            declaration.setFile(name);
            declaration.setLine(i, i2);
        }
    }
}
