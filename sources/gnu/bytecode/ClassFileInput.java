package gnu.bytecode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class ClassFileInput extends DataInputStream {
    ClassType ctype;
    InputStream str;

    public ClassFileInput(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    public ClassFileInput(ClassType classType, InputStream inputStream) throws IOException, ClassFormatError {
        super(inputStream);
        this.ctype = classType;
        if (readHeader()) {
            classType.constants = readConstants();
            readClassInfo();
            readFields();
            readMethods();
            readAttributes(classType);
            return;
        }
        throw new ClassFormatError("invalid magic number");
    }

    public static ClassType readClassType(InputStream inputStream) throws IOException, ClassFormatError {
        ClassType classType = new ClassType();
        new ClassFileInput(classType, inputStream);
        return classType;
    }

    public boolean readHeader() throws IOException {
        if (readInt() != -889275714) {
            return false;
        }
        readFormatVersion();
        return true;
    }

    public void readFormatVersion() throws IOException {
        this.ctype.classfileFormatVersion = (readUnsignedShort() * 65536) + readUnsignedShort();
    }

    public ConstantPool readConstants() throws IOException {
        return new ConstantPool(this);
    }

    public void readClassInfo() throws IOException {
        this.ctype.access_flags = readUnsignedShort();
        this.ctype.thisClassIndex = readUnsignedShort();
        String str2 = getClassConstant(this.ctype.thisClassIndex).name.string;
        this.ctype.this_name = str2.replace('/', '.');
        this.ctype.setSignature("L" + str2 + ";");
        this.ctype.superClassIndex = readUnsignedShort();
        if (this.ctype.superClassIndex == 0) {
            ClassType classType = null;
            this.ctype.setSuper((ClassType) null);
        } else {
            this.ctype.setSuper(getClassConstant(this.ctype.superClassIndex).name.string.replace('/', '.'));
        }
        int readUnsignedShort = readUnsignedShort();
        if (readUnsignedShort > 0) {
            this.ctype.interfaces = new ClassType[readUnsignedShort];
            this.ctype.interfaceIndexes = new int[readUnsignedShort];
            for (int i = 0; i < readUnsignedShort; i++) {
                int readUnsignedShort2 = readUnsignedShort();
                this.ctype.interfaceIndexes[i] = readUnsignedShort2;
                this.ctype.interfaces[i] = ClassType.make(((CpoolClass) this.ctype.constants.getForced(readUnsignedShort2, 7)).name.string.replace('/', '.'));
            }
        }
    }

    public int readAttributes(AttrContainer attrContainer) throws IOException {
        int readUnsignedShort = readUnsignedShort();
        Attribute attributes = attrContainer.getAttributes();
        for (int i = 0; i < readUnsignedShort; i++) {
            if (attributes != null) {
                while (true) {
                    Attribute next = attributes.getNext();
                    if (next == null) {
                        break;
                    }
                    attributes = next;
                }
            }
            int readUnsignedShort2 = readUnsignedShort();
            CpoolUtf8 cpoolUtf8 = (CpoolUtf8) this.ctype.constants.getForced(readUnsignedShort2, 1);
            int readInt = readInt();
            cpoolUtf8.intern();
            Attribute readAttribute = readAttribute(cpoolUtf8.string, readInt, attrContainer);
            if (readAttribute != null) {
                if (readAttribute.getNameIndex() == 0) {
                    readAttribute.setNameIndex(readUnsignedShort2);
                }
                if (attributes == null) {
                    attrContainer.setAttributes(readAttribute);
                } else {
                    if (attrContainer.getAttributes() == readAttribute) {
                        attrContainer.setAttributes(readAttribute.getNext());
                        readAttribute.setNext((Attribute) null);
                    }
                    attributes.setNext(readAttribute);
                }
                attributes = readAttribute;
            }
        }
        return readUnsignedShort;
    }

    public final void skipAttribute(int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int skip = (int) skip((long) (i - i2));
            if (skip == 0) {
                if (read() >= 0) {
                    skip = 1;
                } else {
                    throw new EOFException("EOF while reading class files attributes");
                }
            }
            i2 += skip;
        }
    }

    public Attribute readAttribute(String str2, int i, AttrContainer attrContainer) throws IOException {
        if (str2 == "SourceFile" && (attrContainer instanceof ClassType)) {
            return new SourceFileAttr(readUnsignedShort(), (ClassType) attrContainer);
        }
        int i2 = 0;
        if (str2 == "Code" && (attrContainer instanceof Method)) {
            CodeAttr codeAttr = new CodeAttr((Method) attrContainer);
            codeAttr.fixup_count = -1;
            codeAttr.setMaxStack(readUnsignedShort());
            codeAttr.setMaxLocals(readUnsignedShort());
            byte[] bArr = new byte[readInt()];
            readFully(bArr);
            codeAttr.setCode(bArr);
            int readUnsignedShort = readUnsignedShort();
            while (i2 < readUnsignedShort) {
                codeAttr.addHandler(readUnsignedShort(), readUnsignedShort(), readUnsignedShort(), readUnsignedShort());
                i2++;
            }
            readAttributes(codeAttr);
            return codeAttr;
        } else if (str2 == "LineNumberTable" && (attrContainer instanceof CodeAttr)) {
            int readUnsignedShort2 = readUnsignedShort() * 2;
            short[] sArr = new short[readUnsignedShort2];
            while (i2 < readUnsignedShort2) {
                sArr[i2] = readShort();
                i2++;
            }
            return new LineNumbersAttr(sArr, (CodeAttr) attrContainer);
        } else if (str2 == "LocalVariableTable" && (attrContainer instanceof CodeAttr)) {
            CodeAttr codeAttr2 = (CodeAttr) attrContainer;
            LocalVarsAttr localVarsAttr = new LocalVarsAttr(codeAttr2);
            Method method = localVarsAttr.getMethod();
            if (localVarsAttr.parameter_scope == null) {
                localVarsAttr.parameter_scope = method.pushScope();
            }
            Scope scope = localVarsAttr.parameter_scope;
            if (scope.end == null) {
                scope.end = new Label(codeAttr2.PC);
            }
            ConstantPool constants = method.getConstants();
            int readUnsignedShort3 = readUnsignedShort();
            int i3 = scope.start.position;
            int i4 = scope.end.position;
            while (i2 < readUnsignedShort3) {
                Variable variable = new Variable();
                int readUnsignedShort4 = readUnsignedShort();
                int readUnsignedShort5 = readUnsignedShort() + readUnsignedShort4;
                if (readUnsignedShort4 != i3 || readUnsignedShort5 != i4) {
                    while (scope.parent != null && (readUnsignedShort4 < scope.start.position || readUnsignedShort5 > scope.end.position)) {
                        scope = scope.parent;
                    }
                    Scope scope2 = new Scope(new Label(readUnsignedShort4), new Label(readUnsignedShort5));
                    scope2.linkChild(scope);
                    scope = scope2;
                    i3 = readUnsignedShort4;
                    i4 = readUnsignedShort5;
                }
                scope.addVariable(variable);
                variable.setName(readUnsignedShort(), constants);
                variable.setSignature(readUnsignedShort(), constants);
                variable.offset = readUnsignedShort();
                i2++;
            }
            return localVarsAttr;
        } else if (str2 == "Signature" && (attrContainer instanceof Member)) {
            return new SignatureAttr(readUnsignedShort(), (Member) attrContainer);
        } else {
            if (str2 == "StackMapTable" && (attrContainer instanceof CodeAttr)) {
                byte[] bArr2 = new byte[i];
                readFully(bArr2, 0, i);
                return new StackMapTableAttr(bArr2, (CodeAttr) attrContainer);
            } else if ((str2 == "RuntimeVisibleAnnotations" || str2 == "RuntimeInvisibleAnnotations") && ((attrContainer instanceof Field) || (attrContainer instanceof Method) || (attrContainer instanceof ClassType))) {
                byte[] bArr3 = new byte[i];
                readFully(bArr3, 0, i);
                return new RuntimeAnnotationsAttr(str2, bArr3, attrContainer);
            } else if (str2 == "ConstantValue" && (attrContainer instanceof Field)) {
                return new ConstantValueAttr(readUnsignedShort());
            } else {
                if (str2 == "InnerClasses" && (attrContainer instanceof ClassType)) {
                    int readUnsignedShort6 = readUnsignedShort() * 4;
                    short[] sArr2 = new short[readUnsignedShort6];
                    while (i2 < readUnsignedShort6) {
                        sArr2[i2] = readShort();
                        i2++;
                    }
                    return new InnerClassesAttr(sArr2, (ClassType) attrContainer);
                } else if (str2 == "EnclosingMethod" && (attrContainer instanceof ClassType)) {
                    return new EnclosingMethodAttr(readUnsignedShort(), readUnsignedShort(), (ClassType) attrContainer);
                } else {
                    if (str2 == "Exceptions" && (attrContainer instanceof Method)) {
                        Method method2 = (Method) attrContainer;
                        int readUnsignedShort7 = readUnsignedShort();
                        short[] sArr3 = new short[readUnsignedShort7];
                        while (i2 < readUnsignedShort7) {
                            sArr3[i2] = readShort();
                            i2++;
                        }
                        method2.setExceptions(sArr3);
                        return method2.getExceptionAttr();
                    } else if (str2 != "SourceDebugExtension" || !(attrContainer instanceof ClassType)) {
                        byte[] bArr4 = new byte[i];
                        readFully(bArr4, 0, i);
                        return new MiscAttr(str2, bArr4);
                    } else {
                        SourceDebugExtAttr sourceDebugExtAttr = new SourceDebugExtAttr((ClassType) attrContainer);
                        byte[] bArr5 = new byte[i];
                        readFully(bArr5, 0, i);
                        sourceDebugExtAttr.data = bArr5;
                        sourceDebugExtAttr.dlength = i;
                        return sourceDebugExtAttr;
                    }
                }
            }
        }
    }

    public void readFields() throws IOException {
        int readUnsignedShort = readUnsignedShort();
        ConstantPool constantPool = this.ctype.constants;
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedShort2 = readUnsignedShort();
            int readUnsignedShort3 = readUnsignedShort();
            int readUnsignedShort4 = readUnsignedShort();
            Field addField = this.ctype.addField();
            addField.setName(readUnsignedShort3, constantPool);
            addField.setSignature(readUnsignedShort4, constantPool);
            addField.flags = readUnsignedShort2;
            readAttributes(addField);
        }
    }

    public void readMethods() throws IOException {
        int readUnsignedShort = readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            int readUnsignedShort2 = readUnsignedShort();
            int readUnsignedShort3 = readUnsignedShort();
            int readUnsignedShort4 = readUnsignedShort();
            Method addMethod = this.ctype.addMethod((String) null, readUnsignedShort2);
            addMethod.setName(readUnsignedShort3);
            addMethod.setSignature(readUnsignedShort4);
            readAttributes(addMethod);
        }
    }

    /* access modifiers changed from: package-private */
    public CpoolClass getClassConstant(int i) {
        return (CpoolClass) this.ctype.constants.getForced(i, 7);
    }
}
