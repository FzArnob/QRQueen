package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure0;

public class StaticGet extends Procedure0 implements Inlineable {
    ClassType ctype;
    Field field;
    String fname;
    java.lang.reflect.Field reflectField;

    StaticGet(Class cls, String str) {
        this.ctype = (ClassType) Type.make(cls);
        this.fname = str;
    }

    public StaticGet(ClassType classType, String str, Type type, int i) {
        this.ctype = classType;
        this.fname = str;
        Field field2 = classType.getField(str);
        this.field = field2;
        if (field2 == null) {
            this.field = classType.addField(str, type, i);
        }
    }

    public Object apply0() {
        if (this.reflectField == null) {
            Class reflectClass = this.ctype.getReflectClass();
            try {
                this.reflectField = reflectClass.getField(this.fname);
            } catch (NoSuchFieldException unused) {
                throw new RuntimeException("no such field " + this.fname + " in " + reflectClass.getName());
            }
        }
        try {
            return this.reflectField.get((Object) null);
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("illegal access for field " + this.fname);
        }
    }

    private Field getField() {
        if (this.field == null) {
            Field field2 = this.ctype.getField(this.fname);
            this.field = field2;
            if (field2 == null) {
                this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers());
            }
        }
        return this.field;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        getField();
        compilation.getCode().emitGetStatic(this.field);
        target.compileFromStack(compilation, this.field.getType());
    }

    public Type getReturnType(Expression[] expressionArr) {
        return getField().getType();
    }
}
