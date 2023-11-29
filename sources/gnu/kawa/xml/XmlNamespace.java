package gnu.kawa.xml;

import gnu.mapping.Namespace;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class XmlNamespace extends Namespace implements Externalizable {
    public static final XmlNamespace HTML = valueOf("http://www.w3.org/1999/xhtml", "");
    public static final NamespaceBinding HTML_BINDINGS = new NamespaceBinding((String) null, "http://www.w3.org/1999/xhtml", NamespaceBinding.predefinedXML);
    public static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";

    public boolean isConstant(String str) {
        return true;
    }

    public static XmlNamespace getInstance(String str, String str2) {
        String str3 = str + " [xml] -> " + str2;
        synchronized (nsTable) {
            Object obj = nsTable.get(str3);
            if (obj instanceof XmlNamespace) {
                XmlNamespace xmlNamespace = (XmlNamespace) obj;
                return xmlNamespace;
            }
            XmlNamespace xmlNamespace2 = new XmlNamespace();
            xmlNamespace2.setName(str2.intern());
            xmlNamespace2.prefix = str.intern();
            nsTable.put(str3, xmlNamespace2);
            return xmlNamespace2;
        }
    }

    public static XmlNamespace valueOf(String str, String str2) {
        return getInstance(str2, str);
    }

    public Object get(String str) {
        ElementType make = ElementType.make(getSymbol(str));
        if (this == HTML) {
            make.setNamespaceNodes(HTML_BINDINGS);
        }
        return make;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
        objectOutput.writeObject(this.prefix);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName((String) objectInput.readObject());
        this.prefix = (String) objectInput.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        String str = this.prefix + " -> " + getName();
        Namespace namespace = (Namespace) nsTable.get(str);
        if (namespace instanceof XmlNamespace) {
            return namespace;
        }
        nsTable.put(str, this);
        return this;
    }
}
