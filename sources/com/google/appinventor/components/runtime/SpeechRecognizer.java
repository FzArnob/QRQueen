package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.text.TextUtils;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "Component for using Voice Recognition to convert from speech to text", iconName = "images/speechRecognizer.png", nonVisible = true, version = 3)
@UsesPermissions({"android.permission.RECORD_AUDIO", "android.permission.INTERNET"})
public class SpeechRecognizer extends AndroidNonvisibleComponent implements Component, OnClearListener, SpeechListener {
    private Intent B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    private final ComponentContainer container;
    private boolean gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = true;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private SpeechRecognizerController hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = "";

    public SpeechRecognizer(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        componentContainer.$form().registerForOnClear(this);
        this.container = componentContainer;
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = intent;
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        UseLegacy(this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Language() {
        return this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ;
    }

    @SimpleProperty
    public void Language(String str) {
        this.vQeIU3Nt6lISgDcgPcgMyfTsPOzVWtilFVqv3Ws2SOY6jXuMnX7gL1LFHvLOLuoZ = str;
        if (TextUtils.isEmpty(str)) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.removeExtra("android.speech.extra.LANGUAGE");
        } else {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.putExtra("android.speech.extra.LANGUAGE", str);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Result() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @SimpleFunction
    public void GetText() {
        if (!this.havePermission) {
            this.form.runOnUiThread(new Runnable() {
                public final void run() {
                    SpeechRecognizer.this.form.askPermission("android.permission.RECORD_AUDIO", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = this.havePermission = true;
                                this.GetText();
                                return;
                            }
                            SpeechRecognizer.this.form.dispatchPermissionDeniedEvent((Component) this, "GetText", "android.permission.RECORD_AUDIO");
                        }
                    });
                }
            });
            return;
        }
        BeforeGettingText();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.start();
    }

    @SimpleFunction
    public void Stop() {
        SpeechRecognizerController speechRecognizerController = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (speechRecognizerController != null) {
            speechRecognizerController.stop();
        }
    }

    @SimpleEvent
    public void BeforeGettingText() {
        EventDispatcher.dispatchEvent(this, "BeforeGettingText", new Object[0]);
    }

    @SimpleEvent
    public void AfterGettingText(String str, boolean z) {
        EventDispatcher.dispatchEvent(this, "AfterGettingText", str, Boolean.valueOf(z));
    }

    public void onPartialResult(String str) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = str;
        AfterGettingText(str, true);
    }

    public void onResult(String str) {
        this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = str;
        AfterGettingText(str, false);
    }

    public void onError(int i) {
        this.form.dispatchErrorOccurredEvent(this, "GetText", i, new Object[0]);
    }

    public void onClear() {
        Stop();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, an app can retain their older behaviour.")
    public boolean UseLegacy() {
        return this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If true, a separate dialog is used to recognize speech. If false, speech is recognized without changing the user interface and partial results are also provided.")
    public void UseLegacy(boolean z) {
        this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = z;
        Stop();
        if (z) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new IntentBasedSpeechRecognizer(this.container, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ServiceBasedSpeechRecognizer(this.container, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        }
    }
}
