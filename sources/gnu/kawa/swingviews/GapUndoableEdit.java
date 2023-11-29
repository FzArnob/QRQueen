package gnu.kawa.swingviews;

import javax.swing.text.BadLocationException;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/* compiled from: SwingContent */
class GapUndoableEdit extends AbstractUndoableEdit {
    SwingContent content;
    String data;
    boolean isInsertion;
    int nitems;
    int startOffset;

    GapUndoableEdit(int i) {
        this.startOffset = i;
    }

    private void doit(boolean z) throws BadLocationException {
        if (z) {
            this.content.insertString(this.startOffset, this.data);
        } else {
            this.content.remove(this.startOffset, this.nitems);
        }
    }

    public void undo() throws CannotUndoException {
        GapUndoableEdit.super.undo();
        try {
            doit(!this.isInsertion);
        } catch (BadLocationException unused) {
            throw new CannotUndoException();
        }
    }

    public void redo() throws CannotUndoException {
        GapUndoableEdit.super.redo();
        try {
            doit(this.isInsertion);
        } catch (BadLocationException unused) {
            throw new CannotRedoException();
        }
    }
}
