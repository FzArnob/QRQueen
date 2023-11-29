package gnu.xquery.util;

import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XDataType;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

public class MinMax {
    public static Object min(Object obj, NamedCollator namedCollator) {
        return minMax(obj, false, namedCollator);
    }

    public static Object max(Object obj, NamedCollator namedCollator) {
        return minMax(obj, true, namedCollator);
    }

    public static Object minMax(Object obj, boolean z, NamedCollator namedCollator) {
        int i = 16;
        if (obj instanceof Values) {
            TreeList treeList = (TreeList) obj;
            if (!z) {
                i = 4;
            }
            Object posNext = treeList.getPosNext(0);
            if (posNext == Sequence.eofValue) {
                return Values.empty;
            }
            Object convert = convert(posNext);
            int i2 = 0;
            while (true) {
                i2 = treeList.nextPos(i2);
                Object posNext2 = treeList.getPosNext(i2);
                if (posNext2 == Sequence.eofValue) {
                    return convert;
                }
                Object convert2 = convert(posNext2);
                if ((convert instanceof Number) || (convert2 instanceof Number)) {
                    int classifyValue = Arithmetic.classifyValue(convert);
                    int classifyValue2 = Arithmetic.classifyValue(convert2);
                    int compare = NumberCompare.compare(convert, classifyValue, convert2, classifyValue2, false);
                    if (compare != -3) {
                        int i3 = classifyValue < classifyValue2 ? classifyValue2 : classifyValue;
                        boolean z2 = true;
                        if (compare == -2) {
                            convert = NumberValue.NaN;
                        } else if (!NumberCompare.checkCompareCode(compare, i)) {
                            if (i3 == classifyValue2) {
                                z2 = false;
                            }
                            convert = convert2;
                        } else if (i3 == classifyValue) {
                            z2 = false;
                        }
                        if (z2) {
                            convert = Arithmetic.convert(convert, i3);
                        }
                    } else {
                        throw new IllegalArgumentException("values cannot be compared");
                    }
                } else if (!Compare.atomicCompare(i, convert, convert2, namedCollator)) {
                    convert = convert2;
                }
            }
        } else {
            Object convert3 = convert(obj);
            Compare.atomicCompare(16, convert3, convert3, namedCollator);
            return convert3;
        }
    }

    static Object convert(Object obj) {
        Object atomicValue = KNode.atomicValue(obj);
        return atomicValue instanceof UntypedAtomic ? (Double) XDataType.doubleType.valueOf(TextUtils.stringValue(atomicValue)) : atomicValue;
    }
}
