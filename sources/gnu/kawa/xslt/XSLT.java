package gnu.kawa.xslt;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.Focus;
import gnu.kawa.xml.KDocument;
import gnu.lists.TreeList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xquery.lang.XQParser;
import gnu.xquery.lang.XQResolveNames;
import gnu.xquery.lang.XQuery;
import java.io.IOException;

public class XSLT extends XQuery {
    public static XSLT instance;
    public static Symbol nullMode = Symbol.make((Object) null, "");

    public static void defineCallTemplate(Symbol symbol, double d, Procedure procedure) {
    }

    public String getName() {
        return "XSLT";
    }

    public XSLT() {
        instance = this;
        ModuleBody.setMainPrintValues(true);
    }

    public static XSLT getXsltInstance() {
        if (instance == null) {
            new XSLT();
        }
        return instance;
    }

    public Lexer getLexer(InPort inPort, SourceMessages sourceMessages) {
        return new XslTranslator(inPort, sourceMessages, this);
    }

    public boolean parse(Compilation compilation, int i) throws IOException, SyntaxException {
        Compilation.defaultCallConvention = 2;
        ((XslTranslator) compilation.lexer).parse(compilation);
        compilation.setState(4);
        XQParser xQParser = new XQParser((InPort) null, compilation.getMessages(), this);
        XQResolveNames xQResolveNames = new XQResolveNames(compilation);
        xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
        xQResolveNames.parser = xQParser;
        xQResolveNames.resolveModule(compilation.mainLambda);
        return true;
    }

    public static void registerEnvironment() {
        Language.setDefaults(new XSLT());
    }

    public static void defineApplyTemplate(String str, double d, Symbol symbol, Procedure procedure) {
        if (symbol == null) {
            symbol = nullMode;
        }
        TemplateTable.getTemplateTable(symbol).enter(str, d, procedure);
    }

    public static void defineTemplate(Symbol symbol, String str, double d, Symbol symbol2, Procedure procedure) {
        if (symbol != null) {
            defineCallTemplate(symbol, d, procedure);
        }
        if (str != null) {
            defineApplyTemplate(str, d, symbol2, procedure);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void process(gnu.lists.TreeList r6, gnu.kawa.xml.Focus r7, gnu.mapping.CallContext r8) throws java.lang.Throwable {
        /*
            gnu.lists.Consumer r0 = r8.consumer
        L_0x0002:
            int r1 = r7.ipos
            int r2 = r6.getNextKind(r1)
            r3 = 29
            if (r2 == r3) goto L_0x0082
            switch(r2) {
                case 33: goto L_0x0046;
                case 34: goto L_0x0041;
                case 35: goto L_0x001a;
                case 36: goto L_0x0010;
                case 37: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            return
        L_0x0010:
            int r1 = r1 >>> 1
            int r1 = r6.nextDataIndex(r1)
            int r1 = r1 << 1
            goto L_0x0096
        L_0x001a:
            r7.getNextTypeObject()
            java.lang.String r2 = r7.getNextTypeName()
            gnu.kawa.xslt.TemplateTable r3 = gnu.kawa.xslt.TemplateTable.nullModeTable
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "@"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            gnu.mapping.Procedure r2 = r3.find(r2)
            if (r2 == 0) goto L_0x0082
            r2.check0(r8)
            r8.runUntilDone()
            goto L_0x0096
        L_0x0041:
            int r1 = r6.firstChildPos(r1)
            goto L_0x0096
        L_0x0046:
            java.lang.Object r2 = r7.getNextTypeObject()
            java.lang.String r3 = r7.getNextTypeName()
            gnu.kawa.xslt.TemplateTable r4 = gnu.kawa.xslt.TemplateTable.nullModeTable
            gnu.mapping.Procedure r3 = r4.find(r3)
            if (r3 == 0) goto L_0x005d
            r3.check0(r8)
            r8.runUntilDone()
            goto L_0x0076
        L_0x005d:
            r0.startElement(r2)
            int r2 = r6.firstAttributePos(r1)
            if (r2 != 0) goto L_0x006a
            int r2 = r6.firstChildPos(r1)
        L_0x006a:
            r7.push(r6, r2)
            process(r6, r7, r8)
            r7.pop()
            r0.endElement()
        L_0x0076:
            int r1 = r1 >>> 1
            int r1 = r6.nextDataIndex(r1)
            int r1 = r1 << 1
            r7.gotoNext()
            goto L_0x0096
        L_0x0082:
            int r1 = r1 >>> 1
            r2 = 2147483647(0x7fffffff, float:NaN)
            int r2 = r6.nextNodeIndex(r1, r2)
            if (r1 != r2) goto L_0x0091
            int r2 = r6.nextDataIndex(r1)
        L_0x0091:
            r6.consumeIRange(r1, r2, r0)
            int r1 = r2 << 1
        L_0x0096:
            r7.ipos = r1
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xslt.XSLT.process(gnu.lists.TreeList, gnu.kawa.xml.Focus, gnu.mapping.CallContext):void");
    }

    public static void runStylesheet() throws Throwable {
        CallContext instance2 = CallContext.getInstance();
        ApplicationMainSupport.processSetProperties();
        String[] strArr = ApplicationMainSupport.commandLineArgArray;
        for (String parse : strArr) {
            KDocument parse2 = Document.parse(parse);
            Focus current = Focus.getCurrent();
            current.push(parse2.sequence, parse2.ipos);
            process((TreeList) parse2.sequence, current, instance2);
        }
    }

    public static void applyTemplates(String str, Symbol symbol) throws Throwable {
        if (symbol == null) {
            symbol = nullMode;
        }
        TemplateTable.getTemplateTable(symbol);
        CallContext instance2 = CallContext.getInstance();
        Focus current = Focus.getCurrent();
        TreeList treeList = (TreeList) current.sequence;
        current.push(treeList, treeList.firstChildPos(current.ipos));
        process(treeList, current, instance2);
        current.pop();
    }
}
