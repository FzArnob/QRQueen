package gnu.kawa.lispexpr;

public class ReaderIgnoreRestOfLine extends ReadTableEntry {
    static ReaderIgnoreRestOfLine instance = new ReaderIgnoreRestOfLine();

    public static ReaderIgnoreRestOfLine getInstance() {
        return instance;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object read(gnu.text.Lexer r1, int r2, int r3) throws java.io.IOException, gnu.text.SyntaxException {
        /*
            r0 = this;
        L_0x0000:
            int r2 = r1.read()
            if (r2 >= 0) goto L_0x0009
            java.lang.Object r1 = gnu.lists.Sequence.eofValue
            return r1
        L_0x0009:
            r3 = 10
            if (r2 == r3) goto L_0x0011
            r3 = 13
            if (r2 != r3) goto L_0x0000
        L_0x0011:
            gnu.mapping.Values r1 = gnu.mapping.Values.empty
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.ReaderIgnoreRestOfLine.read(gnu.text.Lexer, int, int):java.lang.Object");
    }
}
