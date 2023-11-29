package kawa;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.swingviews.SwingContent;
import gnu.lists.CharBuffer;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.QueueReader;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class ReplDocument extends DefaultStyledDocument implements DocumentListener, FocusListener {
    static Style blueStyle = styles.addStyle("blue", (Style) null);
    public static Style defaultStyle;
    public static Style inputStyle = styles.addStyle("input", (Style) null);
    static Style promptStyle = styles.addStyle("prompt", (Style) null);
    public static Style redStyle = styles.addStyle("red", (Style) null);
    public static StyleContext styles;
    Object closeListeners;
    SwingContent content;
    public int endMark;
    Environment environment;
    final ReplPaneOutPort err_stream;
    final GuiInPort in_p;
    final QueueReader in_r;
    Language language;
    int length;
    final ReplPaneOutPort out_stream;
    public int outputMark;
    JTextPane pane;
    int paneCount;
    Future thread;

    public interface DocumentCloseListener {
        void closed(ReplDocument replDocument);
    }

    static {
        StyleContext styleContext = new StyleContext();
        styles = styleContext;
        defaultStyle = styleContext.addStyle("default", (Style) null);
        StyleConstants.setForeground(redStyle, Color.red);
        StyleConstants.setForeground(blueStyle, Color.blue);
        StyleConstants.setForeground(promptStyle, Color.green);
        StyleConstants.setBold(inputStyle, true);
    }

    public ReplDocument(Language language2, Environment environment2, boolean z) {
        this(new SwingContent(), language2, environment2, z);
    }

    private ReplDocument(SwingContent swingContent, Language language2, Environment environment2, final boolean z) {
        super(swingContent, styles);
        this.outputMark = 0;
        this.endMark = -1;
        this.length = 0;
        this.content = swingContent;
        ModuleBody.exitIncrement();
        addDocumentListener(this);
        this.language = language2;
        AnonymousClass1 r5 = new QueueReader() {
            public void checkAvailable() {
                ReplDocument.this.checkingPendingInput();
            }
        };
        this.in_r = r5;
        ReplPaneOutPort replPaneOutPort = new ReplPaneOutPort(this, "/dev/stdout", defaultStyle);
        this.out_stream = replPaneOutPort;
        ReplPaneOutPort replPaneOutPort2 = new ReplPaneOutPort(this, "/dev/stderr", redStyle);
        this.err_stream = replPaneOutPort2;
        GuiInPort guiInPort = new GuiInPort(r5, Path.valueOf("/dev/stdin"), replPaneOutPort, this);
        this.in_p = guiInPort;
        Future make = Future.make(new repl(language2) {
            public Object apply0() {
                Environment current = Environment.getCurrent();
                if (z) {
                    current.setIndirectDefines();
                }
                ReplDocument.this.environment = current;
                Shell.run(this.language, current);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ReplDocument.this.fireDocumentClosed();
                    }
                });
                return Values.empty;
            }
        }, environment2, guiInPort, replPaneOutPort, replPaneOutPort2);
        this.thread = make;
        make.start();
    }

    public synchronized void deleteOldText() {
        try {
            String text = getText(0, this.outputMark);
            int i = this.outputMark;
            remove(0, i <= 0 ? 0 : text.lastIndexOf(10, i - 1) + 1);
        } catch (BadLocationException e) {
            throw new Error(e);
        }
    }

    public void insertString(int i, String str, AttributeSet attributeSet) {
        try {
            ReplDocument.super.insertString(i, str, attributeSet);
        } catch (BadLocationException e) {
            throw new Error(e);
        }
    }

    public void write(final String str, final AttributeSet attributeSet) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                boolean z = ReplDocument.this.pane != null && ReplDocument.this.pane.getCaretPosition() == ReplDocument.this.outputMark;
                ReplDocument replDocument = ReplDocument.this;
                replDocument.insertString(replDocument.outputMark, str, attributeSet);
                ReplDocument.this.outputMark += str.length();
                if (z) {
                    ReplDocument.this.pane.setCaretPosition(ReplDocument.this.outputMark);
                }
            }
        });
    }

    public void checkingPendingInput() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int i = ReplDocument.this.outputMark;
                if (i <= ReplDocument.this.endMark) {
                    CharBuffer charBuffer = ReplDocument.this.content.buffer;
                    int indexOf = charBuffer.indexOf(10, i);
                    if (indexOf == ReplDocument.this.endMark) {
                        ReplDocument.this.endMark = -1;
                    }
                    if (i == ReplDocument.this.outputMark) {
                        ReplDocument.this.outputMark = indexOf + 1;
                    }
                    if (ReplDocument.this.in_r != null) {
                        synchronized (ReplDocument.this.in_r) {
                            ReplDocument.this.in_r.append((CharSequence) charBuffer, i, indexOf + 1);
                            ReplDocument.this.in_r.notifyAll();
                        }
                    }
                }
            }
        });
    }

    public void focusGained(FocusEvent focusEvent) {
        Object source = focusEvent.getSource();
        boolean z = source instanceof ReplPane;
        ReplPane replPane = null;
        if (z) {
            this.pane = (ReplPane) source;
        } else {
            this.pane = null;
        }
        if (z) {
            replPane = (ReplPane) source;
        }
        this.pane = replPane;
    }

    public void focusLost(FocusEvent focusEvent) {
        this.pane = null;
    }

    public void changedUpdate(DocumentEvent documentEvent) {
        textValueChanged(documentEvent);
    }

    public void insertUpdate(DocumentEvent documentEvent) {
        textValueChanged(documentEvent);
    }

    public void removeUpdate(DocumentEvent documentEvent) {
        textValueChanged(documentEvent);
    }

    public synchronized void textValueChanged(DocumentEvent documentEvent) {
        int offset = documentEvent.getOffset();
        int length2 = getLength();
        int i = this.length;
        int i2 = length2 - i;
        this.length = i + i2;
        int i3 = this.outputMark;
        if (offset < i3) {
            this.outputMark = i3 + i2;
        } else if (offset - i2 < i3) {
            this.outputMark = offset;
        }
        int i4 = this.endMark;
        if (i4 >= 0) {
            if (offset < i4) {
                this.endMark = i4 + i2;
            } else if (offset - i2 < i4) {
                this.endMark = offset;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void close() {
        this.in_r.appendEOF();
        try {
            Thread.sleep(100);
        } catch (InterruptedException unused) {
        }
        this.thread.stop();
        fireDocumentClosed();
        ModuleBody.exitDecrement();
    }

    public void addDocumentCloseListener(DocumentCloseListener documentCloseListener) {
        ArrayList arrayList;
        Object obj = this.closeListeners;
        if (obj == null) {
            this.closeListeners = documentCloseListener;
            return;
        }
        if (obj instanceof ArrayList) {
            arrayList = (ArrayList) obj;
        } else {
            arrayList = new ArrayList(10);
            arrayList.add(this.closeListeners);
            this.closeListeners = arrayList;
        }
        arrayList.add(documentCloseListener);
    }

    public void removeDocumentCloseListener(DocumentCloseListener documentCloseListener) {
        Object obj = this.closeListeners;
        if (obj instanceof DocumentCloseListener) {
            if (obj == documentCloseListener) {
                this.closeListeners = null;
            }
        } else if (obj != null) {
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (arrayList.get(size) == documentCloseListener) {
                    arrayList.remove(size);
                }
            }
            if (arrayList.size() == 0) {
                this.closeListeners = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fireDocumentClosed() {
        Object obj = this.closeListeners;
        if (obj instanceof DocumentCloseListener) {
            ((DocumentCloseListener) obj).closed(this);
        } else if (obj != null) {
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size >= 0) {
                    ((DocumentCloseListener) arrayList.get(size)).closed(this);
                } else {
                    return;
                }
            }
        }
    }
}
