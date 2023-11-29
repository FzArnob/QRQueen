package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.text.SourceMessages;

public class ApplyExp extends Expression {
    public static final int INLINE_IF_CONSTANT = 4;
    public static final int MAY_CONTAIN_BACK_JUMP = 8;
    public static final int TAILCALL = 2;
    Expression[] args;
    LambdaExp context;
    Expression func;
    public ApplyExp nextCall;
    protected Type type;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public final Expression getFunction() {
        return this.func;
    }

    public final Expression[] getArgs() {
        return this.args;
    }

    public final int getArgCount() {
        return this.args.length;
    }

    public void setFunction(Expression expression) {
        this.func = expression;
    }

    public void setArgs(Expression[] expressionArr) {
        this.args = expressionArr;
    }

    public Expression getArg(int i) {
        return this.args[i];
    }

    public void setArg(int i, Expression expression) {
        this.args[i] = expression;
    }

    public final boolean isTailCall() {
        return getFlag(2);
    }

    public final void setTailCall(boolean z) {
        setFlag(z, 2);
    }

    public final Object getFunctionValue() {
        Expression expression = this.func;
        if (expression instanceof QuoteExp) {
            return ((QuoteExp) expression).getValue();
        }
        return null;
    }

    public ApplyExp(Expression expression, Expression... expressionArr) {
        this.func = expression;
        this.args = expressionArr;
    }

    public ApplyExp(Procedure procedure, Expression... expressionArr) {
        this.func = new QuoteExp(procedure);
        this.args = expressionArr;
    }

    public ApplyExp(Method method, Expression... expressionArr) {
        this((Expression) new QuoteExp(new PrimProcedure(method)), expressionArr);
    }

