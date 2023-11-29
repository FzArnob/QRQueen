package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceFileAttr extends Attribute {
    String filename;
    int filename_index;

    public final int getLength() {
        return 2;
    }

    public String getSourceFile() {
        return this.filename;
    }

    public void setSourceFile(String str) {
        this.filename = str;
        this.filename_index = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r0 = r0.charAt(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String fixSourceFile(java.lang.String r3) {
        /*
            java.lang.String r0 = "file.separator"
            java.lang.String r1 = "/"
            java.lang.String r0 = java.lang.System.getProperty(r0, r1)
            if (r0 == 0) goto L_0x001e
            int r1 = r0.length()
            r2 = 1
            if (r1 != r2) goto L_0x001e
            r1 = 0
            char r0 = r0.charAt(r1)
            r1 = 47
            if (r0 == r1) goto L_0x001e
            java.lang.String r3 = r3.replace(r0, r1)
        L_0x001e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.SourceFileAttr.fixSourceFile(java.lang.String):java.lang.String");
    }

    public static void setSourceFile(ClassType classType, String str) {
        Attribute attribute = Attribute.get(classType, "SourceFile");
        if (attribute == null || !(attribute instanceof SourceFileAttr)) {
            new SourceFileAttr(str).addToFrontOf(classType);
        } else {
            ((SourceFileAttr) attribute).setSourceFile(str);
        }
    }

    public SourceFileAttr(String str) {
        super("SourceFile");
        this.filename = str;
    }

    public SourceFileAttr(int i, ClassType classType) {
        super("SourceFile");
        this.filename = ((CpoolUtf8) classType.constants.getForced(i, 1)).string;
        this.filename_index = i;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        if (this.filename_index == 0) {
            this.filename_index = classType.getConstants().addUtf8(this.filename).getIndex();
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.filename_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.print(getLength());
        classTypeWriter.print(", ");
        classTypeWriter.printOptionalIndex(this.filename_index);
        classTypeWriter.print('\"');
        classTypeWriter.print(getSourceFile());
        classTypeWriter.println('\"');
    }
}
