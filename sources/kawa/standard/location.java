package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.ProcLocation;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class location extends Syntax {
    public static final location location;
    private static ClassType thisType = ClassType.make("kawa.standard.location");

    static {
        location location2 = new location();
        location = location2;
        location2.setName("location");
    }

    public Expression rewrite(Object obj, Translator translator) {
        if (!(obj instanceof Pair)) {
            return translator.syntaxError("missing argument to location");
        }
        Pair pair = (Pair) obj;
        if (pair.getCdr() != LList.Empty) {
            return translator.syntaxError("extra arguments to location");
        }
        return Invoke.makeInvokeStatic(thisType, "makeLocationProc", new Expression[]{rewrite(translator.rewrite(pair.getCar()), translator)});
    }

    public static Expression rewrite(Expression expression, Translator translator) {
        if (expression instanceof ReferenceExp) {
            ReferenceExp referenceExp = (ReferenceExp) expression;
            referenceExp.setDontDereference(true);
            Declaration binding = referenceExp.getBinding();
            if (binding != null) {
                binding.maybeIndirectBinding(translator);
                Declaration followAliases = Declaration.followAliases(binding);
                followAliases.setCanRead(true);
                followAliases.setCanWrite(true);
            }
            return referenceExp;
        } else if (!(expression instanceof ApplyExp)) {
            return translator.syntaxError("invalid argument to location");
        } else {
            ApplyExp applyExp = (ApplyExp) expression;
            int length = applyExp.getArgs().length + 1;
            Expression[] expressionArr = new Expression[length];
            expressionArr[0] = applyExp.getFunction();
            System.arraycopy(applyExp.getArgs(), 0, expressionArr, 1, length - 1);
            return Invoke.makeInvokeStatic(thisType, "makeProcLocation", expressionArr);
        }
    }

    public static Location makeProcLocation$V(Procedure procedure, Object[] objArr) {
        int length = objArr.length;
        if ((procedure instanceof ApplyToArgs) && length > 0) {
            Procedure procedure2 = objArr[0];
            if (procedure2 instanceof Procedure) {
                Procedure procedure3 = procedure2;
                if ((procedure3 instanceof LocationProc) && length == 1) {
                    return ((LocationProc) procedure3).getLocation();
                }
                int i = length - 1;
                Object[] objArr2 = new Object[i];
                System.arraycopy(objArr, 1, objArr2, 0, i);
                return new ProcLocation(procedure3, objArr2);
            }
        }
        if (!(procedure instanceof LocationProc) || length != 0) {
            return new ProcLocation(procedure, objArr);
        }
        return ((LocationProc) procedure).getLocation();
    }

    public static Procedure makeLocationProc(Location location2) {
        return new LocationProc(location2);
    }
}
