package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ListRepeatPat extends Pattern implements Printable, Externalizable {
    Pattern element_pattern;

    public ListRepeatPat() {
    }

    public ListRepeatPat(Pattern pattern) {
        this.element_pattern = pattern;
    }

    public static ListRepeatPat make(Pattern pattern) {
        return new ListRepeatPat(pattern);
    }

    public void print(Consumer consumer) {
        consumer.write("#<list-repeat-pattern ");
        this.element_pattern.print(consumer);
        consumer.write(62);
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        int listLength = LList.listLength(obj, false);
        if (listLength < 0) {
            return false;
        }
        int varCount = this.element_pattern.varCount();
        int i2 = varCount;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            objArr[i + i2] = new Object[listLength];
        }
        Object[] objArr2 = new Object[varCount];
        for (int i3 = 0; i3 < listLength; i3++) {
            Pair pair = (Pair) obj;
            if (!this.element_pattern.match(pair.getCar(), objArr2, 0)) {
                return false;
            }
            for (int i4 = 0; i4 < varCount; i4++) {
                objArr[i + i4][i3] = objArr2[i4];
            }
            obj = pair.getCdr();
        }
        return true;
    }

    public int varCount() {
        return this.element_pattern.varCount();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.element_pattern);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.element_pattern = (Pattern) objectInput.readObject();
    }
}
