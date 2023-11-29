package gnu.mapping;

public abstract class Procedure2 extends Procedure {
    public abstract Object apply2(Object obj, Object obj2) throws Throwable;

    public int numArgs() {
        return 8194;
    }

    public Procedure2(String str) {
        super(str);
    }

    public Procedure2() {
    }

    public Object apply0() throws Throwable {
        throw new WrongArguments(getName(), 2, "(?)");
    }

    public Object apply1(Object obj) throws Throwable {
        throw new WrongArguments(this, 1);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) {
        throw new WrongArguments(this, 3);
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        throw new WrongArguments(this, 4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 2) {
            return apply2(objArr[0], objArr[1]);
        }
        throw new WrongArguments(this, objArr.length);
    }
}
