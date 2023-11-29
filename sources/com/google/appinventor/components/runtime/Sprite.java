package com.google.appinventor.components.runtime;

import android.graphics.Canvas;
import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.Direction;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.TimerInternal;
import java.util.HashSet;
import java.util.Set;

@SimpleObject
public abstract class Sprite extends VisibleComponent implements AlarmHandler, Deleteable, OnDestroyListener {
    protected static final boolean DEFAULT_ORIGIN_AT_CENTER = false;
    private final Handler androidUIHandler;
    protected final Canvas canvas;
    protected Form form;
    protected double heading;
    protected double headingCos;
    protected double headingRadians;
    protected double headingSin;
    private final TimerInternal hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    protected boolean initialized;
    protected int interval;
    protected boolean originAtCenter;
    protected float speed;
    protected double userHeading;
    private final Set<Sprite> vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    protected boolean visible;
    protected double xCenter;
    protected double xLeft;
    protected double yCenter;
    protected double yTop;
    protected double zLayer;

    /* access modifiers changed from: protected */
    public abstract void onDraw(Canvas canvas2);

    protected Sprite(ComponentContainer componentContainer, Handler handler) {
        this.initialized = false;
        this.visible = true;
        this.androidUIHandler = handler;
        if (componentContainer instanceof Canvas) {
            Canvas canvas2 = (Canvas) componentContainer;
            this.canvas = canvas2;
            canvas2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this);
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new HashSet();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new TimerInternal(this, true, 100, handler);
            this.form = componentContainer.$form();
            OriginAtCenter(false);
            Heading(0.0d);
            Enabled(true);
            Interval(100);
            Speed(0.0f);
            Visible(true);
            Z(1.0d);
            componentContainer.$form().registerForOnDestroy(this);
            return;
        }
        throw new IllegalArgumentError("Sprite constructor called with container ".concat(String.valueOf(componentContainer)));
    }

    protected Sprite(ComponentContainer componentContainer) {
        this(componentContainer, new Handler());
    }

    public void Initialize() {
        this.initialized = true;
        this.canvas.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this);
    }

    @SimpleProperty(description = "Controls whether the %type% moves and can be interacted with through collisions, dragging, touching, and flinging.")
    public boolean Enabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(z);
    }

    @DesignerProperty(defaultValue = "0", editorType = "float")
    @SimpleProperty
    public void Heading(double d) {
        this.userHeading = d;
        double d2 = -d;
        this.heading = d2;
        double radians = Math.toRadians(d2);
        this.headingRadians = radians;
        this.headingCos = Math.cos(radians);
        this.headingSin = Math.sin(this.headingRadians);
        registerChange();
    }

    @SimpleProperty(description = "Returns the %type%'s heading in degrees above the positive x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the top of the screen.")
    public double Heading() {
        return this.userHeading;
    }

    @SimpleProperty(description = "The interval in milliseconds at which the %type%'s position is updated.  For example, if the interval is 50 and the speed is 10, then every 50 milliseconds the sprite will move 10 pixels in the heading direction.")
    public int Interval() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval();
    }

    @DesignerProperty(defaultValue = "100", editorType = "non_negative_integer")
    @SimpleProperty
    public void Interval(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Interval(i);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty(description = "The number of pixels that the %type% should move every interval, if enabled.")
    public void Speed(float f) {
        this.speed = f;
    }

    @SimpleProperty(description = "The speed at which the %type% moves. The %type% moves this many pixels every interval if enabled.")
    public float Speed() {
        return this.speed;
    }

    @SimpleProperty(description = "Whether the %type% is visible.")
    public boolean Visible() {
        return this.visible;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Visible(boolean z) {
        this.visible = z;
        registerChange();
    }

    public double X() {
        return this.originAtCenter ? this.xCenter : this.xLeft;
    }

    private double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(double d) {
        return d + ((double) (Width() / 2));
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private void m4hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(double d) {
        if (this.originAtCenter) {
            this.xCenter = d;
            this.xLeft = d - ((double) (Width() / 2));
            return;
        }
        this.xLeft = d;
        this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public void X(double d) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
        registerChange();
    }

    private double B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(double d) {
        return d + ((double) (Width() / 2));
    }

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other method in class */
    private void m3B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(double d) {
        if (this.originAtCenter) {
            this.yCenter = d;
            this.yTop = d - ((double) (Width() / 2));
            return;
        }
        this.yTop = d;
        this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d);
    }

    @DesignerProperty(defaultValue = "0.0", editorType = "float")
    @SimpleProperty
    public void Y(double d) {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d);
        registerChange();
    }

    public double Y() {
        return this.originAtCenter ? this.yCenter : this.yTop;
    }

    @DesignerProperty(defaultValue = "1.0", editorType = "float")
    @SimpleProperty
    public void Z(double d) {
        this.zLayer = d;
        Canvas canvas2 = this.canvas;
        canvas2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this);
        canvas2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this);
        canvas2.f60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.invalidate();
    }

    @SimpleProperty(description = "How the %type% should be layered relative to other Balls and ImageSprites, with higher-numbered layers in front of lower-numbered layers.")
    public double Z() {
        return this.zLayer;
    }

    /* access modifiers changed from: protected */
    public void OriginAtCenter(boolean z) {
        this.originAtCenter = z;
    }

    /* access modifiers changed from: protected */
    public void postEvent(final Sprite sprite, final String str, final Object... objArr) {
        this.androidUIHandler.post(new Runnable() {
            public final void run() {
                EventDispatcher.dispatchEvent(sprite, str, objArr);
            }
        });
    }

    @SimpleEvent
    public void CollidedWith(Sprite sprite) {
        if (!this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.contains(sprite)) {
            this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.add(sprite);
            postEvent(this, "CollidedWith", sprite);
        }
    }

    @SimpleEvent(description = "Event handler called when a %type% is dragged. On all calls, the starting coordinates are where the screen was first touched, and the \"current\" coordinates describe the endpoint of the current line segment. On the first call within a given drag, the \"previous\" coordinates are the same as the starting coordinates; subsequently, they are the \"current\" coordinates from the prior call. Note that the %type% won't actually move anywhere in response to the Dragged event unless MoveTo is explicitly called. For smooth movement, each of its coordinates should be set to the sum of its initial value and the difference between its current and previous values.")
    public void Dragged(float f, float f2, float f3, float f4, float f5, float f6) {
        postEvent(this, "Dragged", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f6));
    }

    @SimpleEvent(description = "Event handler called when the %type% reaches an edge of the screen. If Bounce is then called with that edge, the %type% will appear to bounce off of the edge it reached. Edge here is represented as an integer that indicates one of eight directions north (1), northeast (2), east (3), southeast (4), south (-1), southwest (-2), west (-3), and northwest (-4).")
    public void EdgeReached(@Options(Direction.class) int i) {
        Direction fromUnderlyingValue = Direction.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            EdgeReachedAbstract(fromUnderlyingValue);
        }
    }

    public void EdgeReachedAbstract(Direction direction) {
        postEvent(this, "EdgeReached", direction.toUnderlyingValue());
    }

    @SimpleEvent(description = "Event handler called when a pair of sprites (Balls and ImageSprites) are no longer colliding.")
    public void NoLongerCollidingWith(Sprite sprite) {
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.remove(sprite);
        postEvent(this, "NoLongerCollidingWith", sprite);
    }

    @SimpleEvent(description = "Event handler called when the user touches an enabled %type% and then immediately lifts their finger. The provided x and y coordinates are relative to the upper left of the canvas.")
    public void Touched(float f, float f2) {
        postEvent(this, "Touched", Float.valueOf(f), Float.valueOf(f2));
    }

    @SimpleEvent(description = "Event handler called when a fling gesture (quick swipe) is made on an enabled %type%. This provides the x and y coordinates of the start of the fling (relative to the upper left of the canvas), the speed (pixels per millisecond), the heading (0-360 degrees), and the x and y velocity components of the fling's vector.")
    public void Flung(float f, float f2, float f3, float f4, float f5, float f6) {
        postEvent(this, "Flung", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5), Float.valueOf(f6));
    }

    @SimpleEvent(description = "Event handler called when the user stops touching an enabled %type% (lifting their finger after a TouchDown event). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchUp(float f, float f2) {
        postEvent(this, "TouchUp", Float.valueOf(f), Float.valueOf(f2));
    }

    @SimpleEvent(description = "Event handler called when the user begins touching an enabled %type% (placing their finger on a %type% and leaving it there). This provides the x and y coordinates of the touch, relative to the upper left of the canvas.")
    public void TouchDown(float f, float f2) {
        postEvent(this, "TouchDown", Float.valueOf(f), Float.valueOf(f2));
    }

    @SimpleFunction(description = "Makes the %type% bounce, as if off a wall. For normal bouncing, the edge argument should be the one returned by EdgeReached.")
    public void Bounce(@Options(Direction.class) int i) {
        Direction fromUnderlyingValue = Direction.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            BounceAbstract(fromUnderlyingValue);
        }
    }

    public void BounceAbstract(Direction direction) {
        MoveIntoBounds();
        double d = this.userHeading % 360.0d;
        if (d < 0.0d) {
            d += 360.0d;
        }
        if ((direction == Direction.East && (d < 90.0d || d > 270.0d)) || (direction == Direction.West && d > 90.0d && d < 270.0d)) {
            Heading(180.0d - d);
        } else if ((direction == Direction.North && d > 0.0d && d < 180.0d) || (direction == Direction.South && d > 180.0d)) {
            Heading(360.0d - d);
        } else if ((direction == Direction.Northeast && d > 0.0d && d < 90.0d) || ((direction == Direction.Northwest && d > 90.0d && d < 180.0d) || ((direction == Direction.Southwest && d > 180.0d && d < 270.0d) || (direction == Direction.Southeast && d > 270.0d)))) {
            Heading(d + 180.0d);
        }
    }

    @SimpleFunction(description = "Indicates whether a collision has been registered between this %type% and the passed sprite (Ball or ImageSprite).")
    public boolean CollidingWith(Sprite sprite) {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.contains(sprite);
    }

    @SimpleFunction(description = "Moves the %type% back in bounds if part of it extends out of bounds, having no effect otherwise. If the %type% is too wide to fit on the canvas, this aligns the left side of the %type% with the left side of the canvas. If the %type% is too tall to fit on the canvas, this aligns the top side of the %type% with the top side of the canvas.")
    public void MoveIntoBounds() {
        moveIntoBounds(this.canvas.Width(), this.canvas.Height());
    }

    public void MoveTo(double d, double d2) {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d2);
        registerChange();
    }

    @SimpleFunction(description = "Turns the %type% to point towards a designated target sprite (Ball or ImageSprite). The new heading will be parallel to the line joining the centerpoints of the two sprites.")
    public void PointTowards(Sprite sprite) {
        Heading(-Math.toDegrees(Math.atan2(sprite.yCenter - this.yCenter, sprite.xCenter - this.xCenter)));
    }

    @SimpleFunction(description = "Sets the heading of the %type% toward the point with the coordinates (x, y).")
    public void PointInDirection(double d, double d2) {
        Heading(-Math.toDegrees(Math.atan2(d2 - this.yCenter, d - this.xCenter)));
    }

    /* access modifiers changed from: protected */
    public void registerChange() {
        if (!this.initialized) {
            this.canvas.getView().invalidate();
            return;
        }
        Direction hitEdgeAbstract = hitEdgeAbstract();
        if (hitEdgeAbstract != null) {
            EdgeReachedAbstract(hitEdgeAbstract);
        }
        this.canvas.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this);
    }

    /* access modifiers changed from: protected */
    public int hitEdge() {
        Direction hitEdgeAbstract = hitEdgeAbstract();
        if (hitEdgeAbstract == null) {
            return 0;
        }
        return hitEdgeAbstract.toUnderlyingValue().intValue();
    }

    /* access modifiers changed from: protected */
    public int hitEdge(int i, int i2) {
        Direction hitEdgeAbstract = hitEdgeAbstract(i, i2);
        if (hitEdgeAbstract == null) {
            return 0;
        }
        return hitEdgeAbstract.toUnderlyingValue().intValue();
    }

    /* access modifiers changed from: protected */
    public Direction hitEdgeAbstract() {
        if (!this.canvas.ready()) {
            return null;
        }
        return hitEdgeAbstract(this.canvas.Width(), this.canvas.Height());
    }

    /* access modifiers changed from: protected */
    public Direction hitEdgeAbstract(int i, int i2) {
        boolean vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq2 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq();
        boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO();
        boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i);
        boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(i2);
        if (!hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO && !wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou && !B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T && !vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq2) {
            return null;
        }
        MoveIntoBounds();
        if (vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq2) {
            if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
                return Direction.Northwest;
            }
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                return Direction.Southwest;
            }
            return Direction.West;
        } else if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T) {
            if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
                return Direction.Northeast;
            }
            if (wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) {
                return Direction.Southeast;
            }
            return Direction.East;
        } else if (hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) {
            return Direction.North;
        } else {
            return Direction.South;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void moveIntoBounds(int r7, int r8) {
        /*
            r6 = this;
            int r0 = r6.Width()
            r1 = 1
            r2 = 0
            if (r0 <= r7) goto L_0x0018
            double r4 = r6.xLeft
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 == 0) goto L_0x003d
            r6.xLeft = r2
            double r4 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((double) r2)
            r6.xCenter = r4
            goto L_0x0026
        L_0x0018:
            boolean r0 = r6.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq()
            if (r0 == 0) goto L_0x0028
            r6.xLeft = r2
            double r4 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((double) r2)
            r6.xCenter = r4
        L_0x0026:
            r7 = 1
            goto L_0x003e
        L_0x0028:
            boolean r0 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((int) r7)
            if (r0 == 0) goto L_0x003d
            int r0 = r6.Width()
            int r7 = r7 - r0
            double r4 = (double) r7
            r6.xLeft = r4
            double r4 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((double) r4)
            r6.xCenter = r4
            goto L_0x0026
        L_0x003d:
            r7 = 0
        L_0x003e:
            int r0 = r6.Height()
            if (r0 <= r8) goto L_0x0053
            double r4 = r6.yTop
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x0077
            r6.yTop = r2
            double r7 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((double) r2)
            r6.yCenter = r7
            goto L_0x0078
        L_0x0053:
            boolean r0 = r6.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO()
            if (r0 == 0) goto L_0x0062
            r6.yTop = r2
            double r7 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((double) r2)
            r6.yCenter = r7
            goto L_0x0078
        L_0x0062:
            boolean r0 = r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(r8)
            if (r0 == 0) goto L_0x0077
            int r7 = r6.Height()
            int r8 = r8 - r7
            double r7 = (double) r8
            r6.yTop = r7
            double r7 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T((double) r7)
            r6.yCenter = r7
            goto L_0x0078
        L_0x0077:
            r1 = r7
        L_0x0078:
            if (r1 == 0) goto L_0x007d
            r6.registerChange()
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Sprite.moveIntoBounds(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void updateCoordinates() {
        double d = this.xLeft + (((double) this.speed) * this.headingCos);
        this.xLeft = d;
        this.xCenter = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(d);
        double d2 = this.yTop + (((double) this.speed) * this.headingSin);
        this.yTop = d2;
        this.yCenter = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(d2);
    }

    private final boolean vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq() {
        return this.xLeft < 0.0d;
    }

    private final boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        return this.xLeft + ((double) Width()) > ((double) i);
    }

    private final boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO() {
        return this.yTop < 0.0d;
    }

    private final boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(int i) {
        return this.yTop + ((double) Height()) > ((double) i);
    }

    public BoundingBox getBoundingBox(int i) {
        double d = this.xLeft;
        double d2 = (double) i;
        return new BoundingBox(d - d2, this.yTop - d2, ((d + ((double) Width())) - 1.0d) + d2, ((this.yTop + ((double) Height())) - 1.0d) + d2);
    }

    public static boolean colliding(Sprite sprite, Sprite sprite2) {
        BoundingBox boundingBox = sprite.getBoundingBox(1);
        if (!boundingBox.intersectDestructively(sprite2.getBoundingBox(1))) {
            return false;
        }
        for (double left = boundingBox.getLeft(); left <= boundingBox.getRight(); left += 1.0d) {
            for (double top = boundingBox.getTop(); top <= boundingBox.getBottom(); top += 1.0d) {
                if (sprite.containsPoint(left, top) && sprite2.containsPoint(left, top)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean intersectsWith(BoundingBox boundingBox) {
        BoundingBox boundingBox2 = getBoundingBox(0);
        if (!boundingBox2.intersectDestructively(boundingBox)) {
            return false;
        }
        for (double left = boundingBox2.getLeft(); left < boundingBox2.getRight(); left += 1.0d) {
            for (double top = boundingBox2.getTop(); top < boundingBox2.getBottom(); top += 1.0d) {
                if (containsPoint(left, top)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsPoint(double d, double d2) {
        double d3 = this.xLeft;
        if (d < d3 || d >= d3 + ((double) Width())) {
            return false;
        }
        double d4 = this.yTop;
        return d2 >= d4 && d2 < d4 + ((double) Height());
    }

    public void alarm() {
        if (this.initialized && this.speed != 0.0f) {
            updateCoordinates();
            registerChange();
        }
    }

    public HandlesEventDispatching getDispatchDelegate() {
        return this.canvas.$form();
    }

    public void onDestroy() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(false);
    }

    public void onDelete() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Enabled(false);
        this.canvas.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this);
    }
}
