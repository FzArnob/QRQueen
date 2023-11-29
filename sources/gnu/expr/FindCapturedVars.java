package gnu.expr;

import gnu.mapping.EnvironmentKey;
import gnu.mapping.KeyPair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

public class FindCapturedVars extends ExpExpVisitor<Void> {
    int backJumpPossible = 0;
    ModuleExp currentModule = null;
    Hashtable unknownDecls = null;

    public static void findCapturedVars(Expression expression, Compilation compilation) {
        FindCapturedVars findCapturedVars = new FindCapturedVars();
        findCapturedVars.setContext(compilation);
        expression.visit(findCapturedVars, null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (((gnu.expr.LambdaExp) r1).getNeedsClosureEnv() == false) goto L_0x0040;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression visitApplyExp(gnu.expr.ApplyExp r9, java.lang.Void r10) {
        /*
            r8 = this;
            int r0 = r8.backJumpPossible
            gnu.expr.Expression r1 = r9.func
            boolean r1 = r1 instanceof gnu.expr.ReferenceExp
            r2 = 4096(0x1000, double:2.0237E-320)
            r4 = 0
            r5 = 1
            if (r1 == 0) goto L_0x0042
            int r1 = gnu.expr.Compilation.defaultCallConvention
            if (r1 > r5) goto L_0x0042
            gnu.expr.Expression r1 = r9.func
            gnu.expr.ReferenceExp r1 = (gnu.expr.ReferenceExp) r1
            gnu.expr.Declaration r1 = r1.binding
            gnu.expr.Declaration r1 = gnu.expr.Declaration.followAliases(r1)
            if (r1 == 0) goto L_0x003f
            gnu.expr.ScopeExp r6 = r1.context
            boolean r6 = r6 instanceof gnu.expr.ModuleExp
            if (r6 == 0) goto L_0x003f
            boolean r6 = r1.isPublic()
            if (r6 != 0) goto L_0x003f
            boolean r2 = r1.getFlag(r2)
            if (r2 != 0) goto L_0x003f
            gnu.expr.Expression r1 = r1.getValue()
            boolean r2 = r1 instanceof gnu.expr.LambdaExp
            if (r2 == 0) goto L_0x003f
            gnu.expr.LambdaExp r1 = (gnu.expr.LambdaExp) r1
            boolean r1 = r1.getNeedsClosureEnv()
            if (r1 != 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r5 = 0
        L_0x0040:
            r4 = r5
            goto L_0x00a2
        L_0x0042:
            gnu.expr.Expression r1 = r9.func
            boolean r1 = r1 instanceof gnu.expr.QuoteExp
            if (r1 == 0) goto L_0x00a2
            int r1 = r9.getArgCount()
            if (r1 <= 0) goto L_0x00a2
            gnu.expr.Expression r1 = r9.func
            gnu.expr.QuoteExp r1 = (gnu.expr.QuoteExp) r1
            java.lang.Object r1 = r1.getValue()
            gnu.expr.Expression r6 = r9.getArg(r4)
            boolean r7 = r1 instanceof gnu.expr.PrimProcedure
            if (r7 == 0) goto L_0x00a2
            boolean r7 = r6 instanceof gnu.expr.ReferenceExp
            if (r7 == 0) goto L_0x00a2
            gnu.expr.PrimProcedure r1 = (gnu.expr.PrimProcedure) r1
            gnu.expr.ReferenceExp r6 = (gnu.expr.ReferenceExp) r6
            gnu.expr.Declaration r1 = r6.binding
            gnu.expr.Declaration r1 = gnu.expr.Declaration.followAliases(r1)
            if (r1 == 0) goto L_0x00a2
            gnu.expr.ScopeExp r6 = r1.context
            boolean r6 = r6 instanceof gnu.expr.ModuleExp
            if (r6 == 0) goto L_0x00a2
            boolean r2 = r1.getFlag(r2)
            if (r2 != 0) goto L_0x00a2
            gnu.expr.Expression r2 = r1.getValue()
            boolean r3 = r2 instanceof gnu.expr.ClassExp
            if (r3 == 0) goto L_0x00a2
            gnu.expr.Expression[] r3 = r9.getArgs()
            gnu.expr.LambdaExp r2 = (gnu.expr.LambdaExp) r2
            boolean r2 = r2.getNeedsClosureEnv()
            if (r2 != 0) goto L_0x00a2
            gnu.expr.ApplyExp r2 = r1.firstCall
            r9.nextCall = r2
            r1.firstCall = r9
            r1 = 1
        L_0x0095:
            int r2 = r3.length
            if (r1 >= r2) goto L_0x00a0
            r2 = r3[r1]
            r2.visit(r8, r10)
            int r1 = r1 + 1
            goto L_0x0095
        L_0x00a0:
            r4 = 1
            goto L_0x00a3
        L_0x00a2:
            r5 = 0
        L_0x00a3:
            if (r4 != 0) goto L_0x00af
            gnu.expr.Expression r1 = r9.func
            java.lang.Object r1 = r1.visit(r8, r10)
            gnu.expr.Expression r1 = (gnu.expr.Expression) r1
            r9.func = r1
        L_0x00af:
            java.lang.Object r1 = r8.exitValue
            if (r1 != 0) goto L_0x00bd
            if (r5 != 0) goto L_0x00bd
            gnu.expr.Expression[] r1 = r9.args
            gnu.expr.Expression[] r10 = r8.visitExps(r1, r10)
            r9.args = r10
        L_0x00bd:
            int r10 = r8.backJumpPossible
            if (r10 <= r0) goto L_0x00c6
            r10 = 8
            r9.setFlag(r10)
        L_0x00c6:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.FindCapturedVars.visitApplyExp(gnu.expr.ApplyExp, java.lang.Void):gnu.expr.Expression");
    }

    public void visitDefaultArgs(LambdaExp lambdaExp, Void voidR) {
        if (lambdaExp.defaultArgs != null) {
            super.visitDefaultArgs(lambdaExp, voidR);
            for (Declaration firstDecl = lambdaExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
                if (!firstDecl.isSimple()) {
                    lambdaExp.setFlag(true, 512);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitClassExp(ClassExp classExp, Void voidR) {
        Expression expression = (Expression) super.visitClassExp(classExp, voidR);
        if (!classExp.explicitInit && !classExp.instanceType.isInterface()) {
            Compilation.getConstructor(classExp.instanceType, classExp);
        } else if (classExp.getNeedsClosureEnv()) {
            for (LambdaExp lambdaExp = classExp.firstChild; lambdaExp != null; lambdaExp = lambdaExp.nextSibling) {
                if ("*init*".equals(lambdaExp.getName())) {
                    lambdaExp.setNeedsStaticLink(true);
                }
            }
        }
        if (classExp.isSimple() && classExp.getNeedsClosureEnv() && classExp.nameDecl != null && classExp.nameDecl.getType() == Compilation.typeClass) {
            classExp.nameDecl.setType(Compilation.typeClassType);
        }
        return expression;
    }

    /* access modifiers changed from: protected */
    public Expression visitModuleExp(ModuleExp moduleExp, Void voidR) {
        ModuleExp moduleExp2 = this.currentModule;
        Hashtable hashtable = this.unknownDecls;
        this.currentModule = moduleExp;
        this.unknownDecls = null;
        try {
            return visitLambdaExp((LambdaExp) moduleExp, voidR);
        } finally {
            this.currentModule = moduleExp2;
            this.unknownDecls = hashtable;
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeWarnNoDeclarationSeen(Object obj, Compilation compilation, SourceLocator sourceLocator) {
        if (compilation.warnUndefinedVariable()) {
            compilation.error('w', "no declaration seen for " + obj, sourceLocator);
        }
    }

    /* access modifiers changed from: protected */
    public Expression visitFluidLetExp(FluidLetExp fluidLetExp, Void voidR) {
        for (Declaration firstDecl = fluidLetExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (firstDecl.base == null) {
                Object symbol = firstDecl.getSymbol();
                Declaration allocUnboundDecl = allocUnboundDecl(symbol, false);
                maybeWarnNoDeclarationSeen(symbol, this.comp, fluidLetExp);
                capture(allocUnboundDecl);
                firstDecl.base = allocUnboundDecl;
            }
        }
        return (Expression) super.visitLetExp(fluidLetExp, voidR);
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Void voidR) {
        if (letExp.body instanceof BeginExp) {
            Expression[] expressionArr = letExp.inits;
            int length = expressionArr.length;
            Expression[] expressionArr2 = ((BeginExp) letExp.body).exps;
            Declaration firstDecl = letExp.firstDecl();
            int i = 0;
            for (int i2 = 0; i2 < expressionArr2.length && i < length; i2++) {
                Expression expression = expressionArr2[i2];
                if (expression instanceof SetExp) {
                    SetExp setExp = (SetExp) expression;
                    if (setExp.binding == firstDecl && expressionArr[i] == QuoteExp.nullExp && setExp.isDefining()) {
                        Expression expression2 = setExp.new_value;
                        if (((expression2 instanceof QuoteExp) || (expression2 instanceof LambdaExp)) && firstDecl.getValue() == expression2) {
                            expressionArr[i] = expression2;
                            expressionArr2[i2] = QuoteExp.voidExp;
                        }
                        i++;
                        firstDecl = firstDecl.nextDecl();
                    }
                }
            }
        }
        return (Expression) super.visitLetExp(letExp, voidR);
    }

    static Expression checkInlineable(LambdaExp lambdaExp, Set<LambdaExp> set) {
        if (lambdaExp.returnContinuation == LambdaExp.unknownContinuation) {
            return lambdaExp.returnContinuation;
        }
        if (set.contains(lambdaExp)) {
            return lambdaExp.returnContinuation;
        }
        if (lambdaExp.getCanRead() || lambdaExp.isClassMethod() || lambdaExp.min_args != lambdaExp.max_args) {
            lambdaExp.returnContinuation = LambdaExp.unknownContinuation;
            return LambdaExp.unknownContinuation;
        }
        set.add(lambdaExp);
        Expression expression = lambdaExp.returnContinuation;
        if (lambdaExp.tailCallers != null) {
            for (LambdaExp next : lambdaExp.tailCallers) {
                Expression checkInlineable = checkInlineable(next, set);
                if (checkInlineable == LambdaExp.unknownContinuation) {
                    if (expression == null || expression == next.body) {
                        expression = next.body;
                        lambdaExp.inlineHome = next;
                    } else {
                        lambdaExp.returnContinuation = LambdaExp.unknownContinuation;
                        return checkInlineable;
                    }
                } else if (expression == null) {
                    if (lambdaExp.inlineHome == null) {
                        if (!lambdaExp.nestedIn(next)) {
                            next = next.inlineHome;
                        }
                        lambdaExp.inlineHome = next;
                    }
                    expression = checkInlineable;
                } else if ((checkInlineable != null && expression != checkInlineable) || lambdaExp.getFlag(32)) {
                    lambdaExp.returnContinuation = LambdaExp.unknownContinuation;
                    return LambdaExp.unknownContinuation;
                }
            }
        }
        return expression;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Void voidR) {
        if (checkInlineable(lambdaExp, new LinkedHashSet()) != LambdaExp.unknownContinuation && (!(lambdaExp.outer instanceof ModuleExp) || lambdaExp.nameDecl == null)) {
            lambdaExp.setInlineOnly(true);
            this.backJumpPossible++;
        }
        return (Expression) super.visitLambdaExp(lambdaExp, voidR);
    }

    public void capture(Declaration declaration) {
        if (!declaration.getCanRead() && !declaration.getCanCall()) {
            return;
        }
        if (declaration.field != null && declaration.field.getStaticFlag()) {
            return;
        }
        if (!this.comp.immediate || !declaration.hasConstantValue()) {
            LambdaExp currentLambda = getCurrentLambda();
            ScopeExp context = declaration.getContext();
            if (context != null) {
                LambdaExp currentLambda2 = context.currentLambda();
                LambdaExp lambdaExp = null;
                LambdaExp lambdaExp2 = null;
                LambdaExp lambdaExp3 = null;
                while (currentLambda != currentLambda2 && currentLambda.getInlineOnly()) {
                    LambdaExp outerLambda = currentLambda.outerLambda();
                    if (outerLambda != lambdaExp2) {
                        lambdaExp3 = outerLambda.firstChild;
                        lambdaExp2 = outerLambda;
                    }
                    if (lambdaExp3 == null || currentLambda.inlineHome == null) {
                        currentLambda.setCanCall(false);
                        return;
                    } else {
                        currentLambda = currentLambda.getCaller();
                        lambdaExp3 = lambdaExp3.nextSibling;
                    }
                }
                if (this.comp.usingCPStyle()) {
                    if (currentLambda instanceof ModuleExp) {
                        return;
                    }
                } else if (currentLambda == currentLambda2) {
                    return;
                }
                Expression value = declaration.getValue();
                if (value != null && (value instanceof LambdaExp)) {
                    LambdaExp lambdaExp4 = (LambdaExp) value;
                    if (!lambdaExp4.getInlineOnly()) {
                        if (!lambdaExp4.isHandlingTailCalls()) {
                            if (lambdaExp4 != currentLambda || declaration.getCanRead()) {
                                lambdaExp = lambdaExp4;
                            } else {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
                if (declaration.getFlag(65536)) {
                    LambdaExp lambdaExp5 = currentLambda;
                    while (true) {
                        if (lambdaExp5 != currentLambda2) {
                            if (lambdaExp5.nameDecl != null && lambdaExp5.nameDecl.getFlag(2048)) {
                                declaration.setFlag(2048);
                                break;
                            }
                            lambdaExp5 = lambdaExp5.outerLambda();
                        } else {
                            break;
                        }
                    }
                }
                if (declaration.base != null) {
                    declaration.base.setCanRead(true);
                    capture(declaration.base);
                } else if (declaration.getCanRead() || declaration.getCanCall() || lambdaExp == null) {
                    if (!declaration.isStatic()) {
                        if (!declaration.isFluid()) {
                            currentLambda.setImportsLexVars();
                        }
                        LambdaExp outerLambda2 = currentLambda.outerLambda();
                        while (outerLambda2 != currentLambda2 && outerLambda2 != null && (declaration.getCanRead() || lambdaExp != outerLambda2)) {
                            Declaration declaration2 = outerLambda2.nameDecl;
                            if (declaration2 != null && declaration2.getFlag(2048)) {
                                this.comp.error('e', "static " + outerLambda2.getName() + " references non-static " + declaration.getName());
                            }
                            if ((outerLambda2 instanceof ClassExp) && outerLambda2.getName() != null && ((ClassExp) outerLambda2).isSimple()) {
                                this.comp.error('w', outerLambda2.nameDecl, "simple class ", " requiring lexical link (because of reference to " + declaration.getName() + ") - use define-class instead");
                            }
                            outerLambda2.setNeedsStaticLink();
                            outerLambda2 = outerLambda2.outerLambda();
                        }
                    }
                    if (currentLambda2 == null) {
                        System.err.println("null declLambda for " + declaration + " curL:" + currentLambda);
                        for (ScopeExp scopeExp = declaration.context; scopeExp != null; scopeExp = scopeExp.outer) {
                            System.err.println("- context:" + scopeExp);
                        }
                    }
                    currentLambda2.capture(declaration);
                }
            } else {
                throw new Error("null context for " + declaration + " curL:" + currentLambda);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Declaration allocUnboundDecl(Object obj, boolean z) {
        Object obj2;
        Declaration declaration;
        if (!z || !(obj instanceof Symbol)) {
            obj2 = obj;
        } else if (!getCompilation().getLanguage().hasSeparateFunctionNamespace()) {
            obj2 = obj;
            z = false;
        } else {
            obj2 = new KeyPair((Symbol) obj, EnvironmentKey.FUNCTION);
        }
        Hashtable hashtable = this.unknownDecls;
        if (hashtable == null) {
            this.unknownDecls = new Hashtable(100);
            declaration = null;
        } else {
            declaration = (Declaration) hashtable.get(obj2);
        }
        if (declaration == null) {
            declaration = this.currentModule.addDeclaration(obj);
            declaration.setSimple(false);
            declaration.setPrivate(true);
            if (z) {
                declaration.setProcedureDecl(true);
            }
            if (this.currentModule.isStatic()) {
                declaration.setFlag(2048);
            }
            declaration.setCanRead(true);
            declaration.setCanWrite(true);
            declaration.setFlag(327680);
            declaration.setIndirectBinding(true);
            this.unknownDecls.put(obj2, declaration);
        }
        return declaration;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Void voidR) {
        Declaration binding = referenceExp.getBinding();
        if (binding == null) {
            binding = allocUnboundDecl(referenceExp.getSymbol(), referenceExp.isProcedureName());
            referenceExp.setBinding(binding);
        }
        if (binding.getFlag(65536) && this.comp.resolve(referenceExp.getSymbol(), referenceExp.isProcedureName()) == null) {
            maybeWarnNoDeclarationSeen(referenceExp.getSymbol(), this.comp, referenceExp);
        }
        capture(referenceExp.contextDecl(), binding);
        return referenceExp;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
        r0 = (gnu.expr.ReferenceExp) r5.value;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void capture(gnu.expr.Declaration r4, gnu.expr.Declaration r5) {
        /*
            r3 = this;
            boolean r0 = r5.isAlias()
            if (r0 == 0) goto L_0x0024
            gnu.expr.Expression r0 = r5.value
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            if (r0 == 0) goto L_0x0024
            gnu.expr.Expression r0 = r5.value
            gnu.expr.ReferenceExp r0 = (gnu.expr.ReferenceExp) r0
            gnu.expr.Declaration r1 = r0.binding
            if (r1 == 0) goto L_0x0024
            if (r4 == 0) goto L_0x001c
            boolean r2 = r1.needsContext()
            if (r2 != 0) goto L_0x0024
        L_0x001c:
            gnu.expr.Declaration r4 = r0.contextDecl()
            r3.capture(r4, r1)
            return
        L_0x0024:
            boolean r0 = r5.isFluid()
            if (r0 == 0) goto L_0x0033
            gnu.expr.ScopeExp r0 = r5.context
            boolean r0 = r0 instanceof gnu.expr.FluidLetExp
            if (r0 == 0) goto L_0x0033
            gnu.expr.Declaration r5 = r5.base
            goto L_0x0024
        L_0x0033:
            if (r4 == 0) goto L_0x003f
            boolean r0 = r5.needsContext()
            if (r0 == 0) goto L_0x003f
            r3.capture(r4)
            goto L_0x0042
        L_0x003f:
            r3.capture(r5)
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.FindCapturedVars.capture(gnu.expr.Declaration, gnu.expr.Declaration):void");
    }

    /* access modifiers changed from: protected */
    public Expression visitThisExp(ThisExp thisExp, Void voidR) {
        if (!thisExp.isForContext()) {
            return visitReferenceExp((ReferenceExp) thisExp, voidR);
        }
        getCurrentLambda().setImportsLexVars();
        return thisExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void voidR) {
        Declaration declaration = setExp.binding;
        if (declaration == null) {
            declaration = allocUnboundDecl(setExp.getSymbol(), setExp.isFuncDef());
            setExp.binding = declaration;
        }
        if (!declaration.ignorable()) {
            if (!setExp.isDefining()) {
                declaration = Declaration.followAliases(declaration);
            }
            capture(setExp.contextDecl(), declaration);
        }
        return (Expression) super.visitSetExp(setExp, voidR);
    }
}