    public void apply(CallContext callContext) throws Throwable {
        Object eval = this.func.eval(callContext);
        int length = this.args.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = this.args[i].eval(callContext);
        }
        ((Procedure) eval).checkN(objArr, callContext);
    }

    public static void compileToArray(Expression[] expressionArr, Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (expressionArr.length == 0) {
            code.emitGetStatic(Compilation.noArgsField);
            return;
        }
        code.emitPushInt(expressionArr.length);
        code.emitNewArray((Type) Type.pointer_type);
        for (int i = 0; i < expressionArr.length; i++) {
            Expression expression = expressionArr[i];
            if (!compilation.usingCPStyle() || (expression instanceof QuoteExp) || (expression instanceof ReferenceExp)) {
                code.emitDup((Type) Compilation.objArrayType);
                code.emitPushInt(i);
                expression.compileWithPosition(compilation, Target.pushObject);
            } else {
                expression.compileWithPosition(compilation, Target.pushObject);
                code.emitSwap();
                code.emitDup(1, 1);
                code.emitSwap();
                code.emitPushInt(i);
                code.emitSwap();
            }
            code.emitArrayStore(Type.pointer_type);
        }
    }

    public void compile(Compilation compilation, Target target) {
        compile(this, compilation, target, true);
    }

    public static void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        compile(applyExp, compilation, target, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: gnu.bytecode.Method} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v8, types: [gnu.bytecode.Type] */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009b A[LOOP:1: B:45:0x009b->B:46:0x009d, LOOP_START, PHI: r7 
      PHI: (r7v3 int) = (r7v0 int), (r7v4 int) binds: [B:44:0x0099, B:46:0x009d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00a8 A[SYNTHETIC, Splitter:B:48:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void compile(gnu.expr.ApplyExp r11, gnu.expr.Compilation r12, gnu.expr.Target r13, boolean r14) {
        /*
            gnu.expr.Expression[] r0 = r11.args
            int r0 = r0.length
            gnu.expr.Expression r1 = r11.func
            boolean r2 = r1 instanceof gnu.expr.LambdaExp
            r3 = 0
            if (r2 == 0) goto L_0x0014
            r2 = r1
            gnu.expr.LambdaExp r2 = (gnu.expr.LambdaExp) r2
            r2.getName()
            r4 = r3
        L_0x0011:
            r5 = r4
            goto L_0x0085
        L_0x0014:
            boolean r2 = r1 instanceof gnu.expr.ReferenceExp
            if (r2 == 0) goto L_0x0074
            r2 = r1
            gnu.expr.ReferenceExp r2 = (gnu.expr.ReferenceExp) r2
            gnu.expr.Declaration r4 = r2.contextDecl()
            gnu.expr.Declaration r2 = r2.binding
        L_0x0021:
            if (r2 == 0) goto L_0x0047
            boolean r5 = r2.isAlias()
            if (r5 == 0) goto L_0x0047
            gnu.expr.Expression r5 = r2.value
            boolean r5 = r5 instanceof gnu.expr.ReferenceExp
            if (r5 == 0) goto L_0x0047
            gnu.expr.Expression r5 = r2.value
            gnu.expr.ReferenceExp r5 = (gnu.expr.ReferenceExp) r5
            if (r4 != 0) goto L_0x0047
            boolean r6 = r2.needsContext()
            if (r6 != 0) goto L_0x0047
            gnu.expr.Declaration r6 = r5.binding
            if (r6 != 0) goto L_0x0040
            goto L_0x0047
        L_0x0040:
            gnu.expr.Declaration r2 = r5.binding
            gnu.expr.Declaration r4 = r5.contextDecl()
            goto L_0x0021
        L_0x0047:
            r5 = 65536(0x10000, double:3.2379E-319)
            boolean r5 = r2.getFlag(r5)
            if (r5 != 0) goto L_0x0071
            gnu.expr.Expression r5 = r2.getValue()
            r2.getName()
            if (r5 == 0) goto L_0x0061
            boolean r2 = r5 instanceof gnu.expr.LambdaExp
            if (r2 == 0) goto L_0x0061
            r2 = r5
            gnu.expr.LambdaExp r2 = (gnu.expr.LambdaExp) r2
            goto L_0x0062
        L_0x0061:
            r2 = r3
        L_0x0062:
            if (r5 == 0) goto L_0x006f
            boolean r6 = r5 instanceof gnu.expr.QuoteExp
            if (r6 == 0) goto L_0x006f
            gnu.expr.QuoteExp r5 = (gnu.expr.QuoteExp) r5
            java.lang.Object r5 = r5.getValue()
            goto L_0x0085
        L_0x006f:
            r5 = r3
            goto L_0x0085
        L_0x0071:
            r2 = r3
            r5 = r2
            goto L_0x0085
        L_0x0074:
            boolean r2 = r1 instanceof gnu.expr.QuoteExp
            if (r2 == 0) goto L_0x0082
            r2 = r1
            gnu.expr.QuoteExp r2 = (gnu.expr.QuoteExp) r2
            java.lang.Object r5 = r2.getValue()
            r2 = r3
            r4 = r2
            goto L_0x0085
        L_0x0082:
            r2 = r3
            r4 = r2
            goto L_0x0011
        L_0x0085:
            r6 = 101(0x65, float:1.42E-43)
            r7 = 0
            if (r14 == 0) goto L_0x00d1
            boolean r14 = r5 instanceof gnu.mapping.Procedure
            if (r14 == 0) goto L_0x00d1
            r14 = r5
            gnu.mapping.Procedure r14 = (gnu.mapping.Procedure) r14
            boolean r8 = r13 instanceof gnu.expr.IgnoreTarget
            if (r8 == 0) goto L_0x00a8
            boolean r8 = r14.isSideEffectFree()
            if (r8 == 0) goto L_0x00a8
        L_0x009b:
            if (r7 >= r0) goto L_0x00a7
            gnu.expr.Expression[] r14 = r11.args
            r14 = r14[r7]
            r14.compile((gnu.expr.Compilation) r12, (gnu.expr.Target) r13)
            int r7 = r7 + 1
            goto L_0x009b
        L_0x00a7:
            return
        L_0x00a8:
            boolean r14 = inlineCompile(r14, r11, r12, r13)     // Catch:{ all -> 0x00af }
            if (r14 == 0) goto L_0x00d1
            return
        L_0x00af:
            r11 = move-exception
            gnu.text.SourceMessages r12 = r12.getMessages()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "caught exception in inline-compiler for "
            r13.append(r14)
            r13.append(r5)
            java.lang.String r14 = " - "
            r13.append(r14)
            r13.append(r11)
            java.lang.String r13 = r13.toString()
            r12.error((char) r6, (java.lang.String) r13, (java.lang.Throwable) r11)
            return
        L_0x00d1:
            gnu.bytecode.CodeAttr r14 = r12.getCode()
            r5 = 2
            if (r2 == 0) goto L_0x0150
            int r8 = r2.max_args
            if (r8 < 0) goto L_0x00e0
            int r8 = r2.max_args
            if (r0 > r8) goto L_0x0139
        L_0x00e0:
            int r8 = r2.min_args
            if (r0 < r8) goto L_0x0139
            int r8 = r2.getCallConvention()
            boolean r9 = r12.inlineOk((gnu.expr.Expression) r2)
            if (r9 == 0) goto L_0x0150
            if (r8 <= r5) goto L_0x00f9
            r9 = 3
            if (r8 != r9) goto L_0x0150
            boolean r8 = r11.isTailCall()
            if (r8 != 0) goto L_0x0150
        L_0x00f9:
            gnu.bytecode.Method r8 = r2.getMethod(r0)
            if (r8 == 0) goto L_0x0150
            gnu.expr.PrimProcedure r0 = new gnu.expr.PrimProcedure
            r0.<init>((gnu.bytecode.Method) r8, (gnu.expr.LambdaExp) r2)
            boolean r1 = r8.getStaticFlag()
            if (r1 == 0) goto L_0x0110
            gnu.bytecode.Variable r5 = r2.declareClosureEnv()
            if (r5 == 0) goto L_0x0131
        L_0x0110:
            gnu.expr.LambdaExp r5 = r12.curLambda
            if (r5 != r2) goto L_0x0121
            gnu.bytecode.Variable r4 = r2.closureEnv
            if (r4 == 0) goto L_0x011b
            gnu.bytecode.Variable r2 = r2.closureEnv
            goto L_0x011d
        L_0x011b:
            gnu.bytecode.Variable r2 = r2.thisVariable
        L_0x011d:
            r14.emitLoad(r2)
            goto L_0x0130
        L_0x0121:
            if (r4 == 0) goto L_0x0129
            gnu.expr.Target r14 = gnu.expr.Target.pushObject
            r4.load(r3, r7, r12, r14)
            goto L_0x0130
        L_0x0129:
            gnu.expr.LambdaExp r14 = r2.getOwningLambda()
            r14.loadHeapFrame(r12)
        L_0x0130:
            r7 = r1
        L_0x0131:
            if (r7 == 0) goto L_0x0135
            gnu.bytecode.PrimType r3 = gnu.bytecode.Type.voidType
        L_0x0135:
            r0.compile(r3, r11, r12, r13)
            return
        L_0x0139:
            java.lang.Error r11 = new java.lang.Error
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "internal error - wrong number of parameters for "
            r12.append(r13)
            r12.append(r2)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L_0x0150:
            boolean r4 = r11.isTailCall()
            r8 = 1
            if (r4 == 0) goto L_0x015f
            if (r2 == 0) goto L_0x015f
            gnu.expr.LambdaExp r4 = r12.curLambda
            if (r2 != r4) goto L_0x015f
            r4 = 1
            goto L_0x0160
        L_0x015f:
            r4 = 0
        L_0x0160:
            if (r2 == 0) goto L_0x01ac
            boolean r9 = r2.getInlineOnly()
            if (r9 == 0) goto L_0x01ac
            if (r4 != 0) goto L_0x01ac
            int r9 = r2.min_args
            if (r9 != r0) goto L_0x01ac
            gnu.expr.Expression[] r11 = r11.args
            pushArgs(r2, r11, r3, r12)
            r11 = 128(0x80, float:1.794E-43)
            boolean r0 = r2.getFlag(r11)
            if (r0 == 0) goto L_0x0186
            popParams(r14, r2, r3, r7)
            gnu.bytecode.Scope r11 = r2.getVarScope()
            r14.emitTailCall(r7, r11)
            return
        L_0x0186:
            int r0 = r2.flags
            r11 = r11 | r0
            r2.flags = r11
            gnu.expr.LambdaExp r11 = r12.curLambda
            r12.curLambda = r2
            r2.allocChildClasses(r12)
            r2.allocParameters(r12)
            popParams(r14, r2, r3, r7)
            r2.enterFunction(r12)
            gnu.expr.Expression r0 = r2.body
            r0.compileWithPosition(r12, r13)
            r2.compileEnd(r12)
            r2.generateApplyMethods(r12)
            r14.popScope()
            r12.curLambda = r11
            return
        L_0x01ac:
            gnu.expr.LambdaExp r9 = r12.curLambda
            boolean r9 = r9.isHandlingTailCalls()
            r10 = 4
            if (r9 == 0) goto L_0x0249
            boolean r9 = r11.isTailCall()
            if (r9 != 0) goto L_0x01bf
            boolean r9 = r13 instanceof gnu.expr.ConsumerTarget
            if (r9 == 0) goto L_0x0249
        L_0x01bf:
            gnu.expr.LambdaExp r9 = r12.curLambda
            boolean r9 = r9.getInlineOnly()
            if (r9 != 0) goto L_0x0249
            gnu.bytecode.ClassType r2 = gnu.expr.Compilation.typeCallContext
            gnu.expr.StackTarget r3 = new gnu.expr.StackTarget
            gnu.bytecode.ClassType r4 = gnu.expr.Compilation.typeProcedure
            r3.<init>(r4)
            r1.compile((gnu.expr.Compilation) r12, (gnu.expr.Target) r3)
            if (r0 > r10) goto L_0x0203
            r1 = 0
        L_0x01d6:
            if (r1 >= r0) goto L_0x01e4
            gnu.expr.Expression[] r3 = r11.args
            r3 = r3[r1]
            gnu.expr.Target r4 = gnu.expr.Target.pushObject
            r3.compileWithPosition(r12, r4)
            int r1 = r1 + 1
            goto L_0x01d6
        L_0x01e4:
            r12.loadCallContext()
            gnu.bytecode.ClassType r1 = gnu.expr.Compilation.typeProcedure
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "check"
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            int r0 = r0 + r8
            gnu.bytecode.Method r0 = r1.getDeclaredMethod((java.lang.String) r3, (int) r0)
            r14.emitInvoke(r0)
            goto L_0x0216
        L_0x0203:
            gnu.expr.Expression[] r0 = r11.args
            compileToArray(r0, r12)
            r12.loadCallContext()
            gnu.bytecode.ClassType r0 = gnu.expr.Compilation.typeProcedure
            java.lang.String r1 = "checkN"
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r1, (int) r5)
            r14.emitInvoke(r0)
        L_0x0216:
            boolean r11 = r11.isTailCall()
            if (r11 == 0) goto L_0x0220
            r14.emitReturn()
            goto L_0x0248
        L_0x0220:
            gnu.expr.ConsumerTarget r13 = (gnu.expr.ConsumerTarget) r13
            boolean r11 = r13.isContextTarget()
            if (r11 == 0) goto L_0x0235
            r12.loadCallContext()
            java.lang.String r11 = "runUntilDone"
            gnu.bytecode.Method r11 = r2.getDeclaredMethod((java.lang.String) r11, (int) r7)
            r14.emitInvoke(r11)
            goto L_0x0248
        L_0x0235:
            r12.loadCallContext()
            gnu.bytecode.Variable r11 = r13.getConsumerVariable()
            r14.emitLoad(r11)
            java.lang.String r11 = "runUntilValue"
            gnu.bytecode.Method r11 = r2.getDeclaredMethod((java.lang.String) r11, (int) r8)
            r14.emitInvoke(r11)
        L_0x0248:
            return
        L_0x0249:
            if (r4 != 0) goto L_0x0255
            gnu.expr.StackTarget r5 = new gnu.expr.StackTarget
            gnu.bytecode.ClassType r9 = gnu.expr.Compilation.typeProcedure
            r5.<init>(r9)
            r1.compile((gnu.expr.Compilation) r12, (gnu.expr.Target) r5)
        L_0x0255:
            if (r4 == 0) goto L_0x025e
            int r1 = r2.min_args
            int r5 = r2.max_args
            if (r1 == r5) goto L_0x0261
            goto L_0x0262
        L_0x025e:
            if (r0 <= r10) goto L_0x0261
            goto L_0x0262
        L_0x0261:
            r8 = 0
        L_0x0262:
            if (r8 == 0) goto L_0x026e
            gnu.expr.Expression[] r11 = r11.args
            compileToArray(r11, r12)
            gnu.bytecode.Method r11 = gnu.expr.Compilation.applyNmethod
        L_0x026b:
            r0 = r3
            r3 = r11
            goto L_0x0294
        L_0x026e:
            if (r4 == 0) goto L_0x0279
            gnu.expr.Expression[] r11 = r11.args
            int r0 = r11.length
            int[] r0 = new int[r0]
            pushArgs(r2, r11, r0, r12)
            goto L_0x0294
        L_0x0279:
            r1 = 0
        L_0x027a:
            if (r1 >= r0) goto L_0x028f
            gnu.expr.Expression[] r5 = r11.args
            r5 = r5[r1]
            gnu.expr.Target r9 = gnu.expr.Target.pushObject
            r5.compileWithPosition(r12, r9)
            boolean r5 = r14.reachableHere()
            if (r5 != 0) goto L_0x028c
            goto L_0x028f
        L_0x028c:
            int r1 = r1 + 1
            goto L_0x027a
        L_0x028f:
            gnu.bytecode.Method[] r11 = gnu.expr.Compilation.applymethods
            r11 = r11[r0]
            goto L_0x026b
        L_0x0294:
            boolean r11 = r14.reachableHere()
            if (r11 != 0) goto L_0x02a0
            java.lang.String r11 = "unreachable code"
            r12.error(r6, r11)
            return
        L_0x02a0:
            if (r4 == 0) goto L_0x02ad
            popParams(r14, r2, r0, r8)
            gnu.bytecode.Scope r11 = r2.getVarScope()
            r14.emitTailCall(r7, r11)
            return
        L_0x02ad:
            r14.emitInvokeVirtual(r3)
            gnu.bytecode.ClassType r11 = gnu.bytecode.Type.pointer_type
            r13.compileFromStack(r12, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ApplyExp.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target, boolean):void");
    }

    public Expression deepCopy(IdentityHashTable identityHashTable) {
        Expression deepCopy = deepCopy(this.func, identityHashTable);
        Expression[] deepCopy2 = deepCopy(this.args, identityHashTable);
        if (deepCopy == null && this.func != null) {
            return null;
        }
        if (deepCopy2 == null && this.args != null) {
            return null;
        }
        ApplyExp applyExp = new ApplyExp(deepCopy, deepCopy2);
        applyExp.flags = getFlags();
        return applyExp;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitApplyExp(this, d);
    }

    public void visitArgs(InlineCalls inlineCalls) {
        Expression[] expressionArr = this.args;
        this.args = inlineCalls.visitExps(expressionArr, expressionArr.length, null);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        this.func = expVisitor.visitAndUpdate(this.func, d);
        if (expVisitor.exitValue == null) {
            Expression[] expressionArr = this.args;
            this.args = expVisitor.visitExps(expressionArr, expressionArr.length, d);
        }
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Apply", ")", 2);
        if (isTailCall()) {
            outPort.print(" [tailcall]");
        }
        Type type2 = this.type;
        if (!(type2 == null || type2 == Type.pointer_type)) {
            outPort.print(" => ");
            outPort.print((Object) this.type);
        }
        outPort.writeSpaceFill();
        printLineColumn(outPort);
        this.func.print(outPort);
        for (Expression print : this.args) {
            outPort.writeSpaceLinear();
            print.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }

    private static void pushArgs(LambdaExp lambdaExp, Expression[] expressionArr, int[] iArr, Compilation compilation) {
        Declaration firstDecl = lambdaExp.firstDecl();
        int length = expressionArr.length;
        for (int i = 0; i < length; i++) {
            Expression expression = expressionArr[i];
            if (firstDecl.ignorable()) {
                expression.compile(compilation, Target.Ignore);
            } else {
                if (iArr != null) {
                    int canUseInc = SetExp.canUseInc(expression, firstDecl);
                    iArr[i] = canUseInc;
                    if (canUseInc != 65536) {
                    }
                }
                expression.compileWithPosition(compilation, StackTarget.getInstance(firstDecl.getType()));
            }
            firstDecl = firstDecl.nextDecl();
        }
    }

    private static void popParams(CodeAttr codeAttr, LambdaExp lambdaExp, int[] iArr, boolean z) {
        Variable firstVar = lambdaExp.getVarScope().firstVar();
        Declaration firstDecl = lambdaExp.firstDecl();
        if (firstVar != null && firstVar.getName() == "this") {
            firstVar = firstVar.nextVar();
        }
        if (firstVar != null && firstVar.getName() == "$ctx") {
            firstVar = firstVar.nextVar();
        }
        Variable variable = firstVar;
        if (variable != null && variable.getName() == "argsArray") {
            if (z) {
                popParams(codeAttr, 0, 1, (int[]) null, firstDecl, variable);
                return;
            }
            variable = variable.nextVar();
        }
        popParams(codeAttr, 0, lambdaExp.min_args, iArr, firstDecl, variable);
    }

    private static void popParams(CodeAttr codeAttr, int i, int i2, int[] iArr, Declaration declaration, Variable variable) {
        int i3;
        if (i2 > 0) {
            popParams(codeAttr, i + 1, i2 - 1, iArr, declaration.nextDecl(), declaration.getVariable() == null ? variable : variable.nextVar());
            if (declaration.ignorable()) {
                return;
            }
            if (iArr == null || (i3 = iArr[i]) == 65536) {
                codeAttr.emitStore(variable);
            } else {
                codeAttr.emitInc(variable, (short) i3);
            }
        }
    }

    public final Type getTypeRaw() {
        return this.type;
    }

    public final void setType(Type type2) {
        this.type = type2;
    }

    public boolean side_effects() {
        Object valueIfConstant = derefFunc(this.func).valueIfConstant();
        if (!(valueIfConstant instanceof Procedure) || !((Procedure) valueIfConstant).isSideEffectFree()) {
            return true;
        }
        for (Expression side_effects : this.args) {
            if (side_effects.side_effects()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = gnu.expr.Declaration.followAliases(((gnu.expr.ReferenceExp) r3).binding);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static gnu.expr.Expression derefFunc(gnu.expr.Expression r3) {
        /*
            boolean r0 = r3 instanceof gnu.expr.ReferenceExp
            if (r0 == 0) goto L_0x001c
            r0 = r3
            gnu.expr.ReferenceExp r0 = (gnu.expr.ReferenceExp) r0
            gnu.expr.Declaration r0 = r0.binding
            gnu.expr.Declaration r0 = gnu.expr.Declaration.followAliases(r0)
            if (r0 == 0) goto L_0x001c
            r1 = 65536(0x10000, double:3.2379E-319)
            boolean r1 = r0.getFlag(r1)
            if (r1 != 0) goto L_0x001c
            gnu.expr.Expression r3 = r0.getValue()
        L_0x001c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ApplyExp.derefFunc(gnu.expr.Expression):gnu.expr.Expression");
    }

    public final Type getType() {
        Type type2 = this.type;
        if (type2 != null) {
            return type2;
        }
        Expression derefFunc = derefFunc(this.func);
        this.type = Type.objectType;
        if (derefFunc instanceof QuoteExp) {
            Object value = ((QuoteExp) derefFunc).getValue();
            if (value instanceof Procedure) {
                this.type = ((Procedure) value).getReturnType(this.args);
            }
        } else if (derefFunc instanceof LambdaExp) {
            this.type = ((LambdaExp) derefFunc).getReturnType();
        }
        return this.type;
    }

    public static Inlineable asInlineable(Procedure procedure) {
        if (procedure instanceof Inlineable) {
            return (Inlineable) procedure;
        }
        return (Inlineable) Procedure.compilerKey.get(procedure);
    }

    static boolean inlineCompile(Procedure procedure, ApplyExp applyExp, Compilation compilation, Target target) throws Throwable {
        Inlineable asInlineable = asInlineable(procedure);
        if (asInlineable == null) {
            return false;
        }
        asInlineable.compile(applyExp, compilation, target);
        return true;
    }

    public final Expression inlineIfConstant(Procedure procedure, InlineCalls inlineCalls) {
        return inlineIfConstant(procedure, inlineCalls.getMessages());
    }

    public final Expression inlineIfConstant(Procedure procedure, SourceMessages sourceMessages) {
        Declaration binding;
        int length = this.args.length;
        Object[] objArr = new Object[length];
        while (true) {
            length--;
            if (length >= 0) {
                Expression expression = this.args[length];
                if (((expression instanceof ReferenceExp) && (binding = ((ReferenceExp) expression).getBinding()) != null && (expression = binding.getValue()) == QuoteExp.undefined_exp) || !(expression instanceof QuoteExp)) {
                    return this;
                }
                objArr[length] = ((QuoteExp) expression).getValue();
            } else {
                try {
                    return new QuoteExp(procedure.applyN(objArr), this.type);
                } catch (Throwable th) {
                    if (sourceMessages != null) {
                        sourceMessages.error('w', "call to " + procedure + " throws " + th);
                    }
                    return this;
                }
            }
        }
    }

    public String toString() {
        if (this == LambdaExp.unknownContinuation) {
            return "ApplyExp[unknownContinuation]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ApplyExp/");
        Expression[] expressionArr = this.args;
        sb.append(expressionArr == null ? 0 : expressionArr.length);
        sb.append('[');
        sb.append(this.func);
        sb.append(']');
        return sb.toString();
    }
}
