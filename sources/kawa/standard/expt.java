package kawa.standard;

import gnu.mapping.Procedure2;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.math.Numeric;

public class expt extends Procedure2 {
    public static final expt expt = new expt("expt");

    public expt(String str) {
        super(str);
    }

    public static IntNum expt(IntNum intNum, int i) {
        return IntNum.power(intNum, i);
    }

    public static Numeric expt(Object obj, Object obj2) {
        if (obj2 instanceof IntNum) {
            return ((Numeric) obj).power((IntNum) obj2);
        }
        return Complex.power((Complex) obj, (Complex) obj2);
    }

    public Object apply2(Object obj, Object obj2) {
        return expt(obj, obj2);
    }
}
