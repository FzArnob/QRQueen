package gnu.xml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class NamespaceBinding implements Externalizable {
    public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";
    public static final NamespaceBinding predefinedXML = new NamespaceBinding("xml", XML_NAMESPACE, (NamespaceBinding) null);
    int depth;
    NamespaceBinding next;
    String prefix;
    String uri;

    public final String getPrefix() {
        return this.prefix;
    }

    public final void setPrefix(String str) {
        this.prefix = str;
    }

    public final String getUri() {
        return this.uri;
    }

    public final void setUri(String str) {
        this.uri = str;
    }

    public final NamespaceBinding getNext() {
        return this.next;
    }

    public final void setNext(NamespaceBinding namespaceBinding) {
        int i;
        this.next = namespaceBinding;
        if (namespaceBinding == null) {
            i = 0;
        } else {
            i = namespaceBinding.depth + 1;
        }
        this.depth = i;
    }

    public static final NamespaceBinding nconc(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        if (namespaceBinding == null) {
            return namespaceBinding2;
        }
        namespaceBinding.setNext(nconc(namespaceBinding.next, namespaceBinding2));
        return namespaceBinding;
    }

    public NamespaceBinding(String str, String str2, NamespaceBinding namespaceBinding) {
        this.prefix = str;
        this.uri = str2;
        setNext(namespaceBinding);
    }

    public String resolve(String str) {
        for (NamespaceBinding namespaceBinding = this; namespaceBinding != null; namespaceBinding = namespaceBinding.next) {
            if (namespaceBinding.prefix == str) {
                return namespaceBinding.uri;
            }
        }
        return null;
    }

    public String resolve(String str, NamespaceBinding namespaceBinding) {
        for (NamespaceBinding namespaceBinding2 = this; namespaceBinding2 != namespaceBinding; namespaceBinding2 = namespaceBinding2.next) {
            if (namespaceBinding2.prefix == str) {
                return namespaceBinding2.uri;
            }
        }
        return null;
    }

    public static NamespaceBinding commonAncestor(NamespaceBinding namespaceBinding, NamespaceBinding namespaceBinding2) {
        if (namespaceBinding.depth <= namespaceBinding2.depth) {
            NamespaceBinding namespaceBinding3 = namespaceBinding2;
            namespaceBinding2 = namespaceBinding;
            namespaceBinding = namespaceBinding3;
        }
        while (namespaceBinding.depth > namespaceBinding2.depth) {
            namespaceBinding = namespaceBinding.next;
        }
        while (namespaceBinding2 != namespaceBinding) {
            namespaceBinding2 = namespaceBinding2.next;
            namespaceBinding = namespaceBinding.next;
        }
        return namespaceBinding2;
    }

    public NamespaceBinding reversePrefix(NamespaceBinding namespaceBinding) {
        int i = namespaceBinding == null ? -1 : namespaceBinding.depth;
        NamespaceBinding namespaceBinding2 = this;
        NamespaceBinding namespaceBinding3 = namespaceBinding;
        while (namespaceBinding2 != namespaceBinding) {
            NamespaceBinding namespaceBinding4 = namespaceBinding2.next;
            namespaceBinding2.next = namespaceBinding3;
            i++;
            namespaceBinding2.depth = i;
            namespaceBinding3 = namespaceBinding2;
            namespaceBinding2 = namespaceBinding4;
        }
        return namespaceBinding3;
    }

    public int count(NamespaceBinding namespaceBinding) {
        int i = 0;
        for (NamespaceBinding namespaceBinding2 = this; namespaceBinding2 != namespaceBinding; namespaceBinding2 = namespaceBinding2.next) {
            i++;
        }
        return i;
    }

    public static NamespaceBinding maybeAdd(String str, String str2, NamespaceBinding namespaceBinding) {
        if (namespaceBinding == null) {
            if (str2 == null) {
                return namespaceBinding;
            }
            namespaceBinding = predefinedXML;
        }
        String resolve = namespaceBinding.resolve(str);
        if (resolve != null ? !resolve.equals(str2) : str2 != null) {
            return new NamespaceBinding(str, str2, namespaceBinding);
        }
        return namespaceBinding;
    }

    public String toString() {
        return "Namespace{" + this.prefix + "=" + this.uri + ", depth:" + this.depth + "}";
    }

    public String toStringAll() {
        StringBuffer stringBuffer = new StringBuffer("Namespaces{");
        NamespaceBinding namespaceBinding = this;
        while (namespaceBinding != null) {
            stringBuffer.append(namespaceBinding.prefix);
            stringBuffer.append("=\"");
            stringBuffer.append(namespaceBinding.uri);
            stringBuffer.append(namespaceBinding == null ? "\"" : "\", ");
            namespaceBinding = namespaceBinding.next;
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.prefix);
        objectOutput.writeUTF(this.uri);
        objectOutput.writeObject(this.next);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.prefix = objectInput.readUTF();
        this.uri = objectInput.readUTF();
        this.next = (NamespaceBinding) objectInput.readObject();
    }
}
