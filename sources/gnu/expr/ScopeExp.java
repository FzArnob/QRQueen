package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

public abstract class ScopeExp extends Expression {
    static int counter;
    Declaration decls;
    protected int frameSize;
    public int id;
    Declaration last;
    public ScopeExp outer;
    private Scope scope;

    public Declaration firstDecl() {
        return this.decls;
    }

    public Scope getVarScope() {
        Scope scope2 = this.scope;
        if (scope2 != null) {
            return scope2;
        }
        Scope scope3 = new Scope();
        this.scope = scope3;
        return scope3;
    }

    public void popScope(CodeAttr codeAttr) {
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            firstDecl.var = null;
        }
        codeAttr.popScope();
        this.scope = null;
    }

    public void add(Declaration declaration) {
        Declaration declaration2 = this.last;
        if (declaration2 == null) {
            this.decls = declaration;
        } else {
            declaration2.next = declaration;
        }
        this.last = declaration;
        declaration.context = this;
    }

    public void add(Declaration declaration, Declaration declaration2) {
        if (declaration == null) {
            declaration2.next = this.decls;
            this.decls = declaration2;
        } else {
            declaration2.next = declaration.next;
            declaration.next = declaration2;
        }
        if (this.last == declaration) {
            this.last = declaration2;
        }
        declaration2.context = this;
    }

    public void replaceFollowing(Declaration declaration, Declaration declaration2) {
        Declaration declaration3;
        if (declaration == null) {
            declaration3 = this.decls;
            this.decls = declaration2;
        } else {
            Declaration declaration4 = declaration.next;
            declaration.next = declaration2;
            declaration3 = declaration4;
        }
        declaration2.next = declaration3.next;
        if (this.last == declaration3) {
            this.last = declaration2;
        }
        declaration3.next = null;
        declaration2.context = this;
    }

    public void remove(Declaration declaration) {
        Declaration declaration2 = null;
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (firstDecl == declaration) {
                remove(declaration2, declaration);
                return;
            }
            declaration2 = firstDecl;
        }
    }

    public void remove(Declaration declaration, Declaration declaration2) {
        if (declaration == null) {
            this.decls = declaration2.next;
        } else {
            declaration.next = declaration2.next;
        }
        if (this.last == declaration2) {
            this.last = declaration;
        }
    }

    public ScopeExp() {
        int i = counter + 1;
        counter = i;
        this.id = i;
    }

    public LambdaExp currentLambda() {
        for (ScopeExp scopeExp = this; scopeExp != null; scopeExp = scopeExp.outer) {
            if (scopeExp instanceof LambdaExp) {
                return (LambdaExp) scopeExp;
            }
        }
        return null;
    }

    public ScopeExp topLevel() {
        ScopeExp scopeExp = this;
        while (true) {
            ScopeExp scopeExp2 = scopeExp.outer;
            if (scopeExp2 == null || (scopeExp2 instanceof ModuleExp)) {
                return scopeExp;
            }
            scopeExp = scopeExp2;
        }
        return scopeExp;
    }

    public ModuleExp currentModule() {
        for (ScopeExp scopeExp = this; scopeExp != null; scopeExp = scopeExp.outer) {
            if (scopeExp instanceof ModuleExp) {
                return (ModuleExp) scopeExp;
            }
        }
        return null;
    }

    public Declaration lookup(Object obj) {
        if (obj == null) {
            return null;
        }
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (obj.equals(firstDecl.symbol)) {
                return firstDecl;
            }
        }
        return null;
    }

    public Declaration lookup(Object obj, Language language, int i) {
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (obj.equals(firstDecl.symbol) && language.hasNamespace(firstDecl, i)) {
                return firstDecl;
            }
        }
        return null;
    }

    public Declaration getNoDefine(Object obj) {
        Declaration lookup = lookup(obj);
        if (lookup != null) {
            return lookup;
        }
        Declaration addDeclaration = addDeclaration(obj);
        addDeclaration.flags |= 66048;
        return addDeclaration;
    }

    public Declaration getDefine(Object obj, char c, Compilation compilation) {
        Declaration lookup = lookup(obj);
        if (lookup == null) {
            return addDeclaration(obj);
        }
        if ((lookup.flags & 66048) != 0) {
            lookup.flags &= -66049;
            return lookup;
        }
        Declaration addDeclaration = addDeclaration(obj);
        duplicateDeclarationError(lookup, addDeclaration, compilation);
        return addDeclaration;
    }

    public static void duplicateDeclarationError(Declaration declaration, Declaration declaration2, Compilation compilation) {
        compilation.error('e', declaration2, "duplicate declaration of '", "'");
        compilation.error('e', declaration, "(this is the previous declaration of '", "')");
    }

    public final Declaration addDeclaration(Object obj) {
        Declaration declaration = new Declaration(obj);
        add(declaration);
        return declaration;
    }

    public final Declaration addDeclaration(Object obj, Type type) {
        Declaration declaration = new Declaration(obj, type);
        add(declaration);
        return declaration;
    }

    public final void addDeclaration(Declaration declaration) {
        add(declaration);
    }

    public int countDecls() {
        int i = 0;
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            i++;
        }
        return i;
    }

    public int countNonDynamicDecls() {
        int i = 0;
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (!firstDecl.getFlag(268435456)) {
                i++;
            }
        }
        return i;
    }

    public static int nesting(ScopeExp scopeExp) {
        int i = 0;
        while (scopeExp != null) {
            scopeExp = scopeExp.outer;
            i++;
        }
        return i;
    }

    public boolean nestedIn(ScopeExp scopeExp) {
        for (ScopeExp scopeExp2 = this; scopeExp2 != null; scopeExp2 = scopeExp2.outer) {
            if (scopeExp2 == scopeExp) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void setIndexes() {
        Declaration firstDecl = firstDecl();
        int i = 0;
        while (firstDecl != null) {
            firstDecl.evalIndex = i;
            firstDecl = firstDecl.nextDecl();
            i++;
        }
        this.frameSize = i;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitScopeExp(this, d);
    }

    public String toString() {
        return getClass().getName() + "#" + this.id;
    }
}
