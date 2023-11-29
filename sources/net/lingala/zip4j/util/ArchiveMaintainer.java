package net.lingala.zip4j.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;

public class ArchiveMaintainer {
    public HashMap removeZipFile(ZipModel zipModel, FileHeader fileHeader, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        if (z) {
            final ZipModel zipModel2 = zipModel;
            final FileHeader fileHeader2 = fileHeader;
            final ProgressMonitor progressMonitor2 = progressMonitor;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ ArchiveMaintainer this$0;

                {
                    this.this$0 = r1;
                }

                public void run() {
                    try {
                        this.this$0.initRemoveZipFile(zipModel2, fileHeader2, progressMonitor2);
                        progressMonitor2.endProgressMonitorSuccess();
                    } catch (ZipException unused) {
                    }
                }
            }.start();
            return null;
        }
        HashMap initRemoveZipFile = initRemoveZipFile(zipModel, fileHeader, progressMonitor);
        progressMonitor.endProgressMonitorSuccess();
        return initRemoveZipFile;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v8, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v17, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v21, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v9, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v24, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v25, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v28, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v12, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v29, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v37, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v15, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v16, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v29, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v41, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v41, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v18, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v45, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v20, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v32, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v21, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v22, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v51, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v23, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v35, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v37, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v30, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v31, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v32, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v49, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v54, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v1, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v60, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v3, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v33, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v5, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v34, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v35, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v67, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v7, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v8, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v68, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v37, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v70, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v10, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v11, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v39, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v12, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v40, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v13, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v41, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v14, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v42, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v85, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v44, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v77, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v86, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v78, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v75, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v92, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v76, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v94, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v95, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v78, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v97, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v98, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v83, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v101, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v103, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v104, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v86, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v106, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v107, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v109, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v102, resolved type: net.lingala.zip4j.io.SplitOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v112, resolved type: net.lingala.zip4j.model.FileHeader} */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r16v14 */
    /* JADX WARNING: type inference failed for: r2v44 */
    /* JADX WARNING: type inference failed for: r2v46 */
    /* JADX WARNING: type inference failed for: r2v47 */
    /* JADX WARNING: type inference failed for: r2v48 */
    /* JADX WARNING: type inference failed for: r2v49 */
    /* JADX WARNING: type inference failed for: r1v48, types: [boolean] */
    /* JADX WARNING: type inference failed for: r16v36 */
    /* JADX WARNING: type inference failed for: r2v77 */
    /* JADX WARNING: type inference failed for: r2v88 */
    /* JADX WARNING: type inference failed for: r2v89 */
    /* JADX WARNING: type inference failed for: r2v90 */
    /* JADX WARNING: type inference failed for: r2v94 */
    /* JADX WARNING: type inference failed for: r2v95 */
    /* JADX WARNING: type inference failed for: r2v96 */
    /* JADX WARNING: type inference failed for: r2v103 */
    /* JADX WARNING: type inference failed for: r2v104 */
    /* JADX WARNING: type inference failed for: r2v105 */
    /* JADX WARNING: type inference failed for: r2v106 */
    /* JADX WARNING: type inference failed for: r2v107 */
    /* JADX WARNING: type inference failed for: r2v108 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0318 A[Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }] */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x0476 A[SYNTHETIC, Splitter:B:284:0x0476] */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x047b A[Catch:{ IOException -> 0x047f }] */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0487  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x048b  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0110  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashMap initRemoveZipFile(net.lingala.zip4j.model.ZipModel r32, net.lingala.zip4j.model.FileHeader r33, net.lingala.zip4j.progress.ProgressMonitor r34) throws net.lingala.zip4j.exception.ZipException {
        /*
            r31 = this;
            r9 = r31
            r0 = r32
            r1 = r33
            r10 = r34
            java.lang.String r11 = "cannot close input stream or output stream when trying to delete a file from zip file"
            if (r1 == 0) goto L_0x0494
            if (r0 == 0) goto L_0x0494
            java.util.HashMap r12 = new java.util.HashMap
            r12.<init>()
            r15 = 1
            int r8 = net.lingala.zip4j.util.Zip4jUtil.getIndexOfFileHeader(r32, r33)     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            if (r8 < 0) goto L_0x043f
            boolean r2 = r32.isSplitArchive()     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            if (r2 != 0) goto L_0x0436
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            java.lang.String r5 = r32.getZipFile()     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            r4.<init>(r5)     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            r5 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 % r5
            java.lang.StringBuffer r2 = r4.append(r2)     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            java.lang.String r2 = r2.toString()     // Catch:{ ZipException -> 0x0465, Exception -> 0x0455, all -> 0x044e }
            java.io.File r3 = new java.io.File     // Catch:{ ZipException -> 0x042d, Exception -> 0x0424, all -> 0x041b }
            r3.<init>(r2)     // Catch:{ ZipException -> 0x042d, Exception -> 0x0424, all -> 0x041b }
            r7 = r2
        L_0x0042:
            boolean r2 = r3.exists()     // Catch:{ ZipException -> 0x040f, Exception -> 0x0403, all -> 0x03f9 }
            if (r2 != 0) goto L_0x03b6
            net.lingala.zip4j.io.SplitOutputStream r6 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ FileNotFoundException -> 0x03ac, ZipException -> 0x03a7, Exception -> 0x03a2, all -> 0x039d }
            java.io.File r2 = new java.io.File     // Catch:{ FileNotFoundException -> 0x03ac, ZipException -> 0x03a7, Exception -> 0x03a2, all -> 0x039d }
            r2.<init>(r7)     // Catch:{ FileNotFoundException -> 0x03ac, ZipException -> 0x03a7, Exception -> 0x03a2, all -> 0x039d }
            r6.<init>((java.io.File) r2)     // Catch:{ FileNotFoundException -> 0x03ac, ZipException -> 0x03a7, Exception -> 0x03a2, all -> 0x039d }
            java.io.File r4 = new java.io.File     // Catch:{ ZipException -> 0x0390, Exception -> 0x0383, all -> 0x0378 }
            java.lang.String r2 = r32.getZipFile()     // Catch:{ ZipException -> 0x0390, Exception -> 0x0383, all -> 0x0378 }
            r4.<init>(r2)     // Catch:{ ZipException -> 0x0390, Exception -> 0x0383, all -> 0x0378 }
            java.lang.String r2 = "r"
            java.io.RandomAccessFile r5 = r9.createFileHandler(r0, r2)     // Catch:{ ZipException -> 0x036f, Exception -> 0x0366, all -> 0x0358 }
            net.lingala.zip4j.core.HeaderReader r2 = new net.lingala.zip4j.core.HeaderReader     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            r2.<init>(r5)     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            net.lingala.zip4j.model.LocalFileHeader r2 = r2.readLocalFileHeader(r1)     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            if (r2 == 0) goto L_0x0326
            long r2 = r33.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            net.lingala.zip4j.model.Zip64ExtendedInfo r16 = r33.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            r17 = -1
            if (r16 == 0) goto L_0x00a1
            net.lingala.zip4j.model.Zip64ExtendedInfo r16 = r33.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r19 = r16.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            int r16 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r16 == 0) goto L_0x00a1
            net.lingala.zip4j.model.Zip64ExtendedInfo r1 = r33.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r2 = r1.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            goto L_0x00a1
        L_0x008d:
            r0 = move-exception
            r1 = r4
            r14 = r5
            r2 = r6
            goto L_0x03e0
        L_0x0093:
            r0 = move-exception
            r1 = r4
            r16 = r5
            r14 = r6
            goto L_0x03e8
        L_0x009a:
            r0 = move-exception
            r1 = r4
            r16 = r5
            r14 = r6
            goto L_0x03f0
        L_0x00a1:
            r19 = r2
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            long r1 = r1.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            boolean r3 = r32.isZip64Format()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            if (r3 == 0) goto L_0x00bf
            net.lingala.zip4j.model.Zip64EndCentralDirRecord r3 = r32.getZip64EndCentralDirRecord()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            if (r3 == 0) goto L_0x00bf
            net.lingala.zip4j.model.Zip64EndCentralDirRecord r1 = r32.getZip64EndCentralDirRecord()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r1 = r1.getOffsetStartCenDirWRTStartDiskNo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
        L_0x00bf:
            r21 = r1
            net.lingala.zip4j.model.CentralDirectory r1 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            java.util.ArrayList r1 = r1.getFileHeaders()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            int r2 = r1.size()     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            int r2 = r2 - r15
            r23 = 1
            if (r8 != r2) goto L_0x00d7
            long r2 = r21 - r23
        L_0x00d4:
            r25 = r2
            goto L_0x0106
        L_0x00d7:
            int r2 = r8 + 1
            java.lang.Object r2 = r1.get(r2)     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            net.lingala.zip4j.model.FileHeader r2 = (net.lingala.zip4j.model.FileHeader) r2     // Catch:{ ZipException -> 0x0350, Exception -> 0x0348, all -> 0x033c }
            if (r2 == 0) goto L_0x0104
            long r25 = r2.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r25 = r25 - r23
            net.lingala.zip4j.model.Zip64ExtendedInfo r3 = r2.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            if (r3 == 0) goto L_0x0106
            net.lingala.zip4j.model.Zip64ExtendedInfo r3 = r2.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r27 = r3.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            int r3 = (r27 > r17 ? 1 : (r27 == r17 ? 0 : -1))
            if (r3 == 0) goto L_0x0106
            net.lingala.zip4j.model.Zip64ExtendedInfo r2 = r2.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r2 = r2.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x009a, Exception -> 0x0093, all -> 0x008d }
            long r2 = r2 - r23
            goto L_0x00d4
        L_0x0104:
            r25 = r17
        L_0x0106:
            r2 = 0
            int r16 = (r19 > r2 ? 1 : (r19 == r2 ? 0 : -1))
            if (r16 < 0) goto L_0x0318
            int r16 = (r25 > r2 ? 1 : (r25 == r2 ? 0 : -1))
            if (r16 < 0) goto L_0x0318
            if (r8 != 0) goto L_0x0174
            net.lingala.zip4j.model.CentralDirectory r1 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0164, Exception -> 0x0154, all -> 0x0143 }
            java.util.ArrayList r1 = r1.getFileHeaders()     // Catch:{ ZipException -> 0x0164, Exception -> 0x0154, all -> 0x0143 }
            int r1 = r1.size()     // Catch:{ ZipException -> 0x0164, Exception -> 0x0154, all -> 0x0143 }
            if (r1 <= r15) goto L_0x0139
            long r27 = r25 + r23
            r1 = r31
            r2 = r5
            r3 = r6
            r29 = r4
            r16 = r5
            r4 = r27
            r33 = r6
            r14 = r7
            r6 = r21
            r13 = r8
            r8 = r34
            r1.copyFile(r2, r3, r4, r6, r8)     // Catch:{ ZipException -> 0x0197, Exception -> 0x0195, all -> 0x0193 }
            goto L_0x01b7
        L_0x0139:
            r29 = r4
            r16 = r5
            r33 = r6
            r14 = r7
            r13 = r8
            goto L_0x01b7
        L_0x0143:
            r0 = move-exception
            r29 = r4
            r16 = r5
            r33 = r6
            r14 = r7
        L_0x014b:
            r2 = r33
            r4 = r14
            r14 = r16
            r1 = r29
            goto L_0x0453
        L_0x0154:
            r0 = move-exception
            r29 = r4
            r16 = r5
            r33 = r6
            r14 = r7
        L_0x015c:
            r4 = r14
            r1 = r29
            r13 = 0
            r14 = r33
            goto L_0x045c
        L_0x0164:
            r0 = move-exception
            r29 = r4
            r16 = r5
            r33 = r6
            r14 = r7
        L_0x016c:
            r4 = r14
            r1 = r29
            r13 = 0
            r14 = r33
            goto L_0x046c
        L_0x0174:
            r29 = r4
            r16 = r5
            r33 = r6
            r14 = r7
            r13 = r8
            int r1 = r1.size()     // Catch:{ ZipException -> 0x0312, Exception -> 0x030c, all -> 0x0306 }
            int r1 = r1 - r15
            if (r13 != r1) goto L_0x0199
            r4 = 0
            r1 = r31
            r2 = r16
            r3 = r33
            r6 = r19
            r8 = r34
            r1.copyFile(r2, r3, r4, r6, r8)     // Catch:{ ZipException -> 0x0197, Exception -> 0x0195, all -> 0x0193 }
            goto L_0x01b7
        L_0x0193:
            r0 = move-exception
            goto L_0x014b
        L_0x0195:
            r0 = move-exception
            goto L_0x015c
        L_0x0197:
            r0 = move-exception
            goto L_0x016c
        L_0x0199:
            r4 = 0
            r1 = r31
            r2 = r16
            r3 = r33
            r6 = r19
            r8 = r34
            r1.copyFile(r2, r3, r4, r6, r8)     // Catch:{ ZipException -> 0x0312, Exception -> 0x030c, all -> 0x0306 }
            long r4 = r25 + r23
            r1 = r31
            r2 = r16
            r3 = r33
            r6 = r21
            r8 = r34
            r1.copyFile(r2, r3, r4, r6, r8)     // Catch:{ ZipException -> 0x0312, Exception -> 0x030c, all -> 0x0306 }
        L_0x01b7:
            boolean r1 = r34.isCancelAllTasks()     // Catch:{ ZipException -> 0x0312, Exception -> 0x030c, all -> 0x0306 }
            if (r1 == 0) goto L_0x01e8
            r0 = 3
            r10.setResult(r0)     // Catch:{ ZipException -> 0x01e5, Exception -> 0x01e1, all -> 0x01dd }
            r3 = 0
            r10.setState(r3)     // Catch:{ ZipException -> 0x0197, Exception -> 0x0195, all -> 0x0193 }
            if (r16 == 0) goto L_0x01ca
            r16.close()     // Catch:{ IOException -> 0x01d7 }
        L_0x01ca:
            r33.close()     // Catch:{ IOException -> 0x01d7 }
            java.io.File r0 = new java.io.File
            r0.<init>(r14)
            r0.delete()
            r4 = 0
            return r4
        L_0x01d7:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            r0.<init>((java.lang.String) r11)
            throw r0
        L_0x01dd:
            r0 = move-exception
            r3 = 0
            goto L_0x014b
        L_0x01e1:
            r0 = move-exception
            r3 = 0
            goto L_0x015c
        L_0x01e5:
            r0 = move-exception
            r3 = 0
            goto L_0x016c
        L_0x01e8:
            r3 = 0
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x02ff, Exception -> 0x02f8, all -> 0x02f2 }
            r2 = r33
            r6 = r2
            net.lingala.zip4j.io.SplitOutputStream r6 = (net.lingala.zip4j.io.SplitOutputStream) r6     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            long r4 = r2.getFilePointer()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            r1.setOffsetOfStartOfCentralDir(r4)     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            net.lingala.zip4j.model.EndCentralDirRecord r4 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            int r4 = r4.getTotNoOfEntriesInCentralDir()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            int r4 = r4 - r15
            r1.setTotNoOfEntriesInCentralDir(r4)     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            net.lingala.zip4j.model.EndCentralDirRecord r1 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            net.lingala.zip4j.model.EndCentralDirRecord r4 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            int r4 = r4.getTotNoOfEntriesInCentralDirOnThisDisk()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            int r4 = r4 - r15
            r1.setTotNoOfEntriesInCentralDirOnThisDisk(r4)     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            net.lingala.zip4j.model.CentralDirectory r1 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            java.util.ArrayList r1 = r1.getFileHeaders()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            r1.remove(r13)     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            r8 = r13
        L_0x0225:
            net.lingala.zip4j.model.CentralDirectory r1 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            java.util.ArrayList r1 = r1.getFileHeaders()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            int r1 = r1.size()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            if (r8 < r1) goto L_0x0277
            net.lingala.zip4j.core.HeaderWriter r1 = new net.lingala.zip4j.core.HeaderWriter     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            r1.<init>()     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            r1.finalizeZipFile(r0, r2)     // Catch:{ ZipException -> 0x02f0, Exception -> 0x02ee, all -> 0x02ec }
            java.lang.String r1 = "offsetCentralDir"
            net.lingala.zip4j.model.EndCentralDirRecord r0 = r32.getEndCentralDirRecord()     // Catch:{ ZipException -> 0x0270, Exception -> 0x0269, all -> 0x0260 }
            long r3 = r0.getOffsetOfStartOfCentralDir()     // Catch:{ ZipException -> 0x0270, Exception -> 0x0269, all -> 0x0260 }
            java.lang.String r0 = java.lang.Long.toString(r3)     // Catch:{ ZipException -> 0x0270, Exception -> 0x0269, all -> 0x0260 }
            r12.put(r1, r0)     // Catch:{ ZipException -> 0x0270, Exception -> 0x0269, all -> 0x0260 }
            if (r16 == 0) goto L_0x0251
            r16.close()     // Catch:{ IOException -> 0x025a }
        L_0x0251:
            r2.close()     // Catch:{ IOException -> 0x025a }
            r1 = r29
            r9.restoreFileName(r1, r14)
            return r12
        L_0x025a:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            r0.<init>((java.lang.String) r11)
            throw r0
        L_0x0260:
            r0 = move-exception
            r1 = r29
            r4 = r14
            r14 = r16
            r13 = 1
            goto L_0x0474
        L_0x0269:
            r0 = move-exception
            r1 = r29
            r4 = r14
            r13 = 1
            goto L_0x038d
        L_0x0270:
            r0 = move-exception
            r1 = r29
            r4 = r14
            r13 = 1
            goto L_0x039a
        L_0x0277:
            r1 = r29
            net.lingala.zip4j.model.CentralDirectory r4 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.util.ArrayList r4 = r4.getFileHeaders()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.Object r4 = r4.get(r8)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.FileHeader r4 = (net.lingala.zip4j.model.FileHeader) r4     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            long r4 = r4.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.CentralDirectory r6 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.util.ArrayList r6 = r6.getFileHeaders()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.Object r6 = r6.get(r8)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.FileHeader r6 = (net.lingala.zip4j.model.FileHeader) r6     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.Zip64ExtendedInfo r6 = r6.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            if (r6 == 0) goto L_0x02cf
            net.lingala.zip4j.model.CentralDirectory r6 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.util.ArrayList r6 = r6.getFileHeaders()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.Object r6 = r6.get(r8)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.FileHeader r6 = (net.lingala.zip4j.model.FileHeader) r6     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.Zip64ExtendedInfo r6 = r6.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            long r6 = r6.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            int r13 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r13 == 0) goto L_0x02cf
            net.lingala.zip4j.model.CentralDirectory r4 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.util.ArrayList r4 = r4.getFileHeaders()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.Object r4 = r4.get(r8)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.FileHeader r4 = (net.lingala.zip4j.model.FileHeader) r4     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.Zip64ExtendedInfo r4 = r4.getZip64ExtendedInfo()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            long r4 = r4.getOffsetLocalHeader()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
        L_0x02cf:
            net.lingala.zip4j.model.CentralDirectory r6 = r32.getCentralDirectory()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.util.ArrayList r6 = r6.getFileHeaders()     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.Object r6 = r6.get(r8)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            net.lingala.zip4j.model.FileHeader r6 = (net.lingala.zip4j.model.FileHeader) r6     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            long r21 = r25 - r19
            long r4 = r4 - r21
            long r4 = r4 - r23
            r6.setOffsetLocalHeader(r4)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            int r8 = r8 + 1
            r29 = r1
            goto L_0x0225
        L_0x02ec:
            r0 = move-exception
            goto L_0x02f5
        L_0x02ee:
            r0 = move-exception
            goto L_0x02fb
        L_0x02f0:
            r0 = move-exception
            goto L_0x0302
        L_0x02f2:
            r0 = move-exception
            r2 = r33
        L_0x02f5:
            r1 = r29
            goto L_0x0343
        L_0x02f8:
            r0 = move-exception
            r2 = r33
        L_0x02fb:
            r1 = r29
            goto L_0x038b
        L_0x02ff:
            r0 = move-exception
            r2 = r33
        L_0x0302:
            r1 = r29
            goto L_0x0398
        L_0x0306:
            r0 = move-exception
            r2 = r33
            r1 = r29
            goto L_0x0342
        L_0x030c:
            r0 = move-exception
            r2 = r33
            r1 = r29
            goto L_0x034e
        L_0x0312:
            r0 = move-exception
            r2 = r33
            r1 = r29
            goto L_0x0356
        L_0x0318:
            r1 = r4
            r16 = r5
            r2 = r6
            r14 = r7
            r3 = 0
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.String r4 = "invalid offset for start and end of local file, cannot remove file"
            r0.<init>((java.lang.String) r4)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            throw r0     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
        L_0x0326:
            r1 = r4
            r16 = r5
            r2 = r6
            r14 = r7
            r3 = 0
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            java.lang.String r4 = "invalid local file header, cannot remove file from archive"
            r0.<init>((java.lang.String) r4)     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
            throw r0     // Catch:{ ZipException -> 0x0339, Exception -> 0x0336, all -> 0x0334 }
        L_0x0334:
            r0 = move-exception
            goto L_0x0343
        L_0x0336:
            r0 = move-exception
            goto L_0x038b
        L_0x0339:
            r0 = move-exception
            goto L_0x0398
        L_0x033c:
            r0 = move-exception
            r1 = r4
            r16 = r5
            r2 = r6
            r14 = r7
        L_0x0342:
            r3 = 0
        L_0x0343:
            r4 = r14
            r14 = r16
            goto L_0x0453
        L_0x0348:
            r0 = move-exception
            r1 = r4
            r16 = r5
            r2 = r6
            r14 = r7
        L_0x034e:
            r3 = 0
            goto L_0x038b
        L_0x0350:
            r0 = move-exception
            r1 = r4
            r16 = r5
            r2 = r6
            r14 = r7
        L_0x0356:
            r3 = 0
            goto L_0x0398
        L_0x0358:
            r0 = move-exception
            r1 = r4
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r13 = 0
            r30 = r14
            r14 = r4
            r4 = r30
            goto L_0x0474
        L_0x0366:
            r0 = move-exception
            r1 = r4
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r16 = r4
            goto L_0x038b
        L_0x036f:
            r0 = move-exception
            r1 = r4
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r16 = r4
            goto L_0x0398
        L_0x0378:
            r0 = move-exception
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r1 = r4
            r13 = 0
            r4 = r14
            r14 = r1
            goto L_0x0474
        L_0x0383:
            r0 = move-exception
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r1 = r4
            r16 = r1
        L_0x038b:
            r4 = r14
            r13 = 0
        L_0x038d:
            r14 = r2
            goto L_0x045c
        L_0x0390:
            r0 = move-exception
            r2 = r6
            r14 = r7
            r3 = 0
            r4 = 0
            r1 = r4
            r16 = r1
        L_0x0398:
            r4 = r14
            r13 = 0
        L_0x039a:
            r14 = r2
            goto L_0x046c
        L_0x039d:
            r0 = move-exception
            r14 = r7
            r3 = 0
            goto L_0x03fb
        L_0x03a2:
            r0 = move-exception
            r14 = r7
            r3 = 0
            goto L_0x0405
        L_0x03a7:
            r0 = move-exception
            r14 = r7
            r3 = 0
            goto L_0x0411
        L_0x03ac:
            r0 = move-exception
            r14 = r7
            r3 = 0
            r4 = 0
            net.lingala.zip4j.exception.ZipException r1 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            r1.<init>((java.lang.Throwable) r0)     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            throw r1     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
        L_0x03b6:
            r14 = r7
            r13 = r8
            r3 = 0
            r4 = 0
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            java.lang.String r16 = r32.getZipFile()     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            java.lang.String r3 = java.lang.String.valueOf(r16)     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            r2.<init>(r3)     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            long r7 = r7 % r5
            java.lang.StringBuffer r2 = r2.append(r7)     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            java.lang.String r7 = r2.toString()     // Catch:{ ZipException -> 0x03f7, Exception -> 0x03f5, all -> 0x03f3 }
            java.io.File r3 = new java.io.File     // Catch:{ ZipException -> 0x03eb, Exception -> 0x03e3, all -> 0x03dc }
            r3.<init>(r7)     // Catch:{ ZipException -> 0x03eb, Exception -> 0x03e3, all -> 0x03dc }
            r8 = r13
            goto L_0x0042
        L_0x03dc:
            r0 = move-exception
            r1 = r4
            r2 = r1
            r14 = r2
        L_0x03e0:
            r4 = r7
            goto L_0x0453
        L_0x03e3:
            r0 = move-exception
            r1 = r4
            r14 = r1
            r16 = r14
        L_0x03e8:
            r4 = r7
            goto L_0x045b
        L_0x03eb:
            r0 = move-exception
            r1 = r4
            r14 = r1
            r16 = r14
        L_0x03f0:
            r4 = r7
            goto L_0x046b
        L_0x03f3:
            r0 = move-exception
            goto L_0x03fc
        L_0x03f5:
            r0 = move-exception
            goto L_0x0406
        L_0x03f7:
            r0 = move-exception
            goto L_0x0412
        L_0x03f9:
            r0 = move-exception
            r14 = r7
        L_0x03fb:
            r4 = 0
        L_0x03fc:
            r1 = r4
            r2 = r1
            r13 = 0
            r4 = r14
            r14 = r2
            goto L_0x0474
        L_0x0403:
            r0 = move-exception
            r14 = r7
        L_0x0405:
            r4 = 0
        L_0x0406:
            r1 = r4
            r16 = r1
            r13 = 0
            r4 = r14
            r14 = r16
            goto L_0x045c
        L_0x040f:
            r0 = move-exception
            r14 = r7
        L_0x0411:
            r4 = 0
        L_0x0412:
            r1 = r4
            r16 = r1
            r13 = 0
            r4 = r14
            r14 = r16
            goto L_0x046c
        L_0x041b:
            r0 = move-exception
            r4 = 0
            r1 = r4
            r14 = r1
            r13 = 0
            r4 = r2
            r2 = r14
            goto L_0x0474
        L_0x0424:
            r0 = move-exception
            r4 = 0
            r1 = r4
            r14 = r1
            r16 = r14
            r13 = 0
            r4 = r2
            goto L_0x045c
        L_0x042d:
            r0 = move-exception
            r4 = 0
            r1 = r4
            r14 = r1
            r16 = r14
            r13 = 0
            r4 = r2
            goto L_0x046c
        L_0x0436:
            r4 = 0
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
            java.lang.String r1 = "This is a split archive. Zip file format does not allow updating split/spanned files"
            r0.<init>((java.lang.String) r1)     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
            throw r0     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
        L_0x043f:
            r4 = 0
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
            java.lang.String r1 = "file header not found in zip model, cannot remove file"
            r0.<init>((java.lang.String) r1)     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
            throw r0     // Catch:{ ZipException -> 0x044c, Exception -> 0x044a, all -> 0x0448 }
        L_0x0448:
            r0 = move-exception
            goto L_0x0450
        L_0x044a:
            r0 = move-exception
            goto L_0x0457
        L_0x044c:
            r0 = move-exception
            goto L_0x0467
        L_0x044e:
            r0 = move-exception
            r4 = 0
        L_0x0450:
            r1 = r4
            r2 = r1
            r14 = r2
        L_0x0453:
            r13 = 0
            goto L_0x0474
        L_0x0455:
            r0 = move-exception
            r4 = 0
        L_0x0457:
            r1 = r4
            r14 = r1
            r16 = r14
        L_0x045b:
            r13 = 0
        L_0x045c:
            r10.endProgressMonitorError(r0)     // Catch:{ all -> 0x0470 }
            net.lingala.zip4j.exception.ZipException r2 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0470 }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x0470 }
            throw r2     // Catch:{ all -> 0x0470 }
        L_0x0465:
            r0 = move-exception
            r4 = 0
        L_0x0467:
            r1 = r4
            r14 = r1
            r16 = r14
        L_0x046b:
            r13 = 0
        L_0x046c:
            r10.endProgressMonitorError(r0)     // Catch:{ all -> 0x0470 }
            throw r0     // Catch:{ all -> 0x0470 }
        L_0x0470:
            r0 = move-exception
            r2 = r14
            r14 = r16
        L_0x0474:
            if (r14 == 0) goto L_0x0479
            r14.close()     // Catch:{ IOException -> 0x047f }
        L_0x0479:
            if (r2 == 0) goto L_0x0485
            r2.close()     // Catch:{ IOException -> 0x047f }
            goto L_0x0485
        L_0x047f:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            r0.<init>((java.lang.String) r11)
            throw r0
        L_0x0485:
            if (r13 == 0) goto L_0x048b
            r9.restoreFileName(r1, r4)
            goto L_0x0493
        L_0x048b:
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            r1.delete()
        L_0x0493:
            throw r0
        L_0x0494:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "input parameters is null in maintain zip file, cannot remove file from archive"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.ArchiveMaintainer.initRemoveZipFile(net.lingala.zip4j.model.ZipModel, net.lingala.zip4j.model.FileHeader, net.lingala.zip4j.progress.ProgressMonitor):java.util.HashMap");
    }

    private void restoreFileName(File file, String str) throws ZipException {
        if (!file.delete()) {
            throw new ZipException("cannot delete old zip file");
        } else if (!new File(str).renameTo(file)) {
            throw new ZipException("cannot rename modified zip file");
        }
    }

    private void copyFile(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor) throws ZipException {
        if (randomAccessFile == null || outputStream == null) {
            throw new ZipException("input or output stream is null, cannot copy file");
        }
        long j3 = 0;
        if (j < 0) {
            throw new ZipException("starting offset is negative, cannot copy file");
        } else if (j2 >= 0) {
            int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
            if (i > 0) {
                throw new ZipException("start offset is greater than end offset, cannot copy file");
            } else if (i != 0) {
                if (progressMonitor.isCancelAllTasks()) {
                    progressMonitor.setResult(3);
                    progressMonitor.setState(0);
                    return;
                }
                try {
                    randomAccessFile.seek(j);
                    long j4 = j2 - j;
                    byte[] bArr = j4 < 4096 ? new byte[((int) j4)] : new byte[4096];
                    while (true) {
                        int read = randomAccessFile.read(bArr);
                        if (read != -1) {
                            outputStream.write(bArr, 0, read);
                            long j5 = (long) read;
                            progressMonitor.updateWorkCompleted(j5);
                            if (progressMonitor.isCancelAllTasks()) {
                                progressMonitor.setResult(3);
                                return;
                            }
                            j3 += j5;
                            if (j3 != j4) {
                                if (((long) bArr.length) + j3 > j4) {
                                    bArr = new byte[((int) (j4 - j3))];
                                }
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                } catch (IOException e) {
                    throw new ZipException((Throwable) e);
                } catch (Exception e2) {
                    throw new ZipException((Throwable) e2);
                }
            }
        } else {
            throw new ZipException("end offset is negative, cannot copy file");
        }
    }

    private RandomAccessFile createFileHandler(ZipModel zipModel, String str) throws ZipException {
        if (zipModel == null || !Zip4jUtil.isStringNotNullAndNotEmpty(zipModel.getZipFile())) {
            throw new ZipException("input parameter is null in getFilePointer, cannot create file handler to remove file");
        }
        try {
            return new RandomAccessFile(new File(zipModel.getZipFile()), str);
        } catch (FileNotFoundException e) {
            throw new ZipException((Throwable) e);
        }
    }

    public void mergeSplitZipFiles(ZipModel zipModel, File file, ProgressMonitor progressMonitor, boolean z) throws ZipException {
        if (z) {
            final ZipModel zipModel2 = zipModel;
            final File file2 = file;
            final ProgressMonitor progressMonitor2 = progressMonitor;
            new Thread(this, InternalZipConstants.THREAD_NAME) {
                final /* synthetic */ ArchiveMaintainer this$0;

                {
                    this.this$0 = r1;
                }

                public void run() {
                    try {
                        this.this$0.initMergeSplitZipFile(zipModel2, file2, progressMonitor2);
                    } catch (ZipException unused) {
                    }
                }
            }.start();
            return;
        }
        initMergeSplitZipFile(zipModel, file, progressMonitor);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0108, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0109, code lost:
        r1 = r13;
        r7 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x010d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x010e, code lost:
        r1 = r13;
        r7 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0112, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0113, code lost:
        r1 = r13;
        r7 = r24;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0153 A[SYNTHETIC, Splitter:B:106:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x015a A[SYNTHETIC, Splitter:B:110:0x015a] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x00e2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ae A[Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f6 A[SYNTHETIC, Splitter:B:63:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0108 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:51:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x010d A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:51:0x00d1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initMergeSplitZipFile(net.lingala.zip4j.model.ZipModel r26, java.io.File r27, net.lingala.zip4j.progress.ProgressMonitor r28) throws net.lingala.zip4j.exception.ZipException {
        /*
            r25 = this;
            r9 = r25
            r0 = r26
            r10 = r28
            if (r0 == 0) goto L_0x0169
            boolean r1 = r26.isSplitArchive()
            if (r1 == 0) goto L_0x015e
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r1 = 0
            net.lingala.zip4j.model.EndCentralDirRecord r2 = r26.getEndCentralDirRecord()     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
            int r12 = r2.getNoOfThisDisk()     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
            if (r12 <= 0) goto L_0x012f
            r2 = r27
            java.io.OutputStream r13 = r9.prepareOutputStreamForMerge(r2)     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
            r14 = 0
            r4 = r14
            r2 = 0
            r6 = 0
        L_0x0029:
            if (r6 <= r12) goto L_0x0053
            java.lang.Object r0 = r26.clone()     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            net.lingala.zip4j.model.ZipModel r0 = (net.lingala.zip4j.model.ZipModel) r0     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            net.lingala.zip4j.model.EndCentralDirRecord r3 = r0.getEndCentralDirRecord()     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            r3.setOffsetOfStartOfCentralDir(r4)     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            r9.updateSplitZipModel(r0, r11, r2)     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            net.lingala.zip4j.core.HeaderWriter r2 = new net.lingala.zip4j.core.HeaderWriter     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            r2.<init>()     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            r2.finalizeZipFileWithoutValidations(r0, r13)     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            r28.endProgressMonitorSuccess()     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            if (r13 == 0) goto L_0x004d
            r13.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x004d
        L_0x004c:
        L_0x004d:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            return
        L_0x0053:
            java.io.RandomAccessFile r7 = r9.createSplitZipFileHandler(r0, r6)     // Catch:{ IOException -> 0x012b, Exception -> 0x0127, all -> 0x0123 }
            java.lang.Long r1 = new java.lang.Long     // Catch:{ IOException -> 0x011f, Exception -> 0x011b, all -> 0x0117 }
            long r8 = r7.length()     // Catch:{ IOException -> 0x011f, Exception -> 0x011b, all -> 0x0117 }
            r1.<init>(r8)     // Catch:{ IOException -> 0x011f, Exception -> 0x011b, all -> 0x0117 }
            r3 = 4
            if (r6 != 0) goto L_0x00a6
            net.lingala.zip4j.model.CentralDirectory r8 = r26.getCentralDirectory()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            if (r8 == 0) goto L_0x00a6
            net.lingala.zip4j.model.CentralDirectory r8 = r26.getCentralDirectory()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            java.util.ArrayList r8 = r8.getFileHeaders()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            if (r8 == 0) goto L_0x00a6
            net.lingala.zip4j.model.CentralDirectory r8 = r26.getCentralDirectory()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            java.util.ArrayList r8 = r8.getFileHeaders()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            int r8 = r8.size()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            if (r8 <= 0) goto L_0x00a6
            byte[] r8 = new byte[r3]     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r7.seek(r14)     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r7.read(r8)     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r9 = 0
            int r8 = net.lingala.zip4j.util.Raw.readIntLittleEndian(r8, r9)     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r16 = r4
            long r3 = (long) r8     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r18 = 134695760(0x8074b50, double:6.65485477E-316)
            int r5 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r5 != 0) goto L_0x00a9
            r2 = 1
            r3 = 4
            r18 = 1
            goto L_0x00ac
        L_0x009d:
            r0 = move-exception
            goto L_0x0125
        L_0x00a0:
            r0 = move-exception
            goto L_0x0129
        L_0x00a3:
            r0 = move-exception
            goto L_0x012d
        L_0x00a6:
            r16 = r4
            r9 = 0
        L_0x00a9:
            r18 = r2
            r3 = 0
        L_0x00ac:
            if (r6 != r12) goto L_0x00bb
            java.lang.Long r1 = new java.lang.Long     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            net.lingala.zip4j.model.EndCentralDirRecord r2 = r26.getEndCentralDirRecord()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            long r4 = r2.getOffsetOfStartOfCentralDir()     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
            r1.<init>(r4)     // Catch:{ IOException -> 0x00a3, Exception -> 0x00a0, all -> 0x009d }
        L_0x00bb:
            r8 = r1
            long r4 = (long) r3
            long r19 = r8.longValue()     // Catch:{ IOException -> 0x011f, Exception -> 0x011b, all -> 0x0117 }
            r1 = r25
            r2 = r7
            r3 = r13
            r21 = r4
            r23 = r6
            r24 = r7
            r6 = r19
            r9 = r8
            r14 = 0
            r8 = r28
            r1.copyFile(r2, r3, r4, r6, r8)     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            long r1 = r9.longValue()     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            long r1 = r1 - r21
            long r4 = r16 + r1
            boolean r1 = r28.isCancelAllTasks()     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            if (r1 == 0) goto L_0x00f6
            r0 = 3
            r10.setResult(r0)     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            r10.setState(r14)     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            if (r13 == 0) goto L_0x00f0
            r13.close()     // Catch:{ IOException -> 0x00ef }
            goto L_0x00f0
        L_0x00ef:
        L_0x00f0:
            if (r24 == 0) goto L_0x00f5
            r24.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x00f5:
            return
        L_0x00f6:
            r11.add(r9)     // Catch:{ IOException -> 0x0112, Exception -> 0x010d, all -> 0x0108 }
            r24.close()     // Catch:{ IOException -> 0x00fc, Exception -> 0x010d, all -> 0x0108 }
        L_0x00fc:
            int r6 = r23 + 1
            r14 = 0
            r9 = r25
            r2 = r18
            r1 = r24
            goto L_0x0029
        L_0x0108:
            r0 = move-exception
            r1 = r13
            r7 = r24
            goto L_0x0151
        L_0x010d:
            r0 = move-exception
            r1 = r13
            r7 = r24
            goto L_0x013c
        L_0x0112:
            r0 = move-exception
            r1 = r13
            r7 = r24
            goto L_0x0147
        L_0x0117:
            r0 = move-exception
            r24 = r7
            goto L_0x0125
        L_0x011b:
            r0 = move-exception
            r24 = r7
            goto L_0x0129
        L_0x011f:
            r0 = move-exception
            r24 = r7
            goto L_0x012d
        L_0x0123:
            r0 = move-exception
            r7 = r1
        L_0x0125:
            r1 = r13
            goto L_0x0151
        L_0x0127:
            r0 = move-exception
            r7 = r1
        L_0x0129:
            r1 = r13
            goto L_0x013c
        L_0x012b:
            r0 = move-exception
            r7 = r1
        L_0x012d:
            r1 = r13
            goto L_0x0147
        L_0x012f:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
            java.lang.String r2 = "corrupt zip model, archive not a split zip file"
            r0.<init>((java.lang.String) r2)     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
            throw r0     // Catch:{ IOException -> 0x0145, Exception -> 0x013a, all -> 0x0137 }
        L_0x0137:
            r0 = move-exception
            r7 = r1
            goto L_0x0151
        L_0x013a:
            r0 = move-exception
            r7 = r1
        L_0x013c:
            r10.endProgressMonitorError(r0)     // Catch:{ all -> 0x0150 }
            net.lingala.zip4j.exception.ZipException r2 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0150 }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x0150 }
            throw r2     // Catch:{ all -> 0x0150 }
        L_0x0145:
            r0 = move-exception
            r7 = r1
        L_0x0147:
            r10.endProgressMonitorError(r0)     // Catch:{ all -> 0x0150 }
            net.lingala.zip4j.exception.ZipException r2 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0150 }
            r2.<init>((java.lang.Throwable) r0)     // Catch:{ all -> 0x0150 }
            throw r2     // Catch:{ all -> 0x0150 }
        L_0x0150:
            r0 = move-exception
        L_0x0151:
            if (r1 == 0) goto L_0x0158
            r1.close()     // Catch:{ IOException -> 0x0157 }
            goto L_0x0158
        L_0x0157:
        L_0x0158:
            if (r7 == 0) goto L_0x015d
            r7.close()     // Catch:{ IOException -> 0x015d }
        L_0x015d:
            throw r0
        L_0x015e:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "archive not a split zip file"
            r0.<init>((java.lang.String) r1)
            r10.endProgressMonitorError(r0)
            throw r0
        L_0x0169:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r1 = "one of the input parameters is null, cannot merge split zip file"
            r0.<init>((java.lang.String) r1)
            r10.endProgressMonitorError(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.ArchiveMaintainer.initMergeSplitZipFile(net.lingala.zip4j.model.ZipModel, java.io.File, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    private RandomAccessFile createSplitZipFileHandler(ZipModel zipModel, int i) throws ZipException {
        String str;
        if (zipModel == null) {
            throw new ZipException("zip model is null, cannot create split file handler");
        } else if (i >= 0) {
            try {
                String zipFile = zipModel.getZipFile();
                if (i == zipModel.getEndCentralDirRecord().getNoOfThisDisk()) {
                    str = zipModel.getZipFile();
                } else if (i >= 9) {
                    str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z").append(i + 1).toString();
                } else {
                    str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z0").append(i + 1).toString();
                }
                File file = new File(str);
                if (Zip4jUtil.checkFileExists(file)) {
                    return new RandomAccessFile(file, "r");
                }
                throw new ZipException(new StringBuffer("split file does not exist: ").append(str).toString());
            } catch (FileNotFoundException e) {
                throw new ZipException((Throwable) e);
            } catch (Exception e2) {
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("invlaid part number, cannot create split file handler");
        }
    }

    private OutputStream prepareOutputStreamForMerge(File file) throws ZipException {
        if (file != null) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new ZipException((Throwable) e);
            } catch (Exception e2) {
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("outFile is null, cannot create outputstream");
        }
    }

    private void updateSplitZipModel(ZipModel zipModel, ArrayList arrayList, boolean z) throws ZipException {
        if (zipModel != null) {
            zipModel.setSplitArchive(false);
            updateSplitFileHeader(zipModel, arrayList, z);
            updateSplitEndCentralDirectory(zipModel);
            if (zipModel.isZip64Format()) {
                updateSplitZip64EndCentralDirLocator(zipModel, arrayList);
                updateSplitZip64EndCentralDirRec(zipModel, arrayList);
                return;
            }
            return;
        }
        throw new ZipException("zip model is null, cannot update split zip model");
    }

    private void updateSplitFileHeader(ZipModel zipModel, ArrayList arrayList, boolean z) throws ZipException {
        try {
            if (zipModel.getCentralDirectory() != null) {
                int size = zipModel.getCentralDirectory().getFileHeaders().size();
                int i = z ? 4 : 0;
                for (int i2 = 0; i2 < size; i2++) {
                    long j = 0;
                    for (int i3 = 0; i3 < ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2)).getDiskNumberStart(); i3++) {
                        j += ((Long) arrayList.get(i3)).longValue();
                    }
                    ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2)).setOffsetLocalHeader((((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2)).getOffsetLocalHeader() + j) - ((long) i));
                    ((FileHeader) zipModel.getCentralDirectory().getFileHeaders().get(i2)).setDiskNumberStart(0);
                }
                return;
            }
            throw new ZipException("corrupt zip model - getCentralDirectory, cannot update split zip model");
        } catch (ZipException e) {
            throw e;
        } catch (Exception e2) {
            throw new ZipException((Throwable) e2);
        }
    }

    private void updateSplitEndCentralDirectory(ZipModel zipModel) throws ZipException {
        if (zipModel != null) {
            try {
                if (zipModel.getCentralDirectory() != null) {
                    zipModel.getEndCentralDirRecord().setNoOfThisDisk(0);
                    zipModel.getEndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(0);
                    zipModel.getEndCentralDirRecord().setTotNoOfEntriesInCentralDir(zipModel.getCentralDirectory().getFileHeaders().size());
                    zipModel.getEndCentralDirRecord().setTotNoOfEntriesInCentralDirOnThisDisk(zipModel.getCentralDirectory().getFileHeaders().size());
                    return;
                }
                throw new ZipException("corrupt zip model - getCentralDirectory, cannot update split zip model");
            } catch (ZipException e) {
                throw e;
            } catch (Exception e2) {
                throw new ZipException((Throwable) e2);
            }
        } else {
            throw new ZipException("zip model is null - cannot update end of central directory for split zip model");
        }
    }

    private void updateSplitZip64EndCentralDirLocator(ZipModel zipModel, ArrayList arrayList) throws ZipException {
        if (zipModel == null) {
            throw new ZipException("zip model is null, cannot update split Zip64 end of central directory locator");
        } else if (zipModel.getZip64EndCentralDirLocator() != null) {
            zipModel.getZip64EndCentralDirLocator().setNoOfDiskStartOfZip64EndOfCentralDirRec(0);
            long j = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                j += ((Long) arrayList.get(i)).longValue();
            }
            zipModel.getZip64EndCentralDirLocator().setOffsetZip64EndOfCentralDirRec(zipModel.getZip64EndCentralDirLocator().getOffsetZip64EndOfCentralDirRec() + j);
            zipModel.getZip64EndCentralDirLocator().setTotNumberOfDiscs(1);
        }
    }

    private void updateSplitZip64EndCentralDirRec(ZipModel zipModel, ArrayList arrayList) throws ZipException {
        if (zipModel == null) {
            throw new ZipException("zip model is null, cannot update split Zip64 end of central directory record");
        } else if (zipModel.getZip64EndCentralDirRecord() != null) {
            zipModel.getZip64EndCentralDirRecord().setNoOfThisDisk(0);
            zipModel.getZip64EndCentralDirRecord().setNoOfThisDiskStartOfCentralDir(0);
            zipModel.getZip64EndCentralDirRecord().setTotNoOfEntriesInCentralDirOnThisDisk((long) zipModel.getEndCentralDirRecord().getTotNoOfEntriesInCentralDir());
            long j = 0;
            for (int i = 0; i < arrayList.size(); i++) {
                j += ((Long) arrayList.get(i)).longValue();
            }
            zipModel.getZip64EndCentralDirRecord().setOffsetStartCenDirWRTStartDiskNo(zipModel.getZip64EndCentralDirRecord().getOffsetStartCenDirWRTStartDiskNo() + j);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a A[SYNTHETIC, Splitter:B:38:0x009a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setComment(net.lingala.zip4j.model.ZipModel r6, java.lang.String r7) throws net.lingala.zip4j.exception.ZipException {
        /*
            r5 = this;
            if (r7 == 0) goto L_0x00ae
            if (r6 == 0) goto L_0x00a6
            byte[] r0 = r7.getBytes()
            int r1 = r7.length()
            java.lang.String r2 = "windows-1254"
            boolean r3 = net.lingala.zip4j.util.Zip4jUtil.isSupportedCharset(r2)
            if (r3 == 0) goto L_0x0032
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x002a }
            byte[] r1 = r7.getBytes(r2)     // Catch:{ UnsupportedEncodingException -> 0x002a }
            r0.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x002a }
            byte[] r1 = r0.getBytes(r2)     // Catch:{ UnsupportedEncodingException -> 0x002a }
            int r7 = r0.length()     // Catch:{ UnsupportedEncodingException -> 0x002a }
            r4 = r1
            r1 = r7
            r7 = r0
            r0 = r4
            goto L_0x0032
        L_0x002a:
            byte[] r0 = r7.getBytes()
            int r1 = r7.length()
        L_0x0032:
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r1 > r2) goto L_0x009e
            net.lingala.zip4j.model.EndCentralDirRecord r2 = r6.getEndCentralDirRecord()
            r2.setComment(r7)
            net.lingala.zip4j.model.EndCentralDirRecord r7 = r6.getEndCentralDirRecord()
            r7.setCommentBytes(r0)
            net.lingala.zip4j.model.EndCentralDirRecord r7 = r6.getEndCentralDirRecord()
            r7.setCommentLength(r1)
            r7 = 0
            net.lingala.zip4j.core.HeaderWriter r0 = new net.lingala.zip4j.core.HeaderWriter     // Catch:{ FileNotFoundException -> 0x0091, IOException -> 0x008a }
            r0.<init>()     // Catch:{ FileNotFoundException -> 0x0091, IOException -> 0x008a }
            net.lingala.zip4j.io.SplitOutputStream r1 = new net.lingala.zip4j.io.SplitOutputStream     // Catch:{ FileNotFoundException -> 0x0091, IOException -> 0x008a }
            java.lang.String r2 = r6.getZipFile()     // Catch:{ FileNotFoundException -> 0x0091, IOException -> 0x008a }
            r1.<init>((java.lang.String) r2)     // Catch:{ FileNotFoundException -> 0x0091, IOException -> 0x008a }
            boolean r7 = r6.isZip64Format()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            if (r7 == 0) goto L_0x006d
            net.lingala.zip4j.model.Zip64EndCentralDirRecord r7 = r6.getZip64EndCentralDirRecord()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            long r2 = r7.getOffsetStartCenDirWRTStartDiskNo()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            r1.seek(r2)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            goto L_0x0078
        L_0x006d:
            net.lingala.zip4j.model.EndCentralDirRecord r7 = r6.getEndCentralDirRecord()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            long r2 = r7.getOffsetOfStartOfCentralDir()     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            r1.seek(r2)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
        L_0x0078:
            r0.finalizeZipFileWithoutValidations(r6, r1)     // Catch:{ FileNotFoundException -> 0x0085, IOException -> 0x0082, all -> 0x007f }
            r1.close()     // Catch:{ IOException -> 0x007e }
        L_0x007e:
            return
        L_0x007f:
            r6 = move-exception
            r7 = r1
            goto L_0x0098
        L_0x0082:
            r6 = move-exception
            r7 = r1
            goto L_0x008b
        L_0x0085:
            r6 = move-exception
            r7 = r1
            goto L_0x0092
        L_0x0088:
            r6 = move-exception
            goto L_0x0098
        L_0x008a:
            r6 = move-exception
        L_0x008b:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0088 }
            r0.<init>((java.lang.Throwable) r6)     // Catch:{ all -> 0x0088 }
            throw r0     // Catch:{ all -> 0x0088 }
        L_0x0091:
            r6 = move-exception
        L_0x0092:
            net.lingala.zip4j.exception.ZipException r0 = new net.lingala.zip4j.exception.ZipException     // Catch:{ all -> 0x0088 }
            r0.<init>((java.lang.Throwable) r6)     // Catch:{ all -> 0x0088 }
            throw r0     // Catch:{ all -> 0x0088 }
        L_0x0098:
            if (r7 == 0) goto L_0x009d
            r7.close()     // Catch:{ IOException -> 0x009d }
        L_0x009d:
            throw r6
        L_0x009e:
            net.lingala.zip4j.exception.ZipException r6 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r7 = "comment length exceeds maximum length"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x00a6:
            net.lingala.zip4j.exception.ZipException r6 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r7 = "zipModel is null, cannot update Zip file with comment"
            r6.<init>((java.lang.String) r7)
            throw r6
        L_0x00ae:
            net.lingala.zip4j.exception.ZipException r6 = new net.lingala.zip4j.exception.ZipException
            java.lang.String r7 = "comment is null, cannot update Zip file with comment"
            r6.<init>((java.lang.String) r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.util.ArchiveMaintainer.setComment(net.lingala.zip4j.model.ZipModel, java.lang.String):void");
    }

    public void initProgressMonitorForRemoveOp(ZipModel zipModel, FileHeader fileHeader, ProgressMonitor progressMonitor) throws ZipException {
        if (zipModel == null || fileHeader == null || progressMonitor == null) {
            throw new ZipException("one of the input parameters is null, cannot calculate total work");
        }
        progressMonitor.setCurrentOperation(2);
        progressMonitor.setFileName(fileHeader.getFileName());
        progressMonitor.setTotalWork(calculateTotalWorkForRemoveOp(zipModel, fileHeader));
        progressMonitor.setState(1);
    }

    private long calculateTotalWorkForRemoveOp(ZipModel zipModel, FileHeader fileHeader) throws ZipException {
        return Zip4jUtil.getFileLengh(new File(zipModel.getZipFile())) - fileHeader.getCompressedSize();
    }

    public void initProgressMonitorForMergeOp(ZipModel zipModel, ProgressMonitor progressMonitor) throws ZipException {
        if (zipModel != null) {
            progressMonitor.setCurrentOperation(4);
            progressMonitor.setFileName(zipModel.getZipFile());
            progressMonitor.setTotalWork(calculateTotalWorkForMergeOp(zipModel));
            progressMonitor.setState(1);
            return;
        }
        throw new ZipException("zip model is null, cannot calculate total work for merge op");
    }

    private long calculateTotalWorkForMergeOp(ZipModel zipModel) throws ZipException {
        String str;
        long j = 0;
        if (zipModel.isSplitArchive()) {
            int noOfThisDisk = zipModel.getEndCentralDirRecord().getNoOfThisDisk();
            String zipFile = zipModel.getZipFile();
            for (int i = 0; i <= noOfThisDisk; i++) {
                if (zipModel.getEndCentralDirRecord().getNoOfThisDisk() == 0) {
                    str = zipModel.getZipFile();
                } else {
                    str = new StringBuffer(String.valueOf(zipFile.substring(0, zipFile.lastIndexOf(".")))).append(".z0").append(1).toString();
                }
                j += Zip4jUtil.getFileLengh(new File(str));
            }
        }
        return j;
    }
}
