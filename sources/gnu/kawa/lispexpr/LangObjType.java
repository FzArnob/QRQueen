package gnu.kawa.lispexpr;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.functions.MakeList;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.util.List;

public class LangObjType extends ObjectType implements TypeValue {
    private static final int CLASSTYPE_TYPE_CODE = 6;
    private static final int CLASS_TYPE_CODE = 4;
    private static final int DFLONUM_TYPE_CODE = 15;
    private static final int FILEPATH_TYPE_CODE = 2;
    private static final int INTEGER_TYPE_CODE = 7;
    private static final int LIST_TYPE_CODE = 11;
    private static final int NUMERIC_TYPE_CODE = 10;
    private static final int PATH_TYPE_CODE = 1;
    private static final int RATIONAL_TYPE_CODE = 8;
    private static final int REAL_TYPE_CODE = 9;
    private static final int REGEX_TYPE_CODE = 14;
    private static final int STRING_TYPE_CODE = 13;
    private static final int TYPE_TYPE_CODE = 5;
    public static final LangObjType URIType = new LangObjType("URI", "gnu.text.URIPath", 3);
    private static final int URI_TYPE_CODE = 3;
    static final String VARARGS_SUFFIX = "";
    private static final int VECTOR_TYPE_CODE = 12;
    public static final LangObjType dflonumType = new LangObjType("DFloNum", "gnu.math.DFloNum", 15);
    public static final LangObjType filepathType = new LangObjType("filepath", "gnu.text.FilePath", 2);
    public static final LangObjType integerType = new LangObjType(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, "gnu.math.IntNum", 7);
    public static final LangObjType listType = new LangObjType("list", "gnu.lists.LList", 11);
    static PrimProcedure makeFilepathProc = new PrimProcedure("gnu.text.FilePath", "makeFilePath", 1);
    static PrimProcedure makePathProc = new PrimProcedure("gnu.text.Path", "valueOf", 1);
    static PrimProcedure makeURIProc = new PrimProcedure("gnu.text.URIPath", "makeURI", 1);
    public static final LangObjType numericType = new LangObjType("number", "gnu.math.Numeric", 10);
    public static final LangObjType pathType = new LangObjType("path", "gnu.text.Path", 1);
    public static final LangObjType rationalType = new LangObjType("rational", "gnu.math.RatNum", 8);
    public static final LangObjType realType = new LangObjType("real", "gnu.math.RealNum", 9);
    public static final LangObjType regexType = new LangObjType("regex", "java.util.regex.Pattern", 14);
    public static final LangObjType stringType = new LangObjType("string", "java.lang.CharSequence", 13);
    static final ClassType typeArithmetic = ClassType.make("gnu.kawa.functions.Arithmetic");
    public static final LangObjType typeClass = new LangObjType("class", "java.lang.Class", 4);
    public static final LangObjType typeClassType = new LangObjType("class-type", "gnu.bytecode.ClassType", 6);
    public static final ClassType typeLangObjType = ClassType.make("gnu.kawa.lispexpr.LangObjType");
    public static final LangObjType typeType = new LangObjType(CommonProperties.TYPE, "gnu.bytecode.Type", 5);
    public static final LangObjType vectorType = new LangObjType("vector", "gnu.lists.FVector", 12);
    ClassType implementationType;
    final int typeCode;

