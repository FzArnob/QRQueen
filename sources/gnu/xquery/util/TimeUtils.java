package gnu.xquery.util;

import com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XTimeType;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.xml.TextUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TimeZone;

public class TimeUtils {
    static final ThreadLocal<DateTime> currentDateTimeLocal = new ThreadLocal<>();

    static DateTime coerceToDateTime(String str, Object obj) {
        if (XTimeType.dateTimeType.isInstance(obj)) {
            return (DateTime) obj;
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(obj), 126);
        }
        throw new WrongType(str, 1, obj, "xs:dateTime");
    }

    static DateTime coerceToDate(String str, Object obj) {
        if (XTimeType.dateType.isInstance(obj)) {
            return (DateTime) obj;
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(obj), 14);
        }
        throw new WrongType(str, 1, obj, "xs:date");
    }

    static DateTime coerceToTime(String str, Object obj) {
        if (XTimeType.timeType.isInstance(obj)) {
            return (DateTime) obj;
        }
        if ((obj instanceof KNode) || (obj instanceof UntypedAtomic)) {
            return XTimeType.parseDateTime(TextUtils.stringValue(obj), 112);
        }
        throw new WrongType(str, 1, obj, "xs:time");
    }

    static Duration coerceToDuration(String str, Object obj) {
        if (obj instanceof Duration) {
            return (Duration) obj;
        }
        throw new WrongType(str, 1, obj, "xs:duration");
    }

    static Object timeZoneFromXTime(DateTime dateTime) {
        if (dateTime.isZoneUnspecified()) {
            return Values.empty;
        }
        return Duration.makeMinutes(dateTime.getZoneMinutes());
    }

    static IntNum asInteger(int i) {
        return IntNum.make(i);
    }

    public static Object yearFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDateTime("year-from-dateTime", obj).getYear());
    }

    public static Object monthFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDateTime("month-from-dateTime", obj).getMonth());
    }

    public static Object dayFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDateTime("day-from-dateTime", obj).getDay());
    }

    public static Object hoursFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDateTime("hours-from-dateTime", obj).getHours());
    }

    public static Object minutesFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDateTime("minutes-from-dateTime", obj).getMinutes());
    }

    static Number getSeconds(DateTime dateTime) {
        int secondsOnly = dateTime.getSecondsOnly();
        long nanoSecondsOnly = (long) dateTime.getNanoSecondsOnly();
        if (nanoSecondsOnly == 0) {
            return IntNum.make(secondsOnly);
        }
        return new BigDecimal(BigInteger.valueOf(nanoSecondsOnly + (((long) secondsOnly) * 1000000000)), 9);
    }

    public static Object secondsFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : getSeconds(coerceToDateTime("seconds-from-dateTime", obj));
    }

    public static Object timezoneFromDateTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : timeZoneFromXTime(coerceToDateTime("timezone-from-datetime", obj));
    }

    public static Object yearFromDate(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDate("year-from-date", obj).getYear());
    }

    public static Object monthFromDate(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDate("month-from-date", obj).getMonth());
    }

    public static Object dayFromDate(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDate("day-from-date", obj).getDay());
    }

    public static Object timezoneFromDate(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : timeZoneFromXTime(coerceToDate("timezone-from-date", obj));
    }

    public static Object hoursFromTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToTime("hours-from-time", obj).getHours());
    }

    public static Object minutesFromTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToTime("minutes-from-time", obj).getMinutes());
    }

    public static Object secondsFromTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : getSeconds(coerceToTime("seconds-from-time", obj));
    }

    public static Object timezoneFromTime(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : timeZoneFromXTime(coerceToTime("timezone-from-time", obj));
    }

    public static Object yearsFromDuration(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDuration("years-from-duration", obj).getYears());
    }

    public static Object monthsFromDuration(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDuration("months-from-duration", obj).getMonths());
    }

    public static Object daysFromDuration(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDuration("days-from-duration", obj).getDays());
    }

    public static Object hoursFromDuration(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDuration("hours-from-duration", obj).getHours());
    }

    public static Object minutesFromDuration(Object obj) {
        return (obj == null || obj == Values.empty) ? obj : asInteger(coerceToDuration("minutes-from-duration", obj).getMinutes());
    }

    public static BigDecimal secondsBigDecimalFromDuration(long j, int i) {
        if (i == 0) {
            return BigDecimal.valueOf(j);
        }
        int i2 = 9;
        boolean z = ((long) ((int) j)) != j;
        long j2 = z ? (long) i : (1000000000 * j) + ((long) i);
        while (j2 % 10 == 0) {
            j2 /= 10;
            i2--;
        }
        BigDecimal bigDecimal = new BigDecimal(BigInteger.valueOf(j2), i2);
        return z ? BigDecimal.valueOf(j).add(bigDecimal) : bigDecimal;
    }

    public static Object secondsFromDuration(Object obj) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        Duration coerceToDuration = coerceToDuration("seconds-from-duration", obj);
        int secondsOnly = coerceToDuration.getSecondsOnly();
        int nanoSecondsOnly = coerceToDuration.getNanoSecondsOnly();
        if (nanoSecondsOnly == 0) {
            return asInteger(secondsOnly);
        }
        return secondsBigDecimalFromDuration((long) secondsOnly, nanoSecondsOnly);
    }

    public static Duration getImplicitTimezone() {
        return Duration.makeMinutes(TimeZone.getDefault().getRawOffset() / 60000);
    }

    public static Object adjustDateTimeToTimezone(Object obj) {
        return adjustDateTimeToTimezone(obj, getImplicitTimezone());
    }

    public static Object adjustDateTimeToTimezone(Object obj, Object obj2) {
        return (obj == Values.empty || obj == null) ? obj : adjustDateTimeToTimezoneRaw(coerceToDateTime("adjust-dateTime-to-timezone", obj), obj2);
    }

    public static Object adjustDateToTimezone(Object obj) {
        return adjustDateToTimezone(obj, getImplicitTimezone());
    }

    public static Object adjustDateToTimezone(Object obj, Object obj2) {
        return (obj == Values.empty || obj == null) ? obj : adjustDateTimeToTimezoneRaw(coerceToDate("adjust-date-to-timezone", obj), obj2);
    }

    public static Object adjustTimeToTimezone(Object obj) {
        return adjustTimeToTimezone(obj, getImplicitTimezone());
    }

    public static Object adjustTimeToTimezone(Object obj, Object obj2) {
        return (obj == Values.empty || obj == null) ? obj : adjustDateTimeToTimezoneRaw(coerceToTime("adjust-time-to-timezone", obj), obj2);
    }

    static Object adjustDateTimeToTimezoneRaw(DateTime dateTime, Object obj) {
        if (obj == Values.empty || obj == null) {
            return dateTime.withZoneUnspecified();
        }
        Duration duration = (Duration) obj;
        if (duration.getNanoSecondsOnly() == 0 && duration.getSecondsOnly() == 0) {
            int totalMinutes = (int) duration.getTotalMinutes();
            if (totalMinutes >= -840 && totalMinutes <= 840) {
                return dateTime.adjustTimezone(totalMinutes);
            }
            throw new IllegalArgumentException("timezone offset out of range");
        }
        throw new IllegalArgumentException("timezone offset with fractional minute");
    }

    public static DateTime now() {
        return XTimeType.dateTimeType.now();
    }

    public static Object dateTime(Object obj, Object obj2) {
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (obj2 == null || obj2 == Values.empty) {
            return obj2;
        }
        DateTime coerceToDate = coerceToDate(DateTimeTypedProperty.TYPE, obj);
        DateTime coerceToTime = coerceToTime(DateTimeTypedProperty.TYPE, obj2);
        StringBuffer stringBuffer = new StringBuffer();
        coerceToDate.toStringDate(stringBuffer);
        stringBuffer.append('T');
        coerceToTime.toStringTime(stringBuffer);
        boolean z = !coerceToDate.isZoneUnspecified();
        boolean z2 = !coerceToTime.isZoneUnspecified();
        if (z || z2) {
            int zoneMinutes = coerceToDate.getZoneMinutes();
            int zoneMinutes2 = coerceToTime.getZoneMinutes();
            if (!z || !z2 || zoneMinutes == zoneMinutes2) {
                if (!z) {
                    zoneMinutes = zoneMinutes2;
                }
                DateTime.toStringZone(zoneMinutes, stringBuffer);
            } else {
                throw new Error("dateTime: incompatible timezone in arguments");
            }
        }
        return (DateTime) XTimeType.dateTimeType.valueOf(stringBuffer.toString());
    }

    public static DateTime currentDateTime() {
        ThreadLocal<DateTime> threadLocal = currentDateTimeLocal;
        DateTime dateTime = threadLocal.get();
        if (dateTime != null) {
            return dateTime;
        }
        DateTime now = now();
        threadLocal.set(now);
        return now;
    }

    public static DateTime currentDate() {
        return currentDateTime().cast(14);
    }

    public static DateTime currentTime() {
        return currentDateTime().cast(112);
    }

    public static Object implicitTimezone() {
        return timeZoneFromXTime(currentDateTime());
    }
}
