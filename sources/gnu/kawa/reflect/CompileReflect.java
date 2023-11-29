package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class CompileReflect {
    public static int checkKnownClass(Type type, Compilation compilation) {
        if (!(type instanceof ClassType) || !type.isExisting()) {
            return 0;
        }
        try {
            type.getReflectClass();
            return 1;
        } catch (Exception unused) {
            compilation.error('e', "unknown class: " + type.getName());
            return -1;
        }
    }

    public static ApplyExp inlineClassName(ApplyExp applyExp, int i, InlineCalls inlineCalls) {
        Compilation compilation = inlineCalls.getCompilation();
        Language language = compilation.getLanguage();
        Expression[] args = applyExp.getArgs();
        if (args.length <= i) {
            return applyExp;
        }
        Type typeFor = language.getTypeFor(args[i]);
        if (!(typeFor instanceof Type)) {
            return applyExp;
        }
        checkKnownClass(typeFor, compilation);
        Expression[] expressionArr = new Expression[args.length];
        System.arraycopy(args, 0, expressionArr, 0, args.length);
        expressionArr[i] = new QuoteExp(typeFor);
        ApplyExp applyExp2 = new ApplyExp(applyExp.getFunction(), expressionArr);
        applyExp2.setLine((Expression) applyExp);
        return applyExp2;
    }

    public static Expression validateApplyInstanceOf(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        ApplyExp inlineClassName = inlineClassName(applyExp, 1, inlineCalls);
        Expression[] args = inlineClassName.getArgs();
        if (args.length != 2) {
            return inlineClassName;
        }
        Expression expression = args[0];
        Expression expression2 = args[1];
        if (!(expression2 instanceof QuoteExp)) {
            return inlineClassName;
        }
        Object value = ((QuoteExp) expression2).getValue();
        if (!(value instanceof Type)) {
            return inlineClassName;
        }
        Type type2 = (Type) value;
        if (type2 instanceof PrimType) {
            type2 = ((PrimType) type2).boxedType();
        }
        if (expression instanceof QuoteExp) {
            return type2.isInstance(((QuoteExp) expression).getValue()) ? QuoteExp.trueExp : QuoteExp.falseExp;
        }
        if (expression.side_effects()) {
            return inlineClassName;
        }
        int compare = type2.compare(expression.getType());
        if (compare == 1 || compare == 0) {
            return QuoteExp.trueExp;
        }
        if (compare == -3) {
            return QuoteExp.falseExp;
        }
        return inlineClassName;
    }

    public static Expression validateApplySlotGet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Type type2;
        ApplyExp applyExp2 = applyExp;
        applyExp.visitArgs(inlineCalls);
        Compilation compilation = inlineCalls.getCompilation();
        Language language = compilation.getLanguage();
        boolean z = ((SlotGet) procedure).isStatic;
        Expression[] args = applyExp.getArgs();
        Expression expression = args[0];
        Expression expression2 = args[1];
        Object valueIfConstant = expression2.valueIfConstant();
        if (!(valueIfConstant instanceof String) && !(valueIfConstant instanceof FString) && !(valueIfConstant instanceof Symbol)) {
            return applyExp2;
        }
        String obj = valueIfConstant.toString();
        if (z) {
            type2 = language.getTypeFor(expression);
            int checkKnownClass = checkKnownClass(type2, compilation);
            if (checkKnownClass < 0) {
                return applyExp2;
            }
            if ("class".equals(obj)) {
                if (checkKnownClass > 0) {
                    return QuoteExp.getInstance(type2.getReflectClass());
                }
                return new ApplyExp(Compilation.typeType.getDeclaredMethod("getReflectClass", 0), expression);
            } else if (type2 != null) {
                ApplyExp applyExp3 = new ApplyExp(applyExp.getFunction(), new QuoteExp(type2), expression2);
                applyExp3.setLine((Expression) applyExp2);
                applyExp2 = applyExp3;
            }
        } else {
            type2 = expression.getType();
        }
        if (type2 instanceof ArrayType) {
            return applyExp2;
        }
        if (type2 instanceof ObjectType) {
            ObjectType objectType = (ObjectType) type2;
            ClassType classType = compilation.curClass != null ? compilation.curClass : compilation.mainClass;
            Member lookupMember = SlotGet.lookupMember(objectType, obj, classType);
            if (lookupMember instanceof Field) {
                Field field = (Field) lookupMember;
                boolean z2 = (field.getModifiers() & 8) != 0;
                if (z && !z2) {
                    return new ErrorExp("cannot access non-static field `" + obj + "' using `" + procedure.getName() + '\'', compilation);
                } else if (classType != null && !classType.isAccessible(field, objectType)) {
                    return new ErrorExp("field " + field.getDeclaringClass().getName() + '.' + obj + " is not accessible here", compilation);
                }
            } else if (lookupMember instanceof Method) {
                Method method = (Method) lookupMember;
                ClassType declaringClass = method.getDeclaringClass();
                int modifiers = method.getModifiers();
                boolean staticFlag = method.getStaticFlag();
                if (z && !staticFlag) {
                    return new ErrorExp("cannot call non-static getter method `" + obj + "' using `" + procedure.getName() + '\'', compilation);
                } else if (classType != null && !classType.isAccessible(declaringClass, objectType, modifiers)) {
                    return new ErrorExp("method " + method + " is not accessible here", compilation);
                }
            }
            if (lookupMember != null) {
                ApplyExp applyExp4 = new ApplyExp(applyExp2.getFunction(), expression, new QuoteExp(lookupMember));
                applyExp4.setLine((Expression) applyExp2);
                return applyExp4;
            } else if (type2 != Type.pointer_type && compilation.warnUnknownMember()) {
                compilation.error('e', "no slot `" + obj + "' in " + objectType.getName());
            }
        }
        String intern = Compilation.mangleNameIfNeeded(obj).intern();
        String slotToMethodName = ClassExp.slotToMethodName("get", obj);
        String slotToMethodName2 = ClassExp.slotToMethodName("is", obj);
        Invoke invoke = Invoke.invokeStatic;
        Expression[] expressionArr = new Expression[9];
        expressionArr[0] = QuoteExp.getInstance("gnu.kawa.reflect.SlotGet");
        expressionArr[1] = QuoteExp.getInstance("getSlotValue");
        expressionArr[2] = z ? QuoteExp.trueExp : QuoteExp.falseExp;
        expressionArr[3] = args[0];
        expressionArr[4] = QuoteExp.getInstance(obj);
        expressionArr[5] = QuoteExp.getInstance(intern);
        expressionArr[6] = QuoteExp.getInstance(slotToMethodName);
        expressionArr[7] = QuoteExp.getInstance(slotToMethodName2);
        expressionArr[8] = QuoteExp.getInstance(language);
        ApplyExp applyExp5 = new ApplyExp((Procedure) invoke, expressionArr);
        applyExp5.setLine((Expression) applyExp2);
        return inlineCalls.visitApplyOnly(applyExp5, (Type) null);
    }

    public static Expression validateApplySlotSet(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        SlotSet slotSet = (SlotSet) procedure;
        if (slotSet.isStatic && inlineCalls.getCompilation().mustCompile) {
            applyExp = inlineClassName(applyExp, 0, inlineCalls);
        }
        applyExp.setType((!slotSet.returnSelf || applyExp.getArgCount() != 3) ? Type.voidType : applyExp.getArg(0).getType());
        return applyExp;
    }

    public static Expression validateApplyTypeSwitch(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        for (int i = 1; i < args.length; i++) {
            Expression expression = args[i];
            if (expression instanceof LambdaExp) {
                LambdaExp lambdaExp = (LambdaExp) expression;
                lambdaExp.setInlineOnly(true);
                lambdaExp.returnContinuation = applyExp;
                lambdaExp.inlineHome = inlineCalls.getCurrentLambda();
            }
        }
        return applyExp;
    }
}
