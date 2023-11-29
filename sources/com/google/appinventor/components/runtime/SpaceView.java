package com.google.appinventor.components.runtime;

import android.widget.Space;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "", helpUrl = "https://docs.kodular.io/components/layout/space/", iconName = "images/space.png", version = 1)
public final class SpaceView extends AndroidViewComponent implements Component {
    private Space hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public SpaceView(ComponentContainer componentContainer) {
        super(componentContainer);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Space(componentContainer.$context());
        componentContainer.$add(this);
    }

    public final Space getView() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }
}
