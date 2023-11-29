package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.math.IntNum;

public class NumberPredicate extends Procedure1 implements Inlineable {
    public static final int EVEN = 2;
    public static final int ODD = 1;
    Language language;
    final int op;

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    /* access modifiers changed from: protected */
    public final Language getLanguage() {
        return this.language;
    }

    public Object apply1(Object obj) {
        boolean z;
        IntNum coerceIntNum = LangObjType.coerceIntNum(obj);
        int i = this.op;
        if (i == 1) {
            z = coerceIntNum.isOdd();
        } else if (i == 2) {
            z = !coerceIntNum.isOdd();
        } else {
            throw new Error();
        }
        return getLanguage().booleanObject(z);
    }

    public NumberPredicate(Language language2, String str, int i) {
        super(str);
        this.language = language2;
        this.op = i;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberPredicate");
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        int i;
        Expression[] args = applyExp.getArgs();
        if (args.length == 1 && ((i = this.op) == 1 || i == 2)) {
            Expression expression = args[0];
            if (Arithmetic.classifyType(expression.getType()) <= 4) {
                Target instance = StackTarget.getInstance(Type.intType);
                CodeAttr code = compilation.getCode();
                if (this.op == 2) {
                    code.emitPushInt(1);
                }
                expression.compile(compilation, instance);
                code.emitPushInt(1);
                code.emitAnd();
                if (this.op == 2) {
                    code.emitSub(Type.intType);
                }
                target.compileFromStack(compilation, Type.booleanType);
                return;
            }
        }
        ApplyExp.compile(applyExp, compilation, target);
    }
}
