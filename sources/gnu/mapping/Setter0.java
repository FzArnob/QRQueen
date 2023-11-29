package gnu.mapping;

import androidx.fragment.app.FragmentTransaction;

public class Setter0 extends Setter {
    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Setter0(Procedure procedure) {
        super(procedure);
    }

    public Object apply1(Object obj) throws Throwable {
        this.getter.set0(obj);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        int length = objArr.length;
        if (length == 1) {
            this.getter.set0(objArr[0]);
            return Values.empty;
        }
        throw new WrongArguments(this, length);
    }
}
