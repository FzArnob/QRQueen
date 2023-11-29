package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;

public class KText extends KCharacterData implements Text {
    public String getNodeName() {
        return "#text";
    }

    public short getNodeType() {
        return 3;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean isElementContentWhitespace() {
        return false;
    }

    public KText(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public static KText make(String str) {
        NodeTree nodeTree = new NodeTree();
        nodeTree.append((CharSequence) str);
        return new KText(nodeTree, 0);
    }

    public Text splitText(int i) throws DOMException {
        throw new DOMException(7, "splitText not supported");
    }

    public String getWholeText() {
        throw new UnsupportedOperationException("getWholeText not implemented yet");
    }

    public Text replaceWholeText(String str) throws DOMException {
        throw new DOMException(7, "splitText not supported");
    }
}
