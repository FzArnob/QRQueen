package gnu.xml;

import gnu.mapping.Symbol;

/* compiled from: XMLFilter */
final class MappingInfo {
    int index = -1;
    String local;
    NamespaceBinding namespaces;
    MappingInfo nextInBucket;
    String prefix;
    Symbol qname;
    int tagHash;
    XName type;
    String uri;

    MappingInfo() {
    }

    static int hash(String str, String str2) {
        int hashCode = str2.hashCode();
        return str != null ? hashCode ^ str.hashCode() : hashCode;
    }

    static int hash(char[] cArr, int i, int i2) {
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            char c = cArr[i + i6];
            if (c != ':' || i3 >= 0) {
                i5 = (i5 * 31) + c;
            } else {
                i3 = i6;
                i4 = i5;
                i5 = 0;
            }
        }
        return i4 ^ i5;
    }

    /* access modifiers changed from: package-private */
    public boolean match(char[] cArr, int i, int i2) {
        if (this.prefix == null) {
            return equals(this.local, cArr, i, i2);
        }
        int length = this.local.length();
        int length2 = this.prefix.length();
        if (i2 != length2 + 1 + length || cArr[length2] != ':' || !equals(this.prefix, cArr, i, length2) || !equals(this.local, cArr, i + length2 + 1, length)) {
            return false;
        }
        return true;
    }

    static boolean equals(String str, StringBuffer stringBuffer) {
        int length = stringBuffer.length();
        if (str.length() != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (stringBuffer.charAt(i) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean equals(String str, char[] cArr, int i, int i2) {
        if (str.length() != i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (cArr[i + i3] != str.charAt(i3)) {
                return false;
            }
        }
        return true;
    }
}
