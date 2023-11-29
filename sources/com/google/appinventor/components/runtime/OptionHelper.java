package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.OptionList;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class OptionHelper {
    private static final Map<String, Map<String, Method>> l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = new HashMap();

    public static <T> Object optionListFromValue(Component component, String str, T t) {
        Options options;
        Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(component, str);
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null || (options = (Options) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAnnotation(Options.class)) == null) {
            return t;
        }
        Class<? extends OptionList<?>> value = options.value();
        try {
            Object invoke = value.getMethod("fromUnderlyingValue", new Class[]{t.getClass()}).invoke(value, new Object[]{t});
            return invoke != null ? invoke : t;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    public static Object[] optionListsFromValues(Component component, String str, Object... objArr) {
        Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (objArr.length == 0 || (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(component, str)) == null) {
            return objArr;
        }
        int i = 0;
        for (Annotation[] annotationArr : hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getParameterAnnotations()) {
            int length = annotationArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                Annotation annotation = annotationArr[i2];
                if (annotation.annotationType() == Options.class) {
                    Class<? extends OptionList<?>> value = ((Options) annotation).value();
                    try {
                        Object invoke = value.getMethod("fromUnderlyingValue", new Class[]{objArr[i].getClass()}).invoke(value, new Object[]{objArr[i]});
                        if (invoke != null) {
                            objArr[i] = invoke;
                        }
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                    }
                } else {
                    i2++;
                }
            }
            i++;
        }
        return objArr;
    }

    private static Method hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Component component, String str) {
        Class<?> cls = component.getClass();
        String simpleName = cls.getSimpleName();
        Map<String, Map<String, Method>> map = l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
        Map<String, Method> map2 = map.get(simpleName);
        if (map2 == null) {
            map2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(cls);
            map.put(simpleName, map2);
        }
        return map2.get(str);
    }

    private static Map<String, Method> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Class<?> cls) {
        HashMap hashMap = new HashMap();
        for (Method method : cls.getMethods()) {
            if ((method.getModifiers() & 1) != 0) {
                String name = method.getName();
                if (((SimpleEvent) method.getAnnotation(SimpleEvent.class)) != null) {
                    hashMap.put(name, method);
                } else if (method.getReturnType() != Void.TYPE) {
                    if (((SimpleFunction) method.getAnnotation(SimpleFunction.class)) != null) {
                        hashMap.put(name, method);
                    } else if (((SimpleProperty) method.getAnnotation(SimpleProperty.class)) != null) {
                        hashMap.put(name, method);
                    }
                }
            }
        }
        return hashMap;
    }
}
