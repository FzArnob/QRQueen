package gnu.ecmascript;

public class Convert {
    public static double toNumber(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? 1.0d : 0.0d;
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj).doubleValue();
            } catch (NumberFormatException unused) {
            }
        }
        return Double.NaN;
    }

    public static double toInteger(double d) {
        if (Double.isNaN(d)) {
            return 0.0d;
        }
        return d < 0.0d ? Math.ceil(d) : Math.floor(d);
    }

    public static double toInteger(Object obj) {
        return toInteger(toNumber(obj));
    }

    public int toInt32(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            return 0;
        }
        return (int) d;
    }

    public int toInt32(Object obj) {
        return toInt32(toNumber(obj));
    }
}
