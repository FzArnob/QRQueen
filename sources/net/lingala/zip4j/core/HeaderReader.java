package net.lingala.zip4j.core;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.DigitalSignature;
import net.lingala.zip4j.model.EndCentralDirRecord;
import net.lingala.zip4j.model.ExtraDataRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.Zip64EndCentralDirLocator;
import net.lingala.zip4j.model.Zip64EndCentralDirRecord;
import net.lingala.zip4j.model.Zip64ExtendedInfo;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Raw;
import net.lingala.zip4j.util.Zip4jUtil;

public class HeaderReader {
    private RandomAccessFile zip4jRaf;
    private ZipModel zipModel;

    public HeaderReader(RandomAccessFile randomAccessFile) {
        this.zip4jRaf = randomAccessFile;
    }

    public ZipModel readAllHeaders() throws ZipException {
        return readAllHeaders((String) null);
    }

    public ZipModel readAllHeaders(String str) throws ZipException {
        ZipModel zipModel2 = new ZipModel();
        this.zipModel = zipModel2;
        zipModel2.setFileNameCharset(str);
        this.zipModel.setEndCentralDirRecord(readEndOfCentralDirectoryRecord());
        this.zipModel.setZip64EndCentralDirLocator(readZip64EndCentralDirLocator());
        if (this.zipModel.isZip64Format()) {
            this.zipModel.setZip64EndCentralDirRecord(readZip64EndCentralDirRec());
            if (this.zipModel.getZip64EndCentralDirRecord() == null || this.zipModel.getZip64EndCentralDirRecord().getNoOfThisDisk() <= 0) {
                this.zipModel.setSplitArchive(false);
            } else {
                this.zipModel.setSplitArchive(true);
            }
        }
        this.zipModel.setCentralDirectory(readCentralDirectory());
        return this.zipModel;
    }

