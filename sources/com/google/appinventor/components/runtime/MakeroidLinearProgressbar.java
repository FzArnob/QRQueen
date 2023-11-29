package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", version = 1)
public final class MakeroidLinearProgressbar extends AndroidViewComponent {
    private int MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = -14575886;
    private Context context;
    private ProgressBar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = -14575886;

    public final void HeightPercent(int i) {
    }

    public MakeroidLinearProgressbar(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new ProgressBar(this.context, (AttributeSet) null, 16842872);
        componentContainer.$add(this);
        Minimum(0);
        Maximum(100);
        ProgressColor(-14575886);
        IndeterminateColor(-14575886);
        Indeterminate(true);
        Width(-2);
        Log.d("Makeroid Progress bar", "Makeroid Progress bar created");
    }

    public final ProgressBar getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public final int Height() {
        return getView().getHeight();
    }

    public final void Height(int i) {
        this.container.setChildHeight(this, i);
    }

    @DesignerProperty(defaultValue = "0", editorType = "integer")
    @SimpleProperty(description = "Set the lower range of the progress bar to min. This function works only for devices with API >= 26")
    public final void Minimum(int i) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMin(i);
            Log.i("Makeroid Progress bar", "setMin = ".concat(String.valueOf(i)));
            return;
        }
        Log.i("Makeroid Progress bar", "setMin of progress bar is not possible. API is " + Build.VERSION.SDK_INT);
    }

    @SimpleProperty
    public final int Minimum() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMin();
        }
        return 0;
    }

    @DesignerProperty(defaultValue = "100", editorType = "integer")
    @SimpleProperty(description = "Set the upper range of the progress bar max.")
    public final void Maximum(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMax(i);
        Log.i("Makeroid Progress bar", "setMax = ".concat(String.valueOf(i)));
    }

    @SimpleProperty
    public final int Maximum() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMax();
    }

    @SimpleProperty(description = "Sets the current progress to the specified value. Does not do anything if the progress bar is in indeterminate mode.")
    public final void Progress(int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgress(i, true);
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setProgress(i);
        }
        ProgressChanged(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress());
    }

    @SimpleProperty(description = "Get the progress bar's current level of progress.")
    public final int Progress() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress();
    }

    @SimpleFunction(description = "Increase the progress bar's progress by the specified amount.")
    public final void IncrementProgressBy(int i) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.incrementProgressBy(i);
        ProgressChanged(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgress());
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty(description = "Change the progress color of the progress bar.")
    public final void ProgressColor(int i) {
        this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProgressDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
        Log.i("Makeroid Progress bar", "Progress Color = ".concat(String.valueOf(i)));
    }

    @SimpleProperty
    public final int ProgressColor() {
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty(description = "Change the indeterminate color of the progress bar.")
    public final void IndeterminateColor(int i) {
        this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getIndeterminateDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
        Log.i("Makeroid Progress bar", "Indeterminate Color = ".concat(String.valueOf(i)));
    }

    @SimpleProperty
    public final int IndeterminateColor() {
        return this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Change the indeterminate mode for this progress bar. In indeterminate mode, the progress is ignored and the progress bar shows an infinite animation instead.")
    public final void Indeterminate(boolean z) {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setIndeterminate(z);
        Log.i("Makeroid Progress bar", "Indeterminate is: ".concat(String.valueOf(z)));
    }

    @SimpleProperty(description = "Indicate whether this progress bar is in indeterminate mode.")
    public final boolean Indeterminate() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isIndeterminate();
    }

    @SimpleEvent(description = "Event that indicates that the progress of the progress bar has been changed. Returns the current progress value. If \"Indeterminate\" is set to true, then it returns \"0\".")
    public final void ProgressChanged(int i) {
        EventDispatcher.dispatchEvent(this, "ProgressChanged", Integer.valueOf(i));
    }
}
