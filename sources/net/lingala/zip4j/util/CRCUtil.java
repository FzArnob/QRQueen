package net.lingala.zip4j.util;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

public class CRCUtil {
    private static final int BUF_SIZE = 16384;

    public static long computeFileCRC(String str) throws ZipException {
        return computeFileCRC(str, (ProgressMonitor) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0072 A[SYNTHETIC, Splitter:B:45:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long computeFileCRC(java.lang.String r7, net.lingala.zip4j.progress.ProgressMonitor r8) throws net.lingala.zip4j.exception.ZipException {
        /*
            java.lang.String r0 = "error while closing the file after calculating crc"
            boolean r1 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r7)
            if (r1 == 0) goto L_0x007d
            r1 = 0
            net.lingala.zip4j.util.Zip4jUtil.checkFileReadAccess(r7)     // Catch:{ IOException -> 0x0069, Exception -> 0x0062 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0069, Exception -> 0x0062 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0069, Exception -> 0x0062 }
            r3.<init>(r7)     // Catch:{ IOException -> 0x0069, Exception -> 0x0062 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0069, Exception -> 0x0062 }
            r7 = 16384(0x4000, float:2.2959E-41)
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            java.util.zip.CRC32 r1 = new java.util.zip.CRC32     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r1.<init>()     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
        L_0x001f:
            int r3 = r2.read(r7)     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r4 = -1
            if (r3 != r4) goto L_0x0034
            long r7 = r1.getValue()     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r2.close()     // Catch:{ IOException -> 0x002e }
            return r7
        L_0x002e:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException
            r7.<init>((java.lang.String) r0)
            throw r7
        L_0x0034:
            r4 = 0
            r1.update(r7, r4, r3)     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            if (r8 == 0) goto L_0x001f
            long r5 = (long) r3     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r8.updateWorkCompleted(r5)     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            boolean r3 = r8.isCancelAllTasks()     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            if (r3 == 0) goto L_0x001f
            r7 = 3
            r8.setResult(r7)     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r8.setState(r4)     // Catch:{ IOException -> 0x005d, Exception -> 0x005a, all -> 0x0057 }
            r2.close()     // Catch:{ IOException -> 0x0051 }
            r7 = 0
            return r7
        L_0x0051:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException
            r7.<init>((java.lang.String) r0)
            throw r7
        L_0x0057:
            r7 = move-exception
            r1 = r2
            goto L_0x0070
        L_0x005a:
            r7 = move-exception
            r1 = r2
            goto L_0x0063
        L_0x005d:
            r7 = move-exception
            r1 = r2
            goto L_0x006a
        L_0x0060:
            r7 = move-exception
            goto L_0x0070
        L_0x0062:
            r7 = move-exception
        L_0x0063:
            net.lingala.zip4j.exception.ZipException r8 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0060 }
            r8.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0060 }
            throw r8     // Catch:{ all -> 0x0060 }
        L_0x0069:
            r7 = move-exception
        L_0x006a:
            net.lingala.zip4j.exception.ZipException r8 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0060 }
            r8.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0060 }
            throw r8     // Catch:{ all -> 0x0060 }
        L_0x0070:
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007c
        L_0x0076:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException
            r7.<init>((java.lang.String) r0)
            throw r7
        L_0x007c:
            throw r7
        L_0x007d:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r8 = "input file is null or empty, cannot calculate CRC for the file"
            r7.<init>((java.lang.String) r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.CRCUtil.computeFileCRC(java.lang.String, net.lingala.zip4j.progress.ProgressMonitor):long");
    }
}
