package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.WrappedException;

public class ModuleContext {
    public static int IN_HTTP_SERVER = 1;
    public static int IN_SERVLET = 2;
    static ModuleContext global = new ModuleContext(ModuleManager.instance);
    int flags;
    ModuleManager manager;
    private ClassToInstanceMap table = new ClassToInstanceMap();

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public void addFlags(int i) {
        this.flags = i | this.flags;
    }

    public ModuleContext(ModuleManager moduleManager) {
        this.manager = moduleManager;
    }

    public static ModuleContext getContext() {
        return global;
    }

    public ModuleManager getManager() {
        return this.manager;
    }

    public synchronized Object findInstance(ModuleInfo moduleInfo) {
        try {
        } catch (ClassNotFoundException e) {
            String className = moduleInfo.getClassName();
            throw new WrappedException("cannot find module " + className, e);
        }
        return findInstance(moduleInfo.getModuleClass());
    }

    public synchronized Object searchInstance(Class cls) {
        return this.table.get(cls);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        throw new gnu.mapping.WrappedException("exception while initializing module " + r5.getName(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r5 = r5.newInstance();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object findInstance(java.lang.Class r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            gnu.expr.ModuleContext$ClassToInstanceMap r0 = r4.table     // Catch:{ all -> 0x003d }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x003d }
            if (r0 != 0) goto L_0x003b
            java.lang.String r0 = "$instance"
            java.lang.reflect.Field r0 = r5.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x0017 }
            r1 = 0
            java.lang.Object r5 = r0.get(r1)     // Catch:{ NoSuchFieldException -> 0x0017 }
            goto L_0x001b
        L_0x0015:
            r0 = move-exception
            goto L_0x0020
        L_0x0017:
            java.lang.Object r5 = r5.newInstance()     // Catch:{ all -> 0x0015 }
        L_0x001b:
            r0 = r5
            r4.setInstance(r0)     // Catch:{ all -> 0x003d }
            goto L_0x003b
        L_0x0020:
            gnu.mapping.WrappedException r1 = new gnu.mapping.WrappedException     // Catch:{ all -> 0x003d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x003d }
            r2.<init>()     // Catch:{ all -> 0x003d }
            java.lang.String r3 = "exception while initializing module "
            r2.append(r3)     // Catch:{ all -> 0x003d }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x003d }
            r2.append(r5)     // Catch:{ all -> 0x003d }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x003d }
            r1.<init>(r5, r0)     // Catch:{ all -> 0x003d }
            throw r1     // Catch:{ all -> 0x003d }
        L_0x003b:
            monitor-exit(r4)
            return r0
        L_0x003d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleContext.findInstance(java.lang.Class):java.lang.Object");
    }

    public synchronized void setInstance(Object obj) {
        this.table.put(obj.getClass(), obj);
    }

    public ModuleInfo findFromInstance(Object obj) {
        ModuleInfo findWithClass;
        Class<?> cls = obj.getClass();
        synchronized (this) {
            findWithClass = ModuleManager.findWithClass(cls);
            setInstance(obj);
        }
        return findWithClass;
    }

    public synchronized void clear() {
        this.table.clear();
    }

    static class ClassToInstanceMap extends AbstractWeakHashTable<Class, Object> {
        /* access modifiers changed from: protected */
        public boolean matches(Class cls, Class cls2) {
            return cls == cls2;
        }

        ClassToInstanceMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(Object obj) {
            return obj.getClass();
        }
    }
}
