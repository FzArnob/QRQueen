package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;

public class define extends Syntax {
    public static final define defineRaw = new define(SchemeCompilation.lambda);
    Lambda lambda;

    /* access modifiers changed from: package-private */
    public String getName(int i) {
        return (i & 4) != 0 ? "define-private" : (i & 8) != 0 ? "define-constant" : "define";
    }

    public define(Lambda lambda2) {
        this.lambda = lambda2;
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        int i;
        ScopeExp scopeExp2 = scopeExp;
        Translator translator2 = translator;
        Pair pair2 = (Pair) pair.getCdr();
        Pair pair3 = (Pair) pair2.getCdr();
        Pair pair4 = (Pair) pair3.getCdr();
        Pair pair5 = (Pair) pair4.getCdr();
        Object car = pair2.getCar();
        SyntaxForm syntaxForm = null;
        while (car instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) car;
            car = syntaxForm.getDatum();
        }
        int intValue = ((Number) Translator.stripSyntax(pair3.getCar())).intValue();
        boolean z = false;
        boolean z2 = (intValue & 4) != 0;
        if ((intValue & 8) != 0) {
            z = true;
        }
        translator.currentScope();
        Object namespaceResolve = translator2.namespaceResolve(car);
        if (!(namespaceResolve instanceof Symbol)) {
            translator2.error('e', "'" + namespaceResolve + "' is not a valid identifier");
            namespaceResolve = null;
        }
        Object pushPositionOf = translator2.pushPositionOf(pair2);
        Declaration define = translator2.define(namespaceResolve, syntaxForm, scopeExp2);
        translator2.popPositionOf(pushPositionOf);
        Object symbol = define.getSymbol();
        if (z2) {
            define.setFlag(16777216);
            define.setPrivate(true);
        }
        if (z) {
            define.setFlag(16384);
        }
        define.setFlag(262144);
        if ((intValue & 2) != 0) {
            LambdaExp lambdaExp = new LambdaExp();
            lambdaExp.setSymbol(symbol);
            if (Compilation.inlineOk) {
                define.setProcedureDecl(true);
                define.setType(Compilation.typeProcedure);
                lambdaExp.nameDecl = define;
            }
            Object car2 = pair5.getCar();
            Object cdr = pair5.getCdr();
            Translator.setLine((Expression) lambdaExp, (Object) pair2);
            this.lambda.rewriteFormals(lambdaExp, car2, translator2, (TemplateScope) null);
            Object rewriteAttrs = this.lambda.rewriteAttrs(lambdaExp, cdr, translator2);
            if (rewriteAttrs != cdr) {
                pair3 = new Pair(pair3.getCar(), new Pair(pair4.getCar(), new Pair(car2, rewriteAttrs)));
            }
            define.noteValue(lambdaExp);
        }
        if (!(scopeExp2 instanceof ModuleExp) || z2 || (Compilation.inlineOk && !translator.sharedModuleDefs())) {
            i = 1;
        } else {
            i = 1;
            define.setCanWrite(true);
        }
        if ((i & intValue) != 0) {
            define.setTypeExp(new LangExp(pair4));
            define.setFlag(8192);
        }
        Pair makePair = Translator.makePair(pair, this, Translator.makePair(pair2, define, pair3));
        Translator.setLine(define, (Object) pair2);
        translator2.formStack.addElement(makePair);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        LambdaExp lambdaExp;
        Pair pair2 = (Pair) pair.getCdr();
        Pair pair3 = (Pair) pair2.getCdr();
        Pair pair4 = (Pair) ((Pair) pair3.getCdr()).getCdr();
        Object stripSyntax = Translator.stripSyntax(pair2.getCar());
        int intValue = ((Number) Translator.stripSyntax(pair3.getCar())).intValue();
        boolean z = (intValue & 4) != 0;
        if (!(stripSyntax instanceof Declaration)) {
            return translator.syntaxError(getName(intValue) + " is only allowed in a <body>");
        }
        Declaration declaration = (Declaration) stripSyntax;
        if (declaration.getFlag(8192)) {
            Expression typeExp = declaration.getTypeExp();
            if (typeExp instanceof LangExp) {
                declaration.setType(translator.exp2Type((Pair) ((LangExp) typeExp).getLangValue()));
            }
        }
        Expression expression = null;
        if ((intValue & 2) != 0) {
            LambdaExp lambdaExp2 = (LambdaExp) declaration.getValue();
            this.lambda.rewriteBody(lambdaExp2, pair4.getCdr(), translator);
            lambdaExp = lambdaExp2;
            if (!Compilation.inlineOk) {
                declaration.noteValue((Expression) null);
                lambdaExp = lambdaExp2;
            }
        } else {
            Expression rewrite = translator.rewrite(pair4.getCar());
            if (!(declaration.context instanceof ModuleExp) || z || !declaration.getCanWrite()) {
                expression = rewrite;
            }
            declaration.noteValue(expression);
            lambdaExp = rewrite;
        }
        SetExp setExp = new SetExp(declaration, lambdaExp);
        setExp.setDefining(true);
        if (z && !(translator.currentScope() instanceof ModuleExp)) {
            translator.error('w', "define-private not at top level " + translator.currentScope());
        }
        return setExp;
    }
}
