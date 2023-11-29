package gnu.kawa.functions;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ExpVisitor;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.WrongArguments;
import kawa.standard.Scheme;

public class CompileMisc implements Inlineable {
    static final int CONVERT = 2;
    static final int NOT = 3;
    static Method coerceMethod;
    public static final ClassType typeContinuation = ClassType.make("kawa.lang.Continuation");
    static ClassType typeType;
    int code;
    Procedure proc;

    public CompileMisc(Procedure procedure, int i) {
        this.proc = procedure;
        this.code = i;
    }

    public static CompileMisc forConvert(Object obj) {
        return new CompileMisc((Procedure) obj, 2);
    }

    public static CompileMisc forNot(Object obj) {
        return new CompileMisc((Procedure) obj, 3);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        int i = this.code;
        if (i == 2) {
            compileConvert((Convert) this.proc, applyExp, compilation, target);
        } else if (i == 3) {
            compileNot((Not) this.proc, applyExp, compilation, target);
        } else {
            throw new Error();
        }
    }

    public static Expression validateApplyConstantFunction0(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        int argCount = applyExp.getArgCount();
        if (argCount == 0 || inlineCalls == null) {
            return ((ConstantFunction0) procedure).constant;
        }
        return inlineCalls.noteError(WrongArguments.checkArgCount(procedure, argCount));
    }

    public static Expression validateApplyConvert(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Compilation compilation = inlineCalls.getCompilation();
        Language language = compilation.getLanguage();
        Expression[] args = applyExp.getArgs();
        if (args.length == 2) {
            Expression visit = inlineCalls.visit(args[0], (Type) null);
            args[0] = visit;
            Type typeFor = language.getTypeFor(visit);
            if (typeFor instanceof Type) {
                args[0] = new QuoteExp(typeFor);
                args[1] = inlineCalls.visit(args[1], typeFor);
                CompileReflect.checkKnownClass(typeFor, compilation);
                applyExp.setType(typeFor);
                return applyExp;
            }
        }
        applyExp.visitArgs(inlineCalls);
        return applyExp;
    }

    public static Expression validateApplyNot(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        applyExp.setType(inlineCalls.getCompilation().getLanguage().getTypeFor(Boolean.TYPE));
        return applyExp.inlineIfConstant(procedure, inlineCalls);
    }

    public static Expression validateApplyFormat(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Type type2 = Type.objectType;
        Expression[] args = applyExp.getArgs();
        if (args.length > 0) {
            ClassType make = ClassType.make("gnu.kawa.functions.Format");
            Object valueIfConstant = args[0].valueIfConstant();
            Type type3 = args[0].getType();
            if (valueIfConstant == Boolean.FALSE || type3.isSubtype(LangObjType.stringType)) {
                int i = valueIfConstant == Boolean.FALSE ? 1 : 0;
                int length = (args.length + 1) - i;
                Expression[] expressionArr = new Expression[length];
                expressionArr[0] = new QuoteExp(0, Type.intType);
                System.arraycopy(args, i, expressionArr, 1, length - 1);
                ApplyExp applyExp2 = new ApplyExp(make.getDeclaredMethod("formatToString", 2), expressionArr);
                applyExp2.setType(Type.javalangStringType);
                return applyExp2;
            } else if (valueIfConstant == Boolean.TRUE || type3.isSubtype(ClassType.make("java.io.Writer"))) {
                if (valueIfConstant == Boolean.TRUE) {
                    Expression[] expressionArr2 = new Expression[args.length];
                    expressionArr2[0] = QuoteExp.nullExp;
                    System.arraycopy(args, 1, expressionArr2, 1, args.length - 1);
                    args = expressionArr2;
                }
                ApplyExp applyExp3 = new ApplyExp(make.getDeclaredMethod("formatToWriter", 3), args);
                applyExp3.setType(Type.voidType);
                return applyExp3;
            } else if (type3.isSubtype(ClassType.make("java.io.OutputStream"))) {
                type2 = Type.voidType;
            }
        }
        applyExp.setType(type2);
        return null;
    }

