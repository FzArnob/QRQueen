package net.lingala.zip4j.crypto;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import net.lingala.zip4j.crypto.engine.ZipCryptoEngine;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class StandardDecrypter implements IDecrypter {
    private byte[] crc = new byte[4];
    private FileHeader fileHeader;
    private ZipCryptoEngine zipCryptoEngine;

    public StandardDecrypter(FileHeader fileHeader2, byte[] bArr) throws ZipException {
        if (fileHeader2 != null) {
            this.fileHeader = fileHeader2;
            this.zipCryptoEngine = new ZipCryptoEngine();
            init(bArr);
            return;
        }
        throw new ZipException("one of more of the input parameters were null in StandardDecryptor");
    }

    public int decryptData(byte[] bArr) throws ZipException {
        return decryptData(bArr, 0, bArr.length);
    }

    public int decryptData(byte[] bArr, int i, int i2) throws ZipException {
        if (i < 0 || i2 < 0) {
            throw new ZipException("one of the input parameters were null in standard decrpyt data");
        }
        int i3 = i;
        while (i3 < i + i2) {
            try {
                byte decryptByte = (byte) (((bArr[i3] & Ev3Constants.Opcode.TST) ^ this.zipCryptoEngine.decryptByte()) & Ev3Constants.Opcode.TST);
                this.zipCryptoEngine.updateKeys(decryptByte);
                bArr[i3] = decryptByte;
                i3++;
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
        }
        return i2;
    }

    public void init(byte[] bArr) throws ZipException {
        byte[] crcBuff = this.fileHeader.getCrcBuff();
        byte[] bArr2 = this.crc;
        bArr2[3] = (byte) (crcBuff[3] & Ev3Constants.Opcode.TST);
        byte b = crcBuff[3];
        byte b2 = (byte) ((b >> 8) & 255);
        bArr2[2] = b2;
        byte b3 = (byte) ((b >> 16) & 255);
        bArr2[1] = b3;
        byte b4 = (byte) ((b >> 24) & 255);
        int i = 0;
        bArr2[0] = b4;
        if (b2 > 0 || b3 > 0 || b4 > 0) {
            throw new IllegalStateException("Invalid CRC in File Header");
        } else if (this.fileHeader.getPassword() == null || this.fileHeader.getPassword().length <= 0) {
            throw new ZipException("Wrong password!", 5);
        } else {
            this.zipCryptoEngine.initKeys(this.fileHeader.getPassword());
            try {
                byte b5 = bArr[0];
                while (i < 12) {
                    ZipCryptoEngine zipCryptoEngine2 = this.zipCryptoEngine;
                    zipCryptoEngine2.updateKeys((byte) (zipCryptoEngine2.decryptByte() ^ b5));
                    i++;
                    if (i != 12) {
                        b5 = bArr[i];
                    }
                }
            } catch (Exception e) {
                throw new ZipException((Throwable) e);
            }
        }
    }
}
