package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyZone;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.google.appinventor.components.runtime.util.KodularGDPRUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/adcolony-interstitial/", iconName = "images/adcolony.png", nonVisible = true, version = 1)
@UsesLibraries({"AdColony.jar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "keyboardHidden|orientation|screenSize", hardwareAccelerated = "true", name = "com.adcolony.sdk.AdColonyAdViewActivity")})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.VIBRATE", "com.google.android.gms.permission.AD_ID"})
public class KodularAdColonyInterstitialAd extends AndroidNonvisibleComponent implements OnDestroyListener, OnInitializeListener {
    private String ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = "";
    private String N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = "";
    private boolean TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = false;
    private String appId = "";
    private ComponentContainer container;
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private AdColonyAppOptions hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    public AdColonyInterstitial f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AdColonyInterstitialListener f165hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public KodularAdColonyInterstitialAd(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnDestroy(this);
        this.form.registerForOnInitialize(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AdColonyAppOptions();
        Log.d("AdColony Interstitial Ad", "Kodular AdColony Interstitial Ad created");
    }

    public void onInitialize() {
        if (AppID().isEmpty() || ZoneID().isEmpty()) {
            AdColony.configure(this.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, KodularAdsUtil.AD_COLONY_APP_ID, new String[]{KodularAdsUtil.AD_COLONY_ZONE_ID});
            this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = KodularAdsUtil.AD_COLONY_ZONE_ID;
        } else {
            AdColony.configure(this.container.$context(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, AppID(), new String[]{ZoneID()});
            this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = ZoneID();
        }
        this.f165hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AdColonyInterstitialListener() {
            public final void onRequestFilled(AdColonyInterstitial adColonyInterstitial) {
                KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_ADCOLONY, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, KodularAdColonyInterstitialAd.this.form);
                AdColonyInterstitial unused = KodularAdColonyInterstitialAd.this.f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adColonyInterstitial;
                KodularAdColonyInterstitialAd.this.AdLoaded();
                Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad loaded");
            }

            public final void onRequestNotFilled(AdColonyZone adColonyZone) {
                KodularAdColonyInterstitialAd.this.Error("Request not filled.");
                Log.e("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad failed to load: request not filled");
            }

            public final void onOpened(AdColonyInterstitial adColonyInterstitial) {
                KodularAdColonyInterstitialAd.this.AdOpened();
                Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad opened");
            }

            public final void onExpiring(AdColonyInterstitial adColonyInterstitial) {
                KodularAdColonyInterstitialAd.this.AdExpiring();
                Log.i("AdColony Interstitial Ad", "AdColony Interstitial: Interstitial ad is expiring");
            }
        };
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        AdColony.requestInterstitial(this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd, this.f165hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (AdColonyAdOptions) null);
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        AdColonyInterstitial adColonyInterstitial = this.f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adColonyInterstitial != null) {
            if (adColonyInterstitial.isExpired()) {
                AdExpiring();
            } else {
                this.f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String AppID() {
        return this.appId;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void AppID(String str) {
        this.appId = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String ZoneID() {
        return this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void ZoneID(String str) {
        this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = str;
    }

    @SimpleEvent(description = "Called when an ad request failed. The message will display the reason for why the ad failed.")
    public void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when the ad is expiring. You should load a new ad.")
    public void AdExpiring() {
        EventDispatcher.dispatchEvent(this, "AdExpiring", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @SimpleProperty(description = "If set to true the user allowed the ad network to show personalized ads. You only need to request the consent from european users.")
    public void UserConsent(boolean z) {
        this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPrivacyFrameworkRequired("GDPR", true);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPrivacyConsentString("GDPR", z ? "1" : "0");
        AdColony.setAppOptions(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean UserConsent() {
        return this.TQYS6YBjqkoWFaGULpL2H003Eu6rkiOxhECYAYl6g8NMleEVKHe9nH3AkWIoC5xt;
    }

    @SimpleFunction(description = "Returns true if the current app user is located in europe. If true you must ask the user as example in a dialog if he give his consent for personalized ads.")
    public boolean IsEuropeanUser() {
        return KodularGDPRUtil.isRequestLocationInEurope(this.container.$context());
    }

    public void onDestroy() {
        AdColonyInterstitial adColonyInterstitial = this.f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adColonyInterstitial != null) {
            adColonyInterstitial.destroy();
            this.f164hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }
}
