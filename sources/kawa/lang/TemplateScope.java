package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TemplateScope extends LetExp implements Externalizable {
    Declaration macroContext;
    private Syntax syntax;

    public TemplateScope() {
        super((Expression[]) null);
    }

    public TemplateScope(ScopeExp scopeExp) {
        super((Expression[]) null);
        this.outer = scopeExp;
    }

    public static TemplateScope make() {
        return make((Translator) Compilation.getCurrent());
    }

    public static TemplateScope make(Translator translator) {
        TemplateScope templateScope = new TemplateScope();
        Syntax currentSyntax = translator.getCurrentSyntax();
        if (currentSyntax instanceof Macro) {
            templateScope.outer = ((Macro) currentSyntax).getCapturedScope();
            templateScope.macroContext = translator.macroContext;
        }
        templateScope.syntax = currentSyntax;
        return templateScope;
    }

    public String toString() {
        return super.toString() + "(for " + this.syntax + ")";
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.outer);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.outer = (ScopeExp) objectInput.readObject();
    }
}
