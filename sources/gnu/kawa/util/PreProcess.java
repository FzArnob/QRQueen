package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class PreProcess {
    static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
    static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
    static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
    static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
    static String[] version_features = {"java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer"};
    String filename;
    Hashtable keywords = new Hashtable();
    int lineno;
    byte[] resultBuffer;
    int resultLength;

    /* access modifiers changed from: package-private */
    public void error(String str) {
        PrintStream printStream = System.err;
        printStream.println(this.filename + ':' + this.lineno + ": " + str);
        System.exit(-1);
    }

    public void filter(String str) throws Throwable {
        if (filter(str, new BufferedInputStream(new FileInputStream(str)))) {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(this.resultBuffer, 0, this.resultLength);
            fileOutputStream.close();
            PrintStream printStream = System.err;
            printStream.println("Pre-processed " + str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01dd, code lost:
        if (r4 == 0) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x025f, code lost:
        if ((r15.charAt(3) == 'n') != (r10 == java.lang.Boolean.FALSE)) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0082, code lost:
        if (r3 != 35) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e9, code lost:
        r5 = r20.read();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean filter(java.lang.String r19, java.io.BufferedInputStream r20) throws java.lang.Throwable {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r0.filename = r1
            r2 = 2000(0x7d0, float:2.803E-42)
            byte[] r2 = new byte[r2]
            r3 = 1
            r0.lineno = r3
            r4 = 0
            r7 = 0
            r8 = -1
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0019:
            int r5 = r20.read()
            if (r5 >= 0) goto L_0x0021
            goto L_0x00c8
        L_0x0021:
            int r3 = r7 + 10
            int r6 = r2.length
            if (r3 < r6) goto L_0x002f
            int r3 = r7 * 2
            byte[] r3 = new byte[r3]
            r6 = 0
            java.lang.System.arraycopy(r2, r6, r3, r6, r7)
            r2 = r3
        L_0x002f:
            r3 = 13
            r6 = 10
            if (r5 != r6) goto L_0x0045
            if (r7 <= 0) goto L_0x0045
            int r17 = r7 + -1
            byte r6 = r2[r17]
            if (r6 != r3) goto L_0x0045
            int r6 = r7 + 1
            byte r3 = (byte) r5
            r2[r7] = r3
            r7 = r6
        L_0x0043:
            r3 = 1
            goto L_0x0019
        L_0x0045:
            r6 = 32
            if (r9 < 0) goto L_0x00a2
            if (r8 >= 0) goto L_0x00a2
            if (r10 > 0) goto L_0x00a2
            if (r5 == r3) goto L_0x00a2
            r3 = 10
            if (r5 == r3) goto L_0x00a2
            if (r9 == r11) goto L_0x005b
            if (r5 == r6) goto L_0x00a2
            r3 = 9
            if (r5 == r3) goto L_0x00a2
        L_0x005b:
            r3 = 47
            if (r5 != r3) goto L_0x0089
            r6 = 100
            r3 = r20
            r3.mark(r6)
            int r6 = r20.read()
            r3 = 47
            if (r6 != r3) goto L_0x0070
        L_0x006e:
            r3 = 0
            goto L_0x0085
        L_0x0070:
            r3 = 42
            if (r6 != r3) goto L_0x0084
        L_0x0074:
            int r3 = r20.read()
            r6 = 32
            if (r3 == r6) goto L_0x0074
            r6 = 9
            if (r3 == r6) goto L_0x0074
            r6 = 35
            if (r3 == r6) goto L_0x006e
        L_0x0084:
            r3 = 1
        L_0x0085:
            r20.reset()
            goto L_0x008a
        L_0x0089:
            r3 = 1
        L_0x008a:
            if (r3 == 0) goto L_0x00a0
            int r3 = r7 + 1
            r6 = 47
            r2[r7] = r6
            int r7 = r3 + 1
            r2[r3] = r6
            int r3 = r7 + 1
            r6 = 32
            r2[r7] = r6
            r7 = r3
            r10 = 1
            r13 = 1
            goto L_0x00a2
        L_0x00a0:
            r6 = 32
        L_0x00a2:
            if (r5 == r6) goto L_0x00fa
            r3 = 9
            if (r5 == r3) goto L_0x00fa
            if (r8 >= 0) goto L_0x00fa
            if (r12 <= 0) goto L_0x00f9
            if (r9 == r11) goto L_0x00f9
            r3 = 47
            if (r5 != r3) goto L_0x00f9
            int r5 = r20.read()
            if (r5 >= 0) goto L_0x00b9
            goto L_0x00c8
        L_0x00b9:
            if (r5 == r3) goto L_0x00c2
            int r6 = r7 + 1
            r2[r7] = r3
            r8 = r7
            r7 = r6
            goto L_0x00fa
        L_0x00c2:
            int r5 = r20.read()
            if (r5 >= 0) goto L_0x00e5
        L_0x00c8:
            if (r12 == 0) goto L_0x00e0
            r0.lineno = r14
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "unterminated "
            r1.append(r3)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            r0.error(r1)
        L_0x00e0:
            r0.resultBuffer = r2
            r0.resultLength = r7
            return r13
        L_0x00e5:
            r3 = 32
            if (r5 != r3) goto L_0x00f5
            int r5 = r20.read()
            if (r5 == r3) goto L_0x00f3
            r3 = 9
            if (r5 != r3) goto L_0x00f5
        L_0x00f3:
            r8 = -1
            goto L_0x00f6
        L_0x00f5:
            r8 = r7
        L_0x00f6:
            r10 = -1
            r13 = 1
            goto L_0x00fa
        L_0x00f9:
            r8 = r7
        L_0x00fa:
            byte r3 = (byte) r5
            r2[r7] = r3
            r3 = 1
            int r7 = r7 + r3
            r3 = 13
            if (r5 == r3) goto L_0x011a
            r3 = 10
            if (r5 != r3) goto L_0x0108
            goto L_0x011a
        L_0x0108:
            if (r8 >= 0) goto L_0x0116
            r3 = 9
            if (r5 != r3) goto L_0x0113
            int r11 = r11 + 8
            r3 = r11 & -8
            goto L_0x0115
        L_0x0113:
            int r3 = r11 + 1
        L_0x0115:
            r11 = r3
        L_0x0116:
            r5 = 1
            r6 = 0
            goto L_0x0043
        L_0x011a:
            r3 = r16
            r5 = -1
            r6 = 0
        L_0x011e:
            int r8 = r7 + -1
            if (r3 >= r8) goto L_0x0138
            byte r8 = r2[r3]
            r10 = 32
            if (r8 == r10) goto L_0x0133
            r10 = 9
            if (r8 == r10) goto L_0x0135
            if (r5 >= 0) goto L_0x0131
            r5 = r3
            r6 = r5
            goto L_0x0135
        L_0x0131:
            r6 = r3
            goto L_0x0135
        L_0x0133:
            r10 = 9
        L_0x0135:
            int r3 = r3 + 1
            goto L_0x011e
        L_0x0138:
            int r3 = r6 - r5
            r8 = 4
            if (r3 < r8) goto L_0x0263
            byte r3 = r2[r5]
            r8 = 47
            if (r3 != r8) goto L_0x0263
            int r3 = r5 + 1
            byte r3 = r2[r3]
            r10 = 42
            if (r3 != r10) goto L_0x0263
            int r3 = r6 + -1
            byte r3 = r2[r3]
            if (r3 != r10) goto L_0x0263
            byte r3 = r2[r6]
            if (r3 != r8) goto L_0x0263
            int r5 = r5 + 2
        L_0x0157:
            if (r5 >= r6) goto L_0x0162
            byte r3 = r2[r5]
            r8 = 32
            if (r3 != r8) goto L_0x0164
            int r5 = r5 + 1
            goto L_0x0157
        L_0x0162:
            r8 = 32
        L_0x0164:
            int r6 = r6 + -2
        L_0x0166:
            if (r6 <= r5) goto L_0x0171
            byte r3 = r2[r6]
            if (r3 != r8) goto L_0x0171
            int r6 = r6 + -1
            r8 = 32
            goto L_0x0166
        L_0x0171:
            byte r3 = r2[r5]
            r8 = 35
            if (r3 != r8) goto L_0x0263
            java.lang.String r3 = new java.lang.String
            int r6 = r6 - r5
            r8 = 1
            int r6 = r6 + r8
            java.lang.String r8 = "ISO-8859-1"
            r3.<init>(r2, r5, r6, r8)
            r5 = 32
            int r5 = r3.indexOf(r5)
            int r14 = r0.lineno
            if (r5 <= 0) goto L_0x01a0
            r6 = 0
            java.lang.String r8 = r3.substring(r6, r5)
            java.lang.String r5 = r3.substring(r5)
            java.lang.String r5 = r5.trim()
            java.util.Hashtable r10 = r0.keywords
            java.lang.Object r10 = r10.get(r5)
            r15 = r8
            goto L_0x01a5
        L_0x01a0:
            r6 = 0
            java.lang.String r5 = ""
            r15 = r3
            r10 = 0
        L_0x01a5:
            java.lang.String r8 = "#ifdef"
            boolean r8 = r8.equals(r15)
            if (r8 != 0) goto L_0x021f
            java.lang.String r8 = "#ifndef"
            boolean r8 = r8.equals(r15)
            if (r8 == 0) goto L_0x01b7
            goto L_0x021f
        L_0x01b7:
            java.lang.String r5 = "#else"
            boolean r5 = r5.equals(r15)
            java.lang.String r8 = "unexpected "
            if (r5 == 0) goto L_0x01e3
            if (r12 != 0) goto L_0x01d7
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            r3.append(r15)
            java.lang.String r3 = r3.toString()
            r0.error(r3)
            goto L_0x0264
        L_0x01d7:
            if (r12 != r4) goto L_0x01dd
            r4 = 0
            r9 = -1
            goto L_0x0264
        L_0x01dd:
            if (r4 != 0) goto L_0x024a
        L_0x01df:
            r9 = r11
            r4 = r12
            goto L_0x0264
        L_0x01e3:
            java.lang.String r5 = "#endif"
            boolean r5 = r5.equals(r15)
            if (r5 == 0) goto L_0x020a
            if (r12 != 0) goto L_0x01ff
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            r3.append(r15)
            java.lang.String r3 = r3.toString()
            r0.error(r3)
        L_0x01ff:
            if (r12 != r4) goto L_0x0204
            r4 = 0
            r9 = -1
            goto L_0x0207
        L_0x0204:
            if (r4 <= 0) goto L_0x0207
            r9 = r11
        L_0x0207:
            int r12 = r12 + -1
            goto L_0x0264
        L_0x020a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r8 = "unknown command: "
            r5.append(r8)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r0.error(r3)
            goto L_0x0264
        L_0x021f:
            if (r10 != 0) goto L_0x0246
            java.io.PrintStream r3 = java.lang.System.err
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r10 = ":"
            r8.append(r10)
            int r10 = r0.lineno
            r8.append(r10)
            java.lang.String r10 = ": warning - undefined keyword: "
            r8.append(r10)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            r3.println(r5)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
        L_0x0246:
            int r12 = r12 + 1
            if (r4 <= 0) goto L_0x024c
        L_0x024a:
            r9 = r11
            goto L_0x0264
        L_0x024c:
            r3 = 3
            char r3 = r15.charAt(r3)
            r5 = 110(0x6e, float:1.54E-43)
            if (r3 != r5) goto L_0x0257
            r3 = 1
            goto L_0x0258
        L_0x0257:
            r3 = 0
        L_0x0258:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            if (r10 != r5) goto L_0x025e
            r5 = 1
            goto L_0x025f
        L_0x025e:
            r5 = 0
        L_0x025f:
            if (r3 == r5) goto L_0x0264
            goto L_0x01df
        L_0x0263:
            r6 = 0
        L_0x0264:
            int r3 = r0.lineno
            r5 = 1
            int r3 = r3 + r5
            r0.lineno = r3
            r16 = r7
            r8 = -1
            r10 = 0
            r11 = 0
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.util.PreProcess.filter(java.lang.String, java.io.BufferedInputStream):boolean");
    }

    /* access modifiers changed from: package-private */
    public void handleArg(String str) {
        int i = 0;
        if (str.charAt(0) == '%') {
            String substring = str.substring(1);
            while (true) {
                if (i >= version_features.length) {
                    PrintStream printStream = System.err;
                    printStream.println("Unknown version: " + substring);
                    System.exit(-1);
                }
                if (substring.equals(version_features[i])) {
                    break;
                }
                i += 2;
            }
            String str2 = version_features[i + 1];
            PrintStream printStream2 = System.err;
            printStream2.println("(variant " + substring + " maps to: " + str2 + ")");
            StringTokenizer stringTokenizer = new StringTokenizer(str2);
            while (stringTokenizer.hasMoreTokens()) {
                handleArg(stringTokenizer.nextToken());
            }
        } else if (str.charAt(0) == '+') {
            this.keywords.put(str.substring(1), Boolean.TRUE);
        } else if (str.charAt(0) == '-') {
            int indexOf = str.indexOf(61);
            if (indexOf > 1) {
                String substring2 = str.substring(str.charAt(1) == '-' ? 2 : 1, indexOf);
                String substring3 = str.substring(indexOf + 1);
                Boolean bool = Boolean.FALSE;
                if (substring3.equalsIgnoreCase("true")) {
                    bool = Boolean.TRUE;
                } else if (!substring3.equalsIgnoreCase("false")) {
                    PrintStream printStream3 = System.err;
                    printStream3.println("invalid value " + substring3 + " for " + substring2);
                    System.exit(-1);
                }
                this.keywords.put(substring2, bool);
                return;
            }
            this.keywords.put(str.substring(1), Boolean.FALSE);
        } else {
            try {
                filter(str);
            } catch (Throwable th) {
                PrintStream printStream4 = System.err;
                printStream4.println("caught " + th);
                th.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static void main(String[] strArr) {
        PreProcess preProcess = new PreProcess();
        preProcess.keywords.put("true", Boolean.TRUE);
        preProcess.keywords.put("false", Boolean.FALSE);
        for (String handleArg : strArr) {
            preProcess.handleArg(handleArg);
        }
    }
}
