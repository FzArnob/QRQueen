package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import kawa.lang.Syntax;

public class FieldLocation extends ClassMemberLocation {
    static final int CONSTANT = 4;
    static final int INDIRECT_LOCATION = 2;
    public static final int KIND_FLAGS_SET = 64;
    public static final int PROCEDURE = 16;
    static final int SETUP_DONE = 1;
    public static final int SYNTAX = 32;
    static final int VALUE_SET = 8;
    Declaration decl;
    private int flags;
    Object value;

    public boolean isIndirectLocation() {
        return (this.flags & 2) != 0;
    }

    public void setProcedure() {
        this.flags |= 84;
    }

    public void setSyntax() {
        this.flags |= 100;
    }

    /* access modifiers changed from: package-private */
    public void setKindFlags() {
        Field declaredField = getDeclaringClass().getDeclaredField(getMemberName());
        int modifiers = declaredField.getModifiers();
        Type type = declaredField.getType();
        if (type.isSubtype(Compilation.typeLocation)) {
            this.flags |= 2;
        }
        if ((modifiers & 16) != 0) {
            int i = this.flags;
            if ((i & 2) == 0) {
                this.flags = i | 4;
                if (type.isSubtype(Compilation.typeProcedure)) {
                    this.flags |= 16;
                }
                if ((type instanceof ClassType) && ((ClassType) type).isSubclass("kawa.lang.Syntax")) {
                    this.flags |= 32;
                }
            } else {
                Location location = (Location) getFieldValue();
                if (location instanceof FieldLocation) {
                    FieldLocation fieldLocation = (FieldLocation) location;
                    if ((fieldLocation.flags & 64) == 0) {
                        fieldLocation.setKindFlags();
                    }
                    int i2 = this.flags | (fieldLocation.flags & 52);
                    this.flags = i2;
                    int i3 = fieldLocation.flags;
                    if ((i3 & 4) == 0) {
                        this.value = fieldLocation;
                        this.flags = i2 | 8;
                    } else if ((i3 & 8) != 0) {
                        this.value = fieldLocation.value;
                        this.flags = i2 | 8;
                    }
                } else if (location.isConstant()) {
                    Object obj = location.get((Object) null);
                    if (obj instanceof Procedure) {
                        this.flags |= 16;
                    }
                    if (obj instanceof Syntax) {
                        this.flags |= 32;
                    }
                    this.flags |= 12;
                    this.value = obj;
                }
            }
        }
        this.flags |= 64;
    }

    public boolean isProcedureOrSyntax() {
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        return (this.flags & 48) != 0;
    }

    public FieldLocation(Object obj, String str, String str2) {
        super(obj, ClassType.make(str), str2);
    }

    public FieldLocation(Object obj, ClassType classType, String str) {
        super(obj, classType, str);
    }

    public FieldLocation(Object obj, java.lang.reflect.Field field) {
        super(obj, field);
        this.type = (ClassType) Type.make(field.getDeclaringClass());
    }

    public void setDeclaration(Declaration declaration) {
        this.decl = declaration;
    }

    public Field getField() {
        return this.type.getDeclaredField(this.mname);
    }

