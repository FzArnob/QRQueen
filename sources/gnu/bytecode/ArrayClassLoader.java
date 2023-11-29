package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

public class ArrayClassLoader extends ClassLoader {
    Hashtable cmap = new Hashtable(100);
    URL context;
    Hashtable map = new Hashtable(100);

    public ArrayClassLoader() {
    }

    public ArrayClassLoader(ClassLoader classLoader) {
        super(classLoader);
    }

    public URL getResourceContext() {
        return this.context;
    }

    public void setResourceContext(URL url) {
        this.context = url;
    }

    public ArrayClassLoader(byte[][] bArr) {
        int length = bArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                addClass("lambda" + length, bArr[length]);
            } else {
                return;
            }
        }
    }

    public ArrayClassLoader(String[] strArr, byte[][] bArr) {
        int length = bArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                addClass(strArr[length], bArr[length]);
            } else {
                return;
            }
        }
    }

    public void addClass(Class cls) {
        this.cmap.put(cls.getName(), cls);
    }

    public void addClass(String str, byte[] bArr) {
        this.map.put(str, bArr);
    }

    public void addClass(ClassType classType) {
        this.map.put(classType.getName(), classType);
    }

    public InputStream getResourceAsStream(String str) {
        InputStream resourceAsStream = super.getResourceAsStream(str);
        if (resourceAsStream != null || !str.endsWith(".class")) {
            return resourceAsStream;
        }
        Object obj = this.map.get(str.substring(0, str.length() - 6).replace('/', '.'));
        return obj instanceof byte[] ? new ByteArrayInputStream((byte[]) obj) : resourceAsStream;
    }

    /* access modifiers changed from: protected */
    public URL findResource(String str) {
        if (this.context != null) {
            try {
                URL url = new URL(this.context, str);
                url.openConnection().connect();
                return url;
            } catch (Throwable unused) {
            }
        }
        return super.findResource(str);
    }

    public Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class loadClass = loadClass(str);
        if (z) {
            resolveClass(loadClass);
        }
        return loadClass;
    }

    public Class loadClass(String str) throws ClassNotFoundException {
        Class cls;
        Object obj = this.cmap.get(str);
        if (obj != null) {
            return (Class) obj;
        }
        Object obj2 = this.map.get(str);
        if (obj2 instanceof ClassType) {
            ClassType classType = (ClassType) obj2;
            if (classType.isExisting()) {
                obj2 = classType.reflectClass;
            } else {
                obj2 = classType.writeToArray();
            }
        }
        if (obj2 instanceof byte[]) {
            synchronized (this) {
                Object obj3 = this.map.get(str);
                if (obj3 instanceof byte[]) {
                    byte[] bArr = (byte[]) obj3;
                    cls = defineClass(str, bArr, 0, bArr.length);
                    this.cmap.put(str, cls);
                } else {
                    cls = (Class) obj3;
                }
            }
            return cls;
        } else if (obj2 == null) {
            return getParent().loadClass(str);
        } else {
            return (Class) obj2;
        }
    }

    public static Package getContextPackage(String str) {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader instanceof ArrayClassLoader) {
                return ((ArrayClassLoader) contextClassLoader).getPackage(str);
            }
        } catch (SecurityException unused) {
        }
        return Package.getPackage(str);
    }
}
