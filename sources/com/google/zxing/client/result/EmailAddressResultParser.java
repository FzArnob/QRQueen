package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;

public final class EmailAddressResultParser extends ResultParser {
    public EmailAddressParsedResult parse(Result result) {
        String str;
        String massagedText = getMassagedText(result);
        String str2 = null;
        if (massagedText.startsWith("mailto:") || massagedText.startsWith("MAILTO:")) {
            String substring = massagedText.substring(7);
            int indexOf = substring.indexOf(63);
            if (indexOf >= 0) {
                substring = substring.substring(0, indexOf);
            }
            Map<String, String> parseNameValuePairs = parseNameValuePairs(massagedText);
            if (parseNameValuePairs != null) {
                if (substring.length() == 0) {
                    substring = parseNameValuePairs.get("to");
                }
                str2 = parseNameValuePairs.get("subject");
                str = parseNameValuePairs.get("body");
            } else {
                str = null;
            }
            return new EmailAddressParsedResult(substring, str2, str, massagedText);
        } else if (!EmailDoCoMoResultParser.isBasicallyValidEmailAddress(massagedText)) {
            return null;
        } else {
            return new EmailAddressParsedResult(massagedText, (String) null, (String) null, "mailto:" + massagedText);
        }
    }
}
