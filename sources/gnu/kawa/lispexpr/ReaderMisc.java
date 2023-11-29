package gnu.kawa.lispexpr;

public class ReaderMisc extends ReadTableEntry {
    int kind;

    public ReaderMisc(int i) {
        this.kind = i;
    }

    public int getKind() {
        return this.kind;
    }
}
