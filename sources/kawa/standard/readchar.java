package kawa.standard;

import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0or1;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class readchar extends Procedure0or1 {
    public static final readchar peekChar = new readchar(true);
    public static final readchar readChar = new readchar(false);
    boolean peeking;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public readchar(boolean z) {
        super(z ? "peek-char" : "read-char");
        this.peeking = z;
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InPort inPort) {
        try {
            int peek = this.peeking ? inPort.peek() : inPort.read();
            if (peek < 0) {
                return Sequence.eofValue;
            }
            return Char.make(peek);
        } catch (IOException unused) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(Reader reader) {
        int i;
        try {
            if (this.peeking) {
                reader.mark(1);
                i = reader.read();
                reader.reset();
            } else {
                i = reader.read();
            }
            if (i < 0) {
                return Sequence.eofValue;
            }
            return Char.make(i);
        } catch (IOException unused) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    /* access modifiers changed from: package-private */
    public final Object readChar(InputStream inputStream) {
        int i;
        try {
            if (this.peeking) {
                inputStream.mark(1);
                i = inputStream.read();
                inputStream.reset();
            } else {
                i = inputStream.read();
            }
            if (i < 0) {
                return Sequence.eofValue;
            }
            return Char.make(i);
        } catch (IOException unused) {
            throw new RuntimeException("IO Exception caught");
        }
    }

    public final Object apply0() {
        return readChar(InPort.inDefault());
    }

    public final Object apply1(Object obj) {
        if (obj instanceof InPort) {
            return readChar((InPort) obj);
        }
        if (obj instanceof Reader) {
            return readChar((Reader) obj);
        }
        if (obj instanceof InputStream) {
            return readChar((InputStream) obj);
        }
        throw new WrongType((Procedure) this, 1, obj, "<input-port>");
    }
}
