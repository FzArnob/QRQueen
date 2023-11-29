package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.PatternScope;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxTemplate;
import kawa.lang.Translator;

public class syntax extends Quote {
    static final Method makeTemplateScopeMethod;
    public static final syntax quasiSyntax = new syntax("quasisyntax", true);
    public static final syntax syntax = new syntax("syntax", false);
    static final ClassType typeTemplateScope;

    /* access modifiers changed from: protected */
    public boolean expandColonForms() {
        return false;
    }

    static {
        ClassType make = ClassType.make("kawa.lang.TemplateScope");
        typeTemplateScope = make;
        makeTemplateScopeMethod = make.getDeclaredMethod("make", 0);
    }

    public syntax(String str, boolean z) {
        super(str, z);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        if (pair.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) pair.getCdr();
            Pair pair3 = pair2;
            if (pair2.getCdr() == LList.Empty) {
                Declaration declaration = translator.templateScopeDecl;
                if (declaration == null) {
                    translator.letStart();
                    Declaration letVariable = translator.letVariable((Object) null, typeTemplateScope, new ApplyExp(makeTemplateScopeMethod, Expression.noExpressions));
                    letVariable.setCanRead();
                    translator.templateScopeDecl = letVariable;
                    translator.letEnter();
                }
                try {
                    Expression coerceExpression = coerceExpression(expand(pair2.getCar(), this.isQuasi ? 1 : -1, translator), translator);
                    if (declaration == null) {
                        coerceExpression = translator.letDone(coerceExpression);
                    }
                    return coerceExpression;
                } finally {
                    translator.templateScopeDecl = declaration;
                }
            }
        }
        return translator.syntaxError("syntax forms requires a single form");
    }

    /* access modifiers changed from: protected */
    public Expression leaf(Object obj, Translator translator) {
        return makeSyntax(obj, translator);
    }

    static Expression makeSyntax(Object obj, Translator translator) {
        SyntaxTemplate syntaxTemplate = new SyntaxTemplate(obj, (SyntaxForm) null, translator);
        Expression expression = QuoteExp.nullExp;
        PatternScope patternScope = translator.patternScope;
        if (!(patternScope == null || patternScope.matchArray == null)) {
            expression = new ReferenceExp(patternScope.matchArray);
        }
        return new ApplyExp(ClassType.make("kawa.lang.SyntaxTemplate").getDeclaredMethod("execute", 2), new QuoteExp(syntaxTemplate), expression, new ReferenceExp(translator.templateScopeDecl));
    }
}
