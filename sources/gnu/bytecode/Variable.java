package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class Variable extends Location implements Enumeration {
    private static final int LIVE_FLAG = 4;
    private static final int PARAMETER_FLAG = 2;
    private static final int SIMPLE_FLAG = 1;
    static final int UNASSIGNED = -1;
    private int flags = 1;
    Variable next;
    int offset = -1;
    Scope scope;

    public final Variable nextVar() {
        return this.next;
    }

    public final boolean hasMoreElements() {
        return this.next != null;
    }

    public Object nextElement() {
        Variable variable = this.next;
        if (variable != null) {
            return variable;
        }
        throw new NoSuchElementException("Variable enumeration");
    }

    public Variable() {
    }

    public Variable(String str) {
        setName(str);
    }

    public Variable(String str, Type type) {
        setName(str);
        setType(type);
    }

    public final boolean isAssigned() {
        return this.offset != -1;
    }

    public final boolean dead() {
        return (this.flags & 4) == 0;
    }

    private void setFlag(boolean z, int i) {
        if (z) {
            this.flags |= i;
        } else {
            this.flags &= ~i;
        }
    }

    public final boolean isSimple() {
        return (this.flags & 1) != 0;
    }

    public final void setSimple(boolean z) {
        setFlag(z, 1);
    }

    public final boolean isParameter() {
        return (this.flags & 2) != 0;
    }

    public final void setParameter(boolean z) {
        setFlag(z, 2);
    }

    public boolean reserveLocal(int i, CodeAttr codeAttr) {
        int sizeInWords = getType().getSizeInWords();
        if (codeAttr.locals.used == null) {
            codeAttr.locals.used = new Variable[(sizeInWords + 20)];
        } else if (codeAttr.getMaxLocals() + sizeInWords >= codeAttr.locals.used.length) {
            Variable[] variableArr = new Variable[((codeAttr.locals.used.length * 2) + sizeInWords)];
            System.arraycopy(codeAttr.locals.used, 0, variableArr, 0, codeAttr.getMaxLocals());
            codeAttr.locals.used = variableArr;
        }
        for (int i2 = 0; i2 < sizeInWords; i2++) {
            if (codeAttr.locals.used[i + i2] != null) {
                return false;
            }
        }
        for (int i3 = 0; i3 < sizeInWords; i3++) {
            codeAttr.locals.used[i + i3] = this;
        }
        int i4 = sizeInWords + i;
        if (i4 > codeAttr.getMaxLocals()) {
            codeAttr.setMaxLocals(i4);
        }
        this.offset = i;
        this.flags |= 4;
        if (i != 0 || !"<init>".equals(codeAttr.getMethod().getName())) {
            return true;
        }
        setType(codeAttr.local_types[0]);
        return true;
    }

    public void allocateLocal(CodeAttr codeAttr) {
        if (this.offset == -1) {
            for (int i = 0; !reserveLocal(i, codeAttr); i++) {
            }
        }
    }

    public void freeLocal(CodeAttr codeAttr) {
        this.flags &= -5;
        int i = this.offset + (getType().size > 4 ? 2 : 1);
        while (true) {
            i--;
            if (i >= this.offset) {
                codeAttr.locals.used[i] = null;
                Type[] typeArr = codeAttr.local_types;
                if (typeArr != null) {
                    typeArr[i] = null;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0012, code lost:
        r1 = (r1 = r0.start).position;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        r0 = r0.end;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldEmit() {
        /*
            r2 = this;
            gnu.bytecode.Scope r0 = r2.scope
            boolean r1 = r2.isSimple()
            if (r1 == 0) goto L_0x0020
            java.lang.String r1 = r2.name
            if (r1 == 0) goto L_0x0020
            if (r0 == 0) goto L_0x0020
            gnu.bytecode.Label r1 = r0.start
            if (r1 == 0) goto L_0x0020
            int r1 = r1.position
            if (r1 < 0) goto L_0x0020
            gnu.bytecode.Label r0 = r0.end
            if (r0 == 0) goto L_0x0020
            int r0 = r0.position
            if (r0 <= r1) goto L_0x0020
            r0 = 1
            goto L_0x0021
        L_0x0020:
            r0 = 0
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.Variable.shouldEmit():boolean");
    }

    public String toString() {
        return "Variable[" + getName() + " offset:" + this.offset + ']';
    }
}
