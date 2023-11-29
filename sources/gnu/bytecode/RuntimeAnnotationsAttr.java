package gnu.bytecode;

public class RuntimeAnnotationsAttr extends MiscAttr {
    int numEntries = u2(0);

    public RuntimeAnnotationsAttr(String str, byte[] bArr, AttrContainer attrContainer) {
        super(str, bArr, 0, bArr.length);
        addToFrontOf(attrContainer);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", number of entries: ");
        classTypeWriter.println(this.numEntries);
        int i = this.offset;
        this.offset = i + 2;
        for (int i2 = 0; i2 < this.numEntries; i2++) {
            printAnnotation(2, classTypeWriter);
        }
        this.offset = i;
    }

    public void printAnnotation(int i, ClassTypeWriter classTypeWriter) {
        int u2 = u2();
        classTypeWriter.printSpaces(i);
        classTypeWriter.printOptionalIndex(u2);
        classTypeWriter.print('@');
        classTypeWriter.printContantUtf8AsClass(u2);
        int u22 = u2();
        classTypeWriter.println();
        int i2 = i + 2;
        for (int i3 = 0; i3 < u22; i3++) {
            int u23 = u2();
            classTypeWriter.printSpaces(i2);
            classTypeWriter.printOptionalIndex(u23);
            classTypeWriter.printConstantTersely(u23, 1);
            classTypeWriter.print(" => ");
            printAnnotationElementValue(i2, classTypeWriter);
            classTypeWriter.println();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void printAnnotationElementValue(int r7, gnu.bytecode.ClassTypeWriter r8) {
        /*
            r6 = this;
            int r0 = r6.u1()
            int r1 = r8.flags
            r1 = r1 & 8
            if (r1 == 0) goto L_0x0024
            java.lang.String r1 = "[kind:"
            r8.print(r1)
            r1 = 65
            if (r0 < r1) goto L_0x001c
            r1 = 122(0x7a, float:1.71E-43)
            if (r0 > r1) goto L_0x001c
            char r1 = (char) r0
            r8.print(r1)
            goto L_0x001f
        L_0x001c:
            r8.print(r0)
        L_0x001f:
            java.lang.String r1 = "] "
            r8.print(r1)
        L_0x0024:
            r1 = 64
            if (r0 == r1) goto L_0x00fc
            r1 = 70
            r2 = 3
            r3 = 90
            r4 = 0
            r5 = 1
            if (r0 == r1) goto L_0x00c6
            r1 = 83
            if (r0 == r1) goto L_0x00be
            r1 = 99
            if (r0 == r1) goto L_0x00b3
            r1 = 101(0x65, float:1.42E-43)
            if (r0 == r1) goto L_0x0078
            r1 = 115(0x73, float:1.61E-43)
            if (r0 == r1) goto L_0x00c9
            r1 = 73
            if (r0 == r1) goto L_0x00be
            r1 = 74
            if (r0 == r1) goto L_0x00bf
            if (r0 == r3) goto L_0x00be
            r1 = 91
            if (r0 == r1) goto L_0x0054
            switch(r0) {
                case 66: goto L_0x00be;
                case 67: goto L_0x00be;
                case 68: goto L_0x00c3;
                default: goto L_0x0052;
            }
        L_0x0052:
            goto L_0x0107
        L_0x0054:
            int r0 = r6.u2()
            java.lang.String r1 = "array length:"
            r8.print(r1)
            r8.print(r0)
        L_0x0060:
            if (r4 >= r0) goto L_0x0107
            r8.println()
            int r1 = r7 + 2
            r8.printSpaces(r1)
            r8.print(r4)
            java.lang.String r2 = ": "
            r8.print(r2)
            r6.printAnnotationElementValue(r1, r8)
            int r4 = r4 + 1
            goto L_0x0060
        L_0x0078:
            int r7 = r6.u2()
            int r0 = r6.u2()
            java.lang.String r1 = "enum["
            r8.print(r1)
            int r1 = r8.flags
            r1 = r1 & 8
            if (r1 == 0) goto L_0x0090
            java.lang.String r1 = "type:"
            r8.print(r1)
        L_0x0090:
            r8.printOptionalIndex((int) r7)
            r8.printContantUtf8AsClass(r7)
            int r7 = r8.flags
            r7 = r7 & 8
            if (r7 == 0) goto L_0x00a2
            java.lang.String r7 = " value:"
            r8.print(r7)
            goto L_0x00a7
        L_0x00a2:
            r7 = 32
            r8.print(r7)
        L_0x00a7:
            r8.printOptionalIndex((int) r0)
            r8.printConstantTersely((int) r0, (int) r5)
            java.lang.String r7 = "]"
            r8.print(r7)
            goto L_0x0107
        L_0x00b3:
            int r7 = r6.u2()
            r8.printOptionalIndex((int) r7)
            r8.printContantUtf8AsClass(r7)
            goto L_0x0107
        L_0x00be:
            r4 = 3
        L_0x00bf:
            if (r4 != 0) goto L_0x00c3
            r7 = 5
            r4 = 5
        L_0x00c3:
            if (r4 != 0) goto L_0x00c6
            r4 = 6
        L_0x00c6:
            if (r4 != 0) goto L_0x00c9
            r4 = 4
        L_0x00c9:
            if (r4 != 0) goto L_0x00cc
            r4 = 1
        L_0x00cc:
            int r7 = r6.u2()
            gnu.bytecode.CpoolEntry r1 = r8.getCpoolEntry(r7)
            r8.printOptionalIndex((gnu.bytecode.CpoolEntry) r1)
            if (r0 != r3) goto L_0x00f8
            if (r1 == 0) goto L_0x00f8
            int r0 = r1.getTag()
            if (r0 != r2) goto L_0x00f8
            gnu.bytecode.CpoolValue1 r1 = (gnu.bytecode.CpoolValue1) r1
            int r0 = r1.value
            if (r0 == 0) goto L_0x00eb
            int r0 = r1.value
            if (r0 != r5) goto L_0x00f8
        L_0x00eb:
            int r7 = r1.value
            if (r7 != 0) goto L_0x00f2
            java.lang.String r7 = "false"
            goto L_0x00f4
        L_0x00f2:
            java.lang.String r7 = "true"
        L_0x00f4:
            r8.print(r7)
            goto L_0x0107
        L_0x00f8:
            r8.printConstantTersely((int) r7, (int) r4)
            goto L_0x0107
        L_0x00fc:
            r8.println()
            int r7 = r7 + 2
            r8.printSpaces(r7)
            r6.printAnnotation(r7, r8)
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.RuntimeAnnotationsAttr.printAnnotationElementValue(int, gnu.bytecode.ClassTypeWriter):void");
    }
}
