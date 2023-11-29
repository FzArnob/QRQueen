package gnu.kawa.functions;

import gnu.expr.Declaration;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class Map extends ProcedureN {
    final Declaration applyFieldDecl;
    final ApplyToArgs applyToArgs;
    boolean collect;
    final IsEq isEq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Map(boolean z, ApplyToArgs applyToArgs2, Declaration declaration, IsEq isEq2) {
        super(z ? "map" : "for-each");
        this.collect = z;
        this.applyToArgs = applyToArgs2;
        this.applyFieldDecl = declaration;
        this.isEq = isEq2;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyMap");
    }

    public static Object map1(Procedure procedure, Object obj) throws Throwable {
        LList lList = LList.Empty;
        Pair pair = null;
        while (obj != LList.Empty) {
            Pair pair2 = (Pair) obj;
            Pair pair3 = new Pair(procedure.apply1(pair2.getCar()), LList.Empty);
            if (pair == null) {
                lList = pair3;
            } else {
                pair.setCdr(pair3);
            }
            obj = pair2.getCdr();
            pair = pair3;
        }
        return lList;
    }

    public static void forEach1(Procedure procedure, Object obj) throws Throwable {
        while (obj != LList.Empty) {
            Pair pair = (Pair) obj;
            procedure.apply1(pair.getCar());
            obj = pair.getCdr();
        }
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        if (obj instanceof Procedure) {
            Procedure procedure = (Procedure) obj;
            if (this.collect) {
                return map1(procedure, obj2);
            }
            forEach1(procedure, obj2);
            return Values.empty;
        }
        return applyN(new Object[]{obj, obj2});
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Object obj;
        Procedure procedure;
        Object[] objArr2;
        int i = 1;
        int length = objArr.length - 1;
        if (length == 1) {
            Procedure procedure2 = objArr[0];
            if (procedure2 instanceof Procedure) {
                Procedure procedure3 = procedure2;
                Procedure procedure4 = procedure3;
                if (this.collect) {
                    return map1(procedure3, objArr[1]);
                }
                forEach1(procedure3, objArr[1]);
                return Values.empty;
            }
        }
        Pair pair = null;
        if (this.collect) {
            obj = LList.Empty;
        } else {
            obj = Values.empty;
        }
        Object[] objArr3 = new Object[length];
        System.arraycopy(objArr, 1, objArr3, 0, length);
        Procedure procedure5 = objArr[0];
        if (procedure5 instanceof Procedure) {
            procedure = procedure5;
            objArr2 = new Object[length];
            i = 0;
        } else {
            objArr2 = new Object[(length + 1)];
            objArr2[0] = procedure5;
            procedure = this.applyToArgs;
        }
        while (true) {
            for (int i2 = 0; i2 < length; i2++) {
                Object obj2 = objArr3[i2];
                if (obj2 == LList.Empty) {
                    return obj;
                }
                Pair pair2 = (Pair) obj2;
                objArr2[i + i2] = pair2.getCar();
                objArr3[i2] = pair2.getCdr();
            }
            Object applyN = procedure.applyN(objArr2);
            if (this.collect) {
                Object pair3 = new Pair(applyN, LList.Empty);
                if (pair == null) {
                    obj = pair3;
                } else {
                    pair.setCdr(pair3);
                }
                pair = pair3;
            }
        }
    }
}
