package gnu.kawa.models;

import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class DrawImage extends Model implements Paintable, Serializable {
    String description;
    BufferedImage image;
    Path src;

    public DrawImage() {
    }

    public void makeView(Display display, Object obj) {
        display.addImage(this, obj);
    }

    /* access modifiers changed from: package-private */
    public void loadImage() {
        if (this.image == null) {
            try {
                this.image = ImageIO.read(this.src.openInputStream());
            } catch (Throwable th) {
                throw WrappedException.wrapIfNeeded(th);
            }
        }
    }

    public DrawImage(BufferedImage bufferedImage) {
        this.image = bufferedImage;
    }

    public void paint(Graphics2D graphics2D) {
        loadImage();
        graphics2D.drawImage(this.image, (AffineTransform) null, (ImageObserver) null);
    }

    public Rectangle2D getBounds2D() {
        loadImage();
        return new Rectangle2D.Float(0.0f, 0.0f, (float) this.image.getWidth(), (float) this.image.getHeight());
    }

    public Paintable transform(AffineTransform affineTransform) {
        return new WithTransform(this, affineTransform);
    }

    public Image getImage() {
        loadImage();
        return this.image;
    }

    public Path getSrc() {
        return this.src;
    }

    public void setSrc(Path path) {
        this.src = path;
    }
}
