package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.IdentityHashMap;

public class Quote extends Syntax {
    private static final Object CYCLE = new String("(cycle)");
    protected static final int QUOTE_DEPTH = -1;
    private static final Object WORKING = new String("(working)");
    static final Method appendMethod;
    static final Method consXMethod;
    static final Method makePairMethod = Compilation.typePair.getDeclaredMethod("make", 2);
    static final Method makeVectorMethod = ClassType.make("gnu.lists.FVector").getDeclaredMethod("make", 1);
    public static final Quote plainQuote = new Quote(LispLanguage.quote_sym, false);
    public static final Quote quasiQuote = new Quote(LispLanguage.quasiquote_sym, true);
    static final ClassType quoteType;
    static final Method vectorAppendMethod = ClassType.make("kawa.standard.vector_append").getDeclaredMethod("apply$V", 1);
    protected boolean isQuasi;

    /* access modifiers changed from: protected */
    public boolean expandColonForms() {
        return true;
    }

    static {
        ClassType make = ClassType.make("kawa.lang.Quote");
        quoteType = make;
        consXMethod = make.getDeclaredMethod("consX$V", 1);
        appendMethod = make.getDeclaredMethod("append$V", 1);
    }

    public Quote(String str, boolean z) {
        super(str);
        this.isQuasi = z;
    }

    /* access modifiers changed from: protected */
    public Object expand(Object obj, int i, Translator translator) {
        return expand(obj, i, (SyntaxForm) null, new IdentityHashMap(), translator);
    }

    public static Object quote(Object obj, Translator translator) {
        return plainQuote.expand(obj, -1, translator);
    }

    public static Object quote(Object obj) {
        return plainQuote.expand(obj, -1, (Translator) Compilation.getCurrent());
    }

    /* access modifiers changed from: protected */
    public Expression coerceExpression(Object obj, Translator translator) {
        return obj instanceof Expression ? (Expression) obj : leaf(obj, translator);
    }

    /* access modifiers changed from: protected */
    public Expression leaf(Object obj, Translator translator) {
        return new QuoteExp(obj);
    }

