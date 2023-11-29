package gnu.bytecode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

public class ClassTypeWriter extends PrintWriter {
    public static final int PRINT_CONSTANT_POOL = 1;
    public static final int PRINT_CONSTANT_POOL_INDEXES = 2;
    public static final int PRINT_EXTRAS = 8;
    public static final int PRINT_VERBOSE = 15;
    public static final int PRINT_VERSION = 4;
    ClassType ctype;
    int flags;

    public ClassTypeWriter(ClassType classType, Writer writer, int i) {
        super(writer);
        this.ctype = classType;
        this.flags = i;
    }

    public ClassTypeWriter(ClassType classType, OutputStream outputStream, int i) {
        super(outputStream);
        this.ctype = classType;
        this.flags = i;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public static void print(ClassType classType, PrintWriter printWriter, int i) {
        ClassTypeWriter classTypeWriter = new ClassTypeWriter(classType, (Writer) printWriter, i);
        classTypeWriter.print();
        classTypeWriter.flush();
    }

    public static void print(ClassType classType, PrintStream printStream, int i) {
        ClassTypeWriter classTypeWriter = new ClassTypeWriter(classType, (OutputStream) printStream, i);
        classTypeWriter.print();
        classTypeWriter.flush();
    }

    public void print() {
        if ((this.flags & 4) != 0) {
            print("Classfile format major version: ");
            print(this.ctype.getClassfileMajorVersion());
            print(", minor version: ");
            print(this.ctype.getClassfileMinorVersion());
            println('.');
        }
        if ((this.flags & 1) != 0) {
            printConstantPool();
        }
        printClassInfo();
        printFields();
        printMethods();
        printAttributes();
    }

    public void setClass(ClassType classType) {
        this.ctype = classType;
    }

    public void print(ClassType classType) {
        this.ctype = classType;
        print();
    }

    public void printAttributes() {
        ClassType classType = this.ctype;
        println();
        print("Attributes (count: ");
        print(Attribute.count(classType));
        println("):");
        printAttributes(classType);
    }

    public void printAttributes(AttrContainer attrContainer) {
        for (Attribute attributes = attrContainer.getAttributes(); attributes != null; attributes = attributes.next) {
            attributes.print(this);
        }
    }

    public void printClassInfo() {
        int i;
        println();
        print("Access flags:");
        print(Access.toString(this.ctype.getModifiers(), Access.CLASS_CONTEXT));
        println();
        print("This class: ");
        printOptionalIndex(this.ctype.thisClassIndex);
        printConstantTersely(this.ctype.thisClassIndex, 7);
        print(" super: ");
        if (this.ctype.superClassIndex == -1) {
            print("<unknown>");
        } else if (this.ctype.superClassIndex == 0) {
            print("0");
        } else {
            printOptionalIndex(this.ctype.superClassIndex);
            printConstantTersely(this.ctype.superClassIndex, 7);
        }
        println();
        print("Interfaces (count: ");
        int[] iArr = this.ctype.interfaceIndexes;
        if (iArr == null) {
            i = 0;
        } else {
            i = iArr.length;
        }
        print(i);
        print("):");
        println();
        for (int i2 = 0; i2 < i; i2++) {
            print("- Implements: ");
            int i3 = iArr[i2];
            printOptionalIndex(i3);
            printConstantTersely(i3, 7);
            println();
        }
    }

    public void printFields() {
        println();
        print("Fields (count: ");
        print(this.ctype.fields_count);
        print("):");
        println();
        for (Field field = this.ctype.fields; field != null; field = field.next) {
            print("Field name: ");
            if (field.name_index != 0) {
                printOptionalIndex(field.name_index);
            }
            print(field.getName());
            print(Access.toString(field.flags, Access.FIELD_CONTEXT));
            print(" Signature: ");
            if (field.signature_index != 0) {
                printOptionalIndex(field.signature_index);
            }
            printSignature(field.type);
            println();
            printAttributes(field);
        }
    }

    public void printMethods() {
        println();
        print("Methods (count: ");
        print(this.ctype.methods_count);
        print("):");
        println();
        for (Method method = this.ctype.methods; method != null; method = method.next) {
            printMethod(method);
        }
    }

    public void printMethod(Method method) {
        println();
        print("Method name:");
        if (method.name_index != 0) {
            printOptionalIndex(method.name_index);
        }
        print('\"');
        print(method.getName());
        print('\"');
        print(Access.toString(method.access_flags, Access.METHOD_CONTEXT));
        print(" Signature: ");
        if (method.signature_index != 0) {
            printOptionalIndex(method.signature_index);
        }
        print('(');
        for (int i = 0; i < method.arg_types.length; i++) {
            if (i > 0) {
                print(',');
            }
            printSignature(method.arg_types[i]);
        }
        print(')');
        printSignature(method.return_type);
        println();
        printAttributes(method);
    }

    /* access modifiers changed from: package-private */
    public CpoolEntry getCpoolEntry(int i) {
        CpoolEntry[] cpoolEntryArr = this.ctype.constants.pool;
        if (cpoolEntryArr == null || i < 0 || i >= cpoolEntryArr.length) {
            return null;
        }
        return cpoolEntryArr[i];
    }

    /* access modifiers changed from: package-private */
    public final void printConstantTersely(CpoolEntry cpoolEntry, int i) {
        if (cpoolEntry == null) {
            print("<invalid constant index>");
        } else if (cpoolEntry.getTag() != i) {
            print("<unexpected constant type ");
            cpoolEntry.print(this, 1);
            print('>');
        } else {
            cpoolEntry.print(this, 0);
        }
    }

    /* access modifiers changed from: package-private */
    public final void printConstantTersely(int i, int i2) {
        printConstantTersely(getCpoolEntry(i), i2);
    }

    /* access modifiers changed from: package-private */
    public final void printContantUtf8AsClass(int i) {
        CpoolEntry cpoolEntry = getCpoolEntry(i);
        if (cpoolEntry == null || cpoolEntry.getTag() != 1) {
            printConstantTersely(i, 1);
            return;
        }
        String str = ((CpoolUtf8) cpoolEntry).string;
        Type.printSignature(str, 0, str.length(), this);
    }

    /* access modifiers changed from: package-private */
    public final void printConstantOperand(int i) {
        CpoolEntry cpoolEntry;
        print(' ');
        printOptionalIndex(i);
        CpoolEntry[] cpoolEntryArr = this.ctype.constants.pool;
        if (cpoolEntryArr == null || i < 0 || i >= cpoolEntryArr.length || (cpoolEntry = cpoolEntryArr[i]) == null) {
            print("<invalid constant index>");
            return;
        }
        print('<');
        cpoolEntry.print(this, 1);
        print('>');
    }

    public final void printQuotedString(String str) {
        print('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                print("\\\"");
            } else if (charAt < ' ' || charAt >= 127) {
                if (charAt != 10) {
                    print("\\u");
                    int i2 = 4;
                    while (true) {
                        i2--;
                        if (i2 < 0) {
                            break;
                        }
                        print(Character.forDigit((charAt >> (i2 * 4)) & 15, 16));
                    }
                } else {
                    print("\\n");
                }
            } else {
                print(charAt);
            }
        }
        print('\"');
    }

