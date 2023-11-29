package gnu.expr;

import gnu.mapping.Symbol;

public abstract class AccessExp extends Expression {
    Declaration binding;
    private Declaration context;
    Object symbol;

    public String string_name() {
        return this.symbol.toString();
    }

    public final String getName() {
        Object obj = this.symbol;
        return obj instanceof Symbol ? ((Symbol) obj).getName() : obj.toString();
    }

    public final String getSimpleName() {
        Object obj = this.symbol;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Symbol)) {
            return null;
        }
        Symbol symbol2 = (Symbol) obj;
        if (symbol2.hasEmptyNamespace()) {
            return symbol2.getLocalName();
        }
        return null;
    }

    public final Object getSymbol() {
        return this.symbol;
    }

    public final Declaration getBinding() {
        return this.binding;
    }

    public final void setBinding(Declaration declaration) {
        this.binding = declaration;
    }

    public final Declaration contextDecl() {
        return this.context;
    }

    public final void setContextDecl(Declaration declaration) {
        this.context = declaration;
    }
}
