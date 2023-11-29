package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.text.Printable;
import gnu.text.SourceLocator;
import java.io.PrintWriter;
import java.io.Writer;

public abstract class Expression extends Procedure0 implements Printable, SourceLocator {
    protected static final int NEXT_AVAIL_FLAG = 2;
    public static final int VALIDATED = 1;
    public static final Expression[] noExpressions = new Expression[0];
    String filename;
    protected int flags;
    int position;

    public abstract void compile(Compilation compilation, Target target);

    /* access modifiers changed from: protected */
    public Expression deepCopy(IdentityHashTable identityHashTable) {
        return null;
    }

    public String getPublicId() {
        return null;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract boolean mustCompile();

    public abstract void print(OutPort outPort);

    public boolean side_effects() {
        return true;
    }

    public Object valueIfConstant() {
        return null;
    }

    /* access modifiers changed from: protected */
    public <R, D> void visitChildren(ExpVisitor<R, D> expVisitor, D d) {
    }

    public final Object eval(CallContext callContext) throws Throwable {
        int startFromContext = callContext.startFromContext();
        try {
            match0(callContext);
            return callContext.getFromContext(startFromContext);
        } catch (Throwable th) {
            callContext.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public final Object eval(Environment environment) throws Throwable {
        CallContext instance = CallContext.getInstance();
        Environment saveCurrent = Environment.setSaveCurrent(environment);
        try {
            return eval(instance);
        } finally {
            Environment.restoreCurrent(saveCurrent);
        }
    }

    public final int match0(CallContext callContext) {
        callContext.proc = this;
        callContext.pc = 0;
        return 0;
    }

    public final Object apply0() throws Throwable {
        CallContext instance = CallContext.getInstance();
        check0(instance);
        return instance.runUntilValue();
    }

    public void apply(CallContext callContext) throws Throwable {
        throw new RuntimeException("internal error - " + getClass() + ".eval called");
    }

    public final void print(Consumer consumer) {
        if (consumer instanceof OutPort) {
            print((OutPort) consumer);
        } else if (consumer instanceof PrintWriter) {
            OutPort outPort = new OutPort((Writer) (PrintWriter) consumer);
            print(outPort);
            outPort.close();
        } else {
            CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
            print((OutPort) charArrayOutPort);
            charArrayOutPort.close();
            charArrayOutPort.writeTo(consumer);
        }
    }

    public void printLineColumn(OutPort outPort) {
        int lineNumber = getLineNumber();
        if (lineNumber > 0) {
            outPort.print("line:");
            outPort.print(lineNumber);
            int columnNumber = getColumnNumber();
            if (columnNumber > 0) {
                outPort.print(':');
                outPort.print(columnNumber);
            }
            outPort.writeSpaceFill();
        }
    }

    public final void compileWithPosition(Compilation compilation, Target target) {
        int lineNumber = getLineNumber();
        if (lineNumber > 0) {
            compilation.getCode().putLineNumber(getFileName(), lineNumber);
            compileNotePosition(compilation, target, this);
            return;
        }
        compile(compilation, target);
    }

    public final void compileWithPosition(Compilation compilation, Target target, Expression expression) {
        int lineNumber = expression.getLineNumber();
        if (lineNumber > 0) {
            compilation.getCode().putLineNumber(expression.getFileName(), lineNumber);
            compileNotePosition(compilation, target, expression);
            return;
        }
        compile(compilation, target);
    }

    public final void compileNotePosition(Compilation compilation, Target target, Expression expression) {
        String fileName = compilation.getFileName();
        int lineNumber = compilation.getLineNumber();
        int columnNumber = compilation.getColumnNumber();
        compilation.setLine(expression);
        compile(compilation, target);
        compilation.setLine(fileName, lineNumber, columnNumber);
    }

    public final void compile(Compilation compilation, Type type) {
        compile(compilation, StackTarget.getInstance(type));
    }

    public final void compile(Compilation compilation, Declaration declaration) {
        compile(compilation, CheckedTarget.getInstance(declaration));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r4 = (gnu.expr.BeginExp) r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void compileButFirst(gnu.expr.Expression r4, gnu.expr.Compilation r5) {
        /*
            boolean r0 = r4 instanceof gnu.expr.BeginExp
            if (r0 == 0) goto L_0x0020
            gnu.expr.BeginExp r4 = (gnu.expr.BeginExp) r4
            int r0 = r4.length
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            gnu.expr.Expression[] r4 = r4.exps
            r1 = 0
            r1 = r4[r1]
            compileButFirst(r1, r5)
            r1 = 1
        L_0x0014:
            if (r1 >= r0) goto L_0x0020
            r2 = r4[r1]
            gnu.expr.Target r3 = gnu.expr.Target.Ignore
            r2.compileWithPosition(r5, r3)
            int r1 = r1 + 1
            goto L_0x0014
        L_0x0020:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.Expression.compileButFirst(gnu.expr.Expression, gnu.expr.Compilation):void");
    }

    public static Expression deepCopy(Expression expression, IdentityHashTable identityHashTable) {
        if (expression == null) {
            return null;
        }
        Object obj = identityHashTable.get(expression);
        if (obj != null) {
            return (Expression) obj;
        }
        Expression deepCopy = expression.deepCopy(identityHashTable);
        identityHashTable.put(expression, deepCopy);
        return deepCopy;
    }

    public static Expression[] deepCopy(Expression[] expressionArr, IdentityHashTable identityHashTable) {
        if (expressionArr == null) {
            return null;
        }
        int length = expressionArr.length;
        Expression[] expressionArr2 = new Expression[length];
        for (int i = 0; i < length; i++) {
            Expression expression = expressionArr[i];
            Expression deepCopy = deepCopy(expression, identityHashTable);
            if (deepCopy == null && expression != null) {
                return null;
            }
            expressionArr2[i] = deepCopy;
        }
        return expressionArr2;
    }

    protected static Expression deepCopy(Expression expression) {
        return deepCopy(expression, new IdentityHashTable());
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitExpression(this, d);
    }

    public Expression validateApply(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Declaration declaration) {
        applyExp.args = inlineCalls.visitExps(applyExp.args, null);
        return applyExp;
    }

    public static Expression makeWhile(Object obj, Object obj2, Compilation compilation) {
        LetExp letExp = new LetExp(r0);
        Declaration addDeclaration = letExp.addDeclaration((Object) "%do%loop");
        ReferenceExp referenceExp = new ReferenceExp(addDeclaration);
        Expression[] expressionArr = noExpressions;
        ApplyExp applyExp = new ApplyExp((Expression) referenceExp, expressionArr);
        LambdaExp lambdaExp = new LambdaExp();
        compilation.push((ScopeExp) lambdaExp);
        lambdaExp.body = new IfExp(compilation.parse(obj), new BeginExp(compilation.parse(obj2), applyExp), QuoteExp.voidExp);
        lambdaExp.setName("%do%loop");
        compilation.pop(lambdaExp);
        Expression[] expressionArr2 = {lambdaExp};
        addDeclaration.noteValue(lambdaExp);
        letExp.setBody(new ApplyExp((Expression) new ReferenceExp(addDeclaration), expressionArr));
        return letExp;
    }

    public final void setLocation(SourceLocator sourceLocator) {
        this.filename = sourceLocator.getFileName();
        setLine(sourceLocator.getLineNumber(), sourceLocator.getColumnNumber());
    }

    public final Expression setLine(Expression expression) {
        setLocation(expression);
        return this;
    }

    public final void setFile(String str) {
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.position = (i << 12) + i2;
    }

    public final void setLine(int i) {
        setLine(i, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public void setLine(Compilation compilation) {
        int lineNumber = compilation.getLineNumber();
        if (lineNumber > 0) {
            setFile(compilation.getFileName());
            setLine(lineNumber, compilation.getColumnNumber());
        }
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int i = this.position >> 12;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public final int getColumnNumber() {
        int i = this.position & 4095;
        if (i == 0) {
            return -1;
        }
        return i;
    }

    public Type getType() {
        return Type.pointer_type;
    }

    public boolean isSingleValue() {
        return OccurrenceType.itemCountIsOne(getType());
    }

    public void setFlag(boolean z, int i) {
        if (z) {
            this.flags |= i;
        } else {
            this.flags &= ~i;
        }
    }

    public void setFlag(int i) {
        this.flags = i | this.flags;
    }

    public int getFlags() {
        return this.flags;
    }

    public boolean getFlag(int i) {
        return (i & this.flags) != 0;
    }

    public String toString() {
        String name = getClass().getName();
        if (name.startsWith("gnu.expr.")) {
            name = name.substring(9);
        }
        return name + GetNamedPart.CAST_METHOD_NAME + Integer.toHexString(hashCode());
    }
}
