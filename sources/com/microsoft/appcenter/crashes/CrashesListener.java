package com.microsoft.appcenter.crashes;

import com.microsoft.appcenter.crashes.ingestion.models.ErrorAttachmentLog;
import com.microsoft.appcenter.crashes.model.ErrorReport;

public interface CrashesListener {
    Iterable<ErrorAttachmentLog> getErrorAttachments(ErrorReport errorReport);

    void onBeforeSending(ErrorReport errorReport);

    void onSendingFailed(ErrorReport errorReport, Exception exc);

    void onSendingSucceeded(ErrorReport errorReport);

    boolean shouldAwaitUserConfirmation();

    boolean shouldProcess(ErrorReport errorReport);
}
