package com.google.appinventor.components.runtime;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularChromeCustomTabsHelper;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Chrome Custom Tabs component", iconName = "images/chrome.png", nonVisible = true, version = 3)
@UsesLibraries(libraries = "browser.aar, browser.jar")
public final class MakeroidChromeCustomTabs extends AndroidNonvisibleComponent {
    private boolean HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = true;
    private Context context;
    private boolean dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = false;
    private String g16lHC6pRQoq0FWou0AzFVTKqyDojHRb8fcaYD4yg7tKEFm8ChlIf2uMkTa8F6nE;
    private CustomTabsIntent.Builder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = Component.COLOR_INDIGO;
    private boolean showTitle = true;
    private boolean tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = true;
    private boolean x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = true;

    public MakeroidChromeCustomTabs(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new CustomTabsIntent.Builder();
        Log.d("Chrome Custom Tabs", "Custom Tabs Created");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL to load in the custom tab. The URL must start with 'http://' or 'https://'")
    public final void Url(String str) {
        this.g16lHC6pRQoq0FWou0AzFVTKqyDojHRb8fcaYD4yg7tKEFm8ChlIf2uMkTa8F6nE = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the URL.")
    public final String Url() {
        return this.g16lHC6pRQoq0FWou0AzFVTKqyDojHRb8fcaYD4yg7tKEFm8ChlIf2uMkTa8F6nE;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to show the title.", userVisible = true)
    public final void ShowTitle(boolean z) {
        this.showTitle = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the title is shown or not.")
    public final boolean ShowTitle() {
        return this.showTitle;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to hide the url bar on scrolling.", userVisible = false)
    public final void UrlBarHidingOnScroll(boolean z) {
        this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the url bar is hide on scrolling or not.")
    public final boolean UrlBarHidingOnScroll() {
        return this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to add the default share menu item into the menu.", userVisible = false)
    public final void DefaultShareMenuItem(boolean z) {
        this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the default share menu item is added or not.")
    public final boolean DefaultShareMenuItem() {
        return this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to enable instant apps.", userVisible = true)
    public final void InstantAppsEnabled(boolean z) {
        this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return whether the instant apps support is enabled or not.")
    public final boolean InstantAppsEnabled() {
        return this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Specifies the color of the toolbar.")
    public final void ToolbarColor(int i) {
        this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = i;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Return the toolbar color")
    public final int ToolbarColor() {
        return this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Should open the native app handling the URL instead.", userVisible = true)
    public final void PreferNative(boolean z) {
        this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc = z;
    }

    @SimpleFunction(description = "Use this block to add a menu item with the given title and page link. The page can be as example 'https://www.instagram.com/kodular/'. If the user have Instagram installed,the page will then be opened in the official Instagram app. Else in the default browser.")
    public final void AddMenuItemOpenPage(String str, String str2) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMenuItem(str, PendingIntent.getActivity(this.context, 0, new Intent("android.intent.action.VIEW", Uri.parse(str2)), 0));
    }

    @SimpleFunction(description = "Use this block to add a menu item with the given title to open any app you want with the package name. The package name can be as example 'com.instagram.android' to open Instagram on the menu item click. If the app is not installed the menu item will not be added.")
    public final void AddMenuItemOpenApp(String str, String str2) {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(str2);
        if (launchIntentForPackage != null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addMenuItem(str, PendingIntent.getActivity(this.context, 0, launchIntentForPackage, 0));
        }
    }

    @SimpleFunction(description = "Use this block to open the custom tab.")
    public final void OpenCustomTab() {
        String packageNameToUse;
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStartAnimations(this.context, 17432578, 17432579);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setShowTitle(this.showTitle);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setToolbarColor(this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw);
            if (this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enableUrlBarHiding();
            }
            if (this.tOvM9eIUWszKzvISo4zKh7g8MyRaLHuNLQ5NdigAhriBDKCBmghUBEUlM2ZIvV1J) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addDefaultShareMenuItem();
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setInstantAppsEnabled(this.HSgmARz5w7nma58XTyePpkN53AMPZCg0W3RVNCiWbSBR7PJtyMriHlkRP1TBmIZy);
            CustomTabsIntent build = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.build();
            if (this.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc && (packageNameToUse = KodularChromeCustomTabsHelper.getPackageNameToUse(this.context)) != null) {
                build.intent.setPackage(packageNameToUse);
            }
            build.launchUrl(this.context, Uri.parse(this.g16lHC6pRQoq0FWou0AzFVTKqyDojHRb8fcaYD4yg7tKEFm8ChlIf2uMkTa8F6nE));
        } catch (Exception e) {
            Log.e("Chrome Custom Tabs", String.valueOf(e));
        }
    }
}
