package gnu.xml;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.Symbol;

public class NamedChildrenFilter extends FilterConsumer {
    int level;
    String localName;
    int matchLevel;
    String namespaceURI;

    public static NamedChildrenFilter make(String str, String str2, Consumer consumer) {
        return new NamedChildrenFilter(str, str2, consumer);
    }

    public NamedChildrenFilter(String str, String str2, Consumer consumer) {
        super(consumer);
        this.namespaceURI = str;
        this.localName = str2;
        this.skipping = true;
    }

    public void startDocument() {
        this.level++;
        super.startDocument();
    }

    public void endDocument() {
        this.level--;
        super.endDocument();
    }

    public void startElement(Object obj) {
        String str;
        String str2;
        String str3;
        if (this.skipping && this.level == 1) {
            if (obj instanceof Symbol) {
                Symbol symbol = (Symbol) obj;
                str = symbol.getNamespaceURI();
                str2 = symbol.getLocalName();
            } else {
                str2 = obj.toString().intern();
                str = "";
            }
            String str4 = this.localName;
            if ((str4 == str2 || str4 == null) && ((str3 = this.namespaceURI) == str || str3 == null)) {
                this.skipping = false;
                this.matchLevel = this.level;
            }
        }
        super.startElement(obj);
        this.level++;
    }

    public void endElement() {
        this.level--;
        super.endElement();
        if (!this.skipping && this.matchLevel == this.level) {
            this.skipping = true;
        }
    }

    public void writeObject(Object obj) {
        if (obj instanceof SeqPosition) {
            SeqPosition seqPosition = (SeqPosition) obj;
            if (seqPosition.sequence instanceof TreeList) {
                ((TreeList) seqPosition.sequence).consumeNext(seqPosition.ipos, this);
                return;
            }
        }
        if (obj instanceof Consumable) {
            ((Consumable) obj).consume(this);
        } else {
            super.writeObject(obj);
        }
    }
}
