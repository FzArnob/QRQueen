package gnu.bytecode;

public class PrimType extends Type {
    private static final String numberHierarchy = "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;";

    public PrimType(String str, String str2, int i, Class cls) {
        super(str, str2);
        this.size = i;
        this.reflectClass = cls;
        Type.registerTypeForClass(cls, this);
    }

    protected PrimType(PrimType primType) {
        super(primType.this_name, primType.signature);
        this.size = primType.size;
        this.reflectClass = primType.reflectClass;
    }

    public Object coerceFromObject(Object obj) {
        if (obj.getClass() == this.reflectClass) {
            return obj;
        }
        char charAt = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (charAt == 'B') {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (charAt == 'D') {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (charAt == 'F') {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (charAt == 'S') {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (charAt == 'Z') {
            return Boolean.valueOf(((Boolean) obj).booleanValue());
        }
        if (charAt == 'I') {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (charAt == 'J') {
            return Long.valueOf(((Number) obj).longValue());
        }
        throw new ClassCastException("don't know how to coerce " + obj.getClass().getName() + " to " + getName());
    }

    public char charValue(Object obj) {
        return ((Character) obj).charValue();
    }

    public static boolean booleanValue(Object obj) {
        return !(obj instanceof Boolean) || ((Boolean) obj).booleanValue();
    }

    public ClassType boxedType() {
        String str;
        char charAt = getSignature().charAt(0);
        if (charAt == 'F') {
            str = "java.lang.Float";
        } else if (charAt == 'S') {
            str = "java.lang.Short";
        } else if (charAt == 'Z') {
            str = "java.lang.Boolean";
        } else if (charAt == 'I') {
            str = "java.lang.Integer";
        } else if (charAt != 'J') {
            switch (charAt) {
                case 'B':
                    str = "java.lang.Byte";
                    break;
                case 'C':
                    str = "java.lang.Character";
                    break;
                case 'D':
                    str = "java.lang.Double";
                    break;
                default:
                    str = null;
                    break;
            }
        } else {
            str = "java.lang.Long";
        }
        return ClassType.make(str);
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        Method method;
        char charAt = getSignature().charAt(0);
        ClassType boxedType = boxedType();
        if (charAt == 'Z') {
            codeAttr.emitIfIntNotZero();
            codeAttr.emitGetStatic(boxedType.getDeclaredField("TRUE"));
            codeAttr.emitElse();
            codeAttr.emitGetStatic(boxedType.getDeclaredField("FALSE"));
            codeAttr.emitFi();
            return;
        }
        Type[] typeArr = {this};
        if (codeAttr.getMethod().getDeclaringClass().classfileFormatVersion >= 3211264) {
            method = boxedType.getDeclaredMethod("valueOf", typeArr);
        } else {
            method = boxedType.getDeclaredMethod("<init>", typeArr);
            codeAttr.emitNew(boxedType);
            codeAttr.emitDupX();
            codeAttr.emitSwap();
        }
        codeAttr.emitInvoke(method);
    }

    public void emitIsInstance(CodeAttr codeAttr) {
        char charAt = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (charAt == 'Z') {
            javalangBooleanType.emitIsInstance(codeAttr);
        } else if (charAt == 'V') {
            codeAttr.emitPop(1);
            codeAttr.emitPushInt(1);
        } else {
            javalangNumberType.emitIsInstance(codeAttr);
        }
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        char charAt = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (charAt == 'Z') {
            codeAttr.emitCheckcast(javalangBooleanType);
            codeAttr.emitInvokeVirtual(booleanValue_method);
        } else if (charAt == 'V') {
            codeAttr.emitPop(1);
        } else {
            codeAttr.emitCheckcast(javalangNumberType);
            if (charAt == 'I' || charAt == 'S' || charAt == 'B') {
                codeAttr.emitInvokeVirtual(intValue_method);
            } else if (charAt == 'J') {
                codeAttr.emitInvokeVirtual(longValue_method);
            } else if (charAt == 'D') {
                codeAttr.emitInvokeVirtual(doubleValue_method);
            } else if (charAt == 'F') {
                codeAttr.emitInvokeVirtual(floatValue_method);
            } else {
                super.emitCoerceFromObject(codeAttr);
            }
        }
    }

    public static int compare(PrimType primType, PrimType primType2) {
        char charAt = primType.signature.charAt(0);
        char charAt2 = primType2.signature.charAt(0);
        if (charAt == charAt2) {
            return 0;
        }
        if (charAt == 'V') {
            return 1;
        }
        if (charAt2 == 'V') {
            return -1;
        }
        if (!(charAt == 'Z' || charAt2 == 'Z')) {
            if (charAt == 'C') {
                return primType2.size > 2 ? -1 : -3;
            }
            if (charAt2 == 'C') {
                if (primType.size > 2) {
                    return 1;
                }
                return -3;
            } else if (charAt == 'D') {
                return 1;
            } else {
                if (charAt2 == 'D') {
                    return -1;
                }
                if (charAt == 'F') {
                    return 1;
                }
                if (charAt2 == 'F') {
                    return -1;
                }
                if (charAt == 'J') {
                    return 1;
                }
                if (charAt2 == 'J') {
                    return -1;
                }
                if (charAt == 'I') {
                    return 1;
                }
                if (charAt2 == 'I') {
                    return -1;
                }
                if (charAt == 'S') {
                    return 1;
                }
                if (charAt2 == 'S') {
                    return -1;
                }
            }
        }
        return -3;
    }

    public Type promotedType() {
        char charAt = this.signature.charAt(0);
        if (charAt == 'B' || charAt == 'C' || charAt == 'I' || charAt == 'S' || charAt == 'Z') {
            return Type.intType;
        }
        return getImplementationType();
    }

    private static char findInHierarchy(String str) {
        int indexOf = numberHierarchy.indexOf(str) - 2;
        if (indexOf < 0) {
            return 0;
        }
        return numberHierarchy.charAt(indexOf);
    }

    public int compare(Type type) {
        char findInHierarchy;
        if (type instanceof PrimType) {
            if (type.getImplementationType() != type) {
                return swappedCompareResult(type.compare(this));
            }
            return compare(this, (PrimType) type);
        } else if (type instanceof ClassType) {
            char charAt = this.signature.charAt(0);
            String name = type.getName();
            if (name == null) {
                return -1;
            }
            char c = Access.INNERCLASS_CONTEXT;
            if (charAt == 'F') {
                c = 'H';
            } else if (charAt == 'S') {
                c = 'B';
            } else if (charAt == 'V') {
                return 1;
            } else {
                if (charAt != 'Z') {
                    if (charAt == 'I') {
                        c = Access.CLASS_CONTEXT;
                    } else if (charAt != 'J') {
                        switch (charAt) {
                            case 'B':
                                c = 'A';
                                break;
                            case 'C':
                                break;
                            case 'D':
                                break;
                        }
                    } else {
                        c = 'D';
                    }
                } else if (name.equals("java.lang.Boolean")) {
                    return 0;
                }
                if (name.equals("java.lang.Character")) {
                    return 0;
                }
                c = 0;
            }
            if (c == 0 || (findInHierarchy = findInHierarchy(name)) == 0) {
                if (name.equals("java.lang.Object") || type == toStringType) {
                    return -1;
                }
                return -3;
            } else if (findInHierarchy == c) {
                return 0;
            } else {
                return findInHierarchy < c ? 1 : -1;
            }
        } else if (type instanceof ArrayType) {
            return -3;
        } else {
            return swappedCompareResult(type.compare(this));
        }
    }
}
