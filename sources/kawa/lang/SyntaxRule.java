package kawa.lang;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRule extends SyntaxTemplate implements Externalizable {
    SyntaxPattern pattern;

    public SyntaxRule() {
    }

    public SyntaxRule(SyntaxPattern syntaxPattern, String str, String str2, Object[] objArr, int i) {
        super(str, str2, objArr, i);
        this.pattern = syntaxPattern;
    }

    public SyntaxRule(SyntaxPattern syntaxPattern, Object obj, SyntaxForm syntaxForm, Translator translator) {
        super(obj, syntaxForm, translator);
        this.pattern = syntaxPattern;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.pattern);
        super.writeExternal(objectOutput);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.pattern = (SyntaxPattern) objectInput.readObject();
        super.readExternal(objectInput);
    }
}
