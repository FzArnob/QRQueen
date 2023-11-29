package gnu.ecmascript;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import kawa.standard.Scheme;

public class Parser {
    public static final Expression[] emptyArgs = new Expression[0];
    static Expression emptyStatement = new QuoteExp(Values.empty);
    public static Expression eofExpr = new QuoteExp(Sequence.eofValue);
    public int errors;
    Lexer lexer;
    InPort port;
    Object previous_token;
    Object token;

    public Expression makePropertyAccessor(Expression expression, Expression expression2) {
        return null;
    }

    public Parser(InPort inPort) {
        this.port = inPort;
        this.lexer = new Lexer(inPort);
    }

    public Expression parseConditionalExpression() throws IOException, SyntaxException {
        Expression parseBinaryExpression = parseBinaryExpression(1);
        if (peekToken() != Lexer.condToken) {
            return parseBinaryExpression;
        }
        skipToken();
        Expression parseAssignmentExpression = parseAssignmentExpression();
        if (getToken() != Lexer.colonToken) {
            return syntaxError("expected ':' in conditional expression");
        }
        return new IfExp(parseBinaryExpression, parseAssignmentExpression, parseAssignmentExpression());
    }

    public Expression parseAssignmentExpression() throws IOException, SyntaxException {
        Expression parseConditionalExpression = parseConditionalExpression();
        Object peekToken = peekToken();
        if (peekToken == Lexer.equalToken) {
            skipToken();
            Expression parseAssignmentExpression = parseAssignmentExpression();
            if (!(parseConditionalExpression instanceof ReferenceExp)) {
                return syntaxError("unmplemented non-symbol ihs in assignment");
            }
            SetExp setExp = new SetExp((Object) ((ReferenceExp) parseConditionalExpression).getName(), parseAssignmentExpression);
            setExp.setDefining(true);
            return setExp;
        } else if (!(peekToken instanceof Reserved)) {
            return parseConditionalExpression;
        } else {
            Reserved reserved = (Reserved) peekToken;
            if (!reserved.isAssignmentOp()) {
                return parseConditionalExpression;
            }
            skipToken();
            return new ApplyExp((Expression) new QuoteExp(reserved.proc), parseConditionalExpression, parseAssignmentExpression());
        }
    }

    public Expression parseExpression() throws IOException, SyntaxException {
        int i;
        Expression[] expressionArr = null;
        int i2 = 0;
        while (true) {
            Expression parseAssignmentExpression = parseAssignmentExpression();
            boolean z = peekToken() != Lexer.commaToken;
            if (expressionArr != null) {
                int length = expressionArr.length;
                if (!z ? length <= i2 : length != i2 + 1) {
                    if (z) {
                        i = i2 + 1;
                    } else {
                        i = expressionArr.length * 2;
                    }
                    Expression[] expressionArr2 = new Expression[i];
                    System.arraycopy(expressionArr, 0, expressionArr2, 0, i2);
                    expressionArr = expressionArr2;
                }
            } else if (z) {
                return parseAssignmentExpression;
            } else {
                expressionArr = new Expression[2];
            }
            int i3 = i2 + 1;
            expressionArr[i2] = parseAssignmentExpression;
            if (z) {
                return new BeginExp(expressionArr);
            }
            skipToken();
            i2 = i3;
        }
    }

    public Object peekTokenOrLine() throws IOException, SyntaxException {
        if (this.token == null) {
            this.token = this.lexer.getToken();
        }
        return this.token;
    }

    public Object peekToken() throws IOException, SyntaxException {
        if (this.token == null) {
            this.token = this.lexer.getToken();
        }
        while (this.token == Lexer.eolToken) {
            skipToken();
            this.token = this.lexer.getToken();
        }
        return this.token;
    }

    public Object getToken() throws IOException, SyntaxException {
        Object peekToken = peekToken();
        skipToken();
        return peekToken;
    }

    public final void skipToken() {
        if (this.token != Lexer.eofToken) {
            this.previous_token = this.token;
            this.token = null;
        }
    }

    public void getSemicolon() throws IOException, SyntaxException {
        Object peekToken = peekToken();
        this.token = peekToken;
        if (peekToken == Lexer.semicolonToken) {
            skipToken();
        } else if (this.token != Lexer.rbraceToken && this.token != Lexer.eofToken && this.previous_token != Lexer.eolToken) {
            syntaxError("missing ';' after expression");
        }
    }

    public Expression parsePrimaryExpression() throws IOException, SyntaxException {
        Object token2 = getToken();
        if (token2 instanceof QuoteExp) {
            return (QuoteExp) token2;
        }
        if (token2 instanceof String) {
            return new ReferenceExp((Object) (String) token2);
        }
        if (token2 == Lexer.lparenToken) {
            Expression parseExpression = parseExpression();
            Object token3 = getToken();
            if (token3 == Lexer.rparenToken) {
                return parseExpression;
            }
            return syntaxError("expected ')' - got:" + token3);
        }
        return syntaxError("unexpected token: " + token2);
    }

