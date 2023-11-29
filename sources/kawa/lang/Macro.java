package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Macro extends Syntax implements Printable, Externalizable {
    private ScopeExp capturedScope;
    public Object expander;
    private boolean hygienic = true;
    Object instance;

    public ScopeExp getCapturedScope() {
        if (this.capturedScope == null) {
            Object obj = this.instance;
            if (obj instanceof ModuleExp) {
                this.capturedScope = (ModuleExp) obj;
            } else if (obj != null) {
                this.capturedScope = ModuleInfo.findFromInstance(obj).getModuleExp();
            }
        }
        return this.capturedScope;
    }

    public void setCapturedScope(ScopeExp scopeExp) {
        this.capturedScope = scopeExp;
    }

    public static Macro make(Declaration declaration) {
        Macro macro = new Macro(declaration.getSymbol());
        declaration.setSyntax();
        macro.capturedScope = declaration.context;
        return macro;
    }

    public static Macro makeNonHygienic(Object obj, Procedure procedure) {
        Macro macro = new Macro(obj, procedure);
        macro.hygienic = false;
        return macro;
    }

    public static Macro makeNonHygienic(Object obj, Procedure procedure, Object obj2) {
        Macro macro = new Macro(obj, procedure);
        macro.hygienic = false;
        macro.instance = obj2;
        return macro;
    }

    public static Macro make(Object obj, Procedure procedure) {
        return new Macro(obj, procedure);
    }

    public static Macro make(Object obj, Procedure procedure, Object obj2) {
        Macro macro = new Macro(obj, procedure);
        macro.instance = obj2;
        return macro;
    }

    public final boolean isHygienic() {
        return this.hygienic;
    }

    public final void setHygienic(boolean z) {
        this.hygienic = z;
    }

    public Macro() {
    }

    public Macro(Macro macro) {
        this.name = macro.name;
        this.expander = macro.expander;
        this.hygienic = macro.hygienic;
    }

    public Macro(Object obj, Procedure procedure) {
        super(obj);
        this.expander = new QuoteExp(procedure);
    }

    public Macro(Object obj) {
        super(obj);
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        return translator.rewrite(expand(pair, translator));
    }

    public Expression rewriteForm(Object obj, Translator translator) {
        return translator.rewrite(expand(obj, translator));
    }

    public String toString() {
        return "#<macro " + getName() + '>';
    }

    public void print(Consumer consumer) {
        consumer.write("#<macro ");
        consumer.write(getName());
        consumer.write(62);
    }

    public Object expand(Object obj, Translator translator) {
        Procedure procedure;
        Object obj2;
        Macro macro;
        try {
            Object obj3 = this.expander;
            if (!(obj3 instanceof Procedure) || (obj3 instanceof Expression)) {
                if (!(obj3 instanceof Expression)) {
                    macro = translator.currentMacroDefinition;
                    translator.currentMacroDefinition = this;
                    obj3 = translator.rewrite(obj3);
                    this.expander = obj3;
                    translator.currentMacroDefinition = macro;
                }
                procedure = (Procedure) ((Expression) obj3).eval(translator.getGlobalEnvironment());
            } else {
                procedure = (Procedure) obj3;
            }
            if (!this.hygienic) {
                obj = Quote.quote(obj, translator);
                int listLength = Translator.listLength(obj);
                if (listLength <= 0) {
                    return translator.syntaxError("invalid macro argument list to " + this);
                }
                Object[] objArr = new Object[(listLength - 1)];
                for (int i = 0; i < listLength; i++) {
                    Pair pair = (Pair) obj;
                    if (i > 0) {
                        objArr[i - 1] = pair.getCar();
                    }
                    obj = pair.getCdr();
                }
                obj2 = procedure.applyN(objArr);
            } else {
                obj2 = procedure.apply1(obj);
            }
            if (!(obj instanceof PairWithPosition) || !(obj2 instanceof Pair) || (obj2 instanceof PairWithPosition)) {
                return obj2;
            }
            Pair pair2 = (Pair) obj2;
            return new PairWithPosition((PairWithPosition) obj, pair2.getCar(), pair2.getCdr());
        } catch (Throwable th) {
            return translator.syntaxError("evaluating syntax transformer '" + getName() + "' threw " + th);
        }
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        String fileName = translator.getFileName();
        int lineNumber = translator.getLineNumber();
        int columnNumber = translator.getColumnNumber();
        Syntax syntax = translator.currentSyntax;
        try {
            translator.setLine((Object) pair);
            translator.currentSyntax = this;
            translator.scanForm(expand(pair, translator), scopeExp);
        } finally {
            translator.setLine(fileName, lineNumber, columnNumber);
            translator.currentSyntax = syntax;
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
        objectOutput.writeObject(((QuoteExp) this.expander).getValue());
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName((String) objectInput.readObject());
        this.expander = new QuoteExp(objectInput.readObject());
    }
}
