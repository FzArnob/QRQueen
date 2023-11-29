package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Snackbar", iconName = "images/snackbar.png", nonVisible = true, version = 4)
public class MakeroidSnackbar extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Snackbar";
    private final Activity activity;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private int buttonTextColor = -1;
    private int duration = 0;
    private Form form;
    /* access modifiers changed from: private */
    public Snackbar snackbar;
    private int textColor = -1;

    public MakeroidSnackbar(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.form = componentContainer.$form();
        Log.d(LOG_TAG, "Snackbar created");
    }

    @DesignerProperty(defaultValue = "0", editorType = "snackbar_duration")
    @SimpleProperty(userVisible = false)
    public void Duration(int i) {
        this.duration = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "specifies the length of time that the Snackbar is shown - either \"short\" or \"long\".")
    public int Duration() {
        return this.duration;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void ButtonTextColor(int i) {
        this.buttonTextColor = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the text color for action button.")
    public int ButtonTextColor() {
        return this.buttonTextColor;
    }

    @SimpleFunction(description = "Show Snackbar (message supports HTML formatting)")
    public void Show(String str) {
        showSnackbar(str, (String) null);
    }

    @SimpleFunction(description = "Returns true whether this snackbar is currently being shown.")
    public boolean IsShown() {
        Snackbar snackbar2 = this.snackbar;
        if (snackbar2 != null) {
            return snackbar2.isShown();
        }
        return false;
    }

    @SimpleFunction(description = "Dismiss the snackbar.")
    public void Dismiss() {
        Snackbar snackbar2 = this.snackbar;
        if (snackbar2 != null) {
            snackbar2.dismiss();
        }
    }

    @SimpleFunction(description = "Show Snackbar with action button (message supports HTML formatting)")
    public void ShowWithButton(String str, String str2) {
        showSnackbar(str, str2);
    }

    private void showSnackbar(String str, String str2) {
        if (str.isEmpty()) {
            str = "Hello world! Snackbar example text";
        }
        Form form2 = this.form;
        if (form2 == null || form2.coordinatorLayout == null) {
            this.snackbar = Snackbar.make(this.activity.findViewById(16908290), (CharSequence) TextViewUtil.fromHtml(getColoredText(str)), this.duration);
        } else {
            this.snackbar = Snackbar.make((View) this.form.coordinatorLayout, (CharSequence) TextViewUtil.fromHtml(getColoredText(str)), this.duration);
        }
        if (str2 != null && !str2.isEmpty()) {
            this.snackbar.setAction((CharSequence) str2, (View.OnClickListener) new View.OnClickListener() {
                public final void onClick(View view) {
                    MakeroidSnackbar.this.Click();
                }
            }).setActionTextColor(this.buttonTextColor);
        }
        this.snackbar.getView().setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MakeroidSnackbar.this.snackbar.dismiss();
            }
        });
        this.snackbar.addCallback(new Snackbar.Callback() {
            public final void onDismissed(Snackbar snackbar, int i) {
                MakeroidSnackbar.this.OnDismissed(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNDEFINED" : "CONSECUTIVE" : "MANUAL" : "TIMEOUT" : "ACTION" : "SWIPE");
            }

            public final void onShown(Snackbar snackbar) {
                MakeroidSnackbar.this.OnShown();
            }
        });
        this.snackbar.getView().setBackgroundColor(this.backgroundColor);
        this.snackbar.show();
    }

    @SimpleEvent(description = "Event to detect the snackbar was dismissed. Possible results can be: \"UNDEFINED\", \"ACTION\", \"CONSECUTIVE\", \"MANUAL\", \"SWIPE\" or \"TIMEOUT\". You can find more information at: https://developer.android.com/reference/android/support/design/widget/Snackbar.Callback.html ")
    public void OnDismissed(String str) {
        EventDispatcher.dispatchEvent(this, "OnDismissed", str);
    }

    @SimpleEvent(description = "Event to detect the snackbar is shown.")
    public void OnShown() {
        EventDispatcher.dispatchEvent(this, "OnShown", new Object[0]);
    }

    @SimpleEvent(description = "User clicked on the action button.")
    public void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the snackbar's background color.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        Snackbar snackbar2 = this.snackbar;
        if (snackbar2 != null) {
            snackbar2.getView().setBackgroundColor(i);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the snackbar's background color.")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        this.textColor = i;
        if (i == 0) {
            this.textColor = -1;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public int TextColor() {
        return this.textColor;
    }

    private String getColoredText(String str) {
        String format = String.format("#%06X", new Object[]{Integer.valueOf(this.textColor & 16777215)});
        return "<font color='" + format + "'>" + str + "</font>";
    }
}
