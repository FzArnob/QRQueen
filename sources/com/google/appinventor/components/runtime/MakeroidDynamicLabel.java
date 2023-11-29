package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic labels in Arrangements", iconName = "images/label.png", nonVisible = true, version = 3)
public final class MakeroidDynamicLabel extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicLabel";
    private Context context;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();

    @SimpleProperty(description = "Center alignment (1)")
    public final int AlignmentCenter() {
        return 1;
    }

    @SimpleProperty(description = "Left alignment (0)")
    public final int AlignmentLeft() {
        return 0;
    }

    @SimpleProperty(description = "Right alignment (2)")
    public final int AlignmentRight() {
        return 2;
    }

    public MakeroidDynamicLabel(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
    }

    @SimpleFunction(description = "Create a Dynamic Label")
    public final void CreateLabel(int i, AndroidViewComponent androidViewComponent) {
        try {
            TextView textView = new TextView(this.context);
            TextViewUtil.setFontTypeface(textView, 0, false, false);
            TextViewUtil.setTextColor(textView, -16777216);
            this.kodularDynamicModelList.add(new KodularDynamicModel(i, textView, androidViewComponent));
            ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(textView);
        } catch (Exception e) {
            Log.e("KodularDynamicLabel", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Remove a label component with the given id.")
    public final void DeleteLabel(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("KodularDynamicLabel", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the label referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetLabelById(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(textView);
        }
        return null;
    }

    @SimpleFunction(description = "Update the Background Color of a Label")
    public final void SetBackgroundColor(int i, int i2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView == null) {
            return;
        }
        if (i2 != 0) {
            TextViewUtil.setBackgroundColor(textView, i2);
        } else {
            TextViewUtil.setBackgroundColor(textView, 16777215);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a Label")
    public final void SetTextColor(int i, int i2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView == null) {
            return;
        }
        if (i2 != 0) {
            TextViewUtil.setTextColor(textView, i2);
        } else {
            TextViewUtil.setTextColor(textView, -16777216);
        }
    }

    @SimpleFunction(description = "Update the Font of a Label")
    public final void SetFont(int i, boolean z, boolean z2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            TextViewUtil.setFontTypeface(textView, 0, z, z2);
        }
    }

    @SimpleFunction(description = "Update the Width of a Label")
    public final void SetWidth(int i, int i2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            textView.setWidth(i2);
        }
    }

    @SimpleFunction(description = "Get the Width of a Label")
    public final int GetWidth(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            return textView.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a Label")
    public final void SetHeight(int i, int i2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            textView.setHeight(i2);
        }
    }

    @SimpleFunction(description = "Get the Height of a Label")
    public final int GetHeight(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            return textView.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Font Size of a Label")
    public final void SetFontSize(int i, float f) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            TextViewUtil.setFontSize(textView, f);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a Label")
    public final float GetFontSize(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            return TextViewUtil.getFontSize(textView, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a Label")
    public final void SetText(int i, String str, boolean z) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView == null) {
            return;
        }
        if (z) {
            TextViewUtil.setTextHTML(textView, str);
        } else {
            TextViewUtil.setText(textView, str);
        }
    }

    @SimpleFunction(description = "Get the Text of a Label")
    public final String GetText(int i) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        return textView != null ? TextViewUtil.getText(textView) : "";
    }

    @SimpleFunction(description = "Update the Text Alignment of a Label. 0 = left, 1 = center and 2 = right.")
    public final void SetAlignment(int i, int i2) {
        TextView textView = (TextView) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (textView != null) {
            TextViewUtil.setAlignment(textView, i2, false);
        }
    }
}
