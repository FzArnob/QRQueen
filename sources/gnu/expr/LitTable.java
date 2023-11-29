package gnu.expr;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.zxing.client.android.Intents;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Symbol;
import gnu.mapping.Table2D;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

public class LitTable implements ObjectOutput {
    static Table2D staticTable = new Table2D(100);
    Compilation comp;
    IdentityHashMap literalTable = new IdentityHashMap(100);
    Literal literalsChain;
    int literalsCount;
    ClassType mainClass;
    int stackPointer;
    Type[] typeStack = new Type[20];
    Object[] valueStack = new Object[20];

    public void close() {
    }

    public void flush() {
    }

    public LitTable(Compilation compilation) {
        this.comp = compilation;
        this.mainClass = compilation.mainClass;
    }

    public void emit() throws IOException {
        for (Literal literal = this.literalsChain; literal != null; literal = literal.next) {
            writeObject(literal.value);
        }
        for (Literal literal2 = this.literalsChain; literal2 != null; literal2 = literal2.next) {
            emit(literal2, true);
        }
        this.literalTable = null;
        this.literalsCount = 0;
    }

    /* access modifiers changed from: package-private */
    public void push(Object obj, Type type) {
        int i = this.stackPointer;
        Object[] objArr = this.valueStack;
        if (i >= objArr.length) {
            Object[] objArr2 = new Object[(objArr.length * 2)];
            Type[] typeArr = new Type[(this.typeStack.length * 2)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.typeStack, 0, typeArr, 0, this.stackPointer);
            this.valueStack = objArr2;
            this.typeStack = typeArr;
        }
        Object[] objArr3 = this.valueStack;
        int i2 = this.stackPointer;
        objArr3[i2] = obj;
        this.typeStack[i2] = type;
        this.stackPointer = i2 + 1;
    }

    /* access modifiers changed from: package-private */
    public void error(String str) {
        throw new Error(str);
    }

    public void write(int i) throws IOException {
        error("cannot handle call to write(int) when externalizing literal");
    }

    public void writeBytes(String str) throws IOException {
        error("cannot handle call to writeBytes(String) when externalizing literal");
    }

    public void write(byte[] bArr) throws IOException {
        error("cannot handle call to write(byte[]) when externalizing literal");
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        error("cannot handle call to write(byte[],int,int) when externalizing literal");
    }

    public void writeBoolean(boolean z) {
        push(new Boolean(z), Type.booleanType);
    }

    public void writeChar(int i) {
        push(new Character((char) i), Type.charType);
    }

    public void writeByte(int i) {
        push(new Byte((byte) i), Type.byteType);
    }

    public void writeShort(int i) {
        push(new Short((short) i), Type.shortType);
    }

    public void writeInt(int i) {
        push(new Integer(i), Type.intType);
    }

    public void writeLong(long j) {
        push(new Long(j), Type.longType);
    }

    public void writeFloat(float f) {
        push(new Float(f), Type.floatType);
    }

    public void writeDouble(double d) {
        push(new Double(d), Type.doubleType);
    }

    public void writeUTF(String str) {
        push(str, Type.string_type);
    }

    public void writeChars(String str) {
        push(str, Type.string_type);
    }

