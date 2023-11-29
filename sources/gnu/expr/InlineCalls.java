package gnu.expr;

import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class InlineCalls extends ExpExpVisitor<Type> {
    private static Class[] inlinerMethodArgTypes;

    public static Expression inlineCalls(Expression expression, Compilation compilation) {
        return new InlineCalls(compilation).visit(expression, (Type) null);
    }

    public InlineCalls(Compilation compilation) {
        setContext(compilation);
    }

    public Expression visit(Expression expression, Type type) {
        if (!expression.getFlag(1)) {
            expression.setFlag(1);
            expression = (Expression) super.visit(expression, type);
            expression.setFlag(1);
        }
        return checkType(expression, type);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression checkType(gnu.expr.Expression r7, gnu.bytecode.Type r8) {
        /*
            r6 = this;
            gnu.bytecode.Type r0 = r7.getType()
            boolean r1 = r8 instanceof gnu.bytecode.ClassType
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0060
            r1 = r8
            gnu.bytecode.ClassType r1 = (gnu.bytecode.ClassType) r1
            boolean r4 = r1.isInterface()
            if (r4 == 0) goto L_0x0060
            gnu.bytecode.ClassType r4 = gnu.expr.Compilation.typeProcedure
            boolean r4 = r0.isSubtype(r4)
            if (r4 == 0) goto L_0x0060
            boolean r4 = r0.isSubtype(r8)
            if (r4 != 0) goto L_0x0060
            boolean r4 = r7 instanceof gnu.expr.LambdaExp
            if (r4 == 0) goto L_0x0081
            gnu.bytecode.Method r1 = r1.checkSingleAbstractMethod()
            if (r1 == 0) goto L_0x0081
            r0 = r7
            gnu.expr.LambdaExp r0 = (gnu.expr.LambdaExp) r0
            gnu.expr.ObjectExp r4 = new gnu.expr.ObjectExp
            r4.<init>()
            r4.setLocation(r7)
            gnu.expr.Expression[] r7 = new gnu.expr.Expression[r3]
            gnu.expr.QuoteExp r3 = new gnu.expr.QuoteExp
            r3.<init>(r8)
            r7[r2] = r3
            r4.supers = r7
            gnu.expr.Compilation r7 = r6.getCompilation()
            r4.setTypes(r7)
            java.lang.String r7 = r1.getName()
            r4.addMethod(r0, r7)
            gnu.bytecode.ClassType r1 = gnu.expr.Compilation.typeProcedure
            r4.addDeclaration(r7, r1)
            r4.firstChild = r0
            gnu.expr.Compilation r7 = r6.comp
            r4.declareParts(r7)
            gnu.expr.Expression r7 = r6.visit((gnu.expr.Expression) r4, (gnu.bytecode.Type) r8)
            return r7
        L_0x0060:
            gnu.bytecode.ClassType r1 = gnu.bytecode.Type.toStringType
            if (r0 != r1) goto L_0x0066
            gnu.bytecode.ClassType r0 = gnu.bytecode.Type.javalangStringType
        L_0x0066:
            if (r8 == 0) goto L_0x0070
            int r1 = r8.compare(r0)
            r4 = -3
            if (r1 != r4) goto L_0x0070
            r2 = 1
        L_0x0070:
            if (r2 == 0) goto L_0x0080
            boolean r1 = r8 instanceof gnu.expr.TypeValue
            if (r1 == 0) goto L_0x0080
            r1 = r8
            gnu.expr.TypeValue r1 = (gnu.expr.TypeValue) r1
            gnu.expr.Expression r1 = r1.convertValue(r7)
            if (r1 == 0) goto L_0x0080
            return r1
        L_0x0080:
            r3 = r2
        L_0x0081:
            if (r3 == 0) goto L_0x00b1
            gnu.expr.Compilation r1 = r6.comp
            gnu.expr.Language r1 = r1.getLanguage()
            gnu.expr.Compilation r2 = r6.comp
            r3 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "type "
            r4.append(r5)
            java.lang.String r0 = r1.formatType(r0)
            r4.append(r0)
            java.lang.String r0 = " is incompatible with required type "
            r4.append(r0)
            java.lang.String r8 = r1.formatType(r8)
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            r2.error(r3, r8, r7)
        L_0x00b1:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.InlineCalls.checkType(gnu.expr.Expression, gnu.bytecode.Type):gnu.expr.Expression");
    }

    /* access modifiers changed from: protected */
    public Expression visitApplyExp(ApplyExp applyExp, Type type) {
        Expression inlineCall;
        Expression expression = applyExp.func;
        if ((expression instanceof LambdaExp) && (inlineCall = inlineCall((LambdaExp) expression, applyExp.args, false)) != null) {
            return visit(inlineCall, type);
        }
        Expression visit = visit(expression, (Type) null);
        applyExp.func = visit;
        return visit.validateApply(applyExp, this, type, (Declaration) null);
    }

    public final Expression visitApplyOnly(ApplyExp applyExp, Type type) {
        return applyExp.func.validateApply(applyExp, this, type, (Declaration) null);
    }

    public static Integer checkIntValue(Expression expression) {
        if (!(expression instanceof QuoteExp)) {
            return null;
        }
        QuoteExp quoteExp = (QuoteExp) expression;
        Object value = quoteExp.getValue();
        if (quoteExp.isExplicitlyTyped() || !(value instanceof IntNum)) {
            return null;
        }
        IntNum intNum = (IntNum) value;
        if (intNum.inIntRange()) {
            return Integer.valueOf(intNum.intValue());
        }
        return null;
    }

    public static Long checkLongValue(Expression expression) {
        if (!(expression instanceof QuoteExp)) {
            return null;
        }
        QuoteExp quoteExp = (QuoteExp) expression;
        Object value = quoteExp.getValue();
        if (quoteExp.isExplicitlyTyped() || !(value instanceof IntNum)) {
            return null;
        }
        IntNum intNum = (IntNum) value;
        if (intNum.inLongRange()) {
            return Long.valueOf(intNum.longValue());
        }
        return null;
    }

    public QuoteExp fixIntValue(Expression expression) {
        Integer checkIntValue = checkIntValue(expression);
        if (checkIntValue != null) {
            return new QuoteExp(checkIntValue, this.comp.getLanguage().getTypeFor(Integer.TYPE));
        }
        return null;
    }

    public QuoteExp fixLongValue(Expression expression) {
        Long checkLongValue = checkLongValue(expression);
        if (checkLongValue != null) {
            return new QuoteExp(checkLongValue, this.comp.getLanguage().getTypeFor(Long.TYPE));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Expression visitQuoteExp(QuoteExp quoteExp, Type type) {
        Object value;
        if (quoteExp.getRawType() != null || quoteExp.isSharedConstant() || (value = quoteExp.getValue()) == null) {
            return quoteExp;
        }
        Type typeFor = this.comp.getLanguage().getTypeFor((Class) value.getClass());
        if (typeFor == Type.toStringType) {
            typeFor = Type.javalangStringType;
        }
        quoteExp.type = typeFor;
        if (!(type instanceof PrimType)) {
            return quoteExp;
        }
        char charAt = type.getSignature().charAt(0);
        QuoteExp fixIntValue = charAt == 'I' ? fixIntValue(quoteExp) : charAt == 'J' ? fixLongValue(quoteExp) : null;
        return fixIntValue != null ? fixIntValue : quoteExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Type type) {
        Declaration binding = referenceExp.getBinding();
        if (binding != null && binding.field == null && !binding.getCanWrite()) {
            Expression value = binding.getValue();
            if ((value instanceof QuoteExp) && value != QuoteExp.undefined_exp) {
                return visitQuoteExp((QuoteExp) value, type);
            }
            if ((value instanceof ReferenceExp) && !binding.isAlias()) {
                ReferenceExp referenceExp2 = (ReferenceExp) value;
                Declaration binding2 = referenceExp2.getBinding();
                Type type2 = binding.getType();
                if (binding2 != null && !binding2.getCanWrite() && ((type2 == null || type2 == Type.pointer_type || type2 == binding2.getType()) && !referenceExp2.getDontDereference())) {
                    return visitReferenceExp(referenceExp2, type);
                }
            }
            if (!referenceExp.isProcedureName() && (binding.flags & 1048704) == 1048704) {
                Compilation compilation = this.comp;
                compilation.error('e', "unimplemented: reference to method " + binding.getName() + " as variable");
                this.comp.error('e', binding, "here is the definition of ", "");
            }
        }
        return (Expression) super.visitReferenceExp(referenceExp, type);
    }

    /* access modifiers changed from: protected */
    public Expression visitIfExp(IfExp ifExp, Type type) {
        Declaration binding;
        Expression expression = (Expression) ifExp.test.visit(this, null);
        if ((expression instanceof ReferenceExp) && (binding = ((ReferenceExp) expression).getBinding()) != null) {
            Expression value = binding.getValue();
            if ((value instanceof QuoteExp) && value != QuoteExp.undefined_exp) {
                expression = value;
            }
        }
        ifExp.test = expression;
        if (this.exitValue == null) {
            ifExp.then_clause = visit(ifExp.then_clause, type);
        }
        if (this.exitValue == null && ifExp.else_clause != null) {
            ifExp.else_clause = visit(ifExp.else_clause, type);
        }
        if (expression instanceof QuoteExp) {
            return ifExp.select(this.comp.getLanguage().isTrue(((QuoteExp) expression).getValue()));
        }
        if (!expression.getType().isVoid()) {
            return ifExp;
        }
        boolean isTrue = this.comp.getLanguage().isTrue(Values.empty);
        Compilation compilation = this.comp;
        compilation.error('w', "void-valued condition is always " + isTrue);
        return new BeginExp(expression, ifExp.select(isTrue));
    }

    /* access modifiers changed from: protected */
    public Expression visitBeginExp(BeginExp beginExp, Type type) {
        int i = beginExp.length - 1;
        int i2 = 0;
        while (i2 <= i) {
            beginExp.exps[i2] = visit(beginExp.exps[i2], i2 < i ? null : type);
            i2++;
        }
        return beginExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitScopeExp(ScopeExp scopeExp, Type type) {
        scopeExp.visitChildren(this, null);
        visitDeclarationTypes(scopeExp);
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (firstDecl.type == null) {
                Expression value = firstDecl.getValue();
                firstDecl.type = Type.objectType;
                firstDecl.setType((value == null || value == QuoteExp.undefined_exp) ? Type.objectType : value.getType());
            }
        }
        return scopeExp;
    }

    /* access modifiers changed from: protected */
    public Expression visitLetExp(LetExp letExp, Type type) {
        ReferenceExp referenceExp;
        Declaration binding;
        Declaration firstDecl = letExp.firstDecl();
        int length = letExp.inits.length;
        int i = 0;
        while (true) {
            Type type2 = null;
            if (i >= length) {
                break;
            }
            Expression expression = letExp.inits[i];
            boolean flag = firstDecl.getFlag(8192);
            if (flag && expression != QuoteExp.undefined_exp) {
                type2 = firstDecl.getType();
            }
            Expression visit = visit(expression, type2);
            letExp.inits[i] = visit;
            if (firstDecl.value == expression) {
                firstDecl.value = visit;
                if (!flag) {
                    firstDecl.setType(visit.getType());
                }
            }
            i++;
            firstDecl = firstDecl.nextDecl();
        }
        if (this.exitValue == null) {
            letExp.body = visit(letExp.body, type);
        }
        if (!(letExp.body instanceof ReferenceExp) || (binding = referenceExp.getBinding()) == null || binding.context != letExp || (referenceExp = (ReferenceExp) letExp.body).getDontDereference() || length != 1) {
            return letExp;
        }
        Expression expression2 = letExp.inits[0];
        Expression typeExp = binding.getTypeExp();
        return typeExp != QuoteExp.classObjectExp ? visitApplyOnly(Compilation.makeCoercion(expression2, typeExp), (Type) null) : expression2;
    }

    /* access modifiers changed from: protected */
    public Expression visitLambdaExp(LambdaExp lambdaExp, Type type) {
        Declaration firstDecl = lambdaExp.firstDecl();
        if (firstDecl != null && firstDecl.isThisParameter() && !lambdaExp.isClassMethod() && firstDecl.type == null) {
            firstDecl.setType(this.comp.mainClass);
        }
        return visitScopeExp((ScopeExp) lambdaExp, type);
    }

    /* access modifiers changed from: protected */
    public Expression visitTryExp(TryExp tryExp, Type type) {
        if (tryExp.getCatchClauses() == null && tryExp.getFinallyClause() == null) {
            return visit(tryExp.try_clause, type);
        }
        return (Expression) super.visitTryExp(tryExp, type);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExpValue(Expression expression, Type type, Declaration declaration) {
        return visit(expression, (declaration == null || declaration.isAlias()) ? null : declaration.type);
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Type type) {
        Declaration binding = setExp.getBinding();
        super.visitSetExp(setExp, type);
        if (!setExp.isDefining() && binding != null && (binding.flags & 1048704) == 1048704) {
            Compilation compilation = this.comp;
            compilation.error('e', "can't assign to method " + binding.getName(), setExp);
        }
        if (binding != null && binding.getFlag(8192) && CompileReflect.checkKnownClass(binding.getType(), this.comp) < 0) {
            binding.setType(Type.errorType);
        }
        return setExp;
    }

    private static synchronized Class[] getInlinerMethodArgTypes() throws Exception {
        Class[] clsArr;
        synchronized (InlineCalls.class) {
            clsArr = inlinerMethodArgTypes;
            if (clsArr == null) {
                clsArr = new Class[]{Class.forName("gnu.expr.ApplyExp"), Class.forName("gnu.expr.InlineCalls"), Class.forName("gnu.bytecode.Type"), Class.forName("gnu.mapping.Procedure")};
                inlinerMethodArgTypes = clsArr;
            }
        }
        return clsArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        if (r2 == null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r3 = new java.lang.Object[]{r8, r7, r9, r10};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0068, code lost:
        if ((r2 instanceof gnu.mapping.Procedure) == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0072, code lost:
        return (gnu.expr.Expression) ((gnu.mapping.Procedure) r2).applyN(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        if ((r2 instanceof java.lang.reflect.Method) == false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007f, code lost:
        return (gnu.expr.Expression) ((java.lang.reflect.Method) r2).invoke((java.lang.Object) null, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression maybeInline(gnu.expr.ApplyExp r8, gnu.bytecode.Type r9, gnu.mapping.Procedure r10) {
        /*
            r7 = this;
            r0 = 101(0x65, float:1.42E-43)
            r1 = 0
            monitor-enter(r10)     // Catch:{ all -> 0x0083 }
            gnu.mapping.Symbol r2 = gnu.mapping.Procedure.validateApplyKey     // Catch:{ all -> 0x0080 }
            java.lang.Object r2 = r10.getProperty(r2, r1)     // Catch:{ all -> 0x0080 }
            boolean r3 = r2 instanceof java.lang.String     // Catch:{ all -> 0x0080 }
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x0056
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0080 }
            r3 = 58
            int r3 = r2.indexOf(r3)     // Catch:{ all -> 0x0080 }
            if (r3 <= 0) goto L_0x0038
            java.lang.String r6 = r2.substring(r4, r3)     // Catch:{ all -> 0x0080 }
            int r3 = r3 + r5
            java.lang.String r2 = r2.substring(r3)     // Catch:{ all -> 0x0080 }
            java.lang.Class r3 = r10.getClass()     // Catch:{ all -> 0x0080 }
            java.lang.ClassLoader r3 = r3.getClassLoader()     // Catch:{ all -> 0x0080 }
            java.lang.Class r3 = java.lang.Class.forName(r6, r5, r3)     // Catch:{ all -> 0x0080 }
            java.lang.Class[] r6 = getInlinerMethodArgTypes()     // Catch:{ all -> 0x0080 }
            java.lang.reflect.Method r2 = r3.getDeclaredMethod(r2, r6)     // Catch:{ all -> 0x0080 }
            goto L_0x0039
        L_0x0038:
            r2 = r1
        L_0x0039:
            if (r2 != 0) goto L_0x0056
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r8.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r9 = "inliner property string for "
            r8.append(r9)     // Catch:{ all -> 0x0080 }
            r8.append(r10)     // Catch:{ all -> 0x0080 }
            java.lang.String r9 = " is not of the form CLASS:METHOD"
            r8.append(r9)     // Catch:{ all -> 0x0080 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0080 }
            r7.error(r0, r8)     // Catch:{ all -> 0x0080 }
            monitor-exit(r10)     // Catch:{ all -> 0x0080 }
            return r1
        L_0x0056:
            monitor-exit(r10)     // Catch:{ all -> 0x0080 }
            if (r2 == 0) goto L_0x00ac
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0083 }
            r3[r4] = r8     // Catch:{ all -> 0x0083 }
            r3[r5] = r7     // Catch:{ all -> 0x0083 }
            r8 = 2
            r3[r8] = r9     // Catch:{ all -> 0x0083 }
            r8 = 3
            r3[r8] = r10     // Catch:{ all -> 0x0083 }
            boolean r8 = r2 instanceof gnu.mapping.Procedure     // Catch:{ all -> 0x0083 }
            if (r8 == 0) goto L_0x0073
            gnu.mapping.Procedure r2 = (gnu.mapping.Procedure) r2     // Catch:{ all -> 0x0083 }
            java.lang.Object r8 = r2.applyN(r3)     // Catch:{ all -> 0x0083 }
            gnu.expr.Expression r8 = (gnu.expr.Expression) r8     // Catch:{ all -> 0x0083 }
            return r8
        L_0x0073:
            boolean r8 = r2 instanceof java.lang.reflect.Method     // Catch:{ all -> 0x0083 }
            if (r8 == 0) goto L_0x00ac
            java.lang.reflect.Method r2 = (java.lang.reflect.Method) r2     // Catch:{ all -> 0x0083 }
            java.lang.Object r8 = r2.invoke(r1, r3)     // Catch:{ all -> 0x0083 }
            gnu.expr.Expression r8 = (gnu.expr.Expression) r8     // Catch:{ all -> 0x0083 }
            return r8
        L_0x0080:
            r8 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0080 }
            throw r8     // Catch:{ all -> 0x0083 }
        L_0x0083:
            r8 = move-exception
            boolean r9 = r8 instanceof java.lang.reflect.InvocationTargetException
            if (r9 == 0) goto L_0x008e
            java.lang.reflect.InvocationTargetException r8 = (java.lang.reflect.InvocationTargetException) r8
            java.lang.Throwable r8 = r8.getTargetException()
        L_0x008e:
            gnu.text.SourceMessages r9 = r7.messages
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "caught exception in inliner for "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r10 = " - "
            r2.append(r10)
            r2.append(r8)
            java.lang.String r10 = r2.toString()
            r9.error((char) r0, (java.lang.String) r10, (java.lang.Throwable) r8)
        L_0x00ac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.InlineCalls.maybeInline(gnu.expr.ApplyExp, gnu.bytecode.Type, gnu.mapping.Procedure):gnu.expr.Expression");
    }

    public static Expression inlineCall(LambdaExp lambdaExp, Expression[] expressionArr, boolean z) {
        Expression[] expressionArr2;
        IdentityHashTable identityHashTable;
        if (lambdaExp.keywords == null && (lambdaExp.nameDecl == null || z)) {
            int i = 0;
            boolean z2 = lambdaExp.max_args < 0;
            if ((lambdaExp.min_args == lambdaExp.max_args && lambdaExp.min_args == expressionArr.length) || (z2 && lambdaExp.min_args == 0)) {
                if (z) {
                    identityHashTable = new IdentityHashTable();
                    expressionArr2 = Expression.deepCopy(expressionArr, identityHashTable);
                    if (expressionArr2 == null && expressionArr != null) {
                        return null;
                    }
                } else {
                    expressionArr2 = expressionArr;
                    identityHashTable = null;
                }
                if (z2) {
                    Expression[] expressionArr3 = new Expression[(expressionArr.length + 1)];
                    expressionArr3[0] = QuoteExp.getInstance(lambdaExp.firstDecl().type);
                    System.arraycopy(expressionArr, 0, expressionArr3, 1, expressionArr.length);
                    expressionArr2 = new Expression[]{new ApplyExp((Procedure) Invoke.make, expressionArr3)};
                }
                LetExp letExp = new LetExp(expressionArr2);
                Declaration firstDecl = lambdaExp.firstDecl();
                Declaration declaration = null;
                while (firstDecl != null) {
                    Declaration nextDecl = firstDecl.nextDecl();
                    if (z) {
                        Declaration addDeclaration = letExp.addDeclaration(firstDecl.symbol, firstDecl.type);
                        if (firstDecl.typeExp != null) {
                            addDeclaration.typeExp = Expression.deepCopy(firstDecl.typeExp);
                            if (addDeclaration.typeExp == null) {
                                return null;
                            }
                        }
                        identityHashTable.put(firstDecl, addDeclaration);
                    } else {
                        lambdaExp.remove(declaration, firstDecl);
                        letExp.add(declaration, firstDecl);
                    }
                    if (!z2 && !firstDecl.getCanWrite()) {
                        firstDecl.setValue(expressionArr2[i]);
                    }
                    i++;
                    declaration = firstDecl;
                    firstDecl = nextDecl;
                }
                Expression expression = lambdaExp.body;
                if (z && (expression = Expression.deepCopy(expression, identityHashTable)) == null && lambdaExp.body != null) {
                    return null;
                }
                letExp.body = expression;
                return letExp;
            }
        }
        return null;
    }
}
