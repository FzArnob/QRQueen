package gnu.kawa.models;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.lists.CharBuffer;
import java.io.Serializable;

public class Text extends Model implements Viewable, Serializable {
    public final CharBuffer buffer;

    public Text() {
        this("");
    }

    public Text(String str) {
        CharBuffer charBuffer = new CharBuffer(100);
        this.buffer = charBuffer;
        charBuffer.gapEnd = 99;
        charBuffer.getArray()[charBuffer.gapEnd] = 10;
        setText(str);
    }

    public void makeView(Display display, Object obj) {
        display.addText(this, obj);
    }

    public String getText() {
        int size = this.buffer.size() - 1;
        return new String(this.buffer.getArray(), this.buffer.getSegment(0, size), size);
    }

    public void setText(String str) {
        int size = this.buffer.size() - 1;
        if (size > 0) {
            this.buffer.delete(0, size);
        }
        this.buffer.insert(0, str, false);
        notifyListeners(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
    }

    public CharBuffer getBuffer() {
        return this.buffer;
    }
}
