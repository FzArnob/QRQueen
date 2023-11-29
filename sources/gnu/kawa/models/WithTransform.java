package gnu.kawa.models;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class WithTransform implements Paintable {
    Paintable paintable;
    AffineTransform transform;

    public WithTransform(Paintable paintable2, AffineTransform affineTransform) {
        this.paintable = paintable2;
        this.transform = affineTransform;
    }

    public void paint(Graphics2D graphics2D) {
        AffineTransform transform2 = graphics2D.getTransform();
        try {
            graphics2D.transform(this.transform);
            this.paintable.paint(graphics2D);
        } finally {
            graphics2D.setTransform(transform2);
        }
    }

    public Rectangle2D getBounds2D() {
        return this.transform.createTransformedShape(this.paintable.getBounds2D()).getBounds2D();
    }

    public Paintable transform(AffineTransform affineTransform) {
        AffineTransform affineTransform2 = new AffineTransform(this.transform);
        affineTransform2.concatenate(affineTransform);
        return new WithTransform(this.paintable, affineTransform2);
    }
}
