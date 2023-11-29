package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.text.SourceMessages;
import java.net.URL;

public class Eval extends Procedure1or2 {
    public static final Eval eval;
    static final String evalFunctionName = "atEvalLevel$";

    static {
        Eval eval2 = new Eval();
        eval = eval2;
        eval2.setName("eval");
    }

    public static void eval(Object obj, Environment environment, CallContext callContext) throws Throwable {
        PairWithPosition pairWithPosition;
        if (obj instanceof PairWithPosition) {
            pairWithPosition = new PairWithPosition((PairWithPosition) obj, obj, LList.Empty);
        } else {
            pairWithPosition = new PairWithPosition(obj, LList.Empty);
            pairWithPosition.setFile("<eval>");
        }
        evalBody(pairWithPosition, environment, new SourceMessages(), callContext);
    }

    public static Object evalBody(Object obj, Environment environment, SourceMessages sourceMessages) throws Throwable {
        CallContext instance = CallContext.getInstance();
        int startFromContext = instance.startFromContext();
        try {
            evalBody(obj, environment, sourceMessages, instance);
            return instance.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public static Object eval(Object obj, Environment environment) throws Throwable {
        CallContext instance = CallContext.getInstance();
        int startFromContext = instance.startFromContext();
        try {
            eval(obj, environment, instance);
            return instance.getFromContext(startFromContext);
        } catch (Throwable th) {
            instance.cleanupFromContext(startFromContext);
            throw th;
        }
    }

    public static void evalBody(Object obj, Environment environment, SourceMessages sourceMessages, CallContext callContext) throws Throwable {
        Compilation saveCurrent;
        Language defaultLanguage = Language.getDefaultLanguage();
        Environment current = Environment.getCurrent();
        if (environment != current) {
            try {
                Environment.setCurrent(environment);
            } catch (Throwable th) {
                if (environment != current) {
                    Environment.setCurrent(current);
                }
                throw th;
            }
        }
        Translator translator = new Translator(defaultLanguage, sourceMessages, NameLookup.getInstance(environment, defaultLanguage));
        translator.immediate = true;
        translator.setState(3);
        translator.setSharedModuleDefs(true);
        String str = null;
        ModuleExp pushNewModule = translator.pushNewModule((String) null);
        saveCurrent = Compilation.setSaveCurrent(translator);
        int size = translator.formStack.size();
        translator.scanBody(obj, pushNewModule, false);
        translator.firstForm = size;
        translator.finishModule(pushNewModule);
        Compilation.restoreCurrent(saveCurrent);
        if (obj instanceof PairWithPosition) {
            pushNewModule.setFile(((PairWithPosition) obj).getFileName());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(evalFunctionName);
        int i = ModuleExp.interactiveCounter + 1;
        ModuleExp.interactiveCounter = i;
        sb.append(i);
        pushNewModule.setName(sb.toString());
        ModuleExp.evalModule(environment, callContext, translator, (URL) null, (OutPort) null);
        if (sourceMessages.seenErrors()) {
            throw new RuntimeException("invalid syntax in eval form:\n" + sourceMessages.toString(20));
        } else if (environment != current) {
            Environment.setCurrent(current);
        }
    }

    public Object apply1(Object obj) throws Throwable {
        return eval(obj, Environment.user());
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        return eval(obj, (Environment) obj2);
    }

    public void apply(CallContext callContext) throws Throwable {
        Procedure.checkArgCount(this, callContext.count);
        Object nextArg = callContext.getNextArg();
        Environment environment = (Environment) callContext.getNextArg((Object) null);
        if (environment == null) {
            environment = Environment.user();
        }
        callContext.lastArg();
        eval(nextArg, environment, callContext);
    }
}
