package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import java.lang.reflect.Array;

/* compiled from: LambdaExp */
class Closure extends MethodProc {
    Object[][] evalFrames;
    LambdaExp lambda;

    public int numArgs() {
        return this.lambda.min_args | (this.lambda.max_args << 12);
    }

    public Closure(LambdaExp lambdaExp, CallContext callContext) {
        this.lambda = lambdaExp;
        Object[][] objArr = callContext.evalFrames;
        if (objArr != null) {
            int length = objArr.length;
            while (length > 0 && objArr[length - 1] == null) {
                length--;
            }
            Object[][] objArr2 = new Object[length][];
            this.evalFrames = objArr2;
            System.arraycopy(objArr, 0, objArr2, 0, length);
        }
        setSymbol(this.lambda.getSymbol());
    }

    public int match0(CallContext callContext) {
        return matchN(new Object[0], callContext);
    }

    public int match1(Object obj, CallContext callContext) {
        return matchN(new Object[]{obj}, callContext);
    }

    public int match2(Object obj, Object obj2, CallContext callContext) {
        return matchN(new Object[]{obj, obj2}, callContext);
    }

    public int match3(Object obj, Object obj2, Object obj3, CallContext callContext) {
        return matchN(new Object[]{obj, obj2, obj3}, callContext);
    }

    public int match4(Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        return matchN(new Object[]{obj, obj2, obj3, obj4}, callContext);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        int i;
        Location location;
        Object obj;
        Object obj2;
        Object obj3;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        int numArgs = numArgs();
        int length = objArr2.length;
        int i2 = numArgs & 4095;
        if (length < i2) {
            return -983040 | i2;
        }
        int i3 = numArgs >> 12;
        if (length > i3 && i3 >= 0) {
            return -917504 | i3;
        }
        Object[] objArr3 = new Object[this.lambda.frameSize];
        int i4 = 0;
        int length2 = this.lambda.defaultArgs == null ? 0 : this.lambda.defaultArgs.length - (this.lambda.keywords == null ? 0 : this.lambda.keywords.length);
        int i5 = this.lambda.min_args;
        Declaration firstDecl = this.lambda.firstDecl();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (firstDecl != null) {
            if (i6 < i5) {
                i = i6 + 1;
                location = objArr2[i6];
            } else {
                int i9 = i5 + length2;
                if (i6 < i9) {
                    if (i6 < length) {
                        obj3 = objArr2[i6];
                        i6++;
                    } else {
                        obj3 = this.lambda.evalDefaultArg(i7, callContext2);
                    }
                    i7++;
                    Object obj4 = obj3;
                    i = i6;
                    location = obj4;
                } else {
                    if (this.lambda.max_args >= 0 || i6 != i9) {
                        int i10 = i8 + 1;
                        obj = Keyword.searchForKeyword(objArr2, i9, this.lambda.keywords[i8]);
                        if (obj == Special.dfault) {
                            obj = this.lambda.evalDefaultArg(i7, callContext2);
                        }
                        i7++;
                        i = i6;
                        i8 = i10;
                    } else if (firstDecl.type instanceof ArrayType) {
                        int i11 = length - i6;
                        Type componentType = ((ArrayType) firstDecl.type).getComponentType();
                        if (componentType == Type.objectType) {
                            obj2 = new Object[i11];
                            System.arraycopy(objArr2, i6, obj2, i4, i11);
                        } else {
                            Object newInstance = Array.newInstance(componentType.getReflectClass(), i11);
                            while (i4 < i11) {
                                int i12 = i6 + i4;
                                try {
                                    Array.set(newInstance, i4, componentType.coerceFromObject(objArr2[i12]));
                                    i4++;
                                } catch (ClassCastException unused) {
                                    return i12 | MethodProc.NO_MATCH_BAD_TYPE;
                                }
                            }
                            obj2 = newInstance;
                        }
                        i = i6;
                        location = obj2;
                    } else {
                        obj = LList.makeList(objArr2, i6);
                        i = i6;
                    }
                    location = obj;
                }
            }
            if (firstDecl.type != null) {
                try {
                    location = firstDecl.type.coerceFromObject(location);
                } catch (ClassCastException unused2) {
                    return -786432 | i;
                }
            }
            if (firstDecl.isIndirectBinding()) {
                Location makeIndirectLocationFor = firstDecl.makeIndirectLocationFor();
                makeIndirectLocationFor.set(location);
                location = makeIndirectLocationFor;
            }
            objArr3[firstDecl.evalIndex] = location;
            firstDecl = firstDecl.nextDecl();
            i6 = i;
            i4 = 0;
        }
        callContext2.values = objArr3;
        callContext2.where = 0;
        callContext2.next = 0;
        callContext2.proc = this;
        return 0;
    }

    public void apply(CallContext callContext) throws Throwable {
        int nesting = ScopeExp.nesting(this.lambda);
        Object[] objArr = callContext.values;
        Object[][] objArr2 = callContext.evalFrames;
        Object[][] objArr3 = this.evalFrames;
        int length = objArr3 == null ? 0 : objArr3.length;
        if (nesting >= length) {
            length = nesting;
        }
        Object[][] objArr4 = new Object[(length + 10)][];
        if (objArr3 != null) {
            System.arraycopy(objArr3, 0, objArr4, 0, objArr3.length);
        }
        objArr4[nesting] = objArr;
        callContext.evalFrames = objArr4;
        try {
            if (this.lambda.body == null) {
                StringBuffer stringBuffer = new StringBuffer("procedure ");
                String name = this.lambda.getName();
                if (name == null) {
                    name = "<anonymous>";
                }
                stringBuffer.append(name);
                int lineNumber = this.lambda.getLineNumber();
                if (lineNumber > 0) {
                    stringBuffer.append(" at line ");
                    stringBuffer.append(lineNumber);
                }
                stringBuffer.append(" was called before it was expanded");
                throw new RuntimeException(stringBuffer.toString());
            }
            this.lambda.body.apply(callContext);
        } finally {
            callContext.evalFrames = objArr2;
        }
    }

    public Object getProperty(Object obj, Object obj2) {
        Object property = super.getProperty(obj, obj2);
        return property == null ? this.lambda.getProperty(obj, obj2) : property;
    }
}
