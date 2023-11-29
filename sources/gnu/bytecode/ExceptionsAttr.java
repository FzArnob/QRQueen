package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ExceptionsAttr extends Attribute {
    short[] exception_table;
    ClassType[] exceptions;

    public ExceptionsAttr(Method method) {
        super("Exceptions");
        addToFrontOf(method);
    }

    public void setExceptions(short[] sArr, ClassType classType) {
        this.exception_table = sArr;
        this.exceptions = new ClassType[sArr.length];
        ConstantPool constants = classType.getConstants();
        for (int length = sArr.length - 1; length >= 0; length--) {
            this.exceptions[length] = (ClassType) ((CpoolClass) constants.getPoolEntry(sArr[length])).getClassType();
        }
    }

    public void setExceptions(ClassType[] classTypeArr) {
        this.exceptions = classTypeArr;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        ConstantPool constants = classType.getConstants();
        int length = this.exceptions.length;
        this.exception_table = new short[length];
        for (int i = length - 1; i >= 0; i--) {
            this.exception_table[i] = (short) constants.addClass((ObjectType) this.exceptions[i]).index;
        }
    }

    public final int getLength() {
        ClassType[] classTypeArr = this.exceptions;
        return ((classTypeArr == null ? 0 : classTypeArr.length) * 2) + 2;
    }

    public final ClassType[] getExceptions() {
        return this.exceptions;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        int length = this.exceptions.length;
        dataOutputStream.writeShort(length);
        for (int i = 0; i < length; i++) {
            dataOutputStream.writeShort(this.exception_table[i]);
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", count: ");
        int length = this.exceptions.length;
        classTypeWriter.println(length);
        for (int i = 0; i < length; i++) {
            short s = this.exception_table[i] & 65535;
            classTypeWriter.print("  ");
            classTypeWriter.printOptionalIndex((int) s);
            classTypeWriter.printConstantTersely((int) s, 7);
            classTypeWriter.println();
        }
    }
}
