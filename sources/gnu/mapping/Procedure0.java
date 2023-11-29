package gnu.mapping;

public abstract class Procedure0 extends Procedure {
    public abstract Object apply0() throws Throwable;

    public int numArgs() {
        return 0;
    }

    public Procedure0() {
    }

    public Procedure0(String str) {
        super(str);
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

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) {
        throw new WrongArguments(this, 4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 0) {
            return apply0();
        }
        throw new WrongArguments(this, objArr.length);
    }
}
