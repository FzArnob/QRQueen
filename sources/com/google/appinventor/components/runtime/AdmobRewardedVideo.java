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
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "add description in OdeMessage ", iconName = "images/admob.png", nonVisible = true, version = 3)
public class AdmobRewardedVideo extends AndroidNonvisibleComponent implements Component {
    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This property must be set to true to receive ads.")
    public void AdEnabled(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean AdEnabled() {
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String AdUnitId() {
        return "";
    }

    @DesignerProperty(defaultValue = "ca-app-pub-3940256099942544/5224354917", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void AdUnitId(String str) {
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

    @SimpleFunction(description = "Load a new AdMob Rewarded Video ad.")
    public void LoadAd() {
    }

    @SimpleProperty(description = "Returns the current personalized consent. If true user has consent to personalized ads.")
    public boolean PersonalizedResult() {
        return false;
    }

    @SimpleFunction(description = "Deletes the user's consent. Useful if you want to test the consent dialog in development.")
    public void RevokeConsent() {
    }

    @SimpleFunction(description = "It will show the Video")
    public void ShowAd() {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component, that this property to true. After it you will receive test ads.", userVisible = false)
    public void TestMode(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean TestMode() {
        return true;
    }

    public AdmobRewardedVideo(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleEvent(description = "User watched video and should be rewarded.")
    public void Rewarded(String str, int i) {
        EventDispatcher.dispatchEvent(this, "Rewarded", str, Integer.valueOf(i));
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad started to show content.")
    public void AdVideoStarted() {
        EventDispatcher.dispatchEvent(this, "AdVideoStarted", new Object[0]);
    }

    @SimpleEvent(description = "Called when an video ad was completed.")
    public void AdVideoCompleted() {
        EventDispatcher.dispatchEvent(this, "AdVideoCompleted", new Object[0]);
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
