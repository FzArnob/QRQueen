package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderMacro extends ReaderMisc {
    Procedure procedure;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReaderMacro(Procedure procedure2, boolean z) {
        super(z ? 6 : 5);
        this.procedure = procedure2;
    }

    public ReaderMacro(Procedure procedure2) {
        super(5);
        this.procedure = procedure2;
    }

    public boolean isNonTerminating() {
        return this.kind == 6;
    }

    public Procedure getProcedure() {
        return this.procedure;
    }

    public Object read(Lexer lexer, int i, int i2) throws IOException, SyntaxException {
        try {
            return this.procedure.apply2(lexer.getPort(), Char.make(i));
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
