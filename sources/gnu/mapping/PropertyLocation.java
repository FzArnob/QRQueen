package gnu.mapping;

import gnu.lists.LList;
import gnu.lists.Pair;

public class PropertyLocation extends Location {
    Pair pair;

    public boolean isBound() {
        return true;
    }

    public final Object get(Object obj) {
        return this.pair.getCar();
    }

    public final void set(Object obj) {
        this.pair.setCar(obj);
    }

    public static Object getPropertyList(Object obj, Environment environment) {
        return environment.get(Symbol.PLIST, obj, LList.Empty);
    }

    public static Object getPropertyList(Object obj) {
        return Environment.getCurrent().get(Symbol.PLIST, obj, LList.Empty);
    }

    public static void setPropertyList(Object obj, Object obj2, Environment environment) {
        PropertyLocation propertyLocation;
        synchronized (environment) {
            Location lookup = environment.lookup(Symbol.PLIST, obj);
            if (obj instanceof Symbol) {
                Symbol symbol = (Symbol) obj;
                Object obj3 = lookup.get(LList.Empty);
                while (obj3 instanceof Pair) {
                    Pair pair2 = (Pair) obj3;
                    Object car = pair2.getCar();
                    if (plistGet(obj2, car, (Object) null) != null) {
                        environment.remove(symbol, car);
                    }
                    obj3 = ((Pair) pair2.getCdr()).getCdr();
                }
                Object obj4 = obj2;
                while (obj4 instanceof Pair) {
                    Pair pair3 = (Pair) obj4;
                    Object car2 = pair3.getCar();
                    Location lookup2 = environment.lookup(symbol, car2);
                    if (lookup2 != null) {
                        Location base = lookup2.getBase();
                        if (base instanceof PropertyLocation) {
                            propertyLocation = (PropertyLocation) base;
                            Pair pair4 = (Pair) pair3.getCdr();
                            propertyLocation.pair = pair4;
                            obj4 = pair4.getCdr();
                        }
                    }
                    propertyLocation = new PropertyLocation();
                    environment.addLocation(symbol, car2, propertyLocation);
                    Pair pair42 = (Pair) pair3.getCdr();
                    propertyLocation.pair = pair42;
                    obj4 = pair42.getCdr();
                }
            }
            lookup.set(obj2);
        }
    }

    public static void setPropertyList(Object obj, Object obj2) {
        setPropertyList(obj, obj2, Environment.getCurrent());
    }

    public static Object getProperty(Object obj, Object obj2, Object obj3, Environment environment) {
        if (!(obj instanceof Symbol)) {
            if (!(obj instanceof String)) {
                return plistGet(environment.get(Symbol.PLIST, obj, LList.Empty), obj2, obj3);
            }
            obj = Namespace.getDefaultSymbol((String) obj);
        }
        return environment.get((Symbol) obj, obj2, obj3);
    }

    public static Object getProperty(Object obj, Object obj2, Object obj3) {
        return getProperty(obj, obj2, obj3, Environment.getCurrent());
    }

    public static void putProperty(Object obj, Object obj2, Object obj3, Environment environment) {
        if (!(obj instanceof Symbol)) {
            if (obj instanceof String) {
                obj = Namespace.getDefaultSymbol((String) obj);
            } else {
                Location location = environment.getLocation(Symbol.PLIST, obj);
                location.set(plistPut(location.get(LList.Empty), obj2, obj3));
                return;
            }
        }
        Symbol symbol = (Symbol) obj;
        Location lookup = environment.lookup(symbol, obj2);
        if (lookup != null) {
            Location base = lookup.getBase();
            if (base instanceof PropertyLocation) {
                ((PropertyLocation) base).set(obj3);
                return;
            }
        }
        Location location2 = environment.getLocation(Symbol.PLIST, obj);
        Pair pair2 = new Pair(obj3, location2.get(LList.Empty));
        location2.set(new Pair(obj2, pair2));
        PropertyLocation propertyLocation = new PropertyLocation();
        propertyLocation.pair = pair2;
        environment.addLocation(symbol, obj2, propertyLocation);
    }

    public static void putProperty(Object obj, Object obj2, Object obj3) {
        putProperty(obj, obj2, obj3, Environment.getCurrent());
    }

    public static boolean removeProperty(Object obj, Object obj2, Environment environment) {
        Location lookup = environment.lookup(Symbol.PLIST, obj);
        if (lookup == null) {
            return false;
        }
        Object obj3 = lookup.get(LList.Empty);
        if (!(obj3 instanceof Pair)) {
            return false;
        }
        Pair pair2 = (Pair) obj3;
        Pair pair3 = null;
        while (pair2.getCar() != obj2) {
            Object cdr = pair2.getCdr();
            if (!(cdr instanceof Pair)) {
                return false;
            }
            Pair pair4 = (Pair) cdr;
            pair3 = pair2;
            pair2 = pair4;
        }
        Object cdr2 = ((Pair) pair2.getCdr()).getCdr();
        if (pair3 == null) {
            lookup.set(cdr2);
        } else {
            pair3.setCdr(cdr2);
        }
        if (!(obj instanceof Symbol)) {
            return true;
        }
        environment.remove((Symbol) obj, obj2);
        return true;
    }

    public static boolean removeProperty(Object obj, Object obj2) {
        return removeProperty(obj, obj2, Environment.getCurrent());
    }

    public static Object plistGet(Object obj, Object obj2, Object obj3) {
        while (obj instanceof Pair) {
            Pair pair2 = (Pair) obj;
            if (pair2.getCar() == obj2) {
                return ((Pair) pair2.getCdr()).getCar();
            }
        }
        return obj3;
    }

    public static Object plistPut(Object obj, Object obj2, Object obj3) {
        Object obj4 = obj;
        while (obj4 instanceof Pair) {
            Pair pair2 = (Pair) obj4;
            Pair pair3 = (Pair) pair2.getCdr();
            if (pair2.getCar() == obj2) {
                pair3.setCar(obj3);
                return obj;
            }
            obj4 = pair3.getCdr();
        }
        return new Pair(obj2, new Pair(obj3, obj));
    }

    public static Object plistRemove(Object obj, Object obj2) {
        Pair pair2 = null;
        Object obj3 = obj;
        while (obj3 instanceof Pair) {
            Pair pair3 = (Pair) obj3;
            Pair pair4 = (Pair) pair3.getCdr();
            Object cdr = pair4.getCdr();
            if (pair3.getCar() != obj2) {
                pair2 = pair4;
                obj3 = cdr;
            } else if (pair2 == null) {
                return cdr;
            } else {
                pair2.setCdr(cdr);
                return obj;
            }
        }
        return obj;
    }
}
