package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class IOUtils {
    public static void closeQuietly(String str, Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.w(str, "Failed to close resource", e);
            }
        }
    }

    public static byte[] readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String readStreamAsString(InputStream inputStream, String str) throws IOException {
        return new String(readStream(inputStream), str);
    }

    public static String readStreamAsString(InputStream inputStream) throws IOException {
        return readStreamAsString(inputStream, "UTF-8");
    }

    public static String readReader(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read <= 0) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public static String normalizeNewLines(String str) {
        return str.replaceAll("\r\n", "\n");
    }

    public static void mkdirs(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Unable to create directory for ".concat(String.valueOf(file)));
        }
    }
}
