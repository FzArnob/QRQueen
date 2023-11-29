package gnu.expr;

import gnu.bytecode.Type;

public class ObjectExp extends ClassExp {
    public ObjectExp() {
        super(true);
    }

    public Type getType() {
        return this.type;
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitObjectExp(this, d);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void compile(gnu.expr.Compilation r6, gnu.expr.Target r7) {
        /*
            r5 = this;
            r5.compileMembers(r6)
            gnu.bytecode.CodeAttr r0 = r6.getCode()
            gnu.bytecode.ClassType r1 = r5.type
            r0.emitNew(r1)
            r1 = 1
            r0.emitDup((int) r1)
            gnu.bytecode.ClassType r1 = r5.type
            gnu.bytecode.Method r1 = gnu.expr.Compilation.getConstructor(r1, r5)
            gnu.bytecode.Field r2 = r5.closureEnvField
            if (r2 == 0) goto L_0x003a
            gnu.expr.LambdaExp r2 = r5.outerLambda()
            int r3 = gnu.expr.Compilation.defaultCallConvention
            r4 = 2
            if (r3 >= r4) goto L_0x002a
            gnu.expr.LambdaExp r2 = r5.getOwningLambda()
        L_0x0027:
            gnu.bytecode.Variable r2 = r2.heapFrame
            goto L_0x0031
        L_0x002a:
            gnu.bytecode.Variable r3 = r2.heapFrame
            if (r3 == 0) goto L_0x002f
            goto L_0x0027
        L_0x002f:
            gnu.bytecode.Variable r2 = r2.closureEnv
        L_0x0031:
            if (r2 != 0) goto L_0x0037
            r0.emitPushThis()
            goto L_0x003a
        L_0x0037:
            r0.emitLoad(r2)
        L_0x003a:
            r0.emitInvokeSpecial(r1)
            gnu.bytecode.ClassType r0 = r5.getCompiledClassType(r6)
            r7.compileFromStack(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ObjectExp.compile(gnu.expr.Compilation, gnu.expr.Target):void");
    }
}
