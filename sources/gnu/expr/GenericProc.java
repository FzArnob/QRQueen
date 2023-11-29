package gnu.expr;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;

public class GenericProc extends MethodProc {
    int count;
    int maxArgs;
    protected MethodProc[] methods;
    int minArgs;

    public GenericProc(String str) {
        setName(str);
    }

    public GenericProc() {
    }

    public int getMethodCount() {
        return this.count;
    }

    public MethodProc getMethod(int i) {
        if (i >= this.count) {
            return null;
        }
        return this.methods[i];
    }

    public int numArgs() {
        return this.minArgs | (this.maxArgs << 12);
    }

    /* access modifiers changed from: protected */
    public synchronized void add(MethodProc[] methodProcArr) {
        if (this.methods == null) {
            this.methods = new MethodProc[r0];
        }
        for (MethodProc add : methodProcArr) {
            add(add);
        }
    }

    public synchronized void addAtEnd(MethodProc methodProc) {
        int i = this.count;
        MethodProc[] methodProcArr = this.methods;
        if (methodProcArr == null) {
            this.methods = new MethodProc[8];
        } else if (i >= methodProcArr.length) {
            MethodProc[] methodProcArr2 = new MethodProc[(methodProcArr.length * 2)];
            System.arraycopy(methodProcArr, 0, methodProcArr2, 0, i);
            this.methods = methodProcArr2;
        }
        this.methods[i] = methodProc;
        int minArgs2 = methodProc.minArgs();
        if (minArgs2 < this.minArgs || this.count == 0) {
            this.minArgs = minArgs2;
        }
        int maxArgs2 = methodProc.maxArgs();
        if (maxArgs2 == -1 || maxArgs2 > this.maxArgs) {
            this.maxArgs = maxArgs2;
        }
        this.count = i + 1;
    }

    public synchronized void add(MethodProc methodProc) {
        int i = this.count;
        addAtEnd(methodProc);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            } else if (MethodProc.mostSpecific(methodProc, this.methods[i2]) == methodProc) {
                MethodProc[] methodProcArr = this.methods;
                System.arraycopy(methodProcArr, i2, methodProcArr, i2 + 1, i - i2);
                this.methods[i2] = methodProc;
                break;
            } else {
                i2++;
            }
        }
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (this.count == 1) {
            return this.methods[0].applyN(objArr);
        }
        checkArgCount(this, objArr.length);
        CallContext instance = CallContext.getInstance();
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].matchN(objArr, instance) == 0) {
                return instance.runUntilValue();
            }
        }
        throw new WrongType((Procedure) this, -1, (ClassCastException) null);
    }

    public int isApplicable(Type[] typeArr) {
        int i = this.count;
        int i2 = -1;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            int isApplicable = this.methods[i].isApplicable(typeArr);
            if (isApplicable == 1) {
                return 1;
            }
            if (isApplicable == 0) {
                i2 = 0;
            }
        }
    }

    public int match0(CallContext callContext) {
        if (this.count == 1) {
            return this.methods[0].match0(callContext);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match0(callContext) == 0) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public int match1(Object obj, CallContext callContext) {
        if (this.count == 1) {
            return this.methods[0].match1(obj, callContext);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match1(obj, callContext) == 0) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        if (this.count == 1) {
            return this.methods[0].match2(obj, obj2, callContext);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match2(obj, obj2, callContext) == 0) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (this.count == 1) {
            return this.methods[0].match3(obj, obj2, obj3, callContext);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match3(obj, obj2, obj3, callContext) == 0) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (this.count == 1) {
            return this.methods[0].match4(obj, obj2, obj3, obj4, callContext);
        }
        for (int i = 0; i < this.count; i++) {
            if (this.methods[i].match4(obj, obj2, obj3, obj4, callContext) == 0) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        Type type;
        if (this.count == 1) {
            return this.methods[0].matchN(objArr, callContext);
        }
        int length = objArr.length;
        Type[] typeArr = new Type[length];
        Language defaultLanguage = Language.getDefaultLanguage();
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            if (obj == null) {
                type = Type.nullType;
            } else {
                Class<?> cls = obj.getClass();
                if (defaultLanguage != null) {
                    type = defaultLanguage.getTypeFor((Class) cls);
                } else {
                    type = Type.make(cls);
                }
            }
            typeArr[i] = type;
        }
        int[] iArr = new int[this.count];
        int i2 = 0;
        int i3 = 0;
        int i4 = -1;
        for (int i5 = 0; i5 < this.count; i5++) {
            int isApplicable = this.methods[i5].isApplicable(typeArr);
            if (i2 == 0 && isApplicable >= 0) {
                i4 = i5;
            }
            if (isApplicable > 0) {
                i2++;
            } else if (isApplicable == 0) {
                i3++;
            }
            iArr[i5] = isApplicable;
        }
        if (i2 == 1 || (i2 == 0 && i3 == 1)) {
            return this.methods[i4].matchN(objArr, callContext);
        }
        for (int i6 = 0; i6 < this.count; i6++) {
            int i7 = iArr[i6];
            if (i7 >= 0 && ((i7 != 0 || i2 <= 0) && this.methods[i6].matchN(objArr, callContext) == 0)) {
                return 0;
            }
        }
        callContext.proc = null;
        return -1;
    }

    public void setProperty(Keyword keyword, Object obj) {
        String name = keyword.getName();
        if (name == CommonProperties.NAME) {
            setName(obj.toString());
        } else if (name == "method") {
            add((MethodProc) obj);
        } else {
            super.setProperty(keyword.asSymbol(), obj);
        }
    }

    public final void setProperties(Object[] objArr) {
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                i++;
                setProperty(keyword, objArr[i]);
            } else {
                add((MethodProc) keyword);
            }
            i++;
        }
    }

    public static GenericProc make(Object[] objArr) {
        GenericProc genericProc = new GenericProc();
        genericProc.setProperties(objArr);
        return genericProc;
    }

    public static GenericProc makeWithoutSorting(Object... objArr) {
        GenericProc genericProc = new GenericProc();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                i++;
                genericProc.setProperty(keyword, objArr[i]);
            } else {
                genericProc.addAtEnd((MethodProc) keyword);
            }
            i++;
        }
        return genericProc;
    }
}
