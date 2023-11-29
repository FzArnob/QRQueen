package gnu.lists;

import gnu.text.SourceLocator;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairWithPosition extends ImmutablePair implements SourceLocator {
    String filename;
    int position;

    public String getPublicId() {
        return null;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public final void setFile(String str) {
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.position = (i << 12) + i2;
    }

    public final void setLine(int i) {
        setLine(i, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int i = this.position >> 12;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public final int getColumnNumber() {
        int i = this.position & 4095;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public PairWithPosition() {
    }

    public PairWithPosition(SourceLocator sourceLocator, Object obj, Object obj2) {
        super(obj, obj2);
        this.filename = sourceLocator.getFileName();
        setLine(sourceLocator.getLineNumber(), sourceLocator.getColumnNumber());
    }

    public PairWithPosition(Object obj, Object obj2) {
        super(obj, obj2);
    }

    public static PairWithPosition make(Object obj, Object obj2, String str, int i, int i2) {
        PairWithPosition pairWithPosition = new PairWithPosition(obj, obj2);
        pairWithPosition.filename = str;
        pairWithPosition.setLine(i, i2);
        return pairWithPosition;
    }

    public static PairWithPosition make(Object obj, Object obj2, String str, int i) {
        PairWithPosition pairWithPosition = new PairWithPosition(obj, obj2);
        pairWithPosition.filename = str;
        pairWithPosition.position = i;
        return pairWithPosition;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.car);
        objectOutput.writeObject(this.cdr);
        objectOutput.writeObject(this.filename);
        objectOutput.writeInt(this.position);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.car = objectInput.readObject();
        this.cdr = objectInput.readObject();
        this.filename = (String) objectInput.readObject();
        this.position = objectInput.readInt();
    }
}
