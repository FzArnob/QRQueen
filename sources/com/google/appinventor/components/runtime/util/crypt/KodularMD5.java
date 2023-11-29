package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularMD5 {
    public static String createHash(String str) {
        try {
            String bigInteger = new BigInteger(1, MessageDigest.getInstance("MD5").digest(str.getBytes())).toString(16);
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
