package gnu.mapping;

public class PlainLocation extends NamedLocation {
    public PlainLocation(Symbol symbol, Object obj) {
        super(symbol, obj);
    }

    public PlainLocation(Symbol symbol, Object obj, Object obj2) {
        super(symbol, obj);
        this.value = obj2;
    }

    public final Object get(Object obj) {
        if (this.base != null) {
            return this.base.get(obj);
        }
        return this.value == Location.UNBOUND ? obj : this.value;
    }

    public boolean isBound() {
        if (this.base != null) {
            return this.base.isBound();
        }
        return this.value != Location.UNBOUND;
    }

    public final void set(Object obj) {
        if (this.base == null) {
            this.value = obj;
        } else if (this.value == DIRECT_ON_SET) {
            this.base = null;
            this.value = obj;
        } else if (this.base.isConstant()) {
            getEnvironment().put(getKeySymbol(), getKeyProperty(), obj);
        } else {
            this.base.set(obj);
        }
    }
}
