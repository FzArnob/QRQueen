package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

@UsesContentProviders(providers = {@ProviderElement(authorities = "%packageName%.startappinitprovider", exported = "false", name = "com.startapp.sdk.adsbase.StartAppInitProvider")})
@UsesServices(services = {@ServiceElement(name = "com.startapp.sdk.cachedservice.BackgroundService"), @ServiceElement(name = "com.startapp.sdk.jobs.SchedulerService", permission = "android.permission.BIND_JOB_SERVICE")})
@UsesActivities(activities = {@ActivityElement(configChanges = "orientation|screenSize|screenLayout|keyboardHidden", name = "com.startapp.sdk.adsbase.consent.ConsentActivity", theme = "@android:style/Theme.Translucent"), @ActivityElement(name = "com.startapp.sdk.ads.list3d.List3DActivity", theme = "@android:style/Theme"), @ActivityElement(configChanges = "orientation|screenSize|screenLayout|keyboardHidden", name = "com.startapp.sdk.ads.interstitials.OverlayActivity", theme = "@android:style/Theme.Translucent")})
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE", "com.google.android.gms.permission.AD_ID"})
@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "Component for displaying banner ads from StartApp", iconName = "images/kodularStartAppBanner.png", nonVisible = false, version = 1)
@UsesLibraries({"startapp-ads.jar"})
@SimpleObject
@UsesBroadcastReceivers(receivers = {@ReceiverElement(exported = "true", intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.intent.action.BOOT_COMPLETED")})}, name = "com.startapp.sdk.adsbase.remoteconfig.BootCompleteListener")})
public final class KodularStartAppBanner extends AndroidViewComponent implements BannerListener {
    private static final String LOG_TAG = "StartApp Banner";
    private Activity activity;
    private String appId;
    private Banner bannerView;
    private ComponentContainer container;
    private Form form;
    private boolean isAddedToContainer = false;
    private boolean isInitialized = false;
    LinearLayout mainLayout;
    private StartAppAd startAppAd;

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    public final void onImpression(View view) {
    }

    public KodularStartAppBanner(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.form = componentContainer.$form();
        this.activity = componentContainer.$context();
        LinearLayout linearLayout = new LinearLayout(componentContainer.$context());
        this.mainLayout = linearLayout;
        linearLayout.setGravity(1);
        componentContainer.$add(this);
    }

    private void littleBannerHelper(boolean z) {
        if (z) {
            this.mainLayout.removeView(this.bannerView);
        }
        Banner banner = new Banner(this.container.$context(), this);
        this.bannerView = banner;
        this.mainLayout.addView(banner, new LinearLayout.LayoutParams(-1, -2));
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public final void Visible(boolean z) {
        Banner banner = this.bannerView;
        if (banner != null) {
            if (z) {
                banner.showBanner();
            } else {
                banner.hideBanner();
            }
            super.Visible(z);
        }
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AppId(String str) {
        this.appId = str;
    }

    @SimpleFunction(description = "Load a new StartApp Banner ad.")
    public final void LoadAd() {
        if (!this.isAddedToContainer) {
            littleBannerHelper(false);
            this.isAddedToContainer = true;
        } else {
            littleBannerHelper(true);
        }
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_STARTAPP, KodularAnalyticsUtil.Ads.FORMAT_BANNER, this.form);
        initStartAppSdk();
        this.bannerView.loadAd(ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, 50);
        Log.i(LOG_TAG, "Loading banner ad");
    }

    @SimpleEvent(description = "Called when an ad request failed to load.")
    public final void OnFailedToReceiveAd(String str) {
        EventDispatcher.dispatchEvent(this, "OnFailedToReceiveAd", str);
    }

    @SimpleEvent(description = "Called when an ad request succeeds.")
    public final void OnReceiveAd() {
        EventDispatcher.dispatchEvent(this, "OnReceiveAd", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the user has clicked on the banner ad.")
    public final void AdClicked() {
        EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    private void initStartAppSdk() {
        StartAppSDK.init(this.activity, "".equals(this.appId) ? KodularAdsUtil.STARTAPP_APP_ID : this.appId, false);
        Log.i(LOG_TAG, "Initializing Startapp SDK wit APP ID" + this.appId);
        StartAppAd.disableSplash();
        StartAppSDK.enableReturnAds(false);
        StartAppSDK.setUserConsent(this.container.$context(), "pas", System.currentTimeMillis(), false);
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

    public final View getView() {
        return this.mainLayout;
    }

    public final void onReceiveAd(View view) {
        OnReceiveAd();
    }

    public final void onFailedToReceiveAd(View view) {
        OnFailedToReceiveAd("Cannot load banner ad");
    }

    public final void onClick(View view) {
        AdClicked();
    }
}
