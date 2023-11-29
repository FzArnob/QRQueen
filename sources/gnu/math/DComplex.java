package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DComplex extends Complex implements Externalizable {
    double imag;
    double real;

    public boolean isExact() {
        return false;
    }

    public DComplex() {
    }

    public DComplex(double d, double d2) {
        this.real = d;
        this.imag = d2;
    }

    public RealNum re() {
        return new DFloNum(this.real);
    }

    public double doubleValue() {
        return this.real;
    }

    public RealNum im() {
        return new DFloNum(this.imag);
    }

    public double doubleImagValue() {
        return this.imag;
    }

    public Complex toExact() {
        return new CComplex(DFloNum.toExact(this.real), DFloNum.toExact(this.imag));
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Complex)) {
            return false;
        }
        Complex complex = (Complex) obj;
        if (complex.unit() == Unit.Empty && Double.doubleToLongBits(this.real) == Double.doubleToLongBits(complex.reValue()) && Double.doubleToLongBits(this.imag) == Double.doubleToLongBits(complex.imValue())) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r12 = this;
            double r0 = r12.real
            r2 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r4 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            java.lang.String r6 = "#i"
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 != 0) goto L_0x0010
            java.lang.String r0 = "1/0"
        L_0x000e:
            r1 = r6
            goto L_0x0028
        L_0x0010:
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 != 0) goto L_0x0017
            java.lang.String r0 = "-1/0"
            goto L_0x000e
        L_0x0017:
            boolean r0 = java.lang.Double.isNaN(r0)
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = "0/0"
            goto L_0x000e
        L_0x0020:
            double r0 = r12.real
            java.lang.String r0 = java.lang.Double.toString(r0)
            java.lang.String r1 = ""
        L_0x0028:
            double r7 = r12.imag
            long r7 = java.lang.Double.doubleToLongBits(r7)
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0044
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            return r0
        L_0x0044:
            double r7 = r12.imag
            int r11 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r11 != 0) goto L_0x004d
            java.lang.String r1 = "+1/0i"
            goto L_0x0090
        L_0x004d:
            int r4 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0054
            java.lang.String r1 = "-1/0i"
            goto L_0x0090
        L_0x0054:
            boolean r2 = java.lang.Double.isNaN(r7)
            if (r2 == 0) goto L_0x005d
            java.lang.String r1 = "+0/0i"
            goto L_0x0090
        L_0x005d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            double r3 = r12.imag
            java.lang.String r3 = java.lang.Double.toString(r3)
            r2.append(r3)
            java.lang.String r3 = "i"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            char r3 = r2.charAt(r3)
            r4 = 45
            if (r3 == r4) goto L_0x008e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "+"
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
        L_0x008e:
            r6 = r1
            r1 = r2
        L_0x0090:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            double r3 = r12.real
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x00a0
            goto L_0x00af
        L_0x00a0:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r6)
            r3.append(r0)
            java.lang.String r6 = r3.toString()
        L_0x00af:
            r2.append(r6)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.DComplex.toString():java.lang.String");
    }

    public String toString(int i) {
        if (i == 10) {
            return toString();
        }
        return "#d" + toString();
    }

    public final Numeric neg() {
        return new DComplex(-this.real, -this.imag);
    }

    public Numeric add(Object obj, int i) {
        if (!(obj instanceof Complex)) {
            return ((Numeric) obj).addReversed(this, i);
        }
        Complex complex = (Complex) obj;
        if (complex.dimensions() == Dimensions.Empty) {
            double d = (double) i;
            return new DComplex(this.real + (complex.reValue() * d), this.imag + (d * complex.imValue()));
        }
        throw new ArithmeticException("units mis-match");
    }

    public Numeric mul(Object obj) {
        if (!(obj instanceof Complex)) {
            return ((Numeric) obj).mulReversed(this);
        }
        Complex complex = (Complex) obj;
        if (complex.unit() != Unit.Empty) {
            return Complex.times(this, complex);
        }
        double reValue = complex.reValue();
        double imValue = complex.imValue();
        double d = this.real;
        double d2 = this.imag;
        return new DComplex((d * reValue) - (d2 * imValue), (d * imValue) + (d2 * reValue));
    }

    public Numeric div(Object obj) {
        if (!(obj instanceof Complex)) {
            return ((Numeric) obj).divReversed(this);
        }
        Complex complex = (Complex) obj;
        return div(this.real, this.imag, complex.doubleValue(), complex.doubleImagValue());
    }

    public static DComplex power(double d, double d2, double d3, double d4) {
        double log = Math.log(Math.hypot(d, d2));
        double atan2 = Math.atan2(d2, d);
        return Complex.polar(Math.exp((log * d3) - (d4 * atan2)), (d4 * log) + (d3 * atan2));
    }

    public static Complex log(double d, double d2) {
        return make(Math.log(Math.hypot(d, d2)), Math.atan2(d2, d));
    }

    public static DComplex div(double d, double d2, double d3, double d4) {
        double d5;
        double d6;
        double d7;
        if (Math.abs(d3) <= Math.abs(d4)) {
            double d8 = d3 / d4;
            d5 = d4 * ((d8 * d8) + 1.0d);
            d7 = (d * d8) + d2;
            d6 = (d2 * d8) - d;
        } else {
            double d9 = d4 / d3;
            d7 = (d2 * d9) + d;
            d6 = d2 - (d * d9);
            d5 = d3 * ((d9 * d9) + 1.0d);
        }
        return new DComplex(d7 / d5, d6 / d5);
    }

    public static Complex sqrt(double d, double d2) {
        double d3;
        double hypot = Math.hypot(d, d2);
        if (hypot == 0.0d) {
            d3 = hypot;
        } else if (d > 0.0d) {
            hypot = Math.sqrt((hypot + d) * 0.5d);
            d3 = (d2 / hypot) / 2.0d;
        } else {
            double sqrt = Math.sqrt((hypot - d) * 0.5d);
            if (d2 < 0.0d) {
                sqrt = -sqrt;
            }
            double d4 = sqrt;
            d3 = d4;
            hypot = (d2 / d4) / 2.0d;
        }
        return new DComplex(hypot, d3);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeDouble(this.real);
        objectOutput.writeDouble(this.imag);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.real = objectInput.readDouble();
        this.imag = objectInput.readDouble();
    }
}
