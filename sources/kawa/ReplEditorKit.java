package kawa;

import gnu.kawa.models.Paintable;
import gnu.kawa.models.Viewable;
import gnu.kawa.swingviews.SwingDisplay;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/* compiled from: ReplPane */
class ReplEditorKit extends StyledEditorKit {
    ViewFactory factory;
    final ReplPane pane;
    ViewFactory styledFactory = ReplEditorKit.super.getViewFactory();

    public ReplEditorKit(final ReplPane replPane) {
        this.pane = replPane;
        this.factory = new ViewFactory() {
            public View create(Element element) {
                String name = element.getName();
                if (name == ReplPane.ViewableElementName) {
                    return new ComponentView(element) {
                        /* access modifiers changed from: protected */
                        public Component createComponent() {
                            AttributeSet attributes = getElement().getAttributes();
                            JPanel jPanel = new JPanel();
                            ((Viewable) attributes.getAttribute(ReplPane.ViewableAttribute)).makeView(SwingDisplay.getInstance(), jPanel);
                            if (jPanel.getComponentCount() == 1) {
                                Component component = jPanel.getComponent(0);
                                jPanel.removeAll();
                                return component;
                            }
                            jPanel.setBackground(replPane.getBackground());
                            return jPanel;
                        }
                    };
                }
                if (name == ReplPane.PaintableElementName) {
                    return new PaintableView(element, (Paintable) element.getAttributes().getAttribute(ReplPane.PaintableAttribute));
                }
                return ReplEditorKit.this.styledFactory.create(element);
            }
        };
    }

    public ViewFactory getViewFactory() {
        return this.factory;
    }
}
