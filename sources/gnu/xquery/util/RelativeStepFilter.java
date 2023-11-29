package gnu.xquery.util;

import gnu.kawa.xml.SortedNodes;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;

public class RelativeStepFilter extends FilterConsumer implements PositionConsumer {
    char seen;
    SortedNodes snodes;

    public RelativeStepFilter(Consumer consumer) {
        super(consumer);
    }

    public void consume(SeqPosition seqPosition) {
        writePosition(seqPosition.sequence, seqPosition.ipos);
    }

    public void writeObject(Object obj) {
        if (obj instanceof SeqPosition) {
            SeqPosition seqPosition = (SeqPosition) obj;
            writePosition(seqPosition.sequence, seqPosition.ipos);
            return;
        }
        super.writeObject(obj);
    }

    /* access modifiers changed from: protected */
    public void beforeContent() {
        if (this.seen != 'N') {
            this.seen = 'A';
            return;
        }
        throw new Error("path returns mix of atoms and nodes");
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        if (this.seen != 'A') {
            this.seen = 'N';
            if (this.snodes == null) {
                this.snodes = new SortedNodes();
            }
            this.snodes.writePosition(abstractSequence, i);
            return;
        }
        throw new Error("path returns mix of atoms and nodes");
    }

    public void finish() {
        SortedNodes sortedNodes = this.snodes;
        if (sortedNodes != null) {
            sortedNodes.consume(this.base);
        }
        this.snodes = null;
    }
}
