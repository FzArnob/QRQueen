package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.OptionList;
import com.google.appinventor.components.runtime.errors.DispatchableError;
import gnu.mapping.Symbol;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class TypeUtil {
    private TypeUtil() {
    }

    public static <T> T cast(Object obj, Class<T> cls, String str) {
        if (obj == null) {
            return null;
        }
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        throw new DispatchableError(ErrorMessages.ERROR_INVALID_TYPE, obj.getClass().getSimpleName(), str);
    }

    public static <T> T castNotNull(Object obj, Class<T> cls, String str) {
        if (obj != null) {
            return cast(obj, cls, str);
        }
        throw new DispatchableError(ErrorMessages.ERROR_INVALID_TYPE, "null", str);
    }

    public static <T> OptionList<T> castToEnum(T t, Symbol symbol) {
        String name = symbol.getName();
        if (name.endsWith("Enum")) {
            name = name.substring(0, name.length() - 4);
        }
        try {
            Class<?> cls = Class.forName(name);
            if (OptionList.class.isAssignableFrom(cls)) {
                for (Method method : cls.getMethods()) {
                    if ("fromUnderlyingValue".equals(method.getName())) {
                        return (OptionList) method.invoke(cls, new Object[]{t});
                    }
                }
                return null;
            }
            throw new IllegalArgumentException(name + " does not identify an OptionList type.");
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }
}
