package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "AirTable Component", iconName = "images/airtable.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.INTERNET"})
public class MakeroidAirtable extends AndroidNonvisibleComponent {
    private String Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW = "Grid view";
    private String IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt;
    private String WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA;
    private final Activity activity;
    private int cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3;
    private String sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;

    public MakeroidAirtable(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        Log.d("Makeroid Airtable", "Makeroid Airtable Created");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ApiKey() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Your apiKey")
    public void ApiKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String BaseId() {
        return this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void BaseId(String str) {
        this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String TableName() {
        return this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void TableName(String str) {
        this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ViewName() {
        return this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW;
    }

    @DesignerProperty(defaultValue = "Grid view", editorType = "string")
    @SimpleProperty
    public void ViewName(String str) {
        this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW = str;
    }

    public void GetAllData() throws Exception {
        String str = FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW).replaceAll(" ", "%20")).openConnection();
            httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, str);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
            httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            final JSONArray jSONArray = jSONObject.getJSONArray("records");
            if (jSONObject.has("offset")) {
                GetAllWithOffset(jSONObject.getString("offset"), jSONArray);
                return;
            }
            this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA = jSONArray.toString();
            this.cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = httpURLConnection.getResponseCode();
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    MakeroidAirtable makeroidAirtable = MakeroidAirtable.this;
                    makeroidAirtable.GotAllRows(MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(makeroidAirtable), MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidAirtable.this), jSONArray.length());
                }
            });
        } catch (Exception e) {
            Log.e("GetAllData", e.getMessage());
        }
    }

    public void GetAllWithOffset(String str, JSONArray jSONArray) {
        String str2 = FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW + "&offset=" + str).replaceAll(" ", "%20")).openConnection();
            httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, str2);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
            httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray jSONArray2 = jSONObject.getJSONArray("records");
            for (int i = 0; i < jSONArray2.length(); i++) {
                jSONArray.put(jSONArray2.getJSONObject(i));
            }
            if (jSONObject.has("offset")) {
                GetAllWithOffset(jSONObject.getString("offset"), jSONArray);
                return;
            }
            this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA = jSONArray.toString();
            this.cxDeivMnEpGbjLe4A1R1VhwwbdnX12vGTzD2ggofq0XWzd0wEZ70Vx6p1IyPlwn3 = httpURLConnection.getResponseCode();
            final int length = jSONArray.length();
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    MakeroidAirtable makeroidAirtable = MakeroidAirtable.this;
                    makeroidAirtable.GotAllRows(MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(makeroidAirtable), MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidAirtable.this), length);
                }
            });
        } catch (Exception e) {
            Log.e("GetAllData", e.getMessage());
        }
    }

    @SimpleFunction(description = "Gets cell data")
    public void GetCell(final int i, final String str) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.Cell(i, str);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void Cell(int i, String str) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?fields[]=" + str + "&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW).replaceAll(" ", "%20")).openConnection();
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp);
        httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
        final int responseCode = httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        JSONObject jSONObject = new JSONObject(sb.toString());
        JSONArray jSONArray = jSONObject.getJSONArray("records");
        if (jSONObject.has("offset")) {
            GetCellWithOffset(jSONObject.getString("offset"), jSONArray, str, i);
            return;
        }
        int i2 = i - 1;
        JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
        final String string = jSONObject2.getString(CommonProperties.ID);
        final String string2 = jSONObject2.getString("createdTime");
        final String string3 = ((JSONObject) jSONArray.get(i2)).getJSONObject("fields").getString(str);
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                MakeroidAirtable.this.GotCell(responseCode, string3, string, string2);
            }
        });
    }

    public void GetCellWithOffset(String str, JSONArray jSONArray, String str2, int i) {
        String str3 = FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?fields[]=" + str2 + "&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW + "&offset=" + str).replaceAll(" ", "%20")).openConnection();
            httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, str3);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
            final int responseCode = httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray jSONArray2 = jSONObject.getJSONArray("records");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                jSONArray.put(jSONArray2.getJSONObject(i2));
            }
            if (jSONObject.has("offset")) {
                GetCellWithOffset(jSONObject.getString("offset"), jSONArray, str2, i);
                return;
            }
            int i3 = i - 1;
            JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
            final String string = jSONObject2.getString(CommonProperties.ID);
            final String string2 = jSONObject2.getString("createdTime");
            final String string3 = ((JSONObject) jSONArray.get(i3)).getJSONObject("fields").getString(str2);
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    MakeroidAirtable.this.GotCell(responseCode, string3, string, string2);
                }
            });
        } catch (Exception e) {
            Log.e("GetCell", e.getMessage());
        }
    }

    @SimpleFunction(description = "Gets column data")
    public void GetColumn(final String str, final int i) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.Column(str, i);
                } catch (Throwable th) {
                    Log.e("GetColumn", th.getMessage());
                }
            }
        });
    }

    public void Column(String str, int i) throws Exception {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?fields[]=" + str + "&maxRecords=" + i + "&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW).replaceAll(" ", "%20")).openConnection();
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp);
        httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
        final int responseCode = httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        JSONObject jSONObject = new JSONObject(sb.toString());
        JSONArray jSONArray = jSONObject.getJSONArray("records");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            String string = jSONObject2.getString(CommonProperties.ID);
            String string2 = jSONObject2.getString("createdTime");
            String string3 = jSONObject2.getJSONObject("fields").getString(str);
            arrayList.add(string);
            arrayList2.add(string2);
            arrayList3.add(string3);
        }
        if (jSONObject.has("offset")) {
            ColumnWithOffset(arrayList, arrayList2, arrayList3, str, i, jSONObject.getString("offset"));
            return;
        }
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                MakeroidAirtable.this.GotColumn(responseCode, arrayList3, arrayList, arrayList2);
            }
        });
    }

    public void ColumnWithOffset(List<String> list, List<String> list2, List<String> list3, String str, int i, String str2) throws Exception {
        String str3 = str;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?fields[]=" + str3 + "&maxRecords=" + i + "&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW + "&offset=" + str2).replaceAll(" ", "%20")).openConnection();
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp);
        httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
        final int responseCode = httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            sb.append(readLine);
        }
        bufferedReader.close();
        JSONObject jSONObject = new JSONObject(sb.toString());
        JSONArray jSONArray = jSONObject.getJSONArray("records");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            String string = jSONObject2.getString(CommonProperties.ID);
            String string2 = jSONObject2.getString("createdTime");
            String string3 = jSONObject2.getJSONObject("fields").getString(str3);
            List<String> list4 = list;
            list.add(string);
            List<String> list5 = list2;
            list2.add(string2);
            List<String> list6 = list3;
            list3.add(string3);
        }
        List<String> list7 = list;
        List<String> list8 = list2;
        List<String> list9 = list3;
        if (jSONObject.has("offset")) {
            ColumnWithOffset(list, list2, list3, str, i, jSONObject.getString("offset"));
            return;
        }
        final List<String> list10 = list3;
        final List<String> list11 = list;
        final List<String> list12 = list2;
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                MakeroidAirtable.this.GotColumn(responseCode, list10, list11, list12);
            }
        });
    }

    @SimpleFunction(description = "Gets row data")
    public void GetRow(final int i) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.getRow(i);
                } catch (Throwable th) {
                    Log.e("GetRow", th.getMessage());
                }
            }
        });
    }

    public void getRow(int i) {
        String str = FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
        final ArrayList arrayList = new ArrayList();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW).replaceAll(" ", "%20")).openConnection();
            httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, str);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
            final int responseCode = httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray jSONArray = jSONObject.getJSONArray("records");
            JSONObject jSONObject2 = jSONArray.getJSONObject(i - 1).getJSONObject("fields");
            JSONArray names = jSONObject2.names();
            for (int i2 = 0; i2 < names.length(); i2++) {
                arrayList.add(jSONObject2.getString(names.getString(i2)));
            }
            if (jSONObject.has("offset")) {
                RowWithOffset(i, jSONObject.getString("offset"), arrayList, jSONArray);
            } else {
                this.activity.runOnUiThread(new Runnable() {
                    public final void run() {
                        MakeroidAirtable.this.GotRow(responseCode, arrayList);
                    }
                });
            }
        } catch (Exception e) {
            Log.e("GetRow", e.getMessage());
        }
    }

    public void RowWithOffset(int i, String str, final List<String> list, JSONArray jSONArray) {
        String str2 = FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + "?&view=" + this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW + "&offset=" + str).replaceAll(" ", "%20")).openConnection();
            httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, str2);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
            final int responseCode = httpURLConnection.getResponseCode();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray jSONArray2 = jSONObject.getJSONArray("records");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                jSONArray.put(jSONArray2.getJSONObject(i2));
            }
            if (jSONObject.has("offset")) {
                RowWithOffset(i, jSONObject.getString("offset"), list, jSONArray);
                return;
            }
            JSONObject jSONObject2 = jSONArray.getJSONObject(i - 1).getJSONObject("fields");
            JSONArray names = jSONObject2.names();
            for (int i3 = 0; i3 < names.length(); i3++) {
                list.add(jSONObject2.getString(names.getString(i3)));
            }
            this.activity.runOnUiThread(new Runnable() {
                public final void run() {
                    MakeroidAirtable.this.GotRow(responseCode, list);
                }
            });
        } catch (Exception e) {
            Log.e("GetRow", e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the value of a cell")
    public void SetCell(final int i, final String str, final String str2) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.setCell(i, str, str2);
                } catch (Throwable th) {
                    Log.e("SetCell", th.getMessage());
                    throw new RuntimeException(th.getMessage());
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:24:0x00f0=Splitter:B:24:0x00f0, B:30:0x00fe=Splitter:B:30:0x00fe} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCell(int r9, java.lang.String r10, java.lang.String r11) throws java.lang.Exception {
        /*
            r8 = this;
            java.lang.String r0 = "/"
            java.lang.String r1 = "Makeroid Airtable"
            r8.GetAllData()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Bearer "
            r2.<init>(r3)
            java.lang.String r3 = r8.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            org.json.JSONArray r3 = new org.json.JSONArray
            java.lang.String r4 = r8.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA
            r3.<init>(r4)
            r4 = 1
            int r9 = r9 - r4
            org.json.JSONObject r9 = r3.getJSONObject(r9)
            java.lang.String r3 = "id"
            java.lang.String r9 = r9.getString(r3)
            r3 = 0
            java.net.URL r5 = new java.net.URL     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r7 = "https://api.airtable.com/v0/"
            r6.<init>(r7)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r7 = r8.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r6.append(r0)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r7 = r8.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r6.append(r0)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r6.append(r9)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r9 = r6.toString()     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r0 = " "
            java.lang.String r6 = "%20"
            java.lang.String r9 = r9.replaceAll(r0, r6)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r5.<init>(r9)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.net.URLConnection r9 = r5.openConnection()     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r5 = "{\"fields\": {\""
            r0.<init>(r5)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r0.append(r10)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r10 = "\": \""
            r0.append(r10)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r0.append(r11)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r10 = "\"},\"typecast\": true}"
            r0.append(r10)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r10 = r0.toString()     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r11 = "Content-Type"
            java.lang.String r0 = "application/json"
            r9.setRequestProperty(r11, r0)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r11 = "Authorization"
            r9.addRequestProperty(r11, r2)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.lang.String r11 = "PATCH"
            r9.setRequestMethod(r11)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r9.setDoOutput(r4)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.io.DataOutputStream r11 = new java.io.DataOutputStream     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            java.io.OutputStream r0 = r9.getOutputStream()     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r11.<init>(r0)     // Catch:{ MalformedURLException -> 0x00fd, Exception -> 0x00ef }
            r11.writeBytes(r10)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r11.flush()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            int r10 = r9.getResponseCode()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r0 = 200(0xc8, float:2.8E-43)
            if (r10 != r0) goto L_0x00cc
            int r10 = r9.getResponseCode()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.io.InputStream r3 = r9.getInputStream()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r2.<init>(r3)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r0.<init>(r2)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
        L_0x00b4:
            java.lang.String r2 = r0.readLine()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            if (r2 == 0) goto L_0x00c5
            android.app.Activity r2 = r8.activity     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            com.google.appinventor.components.runtime.MakeroidAirtable$5 r3 = new com.google.appinventor.components.runtime.MakeroidAirtable$5     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r3.<init>(r10)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r2.runOnUiThread(r3)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            goto L_0x00b4
        L_0x00c5:
            r9.disconnect()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r11.close()
            return
        L_0x00cc:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.lang.String r2 = "Failed : HTTP error code : "
            r0.<init>(r2)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            int r9 = r9.getResponseCode()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r0.append(r9)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            java.lang.String r9 = r0.toString()     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            r10.<init>(r9)     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
            throw r10     // Catch:{ MalformedURLException -> 0x00ea, Exception -> 0x00e7, all -> 0x00e4 }
        L_0x00e4:
            r9 = move-exception
            r3 = r11
            goto L_0x010b
        L_0x00e7:
            r9 = move-exception
            r3 = r11
            goto L_0x00f0
        L_0x00ea:
            r9 = move-exception
            r3 = r11
            goto L_0x00fe
        L_0x00ed:
            r9 = move-exception
            goto L_0x010b
        L_0x00ef:
            r9 = move-exception
        L_0x00f0:
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00ed }
            android.util.Log.e(r1, r9)     // Catch:{ all -> 0x00ed }
            if (r3 == 0) goto L_0x010a
            r3.close()
            return
        L_0x00fd:
            r9 = move-exception
        L_0x00fe:
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00ed }
            android.util.Log.e(r1, r9)     // Catch:{ all -> 0x00ed }
            if (r3 == 0) goto L_0x010a
            r3.close()
        L_0x010a:
            return
        L_0x010b:
            if (r3 == 0) goto L_0x0110
            r3.close()
        L_0x0110:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidAirtable.setCell(int, java.lang.String, java.lang.String):void");
    }

    @SimpleFunction(description = "Deletes the given row")
    public void DeleteRowNum(final int i) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.deleteRowNum(i);
                } catch (Throwable th) {
                    Log.e("DeleteRowNum", th.getMessage());
                }
            }
        });
    }

    public void deleteRowNum(int i) throws Exception {
        GetAllData();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(("https://api.airtable.com/v0/" + this.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW + InternalZipConstants.ZIP_FILE_SEPARATOR + this.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt + InternalZipConstants.ZIP_FILE_SEPARATOR + new JSONArray(this.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA).getJSONObject(i - 1).getString(CommonProperties.ID)).replaceAll(" ", "%20")).openConnection();
        httpURLConnection.setRequestProperty(Constants.AUTHORIZATION_HEADER, FusiontablesControl.AUTHORIZATION_HEADER_PREFIX + this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp);
        httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/json");
        httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_DELETE);
        final int responseCode = httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        do {
        } while (bufferedReader.readLine() != null);
        bufferedReader.close();
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                MakeroidAirtable.this.DeletedRowByNumber(responseCode);
            }
        });
    }

    @SimpleFunction(description = "Creates a new row")
    public void CreateRow(final YailList yailList, final YailList yailList2) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.createRow(yailList, yailList2);
                } catch (Throwable th) {
                    Log.e("CreateRow", th.getMessage());
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:34:0x00fa=Splitter:B:34:0x00fa, B:28:0x00ec=Splitter:B:28:0x00ec} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createRow(com.google.appinventor.components.runtime.util.YailList r7, com.google.appinventor.components.runtime.util.YailList r8) throws java.lang.Exception {
        /*
            r6 = this;
            java.lang.String r0 = "Makeroid Airtable"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Bearer "
            r1.<init>(r2)
            java.lang.String r2 = r6.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String[] r7 = r7.toStringArray()
            java.lang.String[] r8 = r8.toStringArray()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            r3 = 0
        L_0x0020:
            int r4 = r7.length
            if (r3 >= r4) goto L_0x002d
            r4 = r7[r3]
            r5 = r8[r3]
            r2.put(r4, r5)
            int r3 = r3 + 1
            goto L_0x0020
        L_0x002d:
            java.lang.String r7 = r2.toString()
            r8 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = "https://api.airtable.com/v0/"
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = r6.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r3.append(r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = r6.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r3.append(r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r3 = r3.toString()     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = " "
            java.lang.String r5 = "%20"
            java.lang.String r3 = r3.replaceAll(r4, r5)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r2.<init>(r3)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r4 = "{\"fields\":"
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r3.append(r7)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r7 = ",\"typecast\": true}"
            r3.append(r7)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r7 = r3.toString()     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r3 = "Content-Type"
            java.lang.String r4 = "application/json"
            r2.setRequestProperty(r3, r4)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r3 = "Authorization"
            r2.addRequestProperty(r3, r1)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.lang.String r1 = "POST"
            r2.setRequestMethod(r1)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r1 = 1
            r2.setDoOutput(r1)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            java.io.OutputStream r3 = r2.getOutputStream()     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r1.<init>(r3)     // Catch:{ MalformedURLException -> 0x00f9, Exception -> 0x00eb }
            r1.writeBytes(r7)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r1.flush()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            int r7 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L_0x00c8
            int r7 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.io.InputStream r4 = r2.getInputStream()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r8.<init>(r3)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
        L_0x00b0:
            java.lang.String r3 = r8.readLine()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            if (r3 == 0) goto L_0x00c1
            android.app.Activity r3 = r6.activity     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            com.google.appinventor.components.runtime.MakeroidAirtable$9 r4 = new com.google.appinventor.components.runtime.MakeroidAirtable$9     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r4.<init>(r7)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r3.runOnUiThread(r4)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            goto L_0x00b0
        L_0x00c1:
            r2.disconnect()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r1.close()
            return
        L_0x00c8:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.lang.String r3 = "Failed : HTTP error code : "
            r8.<init>(r3)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            int r2 = r2.getResponseCode()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r8.append(r2)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            java.lang.String r8 = r8.toString()     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            r7.<init>(r8)     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
            throw r7     // Catch:{ MalformedURLException -> 0x00e6, Exception -> 0x00e3, all -> 0x00e0 }
        L_0x00e0:
            r7 = move-exception
            r8 = r1
            goto L_0x0107
        L_0x00e3:
            r7 = move-exception
            r8 = r1
            goto L_0x00ec
        L_0x00e6:
            r7 = move-exception
            r8 = r1
            goto L_0x00fa
        L_0x00e9:
            r7 = move-exception
            goto L_0x0107
        L_0x00eb:
            r7 = move-exception
        L_0x00ec:
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x00e9 }
            android.util.Log.e(r0, r7)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x0106
            r8.close()
            return
        L_0x00f9:
            r7 = move-exception
        L_0x00fa:
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x00e9 }
            android.util.Log.e(r0, r7)     // Catch:{ all -> 0x00e9 }
            if (r8 == 0) goto L_0x0106
            r8.close()
        L_0x0106:
            return
        L_0x0107:
            if (r8 == 0) goto L_0x010c
            r8.close()
        L_0x010c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidAirtable.createRow(com.google.appinventor.components.runtime.util.YailList, com.google.appinventor.components.runtime.util.YailList):void");
    }

    @SimpleFunction(description = "Updates the given row data")
    public void UpdateRowByNum(final int i, final YailList yailList, final YailList yailList2) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.updateRowByNum(i, yailList, yailList2);
                } catch (Throwable th) {
                    Log.e("UpdateRowByNum", th.getMessage());
                }
            }
        });
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateRowByNum(int r8, com.google.appinventor.components.runtime.util.YailList r9, com.google.appinventor.components.runtime.util.YailList r10) throws java.lang.Exception {
        /*
            r7 = this;
            java.lang.String r0 = "/"
            r7.GetAllData()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Bearer "
            r1.<init>(r2)
            java.lang.String r2 = r7.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String[] r9 = r9.toStringArray()
            java.lang.String[] r10 = r10.toStringArray()
            org.json.JSONArray r2 = new org.json.JSONArray
            java.lang.String r3 = r7.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA
            r2.<init>(r3)
            r3 = 1
            int r8 = r8 - r3
            org.json.JSONObject r8 = r2.getJSONObject(r8)
            java.lang.String r2 = "id"
            java.lang.String r8 = r8.getString(r2)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            r4 = 0
        L_0x0036:
            int r5 = r9.length
            if (r4 >= r5) goto L_0x0043
            r5 = r9[r4]
            r6 = r10[r4]
            r2.put(r5, r6)
            int r4 = r4 + 1
            goto L_0x0036
        L_0x0043:
            java.lang.String r9 = r2.toString()
            r10 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0101 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0101 }
            java.lang.String r5 = "https://api.airtable.com/v0/"
            r4.<init>(r5)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r5 = r7.sOlouMp7GHVTpUn4YBGbQbUCVWieKOYZN8RaxLZS4Jb0AfyW3N6tLVaVFyvseW     // Catch:{ Exception -> 0x0101 }
            r4.append(r5)     // Catch:{ Exception -> 0x0101 }
            r4.append(r0)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r5 = r7.IMDN6rSQcZszgVNpJJVzEqq6OCV4rs9tTZyufLfJdSMnHOCoW11dHNeXoNpXCGNt     // Catch:{ Exception -> 0x0101 }
            r4.append(r5)     // Catch:{ Exception -> 0x0101 }
            r4.append(r0)     // Catch:{ Exception -> 0x0101 }
            r4.append(r8)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r8 = r4.toString()     // Catch:{ Exception -> 0x0101 }
            java.lang.String r0 = " "
            java.lang.String r4 = "%20"
            java.lang.String r8 = r8.replaceAll(r0, r4)     // Catch:{ Exception -> 0x0101 }
            r2.<init>(r8)     // Catch:{ Exception -> 0x0101 }
            java.net.URLConnection r8 = r2.openConnection()     // Catch:{ Exception -> 0x0101 }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ Exception -> 0x0101 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0101 }
            java.lang.String r2 = "{\"fields\":"
            r0.<init>(r2)     // Catch:{ Exception -> 0x0101 }
            r0.append(r9)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r9 = ",\"typecast\": true}"
            r0.append(r9)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r9 = r0.toString()     // Catch:{ Exception -> 0x0101 }
            java.lang.String r0 = "Content-Type"
            java.lang.String r2 = "application/json"
            r8.setRequestProperty(r0, r2)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r0 = "Authorization"
            r8.addRequestProperty(r0, r1)     // Catch:{ Exception -> 0x0101 }
            java.lang.String r0 = "PUT"
            r8.setRequestMethod(r0)     // Catch:{ Exception -> 0x0101 }
            r8.setDoOutput(r3)     // Catch:{ Exception -> 0x0101 }
            java.io.DataOutputStream r0 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0101 }
            java.io.OutputStream r1 = r8.getOutputStream()     // Catch:{ Exception -> 0x0101 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0101 }
            r0.writeBytes(r9)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r0.flush()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            int r9 = r8.getResponseCode()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r10 = 200(0xc8, float:2.8E-43)
            if (r9 != r10) goto L_0x00e1
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            java.io.InputStream r1 = r8.getInputStream()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r10.<init>(r1)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
        L_0x00c5:
            java.lang.String r10 = r9.readLine()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            if (r10 == 0) goto L_0x00da
            int r10 = r8.getResponseCode()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            android.app.Activity r1 = r7.activity     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            com.google.appinventor.components.runtime.MakeroidAirtable$11 r2 = new com.google.appinventor.components.runtime.MakeroidAirtable$11     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r2.<init>(r10)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r1.runOnUiThread(r2)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            goto L_0x00c5
        L_0x00da:
            r8.disconnect()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r0.close()
            return
        L_0x00e1:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            java.lang.String r1 = "Failed : HTTP error code : "
            r10.<init>(r1)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            int r8 = r8.getResponseCode()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r10.append(r8)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            java.lang.String r8 = r10.toString()     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            r9.<init>(r8)     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
            throw r9     // Catch:{ Exception -> 0x00fc, all -> 0x00f9 }
        L_0x00f9:
            r8 = move-exception
            r10 = r0
            goto L_0x0111
        L_0x00fc:
            r8 = move-exception
            r10 = r0
            goto L_0x0102
        L_0x00ff:
            r8 = move-exception
            goto L_0x0111
        L_0x0101:
            r8 = move-exception
        L_0x0102:
            java.lang.String r9 = "Makeroid Airtable"
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x00ff }
            android.util.Log.e(r9, r8)     // Catch:{ all -> 0x00ff }
            if (r10 == 0) goto L_0x0110
            r10.close()
        L_0x0110:
            return
        L_0x0111:
            if (r10 == 0) goto L_0x0116
            r10.close()
        L_0x0116:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidAirtable.updateRowByNum(int, com.google.appinventor.components.runtime.util.YailList, com.google.appinventor.components.runtime.util.YailList):void");
    }

    @SimpleFunction(description = "Gets all rows")
    public void GetAllRows() {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                try {
                    MakeroidAirtable.this.GetAllData();
                } catch (Throwable th) {
                    Log.e("GetAllRows", th.getMessage());
                }
            }
        });
    }

    @SimpleEvent(description = "Triggered when receiving cell data. ResponseCode is a number, the other ones are strings")
    public void GotCell(int i, String str, String str2, String str3) {
        EventDispatcher.dispatchEvent(this, "GotCell", Integer.valueOf(i), str, str2, str3);
    }

    @SimpleEvent(description = "Triggered when receiving column data. ResponseCode is a number, the other ones are lists")
    public void GotColumn(int i, List<String> list, List<String> list2, List<String> list3) {
        EventDispatcher.dispatchEvent(this, "GotColumn", Integer.valueOf(i), list, list2, list3);
    }

    @SimpleEvent(description = "Triggered when receiving row data. ResponseCode is a number, Values is a list")
    public void GotRow(int i, List<String> list) {
        EventDispatcher.dispatchEvent(this, "GotRow", Integer.valueOf(i), list);
    }

    @SimpleEvent(description = "Triggered when changing cell data. ResponseCode is a number")
    public void CellChanged(int i) {
        EventDispatcher.dispatchEvent(this, "CellChanged", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when destroying a row. ResponseCode is a number")
    public void DeletedRowByNumber(int i) {
        EventDispatcher.dispatchEvent(this, "DeletedRowByNumber", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when creating a row. ResponseCode is a number")
    public void RowCreated(int i) {
        EventDispatcher.dispatchEvent(this, "RowCreated", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when updating a row. ResponseCode is a number")
    public void RowUpdated(int i) {
        EventDispatcher.dispatchEvent(this, "RowUpdated", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when getting all rows. ResponseCode is a number, ResponseContent is a string")
    public void GotAllRows(int i, String str, int i2) {
        EventDispatcher.dispatchEvent(this, "GotAllRows", Integer.valueOf(i), str, Integer.valueOf(i2));
    }
}
