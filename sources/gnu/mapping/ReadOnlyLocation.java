package gnu.mapping;

public class ReadOnlyLocation extends ConstrainedLocation {
    public boolean isConstant() {
        return true;
    }

    public static ReadOnlyLocation make(Location location) {
        ReadOnlyLocation readOnlyLocation = new ReadOnlyLocation();
        readOnlyLocation.base = location;
        return readOnlyLocation;
    }

    /* access modifiers changed from: protected */
    public Object coerce(Object obj) {
        StringBuffer stringBuffer = new StringBuffer("attempt to modify read-only location");
        Symbol keySymbol = getKeySymbol();
        if (keySymbol != null) {
            stringBuffer.append(": ");
            stringBuffer.append(keySymbol);
        }
        throw new IllegalStateException(stringBuffer.toString());
    }
}
