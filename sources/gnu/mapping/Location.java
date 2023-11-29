package gnu.mapping;

import java.io.PrintWriter;

public abstract class Location {
    public static final String UNBOUND = new String("(unbound)");

    public boolean entered() {
        return false;
    }

    public abstract Object get(Object obj);

    public Location getBase() {
        return this;
    }

    public Object getKeyProperty() {
        return null;
    }

    public Symbol getKeySymbol() {
        return null;
    }

    public boolean isConstant() {
        return false;
    }

    public abstract void set(Object obj);

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        Symbol keySymbol = getKeySymbol();
        stringBuffer.append('[');
        if (keySymbol != null) {
            stringBuffer.append(keySymbol);
            Object keyProperty = getKeyProperty();
            if (!(keyProperty == null || keyProperty == this)) {
                stringBuffer.append('/');
                stringBuffer.append(keyProperty);
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public final Object get() {
        String str = UNBOUND;
        Object obj = get(str);
        if (obj != str) {
            return obj;
        }
        throw new UnboundLocationException(this);
    }

    public void undefine() {
        set(UNBOUND);
    }

    public Object setWithSave(Object obj) {
        Object obj2 = get(UNBOUND);
        set(obj);
        return obj2;
    }

    public void setRestore(Object obj) {
        set(obj);
    }

    public boolean isBound() {
        String str = UNBOUND;
        return get(str) != str;
    }

    public final Object getValue() {
        return get((Object) null);
    }

    public final Object setValue(Object obj) {
        Object obj2 = get((Object) null);
        set(obj);
        return obj2;
    }

    public void print(PrintWriter printWriter) {
        printWriter.print("#<location ");
        Symbol keySymbol = getKeySymbol();
        if (keySymbol != null) {
            printWriter.print(keySymbol);
        }
        String str = UNBOUND;
        Object obj = get(str);
        if (obj != str) {
            printWriter.print(" -> ");
            printWriter.print(obj);
        } else {
            printWriter.print("(unbound)");
        }
        printWriter.print('>');
    }

    public static Location make(Object obj, String str) {
        ThreadLocation threadLocation = new ThreadLocation(str);
        threadLocation.setGlobal(obj);
        return threadLocation;
    }

    public static IndirectableLocation make(String str) {
        PlainLocation plainLocation = new PlainLocation(Namespace.EmptyNamespace.getSymbol(str.intern()), (Object) null);
        plainLocation.base = null;
        plainLocation.value = UNBOUND;
        return plainLocation;
    }

    public static IndirectableLocation make(Symbol symbol) {
        PlainLocation plainLocation = new PlainLocation(symbol, (Object) null);
        plainLocation.base = null;
        plainLocation.value = UNBOUND;
        return plainLocation;
    }
}
