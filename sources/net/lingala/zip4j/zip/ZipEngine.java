package net.lingala.zip4j.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.EndCentralDirRecord;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.ArchiveMaintainer;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.Zip4jUtil;

public class ZipEngine {
    private ZipModel zipModel;

    public ZipEngine(ZipModel zipModel2) throws ZipException {
        if (zipModel2 != null) {
            this.zipModel = zipModel2;
            return;
        }
        throw new ZipException("zip model is null in ZipEngine constructor");
    }

    public void addFiles(ArrayList arrayList, ZipParameters zipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        if (arrayList == null || zipParameters == null) {
            throw new ZipException("one of the input parameters is null when adding files");
        } else if (arrayList.size() > 0) {
            progressMonitor.setCurrentOperation(0);
            progressMonitor.setState(1);
            progressMonitor.setResult(1);
            if (z) {
                progressMonitor.setTotalWork(calculateTotalWork(arrayList, zipParameters));
                progressMonitor.setFileName(((File) arrayList.get(0)).getAbsolutePath());
                final ArrayList arrayList2 = arrayList;
                final ZipParameters zipParameters2 = zipParameters;
                final ProgressMonitor progressMonitor2 = progressMonitor;
                new Thread(this, InternalZipConstants.THREAD_NAME) {
                    final /* synthetic */ ZipEngine this$0;

                    {
                        this.this$0 = r1;
                    }

                    public void run() {
                        try {
                            this.this$0.initAddFiles(arrayList2, zipParameters2, progressMonitor2);
                        } catch (ZipException unused) {
                        }
                    }
                }.start();
                return;
            }
            initAddFiles(arrayList, zipParameters, progressMonitor);
        } else {
            throw new ZipException("no files to add");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: net.lingala.zip4j.io.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: net.lingala.zip4j.io.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: net.lingala.zip4j.io.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: net.lingala.zip4j.io.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: net.lingala.zip4j.io.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.FileInputStream} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(14:4|(1:6)|7|8|9|(3:11|12|(1:14)(2:15|16))|17|(4:27|28|(6:113|30|(2:32|33)|34|35|122)(6:37|38|(5:40|(2:44|(6:114|46|(2:48|49)|50|51|124))|53|54|(1:56))|57|(2:59|118)(2:60|(3:61|62|(3:119|64|117)(2:66|(7:116|68|69|70|71|72|126)(2:74|75))))|65)|18)|115|20|(2:22|23)|24|25|120) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:113|30|(2:32|33)|34|35|122) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:114|46|(2:48|49)|50|51|124) */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:116|68|69|70|71|72|126) */
    /* JADX WARNING: Code restructure failed: missing block: B:121:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0080 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x0096 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x00ee */
    /* JADX WARNING: Missing exception handler attribute for start block: B:71:0x014a */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x017f A[SYNTHETIC, Splitter:B:101:0x017f] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0186 A[SYNTHETIC, Splitter:B:105:0x0186] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initAddFiles(java.util.ArrayList r13, net.lingala.zip4j.model.ZipParameters r14, net.lingala.zip4j.progress.ProgressMonitor r15) throws net.lingala.zip4j.exception.ZipException {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x0192
            if (r14 == 0) goto L_0x0192
            int r0 = r13.size()
            if (r0 <= 0) goto L_0x018a
            net.lingala.zip4j.model.ZipModel r0 = r12.zipModel
            net.lingala.zip4j.model.EndCentralDirRecord r0 = r0.getEndCentralDirRecord()
            if (r0 != 0) goto L_0x001b
            net.lingala.zip4j.model.ZipModel r0 = r12.zipModel
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r12.createEndOfCentralDirectoryRecord()
            r0.setEndCentralDirRecord(r1)
        L_0x001b:
            r0 = 0
            r12.checkParameters(r14)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            r12.removeFilesIfExists(r13, r14, r15)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.model.ZipModel r1 = r12.zipModel     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            java.lang.String r1 = r1.getZipFile()     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            boolean r1 = net.lingala.zip4j.util.Zip4jUtil.checkFileExists((java.lang.String) r1)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.io.SplitOutputStream r2 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            java.io.File r3 = new java.io.File     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.model.ZipModel r4 = r12.zipModel     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            java.lang.String r4 = r4.getZipFile()     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            r3.<init>(r4)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.model.ZipModel r4 = r12.zipModel     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            long r4 = r4.getSplitLength()     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            r2.<init>((java.io.File) r3, (long) r4)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.io.ZipOutputStream r3 = new net.lingala.zip4j.io.ZipOutputStream     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            net.lingala.zip4j.model.ZipModel r4 = r12.zipModel     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            r3.<init>(r2, r4)     // Catch:{ ZipException -> 0x0174, Exception -> 0x0169, all -> 0x0166 }
            if (r1 == 0) goto L_0x0069
            net.lingala.zip4j.model.ZipModel r1 = r12.zipModel     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r1.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r1 == 0) goto L_0x0061
            net.lingala.zip4j.model.ZipModel r1 = r12.zipModel     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r1.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            long r4 = r1.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r2.seek(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            goto L_0x0069
        L_0x0061:
            net.lingala.zip4j.exception.ZipException r13 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.String r14 = "invalid end of central directory record"
            r13.<init>((java.lang.String) r14)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            throw r13     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
        L_0x0069:
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r1]     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r2 = 0
            r4 = 0
        L_0x006f:
            int r5 = r13.size()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r4 < r5) goto L_0x0084
            r3.finish()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r15.endProgressMonitorSuccess()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r0 == 0) goto L_0x0080
            r0.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            r3.close()     // Catch:{ IOException -> 0x0083 }
        L_0x0083:
            return
        L_0x0084:
            boolean r5 = r15.isCancelAllTasks()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r6 = 3
            if (r5 == 0) goto L_0x009a
            r15.setResult(r6)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r15.setState(r2)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r0 == 0) goto L_0x0096
            r0.close()     // Catch:{ IOException -> 0x0096 }
        L_0x0096:
            r3.close()     // Catch:{ IOException -> 0x0099 }
        L_0x0099:
            return
        L_0x009a:
            java.lang.Object r5 = r14.clone()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            net.lingala.zip4j.model.ZipParameters r5 = (net.lingala.zip4j.model.ZipParameters) r5     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r15.setFileName(r7)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            boolean r7 = r7.isDirectory()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r7 != 0) goto L_0x0105
            boolean r7 = r5.isEncryptFiles()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r7 == 0) goto L_0x00f2
            int r7 = r5.getEncryptionMethod()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r7 != 0) goto L_0x00f2
            r15.setCurrentOperation(r6)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            long r7 = net.lingala.zip4j.util.CRCUtil.computeFileCRC(r7, r15)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            int r8 = (int) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r5.setSourceFileCRC(r8)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r15.setCurrentOperation(r2)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            boolean r7 = r15.isCancelAllTasks()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r7 == 0) goto L_0x00f2
            r15.setResult(r6)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r15.setState(r2)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r0 == 0) goto L_0x00ee
            r0.close()     // Catch:{ IOException -> 0x00ee }
        L_0x00ee:
            r3.close()     // Catch:{ IOException -> 0x00f1 }
        L_0x00f1:
            return
        L_0x00f2:
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            long r7 = net.lingala.zip4j.util.Zip4jUtil.getFileLengh((java.io.File) r7)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0105
            r5.setCompressionMethod(r2)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
        L_0x0105:
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r3.putNextEntry(r7, r5)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.Object r5 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r5 = (java.io.File) r5     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            boolean r5 = r5.isDirectory()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            if (r5 == 0) goto L_0x011e
            r3.closeEntry()     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            goto L_0x0137
        L_0x011e:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.lang.Object r7 = r13.get(r4)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            java.io.File r7 = (java.io.File) r7     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
            r5.<init>(r7)     // Catch:{ ZipException -> 0x0162, Exception -> 0x015e, all -> 0x015c }
        L_0x0129:
            int r0 = r5.read(r1)     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r7 = -1
            if (r0 != r7) goto L_0x013b
            r3.closeEntry()     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r5.close()     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r0 = r5
        L_0x0137:
            int r4 = r4 + 1
            goto L_0x006f
        L_0x013b:
            boolean r7 = r15.isCancelAllTasks()     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            if (r7 == 0) goto L_0x014e
            r15.setResult(r6)     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r15.setState(r2)     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r5.close()     // Catch:{ IOException -> 0x014a }
        L_0x014a:
            r3.close()     // Catch:{ IOException -> 0x014d }
        L_0x014d:
            return
        L_0x014e:
            r3.write(r1, r2, r0)     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            long r7 = (long) r0     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            r15.updateWorkCompleted(r7)     // Catch:{ ZipException -> 0x015a, Exception -> 0x0158, all -> 0x0156 }
            goto L_0x0129
        L_0x0156:
            r13 = move-exception
            goto L_0x017c
        L_0x0158:
            r13 = move-exception
            goto L_0x0160
        L_0x015a:
            r13 = move-exception
            goto L_0x0164
        L_0x015c:
            r13 = move-exception
            goto L_0x017d
        L_0x015e:
            r13 = move-exception
            r5 = r0
        L_0x0160:
            r0 = r3
            goto L_0x016b
        L_0x0162:
            r13 = move-exception
            r5 = r0
        L_0x0164:
            r0 = r3
            goto L_0x0176
        L_0x0166:
            r13 = move-exception
            r3 = r0
            goto L_0x017d
        L_0x0169:
            r13 = move-exception
            r5 = r0
        L_0x016b:
            r15.endProgressMonitorError(r13)     // Catch:{ all -> 0x017a }
            net.lingala.zip4j.exception.ZipException r14 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x017a }
            r14.<init>((java.lang.Throwable) r13)     // Catch:{ all -> 0x017a }
            throw r14     // Catch:{ all -> 0x017a }
        L_0x0174:
            r13 = move-exception
            r5 = r0
        L_0x0176:
            r15.endProgressMonitorError(r13)     // Catch:{ all -> 0x017a }
            throw r13     // Catch:{ all -> 0x017a }
        L_0x017a:
            r13 = move-exception
            r3 = r0
        L_0x017c:
            r0 = r5
        L_0x017d:
            if (r0 == 0) goto L_0x0184
            r0.close()     // Catch:{ IOException -> 0x0183 }
            goto L_0x0184
        L_0x0183:
        L_0x0184:
            if (r3 == 0) goto L_0x0189
            r3.close()     // Catch:{ IOException -> 0x0189 }
        L_0x0189:
            throw r13
        L_0x018a:
            net.lingala.zip4j.exception.ZipException r13 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r14 = "no files to add"
            r13.<init>((java.lang.String) r14)
            throw r13
        L_0x0192:
            net.lingala.zip4j.exception.ZipException r13 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r14 = "one of the input parameters is null when adding files"
            r13.<init>((java.lang.String) r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.zip.ZipEngine.initAddFiles(java.util.ArrayList, net.lingala.zip4j.model.ZipParameters, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009b A[SYNTHETIC, Splitter:B:38:0x009b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addStreamToZip(java.io.InputStream r7, net.lingala.zip4j.model.ZipParameters r8) throws net.lingala.zip4j.exception.ZipException {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x009f
            if (r8 == 0) goto L_0x009f
            r0 = 0
            r6.checkParameters(r8)     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.model.ZipModel r1 = r6.zipModel     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            java.lang.String r1 = r1.getZipFile()     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            boolean r1 = net.lingala.zip4j.util.Zip4jUtil.checkFileExists((java.lang.String) r1)     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.io.SplitOutputStream r2 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            java.io.File r3 = new java.io.File     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.model.ZipModel r4 = r6.zipModel     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            java.lang.String r4 = r4.getZipFile()     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            r3.<init>(r4)     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.model.ZipModel r4 = r6.zipModel     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            long r4 = r4.getSplitLength()     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            r2.<init>((java.io.File) r3, (long) r4)     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.io.ZipOutputStream r3 = new net.lingala.zip4j.io.ZipOutputStream     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            net.lingala.zip4j.model.ZipModel r4 = r6.zipModel     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            r3.<init>(r2, r4)     // Catch:{ ZipException -> 0x0097, Exception -> 0x0090 }
            if (r1 == 0) goto L_0x004f
            net.lingala.zip4j.model.ZipModel r1 = r6.zipModel     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r1.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            if (r1 == 0) goto L_0x0047
            net.lingala.zip4j.model.ZipModel r1 = r6.zipModel     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r1.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            long r4 = r1.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            r2.seek(r4)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            goto L_0x004f
        L_0x0047:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            java.lang.String r8 = "invalid end of central directory record"
            r7.<init>((java.lang.String) r8)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            throw r7     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
        L_0x004f:
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r1 = new byte[r1]     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            r3.putNextEntry(r0, r8)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            java.lang.String r0 = r8.getFileNameInZip()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            java.lang.String r2 = "/"
            boolean r0 = r0.endsWith(r2)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            if (r0 != 0) goto L_0x007b
            java.lang.String r8 = r8.getFileNameInZip()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            java.lang.String r0 = "\\"
            boolean r8 = r8.endsWith(r0)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            if (r8 != 0) goto L_0x007b
        L_0x006e:
            int r8 = r7.read(r1)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            r0 = -1
            if (r8 != r0) goto L_0x0076
            goto L_0x007b
        L_0x0076:
            r0 = 0
            r3.write(r1, r0, r8)     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            goto L_0x006e
        L_0x007b:
            r3.closeEntry()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            r3.finish()     // Catch:{ ZipException -> 0x008b, Exception -> 0x0088, all -> 0x0085 }
            r3.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0084:
            return
        L_0x0085:
            r7 = move-exception
            r0 = r3
            goto L_0x0099
        L_0x0088:
            r7 = move-exception
            r0 = r3
            goto L_0x0091
        L_0x008b:
            r7 = move-exception
            r0 = r3
            goto L_0x0098
        L_0x008e:
            r7 = move-exception
            goto L_0x0099
        L_0x0090:
            r7 = move-exception
        L_0x0091:
            net.lingala.zip4j.exception.ZipException r8 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x008e }
            r8.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x008e }
            throw r8     // Catch:{ all -> 0x008e }
        L_0x0097:
            r7 = move-exception
        L_0x0098:
            throw r7     // Catch:{ all -> 0x008e }
        L_0x0099:
            if (r0 == 0) goto L_0x009e
            r0.close()     // Catch:{ IOException -> 0x009e }
        L_0x009e:
            throw r7
        L_0x009f:
            net.lingala.zip4j.exception.ZipException r7 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r8 = "one of the input parameters is null, cannot add stream to zip"
            r7.<init>((java.lang.String) r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.zip.ZipEngine.addStreamToZip(java.io.InputStream, net.lingala.zip4j.model.ZipParameters):void");
    }

    public void addFolderToZip(File file, ZipParameters zipParameters, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        String str;
        if (file == null || zipParameters == null) {
            throw new ZipException("one of the input parameters is null, cannot add folder to zip");
        } else if (!Zip4jUtil.checkFileExists(file.getAbsolutePath())) {
            throw new ZipException("input folder does not exist");
        } else if (!file.isDirectory()) {
            throw new ZipException("input file is not a folder, user addFileToZip method to add files");
        } else if (Zip4jUtil.checkFileReadAccess(file.getAbsolutePath())) {
            if (zipParameters.isIncludeRootFolder()) {
                str = "";
                if (file.getAbsolutePath() != null) {
                    if (file.getAbsoluteFile().getParentFile() != null) {
                        str = file.getAbsoluteFile().getParentFile().getAbsolutePath();
                    }
                } else if (file.getParentFile() != null) {
                    str = file.getParentFile().getAbsolutePath();
                }
            } else {
                str = file.getAbsolutePath();
            }
            zipParameters.setDefaultFolderPath(str);
            ArrayList filesInDirectoryRec = Zip4jUtil.getFilesInDirectoryRec(file, zipParameters.isReadHiddenFiles());
            if (zipParameters.isIncludeRootFolder()) {
                if (filesInDirectoryRec == null) {
                    filesInDirectoryRec = new ArrayList();
                }
                filesInDirectoryRec.add(file);
            }
            addFiles(filesInDirectoryRec, zipParameters, progressMonitor, z);
        } else {
            throw new ZipException(new StringBuffer("cannot read folder: ").append(file.getAbsolutePath()).toString());
        }
    }

    private void checkParameters(ZipParameters zipParameters) throws ZipException {
        if (zipParameters == null) {
            throw new ZipException("cannot validate zip parameters");
        } else if (zipParameters.getCompressionMethod() != 0 && zipParameters.getCompressionMethod() != 8) {
            throw new ZipException("unsupported compression type");
        } else if (zipParameters.getCompressionMethod() == 8 && zipParameters.getCompressionLevel() < 0 && zipParameters.getCompressionLevel() > 9) {
            throw new ZipException("invalid compression level. compression level dor deflate should be in the range of 0-9");
        } else if (!zipParameters.isEncryptFiles()) {
            zipParameters.setAesKeyStrength(-1);
            zipParameters.setEncryptionMethod(-1);
        } else if (zipParameters.getEncryptionMethod() != 0 && zipParameters.getEncryptionMethod() != 99) {
            throw new ZipException("unsupported encryption method");
        } else if (zipParameters.getPassword() == null || zipParameters.getPassword().length <= 0) {
            throw new ZipException("input password is empty or null");
        }
    }

    private void removeFilesIfExists(ArrayList arrayList, ZipParameters zipParameters, ProgressMonitor progressMonitor) throws ZipException {
        ZipModel zipModel2 = this.zipModel;
        if (zipModel2 != null && zipModel2.getCentralDirectory() != null && this.zipModel.getCentralDirectory().getFileHeaders() != null && this.zipModel.getCentralDirectory().getFileHeaders().size() > 0) {
            RandomAccessFile randomAccessFile = null;
            int i = 0;
            while (i < arrayList.size()) {
                try {
                    FileHeader fileHeader = Zip4jUtil.getFileHeader(this.zipModel, Zip4jUtil.getRelativeFileName(((File) arrayList.get(i)).getAbsolutePath(), zipParameters.getRootFolderInZip(), zipParameters.getDefaultFolderPath()));
                    if (fileHeader != null) {
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                            randomAccessFile = null;
                        }
                        ArchiveMaintainer archiveMaintainer = new ArchiveMaintainer();
                        progressMonitor.setCurrentOperation(2);
                        HashMap initRemoveZipFile = archiveMaintainer.initRemoveZipFile(this.zipModel, fileHeader, progressMonitor);
                        if (progressMonitor.isCancelAllTasks()) {
                            progressMonitor.setResult(3);
                            progressMonitor.setState(0);
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                    return;
                                } catch (IOException unused) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            progressMonitor.setCurrentOperation(0);
                            if (randomAccessFile == null) {
                                randomAccessFile = prepareFileOutputStream();
                                if (!(initRemoveZipFile == null || initRemoveZipFile.get(InternalZipConstants.OFFSET_CENTRAL_DIR) == null)) {
                                    long parseLong = Long.parseLong((String) initRemoveZipFile.get(InternalZipConstants.OFFSET_CENTRAL_DIR));
                                    if (parseLong >= 0) {
                                        randomAccessFile.seek(parseLong);
                                    }
                                }
                            }
                        }
                    }
                    i++;
                } catch (NumberFormatException unused2) {
                    throw new ZipException("NumberFormatException while parsing offset central directory. Cannot update already existing file header");
                } catch (Exception unused3) {
                    throw new ZipException("Error while parsing offset central directory. Cannot update already existing file header");
                } catch (IOException e) {
                    try {
                        throw new ZipException((Throwable) e);
                    } catch (Throwable th) {
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException unused4) {
                            }
                        }
                        throw th;
                    }
                }
            }
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException unused5) {
                }
            }
        }
    }

    private RandomAccessFile prepareFileOutputStream() throws ZipException {
        String zipFile = this.zipModel.getZipFile();
        if (Zip4jUtil.isStringNotNullAndNotEmpty(zipFile)) {
            try {
                File file = new File(zipFile);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                return new RandomAccessFile(file, InternalZipConstants.WRITE_MODE);
            } catch (FileNotFoundException e) {
                throw new ZipException((Throwable) e);
            }
        } else {
            throw new ZipException("invalid output path");
        }
    }

    private EndCentralDirRecord createEndOfCentralDirectoryRecord() {
        EndCentralDirRecord endCentralDirRecord = new EndCentralDirRecord();
        endCentralDirRecord.setSignature(InternalZipConstants.ENDSIG);
        endCentralDirRecord.setNoOfThisDisk(0);
        endCentralDirRecord.setTotNoOfEntriesInCentralDir(0);
        endCentralDirRecord.setTotNoOfEntriesInCentralDirOnThisDisk(0);
        endCentralDirRecord.setOffsetOfStartOfCentralDir(0);
        return endCentralDirRecord;
    }

    private long calculateTotalWork(ArrayList arrayList, ZipParameters zipParameters) throws ZipException {
        long j;
        FileHeader fileHeader;
        if (arrayList != null) {
            long j2 = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                if ((arrayList.get(i) instanceof File) && ((File) arrayList.get(i)).exists()) {
                    if (!zipParameters.isEncryptFiles() || zipParameters.getEncryptionMethod() != 0) {
                        j = Zip4jUtil.getFileLengh((File) arrayList.get(i));
                    } else {
                        j = Zip4jUtil.getFileLengh((File) arrayList.get(i)) * 2;
                    }
                    j2 += j;
                    if (!(this.zipModel.getCentralDirectory() == null || this.zipModel.getCentralDirectory().getFileHeaders() == null || this.zipModel.getCentralDirectory().getFileHeaders().size() <= 0 || (fileHeader = Zip4jUtil.getFileHeader(this.zipModel, Zip4jUtil.getRelativeFileName(((File) arrayList.get(i)).getAbsolutePath(), zipParameters.getRootFolderInZip(), zipParameters.getDefaultFolderPath()))) == null)) {
                        j2 += Zip4jUtil.getFileLengh(new File(this.zipModel.getZipFile())) - fileHeader.getCompressedSize();
                    }
                }
            }
            return j2;
        }
        throw new ZipException("file list is null, cannot calculate total work");
    }
}
