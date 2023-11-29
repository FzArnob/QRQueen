package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_syntax extends Syntax {
    public static final define_syntax define_macro = new define_syntax("%define-macro", false);
    public static final define_syntax define_syntax = new define_syntax("%define-syntax", true);
    static PrimProcedure makeHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("make", 3));
    static PrimProcedure makeNonHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("makeNonHygienic", 3));
    static PrimProcedure setCapturedScope = new PrimProcedure(typeMacro.getDeclaredMethod("setCapturedScope", 1));
    static ClassType typeMacro = ClassType.make("kawa.lang.Macro");
    boolean hygienic;

    static {
        makeHygienic.setSideEffectFree();
        makeNonHygienic.setSideEffectFree();
    }

    public define_syntax() {
        this.hygienic = true;
    }

    public define_syntax(Object obj, boolean z) {
        super(obj);
        this.hygienic = z;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        return translator.syntaxError("define-syntax not in a body");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Object cdr = pair.getCdr();
        Object obj = null;
        SyntaxForm syntaxForm = null;
        while (cdr instanceof SyntaxForm) {
            syntaxForm = (SyntaxForm) cdr;
            cdr = syntaxForm.getDatum();
        }
        if (cdr instanceof Pair) {
            Pair pair2 = (Pair) cdr;
            obj = pair2.getCar();
            cdr = pair2.getCdr();
        }
        SyntaxForm syntaxForm2 = syntaxForm;
        while (obj instanceof SyntaxForm) {
            syntaxForm2 = (SyntaxForm) obj;
            obj = syntaxForm2.getDatum();
        }
        Object namespaceResolve = translator.namespaceResolve(obj);
        if (!(namespaceResolve instanceof Symbol)) {
            translator.formStack.addElement(translator.syntaxError("missing macro name for " + Translator.safeCar(pair)));
        } else if (cdr == null || Translator.safeCdr(cdr) != LList.Empty) {
            translator.formStack.addElement(translator.syntaxError("invalid syntax for " + getName()));
        } else {
            Declaration define = translator.define(namespaceResolve, syntaxForm2, scopeExp);
            define.setType(typeMacro);
            translator.push(define);
            Macro macro = translator.currentMacroDefinition;
            Macro make = Macro.make(define);
            make.setHygienic(this.hygienic);
            translator.currentMacroDefinition = make;
            Expression rewrite_car = translator.rewrite_car((Pair) cdr, syntaxForm);
            translator.currentMacroDefinition = macro;
            make.expander = rewrite_car;
            if (rewrite_car instanceof LambdaExp) {
                ((LambdaExp) rewrite_car).setFlag(256);
            }
            ApplyExp applyExp = new ApplyExp((Procedure) this.hygienic ? makeHygienic : makeNonHygienic, new QuoteExp(namespaceResolve), rewrite_car, ThisExp.makeGivingContext(scopeExp));
            define.noteValue(applyExp);
            define.setProcedureDecl(true);
            if (define.context instanceof ModuleExp) {
                SetExp setExp = new SetExp(define, (Expression) applyExp);
                setExp.setDefining(true);
                if (translator.getLanguage().hasSeparateFunctionNamespace()) {
                    setExp.setFuncDef(true);
                }
                translator.formStack.addElement(setExp);
                if (translator.immediate) {
                    translator.formStack.addElement(new ApplyExp((Procedure) setCapturedScope, new ReferenceExp(define), new QuoteExp(scopeExp)));
                }
            }
        }
    }
}
