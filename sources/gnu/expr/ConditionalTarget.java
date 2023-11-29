package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Type;

public class ConditionalTarget extends Target {
    public Label ifFalse;
    public Label ifTrue;
    Language language;
    public boolean trueBranchComesFirst = true;

    public ConditionalTarget(Label label, Label label2, Language language2) {
        this.ifTrue = label;
        this.ifFalse = label2;
        this.language = language2;
    }

    public Type getType() {
        return Type.booleanType;
    }

    public void compileFromStack(Compilation compilation, Type type) {
        CodeAttr code = compilation.getCode();
        char charAt = type.getSignature().charAt(0);
        if (charAt == 'D') {
            code.emitPushDouble(0.0d);
        } else if (charAt == 'F') {
            code.emitPushFloat(0.0f);
        } else if (charAt == 'J') {
            code.emitPushLong(0);
        } else if (charAt == 'L' || charAt == '[') {
            Language language2 = this.language;
            compilation.compileConstant(language2 == null ? Boolean.FALSE : language2.booleanObject(false));
        } else if (this.trueBranchComesFirst) {
            code.emitGotoIfIntEqZero(this.ifFalse);
            code.emitGoto(this.ifTrue);
            return;
        } else {
            code.emitGotoIfIntNeZero(this.ifTrue);
            code.emitGoto(this.ifFalse);
            return;
        }
        if (this.trueBranchComesFirst) {
            code.emitGotoIfEq(this.ifFalse);
        } else {
            code.emitGotoIfNE(this.ifTrue);
        }
        emitGotoFirstBranch(code);
    }

    public final void emitGotoFirstBranch(CodeAttr codeAttr) {
        codeAttr.emitGoto(this.trueBranchComesFirst ? this.ifTrue : this.ifFalse);
    }
}
