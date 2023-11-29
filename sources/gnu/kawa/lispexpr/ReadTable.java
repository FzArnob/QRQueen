package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.RangeTable;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ThreadLocation;

public class ReadTable extends RangeTable {
    public static final int CONSTITUENT = 2;
    public static final int ILLEGAL = 0;
    public static final int MULTIPLE_ESCAPE = 4;
    public static final int NON_TERMINATING_MACRO = 6;
    public static final int SINGLE_ESCAPE = 3;
    public static final int TERMINATING_MACRO = 5;
    public static final int WHITESPACE = 1;
    static final ThreadLocation current = new ThreadLocation("read-table");
    public static int defaultBracketMode = -1;
    Environment ctorTable = null;
    protected boolean finalColonIsKeyword;
    protected boolean hexEscapeAfterBackslash = true;
    protected boolean initialColonIsKeyword;
    public char postfixLookupOperator = LispReader.TOKEN_ESCAPE_CHAR;

    public void setInitialColonIsKeyword(boolean z) {
        this.initialColonIsKeyword = z;
    }

    public void setFinalColonIsKeyword(boolean z) {
        this.finalColonIsKeyword = z;
    }

    public void initialize() {
        ReadTableEntry whitespaceInstance = ReadTableEntry.getWhitespaceInstance();
        set(32, whitespaceInstance);
        set(9, whitespaceInstance);
        set(10, whitespaceInstance);
        set(13, whitespaceInstance);
        set(12, whitespaceInstance);
        set(124, ReadTableEntry.getMultipleEscapeInstance());
        set(92, ReadTableEntry.getSingleEscapeInstance());
        set(48, 57, ReadTableEntry.getDigitInstance());
        ReadTableEntry constituentInstance = ReadTableEntry.getConstituentInstance();
        set(97, 122, constituentInstance);
        set(65, 90, constituentInstance);
        set(33, constituentInstance);
        set(36, constituentInstance);
        set(37, constituentInstance);
        set(38, constituentInstance);
        set(42, constituentInstance);
        set(43, constituentInstance);
        set(45, constituentInstance);
        set(46, constituentInstance);
        set(47, constituentInstance);
        set(61, constituentInstance);
        set(62, constituentInstance);
        set(63, constituentInstance);
        set(64, constituentInstance);
        set(94, constituentInstance);
        set(95, constituentInstance);
        set(123, ReadTableEntry.brace);
        set(126, constituentInstance);
        set(127, constituentInstance);
        set(8, constituentInstance);
        set(58, new ReaderColon());
        set(34, new ReaderString());
        set(35, ReaderDispatch.create(this));
        set(59, ReaderIgnoreRestOfLine.getInstance());
        set(40, ReaderParens.getInstance('(', ')'));
        set(39, new ReaderQuote(makeSymbol(LispLanguage.quote_sym)));
        set(96, new ReaderQuote(makeSymbol(LispLanguage.quasiquote_sym)));
        set(44, new ReaderQuote(makeSymbol(LispLanguage.unquote_sym), '@', makeSymbol(LispLanguage.unquotesplicing_sym)));
        setBracketMode();
    }

    public static ReadTable createInitial() {
        ReadTable readTable = new ReadTable();
        readTable.initialize();
        return readTable;
    }

    public void setBracketMode(int i) {
        if (i <= 0) {
            ReadTableEntry constituentInstance = ReadTableEntry.getConstituentInstance();
            set(60, constituentInstance);
            if (i < 0) {
                set(91, constituentInstance);
                set(93, constituentInstance);
            }
        } else {
            set(60, new ReaderTypespec());
        }
        if (i >= 0) {
            set(91, ReaderParens.getInstance('[', ']'));
            remove(93);
        }
    }

    public void setBracketMode() {
        setBracketMode(defaultBracketMode);
    }

    /* access modifiers changed from: package-private */
    public void initCtorTable() {
        if (this.ctorTable == null) {
            this.ctorTable = Environment.make();
        }
    }

    public synchronized void putReaderCtor(String str, Procedure procedure) {
        initCtorTable();
        this.ctorTable.put(str, (Object) procedure);
    }

    public synchronized void putReaderCtor(String str, Type type) {
        initCtorTable();
        this.ctorTable.put(str, (Object) type);
    }

