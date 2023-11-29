package gnu.kawa.lispexpr;

import gnu.kawa.util.RangeTable;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatch extends ReadTableEntry {
    int kind;
    RangeTable table;

    public int getKind() {
        return this.kind;
    }

    public void set(int i, Object obj) {
        this.table.set(i, i, obj);
    }

    public ReadTableEntry lookup(int i) {
        return (ReadTableEntry) this.table.lookup(i, (Object) null);
    }

    public ReaderDispatch() {
        this.table = new RangeTable();
        this.kind = 5;
    }

    public ReaderDispatch(boolean z) {
        this.table = new RangeTable();
        this.kind = z ? 6 : 5;
    }

    public static ReaderDispatch create(ReadTable readTable) {
        ReaderDispatch readerDispatch = new ReaderDispatch();
        ReaderDispatchMisc instance = ReaderDispatchMisc.getInstance();
        readerDispatch.set(58, instance);
        readerDispatch.set(66, instance);
        readerDispatch.set(68, instance);
        readerDispatch.set(69, instance);
        readerDispatch.set(70, instance);
        readerDispatch.set(73, instance);
        readerDispatch.set(79, instance);
        readerDispatch.set(82, instance);
        readerDispatch.set(83, instance);
        readerDispatch.set(84, instance);
        readerDispatch.set(85, instance);
        readerDispatch.set(88, instance);
        readerDispatch.set(124, instance);
        readerDispatch.set(59, instance);
        readerDispatch.set(33, instance);
        readerDispatch.set(92, instance);
        readerDispatch.set(61, instance);
        readerDispatch.set(35, instance);
        readerDispatch.set(47, instance);
        readerDispatch.set(39, new ReaderQuote(readTable.makeSymbol("function")));
        readerDispatch.set(40, new ReaderVector(')'));
        readerDispatch.set(60, new ReaderXmlElement());
        return readerDispatch;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        int read;
        int i3 = -1;
        while (true) {
            read = lexer.read();
            if (read < 0) {
                lexer.eofError("unexpected EOF after " + ((char) read));
            }
            if (read > 65536) {
                break;
            }
            char c = (char) read;
            int digit = Character.digit(c, 10);
            if (digit < 0) {
                read = Character.toUpperCase(c);
                break;
            }
            i3 = i3 < 0 ? digit : (i3 * 10) + digit;
        }
        ReadTableEntry readTableEntry = (ReadTableEntry) this.table.lookup(read, (Object) null);
        if (readTableEntry != null) {
            return readTableEntry.read(lexer, read, i3);
        }
        String name = lexer.getName();
        int lineNumber = lexer.getLineNumber() + 1;
        int columnNumber = lexer.getColumnNumber();
        lexer.error('e', name, lineNumber, columnNumber, "invalid dispatch character '" + ((char) read) + '\'');
        return Values.empty;
    }
}
