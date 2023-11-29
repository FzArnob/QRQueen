package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class setq extends Syntax {
    public Expression rewriteForm(Pair pair, Translator translator) {
        Object cdr = pair.getCdr();
        Vector vector = null;
        while (cdr != LList.Empty) {
            if (!(cdr instanceof Pair)) {
                return translator.syntaxError("invalid syntax for setq");
            }
            Pair pair2 = (Pair) cdr;
            Object car = pair2.getCar();
            if (!(car instanceof Symbol) && !(car instanceof String)) {
                if (car != CommonLisp.FALSE) {
                    return translator.syntaxError("invalid variable name in setq");
                }
                car = "nil";
            }
            Object cdr2 = pair2.getCdr();
            if (!(cdr2 instanceof Pair)) {
                return translator.syntaxError("wrong number of arguments for setq");
            }
            Pair pair3 = (Pair) cdr2;
            Expression rewrite = translator.rewrite(pair3.getCar());
            cdr = pair3.getCdr();
            SetExp setExp = new SetExp(car, rewrite);
            setExp.setFlag(8);
            if (cdr == LList.Empty) {
                setExp.setHasValue(true);
                if (vector == null) {
                    return setExp;
                }
            }
            if (vector == null) {
                vector = new Vector(10);
            }
            vector.addElement(setExp);
        }
        if (vector == null) {
            return CommonLisp.nilExpr;
        }
        Expression[] expressionArr = new Expression[vector.size()];
        vector.copyInto(expressionArr);
        return new BeginExp(expressionArr);
    }
}
