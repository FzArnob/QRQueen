package gnu.expr;

import com.microsoft.appcenter.Constants;
import gnu.mapping.OutPort;
import gnu.text.SourceMessages;

public class ErrorExp extends Expression {
    String message;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public ErrorExp(String str) {
        this.message = str;
    }

    public ErrorExp(String str, SourceMessages sourceMessages) {
        sourceMessages.error('e', str);
        this.message = str;
    }

    public ErrorExp(String str, Compilation compilation) {
        compilation.getMessages().error('e', str);
        this.message = str;
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Error", false, ")");
        outPort.writeSpaceLinear();
        outPort.print(this.message);
        outPort.endLogicalBlock(")");
    }

    public void compile(Compilation compilation, Target target) {
        throw new Error(compilation.getFileName() + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + compilation.getLineNumber() + ": internal error: compiling error expression: " + this.message);
    }
}
