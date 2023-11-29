package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class BindingInitializer extends Initializer {
    static final ClassType typeThreadLocation = ClassType.make("gnu.mapping.ThreadLocation");
    Declaration decl;
    Expression value;

    public static void create(Declaration declaration, Expression expression, Compilation compilation) {
        BindingInitializer bindingInitializer = new BindingInitializer(declaration, expression);
        if (declaration.field == null ? !declaration.isStatic() : !declaration.field.getStaticFlag()) {
            bindingInitializer.next = compilation.mainLambda.initChain;
            compilation.mainLambda.initChain = bindingInitializer;
            return;
        }
        bindingInitializer.next = compilation.clinitChain;
        compilation.clinitChain = bindingInitializer;
    }

    public BindingInitializer(Declaration declaration, Expression expression) {
        this.decl = declaration;
        this.value = expression;
        this.field = declaration.field;
    }

    public void emit(Compilation compilation) {
        Object value2;
        if (this.decl.ignorable()) {
            Expression expression = this.value;
            if (expression != null) {
                expression.compile(compilation, Target.Ignore);
                return;
            }
            return;
        }
        CodeAttr code = compilation.getCode();
        Expression expression2 = this.value;
        if (!(expression2 instanceof QuoteExp) || (value2 = ((QuoteExp) expression2).getValue()) == null || (value2 instanceof String) || compilation.litTable.findLiteral(value2).field != this.field) {
            int lineNumber = this.decl.getLineNumber();
            SourceMessages messages = compilation.getMessages();
            SourceLocator swapSourceLocator = messages.swapSourceLocator(this.decl);
            if (lineNumber > 0) {
                code.putLineNumber(this.decl.getFileName(), lineNumber);
            }
            if (this.field != null && !this.field.getStaticFlag()) {
                code.emitPushThis();
            }
            if (this.value == null) {
                Object obj = (!compilation.getLanguage().hasSeparateFunctionNamespace() || !this.decl.isProcedureDecl()) ? null : EnvironmentKey.FUNCTION;
                Object symbol = this.decl.getSymbol();
                if (this.decl.getFlag(268500992)) {
                    if (symbol instanceof String) {
                        symbol = Namespace.EmptyNamespace.getSymbol((String) symbol);
                    }
                    compilation.compileConstant(symbol, Target.pushObject);
                    if (obj == null) {
                        code.emitPushNull();
                    } else {
                        compilation.compileConstant(obj, Target.pushObject);
                    }
                    code.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("getInstance", 2));
                } else if (this.decl.isFluid()) {
                    Type[] typeArr = new Type[1];
                    typeArr[0] = symbol instanceof Symbol ? Compilation.typeSymbol : Type.toStringType;
                    compilation.compileConstant(symbol, Target.pushObject);
                    code.emitInvokeStatic(typeThreadLocation.getDeclaredMethod("makeAnonymous", typeArr));
                } else {
                    compilation.compileConstant(symbol, Target.pushObject);
                    code.emitInvokeStatic(makeLocationMethod(symbol));
                }
            } else {
                this.value.compileWithPosition(compilation, StackTarget.getInstance(this.field == null ? this.decl.getType() : this.field.getType()));
            }
            if (this.field == null) {
                Variable variable = this.decl.getVariable();
                if (variable == null) {
                    variable = this.decl.allocateVariable(code);
                }
                code.emitStore(variable);
            } else if (this.field.getStaticFlag()) {
                code.emitPutStatic(this.field);
            } else {
                code.emitPutField(this.field);
            }
            messages.swapSourceLocator(swapSourceLocator);
        }
    }

    public static Method makeLocationMethod(Object obj) {
        Type[] typeArr = new Type[1];
        if (obj instanceof Symbol) {
            typeArr[0] = Compilation.typeSymbol;
        } else {
            typeArr[0] = Type.javalangStringType;
        }
        return Compilation.typeLocation.getDeclaredMethod("make", typeArr);
    }
}
