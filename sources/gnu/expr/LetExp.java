package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.OutPort;

public class LetExp extends ScopeExp {
    public Expression body;
    public Expression[] inits;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public LetExp(Expression[] expressionArr) {
        this.inits = expressionArr;
    }

    public Expression getBody() {
        return this.body;
    }

    public void setBody(Expression expression) {
        this.body = expression;
    }

    /* access modifiers changed from: protected */
    public Object evalVariable(int i, CallContext callContext) throws Throwable {
        return this.inits[i].eval(callContext);
    }

    public void apply(CallContext callContext) throws Throwable {
        setIndexes();
        int nesting = ScopeExp.nesting(this);
        Object[][] objArr = new Object[this.frameSize];
        objArr = callContext.evalFrames;
        int i = 0;
        if (objArr == null) {
            objArr = new Object[(nesting + 10)][];
            callContext.evalFrames = objArr;
        } else if (nesting >= objArr.length) {
            Object[][] objArr2 = new Object[(nesting + 10)][];
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
            callContext.evalFrames = objArr2;
            objArr = objArr2;
        }
        Object[] objArr3 = objArr[nesting];
        objArr[nesting] = objArr;
        try {
            Declaration firstDecl = firstDecl();
            while (firstDecl != null) {
                if (this.inits[nesting] != QuoteExp.undefined_exp) {
                    Location evalVariable = evalVariable(nesting, callContext);
                    Type type = firstDecl.type;
                    if (!(type == null || type == Type.pointer_type)) {
                        evalVariable = type.coerceFromObject(evalVariable);
                    }
                    if (firstDecl.isIndirectBinding()) {
                        Location makeIndirectLocationFor = firstDecl.makeIndirectLocationFor();
                        makeIndirectLocationFor.set(evalVariable);
                        evalVariable = makeIndirectLocationFor;
                    }
                }
                firstDecl = firstDecl.nextDecl();
                i = nesting + 1;
            }
            this.body.apply(callContext);
            objArr[nesting] = objArr3;
        } finally {
            objArr[nesting] = objArr3;
        }
    }

    /* access modifiers changed from: package-private */
    public void store_rest(Compilation compilation, int i, Declaration declaration) {
        if (declaration != null) {
            store_rest(compilation, i + 1, declaration.nextDecl());
            if (declaration.needsInit()) {
                if (declaration.isIndirectBinding()) {
                    CodeAttr code = compilation.getCode();
                    if (this.inits[i] == QuoteExp.undefined_exp) {
                        Object symbol = declaration.getSymbol();
                        compilation.compileConstant(symbol, Target.pushObject);
                        code.emitInvokeStatic(BindingInitializer.makeLocationMethod(symbol));
                    } else {
                        declaration.pushIndirectBinding(compilation);
                    }
                }
                declaration.compileStore(compilation);
            }
        }
    }

    public void compile(Compilation compilation, Target target) {
        Target target2;
        CodeAttr code = compilation.getCode();
        Declaration firstDecl = firstDecl();
        int i = 0;
        while (true) {
            Expression[] expressionArr = this.inits;
            if (i < expressionArr.length) {
                Expression expression = expressionArr[i];
                boolean needsInit = firstDecl.needsInit();
                if (needsInit && firstDecl.isSimple()) {
                    firstDecl.allocateVariable(code);
                }
                if (!needsInit || (firstDecl.isIndirectBinding() && expression == QuoteExp.undefined_exp)) {
                    target2 = Target.Ignore;
                } else {
                    Type type = firstDecl.getType();
                    target2 = CheckedTarget.getInstance(firstDecl);
                    if (expression == QuoteExp.undefined_exp) {
                        if (type instanceof PrimType) {
                            expression = new QuoteExp(new Byte((byte) 0));
                        } else if (!(type == null || type == Type.pointer_type)) {
                            expression = QuoteExp.nullExp;
                        }
                    }
                }
                expression.compileWithPosition(compilation, target2);
                i++;
                firstDecl = firstDecl.nextDecl();
            } else {
                code.enterScope(getVarScope());
                store_rest(compilation, 0, firstDecl());
                this.body.compileWithPosition(compilation, target);
                popScope(code);
                return;
            }
        }
    }

    public final Type getType() {
        return this.body.getType();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitLetExp(this, d);
    }

    public <R, D> void visitInitializers(ExpVisitor<R, D> expVisitor, D d) {
        Declaration firstDecl = firstDecl();
        int i = 0;
        while (true) {
            Expression[] expressionArr = this.inits;
            if (i < expressionArr.length) {
                Expression expression = expressionArr[i];
                if (expression != null) {
                    Expression visitAndUpdate = expVisitor.visitAndUpdate(expression, d);
                    if (visitAndUpdate != null) {
                        this.inits[i] = visitAndUpdate;
                        if (firstDecl.value == expression) {
                            firstDecl.value = visitAndUpdate;
                        }
                        i++;
                        firstDecl = firstDecl.nextDecl();
                    } else {
                        throw new Error("null2 init for " + this + " was:" + expression);
                    }
                } else {
                    throw new Error("null1 init for " + this + " i:" + i + " d:" + firstDecl);
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        visitInitializers(expVisitor, d);
        if (expVisitor.exitValue == null) {
            this.body = expVisitor.visitAndUpdate(this.body, d);
        }
    }

    public void print(OutPort outPort) {
        print(outPort, "(Let", ")");
    }

    public void print(OutPort outPort, String str, String str2) {
        outPort.startLogicalBlock(str + "#" + this.id, str2, 2);
        outPort.writeSpaceFill();
        printLineColumn(outPort);
        outPort.startLogicalBlock("(", false, ")");
        int i = 0;
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (i > 0) {
                outPort.writeSpaceFill();
            }
            outPort.startLogicalBlock("(", false, ")");
            firstDecl.printInfo(outPort);
            if (this.inits != null) {
                outPort.writeSpaceFill();
                outPort.print('=');
                outPort.writeSpaceFill();
                Expression[] expressionArr = this.inits;
                if (i >= expressionArr.length) {
                    outPort.print("<missing init>");
                } else {
                    Expression expression = expressionArr[i];
                    if (expression == null) {
                        outPort.print("<null>");
                    } else {
                        expression.print(outPort);
                    }
                }
                i++;
            }
            outPort.endLogicalBlock(")");
        }
        outPort.endLogicalBlock(")");
        outPort.writeSpaceLinear();
        Expression expression2 = this.body;
        if (expression2 == null) {
            outPort.print("<null body>");
        } else {
            expression2.print(outPort);
        }
        outPort.endLogicalBlock(str2);
    }
}
