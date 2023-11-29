package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.xml.SortedNodes;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.IntNum;

public class ValuesFilter extends MethodProc implements Inlineable {
    public static final ValuesFilter exprFilter = new ValuesFilter('P');
    public static final ValuesFilter forwardFilter = new ValuesFilter(Access.FIELD_CONTEXT);
    public static final Method matchesMethod;
    public static final ValuesFilter reverseFilter = new ValuesFilter('R');
    public static final ClassType typeValuesFilter;
    char kind;
    int last_or_position_needed = 2;

    public int numArgs() {
        return 8194;
    }

    public ValuesFilter(char c) {
        this.kind = c;
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyValuesFilter");
    }

    public static ValuesFilter get(char c) {
        if (c == 'F') {
            return forwardFilter;
        }
        if (c == 'R') {
            return reverseFilter;
        }
        return exprFilter;
    }

    public static boolean matches(Object obj, long j) {
        if (obj instanceof Values) {
            obj = ((Values) obj).canonicalize();
        }
        if (!(obj instanceof Number)) {
            return BooleanValue.booleanValue(obj);
        }
        if (obj instanceof IntNum) {
            if (IntNum.compare((IntNum) obj, j) == 0) {
                return true;
            }
            return false;
        } else if ((obj instanceof Double) || (obj instanceof Float) || (obj instanceof DFloNum)) {
            if (((Number) obj).doubleValue() == ((double) j)) {
                return true;
            }
            return false;
        } else if (!(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Short) && !(obj instanceof Byte)) {
            return NumberCompare.applyWithPromotion(8, IntNum.make(j), obj);
        } else {
            if (j == ((Number) obj).longValue()) {
                return true;
            }
            return false;
        }
    }

    public void apply(CallContext callContext) throws Throwable {
        Values values;
        Object nextArg = callContext.getNextArg();
        Procedure procedure = (Procedure) callContext.getNextArg();
        Consumer consumer = callContext.consumer;
        if (this.kind != 'P') {
            values = new SortedNodes();
            Values.writeValues(nextArg, values);
        } else if (nextArg instanceof Values) {
            values = (Values) nextArg;
        } else {
            IntNum one = IntNum.one();
            if (matches(procedure.apply3(nextArg, one, one), 1)) {
                consumer.writeObject(nextArg);
                return;
            }
            return;
        }
        int size = values.size();
        IntNum make = IntNum.make(size);
        int maxArgs = procedure.maxArgs();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i = values.nextPos(i);
            Object posPrevious = values.getPosPrevious(i);
            int i3 = this.kind == 'R' ? size - i2 : i2 + 1;
            IntNum make2 = IntNum.make(i3);
            if (matches(maxArgs == 2 ? procedure.apply2(posPrevious, make2) : procedure.apply3(posPrevious, make2, make), (long) i3)) {
                consumer.writeObject(posPrevious);
            }
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Expression[] args = applyExp.getArgs();
        Expression expression = args[0];
        Expression expression2 = args[1];
        if (target instanceof IgnoreTarget) {
            expression.compile(compilation, target);
            expression2.compile(compilation, target);
        } else if (!(expression2 instanceof LambdaExp)) {
            ApplyExp.compile(applyExp, compilation, target);
        } else if (!(target instanceof ConsumerTarget)) {
            ConsumerTarget.compileUsingConsumer(applyExp, compilation, target);
        } else {
            ValuesMap.compileInlined((LambdaExp) expression2, expression, 1, matchesMethod, compilation, target);
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }

    static {
        ClassType make = ClassType.make("gnu.xquery.util.ValuesFilter");
        typeValuesFilter = make;
        matchesMethod = make.getDeclaredMethod("matches", 2);
    }
}
