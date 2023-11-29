package gnu.kawa.models;

import java.lang.ref.WeakReference;

public class WeakListener extends WeakReference {
    WeakListener next;

    public WeakListener(Object obj) {
        super(obj);
    }

    public WeakListener(Object obj, WeakListener weakListener) {
        super(obj);
        this.next = weakListener;
    }

    public void update(Object obj, Model model, Object obj2) {
        ((ModelListener) obj).modelUpdated(model, obj2);
    }
}
