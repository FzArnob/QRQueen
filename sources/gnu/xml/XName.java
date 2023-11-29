package gnu.xml;

import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class XName extends Symbol implements Externalizable {
    NamespaceBinding namespaceNodes;

    public XName() {
    }

    public XName(Symbol symbol, NamespaceBinding namespaceBinding) {
        super(symbol.getNamespace(), symbol.getName());
        this.namespaceNodes = namespaceBinding;
    }

    public final NamespaceBinding getNamespaceNodes() {
        return this.namespaceNodes;
    }

    public final void setNamespaceNodes(NamespaceBinding namespaceBinding) {
        this.namespaceNodes = namespaceBinding;
    }

    /* access modifiers changed from: package-private */
    public String lookupNamespaceURI(String str) {
        for (NamespaceBinding namespaceBinding = this.namespaceNodes; namespaceBinding != null; namespaceBinding = namespaceBinding.next) {
            if (str == namespaceBinding.prefix) {
                return namespaceBinding.uri;
            }
        }
        return null;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        super.writeExternal(objectOutput);
        objectOutput.writeObject(this.namespaceNodes);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        super.readExternal(objectInput);
        this.namespaceNodes = (NamespaceBinding) objectInput.readObject();
    }

    public static boolean isNameStart(int i) {
        return Character.isUnicodeIdentifierStart(i) || i == 95;
    }

    public static boolean isNamePart(int i) {
        return Character.isUnicodeIdentifierPart(i) || i == 45 || i == 46;
    }

    public static boolean isNmToken(String str) {
        return checkName(str) >= 0;
    }

    public static boolean isName(String str) {
        return checkName(str) > 0;
    }

    public static boolean isNCName(String str) {
        return checkName(str) > 1;
    }

    public static int checkName(String str) {
        int length = str.length();
        if (length == 0) {
            return -1;
        }
        int i = 0;
        int i2 = 2;
        while (i < length) {
            boolean z = i == 0;
            int i3 = i + 1;
            int charAt = str.charAt(i);
            if (charAt >= 55296 && charAt < 56320 && i3 < length) {
                charAt = ((charAt - 55296) * 1024) + (str.charAt(i3) - 56320) + 65536;
                i3++;
            }
            if (charAt == 58) {
                if (i2 == 2) {
                    i2 = 1;
                }
            } else if (!isNamePart(charAt)) {
                return -1;
            } else {
                if (z && !isNameStart(charAt)) {
                    i2 = 0;
                }
            }
            i = i3;
        }
        return i2;
    }
}
