package gnu.lists;

public class FilterConsumer implements XConsumer {
    protected Object attributeType;
    protected Consumer base;
    protected boolean inAttribute;
    protected boolean skipping;

    /* access modifiers changed from: protected */
    public void beforeContent() {
    }

    /* access modifiers changed from: protected */
    public void beforeNode() {
    }

    public FilterConsumer(Consumer consumer) {
        this.base = consumer;
    }

    public void write(int i) {
        beforeContent();
        if (!this.skipping) {
            this.base.write(i);
        }
    }

    public void writeBoolean(boolean z) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeBoolean(z);
        }
    }

    public void writeFloat(float f) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeFloat(f);
        }
    }

    public void writeDouble(double d) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeDouble(d);
        }
    }

    public void writeInt(int i) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeInt(i);
        }
    }

    public void writeLong(long j) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeLong(j);
        }
    }

    public void startDocument() {
        if (!this.skipping) {
            this.base.startDocument();
        }
    }

    public void endDocument() {
        if (!this.skipping) {
            this.base.endDocument();
        }
    }

    public void startElement(Object obj) {
        if (!this.skipping) {
            beforeNode();
            this.base.startElement(obj);
        }
    }

    public void endElement() {
        if (!this.skipping) {
            this.base.endElement();
        }
    }

    public void startAttribute(Object obj) {
        this.attributeType = obj;
        this.inAttribute = true;
        if (!this.skipping) {
            beforeNode();
            this.base.startAttribute(obj);
        }
    }

    public void endAttribute() {
        if (!this.skipping) {
            this.base.endAttribute();
        }
        this.inAttribute = false;
    }

    public void writeComment(char[] cArr, int i, int i2) {
        if (!this.skipping) {
            beforeNode();
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeComment(cArr, i, i2);
            }
        }
    }

    public void writeProcessingInstruction(String str, char[] cArr, int i, int i2) {
        if (!this.skipping) {
            beforeNode();
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeProcessingInstruction(str, cArr, i, i2);
            }
        }
    }

    public void writeCDATA(char[] cArr, int i, int i2) {
        beforeContent();
        if (!this.skipping) {
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).writeCDATA(cArr, i, i2);
            } else {
                consumer.write(cArr, i, i2);
            }
        }
    }

    public void beginEntity(Object obj) {
        if (!this.skipping) {
            beforeNode();
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).beginEntity(obj);
            }
        }
    }

    public void endEntity() {
        if (!this.skipping) {
            Consumer consumer = this.base;
            if (consumer instanceof XConsumer) {
                ((XConsumer) consumer).endEntity();
            }
        }
    }

    public void writeObject(Object obj) {
        beforeContent();
        if (!this.skipping) {
            this.base.writeObject(obj);
        }
    }

    public boolean ignoring() {
        return this.base.ignoring();
    }

    public void write(char[] cArr, int i, int i2) {
        beforeContent();
        if (!this.skipping) {
            this.base.write(cArr, i, i2);
        }
    }

    public void write(String str) {
        write((CharSequence) str, 0, str.length());
    }

    public void write(CharSequence charSequence, int i, int i2) {
        beforeContent();
        if (!this.skipping) {
            this.base.write(charSequence, i, i2);
        }
    }

    public Consumer append(char c) {
        write((int) c);
        return this;
    }

    public Consumer append(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence, 0, charSequence.length());
        return this;
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        write(charSequence, i, i2 - i);
        return this;
    }
}
