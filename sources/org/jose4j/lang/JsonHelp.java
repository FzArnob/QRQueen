package org.jose4j.lang;

import androidx.core.os.EnvironmentCompat;
import java.util.List;
import java.util.Map;
import org.jose4j.jwt.IntDate;

public class JsonHelp {
    public static String getString(Map<String, Object> map, String str) {
        return (String) map.get(str);
    }

    public static String getStringChecked(Map<String, Object> map, String str) throws JoseException {
        Object obj = map.get(str);
        try {
            return (String) obj;
        } catch (ClassCastException unused) {
            throw new JoseException("'" + str + "' parameter was " + jsonTypeName(obj) + " type but is required to be a String.");
        }
    }

    public static List<String> getStringArray(Map<String, Object> map, String str) {
        return (List) map.get(str);
    }

    public static IntDate getIntDate(Map<String, Object> map, String str) {
        return IntDate.fromSeconds(getLong(map, str).longValue());
    }

    public static Long getLong(Map<String, ?> map, String str) {
        Object obj = map.get(str);
        if (obj != null) {
            return Long.valueOf(((Number) obj).longValue());
        }
        return null;
    }

    public static String jsonTypeName(Object obj) {
        if (obj instanceof Number) {
            return "Number";
        }
        if (obj instanceof Boolean) {
            return "Boolean";
        }
        if (obj instanceof List) {
            return "Array";
        }
        if (obj instanceof Map) {
            return "Object";
        }
        return obj instanceof String ? "String" : EnvironmentCompat.MEDIA_UNKNOWN;
    }
}
