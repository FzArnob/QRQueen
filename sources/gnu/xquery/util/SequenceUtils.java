package gnu.xquery.util;

import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.NodeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.xml.NodeTree;

public class SequenceUtils {
    public static final NodeType textOrElement = new NodeType("element-or-text", 3);

    public static boolean isZeroOrOne(Object obj) {
        return !(obj instanceof Values) || ((Values) obj).isEmpty();
    }

    static Object coerceToZeroOrOne(Object obj, String str, int i) {
        if (isZeroOrOne(obj)) {
            return obj;
        }
        throw new WrongType(str, i, obj, "xs:item()?");
    }

    public static Object zeroOrOne(Object obj) {
        return coerceToZeroOrOne(obj, "zero-or-one", 1);
    }

    public static Object oneOrMore(Object obj) {
        if (!(obj instanceof Values) || !((Values) obj).isEmpty()) {
            return obj;
        }
        throw new IllegalArgumentException();
    }

    public static Object exactlyOne(Object obj) {
        if (!(obj instanceof Values)) {
            return obj;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isEmptySequence(Object obj) {
        return (obj instanceof Values) && ((Values) obj).isEmpty();
    }

    public static boolean exists(Object obj) {
        return !(obj instanceof Values) || !((Values) obj).isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        if (r5 == r9) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void insertBefore$X(java.lang.Object r8, long r9, java.lang.Object r11, gnu.mapping.CallContext r12) {
        /*
            gnu.lists.Consumer r12 = r12.consumer
            r0 = 0
            r2 = 1
            int r4 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r4 > 0) goto L_0x000b
            r9 = r2
        L_0x000b:
            boolean r4 = r8 instanceof gnu.mapping.Values
            if (r4 == 0) goto L_0x002e
            r4 = r8
            gnu.mapping.Values r4 = (gnu.mapping.Values) r4
            r8 = 0
            r5 = r0
            r0 = 0
        L_0x0015:
            int r1 = r4.nextPos(r8)
            if (r1 != 0) goto L_0x001d
            if (r0 == 0) goto L_0x0022
        L_0x001d:
            long r5 = r5 + r2
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x0026
        L_0x0022:
            gnu.mapping.Values.writeValues(r11, r12)
            r0 = 1
        L_0x0026:
            if (r1 != 0) goto L_0x0029
            goto L_0x003d
        L_0x0029:
            r4.consumePosRange(r8, r1, r12)
            r8 = r1
            goto L_0x0015
        L_0x002e:
            int r0 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r0 > 0) goto L_0x0035
            gnu.mapping.Values.writeValues(r11, r12)
        L_0x0035:
            r12.writeObject(r8)
            if (r0 <= 0) goto L_0x003d
            gnu.mapping.Values.writeValues(r11, r12)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.insertBefore$X(java.lang.Object, long, java.lang.Object, gnu.mapping.CallContext):void");
    }

    public static void remove$X(Object obj, long j, CallContext callContext) {
        Consumer consumer = callContext.consumer;
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int i = 0;
            long j2 = 0;
            while (true) {
                int nextPos = values.nextPos(i);
                if (nextPos != 0) {
                    j2++;
                    if (j2 != j) {
                        values.consumePosRange(i, nextPos, consumer);
                    }
                    i = nextPos;
                } else {
                    return;
                }
            }
        } else if (j != 1) {
            consumer.writeObject(obj);
        }
    }

    public static void reverse$X(Object obj, CallContext callContext) {
        int i;
        Consumer consumer = callContext.consumer;
        if (!(obj instanceof Values)) {
            consumer.writeObject(obj);
            return;
        }
        Values values = (Values) obj;
        int[] iArr = new int[100];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= iArr.length) {
                int[] iArr2 = new int[(i2 * 2)];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                iArr = iArr2;
            }
            i = i2 + 1;
            iArr[i2] = i3;
            i3 = values.nextPos(i3);
            if (i3 == 0) {
                break;
            }
            i2 = i;
        }
        int i4 = i - 1;
        while (true) {
            i4--;
            if (i4 >= 0) {
                values.consumePosRange(iArr[i4], iArr[i4 + 1], consumer);
            } else {
                return;
            }
        }
    }

    public static void indexOf$X(Object obj, Object obj2, NamedCollator namedCollator, CallContext callContext) {
        Consumer consumer = callContext.consumer;
        int i = 1;
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int startPos = values.startPos();
            while (true) {
                startPos = values.nextPos(startPos);
                if (startPos != 0) {
                    if (Compare.apply(72, values.getPosPrevious(startPos), obj2, namedCollator)) {
                        consumer.writeInt(i);
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else if (Compare.apply(72, obj, obj2, namedCollator)) {
            consumer.writeInt(1);
        }
    }

    public static boolean deepEqualChildren(NodeTree nodeTree, int i, NodeTree nodeTree2, int i2, NamedCollator namedCollator) {
        NodeType nodeType = textOrElement;
        int firstChildPos = nodeTree.firstChildPos(i, nodeType);
        int firstChildPos2 = nodeTree2.firstChildPos(i2, nodeType);
        while (firstChildPos != 0 && firstChildPos2 != 0) {
            if (!deepEqual(nodeTree, firstChildPos, nodeTree2, firstChildPos2, namedCollator)) {
                return false;
            }
            firstChildPos = nodeTree.nextMatching(firstChildPos, nodeType, -1, false);
            firstChildPos2 = nodeTree2.nextMatching(firstChildPos2, nodeType, -1, false);
        }
        if (firstChildPos == firstChildPos2) {
            return true;
        }
        return false;
    }

    public static boolean deepEqual(NodeTree nodeTree, int i, NodeTree nodeTree2, int i2, NamedCollator namedCollator) {
        int nextKind = nodeTree.getNextKind(i);
        int nextKind2 = nodeTree2.getNextKind(i2);
        switch (nextKind) {
            case 33:
                if (nextKind != nextKind2 || nodeTree.posLocalName(i) != nodeTree2.posLocalName(i2) || nodeTree.posNamespaceURI(i) != nodeTree2.posNamespaceURI(i2)) {
                    return false;
                }
                int firstAttributePos = nodeTree.firstAttributePos(i);
                int i3 = 0;
                while (firstAttributePos != 0 && nodeTree.getNextKind(firstAttributePos) == 35) {
                    i3++;
                    int attributeI = nodeTree2.getAttributeI(i2, nodeTree.posNamespaceURI(firstAttributePos), nodeTree.posLocalName(firstAttributePos));
                    if (attributeI == 0 || !deepEqualItems(KNode.getNodeValue(nodeTree, firstAttributePos), KNode.getNodeValue(nodeTree2, attributeI), namedCollator)) {
                        return false;
                    }
                    firstAttributePos = nodeTree.nextPos(firstAttributePos);
                }
                if (i3 != nodeTree2.getAttributeCount(i2)) {
                    return false;
                }
                break;
            case 34:
                break;
            case 35:
                if (nodeTree.posLocalName(i) == nodeTree2.posLocalName(i2) && nodeTree.posNamespaceURI(i) == nodeTree2.posNamespaceURI(i2)) {
                    return deepEqualItems(KAttr.getObjectValue(nodeTree, i), KAttr.getObjectValue(nodeTree2, i2), namedCollator);
                }
                return false;
            case 37:
                if (!nodeTree.posTarget(i).equals(nodeTree2.posTarget(i2))) {
                    return false;
                }
                return KNode.getNodeValue(nodeTree, i).equals(KNode.getNodeValue(nodeTree2, i2));
            default:
                if (nextKind != nextKind2) {
                    return false;
                }
                return KNode.getNodeValue(nodeTree, i).equals(KNode.getNodeValue(nodeTree2, i2));
        }
        return deepEqualChildren(nodeTree, i, nodeTree2, i2, namedCollator);
    }

    public static boolean deepEqualItems(Object obj, Object obj2, NamedCollator namedCollator) {
        if (!NumberValue.isNaN(obj) || !NumberValue.isNaN(obj2)) {
            return Compare.atomicCompare(8, obj, obj2, namedCollator);
        }
        return true;
    }

    public static boolean deepEqual(Object obj, Object obj2, NamedCollator namedCollator) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj == Values.empty) {
            if (obj2 == null || obj2 == Values.empty) {
                return true;
            }
            return false;
        } else if (obj2 == null || obj2 == Values.empty) {
            return false;
        } else {
            boolean z = obj instanceof Values;
            boolean z2 = obj2 instanceof Values;
            Values values = null;
            Values values2 = z ? (Values) obj : null;
            if (z2) {
                values = (Values) obj2;
            }
            boolean z3 = true;
            int i = 1;
            int i2 = 1;
            while (true) {
                if (z) {
                    if (z3) {
                        i = values2.startPos();
                    }
                    i = values2.nextPos(i);
                }
                if (z2) {
                    if (z3) {
                        i2 = values.startPos();
                    }
                    i2 = values.nextPos(i2);
                }
                if (i != 0 && i2 != 0) {
                    Object posPrevious = z ? values2.getPosPrevious(i) : obj;
                    Object posPrevious2 = z2 ? values.getPosPrevious(i2) : obj2;
                    boolean z4 = posPrevious instanceof KNode;
                    if (!z4 && !(posPrevious2 instanceof KNode)) {
                        try {
                            if (!deepEqualItems(obj, obj2, namedCollator)) {
                                break;
                            }
                        } catch (Throwable unused) {
                        }
                    } else if (!z4 || !(posPrevious2 instanceof KNode)) {
                        return false;
                    } else {
                        KNode kNode = (KNode) posPrevious;
                        KNode kNode2 = (KNode) posPrevious2;
                        if (!deepEqual((NodeTree) kNode.sequence, kNode.ipos, (NodeTree) kNode2.sequence, kNode2.ipos, namedCollator)) {
                            return false;
                        }
                    }
                    if (z3) {
                        if (!z) {
                            i = 0;
                        }
                        z3 = false;
                        if (!z2) {
                            i2 = 0;
                        }
                    }
                } else if (i == i2) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }
}
