package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class URIResultParser extends ResultParser {
    private static final String ALPHANUM_PART = "[a-zA-Z0-9\\-]";
    private static final Pattern URL_WITHOUT_PROTOCOL_PATTERN = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z0-9\\-]{2,}(:\\d{1,5})?(/|\\?|$)");
    private static final Pattern URL_WITH_PROTOCOL_PATTERN = Pattern.compile("[a-zA-Z0-9]{2,}:");

    public URIParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        if (massagedText.startsWith("URL:") || massagedText.startsWith("URI:")) {
            return new URIParsedResult(massagedText.substring(4).trim(), (String) null);
        }
        String trim = massagedText.trim();
        if (isBasicallyValidURI(trim)) {
            return new URIParsedResult(trim, (String) null);
        }
        return null;
    }

    static boolean isBasicallyValidURI(CharSequence charSequence) {
        Matcher matcher = URL_WITH_PROTOCOL_PATTERN.matcher(charSequence);
        if (matcher.find() && matcher.start() == 0) {
            return true;
        }
        Matcher matcher2 = URL_WITHOUT_PROTOCOL_PATTERN.matcher(charSequence);
        if (!matcher2.find() || matcher2.start() != 0) {
            return false;
        }
        return true;
    }
}
