package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ElementsUtil;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.appinventor.components.runtime.util.KodularUnitUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "The Notifier component displays alert dialogs, messages, and temporary alerts, and creates Android log entries through the following methods: <ul><li> ShowMessageDialog: displays a message which the user must dismiss by pressing a button.</li><li> ShowChooseDialog: displays a message two buttons to let the user choose one of two responses, for example, yes or no, after which the AfterChoosing event is raised.</li><li> ShowTextDialog: lets the user enter text in response to the message, after which the AfterTextInput event is raised. <li> ShowPasswordDialog: lets the user enter password in response to the message, after which the AfterTextInput event is raised. <li> ShowAlert: displays a temporary  alert that goes away by itself after a short time.</li><li> ShowProgressDialog: displays an alert with a loading spinner that cannot be dismissed by the user. It can only be dismissed by using the DismissProgressDialog block.</li><li> CustomMessageDialog: New version to display dialogs with icon and in fullscreen mode.</li><li> CustomChooseDialog: New version to display choose dialogs with icon and in fullscreen mode.<li> LightTheme: To display the dialogs in a light or dark theme.</li><li> ShowLightbox: Displays a png or gif file from assets or from web url in a dialog.</li><li> ShowLinearProgress: Shows a progress dialog with a horizontal progress bar.</li><li> ShowSpinningProgress: Shows a spinning progress dialog.</li><li> UpdateProgress: Change the current value of the linear progress dialog.</li><li> ShowRadioListDialog: Shows a radio list dialog with a list of options.</li><li> ShowCheckboxListDialog: Shows a picker dialog with a list of options.</li><li> ShowListPicker: Shows a list picker dialog.</li><li> ShowTextInputDialog: Show a text input dialog.</li><li> ShowImageDialog: Show a image dialog. Animation types like \"*.gif\" are not supported.</li><li> ShowNumberPicker: Shows a number picker dialog that enables the user to select a number from a predefined range.</li><li> ShowWordPicker: Shows a word picker dialog that enables the user to select a number from a predefined word.</li><li> DismissProgressDialog: Dismisses the progress dialog displayed by ShowProgressDialog.</li><li> DismissSpinningProgress: Dismisses the progress dialog displayed by ShowSpinningProgress.</li><li> DismissLinearProgress: Dismisses the progress dialog displayed by ShowLinearProgress.</li><li> LogError: logs an error message to the Android log. </li><li> LogInfo: logs an info message to the Android log.</li><li> LogWarning: logs a warning message to the Android log.</li><li>The messages in the dialogs can be formatted using the following HTML tags:&lt;b&gt;, &lt;big&gt;, &lt;blockquote&gt;, &lt;br&gt;, &lt;cite&gt;, &lt;dfn&gt;, &lt;div&gt;, &lt;em&gt;, &lt;small&gt;, &lt;strong&gt;, &lt;sub&gt;, &lt;sup&gt;, &lt;tt&gt;. &lt;u&gt;</li><li>You can also use the font tag to specify color, for example, &lt;font color=\"blue\"&gt;.  Some of the available color names are aqua, black, blue, fuchsia, green, grey, lime, maroon, navy, olive, purple, red, silver, teal, white, and yellow</li></ul>", iconName = "images/notifier.png", nonVisible = true, version = 11)
@UsesLibraries({"glide.jar"})
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public final class Notifier extends AndroidNonvisibleComponent implements Component {
    private static final String LOG_TAG = "Notifier";
    private int DIALOG_DARK;
    private int DIALOG_DARK_FULLSCREEN;
    private int DIALOG_DARK_RADIO;
    private int DIALOG_LIGHT;
    private int DIALOG_LIGHT_FULLSCREEN;
    private int DIALOG_LIGHT_RADIO;
    /* access modifiers changed from: private */
    public final Activity activity;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private int checkTheme;
    private ComponentContainer container;
    /* access modifiers changed from: private */
    public int currentSelection = -1;
    private int currentTheme;
    private AlertDialog customDialog;
    private float dimAmount = 0.5f;
    private final Handler handler;
    private boolean isRepl;
    private boolean linkify = false;
    private int messageTypeface = 0;
    private String messagefontTypefacePath = "";
    private int msgTheme;
    private int notifierLength = 1;
    private ProgressDialog progress;
    private ProgressDialog progressDialog;
    private ProgressDialog progressl;
    private int radioTheme;
    /* access modifiers changed from: private */
    public int selectedNumber = 0;
    /* access modifiers changed from: private */
    public String selectedWord = "";
    private int textColor = -1;
    private int tglTheme;
    private boolean theme;
    private int titleColor;
    private int titleTypeface = 0;
    private String titlefontTypefacePath = "";
    private int txtTheme;
    private boolean useBackgroundColor = true;

    public Notifier(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        Activity $context = componentContainer.$context();
        this.activity = $context;
        this.handler = new Handler();
        if (componentContainer.$form() instanceof ReplForm) {
            this.isRepl = true;
        }
        this.DIALOG_LIGHT = KodularResourcesUtil.getStyle($context, "Theme.AppCompat.Light.Dialog.Alert");
        this.DIALOG_LIGHT_FULLSCREEN = KodularResourcesUtil.getStyle($context, "Theme.AppCompat.Light.NoActionBar");
        this.DIALOG_LIGHT_RADIO = KodularResourcesUtil.getStyle($context, "Theme.AppCompat.Light.DialogWhenLarge");
        this.DIALOG_DARK = KodularResourcesUtil.getStyle($context, "AppTheme.Dialog.Alert");
        this.DIALOG_DARK_FULLSCREEN = KodularResourcesUtil.getStyle($context, "Theme.AppCompat.NoActionBar");
        this.DIALOG_DARK_RADIO = KodularResourcesUtil.getStyle($context, "Theme.AppCompat.DialogWhenLarge");
        LightTheme(false);
        UseBackgroundColor(false);
        TextViewUtil.setContext($context);
    }

    @SimpleFunction(description = "Dismiss a previously displayed ProgressDialog box")
    public final void DismissProgressDialog() {
        dismissProgressDialogHelper(this.progressDialog);
    }

    @SimpleFunction(description = "Dismiss a previously displayed SpinningProgress box")
    public final void DismissSpinningProgress() {
        dismissProgressDialogHelper(this.progress);
    }

    @SimpleFunction(description = "Dismiss a previously displayed LinearProgress box")
    public final void DismissLinearProgress() {
        dismissProgressDialogHelper(this.progressl);
    }

    @SimpleFunction(description = "Show a Message Dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void CustomMessageDialog(String str, String str2, String str3, String str4, boolean z) {
        customMessage(this.activity, str, str2, str3, str4, z);
    }

    public final void customMessage(Activity activity2, String str, final String str2, String str3, String str4, boolean z) {
        int i = this.currentTheme;
        if (i == this.DIALOG_LIGHT) {
            if (z) {
                this.msgTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.msgTheme = i;
            }
        } else if (z) {
            this.msgTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.msgTheme = i;
        }
        AlertDialog create = new AlertDialog.Builder(activity2, this.msgTheme).create();
        if (!str2.isEmpty()) {
            create.setTitle(str2);
        }
        create.setCancelable(false);
        create.setIcon(iconHelper(str4));
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        linkifyIfNeeded(this.linkify, create, str);
        create.setButton(-3, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier.this.AfterMessageDialog(str2);
            }
        });
        create.show();
        setDialogHelper(create, z);
    }

    @SimpleEvent(description = "Event to detect that a user clicked on a button from the \"Show Custom Message Dialog\". Use the \"title\" response to know which dialog the user has clicked.")
    public final void AfterMessageDialog(String str) {
        EventDispatcher.dispatchEvent(this, "AfterMessageDialog", str);
    }

    @SimpleEvent(description = "Event to detect that a user have done his selection in the CustomChooseDialog.")
    public final void GotCustomChooseDialog(int i, String str) {
        EventDispatcher.dispatchEvent(this, "GotCustomChooseDialog", Integer.valueOf(i), str);
    }

    @SimpleFunction(description = "Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the GotCustomChooseDialog event.  The \"choice\" parameter to GotCustomChooseDialog will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color. Use the 'Show Custom Dialog' block to the show the created custom dialog. The added component must be visible on screen. After you used this block here it will be removed automatic from screen and will be only visible again after you have shown the custom dialog.")
    public final void CustomChooseDialog(int i, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2) {
        final int i2 = i;
        final String str7 = str3;
        final String str8 = str4;
        final String str9 = str5;
        customChoose(this.activity, str, str2, str7, str8, str9, str6, z, z2, new Runnable() {
            public final void run() {
                Notifier.this.GotCustomChooseDialog(i2, str7);
            }
        }, new Runnable() {
            public final void run() {
                Notifier.this.GotCustomChooseDialog(i2, str8);
            }
        }, new Runnable() {
            public final void run() {
                Notifier.this.GotCustomChooseDialog(i2, str9);
            }
        }, this.linkify);
    }

    public final void customChoose(Activity activity2, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, final Runnable runnable, final Runnable runnable2, final Runnable runnable3, boolean z3) {
        if (this.currentTheme == KodularResourcesUtil.getStyle(activity2, "Theme.AppCompat.Light.Dialog.Alert")) {
            if (z2) {
                this.msgTheme = KodularResourcesUtil.getStyle(activity2, "Theme.AppCompat.Light.NoActionBar");
            } else {
                this.msgTheme = this.currentTheme;
            }
        } else if (z2) {
            this.msgTheme = KodularResourcesUtil.getStyle(activity2, "Theme.AppCompat.NoActionBar");
        } else {
            this.msgTheme = this.currentTheme;
        }
        AlertDialog create = new AlertDialog.Builder(activity2, this.msgTheme).create();
        if (!str2.isEmpty()) {
            create.setTitle(str2);
        }
        create.setCancelable(false);
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        linkifyIfNeeded(z3, create, str);
        create.setButton(-1, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                runnable.run();
            }
        });
        create.setButton(-3, str4, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                runnable2.run();
            }
        });
        if (z) {
            create.setButton(-2, str5, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    runnable3.run();
                }
            });
        }
        create.setIcon(iconHelper(str6));
        create.show();
        setDialogHelper(create, z2);
    }

    @SimpleFunction(description = "Show a alert \"toast\" message.")
    public final void ShowAlert(final String str) {
        this.handler.post(new Runnable() {
            public final void run() {
                Notifier.this.toastNow(str);
            }
        });
    }

    @DesignerProperty(defaultValue = "1", editorType = "toast_length")
    @SimpleProperty(userVisible = false)
    public final void NotifierLength(int i) {
        this.notifierLength = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "specifies the length of time that the alert is shown -- either \"short\" or \"long\".")
    public final int NotifierLength() {
        return this.notifierLength;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the background color for alerts (not dialogs).")
    public final void BackgroundColor(int i) {
        this.backgroundColor = i;
    }

    @SimpleProperty
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the title text color for dialogs.")
    public final int TitleColor() {
        return this.titleColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void TitleColor(int i) {
        this.titleColor = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Specifies the text color for alerts or for dialogs message.")
    public final int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void TextColor(int i) {
        this.textColor = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int TitleFontTypeface() {
        return this.titleTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void TitleFontTypeface(int i) {
        this.titleTypeface = i;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void TitleFontTypefaceImport(String str) {
        this.titlefontTypefacePath = str;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int TextFontTypeface() {
        return this.messageTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void TextFontTypeface(int i) {
        this.messageTypeface = i;
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void TextFontTypefaceImport(String str) {
        this.messagefontTypefacePath = str;
    }

    @SimpleFunction(description = "Writes an error message to the Android system log. See the Google Android documentation for how to access the log.")
    public final void LogError(String str) {
        Log.e(LOG_TAG, str);
    }

    @SimpleFunction(description = "Writes a warning message to the Android log. See the Google Android documentation for how to access the log.")
    public final void LogWarning(String str) {
        Log.w(LOG_TAG, str);
    }

    @SimpleFunction(description = "Writes an information message to the Android log.")
    public final void LogInfo(String str) {
        Log.i(LOG_TAG, str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Gets the current theme")
    public final void LightTheme(boolean z) {
        if (z) {
            this.currentTheme = this.DIALOG_LIGHT;
        } else {
            this.currentTheme = this.DIALOG_DARK;
        }
        this.theme = z;
    }

    @SimpleProperty(description = "Sets the current theme")
    public final boolean LightTheme() {
        return this.theme;
    }

    @SimpleFunction(description = "Displays a lightbox. You can use images like \"*.png\" or \"*,gif\" from assets folder or from a web url.")
    public final void ShowLightbox(final int i, final String str) {
        this.handler.post(new Runnable() {
            public final void run() {
                Notifier.this.lightbox(i, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void lightbox(final int i, String str) {
        Dialog dialog = new Dialog(this.activity, 16973832);
        dialog.requestWindowFeature(1);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public final void onDismiss(DialogInterface dialogInterface) {
                Notifier.this.LightboxClosed(i);
            }
        });
        if (!str.isEmpty()) {
            ImageView imageView = new ImageView(this.activity);
            KodularUtil.LoadPicture((Context) this.activity, str, imageView, this.isRepl);
            dialog.addContentView(imageView, new RelativeLayout.LayoutParams(-1, -1));
        }
        dialog.show();
    }

    @SimpleEvent(description = "Event to detect that a user have closed the Lightbox.")
    public final void LightboxClosed(int i) {
        EventDispatcher.dispatchEvent(this, "LightboxClosed", Integer.valueOf(i));
    }

    @SimpleFunction(description = "Shows a progress dialog with a horizontal progress bar. Can be dismissed by user if 'cancelable' is set to true. If indeterminate is true, maxValue and the 'UpdateProgress' method will have no effect. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowLinearProgress(String str, String str2, boolean z, boolean z2, int i, String str3) {
        final String str4 = str;
        final String str5 = str2;
        final boolean z3 = z;
        final boolean z4 = z2;
        final int i2 = i;
        final String str6 = str3;
        this.handler.post(new Runnable() {
            public final void run() {
                Notifier.this.linearProgress(str4, str5, z3, z4, i2, str6);
            }
        });
    }

    /* access modifiers changed from: private */
    public void linearProgress(String str, String str2, boolean z, boolean z2, int i, String str3) {
        if (this.progress != null) {
            DismissSpinningProgress();
        }
        if (this.progressl != null) {
            DismissLinearProgress();
        }
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        ProgressDialog progressDialog2 = new ProgressDialog(this.activity, this.currentTheme);
        this.progressl = progressDialog2;
        progressDialog2.setProgressStyle(1);
        this.progressl.setIndeterminate(z2);
        this.progressl.setCancelable(z);
        if (!str.isEmpty()) {
            this.progressl.setMessage(TextViewUtil.fromHtml(str));
        }
        if (!str2.isEmpty()) {
            this.progressl.setTitle(TextViewUtil.fromHtml(str2));
        }
        this.progressl.setProgress(0);
        this.progressl.setMax(i);
        this.progressl.setProgress(0);
        if (z2) {
            this.progressl.setProgressNumberFormat((String) null);
            this.progressl.setProgressPercentFormat((NumberFormat) null);
        }
        this.progressl.setIcon(iconHelper(str3));
        this.progressl.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public final void onDismiss(DialogInterface dialogInterface) {
                Notifier.this.LinearProgressDismissed();
            }
        });
        this.progressl.show();
        setDialogHelper(this.progressl, false);
    }

    @SimpleEvent(description = "Event to detect that the linear progress dialog was dismissed.")
    public final void LinearProgressDismissed() {
        EventDispatcher.dispatchEvent(this, "LinearProgressDismissed", new Object[0]);
    }

    @SimpleFunction(description = "Change the current value of the linear progress dialog. Has no effect if \"indeterminate\" is set to true.")
    public final void UpdateProgress(int i) {
        ProgressDialog progressDialog2 = this.progressl;
        if (progressDialog2 != null) {
            progressDialog2.setProgress(i);
        }
    }

    @SimpleFunction(description = "Shows a spinning progress dialog which can be dismissed by the user if 'cancelable' is set to true. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowSpinningProgress(String str, String str2, boolean z, String str3) {
        final String str4 = str;
        final String str5 = str2;
        final boolean z2 = z;
        final String str6 = str3;
        this.handler.post(new Runnable() {
            public final void run() {
                Notifier.this.spinningProgress(str4, str5, z2, str6);
            }
        });
    }

    /* access modifiers changed from: private */
    public void spinningProgress(String str, String str2, boolean z, String str3) {
        if (this.progress != null) {
            DismissSpinningProgress();
        }
        if (this.progressl != null) {
            DismissLinearProgress();
        }
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        ProgressDialog progressDialog2 = new ProgressDialog(this.activity, this.currentTheme);
        this.progress = progressDialog2;
        progressDialog2.setProgressStyle(0);
        this.progress.setIndeterminate(true);
        this.progress.setCancelable(z);
        this.progress.setIcon(iconHelper(str3));
        if (this.currentTheme == this.DIALOG_LIGHT) {
            if (!str.isEmpty()) {
                ProgressDialog progressDialog3 = this.progress;
                StringBuilder sb = new StringBuilder();
                sb.append(TextViewUtil.fromHtml(str));
                progressDialog3.setMessage(sb.toString());
            }
        } else if (!str.isEmpty()) {
            ProgressDialog progressDialog4 = this.progress;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(TextViewUtil.fromHtml(str));
            progressDialog4.setMessage(sb2.toString());
        }
        if (str2 != null && !str2.isEmpty()) {
            this.progress.setTitle(TextViewUtil.fromHtml(str2));
        }
        this.progress.show();
        setDialogHelper(this.progress, false);
    }

    @SimpleFunction(description = "Shows a radio list dialog with a list of options of which only one can be chosen. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowRadioListDialog(int i, String str, YailList yailList, String str2, boolean z, String str3, int i2, String str4, boolean z2) {
        final int i3 = i;
        final String str5 = str;
        final YailList yailList2 = yailList;
        final String str6 = str2;
        final boolean z3 = z;
        final String str7 = str3;
        final int i4 = i2;
        final String str8 = str4;
        final boolean z4 = z2;
        this.handler.post(new Runnable() {
            public final void run() {
                Dialog access$400 = Notifier.this.radioListDialog(i3, str5, yailList2, str6, z3, str7, i4, str8, z4);
                access$400.show();
                Notifier.this.setDialogHelper(access$400, z4);
            }
        });
    }

    /* access modifiers changed from: private */
    public Dialog radioListDialog(final int i, String str, YailList yailList, String str2, boolean z, String str3, int i2, String str4, boolean z2) {
        int i3 = this.currentTheme;
        if (i3 == this.DIALOG_LIGHT) {
            if (z2) {
                this.radioTheme = this.DIALOG_LIGHT_RADIO;
            } else {
                this.radioTheme = i3;
            }
        } else if (z2) {
            this.radioTheme = this.DIALOG_DARK_RADIO;
        } else {
            this.radioTheme = i3;
        }
        int i4 = i2 - 1;
        this.currentSelection = i4 + 1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity, this.radioTheme);
        String[] stringArray = yailList.toStringArray();
        builder.setCancelable(z);
        builder.setTitle(TextViewUtil.fromHtml(str)).setSingleChoiceItems(stringArray, i4, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                int unused = Notifier.this.currentSelection = i + 1;
            }
        });
        builder.setPositiveButton(str2, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier notifier = Notifier.this;
                notifier.RadioSelection(i, notifier.currentSelection);
            }
        });
        if (z) {
            builder.setNegativeButton(TextViewUtil.fromHtml(str3), new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Notifier.this.RadioSelection(i, -1);
                }
            });
        }
        builder.setIcon(iconHelper(str4));
        return builder.create();
    }

    @SimpleEvent(description = "Event invoked when user has selected an option from the radio button picker. Outputs the index of the selected item. Returns -1 if cancel was pressed.")
    public final void RadioSelection(int i, int i2) {
        EventDispatcher.dispatchEvent(this, "RadioSelection", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @SimpleFunction(description = "Shows a picker dialog with a list of options of whichmore than one can be chosen. Invokes the 'AfterMultiSelection' event. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowCheckboxListDialog(final int i, String str, YailList yailList, String str2, String str3, boolean z, String str4, boolean z2) {
        int i2 = this.currentTheme;
        if (i2 == this.DIALOG_LIGHT) {
            if (z2) {
                this.checkTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.checkTheme = i2;
            }
        } else if (z2) {
            this.checkTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.checkTheme = i2;
        }
        String[] stringArray = yailList.toStringArray();
        final ArrayList arrayList = new ArrayList();
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(this.activity, this.checkTheme).setTitle(TextViewUtil.fromHtml(str)).setMultiChoiceItems(stringArray, (boolean[]) null, new DialogInterface.OnMultiChoiceClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i, boolean z) {
                if (z) {
                    arrayList.add(Integer.valueOf(i + 1));
                    return;
                }
                int i2 = i + 1;
                if (arrayList.contains(Integer.valueOf(i2))) {
                    arrayList.remove(Integer.valueOf(i2));
                }
            }
        }).setPositiveButton(str2, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier.this.CheckboxSelection(i, YailList.makeList((List) arrayList));
            }
        });
        if (z) {
            positiveButton.setNegativeButton(str3, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Notifier.this.CheckboxSelection(i, ElementsUtil.elementsFromString("-1"));
                }
            });
        }
        AlertDialog create = positiveButton.create();
        positiveButton.setCancelable(z);
        positiveButton.setIcon(iconHelper(str4));
        positiveButton.show();
        setDialogHelper(create, z2);
    }

    @SimpleEvent(description = "Invoked after user has finished selecting items from the Checkbox picker. Returns a list of indices of the selected items in the order of selection. Returns a list having -1 if cancel was pressed.")
    public final void CheckboxSelection(int i, YailList yailList) {
        EventDispatcher.dispatchEvent(this, "CheckboxSelection", Integer.valueOf(i), yailList);
    }

    @SimpleFunction(description = "Shows a list picker dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowListPicker(final int i, String str, YailList yailList, String str2, boolean z) {
        int i2 = this.currentTheme;
        if (i2 == this.DIALOG_LIGHT) {
            if (z) {
                this.tglTheme = this.DIALOG_LIGHT_FULLSCREEN;
            } else {
                this.tglTheme = i2;
            }
        } else if (z) {
            this.tglTheme = this.DIALOG_DARK_FULLSCREEN;
        } else {
            this.tglTheme = i2;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity, this.tglTheme);
        if (!str.isEmpty()) {
            builder.setTitle(TextViewUtil.fromHtml(str));
        }
        final String[] stringArray = yailList.toStringArray();
        builder.setCancelable(true);
        builder.setItems(stringArray, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier.this.ListPickerSelection(i, stringArray[i].toString());
            }
        });
        builder.setIcon(iconHelper(str2));
        AlertDialog create = builder.create();
        create.show();
        setDialogHelper(create, z);
    }

    @SimpleEvent(description = "Event to get the picked list selection from the List Picker.")
    public final void ListPickerSelection(int i, String str) {
        EventDispatcher.dispatchEvent(this, "ListPickerSelection", Integer.valueOf(i), str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c1  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Show a text input dialog. Possible input types are: \"1= Normal text\", \"2= Password text\", \"3= Person name\", \"4= Email adress\", \"5|6= Number\", \"7= Password number\" or \"8= datetime\". You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void ShowTextInputDialog(final int r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, boolean r18, boolean r19, int r20, java.lang.String r21, java.lang.String r22, int r23, int r24) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r15
            r3 = r17
            r4 = r18
            r5 = r20
            r6 = r22
            int r7 = r0.currentTheme
            int r8 = r0.DIALOG_LIGHT
            if (r7 != r8) goto L_0x001b
            if (r4 == 0) goto L_0x0018
            int r7 = r0.DIALOG_LIGHT_FULLSCREEN
            r0.txtTheme = r7
            goto L_0x0024
        L_0x0018:
            r0.txtTheme = r7
            goto L_0x0024
        L_0x001b:
            if (r4 == 0) goto L_0x0022
            int r7 = r0.DIALOG_DARK_FULLSCREEN
            r0.txtTheme = r7
            goto L_0x0024
        L_0x0022:
            r0.txtTheme = r7
        L_0x0024:
            android.app.AlertDialog$Builder r7 = new android.app.AlertDialog$Builder
            android.app.Activity r8 = r0.activity
            int r9 = r0.txtTheme
            r7.<init>(r8, r9)
            android.app.AlertDialog r7 = r7.create()
            boolean r8 = r14.isEmpty()
            if (r8 != 0) goto L_0x003e
            android.text.Spanned r8 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r14)
            r7.setTitle(r8)
        L_0x003e:
            android.widget.EditText r8 = new android.widget.EditText
            android.app.Activity r9 = r0.activity
            r8.<init>(r9)
            r9 = 1
            if (r5 == r9) goto L_0x0081
            r10 = 2
            if (r5 != r10) goto L_0x0051
            r5 = 129(0x81, float:1.81E-43)
            r8.setInputType(r5)
            goto L_0x0084
        L_0x0051:
            r10 = 3
            if (r5 != r10) goto L_0x005a
            r5 = 97
            r8.setInputType(r5)
            goto L_0x0084
        L_0x005a:
            r10 = 4
            if (r5 != r10) goto L_0x0063
            r5 = 33
            r8.setInputType(r5)
            goto L_0x0084
        L_0x0063:
            r11 = 5
            if (r5 == r11) goto L_0x007b
            r11 = 6
            if (r5 != r11) goto L_0x006a
            goto L_0x007b
        L_0x006a:
            r11 = 7
            if (r5 != r11) goto L_0x0073
            r5 = 12306(0x3012, float:1.7244E-41)
            r8.setInputType(r5)
            goto L_0x0084
        L_0x0073:
            r11 = 8
            if (r5 != r11) goto L_0x0081
            r8.setInputType(r10)
            goto L_0x0084
        L_0x007b:
            r5 = 12290(0x3002, float:1.7222E-41)
            r8.setInputType(r5)
            goto L_0x0084
        L_0x0081:
            r8.setInputType(r9)
        L_0x0084:
            if (r2 == 0) goto L_0x008f
            boolean r5 = r15.isEmpty()
            if (r5 != 0) goto L_0x008f
            r8.setText(r15)
        L_0x008f:
            if (r3 == 0) goto L_0x009a
            boolean r2 = r17.isEmpty()
            if (r2 != 0) goto L_0x009a
            r8.setHint(r3)
        L_0x009a:
            r2 = r23
            r8.setTextColor(r2)
            r2 = r24
            r8.setHintTextColor(r2)
            r7.setView(r8)
            r2 = -1
            com.google.appinventor.components.runtime.Notifier$15 r3 = new com.google.appinventor.components.runtime.Notifier$15
            r3.<init>(r8, r13)
            r5 = r21
            r7.setButton(r2, r5, r3)
            if (r19 == 0) goto L_0x00c1
            r2 = -2
            com.google.appinventor.components.runtime.Notifier$16 r3 = new com.google.appinventor.components.runtime.Notifier$16
            r3.<init>(r8, r13, r6)
            r7.setButton(r2, r6, r3)
            r7.setCancelable(r9)
            goto L_0x00c5
        L_0x00c1:
            r1 = 0
            r7.setCancelable(r1)
        L_0x00c5:
            r1 = r16
            android.graphics.drawable.Drawable r1 = r12.iconHelper(r1)
            r7.setIcon(r1)
            r7.show()
            r12.setDialogHelper(r7, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Notifier.ShowTextInputDialog(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, int, java.lang.String, java.lang.String, int, int):void");
    }

    @SimpleEvent(description = "Event to detect that a user have done his text input in the \"Show Text Input Dialog\".")
    public final void GotTextInputFromDialog(int i, String str) {
        EventDispatcher.dispatchEvent(this, "GotTextInputFromDialog", Integer.valueOf(i), str);
    }

    @SimpleFunction(description = "Show a image dialog. Animation types like \"*.gif\" are not supported. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowImageDialog(final int i, String str, String str2, String str3) {
        this.checkTheme = this.currentTheme;
        AlertDialog create = new AlertDialog.Builder(this.activity, this.checkTheme).create();
        if (!str.isEmpty()) {
            create.setTitle(str);
        } else {
            create.requestWindowFeature(1);
        }
        create.setCancelable(false);
        create.setButton(-1, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier.this.ImageDialogClosed(i);
            }
        });
        if (!str2.isEmpty()) {
            ImageView imageView = new ImageView(this.activity);
            KodularUtil.LoadPicture((Context) this.activity, str2, imageView, this.isRepl);
            create.setView(imageView, 0, 0, 0, 0);
        }
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has watched the image dialog.")
    public final void ImageDialogClosed(int i) {
        EventDispatcher.dispatchEvent(this, "ImageDialogClosed", Integer.valueOf(i));
    }

    @SimpleFunction(description = "Shows a number picker dialog that enables the user to select a number from a predefined range. You can use the \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowNumberPicker(final int i, String str, String str2, String str3, int i2, int i3, int i4) {
        this.selectedNumber = i4;
        AlertDialog create = new AlertDialog.Builder(this.activity).create();
        if (!str.isEmpty()) {
            create.setTitle(str);
        }
        create.setCancelable(false);
        if (str2.isEmpty()) {
            str2 = "OK";
        }
        create.setButton(-1, str2, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier notifier = Notifier.this;
                notifier.NumberPickerSelection(i, notifier.selectedNumber);
            }
        });
        if (str3.isEmpty()) {
            str3 = "Close";
        }
        create.setButton(-2, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        NumberPicker numberPicker = new NumberPicker(this.activity);
        numberPicker.setMinValue(i2);
        numberPicker.setMaxValue(i3);
        numberPicker.setValue(i4);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int unused = Notifier.this.selectedNumber = i2;
            }
        });
        create.setView(numberPicker, 30, 30, 30, 30);
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has selected a number from the number picker dialog.")
    public final void NumberPickerSelection(int i, int i2) {
        EventDispatcher.dispatchEvent(this, "NumberPickerSelection", Integer.valueOf(i), Integer.valueOf(i2));
    }

    @SimpleFunction(description = "Shows a word picker dialog that enables the user to select a number from a predefined word. You can use the \"Use Background Color\" property to use the background color property as background dialog color.")
    public final void ShowWordPicker(final int i, String str, String str2, String str3, YailList yailList) {
        final String[] stringArray = yailList.toStringArray();
        AlertDialog create = new AlertDialog.Builder(this.activity).create();
        if (!str.isEmpty()) {
            create.setTitle(str);
        }
        create.setCancelable(false);
        if (str2.isEmpty()) {
            str2 = "OK";
        }
        create.setButton(-1, str2, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier notifier = Notifier.this;
                notifier.WordPickerSelection(i, notifier.selectedWord);
            }
        });
        if (str3.isEmpty()) {
            str3 = "Close";
        }
        create.setButton(-2, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        NumberPicker numberPicker = new NumberPicker(this.activity);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(stringArray.length - 1);
        numberPicker.setDisplayedValues(stringArray);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
                String unused = Notifier.this.selectedWord = stringArray[i2];
            }
        });
        create.setView(numberPicker, 30, 30, 30, 30);
        create.show();
        setDialogHelper(create, false);
    }

    @SimpleEvent(description = "Event to detect that the user has selected a word from the word picker dialog.")
    public final void WordPickerSelection(int i, String str) {
        EventDispatcher.dispatchEvent(this, "WordPickerSelection", Integer.valueOf(i), str);
    }

    @SimpleFunction(description = "Show the custom dialog. Dont forget that you have first to create the custom dialog.")
    public final void ShowCustomDialog() {
        AlertDialog alertDialog = this.customDialog;
        if (alertDialog != null) {
            alertDialog.show();
            setDialogHelper(this.customDialog, false);
        }
    }

    @SimpleFunction(description = "Use this block to dismiss the created custom dialog.the custom dialog.")
    public final void DismissCustomDialog() {
        AlertDialog alertDialog = this.customDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    @SimpleFunction(description = "Show whatever you want in a dialog. You can use as example arrangements, or images or whatever you want. Your chosen layout will be then removed from the screen and only visible in custom dialog. You can use the \"Light Theme\" property to have a light or dark background. Or you enable \"Use Background Color\" property to use the background color property as background dialog color. Please make sure the layout you want to use is visible.")
    public final void CreateCustomDialog(AndroidViewComponent androidViewComponent, String str, final String str2, final String str3, boolean z) {
        this.checkTheme = this.currentTheme;
        this.customDialog = new AlertDialog.Builder(this.activity, this.checkTheme).create();
        if (!str.isEmpty()) {
            this.customDialog.setTitle(str);
        }
        this.customDialog.setCancelable(z);
        if (!str2.isEmpty()) {
            this.customDialog.setButton(-1, str2, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Notifier.this.CustomDialogSelection(str2);
                }
            });
        }
        if (!str3.isEmpty()) {
            this.customDialog.setButton(-2, str3, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Notifier.this.CustomDialogSelection(str3);
                }
            });
        }
        try {
            ViewGroup viewGroup = (ViewGroup) androidViewComponent.getView();
            ((ViewGroup) viewGroup.getParent()).removeView(viewGroup);
            this.customDialog.setView(viewGroup, 0, 0, 0, 0);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
    }

    @SimpleEvent(description = "Event to detect that the user has pressed a button from the custom dialog. It returns then the text of the button that was pressed.")
    public final void CustomDialogSelection(String str) {
        EventDispatcher.dispatchEvent(this, "CustomDialogSelection", str);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled the dialog will use the color from the \"Background Color\"-option. Else it will use the theme (light or dark).")
    public final void UseBackgroundColor(boolean z) {
        this.useBackgroundColor = z;
    }

    @SimpleProperty
    public final boolean UseBackgroundColor() {
        return this.useBackgroundColor;
    }

    @DesignerProperty(defaultValue = "0.5", editorType = "non_negative_float", propertyType = "advanced")
    @SimpleProperty(description = "Set the amount of dim behind the dialog window. Use '0.0' for no dim and '1.0' for full dim.")
    public final void DimAmount(float f) {
        this.dimAmount = f;
    }

    @SimpleProperty
    public final float DimAmount() {
        return this.dimAmount;
    }

    public static void aboutThisApp(Activity activity2, String str, String str2, String str3, int i, boolean z) {
        AlertDialog create = new AlertDialog.Builder(activity2, KodularResourcesUtil.getStyle(activity2, z ? "Theme.AppCompat.Light.Dialog.Alert" : "AppTheme.Dialog.Alert")).create();
        if (!str2.isEmpty()) {
            create.setTitle(str2);
        }
        create.setCancelable(false);
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        create.setButton(-3, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        create.show();
        try {
            ((TextView) create.findViewById(16908299)).setMovementMethod(LinkMovementMethod.getInstance());
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
        if (create.getWindow() != null) {
            create.getWindow().getDecorView().getBackground().setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
        }
    }

    @SimpleFunction(description = "Show a Message Dialog.")
    public final void ShowMessageDialog(String str, String str2, String str3) {
        oneButtonAlert(this.activity, str, str2, str3, this.linkify);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3) {
        oneButtonAlert(activity2, str, str2, str3, false);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, boolean z) {
        oneButtonAlert(activity2, str, str2, str3, (Runnable) null, z);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, Runnable runnable) {
        oneButtonAlert(activity2, str, str2, str3, runnable, false);
    }

    public static void oneButtonAlert(Activity activity2, String str, String str2, String str3, final Runnable runnable, boolean z) {
        Log.i(LOG_TAG, "One button alert ".concat(String.valueOf(str)));
        AlertDialog create = new AlertDialog.Builder(activity2).create();
        if (!str2.isEmpty()) {
            create.setTitle(str2);
        }
        create.setCancelable(false);
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        create.setButton(-3, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Runnable runnable = runnable;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        linkifyIfNeeded(z, create, str);
        create.show();
    }

    @SimpleFunction(description = "Shows a dialog box with two buttons, from which the user can choose.  If cancelable is true there will be an additional CANCEL button. Pressing a button will raise the AfterChoosing event.  The \"choice\" parameter to AfterChoosing will be the text on the button that was pressed, or \"Cancel\" if the  CANCEL button was pressed.")
    public final void ShowChooseDialog(String str, String str2, final String str3, String str4, boolean z) {
        final String str5 = str4;
        twoButtonDialog(this.activity, str, str2, str3, str5, z, new Runnable() {
            public final void run() {
                Notifier.this.AfterChoosing(str3);
            }
        }, new Runnable() {
            public final void run() {
                Notifier.this.AfterChoosing(str5);
            }
        }, new Runnable() {
            public final void run() {
                Notifier notifier = Notifier.this;
                notifier.AfterChoosing(notifier.activity.getString(17039360));
            }
        }, this.linkify);
    }

    public final void twoButtonDialog(Activity activity2, String str, String str2, String str3, String str4, boolean z, final Runnable runnable, final Runnable runnable2, final Runnable runnable3, boolean z2) {
        Log.i(LOG_TAG, "ShowChooseDialog: ".concat(String.valueOf(str)));
        AlertDialog create = new AlertDialog.Builder(activity2).create();
        if (!str2.isEmpty()) {
            create.setTitle(str2);
        }
        create.setCancelable(false);
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        linkifyIfNeeded(z2, create, str);
        create.setButton(-1, str3, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                runnable.run();
            }
        });
        create.setButton(-3, str4, new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                runnable2.run();
            }
        });
        if (z) {
            create.setButton(-2, activity2.getString(17039360), new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    runnable3.run();
                }
            });
        }
        create.show();
    }

    @SimpleFunction(description = "Shows a dialog box where the user can enter text, after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering text will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the text that was entered, or \"Cancel\" if the CANCEL button was pressed.")
    public final void ShowTextDialog(String str, String str2, boolean z) {
        textInputDialog(str, str2, z, false);
    }

    @SimpleFunction(description = "Shows a dialog box where the user can enter password (input is masked), after which the AfterTextInput event will be raised.  If cancelable is true there will be an additional CANCEL button. Entering password will raise the AfterTextInput event.  The \"response\" parameter to AfterTextInput will be the entered password, or \"Cancel\" if CANCEL button was pressed.")
    public final void ShowPasswordDialog(String str, String str2, boolean z) {
        textInputDialog(str, str2, z, true);
    }

    private void textInputDialog(String str, String str2, boolean z, boolean z2) {
        AlertDialog create = new AlertDialog.Builder(this.activity).create();
        if (!str2.isEmpty()) {
            create.setTitle(TextViewUtil.fromHtml(str2));
        }
        if (!str.isEmpty()) {
            create.setMessage(TextViewUtil.fromHtml(str));
        }
        linkifyIfNeeded(this.linkify, create, str);
        final EditText editText = new EditText(this.activity);
        if (z2) {
            editText.setInputType(129);
        }
        create.setView(editText);
        create.setCancelable(false);
        create.setButton(-1, "OK", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                Notifier.this.hideKeyboardHelper(editText);
                Notifier.this.AfterTextInput(editText.getText().toString());
            }
        });
        if (z) {
            final String string = this.activity.getString(17039360);
            create.setButton(-2, string, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Notifier.this.hideKeyboardHelper(editText);
                    Notifier.this.AfterTextInput(string);
                }
            });
        }
        create.show();
    }

    @SimpleEvent(description = "Event to detect that a user have done his text input in the \"Show Text Dialog\".")
    public final void AfterTextInput(String str) {
        EventDispatcher.dispatchEvent(this, "AfterTextInput", str);
    }

    @SimpleFunction(description = "Shows a dialog box with an optional title and message (use empty strings if they are not wanted). This dialog box contains a spinning artifact to indicate that the program is working. It cannot be canceled by the user but must be dismissed by the App Inventor Program by using the DismissProgressDialog block.")
    public final void ShowProgressDialog(String str, String str2) {
        progressDialogHelper(str, str2);
    }

    @SimpleEvent(description = "Event to detect that a user have done his selection.")
    public final void AfterChoosing(String str) {
        EventDispatcher.dispatchEvent(this, "AfterChoosing", str);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true will attempt to make text clickable where possible; e.g. hyperlinks, phone numbers. Can not be used for Progress Dialogs. ")
    public final void Linkify(boolean z) {
        this.linkify = z;
    }

    @SimpleProperty(description = "Returns status of Linkify property")
    public final boolean Linkify() {
        return this.linkify;
    }

    private static void linkifyIfNeeded(boolean z, AlertDialog alertDialog, String str) {
        if (z) {
            TextView textView = new TextView(alertDialog.getContext());
            int DpToPixels = KodularUnitUtil.DpToPixels(alertDialog.getContext(), 15);
            textView.setPadding(DpToPixels, DpToPixels, DpToPixels, DpToPixels);
            textView.setText(TextViewUtil.linkifyMessage(str));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            alertDialog.setMessage((CharSequence) null);
            alertDialog.setView(textView);
        }
    }

    /* access modifiers changed from: private */
    public void toastNow(String str) {
        String str2 = "<font color='" + String.format("#%06X", new Object[]{Integer.valueOf(this.textColor & 16777215)}) + "'>" + str + "</font>";
        int round = Math.round(this.activity.getResources().getDisplayMetrics().density * 50.0f);
        Toast makeText = Toast.makeText(this.activity, TextViewUtil.fromHtml(str2), this.notifierLength);
        makeText.setGravity(81, 0, round);
        try {
            View view = makeText.getView();
            view.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
            makeText.setView(view);
        } catch (Exception e) {
            Log.e(LOG_TAG, String.valueOf(e));
        }
        makeText.show();
    }

    private void dismissProgressDialogHelper(ProgressDialog progressDialog2) {
        if (progressDialog2 != null) {
            progressDialog2.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void setDialogHelper(Dialog dialog, boolean z) {
        if (this.useBackgroundColor) {
            try {
                if (dialog.getWindow() != null) {
                    dialog.getWindow().getDecorView().getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        if (dialog.getWindow() != null) {
            dialog.getWindow().setDimAmount(this.dimAmount);
        }
        try {
            TextView textView = (TextView) dialog.findViewById(16908299);
            if (textView != null) {
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        } catch (Exception e2) {
            Log.e(LOG_TAG, String.valueOf(e2));
        }
        try {
            TextView textView2 = (TextView) dialog.findViewById(16908299);
            if (textView2 != null) {
                messageFontHelper(textView2);
                textView2.setTextColor(this.textColor);
            }
        } catch (Exception e3) {
            Log.e(LOG_TAG, String.valueOf(e3));
        }
        try {
            TextView textView3 = (TextView) dialog.findViewById(this.activity.getResources().getIdentifier("alertTitle", CommonProperties.ID, "android"));
            if (textView3 != null) {
                titleFontHelper(textView3);
                textView3.setTextColor(this.titleColor);
            }
        } catch (Exception e4) {
            Log.e(LOG_TAG, String.valueOf(e4));
        }
    }

    private void titleFontHelper(TextView textView) {
        if (!this.titlefontTypefacePath.isEmpty()) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), textView, this.titlefontTypefacePath, false, false);
        } else {
            TextViewUtil.setFontTypeface(textView, this.titleTypeface, false, false);
        }
    }

    private void messageFontHelper(TextView textView) {
        if (!this.messagefontTypefacePath.isEmpty()) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), textView, this.messagefontTypefacePath, false, false);
        } else {
            TextViewUtil.setFontTypeface(textView, this.messageTypeface, false, false);
        }
    }

    private void progressDialogHelper(String str, String str2) {
        if (this.progressDialog != null) {
            DismissProgressDialog();
        }
        ProgressDialog show = ProgressDialog.show(this.activity, str2, str);
        this.progressDialog = show;
        show.setCancelable(false);
    }

    /* access modifiers changed from: private */
    public void hideKeyboardHelper(View view) {
        InputMethodManager inputMethodManager;
        if (view != null && (inputMethodManager = (InputMethodManager) this.activity.getSystemService("input_method")) != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private Drawable iconHelper(String str) {
        if (str.isEmpty()) {
            return null;
        }
        try {
            return new BitmapDrawable(this.activity.getResources(), MediaUtil.getBitmapDrawable(this.container.$form(), str).getBitmap());
        } catch (Exception unused) {
            return null;
        }
    }
}
