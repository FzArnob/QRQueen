package kawa;

import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;

public class Shell {
    private static Class[] boolClasses;
    public static ThreadLocal currentLoadPath = new ThreadLocal();
    public static Object[] defaultFormatInfo;
    public static Method defaultFormatMethod;
    public static String defaultFormatName;
    static Object[][] formats;
    private static Class[] httpPrinterClasses = {OutPort.class};
    private static Class[] noClasses = new Class[0];
    private static Object portArg = "(port)";
    private static Class[] xmlPrinterClasses = {OutPort.class, Object.class};

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            currentLoadPath = r0
            r0 = 0
            java.lang.Class[] r1 = new java.lang.Class[r0]
            noClasses = r1
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r3 = java.lang.Boolean.TYPE
            r2[r0] = r3
            boolClasses = r2
            r3 = 2
            java.lang.Class[] r4 = new java.lang.Class[r3]
            java.lang.Class<gnu.mapping.OutPort> r5 = gnu.mapping.OutPort.class
            r4[r0] = r5
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            r4[r1] = r5
            xmlPrinterClasses = r4
            java.lang.Class[] r4 = new java.lang.Class[r1]
            java.lang.Class<gnu.mapping.OutPort> r5 = gnu.mapping.OutPort.class
            r4[r0] = r5
            httpPrinterClasses = r4
            java.lang.String r4 = "(port)"
            portArg = r4
            r4 = 14
            java.lang.Object[][] r4 = new java.lang.Object[r4][]
            r5 = 5
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.String r7 = "scheme"
            r6[r0] = r7
            java.lang.String r7 = "gnu.kawa.functions.DisplayFormat"
            r6[r1] = r7
            java.lang.String r8 = "getSchemeFormat"
            r6[r3] = r8
            r9 = 3
            r6[r9] = r2
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            r10 = 4
            r6[r10] = r2
            r4[r0] = r6
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r6 = "readable-scheme"
            r2[r0] = r6
            r2[r1] = r7
            r2[r3] = r8
            java.lang.Class[] r6 = boolClasses
            r2[r9] = r6
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r2[r10] = r6
            r4[r1] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r6 = "elisp"
            r2[r0] = r6
            r2[r1] = r7
            java.lang.String r6 = "getEmacsLispFormat"
            r2[r3] = r6
            java.lang.Class[] r8 = boolClasses
            r2[r9] = r8
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            r2[r10] = r8
            r4[r3] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r8 = "readable-elisp"
            r2[r0] = r8
            r2[r1] = r7
            r2[r3] = r6
            java.lang.Class[] r6 = boolClasses
            r2[r9] = r6
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r2[r10] = r6
            r4[r9] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r6 = "clisp"
            r2[r0] = r6
            r2[r1] = r7
            java.lang.String r6 = "getCommonLispFormat"
            r2[r3] = r6
            java.lang.Class[] r8 = boolClasses
            r2[r9] = r8
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            r2[r10] = r8
            r4[r10] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r8 = "readable-clisp"
            r2[r0] = r8
            r2[r1] = r7
            r2[r3] = r6
            java.lang.Class[] r8 = boolClasses
            r2[r9] = r8
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            r2[r10] = r8
            r4[r5] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r8 = "commonlisp"
            r2[r0] = r8
            r2[r1] = r7
            r2[r3] = r6
            java.lang.Class[] r8 = boolClasses
            r2[r9] = r8
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            r2[r10] = r8
            r8 = 6
            r4[r8] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r11 = "readable-commonlisp"
            r2[r0] = r11
            r2[r1] = r7
            r2[r3] = r6
            java.lang.Class[] r6 = boolClasses
            r2[r9] = r6
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            r2[r10] = r6
            r6 = 7
            r4[r6] = r2
            java.lang.Object[] r2 = new java.lang.Object[r8]
            java.lang.String r6 = "xml"
            r2[r0] = r6
            java.lang.String r6 = "gnu.xml.XMLPrinter"
            r2[r1] = r6
            java.lang.String r7 = "make"
            r2[r3] = r7
            java.lang.Class[] r11 = xmlPrinterClasses
            r2[r9] = r11
            java.lang.Object r12 = portArg
            r2[r10] = r12
            r13 = 0
            r2[r5] = r13
            r14 = 8
            r4[r14] = r2
            java.lang.Object[] r2 = new java.lang.Object[r8]
            java.lang.String r14 = "html"
            r2[r0] = r14
            r2[r1] = r6
            r2[r3] = r7
            r2[r9] = r11
            r2[r10] = r12
            r2[r5] = r14
            r14 = 9
            r4[r14] = r2
            java.lang.Object[] r2 = new java.lang.Object[r8]
            java.lang.String r8 = "xhtml"
            r2[r0] = r8
            r2[r1] = r6
            r2[r3] = r7
            r2[r9] = r11
            r2[r10] = r12
            r2[r5] = r8
            r6 = 10
            r4[r6] = r2
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r5 = "cgi"
            r2[r0] = r5
            java.lang.String r5 = "gnu.kawa.xml.HttpPrinter"
            r2[r1] = r5
            r2[r3] = r7
            java.lang.Class[] r5 = httpPrinterClasses
            r2[r9] = r5
            r2[r10] = r12
            r5 = 11
            r4[r5] = r2
            java.lang.Object[] r2 = new java.lang.Object[r10]
            java.lang.String r5 = "ignore"
            r2[r0] = r5
            java.lang.String r5 = "gnu.lists.VoidConsumer"
            r2[r1] = r5
            java.lang.String r5 = "getInstance"
            r2[r3] = r5
            java.lang.Class[] r3 = noClasses
            r2[r9] = r3
            r3 = 12
            r4[r3] = r2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r13
            r0 = 13
            r4[r0] = r1
            formats = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Shell.<clinit>():void");
    }

    public static void setDefaultFormat(String str) {
        Object[] objArr;
        String intern = str.intern();
        defaultFormatName = intern;
        int i = 0;
        while (true) {
            objArr = formats[i];
            Object obj = objArr[0];
            if (obj == null) {
                PrintStream printStream = System.err;
                printStream.println("kawa: unknown output format '" + intern + "'");
                System.exit(-1);
            } else if (obj == intern) {
                break;
            }
            i++;
        }
        defaultFormatInfo = objArr;
        try {
            defaultFormatMethod = Class.forName((String) objArr[1]).getMethod((String) objArr[2], (Class[]) objArr[3]);
        } catch (Throwable th) {
            PrintStream printStream2 = System.err;
            printStream2.println("kawa:  caught " + th + " while looking for format '" + intern + "'");
            System.exit(-1);
        }
        if (!defaultFormatInfo[1].equals("gnu.lists.VoidConsumer")) {
            ModuleBody.setMainPrintValues(true);
        }
    }

    public static Consumer getOutputConsumer(OutPort outPort) {
        Object[] objArr = defaultFormatInfo;
        if (outPort == null) {
            return VoidConsumer.getInstance();
        }
        if (objArr == null) {
            return Language.getDefaultLanguage().getOutputConsumer(outPort);
        }
        try {
            int length = objArr.length - 4;
            Object[] objArr2 = new Object[length];
            System.arraycopy(objArr, 4, objArr2, 0, length);
            while (true) {
                length--;
                if (length < 0) {
                    break;
                } else if (objArr2[length] == portArg) {
                    objArr2[length] = outPort;
                }
            }
            Object invoke = defaultFormatMethod.invoke((Object) null, objArr2);
            if (!(invoke instanceof AbstractFormat)) {
                return (Consumer) invoke;
            }
            outPort.objectFormat = (AbstractFormat) invoke;
            return outPort;
        } catch (Throwable th) {
            throw new RuntimeException("cannot get output-format '" + defaultFormatName + "' - caught " + th);
        }
    }

    public static boolean run(Language language, Environment environment) {
        OutPort outPort;
        InPort inDefault = InPort.inDefault();
        SourceMessages sourceMessages = new SourceMessages();
        if (inDefault instanceof TtyInPort) {
            Procedure prompter = language.getPrompter();
            if (prompter != null) {
                ((TtyInPort) inDefault).setPrompter(prompter);
            }
            outPort = OutPort.errDefault();
        } else {
            outPort = null;
        }
        OutPort outPort2 = outPort;
        Throwable run = run(language, environment, inDefault, OutPort.outDefault(), outPort2, sourceMessages);
        if (run == null) {
            return true;
        }
        printError(run, sourceMessages, OutPort.errDefault());
        return false;
    }

    public static Throwable run(Language language, Environment environment, InPort inPort, OutPort outPort, OutPort outPort2, SourceMessages sourceMessages) {
        AbstractFormat abstractFormat = outPort != null ? outPort.objectFormat : null;
        try {
            return run(language, environment, inPort, getOutputConsumer(outPort), outPort2, (URL) null, sourceMessages);
        } finally {
            if (outPort != null) {
                outPort.objectFormat = abstractFormat;
            }
        }
    }

    public static boolean run(Language language, Environment environment, InPort inPort, Consumer consumer, OutPort outPort, URL url) {
        SourceMessages sourceMessages = new SourceMessages();
        Throwable run = run(language, environment, inPort, consumer, outPort, url, sourceMessages);
        if (run != null) {
            printError(run, sourceMessages, outPort);
        }
        return run == null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x00c9 A[SYNTHETIC, Splitter:B:63:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00c1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Throwable run(gnu.expr.Language r16, gnu.mapping.Environment r17, gnu.mapping.InPort r18, gnu.lists.Consumer r19, gnu.mapping.OutPort r20, java.net.URL r21, gnu.text.SourceMessages r22) {
        /*
            r1 = r16
            r2 = r19
            r3 = r20
            r4 = r22
            gnu.expr.Language r5 = gnu.expr.Language.setSaveCurrent(r16)
            r6 = r18
            gnu.text.Lexer r7 = r1.getLexer(r6, r4)
            r8 = 1
            if (r3 == 0) goto L_0x0017
            r10 = 1
            goto L_0x0018
        L_0x0017:
            r10 = 0
        L_0x0018:
            r7.setInteractive(r10)
            gnu.mapping.CallContext r11 = gnu.mapping.CallContext.getInstance()
            r12 = 0
            if (r2 == 0) goto L_0x0028
            gnu.lists.Consumer r0 = r11.consumer
            r11.consumer = r2
            r13 = r0
            goto L_0x0029
        L_0x0028:
            r13 = r12
        L_0x0029:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ SecurityException -> 0x003d }
            java.lang.ClassLoader r14 = r0.getContextClassLoader()     // Catch:{ SecurityException -> 0x003d }
            boolean r15 = r14 instanceof gnu.bytecode.ArrayClassLoader     // Catch:{ SecurityException -> 0x003d }
            if (r15 != 0) goto L_0x003d
            gnu.bytecode.ArrayClassLoader r15 = new gnu.bytecode.ArrayClassLoader     // Catch:{ SecurityException -> 0x003d }
            r15.<init>((java.lang.ClassLoader) r14)     // Catch:{ SecurityException -> 0x003d }
            r0.setContextClassLoader(r15)     // Catch:{ SecurityException -> 0x003d }
        L_0x003d:
            r0 = 7
            gnu.expr.Compilation r0 = r1.parse((gnu.text.Lexer) r7, (int) r0, (gnu.expr.ModuleInfo) r12)     // Catch:{ all -> 0x00ba }
            if (r10 == 0) goto L_0x004b
            r14 = 20
            boolean r14 = r4.checkErrors((java.io.PrintWriter) r3, (int) r14)     // Catch:{ all -> 0x00ba }
            goto L_0x0052
        L_0x004b:
            boolean r14 = r22.seenErrors()     // Catch:{ all -> 0x00ba }
            if (r14 != 0) goto L_0x00ae
            r14 = 0
        L_0x0052:
            if (r0 != 0) goto L_0x0055
            goto L_0x00a6
        L_0x0055:
            if (r14 == 0) goto L_0x0058
            goto L_0x003d
        L_0x0058:
            gnu.expr.ModuleExp r14 = r0.getModule()     // Catch:{ all -> 0x00ba }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r15.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = "atInteractiveLevel$"
            r15.append(r9)     // Catch:{ all -> 0x00ba }
            int r9 = gnu.expr.ModuleExp.interactiveCounter     // Catch:{ all -> 0x00ba }
            int r9 = r9 + r8
            gnu.expr.ModuleExp.interactiveCounter = r9     // Catch:{ all -> 0x00ba }
            r15.append(r9)     // Catch:{ all -> 0x00ba }
            java.lang.String r9 = r15.toString()     // Catch:{ all -> 0x00ba }
            r14.setName(r9)     // Catch:{ all -> 0x00ba }
        L_0x0075:
            int r9 = r18.read()     // Catch:{ all -> 0x00ba }
            if (r9 < 0) goto L_0x008f
            r14 = 13
            if (r9 == r14) goto L_0x008f
            r14 = 10
            if (r9 != r14) goto L_0x0084
            goto L_0x008f
        L_0x0084:
            r14 = 32
            if (r9 == r14) goto L_0x0075
            r14 = 9
            if (r9 == r14) goto L_0x0075
            r18.unread()     // Catch:{ all -> 0x00ba }
        L_0x008f:
            r14 = r17
            r15 = r21
            boolean r0 = gnu.expr.ModuleExp.evalModule(r14, r11, r0, r15, r3)     // Catch:{ all -> 0x00b8 }
            if (r0 != 0) goto L_0x009a
            goto L_0x003d
        L_0x009a:
            boolean r0 = r2 instanceof java.io.Writer     // Catch:{ all -> 0x00b8 }
            if (r0 == 0) goto L_0x00a4
            r0 = r2
            java.io.Writer r0 = (java.io.Writer) r0     // Catch:{ all -> 0x00b8 }
            r0.flush()     // Catch:{ all -> 0x00b8 }
        L_0x00a4:
            if (r9 >= 0) goto L_0x003d
        L_0x00a6:
            if (r2 == 0) goto L_0x00aa
            r11.consumer = r13
        L_0x00aa:
            gnu.expr.Language.restoreCurrent(r5)
            return r12
        L_0x00ae:
            r14 = r17
            r15 = r21
            gnu.text.SyntaxException r0 = new gnu.text.SyntaxException     // Catch:{ all -> 0x00b8 }
            r0.<init>(r4)     // Catch:{ all -> 0x00b8 }
            throw r0     // Catch:{ all -> 0x00b8 }
        L_0x00b8:
            r0 = move-exception
            goto L_0x00bf
        L_0x00ba:
            r0 = move-exception
            r14 = r17
            r15 = r21
        L_0x00bf:
            if (r10 != 0) goto L_0x00c9
            if (r2 == 0) goto L_0x00c5
            r11.consumer = r13
        L_0x00c5:
            gnu.expr.Language.restoreCurrent(r5)
            return r0
        L_0x00c9:
            printError(r0, r4, r3)     // Catch:{ all -> 0x00ce }
            goto L_0x003d
        L_0x00ce:
            r0 = move-exception
            r1 = r0
            if (r2 == 0) goto L_0x00d4
            r11.consumer = r13
        L_0x00d4:
            gnu.expr.Language.restoreCurrent(r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Shell.run(gnu.expr.Language, gnu.mapping.Environment, gnu.mapping.InPort, gnu.lists.Consumer, gnu.mapping.OutPort, java.net.URL, gnu.text.SourceMessages):java.lang.Throwable");
    }

    public static void printError(Throwable th, SourceMessages sourceMessages, OutPort outPort) {
        if (th instanceof WrongArguments) {
            WrongArguments wrongArguments = (WrongArguments) th;
            sourceMessages.printAll((PrintWriter) outPort, 20);
            if (wrongArguments.usage != null) {
                outPort.println("usage: " + wrongArguments.usage);
            }
            wrongArguments.printStackTrace(outPort);
        } else if (th instanceof ClassCastException) {
            sourceMessages.printAll((PrintWriter) outPort, 20);
            outPort.println("Invalid parameter, was: " + th.getMessage());
            th.printStackTrace(outPort);
        } else {
            if (th instanceof SyntaxException) {
                SyntaxException syntaxException = (SyntaxException) th;
                if (syntaxException.getMessages() == sourceMessages) {
                    syntaxException.printAll(outPort, 20);
                    syntaxException.clear();
                    return;
                }
            }
            sourceMessages.printAll((PrintWriter) outPort, 20);
            th.printStackTrace(outPort);
        }
    }

    public static final CompiledModule checkCompiledZip(InputStream inputStream, Path path, Environment environment, Language language) throws IOException {
        try {
            inputStream.mark(5);
            boolean z = inputStream.read() == 80 && inputStream.read() == 75 && inputStream.read() == 3 && inputStream.read() == 4;
            inputStream.reset();
            if (!z) {
                return null;
            }
            inputStream.close();
            Environment current = Environment.getCurrent();
            String obj = path.toString();
            if (environment != current) {
                try {
                    Environment.setCurrent(environment);
                } catch (IOException e) {
                    throw new WrappedException("load: " + obj + " - " + e.toString(), e);
                } catch (Throwable th) {
                    if (environment != current) {
                        Environment.setCurrent(current);
                    }
                    throw th;
                }
            }
            if (path instanceof FilePath) {
                File file = ((FilePath) path).toFile();
                if (!file.exists()) {
                    throw new RuntimeException("load: " + obj + " - not found");
                } else if (file.canRead()) {
                    CompiledModule make = CompiledModule.make(new ZipLoader(obj).loadAllClasses(), language);
                    if (environment != current) {
                        Environment.setCurrent(current);
                    }
                    return make;
                } else {
                    throw new RuntimeException("load: " + obj + " - not readable");
                }
            } else {
                throw new RuntimeException("load: " + obj + " - not a file path");
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static boolean runFileOrClass(String str, boolean z, int i) {
        InputStream inputStream;
        Path path;
        Language defaultLanguage = Language.getDefaultLanguage();
        try {
            if (str.equals("-")) {
                path = Path.valueOf("/dev/stdin");
                inputStream = System.in;
            } else {
                path = Path.valueOf(str);
                inputStream = path.openInputStream();
            }
            return runFile(inputStream, path, Environment.getCurrent(), z, i);
        } catch (Throwable th) {
            try {
                CompiledModule.make(Class.forName(str), defaultLanguage).evalModule(Environment.getCurrent(), OutPort.outDefault());
                return true;
            } catch (Throwable th2) {
                th2.printStackTrace();
                return false;
            }
        }
    }

    public static final boolean runFile(InputStream inputStream, Path path, Environment environment, boolean z, int i) throws Throwable {
        InPort openFile;
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        Language defaultLanguage = Language.getDefaultLanguage();
        Path path2 = (Path) currentLoadPath.get();
        try {
            currentLoadPath.set(path);
            CompiledModule checkCompiledZip = checkCompiledZip(inputStream, path, environment, defaultLanguage);
            if (checkCompiledZip == null) {
                openFile = InPort.openFile(inputStream, path);
                while (true) {
                    i--;
                    if (i < 0) {
                        break;
                    }
                    openFile.skipRestOfLine();
                }
                SourceMessages sourceMessages = new SourceMessages();
                URL url = path.toURL();
                if (z) {
                    Throwable run = run(defaultLanguage, environment, openFile, ModuleBody.getMainPrintValues() ? getOutputConsumer(OutPort.outDefault()) : new VoidConsumer(), (OutPort) null, url, sourceMessages);
                    if (run != null) {
                        throw run;
                    }
                } else {
                    CompiledModule compileSource = compileSource(openFile, environment, url, defaultLanguage, sourceMessages);
                    sourceMessages.printAll((PrintWriter) OutPort.errDefault(), 20);
                    if (compileSource == null) {
                        openFile.close();
                        currentLoadPath.set(path2);
                        return false;
                    }
                    checkCompiledZip = compileSource;
                }
                openFile.close();
            }
            if (checkCompiledZip != null) {
                checkCompiledZip.evalModule(environment, OutPort.outDefault());
            }
            currentLoadPath.set(path2);
            return true;
        } catch (Throwable th) {
            currentLoadPath.set(path2);
            throw th;
        }
    }

    static CompiledModule compileSource(InPort inPort, Environment environment, URL url, Language language, SourceMessages sourceMessages) throws SyntaxException, IOException {
        Compilation parse = language.parse(inPort, sourceMessages, 1, ModuleManager.getInstance().findWithSourcePath(inPort.getName()));
        CallContext.getInstance().values = Values.noArgs;
        Object evalModule1 = ModuleExp.evalModule1(environment, parse, url, (OutPort) null);
        if (evalModule1 == null || sourceMessages.seenErrors()) {
            return null;
        }
        return new CompiledModule(parse.getModule(), evalModule1, language);
    }
}
