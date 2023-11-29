package com.microsoft.appcenter.analytics;

import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.TypedProperty;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventProperties {
    private static final String VALUE_NULL_ERROR_MESSAGE = "Property value cannot be null";
    private final Map<String, TypedProperty> mProperties = new ConcurrentHashMap();

    /* access modifiers changed from: package-private */
    public Map<String, TypedProperty> getProperties() {
        return this.mProperties;
    }

    public EventProperties set(String str, boolean z) {
        if (isValidKey(str)) {
            BooleanTypedProperty booleanTypedProperty = new BooleanTypedProperty();
            booleanTypedProperty.setName(str);
            booleanTypedProperty.setValue(z);
            this.mProperties.put(str, booleanTypedProperty);
        }
        return this;
    }

    public EventProperties set(String str, Date date) {
        if (isValidKey(str) && isValidValue(date)) {
            DateTimeTypedProperty dateTimeTypedProperty = new DateTimeTypedProperty();
            dateTimeTypedProperty.setName(str);
            dateTimeTypedProperty.setValue(date);
            this.mProperties.put(str, dateTimeTypedProperty);
        }
        return this;
    }

    public EventProperties set(String str, double d) {
        if (isValidKey(str)) {
            if (Double.isInfinite(d) || Double.isNaN(d)) {
                AppCenterLog.error(Analytics.LOG_TAG, "Double property value cannot be NaN or infinite.");
            } else {
                DoubleTypedProperty doubleTypedProperty = new DoubleTypedProperty();
                doubleTypedProperty.setName(str);
                doubleTypedProperty.setValue(d);
                this.mProperties.put(str, doubleTypedProperty);
            }
        }
        return this;
    }

    public EventProperties set(String str, long j) {
        if (isValidKey(str)) {
            LongTypedProperty longTypedProperty = new LongTypedProperty();
            longTypedProperty.setName(str);
            longTypedProperty.setValue(j);
            this.mProperties.put(str, longTypedProperty);
        }
        return this;
    }

    public EventProperties set(String str, String str2) {
        if (isValidKey(str) && isValidValue(str2)) {
            StringTypedProperty stringTypedProperty = new StringTypedProperty();
            stringTypedProperty.setName(str);
            stringTypedProperty.setValue(str2);
            this.mProperties.put(str, stringTypedProperty);
        }
        return this;
    }

    private boolean isValidKey(String str) {
        if (str == null) {
            AppCenterLog.error(Analytics.LOG_TAG, "Property key must not be null");
            return false;
        } else if (!this.mProperties.containsKey(str)) {
            return true;
        } else {
            AppCenterLog.warn(Analytics.LOG_TAG, "Property \"" + str + "\" is already set and will be overridden.");
            return true;
        }
    }

    private boolean isValidValue(Object obj) {
        if (obj != null) {
            return true;
        }
        AppCenterLog.error(Analytics.LOG_TAG, VALUE_NULL_ERROR_MESSAGE);
        return false;
    }
}
