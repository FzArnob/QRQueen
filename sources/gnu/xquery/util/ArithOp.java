package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.xml.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import org.slf4j.Marker;

public class ArithOp extends Procedure1or2 implements Inlineable {
    static final BigInteger TEN = BigInteger.valueOf(10);
    public static final ArithOp add = new ArithOp(Marker.ANY_NON_NULL_MARKER, '+', 2);
    public static final ArithOp div = new ArithOp("div", 'd', 2);
    public static final ArithOp idiv = new ArithOp("idiv", 'i', 2);
    public static final ArithOp minus = new ArithOp("-", Access.METHOD_CONTEXT, 1);
    public static final ArithOp mod = new ArithOp("mod", 'm', 2);
    public static final ArithOp mul = new ArithOp(Marker.ANY_MARKER, '*', 2);
    public static final ArithOp plus = new ArithOp(Marker.ANY_NON_NULL_MARKER, 'P', 1);
    public static final ArithOp sub = new ArithOp("-", '-', 2);
    char op;

    ArithOp(String str, char c, int i) {
        super(str);
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateArithOp");
        this.op = c;
    }

    public Object apply1(Object obj) throws Throwable {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            obj = XDataType.doubleType.valueOf(TextUtils.stringValue(obj));
        }
        char c = this.op;
        if (c == 'M') {
            int classifyValue = Arithmetic.classifyValue(obj);
            if (classifyValue == 7) {
                return XDataType.makeFloat(-Arithmetic.asFloat(obj));
            }
            if (classifyValue == 8) {
                return XDataType.makeDouble(-Arithmetic.asDouble(obj));
            }
            if (obj instanceof Numeric) {
                return ((Numeric) obj).neg();
            }
            return AddOp.apply2(-1, IntNum.zero(), obj);
        } else if (c == 'P') {
            return AddOp.apply2(1, IntNum.zero(), obj);
        } else {
            throw new UnsupportedOperationException(getName());
        }
    }

    public static BigDecimal div(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return bigDecimal.divide(bigDecimal2, MathContext.DECIMAL128);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        if (obj == Values.empty || obj == null) {
            return obj;
        }
        if (obj2 == Values.empty || obj2 == null) {
            return obj2;
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            obj = XDataType.doubleType.valueOf(TextUtils.stringValue(obj));
        }
        if ((obj2 instanceof KNode) || (obj2 instanceof UntypedAtomic)) {
            obj2 = XDataType.doubleType.valueOf(TextUtils.stringValue(obj2));
        }
        char c = this.op;
        if (c == '*') {
            return MultiplyOp.$St.apply2(obj, obj2);
        }
        if (c == '+') {
            return AddOp.apply2(1, obj, obj2);
        }
        if (c == '-') {
            return AddOp.apply2(-1, obj, obj2);
        }
        int classifyValue = Arithmetic.classifyValue(obj);
        int classifyValue2 = Arithmetic.classifyValue(obj2);
        int i = classifyValue < classifyValue2 ? classifyValue2 : classifyValue;
        char c2 = this.op;
        if (c2 == 'd') {
            if (classifyValue >= 0 && classifyValue2 >= 0) {
                if (i <= 6) {
                    return div((BigDecimal) XDataType.decimalType.cast(obj), (BigDecimal) XDataType.decimalType.cast(obj2));
                }
                if (i == 7) {
                    return new Float(((Number) obj).floatValue() / ((Number) obj2).floatValue());
                }
                if (i == 8) {
                    return new Double(((Number) obj).doubleValue() / ((Number) obj2).doubleValue());
                }
                if ((obj instanceof Duration) && (obj2 instanceof Duration)) {
                    Duration duration = (Duration) obj;
                    Duration duration2 = (Duration) obj2;
                    if (duration.unit() == Unit.second && duration2.unit() == Unit.second) {
                        return div(TimeUtils.secondsBigDecimalFromDuration(duration.getTotalSeconds(), duration.getNanoSecondsOnly()), TimeUtils.secondsBigDecimalFromDuration(duration2.getTotalSeconds(), duration2.getNanoSecondsOnly()));
                    } else if (duration.unit() == Unit.month && duration2.unit() == Unit.month) {
                        return div(BigDecimal.valueOf((long) duration.getTotalMonths()), BigDecimal.valueOf((long) duration2.getTotalMonths()));
                    } else {
                        throw new ArithmeticException("divide of incompatible durations");
                    }
                } else if (i >= 0) {
                    return Arithmetic.asNumeric(obj).div(Arithmetic.asNumeric(obj2));
                }
            }
            throw new UnsupportedOperationException(getName());
        } else if (c2 != 'i') {
            if (c2 == 'm' && classifyValue >= 0 && classifyValue2 >= 0) {
                if (i <= 4) {
                    return IntNum.remainder(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj2));
                }
                if (i <= 6) {
                    return sub.apply2(obj, mul.apply2(idiv.apply2(obj, obj2), obj2));
                }
                if (i <= 7) {
                    return XDataType.makeFloat(Arithmetic.asFloat(obj) % Arithmetic.asFloat(obj2));
                }
                if (i <= 9) {
                    double asDouble = Arithmetic.asDouble(obj) % Arithmetic.asDouble(obj2);
                    if (i == 9) {
                        return DFloNum.make(asDouble);
                    }
                    return XDataType.makeDouble(asDouble);
                }
            }
            throw new UnsupportedOperationException(getName());
        }
        if (classifyValue >= 0 && classifyValue2 >= 0) {
            if (i <= 4) {
                return IntNum.quotient(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj2));
            }
            if (i <= 6) {
                return Arithmetic.asIntNum(((BigDecimal) XDataType.decimalType.cast(obj)).divide((BigDecimal) XDataType.decimalType.cast(obj2), 0, 1));
            }
            if (i <= 7) {
                return RealNum.toExactInt((double) (((Number) obj).floatValue() / ((Number) obj2).floatValue()), 3);
            }
            return RealNum.toExactInt(((Number) obj).doubleValue() / ((Number) obj2).doubleValue(), 3);
        }
        throw new UnsupportedOperationException(getName());
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp.compile(applyExp, compilation, target);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }
}
