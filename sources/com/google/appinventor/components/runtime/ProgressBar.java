package com.google.appinventor.components.runtime;

import android.graphics.Rect;
import android.view.View;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.RotatingPlane;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.runtime.util.ProgressHelper;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", version = 4)
@UsesLibraries({"spinkit.jar"})
public final class ProgressBar extends AndroidViewComponent {
    private final android.widget.ProgressBar B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = Component.COLOR_CYAN_DAK;
    private String Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm = ComponentConstants.DEFAULT_PROGRESSBAR_ANIMATION;
    private int backgroundColor;
    private Rect hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Sprite f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public ProgressBar(ComponentContainer componentContainer) {
        super(componentContainer);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new android.widget.ProgressBar(componentContainer.$context());
        Color(Component.COLOR_CYAN_DAK);
        AnimationStyle(this.Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm);
        componentContainer.$add(this);
        BackgroundColor(16777215);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int BackgroundColor() {
        return this.backgroundColor;
    }

    @DesignerProperty(defaultValue = "&H00FFFFFF", editorType = "color")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Sets the background color of this component.")
    public final void BackgroundColor(int i) {
        this.backgroundColor = i;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setBackgroundColor(i);
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE)
    public final int Color() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @DesignerProperty(defaultValue = "&HFF008b8b", editorType = "color")
    @SimpleProperty(description = "Sets the color of the component")
    public final void Color(int i) {
        Sprite sprite = this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (sprite != null) {
            this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = i;
            sprite.setColor(i);
        }
    }

    @SimpleFunction(description = "Gets style names of all possible animations.")
    public final YailList GetAnimationStyleNames() {
        return YailList.makeList((List) ProgressHelper.getAnimationStyles());
    }

    @DesignerProperty(defaultValue = "Wave", editorType = "progress_options")
    @SimpleProperty(description = "Allows you to set animation style. Valid (case-insensitive) values are: ChasingDots, Circle, CubeGrid, DoubleBounce, FadingCircle, FoldingCube, Pulse, RotatingCircle, RotatingPlane, ThreeBounce, WanderingCubes, Wave. If invalid style is used, Wave animation will be used.")
    public final void AnimationStyle(String str) {
        Sprite sprite = getSprite(str);
        this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sprite;
        sprite.start();
        this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setColor(this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setIndeterminateDrawable(this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBounds();
        }
        this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBounds(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.left, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.top, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.right, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bottom);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.invalidateDrawable(this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public final Sprite getSprite(String str) {
        this.Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm = str;
        String replaceAll = str.toLowerCase().replaceAll("\\s", "");
        replaceAll.hashCode();
        char c = 65535;
        switch (replaceAll.hashCode()) {
            case -1360216880:
                if (replaceAll.equals("circle")) {
                    c = 0;
                    break;
                }
                break;
            case -817913340:
                if (replaceAll.equals("rotatingplane")) {
                    c = 1;
                    break;
                }
                break;
            case -741786634:
                if (replaceAll.equals("foldingcube")) {
                    c = 2;
                    break;
                }
                break;
            case 3642105:
                if (replaceAll.equals("wave")) {
                    c = 3;
                    break;
                }
                break;
            case 40036904:
                if (replaceAll.equals("rotatingcircle")) {
                    c = 4;
                    break;
                }
                break;
            case 105393403:
                if (replaceAll.equals("cubegrid")) {
                    c = 5;
                    break;
                }
                break;
            case 107027353:
                if (replaceAll.equals("pulse")) {
                    c = 6;
                    break;
                }
                break;
            case 509233141:
                if (replaceAll.equals("chasingdots")) {
                    c = 7;
                    break;
                }
                break;
            case 1143631270:
                if (replaceAll.equals("threebounce")) {
                    c = 8;
                    break;
                }
                break;
            case 1217522153:
                if (replaceAll.equals("wanderingcubes")) {
                    c = 9;
                    break;
                }
                break;
            case 1384198729:
                if (replaceAll.equals("fadingcircle")) {
                    c = 10;
                    break;
                }
                break;
            case 1471386009:
                if (replaceAll.equals("doublebounce")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new Circle();
            case 1:
                return new RotatingPlane();
            case 2:
                return new FoldingCube();
            case 3:
                return new Wave();
            case 4:
                return new RotatingCircle();
            case 5:
                return new CubeGrid();
            case 6:
                return new Pulse();
            case 7:
                return new ChasingDots();
            case 8:
                return new ThreeBounce();
            case 9:
                return new WanderingCubes();
            case 10:
                return new FadingCircle();
            case 11:
                return new DoubleBounce();
            default:
                this.Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm = ComponentConstants.DEFAULT_PROGRESSBAR_ANIMATION;
                return new Wave();
        }
    }

    @SimpleProperty(description = "Gets current animation style")
    public final String AnimationStyle() {
        return this.Sj3UMCQcT7f46E8U4TVavenawPElr174psURarVHJJBTqMXe22hIekct3fzxe7Vm;
    }

    @SimpleProperty
    public final void Height(int i) {
        super.Height(i);
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud();
    }

    @SimpleProperty
    public final void Width(int i) {
        super.Width(i);
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud();
    }

    private void ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud() {
        try {
            Rect bounds = this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getBounds();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = bounds;
            this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBounds(bounds.left, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.top, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.right, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.bottom);
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.invalidateDrawable(this.f244hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        } catch (Exception unused) {
        }
    }

    public final View getView() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }
}
