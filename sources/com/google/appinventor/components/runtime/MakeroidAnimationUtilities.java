package com.google.appinventor.components.runtime;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", helpUrl = "https://docs.kodular.io/components/drawing-and-animation/animation-util/", iconName = "images/animationUtil.png", nonVisible = true, version = 1)
public class MakeroidAnimationUtilities extends AndroidNonvisibleComponent implements Component {
    private boolean ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = false;
    private float J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = 0.0f;
    private Context context;

    public MakeroidAnimationUtilities(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("Makeroid Animation Utilities", "Makeroid Animation Utilities Created");
    }

    @SimpleEvent(description = "This event is triggered when there was a error catched. Possible values for the error code and method: 1 'GetLeftPosition', 2 'GetTopPosition', 3 'GetRightPosition', 4 'GetBottomPosition', 5 'GetXPosition', 6 'GetYPosition', 7 'Rotation', 8 'BounceHorizontal', 9 'BounceVertical', 10 'OvershootHorizontal', 11 'OvershootVertical', 12 'Zoom'. The error message will return you the error reason.")
    public void Error(int i, String str, String str2) {
        EventDispatcher.dispatchEvent(this, "Error", Integer.valueOf(i), str, str2);
    }

    @SimpleFunction(description = "Returns the left position of a component. It will return '-9999' if there was a error.")
    public int GetLeftPosition(AndroidViewComponent androidViewComponent) {
        try {
            return androidViewComponent.getView().getLeft();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(1, e.getMessage(), "GetLeftPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the top position of a component. It will return '-9999' if there was a error.")
    public int GetTopPosition(AndroidViewComponent androidViewComponent) {
        try {
            return androidViewComponent.getView().getTop();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(2, e.getMessage(), "GetTopPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the right position of a component. It will return '-9999' if there was a error.")
    public int GetRightPosition(AndroidViewComponent androidViewComponent) {
        try {
            return androidViewComponent.getView().getRight();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(3, e.getMessage(), "GetRightPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the bottom position of a component. It will return '-9999' if there was a error.")
    public int GetBottomPosition(AndroidViewComponent androidViewComponent) {
        try {
            return androidViewComponent.getView().getBottom();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(4, e.getMessage(), "GetBottomPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the x position of a component. It will return '-9999' if there was a error.")
    public int GetXPosition(AndroidViewComponent androidViewComponent) {
        try {
            return (int) androidViewComponent.getView().getX();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(5, e.getMessage(), "GetXPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Returns the y position of a component. It will return '-9999' if there was a error.")
    public int GetYPosition(AndroidViewComponent androidViewComponent) {
        try {
            return (int) androidViewComponent.getView().getY();
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(6, e.getMessage(), "GetYPosition");
            return -9999;
        }
    }

    @SimpleFunction(description = "Start a rotation on any component. Use as example in 'rotation Start Degrees' 0, in 'rotation End Degrees' 360 and in 'duration' 300 (millisecond) to run a clockwise, 360 degress animation. You can also use negative numbers for the degress.")
    public void Rotation(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        try {
            View view = androidViewComponent.getView();
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "rotation", f, f2, i);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(7, e.getMessage(), "Rotation");
        }
    }

    @SimpleFunction(description = "Start a horizontal bounce animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void BounceHorizontal(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        try {
            View view = androidViewComponent.getView();
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = true;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationX", f, f2, i);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(8, e.getMessage(), "BounceHorizontal");
        }
    }

    @SimpleFunction(description = "Start a vertical bounce animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void BounceVertical(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        try {
            View view = androidViewComponent.getView();
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = true;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationY", f, f2, i);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(9, e.getMessage(), "BounceVertical");
        }
    }

    @SimpleFunction(description = "Start a horizontal overshoot animation. If 'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void OvershootHorizontal(AndroidViewComponent androidViewComponent, float f, float f2, int i, float f3) {
        try {
            View view = androidViewComponent.getView();
            this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = f3;
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationX", f, f2, i);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(10, e.getMessage(), "OvershootHorizontal");
        }
    }

    @SimpleFunction(description = "Start a vertical overshoot animation. If 'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void OvershootVertical(AndroidViewComponent androidViewComponent, float f, float f2, int i, float f3) {
        try {
            View view = androidViewComponent.getView();
            this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = f3;
            this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio = false;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(view, "translationY", f, f2, i);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(11, e.getMessage(), "OvershootVertical");
        }
    }

    @SimpleFunction(description = "Start a zoom animation.  'tension' is set to 0 you will not see a overshoot animation. Then you will see just a simple deceleration animation. The duration is set in millisecond. Use as example for 1 second '1000'.")
    public void Zoom(AndroidViewComponent androidViewComponent, float f, float f2, int i) {
        try {
            View view = androidViewComponent.getView();
            ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration((long) i);
            view.startAnimation(scaleAnimation);
        } catch (Exception e) {
            Log.e("Makeroid Animation Utilities", e.getMessage());
            Error(12, e.getMessage(), "Zoom");
        }
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(View view, String str, float f, float f2, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, str, new float[]{f, f2});
        if (!str.equals("rotation")) {
            if (this.ATBpAON767TQmZZYK3lMfxQA5S6eF21JqXY9TAJY6l4cbZGeVMlZehfHdCrTaqio) {
                ofFloat.setInterpolator(new BounceInterpolator());
            } else {
                ofFloat.setInterpolator(new OvershootInterpolator(this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU));
            }
        }
        ofFloat.setDuration((long) i);
        ofFloat.start();
    }
}
