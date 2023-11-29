package kawa;

import gnu.bytecode.ClassType;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.FString;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.Values;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.WriterManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import net.lingala.zip4j.util.InternalZipConstants;

public class repl extends Procedure0or1 {
    public static String compilationTopname = null;
    static int defaultParseOptions = 72;
    public static String homeDirectory;
    public static boolean noConsole;
    static Language previousLanguage;
    static boolean shutdownRegistered = WriterManager.instance.registerShutdownHook();
    Language language;

    public repl(Language language2) {
        this.language = language2;
    }

    public Object apply0() {
        Shell.run(this.language, Environment.getCurrent());
        return Values.empty;
    }

    public Object apply1(Object obj) {
        Shell.run(this.language, (Environment) obj);
        return Values.empty;
    }

    static void bad_option(String str) {
        PrintStream printStream = System.err;
        printStream.println("kawa: bad option '" + str + "'");
        printOptions(System.err);
        System.exit(-1);
    }

    public static void printOption(PrintStream printStream, String str, String str2) {
        printStream.print(" ");
        printStream.print(str);
        int length = str.length() + 1;
        for (int i = 0; i < 30 - length; i++) {
            printStream.print(" ");
        }
        printStream.print(" ");
        printStream.println(str2);
    }

    public static void printOptions(PrintStream printStream) {
        printStream.println("Usage: [java kawa.repl | kawa] [options ...]");
        printStream.println();
        printStream.println(" Generic options:");
        printOption(printStream, "--help", "Show help about options");
        printOption(printStream, "--author", "Show author information");
        printOption(printStream, "--version", "Show version information");
        printStream.println();
        printStream.println(" Options");
        printOption(printStream, "-e <expr>", "Evaluate expression <expr>");
        printOption(printStream, "-c <expr>", "Same as -e, but make sure ~/.kawarc.scm is run first");
        printOption(printStream, "-f <filename>", "File to interpret");
        printOption(printStream, "-s| --", "Start reading commands interactively from console");
        printOption(printStream, "-w", "Launch the interpreter in a GUI window");
        printOption(printStream, "--server <port>", "Start a server accepting telnet connections on <port>");
        printOption(printStream, "--debug-dump-zip", "Compiled interactive expressions to a zip archive");
        printOption(printStream, "--debug-print-expr", "Print generated internal expressions");
        printOption(printStream, "--debug-print-final-expr", "Print expression after any optimizations");
        printOption(printStream, "--debug-error-prints-stack-trace", "Print stack trace with errors");
        printOption(printStream, "--debug-warning-prints-stack-trace", "Print stack trace with warnings");
        printOption(printStream, "--[no-]full-tailcalls", "(Don't) use full tail-calls");
        printOption(printStream, "-C <filename> ...", "Compile named files to Java class files");
        printOption(printStream, "--output-format <format>", "Use <format> when printing top-level output");
        printOption(printStream, "--<language>", "Select source language, one of:");
        String[][] languages = Language.getLanguages();
        for (int i = 0; i < languages.length; i++) {
            printStream.print("   ");
            String[] strArr = languages[i];
            int length = strArr.length - 1;
            for (int i2 = 0; i2 < length; i2++) {
                printStream.print(strArr[i2] + " ");
            }
            if (i == 0) {
                printStream.print("[default]");
            }
            printStream.println();
        }
        printStream.println(" Compilation options, must be specified before -C");
        printOption(printStream, "-d <dirname>", "Directory to place .class files in");
        printOption(printStream, "-P <prefix>", "Prefix to prepand to class names");
        printOption(printStream, "-T <topname>", "name to give to top-level class");
        printOption(printStream, "--main", "Generate an application, with a main method");
        printOption(printStream, "--applet", "Generate an applet");
        printOption(printStream, "--servlet", "Generate a servlet");
        printOption(printStream, "--module-static", "Top-level definitions are by default static");
        ArrayList<String> keys = Compilation.options.keys();
        for (int i3 = 0; i3 < keys.size(); i3++) {
            String str = keys.get(i3);
            printOption(printStream, "--" + str, Compilation.options.getDoc(str));
        }
        printStream.println();
        printStream.println("For more information go to:  http://www.gnu.org/software/kawa/");
    }

    static void checkInitFile() {
        File file;
        Object obj;
        if (homeDirectory == null) {
            String property = System.getProperty("user.home");
            homeDirectory = property;
            if (property != null) {
                obj = new FString(homeDirectory);
                file = new File(homeDirectory, InternalZipConstants.ZIP_FILE_SEPARATOR.equals(System.getProperty("file.separator")) ? ".kawarc.scm" : "kawarc.scm");
            } else {
                file = null;
                obj = Boolean.FALSE;
            }
            Environment.getCurrent().put("home-directory", obj);
            if (file != null && file.exists() && !Shell.runFileOrClass(file.getPath(), true, 0)) {
                System.exit(-1);
            }
        }
    }

    public static void setArgs(String[] strArr, int i) {
        ApplicationMainSupport.setArgs(strArr, i);
    }

