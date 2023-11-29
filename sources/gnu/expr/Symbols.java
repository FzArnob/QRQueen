package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;

public class Symbols {
    private static int gensym_counter;

    private Symbols() {
    }

    static synchronized int generateInt() {
        int i;
        synchronized (Symbols.class) {
            i = gensym_counter + 1;
            gensym_counter = i;
        }
        return i;
    }

    public static final SimpleSymbol gentemp() {
        return SimpleSymbol.valueOf("GS." + Integer.toString(generateInt()));
    }

    public static String make(String str) {
        return str.intern();
    }

    public static final String intern(String str) {
        return make(str);
    }

    public static void print(String str, Consumer consumer) {
        if ((consumer instanceof OutPort) && ((OutPort) consumer).printReadable) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (!(Character.isLowerCase(charAt) || charAt == '!' || charAt == '$' || charAt == '%' || charAt == '&' || charAt == '*' || charAt == '/' || charAt == ':' || charAt == '<' || charAt == '=' || charAt == '>' || charAt == '?' || charAt == '~' || charAt == '_' || charAt == '^' || (((charAt == '+' || charAt == '-') && (i > 0 || length == 1)) || ((Character.isDigit(charAt) && i > 0) || (charAt == '.' && (i == 0 || str.charAt(i - 1) == '.')))))) {
                    consumer.write(92);
                }
                consumer.write((int) charAt);
            }
            return;
        }
        consumer.write(str);
    }
}
