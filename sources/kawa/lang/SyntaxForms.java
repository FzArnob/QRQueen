package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.ImmutablePair;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;

public class SyntaxForms {
    public static final boolean DEBUGGING = true;

    public static Object makeForm(Object obj, TemplateScope templateScope) {
        if (obj instanceof Pair) {
            return new PairSyntaxForm((Pair) obj, templateScope);
        }
        if (obj == LList.Empty) {
            return obj;
        }
        return new SimpleSyntaxForm(obj, templateScope);
    }

    public static Object makeWithTemplate(Object obj, Object obj2) {
        if (obj2 instanceof SyntaxForm) {
            return (SyntaxForm) obj2;
        }
        if (!(obj instanceof SyntaxForm)) {
            return obj2;
        }
        SyntaxForm syntaxForm = (SyntaxForm) obj;
        if (obj2 == syntaxForm.getDatum()) {
            return syntaxForm;
        }
        return fromDatum(obj2, syntaxForm);
    }

    public static boolean freeIdentifierEquals(SyntaxForm syntaxForm, SyntaxForm syntaxForm2) {
        Translator translator = (Translator) Compilation.getCurrent();
        return translator.lexical.lookup(syntaxForm.getDatum(), -1) == translator.lexical.lookup(syntaxForm2.getDatum(), -1);
    }

    public static boolean isIdentifier(SyntaxForm syntaxForm) {
        return syntaxForm.getDatum() instanceof Symbol;
    }

    public static Object fromDatum(Object obj, SyntaxForm syntaxForm) {
        return makeForm(obj, syntaxForm.getScope());
    }

    public static Object fromDatumIfNeeded(Object obj, SyntaxForm syntaxForm) {
        if (obj == syntaxForm.getDatum()) {
            return syntaxForm;
        }
        if (obj instanceof SyntaxForm) {
            return (SyntaxForm) obj;
        }
        return fromDatum(obj, syntaxForm);
    }

    public static Expression rewrite(Object obj) {
        return ((Translator) Compilation.getCurrent()).rewrite(obj);
    }

    public static Expression rewriteBody(Object obj) {
        return ((Translator) Compilation.getCurrent()).rewrite_body(obj);
    }

    public static String toString(SyntaxForm syntaxForm, String str) {
        StringBuilder sb = new StringBuilder("#<syntax");
        if (str != null) {
            sb.append('#');
            sb.append(str);
        }
        sb.append(' ');
        sb.append(syntaxForm.getDatum());
        TemplateScope scope = syntaxForm.getScope();
        if (scope == null) {
            sb.append(" in null");
        } else {
            sb.append(" in #");
            sb.append(scope.id);
        }
        sb.append(">");
        return sb.toString();
    }

    static class SimpleSyntaxForm implements SyntaxForm {
        static int counter;
        private Object datum;
        int id;
        private TemplateScope scope;

        SimpleSyntaxForm(Object obj, TemplateScope templateScope) {
            int i = counter + 1;
            counter = i;
            this.id = i;
            this.datum = obj;
            this.scope = templateScope;
        }

        public Object getDatum() {
            return this.datum;
        }

        public TemplateScope getScope() {
            return this.scope;
        }

        public String toString() {
            return SyntaxForms.toString(this, Integer.toString(this.id));
        }
    }

    static class PairSyntaxForm extends ImmutablePair implements SyntaxForm {
        private Pair datum;
        private TemplateScope scope;

        public PairSyntaxForm(Pair pair, TemplateScope templateScope) {
            this.datum = pair;
            this.scope = templateScope;
        }

        public Object getDatum() {
            return this.datum;
        }

        public TemplateScope getScope() {
            return this.scope;
        }

        public Object getCar() {
            if (this.car == null) {
                this.car = SyntaxForms.makeForm(this.datum.getCar(), this.scope);
            }
            return this.car;
        }

        public Object getCdr() {
            if (this.cdr == null) {
                this.cdr = SyntaxForms.makeForm(this.datum.getCdr(), this.scope);
            }
            return this.cdr;
        }

        public String toString() {
            return SyntaxForms.toString(this, (String) null);
        }
    }
}
