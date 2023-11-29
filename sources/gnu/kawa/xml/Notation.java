package gnu.kawa.xml;

import gnu.mapping.Symbol;

public class Notation {
    Symbol qname;

    public boolean equals(Notation notation, Notation notation2) {
        return notation.qname.equals(notation2.qname);
    }

    public boolean equals(Object obj) {
        return (obj instanceof Notation) && equals(this, (Notation) obj);
    }

    public int hashCode() {
        return this.qname.hashCode();
    }
}
