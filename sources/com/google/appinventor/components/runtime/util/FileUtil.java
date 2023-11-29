package com.google.appinventor.components.runtime.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.RuntimeError;
import com.microsoft.appcenter.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

public class FileUtil {
    private static final String LOG_TAG = "FileUtil";

    private FileUtil() {
    }

    public static String getFileUrl(String str) {
        return new File(str).toURI().toString();
    }

    @Deprecated
    public static byte[] readFile(String str) throws IOException {
        Log.w(LOG_TAG, "Calling deprecated function readFile", new IllegalAccessException());
        return readFile(Form.getActiveForm(), str);
    }

    public static byte[] readFile(Form form, String str) throws IOException {
        InputStream openFile;
        if (str.startsWith("file://")) {
            str = str.substring(7);
        }
        InputStream inputStream = null;
        try {
            if (str.startsWith("/android_asset/")) {
                openFile = form.openAsset(str.substring(str.lastIndexOf(47) + 1));
            } else if (new File(str).isFile()) {
                openFile = openFile(form, str);
            } else {
                throw new FileNotFoundException("Cannot find file: ".concat(String.valueOf(str)));
            }
            inputStream = openFile;
            return IOUtils.readStream(inputStream);
        } finally {
            IOUtils.closeQuietly(LOG_TAG, inputStream);
        }
    }

    @Deprecated
    public static FileInputStream openFile(String str) throws IOException, PermissionException {
        Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
        return openFile(Form.getActiveForm(), str);
    }

