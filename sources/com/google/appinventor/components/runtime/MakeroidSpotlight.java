package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;
import com.wooplr.spotlight.utils.SpotlightListener;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Spotlight component", iconName = "images/spotlight.png", nonVisible = true, version = 2)
@UsesLibraries({"Spotlight.aar", "Spotlight.jar"})
public final class MakeroidSpotlight extends AndroidNonvisibleComponent implements OnOrientationChangeListener {
    private Typeface B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private AndroidViewComponent f213B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private boolean JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = true;
    private String K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0;
    private int LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = Component.COLOR_INDIGO;
    private int MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = 0;

    /* renamed from: MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw  reason: collision with other field name */
    private boolean f214MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = true;
    private int O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS = -1;
    private int OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC = Component.COLOR_INDIGO;
    private boolean RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = true;
    private Activity activity;
    private int cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = 16;
    private Context context;
    private Form form;
    private int gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = 20;

    /* renamed from: gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ  reason: collision with other field name */
    private boolean f215gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = true;
    private long hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 400;
    private String hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
    private Typeface hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SpotlightView f216hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private PreferencesManager f217hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public boolean pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = false;
    private int q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = 32;
    private long qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 400;
    private int seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = -1107296256;
    private long vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = 400;
    private int z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = 0;

    /* renamed from: z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj  reason: collision with other field name */
    private boolean f218z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = true;

    public MakeroidSpotlight(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
        this.f217hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new PreferencesManager(this.context);
        TextViewUtil.setContext(this.context);
        componentContainer.$form().registerForOnOrientationChangeListener(this);
    }

