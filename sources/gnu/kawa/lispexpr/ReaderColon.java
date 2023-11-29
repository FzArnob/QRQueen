package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderColon extends ReadTableEntry {
    public int getKind() {
        return 6;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        LispReader lispReader = (LispReader) lexer;
        ReadTable current = ReadTable.getCurrent();
        int i3 = lispReader.tokenBufferLength;
        if (i == current.postfixLookupOperator) {
            int read = lispReader.read();
            if (read == 58) {
                return current.makeSymbol("::");
            }
            lispReader.tokenBufferAppend(i);
            i = read;
        }
        return lispReader.readAndHandleToken(i, i3, current);
    }
}
