package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.xml.XMLFilter;

public class MakeAttribute extends NodeConstructor {
    static final Method endAttributeMethod = Compilation.typeConsumer.getDeclaredMethod("endAttribute", 0);
    public static final MakeAttribute makeAttribute;
    public static final QuoteExp makeAttributeExp;
    static final Method startAttributeMethod;
    static final ClassType typeMakeAttribute;

    public int numArgs() {
        return -4095;
    }

    static {
        MakeAttribute makeAttribute2 = new MakeAttribute();
        makeAttribute = makeAttribute2;
        makeAttributeExp = new QuoteExp(makeAttribute2);
        ClassType make = ClassType.make("gnu.kawa.xml.MakeAttribute");
        typeMakeAttribute = make;
        startAttributeMethod = make.getDeclaredMethod("startAttribute", 2);
    }

    public static void startAttribute(Consumer consumer, Object obj) {
        consumer.startAttribute(obj);
    }

    public void apply(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        XMLFilter pushNodeContext = pushNodeContext(callContext);
        try {
            startAttribute(pushNodeContext, callContext.getNextArg());
            Special special = Special.dfault;
            while (true) {
                Object nextArg = callContext.getNextArg(special);
                if (nextArg == special) {
                    pushNodeContext.endAttribute();
                    return;
                } else if (nextArg instanceof Consumable) {
                    ((Consumable) nextArg).consume(pushNodeContext);
                } else {
                    callContext.writeValue(nextArg);
                }
            }
        } finally {
            popNodeContext(consumer, callContext);
        }
    }

    public void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget) {
        Variable consumerVariable = consumerTarget.getConsumerVariable();
        Expression[] args = applyExp.getArgs();
        int length = args.length;
        CodeAttr code = compilation.getCode();
        code.emitLoad(consumerVariable);
        code.emitDup();
        args[0].compile(compilation, Target.pushObject);
        code.emitInvokeStatic(startAttributeMethod);
        for (int i = 1; i < length; i++) {
            compileChild(args[i], compilation, consumerTarget);
        }
        code.emitInvokeInterface(endAttributeMethod);
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Compilation.typeObject;
    }
}
