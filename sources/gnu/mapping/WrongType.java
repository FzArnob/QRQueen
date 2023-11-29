package gnu.mapping;

import gnu.bytecode.Type;

public class WrongType extends WrappedException {
    public static final int ARG_CAST = -4;
    public static final int ARG_DESCRIPTION = -3;
    public static final int ARG_UNKNOWN = -1;
    public static final int ARG_VARNAME = -2;
    public Object argValue;
    public Object expectedType;
    public int number;
    public Procedure proc;
    public String procname;

    public WrongType(String str, int i, String str2) {
        super((String) null, (Throwable) null);
        this.procname = str;
        this.number = i;
        this.expectedType = str2;
    }

    public WrongType(Procedure procedure, int i, ClassCastException classCastException) {
        super((Throwable) classCastException);
        this.proc = procedure;
        this.procname = procedure.getName();
        this.number = i;
    }

    public WrongType(ClassCastException classCastException, Procedure procedure, int i, Object obj) {
        this(procedure, i, classCastException);
        this.argValue = obj;
    }

    public WrongType(Procedure procedure, int i, Object obj) {
        this.proc = procedure;
        this.procname = procedure.getName();
        this.number = i;
        this.argValue = obj;
    }

    public WrongType(Procedure procedure, int i, Object obj, Type type) {
        this.proc = procedure;
        this.procname = procedure.getName();
        this.number = i;
        this.argValue = obj;
        this.expectedType = type;
    }

    public WrongType(int i, Object obj, Type type) {
        this.number = i;
        this.argValue = obj;
        this.expectedType = type;
    }

    public WrongType(Procedure procedure, int i, Object obj, String str) {
        this(procedure.getName(), i, obj, str);
        this.proc = procedure;
    }

    public WrongType(String str, int i, Object obj, String str2) {
        this.procname = str;
        this.number = i;
        this.argValue = obj;
        this.expectedType = str2;
    }

    public WrongType(String str, int i, ClassCastException classCastException) {
        super((Throwable) classCastException);
        this.procname = str;
        this.number = i;
    }

    public WrongType(ClassCastException classCastException, String str, int i, Object obj) {
        this(str, i, classCastException);
        this.argValue = obj;
    }

    public static WrongType make(ClassCastException classCastException, Procedure procedure, int i) {
        return new WrongType(procedure, i, classCastException);
    }

    public static WrongType make(ClassCastException classCastException, String str, int i) {
        return new WrongType(str, i, classCastException);
    }

    public static WrongType make(ClassCastException classCastException, Procedure procedure, int i, Object obj) {
        WrongType wrongType = new WrongType(procedure, i, classCastException);
        wrongType.argValue = obj;
        return wrongType;
    }

    public static WrongType make(ClassCastException classCastException, String str, int i, Object obj) {
        WrongType wrongType = new WrongType(str, i, classCastException);
        wrongType.argValue = obj;
        return wrongType;
    }

    public String getMessage() {
        String message;
        int i;
        int i2;
        StringBuffer stringBuffer = new StringBuffer(100);
        int i3 = this.number;
        if (i3 == -3) {
            stringBuffer.append(this.procname);
        } else if (i3 == -4 || i3 == -2) {
            stringBuffer.append("Value");
        } else {
            stringBuffer.append("Argument ");
            if (this.number > 0) {
                stringBuffer.append('#');
                stringBuffer.append(this.number);
            }
        }
        if (this.argValue != null) {
            stringBuffer.append(" (");
            String obj = this.argValue.toString();
            if (obj.length() > 50) {
                stringBuffer.append(obj.substring(0, 47));
                stringBuffer.append("...");
            } else {
                stringBuffer.append(obj);
            }
            stringBuffer.append(")");
        }
        if (!(this.procname == null || (i2 = this.number) == -3)) {
            stringBuffer.append(i2 == -2 ? " for variable '" : " to '");
            stringBuffer.append(this.procname);
            stringBuffer.append("'");
        }
        stringBuffer.append(" has wrong type");
        if (this.argValue != null) {
            stringBuffer.append(" (");
            stringBuffer.append(this.argValue.getClass().getName());
            stringBuffer.append(")");
        }
        Object obj2 = this.expectedType;
        if (obj2 == null && (i = this.number) > 0) {
            Procedure procedure = this.proc;
            if (procedure instanceof MethodProc) {
                obj2 = ((MethodProc) procedure).getParameterType(i - 1);
            }
        }
        if (!(obj2 == null || obj2 == Type.pointer_type)) {
            stringBuffer.append(" (expected: ");
            if (obj2 instanceof Type) {
                obj2 = ((Type) obj2).getName();
            } else if (obj2 instanceof Class) {
                obj2 = ((Class) obj2).getName();
            }
            stringBuffer.append(obj2);
            stringBuffer.append(")");
        }
        Throwable cause = getCause();
        if (!(cause == null || (message = cause.getMessage()) == null)) {
            stringBuffer.append(" (");
            stringBuffer.append(message);
            stringBuffer.append(')');
        }
        return stringBuffer.toString();
    }
}
