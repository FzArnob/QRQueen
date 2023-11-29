package gnu.mapping;

public class ThreadLocation extends NamedLocation implements Named {
    public static final String ANONYMOUS = new String("(dynamic)");
    static int counter;
    static SimpleEnvironment env;
    SharedLocation global;
    private int hash;
    private ThreadLocal<NamedLocation> thLocal;

    private static synchronized int nextCounter() {
        int i;
        synchronized (ThreadLocation.class) {
            i = counter + 1;
            counter = i;
        }
        return i;
    }

    public ThreadLocation() {
        this("param#" + nextCounter());
    }

    public ThreadLocation(String str) {
        super(Symbol.makeUninterned(str), ANONYMOUS);
        this.thLocal = new InheritingLocation();
        this.global = new SharedLocation(this.name, (Object) null, 0);
    }

    private ThreadLocation(Symbol symbol) {
        super(symbol, ANONYMOUS);
        String str;
        this.thLocal = new InheritingLocation();
        if (symbol == null) {
            str = null;
        } else {
            str = symbol.toString();
        }
        this.global = new SharedLocation(Symbol.makeUninterned(str), (Object) null, 0);
    }

    public ThreadLocation(Symbol symbol, Object obj, SharedLocation sharedLocation) {
        super(symbol, obj);
        this.hash = symbol.hashCode() ^ System.identityHashCode(obj);
        this.global = sharedLocation;
    }

    public static ThreadLocation makeAnonymous(String str) {
        return new ThreadLocation(str);
    }

    public static ThreadLocation makeAnonymous(Symbol symbol) {
        return new ThreadLocation(symbol);
    }

    public void setGlobal(Object obj) {
        synchronized (this) {
            if (this.global == null) {
                this.global = new SharedLocation(this.name, (Object) null, 0);
            }
            this.global.set(obj);
        }
    }

    public NamedLocation getLocation() {
        if (this.property != ANONYMOUS) {
            return Environment.getCurrent().getLocation(this.name, this.property, this.hash, true);
        }
        NamedLocation namedLocation = this.thLocal.get();
        if (namedLocation == null) {
            namedLocation = new SharedLocation(this.name, this.property, 0);
            SharedLocation sharedLocation = this.global;
            if (sharedLocation != null) {
                namedLocation.setBase(sharedLocation);
            }
            this.thLocal.set(namedLocation);
        }
        return namedLocation;
    }

    public Object get(Object obj) {
        return getLocation().get(obj);
    }

    public void set(Object obj) {
        getLocation().set(obj);
    }

    public Object setWithSave(Object obj) {
        return getLocation().setWithSave(obj);
    }

    public void setRestore(Object obj) {
        getLocation().setRestore(obj);
    }

    public String getName() {
        if (this.name == null) {
            return null;
        }
        return this.name.toString();
    }

    public Object getSymbol() {
        if (this.name != null && this.property == ANONYMOUS && this.global.getKeySymbol() == this.name) {
            return this.name.toString();
        }
        return this.name;
    }

    public void setName(String str) {
        throw new RuntimeException("setName not allowed");
    }

    public static synchronized ThreadLocation getInstance(Symbol symbol, Object obj) {
        synchronized (ThreadLocation.class) {
            if (env == null) {
                env = new SimpleEnvironment("[thread-locations]");
            }
            IndirectableLocation indirectableLocation = (IndirectableLocation) env.getLocation(symbol, obj);
            if (indirectableLocation.base != null) {
                ThreadLocation threadLocation = (ThreadLocation) indirectableLocation.base;
                return threadLocation;
            }
            ThreadLocation threadLocation2 = new ThreadLocation(symbol, obj, (SharedLocation) null);
            indirectableLocation.base = threadLocation2;
            return threadLocation2;
        }
    }

    public class InheritingLocation extends InheritableThreadLocal<NamedLocation> {
        public InheritingLocation() {
        }

        /* access modifiers changed from: protected */
        public SharedLocation childValue(SharedLocation sharedLocation) {
            if (ThreadLocation.this.property == ThreadLocation.ANONYMOUS) {
                if (sharedLocation == null) {
                    sharedLocation = (SharedLocation) ThreadLocation.this.getLocation();
                }
                if (sharedLocation.base == null) {
                    SharedLocation sharedLocation2 = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
                    sharedLocation2.value = sharedLocation.value;
                    sharedLocation.base = sharedLocation2;
                    sharedLocation.value = null;
                    sharedLocation = sharedLocation2;
                }
                SharedLocation sharedLocation3 = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
                sharedLocation3.value = null;
                sharedLocation3.base = sharedLocation;
                return sharedLocation3;
            }
            throw new Error();
        }
    }
}
