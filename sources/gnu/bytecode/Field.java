package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class Field extends Location implements AttrContainer, Member {
    Attribute attributes;
    int flags;
    Field next;
    ClassType owner;
    java.lang.reflect.Field rfield;
    String sourceName;

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attribute) {
        this.attributes = attribute;
    }

    public Field(ClassType classType) {
        if (classType.last_field == null) {
            classType.fields = this;
        } else {
            classType.last_field.next = this;
        }
        classType.last_field = this;
        classType.fields_count++;
        this.owner = classType;
    }

    public final ClassType getDeclaringClass() {
        return this.owner;
    }

    public final void setStaticFlag(boolean z) {
        if (z) {
            this.flags |= 8;
        } else {
            this.flags ^= -9;
        }
    }

    public final boolean getStaticFlag() {
        return (this.flags & 8) != 0;
    }

    public final int getFlags() {
        return this.flags;
    }

    public final int getModifiers() {
        return this.flags;
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream, ClassType classType) throws IOException {
        dataOutputStream.writeShort(this.flags);
        dataOutputStream.writeShort(this.name_index);
        dataOutputStream.writeShort(this.signature_index);
        Attribute.writeAll(this, dataOutputStream);
    }

    /* access modifiers changed from: package-private */
    public void assign_constants(ClassType classType) {
        ConstantPool constantPool = classType.constants;
        if (this.name_index == 0 && this.name != null) {
            this.name_index = constantPool.addUtf8(this.name).index;
        }
        if (this.signature_index == 0 && this.type != null) {
            this.signature_index = constantPool.addUtf8(this.type.getSignature()).index;
        }
        Attribute.assignConstants(this, classType);
    }

    public synchronized java.lang.reflect.Field getReflectField() throws NoSuchFieldException {
        if (this.rfield == null) {
            this.rfield = this.owner.getReflectClass().getDeclaredField(getName());
        }
        return this.rfield;
    }

    public void setSourceName(String str) {
        this.sourceName = str;
    }

    public String getSourceName() {
        if (this.sourceName == null) {
            this.sourceName = getName().intern();
        }
        return this.sourceName;
    }

    public static Field searchField(Field field, String str) {
        while (field != null) {
            if (field.getSourceName() == str) {
                return field;
            }
            field = field.next;
        }
        return null;
    }

    public final Field getNext() {
        return this.next;
    }

    public final void setConstantValue(Object obj, ClassType classType) {
        CpoolEntry cpoolEntry;
        ConstantPool constantPool = classType.constants;
        if (constantPool == null) {
            constantPool = new ConstantPool();
            classType.constants = constantPool;
        }
        char charAt = getType().getSignature().charAt(0);
        if (charAt != 'F') {
            if (charAt != 'S') {
                if (charAt == 'Z') {
                    cpoolEntry = constantPool.addInt(PrimType.booleanValue(obj) ? 1 : 0);
                } else if (charAt != 'I') {
                    if (charAt != 'J') {
                        switch (charAt) {
                            case 'C':
                                if (obj instanceof Character) {
                                    cpoolEntry = constantPool.addInt(((Character) obj).charValue());
                                    break;
                                }
                            case 'B':
                                cpoolEntry = constantPool.addInt(((Number) obj).intValue());
                                break;
                            case 'D':
                                cpoolEntry = constantPool.addDouble(((Number) obj).doubleValue());
                                break;
                            default:
                                cpoolEntry = constantPool.addString(obj.toString());
                                break;
                        }
                    } else {
                        cpoolEntry = constantPool.addLong(((Number) obj).longValue());
                    }
                }
            }
            cpoolEntry = constantPool.addInt(((Number) obj).intValue());
        } else {
            cpoolEntry = constantPool.addFloat(((Number) obj).floatValue());
        }
        new ConstantValueAttr(cpoolEntry.getIndex()).addToFrontOf(this);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("Field:");
        stringBuffer.append(getDeclaringClass().getName());
        stringBuffer.append('.');
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }
}
