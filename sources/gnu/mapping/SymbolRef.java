package gnu.mapping;

import java.lang.ref.WeakReference;

/* compiled from: Namespace */
class SymbolRef extends WeakReference {
    SymbolRef next;

    SymbolRef(Symbol symbol, Namespace namespace) {
        super(symbol);
    }

    /* access modifiers changed from: package-private */
    public Symbol getSymbol() {
        return (Symbol) get();
    }

    public String toString() {
        return "SymbolRef[" + getSymbol() + "]";
    }
}
