package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic buttons in Arrangements", iconName = "images/button.png", nonVisible = true, version = 4)
public final class MakeroidDynamicButton extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "MakeroidDynamicButton";
    private Context context;
    private ColorStateList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f204hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();

    public MakeroidDynamicButton(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Button button = new Button(this.context);
        this.f204hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = button.getBackground();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = button.getTextColors();
    }

    @SimpleFunction(description = "Create a Dynamic Button.")
    public final void CreateButton(final int i, AndroidViewComponent androidViewComponent) {
        Button button = new Button(this.context);
        button.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        button.setAllCaps(false);
        TextViewUtil.setFontTypeface(button, 0, false, false);
        button.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MakeroidDynamicButton.this.ButtonClick(i);
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            public final boolean onLongClick(View view) {
                MakeroidDynamicButton.this.ButtonLongClick(i);
                return true;
            }
        });
        this.kodularDynamicModelList.add(new KodularDynamicModel(i, button, androidViewComponent));
        ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(button);
    }

    @SimpleFunction(description = "Delete a Dynamic Button.")
    public final void DeleteButtonNew(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("MakeroidDynamicButton", String.valueOf(e));
        }
    }

    @Deprecated
    @SimpleFunction(description = "This block is DEPRECATED! Please use instead the 'Delete Button' block without arrangement parameter.")
    public final void DeleteButton(int i, HVArrangement hVArrangement) {
        try {
            ((LinearLayout) ((ViewGroup) hVArrangement.getView()).getChildAt(0)).removeView((Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("MakeroidDynamicButton", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the button referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetButtonById(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(button);
        }
        return null;
    }

    @SimpleFunction(description = "Update the Background Color of a button.")
    public final void SetBackgroundColor(int i, int i2) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            button.getBackground().setColorFilter(i2, PorterDuff.Mode.SRC_ATOP);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a button.")
    public final void SetTextColor(int i, int i2) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button == null) {
            return;
        }
        if (i2 != 0) {
            TextViewUtil.setTextColor(button, i2);
        } else {
            TextViewUtil.setTextColors(button, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
    }

    @SimpleFunction(description = "Update the Font of a button.")
    public final void SetFont(int i, boolean z, boolean z2) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            TextViewUtil.setFontTypeface(button, 0, z, z2);
        }
    }

    @SimpleFunction(description = "Update the Width of a button.")
    public final void SetWidth(int i, int i2) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.width = i2;
            button.setLayoutParams(layoutParams);
            button.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Width of a button.")
    public final int GetWidth(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            return button.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a button.")
    public final void SetHeight(int i, int i2) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
            layoutParams.height = i2;
            button.setLayoutParams(layoutParams);
            button.invalidate();
        }
    }

    @SimpleFunction(description = "Get the Height of a button.")
    public final int GetHeight(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            return button.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Enabled status of a button.")
    public final void SetEnabled(int i, boolean z) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            TextViewUtil.setEnabled(button, z);
        }
    }

    @SimpleFunction(description = "Get the Enabled status of a button.")
    public final boolean GetEnabled(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            return TextViewUtil.isEnabled(button);
        }
        return false;
    }

    @SimpleFunction(description = "Update the Font Size of a button.")
    public final void SetFontSize(int i, float f) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            TextViewUtil.setFontSize(button, f);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a button.")
    public final float GetFontSize(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            return TextViewUtil.getFontSize(button, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a button.")
    public final void SetText(int i, String str) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (button != null) {
            button.setText(TextViewUtil.fromHtml(str));
        }
    }

    @SimpleFunction(description = "Get the Text of a button.")
    public final String GetText(int i) {
        Button button = (Button) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        return button != null ? TextViewUtil.getText(button) : "";
    }

    @SimpleEvent(description = "Trigger when a Dynamic Button is clicked.")
    public final void ButtonClick(int i) {
        EventDispatcher.dispatchEvent(this, "ButtonClick", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Trigger when a Dynamic Button is long clicked.")
    public final void ButtonLongClick(int i) {
        EventDispatcher.dispatchEvent(this, "ButtonLongClick", Integer.valueOf(i));
    }
}
