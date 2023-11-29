package gnu.mapping;

import com.microsoft.appcenter.ingestion.models.CommonProperties;

public abstract class PropertySet implements Named {
    public static final Symbol nameKey = Namespace.EmptyNamespace.getSymbol(CommonProperties.NAME);
    private Object[] properties;

    public String getName() {
        Object property = getProperty(nameKey, (Object) null);
        if (property == null) {
            return null;
        }
        return property instanceof Symbol ? ((Symbol) property).getName() : property.toString();
    }

    public Object getSymbol() {
        return getProperty(nameKey, (Object) null);
    }

    public final void setSymbol(Object obj) {
        setProperty(nameKey, obj);
    }

    public final void setName(String str) {
        setProperty(nameKey, str);
    }

    public Object getProperty(Object obj, Object obj2) {
        Object[] objArr;
        Object[] objArr2 = this.properties;
        if (objArr2 != null) {
            int length = objArr2.length;
            do {
                length -= 2;
                if (length >= 0) {
                    objArr = this.properties;
                }
            } while (objArr[length] != obj);
            return objArr[length + 1];
        }
        return obj2;
    }

    public synchronized void setProperty(Object obj, Object obj2) {
        this.properties = setProperty(this.properties, obj, obj2);
    }

    public static Object[] setProperty(Object[] objArr, Object obj, Object obj2) {
        int i = 0;
        if (objArr == null) {
            objArr = new Object[10];
        } else {
            int i2 = -1;
            int length = objArr.length;
            while (true) {
                length -= 2;
                if (length >= 0) {
                    Object obj3 = objArr[length];
                    if (obj3 == obj) {
                        int i3 = length + 1;
                        Object obj4 = objArr[i3];
                        objArr[i3] = obj2;
                        return objArr;
                    } else if (obj3 == null) {
                        i2 = length;
                    }
                } else if (i2 < 0) {
                    int length2 = objArr.length;
                    Object[] objArr2 = new Object[(length2 * 2)];
                    System.arraycopy(objArr, 0, objArr2, 0, length2);
                    i = length2;
                    objArr = objArr2;
                } else {
                    i = i2;
                }
            }
        }
        objArr[i] = obj;
        objArr[i + 1] = obj2;
        return objArr;
    }

    public Object removeProperty(Object obj) {
        Object[] objArr = this.properties;
        if (objArr == null) {
            return null;
        }
        int length = objArr.length;
        do {
            length -= 2;
            if (length < 0) {
                return null;
            }
        } while (objArr[length] != obj);
        int i = length + 1;
        Object obj2 = objArr[i];
        objArr[length] = null;
        objArr[i] = null;
        return obj2;
    }
}
