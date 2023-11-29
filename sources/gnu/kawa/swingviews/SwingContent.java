package gnu.kawa.swingviews;

import gnu.lists.CharBuffer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.undo.UndoableEdit;

public class SwingContent implements AbstractDocument.Content {
    public final CharBuffer buffer;

    public SwingContent(CharBuffer charBuffer) {
        this.buffer = charBuffer;
    }

    public SwingContent(int i) {
        CharBuffer charBuffer = new CharBuffer(i);
        charBuffer.gapEnd = i - 1;
        charBuffer.getArray()[charBuffer.gapEnd] = 10;
        this.buffer = charBuffer;
    }

    public SwingContent() {
        this(100);
    }

    public int length() {
        return this.buffer.length();
    }

    public void getChars(int i, int i2, Segment segment) throws BadLocationException {
        CharBuffer charBuffer = this.buffer;
        int segment2 = charBuffer.getSegment(i, i2);
        if (segment2 >= 0) {
            segment.offset = segment2;
            segment.array = charBuffer.getArray();
            segment.count = i2;
            return;
        }
        throw new BadLocationException("invalid offset", i);
    }

    public String getString(int i, int i2) throws BadLocationException {
        CharBuffer charBuffer = this.buffer;
        int segment = charBuffer.getSegment(i, i2);
        if (segment >= 0) {
            return new String(charBuffer.getArray(), segment, i2);
        }
        throw new BadLocationException("invalid offset", i);
    }

    public UndoableEdit remove(int i, int i2) throws BadLocationException {
        CharBuffer charBuffer = this.buffer;
        if (i2 < 0 || i < 0 || i + i2 > charBuffer.length()) {
            throw new BadLocationException("invalid remove", i);
        }
        charBuffer.delete(i, i2);
        GapUndoableEdit gapUndoableEdit = new GapUndoableEdit(i);
        gapUndoableEdit.content = this;
        gapUndoableEdit.data = new String(charBuffer.getArray(), charBuffer.gapEnd - i2, i2);
        gapUndoableEdit.nitems = i2;
        gapUndoableEdit.isInsertion = false;
        return gapUndoableEdit;
    }

    public UndoableEdit insertString(int i, String str, boolean z) throws BadLocationException {
        CharBuffer charBuffer = this.buffer;
        if (i < 0 || i > charBuffer.length()) {
            throw new BadLocationException("bad insert", i);
        }
        charBuffer.insert(i, str, z);
        GapUndoableEdit gapUndoableEdit = new GapUndoableEdit(i);
        gapUndoableEdit.content = this;
        gapUndoableEdit.data = str;
        gapUndoableEdit.nitems = str.length();
        gapUndoableEdit.isInsertion = true;
        return gapUndoableEdit;
    }

    public UndoableEdit insertString(int i, String str) throws BadLocationException {
        return insertString(i, str, false);
    }

    public Position createPosition(int i) throws BadLocationException {
        CharBuffer charBuffer = this.buffer;
        if (i >= 0 && i <= charBuffer.length()) {
            return new GapPosition(charBuffer, i);
        }
        throw new BadLocationException("bad offset to createPosition", i);
    }
}
