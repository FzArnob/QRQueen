package com.google.appinventor.components.common;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ComponentCategory {
    USERINTERFACE("User Interface", "devices_other"),
    LAYOUT("Layout", Boolean.TRUE, "table_chart"),
    LAYOUT_GENERAL("General", Boolean.FALSE, r10, Boolean.FALSE, "dashboard"),
    LISTVIEWS("Listviews", Boolean.FALSE, r10, Boolean.FALSE, "dns"),
    NAVIGATION("Navigation", Boolean.FALSE, r10, Boolean.FALSE, "control_camera"),
    VIEWS("Views", Boolean.FALSE, r10, Boolean.FALSE, "view_array"),
    MEDIA("Media", "camera"),
    ANIMATION("Drawing and Animation", "color_lens"),
    MAPS("Maps", "map"),
    SENSORS("Sensors", "explore"),
    SOCIAL("Social", "supervised_user_circle"),
    STORAGE("Storage", "file_copy"),
    UTILITIES("Utilities", "work_outline"),
    DYNAMIC("Dynamic", "code"),
    CONNECTIVITY("Connectivity", "rss_feed"),
    GOOGLE("Google", "mdi-google"),
    MONETIZATION("Monetization", Boolean.TRUE, "monetization_on"),
    MONETIZATION_GENERAL("General", Boolean.FALSE, r35, Boolean.FALSE, "shopping_cart"),
    ADVERTISING("Advertising", Boolean.FALSE, r35, Boolean.FALSE, "branding_watermark"),
    LEGOMINDSTORMS("LEGO® MINDSTORMS®", Boolean.TRUE, "widgets"),
    EV3("LEGO® EV3", Boolean.FALSE, r44, Boolean.FALSE, "gamepad"),
    NXT("LEGO® NXT", Boolean.FALSE, r44, Boolean.FALSE, "gamepad"),
    EXPERIMENTAL("Experimental", "new_releases"),
    DEPRECATED("Deprecated", "format_line_spacing"),
    EXTENSION("Extension", "extension"),
    INTERNAL("For internal use only"),
    UNINITIALIZED("Uninitialized");
    
    private static final Map<String, String> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Boolean f3B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ComponentCategory f4hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Boolean f5hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private String f6hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    static {
        HashMap hashMap = new HashMap();
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = hashMap;
        hashMap.put("User Interface", "user-interface");
        hashMap.put("Layout", "layout");
        hashMap.put("LayoutGeneral", "layout/general");
        hashMap.put("Listviews", "layout/listviews");
        hashMap.put("Views", "layout/views");
        hashMap.put("Navigation", "layout/navigation");
        hashMap.put("Media", "media");
        hashMap.put("Drawing and Animation", "drawing-and-animation");
        hashMap.put("Maps", "maps");
        hashMap.put("Sensors", "sensors");
        hashMap.put("Social", NotificationCompat.CATEGORY_SOCIAL);
        hashMap.put("Storage", "storage");
        hashMap.put("Device", "device");
        hashMap.put("Dynamic", "dynamic");
        hashMap.put("Connectivity", "connectivity");
        hashMap.put("Google", "google");
        hashMap.put("LEGO® MINDSTORMS®", "lego-mindstorms");
        hashMap.put("LEGO® EV3", "lego-mindstorms/ev3");
        hashMap.put("LEGO® NXT", "lego-mindstorms/nxt");
        hashMap.put("Monetization", "monetization");
        hashMap.put("MonetizationGeneral", "monetization/general");
        hashMap.put("Advertising", "monetization/advertising");
        hashMap.put("Utilities", "utilities");
        hashMap.put("Experimental", PropertyPriorityConstants.PROPERTY_TYPE_EXPERIMENTAL);
        hashMap.put("Deprecated", "deprecated");
        hashMap.put("Extension", "extension");
    }

    private ComponentCategory(String str, Boolean bool, ComponentCategory componentCategory, Boolean bool2, String str2) {
        this.f6hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = str;
        this.f5hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bool;
        this.f4hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = componentCategory;
        this.f3B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = bool2;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = str2;
    }

    private ComponentCategory(String str, String str2) {
        this(r9, r10, str, Boolean.TRUE, (ComponentCategory) null, Boolean.FALSE, str2);
    }

    private ComponentCategory(String str) {
        this(r9, r10, str, Boolean.TRUE, (ComponentCategory) null, Boolean.FALSE, "offline_bolt");
    }

    private ComponentCategory(String str, Boolean bool, String str2) {
        this(r9, r10, str, Boolean.TRUE, (ComponentCategory) null, bool, str2);
    }

    public final String getName() {
        return this.f6hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Boolean isParentCategory() {
        return this.f5hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Boolean hasChildCategories() {
        return this.f3B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public final ComponentCategory getParentCategory() {
        return this.f4hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final String getIcon() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    public final List<ComponentCategory> getChildren() {
        ArrayList arrayList = new ArrayList();
        if (!hasChildCategories().booleanValue()) {
            return arrayList;
        }
        for (ComponentCategory componentCategory : values()) {
            if (componentCategory.getParentCategory() == this) {
                arrayList.add(componentCategory);
            }
        }
        return arrayList;
    }

    public final String getDocName() {
        if ("LAYOUT_GENERAL".equals(name())) {
            return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get("LayoutGeneral");
        }
        if ("MONETIZATION_GENERAL".equals(name())) {
            return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get("MonetizationGeneral");
        }
        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(this.f6hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }
}
