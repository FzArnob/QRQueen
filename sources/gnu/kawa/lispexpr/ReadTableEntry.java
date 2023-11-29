package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public abstract class ReadTableEntry {
    public static final ReadTableEntry brace = new ReaderMisc(2);
    public static final ReadTableEntry constituent = new ReaderMisc(2);
    public static final ReadTableEntry illegal = new ReaderMisc(0);
    public static final ReadTableEntry multipleEscape = new ReaderMisc(4);
    public static final ReadTableEntry singleEscape = new ReaderMisc(3);
    public static final ReadTableEntry whitespace = new ReaderMisc(1);

    public int getKind() {
        return 5;
    }

    public static ReadTableEntry getIllegalInstance() {
        return illegal;
    }

    public static ReadTableEntry getWhitespaceInstance() {
        return whitespace;
    }

    public static ReadTableEntry getSingleEscapeInstance() {
        return singleEscape;
    }

    public static ReadTableEntry getMultipleEscapeInstance() {
        return multipleEscape;
    }

    public static ReadTableEntry getDigitInstance() {
        return constituent;
    }

    public static ReadTableEntry getConstituentInstance() {
        return constituent;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        throw new Error("invalid character");
    }
}
