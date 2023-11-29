package gnu.bytecode;

public class Scope {
    Label end;
    Scope firstChild;
    Scope lastChild;
    Variable last_var;
    Scope nextSibling;
    Scope parent;
    boolean preserved;
    Label start;
    Variable vars;

    public Scope() {
    }

    public Scope(Label label, Label label2) {
        this.start = label;
        this.end = label2;
    }

    public final Variable firstVar() {
        return this.vars;
    }

    public VarEnumerator allVars() {
        return new VarEnumerator(this);
    }

    public void linkChild(Scope scope) {
        this.parent = scope;
        if (scope != null) {
            Scope scope2 = scope.lastChild;
            if (scope2 == null) {
                scope.firstChild = this;
            } else {
                scope2.nextSibling = this;
            }
            scope.lastChild = this;
        }
    }

    public Variable addVariable(CodeAttr codeAttr, Type type, String str) {
        Variable variable = new Variable(str, type);
        addVariable(codeAttr, variable);
        return variable;
    }

    public void addVariable(Variable variable) {
        Variable variable2 = this.last_var;
        if (variable2 == null) {
            this.vars = variable;
        } else {
            variable2.next = variable;
        }
        this.last_var = variable;
        variable.scope = this;
    }

    public void addVariableAfter(Variable variable, Variable variable2) {
        if (variable == null) {
            variable2.next = this.vars;
            this.vars = variable2;
        } else {
            variable2.next = variable.next;
            variable.next = variable2;
        }
        if (this.last_var == variable) {
            this.last_var = variable2;
        }
        if (variable2.next != variable2) {
            variable2.scope = this;
            return;
        }
        throw new Error("cycle");
    }

    public void addVariable(CodeAttr codeAttr, Variable variable) {
        addVariable(variable);
        if (variable.isSimple() && codeAttr != null) {
            variable.allocateLocal(codeAttr);
        }
    }

    public Variable getVariable(int i) {
        Variable variable = this.vars;
        while (true) {
            i--;
            if (i < 0) {
                return variable;
            }
            variable = variable.next;
        }
    }

    static boolean equals(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        if (bArr == bArr2) {
            return true;
        }
        int length = bArr.length;
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (bArr[length] == bArr2[length]);
        return false;
    }

    public void setStartPC(CodeAttr codeAttr) {
        if (this.start == null) {
            this.start = new Label();
        }
        boolean reachableHere = codeAttr.reachableHere();
        this.start.define(codeAttr);
        codeAttr.setReachable(reachableHere);
    }

    public void noteStartFunction(CodeAttr codeAttr) {
        setStartPC(codeAttr);
        this.start.setTypes(codeAttr);
    }

    public Variable lookup(String str) {
        for (Variable variable = this.vars; variable != null; variable = variable.next) {
            if (str.equals(variable.name)) {
                return variable;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void freeLocals(CodeAttr codeAttr) {
        if (!this.preserved) {
            for (Variable variable = this.vars; variable != null; variable = variable.next) {
                if (variable.isSimple() && !variable.dead()) {
                    variable.freeLocal(codeAttr);
                }
            }
            for (Scope scope = this.firstChild; scope != null; scope = scope.nextSibling) {
                if (scope.preserved) {
                    scope.preserved = false;
                    scope.freeLocals(codeAttr);
                }
            }
        }
    }
}
