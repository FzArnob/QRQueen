package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairPat extends Pattern implements Printable, Externalizable {
    Pattern car;
    private int car_count;
    Pattern cdr;
    private int cdr_count;

    public PairPat() {
    }

    public PairPat(Pattern pattern, Pattern pattern2) {
        this.car = pattern;
        this.cdr = pattern2;
        this.car_count = pattern.varCount();
        this.cdr_count = pattern2.varCount();
    }

    public static PairPat make(Pattern pattern, Pattern pattern2) {
        return new PairPat(pattern, pattern2);
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (!this.car.match(pair.getCar(), objArr, i) || !this.cdr.match(pair.getCdr(), objArr, i + this.car_count)) {
            return false;
        }
        return true;
    }

    public void print(Consumer consumer) {
        consumer.write("#<pair-pattern car: ");
        this.car.print(consumer);
        consumer.write(" cdr: ");
        this.cdr.print(consumer);
        consumer.write(62);
    }

    public int varCount() {
        return this.car_count + this.cdr_count;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.car);
        objectOutput.writeObject(this.cdr);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.car = (Pattern) objectInput.readObject();
        this.cdr = (Pattern) objectInput.readObject();
    }
}
