package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class dump extends ClassFileInput {
    ClassTypeWriter writer;

    dump(InputStream inputStream, ClassTypeWriter classTypeWriter) throws IOException, ClassFormatError {
        super(inputStream);
        this.ctype = new ClassType();
        readFormatVersion();
        readConstants();
        readClassInfo();
        readFields();
        readMethods();
        readAttributes(this.ctype);
        classTypeWriter.print(this.ctype);
        classTypeWriter.flush();
    }

    public ConstantPool readConstants() throws IOException {
        this.ctype.constants = super.readConstants();
        return this.ctype.constants;
    }

    public Attribute readAttribute(String str, int i, AttrContainer attrContainer) throws IOException {
        return super.readAttribute(str, i, attrContainer);
    }

    static int readMagic(InputStream inputStream) throws IOException {
        int read;
        int i = 0;
        for (int i2 = 0; i2 < 4 && (read = inputStream.read()) >= 0; i2++) {
            i = (i << 8) | (read & 255);
        }
        return i;
    }

    public static void process(InputStream inputStream, String str, OutputStream outputStream, int i) throws IOException {
        process(inputStream, str, new ClassTypeWriter((ClassType) null, outputStream, i));
    }

    public static void process(InputStream inputStream, String str, Writer writer2, int i) throws IOException {
        process(inputStream, str, new ClassTypeWriter((ClassType) null, writer2, i));
    }

    public static void process(InputStream inputStream, String str, ClassTypeWriter classTypeWriter) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedInputStream.mark(5);
        int readMagic = readMagic(bufferedInputStream);
        if (readMagic == -889275714) {
            classTypeWriter.print("Reading .class from ");
            classTypeWriter.print(str);
            classTypeWriter.println('.');
            new dump(bufferedInputStream, classTypeWriter);
        } else if (readMagic == 1347093252) {
            bufferedInputStream.reset();
            classTypeWriter.print("Reading classes from archive ");
            classTypeWriter.print(str);
            classTypeWriter.println('.');
            ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (nextEntry.isDirectory()) {
                        classTypeWriter.print("Archive directory: ");
                        classTypeWriter.print(name);
                        classTypeWriter.println('.');
                    } else {
                        classTypeWriter.println();
                        if (readMagic(zipInputStream) == -889275714) {
                            classTypeWriter.print("Reading class member: ");
                            classTypeWriter.print(name);
                            classTypeWriter.println('.');
                            new dump(zipInputStream, classTypeWriter);
                        } else {
                            classTypeWriter.print("Skipping non-class member: ");
                            classTypeWriter.print(name);
                            classTypeWriter.println('.');
                        }
                    }
                } else {
                    System.exit(-1);
                    return;
                }
            }
        } else {
            PrintStream printStream = System.err;
            printStream.println("File " + str + " is not a valid .class file");
            System.exit(-1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c8, code lost:
        if (r5 != false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r5 = r9.getFile();
        r9 = r5.lastIndexOf(33);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d2, code lost:
        if (r9 > 0) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d4, code lost:
        r5 = r5.substring(0, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        new java.net.URL(r5).openConnection().getInputStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fa, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00fb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        java.lang.System.err.print("Error opening zip archive ");
        java.lang.System.err.print(r8);
        java.lang.System.err.println(" not found.");
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0114, code lost:
        if (r0.getCause() != null) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0116, code lost:
        r0.getCause().printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x011d, code lost:
        java.lang.System.exit(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0123, code lost:
        java.lang.System.err.print("File for URL ");
        java.lang.System.err.print(r8);
        java.lang.System.err.println(" not found.");
        java.lang.System.exit(-1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00e5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x0141 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x014a */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fb A[ExcHandler: ZipException (r0v26 'e' java.util.zip.ZipException A[CUSTOM_DECLARE]), Splitter:B:28:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[ExcHandler: FileNotFoundException (unused java.io.FileNotFoundException), SYNTHETIC, Splitter:B:28:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r17) {
        /*
            r1 = r17
            java.lang.String r2 = "jar:"
            int r3 = r1.length
            gnu.bytecode.ClassTypeWriter r4 = new gnu.bytecode.ClassTypeWriter
            java.io.PrintStream r0 = java.lang.System.out
            r5 = 0
            r6 = 0
            r4.<init>((gnu.bytecode.ClassType) r5, (java.io.OutputStream) r0, (int) r6)
            if (r3 != 0) goto L_0x0015
            java.io.PrintStream r0 = java.lang.System.err
            usage(r0)
        L_0x0015:
            r7 = 0
        L_0x0016:
            if (r7 >= r3) goto L_0x01ca
            r8 = r1[r7]
            java.lang.String r0 = "-verbose"
            boolean r0 = r8.equals(r0)
            if (r0 != 0) goto L_0x01c0
            java.lang.String r0 = "--verbose"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x002c
            goto L_0x01c0
        L_0x002c:
            boolean r0 = uriSchemeSpecified(r8)
            java.lang.String r9 = ".class"
            r10 = 46
            java.lang.String r11 = " not found."
            r12 = 47
            if (r0 == 0) goto L_0x0139
            boolean r0 = r8.startsWith(r2)     // Catch:{ IOException -> 0x013f }
            r14 = 33
            if (r0 == 0) goto L_0x00b7
            r15 = 4
            java.lang.String r15 = r8.substring(r15)     // Catch:{ IOException -> 0x013f }
            boolean r16 = uriSchemeSpecified(r15)     // Catch:{ IOException -> 0x013f }
            if (r16 != 0) goto L_0x007e
            int r5 = r15.indexOf(r14)     // Catch:{ IOException -> 0x013f }
            if (r5 < 0) goto L_0x007e
            java.lang.String r8 = r15.substring(r6, r5)     // Catch:{ IOException -> 0x013f }
            java.io.File r13 = new java.io.File     // Catch:{ IOException -> 0x013f }
            r13.<init>(r8)     // Catch:{ IOException -> 0x013f }
            java.net.URI r8 = r13.toURI()     // Catch:{ IOException -> 0x013f }
            java.net.URL r8 = r8.toURL()     // Catch:{ IOException -> 0x013f }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x013f }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x013f }
            r13.<init>()     // Catch:{ IOException -> 0x013f }
            r13.append(r2)     // Catch:{ IOException -> 0x013f }
            r13.append(r8)     // Catch:{ IOException -> 0x013f }
            java.lang.String r5 = r15.substring(r5)     // Catch:{ IOException -> 0x013f }
            r13.append(r5)     // Catch:{ IOException -> 0x013f }
            java.lang.String r8 = r13.toString()     // Catch:{ IOException -> 0x013f }
        L_0x007e:
            java.lang.String r5 = "!/"
            int r5 = r15.indexOf(r5)     // Catch:{ IOException -> 0x013f }
            if (r5 >= 0) goto L_0x00b7
            int r5 = r8.lastIndexOf(r14)     // Catch:{ IOException -> 0x013f }
            if (r5 > 0) goto L_0x008e
            r5 = 0
            goto L_0x00b8
        L_0x008e:
            int r13 = r8.indexOf(r12, r5)     // Catch:{ IOException -> 0x013f }
            if (r13 >= 0) goto L_0x00b7
            int r5 = r5 + 1
            java.lang.String r13 = r8.substring(r5)     // Catch:{ IOException -> 0x013f }
            java.lang.String r10 = r13.replace(r10, r12)     // Catch:{ IOException -> 0x013f }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x013f }
            r13.<init>()     // Catch:{ IOException -> 0x013f }
            java.lang.String r5 = r8.substring(r6, r5)     // Catch:{ IOException -> 0x013f }
            r13.append(r5)     // Catch:{ IOException -> 0x013f }
            r13.append(r12)     // Catch:{ IOException -> 0x013f }
            r13.append(r10)     // Catch:{ IOException -> 0x013f }
            r13.append(r9)     // Catch:{ IOException -> 0x013f }
            java.lang.String r8 = r13.toString()     // Catch:{ IOException -> 0x013f }
        L_0x00b7:
            r5 = r0
        L_0x00b8:
            java.net.URL r9 = new java.net.URL     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            r9.<init>(r8)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            java.net.URLConnection r0 = r9.openConnection()     // Catch:{ ZipException -> 0x00c7, FileNotFoundException -> 0x0123 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ ZipException -> 0x00c7, FileNotFoundException -> 0x0123 }
            goto L_0x01a8
        L_0x00c7:
            r0 = move-exception
            if (r5 == 0) goto L_0x00fa
            java.lang.String r5 = r9.getFile()     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            int r9 = r5.lastIndexOf(r14)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            if (r9 <= 0) goto L_0x00d8
            java.lang.String r5 = r5.substring(r6, r9)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
        L_0x00d8:
            java.net.URL r9 = new java.net.URL     // Catch:{ FileNotFoundException -> 0x00e5, ZipException -> 0x00fb }
            r9.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00e5, ZipException -> 0x00fb }
            java.net.URLConnection r9 = r9.openConnection()     // Catch:{ FileNotFoundException -> 0x00e5, ZipException -> 0x00fb }
            r9.getInputStream()     // Catch:{ FileNotFoundException -> 0x00e5, ZipException -> 0x00fb }
            goto L_0x00fa
        L_0x00e5:
            java.io.PrintStream r9 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            java.lang.String r10 = "Jar File for URL "
            r9.print(r10)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            java.io.PrintStream r9 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            r9.print(r5)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            r5.println(r11)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
            r5 = -1
            java.lang.System.exit(r5)     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
        L_0x00fa:
            throw r0     // Catch:{ FileNotFoundException -> 0x0123, ZipException -> 0x00fb }
        L_0x00fb:
            r0 = move-exception
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            java.lang.String r9 = "Error opening zip archive "
            r5.print(r9)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r5.print(r8)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r5.println(r11)     // Catch:{ IOException -> 0x013f }
            r0.printStackTrace()     // Catch:{ IOException -> 0x013f }
            java.lang.Throwable r5 = r0.getCause()     // Catch:{ IOException -> 0x013f }
            if (r5 == 0) goto L_0x011d
            java.lang.Throwable r0 = r0.getCause()     // Catch:{ IOException -> 0x013f }
            r0.printStackTrace()     // Catch:{ IOException -> 0x013f }
        L_0x011d:
            r5 = -1
            java.lang.System.exit(r5)     // Catch:{ IOException -> 0x013f }
            goto L_0x01a7
        L_0x0123:
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            java.lang.String r5 = "File for URL "
            r0.print(r5)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r0.print(r8)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r0.println(r11)     // Catch:{ IOException -> 0x013f }
            r5 = -1
            java.lang.System.exit(r5)     // Catch:{ IOException -> 0x013f }
            goto L_0x01a7
        L_0x0139:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0141 }
            r0.<init>(r8)     // Catch:{ FileNotFoundException -> 0x0141 }
            goto L_0x01a8
        L_0x013f:
            r0 = move-exception
            goto L_0x01ac
        L_0x0141:
            java.lang.Class r0 = gnu.bytecode.ObjectType.getContextClass(r8)     // Catch:{ NoClassDefFoundError -> 0x0161, all -> 0x014a }
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ NoClassDefFoundError -> 0x0161, all -> 0x014a }
            goto L_0x0165
        L_0x014a:
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            java.lang.String r5 = "File "
            r0.print(r5)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r0.print(r8)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r0 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r0.println(r11)     // Catch:{ IOException -> 0x013f }
            r5 = -1
            java.lang.System.exit(r5)     // Catch:{ IOException -> 0x013f }
            r0 = 0
            goto L_0x0165
        L_0x0161:
            java.lang.ClassLoader r0 = gnu.bytecode.ObjectType.getContextClassLoader()     // Catch:{ IOException -> 0x013f }
        L_0x0165:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x013f }
            r5.<init>()     // Catch:{ IOException -> 0x013f }
            java.lang.String r10 = r8.replace(r10, r12)     // Catch:{ IOException -> 0x013f }
            r5.append(r10)     // Catch:{ IOException -> 0x013f }
            r5.append(r9)     // Catch:{ IOException -> 0x013f }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x013f }
            java.net.URL r0 = r0.getResource(r5)     // Catch:{ all -> 0x018a }
            java.net.URLConnection r5 = r0.openConnection()     // Catch:{ all -> 0x018a }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ all -> 0x018a }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x018a }
            r0 = r5
            goto L_0x01a8
        L_0x018a:
            r0 = move-exception
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            java.lang.String r9 = "Can't find .class file for class "
            r5.print(r9)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r5.print(r8)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            java.lang.String r9 = " - "
            r5.print(r9)     // Catch:{ IOException -> 0x013f }
            java.io.PrintStream r5 = java.lang.System.err     // Catch:{ IOException -> 0x013f }
            r5.println(r0)     // Catch:{ IOException -> 0x013f }
            r5 = -1
            java.lang.System.exit(r5)     // Catch:{ IOException -> 0x013f }
        L_0x01a7:
            r0 = 0
        L_0x01a8:
            process(r0, r8, r4)     // Catch:{ IOException -> 0x013f }
            goto L_0x01c5
        L_0x01ac:
            r0.printStackTrace()
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.String r8 = "caught "
            r5.println(r8)
            java.io.PrintStream r5 = java.lang.System.err
            r5.print(r0)
            r5 = -1
            java.lang.System.exit(r5)
            goto L_0x01c5
        L_0x01c0:
            r0 = 15
            r4.setFlags(r0)
        L_0x01c5:
            int r7 = r7 + 1
            r5 = 0
            goto L_0x0016
        L_0x01ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.dump.main(java.lang.String[]):void");
    }

    static int uriSchemeLength(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == ':') {
                return i;
            }
            if (i != 0) {
                if (!(Character.isLetterOrDigit(charAt) || charAt == '+' || charAt == '-' || charAt == '.')) {
                }
                i++;
            } else if (Character.isLetter(charAt)) {
                i++;
            }
            return -1;
        }
        return -1;
    }

    static boolean uriSchemeSpecified(String str) {
        int uriSchemeLength = uriSchemeLength(str);
        if (uriSchemeLength == 1 && File.separatorChar == '\\') {
            char charAt = str.charAt(0);
            if (charAt >= 'a' && charAt <= 'z') {
                return false;
            }
            if (charAt < 'A' || charAt > 'Z') {
                return true;
            }
            return false;
        } else if (uriSchemeLength > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void usage(PrintStream printStream) {
        printStream.println("Prints and dis-assembles the contents of JVM .class files.");
        printStream.println("Usage: [--verbose] class-or-jar ...");
        printStream.println("where a class-or-jar can be one of:");
        printStream.println("- a fully-qualified class name; or");
        printStream.println("- the name of a .class file, or a URL reference to one; or");
        printStream.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
        printStream.println("If a .jar/.zip archive is named, all its.class file members are printed.");
        printStream.println();
        printStream.println("You can name a single .class member of an archive with a jar: URL,");
        printStream.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
        printStream.println("The jar-spec can be a URL or the name of the .jar file.");
        printStream.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
        System.exit(-1);
    }
}
