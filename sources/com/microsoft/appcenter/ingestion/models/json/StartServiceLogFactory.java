package com.microsoft.appcenter.ingestion.models.json;

import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.StartServiceLog;

public class StartServiceLogFactory extends AbstractLogFactory {
    public Log create() {
        return new StartServiceLog();
    }
}
