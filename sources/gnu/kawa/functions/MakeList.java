package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;

public class MakeList extends ProcedureN implements Inlineable {
    public static final MakeList list;

    static {
        MakeList makeList = new MakeList();
        list = makeList;
        makeList.setName("list");
    }

    public static Object list$V(Object[] objArr) {
        LList lList = LList.Empty;
        int length = objArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return lList;
            }
            lList = new Pair(objArr[length], lList);
        }
    }

    public Object applyN(Object[] objArr) {
        return list$V(objArr);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        compile(applyExp.getArgs(), 0, compilation);
        target.compileFromStack(compilation, applyExp.getType());
    }

    public static void compile(Expression[] expressionArr, int i, Compilation compilation) {
        int length = expressionArr.length - i;
        CodeAttr code = compilation.getCode();
        if (length == 0) {
            new QuoteExp(LList.Empty).compile(compilation, Target.pushObject);
        } else if (length <= 4) {
            for (int i2 = 0; i2 < length; i2++) {
                expressionArr[i + i2].compile(compilation, Target.pushObject);
            }
            code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list" + length, (Type[]) null));
        } else {
            expressionArr[i].compile(compilation, Target.pushObject);
            code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list1", (Type[]) null));
            code.emitDup(1);
            int i3 = i + 1;
            int i4 = length - 1;
            while (i4 >= 4) {
                expressionArr[i3].compile(compilation, Target.pushObject);
                expressionArr[i3 + 1].compile(compilation, Target.pushObject);
                expressionArr[i3 + 2].compile(compilation, Target.pushObject);
                expressionArr[i3 + 3].compile(compilation, Target.pushObject);
                i4 -= 4;
                i3 += 4;
                code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain4", (Type[]) null));
            }
            while (i4 > 0) {
                expressionArr[i3].compile(compilation, Target.pushObject);
                i4--;
                i3++;
                code.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain1", (Type[]) null));
            }
            code.emitPop(1);
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        return expressionArr.length > 0 ? Compilation.typePair : LangObjType.listType;
    }
}
