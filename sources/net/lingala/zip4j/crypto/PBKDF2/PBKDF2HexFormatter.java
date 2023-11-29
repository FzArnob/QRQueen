package net.lingala.zip4j.crypto.PBKDF2;

import com.microsoft.appcenter.Constants;

class PBKDF2HexFormatter {
    PBKDF2HexFormatter() {
    }

    public boolean fromString(PBKDF2Parameters pBKDF2Parameters, String str) {
        String[] split;
        if (pBKDF2Parameters == null || str == null || (split = str.split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)) == null || split.length != 3) {
            return true;
        }
        byte[] hex2bin = BinTools.hex2bin(split[0]);
        int parseInt = Integer.parseInt(split[1]);
        byte[] hex2bin2 = BinTools.hex2bin(split[2]);
        pBKDF2Parameters.setSalt(hex2bin);
        pBKDF2Parameters.setIterationCount(parseInt);
        pBKDF2Parameters.setDerivedKey(hex2bin2);
        return false;
    }

    public String toString(PBKDF2Parameters pBKDF2Parameters) {
        return new StringBuffer(String.valueOf(BinTools.bin2hex(pBKDF2Parameters.getSalt()))).append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(String.valueOf(pBKDF2Parameters.getIterationCount())).append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR).append(BinTools.bin2hex(pBKDF2Parameters.getDerivedKey())).toString();
    }
}
