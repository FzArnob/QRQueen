package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_autoload extends Syntax {
    public static final define_autoload define_autoload = new define_autoload("define-autoload", false);
    public static final define_autoload define_autoloads_from_file = new define_autoload("define-autoloads-from-file", true);
    boolean fromFile;

    public Expression rewriteForm(Pair pair, Translator translator) {
        return null;
    }

    public define_autoload(String str, boolean z) {
        super(str);
        this.fromFile = z;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeExp, Translator translator) {
        if (!(pair.getCdr() instanceof Pair)) {
            return super.scanForDefinitions(pair, vector, scopeExp, translator);
        }
        Pair pair2 = (Pair) pair.getCdr();
        if (this.fromFile) {
            while (pair2.getCar() instanceof CharSequence) {
                if (!scanFile(pair2.getCar().toString(), scopeExp, translator)) {
                    return false;
                }
                Object cdr = pair2.getCdr();
                if (cdr == LList.Empty) {
                    return true;
                }
                if (!(cdr instanceof Pair)) {
                    break;
                }
                pair2 = (Pair) pair2.getCdr();
            }
            translator.syntaxError("invalid syntax for define-autoloads-from-file");
            return false;
        }
        Object car = pair2.getCar();
        if (pair2.getCdr() instanceof Pair) {
            return process(car, ((Pair) pair2.getCdr()).getCar(), vector, scopeExp, translator);
        }
        translator.syntaxError("invalid syntax for define-autoload");
        return false;
    }

    public boolean scanFile(String str, ScopeExp scopeExp, Translator translator) {
        str.endsWith(".el");
        File file = new File(str);
        if (!file.isAbsolute()) {
            file = new File(new File(translator.getFileName()).getParent(), str);
        }
        String path = file.getPath();
        int lastIndexOf = path.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String substring = path.substring(lastIndexOf);
            Language instance = Language.getInstance(substring);
            if (instance == null) {
                translator.syntaxError("unknown extension for " + path);
                return true;
            }
            String str2 = translator.classPrefix;
            String substring2 = str.substring(0, str.length() - substring.length());
            while (substring2.startsWith("../")) {
                int lastIndexOf2 = str2.lastIndexOf(46, str2.length() - 2);
                if (lastIndexOf2 < 0) {
                    translator.syntaxError("cannot use relative filename \"" + str + "\" with simple prefix \"" + str2 + "\"");
                    return false;
                }
                str2 = str2.substring(0, lastIndexOf2 + 1);
                substring2 = substring2.substring(3);
            }
            try {
                findAutoloadComments((LispReader) instance.getLexer(InPort.openFile(path), translator.getMessages()), (str2 + substring2).replace('/', '.'), scopeExp, translator);
            } catch (Exception e) {
                translator.syntaxError("error reading " + path + ": " + e);
            }
        }
        return true;
    }

    public static void findAutoloadComments(LispReader lispReader, String str, ScopeExp scopeExp, Translator translator) throws IOException, SyntaxException {
        String str2;
        while (true) {
            boolean z = true;
            while (true) {
                int peek = lispReader.peek();
                if (peek >= 0) {
                    if (peek == 10 || peek == 13) {
                        lispReader.read();
                    } else {
                        if (z && peek == 59) {
                            int i = 0;
                            while (i != 14) {
                                peek = lispReader.read();
                                if (peek >= 0) {
                                    if (peek == 10 || peek == 13) {
                                        break;
                                    } else if (i >= 0) {
                                        i = peek == ";;;###autoload".charAt(i) ? i + 1 : -1;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (i > 0) {
                                Object readObject = lispReader.readObject();
                                if (readObject instanceof Pair) {
                                    Pair pair = (Pair) readObject;
                                    Object car = pair.getCar();
                                    AutoloadProcedure autoloadProcedure = null;
                                    if ((car instanceof String ? car.toString() : car instanceof Symbol ? ((Symbol) car).getName() : null) == "defun") {
                                        String obj = ((Pair) pair.getCdr()).getCar().toString();
                                        String str3 = obj;
                                        autoloadProcedure = new AutoloadProcedure(obj, str, translator.getLanguage());
                                        str2 = str3;
                                    } else {
                                        translator.error('w', "unsupported ;;;###autoload followed by: " + pair.getCar());
                                        str2 = null;
                                    }
                                    if (autoloadProcedure != null) {
                                        Declaration define = scopeExp.getDefine(str2, 'w', translator);
                                        QuoteExp quoteExp = new QuoteExp(autoloadProcedure);
                                        define.setFlag(16384);
                                        define.noteValue(quoteExp);
                                        define.setProcedureDecl(true);
                                        define.setType(Compilation.typeProcedure);
                                    }
                                }
                                z = false;
                            }
                        }
                        lispReader.skip();
                        if (peek == 35 && lispReader.peek() == 124) {
                            lispReader.skip();
                            lispReader.readNestedComment('#', '|');
                            z = false;
                        } else {
                            if (!Character.isWhitespace((char) peek) && lispReader.readObject(peek) == Sequence.eofValue) {
                                return;
                            }
                            z = false;
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        r7 = (java.lang.String) r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean process(java.lang.Object r4, java.lang.Object r5, java.util.Vector r6, gnu.expr.ScopeExp r7, kawa.lang.Translator r8) {
        /*
            boolean r0 = r4 instanceof gnu.lists.Pair
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x001e
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            java.lang.Object r0 = r4.getCar()
            boolean r0 = process(r0, r5, r6, r7, r8)
            if (r0 == 0) goto L_0x001d
            java.lang.Object r4 = r4.getCdr()
            boolean r4 = process(r4, r5, r6, r7, r8)
            if (r4 == 0) goto L_0x001d
            r1 = 1
        L_0x001d:
            return r1
        L_0x001e:
            gnu.lists.LList r6 = gnu.lists.LList.Empty
            if (r4 != r6) goto L_0x0023
            return r2
        L_0x0023:
            boolean r6 = r4 instanceof java.lang.String
            if (r6 != 0) goto L_0x002d
            boolean r6 = r4 instanceof gnu.mapping.Symbol
            if (r6 == 0) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            return r1
        L_0x002d:
            java.lang.String r4 = r4.toString()
            r6 = 119(0x77, float:1.67E-43)
            gnu.expr.Declaration r6 = r7.getDefine(r4, r6, r8)
            boolean r7 = r5 instanceof java.lang.String
            if (r7 == 0) goto L_0x005a
            r7 = r5
            java.lang.String r7 = (java.lang.String) r7
            int r0 = r7.length()
            r3 = 2
            if (r0 <= r3) goto L_0x005a
            char r1 = r7.charAt(r1)
            r3 = 60
            if (r1 != r3) goto L_0x005a
            int r0 = r0 - r2
            char r1 = r7.charAt(r0)
            r3 = 62
            if (r1 != r3) goto L_0x005a
            java.lang.String r5 = r7.substring(r2, r0)
        L_0x005a:
            kawa.lang.AutoloadProcedure r7 = new kawa.lang.AutoloadProcedure
            java.lang.String r5 = r5.toString()
            gnu.expr.Language r8 = r8.getLanguage()
            r7.<init>(r4, r5, r8)
            gnu.expr.QuoteExp r4 = new gnu.expr.QuoteExp
            r4.<init>(r7)
            r7 = 16384(0x4000, double:8.0948E-320)
            r6.setFlag(r7)
            r6.noteValue(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.define_autoload.process(java.lang.Object, java.lang.Object, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }
}
