package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.TreeScanner;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class RelativeStep extends MethodProc implements Inlineable {
    public static final RelativeStep relativeStep = new RelativeStep();

    public int numArgs() {
        return 8194;
    }

    RelativeStep() {
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyRelativeStep");
    }

    public void apply(CallContext callContext) throws Throwable {
        Nodes nodes;
        Object nextArg = callContext.getNextArg();
        Procedure procedure = (Procedure) callContext.getNextArg();
        Consumer consumer = callContext.consumer;
        if (nextArg instanceof Nodes) {
            nodes = (Nodes) nextArg;
        } else {
            Nodes nodes2 = new Nodes();
            Values.writeValues(nextArg, nodes2);
            nodes = nodes2;
        }
        int size = nodes.size();
        int i = 0;
        IntNum make = IntNum.make(size);
        RelativeStepFilter relativeStepFilter = new RelativeStepFilter(consumer);
        for (int i2 = 1; i2 <= size; i2++) {
            i = nodes.nextPos(i);
            procedure.check3(nodes.getPosPrevious(i), IntNum.make(i2), make, callContext);
            Values.writeValues(callContext.runUntilValue(), relativeStepFilter);
        }
        relativeStepFilter.finish();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.ApplyExp r16, gnu.expr.Compilation r17, gnu.expr.Target r18) {
        /*
            r15 = this;
            r4 = r17
            r0 = r18
            gnu.expr.Expression[] r1 = r16.getArgs()
            r6 = 0
            r2 = r1[r6]
            r3 = 1
            r1 = r1[r3]
            boolean r5 = r0 instanceof gnu.expr.IgnoreTarget
            if (r5 == 0) goto L_0x0019
            r2.compile((gnu.expr.Compilation) r4, (gnu.expr.Target) r0)
            r1.compile((gnu.expr.Compilation) r4, (gnu.expr.Target) r0)
            return
        L_0x0019:
            gnu.bytecode.Type r5 = r16.getTypeRaw()
            if (r5 != 0) goto L_0x0021
            gnu.bytecode.ClassType r5 = gnu.bytecode.Type.pointer_type
        L_0x0021:
            gnu.bytecode.Type r5 = gnu.kawa.reflect.OccurrenceType.itemPrimeType(r5)
            gnu.kawa.xml.NodeType r7 = gnu.kawa.xml.NodeType.anyNodeTest
            int r5 = r7.compare(r5)
            r7 = 65
            r8 = 32
            r9 = 78
            if (r5 < 0) goto L_0x0036
            r5 = 78
            goto L_0x003e
        L_0x0036:
            r10 = -3
            if (r5 != r10) goto L_0x003c
            r5 = 65
            goto L_0x003e
        L_0x003c:
            r5 = 32
        L_0x003e:
            gnu.kawa.xml.TreeScanner r10 = extractStep(r1)
            r11 = 83
            if (r10 == 0) goto L_0x0069
            gnu.bytecode.Type r12 = r2.getType()
            boolean r13 = r10 instanceof gnu.kawa.xml.ChildAxis
            if (r13 != 0) goto L_0x0056
            boolean r13 = r10 instanceof gnu.kawa.xml.AttributeAxis
            if (r13 != 0) goto L_0x0056
            boolean r10 = r10 instanceof gnu.kawa.xml.SelfAxis
            if (r10 == 0) goto L_0x0069
        L_0x0056:
            boolean r10 = r12 instanceof gnu.kawa.xml.NodeSetType
            if (r10 != 0) goto L_0x0066
            if (r5 != r9) goto L_0x0069
            gnu.bytecode.Type r10 = r2.getType()
            boolean r10 = gnu.kawa.reflect.OccurrenceType.itemCountIsZeroOrOne(r10)
            if (r10 == 0) goto L_0x0069
        L_0x0066:
            r10 = 83
            goto L_0x006a
        L_0x0069:
            r10 = r5
        L_0x006a:
            boolean r5 = r0 instanceof gnu.expr.ConsumerTarget
            if (r5 != 0) goto L_0x0072
            gnu.expr.ConsumerTarget.compileUsingConsumer(r16, r17, r18)
            return
        L_0x0072:
            gnu.bytecode.CodeAttr r12 = r17.getCode()
            gnu.bytecode.Scope r5 = r12.pushScope()
            r13 = 0
            if (r10 == r7) goto L_0x00bd
            if (r10 != r11) goto L_0x0080
            goto L_0x00bd
        L_0x0080:
            java.lang.String r7 = "<init>"
            if (r10 != r9) goto L_0x008f
            java.lang.String r3 = "gnu.kawa.xml.SortedNodes"
            gnu.bytecode.ClassType r3 = gnu.bytecode.ClassType.make(r3)
            gnu.bytecode.Method r7 = r3.getDeclaredMethod((java.lang.String) r7, (int) r6)
            goto L_0x009a
        L_0x008f:
            java.lang.String r11 = "gnu.xquery.util.RelativeStepFilter"
            gnu.bytecode.ClassType r11 = gnu.bytecode.ClassType.make(r11)
            gnu.bytecode.Method r7 = r11.getDeclaredMethod((java.lang.String) r7, (int) r3)
            r3 = r11
        L_0x009a:
            gnu.bytecode.Variable r13 = r5.addVariable(r12, r3, r13)
            gnu.expr.ConsumerTarget r5 = new gnu.expr.ConsumerTarget
            r5.<init>(r13)
            r12.emitNew(r3)
            r12.emitDup((gnu.bytecode.Type) r3)
            gnu.expr.ConsumerTarget r0 = (gnu.expr.ConsumerTarget) r0
            gnu.bytecode.Variable r0 = r0.getConsumerVariable()
            if (r10 == r9) goto L_0x00b4
            r12.emitLoad(r0)
        L_0x00b4:
            r12.emitInvoke(r7)
            r12.emitStore(r13)
            r7 = r0
            r11 = r3
            goto L_0x00c0
        L_0x00bd:
            r5 = r0
            r7 = r13
            r11 = r7
        L_0x00c0:
            r0 = r1
            gnu.expr.LambdaExp r0 = (gnu.expr.LambdaExp) r0
            r3 = 1
            r14 = 0
            r1 = r2
            r2 = r3
            r3 = r14
            r4 = r17
            gnu.kawa.functions.ValuesMap.compileInlined(r0, r1, r2, r3, r4, r5)
            if (r10 != r9) goto L_0x00e2
            r12.emitLoad(r13)
            r12.emitLoad(r7)
            gnu.bytecode.ClassType r0 = gnu.expr.Compilation.typeValues
            r1 = 2
            java.lang.String r2 = "writeValues"
            gnu.bytecode.Method r0 = r0.getDeclaredMethod((java.lang.String) r2, (int) r1)
            r12.emitInvokeStatic(r0)
            goto L_0x00f0
        L_0x00e2:
            if (r10 != r8) goto L_0x00f0
            r12.emitLoad(r13)
            java.lang.String r0 = "finish"
            gnu.bytecode.Method r0 = r11.getDeclaredMethod((java.lang.String) r0, (int) r6)
            r12.emitInvoke(r0)
        L_0x00f0:
            r12.popScope()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.RelativeStep.compile(gnu.expr.ApplyExp, gnu.expr.Compilation, gnu.expr.Target):void");
    }

    public Type getReturnType(Expression[] expressionArr) {
        return Type.pointer_type;
    }

    public static TreeScanner extractStep(Expression expression) {
        while (expression instanceof ApplyExp) {
            ApplyExp applyExp = (ApplyExp) expression;
            Expression function = applyExp.getFunction();
            if (function instanceof QuoteExp) {
                Object value = ((QuoteExp) function).getValue();
                if (value instanceof TreeScanner) {
                    return (TreeScanner) value;
                }
                if (value instanceof ValuesFilter) {
                    expression = applyExp.getArgs()[0];
                }
            }
            return null;
        }
        return null;
    }
}
