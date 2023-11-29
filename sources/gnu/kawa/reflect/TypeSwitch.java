package gnu.kawa.reflect;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class TypeSwitch extends MethodProc implements Inlineable {
    public static final TypeSwitch typeSwitch = new TypeSwitch("typeswitch");

    public int numArgs() {
        return -4094;
    }

    public TypeSwitch(String str) {
        setName(str);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyTypeSwitch");
    }

    public void apply(CallContext callContext) throws Throwable {
        Object[] args = callContext.getArgs();
        Object obj = args[0];
        int i = 1;
        int length = args.length - 1;
        while (i < length) {
            if (((MethodProc) args[i]).match1(obj, callContext) < 0) {
                i++;
            } else {
                return;
            }
        }
        ((Procedure) args[length]).check1(obj, callContext);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Expression[] args = applyExp.getArgs();
        CodeAttr code = compilation.getCode();
        code.pushScope();
        Variable addLocal = code.addLocal(Type.pointer_type);
        args[0].compile(compilation, Target.pushObject);
        code.emitStore(addLocal);
        int i = 1;
        while (i < args.length) {
            if (i > 1) {
                code.emitElse();
            }
            int i2 = i + 1;
            Expression expression = args[i];
            if (expression instanceof LambdaExp) {
                LambdaExp lambdaExp = (LambdaExp) expression;
                Declaration firstDecl = lambdaExp.firstDecl();
                Type type = firstDecl.getType();
                if (!firstDecl.getCanRead()) {
                    firstDecl = null;
                } else {
                    firstDecl.allocateVariable(code);
                }
                if (type instanceof TypeValue) {
                    ((TypeValue) type).emitTestIf(addLocal, firstDecl, compilation);
                } else {
                    if (i2 < args.length) {
                        code.emitLoad(addLocal);
                        type.emitIsInstance(code);
                        code.emitIfIntNotZero();
                    }
                    if (firstDecl != null) {
                        code.emitLoad(addLocal);
                        firstDecl.compileStore(compilation);
                    }
                }
                lambdaExp.allocChildClasses(compilation);
                lambdaExp.body.compileWithPosition(compilation, target);
                i = i2;
            } else {
                throw new Error("not implemented: typeswitch arg not LambdaExp");
            }
        }
        int length = args.length - 2;
        while (true) {
            length--;
            if (length >= 0) {
                code.emitFi();
            } else {
                code.popScope();
                return;
            }
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }
}
