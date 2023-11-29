package com.google.appinventor.components.runtime;

import android.view.View;
import android.widget.Space;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", version = 1)
public class Spacer extends AndroidViewComponent implements Component {
    private final Space B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    public Spacer(ComponentContainer componentContainer) {
        super(componentContainer);
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new Space(componentContainer.$context());
        componentContainer.$add(this);
        BackgroundColor(Component.COLOR_LIGHT_GRAY);
        Height(10);
    }

    @DesignerProperty(defaultValue = "&HFFCCCCCC", editorType = "color")
    @SimpleProperty(userVisible = false)
    public void BackgroundColor(int i) {
        getView().setBackgroundColor(i);
    }

    public View getView() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }
}
