package net.lingala.zip4j.crypto;

import java.util.Arrays;
import net.lingala.zip4j.crypto.PBKDF2.MacBasedPRF;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Engine;
import net.lingala.zip4j.crypto.PBKDF2.PBKDF2Parameters;
import net.lingala.zip4j.crypto.engine.AESEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.AESExtraDataRecord;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.util.Raw;

public class AESDecrypter implements IDecrypter {
    private int KEY_LENGTH;
    private int MAC_LENGTH;
    private final int PASSWORD_VERIFIER_LENGTH = 2;
    private int SALT_LENGTH;
    private AESEngine aesEngine;
    private byte[] aesKey;
    private byte[] counterBlock;
    private byte[] derivedPasswordVerifier;
    private byte[] iv;
    private LocalFileHeader localFileHeader;
    private int loopCount = 0;
    private MacBasedPRF mac;
    private byte[] macKey;
    private int nonce = 1;
    private byte[] storedMac;

    public int getPasswordVerifierLength() {
        return 2;
    }

    public AESDecrypter(LocalFileHeader localFileHeader2, byte[] bArr, byte[] bArr2) throws ZipException {
        if (localFileHeader2 != null) {
            this.localFileHeader = localFileHeader2;
            this.storedMac = null;
            this.iv = new byte[16];
            this.counterBlock = new byte[16];
            init(bArr, bArr2);
            return;
        }
        throw new ZipException("one of the input parameters is null in AESDecryptor Constructor");
    }

    private void init(byte[] bArr, byte[] bArr2) throws ZipException {
        LocalFileHeader localFileHeader2 = this.localFileHeader;
        if (localFileHeader2 != null) {
            AESExtraDataRecord aesExtraDataRecord = localFileHeader2.getAesExtraDataRecord();
            if (aesExtraDataRecord != null) {
                int aesStrength = aesExtraDataRecord.getAesStrength();
                if (aesStrength == 1) {
                    this.KEY_LENGTH = 16;
                    this.MAC_LENGTH = 16;
                    this.SALT_LENGTH = 8;
                } else if (aesStrength == 2) {
                    this.KEY_LENGTH = 24;
                    this.MAC_LENGTH = 24;
                    this.SALT_LENGTH = 12;
                } else if (aesStrength == 3) {
                    this.KEY_LENGTH = 32;
                    this.MAC_LENGTH = 32;
                    this.SALT_LENGTH = 16;
                } else {
                    throw new ZipException(new StringBuffer("invalid aes key strength for file: ").append(this.localFileHeader.getFileName()).toString());
                }
                if (this.localFileHeader.getPassword() == null || this.localFileHeader.getPassword().length <= 0) {
                    throw new ZipException("empty or null password provided for AES Decryptor");
                }
                byte[] deriveKey = deriveKey(bArr, this.localFileHeader.getPassword());
                if (deriveKey != null) {
                    int length = deriveKey.length;
                    int i = this.KEY_LENGTH;
                    int i2 = this.MAC_LENGTH;
                    if (length == i + i2 + 2) {
                        byte[] bArr3 = new byte[i];
                        this.aesKey = bArr3;
                        this.macKey = new byte[i2];
                        this.derivedPasswordVerifier = new byte[2];
                        System.arraycopy(deriveKey, 0, bArr3, 0, i);
                        System.arraycopy(deriveKey, this.KEY_LENGTH, this.macKey, 0, this.MAC_LENGTH);
                        System.arraycopy(deriveKey, this.KEY_LENGTH + this.MAC_LENGTH, this.derivedPasswordVerifier, 0, 2);
                        byte[] bArr4 = this.derivedPasswordVerifier;
                        if (bArr4 == null) {
                            throw new ZipException("invalid derived password verifier for AES");
                        } else if (Arrays.equals(bArr2, bArr4)) {
                            this.aesEngine = new AESEngine(this.aesKey);
                            MacBasedPRF macBasedPRF = new MacBasedPRF("HmacSHA1");
                            this.mac = macBasedPRF;
                            macBasedPRF.init(this.macKey);
                            return;
                        } else {
                            throw new ZipException(new StringBuffer("Wrong Password for file: ").append(this.localFileHeader.getFileName()).toString(), 5);
                        }
                    }
                }
                throw new ZipException("invalid derived key");
            }
            throw new ZipException("invalid aes extra data record - in init method of AESDecryptor");
        }
        throw new ZipException("invalid file header in init method of AESDecryptor");
    }

    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (this.aesEngine != null) {
            int i3 = i;
            while (true) {
                int i4 = i + i2;
                if (i3 >= i4) {
                    return i2;
                }
                int i5 = i3 + 16;
                int i6 = i5 <= i4 ? 16 : i4 - i3;
                try {
                    this.loopCount = i6;
                    this.mac.update(bArr, i3, i6);
                    Raw.prepareBuffAESIVBytes(this.iv, this.nonce, 16);
                    this.aesEngine.processBlock(this.iv, this.counterBlock);
                    for (int i7 = 0; i7 < this.loopCount; i7++) {
                        int i8 = i3 + i7;
                        bArr[i8] = (byte) (bArr[i8] ^ this.counterBlock[i7]);
                    }
                    this.nonce++;
                    i3 = i5;
                } catch (ZipException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new ZipException((Throwable) e2);
                }
            }
        } else {
            throw new ZipException("AES not initialized properly");
        }
    }

    public int decryptData(byte[] bArr) throws ZipException {
        return decryptData(bArr, 0, bArr.length);
    }

    private byte[] deriveKey(byte[] bArr, char[] cArr) throws ZipException {
        try {
            return new PBKDF2Engine(new PBKDF2Parameters("HmacSHA1", "ISO-8859-1", bArr, 1000)).deriveKey(cArr, this.KEY_LENGTH + this.MAC_LENGTH + 2);
        } catch (Exception e) {
            throw new ZipException((Throwable) e);
        }
    }

    public int getSaltLength() {
        return this.SALT_LENGTH;
    }

    public byte[] getCalculatedAuthenticationBytes() {
        return this.mac.doFinal();
    }

    public void setStoredMac(byte[] bArr) {
        this.storedMac = bArr;
    }

    public byte[] getStoredMac() {
        return this.storedMac;
    }
}
