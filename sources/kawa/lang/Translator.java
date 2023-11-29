package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.Special;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.xml.NamespaceBinding;
import java.util.Stack;
import java.util.Vector;
import kawa.standard.begin;
import kawa.standard.require;

public class Translator extends Compilation {
    private static Expression errorExp = new ErrorExp("unknown syntax error");
    public static final Declaration getNamedPartDecl;
    public LambdaExp curMethodLambda;
    public Macro currentMacroDefinition;
    Syntax currentSyntax;
    private Environment env = Environment.getCurrent();
    public int firstForm;
    public Stack formStack = new Stack();
    Declaration macroContext;
    public Declaration matchArray;
    Vector notedAccess;
    public PatternScope patternScope;
    public Object pendingForm;
    PairWithPosition positionPair;
    Stack renamedAliasStack;
    public Declaration templateScopeDecl;
    public NamespaceBinding xmlElementNamespaces = NamespaceBinding.predefinedXML;

    static {
        Declaration declarationFromStatic = Declaration.getDeclarationFromStatic("gnu.kawa.functions.GetNamedPart", "getNamedPart");
        getNamedPartDecl = declarationFromStatic;
        LispLanguage.getNamedPartLocation.setDeclaration(declarationFromStatic);
    }

    public Translator(Language language, SourceMessages sourceMessages, NameLookup nameLookup) {
        super(language, sourceMessages, nameLookup);
    }

    public final Environment getGlobalEnvironment() {
        return this.env;
    }

    public Expression parse(Object obj) {
        return rewrite(obj);
    }

    /* JADX INFO: finally extract failed */
    public final Expression rewrite_car(Pair pair, SyntaxForm syntaxForm) {
        if (syntaxForm == null || syntaxForm.getScope() == this.current_scope || (pair.getCar() instanceof SyntaxForm)) {
            return rewrite_car(pair, false);
        }
        ScopeExp scopeExp = this.current_scope;
        try {
            setCurrentScope(syntaxForm.getScope());
            Expression rewrite_car = rewrite_car(pair, false);
            setCurrentScope(scopeExp);
            return rewrite_car;
        } catch (Throwable th) {
            setCurrentScope(scopeExp);
            throw th;
        }
    }

    public final Expression rewrite_car(Pair pair, boolean z) {
        Object car = pair.getCar();
        if (pair instanceof PairWithPosition) {
            return rewrite_with_position(car, z, (PairWithPosition) pair);
        }
        return rewrite(car, z);
    }

    public Syntax getCurrentSyntax() {
        return this.currentSyntax;
    }

    /* access modifiers changed from: package-private */
    public Expression apply_rewrite(Syntax syntax, Pair pair) {
        Syntax syntax2 = this.currentSyntax;
        this.currentSyntax = syntax;
        try {
            return syntax.rewriteForm(pair, this);
        } finally {
            this.currentSyntax = syntax2;
        }
    }

    static ReferenceExp getOriginalRef(Declaration declaration) {
        if (declaration == null || !declaration.isAlias() || declaration.isIndirectBinding()) {
            return null;
        }
        Expression value = declaration.getValue();
        if (value instanceof ReferenceExp) {
            return (ReferenceExp) value;
        }
        return null;
    }

    public final boolean selfEvaluatingSymbol(Object obj) {
        return ((LispLanguage) getLanguage()).selfEvaluatingSymbol(obj);
    }

    public final boolean matches(Object obj, String str) {
        return matches(obj, (SyntaxForm) null, str);
    }

