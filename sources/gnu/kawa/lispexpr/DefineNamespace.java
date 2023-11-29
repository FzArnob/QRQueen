package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class DefineNamespace extends Syntax {
    public static final String XML_NAMESPACE_MAGIC = "&xml&";
    public static final DefineNamespace define_namespace;
    public static final DefineNamespace define_private_namespace;
    public static final DefineNamespace define_xml_namespace;
    private boolean makePrivate;
    private boolean makeXML;

    static {
        DefineNamespace defineNamespace = new DefineNamespace();
        define_namespace = defineNamespace;
        DefineNamespace defineNamespace2 = new DefineNamespace();
        define_private_namespace = defineNamespace2;
        DefineNamespace defineNamespace3 = new DefineNamespace();
        define_xml_namespace = defineNamespace3;
        defineNamespace.setName("define-namespace");
        defineNamespace2.setName("define-private-namespace");
        defineNamespace2.makePrivate = true;
        defineNamespace3.setName("define-xml-namespace");
        defineNamespace3.makeXML = true;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Expression expression;
        Object obj;
        if (pair.getCdr() instanceof Pair) {
            Pair pair2 = (Pair) pair.getCdr();
            if ((pair2.getCar() instanceof Symbol) && (pair2.getCdr() instanceof Pair)) {
                Pair pair3 = (Pair) pair2.getCdr();
                if (pair3.getCdr() == LList.Empty) {
                    Symbol symbol = (Symbol) pair2.getCar();
                    Declaration define = scopeExp.getDefine(symbol, 'w', translator);
                    translator.push(define);
                    define.setFlag(2375680);
                    if (this.makePrivate) {
                        define.setFlag(16777216);
                        define.setPrivate(true);
                    } else if (scopeExp instanceof ModuleExp) {
                        define.setCanRead(true);
                    }
                    Translator.setLine(define, (Object) pair2);
                    if (pair3.getCar() instanceof CharSequence) {
                        String obj2 = pair3.getCar().toString();
                        if (obj2.startsWith("class:")) {
                            obj = ClassNamespace.getInstance(obj2, ClassType.make(obj2.substring(6)));
                            define.setType(ClassType.make("gnu.kawa.lispexpr.ClassNamespace"));
                        } else if (this.makeXML) {
                            obj = XmlNamespace.getInstance(symbol.getName(), obj2);
                            define.setType(ClassType.make("gnu.kawa.xml.XmlNamespace"));
                        } else {
                            obj = Namespace.valueOf(obj2);
                            define.setType(ClassType.make("gnu.mapping.Namespace"));
                        }
                        expression = new QuoteExp(obj);
                        define.setFlag(8192);
                    } else {
                        expression = translator.rewrite_car(pair3, false);
                    }
                    define.noteValue(expression);
                    vector.addElement(SetExp.makeDefinition(define, expression));
                    return true;
                }
            }
        }
        translator.error('e', "invalid syntax for define-namespace");
        return false;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        return translator.syntaxError("define-namespace is only allowed in a <body>");
    }
}
