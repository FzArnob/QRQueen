package gnu.kawa.xml;

import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.kawa.functions.AppendValues;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class UnionNodes extends Procedure2 implements Inlineable {
    public static final UnionNodes unionNodes = new UnionNodes();

    public Object apply2(Object obj, Object obj2) {
        SortedNodes sortedNodes = new SortedNodes();
        Values.writeValues(obj, sortedNodes);
        Values.writeValues(obj2, sortedNodes);
        return sortedNodes;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ConsumerTarget.compileUsingConsumer(new ApplyExp((Procedure) AppendValues.appendValues, applyExp.getArgs()), compilation, target, SortNodes.makeSortedNodesMethod, (Method) null);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Compilation.typeObject;
    }
}
