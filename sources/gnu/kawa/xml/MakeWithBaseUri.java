package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.XMLFilter;

public class MakeWithBaseUri extends NodeConstructor {
    static final Method beginEntityMethod;
    static final Method endEntityMethod;
    public static final MakeWithBaseUri makeWithBaseUri = new MakeWithBaseUri();
    static final ClassType typeXConsumer;

    public int numArgs() {
        return 8194;
    }

    static {
        ClassType make = ClassType.make("gnu.lists.XConsumer");
        typeXConsumer = make;
        beginEntityMethod = make.getDeclaredMethod("beginEntity", 1);
        endEntityMethod = make.getDeclaredMethod("endEntity", 0);
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        XMLFilter pushNodeContext = NodeConstructor.pushNodeContext(callContext);
        Object nextArg = callContext.getNextArg();
        Object nextArg2 = callContext.getNextArg();
        boolean z = pushNodeContext instanceof XConsumer;
        if (z) {
            pushNodeContext.beginEntity(nextArg);
        }
        try {
            Values.writeValues(nextArg2, pushNodeContext);
        } finally {
            if (z) {
                pushNodeContext.endEntity();
            }
            if (pushNodeContext instanceof TreeList) {
                ((TreeList) pushNodeContext).dump();
            }
            NodeConstructor.popNodeContext(consumer, callContext);
        }
    }

    public void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget) {
        Variable consumerVariable = consumerTarget.getConsumerVariable();
        Expression[] args = applyExp.getArgs();
        int length = args.length;
        CodeAttr code = compilation.getCode();
        code.emitLoad(consumerVariable);
        args[0].compile(compilation, Target.pushObject);
        code.emitInvokeInterface(beginEntityMethod);
        compileChild(args[1], compilation, consumerTarget);
        code.emitLoad(consumerVariable);
        code.emitInvokeInterface(endEntityMethod);
    }
}
