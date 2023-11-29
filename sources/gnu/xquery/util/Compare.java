package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class Compare extends Procedure2 {
    public static final Compare $Eq = make("=", 8);
    public static final Compare $Ex$Eq = make("!=", 23);
    public static final Compare $Gr = make(">", 16);
    public static final Compare $Gr$Eq = make(">=", 24);
    public static final Compare $Ls = make("<", 4);
    public static final Compare $Ls$Eq = make("<=", 12);
    static final int LENIENT_COMPARISON = 64;
    static final int LENIENT_EQ = 72;
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int RESULT_NAN = -2;
    static final int RESULT_NEQ = -3;
    static final int TRUE_IF_EQU = 8;
    static final int TRUE_IF_GRT = 16;
    static final int TRUE_IF_LSS = 4;
    static final int TRUE_IF_NAN = 2;
    static final int TRUE_IF_NEQ = 1;
    static final int VALUE_COMPARISON = 32;
    public static final Compare valEq = make("eq", 40);
    public static final Compare valGe = make("ge", 56);
    public static final Compare valGt = make("gt", 48);
    public static final Compare valLe = make("le", 44);
    public static final Compare valLt = make("lt", 36);
    public static final Compare valNe = make("ne", 55);
    int flags;

    public static boolean equalityComparison(int i) {
        return ((i & 16) != 0) == ((i & 4) != 0);
    }

    public static Compare make(String str, int i) {
        Compare compare = new Compare();
        compare.setName(str);
        compare.setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateCompare");
        compare.flags = i;
        return compare;
    }

    public static boolean apply(int i, Object obj, Object obj2, NamedCollator namedCollator) {
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int i2 = 0;
            while (true) {
                int nextDataIndex = values.nextDataIndex(i2);
                if (nextDataIndex < 0) {
                    return false;
                }
                if (apply(i, values.getPosNext(i2 << 1), obj2, namedCollator)) {
                    return true;
                }
                i2 = nextDataIndex;
            }
        } else if (!(obj2 instanceof Values)) {
            return atomicCompare(i, KNode.atomicValue(obj), KNode.atomicValue(obj2), namedCollator);
        } else {
            Values values2 = (Values) obj2;
            int i3 = 0;
            while (true) {
                int nextDataIndex2 = values2.nextDataIndex(i3);
                if (nextDataIndex2 < 0) {
                    return false;
                }
                if (apply(i, obj, values2.getPosNext(i3 << 1), namedCollator)) {
                    return true;
                }
                i3 = nextDataIndex2;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c4, code lost:
        if (r8 != false) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c8, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e9, code lost:
        if (r7 > 0) goto L_0x00c8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean atomicCompare(int r6, java.lang.Object r7, java.lang.Object r8, gnu.xquery.util.NamedCollator r9) {
        /*
            boolean r0 = r7 instanceof gnu.kawa.xml.UntypedAtomic
            if (r0 == 0) goto L_0x0042
            java.lang.String r7 = r7.toString()
            r0 = r6 & 32
            if (r0 == 0) goto L_0x000d
            goto L_0x0042
        L_0x000d:
            boolean r0 = r8 instanceof gnu.math.DateTime
            if (r0 == 0) goto L_0x001d
            r0 = r8
            gnu.math.DateTime r0 = (gnu.math.DateTime) r0
            int r0 = r0.components()
            gnu.math.DateTime r7 = gnu.kawa.xml.XTimeType.parseDateTime(r7, r0)
            goto L_0x0042
        L_0x001d:
            boolean r0 = r8 instanceof gnu.math.Duration
            if (r0 == 0) goto L_0x002d
            r0 = r8
            gnu.math.Duration r0 = (gnu.math.Duration) r0
            gnu.math.Unit r0 = r0.unit()
            gnu.math.Duration r7 = gnu.math.Duration.parse(r7, r0)
            goto L_0x0042
        L_0x002d:
            boolean r0 = r8 instanceof java.lang.Number
            if (r0 == 0) goto L_0x0038
            gnu.math.DFloNum r0 = new gnu.math.DFloNum
            r0.<init>((java.lang.String) r7)
            r7 = r0
            goto L_0x0042
        L_0x0038:
            boolean r0 = r8 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x0042
            gnu.kawa.xml.XDataType r0 = gnu.kawa.xml.XDataType.booleanType
            java.lang.Object r7 = r0.valueOf(r7)
        L_0x0042:
            boolean r0 = r8 instanceof gnu.kawa.xml.UntypedAtomic
            if (r0 == 0) goto L_0x0084
            java.lang.String r8 = r8.toString()
            r0 = r6 & 32
            if (r0 == 0) goto L_0x004f
            goto L_0x0084
        L_0x004f:
            boolean r0 = r7 instanceof gnu.math.DateTime
            if (r0 == 0) goto L_0x005f
            r0 = r7
            gnu.math.DateTime r0 = (gnu.math.DateTime) r0
            int r0 = r0.components()
            gnu.math.DateTime r8 = gnu.kawa.xml.XTimeType.parseDateTime(r8, r0)
            goto L_0x0084
        L_0x005f:
            boolean r0 = r7 instanceof gnu.math.Duration
            if (r0 == 0) goto L_0x006f
            r0 = r7
            gnu.math.Duration r0 = (gnu.math.Duration) r0
            gnu.math.Unit r0 = r0.unit()
            gnu.math.Duration r8 = gnu.math.Duration.parse(r8, r0)
            goto L_0x0084
        L_0x006f:
            boolean r0 = r7 instanceof java.lang.Number
            if (r0 == 0) goto L_0x007a
            gnu.math.DFloNum r0 = new gnu.math.DFloNum
            r0.<init>((java.lang.String) r8)
            r8 = r0
            goto L_0x0084
        L_0x007a:
            boolean r0 = r7 instanceof java.lang.Boolean
            if (r0 == 0) goto L_0x0084
            gnu.kawa.xml.XDataType r0 = gnu.kawa.xml.XDataType.booleanType
            java.lang.Object r8 = r0.valueOf(r8)
        L_0x0084:
            boolean r0 = r7 instanceof java.lang.Number
            java.lang.String r1 = "values cannot be compared"
            r2 = 0
            r3 = -3
            if (r0 != 0) goto L_0x00fe
            boolean r0 = r8 instanceof java.lang.Number
            if (r0 == 0) goto L_0x0092
            goto L_0x00fe
        L_0x0092:
            boolean r0 = r7 instanceof gnu.mapping.Symbol
            if (r0 == 0) goto L_0x00ab
            boolean r9 = r8 instanceof gnu.mapping.Symbol
            if (r9 == 0) goto L_0x00a9
            boolean r9 = equalityComparison(r6)
            if (r9 == 0) goto L_0x00a9
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00a7
            goto L_0x00ec
        L_0x00a7:
            r2 = -2
            goto L_0x00ec
        L_0x00a9:
            r2 = -3
            goto L_0x00ec
        L_0x00ab:
            boolean r0 = r7 instanceof java.lang.Boolean
            r4 = -1
            r5 = 1
            if (r0 == 0) goto L_0x00ca
            boolean r9 = r8 instanceof java.lang.Boolean
            if (r9 == 0) goto L_0x00a9
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r7 != r8) goto L_0x00c4
            goto L_0x00ec
        L_0x00c4:
            if (r8 == 0) goto L_0x00c8
        L_0x00c6:
            r2 = -1
            goto L_0x00ec
        L_0x00c8:
            r2 = 1
            goto L_0x00ec
        L_0x00ca:
            boolean r0 = r8 instanceof java.lang.Boolean
            if (r0 != 0) goto L_0x00a9
            boolean r0 = r8 instanceof gnu.mapping.Symbol
            if (r0 == 0) goto L_0x00d3
            goto L_0x00a9
        L_0x00d3:
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = r8.toString()
            if (r9 == 0) goto L_0x00e2
            int r7 = r9.compare(r7, r8)
            goto L_0x00e6
        L_0x00e2:
            int r7 = gnu.xquery.util.NamedCollator.codepointCompare(r7, r8)
        L_0x00e6:
            if (r7 >= 0) goto L_0x00e9
            goto L_0x00c6
        L_0x00e9:
            if (r7 <= 0) goto L_0x00ec
            goto L_0x00c8
        L_0x00ec:
            if (r2 != r3) goto L_0x00f9
            r7 = r6 & 64
            if (r7 == 0) goto L_0x00f3
            goto L_0x00f9
        L_0x00f3:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r1)
            throw r6
        L_0x00f9:
            boolean r6 = gnu.kawa.functions.NumberCompare.checkCompareCode(r2, r6)
            return r6
        L_0x00fe:
            boolean r9 = r7 instanceof gnu.math.Duration
            if (r9 == 0) goto L_0x0124
            boolean r9 = r8 instanceof gnu.math.Duration
            if (r9 != 0) goto L_0x0108
        L_0x0106:
            r7 = -3
            goto L_0x0161
        L_0x0108:
            gnu.math.Duration r7 = (gnu.math.Duration) r7
            gnu.math.Duration r8 = (gnu.math.Duration) r8
            gnu.math.Unit r9 = r7.unit
            gnu.math.Unit r0 = r8.unit
            if (r9 != r0) goto L_0x0118
            gnu.math.Unit r9 = r7.unit
            gnu.math.BaseUnit r0 = gnu.math.Unit.duration
            if (r9 != r0) goto L_0x011f
        L_0x0118:
            boolean r9 = equalityComparison(r6)
            if (r9 != 0) goto L_0x011f
            goto L_0x0106
        L_0x011f:
            int r7 = gnu.math.Duration.compare(r7, r8)
            goto L_0x0161
        L_0x0124:
            boolean r9 = r7 instanceof gnu.math.DateTime
            if (r9 == 0) goto L_0x0154
            boolean r9 = r8 instanceof gnu.math.DateTime
            if (r9 != 0) goto L_0x012d
            goto L_0x0106
        L_0x012d:
            gnu.math.DateTime r7 = (gnu.math.DateTime) r7
            gnu.math.DateTime r8 = (gnu.math.DateTime) r8
            int r9 = r7.components()
            int r0 = r8.components()
            if (r9 == r0) goto L_0x013c
            goto L_0x0106
        L_0x013c:
            boolean r0 = equalityComparison(r6)
            if (r0 != 0) goto L_0x014f
            r0 = 112(0x70, float:1.57E-43)
            if (r9 == r0) goto L_0x014f
            r0 = 14
            if (r9 == r0) goto L_0x014f
            r0 = 126(0x7e, float:1.77E-43)
            if (r9 == r0) goto L_0x014f
            goto L_0x0106
        L_0x014f:
            int r7 = gnu.math.DateTime.compare(r7, r8)
            goto L_0x0161
        L_0x0154:
            boolean r9 = r8 instanceof gnu.math.Duration
            if (r9 != 0) goto L_0x0106
            boolean r9 = r8 instanceof gnu.math.DateTime
            if (r9 == 0) goto L_0x015d
            goto L_0x0106
        L_0x015d:
            int r7 = gnu.kawa.functions.NumberCompare.compare(r7, r8, r2)
        L_0x0161:
            if (r7 != r3) goto L_0x016e
            r8 = r6 & 64
            if (r8 == 0) goto L_0x0168
            goto L_0x016e
        L_0x0168:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            r6.<init>(r1)
            throw r6
        L_0x016e:
            boolean r6 = gnu.kawa.functions.NumberCompare.checkCompareCode(r7, r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.Compare.atomicCompare(int, java.lang.Object, java.lang.Object, gnu.xquery.util.NamedCollator):boolean");
    }

    public Object apply2(Object obj, Object obj2) {
        int i = this.flags;
        if ((i & 32) == 0) {
            return apply(i, obj, obj2, (NamedCollator) null) ? Boolean.TRUE : Boolean.FALSE;
        }
        if (obj == null || obj == Values.empty) {
            return obj;
        }
        if (obj2 == null || obj2 == Values.empty) {
            return obj2;
        }
        return atomicCompare(this.flags, KNode.atomicValue(obj), KNode.atomicValue(obj2), (NamedCollator) null) ? Boolean.TRUE : Boolean.FALSE;
    }
}
