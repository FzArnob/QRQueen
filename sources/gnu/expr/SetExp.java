package gnu.expr;

import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.math.IntNum;

public class SetExp extends AccessExp {
    public static final int BAD_SHORT = 65536;
    public static final int DEFINING_FLAG = 2;
    public static final int GLOBAL_FLAG = 4;
    public static final int HAS_VALUE = 64;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE = 16;
    public static final int SET_IF_UNBOUND = 32;
    Expression new_value;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public SetExp(Object obj, Expression expression) {
        this.symbol = obj;
        this.new_value = expression;
    }

    public SetExp(Declaration declaration, Expression expression) {
        this.binding = declaration;
        this.symbol = declaration.getSymbol();
        this.new_value = expression;
    }

    public static SetExp makeDefinition(Object obj, Expression expression) {
        SetExp setExp = new SetExp(obj, expression);
        setExp.setDefining(true);
        return setExp;
    }

    public static SetExp makeDefinition(Declaration declaration, Expression expression) {
        SetExp setExp = new SetExp(declaration, expression);
        setExp.setDefining(true);
        return setExp;
    }

    public final Expression getNewValue() {
        return this.new_value;
    }

    public final boolean isDefining() {
        return (this.flags & 2) != 0;
    }

    public final void setDefining(boolean z) {
        this.flags = z ? this.flags | 2 : this.flags & -3;
    }

    public final boolean getHasValue() {
        return (this.flags & 64) != 0;
    }

    public final void setHasValue(boolean z) {
        this.flags = z ? this.flags | 64 : this.flags & -65;
    }

    public final boolean isFuncDef() {
        return (this.flags & 16) != 0;
    }

    public final void setFuncDef(boolean z) {
        this.flags = z ? this.flags | 16 : this.flags & -17;
    }

    public final boolean isSetIfUnbound() {
        return (this.flags & 32) != 0;
    }

    public final void setSetIfUnbound(boolean z) {
        this.flags = z ? this.flags | 32 : this.flags & -33;
    }

