package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.PrimProcedure;
import gnu.mapping.MethodProc;

public class CompileInvoke {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: gnu.expr.LetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v16, resolved type: gnu.expr.ApplyExp} */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01ff, code lost:
        if ((gnu.kawa.reflect.ClassMethods.selectApplicable(r7, new gnu.bytecode.Type[]{gnu.expr.Compilation.typeClassType}) >> 32) == 1) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0228, code lost:
        if (gnu.kawa.reflect.ClassMethods.selectApplicable(gnu.kawa.reflect.ClassMethods.getMethods(r11, com.google.appinventor.components.runtime.Component.DEFAULT_VALUE_FAB_ICON_NAME, 'V', (gnu.bytecode.ClassType) null, r3.language), 2) > 0) goto L_0x022a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x03bc, code lost:
        if (r2 == r14.minArgs()) goto L_0x03cf;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03d9 A[LOOP:5: B:189:0x039c->B:211:0x03d9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x03e3 A[EDGE_INSN: B:321:0x03e3->B:213:0x03e3 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fc A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x019f A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Expression validateApplyInvoke(gnu.expr.ApplyExp r30, gnu.expr.InlineCalls r31, gnu.bytecode.Type r32, gnu.mapping.Procedure r33) {
        /*
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r33
            gnu.kawa.reflect.Invoke r3 = (gnu.kawa.reflect.Invoke) r3
            char r4 = r3.kind
            gnu.expr.Compilation r5 = r31.getCompilation()
            gnu.expr.Expression[] r12 = r30.getArgs()
            int r13 = r12.length
            boolean r6 = r5.mustCompile
            if (r6 == 0) goto L_0x05b3
            if (r13 == 0) goto L_0x05b3
            r6 = 42
            r7 = 86
            r14 = 1
            if (r4 == r7) goto L_0x0024
            if (r4 != r6) goto L_0x0028
        L_0x0024:
            if (r13 != r14) goto L_0x0028
            goto L_0x05b3
        L_0x0028:
            r15 = 0
            r8 = r12[r15]
            r9 = 0
            gnu.expr.Expression r8 = r1.visit((gnu.expr.Expression) r8, (gnu.bytecode.Type) r9)
            r12[r15] = r8
            if (r4 == r7) goto L_0x003e
            if (r4 != r6) goto L_0x0037
            goto L_0x003e
        L_0x0037:
            gnu.expr.Language r10 = r3.language
            gnu.bytecode.Type r10 = r10.getTypeFor((gnu.expr.Expression) r8)
            goto L_0x0042
        L_0x003e:
            gnu.bytecode.Type r10 = r8.getType()
        L_0x0042:
            boolean r11 = r10 instanceof gnu.expr.PairClassType
            r9 = 78
            if (r11 == 0) goto L_0x004f
            if (r4 != r9) goto L_0x004f
            gnu.expr.PairClassType r10 = (gnu.expr.PairClassType) r10
            gnu.bytecode.ClassType r10 = r10.instanceType
            goto L_0x0055
        L_0x004f:
            boolean r11 = r10 instanceof gnu.bytecode.ObjectType
            if (r11 == 0) goto L_0x0057
            gnu.bytecode.ObjectType r10 = (gnu.bytecode.ObjectType) r10
        L_0x0055:
            r11 = r10
            goto L_0x0058
        L_0x0057:
            r11 = 0
        L_0x0058:
            java.lang.String r10 = getMethodName(r12, r4)
            r15 = 83
            r17 = -1
            r14 = 2
            if (r4 == r7) goto L_0x0089
            if (r4 != r6) goto L_0x0066
            goto L_0x0089
        L_0x0066:
            if (r4 != r9) goto L_0x006e
            r6 = r13
            r19 = 0
        L_0x006b:
            r20 = -1
            goto L_0x008f
        L_0x006e:
            if (r4 == r15) goto L_0x0084
            r6 = 115(0x73, float:1.61E-43)
            if (r4 != r6) goto L_0x0075
            goto L_0x0084
        L_0x0075:
            r6 = 80
            if (r4 != r6) goto L_0x0080
            int r6 = r13 + -2
            r19 = 3
            r20 = 1
            goto L_0x008f
        L_0x0080:
            r30.visitArgs(r31)
            return r0
        L_0x0084:
            int r6 = r13 + -2
            r19 = 2
            goto L_0x006b
        L_0x0089:
            int r6 = r13 + -1
            r19 = 2
            r20 = 0
        L_0x008f:
            r15 = 101(0x65, float:1.42E-43)
            if (r4 != r9) goto L_0x019f
            boolean r7 = r11 instanceof gnu.bytecode.ArrayType
            if (r7 == 0) goto L_0x019f
            gnu.bytecode.ArrayType r11 = (gnu.bytecode.ArrayType) r11
            gnu.bytecode.Type r2 = r11.getComponentType()
            int r3 = r12.length
            r4 = 3
            if (r3 < r4) goto L_0x00cc
            r3 = 1
            r4 = r12[r3]
            boolean r3 = r4 instanceof gnu.expr.QuoteExp
            if (r3 == 0) goto L_0x00cc
            gnu.expr.QuoteExp r4 = (gnu.expr.QuoteExp) r4
            java.lang.Object r3 = r4.getValue()
            boolean r4 = r3 instanceof gnu.expr.Keyword
            if (r4 == 0) goto L_0x00cc
            gnu.expr.Keyword r3 = (gnu.expr.Keyword) r3
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = "length"
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x00c8
            java.lang.String r4 = "size"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x00cc
        L_0x00c8:
            r3 = r12[r14]
            r4 = 1
            goto L_0x00ce
        L_0x00cc:
            r3 = 0
            r4 = 0
        L_0x00ce:
            if (r3 != 0) goto L_0x00dd
            java.lang.Integer r3 = new java.lang.Integer
            int r6 = r12.length
            r7 = 1
            int r6 = r6 - r7
            r3.<init>(r6)
            gnu.expr.QuoteExp r3 = gnu.expr.QuoteExp.getInstance(r3)
            goto L_0x00de
        L_0x00dd:
            r7 = 1
        L_0x00de:
            gnu.bytecode.PrimType r6 = gnu.bytecode.Type.intType
            gnu.expr.Expression r3 = r1.visit((gnu.expr.Expression) r3, (gnu.bytecode.Type) r6)
            gnu.expr.ApplyExp r6 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.ArrayNew r8 = new gnu.kawa.reflect.ArrayNew
            r8.<init>(r2)
            gnu.expr.Expression[] r9 = new gnu.expr.Expression[r7]
            r10 = 0
            r9[r10] = r3
            r6.<init>((gnu.mapping.Procedure) r8, (gnu.expr.Expression[]) r9)
            r6.setType(r11)
            if (r4 == 0) goto L_0x00fd
            int r3 = r12.length
            r8 = 3
            if (r3 != r8) goto L_0x00fd
            return r6
        L_0x00fd:
            gnu.expr.LetExp r3 = new gnu.expr.LetExp
            gnu.expr.Expression[] r8 = new gnu.expr.Expression[r7]
            r8[r10] = r6
            r3.<init>(r8)
            r7 = 0
            r9 = r7
            java.lang.String r9 = (java.lang.String) r9
            gnu.expr.Declaration r7 = r3.addDeclaration(r7, r11)
            r7.noteValue(r6)
            gnu.expr.BeginExp r6 = new gnu.expr.BeginExp
            r6.<init>()
            if (r4 == 0) goto L_0x011a
            r8 = 3
            goto L_0x011b
        L_0x011a:
            r8 = 1
        L_0x011b:
            r9 = 0
        L_0x011c:
            int r10 = r12.length
            if (r8 >= r10) goto L_0x0194
            r10 = r12[r8]
            if (r4 == 0) goto L_0x015f
            int r11 = r8 + 1
            int r13 = r12.length
            if (r11 >= r13) goto L_0x015f
            boolean r13 = r10 instanceof gnu.expr.QuoteExp
            if (r13 == 0) goto L_0x015f
            r13 = r10
            gnu.expr.QuoteExp r13 = (gnu.expr.QuoteExp) r13
            java.lang.Object r13 = r13.getValue()
            boolean r14 = r13 instanceof gnu.expr.Keyword
            if (r14 == 0) goto L_0x015f
            gnu.expr.Keyword r13 = (gnu.expr.Keyword) r13
            java.lang.String r8 = r13.getName()
            int r9 = java.lang.Integer.parseInt(r8)     // Catch:{ all -> 0x0145 }
            r10 = r12[r11]     // Catch:{ all -> 0x0145 }
            r8 = r11
            goto L_0x015f
        L_0x0145:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "non-integer keyword '"
            r1.append(r2)
            r1.append(r8)
            java.lang.String r2 = "' in array constructor"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r5.error(r15, r1)
            return r0
        L_0x015f:
            gnu.expr.Expression r10 = r1.visit((gnu.expr.Expression) r10, (gnu.bytecode.Type) r2)
            gnu.expr.ApplyExp r11 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.ArraySet r13 = new gnu.kawa.reflect.ArraySet
            r13.<init>(r2)
            r14 = 3
            gnu.expr.Expression[] r15 = new gnu.expr.Expression[r14]
            gnu.expr.ReferenceExp r14 = new gnu.expr.ReferenceExp
            r14.<init>((gnu.expr.Declaration) r7)
            r16 = 0
            r15[r16] = r14
            java.lang.Integer r14 = new java.lang.Integer
            r14.<init>(r9)
            gnu.expr.QuoteExp r14 = gnu.expr.QuoteExp.getInstance(r14)
            r17 = 1
            r15[r17] = r14
            r14 = 2
            r15[r14] = r10
            r11.<init>((gnu.mapping.Procedure) r13, (gnu.expr.Expression[]) r15)
            r6.add(r11)
            int r9 = r9 + 1
            int r8 = r8 + 1
            r14 = 2
            r15 = 101(0x65, float:1.42E-43)
            goto L_0x011c
        L_0x0194:
            gnu.expr.ReferenceExp r0 = new gnu.expr.ReferenceExp
            r0.<init>((gnu.expr.Declaration) r7)
            r6.add(r0)
            r3.body = r6
            return r3
        L_0x019f:
            if (r11 == 0) goto L_0x05af
            if (r10 == 0) goto L_0x05af
            boolean r7 = r11 instanceof gnu.expr.TypeValue
            if (r7 == 0) goto L_0x01c4
            if (r4 != r9) goto L_0x01c4
            r7 = r11
            gnu.expr.TypeValue r7 = (gnu.expr.TypeValue) r7
            gnu.mapping.Procedure r7 = r7.getConstructor()
            if (r7 == 0) goto L_0x01c4
            r14 = 1
            int r13 = r13 - r14
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r13]
            r3 = 0
            java.lang.System.arraycopy(r12, r14, r0, r3, r13)
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            r3.<init>((gnu.mapping.Procedure) r7, (gnu.expr.Expression[]) r0)
            gnu.expr.Expression r0 = r1.visit((gnu.expr.Expression) r3, (gnu.bytecode.Type) r2)
            return r0
        L_0x01c4:
            if (r5 != 0) goto L_0x01c8
            r14 = 0
            goto L_0x01d2
        L_0x01c8:
            gnu.bytecode.ClassType r7 = r5.curClass
            if (r7 == 0) goto L_0x01cf
            gnu.bytecode.ClassType r7 = r5.curClass
            goto L_0x01d1
        L_0x01cf:
            gnu.bytecode.ClassType r7 = r5.mainClass
        L_0x01d1:
            r14 = r7
        L_0x01d2:
            gnu.expr.PrimProcedure[] r7 = getMethods(r11, r10, r14, r3)     // Catch:{ Exception -> 0x0592 }
            int r15 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r7, (int) r6)     // Catch:{ Exception -> 0x0592 }
            r22 = 32
            if (r4 != r9) goto L_0x0357
            r23 = r6
            r9 = 1
            int r6 = hasKeywordArgument(r9, r12)
            int r9 = r12.length
            r24 = r10
            if (r6 < r9) goto L_0x0206
            if (r15 > 0) goto L_0x0202
            r9 = 1
            gnu.bytecode.Type[] r10 = new gnu.bytecode.Type[r9]
            gnu.bytecode.ClassType r9 = gnu.expr.Compilation.typeClassType
            r16 = 0
            r10[r16] = r9
            long r9 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r7, (gnu.bytecode.Type[]) r10)
            long r9 = r9 >> r22
            r25 = 1
            int r27 = (r9 > r25 ? 1 : (r9 == r25 ? 0 : -1))
            if (r27 != 0) goto L_0x0202
            goto L_0x0206
        L_0x0202:
            r26 = r4
            goto L_0x035d
        L_0x0206:
            java.lang.Object[] r9 = checkKeywords(r11, r12, r6, r14)
            int r10 = r9.length
            r25 = r14
            r14 = 2
            int r10 = r10 * 2
            int r14 = r12.length
            int r14 = r14 - r6
            r26 = r4
            java.lang.String r4 = "add"
            if (r10 == r14) goto L_0x022a
            gnu.expr.Language r10 = r3.language
            r21 = r13
            r13 = 0
            r14 = 86
            gnu.expr.PrimProcedure[] r10 = gnu.kawa.reflect.ClassMethods.getMethods(r11, r4, r14, r13, r10)
            r13 = 2
            int r10 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r10, (int) r13)
            if (r10 <= 0) goto L_0x0361
        L_0x022a:
            r10 = 0
            r13 = 0
        L_0x022c:
            int r14 = r9.length
            if (r13 >= r14) goto L_0x0259
            r14 = r9[r13]
            boolean r14 = r14 instanceof java.lang.String
            if (r14 == 0) goto L_0x0256
            if (r10 != 0) goto L_0x0242
            java.lang.StringBuffer r10 = new java.lang.StringBuffer
            r10.<init>()
            java.lang.String r14 = "no field or setter "
            r10.append(r14)
            goto L_0x0247
        L_0x0242:
            java.lang.String r14 = ", "
            r10.append(r14)
        L_0x0247:
            r14 = 96
            r10.append(r14)
            r14 = r9[r13]
            r10.append(r14)
            r14 = 39
            r10.append(r14)
        L_0x0256:
            int r13 = r13 + 1
            goto L_0x022c
        L_0x0259:
            if (r10 == 0) goto L_0x0271
            java.lang.String r1 = " in class "
            r10.append(r1)
            java.lang.String r1 = r11.getName()
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            r2 = 119(0x77, float:1.67E-43)
            r5.error(r2, r1)
            return r0
        L_0x0271:
            int r5 = r12.length
            if (r6 >= r5) goto L_0x028a
            gnu.expr.Expression[] r5 = new gnu.expr.Expression[r6]
            r10 = 0
            java.lang.System.arraycopy(r12, r10, r5, r10, r6)
            gnu.expr.ApplyExp r7 = new gnu.expr.ApplyExp
            gnu.expr.Expression r8 = r30.getFunction()
            r7.<init>((gnu.expr.Expression) r8, (gnu.expr.Expression[]) r5)
            gnu.expr.Expression r5 = r1.visit((gnu.expr.Expression) r7, (gnu.bytecode.Type) r11)
            gnu.expr.ApplyExp r5 = (gnu.expr.ApplyExp) r5
            goto L_0x0297
        L_0x028a:
            r10 = 0
            gnu.expr.ApplyExp r5 = new gnu.expr.ApplyExp
            r7 = r7[r10]
            r13 = 1
            gnu.expr.Expression[] r14 = new gnu.expr.Expression[r13]
            r14[r10] = r8
            r5.<init>((gnu.mapping.Procedure) r7, (gnu.expr.Expression[]) r14)
        L_0x0297:
            r5.setType(r11)
            int r7 = r12.length
            if (r7 <= 0) goto L_0x034e
            r7 = 0
        L_0x029e:
            int r8 = r9.length
            if (r7 >= r8) goto L_0x02ef
            r8 = r9[r7]
            boolean r10 = r8 instanceof gnu.bytecode.Method
            if (r10 == 0) goto L_0x02b2
            r10 = r8
            gnu.bytecode.Method r10 = (gnu.bytecode.Method) r10
            gnu.bytecode.Type[] r10 = r10.getParameterTypes()
            r13 = 0
            r10 = r10[r13]
            goto L_0x02bf
        L_0x02b2:
            boolean r10 = r8 instanceof gnu.bytecode.Field
            if (r10 == 0) goto L_0x02be
            r10 = r8
            gnu.bytecode.Field r10 = (gnu.bytecode.Field) r10
            gnu.bytecode.Type r10 = r10.getType()
            goto L_0x02bf
        L_0x02be:
            r10 = 0
        L_0x02bf:
            if (r10 == 0) goto L_0x02c7
            gnu.expr.Language r13 = r3.language
            gnu.bytecode.Type r10 = r13.getLangTypeFor(r10)
        L_0x02c7:
            int r13 = r7 * 2
            int r13 = r13 + r6
            r14 = 1
            int r13 = r13 + r14
            r13 = r12[r13]
            gnu.expr.Expression r10 = r1.visit((gnu.expr.Expression) r13, (gnu.bytecode.Type) r10)
            r13 = 3
            gnu.expr.Expression[] r15 = new gnu.expr.Expression[r13]
            r13 = 0
            r15[r13] = r5
            gnu.expr.QuoteExp r5 = new gnu.expr.QuoteExp
            r5.<init>(r8)
            r15[r14] = r5
            r5 = 2
            r15[r5] = r10
            gnu.expr.ApplyExp r5 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.SlotSet r8 = gnu.kawa.reflect.SlotSet.setFieldReturnObject
            r5.<init>((gnu.mapping.Procedure) r8, (gnu.expr.Expression[]) r15)
            r5.setType(r11)
            int r7 = r7 + 1
            goto L_0x029e
        L_0x02ef:
            int r3 = r12.length
            if (r6 != r3) goto L_0x02f4
            r3 = 1
            goto L_0x02f9
        L_0x02f4:
            int r3 = r9.length
            r7 = 2
            int r3 = r3 * 2
            int r3 = r3 + r6
        L_0x02f9:
            int r6 = r12.length
            if (r3 >= r6) goto L_0x034e
            gnu.expr.LetExp r6 = new gnu.expr.LetExp
            r7 = 1
            gnu.expr.Expression[] r8 = new gnu.expr.Expression[r7]
            r7 = 0
            r8[r7] = r5
            r6.<init>(r8)
            r7 = 0
            r9 = r7
            java.lang.String r9 = (java.lang.String) r9
            gnu.expr.Declaration r8 = r6.addDeclaration(r7, r11)
            r8.noteValue(r5)
            gnu.expr.BeginExp r5 = new gnu.expr.BeginExp
            r5.<init>()
        L_0x0317:
            int r7 = r12.length
            if (r3 >= r7) goto L_0x0343
            r7 = 3
            gnu.expr.Expression[] r9 = new gnu.expr.Expression[r7]
            gnu.expr.ReferenceExp r10 = new gnu.expr.ReferenceExp
            r10.<init>((gnu.expr.Declaration) r8)
            r11 = 0
            r9[r11] = r10
            gnu.expr.QuoteExp r10 = gnu.expr.QuoteExp.getInstance(r4)
            r11 = 1
            r9[r11] = r10
            r10 = r12[r3]
            r11 = 2
            r9[r11] = r10
            gnu.expr.ApplyExp r10 = new gnu.expr.ApplyExp
            gnu.kawa.reflect.Invoke r11 = gnu.kawa.reflect.Invoke.invoke
            r10.<init>((gnu.mapping.Procedure) r11, (gnu.expr.Expression[]) r9)
            r9 = 0
            gnu.expr.Expression r10 = r1.visit((gnu.expr.Expression) r10, (gnu.bytecode.Type) r9)
            r5.add(r10)
            int r3 = r3 + 1
            goto L_0x0317
        L_0x0343:
            gnu.expr.ReferenceExp r3 = new gnu.expr.ReferenceExp
            r3.<init>((gnu.expr.Declaration) r8)
            r5.add(r3)
            r6.body = r5
            r5 = r6
        L_0x034e:
            gnu.expr.Expression r0 = r5.setLine((gnu.expr.Expression) r0)
            gnu.expr.Expression r0 = r1.checkType(r0, r2)
            return r0
        L_0x0357:
            r26 = r4
            r23 = r6
            r24 = r10
        L_0x035d:
            r21 = r13
            r25 = r14
        L_0x0361:
            r9 = 0
            if (r15 < 0) goto L_0x0410
            r4 = r21
            r3 = 1
        L_0x0367:
            if (r3 >= r4) goto L_0x03f4
            int r13 = r4 + -1
            if (r3 != r13) goto L_0x0371
            r13 = r26
            r6 = 1
            goto L_0x0374
        L_0x0371:
            r13 = r26
            r6 = 0
        L_0x0374:
            r8 = 80
            r10 = 2
            if (r13 != r8) goto L_0x037f
            if (r3 == r10) goto L_0x037c
            goto L_0x037f
        L_0x037c:
            r14 = 1
            goto L_0x03e3
        L_0x037f:
            r14 = 78
            if (r13 == r14) goto L_0x0388
            r14 = 1
            if (r3 != r14) goto L_0x0389
            goto L_0x03e3
        L_0x0388:
            r14 = 1
        L_0x0389:
            if (r13 != r8) goto L_0x038f
            if (r3 != r14) goto L_0x038f
            r9 = r11
            goto L_0x03e3
        L_0x038f:
            if (r15 <= 0) goto L_0x03e2
            r14 = 78
            if (r13 != r14) goto L_0x0397
            r8 = 1
            goto L_0x0399
        L_0x0397:
            r8 = r19
        L_0x0399:
            int r8 = r3 - r8
            r10 = 0
        L_0x039c:
            if (r10 >= r15) goto L_0x03e3
            r14 = r7[r10]
            r2 = 83
            if (r13 == r2) goto L_0x03ac
            boolean r2 = r14.takesTarget()
            if (r2 == 0) goto L_0x03ac
            r2 = 1
            goto L_0x03ad
        L_0x03ac:
            r2 = 0
        L_0x03ad:
            int r2 = r2 + r8
            if (r6 == 0) goto L_0x03bf
            boolean r18 = r14.takesVarArgs()
            if (r18 == 0) goto L_0x03bf
            r18 = r6
            int r6 = r14.minArgs()
            if (r2 != r6) goto L_0x03c1
            goto L_0x03cf
        L_0x03bf:
            r18 = r6
        L_0x03c1:
            gnu.bytecode.Type r2 = r14.getParameterType(r2)
            if (r10 != 0) goto L_0x03c9
        L_0x03c7:
            r9 = r2
            goto L_0x03d6
        L_0x03c9:
            boolean r6 = r2 instanceof gnu.bytecode.PrimType
            boolean r14 = r9 instanceof gnu.bytecode.PrimType
            if (r6 == r14) goto L_0x03d1
        L_0x03cf:
            r9 = 0
            goto L_0x03d6
        L_0x03d1:
            gnu.bytecode.Type r2 = gnu.bytecode.Type.lowestCommonSuperType(r9, r2)
            goto L_0x03c7
        L_0x03d6:
            if (r9 != 0) goto L_0x03d9
            goto L_0x03e3
        L_0x03d9:
            int r10 = r10 + 1
            r2 = r32
            r6 = r18
            r14 = 78
            goto L_0x039c
        L_0x03e2:
            r9 = 0
        L_0x03e3:
            r2 = r12[r3]
            gnu.expr.Expression r2 = r1.visit((gnu.expr.Expression) r2, (gnu.bytecode.Type) r9)
            r12[r3] = r2
            int r3 = r3 + 1
            r2 = r32
            r26 = r13
            r9 = 0
            goto L_0x0367
        L_0x03f4:
            r2 = r23
            r13 = r26
            r6 = r7
            r3 = r7
            r7 = r11
            r8 = r12
            r14 = 78
            r9 = r2
            r28 = r24
            r10 = r19
            r29 = r11
            r11 = r20
            long r6 = selectApplicable(r6, r7, r8, r9, r10, r11)
            long r8 = r6 >> r22
            int r10 = (int) r8
            int r7 = (int) r6
            goto L_0x041f
        L_0x0410:
            r3 = r7
            r29 = r11
            r4 = r21
            r2 = r23
            r28 = r24
            r13 = r26
            r14 = 78
            r7 = 0
            r10 = 0
        L_0x041f:
            int r11 = r3.length
            int r6 = r10 + r7
            if (r6 != 0) goto L_0x044c
            if (r13 != r14) goto L_0x044c
            gnu.kawa.reflect.Invoke r2 = gnu.kawa.reflect.Invoke.invokeStatic
            java.lang.String r3 = "valueOf"
            r7 = r25
            r10 = r29
            gnu.expr.PrimProcedure[] r2 = getMethods(r10, r3, r7, r2)
            r19 = 1
            r3 = 1
            int r4 = r4 - r3
            r3 = -1
            r6 = r2
            r7 = r10
            r8 = r12
            r9 = r4
            r18 = r10
            r10 = r19
            r21 = r11
            r11 = r3
            long r6 = selectApplicable(r6, r7, r8, r9, r10, r11)
            long r8 = r6 >> r22
            int r10 = (int) r8
            int r7 = (int) r6
            r6 = r4
            goto L_0x0452
        L_0x044c:
            r21 = r11
            r18 = r29
            r6 = r2
            r2 = r3
        L_0x0452:
            int r3 = r10 + r7
            java.lang.String r4 = "' in "
            if (r3 != 0) goto L_0x04c6
            r3 = 80
            if (r13 == r3) goto L_0x0466
            boolean r3 = r5.warnInvokeUnknownMethod()
            if (r3 == 0) goto L_0x0463
            goto L_0x0466
        L_0x0463:
            r3 = 1
            goto L_0x055f
        L_0x0466:
            if (r13 != r14) goto L_0x047c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r8 = r28
            r3.append(r8)
            java.lang.String r7 = "/valueOf"
            r3.append(r7)
            java.lang.String r10 = r3.toString()
            goto L_0x047f
        L_0x047c:
            r8 = r28
            r10 = r8
        L_0x047f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r7 = r2.length
            int r11 = r21 + r7
            if (r11 != 0) goto L_0x048f
            java.lang.String r7 = "no accessible method '"
            r3.append(r7)
            goto L_0x04a8
        L_0x048f:
            r7 = -983040(0xfffffffffff10000, float:NaN)
            if (r15 != r7) goto L_0x0499
            java.lang.String r7 = "too few arguments for method '"
            r3.append(r7)
            goto L_0x04a8
        L_0x0499:
            r7 = -917504(0xfffffffffff20000, float:NaN)
            if (r15 != r7) goto L_0x04a3
            java.lang.String r7 = "too many arguments for method '"
            r3.append(r7)
            goto L_0x04a8
        L_0x04a3:
            java.lang.String r7 = "no possibly applicable method '"
            r3.append(r7)
        L_0x04a8:
            r3.append(r10)
            r3.append(r4)
            java.lang.String r4 = r18.getName()
            r3.append(r4)
            r4 = 80
            if (r13 != r4) goto L_0x04bc
            r15 = 101(0x65, float:1.42E-43)
            goto L_0x04be
        L_0x04bc:
            r15 = 119(0x77, float:1.67E-43)
        L_0x04be:
            java.lang.String r3 = r3.toString()
            r5.error(r15, r3)
            goto L_0x0463
        L_0x04c6:
            r8 = r28
            r3 = 1
            if (r10 == r3) goto L_0x055d
            if (r10 != 0) goto L_0x04d1
            if (r7 != r3) goto L_0x04d1
            goto L_0x055d
        L_0x04d1:
            if (r10 <= 0) goto L_0x0528
            int r7 = gnu.mapping.MethodProc.mostSpecific((gnu.mapping.MethodProc[]) r2, (int) r10)
            if (r7 >= 0) goto L_0x04ef
            r9 = 83
            if (r13 != r9) goto L_0x04ef
            r9 = 0
        L_0x04de:
            if (r9 >= r10) goto L_0x04ef
            r11 = r2[r9]
            boolean r11 = r11.getStaticFlag()
            if (r11 == 0) goto L_0x04ec
            if (r7 < 0) goto L_0x04eb
            goto L_0x04f1
        L_0x04eb:
            r7 = r9
        L_0x04ec:
            int r9 = r9 + 1
            goto L_0x04de
        L_0x04ef:
            r17 = r7
        L_0x04f1:
            if (r17 >= 0) goto L_0x055f
            r7 = 80
            if (r13 == r7) goto L_0x04fd
            boolean r7 = r5.warnInvokeUnknownMethod()
            if (r7 == 0) goto L_0x055f
        L_0x04fd:
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r7.<init>()
            java.lang.String r9 = "more than one definitely applicable method `"
            r7.append(r9)
            r7.append(r8)
            r7.append(r4)
            java.lang.String r4 = r18.getName()
            r7.append(r4)
            append(r2, r10, r7)
            r9 = 80
            if (r13 != r9) goto L_0x051e
            r15 = 101(0x65, float:1.42E-43)
            goto L_0x0520
        L_0x051e:
            r15 = 119(0x77, float:1.67E-43)
        L_0x0520:
            java.lang.String r4 = r7.toString()
            r5.error(r15, r4)
            goto L_0x055f
        L_0x0528:
            r9 = 80
            if (r13 == r9) goto L_0x0532
            boolean r9 = r5.warnInvokeUnknownMethod()
            if (r9 == 0) goto L_0x055f
        L_0x0532:
            java.lang.StringBuffer r9 = new java.lang.StringBuffer
            r9.<init>()
            java.lang.String r10 = "more than one possibly applicable method '"
            r9.append(r10)
            r9.append(r8)
            r9.append(r4)
            java.lang.String r4 = r18.getName()
            r9.append(r4)
            append(r2, r7, r9)
            r4 = 80
            if (r13 != r4) goto L_0x0553
            r15 = 101(0x65, float:1.42E-43)
            goto L_0x0555
        L_0x0553:
            r15 = 119(0x77, float:1.67E-43)
        L_0x0555:
            java.lang.String r4 = r9.toString()
            r5.error(r15, r4)
            goto L_0x055f
        L_0x055d:
            r17 = 0
        L_0x055f:
            if (r17 < 0) goto L_0x05af
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r6]
            r2 = r2[r17]
            r2.takesVarArgs()
            if (r20 < 0) goto L_0x0571
            r5 = r12[r20]
            r7 = 0
            r4[r7] = r5
            r14 = 1
            goto L_0x0573
        L_0x0571:
            r7 = 0
            r14 = 0
        L_0x0573:
            r3 = r19
        L_0x0575:
            int r5 = r12.length
            if (r3 >= r5) goto L_0x0583
            if (r14 >= r6) goto L_0x0583
            r5 = r12[r3]
            r4[r14] = r5
            int r3 = r3 + 1
            int r14 = r14 + 1
            goto L_0x0575
        L_0x0583:
            gnu.expr.ApplyExp r3 = new gnu.expr.ApplyExp
            r3.<init>((gnu.mapping.Procedure) r2, (gnu.expr.Expression[]) r4)
            r3.setLine((gnu.expr.Expression) r0)
            r0 = r32
            gnu.expr.Expression r0 = r1.visitApplyOnly(r3, r0)
            return r0
        L_0x0592:
            r18 = r11
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unknown class: "
            r1.append(r2)
            java.lang.String r2 = r18.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 119(0x77, float:1.67E-43)
            r5.error(r2, r1)
            return r0
        L_0x05af:
            r30.visitArgs(r31)
            return r0
        L_0x05b3:
            r30.visitArgs(r31)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.CompileInvoke.validateApplyInvoke(gnu.expr.ApplyExp, gnu.expr.InlineCalls, gnu.bytecode.Type, gnu.mapping.Procedure):gnu.expr.Expression");
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [gnu.bytecode.Member] */
    /* JADX WARNING: type inference failed for: r4v5, types: [gnu.bytecode.Method] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object[] checkKeywords(gnu.bytecode.ObjectType r6, gnu.expr.Expression[] r7, int r8, gnu.bytecode.ClassType r9) {
        /*
            int r0 = r7.length
            r1 = 0
            r2 = 0
        L_0x0003:
            int r3 = r2 * 2
            int r3 = r3 + r8
            int r4 = r3 + 1
            if (r4 >= r0) goto L_0x0017
            r3 = r7[r3]
            java.lang.Object r3 = r3.valueIfConstant()
            boolean r3 = r3 instanceof gnu.expr.Keyword
            if (r3 == 0) goto L_0x0017
            int r2 = r2 + 1
            goto L_0x0003
        L_0x0017:
            java.lang.Object[] r0 = new java.lang.Object[r2]
        L_0x0019:
            if (r1 >= r2) goto L_0x0044
            int r3 = r1 * 2
            int r3 = r3 + r8
            r3 = r7[r3]
            java.lang.Object r3 = r3.valueIfConstant()
            gnu.expr.Keyword r3 = (gnu.expr.Keyword) r3
            java.lang.String r3 = r3.getName()
            gnu.bytecode.Member r4 = gnu.kawa.reflect.SlotSet.lookupMember(r6, r3, r9)
            if (r4 != 0) goto L_0x003c
            java.lang.String r4 = "add"
            java.lang.String r4 = gnu.expr.ClassExp.slotToMethodName(r4, r3)
            gnu.bytecode.Type[] r5 = gnu.kawa.reflect.SlotSet.type1Array
            gnu.bytecode.Method r4 = r6.getMethod(r4, r5)
        L_0x003c:
            if (r4 == 0) goto L_0x003f
            r3 = r4
        L_0x003f:
            r0[r1] = r3
            int r1 = r1 + 1
            goto L_0x0019
        L_0x0044:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.CompileInvoke.checkKeywords(gnu.bytecode.ObjectType, gnu.expr.Expression[], int, gnu.bytecode.ClassType):java.lang.Object[]");
    }

    private static String getMethodName(Expression[] expressionArr, char c) {
        if (c == 'N') {
            return "<init>";
        }
        int i = c == 'P' ? 2 : 1;
        if (expressionArr.length >= i + 1) {
            return ClassMethods.checkName(expressionArr[i], false);
        }
        return null;
    }

    private static void append(PrimProcedure[] primProcedureArr, int i, StringBuffer stringBuffer) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("\n  candidate: ");
            stringBuffer.append(primProcedureArr[i2]);
        }
    }

    protected static PrimProcedure[] getMethods(ObjectType objectType, String str, ClassType classType, Invoke invoke) {
        char c = invoke.kind;
        char c2 = 'V';
        if (c == 'P') {
            c2 = 'P';
        } else if (!(c == '*' || c == 'V')) {
            c2 = 0;
        }
        return ClassMethods.getMethods(objectType, str, c2, classType, invoke.language);
    }

    static int hasKeywordArgument(int i, Expression[] expressionArr) {
        while (i < expressionArr.length) {
            if (expressionArr[i].valueIfConstant() instanceof Keyword) {
                return i;
            }
            i++;
        }
        return expressionArr.length;
    }

    private static long selectApplicable(PrimProcedure[] primProcedureArr, ObjectType objectType, Expression[] expressionArr, int i, int i2, int i3) {
        Type type;
        Type[] typeArr = new Type[i];
        int i4 = 0;
        if (i3 >= 0) {
            typeArr[0] = objectType;
            i4 = 1;
        }
        while (i2 < expressionArr.length && i4 < i) {
            Expression expression = expressionArr[i2];
            if (InlineCalls.checkIntValue(expression) != null) {
                type = Type.intType;
            } else if (InlineCalls.checkLongValue(expression) != null) {
                type = Type.longType;
            } else {
                type = expression.getType();
            }
            typeArr[i4] = type;
            i2++;
            i4++;
        }
        return ClassMethods.selectApplicable(primProcedureArr, typeArr);
    }

    public static synchronized PrimProcedure getStaticMethod(ClassType classType, String str, Expression[] expressionArr) {
        PrimProcedure primProcedure;
        synchronized (CompileInvoke.class) {
            primProcedure = null;
            PrimProcedure[] methods = getMethods(classType, str, (ClassType) null, Invoke.invokeStatic);
            long selectApplicable = selectApplicable(methods, classType, expressionArr, expressionArr.length, 0, -1);
            int i = (int) (selectApplicable >> 32);
            int i2 = (int) selectApplicable;
            int i3 = -1;
            if (methods != null) {
                if (i > 0) {
                    i3 = MethodProc.mostSpecific((MethodProc[]) methods, i);
                } else if (i2 == 1) {
                    i3 = 0;
                }
            }
            if (i3 >= 0) {
                primProcedure = methods[i3];
            }
        }
        return primProcedure;
    }
}
