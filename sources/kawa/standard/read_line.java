package kawa.standard;

public class read_line {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        if (r15 == "trim") goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r15 != "peek") goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if (r15 != "concat") goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r4 != 10) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r12 = r12 + 1;
        r14.pos = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r15 != "peek") goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        if (r4 != 10) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r4 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        if (r4 >= r2) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0049, code lost:
        if (r3[r4] != 10) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
        r14.pos = r1 + r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        return new gnu.lists.FString(r3, r0, r12 - r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object apply(gnu.text.LineBufferedReader r14, java.lang.String r15) throws java.io.IOException {
        /*
            int r0 = r14.read()
            if (r0 >= 0) goto L_0x0009
            java.lang.Object r14 = gnu.expr.Special.eof
            return r14
        L_0x0009:
            int r0 = r14.pos
            r1 = 1
            int r0 = r0 - r1
            int r2 = r14.limit
            char[] r3 = r14.buffer
            r4 = r0
        L_0x0012:
            java.lang.String r5 = "concat"
            r6 = -1
            java.lang.String r7 = "peek"
            r8 = 13
            r9 = 2
            r10 = 0
            r11 = 10
            if (r4 >= r2) goto L_0x0056
            int r12 = r4 + 1
            char r4 = r3[r4]
            if (r4 == r8) goto L_0x002a
            if (r4 != r11) goto L_0x0028
            goto L_0x002a
        L_0x0028:
            r4 = r12
            goto L_0x0012
        L_0x002a:
            int r12 = r12 + r6
            java.lang.String r13 = "trim"
            if (r15 == r13) goto L_0x003d
            if (r15 != r7) goto L_0x0032
            goto L_0x003d
        L_0x0032:
            if (r15 != r5) goto L_0x003b
            if (r4 != r11) goto L_0x003b
            int r12 = r12 + 1
            r14.pos = r12
            goto L_0x004f
        L_0x003b:
            r4 = r12
            goto L_0x0056
        L_0x003d:
            if (r15 != r7) goto L_0x0040
            r6 = 0
        L_0x0040:
            if (r4 != r11) goto L_0x0043
            goto L_0x004c
        L_0x0043:
            int r4 = r12 + 1
            if (r4 >= r2) goto L_0x003b
            char r15 = r3[r4]
            if (r15 != r11) goto L_0x004c
            r1 = 2
        L_0x004c:
            int r1 = r1 + r12
            r14.pos = r1
        L_0x004f:
            gnu.lists.FString r14 = new gnu.lists.FString
            int r12 = r12 - r0
            r14.<init>((char[]) r3, (int) r0, (int) r12)
            return r14
        L_0x0056:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r12 = 100
            r2.<init>(r12)
            if (r4 <= r0) goto L_0x0064
            int r12 = r4 - r0
            r2.append(r3, r0, r12)
        L_0x0064:
            r14.pos = r4
            java.lang.String r0 = "split"
            if (r15 != r7) goto L_0x006d
            r3 = 80
            goto L_0x0077
        L_0x006d:
            if (r15 == r5) goto L_0x0075
            if (r15 != r0) goto L_0x0072
            goto L_0x0075
        L_0x0072:
            r3 = 73
            goto L_0x0077
        L_0x0075:
            r3 = 65
        L_0x0077:
            r14.readLine(r2, r3)
            int r14 = r2.length()
            if (r15 != r0) goto L_0x009e
            if (r14 != 0) goto L_0x0084
            r6 = 0
            goto L_0x009e
        L_0x0084:
            int r3 = r14 + -1
            char r3 = r2.charAt(r3)
            if (r3 != r8) goto L_0x008e
        L_0x008c:
            r6 = 1
            goto L_0x009d
        L_0x008e:
            if (r3 == r11) goto L_0x0092
            r6 = 0
            goto L_0x009d
        L_0x0092:
            if (r3 <= r9) goto L_0x008c
            int r3 = r14 + -2
            char r3 = r2.charAt(r3)
            if (r3 != r8) goto L_0x008c
            r6 = 2
        L_0x009d:
            int r14 = r14 - r6
        L_0x009e:
            gnu.lists.FString r3 = new gnu.lists.FString
            r3.<init>((java.lang.StringBuffer) r2, (int) r10, (int) r14)
            if (r15 != r0) goto L_0x00b7
            gnu.lists.FString r15 = new gnu.lists.FString
            int r14 = r14 - r6
            r15.<init>((java.lang.StringBuffer) r2, (int) r14, (int) r6)
            java.lang.Object[] r14 = new java.lang.Object[r9]
            r14[r10] = r3
            r14[r1] = r15
            gnu.mapping.Values r15 = new gnu.mapping.Values
            r15.<init>(r14)
            return r15
        L_0x00b7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.read_line.apply(gnu.text.LineBufferedReader, java.lang.String):java.lang.Object");
    }
}
