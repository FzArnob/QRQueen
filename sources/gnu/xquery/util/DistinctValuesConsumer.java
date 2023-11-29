package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Values;
import gnu.xml.NodeTree;

/* compiled from: DistinctValues */
class DistinctValuesConsumer extends FilterConsumer implements PositionConsumer {
    DistinctValuesHashTable table;

    public DistinctValuesConsumer(NamedCollator namedCollator, Consumer consumer) {
        super(consumer);
        this.table = new DistinctValuesHashTable(namedCollator);
    }

    public void consume(SeqPosition seqPosition) {
        writeObject(seqPosition);
    }

    public void writePosition(AbstractSequence abstractSequence, int i) {
        writeObject(((NodeTree) abstractSequence).typedValue(i));
    }

    public void writeBoolean(boolean z) {
        writeObject(z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void writeObject(Object obj) {
        if (obj instanceof Values) {
            Values.writeValues(obj, this);
        } else if (obj instanceof KNode) {
            KNode kNode = (KNode) obj;
            writeObject(((NodeTree) kNode.sequence).typedValue(kNode.ipos));
        } else if (this.table.get(obj, null) == null) {
            this.table.put(obj, obj);
            this.base.writeObject(obj);
        }
    }
}
