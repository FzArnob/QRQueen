package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class ValuesMap extends MethodProc implements Inlineable {
    public static final ValuesMap valuesMap = new ValuesMap(-1);
    public static final ValuesMap valuesMapWithPos = new ValuesMap(1);
    private final int startCounter;

    public int numArgs() {
        return 8194;
    }

    private ValuesMap(int i) {
        this.startCounter = i;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyValuesMap");
    }

    public void apply(CallContext callContext) throws Throwable {
        Procedure procedure = (Procedure) callContext.getNextArg();
        Consumer consumer = callContext.consumer;
        Object nextArg = callContext.getNextArg();
        Procedure.checkArgCount(procedure, 1);
        if (nextArg instanceof Values) {
            int i = 0;
            int i2 = this.startCounter;
            Values values = (Values) nextArg;
            while (true) {
                i = values.nextPos(i);
                if (i != 0) {
                    Object posPrevious = values.getPosPrevious(i);
                    if (this.startCounter >= 0) {
                        procedure.check2(posPrevious, IntNum.make(i2), callContext);
                        i2++;
                    } else {
                        procedure.check1(posPrevious, callContext);
                    }
                    callContext.runUntilDone();
                } else {
                    return;
                }
            }
        } else {
            int i3 = this.startCounter;
            if (i3 >= 0) {
                procedure.check2(nextArg, IntNum.make(i3), callContext);
            } else {
                procedure.check1(nextArg, callContext);
            }
            callContext.runUntilDone();
        }
    }

    static LambdaExp canInline(ApplyExp applyExp, ValuesMap valuesMap2) {
        Expression[] args = applyExp.getArgs();
        int i = 2;
        if (args.length != 2) {
            return null;
        }
        Expression expression = args[0];
        if (!(expression instanceof LambdaExp)) {
            return null;
        }
        LambdaExp lambdaExp = (LambdaExp) expression;
        if (lambdaExp.min_args != lambdaExp.max_args) {
            return null;
        }
        int i2 = lambdaExp.min_args;
        if (valuesMap2.startCounter < 0) {
            i = 1;
        }
        if (i2 == i) {
            return lambdaExp;
        }
        return null;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        LambdaExp canInline = canInline(applyExp, this);
        if (canInline == null) {
            ApplyExp.compile(applyExp, compilation, target);
            return;
        }
        Expression[] args = applyExp.getArgs();
        if ((target instanceof IgnoreTarget) || (target instanceof ConsumerTarget)) {
            compileInlined(canInline, args[1], this.startCounter, (Method) null, compilation, target);
        } else {
            ConsumerTarget.compileUsingConsumer(applyExp, compilation, target);
        }
    }

    public static void compileInlined(LambdaExp lambdaExp, Expression expression, int i, Method method, Compilation compilation, Target target) {
        Variable variable;
        int i2 = i;
        Method method2 = method;
        Compilation compilation2 = compilation;
        Declaration firstDecl = lambdaExp.firstDecl();
        CodeAttr code = compilation.getCode();
        Scope pushScope = code.pushScope();
        Type type = firstDecl.getType();
        Declaration declaration = null;
        if (i2 >= 0) {
            Variable addVariable = pushScope.addVariable(code, Type.intType, "position");
            code.emitPushInt(i2);
            code.emitStore(addVariable);
            Variable variable2 = addVariable;
            declaration = new Declaration(addVariable);
            variable = variable2;
        } else {
            variable = null;
        }
        if (!firstDecl.isSimple() || method2 != null) {
            firstDecl = new Declaration(code.addLocal(type.getImplementationType(), Compilation.mangleNameIfNeeded(firstDecl.getName())));
        } else {
            firstDecl.allocateVariable(code);
        }
        Expression applyExp = new ApplyExp((Expression) lambdaExp, i2 >= 0 ? new Expression[]{new ReferenceExp(firstDecl), new ReferenceExp(declaration)} : new Expression[]{new ReferenceExp(firstDecl)});
        if (method2 != null) {
            if (applyExp.getType().getImplementationType() != Type.booleanType) {
                applyExp = new ApplyExp(method2, applyExp, new ReferenceExp(declaration));
            }
            applyExp = new IfExp(applyExp, new ReferenceExp(firstDecl), QuoteExp.voidExp);
        }
        Variable addLocal = code.addLocal(Type.intType);
        Variable addLocal2 = code.addLocal(Type.pointer_type);
        Variable addLocal3 = code.addLocal(Type.intType);
        expression.compileWithPosition(compilation2, Target.pushObject);
        code.emitStore(addLocal2);
        code.emitPushInt(0);
        code.emitStore(addLocal);
        Label label = new Label(code);
        Label label2 = new Label(code);
        label.define(code);
        code.emitLoad(addLocal2);
        code.emitLoad(addLocal);
        code.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextIndex", 2));
        code.emitDup((Type) Type.intType);
        code.emitStore(addLocal3);
        code.emitGotoIfIntLtZero(label2);
        code.emitLoad(addLocal2);
        code.emitLoad(addLocal);
        code.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextValue", 2));
        StackTarget.convert(compilation2, Type.objectType, type);
        firstDecl.compileStore(compilation2);
        applyExp.compile(compilation2, target);
        if (i2 >= 0) {
            code.emitInc(variable, 1);
        }
        code.emitLoad(addLocal3);
        code.emitStore(addLocal);
        code.emitGoto(label);
        label2.define(code);
        code.popScope();
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }
}
