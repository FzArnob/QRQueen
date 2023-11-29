package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;

public class CompileXmlFunctions {
    public static Expression validateApplyMakeUnescapedData(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        return (args.length != 1 || !(args[0] instanceof QuoteExp)) ? applyExp : new QuoteExp(((MakeUnescapedData) procedure).apply1(((QuoteExp) args[0]).getValue()));
    }

    public static Expression validateApplyTreeScanner(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        NodePredicate nodePredicate = ((TreeScanner) procedure).type;
        if (applyExp.getTypeRaw() == null && (nodePredicate instanceof Type)) {
            applyExp.setType(NodeSetType.getInstance((Type) nodePredicate));
        }
        return applyExp;
    }
}
