package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Keyword extends Symbol implements Printable, Externalizable {
    public static final Namespace keywordNamespace;

    static {
        Namespace create = Namespace.create();
        keywordNamespace = create;
        create.setName("(keywords)");
    }

    public Keyword() {
    }

    private Keyword(String str) {
        super(keywordNamespace, str);
    }

    public Keyword(Namespace namespace, String str) {
        super(namespace, str);
    }

    public Symbol asSymbol() {
        return Namespace.EmptyNamespace.getSymbol(getName());
    }

    public static Keyword make(String str) {
        int hashCode = str.hashCode();
        Namespace namespace = keywordNamespace;
        Keyword keyword = (Keyword) namespace.lookup(str, hashCode, false);
        if (keyword != null) {
            return keyword;
        }
        Keyword keyword2 = new Keyword(str);
        namespace.add(keyword2, hashCode);
        return keyword2;
    }

    public static boolean isKeyword(Object obj) {
        return obj instanceof Keyword;
    }

    public final String toString() {
        return getName() + ':';
    }

    public void print(Consumer consumer) {
        Symbols.print(getName(), consumer);
        consumer.write(58);
    }

    public static Object searchForKeyword(Object[] objArr, int i, Object obj) {
        while (i < objArr.length) {
            if (objArr[i] == obj) {
                return objArr[i + 1];
            }
            i += 2;
        }
        return Special.dfault;
    }

    public static Object searchForKeyword(Object[] objArr, int i, Object obj, Object obj2) {
        while (i < objArr.length) {
            if (objArr[i] == obj) {
                return objArr[i + 1];
            }
            i += 2;
        }
        return obj2;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = (String) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        return make(keywordNamespace, getName());
    }
}
