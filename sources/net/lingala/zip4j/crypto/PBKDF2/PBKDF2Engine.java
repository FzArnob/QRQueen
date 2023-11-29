package net.lingala.zip4j.crypto.PBKDF2;

import net.lingala.zip4j.util.Raw;

public class PBKDF2Engine {
    protected PBKDF2Parameters parameters;
    protected PRF prf;

    public PBKDF2Engine() {
        this.parameters = null;
        this.prf = null;
    }

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters) {
        this.parameters = pBKDF2Parameters;
        this.prf = null;
    }

    public PBKDF2Engine(PBKDF2Parameters pBKDF2Parameters, PRF prf2) {
        this.parameters = pBKDF2Parameters;
        this.prf = prf2;
    }

    public byte[] deriveKey(char[] cArr) {
        return deriveKey(cArr, 0);
    }

    public byte[] deriveKey(char[] cArr, int i) {
        byte[] bArr = null;
        cArr.getClass();
        assertPRF(Raw.convertCharArrayToByteArray(cArr));
        if (i == 0) {
            i = this.prf.getHLen();
        }
        return PBKDF2(this.prf, this.parameters.getSalt(), this.parameters.getIterationCount(), i);
    }

    public boolean verifyKey(char[] cArr) {
        byte[] deriveKey;
        byte[] derivedKey = getParameters().getDerivedKey();
        if (derivedKey == null || derivedKey.length == 0 || (deriveKey = deriveKey(cArr, derivedKey.length)) == null || deriveKey.length != derivedKey.length) {
            return false;
        }
        for (int i = 0; i < deriveKey.length; i++) {
            if (deriveKey[i] != derivedKey[i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void assertPRF(byte[] bArr) {
        if (this.prf == null) {
            this.prf = new MacBasedPRF(this.parameters.getHashAlgorithm());
        }
        this.prf.init(bArr);
    }

    public PRF getPseudoRandomFunction() {
        return this.prf;
    }

    /* access modifiers changed from: protected */
    public byte[] PBKDF2(PRF prf2, byte[] bArr, int i, int i2) {
        int i3 = i2;
        byte[] bArr2 = bArr == null ? new byte[0] : bArr;
        int hLen = prf2.getHLen();
        int ceil = ceil(i3, hLen);
        int i4 = i3 - ((ceil - 1) * hLen);
        byte[] bArr3 = new byte[(ceil * hLen)];
        int i5 = 0;
        for (int i6 = 1; i6 <= ceil; i6++) {
            _F(bArr3, i5, prf2, bArr2, i, i6);
            i5 += hLen;
        }
        if (i4 >= hLen) {
            return bArr3;
        }
        byte[] bArr4 = new byte[i3];
        System.arraycopy(bArr3, 0, bArr4, 0, i3);
        return bArr4;
    }

    /* access modifiers changed from: protected */
    public int ceil(int i, int i2) {
        return (i / i2) + (i % i2 > 0 ? 1 : 0);
    }

    /* access modifiers changed from: protected */
    public void _F(byte[] bArr, int i, PRF prf2, byte[] bArr2, int i2, int i3) {
        int hLen = prf2.getHLen();
        byte[] bArr3 = new byte[hLen];
        byte[] bArr4 = new byte[(bArr2.length + 4)];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        INT(bArr4, bArr2.length, i3);
        for (int i4 = 0; i4 < i2; i4++) {
            bArr4 = prf2.doFinal(bArr4);
            xor(bArr3, bArr4);
        }
        System.arraycopy(bArr3, 0, bArr, i, hLen);
    }

    /* access modifiers changed from: protected */
    public void xor(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void INT(byte[] bArr, int i, int i2) {
        bArr[i + 0] = (byte) (i2 / 16777216);
        bArr[i + 1] = (byte) (i2 / 65536);
        bArr[i + 2] = (byte) (i2 / 256);
        bArr[i + 3] = (byte) i2;
    }

    public PBKDF2Parameters getParameters() {
        return this.parameters;
    }

    public void setParameters(PBKDF2Parameters pBKDF2Parameters) {
        this.parameters = pBKDF2Parameters;
    }

    public void setPseudoRandomFunction(PRF prf2) {
        this.prf = prf2;
    }
}
