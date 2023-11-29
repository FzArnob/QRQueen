package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class defun extends Syntax {
    Lambda lambdaSyntax;

    public defun(Lambda lambda) {
        this.lambdaSyntax = lambda;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        if (pair.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) pair.getCdr();
            if ((pair2.getCar() instanceof String) || (pair2.getCar() instanceof Symbol)) {
                Object car = pair2.getCar();
                Declaration lookup = scopeExp.lookup(car);
                if (lookup == null) {
                    lookup = new Declaration(car);
                    lookup.setProcedureDecl(true);
                    scopeExp.addDeclaration(lookup);
                } else {
                    translator.error('w', "duplicate declaration for `" + car + "'");
                }
                if (scopeExp instanceof ModuleExp) {
                    lookup.setCanRead(true);
                }
                vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair2, lookup, pair2.getCdr())));
                return true;
            }
        }
        return super.scanForDefinitions(pair, vector, scopeExp, translator);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Object obj;
        Declaration declaration;
        Object cdr = pair.getCdr();
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            Object car = pair2.getCar();
            LambdaExp lambdaExp = null;
            if ((car instanceof Symbol) || (car instanceof String)) {
                obj = car.toString();
                declaration = null;
            } else if (car instanceof Declaration) {
                declaration = (Declaration) car;
                obj = declaration.getSymbol();
            } else {
                declaration = null;
                obj = null;
            }
            if (obj != null && (pair2.getCdr() instanceof Pair)) {
                Pair pair3 = (Pair) pair2.getCdr();
                LambdaExp lambdaExp2 = new LambdaExp();
                this.lambdaSyntax.rewrite(lambdaExp2, pair3.getCar(), pair3.getCdr(), translator, (TemplateScope) null);
                lambdaExp2.setSymbol(obj);
                if (pair3 instanceof PairWithPosition) {
                    lambdaExp2.setLocation((PairWithPosition) pair3);
                }
                SetExp setExp = new SetExp(obj, (Expression) lambdaExp2);
                setExp.setDefining(true);
                setExp.setFuncDef(true);
                if (declaration != null) {
                    setExp.setBinding(declaration);
                    if (!(declaration.context instanceof ModuleExp) || !declaration.getCanWrite()) {
                        lambdaExp = lambdaExp2;
                    }
                    declaration.noteValue(lambdaExp);
                }
                return setExp;
            }
        }
        return translator.syntaxError("invalid syntax for " + getName());
    }
}
