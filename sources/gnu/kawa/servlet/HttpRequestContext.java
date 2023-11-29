package gnu.kawa.servlet;

import com.microsoft.appcenter.http.DefaultHttpClient;
import gnu.mapping.InPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class HttpRequestContext {
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_OK = 200;
    static final int STATUS_SENT = -999;
    public static int importServletDefinitions;
    protected static final ThreadLocal<HttpRequestContext> instance = new ThreadLocal<>();
    ServletPrinter consumer;
    String localPath = "";
    String scriptPath = "";
    public int statusCode = 200;
    public String statusReasonPhrase = null;

    public abstract Object getAttribute(String str);

    public abstract String getContextPath();

    public abstract int getLocalPort();

    public abstract String getPathTranslated();

    public abstract String getQueryString();

    public abstract InetAddress getRemoteHost();

    public abstract String getRemoteIPAddress();

    public abstract int getRemotePort();

    public abstract String getRequestHeader(String str);

    public abstract List<String> getRequestHeaders(String str);

    public abstract Map<String, List<String>> getRequestHeaders();

    public abstract String getRequestMethod();

    public abstract Map<String, List<String>> getRequestParameters();

    public String getRequestScheme() {
        return "http";
    }

    public abstract InputStream getRequestStream();

    public abstract URI getRequestURI();

    public abstract URL getResourceURL(String str);

    public abstract OutputStream getResponseStream();

    public abstract void log(String str);

    public abstract void log(String str, Throwable th);

    public abstract boolean reset(boolean z);

    public abstract void sendResponseHeaders(int i, String str, long j) throws IOException;

    public abstract void setAttribute(String str, Object obj);

    public abstract void setResponseHeader(String str, String str2);

    public static HttpRequestContext getInstance() {
        HttpRequestContext httpRequestContext = instance.get();
        if (httpRequestContext != null) {
            return httpRequestContext;
        }
        throw new UnsupportedOperationException("can only be called by http-server");
    }

    public static HttpRequestContext getInstance(String str) {
        HttpRequestContext httpRequestContext = instance.get();
        if (httpRequestContext != null) {
            return httpRequestContext;
        }
        throw new UnsupportedOperationException(str + " can only be called within http-server");
    }

    public static void setInstance(HttpRequestContext httpRequestContext) {
        instance.set(httpRequestContext);
    }

    public InPort getRequestPort() {
        return new InPort(getRequestStream());
    }

    public String getRequestBodyChars() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(getRequestStream());
        int i = 1024;
        char[] cArr = new char[1024];
        int i2 = 0;
        while (true) {
            int i3 = i - i2;
            if (i3 <= 0) {
                char[] cArr2 = new char[(i * 2)];
                System.arraycopy(cArr, 0, cArr2, 0, i);
                i += i;
                cArr = cArr2;
            }
            int read = inputStreamReader.read(cArr, i2, i3);
            if (read < 0) {
                inputStreamReader.close();
                return new String(cArr, 0, i2);
            }
            i2 += read;
        }
    }

    public ServletPrinter getConsumer() throws IOException {
        if (this.consumer == null) {
            this.consumer = new ServletPrinter(this, 8192);
        }
        return this.consumer;
    }

    public String getRequestParameter(String str) {
        List list = getRequestParameters().get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    public String getScriptPath() {
        return this.scriptPath;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setScriptAndLocalPath(String str, String str2) {
        this.scriptPath = str;
        this.localPath = str2;
    }

    public String getRequestPath() {
        return getRequestURI().getPath();
    }

    public InetSocketAddress getLocalSocketAddress() {
        return new InetSocketAddress(getLocalHost(), getLocalPort());
    }

    public String getLocalIPAddress() {
        return getLocalHost().getHostAddress();
    }

    public InetAddress getLocalHost() {
        try {
            return InetAddress.getLocalHost();
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    public InetSocketAddress getRemoteSocketAddress() {
        return new InetSocketAddress(getRemoteHost(), getRemotePort());
    }

    public StringBuffer getRequestURLBuffer() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getRequestScheme());
        stringBuffer.append("://");
        String requestHeader = getRequestHeader("Host");
        if (requestHeader != null) {
            stringBuffer.append(requestHeader);
        } else {
            stringBuffer.append(getLocalIPAddress());
            stringBuffer.append(':');
            stringBuffer.append(getLocalPort());
        }
        stringBuffer.append(getRequestPath());
        return stringBuffer;
    }

    public void setContentType(String str) {
        setResponseHeader(DefaultHttpClient.CONTENT_TYPE_KEY, str);
    }

    /* access modifiers changed from: protected */
    public String normalizeToContext(String str) {
        String str2;
        if (str.length() <= 0 || str.charAt(0) != '/') {
            str2 = getScriptPath() + str;
        } else {
            str2 = str.substring(1);
        }
        if (str2.indexOf("..") < 0) {
            return str2;
        }
        String uri = URI.create(str2).normalize().toString();
        if (uri.startsWith("../")) {
            return null;
        }
        return uri;
    }

    public void sendNotFound(String str) throws IOException {
        byte[] bytes = ("The requested URL " + str + " was not found on this server.\r\n").getBytes();
        sendResponseHeaders(404, (String) null, (long) bytes.length);
        try {
            getResponseStream().write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
