package gnu.lists;

import java.io.PrintWriter;

public class Strings {
    public static void makeUpperCase(CharSeq charSeq) {
        int length = charSeq.length();
        while (true) {
            length--;
            if (length >= 0) {
                charSeq.setCharAt(length, Character.toUpperCase(charSeq.charAt(length)));
            } else {
                return;
            }
        }
    }

    public static void makeLowerCase(CharSeq charSeq) {
        int length = charSeq.length();
        while (true) {
            length--;
            if (length >= 0) {
                charSeq.setCharAt(length, Character.toLowerCase(charSeq.charAt(length)));
            } else {
                return;
            }
        }
    }

    public static void makeCapitalize(CharSeq charSeq) {
        int length = charSeq.length();
        char c = ' ';
        for (int i = 0; i < length; i++) {
            char charAt = charSeq.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                c = Character.toTitleCase(charAt);
            } else {
                c = Character.toLowerCase(charAt);
            }
            charSeq.setCharAt(i, c);
        }
    }

    public static void printQuoted(CharSequence charSequence, PrintWriter printWriter, int i) {
        int length = charSequence.length();
        printWriter.print('\"');
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i2);
            if (charAt == '\\' || charAt == '\"') {
                printWriter.print('\\');
            } else if (i > 0) {
                if (charAt == 10) {
                    printWriter.print("\\n");
                } else if (charAt == 13) {
                    printWriter.print("\\r");
                } else if (charAt == 9) {
                    printWriter.print("\\t");
                } else if (charAt == 7) {
                    printWriter.print("\\a");
                } else if (charAt == 8) {
                    printWriter.print("\\b");
                } else if (charAt == 11) {
                    printWriter.print("\\v");
                } else if (charAt == 12) {
                    printWriter.print("\\f");
                }
            }
            printWriter.print(charAt);
        }
        printWriter.print('\"');
    }
}
