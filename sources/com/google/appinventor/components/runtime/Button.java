package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Button with the ability to detect clicks.  Many aspects of its appearance can be changed, as well as whether it is clickable (<code>Enabled</code>), can be changed in the Designer or in the Blocks Editor.", version = 13)
public final class Button extends ButtonBase {
    public Button(ComponentContainer componentContainer) {
        super(componentContainer);
    }

    public final void click() {
        Click();
    }

    @SimpleEvent(description = "User tapped and released the button.")
    public final void Click() {
        try {
            EventDispatcher.dispatchEvent(this, "Click", new Object[0]);
        } catch (Exception e) {
            Log.e("Button", e.getMessage());
        }
    }

    public final boolean longClick() {
        return LongClick();
    }

    @SimpleEvent(description = "User held the button down.")
    public final boolean LongClick() {
        return EventDispatcher.dispatchEvent(this, "LongClick", new Object[0]);
    }
}
