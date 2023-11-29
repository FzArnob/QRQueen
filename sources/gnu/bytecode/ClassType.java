package gnu.bytecode;

import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ClassType extends ObjectType implements AttrContainer, Externalizable, Member {
    public static final int JDK_1_1_VERSION = 2949123;
    public static final int JDK_1_2_VERSION = 3014656;
    public static final int JDK_1_3_VERSION = 3080192;
    public static final int JDK_1_4_VERSION = 3145728;
    public static final int JDK_1_5_VERSION = 3211264;
    public static final int JDK_1_6_VERSION = 3276800;
    public static final int JDK_1_7_VERSION = 3342336;
    public static final ClassType[] noClasses = new ClassType[0];
    int Code_name_index;
    int ConstantValue_name_index;
    int LineNumberTable_name_index;
    int LocalVariableTable_name_index;
    int access_flags;
    Attribute attributes;
    int classfileFormatVersion = JDK_1_1_VERSION;
    ConstantPool constants;
    public Method constructor;
    boolean emitDebugInfo = true;
    Member enclosingMember;
    Field fields;
    int fields_count;
    ClassType firstInnerClass;
    int[] interfaceIndexes;
    ClassType[] interfaces;
    Field last_field;
    Method last_method;
    Method methods;
    int methods_count;
    ClassType nextInnerClass;
    SourceDebugExtAttr sourceDbgExt;
    ClassType superClass;
    int superClassIndex = -1;
    int thisClassIndex;

    public short getClassfileMajorVersion() {
        return (short) (this.classfileFormatVersion >> 16);
    }

    public short getClassfileMinorVersion() {
        return (short) (this.classfileFormatVersion & 65535);
    }

    public void setClassfileVersion(int i, int i2) {
        this.classfileFormatVersion = ((i & 65535) * 65536) + (i2 * 65535);
    }

    public void setClassfileVersion(int i) {
        this.classfileFormatVersion = i;
    }

    public int getClassfileVersion() {
        return this.classfileFormatVersion;
    }

    public void setClassfileVersionJava5() {
        setClassfileVersion(JDK_1_5_VERSION);
    }

    public static ClassType make(String str) {
        return (ClassType) Type.getType(str);
    }

    public static ClassType make(String str, ClassType classType) {
        ClassType make = make(str);
        if (make.superClass == null) {
            make.setSuper(classType);
        }
        return make;
    }

    public final Attribute getAttributes() {
        return this.attributes;
    }

    public final void setAttributes(Attribute attribute) {
        this.attributes = attribute;
    }

    public final ConstantPool getConstants() {
        return this.constants;
    }

    public final CpoolEntry getConstant(int i) {
        ConstantPool constantPool = this.constants;
        if (constantPool == null || constantPool.pool == null || i > this.constants.count) {
            return null;
        }
        return this.constants.pool[i];
    }

    public final synchronized int getModifiers() {
        if (!(this.access_flags != 0 || (this.flags & 16) == 0 || getReflectClass() == null)) {
            this.access_flags = this.reflectClass.getModifiers();
        }
        return this.access_flags;
    }

    public final boolean getStaticFlag() {
        return (getModifiers() & 8) != 0;
    }

    public final void setModifiers(int i) {
        this.access_flags = i;
    }

    public final void addModifiers(int i) {
        this.access_flags = i | this.access_flags;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:10|11|(1:13)|14|(3:16|(2:17|(1:24)(1:23))|24)) */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0049, code lost:
        return r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0015 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0015 A[SYNTHETIC, Splitter:B:10:0x0015] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getSimpleName() {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.flags     // Catch:{ all -> 0x004a }
            r0 = r0 & 16
            if (r0 == 0) goto L_0x0015
            java.lang.Class r0 = r5.getReflectClass()     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0015
            java.lang.Class r0 = r5.reflectClass     // Catch:{ all -> 0x0015 }
            java.lang.String r0 = r0.getSimpleName()     // Catch:{ all -> 0x0015 }
            monitor-exit(r5)
            return r0
        L_0x0015:
            java.lang.String r0 = r5.getName()     // Catch:{ all -> 0x004a }
            r1 = 46
            int r1 = r0.lastIndexOf(r1)     // Catch:{ all -> 0x004a }
            if (r1 <= 0) goto L_0x0027
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)     // Catch:{ all -> 0x004a }
        L_0x0027:
            r1 = 36
            int r1 = r0.lastIndexOf(r1)     // Catch:{ all -> 0x004a }
            if (r1 < 0) goto L_0x0048
            int r2 = r0.length()     // Catch:{ all -> 0x004a }
        L_0x0033:
            int r1 = r1 + 1
            if (r1 >= r2) goto L_0x0044
            char r3 = r0.charAt(r1)     // Catch:{ all -> 0x004a }
            r4 = 48
            if (r3 < r4) goto L_0x0044
            r4 = 57
            if (r3 > r4) goto L_0x0044
            goto L_0x0033
        L_0x0044:
            java.lang.String r0 = r0.substring(r1)     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r5)
            return r0
        L_0x004a:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.getSimpleName():java.lang.String");
    }

    public void addMemberClass(ClassType classType) {
        ClassType classType2 = this.firstInnerClass;
        ClassType classType3 = null;
        while (classType2 != null) {
            if (classType2 != classType) {
                classType3 = classType2;
                classType2 = classType2.nextInnerClass;
            } else {
                return;
            }
        }
        if (classType3 == null) {
            this.firstInnerClass = classType;
        } else {
            classType3.nextInnerClass = classType;
        }
    }

    public ClassType getDeclaredClass(String str) {
        addMemberClasses();
        for (ClassType classType = this.firstInnerClass; classType != null; classType = classType.nextInnerClass) {
            if (str.equals(classType.getSimpleName())) {
                return classType;
            }
        }
        return null;
    }

    public ClassType getDeclaringClass() {
        addEnclosingMember();
        Member member = this.enclosingMember;
        if (member instanceof ClassType) {
            return (ClassType) member;
        }
        return null;
    }

    public Member getEnclosingMember() {
        addEnclosingMember();
        return this.enclosingMember;
    }

    public void setEnclosingMember(Member member) {
        this.enclosingMember = member;
    }

    /* access modifiers changed from: package-private */
    public synchronized void addEnclosingMember() {
        if ((this.flags & 24) == 16) {
            Class reflectClass = getReflectClass();
            this.flags |= 8;
            Class<?> enclosingClass = reflectClass.getEnclosingClass();
            if (enclosingClass != null) {
                if (!reflectClass.isMemberClass()) {
                    Method enclosingMethod = reflectClass.getEnclosingMethod();
                    if (enclosingMethod != null) {
                        this.enclosingMember = addMethod(enclosingMethod);
                        return;
                    }
                    Constructor<?> enclosingConstructor = reflectClass.getEnclosingConstructor();
                    if (enclosingConstructor != null) {
                        this.enclosingMember = addMethod((Constructor) enclosingConstructor);
                        return;
                    }
                }
                this.enclosingMember = (ClassType) Type.make(enclosingClass);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addMemberClasses() {
        /*
            r4 = this;
            monitor-enter(r4)
            int r0 = r4.flags     // Catch:{ all -> 0x002f }
            r0 = r0 & 20
            r1 = 16
            if (r0 == r1) goto L_0x000b
            monitor-exit(r4)
            return
        L_0x000b:
            java.lang.Class r0 = r4.getReflectClass()     // Catch:{ all -> 0x002f }
            int r1 = r4.flags     // Catch:{ all -> 0x002f }
            r1 = r1 | 4
            r4.flags = r1     // Catch:{ all -> 0x002f }
            java.lang.Class[] r0 = r0.getClasses()     // Catch:{ all -> 0x002f }
            int r1 = r0.length     // Catch:{ all -> 0x002f }
            if (r1 <= 0) goto L_0x002d
            r2 = 0
        L_0x001d:
            if (r2 >= r1) goto L_0x002d
            r3 = r0[r2]     // Catch:{ all -> 0x002f }
            gnu.bytecode.Type r3 = gnu.bytecode.Type.make(r3)     // Catch:{ all -> 0x002f }
            gnu.bytecode.ClassType r3 = (gnu.bytecode.ClassType) r3     // Catch:{ all -> 0x002f }
            r4.addMemberClass(r3)     // Catch:{ all -> 0x002f }
            int r2 = r2 + 1
            goto L_0x001d
        L_0x002d:
            monitor-exit(r4)
            return
        L_0x002f:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.addMemberClasses():void");
    }

    public final boolean hasOuterLink() {
        getFields();
        return (this.flags & 32) != 0;
    }

    public ClassType getOuterLinkType() {
        if (!hasOuterLink()) {
            return null;
        }
        return (ClassType) getDeclaredField("this$0").getType();
    }

    public final Field setOuterLink(ClassType classType) {
        if ((this.flags & 16) == 0) {
            Field declaredField = getDeclaredField("this$0");
            if (declaredField == null) {
                declaredField = addField("this$0", classType);
                this.flags |= 32;
                for (Method method = this.methods; method != null; method = method.getNext()) {
                    if ("<init>".equals(method.getName())) {
                        if (method.code == null) {
                            Type[] typeArr = method.arg_types;
                            Type[] typeArr2 = new Type[(typeArr.length + 1)];
                            System.arraycopy(typeArr, 0, typeArr2, 1, typeArr.length);
                            typeArr2[0] = classType;
                            method.arg_types = typeArr2;
                            method.signature = null;
                        } else {
                            throw new Error("setOuterLink called when " + method + " has code");
                        }
                    }
                }
            } else if (!classType.equals(declaredField.getType())) {
                throw new Error("inconsistent setOuterLink call for " + getName());
            }
            return declaredField;
        }
        throw new Error("setOuterLink called for existing class " + getName());
    }

    public boolean isAccessible(Member member, ObjectType objectType) {
        if (member.getStaticFlag()) {
            objectType = null;
        }
        return isAccessible(member.getDeclaringClass(), objectType, member.getModifiers());
    }

    public boolean isAccessible(ClassType classType, ObjectType objectType, int i) {
        int modifiers = classType.getModifiers();
        if ((i & 1) != 0 && (modifiers & 1) != 0) {
            return true;
        }
        String name = getName();
        String name2 = classType.getName();
        if (name.equals(name2)) {
            return true;
        }
        if ((i & 2) != 0) {
            return false;
        }
        int lastIndexOf = name.lastIndexOf(46);
        String str = "";
        String substring = lastIndexOf >= 0 ? name.substring(0, lastIndexOf) : str;
        int lastIndexOf2 = name2.lastIndexOf(46);
        if (lastIndexOf2 >= 0) {
            str = name2.substring(0, lastIndexOf2);
        }
        if (substring.equals(str)) {
            return true;
        }
        if ((modifiers & 1) != 0 && (i & 4) != 0 && isSubclass(classType) && (!(objectType instanceof ClassType) || ((ClassType) objectType).isSubclass(this))) {
            return true;
        }
        return false;
    }

    public void setName(String str) {
        this.this_name = str;
        setSignature("L" + str.replace('.', '/') + ";");
    }

    public void setStratum(String str) {
        if (this.sourceDbgExt == null) {
            this.sourceDbgExt = new SourceDebugExtAttr(this);
        }
        this.sourceDbgExt.addStratum(str);
    }

    public void setSourceFile(String str) {
        SourceDebugExtAttr sourceDebugExtAttr = this.sourceDbgExt;
        if (sourceDebugExtAttr != null) {
            sourceDebugExtAttr.addFile(str);
            if (this.sourceDbgExt.fileCount > 1) {
                return;
            }
        }
        String fixSourceFile = SourceFileAttr.fixSourceFile(str);
        int lastIndexOf = fixSourceFile.lastIndexOf(47);
        if (lastIndexOf >= 0) {
            fixSourceFile = fixSourceFile.substring(lastIndexOf + 1);
        }
        SourceFileAttr.setSourceFile(this, fixSourceFile);
    }

    public void setSuper(String str) {
        setSuper(str == null ? Type.pointer_type : make(str));
    }

    public void setSuper(ClassType classType) {
        this.superClass = classType;
    }

    public synchronized ClassType getSuperclass() {
        if (this.superClass == null && !isInterface() && !"java.lang.Object".equals(getName()) && (this.flags & 16) != 0 && getReflectClass() != null) {
            this.superClass = (ClassType) make(this.reflectClass.getSuperclass());
        }
        return this.superClass;
    }

    public String getPackageName() {
        String name = getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return "";
        }
        return name.substring(0, lastIndexOf);
    }

    public synchronized ClassType[] getInterfaces() {
        if (!(this.interfaces != null || (this.flags & 16) == 0 || getReflectClass() == null)) {
            Class[] interfaces2 = this.reflectClass.getInterfaces();
            int length = interfaces2.length;
            this.interfaces = length == 0 ? noClasses : new ClassType[length];
            for (int i = 0; i < length; i++) {
                this.interfaces[i] = (ClassType) Type.make(interfaces2[i]);
            }
        }
        return this.interfaces;
    }

    public void setInterfaces(ClassType[] classTypeArr) {
        this.interfaces = classTypeArr;
    }

    public void addInterface(ClassType classType) {
        ClassType[] classTypeArr = this.interfaces;
        int i = 0;
        if (classTypeArr == null || classTypeArr.length == 0) {
            this.interfaces = new ClassType[1];
        } else {
            int length = classTypeArr.length;
            int i2 = length;
            do {
                i2--;
                if (i2 < 0) {
                    ClassType[] classTypeArr2 = new ClassType[(length + 1)];
                    System.arraycopy(this.interfaces, 0, classTypeArr2, 0, length);
                    this.interfaces = classTypeArr2;
                    i = length;
                }
            } while (this.interfaces[i2] != classType);
            return;
        }
        this.interfaces[i] = classType;
    }

    public final boolean isInterface() {
        return (getModifiers() & 512) != 0;
    }

    public final void setInterface(boolean z) {
        if (z) {
            this.access_flags |= 1536;
        } else {
            this.access_flags &= -513;
        }
    }

    public ClassType() {
    }

    public ClassType(String str) {
        setName(str);
    }

    public final synchronized Field getFields() {
        if ((this.flags & 17) == 16) {
            addFields();
        }
        return this.fields;
    }

    public final int getFieldCount() {
        return this.fields_count;
    }

    public Field getDeclaredField(String str) {
        for (Field fields2 = getFields(); fields2 != null; fields2 = fields2.next) {
            if (str.equals(fields2.name)) {
                return fields2;
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized gnu.bytecode.Field getField(java.lang.String r5, int r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = r4
        L_0x0002:
            gnu.bytecode.Field r1 = r0.getDeclaredField(r5)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0014
            r2 = -1
            if (r6 == r2) goto L_0x0012
            int r2 = r1.getModifiers()     // Catch:{ all -> 0x0034 }
            r2 = r2 & r6
            if (r2 == 0) goto L_0x0014
        L_0x0012:
            monitor-exit(r4)
            return r1
        L_0x0014:
            gnu.bytecode.ClassType[] r1 = r0.getInterfaces()     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x002b
            r2 = 0
        L_0x001b:
            int r3 = r1.length     // Catch:{ all -> 0x0034 }
            if (r2 >= r3) goto L_0x002b
            r3 = r1[r2]     // Catch:{ all -> 0x0034 }
            gnu.bytecode.Field r3 = r3.getField(r5, r6)     // Catch:{ all -> 0x0034 }
            if (r3 == 0) goto L_0x0028
            monitor-exit(r4)
            return r3
        L_0x0028:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x002b:
            gnu.bytecode.ClassType r0 = r0.getSuperclass()     // Catch:{ all -> 0x0034 }
            if (r0 != 0) goto L_0x0002
            r5 = 0
            monitor-exit(r4)
            return r5
        L_0x0034:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.getField(java.lang.String, int):gnu.bytecode.Field");
    }

    public Field getField(String str) {
        return getField(str, 1);
    }

    public Field addField() {
        return new Field(this);
    }

    public Field addField(String str) {
        Field field = new Field(this);
        field.setName(str);
        return field;
    }

    public final Field addField(String str, Type type) {
        Field field = new Field(this);
        field.setName(str);
        field.setType(type);
        return field;
    }

    public final Field addField(String str, Type type, int i) {
        Field addField = addField(str, type);
        addField.flags = i;
        return addField;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r0 = r0.getFields();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addFields() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.Class r0 = r6.getReflectClass()     // Catch:{ all -> 0x0044 }
            java.lang.reflect.Field[] r0 = r0.getDeclaredFields()     // Catch:{ SecurityException -> 0x000a }
            goto L_0x000e
        L_0x000a:
            java.lang.reflect.Field[] r0 = r0.getFields()     // Catch:{ all -> 0x0044 }
        L_0x000e:
            int r1 = r0.length     // Catch:{ all -> 0x0044 }
            r2 = 0
        L_0x0010:
            if (r2 >= r1) goto L_0x003c
            r3 = r0[r2]     // Catch:{ all -> 0x0044 }
            java.lang.String r4 = "this$0"
            java.lang.String r5 = r3.getName()     // Catch:{ all -> 0x0044 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0044 }
            if (r4 == 0) goto L_0x0026
            int r4 = r6.flags     // Catch:{ all -> 0x0044 }
            r4 = r4 | 32
            r6.flags = r4     // Catch:{ all -> 0x0044 }
        L_0x0026:
            java.lang.String r4 = r3.getName()     // Catch:{ all -> 0x0044 }
            java.lang.Class r5 = r3.getType()     // Catch:{ all -> 0x0044 }
            gnu.bytecode.Type r5 = gnu.bytecode.Type.make(r5)     // Catch:{ all -> 0x0044 }
            int r3 = r3.getModifiers()     // Catch:{ all -> 0x0044 }
            r6.addField(r4, r5, r3)     // Catch:{ all -> 0x0044 }
            int r2 = r2 + 1
            goto L_0x0010
        L_0x003c:
            int r0 = r6.flags     // Catch:{ all -> 0x0044 }
            r0 = r0 | 1
            r6.flags = r0     // Catch:{ all -> 0x0044 }
            monitor-exit(r6)
            return
        L_0x0044:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.addFields():void");
    }

    public final Method getMethods() {
        return this.methods;
    }

    public final int getMethodCount() {
        return this.methods_count;
    }

    /* access modifiers changed from: package-private */
    public Method addMethod() {
        return new Method(this, 0);
    }

    public Method addMethod(String str) {
        return addMethod(str, 0);
    }

    public Method addMethod(String str, int i) {
        Method method = new Method(this, i);
        method.setName(str);
        return method;
    }

    public Method addMethod(String str, Type[] typeArr, Type type, int i) {
        return addMethod(str, i, typeArr, type);
    }

    public synchronized Method addMethod(String str, int i, Type[] typeArr, Type type) {
        Method declaredMethod = getDeclaredMethod(str, typeArr);
        if (declaredMethod != null && type.equals(declaredMethod.getReturnType()) && (declaredMethod.access_flags & i) == i) {
            return declaredMethod;
        }
        Method addMethod = addMethod(str, i);
        addMethod.arg_types = typeArr;
        addMethod.return_type = type;
        return addMethod;
    }

    public Method addMethod(Method method) {
        int modifiers = method.getModifiers();
        Class[] parameterTypes = method.getParameterTypes();
        int length = parameterTypes.length;
        Type[] typeArr = new Type[length];
        while (true) {
            length--;
            if (length >= 0) {
                typeArr[length] = Type.make(parameterTypes[length]);
            } else {
                return addMethod(method.getName(), modifiers, typeArr, Type.make(method.getReturnType()));
            }
        }
    }

    public Method addMethod(Constructor constructor2) {
        Class[] parameterTypes = constructor2.getParameterTypes();
        int modifiers = constructor2.getModifiers();
        int length = parameterTypes.length;
        Type[] typeArr = new Type[length];
        while (true) {
            length--;
            if (length < 0) {
                return addMethod("<init>", modifiers, typeArr, (Type) Type.voidType);
            }
            typeArr[length] = Type.make(parameterTypes[length]);
        }
    }

    public Method addMethod(String str, String str2, int i) {
        Method addMethod = addMethod(str, i);
        addMethod.setSignature(str2);
        return addMethod;
    }

    public Method getMethod(Method method) {
        String name = method.getName();
        Class[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        int length = parameterTypes.length;
        while (true) {
            length--;
            if (length < 0) {
                return addMethod(name, method.getModifiers(), typeArr, Type.make(method.getReturnType()));
            }
            typeArr[length] = Type.make(parameterTypes[length]);
        }
    }

    public final synchronized Method getDeclaredMethods() {
        if ((this.flags & 18) == 16) {
            addMethods(getReflectClass());
        }
        return this.methods;
    }

    public final int countMethods(Filter filter, int i) {
        Vector vector = new Vector();
        getMethods(filter, i, vector);
        return vector.size();
    }

    public Method[] getMethods(Filter filter, boolean z) {
        return getMethods(filter, z ? 1 : 0);
    }

    public Method[] getMethods(Filter filter, int i) {
        Vector vector = new Vector();
        getMethods(filter, i, vector);
        int size = vector.size();
        Method[] methodArr = new Method[size];
        for (int i2 = 0; i2 < size; i2++) {
            methodArr[i2] = (Method) vector.elementAt(i2);
        }
        return methodArr;
    }

    public int getMethods(Filter filter, int i, Method[] methodArr, int i2) {
        Vector vector = new Vector();
        getMethods(filter, i, vector);
        int size = vector.size();
        for (int i3 = 0; i3 < size; i3++) {
            methodArr[i2 + i3] = (Method) vector.elementAt(i3);
        }
        return size;
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        ClassType[] interfaces2;
        String str = null;
        int i2 = 0;
        ClassType classType = this;
        while (classType != null) {
            String packageName = classType.getPackageName();
            for (Method declaredMethods = classType.getDeclaredMethods(); declaredMethods != null; declaredMethods = declaredMethods.getNext()) {
                if (classType != this) {
                    int modifiers = declaredMethods.getModifiers();
                    if ((modifiers & 2) == 0) {
                        if ((modifiers & 5) == 0 && !packageName.equals(str)) {
                        }
                    }
                }
                if (filter.select(declaredMethods)) {
                    if (list != null) {
                        list.add(declaredMethods);
                    }
                    i2++;
                }
            }
            if (i == 0) {
                break;
            }
            if (i > 1 && (interfaces2 = classType.getInterfaces()) != null) {
                for (ClassType methods2 : interfaces2) {
                    i2 += methods2.getMethods(filter, i, list);
                }
            }
            classType = classType.getSuperclass();
            str = packageName;
        }
        return i2;
    }

    static class AbstractMethodFilter implements Filter {
        public static final AbstractMethodFilter instance = new AbstractMethodFilter();

        AbstractMethodFilter() {
        }

        public boolean select(Object obj) {
            return ((Method) obj).isAbstract();
        }
    }

    public Method[] getAbstractMethods() {
        return getMethods((Filter) AbstractMethodFilter.instance, 2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0052 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0053 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.Method getDeclaredMethod(java.lang.String r7, gnu.bytecode.Type[] r8) {
        /*
            r6 = this;
            java.lang.String r0 = "<init>"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0010
            boolean r0 = r6.hasOuterLink()
            if (r0 == 0) goto L_0x0010
            r0 = 1
            goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            gnu.bytecode.Method r1 = r6.getDeclaredMethods()
        L_0x0015:
            if (r1 == 0) goto L_0x0057
            java.lang.String r2 = r1.getName()
            boolean r2 = r7.equals(r2)
            if (r2 != 0) goto L_0x0022
            goto L_0x0053
        L_0x0022:
            gnu.bytecode.Type[] r2 = r1.getParameterTypes()
            if (r8 == 0) goto L_0x0056
            if (r8 != r2) goto L_0x002d
            if (r0 != 0) goto L_0x002d
            goto L_0x0056
        L_0x002d:
            int r3 = r8.length
            int r4 = r2.length
            int r4 = r4 - r0
            if (r3 == r4) goto L_0x0033
            goto L_0x0053
        L_0x0033:
            int r3 = r3 + -1
            if (r3 < 0) goto L_0x0050
            int r4 = r3 + r0
            r4 = r2[r4]
            r5 = r8[r3]
            if (r4 == r5) goto L_0x0033
            if (r5 != 0) goto L_0x0042
            goto L_0x0033
        L_0x0042:
            java.lang.String r4 = r4.getSignature()
            java.lang.String r5 = r5.getSignature()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0033
        L_0x0050:
            if (r3 >= 0) goto L_0x0053
            return r1
        L_0x0053:
            gnu.bytecode.Method r1 = r1.next
            goto L_0x0015
        L_0x0056:
            return r1
        L_0x0057:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.getDeclaredMethod(java.lang.String, gnu.bytecode.Type[]):gnu.bytecode.Method");
    }

    public synchronized Method getDeclaredMethod(String str, int i) {
        Method method;
        method = null;
        int i2 = (!"<init>".equals(str) || !hasOuterLink()) ? 0 : 1;
        for (Method declaredMethods = getDeclaredMethods(); declaredMethods != null; declaredMethods = declaredMethods.next) {
            if (str.equals(declaredMethods.getName()) && i + i2 == declaredMethods.getParameterTypes().length) {
                if (method == null) {
                    method = declaredMethods;
                } else {
                    throw new Error("ambiguous call to getDeclaredMethod(\"" + str + "\", " + i + ")\n - " + method + "\n - " + declaredMethods);
                }
            }
        }
        return method;
    }

    public synchronized Method getMethod(String str, Type[] typeArr) {
        ClassType classType = this;
        do {
            Method declaredMethod = classType.getDeclaredMethod(str, typeArr);
            if (declaredMethod != null) {
                return declaredMethod;
            }
            classType = classType.getSuperclass();
        } while (classType != null);
        ClassType classType2 = this;
        do {
            ClassType[] interfaces2 = classType2.getInterfaces();
            if (interfaces2 != null) {
                for (ClassType declaredMethod2 : interfaces2) {
                    Method declaredMethod3 = declaredMethod2.getDeclaredMethod(str, typeArr);
                    if (declaredMethod3 != null) {
                        return declaredMethod3;
                    }
                }
            }
            classType2 = classType2.getSuperclass();
        } while (classType2 != null);
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = r7.getConstructors();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r0 = r7.getMethods();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addMethods(java.lang.Class r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r6.flags     // Catch:{ all -> 0x0049 }
            r0 = r0 | 2
            r6.flags = r0     // Catch:{ all -> 0x0049 }
            java.lang.reflect.Method[] r0 = r7.getDeclaredMethods()     // Catch:{ SecurityException -> 0x000c }
            goto L_0x0010
        L_0x000c:
            java.lang.reflect.Method[] r0 = r7.getMethods()     // Catch:{ all -> 0x0049 }
        L_0x0010:
            int r1 = r0.length     // Catch:{ all -> 0x0049 }
            r2 = 0
            r3 = 0
        L_0x0013:
            if (r3 >= r1) goto L_0x0028
            r4 = r0[r3]     // Catch:{ all -> 0x0049 }
            java.lang.Class r5 = r4.getDeclaringClass()     // Catch:{ all -> 0x0049 }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0049 }
            if (r5 != 0) goto L_0x0022
            goto L_0x0025
        L_0x0022:
            r6.addMethod((java.lang.reflect.Method) r4)     // Catch:{ all -> 0x0049 }
        L_0x0025:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0028:
            java.lang.reflect.Constructor[] r0 = r7.getDeclaredConstructors()     // Catch:{ SecurityException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            java.lang.reflect.Constructor[] r0 = r7.getConstructors()     // Catch:{ all -> 0x0049 }
        L_0x0031:
            int r1 = r0.length     // Catch:{ all -> 0x0049 }
        L_0x0032:
            if (r2 >= r1) goto L_0x0047
            r3 = r0[r2]     // Catch:{ all -> 0x0049 }
            java.lang.Class r4 = r3.getDeclaringClass()     // Catch:{ all -> 0x0049 }
            boolean r4 = r4.equals(r7)     // Catch:{ all -> 0x0049 }
            if (r4 != 0) goto L_0x0041
            goto L_0x0044
        L_0x0041:
            r6.addMethod((java.lang.reflect.Constructor) r3)     // Catch:{ all -> 0x0049 }
        L_0x0044:
            int r2 = r2 + 1
            goto L_0x0032
        L_0x0047:
            monitor-exit(r6)
            return
        L_0x0049:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ClassType.addMethods(java.lang.Class):void");
    }

    public Method[] getMatchingMethods(String str, Type[] typeArr, int i) {
        Vector vector = new Vector(10);
        int i2 = 0;
        for (Method method = this.methods; method != null; method = method.getNext()) {
            if (str.equals(method.getName()) && (i & 8) == (method.access_flags & 8) && (i & 1) <= (method.access_flags & 1) && method.arg_types.length == typeArr.length) {
                i2++;
                vector.addElement(method);
            }
        }
        Method[] methodArr = new Method[i2];
        vector.copyInto(methodArr);
        return methodArr;
    }

    public void doFixups() {
        if (this.constants == null) {
            this.constants = new ConstantPool();
        }
        if (this.thisClassIndex == 0) {
            this.thisClassIndex = this.constants.addClass((ObjectType) this).index;
        }
        if (this.superClass == this) {
            ClassType classType = null;
            setSuper((ClassType) null);
        }
        if (this.superClassIndex < 0) {
            ClassType classType2 = this.superClass;
            this.superClassIndex = classType2 == null ? 0 : this.constants.addClass((ObjectType) classType2).index;
        }
        ClassType[] classTypeArr = this.interfaces;
        if (classTypeArr != null && this.interfaceIndexes == null) {
            int length = classTypeArr.length;
            this.interfaceIndexes = new int[length];
            for (int i = 0; i < length; i++) {
                this.interfaceIndexes[i] = this.constants.addClass((ObjectType) this.interfaces[i]).index;
            }
        }
        for (Field field = this.fields; field != null; field = field.next) {
            field.assign_constants(this);
        }
        for (Method method = this.methods; method != null; method = method.next) {
            method.assignConstants();
        }
        Member member = this.enclosingMember;
        if (member instanceof Method) {
            EnclosingMethodAttr firstEnclosingMethod = EnclosingMethodAttr.getFirstEnclosingMethod(getAttributes());
            if (firstEnclosingMethod == null) {
                firstEnclosingMethod = new EnclosingMethodAttr(this);
            }
            firstEnclosingMethod.method = (Method) this.enclosingMember;
        } else if (member instanceof ClassType) {
            this.constants.addClass((ObjectType) (ClassType) member);
        }
        for (ClassType classType3 = this.firstInnerClass; classType3 != null; classType3 = classType3.nextInnerClass) {
            this.constants.addClass((ObjectType) classType3);
        }
        InnerClassesAttr firstInnerClasses = InnerClassesAttr.getFirstInnerClasses(getAttributes());
        if (firstInnerClasses != null) {
            firstInnerClasses.setSkipped(true);
        }
        Attribute.assignConstants(this, this);
        for (int i2 = 1; i2 <= this.constants.count; i2++) {
            CpoolEntry cpoolEntry = this.constants.pool[i2];
            if (cpoolEntry instanceof CpoolClass) {
                CpoolClass cpoolClass = (CpoolClass) cpoolEntry;
                if ((cpoolClass.clas instanceof ClassType) && ((ClassType) cpoolClass.clas).getEnclosingMember() != null) {
                    if (firstInnerClasses == null) {
                        firstInnerClasses = new InnerClassesAttr(this);
                    }
                    firstInnerClasses.addClass(cpoolClass, this);
                }
            }
        }
        if (firstInnerClasses != null) {
            firstInnerClasses.setSkipped(false);
            firstInnerClasses.assignConstants(this);
        }
    }

    public void writeToStream(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        doFixups();
        dataOutputStream.writeInt(-889275714);
        dataOutputStream.writeShort(getClassfileMinorVersion());
        dataOutputStream.writeShort(getClassfileMajorVersion());
        ConstantPool constantPool = this.constants;
        if (constantPool == null) {
            dataOutputStream.writeShort(1);
        } else {
            constantPool.write(dataOutputStream);
        }
        dataOutputStream.writeShort(this.access_flags);
        dataOutputStream.writeShort(this.thisClassIndex);
        dataOutputStream.writeShort(this.superClassIndex);
        int[] iArr = this.interfaceIndexes;
        if (iArr == null) {
            dataOutputStream.writeShort(0);
        } else {
            int length = iArr.length;
            dataOutputStream.writeShort(length);
            for (int i = 0; i < length; i++) {
                dataOutputStream.writeShort(this.interfaceIndexes[i]);
            }
        }
        dataOutputStream.writeShort(this.fields_count);
        for (Field field = this.fields; field != null; field = field.next) {
            field.write(dataOutputStream, this);
        }
        dataOutputStream.writeShort(this.methods_count);
        for (Method method = this.methods; method != null; method = method.next) {
            method.write(dataOutputStream, this);
        }
        Attribute.writeAll(this, dataOutputStream);
        this.flags |= 3;
    }

    public void writeToFile(String str) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
        writeToStream(bufferedOutputStream);
        bufferedOutputStream.close();
    }

    public void writeToFile() throws IOException {
        writeToFile(this.this_name.replace('.', File.separatorChar) + ".class");
    }

    public byte[] writeToArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(500);
        try {
            writeToStream(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new InternalError(e.toString());
        }
    }

    public static byte[] to_utf8(String str) {
        int i;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            i2 = (charAt <= 0 || charAt > 127) ? charAt <= 2047 ? i2 + 2 : i2 + 3 : i2 + 1;
        }
        byte[] bArr = new byte[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            char charAt2 = str.charAt(i5);
            if (charAt2 > 0 && charAt2 <= 127) {
                i = i4 + 1;
                bArr[i4] = (byte) charAt2;
            } else if (charAt2 <= 2047) {
                int i6 = i4 + 1;
                bArr[i4] = (byte) (((charAt2 >> 6) & 31) | FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE);
                i4 = i6 + 1;
                bArr[i6] = (byte) (((charAt2 >> 0) & 63) | 128);
            } else {
                int i7 = i4 + 1;
                bArr[i4] = (byte) (((charAt2 >> 12) & 15) | 224);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (((charAt2 >> 6) & 63) | 128);
                i = i8 + 1;
                bArr[i8] = (byte) (((charAt2 >> 0) & 63) | 128);
            }
            i4 = i;
        }
        return bArr;
    }

    public final boolean implementsInterface(ClassType classType) {
        if (this == classType) {
            return true;
        }
        ClassType superclass = getSuperclass();
        if (superclass != null && superclass.implementsInterface(classType)) {
            return true;
        }
        ClassType[] interfaces2 = getInterfaces();
        if (interfaces2 == null) {
            return false;
        }
        int length = interfaces2.length;
        do {
            length--;
            if (length < 0) {
                return false;
            }
        } while (!interfaces2[length].implementsInterface(classType));
        return true;
    }

    public final boolean isSubclass(String str) {
        ClassType classType = this;
        while (!str.equals(classType.getName())) {
            classType = classType.getSuperclass();
            if (classType == null) {
                return false;
            }
        }
        return true;
    }

    public final boolean isSubclass(ClassType classType) {
        if (classType.isInterface()) {
            return implementsInterface(classType);
        }
        if ((this == toStringType && classType == javalangStringType) || (this == javalangStringType && classType == toStringType)) {
            return true;
        }
        for (ClassType classType2 = this; classType2 != null; classType2 = classType2.getSuperclass()) {
            if (classType2 == classType) {
                return true;
            }
        }
        return false;
    }

    public int compare(Type type) {
        if (type == nullType) {
            return 1;
        }
        if (!(type instanceof ClassType)) {
            return swappedCompareResult(type.compare(this));
        }
        String name = getName();
        if (name != null && name.equals(type.getName())) {
            return 0;
        }
        ClassType classType = (ClassType) type;
        if (isSubclass(classType)) {
            return -1;
        }
        if (classType.isSubclass(this)) {
            return 1;
        }
        if (this == toStringType) {
            if (classType == Type.javalangObjectType) {
                return -1;
            }
            return 1;
        } else if (classType == toStringType) {
            if (this == Type.javalangObjectType) {
                return 1;
            }
            return -1;
        } else if (isInterface()) {
            if (classType == Type.javalangObjectType) {
                return -1;
            }
            return -2;
        } else if (!classType.isInterface()) {
            return -3;
        } else {
            if (this == Type.javalangObjectType) {
                return 1;
            }
            return -2;
        }
    }

    public String toString() {
        return "ClassType " + getName();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(getName());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName(objectInput.readUTF());
        this.flags |= 16;
    }

    public Object readResolve() throws ObjectStreamException {
        String name = getName();
        HashMap hashMap = mapNameToType;
        synchronized (hashMap) {
            Type type = (Type) hashMap.get(name);
            if (type != null) {
                return type;
            }
            hashMap.put(name, this);
            return this;
        }
    }

    public void cleanupAfterCompilation() {
        for (Method method = this.methods; method != null; method = method.getNext()) {
            method.cleanupAfterCompilation();
        }
        this.constants = null;
        this.attributes = null;
        this.sourceDbgExt = null;
    }

    public Method checkSingleAbstractMethod() {
        Method method = null;
        for (Method method2 : getAbstractMethods()) {
            Method method3 = getMethod(method2.getName(), method2.getParameterTypes());
            if (method3 == null || method3.isAbstract()) {
                if (method != null) {
                    return null;
                }
                method = method2;
            }
        }
        return method;
    }
}
