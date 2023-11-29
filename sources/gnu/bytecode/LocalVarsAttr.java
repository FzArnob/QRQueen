package gnu.bytecode;

import androidx.core.os.EnvironmentCompat;
import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVarsAttr extends Attribute {
    public Scope current_scope;
    private Method method;
    Scope parameter_scope;
    Variable[] used;

    public LocalVarsAttr(CodeAttr codeAttr) {
        super("LocalVariableTable");
        addToFrontOf(codeAttr);
        this.method = (Method) codeAttr.getContainer();
        codeAttr.locals = this;
    }

    public LocalVarsAttr(Method method2) {
        super("LocalVariableTable");
        CodeAttr codeAttr = method2.code;
        this.method = method2;
        codeAttr.locals = this;
    }

    public final Method getMethod() {
        return this.method;
    }

    public VarEnumerator allVars() {
        return new VarEnumerator(this.parameter_scope);
    }

    public void enterScope(Scope scope) {
        scope.linkChild(this.current_scope);
        this.current_scope = scope;
        CodeAttr code = this.method.getCode();
        for (Variable firstVar = scope.firstVar(); firstVar != null; firstVar = firstVar.nextVar()) {
            if (firstVar.isSimple()) {
                if (!firstVar.isAssigned()) {
                    firstVar.allocateLocal(code);
                } else if (this.used[firstVar.offset] == null) {
                    this.used[firstVar.offset] = firstVar;
                } else if (this.used[firstVar.offset] != firstVar) {
                    throw new Error("inconsistent local variable assignments for " + firstVar + " != " + this.used[firstVar.offset]);
                }
            }
        }
    }

    public void preserveVariablesUpto(Scope scope) {
        for (Scope scope2 = this.current_scope; scope2 != scope; scope2 = scope2.parent) {
            scope2.preserved = true;
        }
    }

    public final boolean isEmpty() {
        VarEnumerator allVars = allVars();
        while (true) {
            Variable nextVar = allVars.nextVar();
            if (nextVar == null) {
                return true;
            }
            if (nextVar.isSimple() && nextVar.name != null) {
                return false;
            }
        }
    }

    public final int getCount() {
        VarEnumerator allVars = allVars();
        int i = 0;
        while (true) {
            Variable nextVar = allVars.nextVar();
            if (nextVar == null) {
                return i;
            }
            if (nextVar.shouldEmit()) {
                i++;
            }
        }
    }

    public final int getLength() {
        return (getCount() * 10) + 2;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        VarEnumerator allVars = allVars();
        while (true) {
            Variable nextVar = allVars.nextVar();
            if (nextVar == null) {
                return;
            }
            if (nextVar.isSimple() && nextVar.name != null) {
                if (nextVar.name_index == 0) {
                    nextVar.name_index = classType.getConstants().addUtf8(nextVar.getName()).index;
                }
                if (nextVar.signature_index == 0) {
                    nextVar.signature_index = classType.getConstants().addUtf8(nextVar.getType().getSignature()).index;
                }
            }
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        VarEnumerator allVars = allVars();
        dataOutputStream.writeShort(getCount());
        allVars.reset();
        while (true) {
            Variable nextVar = allVars.nextVar();
            if (nextVar == null) {
                return;
            }
            if (nextVar.shouldEmit()) {
                Scope scope = nextVar.scope;
                int i = scope.start.position;
                int i2 = scope.end.position;
                dataOutputStream.writeShort(i);
                dataOutputStream.writeShort(i2 - i);
                dataOutputStream.writeShort(nextVar.name_index);
                dataOutputStream.writeShort(nextVar.signature_index);
                dataOutputStream.writeShort(nextVar.offset);
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        int i;
        int i2;
        VarEnumerator allVars = allVars();
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", count: ");
        classTypeWriter.println(getCount());
        allVars.reset();
        while (true) {
            Variable nextVar = allVars.nextVar();
            if (nextVar == null) {
                return;
            }
            if (nextVar.isSimple() && nextVar.name != null) {
                classTypeWriter.print("  slot#");
                classTypeWriter.print(nextVar.offset);
                classTypeWriter.print(": name: ");
                classTypeWriter.printOptionalIndex(nextVar.name_index);
                classTypeWriter.print(nextVar.getName());
                classTypeWriter.print(", type: ");
                classTypeWriter.printOptionalIndex(nextVar.signature_index);
                classTypeWriter.printSignature(nextVar.getType());
                classTypeWriter.print(" (pc: ");
                Scope scope = nextVar.scope;
                if (scope == null || scope.start == null || scope.end == null || (i = scope.start.position) < 0 || (i2 = scope.end.position) < 0) {
                    classTypeWriter.print(EnvironmentCompat.MEDIA_UNKNOWN);
                } else {
                    classTypeWriter.print(i);
                    classTypeWriter.print(" length: ");
                    classTypeWriter.print(i2 - i);
                }
                classTypeWriter.println(')');
            }
        }
    }
}
