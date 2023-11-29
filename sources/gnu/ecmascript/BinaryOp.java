package gnu.ecmascript;

import gnu.mapping.Procedure2;

public class BinaryOp extends Procedure2 {
    int op;

    public BinaryOp(String str, int i) {
        setName(str);
        this.op = i;
    }

    public Object apply2(Object obj, Object obj2) {
        if (this.op == 5) {
            return Convert.toNumber(obj) < Convert.toNumber(obj2) ? Boolean.TRUE : Boolean.FALSE;
        }
        return new Double(apply(Convert.toNumber(obj), Convert.toNumber(obj2)));
    }

    public double apply(double d, double d2) {
        int i = this.op;
        if (i == 1) {
            return d + d2;
        }
        if (i == 2) {
            return d - d2;
        }
        if (i == 3) {
            return d * d2;
        }
        if (i != 4) {
            return Double.NaN;
        }
        return (double) (((int) d) << (((int) d2) & 31));
    }
}