    public void printConstantPool() {
        CpoolEntry[] cpoolEntryArr = this.ctype.constants.pool;
        int i = this.ctype.constants.count;
        for (int i2 = 1; i2 <= i; i2++) {
            CpoolEntry cpoolEntry = cpoolEntryArr[i2];
            if (cpoolEntry != null) {
                print('#');
                print(cpoolEntry.index);
                print(": ");
                cpoolEntry.print(this, 2);
                println();
            }
        }
    }

    public final void printOptionalIndex(int i) {
        if ((this.flags & 2) != 0) {
            print('#');
            print(i);
            print('=');
        }
    }

    public final void printOptionalIndex(CpoolEntry cpoolEntry) {
        printOptionalIndex(cpoolEntry.index);
    }

    /* access modifiers changed from: package-private */
    public void printName(String str) {
        print(str);
    }

    public final int printSignature(String str, int i) {
        int length = str.length();
        if (i >= length) {
            print("<empty signature>");
            return i;
        }
        int signatureLength = Type.signatureLength(str, i);
        if (signatureLength > 0) {
            int i2 = signatureLength + i;
            String signatureToName = Type.signatureToName(str.substring(i, i2));
            if (signatureToName != null) {
                print(signatureToName);
                return i2;
            }
        }
        char charAt = str.charAt(i);
        if (charAt != '(') {
            print(charAt);
            return i + 1;
        }
        int i3 = 0;
        int i4 = i + 1;
        print(charAt);
        while (i4 < length) {
            char charAt2 = str.charAt(i4);
            if (charAt2 == ')') {
                print(charAt2);
                return printSignature(str, i4 + 1);
            }
            int i5 = i3 + 1;
            if (i3 > 0) {
                print(',');
            }
            i4 = printSignature(str, i4);
            i3 = i5;
        }
        print("<truncated method signature>");
        return i4;
    }

    public final void printSignature(String str) {
        int printSignature = printSignature(str, 0);
        if (printSignature < str.length()) {
            print("<trailing junk:");
            print(str.substring(printSignature));
            print('>');
        }
    }

    public final void printSignature(Type type) {
        if (type == null) {
            print("<unknown type>");
        } else {
            printSignature(type.getSignature());
        }
    }

    public void printSpaces(int i) {
        while (true) {
            i--;
            if (i >= 0) {
                print(' ');
            } else {
                return;
            }
        }
    }
}
