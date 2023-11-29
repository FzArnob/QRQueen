package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
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
import com.google.appinventor.components.runtime.util.OnInitializeListener;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/unity-ads_ads/", iconName = "images/unityads.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class KodularUnityAds extends AndroidNonvisibleComponent implements OnInitializeListener {
    private Activity activity;
    private ComponentContainer container;
    private Context context;
    private Form form;
    private String ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = "";
    private String iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = "";
    private String[] qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = {"at", "be", "bg", "cy", "cz", "de", "dk", "ee", "es", "fi", "fr", "gb", "gr", "hr", "hu", "ie", "it", "lt", "lu", "lv", "mt", "nl", "pl", "pt", "ro", "se", "si", "sk"};
    private boolean testMode = false;

    @SimpleFunction(description = "Returns true if the ad is finished loading and can now be shown.")
    public boolean IsReady() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KodularUnityAds(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnInitialize(this);
        Log.d("Unity Ads", "Kodular Unity Ads created");
    }

    public void onInitialize() {
        if (GameID() == null || GameID().isEmpty()) {
            Error("The game id can not be empty.");
        }
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        Error("The ad was not ready to be shown.");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String GameID() {
        return this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void GameID(String str) {
        this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String PlacementID() {
        return this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void PlacementID(String str) {
        this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = str;
    }

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened(String str) {
        EventDispatcher.dispatchEvent(this, "AdOpened", str);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed(String str, boolean z, boolean z2) {
        EventDispatcher.dispatchEvent(this, "AdClosed", str, Boolean.valueOf(z), Boolean.valueOf(z2));
    }

    @SimpleEvent(description = "Called when an ad was started.")
    public void AdStarted(String str) {
        EventDispatcher.dispatchEvent(this, "AdStarted", str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.", userVisible = false)
    public void TestMode(boolean z) {
        this.testMode = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.testMode;
    }
}
