package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Stack;

public class Method implements AttrContainer, Member {
    int access_flags;
    Type[] arg_types;
    Attribute attributes;
    ClassType classfile;
    CodeAttr code;
    ExceptionsAttr exceptions;
    private String name;
    int name_index;
    Method next;
    Type return_type;
    String signature;
    int signature_index;

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attribute) {
        this.attributes = attribute;
    }

    public final ExceptionsAttr getExceptionAttr() {
        return this.exceptions;
    }

    public void setExceptions(short[] sArr) {
        if (this.exceptions == null) {
            this.exceptions = new ExceptionsAttr(this);
        }
        this.exceptions.setExceptions(sArr, this.classfile);
    }

    public void setExceptions(ClassType[] classTypeArr) {
        if (this.exceptions == null) {
            this.exceptions = new ExceptionsAttr(this);
        }
        this.exceptions.setExceptions(classTypeArr);
    }

    public final CodeAttr getCode() {
        return this.code;
    }

    private Method() {
    }

    public static Method makeCloneMethod(Type type) {
        Method method = new Method();
        method.name = "clone";
        method.access_flags = 1;
        method.arg_types = Type.typeArray0;
        method.return_type = type;
        method.classfile = Type.pointer_type;
        return method;
    }

    public Method(Method method, ClassType classType) {
        this.arg_types = method.arg_types;
        this.return_type = method.return_type;
        this.name = method.name;
        this.access_flags = method.access_flags;
        this.classfile = classType;
    }

    Method(ClassType classType, int i) {
        if (classType.last_method == null) {
            classType.methods = this;
        } else {
            classType.last_method.next = this;
        }
        classType.last_method = this;
        classType.methods_count++;
        this.access_flags = i;
        this.classfile = classType;
    }

    public final void setStaticFlag(boolean z) {
        if (z) {
            this.access_flags |= 8;
        } else {
            this.access_flags ^= -9;
        }
    }

    public final boolean getStaticFlag() {
        return (this.access_flags & 8) != 0;
    }

    public final boolean isAbstract() {
        return (this.access_flags & 1024) != 0;
    }

    public int getModifiers() {
        return this.access_flags;
    }

    public void setModifiers(int i) {
        this.access_flags = i;
    }

    public final ConstantPool getConstants() {
        return this.classfile.constants;
    }

    public Scope pushScope() {
        prepareCode(0);
        return this.code.pushScope();
    }

    public final boolean reachableHere() {
        return this.code.reachableHere();
    }

    public Scope popScope() {
        return this.code.popScope();
    }

    public void allocate_local(Variable variable) {
        variable.allocateLocal(this.code);
    }

    public void initCode() {
        if (this.classfile.constants == null) {
            this.classfile.constants = new ConstantPool();
        }
        prepareCode(0);
        this.code.sourceDbgExt = this.classfile.sourceDbgExt;
        this.code.noteParamTypes();
        this.code.pushScope();
    }

    public void init_param_slots() {
        startCode();
    }

    public CodeAttr startCode() {
        initCode();
        this.code.addParamLocals();
        return this.code;
    }

    /* access modifiers changed from: package-private */
    public void kill_local(Variable variable) {
        variable.freeLocal(this.code);
    }

    /* access modifiers changed from: package-private */
    public void prepareCode(int i) {
        if (this.code == null) {
            this.code = new CodeAttr(this);
        }
        this.code.reserve(i);
    }

    /* access modifiers changed from: package-private */
    public void instruction_start_hook(int i) {
        prepareCode(i);
    }

    /* access modifiers changed from: package-private */
    public final Type pop_stack_type() {
        return this.code.popType();
    }

    /* access modifiers changed from: package-private */
    public final void push_stack_type(Type type) {
        this.code.pushType(type);
    }

    public void compile_checkcast(Type type) {
        this.code.emitCheckcast(type);
    }

    public void maybe_compile_checkcast(Type type) {
        if (type != this.code.topType()) {
            this.code.emitCheckcast(type);
        }
    }

    public void push_var(Variable variable) {
        this.code.emitLoad(variable);
    }

    public void compile_push_value(Variable variable) {
        this.code.emitLoad(variable);
    }

    public void compile_store_value(Variable variable) {
        this.code.emitStore(variable);
    }

    public void compile_push_this() {
        this.code.emitPushThis();
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream, ClassType classType) throws IOException {
        dataOutputStream.writeShort(this.access_flags);
        dataOutputStream.writeShort(this.name_index);
        dataOutputStream.writeShort(this.signature_index);
        Attribute.writeAll(this, dataOutputStream);
    }

    public static String makeSignature(Type[] typeArr, Type type) {
        StringBuilder sb = new StringBuilder(100);
        sb.append('(');
        for (Type signature2 : typeArr) {
            sb.append(signature2.getSignature());
        }
        sb.append(')');
        sb.append(type.getSignature());
        return sb.toString();
    }

    public String getSignature() {
        if (this.signature == null) {
            this.signature = makeSignature(this.arg_types, this.return_type);
        }
        return this.signature;
    }

    public void setSignature(String str) {
        int length = str.length();
        if (length < 3 || str.charAt(0) != '(') {
            throw new ClassFormatError("bad method signature");
        }
        Stack stack = new Stack();
        int i = 1;
        while (true) {
            int signatureLength = Type.signatureLength(str, i);
            if (signatureLength < 0) {
                break;
            }
            stack.push(Type.signatureToType(str, i, signatureLength));
            i += signatureLength;
        }
        if (i >= length || str.charAt(i) != ')') {
            throw new ClassFormatError("bad method signature");
        }
        this.arg_types = new Type[stack.size()];
        int size = stack.size();
        while (true) {
            size--;
            if (size >= 0) {
                this.arg_types[size] = (Type) stack.pop();
            } else {
                this.return_type = Type.signatureToType(str, i + 1, (length - i) - 1);
                return;
            }
        }
    }

    public void setSignature(int i) {
        this.signature_index = i;
        setSignature(((CpoolUtf8) getConstants().getForced(i, 1)).string);
    }

    /* access modifiers changed from: package-private */
    public void assignConstants() {
        String str;
        ConstantPool constants = getConstants();
        if (this.name_index == 0 && (str = this.name) != null) {
            this.name_index = constants.addUtf8(str).index;
        }
        if (this.signature_index == 0) {
            this.signature_index = constants.addUtf8(getSignature()).index;
        }
        Attribute.assignConstants(this, this.classfile);
    }

    public ClassType getDeclaringClass() {
        return this.classfile;
    }

    public final Type getReturnType() {
        return this.return_type;
    }

    public final Type[] getParameterTypes() {
        return this.arg_types;
    }

    public final ClassType[] getExceptions() {
        ExceptionsAttr exceptionsAttr = this.exceptions;
        if (exceptionsAttr == null) {
            return null;
        }
        return exceptionsAttr.getExceptions();
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final void setName(int i) {
        if (i <= 0) {
            this.name = null;
        } else {
            this.name = ((CpoolUtf8) getConstants().getForced(i, 1)).string;
        }
        this.name_index = i;
    }

    public final Method getNext() {
        return this.next;
    }

    public void listParameters(StringBuffer stringBuffer) {
        int length = this.arg_types.length;
        stringBuffer.append('(');
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append(this.arg_types[i].getName());
        }
        stringBuffer.append(')');
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(getDeclaringClass().getName());
        stringBuffer.append('.');
        stringBuffer.append(this.name);
        if (this.arg_types != null) {
            listParameters(stringBuffer);
            stringBuffer.append(this.return_type.getName());
        }
        return stringBuffer.toString();
    }

    public void cleanupAfterCompilation() {
        this.attributes = null;
        this.exceptions = null;
        this.code = null;
    }
}
