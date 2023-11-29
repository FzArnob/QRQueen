package gnu.kawa.xml;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;
import gnu.xml.NodeTree;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class NodeType extends ObjectType implements TypeValue, NodePredicate, Externalizable {
    public static final int ATTRIBUTE_OK = 4;
    public static final int COMMENT_OK = 16;
    public static final int DOCUMENT_OK = 8;
    public static final int ELEMENT_OK = 2;
    public static final int PI_OK = 32;
    public static final int TEXT_OK = 1;
    public static final NodeType anyNodeTest = new NodeType("node");
    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final NodeType commentNodeTest = new NodeType("comment", 16);
    public static final NodeType documentNodeTest = new NodeType("document-node", 8);
    public static final NodeType nodeType = new NodeType("gnu.kawa.xml.KNode");
    public static final NodeType textNodeTest = new NodeType(PropertyTypeConstants.PROPERTY_TYPE_TEXT, 1);
    public static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
    public static final ClassType typeNodeType;
    int kinds;

    public Procedure getConstructor() {
        return null;
    }

    public NodeType(String str, int i) {
        super(str);
        this.kinds = i;
    }

    public NodeType(String str) {
        this(str, -1);
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        codeAttr.emitPushInt(this.kinds);
        codeAttr.emitInvokeStatic(coerceMethod);
    }

    public Expression convertValue(Expression expression) {
        ApplyExp applyExp = new ApplyExp(coerceMethod, expression);
        applyExp.setType(this);
        return applyExp;
    }

    public Object coerceFromObject(Object obj) {
        return coerceForce(obj, this.kinds);
    }

    public Type getImplementationType() {
        return typeKNode;
    }

    public int compare(Type type) {
        return getImplementationType().compare(type);
    }

    public boolean isInstance(Object obj) {
        if (!(obj instanceof KNode)) {
            return false;
        }
        KNode kNode = (KNode) obj;
        return isInstancePos(kNode.sequence, kNode.getPos());
    }

    public boolean isInstancePos(AbstractSequence abstractSequence, int i) {
        return isInstance(abstractSequence, i, this.kinds);
    }

    public static boolean isInstance(AbstractSequence abstractSequence, int i, int i2) {
        int nextKind = abstractSequence.getNextKind(i);
        if (i2 < 0) {
            return nextKind != 0;
        }
        if (nextKind == 0) {
            return false;
        }
        switch (nextKind) {
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                break;
            default:
                switch (nextKind) {
                    case 32:
                        break;
                    case 33:
                        return (i2 & 2) != 0;
                    case 34:
                        return (i2 & 8) != 0;
                    case 35:
                        return (i2 & 4) != 0;
                    case 36:
                        return (i2 & 16) != 0;
                    case 37:
                        return (i2 & 32) != 0;
                    default:
                        return true;
                }
        }
        return (i2 & 1) != 0;
    }

    public static KNode coerceForce(Object obj, int i) {
        KNode coerceOrNull = coerceOrNull(obj, i);
        if (coerceOrNull != null) {
            return coerceOrNull;
        }
        throw new ClassCastException("coerce from " + obj.getClass());
    }

    public static KNode coerceOrNull(Object obj, int i) {
        KNode kNode;
        if (obj instanceof NodeTree) {
            kNode = KNode.make((NodeTree) obj);
        } else if (!(obj instanceof KNode)) {
            return null;
        } else {
            kNode = (KNode) obj;
        }
        if (isInstance(kNode.sequence, kNode.ipos, i)) {
            return kNode;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void emitCoerceOrNullMethod(Variable variable, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (variable != null) {
            code.emitLoad(variable);
        }
        code.emitPushInt(this.kinds);
        code.emitInvokeStatic(coerceOrNullMethod);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        emitCoerceOrNullMethod(variable, compilation);
        if (declaration != null) {
            code.emitDup();
            declaration.compileStore(compilation);
        }
        code.emitIfNotNull();
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        if (target instanceof ConditionalTarget) {
            ConditionalTarget conditionalTarget = (ConditionalTarget) target;
            emitCoerceOrNullMethod(variable, compilation);
            CodeAttr code = compilation.getCode();
            if (conditionalTarget.trueBranchComesFirst) {
                code.emitGotoIfCompare1(conditionalTarget.ifFalse, 198);
            } else {
                code.emitGotoIfCompare1(conditionalTarget.ifTrue, 199);
            }
            conditionalTarget.emitGotoFirstBranch(code);
            return;
        }
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.NodeType");
        typeNodeType = make;
        coerceMethod = make.getDeclaredMethod("coerceForce", 2);
        coerceOrNullMethod = make.getDeclaredMethod("coerceOrNull", 2);
    }

    public String toString() {
        return "NodeType " + getName();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        String name = getName();
        if (name == null) {
            name = "";
        }
        objectOutput.writeUTF(name);
        objectOutput.writeInt(this.kinds);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        String readUTF = objectInput.readUTF();
        if (readUTF.length() > 0) {
            setName(readUTF);
        }
        this.kinds = objectInput.readInt();
    }
}
