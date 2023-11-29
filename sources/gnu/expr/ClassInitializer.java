package gnu.expr;

import gnu.bytecode.CodeAttr;

public class ClassInitializer extends Initializer {
    ClassExp cexp;

    public ClassInitializer(ClassExp classExp, Compilation compilation) {
        this.field = classExp.allocFieldFor(compilation);
        classExp.compileMembers(compilation);
        this.cexp = classExp;
        if (this.field.getStaticFlag()) {
            this.next = compilation.clinitChain;
            compilation.clinitChain = this;
            return;
        }
        LambdaExp owningLambda = classExp.getOwningLambda();
        this.next = owningLambda.initChain;
        owningLambda.initChain = this;
    }

    public void emit(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (!this.field.getStaticFlag()) {
            code.emitPushThis();
        }
        this.cexp.compilePushClass(compilation, Target.pushValue(Compilation.typeClassType));
        if (this.field.getStaticFlag()) {
            code.emitPutStatic(this.field);
        } else {
            code.emitPutField(this.field);
        }
    }
}
