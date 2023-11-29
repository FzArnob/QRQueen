package gnu.kawa.lispexpr;

import gnu.lists.PairWithPosition;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderQuote extends ReadTableEntry {
    Object magicSymbol;
    Object magicSymbol2;
    char next;

    public ReaderQuote(Object obj) {
        this.magicSymbol = obj;
    }

    public ReaderQuote(Object obj, char c, Object obj2) {
        this.next = c;
        this.magicSymbol = obj;
        this.magicSymbol2 = obj2;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        LispReader lispReader = (LispReader) lexer;
        String name = lispReader.getName();
        int lineNumber = lispReader.getLineNumber() + 1;
        int columnNumber = lispReader.getColumnNumber() + 1;
        Object obj = this.magicSymbol;
        if (this.next != 0) {
            int read = lispReader.read();
            if (read == this.next) {
                obj = this.magicSymbol2;
            } else if (read >= 0) {
                lispReader.unread(read);
            }
        }
        return PairWithPosition.make(obj, PairWithPosition.make(lispReader.readObject(), lispReader.makeNil(), name, lispReader.getLineNumber() + 1, lispReader.getColumnNumber() + 1), name, lineNumber, columnNumber);
    }
}
