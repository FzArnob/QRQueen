package gnu.text;

import gnu.bytecode.Access;
import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URIPath extends Path implements Comparable<URIPath> {
    final URI uri;

    URIPath(URI uri2) {
        this.uri = uri2;
    }

    public static URIPath coerceToURIPathOrNull(Object obj) {
        String str;
        if (obj instanceof URIPath) {
            return (URIPath) obj;
        }
        if (obj instanceof URL) {
            return URLPath.valueOf((URL) obj);
        }
        if (obj instanceof URI) {
            return valueOf((URI) obj);
        }
        if ((obj instanceof File) || (obj instanceof Path) || (obj instanceof FString)) {
            str = obj.toString();
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            str = (String) obj;
        }
        return valueOf(str);
    }

    public static URIPath makeURI(Object obj) {
        URIPath coerceToURIPathOrNull = coerceToURIPathOrNull(obj);
        if (coerceToURIPathOrNull != null) {
            return coerceToURIPathOrNull;
        }
        String str = null;
        throw new WrongType((String) null, -4, obj, "URI");
    }

    public static URIPath valueOf(URI uri2) {
        return new URIPath(uri2);
    }

    public static URIPath valueOf(String str) {
        try {
            return new URIStringPath(new URI(encodeForUri(str, Access.INNERCLASS_CONTEXT)), str);
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public boolean isAbsolute() {
        return this.uri.isAbsolute();
    }

    public boolean exists() {
        try {
            URLConnection openConnection = toURL().openConnection();
            if (openConnection instanceof HttpURLConnection) {
                if (((HttpURLConnection) openConnection).getResponseCode() == 200) {
                    return true;
                }
                return false;
            } else if (openConnection.getLastModified() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public long getLastModified() {
        return URLPath.getLastModified(toURL());
    }

    public long getContentLength() {
        return (long) URLPath.getContentLength(toURL());
    }

    public URI toUri() {
        return this.uri;
    }

    public String toURIString() {
        return this.uri.toString();
    }

    public Path resolve(String str) {
        if (Path.uriSchemeSpecified(str)) {
            return valueOf(str);
        }
        char c = File.separatorChar;
        if (c != '/') {
            if (str.length() >= 2 && ((str.charAt(1) == ':' && Character.isLetter(str.charAt(0))) || (str.charAt(0) == c && str.charAt(1) == c))) {
                return FilePath.valueOf(new File(str));
            }
            str = str.replace(c, '/');
        }
        try {
            return valueOf(this.uri.resolve(new URI((String) null, str, (String) null)));
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public int compareTo(URIPath uRIPath) {
        return this.uri.compareTo(uRIPath.uri);
    }

    public boolean equals(Object obj) {
        return (obj instanceof URIPath) && this.uri.equals(((URIPath) obj).uri);
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    public String toString() {
        return toURIString();
    }

    public URL toURL() {
        return Path.toURL(this.uri.toString());
    }

    public InputStream openInputStream() throws IOException {
        return URLPath.openInputStream(toURL());
    }

    public OutputStream openOutputStream() throws IOException {
        return URLPath.openOutputStream(toURL());
    }

    public String getScheme() {
        return this.uri.getScheme();
    }

    public String getHost() {
        return this.uri.getHost();
    }

    public String getAuthority() {
        return this.uri.getAuthority();
    }

    public String getUserInfo() {
        return this.uri.getUserInfo();
    }

    public int getPort() {
        return this.uri.getPort();
    }

    public String getPath() {
        return this.uri.getPath();
    }

    public String getQuery() {
        return this.uri.getQuery();
    }

    public String getFragment() {
        return this.uri.getFragment();
    }

    public Path getCanonical() {
        if (!isAbsolute()) {
            return getAbsolute().getCanonical();
        }
        URI normalize = this.uri.normalize();
        if (normalize == this.uri) {
            return this;
        }
        return valueOf(normalize);
    }

    public static String encodeForUri(String str, char c) {
        int i;
        int i2;
        int i3;
        String str2 = str;
        char c2 = c;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i4 = 0; i4 < length; i4 = i) {
            i = i4 + 1;
            int charAt = str2.charAt(i4);
            if (charAt >= 55296 && charAt < 56320 && i < length) {
                charAt = ((charAt - 55296) * 1024) + (str2.charAt(i) - 56320) + 65536;
                i++;
            }
            if (c2 != 'H' ? (i2 < 97 || i2 > 122) && ((i2 < 65 || i2 > 90) && !((i2 >= 48 && i2 <= 57) || i2 == 45 || i2 == 95 || i2 == 46 || i2 == 126 || (c2 == 'I' && (i2 == 59 || i2 == 47 || i2 == 63 || i2 == 58 || i2 == 42 || i2 == 39 || i2 == 40 || i2 == 41 || i2 == 64 || i2 == 38 || i2 == 61 || i2 == 43 || i2 == 36 || i2 == 44 || i2 == 91 || i2 == 93 || i2 == 35 || i2 == 33 || i2 == 37)))) : i2 < 32 || i2 > 126) {
                int length2 = stringBuffer.length();
                if (i2 >= 128) {
                }
                int i5 = 0;
                do {
                    if (i2 < (1 << (i5 == 0 ? 7 : 6 - i5))) {
                        if (i5 > 0) {
                            i2 |= (65408 >> i5) & 255;
                        }
                        i3 = i2;
                        i2 = 0;
                    } else {
                        i3 = (i2 & 63) | 128;
                        i2 >>= 6;
                    }
                    i5++;
                    for (int i6 = 0; i6 <= 1; i6++) {
                        int i7 = i3 & 15;
                        stringBuffer.insert(length2, (char) (i7 <= 9 ? i7 + 48 : (i7 - 10) + 65));
                        i3 >>= 4;
                    }
                    stringBuffer.insert(length2, '%');
                } while (i2 != 0);
            } else {
                stringBuffer.append((char) i2);
            }
        }
        return stringBuffer.toString();
    }
}
