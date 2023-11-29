package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
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
import gnu.expr.Target;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeText extends NodeConstructor {
    public static final MakeText makeText = new MakeText();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public Object apply1(Object obj) {
        if (obj == null) {
            return obj;
        }
        if ((obj instanceof Values) && ((Values) obj).isEmpty()) {
            return obj;
        }
        NodeTree nodeTree = new NodeTree();
        TextUtils.textValue(obj, new XMLFilter(nodeTree));
        return KText.make(nodeTree);
    }

    public static void text$X(Object obj, CallContext callContext) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof Values) || !((Values) obj).isEmpty()) {
            Consumer consumer = callContext.consumer;
            try {
                TextUtils.textValue(obj, NodeConstructor.pushNodeContext(callContext));
            } finally {
                NodeConstructor.popNodeContext(consumer, callContext);
            }
        }
    }

    public void apply(CallContext callContext) {
        text$X(callContext.getNextArg((Object) null), callContext);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp.compile(applyExp, compilation, target);
    }

    public void compileToNode(ApplyExp applyExp, Compilation compilation, ConsumerTarget consumerTarget) {
        CodeAttr code = compilation.getCode();
        int i = 0;
        Expression expression = applyExp.getArgs()[0];
        Variable consumerVariable = consumerTarget.getConsumerVariable();
        if (expression instanceof QuoteExp) {
            Object value = ((QuoteExp) expression).getValue();
            if (value instanceof String) {
                String str = (String) value;
                String calculateSplit = CodeAttr.calculateSplit(str);
                int length = calculateSplit.length();
                Method method = ((ClassType) consumerVariable.getType()).getMethod("write", new Type[]{Type.string_type});
                char c = 0;
                while (i < length) {
                    code.emitLoad(consumerVariable);
                    int charAt = calculateSplit.charAt(i) + c;
                    code.emitPushString(str.substring(c, charAt));
                    code.emitInvoke(method);
                    i++;
                    c = charAt;
                }
                return;
            }
        }
        expression.compile(compilation, Target.pushObject);
        code.emitLoad(consumerVariable);
        code.emitInvokeStatic(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("textValue", 2));
    }
}
