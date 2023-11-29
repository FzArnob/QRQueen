package gnu.expr;

import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;

public class BuiltinEnvironment extends Environment {
    static final BuiltinEnvironment instance;

    static {
        BuiltinEnvironment builtinEnvironment = new BuiltinEnvironment();
        instance = builtinEnvironment;
        builtinEnvironment.setName("language-builtins");
    }

    private BuiltinEnvironment() {
    }

    public static BuiltinEnvironment getInstance() {
        return instance;
    }

    public Environment getLangEnvironment() {
        Language defaultLanguage = Language.getDefaultLanguage();
        if (defaultLanguage == null) {
            return null;
        }
        return defaultLanguage.getLangEnvironment();
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i) {
        Language defaultLanguage;
        if (obj == ThreadLocation.ANONYMOUS || (defaultLanguage = Language.getDefaultLanguage()) == null) {
            return null;
        }
        return defaultLanguage.lookupBuiltin(symbol, obj, i);
    }

    public NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean z) {
        throw new RuntimeException();
    }

    public void define(Symbol symbol, Object obj, Object obj2) {
        throw new RuntimeException();
    }

    public LocationEnumeration enumerateLocations() {
        return getLangEnvironment().enumerateLocations();
    }

    public LocationEnumeration enumerateAllLocations() {
        return getLangEnvironment().enumerateAllLocations();
    }

    /* access modifiers changed from: protected */
    public boolean hasMoreElements(LocationEnumeration locationEnumeration) {
        throw new RuntimeException();
    }

    public NamedLocation addLocation(Symbol symbol, Object obj, Location location) {
        throw new RuntimeException();
    }
}
