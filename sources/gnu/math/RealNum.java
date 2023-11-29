package gnu.math;

import java.math.BigDecimal;

public abstract class RealNum extends Complex implements Comparable {
    public abstract Numeric add(Object obj, int i);

    public abstract Numeric div(Object obj);

    public abstract boolean isNegative();

    public abstract Numeric mul(Object obj);

    public final RealNum re() {
        return this;
    }

    public abstract int sign();

    public final RealNum im() {
        return IntNum.zero();
    }

    public static RealNum asRealNumOrNull(Object obj) {
        if (obj instanceof RealNum) {
            return (RealNum) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return new DFloNum(((Number) obj).doubleValue());
        }
        return RatNum.asRatNumOrNull(obj);
    }

    public RealNum max(RealNum realNum) {
        boolean z = isExact() && realNum.isExact();
        if (grt(realNum)) {
            realNum = this;
        }
        return (z || !realNum.isExact()) ? realNum : new DFloNum(realNum.doubleValue());
    }

    public RealNum min(RealNum realNum) {
        boolean z = isExact() && realNum.isExact();
        if (!grt(realNum)) {
            realNum = this;
        }
        return (z || !realNum.isExact()) ? realNum : new DFloNum(realNum.doubleValue());
    }

    public static RealNum add(RealNum realNum, RealNum realNum2, int i) {
        RealNum realNum3 = (RealNum) realNum.add(realNum2, i);
        RealNum realNum4 = realNum3;
        return realNum3;
    }

    public static RealNum times(RealNum realNum, RealNum realNum2) {
        RealNum realNum3 = (RealNum) realNum.mul(realNum2);
        RealNum realNum4 = realNum3;
        return realNum3;
    }

    public static RealNum divide(RealNum realNum, RealNum realNum2) {
        RealNum realNum3 = (RealNum) realNum.div(realNum2);
        RealNum realNum4 = realNum3;
        return realNum3;
    }

    public Numeric abs() {
        return isNegative() ? neg() : this;
    }

    public RealNum rneg() {
        return (RealNum) neg();
    }

    public boolean isZero() {
        return sign() == 0;
    }

    public RatNum toExact() {
        return DFloNum.toExact(doubleValue());
    }

    public RealNum toInexact() {
        return isExact() ? new DFloNum(doubleValue()) : this;
    }

    public static double toInt(double d, int i) {
        if (i == 1) {
            return Math.floor(d);
        }
        if (i != 2) {
            return i != 3 ? i != 4 ? d : Math.rint(d) : d < 0.0d ? Math.ceil(d) : Math.floor(d);
        }
        return Math.ceil(d);
    }

    public RealNum toInt(int i) {
        return new DFloNum(toInt(doubleValue(), i));
    }

    public IntNum toExactInt(int i) {
        return toExactInt(doubleValue(), i);
    }

    public static IntNum toExactInt(double d, int i) {
        return toExactInt(toInt(d, i));
    }

