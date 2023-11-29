package gnu.xquery.util;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.xml.CoerceNodes;
import gnu.kawa.xml.SortNodes;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.xquery.lang.XQuery;

public class CompileMisc {
    static final Method castMethod;
    static final Method castableMethod;
    static final ClassType typeTuples = ClassType.make("gnu.xquery.util.OrderedTuples");
    static final ClassType typeXDataType;

    public static Expression validateCompare(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression inlineIfConstant = applyExp.inlineIfConstant(procedure, inlineCalls);
        if (inlineIfConstant != applyExp) {
            return inlineIfConstant;
        }
        Compare compare = (Compare) procedure;
        if ((compare.flags & 32) == 0) {
            applyExp = new ApplyExp(ClassType.make("gnu.xquery.util.Compare").getDeclaredMethod("apply", 4), new QuoteExp(IntNum.make(compare.flags)), applyExp.getArg(0), applyExp.getArg(1), QuoteExp.nullExp);
        }
        if (applyExp.getTypeRaw() == null) {
            applyExp.setType(XDataType.booleanType);
        }
        return applyExp;
    }

    public static Expression validateBooleanValue(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length == 1) {
            Expression expression = args[0];
            Type type2 = expression.getType();
            if (type2 == XDataType.booleanType) {
                return expression;
            }
            if (type2 == null) {
                applyExp.setType(XDataType.booleanType);
            }
            if (expression instanceof QuoteExp) {
                try {
                    return BooleanValue.booleanValue(((QuoteExp) expression).getValue()) ? XQuery.trueExp : XQuery.falseExp;
                } catch (Throwable unused) {
                    inlineCalls.getMessages().error('e', "cannot convert to a boolean");
                    return new ErrorExp("cannot convert to a boolean");
                }
            }
        }
        return applyExp;
    }

    public static Expression validateArithOp(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        return applyExp;
    }

    public static Expression validateApplyValuesFilter(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Method method;
        Type type2;
        ApplyExp applyExp2 = applyExp;
        ValuesFilter valuesFilter = (ValuesFilter) procedure;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        Expression expression = args[1];
        if (!(expression instanceof LambdaExp)) {
            return applyExp2;
        }
        LambdaExp lambdaExp = (LambdaExp) expression;
        if (lambdaExp.min_args != 3 || lambdaExp.max_args != 3) {
            return applyExp2;
        }
        applyExp2.setType(args[0].getType());
        Compilation compilation = inlineCalls.getCompilation();
        Declaration firstDecl = lambdaExp.firstDecl();
        Declaration nextDecl = firstDecl.nextDecl();
        Declaration nextDecl2 = nextDecl.nextDecl();
        lambdaExp.setInlineOnly(true);
        lambdaExp.returnContinuation = applyExp2;
        lambdaExp.inlineHome = inlineCalls.getCurrentLambda();
        lambdaExp.remove(nextDecl, nextDecl2);
        lambdaExp.min_args = 2;
        lambdaExp.max_args = 2;
        if (!nextDecl2.getCanRead() && valuesFilter.kind != 'R') {
            return applyExp2;
        }
        compilation.letStart();
        ApplyExp applyExp3 = args[0];
        if (valuesFilter.kind == 'P') {
            type2 = applyExp3.getType();
            method = Compilation.typeValues.getDeclaredMethod("countValues", 1);
        } else {
            type2 = SortNodes.typeSortedNodes;
            ApplyExp applyExp4 = new ApplyExp((Procedure) SortNodes.sortNodes, applyExp3);
            method = CoerceNodes.typeNodes.getDeclaredMethod(PropertyTypeConstants.PROPERTY_TYPE_FAB_SIZE, 0);
            applyExp3 = applyExp4;
        }
        Declaration letVariable = compilation.letVariable("sequence", type2, applyExp3);
        compilation.letEnter();
        LetExp letExp = lambdaExp.body;
        if (lambdaExp.body.getType() != XDataType.booleanType) {
            letExp = new ApplyExp(ValuesFilter.matchesMethod, letExp, new ReferenceExp(nextDecl));
        }
        if (valuesFilter.kind == 'R') {
            Declaration declaration = new Declaration((Object) null, (Type) Type.intType);
            ApplyExp applyExp5 = new ApplyExp((Procedure) AddOp.$Mn, new ReferenceExp(nextDecl2), new ReferenceExp(declaration));
            LetExp letExp2 = new LetExp(new Expression[]{new ApplyExp((Procedure) AddOp.$Pl, applyExp5, new QuoteExp(IntNum.one()))});
            lambdaExp.replaceFollowing(firstDecl, declaration);
            letExp2.add(nextDecl);
            letExp2.body = letExp;
            letExp = letExp2;
        }
        lambdaExp.body = new IfExp(letExp, new ReferenceExp(firstDecl), QuoteExp.voidExp);
        ApplyExp applyExp6 = new ApplyExp((Procedure) ValuesMap.valuesMapWithPos, lambdaExp, new ReferenceExp(letVariable));
        applyExp6.setType(firstDecl.getType());
        lambdaExp.returnContinuation = applyExp6;
        LetExp letExp3 = new LetExp(new Expression[]{new ApplyExp(method, new ReferenceExp(letVariable))});
        letExp3.add(nextDecl2);
        letExp3.body = gnu.kawa.functions.CompileMisc.validateApplyValuesMap(applyExp6, inlineCalls, type, ValuesMap.valuesMapWithPos);
        return compilation.letDone(letExp3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0194  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Expression validateApplyRelativeStep(gnu.expr.ApplyExp r10, gnu.expr.InlineCalls r11, gnu.bytecode.Type r12, gnu.mapping.Procedure r13) {
        /*
            r10.visitArgs(r11)
            gnu.expr.Expression[] r12 = r10.getArgs()
            r13 = 0
            r0 = r12[r13]
            r1 = 1
            r12 = r12[r1]
            gnu.expr.Compilation r2 = r11.getCompilation()
            boolean r3 = r12 instanceof gnu.expr.LambdaExp
            if (r3 == 0) goto L_0x01c6
            boolean r3 = r2.mustCompile
            if (r3 == 0) goto L_0x01c6
            gnu.expr.LambdaExp r12 = (gnu.expr.LambdaExp) r12
            int r3 = r12.min_args
            r4 = 3
            if (r3 != r4) goto L_0x01c6
            int r3 = r12.max_args
            if (r3 == r4) goto L_0x0026
            goto L_0x01c6
        L_0x0026:
            r12.setInlineOnly(r1)
            r12.returnContinuation = r10
            gnu.expr.LambdaExp r3 = r11.getCurrentLambda()
            r12.inlineHome = r3
            gnu.expr.Expression r3 = r12.body
            gnu.expr.Declaration r4 = r12.firstDecl()
            gnu.expr.Declaration r4 = r4.nextDecl()
            gnu.expr.Declaration r5 = r4.nextDecl()
            gnu.expr.Declaration r6 = r5.nextDecl()
            r4.setNext(r6)
            r4 = 0
            r5.setNext(r4)
            r6 = 2
            r12.min_args = r6
            r12.max_args = r6
            gnu.bytecode.Type r7 = r0.getType()
            r8 = -3
            if (r7 == 0) goto L_0x008f
            gnu.kawa.xml.NodeType r9 = gnu.kawa.xml.NodeType.anyNodeTest
            int r9 = r9.compare(r7)
            if (r9 != r8) goto L_0x008f
            gnu.expr.Compilation r10 = r11.getCompilation()
            gnu.expr.Language r10 = r10.getLanguage()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "step input is "
            r12.append(r13)
            java.lang.String r10 = r10.formatType(r7)
            r12.append(r10)
            java.lang.String r10 = " - not a node sequence"
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            gnu.text.SourceMessages r11 = r11.getMessages()
            r12 = 101(0x65, float:1.42E-43)
            r11.error(r12, r10)
            gnu.expr.ErrorExp r11 = new gnu.expr.ErrorExp
            r11.<init>(r10)
            return r11
        L_0x008f:
            gnu.bytecode.Type r11 = r10.getTypeRaw()
            if (r11 == 0) goto L_0x0099
            gnu.bytecode.ClassType r7 = gnu.bytecode.Type.pointer_type
            if (r11 != r7) goto L_0x00b6
        L_0x0099:
            gnu.bytecode.Type r11 = r3.getType()
            gnu.bytecode.Type r11 = gnu.kawa.reflect.OccurrenceType.itemPrimeType(r11)
            gnu.kawa.xml.NodeType r7 = gnu.kawa.xml.NodeType.anyNodeTest
            int r7 = r7.compare(r11)
            if (r7 < 0) goto L_0x00ae
            gnu.bytecode.Type r11 = gnu.kawa.xml.NodeSetType.getInstance(r11)
            goto L_0x00b3
        L_0x00ae:
            r7 = -1
            gnu.bytecode.Type r11 = gnu.kawa.reflect.OccurrenceType.getInstance(r11, r13, r7)
        L_0x00b3:
            r10.setType(r11)
        L_0x00b6:
            boolean r11 = r5.getCanRead()
            if (r11 == 0) goto L_0x010e
            gnu.bytecode.ClassType r11 = gnu.kawa.xml.CoerceNodes.typeNodes
            r2.letStart()
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            gnu.kawa.xml.CoerceNodes r7 = gnu.kawa.xml.CoerceNodes.coerceNodes
            gnu.expr.Expression[] r8 = new gnu.expr.Expression[r1]
            r8[r13] = r0
            r3.<init>((gnu.mapping.Procedure) r7, (gnu.expr.Expression[]) r8)
            gnu.expr.Declaration r0 = r2.letVariable(r4, r11, r3)
            r2.letEnter()
            java.lang.String r3 = "size"
            gnu.bytecode.Method r11 = r11.getDeclaredMethod((java.lang.String) r3, (int) r13)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r1]
            gnu.expr.ReferenceExp r7 = new gnu.expr.ReferenceExp
            r7.<init>((gnu.expr.Declaration) r0)
            r4[r13] = r7
            r3.<init>((gnu.bytecode.Method) r11, (gnu.expr.Expression[]) r4)
            gnu.expr.LetExp r11 = new gnu.expr.LetExp
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r1]
            r4[r13] = r3
            r11.<init>(r4)
            r11.addDeclaration((gnu.expr.Declaration) r5)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            gnu.expr.Expression r10 = r10.getFunction()
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r6]
            gnu.expr.ReferenceExp r5 = new gnu.expr.ReferenceExp
            r5.<init>((gnu.expr.Declaration) r0)
            r4[r13] = r5
            r4[r1] = r12
            r3.<init>((gnu.expr.Expression) r10, (gnu.expr.Expression[]) r4)
            r11.body = r3
            gnu.expr.LetExp r10 = r2.letDone(r11)
            return r10
        L_0x010e:
            boolean r11 = r3 instanceof gnu.expr.ApplyExp
            if (r11 == 0) goto L_0x0161
            r11 = r3
            gnu.expr.ApplyExp r11 = (gnu.expr.ApplyExp) r11
            gnu.expr.Expression r2 = r11.getFunction()
            java.lang.Object r2 = r2.valueIfConstant()
            boolean r2 = r2 instanceof gnu.xquery.util.ValuesFilter
            if (r2 == 0) goto L_0x0161
            gnu.expr.Expression[] r2 = r11.getArgs()
            r2 = r2[r1]
            boolean r4 = r2 instanceof gnu.expr.LambdaExp
            if (r4 == 0) goto L_0x0161
            gnu.expr.LambdaExp r2 = (gnu.expr.LambdaExp) r2
            gnu.expr.Declaration r4 = r2.firstDecl()
            if (r4 == 0) goto L_0x0161
            gnu.expr.Declaration r4 = r4.nextDecl()
            if (r4 == 0) goto L_0x0161
            gnu.expr.Declaration r5 = r4.nextDecl()
            if (r5 != 0) goto L_0x0161
            boolean r4 = r4.getCanRead()
            if (r4 != 0) goto L_0x0161
            java.lang.String r4 = "java.lang.Number"
            gnu.bytecode.ClassType r4 = gnu.bytecode.ClassType.make(r4)
            gnu.expr.Expression r2 = r2.body
            gnu.bytecode.Type r2 = r2.getType()
            int r2 = r4.compare(r2)
            if (r2 != r8) goto L_0x0161
            gnu.expr.Expression r3 = r11.getArg(r13)
            r12.body = r3
            r11.setArg(r13, r10)
            goto L_0x0162
        L_0x0161:
            r11 = r10
        L_0x0162:
            boolean r12 = r0 instanceof gnu.expr.ApplyExp
            if (r12 == 0) goto L_0x01c5
            boolean r12 = r3 instanceof gnu.expr.ApplyExp
            if (r12 == 0) goto L_0x01c5
            gnu.expr.ApplyExp r0 = (gnu.expr.ApplyExp) r0
            gnu.expr.ApplyExp r3 = (gnu.expr.ApplyExp) r3
            gnu.expr.Expression r12 = r0.getFunction()
            java.lang.Object r12 = r12.valueIfConstant()
            gnu.expr.Expression r2 = r3.getFunction()
            java.lang.Object r2 = r2.valueIfConstant()
            gnu.xquery.util.RelativeStep r4 = gnu.xquery.util.RelativeStep.relativeStep
            if (r12 != r4) goto L_0x01c5
            boolean r12 = r2 instanceof gnu.kawa.xml.ChildAxis
            if (r12 == 0) goto L_0x01c5
            int r12 = r0.getArgCount()
            if (r12 != r6) goto L_0x01c5
            gnu.expr.Expression r12 = r0.getArg(r1)
            boolean r1 = r12 instanceof gnu.expr.LambdaExp
            if (r1 == 0) goto L_0x01c5
            gnu.expr.LambdaExp r12 = (gnu.expr.LambdaExp) r12
            gnu.expr.Expression r1 = r12.body
            boolean r1 = r1 instanceof gnu.expr.ApplyExp
            if (r1 == 0) goto L_0x01c5
            gnu.expr.Expression r12 = r12.body
            gnu.expr.ApplyExp r12 = (gnu.expr.ApplyExp) r12
            gnu.expr.Expression r12 = r12.getFunction()
            java.lang.Object r12 = r12.valueIfConstant()
            gnu.kawa.xml.DescendantOrSelfAxis r1 = gnu.kawa.xml.DescendantOrSelfAxis.anyNode
            if (r12 != r1) goto L_0x01c5
            gnu.expr.Expression r12 = r0.getArg(r13)
            r10.setArg(r13, r12)
            gnu.expr.QuoteExp r10 = new gnu.expr.QuoteExp
            gnu.kawa.xml.ChildAxis r2 = (gnu.kawa.xml.ChildAxis) r2
            gnu.lists.NodePredicate r12 = r2.getNodePredicate()
            gnu.kawa.xml.DescendantAxis r12 = gnu.kawa.xml.DescendantAxis.make(r12)
            r10.<init>(r12)
            r3.setFunction(r10)
        L_0x01c5:
            return r11
        L_0x01c6:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.CompileMisc.validateApplyRelativeStep(gnu.expr.ApplyExp, gnu.expr.InlineCalls, gnu.bytecode.Type, gnu.mapping.Procedure):gnu.expr.Expression");
    }

    public static Expression validateApplyOrderedMap(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length <= 2) {
            return applyExp;
        }
        int length = args.length - 1;
        Expression[] expressionArr = new Expression[length];
        System.arraycopy(args, 1, expressionArr, 0, length);
        return new ApplyExp(procedure, args[0], new ApplyExp(typeTuples.getDeclaredMethod("make$V", 2), expressionArr));
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xml.XDataType");
        typeXDataType = make;
        castMethod = make.getDeclaredMethod("cast", 1);
        castableMethod = make.getDeclaredMethod("castable", 1);
    }

    public static void compileOrderedMap(ApplyExp applyExp, Compilation compilation, Target target, Procedure procedure) {
        Expression[] args = applyExp.getArgs();
        if (args.length != 2) {
            ApplyExp.compile(applyExp, compilation, target);
            return;
        }
        CodeAttr code = compilation.getCode();
        Scope pushScope = code.pushScope();
        ClassType classType = typeTuples;
        Variable addVariable = pushScope.addVariable(code, classType, (String) null);
        args[1].compile(compilation, Target.pushValue(classType));
        code.emitStore(addVariable);
        args[0].compile(compilation, (Target) new ConsumerTarget(addVariable));
        Method declaredMethod = classType.getDeclaredMethod("run$X", 1);
        code.emitLoad(addVariable);
        PrimProcedure.compileInvoke(compilation, declaredMethod, target, applyExp.isTailCall(), 182, Type.pointer_type);
        code.popScope();
    }

    public static Expression validateApplyCastAs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        ApplyExp inlineClassName = CompileReflect.inlineClassName(applyExp, 0, inlineCalls);
        Expression[] args = inlineClassName.getArgs();
        if (args.length != 2) {
            return inlineClassName;
        }
        Expression expression = args[0];
        return ((expression instanceof QuoteExp) && (((QuoteExp) expression).getValue() instanceof XDataType)) ? new ApplyExp(castMethod, args) : inlineClassName;
    }

    public static Expression validateApplyCastableAs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        ApplyExp inlineClassName = CompileReflect.inlineClassName(applyExp, 1, inlineCalls);
        Expression[] args = inlineClassName.getArgs();
        if (args.length != 2) {
            return inlineClassName;
        }
        Expression expression = args[1];
        if (!(expression instanceof QuoteExp) || !(((QuoteExp) expression).getValue() instanceof XDataType)) {
            return inlineClassName;
        }
        return new ApplyExp(castableMethod, args[1], args[0]);
    }
}
