package gnu.kawa.lispexpr;

import gnu.lists.Pair;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderParens extends ReadTableEntry {
    private static ReaderParens instance;
    char close;
    Object command;
    int kind;
    char open;

    public int getKind() {
        return this.kind;
    }

    public static ReaderParens getInstance(char c, char c2) {
        return getInstance(c, c2, 5);
    }

    public static ReaderParens getInstance(char c, char c2, int i) {
        if (c != '(' || c2 != ')' || i != 5) {
            return new ReaderParens(c, c2, i, (Object) null);
        }
        if (instance == null) {
            instance = new ReaderParens(c, c2, i, (Object) null);
        }
        return instance;
    }

    public static ReaderParens getInstance(char c, char c2, int i, Object obj) {
        if (obj == null) {
            return getInstance(c, c2, i);
        }
        return new ReaderParens(c, c2, i, obj);
    }

    public ReaderParens(char c, char c2, int i, Object obj) {
        this.open = c;
        this.close = c2;
        this.kind = i;
        this.command = obj;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        LispReader lispReader = (LispReader) lexer;
        Object readList = readList(lispReader, i, i2, this.close);
        if (this.command == null) {
            return readList;
        }
        LineBufferedReader port = lexer.getPort();
        Pair makePair = lispReader.makePair(this.command, port.getLineNumber(), port.getColumnNumber());
        lispReader.setCdr(makePair, readList);
        return makePair;
    }

    public static Object readList(LispReader lispReader, int i, int i2, int i3) throws IOException, SyntaxException {
        Object obj;
        ReadTableEntry readTableEntry;
        LispReader lispReader2 = lispReader;
        int i4 = i3;
        LineBufferedReader port = lispReader.getPort();
        char pushNesting = lispReader2.pushNesting(i4 == 93 ? '[' : '(');
        int lineNumber = port.getLineNumber();
        int columnNumber = port.getColumnNumber();
        try {
            Object makeNil = lispReader.makeNil();
            ReadTable current = ReadTable.getCurrent();
            Object obj2 = null;
            boolean z = false;
            loop0:
            while (true) {
                boolean z2 = false;
                while (true) {
                    int lineNumber2 = port.getLineNumber();
                    int columnNumber2 = port.getColumnNumber();
                    int read = port.read();
                    if (read == i4) {
                        break loop0;
                    }
                    if (read < 0) {
                        lispReader2.eofError("unexpected EOF in list starting here", lineNumber + 1, columnNumber);
                    }
                    Object obj3 = obj;
                    if (read == 46) {
                        read = port.peek();
                        readTableEntry = current.lookup(read);
                        int kind2 = readTableEntry.getKind();
                        if (!(kind2 == 1 || kind2 == 5)) {
                            if (kind2 != 0) {
                                readTableEntry = ReadTableEntry.getConstituentInstance();
                                obj = obj3;
                                read = 46;
                            }
                        }
                        port.skip();
                        columnNumber2++;
                        if (read == i4) {
                            lispReader2.error("unexpected '" + ((char) i4) + "' after '.'");
                            break loop0;
                        }
                        if (read < 0) {
                            lispReader2.eofError("unexpected EOF in list starting here", lineNumber + 1, columnNumber);
                        }
                        if (z) {
                            lispReader2.error("multiple '.' in list");
                            makeNil = lispReader.makeNil();
                            obj = null;
                            z2 = false;
                        } else {
                            obj = obj3;
                        }
                        z = true;
                    } else {
                        readTableEntry = current.lookup(read);
                        obj = obj3;
                    }
                    Object readValues = lispReader2.readValues(read, readTableEntry, current);
                    if (readValues != Values.empty) {
                        Object handlePostfix = lispReader2.handlePostfix(readValues, current, lineNumber2, columnNumber2);
                        if (z2) {
                            break;
                        }
                        if (z) {
                            z2 = true;
                        } else {
                            if (obj == null) {
                                columnNumber2 = columnNumber - 1;
                                lineNumber2 = lineNumber;
                            }
                            handlePostfix = lispReader2.makePair(handlePostfix, lineNumber2, columnNumber2);
                        }
                        if (obj == null) {
                            makeNil = handlePostfix;
                        } else {
                            lispReader2.setCdr(obj, handlePostfix);
                        }
                        obj = handlePostfix;
                    }
                }
                lispReader2.error("multiple values after '.'");
                makeNil = lispReader.makeNil();
                obj2 = null;
            }
            return makeNil;
        } finally {
            lispReader2.popNesting(pushNesting);
        }
    }
}
