package gnu.mapping;

public class ConstrainedLocation extends Location {
    protected Location base;
    protected Procedure converter;

    public static ConstrainedLocation make(Location location, Procedure procedure) {
        ConstrainedLocation constrainedLocation = new ConstrainedLocation();
        constrainedLocation.base = location;
        constrainedLocation.converter = procedure;
        return constrainedLocation;
    }

    public Symbol getKeySymbol() {
        return this.base.getKeySymbol();
    }

    public Object getKeyProperty() {
        return this.base.getKeyProperty();
    }

    public boolean isConstant() {
        return this.base.isConstant();
    }

    public final Object get(Object obj) {
        return this.base.get(obj);
    }

    public boolean isBound() {
        return this.base.isBound();
    }

    /* access modifiers changed from: protected */
    public Object coerce(Object obj) {
        try {
            return this.converter.apply1(obj);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public final void set(Object obj) {
        this.base.set(coerce(obj));
    }

    public Object setWithSave(Object obj) {
        return this.base.setWithSave(coerce(obj));
    }

    public void setRestore(Object obj) {
        this.base.setRestore(obj);
    }
}
