package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA384 {
    public static String sha384(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-384").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & Ev3Constants.Opcode.TST) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        } catch (UnsupportedEncodingException e2) {
            Log.e("MakeroidCrypt", e2.getMessage());
            return "";
        }
    }
}
