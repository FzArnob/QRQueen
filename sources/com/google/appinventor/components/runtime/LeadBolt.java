package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.apptracker.android.listener.AppModuleListener;
import com.apptracker.android.track.AppTracker;
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

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "LeadBolt is a non-visible component allowing you to show Network and Rewarded ads.", helpUrl = "https://docs.kodular.io/components/monetization/leadbolt/", iconName = "images/leadbolt.png", nonVisible = true, version = 2)
@UsesLibraries({"AppTracker.jar", "AdMobAppTracker.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class LeadBolt extends AndroidNonvisibleComponent implements Component {
    private ComponentContainer container;
    private Context context;
    private AppModuleListener hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new AppModuleListener() {
        public final void onModuleLoaded(String str) {
            KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_LEADBOLT, LeadBolt.this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx ? KodularAnalyticsUtil.Ads.FORMAT_VIDEO : KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, LeadBolt.this.form);
            LeadBolt.this.LBLoaded(str);
        }

        public final void onModuleFailed(String str, String str2, boolean z) {
            LeadBolt.this.LBFailed(str, str2, z);
        }

        public final void onModuleClosed(String str, boolean z) {
            LeadBolt.this.LBClosed(str, z);
        }

        public final void onModuleClicked(String str) {
            LeadBolt.this.LBClicked(str);
        }

        public final void onModuleCached(String str) {
            LeadBolt.this.LBCached(str);
        }
    };
    private boolean nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = false;
    /* access modifiers changed from: private */
    public boolean op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = false;
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = "";

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_rewarded_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionRewardedAdsNetwork(String str) {
    }

    public LeadBolt(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str) {
        if (!this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG) {
            AppTracker.setModuleListener(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            AppTracker.startSession(this.context, this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp, true);
            this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = true;
            if (str.contains("NetworkAd")) {
                LoadNetworkAd();
            } else if (str.contains("RewardedAd")) {
                LoadRewardedAd();
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public void APIKey(String str) {
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str;
    }

    @SimpleFunction(description = "Use this block to load a Network ad.")
    public void LoadNetworkAd() {
        this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = false;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou("NetworkAd");
        if (AppTracker.isAdReady("inapp")) {
            AppTracker.loadModuleToCache(this.context, "inapp");
            return;
        }
        this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = false;
        Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block after LoadNetworkAd to show the loaded ad.")
    public void ShowNetworkAd() {
        if (AppTracker.isAdReady("inapp")) {
            AppTracker.loadModule(this.context, "inapp");
            return;
        }
        this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = false;
        Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block to load a Rewarded Video ad.")
    public void LoadRewardedAd() {
        this.op7m9tjL9FfXtQ4MVagpcEhDmFIa6xyIVDUYWgTs5INAROZGTkftwWcRnCCOYoFx = true;
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou("RewardedAd");
        if (AppTracker.isAdReady("reward")) {
            AppTracker.loadModuleToCache(this.context, "reward");
            return;
        }
        this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = false;
        Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block after LoadRewardedAd to show the loaded ad.")
    public void ShowRewardedAd() {
        if (AppTracker.isAdReady("reward")) {
            AppTracker.loadModule(this.context, "reward");
            return;
        }
        this.nn72cmMvpJDARAoxBpvS24CN9Of9fpinGcvMsNPLOdwkLzCSrjpb4bynF9b6riOG = false;
        Log.i("LeadBolt", "No ads ready");
    }

    @SimpleFunction(description = "Use this block after loading an ad to check whether it's ready to show.")
    public boolean IsNetworkAdReady() {
        return AppTracker.isAdReady("inapp");
    }

    @SimpleFunction(description = "Use this block after loading an ad to check whether it's ready to show.")
    public boolean IsRewardedAdReady() {
        return AppTracker.isAdReady("reward");
    }

    @SimpleEvent(description = "Event triggered when ads are shown")
    public void LBLoaded(String str) {
        EventDispatcher.dispatchEvent(this, "LBLoaded", str);
    }

    @SimpleEvent(description = "Event triggered when ads are failed to load")
    public void LBFailed(String str, String str2, boolean z) {
        EventDispatcher.dispatchEvent(this, "LBFailed", str, str2, Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Event triggered when ads are closed")
    public void LBClosed(String str, boolean z) {
        EventDispatcher.dispatchEvent(this, "LBClosed", str, Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Event triggered when ads are clicked")
    public void LBClicked(String str) {
        EventDispatcher.dispatchEvent(this, "LBClicked", str);
    }

    @SimpleEvent(description = "Event triggered when ads are loaded")
    public void LBCached(String str) {
        EventDispatcher.dispatchEvent(this, "LBCached", str);
    }
}
