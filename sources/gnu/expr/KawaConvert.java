package gnu.expr;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import gnu.lists.Convert;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.text.Char;
import net.lingala.zip4j.util.InternalZipConstants;

public class KawaConvert extends Convert {
    public static Convert instance = new KawaConvert();

    public static Convert getInstance() {
        return instance;
    }

    public static void setInstance(Convert convert) {
        instance = convert;
    }

    public Object charToObject(char c) {
        return Char.make(c);
    }

    public char objectToChar(Object obj) {
        return ((Char) obj).charValue();
    }

    public Object byteToObject(byte b) {
        return IntNum.make((int) b);
    }

    public Object shortToObject(short s) {
        return IntNum.make((int) s);
    }

    public Object intToObject(int i) {
        return IntNum.make(i);
    }

    public Object longToObject(long j) {
        return IntNum.make(j);
    }

    public Object byteToObjectUnsigned(byte b) {
        return IntNum.make((int) b & Ev3Constants.Opcode.TST);
    }

    public Object shortToObjectUnsigned(short s) {
        return IntNum.make((int) s & 65535);
    }

    public Object intToObjectUnsigned(int i) {
        return IntNum.make(((long) i) & InternalZipConstants.ZIP_64_LIMIT);
    }

    public Object longToObjectUnsigned(long j) {
        return IntNum.makeU(j);
    }

    public Object floatToObject(float f) {
        return DFloNum.make((double) f);
    }

    public Object doubleToObject(double d) {
        return DFloNum.make(d);
    }
}
