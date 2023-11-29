package com.microsoft.appcenter.analytics.ingestion.models.json;

import com.microsoft.appcenter.analytics.ingestion.models.EventLog;
import com.microsoft.appcenter.analytics.ingestion.models.one.CommonSchemaEventLog;
import com.microsoft.appcenter.ingestion.models.Log;
import com.microsoft.appcenter.ingestion.models.json.AbstractLogFactory;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaDataUtils;
import com.microsoft.appcenter.ingestion.models.one.CommonSchemaLog;
import com.microsoft.appcenter.ingestion.models.one.PartAUtils;
import java.util.Collection;
import java.util.LinkedList;

public class EventLogFactory extends AbstractLogFactory {
    public EventLog create() {
        return new EventLog();
    }

    public Collection<CommonSchemaLog> toCommonSchemaLogs(Log log) {
        LinkedList linkedList = new LinkedList();
        for (String addPartAFromLog : log.getTransmissionTargetTokens()) {
            CommonSchemaEventLog commonSchemaEventLog = new CommonSchemaEventLog();
            EventLog eventLog = (EventLog) log;
            PartAUtils.setName(commonSchemaEventLog, eventLog.getName());
            PartAUtils.addPartAFromLog(log, commonSchemaEventLog, addPartAFromLog);
            CommonSchemaDataUtils.addCommonSchemaData(eventLog.getTypedProperties(), commonSchemaEventLog);
            linkedList.add(commonSchemaEventLog);
            commonSchemaEventLog.setTag(log.getTag());
        }
        return linkedList;
    }
}
