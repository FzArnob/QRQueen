package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SignatureAttr extends Attribute {
    String signature;
    int signature_index;

    public final int getLength() {
        return 2;
    }

    public final String getSignature() {
        return this.signature;
    }

    /* access modifiers changed from: protected */
    public void setSignature(String str) {
        this.signature = str;
        this.signature_index = 0;
    }

    public SignatureAttr(String str) {
        super("Signature");
        this.signature = str;
    }

    public SignatureAttr(int i, Member member) {
        super("Signature");
        this.signature = ((CpoolUtf8) (member instanceof ClassType ? (ClassType) member : member.getDeclaringClass()).constants.getForced(i, 1)).string;
        this.signature_index = i;
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        if (this.signature_index == 0) {
            this.signature_index = classType.getConstants().addUtf8(this.signature).getIndex();
        }
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.signature_index);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        super.print(classTypeWriter);
        classTypeWriter.print("  ");
        classTypeWriter.printOptionalIndex(this.signature_index);
        classTypeWriter.print('\"');
        classTypeWriter.print(getSignature());
        classTypeWriter.println('\"');
    }
}
