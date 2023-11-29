package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatchMacro extends ReaderMisc {
    Procedure procedure;

    public ReaderDispatchMacro(Procedure procedure2) {
        super(5);
        this.procedure = procedure2;
    }

    public Procedure getProcedure() {
        return this.procedure;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        try {
            return this.procedure.apply3(lexer.getPort(), Char.make(i), IntNum.make(i2));
        } catch (IOException e) {
            throw e;
        } catch (SyntaxException e2) {
            throw e2;
        } catch (Throwable th) {
            lexer.fatal("reader macro '" + this.procedure + "' threw: " + th);
            return null;
        }
    }
}
