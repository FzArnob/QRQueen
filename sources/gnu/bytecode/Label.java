package gnu.bytecode;

import java.util.ArrayList;
import java.util.Iterator;

public class Label {
    int first_fixup;
    Type[] localTypes;
    boolean needsStackMapEntry;
    int position;
    Type[] stackTypes;
    private Object[] typeChangeListeners;

    public final boolean defined() {
        return this.position >= 0;
    }

    public Label() {
        this(-1);
    }

    public Label(CodeAttr codeAttr) {
        this(-1);
    }

    public Label(int i) {
        this.position = i;
    }

    /* access modifiers changed from: package-private */
    public Type mergeTypes(Type type, Type type2) {
        if ((type instanceof PrimType) != (type2 instanceof PrimType)) {
            return null;
        }
        return Type.lowestCommonSuperType(type, type2);
    }

    /* access modifiers changed from: package-private */
    public void setTypes(Type[] typeArr, int i, Type[] typeArr2, int i2) {
        while (i > 0 && typeArr[i - 1] == null) {
            i--;
        }
        Type[] typeArr3 = this.stackTypes;
        if (typeArr3 == null) {
            if (i2 == 0) {
                this.stackTypes = Type.typeArray0;
            } else {
                Type[] typeArr4 = new Type[i2];
                this.stackTypes = typeArr4;
                System.arraycopy(typeArr2, 0, typeArr4, 0, i2);
            }
            if (i == 0) {
                this.localTypes = Type.typeArray0;
                return;
            }
            Type[] typeArr5 = new Type[i];
            this.localTypes = typeArr5;
            System.arraycopy(typeArr, 0, typeArr5, 0, i);
        } else if (i2 == typeArr3.length) {
            for (int i3 = 0; i3 < i2; i3++) {
                Type[] typeArr6 = this.stackTypes;
                typeArr6[i3] = mergeTypes(typeArr6[i3], typeArr2[i3]);
            }
            Type[] typeArr7 = this.localTypes;
            int length = i < typeArr7.length ? i : typeArr7.length;
            for (int i4 = 0; i4 < length; i4++) {
                mergeLocalType(i4, typeArr[i4]);
            }
            while (true) {
                Type[] typeArr8 = this.localTypes;
                if (i < typeArr8.length) {
                    typeArr8[i] = null;
                    i++;
                } else {
                    return;
                }
            }
        } else {
            throw new InternalError("inconsistent stack length");
        }
    }

    public void setTypes(CodeAttr codeAttr) {
        addTypeChangeListeners(codeAttr);
        if (this.stackTypes == null || codeAttr.SP == this.stackTypes.length) {
            setTypes(codeAttr.local_types, codeAttr.local_types == null ? 0 : codeAttr.local_types.length, codeAttr.stack_types, codeAttr.SP);
            return;
        }
        throw new InternalError();
    }

    public void setTypes(Label label) {
        Type[] typeArr = label.localTypes;
        int length = typeArr.length;
        Type[] typeArr2 = label.stackTypes;
        setTypes(typeArr, length, typeArr2, typeArr2.length);
    }

    private void mergeLocalType(int i, Type type) {
        Type type2 = this.localTypes[i];
        Type mergeTypes = mergeTypes(type2, type);
        this.localTypes[i] = mergeTypes;
        if (mergeTypes != type2) {
            notifyTypeChangeListeners(i, mergeTypes);
        }
    }

    private void notifyTypeChangeListeners(int i, Type type) {
        Object obj;
        Object[] objArr = this.typeChangeListeners;
        if (objArr != null && objArr.length > i && (obj = objArr[i]) != null) {
            if (obj instanceof Label) {
                ((Label) obj).mergeLocalType(i, type);
            } else {
                Iterator it = ((ArrayList) obj).iterator();
                while (it.hasNext()) {
                    ((Label) it.next()).mergeLocalType(i, type);
                }
            }
            if (type == null) {
                objArr[i] = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addTypeChangeListener(int i, Label label) {
        ArrayList arrayList;
        Object[] objArr = this.typeChangeListeners;
        if (objArr == null) {
            objArr = new Object[(i + 10)];
            this.typeChangeListeners = objArr;
        } else if (objArr.length <= i) {
            Object[] objArr2 = new Object[(i + 10)];
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
            this.typeChangeListeners = objArr2;
            objArr = objArr2;
        }
        Object obj = objArr[i];
        if (obj == null) {
            objArr[i] = label;
            return;
        }
        if (obj instanceof Label) {
            arrayList = new ArrayList();
            arrayList.add((Label) obj);
            objArr[i] = arrayList;
        } else {
            arrayList = obj;
        }
        arrayList.add(label);
    }

    /* access modifiers changed from: package-private */
    public void addTypeChangeListeners(CodeAttr codeAttr) {
        if (codeAttr.local_types != null && codeAttr.previousLabel != null) {
            int length = codeAttr.local_types.length;
            for (int i = 0; i < length; i++) {
                if (codeAttr.local_types[i] != null && (codeAttr.varsSetInCurrentBlock == null || codeAttr.varsSetInCurrentBlock.length <= i || !codeAttr.varsSetInCurrentBlock[i])) {
                    codeAttr.previousLabel.addTypeChangeListener(i, this);
                }
            }
        }
    }

    public void defineRaw(CodeAttr codeAttr) {
        if (this.position < 0) {
            this.position = codeAttr.PC;
            int i = codeAttr.fixup_count;
            this.first_fixup = i;
            if (i >= 0) {
                codeAttr.fixupAdd(1, this);
                return;
            }
            return;
        }
        throw new Error("label definition more than once");
    }

    public void define(CodeAttr codeAttr) {
        if (codeAttr.reachableHere()) {
            setTypes(codeAttr);
        } else {
            Type[] typeArr = this.localTypes;
            if (typeArr != null) {
                int length = typeArr.length;
                while (true) {
                    length--;
                    if (length < 0) {
                        break;
                    } else if (this.localTypes[length] != null && (codeAttr.locals.used == null || codeAttr.locals.used[length] == null)) {
                        this.localTypes[length] = null;
                    }
                }
            }
        }
        codeAttr.previousLabel = this;
        codeAttr.varsSetInCurrentBlock = null;
        defineRaw(codeAttr);
        if (this.localTypes != null) {
            codeAttr.setTypes(this);
        }
        codeAttr.setReachable(true);
    }
}
