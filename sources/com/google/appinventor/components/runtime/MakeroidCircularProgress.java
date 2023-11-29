package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "", version = 1)
public final class MakeroidCircularProgress extends AndroidViewComponent {
    private LinearLayout.LayoutParams B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private int MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = -14575886;
    private Context context;
    private ProgressBar hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public MakeroidCircularProgress(ComponentContainer componentContainer) {
        super(componentContainer);
        this.context = componentContainer.$context();
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new LinearLayout.LayoutParams(-2, -2);
        ProgressBar progressBar = new ProgressBar(this.context, (AttributeSet) null, 16842871);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = progressBar;
        progressBar.setLayoutParams(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        componentContainer.$add(this);
        Color(-14575886);
        Log.d("Makeroid Progress bar circular ", "Makeroid Progress bar circular created");
    }

    public final ProgressBar getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @DesignerProperty(defaultValue = "&HFF2196F2", editorType = "color")
    @SimpleProperty(description = "Change the indeterminate color of the circular progress bar.")
    public final void Color(int i) {
        this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getIndeterminateDrawable().setColorFilter(i, PorterDuff.Mode.SRC_IN);
        Log.i("Makeroid Progress bar circular ", "Indeterminate Color = ".concat(String.valueOf(i)));
    }

    @SimpleProperty
    public final int Color() {
        return this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0;
    }
}
