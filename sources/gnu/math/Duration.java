package gnu.math;

import gnu.bytecode.Access;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Duration extends Quantity implements Externalizable {
    int months;
    int nanos;
    long seconds;
    public Unit unit;

    public boolean isExact() {
        return false;
    }

    public static Duration make(int i, long j, int i2, Unit unit2) {
        Duration duration = new Duration();
        duration.months = i;
        duration.seconds = j;
        duration.nanos = i2;
        duration.unit = unit2;
        return duration;
    }

    public static Duration makeMonths(int i) {
        Duration duration = new Duration();
        duration.unit = Unit.month;
        duration.months = i;
        return duration;
    }

    public static Duration makeMinutes(int i) {
        Duration duration = new Duration();
        duration.unit = Unit.second;
        duration.seconds = (long) (i * 60);
        return duration;
    }

    public static Duration parse(String str, Unit unit2) {
        Duration valueOf = valueOf(str, unit2);
        if (valueOf != null) {
            return valueOf;
        }
        throw new IllegalArgumentException("not a valid " + unit2.getName() + " duration: '" + str + "'");
    }

    public static Duration parseDuration(String str) {
        return parse(str, Unit.duration);
    }

    public static Duration parseYearMonthDuration(String str) {
        return parse(str, Unit.month);
    }

    public static Duration parseDayTimeDuration(String str) {
        return parse(str, Unit.second);
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x0133 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0134  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.math.Duration valueOf(java.lang.String r20, gnu.math.Unit r21) {
        /*
            r0 = r21
            java.lang.String r1 = r20.trim()
            int r2 = r1.length()
            r3 = 0
            r4 = 1
            if (r2 <= 0) goto L_0x0018
            char r5 = r1.charAt(r3)
            r6 = 45
            if (r5 != r6) goto L_0x0018
            r5 = 1
            goto L_0x001a
        L_0x0018:
            r4 = 0
            r5 = 0
        L_0x001a:
            int r6 = r4 + 1
            r7 = 0
            if (r6 >= r2) goto L_0x0147
            char r4 = r1.charAt(r4)
            r8 = 80
            if (r4 == r8) goto L_0x0029
            goto L_0x0147
        L_0x0029:
            long r10 = scanPart(r1, r6)
            int r4 = (int) r10
            int r6 = r4 >> 16
            char r4 = (char) r4
            gnu.math.NamedUnit r12 = gnu.math.Unit.second
            r13 = 89
            r14 = 77
            if (r0 != r12) goto L_0x003e
            if (r4 == r13) goto L_0x003d
            if (r4 != r14) goto L_0x003e
        L_0x003d:
            return r7
        L_0x003e:
            r12 = 32
            if (r4 != r13) goto L_0x0050
            long r10 = r10 >> r12
            int r4 = (int) r10
            int r4 = r4 * 12
            long r10 = scanPart(r1, r6)
            int r13 = (int) r10
            char r13 = (char) r13
            r8 = r10
            r10 = r4
            r4 = r13
            goto L_0x0052
        L_0x0050:
            r8 = r10
            r10 = 0
        L_0x0052:
            if (r4 != r14) goto L_0x0063
            long r10 = (long) r10
            long r17 = r8 >> r12
            long r10 = r10 + r17
            int r10 = (int) r10
            int r4 = (int) r8
            int r6 = r4 >> 16
            long r8 = scanPart(r1, r6)
            int r4 = (int) r8
            char r4 = (char) r4
        L_0x0063:
            gnu.math.NamedUnit r11 = gnu.math.Unit.month
            if (r0 != r11) goto L_0x006a
            if (r6 == r2) goto L_0x006a
            return r7
        L_0x006a:
            r11 = 68
            if (r4 != r11) goto L_0x0085
            gnu.math.NamedUnit r4 = gnu.math.Unit.month
            if (r0 != r4) goto L_0x0073
            return r7
        L_0x0073:
            r15 = 86400(0x15180, double:4.26873E-319)
            long r3 = r8 >> r12
            int r4 = (int) r3
            long r3 = (long) r4
            long r3 = r3 * r15
            int r6 = (int) r8
            int r6 = r6 >> 16
            long r8 = scanPart(r1, r6)
            r15 = r3
            goto L_0x0087
        L_0x0085:
            r15 = 0
        L_0x0087:
            int r3 = r6 << 16
            long r3 = (long) r3
            int r11 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r11 == 0) goto L_0x008f
            return r7
        L_0x008f:
            if (r6 != r2) goto L_0x0095
        L_0x0091:
            r8 = r15
            r3 = 0
            goto L_0x0131
        L_0x0095:
            char r3 = r1.charAt(r6)
            r4 = 84
            if (r3 != r4) goto L_0x0147
            int r6 = r6 + 1
            if (r6 != r2) goto L_0x00a3
            goto L_0x0147
        L_0x00a3:
            gnu.math.NamedUnit r3 = gnu.math.Unit.month
            if (r0 != r3) goto L_0x00a8
            return r7
        L_0x00a8:
            long r3 = scanPart(r1, r6)
            int r8 = (int) r3
            char r9 = (char) r8
            r11 = 72
            if (r9 != r11) goto L_0x00c0
            long r3 = r3 >> r12
            int r4 = (int) r3
            int r4 = r4 * 3600
            long r3 = (long) r4
            long r15 = r15 + r3
            int r6 = r8 >> 16
            long r3 = scanPart(r1, r6)
            int r8 = (int) r3
            char r9 = (char) r8
        L_0x00c0:
            if (r9 != r14) goto L_0x00d2
            long r8 = r3 >> r12
            int r6 = (int) r8
            int r6 = r6 * 60
            long r8 = (long) r6
            long r15 = r15 + r8
            int r4 = (int) r3
            int r6 = r4 >> 16
            long r3 = scanPart(r1, r6)
            int r8 = (int) r3
            char r9 = (char) r8
        L_0x00d2:
            r8 = 46
            r11 = 83
            if (r9 == r11) goto L_0x00da
            if (r9 != r8) goto L_0x00e3
        L_0x00da:
            long r12 = r3 >> r12
            int r6 = (int) r12
            long r12 = (long) r6
            long r15 = r15 + r12
            int r4 = (int) r3
            int r3 = r4 >> 16
            r6 = r3
        L_0x00e3:
            if (r9 != r8) goto L_0x0091
            int r3 = r6 + 1
            if (r3 >= r2) goto L_0x0091
            char r3 = r1.charAt(r6)
            r4 = 10
            int r3 = java.lang.Character.digit(r3, r4)
            if (r3 < 0) goto L_0x0091
            r8 = r6
            r3 = 0
            r6 = 0
        L_0x00f8:
            r12 = 9
            if (r8 >= r2) goto L_0x0123
            int r9 = r8 + 1
            char r8 = r1.charAt(r8)
            int r13 = java.lang.Character.digit(r8, r4)
            if (r13 >= 0) goto L_0x010e
            r19 = r9
            r9 = r8
            r8 = r19
            goto L_0x0123
        L_0x010e:
            if (r3 >= r12) goto L_0x0114
            int r6 = r6 * 10
            int r6 = r6 + r13
            goto L_0x011b
        L_0x0114:
            if (r3 != r12) goto L_0x011b
            r12 = 5
            if (r13 < r12) goto L_0x011b
            int r6 = r6 + 1
        L_0x011b:
            int r3 = r3 + 1
            r19 = r9
            r9 = r8
            r8 = r19
            goto L_0x00f8
        L_0x0123:
            int r1 = r3 + 1
            if (r3 >= r12) goto L_0x012b
            int r6 = r6 * 10
            r3 = r1
            goto L_0x0123
        L_0x012b:
            if (r9 == r11) goto L_0x012e
            return r7
        L_0x012e:
            r3 = r6
            r6 = r8
            r8 = r15
        L_0x0131:
            if (r6 == r2) goto L_0x0134
            return r7
        L_0x0134:
            gnu.math.Duration r1 = new gnu.math.Duration
            r1.<init>()
            if (r5 == 0) goto L_0x013e
            int r10 = -r10
            long r8 = -r8
            int r3 = -r3
        L_0x013e:
            r1.months = r10
            r1.seconds = r8
            r1.nanos = r3
            r1.unit = r0
            return r1
        L_0x0147:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.math.Duration.valueOf(java.lang.String, gnu.math.Unit):gnu.math.Duration");
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof Duration) {
            return add(this, (Duration) obj, i);
        }
        if ((obj instanceof DateTime) && i == 1) {
            return DateTime.add((DateTime) obj, this, 1);
        }
        throw new IllegalArgumentException();
    }

    public Numeric mul(Object obj) {
        if (obj instanceof RealNum) {
            return times(this, ((RealNum) obj).doubleValue());
        }
        return ((Numeric) obj).mulReversed(this);
    }

    public Numeric mulReversed(Numeric numeric) {
        if (numeric instanceof RealNum) {
            return times(this, ((RealNum) numeric).doubleValue());
        }
        throw new IllegalArgumentException();
    }

    public static double div(Duration duration, Duration duration2) {
        int i = duration.months;
        int i2 = duration2.months;
        int i3 = duration.nanos;
        double d = ((double) duration.seconds) + (((double) i3) * 1.0E-9d);
        double d2 = ((double) duration2.seconds) + (((double) i3) * 1.0E-9d);
        if (i2 == 0 && d2 == 0.0d) {
            throw new ArithmeticException("divide duration by zero");
        }
        if (i2 == 0) {
            if (i == 0) {
                return d / d2;
            }
        } else if (d2 == 0.0d && d == 0.0d) {
            return ((double) i) / ((double) i2);
        }
        throw new ArithmeticException("divide of incompatible durations");
    }

    public Numeric div(Object obj) {
        if (obj instanceof RealNum) {
            double doubleValue = ((RealNum) obj).doubleValue();
            if (doubleValue != 0.0d && !Double.isNaN(doubleValue)) {
                return times(this, 1.0d / doubleValue);
            }
            throw new ArithmeticException("divide of duration by 0 or NaN");
        } else if (obj instanceof Duration) {
            return new DFloNum(div(this, (Duration) obj));
        } else {
            return ((Numeric) obj).divReversed(this);
        }
    }

    public static Duration add(Duration duration, Duration duration2, int i) {
        long j = (long) i;
        long j2 = ((long) duration.months) + (((long) duration2.months) * j);
        long j3 = (duration.seconds * 1000000000) + ((long) duration.nanos) + (j * ((duration2.seconds * 1000000000) + ((long) duration2.nanos)));
        Duration duration3 = new Duration();
        duration3.months = (int) j2;
        duration3.seconds = (long) ((int) (j3 / 1000000000));
        duration3.nanos = (int) (j3 % 1000000000);
        Unit unit2 = duration.unit;
        if (unit2 != duration2.unit || unit2 == Unit.duration) {
            throw new ArithmeticException("cannot add these duration types");
        }
        duration3.unit = duration.unit;
        return duration3;
    }

    public static Duration times(Duration duration, double d) {
        if (duration.unit != Unit.duration) {
            double d2 = ((double) duration.months) * d;
            if (Double.isInfinite(d2) || Double.isNaN(d2)) {
                throw new ArithmeticException("overflow/NaN when multiplying a duration");
            }
            double d3 = ((double) ((duration.seconds * 1000000000) + ((long) duration.nanos))) * d;
            Duration duration2 = new Duration();
            duration2.months = (int) Math.floor(d2 + 0.5d);
            duration2.seconds = (long) ((int) (d3 / 1.0E9d));
            duration2.nanos = (int) (d3 % 1.0E9d);
            duration2.unit = duration.unit;
            return duration2;
        }
        throw new IllegalArgumentException("cannot multiply general duration");
    }

    public static int compare(Duration duration, Duration duration2) {
        long j = ((long) duration.months) - ((long) duration2.months);
        long j2 = ((duration.seconds * 1000000000) + ((long) duration.nanos)) - ((duration2.seconds * 1000000000) + ((long) duration2.nanos));
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0 && j2 <= 0) {
            return -1;
        }
        if (i > 0 && j2 >= 0) {
            return 1;
        }
        if (i != 0) {
            return -2;
        }
        int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i2 < 0) {
            return -1;
        }
        return i2 > 0 ? 1 : 0;
    }

    public int compare(Object obj) {
        if (obj instanceof Duration) {
            return compare(this, (Duration) obj);
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.months;
        long j = this.seconds;
        int i2 = this.nanos;
        if (i < 0 || j < 0 || i2 < 0) {
            i = -i;
            j = -j;
            i2 = -i2;
            stringBuffer.append('-');
        }
        stringBuffer.append('P');
        int i3 = i / 12;
        if (i3 != 0) {
            stringBuffer.append(i3);
            stringBuffer.append('Y');
            i -= i3 * 12;
        }
        if (i != 0) {
            stringBuffer.append(i);
            stringBuffer.append(Access.METHOD_CONTEXT);
        }
        long j2 = j / 86400;
        if (j2 != 0) {
            stringBuffer.append(j2);
            stringBuffer.append('D');
            j -= j2 * 86400;
        }
        if (j != 0 || i2 != 0) {
            stringBuffer.append('T');
            long j3 = j / 3600;
            if (j3 != 0) {
                stringBuffer.append(j3);
                stringBuffer.append('H');
                j -= j3 * 3600;
            }
            long j4 = j / 60;
            if (j4 != 0) {
                stringBuffer.append(j4);
                stringBuffer.append(Access.METHOD_CONTEXT);
                j -= j4 * 60;
            }
            if (!(j == 0 && i2 == 0)) {
                stringBuffer.append(j);
                appendNanoSeconds(i2, stringBuffer);
                stringBuffer.append('S');
            }
        } else if (stringBuffer.length() == 1) {
            stringBuffer.append(this.unit == Unit.month ? "0M" : "T0S");
        }
        return stringBuffer.toString();
    }

    static void appendNanoSeconds(int i, StringBuffer stringBuffer) {
        if (i != 0) {
            stringBuffer.append('.');
            int length = stringBuffer.length();
            stringBuffer.append(i);
            int i2 = length + 9;
            int length2 = i2 - stringBuffer.length();
            while (true) {
                length2--;
                if (length2 < 0) {
                    break;
                }
                stringBuffer.insert(length, '0');
            }
            do {
                i2--;
            } while (stringBuffer.charAt(i2) == '0');
            stringBuffer.setLength(i2 + 1);
        }
    }

    private static long scanPart(String str, int i) {
        int length = str.length();
        int i2 = i;
        long j = -1;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            i2++;
            int digit = Character.digit(charAt, 10);
            if (digit < 0) {
                return j < 0 ? (long) (i << 16) : (j << 32) | ((long) (i2 << 16)) | ((long) charAt);
            }
            j = j < 0 ? (long) digit : (j * 10) + ((long) digit);
            if (j > 2147483647L) {
                return -1;
            }
        }
        if (j < 0) {
            return (long) (i << 16);
        }
        return -1;
    }

    public int getYears() {
        return this.months / 12;
    }

    public int getMonths() {
        return this.months % 12;
    }

    public int getDays() {
        return (int) (this.seconds / 86400);
    }

    public int getHours() {
        return (int) ((this.seconds / 3600) % 24);
    }

    public int getMinutes() {
        return (int) ((this.seconds / 60) % 60);
    }

    public int getSecondsOnly() {
        return (int) (this.seconds % 60);
    }

    public int getNanoSecondsOnly() {
        return this.nanos;
    }

    public int getTotalMonths() {
        return this.months;
    }

    public long getTotalSeconds() {
        return this.seconds;
    }

    public long getTotalMinutes() {
        return this.seconds / 60;
    }

    public long getNanoSeconds() {
        return (this.seconds * 1000000000) + ((long) this.nanos);
    }

    public boolean isZero() {
        return this.months == 0 && this.seconds == 0 && this.nanos == 0;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.months);
        objectOutput.writeLong(this.seconds);
        objectOutput.writeInt(this.nanos);
        objectOutput.writeObject(this.unit);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.months = objectInput.readInt();
        this.seconds = objectInput.readLong();
        this.nanos = objectInput.readInt();
        this.unit = (Unit) objectInput.readObject();
    }

    public Unit unit() {
        return this.unit;
    }

    public Complex number() {
        throw new Error("number needs to be implemented!");
    }

    public int hashCode() {
        return (this.months ^ ((int) this.seconds)) ^ this.nanos;
    }

    public static boolean equals(Duration duration, Duration duration2) {
        return duration.months == duration2.months && duration.seconds == duration2.seconds && duration.nanos == duration2.nanos;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Duration)) {
            return false;
        }
        return equals(this, (Duration) obj);
    }
}
