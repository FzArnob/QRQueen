package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class KElement extends KNode implements Element {
    public short getNodeType() {
        return 1;
    }

    public String getNodeValue() {
        return null;
    }

    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    public KElement(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public String getTagName() {
        return this.sequence.getNextTypeName(this.ipos);
    }

    public boolean hasAttributes() {
        return ((NodeTree) this.sequence).posHasAttributes(this.ipos);
    }

    public String getAttribute(String str) {
        if (str == null) {
            str = "";
        }
        NodeTree nodeTree = (NodeTree) this.sequence;
        int attribute = nodeTree.getAttribute(this.ipos, (String) null, str);
        if (attribute == 0) {
            return "";
        }
        return KNode.getNodeValue(nodeTree, attribute);
    }

    public void setAttribute(String str, String str2) throws DOMException {
        throw new DOMException(7, "setAttribute not supported");
    }

    public void setIdAttribute(String str, boolean z) throws DOMException {
        throw new DOMException(7, "setIdAttribute not supported");
    }

    public void setIdAttributeNS(String str, String str2, boolean z) throws DOMException {
        throw new DOMException(7, "setIdAttributeNS not supported");
    }

    public void setIdAttributeNode(Attr attr, boolean z) throws DOMException {
        throw new DOMException(7, "setIdAttributeNode not supported");
    }

    public void removeAttribute(String str) throws DOMException {
        throw new DOMException(7, "removeAttribute not supported");
    }

    public KAttr getAttributeNode(String str) {
        if (str == null) {
            str = "";
        }
        NodeTree nodeTree = (NodeTree) this.sequence;
        int attribute = nodeTree.getAttribute(this.ipos, (String) null, str);
        if (attribute == 0) {
            return null;
        }
        return new KAttr(nodeTree, attribute);
    }

    public Attr setAttributeNode(Attr attr) throws DOMException {
        throw new DOMException(7, "setAttributeNode not supported");
    }

    public Attr removeAttributeNode(Attr attr) throws DOMException {
        throw new DOMException(7, "removeAttributeNode not supported");
    }

    public String getAttributeNS(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        NodeTree nodeTree = (NodeTree) this.sequence;
        int attribute = nodeTree.getAttribute(this.ipos, str, str2);
        if (attribute == 0) {
            return "";
        }
        return getNodeValue(nodeTree, attribute);
    }

    public void setAttributeNS(String str, String str2, String str3) throws DOMException {
        throw new DOMException(7, "setAttributeNS not supported");
    }

    public void removeAttributeNS(String str, String str2) throws DOMException {
        throw new DOMException(7, "removeAttributeNS not supported");
    }

    public KAttr getAttributeNodeNS(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        NodeTree nodeTree = (NodeTree) this.sequence;
        int attribute = nodeTree.getAttribute(this.ipos, str, str2);
        if (attribute == 0) {
            return null;
        }
        return new KAttr(nodeTree, attribute);
    }

    public Attr setAttributeNodeNS(Attr attr) throws DOMException {
        throw new DOMException(7, "setAttributeNodeNS not supported");
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        throw new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
    }

    public boolean hasAttribute(String str) {
        NodeTree nodeTree = (NodeTree) this.sequence;
        int i = this.ipos;
        if (str == null) {
            str = "";
        }
        return nodeTree.getAttribute(i, (String) null, str) != 0;
    }

    public boolean hasAttributeNS(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return ((NodeTree) this.sequence).getAttribute(this.ipos, str, str2) != 0;
    }
}
