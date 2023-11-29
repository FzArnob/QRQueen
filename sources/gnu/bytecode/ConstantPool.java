package gnu.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantPool {
    public static final byte CLASS = 7;
    public static final byte DOUBLE = 6;
    public static final byte FIELDREF = 9;
    public static final byte FLOAT = 4;
    public static final byte INTEGER = 3;
    public static final byte INTERFACE_METHODREF = 11;
    public static final byte LONG = 5;
    public static final byte METHODREF = 10;
    public static final byte NAME_AND_TYPE = 12;
    public static final byte STRING = 8;
    public static final byte UTF8 = 1;
    int count;
    CpoolEntry[] hashTab;
    boolean locked;
    CpoolEntry[] pool;

    public final int getCount() {
        return this.count;
    }

    public final CpoolEntry getPoolEntry(int i) {
        return this.pool[i];
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        if (this.hashTab == null && this.count > 0) {
            int length = this.pool.length;
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                CpoolEntry cpoolEntry = this.pool[length];
                if (cpoolEntry != null) {
                    cpoolEntry.hashCode();
                }
            }
        }
        int i = this.count;
        this.hashTab = new CpoolEntry[(i < 5 ? 101 : i * 2)];
        CpoolEntry[] cpoolEntryArr = this.pool;
        if (cpoolEntryArr != null) {
            int length2 = cpoolEntryArr.length;
            while (true) {
                length2--;
                if (length2 >= 0) {
                    CpoolEntry cpoolEntry2 = this.pool[length2];
                    if (cpoolEntry2 != null) {
                        cpoolEntry2.add_hashed(this);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public CpoolUtf8 addUtf8(String str) {
        String intern = str.intern();
        int hashCode = intern.hashCode();
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolUtf8)) {
                CpoolUtf8 cpoolUtf8 = (CpoolUtf8) cpoolEntry;
                if (cpoolUtf8.string == intern) {
                    return cpoolUtf8;
                }
            }
        }
        if (!this.locked) {
            return new CpoolUtf8(this, hashCode, intern);
        }
        throw new Error("adding new Utf8 entry to locked contant pool: " + intern);
    }

    public CpoolClass addClass(ObjectType objectType) {
        CpoolClass addClass = addClass(addUtf8(objectType.getInternalName()));
        addClass.clas = objectType;
        return addClass;
    }

    public CpoolClass addClass(CpoolUtf8 cpoolUtf8) {
        int hashCode = CpoolClass.hashCode(cpoolUtf8);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolClass)) {
                CpoolClass cpoolClass = (CpoolClass) cpoolEntry;
                if (cpoolClass.name == cpoolUtf8) {
                    return cpoolClass;
                }
            }
        }
        return new CpoolClass(this, hashCode, cpoolUtf8);
    }

    /* access modifiers changed from: package-private */
    public CpoolValue1 addValue1(int i, int i2) {
        int hashCode = CpoolValue1.hashCode(i2);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolValue1)) {
                CpoolValue1 cpoolValue1 = (CpoolValue1) cpoolEntry;
                if (cpoolValue1.tag == i && cpoolValue1.value == i2) {
                    return cpoolValue1;
                }
            }
        }
        return new CpoolValue1(this, i, hashCode, i2);
    }

    /* access modifiers changed from: package-private */
    public CpoolValue2 addValue2(int i, long j) {
        int hashCode = CpoolValue2.hashCode(j);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolValue2)) {
                CpoolValue2 cpoolValue2 = (CpoolValue2) cpoolEntry;
                if (cpoolValue2.tag == i && cpoolValue2.value == j) {
                    return cpoolValue2;
                }
            }
        }
        return new CpoolValue2(this, i, hashCode, j);
    }

    public CpoolValue1 addInt(int i) {
        return addValue1(3, i);
    }

    public CpoolValue2 addLong(long j) {
        return addValue2(5, j);
    }

    public CpoolValue1 addFloat(float f) {
        return addValue1(4, Float.floatToIntBits(f));
    }

    public CpoolValue2 addDouble(double d) {
        return addValue2(6, Double.doubleToLongBits(d));
    }

    public final CpoolString addString(String str) {
        return addString(addUtf8(str));
    }

    public CpoolString addString(CpoolUtf8 cpoolUtf8) {
        int hashCode = CpoolString.hashCode(cpoolUtf8);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolString)) {
                CpoolString cpoolString = (CpoolString) cpoolEntry;
                if (cpoolString.str == cpoolUtf8) {
                    return cpoolString;
                }
            }
        }
        return new CpoolString(this, hashCode, cpoolUtf8);
    }

    public CpoolNameAndType addNameAndType(Method method) {
        return addNameAndType(addUtf8(method.getName()), addUtf8(method.getSignature()));
    }

    public CpoolNameAndType addNameAndType(Field field) {
        return addNameAndType(addUtf8(field.getName()), addUtf8(field.getSignature()));
    }

    public CpoolNameAndType addNameAndType(CpoolUtf8 cpoolUtf8, CpoolUtf8 cpoolUtf82) {
        int hashCode = CpoolNameAndType.hashCode(cpoolUtf8, cpoolUtf82);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolNameAndType)) {
                CpoolNameAndType cpoolNameAndType = (CpoolNameAndType) cpoolEntry;
                if (cpoolNameAndType.name == cpoolUtf8 && cpoolNameAndType.type == cpoolUtf82) {
                    return cpoolNameAndType;
                }
            }
        }
        return new CpoolNameAndType(this, hashCode, cpoolUtf8, cpoolUtf82);
    }

    public CpoolRef addRef(int i, CpoolClass cpoolClass, CpoolNameAndType cpoolNameAndType) {
        int hashCode = CpoolRef.hashCode(cpoolClass, cpoolNameAndType);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry[] cpoolEntryArr = this.hashTab;
        for (CpoolEntry cpoolEntry = cpoolEntryArr[(Integer.MAX_VALUE & hashCode) % cpoolEntryArr.length]; cpoolEntry != null; cpoolEntry = cpoolEntry.next) {
            if (hashCode == cpoolEntry.hash && (cpoolEntry instanceof CpoolRef)) {
                CpoolRef cpoolRef = (CpoolRef) cpoolEntry;
                if (cpoolRef.tag == i && cpoolRef.clas == cpoolClass && cpoolRef.nameAndType == cpoolNameAndType) {
                    return cpoolRef;
                }
            }
        }
        return new CpoolRef(this, hashCode, i, cpoolClass, cpoolNameAndType);
    }

    public CpoolRef addMethodRef(Method method) {
        return addRef((method.getDeclaringClass().getModifiers() & 512) == 0 ? 10 : 11, addClass((ObjectType) method.classfile), addNameAndType(method));
    }

    public CpoolRef addFieldRef(Field field) {
        return addRef(9, addClass((ObjectType) field.owner), addNameAndType(field));
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.count + 1);
        for (int i = 1; i <= this.count; i++) {
            CpoolEntry cpoolEntry = this.pool[i];
            if (cpoolEntry != null) {
                cpoolEntry.write(dataOutputStream);
            }
        }
        this.locked = true;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r2.pool[r3] = r0;
        r0.index = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.CpoolEntry getForced(int r3, int r4) {
        /*
            r2 = this;
            r0 = 65535(0xffff, float:9.1834E-41)
            r3 = r3 & r0
            gnu.bytecode.CpoolEntry[] r0 = r2.pool
            r0 = r0[r3]
            if (r0 != 0) goto L_0x004b
            boolean r1 = r2.locked
            if (r1 != 0) goto L_0x0043
            switch(r4) {
                case 1: goto L_0x0036;
                case 2: goto L_0x0011;
                case 3: goto L_0x0030;
                case 4: goto L_0x0030;
                case 5: goto L_0x002a;
                case 6: goto L_0x002a;
                case 7: goto L_0x0024;
                case 8: goto L_0x001e;
                case 9: goto L_0x0018;
                case 10: goto L_0x0018;
                case 11: goto L_0x0018;
                case 12: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x003c
        L_0x0012:
            gnu.bytecode.CpoolNameAndType r4 = new gnu.bytecode.CpoolNameAndType
            r4.<init>()
            goto L_0x003b
        L_0x0018:
            gnu.bytecode.CpoolRef r0 = new gnu.bytecode.CpoolRef
            r0.<init>(r4)
            goto L_0x003c
        L_0x001e:
            gnu.bytecode.CpoolString r4 = new gnu.bytecode.CpoolString
            r4.<init>()
            goto L_0x003b
        L_0x0024:
            gnu.bytecode.CpoolClass r4 = new gnu.bytecode.CpoolClass
            r4.<init>()
            goto L_0x003b
        L_0x002a:
            gnu.bytecode.CpoolValue2 r0 = new gnu.bytecode.CpoolValue2
            r0.<init>(r4)
            goto L_0x003c
        L_0x0030:
            gnu.bytecode.CpoolValue1 r0 = new gnu.bytecode.CpoolValue1
            r0.<init>(r4)
            goto L_0x003c
        L_0x0036:
            gnu.bytecode.CpoolUtf8 r4 = new gnu.bytecode.CpoolUtf8
            r4.<init>()
        L_0x003b:
            r0 = r4
        L_0x003c:
            gnu.bytecode.CpoolEntry[] r4 = r2.pool
            r4[r3] = r0
            r0.index = r3
            goto L_0x0051
        L_0x0043:
            java.lang.Error r3 = new java.lang.Error
            java.lang.String r4 = "adding new entry to locked contant pool"
            r3.<init>(r4)
            throw r3
        L_0x004b:
            int r1 = r0.getTag()
            if (r1 != r4) goto L_0x0052
        L_0x0051:
            return r0
        L_0x0052:
            java.lang.ClassFormatError r4 = new java.lang.ClassFormatError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "conflicting constant pool tags at "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.ConstantPool.getForced(int, int):gnu.bytecode.CpoolEntry");
    }

    /* access modifiers changed from: package-private */
    public CpoolClass getForcedClass(int i) {
        return (CpoolClass) getForced(i, 7);
    }

    public ConstantPool() {
    }

    public ConstantPool(DataInputStream dataInputStream) throws IOException {
        int readUnsignedShort = dataInputStream.readUnsignedShort() - 1;
        this.count = readUnsignedShort;
        this.pool = new CpoolEntry[(readUnsignedShort + 1)];
        int i = 1;
        while (i <= this.count) {
            byte readByte = dataInputStream.readByte();
            CpoolEntry forced = getForced(i, readByte);
            switch (readByte) {
                case 1:
                    ((CpoolUtf8) forced).string = dataInputStream.readUTF();
                    break;
                case 3:
                case 4:
                    ((CpoolValue1) forced).value = dataInputStream.readInt();
                    break;
                case 5:
                case 6:
                    ((CpoolValue2) forced).value = dataInputStream.readLong();
                    i++;
                    break;
                case 7:
                    ((CpoolClass) forced).name = (CpoolUtf8) getForced(dataInputStream.readUnsignedShort(), 1);
                    break;
                case 8:
                    ((CpoolString) forced).str = (CpoolUtf8) getForced(dataInputStream.readUnsignedShort(), 1);
                    break;
                case 9:
                case 10:
                case 11:
                    CpoolRef cpoolRef = (CpoolRef) forced;
                    cpoolRef.clas = getForcedClass(dataInputStream.readUnsignedShort());
                    cpoolRef.nameAndType = (CpoolNameAndType) getForced(dataInputStream.readUnsignedShort(), 12);
                    break;
                case 12:
                    CpoolNameAndType cpoolNameAndType = (CpoolNameAndType) forced;
                    cpoolNameAndType.name = (CpoolUtf8) getForced(dataInputStream.readUnsignedShort(), 1);
                    cpoolNameAndType.type = (CpoolUtf8) getForced(dataInputStream.readUnsignedShort(), 1);
                    break;
            }
            i++;
        }
    }
}
