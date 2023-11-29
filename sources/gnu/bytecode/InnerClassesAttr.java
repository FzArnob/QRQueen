package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class InnerClassesAttr extends Attribute {
    int count;
    short[] data;

    public InnerClassesAttr(ClassType classType) {
        super("InnerClasses");
        addToFrontOf(classType);
    }

    public InnerClassesAttr(short[] sArr, ClassType classType) {
        this(classType);
        this.count = (short) (sArr.length >> 2);
        this.data = sArr;
    }

    public static InnerClassesAttr getFirstInnerClasses(Attribute attribute) {
        while (attribute != null && !(attribute instanceof InnerClassesAttr)) {
            attribute = attribute.next;
        }
        return (InnerClassesAttr) attribute;
    }

    /* access modifiers changed from: package-private */
    public void addClass(CpoolClass cpoolClass, ClassType classType) {
        int i = this.count;
        this.count = i + 1;
        int i2 = i * 4;
        short[] sArr = this.data;
        short s = 0;
        if (sArr == null) {
            this.data = new short[16];
        } else if (i2 >= sArr.length) {
            short[] sArr2 = new short[(i2 * 2)];
            System.arraycopy(sArr, 0, sArr2, 0, i2);
            this.data = sArr2;
        }
        ConstantPool constantPool = classType.constants;
        ClassType classType2 = (ClassType) cpoolClass.getClassType();
        String simpleName = classType2.getSimpleName();
        int i3 = (simpleName == null || simpleName.length() == 0) ? 0 : constantPool.addUtf8(simpleName).index;
        this.data[i2] = (short) cpoolClass.index;
        ClassType declaringClass = classType2.getDeclaringClass();
        short[] sArr3 = this.data;
        int i4 = i2 + 1;
        if (declaringClass != null) {
            s = (short) constantPool.addClass((ObjectType) declaringClass).index;
        }
        sArr3[i4] = s;
        this.data[i2 + 2] = (short) i3;
        this.data[i2 + 3] = (short) (classType2.getModifiers() & -33);
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
    }

    public int getLength() {
        return (this.count * 8) + 2;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.count);
        for (int i = 0; i < this.count; i++) {
            int i2 = i * 4;
            dataOutputStream.writeShort(this.data[i2]);
            dataOutputStream.writeShort(this.data[i2 + 1]);
            dataOutputStream.writeShort(this.data[i2 + 2]);
            dataOutputStream.writeShort(this.data[i2 + 3]);
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        short s;
        String str;
        char charAt;
        ClassType classType = (ClassType) this.container;
        ConstantPool constants = this.data == null ? null : classType.getConstants();
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", count: ");
        classTypeWriter.println(this.count);
        for (int i = 0; i < this.count; i++) {
            if (constants == null) {
                s = 0;
            } else {
                s = this.data[i * 4] & 65535;
            }
            CpoolClass forcedClass = (constants == null || s == 0) ? null : constants.getForcedClass(s);
            ClassType classType2 = (forcedClass == null || !(forcedClass.clas instanceof ClassType)) ? null : (ClassType) forcedClass.clas;
            classTypeWriter.print(' ');
            classTypeWriter.print(Access.toString((s != 0 || classType2 == null) ? this.data[(i * 4) + 3] & 65535 : classType2.getModifiers(), Access.INNERCLASS_CONTEXT));
            classTypeWriter.print(' ');
            if (s != 0 || classType2 == null) {
                short s2 = this.data[(i * 4) + 2] & 65535;
                if (constants == null || s2 == 0) {
                    str = "(Anonymous)";
                } else {
                    classTypeWriter.printOptionalIndex((int) s2);
                    CpoolUtf8 cpoolUtf8 = (CpoolUtf8) constants.getForced(s2, 1);
                    CpoolUtf8 cpoolUtf82 = cpoolUtf8;
                    str = cpoolUtf8.string;
                }
            } else {
                str = classType2.getSimpleName();
            }
            classTypeWriter.print(str);
            classTypeWriter.print(" = ");
            classTypeWriter.print(forcedClass != null ? forcedClass.getClassName() : "(Unknown)");
            classTypeWriter.print("; ");
            if (s != 0 || classType2 == null) {
                short s3 = 65535 & this.data[(i * 4) + 1];
                if (s3 == 0) {
                    classTypeWriter.print("not a member");
                } else {
                    classTypeWriter.print("member of ");
                    classTypeWriter.print(((CpoolClass) constants.getForced(s3, 7)).getStringName());
                }
            } else {
                String name = classType2.getName();
                int lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf > 0) {
                    name = name.substring(lastIndexOf + 1);
                }
                int lastIndexOf2 = name.lastIndexOf(36) + 1;
                if (lastIndexOf2 >= name.length() || (charAt = name.charAt(lastIndexOf2)) < '0' || charAt > '9') {
                    classTypeWriter.print("member of ");
                    classTypeWriter.print(classType.getName());
                } else {
                    classTypeWriter.print("not a member");
                }
            }
            classTypeWriter.println();
        }
    }
}
