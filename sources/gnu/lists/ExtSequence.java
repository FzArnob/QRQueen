package gnu.lists;

public abstract class ExtSequence extends AbstractSequence {
    public int copyPos(int i) {
        return i <= 0 ? i : PositionManager.manager.register(PositionManager.getPositionObject(i).copy());
    }

    /* access modifiers changed from: protected */
    public void releasePos(int i) {
        if (i > 0) {
            PositionManager.manager.release(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAfterPos(int i) {
        return i <= 0 ? i < 0 : (PositionManager.getPositionObject(i).ipos & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public int nextIndex(int i) {
        if (i == -1) {
            return size();
        }
        if (i == 0) {
            return 0;
        }
        return PositionManager.getPositionObject(i).nextIndex();
    }
}
