package gnu.text;

import gnu.mapping.WrappedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URLPath extends URIPath {
    final URL url;

    public boolean isAbsolute() {
        return true;
    }

    URLPath(URL url2) {
        super(toUri(url2));
        this.url = url2;
    }

    public static URLPath valueOf(URL url2) {
        return new URLPath(url2);
    }

    public long getLastModified() {
        return getLastModified(this.url);
    }

    public static long getLastModified(URL url2) {
        try {
            return url2.openConnection().getLastModified();
        } catch (Throwable unused) {
            return 0;
        }
    }

    public long getContentLength() {
        return (long) getContentLength(this.url);
    }

    public static int getContentLength(URL url2) {
        try {
            return url2.openConnection().getContentLength();
        } catch (Throwable unused) {
            return -1;
        }
    }

    public URL toURL() {
        return this.url;
    }

    public static URI toUri(URL url2) {
        try {
            return url2.toURI();
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public URI toUri() {
        return toUri(this.url);
    }

    public String toURIString() {
        return this.url.toString();
    }

    public Path resolve(String str) {
        try {
            return valueOf(new URL(this.url, str));
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    public static InputStream openInputStream(URL url2) throws IOException {
        return url2.openConnection().getInputStream();
    }

    public InputStream openInputStream() throws IOException {
        return openInputStream(this.url);
    }

    public static OutputStream openOutputStream(URL url2) throws IOException {
        String url3 = url2.toString();
        if (url3.startsWith("file:")) {
            try {
                return new FileOutputStream(new File(new URI(url3)));
            } catch (Throwable unused) {
            }
        }
        URLConnection openConnection = url2.openConnection();
        openConnection.setDoInput(false);
        openConnection.setDoOutput(true);
        return openConnection.getOutputStream();
    }

    public OutputStream openOutputStream() throws IOException {
        return openOutputStream(this.url);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r4 = r4.getClassLoader().getResource(r4.getName().replace('.', '/') + ".class");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
        throw gnu.mapping.WrappedException.wrapIfNeeded(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.text.URLPath classResourcePath(java.lang.Class r4) {
        /*
            java.net.URL r4 = gnu.text.ResourceStreamHandler.makeURL(r4)     // Catch:{ SecurityException -> 0x0007 }
            goto L_0x002c
        L_0x0005:
            r4 = move-exception
            goto L_0x0031
        L_0x0007:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0005 }
            r0.<init>()     // Catch:{ all -> 0x0005 }
            java.lang.String r1 = r4.getName()     // Catch:{ all -> 0x0005 }
            r2 = 46
            r3 = 47
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x0005 }
            r0.append(r1)     // Catch:{ all -> 0x0005 }
            java.lang.String r1 = ".class"
            r0.append(r1)     // Catch:{ all -> 0x0005 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0005 }
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ all -> 0x0005 }
            java.net.URL r4 = r4.getResource(r0)     // Catch:{ all -> 0x0005 }
        L_0x002c:
            gnu.text.URLPath r4 = valueOf(r4)
            return r4
        L_0x0031:
            java.lang.RuntimeException r4 = gnu.mapping.WrappedException.wrapIfNeeded(r4)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.text.URLPath.classResourcePath(java.lang.Class):gnu.text.URLPath");
    }
}
