package gnu.kawa.xml;

import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class KDocument extends KNode implements Document {
    public DocumentType getDoctype() {
        return null;
    }

    public String getDocumentURI() {
        return null;
    }

    public Element getElementById(String str) {
        return null;
    }

    public String getInputEncoding() {
        return null;
    }

    public String getNodeName() {
        return "#document";
    }

    public short getNodeType() {
        return 9;
    }

    public String getNodeValue() {
        return null;
    }

    public Node getParentNode() {
        return null;
    }

    public boolean getStrictErrorChecking() {
        return false;
    }

    public String getTextContent() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void getTextContent(StringBuffer stringBuffer) {
    }

    public String getXmlEncoding() {
        return null;
    }

    public boolean getXmlStandalone() {
        return false;
    }

    public String getXmlVersion() {
        return "1.1";
    }

    public boolean hasAttributes() {
        return false;
    }

    public void normalizeDocument() {
    }

    public void setDocumentURI(String str) {
    }

    public void setStrictErrorChecking(boolean z) {
    }

    public void setXmlStandalone(boolean z) {
    }

    public void setXmlVersion(String str) {
    }

    public KDocument(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public DOMImplementation getImplementation() {
        throw new UnsupportedOperationException("getImplementation not implemented");
    }

    public KElement getDocumentElement() {
        int posFirstChild = ((NodeTree) this.sequence).posFirstChild(this.ipos);
        while (posFirstChild != -1) {
            if (this.sequence.getNextKind(posFirstChild) != 36) {
                return (KElement) make((NodeTree) this.sequence, posFirstChild);
            }
            posFirstChild = this.sequence.nextPos(posFirstChild);
        }
        return null;
    }

    public Element createElement(String str) {
        throw new UnsupportedOperationException("createElement not implemented");
    }

    public DocumentFragment createDocumentFragment() {
        throw new UnsupportedOperationException("createDocumentFragment not implemented");
    }

    public Text createTextNode(String str) {
        throw new UnsupportedOperationException("createTextNode not implemented");
    }

    public Comment createComment(String str) {
        throw new UnsupportedOperationException("createComment not implemented");
    }

    public CDATASection createCDATASection(String str) {
        throw new UnsupportedOperationException("createCDATASection not implemented");
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        throw new UnsupportedOperationException("createProcessingInstruction not implemented");
    }

    public Attr createAttribute(String str) {
        throw new UnsupportedOperationException("createAttribute not implemented");
    }

    public EntityReference createEntityReference(String str) {
        throw new UnsupportedOperationException("createEntityReference implemented");
    }

    public Node importNode(Node node, boolean z) {
        throw new UnsupportedOperationException("importNode not implemented");
    }

    public Element createElementNS(String str, String str2) {
        throw new UnsupportedOperationException("createElementNS not implemented");
    }

    public Attr createAttributeNS(String str, String str2) {
        throw new UnsupportedOperationException("createAttributeNS not implemented");
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        throw new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
    }

    public Node renameNode(Node node, String str, String str2) throws DOMException {
        throw new DOMException(9, "renameNode not implemented");
    }

    public Node adoptNode(Node node) throws DOMException {
        throw new DOMException(9, "adoptNode not implemented");
    }

    public DOMConfiguration getDomConfig() {
        throw new DOMException(9, "getDomConfig not implemented");
    }
}
