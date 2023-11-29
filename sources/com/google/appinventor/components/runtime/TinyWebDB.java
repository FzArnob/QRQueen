package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import org.apache.http.message.BasicNameValuePair;
import org.jose4j.jwx.HeaderParameterNames;
import org.json.JSONArray;
import org.json.JSONException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that communicates with a Web service to store and retrieve information.", iconName = "images/tinyWebDB.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.INTERNET"})
public class TinyWebDB extends AndroidNonvisibleComponent implements Component {
    private String Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5 = "http://tinywebdb.builder.makeroid.io/";
    /* access modifiers changed from: private */
    public Handler androidUIHandler = new Handler();

    public TinyWebDB(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ServiceURL() {
        return this.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5;
    }

    @DesignerProperty(defaultValue = "http://tinywebdb.builder.makeroid.io", editorType = "string")
    @SimpleProperty(description = "Specifies the URL of the  Web service.")
    public void ServiceURL(String str) {
        this.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5 = str;
    }

    @SimpleFunction(description = "Asks the Web service to store the given value under the given tag.")
    public void StoreValue(final String str, final Object obj) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                TinyWebDB.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB.this, str, obj);
            }
        });
    }

    @SimpleEvent(description = "Event indicating that a StoreValue server request has succeeded.")
    public void ValueStored() {
        EventDispatcher.dispatchEvent(this, "ValueStored", new Object[0]);
    }

    @SimpleFunction(description = "GetValue asks the Web service to get the value stored under the given tag.")
    public void GetValue(final String str) {
        AsynchUtil.runAsynchronously(new Runnable() {
            public final void run() {
                TinyWebDB.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB.this, str);
            }
        });
    }

    @SimpleEvent(description = "Indicates that a GetValue server request has succeeded.")
    public void GotValue(String str, Object obj) {
        EventDispatcher.dispatchEvent(this, "GotValue", str, obj);
    }

    @SimpleEvent(description = "Indicates that the communication with the Web service signaled an error.")
    public void WebServiceError(String str) {
        EventDispatcher.dispatchEvent(this, "WebServiceError", str);
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB tinyWebDB, String str, Object obj) {
        AnonymousClass2 r0 = new AsyncCallbackPair<String>() {
            public final void onFailure(final String str) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        TinyWebDB.this.WebServiceError(str);
                    }
                });
            }

            public final /* synthetic */ void onSuccess(Object obj) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        TinyWebDB.this.ValueStored();
                    }
                });
            }
        };
        try {
            WebServiceUtil.getInstance().postCommand(tinyWebDB.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5, "storeavalue", Lists.newArrayList(new BasicNameValuePair(HeaderParameterNames.AUTHENTICATION_TAG, str), new BasicNameValuePair(CommonProperties.VALUE, JsonUtil.getJsonRepresentation(obj))), r0);
        } catch (JSONException unused) {
            throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(TinyWebDB tinyWebDB, final String str) {
        AnonymousClass4 r0 = new AsyncCallbackPair<JSONArray>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                final Object obj2;
                JSONArray jSONArray = (JSONArray) obj;
                if (jSONArray == null) {
                    TinyWebDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            TinyWebDB tinyWebDB = TinyWebDB.this;
                            tinyWebDB.WebServiceError("The Web server did not respond to the get value request for the tag " + str + ".");
                        }
                    });
                    return;
                }
                try {
                    final String string = jSONArray.getString(1);
                    String string2 = jSONArray.getString(2);
                    if (string2.length() == 0) {
                        obj2 = "";
                    } else {
                        obj2 = JsonUtil.getObjectFromJson(string2, true);
                    }
                    TinyWebDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            TinyWebDB.this.GotValue(string, obj2);
                        }
                    });
                } catch (JSONException unused) {
                    TinyWebDB.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            TinyWebDB tinyWebDB = TinyWebDB.this;
                            tinyWebDB.WebServiceError("The Web server returned a garbled value for the tag " + str + ".");
                        }
                    });
                }
            }

            public final void onFailure(final String str) {
                TinyWebDB.this.androidUIHandler.post(new Runnable() {
                    public final void run() {
                        TinyWebDB.this.WebServiceError(str);
                    }
                });
            }
        };
        WebServiceUtil.getInstance().postCommandReturningArray(tinyWebDB.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5, "getvalue", Lists.newArrayList(new BasicNameValuePair(HeaderParameterNames.AUTHENTICATION_TAG, str)), r0);
    }
}
