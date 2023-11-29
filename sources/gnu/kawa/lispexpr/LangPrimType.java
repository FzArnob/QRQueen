package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Char;

public class LangPrimType extends PrimType implements TypeValue {
    public static final PrimType byteType = Type.byteType;
    public static final LangPrimType charType = new LangPrimType(Type.charType);
    public static final PrimType doubleType = Type.doubleType;
    public static final PrimType floatType = Type.floatType;
    public static final PrimType intType = Type.intType;
    public static final PrimType longType = Type.longType;
    public static final PrimType shortType = Type.shortType;
    public static final LangPrimType voidType = new LangPrimType(Type.voidType);
    PrimType implementationType;
    Language language;

    public Expression convertValue(Expression expression) {
        return null;
    }

    public Procedure getConstructor() {
        return null;
    }

    public LangPrimType(PrimType primType) {
        super(primType);
        this.implementationType = primType;
    }

    public LangPrimType(PrimType primType, Language language2) {
        super(primType);
        this.language = language2;
        this.implementationType = primType;
    }

    public LangPrimType(String str, String str2, int i, Class cls) {
        super(str, str2, i, cls);
    }

    public LangPrimType(String str, String str2, int i, Class cls, Language language2) {
        this(str, str2, i, cls);
        this.implementationType = Type.signatureToPrimitive(str2.charAt(0));
        this.language = language2;
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public Object coerceFromObject(Object obj) {
        if (obj.getClass() == this.reflectClass) {
            return obj;
        }
        char charAt = getSignature().charAt(0);
        if (charAt == 'C') {
            return new Character(((Char) obj).charValue());
        }
        if (charAt == 'V') {
            return Values.empty;
        }
        if (charAt != 'Z') {
            return super.coerceFromObject(obj);
        }
        return this.language.isTrue(obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    public char charValue(Object obj) {
        if (obj instanceof Character) {
            return ((Character) obj).charValue();
        }
        return ((Char) obj).charValue();
    }

    public void emitIsInstance(CodeAttr codeAttr) {
        char charAt = getSignature().charAt(0);
        if (charAt == 'C') {
            codeAttr.emitInstanceof(ClassType.make("gnu.text.Char"));
        } else if (charAt != 'Z') {
            super.emitIsInstance(codeAttr);
        } else {
            codeAttr.emitPop(1);
            codeAttr.emitPushInt(1);
        }
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        char charAt = getSignature().charAt(0);
        if (charAt == 'C') {
            ClassType make = ClassType.make("gnu.text.Char");
            Method declaredMethod = make.getDeclaredMethod("charValue", 0);
            codeAttr.emitCheckcast(make);
            codeAttr.emitInvokeVirtual(declaredMethod);
        } else if (charAt != 'Z') {
            super.emitCoerceFromObject(codeAttr);
        } else {
            this.language.emitCoerceToBoolean(codeAttr);
        }
    }

    public Object coerceToObject(Object obj) {
        char charAt = getSignature().charAt(0);
        if (charAt != 'C') {
            if (charAt == 'V') {
                return Values.empty;
            }
            if (charAt != 'Z') {
                return super.coerceToObject(obj);
            }
            return this.language.booleanObject(((Boolean) obj).booleanValue());
        } else if (obj instanceof Char) {
            return obj;
        } else {
            return Char.make(((Character) obj).charValue());
        }
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        char charAt = getSignature().charAt(0);
        if (charAt == 'C') {
            codeAttr.emitInvokeStatic(ClassType.make("gnu.text.Char").getDeclaredMethod("make", 1));
        } else if (charAt != 'Z') {
            super.emitCoerceToObject(codeAttr);
        } else {
            codeAttr.emitIfIntNotZero();
            this.language.emitPushBoolean(true, codeAttr);
            codeAttr.emitElse();
            this.language.emitPushBoolean(false, codeAttr);
            codeAttr.emitFi();
        }
    }

    public int compare(Type type) {
        char charAt = getSignature().charAt(0);
        if (type instanceof PrimType) {
            char charAt2 = type.getSignature().charAt(0);
            if (charAt == charAt2) {
                return 0;
            }
            if (charAt == 'V') {
                return 1;
            }
            if (charAt2 == 'V' || charAt2 == 'Z') {
                return -1;
            }
        }
        if (charAt == 'V' || charAt == 'Z') {
            return 1;
        }
        if (charAt == 'C' && type.getName().equals("gnu.text.Char")) {
            return -1;
        }
        if (type instanceof LangObjType) {
            return swappedCompareResult(type.compare(this));
        }
        return super.compare(type);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        getSignature().charAt(0);
        CodeAttr code = compilation.getCode();
        if (variable != null) {
            code.emitLoad(variable);
        }
        if (declaration != null) {
            code.emitDup();
            declaration.compileStore(compilation);
        }
        emitIsInstance(code);
        code.emitIfIntNotZero();
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }
}
