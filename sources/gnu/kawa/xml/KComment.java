package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Comment;

public class KComment extends KCharacterData implements Comment {
    public String getNodeName() {
        return "#comment";
    }

    public short getNodeType() {
        return 8;
    }

    public KComment(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public static KComment valueOf(String str) {
        NodeTree nodeTree = new NodeTree();
        nodeTree.writeComment(str, 0, str.length());
        return new KComment(nodeTree, 0);
    }
}
