package com.google.zxing.client.result;

import com.google.zxing.Result;

public final class AddressBookDoCoMoResultParser extends AbstractDoCoMoResultParser {
    public AddressBookParsedResult parse(Result result) {
        String[] matchDoCoMoPrefixedField;
        String massagedText = getMassagedText(result);
        if (!massagedText.startsWith("MECARD:") || (matchDoCoMoPrefixedField = matchDoCoMoPrefixedField("N:", massagedText, true)) == null) {
            return null;
        }
        String parseName = parseName(matchDoCoMoPrefixedField[0]);
        String matchSingleDoCoMoPrefixedField = matchSingleDoCoMoPrefixedField("SOUND:", massagedText, true);
        String[] matchDoCoMoPrefixedField2 = matchDoCoMoPrefixedField("TEL:", massagedText, true);
        String[] matchDoCoMoPrefixedField3 = matchDoCoMoPrefixedField("EMAIL:", massagedText, true);
        String matchSingleDoCoMoPrefixedField2 = matchSingleDoCoMoPrefixedField("NOTE:", massagedText, false);
        String[] matchDoCoMoPrefixedField4 = matchDoCoMoPrefixedField("ADR:", massagedText, true);
        String matchSingleDoCoMoPrefixedField3 = matchSingleDoCoMoPrefixedField("BDAY:", massagedText, true);
        return new AddressBookParsedResult(maybeWrap(parseName), matchSingleDoCoMoPrefixedField, matchDoCoMoPrefixedField2, (String[]) null, matchDoCoMoPrefixedField3, (String[]) null, (String) null, matchSingleDoCoMoPrefixedField2, matchDoCoMoPrefixedField4, (String[]) null, matchSingleDoCoMoPrefixedField("ORG:", massagedText, true), (matchSingleDoCoMoPrefixedField3 == null || isStringOfDigits(matchSingleDoCoMoPrefixedField3, 8)) ? matchSingleDoCoMoPrefixedField3 : null, (String) null, matchSingleDoCoMoPrefixedField("URL:", massagedText, true));
    }

    private static String parseName(String str) {
        int indexOf = str.indexOf(44);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(indexOf + 1) + ' ' + str.substring(0, indexOf);
    }
}
