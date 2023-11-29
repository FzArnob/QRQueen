package gnu.bytecode;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeAttr extends Attribute implements AttrContainer {
    public static final int DONT_USE_JSR = 2;
    static final int FIXUP_CASE = 3;
    static final int FIXUP_DEFINE = 1;
    static final int FIXUP_DELETE3 = 8;
    static final int FIXUP_GOTO = 4;
    static final int FIXUP_JSR = 5;
    static final int FIXUP_LINE_NUMBER = 15;
    static final int FIXUP_LINE_PC = 14;
    static final int FIXUP_MOVE = 9;
    static final int FIXUP_MOVE_TO_END = 10;
    static final int FIXUP_NONE = 0;
    static final int FIXUP_SWITCH = 2;
    static final int FIXUP_TRANSFER = 6;
    static final int FIXUP_TRANSFER2 = 7;
    static final int FIXUP_TRY = 11;
    static final int FIXUP_TRY_END = 12;
    static final int FIXUP_TRY_HANDLER = 13;
    public static final int GENERATE_STACK_MAP_TABLE = 1;
    public static boolean instructionLineMode = false;
    int PC;
    int SP;
    Attribute attributes;
    byte[] code;
    ExitableBlock currentExitableBlock;
    short[] exception_table;
    int exception_table_length;
    int exitableBlockLevel;
    int fixup_count;
    Label[] fixup_labels;
    int[] fixup_offsets;
    int flags;
    IfState if_stack;
    LineNumbersAttr lines;
    Type[] local_types;
    public LocalVarsAttr locals;
    private int max_locals;
    private int max_stack;
    Label previousLabel;
    SourceDebugExtAttr sourceDbgExt;
    public StackMapTableAttr stackMap;
    public Type[] stack_types;
    TryState try_stack;
    private boolean unreachable_here;
    boolean[] varsSetInCurrentBlock;

    private int adjustTypedOp(char c) {
        if (c == 'F') {
            return 2;
        }
        if (c == 'S') {
            return 7;
        }
        if (c == 'Z') {
            return 5;
        }
        if (c == 'I') {
            return 0;
        }
        if (c == 'J') {
            return 1;
        }
        switch (c) {
            case 'B':
                return 5;
            case 'C':
                return 6;
            case 'D':
                return 3;
            default:
                return 4;
        }
    }

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attribute) {
        this.attributes = attribute;
    }

    /* access modifiers changed from: package-private */
    public boolean useJsr() {
        return (this.flags & 2) == 0;
    }

    public final void fixupChain(Label label, Label label2) {
        fixupAdd(9, 0, label2);
        label.defineRaw(this);
    }

    public final void fixupAdd(int i, Label label) {
        fixupAdd(i, this.PC, label);
    }

    /* access modifiers changed from: package-private */
    public final void fixupAdd(int i, int i2, Label label) {
        if (!(label == null || i == 1 || i == 0 || i == 2 || i == 11)) {
            label.needsStackMapEntry = true;
        }
        int i3 = this.fixup_count;
        if (i3 == 0) {
            this.fixup_offsets = new int[30];
            this.fixup_labels = new Label[30];
        } else if (i3 == this.fixup_offsets.length) {
            int i4 = i3 * 2;
            Label[] labelArr = new Label[i4];
            System.arraycopy(this.fixup_labels, 0, labelArr, 0, i3);
            this.fixup_labels = labelArr;
            int[] iArr = new int[i4];
            System.arraycopy(this.fixup_offsets, 0, iArr, 0, i3);
            this.fixup_offsets = iArr;
        }
        this.fixup_offsets[i3] = i | (i2 << 4);
        this.fixup_labels[i3] = label;
        this.fixup_count = i3 + 1;
    }

    private final int fixupOffset(int i) {
        return this.fixup_offsets[i] >> 4;
    }

    private final int fixupKind(int i) {
        return this.fixup_offsets[i] & 15;
    }

    public final Method getMethod() {
        return (Method) getContainer();
    }

    public final int getPC() {
        return this.PC;
    }

    public final int getSP() {
        return this.SP;
    }

    public final ConstantPool getConstants() {
        return getMethod().classfile.constants;
    }

    public final boolean reachableHere() {
        return !this.unreachable_here;
    }

    public final void setReachable(boolean z) {
        this.unreachable_here = !z;
    }

    public final void setUnreachable() {
        this.unreachable_here = true;
    }

    public int getMaxStack() {
        return this.max_stack;
    }

    public int getMaxLocals() {
        return this.max_locals;
    }

    public void setMaxStack(int i) {
        this.max_stack = i;
    }

    public void setMaxLocals(int i) {
        this.max_locals = i;
    }

    public byte[] getCode() {
        return this.code;
    }

    public void setCode(byte[] bArr) {
        this.code = bArr;
        this.PC = bArr.length;
    }

    public void setCodeLength(int i) {
        this.PC = i;
    }

    public int getCodeLength() {
        return this.PC;
    }

    public CodeAttr(Method method) {
        super("Code");
        addToFrontOf(method);
        method.code = this;
        if (method.getDeclaringClass().getClassfileMajorVersion() >= 50) {
            this.flags |= 3;
        }
    }

    public final void reserve(int i) {
        byte[] bArr = this.code;
        if (bArr == null) {
            this.code = new byte[(i + 100)];
            return;
        }
        int i2 = this.PC;
        if (i2 + i > bArr.length) {
            byte[] bArr2 = new byte[((bArr.length * 2) + i)];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.code = bArr2;
        }
    }

    /* access modifiers changed from: package-private */
    public byte invert_opcode(byte b) {
        byte b2 = b & Ev3Constants.Opcode.TST;
        if ((b2 >= 153 && b2 <= 166) || (b2 >= 198 && b2 <= 199)) {
            return (byte) (b2 ^ 1);
        }
        throw new Error("unknown opcode to invert_opcode");
    }

    public final void put1(int i) {
        byte[] bArr = this.code;
        int i2 = this.PC;
        this.PC = i2 + 1;
        bArr[i2] = (byte) i;
        this.unreachable_here = false;
    }

    public final void put2(int i) {
        byte[] bArr = this.code;
        int i2 = this.PC;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >> 8);
        this.PC = i3 + 1;
        bArr[i3] = (byte) i;
        this.unreachable_here = false;
    }

    public final void put4(int i) {
        byte[] bArr = this.code;
        int i2 = this.PC;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >> 8);
        this.PC = i5 + 1;
        bArr[i5] = (byte) i;
        this.unreachable_here = false;
    }

    public final void putIndex2(CpoolEntry cpoolEntry) {
        put2(cpoolEntry.index);
    }

    public final void putLineNumber(String str, int i) {
        if (str != null) {
            getMethod().classfile.setSourceFile(str);
        }
        putLineNumber(i);
    }

    public final void putLineNumber(int i) {
        SourceDebugExtAttr sourceDebugExtAttr = this.sourceDbgExt;
        if (sourceDebugExtAttr != null) {
            i = sourceDebugExtAttr.fixLine(i);
        }
        fixupAdd(14, (Label) null);
        fixupAdd(15, i, (Label) null);
    }

    /* access modifiers changed from: package-private */
    public void noteParamTypes() {
        int i;
        Method method = getMethod();
        if ((method.access_flags & 8) == 0) {
            ClassType classType = method.classfile;
            Type type = classType;
            if ("<init>".equals(method.getName())) {
                boolean equals = "java.lang.Object".equals(classType.getName());
                type = classType;
                if (!equals) {
                    ClassType classType2 = classType;
                    type = UninitializedType.uninitializedThis(classType);
                }
            }
            noteVarType(0, type);
            i = 1;
        } else {
            i = 0;
        }
        int length = method.arg_types.length;
        int i2 = 0;
        while (i2 < length) {
            Type type2 = method.arg_types[i2];
            int i3 = i + 1;
            noteVarType(i, type2);
            int sizeInWords = type2.getSizeInWords();
            while (true) {
                sizeInWords--;
                if (sizeInWords <= 0) {
                    break;
                }
                i3++;
            }
            i2++;
            i = i3;
        }
        if ((this.flags & 1) != 0) {
            this.stackMap = new StackMapTableAttr();
            int[] iArr = new int[(i + 20)];
            int i4 = 0;
            int i5 = 0;
            while (i4 < i) {
                int encodeVerificationType = this.stackMap.encodeVerificationType(this.local_types[i4], this);
                int i6 = i5 + 1;
                iArr[i5] = encodeVerificationType;
                int i7 = encodeVerificationType & 255;
                if (i7 == 3 || i7 == 4) {
                    i4++;
                }
                i4++;
                i5 = i6;
            }
            this.stackMap.encodedLocals = iArr;
            this.stackMap.countLocals = i5;
            this.stackMap.encodedStack = new int[10];
            this.stackMap.countStack = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        r4 = r7 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void noteVarType(int r7, gnu.bytecode.Type r8) {
        /*
            r6 = this;
            int r0 = r8.getSizeInWords()
            gnu.bytecode.Type[] r1 = r6.local_types
            r2 = 2
            r3 = 0
            if (r1 != 0) goto L_0x0013
            int r1 = r7 + r0
            int r1 = r1 + 20
            gnu.bytecode.Type[] r1 = new gnu.bytecode.Type[r1]
            r6.local_types = r1
            goto L_0x0022
        L_0x0013:
            int r4 = r7 + r0
            int r5 = r1.length
            if (r4 <= r5) goto L_0x0022
            int r4 = r4 * 2
            gnu.bytecode.Type[] r4 = new gnu.bytecode.Type[r4]
            int r5 = r1.length
            java.lang.System.arraycopy(r1, r3, r4, r3, r5)
            r6.local_types = r4
        L_0x0022:
            gnu.bytecode.Type[] r1 = r6.local_types
            r1[r7] = r8
            boolean[] r8 = r6.varsSetInCurrentBlock
            if (r8 != 0) goto L_0x0030
            int r8 = r1.length
            boolean[] r8 = new boolean[r8]
            r6.varsSetInCurrentBlock = r8
            goto L_0x003c
        L_0x0030:
            int r4 = r8.length
            if (r4 > r7) goto L_0x003c
            int r1 = r1.length
            boolean[] r1 = new boolean[r1]
            int r4 = r8.length
            java.lang.System.arraycopy(r8, r3, r1, r3, r4)
            r6.varsSetInCurrentBlock = r1
        L_0x003c:
            boolean[] r8 = r6.varsSetInCurrentBlock
            r1 = 1
            r8[r7] = r1
            r8 = 0
            if (r7 <= 0) goto L_0x0056
            gnu.bytecode.Type[] r3 = r6.local_types
            int r4 = r7 + -1
            r3 = r3[r4]
            if (r3 == 0) goto L_0x0056
            int r3 = r3.getSizeInWords()
            if (r3 != r2) goto L_0x0056
            gnu.bytecode.Type[] r2 = r6.local_types
            r2[r4] = r8
        L_0x0056:
            int r0 = r0 + -1
            if (r0 <= 0) goto L_0x0060
            gnu.bytecode.Type[] r2 = r6.local_types
            int r7 = r7 + r1
            r2[r7] = r8
            goto L_0x0056
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.noteVarType(int, gnu.bytecode.Type):void");
    }

    public final void setTypes(Label label) {
        setTypes(label.localTypes, label.stackTypes);
    }

    public final void setTypes(Type[] typeArr, Type[] typeArr2) {
        int length = typeArr2.length;
        int length2 = typeArr.length;
        Type[] typeArr3 = this.local_types;
        if (typeArr3 != null) {
            if (length2 > 0) {
                System.arraycopy(typeArr, 0, typeArr3, 0, length2);
            }
            while (true) {
                Type[] typeArr4 = this.local_types;
                if (length2 >= typeArr4.length) {
                    break;
                }
                typeArr4[length2] = null;
                length2++;
            }
        }
        Type[] typeArr5 = this.stack_types;
        if (typeArr5 != null && length <= typeArr5.length) {
            int i = length;
            while (true) {
                Type[] typeArr6 = this.stack_types;
                if (i >= typeArr6.length) {
                    break;
                }
                typeArr6[i] = null;
                i++;
            }
        } else {
            this.stack_types = new Type[length];
        }
        System.arraycopy(typeArr2, 0, this.stack_types, 0, length);
        this.SP = length;
    }

    public final void pushType(Type type) {
        if (type.size != 0) {
            Type[] typeArr = this.stack_types;
            if (typeArr == null || typeArr.length == 0) {
                this.stack_types = new Type[20];
            } else {
                int i = this.SP;
                if (i + 1 >= typeArr.length) {
                    Type[] typeArr2 = new Type[(typeArr.length * 2)];
                    System.arraycopy(typeArr, 0, typeArr2, 0, i);
                    this.stack_types = typeArr2;
                }
            }
            if (type.size == 8) {
                Type[] typeArr3 = this.stack_types;
                int i2 = this.SP;
                this.SP = i2 + 1;
                typeArr3[i2] = Type.voidType;
            }
            Type[] typeArr4 = this.stack_types;
            int i3 = this.SP;
            int i4 = i3 + 1;
            this.SP = i4;
            typeArr4[i3] = type;
            if (i4 > this.max_stack) {
                this.max_stack = i4;
                return;
            }
            return;
        }
        throw new Error("pushing void type onto stack");
    }

    public final Type popType() {
        int i = this.SP;
        if (i > 0) {
            Type[] typeArr = this.stack_types;
            int i2 = i - 1;
            this.SP = i2;
            Type type = typeArr[i2];
            if (type.size != 8 || popType().isVoid()) {
                return type;
            }
            throw new Error("missing void type on stack");
        }
        throw new Error("popType called with empty stack " + getMethod());
    }

    public final Type topType() {
        return this.stack_types[this.SP - 1];
    }

    public void emitPop(int i) {
        while (i > 0) {
            reserve(1);
            if (popType().size > 4) {
                put1(88);
            } else if (i > 1) {
                if (popType().size > 4) {
                    put1(87);
                    reserve(1);
                }
                put1(88);
                i--;
            } else {
                put1(87);
            }
            i--;
        }
    }

    public Label getLabel() {
        Label label = new Label();
        label.defineRaw(this);
        return label;
    }

    public void emitSwap() {
        reserve(1);
        Type popType = popType();
        Type popType2 = popType();
        if (popType.size > 4 || popType2.size > 4) {
            pushType(popType2);
            pushType(popType);
            emitDupX();
            emitPop(1);
            return;
        }
        pushType(popType);
        put1(95);
        pushType(popType2);
    }

    public void emitDup() {
        reserve(1);
        Type type = topType();
        put1(type.size <= 4 ? 89 : 92);
        pushType(type);
    }

    public void emitDupX() {
        reserve(1);
        Type popType = popType();
        Type popType2 = popType();
        if (popType2.size <= 4) {
            put1(popType.size <= 4 ? 90 : 93);
        } else {
            put1(popType.size <= 4 ? 91 : 94);
        }
        pushType(popType);
        pushType(popType2);
        pushType(popType);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void emitDup(int r9, int r10) {
        /*
            r8 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 1
            r8.reserve(r0)
            gnu.bytecode.Type r1 = r8.popType()
            r2 = 2
            java.lang.String r3 = "dup will cause invalid types on stack"
            r4 = 0
            r5 = 4
            if (r9 != r0) goto L_0x001f
            int r6 = r1.size
            if (r6 > r5) goto L_0x0017
            goto L_0x0034
        L_0x0017:
            java.lang.Error r9 = new java.lang.Error
            java.lang.String r10 = "using dup for 2-word type"
            r9.<init>(r10)
            throw r9
        L_0x001f:
            if (r9 != r2) goto L_0x009e
            int r6 = r1.size
            if (r6 > r5) goto L_0x0034
            gnu.bytecode.Type r6 = r8.popType()
            int r7 = r6.size
            if (r7 > r5) goto L_0x002e
            goto L_0x0035
        L_0x002e:
            java.lang.Error r9 = new java.lang.Error
            r9.<init>(r3)
            throw r9
        L_0x0034:
            r6 = r4
        L_0x0035:
            if (r10 != 0) goto L_0x0040
            if (r9 != r0) goto L_0x003c
            r9 = 89
            goto L_0x003e
        L_0x003c:
            r9 = 92
        L_0x003e:
            r10 = r4
            goto L_0x0078
        L_0x0040:
            if (r10 != r0) goto L_0x0058
            if (r9 != r0) goto L_0x0047
            r9 = 90
            goto L_0x0049
        L_0x0047:
            r9 = 93
        L_0x0049:
            gnu.bytecode.Type r10 = r8.popType()
            int r0 = r10.size
            if (r0 > r5) goto L_0x0052
            goto L_0x0078
        L_0x0052:
            java.lang.Error r9 = new java.lang.Error
            r9.<init>(r3)
            throw r9
        L_0x0058:
            if (r10 != r2) goto L_0x0096
            if (r9 != r0) goto L_0x005f
            r9 = 91
            goto L_0x0061
        L_0x005f:
            r9 = 94
        L_0x0061:
            gnu.bytecode.Type r10 = r8.popType()
            int r0 = r10.size
            if (r0 > r5) goto L_0x0078
            gnu.bytecode.Type r4 = r8.popType()
            int r0 = r4.size
            if (r0 > r5) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            java.lang.Error r9 = new java.lang.Error
            r9.<init>(r3)
            throw r9
        L_0x0078:
            r8.put1(r9)
            if (r6 == 0) goto L_0x0080
            r8.pushType(r6)
        L_0x0080:
            r8.pushType(r1)
            if (r4 == 0) goto L_0x0088
            r8.pushType(r4)
        L_0x0088:
            if (r10 == 0) goto L_0x008d
            r8.pushType(r10)
        L_0x008d:
            if (r6 == 0) goto L_0x0092
            r8.pushType(r6)
        L_0x0092:
            r8.pushType(r1)
            return
        L_0x0096:
            java.lang.Error r9 = new java.lang.Error
            java.lang.String r10 = "emitDup:  invalid offset"
            r9.<init>(r10)
            throw r9
        L_0x009e:
            java.lang.Error r9 = new java.lang.Error
            java.lang.String r10 = "invalid size to emitDup"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.emitDup(int, int):void");
    }

    public void emitDup(int i) {
        emitDup(i, 0);
    }

    public void emitDup(Type type) {
        emitDup(type.size > 4 ? 2 : 1, 0);
    }

    public void enterScope(Scope scope) {
        scope.setStartPC(this);
        this.locals.enterScope(scope);
    }

    public Scope pushScope() {
        Scope scope = new Scope();
        if (this.locals == null) {
            this.locals = new LocalVarsAttr(getMethod());
        }
        enterScope(scope);
        if (this.locals.parameter_scope == null) {
            this.locals.parameter_scope = scope;
        }
        return scope;
    }

    public Scope getCurrentScope() {
        return this.locals.current_scope;
    }

    public Scope popScope() {
        Scope scope = this.locals.current_scope;
        this.locals.current_scope = scope.parent;
        scope.freeLocals(this);
        scope.end = getLabel();
        return scope;
    }

    public Variable getArg(int i) {
        return this.locals.parameter_scope.getVariable(i);
    }

    public Variable lookup(String str) {
        for (Scope scope = this.locals.current_scope; scope != null; scope = scope.parent) {
            Variable lookup = scope.lookup(str);
            if (lookup != null) {
                return lookup;
            }
        }
        return null;
    }

    public Variable addLocal(Type type) {
        return this.locals.current_scope.addVariable(this, type, (String) null);
    }

    public Variable addLocal(Type type, String str) {
        return this.locals.current_scope.addVariable(this, type, str);
    }

    public void addParamLocals() {
        Method method = getMethod();
        if ((method.access_flags & 8) == 0) {
            addLocal(method.classfile).setParameter(true);
        }
        for (Type addLocal : method.arg_types) {
            addLocal(addLocal).setParameter(true);
        }
    }

    public final void emitPushConstant(int i, Type type) {
        char charAt = type.getSignature().charAt(0);
        if (charAt != 'F') {
            if (!(charAt == 'S' || charAt == 'Z' || charAt == 'I')) {
                if (charAt != 'J') {
                    switch (charAt) {
                        case 'B':
                        case 'C':
                            break;
                        case 'D':
                            emitPushDouble((double) i);
                            return;
                        default:
                            throw new Error("bad type to emitPushConstant");
                    }
                } else {
                    emitPushLong((long) i);
                    return;
                }
            }
            emitPushInt(i);
            return;
        }
        emitPushFloat((float) i);
    }

    public final void emitPushConstant(CpoolEntry cpoolEntry) {
        reserve(3);
        int i = cpoolEntry.index;
        if (cpoolEntry instanceof CpoolValue2) {
            put1(20);
            put2(i);
        } else if (i < 256) {
            put1(18);
            put1(i);
        } else {
            put1(19);
            put2(i);
        }
    }

    public final void emitPushInt(int i) {
        reserve(3);
        if (i >= -1 && i <= 5) {
            put1(i + 3);
        } else if (i >= -128 && i < 128) {
            put1(16);
            put1(i);
        } else if (i < -32768 || i >= 32768) {
            emitPushConstant(getConstants().addInt(i));
        } else {
            put1(17);
            put2(i);
        }
        pushType(Type.intType);
    }

    public void emitPushLong(long j) {
        if (j == 0 || j == 1) {
            reserve(1);
            put1(((int) j) + 9);
        } else {
            int i = (int) j;
            if (((long) i) == j) {
                emitPushInt(i);
                reserve(1);
                popType();
                put1(133);
            } else {
                emitPushConstant(getConstants().addLong(j));
            }
        }
        pushType(Type.longType);
    }

    public void emitPushFloat(float f) {
        int i = (int) f;
        if (((float) i) != f || i < -128 || i >= 128) {
            emitPushConstant(getConstants().addFloat(f));
        } else if (i < 0 || i > 2) {
            emitPushInt(i);
            reserve(1);
            popType();
            put1(134);
        } else {
            reserve(1);
            put1(i + 11);
            if (i == 0 && Float.floatToIntBits(f) != 0) {
                reserve(1);
                put1(118);
            }
        }
        pushType(Type.floatType);
    }

    public void emitPushDouble(double d) {
        int i = (int) d;
        if (((double) i) != d || i < -128 || i >= 128) {
            emitPushConstant(getConstants().addDouble(d));
        } else if (i == 0 || i == 1) {
            reserve(1);
            put1(i + 14);
            if (i == 0 && Double.doubleToLongBits(d) != 0) {
                reserve(1);
                put1(119);
            }
        } else {
            emitPushInt(i);
            reserve(1);
            popType();
            put1(135);
        }
        pushType(Type.doubleType);
    }

    public static final String calculateSplit(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(20);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            int i4 = charAt >= 2048 ? 3 : (charAt >= 128 || charAt == 0) ? 2 : 1;
            if (i2 + i4 > 65535) {
                stringBuffer.append((char) (i3 - i));
                i = i3;
                i2 = 0;
            }
            i2 += i4;
        }
        stringBuffer.append((char) (length - i));
        return stringBuffer.toString();
    }

    public final void emitPushString(String str) {
        if (str == null) {
            emitPushNull();
            return;
        }
        int length = str.length();
        String calculateSplit = calculateSplit(str);
        int length2 = calculateSplit.length();
        if (length2 <= 1) {
            emitPushConstant(getConstants().addString(str));
            pushType(Type.javalangStringType);
            return;
        }
        if (length2 == 2) {
            char charAt = calculateSplit.charAt(0);
            emitPushString(str.substring(0, charAt));
            emitPushString(str.substring(charAt));
            emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("concat", 1));
        } else {
            ClassType make = ClassType.make("java.lang.StringBuffer");
            emitNew(make);
            emitDup((Type) make);
            emitPushInt(length);
            emitInvokeSpecial(make.getDeclaredMethod("<init>", new Type[]{Type.intType}));
            Method declaredMethod = make.getDeclaredMethod("append", new Type[]{Type.javalangStringType});
            int i = 0;
            char c = 0;
            while (i < length2) {
                emitDup((Type) make);
                int charAt2 = calculateSplit.charAt(i) + c;
                emitPushString(str.substring(c, charAt2));
                emitInvokeVirtual(declaredMethod);
                i++;
                c = charAt2;
            }
            emitInvokeVirtual(Type.toString_method);
        }
        if (str == str.intern()) {
            emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("intern", 0));
        }
    }

    public final void emitPushClass(ObjectType objectType) {
        emitPushConstant(getConstants().addClass(objectType));
        pushType(Type.javalangClassType);
    }

    public void emitPushNull() {
        reserve(1);
        put1(1);
        pushType(Type.nullType);
    }

    public void emitPushDefaultValue(Type type) {
        Type implementationType = type.getImplementationType();
        if (implementationType instanceof PrimType) {
            emitPushConstant(0, implementationType);
        } else {
            emitPushNull();
        }
    }

    public void emitStoreDefaultValue(Variable variable) {
        emitPushDefaultValue(variable.getType());
        emitStore(variable);
    }

    public final void emitPushThis() {
        emitLoad(this.locals.used[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0058, code lost:
        if (r5 == 0) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        if (r5 == 0) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008e, code lost:
        if (r5 == 0) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a1, code lost:
        if (r5 == 0) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00af, code lost:
        if (r5 == 0) goto L_0x0068;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00cc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void emitPushPrimArray(java.lang.Object r21, gnu.bytecode.ArrayType r22) {
        /*
            r20 = this;
            r0 = r20
            gnu.bytecode.Type r1 = r22.getComponentType()
            int r2 = java.lang.reflect.Array.getLength(r21)
            r0.emitPushInt(r2)
            r0.emitNewArray((gnu.bytecode.Type) r1)
            java.lang.String r3 = r1.getSignature()
            r4 = 0
            char r3 = r3.charAt(r4)
        L_0x0019:
            if (r4 >= r2) goto L_0x00f1
            r6 = 74
            r7 = 73
            r8 = 90
            r9 = 83
            r10 = 70
            r11 = 0
            r13 = 0
            if (r3 == r10) goto L_0x00b2
            if (r3 == r9) goto L_0x00a4
            if (r3 == r8) goto L_0x0091
            if (r3 == r7) goto L_0x0083
            if (r3 == r6) goto L_0x0071
            switch(r3) {
                case 66: goto L_0x005b;
                case 67: goto L_0x004d;
                case 68: goto L_0x003b;
                default: goto L_0x0036;
            }
        L_0x0036:
            r6 = r22
        L_0x0038:
            r5 = 0
            goto L_0x00c4
        L_0x003b:
            r15 = r21
            double[] r15 = (double[]) r15
            double[] r15 = (double[]) r15
            r16 = r15[r4]
            int r15 = (r16 > r11 ? 1 : (r16 == r11 ? 0 : -1))
            if (r15 != 0) goto L_0x0048
            goto L_0x0068
        L_0x0048:
            r6 = r22
            r11 = r16
            goto L_0x0038
        L_0x004d:
            r15 = r21
            char[] r15 = (char[]) r15
            char[] r15 = (char[]) r15
            char r15 = r15[r4]
            long r5 = (long) r15
            int r15 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x006c
            goto L_0x0068
        L_0x005b:
            r5 = r21
            byte[] r5 = (byte[]) r5
            byte[] r5 = (byte[]) r5
            byte r5 = r5[r4]
            long r5 = (long) r5
            int r15 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x006c
        L_0x0068:
            r6 = r22
            goto L_0x00ed
        L_0x006c:
            r13 = r5
            r5 = 0
            r6 = r22
            goto L_0x00c4
        L_0x0071:
            r5 = r21
            long[] r5 = (long[]) r5
            long[] r5 = (long[]) r5
            r18 = r5[r4]
            int r5 = (r18 > r13 ? 1 : (r18 == r13 ? 0 : -1))
            if (r5 != 0) goto L_0x007e
            goto L_0x0068
        L_0x007e:
            r6 = r22
            r13 = r18
            goto L_0x0038
        L_0x0083:
            r5 = r21
            int[] r5 = (int[]) r5
            int[] r5 = (int[]) r5
            r5 = r5[r4]
            long r5 = (long) r5
            int r15 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x006c
            goto L_0x0068
        L_0x0091:
            r5 = r21
            boolean[] r5 = (boolean[]) r5
            boolean[] r5 = (boolean[]) r5
            boolean r5 = r5[r4]
            if (r5 == 0) goto L_0x009e
            r5 = 1
            goto L_0x009f
        L_0x009e:
            r5 = r13
        L_0x009f:
            int r15 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x006c
            goto L_0x0068
        L_0x00a4:
            r5 = r21
            short[] r5 = (short[]) r5
            short[] r5 = (short[]) r5
            short r5 = r5[r4]
            long r5 = (long) r5
            int r15 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r15 != 0) goto L_0x006c
            goto L_0x0068
        L_0x00b2:
            r5 = r21
            float[] r5 = (float[]) r5
            float[] r5 = (float[]) r5
            r5 = r5[r4]
            double r13 = (double) r5
            int r6 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r6 != 0) goto L_0x00c0
            goto L_0x0068
        L_0x00c0:
            r6 = r22
            r13 = 0
        L_0x00c4:
            r0.emitDup((gnu.bytecode.Type) r6)
            r0.emitPushInt(r4)
            if (r3 == r10) goto L_0x00e7
            if (r3 == r9) goto L_0x00e2
            if (r3 == r8) goto L_0x00e2
            if (r3 == r7) goto L_0x00e2
            r5 = 74
            if (r3 == r5) goto L_0x00de
            switch(r3) {
                case 66: goto L_0x00e2;
                case 67: goto L_0x00e2;
                case 68: goto L_0x00da;
                default: goto L_0x00d9;
            }
        L_0x00d9:
            goto L_0x00ea
        L_0x00da:
            r0.emitPushDouble(r11)
            goto L_0x00ea
        L_0x00de:
            r0.emitPushLong(r13)
            goto L_0x00ea
        L_0x00e2:
            int r5 = (int) r13
            r0.emitPushInt(r5)
            goto L_0x00ea
        L_0x00e7:
            r0.emitPushFloat(r5)
        L_0x00ea:
            r0.emitArrayStore(r1)
        L_0x00ed:
            int r4 = r4 + 1
            goto L_0x0019
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.emitPushPrimArray(java.lang.Object, gnu.bytecode.ArrayType):void");
    }

    /* access modifiers changed from: package-private */
    public void emitNewArray(int i) {
        reserve(2);
        put1(188);
        put1(i);
    }

    public final void emitArrayLength() {
        if (popType() instanceof ArrayType) {
            reserve(1);
            put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK);
            pushType(Type.intType);
            return;
        }
        throw new Error("non-array type in emitArrayLength");
    }

    private int adjustTypedOp(Type type) {
        return adjustTypedOp(type.getSignature().charAt(0));
    }

    private void emitTypedOp(int i, Type type) {
        reserve(1);
        put1(i + adjustTypedOp(type));
    }

    private void emitTypedOp(int i, char c) {
        reserve(1);
        put1(i + adjustTypedOp(c));
    }

    public void emitArrayStore(Type type) {
        popType();
        popType();
        popType();
        emitTypedOp(79, type);
    }

    public void emitArrayStore() {
        popType();
        popType();
        emitTypedOp(79, ((ArrayType) popType().getImplementationType()).getComponentType());
    }

    public void emitArrayLoad(Type type) {
        popType();
        popType();
        emitTypedOp(46, type);
        pushType(type);
    }

    public void emitArrayLoad() {
        popType();
        Type componentType = ((ArrayType) popType().getImplementationType()).getComponentType();
        emitTypedOp(46, componentType);
        pushType(componentType);
    }

    public void emitNew(ClassType classType) {
        reserve(3);
        Label label = new Label(this);
        label.defineRaw(this);
        put1(187);
        putIndex2(getConstants().addClass((ObjectType) classType));
        pushType(new UninitializedType(classType, label));
    }

    public void emitNewArray(Type type, int i) {
        if (popType().promote() == Type.intType) {
            int i2 = 4;
            if (type instanceof PrimType) {
                char charAt = type.getSignature().charAt(0);
                if (charAt == 'F') {
                    i2 = 6;
                } else if (charAt == 'S') {
                    i2 = 9;
                } else if (charAt != 'Z') {
                    if (charAt == 'I') {
                        i2 = 10;
                    } else if (charAt != 'J') {
                        switch (charAt) {
                            case 'B':
                                i2 = 8;
                                break;
                            case 'C':
                                i2 = 5;
                                break;
                            case 'D':
                                i2 = 7;
                                break;
                            default:
                                throw new Error("bad PrimType in emitNewArray");
                        }
                    } else {
                        i2 = 11;
                    }
                }
                emitNewArray(i2);
            } else if (type instanceof ObjectType) {
                reserve(3);
                put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG);
                putIndex2(getConstants().addClass((ObjectType) type));
            } else if (type instanceof ArrayType) {
                reserve(4);
                put1(197);
                putIndex2(getConstants().addClass((ObjectType) new ArrayType(type)));
                if (i < 1 || i > 255) {
                    throw new Error("dims out of range in emitNewArray");
                }
                put1(i);
                do {
                    i--;
                    if (i > 0) {
                    }
                } while (popType().promote() == Type.intType);
                throw new Error("non-int dim. spec. in emitNewArray");
            } else {
                throw new Error("unimplemented type in emitNewArray");
            }
            pushType(new ArrayType(type));
            return;
        }
        throw new Error("non-int dim. spec. in emitNewArray");
    }

    public void emitNewArray(Type type) {
        emitNewArray(type, 1);
    }

    private void emitBinop(int i) {
        Type promote = popType().promote();
        Type popType = popType();
        Type promote2 = popType.promote();
        if (promote2 != promote || !(promote2 instanceof PrimType)) {
            throw new Error("non-matching or bad types in binary operation");
        }
        emitTypedOp(i, promote2);
        pushType(popType);
    }

    private void emitBinop(int i, char c) {
        popType();
        popType();
        emitTypedOp(i, c);
        pushType(Type.signatureToPrimitive(c));
    }

    public void emitBinop(int i, Type type) {
        popType();
        popType();
        emitTypedOp(i, type);
        pushType(type);
    }

    public final void emitAdd(char c) {
        emitBinop(96, c);
    }

    public final void emitAdd(PrimType primType) {
        emitBinop(96, (Type) primType);
    }

    public final void emitAdd() {
        emitBinop(96);
    }

    public final void emitSub(char c) {
        emitBinop(100, c);
    }

    public final void emitSub(PrimType primType) {
        emitBinop(100, (Type) primType);
    }

    public final void emitSub() {
        emitBinop(100);
    }

    public final void emitMul() {
        emitBinop(104);
    }

    public final void emitDiv() {
        emitBinop(108);
    }

    public final void emitRem() {
        emitBinop(112);
    }

    public final void emitAnd() {
        emitBinop(126);
    }

    public final void emitIOr() {
        emitBinop(128);
    }

    public final void emitXOr() {
        emitBinop(130);
    }

    public final void emitShl() {
        emitShift(120);
    }

    public final void emitShr() {
        emitShift(122);
    }

    public final void emitUshr() {
        emitShift(124);
    }

    private void emitShift(int i) {
        Type promote = popType().promote();
        Type popType = popType();
        Type promote2 = popType.promote();
        if (promote2 != Type.intType && promote2 != Type.longType) {
            throw new Error("the value shifted must be an int or a long");
        } else if (promote == Type.intType) {
            emitTypedOp(i, promote2);
            pushType(popType);
        } else {
            throw new Error("the amount of shift must be an int");
        }
    }

    public final void emitNot(Type type) {
        emitPushConstant(1, type);
        emitAdd();
        emitPushConstant(1, type);
        emitAnd();
    }

    public void emitPrimop(int i, int i2, Type type) {
        reserve(1);
        while (true) {
            i2--;
            if (i2 >= 0) {
                popType();
            } else {
                put1(i);
                pushType(type);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void emitMaybeWide(int i, int i2) {
        if (i2 >= 256) {
            put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
            put1(i);
            put2(i2);
            return;
        }
        put1(i);
        put1(i2);
    }

    public final void emitLoad(Variable variable) {
        if (!variable.dead()) {
            int i = variable.offset;
            if (i < 0 || !variable.isSimple()) {
                throw new Error("attempting to load from unassigned variable " + variable + " simple:" + variable.isSimple() + ", offset: " + i);
            }
            Type promote = variable.getType().promote();
            reserve(4);
            int adjustTypedOp = adjustTypedOp(promote);
            if (i <= 3) {
                put1((adjustTypedOp * 4) + 26 + i);
            } else {
                emitMaybeWide(adjustTypedOp + 21, i);
            }
            pushType(variable.getType());
            return;
        }
        throw new Error("attempting to push dead variable");
    }

    public void emitStore(Variable variable) {
        int i = variable.offset;
        if (i < 0 || !variable.isSimple()) {
            throw new Error("attempting to store in unassigned " + variable + " simple:" + variable.isSimple() + ", offset: " + i);
        }
        Type promote = variable.getType().promote();
        noteVarType(i, promote);
        reserve(4);
        popType();
        int adjustTypedOp = adjustTypedOp(promote);
        if (i <= 3) {
            put1((adjustTypedOp * 4) + 59 + i);
        } else {
            emitMaybeWide(adjustTypedOp + 54, i);
        }
    }

    public void emitInc(Variable variable, short s) {
        if (!variable.dead()) {
            int i = variable.offset;
            if (i < 0 || !variable.isSimple()) {
                throw new Error("attempting to increment unassigned variable" + variable.getName() + " simple:" + variable.isSimple() + ", offset: " + i);
            }
            reserve(6);
            if (variable.getType().getImplementationType().promote() == Type.intType) {
                if (i > 255 || s > 255 || s < -256) {
                    put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
                    put1(132);
                    put2(i);
                    put2(s);
                    return;
                }
                put1(132);
                put1(i);
                put1(s);
                return;
            }
            throw new Error("attempting to increment non-int variable");
        }
        throw new Error("attempting to increment dead variable");
    }

    private final void emitFieldop(Field field, int i) {
        reserve(3);
        put1(i);
        putIndex2(getConstants().addFieldRef(field));
    }

    public final void emitGetStatic(Field field) {
        pushType(field.type);
        emitFieldop(field, 178);
    }

    public final void emitGetField(Field field) {
        popType();
        pushType(field.type);
        emitFieldop(field, 180);
    }

    public final void emitPutStatic(Field field) {
        popType();
        emitFieldop(field, 179);
    }

    public final void emitPutField(Field field) {
        popType();
        popType();
        emitFieldop(field, 181);
    }

    private int words(Type[] typeArr) {
        int length = typeArr.length;
        int i = 0;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = typeArr[length].size > 4 ? i + 2 : i + 1;
        }
    }

    public void emitInvokeMethod(Method method, int i) {
        int i2;
        Type popType;
        reserve(i == 185 ? 5 : 3);
        int length = method.arg_types.length;
        int i3 = 0;
        boolean z = i == 184;
        boolean z2 = i == 183 && "<init>".equals(method.getName());
        if (z == ((method.access_flags & 8) != 0)) {
            if (!z && !z2) {
                length++;
            }
            put1(i);
            putIndex2(getConstants().addMethodRef(method));
            if (i == 185) {
                put1(words(method.arg_types) + 1);
                put1(0);
            }
            do {
                length--;
                if (length >= 0) {
                    popType = popType();
                } else {
                    if (z2) {
                        Type popType2 = popType();
                        if (popType2 instanceof UninitializedType) {
                            ClassType classType = ((UninitializedType) popType2).ctype;
                            for (int i4 = 0; i4 < this.SP; i4++) {
                                Type[] typeArr = this.stack_types;
                                if (typeArr[i4] == popType2) {
                                    typeArr[i4] = classType;
                                }
                            }
                            Variable[] variableArr = this.locals.used;
                            if (variableArr == null) {
                                i2 = 0;
                            } else {
                                i2 = variableArr.length;
                            }
                            while (true) {
                                i2--;
                                if (i2 < 0) {
                                    break;
                                }
                                Variable variable = variableArr[i2];
                                if (variable != null && variable.type == popType2) {
                                    variable.type = classType;
                                }
                            }
                            Type[] typeArr2 = this.local_types;
                            if (typeArr2 != null) {
                                i3 = typeArr2.length;
                            }
                            while (true) {
                                i3--;
                                if (i3 < 0) {
                                    break;
                                }
                                Type[] typeArr3 = this.local_types;
                                if (typeArr3[i3] == popType2) {
                                    typeArr3[i3] = classType;
                                }
                            }
                        } else {
                            throw new Error("calling <init> on already-initialized object");
                        }
                    }
                    if (method.return_type.size != 0) {
                        pushType(method.return_type);
                        return;
                    }
                    return;
                }
            } while (!(popType instanceof UninitializedType));
            throw new Error("passing " + popType + " as parameter");
        }
        throw new Error("emitInvokeXxx static flag mis-match method.flags=" + method.access_flags);
    }

    public void emitInvoke(Method method) {
        int i;
        if ((method.access_flags & 8) != 0) {
            i = 184;
        } else if (method.classfile.isInterface()) {
            i = 185;
        } else {
            i = "<init>".equals(method.getName()) ? 183 : 182;
        }
        emitInvokeMethod(method, i);
    }

    public void emitInvokeVirtual(Method method) {
        emitInvokeMethod(method, 182);
    }

    public void emitInvokeSpecial(Method method) {
        emitInvokeMethod(method, 183);
    }

    public void emitInvokeStatic(Method method) {
        emitInvokeMethod(method, 184);
    }

    public void emitInvokeInterface(Method method) {
        emitInvokeMethod(method, 185);
    }

    /* access modifiers changed from: package-private */
    public final void emitTransfer(Label label, int i) {
        label.setTypes(this);
        fixupAdd(6, label);
        put1(i);
        this.PC += 2;
    }

    public final void emitGoto(Label label) {
        label.setTypes(this);
        fixupAdd(4, label);
        reserve(3);
        put1(167);
        this.PC += 2;
        setUnreachable();
    }

    public final void emitJsr(Label label) {
        fixupAdd(5, label);
        reserve(3);
        put1(168);
        this.PC += 2;
    }

    public ExitableBlock startExitableBlock(Type type, boolean z) {
        ExitableBlock exitableBlock = new ExitableBlock(type, this, z);
        exitableBlock.outer = this.currentExitableBlock;
        this.currentExitableBlock = exitableBlock;
        return exitableBlock;
    }

    public void endExitableBlock() {
        ExitableBlock exitableBlock = this.currentExitableBlock;
        exitableBlock.finish();
        this.currentExitableBlock = exitableBlock.outer;
    }

    public final void emitGotoIfCompare1(Label label, int i) {
        popType();
        reserve(3);
        emitTransfer(label, i);
    }

    public final void emitGotoIfIntEqZero(Label label) {
        emitGotoIfCompare1(label, 153);
    }

    public final void emitGotoIfIntNeZero(Label label) {
        emitGotoIfCompare1(label, 154);
    }

    public final void emitGotoIfIntLtZero(Label label) {
        emitGotoIfCompare1(label, 155);
    }

    public final void emitGotoIfIntGeZero(Label label) {
        emitGotoIfCompare1(label, 156);
    }

    public final void emitGotoIfIntGtZero(Label label) {
        emitGotoIfCompare1(label, 157);
    }

    public final void emitGotoIfIntLeZero(Label label) {
        emitGotoIfCompare1(label, 158);
    }

    public final void emitGotoIfCompare2(Label label, int i) {
        if (i < 153 || i > 158) {
            throw new Error("emitGotoIfCompare2: logop must be one of ifeq...ifle");
        }
        Type promote = popType().promote();
        Type promote2 = popType().promote();
        reserve(4);
        boolean z = false;
        char charAt = promote2.getSignature().charAt(0);
        char charAt2 = promote.getSignature().charAt(0);
        if (i == 155 || i == 158) {
            z = true;
        }
        if (charAt == 'I' && charAt2 == 'I') {
            i += 6;
        } else if (charAt == 'J' && charAt2 == 'J') {
            put1(148);
        } else if (charAt == 'F' && charAt2 == 'F') {
            put1(z ? 149 : 150);
        } else if (charAt == 'D' && charAt2 == 'D') {
            put1(z ? 151 : 152);
        } else if ((charAt == 'L' || charAt == '[') && ((charAt2 == 'L' || charAt2 == '[') && i <= 154)) {
            i += 12;
        } else {
            throw new Error("invalid types to emitGotoIfCompare2");
        }
        emitTransfer(label, i);
    }

    public final void emitGotoIfEq(Label label, boolean z) {
        emitGotoIfCompare2(label, z ? 154 : 153);
    }

    public final void emitGotoIfEq(Label label) {
        emitGotoIfCompare2(label, 153);
    }

    public final void emitGotoIfNE(Label label) {
        emitGotoIfCompare2(label, 154);
    }

    public final void emitGotoIfLt(Label label) {
        emitGotoIfCompare2(label, 155);
    }

    public final void emitGotoIfGe(Label label) {
        emitGotoIfCompare2(label, 156);
    }

    public final void emitGotoIfGt(Label label) {
        emitGotoIfCompare2(label, 157);
    }

    public final void emitGotoIfLe(Label label) {
        emitGotoIfCompare2(label, 158);
    }

    public final void emitIfCompare1(int i) {
        IfState ifState = new IfState(this);
        if (popType().promote() == Type.intType) {
            reserve(3);
            emitTransfer(ifState.end_label, i);
            ifState.start_stack_size = this.SP;
            return;
        }
        throw new Error("non-int type to emitIfCompare1");
    }

    public final void emitIfIntNotZero() {
        emitIfCompare1(153);
    }

    public final void emitIfIntEqZero() {
        emitIfCompare1(154);
    }

    public final void emitIfIntLEqZero() {
        emitIfCompare1(157);
    }

    public final void emitIfRefCompare1(int i) {
        IfState ifState = new IfState(this);
        if (popType() instanceof ObjectType) {
            reserve(3);
            emitTransfer(ifState.end_label, i);
            ifState.start_stack_size = this.SP;
            return;
        }
        throw new Error("non-ref type to emitIfRefCompare1");
    }

    public final void emitIfNotNull() {
        emitIfRefCompare1(198);
    }

    public final void emitIfNull() {
        emitIfRefCompare1(199);
    }

    public final void emitIfIntCompare(int i) {
        IfState ifState = new IfState(this);
        popType();
        popType();
        reserve(3);
        emitTransfer(ifState.end_label, i);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfIntLt() {
        emitIfIntCompare(162);
    }

    public final void emitIfNEq() {
        IfState ifState = new IfState(this);
        emitGotoIfEq(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfEq() {
        IfState ifState = new IfState(this);
        emitGotoIfNE(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfLt() {
        IfState ifState = new IfState(this);
        emitGotoIfGe(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfGe() {
        IfState ifState = new IfState(this);
        emitGotoIfLt(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfGt() {
        IfState ifState = new IfState(this);
        emitGotoIfLe(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public final void emitIfLe() {
        IfState ifState = new IfState(this);
        emitGotoIfGt(ifState.end_label);
        ifState.start_stack_size = this.SP;
    }

    public void emitRet(Variable variable) {
        int i = variable.offset;
        if (i < 256) {
            reserve(2);
            put1(169);
            put1(i);
            return;
        }
        reserve(4);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION);
        put1(169);
        put2(i);
    }

    public final void emitThen() {
        this.if_stack.start_stack_size = this.SP;
    }

    public final void emitIfThen() {
        new IfState(this, (Label) null);
    }

    public final void emitElse() {
        Label label = this.if_stack.end_label;
        if (reachableHere()) {
            Label label2 = new Label(this);
            this.if_stack.end_label = label2;
            int i = this.SP - this.if_stack.start_stack_size;
            this.if_stack.stack_growth = i;
            if (i > 0) {
                this.if_stack.then_stacked_types = new Type[i];
                System.arraycopy(this.stack_types, this.if_stack.start_stack_size, this.if_stack.then_stacked_types, 0, i);
            } else {
                this.if_stack.then_stacked_types = new Type[0];
            }
            emitGoto(label2);
        } else {
            this.if_stack.end_label = null;
        }
        while (this.SP > this.if_stack.start_stack_size) {
            popType();
        }
        this.SP = this.if_stack.start_stack_size;
        if (label != null) {
            label.define(this);
        }
        this.if_stack.doing_else = true;
    }

    public final void emitFi() {
        boolean z = false;
        if (!this.if_stack.doing_else) {
            if (reachableHere() && this.SP != this.if_stack.start_stack_size) {
                throw new Error("at PC " + this.PC + " then clause grows stack with no else clause");
            }
        } else if (this.if_stack.then_stacked_types != null) {
            int i = this.if_stack.start_stack_size + this.if_stack.stack_growth;
            if (!reachableHere()) {
                if (this.if_stack.stack_growth > 0) {
                    System.arraycopy(this.if_stack.then_stacked_types, 0, this.stack_types, this.if_stack.start_stack_size, this.if_stack.stack_growth);
                }
                this.SP = i;
            } else if (this.SP != i) {
                throw new Error("at PC " + this.PC + ": SP at end of 'then' was " + i + " while SP at end of 'else' was " + this.SP);
            }
        } else if (this.unreachable_here) {
            z = true;
        }
        if (this.if_stack.end_label != null) {
            this.if_stack.end_label.define(this);
        }
        if (z) {
            setUnreachable();
        }
        this.if_stack = this.if_stack.previous;
    }

    public final void emitConvert(Type type, Type type2) {
        String signature = type2.getSignature();
        String signature2 = type.getSignature();
        int i = -1;
        if (signature.length() == 1 || signature2.length() == 1) {
            char charAt = signature.charAt(0);
            char charAt2 = signature2.charAt(0);
            if (charAt2 != charAt) {
                if (type.size < 4) {
                    charAt2 = Access.INNERCLASS_CONTEXT;
                }
                if (type2.size < 4) {
                    emitConvert(type, Type.intType);
                    charAt2 = Access.INNERCLASS_CONTEXT;
                }
                if (charAt2 != charAt) {
                    if (charAt2 != 'D') {
                        if (charAt2 != 'F') {
                            if (charAt2 != 'I') {
                                if (charAt2 == 'J') {
                                    if (charAt == 'D') {
                                        i = 138;
                                    } else if (charAt == 'F') {
                                        i = 137;
                                    } else if (charAt == 'I') {
                                        i = 136;
                                    }
                                }
                            } else if (charAt == 'F') {
                                i = 134;
                            } else if (charAt == 'J') {
                                i = 133;
                            } else if (charAt != 'S') {
                                switch (charAt) {
                                    case 'B':
                                        i = 145;
                                        break;
                                    case 'C':
                                        i = 146;
                                        break;
                                    case 'D':
                                        i = 135;
                                        break;
                                }
                            } else {
                                i = 147;
                            }
                        } else if (charAt == 'D') {
                            i = 141;
                        } else if (charAt == 'I') {
                            i = 139;
                        } else if (charAt == 'J') {
                            i = 140;
                        }
                    } else if (charAt == 'F') {
                        i = 144;
                    } else if (charAt == 'I') {
                        i = 142;
                    } else if (charAt == 'J') {
                        i = 143;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        if (i >= 0) {
            reserve(1);
            popType();
            put1(i);
            pushType(type2);
            return;
        }
        throw new Error("unsupported CodeAttr.emitConvert");
    }

    private void emitCheckcast(Type type, int i) {
        reserve(3);
        popType();
        put1(i);
        if (type instanceof ObjectType) {
            putIndex2(getConstants().addClass((ObjectType) type));
            return;
        }
        throw new Error("unimplemented type " + type + " in emitCheckcast/emitInstanceof");
    }

    public static boolean castNeeded(Type type, Type type2) {
        if (type instanceof UninitializedType) {
            type = ((UninitializedType) type).getImplementationType();
        }
        while (type != type2) {
            if ((type2 instanceof ClassType) && (type instanceof ClassType) && ((ClassType) type).isSubclass((ClassType) type2)) {
                return false;
            }
            if (!(type2 instanceof ArrayType) || !(type instanceof ArrayType)) {
                return true;
            }
            type2 = ((ArrayType) type2).getComponentType();
            type = ((ArrayType) type).getComponentType();
        }
        return false;
    }

    public void emitCheckcast(Type type) {
        if (castNeeded(topType(), type)) {
            emitCheckcast(type, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE);
            pushType(type);
        }
    }

    public void emitInstanceof(Type type) {
        emitCheckcast(type, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP);
        pushType(Type.booleanType);
    }

    public final void emitThrow() {
        popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY);
        setUnreachable();
    }

    public final void emitMonitorEnter() {
        popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE);
    }

    public final void emitMonitorExit() {
        popType();
        reserve(1);
        put1(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN);
    }

    public final void emitReturn() {
        if (this.try_stack != null) {
            new Error();
        }
        emitRawReturn();
    }

    /* access modifiers changed from: package-private */
    public final void emitRawReturn() {
        if (getMethod().getReturnType().size == 0) {
            reserve(1);
            put1(177);
        } else {
            emitTypedOp(172, popType().promote());
        }
        setUnreachable();
    }

    public void addHandler(int i, int i2, int i3, int i4) {
        int i5 = this.exception_table_length * 4;
        short[] sArr = this.exception_table;
        if (sArr == null) {
            this.exception_table = new short[20];
        } else if (sArr.length <= i5) {
            short[] sArr2 = new short[(sArr.length * 2)];
            System.arraycopy(sArr, 0, sArr2, 0, i5);
            this.exception_table = sArr2;
        }
        short[] sArr3 = this.exception_table;
        int i6 = i5 + 1;
        sArr3[i5] = (short) i;
        int i7 = i6 + 1;
        sArr3[i6] = (short) i2;
        sArr3[i7] = (short) i3;
        sArr3[i7 + 1] = (short) i4;
        this.exception_table_length++;
    }

    public void addHandler(Label label, Label label2, ClassType classType) {
        int i;
        ConstantPool constants = getConstants();
        if (classType == null) {
            i = 0;
        } else {
            i = constants.addClass((ObjectType) classType).index;
        }
        fixupAdd(11, label);
        fixupAdd(12, i, label2);
        Label label3 = new Label();
        label3.localTypes = label.localTypes;
        label3.stackTypes = new Type[1];
        if (classType == null) {
            classType = Type.javalangThrowableType;
        }
        label3.stackTypes[0] = classType;
        setTypes(label3);
        fixupAdd(13, 0, label3);
    }

    public void emitWithCleanupStart() {
        int i = this.SP;
        this.SP = 0;
        emitTryStart(false, (Type) null);
        this.SP = i;
    }

    public void emitWithCleanupCatch(Variable variable) {
        Type[] typeArr;
        emitTryEnd();
        int i = this.SP;
        if (i > 0) {
            typeArr = new Type[i];
            System.arraycopy(this.stack_types, 0, typeArr, 0, i);
            this.SP = 0;
        } else {
            typeArr = null;
        }
        this.try_stack.savedTypes = typeArr;
        this.try_stack.saved_result = variable;
        emitCatchStart(variable);
    }

    public void emitWithCleanupDone() {
        Variable variable = this.try_stack.saved_result;
        this.try_stack.saved_result = null;
        if (variable != null) {
            emitLoad(variable);
        }
        emitThrow();
        emitCatchEnd();
        Type[] typeArr = this.try_stack.savedTypes;
        emitTryCatchEnd();
        if (typeArr != null) {
            int length = typeArr.length;
            this.SP = length;
            Type[] typeArr2 = this.stack_types;
            if (length >= typeArr2.length) {
                this.stack_types = typeArr;
            } else {
                System.arraycopy(typeArr, 0, typeArr2, 0, length);
            }
        } else {
            this.SP = 0;
        }
    }

    public void emitTryStart(boolean z, Type type) {
        Type[] typeArr;
        Variable[] variableArr = null;
        if (type != null && type.isVoid()) {
            type = null;
        }
        if (type != null || this.SP > 0) {
            pushScope();
        }
        int i = this.SP;
        if (i > 0) {
            variableArr = new Variable[i];
            int i2 = 0;
            while (this.SP > 0) {
                Variable addLocal = addLocal(topType());
                emitStore(addLocal);
                variableArr[i2] = addLocal;
                i2++;
            }
        }
        TryState tryState = new TryState(this);
        tryState.savedStack = variableArr;
        Type[] typeArr2 = this.local_types;
        int length = typeArr2 == null ? 0 : typeArr2.length;
        while (length > 0 && this.local_types[length - 1] == null) {
            length--;
        }
        if (length == 0) {
            typeArr = Type.typeArray0;
        } else {
            Type[] typeArr3 = new Type[length];
            System.arraycopy(this.local_types, 0, typeArr3, 0, length);
            typeArr = typeArr3;
        }
        tryState.start_try.localTypes = typeArr;
        if (type != null) {
            tryState.saved_result = addLocal(type);
        }
        if (z) {
            tryState.finally_subr = new Label();
        }
    }

    public void emitTryEnd() {
        emitTryEnd(false);
    }

    private void emitTryEnd(boolean z) {
        if (!this.try_stack.tryClauseDone) {
            this.try_stack.tryClauseDone = true;
            if (this.try_stack.finally_subr != null) {
                this.try_stack.exception = addLocal(Type.javalangThrowableType);
            }
            gotoFinallyOrEnd(z);
            this.try_stack.end_try = getLabel();
        }
    }

    public void emitCatchStart(Variable variable) {
        ClassType classType;
        emitTryEnd(false);
        setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
        if (this.try_stack.try_type != null) {
            emitCatchEnd();
        }
        if (variable == null) {
            classType = null;
        } else {
            classType = (ClassType) variable.getType();
        }
        this.try_stack.try_type = classType;
        addHandler(this.try_stack.start_try, this.try_stack.end_try, classType);
        if (variable != null) {
            emitStore(variable);
        }
    }

    public void emitCatchEnd() {
        gotoFinallyOrEnd(false);
        this.try_stack.try_type = null;
    }

    private void gotoFinallyOrEnd(boolean z) {
        if (reachableHere()) {
            if (this.try_stack.saved_result != null) {
                emitStore(this.try_stack.saved_result);
            }
            if (this.try_stack.end_label == null) {
                this.try_stack.end_label = new Label();
            }
            if (this.try_stack.finally_subr == null || useJsr()) {
                if (this.try_stack.finally_subr != null) {
                    emitJsr(this.try_stack.finally_subr);
                }
                emitGoto(this.try_stack.end_label);
                return;
            }
            if (this.try_stack.exitCases != null) {
                emitPushInt(0);
            }
            emitPushNull();
            if (!z) {
                emitGoto(this.try_stack.finally_subr);
            }
        }
    }

    public void emitFinallyStart() {
        emitTryEnd(true);
        if (this.try_stack.try_type != null) {
            emitCatchEnd();
        }
        this.try_stack.end_try = getLabel();
        pushScope();
        if (useJsr()) {
            this.SP = 0;
            emitCatchStart((Variable) null);
            emitStore(this.try_stack.exception);
            emitJsr(this.try_stack.finally_subr);
            emitLoad(this.try_stack.exception);
            emitThrow();
        } else {
            setUnreachable();
            int beginFragment = beginFragment(new Label(this));
            addHandler(this.try_stack.start_try, this.try_stack.end_try, Type.javalangThrowableType);
            if (this.try_stack.saved_result != null) {
                emitStoreDefaultValue(this.try_stack.saved_result);
            }
            if (this.try_stack.exitCases != null) {
                emitPushInt(-1);
                emitSwap();
            }
            emitGoto(this.try_stack.finally_subr);
            endFragment(beginFragment);
        }
        this.try_stack.finally_subr.define(this);
        if (useJsr()) {
            ClassType classType = Type.objectType;
            this.try_stack.finally_ret_addr = addLocal(classType);
            pushType(classType);
            emitStore(this.try_stack.finally_ret_addr);
        }
    }

    public void emitFinallyEnd() {
        if (useJsr()) {
            emitRet(this.try_stack.finally_ret_addr);
        } else if (this.try_stack.end_label == null && this.try_stack.exitCases == null) {
            emitThrow();
        } else {
            emitStore(this.try_stack.exception);
            emitLoad(this.try_stack.exception);
            emitIfNotNull();
            emitLoad(this.try_stack.exception);
            emitThrow();
            emitElse();
            ExitableBlock exitableBlock = this.try_stack.exitCases;
            if (exitableBlock != null) {
                SwitchState startSwitch = startSwitch();
                while (exitableBlock != null) {
                    ExitableBlock exitableBlock2 = exitableBlock.nextCase;
                    exitableBlock.nextCase = null;
                    exitableBlock.currentTryState = null;
                    TryState outerHandler = TryState.outerHandler(this.try_stack.previous, exitableBlock.initialTryState);
                    if (outerHandler == exitableBlock.initialTryState) {
                        startSwitch.addCaseGoto(exitableBlock.switchCase, this, exitableBlock.endLabel);
                    } else {
                        startSwitch.addCase(exitableBlock.switchCase, this);
                        exitableBlock.exit(outerHandler);
                    }
                    exitableBlock = exitableBlock2;
                }
                this.try_stack.exitCases = null;
                startSwitch.addDefault(this);
                startSwitch.finish(this);
            }
            emitFi();
            setUnreachable();
        }
        popScope();
        this.try_stack.finally_subr = null;
    }

    public void emitTryCatchEnd() {
        if (this.try_stack.finally_subr != null) {
            emitFinallyEnd();
        }
        Variable[] variableArr = this.try_stack.savedStack;
        if (this.try_stack.end_label == null) {
            setUnreachable();
        } else {
            setTypes(this.try_stack.start_try.localTypes, Type.typeArray0);
            this.try_stack.end_label.define(this);
            if (variableArr != null) {
                int length = variableArr.length;
                while (true) {
                    length--;
                    if (length < 0) {
                        break;
                    }
                    Variable variable = variableArr[length];
                    if (variable != null) {
                        emitLoad(variable);
                    }
                }
            }
            if (this.try_stack.saved_result != null) {
                emitLoad(this.try_stack.saved_result);
            }
        }
        if (!(this.try_stack.saved_result == null && variableArr == null)) {
            popScope();
        }
        this.try_stack = this.try_stack.previous;
    }

    public final TryState getCurrentTry() {
        return this.try_stack;
    }

    public final boolean isInTry() {
        return this.try_stack != null;
    }

    public SwitchState startSwitch() {
        SwitchState switchState = new SwitchState(this);
        switchState.switchValuePushed(this);
        return switchState;
    }

    public void emitTailCall(boolean z, Scope scope) {
        if (z) {
            Method method = getMethod();
            int i = (method.access_flags & 8) != 0 ? 0 : 1;
            int length = method.arg_types.length;
            while (true) {
                length--;
                int i2 = 2;
                if (length < 0) {
                    break;
                }
                if (method.arg_types[length].size <= 4) {
                    i2 = 1;
                }
                i += i2;
            }
            int length2 = method.arg_types.length;
            while (true) {
                length2--;
                if (length2 < 0) {
                    break;
                }
                i -= method.arg_types[length2].size > 4 ? 2 : 1;
                emitStore(this.locals.used[i]);
            }
        }
        emitGoto(scope.start);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01da, code lost:
        r7 = invert_opcode(r14);
        r14 = r6 + 1;
        r3[r6] = r7;
        r6 = r14 + 1;
        r3[r14] = 0;
        r3[r6] = 8;
        r6 = r6 + 1;
        r7 = com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.READ8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01f3, code lost:
        r7 = (byte) (r14 + com.google.appinventor.components.runtime.util.Ev3Constants.Opcode.OR16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01f6, code lost:
        r14 = r6 + 1;
        r3[r6] = r7;
        r6 = r14 + 1;
        r3[r14] = (byte) (r11 >> 24);
        r7 = r6 + 1;
        r3[r6] = (byte) (r11 >> 16);
        r6 = r7 + 1;
        r3[r7] = (byte) (r11 >> 8);
        r3[r6] = (byte) (r11 & 255);
        r1 = r1 + 3;
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x021c, code lost:
        if (r0.lines != null) goto L_0x0225;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x021e, code lost:
        r0.lines = new gnu.bytecode.LineNumbersAttr(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0225, code lost:
        r2 = r2 + 1;
        r7 = fixupOffset(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x022b, code lost:
        if (r7 == r10) goto L_0x0232;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x022d, code lost:
        r0.lines.put(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0232, code lost:
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0234, code lost:
        r11 = r2 + 2;
        r7 = r0.fixup_labels[r11];
        r14 = r2 + 1;
        r8 = fixupOffset(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0242, code lost:
        if (r0.stackMap == null) goto L_0x0248;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0244, code lost:
        r9 = mergeLabels(r9, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0248, code lost:
        addHandler(r0.fixup_labels[r2].position, r0.fixup_labels[r14].position, r6, r8);
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x025a, code lost:
        r7 = (3 - r6) & 3;
        r8 = r6 + 1;
        r14 = r1 + 1;
        r3[r6] = r0.code[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0267, code lost:
        r7 = r7 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0268, code lost:
        if (r7 < 0) goto L_0x0271;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x026a, code lost:
        r3[r8] = 0;
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003c, code lost:
        r7 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0274, code lost:
        if (r2 >= r0.fixup_count) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0276, code lost:
        r1 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x027c, code lost:
        if (fixupKind(r1) != 3) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x027e, code lost:
        r2 = fixupOffset(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0282, code lost:
        if (r14 >= r2) goto L_0x0292;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0284, code lost:
        r3[r8] = r0.code[r14];
        r8 = r8 + 1;
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0292, code lost:
        r2 = r0.fixup_labels[r1].position - r6;
        r7 = r8 + 1;
        r3[r8] = (byte) (r2 >> 24);
        r8 = r7 + 1;
        r3[r7] = (byte) (r2 >> 16);
        r7 = r8 + 1;
        r3[r8] = (byte) (r2 >> 8);
        r8 = r7 + 1;
        r3[r7] = (byte) (r2 & 255);
        r14 = r14 + 4;
        r2 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02b9, code lost:
        r6 = r8;
        r1 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02bf, code lost:
        if (r0.stackMap == null) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
        if (r7 < r0.fixup_count) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02c1, code lost:
        if (r11 == null) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02c5, code lost:
        if (r11.stackTypes == null) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x02c9, code lost:
        if (r11.needsStackMapEntry == false) goto L_0x02d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x02cb, code lost:
        r9 = mergeLabels(r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02d2, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x02d3, code lost:
        r2 = r2 + r7;
        r7 = fixupOffset(r2);
        r8 = 6;
        r15 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x02dc, code lost:
        r5 = r11.first_fixup;
        r6 = (r7 + r6) - fixupOffset(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r7 = r0.PC;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
        r7 = fixupOffset(r10[r7].first_fixup);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x032c, code lost:
        r3 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x032d, code lost:
        r5 = r5 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r0.fixup_offsets[r5] = (r7 << 4) | 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0054, code lost:
        if (r11 != null) goto L_0x02dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        r5 = r0.PC;
        r1 = 0;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005b, code lost:
        r8 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        if (r1 >= r0.fixup_count) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        r6 = r0.fixup_offsets[r1];
        r7 = r6 & 15;
        r11 = r0.fixup_labels[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006b, code lost:
        if (r11 == null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        if (r11.position < 0) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        throw new java.lang.Error("undefined label " + r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008a, code lost:
        if (r11 == null) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008c, code lost:
        if (r7 < 4) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008e, code lost:
        if (r7 > 7) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0095, code lost:
        if ((r11.first_fixup + 1) >= r0.fixup_count) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a7, code lost:
        if (r0.fixup_offsets[r11.first_fixup + 1] != ((r0.fixup_offsets[r11.first_fixup] & 15) | 4)) goto L_0x00b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a9, code lost:
        r11 = r0.fixup_labels[r11.first_fixup + 1];
        r0.fixup_labels[r1] = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b7, code lost:
        r3 = r6 >> 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b9, code lost:
        switch(r7) {
            case 0: goto L_0x0109;
            case 1: goto L_0x0106;
            case 2: goto L_0x00ff;
            case 3: goto L_0x0109;
            case 4: goto L_0x00e1;
            case 5: goto L_0x00e1;
            case 6: goto L_0x00e1;
            case 7: goto L_0x00bc;
            case 8: goto L_0x00dc;
            case 9: goto L_0x00cf;
            case 10: goto L_0x00bc;
            case 11: goto L_0x00c5;
            case 12: goto L_0x00bc;
            case 13: goto L_0x00bc;
            case 14: goto L_0x00c2;
            default: goto L_0x00bc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c1, code lost:
        throw new java.lang.Error("unexpected fixup");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c2, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c5, code lost:
        r1 = r1 + 2;
        r0.fixup_labels[r1].position = r3 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cf, code lost:
        if (r11 != null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        r1 = r11.first_fixup;
        r2 = (r3 + r2) - fixupOffset(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00dc, code lost:
        r2 = r2 - 3;
        r5 = r5 - 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e1, code lost:
        r4 = r11.position - (r3 + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e7, code lost:
        if (((short) r4) != r4) goto L_0x00f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e9, code lost:
        r0.fixup_offsets[r1] = (r3 << 4) | 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f2, code lost:
        if (r7 != 6) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f4, code lost:
        r4 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f6, code lost:
        r4 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f7, code lost:
        r2 = r2 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f8, code lost:
        if (r7 != 6) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fa, code lost:
        r10 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fc, code lost:
        r10 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fd, code lost:
        r5 = r5 + r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ff, code lost:
        r3 = (3 - (r3 + r2)) & 3;
        r2 = r2 + r3;
        r5 = r5 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0106, code lost:
        r11.position = r3 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0109, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x010e, code lost:
        r3 = new byte[r5];
        r7 = fixupOffset(0);
        r1 = 0;
        r2 = 0;
        r6 = 0;
        r9 = null;
        r10 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x011c, code lost:
        if (r1 >= r7) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x011e, code lost:
        r3[r6] = r0.code[r1];
        r6 = r6 + 1;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012c, code lost:
        r7 = r0.fixup_offsets[r2] & 15;
        r11 = r0.fixup_labels[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0136, code lost:
        if (r9 == null) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x013a, code lost:
        if (r9.position >= r6) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x013c, code lost:
        r0.stackMap.emitStackMapEntry(r9, r0);
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0142, code lost:
        if (r9 == null) goto L_0x0151;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0146, code lost:
        if (r9.position > r6) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0150, code lost:
        throw new java.lang.Error("labels out of order");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0151, code lost:
        if (r7 == 0) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        if (r7 == r15) goto L_0x02bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0156, code lost:
        if (r7 == 2) goto L_0x025a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x015a, code lost:
        if (r7 == 11) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015e, code lost:
        if (r7 == 14) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0160, code lost:
        switch(r7) {
            case 4: goto L_0x01d1;
            case 5: goto L_0x01d1;
            case 6: goto L_0x01d1;
            case 7: goto L_0x01b1;
            case 8: goto L_0x01ae;
            case 9: goto L_0x0169;
            default: goto L_0x0163;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0168, code lost:
        throw new java.lang.Error("unexpected fixup");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0169, code lost:
        if (r11 != null) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x016b, code lost:
        if (r5 != r6) goto L_0x017a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x016d, code lost:
        r0.PC = r5;
        r0.code = r3;
        r0.fixup_count = 0;
        r0.fixup_labels = null;
        r0.fixup_offsets = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0179, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0198, code lost:
        throw new java.lang.Error("PC confusion new_pc:" + r6 + " new_size:" + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0199, code lost:
        r2 = r11.first_fixup;
        r7 = fixupOffset(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01a1, code lost:
        if (r11.position != r6) goto L_0x01a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01a3, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01ad, code lost:
        throw new java.lang.Error("bad pc");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ae, code lost:
        r1 = r1 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01b1, code lost:
        r7 = r11.position - r6;
        r11 = r6 + 1;
        r3[r6] = r0.code[r1];
        r6 = r11 + 1;
        r3[r11] = (byte) (r7 >> 8);
        r3[r6] = (byte) (r7 & 255);
        r1 = r1 + 3;
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01cd, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01d1, code lost:
        r11 = r11.position - r6;
        r14 = r0.code[r1];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01d8, code lost:
        if (r7 != r8) goto L_0x01f3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processFixups() {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.fixup_count
            if (r1 > 0) goto L_0x0007
            return
        L_0x0007:
            r2 = 9
            r3 = 0
            r4 = 0
            r0.fixupAdd(r2, r3, r4)
            r5 = 0
            r6 = 0
        L_0x0010:
            int[] r7 = r0.fixup_offsets
            r7 = r7[r5]
            r8 = r7 & 15
            r9 = 4
            int r7 = r7 >> r9
            gnu.bytecode.Label[] r10 = r0.fixup_labels
            r11 = r10[r5]
            r12 = 32768(0x8000, float:4.5918E-41)
            java.lang.String r13 = "unexpected fixup"
            r15 = 1
            switch(r8) {
                case 0: goto L_0x032b;
                case 1: goto L_0x0324;
                case 2: goto L_0x0320;
                case 3: goto L_0x032b;
                case 4: goto L_0x02f8;
                case 5: goto L_0x02f6;
                case 6: goto L_0x02ea;
                case 7: goto L_0x0025;
                case 8: goto L_0x032b;
                case 9: goto L_0x003c;
                case 10: goto L_0x0035;
                case 11: goto L_0x002f;
                case 12: goto L_0x0025;
                case 13: goto L_0x0025;
                case 14: goto L_0x002b;
                default: goto L_0x0025;
            }
        L_0x0025:
            java.lang.Error r1 = new java.lang.Error
            r1.<init>(r13)
            throw r1
        L_0x002b:
            int r5 = r5 + 1
            goto L_0x032b
        L_0x002f:
            int r5 = r5 + 2
            r3 = 1
            r15 = 0
            goto L_0x032d
        L_0x0035:
            int r8 = r5 + 1
            r8 = r10[r8]
            r10[r1] = r8
            r1 = r7
        L_0x003c:
            int r7 = r5 + 1
            int r8 = r0.fixup_count
            if (r7 < r8) goto L_0x0045
            int r7 = r0.PC
            goto L_0x004d
        L_0x0045:
            r7 = r10[r7]
            int r7 = r7.first_fixup
            int r7 = r0.fixupOffset(r7)
        L_0x004d:
            int[] r8 = r0.fixup_offsets
            int r10 = r7 << 4
            r10 = r10 | r2
            r8[r5] = r10
            if (r11 != 0) goto L_0x02dc
            int r1 = r0.PC
            r5 = r1
            r1 = 0
            r2 = 0
        L_0x005b:
            int r6 = r0.fixup_count
            r8 = 6
            r12 = 3
            if (r1 >= r6) goto L_0x010e
            int[] r6 = r0.fixup_offsets
            r6 = r6[r1]
            r7 = r6 & 15
            gnu.bytecode.Label[] r11 = r0.fixup_labels
            r11 = r11[r1]
            if (r11 == 0) goto L_0x0089
            int r14 = r11.position
            if (r14 < 0) goto L_0x0072
            goto L_0x0089
        L_0x0072:
            java.lang.Error r1 = new java.lang.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "undefined label "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0089:
            r14 = 7
            if (r11 == 0) goto L_0x00b7
            if (r7 < r9) goto L_0x00b7
            if (r7 > r14) goto L_0x00b7
            int r4 = r11.first_fixup
            int r4 = r4 + r15
            int r10 = r0.fixup_count
            if (r4 >= r10) goto L_0x00b7
            int[] r4 = r0.fixup_offsets
            int r10 = r11.first_fixup
            int r10 = r10 + r15
            r4 = r4[r10]
            int[] r10 = r0.fixup_offsets
            int r3 = r11.first_fixup
            r3 = r10[r3]
            r3 = r3 & 15
            r3 = r3 | r9
            if (r4 != r3) goto L_0x00b7
            gnu.bytecode.Label[] r3 = r0.fixup_labels
            int r4 = r11.first_fixup
            int r4 = r4 + r15
            r11 = r3[r4]
            gnu.bytecode.Label[] r3 = r0.fixup_labels
            r3[r1] = r11
            r3 = 0
            r4 = 0
            goto L_0x0089
        L_0x00b7:
            int r3 = r6 >> 4
            switch(r7) {
                case 0: goto L_0x0109;
                case 1: goto L_0x0106;
                case 2: goto L_0x00ff;
                case 3: goto L_0x0109;
                case 4: goto L_0x00e1;
                case 5: goto L_0x00e1;
                case 6: goto L_0x00e1;
                case 7: goto L_0x00bc;
                case 8: goto L_0x00dc;
                case 9: goto L_0x00cf;
                case 10: goto L_0x00bc;
                case 11: goto L_0x00c5;
                case 12: goto L_0x00bc;
                case 13: goto L_0x00bc;
                case 14: goto L_0x00c2;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            java.lang.Error r1 = new java.lang.Error
            r1.<init>(r13)
            throw r1
        L_0x00c2:
            int r1 = r1 + 1
            goto L_0x0109
        L_0x00c5:
            int r1 = r1 + 2
            gnu.bytecode.Label[] r4 = r0.fixup_labels
            r4 = r4[r1]
            int r3 = r3 + r2
            r4.position = r3
            goto L_0x0109
        L_0x00cf:
            if (r11 != 0) goto L_0x00d2
            goto L_0x010e
        L_0x00d2:
            int r1 = r11.first_fixup
            int r4 = r0.fixupOffset(r1)
            int r3 = r3 + r2
            int r2 = r3 - r4
            goto L_0x010a
        L_0x00dc:
            int r2 = r2 + -3
            int r5 = r5 + -3
            goto L_0x0109
        L_0x00e1:
            int r4 = r11.position
            int r6 = r3 + r2
            int r4 = r4 - r6
            short r6 = (short) r4
            if (r6 != r4) goto L_0x00f1
            int[] r4 = r0.fixup_offsets
            int r3 = r3 << 4
            r3 = r3 | r14
            r4[r1] = r3
            goto L_0x0109
        L_0x00f1:
            r3 = 5
            if (r7 != r8) goto L_0x00f6
            r4 = 5
            goto L_0x00f7
        L_0x00f6:
            r4 = 2
        L_0x00f7:
            int r2 = r2 + r4
            if (r7 != r8) goto L_0x00fc
            r10 = 5
            goto L_0x00fd
        L_0x00fc:
            r10 = 2
        L_0x00fd:
            int r5 = r5 + r10
            goto L_0x0109
        L_0x00ff:
            int r3 = r3 + r2
            int r3 = 3 - r3
            r3 = r3 & r12
            int r2 = r2 + r3
            int r5 = r5 + r3
            goto L_0x0109
        L_0x0106:
            int r3 = r3 + r2
            r11.position = r3
        L_0x0109:
            int r1 = r1 + r15
        L_0x010a:
            r3 = 0
            r4 = 0
            goto L_0x005b
        L_0x010e:
            byte[] r3 = new byte[r5]
            r1 = 0
            int r2 = r0.fixupOffset(r1)
            r4 = -1
            r7 = r2
            r1 = 0
            r2 = 0
            r6 = 0
            r9 = 0
            r10 = -1
        L_0x011c:
            if (r1 >= r7) goto L_0x012c
            int r11 = r6 + 1
            byte[] r14 = r0.code
            int r17 = r1 + 1
            byte r1 = r14[r1]
            r3[r6] = r1
            r6 = r11
            r1 = r17
            goto L_0x011c
        L_0x012c:
            int[] r7 = r0.fixup_offsets
            r7 = r7[r2]
            r7 = r7 & 15
            gnu.bytecode.Label[] r11 = r0.fixup_labels
            r11 = r11[r2]
            if (r9 == 0) goto L_0x0142
            int r14 = r9.position
            if (r14 >= r6) goto L_0x0142
            gnu.bytecode.StackMapTableAttr r14 = r0.stackMap
            r14.emitStackMapEntry(r9, r0)
            r9 = 0
        L_0x0142:
            if (r9 == 0) goto L_0x0151
            int r14 = r9.position
            if (r14 > r6) goto L_0x0149
            goto L_0x0151
        L_0x0149:
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r2 = "labels out of order"
            r1.<init>(r2)
            throw r1
        L_0x0151:
            if (r7 == 0) goto L_0x02d1
            if (r7 == r15) goto L_0x02bc
            r14 = 2
            if (r7 == r14) goto L_0x025a
            r14 = 11
            if (r7 == r14) goto L_0x0234
            r14 = 14
            if (r7 == r14) goto L_0x021a
            switch(r7) {
                case 4: goto L_0x01d1;
                case 5: goto L_0x01d1;
                case 6: goto L_0x01d1;
                case 7: goto L_0x01b1;
                case 8: goto L_0x01ae;
                case 9: goto L_0x0169;
                default: goto L_0x0163;
            }
        L_0x0163:
            java.lang.Error r1 = new java.lang.Error
            r1.<init>(r13)
            throw r1
        L_0x0169:
            if (r11 != 0) goto L_0x0199
            if (r5 != r6) goto L_0x017a
            r0.PC = r5
            r0.code = r3
            r1 = 0
            r0.fixup_count = r1
            r1 = 0
            r0.fixup_labels = r1
            r0.fixup_offsets = r1
            return
        L_0x017a:
            java.lang.Error r1 = new java.lang.Error
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "PC confusion new_pc:"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r3 = " new_size:"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0199:
            int r2 = r11.first_fixup
            int r7 = r0.fixupOffset(r2)
            int r1 = r11.position
            if (r1 != r6) goto L_0x01a6
            r1 = r7
            goto L_0x011c
        L_0x01a6:
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r2 = "bad pc"
            r1.<init>(r2)
            throw r1
        L_0x01ae:
            int r1 = r1 + 3
            goto L_0x01cd
        L_0x01b1:
            int r7 = r11.position
            int r7 = r7 - r6
            int r11 = r6 + 1
            byte[] r14 = r0.code
            byte r14 = r14[r1]
            r3[r6] = r14
            int r6 = r11 + 1
            int r14 = r7 >> 8
            byte r14 = (byte) r14
            r3[r11] = r14
            int r11 = r6 + 1
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r3[r6] = r7
            int r1 = r1 + 3
            r6 = r11
        L_0x01cd:
            r7 = 1
            r15 = 0
            goto L_0x02d3
        L_0x01d1:
            int r11 = r11.position
            int r11 = r11 - r6
            byte[] r14 = r0.code
            byte r14 = r14[r1]
            if (r7 != r8) goto L_0x01f3
            byte r7 = r0.invert_opcode(r14)
            int r14 = r6 + 1
            r3[r6] = r7
            int r6 = r14 + 1
            r7 = 0
            r3[r14] = r7
            int r7 = r6 + 1
            r14 = 8
            r3[r6] = r14
            r6 = -56
            r6 = r7
            r7 = -56
            goto L_0x01f6
        L_0x01f3:
            int r14 = r14 + 33
            byte r7 = (byte) r14
        L_0x01f6:
            int r14 = r6 + 1
            r3[r6] = r7
            int r6 = r14 + 1
            int r7 = r11 >> 24
            byte r7 = (byte) r7
            r3[r14] = r7
            int r7 = r6 + 1
            int r14 = r11 >> 16
            byte r14 = (byte) r14
            r3[r6] = r14
            int r6 = r7 + 1
            int r14 = r11 >> 8
            byte r14 = (byte) r14
            r3[r7] = r14
            int r7 = r6 + 1
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r11 = (byte) r11
            r3[r6] = r11
            int r1 = r1 + 3
            r6 = r7
            goto L_0x01cd
        L_0x021a:
            gnu.bytecode.LineNumbersAttr r7 = r0.lines
            if (r7 != 0) goto L_0x0225
            gnu.bytecode.LineNumbersAttr r7 = new gnu.bytecode.LineNumbersAttr
            r7.<init>(r0)
            r0.lines = r7
        L_0x0225:
            int r2 = r2 + 1
            int r7 = r0.fixupOffset(r2)
            if (r7 == r10) goto L_0x0232
            gnu.bytecode.LineNumbersAttr r10 = r0.lines
            r10.put(r7, r6)
        L_0x0232:
            r10 = r7
            goto L_0x01cd
        L_0x0234:
            gnu.bytecode.Label[] r7 = r0.fixup_labels
            int r11 = r2 + 2
            r7 = r7[r11]
            int r14 = r2 + 1
            int r8 = r0.fixupOffset(r14)
            gnu.bytecode.StackMapTableAttr r15 = r0.stackMap
            if (r15 == 0) goto L_0x0248
            gnu.bytecode.Label r9 = r0.mergeLabels(r9, r7)
        L_0x0248:
            gnu.bytecode.Label[] r7 = r0.fixup_labels
            r2 = r7[r2]
            int r2 = r2.position
            gnu.bytecode.Label[] r7 = r0.fixup_labels
            r7 = r7[r14]
            int r7 = r7.position
            r0.addHandler(r2, r7, r6, r8)
            r2 = r11
            goto L_0x01cd
        L_0x025a:
            int r7 = 3 - r6
            r7 = r7 & r12
            int r8 = r6 + 1
            byte[] r11 = r0.code
            int r14 = r1 + 1
            byte r1 = r11[r1]
            r3[r6] = r1
        L_0x0267:
            int r7 = r7 + r4
            if (r7 < 0) goto L_0x0271
            int r1 = r8 + 1
            r15 = 0
            r3[r8] = r15
            r8 = r1
            goto L_0x0267
        L_0x0271:
            r15 = 0
        L_0x0272:
            int r1 = r0.fixup_count
            if (r2 >= r1) goto L_0x02b9
            int r1 = r2 + 1
            int r7 = r0.fixupKind(r1)
            if (r7 != r12) goto L_0x02b9
            int r2 = r0.fixupOffset(r1)
        L_0x0282:
            if (r14 >= r2) goto L_0x0292
            int r7 = r8 + 1
            byte[] r11 = r0.code
            int r16 = r14 + 1
            byte r11 = r11[r14]
            r3[r8] = r11
            r8 = r7
            r14 = r16
            goto L_0x0282
        L_0x0292:
            gnu.bytecode.Label[] r2 = r0.fixup_labels
            r2 = r2[r1]
            int r2 = r2.position
            int r2 = r2 - r6
            int r7 = r8 + 1
            int r11 = r2 >> 24
            byte r11 = (byte) r11
            r3[r8] = r11
            int r8 = r7 + 1
            int r11 = r2 >> 16
            byte r11 = (byte) r11
            r3[r7] = r11
            int r7 = r8 + 1
            int r11 = r2 >> 8
            byte r11 = (byte) r11
            r3[r8] = r11
            int r8 = r7 + 1
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r3[r7] = r2
            int r14 = r14 + 4
            r2 = r1
            goto L_0x0272
        L_0x02b9:
            r6 = r8
            r1 = r14
            goto L_0x02d2
        L_0x02bc:
            r15 = 0
            gnu.bytecode.StackMapTableAttr r7 = r0.stackMap
            if (r7 == 0) goto L_0x02d2
            if (r11 == 0) goto L_0x02d2
            gnu.bytecode.Type[] r7 = r11.stackTypes
            if (r7 == 0) goto L_0x02d2
            boolean r7 = r11.needsStackMapEntry
            if (r7 == 0) goto L_0x02d2
            gnu.bytecode.Label r7 = r0.mergeLabels(r9, r11)
            r9 = r7
            goto L_0x02d2
        L_0x02d1:
            r15 = 0
        L_0x02d2:
            r7 = 1
        L_0x02d3:
            int r2 = r2 + r7
            int r7 = r0.fixupOffset(r2)
            r8 = 6
            r15 = 1
            goto L_0x011c
        L_0x02dc:
            r15 = 0
            int r5 = r11.first_fixup
            int r3 = r0.fixupOffset(r5)
            int r7 = r7 + r6
            int r6 = r7 - r3
            r3 = 0
            r4 = 0
            goto L_0x0010
        L_0x02ea:
            r15 = 0
            int r3 = r0.PC
            if (r3 < r12) goto L_0x02f4
            int r6 = r6 + 5
            r3 = 1
            r4 = 0
            goto L_0x032d
        L_0x02f4:
            r4 = 0
            goto L_0x032c
        L_0x02f6:
            r15 = 0
            goto L_0x0319
        L_0x02f8:
            r15 = 0
            int r3 = r11.first_fixup
            int r4 = r5 + 1
            if (r3 != r4) goto L_0x0318
            int r3 = r0.fixupOffset(r4)
            int r4 = r7 + 3
            if (r3 != r4) goto L_0x0318
            int[] r3 = r0.fixup_offsets
            int r4 = r7 << 4
            r7 = 8
            r4 = r4 | r7
            r3[r5] = r4
            gnu.bytecode.Label[] r3 = r0.fixup_labels
            r4 = 0
            r3[r5] = r4
            int r6 = r6 + -3
            goto L_0x032c
        L_0x0318:
            r4 = 0
        L_0x0319:
            int r3 = r0.PC
            if (r3 < r12) goto L_0x032c
            int r6 = r6 + 2
            goto L_0x032c
        L_0x0320:
            r15 = 0
            int r6 = r6 + 3
            goto L_0x032c
        L_0x0324:
            r15 = 0
            int r3 = r11.position
            int r3 = r3 + r6
            r11.position = r3
            goto L_0x032c
        L_0x032b:
            r15 = 0
        L_0x032c:
            r3 = 1
        L_0x032d:
            int r5 = r5 + r3
            r3 = 0
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.processFixups():void");
    }

    private Label mergeLabels(Label label, Label label2) {
        if (label != null) {
            label2.setTypes(label);
        }
        return label2;
    }

    public void assignConstants(ClassType classType) {
        LocalVarsAttr localVarsAttr = this.locals;
        if (localVarsAttr != null && localVarsAttr.container == null && !this.locals.isEmpty()) {
            this.locals.addToFrontOf(this);
        }
        processFixups();
        StackMapTableAttr stackMapTableAttr = this.stackMap;
        if (stackMapTableAttr != null && stackMapTableAttr.numEntries > 0) {
            this.stackMap.addToFrontOf(this);
        }
        if (instructionLineMode) {
            if (this.lines == null) {
                this.lines = new LineNumbersAttr(this);
            }
            this.lines.linenumber_count = 0;
            int codeLength = getCodeLength();
            for (int i = 0; i < codeLength; i++) {
                this.lines.put(i, i);
            }
        }
        super.assignConstants(classType);
        Attribute.assignConstants(this, classType);
    }

    public final int getLength() {
        return getCodeLength() + 12 + (this.exception_table_length * 8) + Attribute.getLengthAll(this);
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.max_stack);
        dataOutputStream.writeShort(this.max_locals);
        dataOutputStream.writeInt(this.PC);
        int i = 0;
        dataOutputStream.write(this.code, 0, this.PC);
        dataOutputStream.writeShort(this.exception_table_length);
        int i2 = this.exception_table_length;
        while (true) {
            i2--;
            if (i2 >= 0) {
                dataOutputStream.writeShort(this.exception_table[i]);
                dataOutputStream.writeShort(this.exception_table[i + 1]);
                dataOutputStream.writeShort(this.exception_table[i + 2]);
                dataOutputStream.writeShort(this.exception_table[i + 3]);
                i += 4;
            } else {
                Attribute.writeAll(this, dataOutputStream);
                return;
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", max_stack:");
        classTypeWriter.print(this.max_stack);
        classTypeWriter.print(", max_locals:");
        classTypeWriter.print(this.max_locals);
        classTypeWriter.print(", code_length:");
        int codeLength = getCodeLength();
        classTypeWriter.println(codeLength);
        int i = 0;
        disAssemble(classTypeWriter, 0, codeLength);
        if (this.exception_table_length > 0) {
            classTypeWriter.print("Exceptions (count: ");
            classTypeWriter.print(this.exception_table_length);
            classTypeWriter.println("):");
            int i2 = this.exception_table_length;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                classTypeWriter.print("  start: ");
                classTypeWriter.print(this.exception_table[i] & 65535);
                classTypeWriter.print(", end: ");
                classTypeWriter.print(this.exception_table[i + 1] & 65535);
                classTypeWriter.print(", handler: ");
                classTypeWriter.print(this.exception_table[i + 2] & 65535);
                classTypeWriter.print(", type: ");
                short s = this.exception_table[i + 3] & 65535;
                if (s == 0) {
                    classTypeWriter.print("0 /* finally */");
                } else {
                    classTypeWriter.printOptionalIndex((int) s);
                    classTypeWriter.printConstantTersely((int) s, 7);
                }
                classTypeWriter.println();
                i += 4;
            }
        }
        classTypeWriter.printAttributes(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v82, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v92, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v93, resolved type: byte} */
    /* JADX WARNING: type inference failed for: r3v56, types: [int] */
    /* JADX WARNING: type inference failed for: r5v8, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x03b4  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x03ca  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disAssemble(gnu.bytecode.ClassTypeWriter r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            r3 = r19
            r4 = r20
            r5 = 0
        L_0x000a:
            if (r3 >= r4) goto L_0x03d0
            int r6 = r3 + 1
            byte[] r7 = r0.code
            byte r7 = r7[r3]
            r7 = r7 & 255(0xff, float:3.57E-43)
            java.lang.String r8 = java.lang.Integer.toString(r3)
            int r9 = r8.length()
        L_0x001c:
            r10 = 1
            int r9 = r9 + r10
            r11 = 3
            r12 = 32
            if (r9 > r11) goto L_0x0027
            r1.print(r12)
            goto L_0x001c
        L_0x0027:
            r1.print(r8)
            java.lang.String r8 = ": "
            r1.print(r8)
            r9 = 108(0x6c, float:1.51E-43)
            java.lang.String r13 = "ilfda"
            r14 = 120(0x78, float:1.68E-43)
            r15 = -1
            r16 = 2
            if (r7 >= r14) goto L_0x012e
            r3 = 87
            if (r7 >= r3) goto L_0x010d
            if (r7 >= r11) goto L_0x0047
            java.lang.String r3 = "nop;aconst_null;iconst_m1;"
            r0.print(r3, r7, r1)
            goto L_0x03b1
        L_0x0047:
            r3 = 9
            if (r7 >= r3) goto L_0x0057
            java.lang.String r3 = "iconst_"
            r1.print(r3)
            int r7 = r7 + -3
            r1.print(r7)
            goto L_0x03b1
        L_0x0057:
            r3 = 16
            if (r7 >= r3) goto L_0x007c
            r3 = 11
            if (r7 >= r3) goto L_0x0062
            int r7 = r7 + -9
            goto L_0x006f
        L_0x0062:
            r3 = 14
            if (r7 >= r3) goto L_0x006b
            r9 = 102(0x66, float:1.43E-43)
            int r7 = r7 + -11
            goto L_0x006f
        L_0x006b:
            r9 = 100
            int r7 = r7 + -14
        L_0x006f:
            r1.print(r9)
            java.lang.String r3 = "const_"
            r1.print(r3)
            r1.print(r7)
            goto L_0x03b1
        L_0x007c:
            r8 = 21
            if (r7 >= r8) goto L_0x00b0
            r8 = 18
            if (r7 >= r8) goto L_0x00a1
            int r8 = r7 + -16
            java.lang.String r9 = "bipush ;sipush ;"
            r0.print(r9, r8, r1)
            if (r7 != r3) goto L_0x0095
            byte[] r3 = r0.code
            int r7 = r6 + 1
            byte r3 = r3[r6]
            r6 = r7
            goto L_0x009c
        L_0x0095:
            int r3 = r0.readUnsignedShort(r6)
            short r3 = (short) r3
            int r6 = r6 + 2
        L_0x009c:
            r1.print(r3)
            goto L_0x03b1
        L_0x00a1:
            if (r7 != r8) goto L_0x00a5
            r16 = 1
        L_0x00a5:
            int r7 = r7 + -18
            java.lang.String r3 = "ldc;ldc_w;ldc2_w;"
            r0.print(r3, r7, r1)
            r3 = r16
            goto L_0x03b2
        L_0x00b0:
            r3 = 54
            if (r7 >= r3) goto L_0x00b7
            java.lang.String r3 = "load"
            goto L_0x00bb
        L_0x00b7:
            int r7 = r7 + -33
            java.lang.String r3 = "store"
        L_0x00bb:
            r8 = 26
            if (r7 >= r8) goto L_0x00c3
            int r7 = r7 + -21
            r8 = -1
            goto L_0x00d1
        L_0x00c3:
            r8 = 46
            if (r7 >= r8) goto L_0x00ce
            int r7 = r7 + -26
            int r8 = r7 % 4
            int r7 = r7 >> 2
            goto L_0x00d1
        L_0x00ce:
            r8 = -2
            int r7 = r7 + -46
        L_0x00d1:
            java.lang.String r9 = "ilfdabcs"
            char r7 = r9.charAt(r7)
            r1.print(r7)
            r7 = -2
            if (r8 != r7) goto L_0x00e2
            r7 = 97
            r1.write(r7)
        L_0x00e2:
            r1.print(r3)
            if (r8 < 0) goto L_0x00f1
            r3 = 95
            r1.write(r3)
            r1.print(r8)
            goto L_0x03b1
        L_0x00f1:
            if (r8 != r15) goto L_0x03b1
            if (r5 == 0) goto L_0x00fc
            int r3 = r0.readUnsignedShort(r6)
            int r6 = r6 + 2
            goto L_0x0104
        L_0x00fc:
            byte[] r3 = r0.code
            byte r3 = r3[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r6 = r6 + 1
        L_0x0104:
            r1.print(r12)
            r1.print(r3)
            r5 = 0
            goto L_0x03b1
        L_0x010d:
            r3 = 96
            if (r7 >= r3) goto L_0x011a
            int r7 = r7 + -87
            java.lang.String r3 = "pop;pop2;dup;dup_x1;dup_x2;dup2;dup2_x1;dup2_x2;swap;"
            r0.print(r3, r7, r1)
            goto L_0x03b1
        L_0x011a:
            int r7 = r7 + -96
            int r3 = r7 % 4
            char r3 = r13.charAt(r3)
            r1.print(r3)
            int r3 = r7 >> 2
            java.lang.String r7 = "add;sub;mul;div;rem;neg;"
            r0.print(r7, r3, r1)
            goto L_0x03b1
        L_0x012e:
            r11 = 170(0xaa, float:2.38E-43)
            if (r7 >= r11) goto L_0x0214
            r8 = 132(0x84, float:1.85E-43)
            if (r7 >= r8) goto L_0x014a
            r3 = r7 & 1
            if (r3 != 0) goto L_0x013c
            r9 = 105(0x69, float:1.47E-43)
        L_0x013c:
            r1.print(r9)
            int r7 = r7 + -120
            int r3 = r7 >> 1
            java.lang.String r7 = "shl;shr;ushr;and;or;xor;"
            r0.print(r7, r3, r1)
            goto L_0x03b1
        L_0x014a:
            if (r7 != r8) goto L_0x0180
            java.lang.String r3 = "iinc"
            r1.print(r3)
            if (r5 != 0) goto L_0x0160
            byte[] r3 = r0.code
            int r7 = r6 + 1
            byte r6 = r3[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r8 = r7 + 1
            byte r3 = r3[r7]
            goto L_0x0171
        L_0x0160:
            int r3 = r0.readUnsignedShort(r6)
            int r6 = r6 + 2
            int r5 = r0.readUnsignedShort(r6)
            short r5 = (short) r5
            int r6 = r6 + 2
            r8 = r6
            r6 = r3
            r3 = r5
            r5 = 0
        L_0x0171:
            r1.print(r12)
            r1.print(r6)
            r1.print(r12)
            r1.print(r3)
            r6 = r8
            goto L_0x03b1
        L_0x0180:
            r8 = 148(0x94, float:2.07E-43)
            if (r7 >= r8) goto L_0x01a1
            int r7 = r7 + -133
            int r3 = r7 / 3
            java.lang.String r8 = "ilfdi"
            char r3 = r8.charAt(r3)
            r1.print(r3)
            r3 = 50
            r1.print(r3)
            java.lang.String r3 = "lfdifdildilfbcs"
            char r3 = r3.charAt(r7)
            r1.print(r3)
            goto L_0x03b1
        L_0x01a1:
            r8 = 153(0x99, float:2.14E-43)
            if (r7 >= r8) goto L_0x01ae
            int r7 = r7 + -148
            java.lang.String r3 = "lcmp;fcmpl;fcmpg;dcmpl;dcmpg;"
            r0.print(r3, r7, r1)
            goto L_0x03b1
        L_0x01ae:
            r8 = 169(0xa9, float:2.37E-43)
            if (r7 >= r8) goto L_0x01f7
            r8 = 159(0x9f, float:2.23E-43)
            if (r7 >= r8) goto L_0x01c3
            java.lang.String r8 = "if"
            r1.print(r8)
            int r7 = r7 + -153
            java.lang.String r8 = "eq;ne;lt;ge;gt;le;"
            r0.print(r8, r7, r1)
            goto L_0x01e7
        L_0x01c3:
            r8 = 167(0xa7, float:2.34E-43)
            if (r7 >= r8) goto L_0x01e0
            r8 = 165(0xa5, float:2.31E-43)
            if (r7 >= r8) goto L_0x01d1
            java.lang.String r8 = "if_icmp"
            r1.print(r8)
            goto L_0x01d8
        L_0x01d1:
            java.lang.String r8 = "if_acmp"
            r1.print(r8)
            int r7 = r7 + -6
        L_0x01d8:
            int r7 = r7 + -159
            java.lang.String r8 = "eq;ne;lt;ge;gt;le;"
            r0.print(r8, r7, r1)
            goto L_0x01e7
        L_0x01e0:
            int r7 = r7 + -167
            java.lang.String r8 = "goto;jsr;"
            r0.print(r8, r7, r1)
        L_0x01e7:
            int r7 = r0.readUnsignedShort(r6)
            short r7 = (short) r7
            int r6 = r6 + 2
            r1.print(r12)
            int r3 = r3 + r7
            r1.print(r3)
            goto L_0x03b1
        L_0x01f7:
            java.lang.String r3 = "ret "
            r1.print(r3)
            if (r5 == 0) goto L_0x0205
            int r3 = r0.readUnsignedShort(r6)
            int r3 = r3 + 2
            goto L_0x020d
        L_0x0205:
            byte[] r3 = r0.code
            byte r3 = r3[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r6 = r6 + 1
        L_0x020d:
            r1.print(r3)
            r3 = 0
            r5 = 0
            goto L_0x03b2
        L_0x0214:
            r9 = 172(0xac, float:2.41E-43)
            r14 = 4
            if (r7 >= r9) goto L_0x02ab
            int r9 = r0.fixup_count
            if (r9 > 0) goto L_0x0221
            int r6 = r6 + 3
            r6 = r6 & -4
        L_0x0221:
            int r9 = r0.readInt(r6)
            int r6 = r6 + r14
            if (r7 != r11) goto L_0x026e
            java.lang.String r7 = "tableswitch"
            r1.print(r7)
            int r7 = r0.readInt(r6)
            int r6 = r6 + 4
            int r11 = r0.readInt(r6)
            int r6 = r6 + r14
            java.lang.String r12 = " low: "
            r1.print(r12)
            r1.print(r7)
            java.lang.String r12 = " high: "
            r1.print(r12)
            r1.print(r11)
            java.lang.String r12 = " default: "
            r1.print(r12)
            int r9 = r9 + r3
            r1.print(r9)
        L_0x0251:
            if (r7 > r11) goto L_0x03b1
            int r9 = r0.readInt(r6)
            int r6 = r6 + 4
            r18.println()
            java.lang.String r12 = "  "
            r1.print(r12)
            r1.print(r7)
            r1.print(r8)
            int r9 = r9 + r3
            r1.print(r9)
            int r7 = r7 + 1
            goto L_0x0251
        L_0x026e:
            java.lang.String r7 = "lookupswitch"
            r1.print(r7)
            int r7 = r0.readInt(r6)
            int r6 = r6 + 4
            java.lang.String r11 = " npairs: "
            r1.print(r11)
            r1.print(r7)
            java.lang.String r11 = " default: "
            r1.print(r11)
            int r9 = r9 + r3
            r1.print(r9)
        L_0x028a:
            int r7 = r7 + r15
            if (r7 < 0) goto L_0x03b1
            int r9 = r0.readInt(r6)
            int r6 = r6 + 4
            int r11 = r0.readInt(r6)
            int r6 = r6 + r14
            r18.println()
            java.lang.String r12 = "  "
            r1.print(r12)
            r1.print(r9)
            r1.print(r8)
            int r11 = r11 + r3
            r1.print(r11)
            goto L_0x028a
        L_0x02ab:
            r8 = 178(0xb2, float:2.5E-43)
            if (r7 >= r8) goto L_0x02c3
            r3 = 177(0xb1, float:2.48E-43)
            if (r7 >= r3) goto L_0x02bc
            int r7 = r7 + -172
            char r3 = r13.charAt(r7)
            r1.print(r3)
        L_0x02bc:
            java.lang.String r3 = "return"
            r1.print(r3)
            goto L_0x03b1
        L_0x02c3:
            r8 = 182(0xb6, float:2.55E-43)
            if (r7 >= r8) goto L_0x02d1
            int r7 = r7 + -178
            java.lang.String r3 = "getstatic;putstatic;getfield;putfield;"
            r0.print(r3, r7, r1)
        L_0x02ce:
            r3 = 2
            goto L_0x03b2
        L_0x02d1:
            r8 = 185(0xb9, float:2.59E-43)
            if (r7 >= r8) goto L_0x02e2
            java.lang.String r3 = "invoke"
            r1.print(r3)
            int r7 = r7 + -182
            java.lang.String r3 = "virtual;special;static;"
            r0.print(r3, r7, r1)
            goto L_0x02ce
        L_0x02e2:
            r8 = 185(0xb9, float:2.59E-43)
            if (r7 != r8) goto L_0x0312
            java.lang.String r3 = "invokeinterface ("
            r1.print(r3)
            int r3 = r0.readUnsignedShort(r6)
            int r6 = r6 + 2
            byte[] r7 = r0.code
            byte r7 = r7[r6]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r6 = r6 + 2
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r7)
            java.lang.String r7 = " args)"
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r1.print(r7)
            r1.printConstantOperand(r3)
            goto L_0x03b1
        L_0x0312:
            r8 = 196(0xc4, float:2.75E-43)
            if (r7 >= r8) goto L_0x034d
            int r3 = r7 + -186
            java.lang.String r8 = "186;new;newarray;anewarray;arraylength;athrow;checkcast;instanceof;monitorenter;monitorexit;"
            r0.print(r8, r3, r1)
            r3 = 187(0xbb, float:2.62E-43)
            if (r7 == r3) goto L_0x02ce
            r3 = 189(0xbd, float:2.65E-43)
            if (r7 == r3) goto L_0x02ce
            r3 = 192(0xc0, float:2.69E-43)
            if (r7 == r3) goto L_0x02ce
            r3 = 193(0xc1, float:2.7E-43)
            if (r7 != r3) goto L_0x032e
            goto L_0x02ce
        L_0x032e:
            r3 = 188(0xbc, float:2.63E-43)
            if (r7 != r3) goto L_0x03b1
            byte[] r3 = r0.code
            int r7 = r6 + 1
            byte r3 = r3[r6]
            r1.print(r12)
            if (r3 < r14) goto L_0x0349
            r6 = 11
            if (r3 > r6) goto L_0x0349
            int r3 = r3 + -4
            java.lang.String r6 = "boolean;char;float;double;byte;short;int;long;"
            r0.print(r6, r3, r1)
            goto L_0x0379
        L_0x0349:
            r1.print(r3)
            goto L_0x0379
        L_0x034d:
            r8 = 196(0xc4, float:2.75E-43)
            if (r7 != r8) goto L_0x0359
            java.lang.String r3 = "wide"
            r1.print(r3)
            r3 = 0
            r5 = 1
            goto L_0x03b2
        L_0x0359:
            r8 = 197(0xc5, float:2.76E-43)
            if (r7 != r8) goto L_0x037b
            java.lang.String r3 = "multianewarray"
            r1.print(r3)
            int r3 = r0.readUnsignedShort(r6)
            int r6 = r6 + 2
            r1.printConstantOperand(r3)
            byte[] r3 = r0.code
            int r7 = r6 + 1
            byte r3 = r3[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r1.print(r12)
            r1.print(r3)
        L_0x0379:
            r6 = r7
            goto L_0x03b1
        L_0x037b:
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 >= r8) goto L_0x0395
            int r7 = r7 + -198
            java.lang.String r8 = "ifnull;ifnonnull;"
            r0.print(r8, r7, r1)
            int r7 = r0.readUnsignedShort(r6)
            short r7 = (short) r7
            int r6 = r6 + 2
            r1.print(r12)
            int r3 = r3 + r7
            r1.print(r3)
            goto L_0x03b1
        L_0x0395:
            r8 = 202(0xca, float:2.83E-43)
            if (r7 >= r8) goto L_0x03ae
            int r7 = r7 + -200
            java.lang.String r8 = "goto_w;jsr_w;"
            r0.print(r8, r7, r1)
            int r7 = r0.readInt(r6)
            int r6 = r6 + 4
            r1.print(r12)
            int r3 = r3 + r7
            r1.print(r3)
            goto L_0x03b1
        L_0x03ae:
            r1.print(r7)
        L_0x03b1:
            r3 = 0
        L_0x03b2:
            if (r3 <= 0) goto L_0x03ca
            if (r3 != r10) goto L_0x03bf
            byte[] r3 = r0.code
            int r7 = r6 + 1
            byte r3 = r3[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            goto L_0x03c5
        L_0x03bf:
            int r3 = r0.readUnsignedShort(r6)
            int r7 = r6 + 2
        L_0x03c5:
            r1.printConstantOperand(r3)
            r3 = r7
            goto L_0x03cb
        L_0x03ca:
            r3 = r6
        L_0x03cb:
            r18.println()
            goto L_0x000a
        L_0x03d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.CodeAttr.disAssemble(gnu.bytecode.ClassTypeWriter, int, int):void");
    }

    private int readUnsignedShort(int i) {
        byte[] bArr = this.code;
        return (bArr[i + 1] & Ev3Constants.Opcode.TST) | ((bArr[i] & Ev3Constants.Opcode.TST) << 8);
    }

    private int readInt(int i) {
        return readUnsignedShort(i + 2) | (readUnsignedShort(i) << 16);
    }

    private void print(String str, int i, PrintWriter printWriter) {
        int i2 = -1;
        int i3 = 0;
        while (i >= 0) {
            i3 = i2 + 1;
            i2 = str.indexOf(59, i3);
            i--;
        }
        printWriter.write(str, i3, i2 - i3);
    }

    public int beginFragment(Label label) {
        return beginFragment(new Label(), label);
    }

    public int beginFragment(Label label, Label label2) {
        int i = this.fixup_count;
        fixupAdd(10, label2);
        label.define(this);
        return i;
    }

    public void endFragment(int i) {
        this.fixup_offsets[i] = (this.fixup_count << 4) | 10;
        Label label = this.fixup_labels[i];
        fixupAdd(9, 0, (Label) null);
        label.define(this);
    }
}
