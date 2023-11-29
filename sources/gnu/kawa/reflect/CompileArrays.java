package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class CompileArrays implements Inlineable {
    public char code;
    Procedure proc;

    public CompileArrays(Procedure procedure, char c) {
        this.proc = procedure;
        this.code = c;
    }

    public static CompileArrays getForArrayGet(Object obj) {
        return new CompileArrays((Procedure) obj, 'G');
    }

    public static CompileArrays getForArraySet(Object obj) {
        return new CompileArrays((Procedure) obj, 'S');
    }

    public static CompileArrays getForArrayLength(Object obj) {
        return new CompileArrays((Procedure) obj, 'L');
    }

    public static CompileArrays getForArrayNew(Object obj) {
        return new CompileArrays((Procedure) obj, 'N');
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        char c = this.code;
        if (c == 'G') {
            compileArrayGet((ArrayGet) this.proc, applyExp, compilation, target);
        } else if (c == 'N') {
            compileArrayNew((ArrayNew) this.proc, applyExp, compilation, target);
        } else if (c != 'S') {
            compileArrayLength((ArrayLength) this.proc, applyExp, compilation, target);
        } else {
            compileArraySet((ArraySet) this.proc, applyExp, compilation, target);
        }
    }

    public static void compileArrayGet(ArrayGet arrayGet, ApplyExp applyExp, Compilation compilation, Target target) {
        Type type = arrayGet.element_type;
        Expression[] args = applyExp.getArgs();
        args[0].compile(compilation, (Type) ArrayType.make(type));
        args[1].compile(compilation, (Type) Type.int_type);
        compilation.getCode().emitArrayLoad(type);
        target.compileFromStack(compilation, type);
    }

    public static void compileArraySet(ArraySet arraySet, ApplyExp applyExp, Compilation compilation, Target target) {
        Type type = arraySet.element_type;
        Expression[] args = applyExp.getArgs();
        args[0].compile(compilation, (Type) ArrayType.make(type));
        args[1].compile(compilation, (Type) Type.int_type);
        args[2].compile(compilation, type);
        compilation.getCode().emitArrayStore(type);
        compilation.compileConstant(Values.empty, target);
    }

    public static void compileArrayNew(ArrayNew arrayNew, ApplyExp applyExp, Compilation compilation, Target target) {
        Type type = arrayNew.element_type;
        applyExp.getArgs()[0].compile(compilation, (Type) Type.intType);
        compilation.getCode().emitNewArray(type.getImplementationType());
        target.compileFromStack(compilation, ArrayType.make(type));
    }

    public static void compileArrayLength(ArrayLength arrayLength, ApplyExp applyExp, Compilation compilation, Target target) {
        applyExp.getArgs()[0].compile(compilation, (Type) ArrayType.make(arrayLength.element_type));
        compilation.getCode().emitArrayLength();
        target.compileFromStack(compilation, LangPrimType.intType);
    }

    public static Expression validateArrayNew(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        applyExp.setType(ArrayType.make(((ArrayNew) procedure).element_type));
        return applyExp;
    }

    public static Expression validateArrayLength(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        applyExp.setType(LangPrimType.intType);
        return applyExp;
    }

    public static Expression validateArrayGet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        applyExp.setType(((ArrayGet) procedure).element_type);
        return applyExp;
    }

    public static Expression validateArraySet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        applyExp.setType(Type.void_type);
        return applyExp;
    }
}
