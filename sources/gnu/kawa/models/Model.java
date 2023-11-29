package gnu.kawa.models;

public abstract class Model implements Viewable {
    transient WeakListener listeners;

    public void addListener(ModelListener modelListener) {
        this.listeners = new WeakListener(modelListener, this.listeners);
    }

    public void addListener(WeakListener weakListener) {
        weakListener.next = this.listeners;
        this.listeners = weakListener;
    }

    public void notifyListeners(String str) {
        WeakListener weakListener = this.listeners;
        WeakListener weakListener2 = null;
        while (weakListener != null) {
            Object obj = weakListener.get();
            WeakListener weakListener3 = weakListener.next;
            if (obj != null) {
                weakListener.update(obj, this, str);
                weakListener2 = weakListener;
            } else if (weakListener2 != null) {
                weakListener2.next = weakListener3;
            }
            weakListener = weakListener3;
        }
    }
}
