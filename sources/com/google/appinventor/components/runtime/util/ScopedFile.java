package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;
import java.io.File;
import java.net.URI;
import net.lingala.zip4j.util.InternalZipConstants;

public class ScopedFile {
    private final String fileName;
    private final FileScope scope;

    public ScopedFile(FileScope fileScope, String str) {
        if (str.startsWith("//")) {
            fileScope = FileScope.Asset;
            str = str.substring(2);
        } else if (!str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && fileScope == FileScope.Legacy) {
            fileScope = FileScope.Private;
        } else if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && fileScope != FileScope.Legacy) {
            str = str.substring(1);
        }
        this.scope = fileScope;
        this.fileName = str;
    }

    public FileScope getScope() {
        return this.scope;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int hashCode() {
        return (this.scope.hashCode() * 37) + this.fileName.hashCode();
    }

    public boolean equals(Object obj) {
        String str;
        if (obj instanceof ScopedFile) {
            ScopedFile scopedFile = (ScopedFile) obj;
            if (this.scope != scopedFile.scope) {
                return false;
            }
            String str2 = this.fileName;
            if (str2 == null && scopedFile.fileName == null) {
                return true;
            }
            if (!(str2 == null || (str = scopedFile.fileName) == null)) {
                return str2.equals(str);
            }
        }
        return false;
    }

    public File resolve(Form form) {
        return new File(URI.create(FileUtil.resolveFileName(form, this.fileName, this.scope)));
    }

    public String toString() {
        return "ScopedFile{scope=" + this.scope + ", fileName='" + this.fileName + '\'' + '}';
    }
}
