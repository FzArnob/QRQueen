package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
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
import com.google.appinventor.components.runtime.util.ClientLoginHelper;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.microsoft.appcenter.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "<p>A non-visible component that communicates with Google Fusion Tables. Fusion Tables let you store, share, query and visualize data tables; this component lets you query, create, and modify these tables.</p> <p>This component uses the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API V2.0</a>. <p>Applications using Fusion Tables must authentication to Google's servers. There are two ways this can be done. The first way uses an API Key which you the developer obtain (see below). With this approach end-users must also login to access a Fusion Table. The second approach is to use a Service Account. With this approach you create credentials and a special \"Service Account Email Address\" which you obtain from the <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a>. You then tell the Fusion Table Control the name of the Service Account Email address and upload the secret key as an asset to your application and set the KeyFile property to point at this file. Finally you check the \"UseServiceAuthentication\" checkbox in the designer. When using a Service Account, end-users do not need to login to use Fusion Tables, your service account authenticates all access.</p> <p>To get an API key, follow these instructions.</p> <ol><li>Go to your <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a> and login if necessary.</li><li>Select the <i>Services</i> item from the menu on the left.</li><li>Choose the <i>Fusiontables</i> service from the list provided and turn it on.</li><li>Go back to the main menu and select the <i>API Access</i> item. </li></ol><p>Your API Key will be near the bottom of that pane in the section called \"Simple API Access\".You will have to provide that key as the value for the <i>ApiKey</i> property in your Fusiontables app.</p><p>Once you have an API key, set the value of the <i>Query</i> property to a valid Fusiontables SQL query and call <i>SendQuery</i> to execute the query.  App Inventor will send the query to the Fusion Tables server and the <i>GotResult</i> block will fire when a result is returned from the server.Query results will be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.</p><p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure the query follows the syntax described in <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">the reference manual</a>, which means that things like capitalization for names of columns matters, and that single quotes must be used around column names if there are spaces in them.</p>", iconName = "images/fusiontables.png", nonVisible = true, version = 4)
@UsesLibraries({"fusiontables.jar", "google-api-client-beta.jar", "google-api-client-android2-beta.jar", "google-http-client-beta.jar", "google-http-client-android2-beta.jar", "google-http-client-android3-beta.jar", "google-oauth-client-beta.jar", "guava.jar", "gson.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCOUNT_MANAGER", "android.permission.MANAGE_ACCOUNTS", "android.permission.GET_ACCOUNTS", "android.permission.USE_CREDENTIALS"})
public class FusiontablesControl extends AndroidNonvisibleComponent implements Component {
    public static final String APP_NAME = "App Inventor";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    public static final String AUTH_TOKEN_TYPE_FUSIONTABLES = "oauth2:https://www.googleapis.com/auth/fusiontables";
    public static final String FUSIONTABLES_POST = "https://www.googleapis.com/fusiontables/v2/tables";
    /* access modifiers changed from: private */
    public String JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
    /* access modifiers changed from: private */
    public String OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    /* access modifiers changed from: private */
    public String UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = "";
    /* access modifiers changed from: private */
    public String YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = "";
    /* access modifiers changed from: private */
    public String ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = "https://www.googleapis.com/auth/fusiontables";
    private final Activity activity;
    private final Handler androidUIHandler = new Handler();
    private final ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private final IClientLoginHelper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public File f124hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    /* access modifiers changed from: private */
    public String joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = "Error on Fusion Tables query";
    private String muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = "Please wait loading...";
    private String qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;

    /* renamed from: qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM  reason: collision with other field name */
    private boolean f125qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = true;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = AUTH_TOKEN_TYPE_FUSIONTABLES;
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;

    /* renamed from: sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp  reason: collision with other field name */
    private boolean f126sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = false;

