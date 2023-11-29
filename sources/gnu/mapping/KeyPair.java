package gnu.mapping;

public class KeyPair implements EnvironmentKey {
    Symbol name;
    Object property;

    public KeyPair(Symbol symbol, Object obj) {
        this.name = symbol;
        this.property = obj;
    }

    public Symbol getKeySymbol() {
        return this.name;
    }

    public Object getKeyProperty() {
        return this.property;
    }

    public final boolean matches(EnvironmentKey environmentKey) {
        return Symbol.equals(environmentKey.getKeySymbol(), this.name) && environmentKey.getKeyProperty() == this.property;
    }

    public final boolean matches(Symbol symbol, Object obj) {
        return Symbol.equals(symbol, this.name) && obj == this.property;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001d A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof gnu.mapping.KeyPair
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            gnu.mapping.KeyPair r4 = (gnu.mapping.KeyPair) r4
            java.lang.Object r0 = r3.property
            java.lang.Object r2 = r4.property
            if (r0 != r2) goto L_0x001e
            gnu.mapping.Symbol r0 = r3.name
            gnu.mapping.Symbol r4 = r4.name
            if (r0 != 0) goto L_0x0017
            if (r4 != 0) goto L_0x001e
            goto L_0x001d
        L_0x0017:
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L_0x001e
        L_0x001d:
            r1 = 1
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.KeyPair.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this.name.hashCode() ^ System.identityHashCode(this.property);
    }

    public String toString() {
        return "KeyPair[sym:" + this.name + " prop:" + this.property + "]";
    }
}
