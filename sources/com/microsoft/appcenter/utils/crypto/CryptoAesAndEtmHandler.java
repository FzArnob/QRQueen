package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;
import org.jose4j.mac.MacUtil;

public class CryptoAesAndEtmHandler implements CryptoHandler {
    private static final int AUTHENTICATION_KEY_LENGTH = 16;
    private static final int ENCRYPTION_KEY_LENGTH = 32;

    public String getAlgorithm() {
        return "AES/CBC/PKCS7Padding/256/HmacSHA256";
    }

    public void generateKey(CryptoUtils.ICryptoFactory iCryptoFactory, String str, Context context) throws Exception {
        Calendar instance = Calendar.getInstance();
        instance.add(1, 1);
        CryptoUtils.IKeyGenerator keyGenerator = iCryptoFactory.getKeyGenerator(MacUtil.HMAC_SHA256, "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(str, 4).setKeyValidityForOriginationEnd(instance.getTime()).build());
        keyGenerator.generateKey();
    }

    public byte[] encrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) throws Exception {
        SecretKey secretKey = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
        byte[] subkey = getSubkey(secretKey, 32);
        byte[] subkey2 = getSubkey(secretKey, 16);
        CryptoUtils.ICipher cipher = iCryptoFactory.getCipher("AES/CBC/PKCS7Padding", (String) null);
        cipher.init(1, new SecretKeySpec(subkey, AesKey.ALGORITHM));
        byte[] iv = cipher.getIV();
        byte[] doFinal = cipher.doFinal(bArr);
        byte[] macBytes = getMacBytes(subkey2, iv, doFinal);
        ByteBuffer allocate = ByteBuffer.allocate(iv.length + 1 + 1 + macBytes.length + doFinal.length);
        allocate.put((byte) iv.length);
        allocate.put(iv);
        allocate.put((byte) macBytes.length);
        allocate.put(macBytes);
        allocate.put(doFinal);
        return allocate.array();
    }

    public byte[] decrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) throws Exception {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int i2 = wrap.get();
        if (i2 == 16) {
            byte[] bArr2 = new byte[i2];
            wrap.get(bArr2);
            int i3 = wrap.get();
            if (i3 == 32) {
                byte[] bArr3 = new byte[i3];
                wrap.get(bArr3);
                byte[] bArr4 = new byte[wrap.remaining()];
                wrap.get(bArr4);
                SecretKey secretKey = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
                byte[] subkey = getSubkey(secretKey, 32);
                if (MessageDigest.isEqual(getMacBytes(getSubkey(secretKey, 16), bArr2, bArr4), bArr3)) {
                    CryptoUtils.ICipher cipher = iCryptoFactory.getCipher("AES/CBC/PKCS7Padding", (String) null);
                    cipher.init(2, new SecretKeySpec(subkey, AesKey.ALGORITHM), new IvParameterSpec(bArr2));
                    return cipher.doFinal(bArr4);
                }
                throw new SecurityException("Could not authenticate MAC value.");
            }
            throw new IllegalArgumentException("Invalid MAC length.");
        }
        throw new IllegalArgumentException("Invalid IV length.");
    }

    private byte[] getMacBytes(byte[] bArr, byte[] bArr2, byte[] bArr3) throws InvalidKeyException, NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, MacUtil.HMAC_SHA256);
        Mac instance = Mac.getInstance(MacUtil.HMAC_SHA256);
        instance.init(secretKeySpec);
        instance.update(bArr2);
        instance.update(bArr3);
        return instance.doFinal();
    }

    /* access modifiers changed from: package-private */
    public byte[] getSubkey(SecretKey secretKey, int i) throws NoSuchAlgorithmException, InvalidKeyException {
        if (i >= 1) {
            Mac instance = Mac.getInstance(MacUtil.HMAC_SHA256);
            instance.init(secretKey);
            int ceil = (int) Math.ceil(((double) i) / ((double) instance.getMacLength()));
            if (ceil <= 255) {
                byte[] bArr = new byte[0];
                ByteBuffer allocate = ByteBuffer.allocate(i);
                int i2 = 0;
                while (i2 < ceil) {
                    instance.update(bArr);
                    i2++;
                    instance.update((byte) i2);
                    bArr = instance.doFinal();
                    int min = Math.min(i, bArr.length);
                    allocate.put(bArr, 0, min);
                    i -= min;
                }
                return allocate.array();
            }
            throw new IllegalArgumentException("Output data length must be maximum of 255 * hash-length.");
        }
        throw new IllegalArgumentException("Output data length must be greater than zero.");
    }
}
