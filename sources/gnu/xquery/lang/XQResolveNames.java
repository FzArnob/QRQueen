package gnu.xquery.lang;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ResolveNames;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.GetModuleClass;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.WrongArguments;
import gnu.xml.NamespaceBinding;
import gnu.xquery.util.NamedCollator;

public class XQResolveNames extends ResolveNames {
    public static final int BASE_URI_BUILTIN = -11;
    public static final int CASTABLE_AS_BUILTIN = -34;
    public static final int CAST_AS_BUILTIN = -33;
    public static final int COLLECTION_BUILTIN = -8;
    public static final int COMPARE_BUILTIN = -4;
    public static final int DEEP_EQUAL_BUILTIN = -25;
    public static final int DEFAULT_COLLATION_BUILTIN = -29;
    public static final int DISTINCT_VALUES_BUILTIN = -5;
    public static final int DOC_AVAILABLE_BUILTIN = -10;
    public static final int DOC_BUILTIN = -9;
    public static final int HANDLE_EXTENSION_BUILTIN = -3;
    public static final int IDREF_BUILTIN = -31;
    public static final int ID_BUILTIN = -30;
    public static final int INDEX_OF_BUILTIN = -15;
    public static final int LANG_BUILTIN = -23;
    public static final int LAST_BUILTIN = -1;
    public static final int LOCAL_NAME_BUILTIN = -6;
    public static final int MAX_BUILTIN = -27;
    public static final int MIN_BUILTIN = -26;
    public static final int NAMESPACE_URI_BUILTIN = -7;
    public static final int NAME_BUILTIN = -24;
    public static final int NORMALIZE_SPACE_BUILTIN = -17;
    public static final int NUMBER_BUILTIN = -28;
    public static final int POSITION_BUILTIN = -2;
    public static final int RESOLVE_PREFIX_BUILTIN = -13;
    public static final int RESOLVE_URI_BUILTIN = -12;
    public static final int ROOT_BUILTIN = -32;
    public static final int STATIC_BASE_URI_BUILTIN = -14;
    public static final int STRING_BUILTIN = -16;
    public static final int UNORDERED_BUILTIN = -18;
    public static final int XS_QNAME_BUILTIN = -35;
    public static final int XS_QNAME_IGNORE_DEFAULT_BUILTIN = -36;
    public static final Declaration castAsDecl = makeBuiltin("(cast as)", -33);
    public static final Declaration castableAsDecl = makeBuiltin("(castable as)", -34);
    public static final Declaration handleExtensionDecl = makeBuiltin("(extension)", -3);
    public static final Declaration lastDecl = makeBuiltin("last", -1);
    public static final Declaration resolvePrefixDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(resolve-prefix)"), -13);
    public static final Declaration staticBaseUriDecl = makeBuiltin("static-base-uri", -14);
    public static final Declaration xsQNameDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "QName"), -35);
    public static final Declaration xsQNameIgnoreDefaultDecl = makeBuiltin(Symbol.make(XQuery.SCHEMA_NAMESPACE, "(QName-ignore-default)"), -36);
    public Namespace[] functionNamespacePath;
    private Declaration moduleDecl;
    public XQParser parser;

    public Expression checkPragma(Symbol symbol, Expression expression) {
        return null;
    }

    public static Declaration makeBuiltin(String str, int i) {
        return makeBuiltin(Symbol.make(XQuery.XQUERY_FUNCTION_NAMESPACE, str, "fn"), i);
    }

    public static Declaration makeBuiltin(Symbol symbol, int i) {
        Declaration declaration = new Declaration((Object) symbol);
        declaration.setProcedureDecl(true);
        declaration.setCode(i);
        return declaration;
    }

    public XQResolveNames() {
        this((Compilation) null);
    }

    /* access modifiers changed from: package-private */
    public void pushBuiltin(String str, int i) {
        this.lookup.push(makeBuiltin(str, i));
    }

    public XQResolveNames(Compilation compilation) {
        super(compilation);
        this.functionNamespacePath = XQuery.defaultFunctionNamespacePath;
        this.lookup.push(lastDecl);
        this.lookup.push(xsQNameDecl);
        this.lookup.push(staticBaseUriDecl);
        pushBuiltin("position", -2);
        pushBuiltin("compare", -4);
        pushBuiltin("distinct-values", -5);
        pushBuiltin("local-name", -6);
        pushBuiltin(CommonProperties.NAME, -24);
        pushBuiltin("namespace-uri", -7);
        pushBuiltin("root", -32);
        pushBuiltin("base-uri", -11);
        pushBuiltin("lang", -23);
        pushBuiltin("resolve-uri", -12);
        pushBuiltin("collection", -8);
        pushBuiltin("doc", -9);
        pushBuiltin("document", -9);
        pushBuiltin("doc-available", -10);
        pushBuiltin("index-of", -15);
        pushBuiltin("string", -16);
        pushBuiltin("normalize-space", -17);
        pushBuiltin("unordered", -18);
        pushBuiltin("deep-equal", -25);
        pushBuiltin("min", -26);
        pushBuiltin("max", -27);
        pushBuiltin("number", -28);
        pushBuiltin("default-collation", -29);
        pushBuiltin(CommonProperties.ID, -30);
        pushBuiltin("idref", -31);
    }

    /* access modifiers changed from: protected */
    public void push(ScopeExp scopeExp) {
        for (Declaration firstDecl = scopeExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            push(firstDecl);
        }
    }

    /* access modifiers changed from: package-private */
    public void push(Declaration declaration) {
        Compilation compilation = getCompilation();
        Object symbol = declaration.getSymbol();
        boolean isProcedureDecl = declaration.isProcedureDecl();
        if (symbol instanceof String) {
            if (declaration.getLineNumber() <= 0 || compilation == null) {
                symbol = this.parser.namespaceResolve((String) symbol, isProcedureDecl);
            } else {
                String fileName = compilation.getFileName();
                int lineNumber = compilation.getLineNumber();
                int columnNumber = compilation.getColumnNumber();
                compilation.setLocation(declaration);
                symbol = this.parser.namespaceResolve((String) symbol, isProcedureDecl);
                compilation.setLine(fileName, lineNumber, columnNumber);
            }
            if (symbol != null) {
                declaration.setName(symbol);
            } else {
                return;
            }
        }
        Declaration lookup = this.lookup.lookup(symbol, XQuery.instance.getNamespaceOf(declaration));
        if (lookup != null) {
            if (declaration.context == lookup.context) {
                ScopeExp.duplicateDeclarationError(lookup, declaration, compilation);
            } else if (XQParser.warnHidePreviousDeclaration && (!(symbol instanceof Symbol) || ((Symbol) symbol).getNamespace() != null)) {
                compilation.error('w', declaration, "declaration ", " hides previous declaration");
            }
        }
        this.lookup.push(declaration);
    }

    /* access modifiers changed from: package-private */
    public Declaration flookup(Symbol symbol) {
        Declaration declaration;
        Location lookup = XQuery.xqEnvironment.lookup(symbol, EnvironmentKey.FUNCTION);
        if (lookup == null) {
            return null;
        }
        Location base = lookup.getBase();
        if ((base instanceof StaticFieldLocation) && (declaration = ((StaticFieldLocation) base).getDeclaration()) != null) {
            return declaration;
        }
        Object obj = base.get((Object) null);
        if (obj != null) {
            return procToDecl(symbol, obj);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Expression visitReferenceExp(ReferenceExp referenceExp, Void voidR) {
        ApplyExp applyExp = null;
        return visitReferenceExp(referenceExp, (ApplyExp) null);
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [gnu.bytecode.Type] */
    /* JADX WARNING: type inference failed for: r5v4, types: [gnu.bytecode.Type] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression visitReferenceExp(gnu.expr.ReferenceExp r11, gnu.expr.ApplyExp r12) {
        /*
            r10 = this;
            gnu.expr.Declaration r0 = r11.getBinding()
            if (r0 != 0) goto L_0x016c
            java.lang.Object r0 = r11.getSymbol()
            boolean r1 = r11.isProcedureName()
            r2 = 16
            boolean r2 = r11.getFlag(r2)
            if (r12 != 0) goto L_0x0018
            r12 = 1
            goto L_0x0020
        L_0x0018:
            int r12 = r12.getArgCount()
            int r12 = gnu.xquery.lang.XQuery.namespaceForFunctions(r12)
        L_0x0020:
            gnu.expr.NameLookup r3 = r10.lookup
            gnu.expr.Declaration r3 = r3.lookup((java.lang.Object) r0, (int) r12)
            if (r3 == 0) goto L_0x002a
            goto L_0x011a
        L_0x002a:
            boolean r4 = r0 instanceof gnu.mapping.Symbol
            r5 = 0
            r6 = 0
            java.lang.String r7 = ""
            if (r4 == 0) goto L_0x006c
            r8 = r0
            gnu.mapping.Symbol r8 = (gnu.mapping.Symbol) r8
            java.lang.String r9 = r8.getNamespaceURI()
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x006c
            java.lang.String r12 = r8.getLocalName()
            java.lang.String r4 = "request"
            boolean r4 = r4.equals(r12)
            if (r4 == 0) goto L_0x004e
            java.lang.String r5 = "getCurrentRequest"
            goto L_0x0058
        L_0x004e:
            java.lang.String r4 = "response"
            boolean r12 = r4.equals(r12)
            if (r12 == 0) goto L_0x0058
            java.lang.String r5 = "getCurrentResponse"
        L_0x0058:
            if (r5 == 0) goto L_0x011a
            java.lang.String r11 = "gnu.kawa.servlet.ServletRequestContext"
            gnu.bytecode.ClassType r11 = gnu.bytecode.ClassType.make(r11)
            gnu.bytecode.Method r11 = r11.getDeclaredMethod((java.lang.String) r5, (int) r6)
            gnu.expr.ApplyExp r12 = new gnu.expr.ApplyExp
            gnu.expr.Expression[] r0 = gnu.expr.Expression.noExpressions
            r12.<init>((gnu.bytecode.Method) r11, (gnu.expr.Expression[]) r0)
            return r12
        L_0x006c:
            if (r4 == 0) goto L_0x0077
            r12 = r0
            gnu.mapping.Symbol r12 = (gnu.mapping.Symbol) r12
            gnu.expr.Declaration r3 = r10.flookup(r12)
            goto L_0x011a
        L_0x0077:
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            r8 = 58
            int r8 = r4.indexOf(r8)
            if (r8 >= 0) goto L_0x00a7
            java.lang.String r4 = r4.intern()
            if (r1 == 0) goto L_0x00a7
        L_0x0088:
            gnu.mapping.Namespace[] r8 = r10.functionNamespacePath
            int r9 = r8.length
            if (r6 >= r9) goto L_0x00a7
            r3 = r8[r6]
            gnu.mapping.Symbol r3 = r3.getSymbol(r4)
            gnu.expr.NameLookup r8 = r10.lookup
            gnu.expr.Declaration r8 = r8.lookup((java.lang.Object) r3, (int) r12)
            if (r8 == 0) goto L_0x009d
            r3 = r8
            goto L_0x00a7
        L_0x009d:
            gnu.expr.Declaration r3 = r10.flookup(r3)
            if (r3 == 0) goto L_0x00a4
            goto L_0x00a7
        L_0x00a4:
            int r6 = r6 + 1
            goto L_0x0088
        L_0x00a7:
            if (r3 != 0) goto L_0x011a
            gnu.xquery.lang.XQParser r6 = r10.parser
            gnu.mapping.Symbol r4 = r6.namespaceResolve(r4, r1)
            if (r4 == 0) goto L_0x011a
            gnu.expr.NameLookup r3 = r10.lookup
            gnu.expr.Declaration r3 = r3.lookup((java.lang.Object) r4, (int) r12)
            if (r3 != 0) goto L_0x011a
            if (r1 != 0) goto L_0x00bd
            if (r2 == 0) goto L_0x011a
        L_0x00bd:
            java.lang.String r12 = r4.getNamespaceURI()
            java.lang.String r3 = "http://www.w3.org/2001/XMLSchema"
            boolean r3 = r3.equals(r12)
            if (r3 == 0) goto L_0x00d2
            java.lang.String r3 = r4.getName()
            gnu.bytecode.Type r5 = gnu.xquery.lang.XQuery.getStandardType(r3)
            goto L_0x00e8
        L_0x00d2:
            if (r2 == 0) goto L_0x00e8
            if (r12 != r7) goto L_0x00e8
            gnu.expr.Compilation r3 = r10.getCompilation()
            boolean r3 = r3.isPedantic()
            if (r3 != 0) goto L_0x00e8
            java.lang.String r3 = r4.getName()
            gnu.bytecode.Type r5 = kawa.standard.Scheme.string2Type(r3)
        L_0x00e8:
            if (r5 == 0) goto L_0x00f4
            gnu.expr.QuoteExp r12 = new gnu.expr.QuoteExp
            r12.<init>(r5)
            gnu.expr.Expression r11 = r12.setLine((gnu.expr.Expression) r11)
            return r11
        L_0x00f4:
            if (r12 == 0) goto L_0x0116
            int r3 = r12.length()
            r5 = 6
            if (r3 <= r5) goto L_0x0116
            java.lang.String r3 = "class:"
            boolean r3 = r12.startsWith(r3)
            if (r3 == 0) goto L_0x0116
            java.lang.String r11 = r12.substring(r5)
            gnu.bytecode.ClassType r11 = gnu.bytecode.ClassType.make(r11)
            java.lang.String r12 = r4.getName()
            gnu.expr.Expression r11 = gnu.kawa.functions.CompileNamedPart.makeExp((gnu.bytecode.Type) r11, (java.lang.String) r12)
            return r11
        L_0x0116:
            gnu.expr.Declaration r3 = r10.flookup(r4)
        L_0x011a:
            if (r3 == 0) goto L_0x0120
            r11.setBinding(r3)
            goto L_0x016c
        L_0x0120:
            r12 = 101(0x65, float:1.42E-43)
            if (r1 == 0) goto L_0x0139
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown function "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r10.error(r12, r0)
            goto L_0x016c
        L_0x0139:
            if (r2 == 0) goto L_0x0154
            gnu.text.SourceMessages r1 = r10.messages
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unknown type "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "XPST0051"
            r1.error(r12, r11, r0, r2)
            goto L_0x016c
        L_0x0154:
            gnu.text.SourceMessages r1 = r10.messages
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unknown variable $"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "XPST0008"
            r1.error(r12, r11, r0, r2)
        L_0x016c:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQResolveNames.visitReferenceExp(gnu.expr.ReferenceExp, gnu.expr.ApplyExp):gnu.expr.Expression");
    }

    /* access modifiers changed from: protected */
    public Expression visitSetExp(SetExp setExp, Void voidR) {
        Expression visitSetExp = super.visitSetExp(setExp, voidR);
        Declaration binding = setExp.getBinding();
        if (binding != null && !getCompilation().immediate) {
            Object symbol = binding.getSymbol();
            if ((symbol instanceof Symbol) && XQuery.LOCAL_NAMESPACE.equals(((Symbol) symbol).getNamespaceURI())) {
                Expression newValue = setExp.getNewValue();
                if (!(newValue instanceof ApplyExp) || ((ApplyExp) newValue).getFunction() != XQParser.getExternalFunction) {
                    binding.setFlag(16777216);
                    binding.setPrivate(true);
                }
            }
        }
        return visitSetExp;
    }

    private Expression visitStatements(Expression expression) {
        if (expression instanceof BeginExp) {
            BeginExp beginExp = (BeginExp) expression;
            Expression[] expressions = beginExp.getExpressions();
            int expressionCount = beginExp.getExpressionCount();
            for (int i = 0; i < expressionCount; i++) {
                expressions[i] = visitStatements(expressions[i]);
            }
            return expression;
        } else if (!(expression instanceof SetExp)) {
            return (Expression) visit(expression, null);
        } else {
            Declaration declaration = this.moduleDecl;
            SetExp setExp = (SetExp) expression;
            Expression visitSetExp = visitSetExp(setExp, (Void) null);
            if (setExp.isDefining() && setExp.getBinding() == declaration) {
                if (!declaration.isProcedureDecl()) {
                    push(declaration);
                }
                declaration = declaration.nextDecl();
            }
            this.moduleDecl = declaration;
            return visitSetExp;
        }
    }

    public void resolveModule(ModuleExp moduleExp) {
        this.currentLambda = moduleExp;
        for (Declaration firstDecl = moduleExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (firstDecl.isProcedureDecl()) {
                push(firstDecl);
            }
        }
        this.moduleDecl = moduleExp.firstDecl();
        moduleExp.body = visitStatements(moduleExp.body);
        for (Declaration firstDecl2 = moduleExp.firstDecl(); firstDecl2 != null; firstDecl2 = firstDecl2.nextDecl()) {
            if (firstDecl2.getSymbol() != null) {
                this.lookup.removeSubsumed(firstDecl2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Expression getCollator(Expression[] expressionArr, int i) {
        if (expressionArr == null || expressionArr.length <= i) {
            NamedCollator namedCollator = this.parser.defaultCollator;
            return namedCollator == null ? QuoteExp.nullExp : new QuoteExp(namedCollator);
        }
        return new ApplyExp(ClassType.make("gnu.xquery.util.NamedCollator").getDeclaredMethod("find", 1), expressionArr[i]);
    }

    /* access modifiers changed from: package-private */
    public Expression withCollator(Method method, Expression[] expressionArr, String str, int i) {
        return withCollator((Expression) new QuoteExp(new PrimProcedure(method)), expressionArr, str, i);
    }

    /* access modifiers changed from: package-private */
    public Expression withCollator(Expression expression, Expression[] expressionArr, String str, int i) {
        int i2 = i + 1;
        String checkArgCount = WrongArguments.checkArgCount(str, i, i2, expressionArr.length);
        if (checkArgCount != null) {
            return getCompilation().syntaxError(checkArgCount);
        }
        Expression[] expressionArr2 = new Expression[i2];
        System.arraycopy(expressionArr, 0, expressionArr2, 0, i);
        expressionArr2[i] = getCollator(expressionArr, i);
        return new ApplyExp(expression, expressionArr2);
    }

    /* access modifiers changed from: package-private */
    public Expression withContext(Method method, Expression[] expressionArr, String str, int i) {
        int i2 = i + 1;
        String checkArgCount = WrongArguments.checkArgCount(str, i, i2, expressionArr.length);
        if (checkArgCount != null) {
            return getCompilation().syntaxError(checkArgCount);
        }
        if (expressionArr.length == i) {
            Expression[] expressionArr2 = new Expression[i2];
            System.arraycopy(expressionArr, 0, expressionArr2, 0, i);
            Declaration lookup = this.lookup.lookup((Object) XQParser.DOT_VARNAME, false);
            if (lookup == null) {
                String str2 = "undefined context for " + str;
                this.messages.error('e', str2, "XPDY0002");
                return new ErrorExp(str2);
            }
            expressionArr2[i] = new ReferenceExp(lookup);
            expressionArr = expressionArr2;
        }
        return new ApplyExp(method, expressionArr);
    }

    private Expression checkArgCount(Expression[] expressionArr, Declaration declaration, int i, int i2) {
        String checkArgCount = WrongArguments.checkArgCount("fn:" + declaration.getName(), i, i2, expressionArr.length);
        if (checkArgCount == null) {
            return null;
        }
        return getCompilation().syntaxError(checkArgCount);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0411  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0414  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0433  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0445  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0457  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression visitApplyExp(gnu.expr.ApplyExp r18, java.lang.Void r19) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            gnu.expr.Expression r3 = r18.getFunction()
            gnu.xquery.lang.XQParser r4 = r1.parser
            gnu.xml.NamespaceBinding r4 = r4.constructorNamespaces
            java.lang.Object r5 = r18.getFunctionValue()
            boolean r6 = r5 instanceof gnu.kawa.xml.MakeElement
            if (r6 == 0) goto L_0x0027
            gnu.kawa.xml.MakeElement r5 = (gnu.kawa.xml.MakeElement) r5
            gnu.xml.NamespaceBinding r6 = r5.getNamespaceNodes()
            gnu.xml.NamespaceBinding r6 = gnu.xml.NamespaceBinding.nconc(r6, r4)
            r5.setNamespaceNodes(r6)
            gnu.xquery.lang.XQParser r5 = r1.parser
            r5.constructorNamespaces = r6
        L_0x0027:
            boolean r5 = r3 instanceof gnu.expr.ReferenceExp
            if (r5 == 0) goto L_0x0032
            gnu.expr.ReferenceExp r3 = (gnu.expr.ReferenceExp) r3
            gnu.expr.Expression r3 = r1.visitReferenceExp((gnu.expr.ReferenceExp) r3, (gnu.expr.ApplyExp) r0)
            goto L_0x0038
        L_0x0032:
            java.lang.Object r3 = r1.visit(r3, r2)
            gnu.expr.Expression r3 = (gnu.expr.Expression) r3
        L_0x0038:
            r0.setFunction(r3)
            gnu.expr.Expression[] r3 = r18.getArgs()
            r1.visitExps(r3, r2)
            gnu.xquery.lang.XQParser r3 = r1.parser
            r3.constructorNamespaces = r4
            gnu.expr.Expression r3 = r18.getFunction()
            boolean r4 = r3 instanceof gnu.expr.ReferenceExp
            java.lang.String r5 = "castAs"
            java.lang.String r6 = "gnu.xquery.util.CastAs"
            r8 = 101(0x65, float:1.42E-43)
            r9 = 2
            r10 = 0
            r11 = 1
            if (r4 == 0) goto L_0x04f9
            gnu.expr.ReferenceExp r3 = (gnu.expr.ReferenceExp) r3
            gnu.expr.Declaration r3 = r3.getBinding()
            if (r3 == 0) goto L_0x04f9
            int r4 = r3.getCode()
            if (r4 >= 0) goto L_0x04f9
            java.lang.String r12 = "gnu.xquery.util.StringUtils"
            java.lang.String r13 = "gnu.xquery.util.SequenceUtils"
            java.lang.String r14 = "gnu.xquery.util.MinMax"
            java.lang.String r15 = "gnu.xquery.util.QNameUtils"
            r7 = 3
            java.lang.String r16 = "gnu.xquery.util.NodeUtils"
            switch(r4) {
                case -36: goto L_0x0489;
                case -35: goto L_0x0489;
                case -34: goto L_0x03e1;
                case -33: goto L_0x03e1;
                case -32: goto L_0x03cc;
                case -31: goto L_0x03b7;
                case -30: goto L_0x03a2;
                case -29: goto L_0x0385;
                case -28: goto L_0x036e;
                case -27: goto L_0x0359;
                case -26: goto L_0x0344;
                case -25: goto L_0x032f;
                case -24: goto L_0x031a;
                case -23: goto L_0x0305;
                case -22: goto L_0x0073;
                case -21: goto L_0x0073;
                case -20: goto L_0x0073;
                case -19: goto L_0x0073;
                case -18: goto L_0x02f7;
                case -17: goto L_0x02e2;
                case -16: goto L_0x02cb;
                case -15: goto L_0x02b5;
                case -14: goto L_0x02a5;
                case -13: goto L_0x0220;
                case -12: goto L_0x01f1;
                case -11: goto L_0x01dc;
                case -10: goto L_0x0186;
                case -9: goto L_0x0186;
                case -8: goto L_0x0154;
                case -7: goto L_0x013f;
                case -6: goto L_0x012a;
                case -5: goto L_0x0113;
                case -4: goto L_0x00fe;
                case -3: goto L_0x00a7;
                case -2: goto L_0x0075;
                case -1: goto L_0x0075;
                default: goto L_0x0073;
            }
        L_0x0073:
            goto L_0x04f9
        L_0x0075:
            r0 = -1
            if (r4 != r0) goto L_0x007b
            gnu.mapping.Symbol r0 = gnu.xquery.lang.XQParser.LAST_VARNAME
            goto L_0x007d
        L_0x007b:
            gnu.mapping.Symbol r0 = gnu.xquery.lang.XQParser.POSITION_VARNAME
        L_0x007d:
            gnu.expr.NameLookup r2 = r1.lookup
            gnu.expr.Declaration r2 = r2.lookup((java.lang.Object) r0, (boolean) r10)
            if (r2 != 0) goto L_0x009e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "undefined context for "
            r3.append(r4)
            java.lang.String r4 = r0.getName()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.error(r8, r3)
            goto L_0x00a1
        L_0x009e:
            r2.setCanRead(r11)
        L_0x00a1:
            gnu.expr.ReferenceExp r3 = new gnu.expr.ReferenceExp
            r3.<init>(r0, r2)
            return r3
        L_0x00a7:
            gnu.expr.Compilation r2 = r17.getCompilation()
            gnu.expr.Expression[] r0 = r18.getArgs()
            r3 = 0
        L_0x00b0:
            int r4 = r0.length
            int r4 = r4 - r11
            if (r3 >= r4) goto L_0x00e5
            r4 = r0[r3]
            gnu.expr.QuoteExp r4 = (gnu.expr.QuoteExp) r4
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            gnu.xquery.lang.XQParser r5 = r1.parser
            gnu.mapping.Symbol r4 = r5.namespaceResolve(r4, r10)
            if (r4 != 0) goto L_0x00c7
            goto L_0x00e2
        L_0x00c7:
            java.lang.String r5 = r4.getNamespaceURI()
            int r5 = r5.length()
            if (r5 != 0) goto L_0x00d7
            java.lang.String r4 = "pragma name cannot be in the empty namespace"
            r2.error(r8, r4)
            goto L_0x00e2
        L_0x00d7:
            int r5 = r3 + 1
            r5 = r0[r5]
            gnu.expr.Expression r4 = r1.checkPragma(r4, r5)
            if (r4 == 0) goto L_0x00e2
            return r4
        L_0x00e2:
            int r3 = r3 + 2
            goto L_0x00b0
        L_0x00e5:
            int r2 = r0.length
            if (r3 >= r2) goto L_0x00ed
            int r2 = r0.length
            int r2 = r2 - r11
            r0 = r0[r2]
            return r0
        L_0x00ed:
            java.lang.String r0 = "no recognized pragma or default in extension expression"
            gnu.text.SourceMessages r2 = r17.getMessages()
            java.lang.String r3 = "XQST0079"
            r2.error((char) r8, (java.lang.String) r0, (java.lang.String) r3)
            gnu.expr.ErrorExp r2 = new gnu.expr.ErrorExp
            r2.<init>(r0)
            return r2
        L_0x00fe:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r12)
            java.lang.String r3 = "compare"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r7)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:compare"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r9)
            return r0
        L_0x0113:
            java.lang.String r2 = "gnu.xquery.util.DistinctValues"
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r2)
            java.lang.String r3 = "distinctValues$X"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r7)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:distinct-values"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r11)
            return r0
        L_0x012a:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "localName"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:local-name"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x013f:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "namespaceURI"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:namespace-uri"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x0154:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r4 = "collection"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r4, (int) r9)
            gnu.expr.Expression r3 = r1.checkArgCount(r0, r3, r10, r11)
            if (r3 == 0) goto L_0x0169
            return r3
        L_0x0169:
            gnu.expr.Expression r3 = r17.getBaseUriExpr()
            int r4 = r0.length
            if (r4 <= 0) goto L_0x0173
            r0 = r0[r10]
            goto L_0x0175
        L_0x0173:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.voidExp
        L_0x0175:
            gnu.expr.ApplyExp r4 = new gnu.expr.ApplyExp
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r9]
            r5[r10] = r0
            r5[r11] = r3
            r4.<init>((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r5)
            gnu.kawa.xml.NodeType r0 = gnu.kawa.xml.NodeType.documentNodeTest
            r4.setType(r0)
            return r4
        L_0x0186:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            r5 = -9
            if (r4 != r5) goto L_0x01b0
            boolean r6 = gnu.xquery.lang.XQParser.warnOldVersion
            if (r6 == 0) goto L_0x01ad
            java.lang.String r6 = r3.getName()
            java.lang.String r7 = "document"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x01ad
            gnu.expr.Compilation r6 = r17.getCompilation()
            r7 = 119(0x77, float:1.67E-43)
            java.lang.String r8 = "replace 'document' by 'doc'"
            r6.error(r7, r8)
        L_0x01ad:
            java.lang.String r6 = "docCached"
            goto L_0x01b2
        L_0x01b0:
            java.lang.String r6 = "availableCached"
        L_0x01b2:
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r6, (int) r9)
            gnu.expr.Expression r3 = r1.checkArgCount(r0, r3, r11, r11)
            if (r3 == 0) goto L_0x01bd
            return r3
        L_0x01bd:
            gnu.expr.Expression r3 = r17.getBaseUriExpr()
            gnu.expr.ApplyExp r6 = new gnu.expr.ApplyExp
            gnu.expr.Expression[] r7 = new gnu.expr.Expression[r9]
            r0 = r0[r10]
            r7[r10] = r0
            r7[r11] = r3
            r6.<init>((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r7)
            if (r4 != r5) goto L_0x01d6
            gnu.kawa.xml.NodeType r0 = gnu.kawa.xml.NodeType.documentNodeTest
            r6.setType(r0)
            goto L_0x01db
        L_0x01d6:
            gnu.kawa.xml.XDataType r0 = gnu.kawa.xml.XDataType.booleanType
            r6.setType(r0)
        L_0x01db:
            return r6
        L_0x01dc:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "baseUri"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:base-uri"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x01f1:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r2 = r1.checkArgCount(r0, r3, r11, r9)
            if (r2 == 0) goto L_0x01fc
            return r2
        L_0x01fc:
            gnu.expr.Expression[] r2 = new gnu.expr.Expression[r9]
            r3 = r0[r10]
            r2[r10] = r3
            int r3 = r0.length
            if (r3 != r11) goto L_0x020c
            gnu.expr.Expression r0 = r17.getBaseUriExpr()
            r2[r11] = r0
            goto L_0x0210
        L_0x020c:
            r0 = r0[r11]
            r2[r11] = r0
        L_0x0210:
            gnu.bytecode.ClassType r0 = gnu.bytecode.ClassType.make(r15)
            java.lang.String r3 = "resolveURI"
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r3, (int) r9)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            r3.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r2)
            return r3
        L_0x0220:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r2 = r1.checkArgCount(r0, r3, r11, r11)
            if (r2 == 0) goto L_0x022b
            return r2
        L_0x022b:
            r0 = r0[r10]
            boolean r2 = r0 instanceof gnu.expr.QuoteExp
            if (r2 == 0) goto L_0x0272
            gnu.expr.QuoteExp r0 = (gnu.expr.QuoteExp) r0
            java.lang.Object r0 = r0.getValue()
            if (r0 != 0) goto L_0x023b
            r7 = 0
            goto L_0x023f
        L_0x023b:
            java.lang.String r7 = r0.toString()
        L_0x023f:
            gnu.xquery.lang.XQParser r0 = r1.parser
            gnu.xml.NamespaceBinding r0 = r0.constructorNamespaces
            gnu.xquery.lang.XQParser r2 = r1.parser
            gnu.xml.NamespaceBinding r2 = r2.prologNamespaces
            java.lang.String r0 = gnu.xquery.util.QNameUtils.lookupPrefix(r7, r0, r2)
            if (r0 != 0) goto L_0x026c
            gnu.expr.Compilation r0 = r17.getCompilation()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unknown namespace prefix '"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r3 = "'"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            gnu.expr.Expression r0 = r0.syntaxError(r2)
            return r0
        L_0x026c:
            gnu.expr.QuoteExp r2 = new gnu.expr.QuoteExp
            r2.<init>(r0)
            return r2
        L_0x0272:
            gnu.expr.Expression[] r2 = new gnu.expr.Expression[r7]
            r2[r10] = r0
            gnu.expr.QuoteExp r0 = new gnu.expr.QuoteExp
            gnu.xquery.lang.XQParser r3 = r1.parser
            gnu.xml.NamespaceBinding r3 = r3.constructorNamespaces
            r0.<init>(r3)
            r2[r11] = r0
            gnu.expr.QuoteExp r0 = new gnu.expr.QuoteExp
            gnu.xquery.lang.XQParser r3 = r1.parser
            gnu.xml.NamespaceBinding r3 = r3.prologNamespaces
            r0.<init>(r3)
            r2[r9] = r0
            gnu.expr.PrimProcedure r0 = new gnu.expr.PrimProcedure
            gnu.bytecode.ClassType r3 = gnu.bytecode.ClassType.make(r15)
            java.lang.String r4 = "resolvePrefix"
            gnu.bytecode.Method r3 = r3.getDeclaredMethod((java.lang.String) r4, (int) r7)
            r0.<init>(r3)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            r3.<init>((gnu.mapping.Procedure) r0, (gnu.expr.Expression[]) r2)
            r2 = 4
            r3.setFlag(r2)
            return r3
        L_0x02a5:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r0 = r1.checkArgCount(r0, r3, r10, r10)
            if (r0 == 0) goto L_0x02b0
            return r0
        L_0x02b0:
            gnu.expr.Expression r0 = r17.getBaseUriExpr()
            return r0
        L_0x02b5:
            r2 = 4
            gnu.bytecode.ClassType r3 = gnu.bytecode.ClassType.make(r13)
            java.lang.String r4 = "indexOf$X"
            gnu.bytecode.Method r2 = r3.getDeclaredMethod((java.lang.String) r4, (int) r2)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:index-of"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r9)
            return r0
        L_0x02cb:
            java.lang.String r2 = "gnu.xml.TextUtils"
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r2)
            java.lang.String r3 = "asString"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:string"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x02e2:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r12)
            java.lang.String r3 = "normalizeSpace"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:normalize-space"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x02f7:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r2 = r1.checkArgCount(r0, r3, r11, r11)
            if (r2 == 0) goto L_0x0302
            return r2
        L_0x0302:
            r0 = r0[r10]
            return r0
        L_0x0305:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "lang"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r9)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:lang"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r11)
            return r0
        L_0x031a:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "name"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:name"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x032f:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r13)
            java.lang.String r3 = "deepEqual"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r7)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:deep-equal"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r9)
            return r0
        L_0x0344:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r14)
            java.lang.String r3 = "min"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r9)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:min"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r11)
            return r0
        L_0x0359:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r14)
            java.lang.String r3 = "max"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r9)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:max"
            gnu.expr.Expression r0 = r1.withCollator((gnu.bytecode.Method) r2, (gnu.expr.Expression[]) r0, (java.lang.String) r3, (int) r11)
            return r0
        L_0x036e:
            java.lang.String r2 = "gnu.xquery.util.NumberValue"
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r2)
            java.lang.String r3 = "numberValue"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:number"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x0385:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r0 = r1.checkArgCount(r0, r3, r10, r10)
            if (r0 == 0) goto L_0x0390
            return r0
        L_0x0390:
            gnu.xquery.lang.XQParser r0 = r1.parser
            gnu.xquery.util.NamedCollator r0 = r0.defaultCollator
            if (r0 == 0) goto L_0x039b
            java.lang.String r0 = r0.getName()
            goto L_0x039d
        L_0x039b:
            java.lang.String r0 = "http://www.w3.org/2005/xpath-functions/collation/codepoint"
        L_0x039d:
            gnu.expr.QuoteExp r0 = gnu.expr.QuoteExp.getInstance(r0)
            return r0
        L_0x03a2:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "id$X"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r7)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:id"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r11)
            return r0
        L_0x03b7:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "idref"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r9)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:idref"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r11)
            return r0
        L_0x03cc:
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r16)
            java.lang.String r3 = "root"
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r11)
            gnu.expr.Expression[] r0 = r18.getArgs()
            java.lang.String r3 = "fn:root"
            gnu.expr.Expression r0 = r1.withContext(r2, r0, r3, r10)
            return r0
        L_0x03e1:
            gnu.expr.Expression[] r3 = r18.getArgs()
            r7 = -33
            if (r4 != r7) goto L_0x03eb
            r9 = 0
            goto L_0x03ec
        L_0x03eb:
            r9 = 1
        L_0x03ec:
            r9 = r3[r9]
            boolean r12 = r9 instanceof gnu.expr.ApplyExp
            if (r12 == 0) goto L_0x0406
            r13 = r9
            gnu.expr.ApplyExp r13 = (gnu.expr.ApplyExp) r13
            gnu.expr.Expression r14 = r13.getFunction()
            java.lang.Object r14 = r14.valueIfConstant()
            gnu.expr.PrimProcedure r15 = gnu.xquery.lang.XQParser.proc_OccurrenceType_getInstance
            if (r14 != r15) goto L_0x0406
            gnu.expr.Expression r13 = r13.getArg(r10)
            goto L_0x0407
        L_0x0406:
            r13 = r9
        L_0x0407:
            java.lang.Object r13 = r13.valueIfConstant()
            gnu.kawa.reflect.SingletonType r14 = gnu.kawa.reflect.SingletonType.getInstance()
            if (r13 != r14) goto L_0x0414
            java.lang.String r14 = "type to 'cast as' or 'castable as' must be atomic"
            goto L_0x0431
        L_0x0414:
            gnu.kawa.xml.XDataType r14 = gnu.kawa.xml.XDataType.anyAtomicType
            if (r13 != r14) goto L_0x041b
            java.lang.String r14 = "type to 'cast as' or 'castable as' cannot be anyAtomicType"
            goto L_0x0431
        L_0x041b:
            gnu.kawa.xml.XDataType r14 = gnu.kawa.xml.XDataType.anySimpleType
            if (r13 != r14) goto L_0x0422
            java.lang.String r14 = "type to 'cast as' or 'castable as' cannot be anySimpleType"
            goto L_0x0431
        L_0x0422:
            gnu.kawa.xml.XDataType r14 = gnu.kawa.xml.XDataType.untypedType
            if (r13 != r14) goto L_0x0429
            java.lang.String r14 = "type to 'cast as' or 'castable as' cannot be untyped"
            goto L_0x0431
        L_0x0429:
            gnu.kawa.xml.XDataType r14 = gnu.kawa.xml.XDataType.NotationType
            if (r13 != r14) goto L_0x0430
            java.lang.String r14 = "type to 'cast as' or 'castable as' cannot be NOTATION"
            goto L_0x0431
        L_0x0430:
            r14 = 0
        L_0x0431:
            if (r14 == 0) goto L_0x043a
            gnu.text.SourceMessages r15 = r1.messages
            java.lang.String r10 = "XPST0080"
            r15.error(r8, r9, r14, r10)
        L_0x043a:
            gnu.bytecode.ClassType r8 = gnu.expr.Compilation.typeSymbol
            if (r13 != r8) goto L_0x0442
            if (r12 != 0) goto L_0x0442
            r8 = 1
            goto L_0x0443
        L_0x0442:
            r8 = 0
        L_0x0443:
            if (r4 != r7) goto L_0x0457
            if (r8 == 0) goto L_0x0452
            r0 = r3[r11]
            gnu.expr.ApplyExp r0 = gnu.xquery.lang.XQParser.castQName(r0, r11)
            gnu.expr.Expression r0 = r1.visitApplyExp((gnu.expr.ApplyExp) r0, (java.lang.Void) r2)
            return r0
        L_0x0452:
            gnu.expr.Expression r2 = gnu.xquery.lang.XQParser.makeFunctionExp(r6, r5)
            goto L_0x047f
        L_0x0457:
            if (r8 == 0) goto L_0x0477
            r2 = 0
            r2 = r3[r2]
            boolean r4 = r2 instanceof gnu.expr.QuoteExp
            if (r4 == 0) goto L_0x0477
            gnu.expr.QuoteExp r2 = (gnu.expr.QuoteExp) r2
            java.lang.Object r0 = r2.getValue()
            gnu.xquery.lang.XQParser r2 = r1.parser     // Catch:{ RuntimeException -> 0x0474 }
            gnu.xml.NamespaceBinding r2 = r2.constructorNamespaces     // Catch:{ RuntimeException -> 0x0474 }
            gnu.xquery.lang.XQParser r3 = r1.parser     // Catch:{ RuntimeException -> 0x0474 }
            gnu.xml.NamespaceBinding r3 = r3.prologNamespaces     // Catch:{ RuntimeException -> 0x0474 }
            gnu.xquery.util.QNameUtils.resolveQName(r0, r2, r3)     // Catch:{ RuntimeException -> 0x0474 }
            gnu.expr.QuoteExp r0 = gnu.xquery.lang.XQuery.trueExp     // Catch:{ RuntimeException -> 0x0474 }
            return r0
        L_0x0474:
            gnu.expr.QuoteExp r0 = gnu.xquery.lang.XQuery.falseExp
            return r0
        L_0x0477:
            java.lang.String r2 = "gnu.xquery.lang.XQParser"
            java.lang.String r4 = "castableAs"
            gnu.expr.Expression r2 = gnu.xquery.lang.XQParser.makeFunctionExp(r2, r4)
        L_0x047f:
            gnu.expr.ApplyExp r4 = new gnu.expr.ApplyExp
            r4.<init>((gnu.expr.Expression) r2, (gnu.expr.Expression[]) r3)
            gnu.expr.Expression r0 = r4.setLine((gnu.expr.Expression) r0)
            return r0
        L_0x0489:
            gnu.expr.Expression[] r0 = r18.getArgs()
            gnu.expr.Expression r2 = r1.checkArgCount(r0, r3, r11, r11)
            if (r2 == 0) goto L_0x0494
            return r2
        L_0x0494:
            gnu.xquery.lang.XQParser r2 = r1.parser
            gnu.xml.NamespaceBinding r2 = r2.constructorNamespaces
            r3 = -36
            if (r4 != r3) goto L_0x04a5
            gnu.xml.NamespaceBinding r3 = new gnu.xml.NamespaceBinding
            java.lang.String r4 = ""
            r10 = 0
            r3.<init>(r10, r4, r2)
            r2 = r3
        L_0x04a5:
            r3 = 0
            r0 = r0[r3]
            boolean r3 = r0 instanceof gnu.expr.QuoteExp
            if (r3 == 0) goto L_0x04ce
            gnu.expr.QuoteExp r0 = (gnu.expr.QuoteExp) r0     // Catch:{ RuntimeException -> 0x04c0 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ RuntimeException -> 0x04c0 }
            gnu.xquery.lang.XQParser r3 = r1.parser     // Catch:{ RuntimeException -> 0x04c0 }
            gnu.xml.NamespaceBinding r3 = r3.prologNamespaces     // Catch:{ RuntimeException -> 0x04c0 }
            java.lang.Object r0 = gnu.xquery.util.QNameUtils.resolveQName(r0, r2, r3)     // Catch:{ RuntimeException -> 0x04c0 }
            gnu.expr.QuoteExp r2 = new gnu.expr.QuoteExp     // Catch:{ RuntimeException -> 0x04c0 }
            r2.<init>(r0)     // Catch:{ RuntimeException -> 0x04c0 }
            return r2
        L_0x04c0:
            r0 = move-exception
            gnu.expr.Compilation r2 = r17.getCompilation()
            java.lang.String r0 = r0.getMessage()
            gnu.expr.Expression r0 = r2.syntaxError(r0)
            return r0
        L_0x04ce:
            gnu.expr.Expression[] r3 = new gnu.expr.Expression[r7]
            r4 = 0
            r3[r4] = r0
            gnu.expr.QuoteExp r0 = new gnu.expr.QuoteExp
            r0.<init>(r2)
            r3[r11] = r0
            gnu.expr.QuoteExp r0 = new gnu.expr.QuoteExp
            gnu.xquery.lang.XQParser r2 = r1.parser
            gnu.xml.NamespaceBinding r2 = r2.prologNamespaces
            r0.<init>(r2)
            r3[r9] = r0
            gnu.bytecode.ClassType r0 = gnu.bytecode.ClassType.make(r15)
            java.lang.String r2 = "resolveQName"
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r2, (int) r7)
            gnu.expr.ApplyExp r2 = new gnu.expr.ApplyExp
            r2.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r3)
            r0 = 4
            r2.setFlag(r0)
            return r2
        L_0x04f9:
            r10 = 0
            java.lang.Object r2 = r18.getFunctionValue()
            boolean r3 = r2 instanceof gnu.bytecode.Type
            if (r3 == 0) goto L_0x0528
            gnu.expr.Expression[] r2 = r18.getArgs()
            int r3 = r2.length
            if (r3 == r11) goto L_0x0511
            gnu.text.SourceMessages r2 = r1.messages
            java.lang.String r3 = "type constructor requires a single argument"
            r2.error(r8, r3)
            return r0
        L_0x0511:
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            gnu.expr.Expression r4 = gnu.xquery.lang.XQParser.makeFunctionExp(r6, r5)
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r9]
            gnu.expr.Expression r0 = r18.getFunction()
            r6 = 0
            r5[r6] = r0
            r0 = r2[r6]
            r5[r11] = r0
            r3.<init>((gnu.expr.Expression) r4, (gnu.expr.Expression[]) r5)
            return r3
        L_0x0528:
            boolean r3 = r2 instanceof gnu.kawa.xml.MakeElement
            if (r3 == 0) goto L_0x05a6
            gnu.kawa.xml.MakeElement r2 = (gnu.kawa.xml.MakeElement) r2
            gnu.xml.NamespaceBinding r3 = r2.getNamespaceNodes()
            gnu.mapping.Symbol r4 = r2.tag
            if (r4 != 0) goto L_0x053a
            gnu.mapping.Symbol r4 = gnu.kawa.xml.MakeElement.getTagName(r18)
        L_0x053a:
            r5 = r4
            r4 = 0
            gnu.xml.NamespaceBinding r3 = maybeAddNamespace(r5, r4, r3)
            gnu.expr.Expression[] r5 = r18.getArgs()
            int r6 = r5.length
            gnu.mapping.Symbol[] r6 = new gnu.mapping.Symbol[r6]
            r9 = r3
            r3 = 0
            r7 = 0
        L_0x054a:
            int r12 = r5.length
            if (r3 >= r12) goto L_0x05a1
            r12 = r5[r3]
            boolean r13 = r12 instanceof gnu.expr.ApplyExp
            if (r13 == 0) goto L_0x059c
            gnu.expr.ApplyExp r12 = (gnu.expr.ApplyExp) r12
            gnu.expr.Expression r13 = r12.getFunction()
            gnu.expr.QuoteExp r14 = gnu.kawa.xml.MakeAttribute.makeAttributeExp
            if (r13 != r14) goto L_0x059c
            gnu.mapping.Symbol r13 = gnu.kawa.xml.MakeElement.getTagName(r12)
            if (r13 == 0) goto L_0x059c
            r14 = 0
        L_0x0564:
            if (r14 != r7) goto L_0x0571
            int r12 = r7 + 1
            r6[r7] = r13
            gnu.xml.NamespaceBinding r7 = maybeAddNamespace(r13, r11, r9)
            r9 = r7
            r7 = r12
            goto L_0x059c
        L_0x0571:
            r15 = r6[r14]
            boolean r15 = r13.equals(r15)
            if (r15 == 0) goto L_0x0597
            gnu.expr.Compilation r15 = r17.getCompilation()
            r15.setLine((gnu.expr.Expression) r12)
            gnu.mapping.Symbol r15 = gnu.kawa.xml.MakeElement.getTagName(r18)
            if (r15 != 0) goto L_0x0588
            r15 = r10
            goto L_0x058c
        L_0x0588:
            java.lang.String r15 = r15.toString()
        L_0x058c:
            gnu.text.SourceMessages r4 = r1.messages
            java.lang.String r15 = gnu.xml.XMLFilter.duplicateAttributeMessage(r13, r15)
            java.lang.String r10 = "XQST0040"
            r4.error((char) r8, (java.lang.String) r15, (java.lang.String) r10)
        L_0x0597:
            int r14 = r14 + 1
            r4 = 0
            r10 = 0
            goto L_0x0564
        L_0x059c:
            int r3 = r3 + 1
            r4 = 0
            r10 = 0
            goto L_0x054a
        L_0x05a1:
            if (r9 == 0) goto L_0x05a6
            r2.setNamespaceNodes(r9)
        L_0x05a6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.lang.XQResolveNames.visitApplyExp(gnu.expr.ApplyExp, java.lang.Void):gnu.expr.Expression");
    }

    /* access modifiers changed from: package-private */
    public Expression getBaseUriExpr() {
        Compilation compilation = getCompilation();
        String staticBaseUri = this.parser.getStaticBaseUri();
        if (staticBaseUri != null) {
            return QuoteExp.getInstance(staticBaseUri);
        }
        return GetModuleClass.getModuleClassURI(compilation);
    }

    static NamespaceBinding maybeAddNamespace(Symbol symbol, boolean z, NamespaceBinding namespaceBinding) {
        if (symbol == null) {
            return namespaceBinding;
        }
        String prefix = symbol.getPrefix();
        String namespaceURI = symbol.getNamespaceURI();
        if (prefix == "") {
            prefix = null;
        }
        if (namespaceURI == "") {
            namespaceURI = null;
        }
        if (z && prefix == null && namespaceURI == null) {
            return namespaceBinding;
        }
        return NamespaceBinding.maybeAdd(prefix, namespaceURI, namespaceBinding);
    }

    static Declaration procToDecl(Object obj, Object obj2) {
        Declaration declaration = new Declaration(obj);
        declaration.setProcedureDecl(true);
        declaration.noteValue(new QuoteExp(obj2));
        declaration.setFlag(16384);
        return declaration;
    }
}
