package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class SortNodes extends Procedure1 implements Inlineable {
    public static final Method canonicalizeMethod = Compilation.typeValues.getDeclaredMethod("canonicalize", 0);
    public static final Method makeSortedNodesMethod;
    public static final SortNodes sortNodes = new SortNodes();
    public static final ClassType typeSortedNodes;

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.SortedNodes");
        typeSortedNodes = make;
        makeSortedNodesMethod = make.getDeclaredMethod("<init>", 0);
    }

    public Object apply1(Object obj) {
        SortedNodes sortedNodes = new SortedNodes();
        Values.writeValues(obj, sortedNodes);
        if (sortedNodes.count > 1) {
            return sortedNodes;
        }
        if (sortedNodes.count == 0) {
            return Values.empty;
        }
        return sortedNodes.get(0);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        Expression[] args = applyExp.getArgs();
        if (args.length != 1 || !compilation.mustCompile) {
            ApplyExp.compile(applyExp, compilation, target);
        } else {
            ConsumerTarget.compileUsingConsumer(args[0], compilation, target, makeSortedNodesMethod, ((target instanceof ConsumerTarget) || ((target instanceof StackTarget) && target.getType().isSubtype(Compilation.typeValues))) ? null : canonicalizeMethod);
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Compilation.typeObject;
    }
}
