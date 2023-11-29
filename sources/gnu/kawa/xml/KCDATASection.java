package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.CDATASection;

public class KCDATASection extends KText implements CDATASection {
    public String getNodeName() {
        return "#cdata-section";
    }

    public short getNodeType() {
        return 4;
    }

    public KCDATASection(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public String getData() {
        return getNodeValue();
    }

    public int getLength() {
        StringBuffer stringBuffer = new StringBuffer();
        NodeTree nodeTree = (NodeTree) this.sequence;
        nodeTree.stringValue(nodeTree.posToDataIndex(this.ipos), stringBuffer);
        return stringBuffer.length();
    }
}
