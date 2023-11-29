package kawa.standard;

import kawa.lang.Syntax;

public class module_name extends Syntax {
    public static final module_name module_name;

    static {
        module_name module_name2 = new module_name();
        module_name = module_name2;
        module_name2.setName("module-name");
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void scanForm(gnu.lists.Pair r10, gnu.expr.ScopeExp r11, kawa.lang.Translator r12) {
        /*
            r9 = this;
            java.lang.Object r10 = r10.getCdr()
            r0 = 0
            r1 = r0
        L_0x0006:
            boolean r2 = r10 instanceof kawa.lang.SyntaxForm
            if (r2 == 0) goto L_0x0012
            r1 = r10
            kawa.lang.SyntaxForm r1 = (kawa.lang.SyntaxForm) r1
            java.lang.Object r10 = r1.getDatum()
            goto L_0x0006
        L_0x0012:
            boolean r2 = r10 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x001d
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            java.lang.Object r10 = r10.getCar()
            goto L_0x001e
        L_0x001d:
            r10 = r0
        L_0x001e:
            boolean r2 = r10 instanceof kawa.lang.SyntaxForm
            if (r2 == 0) goto L_0x002a
            r1 = r10
            kawa.lang.SyntaxForm r1 = (kawa.lang.SyntaxForm) r1
            java.lang.Object r10 = r1.getDatum()
            goto L_0x001e
        L_0x002a:
            boolean r2 = r10 instanceof gnu.lists.Pair
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0063
            r2 = r10
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            java.lang.Object r5 = r2.getCar()
            java.lang.String r6 = "quote"
            if (r5 != r6) goto L_0x0063
            java.lang.Object r10 = r2.getCdr()
            boolean r11 = r10 instanceof gnu.lists.Pair
            if (r11 == 0) goto L_0x005d
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            java.lang.Object r11 = r10.getCdr()
            gnu.lists.LList r1 = gnu.lists.LList.Empty
            if (r11 != r1) goto L_0x005d
            java.lang.Object r11 = r10.getCar()
            boolean r11 = r11 instanceof java.lang.String
            if (r11 != 0) goto L_0x0056
            goto L_0x005d
        L_0x0056:
            java.lang.Object r10 = r10.getCar()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x009e
        L_0x005d:
            java.lang.String r10 = "invalid quoted symbol for 'module-name'"
        L_0x005f:
            r11 = r0
            r0 = r10
            r10 = r11
            goto L_0x009f
        L_0x0063:
            boolean r2 = r10 instanceof gnu.lists.FString
            if (r2 != 0) goto L_0x009a
            boolean r2 = r10 instanceof java.lang.String
            if (r2 == 0) goto L_0x006c
            goto L_0x009a
        L_0x006c:
            boolean r2 = r10 instanceof gnu.mapping.Symbol
            if (r2 == 0) goto L_0x0097
            java.lang.String r2 = r10.toString()
            int r5 = r2.length()
            r6 = 2
            if (r5 <= r6) goto L_0x0090
            char r6 = r2.charAt(r3)
            r7 = 60
            if (r6 != r7) goto L_0x0090
            int r5 = r5 - r4
            char r6 = r2.charAt(r5)
            r7 = 62
            if (r6 != r7) goto L_0x0090
            java.lang.String r2 = r2.substring(r4, r5)
        L_0x0090:
            gnu.expr.Declaration r10 = r12.define(r10, r1, r11)
            r11 = r10
            r10 = r2
            goto L_0x009f
        L_0x0097:
            java.lang.String r10 = "un-implemented expression in module-name"
            goto L_0x005f
        L_0x009a:
            java.lang.String r10 = r10.toString()
        L_0x009e:
            r11 = r0
        L_0x009f:
            if (r0 == 0) goto L_0x00ac
            java.util.Stack r10 = r12.formStack
            gnu.expr.Expression r11 = r12.syntaxError(r0)
            r10.add(r11)
            goto L_0x014e
        L_0x00ac:
            r0 = 46
            int r0 = r10.lastIndexOf(r0)
            if (r0 < 0) goto L_0x00bd
            int r0 = r0 + r4
            java.lang.String r0 = r10.substring(r3, r0)
            r12.classPrefix = r0
            r0 = r10
            goto L_0x00e6
        L_0x00bd:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r12.classPrefix
            r0.append(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r12.classPrefix
            r0.append(r1)
            java.lang.String r1 = gnu.expr.Compilation.mangleName(r10)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8 = r0
            r0 = r10
            r10 = r8
        L_0x00e6:
            gnu.expr.ModuleExp r1 = r12.getModule()
            gnu.bytecode.ClassType r2 = r12.mainClass
            if (r2 != 0) goto L_0x00f6
            gnu.bytecode.ClassType r2 = new gnu.bytecode.ClassType
            r2.<init>(r10)
            r12.mainClass = r2
            goto L_0x011e
        L_0x00f6:
            gnu.bytecode.ClassType r2 = r12.mainClass
            java.lang.String r2 = r2.getName()
            if (r2 != 0) goto L_0x0104
            gnu.bytecode.ClassType r2 = r12.mainClass
            r2.setName(r10)
            goto L_0x011e
        L_0x0104:
            boolean r10 = r2.equals(r10)
            if (r10 != 0) goto L_0x011e
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r3 = "duplicate module-name: old name: "
            r10.append(r3)
            r10.append(r2)
            java.lang.String r10 = r10.toString()
            r12.syntaxError(r10)
        L_0x011e:
            gnu.bytecode.ClassType r10 = r12.mainClass
            r1.setType(r10)
            r1.setName(r0)
            if (r11 == 0) goto L_0x014b
            gnu.expr.QuoteExp r10 = new gnu.expr.QuoteExp
            gnu.bytecode.ClassType r0 = r12.mainClass
            gnu.bytecode.ClassType r2 = gnu.expr.Compilation.typeClass
            r10.<init>(r0, r2)
            r11.noteValue(r10)
            r2 = 16793600(0x1004000, double:8.297141E-317)
            r11.setFlag(r2)
            gnu.expr.ScopeExp r10 = r1.outer
            if (r10 != 0) goto L_0x0143
            r0 = 2048(0x800, double:1.0118E-320)
            r11.setFlag(r0)
        L_0x0143:
            r11.setPrivate(r4)
            gnu.bytecode.ClassType r10 = gnu.expr.Compilation.typeClass
            r11.setType(r10)
        L_0x014b:
            r12.mustCompileHere()
        L_0x014e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.module_name.scanForm(gnu.lists.Pair, gnu.expr.ScopeExp, kawa.lang.Translator):void");
    }
}
