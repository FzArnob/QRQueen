package gnu.mapping;

import java.util.Map;

public abstract class NamedLocation extends IndirectableLocation implements Map.Entry, EnvironmentKey {
    final Symbol name;
    NamedLocation next;
    final Object property;

    public boolean entered() {
        return this.next != null;
    }

    public Environment getEnvironment() {
        Environment environment;
        for (NamedLocation namedLocation = this; namedLocation != null; namedLocation = namedLocation.next) {
            if (namedLocation.name == null && (environment = (Environment) namedLocation.value) != null) {
                return environment;
            }
        }
        return super.getEnvironment();
    }

    public NamedLocation(NamedLocation namedLocation) {
        this.name = namedLocation.name;
        this.property = namedLocation.property;
    }

    public NamedLocation(Symbol symbol, Object obj) {
        this.name = symbol;
        this.property = obj;
    }

    public final Symbol getKeySymbol() {
        return this.name;
    }

    public final Object getKeyProperty() {
        return this.property;
    }

    public final boolean matches(EnvironmentKey environmentKey) {
        return Symbol.equals(environmentKey.getKeySymbol(), this.name) && environmentKey.getKeyProperty() == this.property;
    }

    public final boolean matches(Symbol symbol, Object obj) {
        return Symbol.equals(symbol, this.name) && obj == this.property;
    }

    public final Object getKey() {
        return this.property == null ? this.name : this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NamedLocation)) {
            return false;
        }
        NamedLocation namedLocation = (NamedLocation) obj;
        Symbol symbol = this.name;
        if (symbol != null ? !symbol.equals(namedLocation.name) : namedLocation.name != null) {
            return false;
        }
        if (this.property != namedLocation.property) {
            return false;
        }
        Object value = getValue();
        Object value2 = namedLocation.getValue();
        if (value == value2) {
            return true;
        }
        if (value == null || value2 == null) {
            return false;
        }
        return value.equals(value2);
    }

    public int hashCode() {
        int hashCode = this.name.hashCode() ^ System.identityHashCode(this.property);
        Object value = getValue();
        return value != null ? hashCode ^ value.hashCode() : hashCode;
    }

    public synchronized Object setWithSave(Object obj) {
        if (this.value == INDIRECT_FLUIDS) {
            return this.base.setWithSave(obj);
        }
        ThreadLocation makeAnonymous = ThreadLocation.makeAnonymous(this.name);
        makeAnonymous.global.base = this.base;
        makeAnonymous.global.value = this.value;
        setAlias(makeAnonymous);
        NamedLocation location = makeAnonymous.getLocation();
        location.value = obj;
        location.base = null;
        return makeAnonymous.global;
    }

    public synchronized void setRestore(Object obj) {
        if (this.value == INDIRECT_FLUIDS) {
            this.base.setRestore(obj);
        } else if (obj instanceof Location) {
            this.value = null;
            this.base = (Location) obj;
        } else {
            this.value = obj;
            this.base = null;
        }
    }
}