    LangObjType(String str, String str2, int i) {
        super(str);
        ClassType make = ClassType.make(str2);
        this.implementationType = make;
        this.typeCode = i;
        setSignature(make.getSignature());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        if (r0 != 15) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        if (r5 != r1.implementationType) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compare(gnu.bytecode.Type r5) {
        /*
            r4 = this;
            int r0 = r4.typeCode
            r1 = 4
            r2 = -1
            if (r0 == r1) goto L_0x0071
            r1 = 5
            r3 = 1
            if (r0 == r1) goto L_0x004f
            r1 = 6
            if (r0 == r1) goto L_0x0060
            r1 = 7
            r2 = 0
            if (r0 == r1) goto L_0x001b
            r1 = 9
            if (r0 == r1) goto L_0x0039
            r1 = 15
            if (r0 == r1) goto L_0x0039
            goto L_0x0082
        L_0x001b:
            boolean r0 = r5 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = r5.getSignature()
            char r0 = r0.charAt(r2)
            r1 = 66
            if (r0 == r1) goto L_0x0038
            r1 = 83
            if (r0 == r1) goto L_0x0038
            r1 = 73
            if (r0 == r1) goto L_0x0038
            r1 = 74
            if (r0 == r1) goto L_0x0038
            goto L_0x0039
        L_0x0038:
            return r3
        L_0x0039:
            boolean r0 = r5 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L_0x0082
            java.lang.String r0 = r5.getSignature()
            char r0 = r0.charAt(r2)
            r1 = 68
            if (r0 == r1) goto L_0x004e
            r1 = 70
            if (r0 == r1) goto L_0x004e
            goto L_0x0082
        L_0x004e:
            return r3
        L_0x004f:
            gnu.kawa.lispexpr.LangObjType r0 = typeClass
            if (r5 == r0) goto L_0x0070
            gnu.kawa.lispexpr.LangObjType r1 = typeClassType
            if (r5 == r1) goto L_0x0070
            gnu.bytecode.ClassType r0 = r0.implementationType
            if (r5 == r0) goto L_0x0070
            gnu.bytecode.ClassType r0 = r1.implementationType
            if (r5 != r0) goto L_0x0060
            goto L_0x0070
        L_0x0060:
            gnu.kawa.lispexpr.LangObjType r0 = typeClass
            if (r5 == r0) goto L_0x0070
            gnu.bytecode.ClassType r0 = r0.implementationType
            if (r5 != r0) goto L_0x0069
            goto L_0x0070
        L_0x0069:
            gnu.kawa.lispexpr.LangObjType r1 = typeType
            if (r5 == r1) goto L_0x006f
            if (r5 != r0) goto L_0x0082
        L_0x006f:
            return r2
        L_0x0070:
            return r3
        L_0x0071:
            gnu.kawa.lispexpr.LangObjType r0 = typeType
            if (r5 == r0) goto L_0x008f
            gnu.kawa.lispexpr.LangObjType r1 = typeClassType
            if (r5 == r1) goto L_0x008f
            gnu.bytecode.ClassType r0 = r0.implementationType
            if (r5 == r0) goto L_0x008f
            gnu.bytecode.ClassType r0 = r1.implementationType
            if (r5 != r0) goto L_0x0082
            goto L_0x008f
        L_0x0082:
            gnu.bytecode.Type r0 = r4.getImplementationType()
            gnu.bytecode.Type r5 = r5.getImplementationType()
            int r5 = r0.compare(r5)
            return r5
        L_0x008f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangObjType.compare(gnu.bytecode.Type):int");
    }

    public Field getField(String str, int i) {
        return this.implementationType.getField(str, i);
    }

    public Method getMethod(String str, Type[] typeArr) {
        return this.implementationType.getMethod(str, typeArr);
    }

    public Method getDeclaredMethod(String str, int i) {
        return this.implementationType.getDeclaredMethod(str, i);
    }

    public int getMethods(Filter filter, int i, List<Method> list) {
        return this.implementationType.getMethods(filter, i, list);
    }

    public Class getReflectClass() {
        return this.implementationType.getReflectClass();
    }

    public Type getRealType() {
        return this.implementationType;
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        switch (this.typeCode) {
            case 11:
            case 12:
            case 13:
            case 14:
                this.implementationType.emitIsInstance(compilation.getCode());
                target.compileFromStack(compilation, compilation.getLanguage().getTypeFor(Boolean.TYPE));
                return;
            default:
                InstanceOf.emitIsInstance(this, variable, compilation, target);
                return;
        }
    }

    public static Numeric coerceNumeric(Object obj) {
        Numeric asNumericOrNull = Numeric.asNumericOrNull(obj);
        if (asNumericOrNull != null || obj == null) {
            return asNumericOrNull;
        }
        throw new WrongType(-4, obj, (Type) numericType);
    }

    public static RealNum coerceRealNum(Object obj) {
        RealNum asRealNumOrNull = RealNum.asRealNumOrNull(obj);
        if (asRealNumOrNull != null || obj == null) {
            return asRealNumOrNull;
        }
        throw new WrongType(-4, obj, (Type) realType);
    }

    public static DFloNum coerceDFloNum(Object obj) {
        DFloNum asDFloNumOrNull = DFloNum.asDFloNumOrNull(obj);
        if (asDFloNumOrNull != null || obj == null) {
            return asDFloNumOrNull;
        }
        throw new WrongType(-4, obj, (Type) dflonumType);
    }

    public static RatNum coerceRatNum(Object obj) {
        RatNum asRatNumOrNull = RatNum.asRatNumOrNull(obj);
        if (asRatNumOrNull != null || obj == null) {
            return asRatNumOrNull;
        }
        throw new WrongType(-4, obj, (Type) rationalType);
    }

    public static IntNum coerceIntNum(Object obj) {
        IntNum asIntNumOrNull = IntNum.asIntNumOrNull(obj);
        if (asIntNumOrNull != null || obj == null) {
            return asIntNumOrNull;
        }
        throw new WrongType(-4, obj, (Type) integerType);
    }

    public static Class coerceToClassOrNull(Object obj) {
        if (obj instanceof Class) {
            return (Class) obj;
        }
        if (!(obj instanceof Type) || !(obj instanceof ClassType) || (obj instanceof PairClassType)) {
            return null;
        }
        return ((ClassType) obj).getReflectClass();
    }

    public static Class coerceToClass(Object obj) {
        Class coerceToClassOrNull = coerceToClassOrNull(obj);
        if (coerceToClassOrNull != null || obj == null) {
            return coerceToClassOrNull;
        }
        throw new ClassCastException("cannot cast " + obj + " to type");
    }

    public static ClassType coerceToClassTypeOrNull(Object obj) {
        if (obj instanceof ClassType) {
            return (ClassType) obj;
        }
        if (!(obj instanceof Class)) {
            return null;
        }
        Type typeFor = Language.getDefaultLanguage().getTypeFor((Class) obj);
        if (typeFor instanceof ClassType) {
            return (ClassType) typeFor;
        }
        return null;
    }

    public static ClassType coerceToClassType(Object obj) {
        ClassType coerceToClassTypeOrNull = coerceToClassTypeOrNull(obj);
        if (coerceToClassTypeOrNull != null || obj == null) {
            return coerceToClassTypeOrNull;
        }
        throw new ClassCastException("cannot cast " + obj + " to class-type");
    }

    public static Type coerceToTypeOrNull(Object obj) {
        if (obj instanceof Type) {
            return (Type) obj;
        }
        if (obj instanceof Class) {
            return Language.getDefaultLanguage().getTypeFor((Class) obj);
        }
        return null;
    }

    public static Type coerceToType(Object obj) {
        Type coerceToTypeOrNull = coerceToTypeOrNull(obj);
        if (coerceToTypeOrNull != null || obj == null) {
            return coerceToTypeOrNull;
        }
        throw new ClassCastException("cannot cast " + obj + " to type");
    }

    /* access modifiers changed from: package-private */
    public Method coercionMethod() {
        switch (this.typeCode) {
            case 4:
                return typeLangObjType.getDeclaredMethod("coerceToClass", 1);
            case 5:
                return typeLangObjType.getDeclaredMethod("coerceToType", 1);
            case 6:
                return typeLangObjType.getDeclaredMethod("coerceToClassType", 1);
            case 7:
                return typeLangObjType.getDeclaredMethod("coerceIntNum", 1);
            case 8:
                return typeLangObjType.getDeclaredMethod("coerceRatNum", 1);
            case 9:
                return typeLangObjType.getDeclaredMethod("coerceRealNum", 1);
            case 10:
                return typeLangObjType.getDeclaredMethod("coerceNumeric", 1);
            case 11:
            case 12:
            case 13:
            case 14:
                return null;
            case 15:
                return typeLangObjType.getDeclaredMethod("coerceDFloNum", 1);
            default:
                return ((PrimProcedure) getConstructor()).getMethod();
        }
    }

    /* access modifiers changed from: package-private */
    public Method coercionOrNullMethod() {
        String str;
        ClassType classType = this.implementationType;
        int i = this.typeCode;
        if (i != 15) {
            switch (i) {
                case 1:
                    str = "coerceToPathOrNull";
                    break;
                case 2:
                    str = "coerceToFilePathOrNull";
                    break;
                case 3:
                    str = "coerceToURIPathOrNull";
                    break;
                case 4:
                    classType = typeLangObjType;
                    str = "coerceToClassOrNull";
                    break;
                case 5:
                    classType = typeLangObjType;
                    str = "coerceToTypeOrNull";
                    break;
                case 6:
                    classType = typeLangObjType;
                    str = "coerceToClassTypeOrNull";
                    break;
                case 7:
                    str = "asIntNumOrNull";
                    break;
                case 8:
                    str = "asRatNumOrNull";
                    break;
                case 9:
                    str = "asRealNumOrNull";
                    break;
                case 10:
                    str = "asNumericOrNull";
                    break;
                default:
                    return null;
            }
        } else {
            str = "asDFloNumOrNull";
        }
        return classType.getDeclaredMethod(str, 1);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (variable != null) {
            code.emitLoad(variable);
        }
        Method coercionOrNullMethod = coercionOrNullMethod();
        if (coercionOrNullMethod != null) {
            code.emitInvokeStatic(coercionOrNullMethod);
        }
        if (declaration != null) {
            code.emitDup();
            declaration.compileStore(compilation);
        }
        if (coercionOrNullMethod != null) {
            code.emitIfNotNull();
            return;
        }
        this.implementationType.emitIsInstance(code);
        code.emitIfIntNotZero();
    }

    public Object coerceFromObject(Object obj) {
        int i = this.typeCode;
        if (i == 15) {
            return coerceDFloNum(obj);
        }
        switch (i) {
            case 1:
                return Path.valueOf(obj);
            case 2:
                return FilePath.makeFilePath(obj);
            case 3:
                return URIPath.makeURI(obj);
            case 4:
                return coerceToClass(obj);
            case 5:
                return coerceToType(obj);
            case 6:
                return coerceToClassType(obj);
            case 7:
                return coerceIntNum(obj);
            case 8:
                return coerceRatNum(obj);
            case 9:
                return coerceRealNum(obj);
            case 10:
                return coerceNumeric(obj);
            default:
                return super.coerceFromObject(obj);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void emitConvertFromPrimitive(gnu.bytecode.Type r6, gnu.bytecode.CodeAttr r7) {
        /*
            r5 = this;
            int r0 = r5.typeCode
            r1 = 15
            java.lang.String r2 = "gnu.math.DFloNum"
            java.lang.String r3 = "gnu.math.IntNum"
            r4 = 0
            if (r0 == r1) goto L_0x004a
            switch(r0) {
                case 7: goto L_0x0010;
                case 8: goto L_0x0010;
                case 9: goto L_0x0010;
                case 10: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x006f
        L_0x0010:
            boolean r0 = r6 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L_0x006f
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.intType
            if (r6 == r0) goto L_0x0046
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.byteType
            if (r6 == r0) goto L_0x0046
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.shortType
            if (r6 != r0) goto L_0x0021
            goto L_0x0046
        L_0x0021:
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.longType
            if (r6 != r0) goto L_0x0028
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.long_type
            goto L_0x0048
        L_0x0028:
            int r0 = r5.typeCode
            r1 = 9
            if (r0 == r1) goto L_0x0032
            r1 = 10
            if (r0 != r1) goto L_0x006f
        L_0x0032:
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.floatType
            if (r6 != r0) goto L_0x003f
            gnu.bytecode.PrimType r6 = gnu.bytecode.Type.float_type
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.double_type
            r7.emitConvert(r6, r0)
            gnu.bytecode.PrimType r6 = gnu.bytecode.Type.doubleType
        L_0x003f:
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.doubleType
            if (r6 != r0) goto L_0x006f
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.doubleType
            goto L_0x0070
        L_0x0046:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.int_type
        L_0x0048:
            r2 = r3
            goto L_0x0070
        L_0x004a:
            boolean r0 = r6 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L_0x006f
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.intType
            if (r6 == r0) goto L_0x0062
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.byteType
            if (r6 == r0) goto L_0x0062
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.shortType
            if (r6 == r0) goto L_0x0062
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.longType
            if (r6 == r0) goto L_0x0062
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.floatType
            if (r6 != r0) goto L_0x0069
        L_0x0062:
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.doubleType
            r7.emitConvert(r6, r0)
            gnu.bytecode.PrimType r6 = gnu.bytecode.Type.doubleType
        L_0x0069:
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.doubleType
            if (r6 != r0) goto L_0x006f
            r4 = r6
            goto L_0x0070
        L_0x006f:
            r2 = r4
        L_0x0070:
            if (r2 == 0) goto L_0x0086
            gnu.bytecode.ClassType r6 = gnu.bytecode.ClassType.make(r2)
            r0 = 1
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r1 = 0
            r0[r1] = r4
            java.lang.String r1 = "make"
            gnu.bytecode.Method r6 = r6.getDeclaredMethod((java.lang.String) r1, (gnu.bytecode.Type[]) r0)
            r7.emitInvokeStatic(r6)
            goto L_0x0089
        L_0x0086:
            super.emitConvertFromPrimitive(r6, r7)
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangObjType.emitConvertFromPrimitive(gnu.bytecode.Type, gnu.bytecode.CodeAttr):void");
    }

    public Expression convertValue(Expression expression) {
        Method coercionMethod;
        int i = this.typeCode;
        if (i == 7 || i == 10 || i == 9 || i == 8 || i == 15 || (coercionMethod = coercionMethod()) == null) {
            return null;
        }
        ApplyExp applyExp = new ApplyExp(coercionMethod, expression);
        applyExp.setType(this);
        return applyExp;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        switch (this.typeCode) {
            case 11:
            case 12:
            case 13:
            case 14:
                codeAttr.emitCheckcast(this.implementationType);
                return;
            default:
                codeAttr.emitInvoke(coercionMethod());
                return;
        }
    }

    public Procedure getConstructor() {
        int i = this.typeCode;
        if (i == 1) {
            return makePathProc;
        }
        if (i == 2) {
            return makeFilepathProc;
        }
        if (i == 3) {
            return makeURIProc;
        }
        switch (i) {
            case 11:
                return MakeList.list;
            case 12:
                return new PrimProcedure("gnu.lists.FVector", "make", 1);
            case 13:
                return new PrimProcedure("kawa.lib.strings", "$make$string$", 1);
            case 14:
                return new PrimProcedure("java.util.regex.Pattern", "compile", 1);
            default:
                return null;
        }
    }
}
