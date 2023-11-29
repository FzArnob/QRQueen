package com.google.appinventor.components.runtime.util;

import android.os.Build;
import android.util.Log;
import com.google.appinventor.components.common.FileScope;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.PermissionResultHandler;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public abstract class FileOperation implements PermissionResultHandler, Runnable {
    /* access modifiers changed from: package-private */
    public static final String LOG_TAG = "FileOperation";
    protected volatile boolean askedForPermission = false;
    protected final boolean async;
    protected final Component component;
    protected final Form form;
    protected volatile boolean hasPermission = false;
    protected final String method;

    public interface FileInvocation {
        void call(ScopedFile[] scopedFileArr) throws IOException;
    }

    public abstract List<String> getPermissions();

    /* access modifiers changed from: protected */
    public abstract boolean needsExternalStorage();

    /* access modifiers changed from: protected */
    public abstract boolean needsPermission();

    /* access modifiers changed from: protected */
    public abstract void performOperation();

    FileOperation(Form form2, Component component2, String str, boolean z) {
        this.form = form2;
        this.component = component2;
        this.method = str;
        this.async = z;
    }

    public void HandlePermissionResponse(String str, boolean z) {
        this.askedForPermission = true;
        this.hasPermission = z;
        run();
    }

    /* access modifiers changed from: protected */
    public void reportError(final int i, final Object... objArr) {
        this.form.runOnUiThread(new Runnable() {
            public final void run() {
                FileOperation.this.form.dispatchErrorOccurredEvent(FileOperation.this.component, FileOperation.this.method, i, objArr);
            }
        });
    }

    public static class Builder {
        private boolean HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = true;
        private final List<FileInvocation> ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = new ArrayList();
        private boolean async = true;
        private Component component;
        /* access modifiers changed from: private */
        public boolean dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = false;
        private Form form;
        private final LinkedHashMap<ScopedFile, FileAccessMode> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new LinkedHashMap<>();
        private String method;
        private final Set<String> vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = new HashSet();

        public Builder() {
        }

        public Builder(Form form2, Component component2, String str) {
            this.form = form2;
            this.component = component2;
            this.method = str;
        }

        public Builder setForm(Form form2) {
            this.form = form2;
            return this;
        }

        public Builder setComponent(Component component2) {
            this.component = component2;
            return this;
        }

        public Builder setMethod(String str) {
            this.method = str;
            return this;
        }

        public Builder addFile(FileScope fileScope, String str, FileAccessMode fileAccessMode) {
            ScopedFile scopedFile = new ScopedFile(fileScope, str);
            if (scopedFile.getScope() != FileScope.Asset || fileAccessMode == FileAccessMode.READ) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(scopedFile, fileAccessMode);
                String resolveFileName = FileUtil.resolveFileName(this.form, str, fileScope);
                String vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R2 = FileOperation.LOG_TAG;
                Log.d(vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R2, this.method + " resolved " + resolveFileName);
                this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw | FileUtil.needsPermission(this.form, resolveFileName);
                String neededPermission = FileUtil.getNeededPermission(this.form, resolveFileName, fileAccessMode);
                if (neededPermission != null) {
                    this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(neededPermission);
                }
                if (Build.VERSION.SDK_INT >= 33 && scopedFile.getScope() == FileScope.Shared && fileAccessMode == FileAccessMode.READ) {
                    this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.remove("android.permission.READ_EXTERNAL_STORAGE");
                    this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add("android.permission.READ_MEDIA_AUDIO");
                    this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add("android.permission.READ_MEDIA_IMAGES");
                    this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add("android.permission.READ_MEDIA_VIDEO");
                }
                return this;
            }
            this.form.dispatchErrorOccurredEvent(this.component, this.method, ErrorMessages.ERROR_CANNOT_WRITE_ASSET, scopedFile.getFileName());
            throw new StopBlocksExecution();
        }

        public Builder addCommand(FileInvocation fileInvocation) {
            this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR.add(fileInvocation);
            return this;
        }

        public Builder setAsynchronous(boolean z) {
            this.async = z;
            return this;
        }

        public Builder setAskPermission(boolean z) {
            this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = z;
            return this;
        }

        public FileOperation build() {
            return new FileOperation(this.form, this.component, this.method, this.async) {
                public final List<String> getPermissions() {
                    return new ArrayList(Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this));
                }

                /* access modifiers changed from: protected */
                public final void performOperation() {
                    if (Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this) && !Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this).isEmpty()) {
                        Iterator it = Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this).iterator();
                        while (it.hasNext()) {
                            if (!this.form.isDeniedPermission((String) it.next())) {
                                it.remove();
                            }
                        }
                        if (needsPermission()) {
                            String str = FileOperation.LOG_TAG;
                            Log.d(str, this.method + " need permissions: " + Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this));
                            this.form.askPermission(new BulkPermissionRequest(this.component, this.method, (String[]) Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this).toArray(new String[0])) {
                                public final void onGranted() {
                                }
                            });
                            throw new StopBlocksExecution();
                        }
                    }
                    ScopedFile[] scopedFileArr = (ScopedFile[]) Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this).keySet().toArray(new ScopedFile[0]);
                    for (FileInvocation call : Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this)) {
                        try {
                            call.call(scopedFileArr);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                /* access modifiers changed from: protected */
                public final boolean needsPermission() {
                    return !Builder.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Builder.this).isEmpty();
                }

                /* access modifiers changed from: protected */
                public final boolean needsExternalStorage() {
                    return Builder.this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw;
                }
            };
        }
    }

    public final void run() {
        List list;
        if (this.hasPermission) {
            list = Collections.emptyList();
        } else {
            List<String> permissions = getPermissions();
            HashSet hashSet = new HashSet();
            for (String next : permissions) {
                if (this.form.isDeniedPermission(next)) {
                    hashSet.add(next);
                }
            }
            list = new ArrayList(hashSet);
        }
        if (AsynchUtil.isUiThread()) {
            if (needsExternalStorage()) {
                FileUtil.checkExternalStorageWriteable();
            }
            if (list.isEmpty()) {
                this.hasPermission = true;
                if (this.async) {
                    AsynchUtil.runAsynchronously(this);
                } else {
                    performOperation();
                }
            } else if (!this.hasPermission) {
                if (this.askedForPermission) {
                    this.form.dispatchPermissionDeniedEvent(this.component, this.method, (String) list.get(0));
                    this.askedForPermission = false;
                } else {
                    this.askedForPermission = true;
                    this.form.askPermission(new BulkPermissionRequest(this.component, this.method, (String[]) list.toArray(new String[0])) {
                        public final void onGranted() {
                            FileOperation.this.hasPermission = true;
                            FileOperation.this.run();
                        }
                    });
                }
                throw new StopBlocksExecution();
            } else if (this.async) {
                AsynchUtil.runAsynchronously(this);
            } else {
                performOperation();
            }
        } else if (!list.isEmpty()) {
            this.hasPermission = false;
            this.askedForPermission = false;
            this.form.runOnUiThread(this);
        } else {
            performOperation();
        }
    }
}
