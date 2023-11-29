package gnu.kawa.swingviews;

import gnu.kawa.models.Button;
import javax.swing.DefaultButtonModel;

/* compiled from: SwingButton */
class SwModel extends DefaultButtonModel {
    Button model;

    public SwModel(Button button) {
        this.model = button;
        setActionCommand(button.getText());
    }
}
