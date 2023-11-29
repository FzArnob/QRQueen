package gnu.expr;

import gnu.mapping.OutPort;

public class LangExp extends Expression {
    Object hook;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public Object getLangValue() {
        return this.hook;
    }

    public void setLangValue(Object obj) {
        this.hook = obj;
    }

    public LangExp() {
    }

    public LangExp(Object obj) {
        this.hook = obj;
    }

    public void print(OutPort outPort) {
        outPort.print("(LangExp ???)");
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitLangExp(this, d);
    }

    public void compile(Compilation compilation, Target target) {
        throw new RuntimeException("compile called on LangExp");
    }
}
