package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class OccurrenceType extends ObjectType implements Externalizable, TypeValue {
    public static final Type emptySequenceType = getInstance(SingletonType.instance, 0, 0);
    static final Method isInstanceMethod;
    public static final ClassType typeOccurrenceType;
    Type base;
    int maxOccurs;
    int minOccurs;

    public Expression convertValue(Expression expression) {
        return null;
    }

    public Procedure getConstructor() {
        return null;
    }

    public Type getBase() {
        return this.base;
    }

    public int minOccurs() {
        return this.minOccurs;
    }

    public int maxOccurs() {
        return this.maxOccurs;
    }

    public OccurrenceType(Type type, int i, int i2) {
        this.base = type;
        this.minOccurs = i;
        this.maxOccurs = i2;
    }

    public static Type getInstance(Type type, int i, int i2) {
        if (i == 1 && i2 == 1) {
            return type;
        }
        if (i == 0 && i2 < 0 && (type == SingletonType.instance || type == Type.pointer_type)) {
            return Type.pointer_type;
        }
        return new OccurrenceType(type, i, i2);
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.reflect.OccurrenceType");
        typeOccurrenceType = make;
        isInstanceMethod = make.getDeclaredMethod("isInstance", 1);
    }

    public Type getImplementationType() {
        return Type.pointer_type;
    }

    public int compare(Type type) {
        if (!(type instanceof OccurrenceType)) {
            return -2;
        }
        OccurrenceType occurrenceType = (OccurrenceType) type;
        if (this.minOccurs == occurrenceType.minOccurs && this.maxOccurs == occurrenceType.maxOccurs) {
            return this.base.compare(occurrenceType.getBase());
        }
        return -2;
    }

    public Object coerceFromObject(Object obj) {
        if (!(obj instanceof Values) && this.minOccurs <= 1 && this.maxOccurs != 0) {
            return this.base.coerceFromObject(obj);
        }
        if (isInstance(obj)) {
            return obj;
        }
        throw new ClassCastException();
    }

    public boolean isInstance(Object obj) {
        int i;
        int i2;
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int startPos = values.startPos();
            Type type = this.base;
            if (type instanceof ItemPredicate) {
                ItemPredicate itemPredicate = (ItemPredicate) type;
                int i3 = 0;
                while (true) {
                    boolean isInstancePos = itemPredicate.isInstancePos(values, startPos);
                    startPos = values.nextPos(startPos);
                    if (startPos == 0) {
                        if (i3 < this.minOccurs || ((i2 = this.maxOccurs) >= 0 && i3 > i2)) {
                            return false;
                        }
                        return true;
                    } else if (!isInstancePos) {
                        return false;
                    } else {
                        i3++;
                    }
                }
            } else {
                int i4 = 0;
                while (true) {
                    startPos = values.nextPos(startPos);
                    if (startPos != 0) {
                        if (!this.base.isInstance(values.getPosPrevious(startPos))) {
                            return false;
                        }
                        i4++;
                    } else if (i4 < this.minOccurs || ((i = this.maxOccurs) >= 0 && i4 > i)) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else if (this.minOccurs > 1 || this.maxOccurs == 0) {
            return false;
        } else {
            return this.base.isInstance(obj);
        }
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (variable != null) {
            code.emitLoad(variable);
        }
        if (declaration != null) {
            code.emitDup();
            declaration.compileStore(compilation);
        }
        compilation.compileConstant(this);
        code.emitSwap();
        code.emitInvokeVirtual(isInstanceMethod);
        code.emitIfIntNotZero();
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target) {
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004b, code lost:
        if (r3 > 1048575) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int itemCountRange(gnu.bytecode.Type r7) {
        /*
            boolean r0 = r7 instanceof gnu.kawa.reflect.SingletonType
            r1 = 4097(0x1001, float:5.741E-42)
            if (r0 == 0) goto L_0x0007
            return r1
        L_0x0007:
            boolean r0 = r7 instanceof gnu.kawa.reflect.OccurrenceType
            r2 = 0
            if (r0 == 0) goto L_0x0053
            gnu.kawa.reflect.OccurrenceType r7 = (gnu.kawa.reflect.OccurrenceType) r7
            int r0 = r7.minOccurs()
            int r3 = r7.maxOccurs()
            gnu.bytecode.Type r7 = r7.getBase()
            int r7 = itemCountRange(r7)
            r4 = 1
            if (r0 != r4) goto L_0x0023
            if (r3 == r4) goto L_0x0025
        L_0x0023:
            if (r7 != 0) goto L_0x0026
        L_0x0025:
            return r7
        L_0x0026:
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r5 = -1
            if (r3 <= r4) goto L_0x002d
            r3 = -1
        L_0x002d:
            if (r3 != 0) goto L_0x0030
            return r2
        L_0x0030:
            r2 = r7 & 4095(0xfff, float:5.738E-42)
            int r6 = r7 >> 12
            if (r7 == r1) goto L_0x004e
            r7 = 4095(0xfff, float:5.738E-42)
            if (r0 <= r7) goto L_0x003c
            r0 = 4095(0xfff, float:5.738E-42)
        L_0x003c:
            int r0 = r0 * r2
            if (r0 <= r7) goto L_0x0042
            r0 = 4095(0xfff, float:5.738E-42)
        L_0x0042:
            if (r3 < 0) goto L_0x004a
            if (r6 >= 0) goto L_0x0047
            goto L_0x004a
        L_0x0047:
            int r3 = r3 * r6
            goto L_0x004b
        L_0x004a:
            r3 = -1
        L_0x004b:
            if (r3 <= r4) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r5 = r3
        L_0x004f:
            int r7 = r5 << 12
            r7 = r7 | r0
            return r7
        L_0x0053:
            boolean r0 = r7 instanceof gnu.bytecode.PrimType
            if (r0 == 0) goto L_0x005f
            boolean r7 = r7.isVoid()
            if (r7 == 0) goto L_0x005e
            r1 = 0
        L_0x005e:
            return r1
        L_0x005f:
            boolean r0 = r7 instanceof gnu.bytecode.ArrayType
            if (r0 == 0) goto L_0x0064
            return r1
        L_0x0064:
            boolean r0 = r7 instanceof gnu.bytecode.ObjectType
            if (r0 == 0) goto L_0x0072
            gnu.bytecode.ClassType r0 = gnu.expr.Compilation.typeValues
            int r7 = r7.compare(r0)
            r0 = -3
            if (r7 != r0) goto L_0x0072
            return r1
        L_0x0072:
            r7 = -4096(0xfffffffffffff000, float:NaN)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.OccurrenceType.itemCountRange(gnu.bytecode.Type):int");
    }

    public static char itemCountCode(Type type) {
        int itemCountRange = itemCountRange(type);
        int i = itemCountRange & 4095;
        int i2 = itemCountRange >> 12;
        if (i2 == 0) {
            return '0';
        }
        return i == 0 ? i2 == 1 ? '?' : '*' : (i == 1 && i2 == 1) ? '1' : '+';
    }

    public static boolean itemCountIsZeroOrOne(Type type) {
        return (itemCountRange(type) >> 13) == 0;
    }

    public static boolean itemCountIsOne(Type type) {
        return itemCountRange(type) == 4097;
    }

    public static Type itemPrimeType(Type type) {
        while (type instanceof OccurrenceType) {
            type = ((OccurrenceType) type).getBase();
        }
        return itemCountIsOne(type) ? type : SingletonType.instance;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.base);
        objectOutput.writeInt(this.minOccurs);
        objectOutput.writeInt(this.maxOccurs);
    }

    public String toString() {
        String type = this.base.toString();
        boolean z = type == null || type.indexOf(32) >= 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append('(');
        }
        stringBuffer.append(type);
        if (z) {
            stringBuffer.append(')');
        }
        int i = this.minOccurs;
        if (!(i == 1 && this.maxOccurs == 1)) {
            if (i == 0 && this.maxOccurs == 1) {
                stringBuffer.append('?');
            } else if (i == 1 && this.maxOccurs == -1) {
                stringBuffer.append('+');
            } else if (i == 0 && this.maxOccurs == -1) {
                stringBuffer.append('*');
            } else {
                stringBuffer.append('{');
                stringBuffer.append(this.minOccurs);
                stringBuffer.append(',');
                int i2 = this.maxOccurs;
                if (i2 >= 0) {
                    stringBuffer.append(i2);
                } else {
                    stringBuffer.append('*');
                }
                stringBuffer.append('}');
            }
        }
        return stringBuffer.toString();
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.base = (Type) objectInput.readObject();
        this.minOccurs = objectInput.readInt();
        this.maxOccurs = objectInput.readInt();
    }
}
