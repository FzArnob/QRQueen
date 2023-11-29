package gnu.kawa.xml;

import androidx.appcompat.widget.ActivityChooserView;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import gnu.bytecode.ClassType;
import gnu.kawa.functions.Arithmetic;
import gnu.math.IntNum;
import gnu.math.RealNum;
import java.math.BigDecimal;
import net.lingala.zip4j.util.InternalZipConstants;

public class XIntegerType extends XDataType {
    public static final XIntegerType byteType;
    public static final XIntegerType intType;
    public static final XIntegerType integerType;
    public static final XIntegerType longType;
    public static final XIntegerType negativeIntegerType;
    public static final XIntegerType nonNegativeIntegerType;
    public static final XIntegerType nonPositiveIntegerType;
    public static final XIntegerType positiveIntegerType;
    public static final XIntegerType shortType;
    static ClassType typeIntNum = ClassType.make("gnu.math.IntNum");
    public static final XIntegerType unsignedByteType;
    public static final XIntegerType unsignedIntType;
    public static final XIntegerType unsignedLongType;
    public static final XIntegerType unsignedShortType;
    boolean isUnsignedType;
    public final IntNum maxValue;
    public final IntNum minValue;

    static {
        XIntegerType xIntegerType = new XIntegerType(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, decimalType, 5, (IntNum) null, (IntNum) null);
        integerType = xIntegerType;
        XIntegerType xIntegerType2 = new XIntegerType(LongTypedProperty.TYPE, (XDataType) xIntegerType, 8, IntNum.make(Long.MIN_VALUE), IntNum.make(Long.MAX_VALUE));
        longType = xIntegerType2;
        XIntegerType xIntegerType3 = new XIntegerType("int", (XDataType) xIntegerType2, 9, IntNum.make(Integer.MIN_VALUE), IntNum.make((int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        intType = xIntegerType3;
        XIntegerType xIntegerType4 = new XIntegerType("short", (XDataType) xIntegerType3, 10, IntNum.make(-32768), IntNum.make(32767));
        shortType = xIntegerType4;
        byteType = new XIntegerType("byte", (XDataType) xIntegerType4, 11, IntNum.make(-128), IntNum.make(127));
        XIntegerType xIntegerType5 = xIntegerType;
        XIntegerType xIntegerType6 = new XIntegerType("nonPositiveInteger", (XDataType) xIntegerType5, 6, (IntNum) null, IntNum.zero());
        nonPositiveIntegerType = xIntegerType6;
        negativeIntegerType = new XIntegerType("negativeInteger", (XDataType) xIntegerType6, 7, (IntNum) null, IntNum.minusOne());
        XIntegerType xIntegerType7 = new XIntegerType("nonNegativeInteger", (XDataType) xIntegerType5, 12, IntNum.zero(), (IntNum) null);
        nonNegativeIntegerType = xIntegerType7;
        XIntegerType xIntegerType8 = new XIntegerType("unsignedLong", (XDataType) xIntegerType7, 13, IntNum.zero(), IntNum.valueOf("18446744073709551615"));
        unsignedLongType = xIntegerType8;
        XIntegerType xIntegerType9 = new XIntegerType("unsignedInt", (XDataType) xIntegerType8, 14, IntNum.zero(), IntNum.make((long) InternalZipConstants.ZIP_64_LIMIT));
        unsignedIntType = xIntegerType9;
        XIntegerType xIntegerType10 = new XIntegerType("unsignedShort", (XDataType) xIntegerType9, 15, IntNum.zero(), IntNum.make(65535));
        unsignedShortType = xIntegerType10;
        unsignedByteType = new XIntegerType("unsignedByte", (XDataType) xIntegerType10, 16, IntNum.zero(), IntNum.make(255));
        positiveIntegerType = new XIntegerType("positiveInteger", (XDataType) xIntegerType7, 17, IntNum.one(), (IntNum) null);
    }

    public boolean isUnsignedType() {
        return this.isUnsignedType;
    }

    public XIntegerType(String str, XDataType xDataType, int i, IntNum intNum, IntNum intNum2) {
        this((Object) str, xDataType, i, intNum, intNum2);
        this.isUnsignedType = str.startsWith("unsigned");
    }

    public XIntegerType(Object obj, XDataType xDataType, int i, IntNum intNum, IntNum intNum2) {
        super(obj, typeIntNum, i);
        this.minValue = intNum;
        this.maxValue = intNum2;
        this.baseType = xDataType;
    }

    public boolean isInstance(Object obj) {
        if (!(obj instanceof IntNum)) {
            return false;
        }
        XDataType xDataType = integerType;
        if (this == xDataType) {
            return true;
        }
        if (obj instanceof XInteger) {
            xDataType = ((XInteger) obj).getIntegerType();
        }
        while (xDataType != null) {
            if (xDataType == this) {
                return true;
            }
            xDataType = xDataType.baseType;
        }
        return false;
    }

    public Object coerceFromObject(Object obj) {
        return valueOf((IntNum) obj);
    }

    public IntNum valueOf(IntNum intNum) {
        IntNum intNum2;
        if (this == integerType) {
            return intNum;
        }
        IntNum intNum3 = this.minValue;
        if ((intNum3 == null || IntNum.compare(intNum, intNum3) >= 0) && ((intNum2 = this.maxValue) == null || IntNum.compare(intNum, intNum2) <= 0)) {
            return new XInteger(intNum, this);
        }
        throw new ClassCastException("cannot cast " + intNum + " to " + this.name);
    }

    public Object cast(Object obj) {
        if (obj instanceof Boolean) {
            return valueOf(((Boolean) obj).booleanValue() ? IntNum.one() : IntNum.zero());
        } else if (obj instanceof IntNum) {
            return valueOf((IntNum) obj);
        } else {
            if (obj instanceof BigDecimal) {
                return valueOf(Arithmetic.asIntNum((BigDecimal) obj));
            }
            if (obj instanceof RealNum) {
                return valueOf(((RealNum) obj).toExactInt(3));
            }
            if (obj instanceof Number) {
                return valueOf(RealNum.toExactInt(((Number) obj).doubleValue(), 3));
            }
            return super.cast(obj);
        }
    }

    public Object valueOf(String str) {
        return valueOf(IntNum.valueOf(str.trim(), 10));
    }

    public IntNum valueOf(String str, int i) {
        return valueOf(IntNum.valueOf(str.trim(), i));
    }
}
