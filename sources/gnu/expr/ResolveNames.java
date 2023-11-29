package gnu.expr;

public class ResolveNames extends ExpExpVisitor<Void> {
    protected NameLookup lookup;

    public ResolveNames() {
    }

    public ResolveNames(Compilation compilation) {
        setContext(compilation);
        this.lookup = compilation.lexical;
    }

    public void resolveModule(ModuleExp moduleExp) {
        Compilation saveCurrent = Compilation.setSaveCurrent(this.comp);
        try {
            push(moduleExp);
            moduleExp.visitChildren(this, null);
        } finally {
            Compilation.restoreCurrent(saveCurrent);
        }
    }

    /* access modifiers changed from: protected */
    public void push(ScopeExp scopeExp) {
        this.lookup.push(scopeExp);
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, Void voidR) {
        visitDeclarationTypes(scopeExp);
        push(scopeExp);
        scopeExp.visitChildren(this, voidR);
        this.lookup.pop(scopeExp);
        return scopeExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Void voidR) {
        visitDeclarationTypes(letExp);
        letExp.visitInitializers(this, voidR);
        push(letExp);
        letExp.body = (Expression) visit(letExp.body, voidR);
        this.lookup.pop((ScopeExp) letExp);
        return letExp;
    }

    public Declaration lookup(Expression expression, Object obj, boolean z) {
        return this.lookup.lookup(obj, z);
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Void voidR) {
        Declaration lookup2;
        if (referenceExp.getBinding() == null && (lookup2 = lookup(referenceExp, referenceExp.getSymbol(), referenceExp.isProcedureName())) != null) {
            referenceExp.setBinding(lookup2);
        }
        return referenceExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void voidR) {
        if (setExp.binding == null) {
            Declaration lookup2 = lookup(setExp, setExp.getSymbol(), setExp.isFuncDef());
            if (lookup2 != null) {
                lookup2.setCanWrite(true);
            }
            setExp.binding = lookup2;
        }
        return (Expression) super.visitSetExp(setExp, voidR);
    }
}
