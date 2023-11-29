package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ArrayGet;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.Numeric;
import gnu.text.Char;
import java.io.Externalizable;

public class CompilationHelpers {
    public static final Declaration setterDecl;
    static final Field setterField;
    static final ClassType setterType;
    static final ClassType typeList = ClassType.make("java.util.List");

    private static boolean nonNumeric(Expression expression) {
        if (!(expression instanceof QuoteExp)) {
            return false;
        }
        Object value = ((QuoteExp) expression).getValue();
        if ((value instanceof Numeric) || (value instanceof Char) || (value instanceof Symbol)) {
            return false;
        }
        return true;
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.functions.Setter");
        setterType = make;
        Field declaredField = make.getDeclaredField("setter");
        setterField = declaredField;
        Declaration declaration = new Declaration((Object) "setter", declaredField);
        setterDecl = declaration;
        declaration.noteValue(new QuoteExp(Setter.setter));
    }

    public static Expression validateApplyToArgs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Expression[] args = applyExp.getArgs();
        int length = args.length - 1;
        if (length >= 0) {
            Expression expression = args[0];
            ApplyExp applyExp2 = null;
            if (!expression.getFlag(1)) {
                if (expression instanceof LambdaExp) {
                    Expression[] expressionArr = new Expression[length];
                    System.arraycopy(args, 1, expressionArr, 0, length);
                    return inlineCalls.visit(new ApplyExp(expression, expressionArr).setLine((Expression) applyExp), type);
                }
                expression = inlineCalls.visit(expression, (Type) null);
                args[0] = expression;
            }
            Type realType = expression.getType().getRealType();
            Compilation compilation = inlineCalls.getCompilation();
            Language language = compilation.getLanguage();
            if (realType.isSubtype(Compilation.typeProcedure)) {
                Expression[] expressionArr2 = new Expression[length];
                System.arraycopy(args, 1, expressionArr2, 0, length);
                ApplyExp applyExp3 = new ApplyExp(expression, expressionArr2);
                applyExp3.setLine((Expression) applyExp);
                return expression.validateApply(applyExp3, inlineCalls, type, (Declaration) null);
            }
            if (CompileReflect.checkKnownClass(realType, compilation) >= 0) {
                if (realType.isSubtype(Compilation.typeType) || language.getTypeFor(expression, false) != null) {
                    applyExp2 = new ApplyExp((Procedure) Invoke.make, args);
                } else if (realType instanceof ArrayType) {
                    applyExp2 = new ApplyExp((Procedure) new ArrayGet(((ArrayType) realType).getComponentType()), args);
                } else if (realType instanceof ClassType) {
                    ClassType classType = (ClassType) realType;
                    if (classType.isSubclass(typeList) && length == 1) {
                        applyExp2 = new ApplyExp(classType.getMethod("get", new Type[]{Type.intType}), args);
                    }
                }
            }
            if (applyExp2 != null) {
                applyExp2.setLine((Expression) applyExp);
                return inlineCalls.visitApplyOnly(applyExp2, type);
            }
        }
        applyExp.visitArgs(inlineCalls);
        return applyExp;
    }

    public static Expression validateSetter(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Declaration binding;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length != 1) {
            return applyExp;
        }
        Expression expression = args[0];
        Type type2 = expression.getType();
        if (type2 instanceof ArrayType) {
            return new SetArrayExp(expression, (ArrayType) type2);
        }
        if (!(type2 instanceof ClassType) || !((ClassType) type2).isSubclass(typeList)) {
            if ((expression instanceof ReferenceExp) && (binding = ((ReferenceExp) expression).getBinding()) != null) {
                expression = binding.getValue();
            }
            if (!(expression instanceof QuoteExp)) {
                return applyExp;
            }
            Object value = ((QuoteExp) expression).getValue();
            if (!(value instanceof Procedure)) {
                return applyExp;
            }
            Procedure setter = ((Procedure) value).getSetter();
            if (!(setter instanceof Procedure)) {
                return applyExp;
            }
            if (setter instanceof Externalizable) {
                return new QuoteExp(setter);
            }
            Procedure procedure2 = setter;
            Declaration declaration = Declaration.getDeclaration(setter);
            return declaration != null ? new ReferenceExp(declaration) : applyExp;
        } else if (applyExp instanceof SetListExp) {
            return applyExp;
        } else {
            return new SetListExp(applyExp.getFunction(), args);
        }
    }

    public static Expression validateIsEqv(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (nonNumeric(args[0]) || nonNumeric(args[1])) {
            return new ApplyExp((Procedure) ((IsEqv) procedure).isEq, args);
        }
        return applyExp;
    }
}
