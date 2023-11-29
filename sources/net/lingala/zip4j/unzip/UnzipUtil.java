package net.lingala.zip4j.unzip;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.util.Zip4jUtil;

public class UnzipUtil {
    public static void applyFileAttributes(FileHeader fileHeader, File file) throws ZipException {
        applyFileAttributes(fileHeader, file, (UnzipParameters) null);
    }

    public static void applyFileAttributes(FileHeader fileHeader, File file, UnzipParameters unzipParameters) throws ZipException {
        if (fileHeader == null) {
            throw new ZipException("cannot set file properties: file header is null");
        } else if (file == null) {
            throw new ZipException("cannot set file properties: output file is null");
        } else if (Zip4jUtil.checkFileExists(file)) {
            if (unzipParameters == null || !unzipParameters.isIgnoreDateTimeAttributes()) {
                setFileLastModifiedTime(fileHeader, file);
            }
            if (unzipParameters == null) {
                setFileAttributes(fileHeader, file, true, true, true, true);
            } else if (unzipParameters.isIgnoreAllFileAttributes()) {
                setFileAttributes(fileHeader, file, false, false, false, false);
            } else {
                setFileAttributes(fileHeader, file, !unzipParameters.isIgnoreReadOnlyFileAttribute(), !unzipParameters.isIgnoreHiddenFileAttribute(), !unzipParameters.isIgnoreArchiveFileAttribute(), !unzipParameters.isIgnoreSystemFileAttribute());
            }
        } else {
            throw new ZipException("cannot set file properties: file doesnot exist");
        }
    }

    private static void setFileAttributes(FileHeader fileHeader, File file, boolean z, boolean z2, boolean z3, boolean z4) throws ZipException {
        if (fileHeader != null) {
            byte[] externalFileAttr = fileHeader.getExternalFileAttr();
            if (externalFileAttr != null) {
                byte b = externalFileAttr[0];
                if (b != 1) {
                    if (b != 2) {
                        if (b == 3) {
                            if (z) {
                                Zip4jUtil.setFileReadOnly(file);
                            }
                            if (z2) {
                                Zip4jUtil.setFileHidden(file);
                                return;
                            }
                            return;
                        } else if (b != 18) {
                            if (b != 38) {
                                if (b != 48) {
                                    if (b != 50) {
                                        switch (b) {
                                            case 32:
                                                break;
                                            case 33:
                                                if (z3) {
                                                    Zip4jUtil.setFileArchive(file);
                                                }
                                                if (z) {
                                                    Zip4jUtil.setFileReadOnly(file);
                                                    return;
                                                }
                                                return;
                                            case 34:
                                                break;
                                            case 35:
                                                if (z3) {
                                                    Zip4jUtil.setFileArchive(file);
                                                }
                                                if (z) {
                                                    Zip4jUtil.setFileReadOnly(file);
                                                }
                                                if (z2) {
                                                    Zip4jUtil.setFileHidden(file);
                                                    return;
                                                }
                                                return;
                                            default:
                                                return;
                                        }
                                    }
                                    if (z3) {
                                        Zip4jUtil.setFileArchive(file);
                                    }
                                    if (z2) {
                                        Zip4jUtil.setFileHidden(file);
                                        return;
                                    }
                                    return;
                                }
                                if (z3) {
                                    Zip4jUtil.setFileArchive(file);
                                    return;
                                }
                                return;
                            }
                            if (z) {
                                Zip4jUtil.setFileReadOnly(file);
                            }
                            if (z2) {
                                Zip4jUtil.setFileHidden(file);
                            }
                            if (z4) {
                                Zip4jUtil.setFileSystemMode(file);
                                return;
                            }
                            return;
                        }
                    }
                    if (z2) {
                        Zip4jUtil.setFileHidden(file);
                    }
                } else if (z) {
                    Zip4jUtil.setFileReadOnly(file);
                }
            }
        } else {
            throw new ZipException("invalid file header. cannot set file attributes");
        }
    }

    private static void setFileLastModifiedTime(FileHeader fileHeader, File file) throws ZipException {
        if (fileHeader.getLastModFileTime() > 0 && file.exists()) {
            file.setLastModified(Zip4jUtil.dosToJavaTme(fileHeader.getLastModFileTime()));
        }
    }
}
