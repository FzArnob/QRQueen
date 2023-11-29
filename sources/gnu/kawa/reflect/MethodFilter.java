package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;

/* compiled from: ClassMethods */
class MethodFilter implements Filter {
    ClassType caller;
    int modifiers;
    int modmask;
    String name;
    int nlen;
    ObjectType receiver;

    public MethodFilter(String str, int i, int i2, ClassType classType, ObjectType objectType) {
        this.name = str;
        this.nlen = str.length();
        this.modifiers = i;
        this.modmask = i2;
        this.caller = classType;
        this.receiver = objectType;
    }

    public boolean select(Object obj) {
        char charAt;
        Method method = (Method) obj;
        String name2 = method.getName();
        int modifiers2 = method.getModifiers();
        if ((this.modmask & modifiers2) != this.modifiers || (modifiers2 & 4096) != 0 || !name2.startsWith(this.name)) {
            return false;
        }
        int length = name2.length();
        int i = this.nlen;
        if (length != i && ((length != i + 2 || name2.charAt(i) != '$' || ((charAt = name2.charAt(this.nlen + 1)) != 'V' && charAt != 'X')) && (length != this.nlen + 4 || !name2.endsWith("$V$X")))) {
            return false;
        }
        ObjectType objectType = this.receiver;
        ClassType declaringClass = objectType instanceof ClassType ? (ClassType) objectType : method.getDeclaringClass();
        ClassType classType = this.caller;
        if (classType == null || classType.isAccessible(declaringClass, this.receiver, method.getModifiers())) {
            return true;
        }
        return false;
    }
}
