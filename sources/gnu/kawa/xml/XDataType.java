package gnu.kawa.xml;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.text.Path;
import gnu.text.Printable;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

public class XDataType extends Type implements TypeValue {
    public static final int ANY_ATOMIC_TYPE_CODE = 3;
    public static final int ANY_SIMPLE_TYPE_CODE = 2;
    public static final int ANY_URI_TYPE_CODE = 33;
    public static final int BASE64_BINARY_TYPE_CODE = 34;
    public static final int BOOLEAN_TYPE_CODE = 31;
    public static final int BYTE_TYPE_CODE = 11;
    public static final int DATE_TIME_TYPE_CODE = 20;
    public static final int DATE_TYPE_CODE = 21;
    public static final int DAY_TIME_DURATION_TYPE_CODE = 30;
    public static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1);
    public static final int DECIMAL_TYPE_CODE = 4;
    public static final Double DOUBLE_ONE = makeDouble(1.0d);
    public static final int DOUBLE_TYPE_CODE = 19;
    public static final Double DOUBLE_ZERO = makeDouble(0.0d);
    public static final int DURATION_TYPE_CODE = 28;
    public static final int ENTITY_TYPE_CODE = 47;
    public static final Float FLOAT_ONE = makeFloat(1.0f);
    public static final int FLOAT_TYPE_CODE = 18;
    public static final Float FLOAT_ZERO = makeFloat(0.0f);
    public static final int G_DAY_TYPE_CODE = 26;
    public static final int G_MONTH_DAY_TYPE_CODE = 25;
    public static final int G_MONTH_TYPE_CODE = 27;
    public static final int G_YEAR_MONTH_TYPE_CODE = 23;
    public static final int G_YEAR_TYPE_CODE = 24;
    public static final int HEX_BINARY_TYPE_CODE = 35;
    public static final int IDREF_TYPE_CODE = 46;
    public static final int ID_TYPE_CODE = 45;
    public static final int INTEGER_TYPE_CODE = 5;
    public static final int INT_TYPE_CODE = 9;
    public static final int LANGUAGE_TYPE_CODE = 41;
    public static final int LONG_TYPE_CODE = 8;
    public static final int NAME_TYPE_CODE = 43;
    public static final int NCNAME_TYPE_CODE = 44;
    public static final int NEGATIVE_INTEGER_TYPE_CODE = 7;
    public static final int NMTOKEN_TYPE_CODE = 42;
    public static final int NONNEGATIVE_INTEGER_TYPE_CODE = 12;
    public static final int NON_POSITIVE_INTEGER_TYPE_CODE = 6;
    public static final int NORMALIZED_STRING_TYPE_CODE = 39;
    public static final int NOTATION_TYPE_CODE = 36;
    public static final XDataType NotationType = new XDataType("NOTATION", ClassType.make("gnu.kawa.xml.Notation"), 36);
    public static final int POSITIVE_INTEGER_TYPE_CODE = 17;
    public static final int QNAME_TYPE_CODE = 32;
    public static final int SHORT_TYPE_CODE = 10;
    public static final int STRING_TYPE_CODE = 38;
    public static final int TIME_TYPE_CODE = 22;
    public static final int TOKEN_TYPE_CODE = 40;
    public static final int UNSIGNED_BYTE_TYPE_CODE = 16;
    public static final int UNSIGNED_INT_TYPE_CODE = 14;
    public static final int UNSIGNED_LONG_TYPE_CODE = 13;
    public static final int UNSIGNED_SHORT_TYPE_CODE = 15;
    public static final int UNTYPED_ATOMIC_TYPE_CODE = 37;
    public static final int UNTYPED_TYPE_CODE = 48;
    public static final int YEAR_MONTH_DURATION_TYPE_CODE = 29;
    public static final XDataType anyAtomicType = new XDataType("anyAtomicType", Type.objectType, 3);
    public static final XDataType anySimpleType = new XDataType("anySimpleType", Type.objectType, 2);
    public static final XDataType anyURIType = new XDataType("anyURI", ClassType.make("gnu.text.Path"), 33);
    public static final XDataType base64BinaryType = new XDataType("base64Binary", ClassType.make("gnu.kawa.xml.Base64Binary"), 34);
    public static final XDataType booleanType = new XDataType("boolean", Type.booleanType, 31);
    public static final XDataType dayTimeDurationType = new XDataType("dayTimeDuration", ClassType.make("gnu.math.Duration"), 30);
    public static final XDataType decimalType = new XDataType("decimal", ClassType.make("java.lang.Number"), 4);
    public static final XDataType doubleType = new XDataType(DoubleTypedProperty.TYPE, ClassType.make("java.lang.Double"), 19);
    public static final XDataType durationType = new XDataType("duration", ClassType.make("gnu.math.Duration"), 28);
    public static final XDataType floatType = new XDataType(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, ClassType.make("java.lang.Float"), 18);
    public static final XDataType hexBinaryType = new XDataType("hexBinary", ClassType.make("gnu.kawa.xml.HexBinary"), 35);
    public static final XDataType stringStringType = new XDataType("String", ClassType.make("java.lang.String"), 38);
    public static final XDataType stringType = new XDataType("string", ClassType.make("java.lang.CharSequence"), 38);
    public static final XDataType untypedAtomicType = new XDataType("string", ClassType.make("gnu.kawa.xml.UntypedAtomic"), 37);
    public static final XDataType untypedType = new XDataType("untyped", Type.objectType, 48);
    public static final XDataType yearMonthDurationType = new XDataType("yearMonthDuration", ClassType.make("gnu.math.Duration"), 29);
    XDataType baseType;
    Type implementationType;
    Object name;
    int typeCode;

    public Expression convertValue(Expression expression) {
        return null;
    }

    public Procedure getConstructor() {
        return null;
    }

    public XDataType(Object obj, Type type, int i) {
        super(type);
        this.name = obj;
        if (obj != null) {
            setName(obj.toString());
        }
        this.implementationType = type;
        this.typeCode = i;
    }

    public Class getReflectClass() {
        return this.implementationType.getReflectClass();
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        Compilation.getCurrent().compileConstant(this, Target.pushObject);
        Method declaredMethod = ClassType.make("gnu.kawa.xml.XDataType").getDeclaredMethod("coerceFromObject", 1);
        codeAttr.emitSwap();
        codeAttr.emitInvokeVirtual(declaredMethod);
        this.implementationType.emitCoerceFromObject(codeAttr);
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        if (this.typeCode == 31) {
            this.implementationType.emitCoerceToObject(codeAttr);
        } else {
            super.emitCoerceToObject(codeAttr);
        }
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (this.typeCode == 31) {
            if (variable != null) {
                code.emitLoad(variable);
            }
            Type.javalangBooleanType.emitIsInstance(code);
            code.emitIfIntNotZero();
            if (declaration != null) {
                code.emitLoad(variable);
                Type.booleanType.emitCoerceFromObject(code);
                declaration.compileStore(compilation);
                return;
            }
            return;
        }
        compilation.compileConstant(this, Target.pushObject);
        if (variable == null) {
            code.emitSwap();
        } else {
            code.emitLoad(variable);
        }
        code.emitInvokeVirtual(Compilation.typeType.getDeclaredMethod("isInstance", 1));
        code.emitIfIntNotZero();
        if (declaration != null) {
            code.emitLoad(variable);
            emitCoerceFromObject(code);
            declaration.compileStore(compilation);
        }
    }

    public boolean isInstance(Object obj) {
        int i = this.typeCode;
        if (i == 2) {
            return !(obj instanceof SeqPosition) && !(obj instanceof Nodes);
        }
        if (i != 3) {
            if (i != 4) {
                if (i == 18) {
                    return obj instanceof Float;
                }
                if (i == 19) {
                    return obj instanceof Double;
                }
                if (i == 33) {
                    return obj instanceof Path;
                }
                if (i == 48) {
                    return true;
                }
                if (i == 37) {
                    return obj instanceof UntypedAtomic;
                }
                if (i == 38) {
                    return obj instanceof CharSequence;
                }
                switch (i) {
                    case 28:
                        return obj instanceof Duration;
                    case 29:
                        if (!(obj instanceof Duration) || ((Duration) obj).unit() != Unit.month) {
                            return false;
                        }
                        return true;
                    case 30:
                        if (!(obj instanceof Duration) || ((Duration) obj).unit() != Unit.second) {
                            return false;
                        }
                        return true;
                    case 31:
                        return obj instanceof Boolean;
                    default:
                        return super.isInstance(obj);
                }
            } else if ((obj instanceof BigDecimal) || (obj instanceof IntNum)) {
                return true;
            } else {
                return false;
            }
        } else if ((obj instanceof Values) || (obj instanceof SeqPosition)) {
            return false;
        } else {
            return true;
        }
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }

    public String toString(Object obj) {
        return obj.toString();
    }

    public void print(Object obj, Consumer consumer) {
        if (obj instanceof Printable) {
            ((Printable) obj).print(consumer);
        } else {
            consumer.write(toString(obj));
        }
    }

    public boolean castable(Object obj) {
        try {
            cast(obj);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public Object cast(Object obj) {
        Object atomicValue = KNode.atomicValue(obj);
        if (atomicValue instanceof UntypedAtomic) {
            if (this.typeCode == 37) {
                return atomicValue;
            }
            return valueOf(atomicValue.toString());
        } else if (atomicValue instanceof String) {
            return valueOf(atomicValue.toString());
        } else {
            int i = this.typeCode;
            if (i != 4) {
                if (i == 37) {
                    return new UntypedAtomic(TextUtils.stringValue(atomicValue));
                }
                if (i != 38) {
                    switch (i) {
                        case 18:
                            if (atomicValue instanceof Float) {
                                return atomicValue;
                            }
                            if (atomicValue instanceof Number) {
                                return makeFloat(((Number) atomicValue).floatValue());
                            }
                            if (atomicValue instanceof Boolean) {
                                return ((Boolean) atomicValue).booleanValue() ? FLOAT_ONE : FLOAT_ZERO;
                            }
                            break;
                        case 19:
                            if (atomicValue instanceof Double) {
                                return atomicValue;
                            }
                            if (atomicValue instanceof Number) {
                                return makeDouble(((Number) atomicValue).doubleValue());
                            }
                            if (atomicValue instanceof Boolean) {
                                return ((Boolean) atomicValue).booleanValue() ? DOUBLE_ONE : DOUBLE_ZERO;
                            }
                            break;
                        case 20:
                        case 21:
                        case 22:
                            if (atomicValue instanceof DateTime) {
                                return ((DateTime) atomicValue).cast(XTimeType.components(((XTimeType) this).typeCode));
                            }
                            break;
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 27:
                            if (atomicValue instanceof DateTime) {
                                int components = XTimeType.components(((XTimeType) this).typeCode);
                                DateTime dateTime = (DateTime) atomicValue;
                                int components2 = dateTime.components();
                                if (components == components2 || (components2 & 14) == 14) {
                                    return dateTime.cast(components);
                                }
                                throw new ClassCastException();
                            }
                            break;
                        case 28:
                            return castToDuration(atomicValue, Unit.duration);
                        case 29:
                            return castToDuration(atomicValue, Unit.month);
                        case 30:
                            return castToDuration(atomicValue, Unit.second);
                        case 31:
                            if (atomicValue instanceof Boolean) {
                                return ((Boolean) atomicValue).booleanValue() ? Boolean.TRUE : Boolean.FALSE;
                            }
                            if (atomicValue instanceof Number) {
                                double doubleValue = ((Number) atomicValue).doubleValue();
                                return (doubleValue == 0.0d || Double.isNaN(doubleValue)) ? Boolean.FALSE : Boolean.TRUE;
                            }
                            break;
                        default:
                            switch (i) {
                                case 33:
                                    return URIPath.makeURI(atomicValue);
                                case 34:
                                    if (atomicValue instanceof BinaryObject) {
                                        return new Base64Binary(((BinaryObject) atomicValue).getBytes());
                                    }
                                    break;
                                case 35:
                                    break;
                            }
                            if (atomicValue instanceof BinaryObject) {
                                return new HexBinary(((BinaryObject) atomicValue).getBytes());
                            }
                            break;
                    }
                } else {
                    return TextUtils.asString(atomicValue);
                }
            } else if (atomicValue instanceof BigDecimal) {
                return atomicValue;
            } else {
                if (atomicValue instanceof RealNum) {
                    return ((RealNum) atomicValue).asBigDecimal();
                }
                if ((atomicValue instanceof Float) || (atomicValue instanceof Double)) {
                    return BigDecimal.valueOf(((Number) atomicValue).doubleValue());
                }
                if (atomicValue instanceof Boolean) {
                    return cast(((Boolean) atomicValue).booleanValue() ? IntNum.one() : IntNum.zero());
                }
            }
            return coerceFromObject(atomicValue);
        }
    }

    /* access modifiers changed from: package-private */
    public Duration castToDuration(Object obj, Unit unit) {
        if (!(obj instanceof Duration)) {
            return (Duration) coerceFromObject(obj);
        }
        Duration duration = (Duration) obj;
        if (duration.unit() == unit) {
            return duration;
        }
        int totalMonths = duration.getTotalMonths();
        long totalSeconds = duration.getTotalSeconds();
        int nanoSecondsOnly = duration.getNanoSecondsOnly();
        if (unit == Unit.second) {
            totalMonths = 0;
        }
        if (unit == Unit.month) {
            totalSeconds = 0;
            nanoSecondsOnly = 0;
        }
        return Duration.make(totalMonths, totalSeconds, nanoSecondsOnly, unit);
    }

    public Object coerceFromObject(Object obj) {
        if (isInstance(obj)) {
            return obj;
        }
        throw new ClassCastException("cannot cast " + obj + " to " + this.name);
    }

    public int compare(Type type) {
        if (this == type) {
            return 0;
        }
        XDataType xDataType = stringStringType;
        if (this == xDataType && type == stringType) {
            return 0;
        }
        if (this == stringType && type == xDataType) {
            return 0;
        }
        return this.implementationType.compare(type);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00ff A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object valueOf(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r4.typeCode
            r1 = 4
            java.lang.String r2 = "'"
            if (r0 == r1) goto L_0x00cc
            r1 = 18
            if (r0 == r1) goto L_0x00a5
            r3 = 19
            if (r0 == r3) goto L_0x00a5
            r1 = 37
            if (r0 == r1) goto L_0x009f
            r1 = 38
            if (r0 == r1) goto L_0x009e
            switch(r0) {
                case 28: goto L_0x009a;
                case 29: goto L_0x0095;
                case 30: goto L_0x0090;
                case 31: goto L_0x004a;
                default: goto L_0x001a;
            }
        L_0x001a:
            switch(r0) {
                case 33: goto L_0x0040;
                case 34: goto L_0x003b;
                case 35: goto L_0x0036;
                default: goto L_0x001d;
            }
        L_0x001d:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "valueOf not implemented for "
            r0.append(r1)
            java.lang.Object r1 = r4.name
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L_0x0036:
            gnu.kawa.xml.HexBinary r5 = gnu.kawa.xml.HexBinary.valueOf(r5)
            return r5
        L_0x003b:
            gnu.kawa.xml.Base64Binary r5 = gnu.kawa.xml.Base64Binary.valueOf(r5)
            return r5
        L_0x0040:
            r0 = 1
            java.lang.String r5 = gnu.xml.TextUtils.replaceWhitespace(r5, r0)
            gnu.text.URIPath r5 = gnu.text.URIPath.makeURI(r5)
            return r5
        L_0x004a:
            java.lang.String r5 = r5.trim()
            java.lang.String r0 = "true"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x008d
            java.lang.String r0 = "1"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x005f
            goto L_0x008d
        L_0x005f:
            java.lang.String r0 = "false"
            boolean r0 = r5.equals(r0)
            if (r0 != 0) goto L_0x008a
            java.lang.String r0 = "0"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0070
            goto L_0x008a
        L_0x0070:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "not a valid boolean: '"
            r1.append(r3)
            r1.append(r5)
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L_0x008a:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L_0x008d:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        L_0x0090:
            gnu.math.Duration r5 = gnu.math.Duration.parseDayTimeDuration(r5)
            return r5
        L_0x0095:
            gnu.math.Duration r5 = gnu.math.Duration.parseYearMonthDuration(r5)
            return r5
        L_0x009a:
            gnu.math.Duration r5 = gnu.math.Duration.parseDuration(r5)
        L_0x009e:
            return r5
        L_0x009f:
            gnu.kawa.xml.UntypedAtomic r0 = new gnu.kawa.xml.UntypedAtomic
            r0.<init>(r5)
            return r0
        L_0x00a5:
            java.lang.String r5 = r5.trim()
            java.lang.String r0 = "INF"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x00b4
            java.lang.String r5 = "Infinity"
            goto L_0x00be
        L_0x00b4:
            java.lang.String r0 = "-INF"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x00be
            java.lang.String r5 = "-Infinity"
        L_0x00be:
            int r0 = r4.typeCode
            if (r0 != r1) goto L_0x00c7
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            goto L_0x00cb
        L_0x00c7:
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
        L_0x00cb:
            return r5
        L_0x00cc:
            java.lang.String r5 = r5.trim()
            int r0 = r5.length()
        L_0x00d4:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x00ff
            char r1 = r5.charAt(r0)
            r3 = 101(0x65, float:1.42E-43)
            if (r1 == r3) goto L_0x00e5
            r3 = 69
            if (r1 == r3) goto L_0x00e5
            goto L_0x00d4
        L_0x00e5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "not a valid decimal: '"
            r1.append(r3)
            r1.append(r5)
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            throw r0
        L_0x00ff:
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.XDataType.valueOf(java.lang.String):java.lang.Object");
    }

    public static Float makeFloat(float f) {
        return Float.valueOf(f);
    }

    public static Double makeDouble(double d) {
        return Double.valueOf(d);
    }
}
