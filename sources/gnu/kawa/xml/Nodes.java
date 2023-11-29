package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Nodes extends Values implements NodeList {
    static final int POS_SIZE = 5;
    int count;
    XMLFilter curFragment;
    NodeTree curNode;
    boolean inAttribute;
    int nesting = 0;

    public void writePosition(AbstractSequence abstractSequence, int i) {
        this.count++;
        super.writePosition(abstractSequence, i);
    }

    public int find(Object obj) {
        if (this.gapStart > 0) {
            int intN = getIntN((this.gapStart - 5) + 1);
            if (this.objects[intN] == obj) {
                return intN;
            }
        }
        if (this.gapEnd < this.data.length) {
            int intN2 = getIntN(this.gapEnd + 1);
            if (this.objects[intN2] == obj) {
                return intN2;
            }
        }
        return super.find(obj);
    }

    public void writeObject(Object obj) {
        XMLFilter xMLFilter = this.curFragment;
        if (xMLFilter != null) {
            if (this.nesting != 0 || (!(obj instanceof SeqPosition) && !(obj instanceof TreeList))) {
                xMLFilter.writeObject(obj);
                return;
            }
            finishFragment();
        }
        if (obj instanceof SeqPosition) {
            SeqPosition seqPosition = (SeqPosition) obj;
            writePosition(seqPosition.sequence, seqPosition.ipos);
        } else if (obj instanceof TreeList) {
            writePosition((TreeList) obj, 0);
        } else {
            handleNonNode();
            this.curFragment.writeObject(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeStartTextNode() {
        if (this.curFragment == null) {
            throw new IllegalArgumentException("non-node where node required");
        }
    }

    /* access modifiers changed from: package-private */
    public void handleNonNode() {
        if (this.curFragment == null) {
            throw new ClassCastException("atomic value where node is required");
        }
    }

    public void writeFloat(float f) {
        handleNonNode();
        this.curFragment.writeFloat(f);
    }

    public void writeDouble(double d) {
        handleNonNode();
        this.curFragment.writeDouble(d);
    }

    public void writeLong(long j) {
        handleNonNode();
        this.curFragment.writeLong(j);
    }

    public void writeInt(int i) {
        handleNonNode();
        this.curFragment.writeInt(i);
    }

    public void writeBoolean(boolean z) {
        handleNonNode();
        this.curFragment.writeBoolean(z);
    }

    public void write(int i) {
        maybeStartTextNode();
        this.curFragment.write(i);
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        maybeStartTextNode();
        this.curFragment.write(charSequence, i, i2);
        return this;
    }

    public void write(char[] cArr, int i, int i2) {
        maybeStartTextNode();
        this.curFragment.write(cArr, i, i2);
    }

    public void write(CharSequence charSequence, int i, int i2) {
        maybeStartTextNode();
        this.curFragment.write(charSequence, i, i2);
    }

    public void write(String str) {
        maybeStartTextNode();
        this.curFragment.write(str);
    }

    private void maybeStartNonTextNode() {
        if (this.curFragment != null && this.nesting == 0) {
            finishFragment();
        }
        if (this.curFragment == null) {
            startFragment();
        }
        this.nesting++;
    }

    private void maybeEndNonTextNode() {
        int i = this.nesting - 1;
        this.nesting = i;
        if (i == 0) {
            finishFragment();
        }
    }

    public void startElement(Object obj) {
        maybeStartNonTextNode();
        this.curFragment.startElement(obj);
    }

    public void endElement() {
        this.curFragment.endElement();
        maybeEndNonTextNode();
    }

    public void startAttribute(Object obj) {
        maybeStartNonTextNode();
        this.curFragment.startAttribute(obj);
        this.inAttribute = true;
    }

    public void endAttribute() {
        if (this.inAttribute) {
            this.inAttribute = false;
            this.curFragment.endAttribute();
            maybeEndNonTextNode();
        }
    }

    public void writeComment(char[] cArr, int i, int i2) {
        maybeStartNonTextNode();
        this.curFragment.writeComment(cArr, i, i2);
        maybeEndNonTextNode();
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        maybeStartNonTextNode();
        this.curFragment.writeCDATA(cArr, i, i2);
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        maybeStartNonTextNode();
        this.curFragment.writeProcessingInstruction(str, cArr, i, i2);
        maybeEndNonTextNode();
    }

    public void startDocument() {
        maybeStartNonTextNode();
        this.curFragment.startDocument();
    }

    public void endDocument() {
        this.curFragment.endDocument();
        maybeEndNonTextNode();
    }

    public void beginEntity(Object obj) {
        maybeStartNonTextNode();
        this.curFragment.beginEntity(obj);
    }

    public void endEntity() {
        this.curFragment.endEntity();
        maybeEndNonTextNode();
    }

    /* access modifiers changed from: package-private */
    public void startFragment() {
        NodeTree nodeTree = new NodeTree();
        this.curNode = nodeTree;
        this.curFragment = new XMLFilter(nodeTree);
        writePosition(this.curNode, 0);
    }

    /* access modifiers changed from: package-private */
    public void finishFragment() {
        this.curNode = null;
        this.curFragment = null;
    }

    public int size() {
        return this.count;
    }

    public int getLength() {
        return this.count;
    }

    public Object get(int i) {
        int i2 = i * 5;
        if (i2 >= this.gapStart) {
            i2 += this.gapEnd - this.gapStart;
        }
        if (i2 < 0 || i2 >= this.data.length) {
            throw new IndexOutOfBoundsException();
        } else if (this.data[i2] == 61711) {
            return KNode.make((NodeTree) this.objects[getIntN(i2 + 1)], getIntN(i2 + 3));
        } else {
            throw new RuntimeException("internal error - unexpected data");
        }
    }

    public Node item(int i) {
        if (i >= this.count) {
            return null;
        }
        return (Node) get(i);
    }

    public Object getPosNext(int i) {
        int posToDataIndex = posToDataIndex(i);
        if (posToDataIndex == this.data.length) {
            return Sequence.eofValue;
        }
        if (this.data[posToDataIndex] == 61711) {
            return KNode.make((NodeTree) this.objects[getIntN(posToDataIndex + 1)], getIntN(posToDataIndex + 3));
        }
        throw new RuntimeException("internal error - unexpected data");
    }

    public AbstractSequence getSeq(int i) {
        int i2 = i * 5;
        if (i2 >= this.gapStart) {
            i2 += this.gapEnd - this.gapStart;
        }
        if (i2 < 0 || i2 >= this.data.length) {
            return null;
        }
        if (this.data[i2] == 61711) {
            return (AbstractSequence) this.objects[getIntN(i2 + 1)];
        }
        throw new RuntimeException("internal error - unexpected data");
    }

    public int getPos(int i) {
        int i2 = i * 5;
        if (i2 >= this.gapStart) {
            i2 += this.gapEnd - this.gapStart;
        }
        if (this.data[i2] == 61711) {
            return getIntN(i2 + 3);
        }
        throw new RuntimeException("internal error - unexpected data");
    }

    public static KNode root(NodeTree nodeTree, int i) {
        int i2 = 0;
        if (nodeTree.gapStart > 5 && nodeTree.data[0] == 61714) {
            i2 = 10;
        }
        return KNode.make(nodeTree, i2);
    }
}
