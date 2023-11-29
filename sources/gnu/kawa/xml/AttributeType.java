package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.AttributePredicate;
import gnu.lists.SeqPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

public class AttributeType extends NodeType implements TypeValue, Externalizable, AttributePredicate {
    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ClassType typeAttributeType;
    Symbol qname;

    public static AttributeType make(String str, String str2) {
        Symbol symbol;
        if (str != null) {
            symbol = Symbol.make(str, str2);
        } else if (str2 == "") {
            symbol = ElementType.MATCH_ANY_QNAME;
        } else {
            symbol = new Symbol((Namespace) null, str2);
        }
        return new AttributeType(symbol);
    }

    public static AttributeType make(Symbol symbol) {
        return new AttributeType(symbol);
    }

    public AttributeType(Symbol symbol) {
        this((String) null, symbol);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AttributeType(java.lang.String r2, gnu.mapping.Symbol r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0009
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x0009
            goto L_0x001f
        L_0x0009:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r0 = "ATTRIBUTE "
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = " (*)"
            r2.append(r0)
            java.lang.String r2 = r2.toString()
        L_0x001f:
            r1.<init>(r2)
            r1.qname = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.AttributeType.<init>(java.lang.String, gnu.mapping.Symbol):void");
    }

    public Type getImplementationType() {
        return ClassType.make("gnu.kawa.xml.KAttr");
    }

    public final String getNamespaceURI() {
        return this.qname.getNamespaceURI();
    }

    public final String getLocalName() {
        return this.qname.getLocalName();
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        codeAttr.emitPushString(this.qname.getNamespaceURI());
        codeAttr.emitPushString(this.qname.getLocalName());
        codeAttr.emitInvokeStatic(coerceMethod);
    }

    public Object coerceFromObject(Object obj) {
        return coerce(obj, this.qname.getNamespaceURI(), this.qname.getLocalName());
    }

    public boolean isInstancePos(AbstractSequence abstractSequence, int i) {
        int nextKind = abstractSequence.getNextKind(i);
        if (nextKind == 35) {
            return isInstance(abstractSequence, i, abstractSequence.getNextTypeObject(i));
        }
        if (nextKind == 32) {
            return isInstance(abstractSequence.getPosNext(i));
        }
        return false;
    }

    public boolean isInstance(AbstractSequence abstractSequence, int i, Object obj) {
        String str;
        String str2;
        String namespaceURI = this.qname.getNamespaceURI();
        String localName = this.qname.getLocalName();
        if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            str2 = symbol.getNamespaceURI();
            str = symbol.getLocalName();
        } else if (obj instanceof QName) {
            QName qName = (QName) obj;
            str2 = qName.getNamespaceURI();
            str = qName.getLocalPart();
        } else {
            str = obj.toString().intern();
            str2 = "";
        }
        if (localName != null && localName.length() == 0) {
            localName = null;
        }
        return (localName == str || localName == null) && (namespaceURI == str2 || namespaceURI == null);
    }

    public boolean isInstance(Object obj) {
        return coerceOrNull(obj, this.qname.getNamespaceURI(), this.qname.getLocalName()) != null;
    }

    public static KAttr coerceOrNull(Object obj, String str, String str2) {
        String str3;
        String str4;
        KNode coerceOrNull = NodeType.coerceOrNull(obj, 4);
        if (coerceOrNull == null) {
            return null;
        }
        if (str2 != null && str2.length() == 0) {
            str2 = null;
        }
        Object nextTypeObject = coerceOrNull.getNextTypeObject();
        if (nextTypeObject instanceof Symbol) {
            Symbol symbol = (Symbol) nextTypeObject;
            str3 = symbol.getNamespaceURI();
            str4 = symbol.getLocalName();
        } else if (nextTypeObject instanceof QName) {
            QName qName = (QName) nextTypeObject;
            str3 = qName.getNamespaceURI();
            str4 = qName.getLocalPart();
        } else {
            str4 = nextTypeObject.toString().intern();
            str3 = "";
        }
        if ((str2 == str4 || str2 == null) && (str == str3 || str == null)) {
            return (KAttr) coerceOrNull;
        }
        return null;
    }

    public static SeqPosition coerce(Object obj, String str, String str2) {
        KAttr coerceOrNull = coerceOrNull(obj, str, str2);
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
        code.emitPushString(this.qname.getNamespaceURI());
        code.emitPushString(this.qname.getLocalName());
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.AttributeType");
        typeAttributeType = make;
        coerceMethod = make.getDeclaredMethod("coerce", 3);
        coerceOrNullMethod = make.getDeclaredMethod("coerceOrNull", 3);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        String name = getName();
        if (name == null) {
            name = "";
        }
        objectOutput.writeUTF(name);
        objectOutput.writeObject(this.qname);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        String readUTF = objectInput.readUTF();
        if (readUTF.length() > 0) {
            setName(readUTF);
        }
        this.qname = (Symbol) objectInput.readObject();
    }

    public String toString() {
        return "AttributeType " + this.qname;
    }
}
