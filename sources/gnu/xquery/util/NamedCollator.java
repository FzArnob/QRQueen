package gnu.xquery.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.CollationKey;
import java.text.Collator;

public class NamedCollator extends Collator implements Externalizable {
    public static final String UNICODE_CODEPOINT_COLLATION = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
    public static final NamedCollator codepointCollation;
    Collator collator;
    String name;

    public static NamedCollator make(String str) {
        NamedCollator namedCollator = new NamedCollator();
        namedCollator.name = str;
        namedCollator.resolve();
        return namedCollator;
    }

    public String getName() {
        return this.name;
    }

    public static NamedCollator find(String str) {
        return make(str);
    }

    static {
        NamedCollator namedCollator = new NamedCollator();
        codepointCollation = namedCollator;
        namedCollator.name = UNICODE_CODEPOINT_COLLATION;
    }

    public void resolve() {
        String str = this.name;
        if (str != null && !str.equals(UNICODE_CODEPOINT_COLLATION)) {
            throw new RuntimeException("unknown collation: " + this.name);
        }
    }

    public static int codepointCompare(String str, String str2) {
        char c;
        int i;
        int length = str.length();
        int length2 = str2.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 != length) {
            if (i3 == length2) {
                return 1;
            }
            int i4 = i2 + 1;
            char charAt = str.charAt(i2);
            if (charAt < 55296 || charAt >= 56320 || i4 >= length) {
                int i5 = i4;
                c = charAt;
                i2 = i5;
            } else {
                int i6 = i4 + 1;
                c = ((charAt - 55296) * 1024) + (str.charAt(i4) - 56320) + 65536;
                i2 = i6;
            }
            int i7 = i3 + 1;
            int charAt2 = str2.charAt(i3);
            if (charAt2 < 55296 || charAt2 >= 56320 || i7 >= length2) {
                i = i7;
            } else {
                i = i7 + 1;
                charAt2 = ((charAt2 - 55296) * 1024) + (str2.charAt(i7) - 56320) + 65536;
            }
            if (c == charAt2) {
                i3 = i;
            } else if (c < charAt2) {
                return -1;
            } else {
                return 1;
            }
        }
        return i3 == length2 ? 0 : -1;
    }

    public int compare(String str, String str2) {
        Collator collator2 = this.collator;
        if (collator2 != null) {
            return collator2.compare(str, str2);
        }
        return codepointCompare(str, str2);
    }

    public CollationKey getCollationKey(String str) {
        return this.collator.getCollationKey(str);
    }

    public int hashCode() {
        Collator collator2 = this.collator;
        if (collator2 != null) {
            return collator2.hashCode();
        }
        return 0;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.name);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.name = objectInput.readUTF();
        resolve();
    }
}
