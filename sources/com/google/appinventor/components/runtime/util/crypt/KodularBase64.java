package com.google.appinventor.components.runtime.util.crypt;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class KodularBase64 {
    public static String encode(String str) {
        return Base64.encodeToString(str.getBytes(), 0);
    }

    public static String decode(String str) {
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
