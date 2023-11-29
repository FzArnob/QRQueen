package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/resourceUtilities.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE"})
public class KodularResourceUtilities extends AndroidNonvisibleComponent implements Component {
    private String AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = "";
    private Context context;
    private boolean isCompanion = false;
    private String nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = "";

    @Deprecated
    @SimpleFunction(description = "This block is deprecated and will be removed soon. Use instead 'Get String From Asset' or 'Get String From Path'.")
    public String GetStringContentByName(String str, String str2) {
        return "'Get String Content By Name' is deprecated and works not longer. Use instead 'Get String From Asset' or 'Get String From Path' blocks.";
    }

    public KodularResourceUtilities(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        if (this.form instanceof ReplForm) {
            this.isCompanion = true;
        }
        Log.d("KodularResourceUtilities", "Kodular Resource Utilities Created");
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty(description = "Set the file which is used as resource file. The file must be stored in the assets folder.")
    public void ResourceFileFromAsset(String str) {
        if (str != null) {
            this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl = str;
        }
    }

    @SimpleProperty(description = "Returns the used resource file from asset.")
    public String ResourceFileFromAsset() {
        return this.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the file which is used as resource file. The file path must be valid to any file you want to use. The path can be too a link to a url.")
    public void ResourceFileFromPath(String str) {
        if (str != null) {
            this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow = str;
        }
    }

    @SimpleProperty(description = "Returns the used resource file from path.")
    public String ResourceFileFromPath() {
        return this.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow;
    }

    @SimpleFunction(description = "Get the text from a asset resource file. Make sure you uploaded a file at 'Resource File From Asset' property.")
    public String GetStringFromAsset(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ResourceFileFromAsset(), this.isCompanion), str, str2);
    }

    @SimpleFunction(description = "Get the text from a path resource file. Make sure you added a file path at 'Resource File From Path' property. The path can be too a link to a url.")
    public String GetStringFromPath(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ResourceFileFromPath(), this.isCompanion), str, str2);
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, String str3) {
        if (str == null) {
            return "There was a problem to get the string '" + str2 + "'. Make sure on your side is all correct.";
        }
        try {
            String string = new JSONObject(str.replace("\\", "")).getString(str2);
            return string != null ? string : str3;
        } catch (PermissionException e) {
            Log.e("KodularResourceUtilities", String.valueOf(e));
            return e.getMessage();
        } catch (Exception e2) {
            Log.e("KodularResourceUtilities", String.valueOf(e2));
            return str3;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A[Catch:{ Exception -> 0x0096 }, LOOP:0: B:26:0x0084->B:28:0x008a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String r5, boolean r6) {
        /*
            r4 = this;
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x0009
            java.lang.String r5 = "The file name can not be empty."
            return r5
        L_0x0009:
            r0 = 0
            if (r6 == 0) goto L_0x0020
            java.lang.String r1 = "file://"
            boolean r2 = r5.startsWith(r1)
            if (r2 == 0) goto L_0x0020
            java.lang.String r2 = ""
            java.lang.String r5 = r5.replace(r1, r2)
            java.io.File r1 = new java.io.File
            r1.<init>(r5)
            goto L_0x0021
        L_0x0020:
            r1 = r0
        L_0x0021:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            if (r6 == 0) goto L_0x006f
            if (r1 == 0) goto L_0x006f
            java.lang.String r6 = "http://"
            boolean r6 = r5.startsWith(r6)     // Catch:{ Exception -> 0x0096 }
            if (r6 != 0) goto L_0x0046
            java.lang.String r6 = "https://"
            boolean r6 = r5.startsWith(r6)     // Catch:{ Exception -> 0x0096 }
            if (r6 == 0) goto L_0x003b
            goto L_0x0046
        L_0x003b:
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0096 }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ Exception -> 0x0096 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0096 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0096 }
            goto L_0x0084
        L_0x0046:
            java.net.URL r6 = new java.net.URL     // Catch:{ Exception -> 0x0096 }
            r6.<init>(r5)     // Catch:{ Exception -> 0x0096 }
            java.net.URLConnection r5 = r6.openConnection()     // Catch:{ Exception -> 0x0096 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ Exception -> 0x0096 }
            r6 = 60000(0xea60, float:8.4078E-41)
            r5.setConnectTimeout(r6)     // Catch:{ Exception -> 0x0096 }
            int r6 = r5.getResponseCode()     // Catch:{ Exception -> 0x0096 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r6 != r1) goto L_0x006e
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0096 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0096 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ Exception -> 0x0096 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0096 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0096 }
            goto L_0x0083
        L_0x006e:
            return r0
        L_0x006f:
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0096 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0096 }
            android.content.Context r3 = r4.context     // Catch:{ Exception -> 0x0096 }
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch:{ Exception -> 0x0096 }
            java.io.InputStream r5 = r3.open(r5)     // Catch:{ Exception -> 0x0096 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0096 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0096 }
        L_0x0083:
            r5 = r6
        L_0x0084:
            java.lang.String r6 = r5.readLine()     // Catch:{ Exception -> 0x0096 }
            if (r6 == 0) goto L_0x008e
            r2.append(r6)     // Catch:{ Exception -> 0x0096 }
            goto L_0x0084
        L_0x008e:
            r5.close()     // Catch:{ Exception -> 0x0096 }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x0096 }
            return r5
        L_0x0096:
            r5 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r6 = "KodularResourceUtilities"
            android.util.Log.e(r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularResourceUtilities.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String, boolean):java.lang.String");
    }
}
