package net.lingala.zip4j.crypto.PBKDF2;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MacBasedPRF implements PRF {
    protected int hLen;
    protected Mac mac;
    protected String macAlgorithm;

    public MacBasedPRF(String str) {
        this.macAlgorithm = str;
        try {
            Mac instance = Mac.getInstance(str);
            this.mac = instance;
            this.hLen = instance.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public MacBasedPRF(String str, String str2) {
        this.macAlgorithm = str;
        try {
            Mac instance = Mac.getInstance(str, str2);
            this.mac = instance;
            this.hLen = instance.getMacLength();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e2) {
            throw new RuntimeException(e2);
        }
    }

    public byte[] doFinal(byte[] bArr) {
        return this.mac.doFinal(bArr);
    }

    public byte[] doFinal() {
        return this.mac.doFinal();
    }

    public int getHLen() {
        return this.hLen;
    }

    public void init(byte[] bArr) {
        try {
            this.mac.init(new SecretKeySpec(bArr, this.macAlgorithm));
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(byte[] bArr) {
        try {
            this.mac.update(bArr);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        try {
            this.mac.update(bArr, i, i2);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }
}
