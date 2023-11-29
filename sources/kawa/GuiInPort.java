package kawa;

import gnu.mapping.OutPort;
import gnu.mapping.TtyInPort;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

class GuiInPort extends TtyInPort {
    ReplDocument document;

    public GuiInPort(Reader reader, Path path, OutPort outPort, ReplDocument replDocument) {
        super(reader, path, outPort);
        this.document = replDocument;
    }

    public void emitPrompt(String str) throws IOException {
        this.document.write(str, ReplDocument.promptStyle);
    }
}
