package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FilePath extends Path implements Comparable<FilePath> {
    final File file;
    final String path;

    private FilePath(File file2) {
        this.file = file2;
        this.path = file2.toString();
    }

    private FilePath(File file2, String str) {
        this.file = file2;
        this.path = str;
    }

    public static FilePath valueOf(String str) {
        return new FilePath(new File(str), str);
    }

    public static FilePath valueOf(File file2) {
        return new FilePath(file2);
    }

    public static FilePath coerceToFilePathOrNull(Object obj) {
        String str;
        if (obj instanceof FilePath) {
            return (FilePath) obj;
        }
        if (obj instanceof URIPath) {
            return valueOf(new File(((URIPath) obj).uri));
        }
        if (obj instanceof URI) {
            return valueOf(new File((URI) obj));
        }
        if (obj instanceof File) {
            return valueOf((File) obj);
        }
        if (obj instanceof FString) {
            str = obj.toString();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            str = (String) obj;
        }
        return valueOf(str);
    }

    public static FilePath makeFilePath(Object obj) {
        FilePath coerceToFilePathOrNull = coerceToFilePathOrNull(obj);
        if (coerceToFilePathOrNull != null) {
            return coerceToFilePathOrNull;
        }
        String str = null;
        throw new WrongType((String) null, -4, obj, "filepath");
    }

    public boolean isAbsolute() {
        return this == Path.userDirPath || this.file.isAbsolute();
    }

    public boolean isDirectory() {
        int length;
        if (this.file.isDirectory()) {
            return true;
        }
        if (this.file.exists() || (length = this.path.length()) <= 0) {
            return false;
        }
        char charAt = this.path.charAt(length - 1);
        if (charAt == '/' || charAt == File.separatorChar) {
            return true;
        }
        return false;
    }

    public boolean delete() {
        return toFile().delete();
    }

    public long getLastModified() {
        return this.file.lastModified();
    }

    public boolean exists() {
        return this.file.exists();
    }

    public long getContentLength() {
        long length = this.file.length();
        if (length != 0 || this.file.exists()) {
            return length;
        }
        return -1;
    }

    public String getPath() {
        return this.file.getPath();
    }

    public String getLast() {
        return this.file.getName();
    }

    public FilePath getParent() {
        File parentFile = this.file.getParentFile();
        if (parentFile == null) {
            return null;
        }
        return valueOf(parentFile);
    }

    public int compareTo(FilePath filePath) {
        return this.file.compareTo(filePath.file);
    }

    public boolean equals(Object obj) {
        return (obj instanceof FilePath) && this.file.equals(((FilePath) obj).file);
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    public String toString() {
        return this.path;
    }

    public File toFile() {
        return this.file;
    }

    public URL toURL() {
        if (this == Path.userDirPath) {
            return resolve("").toURL();
        }
        if (!isAbsolute()) {
            return getAbsolute().toURL();
        }
        try {
            return this.file.toURI().toURL();
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    private static URI toUri(File file2) {
        try {
            if (file2.isAbsolute()) {
                return file2.toURI();
            }
            String file3 = file2.toString();
            char c = File.separatorChar;
            if (c != '/') {
                file3 = file3.replace(c, '/');
            }
            return new URI((String) null, (String) null, file3, (String) null);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public URI toUri() {
        if (this == Path.userDirPath) {
            return resolve("").toURI();
        }
        return toUri(this.file);
    }

    public InputStream openInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public OutputStream openOutputStream() throws IOException {
        return new FileOutputStream(this.file);
    }

    public String getScheme() {
        if (isAbsolute()) {
            return "file";
        }
        return null;
    }

    public Path resolve(String str) {
        File file2;
        if (Path.uriSchemeSpecified(str)) {
            return URLPath.valueOf(str);
        }
        File file3 = new File(str);
        if (file3.isAbsolute()) {
            return valueOf(file3);
        }
        char c = File.separatorChar;
        if (c != '/') {
            str = str.replace('/', c);
        }
        if (this == Path.userDirPath) {
            file2 = new File(System.getProperty("user.dir"), str);
        } else {
            file2 = new File(isDirectory() ? this.file : this.file.getParentFile(), str);
        }
        return valueOf(file2);
    }

    public Path getCanonical() {
        try {
            File canonicalFile = this.file.getCanonicalFile();
            if (!canonicalFile.equals(this.file)) {
                return valueOf(canonicalFile);
            }
        } catch (Throwable unused) {
        }
        return this;
    }
}
