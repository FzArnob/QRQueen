package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AlignmentUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.ViewUtil;
import com.google.appinventor.components.runtime.util.YailList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "", version = 2)
@UsesLibraries(libraries = "swiperefreshlayout.jar, swiperefreshlayout.aar")
public class MakeroidSwipeRefreshLayout extends AndroidViewComponent implements SwipeRefreshLayout.OnRefreshListener, ComponentContainer {
    private boolean AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = false;
    private int RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = -1;
    private boolean Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = false;
    private final Activity activity;
    private AlignmentUtil alignmentSetter;
    private final Handler androidUIHandler = new Handler();
    private int backgroundColor = 0;
    private ComponentContainer container;
    private Context context;
    private int horizontalAlignment;
    private final SwipeRefreshLayout hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = true;
    private final ViewGroup mainLayout;
    private YailList sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = YailList.makeList(new Object[]{-14575886, Integer.valueOf(Component.COLOR_GREEN), Integer.valueOf(Component.COLOR_YELLOW), Integer.valueOf(Component.COLOR_RED)});
    private int verticalAlignment;
    private final LinearLayout viewLayout;

    public MakeroidSwipeRefreshLayout(ComponentContainer componentContainer) {
        super(componentContainer);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(this.context, 1, 100, 100);
        this.viewLayout = linearLayout;
        linearLayout.setBaselineAligned(false);
        AlignmentUtil alignmentUtil = new AlignmentUtil(linearLayout);
        this.alignmentSetter = alignmentUtil;
        this.horizontalAlignment = 1;
        this.verticalAlignment = 1;
        alignmentUtil.setHorizontalAlignment(1);
        this.alignmentSetter.setVerticalAlignment(this.verticalAlignment);
        swipeRefreshLayout.addView(linearLayout.getLayoutManager(), new ViewGroup.LayoutParams(-1, -1));
        FrameLayout frameLayout = new FrameLayout(this.context);
        this.mainLayout = frameLayout;
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
        frameLayout.addView(swipeRefreshLayout);
        componentContainer.$add(this);
        Enabled(true);
        ProgressAnimationColors(this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
        ProgressBackgroundColor(-1);
        LargeSize(false);
        BackgroundColor(0);
        NestedScrolling(false);
        Log.d("Swipe Refresh Layout", "Swipe Refresh Layout Created");
    }

    public View getView() {
        return this.mainLayout;
    }

    public Activity $context() {
        return this.activity;
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.viewLayout.add(androidViewComponent);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        setChildWidth(androidViewComponent, i, 0);
    }

    public void setChildWidth(final AndroidViewComponent androidViewComponent, final int i, final int i2) {
        int Width = this.container.$form().Width();
        if (Width == 0 && i2 < 2) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    MakeroidSwipeRefreshLayout.this.setChildWidth(androidViewComponent, i, i2 + 1);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Width * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastWidth(i);
        ViewUtil.setChildWidthForVerticalLayout(androidViewComponent.getView(), i);
    }

    public void setChildHeight(final AndroidViewComponent androidViewComponent, final int i) {
        int Height = this.container.$form().Height();
        if (Height == 0) {
            this.androidUIHandler.postDelayed(new Runnable() {
                public final void run() {
                    MakeroidSwipeRefreshLayout.this.setChildHeight(androidViewComponent, i);
                }
            }, 100);
        }
        if (i <= -1000) {
            i = (Height * (-(i + 1000))) / 100;
        }
        androidViewComponent.setLastHeight(i);
        ViewUtil.setChildHeightForVerticalLayout(androidViewComponent.getView(), i);
    }

    public void onRefresh() {
        if (this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH) {
            OnRefresh();
        }
    }

    @SimpleEvent(description = "Event to detect when a refresh is triggered via the swipe gesture.")
    public void OnRefresh() {
        EventDispatcher.dispatchEvent(this, "OnRefresh", new Object[0]);
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Change the pull to refresh background color.")
    public void ProgressBackgroundColor(int i) {
        this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgressBackgroundColorSchemeColor(i);
    }

    @SimpleProperty(description = "Return the pull to refresh background color.")
    public int ProgressBackgroundColor() {
        return this.RC7PBJGdnqEffr8752ypFkbK8qZYkmQ3ci6maWfntRZXmeHa8bLhdKUgkXcpRo6T;
    }

    @SimpleProperty(description = "Set the colors used in the progress animation. Use a 'make a list' block. The first color on the list is than the first spinner color.")
    public void ProgressAnimationColors(YailList yailList) {
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = yailList;
        String[] stringArray = yailList.toStringArray();
        int[] iArr = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            iArr[i] = Integer.parseInt(stringArray[i]);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setColorSchemeColors(iArr);
    }

    @SimpleProperty(description = "Returns the used colors als list.")
    public YailList ProgressAnimationColors() {
        return this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
    }

    @SimpleProperty(description = "Notify the widget that refresh state has changed. Do not call this when refresh is triggered by a swipe gesture.")
    public void Refreshing(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRefreshing(z);
    }

    @SimpleProperty(description = "Returns true if is actively showing refresh progress.")
    public boolean IsRefreshing() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRefreshing();
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If enabled is set to true the pull size will be large, else the size is default.")
    public void LargeSize(boolean z) {
        this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSize(z ^ true ? 1 : 0);
    }

    @SimpleProperty(description = "Returns true if large size is used.")
    public boolean LargeSize() {
        return this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true the swpipe refresh layout is enabled.")
    public void Enabled(boolean z) {
        this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setEnabled(z);
    }

    @SimpleProperty(description = "Returns true if enabled.")
    public boolean Enabled() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Enable or disable nested scrolling for this view.")
    public void NestedScrolling(boolean z) {
        this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNestedScrollingEnabled(z);
    }

    @SimpleProperty(description = "Returns true if nested scrolling is enabled.")
    public boolean NestedScrolling() {
        return this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi;
    }

    @DesignerProperty(defaultValue = "1", editorType = "horizontal_alignment")
    @SimpleProperty
    public void AlignHorizontal(int i) {
        try {
            this.alignmentSetter.setHorizontalAlignment(i);
            this.horizontalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "HorizontalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_HORIZONTAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how contents of the arrangement are aligned  horizontally. The choices are: 1 = left aligned, 2 = right aligned,  3 = horizontally centered.  Alignment has no effect if the arrangement's width is automatic.")
    public int AlignHorizontal() {
        return this.horizontalAlignment;
    }

    @DesignerProperty(defaultValue = "1", editorType = "vertical_alignment")
    @SimpleProperty
    public void AlignVertical(int i) {
        try {
            this.alignmentSetter.setVerticalAlignment(i);
            this.verticalAlignment = i;
        } catch (IllegalArgumentException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "VerticalAlignment", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, Integer.valueOf(i));
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how the contents of the arrangement are aligned  vertically. The choices are: 1 = aligned at the top, 2 = vertically centered, 3 = aligned at the bottom.  Alignment has no effect if the arrangement's height is automatic.")
    public int AlignVertical() {
        return this.verticalAlignment;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        this.mainLayout.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the swipe refresh layout.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }
}
