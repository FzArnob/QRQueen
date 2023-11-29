package gnu.kawa.xml;

import com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty;
import gnu.bytecode.ClassType;
import gnu.math.DateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class XTimeType extends XDataType {
    public static final XTimeType dateTimeType = new XTimeType(DateTimeTypedProperty.TYPE, 20);
    public static final XTimeType dateType = new XTimeType("date", 21);
    private static TimeZone fixedTimeZone;
    public static final XTimeType gDayType = new XTimeType("gDay", 26);
    public static final XTimeType gMonthDayType = new XTimeType("gMonthDay", 25);
    public static final XTimeType gMonthType = new XTimeType("gMonth", 27);
    public static final XTimeType gYearMonthType = new XTimeType("gYearMonth", 23);
    public static final XTimeType gYearType = new XTimeType("gYear", 24);
    public static final XTimeType timeType = new XTimeType("time", 22);
    static ClassType typeDateTime = ClassType.make("gnu.math.DateTime");

    static int components(int i) {
        switch (i) {
            case 20:
                return 126;
            case 21:
                return 14;
            case 22:
                return 112;
            case 23:
                return 6;
            case 24:
                return 2;
            case 25:
                return 12;
            case 26:
                return 8;
            case 27:
                return 4;
            case 28:
                return 126;
            case 29:
                return 6;
            case 30:
                return 120;
            default:
                return 0;
        }
    }

    XTimeType(String str, int i) {
        super(str, typeDateTime, i);
    }

    public DateTime now() {
        return new DateTime(components(this.typeCode) | 128, (GregorianCalendar) Calendar.getInstance(fixedTimeZone()));
    }

    private static synchronized TimeZone fixedTimeZone() {
        TimeZone timeZone;
        synchronized (XTimeType.class) {
            if (fixedTimeZone == null) {
                fixedTimeZone = DateTime.minutesToTimeZone(TimeZone.getDefault().getRawOffset() / 60000);
            }
            timeZone = fixedTimeZone;
        }
        return timeZone;
    }

    public static DateTime parseDateTime(String str, int i) {
        DateTime parse = DateTime.parse(str, i);
        if (parse.isZoneUnspecified()) {
            parse.setTimeZone(fixedTimeZone());
        }
        return parse;
    }

    public Object valueOf(String str) {
        return parseDateTime(str, components(this.typeCode));
    }

    public boolean isInstance(Object obj) {
        if ((obj instanceof DateTime) && components(this.typeCode) == ((DateTime) obj).components()) {
            return true;
        }
        return false;
    }
}
