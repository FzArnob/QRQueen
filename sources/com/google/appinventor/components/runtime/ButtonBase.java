package com.google.appinventor.components.runtime;

import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.runtime.util.KodularHelper;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.ViewUtil;

@SimpleObject
@UsesLibraries({"spinkit.jar"})
@UsesPermissions({"android.permission.INTERNET"})
public abstract class ButtonBase extends AndroidViewComponent implements View.OnClickListener, View.OnFocusChangeListener, View.OnLongClickListener, View.OnTouchListener {
    private static int F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = 0;
    private static int LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = 0;
    private static final float[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = {10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};
    private AlphaAnimation B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Button f51B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_GRAY;
    private boolean PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = true;
    private int backgroundColor = Component.COLOR_DARK_GRAY;
    private Drawable backgroundImageDrawable;
    private Context context;
    private int fontTypeface = 0;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ColorStateList f52hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Drawable f53hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private AlphaAnimation f54hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Button f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String imagePath = "";
    private int l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = 0;
    private int mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = 1;
    private boolean moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    private boolean oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = false;
    private double rotationAngle = 0.0d;
    private int textColor = -1;
    private boolean vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = false;
    private boolean yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = true;

    public abstract void click();

    public boolean longClick() {
        return false;
    }

    public ButtonBase(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        Button button = new Button(this.context);
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = button;
        button.setAllCaps(false);
        this.f53hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = button.getBackground();
        this.f52hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = button.getTextColors();
        F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = button.getMinWidth();
        LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = button.getMinHeight();
        this.f51B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = button;
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.9f);
        this.f54hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = alphaAnimation;
        alphaAnimation.setDuration(350);
        this.f54hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFillAfter(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.9f, 1.0f);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = alphaAnimation2;
        alphaAnimation2.setDuration(350);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setFillAfter(true);
        componentContainer.$add(this);
        button.setOnClickListener(this);
        button.setOnFocusChangeListener(this);
        button.setOnLongClickListener(this);
        button.setOnTouchListener(this);
        TextAlignment(1);
        BackgroundColor(Component.COLOR_DARK_GRAY);
        Image("");
        Enabled(true);
        TextViewUtil.setContext(this.context);
        TextViewUtil.setFontTypeface(button, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        FontSize(14.0f);
        Text("");
        TextColor(-1);
        Shape(0);
        TouchColor(Component.COLOR_LIGHT_GRAY);
        RotationAngle(0.0d);
        BorderShadow(true);
        FontBold(false);
        FontItalic(false);
        FontTypeface(0);
        FontTypefaceImport("");
        Width(-1);
        Height(-1);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (ShowFeedback() && this.backgroundImageDrawable == null) {
            KodularHelper.prepareRippleDrawable(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, motionEvent, this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
        }
        if (motionEvent.getAction() == 0) {
            if (ShowFeedback() && this.backgroundImageDrawable != null) {
                view.startAnimation(this.f54hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                view.invalidate();
            }
            try {
                TouchDown();
                return false;
            } catch (Exception e) {
                Log.e("ButtonBase", String.valueOf(e));
                return false;
            }
        } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return false;
        } else {
            if (ShowFeedback() && this.backgroundImageDrawable != null) {
                view.startAnimation(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
                view.invalidate();
            }
            try {
                TouchUp();
                return false;
            } catch (Exception e2) {
                Log.e("ButtonBase", String.valueOf(e2));
                return false;
            }
        }
    }

    public View getView() {
        return this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleFunction(description = "Place a blurred shadow of text underneath the text, drawn with the specified x, y, radius, color (e.g. -11, 12, 13, black.")
    public void SetShadow(float f, float f2, float f3, int i) {
        KodularHelper.setShadow(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f, f2, f3, i);
    }

    @SimpleFunction(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, animation will be removed.Position can be: top, left, right, bottom. Size can be 100. ")
    public void AnimationStyle(String str, String str2, int i, int i2) {
        ProgressHelper.setButtonProgressAnimation(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, str2, i, i2);
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(0);
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float", propertyType = "advanced")
    @SimpleProperty
    public void RotationAngle(double d) {
        this.rotationAngle = d;
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotation((float) d);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the degrees that the button is rotated around the pivot point. Increasing values result in clockwise rotation.")
    public double RotationAngle() {
        return this.rotationAngle;
    }

    @SimpleEvent(description = "Indicates that the button was pressed down.")
    public void TouchDown() {
        EventDispatcher.dispatchEvent(this, "TouchDown", new Object[0]);
    }

    @SimpleEvent(description = "Indicates that a button has been released.")
    public void TouchUp() {
        EventDispatcher.dispatchEvent(this, "TouchUp", new Object[0]);
    }

    @SimpleEvent(description = "Indicates the cursor moved over the button so it is now possible to click it.")
    public void GotFocus() {
        EventDispatcher.dispatchEvent(this, "GotFocus", new Object[0]);
    }

    @SimpleEvent(description = "Indicates the cursor moved away from the button so it is now no longer possible to click it.")
    public void LostFocus() {
        EventDispatcher.dispatchEvent(this, "LostFocus", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Left, center, or right.", userVisible = false)
    public int TextAlignment() {
        return this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    }

    @DesignerProperty(defaultValue = "1", editorType = "textalignment")
    @SimpleProperty(userVisible = false)
    public void TextAlignment(int i) {
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = i;
        TextViewUtil.setAlignment(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i, true);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public int Shape() {
        return this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    }

    @DesignerProperty(defaultValue = "0", editorType = "button_shape")
    @SimpleProperty(description = "Specifies the button's shape (default, rounded, rectangular, oval). The shape will not be visible if an Image is being displayed.", userVisible = false)
    public void Shape(int i) {
        this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = i;
        updateAppearance();
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Image to display on button.")
    public String Image() {
        return this.imagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty(description = "Specifies the path of the button's image.  If there is both an Image and a BackgroundColor, only the Image will be visible.")
    public void Image(@Asset String str) {
        if (!str.equals(this.imagePath) || this.backgroundImageDrawable == null) {
            this.imagePath = str;
            this.backgroundImageDrawable = null;
            if (str.length() > 0) {
                try {
                    this.backgroundImageDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), this.imagePath);
                } catch (Exception unused) {
                    Log.e("ButtonBase", "Unable to load " + this.imagePath);
                }
            }
            updateAppearance();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the button's background color")
    public int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFF444444", editorType = "color")
    @SimpleProperty(description = "Specifies the button's background color. The background color will not be visible if an Image is being displayed.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        updateAppearance();
    }

    private void updateAppearance() {
        Drawable drawable = this.backgroundImageDrawable;
        if (drawable == null) {
            if (this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j == 0) {
                int i = this.backgroundColor;
                if (i == -12303292 || i == 0) {
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f53hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                } else if (i == 16777215) {
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground((Drawable) null);
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f53hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackground().setAlpha(0);
                } else {
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground((Drawable) null);
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(this.f53hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBackground().setColorFilter(this.backgroundColor, PorterDuff.Mode.SRC_ATOP);
                }
                this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
            } else {
                vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R();
            }
            TextViewUtil.setMinSize(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho, LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn);
            return;
        }
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackground(drawable);
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        TextViewUtil.setMinSize(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0, 0);
    }

    private void vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R() {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        int i = this.backgroundColor;
        if (i == -12303292 || i == 0) {
            i = Color.parseColor("#5a595b");
        }
        shapeDrawable.getPaint().setColor(i);
        int i2 = this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
        if (i2 == 1) {
            shapeDrawable.setShape(new RoundRectShape(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (RectF) null, (float[]) null));
        } else if (i2 == 2) {
            shapeDrawable.setShape(new RectShape());
        } else if (i2 == 3) {
            shapeDrawable.setShape(new OvalShape());
        } else {
            throw new IllegalArgumentException();
        }
        if (ShowFeedback()) {
            KodularHelper.setRippleDrawable(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, shapeDrawable, this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
            setMargin();
            return;
        }
        ViewUtil.setBackgroundDrawable(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, shapeDrawable);
        setMargin();
    }

    public void setMargin() {
        KodularHelper.setMarginToButton(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.context, 4, 2, 4, 2);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If set, user can tap check box to cause action.")
    public boolean Enabled() {
        return this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isEnabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setEnabled(z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, button text is displayed in bold.")
    public boolean FontBold() {
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontBold(boolean z) {
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = z;
        TextViewUtil.setFontTypeface(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, z, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(description = "Specifies if a visual feedback should be shown for a button that as an image as background.")
    public void ShowFeedback(boolean z) {
        this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = z;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the button's visual feedback state")
    public boolean ShowFeedback() {
        return this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If set, button text is displayed in italics.")
    public boolean FontItalic() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontItalic(boolean z) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = z;
        TextViewUtil.setFontTypeface(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Point size for button text.")
    public float FontSize() {
        return TextViewUtil.getFontSize(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.context);
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void FontSize(float f) {
        TextViewUtil.setFontSize(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, f);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Font family for button text.", userVisible = false)
    public int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public void FontTypeface(int i) {
        this.fontTypeface = i;
        TextViewUtil.setFontTypeface(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Text to display on button.")
    public String Text() {
        return this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getText().toString();
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty
    public void Text(String str) {
        if (this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0) {
            TextViewUtil.setTextHTML(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str);
        } else {
            TextViewUtil.setText(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "If true, then this button will show html text else it will show plain text. Note: Not all HTML is supported.")
    public boolean HTMLFormat() {
        return this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void HTMLFormat(boolean z) {
        this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = z;
        Text(Text());
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Color for button text.")
    public int TextColor() {
        return this.textColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public void TextColor(int i) {
        this.textColor = i;
        if (i != 0) {
            this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(i);
        } else {
            this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(this.f52hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public void FontTypefaceImport(String str) {
        if (str != null) {
            TextViewUtil.setCustomFontTypeface(this.container.$form(), this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        }
    }

    @SimpleFunction(description = "Perform a button click as function.")
    public void ButtonClick() {
        this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.performClick();
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color", propertyType = "advanced")
    @SimpleProperty(description = "Set the buttons touch color.")
    public void TouchColor(int i) {
        if (i != 0) {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = i;
        } else {
            this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = Component.COLOR_LIGHT_GRAY;
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the buttons touch color.")
    public int TouchColor() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean", propertyType = "advanced")
    @SimpleProperty(userVisible = false)
    public void BorderShadow(boolean z) {
        if (z) {
            try {
                if (this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStateListAnimator() == null) {
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateListAnimator(this.f51B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getStateListAnimator());
                    this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
                    this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = true;
                }
            } catch (Exception e) {
                Log.e("ButtonBase", String.valueOf(e));
            }
        } else {
            try {
                this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStateListAnimator((StateListAnimator) null);
                this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
                this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = false;
            } catch (Exception e2) {
                Log.e("ButtonBase", String.valueOf(e2));
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns true if the button have a outside border shadow on click.")
    public boolean BorderShadow() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    public void onClick(View view) {
        click();
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            GotFocus();
        } else {
            LostFocus();
        }
    }

    public boolean onLongClick(View view) {
        return longClick();
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text.")
    public void WithIconFromPicture(String str, String str2, int i, int i2, int i3) {
        try {
            BitmapDrawable bitmapDrawable = MediaUtil.getBitmapDrawable(this.container.$form(), str2);
            if (i2 == 0) {
                i2 = 100;
            }
            if (i3 == 0) {
                i3 = 100;
            }
            bitmapDrawable.setBounds(0, 0, i2, i3);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, bitmapDrawable, i);
        } catch (Exception unused) {
            Log.e("ButtonBase", "Unable to load ".concat(String.valueOf(str2)));
        }
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text. Use a 'Material' icon as the button icon without uploading a image resource into your project. You can find the icon name here at https://material.io/resources/icons")
    public void WithIconFromMaterialFont(String str, String str2, int i, int i2, float f) {
        if (f == 0.0f) {
            f = 80.0f;
        }
        int i3 = (int) f;
        try {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(KodularHelper.textToBitmap(this.context, "material", str2, i, f));
            bitmapDrawable.setBounds(0, 0, i3, i3);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, bitmapDrawable, i2);
        } catch (Exception unused) {
            Log.e("ButtonBase", "Unable to load the material icon font.");
        }
    }

    @SimpleFunction(description = "Show an image on the given position near to the button. You can use following words for the position: 'Left', 'Right', 'Top' or 'Bottom'. Use the padding to add space between the icon and text. Use a 'FontAwesome' icon as the button icon without uploading a image resource into your project. You can find the icon code here at https://fontawesome.com/cheatsheet Use as example for a heart icon just 'f004' or '&#xf004;'")
    public void WithIconFromFontAwesome(String str, String str2, int i, int i2, float f) {
        if (f == 0.0f) {
            f = 80.0f;
        }
        int i3 = (int) f;
        try {
            if (!str2.startsWith("&#x")) {
                str2 = "&#x".concat(String.valueOf(str2));
            }
            if (!str2.endsWith(";")) {
                str2 = str2 + ";";
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(KodularHelper.textToBitmap(this.context, "font_awesome", str2, i, f));
            bitmapDrawable.setBounds(0, 0, i3, i3);
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f55hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, bitmapDrawable, i2);
        } catch (Exception unused) {
            Log.e("ButtonBase", "Unable to load the material icon font.");
        }
    }

    private static void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Button button, String str, Drawable drawable, int i) {
        if (str.equalsIgnoreCase("Left")) {
            button.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            button.setCompoundDrawablePadding(i);
        } else if (str.equalsIgnoreCase("Top")) {
            button.setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
            button.setCompoundDrawablePadding(i);
        } else if (str.equalsIgnoreCase("Right")) {
            button.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            button.setCompoundDrawablePadding(i);
        } else if (str.equalsIgnoreCase("Bottom")) {
            button.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable);
            button.setCompoundDrawablePadding(i);
        }
    }
}
