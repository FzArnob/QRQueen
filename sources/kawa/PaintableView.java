package kawa;

import gnu.kawa.models.Paintable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.View;

/* compiled from: ReplPane */
class PaintableView extends View {
    Rectangle2D bounds;
    Paintable p;

    public PaintableView(Element element, Paintable paintable) {
        super(element);
        this.p = paintable;
        this.bounds = paintable.getBounds2D();
    }

    public void paint(Graphics graphics, Shape shape) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        Rectangle bounds2 = shape.getBounds();
        AffineTransform transform = graphics2D.getTransform();
        Paint paint = graphics2D.getPaint();
        try {
            graphics2D.translate(bounds2.x, bounds2.y);
            graphics2D.setPaint(Color.BLACK);
            this.p.paint(graphics2D);
        } finally {
            graphics2D.setTransform(transform);
            graphics2D.setPaint(paint);
        }
    }

    public float getAlignment(int i) {
        if (i != 1) {
            return PaintableView.super.getAlignment(i);
        }
        return 1.0f;
    }

    public float getPreferredSpan(int i) {
        double width;
        if (i == 0) {
            width = this.bounds.getWidth();
        } else if (i == 1) {
            width = this.bounds.getHeight();
        } else {
            throw new IllegalArgumentException("Invalid axis: " + i);
        }
        return (float) width;
    }

    public Shape modelToView(int i, Shape shape, Position.Bias bias) throws BadLocationException {
        int startOffset = getStartOffset();
        int endOffset = getEndOffset();
        if (i < startOffset || i > endOffset) {
            throw new BadLocationException(i + " not in range " + startOffset + "," + endOffset, i);
        }
        Rectangle bounds2 = shape.getBounds();
        if (i == endOffset) {
            bounds2.x += bounds2.width;
        }
        bounds2.width = 0;
        return bounds2;
    }

    public int viewToModel(float f, float f2, Shape shape, Position.Bias[] biasArr) {
        Rectangle rectangle = (Rectangle) shape;
        if (f < ((float) (rectangle.x + (rectangle.width / 2)))) {
            biasArr[0] = Position.Bias.Forward;
            return getStartOffset();
        }
        biasArr[0] = Position.Bias.Backward;
        return getEndOffset();
    }
}
