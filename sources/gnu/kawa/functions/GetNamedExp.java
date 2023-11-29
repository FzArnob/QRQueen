package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.PrimProcedure;
import gnu.expr.ReferenceExp;
import gnu.mapping.Procedure;

/* compiled from: CompileNamedPart */
class GetNamedExp extends ApplyExp {
    static final Declaration castDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.Convert", "as");
    static final Declaration fieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "field");
    static final Declaration instanceOfDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "instanceOf");
    static final Declaration invokeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invoke");
    static final Declaration invokeStaticDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invokeStatic");
    static final Declaration makeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "make");
    static final Declaration staticFieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "staticField");
    public String combinedName;
    char kind;
    PrimProcedure[] methods;

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = gnu.mapping.Environment.getCurrent();
        r1 = r0.getSymbol(r4.combinedName);
        r2 = gnu.mapping.Location.UNBOUND;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void apply(gnu.mapping.CallContext r5) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.String r0 = r4.combinedName
            if (r0 == 0) goto L_0x001b
            gnu.mapping.Environment r0 = gnu.mapping.Environment.getCurrent()
            java.lang.String r1 = r4.combinedName
            gnu.mapping.Symbol r1 = r0.getSymbol(r1)
            java.lang.String r2 = gnu.mapping.Location.UNBOUND
            r3 = 0
            java.lang.Object r0 = r0.get(r1, r3, r2)
            if (r0 == r2) goto L_0x001b
            r5.writeValue(r0)
            return
        L_0x001b:
            super.apply(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.GetNamedExp.apply(gnu.mapping.CallContext):void");
    }

    public GetNamedExp(Expression[] expressionArr) {
        super((Procedure) GetNamedPart.getNamedPart, expressionArr);
    }

    /* access modifiers changed from: protected */
    public GetNamedExp setProcedureKind(char c) {
        this.type = Compilation.typeProcedure;
        this.kind = c;
        return this;
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        Declaration declaration2;
        Expression[] expressionArr;
        Expression[] args = getArgs();
        Expression expression = args[0];
        Expression[] args2 = applyExp.getArgs();
        char c = this.kind;
        if (c == 'C') {
            declaration2 = castDecl;
            expressionArr = new Expression[(args2.length + 1)];
            System.arraycopy(args2, 1, expressionArr, 2, args2.length - 1);
            expressionArr[0] = expression;
            expressionArr[1] = args2[0];
        } else if (c == 'I') {
            declaration2 = instanceOfDecl;
            expressionArr = new Expression[(args2.length + 1)];
            System.arraycopy(args2, 1, expressionArr, 2, args2.length - 1);
            expressionArr[0] = args2[0];
            expressionArr[1] = expression;
        } else if (c == 'S') {
            Declaration declaration3 = invokeStaticDecl;
            Expression[] expressionArr2 = new Expression[(args2.length + 2)];
            expressionArr2[0] = expression;
            expressionArr2[1] = args[1];
            System.arraycopy(args2, 0, expressionArr2, 2, args2.length);
            declaration2 = declaration3;
            expressionArr = expressionArr2;
        } else if (c == 'M') {
            Declaration declaration4 = invokeDecl;
            expressionArr = new Expression[(args2.length + 2)];
            expressionArr[0] = args[0];
            expressionArr[1] = args[1];
            System.arraycopy(args2, 0, expressionArr, 2, args2.length);
            declaration2 = declaration4;
        } else if (c != 'N') {
            return applyExp;
        } else {
            declaration2 = makeDecl;
            expressionArr = new Expression[(args2.length + 1)];
            System.arraycopy(args2, 0, expressionArr, 1, args2.length);
            expressionArr[0] = expression;
        }
        ApplyExp applyExp2 = new ApplyExp((Expression) new ReferenceExp(declaration2), expressionArr);
        applyExp2.setLine((Expression) applyExp);
        return inlineCalls.visit((Expression) applyExp2, type);
    }

    public boolean side_effects() {
        char c = this.kind;
        if (c == 'S' || c == 'N' || c == 'C' || c == 'I') {
            return false;
        }
        if (c == 'M') {
            return getArgs()[0].side_effects();
        }
        return true;
    }
}
