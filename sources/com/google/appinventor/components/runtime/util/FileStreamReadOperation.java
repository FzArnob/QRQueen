package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class FileStreamReadOperation extends FileReadOperation {
    private static final String LOG_TAG = "FileStreamReadOperation";

    /* access modifiers changed from: protected */
    public abstract boolean process(String str);

    public FileStreamReadOperation(Form form, Component component, String str, String str2, FileScope fileScope, boolean z) {
        super(form, component, str, str2, fileScope, z);
    }

    /* access modifiers changed from: protected */
    public boolean process(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = null;
        try {
            InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream);
            try {
                boolean process = process(inputStreamReader2);
                if (process) {
                    IOUtils.closeQuietly(LOG_TAG, inputStreamReader2);
                }
                return process;
            } catch (Throwable th) {
                th = th;
                inputStreamReader = inputStreamReader2;
                IOUtils.closeQuietly(LOG_TAG, inputStreamReader);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(LOG_TAG, inputStreamReader);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public boolean process(InputStreamReader inputStreamReader) throws IOException {
        return process(IOUtils.readReader(inputStreamReader));
    }
}
