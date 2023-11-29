package gnu.mapping;

public abstract class IndirectableLocation extends Location {
    protected static final Object DIRECT_ON_SET = new String("(direct-on-set)");
    protected static final Object INDIRECT_FLUIDS = new String("(indirect-fluids)");
    protected Location base;
    protected Object value;

    public Symbol getKeySymbol() {
        Location location = this.base;
        if (location != null) {
            return location.getKeySymbol();
        }
        return null;
    }

    public Object getKeyProperty() {
        Location location = this.base;
        if (location != null) {
            return location.getKeyProperty();
        }
        return null;
    }

    public boolean isConstant() {
        Location location = this.base;
        return location != null && location.isConstant();
    }

    public Location getBase() {
        Location location = this.base;
        return location == null ? this : location.getBase();
    }

    public Location getBaseForce() {
        Location location = this.base;
        return location == null ? new PlainLocation(getKeySymbol(), getKeyProperty(), this.value) : location;
    }

    public void setBase(Location location) {
        this.base = location;
        this.value = null;
    }

    public void setAlias(Location location) {
        this.base = location;
        this.value = INDIRECT_FLUIDS;
    }

    public void undefine() {
        this.base = null;
        this.value = UNBOUND;
    }

    public Environment getEnvironment() {
        Location location = this.base;
        if (location instanceof NamedLocation) {
            return ((NamedLocation) location).getEnvironment();
        }
        return null;
    }
}
