package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.CallContext;
import gnu.mapping.HasSetter;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import kawa.standard.Scheme;

/* compiled from: GetNamedPart */
class NamedPart extends ProcedureN implements HasSetter, Externalizable {
    Object container;
    char kind;
    Object member;
    MethodProc methods;

    public NamedPart(Object obj, Object obj2, char c) {
        this.container = obj;
        this.member = obj2;
        this.kind = c;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateNamedPart");
    }

    public NamedPart(Object obj, String str, char c, MethodProc methodProc) {
        this(obj, str, c);
        this.methods = methodProc;
    }

    public int numArgs() {
        char c = this.kind;
        if (c == 'I' || c == 'C') {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return c == 'D' ? 4096 : -4096;
    }

    public void apply(CallContext callContext) throws Throwable {
        apply(callContext.getArgs(), callContext);
    }

    public void apply(Object[] objArr, CallContext callContext) throws Throwable {
        char c = this.kind;
        if (c == 'S') {
            this.methods.checkN(objArr, callContext);
        } else if (c == 'M') {
            int length = objArr.length;
            Object[] objArr2 = new Object[(length + 1)];
            objArr2[0] = this.container;
            System.arraycopy(objArr, 0, objArr2, 1, length);
            this.methods.checkN(objArr2, callContext);
        } else {
            callContext.writeValue(applyN(objArr));
        }
    }

    public Object applyN(Object[] objArr) throws Throwable {
        char c = this.kind;
        if (c == 'C') {
            return Convert.as.apply2(this.container, objArr[0]);
        }
        if (c == 'D') {
            String substring = this.member.toString().substring(1);
            if (objArr.length == 0) {
                return SlotGet.staticField((ClassType) this.container, substring);
            }
            return SlotGet.field(((Type) this.container).coerceFromObject(objArr[0]), substring);
        } else if (c == 'I') {
            return Scheme.instanceOf.apply2(objArr[0], this.container);
        } else {
            if (c == 'S') {
                return this.methods.applyN(objArr);
            }
            if (c == 'M') {
                Object[] objArr2 = new Object[(objArr.length + 1)];
                objArr2[0] = this.container;
                System.arraycopy(objArr, 0, objArr2, 1, objArr.length);
                return this.methods.applyN(objArr2);
            } else if (c == 'N') {
                Object[] objArr3 = new Object[(objArr.length + 1)];
                objArr3[0] = this.container;
                System.arraycopy(objArr, 0, objArr3, 1, objArr.length);
                return Invoke.make.applyN(objArr3);
            } else {
                throw new Error("unknown part " + this.member + " in " + this.container);
            }
        }
    }

    public Procedure getSetter() {
        if (this.kind == 'D') {
            return new NamedPartSetter(this);
        }
        throw new RuntimeException("procedure '" + getName() + "' has no setter");
    }

    public void set0(Object obj) throws Throwable {
        if (this.kind == 'D') {
            SlotSet.setStaticField((ClassType) this.container, this.member.toString().substring(1), obj);
            return;
        }
        throw new Error("invalid setter for " + this);
    }

    public void set1(Object obj, Object obj2) throws Throwable {
        if (this.kind == 'D') {
            SlotSet.setField(((Type) this.container).coerceFromObject(obj), this.member.toString().substring(1), obj2);
            return;
        }
        throw new Error("invalid setter for " + this);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.container);
        objectOutput.writeObject(this.member);
        objectOutput.writeChar(this.kind);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.kind = objectInput.readChar();
        this.container = (Procedure) objectInput.readObject();
        this.member = (Procedure) objectInput.readObject();
    }
}
