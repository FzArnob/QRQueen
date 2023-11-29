package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Target;
import gnu.kawa.functions.Convert;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.mapping.Values;

public class CastAs extends Convert {
    public static final CastAs castAs = new CastAs();

    public CastAs() {
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyCastAs");
    }

    public Object apply2(Object obj, Object obj2) {
        Type type = (Type) obj;
        if (type instanceof XDataType) {
            return ((XDataType) type).cast(obj2);
        }
        if (type instanceof OccurrenceType) {
            OccurrenceType occurrenceType = (OccurrenceType) type;
            Type base = occurrenceType.getBase();
            if (base instanceof XDataType) {
                int minOccurs = occurrenceType.minOccurs();
                int maxOccurs = occurrenceType.maxOccurs();
                if (obj2 instanceof Values) {
                    if (obj2 == Values.empty && minOccurs == 0) {
                        return obj2;
                    }
                    Values values = (Values) obj2;
                    int startPos = values.startPos();
                    int i = 0;
                    Values values2 = new Values();
                    while (true) {
                        startPos = values.nextPos(startPos);
                        if (startPos == 0) {
                            break;
                        }
                        values2.writeObject(((XDataType) base).cast(values.getPosPrevious(startPos)));
                        i++;
                    }
                    if (i >= minOccurs && (maxOccurs < 0 || i <= maxOccurs)) {
                        return values2.canonicalize();
                    }
                } else if (minOccurs <= 1 && maxOccurs != 0) {
                    return ((XDataType) base).cast(obj2);
                }
                throw new ClassCastException("cannot cast " + obj2 + " to " + obj);
            }
        }
        return super.apply2(obj, obj2);
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp.compile(applyExp, compilation, target);
    }
}
