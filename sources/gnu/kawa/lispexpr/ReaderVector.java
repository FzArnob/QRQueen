package gnu.kawa.lispexpr;

import gnu.expr.QuoteExp;
import gnu.lists.FVector;
import gnu.mapping.InPort;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.Vector;

public class ReaderVector extends ReadTableEntry {
    char close;

    public ReaderVector(char c) {
        this.close = c;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        return readVector((LispReader) lexer, lexer.getPort(), i2, this.close);
    }

    public static FVector readVector(LispReader lispReader, LineBufferedReader lineBufferedReader, int i, char c) throws IOException, SyntaxException {
        char c2;
        boolean z = lineBufferedReader instanceof InPort;
        if (z) {
            InPort inPort = (InPort) lineBufferedReader;
            c2 = inPort.readState;
            inPort.readState = c == ']' ? '[' : '(';
        } else {
            c2 = ' ';
        }
        try {
            Vector vector = new Vector();
            ReadTable current = ReadTable.getCurrent();
            while (true) {
                int read = lispReader.read();
                if (read < 0) {
                    lispReader.eofError("unexpected EOF in vector");
                }
                if (read == c) {
                    break;
                }
                Object readValues = lispReader.readValues(read, current);
                if (readValues instanceof Values) {
                    for (Object addElement : ((Values) readValues).getValues()) {
                        vector.addElement(addElement);
                    }
                } else {
                    if (readValues == QuoteExp.voidExp) {
                        readValues = Values.empty;
                    }
                    vector.addElement(readValues);
                }
            }
            Object[] objArr = new Object[vector.size()];
            vector.copyInto(objArr);
            return new FVector(objArr);
        } finally {
            if (z) {
                ((InPort) lineBufferedReader).readState = c2;
            }
        }
    }
}
