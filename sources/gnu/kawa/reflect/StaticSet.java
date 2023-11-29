package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class StaticSet extends Procedure1 implements Inlineable {
    ClassType ctype;
    Field field;
    String fname;
    java.lang.reflect.Field reflectField;

    StaticSet(Class cls, String str) {
        this.ctype = (ClassType) Type.make(cls);
        this.fname = str;
    }

    public StaticSet(ClassType classType, String str, Type type, int i) {
        this.ctype = classType;
        this.fname = str;
        Field field2 = classType.getField(str);
        this.field = field2;
        if (field2 == null) {
            this.field = classType.addField(str, type, i);
        }
    }

    public Object apply1(Object obj) {
        if (this.reflectField == null) {
            Class reflectClass = this.ctype.getReflectClass();
            try {
                this.reflectField = reflectClass.getField(this.fname);
            } catch (NoSuchFieldException unused) {
                throw new RuntimeException("no such field " + this.fname + " in " + reflectClass.getName());
            }
        }
        try {
            this.reflectField.set((Object) null, obj);
            return Values.empty;
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("illegal access for field " + this.fname);
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        if (this.field == null) {
            Field field2 = this.ctype.getField(this.fname);
            this.field = field2;
            if (field2 == null) {
                this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers());
            }
        }
        applyExp.getArgs()[0].compile(compilation, this.field.getType());
        compilation.getCode().emitPutStatic(this.field);
        compilation.compileConstant(Values.empty, target);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.voidType;
    }
}
