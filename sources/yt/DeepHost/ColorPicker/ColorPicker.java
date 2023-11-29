package yt.DeepHost.ColorPicker;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.core.internal.view.SupportMenu;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import yt.DeepHost.ColorPicker.libary.colorpicker.ColorPickerView;
import yt.DeepHost.ColorPicker.libary.colorpicker.OnColorChangedListener;
import yt.DeepHost.ColorPicker.libary.colorpicker.OnColorSelectedListener;
import yt.DeepHost.ColorPicker.libary.colorpicker.builder.ColorPickerClickListener;
import yt.DeepHost.ColorPicker.libary.colorpicker.builder.ColorPickerDialogBuilder;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "<p>Color Picker Extension<br><br> <a href = \"https://www.youtube.com/c/DeepHost\" target = \"_blank\">Youtube Channel Link</a> </a></p>", iconName = "https://res.cloudinary.com/dluhwmiwm/image/upload/v1545063005/ic.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_NETWORK_STATE")
public final class ColorPicker extends AndroidNonvisibleComponent implements Component {
    public static final int VERSION = 1;
    private boolean CIRCLE = true;
    private boolean DARK = false;
    private boolean FLOWER = false;
    private boolean LIGHT = true;
    private final Activity activity;
    private ColorPickerDialogBuilder color_picker;
    private ComponentContainer container;
    private Context context;
    private int default_color = SupportMenu.CATEGORY_MASK;
    private String negative_button = "Cancel";
    private String positive_button = "OK";
    private String title = "Choose Color";
    private String type = "CIRCLE";

    public ColorPicker(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
    }

    @DesignerProperty(defaultValue = "Choose Color", editorType = "string")
    @SimpleProperty
    public void Title(String str) {
        this.title = str;
    }

    @DesignerProperty(defaultValue = "OK", editorType = "string")
    @SimpleProperty
    public void Positive_Button(String str) {
        this.positive_button = str;
    }

    @DesignerProperty(defaultValue = "Cancel", editorType = "string")
    @SimpleProperty
    public void Negative_Button(String str) {
        this.negative_button = str;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Type_CIRCLE(boolean z) {
        this.CIRCLE = z;
        this.FLOWER = false;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Type_FLOWER(boolean z) {
        this.FLOWER = z;
        this.CIRCLE = false;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Theme_LIGHT(boolean z) {
        this.LIGHT = z;
        this.DARK = false;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void Theme_DARK(boolean z) {
        this.DARK = z;
        this.LIGHT = false;
    }

    @DesignerProperty(defaultValue = "&HFFFF0000", editorType = "color")
    @SimpleProperty
    public void Default_Color(int i) {
        this.default_color = i;
    }

    public int theme() {
        if (!this.LIGHT && this.DARK) {
            return 4;
        }
        return 5;
    }

    public ColorPickerView.WHEEL_TYPE wheelType() {
        if (this.CIRCLE) {
            return ColorPickerView.WHEEL_TYPE.CIRCLE;
        }
        if (this.FLOWER) {
            return ColorPickerView.WHEEL_TYPE.FLOWER;
        }
        return ColorPickerView.WHEEL_TYPE.CIRCLE;
    }

    @SimpleFunction
    public void ColorPicker() {
        ColorPickerDialogBuilder.with(this.context, theme()).setTitle(this.title).initialColor(this.default_color).wheelType(wheelType()).density(12).setOnColorChangedListener(new OnColorChangedListener() {
            public void onColorChanged(int i) {
                ColorPicker.this.onColorChanged(i);
            }
        }).setOnColorSelectedListener(new OnColorSelectedListener() {
            public void onColorSelected(int i) {
                ColorPicker.this.onColorSelected(i);
            }
        }).setPositiveButton((CharSequence) this.positive_button, (ColorPickerClickListener) new ColorPickerClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
                ColorPicker.this.PositiveButton(i);
            }
        }).setNegativeButton((CharSequence) this.negative_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ColorPicker.this.NegativeButton();
            }
        }).build().show();
    }

    @SimpleEvent
    public void onColorChanged(int i) {
        EventDispatcher.dispatchEvent(this, "onColorChanged", Integer.valueOf(i));
    }

    @SimpleEvent
    public void onColorSelected(int i) {
        EventDispatcher.dispatchEvent(this, "onColorSelected", Integer.valueOf(i));
    }

    @SimpleEvent
    public void PositiveButton(int i) {
        EventDispatcher.dispatchEvent(this, "PositiveButton", Integer.valueOf(i));
    }

    @SimpleEvent
    public void NegativeButton() {
        EventDispatcher.dispatchEvent(this, "NegativeButton", new Object[0]);
    }
}
