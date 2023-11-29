package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;

public class syntax_rules extends Syntax {
    public static final syntax_rules syntax_rules;

    static {
        syntax_rules syntax_rules2 = new syntax_rules();
        syntax_rules = syntax_rules2;
        syntax_rules2.setName("syntax-rules");
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = (Pair) pair.getCdr();
        return new QuoteExp(new SyntaxRules(SyntaxPattern.getLiteralsList(pair2.getCar(), (SyntaxForm) null, translator), pair2.getCdr(), translator));
    }
}
