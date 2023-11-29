package kawa.standard;

import gnu.mapping.Environment;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import kawa.Shell;

public class load extends Procedure1 {
    public static final load load = new load("load", false);
    public static final load loadRelative = new load("load-relative", true);
    boolean relative;

    public load(String str, boolean z) {
        super(str);
        this.relative = z;
    }

    public final Object apply1(Object obj) throws Throwable {
        return apply2(obj, Environment.getCurrent());
    }

    public final Object apply2(Object obj, Object obj2) throws Throwable {
        Path path;
        try {
            Environment environment = (Environment) obj2;
            Path valueOf = Path.valueOf(obj);
            if (this.relative && (path = (Path) Shell.currentLoadPath.get()) != null) {
                valueOf = path.resolve(valueOf);
            }
            Shell.runFile(valueOf.openInputStream(), valueOf, environment, true, 0);
            return Values.empty;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("cannot load " + e.getMessage());
        } catch (SyntaxException e2) {
            throw new RuntimeException("load: errors while compiling '" + obj + "':\n" + e2.getMessages().toString(20));
        }
    }
}
