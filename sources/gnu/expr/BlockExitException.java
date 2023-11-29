package gnu.expr;

/* compiled from: BlockExp */
class BlockExitException extends RuntimeException {
    ExitExp exit;
    Object result;

    public BlockExitException(ExitExp exitExp, Object obj) {
        this.exit = exitExp;
        this.result = obj;
    }
}
