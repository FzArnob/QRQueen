package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jose4j.lang.HashUtil;

public class KodularSHA256 {
    public static String sha256(String str) {
        try {
            return new String(MessageDigest.getInstance(HashUtil.SHA_256).digest(str.getBytes("UTF-8")), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        } catch (UnsupportedEncodingException e2) {
            Log.e("MakeroidCrypt", e2.getMessage());
            return "";
        }
    }
}
