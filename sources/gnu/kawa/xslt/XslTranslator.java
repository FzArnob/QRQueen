package gnu.kawa.xslt;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.lists.Consumer;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLParser;
import gnu.xml.XName;
import gnu.xquery.lang.XQParser;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class XslTranslator extends Lexer implements Consumer {
    static final String XSL_TRANSFORM_URI = "http://www.w3.org/1999/XSL/Transform";
    static final Method applyTemplatesMethod;
    static final PrimProcedure applyTemplatesProc;
    static final Method defineTemplateMethod;
    static final PrimProcedure defineTemplateProc;
    static final Method runStylesheetMethod;
    static final PrimProcedure runStylesheetProc;
    static final ClassType typeTemplateTable = ClassType.make("gnu.kawa.xslt.TemplateTable");
    static final ClassType typeXSLT;
    Object attributeType;
    StringBuffer attributeValue = new StringBuffer(100);
    Compilation comp;
    Declaration consumerDecl;
    InPort in;
    boolean inAttribute;
    boolean inTemplate;
    XSLT interpreter;
    ModuleExp mexp;
    StringBuffer nesting = new StringBuffer(100);
    boolean preserveSpace;
    LambdaExp templateLambda;

    /* access modifiers changed from: package-private */
    public void append(Expression expression) {
    }

    public void endDocument() {
    }

    public boolean ignoring() {
        return false;
    }

    public void startDocument() {
    }

    XslTranslator(InPort inPort, SourceMessages sourceMessages, XSLT xslt) {
        super(inPort, sourceMessages);
        this.interpreter = xslt;
        this.in = inPort;
    }

    /* access modifiers changed from: package-private */
    public void maybeSkipWhitespace() {
        String str;
        if (!this.preserveSpace) {
            int size = this.comp.exprStack.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                Expression expression = (Expression) this.comp.exprStack.elementAt(size);
                if (!(expression instanceof QuoteExp)) {
                    break;
                }
                Object value = ((QuoteExp) expression).getValue();
                if (value == null) {
                    str = "";
                } else {
                    str = value.toString();
                }
                int length = str.length();
                while (true) {
                    length--;
                    if (length >= 0) {
                        char charAt = str.charAt(length);
                        if (charAt != ' ' && charAt != 9 && charAt != 13 && charAt != 10) {
                            return;
                        }
                    }
                }
            }
            this.comp.exprStack.setSize(size + 1);
        }
    }

    public String popMatchingAttribute(String str, String str2, int i) {
        int size = this.comp.exprStack.size();
        for (int i2 = i; i2 < size; i2++) {
            Object elementAt = this.comp.exprStack.elementAt(i);
            if (!(elementAt instanceof ApplyExp)) {
                return null;
            }
            ApplyExp applyExp = (ApplyExp) elementAt;
            applyExp.getFunction();
            if (applyExp.getFunction() != MakeAttribute.makeAttributeExp) {
                return null;
            }
            Expression[] args = applyExp.getArgs();
            if (args.length != 2) {
                return null;
            }
            Expression expression = args[0];
            if (!(expression instanceof QuoteExp)) {
                return null;
            }
            Object value = ((QuoteExp) expression).getValue();
            if (!(value instanceof Symbol)) {
                return null;
            }
            Symbol symbol = (Symbol) value;
            if (symbol.getLocalPart() == str2 && symbol.getNamespaceURI() == str) {
                this.comp.exprStack.removeElementAt(i2);
                return (String) ((QuoteExp) args[1]).getValue();
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Expression popTemplateBody(int i) {
        int size = this.comp.exprStack.size() - i;
        Expression[] expressionArr = new Expression[size];
        while (true) {
            size--;
            if (size < 0) {
                return new ApplyExp((Procedure) AppendValues.appendValues, expressionArr);
            }
            expressionArr[size] = this.comp.exprStack.pop();
        }
    }

    public static String isXslTag(Object obj) {
        if (obj instanceof QuoteExp) {
            obj = ((QuoteExp) obj).getValue();
        }
        if (!(obj instanceof Symbol)) {
            return null;
        }
        Symbol symbol = (Symbol) obj;
        if (symbol.getNamespaceURI() != XSL_TRANSFORM_URI) {
            return null;
        }
        return symbol.getLocalName();
    }

    public void startElement(Object obj) {
        maybeSkipWhitespace();
        String isXslTag = isXslTag(obj);
        if (isXslTag == "template") {
            if (this.templateLambda != null) {
                error("nested xsl:template");
            }
            this.templateLambda = new LambdaExp();
        } else if (isXslTag == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            this.preserveSpace = false;
        }
        if (obj instanceof XName) {
            XName xName = (XName) obj;
            obj = Symbol.make(xName.getNamespaceURI(), xName.getLocalPart(), xName.getPrefix());
        }
        this.nesting.append((char) this.comp.exprStack.size());
        push(obj);
    }

    public void startAttribute(Object obj) {
        if (this.inAttribute) {
            error('f', "internal error - attribute inside attribute");
        }
        this.attributeType = obj;
        this.attributeValue.setLength(0);
        this.nesting.append((char) this.comp.exprStack.size());
        this.inAttribute = true;
    }

    public void endAttribute() {
        push((Expression) new ApplyExp((Expression) MakeAttribute.makeAttributeExp, new QuoteExp(this.attributeType), new QuoteExp(this.attributeValue.toString())));
        StringBuffer stringBuffer = this.nesting;
        stringBuffer.setLength(stringBuffer.length() - 1);
        this.inAttribute = false;
    }

    public void endElement() {
        maybeSkipWhitespace();
        int length = this.nesting.length() - 1;
        char charAt = this.nesting.charAt(length);
        this.nesting.setLength(length);
        String isXslTag = isXslTag((Expression) this.comp.exprStack.elementAt(charAt));
        if (isXslTag == "value-of") {
            String popMatchingAttribute = popMatchingAttribute("", "select", charAt + 1);
            if (popMatchingAttribute != null) {
                ApplyExp applyExp = new ApplyExp(XQParser.makeText, parseXPath(popMatchingAttribute));
                this.comp.exprStack.pop();
                push((Expression) applyExp);
            }
        } else if (isXslTag == "copy-of") {
            String popMatchingAttribute2 = popMatchingAttribute("", "select", charAt + 1);
            if (popMatchingAttribute2 != null) {
                Expression parseXPath = parseXPath(popMatchingAttribute2);
                this.comp.exprStack.pop();
                push(parseXPath);
            }
        } else if (isXslTag == "apply-templates") {
            int i = charAt + 1;
            Expression[] expressionArr = {new QuoteExp(popMatchingAttribute("", "select", i)), resolveQNameExpression(popMatchingAttribute("", "mode", i))};
            this.comp.exprStack.pop();
            push((Expression) new ApplyExp((Expression) new QuoteExp(applyTemplatesProc), expressionArr));
        } else if (isXslTag == "if") {
            int i2 = charAt + 1;
            Expression booleanValue = XQParser.booleanValue(parseXPath(popMatchingAttribute("", "test", i2)));
            Expression popTemplateBody = popTemplateBody(i2);
            this.comp.exprStack.pop();
            push((Expression) new IfExp(booleanValue, popTemplateBody, QuoteExp.voidExp));
        } else if (isXslTag == "stylesheet" || isXslTag == "transform") {
            int i3 = charAt + 1;
            popMatchingAttribute("", "version", i3);
            push((Expression) new ApplyExp((Expression) new QuoteExp(runStylesheetProc), Expression.noExpressions));
            Expression popTemplateBody2 = popTemplateBody(i3);
            push(popTemplateBody2);
            this.mexp.body = popTemplateBody2;
        } else if (isXslTag == "template") {
            int i4 = charAt + 1;
            String popMatchingAttribute3 = popMatchingAttribute("", "match", i4);
            String popMatchingAttribute4 = popMatchingAttribute("", CommonProperties.NAME, i4);
            popMatchingAttribute("", "priority", i4);
            String popMatchingAttribute5 = popMatchingAttribute("", "mode", i4);
            this.templateLambda.body = popTemplateBody(i4);
            this.comp.exprStack.pop();
            push((Expression) new ApplyExp((Expression) new QuoteExp(defineTemplateProc), resolveQNameExpression(popMatchingAttribute4), new QuoteExp(popMatchingAttribute3), new QuoteExp(DFloNum.make(0.0d)), resolveQNameExpression(popMatchingAttribute5), this.templateLambda));
            this.templateLambda = null;
        } else if (isXslTag == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            this.preserveSpace = false;
            int size = (this.comp.exprStack.size() - charAt) - 1;
            Expression[] expressionArr2 = new Expression[size];
            while (true) {
                size--;
                if (size >= 0) {
                    expressionArr2[size] = this.comp.exprStack.pop();
                } else {
                    this.comp.exprStack.pop();
                    ApplyExp applyExp2 = new ApplyExp(XQParser.makeText, expressionArr2);
                    push((Expression) applyExp2);
                    this.mexp.body = applyExp2;
                    return;
                }
            }
        } else {
            int size2 = this.comp.exprStack.size() - charAt;
            Expression[] expressionArr3 = new Expression[size2];
            while (true) {
                size2--;
                if (size2 >= 0) {
                    expressionArr3[size2] = this.comp.exprStack.pop();
                } else {
                    ApplyExp applyExp3 = new ApplyExp((Expression) new QuoteExp(new MakeElement()), expressionArr3);
                    push((Expression) applyExp3);
                    this.mexp.body = applyExp3;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Expression parseXPath(String str) {
        try {
            XQParser xQParser = new XQParser(new CharArrayInPort(str), this.comp.getMessages(), this.interpreter);
            Vector vector = new Vector(20);
            while (true) {
                Expression parse = xQParser.parse(this.comp);
                if (parse == null) {
                    break;
                }
                vector.addElement(parse);
            }
            int size = vector.size();
            if (size == 0) {
                return QuoteExp.voidExp;
            }
            if (size == 1) {
                return (Expression) vector.elementAt(0);
            }
            throw new InternalError("too many xpath expressions");
        } catch (Throwable th) {
            th.printStackTrace();
            throw new InternalError("caught " + th);
        }
    }

    public void write(int i) {
        String str;
        if (this.inAttribute) {
            this.attributeValue.appendCodePoint(i);
            return;
        }
        if (i < 65536) {
            str = String.valueOf(i);
        } else {
            str = new String(new char[]{(char) (((i - 65536) >> 10) + 55296), (char) ((i & 1023) + 56320)});
        }
        push((Object) str);
    }

    public Consumer append(char c) {
        if (this.inAttribute) {
            this.attributeValue.append(c);
        } else {
            push((Object) String.valueOf(c));
        }
        return this;
    }

    public Consumer append(CharSequence charSequence) {
        if (this.inAttribute) {
            this.attributeValue.append(charSequence);
        } else {
            push((Object) charSequence.toString());
        }
        return this;
    }

    public Consumer append(CharSequence charSequence, int i, int i2) {
        return append(charSequence.subSequence(i, i2));
    }

    /* access modifiers changed from: package-private */
    public void push(Expression expression) {
        this.comp.exprStack.push(expression);
    }

    /* access modifiers changed from: package-private */
    public void push(Object obj) {
        push((Expression) new QuoteExp(obj));
    }

    public void writeBoolean(boolean z) {
        if (this.inAttribute) {
            this.attributeValue.append(z);
        } else {
            push((Expression) z ? QuoteExp.trueExp : QuoteExp.falseExp);
        }
    }

    public void writeFloat(float f) {
        if (this.inAttribute) {
            this.attributeValue.append(f);
        } else {
            push((Object) DFloNum.make((double) f));
        }
    }

    public void writeDouble(double d) {
        if (this.inAttribute) {
            this.attributeValue.append(d);
        } else {
            push((Object) DFloNum.make(d));
        }
    }

    public void writeInt(int i) {
        if (this.inAttribute) {
            this.attributeValue.append(i);
        } else {
            push((Object) IntNum.make(i));
        }
    }

    public void writeLong(long j) {
        if (this.inAttribute) {
            this.attributeValue.append(j);
        } else {
            push((Object) IntNum.make(j));
        }
    }

    public void startDocument(ModuleExp moduleExp) {
        this.mexp = moduleExp;
        startDocument();
    }

    public void writeObject(Object obj) {
        if (this.inAttribute) {
            this.attributeValue.append(obj);
        } else {
            push(obj);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        if (this.inAttribute) {
            this.attributeValue.append(cArr, i, i2);
        } else {
            push((Object) new String(cArr, i, i2));
        }
    }

    public void write(String str) {
        if (this.inAttribute) {
            this.attributeValue.append(str);
        } else {
            push((Object) str);
        }
    }

    public void write(CharSequence charSequence, int i, int i2) {
        write(charSequence.subSequence(i, i2).toString());
    }

    public Expression getExpression() {
        return this.comp.exprStack.pop();
    }

    public void error(char c, String str) {
        getMessages().error(c, str);
    }

    /* access modifiers changed from: package-private */
    public Expression resolveQNameExpression(String str) {
        if (str == null) {
            return QuoteExp.nullExp;
        }
        return new QuoteExp(Symbol.make((Object) null, str));
    }

    public void parse(Compilation compilation) throws IOException {
        this.comp = compilation;
        if (compilation.exprStack == null) {
            compilation.exprStack = new Stack<>();
        }
        ModuleExp pushNewModule = compilation.pushNewModule((Lexer) this);
        compilation.mustCompileHere();
        startDocument(pushNewModule);
        XMLParser.parse((LineBufferedReader) this.in, getMessages(), (Consumer) this);
        endDocument();
        compilation.pop(pushNewModule);
    }

    static {
        ClassType make = ClassType.make("gnu.kawa.xslt.XSLT");
        typeXSLT = make;
        Method declaredMethod = make.getDeclaredMethod("defineTemplate", 5);
        defineTemplateMethod = declaredMethod;
        Method declaredMethod2 = make.getDeclaredMethod("runStylesheet", 0);
        runStylesheetMethod = declaredMethod2;
        defineTemplateProc = new PrimProcedure(declaredMethod);
        runStylesheetProc = new PrimProcedure(declaredMethod2);
        Method declaredMethod3 = make.getDeclaredMethod("applyTemplates", 2);
        applyTemplatesMethod = declaredMethod3;
        applyTemplatesProc = new PrimProcedure(declaredMethod3);
    }
}
