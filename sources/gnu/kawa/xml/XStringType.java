package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.xml.TextUtils;
import java.util.regex.Pattern;

public class XStringType extends XDataType {
    public static final XStringType ENTITYType;
    public static final XStringType IDREFType;
    public static final XStringType IDType;
    public static final XStringType NCNameType;
    public static final XStringType NMTOKENType;
    public static final XStringType NameType;
    static ClassType XStringType = ClassType.make("gnu.kawa.xml.XString");
    public static final XStringType languageType;
    public static final XStringType normalizedStringType;
    public static final XStringType tokenType;
    Pattern pattern;

    static {
        XStringType xStringType = new XStringType("normalizedString", stringType, 39, (String) null);
        normalizedStringType = xStringType;
        XStringType xStringType2 = new XStringType("token", xStringType, 40, (String) null);
        tokenType = xStringType2;
        languageType = new XStringType("language", xStringType2, 41, "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*");
        NMTOKENType = new XStringType("NMTOKEN", xStringType2, 42, "\\c+");
        XStringType xStringType3 = new XStringType("Name", xStringType2, 43, (String) null);
        NameType = xStringType3;
        XStringType xStringType4 = new XStringType("NCName", xStringType3, 44, (String) null);
        NCNameType = xStringType4;
        IDType = new XStringType("ID", xStringType4, 45, (String) null);
        IDREFType = new XStringType("IDREF", xStringType4, 46, (String) null);
        ENTITYType = new XStringType("ENTITY", xStringType4, 47, (String) null);
    }

    public XStringType(String str, XDataType xDataType, int i, String str2) {
        super(str, XStringType, i);
        this.baseType = xDataType;
        if (str2 != null) {
            this.pattern = Pattern.compile(str2);
        }
    }

    public boolean isInstance(Object obj) {
        if (!(obj instanceof XString)) {
            return false;
        }
        for (XDataType stringType = ((XString) obj).getStringType(); stringType != null; stringType = stringType.baseType) {
            if (stringType == this) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if (r5 == gnu.xml.TextUtils.replaceWhitespace(r5, r4.typeCode != 39)) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r0.matcher(r5).matches() == false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String matches(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r4.typeCode
            r1 = 1
            r2 = 0
            switch(r0) {
                case 39: goto L_0x0025;
                case 40: goto L_0x0025;
                case 41: goto L_0x0007;
                case 42: goto L_0x0020;
                case 43: goto L_0x001b;
                case 44: goto L_0x0016;
                case 45: goto L_0x0016;
                case 46: goto L_0x0016;
                case 47: goto L_0x0016;
                default: goto L_0x0007;
            }
        L_0x0007:
            java.util.regex.Pattern r0 = r4.pattern
            if (r0 == 0) goto L_0x0036
            java.util.regex.Matcher r5 = r0.matcher(r5)
            boolean r5 = r5.matches()
            if (r5 == 0) goto L_0x0035
            goto L_0x0036
        L_0x0016:
            boolean r5 = gnu.xml.XName.isNCName(r5)
            goto L_0x0037
        L_0x001b:
            boolean r5 = gnu.xml.XName.isName(r5)
            goto L_0x0037
        L_0x0020:
            boolean r5 = gnu.xml.XName.isNmToken(r5)
            goto L_0x0037
        L_0x0025:
            int r0 = r4.typeCode
            r3 = 39
            if (r0 == r3) goto L_0x002d
            r0 = 1
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            java.lang.String r0 = gnu.xml.TextUtils.replaceWhitespace(r5, r0)
            if (r5 != r0) goto L_0x0035
            goto L_0x0036
        L_0x0035:
            r1 = 0
        L_0x0036:
            r5 = r1
        L_0x0037:
            if (r5 == 0) goto L_0x003b
            r5 = 0
            goto L_0x0050
        L_0x003b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "not a valid XML "
            r5.append(r0)
            java.lang.String r0 = r4.getName()
            r5.append(r0)
            java.lang.String r5 = r5.toString()
        L_0x0050:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.XStringType.matches(java.lang.String):java.lang.String");
    }

    public Object valueOf(String str) {
        String replaceWhitespace = TextUtils.replaceWhitespace(str, this != normalizedStringType);
        if (matches(replaceWhitespace) == null) {
            return new XString(replaceWhitespace, this);
        }
        throw new ClassCastException("cannot cast " + replaceWhitespace + " to " + this.name);
    }

    public Object cast(Object obj) {
        if (obj instanceof XString) {
            XString xString = (XString) obj;
            if (xString.getStringType() == this) {
                return xString;
            }
        }
        return valueOf((String) stringType.cast(obj));
    }

    public static XString makeNCName(String str) {
        return (XString) NCNameType.valueOf(str);
    }
}
