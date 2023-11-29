package gnu.kawa.xml;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.xml.XMLFilter;

public class DocumentConstructor extends NodeConstructor {
    public static final DocumentConstructor documentConstructor = new DocumentConstructor();
    static final Method endDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("endDocument", 0);
    static final Method startDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("startDocument", 0);

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        XMLFilter pushNodeContext = pushNodeContext(callContext);
        try {
            String str = Location.UNBOUND;
            pushNodeContext.startDocument();
            while (true) {
                Object nextArg = callContext.getNextArg(str);
                if (nextArg == str) {
                    pushNodeContext.endDocument();
                    return;
                } else if (nextArg instanceof Consumable) {
                    ((Consumable) nextArg).consume(pushNodeContext);
                } else {
                    pushNodeContext.writeObject(nextArg);
                }
            }
        } finally {
            popNodeContext(consumer, callContext);
        }
    }

    public void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget) {
        Variable consumerVariable = consumerTarget.getConsumerVariable();
        CodeAttr code = compilation.getCode();
        code.emitLoad(consumerVariable);
        code.emitInvokeInterface(startDocumentMethod);
        for (Expression compileChild : applyExp.getArgs()) {
            compileChild(compileChild, compilation, consumerTarget);
        }
        code.emitLoad(consumerVariable);
        code.emitInvokeInterface(endDocumentMethod);
    }
}
