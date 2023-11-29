package gnu.xquery.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

/* compiled from: XQuery */
class Prompter extends Procedure1 {
    Prompter() {
    }

    public Object apply1(Object obj) {
        InPort inPort = (InPort) obj;
        int lineNumber = inPort.getLineNumber() + 1;
        char c = inPort.readState;
        if (c == 10) {
            c = ' ';
        }
        if (c == '<') {
            return "<!--" + lineNumber + "-->";
        } else if (c == ':') {
            return "-(:" + lineNumber + "c:) ";
        } else {
            return "(: " + lineNumber + c + ":) ";
        }
    }
}
