package gnu.kawa.functions;

import gnu.bytecode.Access;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasNamedParts;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.lang.Translator;
import org.slf4j.Marker;

public class CompileNamedPart {
    static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");

    public static Expression validateGetNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Declaration declaration;
        Object constantValue;
        ApplyExp applyExp2 = applyExp;
        InlineCalls inlineCalls2 = inlineCalls;
        Type type2 = type;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length == 2 && (args[1] instanceof QuoteExp) && (applyExp2 instanceof GetNamedExp)) {
            Expression expression = args[0];
            ClassType classType = null;
            if (expression instanceof ReferenceExp) {
                ReferenceExp referenceExp = (ReferenceExp) expression;
                if (Marker.ANY_MARKER.equals(referenceExp.getName())) {
                    return makeGetNamedInstancePartExp(args[1]);
                }
                declaration = referenceExp.getBinding();
            } else {
                declaration = null;
            }
            String obj = ((QuoteExp) args[1]).getValue().toString();
            Type type3 = expression.getType();
            QuoteExp quoteExp = QuoteExp.nullExp;
            Compilation compilation = inlineCalls.getCompilation();
            Language language = compilation.getLanguage();
            Type typeFor = language.getTypeFor(expression, false);
            if (compilation != null) {
                classType = compilation.curClass != null ? compilation.curClass : compilation.mainClass;
            }
            GetNamedExp getNamedExp = (GetNamedExp) applyExp2;
            if (typeFor != null) {
                if (obj.equals(GetNamedPart.CLASSTYPE_FOR)) {
                    return new QuoteExp(typeFor);
                }
                if (typeFor instanceof ObjectType) {
                    if (obj.equals("new")) {
                        return getNamedExp.setProcedureKind('N');
                    }
                    if (obj.equals(GetNamedPart.INSTANCEOF_METHOD_NAME)) {
                        return getNamedExp.setProcedureKind(Access.INNERCLASS_CONTEXT);
                    }
                    if (obj.equals(GetNamedPart.CAST_METHOD_NAME)) {
                        return getNamedExp.setProcedureKind(Access.CLASS_CONTEXT);
                    }
                }
            }
            if (typeFor instanceof ObjectType) {
                if (obj.length() > 1 && obj.charAt(0) == '.') {
                    return new QuoteExp(new NamedPart(typeFor, obj, 'D'));
                }
                if (CompileReflect.checkKnownClass(typeFor, compilation) < 0) {
                    return applyExp2;
                }
                PrimProcedure[] methods = ClassMethods.getMethods((ObjectType) typeFor, Compilation.mangleName(obj), 0, classType, language);
                if (methods == null || methods.length <= 0) {
                    ApplyExp applyExp3 = new ApplyExp((Procedure) SlotGet.staticField, args);
                    applyExp3.setLine((Expression) applyExp2);
                    return inlineCalls2.visitApplyOnly(applyExp3, type2);
                }
                getNamedExp.methods = methods;
                return getNamedExp.setProcedureKind('S');
            } else if (!type3.isSubtype(Compilation.typeClassType) && !type3.isSubtype(Type.javalangClassType)) {
                if (type3 instanceof ObjectType) {
                    ObjectType objectType = (ObjectType) type3;
                    PrimProcedure[] methods2 = ClassMethods.getMethods(objectType, Compilation.mangleName(obj), 'V', classType, language);
                    if (methods2 == null || methods2.length <= 0) {
                        ClassType classType2 = typeHasNamedParts;
                        if (type3.isSubtype(classType2)) {
                            if (!(declaration == null || (constantValue = Declaration.followAliases(declaration).getConstantValue()) == null)) {
                                HasNamedParts hasNamedParts = (HasNamedParts) constantValue;
                                if (hasNamedParts.isConstant(obj)) {
                                    return QuoteExp.getInstance(hasNamedParts.get(obj));
                                }
                            }
                            return new ApplyExp(classType2.getDeclaredMethod("get", 1), args[0], QuoteExp.getInstance(obj)).setLine((Expression) applyExp2);
                        } else if (SlotGet.lookupMember(objectType, obj, classType) != null || (obj.equals("length") && (type3 instanceof ArrayType))) {
                            ApplyExp applyExp4 = new ApplyExp((Procedure) SlotGet.field, args);
                            applyExp4.setLine((Expression) applyExp2);
                            return inlineCalls2.visitApplyOnly(applyExp4, type2);
                        }
                    } else {
                        getNamedExp.methods = methods2;
                        return getNamedExp.setProcedureKind(Access.METHOD_CONTEXT);
                    }
                }
                if (compilation.warnUnknownMember()) {
                    compilation.error('w', "no known slot '" + obj + "' in " + type3.getName());
                }
            }
        }
        return applyExp2;
    }

    public static Expression validateSetNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ClassType classType;
        ApplyExp applyExp2;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        if (args.length == 3) {
            Expression expression = args[1];
            if (expression instanceof QuoteExp) {
                Expression expression2 = args[0];
                String obj = ((QuoteExp) expression).getValue().toString();
                Type type2 = expression2.getType();
                Compilation compilation = inlineCalls.getCompilation();
                Type typeFor = compilation.getLanguage().getTypeFor(expression2);
                if (compilation == null) {
                    classType = null;
                } else {
                    classType = compilation.curClass != null ? compilation.curClass : compilation.mainClass;
                }
                if (typeFor instanceof ClassType) {
                    applyExp2 = new ApplyExp((Procedure) SlotSet.set$Mnstatic$Mnfield$Ex, args);
                } else {
                    applyExp2 = (!(type2 instanceof ClassType) || SlotSet.lookupMember((ClassType) type2, obj, classType) == null) ? applyExp : new ApplyExp((Procedure) SlotSet.set$Mnfield$Ex, args);
                }
                if (applyExp2 != applyExp) {
                    applyExp2.setLine((Expression) applyExp);
                }
                applyExp2.setType(Type.voidType);
                return applyExp2;
            }
        }
        return applyExp;
    }

    public static Expression makeExp(Expression expression, Expression expression2) {
        String combineName = combineName(expression, expression2);
        Environment current = Environment.getCurrent();
        if (combineName != null) {
            Symbol symbol = Namespace.EmptyNamespace.getSymbol(combineName);
            Declaration lookup = ((Translator) Compilation.getCurrent()).lexical.lookup((Object) symbol, false);
            if (!Declaration.isUnknown(lookup)) {
                return new ReferenceExp(lookup);
            }
            if (symbol != null && current.isBound(symbol, (Object) null)) {
                return new ReferenceExp((Object) combineName);
            }
        }
        if (expression instanceof ReferenceExp) {
            ReferenceExp referenceExp = (ReferenceExp) expression;
            if (referenceExp.isUnknown()) {
                Object symbol2 = referenceExp.getSymbol();
                if (current.get((EnvironmentKey) symbol2 instanceof Symbol ? (Symbol) symbol2 : current.getSymbol(symbol2.toString()), (Object) null) == null) {
                    try {
                        expression = QuoteExp.getInstance(Type.make(ClassType.getContextClass(referenceExp.getName())));
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        GetNamedExp getNamedExp = new GetNamedExp(new Expression[]{expression, expression2});
        getNamedExp.combinedName = combineName;
        return getNamedExp;
    }

    public static String combineName(Expression expression, Expression expression2) {
        String str;
        Object valueIfConstant = expression2.valueIfConstant();
        if (!(valueIfConstant instanceof SimpleSymbol)) {
            return null;
        }
        if ((!(expression instanceof ReferenceExp) || (str = ((ReferenceExp) expression).getSimpleName()) == null) && (!(expression instanceof GetNamedExp) || (str = ((GetNamedExp) expression).combinedName) == null)) {
            return null;
        }
        return (str + ':' + valueIfConstant).intern();
    }

    public static Expression makeExp(Expression expression, String str) {
        return makeExp(expression, (Expression) new QuoteExp(str));
    }

    public static Expression makeExp(Type type, String str) {
        return makeExp((Expression) new QuoteExp(type), (Expression) new QuoteExp(str));
    }

    public static Expression validateNamedPart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        SlotGet slotGet;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        NamedPart namedPart = (NamedPart) procedure;
        if (namedPart.kind != 'D') {
            return applyExp;
        }
        Expression[] expressionArr = new Expression[2];
        expressionArr[1] = QuoteExp.getInstance(namedPart.member.toString().substring(1));
        if (args.length > 0) {
            expressionArr[0] = Compilation.makeCoercion(args[0], (Expression) new QuoteExp(namedPart.container));
            slotGet = SlotGet.field;
        } else {
            expressionArr[0] = QuoteExp.getInstance(namedPart.container);
            slotGet = SlotGet.staticField;
        }
        ApplyExp applyExp2 = new ApplyExp((Procedure) slotGet, expressionArr);
        applyExp2.setLine((Expression) applyExp);
        return inlineCalls.visitApplyOnly(applyExp2, type);
    }

    public static Expression validateNamedPartSetter(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        SlotSet slotSet;
        applyExp.visitArgs(inlineCalls);
        NamedPart namedPart = (NamedPart) ((NamedPartSetter) procedure).getGetter();
        if (namedPart.kind != 'D') {
            return applyExp;
        }
        Expression[] expressionArr = new Expression[3];
        expressionArr[1] = QuoteExp.getInstance(namedPart.member.toString().substring(1));
        expressionArr[2] = applyExp.getArgs()[0];
        if (applyExp.getArgCount() == 1) {
            expressionArr[0] = QuoteExp.getInstance(namedPart.container);
            slotSet = SlotSet.set$Mnstatic$Mnfield$Ex;
        } else if (applyExp.getArgCount() != 2) {
            return applyExp;
        } else {
            expressionArr[0] = Compilation.makeCoercion(applyExp.getArgs()[0], (Expression) new QuoteExp(namedPart.container));
            slotSet = SlotSet.set$Mnfield$Ex;
        }
        ApplyExp applyExp2 = new ApplyExp((Procedure) slotSet, expressionArr);
        applyExp2.setLine((Expression) applyExp);
        return inlineCalls.visitApplyOnly(applyExp2, type);
    }

    public static Expression makeGetNamedInstancePartExp(Expression expression) {
        if (expression instanceof QuoteExp) {
            Object value = ((QuoteExp) expression).getValue();
            if (value instanceof SimpleSymbol) {
                return QuoteExp.getInstance(new GetNamedInstancePart(value.toString()));
            }
        }
        return new ApplyExp((Procedure) Invoke.make, new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart")), expression);
    }

    public static Expression validateGetNamedInstancePart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Procedure procedure2;
        Expression[] expressionArr;
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        GetNamedInstancePart getNamedInstancePart = (GetNamedInstancePart) procedure;
        if (getNamedInstancePart.isField) {
            expressionArr = new Expression[]{args[0], new QuoteExp(getNamedInstancePart.pname)};
            procedure2 = SlotGet.field;
        } else {
            int length = args.length;
            Expression[] expressionArr2 = new Expression[(length + 1)];
            expressionArr2[0] = args[0];
            expressionArr2[1] = new QuoteExp(getNamedInstancePart.pname);
            System.arraycopy(args, 1, expressionArr2, 2, length - 1);
            procedure2 = Invoke.invoke;
            expressionArr = expressionArr2;
        }
        return inlineCalls.visitApplyOnly(new ApplyExp(procedure2, expressionArr), type);
    }

    public static Expression validateSetNamedInstancePart(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        applyExp.visitArgs(inlineCalls);
        Expression[] args = applyExp.getArgs();
        return inlineCalls.visitApplyOnly(new ApplyExp((Procedure) SlotSet.set$Mnfield$Ex, args[0], new QuoteExp(((SetNamedInstancePart) procedure).pname), args[1]), type);
    }
}
