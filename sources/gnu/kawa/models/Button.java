package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.awt.Color;

public class Button extends Model {
    Object action;
    Color background;
    boolean disabled;
    Color foreground;
    String text;
    Object width;

    public void makeView(Display display, Object obj) {
        display.addButton(this, obj);
    }

    public boolean isDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }

    public Object getAction() {
        return this.action;
    }

    public void setAction(Object obj) {
        this.action = obj;
    }

    public Color getForeground() {
        return this.foreground;
    }

    public void setForeground(Color color) {
        this.foreground = color;
        notifyListeners("foreground");
    }

    public Color getBackground() {
        return this.background;
    }

    public void setBackground(Color color) {
        this.background = color;
        notifyListeners("background");
    }
}
