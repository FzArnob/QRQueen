package gnu.bytecode;

public class IfState {
    boolean doing_else;
    Label end_label;
    IfState previous;
    int stack_growth;
    int start_stack_size;
    Type[] then_stacked_types;

    public IfState(CodeAttr codeAttr) {
        this(codeAttr, new Label(codeAttr));
    }

    public IfState(CodeAttr codeAttr, Label label) {
        this.previous = codeAttr.if_stack;
        codeAttr.if_stack = this;
        this.end_label = label;
        this.start_stack_size = codeAttr.SP;
    }
}
