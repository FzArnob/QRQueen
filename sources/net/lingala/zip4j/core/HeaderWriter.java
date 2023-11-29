package net.lingala.zip4j.core;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.SplitOutputStream;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndCentralDirLocator;
import net.lingala.zip4j.model.Zip64EndCentralDirRecord;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class HeaderWriter {
    private final int ZIP64_EXTRA_BUF = 50;

    public int writeLocalFileHeader(ZipModel zipModel, LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException {
        boolean z;
        LocalFileHeader localFileHeader2 = localFileHeader;
        if (localFileHeader2 != null) {
            try {
                ArrayList arrayList = new ArrayList();
                byte[] bArr = new byte[2];
                byte[] bArr2 = new byte[4];
                byte[] bArr3 = new byte[8];
                byte[] bArr4 = new byte[8];
                Raw.writeIntLittleEndian(bArr2, 0, localFileHeader.getSignature());
                copyByteArrayToArrayList(bArr2, arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getVersionNeededToExtract());
                copyByteArrayToArrayList(bArr, arrayList);
                copyByteArrayToArrayList(localFileHeader.getGeneralPurposeFlag(), arrayList);
                Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getCompressionMethod());
                copyByteArrayToArrayList(bArr, arrayList);
                Raw.writeIntLittleEndian(bArr2, 0, localFileHeader.getLastModFileTime());
                copyByteArrayToArrayList(bArr2, arrayList);
                Raw.writeIntLittleEndian(bArr2, 0, (int) localFileHeader.getCrc32());
                copyByteArrayToArrayList(bArr2, arrayList);
                if (localFileHeader.getUncompressedSize() + 50 >= InternalZipConstants.ZIP_64_LIMIT) {
                    Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                    System.arraycopy(bArr3, 0, bArr2, 0, 4);
                    copyByteArrayToArrayList(bArr2, arrayList);
                    copyByteArrayToArrayList(bArr2, arrayList);
                    zipModel.setZip64Format(true);
                    localFileHeader2.setWriteComprSizeInZip64ExtraRecord(true);
                    z = true;
                } else {
                    ZipModel zipModel2 = zipModel;
                    Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getCompressedSize());
                    System.arraycopy(bArr3, 0, bArr2, 0, 4);
                    copyByteArrayToArrayList(bArr2, arrayList);
                    Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getUncompressedSize());
                    System.arraycopy(bArr3, 0, bArr2, 0, 4);
                    copyByteArrayToArrayList(bArr2, arrayList);
                    localFileHeader2.setWriteComprSizeInZip64ExtraRecord(false);
                    z = false;
                }
                Raw.writeShortLittleEndian(bArr, 0, (short) localFileHeader.getFileNameLength());
                copyByteArrayToArrayList(bArr, arrayList);
                int i = z ? 20 : 0;
                if (localFileHeader.getAesExtraDataRecord() != null) {
                    i += 11;
                }
                Raw.writeShortLittleEndian(bArr, 0, (short) i);
                copyByteArrayToArrayList(bArr, arrayList);
                if (Zip4jUtil.isStringNotNullAndNotEmpty(zipModel.getFileNameCharset())) {
                    copyByteArrayToArrayList(localFileHeader.getFileName().getBytes(zipModel.getFileNameCharset()), arrayList);
                } else {
                    copyByteArrayToArrayList(Zip4jUtil.convertCharset(localFileHeader.getFileName()), arrayList);
                }
                if (z) {
                    Raw.writeShortLittleEndian(bArr, 0, 1);
                    copyByteArrayToArrayList(bArr, arrayList);
                    Raw.writeShortLittleEndian(bArr, 0, 16);
                    copyByteArrayToArrayList(bArr, arrayList);
                    Raw.writeLongLittleEndian(bArr3, 0, localFileHeader.getUncompressedSize());
                    copyByteArrayToArrayList(bArr3, arrayList);
                    copyByteArrayToArrayList(bArr4, arrayList);
                }
                if (localFileHeader.getAesExtraDataRecord() != null) {
                    AESExtraDataRecord aesExtraDataRecord = localFileHeader.getAesExtraDataRecord();
                    Raw.writeShortLittleEndian(bArr, 0, (short) ((int) aesExtraDataRecord.getSignature()));
                    copyByteArrayToArrayList(bArr, arrayList);
                    Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getDataSize());
                    copyByteArrayToArrayList(bArr, arrayList);
                    Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getVersionNumber());
                    copyByteArrayToArrayList(bArr, arrayList);
                    copyByteArrayToArrayList(aesExtraDataRecord.getVendorID().getBytes(), arrayList);
                    copyByteArrayToArrayList(new byte[]{(byte) aesExtraDataRecord.getAesStrength()}, arrayList);
                    Raw.writeShortLittleEndian(bArr, 0, (short) aesExtraDataRecord.getCompressionMethod());
                    copyByteArrayToArrayList(bArr, arrayList);
                }
                byte[] byteArrayListToByteArray = byteArrayListToByteArray(arrayList);
                outputStream.write(byteArrayListToByteArray);
                return byteArrayListToByteArray.length;
            } catch (ZipException e) {
                throw e;
            } catch (Exception e2) {
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("input parameters are null, cannot write local file header");
        }
    }

    public int writeExtendedLocalHeader(LocalFileHeader localFileHeader, OutputStream outputStream) throws ZipException, IOException {
        if (localFileHeader == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write extended local header");
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        Raw.writeIntLittleEndian(bArr, 0, 134695760);
        copyByteArrayToArrayList(bArr, arrayList);
        Raw.writeIntLittleEndian(bArr, 0, (int) localFileHeader.getCrc32());
        copyByteArrayToArrayList(bArr, arrayList);
        long compressedSize = localFileHeader.getCompressedSize();
        long j = 2147483647L;
        if (compressedSize >= 2147483647L) {
            compressedSize = 2147483647L;
        }
        Raw.writeIntLittleEndian(bArr, 0, (int) compressedSize);
        copyByteArrayToArrayList(bArr, arrayList);
        long uncompressedSize = localFileHeader.getUncompressedSize();
        if (uncompressedSize < 2147483647L) {
            j = uncompressedSize;
        }
        Raw.writeIntLittleEndian(bArr, 0, (int) j);
        copyByteArrayToArrayList(bArr, arrayList);
        byte[] byteArrayListToByteArray = byteArrayListToByteArray(arrayList);
        outputStream.write(byteArrayListToByteArray);
        return byteArrayListToByteArray.length;
    }

    public void finalizeZipFile(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file");
        }
        try {
            processHeaderData(zipModel, outputStream);
            long offsetOfStartOfCentralDir = zipModel.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
            ArrayList arrayList = new ArrayList();
            int writeCentralDirectory = writeCentralDirectory(zipModel, outputStream, arrayList);
            if (zipModel.isZip64Format()) {
                if (zipModel.getZip64EndCentralDirRecord() == null) {
                    zipModel.setZip64EndCentralDirRecord(new Zip64EndCentralDirRecord());
                }
                if (zipModel.getZip64EndCentralDirLocator() == null) {
                    zipModel.setZip64EndCentralDirLocator(new Zip64EndCentralDirLocator());
                }
                zipModel.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(((long) writeCentralDirectory) + offsetOfStartOfCentralDir);
                if (outputStream instanceof SplitOutputStream) {
                    zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(((SplitOutputStream) outputStream).getCurrSplitFileCounter());
                    zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(((SplitOutputStream) outputStream).getCurrSplitFileCounter() + 1);
                } else {
                    zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(0);
                    zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(1);
                }
                writeZip64EndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir, arrayList);
                writeZip64EndOfCentralDirectoryLocator(zipModel, outputStream, arrayList);
            }
            writeEndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir, arrayList);
            writeZipHeaderBytes(zipModel, outputStream, byteArrayListToByteArray(arrayList));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    public void finalizeZipFileWithoutValidations(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot finalize zip file without validations");
        }
        try {
            ArrayList arrayList = new ArrayList();
            long offsetOfStartOfCentralDir = zipModel.getEndCentralDirRecord().getOffsetOfStartOfCentralDir();
            int writeCentralDirectory = writeCentralDirectory(zipModel, outputStream, arrayList);
            if (zipModel.isZip64Format()) {
                if (zipModel.getZip64EndCentralDirRecord() == null) {
                    zipModel.setZip64EndCentralDirRecord(new Zip64EndCentralDirRecord());
                }
                if (zipModel.getZip64EndCentralDirLocator() == null) {
                    zipModel.setZip64EndCentralDirLocator(new Zip64EndCentralDirLocator());
                }
                zipModel.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(((long) writeCentralDirectory) + offsetOfStartOfCentralDir);
                writeZip64EndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir, arrayList);
                writeZip64EndOfCentralDirectoryLocator(zipModel, outputStream, arrayList);
            }
            writeEndOfCentralDirectoryRecord(zipModel, outputStream, writeCentralDirectory, offsetOfStartOfCentralDir, arrayList);
            writeZipHeaderBytes(zipModel, outputStream, byteArrayListToByteArray(arrayList));
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private void writeZipHeaderBytes(ZipModel zipModel, OutputStream outputStream, byte[] bArr) throws ZipException {
        if (bArr != null) {
            try {
                if (!(outputStream instanceof SplitOutputStream) || !((SplitOutputStream) outputStream).checkBuffSizeAndStartNextSplitFile(bArr.length)) {
                    outputStream.write(bArr);
                } else {
                    finalizeZipFile(zipModel, outputStream);
                }
            } catch (IOException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid buff to write as zip headers");
        }
    }

    private void processHeaderData(ZipModel zipModel, OutputStream outputStream) throws ZipException {
        int i = 0;
        try {
            if (outputStream instanceof SplitOutputStream) {
                zipModel.getEndCentralDirRecord().setOffsetOfStartOfCentralDir(((SplitOutputStream) outputStream).getFilePointer());
                i = ((SplitOutputStream) outputStream).getCurrSplitFileCounter();
            }
            if (zipModel.isZip64Format()) {
                if (zipModel.getZip64EndCentralDirRecord() == null) {
                    zipModel.setZip64EndCentralDirRecord(new Zip64EndCentralDirRecord());
                }
                if (zipModel.getZip64EndCentralDirLocator() == null) {
                    zipModel.setZip64EndCentralDirLocator(new Zip64EndCentralDirLocator());
                }
                zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(i);
                zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(i + 1);
            }
            zipModel.getEndCentralDirRecord().setNoOfThisDisk(i);
            zipModel.getEndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(i);
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    private int writeCentralDirectory(ZipModel zipModel, OutputStream outputStream, List list) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("input parameters is null, cannot write central directory");
        }
        if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < zipModel.getCentralDirectory().getFileHeaders().size(); i2++) {
            i += writeFileHeader(zipModel, (FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2), outputStream, list);
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x00b7 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00bf A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00cc A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d3 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00d7 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00df A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ff A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0107 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0119 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x012a A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0151 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0154 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0157 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0163 A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x017c A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x018e A[Catch:{ Exception -> 0x01d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int writeFileHeader(net.lingala.zip4j.model.ZipModel r19, net.lingala.zip4j.model.FileHeader r20, java.io.OutputStream r21, java.util.List r22) throws net.lingala.zip4j.exception.ZipException {
        /*
            r18 = this;
            r1 = r18
            r0 = r22
            if (r20 == 0) goto L_0x01e0
            if (r21 == 0) goto L_0x01e0
            r2 = 2
            byte[] r3 = new byte[r2]     // Catch:{ Exception -> 0x01d9 }
            r4 = 4
            byte[] r5 = new byte[r4]     // Catch:{ Exception -> 0x01d9 }
            r6 = 8
            byte[] r7 = new byte[r6]     // Catch:{ Exception -> 0x01d9 }
            byte[] r8 = new byte[r2]     // Catch:{ Exception -> 0x01d9 }
            byte[] r9 = new byte[r4]     // Catch:{ Exception -> 0x01d9 }
            int r10 = r20.getSignature()     // Catch:{ Exception -> 0x01d9 }
            r11 = 0
            net.lingala.zip4j.util.Raw.writeIntLittleEndian(r5, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            int r10 = r20.getVersionMadeBy()     // Catch:{ Exception -> 0x01d9 }
            short r10 = (short) r10     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r10 = r20.getVersionNeededToExtract()     // Catch:{ Exception -> 0x01d9 }
            short r10 = (short) r10     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            byte[] r10 = r20.getGeneralPurposeFlag()     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r10, r0)     // Catch:{ Exception -> 0x01d9 }
            int r10 = r20.getCompressionMethod()     // Catch:{ Exception -> 0x01d9 }
            short r10 = (short) r10     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r10 = r20.getLastModFileTime()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeIntLittleEndian(r5, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            long r12 = r20.getCrc32()     // Catch:{ Exception -> 0x01d9 }
            int r10 = (int) r12     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeIntLittleEndian(r5, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            long r12 = r20.getCompressedSize()     // Catch:{ Exception -> 0x01d9 }
            r10 = 1
            r14 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x0095
            long r12 = r20.getUncompressedSize()     // Catch:{ Exception -> 0x01d9 }
            r16 = 50
            long r12 = r12 + r16
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 < 0) goto L_0x0079
            goto L_0x0095
        L_0x0079:
            long r12 = r20.getCompressedSize()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r12)     // Catch:{ Exception -> 0x01d9 }
            java.lang.System.arraycopy(r7, r11, r5, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            long r12 = r20.getUncompressedSize()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r12)     // Catch:{ Exception -> 0x01d9 }
            java.lang.System.arraycopy(r7, r11, r5, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            r5 = 0
            goto L_0x00a2
        L_0x0095:
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r14)     // Catch:{ Exception -> 0x01d9 }
            java.lang.System.arraycopy(r7, r11, r5, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            r5 = 1
        L_0x00a2:
            int r12 = r20.getFileNameLength()     // Catch:{ Exception -> 0x01d9 }
            short r12 = (short) r12     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r12)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            byte[] r12 = new byte[r4]     // Catch:{ Exception -> 0x01d9 }
            long r16 = r20.getOffsetLocalHeader()     // Catch:{ Exception -> 0x01d9 }
            int r13 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r13 <= 0) goto L_0x00bf
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r14)     // Catch:{ Exception -> 0x01d9 }
            java.lang.System.arraycopy(r7, r11, r12, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r13 = 1
            goto L_0x00ca
        L_0x00bf:
            long r13 = r20.getOffsetLocalHeader()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r13)     // Catch:{ Exception -> 0x01d9 }
            java.lang.System.arraycopy(r7, r11, r12, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r13 = 0
        L_0x00ca:
            if (r5 != 0) goto L_0x00d1
            if (r13 == 0) goto L_0x00cf
            goto L_0x00d1
        L_0x00cf:
            r4 = 0
            goto L_0x00d9
        L_0x00d1:
            if (r5 == 0) goto L_0x00d5
            r4 = 20
        L_0x00d5:
            if (r13 == 0) goto L_0x00d9
            int r4 = r4 + 8
        L_0x00d9:
            net.lingala.zip4j.model.AESExtraDataRecord r14 = r20.getAesExtraDataRecord()     // Catch:{ Exception -> 0x01d9 }
            if (r14 == 0) goto L_0x00e1
            int r4 = r4 + 11
        L_0x00e1:
            short r4 = (short) r4     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r8, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r20.getDiskNumberStart()     // Catch:{ Exception -> 0x01d9 }
            short r4 = (short) r4     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r4)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r8, r0)     // Catch:{ Exception -> 0x01d9 }
            byte[] r4 = r20.getExternalFileAttr()     // Catch:{ Exception -> 0x01d9 }
            if (r4 == 0) goto L_0x0107
            byte[] r4 = r20.getExternalFileAttr()     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r4, r0)     // Catch:{ Exception -> 0x01d9 }
            goto L_0x010a
        L_0x0107:
            r1.copyByteArrayToArrayList(r9, r0)     // Catch:{ Exception -> 0x01d9 }
        L_0x010a:
            r1.copyByteArrayToArrayList(r12, r0)     // Catch:{ Exception -> 0x01d9 }
            r4 = 46
            java.lang.String r8 = r19.getFileNameCharset()     // Catch:{ Exception -> 0x01d9 }
            boolean r8 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r8)     // Catch:{ Exception -> 0x01d9 }
            if (r8 == 0) goto L_0x012a
            java.lang.String r8 = r20.getFileName()     // Catch:{ Exception -> 0x01d9 }
            java.lang.String r9 = r19.getFileNameCharset()     // Catch:{ Exception -> 0x01d9 }
            byte[] r8 = r8.getBytes(r9)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r8, r0)     // Catch:{ Exception -> 0x01d9 }
            int r8 = r8.length     // Catch:{ Exception -> 0x01d9 }
            goto L_0x013d
        L_0x012a:
            java.lang.String r8 = r20.getFileName()     // Catch:{ Exception -> 0x01d9 }
            byte[] r8 = net.lingala.zip4j.util.Zip4jUtil.convertCharset(r8)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r8, r0)     // Catch:{ Exception -> 0x01d9 }
            java.lang.String r8 = r20.getFileName()     // Catch:{ Exception -> 0x01d9 }
            int r8 = net.lingala.zip4j.util.Zip4jUtil.getEncodedStringLength(r8)     // Catch:{ Exception -> 0x01d9 }
        L_0x013d:
            int r4 = r4 + r8
            if (r5 != 0) goto L_0x0142
            if (r13 == 0) goto L_0x0188
        L_0x0142:
            r8 = r19
            r8.setZip64Format(r10)     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r10)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + 2
            if (r5 == 0) goto L_0x0154
            r8 = 16
            goto L_0x0155
        L_0x0154:
            r8 = 0
        L_0x0155:
            if (r13 == 0) goto L_0x0159
            int r8 = r8 + 8
        L_0x0159:
            short r8 = (short) r8     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r8)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + r2
            if (r5 == 0) goto L_0x017a
            long r8 = r20.getUncompressedSize()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r8)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r7, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + 8
            long r8 = r20.getCompressedSize()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r8)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r7, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + r6
        L_0x017a:
            if (r13 == 0) goto L_0x0188
            long r5 = r20.getOffsetLocalHeader()     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeLongLittleEndian(r7, r11, r5)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r7, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + 8
        L_0x0188:
            net.lingala.zip4j.model.AESExtraDataRecord r2 = r20.getAesExtraDataRecord()     // Catch:{ Exception -> 0x01d9 }
            if (r2 == 0) goto L_0x01d8
            net.lingala.zip4j.model.AESExtraDataRecord r2 = r20.getAesExtraDataRecord()     // Catch:{ Exception -> 0x01d9 }
            long r5 = r2.getSignature()     // Catch:{ Exception -> 0x01d9 }
            int r6 = (int) r5     // Catch:{ Exception -> 0x01d9 }
            short r5 = (short) r6     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r5)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r5 = r2.getDataSize()     // Catch:{ Exception -> 0x01d9 }
            short r5 = (short) r5     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r5)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r5 = r2.getVersionNumber()     // Catch:{ Exception -> 0x01d9 }
            short r5 = (short) r5     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r5)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            java.lang.String r5 = r2.getVendorID()     // Catch:{ Exception -> 0x01d9 }
            byte[] r5 = r5.getBytes()     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            byte[] r5 = new byte[r10]     // Catch:{ Exception -> 0x01d9 }
            int r6 = r2.getAesStrength()     // Catch:{ Exception -> 0x01d9 }
            byte r6 = (byte) r6     // Catch:{ Exception -> 0x01d9 }
            r5[r11] = r6     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r5, r0)     // Catch:{ Exception -> 0x01d9 }
            int r2 = r2.getCompressionMethod()     // Catch:{ Exception -> 0x01d9 }
            short r2 = (short) r2     // Catch:{ Exception -> 0x01d9 }
            net.lingala.zip4j.util.Raw.writeShortLittleEndian(r3, r11, r2)     // Catch:{ Exception -> 0x01d9 }
            r1.copyByteArrayToArrayList(r3, r0)     // Catch:{ Exception -> 0x01d9 }
            int r4 = r4 + 11
        L_0x01d8:
            return r4
        L_0x01d9:
            r0 = move-exception
            net.lingala.zip4j.exception.ZipException r2 = new net.lingala.zip4j.exception.ZipException
            r2.<init>((java.lang.Throwable) r0)
            throw r2
        L_0x01e0:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r2 = "input parameters is null, cannot write local file header"
            r0.<init>((java.lang.String) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.core.HeaderWriter.writeFileHeader(net.lingala.zip4j.model.ZipModel, net.lingala.zip4j.model.FileHeader, java.io.OutputStream, java.util.List):int");
    }

    private void writeZip64EndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j, List list) throws ZipException {
        int i2;
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write zip64 end of central directory record");
        }
        try {
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[2];
            byte[] bArr3 = new byte[4];
            byte[] bArr4 = new byte[8];
            Raw.writeIntLittleEndian(bArr3, 0, 101075792);
            copyByteArrayToArrayList(bArr3, list);
            Raw.writeLongLittleEndian(bArr4, 0, 44);
            copyByteArrayToArrayList(bArr4, list);
            if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null || zipModel.getCentralDirectory().getFileHeaders().size() <= 0) {
                copyByteArrayToArrayList(bArr2, list);
                copyByteArrayToArrayList(bArr2, list);
            } else {
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(0)).getVersionMadeBy());
                copyByteArrayToArrayList(bArr, list);
                Raw.writeShortLittleEndian(bArr, 0, (short) ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(0)).getVersionNeededToExtract());
                copyByteArrayToArrayList(bArr, list);
            }
            Raw.writeIntLittleEndian(bArr3, 0, zipModel.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr3, list);
            Raw.writeIntLittleEndian(bArr3, 0, zipModel.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr3, list);
            if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null) {
                throw new ZipException("invalid central directory/file headers, cannot write end of central directory record");
            }
            int size = zipModel.getCentralDirectory().getFileHeaders().size();
            if (zipModel.isSplitArchive()) {
                countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndCentralDirRecord().getNoOfThisDisk());
                i2 = 0;
            } else {
                i2 = size;
            }
            Raw.writeLongLittleEndian(bArr4, 0, (long) i2);
            copyByteArrayToArrayList(bArr4, list);
            Raw.writeLongLittleEndian(bArr4, 0, (long) size);
            copyByteArrayToArrayList(bArr4, list);
            Raw.writeLongLittleEndian(bArr4, 0, (long) i);
            copyByteArrayToArrayList(bArr4, list);
            Raw.writeLongLittleEndian(bArr4, 0, j);
            copyByteArrayToArrayList(bArr4, list);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private void writeZip64EndOfCentralDirectoryLocator(ZipModel zipModel, OutputStream outputStream, List list) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write zip64 end of central directory locator");
        }
        try {
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[8];
            Raw.writeIntLittleEndian(bArr, 0, 117853008);
            copyByteArrayToArrayList(bArr, list);
            Raw.writeIntLittleEndian(bArr, 0, zipModel.getZip64EndCentralDirLocator().getNoOfDiskStartOfZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr, list);
            Raw.writeLongLittleEndian(bArr2, 0, zipModel.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec());
            copyByteArrayToArrayList(bArr2, list);
            Raw.writeIntLittleEndian(bArr, 0, zipModel.getZip64EndCentralDirLocator().getTotNumberOfDiscs());
            copyByteArrayToArrayList(bArr, list);
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private void writeEndOfCentralDirectoryRecord(ZipModel zipModel, OutputStream outputStream, int i, long j, List list) throws ZipException {
        if (zipModel == null || outputStream == null) {
            throw new ZipException("zip model or output stream is null, cannot write end of central directory record");
        }
        try {
            byte[] bArr = new byte[2];
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[8];
            Raw.writeIntLittleEndian(bArr2, 0, (int) zipModel.getEndCentralDirRecord().getSignature());
            copyByteArrayToArrayList(bArr2, list);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel.getEndCentralDirRecord().getNoOfThisDisk());
            copyByteArrayToArrayList(bArr, list);
            Raw.writeShortLittleEndian(bArr, 0, (short) zipModel.getEndCentralDirRecord().getNoOfThisDiskStartOfCentralDir());
            copyByteArrayToArrayList(bArr, list);
            if (zipModel.getCentralDirectory() == null || zipModel.getCentralDirectory().getFileHeaders() == null) {
                throw new ZipException("invalid central directory/file headers, cannot write end of central directory record");
            }
            int size = zipModel.getCentralDirectory().getFileHeaders().size();
            Raw.writeShortLittleEndian(bArr, 0, (short) (zipModel.isSplitArchive() ? countNumberOfFileHeaderEntriesOnDisk(zipModel.getCentralDirectory().getFileHeaders(), zipModel.getEndCentralDirRecord().getNoOfThisDisk()) : size));
            copyByteArrayToArrayList(bArr, list);
            Raw.writeShortLittleEndian(bArr, 0, (short) size);
            copyByteArrayToArrayList(bArr, list);
            Raw.writeIntLittleEndian(bArr2, 0, i);
            copyByteArrayToArrayList(bArr2, list);
            if (j > InternalZipConstants.ZIP_64_LIMIT) {
                Raw.writeLongLittleEndian(bArr3, 0, InternalZipConstants.ZIP_64_LIMIT);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list);
            } else {
                Raw.writeLongLittleEndian(bArr3, 0, j);
                System.arraycopy(bArr3, 0, bArr2, 0, 4);
                copyByteArrayToArrayList(bArr2, list);
            }
            int commentLength = zipModel.getEndCentralDirRecord().getComment() != null ? zipModel.getEndCentralDirRecord().getCommentLength() : 0;
            Raw.writeShortLittleEndian(bArr, 0, (short) commentLength);
            copyByteArrayToArrayList(bArr, list);
            if (commentLength > 0) {
                copyByteArrayToArrayList(zipModel.getEndCentralDirRecord().getCommentBytes(), list);
            }
        } catch (Exception e) {
            throw new ZipException((Throwable) e);
        }
    }

    public void updateLocalFileHeader(LocalFileHeader localFileHeader, long j, int i, ZipModel zipModel, byte[] bArr, int i2, SplitOutputStream splitOutputStream) throws ZipException {
        boolean z;
        SplitOutputStream splitOutputStream2;
        String str;
        int i3 = i;
        int i4 = i2;
        if (localFileHeader == null || j < 0 || zipModel == null) {
            throw new ZipException("invalid input parameters, cannot update local file header");
        }
        try {
            if (i4 != splitOutputStream.getCurrSplitFileCounter()) {
                File file = new File(zipModel.getZipFile());
                String parent = file.getParent();
                String zipFileNameWithoutExt = Zip4jUtil.getZipFileNameWithoutExt(file.getName());
                String stringBuffer = new StringBuffer(String.valueOf(parent)).append(System.getProperty("file.separator")).toString();
                if (i4 < 9) {
                    str = new StringBuffer(String.valueOf(stringBuffer)).append(zipFileNameWithoutExt).append(".z0").append(i4 + 1).toString();
                } else {
                    str = new StringBuffer(String.valueOf(stringBuffer)).append(zipFileNameWithoutExt).append(".z").append(i4 + 1).toString();
                }
                splitOutputStream2 = new SplitOutputStream(new File(str));
                z = true;
            } else {
                splitOutputStream2 = splitOutputStream;
                z = false;
            }
            long filePointer = splitOutputStream2.getFilePointer();
            if (i3 == 14) {
                splitOutputStream2.seek(j + ((long) i3));
                splitOutputStream2.write(bArr);
            } else if (i3 == 18 || i3 == 22) {
                updateCompressedSizeInLocalFileHeader(splitOutputStream2, localFileHeader, j, (long) i3, bArr, zipModel.isZip64Format());
            }
            if (z) {
                splitOutputStream2.close();
            } else {
                splitOutputStream.seek(filePointer);
            }
        } catch (Exception e) {
            throw new ZipException((Throwable) e);
        }
    }

    private void updateCompressedSizeInLocalFileHeader(SplitOutputStream splitOutputStream, LocalFileHeader localFileHeader, long j, long j2, byte[] bArr, boolean z) throws ZipException {
        if (splitOutputStream != null) {
            try {
                if (!localFileHeader.isWriteComprSizeInZip64ExtraRecord()) {
                    splitOutputStream.seek(j + j2);
                    splitOutputStream.write(bArr);
                } else if (bArr.length == 8) {
                    long fileNameLength = j + j2 + 4 + 4 + 2 + 2 + ((long) localFileHeader.getFileNameLength()) + 2 + 2 + 8;
                    if (j2 == 22) {
                        fileNameLength += 8;
                    }
                    splitOutputStream.seek(fileNameLength);
                    splitOutputStream.write(bArr);
                } else {
                    throw new ZipException("attempting to write a non 8-byte compressed size block for a zip64 file");
                }
            } catch (IOException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid output stream, cannot update compressed size for local file header");
        }
    }

    private void copyByteArrayToArrayList(byte[] bArr, List list) throws ZipException {
        if (list == null || bArr == null) {
            throw new ZipException("one of the input parameters is null, cannot copy byte array to array list");
        }
        for (byte b : bArr) {
            list.add(Byte.toString(b));
        }
    }

    private byte[] byteArrayListToByteArray(List list) throws ZipException {
        if (list == null) {
            throw new ZipException("input byte array list is null, cannot conver to byte array");
        } else if (list.size() <= 0) {
            return null;
        } else {
            byte[] bArr = new byte[list.size()];
            for (int i = 0; i < list.size(); i++) {
                bArr[i] = Byte.parseByte((String) list.get(i));
            }
            return bArr;
        }
    }

    private int countNumberOfFileHeaderEntriesOnDisk(ArrayList arrayList, int i) throws ZipException {
        if (arrayList != null) {
            int i2 = 0;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (((FileHeader) arrayList.get(i3)).getDiskNumberStart() == i) {
                    i2++;
                }
            }
            return i2;
        }
        throw new ZipException("file headers are null, cannot calculate number of entries on this disk");
    }
}
