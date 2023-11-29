package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
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

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/facebook-interstitial/", iconName = "images/facebook.png", nonVisible = true, version = 3)
@UsesLibraries({"AudienceNetwork.jar", "AudienceNetwork.aar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class MakeroidFBInterstitialAd extends AndroidNonvisibleComponent implements InterstitialAdListener, Component, OnDestroyListener {
    private Activity activity;
    private ComponentContainer container;
    private Context context;
    private Form form;
    private String ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = "";
    private InterstitialAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
    }

    public void onLoggingImpression(Ad ad) {
    }

    public MakeroidFBInterstitialAd(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnDestroy(this);
        AudienceNetworkAds.initialize(this.context);
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        InterstitialAd interstitialAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (interstitialAd == null || !interstitialAd.isAdLoaded()) {
            Error("The ad is already expired or invalidated. Please load a new ad.");
            return;
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        Log.d("Makeroid FBInterstitial", "showAd() called");
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

    @SimpleEvent(description = "Called when an ad request failed. message will display the reason for why the ad failed")
    public void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when the user is about to return to the application after clicking on an ad")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad is received")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    public void onDestroy() {
        InterstitialAd interstitialAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (interstitialAd != null) {
            interstitialAd.destroy();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    public void onInterstitialDisplayed(Ad ad) {
        Log.d("Makeroid FBInterstitial", "Ad displayed");
    }

    public void onInterstitialDismissed(Ad ad) {
        Log.d("Makeroid FBInterstitial", "Ad dismissed");
        AdClosed();
    }

    public void onError(Ad ad, AdError adError) {
        Log.d("Makeroid FBInterstitial", "onError: Error code is: " + adError.getErrorCode() + " and Error Message is: " + adError.getErrorMessage());
        Error(adError.getErrorMessage());
    }

    public void onAdLoaded(Ad ad) {
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_FACEBOOK, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, this.form);
        Log.d("Makeroid FBInterstitial", "Ad loaded");
        AdLoaded();
    }

    public void onAdClicked(Ad ad) {
        Log.d("Makeroid FBInterstitial", "Ad clicked");
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this.context, this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = interstitialAd;
        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig().withAdListener(this).build());
        Log.d("Makeroid FBInterstitial", "loadAd() called");
    }
}