    public static void getLanguageFromFilenameExtension(String str) {
        if (previousLanguage == null) {
            Language instanceFromFilenameExtension = Language.getInstanceFromFilenameExtension(str);
            previousLanguage = instanceFromFilenameExtension;
            if (instanceFromFilenameExtension != null) {
                Language.setDefaults(instanceFromFilenameExtension);
                return;
            }
        }
        getLanguage();
    }

    public static void getLanguage() {
        if (previousLanguage == null) {
            Language instance = Language.getInstance((String) null);
            previousLanguage = instance;
            Language.setDefaults(instance);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:338:0x067a, code lost:
        if (r4 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x067c, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:?, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int processArgs(java.lang.String[] r16, int r17, int r18) {
        /*
            r0 = r16
            r1 = r18
            r2 = 0
            r3 = r17
            r4 = 0
        L_0x0008:
            r5 = -1
            if (r3 >= r1) goto L_0x067a
            r6 = r0[r3]
            java.lang.String r7 = "-c"
            boolean r8 = r6.equals(r7)
            r9 = 1
            if (r8 != 0) goto L_0x0633
            java.lang.String r8 = "-e"
            boolean r8 = r6.equals(r8)
            if (r8 == 0) goto L_0x0020
            goto L_0x0633
        L_0x0020:
            java.lang.String r7 = "-f"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0047
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x002f
            bad_option(r6)
        L_0x002f:
            r4 = r0[r3]
            getLanguageFromFilenameExtension(r4)
            int r6 = r3 + 1
            setArgs(r0, r6)
            checkInitFile()
            boolean r4 = kawa.Shell.runFileOrClass(r4, r9, r2)
            if (r4 != 0) goto L_0x0675
            java.lang.System.exit(r5)
            goto L_0x0675
        L_0x0047:
            java.lang.String r7 = "--script"
            boolean r7 = r6.startsWith(r7)
            if (r7 == 0) goto L_0x007d
            r4 = 8
            java.lang.String r4 = r6.substring(r4)
            int r3 = r3 + r9
            int r7 = r4.length()
            if (r7 <= 0) goto L_0x0062
            int r2 = java.lang.Integer.parseInt(r4)     // Catch:{ all -> 0x0061 }
            goto L_0x0062
        L_0x0061:
            r3 = r1
        L_0x0062:
            if (r3 != r1) goto L_0x0067
            bad_option(r6)
        L_0x0067:
            r1 = r0[r3]
            getLanguageFromFilenameExtension(r1)
            int r3 = r3 + r9
            setArgs(r0, r3)
            checkInitFile()
            boolean r0 = kawa.Shell.runFileOrClass(r1, r9, r2)
            if (r0 != 0) goto L_0x007c
            java.lang.System.exit(r5)
        L_0x007c:
            return r5
        L_0x007d:
            java.lang.String r7 = "\\"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x01ac
            int r3 = r3 + r9
            if (r3 != r1) goto L_0x008b
            bad_option(r6)
        L_0x008b:
            r1 = r0[r3]
            gnu.text.SourceMessages r4 = new gnu.text.SourceMessages
            r4.<init>()
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x019f }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x019f }
            r7.<init>(r1)     // Catch:{ all -> 0x019f }
            r6.<init>(r7)     // Catch:{ all -> 0x019f }
            int r7 = r6.read()     // Catch:{ all -> 0x019f }
            r10 = 35
            if (r7 != r10) goto L_0x0161
            java.lang.StringBuffer r10 = new java.lang.StringBuffer     // Catch:{ all -> 0x019f }
            r11 = 100
            r10.<init>(r11)     // Catch:{ all -> 0x019f }
            java.util.Vector r11 = new java.util.Vector     // Catch:{ all -> 0x019f }
            r12 = 10
            r11.<init>(r12)     // Catch:{ all -> 0x019f }
        L_0x00b2:
            r13 = 13
            if (r7 == r12) goto L_0x00bf
            if (r7 == r13) goto L_0x00bf
            if (r7 < 0) goto L_0x00bf
            int r7 = r6.read()     // Catch:{ all -> 0x019f }
            goto L_0x00b2
        L_0x00bf:
            r7 = 0
        L_0x00c0:
            int r14 = r6.read()     // Catch:{ all -> 0x019f }
            r15 = 39
            if (r14 >= 0) goto L_0x00e4
            java.io.PrintStream r9 = java.lang.System.err     // Catch:{ all -> 0x019f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x019f }
            r2.<init>()     // Catch:{ all -> 0x019f }
            java.lang.String r8 = "unexpected end-of-file processing argument line for: '"
            r2.append(r8)     // Catch:{ all -> 0x019f }
            r2.append(r1)     // Catch:{ all -> 0x019f }
            r2.append(r15)     // Catch:{ all -> 0x019f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x019f }
            r9.println(r2)     // Catch:{ all -> 0x019f }
            java.lang.System.exit(r5)     // Catch:{ all -> 0x019f }
        L_0x00e4:
            r2 = 92
            if (r7 != 0) goto L_0x0153
            if (r14 == r2) goto L_0x014e
            if (r14 == r15) goto L_0x014e
            r2 = 34
            if (r14 != r2) goto L_0x00f1
            goto L_0x014e
        L_0x00f1:
            if (r14 == r12) goto L_0x0110
            if (r14 != r13) goto L_0x00f6
            goto L_0x0110
        L_0x00f6:
            r2 = 32
            if (r14 == r2) goto L_0x00fe
            r2 = 9
            if (r14 != r2) goto L_0x015c
        L_0x00fe:
            int r2 = r10.length()     // Catch:{ all -> 0x019f }
            if (r2 <= 0) goto L_0x014f
            java.lang.String r2 = r10.toString()     // Catch:{ all -> 0x019f }
            r11.addElement(r2)     // Catch:{ all -> 0x019f }
            r2 = 0
            r10.setLength(r2)     // Catch:{ all -> 0x019f }
            goto L_0x014f
        L_0x0110:
            int r2 = r10.length()     // Catch:{ all -> 0x019f }
            if (r2 <= 0) goto L_0x011d
            java.lang.String r2 = r10.toString()     // Catch:{ all -> 0x019f }
            r11.addElement(r2)     // Catch:{ all -> 0x019f }
        L_0x011d:
            int r2 = r11.size()     // Catch:{ all -> 0x019f }
            if (r2 <= 0) goto L_0x0161
            java.lang.String[] r7 = new java.lang.String[r2]     // Catch:{ all -> 0x019f }
            r11.copyInto(r7)     // Catch:{ all -> 0x019f }
            r8 = 0
            int r7 = processArgs(r7, r8, r2)     // Catch:{ all -> 0x019f }
            if (r7 < 0) goto L_0x0161
            if (r7 >= r2) goto L_0x0161
            java.io.PrintStream r8 = java.lang.System.err     // Catch:{ all -> 0x019f }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x019f }
            r9.<init>()     // Catch:{ all -> 0x019f }
            java.lang.String r10 = ""
            r9.append(r10)     // Catch:{ all -> 0x019f }
            int r2 = r2 - r7
            r9.append(r2)     // Catch:{ all -> 0x019f }
            java.lang.String r2 = " unused meta args"
            r9.append(r2)     // Catch:{ all -> 0x019f }
            java.lang.String r2 = r9.toString()     // Catch:{ all -> 0x019f }
            r8.println(r2)     // Catch:{ all -> 0x019f }
            goto L_0x0161
        L_0x014e:
            r7 = r14
        L_0x014f:
            r2 = 0
        L_0x0150:
            r9 = 1
            goto L_0x00c0
        L_0x0153:
            if (r7 != r2) goto L_0x0157
            r7 = 0
            goto L_0x015c
        L_0x0157:
            if (r14 != r7) goto L_0x015c
            r2 = 0
            r7 = 0
            goto L_0x0150
        L_0x015c:
            char r2 = (char) r14     // Catch:{ all -> 0x019f }
            r10.append(r2)     // Catch:{ all -> 0x019f }
            goto L_0x014f
        L_0x0161:
            getLanguageFromFilenameExtension(r1)     // Catch:{ all -> 0x019f }
            gnu.mapping.InPort r12 = gnu.mapping.InPort.openFile(r6, r1)     // Catch:{ all -> 0x019f }
            r1 = 1
            int r3 = r3 + r1
            setArgs(r0, r3)     // Catch:{ all -> 0x019f }
            checkInitFile()     // Catch:{ all -> 0x019f }
            gnu.mapping.OutPort r0 = gnu.mapping.OutPort.errDefault()     // Catch:{ all -> 0x019f }
            gnu.expr.Language r10 = gnu.expr.Language.getDefaultLanguage()     // Catch:{ all -> 0x019f }
            gnu.mapping.Environment r11 = gnu.mapping.Environment.getCurrent()     // Catch:{ all -> 0x019f }
            gnu.mapping.OutPort r13 = gnu.mapping.OutPort.outDefault()     // Catch:{ all -> 0x019f }
            r14 = 0
            r15 = r4
            java.lang.Throwable r1 = kawa.Shell.run((gnu.expr.Language) r10, (gnu.mapping.Environment) r11, (gnu.mapping.InPort) r12, (gnu.mapping.OutPort) r13, (gnu.mapping.OutPort) r14, (gnu.text.SourceMessages) r15)     // Catch:{ all -> 0x019f }
            r2 = 20
            r4.printAll((java.io.PrintWriter) r0, (int) r2)     // Catch:{ all -> 0x019f }
            if (r1 == 0) goto L_0x01ab
            boolean r0 = r1 instanceof gnu.text.SyntaxException     // Catch:{ all -> 0x019f }
            if (r0 == 0) goto L_0x019e
            r0 = r1
            gnu.text.SyntaxException r0 = (gnu.text.SyntaxException) r0     // Catch:{ all -> 0x019f }
            gnu.text.SourceMessages r0 = r0.getMessages()     // Catch:{ all -> 0x019f }
            if (r0 != r4) goto L_0x019e
            r2 = 1
            java.lang.System.exit(r2)     // Catch:{ all -> 0x019f }
        L_0x019e:
            throw r1     // Catch:{ all -> 0x019f }
        L_0x019f:
            r0 = move-exception
            gnu.mapping.OutPort r1 = gnu.mapping.OutPort.errDefault()
            kawa.Shell.printError(r0, r4, r1)
            r1 = 1
            java.lang.System.exit(r1)
        L_0x01ab:
            return r5
        L_0x01ac:
            java.lang.String r2 = "-s"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x061c
            java.lang.String r2 = "--"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x01be
            goto L_0x061c
        L_0x01be:
            java.lang.String r2 = "-w"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x01d7
            int r3 = r3 + 1
            getLanguage()
            setArgs(r0, r3)
            checkInitFile()
            startGuiConsole()
        L_0x01d4:
            r2 = 0
            goto L_0x0675
        L_0x01d7:
            java.lang.String r2 = "-d"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x01f2
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x01e6
            bad_option(r6)
        L_0x01e6:
            gnu.expr.ModuleManager r2 = gnu.expr.ModuleManager.getInstance()
            r5 = r0[r3]
            r2.setCompilationDirectory(r5)
        L_0x01ef:
            r2 = 0
            goto L_0x0676
        L_0x01f2:
            java.lang.String r2 = "--target"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x059e
            java.lang.String r2 = "target"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0204
            goto L_0x059e
        L_0x0204:
            java.lang.String r2 = "-P"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0218
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x0213
            bad_option(r6)
        L_0x0213:
            r2 = r0[r3]
            gnu.expr.Compilation.classPrefixDefault = r2
            goto L_0x01ef
        L_0x0218:
            java.lang.String r2 = "-T"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x022c
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x0227
            bad_option(r6)
        L_0x0227:
            r2 = r0[r3]
            compilationTopname = r2
            goto L_0x01ef
        L_0x022c:
            java.lang.String r2 = "-C"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x023f
            r2 = 1
            int r3 = r3 + r2
            if (r3 != r1) goto L_0x023b
            bad_option(r6)
        L_0x023b:
            compileFiles(r0, r3, r1)
            return r5
        L_0x023f:
            java.lang.String r2 = "--output-format"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x058f
            java.lang.String r2 = "--format"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0251
            goto L_0x058f
        L_0x0251:
            java.lang.String r2 = "--connect"
            boolean r2 = r6.equals(r2)
            r7 = 0
            java.lang.String r8 = "-"
            if (r2 == 0) goto L_0x02b0
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x0263
            bad_option(r6)
        L_0x0263:
            r2 = r0[r3]
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x026d
            r5 = 0
            goto L_0x0279
        L_0x026d:
            r2 = r0[r3]     // Catch:{ NumberFormatException -> 0x0274 }
            int r5 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0274 }
            goto L_0x0279
        L_0x0274:
            java.lang.String r2 = "--connect port#"
            bad_option(r2)
        L_0x0279:
            java.net.Socket r2 = new java.net.Socket     // Catch:{ IOException -> 0x02a0 }
            java.net.InetAddress r6 = java.net.InetAddress.getByName(r7)     // Catch:{ IOException -> 0x02a0 }
            r2.<init>(r6, r5)     // Catch:{ IOException -> 0x02a0 }
            kawa.Telnet r5 = new kawa.Telnet     // Catch:{ IOException -> 0x02a0 }
            r6 = 1
            r5.<init>(r2, r6)     // Catch:{ IOException -> 0x02a0 }
            kawa.TelnetInputStream r2 = r5.getInputStream()     // Catch:{ IOException -> 0x02a0 }
            kawa.TelnetOutputStream r5 = r5.getOutputStream()     // Catch:{ IOException -> 0x02a0 }
            java.io.PrintStream r7 = new java.io.PrintStream     // Catch:{ IOException -> 0x02a0 }
            r7.<init>(r5, r6)     // Catch:{ IOException -> 0x02a0 }
            java.lang.System.setIn(r2)     // Catch:{ IOException -> 0x02a0 }
            java.lang.System.setOut(r7)     // Catch:{ IOException -> 0x02a0 }
            java.lang.System.setErr(r7)     // Catch:{ IOException -> 0x02a0 }
            goto L_0x01ef
        L_0x02a0:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.err
            r0.printStackTrace(r1)
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x02b0:
            java.lang.String r2 = "--server"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0341
            getLanguage()
            r2 = 1
            int r3 = r3 + r2
            if (r3 != r1) goto L_0x02c2
            bad_option(r6)
        L_0x02c2:
            r1 = r0[r3]
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x02cc
            r2 = 0
            goto L_0x02d9
        L_0x02cc:
            r0 = r0[r3]     // Catch:{ NumberFormatException -> 0x02d3 }
            int r2 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x02d3 }
            goto L_0x02d9
        L_0x02d3:
            java.lang.String r0 = "--server port#"
            bad_option(r0)
            r2 = -1
        L_0x02d9:
            java.net.ServerSocket r0 = new java.net.ServerSocket     // Catch:{ IOException -> 0x0336 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x0336 }
            int r1 = r0.getLocalPort()     // Catch:{ IOException -> 0x0336 }
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ IOException -> 0x0336 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0336 }
            r3.<init>()     // Catch:{ IOException -> 0x0336 }
            java.lang.String r4 = "Listening on port "
            r3.append(r4)     // Catch:{ IOException -> 0x0336 }
            r3.append(r1)     // Catch:{ IOException -> 0x0336 }
            java.lang.String r1 = r3.toString()     // Catch:{ IOException -> 0x0336 }
            r2.println(r1)     // Catch:{ IOException -> 0x0336 }
        L_0x02f8:
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ IOException -> 0x0336 }
            java.lang.String r2 = "waiting ... "
            r1.print(r2)     // Catch:{ IOException -> 0x0336 }
            java.io.PrintStream r1 = java.lang.System.err     // Catch:{ IOException -> 0x0336 }
            r1.flush()     // Catch:{ IOException -> 0x0336 }
            java.net.Socket r1 = r0.accept()     // Catch:{ IOException -> 0x0336 }
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ IOException -> 0x0336 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0336 }
            r3.<init>()     // Catch:{ IOException -> 0x0336 }
            java.lang.String r4 = "got connection from "
            r3.append(r4)     // Catch:{ IOException -> 0x0336 }
            java.net.InetAddress r4 = r1.getInetAddress()     // Catch:{ IOException -> 0x0336 }
            r3.append(r4)     // Catch:{ IOException -> 0x0336 }
            java.lang.String r4 = " port:"
            r3.append(r4)     // Catch:{ IOException -> 0x0336 }
            int r4 = r1.getPort()     // Catch:{ IOException -> 0x0336 }
            r3.append(r4)     // Catch:{ IOException -> 0x0336 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0336 }
            r2.println(r3)     // Catch:{ IOException -> 0x0336 }
            gnu.expr.Language r2 = gnu.expr.Language.getDefaultLanguage()     // Catch:{ IOException -> 0x0336 }
            kawa.TelnetRepl.serve(r2, r1)     // Catch:{ IOException -> 0x0336 }
            goto L_0x02f8
        L_0x0336:
            r0 = move-exception
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0341:
            java.lang.String r2 = "--http-auto-handler"
            boolean r2 = r6.equals(r2)
            java.lang.String r8 = "kawa: HttpServer classes not found"
            if (r2 == 0) goto L_0x035c
            int r3 = r3 + 2
            if (r3 < r1) goto L_0x0352
            bad_option(r6)
        L_0x0352:
            java.io.PrintStream r2 = java.lang.System.err
            r2.println(r8)
            java.lang.System.exit(r5)
            goto L_0x01ef
        L_0x035c:
            java.lang.String r2 = "--http-start"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0377
            int r3 = r3 + 1
            if (r3 < r1) goto L_0x036d
            java.lang.String r2 = "missing httpd port argument"
            bad_option(r2)
        L_0x036d:
            java.io.PrintStream r2 = java.lang.System.err
            r2.println(r8)
            java.lang.System.exit(r5)
            goto L_0x01ef
        L_0x0377:
            java.lang.String r2 = "--main"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0384
            r2 = 1
            gnu.expr.Compilation.generateMainDefault = r2
            goto L_0x01ef
        L_0x0384:
            java.lang.String r2 = "--applet"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0394
            int r2 = defaultParseOptions
            r2 = r2 | 16
            defaultParseOptions = r2
            goto L_0x01ef
        L_0x0394:
            java.lang.String r2 = "--servlet"
            boolean r2 = r6.equals(r2)
            r8 = 2
            if (r2 == 0) goto L_0x03a8
            int r2 = defaultParseOptions
            r5 = 32
            r2 = r2 | r5
            defaultParseOptions = r2
            gnu.kawa.servlet.HttpRequestContext.importServletDefinitions = r8
            goto L_0x01ef
        L_0x03a8:
            java.lang.String r2 = "--debug-dump-zip"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x03b6
            java.lang.String r2 = "kawa-zip-dump-"
            gnu.expr.ModuleExp.dumpZipPrefix = r2
            goto L_0x01ef
        L_0x03b6:
            java.lang.String r2 = "--debug-print-expr"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x03c3
            r2 = 1
            gnu.expr.Compilation.debugPrintExpr = r2
            goto L_0x01ef
        L_0x03c3:
            r2 = 1
            java.lang.String r9 = "--debug-print-final-expr"
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto L_0x03d0
            gnu.expr.Compilation.debugPrintFinalExpr = r2
            goto L_0x01ef
        L_0x03d0:
            java.lang.String r9 = "--debug-error-prints-stack-trace"
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto L_0x03dc
            gnu.text.SourceMessages.debugStackTraceOnError = r2
            goto L_0x01ef
        L_0x03dc:
            java.lang.String r9 = "--debug-warning-prints-stack-trace"
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto L_0x03e8
            gnu.text.SourceMessages.debugStackTraceOnWarning = r2
            goto L_0x01ef
        L_0x03e8:
            java.lang.String r2 = "--module-nonstatic"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x058a
            java.lang.String r2 = "--no-module-static"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x03fa
            goto L_0x058a
        L_0x03fa:
            java.lang.String r2 = "--module-static"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0407
            r2 = 1
            gnu.expr.Compilation.moduleStatic = r2
            goto L_0x01ef
        L_0x0407:
            java.lang.String r2 = "--module-static-run"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0413
            gnu.expr.Compilation.moduleStatic = r8
            goto L_0x01ef
        L_0x0413:
            java.lang.String r2 = "--no-inline"
            boolean r2 = r6.equals(r2)
            if (r2 != 0) goto L_0x0585
            java.lang.String r2 = "--inline=none"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0425
            goto L_0x0585
        L_0x0425:
            java.lang.String r2 = "--no-console"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0432
            r2 = 1
            noConsole = r2
            goto L_0x01ef
        L_0x0432:
            r2 = 1
            java.lang.String r9 = "--inline"
            boolean r9 = r6.equals(r9)
            if (r9 == 0) goto L_0x043f
            gnu.expr.Compilation.inlineOk = r2
            goto L_0x01ef
        L_0x043f:
            java.lang.String r2 = "--cps"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x044c
            r2 = 4
            gnu.expr.Compilation.defaultCallConvention = r2
            goto L_0x01ef
        L_0x044c:
            java.lang.String r2 = "--full-tailcalls"
            boolean r2 = r6.equals(r2)
            r9 = 3
            if (r2 == 0) goto L_0x0459
            gnu.expr.Compilation.defaultCallConvention = r9
            goto L_0x01ef
        L_0x0459:
            java.lang.String r2 = "--no-full-tailcalls"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0466
            r2 = 1
            gnu.expr.Compilation.defaultCallConvention = r2
            goto L_0x01ef
        L_0x0466:
            r2 = 1
            java.lang.String r10 = "--pedantic"
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto L_0x0473
            gnu.expr.Language.requirePedantic = r2
            goto L_0x01ef
        L_0x0473:
            java.lang.String r2 = "--help"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0486
            java.io.PrintStream r2 = java.lang.System.out
            printOptions(r2)
            r2 = 0
            java.lang.System.exit(r2)
            goto L_0x0676
        L_0x0486:
            r2 = 0
            java.lang.String r10 = "--author"
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto L_0x049b
            java.io.PrintStream r5 = java.lang.System.out
            java.lang.String r6 = "Per Bothner <per@bothner.com>"
            r5.println(r6)
            java.lang.System.exit(r2)
            goto L_0x0676
        L_0x049b:
            java.lang.String r2 = "--version"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x04c1
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r4 = "Kawa "
            r2.print(r4)
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r4 = kawa.Version.getVersion()
            r2.print(r4)
            java.io.PrintStream r2 = java.lang.System.out
            r2.println()
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.String r4 = "Copyright (C) 2009 Per Bothner"
            r2.println(r4)
            goto L_0x01d4
        L_0x04c1:
            int r2 = r6.length()
            if (r2 <= 0) goto L_0x057d
            r2 = 0
            char r10 = r6.charAt(r2)
            r11 = 45
            if (r10 != r11) goto L_0x057d
            int r10 = r6.length()
            if (r10 <= r8) goto L_0x04ea
            char r10 = r6.charAt(r2)
            if (r10 != r11) goto L_0x04ea
            r2 = 1
            char r10 = r6.charAt(r2)
            if (r10 != r11) goto L_0x04e4
            goto L_0x04e5
        L_0x04e4:
            r8 = 1
        L_0x04e5:
            java.lang.String r2 = r6.substring(r8)
            goto L_0x04eb
        L_0x04ea:
            r2 = r6
        L_0x04eb:
            gnu.expr.Language r8 = gnu.expr.Language.getInstance(r2)
            if (r8 == 0) goto L_0x0500
            gnu.expr.Language r2 = previousLanguage
            if (r2 != 0) goto L_0x04f9
            gnu.expr.Language.setDefaults(r8)
            goto L_0x04fc
        L_0x04f9:
            gnu.expr.Language.setCurrentLanguage(r8)
        L_0x04fc:
            previousLanguage = r8
            goto L_0x01ef
        L_0x0500:
            java.lang.String r8 = "="
            int r8 = r2.indexOf(r8)
            if (r8 >= 0) goto L_0x0509
            goto L_0x0514
        L_0x0509:
            int r7 = r8 + 1
            java.lang.String r7 = r2.substring(r7)
            r10 = 0
            java.lang.String r2 = r2.substring(r10, r8)
        L_0x0514:
            java.lang.String r8 = "no-"
            boolean r8 = r2.startsWith(r8)
            if (r8 == 0) goto L_0x0524
            int r8 = r2.length()
            if (r8 <= r9) goto L_0x0524
            r8 = 1
            goto L_0x0525
        L_0x0524:
            r8 = 0
        L_0x0525:
            if (r7 != 0) goto L_0x052f
            if (r8 == 0) goto L_0x052f
            java.lang.String r2 = r2.substring(r9)
            java.lang.String r7 = "no"
        L_0x052f:
            gnu.text.Options r9 = gnu.expr.Compilation.options
            java.lang.String r2 = r9.set((java.lang.String) r2, (java.lang.String) r7)
            if (r2 == 0) goto L_0x01ef
            java.lang.String r9 = "unknown option name"
            if (r8 == 0) goto L_0x0553
            if (r2 != r9) goto L_0x0553
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r8 = "both '--no-' prefix and '="
            r2.append(r8)
            r2.append(r7)
            java.lang.String r7 = "' specified"
            r2.append(r7)
            java.lang.String r2 = r2.toString()
        L_0x0553:
            if (r2 != r9) goto L_0x055a
            bad_option(r6)
            goto L_0x01ef
        L_0x055a:
            java.io.PrintStream r7 = java.lang.System.err
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "kawa: bad option '"
            r8.append(r9)
            r8.append(r6)
            java.lang.String r6 = "': "
            r8.append(r6)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r7.println(r2)
            java.lang.System.exit(r5)
            goto L_0x01ef
        L_0x057d:
            boolean r2 = gnu.expr.ApplicationMainSupport.processSetProperty(r6)
            if (r2 != 0) goto L_0x01ef
            goto L_0x067a
        L_0x0585:
            r2 = 0
            gnu.expr.Compilation.inlineOk = r2
            goto L_0x0676
        L_0x058a:
            r2 = 0
            gnu.expr.Compilation.moduleStatic = r5
            goto L_0x0676
        L_0x058f:
            r2 = 0
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x0597
            bad_option(r6)
        L_0x0597:
            r5 = r0[r3]
            kawa.Shell.setDefaultFormat(r5)
            goto L_0x0676
        L_0x059e:
            r2 = 0
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x05a6
            bad_option(r6)
        L_0x05a6:
            r5 = r0[r3]
            java.lang.String r6 = "7"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x05b4
            r6 = 3342336(0x330000, float:4.68361E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r6
        L_0x05b4:
            java.lang.String r6 = "6"
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x0617
            java.lang.String r6 = "1.6"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x05c5
            goto L_0x0617
        L_0x05c5:
            java.lang.String r6 = "5"
            boolean r6 = r5.equals(r6)
            if (r6 != 0) goto L_0x0612
            java.lang.String r6 = "1.5"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x05d6
            goto L_0x0612
        L_0x05d6:
            java.lang.String r6 = "1.4"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x05e4
            r5 = 3145728(0x300000, float:4.408104E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x05e4:
            java.lang.String r6 = "1.3"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x05f2
            r5 = 3080192(0x2f0000, float:4.316268E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x05f2:
            java.lang.String r6 = "1.2"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x0600
            r5 = 3014656(0x2e0000, float:4.224433E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x0600:
            java.lang.String r6 = "1.1"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x060e
            r5 = 2949123(0x2d0003, float:4.132602E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x060e:
            bad_option(r5)
            goto L_0x0676
        L_0x0612:
            r5 = 3211264(0x310000, float:4.49994E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x0617:
            r5 = 3276800(0x320000, float:4.591775E-39)
            gnu.expr.Compilation.defaultClassFileVersion = r5
            goto L_0x0676
        L_0x061c:
            r1 = 1
            int r3 = r3 + r1
            getLanguage()
            setArgs(r0, r3)
            checkInitFile()
            gnu.expr.Language r0 = gnu.expr.Language.getDefaultLanguage()
            gnu.mapping.Environment r1 = gnu.mapping.Environment.getCurrent()
            kawa.Shell.run(r0, r1)
            return r5
        L_0x0633:
            int r3 = r3 + 1
            if (r3 != r1) goto L_0x063a
            bad_option(r6)
        L_0x063a:
            getLanguage()
            int r4 = r3 + 1
            setArgs(r0, r4)
            boolean r4 = r6.equals(r7)
            if (r4 == 0) goto L_0x064b
            checkInitFile()
        L_0x064b:
            gnu.expr.Language r6 = gnu.expr.Language.getDefaultLanguage()
            gnu.text.SourceMessages r4 = new gnu.text.SourceMessages
            r4.<init>()
            gnu.mapping.Environment r7 = gnu.mapping.Environment.getCurrent()
            gnu.mapping.CharArrayInPort r8 = new gnu.mapping.CharArrayInPort
            r9 = r0[r3]
            r8.<init>((java.lang.String) r9)
            gnu.mapping.OutPort r9 = gnu.mapping.OutPort.outDefault()
            r10 = 0
            r11 = r4
            java.lang.Throwable r6 = kawa.Shell.run((gnu.expr.Language) r6, (gnu.mapping.Environment) r7, (gnu.mapping.InPort) r8, (gnu.mapping.OutPort) r9, (gnu.mapping.OutPort) r10, (gnu.text.SourceMessages) r11)
            if (r6 == 0) goto L_0x0675
            gnu.mapping.OutPort r7 = gnu.mapping.OutPort.errDefault()
            kawa.Shell.printError(r6, r4, r7)
            java.lang.System.exit(r5)
        L_0x0675:
            r4 = 1
        L_0x0676:
            r5 = 1
            int r3 = r3 + r5
            goto L_0x0008
        L_0x067a:
            if (r4 == 0) goto L_0x067d
            r3 = -1
        L_0x067d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.repl.processArgs(java.lang.String[], int, int):int");
    }

    public static void compileFiles(String[] strArr, int i, int i2) {
        ModuleManager instance = ModuleManager.getInstance();
        int i3 = i2 - i;
        Compilation[] compilationArr = new Compilation[i3];
        ModuleInfo[] moduleInfoArr = new ModuleInfo[i3];
        SourceMessages sourceMessages = new SourceMessages();
        for (int i4 = i; i4 < i2; i4++) {
            String str = strArr[i4];
            getLanguageFromFilenameExtension(str);
            try {
                Compilation parse = Language.getDefaultLanguage().parse(InPort.openFile(str), sourceMessages, defaultParseOptions);
                String str2 = compilationTopname;
                if (str2 != null) {
                    ClassType classType = new ClassType(Compilation.mangleNameIfNeeded(str2));
                    ModuleExp module = parse.getModule();
                    module.setType(classType);
                    module.setName(compilationTopname);
                    parse.mainClass = classType;
                }
                int i5 = i4 - i;
                moduleInfoArr[i5] = instance.find(parse);
                compilationArr[i5] = parse;
            } catch (FileNotFoundException e) {
                System.err.println(e);
                System.exit(-1);
            } catch (Throwable th) {
                if (!(th instanceof SyntaxException) || th.getMessages() != sourceMessages) {
                    internalError(th, (Compilation) null, str);
                }
            }
            if (sourceMessages.seenErrorsOrWarnings()) {
                PrintStream printStream = System.err;
                printStream.println("(compiling " + str + ')');
                if (sourceMessages.checkErrors(System.err, 20)) {
                    System.exit(1);
                }
            }
        }
        for (int i6 = i; i6 < i2; i6++) {
            String str3 = strArr[i6];
            int i7 = i6 - i;
            Compilation compilation = compilationArr[i7];
            try {
                PrintStream printStream2 = System.err;
                printStream2.println("(compiling " + str3 + " to " + compilation.mainClass.getName() + ')');
                moduleInfoArr[i7].loadByStages(14);
                boolean seenErrors = sourceMessages.seenErrors();
                sourceMessages.checkErrors(System.err, 50);
                if (seenErrors) {
                    System.exit(-1);
                }
                compilationArr[i7] = compilation;
                boolean seenErrors2 = sourceMessages.seenErrors();
                sourceMessages.checkErrors(System.err, 50);
                if (seenErrors2) {
                    System.exit(-1);
                }
            } catch (Throwable th2) {
                internalError(th2, compilation, str3);
            }
        }
    }

    static void internalError(Throwable th, Compilation compilation, Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        if (compilation != null) {
            String fileName = compilation.getFileName();
            int lineNumber = compilation.getLineNumber();
            if (fileName != null && lineNumber > 0) {
                stringBuffer.append(fileName);
                stringBuffer.append(':');
                stringBuffer.append(lineNumber);
                stringBuffer.append(": ");
            }
        }
        stringBuffer.append("internal error while compiling ");
        stringBuffer.append(obj);
        System.err.println(stringBuffer.toString());
        th.printStackTrace(System.err);
        System.exit(-1);
    }

    public static void main(String[] strArr) {
        try {
            int processArgs = processArgs(strArr, 0, strArr.length);
            if (processArgs >= 0) {
                if (processArgs < strArr.length) {
                    String str = strArr[processArgs];
                    getLanguageFromFilenameExtension(str);
                    setArgs(strArr, processArgs + 1);
                    checkInitFile();
                    Shell.runFileOrClass(str, false, 0);
                } else {
                    getLanguage();
                    setArgs(strArr, processArgs);
                    checkInitFile();
                    if (shouldUseGuiConsole()) {
                        startGuiConsole();
                    } else if (!Shell.run(Language.getDefaultLanguage(), Environment.getCurrent())) {
                        System.exit(-1);
                    }
                }
                if (!shutdownRegistered) {
                    OutPort.runCleanups();
                }
                ModuleBody.exitDecrement();
            }
        } finally {
            if (!shutdownRegistered) {
                OutPort.runCleanups();
            }
            ModuleBody.exitDecrement();
        }
    }

    public static boolean shouldUseGuiConsole() {
        if (noConsole) {
            return true;
        }
        try {
            return Class.forName("java.lang.System").getMethod("console", new Class[0]).invoke(new Object[0], new Object[0]) == null;
        } catch (Throwable unused) {
        }
    }

    private static void startGuiConsole() {
        try {
            Class.forName("kawa.GuiConsole").newInstance();
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("failed to create Kawa window: " + e);
            System.exit(-1);
        }
    }
}
