package gnu.text;

import gnu.kawa.util.AbstractWeakHashTable;

/* compiled from: Char */
class CharMap extends AbstractWeakHashTable<Char, Char> {
    /* access modifiers changed from: protected */
    public Char getKeyFromValue(Char charR) {
        return charR;
    }

    CharMap() {
    }

    public Char get(int i) {
        cleanup();
        for (AbstractWeakHashTable.WEntry wEntry = ((AbstractWeakHashTable.WEntry[]) this.table)[hashToIndex(i)]; wEntry != null; wEntry = wEntry.next) {
            Char charR = (Char) wEntry.getValue();
            if (charR != null && charR.intValue() == i) {
                return charR;
            }
        }
        Char charR2 = new Char(i);
        super.put(charR2, charR2);
        return charR2;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Char charR, Char charR2) {
        return charR.intValue() == charR2.intValue();
    }
}
