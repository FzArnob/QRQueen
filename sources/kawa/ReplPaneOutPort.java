package kawa;

import gnu.kawa.models.Paintable;
import gnu.kawa.models.Viewable;
import gnu.mapping.OutPort;
import gnu.text.Path;
import java.awt.Component;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ReplPaneOutPort extends OutPort {
    ReplDocument document;
    String str;
    AttributeSet style;
    TextPaneWriter tout;

    public ReplPaneOutPort(ReplDocument replDocument, String str2, AttributeSet attributeSet) {
        this(new TextPaneWriter(replDocument, attributeSet), replDocument, str2, attributeSet);
    }

    ReplPaneOutPort(TextPaneWriter textPaneWriter, ReplDocument replDocument, String str2, AttributeSet attributeSet) {
        super(textPaneWriter, true, true, Path.valueOf(str2));
        this.str = "";
        this.tout = textPaneWriter;
        this.document = replDocument;
        this.style = attributeSet;
    }

    public void write(String str2, MutableAttributeSet mutableAttributeSet) {
        flush();
        this.document.write(str2, mutableAttributeSet);
        setColumnNumber(1);
    }

    public synchronized void write(Component component) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setComponent(simpleAttributeSet, component);
        write(" ", simpleAttributeSet);
    }

    public void print(Object obj) {
        if (obj instanceof Component) {
            write((Component) obj);
        } else if (obj instanceof Paintable) {
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            simpleAttributeSet.addAttribute("$ename", ReplPane.PaintableElementName);
            simpleAttributeSet.addAttribute(ReplPane.PaintableAttribute, obj);
            write(" ", simpleAttributeSet);
        } else if (obj instanceof Viewable) {
            SimpleAttributeSet simpleAttributeSet2 = new SimpleAttributeSet();
            simpleAttributeSet2.addAttribute("$ename", ReplPane.ViewableElementName);
            simpleAttributeSet2.addAttribute(ReplPane.ViewableAttribute, obj);
            write(" ", simpleAttributeSet2);
        } else {
            super.print(obj);
        }
    }
}
