package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defvar extends Syntax {
    boolean force;

    public defvar(boolean z) {
        this.force = z;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        if (!(pair.getCdr() instanceof Pair)) {
            return super.scanForDefinitions(pair, vector, scopeExp, translator);
        }
        Pair pair2 = (Pair) pair.getCdr();
        Object car = pair2.getCar();
        if ((car instanceof String) || (car instanceof Symbol)) {
            Declaration lookup = scopeExp.lookup(car);
            if (lookup == null) {
                lookup = new Declaration(car);
                lookup.setFlag(268435456);
                scopeExp.addDeclaration(lookup);
            } else {
                translator.error('w', "duplicate declaration for `" + car + "'");
            }
            pair = Translator.makePair(pair, this, Translator.makePair(pair2, lookup, pair2.getCdr()));
            if (scopeExp instanceof ModuleExp) {
                lookup.setCanRead(true);
                lookup.setCanWrite(true);
            }
        }
        vector.addElement(pair);
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewriteForm(gnu.lists.Pair r5, kawa.lang.Translator r6) {
        /*
            r4 = this;
            java.lang.Object r5 = r5.getCdr()
            boolean r0 = r5 instanceof gnu.lists.Pair
            r1 = 0
            if (r0 == 0) goto L_0x0045
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            java.lang.Object r0 = r5.getCar()
            boolean r0 = r0 instanceof gnu.expr.Declaration
            if (r0 == 0) goto L_0x0045
            java.lang.Object r0 = r5.getCar()
            gnu.expr.Declaration r0 = (gnu.expr.Declaration) r0
            java.lang.Object r2 = r0.getSymbol()
            java.lang.Object r3 = r5.getCdr()
            boolean r3 = r3 instanceof gnu.lists.Pair
            if (r3 == 0) goto L_0x0039
            java.lang.Object r5 = r5.getCdr()
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            java.lang.Object r3 = r5.getCar()
            gnu.expr.Expression r3 = r6.rewrite(r3)
            r5.getCdr()
            gnu.lists.LList r5 = gnu.lists.LList.Empty
            goto L_0x0048
        L_0x0039:
            java.lang.Object r5 = r5.getCdr()
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            if (r5 == r3) goto L_0x0043
            r2 = r1
            goto L_0x0047
        L_0x0043:
            r3 = r1
            goto L_0x0048
        L_0x0045:
            r0 = r1
            r2 = r0
        L_0x0047:
            r3 = r2
        L_0x0048:
            if (r2 != 0) goto L_0x0064
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "invalid syntax for "
            r5.append(r0)
            java.lang.String r0 = r4.getName()
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            gnu.expr.Expression r5 = r6.syntaxError(r5)
            return r5
        L_0x0064:
            if (r3 != 0) goto L_0x0073
            boolean r5 = r4.force
            if (r5 == 0) goto L_0x006d
            gnu.expr.Expression r3 = gnu.commonlisp.lang.CommonLisp.nilExpr
            goto L_0x0073
        L_0x006d:
            gnu.expr.QuoteExp r5 = new gnu.expr.QuoteExp
            r5.<init>(r2)
            return r5
        L_0x0073:
            gnu.expr.SetExp r5 = new gnu.expr.SetExp
            r5.<init>((java.lang.Object) r2, (gnu.expr.Expression) r3)
            boolean r6 = r4.force
            r2 = 1
            if (r6 != 0) goto L_0x0080
            r5.setSetIfUnbound(r2)
        L_0x0080:
            r5.setDefining(r2)
            if (r0 == 0) goto L_0x0099
            r5.setBinding(r0)
            gnu.expr.ScopeExp r6 = r0.context
            boolean r6 = r6 instanceof gnu.expr.ModuleExp
            if (r6 == 0) goto L_0x0095
            boolean r6 = r0.getCanWrite()
            if (r6 == 0) goto L_0x0095
            goto L_0x0096
        L_0x0095:
            r1 = r3
        L_0x0096:
            r0.noteValue(r1)
        L_0x0099:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.commonlisp.lang.defvar.rewriteForm(gnu.lists.Pair, kawa.lang.Translator):gnu.expr.Expression");
    }
}
