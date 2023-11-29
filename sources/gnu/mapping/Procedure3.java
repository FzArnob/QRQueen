package gnu.mapping;

public abstract class Procedure3 extends Procedure {
    public abstract Object apply3(Object obj, Object obj2, Object obj3) throws Throwable;

    public int numArgs() {
        return 12291;
    }

    public Procedure3() {
    }

    public Procedure3(String str) {
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

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) {
        throw new WrongArguments(this, 4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (objArr.length == 3) {
            return apply3(objArr[0], objArr[1], objArr[2]);
        }
        throw new WrongArguments(this, objArr.length);
    }
}
