package gnu.kawa.functions;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BindingInitializer;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URLPath;

public class GetModuleClass extends ProcedureN implements Inlineable {
    private static Symbol CLASS_RESOURCE_NAME = Namespace.getDefaultSymbol("$class_resource_URL$");
    public static final GetModuleClass getModuleClass = new GetModuleClass();
    public static final GetModuleClass getModuleUri = new GetModuleClass();
    public static final GetModuleClass getModuleUriDummy = new GetModuleClass();
    static final Method maker;
    static final ClassType typeURLPath;

    static {
        ClassType make = ClassType.make("gnu.text.URLPath");
        typeURLPath = make;
        maker = make.getDeclaredMethod("classResourcePath", 1);
    }

    public Object applyN(Object[] objArr) {
        throw new Error("get-module-class must be inlined");
    }

    public int numArgs() {
        if (this == getModuleUriDummy) {
            return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
        }
        return 0;
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        if (this == getModuleUriDummy) {
            ReferenceExp referenceExp = (ReferenceExp) applyExp.getArgs()[0];
            referenceExp.compile(compilation, target);
            Declaration binding = referenceExp.getBinding();
            Expression value = binding.getValue();
            if (value != null) {
                BindingInitializer.create(binding, value, compilation);
                binding.setValue((Expression) null);
                return;
            }
            return;
        }
        compilation.loadClassRef(compilation.mainClass);
        if (this == getModuleUri) {
            compilation.getCode().emitInvoke(maker);
        }
        target.compileFromStack(compilation, applyExp.getType());
    }

    public Type getReturnType(Expression[] expressionArr) {
        return this == getModuleClass ? Type.javalangClassType : typeURLPath;
    }

    public static Expression getModuleClassURI(Compilation compilation) {
        Expression expression;
        Declaration lookup = compilation.mainLambda.lookup(CLASS_RESOURCE_NAME);
        if (lookup == null) {
            lookup = new Declaration((Object) CLASS_RESOURCE_NAME, (Type) typeURLPath);
            lookup.setFlag(536889344);
            if (compilation.immediate) {
                Path sourceAbsPath = compilation.minfo.getSourceAbsPath();
                if (sourceAbsPath == null) {
                    sourceAbsPath = Path.currentPath();
                }
                if (!(sourceAbsPath instanceof URLPath)) {
                    sourceAbsPath = URLPath.valueOf(sourceAbsPath.toURL());
                }
                expression = QuoteExp.getInstance(sourceAbsPath);
            } else {
                ApplyExp applyExp = new ApplyExp((Procedure) getModuleClass, Expression.noExpressions);
                expression = new ApplyExp(maker, applyExp);
            }
            lookup.setValue(expression);
            compilation.mainLambda.add((Declaration) null, lookup);
        }
        ReferenceExp referenceExp = new ReferenceExp(lookup);
        if (compilation.immediate) {
            return referenceExp;
        }
        return new ApplyExp((Procedure) getModuleUriDummy, referenceExp);
    }
}
