package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import gnu.kawa.util.AbstractWeakHashTable;
import java.io.PrintWriter;
import java.util.HashMap;

public abstract class Type implements java.lang.reflect.Type {
    public static final PrimType booleanType;
    public static final Method booleanValue_method;
    public static final ClassType boolean_ctype;
    public static final PrimType boolean_type;
    public static final PrimType byteType;
    public static final PrimType byte_type;
    public static final PrimType charType;
    public static final PrimType char_type;
    public static final Method clone_method;
    public static final PrimType doubleType;
    public static final Method doubleValue_method;
    public static final PrimType double_type;
    public static final ObjectType errorType = new ClassType("(error type)");
    public static final PrimType floatType;
    public static final Method floatValue_method;
    public static final PrimType float_type;
    public static final PrimType intType;
    public static final Method intValue_method;
    public static final PrimType int_type;
    public static final ClassType java_lang_Class_type;
    public static final ClassType javalangBooleanType;
    public static final ClassType javalangClassType;
    public static final ClassType javalangNumberType;
    public static final ClassType javalangObjectType;
    public static ClassType javalangStringType = ClassType.make("java.lang.String");
    public static final ClassType javalangThrowableType;
    public static final PrimType longType;
    public static final Method longValue_method;
    public static final PrimType long_type;
    static ClassToTypeMap mapClassToType;
    static HashMap<String, Type> mapNameToType;
    public static final PrimType neverReturnsType;
    public static final ObjectType nullType = new ObjectType("(type of null)");
    public static final ClassType number_type;
    public static final ClassType objectType;
    public static final ClassType pointer_type;
    public static final PrimType shortType;
    public static final PrimType short_type;
    public static final ClassType string_type = javalangStringType;
    public static final ClassType throwable_type;
    public static final ClassType toStringType;
    public static final Method toString_method;
    public static final ClassType tostring_type;
    public static final Type[] typeArray0;
    public static final PrimType voidType;
    public static final PrimType void_type;
    ArrayType array_type;
    protected Class reflectClass;
    String signature;
    int size;
    String this_name;

    protected static int swappedCompareResult(int i) {
        if (i == 1) {
            return -1;
        }
        if (i == -1) {
            return 1;
        }
        return i;
    }

    public abstract Object coerceFromObject(Object obj);

    public Object coerceToObject(Object obj) {
        return obj;
    }

    public abstract int compare(Type type);

    public void emitCoerceToObject(CodeAttr codeAttr) {
    }

    public Type getImplementationType() {
        return this;
    }

    public Type getRealType() {
        return this;
    }

    public boolean isExisting() {
        return true;
    }

    protected Type() {
    }

    public static Type lookupType(String str) {
        Type type;
        HashMap<String, Type> hashMap = mapNameToType;
        synchronized (hashMap) {
            type = hashMap.get(str);
        }
        return type;
    }

    public static Type getType(String str) {
        Type type;
        ArrayType arrayType;
        HashMap<String, Type> hashMap = mapNameToType;
        synchronized (hashMap) {
            Type type2 = hashMap.get(str);
            type = type2;
            if (type2 == null) {
                if (str.endsWith("[]")) {
                    arrayType = ArrayType.make(str);
                } else {
                    ClassType classType = new ClassType(str);
                    classType.flags |= 16;
                    arrayType = classType;
                }
                hashMap.put(str, arrayType);
                type = arrayType;
            }
        }
        return type;
    }

