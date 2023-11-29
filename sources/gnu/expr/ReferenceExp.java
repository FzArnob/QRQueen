package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.text.SourceLocator;

public class ReferenceExp extends AccessExp {
    public static final int DONT_DEREFERENCE = 2;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE_NAME = 4;
    public static final int TYPE_NAME = 16;
    static int counter;
    int id;

    /* access modifiers changed from: protected */
    public boolean mustCompile() {
        return false;
    }

    public final boolean getDontDereference() {
        return (this.flags & 2) != 0;
    }

    public final void setDontDereference(boolean z) {
        setFlag(z, 2);
    }

    public final boolean isUnknown() {
        return Declaration.isUnknown(this.binding);
    }

    public final boolean isProcedureName() {
        return (this.flags & 4) != 0;
    }

    public final void setProcedureName(boolean z) {
        setFlag(z, 4);
    }

    public ReferenceExp(Object obj) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.symbol = obj;
    }

    public ReferenceExp(Object obj, Declaration declaration) {
        int i = counter + 1;
        counter = i;
        this.id = i;
        this.symbol = obj;
        this.binding = declaration;
    }

    public ReferenceExp(Declaration declaration) {
        this(declaration.getSymbol(), declaration);
    }

    public final Object valueIfConstant() {
        Expression value;
        if (this.binding == null || (value = this.binding.getValue()) == null) {
            return null;
        }
        return value.valueIfConstant();
    }

    public void apply(CallContext callContext) throws Throwable {
        Object obj;
        Object obj2;
        if (this.binding == null || !this.binding.isAlias() || getDontDereference() || !(this.binding.value instanceof ReferenceExp)) {
            Object obj3 = null;
            if (this.binding != null && this.binding.field != null && this.binding.field.getDeclaringClass().isExisting() && (!getDontDereference() || this.binding.isIndirectBinding())) {
                try {
                    if (!this.binding.field.getStaticFlag()) {
                        obj3 = contextDecl().getValue().eval(callContext);
                    }
                    obj = this.binding.field.getReflectField().get(obj3);
                } catch (Exception e) {
                    throw new UnboundLocationException((Object) "exception evaluating " + this.symbol + " from " + this.binding.field + " - " + e, (SourceLocator) this);
                }
            } else if (this.binding != null && (((this.binding.value instanceof QuoteExp) || (this.binding.value instanceof LambdaExp)) && this.binding.value != QuoteExp.undefined_exp && (!getDontDereference() || this.binding.isIndirectBinding()))) {
                obj = this.binding.value.eval(callContext);
            } else if (this.binding == null || ((this.binding.context instanceof ModuleExp) && !this.binding.isPrivate())) {
                Environment current = Environment.getCurrent();
                Symbol symbol = this.symbol instanceof Symbol ? (Symbol) this.symbol : current.getSymbol(this.symbol.toString());
                if (getFlag(8) && isProcedureName()) {
                    obj3 = EnvironmentKey.FUNCTION;
                }
                if (getDontDereference()) {
                    obj2 = current.getLocation(symbol, obj3);
                } else {
                    String str = Location.UNBOUND;
                    obj2 = current.get(symbol, obj3, str);
                    if (obj2 == str) {
                        throw new UnboundLocationException((Object) symbol, (SourceLocator) this);
                    }
                }
                callContext.writeValue(obj2);
                return;
            } else {
                obj = callContext.evalFrames[ScopeExp.nesting(this.binding.context)][this.binding.evalIndex];
            }
        } else {
            ReferenceExp referenceExp = (ReferenceExp) this.binding.value;
            if (referenceExp.getDontDereference() && referenceExp.binding != null) {
                Expression value = referenceExp.binding.getValue();
                if ((value instanceof QuoteExp) || (value instanceof ReferenceExp) || (value instanceof LambdaExp)) {
                    value.apply(callContext);
                    return;
                }
            }
            obj = this.binding.value.eval(callContext);
        }
        if (!getDontDereference() && this.binding.isIndirectBinding()) {
            obj = ((Location) obj).get();
        }
        callContext.writeValue(obj);
    }

    public void compile(Compilation compilation, Target target) {
        if (!(target instanceof ConsumerTarget) || !((ConsumerTarget) target).compileWrite(this, compilation)) {
            this.binding.load(this, this.flags, compilation, target);
        }
    }

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        ReferenceExp referenceExp = new ReferenceExp(identityHashTable.get(this.symbol, this.symbol), (Declaration) identityHashTable.get(this.binding, this.binding));
        referenceExp.flags = getFlags();
        return referenceExp;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitReferenceExp(this, d);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        Expression value;
        Declaration declaration2 = this.binding;
        if (declaration2 != null && !declaration2.getFlag(65536)) {
            Declaration followAliases = Declaration.followAliases(declaration2);
            if (!followAliases.isIndirectBinding() && (value = followAliases.getValue()) != null) {
                return value.validateApply(applyExp, inlineCalls, type, followAliases);
            }
        } else if (getSymbol() instanceof Symbol) {
            Object function = Environment.getCurrent().getFunction((Symbol) getSymbol(), (Object) null);
            if (function instanceof Procedure) {
                return new QuoteExp(function).validateApply(applyExp, inlineCalls, type, (Declaration) null);
            }
        }
        applyExp.visitArgs(inlineCalls);
        return applyExp;
    }

    public void print(OutPort outPort) {
        outPort.print("(Ref/");
        outPort.print(this.id);
        if (this.symbol != null && (this.binding == null || this.symbol.toString() != this.binding.getName())) {
            outPort.print('/');
            outPort.print(this.symbol);
        }
        if (this.binding != null) {
            outPort.print('/');
            outPort.print((Object) this.binding);
        }
        outPort.print(")");
    }

    public Type getType() {
        Expression value;
        Declaration declaration = this.binding;
        if (declaration == null || declaration.isFluid()) {
            return Type.pointer_type;
        }
        if (getDontDereference()) {
            return Compilation.typeLocation;
        }
        Declaration followAliases = Declaration.followAliases(declaration);
        Type type = followAliases.getType();
        if (!((type != null && type != Type.pointer_type) || (value = followAliases.getValue()) == null || value == QuoteExp.undefined_exp)) {
            Expression expression = followAliases.value;
            followAliases.value = null;
            Type type2 = value.getType();
            followAliases.value = expression;
            type = type2;
        }
        if (type == Type.toStringType) {
            return Type.javalangStringType;
        }
        return type;
    }

    public boolean isSingleValue() {
        if (this.binding == null || !this.binding.getFlag(262144)) {
            return super.isSingleValue();
        }
        return true;
    }

    public boolean side_effects() {
        return this.binding == null || !this.binding.isLexical();
    }

    public String toString() {
        return "RefExp/" + this.symbol + '/' + this.id + '/';
    }
}
