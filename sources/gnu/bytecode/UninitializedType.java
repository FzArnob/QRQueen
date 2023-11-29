package gnu.bytecode;

public class UninitializedType extends ObjectType {
    ClassType ctype;
    Label label;

    UninitializedType(ClassType classType) {
        super(classType.getName());
        setSignature(classType.getSignature());
        this.ctype = classType;
    }

    UninitializedType(ClassType classType, Label label2) {
        this(classType);
        this.label = label2;
    }

    static UninitializedType uninitializedThis(ClassType classType) {
        return new UninitializedType(classType);
    }

    public Type getImplementationType() {
        return this.ctype;
    }

    public String toString() {
        return "Uninitialized<" + this.ctype.getName() + '>';
    }
}
