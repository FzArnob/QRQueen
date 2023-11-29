package kawa.standard;

import gnu.kawa.functions.ObjectFormat;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.math.IntNum;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class TracedProcedure extends ProcedureN {
    static Symbol curIndentSym = Symbol.makeUninterned("current-indentation");
    static int indentationStep = 2;
    boolean enabled;
    public Procedure proc;

    public TracedProcedure(Procedure procedure, boolean z) {
        this.proc = procedure;
        this.enabled = z;
        String name = procedure.getName();
        if (name != null) {
            setName(name);
        }
    }

    static void put(Object obj, PrintWriter printWriter) {
        try {
            if (!ObjectFormat.format(obj, (Writer) printWriter, 50, true)) {
                printWriter.print("...");
            }
        } catch (IOException e) {
            printWriter.print("<caught ");
            printWriter.print(e);
            printWriter.print('>');
        }
    }

    static void indent(int i, PrintWriter printWriter) {
        while (true) {
            i--;
            if (i >= 0) {
                printWriter.print(' ');
            } else {
                return;
            }
        }
    }

    public Object applyN(Object[] objArr) throws Throwable {
        int i;
        if (!this.enabled) {
            return this.proc.applyN(objArr);
        }
        Location location = Environment.getCurrent().getLocation(curIndentSym);
        Object obj = location.get((Object) null);
        if (!(obj instanceof IntNum)) {
            location.set(IntNum.zero());
            i = 0;
        } else {
            i = ((IntNum) obj).intValue();
        }
        OutPort errDefault = OutPort.errDefault();
        String name = getName();
        if (name == null) {
            name = "??";
        }
        indent(i, errDefault);
        errDefault.print("call to ");
        errDefault.print(name);
        int length = objArr.length;
        errDefault.print(" (");
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 > 0) {
                errDefault.print(' ');
            }
            put(objArr[i2], errDefault);
        }
        errDefault.println(")");
        Object withSave = location.setWithSave(IntNum.make(indentationStep + i));
        try {
            Object applyN = this.proc.applyN(objArr);
            location.setRestore(withSave);
            indent(i, errDefault);
            errDefault.print("return from ");
            errDefault.print(name);
            errDefault.print(" => ");
            put(applyN, errDefault);
            errDefault.println();
            return applyN;
        } catch (RuntimeException e) {
            indent(i, errDefault);
            errDefault.println("procedure " + name + " throws exception " + e);
            throw e;
        } catch (Throwable th) {
            location.setRestore(withSave);
            throw th;
        }
    }

    public static Procedure doTrace(Procedure procedure, boolean z) {
        if (!(procedure instanceof TracedProcedure)) {
            return new TracedProcedure(procedure, z);
        }
        ((TracedProcedure) procedure).enabled = z;
        return procedure;
    }

    public void print(PrintWriter printWriter) {
        printWriter.print("#<procedure ");
        String name = getName();
        if (name == null) {
            printWriter.print("<unnamed>");
        } else {
            printWriter.print(name);
        }
        printWriter.print(this.enabled ? ", traced>" : ">");
    }
}
