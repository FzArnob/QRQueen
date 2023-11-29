package gnu.kawa.swingviews;

import gnu.kawa.models.Display;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.Viewable;
import gnu.kawa.models.Window;
import gnu.lists.AbstractSequence;
import gnu.lists.FString;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class SwingFrame extends JFrame implements Window {
    SwingDisplay display;

    public Display getDisplay() {
        return this.display;
    }

    public SwingFrame(String str, JMenuBar jMenuBar, Object obj) {
        if (str != null) {
            setTitle(str);
        }
        if (jMenuBar != null) {
            setJMenuBar(jMenuBar);
        }
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, 0));
        addComponent(obj);
    }

    public void setContent(Object obj) {
        setContentPane(new JPanel());
        addComponent(obj);
        pack();
    }

    public void setMenuBar(Object obj) {
        setJMenuBar((JMenuBar) obj);
    }

    public void addComponent(Object obj) {
        if ((obj instanceof FString) || (obj instanceof String)) {
            getContentPane().add(new JLabel(obj.toString()));
        } else if (obj instanceof AbstractSequence) {
            AbstractSequence abstractSequence = (AbstractSequence) obj;
            int startPos = abstractSequence.startPos();
            while (true) {
                startPos = abstractSequence.nextPos(startPos);
                if (startPos != 0) {
                    addComponent(abstractSequence.getPosPrevious(startPos));
                } else {
                    return;
                }
            }
        } else if (obj instanceof Viewable) {
            ((Viewable) obj).makeView(getDisplay(), getContentPane());
        } else if (obj instanceof Paintable) {
            getContentPane().add(new SwingPaintable((Paintable) obj));
        } else if (obj != null) {
            getContentPane().add((Component) obj);
        }
    }

    public void open() {
        pack();
        setVisible(true);
    }
}
