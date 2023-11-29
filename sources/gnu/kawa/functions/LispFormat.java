package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.text.CompoundFormat;
import gnu.text.ReportFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

public class LispFormat extends CompoundFormat {
    public static final String paramFromCount = "<from count>";
    public static final String paramFromList = "<from list>";
    public static final String paramUnspecified = "<unspecified>";

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: gnu.kawa.functions.LispFreshlineFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: gnu.kawa.functions.LispRepositionFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v65, resolved type: gnu.kawa.functions.LispFreshlineFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v44, resolved type: gnu.kawa.functions.LispObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v66, resolved type: gnu.kawa.functions.LispFreshlineFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v67, resolved type: gnu.kawa.functions.ObjectFormat} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: gnu.kawa.functions.LispObjectFormat} */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x03d6, code lost:
        throw new java.text.ParseException("saw ~> without matching ~<", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0453, code lost:
        throw new java.text.ParseException("saw ~; without matching ~[ or ~<", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x045d, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x048a, code lost:
        throw new java.text.ParseException("saw ~) without matching ~(", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x04af, code lost:
        r9 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x04b0, code lost:
        r8 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x04b2, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x04c0, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x04c1, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0537, code lost:
        r6.setSize(r8);
        r6.push(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0176, code lost:
        throw new java.text.ParseException("saw ~} without matching ~{", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0177, code lost:
        r2 = getParam(r6, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x017b, code lost:
        if (r2 != -1073741824) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x017d, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x017e, code lost:
        r3 = getParam(r6, r8 + 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0184, code lost:
        if (r3 != -1073741824) goto L_0x0190;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0188, code lost:
        if (r5 != '|') goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x018a, code lost:
        r11 = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x018d, code lost:
        r11 = 126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018f, code lost:
        r3 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0190, code lost:
        r2 = gnu.kawa.functions.LispCharacterFormat.getInstance(r3, r2, false, false);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LispFormat(char[] r29, int r30, int r31) throws java.text.ParseException {
        /*
            r28 = this;
            r0 = r28
            r1 = r29
            r2 = 0
            r3 = 0
            r0.<init>(r2, r3)
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r5 = 100
            r4.<init>(r5)
            java.util.Stack r6 = new java.util.Stack
            r6.<init>()
            int r7 = r30 + r31
            r8 = -1
            r8 = r30
            r9 = -1
        L_0x001b:
            r10 = 0
        L_0x001c:
            r11 = 126(0x7e, float:1.77E-43)
            if (r8 >= r7) goto L_0x0024
            char r12 = r1[r8]
            if (r12 != r11) goto L_0x0035
        L_0x0024:
            int r12 = r4.length()
            if (r12 <= 0) goto L_0x0035
            gnu.text.LiteralFormat r12 = new gnu.text.LiteralFormat
            r12.<init>((java.lang.StringBuffer) r4)
            r6.push(r12)
            r4.setLength(r3)
        L_0x0035:
            if (r8 < r7) goto L_0x005b
            if (r8 > r7) goto L_0x0055
            if (r9 >= 0) goto L_0x004d
            int r1 = r6.size()
            r0.length = r1
            int r1 = r0.length
            java.text.Format[] r1 = new java.text.Format[r1]
            r0.formats = r1
            java.text.Format[] r1 = r0.formats
            r6.copyInto(r1)
            return
        L_0x004d:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "missing ~] or ~}"
            r1.<init>(r2, r8)
            throw r1
        L_0x0055:
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
            r1.<init>()
            throw r1
        L_0x005b:
            int r12 = r8 + 1
            char r8 = r1[r8]
            if (r8 == r11) goto L_0x0066
            r4.append(r8)
            r8 = r12
            goto L_0x001c
        L_0x0066:
            int r8 = r6.size()
            int r13 = r12 + 1
            char r12 = r1[r12]
        L_0x006e:
            r14 = 35
            r15 = 8
            r5 = 44
            r11 = 10
            if (r12 != r14) goto L_0x0088
            java.lang.String r12 = "<from count>"
            r6.push(r12)
            int r12 = r13 + 1
            char r13 = r1[r13]
            r27 = r13
            r13 = r12
            r12 = r27
            goto L_0x0101
        L_0x0088:
            r14 = 118(0x76, float:1.65E-43)
            if (r12 == r14) goto L_0x00f6
            r14 = 86
            if (r12 != r14) goto L_0x0092
            goto L_0x00f6
        L_0x0092:
            r14 = 45
            if (r12 == r14) goto L_0x00b9
            int r16 = java.lang.Character.digit(r12, r11)
            if (r16 < 0) goto L_0x009d
            goto L_0x00b9
        L_0x009d:
            r14 = 39
            if (r12 != r14) goto L_0x00b1
            int r12 = r13 + 1
            char r13 = r1[r13]
            gnu.text.Char r13 = gnu.text.Char.make(r13)
            r6.push(r13)
            int r13 = r12 + 1
            char r12 = r1[r12]
            goto L_0x0101
        L_0x00b1:
            if (r12 != r5) goto L_0x0103
            java.lang.String r14 = "<unspecified>"
            r6.push(r14)
            goto L_0x0101
        L_0x00b9:
            if (r12 != r14) goto L_0x00bd
            r14 = 1
            goto L_0x00be
        L_0x00bd:
            r14 = 0
        L_0x00be:
            if (r14 == 0) goto L_0x00c5
            int r12 = r13 + 1
            char r13 = r1[r13]
            goto L_0x00ca
        L_0x00c5:
            r27 = r13
            r13 = r12
            r12 = r27
        L_0x00ca:
            r16 = r12
            r2 = 0
        L_0x00cd:
            int r17 = java.lang.Character.digit(r13, r11)
            if (r17 >= 0) goto L_0x00ea
            int r3 = r16 - r12
            if (r3 >= r15) goto L_0x00df
            if (r14 == 0) goto L_0x00da
            int r2 = -r2
        L_0x00da:
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r2)
            goto L_0x00e3
        L_0x00df:
            gnu.math.IntNum r2 = gnu.math.IntNum.valueOf(r1, r12, r3, r11, r14)
        L_0x00e3:
            r6.push(r2)
            r12 = r13
            r13 = r16
            goto L_0x0101
        L_0x00ea:
            int r2 = r2 * 10
            int r2 = r2 + r17
            int r3 = r16 + 1
            char r13 = r1[r16]
            r16 = r3
            r3 = 0
            goto L_0x00cd
        L_0x00f6:
            java.lang.String r2 = "<from list>"
            r6.push(r2)
            int r2 = r13 + 1
            char r3 = r1[r13]
            r13 = r2
            r12 = r3
        L_0x0101:
            if (r12 == r5) goto L_0x0560
        L_0x0103:
            r2 = 0
            r3 = 0
        L_0x0105:
            r5 = 58
            if (r12 != r5) goto L_0x010b
            r3 = 1
            goto L_0x0110
        L_0x010b:
            r5 = 64
            if (r12 != r5) goto L_0x0116
            r2 = 1
        L_0x0110:
            int r5 = r13 + 1
            char r12 = r1[r13]
            r13 = r5
            goto L_0x0105
        L_0x0116:
            char r5 = java.lang.Character.toUpperCase(r12)
            int r12 = r6.size()
            int r12 = r12 - r8
            r14 = -1610612736(0xffffffffa0000000, float:-1.0842022E-19)
            r11 = -1073741824(0xffffffffc0000000, float:-2.0)
            r15 = 2
            switch(r5) {
                case 10: goto L_0x053e;
                case 33: goto L_0x0531;
                case 36: goto L_0x04d5;
                case 37: goto L_0x04c5;
                case 38: goto L_0x04b7;
                case 40: goto L_0x048b;
                case 41: goto L_0x045f;
                case 42: goto L_0x0454;
                case 59: goto L_0x040b;
                case 60: goto L_0x03d7;
                case 62: goto L_0x032d;
                case 63: goto L_0x031f;
                case 65: goto L_0x02e8;
                case 66: goto L_0x029c;
                case 67: goto L_0x028f;
                case 68: goto L_0x029c;
                case 69: goto L_0x04d5;
                case 70: goto L_0x04d5;
                case 71: goto L_0x04d5;
                case 73: goto L_0x0282;
                case 79: goto L_0x029c;
                case 80: goto L_0x027c;
                case 82: goto L_0x029c;
                case 83: goto L_0x02e8;
                case 84: goto L_0x0264;
                case 87: goto L_0x02e8;
                case 88: goto L_0x029c;
                case 89: goto L_0x02e8;
                case 91: goto L_0x0232;
                case 93: goto L_0x01ed;
                case 94: goto L_0x01d5;
                case 95: goto L_0x01b5;
                case 123: goto L_0x0197;
                case 124: goto L_0x0177;
                case 125: goto L_0x0147;
                case 126: goto L_0x013e;
                default: goto L_0x0127;
            }
        L_0x0127:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "unrecognized format specifier ~"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r13)
            throw r1
        L_0x013e:
            if (r12 != 0) goto L_0x0177
            r4.append(r5)
            r12 = 0
            r14 = 0
            goto L_0x055c
        L_0x0147:
            if (r9 < 0) goto L_0x016f
            java.lang.Object r2 = r6.elementAt(r9)
            boolean r2 = r2 instanceof gnu.kawa.functions.LispIterationFormat
            if (r2 == 0) goto L_0x016f
            java.lang.Object r2 = r6.elementAt(r9)
            gnu.kawa.functions.LispIterationFormat r2 = (gnu.kawa.functions.LispIterationFormat) r2
            r2.atLeastOnce = r3
            int r9 = r9 + 2
            if (r8 <= r9) goto L_0x0163
            java.text.Format r3 = popFormats(r6, r9, r8)
            r2.body = r3
        L_0x0163:
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            int r9 = r2.intValue()
            goto L_0x04b0
        L_0x016f:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "saw ~} without matching ~{"
            r1.<init>(r2, r13)
            throw r1
        L_0x0177:
            int r2 = getParam(r6, r8)
            if (r2 != r11) goto L_0x017e
            r2 = 1
        L_0x017e:
            int r3 = r8 + 1
            int r3 = getParam(r6, r3)
            if (r3 != r11) goto L_0x0190
            r3 = 124(0x7c, float:1.74E-43)
            if (r5 != r3) goto L_0x018d
            r11 = 12
            goto L_0x018f
        L_0x018d:
            r11 = 126(0x7e, float:1.77E-43)
        L_0x018f:
            r3 = r11
        L_0x0190:
            r5 = 0
            gnu.kawa.functions.LispCharacterFormat r2 = gnu.kawa.functions.LispCharacterFormat.getInstance(r3, r2, r5, r5)
            goto L_0x04c1
        L_0x0197:
            gnu.kawa.functions.LispIterationFormat r5 = new gnu.kawa.functions.LispIterationFormat
            r5.<init>()
            r5.seenAt = r2
            r5.seenColon = r3
            int r2 = getParam(r6, r8)
            r5.maxIterations = r2
            r6.setSize(r8)
            r6.push(r5)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r9)
            r6.push(r2)
            goto L_0x04af
        L_0x01b5:
            int r5 = getParam(r6, r8)
            if (r5 != r11) goto L_0x01bc
            r5 = 1
        L_0x01bc:
            if (r2 == 0) goto L_0x01c3
            if (r3 == 0) goto L_0x01c3
            r15 = 82
            goto L_0x01cf
        L_0x01c3:
            if (r2 == 0) goto L_0x01c8
            r15 = 77
            goto L_0x01cf
        L_0x01c8:
            if (r3 == 0) goto L_0x01cd
            r15 = 70
            goto L_0x01cf
        L_0x01cd:
            r15 = 78
        L_0x01cf:
            gnu.kawa.functions.LispNewlineFormat r2 = gnu.kawa.functions.LispNewlineFormat.getInstance(r5, r15)
            goto L_0x04c1
        L_0x01d5:
            int r2 = getParam(r6, r8)
            int r3 = r8 + 1
            int r3 = getParam(r6, r3)
            int r5 = r8 + 2
            int r5 = getParam(r6, r5)
            gnu.kawa.functions.LispEscapeFormat r11 = new gnu.kawa.functions.LispEscapeFormat
            r11.<init>(r2, r3, r5)
            r2 = r11
            goto L_0x04c1
        L_0x01ed:
            if (r9 < 0) goto L_0x022a
            java.lang.Object r2 = r6.elementAt(r9)
            boolean r2 = r2 instanceof gnu.kawa.functions.LispChoiceFormat
            if (r2 == 0) goto L_0x022a
            int r2 = r9 + 3
            int r10 = r10 + r2
            java.text.Format r3 = popFormats(r6, r10, r8)
            r6.push(r3)
            java.lang.Object r3 = r6.elementAt(r9)
            gnu.kawa.functions.LispChoiceFormat r3 = (gnu.kawa.functions.LispChoiceFormat) r3
            int r5 = r6.size()
            java.text.Format[] r5 = getFormats(r6, r2, r5)
            r3.choices = r5
            r6.setSize(r2)
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            int r10 = r2.intValue()
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            int r9 = r2.intValue()
            goto L_0x04b0
        L_0x022a:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "saw ~] without matching ~["
            r1.<init>(r2, r13)
            throw r1
        L_0x0232:
            gnu.kawa.functions.LispChoiceFormat r5 = new gnu.kawa.functions.LispChoiceFormat
            r5.<init>()
            int r12 = getParam(r6, r8)
            r5.param = r12
            int r12 = r5.param
            if (r12 != r11) goto L_0x0243
            r5.param = r14
        L_0x0243:
            if (r3 == 0) goto L_0x0249
            r3 = 1
            r5.testBoolean = r3
            goto L_0x024a
        L_0x0249:
            r3 = 1
        L_0x024a:
            if (r2 == 0) goto L_0x024e
            r5.skipIfFalse = r3
        L_0x024e:
            r6.setSize(r8)
            r6.push(r5)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r9)
            r6.push(r2)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r10)
            r6.push(r2)
            goto L_0x0403
        L_0x0264:
            int r3 = getParam(r6, r8)
            int r5 = r8 + 1
            int r5 = getParam(r6, r5)
            int r11 = r8 + 2
            int r11 = getParam(r6, r11)
            gnu.kawa.functions.LispTabulateFormat r12 = new gnu.kawa.functions.LispTabulateFormat
            r12.<init>(r3, r5, r11, r2)
            r2 = r12
            goto L_0x04c1
        L_0x027c:
            gnu.kawa.functions.LispPluralFormat r2 = gnu.kawa.functions.LispPluralFormat.getInstance(r3, r2)
            goto L_0x04c1
        L_0x0282:
            int r2 = getParam(r6, r8)
            if (r2 != r11) goto L_0x0289
            r2 = 0
        L_0x0289:
            gnu.kawa.functions.LispIndentFormat r2 = gnu.kawa.functions.LispIndentFormat.getInstance(r2, r3)
            goto L_0x04c1
        L_0x028f:
            if (r12 <= 0) goto L_0x0295
            int r14 = getParam(r6, r8)
        L_0x0295:
            r5 = 1
            gnu.kawa.functions.LispCharacterFormat r2 = gnu.kawa.functions.LispCharacterFormat.getInstance(r14, r5, r2, r3)
            goto L_0x04c1
        L_0x029c:
            r11 = 82
            if (r5 != r11) goto L_0x02a9
            int r5 = r8 + 1
            int r15 = getParam(r6, r8)
            r21 = r15
            goto L_0x02c6
        L_0x02a9:
            r11 = 68
            if (r5 != r11) goto L_0x02b1
            r5 = r8
            r21 = 10
            goto L_0x02c6
        L_0x02b1:
            r11 = 79
            if (r5 != r11) goto L_0x02b9
            r5 = r8
            r21 = 8
            goto L_0x02c6
        L_0x02b9:
            r11 = 88
            if (r5 != r11) goto L_0x02c3
            r15 = 16
            r5 = r8
            r21 = 16
            goto L_0x02c6
        L_0x02c3:
            r5 = r8
            r21 = 2
        L_0x02c6:
            int r22 = getParam(r6, r5)
            int r11 = r5 + 1
            int r23 = getParam(r6, r11)
            int r11 = r5 + 2
            int r24 = getParam(r6, r11)
            r11 = 3
            int r5 = r5 + r11
            int r25 = getParam(r6, r5)
            if (r2 == 0) goto L_0x02e0
            r3 = r3 | 2
        L_0x02e0:
            r26 = r3
            java.text.Format r2 = gnu.kawa.functions.IntegerFormat.getInstance(r21, r22, r23, r24, r25, r26)
            goto L_0x04c1
        L_0x02e8:
            r3 = 65
            if (r5 == r3) goto L_0x02ee
            r3 = 1
            goto L_0x02ef
        L_0x02ee:
            r3 = 0
        L_0x02ef:
            gnu.kawa.functions.ObjectFormat r3 = gnu.kawa.functions.ObjectFormat.getInstance(r3)
            if (r12 <= 0) goto L_0x04c0
            int r20 = getParam(r6, r8)
            int r5 = r8 + 1
            int r21 = getParam(r6, r5)
            int r5 = r8 + 2
            int r22 = getParam(r6, r5)
            int r5 = r8 + 3
            int r23 = getParam(r6, r5)
            gnu.kawa.functions.LispObjectFormat r5 = new gnu.kawa.functions.LispObjectFormat
            r19 = r3
            gnu.text.ReportFormat r19 = (gnu.text.ReportFormat) r19
            if (r2 == 0) goto L_0x0316
            r24 = 0
            goto L_0x0318
        L_0x0316:
            r24 = 100
        L_0x0318:
            r18 = r5
            r18.<init>(r19, r20, r21, r22, r23, r24)
            goto L_0x045d
        L_0x031f:
            gnu.kawa.functions.LispIterationFormat r3 = new gnu.kawa.functions.LispIterationFormat
            r3.<init>()
            r3.seenAt = r2
            r2 = 1
            r3.maxIterations = r2
            r3.atLeastOnce = r2
            goto L_0x04c0
        L_0x032d:
            if (r9 < 0) goto L_0x03cf
            java.lang.Object r2 = r6.elementAt(r9)
            boolean r2 = r2 instanceof gnu.kawa.functions.LispPrettyFormat
            if (r2 == 0) goto L_0x03cf
            int r2 = r9 + 3
            int r5 = r2 + r10
            java.text.Format r5 = popFormats(r6, r5, r8)
            r6.push(r5)
            java.lang.Object r5 = r6.elementAt(r9)
            gnu.kawa.functions.LispPrettyFormat r5 = (gnu.kawa.functions.LispPrettyFormat) r5
            int r8 = r6.size()
            java.text.Format[] r8 = getFormats(r6, r2, r8)
            r5.segments = r8
            r6.setSize(r2)
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            r2.intValue()
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            int r9 = r2.intValue()
            if (r3 == 0) goto L_0x03c7
            java.text.Format[] r2 = r5.segments
            int r2 = r2.length
            r3 = 3
            if (r2 > r3) goto L_0x03bf
            if (r2 < r15) goto L_0x0397
            java.text.Format[] r3 = r5.segments
            r8 = 0
            r3 = r3[r8]
            boolean r3 = r3 instanceof gnu.text.LiteralFormat
            if (r3 == 0) goto L_0x038f
            java.text.Format[] r3 = r5.segments
            r3 = r3[r8]
            gnu.text.LiteralFormat r3 = (gnu.text.LiteralFormat) r3
            java.lang.String r3 = r3.content()
            r5.prefix = r3
            java.text.Format[] r3 = r5.segments
            r8 = 1
            r3 = r3[r8]
            r5.body = r3
            goto L_0x039e
        L_0x038f:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "prefix segment is not literal"
            r1.<init>(r2, r13)
            throw r1
        L_0x0397:
            java.text.Format[] r3 = r5.segments
            r8 = 0
            r3 = r3[r8]
            r5.body = r3
        L_0x039e:
            r3 = 3
            if (r2 < r3) goto L_0x04b0
            java.text.Format[] r2 = r5.segments
            r2 = r2[r15]
            boolean r2 = r2 instanceof gnu.text.LiteralFormat
            if (r2 == 0) goto L_0x03b7
            java.text.Format[] r2 = r5.segments
            r2 = r2[r15]
            gnu.text.LiteralFormat r2 = (gnu.text.LiteralFormat) r2
            java.lang.String r2 = r2.content()
            r5.suffix = r2
            goto L_0x04b0
        L_0x03b7:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "suffix segment is not literal"
            r1.<init>(r2, r13)
            throw r1
        L_0x03bf:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "too many segments in Logical Block format"
            r1.<init>(r2, r13)
            throw r1
        L_0x03c7:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "not implemented: justfication i.e. ~<...~>"
            r1.<init>(r2, r13)
            throw r1
        L_0x03cf:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "saw ~> without matching ~<"
            r1.<init>(r2, r13)
            throw r1
        L_0x03d7:
            gnu.kawa.functions.LispPrettyFormat r5 = new gnu.kawa.functions.LispPrettyFormat
            r5.<init>()
            r5.seenAt = r2
            if (r3 == 0) goto L_0x03e9
            java.lang.String r2 = "("
            r5.prefix = r2
            java.lang.String r2 = ")"
            r5.suffix = r2
            goto L_0x03ef
        L_0x03e9:
            java.lang.String r2 = ""
            r5.prefix = r2
            r5.suffix = r2
        L_0x03ef:
            r6.setSize(r8)
            r6.push(r5)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r9)
            r6.push(r2)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r10)
            r6.push(r2)
        L_0x0403:
            r9 = r8
            r8 = r13
            r2 = 0
            r3 = 0
            r5 = 100
            goto L_0x001b
        L_0x040b:
            if (r9 < 0) goto L_0x044c
            java.lang.Object r5 = r6.elementAt(r9)
            boolean r5 = r5 instanceof gnu.kawa.functions.LispChoiceFormat
            if (r5 == 0) goto L_0x042e
            java.lang.Object r2 = r6.elementAt(r9)
            gnu.kawa.functions.LispChoiceFormat r2 = (gnu.kawa.functions.LispChoiceFormat) r2
            if (r3 == 0) goto L_0x0420
            r3 = 1
            r2.lastIsDefault = r3
        L_0x0420:
            int r2 = r9 + 3
            int r2 = r2 + r10
            java.text.Format r2 = popFormats(r6, r2, r8)
            r6.push(r2)
        L_0x042a:
            int r10 = r10 + 1
            goto L_0x04b0
        L_0x042e:
            java.lang.Object r3 = r6.elementAt(r9)
            boolean r3 = r3 instanceof gnu.kawa.functions.LispPrettyFormat
            if (r3 == 0) goto L_0x044c
            java.lang.Object r3 = r6.elementAt(r9)
            gnu.kawa.functions.LispPrettyFormat r3 = (gnu.kawa.functions.LispPrettyFormat) r3
            if (r2 == 0) goto L_0x0441
            r2 = 1
            r3.perLine = r2
        L_0x0441:
            int r2 = r9 + 3
            int r2 = r2 + r10
            java.text.Format r2 = popFormats(r6, r2, r8)
            r6.push(r2)
            goto L_0x042a
        L_0x044c:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "saw ~; without matching ~[ or ~<"
            r1.<init>(r2, r13)
            throw r1
        L_0x0454:
            gnu.kawa.functions.LispRepositionFormat r5 = new gnu.kawa.functions.LispRepositionFormat
            int r11 = getParam(r6, r8)
            r5.<init>(r11, r3, r2)
        L_0x045d:
            r2 = r5
            goto L_0x04c1
        L_0x045f:
            if (r9 < 0) goto L_0x0483
            java.lang.Object r2 = r6.elementAt(r9)
            boolean r2 = r2 instanceof gnu.text.CaseConvertFormat
            if (r2 == 0) goto L_0x0483
            java.lang.Object r2 = r6.elementAt(r9)
            gnu.text.CaseConvertFormat r2 = (gnu.text.CaseConvertFormat) r2
            int r9 = r9 + 2
            java.text.Format r3 = popFormats(r6, r9, r8)
            r2.setBaseFormat(r3)
            java.lang.Object r2 = r6.pop()
            gnu.math.IntNum r2 = (gnu.math.IntNum) r2
            int r9 = r2.intValue()
            goto L_0x04b0
        L_0x0483:
            java.text.ParseException r1 = new java.text.ParseException
            java.lang.String r2 = "saw ~) without matching ~("
            r1.<init>(r2, r13)
            throw r1
        L_0x048b:
            if (r3 == 0) goto L_0x0495
            if (r2 == 0) goto L_0x0492
            r11 = 85
            goto L_0x049c
        L_0x0492:
            r11 = 67
            goto L_0x049c
        L_0x0495:
            if (r2 == 0) goto L_0x049a
            r11 = 84
            goto L_0x049c
        L_0x049a:
            r11 = 76
        L_0x049c:
            gnu.text.CaseConvertFormat r2 = new gnu.text.CaseConvertFormat
            r3 = 0
            r2.<init>(r3, r11)
            r6.setSize(r8)
            r6.push(r2)
            gnu.math.IntNum r2 = gnu.math.IntNum.make((int) r9)
            r6.push(r2)
        L_0x04af:
            r9 = r8
        L_0x04b0:
            r8 = r13
            r2 = 0
        L_0x04b2:
            r3 = 0
            r5 = 100
            goto L_0x001c
        L_0x04b7:
            int r2 = getParam(r6, r8)
            gnu.kawa.functions.LispFreshlineFormat r3 = new gnu.kawa.functions.LispFreshlineFormat
            r3.<init>(r2)
        L_0x04c0:
            r2 = r3
        L_0x04c1:
            r12 = 0
            r14 = 0
            goto L_0x0537
        L_0x04c5:
            r2 = 1
            int r3 = getParam(r6, r8)
            if (r3 != r11) goto L_0x04cd
            goto L_0x04ce
        L_0x04cd:
            r2 = r3
        L_0x04ce:
            r3 = 76
            gnu.kawa.functions.LispNewlineFormat r2 = gnu.kawa.functions.LispNewlineFormat.getInstance(r2, r3)
            goto L_0x04c1
        L_0x04d5:
            gnu.kawa.functions.LispRealFormat r11 = new gnu.kawa.functions.LispRealFormat
            r11.<init>()
            r11.op = r5
            int r12 = getParam(r6, r8)
            r11.arg1 = r12
            int r12 = r8 + 1
            int r12 = getParam(r6, r12)
            r11.arg2 = r12
            int r12 = r8 + 2
            int r12 = getParam(r6, r12)
            r11.arg3 = r12
            int r12 = r8 + 3
            int r12 = getParam(r6, r12)
            r11.arg4 = r12
            r12 = 36
            if (r5 == r12) goto L_0x051e
            int r12 = r8 + 4
            int r12 = getParam(r6, r12)
            r11.arg5 = r12
            r12 = 69
            if (r5 == r12) goto L_0x050e
            r12 = 71
            if (r5 != r12) goto L_0x051e
        L_0x050e:
            int r5 = r8 + 5
            int r5 = getParam(r6, r5)
            r11.arg6 = r5
            int r5 = r8 + 6
            int r5 = getParam(r6, r5)
            r11.arg7 = r5
        L_0x051e:
            r11.showPlus = r2
            r11.internalPad = r3
            int r2 = r11.argsUsed
            if (r2 != 0) goto L_0x052d
            r12 = 0
            r14 = 0
            java.text.Format r2 = r11.resolve(r12, r14)
            goto L_0x0537
        L_0x052d:
            r12 = 0
            r14 = 0
            r2 = r11
            goto L_0x0537
        L_0x0531:
            r12 = 0
            r14 = 0
            gnu.text.FlushFormat r2 = gnu.text.FlushFormat.getInstance()
        L_0x0537:
            r6.setSize(r8)
            r6.push(r2)
            goto L_0x055c
        L_0x053e:
            r12 = 0
            r14 = 0
            if (r2 == 0) goto L_0x0545
            r4.append(r5)
        L_0x0545:
            if (r3 != 0) goto L_0x055c
            r8 = r13
        L_0x0548:
            if (r8 >= r7) goto L_0x0559
            int r2 = r8 + 1
            char r3 = r1[r8]
            boolean r3 = java.lang.Character.isWhitespace(r3)
            if (r3 != 0) goto L_0x0557
            int r8 = r2 + -1
            goto L_0x0559
        L_0x0557:
            r8 = r2
            goto L_0x0548
        L_0x0559:
            r2 = r12
            goto L_0x04b2
        L_0x055c:
            r2 = r12
            r8 = r13
            goto L_0x04b2
        L_0x0560:
            r12 = 0
            r14 = 0
            int r2 = r13 + 1
            char r3 = r1[r13]
            r13 = r2
            r2 = r12
            r5 = 100
            r11 = 126(0x7e, float:1.77E-43)
            r12 = r3
            r3 = 0
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.LispFormat.<init>(char[], int, int):void");
    }

    static Format[] getFormats(Vector vector, int i, int i2) {
        Format[] formatArr = new Format[(i2 - i)];
        for (int i3 = i; i3 < i2; i3++) {
            formatArr[i3 - i] = (Format) vector.elementAt(i3);
        }
        return formatArr;
    }

    static Format popFormats(Vector vector, int i, int i2) {
        Format format;
        if (i2 == i + 1) {
            format = (Format) vector.elementAt(i);
        } else {
            format = new CompoundFormat(getFormats(vector, i, i2));
        }
        vector.setSize(i);
        return format;
    }

    public LispFormat(String str) throws ParseException {
        this(str.toCharArray());
    }

    public LispFormat(char[] cArr) throws ParseException {
        this(cArr, 0, cArr.length);
    }

    public static int getParam(Vector vector, int i) {
        if (i >= vector.size()) {
            return -1073741824;
        }
        Object elementAt = vector.elementAt(i);
        if (elementAt == paramFromList) {
            return -1610612736;
        }
        if (elementAt == paramFromCount) {
            return ReportFormat.PARAM_FROM_COUNT;
        }
        if (elementAt == paramUnspecified) {
            return -1073741824;
        }
        return getParam(elementAt, -1073741824);
    }

    public static Object[] asArray(Object obj) {
        if (obj instanceof Object[]) {
            return (Object[]) obj;
        }
        if (!(obj instanceof Sequence)) {
            return null;
        }
        int size = ((Sequence) obj).size();
        Object[] objArr = new Object[size];
        int i = 0;
        while (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            objArr[i] = pair.getCar();
            obj = pair.getCdr();
            i++;
        }
        if (i < size) {
            if (!(obj instanceof Sequence)) {
                return null;
            }
            Sequence sequence = (Sequence) obj;
            for (int i2 = i; i2 < size; i2++) {
                objArr[i2] = sequence.get(i + i2);
            }
        }
        return objArr;
    }
}
