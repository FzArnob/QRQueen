package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.Closeable;
import java.io.IOException;

public class FileStreamOperation<T extends Closeable> extends SingleFileOperation {
    private static final String LOG_TAG = "FileStreamOperation";

    protected FileStreamOperation(Form form, Component component, String str, String str2, FileScope fileScope, FileAccessMode fileAccessMode, boolean z) {
        super(form, component, str, str2, fileScope, fileAccessMode, z);
    }

    protected FileStreamOperation(Form form, Component component, String str, ScopedFile scopedFile, FileAccessMode fileAccessMode, boolean z) {
        super(form, component, str, scopedFile, fileAccessMode, z);
    }

    /* access modifiers changed from: protected */
    public void processFile(ScopedFile scopedFile) {
        Closeable closeable = null;
        try {
            closeable = openFile();
            if (!process(closeable)) {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            onError(e);
        } catch (Throwable th) {
            IOUtils.closeQuietly(this.component.getClass().getSimpleName(), (Closeable) null);
            throw th;
        }
        IOUtils.closeQuietly(this.component.getClass().getSimpleName(), closeable);
    }

    public void onError(IOException iOException) {
        Log.e(LOG_TAG, "IO error in file operation", iOException);
    }

    /* access modifiers changed from: protected */
    public boolean process(T t) throws IOException {
        throw new UnsupportedOperationException("Subclasses must implement FileOperation#process.");
    }

    /* access modifiers changed from: protected */
    public T openFile() throws IOException {
        throw new UnsupportedOperationException("Subclasses must implement FileOperation#openFile.");
    }
}
