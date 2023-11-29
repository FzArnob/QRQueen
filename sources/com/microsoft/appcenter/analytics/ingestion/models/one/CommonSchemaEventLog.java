package com.microsoft.appcenter.analytics.ingestion.models.one;

import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;

public class CommonSchemaEventLog extends CommonSchemaLog {
    public static final String TYPE = "commonSchemaEvent";

    public String getType() {
        return TYPE;
    }
}
