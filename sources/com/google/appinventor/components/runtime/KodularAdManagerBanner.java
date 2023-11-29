package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
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

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "A visible component that displays banner ads from multiple advertisers and mediation partners", iconName = "images/kodularBanner.png", nonVisible = false, version = 1)
@UsesLibraries({"browser.jar", "browser.aar", "play-services-ads.jar", "play-services-ads.aar", "play-services-ads-base.jar", "play-services-ads-base.aar", "play-services-ads-identifier.jar", "play-services-ads-identifier.aar", "play-services-ads-lite.jar", "play-services-ads-lite.aar", "play-services-appset.jar", "play-services-appset.aar", "play-services-basement.jar", "play-services-basement.aar", "play-services-measurement-sdk-api.jar", "play-services-measurement-sdk-api.aar", "play-services-measurement-base.jar", "play-services-measurement-base.aar", "play-services-tasks.jar", "play-services-tasks.aar", "ump.jar", "AudienceNetwork.jar", "AudienceNetwork.aar"})
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize", name = "com.google.android.gms.ads.AdActivity")})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
public final class KodularAdManagerBanner extends AndroidViewComponent implements OnDestroyListener, OnPauseListener, OnResumeListener, OnInitializeListener {
    private Activity activity;
    private boolean bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH;
    private ComponentContainer container;
    private Context context;
    private Form form;
    private AdManagerAdView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FloorAmount f166hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = FloorAmount.Optimized;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final KodularAdManager f167hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KodularContentProtection f168hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new KodularContentProtection(this.activity);
    private LinearLayout linearLayout;
    private boolean testMode;

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    public KodularAdManagerBanner(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        this.f167hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new KodularAdManager(this.context, $form);
        this.form.registerForOnInitialize(this);
        this.form.registerForOnDestroy(this);
        this.form.registerForOnPause(this);
        this.form.registerForOnResume(this);
        LinearLayout linearLayout2 = new LinearLayout(this.context);
        this.linearLayout = linearLayout2;
        linearLayout2.setOrientation(0);
        this.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        AdManagerAdView adManagerAdView = new AdManagerAdView(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adManagerAdView;
        adManagerAdView.setAdSizes(new AdSize[]{AdSize.SMART_BANNER});
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setLayoutParams(layoutParams);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdListener(new AdListener() {
            public final void onAdClicked() {
            }

            public final void onAdClosed() {
            }

            public final void onAdOpened() {
            }

            public final void onAdLoaded() {
                Log.i("KodularAdManagerBanner", "Loaded ad successfully");
                if (!KodularAdManagerBanner.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerBanner.this)) {
                    KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_ADMANAGER, KodularAnalyticsUtil.Ads.FORMAT_BANNER, KodularAdManagerBanner.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerBanner.this));
                }
            }

            public final void onAdFailedToLoad(LoadAdError loadAdError) {
                KodularAdManagerBanner.this.FailedToLoad(loadAdError.toString());
                Log.e("KodularAdManagerBanner", "Failed to load with message: " + loadAdError.toString());
            }
        });
        this.linearLayout.addView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        componentContainer.$add(this);
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
        this.f166hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = floorAmount;
    }

    public final FloorAmount EcpmFloor() {
        return this.f166hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleFunction(description = "Loads a banner ad and displays it")
    public final void Load() {
        final AdManagerAdRequest build = new AdManagerAdRequest.Builder().addCustomTargeting("k-ad-format", "banner").addCustomTargeting("k-ecpm-floor", this.f166hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.toUnderlyingValue()).addCustomTargeting("k-app-id", this.form.getAppId()).build();
        if (build.isTestDevice(this.context) || !this.testMode) {
            if (!this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH) {
                if (this.testMode || this.form.IsCompanion()) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdUnitId(KodularAdsUtil.AD_MANAGER_BANNER_TEST_ID);
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdUnitId(this.f167hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getAdUnit());
                }
                this.bM4TidozxzoY9OaFqMiIYPyvnCah6tSdq3o2XMkQik5CKYcFIbxeqf36lqbvGbmH = true;
            }
            this.f168hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnValidationResultListener(new KodularContentProtection.OnValidationResultListener() {
                public final void onResult(boolean z, boolean z2, String str) {
                    if (!z || !z2 || KodularAdManagerBanner.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerBanner.this)) {
                        KodularAdManagerBanner.this.FailedToLoad(str);
                    } else {
                        KodularAdManagerBanner.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(KodularAdManagerBanner.this).loadAd(build);
                    }
                }
            });
            this.f168hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startContentValidation(this.form.getAppId());
            return;
        }
        FailedToLoad("Could not load ad: Expected test device but got other");
    }

    @SimpleEvent(description = "Event raised when an ad could not be loaded")
    public final void FailedToLoad(String str) {
        EventDispatcher.dispatchEvent(this, "FailedToLoad", str);
    }

    public final View getView() {
        return this.linearLayout;
    }

    public final void onInitialize() {
        KodularAdManager.Consent.request(this.activity, this.context, this.testMode);
    }

    public final void onPause() {
        AdManagerAdView adManagerAdView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adManagerAdView != null) {
            adManagerAdView.pause();
        }
    }

    public final void onResume() {
        AdManagerAdView adManagerAdView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adManagerAdView != null) {
            adManagerAdView.resume();
        }
    }

    public final void onDestroy() {
        AdManagerAdView adManagerAdView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adManagerAdView != null) {
            adManagerAdView.destroy();
        }
        KodularAdManager.Consent.destroy();
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public final int Width() {
        return getView().getWidth();
    }

    public final void Width(int i) {
        this.container.setChildWidth(this, i);
    }
}
