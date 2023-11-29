package com.google.appinventor.components.runtime;

import android.net.Uri;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.Continuation;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileAccessMode;
import com.google.appinventor.components.runtime.util.FileOperation;
import com.google.appinventor.components.runtime.util.FileStreamReadOperation;
import com.google.appinventor.components.runtime.util.FileStreamWriteOperation;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FileWriteOperation;
import com.google.appinventor.components.runtime.util.IOUtils;
import com.google.appinventor.components.runtime.util.ScopedFile;
import com.google.appinventor.components.runtime.util.SingleFileOperation;
import com.google.appinventor.components.runtime.util.Synchronizer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.InternalZipConstants;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component for storing and retrieving files. Use this component to write or read files on your device. The default behaviour is to write files to the private data directory associated with your App. The Companion is special cased to write files to a public directory for debugging. Use the More information link to read more about how the File component uses paths and scopes to manage access to files.", iconName = "images/file.png", nonVisible = true, version = 8)
@UsesLibraries({"zip4j.jar"})
public class File extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "FileComponent";
    private FileScope scope = FileScope.App;

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
    @SimpleProperty(userVisible = false)
    public void ReadPermission(boolean z) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE"})
    @SimpleProperty(userVisible = false)
    public void WritePermission(boolean z) {
    }

    public File(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        DefaultScope(FileScope.App);
    }

    @DesignerProperty(defaultValue = "App", editorType = "file_scope")
    @SimpleProperty(userVisible = false)
    public void DefaultScope(FileScope fileScope) {
        this.scope = fileScope;
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void LegacyMode(boolean z) {
        this.scope = z ? FileScope.Legacy : FileScope.App;
    }

    @Deprecated
    @SimpleProperty(description = "Allows app to access files from the root of the external storage directory (legacy mode).")
    public boolean LegacyMode() {
        return this.scope == FileScope.Legacy;
    }

    @SimpleProperty
    public void Scope(FileScope fileScope) {
        this.scope = fileScope;
    }

    @SimpleProperty
    public FileScope Scope() {
        return this.scope;
    }

    @SimpleFunction
    public void MakeDirectory(FileScope fileScope, String str, Continuation<Boolean> continuation) {
        if (fileScope == FileScope.Asset) {
            this.form.dispatchErrorOccurredEvent(this, "MakeDirectory", ErrorMessages.ERROR_CANNOT_MAKE_DIRECTORY, str);
            return;
        }
        final Continuation<Boolean> continuation2 = continuation;
        new SingleFileOperation(this, this.form, this, "MakeDirectory", str, fileScope, FileAccessMode.WRITE) {
            private /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
            }

            public final void processFile(ScopedFile scopedFile) {
                java.io.File resolve = scopedFile.resolve(this.form);
                if (resolve.exists()) {
                    if (resolve.isDirectory()) {
                        onSuccess();
                        return;
                    }
                    reportError(ErrorMessages.ERROR_FILE_EXISTS_AT_PATH, resolve.getAbsolutePath());
                } else if (resolve.mkdirs()) {
                    onSuccess();
                } else {
                    reportError(ErrorMessages.ERROR_CANNOT_MAKE_DIRECTORY, resolve.getAbsolutePath());
                }
            }

            private void onSuccess() {
                this.form.runOnUiThread(new Runnable() {
                    public final void run() {
                        continuation2.call(Boolean.TRUE);
                    }
                });
            }
        }.run();
    }

    @SimpleFunction
    public void RemoveDirectory(FileScope fileScope, String str, final boolean z, Continuation<Boolean> continuation) {
        if (fileScope == FileScope.Asset) {
            this.form.dispatchErrorOccurredEvent(this, "RemoveDirectory", ErrorMessages.ERROR_CANNOT_REMOVE_DIRECTORY, str);
            return;
        }
        final Synchronizer synchronizer = new Synchronizer();
        new FileOperation.Builder(this.form, this, "RemoveDirectory").addFile(fileScope, str, FileAccessMode.WRITE).addCommand(new FileOperation.FileInvocation() {
            public final void call(ScopedFile[] scopedFileArr) {
                try {
                    synchronizer.wakeup(Boolean.valueOf(FileUtil.removeDirectory(scopedFileArr[0].resolve(File.this.form), z)));
                } catch (Exception e) {
                    synchronizer.caught(e);
                }
            }
        }).build().run();
        AsynchUtil.finish(synchronizer, continuation);
    }

    @SimpleFunction
    public void ListDirectory(FileScope fileScope, String str, Continuation<List<String>> continuation) {
        if (fileScope == FileScope.Asset && !this.form.isRepl()) {
            try {
                continuation.call(FileUtil.listDirectory(this.form, new ScopedFile(fileScope, str)));
            } catch (IOException unused) {
                this.form.dispatchErrorOccurredEvent(this, "ListDirectory", ErrorMessages.ERROR_CANNOT_LIST_DIRECTORY, str);
            }
        } else if (fileScope != FileScope.Shared || !str.startsWith("content:")) {
            if (!str.contains(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                str = str + InternalZipConstants.ZIP_FILE_SEPARATOR;
            }
            final Synchronizer synchronizer = new Synchronizer();
            new FileOperation.Builder(this.form, this, "ListDirectory").setAskPermission(true).setAsynchronous(true).addFile(fileScope, str, FileAccessMode.READ).addCommand(new FileOperation.FileInvocation() {
                public final void call(ScopedFile[] scopedFileArr) throws IOException {
                    Log.d(File.LOG_TAG, "Listing directory " + scopedFileArr[0]);
                    List<String> listDirectory = FileUtil.listDirectory(File.this.form, scopedFileArr[0]);
                    if (listDirectory == null) {
                        listDirectory = Collections.emptyList();
                    }
                    synchronizer.wakeup(listDirectory);
                }
            }).build().run();
            AsynchUtil.finish(synchronizer, continuation);
        } else {
            DocumentFile[] listFiles = DocumentFile.fromTreeUri(this.form, Uri.parse(str)).listFiles();
            ArrayList arrayList = new ArrayList();
            for (DocumentFile name : listFiles) {
                arrayList.add(name.getName());
            }
            continuation.call(arrayList);
        }
    }

    @Deprecated
    @SimpleFunction(description = "Check whether the path is a directory. Use the other IsDirectory function instead.")
    public boolean IsDirectory(String str) {
        java.io.File externalFile = FileUtil.getExternalFile(this.form, str, FileScope.Legacy);
        return externalFile.exists() && externalFile.isDirectory();
    }

    @SimpleFunction
    public void IsDirectory(FileScope fileScope, String str, Continuation<Boolean> continuation) {
        if (fileScope != FileScope.Asset || this.form.isRepl()) {
            final Synchronizer synchronizer = new Synchronizer();
            new FileOperation.Builder(this.form, this, "IsDirectory").addFile(fileScope, str, FileAccessMode.READ).addCommand(new FileOperation.FileInvocation() {
                public final void call(ScopedFile[] scopedFileArr) {
                    Log.d(File.LOG_TAG, "IsDirectory " + scopedFileArr[0]);
                    synchronizer.wakeup(Boolean.valueOf(scopedFileArr[0].resolve(File.this.form).isDirectory()));
                }
            }).build().run();
            AsynchUtil.finish(synchronizer, continuation);
            return;
        }
        try {
            String[] list = this.form.getAssets().list(str);
            Log.d(LOG_TAG, "contents of " + str + " = " + Arrays.toString(list));
            continuation.call(Boolean.valueOf(list != null && list.length > 0));
        } catch (IOException unused) {
            this.form.dispatchErrorOccurredEvent(this, "IsDirectory", ErrorMessages.ERROR_DIRECTORY_DOES_NOT_EXIST, str);
        }
    }

    @SimpleFunction
    public void CopyFile(FileScope fileScope, String str, FileScope fileScope2, String str2, Continuation<Boolean> continuation) {
        if (fileScope2 != FileScope.Asset) {
            final Synchronizer synchronizer = new Synchronizer();
            new FileOperation.Builder(this.form, this, "CopyFile").addFile(fileScope, str, FileAccessMode.READ).addFile(fileScope2, str2, FileAccessMode.WRITE).addCommand(new FileOperation.FileInvocation() {
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.io.InputStream} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: java.io.OutputStream} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.io.OutputStream} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final void call(com.google.appinventor.components.runtime.util.ScopedFile[] r12) {
                    /*
                        r11 = this;
                        java.lang.String r0 = "FileComponent"
                        r1 = 1
                        r2 = r12[r1]
                        java.lang.String r2 = r2.getFileName()
                        java.lang.String r3 = "content:"
                        boolean r2 = r2.startsWith(r3)
                        java.lang.String r3 = "CopyFile"
                        r4 = 0
                        if (r2 != 0) goto L_0x004c
                        r2 = r12[r1]
                        com.google.appinventor.components.runtime.File r5 = com.google.appinventor.components.runtime.File.this
                        com.google.appinventor.components.runtime.Form r5 = r5.form
                        java.io.File r2 = r2.resolve(r5)
                        java.io.File r2 = r2.getParentFile()
                        boolean r5 = r2.exists()
                        if (r5 != 0) goto L_0x004c
                        boolean r5 = r2.mkdirs()
                        if (r5 != 0) goto L_0x004c
                        com.google.appinventor.components.runtime.File r12 = com.google.appinventor.components.runtime.File.this
                        com.google.appinventor.components.runtime.Form r12 = r12.form
                        com.google.appinventor.components.runtime.File r0 = com.google.appinventor.components.runtime.File.this
                        r5 = 2108(0x83c, float:2.954E-42)
                        java.lang.Object[] r1 = new java.lang.Object[r1]
                        java.lang.String r2 = r2.getAbsolutePath()
                        r1[r4] = r2
                        r12.dispatchErrorOccurredEvent(r0, r3, r5, r1)
                        com.google.appinventor.components.runtime.util.Synchronizer r12 = r0
                        java.io.IOException r0 = new java.io.IOException
                        r0.<init>()
                        r12.caught(r0)
                        return
                    L_0x004c:
                        r2 = 0
                        com.google.appinventor.components.runtime.File r5 = com.google.appinventor.components.runtime.File.this     // Catch:{ IOException -> 0x007f, all -> 0x007c }
                        com.google.appinventor.components.runtime.Form r5 = r5.form     // Catch:{ IOException -> 0x007f, all -> 0x007c }
                        r6 = r12[r4]     // Catch:{ IOException -> 0x007f, all -> 0x007c }
                        java.io.InputStream r5 = com.google.appinventor.components.runtime.util.FileUtil.openForReading(r5, r6)     // Catch:{ IOException -> 0x007f, all -> 0x007c }
                        com.google.appinventor.components.runtime.File r6 = com.google.appinventor.components.runtime.File.this     // Catch:{ IOException -> 0x0077, all -> 0x0072 }
                        com.google.appinventor.components.runtime.Form r6 = r6.form     // Catch:{ IOException -> 0x0077, all -> 0x0072 }
                        r7 = r12[r1]     // Catch:{ IOException -> 0x0077, all -> 0x0072 }
                        java.io.OutputStream r2 = com.google.appinventor.components.runtime.util.FileUtil.openForWriting(r6, r7)     // Catch:{ IOException -> 0x0077, all -> 0x0072 }
                        com.google.appinventor.components.runtime.util.FileUtil.copy(r5, r2)     // Catch:{ IOException -> 0x0077, all -> 0x0072 }
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r5)
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r2)
                        com.google.appinventor.components.runtime.util.Synchronizer r12 = r0
                        java.lang.Boolean r0 = java.lang.Boolean.TRUE
                        r12.wakeup(r0)
                        return
                    L_0x0072:
                        r12 = move-exception
                        r10 = r5
                        r5 = r2
                        r2 = r10
                        goto L_0x00a8
                    L_0x0077:
                        r6 = move-exception
                        r10 = r5
                        r5 = r2
                        r2 = r10
                        goto L_0x0081
                    L_0x007c:
                        r12 = move-exception
                        r5 = r2
                        goto L_0x00a8
                    L_0x007f:
                        r6 = move-exception
                        r5 = r2
                    L_0x0081:
                        java.lang.String r7 = "Unable to copy file"
                        android.util.Log.w(r0, r7, r6)     // Catch:{ all -> 0x00a7 }
                        com.google.appinventor.components.runtime.File r7 = com.google.appinventor.components.runtime.File.this     // Catch:{ all -> 0x00a7 }
                        com.google.appinventor.components.runtime.Form r7 = r7.form     // Catch:{ all -> 0x00a7 }
                        com.google.appinventor.components.runtime.File r8 = com.google.appinventor.components.runtime.File.this     // Catch:{ all -> 0x00a7 }
                        r9 = 1602(0x642, float:2.245E-42)
                        java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00a7 }
                        r12 = r12[r4]     // Catch:{ all -> 0x00a7 }
                        java.lang.String r12 = r12.getFileName()     // Catch:{ all -> 0x00a7 }
                        r1[r4] = r12     // Catch:{ all -> 0x00a7 }
                        r7.dispatchErrorOccurredEvent(r8, r3, r9, r1)     // Catch:{ all -> 0x00a7 }
                        com.google.appinventor.components.runtime.util.Synchronizer r12 = r0     // Catch:{ all -> 0x00a7 }
                        r12.caught(r6)     // Catch:{ all -> 0x00a7 }
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r2)
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r5)
                        return
                    L_0x00a7:
                        r12 = move-exception
                    L_0x00a8:
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r2)
                        com.google.appinventor.components.runtime.util.IOUtils.closeQuietly(r0, r5)
                        throw r12
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.File.AnonymousClass8.call(com.google.appinventor.components.runtime.util.ScopedFile[]):void");
                }
            }).build().run();
            AsynchUtil.finish(synchronizer, continuation);
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "CopyFile", ErrorMessages.ERROR_CANNOT_WRITE_ASSET, str2);
        throw new StopBlocksExecution();
    }

    @SimpleFunction
    public void MoveFile(FileScope fileScope, String str, FileScope fileScope2, String str2, Continuation<Boolean> continuation) {
        if (fileScope == FileScope.Asset) {
            this.form.dispatchErrorOccurredEvent(this, "MoveFile", ErrorMessages.ERROR_CANNOT_DELETE_ASSET, str);
        } else if (fileScope2 == FileScope.Asset) {
            this.form.dispatchErrorOccurredEvent(this, "MoveFile", ErrorMessages.ERROR_CANNOT_WRITE_ASSET, str2);
        } else {
            final Synchronizer synchronizer = new Synchronizer();
            new FileOperation.Builder(this.form, this, "MoveFile").addFile(fileScope, str, FileAccessMode.READ).addFile(fileScope2, str2, FileAccessMode.WRITE).addCommand(new FileOperation.FileInvocation() {
                public final void call(ScopedFile[] scopedFileArr) {
                    try {
                        synchronizer.wakeup(Boolean.valueOf(FileUtil.moveFile(File.this.form, scopedFileArr[0], scopedFileArr[1])));
                    } catch (IOException unused) {
                        synchronizer.wakeup(Boolean.FALSE);
                    }
                }
            }).build().run();
            AsynchUtil.finish(synchronizer, continuation);
        }
    }

    @SimpleFunction
    public String MakeFullPath(FileScope fileScope, String str) {
        return FileUtil.resolveFileName(this.form, str, fileScope);
    }

    @SimpleFunction(description = "Saves text to a file. If the filename begins with a slash (/) the file is written to the sdcard. For example writing to /myFile.txt will write the file to /sdcard/myFile.txt. If the filename does not start with a slash, it will be written in the programs private data directory where it will not be accessible to other programs on the phone. There is a special exception for the AI Companion where these files are written to /sdcard/AppInventor/data to facilitate debugging. Note that this block will overwrite a file if it already exists.\n\nIf you want to add content to a file use the append block.")
    public void SaveFile(String str, String str2) {
        write(str2, "SaveFile", str, false);
    }

    @SimpleFunction(description = "Appends text to the end of a file storage, creating the file if it does not exist. See the help text under SaveFile for information about where files are written.")
    public void AppendToFile(String str, String str2) {
        write(str2, "AppendToFile", str, true);
    }

    @SimpleFunction(description = "Reads text from a file in storage. Prefix the filename with / to read from a specific file on the SD card. for instance /myFile.txt will read the file /sdcard/myFile.txt. To read assets packaged with an application (also works for the Companion) start the filename with // (two slashes). If a filename does not start with a slash, it will be read from the applications private storage (for packaged apps) and from /sdcard/AppInventor/data for the Companion.")
    public void ReadFrom(String str) {
        try {
            new FileStreamReadOperation(this.form, this, "ReadFrom", str, this.scope) {
                public final boolean process(String str) {
                    final String normalizeNewLines = IOUtils.normalizeNewLines(str);
                    this.form.runOnUiThread(new Runnable() {
                        public final void run() {
                            File.this.GotText(normalizeNewLines);
                        }
                    });
                    return true;
                }

                public final void onError(IOException iOException) {
                    if (iOException instanceof FileNotFoundException) {
                        Log.e(File.LOG_TAG, "FileNotFoundException", iOException);
                        this.form.dispatchErrorOccurredEvent(File.this, "ReadFrom", ErrorMessages.ERROR_CANNOT_FIND_FILE, this.fileName);
                        return;
                    }
                    Log.e(File.LOG_TAG, "IOException", iOException);
                    this.form.dispatchErrorOccurredEvent(File.this, "ReadFrom", ErrorMessages.ERROR_CANNOT_READ_FILE, this.fileName);
                }
            }.run();
        } catch (StopBlocksExecution unused) {
        }
    }

    @SimpleFunction(description = "Deletes a file from storage. Prefix the filename with / to delete a specific file in the SD card, for instance /myFile.txt. will delete the file /sdcard/myFile.txt. If the file does not begin with a /, then the file located in the programs private storage will be deleted. Starting the file with // is an error because assets files cannot be deleted.")
    public void Delete(String str) {
        if (str.startsWith("//")) {
            this.form.dispatchErrorOccurredEvent(this, "Delete", ErrorMessages.ERROR_CANNOT_DELETE_ASSET, str);
            return;
        }
        try {
            new FileWriteOperation(this.form, this, "Delete", str, this.scope) {
                public final void processFile(ScopedFile scopedFile) {
                    java.io.File resolve = scopedFile.resolve(this.form);
                    if (resolve.exists() && !resolve.delete()) {
                        this.form.dispatchErrorOccurredEvent(File.this, "Delete", ErrorMessages.ERROR_CANNOT_DELETE_FILE, this.fileName);
                    }
                }
            }.run();
        } catch (StopBlocksExecution unused) {
        }
    }

    @SimpleEvent(description = "Event indicating that the contents from the file have been read.")
    public void GotText(String str) {
        EventDispatcher.dispatchEvent(this, "GotText", str);
    }

    @SimpleEvent(description = "Event indicating that the contents of the file have been written.")
    public void AfterFileSaved(String str) {
        EventDispatcher.dispatchEvent(this, "AfterFileSaved", str);
    }

    @Deprecated
    @SimpleFunction(description = "Move a file. You can not move asset files. Use MoveFile instead.")
    public boolean Move(String str, String str2) {
        if (str.startsWith("//") || str2.startsWith("//")) {
            return false;
        }
        return FileUtil.getExternalFile(this.form, str, FileScope.Legacy).renameTo(FileUtil.getExternalFile(this.form, str2, FileScope.Legacy));
    }

    @Deprecated
    @SimpleFunction(description = "Copy a file. If input path started with two // (slashes) then it's a asset file. You can not copy a file into the assets directory. Use CopyFile instead.")
    public void Copy(String str, String str2) {
        if (!str2.startsWith("//")) {
            CopyFile(FileScope.Legacy, str, FileScope.Legacy, str2, new Continuation<Boolean>() {
                public final /* bridge */ /* synthetic */ void call(Object obj) {
                }
            });
        }
    }

    @Deprecated
    @SimpleFunction(description = "Check whether a file exists. If file path started with two // (slashes) then it means you would check if a asset file exists. Use the other Exists function instead.")
    public boolean Exists(String str) {
        return FileUtil.getExternalFile(this.form, str, FileScope.Legacy).exists();
    }

    @SimpleFunction
    public void Exists(FileScope fileScope, String str, Continuation<Boolean> continuation) {
        final Synchronizer synchronizer = new Synchronizer();
        new FileOperation.Builder(this.form, this, "Exists").addFile(fileScope, str, FileAccessMode.READ).addCommand(new FileOperation.FileInvocation() {
            public final void call(ScopedFile[] scopedFileArr) {
                synchronizer.wakeup(Boolean.valueOf(scopedFileArr[0].resolve(File.this.form).exists()));
            }
        }).build().run();
        AsynchUtil.finish(synchronizer, continuation);
    }

    @SimpleFunction(description = "Get file size")
    public long FileSize(String str) {
        java.io.File externalFile = FileUtil.getExternalFile(this.form, str, FileScope.Legacy);
        if (!externalFile.exists() || !externalFile.isFile()) {
            return -1;
        }
        return externalFile.length();
    }

    @SimpleFunction(description = "Get total space")
    public long GetTotalSpace(String str) {
        return FileUtil.getExternalFile(this.form, str, FileScope.Legacy).getTotalSpace();
    }

    @SimpleFunction(description = "Get Free Space")
    public long GetFreeSpace(String str) {
        return FileUtil.getExternalFile(this.form, str, FileScope.Legacy).getFreeSpace();
    }

    @SimpleFunction(description = "Get file name")
    public String GetFileName(String str) {
        return FileUtil.getExternalFile(this.form, str, FileScope.Legacy).getName();
    }

    @SimpleFunction(description = "Check whether the path is a file")
    public boolean IsFile(String str) {
        java.io.File externalFile = FileUtil.getExternalFile(this.form, str, FileScope.Legacy);
        return externalFile.exists() && externalFile.isFile();
    }

    @SimpleEvent(description = "Event indicating that the zip file have been created.")
    public void AfterZip() {
        EventDispatcher.dispatchEvent(this, "AfterZip", new Object[0]);
    }

    @SimpleEvent(description = "Event indicating that the zip file have been created.")
    public void AfterUnzip() {
        EventDispatcher.dispatchEvent(this, "AfterUnzip", new Object[0]);
    }

    @SimpleEvent(description = "Event indicating that there was any failure on zip or unzip a file.")
    public void OnZipFailure(String str) {
        EventDispatcher.dispatchEvent(this, "OnZipFailure", str);
    }

    @SimpleFunction(description = "Create a zip file with or without a password.")
    public void Zip(String str, String str2, String str3) {
        try {
            ZipParameters zipParameters = new ZipParameters();
            zipParameters.setCompressionMethod(8);
            zipParameters.setCompressionLevel(5);
            if (str3.length() > 0) {
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(99);
                zipParameters.setAesKeyStrength(3);
                zipParameters.setPassword(str3);
            }
            ZipFile zipFile = new ZipFile(str2);
            java.io.File file = new java.io.File(str);
            if (file.isFile()) {
                zipFile.addFile(file, zipParameters);
            } else if (file.isDirectory()) {
                zipFile.addFolder(file, zipParameters);
            }
            AfterZip();
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            OnZipFailure(e.getMessage());
        }
    }

    @SimpleFunction(description = "Unzip a file with or without a password. If you dont need a passwort then let it empty.")
    public void Unzip(String str, String str2, String str3) {
        try {
            ZipFile zipFile = new ZipFile(str);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(str3);
            }
            zipFile.extractAll(str2);
            AfterUnzip();
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
            OnZipFailure(e.getMessage());
        }
    }

    @Deprecated
    @SimpleFunction(description = "Create a new directory. Use MakeDirectory instead.")
    public void CreateDirectory(String str) {
        MakeDirectory(FileScope.Legacy, str, new Continuation<Boolean>() {
            public final /* synthetic */ void call(Object obj) {
                File.this.DirectoryCreated(((Boolean) obj).booleanValue());
            }
        });
    }

    @SimpleEvent(description = "Event indicating that there was a directory created. The return value is 'true' or 'false'.")
    @Deprecated
    public void DirectoryCreated(boolean z) {
        EventDispatcher.dispatchEvent(this, "DirectoryCreated", Boolean.valueOf(z));
    }

    private void write(String str, String str2, String str3, boolean z) {
        if (str.startsWith("//")) {
            this.form.dispatchErrorOccurredEvent(this, str2, ErrorMessages.ERROR_CANNOT_WRITE_ASSET, str);
            return;
        }
        if (str.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            FileUtil.checkExternalStorageWriteable();
        }
        try {
            final String str4 = str3;
            final String str5 = str;
            new FileStreamWriteOperation(this, this.form, this, str2, str, this.scope, z) {
                final /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r10;
                }

                public final void processFile(ScopedFile scopedFile) {
                    boolean z;
                    java.io.File resolve = scopedFile.resolve(this.form);
                    if (!resolve.exists()) {
                        try {
                            IOUtils.mkdirs(resolve);
                            z = resolve.createNewFile();
                        } catch (IOException unused) {
                            Log.e(File.LOG_TAG, "Unable to create file " + resolve.getAbsolutePath());
                            z = false;
                        }
                        if (!z) {
                            this.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.method, ErrorMessages.ERROR_CANNOT_CREATE_FILE, resolve.getAbsolutePath());
                            return;
                        }
                    }
                    super.processFile(scopedFile);
                }

                public final boolean process(OutputStreamWriter outputStreamWriter) throws IOException {
                    outputStreamWriter.write(str4);
                    outputStreamWriter.flush();
                    this.form.runOnUiThread(new Runnable() {
                        public final void run() {
                            AnonymousClass4.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterFileSaved(str5);
                        }
                    });
                    return true;
                }

                public final void onError(IOException iOException) {
                    String str;
                    super.onError(iOException);
                    if (getFile() == null) {
                        str = getScopedFile().getFileName();
                    } else {
                        str = getFile().getAbsolutePath();
                    }
                    this.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.method, ErrorMessages.ERROR_CANNOT_WRITE_TO_FILE, str);
                }
            }.run();
        } catch (StopBlocksExecution unused) {
        }
    }
}
