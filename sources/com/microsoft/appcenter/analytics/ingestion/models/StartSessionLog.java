package com.microsoft.appcenter.analytics.ingestion.models;

import com.microsoft.appcenter.ingestion.models.AbstractLog;

public class StartSessionLog extends AbstractLog {
    public static final String TYPE = "startSession";

    public String getType() {
        return TYPE;
    }
}
