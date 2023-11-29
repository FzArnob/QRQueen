package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.io.Serializable;

public class Label extends Model implements Viewable, Serializable {
    String text;

    public Label() {
    }

    public Label(String str) {
        this.text = str;
    }

    public void makeView(Display display, Object obj) {
        display.addLabel(this, obj);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }
}
