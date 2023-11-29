package kawa.lang;

import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.util.Stack;
import java.util.Vector;

public abstract class Syntax implements Printable, Named {
    Object name;

    public final String getName() {
        Object obj = this.name;
        if (obj == null) {
            return null;
        }
        return obj instanceof Symbol ? ((Symbol) obj).getName() : obj.toString();
    }

    public Object getSymbol() {
        return this.name;
    }

    public void setName(Object obj) {
        this.name = obj;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Syntax() {
    }

    public Syntax(Object obj) {
        setName(obj);
    }

    public Expression rewrite(Object obj, Translator translator) {
        throw new InternalError("rewrite method not defined");
    }

    public Expression rewriteForm(Object obj, Translator translator) {
        if (obj instanceof Pair) {
            return rewriteForm((Pair) obj, translator);
        }
        return translator.syntaxError("non-list form for " + this);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        return rewrite(pair.getCdr(), translator);
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        if (!scanForDefinitions(pair, translator.formStack, scopeExp, translator)) {
            Stack stack = translator.formStack;
            stack.add(new ErrorExp("syntax error expanding " + this));
        }
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        vector.addElement(pair);
        return true;
    }

    public void print(Consumer consumer) {
        consumer.write("#<syntax ");
        String name2 = getName();
        if (name2 == null) {
            name2 = "<unnamed>";
        }
        consumer.write(name2);
        consumer.write(62);
    }
}
