package gnu.kawa.models;

import gnu.lists.FString;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrappedException;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

public abstract class Display {
    public static ThreadLocation myDisplay = new ThreadLocation("my-display");

    public abstract void addBox(Box box, Object obj);

    public abstract void addButton(Button button, Object obj);

    public abstract void addImage(DrawImage drawImage, Object obj);

    public abstract void addLabel(Label label, Object obj);

    public abstract void addView(Object obj, Object obj2);

    public abstract Window makeWindow();

    public static Display getInstance() {
        String str;
        String str2;
        Object obj = myDisplay.get((Object) null);
        if (obj instanceof Display) {
            return (Display) obj;
        }
        if (obj == null) {
            str = "swing";
        } else {
            str = obj.toString();
        }
        Class[] clsArr = new Class[0];
        while (true) {
            int indexOf = str.indexOf(44);
            if (indexOf >= 0) {
                str2 = str.substring(indexOf + 1);
                str = str.substring(0, indexOf);
            } else {
                str2 = null;
            }
            if (str.equals("swing")) {
                str = "gnu.kawa.swingviews.SwingDisplay";
            } else if (str.equals("swt")) {
                str = "gnu.kawa.swtviews.SwtDisplay";
            } else if (str.equals("echo2")) {
                str = "gnu.kawa.echo2.Echo2Display";
            }
            try {
                return (Display) Class.forName(str).getDeclaredMethod("getInstance", clsArr).invoke((Object) null, new Object[0]);
            } catch (ClassNotFoundException unused) {
                if (str2 != null) {
                    str = str2;
                } else {
                    throw new RuntimeException("no display toolkit: " + obj);
                }
            } catch (Throwable th) {
                throw WrappedException.wrapIfNeeded(th);
            }
        }
    }

    public void addText(Text text, Object obj) {
        throw new Error("makeView called on Text");
    }

    public void addSpacer(Spacer spacer, Object obj) {
        throw new Error("makeView called on Spacer");
    }

    public static Dimension asDimension(Dimension2D dimension2D) {
        if ((dimension2D instanceof Dimension) || dimension2D == null) {
            return (Dimension) dimension2D;
        }
        return new Dimension((int) (dimension2D.getWidth() + 0.5d), (int) (dimension2D.getHeight() + 0.5d));
    }

    public Model coerceToModel(Object obj) {
        if ((obj instanceof FString) || (obj instanceof String)) {
            return new Label(obj.toString());
        }
        return (Model) obj;
    }
}
