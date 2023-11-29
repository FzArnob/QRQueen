package com.google.appinventor.components.runtime;

import android.animation.Animator;
import android.content.Context;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.lottie.LottieAnimationView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.InputStreamReader;

@SimpleObject
@DesignerComponent(category = ComponentCategory.ANIMATION, description = "A new component ", iconName = "images/makeroidLottie.png", nonVisible = false, version = 2)
@UsesLibraries({"lottie.aar", "lottie.jar"})
public final class KodularLottie extends AndroidViewComponent {
    private String ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = "";
    private boolean ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = true;
    /* access modifiers changed from: private */
    public boolean clickable = false;
    private Context context;
    private LottieAnimationView hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean isCompanion = false;
    private float moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = 1.0f;

    public KodularLottie(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        if (componentContainer.$form() instanceof ReplForm) {
            this.isCompanion = true;
        }
        LottieAnimationView lottieAnimationView = new LottieAnimationView(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = lottieAnimationView;
        lottieAnimationView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addAnimatorListener(new Animator.AnimatorListener() {
            public final void onAnimationCancel(Animator animator) {
            }

            public final void onAnimationStart(Animator animator) {
                KodularLottie.this.AnimationStart();
            }

            public final void onAnimationEnd(Animator animator) {
                KodularLottie.this.AnimationEnd();
            }

            public final void onAnimationRepeat(Animator animator) {
                KodularLottie.this.AnimationRepeat();
            }
        });
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                if (KodularLottie.this.clickable) {
                    KodularLottie.this.Click();
                }
            }
        });
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnLongClickListener(new View.OnLongClickListener() {
            public final boolean onLongClick(View view) {
                if (KodularLottie.this.clickable) {
                    KodularLottie.this.LongClick();
                }
                return KodularLottie.this.clickable;
            }
        });
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.playAnimation();
        componentContainer.$add(this);
        Height(-1);
        Width(-1);
        Source(this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio);
        LoopAnimation(this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd);
        AnimationSpeed(this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
        Clickable(true);
        Log.d("Lottie", "Lottie Created");
    }

    @DesignerProperty(editorType = "asset")
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Set the file or url of the file you want to load.")
    public final void Source(String str) {
        this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = str;
        try {
            if (!str.isEmpty()) {
                if (!str.startsWith("http://")) {
                    if (!str.startsWith("https://")) {
                        if (this.isCompanion) {
                            try {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimation(new JsonReader(new InputStreamReader(FileUtil.openFile(this.container.$form(), MediaUtil.getAssetFilePath(this.container.$form(), str)))), (String) null);
                                return;
                            } catch (Exception e) {
                                Log.e("Lottie", String.valueOf(e));
                                return;
                            }
                        } else {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimation(str);
                            return;
                        }
                    }
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAnimationFromUrl(str);
            }
        } catch (Exception e2) {
            Log.e("Lottie", String.valueOf(e2));
        }
    }

    @SimpleProperty(description = "Get the file or url of the file that you loaded.")
    public final String Source() {
        return this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set to true if you want to loop the animation.")
    public final void LoopAnimation(boolean z) {
        this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd = z;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRepeatCount(z ? -1 : 0);
    }

    @SimpleProperty(description = "Get whether the animation loops.")
    public final boolean LoopAnimation() {
        return this.ATk09PTFIUWb1DqQXgMu0NmsbfZK89sTID9U9fCgIZdoNsjPWnKT9boGyGQNlMd;
    }

    @DesignerProperty(defaultValue = "1", editorType = "float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the speed of the animation.")
    public final void AnimationSpeed(float f) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSpeed(f);
    }

    @SimpleProperty(description = "Get the speed of the animation.")
    public final float AnimationSpeed() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getSpeed();
    }

    @SimpleFunction(description = "Set the frame where you want the animation to start.")
    public final void StartFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMinFrame(i);
    }

    @SimpleFunction(description = "Set the frame where you want the animation to end.")
    public final void EndFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMaxFrame(i);
    }

    @SimpleFunction(description = "Set the frame where you want the animation to be now.")
    public final void SetCurrentFrame(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFrame(i);
    }

    @SimpleFunction(description = "Get the frame where the animation is now.")
    public final int GetCurrentFrame() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getFrame();
    }

    @SimpleFunction(description = "Get the count of frames in the animation.")
    public final int GetFrameCount() {
        return (int) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaxFrame();
    }

    @SimpleFunction(description = "Resume the animation on the Lottie component.")
    public final void ResumeAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.resumeAnimation();
    }

    @SimpleFunction(description = "Pause the animation on the Lottie component.")
    public final void PauseAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.pauseAnimation();
    }

    @SimpleFunction(description = "Start the animation on the Lottie component.")
    public final void StartAnimation() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.playAnimation();
    }

    @SimpleFunction(description = "Check if the Lottie component is animating.")
    public final boolean IsAnimating() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAnimating();
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Set the component clickable or not clickable.")
    public final void Clickable(boolean z) {
        this.clickable = z;
    }

    @SimpleProperty(description = "Returns true if the component is clickable.")
    public final boolean Clickable() {
        return this.clickable;
    }

    @SimpleEvent(description = "Triggers when the components was clicked.")
    public final void Click() {
        EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the components was long clicked.")
    public final void LongClick() {
        EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation starts.")
    public final void AnimationStart() {
        EventDispatcher.dispatchEvent(this, "AnimationStart", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation ends.")
    public final void AnimationEnd() {
        EventDispatcher.dispatchEvent(this, "AnimationEnd", new Object[0]);
    }

    @SimpleEvent(description = "Triggers when the animation repeated.")
    public final void AnimationRepeat() {
        EventDispatcher.dispatchEvent(this, "AnimationRepeat", new Object[0]);
    }

    public final View getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }
}
