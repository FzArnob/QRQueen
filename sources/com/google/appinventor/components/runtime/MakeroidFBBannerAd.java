package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/facebook-banner/", iconName = "images/facebook.png", nonVisible = false, version = 1)
@UsesLibraries({"AudienceNetwork.jar", "AudienceNetwork.aar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public final class MakeroidFBBannerAd extends AndroidViewComponent implements AdListener, OnDestroyListener {
    private Activity activity;
    private ComponentContainer container;
    private Context context;
    private Form form;
    private String ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = "";
    private AdView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private LinearLayout wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    public final void onLoggingImpression(Ad ad) {
    }

    public MakeroidFBBannerAd(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnDestroy(this);
        LinearLayout linearLayout = new LinearLayout(this.context);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setOrientation(1);
        this.container.$add(this);
        Log.d("Makeroid FBBannerAd", "adView created");
        AudienceNetworkAds.initialize(this.context);
    }

    public final View getView() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty
    @SimpleProperty
    public final void PlacementID(String str) {
        this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = str;
    }

    @SimpleProperty(description = "Set Placement ID")
    public final String PlacementID() {
        return this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl;
    }

    @SimpleEvent(description = "Event triggered when ads are loaded")
    public final void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Event triggered when ads failed to load")
    public final void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Event triggered when ads are clicked")
    public final void AdClicked() {
        EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Event triggered when ads are closed")
    public final void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    public final void onDestroy() {
        AdView adView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adView != null) {
            adView.destroy();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
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

    public final void onError(Ad ad, AdError adError) {
        Error(adError.getErrorMessage());
    }

    public final void onAdLoaded(Ad ad) {
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_FACEBOOK, KodularAnalyticsUtil.Ads.FORMAT_BANNER, this.form);
        AdLoaded();
    }

    public final void onAdClicked(Ad ad) {
        AdClicked();
    }

    @SimpleFunction(description = "Load Ad")
    public final void LoadAd() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AdView(this.context, this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl, AdSize.BANNER_HEIGHT_50);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.removeAllViews();
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.addView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        AdView adView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(this).build());
    }
}