    public FusiontablesControl(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Activity $context = componentContainer.$context();
        this.activity = $context;
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 30000);
        HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 30000);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ClientLoginHelper($context, "fusiontables", "Choose an account to access FusionTables", defaultHttpClient);
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = "show tables";
    }

    public void Initialize() {
        this.form.askPermission("android.permission.GET_ACCOUNTS", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    boolean unused = FusiontablesControl.this.havePermission = true;
                    return;
                }
                boolean unused2 = FusiontablesControl.this.havePermission = false;
                FusiontablesControl.this.form.dispatchPermissionDeniedEvent((Component) FusiontablesControl.this, "Initialize", "android.permission.GET_ACCOUNTS");
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates whether a service account should be used for authentication")
    public boolean UseServiceAuthentication() {
        return this.f126sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void UseServiceAuthentication(boolean z) {
        this.f126sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Service Account Email Address when service account authentication is in use.")
    public String ServiceAccountEmail() {
        return this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ServiceAccountEmail(String str) {
        this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = str;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ApiKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Google API Key. For help, click on the questionmark (?) next to the FusiontablesControl component in the Palette. ")
    public String ApiKey() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "show tables", editorType = "string")
    @SimpleProperty
    public void Query(String str) {
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The query to send to the Fusion Tables API. <p>For legal query formats and examples, see the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API v2.0 reference manual</a>.</p> <p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure it follows the syntax described in the reference manual, which means that things like capitalization for names of columns matters, and that single quotes need to be used around column names if there are spaces in them.</p> ")
    public String Query() {
        return this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty
    public void KeyFile(String str) {
        if (!str.equals(this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1)) {
            File file = this.f124hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (file != null) {
                file.delete();
                this.f124hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
            if (str == null) {
                str = "";
            }
            this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = str;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path of the private key file.  This key file is used to get access to the FusionTables API.")
    public String KeyFile() {
        return this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    }

    @SimpleFunction(description = "Send the query to the Fusiontables server.")
    public void SendQuery() {
        if (this.havePermission) {
            new b(this.activity).execute(new String[]{this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "SendQuery", "android.permission.GET_ACCOUNTS");
    }

    @Deprecated
    @SimpleFunction(description = "DEPRECATED. This block is deprecated as of the end of 2012.  Use SendQuery.")
    public void DoQuery() {
        new a(this, (byte) 0).execute(new String[]{this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM});
    }

    @SimpleEvent(description = "Indicates that the Fusion Tables query has finished processing, with a result.  The result of the query will generally be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.")
    public void GotResult(String str) {
        EventDispatcher.dispatchEvent(this, "GotResult", str);
    }

    @SimpleFunction(description = "Forget end-users login credentials. Has no effect on service authentication")
    public void ForgetLogin() {
        OAuth2Helper.resetAccountCredential(this.activity);
    }

    @SimpleFunction(description = "Inserts a row into the specified fusion table. The tableId field is the id of thefusion table. The columns is a comma-separated list of the columns to insert values into. The values field specifies what values to insert into each column.")
    public void InsertRow(String str, String str2, String str3) {
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = "INSERT INTO " + str + " (" + str2 + ") VALUES (" + str3 + ")";
        if (this.havePermission) {
            new b(this.activity).execute(new String[]{this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "InsertRow", "android.permission.GET_ACCOUNTS");
    }

    @SimpleFunction(description = "Gets all the rows from a specified fusion table. The tableId field is the id of therequired fusion table. The columns field is a comma-separeted list of the columns to retrieve.")
    public void GetRows(String str, String str2) {
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = "SELECT " + str2 + " FROM " + str;
        if (this.havePermission) {
            new b(this.activity).execute(new String[]{this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "GetRows", "android.permission.GET_ACCOUNTS");
    }

    @SimpleFunction(description = "Gets all the rows from a fusion table that meet certain conditions. The tableId field isthe id of the required fusion table. The columns field is a comma-separated list of the columns toretrieve. The conditions field specifies what rows to retrieve from the table, for example the rows in whicha particular column value is not null.")
    public void GetRowsWithConditions(String str, String str2, String str3) {
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = "SELECT " + str2 + " FROM " + str + " WHERE " + str3;
        if (this.havePermission) {
            new b(this.activity).execute(new String[]{this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "GetRowsWithConditions", "android.permission.GET_ACCOUNTS");
    }

    @DesignerProperty(defaultValue = "Please wait loading...", editorType = "string")
    @SimpleProperty
    public void LoadingDialogMessage(String str) {
        this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the loading message for the dialog.")
    public String LoadingDialogMessage() {
        return this.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void ShowLoadingDialog(boolean z) {
        this.f125qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to show the loading dialog")
    public boolean ShowLoadingDialog() {
        return this.f125qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
    }

    class a extends AsyncTask<String, Void, String> {
        private ProgressDialog progress;

        private a() {
            this.progress = null;
        }

        /* synthetic */ a(FusiontablesControl fusiontablesControl, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.progress.dismiss();
            FusiontablesControl.this.GotResult((String) obj);
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            this.progress = ProgressDialog.show(FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this), "Fusiontables", "processing query...", true);
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(String... strArr) {
            try {
                HttpUriRequest hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(strArr[0]);
                Log.d("FUSION", "Fetching: " + strArr[0]);
                HttpResponse execute = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this).execute(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                execute.getEntity().writeTo(byteArrayOutputStream);
                Log.d("FUSION", "Response: " + execute.getStatusLine().toString());
                return byteArrayOutputStream.toString();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
    }

    public com.google.api.client.http.HttpResponse sendQuery(String str, String str2) {
        this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
        Log.i("FUSION", "executing ".concat(String.valueOf(str)));
        try {
            Fusiontables.Query.Sql sql = new Fusiontables.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), new GoogleCredential()).setApplicationName("App Inventor Fusiontables/v2.0").setJsonHttpRequestInitializer(new GoogleKeyInitializer(ApiKey())).build().query().sql(str);
            sql.put("alt", "csv");
            sql.setOauthToken(str2);
            return sql.executeUnparsed();
        } catch (PermissionException e) {
            this.form.dispatchPermissionDeniedEvent((Component) this, "SendQuery", e);
            Log.e("FUSION", String.valueOf(e));
            this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = e.getMessage();
            return null;
        } catch (GoogleJsonResponseException e2) {
            this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = e2.getMessage();
            Log.e("FUSION", "JsonResponseException");
            Log.e("FUSION", "e.getMessage() is " + e2.getMessage());
            Log.e("FUSION", "response is " + null);
            return null;
        } catch (IOException e3) {
            this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = e3.getMessage();
            Log.e("FUSION", "IOException");
            Log.e("FUSION", "e.getMessage() is " + e3.getMessage());
            Log.e("FUSION", "response is " + null);
            return null;
        }
    }

    public static String httpResponseToString(com.google.api.client.http.HttpResponse httpResponse) {
        if (httpResponse != null) {
            if (httpResponse.getStatusCode() != 200) {
                return httpResponse.getStatusCode() + " " + httpResponse.getStatusMessage();
            }
            try {
                return parseResponse(httpResponse.getContent());
            } catch (IOException e) {
                Log.e("FUSION", String.valueOf(e));
            }
        }
        return "";
    }

    public static String httpApacheResponseToString(HttpResponse httpResponse) {
        if (httpResponse != null) {
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                return httpResponse.getStatusLine().getStatusCode() + " " + httpResponse.getStatusLine().getReasonPhrase();
            }
            try {
                return parseResponse(httpResponse.getEntity().getContent());
            } catch (IOException e) {
                Log.e("FUSION", String.valueOf(e));
            }
        }
        return "";
    }

    public static String parseResponse(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine + "\n");
                } else {
                    String sb2 = sb.toString();
                    Log.i("FUSION", "resultStr = ".concat(String.valueOf(sb2)));
                    bufferedReader.close();
                    return sb2;
                }
            }
        } catch (IOException e) {
            Log.e("FUSION", String.valueOf(e));
            return "";
        }
    }

    public void handleOAuthError(String str) {
        Log.i("FUSION", "handleOAuthError: ".concat(String.valueOf(str)));
        this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = str;
    }

    /* access modifiers changed from: private */
    public String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2) {
        String substring = str.trim().substring(12);
        Log.i("FUSION", "Http Post content = ".concat(String.valueOf(substring)));
        HttpPost httpPost = new HttpPost("https://www.googleapis.com/fusiontables/v2/tables?key=" + ApiKey());
        try {
            StringEntity stringEntity = new StringEntity(substring);
            stringEntity.setContentType("application/json");
            httpPost.addHeader(Constants.AUTHORIZATION_HEADER, AUTHORIZATION_HEADER_PREFIX.concat(String.valueOf(str2)));
            httpPost.setEntity(stringEntity);
            try {
                HttpResponse execute = new DefaultHttpClient().execute(httpPost);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (execute == null || statusCode != 200) {
                    Log.i("FUSION", "Error: " + execute.getStatusLine().toString());
                    this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = execute.getStatusLine().toString();
                } else {
                    try {
                        String httpApacheResponseToString = httpApacheResponseToString(execute);
                        JSONObject jSONObject = new JSONObject(httpApacheResponseToString);
                        if (jSONObject.has("tableId")) {
                            this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = "tableId," + jSONObject.get("tableId");
                        } else {
                            this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = httpApacheResponseToString;
                        }
                        Log.i("FUSION", "Response code = " + execute.getStatusLine());
                        Log.i("FUSION", "Query = " + str + "\nResultStr = " + this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd);
                    } catch (IllegalStateException e) {
                        Log.e("FUSION", String.valueOf(e));
                        return "Error: " + e.getMessage();
                    } catch (JSONException e2) {
                        Log.e("FUSION", String.valueOf(e2));
                        return "Error: " + e2.getMessage();
                    }
                }
                return this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
            } catch (ClientProtocolException e3) {
                Log.e("FUSION", String.valueOf(e3));
                return "Error: " + e3.getMessage();
            } catch (IOException e4) {
                Log.e("FUSION", String.valueOf(e4));
                return "Error: " + e4.getMessage();
            }
        } catch (UnsupportedEncodingException e5) {
            Log.e("FUSION", String.valueOf(e5));
            return "Error: " + e5.getMessage();
        }
    }

    class b extends AsyncTask<String, Void, String> {
        private final Activity activity;
        private final ProgressDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            String str = ((String[]) objArr)[0];
            Log.i("QueryProcessorV2", "Starting doInBackground ".concat(String.valueOf(str)));
            if (FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this)) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str);
            }
            String unused = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = "";
            String refreshedAuthToken = new OAuth2Helper().getRefreshedAuthToken(this.activity, FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this));
            if (refreshedAuthToken == null) {
                return OAuth2Helper.getErrorMessage();
            }
            if (str.toLowerCase().contains("create table")) {
                FusiontablesControl fusiontablesControl = FusiontablesControl.this;
                String unused2 = fusiontablesControl.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = fusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str), refreshedAuthToken);
                return FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
            }
            com.google.api.client.http.HttpResponse sendQuery = FusiontablesControl.this.sendQuery(str, refreshedAuthToken);
            if (sendQuery != null) {
                String unused3 = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = FusiontablesControl.httpResponseToString(sendQuery);
                Log.i("QueryProcessorV2", "Query = " + str + "\nResultStr = " + FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd);
            } else {
                FusiontablesControl fusiontablesControl2 = FusiontablesControl.this;
                String unused4 = fusiontablesControl2.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = fusiontablesControl2.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
                Log.i("QueryProcessorV2", "Error:  " + FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
            }
            return FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            Log.i("FUSION", "Query result ".concat(String.valueOf(str)));
            if (str == null) {
                str = FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
            }
            if (!this.activity.isFinishing()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dismiss();
            }
            FusiontablesControl.this.GotResult(str);
        }

        b(Activity activity2) {
            Log.i("QueryProcessorV2", "Creating AsyncFusiontablesQuery");
            this.activity = activity2;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ProgressDialog(activity2);
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            if (FusiontablesControl.this.ShowLoadingDialog()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMessage(FusiontablesControl.this.LoadingDialogMessage());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
            }
        }

        private String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
            String unused = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = "";
            FusiontablesControl fusiontablesControl = FusiontablesControl.this;
            String unused2 = fusiontablesControl.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = fusiontablesControl.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
            HttpTransport newCompatibleTransport = AndroidHttp.newCompatibleTransport();
            GsonFactory gsonFactory = new GsonFactory();
            Log.i("FUSION_SERVICE_ACCOUNT", "keyPath " + FusiontablesControl.this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1);
            try {
                if (FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this) == null) {
                    FusiontablesControl fusiontablesControl2 = FusiontablesControl.this;
                    File unused3 = fusiontablesControl2.f124hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MediaUtil.copyMediaToTempFile(FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(fusiontablesControl2).$form(), FusiontablesControl.this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1);
                }
                Fusiontables.Query.Sql sql = new Fusiontables.Builder(newCompatibleTransport, gsonFactory, new GoogleCredential.Builder().setTransport(newCompatibleTransport).setJsonFactory(gsonFactory).setServiceAccountId(FusiontablesControl.this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO).setServiceAccountScopes(new String[]{FusiontablesControl.this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T}).setServiceAccountPrivateKeyFromP12File(FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.this)).build()).setJsonHttpRequestInitializer(new GoogleKeyInitializer(FusiontablesControl.this.ApiKey())).build().query().sql(str);
                sql.put("alt", "csv");
                com.google.api.client.http.HttpResponse httpResponse = null;
                try {
                    httpResponse = sql.executeUnparsed();
                } catch (GoogleJsonResponseException e) {
                    Log.i("FUSION_SERVICE_ACCOUNT", "Got a JsonResponse exception on sql.executeUnparsed");
                    FusiontablesControl fusiontablesControl3 = FusiontablesControl.this;
                    String str2 = e.getMessage();
                    Log.i("FUSION_SERVICE_ACCOUNT", "parseJsonResponseException: ".concat(String.valueOf(str2)));
                    String unused4 = fusiontablesControl3.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = str2;
                    FusiontablesControl.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
                } catch (Exception e2) {
                    Log.i("FUSION_SERVICE_ACCOUNT", "Got an unanticipated exception on sql.executeUnparsed");
                    Log.i("FUSION_SERVICE_ACCOUNT", "Exception class is " + e2.getClass());
                    Log.i("FUSION_SERVICE_ACCOUNT", "Exception message is " + e2.getMessage());
                    Log.i("FUSION_SERVICE_ACCOUNT", "Exception is ".concat(String.valueOf(e2)));
                    Log.i("FUSION_SERVICE_ACCOUNT", "Point e");
                    Log.i("FUSION_SERVICE_ACCOUNT", "end of printing exception");
                    String unused5 = FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = e2.getMessage();
                    FusiontablesControl.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
                }
                if (httpResponse != null) {
                    String unused6 = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = FusiontablesControl.httpResponseToString(httpResponse);
                    Log.i("FUSION_SERVICE_ACCOUNT", "Query = " + str + "\nResultStr = " + FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd);
                } else {
                    FusiontablesControl fusiontablesControl4 = FusiontablesControl.this;
                    String unused7 = fusiontablesControl4.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = fusiontablesControl4.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
                    Log.i("FUSION_SERVICE_ACCOUNT", "Error with null response:  " + FusiontablesControl.this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG);
                }
                Log.i("FUSION_SERVICE_ACCOUNT", "executed sql query");
            } catch (PermissionException e3) {
                FusiontablesControl.this.form.dispatchPermissionDeniedEvent((Component) FusiontablesControl.this, "UseServiceAuthentication", e3);
                Log.e("FUSION", String.valueOf(e3));
                String unused8 = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = e3.getMessage();
            } catch (Throwable th) {
                Log.i("FUSION_SERVICE_ACCOUNT", "in Catch Throwable e");
                String unused9 = FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = th.getMessage();
            }
            Log.i("FUSION_SERVICE_ACCOUNT", "returning queryResultStr = " + FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd);
            return FusiontablesControl.this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    public final void m1hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2) {
        this.form.dispatchErrorOccurredEventDialog(this, "SendQuery", ErrorMessages.FUSION_TABLES_QUERY_ERROR, str, str2);
    }

    static /* synthetic */ HttpUriRequest hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) throws IOException {
        HttpPost httpPost = new HttpPost("http://www.google.com/fusiontables/v2/query");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new BasicNameValuePair("sql", str));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
        urlEncodedFormEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(urlEncodedFormEntity);
        return httpPost;
    }

    static /* synthetic */ String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
        Log.i("FUSION", "parsetoJSonSqlCreate :".concat(String.valueOf(str)));
        StringBuilder sb = new StringBuilder();
        String trim = str.trim();
        String trim2 = trim.substring(12, trim.indexOf(40)).trim();
        String[] split = trim.substring(trim.indexOf(40) + 1, trim.indexOf(41)).split(",");
        sb.append("{'columns':[");
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb.append("{'name': '" + split2[0].trim() + "', 'type': '" + split2[1].trim() + "'}");
            if (i < split.length - 1) {
                sb.append(",");
            }
        }
        sb.append("],");
        sb.append("'isExportable':'true',");
        sb.append("'name': '" + trim2 + "'");
        sb.append("}");
        sb.insert(0, "CREATE TABLE ");
        Log.i("FUSION", "result = " + sb.toString());
        return sb.toString();
    }
}
