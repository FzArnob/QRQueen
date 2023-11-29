package gnu.kawa.swingviews;

import gnu.kawa.models.Display;
import gnu.kawa.models.Model;
import java.awt.Component;

/* compiled from: SwingDisplay */
class ComponentModel extends Model {
    Component component;

    public ComponentModel(Component component2) {
        this.component = component2;
    }

    public void makeView(Display display, Object obj) {
        display.addView(this.component, obj);
    }
}