    public synchronized void putReaderCtorFld(String str, String str2, String str3) {
        initCtorTable();
        StaticFieldLocation.define(this.ctorTable, this.ctorTable.getSymbol(str), (Object) null, str2, str3);
    }

    public synchronized Object getReaderCtor(String str) {
        initCtorTable();
        return this.ctorTable.get(str, (Object) null);
    }

    public static ReadTable getCurrent() {
        ThreadLocation threadLocation = current;
        ReadTable readTable = (ReadTable) threadLocation.get((Object) null);
        if (readTable == null) {
            Language defaultLanguage = Language.getDefaultLanguage();
            if (defaultLanguage instanceof LispLanguage) {
                readTable = ((LispLanguage) defaultLanguage).defaultReadTable;
            } else {
                readTable = createInitial();
            }
            threadLocation.set(readTable);
        }
        return readTable;
    }

    public static void setCurrent(ReadTable readTable) {
        current.set(readTable);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: gnu.kawa.lispexpr.ReadTableEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: gnu.kawa.lispexpr.ReadTableEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: gnu.kawa.lispexpr.ReadTableEntry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: gnu.kawa.lispexpr.ReadTableEntry} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.kawa.lispexpr.ReadTableEntry lookup(int r5) {
        /*
            r4 = this;
            r0 = 0
            java.lang.Object r1 = r4.lookup(r5, r0)
            gnu.kawa.lispexpr.ReadTableEntry r1 = (gnu.kawa.lispexpr.ReadTableEntry) r1
            if (r1 != 0) goto L_0x0060
            if (r5 < 0) goto L_0x0060
            r2 = 65536(0x10000, float:9.18355E-41)
            if (r5 >= r2) goto L_0x0060
            char r2 = (char) r5
            boolean r3 = java.lang.Character.isDigit(r2)
            if (r3 == 0) goto L_0x0020
            r1 = 48
            java.lang.Object r0 = r4.lookup(r1, r0)
            r1 = r0
            gnu.kawa.lispexpr.ReadTableEntry r1 = (gnu.kawa.lispexpr.ReadTableEntry) r1
            goto L_0x004f
        L_0x0020:
            boolean r3 = java.lang.Character.isLowerCase(r2)
            if (r3 == 0) goto L_0x0030
            r1 = 97
            java.lang.Object r0 = r4.lookup(r1, r0)
            r1 = r0
            gnu.kawa.lispexpr.ReadTableEntry r1 = (gnu.kawa.lispexpr.ReadTableEntry) r1
            goto L_0x004f
        L_0x0030:
            boolean r3 = java.lang.Character.isLetter(r2)
            if (r3 == 0) goto L_0x0040
            r1 = 65
            java.lang.Object r0 = r4.lookup(r1, r0)
            r1 = r0
            gnu.kawa.lispexpr.ReadTableEntry r1 = (gnu.kawa.lispexpr.ReadTableEntry) r1
            goto L_0x004f
        L_0x0040:
            boolean r2 = java.lang.Character.isWhitespace(r2)
            if (r2 == 0) goto L_0x004f
            r1 = 32
            java.lang.Object r0 = r4.lookup(r1, r0)
            r1 = r0
            gnu.kawa.lispexpr.ReadTableEntry r1 = (gnu.kawa.lispexpr.ReadTableEntry) r1
        L_0x004f:
            if (r1 != 0) goto L_0x005a
            r0 = 128(0x80, float:1.794E-43)
            if (r5 < r0) goto L_0x005a
            gnu.kawa.lispexpr.ReadTableEntry r5 = gnu.kawa.lispexpr.ReadTableEntry.getConstituentInstance()
            r1 = r5
        L_0x005a:
            if (r1 != 0) goto L_0x0060
            gnu.kawa.lispexpr.ReadTableEntry r1 = gnu.kawa.lispexpr.ReadTableEntry.getIllegalInstance()
        L_0x0060:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReadTable.lookup(int):gnu.kawa.lispexpr.ReadTableEntry");
    }

    /* access modifiers changed from: protected */
    public Object makeSymbol(String str) {
        return Namespace.EmptyNamespace.getSymbol(str.intern());
    }
}
