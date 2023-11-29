package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import com.microsoft.appcenter.Constants;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class KodularPBKDF2 {
    public static final int HASH_ALGORITHM_INDEX = 0;
    public static final int HASH_SECTIONS = 5;
    public static final int HASH_SIZE_INDEX = 2;
    public static final int ITERATION_INDEX = 1;
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int PBKDF2_INDEX = 4;
    public static final int SALT_INDEX = 3;

    public static class InvalidHashException extends Exception {
        public InvalidHashException(String str) {
            super(str);
        }

        public InvalidHashException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class CannotPerformOperationException extends Exception {
        public CannotPerformOperationException(String str) {
            super(str);
        }

        public CannotPerformOperationException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static String createHash(String str, int i, int i2, int i3) throws CannotPerformOperationException {
        return createHash(str.toCharArray(), i, i2, i3);
    }

    public static String createHash(char[] cArr, int i, int i2, int i3) throws CannotPerformOperationException {
        byte[] bArr = new byte[i2];
        new SecureRandom().nextBytes(bArr);
        byte[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(cArr, bArr, i, i3);
        int length = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.length;
        return "sha1:" + i + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + length + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + DatatypeConverter.printBase64Binary(bArr) + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + DatatypeConverter.printBase64Binary(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
    }

    public static boolean verifyPassword(String str, String str2) throws CannotPerformOperationException, InvalidHashException {
        return verifyPassword(str.toCharArray(), str2);
    }

    public static boolean verifyPassword(char[] cArr, String str) throws CannotPerformOperationException, InvalidHashException {
        String[] split = str.split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        if (split.length != 5) {
            throw new InvalidHashException("Fields are missing from the password hash.");
        } else if (split[0].equals("sha1")) {
            try {
                int parseInt = Integer.parseInt(split[1]);
                if (parseInt > 0) {
                    try {
                        byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(split[3]);
                        try {
                            byte[] parseBase64Binary2 = DatatypeConverter.parseBase64Binary(split[4]);
                            try {
                                if (Integer.parseInt(split[2]) == parseBase64Binary2.length) {
                                    return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(parseBase64Binary2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(cArr, parseBase64Binary, parseInt, parseBase64Binary2.length));
                                }
                                throw new InvalidHashException("Hash length doesn't match stored hash length.");
                            } catch (NumberFormatException e) {
                                Log.e("MakeroidCrypt", e.getMessage());
                                return false;
                            }
                        } catch (IllegalArgumentException e2) {
                            Log.e("MakeroidCrypt", e2.getMessage());
                            return false;
                        }
                    } catch (IllegalArgumentException e3) {
                        Log.e("MakeroidCrypt", e3.getMessage());
                        return false;
                    }
                } else {
                    throw new InvalidHashException("Invalid number of iterations. Must be >= 1.");
                }
            } catch (NumberFormatException e4) {
                Log.e("MakeroidCrypt", e4.getMessage());
                return false;
            }
        } else {
            throw new CannotPerformOperationException("Unsupported hash type.");
        }
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, byte[] bArr2) {
        byte length = bArr.length ^ bArr2.length;
        int i = 0;
        while (i < bArr.length && i < bArr2.length) {
            length |= bArr[i] ^ bArr2[i];
            i++;
        }
        if (length == 0) {
            return true;
        }
        return false;
    }

    private static byte[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(char[] cArr, byte[] bArr, int i, int i2) throws CannotPerformOperationException {
        try {
            return SecretKeyFactory.getInstance(PBKDF2_ALGORITHM).generateSecret(new PBEKeySpec(cArr, bArr, i, i2 << 3)).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            Log.e("MakeroidCrypt", e.getMessage());
            return new byte[0];
        } catch (InvalidKeySpecException e2) {
            Log.e("MakeroidCrypt", e2.getMessage());
            return new byte[0];
        }
    }
}
