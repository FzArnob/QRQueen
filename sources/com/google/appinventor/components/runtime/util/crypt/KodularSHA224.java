package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA224 {
    public static String sha224(String str) {
        try {
            String bigInteger = new BigInteger(1, MessageDigest.getInstance("SHA-224").digest(str.getBytes())).toString(16);
            while (bigInteger.length() < 32) {
                bigInteger = "0".concat(String.valueOf(bigInteger));
            }
            return bigInteger;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        }
    }
}
