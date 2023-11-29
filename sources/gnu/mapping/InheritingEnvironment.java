package gnu.mapping;

public class InheritingEnvironment extends SimpleEnvironment {
    int baseTimestamp;
    Environment[] inherited;
    Namespace[] namespaceMap;
    int numInherited;
    Object[] propertyMap;

    public InheritingEnvironment(String str, Environment environment) {
        super(str);
        addParent(environment);
        if (environment instanceof SimpleEnvironment) {
            SimpleEnvironment simpleEnvironment = (SimpleEnvironment) environment;
            int i = simpleEnvironment.currentTimestamp + 1;
            simpleEnvironment.currentTimestamp = i;
            this.baseTimestamp = i;
            this.currentTimestamp = i;
        }
    }

    public final int getNumParents() {
        return this.numInherited;
    }

    public final Environment getParent(int i) {
        return this.inherited[i];
    }

    public void addParent(Environment environment) {
        int i = this.numInherited;
        if (i == 0) {
            this.inherited = new Environment[4];
        } else {
            Environment[] environmentArr = this.inherited;
            if (i <= environmentArr.length) {
                Environment[] environmentArr2 = new Environment[(i * 2)];
                System.arraycopy(environmentArr, 0, environmentArr2, 0, i);
                this.inherited = environmentArr2;
            }
        }
        Environment[] environmentArr3 = this.inherited;
        int i2 = this.numInherited;
        environmentArr3[i2] = environment;
        this.numInherited = i2 + 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x005e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.mapping.NamedLocation lookupInherited(gnu.mapping.Symbol r6, java.lang.Object r7, int r8) {
        /*
            r5 = this;
            r0 = 0
        L_0x0001:
            int r1 = r5.numInherited
            if (r0 >= r1) goto L_0x0061
            gnu.mapping.Namespace[] r1 = r5.namespaceMap
            if (r1 == 0) goto L_0x0028
            int r2 = r1.length
            int r3 = r0 * 2
            if (r2 <= r3) goto L_0x0028
            r2 = r1[r3]
            int r3 = r3 + 1
            r1 = r1[r3]
            if (r2 != 0) goto L_0x0018
            if (r1 == 0) goto L_0x0028
        L_0x0018:
            gnu.mapping.Namespace r3 = r6.getNamespace()
            if (r3 == r1) goto L_0x001f
            goto L_0x005e
        L_0x001f:
            java.lang.String r1 = r6.getName()
            gnu.mapping.Symbol r1 = gnu.mapping.Symbol.make(r2, r1)
            goto L_0x0029
        L_0x0028:
            r1 = r6
        L_0x0029:
            java.lang.Object[] r2 = r5.propertyMap
            if (r2 == 0) goto L_0x003f
            int r3 = r2.length
            int r4 = r0 * 2
            if (r3 <= r4) goto L_0x003f
            r3 = r2[r4]
            int r4 = r4 + 1
            r2 = r2[r4]
            if (r3 != 0) goto L_0x003c
            if (r2 == 0) goto L_0x003f
        L_0x003c:
            if (r7 == r2) goto L_0x0040
            goto L_0x005e
        L_0x003f:
            r3 = r7
        L_0x0040:
            gnu.mapping.Environment[] r2 = r5.inherited
            r2 = r2[r0]
            gnu.mapping.NamedLocation r1 = r2.lookup(r1, r3, r8)
            if (r1 == 0) goto L_0x005e
            boolean r2 = r1.isBound()
            if (r2 == 0) goto L_0x005e
            boolean r2 = r1 instanceof gnu.mapping.SharedLocation
            if (r2 == 0) goto L_0x005d
            r2 = r1
            gnu.mapping.SharedLocation r2 = (gnu.mapping.SharedLocation) r2
            int r2 = r2.timestamp
            int r3 = r5.baseTimestamp
            if (r2 >= r3) goto L_0x005e
        L_0x005d:
            return r1
        L_0x005e:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0061:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InheritingEnvironment.lookupInherited(gnu.mapping.Symbol, java.lang.Object, int):gnu.mapping.NamedLocation");
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i) {
        NamedLocation lookup = super.lookup(symbol, obj, i);
        if (lookup == null || !lookup.isBound()) {
            return lookupInherited(symbol, obj, i);
        }
        return lookup;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006f, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized gnu.mapping.NamedLocation getLocation(gnu.mapping.Symbol r4, java.lang.Object r5, int r6, boolean r7) {
        /*
            r3 = this;
            monitor-enter(r3)
            gnu.mapping.NamedLocation r0 = r3.lookupDirect(r4, r5, r6)     // Catch:{ all -> 0x0070 }
            if (r0 == 0) goto L_0x0011
            if (r7 != 0) goto L_0x000f
            boolean r1 = r0.isBound()     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x0011
        L_0x000f:
            monitor-exit(r3)
            return r0
        L_0x0011:
            int r0 = r3.flags     // Catch:{ all -> 0x0070 }
            r0 = r0 & 32
            r1 = 1
            if (r0 == 0) goto L_0x0024
            if (r7 == 0) goto L_0x0024
            gnu.mapping.Environment[] r0 = r3.inherited     // Catch:{ all -> 0x0070 }
            r2 = 0
            r0 = r0[r2]     // Catch:{ all -> 0x0070 }
            gnu.mapping.NamedLocation r0 = r0.getLocation(r4, r5, r6, r1)     // Catch:{ all -> 0x0070 }
            goto L_0x0028
        L_0x0024:
            gnu.mapping.NamedLocation r0 = r3.lookupInherited(r4, r5, r6)     // Catch:{ all -> 0x0070 }
        L_0x0028:
            r2 = 0
            if (r0 == 0) goto L_0x0068
            if (r7 == 0) goto L_0x0066
            gnu.mapping.NamedLocation r6 = r3.addUnboundLocation(r4, r5, r6)     // Catch:{ all -> 0x0070 }
            int r7 = r3.flags     // Catch:{ all -> 0x0070 }
            r7 = r7 & r1
            if (r7 != 0) goto L_0x003f
            boolean r7 = r0.isBound()     // Catch:{ all -> 0x0070 }
            if (r7 == 0) goto L_0x003f
            r3.redefineError(r4, r5, r6)     // Catch:{ all -> 0x0070 }
        L_0x003f:
            r6.base = r0     // Catch:{ all -> 0x0070 }
            java.lang.Object r4 = r0.value     // Catch:{ all -> 0x0070 }
            java.lang.Object r5 = gnu.mapping.IndirectableLocation.INDIRECT_FLUIDS     // Catch:{ all -> 0x0070 }
            if (r4 != r5) goto L_0x004c
            java.lang.Object r4 = r0.value     // Catch:{ all -> 0x0070 }
            r6.value = r4     // Catch:{ all -> 0x0070 }
            goto L_0x0059
        L_0x004c:
            int r4 = r3.flags     // Catch:{ all -> 0x0070 }
            r4 = r4 & 16
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = gnu.mapping.IndirectableLocation.DIRECT_ON_SET     // Catch:{ all -> 0x0070 }
            r6.value = r4     // Catch:{ all -> 0x0070 }
            goto L_0x0059
        L_0x0057:
            r6.value = r2     // Catch:{ all -> 0x0070 }
        L_0x0059:
            boolean r4 = r6 instanceof gnu.mapping.SharedLocation     // Catch:{ all -> 0x0070 }
            if (r4 == 0) goto L_0x0064
            r4 = r6
            gnu.mapping.SharedLocation r4 = (gnu.mapping.SharedLocation) r4     // Catch:{ all -> 0x0070 }
            int r5 = r3.baseTimestamp     // Catch:{ all -> 0x0070 }
            r4.timestamp = r5     // Catch:{ all -> 0x0070 }
        L_0x0064:
            monitor-exit(r3)
            return r6
        L_0x0066:
            monitor-exit(r3)
            return r0
        L_0x0068:
            if (r7 == 0) goto L_0x006e
            gnu.mapping.NamedLocation r2 = r3.addUnboundLocation(r4, r5, r6)     // Catch:{ all -> 0x0070 }
        L_0x006e:
            monitor-exit(r3)
            return r2
        L_0x0070:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InheritingEnvironment.getLocation(gnu.mapping.Symbol, java.lang.Object, int, boolean):gnu.mapping.NamedLocation");
    }

    public LocationEnumeration enumerateAllLocations() {
        LocationEnumeration locationEnumeration = new LocationEnumeration(this.table, 1 << this.log2Size);
        locationEnumeration.env = this;
        Environment[] environmentArr = this.inherited;
        if (environmentArr != null && environmentArr.length > 0) {
            locationEnumeration.inherited = environmentArr[0].enumerateAllLocations();
            locationEnumeration.index = 0;
        }
        return locationEnumeration;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r5.prevLoc = null;
        r5.nextLoc = r5.inherited.nextLoc;
        r2 = r5.index + 1;
        r5.index = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r2 != r4.numInherited) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0025, code lost:
        r5.inherited = null;
        r5.bindings = r4.table;
        r5.index = 1 << r4.log2Size;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasMoreElements(gnu.mapping.LocationEnumeration r5) {
        /*
            r4 = this;
            gnu.mapping.LocationEnumeration r0 = r5.inherited
            if (r0 == 0) goto L_0x0053
        L_0x0004:
            gnu.mapping.NamedLocation r0 = r5.nextLoc
        L_0x0006:
            gnu.mapping.LocationEnumeration r1 = r5.inherited
            r1.nextLoc = r0
            gnu.mapping.LocationEnumeration r0 = r5.inherited
            boolean r0 = r0.hasMoreElements()
            r1 = 1
            if (r0 != 0) goto L_0x003f
            r0 = 0
            r5.prevLoc = r0
            gnu.mapping.LocationEnumeration r2 = r5.inherited
            gnu.mapping.NamedLocation r2 = r2.nextLoc
            r5.nextLoc = r2
            int r2 = r5.index
            int r2 = r2 + r1
            r5.index = r2
            int r3 = r4.numInherited
            if (r2 != r3) goto L_0x0032
            r5.inherited = r0
            gnu.mapping.NamedLocation[] r0 = r4.table
            r5.bindings = r0
            int r0 = r4.log2Size
            int r0 = r1 << r0
            r5.index = r0
            goto L_0x0053
        L_0x0032:
            gnu.mapping.Environment[] r0 = r4.inherited
            int r1 = r5.index
            r0 = r0[r1]
            gnu.mapping.LocationEnumeration r0 = r0.enumerateAllLocations()
            r5.inherited = r0
            goto L_0x0004
        L_0x003f:
            gnu.mapping.LocationEnumeration r0 = r5.inherited
            gnu.mapping.NamedLocation r0 = r0.nextLoc
            gnu.mapping.Symbol r2 = r0.name
            java.lang.Object r3 = r0.property
            gnu.mapping.Location r2 = r4.lookup(r2, r3)
            if (r2 != r0) goto L_0x0050
            r5.nextLoc = r0
            return r1
        L_0x0050:
            gnu.mapping.NamedLocation r0 = r0.next
            goto L_0x0006
        L_0x0053:
            boolean r5 = super.hasMoreElements(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.InheritingEnvironment.hasMoreElements(gnu.mapping.LocationEnumeration):boolean");
    }

    /* access modifiers changed from: protected */
    public void toStringBase(StringBuffer stringBuffer) {
        stringBuffer.append(" baseTs:");
        stringBuffer.append(this.baseTimestamp);
        for (int i = 0; i < this.numInherited; i++) {
            stringBuffer.append(" base:");
            stringBuffer.append(this.inherited[i].toStringVerbose());
        }
    }
}
