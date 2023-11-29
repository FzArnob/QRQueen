package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;

public class GetFieldProc extends Procedure1 implements Inlineable {
    ClassType ctype;
    Field field;

    public GetFieldProc(Class cls, String str) {
        this((ClassType) Type.make(cls), str);
    }

    public GetFieldProc(ClassType classType, String str) {
        this.ctype = classType;
        this.field = Field.searchField(classType.getFields(), str);
    }

    public GetFieldProc(ClassType classType, String str, Type type, int i) {
        this.ctype = classType;
        Field field2 = classType.getField(str);
        this.field = field2;
        if (field2 == null) {
            this.field = classType.addField(str, type, i);
        }
    }

    public Object apply1(Object obj) {
        try {
            return this.field.getReflectField().get(obj);
        } catch (NoSuchFieldException unused) {
            throw new RuntimeException("no such field " + this.field.getSourceName() + " in " + this.ctype.getName());
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("illegal access for field " + this.field.getSourceName());
        }
    }

    private Field getField() {
        return this.field;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        if (this.ctype.getReflectClass().getClassLoader() instanceof ArrayClassLoader) {
            ApplyExp.compile(applyExp, compilation, target);
            return;
        }
        applyExp.getArgs()[0].compile(compilation, (Type) this.ctype);
        compilation.getCode().emitGetField(this.field);
        target.compileFromStack(compilation, this.field.getType());
    }

    public Type getReturnType(Expression[] expressionArr) {
        return getField().getType();
    }
}
