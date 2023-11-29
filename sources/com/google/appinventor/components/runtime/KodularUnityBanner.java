package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "...in ode messages file", helpUrl = "https://docs.kodular.io/components/monetization/unity-banner/", iconName = "images/unityads.png", nonVisible = false, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE")
public class KodularUnityBanner extends AndroidViewComponent {
    private ComponentContainer container;
    private Context context;
    private Form form;
    private String ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl;
    private String iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    private LinearLayout linearLayout;

    public void HeightPercent(int i) {
    }

    @SimpleFunction(description = "Load a new Unity ad in the banner")
    public void LoadAd() {
    }

    public void WidthPercent(int i) {
    }

    public KodularUnityBanner(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        LinearLayout linearLayout2 = new LinearLayout(this.context);
        this.linearLayout = linearLayout2;
        linearLayout2.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        this.linearLayout.setLayoutParams(layoutParams);
        this.container.$add(this);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String GameID() {
        return this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void GameID(String str) {
        this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = str;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, userVisible = false)
    public String PlacementID() {
        return this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl;
    }

    @DesignerProperty
    @SimpleProperty(userVisible = false)
    public void PlacementID(String str) {
        this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = str;
        this.linearLayout.removeAllViews();
    }

    @SimpleEvent(description = "Called when an ad clicked.")
    public void AdClicked() {
        EventDispatcher.dispatchEvent(this, "AdClicked", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request was loaded.")
    public void AdLoaded() {
        EventDispatcher.dispatchEvent(this, "AdLoaded", new Object[0]);
    }

    @SimpleEvent(description = "Called when an ad request failed to load. The message will display the error code and error message.")
    public void AdFailedToLoad(int i, String str) {
        EventDispatcher.dispatchEvent(this, "AdFailedToLoad", Integer.valueOf(i), str);
    }

    public View getView() {
        return this.linearLayout;
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
