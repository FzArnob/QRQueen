package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.microsoft.appcenter.Constants;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.lingala.zip4j.util.InternalZipConstants;

@SimpleObject
public final class Dates {
    public static final int DATE_APRIL = 3;
    public static final int DATE_AUGUST = 7;
    public static final int DATE_DAY = 5;
    public static final int DATE_DECEMBER = 11;
    public static final int DATE_FEBRUARY = 1;
    public static final int DATE_FRIDAY = 6;
    public static final int DATE_HOUR = 11;
    public static final int DATE_JANUARY = 0;
    public static final int DATE_JULY = 6;
    public static final int DATE_JUNE = 5;
    public static final int DATE_MARCH = 2;
    public static final int DATE_MAY = 4;
    public static final int DATE_MILLISECOND = 14;
    public static final int DATE_MINUTE = 12;
    public static final int DATE_MONDAY = 2;
    public static final int DATE_MONTH = 2;
    public static final int DATE_NOVEMBER = 10;
    public static final int DATE_OCTOBER = 9;
    public static final int DATE_SATURDAY = 7;
    public static final int DATE_SECOND = 13;
    public static final int DATE_SEPTEMBER = 8;
    public static final int DATE_SUNDAY = 1;
    public static final int DATE_THURSDAY = 5;
    public static final int DATE_TUESDAY = 3;
    public static final int DATE_WEDNESDAY = 4;
    public static final int DATE_WEEK = 3;
    public static final int DATE_YEAR = 1;

    private Dates() {
    }

    @SimpleFunction
    public static void DateAdd(Calendar calendar, int i, int i2) {
        if (!(i == 1 || i == 2 || i == 3 || i == 5)) {
            switch (i) {
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    throw new IllegalArgumentException("illegal date/time interval kind in function DateAdd()");
            }
        }
        calendar.add(i, i2);
    }

    @SimpleFunction
    public static void DateAddInMillis(Calendar calendar, long j) {
        calendar.setTimeInMillis(calendar.getTimeInMillis() + j);
    }

    @SimpleFunction
    public static Calendar DateValue(String str) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str));
        return gregorianCalendar;
    }

    private static Date hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        String[] strArr = {"MM/dd/yyyy hh:mm:ss a", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy hh:mm a", "MM/dd/yyyy HH:mm", "MM/dd/yyyy", "hh:mm:ss a", "HH:mm:ss", "hh:mm a", "HH:mm"};
        int i = 0;
        while (i < 9) {
            try {
                return new SimpleDateFormat(strArr[i]).parse(str);
            } catch (ParseException unused) {
                i++;
            }
        }
        throw new IllegalArgumentException("illegal date/time format in function DateValue()");
    }

    @SimpleFunction
    public static int Day(Calendar calendar) {
        return calendar.get(5);
    }

    @SimpleFunction
    public static long ConvertDuration(long j, int i) {
        if (i == 3) {
            return ((((j / 1000) / 60) / 60) / 24) / 7;
        }
        if (i == 5) {
            return (((j / 1000) / 60) / 60) / 24;
        }
        switch (i) {
            case 11:
                return ((j / 1000) / 60) / 60;
            case 12:
                return (j / 1000) / 60;
            case 13:
                return j / 1000;
            default:
                throw new IllegalArgumentException("illegal date/time interval kind in function Duration()");
        }
    }

    @SimpleFunction
    public static String FormatDateTime(Calendar calendar, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        if (str.length() == 0) {
            simpleDateFormat.applyPattern("MMM d, yyyy hh:mm:ss a");
        } else {
            simpleDateFormat.applyPattern(str);
        }
        return simpleDateFormat.format(calendar.getTime());
    }

    @SimpleFunction
    public static String FormatDate(Calendar calendar, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        if (str.length() == 0) {
            simpleDateFormat.applyPattern("MMM d, yyyy");
        } else {
            simpleDateFormat.applyPattern(str);
        }
        return simpleDateFormat.format(calendar.getTime());
    }

    @SimpleFunction
    public static String FormatTime(Calendar calendar) {
        return DateFormat.getTimeInstance(2).format(calendar.getTime());
    }

    @SimpleFunction
    public static Calendar DateInstant(int i, int i2, int i3) {
        String valueOf = String.valueOf(i);
        String valueOf2 = String.valueOf(i2);
        String valueOf3 = String.valueOf(i3);
        if (i2 < 10) {
            valueOf2 = "0".concat(String.valueOf(valueOf2));
        }
        if (i3 < 10) {
            valueOf3 = "0".concat(String.valueOf(valueOf3));
        }
        return DateValue(valueOf2 + InternalZipConstants.ZIP_FILE_SEPARATOR + valueOf3 + InternalZipConstants.ZIP_FILE_SEPARATOR + valueOf);
    }

    @SimpleFunction
    public static Calendar TimeInstant(int i, int i2) {
        String valueOf = String.valueOf(i);
        String valueOf2 = String.valueOf(i2);
        if (i < 10) {
            valueOf = "0".concat(String.valueOf(valueOf));
        }
        if (i2 < 10) {
            valueOf2 = "0".concat(String.valueOf(valueOf2));
        }
        return DateValue(valueOf + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + valueOf2);
    }

    @SimpleFunction
    public static int Hour(Calendar calendar) {
        return calendar.get(11);
    }

    @SimpleFunction
    public static int Minute(Calendar calendar) {
        return calendar.get(12);
    }

    @SimpleFunction
    public static int Month(Calendar calendar) {
        return calendar.get(2);
    }

    @SimpleFunction
    public static String MonthName(Calendar calendar) {
        return String.format("%1$tB", new Object[]{calendar});
    }

    @SimpleFunction
    public static Calendar Now() {
        return new GregorianCalendar();
    }

    @SimpleFunction
    public static int Second(Calendar calendar) {
        return calendar.get(13);
    }

    @SimpleFunction
    public static long Timer() {
        return System.currentTimeMillis();
    }

    @SimpleFunction
    public static int Weekday(Calendar calendar) {
        return calendar.get(7);
    }

    @SimpleFunction
    public static String WeekdayName(Calendar calendar) {
        return String.format("%1$tA", new Object[]{calendar});
    }

    @SimpleFunction
    public static int Year(Calendar calendar) {
        return calendar.get(1);
    }
}