    public final void onOrientationChange() {
        SpotlightView spotlightView;
        if (this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 && (spotlightView = this.f216hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) != null) {
            spotlightView.removeSpotlightView(false);
            ShowSpotlight();
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "component")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The component to show in the spotlight.")
    public final void Component(AndroidViewComponent androidViewComponent) {
        this.f213B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = androidViewComponent;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the component.")
    public final AndroidViewComponent Component() {
        return this.f213B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @DesignerProperty(defaultValue = "&Hbe000000", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight mask color")
    public final void MaskColor(int i) {
        this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight mask color.")
    public final int MaskColor() {
        return this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading text.")
    public final void HeadingText(String str) {
        this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0 = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text.")
    public final String HeadingText() {
        return this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0;
    }

    @DesignerProperty(defaultValue = "32", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading size.")
    public final void HeadingTextSize(int i) {
        this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5 = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text size.")
    public final int HeadingTextSize() {
        return this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight heading text color.")
    public final void HeadingTextColor(int i) {
        this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight heading text color.")
    public final int HeadingTextColor() {
        return this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text.")
    public final void SubheadingText(String str) {
        this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text.")
    public final String SubheadingText() {
        return this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
    }

    @DesignerProperty(defaultValue = "16", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text size.")
    public final void SubheadingTextSize(int i) {
        this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text size.")
    public final int SubheadingTextSize() {
        return this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight subheading text color.")
    public final void SubheadingTextColor(int i) {
        this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight subheading text color.")
    public final int SubheadingTextColor() {
        return this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Spotlight line and arc color.")
    public final void LineAndArcColor(int i) {
        this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the Spotlight line and arc color.")
    public final int LineAndArcColor() {
        return this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss the spotlight on back pressed.")
    public final void DismissOnBackPress(boolean z) {
        this.f215gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not DismissOnBackPress is enabled.")
    public final boolean DismissOnBackPress() {
        return this.f215gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss the spotlight on touch")
    public final void DismissOnTouch(boolean z) {
        this.f214MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not DismissOnTouch is enabled.")
    public final boolean DismissOnTouch() {
        return this.f214MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Dismiss spotlight on touch after spotlight is completely visible.")
    public final void EnableDismissAfterShown(boolean z) {
        this.f218z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not EnableDismissAfterShown is enabled.")
    public final boolean EnableDismissAfterShown() {
        return this.f218z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Enable reveal animation (Only for Lollipop and above).")
    public final void EnableRevealAnimation(boolean z) {
        this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether or not EnableRevealAnimation is enabled.")
    public final boolean EnableRevealAnimation() {
        return this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Intro animation duration (For Reveal and Fadein).")
    public final void IntroAnimationDuration(long j) {
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = j;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the IntroAnimationDuration.")
    public final long IntroAnimationDuration() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Fade in animation duration for spotlight text (Heading and Sub-heading).")
    public final void FadeinTextDuration(long j) {
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = j;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the FadeinTextDuration.")
    public final long FadeinTextDuration() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "400", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Line animation duration")
    public final void LineAnimationDuration(long j) {
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = j;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the LineAnimationDuration.")
    public final long LineAnimationDuration() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    }

    @DesignerProperty(defaultValue = "20", editorType = "integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The padding for the circle spotlight. Default is '20'.")
    public final void CirclePadding(int i) {
        this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final int CirclePadding() {
        return this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontHeadingTypeface(int i) {
        this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = TextViewUtil.getTitleBarTypeFace(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontHeadingTypeface() {
        return this.MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontHeadingTypefaceImport(String str) {
        if (str != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = TextViewUtil.getTitleBarCustomTypeFace(this.form, str);
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontSubheadingTypeface(int i) {
        this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = i;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = TextViewUtil.getTitleBarTypeFace(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontSubheadingTypeface() {
        return this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontSubheadingTypefaceImport(String str) {
        if (str != null) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = TextViewUtil.getTitleBarCustomTypeFace(this.form, str);
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final void ShowTargetArc(boolean z) {
        this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set to true you will see a half round circle below the spotlight circle.")
    public final boolean ShowTargetArc() {
        return this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    }

    @SimpleFunction(description = "Use this block to show the spotlight.")
    public final void ShowSpotlight() {
        this.f217hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resetAll();
        this.f216hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new SpotlightView.Builder(this.activity).maskColor(this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz).headingTvText(this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0).headingTvColor(this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE).headingTvSize(this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5).subHeadingTvText(this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s).subHeadingTvColor(this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS).subHeadingTvSize(this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN).lineAndArcColor(this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC).dismissOnBackPress(this.f215gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ).dismissOnTouch(this.f214MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw).enableDismissAfterShown(this.f218z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj).enableRevealAnimation(this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T).introAnimationDuration(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO).fadeinTextDuration(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R).lineAnimDuration(this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE).target(this.f213B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getView()).targetPadding(this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ).extraPaddingForArc(20).setHeadingTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setSubHeadingTypeface(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).showTargetArc(this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX).setListener(new SpotlightListener() {
            public final void onUserClicked(SpotlightView spotlightView, String str) {
                MakeroidSpotlight.this.Clicked();
                boolean unused = MakeroidSpotlight.this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = false;
            }
        }).show();
        this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = true;
    }

    @SimpleFunction(description = "Use this block to show the spotlight on a floating action button.")
    public final void ShowSpotlightOnFAB(MakeroidFab makeroidFab) {
        this.f217hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resetAll();
        this.f216hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new SpotlightView.Builder(this.activity).maskColor(this.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz).headingTvText(this.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0).headingTvColor(this.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE).headingTvSize(this.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5).subHeadingTvText(this.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s).subHeadingTvColor(this.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS).subHeadingTvSize(this.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN).lineAndArcColor(this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC).dismissOnBackPress(this.f215gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ).dismissOnTouch(this.f214MYUGxENNZgpsWEBTVSKDauXfXur6zyPKrPdlATl7m89YCcguzmIKP8wXNDkxMYaw).enableDismissAfterShown(this.f218z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj).enableRevealAnimation(this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T).introAnimationDuration(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO).fadeinTextDuration(this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R).lineAnimDuration(this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE).target(makeroidFab.getView()).targetPadding(this.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ).extraPaddingForArc(20).setHeadingTypeface(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setSubHeadingTypeface(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).showTargetArc(this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX).setListener(new SpotlightListener() {
            public final void onUserClicked(SpotlightView spotlightView, String str) {
                MakeroidSpotlight.this.Clicked();
                boolean unused = MakeroidSpotlight.this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = false;
            }
        }).show();
        this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = true;
    }

    @SimpleEvent(description = "Event triggered when the spotlight is clicked.")
    public final void Clicked() {
        EventDispatcher.dispatchEvent(this, "Clicked", new Object[0]);
    }
}
