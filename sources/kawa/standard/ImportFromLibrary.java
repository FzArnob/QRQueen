package kawa.standard;

import gnu.expr.Expression;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ImportFromLibrary extends Syntax {
    private static final String BUILTIN = "";
    private static final String MISSING = null;
    static final String[][] SRFI97Map = {new String[]{"1", "lists", "gnu.kawa.slib.srfi1"}, new String[]{"2", "and-let*", "gnu.kawa.slib.srfi2"}, new String[]{"5", "let", null}, new String[]{"6", "basic-string-ports", ""}, new String[]{"8", "receive", ""}, new String[]{"9", "records", ""}, new String[]{"11", "let-values", ""}, new String[]{"13", "strings", "gnu.kawa.slib.srfi13"}, new String[]{"14", "char-sets", null}, new String[]{"16", "case-lambda", ""}, new String[]{"17", "generalized-set!", ""}, new String[]{"18", "multithreading", null}, new String[]{"19", "time", null}, new String[]{"21", "real-time-multithreading", null}, new String[]{"23", "error", ""}, new String[]{"25", "multi-dimensional-arrays", ""}, new String[]{"26", "cut", ""}, new String[]{"27", "random-bits", null}, new String[]{"28", "basic-format-strings", ""}, new String[]{"29", "localization", null}, new String[]{"31", "rec", null}, new String[]{"38", "with-shared-structure", null}, new String[]{"39", "parameters", ""}, new String[]{"41", "streams", null}, new String[]{"42", "eager-comprehensions", null}, new String[]{"43", "vectors", null}, new String[]{"44", "collections", null}, new String[]{"45", "lazy", null}, new String[]{"46", "syntax-rules", null}, new String[]{"47", "arrays", null}, new String[]{"48", "intermediate-format-strings", null}, new String[]{"51", "rest-values", null}, new String[]{"54", "cat", null}, new String[]{"57", "records", null}, new String[]{"59", "vicinities", null}, new String[]{"60", "integer-bits", null}, new String[]{"61", "cond", null}, new String[]{"63", "arrays", null}, new String[]{"64", "testing", "gnu.kawa.slib.testing"}, new String[]{"66", "octet-vectors", null}, new String[]{"67", "compare-procedures", null}, new String[]{"69", "basic-hash-tables", "gnu.kawa.slib.srfi69"}, new String[]{"71", "let", null}, new String[]{"74", "blobs", null}, new String[]{"78", "lightweight-testing", null}, new String[]{"86", "mu-and-nu", null}, new String[]{"87", "case", null}, new String[]{"95", "sorting-and-merging", "kawa.lib.srfi95"}};
    public static final ImportFromLibrary instance = new ImportFromLibrary();
    public String[] classPrefixPath = {"", "kawa.lib."};

    public Expression rewriteForm(Pair pair, Translator translator) {
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanForDefinitions(gnu.lists.Pair r17, java.util.Vector r18, gnu.expr.ScopeExp r19, kawa.lang.Translator r20) {
        /*
            r16 = this;
            r0 = r16
            r6 = r20
            java.lang.Object r1 = r17.getCdr()
            boolean r2 = r1 instanceof gnu.lists.Pair
            r3 = 0
            if (r2 != 0) goto L_0x000e
            return r3
        L_0x000e:
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            java.lang.Object r2 = r1.getCar()
            int r4 = gnu.lists.LList.listLength(r2, r3)
            r5 = 101(0x65, float:1.42E-43)
            if (r4 > 0) goto L_0x0022
            java.lang.String r1 = "expected <library reference> which must be a list"
            r6.error(r5, r1)
            return r3
        L_0x0022:
            java.lang.Object r1 = r1.getCdr()
            boolean r4 = r1 instanceof gnu.lists.Pair
            r7 = 0
            if (r4 == 0) goto L_0x003d
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            java.lang.Object r4 = r1.getCar()
            boolean r4 = r4 instanceof gnu.mapping.Procedure
            if (r4 == 0) goto L_0x003d
            java.lang.Object r1 = r1.getCar()
            gnu.mapping.Procedure r1 = (gnu.mapping.Procedure) r1
            r4 = r1
            goto L_0x003e
        L_0x003d:
            r4 = r7
        L_0x003e:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r8 = r7
            r9 = r8
        L_0x0045:
            boolean r10 = r2 instanceof gnu.lists.Pair
            r11 = 46
            if (r10 == 0) goto L_0x00ad
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            java.lang.Object r10 = r2.getCar()
            java.lang.Object r2 = r2.getCdr()
            boolean r12 = r10 instanceof gnu.lists.Pair
            if (r12 == 0) goto L_0x0087
            if (r9 == 0) goto L_0x006f
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "duplicate version reference - was "
            r11.append(r12)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            r6.error(r5, r9)
        L_0x006f:
            java.io.PrintStream r9 = java.lang.System.err
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "import version "
            r11.append(r12)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            r9.println(r11)
            r9 = r10
            goto L_0x0045
        L_0x0087:
            boolean r12 = r10 instanceof java.lang.String
            if (r12 == 0) goto L_0x0098
            boolean r8 = r2 instanceof gnu.lists.Pair
            if (r8 == 0) goto L_0x0094
            java.lang.String r8 = "source specifier must be last elemnt in library reference"
            r6.error(r5, r8)
        L_0x0094:
            r8 = r10
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0045
        L_0x0098:
            int r12 = r1.length()
            if (r12 <= 0) goto L_0x00a1
            r1.append(r11)
        L_0x00a1:
            java.lang.String r10 = r10.toString()
            java.lang.String r10 = gnu.expr.Compilation.mangleNameIfNeeded(r10)
            r1.append(r10)
            goto L_0x0045
        L_0x00ad:
            r9 = r19
            if (r8 == 0) goto L_0x00cc
            gnu.expr.ModuleInfo r2 = kawa.standard.require.lookupModuleFromSourcePath(r8, r9)
            if (r2 != 0) goto L_0x00cd
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "malformed URL: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r6.error(r5, r1)
            return r3
        L_0x00cc:
            r2 = r7
        L_0x00cd:
            java.lang.String r1 = r1.toString()
            java.lang.String r8 = "srfi."
            boolean r8 = r1.startsWith(r8)
            r10 = 1
            if (r8 == 0) goto L_0x01ad
            r8 = 5
            java.lang.String r1 = r1.substring(r8)
            java.lang.String r8 = gnu.expr.Compilation.demangleName(r1)
            int r1 = r8.indexOf(r11)
            if (r1 >= 0) goto L_0x00f0
            int r1 = r8.length()
            r11 = r1
            r12 = r7
            goto L_0x00f8
        L_0x00f0:
            int r11 = r1 + 1
            java.lang.String r11 = r8.substring(r11)
            r12 = r11
            r11 = r1
        L_0x00f8:
            r13 = 2
            if (r11 >= r13) goto L_0x0103
            char r1 = r8.charAt(r3)
            r14 = 58
            if (r1 != r14) goto L_0x0117
        L_0x0103:
            r1 = 1
        L_0x0104:
            if (r1 != r11) goto L_0x010b
            java.lang.String r7 = r8.substring(r10, r11)
            goto L_0x0117
        L_0x010b:
            char r14 = r8.charAt(r1)
            r15 = 10
            int r14 = java.lang.Character.digit(r14, r15)
            if (r14 >= 0) goto L_0x01a9
        L_0x0117:
            r14 = r7
            if (r14 != 0) goto L_0x0120
            java.lang.String r1 = "SRFI library reference must have the form: (srfi :NNN [name])"
            r6.error(r5, r1)
            return r3
        L_0x0120:
            java.lang.String[][] r1 = SRFI97Map
            int r1 = r1.length
        L_0x0123:
            int r1 = r1 + -1
            if (r1 >= 0) goto L_0x0141
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown SRFI number '"
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = "' in SRFI library reference"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.error(r5, r1)
            return r3
        L_0x0141:
            java.lang.String[][] r7 = SRFI97Map
            r8 = r7[r1]
            r8 = r8[r3]
            boolean r8 = r8.equals(r14)
            if (r8 == 0) goto L_0x0123
            r1 = r7[r1]
            r7 = r1[r10]
            r1 = r1[r13]
            if (r12 == 0) goto L_0x017e
            boolean r8 = r12.equals(r7)
            if (r8 != 0) goto L_0x017e
            r8 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "the name of SRFI "
            r11.append(r12)
            r11.append(r14)
            java.lang.String r12 = " should be '"
            r11.append(r12)
            r11.append(r7)
            r12 = 39
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r6.error(r8, r11)
        L_0x017e:
            java.lang.String r8 = ""
            if (r1 != r8) goto L_0x0183
            return r10
        L_0x0183:
            java.lang.String r8 = MISSING
            if (r1 != r8) goto L_0x01ad
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "sorry - Kawa does not support SRFI "
            r1.append(r2)
            r1.append(r14)
            java.lang.String r2 = " ("
            r1.append(r2)
            r1.append(r7)
            r2 = 41
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.error(r5, r1)
            return r3
        L_0x01a9:
            int r1 = r1 + 1
            goto L_0x0104
        L_0x01ad:
            java.lang.String[] r7 = r0.classPrefixPath
            int r7 = r7.length
            r8 = 0
        L_0x01b1:
            if (r8 >= r7) goto L_0x01d1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String[] r12 = r0.classPrefixPath
            r12 = r12[r8]
            r11.append(r12)
            r11.append(r1)
            java.lang.String r11 = r11.toString()
            gnu.expr.ModuleManager r12 = gnu.expr.ModuleManager.getInstance()     // Catch:{ Exception -> 0x01ce }
            gnu.expr.ModuleInfo r2 = r12.findWithClassName(r11)     // Catch:{ Exception -> 0x01ce }
        L_0x01ce:
            int r8 = r8 + 1
            goto L_0x01b1
        L_0x01d1:
            if (r2 != 0) goto L_0x01e8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "unknown class "
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r6.error(r5, r1)
            return r3
        L_0x01e8:
            r1 = 0
            r3 = r4
            r4 = r18
            r5 = r19
            r6 = r20
            kawa.standard.require.importDefinitions(r1, r2, r3, r4, r5, r6)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.ImportFromLibrary.scanForDefinitions(gnu.lists.Pair, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }
}
