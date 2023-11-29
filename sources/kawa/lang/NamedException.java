package kawa.lang;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class NamedException extends RuntimeException {
    Object[] args;
    Symbol name;

    public NamedException(Symbol symbol, Object[] objArr) {
        this.name = symbol;
        this.args = objArr;
    }

    public void checkMatch(Object obj) {
        if (obj != this.name && obj != Boolean.TRUE) {
            throw this;
        }
    }

    public Object applyHandler(Object obj, Procedure procedure) throws Throwable {
        checkMatch(obj);
        return procedure.applyN(this.args);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#<ERROR");
        Object[] objArr = this.args;
        int length = objArr.length;
        int i = 0;
        if (length > 1 && objArr[0] == "misc-error") {
            i = 1;
        }
        while (i < length) {
            stringBuffer.append(' ');
            stringBuffer.append(this.args[i]);
            i++;
        }
        stringBuffer.append(">");
        return stringBuffer.toString();
    }
}
