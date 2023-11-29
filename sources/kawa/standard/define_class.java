package kawa.standard;

import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_class extends Syntax {
    public static final define_class define_class = new define_class("define-class", false);
    public static final define_class define_simple_class = new define_class("define-simple-class", true);
    boolean isSimple;
    object objectSyntax;

    define_class(object object, boolean z) {
        this.objectSyntax = object;
        this.isSimple = z;
    }

    define_class(String str, boolean z) {
        super(str);
        this.objectSyntax = object.objectSyntax;
        this.isSimple = z;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        SyntaxForm syntaxForm = null;
        while (cdr instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) cdr;
            cdr = syntaxForm.getDatum();
        }
        if (!(cdr instanceof Pair)) {
            return super.scanForDefinitions(pair, vector, scopeExp, translator);
        }
        Pair pair2 = (Pair) cdr;
        Object obj = pair2.getCar();
        while (obj instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) obj;
            obj = syntaxForm.getDatum();
        }
        Object namespaceResolve = translator.namespaceResolve(obj);
        if ((namespaceResolve instanceof String) || (namespaceResolve instanceof Symbol)) {
            Declaration define = translator.define(namespaceResolve, syntaxForm, scopeExp);
            if (pair2 instanceof PairWithPosition) {
                define.setLocation((PairWithPosition) pair2);
            }
            ClassExp classExp = new ClassExp(this.isSimple);
            define.noteValue(classExp);
            define.setFlag(536887296);
            define.setType(this.isSimple ? Compilation.typeClass : Compilation.typeClassType);
            translator.mustCompileHere();
            String name = namespaceResolve instanceof Symbol ? ((Symbol) namespaceResolve).getName() : namespaceResolve.toString();
            int length = name.length();
            if (length > 2 && name.charAt(0) == '<') {
                int i = length - 1;
                if (name.charAt(i) == '>') {
                    name = name.substring(1, i);
                }
            }
            classExp.setName(name);
            Object obj2 = pair2.getCdr();
            while (obj2 instanceof SyntaxForm) {
                syntaxForm = (SyntaxForm) obj2;
                obj2 = syntaxForm.getDatum();
            }
            if (!(obj2 instanceof Pair)) {
                translator.error('e', "missing class members");
                return false;
            }
            Pair pair3 = (Pair) obj2;
            ScopeExp currentScope = translator.currentScope();
            if (syntaxForm != null) {
                translator.setCurrentScope(syntaxForm.getScope());
            }
            Object[] scanClassDef = this.objectSyntax.scanClassDef(pair3, classExp, translator);
            if (syntaxForm != null) {
                translator.setCurrentScope(currentScope);
            }
            if (scanClassDef == null) {
                return false;
            }
            vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair3, define, scanClassDef)));
            return true;
        }
        translator.error('e', "missing class name");
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewriteForm(gnu.lists.Pair r4, kawa.lang.Translator r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r4.getCdr()
            boolean r1 = r0 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x0030
            r4 = r0
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            java.lang.Object r0 = r4.getCar()
            boolean r1 = r0 instanceof gnu.expr.Declaration
            if (r1 != 0) goto L_0x002d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = r3.getName()
            r4.append(r0)
            java.lang.String r0 = " can only be used in <body>"
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            gnu.expr.Expression r4 = r5.syntaxError(r4)
            return r4
        L_0x002d:
            gnu.expr.Declaration r0 = (gnu.expr.Declaration) r0
            goto L_0x0031
        L_0x0030:
            r0 = 0
        L_0x0031:
            gnu.expr.Expression r1 = r0.getValue()
            gnu.expr.ClassExp r1 = (gnu.expr.ClassExp) r1
            kawa.standard.object r2 = r3.objectSyntax
            java.lang.Object r4 = r4.getCdr()
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            r2.rewriteClassDef(r4, r5)
            gnu.expr.SetExp r4 = new gnu.expr.SetExp
            r4.<init>((gnu.expr.Declaration) r0, (gnu.expr.Expression) r1)
            r5 = 1
            r4.setDefining(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.define_class.rewriteForm(gnu.lists.Pair, kawa.lang.Translator):gnu.expr.Expression");
    }
}
