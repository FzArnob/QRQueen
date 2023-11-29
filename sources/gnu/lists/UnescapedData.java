package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UnescapedData implements CharSequence, Externalizable {
    String data;

    public UnescapedData() {
    }

    public UnescapedData(String str) {
        this.data = str;
    }

    public final String getData() {
        return this.data;
    }

    public final String toString() {
        return this.data;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof UnescapedData) && this.data.equals(obj.toString());
    }

    public final int hashCode() {
        String str = this.data;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public int length() {
        return this.data.length();
    }

    public char charAt(int i) {
        return this.data.charAt(i);
    }

    public CharSequence subSequence(int i, int i2) {
        return new UnescapedData(this.data.substring(i, i2));
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.data);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.data = (String) objectInput.readObject();
    }
}
