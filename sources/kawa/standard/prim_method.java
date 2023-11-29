package kawa.standard;

import kawa.lang.ListPat;
import kawa.lang.Pattern;
import kawa.lang.Syntax;

public class prim_method extends Syntax {
    public static final prim_method interface_method;
    public static final prim_method op1;
    private static Pattern pattern3 = new ListPat(3);
    private static Pattern pattern4 = new ListPat(4);
    public static final prim_method static_method;
    public static final prim_method virtual_method;
    int op_code;

    static {
        prim_method prim_method = new prim_method(182);
        virtual_method = prim_method;
        prim_method.setName("primitive-virtual-method");
        prim_method prim_method2 = new prim_method(184);
        static_method = prim_method2;
        prim_method2.setName("primitive-static-method");
        prim_method prim_method3 = new prim_method(185);
        interface_method = prim_method3;
        prim_method3.setName("primitive-interface-method");
        prim_method prim_method4 = new prim_method();
        op1 = prim_method4;
        prim_method4.setName("primitive-op1");
    }

    /* access modifiers changed from: package-private */
    public int opcode() {
        return this.op_code;
    }

    public prim_method(int i) {
        this.op_code = i;
    }

    public prim_method() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewrite(java.lang.Object r12, kawa.lang.Translator r13) {
        /*
            r11 = this;
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]
            int r1 = r11.op_code
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0012
            kawa.lang.Pattern r1 = pattern3
            boolean r1 = r1.match(r12, r0, r3)
            if (r1 == 0) goto L_0x001a
            goto L_0x0043
        L_0x0012:
            kawa.lang.Pattern r1 = pattern4
            boolean r1 = r1.match(r12, r0, r2)
            if (r1 != 0) goto L_0x0043
        L_0x001a:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "wrong number of arguments to "
            r12.append(r0)
            java.lang.String r0 = r11.getName()
            r12.append(r0)
            java.lang.String r0 = "(opcode:"
            r12.append(r0)
            int r0 = r11.op_code
            r12.append(r0)
            java.lang.String r0 = ")"
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            gnu.expr.Expression r12 = r13.syntaxError(r12)
            return r12
        L_0x0043:
            r1 = 3
            r1 = r0[r1]
            boolean r4 = r1 instanceof gnu.lists.LList
            if (r4 != 0) goto L_0x0064
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "missing/invalid parameter list in "
            r12.append(r0)
            java.lang.String r0 = r11.getName()
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            gnu.expr.Expression r12 = r13.syntaxError(r12)
            return r12
        L_0x0064:
            gnu.lists.LList r1 = (gnu.lists.LList) r1
            int r4 = r1.size()
            gnu.bytecode.Type[] r10 = new gnu.bytecode.Type[r4]
            r5 = 0
        L_0x006d:
            if (r5 >= r4) goto L_0x0080
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            gnu.bytecode.Type r6 = r13.exp2Type(r1)
            r10[r5] = r6
            java.lang.Object r1 = r1.getCdr()
            gnu.lists.LList r1 = (gnu.lists.LList) r1
            int r5 = r5 + 1
            goto L_0x006d
        L_0x0080:
            gnu.lists.Pair r1 = new gnu.lists.Pair
            r4 = 2
            r4 = r0[r4]
            r5 = 0
            r1.<init>(r4, r5)
            gnu.bytecode.Type r9 = r13.exp2Type(r1)
            int r1 = r11.op_code
            if (r1 != 0) goto L_0x00a2
            r12 = r0[r3]
            java.lang.Number r12 = (java.lang.Number) r12
            r13 = r12
            java.lang.Number r13 = (java.lang.Number) r13
            int r12 = r12.intValue()
            gnu.expr.PrimProcedure r13 = new gnu.expr.PrimProcedure
            r13.<init>((int) r12, (gnu.bytecode.Type) r9, (gnu.bytecode.Type[]) r10)
            goto L_0x0103
        L_0x00a2:
            gnu.lists.Pair r12 = (gnu.lists.Pair) r12
            gnu.bytecode.Type r12 = r13.exp2Type(r12)
            if (r12 == 0) goto L_0x00ae
            gnu.bytecode.Type r12 = r12.getImplementationType()
        L_0x00ae:
            gnu.bytecode.ClassType r12 = (gnu.bytecode.ClassType) r12     // Catch:{ Exception -> 0x00b7 }
            r12.getReflectClass()     // Catch:{ Exception -> 0x00b5 }
            r7 = r12
            goto L_0x00d9
        L_0x00b5:
            r5 = r12
            goto L_0x00b8
        L_0x00b7:
        L_0x00b8:
            if (r5 != 0) goto L_0x00bd
            r12 = 101(0x65, float:1.42E-43)
            goto L_0x00c2
        L_0x00bd:
            r12 = 119(0x77, float:1.67E-43)
            r5.setExisting(r2)
        L_0x00c2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "unknown class: "
            r1.append(r4)
            r2 = r0[r2]
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r13.error(r12, r1)
            r7 = r5
        L_0x00d9:
            r12 = r0[r3]
            boolean r13 = r12 instanceof gnu.lists.Pair
            if (r13 == 0) goto L_0x00f5
            gnu.lists.Pair r12 = (gnu.lists.Pair) r12
            java.lang.Object r13 = r12.getCar()
            java.lang.String r1 = "quote"
            if (r13 != r1) goto L_0x00f5
            java.lang.Object r12 = r12.getCdr()
            gnu.lists.Pair r12 = (gnu.lists.Pair) r12
            java.lang.Object r12 = r12.getCar()
            r0[r3] = r12
        L_0x00f5:
            gnu.expr.PrimProcedure r13 = new gnu.expr.PrimProcedure
            int r6 = r11.op_code
            r12 = r0[r3]
            java.lang.String r8 = r12.toString()
            r5 = r13
            r5.<init>(r6, r7, r8, r9, r10)
        L_0x0103:
            gnu.expr.QuoteExp r12 = new gnu.expr.QuoteExp
            r12.<init>(r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.prim_method.rewrite(java.lang.Object, kawa.lang.Translator):gnu.expr.Expression");
    }
}
