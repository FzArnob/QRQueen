package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.slf4j.Marker;

public class ProcessingInstructionType extends NodeType implements TypeValue, Externalizable {
    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ProcessingInstructionType piNodeTest = new ProcessingInstructionType((String) null);
    public static final ClassType typeProcessingInstructionType;
    String target;

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
        typeProcessingInstructionType = make;
        coerceMethod = make.getDeclaredMethod("coerce", 2);
        coerceOrNullMethod = make.getDeclaredMethod("coerceOrNull", 2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ProcessingInstructionType(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0005
            java.lang.String r0 = "processing-instruction()"
            goto L_0x001b
        L_0x0005:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "processing-instruction("
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x001b:
            r2.<init>(r0)
            r2.target = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.ProcessingInstructionType.<init>(java.lang.String):void");
    }

    public static ProcessingInstructionType getInstance(String str) {
        return str == null ? piNodeTest : new ProcessingInstructionType(str);
    }

    public Type getImplementationType() {
        return ClassType.make("gnu.kawa.xml.KProcessingInstruction");
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        codeAttr.emitPushString(this.target);
        codeAttr.emitInvokeStatic(coerceMethod);
    }

    public Object coerceFromObject(Object obj) {
        return coerce(obj, this.target);
    }

    public boolean isInstancePos(AbstractSequence abstractSequence, int i) {
        int nextKind = abstractSequence.getNextKind(i);
        if (nextKind == 37) {
            String str = this.target;
            if (str == null || str.equals(abstractSequence.getNextTypeObject(i))) {
                return true;
            }
            return false;
        } else if (nextKind == 32) {
            return isInstance(abstractSequence.getPosNext(i));
        } else {
            return false;
        }
    }

    public boolean isInstance(Object obj) {
        return coerceOrNull(obj, this.target) != null;
    }

    public static KProcessingInstruction coerceOrNull(Object obj, String str) {
        KProcessingInstruction kProcessingInstruction = (KProcessingInstruction) NodeType.coerceOrNull(obj, 32);
        if (kProcessingInstruction == null || (str != null && !str.equals(kProcessingInstruction.getTarget()))) {
            return null;
        }
        return kProcessingInstruction;
    }

    public static KProcessingInstruction coerce(Object obj, String str) {
        KProcessingInstruction coerceOrNull = coerceOrNull(obj, str);
        if (coerceOrNull != null) {
            return coerceOrNull;
        }
        throw new ClassCastException();
    }

    /* access modifiers changed from: protected */
    public void emitCoerceOrNullMethod(Variable variable, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (variable != null) {
            code.emitLoad(variable);
        }
        code.emitPushString(this.target);
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.target);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.target = (String) objectInput.readObject();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProcessingInstructionType ");
        String str = this.target;
        if (str == null) {
            str = Marker.ANY_MARKER;
        }
        sb.append(str);
        return sb.toString();
    }
}
