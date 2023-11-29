package com.google.appinventor.components.runtime;

import android.view.View;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "AdMob Banner component", iconName = "images/admob.png", nonVisible = false, version = 3)
public final class AdmobBanner extends AndroidViewComponent {
    private ComponentContainer container;
    private TextView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "This property must be set to true to receive ads.")
    public final void AdEnabled(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean AdEnabled() {
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set Ad Unit ID")
    public final String AdUnitId() {
        return "";
    }

    @DesignerProperty(defaultValue = "ca-app-pub-3940256099942544/6300978111", editorType = "string")
    @SimpleProperty(userVisible = false)
    public final void AdUnitId(String str) {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled you will see the consent dialog no matter if you are located in Europe or not. Please use this option only in development.  If this setting is enabled ALL taken consents will not be saved.")
    public final void ConsentDevelopmentMode(boolean z) {
    }

    @SimpleProperty
    public final boolean ConsentDevelopmentMode() {
        return true;
    }

    @SimpleProperty
    public final String ConsentMessage() {
        return "";
    }

    @DesignerProperty(defaultValue = "Can we continue to use your data to tailor ads for you?", editorType = "string")
    @SimpleProperty(description = "The message for the consent dialog.")
    public final void ConsentMessage(String str) {
    }

    @SimpleProperty
    public final String ConsentTitle() {
        return "";
    }

    @DesignerProperty(defaultValue = "Data Protection", editorType = "string")
    @SimpleProperty(description = "The title for the consent dialog.")
    public final void ConsentTitle(String str) {
    }

    public final void HeightPercent(int i) {
    }

    @SimpleFunction(description = "Load a new AdMob Banner ad.")
    public final void LoadAd() {
    }

    @SimpleProperty(description = "Returns the current personalized consent. If true user has consent to personalized ads.")
    public final boolean PersonalizedResult() {
        return false;
    }

    @SimpleFunction(description = "Deletes the user's consent. Useful if you want to test the consent dialog in development.")
    public final void RevokeConsent() {
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If you want to test the component then that this property to true. Then you will receive test ads.", userVisible = false)
    public final void TestMode(boolean z) {
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean TestMode() {
        return true;
    }

    public final void WidthPercent(int i) {
    }

    public AdmobBanner(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        TextView textView = new TextView(componentContainer.$context());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = textView;
        textView.setText("Admob components aren't supported anymore... Please use AdManager");
        componentContainer.$add(this);
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleEvent(description = "Called when an ad request was loaded.")
    public final void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public final void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Called when an ad was opened.")
    public final void AdOpened() {
        EventDispatcher.dispatchEvent(this, "AdOpened", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad was closed.")
    public final void AdClosed() {
        EventDispatcher.dispatchEvent(this, "AdClosed", new Object[0]);
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

    @SimpleEvent(description = "Event triggered when the consent was changed.")
    public final void OnConsentChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnConsentChanged", Boolean.valueOf(z));
    }
}
