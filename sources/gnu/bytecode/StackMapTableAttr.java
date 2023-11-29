package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.io.DataOutputStream;
import java.io.IOException;
import kawa.Telnet;

public class StackMapTableAttr extends MiscAttr {
    public static boolean compressStackMapTable = true;
    int countLocals;
    int countStack;
    int[] encodedLocals;
    int[] encodedStack;
    int numEntries;
    int prevPosition = -1;

    public StackMapTableAttr() {
        super("StackMapTable", (byte[]) null, 0, 0);
        put2(0);
    }

    public StackMapTableAttr(byte[] bArr, CodeAttr codeAttr) {
        super("StackMapTable", bArr, 0, bArr.length);
        addToFrontOf(codeAttr);
        this.numEntries = u2(0);
    }

    public Method getMethod() {
        return ((CodeAttr) this.container).getMethod();
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        put2(0, this.numEntries);
        super.write(dataOutputStream);
    }

    /* access modifiers changed from: package-private */
    public void emitVerificationType(int i) {
        int i2 = i & 255;
        put1(i2);
        if (i2 >= 7) {
            put2(i >> 8);
        }
    }

    /* access modifiers changed from: package-private */
    public int encodeVerificationType(Type type, CodeAttr codeAttr) {
        if (type == null) {
            return 0;
        }
        if (type instanceof UninitializedType) {
            Label label = ((UninitializedType) type).label;
            if (label == null) {
                return 6;
            }
            return (label.position << 8) | 8;
        }
        Type implementationType = type.getImplementationType();
        if (implementationType instanceof PrimType) {
            char charAt = implementationType.signature.charAt(0);
            if (charAt == 'F') {
                return 2;
            }
            if (charAt == 'S' || charAt == 'Z' || charAt == 'I') {
                return 1;
            }
            if (charAt == 'J') {
                return 4;
            }
            switch (charAt) {
                case 'B':
                case 'C':
                    return 1;
                case 'D':
                    return 3;
                default:
                    return 0;
            }
        } else if (implementationType == Type.nullType) {
            return 5;
        } else {
            return (codeAttr.getConstants().addClass((ObjectType) implementationType).index << 8) | 7;
        }
    }

    public void emitStackMapEntry(Label label, CodeAttr codeAttr) {
        int i = (label.position - this.prevPosition) - 1;
        int length = label.localTypes.length;
        int[] iArr = this.encodedLocals;
        int i2 = 0;
        if (length > iArr.length) {
            int[] iArr2 = new int[(iArr.length + length)];
            System.arraycopy(iArr, 0, iArr2, 0, this.countLocals);
            this.encodedLocals = iArr2;
        }
        int length2 = label.stackTypes.length;
        int[] iArr3 = this.encodedStack;
        if (length2 > iArr3.length) {
            int[] iArr4 = new int[(iArr3.length + length2)];
            System.arraycopy(iArr3, 0, iArr4, 0, this.countStack);
            this.encodedStack = iArr4;
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            int i6 = this.encodedLocals[i4];
            int encodeVerificationType = encodeVerificationType(label.localTypes[i3], codeAttr);
            if (i6 == encodeVerificationType && i5 == i4) {
                i5 = i4 + 1;
            }
            int i7 = i4 + 1;
            this.encodedLocals[i4] = encodeVerificationType;
            if (encodeVerificationType == 3 || encodeVerificationType == 4) {
                i3++;
            }
            i3++;
            i4 = i7;
        }
        while (i4 > 0 && this.encodedLocals[i4 - 1] == 0) {
            i4--;
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < length2) {
            int i10 = this.encodedStack[i9];
            Type type = label.stackTypes[i8];
            if (type == Type.voidType) {
                i8++;
                type = label.stackTypes[i8];
            }
            this.encodedStack[i9] = encodeVerificationType(type, codeAttr);
            i8++;
            i9++;
        }
        int i11 = this.countLocals;
        int i12 = i4 - i11;
        boolean z = compressStackMapTable;
        if (!z || i12 != 0 || i4 != i5 || i9 > 1) {
            if (z && i9 == 0 && i4 < i11 && i5 == i4 && i12 >= -3) {
                put1(i12 + Telnet.WILL);
                put2(i);
            } else if (!z || i9 != 0 || i11 != i5 || i12 > 3) {
                put1(255);
                put2(i);
                put2(i4);
                for (int i13 = 0; i13 < i4; i13++) {
                    emitVerificationType(this.encodedLocals[i13]);
                }
                put2(i9);
                while (i2 < i9) {
                    emitVerificationType(this.encodedStack[i2]);
                    i2++;
                }
            } else {
                put1(i12 + Telnet.WILL);
                put2(i);
                while (i2 < i12) {
                    emitVerificationType(this.encodedLocals[i5 + i2]);
                    i2++;
                }
            }
        } else if (i9 != 0) {
            if (i <= 63) {
                put1(i + 64);
            } else {
                put1(247);
                put2(i);
            }
            emitVerificationType(this.encodedStack[0]);
        } else if (i <= 63) {
            put1(i);
        } else {
            put1(Telnet.WILL);
            put2(i);
        }
        this.countLocals = i4;
        this.countStack = i9;
        this.prevPosition = label.position;
        this.numEntries++;
    }

