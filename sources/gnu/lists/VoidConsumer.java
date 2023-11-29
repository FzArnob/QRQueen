package gnu.lists;

public class VoidConsumer extends FilterConsumer {
    public static VoidConsumer instance = new VoidConsumer();

    public boolean ignoring() {
        return true;
    }

    public static VoidConsumer getInstance() {
        return instance;
    }

    public VoidConsumer() {
        super((Consumer) null);
        this.skipping = true;
    }
}
