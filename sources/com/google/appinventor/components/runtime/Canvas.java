package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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
import com.google.appinventor.components.runtime.collect.Sets;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.FileWriteOperation;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.MultiTouchDetector;
import com.google.appinventor.components.runtime.util.PaintUtil;
import com.google.appinventor.components.runtime.util.PolyUtil;
import com.google.appinventor.components.runtime.util.ScopedFile;
import com.google.appinventor.components.runtime.util.Synchronizer;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@DesignerComponent(category = ComponentCategory.ANIMATION, description = "<p>A two-dimensional touch-sensitive rectangular panel on which drawing can be done and sprites can be moved.</p> <p>The <code>BackgroundColor</code>, <code>PaintColor</code>, <code>BackgroundImage</code>, <code>Width</code>, and <code>Height</code> of the Canvas can be set in either the Designer or in the Blocks Editor.  The <code>Width</code> and <code>Height</code> are measured in pixels and must be positive.</p><p>Any location on the Canvas can be specified as a pair of (X, Y) values, where <ul> <li>X is the number of pixels away from the left edge of the Canvas</li><li>Y is the number of pixels away from the top edge of the Canvas</li></ul>.</p> <p>There are events to tell when and where a Canvas has been touched or a <code>Sprite</code> (<code>ImageSprite</code> or <code>Ball</code>) has been dragged.  There are also methods for drawing points, lines, and circles.</p>", version = 15)
@SimpleObject
@UsesPermissions({"android.permission.INTERNET"})
public final class Canvas extends AndroidViewComponent implements ComponentContainer {
    private final Set<ExtensionGestureDetector> B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Sets.newHashSet();
    /* access modifiers changed from: private */
    public boolean KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    /* access modifiers changed from: private */
    public int backgroundColor;
    /* access modifiers changed from: private */
    public String backgroundImagePath = "";
    private int fontTypeface = 0;
    private Form form = $form();
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Paint f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final GestureDetector f59hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    final CanvasView f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final b f61hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final MultiTouchDetector f62hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private PolyUtil f63hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    private boolean oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    private boolean sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = false;
    private boolean vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    private int vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    /* renamed from: vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R  reason: collision with other field name */
    private final List<Sprite> f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    public interface ExtensionGestureDetector {
        boolean onTouchEvent(MotionEvent motionEvent);
    }

    class b {
        boolean f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = false;
        float hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = -1.0f;
        final List<Sprite> qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = new ArrayList();
        float vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = -1.0f;
        float vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = -1.0f;
        float wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = -1.0f;
        boolean zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = false;

        b() {
        }
    }

    public final class CanvasView extends View {
        private Bitmap B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        private Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Bitmap.createBitmap(32, 48, Bitmap.Config.ARGB_8888);

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private android.graphics.Canvas f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private BitmapDrawable f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private Bitmap wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        public CanvasView(Context context) {
            super(context);
        }

        /* access modifiers changed from: private */
        public Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            setDrawingCacheEnabled(true);
            destroyDrawingCache();
            Bitmap drawingCache = getDrawingCache();
            if (drawingCache != null) {
                return drawingCache;
            }
            int width = getWidth();
            int height = getHeight();
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            android.graphics.Canvas canvas = new android.graphics.Canvas(createBitmap);
            layout(0, 0, width, height);
            draw(canvas);
            return createBitmap;
        }

        public final void onDraw(android.graphics.Canvas canvas) {
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = null;
            super.onDraw(canvas);
            canvas.drawBitmap(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0.0f, 0.0f, (Paint) null);
            for (Sprite onDraw : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                onDraw.onDraw(canvas);
            }
            boolean unused = Canvas.this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = true;
        }

