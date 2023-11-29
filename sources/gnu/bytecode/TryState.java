package gnu.bytecode;

public class TryState {
    Label end_label;
    Label end_try;
    Variable exception;
    ExitableBlock exitCases;
    Variable finally_ret_addr;
    Label finally_subr;
    TryState previous;
    Variable[] savedStack;
    Type[] savedTypes;
    Variable saved_result;
    Label start_try;
    boolean tryClauseDone;
    ClassType try_type;

    public TryState(CodeAttr codeAttr) {
        this.previous = codeAttr.try_stack;
        codeAttr.try_stack = this;
        this.start_try = codeAttr.getLabel();
    }

    static TryState outerHandler(TryState tryState, TryState tryState2) {
        while (tryState != tryState2 && (tryState.finally_subr == null || tryState.tryClauseDone)) {
            tryState = tryState.previous;
        }
        return tryState;
    }
}
