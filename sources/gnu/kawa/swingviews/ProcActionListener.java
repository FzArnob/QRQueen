package gnu.kawa.swingviews;

import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* compiled from: SwingDisplay */
class ProcActionListener implements ActionListener {
    Procedure proc;

    public ProcActionListener(Procedure procedure) {
        this.proc = procedure;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            this.proc.apply1(actionEvent);
        } catch (Throwable th) {
            throw new WrappedException(th);
        }
    }
}
