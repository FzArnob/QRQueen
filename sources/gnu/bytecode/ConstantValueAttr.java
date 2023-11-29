package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttr extends Attribute {
    Object value;
    int value_index;

    public final int getLength() {
        return 2;
    }

    public Object getValue(ConstantPool constantPool) {
        Object obj = this.value;
        if (obj != null) {
            return obj;
        }
        CpoolEntry poolEntry = constantPool.getPoolEntry(this.value_index);
        int tag = poolEntry.getTag();
        if (tag == 3) {
            this.value = new Integer(((CpoolValue1) poolEntry).value);
        } else if (tag == 4) {
            this.value = new Float(Float.intBitsToFloat(((CpoolValue1) poolEntry).value));
        } else if (tag == 5) {
            this.value = new Long(((CpoolValue2) poolEntry).value);
        } else if (tag == 6) {
            this.value = new Double(Double.longBitsToDouble(((CpoolValue2) poolEntry).value));
        } else if (tag == 8) {
            this.value = ((CpoolString) poolEntry).getString().getString();
        }
        return this.value;
    }

    public ConstantValueAttr(Object obj) {
        super("ConstantValue");
        this.value = obj;
    }

    public ConstantValueAttr(int i) {
        super("ConstantValue");
        this.value_index = i;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        if (this.value_index == 0) {
            ConstantPool constants = classType.getConstants();
            CpoolEntry cpoolEntry = null;
            Object obj = this.value;
            if (obj instanceof String) {
                cpoolEntry = constants.addString((String) obj);
            } else if (obj instanceof Integer) {
                cpoolEntry = constants.addInt(((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                cpoolEntry = constants.addLong(((Long) obj).longValue());
            } else if (obj instanceof Float) {
                cpoolEntry = constants.addFloat(((Float) obj).floatValue());
            } else if (obj instanceof Long) {
                cpoolEntry = constants.addDouble(((Double) obj).doubleValue());
            }
            this.value_index = cpoolEntry.getIndex();
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.value_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", value: ");
        int i = this.value_index;
        if (i == 0) {
            Object value2 = getValue(classTypeWriter.ctype.constants);
            if (value2 instanceof String) {
                classTypeWriter.printQuotedString((String) value2);
            } else {
                classTypeWriter.print(value2);
            }
        } else {
            classTypeWriter.printOptionalIndex(i);
            classTypeWriter.ctype.constants.getPoolEntry(this.value_index).print(classTypeWriter, 1);
        }
        classTypeWriter.println();
    }
}