    public boolean matches(Object obj, SyntaxForm syntaxForm, String str) {
        ReferenceExp originalRef;
        if (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if ((obj instanceof SimpleSymbol) && !selfEvaluatingSymbol(obj) && (originalRef = getOriginalRef(this.lexical.lookup(obj, -1))) != null) {
            obj = originalRef.getSymbol();
        }
        return (obj instanceof SimpleSymbol) && ((Symbol) obj).getLocalPart() == str;
    }

    public boolean matches(Object obj, SyntaxForm syntaxForm, Symbol symbol) {
        ReferenceExp originalRef;
        if (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if ((obj instanceof SimpleSymbol) && !selfEvaluatingSymbol(obj) && (originalRef = getOriginalRef(this.lexical.lookup(obj, -1))) != null) {
            obj = originalRef.getSymbol();
        }
        return obj == symbol;
    }

    public Object matchQuoted(Pair pair) {
        if (!matches(pair.getCar(), LispLanguage.quote_sym) || !(pair.getCdr() instanceof Pair)) {
            return null;
        }
        Pair pair2 = (Pair) pair.getCdr();
        if (pair2.getCdr() == LList.Empty) {
            return pair2.getCar();
        }
        return null;
    }

    public Declaration lookup(Object obj, int i) {
        Declaration lookup = this.lexical.lookup(obj, i);
        if (lookup == null || !getLanguage().hasNamespace(lookup, i)) {
            return currentModule().lookup(obj, getLanguage(), i);
        }
        return lookup;
    }

    public Declaration lookupGlobal(Object obj) {
        return lookupGlobal(obj, -1);
    }

    public Declaration lookupGlobal(Object obj, int i) {
        ModuleExp currentModule = currentModule();
        Declaration lookup = currentModule.lookup(obj, getLanguage(), i);
        if (lookup != null) {
            return lookup;
        }
        Declaration noDefine = currentModule.getNoDefine(obj);
        noDefine.setIndirectBinding(true);
        return noDefine;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kawa.lang.Syntax check_if_Syntax(gnu.expr.Declaration r6) {
        /*
            r5 = this;
            gnu.expr.Declaration r0 = gnu.expr.Declaration.followAliases(r6)
            gnu.expr.Expression r1 = r0.getValue()
            r2 = 32768(0x8000, double:1.61895E-319)
            r4 = 0
            if (r1 == 0) goto L_0x006e
            boolean r0 = r0.getFlag(r2)
            if (r0 == 0) goto L_0x006e
            gnu.expr.Expression r0 = r6.getValue()     // Catch:{ all -> 0x004f }
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x003a
            gnu.expr.Expression r0 = r6.getValue()     // Catch:{ all -> 0x004f }
            gnu.expr.ReferenceExp r0 = (gnu.expr.ReferenceExp) r0     // Catch:{ all -> 0x004f }
            gnu.expr.Declaration r0 = r0.contextDecl()     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x002b
            r5.macroContext = r0     // Catch:{ all -> 0x004f }
            goto L_0x0048
        L_0x002b:
            gnu.expr.ScopeExp r0 = r5.current_scope     // Catch:{ all -> 0x004f }
            boolean r0 = r0 instanceof kawa.lang.TemplateScope     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x0048
            gnu.expr.ScopeExp r0 = r5.current_scope     // Catch:{ all -> 0x004f }
            kawa.lang.TemplateScope r0 = (kawa.lang.TemplateScope) r0     // Catch:{ all -> 0x004f }
            gnu.expr.Declaration r0 = r0.macroContext     // Catch:{ all -> 0x004f }
            r5.macroContext = r0     // Catch:{ all -> 0x004f }
            goto L_0x0048
        L_0x003a:
            gnu.expr.ScopeExp r0 = r5.current_scope     // Catch:{ all -> 0x004f }
            boolean r0 = r0 instanceof kawa.lang.TemplateScope     // Catch:{ all -> 0x004f }
            if (r0 == 0) goto L_0x0048
            gnu.expr.ScopeExp r0 = r5.current_scope     // Catch:{ all -> 0x004f }
            kawa.lang.TemplateScope r0 = (kawa.lang.TemplateScope) r0     // Catch:{ all -> 0x004f }
            gnu.expr.Declaration r0 = r0.macroContext     // Catch:{ all -> 0x004f }
            r5.macroContext = r0     // Catch:{ all -> 0x004f }
        L_0x0048:
            gnu.mapping.Environment r0 = r5.env     // Catch:{ all -> 0x004f }
            java.lang.Object r6 = r1.eval((gnu.mapping.Environment) r0)     // Catch:{ all -> 0x004f }
            goto L_0x0084
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()
            r0 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unable to evaluate macro for "
            r1.append(r2)
            java.lang.Object r6 = r6.getSymbol()
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r5.error(r0, r6)
            goto L_0x0083
        L_0x006e:
            boolean r0 = r6.getFlag(r2)
            if (r0 == 0) goto L_0x0083
            boolean r0 = r6.needsContext()
            if (r0 != 0) goto L_0x0083
            gnu.kawa.reflect.StaticFieldLocation r6 = gnu.kawa.reflect.StaticFieldLocation.make(r6)
            java.lang.Object r6 = r6.get(r4)
            goto L_0x0084
        L_0x0083:
            r6 = r4
        L_0x0084:
            boolean r0 = r6 instanceof kawa.lang.Syntax
            if (r0 == 0) goto L_0x008b
            r4 = r6
            kawa.lang.Syntax r4 = (kawa.lang.Syntax) r4
        L_0x008b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Translator.check_if_Syntax(gnu.expr.Declaration):kawa.lang.Syntax");
    }

    public Expression rewrite_pair(Pair pair, boolean z) {
        Symbol symbol;
        Expression rewrite_car = rewrite_car(pair, true);
        if (rewrite_car instanceof QuoteExp) {
            Object valueIfConstant = rewrite_car.valueIfConstant();
            if (valueIfConstant instanceof Syntax) {
                return apply_rewrite((Syntax) valueIfConstant, pair);
            }
        }
        boolean z2 = rewrite_car instanceof ReferenceExp;
        if (z2) {
            ReferenceExp referenceExp = (ReferenceExp) rewrite_car;
            Declaration binding = referenceExp.getBinding();
            if (binding == null) {
                Object symbol2 = referenceExp.getSymbol();
                if (!(symbol2 instanceof Symbol) || selfEvaluatingSymbol(symbol2)) {
                    symbol = this.env.getSymbol(symbol2.toString());
                } else {
                    symbol = (Symbol) symbol2;
                    symbol.getName();
                }
                Object obj = this.env.get(symbol, getLanguage().hasSeparateFunctionNamespace() ? EnvironmentKey.FUNCTION : null, (Object) null);
                if (obj instanceof Syntax) {
                    return apply_rewrite((Syntax) obj, pair);
                }
                if (obj instanceof AutoloadProcedure) {
                    try {
                        ((AutoloadProcedure) obj).getLoaded();
                    } catch (RuntimeException unused) {
                    }
                }
            } else {
                Declaration declaration = this.macroContext;
                Syntax check_if_Syntax = check_if_Syntax(binding);
                if (check_if_Syntax != null) {
                    Expression apply_rewrite = apply_rewrite(check_if_Syntax, pair);
                    this.macroContext = declaration;
                    return apply_rewrite;
                }
            }
            referenceExp.setProcedureName(true);
            if (getLanguage().hasSeparateFunctionNamespace()) {
                rewrite_car.setFlag(8);
            }
        }
        Object cdr = pair.getCdr();
        int listLength = listLength(cdr);
        if (listLength == -1) {
            return syntaxError("circular list is not allowed after " + pair.getCar());
        } else if (listLength < 0) {
            return syntaxError("dotted list [" + cdr + "] is not allowed after " + pair.getCar());
        } else {
            Stack stack = new Stack();
            ScopeExp scopeExp = this.current_scope;
            int i = 0;
            while (i < listLength) {
                if (cdr instanceof SyntaxForm) {
                    SyntaxForm syntaxForm = (SyntaxForm) cdr;
                    Object datum = syntaxForm.getDatum();
                    setCurrentScope(syntaxForm.getScope());
                    cdr = datum;
                }
                Pair pair2 = (Pair) cdr;
                i++;
                stack.addElement(rewrite_car(pair2, false));
                cdr = pair2.getCdr();
            }
            Expression[] expressionArr = new Expression[stack.size()];
            stack.copyInto(expressionArr);
            if (scopeExp != this.current_scope) {
                setCurrentScope(scopeExp);
            }
            if (!z2 || ((ReferenceExp) rewrite_car).getBinding() != getNamedPartDecl) {
                return ((LispLanguage) getLanguage()).makeApply(rewrite_car, expressionArr);
            }
            Expression expression = expressionArr[0];
            Expression expression2 = expressionArr[1];
            Symbol namespaceResolve = namespaceResolve(expression, expression2);
            if (namespaceResolve != null) {
                return rewrite(namespaceResolve, z);
            }
            return CompileNamedPart.makeExp(expression, expression2);
        }
    }

    public Namespace namespaceResolvePrefix(Expression expression) {
        Object obj;
        if (expression instanceof ReferenceExp) {
            ReferenceExp referenceExp = (ReferenceExp) expression;
            Declaration binding = referenceExp.getBinding();
            if (binding == null || binding.getFlag(65536)) {
                Object symbol = referenceExp.getSymbol();
                obj = this.env.get((EnvironmentKey) symbol instanceof Symbol ? (Symbol) symbol : this.env.getSymbol(symbol.toString()), (Object) null);
            } else if (binding.isNamespaceDecl()) {
                obj = binding.getConstantValue();
            } else {
                obj = null;
            }
            if (obj instanceof Namespace) {
                Namespace namespace = (Namespace) obj;
                String name = namespace.getName();
                if (name == null || !name.startsWith("class:")) {
                    return namespace;
                }
                return null;
            }
        }
        return null;
    }

    public Symbol namespaceResolve(Namespace namespace, Expression expression) {
        if (namespace == null || !(expression instanceof QuoteExp)) {
            return null;
        }
        return namespace.getSymbol(((QuoteExp) expression).getValue().toString().intern());
    }

    public Symbol namespaceResolve(Expression expression, Expression expression2) {
        return namespaceResolve(namespaceResolvePrefix(expression), expression2);
    }

    public static Object stripSyntax(Object obj) {
        while (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        return obj;
    }

    public static Object safeCar(Object obj) {
        while (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if (!(obj instanceof Pair)) {
            return null;
        }
        return stripSyntax(((Pair) obj).getCar());
    }

    public static Object safeCdr(Object obj) {
        while (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if (!(obj instanceof Pair)) {
            return null;
        }
        return stripSyntax(((Pair) obj).getCdr());
    }

    public static int listLength(Object obj) {
        int i = 0;
        Object obj2 = obj;
        while (true) {
            if (obj instanceof SyntaxForm) {
                obj = ((SyntaxForm) obj).getDatum();
            } else {
                while (obj2 instanceof SyntaxForm) {
                    obj2 = ((SyntaxForm) obj2).getDatum();
                }
                if (obj == LList.Empty) {
                    return i;
                }
                if (!(obj instanceof Pair)) {
                    return -1 - i;
                }
                int i2 = i + 1;
                Object cdr = ((Pair) obj).getCdr();
                while (cdr instanceof SyntaxForm) {
                    cdr = ((SyntaxForm) cdr).getDatum();
                }
                if (cdr == LList.Empty) {
                    return i2;
                }
                if (!(cdr instanceof Pair)) {
                    return -1 - i2;
                }
                obj2 = ((Pair) obj2).getCdr();
                obj = ((Pair) cdr).getCdr();
                i = i2 + 1;
                if (obj == obj2) {
                    return Integer.MIN_VALUE;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void rewriteInBody(Object obj) {
        if (obj instanceof SyntaxForm) {
            SyntaxForm syntaxForm = (SyntaxForm) obj;
            ScopeExp scopeExp = this.current_scope;
            try {
                setCurrentScope(syntaxForm.getScope());
                rewriteInBody(syntaxForm.getDatum());
                setCurrentScope(scopeExp);
            } catch (Throwable th) {
                setCurrentScope(scopeExp);
                throw th;
            }
        } else {
            if (obj instanceof Values) {
                Object[] values = ((Values) obj).getValues();
                for (Object rewriteInBody : values) {
                    rewriteInBody(rewriteInBody);
                }
                return;
            }
            this.formStack.add(rewrite(obj, false));
        }
    }

    public Expression rewrite(Object obj) {
        return rewrite(obj, false);
    }

    public Object namespaceResolve(Object obj) {
        if ((obj instanceof SimpleSymbol) || !(obj instanceof Pair)) {
            return obj;
        }
        Pair pair = (Pair) obj;
        if (safeCar(pair) != LispLanguage.lookup_sym || !(pair.getCdr() instanceof Pair)) {
            return obj;
        }
        Pair pair2 = (Pair) pair.getCdr();
        if (!(pair2.getCdr() instanceof Pair)) {
            return obj;
        }
        Expression rewrite = rewrite(pair2.getCar());
        Expression rewrite2 = rewrite(((Pair) pair2.getCdr()).getCar());
        Symbol namespaceResolve = namespaceResolve(rewrite, rewrite2);
        if (namespaceResolve != null) {
            return namespaceResolve;
        }
        String combineName = CompileNamedPart.combineName(rewrite, rewrite2);
        return combineName != null ? Namespace.EmptyNamespace.getSymbol(combineName) : obj;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x01cf, code lost:
        if ((r2 instanceof gnu.bytecode.ArrayClassLoader) == false) goto L_0x01b6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewrite(java.lang.Object r11, boolean r12) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof kawa.lang.SyntaxForm
            if (r0 == 0) goto L_0x0020
            kawa.lang.SyntaxForm r11 = (kawa.lang.SyntaxForm) r11
            gnu.expr.ScopeExp r0 = r10.current_scope
            kawa.lang.TemplateScope r1 = r11.getScope()     // Catch:{ all -> 0x001b }
            r10.setCurrentScope(r1)     // Catch:{ all -> 0x001b }
            java.lang.Object r11 = r11.getDatum()     // Catch:{ all -> 0x001b }
            gnu.expr.Expression r11 = r10.rewrite(r11, r12)     // Catch:{ all -> 0x001b }
            r10.setCurrentScope(r0)
            return r11
        L_0x001b:
            r11 = move-exception
            r10.setCurrentScope(r0)
            throw r11
        L_0x0020:
            boolean r0 = r11 instanceof gnu.lists.PairWithPosition
            if (r0 == 0) goto L_0x002c
            r0 = r11
            gnu.lists.PairWithPosition r0 = (gnu.lists.PairWithPosition) r0
            gnu.expr.Expression r11 = r10.rewrite_with_position(r11, r12, r0)
            return r11
        L_0x002c:
            boolean r0 = r11 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x0037
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            gnu.expr.Expression r11 = r10.rewrite_pair(r11, r12)
            return r11
        L_0x0037:
            boolean r0 = r11 instanceof gnu.mapping.Symbol
            if (r0 == 0) goto L_0x0260
            boolean r1 = r10.selfEvaluatingSymbol(r11)
            if (r1 != 0) goto L_0x0260
            gnu.expr.NameLookup r1 = r10.lexical
            gnu.expr.Declaration r1 = r1.lookup((java.lang.Object) r11, (boolean) r12)
            gnu.expr.ScopeExp r2 = r10.current_scope
            if (r1 != 0) goto L_0x004d
            r3 = -1
            goto L_0x0053
        L_0x004d:
            gnu.expr.ScopeExp r3 = r1.context
            int r3 = gnu.expr.ScopeExp.nesting(r3)
        L_0x0053:
            r4 = 0
            if (r0 == 0) goto L_0x0064
            r0 = r11
            gnu.mapping.Symbol r0 = (gnu.mapping.Symbol) r0
            boolean r0 = r0.hasEmptyNamespace()
            if (r0 == 0) goto L_0x0064
            java.lang.String r0 = r11.toString()
            goto L_0x0066
        L_0x0064:
            r0 = r4
            r2 = r0
        L_0x0066:
            if (r2 == 0) goto L_0x00da
            boolean r5 = r2 instanceof gnu.expr.LambdaExp
            if (r5 == 0) goto L_0x00d7
            gnu.expr.ScopeExp r5 = r2.outer
            boolean r5 = r5 instanceof gnu.expr.ClassExp
            if (r5 == 0) goto L_0x00d7
            r5 = r2
            gnu.expr.LambdaExp r5 = (gnu.expr.LambdaExp) r5
            boolean r6 = r5.isClassMethod()
            if (r6 == 0) goto L_0x00d7
            gnu.expr.ScopeExp r6 = r2.outer
            int r6 = gnu.expr.ScopeExp.nesting(r6)
            if (r3 < r6) goto L_0x0084
            goto L_0x00da
        L_0x0084:
            gnu.expr.ScopeExp r6 = r2.outer
            gnu.expr.ClassExp r6 = (gnu.expr.ClassExp) r6
            gnu.bytecode.ClassType r7 = r6.getClassType()
            gnu.bytecode.Member r8 = gnu.kawa.reflect.SlotGet.lookupMember(r7, r0, r7)
            gnu.expr.LambdaExp r9 = r6.clinitMethod
            if (r5 == r9) goto L_0x00a3
            gnu.expr.LambdaExp r6 = r6.initMethod
            if (r5 == r6) goto L_0x00a1
            gnu.expr.Declaration r6 = r5.nameDecl
            boolean r6 = r6.isStatic()
            if (r6 == 0) goto L_0x00a1
            goto L_0x00a3
        L_0x00a1:
            r6 = 0
            goto L_0x00a4
        L_0x00a3:
            r6 = 1
        L_0x00a4:
            if (r8 != 0) goto L_0x00b7
            if (r6 == 0) goto L_0x00ab
            r8 = 83
            goto L_0x00ad
        L_0x00ab:
            r8 = 86
        L_0x00ad:
            gnu.expr.Language r9 = r10.language
            gnu.expr.PrimProcedure[] r7 = gnu.kawa.reflect.ClassMethods.getMethods(r7, r0, r8, r7, r9)
            int r7 = r7.length
            if (r7 != 0) goto L_0x00b7
            goto L_0x00d7
        L_0x00b7:
            if (r6 == 0) goto L_0x00c5
            gnu.expr.ReferenceExp r11 = new gnu.expr.ReferenceExp
            gnu.expr.ScopeExp r12 = r5.outer
            gnu.expr.ClassExp r12 = (gnu.expr.ClassExp) r12
            gnu.expr.Declaration r12 = r12.nameDecl
            r11.<init>((gnu.expr.Declaration) r12)
            goto L_0x00ce
        L_0x00c5:
            gnu.expr.ThisExp r11 = new gnu.expr.ThisExp
            gnu.expr.Declaration r12 = r5.firstDecl()
            r11.<init>((gnu.expr.Declaration) r12)
        L_0x00ce:
            gnu.expr.QuoteExp r12 = gnu.expr.QuoteExp.getInstance(r0)
            gnu.expr.Expression r11 = gnu.kawa.functions.CompileNamedPart.makeExp((gnu.expr.Expression) r11, (gnu.expr.Expression) r12)
            return r11
        L_0x00d7:
            gnu.expr.ScopeExp r2 = r2.outer
            goto L_0x0066
        L_0x00da:
            if (r1 == 0) goto L_0x00f4
            java.lang.Object r11 = r1.getSymbol()
            gnu.expr.ReferenceExp r0 = getOriginalRef(r1)
            if (r0 == 0) goto L_0x00f1
            gnu.expr.Declaration r1 = r0.getBinding()
            if (r1 != 0) goto L_0x00f1
            java.lang.Object r11 = r0.getSymbol()
            goto L_0x00f4
        L_0x00f1:
            r0 = r11
            r11 = r4
            goto L_0x00f5
        L_0x00f4:
            r0 = r11
        L_0x00f5:
            r2 = r11
            gnu.mapping.Symbol r2 = (gnu.mapping.Symbol) r2
            gnu.expr.Language r3 = r10.getLanguage()
            boolean r3 = r3.hasSeparateFunctionNamespace()
            if (r1 == 0) goto L_0x0151
            gnu.expr.ScopeExp r11 = r10.current_scope
            boolean r11 = r11 instanceof kawa.lang.TemplateScope
            if (r11 == 0) goto L_0x0116
            boolean r11 = r1.needsContext()
            if (r11 == 0) goto L_0x0116
            gnu.expr.ScopeExp r11 = r10.current_scope
            kawa.lang.TemplateScope r11 = (kawa.lang.TemplateScope) r11
            gnu.expr.Declaration r4 = r11.macroContext
            goto L_0x0211
        L_0x0116:
            r5 = 1048576(0x100000, double:5.180654E-318)
            boolean r11 = r1.getFlag(r5)
            if (r11 == 0) goto L_0x0211
            boolean r11 = r1.isStatic()
            if (r11 != 0) goto L_0x0211
            gnu.expr.ScopeExp r11 = r10.currentScope()
        L_0x0129:
            if (r11 == 0) goto L_0x013a
            gnu.expr.ScopeExp r2 = r11.outer
            gnu.expr.ScopeExp r4 = r1.context
            if (r2 != r4) goto L_0x0137
            gnu.expr.Declaration r4 = r11.firstDecl()
            goto L_0x0211
        L_0x0137:
            gnu.expr.ScopeExp r11 = r11.outer
            goto L_0x0129
        L_0x013a:
            java.lang.Error r11 = new java.lang.Error
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "internal error: missing "
            r12.append(r0)
            r12.append(r1)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L_0x0151:
            gnu.mapping.Environment r5 = r10.env
            if (r12 == 0) goto L_0x015a
            if (r3 == 0) goto L_0x015a
            java.lang.Object r6 = gnu.mapping.EnvironmentKey.FUNCTION
            goto L_0x015b
        L_0x015a:
            r6 = r4
        L_0x015b:
            gnu.mapping.Location r5 = r5.lookup(r2, r6)
            if (r5 == 0) goto L_0x0165
            gnu.mapping.Location r5 = r5.getBase()
        L_0x0165:
            boolean r6 = r5 instanceof gnu.kawa.reflect.FieldLocation
            if (r6 == 0) goto L_0x01fc
            gnu.kawa.reflect.FieldLocation r5 = (gnu.kawa.reflect.FieldLocation) r5
            gnu.expr.Declaration r1 = r5.getDeclaration()     // Catch:{ all -> 0x01d5 }
            boolean r2 = r10.inlineOk((gnu.expr.Expression) r4)     // Catch:{ all -> 0x01d5 }
            if (r2 != 0) goto L_0x0196
            gnu.expr.Declaration r2 = getNamedPartDecl     // Catch:{ all -> 0x01d5 }
            if (r1 == r2) goto L_0x0196
            java.lang.String r2 = "objectSyntax"
            java.lang.String r6 = r5.getMemberName()     // Catch:{ all -> 0x01d5 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x01d5 }
            if (r2 == 0) goto L_0x01d3
            java.lang.String r2 = "kawa.standard.object"
            gnu.bytecode.ClassType r6 = r5.getDeclaringClass()     // Catch:{ all -> 0x01d5 }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x01d5 }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x01d5 }
            if (r2 != 0) goto L_0x0196
            goto L_0x01d3
        L_0x0196:
            boolean r2 = r10.immediate     // Catch:{ all -> 0x01d5 }
            if (r2 == 0) goto L_0x01b9
            boolean r2 = r1.isStatic()     // Catch:{ all -> 0x01d5 }
            if (r2 != 0) goto L_0x01b6
            gnu.expr.Declaration r2 = new gnu.expr.Declaration     // Catch:{ all -> 0x01d5 }
            java.lang.String r6 = "(module-instance)"
            r2.<init>((java.lang.Object) r6)     // Catch:{ all -> 0x01d5 }
            gnu.expr.QuoteExp r6 = new gnu.expr.QuoteExp     // Catch:{ all -> 0x01b4 }
            java.lang.Object r5 = r5.getInstance()     // Catch:{ all -> 0x01b4 }
            r6.<init>(r5)     // Catch:{ all -> 0x01b4 }
            r2.setValue(r6)     // Catch:{ all -> 0x01b4 }
            goto L_0x01b7
        L_0x01b4:
            r1 = move-exception
            goto L_0x01d7
        L_0x01b6:
            r2 = r4
        L_0x01b7:
            r4 = r1
            goto L_0x01f9
        L_0x01b9:
            boolean r2 = r1.isStatic()     // Catch:{ all -> 0x01d5 }
            if (r2 == 0) goto L_0x01d3
            java.lang.Class r2 = r5.getRClass()     // Catch:{ all -> 0x01d5 }
            if (r2 == 0) goto L_0x01d1
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ all -> 0x01d5 }
            boolean r5 = r2 instanceof gnu.bytecode.ZipLoader     // Catch:{ all -> 0x01d5 }
            if (r5 != 0) goto L_0x01d1
            boolean r11 = r2 instanceof gnu.bytecode.ArrayClassLoader     // Catch:{ all -> 0x01d5 }
            if (r11 == 0) goto L_0x01b6
        L_0x01d1:
            r1 = r4
            goto L_0x01b6
        L_0x01d3:
            r2 = r4
            goto L_0x01f9
        L_0x01d5:
            r1 = move-exception
            r2 = r4
        L_0x01d7:
            r5 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "exception loading '"
            r6.append(r7)
            r6.append(r11)
            java.lang.String r11 = "' - "
            r6.append(r11)
            java.lang.String r11 = r1.getMessage()
            r6.append(r11)
            java.lang.String r11 = r6.toString()
            r10.error(r5, r11)
        L_0x01f9:
            r1 = r4
            r4 = r2
            goto L_0x0211
        L_0x01fc:
            if (r5 == 0) goto L_0x0204
            boolean r11 = r5.isBound()
            if (r11 != 0) goto L_0x0211
        L_0x0204:
            gnu.expr.Language r11 = r10.getLanguage()
            gnu.kawa.lispexpr.LispLanguage r11 = (gnu.kawa.lispexpr.LispLanguage) r11
            gnu.expr.Expression r11 = r11.checkDefaultBinding(r2, r10)
            if (r11 == 0) goto L_0x0211
            return r11
        L_0x0211:
            if (r1 == 0) goto L_0x024b
            if (r12 != 0) goto L_0x0224
            java.lang.Object r11 = r1.getConstantValue()
            boolean r11 = r11 instanceof kawa.standard.object
            if (r11 == 0) goto L_0x0224
            java.lang.Class<java.lang.Object> r11 = java.lang.Object.class
            gnu.expr.QuoteExp r11 = gnu.expr.QuoteExp.getInstance(r11)
            return r11
        L_0x0224:
            gnu.expr.ScopeExp r11 = r1.getContext()
            boolean r11 = r11 instanceof kawa.lang.PatternScope
            if (r11 == 0) goto L_0x024b
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "reference to pattern variable "
            r11.append(r12)
            java.lang.String r12 = r1.getName()
            r11.append(r12)
            java.lang.String r12 = " outside syntax template"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            gnu.expr.Expression r11 = r10.syntaxError(r11)
            return r11
        L_0x024b:
            gnu.expr.ReferenceExp r11 = new gnu.expr.ReferenceExp
            r11.<init>(r0, r1)
            r11.setContextDecl(r4)
            r11.setLine((gnu.expr.Compilation) r10)
            if (r12 == 0) goto L_0x025f
            if (r3 == 0) goto L_0x025f
            r12 = 8
            r11.setFlag(r12)
        L_0x025f:
            return r11
        L_0x0260:
            boolean r0 = r11 instanceof gnu.expr.LangExp
            if (r0 == 0) goto L_0x026f
            gnu.expr.LangExp r11 = (gnu.expr.LangExp) r11
            java.lang.Object r11 = r11.getLangValue()
            gnu.expr.Expression r11 = r10.rewrite(r11, r12)
            return r11
        L_0x026f:
            boolean r12 = r11 instanceof gnu.expr.Expression
            if (r12 == 0) goto L_0x0276
            gnu.expr.Expression r11 = (gnu.expr.Expression) r11
            return r11
        L_0x0276:
            gnu.expr.Special r12 = gnu.expr.Special.abstractSpecial
            if (r11 != r12) goto L_0x027d
            gnu.expr.QuoteExp r11 = gnu.expr.QuoteExp.abstractExp
            return r11
        L_0x027d:
            java.lang.Object r11 = kawa.lang.Quote.quote(r11, r10)
            gnu.expr.QuoteExp r11 = gnu.expr.QuoteExp.getInstance(r11, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Translator.rewrite(java.lang.Object, boolean):gnu.expr.Expression");
    }

    public static void setLine(Expression expression, Object obj) {
        if (obj instanceof SourceLocator) {
            expression.setLocation((SourceLocator) obj);
        }
    }

    public static void setLine(Declaration declaration, Object obj) {
        if (obj instanceof SourceLocator) {
            declaration.setLocation((SourceLocator) obj);
        }
    }

    public Object pushPositionOf(Object obj) {
        PairWithPosition pairWithPosition;
        if (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if (!(obj instanceof PairWithPosition)) {
            return null;
        }
        PairWithPosition pairWithPosition2 = (PairWithPosition) obj;
        PairWithPosition pairWithPosition3 = this.positionPair;
        if (pairWithPosition3 != null && pairWithPosition3.getFileName() == getFileName() && this.positionPair.getLineNumber() == getLineNumber() && this.positionPair.getColumnNumber() == getColumnNumber()) {
            pairWithPosition = this.positionPair;
        } else {
            pairWithPosition = new PairWithPosition(this, Special.eof, this.positionPair);
        }
        setLine(obj);
        this.positionPair = pairWithPosition2;
        return pairWithPosition;
    }

    public void popPositionOf(Object obj) {
        if (obj != null) {
            setLine(obj);
            PairWithPosition pairWithPosition = (PairWithPosition) obj;
            this.positionPair = pairWithPosition;
            if (pairWithPosition.getCar() == Special.eof) {
                this.positionPair = (PairWithPosition) this.positionPair.getCdr();
            }
        }
    }

    public void setLineOf(Expression expression) {
        if (!(expression instanceof QuoteExp)) {
            expression.setLocation(this);
        }
    }

    public Type exp2Type(Pair pair) {
        Object pushPositionOf = pushPositionOf(pair);
        try {
            Expression inlineCalls = InlineCalls.inlineCalls(rewrite_car(pair, false), this);
            if (inlineCalls instanceof ErrorExp) {
                return null;
            }
            Type typeFor = getLanguage().getTypeFor(inlineCalls);
            if (typeFor == null) {
                try {
                    Object eval = inlineCalls.eval(this.env);
                    if (eval instanceof Class) {
                        typeFor = Type.make((Class) eval);
                    } else if (eval instanceof Type) {
                        typeFor = (Type) eval;
                    }
                } catch (Throwable unused) {
                }
            }
            if (typeFor == null) {
                if (inlineCalls instanceof ReferenceExp) {
                    error('e', "unknown type name '" + ((ReferenceExp) inlineCalls).getName() + '\'');
                } else {
                    error('e', "invalid type spec (must be \"type\" or 'type or <type>)");
                }
                ClassType classType = Type.pointer_type;
                popPositionOf(pushPositionOf);
                return classType;
            }
            popPositionOf(pushPositionOf);
            return typeFor;
        } finally {
            popPositionOf(pushPositionOf);
        }
    }

    public Expression rewrite_with_position(Object obj, boolean z, PairWithPosition pairWithPosition) {
        Expression expression;
        Object pushPositionOf = pushPositionOf(pairWithPosition);
        if (obj == pairWithPosition) {
            try {
                expression = rewrite_pair(pairWithPosition, z);
            } catch (Throwable th) {
                popPositionOf(pushPositionOf);
                throw th;
            }
        } else {
            expression = rewrite(obj, z);
        }
        setLineOf(expression);
        popPositionOf(pushPositionOf);
        return expression;
    }

    public static Object wrapSyntax(Object obj, SyntaxForm syntaxForm) {
        return (syntaxForm == null || (obj instanceof Expression)) ? obj : SyntaxForms.fromDatumIfNeeded(obj, syntaxForm);
    }

    public Object popForms(int i) {
        Object obj;
        int size = this.formStack.size();
        if (size == i) {
            return Values.empty;
        }
        if (size == i + 1) {
            obj = this.formStack.elementAt(i);
        } else {
            Values values = new Values();
            for (int i2 = i; i2 < size; i2++) {
                values.writeObject(this.formStack.elementAt(i2));
            }
            obj = values;
        }
        this.formStack.setSize(i);
        return obj;
    }

    /* JADX INFO: finally extract failed */
    public void scanForm(Object obj, ScopeExp scopeExp) {
        Syntax syntax;
        if (obj instanceof SyntaxForm) {
            SyntaxForm syntaxForm = (SyntaxForm) obj;
            ScopeExp currentScope = currentScope();
            try {
                setCurrentScope(syntaxForm.getScope());
                int size = this.formStack.size();
                scanForm(syntaxForm.getDatum(), scopeExp);
                this.formStack.add(wrapSyntax(popForms(size), syntaxForm));
                setCurrentScope(currentScope);
            } catch (Throwable th) {
                setCurrentScope(currentScope);
                throw th;
            }
        } else {
            if (obj instanceof Values) {
                if (obj == Values.empty) {
                    obj = QuoteExp.voidExp;
                } else {
                    Object[] values = ((Values) obj).getValues();
                    for (Object scanForm : values) {
                        scanForm(scanForm, scopeExp);
                    }
                    return;
                }
            }
            if (obj instanceof Pair) {
                Pair pair = (Pair) obj;
                Declaration declaration = this.macroContext;
                ScopeExp scopeExp2 = this.current_scope;
                Object pushPositionOf = pushPositionOf(obj);
                if ((obj instanceof SourceLocator) && scopeExp.getLineNumber() < 0) {
                    scopeExp.setLocation((SourceLocator) obj);
                }
                try {
                    Object car = pair.getCar();
                    if (car instanceof SyntaxForm) {
                        SyntaxForm syntaxForm2 = (SyntaxForm) pair.getCar();
                        setCurrentScope(syntaxForm2.getScope());
                        car = syntaxForm2.getDatum();
                    }
                    Syntax syntax2 = null;
                    if (car instanceof Pair) {
                        Pair pair2 = (Pair) car;
                        if (pair2.getCar() == LispLanguage.lookup_sym && (pair2.getCdr() instanceof Pair)) {
                            Pair pair3 = (Pair) pair2.getCdr();
                            if (pair3.getCdr() instanceof Pair) {
                                Expression rewrite = rewrite(pair3.getCar());
                                Expression rewrite2 = rewrite(((Pair) pair3.getCdr()).getCar());
                                Object valueIfConstant = rewrite.valueIfConstant();
                                Object valueIfConstant2 = rewrite2.valueIfConstant();
                                if (!(valueIfConstant instanceof Class) || !(valueIfConstant2 instanceof Symbol)) {
                                    car = namespaceResolve(rewrite, rewrite2);
                                } else {
                                    try {
                                        car = GetNamedPart.getNamedPart(valueIfConstant, (Symbol) valueIfConstant2);
                                        if (car instanceof Syntax) {
                                            syntax2 = (Syntax) car;
                                        }
                                    } catch (Throwable unused) {
                                        car = null;
                                    }
                                }
                            }
                        }
                    }
                    if ((car instanceof Symbol) && !selfEvaluatingSymbol(car)) {
                        Expression rewrite3 = rewrite(car, true);
                        if (rewrite3 instanceof ReferenceExp) {
                            Declaration binding = ((ReferenceExp) rewrite3).getBinding();
                            if (binding != null) {
                                syntax = check_if_Syntax(binding);
                            } else {
                                Object resolve = resolve(car, true);
                                if (resolve instanceof Syntax) {
                                    syntax = (Syntax) resolve;
                                }
                            }
                            syntax2 = syntax;
                        }
                    } else if (car == begin.begin) {
                        syntax2 = (Syntax) car;
                    }
                    if (syntax2 != null) {
                        String fileName = getFileName();
                        int lineNumber = getLineNumber();
                        int columnNumber = getColumnNumber();
                        try {
                            setLine((Object) pair);
                            syntax2.scanForm(pair, scopeExp, this);
                            return;
                        } finally {
                            this.macroContext = declaration;
                            setLine(fileName, lineNumber, columnNumber);
                        }
                    }
                } finally {
                    if (scopeExp2 != this.current_scope) {
                        setCurrentScope(scopeExp2);
                    }
                    popPositionOf(pushPositionOf);
                }
            }
            this.formStack.add(obj);
        }
    }

    /* JADX INFO: finally extract failed */
    public LList scanBody(Object obj, ScopeExp scopeExp, boolean z) {
        Pair pair = z ? LList.Empty : null;
        Pair pair2 = null;
        while (true) {
            if (obj != LList.Empty) {
                if (!(obj instanceof SyntaxForm)) {
                    if (!(obj instanceof Pair)) {
                        this.formStack.add(syntaxError("body is not a proper list"));
                        break;
                    }
                    Pair pair3 = (Pair) obj;
                    int size = this.formStack.size();
                    scanForm(pair3.getCar(), scopeExp);
                    if (getState() == 2) {
                        Object car = pair3.getCar();
                        Object obj2 = this.pendingForm;
                        if (car != obj2) {
                            pair3 = makePair(pair3, obj2, pair3.getCdr());
                        }
                        this.pendingForm = new Pair(begin.begin, pair3);
                        return LList.Empty;
                    }
                    int size2 = this.formStack.size();
                    if (z) {
                        int i = size;
                        while (i < size2) {
                            Pair makePair = makePair(pair3, this.formStack.elementAt(i), LList.Empty);
                            if (pair2 == null) {
                                pair = makePair;
                            } else {
                                pair2.setCdrBackdoor(makePair);
                            }
                            i++;
                            pair2 = makePair;
                        }
                        this.formStack.setSize(size);
                    }
                    obj = pair3.getCdr();
                } else {
                    SyntaxForm syntaxForm = (SyntaxForm) obj;
                    ScopeExp scopeExp2 = this.current_scope;
                    try {
                        setCurrentScope(syntaxForm.getScope());
                        int size3 = this.formStack.size();
                        LList scanBody = scanBody(syntaxForm.getDatum(), scopeExp, z);
                        if (z) {
                            LList lList = (LList) SyntaxForms.fromDatumIfNeeded(scanBody, syntaxForm);
                            if (pair2 == null) {
                                setCurrentScope(scopeExp2);
                                return lList;
                            }
                            pair2.setCdrBackdoor(lList);
                            setCurrentScope(scopeExp2);
                            return pair;
                        }
                        this.formStack.add(wrapSyntax(popForms(size3), syntaxForm));
                        setCurrentScope(scopeExp2);
                        return null;
                    } catch (Throwable th) {
                        setCurrentScope(scopeExp2);
                        throw th;
                    }
                }
            } else {
                break;
            }
        }
        return pair;
    }

    public static Pair makePair(Pair pair, Object obj, Object obj2) {
        if (pair instanceof PairWithPosition) {
            return new PairWithPosition((PairWithPosition) pair, obj, obj2);
        }
        return new Pair(obj, obj2);
    }

    public Expression rewrite_body(Object obj) {
        Object pushPositionOf = pushPositionOf(obj);
        LetExp letExp = new LetExp((Expression[]) null);
        int size = this.formStack.size();
        letExp.outer = this.current_scope;
        this.current_scope = letExp;
        try {
            LList scanBody = scanBody(obj, letExp, true);
            if (scanBody.isEmpty()) {
                this.formStack.add(syntaxError("body with no expressions"));
            }
            int countNonDynamicDecls = letExp.countNonDynamicDecls();
            if (countNonDynamicDecls != 0) {
                Expression[] expressionArr = new Expression[countNonDynamicDecls];
                int i = countNonDynamicDecls;
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    expressionArr[i] = QuoteExp.undefined_exp;
                }
                letExp.inits = expressionArr;
            }
            rewriteBody(scanBody);
            Expression makeBody = makeBody(size, (ScopeExp) null);
            setLineOf(makeBody);
            if (countNonDynamicDecls == 0) {
                return makeBody;
            }
            letExp.body = makeBody;
            setLineOf(letExp);
            pop(letExp);
            popPositionOf(pushPositionOf);
            return letExp;
        } finally {
            pop(letExp);
            popPositionOf(pushPositionOf);
        }
    }

    /* JADX INFO: finally extract failed */
    private void rewriteBody(LList lList) {
        while (lList != LList.Empty) {
            Pair pair = (Pair) lList;
            Object pushPositionOf = pushPositionOf(pair);
            try {
                rewriteInBody(pair.getCar());
                popPositionOf(pushPositionOf);
                lList = (LList) pair.getCdr();
            } catch (Throwable th) {
                popPositionOf(pushPositionOf);
                throw th;
            }
        }
    }

    private Expression makeBody(int i, ScopeExp scopeExp) {
        int size = this.formStack.size() - i;
        if (size == 0) {
            return QuoteExp.voidExp;
        }
        if (size == 1) {
            return (Expression) this.formStack.pop();
        }
        Expression[] expressionArr = new Expression[size];
        for (int i2 = 0; i2 < size; i2++) {
            expressionArr[i2] = (Expression) this.formStack.elementAt(i + i2);
        }
        this.formStack.setSize(i);
        if (scopeExp instanceof ModuleExp) {
            return new ApplyExp((Procedure) AppendValues.appendValues, expressionArr);
        }
        return ((LispLanguage) getLanguage()).makeBody(expressionArr);
    }

    public void noteAccess(Object obj, ScopeExp scopeExp) {
        if (this.notedAccess == null) {
            this.notedAccess = new Vector();
        }
        this.notedAccess.addElement(obj);
        this.notedAccess.addElement(scopeExp);
    }

    public void processAccesses() {
        Vector vector = this.notedAccess;
        if (vector != null) {
            int size = vector.size();
            ScopeExp scopeExp = this.current_scope;
            for (int i = 0; i < size; i += 2) {
                Object elementAt = this.notedAccess.elementAt(i);
                ScopeExp scopeExp2 = (ScopeExp) this.notedAccess.elementAt(i + 1);
                if (this.current_scope != scopeExp2) {
                    setCurrentScope(scopeExp2);
                }
                Declaration lookup = this.lexical.lookup(elementAt, -1);
                if (lookup != null && !lookup.getFlag(65536)) {
                    lookup.getContext().currentLambda().capture(lookup);
                    lookup.setCanRead(true);
                    lookup.setSimple(false);
                    lookup.setFlag(524288);
                }
            }
            if (this.current_scope != scopeExp) {
                setCurrentScope(scopeExp);
            }
        }
    }

    public void finishModule(ModuleExp moduleExp) {
        boolean isStatic = moduleExp.isStatic();
        for (Declaration firstDecl = moduleExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if (firstDecl.getFlag(512)) {
                error('e', firstDecl, "'", firstDecl.getFlag(1024) ? "' exported but never defined" : firstDecl.getFlag(2048) ? "' declared static but never defined" : "' declared but never defined");
            }
            if (moduleExp.getFlag(16384) || (this.generateMain && !this.immediate)) {
                if (!firstDecl.getFlag(1024)) {
                    firstDecl.setPrivate(true);
                } else if (firstDecl.isPrivate()) {
                    if (firstDecl.getFlag(16777216)) {
                        error('e', firstDecl, "'", "' is declared both private and exported");
                    }
                    firstDecl.setPrivate(false);
                }
            }
            if (isStatic) {
                firstDecl.setFlag(2048);
            } else if ((moduleExp.getFlag(65536) && !firstDecl.getFlag(2048)) || Compilation.moduleStatic < 0 || moduleExp.getFlag(131072)) {
                firstDecl.setFlag(4096);
            }
        }
    }

    static void vectorReverse(Vector vector, int i, int i2) {
        int i3 = i2 / 2;
        int i4 = (i2 + i) - 1;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i + i5;
            Object elementAt = vector.elementAt(i6);
            int i7 = i4 - i5;
            vector.setElementAt(vector.elementAt(i7), i6);
            vector.setElementAt(elementAt, i7);
        }
    }

    public void resolveModule(ModuleExp moduleExp) {
        int i = 0;
        int size = this.pendingImports == null ? 0 : this.pendingImports.size();
        while (i < size) {
            int i2 = i + 1;
            ModuleInfo moduleInfo = (ModuleInfo) this.pendingImports.elementAt(i);
            int i3 = i2 + 1;
            ScopeExp scopeExp = (ScopeExp) this.pendingImports.elementAt(i2);
            int i4 = i3 + 1;
            Expression expression = (Expression) this.pendingImports.elementAt(i3);
            int i5 = i4 + 1;
            Integer num = (Integer) this.pendingImports.elementAt(i4);
            if (moduleExp == scopeExp) {
                Object obj = null;
                ReferenceExp referenceExp = new ReferenceExp((Object) null);
                referenceExp.setLine((Compilation) this);
                setLine(expression);
                int size2 = this.formStack.size();
                require.importDefinitions((String) null, moduleInfo, (Procedure) null, this.formStack, scopeExp, this);
                int intValue = num.intValue();
                if (num.intValue() != size2) {
                    int size3 = this.formStack.size();
                    vectorReverse(this.formStack, intValue, size2 - intValue);
                    vectorReverse(this.formStack, size2, size3 - size2);
                    vectorReverse(this.formStack, intValue, size3 - intValue);
                }
                setLine((Expression) referenceExp);
            }
            i = i5;
        }
        this.pendingImports = null;
        processAccesses();
        setModule(moduleExp);
        Compilation saveCurrent = Compilation.setSaveCurrent(this);
        try {
            rewriteInBody(popForms(this.firstForm));
            moduleExp.body = makeBody(this.firstForm, moduleExp);
            if (!this.immediate) {
                this.lexical.pop((ScopeExp) moduleExp);
            }
        } finally {
            Compilation.restoreCurrent(saveCurrent);
        }
    }

    public Declaration makeRenamedAlias(Declaration declaration, ScopeExp scopeExp) {
        return scopeExp == null ? declaration : makeRenamedAlias(declaration.getSymbol(), declaration, scopeExp);
    }

    public Declaration makeRenamedAlias(Object obj, Declaration declaration, ScopeExp scopeExp) {
        Declaration declaration2 = new Declaration(obj);
        declaration2.setAlias(true);
        declaration2.setPrivate(true);
        declaration2.context = scopeExp;
        ReferenceExp referenceExp = new ReferenceExp(declaration);
        referenceExp.setDontDereference(true);
        declaration2.noteValue(referenceExp);
        return declaration2;
    }

    public void pushRenamedAlias(Declaration declaration) {
        Declaration binding = getOriginalRef(declaration).getBinding();
        ScopeExp scopeExp = declaration.context;
        binding.setSymbol((Object) null);
        Declaration lookup = scopeExp.lookup(binding.getSymbol());
        if (lookup != null) {
            scopeExp.remove(lookup);
        }
        scopeExp.addDeclaration(declaration);
        if (this.renamedAliasStack == null) {
            this.renamedAliasStack = new Stack();
        }
        this.renamedAliasStack.push(lookup);
        this.renamedAliasStack.push(declaration);
        this.renamedAliasStack.push(scopeExp);
    }

    public void popRenamedAlias(int i) {
        while (true) {
            i--;
            if (i >= 0) {
                ScopeExp scopeExp = (ScopeExp) this.renamedAliasStack.pop();
                Declaration declaration = (Declaration) this.renamedAliasStack.pop();
                getOriginalRef(declaration).getBinding().setSymbol(declaration.getSymbol());
                scopeExp.remove(declaration);
                Object pop = this.renamedAliasStack.pop();
                if (pop != null) {
                    scopeExp.addDeclaration((Declaration) pop);
                }
            } else {
                return;
            }
        }
    }

    public Declaration define(Object obj, SyntaxForm syntaxForm, ScopeExp scopeExp) {
        boolean z = (syntaxForm == null || syntaxForm.getScope() == currentScope()) ? false : true;
        Declaration define = scopeExp.getDefine(z ? new String(obj.toString()) : obj, 'w', this);
        if (z) {
            syntaxForm.getScope().addDeclaration(makeRenamedAlias(obj, define, syntaxForm.getScope()));
        }
        push(define);
        return define;
    }
}
