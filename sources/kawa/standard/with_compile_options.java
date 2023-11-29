package kawa.standard;

import gnu.expr.Keyword;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Options;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class with_compile_options extends Syntax {
    public static final with_compile_options with_compile_options;

    static {
        with_compile_options with_compile_options2 = new with_compile_options();
        with_compile_options = with_compile_options2;
        with_compile_options2.setName("with-compile-options");
    }

    public void scanForm(Pair pair, ScopeExp scopeExp, Translator translator) {
        Stack stack = new Stack();
        Object options = getOptions(pair.getCdr(), stack, this, translator);
        if (options != LList.Empty) {
            if (options == pair.getCdr()) {
                translator.scanBody(options, scopeExp, false);
                return;
            }
            Pair pair2 = new Pair(stack, translator.scanBody(options, scopeExp, true));
            translator.currentOptions.popOptionValues(stack);
            translator.formStack.add(Translator.makePair(pair, pair.getCar(), pair2));
        }
    }

    public static Object getOptions(Object obj, Stack stack, Syntax syntax, Translator translator) {
        Options options = translator.currentOptions;
        boolean z = false;
        SyntaxForm syntaxForm = null;
        while (true) {
            if (obj instanceof SyntaxForm) {
                syntaxForm = (SyntaxForm) obj;
                obj = syntaxForm.getDatum();
            } else if (!(obj instanceof Pair)) {
                break;
            } else {
                Pair pair = (Pair) obj;
                Object stripSyntax = Translator.stripSyntax(pair.getCar());
                if (!(stripSyntax instanceof Keyword)) {
                    break;
                }
                String name = ((Keyword) stripSyntax).getName();
                z = true;
                Object pushPositionOf = translator.pushPositionOf(pair);
                try {
                    Object obj2 = pair.getCdr();
                    while (obj2 instanceof SyntaxForm) {
                        syntaxForm = (SyntaxForm) obj2;
                        obj2 = syntaxForm.getDatum();
                    }
                    if (!(obj2 instanceof Pair)) {
                        translator.error('e', "keyword " + name + " not followed by value");
                        LList lList = LList.Empty;
                        translator.popPositionOf(pushPositionOf);
                        return lList;
                    }
                    Pair pair2 = (Pair) obj2;
                    Object stripSyntax2 = Translator.stripSyntax(pair2.getCar());
                    Object cdr = pair2.getCdr();
                    Object local = options.getLocal(name);
                    if (options.getInfo(name) == null) {
                        translator.error('w', "unknown compile option: " + name);
                    } else {
                        if (stripSyntax2 instanceof FString) {
                            stripSyntax2 = stripSyntax2.toString();
                        } else if (!(stripSyntax2 instanceof Boolean)) {
                            if (!(stripSyntax2 instanceof Number)) {
                                translator.error('e', "invalid literal value for key " + name);
                                stripSyntax2 = null;
                            }
                        }
                        options.set(name, stripSyntax2, translator.getMessages());
                        if (stack != null) {
                            stack.push(name);
                            stack.push(local);
                            stack.push(stripSyntax2);
                        }
                    }
                    obj = cdr;
                } finally {
                    translator.popPositionOf(pushPositionOf);
                }
            }
        }
        if (!z) {
            translator.error('e', "no option keyword in " + syntax.getName());
        }
        return Translator.wrapSyntax(obj, syntaxForm);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003a A[Catch:{ all -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0037 A[Catch:{ all -> 0x004f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression rewriteForm(gnu.lists.Pair r6, kawa.lang.Translator r7) {
        /*
            r5 = this;
            java.lang.Object r6 = r6.getCdr()
            boolean r0 = r6 instanceof gnu.lists.Pair
            if (r0 == 0) goto L_0x0023
            r0 = r6
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            java.lang.Object r1 = r0.getCar()
            boolean r1 = r1 instanceof java.util.Stack
            if (r1 == 0) goto L_0x0023
            java.lang.Object r6 = r0.getCar()
            java.util.Stack r6 = (java.util.Stack) r6
            java.lang.Object r0 = r0.getCdr()
            gnu.text.Options r1 = r7.currentOptions
            r1.pushOptionValues(r6)
            goto L_0x002f
        L_0x0023:
            java.util.Stack r0 = new java.util.Stack
            r0.<init>()
            java.lang.Object r6 = getOptions(r6, r0, r5, r7)
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x002f:
            gnu.expr.Expression r0 = r7.rewrite_body(r0)     // Catch:{ all -> 0x004f }
            boolean r1 = r0 instanceof gnu.expr.BeginExp     // Catch:{ all -> 0x004f }
            if (r1 == 0) goto L_0x003a
            gnu.expr.BeginExp r0 = (gnu.expr.BeginExp) r0     // Catch:{ all -> 0x004f }
            goto L_0x0046
        L_0x003a:
            gnu.expr.BeginExp r1 = new gnu.expr.BeginExp     // Catch:{ all -> 0x004f }
            r2 = 1
            gnu.expr.Expression[] r2 = new gnu.expr.Expression[r2]     // Catch:{ all -> 0x004f }
            r3 = 0
            r2[r3] = r0     // Catch:{ all -> 0x004f }
            r1.<init>(r2)     // Catch:{ all -> 0x004f }
            r0 = r1
        L_0x0046:
            r0.setCompileOptions(r6)     // Catch:{ all -> 0x004f }
            gnu.text.Options r7 = r7.currentOptions
            r7.popOptionValues(r6)
            return r0
        L_0x004f:
            r0 = move-exception
            gnu.text.Options r7 = r7.currentOptions
            r7.popOptionValues(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.with_compile_options.rewriteForm(gnu.lists.Pair, kawa.lang.Translator):gnu.expr.Expression");
    }
}
