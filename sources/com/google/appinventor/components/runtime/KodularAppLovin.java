package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
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
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.google.appinventor.components.runtime.util.KodularGDPRUtil;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/app-lovin/", iconName = "images/applovin.png", nonVisible = true, version = 1)
@UsesLibraries({"app-lovin.aar", "app-lovin.jar", "browser.jar", "browser.aar", "play-services-ads-base.jar", "play-services-ads-base.aar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-ads-lite.jar", "play-services-ads-lite.aar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class KodularAppLovin extends AndroidNonvisibleComponent {
    private boolean TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = false;
    private ComponentContainer container;
    private Context context;
    private String f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = "";
    /* access modifiers changed from: private */
    public Form form;
    private AppLovinInterstitialAdDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public AppLovinAd f178hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppLovinSdk f179hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AppLovinSdkSettings f180hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static /* synthetic */ String vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(int i) {
        if (i == -103) {
            return "Indicates that the device had no network connectivity at the time of an ad request, either due to airplane mode or no service.";
        }
        if (i == -102) {
            return "Indicates that the network conditions prevented the SDK from receiving an ad.";
        }
        if (i == -8) {
            return "Indicates that the provided ad token is invalid; ad token must be returned from AppLovin S2S integration.";
        }
        if (i == -7) {
            return "Indicates that the zone provided is invalid; the zone needs to be added to your AppLovin account or may still be propagating to our servers.";
        }
        if (i == -6) {
            return "Indicates that there has been a failure to render an ad on screen.";
        }
        switch (i) {
            case -900:
                return "Indicates that a postback URL you attempted to dispatch was empty or nil.";
            case -800:
                return "Indicates that a AppLovin servers have returned an invalid response";
            case -702:
                return "Indicates that the impression has already been tracked.";
            case -700:
                return "Indicates that there was an error while attempting to render a native ad.";
            case -600:
                return "Indicates that the user exited out of the ad early. You may or may not wish to grant a reward depending on your preference. Note: This code is only possible when working with rewarded ads.";
            case -500:
                return "Indicates that a reward validation requested timed out (usually due to poor connectivity). Note: This code is only possible when working with rewarded videos.";
            case -400:
                return "Indicates that an unknown server-side error occurred. Note: This code is only possible when working with rewarded videos.";
            case -300:
                return "Indicates that the developer called for a rewarded video before one was available. Note: This code is only possible when working with rewarded videos.";
            case -22:
                return "Indicates that the SDK is currently disabled.";
            case -1:
                return "Indicates that the system is in unexpected state.";
            case 204:
                return "Indicates that no ads are currently eligible for your device.";
            default:
                switch (i) {
                    case -202:
                        return "Indicates that an attempt to cache a video resource to the filesystem failed; the device may be out of space.";
                    case -201:
                        return "Indicates that an attempt to cache an image resource to the filesystem failed; the device may be out of space.";
                    case -200:
                        return "Indicates that an attempt to cache a resource to the filesystem failed; the device may be out of space.";
                    default:
                        return "No error message available";
                }
        }
    }

    public KodularAppLovin(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.form = componentContainer.$form();
        this.context = componentContainer.$context();
        this.f180hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AppLovinSdkSettings(componentContainer.$context());
        Log.d(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "Kodular AppLovin created");
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        AppLovinSdk instance = AppLovinSdk.getInstance(SdkKey(), this.f180hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.context);
        this.f179hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = instance;
        instance.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener() {
            public final void adReceived(AppLovinAd appLovinAd) {
                KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, KodularAppLovin.this.form);
                AppLovinAd unused = KodularAppLovin.this.f178hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = appLovinAd;
                KodularAppLovin.this.AdLoaded();
                Log.i(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "AppLovin: ad loaded");
            }

            public final void failedToReceiveAd(int i) {
                String vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = KodularAppLovin.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(i);
                KodularAppLovin.this.Error(i, vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
                Log.e(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "AppLovin: ".concat(String.valueOf(vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq)));
            }
        });
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        AppLovinSdk appLovinSdk = this.f179hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (appLovinSdk != null) {
            AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(appLovinSdk, this.container.$context());
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = create;
            create.setAdClickListener(new AppLovinAdClickListener() {
                public final void adClicked(AppLovinAd appLovinAd) {
                    KodularAppLovin.this.AdOpened();
                    Log.i(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "AppLovin: ad opened");
                }
            });
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdDisplayListener(new AppLovinAdDisplayListener() {
                public final void adDisplayed(AppLovinAd appLovinAd) {
                    KodularAppLovin.this.AdStarted();
                    Log.i(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "Unity Ads: ad started");
                }

                public final void adHidden(AppLovinAd appLovinAd) {
                    KodularAppLovin.this.AdClosed();
                    Log.i(KodularAnalyticsUtil.Ads.NETWORK_APPLOVIN, "Unity Ads: ad closed");
                }
            });
            AppLovinAd appLovinAd = this.f178hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (appLovinAd != null) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAndRender(appLovinAd);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String SdkKey() {
        return this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void SdkKey(String str) {
        this.f9MsIiC6MxciaFVJmtpdDaPCPu1tdZDTYoHbgfSwKXBhLXF2LgZTKbRmVlZ1kb0Z = str;
    }

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(int i, String str) {
        EventDispatcher.dispatchEvent(this, "Error", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was started.")
    public void AdStarted() {
        EventDispatcher.dispatchEvent(this, "AdStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.")
    public void TestMode(boolean z) {
        AppLovinSdkSettings appLovinSdkSettings = this.f180hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (appLovinSdkSettings != null) {
            appLovinSdkSettings.setTestAdsEnabled(z);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        AppLovinSdkSettings appLovinSdkSettings = this.f180hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (appLovinSdkSettings != null) {
            return appLovinSdkSettings.isTestAdsEnabled();
        }
        return false;
    }

    @SimpleProperty(description = "If set to true the user allowed the ad network to show personalized ads. You only need to request the consent from european users.")
    public void UserConsent(boolean z) {
        this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = z;
        AppLovinPrivacySettings.setHasUserConsent(z, this.container.$context());
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UserConsent() {
        return this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt;
    }

    @SimpleFunction(description = "Returns true if the current app user is located in europe. If true you must ask the user as example in a dialog if he give his consent for personalized ads.")
    public boolean IsEuropeanUser() {
        return KodularGDPRUtil.isRequestLocationInEurope(this.container.$context());
    }
}
