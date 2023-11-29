package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "", iconName = "images/instantAccess.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public class MakeroidInstantAccess extends AndroidNonvisibleComponent implements Component {
    private final String I1fbWPe6RJ2coGGe88dnbV3SwCWOYXWySlRHSiEJVMw7CeEp0YdmKizbxY7zqrk2 = "Error - no data";
    private String I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH = "";
    private final String Mn7MCs8byCcphc6u6vZkI1pXuzw5IvVJJPq0YTQ0xCW64cXQ1HYdJPP7QsOPqGr1 = "Error - no message";
    private final String P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = "Please check your Client Secret property. Maybe it is empty?";
    private final String boZncMwfbKhn9wEZVXOlCQaCALViR3x19GsnEC1EZxndIE2ufazY5HxCE0U58Zvt = "Please check your Client ID property. Maybe it is empty?";
    private Context context;
    private final String hlRRzlkTvaLJKT7xudtgqrNpSapzuwbeZZHKJdIwcwGUTFYejftgk4y9qZgGh2f0 = "Please check your user property. Maybe it is empty?";
    private String t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = "";
    /* access modifiers changed from: private */
    public int xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = 1;

    public MakeroidInstantAccess(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Instant Access", "Instant Access Created");
    }

    @SimpleFunction(description = "Start a request to user with the instant access login service.")
    public void Request(String str) {
        String str2 = "https://dashboard.instantaccess.io/api/partner/authorize?client_id=" + this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg + "&client_secret=" + this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH + "&user_identifier=" + str;
        String str3 = this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg;
        if (str3 == null || str3.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your Client ID property. Maybe it is empty?");
            Log.e("Instant Access", "Client ID is null or empty.");
            return;
        }
        String str4 = this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH;
        if (str4 == null || str4.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your Client Secret property. Maybe it is empty?");
            Log.e("Instant Access", "Client Secret is null or empty.");
        } else if (str == null || str.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your user property. Maybe it is empty?");
            Log.e("Instant Access", "User is null or empty.");
        } else {
            this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = 1;
            new a(this, (byte) 0).execute(new String[]{str2});
        }
    }

    @SimpleEvent(description = "A event to detect that the login request was sent.")
    public void OnRequestSent(boolean z, String str, String str2) {
        EventDispatcher.dispatchEvent(this, "OnRequestSent", Boolean.valueOf(z), str, str2);
    }

    @SimpleFunction(description = "Check the current status with a given username.")
    public void CheckStatus(String str) {
        String str2 = "https://dashboard.instantaccess.io/api/partner/status?client_id=" + this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg + "&client_secret=" + this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH + "&user_identifier=" + str;
        String str3 = this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg;
        if (str3 == null || str3.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your Client ID property. Maybe it is empty?");
            Log.e("Instant Access", "Client ID is null or empty.");
            return;
        }
        String str4 = this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH;
        if (str4 == null || str4.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your Client Secret property. Maybe it is empty?");
            Log.e("Instant Access", "Client Secret is null or empty.");
        } else if (str == null || str.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your user property. Maybe it is empty?");
            Log.e("Instant Access", "User is null or empty.");
        } else {
            this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 = 2;
            new a(this, (byte) 0).execute(new String[]{str2});
        }
    }

    @SimpleEvent(description = "A event to detect that the status was received.")
    public void OnStatusReceived(boolean z, String str, String str2) {
        EventDispatcher.dispatchEvent(this, "OnStatusReceived", Boolean.valueOf(z), str, str2);
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void ClientID(String str) {
        this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = str;
    }

    @SimpleFunction(description = "Returns the client id.")
    public String ClientID() {
        return this.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void ClientSecret(String str) {
        this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH = str;
    }

    @SimpleFunction(description = "Returns the client secret.")
    public String ClientSecret() {
        return this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH;
    }

    class a extends AsyncTask<String, Void, String> {
        private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;

        private a() {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = null;
        }

        /* synthetic */ a(MakeroidInstantAccess makeroidInstantAccess, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = KodularUtil.GET_REQUEST(((String[]) objArr)[0]);
            if (!KodularUtil.REQUEST_SUCCESS) {
                if (MakeroidInstantAccess.this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 == 1) {
                    MakeroidInstantAccess.this.OnRequestSent(false, "Error - no data", KodularUtil.REQUEST_ERROR_MESSAGE);
                } else if (MakeroidInstantAccess.this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 == 2) {
                    MakeroidInstantAccess.this.OnStatusReceived(false, "Error - no data", KodularUtil.REQUEST_ERROR_MESSAGE);
                }
            }
            return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            String GetJsonAsString = JsonUtil.GetJsonAsString(str, "data", "Error - no data");
            String GetJsonAsString2 = JsonUtil.GetJsonAsString(str, "message", "Error - no message");
            boolean GetJsonAsBoolean = JsonUtil.GetJsonAsBoolean(str, "success");
            if (MakeroidInstantAccess.this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 == 1) {
                MakeroidInstantAccess.this.OnRequestSent(GetJsonAsBoolean, GetJsonAsString, GetJsonAsString2);
            } else if (MakeroidInstantAccess.this.xy1Y0dkIX1oFLPndkUFF3zQF6ijcSVPdmgekjoDrBgvDBhvpj0INgajUPNaUhxY6 == 2) {
                MakeroidInstantAccess.this.OnStatusReceived(GetJsonAsBoolean, GetJsonAsString, GetJsonAsString2);
            }
        }
    }
}
