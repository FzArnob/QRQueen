package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class InstanceOf extends Procedure2 implements Inlineable {
    static Method instanceMethod;
    static ClassType typeType;
    protected Language language;

    public InstanceOf(Language language2) {
        this.language = language2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyInstanceOf");
    }

    public InstanceOf(Language language2, String str) {
        this(language2);
        setName(str);
    }

    public Object apply2(Object obj, Object obj2) {
        Type asType = this.language.asType(obj2);
        if (asType instanceof PrimType) {
            asType = ((PrimType) asType).boxedType();
        }
        return this.language.booleanObject(asType.isInstance(obj));
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Type type;
        Expression[] args = applyExp.getArgs();
        CodeAttr code = compilation.getCode();
        Expression expression = args[1];
        if (expression instanceof QuoteExp) {
            try {
                type = this.language.asType(((QuoteExp) expression).getValue());
            } catch (Exception unused) {
                compilation.error('w', "unknown type spec: " + null);
                type = null;
            }
        } else {
            type = this.language.getTypeFor(expression);
        }
        if (type != null) {
            if (type instanceof PrimType) {
                type = ((PrimType) type).boxedType();
            }
            args[0].compile(compilation, Target.pushObject);
            if (type instanceof TypeValue) {
                ((TypeValue) type).emitIsInstance((Variable) null, compilation, target);
                return;
            } else {
                type.emitIsInstance(code);
                compilation.usedClass(type);
            }
        } else {
            if (typeType == null) {
                ClassType make = ClassType.make("gnu.bytecode.Type");
                typeType = make;
                instanceMethod = make.addMethod("isInstance", Compilation.apply1args, (Type) Type.boolean_type, 1);
            }
            args[1].compile(compilation, (Type) typeType);
            args[0].compile(compilation, Target.pushObject);
            code.emitInvokeVirtual(instanceMethod);
        }
        target.compileFromStack(compilation, this.language.getTypeFor(Boolean.TYPE));
    }

    public Type getReturnType(Expression[] expressionArr) {
        return this.language.getTypeFor(Boolean.TYPE);
    }

    public static void emitIsInstance(TypeValue typeValue, Variable variable, Compilation compilation, Target target) {
        CodeAttr code = compilation.getCode();
        ConditionalTarget conditionalTarget = null;
        typeValue.emitTestIf((Variable) null, (Declaration) null, compilation);
        if (target instanceof ConditionalTarget) {
            conditionalTarget = (ConditionalTarget) target;
            code.emitGoto(conditionalTarget.ifTrue);
        } else {
            code.emitPushInt(1);
        }
        code.emitElse();
        if (conditionalTarget != null) {
            code.emitGoto(conditionalTarget.ifFalse);
        } else {
            code.emitPushInt(0);
        }
        code.emitFi();
        if (conditionalTarget == null) {
            target.compileFromStack(compilation, compilation.getLanguage().getTypeFor(Boolean.TYPE));
        }
    }
}
