package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;

public final class ExpandedProductResultParser extends ResultParser {
    public ExpandedProductParsedResult parse(Result result) {
        String massagedText;
        int i;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED || (massagedText = getMassagedText(result)) == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        int i2 = 0;
        while (i2 < massagedText.length()) {
            String findAIvalue = findAIvalue(i2, massagedText);
            if (findAIvalue == null) {
                return null;
            }
            int length = i2 + findAIvalue.length() + 2;
            String str14 = str12;
            String findValue = findValue(length, massagedText);
            int length2 = length + findValue.length();
            String str15 = massagedText;
            if ("00".equals(findAIvalue)) {
                i = length2;
                str2 = findValue;
            } else if ("01".equals(findAIvalue)) {
                i = length2;
                str = findValue;
            } else if ("10".equals(findAIvalue)) {
                i = length2;
                str3 = findValue;
            } else if ("11".equals(findAIvalue)) {
                i = length2;
                str4 = findValue;
            } else if ("13".equals(findAIvalue)) {
                i = length2;
                str5 = findValue;
            } else if ("15".equals(findAIvalue)) {
                i = length2;
                str6 = findValue;
            } else if ("17".equals(findAIvalue)) {
                i = length2;
                str7 = findValue;
            } else {
                i = length2;
                if ("3100".equals(findAIvalue) || "3101".equals(findAIvalue) || "3102".equals(findAIvalue) || "3103".equals(findAIvalue) || "3104".equals(findAIvalue) || "3105".equals(findAIvalue) || "3106".equals(findAIvalue) || "3107".equals(findAIvalue) || "3108".equals(findAIvalue) || "3109".equals(findAIvalue)) {
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                } else if ("3200".equals(findAIvalue) || "3201".equals(findAIvalue) || "3202".equals(findAIvalue) || "3203".equals(findAIvalue) || "3204".equals(findAIvalue) || "3205".equals(findAIvalue) || "3206".equals(findAIvalue) || "3207".equals(findAIvalue) || "3208".equals(findAIvalue) || "3209".equals(findAIvalue)) {
                    str10 = findAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                } else if ("3920".equals(findAIvalue) || "3921".equals(findAIvalue) || "3922".equals(findAIvalue) || "3923".equals(findAIvalue)) {
                    str11 = findValue;
                    str12 = findAIvalue.substring(3);
                    massagedText = str15;
                    i2 = i;
                } else if (!"3930".equals(findAIvalue) && !"3931".equals(findAIvalue) && !"3932".equals(findAIvalue) && !"3933".equals(findAIvalue)) {
                    hashMap.put(findAIvalue, findValue);
                } else if (findValue.length() < 4) {
                    return null;
                } else {
                    str11 = findValue.substring(3);
                    str13 = findValue.substring(0, 3);
                    str12 = findAIvalue.substring(3);
                    massagedText = str15;
                    i2 = i;
                }
                str8 = findValue;
                str12 = str14;
                massagedText = str15;
                i2 = i;
            }
            str12 = str14;
            massagedText = str15;
            i2 = i;
        }
        String str16 = str12;
        return new ExpandedProductParsedResult(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, hashMap);
    }

    private static String findAIvalue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
