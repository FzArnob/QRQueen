package com.google.appinventor.components.runtime;

import android.app.Activity;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Use this component to translate words and sentences between different languages. This component needs Internet access, as it will request translations to the Yandex.Translate service. Specify the source and target language in the form source-target using two letter language codes. So\"en-es\" will translate from English to Spanish while \"es-ru\" will translate from Spanish to Russian. If you leave out the source language, the service will attempt to detect the source language. So providing just \"es\" will attempt to detect the source language and translate it to Spanish.<p /> This component is powered by the Yandex translation service.  See http://api.yandex.com/translate/ for more information, including the list of available languages and the meanings of the language codes and status codes. <p />Note: Translation happens asynchronously in the background. When the translation is complete, the \"GotTranslation\" event is triggered.", iconName = "images/yandex.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.INTERNET"})
public final class YandexTranslate extends AndroidNonvisibleComponent {
    public static final String YANDEX_TRANSLATE_SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private final Activity activity;
    private String rJWxANjNJzenLOhFGOM8bv7U8Qw2bZkEFFRrWhEK33fiMPh8xnV3JndH9sVxgqEq = "";

    public YandexTranslate(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.setYandexTranslateTagline();
        this.activity = componentContainer.$context();
    }

    @SimpleFunction(description = "By providing a target language to translate to (for instance, 'es' for Spanish, 'en' for English, or 'ru' for Russian), and a word or sentence to translate, this method will request a translation to the Yandex.Translate service.\nOnce the text is translated by the external service, the event GotTranslation will be executed.\nNote: Yandex.Translate will attempt to detect the source language. You can also specify prepending it to the language translation. I.e., es-ru will specify Spanish to Russian translation.")
    public final void RequestTranslation(final String str, final String str2) {
        if (this.rJWxANjNJzenLOhFGOM8bv7U8Qw2bZkEFFRrWhEK33fiMPh8xnV3JndH9sVxgqEq.equals("")) {
            this.form.dispatchErrorOccurredEvent(this, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_NO_KEY_FOUND, new Object[0]);
        } else {
            AsynchUtil.runAsynchronously(new Runnable() {
                public final void run() {
                    try {
                        YandexTranslate.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YandexTranslate.this, str, str2);
                    } catch (JSONException unused) {
                        YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_JSON_RESPONSE, new Object[0]);
                    } catch (Exception unused2) {
                        YandexTranslate.this.form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation", ErrorMessages.ERROR_TRANSLATE_SERVICE_NOT_AVAILABLE, new Object[0]);
                    }
                }
            });
        }
    }

    private static String getResponseContent(HttpURLConnection httpURLConnection) throws IOException {
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (contentEncoding == null) {
            contentEncoding = "UTF-8";
        }
        InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream(), contentEncoding);
        try {
            int contentLength = httpURLConnection.getContentLength();
            StringBuilder sb = contentLength != -1 ? new StringBuilder(contentLength) : new StringBuilder();
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    return sb.toString();
                }
                sb.append(cArr, 0, read);
            }
        } finally {
            inputStreamReader.close();
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public final void ApiKey(String str) {
        this.rJWxANjNJzenLOhFGOM8bv7U8Qw2bZkEFFRrWhEK33fiMPh8xnV3JndH9sVxgqEq = str;
    }

    @SimpleEvent(description = "Event triggered when the Yandex.Translate service returns the translated text. This event also provides a response code for error handling. If the responseCode is not 200, then something went wrong with the call, and the translation will not be available.")
    public final void GotTranslation(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "GotTranslation", str, str2);
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YandexTranslate yandexTranslate, String str, String str2) throws IOException, JSONException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(YANDEX_TRANSLATE_SERVICE_URL + yandexTranslate.rJWxANjNJzenLOhFGOM8bv7U8Qw2bZkEFFRrWhEK33fiMPh8xnV3JndH9sVxgqEq + "&lang=" + str + "&text=" + URLEncoder.encode(str2, "UTF-8")).openConnection();
        if (httpURLConnection != null) {
            try {
                JSONObject jSONObject = new JSONObject(getResponseContent(httpURLConnection));
                final String string = jSONObject.getString("code");
                final String str3 = (String) jSONObject.getJSONArray(PropertyTypeConstants.PROPERTY_TYPE_TEXT).get(0);
                yandexTranslate.activity.runOnUiThread(new Runnable() {
                    public final void run() {
                        YandexTranslate.this.GotTranslation(string, str3);
                    }
                });
            } finally {
                httpURLConnection.disconnect();
            }
        }
    }
}
