package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
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

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/facebook-rewarded/", iconName = "images/facebook.png", nonVisible = true, version = 2)
@UsesLibraries({"AudienceNetwork.jar", "AudienceNetwork.aar", "exoplayer.jar", "play-services-basement.jar", "play-services-basement.aar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class KodularFacebookRewardedVideoAd extends AndroidNonvisibleComponent implements RewardedVideoAdListener, OnDestroyListener {
    private ComponentContainer container;
    private Context context;
    private Form form;
    private String ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = "";
    private RewardedVideoAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    @DesignerProperty(defaultValue = "unity", editorType = "commission_rewarded_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionRewardedAdsNetwork(String str) {
    }

    public KodularFacebookRewardedVideoAd(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Form $form = componentContainer.$form();
        this.form = $form;
        $form.registerForOnDestroy(this);
        AudienceNetworkAds.initialize(this.context);
        Log.d("Facebook Rewarded Video Ad", "Kodular Facebook Rewarded Video Ad created");
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(this.context, PlacementID());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = rewardedVideoAd;
        rewardedVideoAd.loadAd(rewardedVideoAd.buildLoadAdConfig().withAdListener(this).build());
    }

    @SimpleFunction(description = "Shows an ad to the user.")
    public void ShowAd() {
        RewardedVideoAd rewardedVideoAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (rewardedVideoAd != null && rewardedVideoAd.isAdLoaded()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
        }
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

    @SimpleEvent(description = "Called when an ad request failed. message will display the reason for why the ad failed.")
    public void Error(String str) {
        EventDispatcher.dispatchEvent(this, "Error", str);
    }

    @SimpleEvent(description = "Called when the user is about to return to the application after clicking on an ad.")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad is received.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad was completed.")
    public void AdVideoCompleted() {
        EventDispatcher.dispatchEvent(this, "AdVideoCompleted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad started to show content.")
    public void AdVideoStarted() {
        EventDispatcher.dispatchEvent(this, "AdVideoStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    public void onDestroy() {
        RewardedVideoAd rewardedVideoAd = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (rewardedVideoAd != null) {
            rewardedVideoAd.destroy();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    public void onError(Ad ad, AdError adError) {
        Log.e("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video ad failed to load: " + adError.getErrorMessage());
        Error(adError.getErrorMessage());
    }

    public void onAdLoaded(Ad ad) {
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_FACEBOOK, KodularAnalyticsUtil.Ads.FORMAT_VIDEO, this.form);
        AdLoaded();
        Log.i("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video ad loaded");
    }

    public void onAdClicked(Ad ad) {
        AdOpened();
        Log.i("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video ad opened");
    }

    public void onLoggingImpression(Ad ad) {
        AdVideoStarted();
        Log.i("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video started");
    }

    public void onRewardedVideoCompleted() {
        AdVideoCompleted();
        Log.i("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video completed");
    }

    public void onRewardedVideoClosed() {
        AdClosed();
        Log.i("Facebook Rewarded Video Ad", "Facebook Rewarded Video: Video ad closed");
    }
}
