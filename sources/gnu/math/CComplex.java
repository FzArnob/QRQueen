package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CComplex extends Complex implements Externalizable {
    RealNum imag;
    RealNum real;

    public CComplex() {
    }

    public CComplex(RealNum realNum, RealNum realNum2) {
        this.real = realNum;
        this.imag = realNum2;
    }

    public RealNum re() {
        return this.real;
    }

    public RealNum im() {
        return this.imag;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.real);
        objectOutput.writeObject(this.imag);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.real = (RealNum) objectInput.readObject();
        this.imag = (RealNum) objectInput.readObject();
    }
}