    public static FileInputStream openFile(Form form, String str) throws IOException {
        if (needsReadPermission(form, str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) ? "file://".concat(String.valueOf(str)) : str)) {
            form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        }
        return new FileInputStream(str);
    }

    @Deprecated
    public static FileInputStream openFile(File file) throws IOException, PermissionException {
        Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
        return openFile(Form.getActiveForm(), file.getAbsolutePath());
    }

    public static FileInputStream openFile(Form form, File file) throws IOException, PermissionException {
        return openFile(form, file.getAbsolutePath());
    }

    @Deprecated
    public static FileInputStream openFile(URI uri) throws IOException, PermissionException {
        Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
        return openFile(Form.getActiveForm(), uri);
    }

    public static FileInputStream openFile(Form form, URI uri) throws IOException, PermissionException {
        if (MediaUtil.isExternalFileUrl(form, uri.toString())) {
            form.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        }
        return new FileInputStream(new File(uri));
    }

    public static String downloadUrlToFile(String str, String str2) throws IOException {
        InputStream openStream = new URL(str).openStream();
        try {
            return writeStreamToFile(openStream, str2);
        } finally {
            openStream.close();
        }
    }

    public static String writeFile(byte[] bArr, String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            return writeStreamToFile(byteArrayInputStream, str);
        } finally {
            byteArrayInputStream.close();
        }
    }

    public static String copyFile(String str, String str2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            return writeStreamToFile(fileInputStream, str2);
        } finally {
            fileInputStream.close();
        }
    }

    public static boolean copyFile(Form form, ScopedFile scopedFile, ScopedFile scopedFile2) throws IOException {
        Closeable closeable;
        if (Build.VERSION.SDK_INT < 24 || scopedFile.getScope() == FileScope.Shared || scopedFile2.getScope() == FileScope.Shared) {
            InputStream inputStream = null;
            try {
                InputStream openForReading = openForReading(form, scopedFile);
                try {
                    OutputStream openForWriting = openForWriting(form, scopedFile);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = openForReading.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        openForWriting.write(bArr, 0, read);
                    }
                    String str = LOG_TAG;
                    IOUtils.closeQuietly(str, openForReading);
                    IOUtils.closeQuietly(str, openForWriting);
                } catch (Throwable th) {
                    th = th;
                    closeable = null;
                    inputStream = openForReading;
                    String str2 = LOG_TAG;
                    IOUtils.closeQuietly(str2, inputStream);
                    IOUtils.closeQuietly(str2, closeable);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = null;
                String str22 = LOG_TAG;
                IOUtils.closeQuietly(str22, inputStream);
                IOUtils.closeQuietly(str22, closeable);
                throw th;
            }
        } else {
            Files.copy(Paths.get(scopedFile.resolve(form).toURI()), Paths.get(scopedFile2.resolve(form).toURI()), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
        }
        return true;
    }

    public static String writeStreamToFile(InputStream inputStream, String str) throws IOException {
        File file = new File(str);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            copy(inputStream, fileOutputStream);
            return file.toURI().toString();
        } finally {
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 4096);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 4096);
        while (true) {
            int read = bufferedInputStream.read();
            if (read != -1) {
                bufferedOutputStream.write(read);
            } else {
                bufferedOutputStream.flush();
                return;
            }
        }
    }

    public static File getPictureFile(String str) throws IOException, FileException {
        Log.w(LOG_TAG, "Calling deprecated function getPictureFile", new IllegalAccessException());
        return getPictureFile(Form.getActiveForm(), str);
    }

    public static File getPictureFile(Form form, String str) throws IOException, FileException {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, "Pictures", str);
    }

    @Deprecated
    public static File getRecordingFile(String str) throws IOException, FileException {
        return getRecordingFile(Form.getActiveForm(), str);
    }

    public static File getRecordingFile(Form form, String str) throws IOException, FileException {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, "Recordings", str);
    }

    @Deprecated
    public static File getDownloadFile(String str) throws IOException, FileException {
        Log.w(LOG_TAG, "Calling deprecated function getDownloadFile", new IllegalAccessException());
        return getDownloadFile(Form.getActiveForm(), str);
    }

    public static File getDownloadFile(Form form, String str) throws IOException, FileException {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, "Downloads", str);
    }

    private static File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str, String str2) throws IOException, FileException {
        File externalFile = getExternalFile(form, "My Documents/" + str + "/kodular_" + System.currentTimeMillis() + "." + str2);
        File parentFile = externalFile.getParentFile();
        if (parentFile.exists() || parentFile.mkdirs()) {
            return externalFile;
        }
        throw new IOException("Unable to create directory: " + parentFile.getAbsolutePath());
    }

    @Deprecated
    public static File getExternalFile(String str) throws IOException, FileException, SecurityException {
        return getExternalFile(Form.getActiveForm(), str);
    }

    public static File getExternalFile(Form form, String str) throws FileException {
        if (form.DefaultFileScope() == FileScope.Legacy && !str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str = InternalZipConstants.ZIP_FILE_SEPARATOR.concat(String.valueOf(str));
        }
        String resolveFileName = resolveFileName(form, str, form.DefaultFileScope());
        if (isExternalStorageUri(form, resolveFileName)) {
            checkExternalStorageWriteable();
        }
        if (needsPermission(form, resolveFileName)) {
            form.assertPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        return new File(URI.create(resolveFileName));
    }

    public static File getExternalFile(Form form, String str, boolean z, boolean z2) throws IOException, FileException {
        File externalFile = getExternalFile(form, str);
        File parentFile = externalFile.getParentFile();
        if (z && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IOException("Unable to create directory " + parentFile.getAbsolutePath());
        } else if (!z2 || !externalFile.exists() || externalFile.delete()) {
            return externalFile;
        } else {
            throw new IOException("Cannot overwrite existing file " + externalFile.getAbsolutePath());
        }
    }

    public static File getExternalFile(Form form, String str, FileScope fileScope) throws FileException, PermissionException {
        return new File(URI.create(resolveFileName(form, str, fileScope)));
    }

    public static File getExternalFile(Form form, String str, FileScope fileScope, FileAccessMode fileAccessMode, boolean z) throws IOException, FileException, PermissionException {
        File externalFile = getExternalFile(form, str, fileScope);
        if (z && fileAccessMode != FileAccessMode.READ) {
            File parentFile = externalFile.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("Unable to create directory " + parentFile.getAbsolutePath());
            }
        }
        return externalFile;
    }

    public static void checkExternalStorageWriteable() throws FileException {
        String externalStorageState = Environment.getExternalStorageState();
        if (!"mounted".equals(externalStorageState)) {
            if ("mounted_ro".equals(externalStorageState)) {
                throw new FileException(ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_READONLY);
            }
            throw new FileException(ErrorMessages.ERROR_MEDIA_EXTERNAL_STORAGE_NOT_AVAILABLE);
        }
    }

    public static String resolveFileName(Form form, String str, FileScope fileScope) {
        File file;
        if (str.startsWith("//")) {
            file = new File(form.getAssetPath(str.substring(2)).substring(7));
        } else if (str.startsWith("content:")) {
            return str;
        } else {
            if (fileScope == FileScope.App) {
                file = new File(form.getExternalFilesDir(""), str);
            } else if (fileScope == FileScope.Asset) {
                file = new File(form.getAssetPath(str).substring(7));
            } else if (fileScope == FileScope.Cache) {
                file = new File(form.getCachePath(str).substring(7));
            } else if ((fileScope == FileScope.Legacy || fileScope == null) && str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                file = new File(QUtil.getExternalStorageDir(form, false, true), str.substring(1));
            } else if (fileScope == FileScope.Private) {
                file = new File(form.getPrivatePath(str).substring(7));
            } else if (fileScope == FileScope.Shared) {
                file = new File(Environment.getExternalStorageDirectory() + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
            } else if (!str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                file = new File(form.getPrivatePath(str).substring(7));
            } else {
                file = getExternalFile(form, str.substring(1), fileScope);
            }
        }
        return Uri.fromFile(file).toString();
    }

    public static String resolveFileName(Form form, ScopedFile scopedFile) {
        return resolveFileName(form, scopedFile.getFileName(), scopedFile.getScope());
    }

    public static boolean needsPermission(Form form, String str) {
        if (!isAssetUri(form, str) && !isPrivateUri(form, str) && !isAppSpecificExternalUri(form, str)) {
            return isExternalStorageUri(form, str);
        }
        return false;
    }

    public static boolean needsReadPermission(Form form, String str) {
        return needsPermission(form, str);
    }

    /* renamed from: com.google.appinventor.components.runtime.util.FileUtil$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.appinventor.components.common.FileScope[] r0 = com.google.appinventor.components.common.FileScope.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r0
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Legacy     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Shared     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Asset     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.App     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Cache     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.appinventor.components.common.FileScope r1 = com.google.appinventor.components.common.FileScope.Private     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.FileUtil.AnonymousClass1.<clinit>():void");
        }
    }

    public static boolean needsReadPermission(ScopedFile scopedFile) {
        int i = AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[scopedFile.getScope().ordinal()];
        return i == 1 || i == 2;
    }

    public static boolean needsWritePermission(Form form, String str) {
        if (Build.VERSION.SDK_INT >= 30) {
            return false;
        }
        return needsPermission(form, str);
    }

    public static boolean needsWritePermission(ScopedFile scopedFile) {
        return needsWritePermission(scopedFile.getScope());
    }

    public static boolean needsWritePermission(FileScope fileScope) {
        int i = AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[fileScope.ordinal()];
        return (i == 1 || i == 2) && Build.VERSION.SDK_INT < 30;
    }

    public static boolean needsExternalStorage(Form form, ScopedFile scopedFile) {
        return isExternalStorageUri(form, resolveFileName(form, scopedFile.getFileName(), scopedFile.getScope()));
    }

    public static boolean isAssetUri(Form form, String str) {
        return str.startsWith(form.getAssetPath(""));
    }

    public static boolean isPrivateUri(Form form, String str) {
        return str.startsWith(form.getPrivatePath(""));
    }

    public static boolean isAppSpecificExternalUri(Form form, String str) {
        return str.startsWith("file://" + form.getExternalFilesDir("").getAbsolutePath());
    }

    public static boolean isExternalStorageUri(Form form, String str) {
        if (!str.startsWith("file:///sdcard/") && !str.startsWith("file:///storage")) {
            if (!str.startsWith("file://" + Environment.getExternalStorageDirectory().getAbsolutePath())) {
                if (str.startsWith("file://" + form.getExternalFilesDir("").getAbsolutePath())) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public static class FileException extends RuntimeError {
        private final int CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;

        public FileException(int i) {
            this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = i;
        }

        public int getErrorMessageNumber() {
            return this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
        }
    }

    public static String getNeededPermission(Form form, String str, FileAccessMode fileAccessMode) {
        if (str != null) {
            if (str.startsWith("file:") || str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    str = "file://".concat(String.valueOf(str));
                }
                if (!isExternalStorageUri(form, str) || (isAppSpecificExternalUri(form, str) && Build.VERSION.SDK_INT >= 19)) {
                    return null;
                }
                if (fileAccessMode == FileAccessMode.READ) {
                    return "android.permission.READ_EXTERNAL_STORAGE";
                }
                if (Build.VERSION.SDK_INT < 30) {
                    return "android.permission.WRITE_EXTERNAL_STORAGE";
                }
            } else if (!str.contains(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) {
                throw new IllegalArgumentException("path cannot be relative");
            }
            return null;
        }
        throw new NullPointerException("path cannot be null");
    }

    public static boolean moveFile(Form form, ScopedFile scopedFile, ScopedFile scopedFile2) throws IOException {
        Closeable closeable;
        if (Build.VERSION.SDK_INT < 26 || scopedFile.getScope() == FileScope.Shared || scopedFile2.getScope() == FileScope.Shared) {
            byte[] bArr = new byte[4096];
            InputStream inputStream = null;
            try {
                InputStream openForReading = openForReading(form, scopedFile);
                try {
                    OutputStream openForWriting = openForWriting(form, scopedFile2);
                    while (true) {
                        int read = openForReading.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        openForWriting.write(bArr, 0, read);
                    }
                    String str = LOG_TAG;
                    IOUtils.closeQuietly(str, openForReading);
                    IOUtils.closeQuietly(str, openForWriting);
                    File resolve = scopedFile.resolve(form);
                    File resolve2 = scopedFile2.resolve(form);
                    if (resolve.delete()) {
                        return true;
                    }
                    if (resolve2.delete()) {
                        return false;
                    }
                    throw new IOException("Unable to delete fresh file");
                } catch (Throwable th) {
                    th = th;
                    closeable = null;
                    inputStream = openForReading;
                    String str2 = LOG_TAG;
                    IOUtils.closeQuietly(str2, inputStream);
                    IOUtils.closeQuietly(str2, closeable);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = null;
                String str22 = LOG_TAG;
                IOUtils.closeQuietly(str22, inputStream);
                IOUtils.closeQuietly(str22, closeable);
                throw th;
            }
        } else {
            Files.move(Paths.get(scopedFile.resolve(form).toURI()), Paths.get(scopedFile2.resolve(form).toURI()), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            return true;
        }
    }

    public static boolean removeDirectory(File file, boolean z) throws IOException {
        boolean z2;
        file.getClass();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return file.delete();
            }
            if (!z && listFiles.length > 0) {
                return false;
            }
            boolean z3 = true;
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    z2 = removeDirectory(file, z);
                } else {
                    z2 = file2.delete();
                }
                z3 &= z2;
            }
            if (!z3 || !file.delete()) {
                return false;
            }
            return true;
        }
        throw new IllegalArgumentException();
    }

    public static InputStream openForReading(Form form, ScopedFile scopedFile) throws IOException {
        Uri uri;
        switch (AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[scopedFile.getScope().ordinal()]) {
            case 1:
                return new FileInputStream(new File(Environment.getExternalStorageDirectory(), scopedFile.getFileName()));
            case 2:
                if (scopedFile.getFileName().startsWith("content:")) {
                    uri = Uri.parse(scopedFile.getFileName());
                } else {
                    String[] split = scopedFile.getFileName().split(InternalZipConstants.ZIP_FILE_SEPARATOR, 2);
                    Uri hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(split[0]);
                    String[] strArr = {"_id", "_display_name"};
                    Cursor cursor = null;
                    try {
                        cursor = form.getContentResolver().query(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, strArr, "_display_name = ?", new String[]{split[1]}, (String) null);
                        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_id");
                        if (cursor.moveToFirst()) {
                            uri = ContentUris.withAppendedId(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, cursor.getLong(columnIndexOrThrow));
                        } else {
                            throw new FileNotFoundException("Unable to find shared file: " + scopedFile.getFileName());
                        }
                    } finally {
                        IOUtils.closeQuietly(LOG_TAG, cursor);
                    }
                }
                return form.getContentResolver().openInputStream(uri);
            case 3:
                return form.openAsset(scopedFile.getFileName());
            case 4:
                return new FileInputStream(new File(form.getExternalFilesDir(""), scopedFile.getFileName()));
            case 5:
                return new FileInputStream(new File(URI.create(form.getCachePath(scopedFile.getFileName()))));
            case 6:
                return new FileInputStream(new File(URI.create(form.getPrivatePath(scopedFile.getFileName()))));
            default:
                throw new IOException("Unsupported file scope: " + scopedFile.getScope());
        }
    }

    public static OutputStream openForWriting(Form form, ScopedFile scopedFile) throws IOException {
        switch (AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[scopedFile.getScope().ordinal()]) {
            case 1:
                return new FileOutputStream(new File(Environment.getExternalStorageDirectory(), scopedFile.getFileName()));
            case 2:
                ContentResolver contentResolver = form.getContentResolver();
                if (scopedFile.getFileName().startsWith("content:")) {
                    String str = LOG_TAG;
                    Log.d(str, "Opening content URI: " + scopedFile.getFileName());
                    return contentResolver.openOutputStream(Uri.parse(scopedFile.getFileName()));
                }
                String[] split = scopedFile.getFileName().split(InternalZipConstants.ZIP_FILE_SEPARATOR, 2);
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", split[1]);
                contentValues.put("mime_type", "");
                contentValues.put("relative_path", split[0]);
                Uri hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(split[0]);
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                    Uri insert = contentResolver.insert(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, contentValues);
                    if (insert != null) {
                        OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                        if (openOutputStream != null) {
                            return openOutputStream;
                        }
                        throw new IOException("Unable to open stream for writing");
                    }
                    throw new IOException("Unable to insert MediaStore entry for shared content");
                }
                throw new IOException("Unrecognized shared folder: " + split[0]);
            case 3:
                throw new IOException("Assets are read-only.");
            case 4:
                return new FileOutputStream(new File(form.getExternalFilesDir(""), scopedFile.getFileName()));
            case 5:
                return new FileOutputStream(new File(URI.create(form.getCachePath(scopedFile.getFileName()))));
            case 6:
                return new FileOutputStream(new File(URI.create(form.getPrivatePath(scopedFile.getFileName()))));
            default:
                throw new IOException("Unsupported file scope: " + scopedFile.getScope());
        }
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.Closeable, java.util.List<java.lang.String>] */
    public static List<String> listDirectory(Form form, ScopedFile scopedFile) throws IOException {
        String str;
        Cursor cursor = 0;
        switch (AnonymousClass1.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB[scopedFile.getScope().ordinal()]) {
            case 1:
            case 4:
            case 5:
            case 6:
                break;
            case 2:
                String fileName = scopedFile.getFileName();
                if (fileName.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    fileName = fileName.substring(1);
                }
                String[] split = fileName.split(InternalZipConstants.ZIP_FILE_SEPARATOR, 2);
                if (!fileName.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    fileName = fileName + InternalZipConstants.ZIP_FILE_SEPARATOR;
                }
                ContentResolver contentResolver = form.getContentResolver();
                Uri hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(split[0]);
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MediaStore.Files.getContentUri("external");
                }
                Uri uri = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                try {
                    String str2 = Build.VERSION.SDK_INT < 29 ? "_data" : "relative_path";
                    cursor = contentResolver.query(uri, new String[]{"_display_name", str2}, (String) null, (String[]) null, (String) null);
                    int columnIndex = cursor.getColumnIndex("_display_name");
                    int columnIndex2 = cursor.getColumnIndex(str2);
                    ArrayList arrayList = new ArrayList();
                    String str3 = QUtil.getExternalStoragePath(form, false, true) + InternalZipConstants.ZIP_FILE_SEPARATOR;
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(columnIndex);
                        String string2 = cursor.getString(columnIndex2);
                        if (Build.VERSION.SDK_INT < 29) {
                            str = string2.replace(str3, "");
                        } else {
                            str = string2 + string;
                        }
                        if (str.startsWith(fileName)) {
                            arrayList.add(str.substring(fileName.length()));
                        }
                    }
                    return arrayList;
                } finally {
                    IOUtils.closeQuietly(LOG_TAG, cursor);
                }
            case 3:
                if (!form.isRepl()) {
                    String[] list = form.getAssets().list(scopedFile.getFileName());
                    if (list != null) {
                        return Arrays.asList(list);
                    }
                    return Collections.emptyList();
                }
                break;
            default:
                throw new IOException("Unsupported file scope: " + scopedFile.getScope());
        }
        String[] list2 = new File(URI.create(resolveFileName(form, scopedFile.getFileName(), scopedFile.getScope()))).list();
        return list2 != null ? Arrays.asList(list2) : cursor;
    }

    private static Uri hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        if ("DCIM".equals(str) || "Pictures".equals(str) || "Screenshots".equals(str)) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Images.Media.getContentUri("external");
            }
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("Videos".equals(str) || "Movies".equals(str)) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Video.Media.getContentUri("external");
            }
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("Audio".equals(str) || "Music".equals(str)) {
            if (Build.VERSION.SDK_INT >= 29) {
                return MediaStore.Audio.Media.getContentUri("external");
            }
            return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        } else if (Build.VERSION.SDK_INT < 30 || (!"Download".equals(str) && !"Downloads".equals(str))) {
            return MediaStore.Files.getContentUri("external");
        } else {
            return MediaStore.Downloads.getContentUri("external");
        }
    }

    public static ScopedFile getScopedPictureFile(Form form, String str) {
        FileScope DefaultFileScope = form.DefaultFileScope();
        String str2 = "Pictures";
        if (DefaultFileScope == FileScope.Legacy) {
            str2 = "/My Documents/".concat(str2);
        } else if (DefaultFileScope == FileScope.Asset) {
            DefaultFileScope = FileScope.Private;
        }
        return new ScopedFile(DefaultFileScope, str2 + "/kodular_" + System.currentTimeMillis() + "." + str);
    }
}
