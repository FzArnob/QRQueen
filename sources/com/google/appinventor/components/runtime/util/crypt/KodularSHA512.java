package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA512 {
    public static String sha512(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-512");
            instance.reset();
            instance.update(str.getBytes("utf8"));
            return String.format("%040x", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        } catch (UnsupportedEncodingException e2) {
            Log.e("MakeroidCrypt", e2.getMessage());
            return "";
        }
    }
}
