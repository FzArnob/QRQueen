package gnu.mapping;

public abstract class ProcedureN extends Procedure {
    public static final Object[] noArgs = new Object[0];

    public abstract Object applyN(Object[] objArr) throws Throwable;

    public ProcedureN() {
    }

    public ProcedureN(String str) {
        super(str);
    }

    public Object apply0() throws Throwable {
        return applyN(noArgs);
    }

    public Object apply1(Object obj) throws Throwable {
        return applyN(new Object[]{obj});
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        return applyN(new Object[]{obj, obj2});
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        return applyN(new Object[]{obj, obj2, obj3});
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        return applyN(new Object[]{obj, obj2, obj3, obj4});
    }
}
