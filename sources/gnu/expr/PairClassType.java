package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;

public class PairClassType extends ClassType {
    public ClassType instanceType;
    Object staticLink;

    public PairClassType() {
    }

    PairClassType(Class cls, Class cls2) {
        super(cls.getName());
        setExisting(true);
        this.reflectClass = cls;
        Type.registerTypeForClass(cls, this);
        this.instanceType = (ClassType) Type.make(cls2);
    }

    public static PairClassType make(Class cls, Class cls2) {
        return new PairClassType(cls, cls2);
    }

    public static PairClassType make(Class cls, Class cls2, Object obj) {
        PairClassType pairClassType = new PairClassType(cls, cls2);
        pairClassType.staticLink = obj;
        return pairClassType;
    }

    public Object getStaticLink() {
        return this.staticLink;
    }

    public static Object extractStaticLink(ClassType classType) {
        return ((PairClassType) classType).staticLink;
    }
}
