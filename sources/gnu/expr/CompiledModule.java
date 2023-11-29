package gnu.expr;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import java.io.Writer;
import kawa.Shell;

public class CompiledModule {
    Object cookie;
    Language language;
    ModuleExp mexp;

    public CompiledModule(ModuleExp moduleExp, Object obj, Language language2) {
        this.mexp = moduleExp;
        this.cookie = obj;
        this.language = language2;
    }

    public static CompiledModule make(Class cls, Language language2) {
        return new CompiledModule((ModuleExp) null, cls, language2);
    }

    public void evalModule(Environment environment, CallContext callContext) throws Throwable {
        Language saveCurrent = Language.setSaveCurrent(this.language);
        Environment saveCurrent2 = Environment.setSaveCurrent(environment);
        try {
            ModuleExp.evalModule2(environment, callContext, this.language, this.mexp, this.cookie);
        } finally {
            Language.restoreCurrent(saveCurrent);
            Environment.restoreCurrent(saveCurrent2);
        }
    }

    public void evalModule(Environment environment, OutPort outPort) throws Throwable {
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        boolean mainPrintValues = ModuleBody.getMainPrintValues();
        AbstractFormat abstractFormat = outPort.objectFormat;
        instance.consumer = mainPrintValues ? Shell.getOutputConsumer(outPort) : new VoidConsumer();
        try {
            evalModule(environment, instance);
        } finally {
            if (instance.consumer instanceof Writer) {
                ((Writer) instance.consumer).flush();
            }
            instance.consumer = consumer;
            outPort.objectFormat = abstractFormat;
        }
    }

    public Object evalToResultValue(Environment environment, CallContext callContext) throws Throwable {
        int startFromContext = callContext.startFromContext();
        try {
            evalModule(environment, callContext);
            return callContext.getFromContext(startFromContext);
        } catch (Throwable th) {
            callContext.cleanupFromContext(startFromContext);
            throw th;
        }
    }
}
