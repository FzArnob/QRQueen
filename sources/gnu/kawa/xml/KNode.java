package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreePosition;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.xml.NodeTree;
import gnu.xml.XMLPrinter;
import java.io.Writer;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class KNode extends SeqPosition implements Node, Consumable {
    public Object getFeature(String str, String str2) {
        return null;
    }

    public abstract short getNodeType();

    public Object getUserData(String str) {
        return null;
    }

    public boolean hasAttributes() {
        return false;
    }

    public boolean isSupported(String str, String str2) {
        return false;
    }

    public void normalize() {
    }

    public KNode(NodeTree nodeTree, int i) {
        super(nodeTree, i);
    }

    public static Object atomicValue(Object obj) {
        if (!(obj instanceof KNode)) {
            return obj;
        }
        KNode kNode = (KNode) obj;
        return ((NodeTree) kNode.sequence).typedValue(kNode.ipos);
    }

    public static KNode coerce(Object obj) {
        if (obj instanceof KNode) {
            return (KNode) obj;
        }
        if (obj instanceof NodeTree) {
            NodeTree nodeTree = (NodeTree) obj;
            return make(nodeTree, nodeTree.startPos());
        } else if (!(obj instanceof SeqPosition) || (obj instanceof TreePosition)) {
            return null;
        } else {
            SeqPosition seqPosition = (SeqPosition) obj;
            if (seqPosition.sequence instanceof NodeTree) {
                return make((NodeTree) seqPosition.sequence, seqPosition.ipos);
            }
            return null;
        }
    }

    public static KNode make(NodeTree nodeTree, int i) {
        int posToDataIndex = nodeTree.posToDataIndex(i);
        while (posToDataIndex < nodeTree.data.length && nodeTree.data[posToDataIndex] == 61714) {
            posToDataIndex += 5;
            if (posToDataIndex == nodeTree.gapStart) {
                posToDataIndex = nodeTree.gapEnd;
            }
            i = posToDataIndex << 1;
        }
        int nextKindI = nodeTree.getNextKindI(nodeTree.posToDataIndex(i));
        if (nextKindI != 0) {
            if (nextKindI == 31) {
                return new KCDATASection(nodeTree, i);
            }
            switch (nextKindI) {
                case 33:
                    return new KElement(nodeTree, i);
                case 34:
                    return new KDocument(nodeTree, i);
                case 35:
                    return new KAttr(nodeTree, i);
                case 36:
                    return new KComment(nodeTree, i);
                case 37:
                    return new KProcessingInstruction(nodeTree, i);
            }
        } else if (!nodeTree.isEmpty()) {
            return null;
        }
        return new KText(nodeTree, i);
    }

    public KNode copy() {
        return make((NodeTree) this.sequence, this.sequence.copyPos(getPos()));
    }

    public static KNode make(NodeTree nodeTree) {
        return make(nodeTree, 0);
    }

    public String getNodeName() {
        return this.sequence.getNextTypeName(this.ipos);
    }

    public Symbol getNodeSymbol() {
        Object nextTypeObject = ((NodeTree) this.sequence).getNextTypeObject(this.ipos);
        if (nextTypeObject == null) {
            return null;
        }
        if (nextTypeObject instanceof Symbol) {
            return (Symbol) nextTypeObject;
        }
        return Namespace.EmptyNamespace.getSymbol(nextTypeObject.toString().intern());
    }

    public Object getNodeNameObject() {
        return ((NodeTree) this.sequence).getNextTypeObject(this.ipos);
    }

    public String getNamespaceURI() {
        return ((NodeTree) this.sequence).posNamespaceURI(this.ipos);
    }

    public String getPrefix() {
        return ((NodeTree) this.sequence).posPrefix(this.ipos);
    }

    public String getLocalName() {
        return ((NodeTree) this.sequence).posLocalName(this.ipos);
    }

    public static String getNodeValue(NodeTree nodeTree, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        getNodeValue(nodeTree, i, stringBuffer);
        return stringBuffer.toString();
    }

    public static void getNodeValue(NodeTree nodeTree, int i, StringBuffer stringBuffer) {
        nodeTree.stringValue(nodeTree.posToDataIndex(i), stringBuffer);
    }

    public String getNodeValue() {
        StringBuffer stringBuffer = new StringBuffer();
        getNodeValue(stringBuffer);
        return stringBuffer.toString();
    }

    public void getNodeValue(StringBuffer stringBuffer) {
        getNodeValue((NodeTree) this.sequence, this.ipos, stringBuffer);
    }

    public boolean hasChildNodes() {
        return ((NodeTree) this.sequence).posFirstChild(this.ipos) >= 0;
    }

    public String getTextContent() {
        StringBuffer stringBuffer = new StringBuffer();
        getTextContent(stringBuffer);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public void getTextContent(StringBuffer stringBuffer) {
        getNodeValue(stringBuffer);
    }

    public Node getParentNode() {
        int parentPos = this.sequence.parentPos(this.ipos);
        if (parentPos == -1) {
            return null;
        }
        return make((NodeTree) this.sequence, parentPos);
    }

    public Node getPreviousSibling() {
        int parentPos = this.sequence.parentPos(this.ipos);
        if (parentPos == -1) {
            parentPos = 0;
        }
        int posToDataIndex = ((NodeTree) this.sequence).posToDataIndex(this.ipos);
        int firstChildPos = this.sequence.firstChildPos(parentPos);
        while (true) {
            int nextPos = this.sequence.nextPos(firstChildPos);
            if (!(nextPos == 0 || ((NodeTree) this.sequence).posToDataIndex(nextPos) == posToDataIndex)) {
                firstChildPos = nextPos;
            }
        }
        if (firstChildPos == 0) {
            return null;
        }
        return make((NodeTree) this.sequence, firstChildPos);
    }

    public Node getNextSibling() {
        int nextPos = ((NodeTree) this.sequence).nextPos(this.ipos);
        if (nextPos == 0) {
            return null;
        }
        return make((NodeTree) this.sequence, nextPos);
    }

    public Node getFirstChild() {
        return make((NodeTree) this.sequence, ((NodeTree) this.sequence).posFirstChild(this.ipos));
    }

    public Node getLastChild() {
        int firstChildPos = this.sequence.firstChildPos(this.ipos);
        int i = 0;
        while (firstChildPos != 0) {
            i = firstChildPos;
            firstChildPos = this.sequence.nextPos(firstChildPos);
        }
        if (i == 0) {
            return null;
        }
        return make((NodeTree) this.sequence, i);
    }

    public NodeList getChildNodes() {
        SortedNodes sortedNodes = new SortedNodes();
        int firstChildPos = this.sequence.firstChildPos(this.ipos);
        while (firstChildPos != 0) {
            sortedNodes.writePosition(this.sequence, firstChildPos);
            firstChildPos = this.sequence.nextPos(firstChildPos);
        }
        return sortedNodes;
    }

    public NodeList getElementsByTagName(String str) {
        throw new UnsupportedOperationException("getElementsByTagName not implemented yet");
    }

    public void setNodeValue(String str) throws DOMException {
        throw new DOMException(7, "setNodeValue not supported");
    }

    public void setPrefix(String str) throws DOMException {
        throw new DOMException(7, "setPrefix not supported");
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        throw new DOMException(7, "insertBefore not supported");
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        throw new DOMException(7, "replaceChild not supported");
    }

    public Node removeChild(Node node) throws DOMException {
        throw new DOMException(7, "removeChild not supported");
    }

    public Node appendChild(Node node) throws DOMException {
        throw new DOMException(7, "appendChild not supported");
    }

    public void setTextContent(String str) throws DOMException {
        throw new DOMException(7, "setTextContent not supported");
    }

    public Node cloneNode(boolean z) {
        if (z) {
            NodeTree nodeTree = new NodeTree();
            ((NodeTree) this.sequence).consumeNext(this.ipos, nodeTree);
            return make(nodeTree);
        }
        throw new UnsupportedOperationException("shallow cloneNode not implemented");
    }

    public Document getOwnerDocument() {
        if (this.sequence.getNextKind(this.ipos) == 34) {
            return new KDocument((NodeTree) this.sequence, 0);
        }
        return null;
    }

    public NamedNodeMap getAttributes() {
        throw new UnsupportedOperationException("getAttributes not implemented yet");
    }

    public boolean isDefaultNamespace(String str) {
        return ((NodeTree) this.sequence).posIsDefaultNamespace(this.ipos, str);
    }

    public String lookupNamespaceURI(String str) {
        return ((NodeTree) this.sequence).posLookupNamespaceURI(this.ipos, str);
    }

    public String lookupPrefix(String str) {
        return ((NodeTree) this.sequence).posLookupPrefix(this.ipos, str);
    }

    public String getBaseURI() {
        Path baseUriOfPos = ((NodeTree) this.sequence).baseUriOfPos(this.ipos, true);
        if (baseUriOfPos == null) {
            return null;
        }
        return baseUriOfPos.toString();
    }

    public Path baseURI() {
        return ((NodeTree) this.sequence).baseUriOfPos(this.ipos, true);
    }

    public short compareDocumentPosition(Node node) throws DOMException {
        if (node instanceof KNode) {
            KNode kNode = (KNode) node;
            AbstractSequence abstractSequence = kNode.sequence;
            return (short) (this.sequence == abstractSequence ? abstractSequence.compare(this.ipos, kNode.ipos) : this.sequence.stableCompare(abstractSequence));
        }
        throw new DOMException(9, "other Node is a " + node.getClass().getName());
    }

    public boolean isSameNode(Node node) {
        if (!(node instanceof KNode)) {
            return false;
        }
        KNode kNode = (KNode) node;
        if (this.sequence != kNode.sequence) {
            return false;
        }
        return this.sequence.equals(this.ipos, kNode.ipos);
    }

    public boolean isEqualNode(Node node) {
        throw new UnsupportedOperationException("getAttributesisEqualNode not implemented yet");
    }

    public String toString() {
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        XMLPrinter xMLPrinter = new XMLPrinter((Writer) charArrayOutPort);
        ((NodeTree) this.sequence).consumeNext(this.ipos, xMLPrinter);
        xMLPrinter.close();
        charArrayOutPort.close();
        return charArrayOutPort.toString();
    }

    public void consume(Consumer consumer) {
        if (consumer instanceof PositionConsumer) {
            ((PositionConsumer) consumer).consume(this);
        } else {
            ((NodeTree) this.sequence).consumeNext(this.ipos, consumer);
        }
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        throw new UnsupportedOperationException("setUserData not implemented yet");
    }
}