    public Expression[] parseArguments() throws IOException, SyntaxException {
        skipToken();
        if (peekToken() == Lexer.rparenToken) {
            skipToken();
            return emptyArgs;
        }
        Vector vector = new Vector(10);
        while (true) {
            vector.addElement(parseAssignmentExpression());
            Object token2 = getToken();
            if (token2 == Lexer.rparenToken) {
                Expression[] expressionArr = new Expression[vector.size()];
                vector.copyInto(expressionArr);
                return expressionArr;
            } else if (token2 != Lexer.commaToken) {
                syntaxError("invalid token '" + token2 + "' in argument list");
            }
        }
    }

    public Expression makeNewExpression(Expression expression, Expression[] expressionArr) {
        if (expressionArr == null) {
            expressionArr = emptyArgs;
        }
        return new ApplyExp((Expression) null, expressionArr);
    }

    public Expression makeCallExpression(Expression expression, Expression[] expressionArr) {
        return new ApplyExp(expression, expressionArr);
    }

    public String getIdentifier() throws IOException, SyntaxException {
        Object token2 = getToken();
        if (token2 instanceof String) {
            return (String) token2;
        }
        syntaxError("missing identifier");
        return "??";
    }

    public Expression parseLeftHandSideExpression() throws IOException, SyntaxException {
        int i = 0;
        while (peekToken() == Lexer.newToken) {
            i++;
            skipToken();
        }
        Expression parsePrimaryExpression = parsePrimaryExpression();
        while (true) {
            Object peekToken = peekToken();
            if (peekToken == Lexer.dotToken) {
                skipToken();
                parsePrimaryExpression = makePropertyAccessor(parsePrimaryExpression, new QuoteExp(getIdentifier()));
            } else if (peekToken == Lexer.lbracketToken) {
                skipToken();
                Expression parseExpression = parseExpression();
                Object token2 = getToken();
                if (token2 != Lexer.rbracketToken) {
                    return syntaxError("expected ']' - got:" + token2);
                }
                parsePrimaryExpression = makePropertyAccessor(parsePrimaryExpression, parseExpression);
            } else if (peekToken == Lexer.lparenToken) {
                Expression[] parseArguments = parseArguments();
                System.err.println("after parseArgs:" + peekToken());
                if (i > 0) {
                    parsePrimaryExpression = makeNewExpression(parsePrimaryExpression, parseArguments);
                    i--;
                } else {
                    parsePrimaryExpression = makeCallExpression(parsePrimaryExpression, parseArguments);
                }
            } else {
                while (i > 0) {
                    parsePrimaryExpression = makeNewExpression(parsePrimaryExpression, (Expression[]) null);
                    i--;
                }
                return parsePrimaryExpression;
            }
        }
    }

    public Expression parsePostfixExpression() throws IOException, SyntaxException {
        Expression parseLeftHandSideExpression = parseLeftHandSideExpression();
        Object peekTokenOrLine = peekTokenOrLine();
        if (peekTokenOrLine != Reserved.opPlusPlus && peekTokenOrLine != Reserved.opMinusMinus) {
            return parseLeftHandSideExpression;
        }
        skipToken();
        return new ApplyExp((Expression) new QuoteExp(((Reserved) peekTokenOrLine).proc), parseLeftHandSideExpression);
    }

    public Expression parseUnaryExpression() throws IOException, SyntaxException {
        return parsePostfixExpression();
    }

    public Expression syntaxError(String str) {
        this.errors++;
        OutPort errDefault = OutPort.errDefault();
        String name = this.port.getName();
        int lineNumber = this.port.getLineNumber() + 1;
        int columnNumber = this.port.getColumnNumber() + 1;
        if (lineNumber > 0) {
            if (name != null) {
                errDefault.print(name);
            }
            errDefault.print(':');
            errDefault.print(lineNumber);
            if (columnNumber > 1) {
                errDefault.print(':');
                errDefault.print(columnNumber);
            }
            errDefault.print(": ");
        }
        errDefault.println(str);
        return new ErrorExp(str);
    }

    public Expression parseBinaryExpression(int i) throws IOException, SyntaxException {
        Expression parseUnaryExpression = parseUnaryExpression();
        while (true) {
            Object peekToken = peekToken();
            this.token = peekToken;
            if (!(peekToken instanceof Reserved)) {
                return parseUnaryExpression;
            }
            Reserved reserved = (Reserved) peekToken;
            if (reserved.prio < i) {
                return parseUnaryExpression;
            }
            getToken();
            parseUnaryExpression = new ApplyExp((Expression) new QuoteExp(reserved.proc), parseUnaryExpression, parseBinaryExpression(reserved.prio + 1));
        }
    }

