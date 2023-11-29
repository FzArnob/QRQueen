package org.jose4j.jwe;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.GCMParameterSpec;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;

public class SimpleAeadCipher {
    public static final String GCM_TRANSFORMATION_NAME = "AES/GCM/NoPadding";
    private String algorithm;
    private int tagByteLength;

    public SimpleAeadCipher(String str, int i) {
        this.algorithm = str;
        this.tagByteLength = i;
    }

    private Cipher getInitialisedCipher(Key key, byte[] bArr, int i, String str) throws JoseException {
        Cipher cipher = CipherUtil.getCipher(this.algorithm, str);
        try {
            cipher.init(i, key, new GCMParameterSpec(ByteUtil.bitLength(this.tagByteLength), bArr));
            return cipher;
        } catch (InvalidKeyException e) {
            throw new JoseException("Invalid key for " + this.algorithm, e);
        } catch (InvalidAlgorithmParameterException e2) {
            throw new JoseException(e2.toString(), e2);
        }
    }

    public CipherOutput encrypt(Key key, byte[] bArr, byte[] bArr2, byte[] bArr3, String str) throws JoseException {
        Cipher initialisedCipher = getInitialisedCipher(key, bArr, 1, str);
        updateAad(initialisedCipher, bArr3);
        try {
            byte[] doFinal = initialisedCipher.doFinal(bArr2);
            CipherOutput cipherOutput = new CipherOutput();
            int length = doFinal.length - this.tagByteLength;
            byte[] unused = cipherOutput.ciphertext = ByteUtil.subArray(doFinal, 0, length);
            byte[] unused2 = cipherOutput.tag = ByteUtil.subArray(doFinal, length, this.tagByteLength);
            return cipherOutput;
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new JoseException(e.toString(), e);
        }
    }

    private void updateAad(Cipher cipher, byte[] bArr) {
        if (bArr != null && bArr.length > 0) {
            cipher.updateAAD(bArr);
        }
    }

    public byte[] decrypt(Key key, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, String str) throws JoseException {
        Cipher initialisedCipher = getInitialisedCipher(key, bArr, 2, str);
        updateAad(initialisedCipher, bArr4);
        try {
            return initialisedCipher.doFinal(ByteUtil.concat(bArr2, bArr3));
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new JoseException(e.toString(), e);
        }
    }

    public boolean isAvailable(Logger logger, int i, int i2, String str) {
        if (CipherStrengthSupport.isAvailable(this.algorithm, i)) {
            try {
                encrypt(new AesKey(new byte[i]), new byte[i2], new byte[]{Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_EQ8, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.JR_GT16, Ev3Constants.Opcode.JR_EQ32, Ev3Constants.Opcode.JR_LTEQ8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.JR_GTEQ8, Ev3Constants.Opcode.JR_LTEQ8}, new byte[]{Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.JR_LT8}, (String) null);
                return true;
            } catch (Throwable th) {
                logger.debug("{} is not available ({}).", (Object) str, (Object) ExceptionHelp.toStringWithCauses(th));
            }
        }
        return false;
    }

    public static class CipherOutput {
        /* access modifiers changed from: private */
        public byte[] ciphertext;
        /* access modifiers changed from: private */
        public byte[] tag;

        public byte[] getCiphertext() {
            return this.ciphertext;
        }

        public byte[] getTag() {
            return this.tag;
        }
    }
}
