package gnu.lists;

public class GeneralArray1 extends GeneralArray implements Sequence {
    public int createPos(int i, boolean z) {
        return (i << 1) | z ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        return i == -1 ? size() : i >>> 1;
    }

    public void consumePosRange(int i, int i2, Consumer consumer) {
        if (!consumer.ignoring()) {
            while (!equals(i, i2)) {
                if (hasNext(i)) {
                    this.base.consume(this.offset + (this.strides[0] * (i >>> 1)), 1, consumer);
                    i = nextPos(i);
                } else {
                    throw new RuntimeException();
                }
            }
        }
    }
}