    public void apply(CallContext callContext) throws Throwable {
        Environment current = Environment.getCurrent();
        Symbol symbol = this.symbol instanceof Symbol ? (Symbol) this.symbol : current.getSymbol(this.symbol.toString());
        Object obj = null;
        Language defaultLanguage = Language.getDefaultLanguage();
        if (isFuncDef() && defaultLanguage.hasSeparateFunctionNamespace()) {
            obj = EnvironmentKey.FUNCTION;
        }
        if (isSetIfUnbound()) {
            Location location = current.getLocation(symbol, obj);
            if (!location.isBound()) {
                location.set(this.new_value.eval(current));
            }
            if (getHasValue()) {
                callContext.writeValue(location);
                return;
            }
            return;
        }
        Object eval = this.new_value.eval(current);
        if (this.binding != null && !(this.binding.context instanceof ModuleExp)) {
            Object[] objArr = callContext.evalFrames[ScopeExp.nesting(this.binding.context)];
            if (this.binding.isIndirectBinding()) {
                if (isDefining()) {
                    objArr[this.binding.evalIndex] = Location.make(symbol);
                }
                ((Location) objArr[this.binding.evalIndex]).set(this.new_value);
            } else {
                objArr[this.binding.evalIndex] = eval;
            }
        } else if (isDefining()) {
            current.define(symbol, obj, eval);
        } else {
            current.put(symbol, obj, eval);
        }
        if (getHasValue()) {
            callContext.writeValue(eval);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: gnu.expr.SetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: gnu.expr.SetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: gnu.expr.SetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: gnu.expr.SetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: gnu.expr.ReferenceExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: gnu.expr.SetExp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.Compilation r11, gnu.expr.Target r12) {
        /*
            r10 = this;
            gnu.expr.Expression r0 = r10.new_value
            boolean r1 = r0 instanceof gnu.expr.LambdaExp
            if (r1 == 0) goto L_0x0013
            boolean r1 = r12 instanceof gnu.expr.IgnoreTarget
            if (r1 == 0) goto L_0x0013
            gnu.expr.LambdaExp r0 = (gnu.expr.LambdaExp) r0
            boolean r0 = r0.getInlineOnly()
            if (r0 == 0) goto L_0x0013
            return
        L_0x0013:
            gnu.bytecode.CodeAttr r0 = r11.getCode()
            boolean r1 = r10.getHasValue()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0025
            boolean r1 = r12 instanceof gnu.expr.IgnoreTarget
            if (r1 != 0) goto L_0x0025
            r1 = 1
            goto L_0x0026
        L_0x0025:
            r1 = 0
        L_0x0026:
            gnu.expr.Declaration r4 = r10.binding
            gnu.expr.Expression r5 = r4.getValue()
            boolean r6 = r5 instanceof gnu.expr.LambdaExp
            if (r6 == 0) goto L_0x0050
            gnu.expr.ScopeExp r6 = r4.context
            boolean r6 = r6 instanceof gnu.expr.ModuleExp
            if (r6 == 0) goto L_0x0050
            boolean r6 = r4.ignorable()
            if (r6 != 0) goto L_0x0050
            r6 = r5
            gnu.expr.LambdaExp r6 = (gnu.expr.LambdaExp) r6
            java.lang.String r6 = r6.getName()
            if (r6 == 0) goto L_0x0050
            gnu.expr.Expression r6 = r10.new_value
            if (r5 != r6) goto L_0x0050
            gnu.expr.LambdaExp r6 = (gnu.expr.LambdaExp) r6
            r6.compileSetField(r11)
            goto L_0x020a
        L_0x0050:
            boolean r5 = r4.shouldEarlyInit()
            if (r5 != 0) goto L_0x005c
            boolean r5 = r4.isAlias()
            if (r5 == 0) goto L_0x0083
        L_0x005c:
            gnu.expr.ScopeExp r5 = r4.context
            boolean r5 = r5 instanceof gnu.expr.ModuleExp
            if (r5 == 0) goto L_0x0083
            boolean r5 = r10.isDefining()
            if (r5 == 0) goto L_0x0083
            boolean r5 = r4.ignorable()
            if (r5 != 0) goto L_0x0083
            boolean r0 = r4.shouldEarlyInit()
            if (r0 == 0) goto L_0x0079
            gnu.expr.Expression r0 = r10.new_value
            gnu.expr.BindingInitializer.create(r4, r0, r11)
        L_0x0079:
            if (r1 == 0) goto L_0x020a
            gnu.expr.Target r0 = gnu.expr.Target.pushObject
            r4.load(r10, r2, r11, r0)
        L_0x0080:
            r2 = 1
            goto L_0x020a
        L_0x0083:
            gnu.expr.Declaration r5 = r10.contextDecl()
            boolean r6 = r10.isDefining()
            if (r6 != 0) goto L_0x00b6
            r6 = r10
        L_0x008e:
            if (r4 == 0) goto L_0x00b7
            boolean r7 = r4.isAlias()
            if (r7 == 0) goto L_0x00b7
            gnu.expr.Expression r7 = r4.getValue()
            boolean r8 = r7 instanceof gnu.expr.ReferenceExp
            if (r8 != 0) goto L_0x009f
            goto L_0x00b7
        L_0x009f:
            gnu.expr.ReferenceExp r7 = (gnu.expr.ReferenceExp) r7
            gnu.expr.Declaration r8 = r7.binding
            if (r8 != 0) goto L_0x00a6
            goto L_0x00b7
        L_0x00a6:
            if (r5 == 0) goto L_0x00af
            boolean r9 = r8.needsContext()
            if (r9 == 0) goto L_0x00af
            goto L_0x00b7
        L_0x00af:
            gnu.expr.Declaration r5 = r7.contextDecl()
            r6 = r7
            r4 = r8
            goto L_0x008e
        L_0x00b6:
            r6 = r10
        L_0x00b7:
            boolean r7 = r4.ignorable()
            if (r7 == 0) goto L_0x00c6
            gnu.expr.Expression r0 = r10.new_value
            gnu.expr.Target r3 = gnu.expr.Target.Ignore
            r0.compile((gnu.expr.Compilation) r11, (gnu.expr.Target) r3)
            goto L_0x020a
        L_0x00c6:
            boolean r7 = r4.isAlias()
            r8 = 2
            if (r7 == 0) goto L_0x00f3
            boolean r7 = r10.isDefining()
            if (r7 == 0) goto L_0x00f3
            gnu.expr.Target r5 = gnu.expr.Target.pushObject
            r4.load(r10, r8, r11, r5)
            java.lang.String r4 = "gnu.mapping.IndirectableLocation"
            gnu.bytecode.ClassType r4 = gnu.bytecode.ClassType.make(r4)
            r0.emitCheckcast(r4)
            gnu.expr.Expression r5 = r10.new_value
            gnu.expr.Target r6 = gnu.expr.Target.pushObject
            r5.compile((gnu.expr.Compilation) r11, (gnu.expr.Target) r6)
            java.lang.String r5 = "setAlias"
            gnu.bytecode.Method r3 = r4.getDeclaredMethod((java.lang.String) r5, (int) r3)
            r0.emitInvokeVirtual(r3)
            goto L_0x020a
        L_0x00f3:
            boolean r7 = r4.isIndirectBinding()
            java.lang.String r9 = "set"
            if (r7 == 0) goto L_0x0159
            gnu.expr.Target r5 = gnu.expr.Target.pushObject
            r4.load(r6, r8, r11, r5)
            boolean r4 = r10.isSetIfUnbound()
            if (r4 == 0) goto L_0x012f
            if (r1 == 0) goto L_0x010d
            r0.emitDup()
            r4 = 1
            goto L_0x010e
        L_0x010d:
            r4 = 0
        L_0x010e:
            r0.pushScope()
            r0.emitDup()
            gnu.bytecode.ClassType r5 = gnu.expr.Compilation.typeLocation
            gnu.bytecode.Variable r5 = r0.addLocal(r5)
            r0.emitStore(r5)
            gnu.bytecode.ClassType r6 = gnu.expr.Compilation.typeLocation
            java.lang.String r7 = "isBound"
            gnu.bytecode.Method r2 = r6.getDeclaredMethod((java.lang.String) r7, (int) r2)
            r0.emitInvokeVirtual(r2)
            r0.emitIfIntEqZero()
            r0.emitLoad(r5)
            r2 = r4
        L_0x012f:
            gnu.expr.Expression r4 = r10.new_value
            gnu.expr.Target r5 = gnu.expr.Target.pushObject
            r4.compile((gnu.expr.Compilation) r11, (gnu.expr.Target) r5)
            if (r1 == 0) goto L_0x0142
            boolean r4 = r10.isSetIfUnbound()
            if (r4 != 0) goto L_0x0142
            r0.emitDupX()
            r2 = 1
        L_0x0142:
            gnu.bytecode.ClassType r4 = gnu.expr.Compilation.typeLocation
            gnu.bytecode.Method r3 = r4.getDeclaredMethod((java.lang.String) r9, (int) r3)
            r0.emitInvokeVirtual(r3)
            boolean r3 = r10.isSetIfUnbound()
            if (r3 == 0) goto L_0x020a
            r0.emitFi()
            r0.popScope()
            goto L_0x020a
        L_0x0159:
            boolean r6 = r4.isSimple()
            if (r6 == 0) goto L_0x0196
            gnu.bytecode.Type r5 = r4.getType()
            gnu.bytecode.Variable r6 = r4.getVariable()
            if (r6 != 0) goto L_0x016d
            gnu.bytecode.Variable r6 = r4.allocateVariable(r0)
        L_0x016d:
            gnu.expr.Expression r7 = r10.new_value
            int r7 = canUseInc(r7, r4)
            r8 = 65536(0x10000, float:9.18355E-41)
            if (r7 == r8) goto L_0x0186
            gnu.bytecode.CodeAttr r4 = r11.getCode()
            short r5 = (short) r7
            r4.emitInc(r6, r5)
            if (r1 == 0) goto L_0x020a
            r0.emitLoad(r6)
            goto L_0x0080
        L_0x0186:
            gnu.expr.Expression r7 = r10.new_value
            r7.compile((gnu.expr.Compilation) r11, (gnu.expr.Declaration) r4)
            if (r1 == 0) goto L_0x0191
            r0.emitDup((gnu.bytecode.Type) r5)
            r2 = 1
        L_0x0191:
            r0.emitStore(r6)
            goto L_0x020a
        L_0x0196:
            gnu.expr.ScopeExp r6 = r4.context
            boolean r6 = r6 instanceof gnu.expr.ClassExp
            if (r6 == 0) goto L_0x01d6
            gnu.bytecode.Field r6 = r4.field
            if (r6 != 0) goto L_0x01d6
            r6 = 16
            boolean r6 = r10.getFlag(r6)
            if (r6 != 0) goto L_0x01d6
            gnu.expr.ScopeExp r6 = r4.context
            gnu.expr.ClassExp r6 = (gnu.expr.ClassExp) r6
            boolean r6 = r6.isMakingClassPair()
            if (r6 == 0) goto L_0x01d6
            java.lang.String r5 = r4.getName()
            java.lang.String r5 = gnu.expr.ClassExp.slotToMethodName(r9, r5)
            gnu.expr.ScopeExp r6 = r4.context
            gnu.expr.ClassExp r6 = (gnu.expr.ClassExp) r6
            gnu.bytecode.ClassType r7 = r6.type
            gnu.bytecode.Method r5 = r7.getDeclaredMethod((java.lang.String) r5, (int) r3)
            r6.loadHeapFrame(r11)
            gnu.expr.Expression r6 = r10.new_value
            r6.compile((gnu.expr.Compilation) r11, (gnu.expr.Declaration) r4)
            if (r1 == 0) goto L_0x01d2
            r0.emitDupX()
            r2 = 1
        L_0x01d2:
            r0.emitInvoke(r5)
            goto L_0x020a
        L_0x01d6:
            gnu.bytecode.Field r6 = r4.field
            boolean r7 = r6.getStaticFlag()
            if (r7 != 0) goto L_0x01e1
            r4.loadOwningObject(r5, r11)
        L_0x01e1:
            gnu.bytecode.Type r5 = r6.getType()
            gnu.expr.Expression r7 = r10.new_value
            r7.compile((gnu.expr.Compilation) r11, (gnu.expr.Declaration) r4)
            gnu.bytecode.ClassType r4 = r6.getDeclaringClass()
            r11.usedClass(r4)
            boolean r4 = r6.getStaticFlag()
            if (r4 == 0) goto L_0x0201
            if (r1 == 0) goto L_0x01fd
            r0.emitDup((gnu.bytecode.Type) r5)
            r2 = 1
        L_0x01fd:
            r0.emitPutStatic(r6)
            goto L_0x020a
        L_0x0201:
            if (r1 == 0) goto L_0x0207
            r0.emitDupX()
            r2 = 1
        L_0x0207:
            r0.emitPutField(r6)
        L_0x020a:
            if (r1 == 0) goto L_0x0217
            if (r2 == 0) goto L_0x020f
            goto L_0x0217
        L_0x020f:
            java.lang.Error r11 = new java.lang.Error
            java.lang.String r12 = "SetExp.compile: not implemented - return value"
            r11.<init>(r12)
            throw r11
        L_0x0217:
            if (r1 == 0) goto L_0x0221
            gnu.bytecode.Type r0 = r10.getType()
            r12.compileFromStack(r11, r0)
            goto L_0x0226
        L_0x0221:
            gnu.mapping.Values r0 = gnu.mapping.Values.empty
            r11.compileConstant(r0, r12)
        L_0x0226:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.SetExp.compile(gnu.expr.Compilation, gnu.expr.Target):void");
    }

    public static int canUseInc(Expression expression, Declaration declaration) {
        int i;
        Variable variable = declaration.getVariable();
        if (!declaration.isSimple() || variable.getType().getImplementationType().promote() != Type.intType || !(expression instanceof ApplyExp)) {
            return 65536;
        }
        ApplyExp applyExp = (ApplyExp) expression;
        if (applyExp.getArgCount() != 2) {
            return 65536;
        }
        Object valueIfConstant = applyExp.getFunction().valueIfConstant();
        if (valueIfConstant == AddOp.$Pl) {
            i = 1;
        } else if (valueIfConstant != AddOp.$Mn) {
            return 65536;
        } else {
            i = -1;
        }
        Expression arg = applyExp.getArg(0);
        Expression arg2 = applyExp.getArg(1);
        if ((arg instanceof QuoteExp) && i > 0) {
            Expression expression2 = arg;
            arg = arg2;
            arg2 = expression2;
        }
        if (!(arg instanceof ReferenceExp)) {
            return 65536;
        }
        ReferenceExp referenceExp = (ReferenceExp) arg;
        if (referenceExp.getBinding() != declaration || referenceExp.getDontDereference()) {
            return 65536;
        }
        Object valueIfConstant2 = arg2.valueIfConstant();
        if (valueIfConstant2 instanceof Integer) {
            int intValue = ((Integer) valueIfConstant2).intValue();
            if (i < 0) {
                intValue = -intValue;
            }
            if (((short) intValue) == intValue) {
                return intValue;
            }
            return 65536;
        } else if (!(valueIfConstant2 instanceof IntNum)) {
            return 65536;
        } else {
            IntNum intNum = (IntNum) valueIfConstant2;
            int i2 = 32767;
            int i3 = -32767;
            if (i > 0) {
                i3 = -32768;
            } else {
                i2 = 32768;
            }
            if (IntNum.compare(intNum, (long) i3) < 0 || IntNum.compare(intNum, (long) i2) > 0) {
                return 65536;
            }
            return i * intNum.intValue();
        }
    }

    public final Type getType() {
        if (!getHasValue()) {
            return Type.voidType;
        }
        return this.binding == null ? Type.pointer_type : this.binding.getType();
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitSetExp(this, d);
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
        this.new_value = expVisitor.visitAndUpdate(this.new_value, d);
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock(isDefining() ? "(Define" : "(Set", ")", 2);
        outPort.writeSpaceFill();
        printLineColumn(outPort);
        if (this.binding == null || this.symbol.toString() != this.binding.getName()) {
            outPort.print('/');
            outPort.print(this.symbol);
        }
        if (this.binding != null) {
            outPort.print('/');
            outPort.print((Object) this.binding);
        }
        outPort.writeSpaceLinear();
        this.new_value.print(outPort);
        outPort.endLogicalBlock(")");
    }

    public String toString() {
        return "SetExp[" + this.symbol + ":=" + this.new_value + ']';
    }
}
