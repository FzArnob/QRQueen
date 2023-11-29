package net.lingala.zip4j.unzip;

import java.io.File;
import java.util.ArrayList;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipInputStream;
import net.lingala.zip4j.model.CentralDirectory;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class Unzip {
    private ZipModel zipModel;

    public Unzip(ZipModel zipModel2) throws ZipException {
        if (zipModel2 != null) {
            this.zipModel = zipModel2;
            return;
        }
        throw new ZipException("ZipModel is null");
    }

    public void extractAll(UnzipParameters unzipParameters, String str, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        CentralDirectory centralDirectory = this.zipModel.getCentralDirectory();
        if (centralDirectory == null || centralDirectory.getFileHeaders() == null) {
            throw new ZipException("invalid central directory in zipModel");
        }
        final ArrayList fileHeaders = centralDirectory.getFileHeaders();
        progressMonitor.setCurrentOperation(1);
        progressMonitor.setTotalWork(calculateTotalWork(fileHeaders));
        progressMonitor.setState(1);
        if (z) {
            final UnzipParameters unzipParameters2 = unzipParameters;
            final ProgressMonitor progressMonitor2 = progressMonitor;
            final String str2 = str;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ Unzip this$0;

                {
                    this.this$0 = r1;
                }

                public void run() {
                    try {
                        this.this$0.initExtractAll(fileHeaders, unzipParameters2, progressMonitor2, str2);
                        progressMonitor2.endProgressMonitorSuccess();
                    } catch (ZipException unused) {
                    }
                }
            }.start();
            return;
        }
        initExtractAll(fileHeaders, unzipParameters, progressMonitor, str);
    }

    /* access modifiers changed from: private */
    public void initExtractAll(ArrayList arrayList, UnzipParameters unzipParameters, ProgressMonitor progressMonitor, String str) throws ZipException {
        for (int i = 0; i < arrayList.size(); i++) {
            initExtractFile((FileHeader) arrayList.get(i), str, unzipParameters, (String) null, progressMonitor);
            if (progressMonitor.isCancelAllTasks()) {
                progressMonitor.setResult(3);
                progressMonitor.setState(0);
                return;
            }
        }
    }

    public void extractFile(FileHeader fileHeader, String str, UnzipParameters unzipParameters, String str2, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        if (fileHeader != null) {
            progressMonitor.setCurrentOperation(1);
            progressMonitor.setTotalWork(fileHeader.getCompressedSize());
            progressMonitor.setState(1);
            progressMonitor.setPercentDone(0);
            progressMonitor.setFileName(fileHeader.getFileName());
            if (z) {
                final FileHeader fileHeader2 = fileHeader;
                final String str3 = str;
                final UnzipParameters unzipParameters2 = unzipParameters;
                final String str4 = str2;
                final ProgressMonitor progressMonitor2 = progressMonitor;
                new Thread(this, InternalZipConstants.THREAD_NAME) {
                    final /* synthetic */ Unzip this$0;

                    {
                        this.this$0 = r1;
                    }

                    public void run() {
                        try {
                            this.this$0.initExtractFile(fileHeader2, str3, unzipParameters2, str4, progressMonitor2);
                            progressMonitor2.endProgressMonitorSuccess();
                        } catch (ZipException unused) {
                        }
                    }
                }.start();
                return;
            }
            initExtractFile(fileHeader, str, unzipParameters, str2, progressMonitor);
            progressMonitor.endProgressMonitorSuccess();
            return;
        }
        throw new ZipException("fileHeader is null");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0081, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0082, code lost:
        r7.endProgressMonitorError(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0085, code lost:
        throw r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081 A[ExcHandler: ZipException (r3v2 'e' net.lingala.zip4j.exception.ZipException A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initExtractFile(net.lingala.zip4j.model.FileHeader r3, java.lang.String r4, net.lingala.zip4j.model.UnzipParameters r5, java.lang.String r6, net.lingala.zip4j.progress.ProgressMonitor r7) throws net.lingala.zip4j.exception.ZipException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0086
            java.lang.String r0 = r3.getFileName()     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r7.setFileName(r0)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            java.lang.String r0 = net.lingala.zip4j.util.InternalZipConstants.FILE_SEPARATOR     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            boolean r0 = r4.endsWith(r0)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            if (r0 != 0) goto L_0x0024
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r0.<init>(r4)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            java.lang.String r4 = net.lingala.zip4j.util.InternalZipConstants.FILE_SEPARATOR     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            java.lang.StringBuffer r4 = r0.append(r4)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            java.lang.String r4 = r4.toString()     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
        L_0x0024:
            boolean r0 = r3.isDirectory()     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            if (r0 == 0) goto L_0x005f
            java.lang.String r3 = r3.getFileName()     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            boolean r5 = net.lingala.zip4j.util.Zip4jUtil.isStringNotNullAndNotEmpty(r3)     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            if (r5 != 0) goto L_0x0035
            return
        L_0x0035:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            java.lang.StringBuffer r3 = r5.append(r3)     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            boolean r3 = r4.exists()     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            if (r3 != 0) goto L_0x006c
            r4.mkdirs()     // Catch:{ Exception -> 0x0055, ZipException -> 0x0081 }
            goto L_0x006c
        L_0x0055:
            r3 = move-exception
            r7.endProgressMonitorError(r3)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            net.lingala.zip4j.exception.ZipException r4 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r4.<init>((java.lang.Throwable) r3)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            throw r4     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
        L_0x005f:
            r2.checkOutputDirectoryStructure(r3, r4, r6)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            net.lingala.zip4j.unzip.UnzipEngine r0 = new net.lingala.zip4j.unzip.UnzipEngine     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            net.lingala.zip4j.model.ZipModel r1 = r2.zipModel     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r0.<init>(r1, r3)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r0.unzipFile(r7, r4, r6, r5)     // Catch:{ Exception -> 0x006d, ZipException -> 0x0081 }
        L_0x006c:
            return
        L_0x006d:
            r3 = move-exception
            r7.endProgressMonitorError(r3)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            net.lingala.zip4j.exception.ZipException r4 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            r4.<init>((java.lang.Throwable) r3)     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
            throw r4     // Catch:{ ZipException -> 0x0081, Exception -> 0x0077 }
        L_0x0077:
            r3 = move-exception
            r7.endProgressMonitorError(r3)
            net.lingala.zip4j.exception.ZipException r4 = new net.lingala.zip4j.exception.ZipException
            r4.<init>((java.lang.Throwable) r3)
            throw r4
        L_0x0081:
            r3 = move-exception
            r7.endProgressMonitorError(r3)
            throw r3
        L_0x0086:
            net.lingala.zip4j.exception.ZipException r3 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r4 = "fileHeader is null"
            r3.<init>((java.lang.String) r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.unzip.Unzip.initExtractFile(net.lingala.zip4j.model.FileHeader, java.lang.String, net.lingala.zip4j.model.UnzipParameters, java.lang.String, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    public ZipInputStream getInputStream(FileHeader fileHeader) throws ZipException {
        return new UnzipEngine(this.zipModel, fileHeader).getInputStream();
    }

    private void checkOutputDirectoryStructure(FileHeader fileHeader, String str, String str2) throws ZipException {
        if (fileHeader == null || !Zip4jUtil.isStringNotNullAndNotEmpty(str)) {
            throw new ZipException("Cannot check output directory structure...one of the parameters was null");
        }
        String fileName = fileHeader.getFileName();
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            str2 = fileName;
        }
        if (Zip4jUtil.isStringNotNullAndNotEmpty(str2)) {
            try {
                File file = new File(new File(new StringBuffer(String.valueOf(str)).append(str2).toString()).getParent());
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
        }
    }

    private long calculateTotalWork(ArrayList arrayList) throws ZipException {
        long j;
        if (arrayList != null) {
            long j2 = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                FileHeader fileHeader = (FileHeader) arrayList.get(i);
                if (fileHeader.getZip64ExtendedInfo() == null || fileHeader.getZip64ExtendedInfo().getUnCompressedSize() <= 0) {
                    j = fileHeader.getCompressedSize();
                } else {
                    j = fileHeader.getZip64ExtendedInfo().getCompressedSize();
                }
                j2 += j;
            }
            return j2;
        }
        throw new ZipException("fileHeaders is null, cannot calculate total work");
    }
}
