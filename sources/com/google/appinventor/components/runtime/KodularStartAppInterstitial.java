package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.UsesBroadcastReceivers;
import com.google.appinventor.components.annotations.UsesContentProviders;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.UsesServices;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.annotations.androidmanifest.ProviderElement;
import com.google.appinventor.components.annotations.androidmanifest.ReceiverElement;
import com.google.appinventor.components.annotations.androidmanifest.ServiceElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

@UsesContentProviders(providers = {@ProviderElement(authorities = "%packageName%.startappinitprovider", exported = "false", name = "com.startapp.sdk.adsbase.StartAppInitProvider")})
@UsesServices(services = {@ServiceElement(name = "com.startapp.sdk.cachedservice.BackgroundService"), @ServiceElement(name = "com.startapp.sdk.jobs.SchedulerService", permission = "android.permission.BIND_JOB_SERVICE")})
@UsesActivities(activities = {@ActivityElement(configChanges = "orientation|screenSize|screenLayout|keyboardHidden", name = "com.startapp.sdk.adsbase.consent.ConsentActivity", theme = "@android:style/Theme.Translucent"), @ActivityElement(name = "com.startapp.sdk.ads.list3d.List3DActivity", theme = "@android:style/Theme"), @ActivityElement(configChanges = "orientation|screenSize|screenLayout|keyboardHidden", name = "com.startapp.sdk.ads.interstitials.OverlayActivity", theme = "@android:style/Theme.Translucent")})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE", "com.google.android.gms.permission.AD_ID"})
@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "The component for showing Interstial Ads from StartApp", iconName = "images/startAppInterstitial.png", nonVisible = true, version = 1)
@UsesLibraries({"startapp-ads.jar"})
@SimpleObject
@UsesBroadcastReceivers(receivers = {@ReceiverElement(exported = "true", intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.intent.action.BOOT_COMPLETED")})}, name = "com.startapp.sdk.adsbase.remoteconfig.BootCompleteListener")})
public final class KodularStartAppInterstitial extends AndroidNonvisibleComponent {
    private Activity activity;
    private String appId;
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = false;
    private StartAppAd startAppAd;

    public KodularStartAppInterstitial(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.activity = componentContainer.$context();
        this.startAppAd = new StartAppAd(this.activity);
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AppId(String str) {
        this.appId = str;
    }

    @SimpleEvent(description = "Called when an ad request failed to load.")
    public final void OnFailedToReceiveAd(String str) {
        EventDispatcher.dispatchEvent(this, "OnFailedToReceiveAd", str);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void OnReceiveAd() {
        EventDispatcher.dispatchEvent(this, "OnReceiveAd", new Object[0]);
    }

    @SimpleEvent(description = "Called when an an attempt was made to display the ad, but the ad was not ready to display.")
    public final void AdFailedToShow(String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToShow", str);
    }

    @SimpleEvent(description = "Indicates that the user has clicked on the interstial ad")
    public final void AdClicked() {
        EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that an Ad is shown to the user.")
    public final void AdDisplayed() {
        EventDispatcher.dispatchEvent(this, "AdDisplayed", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that an Ad which was shown to the user is now hidden.")
    public final void AdHidden() {
        EventDispatcher.dispatchEvent(this, "AdHidden", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @SimpleFunction(description = "It will show the Interstitial Ad")
    public final void ShowInterstitialAd() {
        if (!this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z) {
            AdFailedToShow("An Ad should be loaded initially before it can be displayed.");
            return;
        }
        this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = false;
        this.startAppAd.showAd(new AdDisplayListener() {
            public final void adHidden(Ad ad) {
                Log.i("StartApp Interstitial", "StartApp Ad hidden");
                KodularStartAppInterstitial.this.AdHidden();
            }

            public final void adDisplayed(Ad ad) {
                Log.i("StartApp Interstitial", "Ad displayed");
                KodularStartAppInterstitial.this.AdDisplayed();
            }

            public final void adClicked(Ad ad) {
                Log.i("StartApp Interstitial", "Ad clicked");
                KodularStartAppInterstitial.this.AdClicked();
            }

            public final void adNotDisplayed(Ad ad) {
                Log.i("StartApp Interstitial", "Ad not displayed");
                KodularStartAppInterstitial.this.AdFailedToShow(ad.getErrorMessage());
            }
        });
    }

    @SimpleFunction(description = "Load a new StartApp Interstitial ad.")
    public final void LoadAd() {
        StartAppSDK.init(this.activity, "".equals(this.appId) ? KodularAdsUtil.STARTAPP_APP_ID : this.appId, false);
        StartAppAd.disableSplash();
        StartAppSDK.enableReturnAds(false);
        StartAppSDK.setUserConsent(this.container.$context(), "pas", System.currentTimeMillis(), false);
        this.startAppAd.loadAd(new AdEventListener() {
            public final void onReceiveAd(Ad ad) {
                KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_STARTAPP, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, KodularStartAppInterstitial.this.form);
                boolean unused = KodularStartAppInterstitial.this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = true;
                KodularStartAppInterstitial.this.OnReceiveAd();
            }

            public final void onFailedToReceiveAd(Ad ad) {
                boolean unused = KodularStartAppInterstitial.this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = false;
                KodularStartAppInterstitial.this.OnFailedToReceiveAd(ad.getErrorMessage());
            }
        });
    }
}
