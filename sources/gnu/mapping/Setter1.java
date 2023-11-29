package gnu.mapping;

public class Setter1 extends Setter {
    public int numArgs() {
        return 8194;
    }

    public Setter1(Procedure procedure) {
        super(procedure);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        this.getter.set1(obj, obj2);
        return Values.empty;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        int length = objArr.length;
        if (length == 2) {
            this.getter.set1(objArr[0], objArr[1]);
            return Values.empty;
        }
        throw new WrongArguments(this, length);
    }
}
