package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

class CryptoRsaHandler implements CryptoHandler {
    public String getAlgorithm() {
        return "RSA/ECB/PKCS1Padding/2048";
    }

    CryptoRsaHandler() {
    }

    public void generateKey(CryptoUtils.ICryptoFactory iCryptoFactory, String str, Context context) throws Exception {
        Calendar instance = Calendar.getInstance();
        instance.add(1, 1);
        KeyPairGenerator instance2 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
        KeyPairGeneratorSpec.Builder alias = new KeyPairGeneratorSpec.Builder(context).setAlias(str);
        instance2.initialize(alias.setSubject(new X500Principal("CN=" + str)).setStartDate(new Date()).setEndDate(instance.getTime()).setSerialNumber(BigInteger.TEN).setKeySize(2048).build());
        instance2.generateKeyPair();
    }

    private CryptoUtils.ICipher getCipher(CryptoUtils.ICryptoFactory iCryptoFactory, int i) throws Exception {
        return iCryptoFactory.getCipher("RSA/ECB/PKCS1Padding", i >= 23 ? "AndroidKeyStoreBCWorkaround" : "AndroidOpenSSL");
    }

    public byte[] encrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) throws Exception {
        CryptoUtils.ICipher cipher = getCipher(iCryptoFactory, i);
        X509Certificate x509Certificate = (X509Certificate) ((KeyStore.PrivateKeyEntry) entry).getCertificate();
        try {
            x509Certificate.checkValidity();
            cipher.init(1, x509Certificate.getPublicKey());
            return cipher.doFinal(bArr);
        } catch (CertificateExpiredException e) {
            throw new InvalidKeyException(e);
        }
    }

    public byte[] decrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) throws Exception {
        CryptoUtils.ICipher cipher = getCipher(iCryptoFactory, i);
        cipher.init(2, ((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
        return cipher.doFinal(bArr);
    }
}
