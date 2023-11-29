package gnu.mapping;

import androidx.fragment.app.FragmentTransaction;

public abstract class Procedure1 extends Procedure {
    public abstract Object apply1(Object obj) throws Throwable;

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Procedure1() {
    }

    public Procedure1(String str) {
        super(str);
    }

    public Object apply0() throws Throwable {
        throw new WrongArguments(this, 0);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        throw new WrongArguments(this, 2);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        throw new WrongArguments(this, 3);
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        throw new WrongArguments(this, 4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 1) {
            return apply1(objArr[0]);
        }
        throw new WrongArguments(this, objArr.length);
    }
}
