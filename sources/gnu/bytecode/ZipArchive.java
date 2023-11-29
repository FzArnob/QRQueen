package gnu.bytecode;

import com.microsoft.appcenter.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;

public class ZipArchive {
    private static void usage() {
        System.err.println("zipfile [ptxq] archive [file ...]");
        System.exit(-1);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static void copy(InputStream inputStream, String str, byte[] bArr) throws IOException {
        File file = new File(str);
        String parent = file.getParent();
        if (parent != null) {
            File file2 = new File(parent);
            if (!file2.exists()) {
                PrintStream printStream = System.err;
                printStream.println("mkdirs:" + file2.mkdirs());
            }
        }
        if (str.charAt(str.length() - 1) != '/') {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            copy(inputStream, (OutputStream) bufferedOutputStream, bArr);
            bufferedOutputStream.close();
        }
    }

    public static void main(String[] strArr) throws IOException {
        int i = 2;
        if (strArr.length < 2) {
            usage();
        }
        String str = strArr[0];
        String str2 = strArr[1];
        try {
            if (!str.equals(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT)) {
                if (!str.equals(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME)) {
                    if (!str.equals(EllipticCurveJsonWebKey.X_MEMBER_NAME)) {
                        if (str.equals(RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME)) {
                            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
                            while (i < strArr.length) {
                                File file = new File(strArr[i]);
                                if (!file.exists()) {
                                    throw new IOException(strArr[i] + " - not found");
                                } else if (file.canRead()) {
                                    int length = (int) file.length();
                                    FileInputStream fileInputStream = new FileInputStream(file);
                                    byte[] bArr = new byte[length];
                                    if (fileInputStream.read(bArr) == length) {
                                        fileInputStream.close();
                                        ZipEntry zipEntry = new ZipEntry(strArr[i]);
                                        zipEntry.setSize((long) length);
                                        zipEntry.setTime(file.lastModified());
                                        zipOutputStream.putNextEntry(zipEntry);
                                        zipOutputStream.write(bArr, 0, length);
                                        i++;
                                    } else {
                                        throw new IOException(strArr[i] + " - read error");
                                    }
                                } else {
                                    throw new IOException(strArr[i] + " - not readable");
                                }
                            }
                            zipOutputStream.close();
                            return;
                        }
                        usage();
                        return;
                    }
                }
            }
            PrintStream printStream = System.out;
            byte[] bArr2 = new byte[1024];
            if (strArr.length == 2) {
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str2)));
                while (true) {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry != null) {
                        String name = nextEntry.getName();
                        if (str.equals(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT)) {
                            printStream.print(name);
                            printStream.print(" size: ");
                            printStream.println(nextEntry.getSize());
                        } else if (str.equals(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME)) {
                            copy((InputStream) zipInputStream, (OutputStream) printStream, bArr2);
                        } else {
                            copy((InputStream) zipInputStream, name, bArr2);
                        }
                    } else {
                        return;
                    }
                }
            } else {
                ZipFile zipFile = new ZipFile(str2);
                while (i < strArr.length) {
                    String str3 = strArr[i];
                    ZipEntry entry = zipFile.getEntry(str3);
                    if (entry == null) {
                        PrintStream printStream2 = System.err;
                        printStream2.println("zipfile " + str2 + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + strArr[i] + " - not found");
                        System.exit(-1);
                    } else if (str.equals(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT)) {
                        printStream.print(str3);
                        printStream.print(" size: ");
                        printStream.println(entry.getSize());
                    } else if (str.equals(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME)) {
                        copy(zipFile.getInputStream(entry), (OutputStream) printStream, bArr2);
                    } else {
                        copy(zipFile.getInputStream(entry), str3, bArr2);
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            PrintStream printStream3 = System.err;
            printStream3.println("I/O Exception:  " + e);
        }
    }
}
