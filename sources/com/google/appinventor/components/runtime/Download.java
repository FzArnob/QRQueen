package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import java.io.File;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "The Download component is a non-visible component that allows users to download any file to the device", iconName = "images/download.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.DOWNLOAD_WITHOUT_NOTIFICATION"})
public final class Download extends AndroidNonvisibleComponent implements Component {
    private long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = "";
    private String G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = "Downloading file..";

    /* renamed from: G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL  reason: collision with other field name */
    private boolean f77G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = false;
    private String IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = "Download..";

    /* renamed from: IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi  reason: collision with other field name */
    private boolean f78IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = false;
    private boolean YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = true;
    private String ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = "NewFile.png";
    private final Activity activity;
    private final Context context;
    private boolean gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = true;
    private final BroadcastReceiver hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private final DownloadManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = false;
    private boolean tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = true;
    private final BroadcastReceiver vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    @Deprecated
    @SimpleProperty(description = "This function is deprecated. Do not use it anymore. We will remove it in the future. Since we support min API 14 the download manager is by default available. The download manager was added in API 9.")
    public final boolean isDownloadManagerAvailable() {
        return true;
    }

    public Download(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        AnonymousClass3 r0 = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                if ("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED".equals(intent.getAction())) {
                    Download.this.NotificationClicked();
                }
            }
        };
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r0;
        AnonymousClass4 r1 = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(new long[]{Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this)});
                try {
                    Cursor query2 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this).query(query);
                    if (query2 != null && query2.moveToFirst() && 8 == query2.getInt(query2.getColumnIndex(NotificationCompat.CATEGORY_STATUS))) {
                        String replace = query2.getString(query2.getColumnIndex("local_uri")).replace("file://", "");
                        Download.this.DownloadComplete(replace, replace.substring(replace.lastIndexOf(47) + 1), query2.getInt(query2.getColumnIndex("total_size")));
                    }
                } catch (Exception e) {
                    Log.d("Download", e.getMessage());
                }
            }
        };
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r1;
        Activity $context = componentContainer.$context();
        this.context = $context;
        Activity $context2 = componentContainer.$context();
        this.activity = $context2;
        $context2.registerReceiver(r0, new IntentFilter("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"));
        $context2.registerReceiver(r1, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (DownloadManager) $context.getSystemService("download");
        Log.d("Download", "Download Extension Created");
    }

    @DesignerProperty(defaultValue = "Downloading file..", editorType = "string")
    @SimpleProperty(description = "Set the description that you will see in the download notification.")
    public final void Description(String str) {
        Log.d("Download", "Description text is: ".concat(String.valueOf(str)));
        this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = str;
    }

    @DesignerProperty(defaultValue = "Download..", editorType = "string")
    @SimpleProperty(description = "Set the title that you will see in the download notification.")
    public final void Title(String str) {
        Log.d("Download", "Title text is: ".concat(String.valueOf(str)));
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = str;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set here the url to the file that you want to download.")
    public final void DownloadUrl(String str) {
        Log.d("Download", "DownloadUrl text is: ".concat(String.valueOf(str)));
        this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = str;
    }

    @DesignerProperty(defaultValue = "NewFile.png", editorType = "string")
    @SimpleProperty(description = "Set here the new filename for the file that you want to download.")
    public final void SaveFileAs(String str) {
        Log.d("Download", "SaveFileAs text is: ".concat(String.valueOf(str)));
        this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = str;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set whether this download may proceed over a roaming connection.")
    public final void AllowedOverRoaming(boolean z) {
        Log.d("Download", "AllowedOverRoaming: ".concat(String.valueOf(z)));
        this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = z;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If false you will see a toast message with a error message when a error is occurred.")
    public final void SuppressWarnings(boolean z) {
        Log.d("Download", "SuppressWarnings: ".concat(String.valueOf(z)));
        this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = z;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Control whether a system notification is posted by the download manager while this download is running or when it is completed.")
    public final void ShowNotification(boolean z) {
        Log.d("Download", "ShowNotification: ".concat(String.valueOf(z)));
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = z;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If the file to be downloaded is to be scanned by MediaScanner.")
    public final void ScanningByMediaScanner(boolean z) {
        Log.d("Download", "ScanningByMediaScanner: ".concat(String.valueOf(z)));
        this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = z;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specify that to run this download, the device needs to be plugged in. Works only for devices with api >= 24.")
    public final void RequiresCharging(boolean z) {
        Log.d("Download", "RequiresCharging: ".concat(String.valueOf(z)));
        this.f77G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = z;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specify that to run, the download needs the device to be in idle mode. Idle mode is a loose definition provided by the system, which means that the device is not in use, and has not been in use for some time. Works only for devices with api >= 24.")
    public final void RequiresDeviceIdle(boolean z) {
        Log.d("Download", "RequiresDeviceIdle: ".concat(String.valueOf(z)));
        this.f78IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = z;
    }

    @SimpleProperty(description = "Return the description text.")
    public final String Description() {
        return this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL;
    }

    @SimpleProperty(description = "Return the title text.")
    public final String Title() {
        return this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    @SimpleProperty(description = "Return the download url.")
    public final String DownloadUrl() {
        return this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL;
    }

    @SimpleProperty(description = "Return the save file as text.")
    public final String SaveFileAs() {
        return this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR;
    }

    @SimpleProperty(description = "Return the allowed over roaming setting.")
    public final boolean AllowedOverRoaming() {
        return this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv;
    }

    @SimpleProperty(description = "Return the suppress warnings setting.")
    public final boolean SuppressWarnings() {
        return this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R;
    }

    @SimpleProperty(description = "Return the show notification setting.")
    public final boolean ShowNotification() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @SimpleProperty(description = "Return the scanning by MediaScanner setting.")
    public final boolean ScanningByMediaScanner() {
        return this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;
    }

    @SimpleProperty(description = "Return the requires device idle setting.")
    public final boolean RequiresDeviceIdle() {
        return this.f78IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    @SimpleProperty(description = "Return the requires charging setting.")
    public final boolean RequiresCharging() {
        return this.f77G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL;
    }

    @SimpleEvent(description = "Event to detect if the download is successful finished. You can use the \"filePath\" to use the downloaded file into your app. The \"fileSize\" will be returned in bytes.")
    public final void DownloadComplete(String str, String str2, int i) {
        EventDispatcher.dispatchEvent(this, "DownloadComplete", str, str2, Integer.valueOf(i));
    }

    @SimpleEvent(description = "Event to detect when the user clicks on a running download, either from a system notification or from the downloads UI.")
    public final void NotificationClicked() {
        EventDispatcher.dispatchEvent(this, "NotificationClicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect file size is ready to be used.")
    public final void GotFileSize(int i) {
        EventDispatcher.dispatchEvent(this, "GotFileSize", Integer.valueOf(i));
    }

    @SimpleFunction(description = "You can open the download folder with this block.")
    public final void ShowDownload() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW_DOWNLOADS");
        this.form.startActivity(intent);
    }

    @SimpleFunction(description = "Get the file size (in bytes) of a file that is stored online or on your device. The block detect automatic if it is a online path or not. You will get the result in the \"Got File Size\" event.")
    public final void GetFileSize(String str) {
        if (!str.startsWith("http")) {
            new a(this, (byte) 0).execute(new String[]{str});
        } else if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME()) {
            new b(this, (byte) 0).execute(new String[]{str});
        } else {
            GotFileSize(0);
        }
    }

    @SimpleFunction(description = "Start the download of the given download url.")
    public final void Download() {
        if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt();
        } else {
            this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this);
                    } else {
                        Download.this.form.dispatchPermissionDeniedEvent((Component) Download.this, "Download", str);
                    }
                }
            });
        }
    }

    private void sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt() {
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL));
            request.setDescription(this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL);
            request.setTitle(this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR);
            request.setAllowedOverRoaming(this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv);
            if (this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u) {
                request.setNotificationVisibility(1);
            } else {
                request.setNotificationVisibility(2);
            }
            if (this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag) {
                request.allowScanningByMediaScanner();
            }
            if (Build.VERSION.SDK_INT >= 24) {
                if (this.f77G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL) {
                    request.setRequiresCharging(true);
                }
                if (this.f78IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi) {
                    request.setRequiresDeviceIdle(true);
                }
            }
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enqueue(request);
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    boolean z = true;
                    while (z) {
                        DownloadManager.Query query = new DownloadManager.Query();
                        query.setFilterById(new long[]{Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this)});
                        Cursor query2 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this).query(query);
                        if (query2 != null && query2.moveToFirst()) {
                            int i = query2.getInt(query2.getColumnIndex("bytes_so_far"));
                            int i2 = query2.getInt(query2.getColumnIndex("total_size"));
                            if (query2.getInt(query2.getColumnIndex(NotificationCompat.CATEGORY_STATUS)) == 8) {
                                z = false;
                            }
                            final int i3 = (int) ((((long) i) * 100) / ((long) i2));
                            Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download.this).runOnUiThread(new Runnable() {
                                public final void run() {
                                    Download.this.DownloadProgress(i3);
                                }
                            });
                            query2.close();
                        }
                    }
                }
            });
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "Download", e);
        } catch (IllegalStateException e2) {
            if (!this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R) {
                Log.d("Download", e2.getMessage());
                Context context2 = this.context;
                Toast.makeText(context2, e2.getMessage(), 1).show();
            }
        }
    }

    @SimpleEvent(description = "Get the progress (in percentage) of the current download task.")
    public final void DownloadProgress(int i) {
        EventDispatcher.dispatchEvent(this, "DownloadProgress", Integer.valueOf(i));
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            Log.d("Download", "Network connections is available: false");
            if (this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R) {
                return false;
            }
            Log.d("Download", "SuppressWarnings is false. Show now a toast message. Missing internet connection.");
            Toast.makeText(this.context, "Please check your internet connection", 1).show();
            return false;
        }
        Log.d("Download", "Network connections is available: true");
        return true;
    }

    class b extends AsyncTask<String, Void, Integer> {
        private String vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK;

        private b() {
            this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = null;
        }

        /* synthetic */ b(Download download, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Download.this.GotFileSize(((Integer) obj).intValue());
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
            r4.disconnect();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0031 */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x003d  */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Integer doInBackground(java.lang.String... r4) {
            /*
                r3 = this;
                r0 = 0
                r4 = r4[r0]
                r3.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = r4
                r4 = 0
                java.net.URL r1 = new java.net.URL     // Catch:{ Exception -> 0x0031 }
                java.lang.String r2 = r3.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK     // Catch:{ Exception -> 0x0031 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0031 }
                java.net.URLConnection r1 = r1.openConnection()     // Catch:{ Exception -> 0x0031 }
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0031 }
                java.lang.String r4 = "HEAD"
                r1.setRequestMethod(r4)     // Catch:{ Exception -> 0x002b, all -> 0x0029 }
                r1.getInputStream()     // Catch:{ Exception -> 0x002b, all -> 0x0029 }
                int r4 = r1.getContentLength()     // Catch:{ Exception -> 0x002b, all -> 0x0029 }
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x002b, all -> 0x0029 }
                if (r1 == 0) goto L_0x0028
                r1.disconnect()
            L_0x0028:
                return r4
            L_0x0029:
                r4 = move-exception
                goto L_0x003b
            L_0x002b:
                r4 = r1
                goto L_0x0031
            L_0x002d:
                r0 = move-exception
                r1 = r4
                r4 = r0
                goto L_0x003b
            L_0x0031:
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x002d }
                if (r4 == 0) goto L_0x003a
                r4.disconnect()
            L_0x003a:
                return r0
            L_0x003b:
                if (r1 == 0) goto L_0x0040
                r1.disconnect()
            L_0x0040:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Download.b.doInBackground(java.lang.String[]):java.lang.Integer");
        }
    }

    class a extends AsyncTask<String, Void, Integer> {
        private a() {
        }

        /* synthetic */ a(Download download, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            File file = new File(((String[]) objArr)[0]);
            if (file.exists()) {
                return Integer.valueOf((int) file.length());
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Download.this.GotFileSize(((Integer) obj).intValue());
        }
    }
}
