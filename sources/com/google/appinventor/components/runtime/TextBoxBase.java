package com.google.appinventor.components.runtime;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.lang.reflect.Field;

@SimpleObject
@UsesLibraries({"spinkit.jar"})
public abstract class TextBoxBase extends AndroidViewComponent implements View.OnFocusChangeListener {
    private String CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
    private boolean TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = true;
    private int backgroundColor;
    private int fontTypeface;
    private int gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = -16777216;
    private Drawable hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private int iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
    private int iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = 0;
    private int mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    private boolean oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    private double rotationAngle = 0.0d;
    private int textColor;
    private boolean vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    protected final EditText view;

    @Deprecated
    @SimpleProperty(description = "DEPRECATED since this feature is not working. Use 'Enabled' instead.")
    public void EnableCopyPaste(boolean z) {
    }

    @Deprecated
    @SimpleProperty
    public boolean EnableCopyPaste() {
        return false;
    }

    public TextBoxBase(ComponentContainer componentContainer, EditText editText) {
        super(componentContainer);
        this.view = editText;
        if (Build.VERSION.SDK_INT >= 24) {
            editText.setInputType(editText.getInputType() | 524288);
        }
        editText.setOnFocusChangeListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            public final void afterTextChanged(Editable editable) {
            }

            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                TextBoxBase.this.OnTextChanged();
            }
        });
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = editText.getBackground();
        componentContainer.$add(this);
        componentContainer.setChildWidth(this, ComponentConstants.TEXTBOX_PREFERRED_WIDTH);
        TextAlignment(0);
        Enabled(true);
        this.fontTypeface = 0;
        TextViewUtil.setContext(componentContainer.$context());
        TextViewUtil.setFontTypeface(editText, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        FontSize(14.0f);
        Hint("");
        Text("");
        CursorColor(-16777216);
        TextColor(-16777216);
        HintColor(-16777216);
        RotationAngle(0.0d);
        CursorVisible(true);
    }

    public View getView() {
        return this.view;
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Please delete this block from your project.This block is deprecated and not longer supported.")
    public int LineColor() {
        return this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn;
    }

    @Deprecated
    @SimpleProperty
    public void LineColor(int i) {
        this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = i;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color", propertyType = "advanced")
    @SimpleProperty
    public void CursorColor(int i) {
        this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = i;
        EditText editText = this.view;
        try {
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i2 = declaredField.getInt(editText);
            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(editText);
            Drawable drawable = ContextCompat.getDrawable(editText.getContext(), i2);
            if (drawable != null) {
                drawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
            }
            Drawable[] drawableArr = {drawable, drawable};
            Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
            declaredField3.setAccessible(true);
            declaredField3.set(obj, drawableArr);
        } catch (Exception unused) {
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the cursor.")
    public int CursorColor() {
        return this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk;
    }

    @SimpleFunction(description = "Place a blurred shadow of text underneath the text, drawn with the specified x, y, radius, color (e.g. -11, 12, 13, black ")
    public void SetShadow(float f, float f2, float f3, int i) {
        KodularHelper.setShadow(this.view, f, f2, f3, i);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationAngle(double d) {
        this.rotationAngle = d;
        this.view.setRotation((float) d);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the degrees that the textbox is rotated around the pivot point. Increasing values result in clockwise rotation.")
    public double RotationAngle() {
        return this.rotationAngle;
    }

    @SimpleEvent(description = "Event to detect text changes.")
    public void OnTextChanged() {
        EventDispatcher.dispatchEvent(this.view, this, "OnTextChanged", new Object[0]);
    }

    @SimpleProperty(description = "Returns the current text length as number.")
    public int TextLength() {
        return this.view.getText().length();
    }

    @SimpleFunction(description = "Set the cursor to the given position.")
    public void SetCursorAt(int i) {
        if (this.view.getText().length() >= i) {
            this.view.setSelection(i);
            return;
        }
        EditText editText = this.view;
        editText.setSelection(editText.getText().length());
    }

    @SimpleFunction(description = "Set the cursor to the end of the text.")
    public void SetCursorAtEnd() {
        EditText editText = this.view;
        editText.setSelection(editText.getText().length());
    }

    @SimpleProperty(description = "Get the current cursor position.")
    public int CurrentPosition() {
        return this.view.getSelectionStart();
    }

    @SimpleEvent
    public void GotFocus() {
        EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent
    public void LostFocus() {
        EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should be left justified, centered, or right justified.  By default, text is left justified.", userVisible = false)
    public int TextAlignment() {
        return this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    }

    @DesignerProperty(defaultValue = "0", editorType = "textalignment")
    @SimpleProperty(userVisible = false)
    public void TextAlignment(int i) {
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = i;
        TextViewUtil.setAlignment(this.view, i, false);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The background color of the input box.  You can choose a color by name in the Designer or in the Blocks Editor.  The default background color is 'default' (shaded 3-D look).")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        if (i != 0) {
            TextViewUtil.setBackgroundColor(this.view, i);
        } else {
            ViewUtil.setBackgroundDrawable(this.view, this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the user can enter text into this input box.  By default, this is true.")
    public boolean Enabled() {
        return TextViewUtil.isEnabled(this.view);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        TextViewUtil.setEnabled(this.view, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the font for the text should be bold.  By default, it is not.", userVisible = false)
    public boolean FontBold() {
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void FontBold(boolean z) {
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = z;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, z, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Whether the text should appear in italics.  By default, it does not.", userVisible = false)
    public boolean FontItalic() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public void FontItalic(boolean z) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = z;
        TextViewUtil.setFontTypeface(this.view, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size for the text.  By default, it is 14.0 points.")
    public float FontSize() {
        return TextViewUtil.getFontSize(this.view, this.container.$context());
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public void FontSize(float f) {
        TextViewUtil.setFontSize(this.view, f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font for the text.  The value can be changed in the Designer.", userVisible = false)
    public int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypeface(int i) {
        this.fontTypeface = i;
        TextViewUtil.setFontTypeface(this.view, i, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text that should appear faintly in the input box to provide a hint as to what the user should enter.  This can only be seen if the <code>Text</code> property is empty.")
    public String Hint() {
        return this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void Hint(String str) {
        this.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = str;
        TextViewUtil.setHint(this.view, str);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String Text() {
        return TextViewUtil.getText(this.view);
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The text in the input box, which can be set by the programmer in the Designer or Blocks Editor, or it can be entered by the user (unless the <code>Enabled</code> property is false).")
    public void Text(String str) {
        TextViewUtil.setText(this.view, str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color for the text.  You can choose a color by name in the Designer or in the Blocks Editor.  The default text color is black.")
    public int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        this.textColor = i;
        if (i != 0) {
            TextViewUtil.setTextColor(this.view, i);
        } else {
            TextViewUtil.setTextColor(this.view, -16777216);
        }
    }

    @SimpleFunction(description = "Sets the textbox active.")
    public void RequestFocus() {
        this.view.requestFocus();
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void FontTypefaceImport(String str) {
        if (str != null) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), this.view, str, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        }
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Set a custom hint text color.")
    public void HintColor(int i) {
        this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = i;
        if (i != 0) {
            TextViewUtil.setHintColor(this.view, i);
        } else {
            TextViewUtil.setHintColor(this.view, -16777216);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Return the hint text color.")
    public int HintColor() {
        return this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
    }

    public void onFocusChange(View view2, boolean z) {
        if (z) {
            GotFocus();
        } else {
            LostFocus();
        }
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Makes the cursor visible (the default) or invisible.")
    public void CursorVisible(boolean z) {
        this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq = z;
        this.view.setCursorVisible(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public boolean CursorVisible() {
        return this.TVKenNjujur1C1Ft9Gj8dhchvJBwuJV9GDuQOmGg2gZVCkxzGoaa0a88A5IZ9COq;
    }

    @SimpleFunction(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, animation will be removed.Position can be: top, left, right, bottom. Size can be 100. ")
    public void AnimationStyle(String str, String str2, int i, int i2) {
        ProgressHelper.setButtonProgressAnimation(this.view, str, str2, i, i2);
        this.view.setVisibility(8);
        this.view.setVisibility(0);
        this.view.invalidate();
    }
}
