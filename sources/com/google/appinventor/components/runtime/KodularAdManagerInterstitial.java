package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.FloorAmount;
import com.google.appinventor.components.runtime.util.KodularAdManager;
import com.google.appinventor.components.runtime.util.KodularAdsUtil;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;
import com.google.appinventor.components.runtime.util.KodularContentProtection;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import java.util.Collections;
import java.util.List;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "A visible component that displays banner ads from multiple advertisers and mediation partners", iconName = "images/kodularInterstitial.png", nonVisible = true, version = 1)
@UsesLibraries({"browser.jar", "browser.aar", "play-services-ads.jar", "play-services-ads.aar", "play-services-ads-base.jar", "play-services-ads-base.aar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-ads-lite.jar", "play-services-ads-lite.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-measurement-sdk-api.jar", "play-services-measurement-sdk-api.aar", "play-services-measurement-sdk.jar", "play-services-measurement-sdk.aar", "play-services-measurement-impl.jar", "play-services-measurement-impl.aar", "play-services-measurement-base.jar", "play-services-measurement-base.aar", "play-services-measurement-api.jar", "play-services-measurement-api.aar", "play-services-measurement.jar", "play-services-measurement.aar", "ump.jar", "AudienceNetwork.jar", "AudienceNetwork.aar"})
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize", name = "com.google.android.gms.ads.AdActivity")})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
public final class KodularAdManagerInterstitial extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnInitializeListener {
    private Activity activity;
    private ComponentContainer container;
    private Context context;
    private Form form;
    /* access modifiers changed from: private */
    public AdManagerInterstitialAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FloorAmount f170hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = FloorAmount.Optimized;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final KodularAdManager f171hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularContentProtection f172hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new KodularContentProtection(this.activity);
    private boolean testMode;

    public KodularAdManagerInterstitial(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        this.f171hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new KodularAdManager(this.context, $form);
        this.form.registerForOnInitialize(this);
        this.form.registerForOnDestroy(this);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void TestMode(boolean z) {
        List list;
        this.testMode = z;
        if (z || this.form.IsCompanion()) {
            list = Collections.singletonList(KodularAdsUtil.guessSelfDeviceId(this.context));
        } else {
            list = Collections.emptyList();
        }
        RequestConfiguration.Builder builder = MobileAds.getRequestConfiguration().toBuilder();
        builder.setTestDeviceIds(list);
        MobileAds.setRequestConfiguration(builder.build());
        KodularAdManager.Consent.request(this.activity, this.context, z);
    }

    @DesignerProperty(defaultValue = "optimized", editorType = "gam_ecpm_floor")
    @SimpleProperty(description = "Set a minimum eCPM floor below which advertisers cannot bid for inventory. Use Optimized to let Google decide an eCPM floor.")
    public final void EcpmFloor(FloorAmount floorAmount) {
        this.f170hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = floorAmount;
    }

    public final FloorAmount EcpmFloor() {
        return this.f170hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Event raised when an ad could not be loaded.")
    public final void FailedToLoad(String str) {
        EventDispatcher.dispatchEvent(this, "FailedToLoad", str);
    }

    @SimpleEvent(description = "Event raised when an ad could not be displayd to the user.")
    public final void FailedToShow(String str) {
        EventDispatcher.dispatchEvent(this, "FailedToShow", str);
    }

    @SimpleEvent(description = "Event raised when an ad was loaded successfully.")
    public final void Loaded() {
        EventDispatcher.dispatchEvent(this, "Loaded", new Object[0]);
    }

    @SimpleFunction(description = "Loads an interstitial ad and prepares it for showing to the user.")
    public final void Load() {
        final AdManagerAdRequest build = new AdManagerAdRequest.Builder().addCustomTargeting("k-ad-format", "interstitial").addCustomTargeting("k-ecpm-floor", this.f170hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toUnderlyingValue()).addCustomTargeting("k-app-id", this.form.getAppId()).build();
        if (build.isTestDevice(this.context) || !this.testMode) {
            final AnonymousClass1 r1 = new AdManagerInterstitialAdLoadCallback() {
                public final /* synthetic */ void onAdLoaded(Object obj) {
                    AdManagerInterstitialAd adManagerInterstitialAd = (AdManagerInterstitialAd) obj;
                    adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        public final void onAdShowedFullScreenContent() {
                        }

                        public final void onAdFailedToShowFullScreenContent(AdError adError) {
                            KodularAdManagerInterstitial.this.FailedToShow(adError.getMessage());
                            AdManagerInterstitialAd unused = KodularAdManagerInterstitial.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                        }

                        public final void onAdDismissedFullScreenContent() {
                            AdManagerInterstitialAd unused = KodularAdManagerInterstitial.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                        }
                    });
                    AdManagerInterstitialAd unused = KodularAdManagerInterstitial.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adManagerInterstitialAd;
                    KodularAdManagerInterstitial.this.Loaded();
                    if (!KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this)) {
                        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_ADMANAGER, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this));
                    }
                }

                public final void onAdFailedToLoad(LoadAdError loadAdError) {
                    Log.e("KodularAdManagerInterstitial", "Failed to load with message: " + loadAdError.getMessage());
                    KodularAdManagerInterstitial.this.FailedToLoad(loadAdError.toString());
                    AdManagerInterstitialAd unused = KodularAdManagerInterstitial.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
                }
            };
            this.f172hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnValidationResultListener(new KodularContentProtection.OnValidationResultListener() {
                public final void onResult(boolean z, boolean z2, String str) {
                    if (!z || !z2 || KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this)) {
                        KodularAdManagerInterstitial.this.FailedToLoad(str);
                    } else {
                        AdManagerInterstitialAd.load(KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this), (KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this) || KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this).IsCompanion()) ? KodularAdsUtil.AD_MANAGER_INTERSTITIAL_TEST_ID : KodularAdManagerInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerInterstitial.this).getAdUnit(), build, r1);
                    }
                }
            });
            this.f172hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startContentValidation(this.form.getAppId());
            return;
        }
        FailedToLoad("Could not load ad: Expected test device but got other");
    }

    @SimpleFunction(description = "Displays a previously loaded interstitial ad.")
    public final void Show() {
        AdManagerInterstitialAd adManagerInterstitialAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adManagerInterstitialAd != null) {
            adManagerInterstitialAd.show(this.activity);
        } else {
            FailedToShow("Could not show ad: No ad loaded!");
        }
    }

    public final void onInitialize() {
        KodularAdManager.Consent.request(this.activity, this.context, this.testMode);
    }

    public final void onDestroy() {
        KodularAdManager.Consent.destroy();
    }
}
