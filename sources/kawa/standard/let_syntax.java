package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class let_syntax extends Syntax {
    public static final let_syntax let_syntax = new let_syntax(false, "let-syntax");
    public static final let_syntax letrec_syntax = new let_syntax(true, "letrec-syntax");
    boolean recursive;

    public let_syntax(boolean z, String str) {
        super(str);
        this.recursive = z;
    }

    public Expression rewrite(Object obj, Translator translator) {
        SyntaxForm syntaxForm;
        TemplateScope templateScope;
        Object obj2 = obj;
        Translator translator2 = translator;
        if (!(obj2 instanceof Pair)) {
            return translator2.syntaxError("missing let-syntax arguments");
        }
        Pair pair = (Pair) obj2;
        Object car = pair.getCar();
        Object cdr = pair.getCdr();
        int listLength = Translator.listLength(car);
        if (listLength < 0) {
            return translator2.syntaxError("bindings not a proper list");
        }
        Expression[] expressionArr = new Expression[listLength];
        Declaration[] declarationArr = new Declaration[listLength];
        Macro[] macroArr = new Macro[listLength];
        Pair[] pairArr = new Pair[listLength];
        SyntaxForm[] syntaxFormArr = new SyntaxForm[listLength];
        LetExp letExp = new LetExp(expressionArr);
        int i = 0;
        int i2 = 0;
        SyntaxForm syntaxForm2 = null;
        Stack stack = null;
        while (i2 < listLength) {
            while (car instanceof SyntaxForm) {
                syntaxForm2 = (SyntaxForm) car;
                car = syntaxForm2.getDatum();
            }
            Pair pair2 = (Pair) car;
            Object car2 = pair2.getCar();
            SyntaxForm syntaxForm3 = syntaxForm2;
            if (car2 instanceof SyntaxForm) {
                SyntaxForm syntaxForm4 = (SyntaxForm) car2;
                syntaxForm = syntaxForm4;
                car2 = syntaxForm4.getDatum();
            } else {
                syntaxForm = syntaxForm3;
            }
            if (!(car2 instanceof Pair)) {
                return translator2.syntaxError(getName() + " binding is not a pair");
            }
            Pair pair3 = (Pair) car2;
            Object obj3 = cdr;
            SyntaxForm syntaxForm5 = syntaxForm;
            Object obj4 = pair3.getCar();
            while (obj4 instanceof SyntaxForm) {
                syntaxForm5 = (SyntaxForm) obj4;
                obj4 = syntaxForm5.getDatum();
            }
            if ((obj4 instanceof String) || (obj4 instanceof Symbol)) {
                Object obj5 = pair3.getCdr();
                while (obj5 instanceof SyntaxForm) {
                    syntaxForm = (SyntaxForm) obj5;
                    obj5 = syntaxForm.getDatum();
                }
                if (!(obj5 instanceof Pair)) {
                    return translator2.syntaxError(getName() + " has no value for '" + obj4 + "'");
                }
                Pair pair4 = (Pair) obj5;
                int i3 = listLength;
                if (pair4.getCdr() != LList.Empty) {
                    return translator2.syntaxError("let binding for '" + obj4 + "' is improper list");
                }
                Declaration declaration = new Declaration(obj4);
                Macro make = Macro.make(declaration);
                macroArr[i2] = make;
                pairArr[i2] = pair4;
                syntaxFormArr[i2] = syntaxForm;
                letExp.addDeclaration(declaration);
                if (syntaxForm5 == null) {
                    templateScope = null;
                } else {
                    templateScope = syntaxForm5.getScope();
                }
                if (templateScope != null) {
                    Declaration makeRenamedAlias = translator2.makeRenamedAlias(declaration, templateScope);
                    if (stack == null) {
                        stack = new Stack();
                    }
                    stack.push(makeRenamedAlias);
                    i++;
                }
                make.setCapturedScope(syntaxForm != null ? syntaxForm.getScope() : this.recursive ? letExp : translator.currentScope());
                declarationArr[i2] = declaration;
                expressionArr[i2] = QuoteExp.nullExp;
                car = pair2.getCdr();
                i2++;
                syntaxForm2 = syntaxForm3;
                cdr = obj3;
                listLength = i3;
            } else {
                return translator2.syntaxError("variable in " + getName() + " binding is not a symbol");
            }
        }
        Object obj6 = cdr;
        int i4 = listLength;
        if (this.recursive) {
            push(letExp, translator2, stack);
        }
        Macro macro = translator2.currentMacroDefinition;
        int i5 = i4;
        int i6 = 0;
        while (i6 < i5) {
            Macro macro2 = macroArr[i6];
            translator2.currentMacroDefinition = macro2;
            Expression rewrite_car = translator2.rewrite_car(pairArr[i6], syntaxFormArr[i6]);
            expressionArr[i6] = rewrite_car;
            Declaration declaration2 = declarationArr[i6];
            macro2.expander = rewrite_car;
            int i7 = i5;
            declaration2.noteValue(new QuoteExp(macro2));
            if (rewrite_car instanceof LambdaExp) {
                LambdaExp lambdaExp = (LambdaExp) rewrite_car;
                lambdaExp.nameDecl = declaration2;
                lambdaExp.setSymbol(declaration2.getSymbol());
            }
            i6++;
            i5 = i7;
        }
        translator2.currentMacroDefinition = macro;
        if (!this.recursive) {
            push(letExp, translator2, stack);
        }
        Expression rewrite_body = translator2.rewrite_body(obj6);
        translator2.pop(letExp);
        translator2.popRenamedAlias(i);
        return rewrite_body;
    }

    private void push(LetExp letExp, Translator translator, Stack stack) {
        translator.push((ScopeExp) letExp);
        if (stack != null) {
            int size = stack.size();
            while (true) {
                size--;
                if (size >= 0) {
                    translator.pushRenamedAlias((Declaration) stack.pop());
                } else {
                    return;
                }
            }
        }
    }
}
