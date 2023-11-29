package gnu.mapping;

public class LazyPropertyKey<T> extends PropertyKey<T> {
    public LazyPropertyKey(String str) {
        super(str);
    }

    public T get(PropertySet propertySet, T t) {
        T t2;
        T property = propertySet.getProperty(this, t);
        if (!(property instanceof String)) {
            return property;
        }
        String str = (String) property;
        int i = str.charAt(0) == '*' ? 1 : 0;
        int indexOf = str.indexOf(58);
        if (indexOf <= i || indexOf >= str.length() - 1) {
            throw new RuntimeException("lazy property " + this + " must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\"");
        }
        String substring = str.substring(i, indexOf);
        String substring2 = str.substring(indexOf + 1);
        try {
            Class<?> cls = Class.forName(substring, true, propertySet.getClass().getClassLoader());
            if (i == 0) {
                t2 = cls.getField(substring2).get((Object) null);
            } else {
                t2 = cls.getDeclaredMethod(substring2, new Class[]{Object.class}).invoke((Object) null, new Object[]{propertySet});
            }
            propertySet.setProperty(this, t2);
            return t2;
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            sb.append("lazy property ");
            sb.append(this);
            sb.append(" has specifier \"");
            sb.append(str);
            sb.append("\" but there is no such ");
            sb.append(i == 0 ? "field" : "method");
            throw new RuntimeException(sb.toString(), th);
        }
    }

    public void set(PropertySet propertySet, String str) {
        propertySet.setProperty(this, str);
    }
}