    public static Symbol makeSymbol(Namespace namespace, Object obj) {
        String str;
        if (obj instanceof CharSequence) {
            str = ((CharSequence) obj).toString();
        } else {
            str = (String) obj;
        }
        return namespace.getSymbol(str.intern());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: gnu.lists.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: gnu.lists.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: gnu.lists.Pair} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0145, code lost:
        return r9.syntaxError("invalid used of " + r10.getCar() + " in quasiquote template");
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01a5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object expand_pair(gnu.lists.Pair r17, int r18, kawa.lang.SyntaxForm r19, java.lang.Object r20, kawa.lang.Translator r21) {
        /*
            r16 = this;
            r6 = r16
            r7 = r17
            r8 = r19
            r9 = r21
            r0 = r18
            r10 = r7
        L_0x000b:
            boolean r1 = r16.expandColonForms()
            r12 = 2
            r13 = 0
            r14 = 1
            if (r1 == 0) goto L_0x00e5
            if (r10 != r7) goto L_0x00e5
            java.lang.Object r1 = r10.getCar()
            gnu.mapping.Symbol r2 = gnu.kawa.lispexpr.LispLanguage.lookup_sym
            boolean r1 = r9.matches((java.lang.Object) r1, (kawa.lang.SyntaxForm) r8, (gnu.mapping.Symbol) r2)
            if (r1 == 0) goto L_0x00e5
            java.lang.Object r1 = r10.getCdr()
            boolean r1 = r1 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x00e5
            java.lang.Object r1 = r10.getCdr()
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            boolean r2 = r1 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x00e5
            java.lang.Object r2 = r1.getCdr()
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            boolean r3 = r2 instanceof gnu.lists.Pair
            if (r3 == 0) goto L_0x00e5
            java.lang.Object r3 = r2.getCdr()
            gnu.lists.LList r4 = gnu.lists.LList.Empty
            if (r3 != r4) goto L_0x00e5
            gnu.expr.Expression r3 = r9.rewrite_car((gnu.lists.Pair) r1, (boolean) r13)
            gnu.expr.Expression r2 = r9.rewrite_car((gnu.lists.Pair) r2, (boolean) r13)
            gnu.mapping.Namespace r4 = r9.namespaceResolvePrefix(r3)
            gnu.mapping.Symbol r5 = r9.namespaceResolve((gnu.mapping.Namespace) r4, (gnu.expr.Expression) r2)
            if (r5 == 0) goto L_0x005a
            goto L_0x0279
        L_0x005a:
            if (r4 == 0) goto L_0x0077
            if (r0 != r14) goto L_0x0077
            gnu.expr.ApplyExp r5 = new gnu.expr.ApplyExp
            gnu.bytecode.ClassType r0 = quoteType
            java.lang.String r1 = "makeSymbol"
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r1, (int) r12)
            gnu.expr.Expression[] r1 = new gnu.expr.Expression[r12]
            gnu.expr.QuoteExp r3 = gnu.expr.QuoteExp.getInstance(r4)
            r1[r13] = r3
            r1[r14] = r2
            r5.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r1)
            goto L_0x0279
        L_0x0077:
            boolean r0 = r3 instanceof gnu.expr.ReferenceExp
            if (r0 == 0) goto L_0x00ad
            boolean r0 = r2 instanceof gnu.expr.QuoteExp
            if (r0 == 0) goto L_0x00ad
            gnu.mapping.Environment r0 = r21.getGlobalEnvironment()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            gnu.expr.ReferenceExp r3 = (gnu.expr.ReferenceExp) r3
            java.lang.String r3 = r3.getName()
            r1.append(r3)
            r3 = 58
            r1.append(r3)
            gnu.expr.QuoteExp r2 = (gnu.expr.QuoteExp) r2
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            gnu.mapping.Symbol r5 = r0.getSymbol(r1)
            goto L_0x0279
        L_0x00ad:
            java.lang.String r0 = gnu.kawa.functions.CompileNamedPart.combineName(r3, r2)
            if (r0 == 0) goto L_0x00bd
            gnu.mapping.Environment r1 = r21.getGlobalEnvironment()
            gnu.mapping.Symbol r5 = r1.getSymbol(r0)
            goto L_0x0279
        L_0x00bd:
            java.lang.Object r0 = r9.pushPositionOf(r10)
            r2 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "'"
            r3.append(r4)
            java.lang.Object r1 = r1.getCar()
            r3.append(r1)
            java.lang.String r1 = "' is not a valid prefix"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r9.error(r2, r1)
            r9.popPositionOf(r0)
            goto L_0x0279
        L_0x00e5:
            java.lang.String r1 = "unquote-splicing"
            java.lang.String r2 = "unquote"
            if (r0 >= 0) goto L_0x00ed
            goto L_0x016b
        L_0x00ed:
            java.lang.Object r3 = r10.getCar()
            java.lang.String r4 = "quasiquote"
            boolean r3 = r9.matches((java.lang.Object) r3, (kawa.lang.SyntaxForm) r8, (java.lang.String) r4)
            if (r3 == 0) goto L_0x00fc
            int r0 = r0 + 1
            goto L_0x016b
        L_0x00fc:
            java.lang.Object r3 = r10.getCar()
            boolean r3 = r9.matches((java.lang.Object) r3, (kawa.lang.SyntaxForm) r8, (java.lang.String) r2)
            java.lang.String r4 = " in quasiquote template"
            java.lang.String r5 = "invalid used of "
            if (r3 == 0) goto L_0x0146
            int r0 = r0 + -1
            java.lang.Object r3 = r10.getCdr()
            boolean r3 = r3 instanceof gnu.lists.Pair
            if (r3 == 0) goto L_0x012b
            java.lang.Object r3 = r10.getCdr()
            gnu.lists.Pair r3 = (gnu.lists.Pair) r3
            java.lang.Object r15 = r3.getCdr()
            gnu.lists.LList r11 = gnu.lists.LList.Empty
            if (r15 == r11) goto L_0x0123
            goto L_0x012b
        L_0x0123:
            if (r0 != 0) goto L_0x016b
            gnu.expr.Expression r5 = r9.rewrite_car((gnu.lists.Pair) r3, (kawa.lang.SyntaxForm) r8)
            goto L_0x0279
        L_0x012b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.Object r1 = r10.getCar()
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            gnu.expr.Expression r0 = r9.syntaxError(r0)
            return r0
        L_0x0146:
            java.lang.Object r3 = r10.getCar()
            boolean r3 = r9.matches((java.lang.Object) r3, (kawa.lang.SyntaxForm) r8, (java.lang.String) r1)
            if (r3 == 0) goto L_0x016b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            java.lang.Object r1 = r10.getCar()
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            gnu.expr.Expression r0 = r9.syntaxError(r0)
            return r0
        L_0x016b:
            r11 = r0
            if (r11 != r14) goto L_0x0212
            java.lang.Object r0 = r10.getCar()
            boolean r0 = r0 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x0212
            java.lang.Object r0 = r10.getCar()
            r3 = r8
        L_0x017b:
            boolean r4 = r0 instanceof kawa.lang.SyntaxForm
            if (r4 == 0) goto L_0x0187
            r3 = r0
            kawa.lang.SyntaxForm r3 = (kawa.lang.SyntaxForm) r3
            java.lang.Object r0 = r3.getDatum()
            goto L_0x017b
        L_0x0187:
            boolean r4 = r0 instanceof gnu.lists.Pair
            if (r4 == 0) goto L_0x01a2
            r4 = r0
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            java.lang.Object r4 = r4.getCar()
            boolean r2 = r9.matches((java.lang.Object) r4, (kawa.lang.SyntaxForm) r3, (java.lang.String) r2)
            if (r2 == 0) goto L_0x019a
            r15 = 0
            goto L_0x01a3
        L_0x019a:
            boolean r1 = r9.matches((java.lang.Object) r4, (kawa.lang.SyntaxForm) r3, (java.lang.String) r1)
            if (r1 == 0) goto L_0x01a2
            r15 = 1
            goto L_0x01a3
        L_0x01a2:
            r15 = -1
        L_0x01a3:
            if (r15 < 0) goto L_0x0212
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r0 = r0.getCdr()
            java.util.Vector r11 = new java.util.Vector
            r11.<init>()
        L_0x01b0:
            boolean r1 = r0 instanceof kawa.lang.SyntaxForm
            if (r1 == 0) goto L_0x01bc
            kawa.lang.SyntaxForm r0 = (kawa.lang.SyntaxForm) r0
            java.lang.Object r1 = r0.getDatum()
            r3 = r0
            r0 = r1
        L_0x01bc:
            gnu.lists.LList r1 = gnu.lists.LList.Empty
            if (r0 != r1) goto L_0x01f9
            int r0 = r11.size()
            int r5 = r0 + 1
            java.lang.Object r1 = r10.getCdr()
            r2 = 1
            r0 = r16
            r3 = r19
            r4 = r20
            r8 = r5
            r5 = r21
            java.lang.Object r0 = r0.expand(r1, r2, r3, r4, r5)
            if (r8 <= r14) goto L_0x01f6
            gnu.expr.Expression[] r1 = new gnu.expr.Expression[r8]
            r11.copyInto(r1)
            int r5 = r8 + -1
            gnu.expr.Expression r0 = r6.coerceExpression(r0, r9)
            r1[r5] = r0
            if (r15 != 0) goto L_0x01ec
            gnu.bytecode.Method r0 = consXMethod
            goto L_0x01ee
        L_0x01ec:
            gnu.bytecode.Method r0 = appendMethod
        L_0x01ee:
            gnu.expr.ApplyExp r2 = new gnu.expr.ApplyExp
            r2.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r1)
            r5 = r2
            goto L_0x0279
        L_0x01f6:
            r5 = r0
            goto L_0x0279
        L_0x01f9:
            boolean r1 = r0 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x020b
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            gnu.expr.Expression r1 = r9.rewrite_car((gnu.lists.Pair) r0, (kawa.lang.SyntaxForm) r3)
            r11.addElement(r1)
            java.lang.Object r0 = r0.getCdr()
            goto L_0x01b0
        L_0x020b:
            java.lang.String r0 = "improper list argument to unquote"
            gnu.expr.Expression r0 = r9.syntaxError(r0)
            return r0
        L_0x0212:
            java.lang.Object r1 = r10.getCar()
            r0 = r16
            r2 = r11
            r3 = r19
            r4 = r20
            r5 = r21
            java.lang.Object r15 = r0.expand(r1, r2, r3, r4, r5)
            java.lang.Object r0 = r10.getCar()
            if (r15 != r0) goto L_0x0245
            java.lang.Object r10 = r10.getCdr()
            boolean r0 = r10 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x0236
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            r0 = r11
            goto L_0x000b
        L_0x0236:
            r0 = r16
            r1 = r10
            r2 = r11
            r3 = r19
            r4 = r20
            r5 = r21
            java.lang.Object r5 = r0.expand(r1, r2, r3, r4, r5)
            goto L_0x0279
        L_0x0245:
            java.lang.Object r1 = r10.getCdr()
            r0 = r16
            r2 = r11
            r3 = r19
            r4 = r20
            r5 = r21
            java.lang.Object r0 = r0.expand(r1, r2, r3, r4, r5)
            boolean r1 = r15 instanceof gnu.expr.Expression
            if (r1 != 0) goto L_0x0264
            boolean r1 = r0 instanceof gnu.expr.Expression
            if (r1 == 0) goto L_0x025f
            goto L_0x0264
        L_0x025f:
            gnu.lists.Pair r5 = kawa.lang.Translator.makePair(r10, r15, r0)
            goto L_0x0279
        L_0x0264:
            gnu.expr.Expression[] r1 = new gnu.expr.Expression[r12]
            gnu.expr.Expression r2 = r6.coerceExpression(r15, r9)
            r1[r13] = r2
            gnu.expr.Expression r0 = r6.coerceExpression(r0, r9)
            r1[r14] = r0
            gnu.expr.ApplyExp r5 = new gnu.expr.ApplyExp
            gnu.bytecode.Method r0 = makePairMethod
            r5.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r1)
        L_0x0279:
            if (r7 != r10) goto L_0x027c
            return r5
        L_0x027c:
            r0 = 20
            gnu.lists.Pair[] r0 = new gnu.lists.Pair[r0]
            r2 = r7
            r1 = 0
        L_0x0282:
            int r3 = r0.length
            if (r1 < r3) goto L_0x028d
            int r3 = r1 * 2
            gnu.lists.Pair[] r3 = new gnu.lists.Pair[r3]
            java.lang.System.arraycopy(r0, r13, r3, r13, r1)
            r0 = r3
        L_0x028d:
            int r3 = r1 + 1
            r0[r1] = r2
            java.lang.Object r1 = r2.getCdr()
            if (r1 != r10) goto L_0x02d9
            boolean r1 = r5 instanceof gnu.expr.Expression
            if (r1 == 0) goto L_0x029e
            gnu.lists.LList r2 = gnu.lists.LList.Empty
            goto L_0x029f
        L_0x029e:
            r2 = r5
        L_0x029f:
            r4 = -1
        L_0x02a0:
            int r3 = r3 + r4
            if (r3 < 0) goto L_0x02ae
            r8 = r0[r3]
            java.lang.Object r10 = r8.getCar()
            gnu.lists.Pair r2 = kawa.lang.Translator.makePair(r8, r10, r2)
            goto L_0x02a0
        L_0x02ae:
            if (r1 == 0) goto L_0x02d8
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r12]
            gnu.expr.Expression r5 = (gnu.expr.Expression) r5
            r0[r14] = r5
            if (r3 != r14) goto L_0x02ca
            java.lang.Object r1 = r17.getCar()
            gnu.expr.Expression r1 = r6.leaf(r1, r9)
            r0[r13] = r1
            gnu.expr.ApplyExp r1 = new gnu.expr.ApplyExp
            gnu.bytecode.Method r2 = makePairMethod
            r1.<init>((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0)
            return r1
        L_0x02ca:
            gnu.expr.Expression r1 = r6.leaf(r2, r9)
            r0[r13] = r1
            gnu.expr.ApplyExp r1 = new gnu.expr.ApplyExp
            gnu.bytecode.Method r2 = appendMethod
            r1.<init>((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0)
            return r1
        L_0x02d8:
            return r2
        L_0x02d9:
            r4 = -1
            java.lang.Object r1 = r2.getCdr()
            r2 = r1
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            r1 = r3
            goto L_0x0282
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Quote.expand_pair(gnu.lists.Pair, int, kawa.lang.SyntaxForm, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00fb A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object expand(java.lang.Object r20, int r21, kawa.lang.SyntaxForm r22, java.lang.Object r23, kawa.lang.Translator r24) {
        /*
            r19 = this;
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r24
            r11 = r23
            java.util.IdentityHashMap r11 = (java.util.IdentityHashMap) r11
            java.lang.Object r0 = r11.get(r7)
            java.lang.Object r1 = WORKING
            if (r0 != r1) goto L_0x001c
            java.lang.Object r1 = CYCLE
            r11.put(r7, r1)
            return r0
        L_0x001c:
            java.lang.Object r1 = CYCLE
            if (r0 != r1) goto L_0x0021
            return r0
        L_0x0021:
            if (r0 == 0) goto L_0x0024
            return r0
        L_0x0024:
            boolean r0 = r7 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x003b
            r1 = r7
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            r0 = r19
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r24
            java.lang.Object r0 = r0.expand_pair(r1, r2, r3, r4, r5)
            goto L_0x0168
        L_0x003b:
            boolean r0 = r7 instanceof kawa.lang.SyntaxForm
            if (r0 == 0) goto L_0x0054
            r3 = r7
            kawa.lang.SyntaxForm r3 = (kawa.lang.SyntaxForm) r3
            java.lang.Object r1 = r3.getDatum()
            r0 = r19
            r2 = r21
            r4 = r23
            r5 = r24
            java.lang.Object r0 = r0.expand(r1, r2, r3, r4, r5)
            goto L_0x0168
        L_0x0054:
            boolean r0 = r7 instanceof gnu.lists.FVector
            if (r0 == 0) goto L_0x0167
            r12 = r7
            gnu.lists.FVector r12 = (gnu.lists.FVector) r12
            int r13 = r12.size()
            java.lang.Object[] r14 = new java.lang.Object[r13]
            byte[] r15 = new byte[r13]
            r16 = 0
            r4 = 0
            r5 = 0
        L_0x0067:
            if (r5 >= r13) goto L_0x0103
            java.lang.Object r1 = r12.get(r5)
            boolean r2 = r1 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x00cd
            r2 = -1
            if (r8 <= r2) goto L_0x00cd
            r2 = r1
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            java.lang.Object r3 = r2.getCar()
            java.lang.String r0 = "unquote-splicing"
            boolean r0 = r10.matches((java.lang.Object) r3, (kawa.lang.SyntaxForm) r9, (java.lang.String) r0)
            if (r0 == 0) goto L_0x00cd
            int r0 = r8 + -1
            if (r0 != 0) goto L_0x00cb
            java.lang.Object r0 = r2.getCdr()
            boolean r0 = r0 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x00ac
            java.lang.Object r0 = r2.getCdr()
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r1 = r0.getCdr()
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            if (r1 == r3) goto L_0x009e
            goto L_0x00ac
        L_0x009e:
            gnu.expr.Expression r0 = r10.rewrite_car((gnu.lists.Pair) r0, (kawa.lang.SyntaxForm) r9)
            r14[r5] = r0
            r0 = 3
            r15[r5] = r0
            r18 = r4
            r17 = r5
            goto L_0x00f3
        L_0x00ac:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "invalid used of "
            r0.append(r1)
            java.lang.Object r1 = r2.getCar()
            r0.append(r1)
            java.lang.String r1 = " in quasiquote template"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            gnu.expr.Expression r0 = r10.syntaxError(r0)
            return r0
        L_0x00cb:
            r2 = r0
            goto L_0x00ce
        L_0x00cd:
            r2 = r8
        L_0x00ce:
            r0 = r19
            r3 = r1
            r8 = 1
            r8 = r3
            r9 = 2
            r3 = r22
            r18 = r4
            r4 = r23
            r17 = r5
            r5 = r24
            java.lang.Object r0 = r0.expand(r1, r2, r3, r4, r5)
            r14[r17] = r0
            if (r0 != r8) goto L_0x00e9
            r15[r17] = r16
            goto L_0x00f3
        L_0x00e9:
            boolean r0 = r0 instanceof gnu.expr.Expression
            if (r0 == 0) goto L_0x00f0
            r15[r17] = r9
            goto L_0x00f3
        L_0x00f0:
            r0 = 1
            r15[r17] = r0
        L_0x00f3:
            byte r4 = r15[r17]
            r1 = r18
            if (r4 <= r1) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r4 = r1
        L_0x00fb:
            int r5 = r17 + 1
            r8 = r21
            r9 = r22
            goto L_0x0067
        L_0x0103:
            r1 = r4
            r0 = 1
            r9 = 2
            if (r1 != 0) goto L_0x010a
            r0 = r12
            goto L_0x0168
        L_0x010a:
            if (r1 != r0) goto L_0x0112
            gnu.lists.FVector r0 = new gnu.lists.FVector
            r0.<init>((java.lang.Object[]) r14)
            goto L_0x0168
        L_0x0112:
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r13]
            r2 = 0
        L_0x0115:
            if (r2 >= r13) goto L_0x0156
            byte r3 = r15[r2]
            r4 = 3
            if (r3 != r4) goto L_0x0124
            r3 = r14[r2]
            gnu.expr.Expression r3 = (gnu.expr.Expression) r3
            r0[r2] = r3
        L_0x0122:
            r3 = 1
            goto L_0x0153
        L_0x0124:
            if (r1 >= r4) goto L_0x012f
            r3 = r14[r2]
            gnu.expr.Expression r3 = r6.coerceExpression(r3, r10)
            r0[r2] = r3
            goto L_0x0122
        L_0x012f:
            if (r3 >= r9) goto L_0x0144
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r5 = r14[r2]
            r4[r16] = r5
            gnu.lists.FVector r5 = new gnu.lists.FVector
            r5.<init>((java.lang.Object[]) r4)
            gnu.expr.Expression r4 = r6.leaf(r5, r10)
            r0[r2] = r4
            goto L_0x0153
        L_0x0144:
            r3 = 1
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r3]
            r5 = r14[r2]
            gnu.expr.Expression r5 = (gnu.expr.Expression) r5
            r4[r16] = r5
            gnu.expr.ApplyExp r4 = makeInvokeMakeVector(r4)
            r0[r2] = r4
        L_0x0153:
            int r2 = r2 + 1
            goto L_0x0115
        L_0x0156:
            r2 = 3
            if (r1 >= r2) goto L_0x015e
            gnu.expr.ApplyExp r0 = makeInvokeMakeVector(r0)
            goto L_0x0168
        L_0x015e:
            gnu.expr.ApplyExp r1 = new gnu.expr.ApplyExp
            gnu.bytecode.Method r2 = vectorAppendMethod
            r1.<init>((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0)
            r0 = r1
            goto L_0x0168
        L_0x0167:
            r0 = r7
        L_0x0168:
            if (r7 == r0) goto L_0x0179
            java.lang.Object r1 = r11.get(r7)
            java.lang.Object r2 = CYCLE
            if (r1 != r2) goto L_0x0179
            r1 = 101(0x65, float:1.42E-43)
            java.lang.String r2 = "cycle in non-literal data"
            r10.error(r1, r2)
        L_0x0179:
            r11.put(r7, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Quote.expand(java.lang.Object, int, kawa.lang.SyntaxForm, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    private static ApplyExp makeInvokeMakeVector(Expression[] expressionArr) {
        return new ApplyExp(makeVectorMethod, expressionArr);
    }

    public Expression rewrite(Object obj, Translator translator) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (pair.getCdr() == LList.Empty) {
                return coerceExpression(expand(pair.getCar(), this.isQuasi ? 1 : -1, translator), translator);
            }
        }
        return translator.syntaxError("wrong number of arguments to quote");
    }

    public static Object consX$V(Object[] objArr) {
        return LList.consX(objArr);
    }

    public static Object append$V(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return LList.Empty;
        }
        int i = length - 1;
        Pair pair = objArr[i];
        while (true) {
            i--;
            if (i < 0) {
                return pair;
            }
            Pair pair2 = objArr[i];
            SyntaxForm syntaxForm = null;
            Pair pair3 = null;
            Pair pair4 = null;
            while (true) {
                if (pair2 instanceof SyntaxForm) {
                    syntaxForm = (SyntaxForm) pair2;
                    pair2 = syntaxForm.getDatum();
                } else if (pair2 == LList.Empty) {
                    break;
                } else {
                    Pair pair5 = pair2;
                    Object car = pair5.getCar();
                    if (syntaxForm != null && !(car instanceof SyntaxForm)) {
                        car = SyntaxForms.makeForm(car, syntaxForm.getScope());
                    }
                    Pair pair6 = new Pair(car, (Object) null);
                    if (pair3 == null) {
                        pair4 = pair6;
                    } else {
                        pair3.setCdr(pair6);
                    }
                    pair2 = pair5.getCdr();
                    pair3 = pair6;
                }
            }
            if (pair3 != null) {
                pair3.setCdr(pair);
                pair = pair4;
            }
        }
    }
}
