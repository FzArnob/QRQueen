package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import java.math.BigDecimal;

public class TextUtils {
    public static String asString(Object obj) {
        if (obj == Values.empty || obj == null) {
            return "";
        }
        if (!(obj instanceof Values)) {
            StringBuffer stringBuffer = new StringBuffer(100);
            stringValue(obj, stringBuffer);
            return stringBuffer.toString();
        }
        throw new ClassCastException();
    }

    public static String stringValue(Object obj) {
        StringBuffer stringBuffer = new StringBuffer(100);
        if (obj instanceof Values) {
            TreeList treeList = (TreeList) obj;
            int i = 0;
            while (true) {
                int nextKind = treeList.getNextKind(i);
                if (nextKind == 0) {
                    break;
                }
                if (nextKind == 32) {
                    stringValue(treeList.getPosNext(i), stringBuffer);
                } else {
                    treeList.stringValue(treeList.posToDataIndex(i), stringBuffer);
                }
                i = treeList.nextPos(i);
            }
        } else {
            stringValue(obj, stringBuffer);
        }
        return stringBuffer.toString();
    }

    public static void stringValue(Object obj, StringBuffer stringBuffer) {
        if (obj instanceof KNode) {
            KNode kNode = (KNode) obj;
            NodeTree nodeTree = (NodeTree) kNode.sequence;
            nodeTree.stringValue(nodeTree.posToDataIndex(kNode.ipos), stringBuffer);
            return;
        }
        if (obj instanceof BigDecimal) {
            obj = XMLPrinter.formatDecimal((BigDecimal) obj);
        } else if ((obj instanceof Double) || (obj instanceof DFloNum)) {
            obj = XMLPrinter.formatDouble(((Number) obj).doubleValue());
        } else if (obj instanceof Float) {
            obj = XMLPrinter.formatFloat(((Number) obj).floatValue());
        }
        if (obj != null && obj != Values.empty) {
            stringBuffer.append(obj);
        }
    }

    public static void textValue(Object obj, Consumer consumer) {
        String str;
        if (obj != null) {
            boolean z = obj instanceof Values;
            if (!z || !((Values) obj).isEmpty()) {
                if (obj instanceof String) {
                    str = (String) obj;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    if (z) {
                        Object[] values = ((Values) obj).getValues();
                        for (int i = 0; i < values.length; i++) {
                            if (i > 0) {
                                stringBuffer.append(' ');
                            }
                            stringValue(values[i], stringBuffer);
                        }
                    } else {
                        stringValue(obj, stringBuffer);
                    }
                    str = stringBuffer.toString();
                }
                consumer.write(str);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x007a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String replaceWhitespace(java.lang.String r12, boolean r13) {
        /*
            int r0 = r12.length()
            r1 = 0
            r2 = 0
            r4 = r13
            r3 = 0
        L_0x0008:
            if (r3 >= r0) goto L_0x007c
            int r5 = r3 + 1
            char r3 = r12.charAt(r3)
            r6 = 32
            r7 = 2
            r8 = 1
            if (r3 != r6) goto L_0x0018
            r9 = 1
            goto L_0x0028
        L_0x0018:
            r9 = 9
            if (r3 == r9) goto L_0x0027
            r9 = 13
            if (r3 == r9) goto L_0x0027
            r9 = 10
            if (r3 != r9) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r9 = 0
            goto L_0x0028
        L_0x0027:
            r9 = 2
        L_0x0028:
            if (r2 != 0) goto L_0x0053
            if (r9 == r7) goto L_0x0038
            if (r9 != r8) goto L_0x0032
            if (r4 <= 0) goto L_0x0032
            if (r13 != 0) goto L_0x0038
        L_0x0032:
            if (r9 != r8) goto L_0x0053
            if (r5 != r0) goto L_0x0053
            if (r13 == 0) goto L_0x0053
        L_0x0038:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            if (r4 <= 0) goto L_0x0042
            int r3 = r5 + -2
            goto L_0x0044
        L_0x0042:
            int r3 = r5 + -1
        L_0x0044:
            r10 = 0
        L_0x0045:
            if (r10 >= r3) goto L_0x0051
            char r11 = r12.charAt(r10)
            r2.append(r11)
            int r10 = r10 + 1
            goto L_0x0045
        L_0x0051:
            r3 = 32
        L_0x0053:
            if (r13 == 0) goto L_0x0075
            if (r4 <= 0) goto L_0x0066
            if (r9 != 0) goto L_0x0066
            if (r2 == 0) goto L_0x0064
            int r4 = r2.length()
            if (r4 <= 0) goto L_0x0064
            r2.append(r6)
        L_0x0064:
            r4 = 0
            goto L_0x0072
        L_0x0066:
            if (r9 == r7) goto L_0x0071
            if (r9 != r8) goto L_0x006d
            if (r4 <= 0) goto L_0x006d
            goto L_0x0071
        L_0x006d:
            if (r9 <= 0) goto L_0x0064
            r4 = 1
            goto L_0x0072
        L_0x0071:
            r4 = 2
        L_0x0072:
            if (r4 <= 0) goto L_0x0075
            goto L_0x007a
        L_0x0075:
            if (r2 == 0) goto L_0x007a
            r2.append(r3)
        L_0x007a:
            r3 = r5
            goto L_0x0008
        L_0x007c:
            if (r2 == 0) goto L_0x0082
            java.lang.String r12 = r2.toString()
        L_0x0082:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xml.TextUtils.replaceWhitespace(java.lang.String, boolean):java.lang.String");
    }
}
