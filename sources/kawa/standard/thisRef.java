package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class thisRef extends Syntax {
    public static final thisRef thisSyntax;

    static {
        thisRef thisref = new thisRef();
        thisSyntax = thisref;
        thisref.setName("this");
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Declaration declaration;
        if (pair.getCdr() != LList.Empty) {
            return translator.syntaxError("this with parameter not implemented");
        }
        LambdaExp lambdaExp = translator.curMethodLambda;
        Declaration declaration2 = null;
        if (lambdaExp == null) {
            declaration = null;
        } else {
            declaration = lambdaExp.firstDecl();
        }
        if (declaration != null && declaration.isThisParameter()) {
            declaration2 = declaration;
        } else if (lambdaExp == null || lambdaExp.nameDecl == null) {
            translator.error('e', "use of 'this' not in a named method");
        } else if (lambdaExp.nameDecl.isStatic()) {
            translator.error('e', "use of 'this' in a static method");
        } else {
            Declaration declaration3 = new Declaration((Object) ThisExp.THIS_NAME);
            lambdaExp.add((Declaration) null, declaration3);
            lambdaExp.nameDecl.setFlag(4096);
            declaration2 = declaration3;
        }
        return new ThisExp(declaration2);
    }
}
