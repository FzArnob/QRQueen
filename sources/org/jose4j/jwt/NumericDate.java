package org.jose4j.jwt;

import java.text.DateFormat;
import java.util.Date;
import org.jose4j.lang.Maths;

public class NumericDate {
    private static final long CONVERSION = 1000;
    private long value;

    private NumericDate(long j) {
        setValue(j);
    }

    public static NumericDate now() {
        return fromMilliseconds(System.currentTimeMillis());
    }

    public static NumericDate fromSeconds(long j) {
        return new NumericDate(j);
    }

    public static NumericDate fromMilliseconds(long j) {
        return fromSeconds(j / 1000);
    }

    public void addSeconds(long j) {
        setValue(Maths.add(this.value, j));
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long j) {
        this.value = j;
    }

    public long getValueInMillis() {
        long value2 = getValue();
        long j = 1000 * value2;
        if (canConvertToMillis()) {
            return j;
        }
        throw new ArithmeticException("converting " + value2 + " seconds to milliseconds (x1000) resulted in long integer overflow (" + j + ")");
    }

    private boolean canConvertToMillis() {
        long value2 = getValue();
        long j = 1000 * value2;
        int i = (value2 > 0 ? 1 : (value2 == 0 ? 0 : -1));
        if ((i <= 0 || j >= value2) && (i >= 0 || j <= value2)) {
            if (!(i == 0) || !(j != 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBefore(NumericDate numericDate) {
        return this.value < numericDate.getValue();
    }

    public boolean isOnOrAfter(NumericDate numericDate) {
        return !isBefore(numericDate);
    }

    public boolean isAfter(NumericDate numericDate) {
        return this.value > numericDate.getValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NumericDate");
        sb.append("{");
        sb.append(getValue());
        if (canConvertToMillis()) {
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 1);
            Date date = new Date(getValueInMillis());
            sb.append(" -> ");
            sb.append(dateTimeInstance.format(date));
        }
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof NumericDate) && this.value == ((NumericDate) obj).value);
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }
}
