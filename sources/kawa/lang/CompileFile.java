package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.InPort;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CompileFile {
    public static final Compilation read(String str, SourceMessages sourceMessages) throws IOException, SyntaxException {
        try {
            InPort openFile = InPort.openFile(str);
            Compilation read = read(openFile, sourceMessages);
            openFile.close();
            return read;
        } catch (FileNotFoundException e) {
            throw new WrappedException("compile-file: file not found: " + str, e);
        } catch (IOException e2) {
            throw new WrappedException("compile-file: read-error: " + str, e2);
        }
    }

    public static final Compilation read(InPort inPort, SourceMessages sourceMessages) throws IOException, SyntaxException {
        return Language.getDefaultLanguage().parse(inPort, sourceMessages, 0);
    }
}