    /* access modifiers changed from: package-private */
    public void printVerificationType(int i, ClassTypeWriter classTypeWriter) {
        int i2 = i & 255;
        switch (i2) {
            case 0:
                classTypeWriter.print("top/unavailable");
                return;
            case 1:
                classTypeWriter.print(PropertyTypeConstants.PROPERTY_TYPE_INTEGER);
                return;
            case 2:
                classTypeWriter.print(PropertyTypeConstants.PROPERTY_TYPE_FLOAT);
                return;
            case 3:
                classTypeWriter.print(DoubleTypedProperty.TYPE);
                return;
            case 4:
                classTypeWriter.print(LongTypedProperty.TYPE);
                return;
            case 5:
                classTypeWriter.print("null");
                return;
            case 6:
                classTypeWriter.print("uninitialized this");
                return;
            case 7:
                int i3 = i >> 8;
                classTypeWriter.printOptionalIndex(i3);
                classTypeWriter.printConstantTersely(i3, 7);
                return;
            case 8:
                classTypeWriter.print("uninitialized object created at ");
                classTypeWriter.print(i >> 8);
                return;
            default:
                classTypeWriter.print("<bad verification type tag " + i2 + '>');
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public int extractVerificationType(int i, int i2) {
        return (i2 == 7 || i2 == 8) ? i2 | (u2(i + 1) << 8) : i2;
    }

    static int[] reallocBuffer(int[] iArr, int i) {
        if (iArr == null) {
            return new int[(i + 10)];
        }
        if (i <= iArr.length) {
            return iArr;
        }
        int[] iArr2 = new int[(i + 10)];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    /* access modifiers changed from: package-private */
    public int extractVerificationTypes(int i, int i2, int i3, int[] iArr) {
        while (true) {
            int i4 = -1;
            i2--;
            if (i2 < 0) {
                return i;
            }
            if (i < this.dataLength) {
                byte b = this.data[i];
                int extractVerificationType = extractVerificationType(i, b);
                i += (b == 7 || b == 8) ? 3 : 1;
                i4 = extractVerificationType;
            }
            iArr[i3] = i4;
            i3++;
        }
    }

    /* access modifiers changed from: package-private */
    public void printVerificationTypes(int[] iArr, int i, int i2, ClassTypeWriter classTypeWriter) {
        int i3 = 0;
        for (int i4 = 0; i4 < i + i2; i4++) {
            int i5 = iArr[i4];
            int i6 = i5 & 255;
            if (i4 >= i) {
                classTypeWriter.print("  ");
                if (i3 < 100) {
                    if (i3 < 10) {
                        classTypeWriter.print(' ');
                    }
                    classTypeWriter.print(' ');
                }
                classTypeWriter.print(i3);
                classTypeWriter.print(": ");
                printVerificationType(i5, classTypeWriter);
                classTypeWriter.println();
            }
            i3++;
            if (i6 == 3 || i6 == 4) {
                i3++;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x0143 A[LOOP:0: B:1:0x0036->B:47:0x0143, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void print(gnu.bytecode.ClassTypeWriter r14) {
        /*
            r13 = this;
            java.lang.String r0 = "Attribute \""
            r14.print(r0)
            java.lang.String r0 = r13.getName()
            r14.print(r0)
            java.lang.String r0 = "\", length:"
            r14.print(r0)
            int r0 = r13.getLength()
            r14.print(r0)
            java.lang.String r0 = ", number of entries: "
            r14.print(r0)
            int r0 = r13.numEntries
            r14.println(r0)
            gnu.bytecode.Method r0 = r13.getMethod()
            boolean r1 = r0.getStaticFlag()
            r2 = 1
            r1 = r1 ^ r2
            gnu.bytecode.Type[] r0 = r0.arg_types
            int r0 = r0.length
            int r1 = r1 + r0
            r0 = -1
            r3 = 2
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = -1
        L_0x0036:
            int r8 = r13.numEntries
            if (r6 >= r8) goto L_0x0147
            int r8 = r13.dataLength
            if (r3 < r8) goto L_0x0040
            goto L_0x0147
        L_0x0040:
            int r8 = r3 + 1
            int r3 = r13.u1(r3)
            int r7 = r7 + r2
            r9 = 127(0x7f, float:1.78E-43)
            if (r3 > r9) goto L_0x004f
            r10 = r3 & 63
            int r7 = r7 + r10
            goto L_0x005e
        L_0x004f:
            int r10 = r8 + 1
            int r11 = r13.dataLength
            if (r10 < r11) goto L_0x0057
            goto L_0x0147
        L_0x0057:
            int r10 = r13.u2(r8)
            int r7 = r7 + r10
            int r8 = r8 + 2
        L_0x005e:
            java.lang.String r10 = "  offset: "
            r14.print(r10)
            r14.print(r7)
            r10 = 63
            if (r3 > r10) goto L_0x0070
            java.lang.String r3 = " - same_frame"
            r14.println(r3)
            goto L_0x00a4
        L_0x0070:
            if (r3 <= r9) goto L_0x0123
            r10 = 247(0xf7, float:3.46E-43)
            if (r3 != r10) goto L_0x0078
            goto L_0x0123
        L_0x0078:
            r9 = 246(0xf6, float:3.45E-43)
            if (r3 > r9) goto L_0x0086
            java.lang.String r0 = " - tag reserved for future use - "
            r14.print(r0)
            r14.println(r3)
            goto L_0x0147
        L_0x0086:
            r9 = 250(0xfa, float:3.5E-43)
            if (r3 > r9) goto L_0x009b
            int r3 = 251 - r3
            java.lang.String r9 = " - chop_frame - undefine "
            r14.print(r9)
            r14.print(r3)
            java.lang.String r9 = " locals"
            r14.println(r9)
            int r1 = r1 - r3
            goto L_0x00a4
        L_0x009b:
            r9 = 251(0xfb, float:3.52E-43)
            if (r3 != r9) goto L_0x00a7
            java.lang.String r3 = " - same_frame_extended"
            r14.println(r3)
        L_0x00a4:
            r3 = r8
            goto L_0x013b
        L_0x00a7:
            r9 = 254(0xfe, float:3.56E-43)
            if (r3 > r9) goto L_0x00cb
            int r3 = r3 + -251
            java.lang.String r9 = " - append_frame - define "
            r14.print(r9)
            r14.print(r3)
            java.lang.String r9 = " more locals"
            r14.println(r9)
            int r9 = r1 + r3
            int[] r5 = reallocBuffer(r5, r9)
            int r8 = r13.extractVerificationTypes(r8, r3, r1, r5)
            r13.printVerificationTypes(r5, r1, r3, r14)
            r3 = r8
            r1 = r9
            goto L_0x013b
        L_0x00cb:
            int r1 = r8 + 1
            int r3 = r13.dataLength
            if (r1 < r3) goto L_0x00d3
            goto L_0x0147
        L_0x00d3:
            int r1 = r13.u2(r8)
            int r8 = r8 + 2
            java.lang.String r3 = " - full_frame.  Locals count: "
            r14.print(r3)
            r14.println(r1)
            int[] r3 = reallocBuffer(r5, r1)
            int r5 = r13.extractVerificationTypes(r8, r1, r4, r3)
            r13.printVerificationTypes(r3, r4, r1, r14)
            int r8 = r5 + 1
            int r9 = r13.dataLength
            if (r8 < r9) goto L_0x00f3
            goto L_0x0147
        L_0x00f3:
            int r8 = r13.u2(r5)
            int r5 = r5 + 2
            java.lang.String r9 = "    (end of locals)"
            r14.print(r9)
            java.lang.String r9 = java.lang.Integer.toString(r7)
            int r9 = r9.length()
        L_0x0106:
            int r9 = r9 + r0
            if (r9 < 0) goto L_0x010f
            r10 = 32
            r14.print(r10)
            goto L_0x0106
        L_0x010f:
            java.lang.String r9 = "       Stack count: "
            r14.print(r9)
            r14.println(r8)
            int[] r3 = reallocBuffer(r3, r8)
            int r5 = r13.extractVerificationTypes(r5, r8, r4, r3)
            r13.printVerificationTypes(r3, r4, r8, r14)
            goto L_0x0138
        L_0x0123:
            if (r3 > r9) goto L_0x0128
            java.lang.String r3 = " - same_locals_1_stack_item_frame"
            goto L_0x012a
        L_0x0128:
            java.lang.String r3 = " - same_locals_1_stack_item_frame_extended"
        L_0x012a:
            r14.println(r3)
            int[] r3 = reallocBuffer(r5, r2)
            int r5 = r13.extractVerificationTypes(r8, r2, r4, r3)
            r13.printVerificationTypes(r3, r4, r2, r14)
        L_0x0138:
            r12 = r5
            r5 = r3
            r3 = r12
        L_0x013b:
            if (r3 >= 0) goto L_0x0143
            java.lang.String r0 = "<ERROR - missing data>"
            r14.println(r0)
            return
        L_0x0143:
            int r6 = r6 + 1
            goto L_0x0036
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.StackMapTableAttr.print(gnu.bytecode.ClassTypeWriter):void");
    }
}
