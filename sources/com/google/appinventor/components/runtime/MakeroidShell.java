package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "Checks Root status and executes Shell command", iconName = "images/shell.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class MakeroidShell extends AndroidNonvisibleComponent {
    private Context context;

    public MakeroidShell(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Shell", "Shell component created");
    }

    @SimpleFunction(description = "Returns true if Phone is Rooted")
    public boolean IsRooted() {
        try {
            Runtime.getRuntime().exec("su");
            return true;
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "IsRooted", e);
            Log.e("Shell", "Permission exception executing su.", e);
            return false;
        } catch (Exception e2) {
            Log.e("Shell", "Exception executing su.", e2);
            return false;
        }
    }

    @SimpleFunction(description = "This returns TRUE if the system user is running the application, and could be a sign of a rooted device. Developed by Cian.")
    public boolean isSystemUser() {
        if (Build.VERSION.SDK_INT >= 23) {
            return ((UserManager) this.context.getSystemService("user")).isSystemUser();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0015  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns TRUE if one of 6 known root packages or varients is installed. The name of the package is not returned, so the user does not know which package name to change. Developed by Cian.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean CheckForSuperUserAPK() {
        /*
            r6 = this;
            android.content.Context r0 = r6.context
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r1 = 0
            java.util.List r0 = r0.getInstalledApplications(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x000f:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r0.next()
            android.content.pm.ApplicationInfo r2 = (android.content.pm.ApplicationInfo) r2
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".noshufou"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0055
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".yellowes.su"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0055
            java.lang.String r3 = r2.packageName
            java.lang.String r4 = ".chainfire.supersu"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0055
            java.lang.String r3 = r2.packageName
            java.lang.String r5 = ".koushikdutta.superuser"
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0055
            java.lang.String r3 = r2.packageName
            java.lang.String r5 = ".thirdparty.superuser"
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0055
            java.lang.String r2 = r2.packageName
            boolean r2 = r2.contains(r4)
            if (r2 == 0) goto L_0x000f
        L_0x0055:
            r0 = 1
            return r0
        L_0x0057:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidShell.CheckForSuperUserAPK():boolean");
    }

    @SimpleFunction(description = "Executes shell commands. To get output, use \"GotOutput\" event block.")
    public void Execute(final String str) {
        this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        Process exec = Runtime.getRuntime().exec(str);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                        exec.waitFor();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                                sb.append("\n");
                            } else {
                                bufferedReader.close();
                                exec.destroy();
                                return;
                            }
                        }
                    } catch (PermissionException e) {
                        MakeroidShell.this.form.dispatchPermissionDeniedEvent((Component) MakeroidShell.this, "Execute", e);
                    } catch (Exception e2) {
                        Log.e("Shell", String.valueOf(e2));
                    } finally {
                        MakeroidShell.this.GotOutput(sb.toString());
                    }
                } else {
                    MakeroidShell.this.form.dispatchPermissionDeniedEvent((Component) MakeroidShell.this, "Execute", str);
                }
            }
        });
    }

    @SimpleEvent(description = "Read output after executing shell command")
    public void GotOutput(String str) {
        EventDispatcher.dispatchEvent(this, "GotOutput", str);
    }
}
