package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jose4j.keys.AesKey;

public class KodularAES128 {
    public static String encode(String str, String str2) {
        String format = String.format("%16s", new Object[]{String.format("%.16s", new Object[]{str2})});
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            byte[] bytes = format.getBytes("UTF-8");
            instance.init(1, new SecretKeySpec(bytes, AesKey.ALGORITHM), new IvParameterSpec(bytes));
            return Base64.encodeToString(instance.doFinal(str.getBytes(InternalZipConstants.CHARSET_UTF8)), 0);
        } catch (Exception e) {
            Log.d("KodularAES128", "Encrypt Exception : " + e.getMessage());
            return "";
        }
    }

    public static String decode(String str, String str2) {
        String format = String.format("%16s", new Object[]{String.format("%.16s", new Object[]{str2})});
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            byte[] bytes = format.getBytes("UTF-8");
            instance.init(2, new SecretKeySpec(bytes, AesKey.ALGORITHM), new IvParameterSpec(bytes));
            return new String(instance.doFinal(Base64.decode(str.getBytes(InternalZipConstants.CHARSET_UTF8), 0)), "UTF-8");
        } catch (Exception e) {
            Log.d("KodularAES128", "decrypt Exception : " + e.getMessage());
            return "";
        }
    }

    public static String generateKey() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(AesKey.ALGORITHM);
            instance.init(128);
            return Base64.encodeToString(instance.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException e) {
            Log.d("KodularAES128", "generateKey: " + e.getMessage());
            return "";
        }
    }
}
