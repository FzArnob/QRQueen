package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;

public class OrderedTuples extends FilterConsumer {
    Procedure body;
    Object[] comps;
    int first;
    int n;
    int[] next;
    Object[] tuples = new Object[10];

    public void writeObject(Object obj) {
        int i = this.n;
        Object[] objArr = this.tuples;
        if (i >= objArr.length) {
            Object[] objArr2 = new Object[(i * 2)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            this.tuples = objArr2;
        }
        Object[] objArr3 = this.tuples;
        int i2 = this.n;
        this.n = i2 + 1;
        objArr3[i2] = obj;
    }

    OrderedTuples() {
        super((Consumer) null);
    }

    public static OrderedTuples make$V(Procedure procedure, Object[] objArr) {
        OrderedTuples orderedTuples = new OrderedTuples();
        orderedTuples.comps = objArr;
        orderedTuples.body = procedure;
        return orderedTuples;
    }

    public void run$X(CallContext callContext) throws Throwable {
        this.first = listsort(0);
        emit(callContext);
    }

    /* access modifiers changed from: package-private */
    public void emit(CallContext callContext) throws Throwable {
        int i = this.first;
        while (i >= 0) {
            emit(i, callContext);
            i = this.next[i];
        }
    }

    /* access modifiers changed from: package-private */
    public void emit(int i, CallContext callContext) throws Throwable {
        this.body.checkN((Object[]) this.tuples[i], callContext);
        callContext.runUntilDone();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b4, code lost:
        if (r8 == (r5.charAt(1) == 'L')) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c0, code lost:
        if (r7 == (r5.charAt(1) == 'L')) goto L_0x00c4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00cb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c7 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int cmp(int r16, int r17) throws java.lang.Throwable {
        /*
            r15 = this;
            r0 = r15
            r1 = 0
            r2 = 0
        L_0x0003:
            java.lang.Object[] r3 = r0.comps
            int r4 = r3.length
            if (r2 >= r4) goto L_0x00d6
            r4 = r3[r2]
            gnu.mapping.Procedure r4 = (gnu.mapping.Procedure) r4
            int r5 = r2 + 1
            r5 = r3[r5]
            java.lang.String r5 = (java.lang.String) r5
            int r6 = r2 + 2
            r3 = r3[r6]
            gnu.xquery.util.NamedCollator r3 = (gnu.xquery.util.NamedCollator) r3
            if (r3 != 0) goto L_0x001c
            gnu.xquery.util.NamedCollator r3 = gnu.xquery.util.NamedCollator.codepointCollation
        L_0x001c:
            java.lang.Object[] r6 = r0.tuples
            r6 = r6[r16]
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            java.lang.Object[] r6 = (java.lang.Object[]) r6
            java.lang.Object r6 = r4.applyN(r6)
            java.lang.Object[] r7 = r0.tuples
            r7 = r7[r17]
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            java.lang.Object[] r7 = (java.lang.Object[]) r7
            java.lang.Object r4 = r4.applyN(r7)
            java.lang.Object r6 = gnu.kawa.xml.KNode.atomicValue(r6)
            java.lang.Object r4 = gnu.kawa.xml.KNode.atomicValue(r4)
            boolean r7 = r6 instanceof gnu.kawa.xml.UntypedAtomic
            if (r7 == 0) goto L_0x0044
            java.lang.String r6 = r6.toString()
        L_0x0044:
            boolean r7 = r4 instanceof gnu.kawa.xml.UntypedAtomic
            if (r7 == 0) goto L_0x004c
            java.lang.String r4 = r4.toString()
        L_0x004c:
            boolean r7 = gnu.xquery.util.SequenceUtils.isEmptySequence(r6)
            boolean r8 = gnu.xquery.util.SequenceUtils.isEmptySequence(r4)
            if (r7 == 0) goto L_0x005a
            if (r8 == 0) goto L_0x005a
            goto L_0x00c7
        L_0x005a:
            r9 = -1
            r10 = 76
            r11 = 1
            if (r7 != 0) goto L_0x00b7
            if (r8 == 0) goto L_0x0063
            goto L_0x00b7
        L_0x0063:
            boolean r7 = r6 instanceof java.lang.Number
            if (r7 == 0) goto L_0x0076
            r8 = r6
            java.lang.Number r8 = (java.lang.Number) r8
            double r12 = r8.doubleValue()
            boolean r8 = java.lang.Double.isNaN(r12)
            if (r8 == 0) goto L_0x0076
            r8 = 1
            goto L_0x0077
        L_0x0076:
            r8 = 0
        L_0x0077:
            boolean r12 = r4 instanceof java.lang.Number
            if (r12 == 0) goto L_0x008a
            r13 = r4
            java.lang.Number r13 = (java.lang.Number) r13
            double r13 = r13.doubleValue()
            boolean r13 = java.lang.Double.isNaN(r13)
            if (r13 == 0) goto L_0x008a
            r13 = 1
            goto L_0x008b
        L_0x008a:
            r13 = 0
        L_0x008b:
            if (r8 == 0) goto L_0x0090
            if (r13 == 0) goto L_0x0090
            goto L_0x00c7
        L_0x0090:
            if (r8 != 0) goto L_0x00ab
            if (r13 == 0) goto L_0x0095
            goto L_0x00ab
        L_0x0095:
            if (r7 == 0) goto L_0x009e
            if (r12 == 0) goto L_0x009e
            int r3 = gnu.kawa.functions.NumberCompare.compare(r6, r4, r1)
            goto L_0x00c5
        L_0x009e:
            java.lang.String r6 = r6.toString()
            java.lang.String r4 = r4.toString()
            int r3 = r3.compare(r6, r4)
            goto L_0x00c5
        L_0x00ab:
            char r3 = r5.charAt(r11)
            if (r3 != r10) goto L_0x00b3
            r3 = 1
            goto L_0x00b4
        L_0x00b3:
            r3 = 0
        L_0x00b4:
            if (r8 != r3) goto L_0x00c3
            goto L_0x00c4
        L_0x00b7:
            char r3 = r5.charAt(r11)
            if (r3 != r10) goto L_0x00bf
            r3 = 1
            goto L_0x00c0
        L_0x00bf:
            r3 = 0
        L_0x00c0:
            if (r7 != r3) goto L_0x00c3
            goto L_0x00c4
        L_0x00c3:
            r9 = 1
        L_0x00c4:
            r3 = r9
        L_0x00c5:
            if (r3 != 0) goto L_0x00cb
        L_0x00c7:
            int r2 = r2 + 3
            goto L_0x0003
        L_0x00cb:
            char r1 = r5.charAt(r1)
            r2 = 65
            if (r1 != r2) goto L_0x00d4
            goto L_0x00d5
        L_0x00d4:
            int r3 = -r3
        L_0x00d5:
            return r3
        L_0x00d6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.OrderedTuples.cmp(int, int):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int listsort(int r14) throws java.lang.Throwable {
        /*
            r13 = this;
            int r0 = r13.n
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int[] r0 = new int[r0]
            r13.next = r0
            r0 = 1
            r2 = 1
        L_0x000c:
            int r3 = r13.n
            if (r2 != r3) goto L_0x0083
            int[] r3 = r13.next
            int r2 = r2 - r0
            r3[r2] = r1
            r2 = 1
        L_0x0016:
            r3 = 0
            r4 = -1
            r5 = -1
            r6 = 0
        L_0x001a:
            if (r14 < 0) goto L_0x0078
            int r7 = r6 + 1
            r9 = r14
            r6 = 0
            r8 = 0
        L_0x0021:
            if (r6 >= r2) goto L_0x002f
            int r8 = r8 + 1
            int[] r10 = r13.next
            r9 = r10[r9]
            if (r9 >= 0) goto L_0x002c
            goto L_0x002f
        L_0x002c:
            int r6 = r6 + 1
            goto L_0x0021
        L_0x002f:
            r6 = r5
            r5 = r4
            r4 = r14
            r14 = r9
            r9 = r2
        L_0x0034:
            if (r8 > 0) goto L_0x003f
            if (r9 <= 0) goto L_0x003b
            if (r14 < 0) goto L_0x003b
            goto L_0x003f
        L_0x003b:
            r4 = r5
            r5 = r6
            r6 = r7
            goto L_0x001a
        L_0x003f:
            if (r8 != 0) goto L_0x004d
            int[] r10 = r13.next
            r10 = r10[r14]
        L_0x0045:
            int r9 = r9 + -1
            r12 = r8
            r8 = r14
            r14 = r10
        L_0x004a:
            r10 = r9
            r9 = r12
            goto L_0x006c
        L_0x004d:
            if (r9 == 0) goto L_0x0062
            if (r14 >= 0) goto L_0x0052
            goto L_0x0062
        L_0x0052:
            int r10 = r13.cmp(r4, r14)
            if (r10 > 0) goto L_0x005d
            int[] r10 = r13.next
            r10 = r10[r4]
            goto L_0x0066
        L_0x005d:
            int[] r10 = r13.next
            r10 = r10[r14]
            goto L_0x0045
        L_0x0062:
            int[] r10 = r13.next
            r10 = r10[r4]
        L_0x0066:
            int r8 = r8 + -1
            r12 = r8
            r8 = r4
            r4 = r10
            goto L_0x004a
        L_0x006c:
            if (r6 < 0) goto L_0x0073
            int[] r11 = r13.next
            r11[r6] = r8
            goto L_0x0074
        L_0x0073:
            r5 = r8
        L_0x0074:
            r6 = r8
            r8 = r9
            r9 = r10
            goto L_0x0034
        L_0x0078:
            int[] r14 = r13.next
            r14[r5] = r1
            if (r6 > r0) goto L_0x007f
            return r4
        L_0x007f:
            int r2 = r2 * 2
            r14 = r4
            goto L_0x0016
        L_0x0083:
            int[] r3 = r13.next
            int r4 = r2 + -1
            r3[r4] = r2
            int r2 = r2 + 1
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.OrderedTuples.listsort(int):int");
    }
}
