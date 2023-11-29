package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.ProcessingInstruction;

public class KProcessingInstruction extends KNode implements ProcessingInstruction {
    public short getNodeType() {
        return 7;
    }

    public KProcessingInstruction(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public String getNodeName() {
        return getTarget();
    }

    public String getData() {
        return getNodeValue();
    }

    public void setData(String str) throws DOMException {
        throw new DOMException(7, "setData not supported");
    }

    public String getTarget() {
        return ((NodeTree) this.sequence).posTarget(this.ipos);
    }

    public static KProcessingInstruction valueOf(String str, String str2) {
        NodeTree nodeTree = new NodeTree();
        nodeTree.writeProcessingInstruction(str, str2, 0, str2.length());
        return new KProcessingInstruction(nodeTree, 0);
    }
}
