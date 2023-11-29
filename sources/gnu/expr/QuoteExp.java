package gnu.expr;

import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.AbstractFormat;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;

public class QuoteExp extends Expression {
    public static final int EXPLICITLY_TYPED = 2;
    public static final int SHARED_CONSTANT = 4;
    public static QuoteExp abstractExp = makeShared(Special.abstractSpecial);
    public static final QuoteExp classObjectExp = makeShared(Type.objectType);
    public static QuoteExp falseExp = makeShared(Boolean.FALSE);
    public static QuoteExp nullExp = makeShared((Object) null, Type.nullType);
    public static QuoteExp trueExp = makeShared(Boolean.TRUE);
    public static QuoteExp undefined_exp = makeShared(Special.undefined);
    public static QuoteExp voidExp = makeShared(Values.empty, Type.voidType);
    protected Type type;
    Object value;

    public Expression deepCopy(IdentityHashTable identityHashTable) {
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public boolean side_effects() {
        return false;
    }

    public final Object getValue() {
        return this.value;
    }

    public final Object valueIfConstant() {
        return this.value;
    }

    public final Type getRawType() {
        return this.type;
    }

    public final Type getType() {
        Type type2 = this.type;
        if (type2 != null) {
            return type2;
        }
        if (this.value == Values.empty) {
            return Type.voidType;
        }
        Object obj = this.value;
        if (obj == null) {
            return Type.nullType;
        }
        if (this == undefined_exp) {
            return Type.pointer_type;
        }
        return Type.make(obj.getClass());
    }

    public void setType(Type type2) {
        this.type = type2;
        setFlag(2);
    }

    public boolean isExplicitlyTyped() {
        return getFlag(2);
    }

    public boolean isSharedConstant() {
        return getFlag(4);
    }

    public static QuoteExp getInstance(Object obj) {
        return getInstance(obj, (SourceLocator) null);
    }

    public static QuoteExp getInstance(Object obj, SourceLocator sourceLocator) {
        if (obj == null) {
            return nullExp;
        }
        if (obj == Type.pointer_type) {
            return classObjectExp;
        }
        if (obj == Special.undefined) {
            return undefined_exp;
        }
        if (obj == Values.empty) {
            return voidExp;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? trueExp : falseExp;
        }
        QuoteExp quoteExp = new QuoteExp(obj);
        if (sourceLocator != null) {
            quoteExp.setLocation(sourceLocator);
        }
        return quoteExp;
    }

    static QuoteExp makeShared(Object obj) {
        QuoteExp quoteExp = new QuoteExp(obj);
        quoteExp.setFlag(4);
        return quoteExp;
    }

    static QuoteExp makeShared(Object obj, Type type2) {
        QuoteExp quoteExp = new QuoteExp(obj, type2);
        quoteExp.setFlag(4);
        return quoteExp;
    }

    public QuoteExp(Object obj) {
        this.value = obj;
    }

    public QuoteExp(Object obj, Type type2) {
        this.value = obj;
        setType(type2);
    }

    public void apply(CallContext callContext) {
        callContext.writeValue(this.value);
    }

    public void compile(Compilation compilation, Target target) {
        Type type2 = this.type;
        if (!(type2 == null || type2 == Type.pointer_type || (target instanceof IgnoreTarget))) {
            Type type3 = this.type;
            if (!(type3 instanceof ObjectType) || !type3.isInstance(this.value)) {
                compilation.compileConstant(this.value, StackTarget.getInstance(this.type));
                target.compileFromStack(compilation, this.type);
                return;
            }
        }
        compilation.compileConstant(this.value, target);
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitQuoteExp(this, d);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type2, Declaration declaration) {
        ApplyExp applyExp2;
        Expression inlineIfConstant;
        String str;
        if (this == undefined_exp) {
            return applyExp;
        }
        Object value2 = getValue();
        if (!(value2 instanceof Procedure)) {
            if (declaration == null || value2 == null) {
                str = "called value is not a procedure";
            } else {
                str = "calling " + declaration.getName() + " which is a " + value2.getClass().getName();
            }
            return inlineCalls.noteError(str);
        }
        Procedure procedure = (Procedure) value2;
        int argCount = applyExp.getArgCount();
        String checkArgCount = WrongArguments.checkArgCount(procedure, argCount);
        if (checkArgCount != null) {
            return inlineCalls.noteError(checkArgCount);
        }
        Expression maybeInline = inlineCalls.maybeInline(applyExp, type2, procedure);
        if (maybeInline != null) {
            return maybeInline;
        }
        Expression[] expressionArr = applyExp.args;
        MethodProc methodProc = procedure instanceof MethodProc ? (MethodProc) procedure : null;
        for (int i = 0; i < argCount; i++) {
            Type parameterType = methodProc != null ? methodProc.getParameterType(i) : null;
            if (i == argCount - 1 && parameterType != null && methodProc.maxArgs() < 0 && i == methodProc.minArgs()) {
                parameterType = null;
            }
            expressionArr[i] = inlineCalls.visit(expressionArr[i], parameterType);
        }
        if (applyExp.getFlag(4) && (inlineIfConstant = applyExp.inlineIfConstant(procedure, inlineCalls)) != applyExp) {
            return inlineCalls.visit(inlineIfConstant, type2);
        }
        Compilation compilation = inlineCalls.getCompilation();
        if (!compilation.inlineOk(procedure)) {
            return applyExp;
        }
        if (ApplyExp.asInlineable(procedure) == null) {
            PrimProcedure methodFor = PrimProcedure.getMethodFor(procedure, declaration, applyExp.args, compilation.getLanguage());
            if (methodFor == null) {
                return applyExp;
            }
            if (methodFor.getStaticFlag() || declaration == null) {
                applyExp2 = new ApplyExp((Procedure) methodFor, applyExp.args);
            } else if (declaration.base == null) {
                return applyExp;
            } else {
                Expression[] expressionArr2 = new Expression[(argCount + 1)];
                System.arraycopy(applyExp.getArgs(), 0, expressionArr2, 1, argCount);
                expressionArr2[0] = new ReferenceExp(declaration.base);
                applyExp2 = new ApplyExp((Procedure) methodFor, expressionArr2);
            }
            return applyExp2.setLine((Expression) applyExp);
        } else if (applyExp.getFunction() == this) {
            return applyExp;
        } else {
            return new ApplyExp((Expression) this, applyExp.getArgs()).setLine((Expression) applyExp);
        }
    }

    public String toString() {
        return "QuoteExp[" + this.value + "]";
    }

    /* JADX INFO: finally extract failed */
    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Quote", ")", 2);
        outPort.writeSpaceLinear();
        Object obj = this.value;
        if (obj instanceof Expression) {
            obj = obj.toString();
        }
        AbstractFormat abstractFormat = outPort.objectFormat;
        try {
            outPort.objectFormat = Language.getDefaultLanguage().getFormat(true);
            outPort.print(obj);
            if (this.type != null) {
                outPort.print(" ::");
                outPort.print(this.type.getName());
            }
            outPort.objectFormat = abstractFormat;
            outPort.endLogicalBlock(")");
        } catch (Throwable th) {
            outPort.objectFormat = abstractFormat;
            throw th;
        }
    }
}
