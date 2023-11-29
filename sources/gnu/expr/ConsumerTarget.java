package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;

public class ConsumerTarget extends Target {
    Variable consumer;
    boolean isContextTarget;

    public ConsumerTarget(Variable variable) {
        this.consumer = variable;
    }

    public Variable getConsumerVariable() {
        return this.consumer;
    }

    public final boolean isContextTarget() {
        return this.isContextTarget;
    }

    public static Target makeContextTarget(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        compilation.loadCallContext();
        code.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
        Variable addVariable = code.getCurrentScope().addVariable(code, Compilation.typeConsumer, "$result");
        code.emitStore(addVariable);
        ConsumerTarget consumerTarget = new ConsumerTarget(addVariable);
        consumerTarget.isContextTarget = true;
        return consumerTarget;
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target) {
        if ((target instanceof ConsumerTarget) || (target instanceof IgnoreTarget)) {
            expression.compile(compilation, target);
            return;
        }
        ClassType classType = Compilation.typeValues;
        compileUsingConsumer(expression, compilation, target, classType.getDeclaredMethod("make", 0), classType.getDeclaredMethod("canonicalize", 0));
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target, Method method, Method method2) {
        ClassType classType;
        CodeAttr code = compilation.getCode();
        Scope pushScope = code.pushScope();
        if (method.getName() == "<init>") {
            ClassType declaringClass = method.getDeclaringClass();
            code.emitNew(declaringClass);
            code.emitDup((Type) declaringClass);
            code.emitInvoke(method);
            classType = declaringClass;
        } else {
            Type returnType = method.getReturnType();
            code.emitInvokeStatic(method);
            classType = returnType;
        }
        Variable addVariable = pushScope.addVariable(code, classType, (String) null);
        ConsumerTarget consumerTarget = new ConsumerTarget(addVariable);
        code.emitStore(addVariable);
        expression.compile(compilation, (Target) consumerTarget);
        code.emitLoad(addVariable);
        if (method2 != null) {
            code.emitInvoke(method2);
        }
        code.popScope();
        Type type = classType;
        if (method2 != null) {
            type = method2.getReturnType();
        }
        target.compileFromStack(compilation, type);
    }

    public void compileFromStack(Compilation compilation, Type type) {
        compileFromStack(compilation, type, -1);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compileFromStack(gnu.expr.Compilation r8, gnu.bytecode.Type r9, int r10) {
        /*
            r7 = this;
            gnu.bytecode.CodeAttr r8 = r8.getCode()
            gnu.bytecode.Type r9 = r9.getImplementationType()
            boolean r0 = r9 instanceof gnu.bytecode.PrimType
            r1 = 0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x0055
            java.lang.String r0 = r9.getSignature()
            char r0 = r0.charAt(r2)
            r4 = 70
            if (r0 == r4) goto L_0x0050
            r4 = 83
            if (r0 == r4) goto L_0x004b
            r4 = 86
            if (r0 == r4) goto L_0x004a
            r4 = 90
            if (r0 == r4) goto L_0x0045
            r4 = 73
            if (r0 == r4) goto L_0x004b
            r4 = 74
            if (r0 == r4) goto L_0x0040
            switch(r0) {
                case 66: goto L_0x004b;
                case 67: goto L_0x003b;
                case 68: goto L_0x0035;
                default: goto L_0x0032;
            }
        L_0x0032:
            r4 = r1
            r5 = r4
            goto L_0x007a
        L_0x0035:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.doubleType
            java.lang.String r5 = "writeDouble"
        L_0x0039:
            r6 = 1
            goto L_0x007b
        L_0x003b:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.charType
            java.lang.String r5 = "append"
            goto L_0x007a
        L_0x0040:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.longType
            java.lang.String r5 = "writeLong"
            goto L_0x0039
        L_0x0045:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.booleanType
            java.lang.String r5 = "writeBoolean"
            goto L_0x007a
        L_0x004a:
            return
        L_0x004b:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.intType
            java.lang.String r5 = "writeInt"
            goto L_0x007a
        L_0x0050:
            gnu.bytecode.PrimType r4 = gnu.bytecode.Type.floatType
            java.lang.String r5 = "writeFloat"
            goto L_0x007a
        L_0x0055:
            if (r10 == r3) goto L_0x0075
            boolean r0 = gnu.kawa.reflect.OccurrenceType.itemCountIsOne(r9)
            if (r0 == 0) goto L_0x005e
            goto L_0x0075
        L_0x005e:
            gnu.bytecode.ClassType r9 = gnu.expr.Compilation.typeValues
            r0 = 2
            java.lang.String r1 = "writeValues"
            gnu.bytecode.Method r9 = r9.getDeclaredMethod((java.lang.String) r1, (int) r0)
            gnu.bytecode.Variable r0 = r7.consumer
            r8.emitLoad(r0)
            if (r10 != 0) goto L_0x0071
            r8.emitSwap()
        L_0x0071:
            r8.emitInvokeStatic(r9)
            return
        L_0x0075:
            gnu.bytecode.ClassType r4 = gnu.bytecode.Type.pointer_type
            java.lang.String r5 = "writeObject"
            r0 = 0
        L_0x007a:
            r6 = 0
        L_0x007b:
            if (r10 < 0) goto L_0x007e
            goto L_0x009e
        L_0x007e:
            if (r6 == 0) goto L_0x0096
            r8.pushScope()
            gnu.bytecode.Variable r9 = r8.addLocal(r9)
            r8.emitStore(r9)
            gnu.bytecode.Variable r10 = r7.consumer
            r8.emitLoad(r10)
            r8.emitLoad(r9)
            r8.popScope()
            goto L_0x009e
        L_0x0096:
            gnu.bytecode.Variable r9 = r7.consumer
            r8.emitLoad(r9)
            r8.emitSwap()
        L_0x009e:
            if (r5 == 0) goto L_0x00aa
            gnu.bytecode.Type[] r9 = new gnu.bytecode.Type[r3]
            r9[r2] = r4
            gnu.bytecode.ClassType r10 = gnu.expr.Compilation.typeConsumer
            gnu.bytecode.Method r1 = r10.getDeclaredMethod((java.lang.String) r5, (gnu.bytecode.Type[]) r9)
        L_0x00aa:
            if (r1 == 0) goto L_0x00af
            r8.emitInvokeInterface(r1)
        L_0x00af:
            r9 = 67
            if (r0 != r9) goto L_0x00b6
            r8.emitPop(r3)
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ConsumerTarget.compileFromStack(gnu.expr.Compilation, gnu.bytecode.Type, int):void");
    }

    public boolean compileWrite(Expression expression, Compilation compilation) {
        Type implementationType = expression.getType().getImplementationType();
        if ((!(implementationType instanceof PrimType) || implementationType.isVoid()) && !OccurrenceType.itemCountIsOne(implementationType)) {
            return false;
        }
        compilation.getCode().emitLoad(this.consumer);
        expression.compile(compilation, StackTarget.getInstance(implementationType));
        compileFromStack(compilation, implementationType, 1);
        return true;
    }

    public Type getType() {
        return Compilation.scmSequenceType;
    }
}