    public void writeObject(Object obj) throws IOException {
        Literal findLiteral = findLiteral(obj);
        if ((findLiteral.flags & 3) != 0) {
            if (findLiteral.field == null && obj != null && !(obj instanceof String)) {
                findLiteral.assign(this);
            }
            if ((findLiteral.flags & 2) == 0) {
                findLiteral.flags |= 4;
            }
        } else {
            findLiteral.flags |= 1;
            int i = this.stackPointer;
            if ((obj instanceof FString) && ((FString) obj).size() < 65535) {
                push(obj.toString(), Type.string_type);
            } else if (obj instanceof Externalizable) {
                ((Externalizable) obj).writeExternal(this);
            } else if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                for (Object writeObject : objArr) {
                    writeObject(writeObject);
                }
            } else if (obj != null && !(obj instanceof String) && !(findLiteral.type instanceof ArrayType)) {
                if (obj instanceof BigInteger) {
                    writeChars(obj.toString());
                } else if (obj instanceof BigDecimal) {
                    BigDecimal bigDecimal = (BigDecimal) obj;
                    writeObject(bigDecimal.unscaledValue());
                    writeInt(bigDecimal.scale());
                } else if (obj instanceof Integer) {
                    push(obj, Type.intType);
                } else if (obj instanceof Short) {
                    push(obj, Type.shortType);
                } else if (obj instanceof Byte) {
                    push(obj, Type.byteType);
                } else if (obj instanceof Long) {
                    push(obj, Type.longType);
                } else if (obj instanceof Double) {
                    push(obj, Type.doubleType);
                } else if (obj instanceof Float) {
                    push(obj, Type.floatType);
                } else if (obj instanceof Character) {
                    push(obj, Type.charType);
                } else if (obj instanceof Class) {
                    push(obj, Type.java_lang_Class_type);
                } else if (obj instanceof Pattern) {
                    Pattern pattern = (Pattern) obj;
                    push(pattern.pattern(), Type.string_type);
                    push(Integer.valueOf(pattern.flags()), Type.intType);
                } else {
                    error(obj.getClass().getName() + " does not implement Externalizable");
                }
            }
            int i2 = this.stackPointer - i;
            if (i2 == 0) {
                findLiteral.argValues = Values.noArgs;
                findLiteral.argTypes = Type.typeArray0;
            } else {
                findLiteral.argValues = new Object[i2];
                findLiteral.argTypes = new Type[i2];
                System.arraycopy(this.valueStack, i, findLiteral.argValues, 0, i2);
                System.arraycopy(this.typeStack, i, findLiteral.argTypes, 0, i2);
                this.stackPointer = i;
            }
            findLiteral.flags |= 2;
        }
        push(findLiteral, findLiteral.type);
    }

    public Literal findLiteral(Object obj) {
        Literal literal;
        if (obj == null) {
            return Literal.nullLiteral;
        }
        Literal literal2 = (Literal) this.literalTable.get(obj);
        if (literal2 != null) {
            return literal2;
        }
        if (this.comp.immediate) {
            return new Literal(obj, this);
        }
        Class cls = obj.getClass();
        Type make = Type.make(cls);
        synchronized (staticTable) {
            literal = (Literal) staticTable.get(obj, (Object) null, (Object) null);
            if ((literal == null || literal.value != obj) && (make instanceof ClassType)) {
                ClassType classType = (ClassType) make;
                while (true) {
                    if (staticTable.get(cls, Boolean.TRUE, (Object) null) != null) {
                        break;
                    }
                    staticTable.put(cls, Boolean.TRUE, cls);
                    for (Field fields = classType.getFields(); fields != null; fields = fields.getNext()) {
                        if ((fields.getModifiers() & 25) == 25) {
                            try {
                                Object obj2 = fields.getReflectField().get((Object) null);
                                if (obj2 != null) {
                                    if (cls.isInstance(obj2)) {
                                        Literal literal3 = new Literal(obj2, fields, this);
                                        staticTable.put(obj2, (Object) null, literal3);
                                        if (obj == obj2) {
                                            literal = literal3;
                                        }
                                    }
                                }
                            } catch (Throwable th) {
                                error("caught " + th + " getting static field " + fields);
                            }
                        }
                    }
                    cls = cls.getSuperclass();
                    if (cls == null) {
                        break;
                    }
                    classType = (ClassType) Type.make(cls);
                }
            }
        }
        if (literal == null) {
            return new Literal(obj, make, this);
        }
        this.literalTable.put(obj, literal);
        return literal;
    }

    /* access modifiers changed from: package-private */
    public Method getMethod(ClassType classType, String str, Literal literal, boolean z) {
        int i;
        Type[] typeArr;
        Method method;
        Type[] typeArr2;
        int i2;
        Type[] typeArr3;
        Method method2;
        Literal literal2 = literal;
        Type[] typeArr4 = literal2.argTypes;
        Method declaredMethods = classType.getDeclaredMethods();
        int length = typeArr4.length;
        boolean z2 = false;
        long j = 0;
        ArrayType[] arrayTypeArr = null;
        Method method3 = null;
        while (declaredMethods != null) {
            if (str.equals(declaredMethods.getName()) && z == declaredMethods.getStaticFlag()) {
                Type[] parameterTypes = declaredMethods.getParameterTypes();
                int i3 = 0;
                int i4 = 0;
                long j2 = 0;
                while (true) {
                    if (i4 != length || i3 != parameterTypes.length) {
                        if (i4 == length || i3 == parameterTypes.length) {
                            break;
                        }
                        Type type = typeArr4[i4];
                        Type type2 = parameterTypes[i3];
                        if (!type.isSubtype(type2)) {
                            typeArr2 = parameterTypes;
                            if (!(type2 instanceof ArrayType) || i3 >= 64 || (type != Type.intType && type != Type.shortType)) {
                                break;
                            }
                            int intValue = ((Number) literal2.argValues[i4]).intValue();
                            if (intValue < 0 && classType.getName().equals("gnu.math.IntNum")) {
                                intValue -= Integer.MIN_VALUE;
                            }
                            Type componentType = ((ArrayType) type2).getComponentType();
                            if (intValue < 0 || (i2 = i4 + intValue) >= length) {
                                break;
                            }
                            while (true) {
                                int i5 = intValue - 1;
                                if (i5 < 0) {
                                    typeArr3 = typeArr4;
                                    method2 = declaredMethods;
                                    j2 |= (long) (1 << i3);
                                    i4 = i2;
                                    break;
                                }
                                int i6 = i5;
                                Type type3 = typeArr4[i4 + i5 + 1];
                                typeArr = typeArr4;
                                if (componentType instanceof PrimType) {
                                    if (componentType.getSignature() != type3.getSignature()) {
                                        break;
                                    }
                                } else if (!type3.isSubtype(componentType)) {
                                    break;
                                }
                                typeArr4 = typeArr;
                                intValue = i6;
                            }
                        } else {
                            typeArr3 = typeArr4;
                            method2 = declaredMethods;
                            typeArr2 = parameterTypes;
                        }
                        i4++;
                        i3++;
                        boolean z3 = z;
                        declaredMethods = method2;
                        parameterTypes = typeArr2;
                        typeArr4 = typeArr3;
                    } else if (method3 == null || (j != 0 && j2 == 0)) {
                        typeArr = typeArr4;
                        method = declaredMethods;
                        method3 = method;
                        arrayTypeArr = parameterTypes;
                        j = j2;
                    } else if (j2 == 0) {
                        int i7 = length;
                        boolean z4 = false;
                        boolean z5 = false;
                        while (true) {
                            i7--;
                            if (i7 < 0) {
                                break;
                            }
                            int compare = arrayTypeArr[i7].compare(parameterTypes[i7]);
                            if (compare != 1) {
                                z5 = true;
                                if (z4) {
                                    break;
                                }
                            }
                            if (compare != -1) {
                                z4 = true;
                                if (z5) {
                                    break;
                                }
                            }
                        }
                        if (z4) {
                            method3 = declaredMethods;
                            arrayTypeArr = parameterTypes;
                        }
                        z2 = z4 && z5;
                    }
                }
            }
            typeArr = typeArr4;
            method = declaredMethods;
            declaredMethods = method.getNext();
            typeArr4 = typeArr;
        }
        if (z2) {
            return null;
        }
        if (j != 0) {
            Object[] objArr = new Object[arrayTypeArr.length];
            Type[] typeArr5 = new Type[arrayTypeArr.length];
            int i8 = 0;
            int i9 = 0;
            while (i8 != length) {
                ArrayType arrayType = arrayTypeArr[i9];
                if ((((long) (1 << i9)) & j) == 0) {
                    objArr[i9] = literal2.argValues[i8];
                    typeArr5[i9] = literal2.argTypes[i8];
                    i = 1;
                } else {
                    int intValue2 = ((Number) literal2.argValues[i8]).intValue();
                    boolean equals = classType.getName().equals("gnu.math.IntNum");
                    if (equals) {
                        intValue2 -= Integer.MIN_VALUE;
                    }
                    Type componentType2 = arrayType.getComponentType();
                    typeArr5[i9] = arrayType;
                    objArr[i9] = Array.newInstance(componentType2.getReflectClass(), intValue2);
                    Object[] objArr2 = literal2.argValues;
                    if (!equals) {
                        int i10 = intValue2;
                        while (true) {
                            i10--;
                            if (i10 < 0) {
                                break;
                            }
                            Array.set(objArr[i9], i10, objArr2[i8 + 1 + i10]);
                            Literal literal3 = literal;
                        }
                    } else {
                        int[] iArr = (int[]) objArr[i9];
                        for (int i11 = intValue2; i11 > 0; i11--) {
                            iArr[intValue2 - i11] = ((Integer) objArr2[i8 + i11]).intValue();
                        }
                    }
                    Literal literal4 = new Literal(objArr[i9], (Type) arrayType);
                    if (componentType2 instanceof ObjectType) {
                        literal4.argValues = (Object[]) objArr[i9];
                    }
                    objArr[i9] = literal4;
                    i8 += intValue2;
                    i = 1;
                }
                i8 += i;
                i9++;
                literal2 = literal;
            }
            literal2.argValues = objArr;
            literal2.argTypes = typeArr5;
        }
        return method3;
    }

    /* access modifiers changed from: package-private */
    public void putArgs(Literal literal, CodeAttr codeAttr) {
        Type[] typeArr = literal.argTypes;
        int length = typeArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = literal.argValues[i];
            if (obj instanceof Literal) {
                emit((Literal) obj, false);
            } else {
                this.comp.compileConstant(obj, new StackTarget(typeArr[i]));
            }
        }
    }

    private void store(Literal literal, boolean z, CodeAttr codeAttr) {
        if (literal.field != null) {
            if (!z) {
                codeAttr.emitDup(literal.type);
            }
            codeAttr.emitPutStatic(literal.field);
        }
        literal.flags |= 8;
    }

    /* access modifiers changed from: package-private */
    public void emit(Literal literal, boolean z) {
        Method method;
        boolean z2;
        CodeAttr code = this.comp.getCode();
        if (literal.value == null) {
            if (!z) {
                code.emitPushNull();
            }
        } else if (literal.value instanceof String) {
            if (!z) {
                code.emitPushString(literal.value.toString());
            }
        } else if ((literal.flags & 8) == 0) {
            boolean z3 = false;
            if (literal.value instanceof Object[]) {
                int length = literal.argValues.length;
                Type componentType = ((ArrayType) literal.type).getComponentType();
                code.emitPushInt(length);
                code.emitNewArray(componentType);
                store(literal, z, code);
                for (int i = 0; i < length; i++) {
                    Literal literal2 = (Literal) literal.argValues[i];
                    if (literal2.value != null) {
                        code.emitDup(componentType);
                        code.emitPushInt(i);
                        emit(literal2, false);
                        code.emitArrayStore(componentType);
                    }
                }
            } else if (literal.type instanceof ArrayType) {
                code.emitPushPrimArray(literal.value, (ArrayType) literal.type);
                store(literal, z, code);
            } else if (literal.value instanceof Class) {
                Class cls = (Class) literal.value;
                if (cls.isPrimitive()) {
                    String name = cls.getName();
                    if (name.equals("int")) {
                        name = PropertyTypeConstants.PROPERTY_TYPE_INTEGER;
                    }
                    code.emitGetStatic(ClassType.make("java.lang." + Character.toUpperCase(name.charAt(0)) + name.substring(1)).getDeclaredField(Intents.WifiConnect.TYPE));
                } else {
                    this.comp.loadClassRef((ObjectType) Type.make(cls));
                }
                store(literal, z, code);
            } else {
                String str = "make";
                if (!(literal.value instanceof ClassType) || ((ClassType) literal.value).isExisting()) {
                    ClassType classType = (ClassType) literal.type;
                    boolean z4 = (literal.flags & 4) != 0;
                    Method method2 = null;
                    if (!z4) {
                        method = !(literal.value instanceof Symbol) ? getMethod(classType, "valueOf", literal, true) : null;
                        if (method == null && !(literal.value instanceof Values)) {
                            if (literal.value instanceof Pattern) {
                                str = "compile";
                            }
                            method = getMethod(classType, str, literal, true);
                        }
                        if (method != null) {
                            z2 = true;
                        } else {
                            if (literal.argTypes.length > 0) {
                                method = getMethod(classType, "<init>", literal, false);
                            }
                            z2 = false;
                        }
                        if (method == null) {
                            z4 = true;
                        }
                    } else {
                        method = null;
                        z2 = false;
                    }
                    if (z4) {
                        method = getMethod(classType, "set", literal, false);
                    }
                    if (method == null && literal.argTypes.length > 0) {
                        error("no method to construct " + literal.type);
                    }
                    if (z2) {
                        putArgs(literal, code);
                        code.emitInvokeStatic(method);
                    } else if (z4) {
                        code.emitNew(classType);
                        code.emitDup((Type) classType);
                        code.emitInvokeSpecial(classType.getDeclaredMethod("<init>", 0));
                    } else {
                        code.emitNew(classType);
                        code.emitDup((Type) classType);
                        putArgs(literal, code);
                        code.emitInvokeSpecial(method);
                    }
                    if (!z2 && !(literal.value instanceof Values)) {
                        method2 = classType.getDeclaredMethod("readResolve", 0);
                    }
                    if (method2 != null) {
                        code.emitInvokeVirtual(method2);
                        classType.emitCoerceFromObject(code);
                    }
                    if (z && (!z4 || method == null)) {
                        z3 = true;
                    }
                    store(literal, z3, code);
                    if (z4 && method != null) {
                        if (!z) {
                            code.emitDup((Type) classType);
                        }
                        putArgs(literal, code);
                        code.emitInvokeVirtual(method);
                        return;
                    }
                    return;
                }
                this.comp.loadClassRef((ClassType) literal.value);
                Method declaredMethod = Compilation.typeType.getDeclaredMethod("valueOf", 1);
                if (declaredMethod == null) {
                    declaredMethod = Compilation.typeType.getDeclaredMethod(str, 1);
                }
                code.emitInvokeStatic(declaredMethod);
                code.emitCheckcast(Compilation.typeClassType);
                store(literal, z, code);
            }
        } else if (!z) {
            code.emitGetStatic(literal.field);
        }
    }
}
