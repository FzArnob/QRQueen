package net.lingala.zip4j.core;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.unzip.Unzip;
import net.lingala.zip4j.util.ArchiveMaintainer;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;
import net.lingala.zip4j.zip.ZipEngine;

public class ZipFile {
    private String file;
    private String fileNameCharset;
    private boolean isEncrypted;
    private int mode;
    private ProgressMonitor progressMonitor;
    private boolean runInThread;
    private ZipModel zipModel;

    public ZipFile(String str) throws ZipException {
        this(new File(str));
    }

    public ZipFile(File file2) throws ZipException {
        if (file2 != null) {
            this.file = file2.getPath();
            this.mode = 2;
            this.progressMonitor = new ProgressMonitor();
            this.runInThread = false;
            return;
        }
        throw new ZipException("Input zip file parameter is not null", 1);
    }

    public void createZipFile(File file2, ZipParameters zipParameters) throws ZipException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(file2);
        createZipFile(arrayList, zipParameters, false, -1);
    }

    public void createZipFile(File file2, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(file2);
        createZipFile(arrayList, zipParameters, z, j);
    }

    public void createZipFile(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        createZipFile(arrayList, zipParameters, false, -1);
    }

    public void createZipFile(ArrayList arrayList, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(this.file)) {
            throw new ZipException("zip file path is empty");
        } else if (Zip4jUtil.checkFileExists(this.file)) {
            throw new ZipException(new StringBuffer("zip file: ").append(this.file).append(" already exists. To add files to existing zip file use addFile method").toString());
        } else if (arrayList == null) {
            throw new ZipException("input file ArrayList is null, cannot create zip file");
        } else if (Zip4jUtil.checkArrayListTypes(arrayList, 1)) {
            createNewZipModel();
            this.zipModel.setSplitArchive(z);
            this.zipModel.setSplitLength(j);
            addFiles(arrayList, zipParameters);
        } else {
            throw new ZipException("One or more elements in the input ArrayList is not of type File");
        }
    }

    public void createZipFileFromFolder(String str, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            createZipFileFromFolder(new File(str), zipParameters, z, j);
            return;
        }
        throw new ZipException("folderToAdd is empty or null, cannot create Zip File from folder");
    }

    public void createZipFileFromFolder(File file2, ZipParameters zipParameters, boolean z, long j) throws ZipException {
        if (file2 == null) {
            throw new ZipException("folderToAdd is null, cannot create zip file from folder");
        } else if (zipParameters == null) {
            throw new ZipException("input parameters are null, cannot create zip file from folder");
        } else if (!Zip4jUtil.checkFileExists(this.file)) {
            createNewZipModel();
            this.zipModel.setSplitArchive(z);
            if (z) {
                this.zipModel.setSplitLength(j);
            }
            addFolder(file2, zipParameters, false);
        } else {
            throw new ZipException(new StringBuffer("zip file: ").append(this.file).append(" already exists. To add files to existing zip file use addFolder method").toString());
        }
    }

    public void addFile(File file2, ZipParameters zipParameters) throws ZipException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(file2);
        addFiles(arrayList, zipParameters);
    }

    public void addFiles(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        checkZipModel();
        if (this.zipModel == null) {
            throw new ZipException("internal error: zip model is null");
        } else if (arrayList == null) {
            throw new ZipException("input file ArrayList is null, cannot add files");
        } else if (!Zip4jUtil.checkArrayListTypes(arrayList, 1)) {
            throw new ZipException("One or more elements in the input ArrayList is not of type File");
        } else if (zipParameters == null) {
            throw new ZipException("input parameters are null, cannot add files to zip");
        } else if (this.progressMonitor.getState() == 1) {
            throw new ZipException("invalid operation - Zip4j is in busy state");
        } else if (!Zip4jUtil.checkFileExists(this.file) || !this.zipModel.isSplitArchive()) {
            new ZipEngine(this.zipModel).addFiles(arrayList, zipParameters, this.progressMonitor, this.runInThread);
        } else {
            throw new ZipException("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
    }

    public void addFolder(String str, ZipParameters zipParameters) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            addFolder(new File(str), zipParameters);
            return;
        }
        throw new ZipException("input path is null or empty, cannot add folder to zip file");
    }

    public void addFolder(File file2, ZipParameters zipParameters) throws ZipException {
        if (file2 == null) {
            throw new ZipException("input path is null, cannot add folder to zip file");
        } else if (zipParameters != null) {
            addFolder(file2, zipParameters, true);
        } else {
            throw new ZipException("input parameters are null, cannot add folder to zip file");
        }
    }

    private void addFolder(File file2, ZipParameters zipParameters, boolean z) throws ZipException {
        checkZipModel();
        ZipModel zipModel2 = this.zipModel;
        if (zipModel2 == null) {
            throw new ZipException("internal error: zip model is null");
        } else if (!z || !zipModel2.isSplitArchive()) {
            new ZipEngine(this.zipModel).addFolderToZip(file2, zipParameters, this.progressMonitor, this.runInThread);
        } else {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
    }

    public void addStream(InputStream inputStream, ZipParameters zipParameters) throws ZipException {
        if (inputStream == null) {
            throw new ZipException("inputstream is null, cannot add file to zip");
        } else if (zipParameters != null) {
            setRunInThread(false);
            checkZipModel();
            if (this.zipModel == null) {
                throw new ZipException("internal error: zip model is null");
            } else if (!Zip4jUtil.checkFileExists(this.file) || !this.zipModel.isSplitArchive()) {
                new ZipEngine(this.zipModel).addStreamToZip(inputStream, zipParameters);
            } else {
                throw new ZipException("Zip file already exists. Zip file format does not allow updating split/spanned files");
            }
        } else {
            throw new ZipException("zip parameters are null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A[SYNTHETIC, Splitter:B:28:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readZipInfo() throws net.lingala.zip4j.exception.ZipException {
        /*
            r5 = this;
            java.lang.String r0 = r5.file
            boolean r0 = net.lingala.zip4j.util.Zip4jUtil.checkFileExists((java.lang.String) r0)
            if (r0 == 0) goto L_0x0068
            java.lang.String r0 = r5.file
            boolean r0 = net.lingala.zip4j.util.Zip4jUtil.checkFileReadAccess(r0)
            if (r0 == 0) goto L_0x0060
            int r0 = r5.mode
            r1 = 2
            if (r0 != r1) goto L_0x0058
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x0047, all -> 0x0042 }
            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0047, all -> 0x0042 }
            java.lang.String r3 = r5.file     // Catch:{ FileNotFoundException -> 0x0047, all -> 0x0042 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0047, all -> 0x0042 }
            java.lang.String r3 = "r"
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x0047, all -> 0x0042 }
            net.lingala.zip4j.model.ZipModel r0 = r5.zipModel     // Catch:{ FileNotFoundException -> 0x0040 }
            if (r0 != 0) goto L_0x003c
            net.lingala.zip4j.core.HeaderReader r0 = new net.lingala.zip4j.core.HeaderReader     // Catch:{ FileNotFoundException -> 0x0040 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0040 }
            java.lang.String r2 = r5.fileNameCharset     // Catch:{ FileNotFoundException -> 0x0040 }
            net.lingala.zip4j.model.ZipModel r0 = r0.readAllHeaders(r2)     // Catch:{ FileNotFoundException -> 0x0040 }
            r5.zipModel = r0     // Catch:{ FileNotFoundException -> 0x0040 }
            if (r0 == 0) goto L_0x003c
            java.lang.String r2 = r5.file     // Catch:{ FileNotFoundException -> 0x0040 }
            r0.setZipFile(r2)     // Catch:{ FileNotFoundException -> 0x0040 }
        L_0x003c:
            r1.close()     // Catch:{ IOException -> 0x003f }
        L_0x003f:
            return
        L_0x0040:
            r0 = move-exception
            goto L_0x004b
        L_0x0042:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0052
        L_0x0047:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x004b:
            net.lingala.zip4j.exception.ZipException r2 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0051 }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x0051 }
            throw r2     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            throw r0
        L_0x0058:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "Invalid mode"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0060:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "no read access for the input zip file"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0068:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "zip file does not exist"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.core.ZipFile.readZipInfo():void");
    }

    public void extractAll(String str) throws ZipException {
        extractAll(str, (UnzipParameters) null);
    }

    public void extractAll(String str, UnzipParameters unzipParameters) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("output path is null or invalid");
        } else if (Zip4jUtil.checkOutputFolder(str)) {
            if (this.zipModel == null) {
                readZipInfo();
            }
            if (this.zipModel == null) {
                throw new ZipException("Internal error occurred when extracting zip file");
            } else if (this.progressMonitor.getState() != 1) {
                new Unzip(this.zipModel).extractAll(unzipParameters, str, this.progressMonitor, this.runInThread);
            } else {
                throw new ZipException("invalid operation - Zip4j is in busy state");
            }
        } else {
            throw new ZipException("invalid output path");
        }
    }

    public void extractFile(FileHeader fileHeader, String str) throws ZipException {
        extractFile(fileHeader, str, (UnzipParameters) null);
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters) throws ZipException {
        extractFile(fileHeader, str, unzipParameters, (String) null);
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters, String str2) throws ZipException {
        if (fileHeader == null) {
            throw new ZipException("input file header is null, cannot extract file");
        } else if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            readZipInfo();
            if (this.progressMonitor.getState() != 1) {
                fileHeader.extractFile(this.zipModel, str, unzipParameters, str2, this.progressMonitor, this.runInThread);
                return;
            }
            throw new ZipException("invalid operation - Zip4j is in busy state");
        } else {
            throw new ZipException("destination path is empty or null, cannot extract file");
        }
    }

    public void extractFile(String str, String str2) throws ZipException {
        extractFile(str, str2, (UnzipParameters) null);
    }

    public void extractFile(String str, String str2, UnzipParameters unzipParameters) throws ZipException {
        extractFile(str, str2, unzipParameters, (String) null);
    }

    public void extractFile(String str, String str2, UnzipParameters unzipParameters, String str3) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("file to extract is null or empty, cannot extract file");
        } else if (Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            readZipInfo();
            FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, str);
            if (fileHeader == null) {
                throw new ZipException("file header not found for given file name, cannot extract file");
            } else if (this.progressMonitor.getState() != 1) {
                fileHeader.extractFile(this.zipModel, str2, unzipParameters, str3, this.progressMonitor, this.runInThread);
            } else {
                throw new ZipException("invalid operation - Zip4j is in busy state");
            }
        } else {
            throw new ZipException("destination string path is empty or null, cannot extract file");
        }
    }

    public void setPassword(String str) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            setPassword(str.toCharArray());
            return;
        }
        throw null;
    }

    public void setPassword(char[] cArr) throws ZipException {
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                throw new ZipException("Zip Model is null");
            }
        }
        if (this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null) {
            throw new ZipException("invalid zip file");
        }
        for (int i = 0; i < this.zipModel.getCentralDirectory().getFileHeaders().size(); i++) {
            if (this.zipModel.getCentralDirectory().getFileHeaders().get(i) != null && ((FileHeader) this.zipModel.getCentralDirectory().getFileHeaders().get(i)).isEncrypted()) {
                ((FileHeader) this.zipModel.getCentralDirectory().getFileHeaders().get(i)).setPassword(cArr);
            }
        }
    }

    public List getFileHeaders() throws ZipException {
        readZipInfo();
        ZipModel zipModel2 = this.zipModel;
        if (zipModel2 == null || zipModel2.getCentralDirectory() == null) {
            return null;
        }
        return this.zipModel.getCentralDirectory().getFileHeaders();
    }

    public FileHeader getFileHeader(String str) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            readZipInfo();
            ZipModel zipModel2 = this.zipModel;
            if (zipModel2 == null || zipModel2.getCentralDirectory() == null) {
                return null;
            }
            return Zip4jUtil.getFileHeader(this.zipModel, str);
        }
        throw new ZipException("input file name is emtpy or null, cannot get FileHeader");
    }

    public boolean isEncrypted() throws ZipException {
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                throw new ZipException("Zip Model is null");
            }
        }
        if (this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null) {
            throw new ZipException("invalid zip file");
        }
        ArrayList fileHeaders = this.zipModel.getCentralDirectory().getFileHeaders();
        int i = 0;
        while (true) {
            if (i >= fileHeaders.size()) {
                break;
            }
            FileHeader fileHeader = (FileHeader) fileHeaders.get(i);
            if (fileHeader != null && fileHeader.isEncrypted()) {
                this.isEncrypted = true;
                break;
            }
            i++;
        }
        return this.isEncrypted;
    }

    public boolean isSplitArchive() throws ZipException {
        if (this.zipModel == null) {
            readZipInfo();
            if (this.zipModel == null) {
                throw new ZipException("Zip Model is null");
            }
        }
        return this.zipModel.isSplitArchive();
    }

    public void removeFile(String str) throws ZipException {
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            if (this.zipModel == null && Zip4jUtil.checkFileExists(this.file)) {
                readZipInfo();
            }
            if (!this.zipModel.isSplitArchive()) {
                FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, str);
                if (fileHeader != null) {
                    removeFile(fileHeader);
                    return;
                }
                throw new ZipException(new StringBuffer("could not find file header for file: ").append(str).toString());
            }
            throw new ZipException("Zip file format does not allow updating split/spanned files");
        }
        throw new ZipException("file name is empty or null, cannot remove file");
    }

    public void removeFile(FileHeader fileHeader) throws ZipException {
        if (fileHeader != null) {
            if (this.zipModel == null && Zip4jUtil.checkFileExists(this.file)) {
                readZipInfo();
            }
            if (!this.zipModel.isSplitArchive()) {
                ArchiveMaintainer archiveMaintainer = new ArchiveMaintainer();
                archiveMaintainer.initProgressMonitorForRemoveOp(this.zipModel, fileHeader, this.progressMonitor);
                archiveMaintainer.removeZipFile(this.zipModel, fileHeader, this.progressMonitor, this.runInThread);
                return;
            }
            throw new ZipException("Zip file format does not allow updating split/spanned files");
        }
        throw new ZipException("file header is null, cannot remove file");
    }

    public void mergeSplitFiles(File file2) throws ZipException {
        if (file2 == null) {
            throw new ZipException("outputZipFile is null, cannot merge split files");
        } else if (!file2.exists()) {
            checkZipModel();
            if (this.zipModel != null) {
                ArchiveMaintainer archiveMaintainer = new ArchiveMaintainer();
                archiveMaintainer.initProgressMonitorForMergeOp(this.zipModel, this.progressMonitor);
                archiveMaintainer.mergeSplitZipFiles(this.zipModel, file2, this.progressMonitor, this.runInThread);
                return;
            }
            throw new ZipException("zip model is null, corrupt zip file?");
        } else {
            throw new ZipException("output Zip File already exists");
        }
    }

    public void setComment(String str) throws ZipException {
        if (str == null) {
            throw new ZipException("input comment is null, cannot update zip file");
        } else if (Zip4jUtil.checkFileExists(this.file)) {
            readZipInfo();
            ZipModel zipModel2 = this.zipModel;
            if (zipModel2 == null) {
                throw new ZipException("zipModel is null, cannot update zip file");
            } else if (zipModel2.getEndCentralDirRecord() != null) {
                new ArchiveMaintainer().setComment(this.zipModel, str);
            } else {
                throw new ZipException("end of central directory is null, cannot set comment");
            }
        } else {
            throw new ZipException("zip file does not exist, cannot set comment for zip file");
        }
    }

    public String getComment() throws ZipException {
        return getComment((String) null);
    }

    public String getComment(String str) throws ZipException {
        if (str == null) {
            str = Zip4jUtil.isSupportedCharset(InternalZipConstants.CHARSET_COMMENTS_DEFAULT) ? InternalZipConstants.CHARSET_COMMENTS_DEFAULT : InternalZipConstants.CHARSET_DEFAULT;
        }
        if (Zip4jUtil.checkFileExists(this.file)) {
            checkZipModel();
            ZipModel zipModel2 = this.zipModel;
            if (zipModel2 == null) {
                throw new ZipException("zip model is null, cannot read comment");
            } else if (zipModel2.getEndCentralDirRecord() == null) {
                throw new ZipException("end of central directory record is null, cannot read comment");
            } else if (this.zipModel.getEndCentralDirRecord().getCommentBytes() == null || this.zipModel.getEndCentralDirRecord().getCommentBytes().length <= 0) {
                return null;
            } else {
                try {
                    return new String(this.zipModel.getEndCentralDirRecord().getCommentBytes(), str);
                } catch (UnsupportedEncodingException e) {
                    throw new ZipException((Throwable) e);
                }
            }
        } else {
            throw new ZipException("zip file does not exist, cannot read comment");
        }
    }

    private void checkZipModel() throws ZipException {
        if (this.zipModel != null) {
            return;
        }
        if (Zip4jUtil.checkFileExists(this.file)) {
            readZipInfo();
        } else {
            createNewZipModel();
        }
    }

    private void createNewZipModel() {
        ZipModel zipModel2 = new ZipModel();
        this.zipModel = zipModel2;
        zipModel2.setZipFile(this.file);
        this.zipModel.setFileNameCharset(this.fileNameCharset);
    }

    public void setFileNameCharset(String str) throws ZipException {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("null or empty charset name");
        } else if (Zip4jUtil.isSupportedCharset(str)) {
            this.fileNameCharset = str;
        } else {
            throw new ZipException(new StringBuffer("unsupported charset: ").append(str).toString());
        }
    }

    public ZipInputStream getInputStream(FileHeader fileHeader) throws ZipException {
        if (fileHeader != null) {
            checkZipModel();
            ZipModel zipModel2 = this.zipModel;
            if (zipModel2 != null) {
                return new Unzip(zipModel2).getInputStream(fileHeader);
            }
            throw new ZipException("zip model is null, cannot get inputstream");
        }
        throw new ZipException("FileHeader is null, cannot get InputStream");
    }

    public boolean isValidZipFile() {
        try {
            readZipInfo();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public ArrayList getSplitZipFiles() throws ZipException {
        checkZipModel();
        return Zip4jUtil.getSplitZipFiles(this.zipModel);
    }

    public ProgressMonitor getProgressMonitor() {
        return this.progressMonitor;
    }

    public boolean isRunInThread() {
        return this.runInThread;
    }

    public void setRunInThread(boolean z) {
        this.runInThread = z;
    }

    public File getFile() {
        return new File(this.file);
    }
}