    public Type getFType() {
        return this.type.getDeclaredField(this.mname).getType();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized gnu.expr.Declaration getDeclaration() {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r3.flags     // Catch:{ all -> 0x0062 }
            r0 = r0 & 64
            if (r0 != 0) goto L_0x000a
            r3.setKindFlags()     // Catch:{ all -> 0x0062 }
        L_0x000a:
            gnu.expr.Declaration r0 = r3.decl     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x0060
            java.lang.String r0 = r3.getMemberName()     // Catch:{ all -> 0x0062 }
            gnu.bytecode.ClassType r1 = r3.getDeclaringClass()     // Catch:{ all -> 0x0062 }
            gnu.bytecode.Field r2 = r1.getDeclaredField(r0)     // Catch:{ all -> 0x0062 }
            if (r2 != 0) goto L_0x001f
            r0 = 0
            monitor-exit(r3)
            return r0
        L_0x001f:
            gnu.expr.ModuleInfo r1 = gnu.expr.ModuleInfo.find(r1)     // Catch:{ all -> 0x0062 }
            gnu.expr.ModuleExp r1 = r1.getModuleExp()     // Catch:{ all -> 0x0062 }
            gnu.expr.Declaration r1 = r1.firstDecl()     // Catch:{ all -> 0x0062 }
        L_0x002b:
            if (r1 == 0) goto L_0x0043
            gnu.bytecode.Field r2 = r1.field     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x003e
            gnu.bytecode.Field r2 = r1.field     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = r2.getName()     // Catch:{ all -> 0x0062 }
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x0062 }
            if (r2 == 0) goto L_0x003e
            goto L_0x0043
        L_0x003e:
            gnu.expr.Declaration r1 = r1.nextDecl()     // Catch:{ all -> 0x0062 }
            goto L_0x002b
        L_0x0043:
            if (r1 == 0) goto L_0x0049
            r3.decl = r1     // Catch:{ all -> 0x0062 }
            r0 = r1
            goto L_0x0060
        L_0x0049:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            r1.<init>()     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = "no field found for "
            r1.append(r2)     // Catch:{ all -> 0x0062 }
            r1.append(r3)     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0062 }
            r0.<init>(r1)     // Catch:{ all -> 0x0062 }
            throw r0     // Catch:{ all -> 0x0062 }
        L_0x0060:
            monitor-exit(r3)
            return r0
        L_0x0062:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.FieldLocation.getDeclaration():gnu.expr.Declaration");
    }

    /* access modifiers changed from: package-private */
    public void setup() {
        synchronized (this) {
            if ((this.flags & 1) == 0) {
                super.setup();
                if ((this.flags & 64) == 0) {
                    setKindFlags();
                }
                this.flags |= 1;
            }
        }
    }

    public Object get(Object obj) {
        Object obj2;
        try {
            setup();
            int i = this.flags;
            if ((i & 8) != 0) {
                obj2 = this.value;
                if ((i & 4) != 0) {
                    return obj2;
                }
            } else {
                obj2 = getFieldValue();
                if ((this.type.getDeclaredField(this.mname).getModifiers() & 16) != 0) {
                    int i2 = this.flags | 8;
                    this.flags = i2;
                    if ((i2 & 2) == 0) {
                        this.flags = i2 | 4;
                    }
                    this.value = obj2;
                }
            }
            if ((this.flags & 2) == 0) {
                return obj2;
            }
            String str = Location.UNBOUND;
            Location location = (Location) obj2;
            Object obj3 = location.get(str);
            if (obj3 == str) {
                return obj;
            }
            if (location.isConstant()) {
                this.flags |= 4;
                this.value = obj3;
            }
            return obj3;
        } catch (Throwable unused) {
            return obj;
        }
    }

    private Object getFieldValue() {
        super.setup();
        try {
            return this.rfield.get(this.instance);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public void set(Object obj) {
        Object obj2;
        setup();
        int i = this.flags;
        if ((i & 2) == 0) {
            try {
                this.rfield.set(this.instance, obj);
            } catch (Throwable th) {
                throw WrappedException.wrapIfNeeded(th);
            }
        } else {
            if ((i & 8) != 0) {
                obj2 = this.value;
            } else {
                this.flags = i | 8;
                obj2 = getFieldValue();
                this.value = obj2;
            }
            ((Location) obj2).set(obj);
        }
    }

    public Object setWithSave(Object obj) {
        Object obj2;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        int i = this.flags;
        if ((i & 2) == 0) {
            return super.setWithSave(obj);
        }
        if ((i & 8) != 0) {
            obj2 = this.value;
        } else {
            this.flags = i | 8;
            obj2 = getFieldValue();
            this.value = obj2;
        }
        return ((Location) obj2).setWithSave(obj);
    }

    public void setRestore(Object obj) {
        if ((this.flags & 2) == 0) {
            super.setRestore(obj);
        } else {
            ((Location) this.value).setRestore(obj);
        }
    }

    public boolean isConstant() {
        Object obj;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        if ((this.flags & 4) != 0) {
            return true;
        }
        if (isIndirectLocation()) {
            if ((this.flags & 8) != 0) {
                obj = this.value;
            } else {
                try {
                    setup();
                    obj = getFieldValue();
                    this.flags |= 8;
                    this.value = obj;
                } catch (Throwable unused) {
                }
            }
            return ((Location) obj).isConstant();
        }
        return false;
    }

    public boolean isBound() {
        Object obj;
        if ((this.flags & 64) == 0) {
            setKindFlags();
        }
        int i = this.flags;
        if ((i & 4) != 0 || (i & 2) == 0) {
            return true;
        }
        if ((i & 8) != 0) {
            obj = this.value;
        } else {
            try {
                setup();
                obj = getFieldValue();
                this.flags |= 8;
                this.value = obj;
            } catch (Throwable unused) {
                return false;
            }
        }
        return ((Location) obj).isBound();
    }

    public static FieldLocation make(Object obj, Declaration declaration) {
        Field field = declaration.field;
        FieldLocation fieldLocation = new FieldLocation(obj, field.getDeclaringClass(), field.getName());
        fieldLocation.setDeclaration(declaration);
        return fieldLocation;
    }

    public static FieldLocation make(Object obj, String str, String str2) {
        return new FieldLocation(obj, ClassType.make(str), str2);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FieldLocation[");
        if (this.instance != null) {
            stringBuffer.append(this.instance);
            stringBuffer.append(' ');
        }
        stringBuffer.append(this.type == null ? "(null)" : this.type.getName());
        stringBuffer.append('.');
        stringBuffer.append(this.mname);
        stringBuffer.append(']');
        return stringBuffer.toString();
    }
}
