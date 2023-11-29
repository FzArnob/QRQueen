package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularDynamicModel;
import com.google.appinventor.components.runtime.util.KodularDynamicUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DYNAMIC, description = "A component to create dynamic textboxes in Arrangements", helpUrl = "https://docs.kodular.io/components/dynamic/dynamic-textbox/", iconName = "images/textbox.png", nonVisible = true, version = 4)
public final class MakeroidDynamicTextBox extends AndroidNonvisibleComponent {
    private final String LOG_TAG = "KodularDynamicTextBox";
    private Context context;
    private Drawable hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private List<KodularDynamicModel> kodularDynamicModelList = new ArrayList();

    public MakeroidDynamicTextBox(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        TextViewUtil.setContext(componentContainer.$context());
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new EditText(this.context).getBackground();
    }

    @SimpleFunction(description = "Create a Dynamic TextBox")
    public final void CreateTextBox(final int i, AndroidViewComponent androidViewComponent) {
        EditText editText = new EditText(this.context);
        TextViewUtil.setFontTypeface(editText, 0, false, false);
        TextViewUtil.setTextColor(editText, -16777216);
        editText.addTextChangedListener(new TextWatcher() {
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public final void afterTextChanged(Editable editable) {
                MakeroidDynamicTextBox.this.OnTextChanged(i, editable.toString());
            }
        });
        this.kodularDynamicModelList.add(new KodularDynamicModel(i, editText, androidViewComponent));
        ((LinearLayout) ((ViewGroup) androidViewComponent.getView()).getChildAt(0)).addView(editText);
    }

    @SimpleFunction(description = "Remove a textbox component with the given id.")
    public final void DeleteTextBox(int i) {
        try {
            ((LinearLayout) ((ViewGroup) ((AndroidViewComponent) KodularDynamicUtil.getViewHolderById(i, this.kodularDynamicModelList)).getView()).getChildAt(0)).removeView((EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList));
            KodularDynamicUtil.removeItemById(i, this.kodularDynamicModelList);
        } catch (Exception e) {
            Log.e("KodularDynamicTextBox", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Returns the textbox referenced by its id.")
    public final KodularDynamicUtil.ComponentReturnHelper GetTextBoxById(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            return new KodularDynamicUtil.ComponentReturnHelper(editText);
        }
        return null;
    }

    @SimpleFunction(description = "Update the Background Color of a TextBox")
    public final void SetBackgroundColor(int i, int i2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText == null) {
            return;
        }
        if (i2 != 0) {
            TextViewUtil.setBackgroundColor(editText, i2);
        } else {
            ViewUtil.setBackgroundDrawable(editText, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        }
    }

    @SimpleFunction(description = "Update the Text Color of a TextBox")
    public final void SetTextColor(int i, int i2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText == null) {
            return;
        }
        if (i2 != 0) {
            TextViewUtil.setTextColor(editText, i2);
        } else {
            TextViewUtil.setTextColor(editText, -16777216);
        }
    }

    @SimpleFunction(description = "Update the Font of a TextBox")
    public final void SetFont(int i, boolean z, boolean z2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            TextViewUtil.setFontTypeface(editText, 0, z, z2);
        }
    }

    @SimpleFunction(description = "Update the Width of a TextBox")
    public final void SetWidth(int i, int i2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            editText.setWidth(i2);
        }
    }

    @SimpleFunction(description = "Get the Width of a TextBox")
    public final int GetWidth(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            return editText.getWidth();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Height of a TextBox")
    public final void SetHeight(int i, int i2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            editText.setHeight(i2);
        }
    }

    @SimpleFunction(description = "Get the Height of a TextBox")
    public final int GetHeight(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            return editText.getHeight();
        }
        return 0;
    }

    @SimpleFunction(description = "Update the Enabled status of a TextBox")
    public final void SetEnabled(int i, boolean z) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            TextViewUtil.setEnabled(editText, z);
        }
    }

    @SimpleFunction(description = "Get the Enabled status of a TextBox")
    public final boolean GetEnabled(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            return TextViewUtil.isEnabled(editText);
        }
        return false;
    }

    @SimpleFunction(description = "Update the Font Size of a TextBox")
    public final void SetFontSize(int i, float f) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            TextViewUtil.setFontSize(editText, f);
        }
    }

    @SimpleFunction(description = "Get the Font Size of a TextBox")
    public final float GetFontSize(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            return TextViewUtil.getFontSize(editText, this.context);
        }
        return 0.0f;
    }

    @SimpleFunction(description = "Update the Text of a TextBox")
    public final void SetText(int i, String str) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            TextViewUtil.setText(editText, str);
        }
    }

    @SimpleFunction(description = "Get the Text of a TextBox")
    public final String GetText(int i) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        return editText != null ? TextViewUtil.getText(editText) : "";
    }

    @SimpleFunction(description = "Update the Text Alignment of a TextBox. 0 = left, 1 = center and 2 = right.")
    public final void SetAlignment(int i, int i2) {
        EditText editText = (EditText) KodularDynamicUtil.getObjectById(i, this.kodularDynamicModelList);
        if (editText != null) {
            TextViewUtil.setAlignment(editText, i2, false);
        }
    }

    @SimpleEvent(description = "Trigger when the text of a Dynamic TextBox changes")
    public final void OnTextChanged(int i, String str) {
        EventDispatcher.dispatchEvent(this, "OnTextChanged", Integer.valueOf(i), str);
    }
}
