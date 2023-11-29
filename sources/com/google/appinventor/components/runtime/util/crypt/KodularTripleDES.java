package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class KodularTripleDES {
    public static String encode(String str, String str2) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] copyOf = Arrays.copyOf(MessageDigest.getInstance("md5").digest(str2.getBytes("utf-8")), 24);
        int i = 16;
        for (int i2 = 0; i2 < 8; i2++) {
            copyOf[i] = copyOf[i2];
            i++;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(copyOf, "DESede");
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(1, secretKeySpec);
        return new String(Base64.encode(instance.doFinal(str.getBytes("utf-8")), 0));
    }

    public static String decode(String str, String str2) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (str == null) {
            return "";
        }
        byte[] decode = Base64.decode(str, 0);
        byte[] copyOf = Arrays.copyOf(MessageDigest.getInstance("MD5").digest(str2.getBytes("utf-8")), 24);
        int i = 16;
        for (int i2 = 0; i2 < 8; i2++) {
            copyOf[i] = copyOf[i2];
            i++;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(copyOf, "DESede");
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(2, secretKeySpec);
        return new String(instance.doFinal(decode), "UTF-8");
    }
}
