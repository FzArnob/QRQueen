package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import gnu.math.RealNum;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Arithmetic {
    public static final int BIGDECIMAL_CODE = 5;
    public static final int BIGINTEGER_CODE = 3;
    public static final int DOUBLE_CODE = 8;
    public static final int FLOAT_CODE = 7;
    public static final int FLONUM_CODE = 9;
    public static final int INTNUM_CODE = 4;
    public static final int INT_CODE = 1;
    public static final int LONG_CODE = 2;
    public static final int NUMERIC_CODE = 11;
    public static final int RATNUM_CODE = 6;
    public static final int REALNUM_CODE = 10;
    static LangObjType typeDFloNum = LangObjType.dflonumType;
    static LangObjType typeIntNum = LangObjType.integerType;
    static ClassType typeNumber = ClassType.make("java.lang.Number");
    static ClassType typeNumeric = ClassType.make("gnu.math.Numeric");
    static LangObjType typeRatNum = LangObjType.rationalType;
    static LangObjType typeRealNum = LangObjType.realType;

    public static int classifyValue(Object obj) {
        if (obj instanceof Numeric) {
            if (obj instanceof IntNum) {
                return 4;
            }
            if (obj instanceof RatNum) {
                return 6;
            }
            if (obj instanceof DFloNum) {
                return 9;
            }
            return obj instanceof RealNum ? 10 : 11;
        } else if (!(obj instanceof Number)) {
            return -1;
        } else {
            if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                return 1;
            }
            if (obj instanceof Long) {
                return 2;
            }
            if (obj instanceof Float) {
                return 7;
            }
            if (obj instanceof Double) {
                return 8;
            }
            if (obj instanceof BigInteger) {
                return 3;
            }
            if (obj instanceof BigDecimal) {
                return 5;
            }
            return -1;
        }
    }

    public static Type kindType(int i) {
        switch (i) {
            case 1:
                return LangPrimType.intType;
            case 2:
                return LangPrimType.longType;
            case 3:
                return ClassType.make("java.math.BigInteger");
            case 4:
                return typeIntNum;
            case 5:
                return ClassType.make("java.math.BigDecimal");
            case 6:
                return typeRatNum;
            case 7:
                return LangPrimType.floatType;
            case 8:
                return LangPrimType.doubleType;
            case 9:
                return typeDFloNum;
            case 10:
                return typeRealNum;
            case 11:
                return typeNumeric;
            default:
                return Type.pointer_type;
        }
    }

    public static int classifyType(Type type) {
        if (type instanceof PrimType) {
            char charAt = type.getSignature().charAt(0);
            if (charAt == 'V' || charAt == 'Z' || charAt == 'C') {
                return 0;
            }
            if (charAt == 'D') {
                return 8;
            }
            if (charAt == 'F') {
                return 7;
            }
            return charAt == 'J' ? 2 : 1;
        }
        String name = type.getName();
        if (type.isSubtype(typeIntNum)) {
            return 4;
        }
        if (type.isSubtype(typeRatNum)) {
            return 6;
        }
        if (type.isSubtype(typeDFloNum)) {
            return 9;
        }
        if ("java.lang.Double".equals(name)) {
            return 8;
        }
        if ("java.lang.Float".equals(name)) {
            return 7;
        }
        if ("java.lang.Long".equals(name)) {
            return 2;
        }
        if ("java.lang.Integer".equals(name) || "java.lang.Short".equals(name) || "java.lang.Byte".equals(name)) {
            return 1;
        }
        if ("java.math.BigInteger".equals(name)) {
            return 3;
        }
        if ("java.math.BigDecimal".equals(name)) {
            return 5;
        }
        if (type.isSubtype(typeRealNum)) {
            return 10;
        }
        if (type.isSubtype(typeNumeric)) {
            return 11;
        }
        return 0;
    }

    public static int asInt(Object obj) {
        return ((Number) obj).intValue();
    }

    public static long asLong(Object obj) {
        return ((Number) obj).longValue();
    }

    public static float asFloat(Object obj) {
        return ((Number) obj).floatValue();
    }

    public static double asDouble(Object obj) {
        return ((Number) obj).doubleValue();
    }

    public static BigInteger asBigInteger(Object obj) {
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (obj instanceof IntNum) {
            return new BigInteger(obj.toString());
        }
        return BigInteger.valueOf(((Number) obj).longValue());
    }

    public static IntNum asIntNum(BigDecimal bigDecimal) {
        return IntNum.valueOf(bigDecimal.toBigInteger().toString(), 10);
    }

    public static IntNum asIntNum(BigInteger bigInteger) {
        return IntNum.valueOf(bigInteger.toString(), 10);
    }

    public static IntNum asIntNum(Object obj) {
        if (obj instanceof IntNum) {
            return (IntNum) obj;
        }
        if (obj instanceof BigInteger) {
            return IntNum.valueOf(obj.toString(), 10);
        }
        if (obj instanceof BigDecimal) {
            return asIntNum((BigDecimal) obj);
        }
        return IntNum.make(((Number) obj).longValue());
    }

    public static BigDecimal asBigDecimal(Object obj) {
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        if ((obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
            return BigDecimal.valueOf(((Number) obj).longValue());
        }
        return new BigDecimal(obj.toString());
    }

    public static RatNum asRatNum(Object obj) {
        if (obj instanceof RatNum) {
            return (RatNum) obj;
        }
        if (obj instanceof BigInteger) {
            return IntNum.valueOf(obj.toString(), 10);
        }
        if (obj instanceof BigDecimal) {
            return RatNum.valueOf((BigDecimal) obj);
        }
        return IntNum.make(((Number) obj).longValue());
    }

    public static Numeric asNumeric(Object obj) {
        Numeric asNumericOrNull = Numeric.asNumericOrNull(obj);
        return asNumericOrNull != null ? asNumericOrNull : (Numeric) obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        return java.lang.Double.toString(asDouble(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        return asNumeric(r2).toString(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        if (r3 != 10) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        return java.lang.Float.toString(asFloat(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r3 != 10) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(java.lang.Object r2, int r3) {
        /*
            int r0 = classifyValue(r2)
            r1 = 10
            switch(r0) {
                case 1: goto L_0x0046;
                case 2: goto L_0x003d;
                case 3: goto L_0x0034;
                case 4: goto L_0x002b;
                case 5: goto L_0x000a;
                case 6: goto L_0x0009;
                case 7: goto L_0x0015;
                case 8: goto L_0x0020;
                case 9: goto L_0x0020;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x004f
        L_0x000a:
            if (r3 != r1) goto L_0x0015
            java.math.BigDecimal r2 = asBigDecimal(r2)
            java.lang.String r2 = r2.toString()
            return r2
        L_0x0015:
            if (r3 != r1) goto L_0x0020
            float r2 = asFloat(r2)
            java.lang.String r2 = java.lang.Float.toString(r2)
            return r2
        L_0x0020:
            if (r3 != r1) goto L_0x004f
            double r2 = asDouble(r2)
            java.lang.String r2 = java.lang.Double.toString(r2)
            return r2
        L_0x002b:
            gnu.math.IntNum r2 = asIntNum((java.lang.Object) r2)
            java.lang.String r2 = r2.toString(r3)
            return r2
        L_0x0034:
            java.math.BigInteger r2 = asBigInteger(r2)
            java.lang.String r2 = r2.toString(r3)
            return r2
        L_0x003d:
            long r0 = asLong(r2)
            java.lang.String r2 = java.lang.Long.toString(r0, r3)
            return r2
        L_0x0046:
            int r2 = asInt(r2)
            java.lang.String r2 = java.lang.Integer.toString(r2, r3)
            return r2
        L_0x004f:
            gnu.math.Numeric r2 = asNumeric(r2)
            java.lang.String r2 = r2.toString(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.Arithmetic.toString(java.lang.Object, int):java.lang.String");
    }

    public static Object convert(Object obj, int i) {
        switch (i) {
            case 1:
                if (obj instanceof Integer) {
                    return obj;
                }
                return Integer.valueOf(((Number) obj).intValue());
            case 2:
                if (obj instanceof Long) {
                    return obj;
                }
                return Long.valueOf(((Number) obj).longValue());
            case 3:
                return asBigInteger(obj);
            case 4:
                return asIntNum(obj);
            case 5:
                return asBigDecimal(obj);
            case 6:
                return asRatNum(obj);
            case 7:
                if (obj instanceof Float) {
                    return obj;
                }
                return Float.valueOf(asFloat(obj));
            case 8:
                if (obj instanceof Double) {
                    return obj;
                }
                return Double.valueOf(asDouble(obj));
            case 9:
                if (obj instanceof DFloNum) {
                    return obj;
                }
                return DFloNum.make(asDouble(obj));
            case 10:
                return (RealNum) asNumeric(obj);
            case 11:
                return asNumeric(obj);
            default:
                return (Number) obj;
        }
    }

    public static boolean isExact(Number number) {
        if (number instanceof Numeric) {
            return ((Numeric) number).isExact();
        }
        return !(number instanceof Double) && !(number instanceof Float) && !(number instanceof BigDecimal);
    }

    public static Number toExact(Number number) {
        if (number instanceof Numeric) {
            return ((Numeric) number).toExact();
        }
        if ((number instanceof Double) || (number instanceof Float) || (number instanceof BigDecimal)) {
            return DFloNum.toExact(number.doubleValue());
        }
        return number;
    }

    public static Number toInexact(Number number) {
        if (number instanceof Numeric) {
            return ((Numeric) number).toInexact();
        }
        return ((number instanceof Double) || (number instanceof Float) || (number instanceof BigDecimal)) ? number : Double.valueOf(number.doubleValue());
    }
}
