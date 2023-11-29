package gnu.kawa.swingviews;

import gnu.kawa.models.Display;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import gnu.kawa.models.Viewable;
import javax.swing.Box;

/* compiled from: SwingDisplay */
class SwingBox extends Box implements ModelListener {
    gnu.kawa.models.Box model;

    public void modelUpdated(Model model2, Object obj) {
    }

    public SwingBox(gnu.kawa.models.Box box, Display display) {
        super(box.getAxis());
        box.addListener((ModelListener) this);
        Viewable cellSpacing = box.getCellSpacing();
        int componentCount = box.getComponentCount();
        for (int i = 0; i < componentCount; i++) {
            if (i > 0 && cellSpacing != null) {
                cellSpacing.makeView(display, this);
            }
            box.getComponent(i).makeView(display, this);
        }
    }
}