    public static synchronized void registerTypeForClass(Class cls, Type type) {
        synchronized (Type.class) {
            ClassToTypeMap classToTypeMap = mapClassToType;
            if (classToTypeMap == null) {
                classToTypeMap = new ClassToTypeMap();
                mapClassToType = classToTypeMap;
            }
            type.reflectClass = cls;
            classToTypeMap.put(cls, type);
        }
    }

    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:260)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:70)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:175)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:152)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:260)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:70)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    public static synchronized gnu.bytecode.Type make(java.lang.Class r5) {
        /*
            java.lang.Class<gnu.bytecode.Type> r0 = gnu.bytecode.Type.class
            monitor-enter(r0)
            gnu.bytecode.Type$ClassToTypeMap r1 = mapClassToType     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0011
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0064 }
            gnu.bytecode.Type r1 = (gnu.bytecode.Type) r1     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0011
            monitor-exit(r0)
            return r1
        L_0x0011:
            boolean r1 = r5.isArray()     // Catch:{ all -> 0x0064 }
            if (r1 == 0) goto L_0x0024
            java.lang.Class r1 = r5.getComponentType()     // Catch:{ all -> 0x0064 }
            gnu.bytecode.Type r1 = make(r1)     // Catch:{ all -> 0x0064 }
            gnu.bytecode.ArrayType r1 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r1)     // Catch:{ all -> 0x0064 }
            goto L_0x0054
        L_0x0024:
            boolean r1 = r5.isPrimitive()     // Catch:{ all -> 0x0064 }
            if (r1 != 0) goto L_0x005c
            java.lang.String r1 = r5.getName()     // Catch:{ all -> 0x0064 }
            java.util.HashMap<java.lang.String, gnu.bytecode.Type> r2 = mapNameToType     // Catch:{ all -> 0x0064 }
            monitor-enter(r2)     // Catch:{ all -> 0x0064 }
            java.lang.Object r3 = r2.get(r1)     // Catch:{ all -> 0x0059 }
            gnu.bytecode.Type r3 = (gnu.bytecode.Type) r3     // Catch:{ all -> 0x0059 }
            if (r3 == 0) goto L_0x0042
            java.lang.Class r4 = r3.reflectClass     // Catch:{ all -> 0x0059 }
            if (r4 == r5) goto L_0x0040
            if (r4 == 0) goto L_0x0040
            goto L_0x0042
        L_0x0040:
            r1 = r3
            goto L_0x0053
        L_0x0042:
            gnu.bytecode.ClassType r3 = new gnu.bytecode.ClassType     // Catch:{ all -> 0x0059 }
            r3.<init>(r1)     // Catch:{ all -> 0x0059 }
            int r4 = r3.flags     // Catch:{ all -> 0x0059 }
            r4 = r4 | 16
            r3.flags = r4     // Catch:{ all -> 0x0059 }
            java.util.HashMap<java.lang.String, gnu.bytecode.Type> r4 = mapNameToType     // Catch:{ all -> 0x0059 }
            r4.put(r1, r3)     // Catch:{ all -> 0x0059 }
            goto L_0x0040
        L_0x0053:
            monitor-exit(r2)     // Catch:{ all -> 0x0059 }
        L_0x0054:
            registerTypeForClass(r5, r1)     // Catch:{ all -> 0x0064 }
            monitor-exit(r0)
            return r1
        L_0x0059:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0059 }
            throw r5     // Catch:{ all -> 0x0064 }
        L_0x005c:
            java.lang.Error r5 = new java.lang.Error     // Catch:{ all -> 0x0064 }
            java.lang.String r1 = "internal error - primitive type not found"
            r5.<init>(r1)     // Catch:{ all -> 0x0064 }
            throw r5     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.Type.make(java.lang.Class):gnu.bytecode.Type");
    }

    public String getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: protected */
    public void setSignature(String str) {
        this.signature = str;
    }

    Type(String str, String str2) {
        this.this_name = str;
        this.signature = str2;
    }

    public Type(Type type) {
        this.this_name = type.this_name;
        this.signature = type.signature;
        this.size = type.size;
        this.reflectClass = type.reflectClass;
    }

    public Type promote() {
        return this.size < 4 ? intType : this;
    }

    public final int getSize() {
        return this.size;
    }

    public int getSizeInWords() {
        return this.size > 4 ? 2 : 1;
    }

    public final boolean isVoid() {
        return this.size == 0;
    }

    public static PrimType signatureToPrimitive(char c) {
        if (c == 'F') {
            return floatType;
        }
        if (c == 'S') {
            return shortType;
        }
        if (c == 'V') {
            return voidType;
        }
        if (c == 'Z') {
            return booleanType;
        }
        if (c == 'I') {
            return intType;
        }
        if (c == 'J') {
            return longType;
        }
        switch (c) {
            case 'B':
                return byteType;
            case 'C':
                return charType;
            case 'D':
                return doubleType;
            default:
                return null;
        }
    }

    public static Type signatureToType(String str, int i, int i2) {
        int i3;
        PrimType signatureToPrimitive;
        if (i2 == 0) {
            return null;
        }
        char charAt = str.charAt(i);
        if (i2 == 1 && (signatureToPrimitive = signatureToPrimitive(charAt)) != null) {
            return signatureToPrimitive;
        }
        if (charAt == '[') {
            Type signatureToType = signatureToType(str, i + 1, i2 - 1);
            if (signatureToType == null) {
                return null;
            }
            return ArrayType.make(signatureToType);
        } else if (charAt == 'L' && i2 > 2 && str.indexOf(59, i) == (i3 = (i2 - 1) + i)) {
            return ClassType.make(str.substring(i + 1, i3).replace('/', '.'));
        } else {
            return null;
        }
    }

    public static Type signatureToType(String str) {
        return signatureToType(str, 0, str.length());
    }

    public static void printSignature(String str, int i, int i2, PrintWriter printWriter) {
        int i3;
        if (i2 != 0) {
            char charAt = str.charAt(i);
            if (i2 == 1) {
                PrimType signatureToPrimitive = signatureToPrimitive(charAt);
                if (signatureToPrimitive != null) {
                    printWriter.print(signatureToPrimitive.getName());
                }
            } else if (charAt == '[') {
                printSignature(str, i + 1, i2 - 1, printWriter);
                printWriter.print("[]");
            } else if (charAt == 'L' && i2 > 2 && str.indexOf(59, i) == (i3 = (i2 - 1) + i)) {
                printWriter.print(str.substring(i + 1, i3).replace('/', '.'));
            } else {
                printWriter.append(str, i, i2 - i);
            }
        }
    }

    public static int signatureLength(String str, int i) {
        int indexOf;
        if (str.length() <= i) {
            return -1;
        }
        char charAt = str.charAt(i);
        int i2 = 0;
        while (charAt == '[') {
            i2++;
            i++;
            charAt = str.charAt(i);
        }
        if (signatureToPrimitive(charAt) != null) {
            return i2 + 1;
        }
        if (charAt != 'L' || (indexOf = str.indexOf(59, i)) <= 0) {
            return -1;
        }
        return ((i2 + indexOf) + 1) - i;
    }

    public static int signatureLength(String str) {
        return signatureLength(str, 0);
    }

    public static String signatureToName(String str) {
        int i;
        PrimType signatureToPrimitive;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        char charAt = str.charAt(0);
        int i2 = 1;
        if (length == 1 && (signatureToPrimitive = signatureToPrimitive(charAt)) != null) {
            return signatureToPrimitive.getName();
        }
        if (charAt == '[') {
            if (1 < length && str.charAt(1) == '[') {
                i2 = 2;
            }
            String signatureToName = signatureToName(str.substring(i2));
            if (signatureToName == null) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(50);
            stringBuffer.append(signatureToName);
            while (true) {
                i2--;
                if (i2 < 0) {
                    return stringBuffer.toString();
                }
                stringBuffer.append("[]");
            }
        } else if (charAt == 'L' && length > 2 && str.indexOf(59) == (i = length - 1)) {
            return str.substring(1, i).replace('/', '.');
        } else {
            return null;
        }
    }

    public final String getName() {
        return this.this_name;
    }

    /* access modifiers changed from: protected */
    public void setName(String str) {
        this.this_name = str;
    }

    public static boolean isValidJavaTypeName(String str) {
        int length = str.length();
        while (length > 2 && str.charAt(length - 1) == ']' && str.charAt(length - 2) == '[') {
            length -= 2;
        }
        int i = 0;
        boolean z = false;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt != '.') {
                if (z) {
                    if (!Character.isJavaIdentifierPart(charAt)) {
                        return false;
                    }
                } else if (!Character.isJavaIdentifierStart(charAt)) {
                    return false;
                }
                z = true;
            } else if (!z) {
                return false;
            } else {
                z = false;
            }
            i++;
        }
        if (i == length) {
            return true;
        }
        return false;
    }

    public boolean isInstance(Object obj) {
        return getReflectClass().isInstance(obj);
    }

    public final boolean isSubtype(Type type) {
        int compare = compare(type);
        return compare == -1 || compare == 0;
    }

    public static Type lowestCommonSuperType(Type type, Type type2) {
        PrimType primType = neverReturnsType;
        if (type == primType) {
            return type2;
        }
        if (type2 == primType) {
            return type;
        }
        if (type == null || type2 == null) {
            return null;
        }
        if (!(type instanceof PrimType) || !(type2 instanceof PrimType)) {
            if (type.isSubtype(type2)) {
                return type2;
            }
            if (type2.isSubtype(type)) {
                return type;
            }
            if (!(type instanceof ClassType) || !(type2 instanceof ClassType)) {
                return objectType;
            }
            ClassType classType = (ClassType) type;
            ClassType classType2 = (ClassType) type2;
            if (classType.isInterface() || classType2.isInterface()) {
                return objectType;
            }
            return lowestCommonSuperType(classType.getSuperclass(), classType2.getSuperclass());
        } else if (type == type2) {
            return type;
        } else {
            Type promotedType = ((PrimType) type).promotedType();
            if (promotedType == ((PrimType) type2).promotedType()) {
                return promotedType;
            }
            return null;
        }
    }

    public static boolean isMoreSpecific(Type[] typeArr, Type[] typeArr2) {
        if (typeArr.length != typeArr2.length) {
            return false;
        }
        int length = typeArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (typeArr[length].isSubtype(typeArr2[length]));
        return false;
    }

    public void emitIsInstance(CodeAttr codeAttr) {
        codeAttr.emitInstanceof(this);
    }

    public void emitConvertFromPrimitive(Type type, CodeAttr codeAttr) {
        type.emitCoerceToObject(codeAttr);
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        throw new Error("unimplemented emitCoerceFromObject for " + this);
    }

    static {
        PrimType primType = new PrimType("byte", "B", 1, Byte.TYPE);
        byteType = primType;
        PrimType primType2 = new PrimType("short", "S", 2, Short.TYPE);
        shortType = primType2;
        PrimType primType3 = new PrimType("int", "I", 4, Integer.TYPE);
        intType = primType3;
        PrimType primType4 = new PrimType(LongTypedProperty.TYPE, "J", 8, Long.TYPE);
        longType = primType4;
        PrimType primType5 = new PrimType(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, "F", 4, Float.TYPE);
        floatType = primType5;
        PrimType primType6 = new PrimType(DoubleTypedProperty.TYPE, "D", 8, Double.TYPE);
        doubleType = primType6;
        Class cls = Boolean.TYPE;
        Object obj = DoubleTypedProperty.TYPE;
        PrimType primType7 = new PrimType("boolean", "Z", 1, cls);
        booleanType = primType7;
        Class cls2 = Character.TYPE;
        Object obj2 = PropertyTypeConstants.PROPERTY_TYPE_FLOAT;
        PrimType primType8 = new PrimType("char", "C", 2, cls2);
        charType = primType8;
        Class cls3 = Void.TYPE;
        Object obj3 = LongTypedProperty.TYPE;
        PrimType primType9 = new PrimType("void", "V", 0, cls3);
        voidType = primType9;
        byte_type = primType;
        short_type = primType2;
        int_type = primType3;
        long_type = primType4;
        float_type = primType5;
        double_type = primType6;
        boolean_type = primType7;
        char_type = primType8;
        void_type = primType9;
        HashMap<String, Type> hashMap = new HashMap<>();
        mapNameToType = hashMap;
        hashMap.put("byte", primType);
        mapNameToType.put("short", primType2);
        mapNameToType.put("int", primType3);
        mapNameToType.put(obj3, primType4);
        mapNameToType.put(obj2, primType5);
        mapNameToType.put(obj, primType6);
        mapNameToType.put("boolean", primType7);
        mapNameToType.put("char", primType8);
        mapNameToType.put("void", primType9);
        PrimType primType10 = new PrimType(primType9);
        neverReturnsType = primType10;
        primType10.this_name = "(never-returns)";
        ClassType classType = new ClassType("java.lang.String");
        toStringType = classType;
        ClassType make = ClassType.make("java.lang.Object");
        javalangObjectType = make;
        objectType = make;
        ClassType make2 = ClassType.make("java.lang.Boolean");
        javalangBooleanType = make2;
        ClassType make3 = ClassType.make("java.lang.Throwable");
        javalangThrowableType = make3;
        Type[] typeArr = new Type[0];
        typeArray0 = typeArr;
        toString_method = make.getDeclaredMethod("toString", 0);
        ClassType make4 = ClassType.make("java.lang.Number");
        javalangNumberType = make4;
        clone_method = Method.makeCloneMethod(make);
        intValue_method = make4.addMethod("intValue", typeArr, (Type) primType3, 1);
        longValue_method = make4.addMethod("longValue", typeArr, (Type) primType4, 1);
        floatValue_method = make4.addMethod("floatValue", typeArr, (Type) primType5, 1);
        doubleValue_method = make4.addMethod("doubleValue", typeArr, (Type) primType6, 1);
        booleanValue_method = make2.addMethod("booleanValue", typeArr, (Type) primType7, 1);
        ClassType make5 = ClassType.make("java.lang.Class");
        javalangClassType = make5;
        pointer_type = make;
        tostring_type = classType;
        java_lang_Class_type = make5;
        boolean_ctype = make2;
        throwable_type = make3;
        number_type = make4;
    }

    public Class getReflectClass() {
        return this.reflectClass;
    }

    public void setReflectClass(Class cls) {
        this.reflectClass = cls;
    }

    public String toString() {
        return "Type " + getName();
    }

    public int hashCode() {
        String type = toString();
        if (type == null) {
            return 0;
        }
        return type.hashCode();
    }

    static class ClassToTypeMap extends AbstractWeakHashTable<Class, Type> {
        /* access modifiers changed from: protected */
        public boolean matches(Class cls, Class cls2) {
            return cls == cls2;
        }

        ClassToTypeMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(Type type) {
            return type.reflectClass;
        }
    }
}
