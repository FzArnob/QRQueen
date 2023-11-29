package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

public abstract class ClassMemberLocation extends Location {
    Object instance;
    String mname;
    Field rfield;
    ClassType type;

    public final Object getInstance() {
        return this.instance;
    }

    public final void setInstance(Object obj) {
        this.instance = obj;
    }

    public ClassMemberLocation(Object obj, ClassType classType, String str) {
        this.instance = obj;
        this.type = classType;
        this.mname = str;
    }

    public ClassMemberLocation(Object obj, Class cls, String str) {
        this.instance = obj;
        this.type = (ClassType) Type.make(cls);
        this.mname = str;
    }

    public ClassMemberLocation(Object obj, Field field) {
        this.instance = obj;
        this.rfield = field;
        this.mname = field.getName();
    }

    public String getMemberName() {
        return this.mname;
    }

    public ClassType getDeclaringClass() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public void setup() {
        if (this.rfield == null) {
            try {
                try {
                    this.rfield = this.type.getReflectClass().getField(this.mname);
                } catch (NoSuchFieldException e) {
                    UnboundLocationException unboundLocationException = new UnboundLocationException((Object) null, "Unbound location  - no field " + this.mname + " in " + this.type.getName());
                    unboundLocationException.initCause(e);
                    throw unboundLocationException;
                }
            } catch (RuntimeException e2) {
                UnboundLocationException unboundLocationException2 = new UnboundLocationException((Object) null, "Unbound location - " + e2.toString());
                unboundLocationException2.initCause(e2);
                throw unboundLocationException2;
            }
        }
    }

    public Field getRField() {
        Field field = this.rfield;
        if (field != null) {
            return field;
        }
        try {
            Field field2 = this.type.getReflectClass().getField(this.mname);
            this.rfield = field2;
            return field2;
        } catch (Exception unused) {
            return null;
        }
    }

    public Class getRClass() {
        Field field = this.rfield;
        if (field != null) {
            return field.getDeclaringClass();
        }
        try {
            return this.type.getReflectClass();
        } catch (Exception unused) {
            return null;
        }
    }

    public Object get(Object obj) {
        Field rField = getRField();
        if (rField == null) {
            return obj;
        }
        try {
            return rField.get(this.instance);
        } catch (IllegalAccessException e) {
            throw WrappedException.wrapIfNeeded(e);
        }
    }

    public boolean isConstant() {
        return (getRField() == null || (this.rfield.getModifiers() & 16) == 0) ? false : true;
    }

    public boolean isBound() {
        return getRField() != null;
    }

    public void set(Object obj) {
        setup();
        try {
            this.rfield.set(this.instance, obj);
        } catch (IllegalAccessException e) {
            throw WrappedException.wrapIfNeeded(e);
        }
    }

    public static void define(Object obj, Field field, String str, Language language, Environment environment) throws IllegalAccessException {
        Symbol symbol;
        Location location;
        Object obj2 = field.get(obj);
        Type make = Type.make(field.getType());
        boolean isSubtype = make.isSubtype(Compilation.typeLocation);
        make.isSubtype(Compilation.typeProcedure);
        int modifiers = field.getModifiers();
        boolean z = false;
        boolean z2 = (modifiers & 16) != 0;
        Object demangleName = (!z2 || !(obj2 instanceof Named) || isSubtype) ? Compilation.demangleName(field.getName(), true) : ((Named) obj2).getSymbol();
        if (demangleName instanceof Symbol) {
            symbol = (Symbol) demangleName;
        } else {
            if (str == null) {
                str = "";
            }
            symbol = Symbol.make(str, demangleName.toString().intern());
        }
        Object obj3 = null;
        if (!isSubtype || !z2) {
            if (z2) {
                obj3 = language.getEnvPropertyFor(field, obj2);
            }
            if ((modifiers & 8) != 0) {
                z = true;
            }
            if (z) {
                location = new StaticFieldLocation(field);
            } else {
                location = new FieldLocation(obj, field);
            }
        } else {
            location = (Location) obj2;
        }
        environment.addLocation(symbol, obj3, location);
    }

    public static void defineAll(Object obj, Language language, Environment environment) throws IllegalAccessException {
        Field[] fields = obj.getClass().getFields();
        int length = fields.length;
        while (true) {
            length--;
            if (length >= 0) {
                Field field = fields[length];
                String name = field.getName();
                if (!name.startsWith(Declaration.PRIVATE_PREFIX) && !name.endsWith("$instance")) {
                    define(obj, field, (String) null, language, environment);
                }
            } else {
                return;
            }
        }
    }
}
