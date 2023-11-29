package gnu.lists;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import net.lingala.zip4j.util.InternalZipConstants;

public class Convert {
    public static Convert instance = new Convert();

    public static Convert getInstance() {
        return instance;
    }

    public static void setInstance(Convert convert) {
        instance = convert;
    }

    public Object booleanToObject(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean objectToBoolean(Object obj) {
        return !(obj instanceof Boolean) || ((Boolean) obj).booleanValue();
    }

    public static Object toObject(boolean z) {
        return instance.booleanToObject(z);
    }

    public static boolean toBoolean(Object obj) {
        return instance.objectToBoolean(obj);
    }

    public Object charToObject(char c) {
        return new Character(c);
    }

    public char objectToChar(Object obj) {
        return ((Character) obj).charValue();
    }

    public static Object toObject(char c) {
        return instance.charToObject(c);
    }

    public static char toChar(Object obj) {
        return instance.objectToChar(obj);
    }

    public Object byteToObject(byte b) {
        return new Byte(b);
    }

    public byte objectToByte(Object obj) {
        return ((Number) obj).byteValue();
    }

    public static Object toObject(byte b) {
        return instance.byteToObject(b);
    }

    public static byte toByte(Object obj) {
        return instance.objectToByte(obj);
    }

    public Object byteToObjectUnsigned(byte b) {
        return new Integer(b & Ev3Constants.Opcode.TST);
    }

    public byte objectToByteUnsigned(Object obj) {
        return ((Number) obj).byteValue();
    }

    public static Object toObjectUnsigned(byte b) {
        return instance.byteToObjectUnsigned(b);
    }

    public static byte toByteUnsigned(Object obj) {
        return instance.objectToByteUnsigned(obj);
    }

    public Object shortToObject(short s) {
        return new Short(s);
    }

    public short objectToShort(Object obj) {
        return ((Number) obj).shortValue();
    }

    public static Object toObject(short s) {
        return instance.shortToObject(s);
    }

    public static short toShort(Object obj) {
        return instance.objectToShort(obj);
    }

    public Object shortToObjectUnsigned(short s) {
        return new Integer(s & 65535);
    }

    public short objectToShortUnsigned(Object obj) {
        return ((Number) obj).shortValue();
    }

    public static Object toObjectUnsigned(short s) {
        return instance.shortToObjectUnsigned(s);
    }

    public static short toShortUnsigned(Object obj) {
        return instance.objectToShortUnsigned(obj);
    }

    public Object intToObject(int i) {
        return new Integer(i);
    }

    public int objectToInt(Object obj) {
        return ((Number) obj).intValue();
    }

    public static Object toObject(int i) {
        return instance.intToObject(i);
    }

    public static int toInt(Object obj) {
        return instance.objectToInt(obj);
    }

    public Object intToObjectUnsigned(int i) {
        if (i >= 0) {
            return new Integer(i);
        }
        return new Long(((long) i) & InternalZipConstants.ZIP_64_LIMIT);
    }

    public int objectToIntUnsigned(Object obj) {
        return ((Number) obj).intValue();
    }

    public static Object toObjectUnsigned(int i) {
        return instance.intToObjectUnsigned(i);
    }

    public static int toIntUnsigned(Object obj) {
        return instance.objectToIntUnsigned(obj);
    }

    public Object longToObject(long j) {
        return new Long(j);
    }

    public long objectToLong(Object obj) {
        return ((Number) obj).longValue();
    }

    public static Object toObject(long j) {
        return instance.longToObject(j);
    }

    public static long toLong(Object obj) {
        return instance.objectToLong(obj);
    }

    public Object longToObjectUnsigned(long j) {
        return new Long(j);
    }

    public long objectToLongUnsigned(Object obj) {
        return ((Number) obj).longValue();
    }

    public static Object toObjectUnsigned(long j) {
        return instance.longToObjectUnsigned(j);
    }

    public static long toLongUnsigned(Object obj) {
        return instance.objectToLongUnsigned(obj);
    }

    public Object floatToObject(float f) {
        return new Float(f);
    }

    public float objectToFloat(Object obj) {
        return ((Number) obj).floatValue();
    }

    public static Object toObject(float f) {
        return instance.floatToObject(f);
    }

    public static float toFloat(Object obj) {
        return instance.objectToFloat(obj);
    }

    public Object doubleToObject(double d) {
        return new Double(d);
    }

    public double objectToDouble(Object obj) {
        return ((Number) obj).doubleValue();
    }

    public static Object toObject(double d) {
        return instance.doubleToObject(d);
    }

    public static double toDouble(Object obj) {
        return instance.objectToDouble(obj);
    }

    public static double parseDouble(String str) {
        return Double.parseDouble(str);
    }
}
