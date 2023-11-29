package gnu.mapping;

public abstract class Procedure1or2 extends Procedure {
    public abstract Object apply1(Object obj) throws Throwable;

    public abstract Object apply2(Object obj, Object obj2) throws Throwable;

    public int numArgs() {
        return 8193;
    }

    public Procedure1or2() {
    }

    public Procedure1or2(String str) {
        super(str);
    }

    public Object apply0() {
        throw new WrongArguments(this, 0);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        throw new WrongArguments(this, 3);
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) {
        throw new WrongArguments(this, 4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 1) {
            return apply1(objArr[0]);
        }
        if (objArr.length == 2) {
            return apply2(objArr[0], objArr[1]);
        }
        throw new WrongArguments(this, objArr.length);
    }
}
