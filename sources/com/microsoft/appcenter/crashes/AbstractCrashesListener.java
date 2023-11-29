package com.microsoft.appcenter.crashes;

import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.model.ErrorReport;

public abstract class AbstractCrashesListener implements CrashesListener {
    public Iterable<ErrorAttachmentLog> getErrorAttachments(ErrorReport errorReport) {
        return null;
    }

    public void onBeforeSending(ErrorReport errorReport) {
    }

    public void onSendingFailed(ErrorReport errorReport, Exception exc) {
    }

    public void onSendingSucceeded(ErrorReport errorReport) {
    }

    public boolean shouldAwaitUserConfirmation() {
        return false;
    }

    public boolean shouldProcess(ErrorReport errorReport) {
        return true;
    }
}
