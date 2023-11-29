package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class CheckedTarget extends StackTarget {
    static Method initWrongTypeProcMethod;
    static Method initWrongTypeStringMethod;
    static ClassType typeClassCastException;
    static ClassType typeWrongType;
    int argno;
    LambdaExp proc;
    String procname;

    public CheckedTarget(Type type) {
        super(type);
        this.argno = -4;
    }

    public CheckedTarget(Type type, LambdaExp lambdaExp, int i) {
        super(type);
        this.proc = lambdaExp;
        this.procname = lambdaExp.getName();
        this.argno = i;
    }

    public CheckedTarget(Type type, String str, int i) {
        super(type);
        this.procname = str;
        this.argno = i;
    }

    public static Target getInstance(Type type, String str, int i) {
        return type == Type.objectType ? Target.pushObject : new CheckedTarget(type, str, i);
    }

    public static Target getInstance(Type type, LambdaExp lambdaExp, int i) {
        return type == Type.objectType ? Target.pushObject : new CheckedTarget(type, lambdaExp, i);
    }

    public static Target getInstance(Type type) {
        return type == Type.objectType ? Target.pushObject : new CheckedTarget(type);
    }

    public static Target getInstance(Declaration declaration) {
        return getInstance(declaration.getType(), declaration.getName(), -2);
    }

    private static void initWrongType() {
        if (typeClassCastException == null) {
            typeClassCastException = ClassType.make("java.lang.ClassCastException");
        }
        if (typeWrongType == null) {
            typeWrongType = ClassType.make("gnu.mapping.WrongType");
            initWrongTypeStringMethod = typeWrongType.addMethod("<init>", 1, new Type[]{typeClassCastException, Compilation.javaStringType, Type.intType, Type.objectType}, (Type) Type.voidType);
            initWrongTypeProcMethod = typeWrongType.addMethod("<init>", 1, new Type[]{typeClassCastException, Compilation.typeProcedure, Type.intType, Type.objectType}, (Type) Type.voidType);
        }
    }

    public void compileFromStack(Compilation compilation, Type type) {
        if (!compileFromStack0(compilation, type)) {
            emitCheckedCoerce(compilation, this.proc, this.procname, this.argno, this.type, (Variable) null);
        }
    }

    public static void emitCheckedCoerce(Compilation compilation, String str, int i, Type type) {
        emitCheckedCoerce(compilation, (LambdaExp) null, str, i, type, (Variable) null);
    }

    public static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaExp, int i, Type type) {
        emitCheckedCoerce(compilation, lambdaExp, lambdaExp.getName(), i, type, (Variable) null);
    }

    public static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaExp, int i, Type type, Variable variable) {
        emitCheckedCoerce(compilation, lambdaExp, lambdaExp.getName(), i, type, variable);
    }

    static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaExp, String str, int i, Type type, Variable variable) {
        Scope scope;
        Variable variable2;
        CodeAttr code = compilation.getCode();
        boolean isInTry = code.isInTry();
        initWrongType();
        Label label = new Label(code);
        boolean z = true;
        if (variable != null || type == Type.toStringType) {
            variable2 = variable;
            scope = null;
        } else {
            scope = code.pushScope();
            variable2 = code.addLocal(Type.objectType);
            code.emitDup(1);
            code.emitStore(variable2);
        }
        int pc = code.getPC();
        label.define(code);
        emitCoerceFromObject(type, compilation);
        if (code.getPC() != pc && type != Type.toStringType) {
            Label label2 = new Label(code);
            label2.define(code);
            Label label3 = new Label(code);
            label3.setTypes(code);
            if (isInTry) {
                code.emitGoto(label3);
            }
            code.setUnreachable();
            int beginFragment = !isInTry ? code.beginFragment(label3) : 0;
            code.addHandler(label, label2, typeClassCastException);
            if (lambdaExp == null || !lambdaExp.isClassGenerated() || compilation.method.getStaticFlag() || compilation.method.getDeclaringClass() != lambdaExp.getCompiledClassType(compilation)) {
                z = false;
            }
            int lineNumber = compilation.getLineNumber();
            if (lineNumber > 0) {
                code.putLineNumber(lineNumber);
            }
            code.emitNew(typeWrongType);
            code.emitDupX();
            code.emitSwap();
            if (z) {
                code.emitPushThis();
            } else {
                if (str == null && i != -4) {
                    str = "lambda";
                }
                code.emitPushString(str);
            }
            code.emitPushInt(i);
            code.emitLoad(variable2);
            code.emitInvokeSpecial(z ? initWrongTypeProcMethod : initWrongTypeStringMethod);
            if (scope != null) {
                code.popScope();
            }
            code.emitThrow();
            if (isInTry) {
                label3.define(code);
            } else {
                code.endFragment(beginFragment);
            }
        } else if (scope != null) {
            code.popScope();
        }
    }
}
