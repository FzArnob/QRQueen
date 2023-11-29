package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

public class Namespace implements Externalizable, HasNamedParts {
    public static final Namespace EmptyNamespace = valueOf("");
    protected static final Hashtable nsTable = new Hashtable(50);
    int log2Size;
    private int mask;
    String name;
    int num_bindings;
    protected String prefix;
    protected SymbolRef[] table;

    public boolean isConstant(String str) {
        return false;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getPrefix() {
        return this.prefix;
    }

    protected Namespace() {
        this(64);
    }

    protected Namespace(int i) {
        this.prefix = "";
        this.log2Size = 4;
        while (true) {
            int i2 = this.log2Size;
            if (i > (1 << i2)) {
                this.log2Size = i2 + 1;
            } else {
                int i3 = 1 << i2;
                this.table = new SymbolRef[i3];
                this.mask = i3 - 1;
                return;
            }
        }
    }

    public static Namespace create(int i) {
        return new Namespace(i);
    }

    public static Namespace create() {
        return new Namespace(64);
    }

    public static Namespace getDefault() {
        return EmptyNamespace;
    }

    public static Symbol getDefaultSymbol(String str) {
        return EmptyNamespace.getSymbol(str);
    }

    public static Namespace valueOf() {
        return EmptyNamespace;
    }

    public static Namespace valueOf(String str) {
        if (str == null) {
            str = "";
        }
        Hashtable hashtable = nsTable;
        synchronized (hashtable) {
            Namespace namespace = (Namespace) hashtable.get(str);
            if (namespace != null) {
                return namespace;
            }
            Namespace namespace2 = new Namespace();
            namespace2.setName(str.intern());
            hashtable.put(str, namespace2);
            return namespace2;
        }
    }

    public static Namespace valueOf(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return valueOf(str);
        }
        String str3 = str2 + " -> " + str;
        Hashtable hashtable = nsTable;
        synchronized (hashtable) {
            Object obj = hashtable.get(str3);
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                return namespace;
            }
            Namespace namespace2 = new Namespace();
            namespace2.setName(str.intern());
            namespace2.prefix = str2.intern();
            hashtable.put(str3, namespace2);
            return namespace2;
        }
    }

    public static Namespace valueOf(String str, SimpleSymbol simpleSymbol) {
        return valueOf(str, simpleSymbol == null ? null : simpleSymbol.getName());
    }

    public static Namespace makeUnknownNamespace(String str) {
        String str2 = "";
        if (!(str == null || str == str2)) {
            str2 = "http://kawa.gnu.org/unknown-namespace/" + str;
        }
        return valueOf(str2, str);
    }

    public Object get(String str) {
        return Environment.getCurrent().get(getSymbol(str));
    }

    public Symbol getSymbol(String str) {
        return lookup(str, str.hashCode(), true);
    }

    public Symbol lookup(String str) {
        return lookup(str, str.hashCode(), false);
    }

    /* access modifiers changed from: protected */
    public final Symbol lookupInternal(String str, int i) {
        int i2 = i & this.mask;
        SymbolRef symbolRef = this.table[i2];
        SymbolRef symbolRef2 = null;
        while (symbolRef != null) {
            SymbolRef symbolRef3 = symbolRef.next;
            Symbol symbol = symbolRef.getSymbol();
            if (symbol == null) {
                if (symbolRef2 == null) {
                    this.table[i2] = symbolRef3;
                } else {
                    symbolRef2.next = symbolRef3;
                }
                this.num_bindings--;
            } else if (symbol.getLocalPart().equals(str)) {
                return symbol;
            } else {
                symbolRef2 = symbolRef;
            }
            symbolRef = symbolRef3;
        }
        return null;
    }

    public Symbol add(Symbol symbol, int i) {
        int i2 = i & this.mask;
        SymbolRef symbolRef = new SymbolRef(symbol, this);
        symbol.namespace = this;
        symbolRef.next = this.table[i2];
        SymbolRef[] symbolRefArr = this.table;
        symbolRefArr[i2] = symbolRef;
        int i3 = this.num_bindings + 1;
        this.num_bindings = i3;
        if (i3 >= symbolRefArr.length) {
            rehash();
        }
        return symbol;
    }

    public Symbol lookup(String str, int i, boolean z) {
        Symbol symbol;
        synchronized (this) {
            Symbol lookupInternal = lookupInternal(str, i);
            if (lookupInternal != null) {
                return lookupInternal;
            }
            if (!z) {
                return null;
            }
            if (this == EmptyNamespace) {
                symbol = new SimpleSymbol(str);
            } else {
                symbol = new Symbol(this, str);
            }
            Symbol add = add(symbol, i);
            return add;
        }
    }

    public boolean remove(Symbol symbol) {
        synchronized (this) {
            int hashCode = symbol.getLocalPart().hashCode() & this.mask;
            SymbolRef symbolRef = null;
            SymbolRef symbolRef2 = this.table[hashCode];
            while (symbolRef2 != null) {
                SymbolRef symbolRef3 = symbolRef2.next;
                Symbol symbol2 = symbolRef2.getSymbol();
                if (symbol2 != null) {
                    if (symbol2 != symbol) {
                        symbolRef = symbolRef2;
                        symbolRef2 = symbolRef3;
                    }
                }
                if (symbolRef == null) {
                    this.table[hashCode] = symbolRef3;
                } else {
                    symbolRef.next = symbolRef3;
                }
                this.num_bindings--;
                if (symbol2 != null) {
                    return true;
                }
                symbolRef2 = symbolRef3;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        SymbolRef[] symbolRefArr = this.table;
        int length = symbolRefArr.length;
        int i = length * 2;
        int i2 = i - 1;
        SymbolRef[] symbolRefArr2 = new SymbolRef[i];
        int i3 = 0;
        while (true) {
            length--;
            if (length >= 0) {
                SymbolRef symbolRef = symbolRefArr[length];
                while (symbolRef != null) {
                    SymbolRef symbolRef2 = symbolRef.next;
                    Symbol symbol = symbolRef.getSymbol();
                    if (symbol != null) {
                        int hashCode = symbol.getName().hashCode() & i2;
                        i3++;
                        symbolRef.next = symbolRefArr2[hashCode];
                        symbolRefArr2[hashCode] = symbolRef;
                    }
                    symbolRef = symbolRef2;
                }
            } else {
                this.table = symbolRefArr2;
                this.log2Size++;
                this.mask = i2;
                this.num_bindings = i3;
                return;
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
        objectOutput.writeObject(this.prefix);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = ((String) objectInput.readObject()).intern();
        this.prefix = (String) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        String name2 = getName();
        if (name2 != null) {
            String str = this.prefix;
            if (!(str == null || str.length() == 0)) {
                name2 = this.prefix + " -> " + name2;
            }
            Hashtable hashtable = nsTable;
            Namespace namespace = (Namespace) hashtable.get(name2);
            if (namespace != null) {
                return namespace;
            }
            hashtable.put(name2, this);
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("#,(namespace \"");
        sb.append(this.name);
        sb.append('\"');
        String str = this.prefix;
        if (!(str == null || str == "")) {
            sb.append(' ');
            sb.append(this.prefix);
        }
        sb.append(')');
        return sb.toString();
    }
}
