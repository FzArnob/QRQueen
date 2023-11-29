package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.TiramisuUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.IOException;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Component for displaying images.  The picture to display, and other aspects of the Image's appearance, can be specified in the Designer or in the Blocks Editor. You can also add gif images.", version = 6)
@UsesPermissions({"android.permission.INTERNET", "android.permission.READ_EXTERNAL_STORAGE"})
public final class Image extends AndroidViewComponent {
    private static final String LOG_TAG = "Image";
    private final AppCompatImageView appCompatImageView;
    private boolean clickable = false;
    private Context context;
    /* access modifiers changed from: private */
    public Form form;
    private int imageTintColor = 16777215;
    private String picturePath = "";
    private boolean repl = false;
    private double rotationAngle = 0.0d;
    private int scalingMode = 0;

    public Image(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        this.appCompatImageView = new AppCompatImageView(this.context) {
            public final boolean verifyDrawable(Drawable drawable) {
                super.verifyDrawable(drawable);
                return true;
            }
        };
        componentContainer.$add(this);
        if (this.form instanceof ReplForm) {
            this.repl = true;
        }
        Log.d(LOG_TAG, "Image component Created");
    }

    public final View getView() {
        return this.appCompatImageView;
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Click\".")
    public final void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect that a user has done a simple \"Long Click\".")
    public final void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public final void Clickable(boolean z) {
        this.clickable = z;
        if (z) {
            this.appCompatImageView.setClickable(true);
            if (Build.VERSION.SDK_INT >= 23) {
                this.appCompatImageView.setForeground(getSelectedItemDrawable());
            }
            this.appCompatImageView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    Image.this.Click();
                }
            });
            this.appCompatImageView.setOnLongClickListener(new View.OnLongClickListener() {
                public final boolean onLongClick(View view) {
                    Image.this.LongClick();
                    return true;
                }
            });
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.appCompatImageView.setForeground((Drawable) null);
        }
        this.appCompatImageView.setOnClickListener((View.OnClickListener) null);
        this.appCompatImageView.setOnLongClickListener((View.OnLongClickListener) null);
        this.appCompatImageView.setClickable(false);
    }

    @SimpleProperty
    public final boolean Clickable() {
        return this.clickable;
    }

    private Drawable getSelectedItemDrawable() {
        TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(new int[]{16843534});
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final String Picture() {
        return this.picturePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty
    public final void Picture(@Asset String str) {
        BitmapDrawable bitmapDrawable;
        final String str2 = str == null ? "" : str;
        if (!TiramisuUtil.requestImagePermissions(this.container.$form(), str, new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    Image.this.Picture(str2);
                } else {
                    Image.this.form.dispatchPermissionDeniedEvent((Component) Image.this, "Picture", str);
                }
            }
        })) {
            this.picturePath = str2;
            try {
                bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.picturePath);
            } catch (IOException unused) {
                Log.e(LOG_TAG, "Unable to load " + this.picturePath);
                bitmapDrawable = null;
            }
            try {
                ViewUtil.setImage(this.appCompatImageView, bitmapDrawable);
            } catch (Exception unused2) {
                this.appCompatImageView.setImageDrawable((Drawable) null);
            }
        }
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    public final void RotationAngle(double d) {
        if (this.rotationAngle != d) {
            this.appCompatImageView.setRotation((float) d);
            this.rotationAngle = d;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The angle at which the image picture appears rotated. This rotation does not appear on the designer screen, only on the device.")
    public final double RotationAngle() {
        return this.rotationAngle;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specifies whether the image should be resized to match the size of the ImageView.")
    public final void ScalePictureToFit(boolean z) {
        if (z) {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "This is a limited form of animation that can attach a small number of motion types to images.  The allowable motions are ScrollRightSlow, ScrollRight, ScrollRightFast, ScrollLeftSlow, ScrollLeft, ScrollLeftFast, and Stop")
    public final void Animation(String str) {
        AnimationUtil.ApplyAnimation(this.appCompatImageView, str);
    }

    @Deprecated
    @SimpleProperty(description = "This property determines how the picture scales according to the Height or Width of the Image. Scale proportionally (0) preserves the picture aspect ratio. Scale to fit (1) matches the Image area, even if the aspect ratio changes.")
    public final void Scaling(int i) {
        if (i == 0) {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else if (i == 1) {
            this.appCompatImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            throw new IllegalArgumentError("Illegal scaling mode: ".concat(String.valueOf(i)));
        }
        this.scalingMode = i;
    }

    @SimpleProperty
    public final int Scaling() {
        return this.scalingMode;
    }

    @SimpleProperty(description = "Change the image tint color. You can use too alpha values if you want with the 'make a list' block. Do not forget to use the 'make color' block together with the 'make a list' block.")
    public final void ImageTintColor(int i) {
        this.imageTintColor = i;
        ImageViewCompat.setImageTintMode(this.appCompatImageView, PorterDuff.Mode.SRC_ATOP);
        ImageViewCompat.setImageTintList(this.appCompatImageView, ColorStateList.valueOf(i));
    }

    @SimpleProperty(description = "Return the image tint color.")
    public final int ImageTintColor() {
        return this.imageTintColor;
    }

    @SimpleFunction(description = "Clear the image tint color.")
    public final void ClearImageTintColor() {
        ImageViewCompat.setImageTintList(this.appCompatImageView, (ColorStateList) null);
    }
}
