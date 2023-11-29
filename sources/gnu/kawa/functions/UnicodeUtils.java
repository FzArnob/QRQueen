package gnu.kawa.functions;

import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.text.BreakIterator;

public class UnicodeUtils {
    static final Symbol Cc;
    static final Symbol Cf;
    static final Symbol Cn;
    static final Symbol Co;
    static final Symbol Cs;
    static final Symbol Ll;
    static final Symbol Lm;
    static final Symbol Lo;
    static final Symbol Lt;
    static final Symbol Lu;
    static final Symbol Mc;
    static final Symbol Me;
    static final Symbol Mn;
    static final Symbol Nd;
    static final Symbol Nl;
    static final Symbol No;
    static final Symbol Pc;
    static final Symbol Pd;
    static final Symbol Pe;
    static final Symbol Pf;
    static final Symbol Pi;
    static final Symbol Po;
    static final Symbol Ps;
    static final Symbol Sc;
    static final Symbol Sk;
    static final Symbol Sm;
    static final Symbol So;
    static final Symbol Zl;
    static final Symbol Zp;
    static final Symbol Zs;

    public static boolean isWhitespace(int i) {
        if (i != 32 && (i < 9 || i > 13)) {
            if (i < 133) {
                return false;
            }
            if (!(i == 133 || i == 160 || i == 5760 || i == 6158)) {
                if (i < 8192 || i > 12288) {
                    return false;
                }
                return i <= 8202 || i == 8232 || i == 8233 || i == 8239 || i == 8287 || i == 12288;
            }
        }
        return true;
    }

    public static String capitalize(String str) {
        StringBuilder sb = new StringBuilder();
        BreakIterator wordInstance = BreakIterator.getWordInstance();
        wordInstance.setText(str);
        int first = wordInstance.first();
        int next = wordInstance.next();
        while (true) {
            int i = next;
            int i2 = first;
            first = i;
            if (first == -1) {
                return sb.toString();
            }
            boolean z = false;
            int i3 = i2;
            while (true) {
                if (i3 >= first) {
                    break;
                } else if (Character.isLetter(str.codePointAt(i3))) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                sb.append(str, i2, first);
            } else {
                sb.append(Character.toTitleCase(str.charAt(i2)));
                sb.append(str.substring(i2 + 1, first).toLowerCase());
            }
            next = wordInstance.next();
        }
    }

    public static String foldCase(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return "";
        }
        StringBuilder sb = null;
        int i = 0;
        int i2 = 0;
        while (true) {
            char charAt = i == length ? 65535 : charSequence.charAt(i);
            boolean z = charAt == 931 || charAt == 963 || charAt == 962;
            if (charAt < 0 || charAt == 304 || charAt == 305 || z) {
                if (sb == null && charAt >= 0) {
                    sb = new StringBuilder();
                }
                if (i > i2) {
                    String lowerCase = charSequence.subSequence(i2, i).toString().toUpperCase().toLowerCase();
                    if (sb == null) {
                        return lowerCase;
                    }
                    sb.append(lowerCase);
                }
                if (charAt < 0) {
                    return sb.toString();
                }
                if (z) {
                    charAt = 963;
                }
                sb.append((char) charAt);
                i2 = i + 1;
            }
            i++;
        }
    }

    public static Symbol generalCategory(int i) {
        switch (Character.getType(i)) {
            case 1:
                return Lu;
            case 2:
                return Ll;
            case 3:
                return Lt;
            case 4:
                return Lm;
            case 5:
                return Lo;
            case 6:
                return Mn;
            case 7:
                return Me;
            case 8:
                return Mc;
            case 9:
                return Nd;
            case 10:
                return Nl;
            case 11:
                return No;
            case 12:
                return Zs;
            case 13:
                return Zl;
            case 14:
                return Zp;
            case 15:
                return Cc;
            case 16:
                return Cf;
            case 18:
                return Co;
            case 19:
                return Cs;
            case 20:
                return Pd;
            case 21:
                return Ps;
            case 22:
                return Pe;
            case 23:
                return Pc;
            case 24:
                return Po;
            case 25:
                return Sm;
            case 26:
                return Sc;
            case 27:
                return Sk;
            case 28:
                return So;
            case 29:
                return Pi;
            case 30:
                return Pf;
            default:
                return Cn;
        }
    }

    static {
        Namespace namespace = Namespace.EmptyNamespace;
        Mc = namespace.getSymbol("Mc");
        Pc = namespace.getSymbol("Pc");
        Cc = namespace.getSymbol("Cc");
        Sc = namespace.getSymbol("Sc");
        Pd = namespace.getSymbol("Pd");
        Nd = namespace.getSymbol("Nd");
        Me = namespace.getSymbol("Me");
        Pe = namespace.getSymbol("Pe");
        Pf = namespace.getSymbol("Pf");
        Cf = namespace.getSymbol("Cf");
        Pi = namespace.getSymbol("Pi");
        Nl = namespace.getSymbol("Nl");
        Zl = namespace.getSymbol("Zl");
        Ll = namespace.getSymbol("Ll");
        Sm = namespace.getSymbol("Sm");
        Lm = namespace.getSymbol("Lm");
        Sk = namespace.getSymbol("Sk");
        Mn = namespace.getSymbol("Mn");
        Lo = namespace.getSymbol("Lo");
        No = namespace.getSymbol("No");
        Po = namespace.getSymbol("Po");
        So = namespace.getSymbol("So");
        Zp = namespace.getSymbol("Zp");
        Co = namespace.getSymbol("Co");
        Zs = namespace.getSymbol("Zs");
        Ps = namespace.getSymbol("Ps");
        Cs = namespace.getSymbol("Cs");
        Lt = namespace.getSymbol("Lt");
        Cn = namespace.getSymbol("Cn");
        Lu = namespace.getSymbol("Lu");
    }
}
