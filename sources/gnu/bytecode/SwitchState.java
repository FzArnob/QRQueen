package gnu.bytecode;

public class SwitchState {
    Label after_label;
    Label cases_label;
    Label defaultLabel;
    Label[] labels;
    int maxValue;
    int minValue;
    int numCases = 0;
    TryState outerTry;
    Label switch_label;
    int[] values;

    public int getMaxValue() {
        return this.maxValue;
    }

    public int getNumCases() {
        return this.numCases;
    }

    public SwitchState(CodeAttr codeAttr) {
        this.switch_label = new Label(codeAttr);
        this.cases_label = new Label(codeAttr);
        this.after_label = new Label(codeAttr);
        this.outerTry = codeAttr.try_stack;
    }

    public void switchValuePushed(CodeAttr codeAttr) {
        codeAttr.popType();
        this.cases_label.setTypes(codeAttr);
        codeAttr.fixupChain(this.cases_label, this.switch_label);
    }

    public boolean addCase(int i, CodeAttr codeAttr) {
        Label label = new Label(codeAttr);
        label.setTypes(this.cases_label);
        label.define(codeAttr);
        return insertCase(i, label, codeAttr);
    }

    public boolean addCaseGoto(int i, CodeAttr codeAttr, Label label) {
        boolean insertCase = insertCase(i, label, codeAttr);
        label.setTypes(this.cases_label);
        codeAttr.setUnreachable();
        return insertCase;
    }

    public void addDefault(CodeAttr codeAttr) {
        Label label = new Label(codeAttr);
        label.setTypes(this.cases_label);
        label.define(codeAttr);
        if (this.defaultLabel == null) {
            this.defaultLabel = label;
            return;
        }
        throw new Error();
    }

    public boolean insertCase(int i, Label label, CodeAttr codeAttr) {
        int[] iArr = this.values;
        if (iArr == null) {
            int[] iArr2 = new int[10];
            this.values = iArr2;
            Label[] labelArr = new Label[10];
            this.labels = labelArr;
            this.numCases = 1;
            this.maxValue = i;
            this.minValue = i;
            iArr2[0] = i;
            labelArr[0] = label;
            return true;
        }
        Label[] labelArr2 = this.labels;
        int i2 = this.numCases;
        if (i2 >= iArr.length) {
            this.values = new int[(i2 * 2)];
            this.labels = new Label[(i2 * 2)];
        }
        if (i < this.minValue) {
            this.minValue = i;
            i2 = 0;
        } else if (i > this.maxValue) {
            this.maxValue = i;
        } else {
            int i3 = i2 - 1;
            int i4 = 0;
            int i5 = 0;
            while (i5 <= i3) {
                i4 = (i5 + i3) >>> 1;
                if (iArr[i4] >= i) {
                    i3 = i4 - 1;
                } else {
                    i5 = i4 + 1;
                    i4 = i5;
                }
            }
            if (i == iArr[i4]) {
                return false;
            }
            i2 = i4;
        }
        int i6 = this.numCases - i2;
        int i7 = i2 + 1;
        System.arraycopy(iArr, i2, this.values, i7, i6);
        System.arraycopy(iArr, 0, this.values, 0, i2);
        this.values[i2] = i;
        System.arraycopy(labelArr2, i2, this.labels, i7, i6);
        System.arraycopy(labelArr2, 0, this.labels, 0, i2);
        this.labels[i2] = label;
        this.numCases++;
        return true;
    }

    public void exitSwitch(CodeAttr codeAttr) {
        if (this.outerTry == codeAttr.try_stack) {
            codeAttr.emitGoto(this.after_label);
            return;
        }
        throw new Error("exitSwitch cannot exit through a try");
    }

    public void finish(CodeAttr codeAttr) {
        int i;
        Label label;
        int i2 = 0;
        if (this.defaultLabel == null) {
            Label label2 = new Label(codeAttr);
            this.defaultLabel = label2;
            label2.define(codeAttr);
            ClassType make = ClassType.make("java.lang.RuntimeException");
            codeAttr.emitNew(make);
            codeAttr.emitDup((Type) make);
            codeAttr.emitPushString("bad case value!");
            codeAttr.emitInvokeSpecial(make.addMethod("<init>", 1, new Type[]{Type.string_type}, (Type) Type.voidType));
            codeAttr.emitThrow();
        }
        codeAttr.fixupChain(this.switch_label, this.after_label);
        int i3 = this.numCases;
        if (i3 <= 1) {
            codeAttr.pushType(Type.intType);
            if (this.numCases == 1) {
                int i4 = this.minValue;
                if (i4 == 0) {
                    codeAttr.emitIfIntEqZero();
                } else {
                    codeAttr.emitPushInt(i4);
                    codeAttr.emitIfEq();
                }
                codeAttr.emitGoto(this.labels[0]);
                codeAttr.emitElse();
                codeAttr.emitGoto(this.defaultLabel);
                codeAttr.emitFi();
            } else {
                codeAttr.emitPop(1);
                codeAttr.emitGoto(this.defaultLabel);
            }
        } else {
            int i5 = i3 * 2;
            int i6 = this.maxValue;
            int i7 = this.minValue;
            if (i5 >= i6 - i7) {
                codeAttr.reserve((((i6 - i7) + 1) * 4) + 13);
                codeAttr.fixupAdd(2, (Label) null);
                codeAttr.put1(170);
                codeAttr.fixupAdd(3, this.defaultLabel);
                codeAttr.PC += 4;
                codeAttr.put4(this.minValue);
                codeAttr.put4(this.maxValue);
                int i8 = this.minValue;
                while (i8 <= this.maxValue) {
                    if (this.values[i2] == i8) {
                        i = i2 + 1;
                        label = this.labels[i2];
                    } else {
                        i = i2;
                        label = this.defaultLabel;
                    }
                    codeAttr.fixupAdd(3, label);
                    codeAttr.PC += 4;
                    i8++;
                    i2 = i;
                }
            } else {
                codeAttr.reserve((i3 * 8) + 9);
                codeAttr.fixupAdd(2, (Label) null);
                codeAttr.put1(171);
                codeAttr.fixupAdd(3, this.defaultLabel);
                codeAttr.PC += 4;
                codeAttr.put4(this.numCases);
                while (i2 < this.numCases) {
                    codeAttr.put4(this.values[i2]);
                    codeAttr.fixupAdd(3, this.labels[i2]);
                    codeAttr.PC += 4;
                    i2++;
                }
            }
        }
        codeAttr.fixupChain(this.after_label, this.cases_label);
    }
}
