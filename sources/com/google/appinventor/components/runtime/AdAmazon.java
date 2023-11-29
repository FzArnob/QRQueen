package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "AdAmazon component allows you to monetize your app. You must have a valid publisher id that can be obtained from https://developer.amazon.com. If your publisher id is invalid, the AdAmazon banner will not display on the emulator or the device.", helpUrl = "https://docs.kodular.io/components/monetization/amazon-banner/", iconName = "images/amazon.png", version = 1)
@UsesLibraries({"amazon-ads.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_WIFI_STATE"})
public class AdAmazon extends AndroidViewComponent implements AdListener, OnDestroyListener {
    private boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = true;
    private String F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = "AmazonPublisherId";
    private String TAG = "AdAmazon";
    private final Handler androidUIHandler = new Handler();
    private Form form;
    private boolean havePermission = false;
    private AdLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private boolean f41hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = true;

    public void HeightPercent(int i) {
    }

    public void WidthPercent(int i) {
    }

    public AdAmazon(ComponentContainer componentContainer) {
        super(componentContainer);
        this.form = componentContainer.$form();
        AdLayout adLayout = new AdLayout(componentContainer.$context(), AdSize.SIZE_320x50);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = adLayout;
        adLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        componentContainer.$add(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setListener(this);
        TestMode(true);
        RefreshAd(true);
    }

    @DesignerProperty(defaultValue = "AmazonPublisherId", editorType = "string")
    @SimpleProperty(description = "Sets the Amazon Ad Publisher Id and refreshes the ad.")
    public void PublisherId(String str) {
        String trim = str.trim();
        this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = trim;
        AdRegistration.setAppKey(trim);
        RefreshAd(true);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Refreshes the ad.")
    public void RefreshAd(boolean z) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = z;
        if (!this.havePermission) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this).askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this, true);
                                AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this, AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this));
                                return;
                            }
                            AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this, false);
                            AdAmazon.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazon.this).dispatchPermissionDeniedEvent((Component) AdAmazon.this, "RefreshAd", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        } else {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(z);
        }
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(boolean z) {
        if (z) {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(new AdTargetingOptions());
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "RefreshAd", e);
                AdFailedToLoad("PERMISSION_ERROR", "Permission \"android.permission.ACCESS_FINE_LOCATION\" was denied but needed.");
            } catch (Exception e2) {
                Log.e("AdAmazon", e2.getMessage());
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String PublisherId() {
        return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void TestMode(boolean z) {
        this.f41hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = z;
        AdRegistration.enableLogging(z);
        AdRegistration.enableTesting(z);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return this.f41hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onDestroy() {
        AdLayout adLayout = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (adLayout != null) {
            adLayout.destroy();
        }
    }

    public void onAdCollapsed(Ad ad) {
        Log.i(this.TAG, "Ad collapsed.");
        AdDismissed();
    }

    public void onAdDismissed(Ad ad) {
        Log.i(this.TAG, "Ad Dismissed.");
        AdDismissed();
    }

    public void onAdExpanded(Ad ad) {
        Log.i(this.TAG, "Ad expanded.");
        AdExpanded();
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        Log.w("AmazonInterstitialAds", "Ad failed to load. Code: " + adError.getCode() + ", Message: " + adError.getMessage());
        AdFailedToLoad(adError.getCode().toString(), adError.getMessage());
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_AMAZON, KodularAnalyticsUtil.Ads.FORMAT_BANNER, this.form);
        String str = this.TAG;
        Log.i(str, adProperties.getAdType().toString() + " ad loaded successfully.");
        AdLoaded();
    }

    @SimpleEvent(description = "Event to detect that a ad was dismissed.")
    public void AdDismissed() {
        EventDispatcher.dispatchEvent(this, "AdDismissed", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a ad was expanded.")
    public void AdExpanded() {
        EventDispatcher.dispatchEvent(this, "AdExpanded", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that the try to load a ad was not successful.")
    public void AdFailedToLoad(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", str, str2);
    }

    @SimpleEvent(description = "Event to detect that a ad was loaded.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    public int Height() {
        return getView().getHeight();
    }

    public void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    public int Width() {
        return getView().getWidth();
    }

    public void Width(int i) {
        this.container.setChildWidth(this, i);
    }
}