    public static IntNum toExactInt(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new ArithmeticException("cannot convert " + d + " to exact integer");
        }
        long doubleToLongBits = Double.doubleToLongBits(d);
        boolean z = doubleToLongBits < 0;
        int i = ((int) (doubleToLongBits >> 52)) & 2047;
        long j = doubleToLongBits & 4503599627370495L;
        long j2 = i == 0 ? j << 1 : j | 4503599627370496L;
        if (i <= 1075) {
            int i2 = 1075 - i;
            if (i2 > 53) {
                return IntNum.zero();
            }
            long j3 = j2 >> i2;
            if (z) {
                j3 = -j3;
            }
            return IntNum.make(j3);
        }
        if (z) {
            j2 = -j2;
        }
        return IntNum.shift(IntNum.make(j2), i - 1075);
    }

    public Complex exp() {
        return new DFloNum(Math.exp(doubleValue()));
    }

    public Complex log() {
        double doubleValue = doubleValue();
        if (doubleValue < 0.0d) {
            return DComplex.log(doubleValue, 0.0d);
        }
        return new DFloNum(Math.log(doubleValue));
    }

    public final Complex sin() {
        return new DFloNum(Math.sin(doubleValue()));
    }

    public final Complex sqrt() {
        double doubleValue = doubleValue();
        if (doubleValue >= 0.0d) {
            return new DFloNum(Math.sqrt(doubleValue));
        }
        return DComplex.sqrt(doubleValue, 0.0d);
    }

    public static IntNum toScaledInt(double d, int i) {
        return toScaledInt(DFloNum.toExact(d), i);
    }

    public static IntNum toScaledInt(RatNum ratNum, int i) {
        if (i != 0) {
            IntNum power = IntNum.power(IntNum.ten(), i < 0 ? -i : i);
            IntNum numerator = ratNum.numerator();
            IntNum denominator = ratNum.denominator();
            if (i >= 0) {
                numerator = IntNum.times(numerator, power);
            } else {
                denominator = IntNum.times(denominator, power);
            }
            ratNum = RatNum.make(numerator, denominator);
        }
        return ratNum.toExactInt(4);
    }

    public IntNum toScaledInt(int i) {
        return toScaledInt(toExact(), i);
    }

    public int compareTo(Object obj) {
        return compare(obj);
    }

    public BigDecimal asBigDecimal() {
        return new BigDecimal(doubleValue());
    }

    public static String toStringScientific(float f) {
        return toStringScientific(Float.toString(f));
    }

    public static String toStringScientific(double d) {
        return toStringScientific(Double.toString(d));
    }

    public static String toStringScientific(String str) {
        if (str.indexOf(69) >= 0) {
            return str;
        }
        int length = str.length();
        char charAt = str.charAt(length - 1);
        if (charAt == 'y' || charAt == 'N') {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(length + 10);
        int stringScientific = toStringScientific(str, stringBuffer);
        stringBuffer.append('E');
        stringBuffer.append(stringScientific);
        return stringBuffer.toString();
    }

    public static int toStringScientific(String str, StringBuffer stringBuffer) {
        char charAt;
        int i;
        char charAt2;
        int i2 = 0;
        int i3 = 1;
        int i4 = str.charAt(0) == '-' ? 1 : 0;
        if (i4 != 0) {
            stringBuffer.append('-');
        }
        int length = str.length();
        if (str.charAt(i4) == '0') {
            int i5 = i4;
            while (true) {
                if (i5 == length) {
                    stringBuffer.append("0");
                    break;
                }
                i = i5 + 1;
                charAt2 = str.charAt(i5);
                if (charAt2 < '0' || charAt2 > '9' || (charAt2 == '0' && i != length)) {
                    i5 = i;
                }
            }
            stringBuffer.append(charAt2);
            stringBuffer.append('.');
            if (charAt2 != '0') {
                i2 = (i4 - i) + 2;
            }
            if (i == length) {
                stringBuffer.append('0');
            } else {
                while (i < length) {
                    stringBuffer.append(str.charAt(i));
                    i++;
                }
            }
        } else {
            if (i4 != 0) {
                i3 = 2;
            }
            i2 = ((length - i3) - length) + str.indexOf(46);
            int i6 = i4 + 1;
            stringBuffer.append(str.charAt(i4));
            stringBuffer.append('.');
            while (i6 < length) {
                int i7 = i6 + 1;
                char charAt3 = str.charAt(i6);
                if (charAt3 != '.') {
                    stringBuffer.append(charAt3);
                }
                i6 = i7;
            }
        }
        int length2 = stringBuffer.length();
        int i8 = -1;
        while (true) {
            length2--;
            charAt = stringBuffer.charAt(length2);
            if (charAt != '0') {
                break;
            }
            i8 = length2;
        }
        if (charAt == '.') {
            i8 = length2 + 2;
        }
        if (i8 >= 0) {
            stringBuffer.setLength(i8);
        }
        return i2;
    }

    public static String toStringDecimal(String str) {
        int indexOf = str.indexOf(69);
        if (indexOf < 0) {
            return str;
        }
        int length = str.length();
        char charAt = str.charAt(length - 1);
        if (charAt == 'y' || charAt == 'N') {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(length + 10);
        boolean z = str.charAt(0) == '-';
        if (str.charAt(indexOf + 1) == '-') {
            int i = 0;
            for (int i2 = indexOf + 2; i2 < length; i2++) {
                i = (i * 10) + (str.charAt(i2) - '0');
            }
            if (z) {
                stringBuffer.append('-');
            }
            stringBuffer.append("0.");
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                stringBuffer.append('0');
            }
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                char charAt2 = str.charAt(i3);
                if (charAt2 == 'E') {
                    return stringBuffer.toString();
                }
                if (((charAt2 != '-') && (charAt2 != '.')) && (charAt2 != '0' || i4 < indexOf)) {
                    stringBuffer.append(charAt2);
                }
                i3 = i4;
            }
        } else {
            throw new Error("not implemented: toStringDecimal given non-negative exponent: " + str);
        }
    }
}
