package com.microsoft.appcenter.ingestion.models;

import java.util.List;

public class LogContainer {
    private List<Log> logs;

    public List<Log> getLogs() {
        return this.logs;
    }

    public void setLogs(List<Log> list) {
        this.logs = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        List<Log> list = this.logs;
        List<Log> list2 = ((LogContainer) obj).logs;
        if (list != null) {
            return list.equals(list2);
        }
        if (list2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        List<Log> list = this.logs;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }
}
