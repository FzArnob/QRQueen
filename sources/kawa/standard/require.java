package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.text.Path;
import java.util.Hashtable;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class require extends Syntax {
    private static final String SLIB_PREFIX = "gnu.kawa.slib.";
    static Hashtable featureMap = new Hashtable();
    public static final require require;

    public Expression rewriteForm(Pair pair, Translator translator) {
        return null;
    }

    static {
        require require2 = new require();
        require = require2;
        require2.setName("require");
        map("generic-write", "gnu.kawa.slib.genwrite");
        map("pretty-print", "gnu.kawa.slib.pp");
        map("pprint-file", "gnu.kawa.slib.ppfile");
        map("printf", "gnu.kawa.slib.printf");
        map("xml", "gnu.kawa.slib.XML");
        map("readtable", "gnu.kawa.slib.readtable");
        map("srfi-10", "gnu.kawa.slib.readtable");
        map("http", "gnu.kawa.servlet.HTTP");
        map("servlets", "gnu.kawa.servlet.servlets");
        map("srfi-1", "gnu.kawa.slib.srfi1");
        map("list-lib", "gnu.kawa.slib.srfi1");
        map("srfi-2", "gnu.kawa.slib.srfi2");
        map("and-let*", "gnu.kawa.slib.srfi2");
        map("srfi-13", "gnu.kawa.slib.srfi13");
        map("string-lib", "gnu.kawa.slib.srfi13");
        map("srfi-34", "gnu.kawa.slib.srfi34");
        map("srfi-35", "gnu.kawa.slib.conditions");
        map("condition", "gnu.kawa.slib.conditions");
        map("conditions", "gnu.kawa.slib.conditions");
        map("srfi-37", "gnu.kawa.slib.srfi37");
        map("args-fold", "gnu.kawa.slib.srfi37");
        map("srfi-64", "gnu.kawa.slib.testing");
        map("testing", "gnu.kawa.slib.testing");
        map("srfi-69", "gnu.kawa.slib.srfi69");
        map("hash-table", "gnu.kawa.slib.srfi69");
        map("basic-hash-tables", "gnu.kawa.slib.srfi69");
        map("srfi-95", "kawa.lib.srfi95");
        map("sorting-and-merging", "kawa.lib.srfi95");
        map("regex", "kawa.lib.kawa.regex");
        map("pregexp", "gnu.kawa.slib.pregexp");
        map("gui", "gnu.kawa.slib.gui");
        map("swing-gui", "gnu.kawa.slib.swing");
        map("android-defs", "gnu.kawa.android.defs");
        map("syntax-utils", "gnu.kawa.slib.syntaxutils");
    }

    static void map(String str, String str2) {
        featureMap.put(str, str2);
    }

    public static String mapFeature(String str) {
        return (String) featureMap.get(str);
    }

    public static Object find(String str) {
        return ModuleManager.getInstance().findWithClassName(str).getInstance();
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanForDefinitions(gnu.lists.Pair r12, java.util.Vector r13, gnu.expr.ScopeExp r14, kawa.lang.Translator r15) {
        /*
            r11 = this;
            int r0 = r15.getState()
            r6 = 1
            if (r0 != r6) goto L_0x000e
            r0 = 2
            r15.setState(r0)
            r15.pendingForm = r12
            return r6
        L_0x000e:
            java.lang.Object r0 = r12.getCdr()
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r1 = r0.getCar()
            r2 = 0
            boolean r3 = r1 instanceof gnu.lists.Pair
            r7 = 101(0x65, float:1.42E-43)
            r8 = 0
            if (r3 == 0) goto L_0x0085
            r3 = r1
            gnu.lists.Pair r3 = (gnu.lists.Pair) r3
            java.lang.Object r9 = r3.getCar()
            java.lang.String r10 = "quote"
            boolean r9 = r15.matches(r9, r10)
            if (r9 == 0) goto L_0x0085
            java.lang.Object r0 = r3.getCdr()
            boolean r1 = r0 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x007f
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r1 = r0.getCdr()
            gnu.lists.LList r2 = gnu.lists.LList.Empty
            if (r1 != r2) goto L_0x007f
            java.lang.Object r1 = r0.getCar()
            boolean r1 = r1 instanceof gnu.mapping.Symbol
            if (r1 != 0) goto L_0x004a
            goto L_0x007f
        L_0x004a:
            java.lang.Object r1 = r0.getCar()
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = mapFeature(r1)
            if (r1 != 0) goto L_0x0076
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown feature name '"
            r1.append(r2)
            java.lang.Object r0 = r0.getCar()
            r1.append(r0)
            java.lang.String r0 = "' for 'require'"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r15.error(r7, r0)
            return r8
        L_0x0076:
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r1)
            goto L_0x010c
        L_0x007f:
            java.lang.String r0 = "invalid quoted symbol for 'require'"
            r15.error(r7, r0)
            return r8
        L_0x0085:
            boolean r3 = r1 instanceof java.lang.CharSequence
            java.lang.String r9 = "malformed URL: "
            if (r3 == 0) goto L_0x00b2
            java.lang.String r0 = r1.toString()
            gnu.expr.ModuleInfo r1 = lookupModuleFromSourcePath(r0, r14)
            if (r1 != 0) goto L_0x00a8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r15.error(r7, r0)
            return r8
        L_0x00a8:
            r0 = 0
            r2 = 0
            r3 = r13
            r4 = r14
            r5 = r15
            boolean r0 = importDefinitions(r0, r1, r2, r3, r4, r5)
            return r0
        L_0x00b2:
            boolean r3 = r1 instanceof gnu.mapping.Symbol
            if (r3 == 0) goto L_0x010c
            boolean r3 = r15.selfEvaluatingSymbol(r1)
            if (r3 != 0) goto L_0x010c
            gnu.expr.Language r2 = r15.getLanguage()
            gnu.expr.Expression r1 = r15.rewrite(r1, r8)
            gnu.bytecode.Type r2 = r2.getTypeFor((gnu.expr.Expression) r1)
            boolean r1 = r2 instanceof gnu.bytecode.ClassType
            if (r1 == 0) goto L_0x010c
            java.lang.Object r1 = r0.getCdr()
            boolean r1 = r1 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x010c
            java.lang.Object r0 = r0.getCdr()
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r0 = r0.getCar()
            boolean r1 = r0 instanceof java.lang.CharSequence
            if (r1 == 0) goto L_0x010c
            java.lang.String r0 = r0.toString()
            gnu.expr.ModuleInfo r1 = lookupModuleFromSourcePath(r0, r14)
            if (r1 != 0) goto L_0x00ff
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r15.error(r7, r0)
            return r8
        L_0x00ff:
            java.lang.String r0 = r2.getName()
            r2 = 0
            r3 = r13
            r4 = r14
            r5 = r15
            boolean r0 = importDefinitions(r0, r1, r2, r3, r4, r5)
            return r0
        L_0x010c:
            boolean r0 = r2 instanceof gnu.bytecode.ClassType
            if (r0 != 0) goto L_0x0116
            java.lang.String r0 = "invalid specifier for 'require'"
            r15.error(r7, r0)
            return r8
        L_0x0116:
            r0 = r2
            gnu.bytecode.ClassType r0 = (gnu.bytecode.ClassType) r0     // Catch:{ Exception -> 0x0126 }
            gnu.expr.ModuleInfo r1 = gnu.expr.ModuleInfo.find(r0)     // Catch:{ Exception -> 0x0126 }
            r0 = 0
            r2 = 0
            r3 = r13
            r4 = r14
            r5 = r15
            importDefinitions(r0, r1, r2, r3, r4, r5)
            return r6
        L_0x0126:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unknown class "
            r0.append(r1)
            java.lang.String r1 = r2.getName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r15.error(r7, r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.require.scanForDefinitions(gnu.lists.Pair, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }

    public static ModuleInfo lookupModuleFromSourcePath(String str, ScopeExp scopeExp) {
        ModuleManager instance = ModuleManager.getInstance();
        String fileName = scopeExp.getFileName();
        if (fileName != null) {
            str = Path.valueOf(fileName).resolve(str).toString();
        }
        return instance.findWithSourcePath(str);
    }

    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r6v5, types: [int, boolean] */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x02a6  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02ae  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0287  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean importDefinitions(java.lang.String r21, gnu.expr.ModuleInfo r22, gnu.mapping.Procedure r23, java.util.Vector r24, gnu.expr.ScopeExp r25, gnu.expr.Compilation r26) {
        /*
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r4 = r26
            gnu.expr.ModuleManager r5 = gnu.expr.ModuleManager.getInstance()
            int r6 = r22.getState()
            r7 = 1
            r6 = r6 & r7
            r8 = 101(0x65, float:1.42E-43)
            r9 = 0
            if (r6 != 0) goto L_0x00ac
            gnu.expr.Compilation r6 = r22.getCompilation()
            if (r6 != 0) goto L_0x00ac
            long r10 = java.lang.System.currentTimeMillis()
            boolean r5 = r0.checkCurrent(r5, r10)
            if (r5 != 0) goto L_0x00ac
            gnu.text.SourceMessages r5 = r26.getMessages()
            gnu.expr.Language r6 = gnu.expr.Language.getDefaultLanguage()
            gnu.text.Path r10 = r22.getSourceAbsPath()     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            gnu.mapping.InPort r10 = gnu.mapping.InPort.openFile(r10)     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            r22.clearClass()     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            r11 = r21
            r0.setClassName(r11)     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            r11 = 8
            boolean r12 = r4.immediate     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            if (r12 == 0) goto L_0x0049
            r11 = 9
        L_0x0049:
            gnu.expr.Compilation r5 = r6.parse(r10, r5, r11, r0)     // Catch:{ FileNotFoundException -> 0x0092, IOException -> 0x007c, SyntaxException -> 0x005d }
            gnu.expr.ModuleExp r6 = r5.getModule()
            gnu.bytecode.ClassType r5 = r6.classFor(r5)
            java.lang.String r5 = r5.getName()
            r0.setClassName(r5)
            goto L_0x00ac
        L_0x005d:
            r0 = move-exception
            gnu.text.SourceMessages r1 = r0.getMessages()
            if (r1 != r5) goto L_0x0065
            return r9
        L_0x0065:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "confussing syntax error: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x007c:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "caught "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.error(r8, r0)
            return r9
        L_0x0092:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "not found: "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.error(r8, r0)
            return r9
        L_0x00ac:
            gnu.expr.ModuleInfo r5 = r4.minfo
            r6 = 6
            if (r5 == 0) goto L_0x00d3
            int r5 = r26.getState()
            r10 = 4
            if (r5 >= r10) goto L_0x00d3
            gnu.expr.ModuleInfo r5 = r4.minfo
            r5.addDependency(r0)
            r5 = 12
            boolean r5 = r0.loadEager(r5)
            if (r5 != 0) goto L_0x00d3
            int r5 = r22.getState()
            if (r5 >= r6) goto L_0x00d3
            int r1 = r24.size()
            r4.pushPendingImport(r0, r3, r1)
            return r7
        L_0x00d3:
            gnu.bytecode.ClassType r5 = r22.getClassType()
            java.lang.String r10 = r5.getName()
            boolean r11 = r26.sharedModuleDefs()
            int r12 = r22.getState()
            if (r12 >= r6) goto L_0x00ee
            gnu.expr.Compilation r6 = r22.getCompilation()
            boolean r6 = r6.makeRunnable()
            goto L_0x00f4
        L_0x00ee:
            gnu.bytecode.ClassType r6 = gnu.expr.Compilation.typeRunnable
            boolean r6 = r5.isSubtype(r6)
        L_0x00f4:
            java.lang.String r12 = "kawa.standard.require"
            gnu.bytecode.ClassType r12 = gnu.bytecode.ClassType.make(r12)
            gnu.expr.Expression[] r13 = new gnu.expr.Expression[r7]
            gnu.expr.QuoteExp r14 = new gnu.expr.QuoteExp
            r14.<init>(r10)
            r13[r9] = r14
            java.lang.String r14 = "find"
            gnu.expr.ApplyExp r12 = gnu.kawa.reflect.Invoke.makeInvokeStatic(r12, r14, r13)
            gnu.expr.Language r13 = r26.getLanguage()
            r12.setLine((gnu.expr.Compilation) r4)
            int r14 = r24.size()
            gnu.expr.ModuleExp r0 = r22.setupModuleExp()
            java.util.Vector r15 = new java.util.Vector
            r15.<init>()
            gnu.expr.Declaration r0 = r0.firstDecl()
            r16 = 0
            r9 = r16
            r17 = r9
            r16 = r14
            r14 = r0
        L_0x012a:
            java.lang.String r7 = "$instance"
            if (r14 == 0) goto L_0x02f8
            boolean r0 = r14.isPrivate()
            if (r0 == 0) goto L_0x0135
            goto L_0x0168
        L_0x0135:
            java.lang.Object r0 = r14.getSymbol()
            gnu.mapping.Symbol r0 = (gnu.mapping.Symbol) r0
            if (r1 == 0) goto L_0x0175
            java.lang.Object r0 = r1.apply1(r0)     // Catch:{ all -> 0x0142 }
            goto L_0x0143
        L_0x0142:
            r0 = move-exception
        L_0x0143:
            if (r0 != 0) goto L_0x0146
            goto L_0x0168
        L_0x0146:
            boolean r8 = r0 instanceof gnu.mapping.Symbol
            if (r8 != 0) goto L_0x0171
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "internal error - import name mapper returned non-symbol: "
            r7.append(r8)
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r8 = 101(0x65, float:1.42E-43)
            r4.error(r8, r0)
        L_0x0168:
            r20 = r12
            r1 = r16
        L_0x016c:
            r16 = r13
        L_0x016e:
            r13 = r10
            goto L_0x02e7
        L_0x0171:
            r8 = 101(0x65, float:1.42E-43)
            gnu.mapping.Symbol r0 = (gnu.mapping.Symbol) r0
        L_0x0175:
            r18 = r9
            r8 = 2048(0x800, double:1.0118E-320)
            boolean r19 = r14.getFlag(r8)
            if (r19 != 0) goto L_0x01da
            if (r18 != 0) goto L_0x01da
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r1 = 46
            r9 = 36
            java.lang.String r1 = r10.replace(r1, r9)
            r8.append(r1)
            r8.append(r7)
            java.lang.String r1 = r8.toString()
            gnu.expr.Declaration r9 = new gnu.expr.Declaration
            gnu.mapping.SimpleSymbol r1 = gnu.mapping.SimpleSymbol.valueOf(r1)
            r9.<init>((java.lang.Object) r1, (gnu.bytecode.Type) r5)
            r1 = 1
            r9.setPrivate(r1)
            r1 = 1073758208(0x40004000, double:5.305070425E-315)
            r9.setFlag(r1)
            r3.addDeclaration((gnu.expr.Declaration) r9)
            r9.noteValue(r12)
            gnu.expr.SetExp r1 = new gnu.expr.SetExp
            r1.<init>((gnu.expr.Declaration) r9, (gnu.expr.Expression) r12)
            r1.setLine((gnu.expr.Compilation) r4)
            r2 = 1
            r1.setDefining(r2)
            r2 = r24
            r2.addElement(r1)
            int r16 = r24.size()
            r1 = 536870912(0x20000000, double:2.652494739E-315)
            r9.setFlag(r1)
            if (r6 == 0) goto L_0x01d2
            r1 = 0
            r9.setSimple(r1)
        L_0x01d2:
            r1 = 8192(0x2000, double:4.0474E-320)
            r9.setFlag(r1)
            r1 = r16
            goto L_0x01de
        L_0x01da:
            r1 = r16
            r9 = r18
        L_0x01de:
            gnu.bytecode.Field r2 = r14.field
            if (r2 == 0) goto L_0x01f8
            gnu.bytecode.Field r2 = r14.field
            java.lang.String r2 = r2.getName()
            boolean r2 = r2.equals(r7)
            if (r2 == 0) goto L_0x01f8
            gnu.bytecode.Field r0 = r14.field
            r2 = r24
            r17 = r0
            r20 = r12
            goto L_0x016c
        L_0x01f8:
            gnu.bytecode.Field r2 = r14.field
            if (r2 == 0) goto L_0x020a
            gnu.bytecode.Field r2 = r14.field
            java.lang.String r2 = r2.getName()
            boolean r2 = r2.endsWith(r7)
            if (r2 == 0) goto L_0x020a
            r2 = 1
            goto L_0x020b
        L_0x020a:
            r2 = 0
        L_0x020b:
            int r7 = r13.getNamespaceOf(r14)
            gnu.expr.Declaration r7 = r3.lookup(r0, r13, r7)
            if (r2 == 0) goto L_0x0237
            if (r7 == 0) goto L_0x021b
            r8 = r12
            r16 = r13
            goto L_0x024e
        L_0x021b:
            gnu.expr.Declaration r0 = r3.addDeclaration((java.lang.Object) r0)
            r7 = 1073758208(0x40004000, double:5.305070425E-315)
            r0.setFlag(r7)
            gnu.bytecode.Type r7 = r14.getType()
            r0.setType(r7)
            r7 = 8192(0x2000, double:4.0474E-320)
            r0.setFlag(r7)
            r20 = r12
            r16 = r13
            r8 = 1
            goto L_0x027a
        L_0x0237:
            r8 = r12
            r16 = r13
            if (r7 == 0) goto L_0x0254
            r12 = 512(0x200, double:2.53E-321)
            boolean r12 = r7.getFlag(r12)
            if (r12 != 0) goto L_0x0254
            gnu.expr.Declaration r12 = gnu.expr.Declaration.followAliases(r7)
            gnu.expr.Declaration r13 = gnu.expr.Declaration.followAliases(r14)
            if (r12 != r13) goto L_0x0254
        L_0x024e:
            r2 = r24
            r20 = r8
            goto L_0x016e
        L_0x0254:
            if (r7 == 0) goto L_0x0266
            r12 = 66048(0x10200, double:3.2632E-319)
            boolean r18 = r7.getFlag(r12)
            if (r18 == 0) goto L_0x0266
            r20 = r8
            r8 = 0
            r7.setFlag(r8, r12)
            goto L_0x0272
        L_0x0266:
            r20 = r8
            gnu.expr.Declaration r0 = r3.addDeclaration((java.lang.Object) r0)
            if (r7 == 0) goto L_0x0271
            gnu.expr.ScopeExp.duplicateDeclarationError(r7, r0, r4)
        L_0x0271:
            r7 = r0
        L_0x0272:
            r8 = 1
            r7.setAlias(r8)
            r7.setIndirectBinding(r8)
            r0 = r7
        L_0x027a:
            r0.setLocation(r4)
            gnu.expr.ReferenceExp r7 = new gnu.expr.ReferenceExp
            r7.<init>((gnu.expr.Declaration) r14)
            r7.setContextDecl(r9)
            if (r2 != 0) goto L_0x028f
            r7.setDontDereference(r8)
            if (r11 != 0) goto L_0x028f
            r0.setPrivate(r8)
        L_0x028f:
            r12 = 16384(0x4000, double:8.0948E-320)
            r0.setFlag(r12)
            r12 = 32768(0x8000, double:1.61895E-319)
            boolean r8 = r14.getFlag(r12)
            if (r8 == 0) goto L_0x02a0
            r0.setFlag(r12)
        L_0x02a0:
            boolean r8 = r14.isProcedureDecl()
            if (r8 == 0) goto L_0x02ab
            r8 = 1
            r0.setProcedureDecl(r8)
            goto L_0x02ac
        L_0x02ab:
            r8 = 1
        L_0x02ac:
            if (r19 == 0) goto L_0x02b3
            r12 = 2048(0x800, double:1.0118E-320)
            r0.setFlag(r12)
        L_0x02b3:
            gnu.expr.SetExp r12 = new gnu.expr.SetExp
            r12.<init>((gnu.expr.Declaration) r0, (gnu.expr.Expression) r7)
            r18 = r9
            r13 = r10
            r9 = 536870912(0x20000000, double:2.652494739E-315)
            r0.setFlag(r9)
            r12.setDefining(r8)
            if (r2 == 0) goto L_0x02ce
            r2 = r24
            r2.insertElementAt(r12, r1)
            int r1 = r1 + 1
            goto L_0x02d3
        L_0x02ce:
            r2 = r24
            r2.addElement(r12)
        L_0x02d3:
            r15.add(r0)
            r15.add(r14)
            r0.noteValue(r7)
            r7 = 131072(0x20000, double:6.47582E-319)
            r0.setFlag(r7)
            r4.push((gnu.expr.Declaration) r0)
            r9 = r18
        L_0x02e7:
            gnu.expr.Declaration r14 = r14.nextDecl()
            r10 = r13
            r13 = r16
            r12 = r20
            r8 = 101(0x65, float:1.42E-43)
            r16 = r1
            r1 = r23
            goto L_0x012a
        L_0x02f8:
            r18 = r9
            r20 = r12
            int r0 = r15.size()
            r1 = 0
        L_0x0301:
            if (r1 >= r0) goto L_0x036d
            java.lang.Object r8 = r15.elementAt(r1)
            gnu.expr.Declaration r8 = (gnu.expr.Declaration) r8
            int r9 = r1 + 1
            java.lang.Object r9 = r15.elementAt(r9)
            gnu.expr.Declaration r9 = (gnu.expr.Declaration) r9
            gnu.expr.Expression r10 = r9.getValue()
            boolean r9 = r9.isIndirectBinding()
            if (r9 == 0) goto L_0x0366
            boolean r9 = r10 instanceof gnu.expr.ReferenceExp
            if (r9 == 0) goto L_0x0366
            gnu.expr.Expression r8 = r8.getValue()
            gnu.expr.ReferenceExp r8 = (gnu.expr.ReferenceExp) r8
            gnu.expr.ReferenceExp r10 = (gnu.expr.ReferenceExp) r10
            gnu.expr.Declaration r9 = r10.getBinding()
            r8.setBinding(r9)
            boolean r10 = r9.needsContext()
            if (r10 == 0) goto L_0x0366
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            gnu.bytecode.Field r9 = r9.field
            gnu.bytecode.ClassType r9 = r9.getDeclaringClass()
            java.lang.String r9 = r9.getName()
            r11 = 36
            r12 = 46
            java.lang.String r9 = r9.replace(r12, r11)
            r10.append(r9)
            r10.append(r7)
            java.lang.String r9 = r10.toString()
            gnu.mapping.SimpleSymbol r9 = gnu.mapping.SimpleSymbol.valueOf(r9)
            gnu.expr.Declaration r9 = r3.lookup(r9)
            r13 = 1024(0x400, double:5.06E-321)
            r9.setFlag(r13)
            r8.setContextDecl(r9)
            goto L_0x036a
        L_0x0366:
            r11 = 36
            r12 = 46
        L_0x036a:
            int r1 = r1 + 2
            goto L_0x0301
        L_0x036d:
            if (r6 == 0) goto L_0x03b2
            gnu.bytecode.ClassType r0 = gnu.expr.Compilation.typeRunnable
            java.lang.String r1 = "run"
            r3 = 0
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r1, (int) r3)
            if (r18 == 0) goto L_0x0383
            gnu.expr.ReferenceExp r12 = new gnu.expr.ReferenceExp
            r9 = r18
            r12.<init>((gnu.expr.Declaration) r9)
            r6 = 1
            goto L_0x03a2
        L_0x0383:
            if (r17 == 0) goto L_0x039f
            r1 = 2
            gnu.expr.Expression[] r1 = new gnu.expr.Expression[r1]
            gnu.expr.QuoteExp r6 = new gnu.expr.QuoteExp
            r6.<init>(r5)
            r1[r3] = r6
            gnu.expr.QuoteExp r5 = new gnu.expr.QuoteExp
            r5.<init>(r7)
            r6 = 1
            r1[r6] = r5
            gnu.expr.ApplyExp r12 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.SlotGet r5 = gnu.kawa.reflect.SlotGet.staticField
            r12.<init>((gnu.mapping.Procedure) r5, (gnu.expr.Expression[]) r1)
            goto L_0x03a2
        L_0x039f:
            r6 = 1
            r12 = r20
        L_0x03a2:
            gnu.expr.ApplyExp r1 = new gnu.expr.ApplyExp
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r6]
            r5[r3] = r12
            r1.<init>((gnu.bytecode.Method) r0, (gnu.expr.Expression[]) r5)
            r1.setLine((gnu.expr.Compilation) r4)
            r2.addElement(r1)
            goto L_0x03b3
        L_0x03b2:
            r6 = 1
        L_0x03b3:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.require.importDefinitions(java.lang.String, gnu.expr.ModuleInfo, gnu.mapping.Procedure, java.util.Vector, gnu.expr.ScopeExp, gnu.expr.Compilation):boolean");
    }
}
