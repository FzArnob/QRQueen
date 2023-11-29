package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.lingala.zip4j.util.InternalZipConstants;

public class NanoHTTPD {
    public static final String HTTP_BADREQUEST = "400 Bad Request";
    public static final String HTTP_FORBIDDEN = "403 Forbidden";
    public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
    public static final String HTTP_NOTFOUND = "404 Not Found";
    public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
    public static final String HTTP_NOTMODIFIED = "304 Not Modified";
    public static final String HTTP_OK = "200 OK";
    public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
    public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String HTTP_REDIRECT = "301 Moved Permanently";
    public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final String MIME_XML = "text/xml";
    /* access modifiers changed from: private */
    public static SimpleDateFormat hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static Hashtable f298hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Hashtable();
    protected static PrintStream myErr = System.err;
    protected static PrintStream myOut = System.out;
    /* access modifiers changed from: private */
    public static int zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs = 16384;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Thread f299hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final ServerSocket f300hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ThreadPoolExecutor f301hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new SynchronousQueue(), new b(this, (byte) 0));
    private int kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q;
    private File vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    public Response serve(String str, String str2, Properties properties, Properties properties2, Properties properties3, Socket socket) {
        PrintStream printStream = myOut;
        printStream.println(str2 + " '" + str + "' ");
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str3 = (String) propertyNames.nextElement();
            PrintStream printStream2 = myOut;
            printStream2.println("  HDR: '" + str3 + "' = '" + properties.getProperty(str3) + "'");
        }
        Enumeration<?> propertyNames2 = properties2.propertyNames();
        while (propertyNames2.hasMoreElements()) {
            String str4 = (String) propertyNames2.nextElement();
            PrintStream printStream3 = myOut;
            printStream3.println("  PRM: '" + str4 + "' = '" + properties2.getProperty(str4) + "'");
        }
        Enumeration<?> propertyNames3 = properties3.propertyNames();
        while (propertyNames3.hasMoreElements()) {
            String str5 = (String) propertyNames3.nextElement();
            PrintStream printStream4 = myOut;
            printStream4.println("  UPLOADED: '" + str5 + "' = '" + properties3.getProperty(str5) + "'");
        }
        return serveFile(str, properties, this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, true);
    }

    public class Response {
        public InputStream data;
        public Properties header;
        public String mimeType;
        public String status;

        public Response() {
            this.header = new Properties();
            this.status = NanoHTTPD.HTTP_OK;
        }

        public Response(String str, String str2, InputStream inputStream) {
            this.header = new Properties();
            this.status = str;
            this.mimeType = str2;
            this.data = inputStream;
        }

        public Response(String str, String str2, String str3) {
            this.header = new Properties();
            this.status = str;
            this.mimeType = str2;
            try {
                this.data = new ByteArrayInputStream(str3.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public void addHeader(String str, String str2) {
            this.header.put(str, str2);
        }
    }

    public NanoHTTPD(int i, File file) throws IOException {
        this.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q = i;
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = file;
        this.f300hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ServerSocket(this.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q);
        Thread thread = new Thread(new Runnable() {
            public final void run() {
                while (true) {
                    try {
                        NanoHTTPD nanoHTTPD = NanoHTTPD.this;
                        new a(NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(nanoHTTPD).accept());
                    } catch (IOException unused) {
                        return;
                    }
                }
            }
        });
        this.f299hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = thread;
        thread.setDaemon(true);
        this.f299hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
    }

    public void stop() {
        try {
            this.f300hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
            this.f299hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.join();
        } catch (IOException | InterruptedException unused) {
        }
    }

    public static void main(String[] strArr) {
        myOut.println("NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\n(Command line options: [-p port] [-d root-dir] [--licence])\n");
        File absoluteFile = new File(".").getAbsoluteFile();
        int i = 80;
        int i2 = 0;
        while (true) {
            if (i2 < strArr.length) {
                if (strArr[i2].equalsIgnoreCase("-p")) {
                    i = Integer.parseInt(strArr[i2 + 1]);
                } else if (strArr[i2].equalsIgnoreCase("-d")) {
                    absoluteFile = new File(strArr[i2 + 1]).getAbsoluteFile();
                } else if (strArr[i2].toLowerCase().endsWith("licence")) {
                    myOut.println("Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
                    break;
                }
                i2++;
            }
        }
        try {
            new NanoHTTPD(i, absoluteFile);
        } catch (IOException e) {
            myErr.println("Couldn't start server:\n".concat(String.valueOf(e)));
            System.exit(-1);
        }
        PrintStream printStream = myOut;
        printStream.println("Now serving files in port " + i + " from \"" + absoluteFile + "\"");
        myOut.println("Hit Enter to stop.\n");
        try {
            System.in.read();
        } catch (Throwable unused) {
        }
    }

    class b implements ThreadFactory {
        private b() {
        }

        /* synthetic */ b(NanoHTTPD nanoHTTPD, byte b) {
            this();
        }

        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(new ThreadGroup("biggerstack"), runnable, "HTTPD Session", 262144);
            thread.setDaemon(true);
            return thread;
        }
    }

    class a implements Runnable {

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private Socket f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        public a(Socket socket) {
            this.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = socket;
            Log.d("AppInvHTTPD", "NanoHTTPD: getPoolSize() = " + NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((NanoHTTPD) NanoHTTPD.this).getPoolSize());
            NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((NanoHTTPD) NanoHTTPD.this).execute(this);
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x0063 A[SYNTHETIC, Splitter:B:18:0x0063] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x008d A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00ab A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00b2 A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x00bb A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00c3 A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x00db A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x00fb A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x019f A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x01b4 A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:86:0x01ba A[Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8, all -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:93:0x0082 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r20 = this;
                r7 = r20
                java.lang.String r8 = "500 Internal Server Error"
                java.net.Socket r0 = r7.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r0 != 0) goto L_0x000d
                return
            L_0x000d:
                r1 = 8192(0x2000, float:1.14794E-41)
                byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r3 = 0
                int r1 = r0.read(r2, r3, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r1 > 0) goto L_0x0019
                return
            L_0x0019:
                java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r4.<init>(r2, r3, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r6.<init>(r4)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r5.<init>(r6)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.Properties r4 = new java.util.Properties     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r4.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.Properties r13 = new java.util.Properties     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r13.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.Properties r12 = new java.util.Properties     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r12.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.Properties r14 = new java.util.Properties     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r14.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.io.BufferedReader) r5, (java.util.Properties) r4, (java.util.Properties) r13, (java.util.Properties) r12)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r5 = "method"
                java.lang.String r11 = r4.getProperty(r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r5 = "uri"
                java.lang.String r10 = r4.getProperty(r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r4 = "content-length"
                java.lang.String r4 = r12.getProperty(r4)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r4 == 0) goto L_0x0059
                int r4 = java.lang.Integer.parseInt(r4)     // Catch:{ NumberFormatException -> 0x0059 }
                long r3 = (long) r4
                goto L_0x005e
            L_0x0059:
                r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            L_0x005e:
                r15 = 0
            L_0x005f:
                r16 = 1
                if (r15 >= r1) goto L_0x0082
                byte r9 = r2[r15]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r5 = 13
                if (r9 != r5) goto L_0x007f
                int r15 = r15 + 1
                byte r6 = r2[r15]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r9 = 10
                if (r6 != r9) goto L_0x007f
                int r15 = r15 + 1
                byte r6 = r2[r15]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r6 != r5) goto L_0x007f
                int r15 = r15 + 1
                byte r5 = r2[r15]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r5 != r9) goto L_0x007f
                r9 = 1
                goto L_0x0083
            L_0x007f:
                int r15 = r15 + 1
                goto L_0x005f
            L_0x0082:
                r9 = 0
            L_0x0083:
                int r15 = r15 + 1
                java.lang.String r5 = "PUT"
                boolean r5 = r11.equalsIgnoreCase(r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r5 == 0) goto L_0x00ab
                java.lang.String r5 = "upload"
                java.lang.String r6 = "bin"
                java.io.File r5 = java.io.File.createTempFile(r5, r6)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r5.deleteOnExit()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r6.<init>(r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r18 = r6
                java.lang.String r6 = "content"
                java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r14.put(r6, r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r6 = r18
                goto L_0x00b0
            L_0x00ab:
                java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r6.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x00b0:
                if (r15 >= r1) goto L_0x00b7
                int r5 = r1 - r15
                r6.write(r2, r15, r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x00b7:
                r18 = 0
                if (r15 >= r1) goto L_0x00c3
                int r2 = r1 - r15
                int r2 = r2 + 1
                r5 = r1
                long r1 = (long) r2     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                long r3 = r3 - r1
                goto L_0x00d1
            L_0x00c3:
                r5 = r1
                if (r9 == 0) goto L_0x00cf
                r1 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r9 != 0) goto L_0x00d1
            L_0x00cf:
                r3 = r18
            L_0x00d1:
                r1 = 512(0x200, float:7.175E-43)
                byte[] r2 = new byte[r1]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x00d5:
                if (r5 < 0) goto L_0x00f3
                int r5 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
                if (r5 <= 0) goto L_0x00f3
                r5 = 0
                int r15 = r0.read(r2, r5, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r17 = r2
                long r1 = (long) r15     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                long r3 = r3 - r1
                if (r15 <= 0) goto L_0x00ec
                r1 = r17
                r6.write(r1, r5, r15)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                goto L_0x00ee
            L_0x00ec:
                r1 = r17
            L_0x00ee:
                r2 = r1
                r5 = r15
                r1 = 512(0x200, float:7.175E-43)
                goto L_0x00d5
            L_0x00f3:
                java.lang.String r1 = "POST"
                boolean r1 = r11.equalsIgnoreCase(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r1 == 0) goto L_0x019f
                java.io.ByteArrayOutputStream r6 = (java.io.ByteArrayOutputStream) r6     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                byte[] r3 = r6.toByteArray()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r1.<init>(r3)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.BufferedReader r15 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r2.<init>(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r15.<init>(r2)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r1 = "content-type"
                java.lang.String r1 = r12.getProperty(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.StringTokenizer r2 = new java.util.StringTokenizer     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r4 = "; "
                r2.<init>(r1, r4)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                boolean r1 = r2.hasMoreTokens()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r4 = ""
                if (r1 == 0) goto L_0x012a
                java.lang.String r1 = r2.nextToken()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                goto L_0x012b
            L_0x012a:
                r1 = r4
            L_0x012b:
                java.lang.String r5 = "multipart/form-data"
                boolean r1 = r1.equalsIgnoreCase(r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r1 == 0) goto L_0x0167
                boolean r1 = r2.hasMoreTokens()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r4 = "400 Bad Request"
                if (r1 != 0) goto L_0x0140
                java.lang.String r1 = "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html"
                r7.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r4, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x0140:
                java.lang.String r1 = r2.nextToken()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.StringTokenizer r2 = new java.util.StringTokenizer     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r5 = "="
                r2.<init>(r1, r5)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                int r1 = r2.countTokens()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r5 = 2
                if (r1 == r5) goto L_0x0157
                java.lang.String r1 = "BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html"
                r7.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r4, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x0157:
                r2.nextToken()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r2 = r2.nextToken()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r1 = r20
                r4 = r15
                r5 = r13
                r6 = r14
                r1.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                goto L_0x019b
            L_0x0167:
                r1 = 512(0x200, float:7.175E-43)
                char[] r1 = new char[r1]     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                int r2 = r15.read(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x016f:
                if (r2 < 0) goto L_0x0194
                java.lang.String r3 = "\r\n"
                boolean r3 = r4.endsWith(r3)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r3 != 0) goto L_0x0194
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r3.<init>()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r3.append(r4)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r4 = 0
                java.lang.String r2 = java.lang.String.valueOf(r1, r4, r2)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r3.append(r2)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                int r3 = r15.read(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r4 = r2
                r2 = r3
                goto L_0x016f
            L_0x0194:
                java.lang.String r1 = r4.trim()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r1, (java.util.Properties) r13)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x019b:
                r15.close()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                goto L_0x01aa
            L_0x019f:
                java.lang.String r1 = "PUT "
                boolean r1 = r11.equalsIgnoreCase(r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r1 == 0) goto L_0x01aa
                r6.close()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x01aa:
                com.google.appinventor.components.runtime.util.NanoHTTPD r9 = com.google.appinventor.components.runtime.util.NanoHTTPD.this     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.net.Socket r15 = r7.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                com.google.appinventor.components.runtime.util.NanoHTTPD$Response r1 = r9.serve(r10, r11, r12, r13, r14, r15)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                if (r1 != 0) goto L_0x01ba
                java.lang.String r1 = "SERVER INTERNAL ERROR: Serve() returned a null response."
                r7.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r8, r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                goto L_0x01c5
            L_0x01ba:
                java.lang.String r2 = r1.status     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.lang.String r3 = r1.mimeType     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.util.Properties r4 = r1.header     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                java.io.InputStream r1 = r1.data     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
                r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r2, (java.lang.String) r3, (java.util.Properties) r4, (java.io.InputStream) r1)     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x01c5:
                r0.close()     // Catch:{ IOException -> 0x01c9, InterruptedException -> 0x01c8 }
            L_0x01c8:
                return
            L_0x01c9:
                r0 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x01df }
                java.lang.String r2 = "SERVER INTERNAL ERROR: IOException: "
                r1.<init>(r2)     // Catch:{ all -> 0x01df }
                java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x01df }
                r1.append(r0)     // Catch:{ all -> 0x01df }
                java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01df }
                r7.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r8, r0)     // Catch:{ all -> 0x01df }
            L_0x01df:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.NanoHTTPD.a.run():void");
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BufferedReader bufferedReader, Properties properties, Properties properties2, Properties properties3) throws InterruptedException {
            String str;
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    if (!stringTokenizer.hasMoreTokens()) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                    }
                    properties.put("method", stringTokenizer.nextToken());
                    if (!stringTokenizer.hasMoreTokens()) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    }
                    String nextToken = stringTokenizer.nextToken();
                    int indexOf = nextToken.indexOf(63);
                    if (indexOf >= 0) {
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(nextToken.substring(indexOf + 1), properties2);
                        str = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(nextToken.substring(0, indexOf));
                    } else {
                        str = KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(nextToken);
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        String readLine2 = bufferedReader.readLine();
                        while (readLine2 != null && readLine2.trim().length() > 0) {
                            int indexOf2 = readLine2.indexOf(58);
                            if (indexOf2 >= 0) {
                                properties3.put(readLine2.substring(0, indexOf2).trim().toLowerCase(), readLine2.substring(indexOf2 + 1).trim());
                            }
                            readLine2 = bufferedReader.readLine();
                        }
                    }
                    properties.put("uri", str);
                }
            } catch (IOException e) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, byte[] bArr, BufferedReader bufferedReader, Properties properties, Properties properties2) throws InterruptedException {
            Properties properties3;
            String str2;
            String str3 = str;
            byte[] bArr2 = bArr;
            try {
                byte[] bytes = str.getBytes();
                Vector vector = new Vector();
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = -1;
                while (i2 < bArr2.length) {
                    if (bArr2[i2] == bytes[i3]) {
                        if (i3 == 0) {
                            i4 = i2;
                        }
                        i3++;
                        if (i3 == bytes.length) {
                            vector.addElement(Integer.valueOf(i4));
                        } else {
                            i2++;
                        }
                    } else {
                        i2 -= i3;
                    }
                    i3 = 0;
                    i4 = -1;
                    i2++;
                }
                int size = vector.size();
                int[] iArr = new int[size];
                for (int i5 = 0; i5 < size; i5++) {
                    iArr[i5] = ((Integer) vector.elementAt(i5)).intValue();
                }
                String readLine = bufferedReader.readLine();
                int i6 = 1;
                while (readLine != null) {
                    if (readLine.indexOf(str3) == -1) {
                        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
                    }
                    i6++;
                    Properties properties4 = new Properties();
                    String readLine2 = bufferedReader.readLine();
                    while (readLine2 != null && readLine2.trim().length() > 0) {
                        int indexOf = readLine2.indexOf(58);
                        if (indexOf != -1) {
                            properties4.put(readLine2.substring(i, indexOf).trim().toLowerCase(), readLine2.substring(indexOf + 1).trim());
                        }
                        readLine2 = bufferedReader.readLine();
                    }
                    if (readLine2 != null) {
                        String property = properties4.getProperty("content-disposition");
                        if (property == null) {
                            wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
                        }
                        StringTokenizer stringTokenizer = new StringTokenizer(property, "; ");
                        Properties properties5 = new Properties();
                        while (stringTokenizer.hasMoreTokens()) {
                            String nextToken = stringTokenizer.nextToken();
                            int indexOf2 = nextToken.indexOf(61);
                            if (indexOf2 != -1) {
                                properties5.put(nextToken.substring(i, indexOf2).trim().toLowerCase(), nextToken.substring(indexOf2 + 1).trim());
                            }
                            i = 0;
                        }
                        String property2 = properties5.getProperty(CommonProperties.NAME);
                        String substring = property2.substring(1, property2.length() - 1);
                        String str4 = "";
                        if (properties4.getProperty("content-type") == null) {
                            while (readLine2 != null && readLine2.indexOf(str3) == -1) {
                                readLine2 = bufferedReader.readLine();
                                if (readLine2 != null) {
                                    int indexOf3 = readLine2.indexOf(str3);
                                    if (indexOf3 == -1) {
                                        str4 = str4 + readLine2;
                                    } else {
                                        str4 = str4 + readLine2.substring(0, indexOf3 - 2);
                                    }
                                }
                            }
                            properties3 = properties;
                            str2 = str4;
                            Properties properties6 = properties2;
                        } else {
                            if (i6 > size) {
                                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, "Error processing request");
                            }
                            int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bArr2, iArr[i6 - 2]);
                            properties2.put(substring, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bArr2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, (iArr[i6 - 1] - hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2) - 4));
                            String property3 = properties5.getProperty("filename");
                            str2 = property3.substring(1, property3.length() - 1);
                            do {
                                readLine2 = bufferedReader.readLine();
                                if (readLine2 == null || readLine2.indexOf(str3) != -1) {
                                    properties3 = properties;
                                }
                                readLine2 = bufferedReader.readLine();
                                break;
                            } while (readLine2.indexOf(str3) != -1);
                            properties3 = properties;
                        }
                        properties3.put(substring, str2);
                    } else {
                        Properties properties7 = properties;
                        Properties properties8 = properties2;
                    }
                    readLine = readLine2;
                    i = 0;
                }
            } catch (IOException e) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage());
            }
        }

        private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, int i, int i2) {
            if (i2 <= 0) {
                return "";
            }
            try {
                File createTempFile = File.createTempFile("NanoHTTPD", "", new File(System.getProperty("java.io.tmpdir")));
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                fileOutputStream.write(bArr, i, i2);
                fileOutputStream.close();
                return createTempFile.getAbsolutePath();
            } catch (Exception e) {
                PrintStream printStream = NanoHTTPD.myErr;
                printStream.println("Error: " + e.getMessage());
                return "";
            }
        }

        private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, int i) {
            while (i < bArr.length) {
                if (bArr[i] == 13) {
                    i++;
                    if (bArr[i] == 10) {
                        i++;
                        if (bArr[i] == 13) {
                            i++;
                            if (bArr[i] == 10) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                i++;
            }
            return i + 1;
        }

        private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(String str) throws InterruptedException {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                int i = 0;
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt == '%') {
                        stringBuffer.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                    } else if (charAt != '+') {
                        stringBuffer.append(charAt);
                    } else {
                        stringBuffer.append(' ');
                    }
                    i++;
                }
                return stringBuffer.toString();
            } catch (Exception unused) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(NanoHTTPD.HTTP_BADREQUEST, "BAD REQUEST: Bad percent-encoding.");
                return null;
            }
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, Properties properties) throws InterruptedException {
            if (str != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
                while (stringTokenizer.hasMoreTokens()) {
                    String nextToken = stringTokenizer.nextToken();
                    int indexOf = nextToken.indexOf(61);
                    if (indexOf >= 0) {
                        properties.put(KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(nextToken.substring(0, indexOf)).trim(), KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH(nextToken.substring(indexOf + 1)));
                    }
                }
            }
        }

        private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str, String str2) throws InterruptedException {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, "text/plain", (Properties) null, (InputStream) new ByteArrayInputStream(str2.getBytes()));
            throw new InterruptedException();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
            r5.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x00d7 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String r6, java.lang.String r7, java.util.Properties r8, java.io.InputStream r9) {
            /*
                r5 = this;
                if (r6 == 0) goto L_0x00cf
                java.net.Socket r0 = r5.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x00d7 }
                java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ IOException -> 0x00d7 }
                java.io.PrintWriter r1 = new java.io.PrintWriter     // Catch:{ IOException -> 0x00d7 }
                r1.<init>(r0)     // Catch:{ IOException -> 0x00d7 }
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r3 = "HTTP/1.0 "
                r2.<init>(r3)     // Catch:{ IOException -> 0x00d7 }
                r2.append(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r6 = " \r\n"
                r2.append(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r6 = r2.toString()     // Catch:{ IOException -> 0x00d7 }
                r1.print(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r6 = "\r\n"
                if (r7 == 0) goto L_0x003b
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r3 = "Content-Type: "
                r2.<init>(r3)     // Catch:{ IOException -> 0x00d7 }
                r2.append(r7)     // Catch:{ IOException -> 0x00d7 }
                r2.append(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r7 = r2.toString()     // Catch:{ IOException -> 0x00d7 }
                r1.print(r7)     // Catch:{ IOException -> 0x00d7 }
            L_0x003b:
                if (r8 == 0) goto L_0x0045
                java.lang.String r7 = "Date"
                java.lang.String r7 = r8.getProperty(r7)     // Catch:{ IOException -> 0x00d7 }
                if (r7 != 0) goto L_0x0066
            L_0x0045:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r2 = "Date: "
                r7.<init>(r2)     // Catch:{ IOException -> 0x00d7 }
                java.text.SimpleDateFormat r2 = com.google.appinventor.components.runtime.util.NanoHTTPD.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x00d7 }
                java.util.Date r3 = new java.util.Date     // Catch:{ IOException -> 0x00d7 }
                r3.<init>()     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r2 = r2.format(r3)     // Catch:{ IOException -> 0x00d7 }
                r7.append(r2)     // Catch:{ IOException -> 0x00d7 }
                r7.append(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00d7 }
                r1.print(r7)     // Catch:{ IOException -> 0x00d7 }
            L_0x0066:
                if (r8 == 0) goto L_0x0097
                java.util.Enumeration r7 = r8.keys()     // Catch:{ IOException -> 0x00d7 }
            L_0x006c:
                boolean r2 = r7.hasMoreElements()     // Catch:{ IOException -> 0x00d7 }
                if (r2 == 0) goto L_0x0097
                java.lang.Object r2 = r7.nextElement()     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r3 = r8.getProperty(r2)     // Catch:{ IOException -> 0x00d7 }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00d7 }
                r4.<init>()     // Catch:{ IOException -> 0x00d7 }
                r4.append(r2)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r2 = ": "
                r4.append(r2)     // Catch:{ IOException -> 0x00d7 }
                r4.append(r3)     // Catch:{ IOException -> 0x00d7 }
                r4.append(r6)     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r2 = r4.toString()     // Catch:{ IOException -> 0x00d7 }
                r1.print(r2)     // Catch:{ IOException -> 0x00d7 }
                goto L_0x006c
            L_0x0097:
                r1.print(r6)     // Catch:{ IOException -> 0x00d7 }
                r1.flush()     // Catch:{ IOException -> 0x00d7 }
                if (r9 == 0) goto L_0x00c3
                int r6 = r9.available()     // Catch:{ IOException -> 0x00d7 }
                int r7 = com.google.appinventor.components.runtime.util.NanoHTTPD.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs     // Catch:{ IOException -> 0x00d7 }
                byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x00d7 }
            L_0x00a9:
                if (r6 <= 0) goto L_0x00c3
                int r8 = com.google.appinventor.components.runtime.util.NanoHTTPD.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs     // Catch:{ IOException -> 0x00d7 }
                if (r6 <= r8) goto L_0x00b6
                int r8 = com.google.appinventor.components.runtime.util.NanoHTTPD.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs     // Catch:{ IOException -> 0x00d7 }
                goto L_0x00b7
            L_0x00b6:
                r8 = r6
            L_0x00b7:
                r1 = 0
                int r8 = r9.read(r7, r1, r8)     // Catch:{ IOException -> 0x00d7 }
                if (r8 <= 0) goto L_0x00c3
                r0.write(r7, r1, r8)     // Catch:{ IOException -> 0x00d7 }
                int r6 = r6 - r8
                goto L_0x00a9
            L_0x00c3:
                r0.flush()     // Catch:{ IOException -> 0x00d7 }
                r0.close()     // Catch:{ IOException -> 0x00d7 }
                if (r9 == 0) goto L_0x00ce
                r9.close()     // Catch:{ IOException -> 0x00d7 }
            L_0x00ce:
                return
            L_0x00cf:
                java.lang.Error r6 = new java.lang.Error     // Catch:{ IOException -> 0x00d7 }
                java.lang.String r7 = "sendResponse(): Status can't be null."
                r6.<init>(r7)     // Catch:{ IOException -> 0x00d7 }
                throw r6     // Catch:{ IOException -> 0x00d7 }
            L_0x00d7:
                java.net.Socket r6 = r5.f302hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ all -> 0x00dc }
                r6.close()     // Catch:{ all -> 0x00dc }
            L_0x00dc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.NanoHTTPD.a.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String, java.lang.String, java.util.Properties, java.io.InputStream):void");
        }
    }

    private static String l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/ ", true);
        String str2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                str2 = str2 + InternalZipConstants.ZIP_FILE_SEPARATOR;
            } else if (nextToken.equals(" ")) {
                str2 = str2 + "%20";
            } else {
                str2 = str2 + URLEncoder.encode(nextToken);
            }
        }
        return str2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x02ce A[SYNTHETIC, Splitter:B:72:0x02ce] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.appinventor.components.runtime.util.NanoHTTPD.Response serveFile(java.lang.String r29, java.util.Properties r30, java.io.File r31, boolean r32) {
        /*
            r28 = this;
            r0 = r28
            r1 = r30
            r2 = r31
            boolean r3 = r31.isDirectory()
            java.lang.String r5 = "text/plain"
            if (r3 != 0) goto L_0x0018
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.String r6 = "500 Internal Server Error"
            java.lang.String r7 = "INTERNAL ERRROR: serveFile(): given homeDir is not a directory."
            r3.<init>((java.lang.String) r6, (java.lang.String) r5, (java.lang.String) r7)
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            r6 = 47
            java.lang.String r7 = "403 Forbidden"
            r8 = 0
            if (r3 != 0) goto L_0x0058
            java.lang.String r9 = r29.trim()
            char r10 = java.io.File.separatorChar
            java.lang.String r9 = r9.replace(r10, r6)
            r10 = 63
            int r11 = r9.indexOf(r10)
            if (r11 < 0) goto L_0x003a
            int r10 = r9.indexOf(r10)
            java.lang.String r9 = r9.substring(r8, r10)
        L_0x003a:
            java.lang.String r10 = ".."
            boolean r11 = r9.startsWith(r10)
            if (r11 != 0) goto L_0x0050
            boolean r10 = r9.endsWith(r10)
            if (r10 != 0) goto L_0x0050
            java.lang.String r10 = "../"
            int r10 = r9.indexOf(r10)
            if (r10 < 0) goto L_0x005a
        L_0x0050:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.String r10 = "FORBIDDEN: Won't serve ../ for security reasons."
            r3.<init>((java.lang.String) r7, (java.lang.String) r5, (java.lang.String) r10)
            goto L_0x005a
        L_0x0058:
            r9 = r29
        L_0x005a:
            java.io.File r10 = new java.io.File
            r10.<init>(r2, r9)
            if (r3 != 0) goto L_0x0070
            boolean r11 = r10.exists()
            if (r11 != 0) goto L_0x0070
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.String r11 = "404 Not Found"
            java.lang.String r12 = "Error 404, file not found."
            r3.<init>((java.lang.String) r11, (java.lang.String) r5, (java.lang.String) r12)
        L_0x0070:
            java.lang.String r11 = "200 OK"
            java.lang.String r12 = "/"
            r13 = 1
            if (r3 != 0) goto L_0x02cb
            boolean r14 = r10.isDirectory()
            if (r14 == 0) goto L_0x02cb
            boolean r14 = r9.endsWith(r12)
            java.lang.String r15 = "\">"
            java.lang.String r4 = "text/html"
            if (r14 != 0) goto L_0x00bb
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r9)
            r3.append(r12)
            java.lang.String r9 = r3.toString()
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r6 = "<html><body>Redirected: <a href=\""
            r14.<init>(r6)
            r14.append(r9)
            r14.append(r15)
            r14.append(r9)
            java.lang.String r6 = "</a></body></html>"
            r14.append(r6)
            java.lang.String r6 = r14.toString()
            java.lang.String r14 = "301 Moved Permanently"
            r3.<init>((java.lang.String) r14, (java.lang.String) r4, (java.lang.String) r6)
            java.lang.String r6 = "Location"
            r3.addHeader(r6, r9)
        L_0x00bb:
            if (r3 != 0) goto L_0x02cb
            java.io.File r6 = new java.io.File
            java.lang.String r14 = "index.html"
            r6.<init>(r10, r14)
            boolean r6 = r6.exists()
            if (r6 == 0) goto L_0x00e2
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            java.lang.String r6 = "/index.html"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r10.<init>(r2, r4)
            goto L_0x02cb
        L_0x00e2:
            java.io.File r6 = new java.io.File
            java.lang.String r14 = "index.htm"
            r6.<init>(r10, r14)
            boolean r6 = r6.exists()
            if (r6 == 0) goto L_0x0107
            java.io.File r10 = new java.io.File
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            java.lang.String r6 = "/index.htm"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r10.<init>(r2, r4)
            goto L_0x02cb
        L_0x0107:
            if (r32 == 0) goto L_0x02c2
            boolean r2 = r10.canRead()
            if (r2 == 0) goto L_0x02c2
            java.lang.String[] r2 = r10.list()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r6 = "<html><body><h1>Directory "
            r3.<init>(r6)
            r3.append(r9)
            java.lang.String r6 = "</h1><br/>"
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            int r6 = r9.length()
            if (r6 <= r13) goto L_0x0161
            int r6 = r9.length()
            int r6 = r6 - r13
            java.lang.String r6 = r9.substring(r8, r6)
            r14 = 47
            int r14 = r6.lastIndexOf(r14)
            if (r14 < 0) goto L_0x0161
            int r6 = r6.length()
            if (r14 >= r6) goto L_0x0161
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            java.lang.String r3 = "<b><a href=\""
            r6.append(r3)
            int r14 = r14 + r13
            java.lang.String r3 = r9.substring(r8, r14)
            r6.append(r3)
            java.lang.String r3 = "\">..</a></b><br/>"
            r6.append(r3)
            java.lang.String r3 = r6.toString()
        L_0x0161:
            if (r2 == 0) goto L_0x02a9
            r6 = 0
        L_0x0164:
            int r14 = r2.length
            if (r6 >= r14) goto L_0x02a9
            java.io.File r14 = new java.io.File
            r8 = r2[r6]
            r14.<init>(r10, r8)
            boolean r8 = r14.isDirectory()
            if (r8 == 0) goto L_0x019c
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r3)
            java.lang.String r3 = "<b>"
            r13.append(r3)
            java.lang.String r3 = r13.toString()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r31 = r3
            r3 = r2[r6]
            r13.append(r3)
            r13.append(r12)
            java.lang.String r3 = r13.toString()
            r2[r6] = r3
            r3 = r31
        L_0x019c:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r3)
            java.lang.String r3 = "<a href=\""
            r13.append(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r9)
            r18 = r9
            r9 = r2[r6]
            r3.append(r9)
            java.lang.String r3 = r3.toString()
            java.lang.String r3 = l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j(r3)
            r13.append(r3)
            r13.append(r15)
            r3 = r2[r6]
            r13.append(r3)
            java.lang.String r3 = "</a>"
            r13.append(r3)
            java.lang.String r3 = r13.toString()
            boolean r9 = r14.isFile()
            if (r9 == 0) goto L_0x0274
            long r13 = r14.length()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r3)
            java.lang.String r3 = " &nbsp;<font size=2>("
            r9.append(r3)
            java.lang.String r3 = r9.toString()
            r19 = 1024(0x400, double:5.06E-321)
            int r9 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r9 >= 0) goto L_0x020c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r3)
            r9.append(r13)
            java.lang.String r3 = " bytes"
            r9.append(r3)
            java.lang.String r3 = r9.toString()
            r31 = r2
            goto L_0x0262
        L_0x020c:
            r21 = 100
            r23 = 10
            java.lang.String r9 = "."
            r25 = 1048576(0x100000, double:5.180654E-318)
            int r27 = (r13 > r25 ? 1 : (r13 == r25 ? 0 : -1))
            if (r27 >= 0) goto L_0x023e
            r31 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            long r0 = r13 / r19
            r2.append(r0)
            r2.append(r9)
            long r13 = r13 % r19
            long r13 = r13 / r23
            long r13 = r13 % r21
            r2.append(r13)
            java.lang.String r0 = " KB"
            r2.append(r0)
            java.lang.String r3 = r2.toString()
            goto L_0x0262
        L_0x023e:
            r31 = r2
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            long r1 = r13 / r25
            r0.append(r1)
            r0.append(r9)
            long r13 = r13 % r25
            long r13 = r13 / r23
            long r13 = r13 % r21
            r0.append(r13)
            java.lang.String r1 = " MB"
            r0.append(r1)
            java.lang.String r3 = r0.toString()
        L_0x0262:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r1 = ")</font>"
            r0.append(r1)
            java.lang.String r3 = r0.toString()
            goto L_0x0276
        L_0x0274:
            r31 = r2
        L_0x0276:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r1 = "<br/>"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            if (r8 == 0) goto L_0x029a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "</b>"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x029a:
            r3 = r0
            int r6 = r6 + 1
            r8 = 0
            r13 = 1
            r0 = r28
            r1 = r30
            r2 = r31
            r9 = r18
            goto L_0x0164
        L_0x02a9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r1 = "</body></html>"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            r1 = r28
            r3.<init>((java.lang.String) r11, (java.lang.String) r4, (java.lang.String) r0)
            goto L_0x02cc
        L_0x02c2:
            r1 = r0
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.String r0 = "FORBIDDEN: No directory listing."
            r3.<init>((java.lang.String) r7, (java.lang.String) r5, (java.lang.String) r0)
            goto L_0x02cc
        L_0x02cb:
            r1 = r0
        L_0x02cc:
            if (r3 != 0) goto L_0x042e
            java.lang.String r0 = r10.getCanonicalPath()     // Catch:{ IOException -> 0x041e }
            r2 = 46
            int r0 = r0.lastIndexOf(r2)     // Catch:{ IOException -> 0x041e }
            if (r0 < 0) goto L_0x02f2
            java.util.Hashtable r2 = f298hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x041e }
            java.lang.String r3 = r10.getCanonicalPath()     // Catch:{ IOException -> 0x041e }
            r4 = 1
            int r0 = r0 + r4
            java.lang.String r0 = r3.substring(r0)     // Catch:{ IOException -> 0x041e }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ IOException -> 0x041e }
            java.lang.Object r0 = r2.get(r0)     // Catch:{ IOException -> 0x041e }
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x041e }
            goto L_0x02f3
        L_0x02f2:
            r4 = 0
        L_0x02f3:
            if (r4 != 0) goto L_0x02f7
            java.lang.String r4 = "application/octet-stream"
        L_0x02f7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x041e }
            r0.<init>()     // Catch:{ IOException -> 0x041e }
            java.lang.String r2 = r10.getAbsolutePath()     // Catch:{ IOException -> 0x041e }
            r0.append(r2)     // Catch:{ IOException -> 0x041e }
            long r2 = r10.lastModified()     // Catch:{ IOException -> 0x041e }
            r0.append(r2)     // Catch:{ IOException -> 0x041e }
            long r2 = r10.length()     // Catch:{ IOException -> 0x041e }
            r0.append(r2)     // Catch:{ IOException -> 0x041e }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x041e }
            int r0 = r0.hashCode()     // Catch:{ IOException -> 0x041e }
            java.lang.String r0 = java.lang.Integer.toHexString(r0)     // Catch:{ IOException -> 0x041e }
            r2 = -1
            java.lang.String r6 = "range"
            r8 = r30
            java.lang.String r6 = r8.getProperty(r6)     // Catch:{ IOException -> 0x041e }
            if (r6 == 0) goto L_0x0359
            java.lang.String r9 = "bytes="
            boolean r9 = r6.startsWith(r9)     // Catch:{ IOException -> 0x041e }
            if (r9 == 0) goto L_0x0359
            r9 = 6
            java.lang.String r6 = r6.substring(r9)     // Catch:{ IOException -> 0x041e }
            r9 = 45
            int r9 = r6.indexOf(r9)     // Catch:{ IOException -> 0x041e }
            if (r9 <= 0) goto L_0x0354
            r15 = 0
            java.lang.String r15 = r6.substring(r15, r9)     // Catch:{ NumberFormatException -> 0x0354 }
            long r15 = java.lang.Long.parseLong(r15)     // Catch:{ NumberFormatException -> 0x0354 }
            r17 = 1
            int r9 = r9 + 1
            java.lang.String r9 = r6.substring(r9)     // Catch:{ NumberFormatException -> 0x0356 }
            long r2 = java.lang.Long.parseLong(r9)     // Catch:{ NumberFormatException -> 0x0356 }
            goto L_0x0356
        L_0x0354:
            r15 = 0
        L_0x0356:
            r9 = r7
            r7 = r15
            goto L_0x035c
        L_0x0359:
            r9 = r7
            r7 = 0
        L_0x035c:
            long r13 = r10.length()     // Catch:{ IOException -> 0x0419 }
            java.lang.String r15 = ""
            r16 = r9
            java.lang.String r9 = "ETag"
            if (r6 == 0) goto L_0x03ea
            r17 = 0
            int r6 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r6 < 0) goto L_0x03ea
            java.lang.String r6 = "Content-Range"
            int r11 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r11 < 0) goto L_0x0391
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r2 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ IOException -> 0x038d }
            java.lang.String r3 = "416 Requested Range Not Satisfiable"
            r2.<init>((java.lang.String) r3, (java.lang.String) r5, (java.lang.String) r15)     // Catch:{ IOException -> 0x038d }
            java.lang.String r3 = "bytes 0-0/"
            java.lang.String r4 = java.lang.String.valueOf(r13)     // Catch:{ IOException -> 0x038d }
            java.lang.String r3 = r3.concat(r4)     // Catch:{ IOException -> 0x038d }
            r2.addHeader(r6, r3)     // Catch:{ IOException -> 0x038d }
            r2.addHeader(r9, r0)     // Catch:{ IOException -> 0x038d }
            goto L_0x042f
        L_0x038d:
            r17 = r5
            goto L_0x0422
        L_0x0391:
            r17 = 1
            r19 = 0
            int r11 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r11 >= 0) goto L_0x039b
            long r2 = r13 - r17
        L_0x039b:
            long r21 = r2 - r7
            long r21 = r21 + r17
            int r11 = (r21 > r19 ? 1 : (r21 == r19 ? 0 : -1))
            r17 = r5
            r29 = r6
            if (r11 >= 0) goto L_0x03aa
            r5 = r19
            goto L_0x03ac
        L_0x03aa:
            r5 = r21
        L_0x03ac:
            com.google.appinventor.components.runtime.util.NanoHTTPD$2 r11 = new com.google.appinventor.components.runtime.util.NanoHTTPD$2     // Catch:{ IOException -> 0x0422 }
            r11.<init>(r10, r5)     // Catch:{ IOException -> 0x0422 }
            r11.skip(r7)     // Catch:{ IOException -> 0x0422 }
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r10 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ IOException -> 0x0422 }
            java.lang.String r15 = "206 Partial Content"
            r10.<init>((java.lang.String) r15, (java.lang.String) r4, (java.io.InputStream) r11)     // Catch:{ IOException -> 0x0422 }
            java.lang.String r4 = "Content-Length"
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ IOException -> 0x0422 }
            r10.addHeader(r4, r5)     // Catch:{ IOException -> 0x0422 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0422 }
            java.lang.String r5 = "bytes "
            r4.<init>(r5)     // Catch:{ IOException -> 0x0422 }
            r4.append(r7)     // Catch:{ IOException -> 0x0422 }
            java.lang.String r5 = "-"
            r4.append(r5)     // Catch:{ IOException -> 0x0422 }
            r4.append(r2)     // Catch:{ IOException -> 0x0422 }
            r4.append(r12)     // Catch:{ IOException -> 0x0422 }
            r4.append(r13)     // Catch:{ IOException -> 0x0422 }
            java.lang.String r2 = r4.toString()     // Catch:{ IOException -> 0x0422 }
            r3 = r29
            r10.addHeader(r3, r2)     // Catch:{ IOException -> 0x0422 }
            r10.addHeader(r9, r0)     // Catch:{ IOException -> 0x0422 }
            r2 = r10
            goto L_0x042f
        L_0x03ea:
            r17 = r5
            java.lang.String r2 = "if-none-match"
            r3 = r30
            java.lang.String r2 = r3.getProperty(r2)     // Catch:{ IOException -> 0x0422 }
            boolean r2 = r0.equals(r2)     // Catch:{ IOException -> 0x0422 }
            if (r2 == 0) goto L_0x0402
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r2 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ IOException -> 0x0422 }
            java.lang.String r0 = "304 Not Modified"
            r2.<init>((java.lang.String) r0, (java.lang.String) r4, (java.lang.String) r15)     // Catch:{ IOException -> 0x0422 }
            goto L_0x042f
        L_0x0402:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r3 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response     // Catch:{ IOException -> 0x0422 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0422 }
            r2.<init>(r10)     // Catch:{ IOException -> 0x0422 }
            r3.<init>((java.lang.String) r11, (java.lang.String) r4, (java.io.InputStream) r2)     // Catch:{ IOException -> 0x0422 }
            java.lang.String r2 = "Content-Length"
            java.lang.String r4 = java.lang.String.valueOf(r13)     // Catch:{ IOException -> 0x0422 }
            r3.addHeader(r2, r4)     // Catch:{ IOException -> 0x0422 }
            r3.addHeader(r9, r0)     // Catch:{ IOException -> 0x0422 }
            goto L_0x042e
        L_0x0419:
            r17 = r5
            r16 = r9
            goto L_0x0422
        L_0x041e:
            r17 = r5
            r16 = r7
        L_0x0422:
            com.google.appinventor.components.runtime.util.NanoHTTPD$Response r2 = new com.google.appinventor.components.runtime.util.NanoHTTPD$Response
            java.lang.String r0 = "FORBIDDEN: Reading file failed."
            r3 = r16
            r4 = r17
            r2.<init>((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r0)
            goto L_0x042f
        L_0x042e:
            r2 = r3
        L_0x042f:
            java.lang.String r0 = "Accept-Ranges"
            java.lang.String r3 = "bytes"
            r2.addHeader(r0, r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.NanoHTTPD.serveFile(java.lang.String, java.util.Properties, java.io.File, boolean):com.google.appinventor.components.runtime.util.NanoHTTPD$Response");
    }

    static {
        StringTokenizer stringTokenizer = new StringTokenizer("css            text/css htm            text/html html           text/html xml            text/xml txt            text/plain asc            text/plain gif            image/gif jpg            image/jpeg jpeg           image/jpeg png            image/png mp3            audio/mpeg m3u            audio/mpeg-url mp4            video/mp4 ogv            video/ogg flv            video/x-flv mov            video/quicktime swf            application/x-shockwave-flash js                     application/javascript pdf            application/pdf doc            application/msword ogg            application/x-ogg zip            application/octet-stream exe            application/octet-stream class          application/octet-stream ");
        while (stringTokenizer.hasMoreTokens()) {
            f298hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}
