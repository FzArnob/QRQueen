package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import com.google.appinventor.components.annotations.Asset;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A 'sprite' that can be placed on a <code>Canvas</code>, where it can react to touches and drags, interact with other sprites (<code>Ball</code>s and other <code>ImageSprite</code>s) and the edge of the Canvas, and move according to its property values.  Its appearance is that of the image specified in its <code>Picture</code> property (unless its <code>Visible</code> property is <code>False</code>).</p> <p>To have an <code>ImageSprite</code> move 10 pixels to the left every 1000 milliseconds (one second), for example, you would set the <code>Speed</code> property to 10 [pixels], the <code>Interval</code> property to 1000 [milliseconds], the <code>Heading</code> property to 180 [degrees], and the <code>Enabled</code> property to <code>True</code>.  A sprite whose <code>Rotates</code> property is <code>True</code> will rotate its image as the sprite's <code>Heading</code> changes.  Checking for collisions with a rotated sprite currently checks the sprite's unrotated position so that collision checking will be inaccurate for tall narrow or short wide sprites that are rotated.  Any of the sprite properties can be changed at any time under program control.</p> ", version = 8)
@UsesPermissions({"android.permission.INTERNET"})
public class ImageSprite extends Sprite {
    private BitmapDrawable B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = -1;
    private boolean Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL;
    private int YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = -1;
    private final Form form;
    private String picturePath = "";

    public void HeightPercent(int i) {
    }

    public void WidthPercent(int i) {
    }

    public ImageSprite(ComponentContainer componentContainer) {
        super(componentContainer);
        this.form = componentContainer.$form();
        this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = true;
    }

    public void onDraw(Canvas canvas) {
        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null && this.visible) {
            int round = (int) (((float) Math.round(this.xLeft)) * this.form.deviceDensity());
            int round2 = (int) (((float) Math.round(this.yTop)) * this.form.deviceDensity());
            int Width = (int) (((float) Width()) * this.form.deviceDensity());
            int Height = (int) (((float) Height()) * this.form.deviceDensity());
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBounds(round, round2, round + Width, round2 + Height);
            if (!this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL) {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.draw(canvas);
                return;
            }
            canvas.save();
            canvas.rotate((float) (-Heading()), (float) (round + (Width / 2)), (float) (round2 + (Height / 2)));
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.draw(canvas);
            canvas.restore();
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The picture that determines the ImageSprite's appearance.")
    public String Picture() {
        return this.picturePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty
    public void Picture(@Asset String str) {
        if (str == null) {
            str = "";
        }
        this.picturePath = str;
        try {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = MediaUtil.getBitmapDrawable(this.form, str);
        } catch (Exception unused) {
            Log.e("ImageSprite", "Unable to load " + this.picturePath);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
        }
        registerChange();
    }

    @SimpleProperty(description = "The height of the ImageSprite in pixels.")
    public int Height() {
        int i = this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
        if (i != -1 && i != -2 && i > -1000) {
            return i;
        }
        BitmapDrawable bitmapDrawable = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (bitmapDrawable == null) {
            return 0;
        }
        return (int) (((float) bitmapDrawable.getBitmap().getHeight()) / this.form.deviceDensity());
    }

    @SimpleProperty
    public void Height(int i) {
        this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = i;
        registerChange();
    }

    @SimpleProperty(description = "The width of the ImageSprite in pixels.")
    public int Width() {
        int i = this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
        if (i != -1 && i != -2 && i > -1000) {
            return i;
        }
        BitmapDrawable bitmapDrawable = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        if (bitmapDrawable == null) {
            return 0;
        }
        return (int) (((float) bitmapDrawable.getBitmap().getWidth()) / this.form.deviceDensity());
    }

    @SimpleProperty
    public void Width(int i) {
        this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = i;
        registerChange();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the image should rotate to match the ImageSprite's heading. The sprite rotates around its centerpoint.")
    public boolean Rotates() {
        return this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Rotates(boolean z) {
        this.Qws8UL0KACxj4AFwEq11K2awfkG72XuPUTZHhPLrlGlIXoxeK9stYQCrIWmabWcL = z;
        registerChange();
    }

    @SimpleProperty(description = "The horizontal coordinate of the left edge of the ImageSprite, increasing as the ImageSprite moves right.")
    public double X() {
        return super.X();
    }

    @SimpleProperty(description = "The vertical coordinate of the top edge of the ImageSprite, increasing as the ImageSprite moves down.")
    public double Y() {
        return super.Y();
    }

    @SimpleFunction(description = "Moves the ImageSprite so that its left top corner is at the specfied x and y coordinates.")
    public void MoveTo(double d, double d2) {
        super.MoveTo(d, d2);
    }
}
