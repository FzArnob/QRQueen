package net.lingala.zip4j.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;

public class Zip4jUtil {
    public static void setFileArchive(File file) throws ZipException {
    }

    public static void setFileHidden(File file) throws ZipException {
    }

    public static void setFileSystemMode(File file) throws ZipException {
    }

    public static boolean isStringNotNullAndNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean checkOutputFolder(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            File file = new File(str);
            if (!file.exists()) {
                try {
                    file.mkdirs();
                    if (!file.isDirectory()) {
                        throw new ZipException("output folder is not valid");
                    } else if (file.canWrite()) {
                        return true;
                    } else {
                        throw new ZipException("no write access to destination folder");
                    }
                } catch (Exception unused) {
                    throw new ZipException("Cannot create destination folder");
                }
            } else if (!file.isDirectory()) {
                throw new ZipException("output folder is not valid");
            } else if (file.canWrite()) {
                return true;
            } else {
                throw new ZipException("no write access to output folder");
            }
        } else {
            throw new ZipException((Throwable) new NullPointerException("output path is null"));
        }
    }

    public static boolean checkFileReadAccess(String str) throws ZipException {
        if (!isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("path is null");
        } else if (checkFileExists(str)) {
            try {
                return new File(str).canRead();
            } catch (Exception unused) {
                throw new ZipException("cannot read zip file");
            }
        } else {
            throw new ZipException(new StringBuffer("file does not exist: ").append(str).toString());
        }
    }

    public static boolean checkFileWriteAccess(String str) throws ZipException {
        if (!isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("path is null");
        } else if (checkFileExists(str)) {
            try {
                return new File(str).canWrite();
            } catch (Exception unused) {
                throw new ZipException("cannot read zip file");
            }
        } else {
            throw new ZipException(new StringBuffer("file does not exist: ").append(str).toString());
        }
    }

    public static boolean checkFileExists(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            return checkFileExists(new File(str));
        }
        throw new ZipException("path is null");
    }

    public static boolean checkFileExists(File file) throws ZipException {
        if (file != null) {
            return file.exists();
        }
        throw new ZipException("cannot check if file exists: input file is null");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().indexOf("win") >= 0;
    }

    public static void setFileReadOnly(File file) throws ZipException {
        if (file == null) {
            throw new ZipException("input file is null. cannot set read only file attribute");
        } else if (file.exists()) {
            file.setReadOnly();
        }
    }

    public static long getLastModifiedFileTime(File file, TimeZone timeZone) throws ZipException {
        if (file == null) {
            throw new ZipException("input file is null, cannot read last modified file time");
        } else if (file.exists()) {
            return file.lastModified();
        } else {
            throw new ZipException("input file does not exist, cannot read last modified file time");
        }
    }

    public static String getFileNameFromFilePath(File file) throws ZipException {
        if (file == null) {
            throw new ZipException("input file is null, cannot get file name");
        } else if (file.isDirectory()) {
            return null;
        } else {
            return file.getName();
        }
    }

    public static long getFileLengh(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            return getFileLengh(new File(str));
        }
        throw new ZipException("invalid file name");
    }

    public static long getFileLengh(File file) throws ZipException {
        if (file == null) {
            throw new ZipException("input file is null, cannot calculate file length");
        } else if (file.isDirectory()) {
            return -1;
        } else {
            return file.length();
        }
    }

    public static long javaToDosTime(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(1);
        if (i < 1980) {
            return 2162688;
        }
        return (long) ((instance.get(13) >> 1) | ((i - 1980) << 25) | ((instance.get(2) + 1) << 21) | (instance.get(5) << 16) | (instance.get(11) << 11) | (instance.get(12) << 5));
    }

    public static long dosToJavaTme(int i) {
        int i2 = (i & 31) * 2;
        int i3 = (i >> 5) & 63;
        int i4 = (i >> 11) & 31;
        int i5 = (i >> 16) & 31;
        int i6 = ((i >> 25) & 127) + 1980;
        Calendar instance = Calendar.getInstance();
        instance.set(i6, ((i >> 21) & 15) - 1, i5, i4, i3, i2);
        instance.set(14, 0);
        return instance.getTime().getTime();
    }

    public static FileHeader getFileHeader(ZipModel zipModel, String str) throws ZipException {
        if (zipModel == null) {
            throw new ZipException(new StringBuffer("zip model is null, cannot determine file header for fileName: ").append(str).toString());
        } else if (isStringNotNullAndNotEmpty(str)) {
            FileHeader fileHeaderWithExactMatch = getFileHeaderWithExactMatch(zipModel, str);
            if (fileHeaderWithExactMatch != null) {
                return fileHeaderWithExactMatch;
            }
            String replaceAll = str.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR);
            FileHeader fileHeaderWithExactMatch2 = getFileHeaderWithExactMatch(zipModel, replaceAll);
            if (fileHeaderWithExactMatch2 == null) {
                return getFileHeaderWithExactMatch(zipModel, replaceAll.replaceAll(InternalZipConstants.ZIP_FILE_SEPARATOR, "\\\\"));
            }
            return fileHeaderWithExactMatch2;
        } else {
            throw new ZipException(new StringBuffer("file name is null, cannot determine file header for fileName: ").append(str).toString());
        }
    }

    public static FileHeader getFileHeaderWithExactMatch(ZipModel zipModel, String str) throws ZipException {
        if (zipModel == null) {
            throw new ZipException(new StringBuffer("zip model is null, cannot determine file header with exact match for fileName: ").append(str).toString());
        } else if (!isStringNotNullAndNotEmpty(str)) {
            throw new ZipException(new StringBuffer("file name is null, cannot determine file header with exact match for fileName: ").append(str).toString());
        } else if (zipModel.getCentralDirectory() == null) {
            throw new ZipException(new StringBuffer("central directory is null, cannot determine file header with exact match for fileName: ").append(str).toString());
        } else if (zipModel.getCentralDirectory().getFileHeaders() == null) {
            throw new ZipException(new StringBuffer("file Headers are null, cannot determine file header with exact match for fileName: ").append(str).toString());
        } else if (zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return null;
        } else {
            ArrayList fileHeaders = zipModel.getCentralDirectory().getFileHeaders();
            for (int i = 0; i < fileHeaders.size(); i++) {
                FileHeader fileHeader = (FileHeader) fileHeaders.get(i);
                String fileName = fileHeader.getFileName();
                if (isStringNotNullAndNotEmpty(fileName) && str.equalsIgnoreCase(fileName)) {
                    return fileHeader;
                }
            }
            return null;
        }
    }

    public static int getIndexOfFileHeader(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        if (zipModel == null || fileHeader == null) {
            throw new ZipException("input parameters is null, cannot determine index of file header");
        } else if (zipModel.getCentralDirectory() == null) {
            throw new ZipException("central directory is null, ccannot determine index of file header");
        } else if (zipModel.getCentralDirectory().getFileHeaders() == null) {
            throw new ZipException("file Headers are null, cannot determine index of file header");
        } else if (zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return -1;
        } else {
            String fileName = fileHeader.getFileName();
            if (isStringNotNullAndNotEmpty(fileName)) {
                ArrayList fileHeaders = zipModel.getCentralDirectory().getFileHeaders();
                for (int i = 0; i < fileHeaders.size(); i++) {
                    String fileName2 = ((FileHeader) fileHeaders.get(i)).getFileName();
                    if (isStringNotNullAndNotEmpty(fileName2) && fileName.equalsIgnoreCase(fileName2)) {
                        return i;
                    }
                }
                return -1;
            }
            throw new ZipException("file name in file header is empty or null, cannot determine index of file header");
        }
    }

    public static ArrayList getFilesInDirectoryRec(File file, boolean z) throws ZipException {
        if (file != null) {
            ArrayList arrayList = new ArrayList();
            List asList = Arrays.asList(file.listFiles());
            if (!file.canRead()) {
                return arrayList;
            }
            for (int i = 0; i < asList.size(); i++) {
                File file2 = (File) asList.get(i);
                if (file2.isHidden() && !z) {
                    return arrayList;
                }
                arrayList.add(file2);
                if (file2.isDirectory()) {
                    arrayList.addAll(getFilesInDirectoryRec(file2, z));
                }
            }
            return arrayList;
        }
        throw new ZipException("input path is null, cannot read files in the directory");
    }

    public static String getZipFileNameWithoutExt(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            if (str.indexOf(System.getProperty("file.separator")) >= 0) {
                str = str.substring(str.lastIndexOf(System.getProperty("file.separator")));
            }
            return str.indexOf(".") > 0 ? str.substring(0, str.lastIndexOf(".")) : str;
        }
        throw new ZipException("zip file name is empty or null, cannot determine zip file name");
    }

    public static byte[] convertCharset(String str) throws ZipException {
        try {
            byte[] bArr = null;
            String detectCharSet = detectCharSet(str);
            if (detectCharSet.equals(InternalZipConstants.CHARSET_CP850)) {
                return str.getBytes(InternalZipConstants.CHARSET_CP850);
            }
            if (detectCharSet.equals(InternalZipConstants.CHARSET_UTF8)) {
                return str.getBytes(InternalZipConstants.CHARSET_UTF8);
            }
            return str.getBytes();
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        } catch (Exception e) {
            throw new ZipException((Throwable) e);
        }
    }

    public static String decodeFileName(byte[] bArr, boolean z) {
        if (!z) {
            return getCp850EncodedString(bArr);
        }
        try {
            return new String(bArr, InternalZipConstants.CHARSET_UTF8);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static String getCp850EncodedString(byte[] bArr) {
        try {
            return new String(bArr, InternalZipConstants.CHARSET_CP850);
        } catch (UnsupportedEncodingException unused) {
            return new String(bArr);
        }
    }

    public static String getAbsoluteFilePath(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            return new File(str).getAbsolutePath();
        }
        throw new ZipException("filePath is null or empty, cannot get absolute file path");
    }

    public static boolean checkArrayListTypes(ArrayList arrayList, int i) throws ZipException {
        if (arrayList == null) {
            throw new ZipException("input arraylist is null, cannot check types");
        } else if (arrayList.size() <= 0) {
            return true;
        } else {
            boolean z = false;
            if (i != 1) {
                if (i == 2) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (!(arrayList.get(i2) instanceof String)) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                return !z;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList.size()) {
                    break;
                } else if (!(arrayList.get(i3) instanceof File)) {
                    break;
                } else {
                    i3++;
                }
            }
            return !z;
            z = true;
            return !z;
        }
    }

    public static String detectCharSet(String str) throws ZipException {
        if (str != null) {
            try {
                if (str.equals(new String(str.getBytes(InternalZipConstants.CHARSET_CP850), InternalZipConstants.CHARSET_CP850))) {
                    return InternalZipConstants.CHARSET_CP850;
                }
                if (str.equals(new String(str.getBytes(InternalZipConstants.CHARSET_UTF8), InternalZipConstants.CHARSET_UTF8))) {
                    return InternalZipConstants.CHARSET_UTF8;
                }
                return InternalZipConstants.CHARSET_DEFAULT;
            } catch (UnsupportedEncodingException unused) {
                return InternalZipConstants.CHARSET_DEFAULT;
            } catch (Exception unused2) {
                return InternalZipConstants.CHARSET_DEFAULT;
            }
        } else {
            throw new ZipException("input string is null, cannot detect charset");
        }
    }

    public static int getEncodedStringLength(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            return getEncodedStringLength(str, detectCharSet(str));
        }
        throw new ZipException("input string is null, cannot calculate encoded String length");
    }

    public static int getEncodedStringLength(String str, String str2) throws ZipException {
        ByteBuffer byteBuffer;
        if (!isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("input string is null, cannot calculate encoded String length");
        } else if (isStringNotNullAndNotEmpty(str2)) {
            try {
                if (str2.equals(InternalZipConstants.CHARSET_CP850)) {
                    byteBuffer = ByteBuffer.wrap(str.getBytes(InternalZipConstants.CHARSET_CP850));
                } else if (str2.equals(InternalZipConstants.CHARSET_UTF8)) {
                    byteBuffer = ByteBuffer.wrap(str.getBytes(InternalZipConstants.CHARSET_UTF8));
                } else {
                    byteBuffer = ByteBuffer.wrap(str.getBytes(str2));
                }
            } catch (UnsupportedEncodingException unused) {
                byteBuffer = ByteBuffer.wrap(str.getBytes());
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
            return byteBuffer.limit();
        } else {
            throw new ZipException("encoding is not defined, cannot calculate string length");
        }
    }

    public static boolean isSupportedCharset(String str) throws ZipException {
        if (isStringNotNullAndNotEmpty(str)) {
            try {
                new String("a".getBytes(), str);
                return true;
            } catch (UnsupportedEncodingException unused) {
                return false;
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("charset is null or empty, cannot check if it is supported");
        }
    }

    public static ArrayList getSplitZipFiles(ZipModel zipModel) throws ZipException {
        if (zipModel == null) {
            throw new ZipException("cannot get split zip files: zipmodel is null");
        } else if (zipModel.getEndCentralDirRecord() == null) {
            return null;
        } else {
            ArrayList arrayList = new ArrayList();
            String zipFile = zipModel.getZipFile();
            String name = new File(zipFile).getName();
            if (!isStringNotNullAndNotEmpty(zipFile)) {
                throw new ZipException("cannot get split zip files: zipfile is null");
            } else if (!zipModel.isSplitArchive()) {
                arrayList.add(zipFile);
                return arrayList;
            } else {
                int noOfThisDisk = zipModel.getEndCentralDirRecord().getNoOfThisDisk();
                if (noOfThisDisk == 0) {
                    arrayList.add(zipFile);
                    return arrayList;
                }
                int i = 0;
                while (i <= noOfThisDisk) {
                    if (i == noOfThisDisk) {
                        arrayList.add(zipModel.getZipFile());
                    } else {
                        arrayList.add(new StringBuffer(String.valueOf(name.indexOf(".") >= 0 ? zipFile.substring(0, zipFile.lastIndexOf(".")) : zipFile)).append(i > 9 ? ".z" : ".z0").append(i + 1).toString());
                    }
                    i++;
                }
                return arrayList;
            }
        }
    }

    public static String getRelativeFileName(String str, String str2, String str3) throws ZipException {
        String str4;
        if (isStringNotNullAndNotEmpty(str)) {
            if (isStringNotNullAndNotEmpty(str3)) {
                String path = new File(str3).getPath();
                if (!path.endsWith(InternalZipConstants.FILE_SEPARATOR)) {
                    path = new StringBuffer(String.valueOf(path)).append(InternalZipConstants.FILE_SEPARATOR).toString();
                }
                String substring = str.substring(path.length());
                if (substring.startsWith(System.getProperty("file.separator"))) {
                    substring = substring.substring(1);
                }
                File file = new File(str);
                if (file.isDirectory()) {
                    str4 = new StringBuffer(String.valueOf(substring.replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR))).append(InternalZipConstants.ZIP_FILE_SEPARATOR).toString();
                } else {
                    str4 = new StringBuffer(String.valueOf(substring.substring(0, substring.lastIndexOf(file.getName())).replaceAll("\\\\", InternalZipConstants.ZIP_FILE_SEPARATOR))).append(file.getName()).toString();
                }
            } else {
                File file2 = new File(str);
                if (file2.isDirectory()) {
                    str4 = new StringBuffer(String.valueOf(file2.getName())).append(InternalZipConstants.ZIP_FILE_SEPARATOR).toString();
                } else {
                    str4 = getFileNameFromFilePath(new File(str));
                }
            }
            if (isStringNotNullAndNotEmpty(str2)) {
                str4 = new StringBuffer(String.valueOf(str2)).append(str4).toString();
            }
            if (isStringNotNullAndNotEmpty(str4)) {
                return str4;
            }
            throw new ZipException("Error determining file name");
        }
        throw new ZipException("input file path/name is empty, cannot calculate relative file name");
    }

    public static long[] getAllHeaderSignatures() {
        return new long[]{InternalZipConstants.LOCSIG, 134695760, InternalZipConstants.CENSIG, InternalZipConstants.ENDSIG, InternalZipConstants.DIGSIG, InternalZipConstants.ARCEXTDATREC, 134695760, InternalZipConstants.ZIP64ENDCENDIRLOC, InternalZipConstants.ZIP64ENDCENDIRREC, 1, 39169};
    }
}
