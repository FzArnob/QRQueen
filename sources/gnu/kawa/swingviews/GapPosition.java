package gnu.kawa.swingviews;

import gnu.lists.CharBuffer;
import gnu.lists.SeqPosition;
import javax.swing.text.Position;

/* compiled from: SwingContent */
class GapPosition extends SeqPosition implements Position {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GapPosition(CharBuffer charBuffer, int i) {
        super(charBuffer, i, i != 0);
    }

    public int getOffset() {
        return nextIndex();
    }
}
