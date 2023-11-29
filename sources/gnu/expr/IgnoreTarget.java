package gnu.expr;

import gnu.bytecode.Type;

public class IgnoreTarget extends Target {
    public Type getType() {
        return Type.voidType;
    }

    public void compileFromStack(Compilation compilation, Type type) {
        if (!type.isVoid()) {
            compilation.getCode().emitPop(1);
        }
    }
}
