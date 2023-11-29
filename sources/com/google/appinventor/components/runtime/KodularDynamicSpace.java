package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic space in Arrangements.", iconName = "images/space.png", nonVisible = true, version = 2)
public final class KodularDynamicSpace extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicSpace";
    private Context context;
    private Form form;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();

    public KodularDynamicSpace(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
    }

    @SimpleFunction(description = "Create a new space component dynamically. Use for width/height '-1' for wrap content or '-2' for fill parent.")
    public final void CreateSpace(int i, AndroidViewComponent androidViewComponent, int i2, int i3) {
        try {
            Space space = new Space(this.context);
            int DpToPixels = KodularUnitUtil.DpToPixels(this.context, i2);
            int DpToPixels2 = KodularUnitUtil.DpToPixels(this.context, i3);
            if (i2 == -1) {
                DpToPixels = -2;
            } else if (i2 == -2) {
                DpToPixels = -1;
            }
            if (i3 == -1) {
                DpToPixels2 = -2;
            } else if (i3 == -2) {
                DpToPixels2 = -1;
            }
            space.setLayoutParams(new LinearLayout.LayoutParams(DpToPixels, DpToPixels2));
            this.kodularDynamicModelList.add(new KodularDynamicModel(i, space, androidViewComponent));
            ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(space);
        } catch (Exception e) {
            Log.e("KodularDynamicSpace", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a space component with the given id.")
    public final void DeleteSpace(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("KodularDynamicSpace", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the space referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetSpaceById(int i) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (space != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(space);
        }
        return null;
    }

    @SimpleFunction(description = "Update the Width of a space component.")
    public final void SetWidth(int i, int i2) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (space != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) space.getLayoutParams();
            layoutParams.width = KodularUnitUtil.DpToPixels(this.context, i2);
            space.setLayoutParams(layoutParams);
            space.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a space component.")
    public final int GetWidth(int i) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (space != null) {
            return space.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a space component.")
    public final void SetHeight(int i, int i2) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (space != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) space.getLayoutParams();
            layoutParams.height = KodularUnitUtil.DpToPixels(this.context, i2);
            space.setLayoutParams(layoutParams);
            space.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a space component.")
    public final int GetHeight(int i) {
        Space space = (Space) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (space != null) {
            return space.getHeight();
        }
        return 0;
    }
}
