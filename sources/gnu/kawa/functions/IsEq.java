package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.mapping.Procedure2;

public class IsEq extends Procedure2 implements Inlineable {
    Language language;

    public boolean apply(Object obj, Object obj2) {
        return obj == obj2;
    }

    public IsEq(Language language2, String str) {
        this.language = language2;
        setName(str);
    }

    public Object apply2(Object obj, Object obj2) {
        return this.language.booleanObject(obj == obj2);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        compile(applyExp.getArgs(), compilation, target, this.language);
    }

    public static void compile(Expression[] expressionArr, Compilation compilation, Target target, Language language2) {
        Type type;
        CodeAttr code = compilation.getCode();
        expressionArr[0].compile(compilation, Target.pushObject);
        expressionArr[1].compile(compilation, Target.pushObject);
        if (target instanceof ConditionalTarget) {
            ConditionalTarget conditionalTarget = (ConditionalTarget) target;
            if (conditionalTarget.trueBranchComesFirst) {
                code.emitGotoIfNE(conditionalTarget.ifFalse);
            } else {
                code.emitGotoIfEq(conditionalTarget.ifTrue);
            }
            conditionalTarget.emitGotoFirstBranch(code);
            return;
        }
        code.emitIfEq();
        if (target.getType() instanceof ClassType) {
            Object booleanObject = language2.booleanObject(true);
            Object booleanObject2 = language2.booleanObject(false);
            compilation.compileConstant(booleanObject, Target.pushObject);
            code.emitElse();
            compilation.compileConstant(booleanObject2, Target.pushObject);
            if (!(booleanObject instanceof Boolean) || !(booleanObject2 instanceof Boolean)) {
                type = Type.pointer_type;
            } else {
                type = Compilation.scmBooleanType;
            }
        } else {
            code.emitPushInt(1);
            code.emitElse();
            code.emitPushInt(0);
            type = language2.getTypeFor(Boolean.TYPE);
        }
        code.emitFi();
        target.compileFromStack(compilation, type);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return this.language.getTypeFor(Boolean.TYPE);
    }
}
