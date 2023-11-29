package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
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
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NAVIGATION, description = "", iconName = "images/viewPager.png", version = 3)
public class MakeroidViewPager extends AndroidViewComponent implements OnOrientationChangeListener {
    private static final int DEFAULT_ACCENT_COLOR = PaintUtil.hexStringToInt(ComponentConstants.DEFAULT_ACCENT_COLOR);
    private static final int DEFAULT_PRIMARY_COLOR = PaintUtil.hexStringToInt("&HFF3F51B5");
    private int AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = -16777216;
    private ArrayList<String> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new ArrayList<>();
    private int JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    private boolean YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = true;
    private ComponentContainer container;
    private Context context;
    private ViewPager.LayoutParams hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ViewPager f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private TabLayout f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private a f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = -16777216;
    private LinearLayout mainLayout;
    private int pFeTJgO2w7vELkZyStZDj0uZpMYRqdjcmMjC2zcPDquoynj4tIsgJjD3RDJtFf88 = -1;
    private FrameLayout.LayoutParams wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public MakeroidViewPager(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        componentContainer.$form().registerForOnOrientationChangeListener(this);
        this.context = componentContainer.$context();
        LinearLayout linearLayout = new LinearLayout(this.context);
        this.mainLayout = linearLayout;
        linearLayout.setOrientation(1);
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TabLayout(this.context);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new FrameLayout.LayoutParams(-1, -2);
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabGravity(1);
        ViewPager viewPager = new ViewPager(this.context);
        this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = viewPager;
        viewPager.setOffscreenPageLimit(10);
        this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public final void onPageScrollStateChanged(int i) {
            }

            public final void onPageScrolled(int i, float f, int i2) {
            }

            public final void onPageSelected(int i) {
                MakeroidViewPager.this.PageSelected(i + 1);
            }
        });
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = layoutParams;
        layoutParams.height = -1;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.width = -1;
        a aVar = new a(this, (byte) 0);
        this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
        this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdapter(aVar);
        this.mainLayout.addView(this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
        this.mainLayout.addView(this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setupWithViewPager(this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        componentContainer.$add(this);
        TabsBackgroundColor(DEFAULT_PRIMARY_COLOR);
        TabsIndicatorColor(-1);
        TabsTextColor(-1);
        TabsActiveTextColor(DEFAULT_ACCENT_COLOR);
        TabsMode(1);
        ShowTabs(true);
        Visible(true);
        Width(-2);
        Height(-2);
        Log.d("Makeroid View Pager", "Makeroid View Pager Created");
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

    @SimpleProperty
    public void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    @SimpleEvent(description = "Event to detect that a page was selected.")
    public void PageSelected(int i) {
        EventDispatcher.dispatchEvent(this, "PageSelected", Integer.valueOf(i));
    }

    @SimpleFunction(description = "Removes a before added view from the view pager. If you want to delete the first page then use as position '1'.")
    public void RemoveViewAt(int i) {
        if (i > 0) {
            try {
                int i2 = i - 1;
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i2);
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2);
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e("Makeroid View Pager", String.valueOf(e));
            }
        } else {
            try {
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0);
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(0);
                this.f223hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notifyDataSetChanged();
            } catch (Exception e2) {
                Log.e("Makeroid View Pager", String.valueOf(e2));
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Select a page which is then the active page. Use '1' to select the first page.")
    public void SelectPage(int i) {
        try {
            this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentItem(i - 1, true);
        } catch (Exception e) {
            Log.e("Makeroid View Pager", String.valueOf(e));
            this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCurrentItem(0, true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Get the current selected visible page.")
    public int GetCurrentPage() {
        try {
            return this.f221hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCurrentItem() + 1;
        } catch (Exception e) {
            Log.e("Makeroid View Pager", String.valueOf(e));
            return 0;
        }
    }

    @SimpleFunction(description = "Add a component to the view pager. The first added component will be the first visible component on the screen.")
    public void AddComponentToView(final AndroidViewComponent androidViewComponent, final String str) {
        try {
            new Handler().post(new Runnable() {
                public final void run() {
                    View view = androidViewComponent.getView();
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(view);
                    }
                    a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = MakeroidViewPager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager.this);
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.add(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.size(), view);
                    MakeroidViewPager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager.this).notifyDataSetChanged();
                    MakeroidViewPager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager.this, !str.isEmpty() ? str : "Tab");
                }
            });
        } catch (Exception e) {
            Log.e("Makeroid View Pager", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove all tab's from view pager.")
    public void RemoveAllTabs() {
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.removeAllTabs();
    }

    @DesignerProperty(defaultValue = "&HFF3F51B5", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's background color.")
    public void TabsBackgroundColor(int i) {
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
        this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsBackgroundColor() {
        return this.JTEvmldvMjbbtPPlVS4hmZghOoRNnXC0kZOUUAZdwkVNl1VM5pL0vCTYv5HQZ7AX;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's indicator color.")
    public void TabsIndicatorColor(int i) {
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSelectedTabIndicatorColor(i);
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
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(i, this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsTextColor() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "&HFFFF4081", editorType = "color")
    @SimpleProperty(description = "Specifies the tab's text color for active tab's.")
    public void TabsActiveTextColor(int i) {
        this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = i;
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabTextColors(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH, i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TabsActiveTextColor() {
        return this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd;
    }

    @DesignerProperty(defaultValue = "1", editorType = "tabs_mode")
    @SimpleProperty(description = "Choose the mode used for the tab's. If no mode is specified, 'Scrollable' is taken as 'Default'. Use '0' for scrollable and '1' for fixed.")
    public void TabsMode(int i) {
        if (i == 0) {
            this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
        } else if (i != 1) {
            this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(0);
        } else {
            this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTabMode(1);
        }
    }

    @SimpleProperty
    public int TabsMode() {
        return this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabMode();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "experimental")
    @SimpleProperty(description = "If set to true, you will see tabs above the view pager.")
    public void ShowTabs(boolean z) {
        this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = z;
        this.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(z ? 0 : 8);
        if (z) {
            this.container.$form().removeElevation();
        } else {
            this.container.$form().addElevation();
        }
    }

    @SimpleProperty
    public boolean ShowTabs() {
        return this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp;
    }

    public void onOrientationChange() {
        if (this.mainLayout.getVisibility() != 0 || !this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp) {
            this.container.$form().addElevation();
        } else {
            this.container.$form().removeElevation();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "visibility")
    @SimpleProperty(description = "Specifies whether the component should be visible on the screen. Value is true if the component is showing and false if hidden.")
    public void Visible(boolean z) {
        getView().setVisibility(z ? 0 : 8);
        if (!z || !this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp) {
            this.container.$form().addElevation();
        } else {
            this.container.$form().removeElevation();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean Visible() {
        return getView().getVisibility() == 0;
    }

    class a extends PagerAdapter {
        ArrayList<View> wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        public final boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        private a() {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new ArrayList<>();
        }

        /* synthetic */ a(MakeroidViewPager makeroidViewPager, byte b) {
            this();
        }

        public final int getItemPosition(Object obj) {
            int indexOf = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.indexOf(obj);
            if (indexOf == -1) {
                return -2;
            }
            return indexOf;
        }

        public final Object instantiateItem(ViewGroup viewGroup, int i) {
            try {
                View view = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(i);
                viewGroup.addView(view);
                return view;
            } catch (Exception e) {
                Log.e("Makeroid View Pager", String.valueOf(e));
                return null;
            }
        }

        public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            try {
                viewGroup.removeView(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(i));
            } catch (Exception e) {
                Log.e("Makeroid View Pager", String.valueOf(e));
            }
        }

        public final int getCount() {
            return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.size();
        }

        public final int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ViewPager viewPager, int i) {
            viewPager.setAdapter((PagerAdapter) null);
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.remove(i);
            viewPager.setAdapter(this);
            return i;
        }

        public final CharSequence getPageTitle(int i) {
            Log.i("Makeroid View Pager", String.valueOf(i));
            try {
                return ((String) MakeroidViewPager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager.this).get(i));
            } catch (Exception e) {
                Log.e("Makeroid View Pager", String.valueOf(e));
                return "";
            }
        }

        public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
            MakeroidViewPager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager.this).remove(i);
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(MakeroidViewPager makeroidViewPager, String str) {
        makeroidViewPager.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.add(str);
        makeroidViewPager.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.newTab().setText((CharSequence) str);
        int i = 0;
        while (i < makeroidViewPager.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.size()) {
            try {
                makeroidViewPager.f222hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTabAt(i).setText((CharSequence) makeroidViewPager.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(i));
                i++;
            } catch (Exception e) {
                Log.e("Makeroid View Pager", String.valueOf(e));
                return;
            }
        }
    }
}
