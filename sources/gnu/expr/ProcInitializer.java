package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;

public class ProcInitializer extends Initializer {
    LambdaExp proc;

    public ProcInitializer(LambdaExp lambdaExp, Compilation compilation, Field field) {
        this.field = field;
        this.proc = lambdaExp;
        LambdaExp module = field.getStaticFlag() ? compilation.getModule() : lambdaExp.getOwningLambda();
        if (!(module instanceof ModuleExp) || !compilation.isStatic()) {
            this.next = module.initChain;
            module.initChain = this;
            return;
        }
        this.next = compilation.clinitChain;
        compilation.clinitChain = this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v44, resolved type: gnu.expr.ModuleMethod} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void emitLoadModuleMethod(gnu.expr.LambdaExp r8, gnu.expr.Compilation r9) {
        /*
            gnu.expr.Declaration r0 = r8.nameDecl
            if (r0 != 0) goto L_0x0009
            java.lang.String r1 = r8.getName()
            goto L_0x000d
        L_0x0009:
            java.lang.Object r1 = r0.getSymbol()
        L_0x000d:
            boolean r2 = r9.immediate
            r3 = 0
            if (r2 == 0) goto L_0x0045
            if (r1 == 0) goto L_0x0045
            if (r0 == 0) goto L_0x0045
            gnu.mapping.Environment r0 = gnu.mapping.Environment.getCurrent()
            boolean r2 = r1 instanceof gnu.mapping.Symbol
            if (r2 == 0) goto L_0x0022
            r2 = r1
            gnu.mapping.Symbol r2 = (gnu.mapping.Symbol) r2
            goto L_0x0030
        L_0x0022:
            java.lang.String r2 = r1.toString()
            java.lang.String r2 = r2.intern()
            java.lang.String r4 = ""
            gnu.mapping.Symbol r2 = gnu.mapping.Symbol.make(r4, r2)
        L_0x0030:
            gnu.expr.Language r4 = r9.getLanguage()
            gnu.expr.Declaration r5 = r8.nameDecl
            java.lang.Object r4 = r4.getEnvPropertyFor(r5)
            java.lang.Object r0 = r0.get(r2, r4, r3)
            boolean r2 = r0 instanceof gnu.expr.ModuleMethod
            if (r2 == 0) goto L_0x0045
            r3 = r0
            gnu.expr.ModuleMethod r3 = (gnu.expr.ModuleMethod) r3
        L_0x0045:
            gnu.bytecode.CodeAttr r0 = r9.getCode()
            gnu.bytecode.ClassType r2 = gnu.expr.Compilation.typeModuleMethod
            r4 = 1
            if (r3 != 0) goto L_0x0057
            r0.emitNew(r2)
            r0.emitDup((int) r4)
            java.lang.String r3 = "<init>"
            goto L_0x0060
        L_0x0057:
            gnu.expr.Target r5 = gnu.expr.Target.pushValue(r2)
            r9.compileConstant(r3, r5)
            java.lang.String r3 = "init"
        L_0x0060:
            r5 = 4
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r5)
            boolean r3 = r8.getNeedsClosureEnv()
            if (r3 == 0) goto L_0x0070
            gnu.expr.LambdaExp r3 = r8.getOwningLambda()
            goto L_0x0074
        L_0x0070:
            gnu.expr.ModuleExp r3 = r9.getModule()
        L_0x0074:
            boolean r5 = r3 instanceof gnu.expr.ClassExp
            r6 = 0
            if (r5 == 0) goto L_0x0089
            gnu.bytecode.Field r5 = r3.staticLinkField
            if (r5 == 0) goto L_0x0089
            gnu.bytecode.Scope r3 = r0.getCurrentScope()
            gnu.bytecode.Variable r3 = r3.getVariable(r4)
            r0.emitLoad(r3)
            goto L_0x00f8
        L_0x0089:
            boolean r3 = r3 instanceof gnu.expr.ModuleExp
            if (r3 == 0) goto L_0x00f5
            gnu.bytecode.ClassType r3 = r9.moduleClass
            gnu.bytecode.ClassType r5 = r9.mainClass
            if (r3 != r5) goto L_0x009c
            gnu.bytecode.Method r3 = r9.method
            boolean r3 = r3.getStaticFlag()
            if (r3 != 0) goto L_0x009c
            goto L_0x00f5
        L_0x009c:
            gnu.bytecode.Variable r3 = r9.moduleInstanceVar
            if (r3 != 0) goto L_0x00ef
            gnu.bytecode.LocalVarsAttr r3 = r0.locals
            gnu.bytecode.Scope r3 = r3.current_scope
            gnu.bytecode.ClassType r5 = r9.moduleClass
            java.lang.String r7 = "$instance"
            gnu.bytecode.Variable r3 = r3.addVariable(r0, r5, r7)
            r9.moduleInstanceVar = r3
            gnu.bytecode.ClassType r3 = r9.moduleClass
            gnu.bytecode.ClassType r5 = r9.mainClass
            if (r3 == r5) goto L_0x00e5
            boolean r3 = r9.isStatic()
            if (r3 != 0) goto L_0x00e5
            gnu.bytecode.ClassType r3 = r9.moduleClass
            r0.emitNew(r3)
            gnu.bytecode.ClassType r3 = r9.moduleClass
            r0.emitDup((gnu.bytecode.Type) r3)
            gnu.bytecode.ClassType r3 = r9.moduleClass
            gnu.bytecode.Method r3 = r3.constructor
            r0.emitInvokeSpecial(r3)
            gnu.bytecode.ClassType r3 = r9.moduleClass
            gnu.bytecode.ClassType r5 = r9.mainClass
            java.lang.String r7 = "$main"
            gnu.bytecode.Field r3 = r3.addField(r7, r5, r6)
            r9.moduleInstanceMainField = r3
            gnu.bytecode.ClassType r3 = r9.moduleClass
            r0.emitDup((gnu.bytecode.Type) r3)
            r0.emitPushThis()
            gnu.bytecode.Field r3 = r9.moduleInstanceMainField
            r0.emitPutField(r3)
            goto L_0x00ea
        L_0x00e5:
            gnu.bytecode.Field r3 = r9.moduleInstanceMainField
            r0.emitGetStatic(r3)
        L_0x00ea:
            gnu.bytecode.Variable r3 = r9.moduleInstanceVar
            r0.emitStore(r3)
        L_0x00ef:
            gnu.bytecode.Variable r3 = r9.moduleInstanceVar
            r0.emitLoad(r3)
            goto L_0x00f8
        L_0x00f5:
            r0.emitPushThis()
        L_0x00f8:
            int r3 = r8.getSelectorValue(r9)
            r0.emitPushInt(r3)
            gnu.expr.Target r3 = gnu.expr.Target.pushObject
            r9.compileConstant(r1, r3)
            int r1 = r8.min_args
            gnu.expr.Keyword[] r3 = r8.keywords
            if (r3 != 0) goto L_0x010d
            int r3 = r8.max_args
            goto L_0x010e
        L_0x010d:
            r3 = -1
        L_0x010e:
            int r3 = r3 << 12
            r1 = r1 | r3
            r0.emitPushInt(r1)
            r0.emitInvoke(r2)
            java.lang.Object[] r1 = r8.properties
            if (r1 == 0) goto L_0x0158
            java.lang.Object[] r1 = r8.properties
            int r1 = r1.length
        L_0x011e:
            if (r6 >= r1) goto L_0x0158
            java.lang.Object[] r2 = r8.properties
            r2 = r2[r6]
            if (r2 == 0) goto L_0x0155
            gnu.mapping.Symbol r3 = gnu.mapping.PropertySet.nameKey
            if (r2 == r3) goto L_0x0155
            java.lang.Object[] r3 = r8.properties
            int r5 = r6 + 1
            r3 = r3[r5]
            r0.emitDup((int) r4)
            r9.compileConstant(r2)
            gnu.expr.Target r2 = gnu.expr.Target.pushObject
            boolean r5 = r3 instanceof gnu.expr.Expression
            if (r5 == 0) goto L_0x0142
            gnu.expr.Expression r3 = (gnu.expr.Expression) r3
            r3.compile((gnu.expr.Compilation) r9, (gnu.expr.Target) r2)
            goto L_0x0145
        L_0x0142:
            r9.compileConstant(r3, r2)
        L_0x0145:
            java.lang.String r2 = "gnu.mapping.PropertySet"
            gnu.bytecode.ClassType r2 = gnu.bytecode.ClassType.make(r2)
            java.lang.String r3 = "setProperty"
            r5 = 2
            gnu.bytecode.Method r2 = r2.getDeclaredMethod((java.lang.String) r3, (int) r5)
            r0.emitInvokeVirtual(r2)
        L_0x0155:
            int r6 = r6 + 2
            goto L_0x011e
        L_0x0158:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ProcInitializer.emitLoadModuleMethod(gnu.expr.LambdaExp, gnu.expr.Compilation):void");
    }

    public void emit(Compilation compilation) {
        CodeAttr code = compilation.getCode();
        if (!this.field.getStaticFlag()) {
            code.emitPushThis();
        }
        emitLoadModuleMethod(this.proc, compilation);
        if (this.field.getStaticFlag()) {
            code.emitPutStatic(this.field);
        } else {
            code.emitPutField(this.field);
        }
    }

    public void reportError(String str, Compilation compilation) {
        String fileName = compilation.getFileName();
        int lineNumber = compilation.getLineNumber();
        int columnNumber = compilation.getColumnNumber();
        compilation.setLocation(this.proc);
        String name = this.proc.getName();
        StringBuffer stringBuffer = new StringBuffer(str);
        if (name == null) {
            stringBuffer.append("unnamed procedure");
        } else {
            stringBuffer.append("procedure ");
            stringBuffer.append(name);
        }
        compilation.error('e', stringBuffer.toString());
        compilation.setLine(fileName, lineNumber, columnNumber);
    }
}