    private EndCentralDirRecord readEndOfCentralDirectoryRecord() throws ZipException {
        RandomAccessFile randomAccessFile = this.zip4jRaf;
        if (randomAccessFile != null) {
            try {
                byte[] bArr = new byte[4];
                long length = randomAccessFile.length() - 22;
                EndCentralDirRecord endCentralDirRecord = new EndCentralDirRecord();
                int i = 0;
                while (true) {
                    long j = length - 1;
                    this.zip4jRaf.seek(length);
                    i++;
                    if (((long) Raw.readLeInt(this.zip4jRaf, bArr)) == InternalZipConstants.ENDSIG) {
                        break;
                    } else if (i > 3000) {
                        break;
                    } else {
                        length = j;
                    }
                }
                if (((long) Raw.readIntLittleEndian(bArr, 0)) == InternalZipConstants.ENDSIG) {
                    byte[] bArr2 = new byte[4];
                    byte[] bArr3 = new byte[2];
                    endCentralDirRecord.setSignature(InternalZipConstants.ENDSIG);
                    readIntoBuff(this.zip4jRaf, bArr3);
                    endCentralDirRecord.setNoOfThisDisk(Raw.readShortLittleEndian(bArr3, 0));
                    readIntoBuff(this.zip4jRaf, bArr3);
                    endCentralDirRecord.setNoOfThisDiskStartOfCentralDir(Raw.readShortLittleEndian(bArr3, 0));
                    readIntoBuff(this.zip4jRaf, bArr3);
                    endCentralDirRecord.setTotNoOfEntriesInCentralDirOnThisDisk(Raw.readShortLittleEndian(bArr3, 0));
                    readIntoBuff(this.zip4jRaf, bArr3);
                    endCentralDirRecord.setTotNoOfEntriesInCentralDir(Raw.readShortLittleEndian(bArr3, 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    endCentralDirRecord.setSizeOfCentralDir(Raw.readIntLittleEndian(bArr2, 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    endCentralDirRecord.setOffsetOfStartOfCentralDir(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
                    readIntoBuff(this.zip4jRaf, bArr3);
                    int readShortLittleEndian = Raw.readShortLittleEndian(bArr3, 0);
                    endCentralDirRecord.setCommentLength(readShortLittleEndian);
                    if (readShortLittleEndian > 0) {
                        byte[] bArr4 = new byte[readShortLittleEndian];
                        readIntoBuff(this.zip4jRaf, bArr4);
                        endCentralDirRecord.setComment(new String(bArr4));
                        endCentralDirRecord.setCommentBytes(bArr4);
                    } else {
                        endCentralDirRecord.setComment((String) null);
                    }
                    if (endCentralDirRecord.getNoOfThisDisk() > 0) {
                        this.zipModel.setSplitArchive(true);
                    } else {
                        this.zipModel.setSplitArchive(false);
                    }
                    return endCentralDirRecord;
                }
                throw new ZipException("zip headers not found. probably not a zip file");
            } catch (IOException e) {
                throw new ZipException("Probably not a zip file or a corrupted zip file", e, 4);
            }
        } else {
            throw new ZipException("random access file was null", 3);
        }
    }

    private CentralDirectory readCentralDirectory() throws ZipException {
        String str;
        if (this.zip4jRaf == null) {
            throw new ZipException("random access file was null", 3);
        } else if (this.zipModel.getEndCentralDirRecord() != null) {
            try {
                CentralDirectory centralDirectory = new CentralDirectory();
                ArrayList arrayList = new ArrayList();
                EndCentralDirRecord endCentralDirRecord = this.zipModel.getEndCentralDirRecord();
                long offsetOfStartOfCentralDir = endCentralDirRecord.getOffsetOfStartOfCentralDir();
                int totNoOfEntriesInCentralDir = endCentralDirRecord.getTotNoOfEntriesInCentralDir();
                if (this.zipModel.isZip64Format()) {
                    offsetOfStartOfCentralDir = this.zipModel.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo();
                    totNoOfEntriesInCentralDir = (int) this.zipModel.getZip64EndCentralDirRecord().getTotNoOfEntriesInCentralDir();
                }
                this.zip4jRaf.seek(offsetOfStartOfCentralDir);
                byte[] bArr = new byte[4];
                byte[] bArr2 = new byte[2];
                int i = 0;
                while (i < totNoOfEntriesInCentralDir) {
                    FileHeader fileHeader = new FileHeader();
                    readIntoBuff(this.zip4jRaf, bArr);
                    int readIntLittleEndian = Raw.readIntLittleEndian(bArr, 0);
                    boolean z = true;
                    if (((long) readIntLittleEndian) == InternalZipConstants.CENSIG) {
                        fileHeader.setSignature(readIntLittleEndian);
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setVersionMadeBy(Raw.readShortLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setFileNameUTF8Encoded((Raw.readShortLittleEndian(bArr2, 0) & 2048) != 0);
                        byte b = bArr2[0];
                        if ((b & 1) != 0) {
                            fileHeader.setEncrypted(true);
                        }
                        fileHeader.setGeneralPurposeFlag((byte[]) bArr2.clone());
                        fileHeader.setDataDescriptorExists((b >> 3) == 1);
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setCompressionMethod(Raw.readShortLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setLastModFileTime(Raw.readIntLittleEndian(bArr, 0));
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setCrc32((long) Raw.readIntLittleEndian(bArr, 0));
                        fileHeader.setCrcBuff((byte[]) bArr.clone());
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setCompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0));
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setUncompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        int readShortLittleEndian = Raw.readShortLittleEndian(bArr2, 0);
                        fileHeader.setFileNameLength(readShortLittleEndian);
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setExtraFieldLength(Raw.readShortLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        int readShortLittleEndian2 = Raw.readShortLittleEndian(bArr2, 0);
                        fileHeader.setFileComment(new String(bArr2));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setDiskNumberStart(Raw.readShortLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        fileHeader.setInternalFileAttr((byte[]) bArr2.clone());
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setExternalFileAttr((byte[]) bArr.clone());
                        readIntoBuff(this.zip4jRaf, bArr);
                        fileHeader.setOffsetLocalHeader(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr), 0) & InternalZipConstants.ZIP_64_LIMIT);
                        if (readShortLittleEndian > 0) {
                            byte[] bArr3 = new byte[readShortLittleEndian];
                            readIntoBuff(this.zip4jRaf, bArr3);
                            if (Zip4jUtil.isStringNotNullAndNotEmpty(this.zipModel.getFileNameCharset())) {
                                str = new String(bArr3, this.zipModel.getFileNameCharset());
                            } else {
                                str = Zip4jUtil.decodeFileName(bArr3, fileHeader.isFileNameUTF8Encoded());
                            }
                            if (str != null) {
                                if (str.indexOf(new StringBuffer(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(System.getProperty("file.separator")).toString()) >= 0) {
                                    str = str.substring(str.indexOf(new StringBuffer(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(System.getProperty("file.separator")).toString()) + 2);
                                }
                                fileHeader.setFileName(str);
                                if (!str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && !str.endsWith("\\")) {
                                    z = false;
                                }
                                fileHeader.setDirectory(z);
                            } else {
                                throw new ZipException("fileName is null when reading central directory");
                            }
                        } else {
                            fileHeader.setFileName((String) null);
                        }
                        readAndSaveExtraDataRecord(fileHeader);
                        readAndSaveZip64ExtendedInfo(fileHeader);
                        readAndSaveAESExtraDataRecord(fileHeader);
                        if (readShortLittleEndian2 > 0) {
                            byte[] bArr4 = new byte[readShortLittleEndian2];
                            readIntoBuff(this.zip4jRaf, bArr4);
                            fileHeader.setFileComment(new String(bArr4));
                        }
                        arrayList.add(fileHeader);
                        i++;
                    } else {
                        throw new ZipException(new StringBuffer("Expected central directory entry not found (#").append(i + 1).append(")").toString());
                    }
                }
                centralDirectory.setFileHeaders(arrayList);
                DigitalSignature digitalSignature = new DigitalSignature();
                readIntoBuff(this.zip4jRaf, bArr);
                int readIntLittleEndian2 = Raw.readIntLittleEndian(bArr, 0);
                if (((long) readIntLittleEndian2) != InternalZipConstants.DIGSIG) {
                    return centralDirectory;
                }
                digitalSignature.setHeaderSignature(readIntLittleEndian2);
                readIntoBuff(this.zip4jRaf, bArr2);
                int readShortLittleEndian3 = Raw.readShortLittleEndian(bArr2, 0);
                digitalSignature.setSizeOfData(readShortLittleEndian3);
                if (readShortLittleEndian3 > 0) {
                    byte[] bArr5 = new byte[readShortLittleEndian3];
                    readIntoBuff(this.zip4jRaf, bArr5);
                    digitalSignature.setSignatureData(new String(bArr5));
                }
                return centralDirectory;
            } catch (IOException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("EndCentralRecord was null, maybe a corrupt zip file");
        }
    }

    private void readAndSaveExtraDataRecord(FileHeader fileHeader) throws ZipException {
        if (this.zip4jRaf == null) {
            throw new ZipException("invalid file handler when trying to read extra data record");
        } else if (fileHeader != null) {
            int extraFieldLength = fileHeader.getExtraFieldLength();
            if (extraFieldLength > 0) {
                fileHeader.setExtraDataRecords(readExtraDataRecords(extraFieldLength));
            }
        } else {
            throw new ZipException("file header is null");
        }
    }

    private void readAndSaveExtraDataRecord(LocalFileHeader localFileHeader) throws ZipException {
        if (this.zip4jRaf == null) {
            throw new ZipException("invalid file handler when trying to read extra data record");
        } else if (localFileHeader != null) {
            int extraFieldLength = localFileHeader.getExtraFieldLength();
            if (extraFieldLength > 0) {
                localFileHeader.setExtraDataRecords(readExtraDataRecords(extraFieldLength));
            }
        } else {
            throw new ZipException("file header is null");
        }
    }

    private ArrayList readExtraDataRecords(int i) throws ZipException {
        if (i <= 0) {
            return null;
        }
        try {
            byte[] bArr = new byte[i];
            this.zip4jRaf.read(bArr);
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    break;
                }
                ExtraDataRecord extraDataRecord = new ExtraDataRecord();
                extraDataRecord.setHeader((long) Raw.readShortLittleEndian(bArr, i2));
                int i3 = i2 + 2;
                int readShortLittleEndian = Raw.readShortLittleEndian(bArr, i3);
                if (readShortLittleEndian + 2 > i) {
                    readShortLittleEndian = Raw.readShortBigEndian(bArr, i3);
                    if (readShortLittleEndian + 2 > i) {
                        break;
                    }
                }
                extraDataRecord.setSizeOfData(readShortLittleEndian);
                int i4 = i3 + 2;
                if (readShortLittleEndian > 0) {
                    byte[] bArr2 = new byte[readShortLittleEndian];
                    System.arraycopy(bArr, i4, bArr2, 0, readShortLittleEndian);
                    extraDataRecord.setData(bArr2);
                }
                i2 = i4 + readShortLittleEndian;
                arrayList.add(extraDataRecord);
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
            return null;
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    private Zip64EndCentralDirLocator readZip64EndCentralDirLocator() throws ZipException {
        if (this.zip4jRaf != null) {
            try {
                Zip64EndCentralDirLocator zip64EndCentralDirLocator = new Zip64EndCentralDirLocator();
                setFilePointerToReadZip64EndCentralDirLoc();
                byte[] bArr = new byte[4];
                byte[] bArr2 = new byte[8];
                readIntoBuff(this.zip4jRaf, bArr);
                long readIntLittleEndian = (long) Raw.readIntLittleEndian(bArr, 0);
                if (readIntLittleEndian == InternalZipConstants.ZIP64ENDCENDIRLOC) {
                    this.zipModel.setZip64Format(true);
                    zip64EndCentralDirLocator.setSignature(readIntLittleEndian);
                    readIntoBuff(this.zip4jRaf, bArr);
                    zip64EndCentralDirLocator.setNoOfDiskStartOfZip64EndOfCentralDirRec(Raw.readIntLittleEndian(bArr, 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    zip64EndCentralDirLocator.setOffsetZip64EndOfCentralDirRec(Raw.readLongLittleEndian(bArr2, 0));
                    readIntoBuff(this.zip4jRaf, bArr);
                    zip64EndCentralDirLocator.setTotNumberOfDiscs(Raw.readIntLittleEndian(bArr, 0));
                    return zip64EndCentralDirLocator;
                }
                this.zipModel.setZip64Format(false);
                return null;
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid file handler when trying to read Zip64EndCentralDirLocator");
        }
    }

    private Zip64EndCentralDirRecord readZip64EndCentralDirRec() throws ZipException {
        if (this.zipModel.getZip64EndCentralDirLocator() != null) {
            long offsetZip64EndOfCentralDirRec = this.zipModel.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec();
            if (offsetZip64EndOfCentralDirRec >= 0) {
                try {
                    this.zip4jRaf.seek(offsetZip64EndOfCentralDirRec);
                    Zip64EndCentralDirRecord zip64EndCentralDirRecord = new Zip64EndCentralDirRecord();
                    byte[] bArr = new byte[2];
                    byte[] bArr2 = new byte[4];
                    byte[] bArr3 = new byte[8];
                    readIntoBuff(this.zip4jRaf, bArr2);
                    long readIntLittleEndian = (long) Raw.readIntLittleEndian(bArr2, 0);
                    if (readIntLittleEndian == InternalZipConstants.ZIP64ENDCENDIRREC) {
                        zip64EndCentralDirRecord.setSignature(readIntLittleEndian);
                        readIntoBuff(this.zip4jRaf, bArr3);
                        zip64EndCentralDirRecord.setSizeOfZip64EndCentralDirRec(Raw.readLongLittleEndian(bArr3, 0));
                        readIntoBuff(this.zip4jRaf, bArr);
                        zip64EndCentralDirRecord.setVersionMadeBy(Raw.readShortLittleEndian(bArr, 0));
                        readIntoBuff(this.zip4jRaf, bArr);
                        zip64EndCentralDirRecord.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        zip64EndCentralDirRecord.setNoOfThisDisk(Raw.readIntLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr2);
                        zip64EndCentralDirRecord.setNoOfThisDiskStartOfCentralDir(Raw.readIntLittleEndian(bArr2, 0));
                        readIntoBuff(this.zip4jRaf, bArr3);
                        zip64EndCentralDirRecord.setTotNoOfEntriesInCentralDirOnThisDisk(Raw.readLongLittleEndian(bArr3, 0));
                        readIntoBuff(this.zip4jRaf, bArr3);
                        zip64EndCentralDirRecord.setTotNoOfEntriesInCentralDir(Raw.readLongLittleEndian(bArr3, 0));
                        readIntoBuff(this.zip4jRaf, bArr3);
                        zip64EndCentralDirRecord.setSizeOfCentralDir(Raw.readLongLittleEndian(bArr3, 0));
                        readIntoBuff(this.zip4jRaf, bArr3);
                        zip64EndCentralDirRecord.setOffsetStartCenDirWRTStartDiskNo(Raw.readLongLittleEndian(bArr3, 0));
                        long sizeOfZip64EndCentralDirRec = zip64EndCentralDirRecord.getSizeOfZip64EndCentralDirRec() - 44;
                        if (sizeOfZip64EndCentralDirRec > 0) {
                            byte[] bArr4 = new byte[((int) sizeOfZip64EndCentralDirRec)];
                            readIntoBuff(this.zip4jRaf, bArr4);
                            zip64EndCentralDirRecord.setExtensibleDataSector(bArr4);
                        }
                        return zip64EndCentralDirRecord;
                    }
                    throw new ZipException("invalid signature for zip64 end of central directory record");
                } catch (IOException e) {
                    throw new ZipException((Throwable) e);
                }
            } else {
                throw new ZipException("invalid offset for start of end of central directory record");
            }
        } else {
            throw new ZipException("invalid zip64 end of central directory locator");
        }
    }

    private void readAndSaveZip64ExtendedInfo(FileHeader fileHeader) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        if (fileHeader == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        } else if (fileHeader.getExtraDataRecords() != null && fileHeader.getExtraDataRecords().size() > 0 && (readZip64ExtendedInfo = readZip64ExtendedInfo(fileHeader.getExtraDataRecords(), fileHeader.getUncompressedSize(), fileHeader.getCompressedSize(), fileHeader.getOffsetLocalHeader(), fileHeader.getDiskNumberStart())) != null) {
            fileHeader.setZip64ExtendedInfo(readZip64ExtendedInfo);
            if (readZip64ExtendedInfo.getUnCompressedSize() != -1) {
                fileHeader.setUncompressedSize(readZip64ExtendedInfo.getUnCompressedSize());
            }
            if (readZip64ExtendedInfo.getCompressedSize() != -1) {
                fileHeader.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
            }
            if (readZip64ExtendedInfo.getOffsetLocalHeader() != -1) {
                fileHeader.setOffsetLocalHeader(readZip64ExtendedInfo.getOffsetLocalHeader());
            }
            if (readZip64ExtendedInfo.getDiskNumberStart() != -1) {
                fileHeader.setDiskNumberStart(readZip64ExtendedInfo.getDiskNumberStart());
            }
        }
    }

    private void readAndSaveZip64ExtendedInfo(LocalFileHeader localFileHeader) throws ZipException {
        Zip64ExtendedInfo readZip64ExtendedInfo;
        if (localFileHeader == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        } else if (localFileHeader.getExtraDataRecords() != null && localFileHeader.getExtraDataRecords().size() > 0 && (readZip64ExtendedInfo = readZip64ExtendedInfo(localFileHeader.getExtraDataRecords(), localFileHeader.getUncompressedSize(), localFileHeader.getCompressedSize(), -1, -1)) != null) {
            localFileHeader.setZip64ExtendedInfo(readZip64ExtendedInfo);
            if (readZip64ExtendedInfo.getUnCompressedSize() != -1) {
                localFileHeader.setUncompressedSize(readZip64ExtendedInfo.getUnCompressedSize());
            }
            if (readZip64ExtendedInfo.getCompressedSize() != -1) {
                localFileHeader.setCompressedSize(readZip64ExtendedInfo.getCompressedSize());
            }
        }
    }

    private Zip64ExtendedInfo readZip64ExtendedInfo(ArrayList arrayList, long j, long j2, long j3, int i) throws ZipException {
        boolean z;
        int i2;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ExtraDataRecord extraDataRecord = (ExtraDataRecord) arrayList.get(i3);
            if (extraDataRecord != null && extraDataRecord.getHeader() == 1) {
                Zip64ExtendedInfo zip64ExtendedInfo = new Zip64ExtendedInfo();
                byte[] data = extraDataRecord.getData();
                if (extraDataRecord.getSizeOfData() <= 0) {
                    return null;
                }
                byte[] bArr = new byte[8];
                byte[] bArr2 = new byte[4];
                boolean z2 = true;
                if ((j & 65535) != 65535 || extraDataRecord.getSizeOfData() <= 0) {
                    i2 = 0;
                    z = false;
                } else {
                    System.arraycopy(data, 0, bArr, 0, 8);
                    zip64ExtendedInfo.setUnCompressedSize(Raw.readLongLittleEndian(bArr, 0));
                    i2 = 8;
                    z = true;
                }
                if ((j2 & 65535) == 65535 && i2 < extraDataRecord.getSizeOfData()) {
                    System.arraycopy(data, i2, bArr, 0, 8);
                    zip64ExtendedInfo.setCompressedSize(Raw.readLongLittleEndian(bArr, 0));
                    i2 += 8;
                    z = true;
                }
                if ((j3 & 65535) == 65535 && i2 < extraDataRecord.getSizeOfData()) {
                    System.arraycopy(data, i2, bArr, 0, 8);
                    zip64ExtendedInfo.setOffsetLocalHeader(Raw.readLongLittleEndian(bArr, 0));
                    i2 += 8;
                    z = true;
                }
                if ((i & 65535) != 65535 || i2 >= extraDataRecord.getSizeOfData()) {
                    z2 = z;
                } else {
                    System.arraycopy(data, i2, bArr2, 0, 4);
                    zip64ExtendedInfo.setDiskNumberStart(Raw.readIntLittleEndian(bArr2, 0));
                }
                if (z2) {
                    return zip64ExtendedInfo;
                }
                return null;
            }
        }
        return null;
    }

    private void setFilePointerToReadZip64EndCentralDirLoc() throws ZipException {
        try {
            byte[] bArr = new byte[4];
            long length = this.zip4jRaf.length() - 22;
            while (true) {
                long j = length - 1;
                this.zip4jRaf.seek(length);
                if (((long) Raw.readLeInt(this.zip4jRaf, bArr)) == InternalZipConstants.ENDSIG) {
                    RandomAccessFile randomAccessFile = this.zip4jRaf;
                    randomAccessFile.seek(((((randomAccessFile.getFilePointer() - 4) - 4) - 8) - 4) - 4);
                    return;
                }
                length = j;
            }
        } catch (IOException e) {
            throw new ZipException((Throwable) e);
        }
    }

    public LocalFileHeader readLocalFileHeader(FileHeader fileHeader) throws ZipException {
        if (fileHeader == null || this.zip4jRaf == null) {
            throw new ZipException("invalid read parameters for local header");
        }
        long offsetLocalHeader = fileHeader.getOffsetLocalHeader();
        if (fileHeader.getZip64ExtendedInfo() != null && fileHeader.getZip64ExtendedInfo().getOffsetLocalHeader() > 0) {
            offsetLocalHeader = fileHeader.getOffsetLocalHeader();
        }
        if (offsetLocalHeader >= 0) {
            try {
                this.zip4jRaf.seek(offsetLocalHeader);
                LocalFileHeader localFileHeader = new LocalFileHeader();
                byte[] bArr = new byte[2];
                byte[] bArr2 = new byte[4];
                readIntoBuff(this.zip4jRaf, bArr2);
                int readIntLittleEndian = Raw.readIntLittleEndian(bArr2, 0);
                if (((long) readIntLittleEndian) == InternalZipConstants.LOCSIG) {
                    localFileHeader.setSignature(readIntLittleEndian);
                    readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader.setVersionNeededToExtract(Raw.readShortLittleEndian(bArr, 0));
                    readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader.setFileNameUTF8Encoded((Raw.readShortLittleEndian(bArr, 0) & 2048) != 0);
                    byte b = bArr[0];
                    if ((b & 1) != 0) {
                        localFileHeader.setEncrypted(true);
                    }
                    localFileHeader.setGeneralPurposeFlag(bArr);
                    String binaryString = Integer.toBinaryString(b);
                    if (binaryString.length() >= 4) {
                        localFileHeader.setDataDescriptorExists(binaryString.charAt(3) == '1');
                    }
                    readIntoBuff(this.zip4jRaf, bArr);
                    localFileHeader.setCompressionMethod(Raw.readShortLittleEndian(bArr, 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader.setLastModFileTime(Raw.readIntLittleEndian(bArr2, 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader.setCrc32((long) Raw.readIntLittleEndian(bArr2, 0));
                    localFileHeader.setCrcBuff((byte[]) bArr2.clone());
                    readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader.setCompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
                    readIntoBuff(this.zip4jRaf, bArr2);
                    localFileHeader.setUncompressedSize(Raw.readLongLittleEndian(getLongByteFromIntByte(bArr2), 0));
                    readIntoBuff(this.zip4jRaf, bArr);
                    int readShortLittleEndian = Raw.readShortLittleEndian(bArr, 0);
                    localFileHeader.setFileNameLength(readShortLittleEndian);
                    readIntoBuff(this.zip4jRaf, bArr);
                    int readShortLittleEndian2 = Raw.readShortLittleEndian(bArr, 0);
                    localFileHeader.setExtraFieldLength(readShortLittleEndian2);
                    int i = 30;
                    if (readShortLittleEndian > 0) {
                        byte[] bArr3 = new byte[readShortLittleEndian];
                        readIntoBuff(this.zip4jRaf, bArr3);
                        String decodeFileName = Zip4jUtil.decodeFileName(bArr3, localFileHeader.isFileNameUTF8Encoded());
                        if (decodeFileName != null) {
                            if (decodeFileName.indexOf(new StringBuffer(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(System.getProperty("file.separator")).toString()) >= 0) {
                                decodeFileName = decodeFileName.substring(decodeFileName.indexOf(new StringBuffer(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(System.getProperty("file.separator")).toString()) + 2);
                            }
                            localFileHeader.setFileName(decodeFileName);
                            i = 30 + readShortLittleEndian;
                        } else {
                            throw new ZipException("file name is null, cannot assign file name to local file header");
                        }
                    } else {
                        localFileHeader.setFileName((String) null);
                    }
                    readAndSaveExtraDataRecord(localFileHeader);
                    localFileHeader.setOffsetStartOfData(offsetLocalHeader + ((long) (i + readShortLittleEndian2)));
                    localFileHeader.setPassword(fileHeader.getPassword());
                    readAndSaveZip64ExtendedInfo(localFileHeader);
                    readAndSaveAESExtraDataRecord(localFileHeader);
                    if (localFileHeader.isEncrypted() && localFileHeader.getEncryptionMethod() != 99) {
                        if ((b & Ev3Constants.Opcode.JR) == 64) {
                            localFileHeader.setEncryptionMethod(1);
                        } else {
                            localFileHeader.setEncryptionMethod(0);
                        }
                    }
                    if (localFileHeader.getCrc32() <= 0) {
                        localFileHeader.setCrc32(fileHeader.getCrc32());
                        localFileHeader.setCrcBuff(fileHeader.getCrcBuff());
                    }
                    if (localFileHeader.getCompressedSize() <= 0) {
                        localFileHeader.setCompressedSize(fileHeader.getCompressedSize());
                    }
                    if (localFileHeader.getUncompressedSize() <= 0) {
                        localFileHeader.setUncompressedSize(fileHeader.getUncompressedSize());
                    }
                    return localFileHeader;
                }
                throw new ZipException(new StringBuffer("invalid local header signature for file: ").append(fileHeader.getFileName()).toString());
            } catch (IOException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid local header offset");
        }
    }

    private void readAndSaveAESExtraDataRecord(FileHeader fileHeader) throws ZipException {
        AESExtraDataRecord readAESExtraDataRecord;
        if (fileHeader == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        } else if (fileHeader.getExtraDataRecords() != null && fileHeader.getExtraDataRecords().size() > 0 && (readAESExtraDataRecord = readAESExtraDataRecord(fileHeader.getExtraDataRecords())) != null) {
            fileHeader.setAesExtraDataRecord(readAESExtraDataRecord);
            fileHeader.setEncryptionMethod(99);
        }
    }

    private void readAndSaveAESExtraDataRecord(LocalFileHeader localFileHeader) throws ZipException {
        AESExtraDataRecord readAESExtraDataRecord;
        if (localFileHeader == null) {
            throw new ZipException("file header is null in reading Zip64 Extended Info");
        } else if (localFileHeader.getExtraDataRecords() != null && localFileHeader.getExtraDataRecords().size() > 0 && (readAESExtraDataRecord = readAESExtraDataRecord(localFileHeader.getExtraDataRecords())) != null) {
            localFileHeader.setAesExtraDataRecord(readAESExtraDataRecord);
            localFileHeader.setEncryptionMethod(99);
        }
    }

    private AESExtraDataRecord readAESExtraDataRecord(ArrayList arrayList) throws ZipException {
        if (arrayList == null) {
            return null;
        }
        int i = 0;
        while (i < arrayList.size()) {
            ExtraDataRecord extraDataRecord = (ExtraDataRecord) arrayList.get(i);
            if (extraDataRecord == null || extraDataRecord.getHeader() != 39169) {
                i++;
            } else if (extraDataRecord.getData() != null) {
                AESExtraDataRecord aESExtraDataRecord = new AESExtraDataRecord();
                aESExtraDataRecord.setSignature(39169);
                aESExtraDataRecord.setDataSize(extraDataRecord.getSizeOfData());
                byte[] data = extraDataRecord.getData();
                aESExtraDataRecord.setVersionNumber(Raw.readShortLittleEndian(data, 0));
                byte[] bArr = new byte[2];
                System.arraycopy(data, 2, bArr, 0, 2);
                aESExtraDataRecord.setVendorID(new String(bArr));
                aESExtraDataRecord.setAesStrength(data[4] & Ev3Constants.Opcode.TST);
                aESExtraDataRecord.setCompressionMethod(Raw.readShortLittleEndian(data, 5));
                return aESExtraDataRecord;
            } else {
                throw new ZipException("corrput AES extra data records");
            }
        }
        return null;
    }

    private byte[] readIntoBuff(RandomAccessFile randomAccessFile, byte[] bArr) throws ZipException {
        try {
            if (randomAccessFile.read(bArr, 0, bArr.length) != -1) {
                return bArr;
            }
            throw new ZipException("unexpected end of file when reading short buff");
        } catch (IOException e) {
            throw new ZipException("IOException when reading short buff", (Throwable) e);
        }
    }

    private byte[] getLongByteFromIntByte(byte[] bArr) throws ZipException {
        if (bArr == null) {
            throw new ZipException("input parameter is null, cannot expand to 8 bytes");
        } else if (bArr.length == 4) {
            byte[] bArr2 = new byte[8];
            bArr2[0] = bArr[0];
            bArr2[1] = bArr[1];
            bArr2[2] = bArr[2];
            bArr2[3] = bArr[3];
            return bArr2;
        } else {
            throw new ZipException("invalid byte length, cannot expand to 8 bytes");
        }
    }
}
