package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure2;

public class IsEqual extends Procedure2 {
    Language language;

    public IsEqual(Language language2, String str) {
        this.language = language2;
        setName(str);
    }

    public static boolean numberEquals(Number number, Number number2) {
        boolean isExact = Arithmetic.isExact(number);
        boolean isExact2 = Arithmetic.isExact(number2);
        if (!isExact || !isExact2) {
            return isExact == isExact2 && number.equals(number2);
        }
        return NumberCompare.$Eq(number, number2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00b3, code lost:
        return apply(r5, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean apply(java.lang.Object r5, java.lang.Object r6) {
        /*
            r0 = 1
            if (r5 != r6) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 == 0) goto L_0x00ba
            if (r6 != 0) goto L_0x000b
            goto L_0x00ba
        L_0x000b:
            boolean r2 = r5 instanceof java.lang.Number
            if (r2 == 0) goto L_0x001c
            boolean r2 = r6 instanceof java.lang.Number
            if (r2 == 0) goto L_0x001c
            java.lang.Number r5 = (java.lang.Number) r5
            java.lang.Number r6 = (java.lang.Number) r6
            boolean r5 = numberEquals(r5, r6)
            return r5
        L_0x001c:
            boolean r2 = r5 instanceof java.lang.CharSequence
            if (r2 == 0) goto L_0x0044
            boolean r2 = r6 instanceof java.lang.CharSequence
            if (r2 != 0) goto L_0x0025
            return r1
        L_0x0025:
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            int r2 = r5.length()
            int r3 = r6.length()
            if (r2 == r3) goto L_0x0034
            return r1
        L_0x0034:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x0043
            char r3 = r5.charAt(r2)
            char r4 = r6.charAt(r2)
            if (r3 == r4) goto L_0x0034
            return r1
        L_0x0043:
            return r0
        L_0x0044:
            boolean r2 = r5 instanceof gnu.lists.FVector
            if (r2 == 0) goto L_0x0071
            boolean r2 = r6 instanceof gnu.lists.FVector
            if (r2 != 0) goto L_0x004d
            return r1
        L_0x004d:
            gnu.lists.FVector r5 = (gnu.lists.FVector) r5
            gnu.lists.FVector r6 = (gnu.lists.FVector) r6
            int r2 = r5.size
            java.lang.Object[] r3 = r6.data
            if (r3 == 0) goto L_0x0070
            int r3 = r6.size
            if (r3 == r2) goto L_0x005c
            goto L_0x0070
        L_0x005c:
            java.lang.Object[] r5 = r5.data
            java.lang.Object[] r6 = r6.data
        L_0x0060:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x006f
            r3 = r5[r2]
            r4 = r6[r2]
            boolean r3 = apply(r3, r4)
            if (r3 != 0) goto L_0x0060
            return r1
        L_0x006f:
            return r0
        L_0x0070:
            return r1
        L_0x0071:
            boolean r2 = r5 instanceof gnu.lists.LList
            if (r2 == 0) goto L_0x00b5
            boolean r2 = r5 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x00b4
            boolean r2 = r6 instanceof gnu.lists.Pair
            if (r2 != 0) goto L_0x007e
            goto L_0x00b4
        L_0x007e:
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6
        L_0x0082:
            java.lang.Object r2 = r5.getCar()
            java.lang.Object r3 = r6.getCar()
            boolean r2 = apply(r2, r3)
            if (r2 != 0) goto L_0x0091
            return r1
        L_0x0091:
            java.lang.Object r5 = r5.getCdr()
            java.lang.Object r6 = r6.getCdr()
            if (r5 != r6) goto L_0x009c
            return r0
        L_0x009c:
            if (r5 == 0) goto L_0x00b4
            if (r6 != 0) goto L_0x00a1
            goto L_0x00b4
        L_0x00a1:
            boolean r2 = r5 instanceof gnu.lists.Pair
            if (r2 == 0) goto L_0x00af
            boolean r2 = r6 instanceof gnu.lists.Pair
            if (r2 != 0) goto L_0x00aa
            goto L_0x00af
        L_0x00aa:
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6
            goto L_0x0082
        L_0x00af:
            boolean r5 = apply(r5, r6)
            return r5
        L_0x00b4:
            return r1
        L_0x00b5:
            boolean r5 = r5.equals(r6)
            return r5
        L_0x00ba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.IsEqual.apply(java.lang.Object, java.lang.Object):boolean");
    }

    public Object apply2(Object obj, Object obj2) {
        return this.language.booleanObject(apply(obj, obj2));
    }
}
