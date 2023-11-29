package gnu.lists;

public class ImmutablePair extends Pair {
    public ImmutablePair(Object obj, Object obj2) {
        this.car = obj;
        this.cdr = obj2;
    }

    public ImmutablePair() {
    }

    public void setCar(Object obj) {
        throw new UnsupportedOperationException("cannot modify read-only pair");
    }

    public void setCdr(Object obj) {
        throw new UnsupportedOperationException("cannot modify read-only pair");
    }
}
