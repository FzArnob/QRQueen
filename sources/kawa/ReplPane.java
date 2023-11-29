package kawa;

import gnu.lists.CharBuffer;
import gnu.mapping.OutPort;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;

public class ReplPane extends JTextPane implements KeyListener {
    public static final Object PaintableAttribute = new String(PaintableElementName);
    public static final String PaintableElementName = "Paintable";
    public static final Object ViewableAttribute = new String(ViewableElementName);
    public static final String ViewableElementName = "Viewable";
    ReplDocument document;

    public void keyReleased(KeyEvent keyEvent) {
    }

    public void keyTyped(KeyEvent keyEvent) {
    }

    public ReplPane(ReplDocument replDocument) {
        super(replDocument);
        this.document = replDocument;
        replDocument.pane = this;
        replDocument.paneCount++;
        addKeyListener(this);
        addFocusListener(replDocument);
        getEditorKit();
        setCaretPosition(replDocument.outputMark);
    }

    /* access modifiers changed from: protected */
    public EditorKit createDefaultEditorKit() {
        return new ReplEditorKit(this);
    }

    public void removeNotify() {
        ReplPane.super.removeNotify();
        ReplDocument replDocument = this.document;
        int i = replDocument.paneCount - 1;
        replDocument.paneCount = i;
        if (i == 0) {
            this.document.close();
        }
    }

    /* access modifiers changed from: package-private */
    public void enter() {
        int i;
        String str;
        int caretPosition = getCaretPosition();
        CharBuffer charBuffer = this.document.content.buffer;
        int length = charBuffer.length() - 1;
        this.document.endMark = -1;
        if (caretPosition >= this.document.outputMark) {
            int indexOf = charBuffer.indexOf(10, caretPosition);
            if (indexOf == length) {
                if (length <= this.document.outputMark || charBuffer.charAt(length - 1) != 10) {
                    this.document.insertString(length, "\n", (AttributeSet) null);
                } else {
                    indexOf--;
                }
            }
            this.document.endMark = indexOf;
            synchronized (this.document.in_r) {
                this.document.in_r.notifyAll();
            }
            if (caretPosition <= indexOf) {
                setCaretPosition(indexOf + 1);
                return;
            }
            return;
        }
        if (caretPosition == 0) {
            i = 0;
        } else {
            i = charBuffer.lastIndexOf(10, caretPosition - 1) + 1;
        }
        Element characterElement = this.document.getCharacterElement(i);
        int indexOf2 = charBuffer.indexOf(10, caretPosition);
        if (characterElement.getAttributes().isEqual(ReplDocument.promptStyle)) {
            i = characterElement.getEndOffset();
        }
        if (indexOf2 < 0) {
            str = charBuffer.substring(i, length) + 10;
        } else {
            str = charBuffer.substring(i, indexOf2 + 1);
        }
        setCaretPosition(this.document.outputMark);
        this.document.write(str, ReplDocument.inputStyle);
        if (this.document.in_r != null) {
            this.document.in_r.append((CharSequence) str, 0, str.length());
        }
    }

    public MutableAttributeSet getInputAttributes() {
        return ReplDocument.inputStyle;
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            enter();
            keyEvent.consume();
        }
    }

    public OutPort getStdout() {
        return this.document.out_stream;
    }

    public OutPort getStderr() {
        return this.document.err_stream;
    }
}
