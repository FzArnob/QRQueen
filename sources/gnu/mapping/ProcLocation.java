package gnu.mapping;

public class ProcLocation extends Location {
    Object[] args;
    Procedure proc;

    public boolean isBound() {
        return true;
    }

    public ProcLocation(Procedure procedure, Object[] objArr) {
        this.proc = procedure;
        this.args = objArr;
    }

    public Object get(Object obj) {
        try {
            return this.proc.applyN(this.args);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th) {
            throw new WrappedException(th);
        }
    }

    public void set(Object obj) {
        Object[] objArr = this.args;
        int length = objArr.length;
        Object[] objArr2 = new Object[(length + 1)];
        objArr2[length] = obj;
        System.arraycopy(objArr, 0, objArr2, 0, length);
        try {
            this.proc.setN(objArr2);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th) {
            throw new WrappedException(th);
        }
    }
}
