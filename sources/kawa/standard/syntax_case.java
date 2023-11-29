package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BlockExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ExitExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import kawa.lang.Pattern;
import kawa.lang.PatternScope;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.Translator;

public class syntax_case extends Syntax {
    public static final syntax_case syntax_case;
    PrimProcedure call_error;

    static {
        syntax_case syntax_case2 = new syntax_case();
        syntax_case = syntax_case2;
        syntax_case2.setName("syntax-case");
    }

    /* access modifiers changed from: package-private */
    public Expression rewriteClauses(Object obj, syntax_case_work syntax_case_work, Translator translator) {
        Expression expression;
        Object obj2 = obj;
        syntax_case_work syntax_case_work2 = syntax_case_work;
        Translator translator2 = translator;
        Language language = translator.getLanguage();
        if (obj2 == LList.Empty) {
            Expression[] expressionArr = {new QuoteExp("syntax-case"), new ReferenceExp(syntax_case_work2.inputExpression)};
            if (this.call_error == null) {
                this.call_error = new PrimProcedure(ClassType.make("kawa.standard.syntax_case").addMethod("error", new Type[]{Compilation.javaStringType, Type.objectType}, (Type) Type.objectType, 9), language);
            }
            return new ApplyExp((Procedure) this.call_error, expressionArr);
        }
        Object pushPositionOf = translator2.pushPositionOf(obj2);
        try {
            if (obj2 instanceof Pair) {
                Object car = ((Pair) obj2).getCar();
                if (car instanceof Pair) {
                    Pair pair = (Pair) car;
                    PatternScope push = PatternScope.push(translator);
                    push.matchArray = translator2.matchArray;
                    translator2.push((ScopeExp) push);
                    SyntaxForm syntaxForm = null;
                    Object obj3 = pair.getCdr();
                    while (obj3 instanceof SyntaxForm) {
                        syntaxForm = (SyntaxForm) obj3;
                        obj3 = syntaxForm.getDatum();
                    }
                    if (!(obj3 instanceof Pair)) {
                        Expression syntaxError = translator2.syntaxError("missing syntax-case output expression");
                        translator2.popPositionOf(pushPositionOf);
                        return syntaxError;
                    }
                    int size = push.pattern_names.size();
                    SyntaxPattern syntaxPattern = new SyntaxPattern(pair.getCar(), syntax_case_work2.literal_identifiers, translator2);
                    int varCount = syntaxPattern.varCount();
                    if (varCount > syntax_case_work2.maxVars) {
                        syntax_case_work2.maxVars = varCount;
                    }
                    BlockExp blockExp = new BlockExp();
                    ApplyExp applyExp = new ApplyExp((Procedure) new PrimProcedure(Pattern.matchPatternMethod, language), new QuoteExp(syntaxPattern), new ReferenceExp(syntax_case_work2.inputExpression), new ReferenceExp(translator2.matchArray), new QuoteExp(IntNum.zero()));
                    int i = varCount - size;
                    Expression[] expressionArr2 = new Expression[i];
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        expressionArr2[i] = QuoteExp.undefined_exp;
                    }
                    push.inits = expressionArr2;
                    Pair pair2 = (Pair) obj3;
                    if (pair2.getCdr() == LList.Empty) {
                        expression = translator2.rewrite_car(pair2, syntaxForm);
                    } else {
                        Expression rewrite_car = translator2.rewrite_car(pair2, syntaxForm);
                        if (pair2.getCdr() instanceof Pair) {
                            Pair pair3 = (Pair) pair2.getCdr();
                            if (pair3.getCdr() == LList.Empty) {
                                expression = new IfExp(rewrite_car, translator2.rewrite_car(pair3, syntaxForm), new ExitExp(blockExp));
                            }
                        }
                        Expression syntaxError2 = translator2.syntaxError("syntax-case:  bad clause");
                        translator2.popPositionOf(pushPositionOf);
                        return syntaxError2;
                    }
                    push.setBody(expression);
                    translator2.pop(push);
                    PatternScope.pop(translator);
                    blockExp.setBody(new IfExp(applyExp, push, new ExitExp(blockExp)), rewriteClauses(((Pair) obj2).getCdr(), syntax_case_work2, translator2));
                    translator2.popPositionOf(pushPositionOf);
                    return blockExp;
                }
            }
            return translator2.syntaxError("syntax-case:  bad clause list");
        } finally {
            translator2.popPositionOf(pushPositionOf);
        }
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        syntax_case_work syntax_case_work = new syntax_case_work();
        Object cdr = pair.getCdr();
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            if (pair2.getCdr() instanceof Pair) {
                Expression[] expressionArr = new Expression[2];
                LetExp letExp = new LetExp(expressionArr);
                String str = null;
                syntax_case_work.inputExpression = letExp.addDeclaration((Object) null);
                Declaration declaration = translator.matchArray;
                Declaration addDeclaration = letExp.addDeclaration((Object) null);
                addDeclaration.setType(Compilation.objArrayType);
                addDeclaration.setCanRead(true);
                translator.matchArray = addDeclaration;
                syntax_case_work.inputExpression.setCanRead(true);
                translator.push((ScopeExp) letExp);
                expressionArr[0] = translator.rewrite(pair2.getCar());
                syntax_case_work.inputExpression.noteValue(expressionArr[0]);
                Pair pair3 = (Pair) pair2.getCdr();
                syntax_case_work.literal_identifiers = SyntaxPattern.getLiteralsList(pair3.getCar(), (SyntaxForm) null, translator);
                letExp.body = rewriteClauses(pair3.getCdr(), syntax_case_work, translator);
                translator.pop(letExp);
                Method declaredMethod = ClassType.make("kawa.lang.SyntaxPattern").getDeclaredMethod("allocVars", 2);
                Expression[] expressionArr2 = new Expression[2];
                expressionArr2[0] = new QuoteExp(IntNum.make(syntax_case_work.maxVars));
                if (declaration == null) {
                    expressionArr2[1] = QuoteExp.nullExp;
                } else {
                    expressionArr2[1] = new ReferenceExp(declaration);
                }
                ApplyExp applyExp = new ApplyExp(declaredMethod, expressionArr2);
                expressionArr[1] = applyExp;
                addDeclaration.noteValue(applyExp);
                translator.matchArray = declaration;
                return letExp;
            }
        }
        return translator.syntaxError("insufficiant arguments to syntax-case");
    }

    public static Object error(String str, Object obj) {
        String str2;
        Translator translator = (Translator) Compilation.getCurrent();
        if (translator != null) {
            Syntax currentSyntax = translator.getCurrentSyntax();
            if (currentSyntax == null) {
                str2 = "some syntax";
            } else {
                str2 = currentSyntax.getName();
            }
            return translator.syntaxError("no matching case while expanding " + str2);
        }
        throw new RuntimeException("no match in syntax-case");
    }
}
