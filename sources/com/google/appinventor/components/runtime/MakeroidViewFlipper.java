package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.VIEWS, description = "", iconName = "images/viewFlipper.png", version = 1)
public class MakeroidViewFlipper extends AndroidViewComponent implements View.OnTouchListener {
    private int backgroundColor;
    private ComponentContainer container;
    private Context context;
    private ViewFlipper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int interval;
    private boolean swipeable;

    public MakeroidViewFlipper(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        ViewFlipper viewFlipper = new ViewFlipper(this.context);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = viewFlipper;
        viewFlipper.setOnTouchListener(this);
        Animation loadAnimation = AnimationUtils.loadAnimation(this.context, 17432576);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.context, 17432577);
        loadAnimation.setDuration(200);
        loadAnimation2.setDuration(200);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setInAnimation(loadAnimation);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOutAnimation(loadAnimation2);
        componentContainer.$add(this);
        AddImagesFromString("");
        BackgroundColor(0);
        FlipInterval(1000);
        Swipeable(true);
        Log.d("Makeroid View Flipper", "Makeroid View Flipper Created");
    }

    public ViewFlipper getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty
    public void Width(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Width(i);
    }

    @SimpleProperty
    public void Height(int i) {
        if (i == -1) {
            i = -2;
        }
        super.Height(i);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 1) {
            float x = motionEvent.getX();
            if (0.0f < x) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showNext();
            }
            if (0.0f > x) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showPrevious();
            }
        }
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If set to true you can swipe with your fingers through views.")
    public void Swipeable(boolean z) {
        this.swipeable = z;
    }

    @SimpleProperty
    public boolean Swipeable() {
        return this.swipeable;
    }

    @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
    @SimpleProperty(description = "How long to wait before flipping to the next view in milliseconds.")
    public void FlipInterval(int i) {
        this.interval = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setFlipInterval(i);
    }

    @SimpleProperty
    public int FlipInterval() {
        return this.interval;
    }

    @SimpleFunction(description = "Start a timer to cycle through child views.")
    public void StartFlipping() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startFlipping();
    }

    @SimpleFunction(description = "No more flips.")
    public void StopFlipping() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.stopFlipping();
    }

    @SimpleFunction(description = "Show the previous view.")
    public void ShowPrevious() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showPrevious();
    }

    @SimpleFunction(description = "Show the next view.")
    public void ShowNext() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.showNext();
    }

    @SimpleFunction(description = "Returns true if the child views are flipping.")
    public void isFlipping() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isFlipping();
    }

    @SimpleFunction(description = "Add a component to the view flipper. The first added component will be the first visible component on the screen.")
    public void AddComponentToView(AndroidViewComponent androidViewComponent) {
        try {
            View view = androidViewComponent.getView();
            ((ViewGroup) view.getParent()).removeView(view);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(view);
        } catch (Exception e) {
            Log.e("Makeroid View Flipper", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Use this function if you try to create a image view flipper. Please use a 'make a list' block.")
    public void AddImagesToView(YailList yailList) {
        String[] stringArray = yailList.toStringArray();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int i = 0;
        while (i < yailList.size()) {
            try {
                ImageView imageView = new ImageView(this.context);
                imageView.setImageDrawable(MediaUtil.getBitmapDrawable(this.container.$form(), stringArray[i]));
                imageView.setAdjustViewBounds(true);
                imageView.setLayoutParams(layoutParams);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addView(imageView);
                i++;
            } catch (Exception unused) {
                Log.e("Makeroid View Flipper", "Unable to load " + stringArray[i]);
                return;
            }
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "textArea")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Use this function if you try to create a image view flipper. Use images separated by commas such as: Image1.png,Image2.png,Image3.png.")
    public void AddImagesFromString(String str) {
        if (str.length() > 0) {
            AddImagesToView(YailList.makeList((Object[]) str.split(" *, *")));
        }
    }

    @DesignerProperty(defaultValue = "&H00000000", editorType = "color")
    @SimpleProperty(description = "Specifies the view flippers background color.")
    public void BackgroundColor(int i) {
        this.backgroundColor = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setBackgroundColor(i);
    }

    @SimpleProperty
    public int BackgroundColor() {
        return this.backgroundColor;
    }
}
