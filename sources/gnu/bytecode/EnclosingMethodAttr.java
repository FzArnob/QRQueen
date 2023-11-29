package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class EnclosingMethodAttr extends Attribute {
    int class_index;
    Method method;
    int method_index;

    public int getLength() {
        return 4;
    }

    public EnclosingMethodAttr(ClassType classType) {
        super("EnclosingMethod");
        addToFrontOf(classType);
    }

    public EnclosingMethodAttr(int i, int i2, ClassType classType) {
        this(classType);
        this.class_index = i;
        this.method_index = i2;
    }

    public static EnclosingMethodAttr getFirstEnclosingMethod(Attribute attribute) {
        while (attribute != null && !(attribute instanceof EnclosingMethodAttr)) {
            attribute = attribute.next;
        }
        return (EnclosingMethodAttr) attribute;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        if (this.method != null) {
            ConstantPool constants = classType.getConstants();
            this.class_index = constants.addClass((ObjectType) this.method.getDeclaringClass()).getIndex();
            this.method_index = constants.addNameAndType(this.method).getIndex();
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.class_index);
        dataOutputStream.writeShort(this.method_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ConstantPool constants = ((ClassType) this.container).getConstants();
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.println(getLength());
        classTypeWriter.print("  class: ");
        classTypeWriter.printOptionalIndex(this.class_index);
        classTypeWriter.print(((CpoolClass) constants.getForced(this.class_index, 7)).getStringName());
        classTypeWriter.print(", method: ");
        classTypeWriter.printOptionalIndex(this.method_index);
        constants.getForced(this.method_index, 12).print(classTypeWriter, 0);
        classTypeWriter.println();
    }
}