    public Expression parseIfStatement() throws IOException, SyntaxException {
        Expression expression;
        skipToken();
        Object token2 = getToken();
        if (token2 != Lexer.lparenToken) {
            return syntaxError("expected '(' - got:" + token2);
        }
        Expression parseExpression = parseExpression();
        Object token3 = getToken();
        if (token3 != Lexer.rparenToken) {
            return syntaxError("expected ')' - got:" + token3);
        }
        Expression parseStatement = parseStatement();
        if (peekToken() == Lexer.elseToken) {
            skipToken();
            expression = parseStatement();
        } else {
            expression = null;
        }
        return new IfExp(parseExpression, parseStatement, expression);
    }

    public Expression buildLoop(Expression expression, Expression expression2, Expression expression3, Expression expression4) {
        if (expression != null) {
            return new BeginExp(new Expression[]{expression, buildLoop((Expression) null, expression2, expression3, expression4)});
        }
        throw new Error("not implemented - buildLoop");
    }

    public Expression parseWhileStatement() throws IOException, SyntaxException {
        skipToken();
        Object token2 = getToken();
        if (token2 != Lexer.lparenToken) {
            return syntaxError("expected '(' - got:" + token2);
        }
        Expression parseExpression = parseExpression();
        Object token3 = getToken();
        if (token3 == Lexer.rparenToken) {
            return buildLoop((Expression) null, parseExpression, (Expression) null, parseStatement());
        }
        return syntaxError("expected ')' - got:" + token3);
    }

    public Expression parseFunctionDefinition() throws IOException, SyntaxException {
        skipToken();
        String identifier = getIdentifier();
        Object token2 = getToken();
        if (token2 != Lexer.lparenToken) {
            return syntaxError("expected '(' - got:" + token2);
        }
        Vector vector = new Vector(10);
        if (peekToken() == Lexer.rparenToken) {
            skipToken();
        } else {
            while (true) {
                vector.addElement(getIdentifier());
                Object token3 = getToken();
                if (token3 == Lexer.rparenToken) {
                    break;
                } else if (token3 != Lexer.commaToken) {
                    syntaxError("invalid token '" + token3 + "' in argument list");
                }
            }
        }
        LambdaExp lambdaExp = new LambdaExp(parseBlock());
        lambdaExp.setName(identifier);
        SetExp setExp = new SetExp((Object) identifier, (Expression) lambdaExp);
        setExp.setDefining(true);
        return setExp;
    }

    public Expression parseBlock() throws IOException, SyntaxException {
        boolean z;
        int i;
        if (getToken() != Lexer.lbraceToken) {
            return syntaxError("extened '{'");
        }
        Expression[] expressionArr = null;
        int i2 = 0;
        while (true) {
            Object peekToken = peekToken();
            this.token = peekToken;
            if (peekToken == Lexer.rbraceToken) {
                skipToken();
                if (expressionArr == null) {
                    return emptyStatement;
                }
                z = true;
            } else {
                z = false;
            }
            if (expressionArr == null) {
                expressionArr = new Expression[2];
            } else {
                int length = expressionArr.length;
                if (!z ? length <= i2 : length != i2) {
                    if (z) {
                        i = i2;
                    } else {
                        i = expressionArr.length * 2;
                    }
                    Expression[] expressionArr2 = new Expression[i];
                    System.arraycopy(expressionArr, 0, expressionArr2, 0, i2);
                    expressionArr = expressionArr2;
                }
            }
            if (z) {
                return new BeginExp(expressionArr);
            }
            expressionArr[i2] = parseStatement();
            i2++;
        }
    }

    public Expression parseStatement() throws IOException, SyntaxException {
        Object peekToken = peekToken();
        if (peekToken instanceof Reserved) {
            int i = ((Reserved) peekToken).prio;
            if (i == 31) {
                return parseIfStatement();
            }
            if (i == 32) {
                return parseWhileStatement();
            }
            if (i == 41) {
                return parseFunctionDefinition();
            }
        }
        if (peekToken == Lexer.eofToken) {
            return eofExpr;
        }
        if (peekToken == Lexer.semicolonToken) {
            skipToken();
            return emptyStatement;
        } else if (peekToken == Lexer.lbraceToken) {
            return parseBlock();
        } else {
            Expression parseExpression = parseExpression();
            getSemicolon();
            return parseExpression;
        }
    }

    public static void main(String[] strArr) {
        new Scheme();
        InPort inDefault = InPort.inDefault();
        if (inDefault instanceof TtyInPort) {
            ((TtyInPort) inDefault).setPrompter(new Prompter());
        }
        Parser parser = new Parser(inDefault);
        OutPort outDefault = OutPort.outDefault();
        while (true) {
            try {
                Expression parseStatement = parser.parseStatement();
                if (parseStatement != eofExpr) {
                    outDefault.print("[Expression: ");
                    parseStatement.print(outDefault);
                    outDefault.println("]");
                    Object eval = parseStatement.eval(Environment.user());
                    outDefault.print("result: ");
                    outDefault.print(eval);
                    outDefault.println();
                } else {
                    return;
                }
            } catch (Throwable th) {
                PrintStream printStream = System.err;
                printStream.println("caught exception:" + th);
                th.printStackTrace(System.err);
                return;
            }
        }
    }
}
