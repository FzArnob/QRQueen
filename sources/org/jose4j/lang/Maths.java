package org.jose4j.lang;

public class Maths {
    public static long add(long j, long j2) {
        long j3 = j + j2;
        if (0 <= ((j ^ j3) & (j2 ^ j3))) {
            return j3;
        }
        throw new ArithmeticException("long overflow adding: " + j + " + " + j2 + " = " + j3);
    }

    public static long subtract(long j, long j2) {
        long j3 = j - j2;
        if (0 <= ((j ^ j2) & (j ^ j3))) {
            return j3;
        }
        throw new ArithmeticException("long overflow subtracting: " + j + " - " + j2 + " = " + j3);
    }
}
