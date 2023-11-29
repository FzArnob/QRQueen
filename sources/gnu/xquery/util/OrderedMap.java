package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class OrderedMap extends MethodProc implements Inlineable {
    public static final OrderedMap orderedMap;

    public static Object[] makeTuple$V(Object[] objArr) {
        return objArr;
    }

    static {
        OrderedMap orderedMap2 = new OrderedMap();
        orderedMap = orderedMap2;
        orderedMap2.setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyOrderedMap");
    }

    public void apply(CallContext callContext) throws Throwable {
        OrderedTuples orderedTuples;
        Object[] args = callContext.getArgs();
        Object obj = args[0];
        if (args.length == 2) {
            orderedTuples = (OrderedTuples) args[1];
        } else {
            int length = args.length - 2;
            Object[] objArr = new Object[length];
            System.arraycopy(args, 2, objArr, 0, length);
            orderedTuples = OrderedTuples.make$V((Procedure) args[1], objArr);
        }
        Values.writeValues(obj, orderedTuples);
        orderedTuples.run$X(callContext);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        CompileMisc.compileOrderedMap(applyExp, compilation, target, this);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }
}
