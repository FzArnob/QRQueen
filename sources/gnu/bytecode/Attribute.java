package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Attribute {
    AttrContainer container;
    String name;
    int name_index;
    Attribute next;

    public abstract int getLength();

    public abstract void write(DataOutputStream dataOutputStream) throws IOException;

    public final AttrContainer getContainer() {
        return this.container;
    }

    public final void setContainer(AttrContainer attrContainer) {
        this.container = attrContainer;
    }

    public final Attribute getNext() {
        return this.next;
    }

    public final void setNext(Attribute attribute) {
        this.next = attribute;
    }

    public void addToFrontOf(AttrContainer attrContainer) {
        setContainer(attrContainer);
        setNext(attrContainer.getAttributes());
        attrContainer.setAttributes(this);
    }

    public final boolean isSkipped() {
        return this.name_index < 0;
    }

    public final void setSkipped(boolean z) {
        this.name_index = z ? -1 : 0;
    }

    public final void setSkipped() {
        this.name_index = -1;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str.intern();
    }

    public final int getNameIndex() {
        return this.name_index;
    }

    public final void setNameIndex(int i) {
        this.name_index = i;
    }

    public Attribute(String str) {
        this.name = str;
    }

    public static Attribute get(AttrContainer attrContainer, String str) {
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            if (attributes.getName() == str) {
                return attributes;
            }
        }
        return null;
    }

    public void assignConstants(ClassType classType) {
        if (this.name_index == 0) {
            this.name_index = classType.getConstants().addUtf8(this.name).getIndex();
        }
    }

    public static void assignConstants(AttrContainer attrContainer, ClassType classType) {
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            if (!attributes.isSkipped()) {
                attributes.assignConstants(classType);
            }
        }
    }

    public static int getLengthAll(AttrContainer attrContainer) {
        int i = 0;
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            if (!attributes.isSkipped()) {
                i += attributes.getLength() + 6;
            }
        }
        return i;
    }

    public static int count(AttrContainer attrContainer) {
        int i = 0;
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            if (!attributes.isSkipped()) {
                i++;
            }
        }
        return i;
    }

    public static void writeAll(AttrContainer attrContainer, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(count(attrContainer));
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            if (!attributes.isSkipped()) {
                int i = attributes.name_index;
                if (i != 0) {
                    dataOutputStream.writeShort(i);
                    dataOutputStream.writeInt(attributes.getLength());
                    attributes.write(dataOutputStream);
                } else {
                    throw new Error("Attribute.writeAll called without assignConstants");
                }
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.println(getLength());
    }
}
