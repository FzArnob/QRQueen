package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;

public class ThisExp extends ReferenceExp {
    static int EVAL_TO_CONTEXT = 2;
    public static final String THIS_NAME = new String("$this$");
    ScopeExp context;

    public final boolean isForContext() {
        return (this.flags & EVAL_TO_CONTEXT) != 0;
    }

    public void apply(CallContext callContext) throws Throwable {
        if (isForContext()) {
            callContext.writeValue(this.context);
        } else {
            super.apply(callContext);
        }
    }

    public ScopeExp getContextScope() {
        return this.context;
    }

    public ThisExp() {
        super((Object) THIS_NAME);
    }

    public ThisExp(ScopeExp scopeExp) {
        super((Object) THIS_NAME);
        this.context = scopeExp;
    }

    public ThisExp(Declaration declaration) {
        super(THIS_NAME, declaration);
    }

    public ThisExp(ClassType classType) {
        this(new Declaration((Object) THIS_NAME, (Type) classType));
    }

    public static ThisExp makeGivingContext(ScopeExp scopeExp) {
        ThisExp thisExp = new ThisExp(scopeExp);
        thisExp.flags |= EVAL_TO_CONTEXT;
        return thisExp;
    }

    public void compile(Compilation compilation, Target target) {
        if (!(target instanceof IgnoreTarget)) {
            if (isForContext()) {
                CodeAttr code = compilation.getCode();
                if (compilation.method.getStaticFlag()) {
                    code.emitGetStatic(compilation.moduleInstanceMainField);
                } else {
                    code.emitPushThis();
                }
                target.compileFromStack(compilation, getType());
                return;
            }
            super.compile(compilation, target);
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitThisExp(this, d);
    }

    public final Type getType() {
        if (this.binding != null) {
            return this.binding.getType();
        }
        ScopeExp scopeExp = this.context;
        if ((scopeExp instanceof ClassExp) || (scopeExp instanceof ModuleExp)) {
            return scopeExp.getType();
        }
        return Type.pointer_type;
    }
}
