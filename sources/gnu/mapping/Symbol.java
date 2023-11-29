package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Symbol implements EnvironmentKey, Comparable, Externalizable {
    public static final Symbol FUNCTION = makeUninterned("(function)");
    public static final Symbol PLIST = makeUninterned("(property-list)");
    protected String name;
    Namespace namespace;

    public final Object getKeyProperty() {
        return null;
    }

    public final Symbol getKeySymbol() {
        return this;
    }

    public boolean matches(EnvironmentKey environmentKey) {
        return equals(environmentKey.getKeySymbol(), this) && environmentKey.getKeyProperty() == null;
    }

    public boolean matches(Symbol symbol, Object obj) {
        return equals(symbol, this) && obj == null;
    }

    public final String getNamespaceURI() {
        Namespace namespace2 = getNamespace();
        if (namespace2 == null) {
            return null;
        }
        return namespace2.getName();
    }

    public final String getLocalPart() {
        return this.name;
    }

    public final String getPrefix() {
        Namespace namespace2 = this.namespace;
        if (namespace2 == null) {
            return "";
        }
        return namespace2.prefix;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasEmptyNamespace() {
        /*
            r1 = this;
            gnu.mapping.Namespace r0 = r1.getNamespace()
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = r0.getName()
            if (r0 == 0) goto L_0x0015
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r0 = 0
            goto L_0x0016
        L_0x0015:
            r0 = 1
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.Symbol.hasEmptyNamespace():boolean");
    }

    public final String getLocalName() {
        return this.name;
    }

    public final String getName() {
        return this.name;
    }

    public static Symbol make(String str, String str2, String str3) {
        return Namespace.valueOf(str, str3).getSymbol(str2.intern());
    }

    public static Symbol make(Object obj, String str) {
        Namespace valueOf = obj instanceof String ? Namespace.valueOf((String) obj) : (Namespace) obj;
        if (valueOf == null || str == null) {
            return makeUninterned(str);
        }
        return valueOf.getSymbol(str.intern());
    }

    public static SimpleSymbol valueOf(String str) {
        return (SimpleSymbol) Namespace.EmptyNamespace.getSymbol(str.intern());
    }

    public static Symbol valueOf(String str, Object obj) {
        Namespace namespace2;
        if (obj == null || obj == Boolean.FALSE) {
            return makeUninterned(str);
        }
        if (obj instanceof Namespace) {
            namespace2 = (Namespace) obj;
        } else if (obj == Boolean.TRUE) {
            namespace2 = Namespace.EmptyNamespace;
        } else {
            namespace2 = Namespace.valueOf(((CharSequence) obj).toString());
        }
        return namespace2.getSymbol(str.intern());
    }

    public static Symbol valueOf(String str, String str2, String str3) {
        return Namespace.valueOf(str2, str3).getSymbol(str.intern());
    }

    public static Symbol parse(String str) {
        int i;
        int length = str.length();
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        int i6 = 0;
        while (true) {
            if (i3 < length) {
                char charAt = str.charAt(i3);
                if (charAt == ':' && i4 == 0) {
                    i = i3 + 1;
                    break;
                }
                if (charAt == '{') {
                    if (i5 < 0) {
                        i5 = i3;
                        i6 = i5;
                    }
                    i4++;
                }
                if (charAt == '}') {
                    i4--;
                    if (i4 == 0) {
                        i = (i3 >= length || str.charAt(i3 + 1) != ':') ? i3 + 1 : i3 + 2;
                        i2 = i3;
                        i3 = i6;
                    } else if (i4 < 0) {
                        i = i6;
                        i3 = i;
                        break;
                    }
                }
                i3++;
            } else {
                i3 = i6;
                i = 0;
                break;
            }
        }
        if (i5 >= 0 && i2 > 0) {
            return valueOf(str.substring(i), str.substring(i5 + 1, i2), i3 > 0 ? str.substring(0, i3) : null);
        } else if (i3 > 0) {
            return makeWithUnknownNamespace(str.substring(i), str.substring(0, i3));
        } else {
            return valueOf(str);
        }
    }

    public static Symbol makeWithUnknownNamespace(String str, String str2) {
        return Namespace.makeUnknownNamespace(str2).getSymbol(str.intern());
    }

    public Symbol() {
    }

    public static Symbol makeUninterned(String str) {
        return new Symbol((Namespace) null, str);
    }

    public Symbol(Namespace namespace2, String str) {
        this.name = str;
        this.namespace = namespace2;
    }

    public int compareTo(Object obj) {
        Symbol symbol = (Symbol) obj;
        if (getNamespaceURI() == symbol.getNamespaceURI()) {
            return getLocalName().compareTo(symbol.getLocalName());
        }
        throw new IllegalArgumentException("comparing Symbols in different namespaces");
    }

    public static boolean equals(Symbol symbol, Symbol symbol2) {
        if (symbol == symbol2) {
            return true;
        }
        if (!(symbol == null || symbol2 == null || symbol.name != symbol2.name)) {
            Namespace namespace2 = symbol.namespace;
            Namespace namespace3 = symbol2.namespace;
            if (namespace2 == null || namespace3 == null || namespace2.name != namespace3.name) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Symbol) && equals(this, (Symbol) obj);
    }

    public int hashCode() {
        String str = this.name;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final Namespace getNamespace() {
        return this.namespace;
    }

    public final void setNamespace(Namespace namespace2) {
        this.namespace = namespace2;
    }

    public String toString() {
        return toString('P');
    }

    public String toString(char c) {
        String namespaceURI = getNamespaceURI();
        String prefix = getPrefix();
        boolean z = true;
        boolean z2 = namespaceURI != null && namespaceURI.length() > 0;
        if (prefix == null || prefix.length() <= 0) {
            z = false;
        }
        String name2 = getName();
        if (!z2 && !z) {
            return name2;
        }
        StringBuilder sb = new StringBuilder();
        if (z && (c != 'U' || !z2)) {
            sb.append(prefix);
        }
        if (z2 && (c != 'P' || !z)) {
            sb.append('{');
            sb.append(getNamespaceURI());
            sb.append('}');
        }
        sb.append(':');
        sb.append(name2);
        return sb.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getNamespace());
        objectOutput.writeObject(getName());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.namespace = (Namespace) objectInput.readObject();
        this.name = (String) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        Namespace namespace2 = this.namespace;
        if (namespace2 == null) {
            return this;
        }
        return make(namespace2, getName());
    }
}
