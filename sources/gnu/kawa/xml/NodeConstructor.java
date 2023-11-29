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
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;

public abstract class NodeConstructor extends MethodProc implements Inlineable {
    static final Method popNodeConsumerMethod;
    static final Method popNodeContextMethod;
    static final Method pushNodeConsumerMethod;
    static final Method pushNodeContextMethod;
    static final ClassType typeKNode = ClassType.make("gnu.kawa.xml.KNode");
    static final ClassType typeNodeConstructor;
    static final ClassType typeXMLFilter = ClassType.make("gnu.xml.XMLFilter");

    public abstract void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget);

    public static XMLFilter pushNodeConsumer(Consumer consumer) {
        if (consumer instanceof XMLFilter) {
            return (XMLFilter) consumer;
        }
        return new XMLFilter(new NodeTree());
    }

    public static void popNodeConsumer(Consumer consumer, Consumer consumer2) {
        if (consumer != consumer2) {
            boolean z = consumer2 instanceof XMLFilter;
            Object obj = consumer2;
            if (z) {
                obj = KNode.make((NodeTree) ((XMLFilter) consumer2).out);
            }
            consumer.writeObject(obj);
        }
    }

    public static XMLFilter pushNodeContext(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        if (consumer instanceof XMLFilter) {
            return (XMLFilter) consumer;
        }
        XMLFilter xMLFilter = new XMLFilter(new NodeTree());
        callContext.consumer = xMLFilter;
        return xMLFilter;
    }

    public static void popNodeContext(Consumer consumer, CallContext callContext) {
        Object obj = callContext.consumer;
        if (consumer != obj) {
            if (obj instanceof XMLFilter) {
                obj = KNode.make((NodeTree) ((XMLFilter) obj).out);
            }
            consumer.writeObject(obj);
            callContext.consumer = consumer;
        }
    }

    public static void compileChild(Expression expression, Compilation compilation, ConsumerTarget consumerTarget) {
        if (expression instanceof ApplyExp) {
            ApplyExp applyExp = (ApplyExp) expression;
            Expression function = applyExp.getFunction();
            if (function instanceof QuoteExp) {
                Object value = ((QuoteExp) function).getValue();
                if (value instanceof NodeConstructor) {
                    ((NodeConstructor) value).compileToNode(applyExp, compilation, consumerTarget);
                    return;
                }
            }
        }
        expression.compileWithPosition(compilation, consumerTarget);
    }

    public static void compileUsingNodeTree(Expression expression, Compilation compilation, Target target) {
        ClassType classType = typeNodeConstructor;
        ConsumerTarget.compileUsingConsumer(expression, compilation, target, classType.getDeclaredMethod("makeNode", 0), classType.getDeclaredMethod("finishNode", 1));
    }

    public static XMLFilter makeNode() {
        return new XMLFilter(new NodeTree());
    }

    public static KNode finishNode(XMLFilter xMLFilter) {
        return KNode.make((NodeTree) xMLFilter.out);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        if (target instanceof IgnoreTarget) {
            ApplyExp.compile(applyExp, compilation, target);
        } else if (!(target instanceof ConsumerTarget)) {
            compileUsingNodeTree(applyExp, compilation, target);
        } else {
            ConsumerTarget consumerTarget = (ConsumerTarget) target;
            Variable consumerVariable = consumerTarget.getConsumerVariable();
            Type type = consumerVariable.getType();
            ClassType classType = typeXMLFilter;
            if (type.isSubtype(classType)) {
                compileToNode(applyExp, compilation, consumerTarget);
                return;
            }
            int length = applyExp.getArgs().length;
            CodeAttr code = compilation.getCode();
            Variable addVariable = code.pushScope().addVariable(code, classType, (String) null);
            if (consumerTarget.isContextTarget()) {
                compilation.loadCallContext();
                code.emitInvokeStatic(pushNodeContextMethod);
            } else {
                code.emitLoad(consumerVariable);
                code.emitInvokeStatic(pushNodeConsumerMethod);
            }
            code.emitStore(addVariable);
            code.emitTryStart(true, Type.void_type);
            compileToNode(applyExp, compilation, new ConsumerTarget(addVariable));
            code.emitTryEnd();
            code.emitFinallyStart();
            code.emitLoad(consumerVariable);
            if (consumerTarget.isContextTarget()) {
                compilation.loadCallContext();
                code.emitInvokeStatic(popNodeContextMethod);
            } else {
                code.emitLoad(addVariable);
                code.emitInvokeStatic(popNodeConsumerMethod);
            }
            code.emitFinallyEnd();
            code.emitTryCatchEnd();
            code.popScope();
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Compilation.typeObject;
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.NodeConstructor");
        typeNodeConstructor = make;
        pushNodeContextMethod = make.getDeclaredMethod("pushNodeContext", 1);
        popNodeContextMethod = make.getDeclaredMethod("popNodeContext", 2);
        pushNodeConsumerMethod = make.getDeclaredMethod("pushNodeConsumer", 1);
        popNodeConsumerMethod = make.getDeclaredMethod("popNodeConsumer", 2);
    }
}
