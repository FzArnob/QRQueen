package org.jose4j.jwt;

import java.util.Date;

public class IntDate {
    private static final long CONVERSION = 1000;
    private long value;

    private IntDate(long j) {
        this.value = j;
    }

    public static IntDate now() {
        return fromMillis(System.currentTimeMillis());
    }

    public static IntDate fromSeconds(long j) {
        return new IntDate(j);
    }

    public static IntDate fromMillis(long j) {
        return fromSeconds(j / 1000);
    }

    public void addSeconds(long j) {
        this.value += j;
    }

    public void addSeconds(int i) {
        addSeconds((long) i);
    }

    public long getValue() {
        return this.value;
    }

    public long getValueInMillis() {
        return getValue() * 1000;
    }

    public boolean before(IntDate intDate) {
        return this.value < intDate.getValue();
    }

    public boolean after(IntDate intDate) {
        return this.value > intDate.getValue();
    }

    public String toString() {
        return "IntDate" + "{" + getValue() + " --> " + new Date(getValueInMillis()) + '}';
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof IntDate) && this.value == ((IntDate) obj).value);
    }

    public int hashCode() {
        long j = this.value;
        return (int) (j ^ (j >>> 32));
    }
}
