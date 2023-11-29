package gnu.mapping;

public class WrongArguments extends IllegalArgumentException {
    public int number;
    Procedure proc;
    public String procname;
    public String usage;

    public static String checkArgCount(Procedure procedure, int i) {
        int numArgs = procedure.numArgs();
        int i2 = numArgs & 4095;
        int i3 = numArgs >> 12;
        String name = procedure.getName();
        if (name == null) {
            name = procedure.getClass().getName();
        }
        return checkArgCount(name, i2, i3, i);
    }

    public static String checkArgCount(String str, int i, int i2, int i3) {
        boolean z;
        if (i3 < i) {
            z = false;
        } else if (i2 < 0 || i3 <= i2) {
            return null;
        } else {
            z = true;
        }
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append("call to ");
        if (str == null) {
            stringBuffer.append("unnamed procedure");
        } else {
            stringBuffer.append('\'');
            stringBuffer.append(str);
            stringBuffer.append('\'');
        }
        stringBuffer.append(z ? " has too many" : " has too few");
        stringBuffer.append(" arguments (");
        stringBuffer.append(i3);
        if (i == i2) {
            stringBuffer.append("; must be ");
            stringBuffer.append(i);
        } else {
            stringBuffer.append("; min=");
            stringBuffer.append(i);
            if (i2 >= 0) {
                stringBuffer.append(", max=");
                stringBuffer.append(i2);
            }
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public String getMessage() {
        String checkArgCount;
        Procedure procedure = this.proc;
        if (procedure == null || (checkArgCount = checkArgCount(procedure, this.number)) == null) {
            return super.getMessage();
        }
        return checkArgCount;
    }

    public WrongArguments(Procedure procedure, int i) {
        this.proc = procedure;
        this.number = i;
    }

    public WrongArguments(String str, int i, String str2) {
        this.procname = str;
        this.number = i;
        this.usage = str2;
    }
}
