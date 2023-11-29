package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "A component that create a Bottom Navigation Menu in the app", iconName = "images/bottomNavigation.png", nonVisible = false, version = 1)
public final class KodularBottomNavigation extends AndroidViewComponent {
    private int UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = PaintUtil.hexStringToInt("&HFF6E6E6E");
    private int ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    private int backgroundColor;
    private Context context;
    private Form form;
    private BottomNavigationView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private int[][] f181hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int[] wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public final void HeightPercent(int i) {
    }

    public final void WidthPercent(int i) {
    }

    public KodularBottomNavigation(ComponentContainer componentContainer) {
        super(componentContainer);
        int hexStringToInt = PaintUtil.hexStringToInt("&HFF3F51B5");
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = hexStringToInt;
        this.f181hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new int[][]{new int[]{16842912}, new int[]{16842910}};
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new int[]{hexStringToInt, this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO};
        this.form = componentContainer.$form();
        this.context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new BottomNavigationView(this.context);
        componentContainer.$add(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                KodularBottomNavigation.this.ItemSelected(menuItem.getItemId(), menuItem.getTitle().toString());
                return true;
            }
        });
        Width(-2);
        Height(-1);
        BackgroundColor(0);
        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH();
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the Bottom Navigation Menu")
    public final void BackgroundColor(int i) {
        this.backgroundColor = i;
        BottomNavigationView bottomNavigationView = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (i == 0) {
            i = -1;
        }
        bottomNavigationView.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the Bottom Navigation Menu.")
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Set the color of the selected item of the Bottom Navigation Menu")
    public final void SelectedColor(int i) {
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = i;
        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the color of the selected item of the Bottom Navigation Menu")
    public final int SelectedColor() {
        return this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    }

    @DesignerProperty(defaultValue = "&HFF6E6E6E", editorType = "color")
    @SimpleProperty(description = "Set the color of the unselected items of the Bottom Navigation Menu")
    public final void UnselectedColor(int i) {
        this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = i;
        KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the color of the unselected items of the Bottom Navigation Menu")
    public final int UnselectedColor() {
        return this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO;
    }

    @SimpleFunction(description = "Add an item to the bottom menu")
    public final void AddItem(int i, String str, String str2) {
        try {
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().size() < 5) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().add(0, i, 0, str).setIcon(MediaUtil.getBitmapDrawable(this.form, str2));
            }
        } catch (Exception unused) {
        }
    }

    @SimpleFunction(description = "Update an item of the bottom menu")
    public final void UpdateItem(int i, String str, String str2) {
        try {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().findItem(i).setTitle(str).setIcon(MediaUtil.getBitmapDrawable(this.form, str2));
        } catch (Exception unused) {
        }
    }

    @SimpleFunction(description = "Remove an item from the bottom menu")
    public final void RemoveItem(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().removeItem(i);
    }

    @SimpleFunction(description = "Remove all items from the bottom menu")
    public final void RemoveAllItems() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().clear();
    }

    @SimpleFunction(description = "Select an item from the bottom menu")
    public final void SelectItem(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu().findItem(i).setChecked(true);
    }

    @SimpleEvent(description = "Event triggers when an item was selected.")
    public final void ItemSelected(int i, String str) {
        EventDispatcher.dispatchEvent(this, "ItemSelected", Integer.valueOf(i), str);
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    private void KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH() {
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new int[]{this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T, this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO};
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(new ColorStateList(this.f181hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(new ColorStateList(this.f181hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
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
}
