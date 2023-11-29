package com.google.appinventor.components.runtime.util;

import android.net.Uri;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class FileWriteOperation extends FileStreamOperation<OutputStream> {
    /* access modifiers changed from: protected */
    public boolean process(OutputStream outputStream) throws IOException {
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileWriteOperation(Form form, Component component, String str, String str2, FileScope fileScope, boolean z, boolean z2) {
        super(form, component, str, str2, fileScope, z ? FileAccessMode.APPEND : FileAccessMode.WRITE, z2);
        if (str2.startsWith("//")) {
            throw new IllegalArgumentException("Cannot perform a write operation on an asset");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileWriteOperation(Form form, Component component, String str, ScopedFile scopedFile, boolean z, boolean z2) {
        super(form, component, str, scopedFile, z ? FileAccessMode.APPEND : FileAccessMode.WRITE, z2);
        if (scopedFile.getScope() == FileScope.Asset) {
            throw new IllegalArgumentException("Cannot perform a write operation on an asset");
        }
    }

    /* access modifiers changed from: protected */
    public OutputStream openFile() throws IOException {
        if (this.fileName.startsWith("content:")) {
            return this.form.getContentResolver().openOutputStream(Uri.parse(this.fileName), this.accessMode == FileAccessMode.WRITE ? "wt" : "wa");
        }
        String resolveFileName = FileUtil.resolveFileName(this.form, this.fileName, this.scope);
        if (resolveFileName.startsWith("file://")) {
            resolveFileName = URI.create(resolveFileName).getPath();
        } else if (resolveFileName.startsWith("file:")) {
            resolveFileName = URI.create(resolveFileName).getPath();
        }
        File file = new File(resolveFileName);
        IOUtils.mkdirs(file);
        return new FileOutputStream(file, FileAccessMode.APPEND.equals(this.accessMode));
    }
}
