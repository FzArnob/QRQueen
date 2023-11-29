package gnu.bytecode;

import androidx.appcompat.widget.ActivityChooserView;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CpoolEntry {
    int hash;
    public int index;
    CpoolEntry next;

    public abstract int getTag();

    public abstract void print(ClassTypeWriter classTypeWriter, int i);

    /* access modifiers changed from: package-private */
    public abstract void write(DataOutputStream dataOutputStream) throws IOException;

    public int getIndex() {
        return this.index;
    }

    public int hashCode() {
        return this.hash;
    }

    /* access modifiers changed from: package-private */
    public void add_hashed(ConstantPool constantPool) {
        CpoolEntry[] cpoolEntryArr = constantPool.hashTab;
        int length = (this.hash & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % cpoolEntryArr.length;
        this.next = cpoolEntryArr[length];
        cpoolEntryArr[length] = this;
    }

    protected CpoolEntry() {
    }

    public CpoolEntry(ConstantPool constantPool, int i) {
        this.hash = i;
        if (!constantPool.locked) {
            int i2 = constantPool.count + 1;
            constantPool.count = i2;
            this.index = i2;
            if (constantPool.pool == null) {
                constantPool.pool = new CpoolEntry[60];
            } else if (this.index >= constantPool.pool.length) {
                int length = constantPool.pool.length;
                CpoolEntry[] cpoolEntryArr = new CpoolEntry[(constantPool.pool.length * 2)];
                for (int i3 = 0; i3 < length; i3++) {
                    cpoolEntryArr[i3] = constantPool.pool[i3];
                }
                constantPool.pool = cpoolEntryArr;
            }
            if (constantPool.hashTab == null || ((double) this.index) >= ((double) constantPool.hashTab.length) * 0.6d) {
                constantPool.rehash();
            }
            constantPool.pool[this.index] = this;
            add_hashed(constantPool);
            return;
        }
        throw new Error("adding new entry to locked contant pool");
    }
}
