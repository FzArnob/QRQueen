package gnu.kawa.functions;

import gnu.expr.QuoteExp;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;

public class ConstantFunction0 extends Procedure0 {
    final QuoteExp constant;
    final Object value;

    public ConstantFunction0(String str, Object obj) {
        this(str, QuoteExp.getInstance(obj));
    }

    public ConstantFunction0(String str, QuoteExp quoteExp) {
        super(str);
        this.value = quoteExp.getValue();
        this.constant = quoteExp;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConstantFunction0");
    }

    public Object apply0() {
        return this.value;
    }
}
