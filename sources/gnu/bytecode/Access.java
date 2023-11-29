package gnu.bytecode;

public class Access {
    public static final short ABSTRACT = 1024;
    public static final short ANNOTATION = 8192;
    public static final short BRIDGE = 64;
    public static final char CLASS_CONTEXT = 'C';
    public static final short CLASS_MODIFIERS = 30257;
    public static final short ENUM = 16384;
    public static final char FIELD_CONTEXT = 'F';
    public static final short FIELD_MODIFIERS = 20703;
    public static final short FINAL = 16;
    public static final char INNERCLASS_CONTEXT = 'I';
    public static final short INNERCLASS_MODIFIERS = 30239;
    public static final short INTERFACE = 512;
    public static final char METHOD_CONTEXT = 'M';
    public static final short METHOD_MODIFIERS = 7679;
    public static final short NATIVE = 256;
    public static final short PRIVATE = 2;
    public static final short PROTECTED = 4;
    public static final short PUBLIC = 1;
    public static final short STATIC = 8;
    public static final short STRICT = 2048;
    public static final short SUPER = 32;
    public static final short SYNCHRONIZED = 32;
    public static final short SYNTHETIC = 4096;
    public static final short TRANSIENT = 128;
    public static final short VARARGS = 128;
    public static final short VOLATILE = 64;

    public static String toString(int i) {
        return toString(i, 0);
    }

    public static String toString(int i, char c) {
        int i2 = c == 'C' ? 30257 : c == 'I' ? 30239 : c == 'F' ? 20703 : c == 'M' ? 7679 : 32767;
        short s = (short) ((~i2) & i);
        int i3 = i & i2;
        StringBuffer stringBuffer = new StringBuffer();
        if ((i3 & 1) != 0) {
            stringBuffer.append(" public");
        }
        if ((i3 & 2) != 0) {
            stringBuffer.append(" private");
        }
        if ((i3 & 4) != 0) {
            stringBuffer.append(" protected");
        }
        if ((i3 & 8) != 0) {
            stringBuffer.append(" static");
        }
        if ((i3 & 16) != 0) {
            stringBuffer.append(" final");
        }
        if ((i3 & 32) != 0) {
            stringBuffer.append(c == 'C' ? " super" : " synchronized");
        }
        if ((i3 & 64) != 0) {
            stringBuffer.append(c == 'M' ? " bridge" : " volatile");
        }
        if ((i3 & 128) != 0) {
            stringBuffer.append(c == 'M' ? " varargs" : " transient");
        }
        if ((i3 & 256) != 0) {
            stringBuffer.append(" native");
        }
        if ((i3 & 512) != 0) {
            stringBuffer.append(" interface");
        }
        if ((i3 & 1024) != 0) {
            stringBuffer.append(" abstract");
        }
        if ((i3 & 2048) != 0) {
            stringBuffer.append(" strict");
        }
        if ((i3 & 16384) != 0) {
            stringBuffer.append(" enum");
        }
        if ((i3 & 4096) != 0) {
            stringBuffer.append(" synthetic");
        }
        if ((i3 & 8192) != 0) {
            stringBuffer.append(" annotation");
        }
        if (s != 0) {
            stringBuffer.append(" unknown-flags:0x");
            stringBuffer.append(Integer.toHexString(s));
        }
        return stringBuffer.toString();
    }
}
