package gnu.kawa.lispexpr;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ResolveNamespace extends Syntax {
    public static final ResolveNamespace resolveNamespace;
    public static final ResolveNamespace resolveQName = new ResolveNamespace("$resolve-qname", true);
    boolean resolvingQName;

    static {
        ResolveNamespace resolveNamespace2 = new ResolveNamespace("$resolve-namespace$", false);
        resolveNamespace = resolveNamespace2;
        resolveNamespace2.setName("$resolve-namespace$");
    }

    public ResolveNamespace(String str, boolean z) {
        super(str);
        this.resolvingQName = z;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = (Pair) pair.getCdr();
        Namespace namespaceResolvePrefix = translator.namespaceResolvePrefix(translator.rewrite_car(pair2, false));
        if (namespaceResolvePrefix == null) {
            String obj = pair2.getCar().toString();
            if (obj == "[default-element-namespace]") {
                namespaceResolvePrefix = Namespace.EmptyNamespace;
            } else {
                Object pushPositionOf = translator.pushPositionOf(pair2);
                translator.error('e', "unknown namespace prefix " + obj);
                translator.popPositionOf(pushPositionOf);
                namespaceResolvePrefix = Namespace.valueOf(obj, obj);
            }
        }
        if (this.resolvingQName) {
            return new QuoteExp(namespaceResolvePrefix.getSymbol(((Pair) pair2.getCdr()).getCar().toString()));
        }
        return new QuoteExp(namespaceResolvePrefix);
    }
}
