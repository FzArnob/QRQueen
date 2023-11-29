package gnu.kawa.swingviews;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.kawa.models.Button;
import gnu.kawa.models.Model;
import gnu.kawa.models.ModelListener;
import java.awt.Color;
import javax.swing.JButton;

public class SwingButton extends JButton implements ModelListener {
    Button model;

    public SwingButton(Button button) {
        super(button.getText());
        setModel(new SwModel(button));
        this.model = button;
        Object action = button.getAction();
        if (action != null) {
            addActionListener(SwingDisplay.makeActionListener(action));
        }
        button.addListener((ModelListener) this);
        Color foreground = button.getForeground();
        if (foreground != null) {
            SwingButton.super.setBackground(foreground);
        }
        Color background = button.getBackground();
        if (background != null) {
            SwingButton.super.setBackground(background);
        }
    }

    public void setText(String str) {
        Button button = this.model;
        if (button == null) {
            SwingButton.super.setText(str);
        } else {
            button.setText(str);
        }
    }

    public void setForeground(Color color) {
        Button button = this.model;
        if (button == null) {
            SwingButton.super.setForeground(color);
        } else {
            button.setForeground(color);
        }
    }

    public void setBackground(Color color) {
        Button button = this.model;
        if (button == null) {
            SwingButton.super.setBackground(color);
        } else {
            button.setBackground(color);
        }
    }

    public void modelUpdated(Model model2, Object obj) {
        Button button;
        Button button2;
        Button button3;
        if (obj == PropertyTypeConstants.PROPERTY_TYPE_TEXT && model2 == (button3 = this.model)) {
            SwingButton.super.setText(button3.getText());
        } else if (obj == "foreground" && model2 == (button2 = this.model)) {
            SwingButton.super.setForeground(button2.getForeground());
        } else if (obj == "background" && model2 == (button = this.model)) {
            SwingButton.super.setBackground(button.getBackground());
        }
    }
}
