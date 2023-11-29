package gnu.commonlisp.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.reflect.FieldLocation;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;
import kawa.lang.Syntax;
import org.jose4j.jwk.RsaJsonWebKey;

public abstract class Lisp2 extends LispLanguage {
    public static final LList FALSE;
    public static final Symbol TRUE = Namespace.getDefault().getSymbol(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT);
    public static final Expression nilExpr;

    public boolean hasSeparateFunctionNamespace() {
        return true;
    }

    static {
        LList lList = LList.Empty;
        FALSE = lList;
        nilExpr = new QuoteExp(lList);
    }

    public boolean isTrue(Object obj) {
        return obj != FALSE;
    }

    public Object booleanObject(boolean z) {
        return z ? TRUE : FALSE;
    }

    public void emitPushBoolean(boolean z, CodeAttr codeAttr) {
        if (z) {
            codeAttr.emitGetStatic(ClassType.make("gnu.commonlisp.lang.Lisp2").getDeclaredField("TRUE"));
        } else {
            codeAttr.emitGetStatic(Compilation.scmListType.getDeclaredField("Empty"));
        }
    }

    public Object noValue() {
        return FALSE;
    }

    public boolean selfEvaluatingSymbol(Object obj) {
        return (obj instanceof Keyword) || obj == TRUE || obj == FALSE;
    }

    public Object getEnvPropertyFor(Field field, Object obj) {
        if (Compilation.typeProcedure.getReflectClass().isAssignableFrom(field.getType()) || (obj instanceof Syntax)) {
            return EnvironmentKey.FUNCTION;
        }
        return null;
    }

    public int getNamespaceOf(Declaration declaration) {
        if (declaration.isAlias()) {
            return 3;
        }
        return declaration.isProcedureDecl() ? 2 : 1;
    }

    public static Object asSymbol(String str) {
        if (str == "nil") {
            return FALSE;
        }
        return Environment.getCurrent().getSymbol(str);
    }

    /* access modifiers changed from: protected */
    public Symbol fromLangSymbol(Object obj) {
        if (obj == LList.Empty) {
            return this.environ.getSymbol("nil");
        }
        return super.fromLangSymbol(obj);
    }

    public static Object getString(String str) {
        return new FString(str);
    }

    public static Object getString(Symbol symbol) {
        return getString(symbol.getName());
    }

    /* access modifiers changed from: protected */
    public void defun(String str, Object obj) {
        this.environ.define(getSymbol(str), EnvironmentKey.FUNCTION, obj);
        if (obj instanceof Named) {
            Named named = (Named) obj;
            if (named.getName() == null) {
                named.setName(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void defun(Symbol symbol, Object obj) {
        this.environ.define(symbol, EnvironmentKey.FUNCTION, obj);
        if (obj instanceof Procedure) {
            Procedure procedure = (Procedure) obj;
            if (procedure.getSymbol() == null) {
                procedure.setSymbol(symbol);
            }
        }
    }

    private void defun(Procedure procedure) {
        defun(procedure.getName(), (Object) procedure);
    }

    /* access modifiers changed from: protected */
    public void importLocation(Location location) {
        Symbol keySymbol = ((NamedLocation) location).getKeySymbol();
        if (!this.environ.isBound(keySymbol, EnvironmentKey.FUNCTION)) {
            Location base = location.getBase();
            if (!(base instanceof FieldLocation) || !((FieldLocation) base).isProcedureOrSyntax()) {
                Object obj = base.get((Object) null);
                if (obj == null) {
                    return;
                }
                if ((obj instanceof Procedure) || (obj instanceof Syntax)) {
                    defun(keySymbol, obj);
                } else if (obj instanceof LangObjType) {
                    defun(keySymbol, (Object) ((LangObjType) obj).getConstructor());
                } else {
                    define(keySymbol.getName(), obj);
                }
            } else {
                this.environ.addLocation(keySymbol, EnvironmentKey.FUNCTION, base);
            }
        }
    }

    public ReadTable createReadTable() {
        Lisp2ReadTable lisp2ReadTable = new Lisp2ReadTable();
        lisp2ReadTable.initialize();
        lisp2ReadTable.setInitialColonIsKeyword(true);
        return lisp2ReadTable;
    }
}
