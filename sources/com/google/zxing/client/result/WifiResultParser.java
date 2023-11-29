package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class WifiResultParser extends ResultParser {
    public WifiParsedResult parse(Result result) {
        String matchSinglePrefixedField;
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("WIFI:") || (matchSinglePrefixedField = matchSinglePrefixedField("S:", massagedText, ';', false)) == null || matchSinglePrefixedField.length() == 0) {
            return null;
        }
        String matchSinglePrefixedField2 = matchSinglePrefixedField("P:", massagedText, ';', false);
        String matchSinglePrefixedField3 = matchSinglePrefixedField("T:", massagedText, ';', false);
        if (matchSinglePrefixedField3 == null) {
            matchSinglePrefixedField3 = "nopass";
        }
        return new WifiParsedResult(matchSinglePrefixedField3, matchSinglePrefixedField, matchSinglePrefixedField2, Boolean.parseBoolean(matchSinglePrefixedField("B:", massagedText, ';', false)));
    }
}
