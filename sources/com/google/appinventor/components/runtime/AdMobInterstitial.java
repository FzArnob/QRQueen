package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "An interstitial ad is a full-page ad. AdMobInterstitial component allows you to monetize your app. You must have a valid AdMob account and AdUnitId that can be obtained from http://www.google.com/AdMob . If your id is invalid, the AdMobInterstitial will not display on the emulator or the device. Warning: Make sure you're in test mode during development to avoid being disabled for clicking your own ads. ", helpUrl = "https://docs.kodular.io/components/monetization/admob-interstitial/", iconName = "images/admob.png", nonVisible = true, version = 4)
public class AdMobInterstitial extends AndroidNonvisibleComponent implements Component {
    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This property must be set to true to receive ads.")
    public void AdEnabled(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean AdEnabled() {
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String AdUnitID() {
        return "";
    }

    @DesignerProperty(defaultValue = "ca-app-pub-3940256099942544/1033173712", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void AdUnitID(String str) {
    }

    @DesignerProperty(defaultValue = "unity", editorType = "commission_interstitial_ads_network")
    @Deprecated
    @SimpleProperty(description = "Sets the ad network used to take the commission.")
    public void CommissionInterstitialAdsNetwork(String str) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see the consent dialog no matter if you are located in Europe or not. Please use this option only in development.  If this setting is enabled ALL taken consents will not be saved.")
    public void ConsentDevelopmentMode(boolean z) {
    }

    @SimpleProperty
    public boolean ConsentDevelopmentMode() {
        return true;
    }

    @SimpleProperty
    public String ConsentMessage() {
        return "";
    }

    @DesignerProperty(defaultValue = "Can we continue to use your data to tailor ads for you?", editorType = "string")
    @SimpleProperty(description = "The message for the consent dialog.")
    public void ConsentMessage(String str) {
    }

    @SimpleProperty
    public String ConsentTitle() {
        return "";
    }

    @DesignerProperty(defaultValue = "Data Protection", editorType = "string")
    @SimpleProperty(description = "The title for the consent dialog.")
    public void ConsentTitle(String str) {
    }

    @SimpleFunction(description = "Load a new AdMob Interstitial ad.")
    public void LoadAd() {
    }

    @SimpleProperty(description = "Returns the current personalized consent. If true user has consent to personalized ads.")
    public boolean PersonalizedResult() {
        return false;
    }

    @SimpleFunction(description = "Deletes the user's consent. Useful if you want to test the consent dialog in development.")
    public void RevokeConsent() {
    }

    @SimpleFunction(description = "It will show the Interstitial Ad")
    public void ShowInterstitialAd() {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int TargetAge() {
        return 0;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty(description = "Leave 0 for targeting ALL ages")
    public void TargetAge(int i) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Indicate whether you want Google to treat your content as child-directed when you make an ad request. Info here: https://developers.google.com/mobile-ads-sdk/docs/admob/android/targeting#child-directed_setting")
    public void TargetForChildren(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TargetForChildren() {
        return false;
    }

    @DesignerProperty(defaultValue = "ALL", editorType = "gender_options")
    @SimpleProperty
    public void TargetGender(String str) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.", userVisible = false)
    public void TestMode(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return true;
    }

    public AdMobInterstitial(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Called when an an attempt was made to display the ad, but the ad was not ready to display.")
    public void AdFailedToShow(String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToShow", str);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad leaves the application (e.g., to go to the browser).")
    public void AdLeftApplication() {
        EventDispatcher.dispatchEvent(this, "AdLeftApplication", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request was loaded.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Event triggered when the consent was changed.")
    public void OnConsentChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnConsentChanged", Boolean.valueOf(z));
    }
}
