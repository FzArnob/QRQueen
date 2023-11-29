package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.DefaultAdListener;
import com.amazon.device.ads.InterstitialAd;
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
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.KodularAnalyticsUtil;

@DesignerComponent(category = ComponentCategory.ADVERTISING, description = "An interstitial ad is a full-page ad. AdAmazonInterstitial component allows you to monetize your app. You must have a valid Amazon Application Key. If your application key is invalid, the ad will not display on the emulator or the device. Warning: Make sure you're in test mode during development to avoid being disabled for clicking your own ads. ", helpUrl = "https://docs.kodular.io/components/monetization/amazon-interstitial/", iconName = "images/amazon.png", nonVisible = true, version = 2)
@UsesLibraries({"amazon-ads.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"})
public class AdAmazonInterstitial extends AndroidNonvisibleComponent implements Component {
    private String LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = "";
    private final Handler androidUIHandler = new Handler();
    private ComponentContainer container;
    private Form form;
    /* access modifiers changed from: private */
    public boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = false;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private InterstitialAd hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    /* renamed from: vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq  reason: collision with other field name */
    private boolean f42vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = true;
    private boolean vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    private boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = true;

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
    }

    public AdAmazonInterstitial(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        this.container = componentContainer;
        InterstitialAd interstitialAd = new InterstitialAd(componentContainer.$context());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = interstitialAd;
        interstitialAd.setListener(new a());
    }

    @SimpleEvent(description = "After a user clicks on the close ad button on an expanded ad, this callback is called immediately after collapsing the ad. This callback can be used to do things like resume your app or restart audio.")
    public void AdCollapsed() {
        EventDispatcher.dispatchEvent(this, "AdCollapsed", new Object[0]);
    }

    @SimpleEvent(description = "This callback is called each time an ad is successfully loaded. You can use this to log metrics on ad views and assist with initial integration. Detailed information about the ad that loaded can be obtained from the AdProperties object.")
    public void AdExpanded() {
        EventDispatcher.dispatchEvent(this, "AdExpanded", new Object[0]);
    }

    @SimpleEvent(description = "Whenever an ad fails to be retrieved, the event is called, returning the error message.")
    public void AdFailedToLoad(String str, String str2) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", str, str2);
    }

    @SimpleEvent(description = "Called when an an attempt was made to display the ad, but the ad was not ready to display")
    public void AdFailedToShow(String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToShow", str);
    }

    @SimpleEvent(description = "Triggered when the close button of the interstitial ad is clicked. It's important to remember only one interstitial ad can be shown at a time. The previous ad has to be dismissed before a new ad can be shown.")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Triggered each time an ad is successfully loaded. But you don't have to display the ad right after it's loaded. For example, set a flag to true and then at a transition point, if flag=true, then display the ad.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String ApplicationKey() {
        return this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    }

    @DesignerProperty(defaultValue = "ApplicationKey", editorType = "string")
    @SimpleProperty(description = "Enter Application Key. Go to Amazon Developer Portal and sign-in for your ApplicationKey", userVisible = false)
    public void ApplicationKey(String str) {
        this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = str;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(userVisible = true)
    public void EnableDebug(boolean z) {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableDebug() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "For debugging / development purposes flag all ad requests as tests, but set to false for production builds", userVisible = true)
    public void EnableTesting(boolean z) {
        this.f42vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableTesting() {
        return this.f42vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true, uses latitude and longitude coordinates as part of an ad request", userVisible = true)
    public void EnableGeoLocationTargeting(boolean z) {
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean EnableGeoLocationTargeting() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int TargetAge() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    }

    @DesignerProperty(defaultValue = "0", editorType = "integer")
    @SimpleProperty(description = "You can pass age information to the Amazon Mobile Ad Network to target specific age groups. If set as 0, Age Targetting will not be used")
    public void TargetAge(int i) {
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = i;
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        if (!this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
            AdRegistration.enableTesting(this.f42vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
            try {
                AdRegistration.setAppKey(this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn);
                if (!this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
                    if (this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq <= 0) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd();
                        return;
                    }
                }
                AdTargetingOptions adTargetingOptions = new AdTargetingOptions();
                boolean z = this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
                if (z) {
                    adTargetingOptions.enableGeoLocation(z);
                }
                int i = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
                if (i > 0) {
                    adTargetingOptions.setAge(i);
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.loadAd(adTargetingOptions);
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "LoadAd", e);
                AdFailedToLoad("PERMISSION_ERROR", "Permission \"android.permission.ACCESS_FINE_LOCATION\" was denied but needed.");
            } catch (Exception e2) {
                Log.e("AdAmazonInterstitial", "Exception thrown: " + e2.toString());
            }
        }
    }

    @SimpleFunction(description = "Loads a new ad.")
    public void LoadAd() {
        if (this.havePermission || !this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        } else {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    AdAmazonInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazonInterstitial.this).askPermission("android.permission.ACCESS_FINE_LOCATION", new PermissionResultHandler() {
                        public final void HandlePermissionResponse(String str, boolean z) {
                            if (z) {
                                boolean unused = AdAmazonInterstitial.this.havePermission = true;
                                AdAmazonInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazonInterstitial.this);
                                return;
                            }
                            boolean unused2 = AdAmazonInterstitial.this.havePermission = false;
                            AdAmazonInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazonInterstitial.this).dispatchPermissionDeniedEvent((Component) AdAmazonInterstitial.this, "LoadAd", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    });
                }
            });
        }
    }

    @SimpleFunction(description = "It will show the Interstitial Ad")
    public void ShowInterstitialAd() {
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
            this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = false;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showAd();
            return;
        }
        Log.d("AdAmazonInterstitial", "Interstitial ad was not ready to be shown. Make sure you have set ad AppKey and you invoke this after LoadAd");
        AdFailedToShow("Interstitial ad was not ready to be shown. Make sure you have set ad AppKey and you invoke this after LoadAd");
    }

    class a extends DefaultAdListener {
        a() {
        }

        public final void onAdCollapsed(Ad ad) {
            Log.i("AdAmazonInterstitial", "Ad collapsed.");
            AdAmazonInterstitial.this.AdCollapsed();
        }

        public final void onAdExpanded(Ad ad) {
            Log.i("AdAmazonInterstitial", "Ad expanded.");
            AdAmazonInterstitial.this.AdExpanded();
        }

        public final void onAdFailedToLoad(Ad ad, AdError adError) {
            Log.w("AdAmazonInterstitial", "Ad failed to load. Code: " + adError.getCode() + ", Message: " + adError.getMessage());
            boolean unused = AdAmazonInterstitial.this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = false;
            AdAmazonInterstitial.this.AdFailedToLoad(adError.getCode().toString(), adError.getMessage());
        }

        public final void onAdLoaded(Ad ad, AdProperties adProperties) {
            KodularAnalyticsUtil.adEvent(KodularAnalyticsUtil.Ads.NETWORK_AMAZON, KodularAnalyticsUtil.Ads.FORMAT_INTERSTITIAL, AdAmazonInterstitial.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(AdAmazonInterstitial.this));
            Log.i("AdAmazonInterstitial", adProperties.getAdType().toString() + " ad loaded successfully.");
            boolean unused = AdAmazonInterstitial.this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = true;
            AdAmazonInterstitial.this.AdLoaded();
        }

        public final void onAdDismissed(Ad ad) {
            Log.i("AdAmazonInterstitial", "Ad onAdDismissed finished");
            AdAmazonInterstitial.this.AdClosed();
        }
    }
}
