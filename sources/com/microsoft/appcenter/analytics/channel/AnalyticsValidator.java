package com.microsoft.appcenter.analytics.channel;

import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.analytics.ingestion.models.EventLog;
import com.microsoft.appcenter.analytics.ingestion.models.LogWithNameAndProperties;
import com.microsoft.appcenter.analytics.ingestion.models.PageLog;
import com.microsoft.appcenter.channel.AbstractChannelListener;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.TypedProperty;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class AnalyticsValidator extends AbstractChannelListener {
    static final int MAX_NAME_LENGTH = 256;
    static final int MAX_PROPERTY_COUNT = 20;
    static final int MAX_PROPERTY_ITEM_LENGTH = 125;

    private boolean validateLog(LogWithNameAndProperties logWithNameAndProperties) {
        String validateName = validateName(logWithNameAndProperties.getName(), logWithNameAndProperties.getType());
        if (validateName == null) {
            return false;
        }
        Map<String, String> validateProperties = validateProperties(logWithNameAndProperties.getProperties(), validateName, logWithNameAndProperties.getType());
        logWithNameAndProperties.setName(validateName);
        logWithNameAndProperties.setProperties(validateProperties);
        return true;
    }

    private boolean validateLog(EventLog eventLog) {
        String validateName = validateName(eventLog.getName(), eventLog.getType());
        if (validateName == null) {
            return false;
        }
        validateProperties(eventLog.getTypedProperties());
        eventLog.setName(validateName);
        return true;
    }

    private static String validateName(String str, String str2) {
        if (str == null || str.isEmpty()) {
            AppCenterLog.error(Analytics.LOG_TAG, str2 + " name cannot be null or empty.");
            return null;
        } else if (str.length() <= 256) {
            return str;
        } else {
            AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : name length cannot be longer than %s characters. Name will be truncated.", new Object[]{str2, str, 256}));
            return str.substring(0, 256);
        }
    }

    private static Map<String, String> validateProperties(Map<String, String> map, String str, String str2) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            String str3 = (String) next.getKey();
            String str4 = (String) next.getValue();
            if (hashMap.size() >= 20) {
                AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : properties cannot contain more than %s items. Skipping other properties.", new Object[]{str2, str, 20}));
                break;
            } else if (str3 == null || str3.isEmpty()) {
                AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : a property key cannot be null or empty. Property will be skipped.", new Object[]{str2, str}));
            } else if (str4 == null) {
                AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : property '%s' : property value cannot be null. Property '%s' will be skipped.", new Object[]{str2, str, str3, str3}));
            } else {
                if (str3.length() > 125) {
                    AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : property '%s' : property key length cannot be longer than %s characters. Property key will be truncated.", new Object[]{str2, str, str3, 125}));
                    str3 = str3.substring(0, 125);
                }
                if (str4.length() > 125) {
                    AppCenterLog.warn(Analytics.LOG_TAG, String.format("%s '%s' : property '%s' : property value cannot be longer than %s characters. Property value will be truncated.", new Object[]{str2, str, str3, 125}));
                    str4 = str4.substring(0, 125);
                }
                hashMap.put(str3, str4);
            }
        }
        return hashMap;
    }

    private static void validateProperties(List<TypedProperty> list) {
        boolean z;
        if (list != null) {
            ListIterator<TypedProperty> listIterator = list.listIterator();
            int i = 0;
            boolean z2 = false;
            while (listIterator.hasNext()) {
                TypedProperty next = listIterator.next();
                String name = next.getName();
                if (i >= 20) {
                    if (!z2) {
                        AppCenterLog.warn(Analytics.LOG_TAG, String.format("Typed properties cannot contain more than %s items. Skipping other properties.", new Object[]{20}));
                        z2 = true;
                    }
                    listIterator.remove();
                } else if (name == null || name.isEmpty()) {
                    AppCenterLog.warn(Analytics.LOG_TAG, "A typed property key cannot be null or empty. Property will be skipped.");
                    listIterator.remove();
                } else {
                    if (name.length() > 125) {
                        AppCenterLog.warn(Analytics.LOG_TAG, String.format("Typed property '%s' : property key length cannot be longer than %s characters. Property key will be truncated.", new Object[]{name, 125}));
                        name = name.substring(0, 125);
                        next = copyProperty(next, name);
                        listIterator.set(next);
                        z = false;
                    } else {
                        z = true;
                    }
                    if (next instanceof StringTypedProperty) {
                        StringTypedProperty stringTypedProperty = (StringTypedProperty) next;
                        String value = stringTypedProperty.getValue();
                        if (value == null) {
                            AppCenterLog.warn(Analytics.LOG_TAG, String.format("Typed property '%s' : property value cannot be null. Property '%s' will be skipped.", new Object[]{name, name}));
                            listIterator.remove();
                        } else if (value.length() > 125) {
                            AppCenterLog.warn(Analytics.LOG_TAG, String.format("A String property '%s' : property value cannot be longer than %s characters. Property value will be truncated.", new Object[]{name, 125}));
                            String substring = value.substring(0, 125);
                            if (z) {
                                StringTypedProperty stringTypedProperty2 = new StringTypedProperty();
                                stringTypedProperty2.setName(name);
                                stringTypedProperty2.setValue(substring);
                                listIterator.set(stringTypedProperty2);
                            } else {
                                stringTypedProperty.setValue(substring);
                            }
                        }
                    }
                    i++;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.microsoft.appcenter.ingestion.models.properties.TypedProperty copyProperty(com.microsoft.appcenter.ingestion.models.properties.TypedProperty r3, java.lang.String r4) {
        /*
            java.lang.String r0 = r3.getType()
            java.lang.String r1 = "boolean"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x001b
            com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty r0 = new com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty
            r0.<init>()
            com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty r3 = (com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty) r3
            boolean r3 = r3.getValue()
            r0.setValue(r3)
            goto L_0x006e
        L_0x001b:
            java.lang.String r1 = "dateTime"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0032
            com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty r0 = new com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty
            r0.<init>()
            com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty r3 = (com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty) r3
            java.util.Date r3 = r3.getValue()
            r0.setValue(r3)
            goto L_0x006e
        L_0x0032:
            java.lang.String r1 = "double"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0049
            com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty r0 = new com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty
            r0.<init>()
            com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty r3 = (com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty) r3
            double r1 = r3.getValue()
            r0.setValue(r1)
            goto L_0x006e
        L_0x0049:
            java.lang.String r1 = "long"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0060
            com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty r0 = new com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty
            r0.<init>()
            com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty r3 = (com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty) r3
            long r1 = r3.getValue()
            r0.setValue(r1)
            goto L_0x006e
        L_0x0060:
            com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty r0 = new com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty
            r0.<init>()
            com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty r3 = (com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty) r3
            java.lang.String r3 = r3.getValue()
            r0.setValue(r3)
        L_0x006e:
            r0.setName(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.appcenter.analytics.channel.AnalyticsValidator.copyProperty(com.microsoft.appcenter.ingestion.models.properties.TypedProperty, java.lang.String):com.microsoft.appcenter.ingestion.models.properties.TypedProperty");
    }

    public boolean shouldFilter(Log log) {
        if (log instanceof PageLog) {
            return !validateLog((LogWithNameAndProperties) log);
        }
        if (log instanceof EventLog) {
            return !validateLog((EventLog) log);
        }
        return false;
    }
}
