package com.google.appinventor.components.runtime;

import android.os.AsyncTask;
import android.util.Log;
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
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "write in ode", iconName = "images/ftp.png", nonVisible = true, version = 2)
@UsesLibraries({"commons-net.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"})
public class MakeroidFtp extends AndroidNonvisibleComponent implements Component {
    private int ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = 21;
    /* access modifiers changed from: private */
    public String D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = "";
    private String FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = "ftp.example.org";
    private String MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl = InternalZipConstants.ZIP_FILE_SEPARATOR;
    private FTPClient hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new FTPClient();
    /* access modifiers changed from: private */
    public String sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = "Your Username";

    public MakeroidFtp(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Log.d("Makeroid Ftp", "Makeroid Ftp Created");
    }

    @DesignerProperty(defaultValue = "/", editorType = "string")
    @SimpleProperty(description = "Set the ftp working dir.")
    public void WorkingDirectory(String str) {
        this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl = str;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.changeWorkingDirectory(str);
        } catch (Exception e) {
            Log.e("Makeroid Ftp", String.valueOf(e));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the ftp working dir.")
    public String WorkingDirectory() {
        return this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl;
    }

    @DesignerProperty(defaultValue = "ftp.example.org", editorType = "string")
    @SimpleProperty(description = "Set the ftp server url.")
    public void FtpServer(String str) {
        this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the ftp server url.")
    public String FtpServer() {
        return this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi;
    }

    @DesignerProperty(defaultValue = "21", editorType = "integer")
    @SimpleProperty(description = "Set the ftp port number.")
    public void Port(int i) {
        this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the ftp port number.")
    public int Port() {
        return this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio;
    }

    @DesignerProperty(defaultValue = "Your Username", editorType = "string")
    @SimpleProperty(description = "Set the username to login into the ftp server.")
    public void Username(String str) {
        this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the username.")
    public String Username() {
        return this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the password to login into the ftp server.")
    public void Password(String str) {
        this.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the password.")
    public String Password() {
        return this.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk;
    }

    @SimpleFunction(description = "Make/create a directory on the ftp server.")
    public void makeDir(String str) {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.makeDirectory(str);
        } catch (Exception e) {
            Log.e("Makeroid Ftp", "mkdiRd" + e.getMessage());
        }
    }

    @SimpleFunction(description = "Delete a directory on the ftp server.")
    public void deleteDir(String str) {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeDirectory(str);
        } catch (Exception e) {
            Log.e("Makeroid Ftp", "mkdiRd" + e.getMessage());
        }
    }

    @SimpleFunction(description = "Get a list of files in a directory. Returns a empty list if a error occurs.")
    public YailList GetListOfFiles(String str) {
        File file = new File(str);
        ArrayList arrayList = new ArrayList();
        if (!file.exists() || !file.isDirectory()) {
            return YailList.makeEmptyList();
        }
        try {
            for (FTPFile name : this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.listFiles(str)) {
                arrayList.add(name.getName());
            }
            return YailList.makeList(arrayList.toArray());
        } catch (Exception unused) {
            return YailList.makeEmptyList();
        }
    }

    @SimpleFunction(description = "Start the connection to the ftp server.")
    public void Connect() {
        new a(this, (byte) 0).execute(new Void[0]);
    }

    class a extends AsyncTask<Void, Void, Boolean> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            MakeroidFtp.this.ConnectionStatus(((Boolean) obj).booleanValue());
        }

        private a() {
        }

        /* synthetic */ a(MakeroidFtp makeroidFtp, byte b) {
            this();
        }

        private Boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            boolean z;
            try {
                MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this).connect(MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this), MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this));
            } catch (Exception e) {
                try {
                    MakeroidFtp makeroidFtp = MakeroidFtp.this;
                    makeroidFtp.ConnectError(e.getMessage());
                    Log.e("Makeroid Ftp", String.valueOf(e));
                } catch (Exception e2) {
                    Log.e("Makeroid Ftp", String.valueOf(e2));
                    MakeroidFtp makeroidFtp2 = MakeroidFtp.this;
                    makeroidFtp2.ConnectError(e2.getMessage());
                    z = false;
                }
            }
            z = MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this).login(MakeroidFtp.this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA, MakeroidFtp.this.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk);
            return Boolean.valueOf(z);
        }
    }

    @SimpleFunction(description = "Disconnect the current connection.")
    public void Disconnect() {
        new b(this, (byte) 0).execute(new Void[0]);
    }

    class b extends AsyncTask<Void, Void, Boolean> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            MakeroidFtp.this.ConnectionStatus(((Boolean) obj).booleanValue());
        }

        private b() {
        }

        /* synthetic */ b(MakeroidFtp makeroidFtp, byte b) {
            this();
        }

        private Boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            boolean z;
            try {
                MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this).disconnect();
                z = false;
            } catch (Exception e) {
                Log.e("Makeroid Ftp", String.valueOf(e));
                MakeroidFtp makeroidFtp = MakeroidFtp.this;
                makeroidFtp.DisconnectError(e.getMessage());
                z = true;
            }
            return Boolean.valueOf(z);
        }
    }

    @SimpleFunction(description = "Start uploading a file.")
    public void UploadFile(final String str, final String str2) {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        new d(MakeroidFtp.this, (byte) 0).execute(new String[]{str, str2});
                    } catch (PermissionException e) {
                        MakeroidFtp.this.form.dispatchPermissionDeniedEvent((Component) MakeroidFtp.this, "UploadFile", e);
                    } catch (Exception e2) {
                        Log.e("Makeroid Ftp", String.valueOf(e2));
                    }
                } else {
                    MakeroidFtp.this.form.dispatchPermissionDeniedEvent((Component) MakeroidFtp.this, "DownloadFile", str);
                }
            }
        });
    }

    class d extends AsyncTask<String, Void, String> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (str.contains("None")) {
                MakeroidFtp.this.UploadDone();
            } else {
                MakeroidFtp.this.UploadError(str);
            }
        }

        private d() {
        }

        /* synthetic */ d(MakeroidFtp makeroidFtp, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            try {
                File file = new File(strArr[0]);
                MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this).setFileType(2);
                FileInputStream fileInputStream = new FileInputStream(file);
                boolean storeFile = MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidFtp.this).storeFile(strArr[1], fileInputStream);
                fileInputStream.close();
                return storeFile ? "None" : "";
            } catch (PermissionException e) {
                MakeroidFtp.this.form.dispatchPermissionDeniedEvent((Component) MakeroidFtp.this, "UploadFile", e);
                String str = e.getMessage();
                Log.e("Makeroid Ftp", str);
                return str;
            } catch (Exception e2) {
                String str2 = e2.getMessage();
                Log.e("Makeroid Ftp", str2);
                return str2;
            }
        }
    }

    @SimpleFunction(description = "Start downloading a file.")
    public void DownloadFile(final String str, final String str2) {
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        new c(MakeroidFtp.this, (byte) 0).execute(new String[]{str, str2});
                    } catch (PermissionException e) {
                        MakeroidFtp.this.form.dispatchPermissionDeniedEvent((Component) MakeroidFtp.this, "DownloadFile", e);
                    } catch (Exception e2) {
                        Log.e("Makeroid Ftp", String.valueOf(e2));
                    }
                } else {
                    MakeroidFtp.this.form.dispatchPermissionDeniedEvent((Component) MakeroidFtp.this, "DownloadFile", str);
                }
            }
        });
    }

    class c extends AsyncTask<String, Void, String> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (str.contains("None")) {
                MakeroidFtp.this.DownloadDone();
            } else {
                MakeroidFtp.this.DownloadError(str);
            }
        }

        private c() {
        }

        /* synthetic */ c(MakeroidFtp makeroidFtp, byte b) {
            this();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x007b A[SYNTHETIC, Splitter:B:28:0x007b] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4 A[SYNTHETIC, Splitter:B:37:0x00b4] */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00c9 A[SYNTHETIC, Splitter:B:42:0x00c9] */
        /* JADX WARNING: Removed duplicated region for block: B:53:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x0062=Splitter:B:25:0x0062, B:34:0x0099=Splitter:B:34:0x0099} */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r7) {
            /*
                r6 = this;
                java.lang.String r0 = "DownloadFile"
                java.lang.String r1 = "Makeroid Ftp"
                java.io.File r2 = new java.io.File     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
                r3 = 1
                r3 = r7[r3]     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
                r2.<init>(r3)     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
                java.io.File r3 = r2.getParentFile()     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
                boolean r4 = r3.exists()     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
                if (r4 != 0) goto L_0x0019
                r3.mkdir()     // Catch:{ PermissionException -> 0x00f2, Exception -> 0x00d9 }
            L_0x0019:
                r3 = 0
                java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ PermissionException -> 0x0098, Exception -> 0x0061 }
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ PermissionException -> 0x0098, Exception -> 0x0061 }
                r5.<init>(r2)     // Catch:{ PermissionException -> 0x0098, Exception -> 0x0061 }
                r4.<init>(r5)     // Catch:{ PermissionException -> 0x0098, Exception -> 0x0061 }
                com.google.appinventor.components.runtime.MakeroidFtp r2 = com.google.appinventor.components.runtime.MakeroidFtp.this     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                org.apache.commons.net.ftp.FTPClient r2 = com.google.appinventor.components.runtime.MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.MakeroidFtp) r2)     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                r3 = 2
                r2.setFileType(r3)     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                com.google.appinventor.components.runtime.MakeroidFtp r2 = com.google.appinventor.components.runtime.MakeroidFtp.this     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                org.apache.commons.net.ftp.FTPClient r2 = com.google.appinventor.components.runtime.MakeroidFtp.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.MakeroidFtp) r2)     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                r3 = 0
                r7 = r7[r3]     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                boolean r7 = r2.retrieveFile(r7, r4)     // Catch:{ PermissionException -> 0x005c, Exception -> 0x0059, all -> 0x0055 }
                if (r7 == 0) goto L_0x0040
                java.lang.String r7 = "None"
                goto L_0x0042
            L_0x0040:
                java.lang.String r7 = ""
            L_0x0042:
                r4.close()     // Catch:{ Exception -> 0x0047 }
                goto L_0x00c6
            L_0x0047:
                r7 = move-exception
                java.lang.String r0 = java.lang.String.valueOf(r7)
                android.util.Log.e(r1, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                goto L_0x008c
            L_0x0055:
                r7 = move-exception
                r3 = r4
                goto L_0x00c7
            L_0x0059:
                r7 = move-exception
                r3 = r4
                goto L_0x0062
            L_0x005c:
                r7 = move-exception
                r3 = r4
                goto L_0x0099
            L_0x005f:
                r7 = move-exception
                goto L_0x00c7
            L_0x0061:
                r7 = move-exception
            L_0x0062:
                java.lang.String r0 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x005f }
                android.util.Log.e(r1, r0)     // Catch:{ all -> 0x005f }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
                r0.<init>()     // Catch:{ all -> 0x005f }
                java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x005f }
                r0.append(r7)     // Catch:{ all -> 0x005f }
                java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x005f }
                if (r3 == 0) goto L_0x00c6
                r3.close()     // Catch:{ Exception -> 0x007f }
                goto L_0x00c6
            L_0x007f:
                r7 = move-exception
                java.lang.String r0 = java.lang.String.valueOf(r7)
                android.util.Log.e(r1, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
            L_0x008c:
                java.lang.String r7 = r7.getMessage()
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                goto L_0x00c6
            L_0x0098:
                r7 = move-exception
            L_0x0099:
                com.google.appinventor.components.runtime.MakeroidFtp r2 = com.google.appinventor.components.runtime.MakeroidFtp.this     // Catch:{ all -> 0x005f }
                com.google.appinventor.components.runtime.Form r2 = r2.form     // Catch:{ all -> 0x005f }
                com.google.appinventor.components.runtime.MakeroidFtp r4 = com.google.appinventor.components.runtime.MakeroidFtp.this     // Catch:{ all -> 0x005f }
                r2.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r4, (java.lang.String) r0, (com.google.appinventor.components.runtime.errors.PermissionException) r7)     // Catch:{ all -> 0x005f }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
                r0.<init>()     // Catch:{ all -> 0x005f }
                java.lang.String r7 = r7.getMessage()     // Catch:{ all -> 0x005f }
                r0.append(r7)     // Catch:{ all -> 0x005f }
                java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x005f }
                if (r3 == 0) goto L_0x00c6
                r3.close()     // Catch:{ Exception -> 0x00b8 }
                goto L_0x00c6
            L_0x00b8:
                r7 = move-exception
                java.lang.String r0 = java.lang.String.valueOf(r7)
                android.util.Log.e(r1, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                goto L_0x008c
            L_0x00c6:
                return r7
            L_0x00c7:
                if (r3 == 0) goto L_0x00d8
                r3.close()     // Catch:{ Exception -> 0x00cd }
                goto L_0x00d8
            L_0x00cd:
                r0 = move-exception
                java.lang.String r2 = java.lang.String.valueOf(r0)
                android.util.Log.e(r1, r2)
                r0.getMessage()
            L_0x00d8:
                throw r7
            L_0x00d9:
                r7 = move-exception
                java.lang.String r0 = java.lang.String.valueOf(r7)
                android.util.Log.e(r1, r0)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r7 = r7.getMessage()
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                return r7
            L_0x00f2:
                r7 = move-exception
                com.google.appinventor.components.runtime.MakeroidFtp r1 = com.google.appinventor.components.runtime.MakeroidFtp.this
                com.google.appinventor.components.runtime.Form r1 = r1.form
                com.google.appinventor.components.runtime.MakeroidFtp r2 = com.google.appinventor.components.runtime.MakeroidFtp.this
                r1.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r2, (java.lang.String) r0, (com.google.appinventor.components.runtime.errors.PermissionException) r7)
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r7 = r7.getMessage()
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidFtp.c.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    @SimpleEvent(description = "This event returns the status of the connection. If it is connect it will return true, else false. ")
    public void ConnectionStatus(boolean z) {
        EventDispatcher.dispatchEvent(this, "ConnectionStatus", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "This event is invoked when the upload is finished.")
    public void UploadDone() {
        EventDispatcher.dispatchEvent(this, "UploadDone", new Object[0]);
    }

    @SimpleEvent(description = "This event is invoked when the download is finished.")
    public void DownloadDone() {
        EventDispatcher.dispatchEvent(this, "DownloadDone", new Object[0]);
    }

    @SimpleEvent(description = "This event returns the reason if a upload was not successful.")
    public void UploadError(String str) {
        EventDispatcher.dispatchEvent(this, "UploadError", str);
    }

    @SimpleEvent(description = "This event returns the reason if a download was not successful.")
    public void DownloadError(String str) {
        EventDispatcher.dispatchEvent(this, "DownloadError", str);
    }

    @SimpleEvent(description = "This event returns the reason if a try to connect was not successful.")
    public void ConnectError(String str) {
        EventDispatcher.dispatchEvent(this, "ConnectError", str);
    }

    @SimpleEvent(description = "This event returns the reason if a try to disconnect was not successful.")
    public void DisconnectError(String str) {
        EventDispatcher.dispatchEvent(this, "DisconnectError", str);
    }
}
