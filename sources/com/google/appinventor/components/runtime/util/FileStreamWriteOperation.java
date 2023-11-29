package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public abstract class FileStreamWriteOperation extends FileWriteOperation {
    private static final String LOG_TAG = "FileStreamWriteOperation";

    /* access modifiers changed from: protected */
    public abstract boolean process(OutputStreamWriter outputStreamWriter) throws IOException;

    public FileStreamWriteOperation(Form form, Component component, String str, String str2, FileScope fileScope, boolean z, boolean z2) {
        super(form, component, str, str2, fileScope, z, z2);
    }

    /* access modifiers changed from: protected */
    public final boolean process(OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = null;
        try {
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(outputStream);
            try {
                boolean process = process(outputStreamWriter2);
                if (process) {
                    IOUtils.closeQuietly(LOG_TAG, outputStreamWriter2);
                }
                return process;
            } catch (Throwable th) {
                th = th;
                outputStreamWriter = outputStreamWriter2;
                IOUtils.closeQuietly(LOG_TAG, outputStreamWriter);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(LOG_TAG, outputStreamWriter);
            throw th;
        }
    }
}
