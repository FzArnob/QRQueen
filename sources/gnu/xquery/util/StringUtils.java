package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XIntegerType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static String ERROR_VALUE = "<error>";

    static String coerceToString(Object obj, String str, int i, String str2) {
        if (obj instanceof KNode) {
            obj = KNode.atomicValue(obj);
        }
        if ((obj == Values.empty || obj == null) && str2 != ERROR_VALUE) {
            return str2;
        }
        if ((obj instanceof UntypedAtomic) || (obj instanceof CharSequence) || (obj instanceof URI) || (obj instanceof Path)) {
            return obj.toString();
        }
        throw new WrongType(str, i, obj, str2 == ERROR_VALUE ? "xs:string" : "xs:string?");
    }

    public static Object lowerCase(Object obj) {
        return coerceToString(obj, "lower-case", 1, "").toLowerCase();
    }

    public static Object upperCase(Object obj) {
        return coerceToString(obj, "upper-case", 1, "").toUpperCase();
    }

    static double asDouble(Object obj) {
        if (!(obj instanceof Number)) {
            obj = NumberValue.numberValue(obj);
        }
        return ((Number) obj).doubleValue();
    }

    public static Object substring(Object obj, Object obj2) {
        double asDouble = asDouble(obj2);
        if (Double.isNaN(asDouble)) {
            return "";
        }
        int i = (int) (asDouble - 0.5d);
        int i2 = 0;
        if (i < 0) {
            i = 0;
        }
        String coerceToString = coerceToString(obj, "substring", 1, "");
        int length = coerceToString.length();
        while (true) {
            i--;
            if (i < 0) {
                return coerceToString.substring(i2);
            }
            if (i2 >= length) {
                return "";
            }
            int i3 = i2 + 1;
            char charAt = coerceToString.charAt(i2);
            if (charAt >= 55296 && charAt < 56320 && i3 < length) {
                i3++;
            }
            i2 = i3;
        }
    }

    public static Object substring(Object obj, Object obj2, Object obj3) {
        String coerceToString = coerceToString(obj, "substring", 1, "");
        int length = coerceToString.length();
        double floor = Math.floor(asDouble(obj2) - 0.5d);
        double floor2 = Math.floor(asDouble(obj3) + 0.5d) + floor;
        if (floor <= 0.0d) {
            floor = 0.0d;
        }
        double d = (double) length;
        if (floor2 > d) {
            floor2 = d;
        }
        if (floor2 <= floor) {
            return "";
        }
        int i = (int) floor;
        int i2 = ((int) floor2) - i;
        int i3 = 0;
        while (true) {
            i--;
            if (i < 0) {
                int i4 = i3;
                while (true) {
                    i2--;
                    if (i2 < 0) {
                        return coerceToString.substring(i3, i4);
                    }
                    if (i4 >= length) {
                        return "";
                    }
                    int i5 = i4 + 1;
                    char charAt = coerceToString.charAt(i4);
                    if (charAt >= 55296 && charAt < 56320 && i5 < length) {
                        i5++;
                    }
                    i4 = i5;
                }
            } else if (i3 >= length) {
                return "";
            } else {
                int i6 = i3 + 1;
                char charAt2 = coerceToString.charAt(i3);
                if (charAt2 >= 55296 && charAt2 < 56320 && i6 < length) {
                    i6++;
                }
                i3 = i6;
            }
        }
    }

    public static Object stringLength(Object obj) {
        String coerceToString = coerceToString(obj, "string-length", 1, "");
        int length = coerceToString.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char charAt = coerceToString.charAt(i);
            if (charAt >= 55296 && charAt < 56320 && i3 < length) {
                i3++;
            }
            i = i3;
            i2++;
        }
        return IntNum.make(i2);
    }

    public static Object substringBefore(Object obj, Object obj2) {
        int indexOf;
        String coerceToString = coerceToString(obj, "substring-before", 1, "");
        String coerceToString2 = coerceToString(obj2, "substring-before", 2, "");
        if (coerceToString2.length() != 0 && (indexOf = coerceToString.indexOf(coerceToString2)) >= 0) {
            return coerceToString.substring(0, indexOf);
        }
        return "";
    }

    public static Object substringAfter(Object obj, Object obj2) {
        String coerceToString = coerceToString(obj, "substring-after", 1, "");
        String coerceToString2 = coerceToString(obj2, "substring-after", 2, "");
        int length = coerceToString2.length();
        if (length == 0) {
            return coerceToString;
        }
        int indexOf = coerceToString.indexOf(coerceToString2);
        if (indexOf >= 0) {
            return coerceToString.substring(indexOf + length);
        }
        return "";
    }

    public static Object translate(Object obj, Object obj2, Object obj3) {
        char c;
        char c2;
        char c3;
        Object obj4 = obj;
        String coerceToString = coerceToString(obj4, "translate", 1, "");
        Object atomicValue = KNode.atomicValue(obj2);
        if ((atomicValue instanceof UntypedAtomic) || (atomicValue instanceof String)) {
            String obj5 = atomicValue.toString();
            int length = obj5.length();
            Object atomicValue2 = KNode.atomicValue(obj3);
            if ((atomicValue2 instanceof UntypedAtomic) || (atomicValue2 instanceof String)) {
                String obj6 = atomicValue2.toString();
                if (length == 0) {
                    return coerceToString;
                }
                int length2 = coerceToString.length();
                StringBuffer stringBuffer = new StringBuffer(length2);
                int length3 = obj6.length();
                int i = 0;
                while (i < length2) {
                    int i2 = i + 1;
                    char charAt = coerceToString.charAt(i);
                    if (charAt < 55296 || charAt >= 56320 || i2 >= length2) {
                        c = 0;
                    } else {
                        c = coerceToString.charAt(i2);
                        i2++;
                    }
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        }
                        int i5 = i3 + 1;
                        char charAt2 = obj5.charAt(i3);
                        if (charAt2 < 55296 || charAt2 >= 56320 || i5 >= length) {
                            c2 = 0;
                        } else {
                            c2 = obj5.charAt(i5);
                            i5++;
                        }
                        if (charAt2 == charAt && c2 == c) {
                            int i6 = 0;
                            while (true) {
                                if (i6 >= length3) {
                                    break;
                                }
                                int i7 = i6 + 1;
                                char charAt3 = obj6.charAt(i6);
                                if (charAt3 < 55296 || charAt3 >= 56320 || i7 >= length3) {
                                    c3 = 0;
                                } else {
                                    c3 = obj6.charAt(i7);
                                    i7++;
                                }
                                if (i4 == 0) {
                                    charAt = charAt3;
                                    break;
                                }
                                i4--;
                                i6 = i7;
                            }
                        } else {
                            i4++;
                            i3 = i5;
                        }
                    }
                    stringBuffer.append(charAt);
                    if (c != 0) {
                        stringBuffer.append(c);
                    }
                    i = i2;
                }
                return stringBuffer.toString();
            }
            throw new WrongType("translate", 3, obj4, "xs:string");
        }
        throw new WrongType("translate", 2, obj4, "xs:string");
    }

    public static Object stringPad(Object obj, Object obj2) {
        int intValue = ((Number) NumberValue.numberValue(obj2)).intValue();
        if (intValue > 0) {
            String coerceToString = coerceToString(obj, "string-pad", 1, "");
            StringBuffer stringBuffer = new StringBuffer(coerceToString.length() * intValue);
            for (int i = 0; i < intValue; i++) {
                stringBuffer.append(coerceToString);
            }
            return stringBuffer.toString();
        } else if (intValue == 0) {
            return "";
        } else {
            throw new IndexOutOfBoundsException("Invalid string-pad count");
        }
    }

    public static Object contains(Object obj, Object obj2) {
        return coerceToString(obj, "contains", 1, "").indexOf(coerceToString(obj2, "contains", 2, "")) < 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object startsWith(Object obj, Object obj2) {
        return coerceToString(obj, "starts-with", 1, "").startsWith(coerceToString(obj2, "starts-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object endsWith(Object obj, Object obj2) {
        return coerceToString(obj, "ends-with", 1, "").endsWith(coerceToString(obj2, "ends-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object stringJoin(Object obj, Object obj2) {
        StringBuffer stringBuffer = new StringBuffer();
        String coerceToString = coerceToString(obj2, "string-join", 2, ERROR_VALUE);
        int length = coerceToString.length();
        int i = 0;
        boolean z = false;
        while (true) {
            int nextIndex = Values.nextIndex(obj, i);
            if (nextIndex < 0) {
                return stringBuffer.toString();
            }
            Object nextValue = Values.nextValue(obj, i);
            if (nextValue != Values.empty) {
                if (z && length > 0) {
                    stringBuffer.append(coerceToString);
                }
                stringBuffer.append(TextUtils.stringValue(nextValue));
                z = true;
                i = nextIndex;
            }
        }
    }

    public static String concat$V(Object obj, Object obj2, Object[] objArr) {
        String stringValue = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(obj, "concat", 1));
        String stringValue2 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(obj2, "concat", 2));
        StringBuilder sb = new StringBuilder(stringValue);
        sb.append(stringValue2);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(objArr[i], "concat", i + 2)));
        }
        return sb.toString();
    }

    public static Object compare(Object obj, Object obj2, NamedCollator namedCollator) {
        if (obj == Values.empty || obj == null || obj2 == Values.empty || obj2 == null) {
            return Values.empty;
        }
        if (namedCollator == null) {
            namedCollator = NamedCollator.codepointCollation;
        }
        int compare = namedCollator.compare(obj.toString(), obj2.toString());
        if (compare < 0) {
            return IntNum.minusOne();
        }
        return compare > 0 ? IntNum.one() : IntNum.zero();
    }

    public static void stringToCodepoints$X(Object obj, CallContext callContext) {
        String coerceToString = coerceToString(obj, "string-to-codepoints", 1, "");
        int length = coerceToString.length();
        Consumer consumer = callContext.consumer;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int charAt = coerceToString.charAt(i);
            if (charAt >= 55296 && charAt < 56320 && i2 < length) {
                charAt = ((charAt - 55296) * 1024) + (coerceToString.charAt(i2) - 56320) + 65536;
                i2++;
            }
            consumer.writeInt(charAt);
            i = i2;
        }
    }

    private static void appendCodepoint(Object obj, StringBuffer stringBuffer) {
        int intValue = ((IntNum) XIntegerType.integerType.cast(obj)).intValue();
        if (intValue <= 0 || (intValue > 55295 && (intValue < 57344 || ((intValue > 65533 && intValue < 65536) || intValue > 1114111)))) {
            throw new IllegalArgumentException("codepoints-to-string: " + intValue + " is not a valid XML character [FOCH0001]");
        }
        if (intValue >= 65536) {
            stringBuffer.append((char) (((intValue - 65536) >> 10) + 55296));
            intValue = (intValue & 1023) + 56320;
        }
        stringBuffer.append((char) intValue);
    }

    public static String codepointsToString(Object obj) {
        if (obj == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof Values) {
            Values values = (Values) obj;
            int startPos = values.startPos();
            while (true) {
                startPos = values.nextPos(startPos);
                if (startPos == 0) {
                    break;
                }
                appendCodepoint(values.getPosPrevious(startPos), stringBuffer);
            }
        } else {
            appendCodepoint(obj, stringBuffer);
        }
        return stringBuffer.toString();
    }

    public static String encodeForUri(Object obj) {
        return URIPath.encodeForUri(coerceToString(obj, "encode-for-uri", 1, ""), 'U');
    }

    public static String iriToUri(Object obj) {
        return URIPath.encodeForUri(coerceToString(obj, "iri-to-uru", 1, ""), Access.INNERCLASS_CONTEXT);
    }

    public static String escapeHtmlUri(Object obj) {
        return URIPath.encodeForUri(coerceToString(obj, "escape-html-uri", 1, ""), 'H');
    }

    public static String normalizeSpace(Object obj) {
        String coerceToString = coerceToString(obj, "normalize-space", 1, "");
        int length = coerceToString.length();
        StringBuffer stringBuffer = null;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = coerceToString.charAt(i2);
            if (Character.isWhitespace(charAt)) {
                if (stringBuffer == null && i == 0 && i2 > 0) {
                    stringBuffer = new StringBuffer(coerceToString.substring(0, i2));
                }
                i++;
            } else {
                if (i > 0) {
                    if (stringBuffer != null) {
                        stringBuffer.append(' ');
                    } else if (i > 1 || i2 == 1 || coerceToString.charAt(i2 - 1) != ' ') {
                        stringBuffer = new StringBuffer();
                    }
                    i = 0;
                }
                if (stringBuffer != null) {
                    stringBuffer.append(charAt);
                }
            }
        }
        if (stringBuffer != null) {
            return stringBuffer.toString();
        }
        if (i > 0) {
            return "";
        }
        return coerceToString;
    }

    public static Pattern makePattern(String str, String str2) {
        int length = str2.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            length--;
            if (length >= 0) {
                char charAt = str2.charAt(length);
                if (charAt == 'i') {
                    i2 |= 66;
                } else if (charAt == 'm') {
                    i2 |= 8;
                } else if (charAt == 's') {
                    i2 |= 32;
                } else if (charAt == 'x') {
                    StringBuffer stringBuffer = new StringBuffer();
                    int length2 = str.length();
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length2) {
                        int i5 = i3 + 1;
                        char charAt2 = str.charAt(i3);
                        if (charAt2 == '\\' && i5 < length2) {
                            stringBuffer.append(charAt2);
                            char charAt3 = str.charAt(i5);
                            i5++;
                            charAt2 = charAt3;
                        } else if (charAt2 == '[') {
                            i4++;
                        } else if (charAt2 == ']') {
                            i4--;
                        } else if (i4 == 0 && Character.isWhitespace(charAt2)) {
                            i3 = i5;
                        }
                        stringBuffer.append(charAt2);
                        i3 = i5;
                    }
                    str = stringBuffer.toString();
                } else {
                    throw new IllegalArgumentException("unknown 'replace' flag");
                }
            } else {
                if (str.indexOf("{Is") >= 0) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    int length3 = str.length();
                    while (i < length3) {
                        int i6 = i + 1;
                        char charAt4 = str.charAt(i);
                        if (charAt4 != '\\' || i6 + 4 >= length3) {
                            stringBuffer2.append(charAt4);
                            i = i6;
                        } else {
                            stringBuffer2.append(charAt4);
                            i = i6 + 1;
                            char charAt5 = str.charAt(i6);
                            stringBuffer2.append(charAt5);
                            if ((charAt5 == 'p' || charAt5 == 'P') && str.charAt(i) == '{' && str.charAt(i + 1) == 'I' && str.charAt(i + 2) == 's') {
                                stringBuffer2.append('{');
                                stringBuffer2.append(Access.INNERCLASS_CONTEXT);
                                stringBuffer2.append('n');
                                i += 3;
                            }
                        }
                    }
                    str = stringBuffer2.toString();
                }
                return Pattern.compile(str, i2);
            }
        }
    }

    public static boolean matches(Object obj, String str) {
        return matches(obj, str, "");
    }

    public static boolean matches(Object obj, String str, String str2) {
        return makePattern(str, str2).matcher(coerceToString(obj, "matches", 1, "")).find();
    }

    public static String replace(Object obj, String str, String str2) {
        return replace(obj, str, str2, "");
    }

    public static String replace(Object obj, String str, String str2, String str3) {
        String coerceToString = coerceToString(obj, "replace", 1, "");
        int length = str2.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str2.charAt(i);
            if (charAt == '\\') {
                if (i2 < charAt) {
                    i = i2 + 1;
                    char charAt2 = str2.charAt(i2);
                    if (!(charAt2 == '\\' || charAt2 == '$')) {
                    }
                }
                throw new IllegalArgumentException("invalid replacement string [FORX0004]");
            }
            i = i2;
        }
        return makePattern(str, str3).matcher(coerceToString).replaceAll(str2);
    }

    public static void tokenize$X(Object obj, String str, CallContext callContext) {
        tokenize$X(obj, str, "", callContext);
    }

    public static void tokenize$X(Object obj, String str, String str2, CallContext callContext) {
        String coerceToString = coerceToString(obj, "tokenize", 1, "");
        Consumer consumer = callContext.consumer;
        Matcher matcher = makePattern(str, str2).matcher(coerceToString);
        if (coerceToString.length() != 0) {
            int i = 0;
            while (matcher.find()) {
                int start = matcher.start();
                consumer.writeObject(coerceToString.substring(i, start));
                i = matcher.end();
                if (i == start) {
                    throw new IllegalArgumentException("pattern matches empty string");
                }
            }
            consumer.writeObject(coerceToString.substring(i));
        }
    }

    public static Object codepointEqual(Object obj, Object obj2) {
        String coerceToString = coerceToString(obj, "codepoint-equal", 1, (String) null);
        String coerceToString2 = coerceToString(obj2, "codepoint-equal", 2, (String) null);
        if (coerceToString == null || coerceToString2 == null) {
            return Values.empty;
        }
        return coerceToString.equals(coerceToString2) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object normalizeUnicode(Object obj) {
        return normalizeUnicode(obj, "NFC");
    }

    public static Object normalizeUnicode(Object obj, String str) {
        String coerceToString = coerceToString(obj, "normalize-unicode", 1, "");
        if ("".equals(str.trim().toUpperCase())) {
            return coerceToString;
        }
        throw new UnsupportedOperationException("normalize-unicode: unicode string normalization not available");
    }
}
