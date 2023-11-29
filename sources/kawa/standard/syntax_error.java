package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class syntax_error extends Syntax {
    public static final syntax_error syntax_error;

    static {
        syntax_error syntax_error2 = new syntax_error();
        syntax_error = syntax_error2;
        syntax_error2.setName("%syntax-error");
    }

    public Expression rewrite(Object obj, Translator translator) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (i > 0) {
                stringBuffer.append(' ');
            }
            stringBuffer.append(pair.getCar());
            obj = pair.getCdr();
            i++;
        }
        if (obj != LList.Empty) {
            if (i > 0) {
                stringBuffer.append(' ');
            }
            stringBuffer.append(obj);
        }
        return translator.syntaxError(stringBuffer.toString());
    }

    public static Expression error(Object obj, Object[] objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (objArr == null || r1 == 0) {
            stringBuffer.append("invalid syntax");
        } else {
            for (Object append : objArr) {
                stringBuffer.append(append);
            }
        }
        Translator translator = (Translator) Compilation.getCurrent();
        if (translator != null) {
            Object pushPositionOf = translator.pushPositionOf(obj);
            try {
                return translator.syntaxError(stringBuffer.toString());
            } finally {
                translator.popPositionOf(pushPositionOf);
            }
        } else {
            throw new RuntimeException(stringBuffer.toString());
        }
    }
}