        /* access modifiers changed from: protected */
        public final void onSizeChanged(int i, int i2, int i3, int i4) {
            Bitmap bitmap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (bitmap != null) {
                int width = bitmap.getWidth();
                int height = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight();
                if (i != width || i2 != height) {
                    Bitmap bitmap2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    try {
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, i, i2, false);
                        if (createScaledBitmap.isMutable()) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = createScaledBitmap;
                            this.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                            this.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new android.graphics.Canvas(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                            this.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawBitmap(bitmap2, new Rect(0, 0, width, height), new RectF(0.0f, 0.0f, (float) i, (float) i2), (Paint) null);
                        }
                    } catch (Exception e) {
                        Log.e("Canvas", String.valueOf(e));
                    }
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
                }
            }
        }

        /* access modifiers changed from: protected */
        public final void onMeasure(int i, int i2) {
            BitmapDrawable bitmapDrawable = this.f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            int i3 = 48;
            int i4 = 32;
            if (bitmapDrawable != null) {
                try {
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    int width = bitmap.getWidth();
                    i3 = bitmap.getHeight();
                    i4 = width;
                } catch (Exception e) {
                    Log.e("Canvas", "Error on backgroundDrawable.getBitmap(): " + e.getMessage());
                }
            }
            setMeasuredDimension(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i, i4), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2, i3));
        }

        private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i, int i2) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == 1073741824) {
                return size;
            }
            return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
        }

        public final boolean onTouchEvent(MotionEvent motionEvent) {
            BoundingBox boundingBox;
            boolean z;
            BoundingBox boundingBox2;
            MotionEvent motionEvent2 = motionEvent;
            Canvas.this.container.$form().dontGrabTouchEventsForComponent();
            b hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this);
            int Width = Canvas.this.Width();
            int Height = Canvas.this.Height();
            float max = Math.max(0.0f, ((float) ((int) motionEvent.getX())) / Canvas.this.$form().deviceDensity());
            float max2 = Math.max(0.0f, ((float) ((int) motionEvent.getY())) / Canvas.this.$form().deviceDensity());
            int i = (int) max;
            int i2 = (int) max2;
            new BoundingBox((double) Math.max(0, i - 12), (double) Math.max(0, i2 - 12), (double) Math.min(Width - 1, i + 12), (double) Math.min(Height - 1, i2 + 12));
            int action = motionEvent.getAction();
            if (action == 0) {
                BoundingBox boundingBox3 = boundingBox;
                z = true;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.clear();
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = max;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = max2;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = max;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = max2;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = false;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = false;
                for (Sprite sprite : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                    if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(boundingBox3)) {
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(sprite);
                        sprite.TouchDown(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
                    }
                }
                Canvas.this.TouchDown(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq);
            } else if (action != 1) {
                if (action == 2) {
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == -1.0f || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq == -1.0f || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO == -1.0f || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R == -1.0f) {
                        Log.w("Canvas", "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: ".concat(String.valueOf(motionEvent)));
                    }
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ || Math.abs(max - hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) >= 15.0f || Math.abs(max2 - hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) >= 15.0f) {
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = true;
                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = true;
                        if ((max > 0.0f && max <= ((float) Width) && max2 > 0.0f && max2 <= ((float) Height)) || Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                            for (Sprite sprite2 : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.contains(sprite2) || !sprite2.Enabled() || !sprite2.Visible()) {
                                    boundingBox2 = boundingBox;
                                } else {
                                    boundingBox2 = boundingBox;
                                    if (sprite2.intersectsWith(boundingBox2)) {
                                        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.add(sprite2);
                                    }
                                }
                                boundingBox = boundingBox2;
                            }
                            boolean z2 = false;
                            for (Sprite next : hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                                if (next.Enabled() && next.Visible()) {
                                    next.Dragged(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, max, max2);
                                    z2 = true;
                                }
                            }
                            z = true;
                            Canvas.this.Dragged(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, max, max2, z2);
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = max;
                            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = max2;
                        }
                    }
                }
                z = true;
            } else {
                z = true;
                if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5) {
                    boolean z3 = false;
                    for (Sprite next2 : hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                        if (next2.Enabled() && next2.Visible()) {
                            next2.Touched(max, max2);
                            next2.TouchUp(max, max2);
                            z3 = true;
                        }
                    }
                    Canvas.this.Touched(max, max2, z3);
                } else {
                    for (Sprite next3 : hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE) {
                        if (next3.Enabled() && next3.Visible()) {
                            next3.Touched(max, max2);
                            next3.TouchUp(max, max2);
                        }
                    }
                }
                Canvas.this.TouchUp(max, max2);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = false;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = -1.0f;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = -1.0f;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = -1.0f;
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = -1.0f;
            }
            Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this).onTouchEvent(motionEvent2);
            for (ExtensionGestureDetector onTouchEvent : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                onTouchEvent.onTouchEvent(motionEvent2);
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
            Canvas canvas = Canvas.this;
            if (str == null) {
                str = "";
            }
            String unused = canvas.backgroundImagePath = str;
            this.f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = null;
            if (!TextUtils.isEmpty(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this))) {
                try {
                    this.f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = MediaUtil.getBitmapDrawable(Canvas.this.container.$form(), Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this));
                } catch (Exception unused2) {
                    Log.e("Canvas", "Unable to load " + Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this));
                }
            }
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE();
            Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB();
        }

        private void qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE() {
            Drawable drawable;
            BitmapDrawable bitmapDrawable;
            int i = -1;
            if (Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this) == "" || (bitmapDrawable = this.f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) == null) {
                if (Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this) != 0) {
                    i = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this);
                }
                drawable = new ColorDrawable(i);
            } else {
                drawable = bitmapDrawable.getConstantState().newDrawable();
                if (Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this) != 0) {
                    i = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this);
                }
                drawable.setColorFilter(i, PorterDuff.Mode.DST_OVER);
            }
            setBackgroundDrawable(drawable);
        }

        private void Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB() {
            this.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawColor(0, PorterDuff.Mode.CLEAR);
            invalidate();
        }

        public final void setBackgroundColor(int i) {
            int unused = Canvas.this.backgroundColor = i;
            qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE();
            Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB();
        }

        /* access modifiers changed from: private */
        public int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i, int i2) {
            if (i >= 0 && i < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() && i2 >= 0 && i2 < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight()) {
                try {
                    int pixel = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPixel(i, i2);
                    if (pixel != 0) {
                        return pixel;
                    }
                    BitmapDrawable bitmapDrawable = this.f68hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    if (bitmapDrawable != null) {
                        if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null) {
                            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight(), false);
                        }
                        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getPixel(i, i2);
                    } else if (Color.alpha(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) != 0) {
                        return Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this);
                    } else {
                        return 16777215;
                    }
                } catch (IllegalArgumentException unused) {
                    Log.e("Canvas", "Returning COLOR_NONE (exception) from getBackgroundPixelColor.");
                }
            }
            return 16777215;
        }

        /* access modifiers changed from: private */
        public int wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i, int i2) {
            if (i >= 0 && i < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getWidth() && i2 >= 0 && i2 < this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getHeight()) {
                if (this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou == null) {
                    boolean z = false;
                    Iterator it = Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this).iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((Sprite) it.next()).Visible()) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (!z) {
                        return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i, i2);
                    }
                    this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
                }
                try {
                    return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.getPixel(i, i2);
                } catch (IllegalArgumentException unused) {
                    Log.e("Canvas", "Returning COLOR_NONE (exception) from getPixelColor.");
                }
            }
            return 16777215;
        }

        static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(CanvasView canvasView, String str, int i, int i2, float f) {
            canvasView.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.save();
            float f2 = (float) i;
            float f3 = (float) i2;
            canvasView.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.rotate(-f, f2, f3);
            canvasView.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawText(str, f2, f3, Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this));
            canvasView.f67hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.restore();
            canvasView.invalidate();
        }
    }

    public Canvas(ComponentContainer componentContainer) {
        super(componentContainer);
        Activity $context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = $context;
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new CanvasView($context);
        componentContainer.$add(this);
        Paint paint = new Paint();
        this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = paint;
        paint.setFlags(1);
        paint.setStrokeWidth(2.0f);
        PaintColor(-16777216);
        BackgroundColor(-1);
        TextAlignment(1);
        FontSize(14.0f);
        TextViewUtil.setContext(componentContainer.$context());
        TextViewUtil.setFontTypefaceCanvas(paint, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = new LinkedList();
        this.f61hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new b();
        this.f59hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new GestureDetector($context, new a());
        this.f62hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new MultiTouchDetector(this);
    }

    public final void Initialize() {
        if (FileUtil.needsWritePermission(this.form.DefaultFileScope())) {
            this.havePermission = !this.form.isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        } else {
            this.havePermission = true;
        }
    }

    public final View getView() {
        return this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Activity getContext() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final void registerCustomGestureDetector(ExtensionGestureDetector extensionGestureDetector) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.add(extensionGestureDetector);
    }

    public final void removeCustomGestureDetector(Object obj) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove(obj);
    }

    public final boolean ready() {
        return this.KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    }

    /* access modifiers changed from: package-private */
    public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Sprite sprite) {
        for (int i = 0; i < this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.size(); i++) {
            if (this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.get(i).Z() > sprite.Z()) {
                this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(i, sprite);
                return;
            }
        }
        this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.add(sprite);
    }

    /* access modifiers changed from: package-private */
    public final void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Sprite sprite) {
        this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.remove(sprite);
    }

    public final Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final Form $form() {
        return this.container.$form();
    }

    public final void $add(AndroidViewComponent androidViewComponent) {
        throw new UnsupportedOperationException("Canvas.$add() called");
    }

    public final void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        throw new UnsupportedOperationException("Canvas.setChildWidth() called");
    }

    public final void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        throw new UnsupportedOperationException("Canvas.setChildHeight() called");
    }

    /* access modifiers changed from: package-private */
    public final void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(Sprite sprite) {
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
        findSpriteCollisions(sprite);
    }

    /* access modifiers changed from: protected */
    public final void findSpriteCollisions(Sprite sprite) {
        for (Sprite next : this.f64vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R) {
            if (next != sprite) {
                if (sprite.CollidingWith(next)) {
                    if (!sprite.Visible() || !sprite.Enabled() || !next.Visible() || !next.Enabled() || !Sprite.colliding(next, sprite)) {
                        sprite.NoLongerCollidingWith(next);
                        next.NoLongerCollidingWith(sprite);
                    }
                } else if (sprite.Visible() && sprite.Enabled() && next.Visible() && next.Enabled() && Sprite.colliding(next, sprite)) {
                    sprite.CollidedWith(next);
                    next.CollidedWith(sprite);
                }
            }
        }
    }

    @SimpleProperty
    public final void Width(int i) {
        if (i > 0 || i == -2 || i == -1 || i <= -1000) {
            super.Width(i);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Width", 1002, new Object[0]);
        }
    }

    @SimpleProperty
    public final void Height(int i) {
        if (i > 0 || i == -2 || i == -1 || i <= -1000) {
            super.Height(i);
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "Height", 1003, new Object[0]);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color of the canvas background.")
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty
    public final void BackgroundColor(int i) {
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The name of a file containing the background image for the canvas")
    public final String BackgroundImage() {
        return this.backgroundImagePath;
    }

    @DesignerProperty(defaultValue = "", editorType = "image_asset")
    @SimpleProperty
    public final void BackgroundImage(@Asset String str) {
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The color in which lines are drawn")
    public final int PaintColor() {
        return this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty
    public final void PaintColor(int i) {
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = i;
        Paint paint = this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (i == 0) {
            PaintUtil.changePaint(paint, -16777216);
        } else if (i != 16777215) {
            PaintUtil.changePaint(paint, i);
        } else {
            PaintUtil.changePaintTransparent(paint);
        }
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The font size of text drawn on the canvas.")
    public final float FontSize() {
        return this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTextSize() / $form().deviceDensity();
    }

    @DesignerProperty(defaultValue = "14.0", editorType = "non_negative_float")
    @SimpleProperty
    public final void FontSize(float f) {
        this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextSize(f * $form().deviceDensity());
    }

    @DesignerProperty(defaultValue = "", editorType = "font_asset", propertyType = "advanced")
    @SimpleProperty(description = "Set a custom font.")
    public final void FontTypefaceImport(String str) {
        if (str != null) {
            TextViewUtil.setCustomFontTypefaceCanvas(this.container.$form(), this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        }
    }

    @DesignerProperty(defaultValue = "0", editorType = "typeface")
    @SimpleProperty(userVisible = false)
    public final void FontTypeface(int i) {
        this.fontTypeface = i;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final int FontTypeface() {
        return this.fontTypeface;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontBold(boolean z) {
        this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = z;
        TextViewUtil.setFontTypefaceCanvas(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, z, this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontBold() {
        return this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = false)
    public final void FontItalic(boolean z) {
        this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = z;
        TextViewUtil.setFontTypefaceCanvas(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.fontTypeface, this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE, z);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
    public final boolean FontItalic() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "The width of lines drawn on the canvas.")
    public final float LineWidth() {
        return this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getStrokeWidth() / $form().deviceDensity();
    }

    @DesignerProperty(defaultValue = "2.0", editorType = "non_negative_float")
    @SimpleProperty
    public final void LineWidth(float f) {
        this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setStrokeWidth(f * $form().deviceDensity());
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Determines the alignment of the text drawn by DrawText() or DrawAngle() with respect to the point specified by that command: point at the left of the text, point at the center of the text, or point at the right of the text.", userVisible = true)
    public final int TextAlignment() {
        return this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    }

    @DesignerProperty(defaultValue = "1", editorType = "textalignment")
    @SimpleProperty(userVisible = true)
    public final void TextAlignment(int i) {
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = i;
        if (i == 0) {
            this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.LEFT);
        } else if (i == 1) {
            this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.CENTER);
        } else if (i == 2) {
            this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextAlign(Paint.Align.RIGHT);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether moves can extend beyond the canvas borders.   Default is false. This should normally be false, and the property is provided for backwards compatibility.", userVisible = true)
    public final boolean ExtendMovesOutsideCanvas() {
        return this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(userVisible = true)
    public final void ExtendMovesOutsideCanvas(boolean z) {
        this.sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = z;
    }

    @SimpleEvent
    public final void Touched(float f, float f2, boolean z) {
        EventDispatcher.dispatchEvent(this, "Touched", Float.valueOf(f), Float.valueOf(f2), Boolean.valueOf(z));
    }

    @SimpleEvent
    public final void TouchDown(float f, float f2) {
        EventDispatcher.dispatchEvent(this, "TouchDown", Float.valueOf(f), Float.valueOf(f2));
    }

    @SimpleEvent
    public final void TouchUp(float f, float f2) {
        EventDispatcher.dispatchEvent(this, "TouchUp", Float.valueOf(f), Float.valueOf(f2));
    }

    @SimpleEvent
    public final void Flung(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        EventDispatcher.dispatchEvent(this, "Flung", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f6), Boolean.valueOf(z));
    }

    @SimpleEvent
    public final void Dragged(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        EventDispatcher.dispatchEvent(this, "Dragged", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f6), Boolean.valueOf(z));
    }

    @SimpleFunction(description = "Clears anything drawn on this Canvas but not any background color or image.")
    public final void Clear() {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    @SimpleFunction
    public final void DrawPoint(int i, int i2) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPoint(((float) i) * $form().deviceDensity(), ((float) i2) * $form().deviceDensity(), this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction
    public final void DrawCircle(int i, int i2, float f, boolean z) {
        float deviceDensity = ((float) i) * $form().deviceDensity();
        float deviceDensity2 = ((float) i2) * $form().deviceDensity();
        float deviceDensity3 = f * $form().deviceDensity();
        Paint paint = new Paint(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        paint.setStyle(z ? Paint.Style.FILL : Paint.Style.STROKE);
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawCircle(deviceDensity, deviceDensity2, deviceDensity3, paint);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction
    public final void DrawLine(int i, int i2, int i3, int i4) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawLine(((float) i) * $form().deviceDensity(), ((float) i2) * $form().deviceDensity(), ((float) i3) * $form().deviceDensity(), ((float) i4) * $form().deviceDensity(), this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws a shape on the canvas. pointList should be a list contains sub-lists with two number which represents a coordinate. The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3)) When fill is true, the shape will be filled.")
    public final void DrawShape(YailList yailList, boolean z) {
        try {
            float[][] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList);
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.length != 0) {
                float deviceDensity = $form().deviceDensity();
                Path path = new Path();
                float[] fArr = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[0];
                path.moveTo(fArr[0] * deviceDensity, fArr[1] * deviceDensity);
                for (int i = 1; i < hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.length; i++) {
                    float[] fArr2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2[i];
                    path.lineTo(fArr2[0] * deviceDensity, fArr2[1] * deviceDensity);
                }
                path.close();
                Paint paint = new Paint(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                paint.setStyle(z ? Paint.Style.FILL : Paint.Style.STROKE);
                CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPath(path, paint);
                this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
                return;
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException unused) {
            $form().dispatchErrorOccurredEvent(this, "DrawShape", 1004, new Object[0]);
        }
    }

    private static float[][] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) throws IllegalArgumentException {
        if (yailList == null || yailList.size() == 0) {
            throw new IllegalArgumentException();
        }
        int size = yailList.size();
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = size;
        float[][] fArr = (float[][]) Array.newInstance(float.class, iArr);
        Object[] array = yailList.toArray();
        int length = array.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object obj = array[i];
            if (obj instanceof YailList) {
                YailList yailList2 = (YailList) obj;
                if (yailList2.size() == 2) {
                    try {
                        fArr[i2][0] = Float.parseFloat(yailList2.getString(0));
                        fArr[i2][1] = Float.parseFloat(yailList2.getString(1));
                        i2++;
                        i++;
                    } catch (NullPointerException | NumberFormatException e) {
                        throw new IllegalArgumentException(e.fillInStackTrace());
                    }
                } else {
                    throw new IllegalArgumentException("length of item YailList(" + i2 + ") is not 2");
                }
            } else {
                throw new IllegalArgumentException("item(" + i2 + ") in YailList is not a YailList");
            }
        }
        return fArr;
    }

    @SimpleFunction(description = "Draw an arc on Canvas, by drawing an arc from a specified oval (specified by left, top, right & bottom). Start angle is 0 when heading to the right, and increase when rotate clockwise. When useCenter is true, a sector will be drawed instead of an arc. When fill is true, a filled arc (or sector) will be drawed instead of just an outline.")
    public final void DrawArc(int i, int i2, int i3, int i4, float f, float f2, boolean z, boolean z2) {
        float deviceDensity = $form().deviceDensity();
        Paint paint = new Paint(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        paint.setStyle(z2 ? Paint.Style.FILL : Paint.Style.STROKE);
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawArc(new RectF(((float) i) * deviceDensity, ((float) i2) * deviceDensity, ((float) i3) * deviceDensity, deviceDensity * ((float) i4)), f, f2, z, paint);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text relative to the specified coordinates using the values of the FontSize and TextAlignment properties.")
    public final void DrawText(String str, int i, int i2) {
        float deviceDensity = $form().deviceDensity();
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawText(str, ((float) i) * deviceDensity, ((float) i2) * deviceDensity, this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Draws the specified text starting at the specified coordinates at the specified angle using the values of the FontSize and TextAlignment properties.")
    public final void DrawTextAtAngle(String str, int i, int i2, float f) {
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str, (int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()), f);
    }

    @SimpleFunction(description = "Gets the color of the specified point. This includes the background and any drawn points, lines, or circles but not sprites.")
    public final int GetBackgroundPixelColor(int i, int i2) {
        return this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()));
    }

    @SimpleFunction(description = "Sets the color of the specified point. This differs from DrawPoint by having an argument for color.")
    public final void SetBackgroundPixelColor(int i, int i2, int i3) {
        Paint paint = new Paint();
        PaintUtil.changePaint(paint, i3);
        CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).drawPoint((float) ((int) (((float) i) * $form().deviceDensity())), (float) ((int) (((float) i2) * $form().deviceDensity())), paint);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleFunction(description = "Gets the color of the specified point.")
    public final int GetPixelColor(int i, int i2) {
        return this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou((int) (((float) i) * $form().deviceDensity()), (int) (((float) i2) * $form().deviceDensity()));
    }

    @UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE"})
    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage. If an error occurs, the Screen's ErrorOccurred event will be called.")
    public final String Save() {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FileUtil.getScopedPictureFile($form(), "png"), Bitmap.CompressFormat.PNG, "Save");
    }

    @UsesPermissions({"android.permission.WRITE_EXTERNAL_STORAGE"})
    @SimpleFunction(description = "Saves a picture of this Canvas to the device's external storage in the file named fileName. fileName must end with one of .jpg, .jpeg, or .png, which determines the file type.")
    public final String SaveAs(String str) {
        Bitmap.CompressFormat compressFormat;
        if (str.endsWith(".jpg") || str.endsWith(".jpeg")) {
            compressFormat = Bitmap.CompressFormat.JPEG;
        } else if (str.endsWith(".png")) {
            compressFormat = Bitmap.CompressFormat.PNG;
        } else if (!str.contains(".")) {
            str = str + ".png";
            compressFormat = Bitmap.CompressFormat.PNG;
        } else {
            this.container.$form().dispatchErrorOccurredEvent(this, "SaveAs", ErrorMessages.ERROR_MEDIA_IMAGE_FILE_FORMAT, new Object[0]);
            return "";
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(new ScopedFile(this.form.DefaultFileScope(), str), compressFormat, "SaveAs");
    }

    @SimpleEvent(description = "This event is invoked when two-finger pinches. ScaleFactor is the ratio of the average distance between two-fingers from current and previous scale event.")
    public final void Scale(double d) {
        EventDispatcher.dispatchEvent(this, "Scale", Double.valueOf(d));
    }

    private String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ScopedFile scopedFile, Bitmap.CompressFormat compressFormat, String str) {
        if (this.havePermission || !FileUtil.needsWritePermission(scopedFile)) {
            Synchronizer synchronizer = new Synchronizer();
            final Synchronizer synchronizer2 = synchronizer;
            final Bitmap.CompressFormat compressFormat2 = compressFormat;
            new FileWriteOperation(this.form, this, str, scopedFile) {
                public final boolean process(OutputStream outputStream) {
                    synchronizer2.wakeup(Boolean.valueOf((CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) == null ? Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() : CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this))).compress(compressFormat2, 100, outputStream)));
                    return true;
                }
            }.run();
            if (synchronizer.getThrowable() instanceof FileNotFoundException) {
                this.container.$form().dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_MEDIA_CANNOT_OPEN, FileUtil.resolveFileName(this.form, scopedFile));
                return "";
            } else if (synchronizer.getThrowable() instanceof IOException) {
                this.container.$form().dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_MEDIA_FILE_ERROR, synchronizer.getThrowable().getMessage());
                return "";
            } else if (synchronizer.getThrowable() instanceof PermissionException) {
                this.container.$form().dispatchPermissionDeniedEvent((Component) this, str, (PermissionException) synchronizer.getThrowable());
                return "";
            } else if (!(synchronizer.getThrowable() instanceof FileUtil.FileException)) {
                return ((Boolean) synchronizer.getResult()).booleanValue() ? FileUtil.resolveFileName(this.form, scopedFile) : "";
            } else {
                this.container.$form().dispatchErrorOccurredEvent(this, str, ((FileUtil.FileException) synchronizer.getThrowable()).getErrorMessageNumber(), new Object[0]);
                return "";
            }
        } else {
            this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    boolean unused = Canvas.this.havePermission = z;
                }
            });
            throw new StopBlocksExecution();
        }
    }

    class a extends GestureDetector.SimpleOnGestureListener {
        a() {
        }

        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            BoundingBox boundingBox;
            float max = (float) Math.max(0, (int) (motionEvent.getX() / Canvas.this.$form().deviceDensity()));
            float max2 = (float) Math.max(0, (int) (motionEvent.getY() / Canvas.this.$form().deviceDensity()));
            float f3 = f / 1000.0f;
            float f4 = f2 / 1000.0f;
            float sqrt = (float) Math.sqrt((double) ((f3 * f3) + (f4 * f4)));
            float f5 = (float) (-Math.toDegrees(Math.atan2((double) f4, (double) f3)));
            int i = (int) max;
            int i2 = (int) max2;
            float f6 = f3;
            float f7 = f4;
            BoundingBox boundingBox2 = new BoundingBox((double) Math.max(0, i - 12), (double) Math.max(0, i2 - 12), (double) Math.min(Canvas.this.Width() - 1, i + 12), (double) Math.min(Canvas.this.Height() - 1, i2 + 12));
            boolean z = false;
            for (Sprite sprite : Canvas.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Canvas.this)) {
                if (!sprite.Enabled() || !sprite.Visible() || !sprite.intersectsWith(boundingBox2)) {
                    boundingBox = boundingBox2;
                } else {
                    boundingBox = boundingBox2;
                    sprite.Flung(max, max2, sqrt, f5, f6, f7);
                    z = true;
                }
                boundingBox2 = boundingBox;
            }
            Canvas.this.Flung(max, max2, sqrt, f5, f6, f7, z);
            return true;
        }
    }

    @SimpleFunction(description = "Creates a polygon with with specified number of sides from a radius.")
    public final void DrawPolygon(float f, float f2, int i, float f3, float f4, float f5, boolean z, boolean z2) {
        if (this.f63hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.f63hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new PolyUtil();
        }
        if (z2) {
            CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        Paint paint = new Paint(this.f58hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        paint.setStyle(z ? Paint.Style.FILL : Paint.Style.STROKE);
        this.f63hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.drawPolygon(CanvasView.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), i, f * $form().deviceDensity(), f2 * $form().deviceDensity(), f3 * $form().deviceDensity(), f4 * $form().deviceDensity(), f5, paint);
        this.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }
}
