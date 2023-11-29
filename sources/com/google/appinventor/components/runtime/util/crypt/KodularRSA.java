package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class KodularRSA {
    private static PrivateKey hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static PublicKey f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        KeyPair buildKeyPair = buildKeyPair();
        f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = buildKeyPair.getPublic();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = buildKeyPair.getPrivate();
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            return instance.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return null;
        }
    }

    public static String encrypt(String str) throws Exception {
        try {
            return new String(encrypt(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str));
        } catch (Exception e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        }
    }

    public static String decrypt(String str) throws Exception {
        try {
            return new String(decrypt(f333hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str.getBytes()));
        } catch (Exception e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        }
    }

    public static byte[] encrypt(PrivateKey privateKey, String str) throws Exception {
        try {
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(1, privateKey);
            return instance.doFinal(str.getBytes());
        } catch (Exception e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] decrypt(PublicKey publicKey, byte[] bArr) throws Exception {
        try {
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(2, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return new byte[0];
        }
    }
}
