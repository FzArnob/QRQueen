package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KodularSHA1 {
    public static String sha1(String str) throws NoSuchAlgorithmException {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toString((b & Ev3Constants.Opcode.TST) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return "";
        }
    }
}
