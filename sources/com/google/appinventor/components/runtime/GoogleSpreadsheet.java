package com.google.appinventor.components.runtime;

import android.app.Activity;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", iconName = "images/spreadsheet.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.INTERNET"})
public class GoogleSpreadsheet extends AndroidNonvisibleComponent implements Component {
    private final String LOG_TAG = "GoogleSpreadsheet";
    private final int M2XSrcNVsTj86KbWYhtzmFwqCl4FRN4hJC3YQ2jC5nTG9V14APZgqJsIs4HMKSeu = 200;
    private String Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = "";
    private String TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = "Your Sheet Name";
    protected Activity activity;
    private final String eaS298peKlTpqlGRGLMTdk3sY259qoFGMqAzTE98DZy2JVNgCwB414XzHrUPTC = "?dev=true";
    private String[][] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ((String[][]) Array.newInstance(String.class, new int[]{5000, 200}));
    private final int iLHecwZEZ1qFKybU5FAR3aqOIDdUsGdPPaR0D5I2BkdnPzgzEMwtpOPW3wNOFXm = 5000;
    private Map<String, Integer> vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = new HashMap();

    public GoogleSpreadsheet(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
    }

    @DesignerProperty(defaultValue = "Enter the Cloudstitch API Endpoint", editorType = "string")
    @SimpleProperty(description = "The Cloudstitch API endpoint.")
    public void ApiEndpoint(String str) {
        this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Retrieves the Cloudstich API endpoint.")
    public String ApiEndpoint() {
        return this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL;
    }

    @DesignerProperty(defaultValue = "Enter Spreadsheet name", editorType = "string")
    @SimpleProperty(description = "The Google spreadsheet name")
    public void SheetName(String str) {
        this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Retrieves the Spreadsheet name")
    public String SheetName() {
        return this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt;
    }

    @SimpleFunction(description = "For the given ApiEndpoint and Spreadsheet, retrieves all data from the spreadsheet.")
    public void GetSpreadsheetData() {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                GoogleSpreadsheet.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleSpreadsheet.this);
            }
        });
    }

    @SimpleFunction(description = "Retrieves all data for an entire column")
    public YailList GetColumnData(String str) {
        if (!this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.containsKey(str.toLowerCase())) {
            return YailList.makeEmptyList();
        }
        Integer num = this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.get(str.toLowerCase());
        String[] strArr = new String[this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.length];
        int i = 0;
        while (true) {
            String[][] strArr2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (i >= strArr2.length) {
                return YailList.makeList((Object[]) strArr);
            }
            strArr[i] = strArr2[i][num.intValue()];
            i++;
        }
    }

    @SimpleFunction(description = "Retrieves data for a specific row number")
    public YailList GetRowData(int i) {
        return YailList.makeList((Object[]) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i - 1]);
    }

    @SimpleFunction(description = "For the given columnName and rowNumber, retrieves the spreadsheet cell data")
    public String GetCellData(String str, int i) {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i - 1][this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.get(str.toLowerCase()).intValue()];
    }

    @SimpleFunction(description = "Stores data into spreadsheet. dataToStore must be in json format. Will trigger AfterAction")
    public void StoreData(final String str) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                GoogleSpreadsheet.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleSpreadsheet.this, str);
            }
        });
    }

    @SimpleEvent(description = "Triggered after an actions such as storing data has occurred. ")
    public void AfterAction(final boolean z, final String str, final String str2) {
        this.activity.runOnUiThread(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(GoogleSpreadsheet.this, "AfterAction", Boolean.valueOf(z), str, str2);
            }
        });
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleSpreadsheet googleSpreadsheet) {
        String str;
        String trim = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL.trim();
        googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = trim;
        if (trim.toLowerCase().endsWith("?dev=true")) {
            str = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL;
        } else {
            str = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL + "?dev=true";
            googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = str;
        }
        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_GET);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            if (httpURLConnection.getResponseCode() == 200) {
                JSONArray jSONArray = (JSONArray) new JSONObject(sb.toString()).get(googleSpreadsheet.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String[] split = jSONArray.get(i).toString().replace("{", "").replace("}", "").replace("\"", "").split(",");
                    if (i == 0) {
                        googleSpreadsheet.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (String[][]) Array.newInstance(String.class, new int[]{jSONArray.length(), split.length});
                    }
                    for (int i2 = 0; i2 < split.length; i2++) {
                        String[] split2 = split[i2].split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
                        googleSpreadsheet.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[i][i2] = split2[1].trim();
                        if (i == 0) {
                            googleSpreadsheet.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.put(split2[0].trim().toLowerCase(), Integer.valueOf(i2));
                        }
                    }
                }
                googleSpreadsheet.AfterAction(true, sb.toString(), "GetData");
                return;
            }
            googleSpreadsheet.AfterAction(false, new StringBuilder(httpURLConnection.getResponseMessage()).toString(), "GetData");
        } catch (Exception e) {
            googleSpreadsheet.AfterAction(false, e.getLocalizedMessage(), "GetData");
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(GoogleSpreadsheet googleSpreadsheet, String str) {
        String str2;
        String trim = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL.trim();
        googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = trim;
        if (trim.toLowerCase().endsWith("?dev=true")) {
            str2 = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL;
        } else {
            str2 = googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL + "?dev=true";
            googleSpreadsheet.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = str2;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2 + InternalZipConstants.ZIP_FILE_SEPARATOR + googleSpreadsheet.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(DefaultHttpClient.METHOD_POST);
            httpURLConnection.setRequestProperty(DefaultHttpClient.CONTENT_TYPE_KEY, "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(str.length()));
            httpURLConnection.getOutputStream().write(str.getBytes());
            googleSpreadsheet.AfterAction(true, httpURLConnection.getResponseMessage(), "StoreData");
        } catch (Exception e) {
            googleSpreadsheet.AfterAction(false, e.getMessage(), "StoreData");
        }
    }
}
