package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.Dates;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that provides the instant in time using the internal clock on the phone. It can fire a timer at regularly set intervals and perform time calculations, manipulations, and conversions.</p> <p>Methods to convert an instant to text are also available. Acceptable patterns are empty string, MM/DD/YYYY HH:mm:ss a, or MMM d, yyyyHH:mm. The empty string will provide the default format, which is \"MMM d, yyyy HH:mm:ss a\" for FormatDateTime \"MMM d, yyyy\" for FormatDate. To see all possible format, please see <a href=\"https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html\" _target=\"_blank\">here</a>. </p> ", iconName = "images/clock.png", nonVisible = true, version = 4)
public class Clock extends AndroidNonvisibleComponent implements AlarmHandler, Component, Deleteable, OnDestroyListener, OnResumeListener, OnStopListener {
    private boolean J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU;
    private boolean bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
    private TimerInternal hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public Clock(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = true;
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = false;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TimerInternal(this, true, 1000);
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnDestroy(this);
        if (this.form instanceof ReplForm) {
            this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = true;
        }
    }

    public Clock() {
        super((Form) null);
        this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = true;
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = false;
    }

    @SimpleEvent(description = "Timer has gone off.")
    public void Timer() {
        if (this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b || this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU) {
            try {
                EventDispatcher.dispatchEvent(this, "Timer", new Object[0]);
            } catch (Exception e) {
                Log.e("Clock", e.getMessage());
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Interval between timer events in ms")
    public int TimerInterval() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval();
    }

    @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
    @SimpleProperty
    public void TimerInterval(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval(i);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Fires timer if true")
    public boolean TimerEnabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void TimerEnabled(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Will fire even when application is not showing on the screen if true")
    public boolean TimerAlwaysFires() {
        return this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void TimerAlwaysFires(boolean z) {
        this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = z;
    }

    public void alarm() {
        Timer();
    }

    @SimpleFunction(description = "The phone's internal time")
    public static long SystemTime() {
        return Dates.Timer();
    }

    @SimpleFunction(description = "The current instant in time read from phone's clock")
    public static Calendar Now() {
        return Dates.Now();
    }

    @SimpleFunction(description = "An instant in time specified by MM/dd/YYYY hh:mm:ss or MM/dd/YYYY or hh:mm")
    public static Calendar MakeInstant(String str) {
        try {
            return Dates.DateValue(str);
        } catch (IllegalArgumentException unused) {
            throw new YailRuntimeError("Argument to MakeInstant should have form MM/dd/YYYY hh:mm:ss, or MM/dd/YYYY or hh:mm", "Sorry to be so picky.");
        }
    }

    @SimpleFunction(description = "Allows the user to set the clock to be a date value.\nValid values for the month field are 1-12 and 1-31 for the day field.\n")
    public Calendar MakeDate(int i, int i2, int i3) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2 - 1, i3);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.getTime();
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "MakeDate", ErrorMessages.ERROR_ILLEGAL_DATE, new Object[0]);
        }
        return Dates.DateInstant(i, i2, i3);
    }

    @SimpleFunction(description = "Allows the user to set the time of the clock - Valid format is hh:mm:ss\n")
    public Calendar MakeTime(int i, int i2, int i3) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.set(11, i);
            gregorianCalendar.set(12, i2);
            gregorianCalendar.set(13, i3);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "MakeTime", ErrorMessages.ERROR_ILLEGAL_DATE, new Object[0]);
        }
        return gregorianCalendar;
    }

    @SimpleFunction(description = "Allows the user to set the date and time to be displayed when the clock opens.\nValid values for the month field are 1-12 and 1-31 for the day field.\n")
    public Calendar MakeInstantFromParts(int i, int i2, int i3, int i4, int i5, int i6) {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2 - 1, i3);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.getTime();
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "MakeInstantFromParts", ErrorMessages.ERROR_ILLEGAL_DATE, new Object[0]);
        }
        Calendar DateInstant = Dates.DateInstant(i, i2, i3);
        try {
            DateInstant.set(11, i4);
            DateInstant.set(12, i5);
            DateInstant.set(13, i6);
        } catch (IllegalArgumentException unused2) {
            this.form.dispatchErrorOccurredEvent(this, "MakeInstantFromParts", ErrorMessages.ERROR_ILLEGAL_DATE, new Object[0]);
        }
        return DateInstant;
    }

    @SimpleFunction(description = "An instant in time specified by the milliseconds since 1970.")
    public static Calendar MakeInstantFromMillis(long j) {
        Calendar Now = Dates.Now();
        Now.setTimeInMillis(j);
        return Now;
    }

    @SimpleFunction(description = "The instant in time measured as milliseconds since 1970.")
    public static long GetMillis(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @SimpleFunction(description = "An instant in time some duration after the argument")
    public static Calendar AddDuration(Calendar calendar, long j) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAddInMillis(calendar2, j);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some seconds after the argument")
    public static Calendar AddSeconds(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 13, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some minutes after the argument")
    public static Calendar AddMinutes(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 12, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some hours after the argument")
    public static Calendar AddHours(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 11, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some days after the argument")
    public static Calendar AddDays(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 5, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some weeks after the argument")
    public static Calendar AddWeeks(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 3, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some months after the argument")
    public static Calendar AddMonths(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 2, i);
        return calendar2;
    }

    @SimpleFunction(description = "An instant in time some years after the argument")
    public static Calendar AddYears(Calendar calendar, int i) {
        Calendar calendar2 = (Calendar) calendar.clone();
        Dates.DateAdd(calendar2, 1, i);
        return calendar2;
    }

    @SimpleFunction(description = "Milliseconds elapsed between instants")
    public static long Duration(Calendar calendar, Calendar calendar2) {
        return calendar2.getTimeInMillis() - calendar.getTimeInMillis();
    }

    @SimpleFunction(description = "convert duration to seconds")
    public static long DurationToSeconds(long j) {
        return Dates.ConvertDuration(j, 13);
    }

    @SimpleFunction(description = "convert duration to minutes")
    public static long DurationToMinutes(long j) {
        return Dates.ConvertDuration(j, 12);
    }

    @SimpleFunction(description = "convert duration to hours")
    public static long DurationToHours(long j) {
        return Dates.ConvertDuration(j, 11);
    }

    @SimpleFunction(description = "convert duration to days")
    public static long DurationToDays(long j) {
        return Dates.ConvertDuration(j, 5);
    }

    @SimpleFunction(description = "convert duration to weeks")
    public static long DurationToWeeks(long j) {
        return Dates.ConvertDuration(j, 3);
    }

    @SimpleFunction(description = "The second of the minute")
    public static int Second(Calendar calendar) {
        return Dates.Second(calendar);
    }

    @SimpleFunction(description = "The minute of the hour")
    public static int Minute(Calendar calendar) {
        return Dates.Minute(calendar);
    }

    @SimpleFunction(description = "The hour of the day")
    public static int Hour(Calendar calendar) {
        return Dates.Hour(calendar);
    }

    @SimpleFunction(description = "The day of the month")
    public static int DayOfMonth(Calendar calendar) {
        return Dates.Day(calendar);
    }

    @SimpleFunction(description = "The day of the week represented as a number from 1 (Sunday) to 7 (Saturday)")
    public static int Weekday(Calendar calendar) {
        return Dates.Weekday(calendar);
    }

    @SimpleFunction(description = "The name of the day of the week")
    public static String WeekdayName(Calendar calendar) {
        return Dates.WeekdayName(calendar);
    }

    @SimpleFunction(description = "The month of the year represented as a number from 1 to 12)")
    public static int Month(Calendar calendar) {
        return Dates.Month(calendar) + 1;
    }

    @SimpleFunction(description = "The name of the month")
    public static String MonthName(Calendar calendar) {
        return Dates.MonthName(calendar);
    }

    @SimpleFunction(description = "The year")
    public static int Year(Calendar calendar) {
        return Dates.Year(calendar);
    }

    @SimpleFunction(description = "Text representing the date and time of an instant in the specified pattern")
    public static String FormatDateTime(Calendar calendar, String str) {
        try {
            return Dates.FormatDateTime(calendar, str);
        } catch (IllegalArgumentException unused) {
            throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDateTime. Acceptable values are empty string, MM/dd/YYYY hh:mm:ss a, MMM d, yyyy HH:mm For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
        }
    }

    @SimpleFunction(description = "Text representing the date of an instant in the specified pattern")
    public static String FormatDate(Calendar calendar, String str) {
        try {
            return Dates.FormatDate(calendar, str);
        } catch (IllegalArgumentException unused) {
            throw new YailRuntimeError("Illegal argument for pattern in Clock.FormatDate. Acceptable values are empty string, MM/dd/YYYY, or MMM d, yyyy. For all possible patterns, see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html", "Sorry to be so picky.");
        }
    }

    @SimpleFunction(description = "Text representing the time of an instant")
    public static String FormatTime(Calendar calendar) {
        return Dates.FormatTime(calendar);
    }

    public void onStop() {
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = false;
    }

    public void onResume() {
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = true;
    }

    public void onDestroy() {
        TimerInternal timerInternal = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (timerInternal != null) {
            timerInternal.Enabled(false);
        }
    }

    public void onDelete() {
        TimerInternal timerInternal = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (timerInternal != null) {
            timerInternal.Enabled(false);
        }
    }
}
