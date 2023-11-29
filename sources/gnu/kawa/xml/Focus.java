package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.Compilation;
import gnu.lists.TreePosition;
import gnu.math.IntNum;

public final class Focus extends TreePosition {
    public static final ClassType TYPE;
    static ThreadLocal current = new ThreadLocal();
    static final Method getCurrentFocusMethod;
    IntNum contextPosition;
    public long position;

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.Focus");
        TYPE = make;
        getCurrentFocusMethod = make.getDeclaredMethod("getCurrent", 0);
    }

    public static Focus getCurrent() {
        Object obj = current.get();
        if (obj == null) {
            obj = new Focus();
            current.set(obj);
        }
        return (Focus) obj;
    }

    public static void compileGetCurrent(Compilation compilation) {
        compilation.getCode().emitInvoke(getCurrentFocusMethod);
    }
}
