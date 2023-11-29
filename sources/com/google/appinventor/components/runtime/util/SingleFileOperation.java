package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.File;
import java.util.Collections;
import java.util.List;

public abstract class SingleFileOperation extends FileOperation {
    private static final String LOG_TAG = "FileOperation";
    protected final FileAccessMode accessMode;
    protected final File file;
    protected final String fileName;
    protected final String resolvedPath;
    protected final FileScope scope;
    protected final ScopedFile scopedFile;

    /* access modifiers changed from: protected */
    public abstract void processFile(ScopedFile scopedFile2);

    static {
        Class<FileOperation> cls = FileOperation.class;
    }

    protected SingleFileOperation(Form form, Component component, String str, String str2, FileScope fileScope, FileAccessMode fileAccessMode, boolean z) {
        super(form, component, str, z);
        this.scope = fileScope;
        this.accessMode = fileAccessMode;
        this.fileName = str2;
        ScopedFile scopedFile2 = new ScopedFile(fileScope, str2);
        this.scopedFile = scopedFile2;
        if (str2.startsWith("content:")) {
            this.file = null;
            this.resolvedPath = str2;
        } else {
            File resolve = scopedFile2.resolve(form);
            this.file = resolve;
            this.resolvedPath = resolve.getAbsolutePath();
        }
        String str3 = LOG_TAG;
        Log.d(str3, "resolvedPath = " + this.resolvedPath);
    }

    protected SingleFileOperation(Form form, Component component, String str, ScopedFile scopedFile2, FileAccessMode fileAccessMode, boolean z) {
        super(form, component, str, z);
        this.scope = scopedFile2.getScope();
        this.accessMode = fileAccessMode;
        String fileName2 = scopedFile2.getFileName();
        this.fileName = fileName2;
        this.scopedFile = scopedFile2;
        if (fileName2.startsWith("content:")) {
            this.file = null;
            this.resolvedPath = fileName2;
        } else {
            File resolve = scopedFile2.resolve(form);
            this.file = resolve;
            this.resolvedPath = resolve.getAbsolutePath();
        }
        String str2 = LOG_TAG;
        Log.d(str2, "resolvedPath = " + this.resolvedPath);
    }

    protected SingleFileOperation(Form form, Component component, String str, String str2, FileScope fileScope, FileAccessMode fileAccessMode) {
        this(form, component, str, str2, fileScope, fileAccessMode, true);
    }

    public List<String> getPermissions() {
        String neededPermission = FileUtil.getNeededPermission(this.form, this.resolvedPath, this.accessMode);
        if (neededPermission == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(neededPermission);
    }

    public final File getFile() {
        return this.file;
    }

    public final ScopedFile getScopedFile() {
        return this.scopedFile;
    }

    public final boolean isAsset() {
        return this.fileName.startsWith("//") || this.scope == FileScope.Asset;
    }

    public final FileScope getScope() {
        return this.scope;
    }

    /* access modifiers changed from: protected */
    public void performOperation() {
        processFile(this.scopedFile);
    }

    /* access modifiers changed from: protected */
    public boolean needsExternalStorage() {
        return FileUtil.isExternalStorageUri(this.form, this.resolvedPath);
    }

    /* access modifiers changed from: protected */
    public final boolean needsPermission() {
        return FileUtil.needsPermission(this.form, this.resolvedPath);
    }
}
