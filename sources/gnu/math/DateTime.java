package gnu.math;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTime extends Quantity implements Cloneable {
    public static final int DATE_MASK = 14;
    static final int DAY_COMPONENT = 3;
    public static final int DAY_MASK = 8;
    public static TimeZone GMT = TimeZone.getTimeZone("GMT");
    static final int HOURS_COMPONENT = 4;
    public static final int HOURS_MASK = 16;
    static final int MINUTES_COMPONENT = 5;
    public static final int MINUTES_MASK = 32;
    static final int MONTH_COMPONENT = 2;
    public static final int MONTH_MASK = 4;
    static final int SECONDS_COMPONENT = 6;
    public static final int SECONDS_MASK = 64;
    static final int TIMEZONE_COMPONENT = 7;
    public static final int TIMEZONE_MASK = 128;
    public static final int TIME_MASK = 112;
    static final int YEAR_COMPONENT = 1;
    public static final int YEAR_MASK = 2;
    private static final Date minDate = new Date(Long.MIN_VALUE);
    GregorianCalendar calendar;
    int mask;
    int nanoSeconds;
    Unit unit = Unit.date;

    public int components() {
        return this.mask & -129;
    }

    public DateTime cast(int i) {
        int i2 = this.mask & -129;
        if (i == i2) {
            return this;
        }
        DateTime dateTime = new DateTime(i, (GregorianCalendar) this.calendar.clone());
        if (((~i2) & i) == 0 || (i2 == 14 && i == 126)) {
            if (isZoneUnspecified()) {
                dateTime.mask &= -129;
            } else {
                dateTime.mask |= 128;
            }
            int i3 = (~i) & i2;
            if ((i3 & 112) != 0) {
                dateTime.calendar.clear(11);
                dateTime.calendar.clear(12);
                dateTime.calendar.clear(13);
            } else {
                dateTime.nanoSeconds = this.nanoSeconds;
            }
            if ((i3 & 2) != 0) {
                dateTime.calendar.clear(1);
                dateTime.calendar.clear(0);
            }
            if ((i3 & 4) != 0) {
                dateTime.calendar.clear(2);
            }
            if ((i3 & 8) != 0) {
                dateTime.calendar.clear(5);
            }
            return dateTime;
        }
        throw new ClassCastException("cannot cast DateTime - missing conponents");
    }

    public DateTime(int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.calendar = gregorianCalendar;
        gregorianCalendar.setGregorianChange(minDate);
        this.calendar.clear();
        this.mask = i;
    }

    public DateTime(int i, GregorianCalendar gregorianCalendar) {
        this.calendar = gregorianCalendar;
        this.mask = i;
    }

    public static DateTime parse(String str, int i) {
        DateTime dateTime = new DateTime(i);
        String trim = str.trim();
        int length = trim.length();
        boolean z = true;
        int i2 = 0;
        boolean z2 = (i & 14) != 0;
        if ((i & 112) == 0) {
            z = false;
        }
        if (z2) {
            i2 = dateTime.parseDate(trim, 0, i);
            if (z) {
                i2 = (i2 < 0 || i2 >= length || trim.charAt(i2) != 'T') ? -1 : i2 + 1;
            }
        }
        if (z) {
            i2 = dateTime.parseTime(trim, i2);
        }
        if (dateTime.parseZone(trim, i2) == length) {
            return dateTime;
        }
        throw new NumberFormatException("Unrecognized date/time '" + trim + '\'');
    }

    /* access modifiers changed from: package-private */
    public int parseDate(String str, int i, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        if (i < 0) {
            return i;
        }
        int length = str.length();
        if (i >= length || str.charAt(i) != '-') {
            z = false;
        } else {
            i++;
            z = true;
        }
        int i6 = i2 & 2;
        if (i6 != 0) {
            int parseDigits = parseDigits(str, i);
            i3 = parseDigits >> 16;
            int i7 = parseDigits & 65535;
            int i8 = i + 4;
            if (i7 != i8 && (i7 <= i8 || str.charAt(i) == '0')) {
                return -1;
            }
            if (z || i3 == 0) {
                this.calendar.set(0, 0);
                this.calendar.set(1, i3 + 1);
            } else {
                this.calendar.set(1, i3);
            }
            i = i7;
        } else if (!z) {
            return -1;
        } else {
            i3 = -1;
        }
        if ((i2 & 12) == 0) {
            return i;
        }
        if (i < length && str.charAt(i) == '-') {
            int i9 = i + 1;
            int i10 = i2 & 4;
            if (i10 != 0) {
                int parseDigits2 = parseDigits(str, i9);
                i4 = parseDigits2 >> 16;
                int i11 = parseDigits2 & 65535;
                if (i4 <= 0 || i4 > 12 || i11 != i9 + 2) {
                    return -1;
                }
                this.calendar.set(2, i4 - 1);
                if ((i2 & 8) == 0) {
                    return i11;
                }
                i9 = i11;
            } else {
                i4 = -1;
            }
            if (i9 < length && str.charAt(i9) == '-') {
                int i12 = i9 + 1;
                int parseDigits3 = parseDigits(str, i12);
                int i13 = parseDigits3 >> 16;
                int i14 = parseDigits3 & 65535;
                if (i13 > 0 && i14 == i12 + 2) {
                    if (i10 == 0) {
                        i5 = 31;
                    } else {
                        int i15 = i4 - 1;
                        if (i6 == 0) {
                            i3 = 2000;
                        }
                        i5 = daysInMonth(i15, i3);
                    }
                    if (i13 <= i5) {
                        this.calendar.set(5, i13);
                        return i14;
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    public static int daysInMonth(int i, int i2) {
        return i != 1 ? (i == 3 || i == 5 || i == 8 || i == 10) ? 30 : 31 : isLeapYear(i2) ? 29 : 28;
    }

    /* access modifiers changed from: package-private */
    public int parseZone(String str, int i) {
        TimeZone timeZone;
        if (i < 0) {
            return i;
        }
        int parseZoneMinutes = parseZoneMinutes(str, i);
        if (parseZoneMinutes == 0) {
            return -1;
        }
        if (parseZoneMinutes == i) {
            return i;
        }
        int i2 = parseZoneMinutes >> 16;
        int i3 = parseZoneMinutes & 65535;
        if (i2 == 0) {
            timeZone = GMT;
        } else {
            timeZone = TimeZone.getTimeZone("GMT" + str.substring(i, i3));
        }
        this.calendar.setTimeZone(timeZone);
        this.mask |= 128;
        return i3;
    }

    /* access modifiers changed from: package-private */
    public int parseZoneMinutes(String str, int i) {
        int length = str.length();
        if (i == length || i < 0) {
            return i;
        }
        char charAt = str.charAt(i);
        if (charAt == 'Z') {
            return i + 1;
        }
        if (charAt != '+' && charAt != '-') {
            return i;
        }
        int i2 = i + 1;
        int parseDigits = parseDigits(str, i2);
        int i3 = parseDigits >> 16;
        if (i3 > 14) {
            return 0;
        }
        int i4 = i3 * 60;
        int i5 = parseDigits & 65535;
        if (i5 != i2 + 2 || i5 >= length) {
            return 0;
        }
        if (str.charAt(i5) == ':') {
            int i6 = i5 + 1;
            int parseDigits2 = parseDigits(str, i6);
            int i7 = parseDigits2 & 65535;
            int i8 = parseDigits2 >> 16;
            if (i8 > 0 && (i8 >= 60 || i3 == 14)) {
                return 0;
            }
            i4 += i8;
            if (i7 != i6 + 2) {
                return 0;
            }
            i5 = i7;
        }
        if (i4 > 840) {
            return 0;
        }
        if (charAt == '-') {
            i4 = -i4;
        }
        return (i4 << 16) | i5;
    }

    /* access modifiers changed from: package-private */
    public int parseTime(String str, int i) {
        int digit;
        if (i < 0) {
            return i;
        }
        int length = str.length();
        int parseDigits = parseDigits(str, i);
        int i2 = parseDigits >> 16;
        int i3 = parseDigits & 65535;
        if (i2 <= 24 && i3 == i + 2 && i3 != length && str.charAt(i3) == ':') {
            int i4 = i3 + 1;
            int parseDigits2 = parseDigits(str, i4);
            int i5 = parseDigits2 >> 16;
            int i6 = parseDigits2 & 65535;
            if (i5 < 60 && i6 == i4 + 2 && i6 != length && str.charAt(i6) == ':') {
                int i7 = i6 + 1;
                int parseDigits3 = parseDigits(str, i7);
                int i8 = parseDigits3 >> 16;
                int i9 = parseDigits3 & 65535;
                if (i8 < 60 && i9 == i7 + 2) {
                    int i10 = i9 + 1;
                    if (i10 < length && str.charAt(i9) == '.' && Character.digit(str.charAt(i10), 10) >= 0) {
                        i9 = i10;
                        int i11 = 0;
                        int i12 = 0;
                        while (i9 < length && (digit = Character.digit(str.charAt(i9), 10)) >= 0) {
                            if (i11 < 9) {
                                i12 = (i12 * 10) + digit;
                            } else if (i11 == 9 && digit >= 5) {
                                i12++;
                            }
                            i11++;
                            i9++;
                        }
                        while (true) {
                            int i13 = i11 + 1;
                            if (i11 >= 9) {
                                break;
                            }
                            i12 *= 10;
                            i11 = i13;
                        }
                        this.nanoSeconds = i12;
                    }
                    if (i2 == 24 && (i5 != 0 || i8 != 0 || this.nanoSeconds != 0)) {
                        return -1;
                    }
                    this.calendar.set(11, i2);
                    this.calendar.set(12, i5);
                    this.calendar.set(13, i8);
                    return i9;
                }
            }
        }
        return -1;
    }

    private static int parseDigits(String str, int i) {
        int length = str.length();
        int i2 = -1;
        while (i < length) {
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                break;
            } else if (i2 > 20000) {
                return 0;
            } else {
                i2 = i2 < 0 ? digit : (i2 * 10) + digit;
                i++;
            }
        }
        return i2 < 0 ? i : i | (i2 << 16);
    }

    public int getYear() {
        int i = this.calendar.get(1);
        return this.calendar.get(0) == 0 ? 1 - i : i;
    }

    public int getMonth() {
        return this.calendar.get(2) + 1;
    }

    public int getDay() {
        return this.calendar.get(5);
    }

    public int getHours() {
        return this.calendar.get(11);
    }

    public int getMinutes() {
        return this.calendar.get(12);
    }

    public int getSecondsOnly() {
        return this.calendar.get(13);
    }

    public int getWholeSeconds() {
        return this.calendar.get(13);
    }

    public int getNanoSecondsOnly() {
        return this.nanoSeconds;
    }

    public static int compare(DateTime dateTime, DateTime dateTime2) {
        long timeInMillis = dateTime.calendar.getTimeInMillis();
        long timeInMillis2 = dateTime2.calendar.getTimeInMillis();
        if (((dateTime.mask | dateTime2.mask) & 14) == 0) {
            if (timeInMillis < 0) {
                timeInMillis += 86400000;
            }
            if (timeInMillis2 < 0) {
                timeInMillis2 += 86400000;
            }
        }
        int i = dateTime.nanoSeconds;
        int i2 = dateTime2.nanoSeconds;
        int i3 = i % 1000000;
        int i4 = i2 % 1000000;
        int i5 = ((timeInMillis + ((long) (i / 1000000))) > (timeInMillis2 + ((long) (i2 / 1000000))) ? 1 : ((timeInMillis + ((long) (i / 1000000))) == (timeInMillis2 + ((long) (i2 / 1000000))) ? 0 : -1));
        if (i5 >= 0) {
            if (i5 > 0) {
                return 1;
            }
            if (i3 >= i4) {
                return i3 > i4 ? 1 : 0;
            }
        }
        return -1;
    }

    public int compare(Object obj) {
        if (obj instanceof DateTime) {
            return compare(this, (DateTime) obj);
        }
        return ((Numeric) obj).compareReversed(this);
    }

    public static Duration sub(DateTime dateTime, DateTime dateTime2) {
        long timeInMillis = dateTime.calendar.getTimeInMillis();
        long timeInMillis2 = dateTime2.calendar.getTimeInMillis();
        int i = dateTime.nanoSeconds;
        int i2 = dateTime2.nanoSeconds;
        int i3 = i % 1000000;
        long j = (timeInMillis + ((long) (i / 1000000))) - (timeInMillis2 + ((long) (i2 / 1000000)));
        long j2 = (long) (i2 % 1000000);
        int i4 = (int) ((((j % 1000) * 1000000) + j2) - j2);
        return Duration.make(0, (j / 1000) + ((long) (i4 / 1000000000)), i4 % 1000000000, Unit.second);
    }

    public DateTime withZoneUnspecified() {
        if (isZoneUnspecified()) {
            return this;
        }
        DateTime dateTime = new DateTime(this.mask, (GregorianCalendar) this.calendar.clone());
        dateTime.calendar.setTimeZone(TimeZone.getDefault());
        dateTime.mask &= -129;
        return dateTime;
    }

    public DateTime adjustTimezone(int i) {
        TimeZone timeZone;
        DateTime dateTime = new DateTime(this.mask, (GregorianCalendar) this.calendar.clone());
        if (i == 0) {
            timeZone = GMT;
        } else {
            StringBuffer stringBuffer = new StringBuffer("GMT");
            toStringZone(i, stringBuffer);
            timeZone = TimeZone.getTimeZone(stringBuffer.toString());
        }
        dateTime.calendar.setTimeZone(timeZone);
        int i2 = dateTime.mask;
        if ((i2 & 128) != 0) {
            dateTime.calendar.setTimeInMillis(this.calendar.getTimeInMillis());
            if ((this.mask & 112) == 0) {
                dateTime.calendar.set(11, 0);
                dateTime.calendar.set(12, 0);
                dateTime.calendar.set(13, 0);
                dateTime.nanoSeconds = 0;
            }
        } else {
            dateTime.mask = i2 | 128;
        }
        return dateTime;
    }

    public static DateTime add(DateTime dateTime, Duration duration, int i) {
        int i2;
        int i3;
        int i4;
        if (duration.unit == Unit.duration || (duration.unit == Unit.month && (dateTime.mask & 14) != 14)) {
            throw new IllegalArgumentException("invalid date/time +/- duration combinatuion");
        }
        DateTime dateTime2 = new DateTime(dateTime.mask, (GregorianCalendar) dateTime.calendar.clone());
        if (duration.months != 0) {
            int year = (dateTime2.getYear() * 12) + dateTime2.calendar.get(2) + (duration.months * i);
            int i5 = dateTime2.calendar.get(5);
            if (year >= 12) {
                i2 = year / 12;
                i4 = year % 12;
                dateTime2.calendar.set(0, 1);
                i3 = daysInMonth(i4, i2);
            } else {
                int i6 = 11 - year;
                dateTime2.calendar.set(0, 0);
                i2 = (i6 / 12) + 1;
                i4 = 11 - (i6 % 12);
                i3 = daysInMonth(i4, 1);
            }
            if (i5 > i3) {
                i5 = i3;
            }
            dateTime2.calendar.set(i2, i4, i5);
        }
        long j = ((long) dateTime.nanoSeconds) + (((long) i) * ((duration.seconds * 1000000000) + ((long) duration.nanos)));
        if (j != 0) {
            if ((dateTime.mask & 112) == 0) {
                long j2 = j % 86400000000000L;
                if (j2 < 0) {
                    j2 += 86400000000000L;
                }
                j -= j2;
            }
            dateTime2.calendar.setTimeInMillis(dateTime2.calendar.getTimeInMillis() + ((j / 1000000000) * 1000));
            dateTime2.nanoSeconds = (int) (j % 1000000000);
        }
        return dateTime2;
    }

    public static DateTime addMinutes(DateTime dateTime, int i) {
        return addSeconds(dateTime, i * 60);
    }

    public static DateTime addSeconds(DateTime dateTime, int i) {
        DateTime dateTime2 = new DateTime(dateTime.mask, (GregorianCalendar) dateTime.calendar.clone());
        long j = ((long) i) * 1000000000;
        if (j != 0) {
            long j2 = ((long) dateTime.nanoSeconds) + j;
            dateTime2.calendar.setTimeInMillis(dateTime.calendar.getTimeInMillis() + (j2 / 1000000));
            dateTime2.nanoSeconds = (int) (j2 % 1000000);
        }
        return dateTime2;
    }

    public Numeric add(Object obj, int i) {
        if (obj instanceof Duration) {
            return add(this, (Duration) obj, i);
        }
        if ((obj instanceof DateTime) && i == -1) {
            return sub(this, (DateTime) obj);
        }
        throw new IllegalArgumentException();
    }

    public Numeric addReversed(Numeric numeric, int i) {
        if ((numeric instanceof Duration) && i == 1) {
            return add(this, (Duration) numeric, i);
        }
        throw new IllegalArgumentException();
    }

    private static void append(int i, StringBuffer stringBuffer, int i2) {
        int length = stringBuffer.length();
        stringBuffer.append(i);
        int length2 = (i2 + length) - stringBuffer.length();
        while (true) {
            length2--;
            if (length2 >= 0) {
                stringBuffer.insert(length, '0');
            } else {
                return;
            }
        }
    }

    public void toStringDate(StringBuffer stringBuffer) {
        int components = components();
        if ((components & 2) != 0) {
            int i = this.calendar.get(1);
            if (this.calendar.get(0) == 0 && i - 1 != 0) {
                stringBuffer.append('-');
            }
            append(i, stringBuffer, 4);
        } else {
            stringBuffer.append('-');
        }
        if ((components & 12) != 0) {
            stringBuffer.append('-');
            if ((components & 4) != 0) {
                append(getMonth(), stringBuffer, 2);
            }
            if ((components & 8) != 0) {
                stringBuffer.append('-');
                append(getDay(), stringBuffer, 2);
            }
        }
    }

    public void toStringTime(StringBuffer stringBuffer) {
        append(getHours(), stringBuffer, 2);
        stringBuffer.append(':');
        append(getMinutes(), stringBuffer, 2);
        stringBuffer.append(':');
        append(getWholeSeconds(), stringBuffer, 2);
        Duration.appendNanoSeconds(this.nanoSeconds, stringBuffer);
    }

    public boolean isZoneUnspecified() {
        return (this.mask & 128) == 0;
    }

    public int getZoneMinutes() {
        return this.calendar.getTimeZone().getRawOffset() / 60000;
    }

    public static TimeZone minutesToTimeZone(int i) {
        if (i == 0) {
            return GMT;
        }
        StringBuffer stringBuffer = new StringBuffer("GMT");
        toStringZone(i, stringBuffer);
        return TimeZone.getTimeZone(stringBuffer.toString());
    }

    public void setTimeZone(TimeZone timeZone) {
        this.calendar.setTimeZone(timeZone);
    }

    public void toStringZone(StringBuffer stringBuffer) {
        if (!isZoneUnspecified()) {
            toStringZone(getZoneMinutes(), stringBuffer);
        }
    }

    public static void toStringZone(int i, StringBuffer stringBuffer) {
        if (i == 0) {
            stringBuffer.append('Z');
            return;
        }
        if (i < 0) {
            stringBuffer.append('-');
            i = -i;
        } else {
            stringBuffer.append('+');
        }
        append(i / 60, stringBuffer, 2);
        stringBuffer.append(':');
        append(i % 60, stringBuffer, 2);
    }

    public void toString(StringBuffer stringBuffer) {
        int components = components();
        boolean z = true;
        boolean z2 = (components & 14) != 0;
        if ((components & 112) == 0) {
            z = false;
        }
        if (z2) {
            toStringDate(stringBuffer);
            if (z) {
                stringBuffer.append('T');
            }
        }
        if (z) {
            toStringTime(stringBuffer);
        }
        toStringZone(stringBuffer);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        toString(stringBuffer);
        return stringBuffer.toString();
    }

    public boolean isExact() {
        return (this.mask & 112) == 0;
    }

    public boolean isZero() {
        throw new Error("DateTime.isZero not meaningful!");
    }

    public Unit unit() {
        return this.unit;
    }

    public Complex number() {
        throw new Error("number needs to be implemented!");
    }
}
