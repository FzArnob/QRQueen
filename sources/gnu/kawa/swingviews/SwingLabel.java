package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import javax.swing.JLabel;

/* compiled from: SwingDisplay */
class SwingLabel extends JLabel implements ModelListener {
    Label model;

    public SwingLabel(Label label) {
        this.model = label;
        String text = label.getText();
        if (text != null) {
            SwingLabel.super.setText(text);
        }
        label.addListener((ModelListener) this);
    }

    public void modelUpdated(Model model2, Object obj) {
        Label label;
        if (obj == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model2 == (label = this.model)) {
            SwingLabel.super.setText(label.getText());
        }
    }

    public void setText(String str) {
        Label label = this.model;
        if (label == null) {
            SwingLabel.super.setText(str);
        } else {
            label.setText(str);
        }
    }
}