    public static Expression validateApplyAppendValues(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length == 1) {
            return args[0];
        }
        if (args.length == 0) {
            return QuoteExp.voidExp;
        }
        Expression inlineIfConstant = applyExp.inlineIfConstant(procedure, inlineCalls);
        return inlineIfConstant != applyExp ? inlineIfConstant : applyExp;
    }

    public static Expression validateApplyMakeProcedure(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        int length = args.length;
        LambdaExp lambdaExp = null;
        int i = 0;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Expression expression = args[i2];
            if (expression instanceof QuoteExp) {
                Object value = ((QuoteExp) expression).getValue();
                if (value instanceof Keyword) {
                    String name = ((Keyword) value).getName();
                    i2++;
                    Expression expression2 = args[i2];
                    if (name == CommonProperties.NAME) {
                        if (expression2 instanceof QuoteExp) {
                            str = ((QuoteExp) expression2).getValue().toString();
                        }
                    } else if (name == "method") {
                        i3++;
                        lambdaExp = expression2;
                    }
                    i2++;
                }
            }
            i3++;
            lambdaExp = expression;
            i2++;
        }
        if (i3 != 1 || !(lambdaExp instanceof LambdaExp)) {
            return applyExp;
        }
        LambdaExp lambdaExp2 = lambdaExp;
        while (i < length) {
            Expression expression3 = args[i];
            if (expression3 instanceof QuoteExp) {
                Object value2 = ((QuoteExp) expression3).getValue();
                if (value2 instanceof Keyword) {
                    String name2 = ((Keyword) value2).getName();
                    i++;
                    Expression expression4 = args[i];
                    if (name2 == CommonProperties.NAME) {
                        lambdaExp2.setName(str);
                    } else if (name2 != "method") {
                        lambdaExp2.setProperty(Namespace.EmptyNamespace.getSymbol(name2), expression4);
                    }
                }
            }
            i++;
        }
        return lambdaExp;
    }

    public static Expression validateApplyValuesMap(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        LambdaExp canInline = ValuesMap.canInline(applyExp, (ValuesMap) procedure);
        if (canInline != null) {
            canInline.setInlineOnly(true);
            canInline.returnContinuation = applyExp;
            canInline.inlineHome = inlineCalls.getCurrentLambda();
        }
        return applyExp;
    }

    public static void compileConvert(Convert convert, ApplyExp applyExp, Compilation compilation, Target target) {
        Expression[] args = applyExp.getArgs();
        if (args.length == 2) {
            CodeAttr code2 = compilation.getCode();
            Type typeValue = Scheme.getTypeValue(args[0]);
            if (typeValue != null) {
                args[1].compile(compilation, Target.pushValue(typeValue));
                if (code2.reachableHere()) {
                    target.compileFromStack(compilation, typeValue);
                    return;
                }
                return;
            }
            if (typeType == null) {
                typeType = ClassType.make("gnu.bytecode.Type");
            }
            if (coerceMethod == null) {
                coerceMethod = typeType.addMethod("coerceFromObject", Compilation.apply1args, (Type) Type.pointer_type, 1);
            }
            args[0].compile(compilation, (Type) LangObjType.typeClassType);
            args[1].compile(compilation, Target.pushObject);
            code2.emitInvokeVirtual(coerceMethod);
            target.compileFromStack(compilation, Type.pointer_type);
            return;
        }
        throw new Error("wrong number of arguments to " + convert.getName());
    }

    public void compileNot(Not not, ApplyExp applyExp, Compilation compilation, Target target) {
        Expression expression = applyExp.getArgs()[0];
        Language language = not.language;
        if (target instanceof ConditionalTarget) {
            ConditionalTarget conditionalTarget = (ConditionalTarget) target;
            ConditionalTarget conditionalTarget2 = new ConditionalTarget(conditionalTarget.ifFalse, conditionalTarget.ifTrue, language);
            conditionalTarget2.trueBranchComesFirst = !conditionalTarget.trueBranchComesFirst;
            expression.compile(compilation, (Target) conditionalTarget2);
            return;
        }
        CodeAttr code2 = compilation.getCode();
        Type type = target.getType();
        if (!(target instanceof StackTarget) || type.getSignature().charAt(0) != 'Z') {
            IfExp.compile(expression, QuoteExp.getInstance(language.booleanObject(false)), QuoteExp.getInstance(language.booleanObject(true)), compilation, target);
            return;
        }
        expression.compile(compilation, target);
        code2.emitNot(target.getType());
    }

    public static Expression validateApplyCallCC(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        LambdaExp canInlineCallCC = canInlineCallCC(applyExp);
        if (canInlineCallCC != null) {
            canInlineCallCC.setInlineOnly(true);
            canInlineCallCC.returnContinuation = applyExp;
            canInlineCallCC.inlineHome = inlineCalls.getCurrentLambda();
            Declaration firstDecl = canInlineCallCC.firstDecl();
            if (!firstDecl.getFlag(8192)) {
                firstDecl.setType(typeContinuation);
            }
        }
        applyExp.visitArgs(inlineCalls);
        return applyExp;
    }

    public static void compileCallCC(ApplyExp applyExp, Compilation compilation, Target target, Procedure procedure) {
        LambdaExp canInlineCallCC = canInlineCallCC(applyExp);
        if (canInlineCallCC == null) {
            ApplyExp.compile(applyExp, compilation, target);
            return;
        }
        CodeAttr code2 = compilation.getCode();
        Declaration firstDecl = canInlineCallCC.firstDecl();
        Type type = null;
        if (!firstDecl.isSimple() || firstDecl.getCanRead() || firstDecl.getCanWrite()) {
            Scope pushScope = code2.pushScope();
            ClassType classType = typeContinuation;
            Variable addVariable = pushScope.addVariable(code2, classType, (String) null);
            Declaration declaration = new Declaration(addVariable);
            code2.emitNew(classType);
            code2.emitDup((Type) classType);
            compilation.loadCallContext();
            code2.emitInvokeSpecial(classType.getDeclaredMethod("<init>", 1));
            code2.emitStore(addVariable);
            code2.emitTryStart(false, ((target instanceof IgnoreTarget) || (target instanceof ConsumerTarget)) ? null : Type.objectType);
            new ApplyExp((Expression) canInlineCallCC, new ReferenceExp(declaration)).compile(compilation, target);
            if (code2.reachableHere()) {
                code2.emitLoad(addVariable);
                code2.emitPushInt(1);
                code2.emitPutField(classType.getField("invoked"));
            }
            code2.emitTryEnd();
            code2.emitCatchStart((Variable) null);
            code2.emitLoad(addVariable);
            if (target instanceof ConsumerTarget) {
                compilation.loadCallContext();
                code2.emitInvokeStatic(classType.getDeclaredMethod("handleException$X", 3));
            } else {
                code2.emitInvokeStatic(classType.getDeclaredMethod("handleException", 2));
                target.compileFromStack(compilation, Type.objectType);
            }
            code2.emitCatchEnd();
            code2.emitTryCatchEnd();
            code2.popScope();
            return;
        }
        firstDecl.setCanCall(false);
        CompileTimeContinuation compileTimeContinuation = new CompileTimeContinuation();
        if (target instanceof StackTarget) {
            type = target.getType();
        }
        compileTimeContinuation.exitableBlock = code2.startExitableBlock(type, ExitThroughFinallyChecker.check(firstDecl, canInlineCallCC.body));
        compileTimeContinuation.blockTarget = target;
        firstDecl.setValue(new QuoteExp(compileTimeContinuation));
        new ApplyExp((Expression) canInlineCallCC, QuoteExp.nullExp).compile(compilation, target);
        code2.endExitableBlock();
    }

    private static LambdaExp canInlineCallCC(ApplyExp applyExp) {
        Expression[] args = applyExp.getArgs();
        if (args.length != 1) {
            return null;
        }
        Expression expression = args[0];
        if (!(expression instanceof LambdaExp)) {
            return null;
        }
        LambdaExp lambdaExp = (LambdaExp) expression;
        if (lambdaExp.min_args == 1 && lambdaExp.max_args == 1 && !lambdaExp.firstDecl().getCanWrite()) {
            return lambdaExp;
        }
        return null;
    }

    static class ExitThroughFinallyChecker extends ExpVisitor<Expression, TryExp> {
        Declaration decl;

        /* access modifiers changed from: protected */
        public Expression defaultValue(Expression expression, TryExp tryExp) {
            return expression;
        }

        ExitThroughFinallyChecker() {
        }

        public static boolean check(Declaration declaration, Expression expression) {
            ExitThroughFinallyChecker exitThroughFinallyChecker = new ExitThroughFinallyChecker();
            exitThroughFinallyChecker.decl = declaration;
            exitThroughFinallyChecker.visit(expression, null);
            return exitThroughFinallyChecker.exitValue != null;
        }

        /* access modifiers changed from: protected */
        public Expression visitReferenceExp(ReferenceExp referenceExp, TryExp tryExp) {
            if (this.decl == referenceExp.getBinding() && tryExp != null) {
                this.exitValue = Boolean.TRUE;
            }
            return referenceExp;
        }

        /* access modifiers changed from: protected */
        public Expression visitTryExp(TryExp tryExp, TryExp tryExp2) {
            if (tryExp.getFinallyClause() != null) {
                tryExp2 = tryExp;
            }
            visitExpression(tryExp, tryExp2);
            return tryExp;
        }
    }

    public static Expression validateApplyMap(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        InlineCalls inlineCalls2 = inlineCalls;
        Map map = (Map) procedure;
        boolean z = map.collect;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        int length = args.length;
        if (length < 2) {
            return applyExp;
        }
        int i = length - 1;
        Expression expression = args[0];
        boolean z2 = !expression.side_effects();
        LetExp letExp = new LetExp(new Expression[]{expression});
        Declaration addDeclaration = letExp.addDeclaration("%proc", Compilation.typeProcedure);
        addDeclaration.noteValue(args[0]);
        Expression[] expressionArr = new Expression[1];
        LetExp letExp2 = new LetExp(expressionArr);
        letExp.setBody(letExp2);
        LambdaExp lambdaExp = new LambdaExp(z ? i + 1 : i);
        expressionArr[0] = lambdaExp;
        Declaration addDeclaration2 = letExp2.addDeclaration((Object) "%loop");
        addDeclaration2.noteValue(lambdaExp);
        Expression[] expressionArr2 = new Expression[i];
        LetExp letExp3 = new LetExp(expressionArr2);
        Declaration[] declarationArr = new Declaration[i];
        Declaration[] declarationArr2 = new Declaration[i];
        ReferenceExp referenceExp = expression;
        int i2 = 0;
        while (i2 < i) {
            LetExp letExp4 = letExp;
            String str = "arg" + i2;
            declarationArr[i2] = lambdaExp.addDeclaration((Object) str);
            declarationArr2[i2] = letExp3.addDeclaration(str, Compilation.typePair);
            ReferenceExp referenceExp2 = new ReferenceExp(declarationArr[i2]);
            expressionArr2[i2] = referenceExp2;
            declarationArr2[i2].noteValue(referenceExp2);
            i2++;
            letExp = letExp4;
            letExp2 = letExp2;
        }
        LetExp letExp5 = letExp;
        LetExp letExp6 = letExp2;
        Declaration addDeclaration3 = z ? lambdaExp.addDeclaration((Object) "result") : null;
        int i3 = i + 1;
        Expression[] expressionArr3 = new Expression[i3];
        Expression[] expressionArr4 = new Expression[(z ? i3 : i)];
        int i4 = i3;
        int i5 = 0;
        while (i5 < i) {
            int i6 = i5 + 1;
            expressionArr3[i6] = inlineCalls2.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(declarationArr2[i5]), "car"), (Type) null);
            expressionArr4[i5] = inlineCalls2.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(declarationArr2[i5]), "cdr"), (Type) null);
            i5 = i6;
            args = args;
            declarationArr = declarationArr;
        }
        Expression[] expressionArr5 = args;
        Declaration[] declarationArr3 = declarationArr;
        if (!z2) {
            referenceExp = new ReferenceExp(addDeclaration);
        }
        expressionArr3[0] = referenceExp;
        Expression visitApplyOnly = inlineCalls2.visitApplyOnly(new ApplyExp((Expression) new ReferenceExp(map.applyFieldDecl), expressionArr3), (Type) null);
        if (z) {
            expressionArr4[i] = Invoke.makeInvokeStatic(Compilation.typePair, "make", new Expression[]{visitApplyOnly, new ReferenceExp(addDeclaration3)});
        }
        Expression visitApplyOnly2 = inlineCalls2.visitApplyOnly(new ApplyExp((Expression) new ReferenceExp(addDeclaration2), expressionArr4), (Type) null);
        if (!z) {
            visitApplyOnly2 = new BeginExp(visitApplyOnly, visitApplyOnly2);
        }
        lambdaExp.body = visitApplyOnly2;
        letExp3.setBody(lambdaExp.body);
        lambdaExp.body = letExp3;
        Expression[] expressionArr6 = new Expression[(z ? i4 : i)];
        QuoteExp quoteExp = new QuoteExp(LList.Empty);
        int i7 = i;
        while (true) {
            i7--;
            if (i7 < 0) {
                break;
            }
            lambdaExp.body = new IfExp(inlineCalls2.visitApplyOnly(new ApplyExp((Procedure) map.isEq, new ReferenceExp(declarationArr3[i7]), quoteExp), (Type) null), z ? new ReferenceExp(addDeclaration3) : QuoteExp.voidExp, lambdaExp.body);
            expressionArr6[i7] = expressionArr5[i7 + 1];
        }
        if (z) {
            expressionArr6[i] = quoteExp;
        }
        Expression visitApplyOnly3 = inlineCalls2.visitApplyOnly(new ApplyExp((Expression) new ReferenceExp(addDeclaration2), expressionArr6), (Type) null);
        if (z) {
            visitApplyOnly3 = Invoke.makeInvokeStatic(Compilation.scmListType, "reverseInPlace", new Expression[]{visitApplyOnly3});
        }
        LetExp letExp7 = letExp6;
        letExp7.setBody(visitApplyOnly3);
        return z2 ? letExp7 : letExp5;
    }
}
