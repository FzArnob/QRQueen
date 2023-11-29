package org.jose4j.zip;

import org.jose4j.keys.KeyPersuasion;

public class DeflateRFC1951CompressionAlgorithm implements CompressionAlgorithm {
    public String getAlgorithmIdentifier() {
        return CompressionAlgorithmIdentifiers.DEFLATE;
    }

    public String getJavaAlgorithm() {
        return null;
    }

    public String getKeyType() {
        return null;
    }

    public boolean isAvailable() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r5.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0031, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0034, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003d, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] compress(byte[] r5) {
        /*
            r4 = this;
            java.util.zip.Deflater r0 = new java.util.zip.Deflater
            r1 = 8
            r2 = 1
            r0.<init>(r1, r2)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0040 }
            r1.<init>()     // Catch:{ IOException -> 0x0040 }
            java.util.zip.DeflaterOutputStream r2 = new java.util.zip.DeflaterOutputStream     // Catch:{ all -> 0x0032 }
            r2.<init>(r1, r0)     // Catch:{ all -> 0x0032 }
            r2.write(r5)     // Catch:{ all -> 0x0026 }
            r2.finish()     // Catch:{ all -> 0x0026 }
            byte[] r5 = r1.toByteArray()     // Catch:{ all -> 0x0026 }
            r2.close()     // Catch:{ all -> 0x0032 }
            r1.close()     // Catch:{ IOException -> 0x0040 }
            r0.end()
            return r5
        L_0x0026:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r2 = move-exception
            r5.addSuppressed(r2)     // Catch:{ all -> 0x0032 }
        L_0x0031:
            throw r3     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ IOException -> 0x0040 }
        L_0x003d:
            throw r2     // Catch:{ IOException -> 0x0040 }
        L_0x003e:
            r5 = move-exception
            goto L_0x0049
        L_0x0040:
            r5 = move-exception
            org.jose4j.lang.UncheckedJoseException r1 = new org.jose4j.lang.UncheckedJoseException     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "Problem compressing data."
            r1.<init>(r2, r5)     // Catch:{ all -> 0x003e }
            throw r1     // Catch:{ all -> 0x003e }
        L_0x0049:
            r0.end()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.zip.DeflateRFC1951CompressionAlgorithm.compress(byte[]):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] decompress(byte[] r6) throws org.jose4j.lang.JoseException {
        /*
            r5 = this;
            java.util.zip.Inflater r0 = new java.util.zip.Inflater
            r1 = 1
            r0.<init>(r1)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.util.zip.InflaterInputStream r2 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x003e }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x003e }
            r3.<init>(r6)     // Catch:{ IOException -> 0x003e }
            r2.<init>(r3, r0)     // Catch:{ IOException -> 0x003e }
            r6 = 256(0x100, float:3.59E-43)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x0030 }
        L_0x0019:
            int r3 = r2.read(r6)     // Catch:{ all -> 0x0030 }
            r4 = -1
            if (r3 == r4) goto L_0x0025
            r4 = 0
            r1.write(r6, r4, r3)     // Catch:{ all -> 0x0030 }
            goto L_0x0019
        L_0x0025:
            byte[] r6 = r1.toByteArray()     // Catch:{ all -> 0x0030 }
            r2.close()     // Catch:{ IOException -> 0x003e }
            r0.end()
            return r6
        L_0x0030:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r1 = move-exception
            r2.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r6.addSuppressed(r2)     // Catch:{ IOException -> 0x003e }
        L_0x003b:
            throw r1     // Catch:{ IOException -> 0x003e }
        L_0x003c:
            r6 = move-exception
            goto L_0x0047
        L_0x003e:
            r6 = move-exception
            org.jose4j.lang.JoseException r1 = new org.jose4j.lang.JoseException     // Catch:{ all -> 0x003c }
            java.lang.String r2 = "Problem decompressing data."
            r1.<init>(r2, r6)     // Catch:{ all -> 0x003c }
            throw r1     // Catch:{ all -> 0x003c }
        L_0x0047:
            r0.end()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jose4j.zip.DeflateRFC1951CompressionAlgorithm.decompress(byte[]):byte[]");
    }

    public KeyPersuasion getKeyPersuasion() {
        return KeyPersuasion.NONE;
    }
}
