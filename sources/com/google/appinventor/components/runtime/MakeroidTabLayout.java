package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "", iconName = "images/tabLayout.png", version = 1)
public class MakeroidTabLayout extends AndroidViewComponent implements OnOrientationChangeListener {
    private static final int DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_ACCENT_COLOR);
    private static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    private int AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = -16777216;
    private ArrayList<String> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new ArrayList<>();
    private int JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    private ComponentContainer container;
    private Context context;
    /* access modifiers changed from: private */
    public TabLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = -16777216;
    private LinearLayout mainLayout;
    private int pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = -1;
    private FrameLayout.LayoutParams wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public void HeightPercent(int i) {
    }

    public MakeroidTabLayout(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        componentContainer.$form().registerForOnOrientationChangeListener(this);
        this.context = componentContainer.$context();
        LinearLayout linearLayout = new LinearLayout(this.context);
        this.mainLayout = linearLayout;
        linearLayout.setOrientation(1);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TabLayout(this.context);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new FrameLayout.LayoutParams(-1, -2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabGravity(1);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public final void onTabReselected(TabLayout.Tab tab) {
            }

            public final void onTabUnselected(TabLayout.Tab tab) {
            }

            public final void onTabSelected(TabLayout.Tab tab) {
                MakeroidTabLayout.this.TabItemSelected(tab.getText().toString(), tab.getPosition() + 1);
            }
        });
        this.mainLayout.addView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        componentContainer.$add(this);
        TabsBackgroundColor(DEFAULT_PRIMARY_COLOR);
        TabsIndicatorColor(-1);
        TabsTextColor(-1);
        TabsActiveTextColor(DEFAULT_ACCENT_COLOR);
        TabsMode(1);
        Width(-2);
        Visible(true);
        componentContainer.$form().removeElevation();
        Log.d("Makeroid Tab Layout", "Makeroid Tab Layout Created");
    }

    public LinearLayout getView() {
        return this.mainLayout;
    }

    @SimpleProperty
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    public int Height() {
        return getView().getHeight();
    }

    public void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    @SimpleEvent(description = "The event returns the name or the position of the selected tab.")
    public void TabItemSelected(String str, int i) {
        EventDispatcher.dispatchEvent(this, "TabItemSelected", str, Integer.valueOf(i));
    }

    @SimpleFunction(description = "Removes a before added tab. If you want to delete the first tab then use as position '1'.")
    public void RemoveTabAt(int i) {
        if (i > 0) {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeTabAt(i - 1);
            } catch (Exception e) {
                Log.e("Makeroid Tab Layout", String.valueOf(e));
            }
        } else {
            try {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeTabAt(0);
            } catch (Exception e2) {
                Log.e("Makeroid Tab Layout", String.valueOf(e2));
            }
        }
    }

    @SimpleFunction(description = "Remove all tab's from tab layout.")
    public void RemoveAllTabs() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeAllTabs();
    }

    @SimpleFunction(description = "Returns the number of current added tab's.")
    public int CountTabs() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabCount();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Select a tab which is then the active tab.")
    public void SelectTab(final int i) {
        try {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    try {
                        MakeroidTabLayout.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabAt(i).select();
                    } catch (NullPointerException e) {
                        Log.e("Makeroid Tab Layout", String.valueOf(e));
                    } catch (Exception e2) {
                        Log.e("Makeroid Tab Layout", String.valueOf(e2));
                    }
                }
            }, 100);
        } catch (Exception e) {
            Log.e("Makeroid Tab Layout", String.valueOf(e));
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the current selected tab.")
    public int GetCurrentTab() {
        try {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getSelectedTabPosition();
        } catch (Exception e) {
            Log.e("Makeroid Tab Layout", String.valueOf(e));
            return 0;
        }
    }

    @SimpleFunction(description = "Add a new tab to the tab layout. If you don't want a icon then let it empty.")
    public void AddNewTab(String str, String str2) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, str2, -1);
    }

    @SimpleFunction(description = "Add a new tab to the tab layout at the given position. If you don't want a icon then let it empty.")
    public void AddNewTabAt(String str, String str2, int i) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str, str2, i);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, int i) {
        if (str.isEmpty()) {
            str = "Tab";
        }
        TabLayout.Tab newTab = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.newTab();
        newTab.setText((CharSequence) str);
        if (!str2.isEmpty()) {
            try {
                newTab.setIcon((Drawable) MediaUtil.getBitmapDrawable(this.container.$form(), str2));
            } catch (Exception e) {
                Log.d("Makeroid Tab Layout", e.getMessage());
            }
        }
        if (i != -1) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTab(newTab, i - 1);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTab(newTab);
        }
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's background color.")
    public void TabsBackgroundColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
        this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsBackgroundColor() {
        return this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's indicator color.")
    public void TabsIndicatorColor(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSelectedTabIndicatorColor(i);
        this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsIndicatorColor() {
        return this.pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's text color for not selected tab's.")
    public void TabsTextColor(int i) {
        this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(i, this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsTextColor() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's text color for active tab's.")
    public void TabsActiveTextColor(int i) {
        this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH, i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsActiveTextColor() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "1", editorType = "tabs_mode")
    @SimpleProperty(description = "Choose the mode used for the tab's. If no mode is specified, 'Scrollable' is taken as 'Default'. Use '0' for scrollable and '1' for fixed.")
    public void TabsMode(int i) {
        if (i == 0) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(1);
        } else if (i != 1) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
        }
    }

    @SimpleProperty
    public int TabsMode() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabMode();
    }

    public void onOrientationChange() {
        if (this.mainLayout.getVisibility() == 0) {
            this.container.$form().removeElevation();
        } else {
            this.container.$form().addElevation();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        getView().setVisibility(z ? 0 : 8);
        if (z) {
            this.container.$form().removeElevation();
        } else {
            this.container.$form().addElevation();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getView().getVisibility() == 0;
    }
}
