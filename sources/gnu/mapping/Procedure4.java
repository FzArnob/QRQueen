package gnu.mapping;

public abstract class Procedure4 extends Procedure {
    public abstract Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable;

    public int numArgs() {
        return 16388;
    }

    public Procedure4() {
    }

    public Procedure4(String str) {
        super(str);
    }

    public Object apply0() {
        throw new WrongArguments(this, 0);
    }

    public Object apply1(Object obj) {
        throw new WrongArguments(this, 1);
    }

    public Object apply2(Object obj, Object obj2) {
        throw new WrongArguments(this, 2);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        throw new WrongArguments(this, 3);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 4) {
            return apply4(objArr[0], objArr[1], objArr[2], objArr[3]);
        }
        throw new WrongArguments(this, objArr.length);
    }
}
