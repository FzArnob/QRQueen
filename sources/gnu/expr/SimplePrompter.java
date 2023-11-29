package gnu.expr;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

/* compiled from: Language */
class SimplePrompter extends Procedure1 {
    public String prefix = "[";
    public String suffix = "] ";

    SimplePrompter() {
    }

    public Object apply1(Object obj) {
        int lineNumber;
        if (!(obj instanceof InPort) || (lineNumber = ((InPort) obj).getLineNumber() + 1) < 0) {
            return this.suffix;
        }
        return this.prefix + lineNumber + this.suffix;
    }
}
