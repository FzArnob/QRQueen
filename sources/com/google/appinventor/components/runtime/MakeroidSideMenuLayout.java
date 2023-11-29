package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import de.hdodenhof.circleimageview.CircleImageView;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "Display a real Navigation Menu in the Side Menu", iconName = "images/makeroidSideMenuLayout.png", nonVisible = true, version = 2)
@UsesLibraries({"circleimageview.aar", "circleimageview.jar", "glide.jar"})
public class MakeroidSideMenuLayout extends AndroidNonvisibleComponent {
    private AppCompatImageView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private TextView Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    private boolean OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC = false;
    private String RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT;
    private int YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = Component.COLOR_GRAY;
    private String ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI;
    private int ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = Component.COLOR_PINK;
    private int backgroundColor = -1;
    private ComponentContainer container;
    private Context context;
    private String gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7;
    private String hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    private Menu hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private NavigationView f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private CircleImageView f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private int[][] f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = {new int[]{16842912}, new int[]{16842910}, new int[]{-16842910}};
    private int iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = -16777216;
    private boolean isCompanion = false;
    private TextView sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
    private LinearLayout vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private int[] wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = {-1565085, -16777216, -6381922};

    public MakeroidSideMenuLayout(final ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.container = componentContainer;
        if (componentContainer.$form() instanceof ReplForm) {
            this.isCompanion = true;
        }
        NavigationView navigationView = new NavigationView(this.context);
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = navigationView;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                componentContainer.$form().SideMenuClose();
                MakeroidSideMenuLayout.this.OnMenuItemClick(menuItem.getTitle().toString());
                return true;
            }
        });
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemBackground(KodularResourcesUtil.getDrawable(this.context, "navigation_item"));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMenu();
        Context context2 = this.context;
        LinearLayout linearLayout = new LinearLayout(context2, (AttributeSet) null, KodularResourcesUtil.getStyle(context2, "ThemeOverlay.AppCompat.Dark"));
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, KodularUnitUtil.DpToPixels(this.context, 176)));
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setGravity(80);
        int DpToPixels = KodularUnitUtil.DpToPixels(this.context, 16);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setPadding(DpToPixels, DpToPixels, DpToPixels, DpToPixels);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setOrientation(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(KodularUnitUtil.DpToPixels(this.context, 72), KodularUnitUtil.DpToPixels(this.context, 72));
        AppCompatImageView appCompatImageView = new AppCompatImageView(this.context);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = appCompatImageView;
        appCompatImageView.setLayoutParams(layoutParams);
        CircleImageView circleImageView = new CircleImageView(this.context);
        this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = circleImageView;
        circleImageView.setLayoutParams(layoutParams);
        TextView textView = new TextView(this.context);
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = textView;
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.setPadding(0, KodularUnitUtil.DpToPixels(this.context, 8), 0, 0);
        TextViewUtil.setFontTypeface(this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB, 5, true, false);
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.setTextColor(-1);
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.setTextSize(14.0f);
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.setMaxLines(1);
        TextView textView2 = new TextView(this.context);
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = textView2;
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        TextViewUtil.setFontTypeface(this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt, 5, false, false);
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.setTextColor(-1);
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.setTextSize(14.0f);
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt.setMaxLines(1);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setWeightSum(0.0f);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.addView(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.addView(this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.addView(this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.addView(this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.invalidate();
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addHeaderView(this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFitsSystemWindows(true);
        componentContainer.$form().SideMenuLayout(this);
        BackgroundColor(this.backgroundColor);
        CircleHeaderPicture(this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC);
        DisabledColor(this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k);
        EnabledColor(this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm);
        HeaderBackgroundImage(this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT);
        HeaderPicture(this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN);
        HeaderSubtitle(this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI);
        HeaderTitle(this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7);
        SelectedColor(this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T);
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Sets the Header Picture of the Side Menu")
    public void HeaderPicture(String str) {
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = str;
        if (this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC) {
            Context context2 = this.context;
            if (str == null) {
                str = "";
            }
            KodularUtil.LoadPicture(context2, str, this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.isCompanion);
            return;
        }
        Context context3 = this.context;
        if (str == null) {
            str = "";
        }
        KodularUtil.LoadPicture(context3, str, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.isCompanion);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the Header Picture of the Side Menu")
    public String HeaderPicture() {
        return this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Sets (or not) the header picture to use a circle shape")
    public void CircleHeaderPicture(boolean z) {
        this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC = z;
        String str = "";
        if (z) {
            this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVisibility(8);
            Context context2 = this.context;
            String str2 = this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
            if (str2 != null) {
                str = str2;
            }
            KodularUtil.LoadPicture(context2, str, this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.isCompanion);
            return;
        }
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setVisibility(0);
        this.f210hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        Context context3 = this.context;
        String str3 = this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
        if (str3 != null) {
            str = str3;
        }
        KodularUtil.LoadPicture(context3, str, this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, this.isCompanion);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns if the Header Picture is using a circular shape")
    public boolean CircleHeaderPicture() {
        return this.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Sets the Header Background Picture")
    public void HeaderBackgroundImage(String str) {
        this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = str;
        try {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setBackground(MediaUtil.getBitmapDrawable(this.container.$form(), str));
        } catch (Exception e) {
            Log.e("MakeroidSideMenuLayout", String.valueOf(e));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Background Picture")
    public String HeaderBackgroundImage() {
        return this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT;
    }

    @DesignerProperty(defaultValue = "", editorType = "text")
    @SimpleProperty(description = "Sets the Header Title")
    public void HeaderTitle(String str) {
        this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = str;
        TextView textView = this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
        if (str == null) {
            str = "";
        }
        TextViewUtil.setText(textView, str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Title")
    public String HeaderTitle() {
        return this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7;
    }

    @DesignerProperty(defaultValue = "", editorType = "text")
    @SimpleProperty(description = "Sets the Header Subtitle")
    public void HeaderSubtitle(String str) {
        this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = str;
        TextView textView = this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
        if (str == null) {
            str = "";
        }
        TextViewUtil.setText(textView, str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Gets the Header Subtitle")
    public String HeaderSubtitle() {
        return this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Sets the color of the enabled elements of the SideMenu")
    public void EnabledColor(int i) {
        this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm = i;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new int[]{this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T, i, this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k};
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the enabled elements of the SideMenu")
    public int EnabledColor() {
        return this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm;
    }

    @DesignerProperty(defaultValue = "&HFF9E9E9E", editorType = "color")
    @SimpleProperty(description = "Sets the color of the disabled elements of the SideMenu")
    public void DisabledColor(int i) {
        this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k = i;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new int[]{this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T, this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm, i};
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the disabled elements of the SideMenu")
    public int DisabledColor() {
        return this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k;
    }

    @DesignerProperty(defaultValue = "&HFFE81E63", editorType = "color")
    @SimpleProperty(description = "Sets the color of the selected element of the SideMenu")
    public void SelectedColor(int i) {
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = i;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new int[]{i, this.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm, this.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k};
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemTextColor(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setItemIconTintList(new ColorStateList(this.f211hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the color of the selected element of the SideMenu")
    public int SelectedColor() {
        return this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    }

    @SimpleFunction(description = "Add an item to the Navigation Menu")
    public void AddItem(String str, String str2, boolean z, boolean z2, int i) {
        MenuItem add = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.add(i, 0, 0, str);
        if (!str2.equals("")) {
            try {
                BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str2);
                if (bitmapDrawable != null) {
                    add.setIcon(bitmapDrawable);
                }
            } catch (Exception e) {
                Log.e("MakeroidSideMenuLayout", "Could not load Image - " + e.getMessage());
            }
        }
        add.setEnabled(z);
        add.setCheckable(true);
        add.setChecked(z2);
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Update an item of the Navigation Menu")
    public void UpdateItem(String str, String str2, String str3, boolean z, boolean z2) {
        for (int i = 0; i < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.size(); i++) {
            MenuItem item = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getItem(i);
            if (item.getTitle() == str) {
                item.setTitle(str2);
                if (str3.equals("")) {
                    item.setIcon((Drawable) null);
                } else {
                    try {
                        BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str3);
                        if (bitmapDrawable != null) {
                            item.setIcon(bitmapDrawable);
                        }
                    } catch (Exception e) {
                        Log.e("MakeroidSideMenuLayout", "Could not load Image for update - " + e.getMessage());
                    }
                }
                item.setEnabled(z);
                item.setChecked(z2);
                this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            }
        }
    }

    @SimpleFunction(description = "Remove an item from the Navigation Menu")
    public void RemoveItem(String str) {
        for (int i = 0; i < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.size(); i++) {
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getItem(i).getTitle() == str) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeItem(i);
                this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            }
        }
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the Sidemenu Layout")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Get the background color of the Sidemenu Layout")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleEvent(description = "Triggers when the user clicks on an item of the Navigation Menu")
    public void OnMenuItemClick(String str) {
        EventDispatcher.dispatchEvent(this, "OnMenuItemClick", str);
    }

    public View getView() {
        return this.f209hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }
}
