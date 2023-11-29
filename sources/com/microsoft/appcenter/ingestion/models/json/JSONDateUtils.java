package com.microsoft.appcenter.ingestion.models.json;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;

public final class JSONDateUtils {
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {
        /* access modifiers changed from: protected */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return simpleDateFormat;
        }
    };

    private static void checkNull(Object obj) throws JSONException {
        if (obj == null) {
            throw new JSONException("date cannot be null");
        }
    }

    public static String toString(Date date) throws JSONException {
        checkNull(date);
        return DATE_FORMAT.get().format(date);
    }

    public static Date toDate(String str) throws JSONException {
        checkNull(str);
        try {
            return DATE_FORMAT.get().parse(str);
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
