package kawa.standard;

import gnu.expr.CatchClause;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.TryExp;
import gnu.lists.FVector;
import kawa.lang.Translator;

public class try_catch {
    public static Expression rewrite(Object obj, Object obj2) {
        Translator translator = (Translator) Compilation.getCurrent();
        Expression rewrite = translator.rewrite(obj);
        FVector fVector = (FVector) obj2;
        int size = fVector.size();
        int i = 0;
        CatchClause catchClause = null;
        CatchClause catchClause2 = null;
        while (i < size) {
            Expression rewrite2 = SchemeCompilation.lambda.rewrite(fVector.get(i), translator);
            if (rewrite2 instanceof ErrorExp) {
                return rewrite2;
            }
            if (!(rewrite2 instanceof LambdaExp)) {
                return translator.syntaxError("internal error with try-catch");
            }
            CatchClause catchClause3 = new CatchClause((LambdaExp) rewrite2);
            if (catchClause2 == null) {
                catchClause = catchClause3;
            } else {
                catchClause2.setNext(catchClause3);
            }
            i++;
            catchClause2 = catchClause3;
        }
        if (rewrite instanceof ErrorExp) {
            return rewrite;
        }
        TryExp tryExp = new TryExp(rewrite, (Expression) null);
        tryExp.setCatchClauses(catchClause);
        return tryExp;
    }
}
