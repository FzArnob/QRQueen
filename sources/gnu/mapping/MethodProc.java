package gnu.mapping;

import androidx.core.internal.view.SupportMenu;
import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;

public abstract class MethodProc extends ProcedureN {
    public static final int NO_MATCH = -1;
    public static final int NO_MATCH_AMBIGUOUS = -851968;
    public static final int NO_MATCH_BAD_TYPE = -786432;
    public static final int NO_MATCH_TOO_FEW_ARGS = -983040;
    public static final int NO_MATCH_TOO_MANY_ARGS = -917504;
    static final Type[] unknownArgTypes = {Type.pointer_type};
    protected Object argTypes;

    public int isApplicable(Type[] typeArr) {
        int length = typeArr.length;
        int numArgs = numArgs();
        if (length < (numArgs & 4095) || (numArgs >= 0 && length > (numArgs >> 12))) {
            return -1;
        }
        int i = 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            int compare = getParameterType(length).compare(typeArr[length]);
            if (compare == -3) {
                return -1;
            }
            if (compare < 0) {
                i = 0;
            }
        }
    }

    public int numParameters() {
        int numArgs = numArgs();
        int i = numArgs >> 12;
        return i >= 0 ? i : (numArgs & 4095) + 1;
    }

    /* access modifiers changed from: protected */
    public void resolveParameterTypes() {
        this.argTypes = unknownArgTypes;
    }

    public Type getParameterType(int i) {
        if (!(this.argTypes instanceof Type[])) {
            resolveParameterTypes();
        }
        Type[] typeArr = (Type[]) this.argTypes;
        if (i < typeArr.length && (i < typeArr.length - 1 || maxArgs() >= 0)) {
            return typeArr[i];
        }
        if (maxArgs() < 0) {
            Type type = typeArr[typeArr.length - 1];
            if (type instanceof ArrayType) {
                return ((ArrayType) type).getComponentType();
            }
        }
        return Type.objectType;
    }

    public static RuntimeException matchFailAsException(int i, Procedure procedure, Object[] objArr) {
        short s = (short) i;
        if ((i & SupportMenu.CATEGORY_MASK) != -786432) {
            return new WrongArguments(procedure, objArr.length);
        }
        return new WrongType(procedure, (int) s, s > 0 ? objArr[s - 1] : null);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        checkArgCount(this, objArr.length);
        CallContext instance = CallContext.getInstance();
        checkN(objArr, instance);
        return instance.runUntilValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.mapping.MethodProc mostSpecific(gnu.mapping.MethodProc r8, gnu.mapping.MethodProc r9) {
        /*
            int r0 = r8.minArgs()
            int r1 = r9.minArgs()
            int r2 = r8.maxArgs()
            int r3 = r9.maxArgs()
            r4 = 0
            if (r2 < 0) goto L_0x0015
            if (r2 < r1) goto L_0x0019
        L_0x0015:
            if (r3 < 0) goto L_0x001a
            if (r3 >= r0) goto L_0x001a
        L_0x0019:
            return r4
        L_0x001a:
            int r5 = r8.numParameters()
            int r6 = r9.numParameters()
            if (r5 <= r6) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r5 = r6
        L_0x0026:
            r6 = 0
            r7 = 1
            if (r2 == r3) goto L_0x0033
            if (r2 >= 0) goto L_0x002e
            r2 = 1
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            if (r3 >= 0) goto L_0x0034
            r3 = 1
            goto L_0x0035
        L_0x0033:
            r2 = 0
        L_0x0034:
            r3 = 0
        L_0x0035:
            if (r0 >= r1) goto L_0x0039
            r2 = 1
            goto L_0x003c
        L_0x0039:
            if (r0 <= r1) goto L_0x003c
            r3 = 1
        L_0x003c:
            if (r6 >= r5) goto L_0x005a
            gnu.bytecode.Type r0 = r8.getParameterType(r6)
            gnu.bytecode.Type r1 = r9.getParameterType(r6)
            int r0 = r0.compare(r1)
            r1 = -1
            if (r0 != r1) goto L_0x0051
            if (r2 == 0) goto L_0x0050
            return r4
        L_0x0050:
            r3 = 1
        L_0x0051:
            if (r0 != r7) goto L_0x0057
            if (r3 == 0) goto L_0x0056
            return r4
        L_0x0056:
            r2 = 1
        L_0x0057:
            int r6 = r6 + 1
            goto L_0x003c
        L_0x005a:
            if (r3 == 0) goto L_0x005d
            goto L_0x0062
        L_0x005d:
            if (r2 == 0) goto L_0x0061
            r8 = r9
            goto L_0x0062
        L_0x0061:
            r8 = r4
        L_0x0062:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.mapping.MethodProc.mostSpecific(gnu.mapping.MethodProc, gnu.mapping.MethodProc):gnu.mapping.MethodProc");
    }

    public static int mostSpecific(MethodProc[] methodProcArr, int i) {
        if (i <= 1) {
            return i - 1;
        }
        MethodProc methodProc = methodProcArr[0];
        MethodProc[] methodProcArr2 = null;
        int i2 = 0;
        for (int i3 = 1; i3 < i; i3++) {
            MethodProc methodProc2 = methodProcArr[i3];
            if (methodProc != null) {
                MethodProc mostSpecific = mostSpecific(methodProc, methodProc2);
                if (mostSpecific == null) {
                    if (methodProcArr2 == null) {
                        methodProcArr2 = new MethodProc[i];
                    }
                    methodProcArr2[0] = methodProc;
                    methodProcArr2[1] = methodProc2;
                    i2 = 2;
                    methodProc = null;
                } else if (mostSpecific != methodProc2) {
                }
            } else {
                int i4 = 0;
                while (true) {
                    if (i4 < i2) {
                        MethodProc methodProc3 = methodProcArr2[i4];
                        MethodProc mostSpecific2 = mostSpecific(methodProc3, methodProc2);
                        if (mostSpecific2 == methodProc3) {
                            break;
                        } else if (mostSpecific2 == null) {
                            methodProcArr2[i2] = methodProc2;
                            i2++;
                            break;
                        } else {
                            i4++;
                        }
                    } else {
                        break;
                    }
                }
            }
            i2 = i3;
            methodProc = methodProc2;
        }
        if (methodProc == null) {
            return -1;
        }
        return i2;
    }
}
