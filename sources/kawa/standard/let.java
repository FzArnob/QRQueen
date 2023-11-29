package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class let extends Syntax {
    public static final let let;

    static {
        let let2 = new let();
        let = let2;
        let2.setName("let");
    }

    public Expression rewrite(Object obj, Translator translator) {
        SyntaxForm syntaxForm;
        TemplateScope templateScope;
        int i;
        Pair pair;
        SyntaxForm syntaxForm2;
        Object obj2 = obj;
        Translator translator2 = translator;
        if (!(obj2 instanceof Pair)) {
            return translator2.syntaxError("missing let arguments");
        }
        Pair pair2 = (Pair) obj2;
        Object car = pair2.getCar();
        Object cdr = pair2.getCdr();
        int listLength = Translator.listLength(car);
        if (listLength < 0) {
            return translator2.syntaxError("bindings not a proper list");
        }
        Expression[] expressionArr = new Expression[listLength];
        LetExp letExp = new LetExp(expressionArr);
        int i2 = 0;
        int i3 = 0;
        SyntaxForm syntaxForm3 = null;
        Stack stack = null;
        while (i2 < listLength) {
            while (car instanceof SyntaxForm) {
                syntaxForm3 = (SyntaxForm) car;
                car = syntaxForm3.getDatum();
            }
            Pair pair3 = (Pair) car;
            Object car2 = pair3.getCar();
            if (car2 instanceof SyntaxForm) {
                SyntaxForm syntaxForm4 = (SyntaxForm) car2;
                syntaxForm = syntaxForm4;
                car2 = syntaxForm4.getDatum();
            } else {
                syntaxForm = syntaxForm3;
            }
            if (!(car2 instanceof Pair)) {
                return translator2.syntaxError("let binding is not a pair:" + car2);
            }
            Pair pair4 = (Pair) car2;
            Object car3 = pair4.getCar();
            if (car3 instanceof SyntaxForm) {
                SyntaxForm syntaxForm5 = (SyntaxForm) car3;
                Object datum = syntaxForm5.getDatum();
                templateScope = syntaxForm5.getScope();
                car3 = datum;
            } else {
                templateScope = syntaxForm == null ? null : syntaxForm.getScope();
            }
            Object namespaceResolve = translator2.namespaceResolve(car3);
            if (!(namespaceResolve instanceof Symbol)) {
                return translator2.syntaxError("variable " + namespaceResolve + " in let binding is not a symbol: " + obj2);
            }
            Declaration addDeclaration = letExp.addDeclaration(namespaceResolve);
            Object obj3 = cdr;
            Pair pair5 = pair3;
            addDeclaration.setFlag(262144);
            if (templateScope != null) {
                Declaration makeRenamedAlias = translator2.makeRenamedAlias(addDeclaration, templateScope);
                if (stack == null) {
                    stack = new Stack();
                }
                stack.push(makeRenamedAlias);
                i3++;
            }
            Object obj4 = pair4.getCdr();
            while (obj4 instanceof SyntaxForm) {
                syntaxForm = (SyntaxForm) obj4;
                obj4 = syntaxForm.getDatum();
            }
            if (!(obj4 instanceof Pair)) {
                return translator2.syntaxError("let has no value for '" + namespaceResolve + "'");
            }
            Pair pair6 = (Pair) obj4;
            Object obj5 = pair6.getCdr();
            while (obj5 instanceof SyntaxForm) {
                syntaxForm = (SyntaxForm) obj5;
                obj5 = syntaxForm.getDatum();
            }
            Object obj6 = obj5;
            if (translator2.matches(pair6.getCar(), "::")) {
                if (obj5 instanceof Pair) {
                    pair6 = (Pair) obj5;
                    if (pair6.getCdr() != LList.Empty) {
                        Object obj7 = pair6.getCdr();
                        while (obj7 instanceof SyntaxForm) {
                            syntaxForm = (SyntaxForm) obj7;
                            obj7 = syntaxForm.getDatum();
                        }
                        obj6 = obj7;
                    }
                }
                return translator2.syntaxError("missing type after '::' in let");
            }
            if (obj6 == LList.Empty) {
                pair = pair6;
                i = i3;
                syntaxForm2 = syntaxForm3;
            } else if (obj6 instanceof Pair) {
                addDeclaration.setType(translator2.exp2Type(pair6));
                i = i3;
                syntaxForm2 = syntaxForm3;
                addDeclaration.setFlag(8192);
                pair = (Pair) obj6;
            } else {
                return translator2.syntaxError("let binding for '" + namespaceResolve + "' is improper list");
            }
            expressionArr[i2] = translator2.rewrite_car(pair, syntaxForm);
            if (pair.getCdr() != LList.Empty) {
                return translator2.syntaxError("junk after declaration of " + namespaceResolve);
            }
            addDeclaration.noteValue(expressionArr[i2]);
            car = pair5.getCdr();
            i2++;
            syntaxForm3 = syntaxForm2;
            i3 = i;
            cdr = obj3;
        }
        Object obj8 = cdr;
        int i4 = i3;
        while (true) {
            i4--;
            if (i4 >= 0) {
                translator2.pushRenamedAlias((Declaration) stack.pop());
            } else {
                translator2.push((ScopeExp) letExp);
                letExp.body = translator2.rewrite_body(obj8);
                translator2.pop(letExp);
                translator2.popRenamedAlias(i3);
                return letExp;
            }
        }
    }
}
