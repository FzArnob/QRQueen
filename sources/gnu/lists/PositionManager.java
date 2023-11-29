package gnu.lists;

public class PositionManager {
    static final PositionManager manager = new PositionManager();
    int freeListHead;
    int[] ivals;
    SeqPosition[] positions = new SeqPosition[50];

    public static SeqPosition getPositionObject(int i) {
        SeqPosition seqPosition;
        PositionManager positionManager = manager;
        synchronized (positionManager) {
            seqPosition = positionManager.positions[i];
        }
        return seqPosition;
    }

    private void addToFreeList(int[] iArr, int i, int i2) {
        int i3 = this.freeListHead;
        while (i < i2) {
            iArr[i] = i3;
            i3 = i;
            i++;
        }
        this.freeListHead = i3;
    }

    private int getFreeSlot() {
        int i = this.freeListHead;
        if (i < 0) {
            SeqPosition[] seqPositionArr = this.positions;
            int length = seqPositionArr.length;
            int i2 = length * 2;
            SeqPosition[] seqPositionArr2 = new SeqPosition[i2];
            int[] iArr = new int[i2];
            System.arraycopy(seqPositionArr, 0, seqPositionArr2, 0, length);
            System.arraycopy(this.ivals, 0, iArr, 0, length);
            this.positions = seqPositionArr2;
            this.ivals = iArr;
            addToFreeList(iArr, length, i2);
            i = this.freeListHead;
        }
        this.freeListHead = this.ivals[i];
        return i;
    }

    public PositionManager() {
        int[] iArr = new int[50];
        this.ivals = iArr;
        this.freeListHead = -1;
        addToFreeList(iArr, 1, iArr.length);
    }

    public synchronized int register(SeqPosition seqPosition) {
        int freeSlot;
        freeSlot = getFreeSlot();
        this.positions[freeSlot] = seqPosition;
        this.ivals[freeSlot] = -1;
        return freeSlot;
    }

    public synchronized void release(int i) {
        SeqPosition seqPosition = this.positions[i];
        if (seqPosition instanceof ExtPosition) {
            ((ExtPosition) seqPosition).position = -1;
        }
        this.positions[i] = null;
        this.ivals[i] = this.freeListHead;
        this.freeListHead = i;
        seqPosition.release();
    }
}
