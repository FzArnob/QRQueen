package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import kawa.lang.Record;

public class make extends ProcedureN {
    public int numArgs() {
        return -4095;
    }

    public Object applyN(Object[] objArr) {
        Class cls;
        int length = objArr.length;
        if (length != 0) {
            ClassType classType = objArr[0];
            if (classType instanceof Class) {
                cls = classType;
            } else {
                cls = classType instanceof ClassType ? classType.getReflectClass() : null;
            }
            int i = 1;
            if (cls != null) {
                try {
                    Object newInstance = cls.newInstance();
                    while (i < length) {
                        int i2 = i + 1;
                        Record.set1(objArr[i2], objArr[i].getName(), newInstance);
                        i = i2 + 1;
                    }
                    return newInstance;
                } catch (Exception e) {
                    throw new WrappedException((Throwable) e);
                }
            } else {
                throw new WrongType((Procedure) this, 1, (Object) classType, "class");
            }
        } else {
            throw new WrongArguments(this, length);
        }
    }
}
