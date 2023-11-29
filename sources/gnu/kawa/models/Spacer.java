package gnu.kawa.models;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.io.Serializable;

public class Spacer extends Model implements Viewable, Serializable {
    Dimension2D maxSize;
    Dimension2D minSize;
    Dimension2D preferredSize;

    public Dimension2D getMinimumSize2D() {
        return this.minSize;
    }

    public Dimension2D getPreferredSize2D() {
        return this.preferredSize;
    }

    public Dimension2D getMaximumSize2D() {
        return this.maxSize;
    }

    public Dimension getMinimumSize() {
        return Display.asDimension(this.minSize);
    }

    public Dimension getPreferredSize() {
        return Display.asDimension(this.preferredSize);
    }

    public Dimension getMaximumSize() {
        return Display.asDimension(this.maxSize);
    }

    public boolean isRigid() {
        Dimension2D dimension2D = this.minSize;
        Dimension2D dimension2D2 = this.maxSize;
        if (dimension2D == dimension2D2) {
            return true;
        }
        if (dimension2D == null || dimension2D2 == null || dimension2D.getWidth() != this.maxSize.getWidth() || this.minSize.getHeight() != this.maxSize.getHeight()) {
            return false;
        }
        return true;
    }

    public static Spacer rigidArea(Dimension2D dimension2D) {
        Spacer spacer = new Spacer();
        spacer.minSize = dimension2D;
        spacer.maxSize = dimension2D;
        spacer.preferredSize = dimension2D;
        return spacer;
    }

    public static Spacer rigidArea(int i, int i2) {
        return rigidArea(new Dimension(i, i2));
    }

    public void makeView(Display display, Object obj) {
        display.addSpacer(this, obj);
    }
}
