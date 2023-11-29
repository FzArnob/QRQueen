package gnu.mapping;

import androidx.fragment.app.FragmentTransaction;

public class Setter extends ProcedureN {
    protected Procedure getter;

    public Setter(Procedure procedure) {
        this.getter = procedure;
        String name = procedure.getName();
        if (name != null) {
            setName("(setter " + name + ")");
        }
    }

    public int numArgs() {
        int numArgs = this.getter.numArgs();
        return numArgs < 0 ? numArgs + 1 : numArgs + FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        this.getter.setN(objArr);
        return Values.empty;
    }
}
