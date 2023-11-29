package com.google.appinventor.components.runtime.util.crypt;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Random;

public class CryptoPref {
    public static String getSalt(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CRYPTO_PREF", 0);
        String string = sharedPreferences.getString("TAG_SALT", (String) null);
        if (string != null) {
            return string;
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder(100);
        for (int i = 0; i < 100; i++) {
            sb.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz|!£$%&/=@#".charAt(random.nextInt(72)));
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("TAG_SALT", sb.toString());
        edit.commit();
        return sb.toString();
    }
}
