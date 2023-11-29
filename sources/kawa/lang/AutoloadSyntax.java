package kawa.lang;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadSyntax extends Syntax implements Externalizable {
    String className;
    Environment env;
    Syntax loaded;

    public AutoloadSyntax() {
    }

    public AutoloadSyntax(String str, String str2) {
        super(str);
        this.className = str2;
    }

    public AutoloadSyntax(String str, String str2, Environment environment) {
        super(str);
        this.className = str2;
        this.env = environment;
    }

    public void print(PrintWriter printWriter) {
        printWriter.print(toString());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("#<syntax ");
        if (getName() != null) {
            stringBuffer.append(getName());
            stringBuffer.append(' ');
        }
        if (this.loaded != null) {
            stringBuffer.append("autoloaded>");
        } else {
            stringBuffer.append("autoload ");
            stringBuffer.append(this.className);
            stringBuffer.append(">");
        }
        return stringBuffer.toString();
    }

    private void throw_error(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(this.className);
        sb.append(" while autoloading ");
        sb.append(getName() == null ? "" : getName().toString());
        throw new GenericError(sb.toString());
    }

    /* access modifiers changed from: package-private */
    public void load() {
        String name = getName();
        try {
            Object newInstance = Class.forName(this.className).newInstance();
            if (newInstance instanceof Syntax) {
                Syntax syntax = (Syntax) newInstance;
                this.loaded = syntax;
                if (name != null && syntax.getName() == null) {
                    this.loaded.setName(name);
                    return;
                }
                return;
            }
            throw_error("failed to autoload valid syntax object ");
        } catch (ClassNotFoundException unused) {
            throw_error("failed to find class ");
        } catch (InstantiationException unused2) {
            throw_error("failed to instantiate class ");
        } catch (IllegalAccessException unused3) {
            throw_error("illegal access in class ");
        } catch (UnboundLocationException e) {
            throw_error("missing symbol '" + e.getMessage() + "' ");
        } catch (WrongArguments unused4) {
            throw_error("type error");
        }
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        if (this.loaded == null) {
            try {
                load();
            } catch (RuntimeException e) {
                translator.syntaxError(e.getMessage());
                return;
            }
        }
        this.loaded.scanForm(pair, scopeExp, translator);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        if (this.loaded == null) {
            try {
                load();
            } catch (GenericError e) {
                return translator.syntaxError(e.getMessage());
            } catch (WrongType e2) {
                return translator.syntaxError(e2.getMessage());
            }
        }
        Syntax syntax = translator.currentSyntax;
        translator.currentSyntax = this.loaded;
        try {
            return this.loaded.rewriteForm(pair, translator);
        } finally {
            translator.currentSyntax = syntax;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
        objectOutput.writeObject(this.className);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName((String) objectInput.readObject());
        this.className = (String) objectInput.readObject();
    }
}
