package gnu.kawa.models;

import gnu.math.IntNum;
import java.awt.Dimension;
import java.io.Serializable;

public abstract class Box extends Model implements Viewable, Serializable {
    Viewable cellSpacing;
    Viewable[] components;
    int numComponents;

    public abstract int getAxis();

    public Viewable getCellSpacing() {
        return this.cellSpacing;
    }

    public void setCellSpacing(Object obj) {
        if ((obj instanceof IntNum) || (obj instanceof Integer)) {
            int intValue = ((Number) obj).intValue();
            this.cellSpacing = Spacer.rigidArea(getAxis() == 0 ? new Dimension(intValue, 0) : new Dimension(0, intValue));
            return;
        }
        this.cellSpacing = (Viewable) obj;
    }

    public final int getComponentCount() {
        return this.numComponents;
    }

    public final Viewable getComponent(int i) {
        return this.components[i];
    }

    public void add(Viewable viewable) {
        Viewable[] viewableArr = this.components;
        int i = this.numComponents;
        if (i == 0) {
            this.components = new Viewable[4];
        } else if (viewableArr.length <= i) {
            Viewable[] viewableArr2 = new Viewable[(i * 2)];
            this.components = viewableArr2;
            System.arraycopy(viewableArr, 0, viewableArr2, 0, i);
        }
        this.components[i] = viewable;
        this.numComponents = i + 1;
    }

    public void makeView(Display display, Object obj) {
        display.addBox(this, obj);
    }
}
