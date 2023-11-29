package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class fluid_let extends Syntax {
    public static final fluid_let fluid_let;
    Expression defaultInit;
    boolean star;

    static {
        fluid_let fluid_let2 = new fluid_let();
        fluid_let = fluid_let2;
        fluid_let2.setName("fluid-set");
    }

    public fluid_let(boolean z, Expression expression) {
        this.star = z;
        this.defaultInit = expression;
    }

    public fluid_let() {
        this.star = false;
    }

    public Expression rewrite(Object obj, Translator translator) {
        if (!(obj instanceof Pair)) {
            return translator.syntaxError("missing let arguments");
        }
        Pair pair = (Pair) obj;
        return rewrite(pair.getCar(), pair.getCdr(), translator);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d9 A[Catch:{ all -> 0x0105 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ef A[Catch:{ all -> 0x0105 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewrite(java.lang.Object r13, java.lang.Object r14, kawa.lang.Translator r15) {
        /*
            r12 = this;
            boolean r0 = r12.star
            r1 = 1
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x000b
        L_0x0007:
            int r0 = gnu.lists.LList.length(r13)
        L_0x000b:
            gnu.expr.Expression[] r2 = new gnu.expr.Expression[r0]
            gnu.expr.FluidLetExp r3 = new gnu.expr.FluidLetExp
            r3.<init>(r2)
            r4 = 0
            r5 = 0
        L_0x0014:
            if (r5 >= r0) goto L_0x010a
            gnu.lists.Pair r13 = (gnu.lists.Pair) r13
            java.lang.Object r6 = r15.pushPositionOf(r13)
            java.lang.Object r7 = r13.getCar()     // Catch:{ all -> 0x0105 }
            boolean r8 = r7 instanceof java.lang.String     // Catch:{ all -> 0x0105 }
            if (r8 != 0) goto L_0x00cb
            boolean r8 = r7 instanceof gnu.mapping.Symbol     // Catch:{ all -> 0x0105 }
            if (r8 == 0) goto L_0x002a
            goto L_0x00cb
        L_0x002a:
            boolean r8 = r7 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0105 }
            if (r8 == 0) goto L_0x00a9
            gnu.lists.Pair r7 = (gnu.lists.Pair) r7     // Catch:{ all -> 0x0105 }
            java.lang.Object r8 = r7.getCar()     // Catch:{ all -> 0x0105 }
            boolean r8 = r8 instanceof java.lang.String     // Catch:{ all -> 0x0105 }
            if (r8 != 0) goto L_0x0048
            java.lang.Object r8 = r7.getCar()     // Catch:{ all -> 0x0105 }
            boolean r8 = r8 instanceof gnu.mapping.Symbol     // Catch:{ all -> 0x0105 }
            if (r8 != 0) goto L_0x0048
            java.lang.Object r8 = r7.getCar()     // Catch:{ all -> 0x0105 }
            boolean r8 = r8 instanceof kawa.lang.SyntaxForm     // Catch:{ all -> 0x0105 }
            if (r8 == 0) goto L_0x00a9
        L_0x0048:
            java.lang.Object r8 = r7.getCar()     // Catch:{ all -> 0x0105 }
            boolean r9 = r8 instanceof kawa.lang.SyntaxForm     // Catch:{ all -> 0x0105 }
            if (r9 == 0) goto L_0x0056
            kawa.lang.SyntaxForm r8 = (kawa.lang.SyntaxForm) r8     // Catch:{ all -> 0x0105 }
            java.lang.Object r8 = r8.getDatum()     // Catch:{ all -> 0x0105 }
        L_0x0056:
            java.lang.Object r9 = r7.getCdr()     // Catch:{ all -> 0x0105 }
            gnu.lists.LList r10 = gnu.lists.LList.Empty     // Catch:{ all -> 0x0105 }
            if (r9 != r10) goto L_0x0064
            gnu.expr.Expression r7 = r12.defaultInit     // Catch:{ all -> 0x0105 }
        L_0x0060:
            r11 = r8
            r8 = r7
            r7 = r11
            goto L_0x00cd
        L_0x0064:
            java.lang.Object r9 = r7.getCdr()     // Catch:{ all -> 0x0105 }
            boolean r9 = r9 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0105 }
            if (r9 == 0) goto L_0x0084
            java.lang.Object r7 = r7.getCdr()     // Catch:{ all -> 0x0105 }
            gnu.lists.Pair r7 = (gnu.lists.Pair) r7     // Catch:{ all -> 0x0105 }
            java.lang.Object r9 = r7.getCdr()     // Catch:{ all -> 0x0105 }
            gnu.lists.LList r10 = gnu.lists.LList.Empty     // Catch:{ all -> 0x0105 }
            if (r9 == r10) goto L_0x007b
            goto L_0x0084
        L_0x007b:
            java.lang.Object r7 = r7.getCar()     // Catch:{ all -> 0x0105 }
            gnu.expr.Expression r7 = r15.rewrite(r7)     // Catch:{ all -> 0x0105 }
            goto L_0x0060
        L_0x0084:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r13.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = "bad syntax for value of "
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            r13.append(r8)     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = " in "
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = r12.getName()     // Catch:{ all -> 0x0105 }
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0105 }
            gnu.expr.Expression r13 = r15.syntaxError(r13)     // Catch:{ all -> 0x0105 }
            r15.popPositionOf(r6)
            return r13
        L_0x00a9:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r13.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = "invalid "
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = r12.getName()     // Catch:{ all -> 0x0105 }
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            java.lang.String r14 = " syntax"
            r13.append(r14)     // Catch:{ all -> 0x0105 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0105 }
            gnu.expr.Expression r13 = r15.syntaxError(r13)     // Catch:{ all -> 0x0105 }
            r15.popPositionOf(r6)
            return r13
        L_0x00cb:
            gnu.expr.Expression r8 = r12.defaultInit     // Catch:{ all -> 0x0105 }
        L_0x00cd:
            gnu.expr.Declaration r9 = r3.addDeclaration((java.lang.Object) r7)     // Catch:{ all -> 0x0105 }
            gnu.expr.NameLookup r10 = r15.lexical     // Catch:{ all -> 0x0105 }
            gnu.expr.Declaration r10 = r10.lookup((java.lang.Object) r7, (boolean) r4)     // Catch:{ all -> 0x0105 }
            if (r10 == 0) goto L_0x00e4
            r10.maybeIndirectBinding(r15)     // Catch:{ all -> 0x0105 }
            r9.base = r10     // Catch:{ all -> 0x0105 }
            r10.setFluid(r1)     // Catch:{ all -> 0x0105 }
            r10.setCanWrite(r1)     // Catch:{ all -> 0x0105 }
        L_0x00e4:
            r9.setCanWrite(r1)     // Catch:{ all -> 0x0105 }
            r9.setFluid(r1)     // Catch:{ all -> 0x0105 }
            r9.setIndirectBinding(r1)     // Catch:{ all -> 0x0105 }
            if (r8 != 0) goto L_0x00f4
            gnu.expr.ReferenceExp r8 = new gnu.expr.ReferenceExp     // Catch:{ all -> 0x0105 }
            r8.<init>((java.lang.Object) r7)     // Catch:{ all -> 0x0105 }
        L_0x00f4:
            r2[r5] = r8     // Catch:{ all -> 0x0105 }
            r7 = 0
            r9.noteValue(r7)     // Catch:{ all -> 0x0105 }
            java.lang.Object r13 = r13.getCdr()     // Catch:{ all -> 0x0105 }
            r15.popPositionOf(r6)
            int r5 = r5 + 1
            goto L_0x0014
        L_0x0105:
            r13 = move-exception
            r15.popPositionOf(r6)
            throw r13
        L_0x010a:
            r15.push((gnu.expr.ScopeExp) r3)
            boolean r0 = r12.star
            if (r0 == 0) goto L_0x011c
            gnu.lists.LList r0 = gnu.lists.LList.Empty
            if (r13 == r0) goto L_0x011c
            gnu.expr.Expression r13 = r12.rewrite(r13, r14, r15)
            r3.body = r13
            goto L_0x0122
        L_0x011c:
            gnu.expr.Expression r13 = r15.rewrite_body(r14)
            r3.body = r13
        L_0x0122:
            r15.pop(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.fluid_let.rewrite(java.lang.Object, java.lang.Object, kawa.lang.Translator):gnu.expr.Expression");
    }
}
