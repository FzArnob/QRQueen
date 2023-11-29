package com.google.appinventor.components.runtime.util.crypt;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.jose4j.keys.AesKey;

public class KodularAES256 {
    private static String IwgfES1cdIS4MWzG0ZcJi0aZu6bnPTqO1J6Lch3joHWe8768gpNonHxbl2D4zbWA = "PBKDF2WithHmacSHA256";
    private static int Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5 = 65536;
    private static String iydqlq85U6KFMIBI7LRgMfKfWOWXdlnJs0rL9QevzYsIGMKLioEecnL9YVjuTN9v = "AES/CBC/PKCS5Padding";
    private static String nEkPfLDGYD22bahiHhpKxaqa4vdjROQiBAWGj9Zqw3V6ag4osXolkZLRAXiafTD = "AES";
    private static int qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww = 256;
    private static byte[] wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static String encode(String str, String str2, Context context) {
        try {
            byte[] bytes = CryptoPref.getSalt(context).getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance(IwgfES1cdIS4MWzG0ZcJi0aZu6bnPTqO1J6Lch3joHWe8768gpNonHxbl2D4zbWA).generateSecret(new PBEKeySpec(str2.toCharArray(), bytes, Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5, qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww)).getEncoded(), nEkPfLDGYD22bahiHhpKxaqa4vdjROQiBAWGj9Zqw3V6ag4osXolkZLRAXiafTD);
            Cipher instance = Cipher.getInstance(iydqlq85U6KFMIBI7LRgMfKfWOWXdlnJs0rL9QevzYsIGMKLioEecnL9YVjuTN9v);
            instance.init(1, secretKeySpec, ivParameterSpec);
            return Base64.encodeToString(instance.doFinal(str.getBytes("UTF-8")), 0);
        } catch (Exception e) {
            Log.d("KodularAES256", "Error while encrypting: " + e.toString());
            return "";
        }
    }

    public static String decode(String str, String str2, Context context) {
        String salt = CryptoPref.getSalt(context);
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance(IwgfES1cdIS4MWzG0ZcJi0aZu6bnPTqO1J6Lch3joHWe8768gpNonHxbl2D4zbWA).generateSecret(new PBEKeySpec(str2.toCharArray(), salt.getBytes(), Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5, qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww)).getEncoded(), nEkPfLDGYD22bahiHhpKxaqa4vdjROQiBAWGj9Zqw3V6ag4osXolkZLRAXiafTD);
            Cipher instance = Cipher.getInstance(iydqlq85U6KFMIBI7LRgMfKfWOWXdlnJs0rL9QevzYsIGMKLioEecnL9YVjuTN9v);
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(Base64.decode(str.getBytes(), 0)));
        } catch (Exception e) {
            Log.d("KodularAES256", "Error while decrypting: " + e.toString());
            return "";
        }
    }

    public static String generateKey() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(AesKey.ALGORITHM);
            instance.init(256);
            return Base64.encodeToString(instance.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException e) {
            Log.d("KodularAES256", "generateKey: " + e.getMessage());
            return "";
        }
    }
}
