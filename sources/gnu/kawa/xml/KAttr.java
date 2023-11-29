package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class KAttr extends KNode implements Attr {
    public short getNodeType() {
        return 2;
    }

    public Node getParentNode() {
        return null;
    }

    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    public boolean getSpecified() {
        return true;
    }

    public boolean isId() {
        return false;
    }

    public KAttr(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public String getName() {
        return this.sequence.getNextTypeName(this.ipos);
    }

    public String getValue() {
        return getNodeValue();
    }

    public static Object getObjectValue(NodeTree nodeTree, int i) {
        return nodeTree.getPosNext(i + 10);
    }

    public Object getObjectValue() {
        return getObjectValue((NodeTree) this.sequence, this.ipos);
    }

    public void setValue(String str) throws DOMException {
        throw new DOMException(7, "setValue not supported");
    }

    public Element getOwnerElement() {
        return (Element) super.getParentNode();
    }
}
