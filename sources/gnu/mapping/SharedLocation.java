package gnu.mapping;

public class SharedLocation extends NamedLocation {
    int timestamp;

    public SharedLocation(Symbol symbol, Object obj, int i) {
        super(symbol, obj);
        this.timestamp = i;
    }

    public final synchronized Object get(Object obj) {
        if (this.base != null) {
            obj = this.base.get(obj);
        } else if (this.value != Location.UNBOUND) {
            obj = this.value;
        }
        return obj;
    }

    public synchronized boolean isBound() {
        return this.base != null ? this.base.isBound() : this.value != Location.UNBOUND;
    }

    public final synchronized void set(Object obj) {
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
