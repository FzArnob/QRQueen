package kawa.standard;

import gnu.math.Quantity;
import gnu.math.Unit;
import kawa.lang.GenericError;

public class sleep {
    public static void sleep(Quantity quantity) {
        Unit unit = quantity.unit();
        if (unit == Unit.Empty || unit.dimensions() == Unit.second.dimensions()) {
            double doubleValue = quantity.doubleValue();
            long j = (long) (1000.0d * doubleValue);
            try {
                Thread.sleep(j, (int) ((doubleValue * 1.0E9d) - (((double) j) * 1000000.0d)));
            } catch (InterruptedException unused) {
                throw new GenericError("sleep was interrupted");
            }
        } else {
            throw new GenericError("bad unit for sleep");
        }
    }
}
