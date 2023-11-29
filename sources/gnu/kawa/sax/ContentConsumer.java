package gnu.kawa.sax;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.xml.XName;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ContentConsumer implements Consumer {
    String attrLocalName;
    String attrQName;
    String attrURI;
    AttributesImpl attributes = new AttributesImpl();
    char[] chBuffer;
    int inStartTag;
    String[] names = new String[15];
    int nesting = 0;
    ContentHandler out;
    StringBuilder strBuffer = new StringBuilder(200);

    public boolean ignoring() {
        return false;
    }

    public ContentConsumer() {
    }

    public ContentConsumer(ContentHandler contentHandler) {
        this.out = contentHandler;
    }

    public void error(String str, SAXException sAXException) {
        throw new RuntimeException("caught " + sAXException + " in " + str);
    }

    public void endStartTag() {
        if (this.inStartTag == 1) {
            int i = (this.nesting - 1) * 3;
            try {
                ContentHandler contentHandler = this.out;
                String[] strArr = this.names;
                contentHandler.startElement(strArr[i], strArr[i + 1], strArr[i + 2], this.attributes);
            } catch (SAXException e) {
                error("startElement", e);
            }
            this.attributes.clear();
            this.inStartTag = 0;
        }
    }

    public void startElement(Object obj) {
        String str;
        String str2;
        if (this.inStartTag == 1) {
            endStartTag();
        }
        flushStrBuffer();
        int i = this.nesting * 3;
        String[] strArr = this.names;
        if (i >= strArr.length) {
            String[] strArr2 = new String[(i * 2)];
            System.arraycopy(strArr, 0, strArr2, 0, i);
            this.names = strArr2;
        }
        if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            str = symbol.getNamespaceURI();
            str2 = symbol.getLocalName();
        } else if (obj instanceof XName) {
            XName xName = (XName) obj;
            str = xName.getNamespaceURI();
            str2 = xName.getLocalName();
        } else {
            str2 = obj.toString();
            str = "";
        }
        String[] strArr3 = this.names;
        strArr3[i] = str;
        strArr3[i + 1] = str2;
        strArr3[i + 2] = obj.toString();
        this.inStartTag = 1;
        this.nesting++;
    }

    public void startAttribute(Object obj) {
        Symbol symbol = (Symbol) obj;
        this.attrURI = symbol.getNamespaceURI();
        this.attrLocalName = symbol.getLocalName();
        this.attrQName = obj.toString();
        this.inStartTag = 2;
    }

    public void endAttribute() {
        this.attributes.addAttribute(this.attrURI, this.attrLocalName, this.attrQName, "CDATA", this.strBuffer.toString());
        this.strBuffer.setLength(0);
        this.inStartTag = 1;
    }

    public void startDocument() {
        try {
            this.out.startDocument();
        } catch (SAXException e) {
            error("startDocument", e);
        }
    }

    public void endDocument() {
        try {
            this.out.endDocument();
        } catch (SAXException e) {
            error("endDocument", e);
        }
    }

    public void endElement() {
        endStartTag();
        flushStrBuffer();
        int i = this.nesting - 1;
        this.nesting = i;
        int i2 = i * 3;
        try {
            ContentHandler contentHandler = this.out;
            String[] strArr = this.names;
            contentHandler.endElement(strArr[i2], strArr[i2 + 1], strArr[i2 + 2]);
        } catch (SAXException e) {
            error("endElement", e);
        }
        String[] strArr2 = this.names;
        strArr2[i2] = null;
        strArr2[i2 + 1] = null;
        strArr2[i2 + 2] = null;
    }

    /* access modifiers changed from: package-private */
    public void flushStrBuffer() {
        if (this.strBuffer.length() > 0) {
            if (this.chBuffer == null) {
                this.chBuffer = new char[200];
            }
            try {
                int length = this.strBuffer.length();
                int i = 0;
                while (true) {
                    int i2 = length - i;
                    if (i2 <= 0) {
                        this.strBuffer.setLength(0);
                        return;
                    }
                    char[] cArr = this.chBuffer;
                    if (i2 > cArr.length) {
                        i2 = cArr.length;
                    }
                    int i3 = i + i2;
                    this.strBuffer.getChars(i, i3, cArr, i);
                    this.out.characters(this.chBuffer, 0, i2);
                    i = i3;
                }
            } catch (SAXException e) {
                error("characters", e);
            }
        }
    }

    public void write(char[] cArr, int i, int i2) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        if (this.inStartTag == 2) {
            this.strBuffer.append(cArr, i, i2);
            return;
        }
        flushStrBuffer();
        try {
            this.out.characters(cArr, i, i2);
        } catch (SAXException e) {
            error("characters", e);
        }
    }

    public void write(int i) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        if (i >= 65536) {
            this.strBuffer.append((char) (((i - 65536) >> 10) + 55296));
            i = (i & 1023) + 56320;
        }
        this.strBuffer.append((char) i);
    }

    public void write(String str) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(str);
    }

    public void write(CharSequence charSequence, int i, int i2) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(charSequence, i, i2);
    }

    public ContentConsumer append(char c) {
        write((int) c);
        return this;
    }

    public ContentConsumer append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence, 0, charSequence.length());
        return this;
    }

    public ContentConsumer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence, i, i2);
        return this;
    }

    public void writeObject(Object obj) {
        String str;
        if (obj instanceof Consumable) {
            ((Consumable) obj).consume(this);
        } else if (obj instanceof SeqPosition) {
            SeqPosition seqPosition = (SeqPosition) obj;
            seqPosition.sequence.consumeNext(seqPosition.ipos, this);
        } else if (obj instanceof Char) {
            ((Char) obj).print(this);
        } else {
            if (obj == null) {
                str = "(null)";
            } else {
                str = obj.toString();
            }
            write(str);
        }
    }

    public void writeBoolean(boolean z) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(z);
    }

    public void writeLong(long j) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(j);
    }

    public void writeInt(int i) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(i);
    }

    public void writeFloat(float f) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(f);
    }

    public void writeDouble(double d) {
        if (this.inStartTag == 1) {
            endStartTag();
        }
        this.strBuffer.append(d);
    }

    public void finalize() {
        flushStrBuffer();
    }

    public void setContentHandler(ContentHandler contentHandler) {
        this.out = contentHandler;
    }

    public ContentHandler getContentHandler() {
        return this.out;
    }
}
