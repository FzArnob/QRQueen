package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Path {
    public static Path defaultPath;
    private static ThreadLocal<Path> pathLocation = new ThreadLocal<>();
    public static final FilePath userDirPath;

    public boolean delete() {
        return false;
    }

    public String getAuthority() {
        return null;
    }

    public long getContentLength() {
        return -1;
    }

    public String getFragment() {
        return null;
    }

    public String getHost() {
        return null;
    }

    public abstract long getLastModified();

    public abstract String getPath();

    public int getPort() {
        return -1;
    }

    public String getQuery() {
        return null;
    }

    public abstract String getScheme();

    public String getUserInfo() {
        return null;
    }

    public abstract boolean isAbsolute();

    public abstract InputStream openInputStream() throws IOException;

    public abstract OutputStream openOutputStream() throws IOException;

    public abstract Path resolve(String str);

    public abstract URL toURL();

    public abstract URI toUri();

    static {
        FilePath valueOf = FilePath.valueOf(new File("."));
        userDirPath = valueOf;
        defaultPath = valueOf;
    }

    protected Path() {
    }

    public static Path currentPath() {
        Path path = pathLocation.get();
        if (path != null) {
            return path;
        }
        return defaultPath;
    }

    public static void setCurrentPath(Path path) {
        pathLocation.set(path);
    }

    public static Path coerceToPathOrNull(Object obj) {
        String str;
        if (obj instanceof Path) {
            return (Path) obj;
        }
        if (obj instanceof URL) {
            return URLPath.valueOf((URL) obj);
        }
        if (obj instanceof URI) {
            return URIPath.valueOf((URI) obj);
        }
        if (obj instanceof File) {
            return FilePath.valueOf((File) obj);
        }
        if (obj instanceof FString) {
            str = obj.toString();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            str = (String) obj;
        }
        if (uriSchemeSpecified(str)) {
            return URIPath.valueOf(str);
        }
        return FilePath.valueOf(str);
    }

    public static Path valueOf(Object obj) {
        Path coerceToPathOrNull = coerceToPathOrNull(obj);
        if (coerceToPathOrNull != null) {
            return coerceToPathOrNull;
        }
        String str = null;
        throw new WrongType((String) null, -4, obj, "path");
    }

    public static URL toURL(String str) {
        try {
            if (!uriSchemeSpecified(str)) {
                Path resolve = currentPath().resolve(str);
                if (resolve.isAbsolute()) {
                    return resolve.toURL();
                }
                str = resolve.toString();
            }
            return new URL(str);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public static int uriSchemeLength(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                return i;
            }
            if (i != 0) {
                if (!(Character.isLetterOrDigit(charAt) || charAt == '+' || charAt == '-' || charAt == '.')) {
                }
                i++;
            } else if (Character.isLetter(charAt)) {
                i++;
            }
            return -1;
        }
        return -1;
    }

    public static boolean uriSchemeSpecified(String str) {
        int uriSchemeLength = uriSchemeLength(str);
        if (uriSchemeLength == 1 && File.separatorChar == '\\') {
            char charAt = str.charAt(0);
            if (charAt >= 'a' && charAt <= 'z') {
                return false;
            }
            if (charAt < 'A' || charAt > 'Z') {
                return true;
            }
            return false;
        } else if (uriSchemeLength > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDirectory() {
        String obj = toString();
        int length = obj.length();
        if (length <= 0) {
            return false;
        }
        char charAt = obj.charAt(length - 1);
        if (charAt == '/' || charAt == File.separatorChar) {
            return true;
        }
        return false;
    }

    public boolean exists() {
        return getLastModified() != 0;
    }

    public Path getDirectory() {
        if (isDirectory()) {
            return this;
        }
        return resolve("");
    }

    public Path getParent() {
        return resolve(isDirectory() ? ".." : "");
    }

    public String getLast() {
        int i;
        int i2;
        String path = getPath();
        if (path == null) {
            return null;
        }
        int length = path.length();
        int i3 = length;
        do {
            i = i3;
            while (true) {
                i3--;
                if (i3 <= 0) {
                    return "";
                }
                char charAt = path.charAt(i3);
                if (charAt == '/' || ((this instanceof FilePath) && charAt == File.separatorChar)) {
                    i2 = i3 + 1;
                }
            }
            i2 = i3 + 1;
        } while (i2 == length);
        return path.substring(i2, i);
    }

    public String getExtension() {
        boolean z;
        String path = getPath();
        if (path == null) {
            return null;
        }
        int length = path.length();
        do {
            length--;
            if (length <= 0) {
                return null;
            }
            char charAt = path.charAt(length);
            z = false;
            if (charAt == '.') {
                charAt = path.charAt(length - 1);
                z = true;
            }
            if (charAt == '/' || ((this instanceof FilePath) && charAt == File.separatorChar)) {
                return null;
            }
        } while (!z);
        return path.substring(length + 1);
    }

    public final URI toURI() {
        return toUri();
    }

    public String toURIString() {
        return toUri().toString();
    }

    public Path resolve(Path path) {
        if (path.isAbsolute()) {
            return path;
        }
        return resolve(path.toString());
    }

    public static InputStream openInputStream(Object obj) throws IOException {
        return valueOf(obj).openInputStream();
    }

    public Reader openReader(boolean z) throws IOException {
        throw new UnsupportedOperationException();
    }

    public Writer openWriter() throws IOException {
        return new OutputStreamWriter(openOutputStream());
    }

    public CharSequence getCharContent(boolean z) throws IOException {
        throw new UnsupportedOperationException();
    }

    public static String relativize(String str, String str2) throws URISyntaxException, IOException {
        char charAt;
        String uri = new URI(str2).normalize().toString();
        String uri2 = URLPath.valueOf(str).toURI().normalize().toString();
        int length = uri.length();
        int length2 = uri2.length();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length && i < length2 && (charAt = uri.charAt(i)) == uri2.charAt(i)) {
            if (charAt == '/') {
                i2 = i;
            }
            if (charAt == ':') {
                i3 = i;
            }
            i++;
        }
        if (i3 <= 0) {
            return str;
        }
        int i4 = i3 + 2;
        if (i2 <= i4 && length > i4 && uri.charAt(i4) == '/') {
            return str;
        }
        int i5 = i2 + 1;
        String substring = uri.substring(i5);
        String substring2 = uri2.substring(i5);
        StringBuilder sb = new StringBuilder();
        int length3 = substring.length();
        while (true) {
            length3--;
            if (length3 < 0) {
                sb.append(substring2);
                return sb.toString();
            } else if (substring.charAt(length3) == '/') {
                sb.append("../");
            }
        }
    }

    public String getName() {
        return toString();
    }

    public Path getAbsolute() {
        if (this == userDirPath) {
            return resolve("");
        }
        return currentPath().resolve(this);
    }

    public Path getCanonical() {
        return getAbsolute();
    }
}
