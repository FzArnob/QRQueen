package com.microsoft.appcenter.utils.crypto;

import android.content.Context;
import com.microsoft.appcenter.utils.crypto.CryptoUtils;
import java.security.KeyStore;

class CryptoNoOpHandler implements CryptoHandler {
    public byte[] decrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) {
        return bArr;
    }

    public byte[] encrypt(CryptoUtils.ICryptoFactory iCryptoFactory, int i, KeyStore.Entry entry, byte[] bArr) {
        return bArr;
    }

    public void generateKey(CryptoUtils.ICryptoFactory iCryptoFactory, String str, Context context) {
    }

    public String getAlgorithm() {
        return "None";
    }

    CryptoNoOpHandler() {
    }
}
