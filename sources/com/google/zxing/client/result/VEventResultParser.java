package com.google.zxing.client.result;

import java.util.List;

public final class VEventResultParser extends ResultParser {
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.client.result.CalendarParsedResult parse(com.google.zxing.Result r17) {
        /*
            r16 = this;
            java.lang.String r0 = getMassagedText(r17)
            java.lang.String r1 = "BEGIN:VEVENT"
            int r1 = r0.indexOf(r1)
            r2 = 0
            if (r1 >= 0) goto L_0x000e
            return r2
        L_0x000e:
            java.lang.String r1 = "SUMMARY"
            r3 = 1
            java.lang.String r5 = matchSingleVCardPrefixedField(r1, r0, r3)
            java.lang.String r1 = "DTSTART"
            java.lang.String r6 = matchSingleVCardPrefixedField(r1, r0, r3)
            if (r6 != 0) goto L_0x001e
            return r2
        L_0x001e:
            java.lang.String r1 = "DTEND"
            java.lang.String r7 = matchSingleVCardPrefixedField(r1, r0, r3)
            java.lang.String r1 = "LOCATION"
            java.lang.String r8 = matchSingleVCardPrefixedField(r1, r0, r3)
            java.lang.String r1 = "ORGANIZER"
            java.lang.String r1 = matchSingleVCardPrefixedField(r1, r0, r3)
            java.lang.String r9 = stripMailto(r1)
            java.lang.String r1 = "ATTENDEE"
            java.lang.String[] r10 = matchVCardPrefixedField(r1, r0, r3)
            r1 = 0
            if (r10 == 0) goto L_0x004c
            r4 = 0
        L_0x003e:
            int r11 = r10.length
            if (r4 >= r11) goto L_0x004c
            r11 = r10[r4]
            java.lang.String r11 = stripMailto(r11)
            r10[r4] = r11
            int r4 = r4 + 1
            goto L_0x003e
        L_0x004c:
            java.lang.String r4 = "DESCRIPTION"
            java.lang.String r11 = matchSingleVCardPrefixedField(r4, r0, r3)
            java.lang.String r4 = "GEO"
            java.lang.String r0 = matchSingleVCardPrefixedField(r4, r0, r3)
            r12 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            if (r0 != 0) goto L_0x005e
            r14 = r12
            goto L_0x0076
        L_0x005e:
            r4 = 59
            int r4 = r0.indexOf(r4)
            java.lang.String r1 = r0.substring(r1, r4)     // Catch:{ NumberFormatException -> 0x007d }
            double r12 = java.lang.Double.parseDouble(r1)     // Catch:{ NumberFormatException -> 0x007d }
            int r4 = r4 + r3
            java.lang.String r0 = r0.substring(r4)     // Catch:{ NumberFormatException -> 0x007d }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x007d }
            r14 = r0
        L_0x0076:
            com.google.zxing.client.result.CalendarParsedResult r0 = new com.google.zxing.client.result.CalendarParsedResult     // Catch:{  }
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14)     // Catch:{  }
            return r0
        L_0x007d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VEventResultParser.parse(com.google.zxing.Result):com.google.zxing.client.result.CalendarParsedResult");
    }

    private static String matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z) {
        List<String> matchSingleVCardPrefixedField = VCardResultParser.matchSingleVCardPrefixedField(charSequence, str, z, false);
        if (matchSingleVCardPrefixedField == null || matchSingleVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchSingleVCardPrefixedField.get(0);
    }

    private static String[] matchVCardPrefixedField(CharSequence charSequence, String str, boolean z) {
        List<List<String>> matchVCardPrefixedField = VCardResultParser.matchVCardPrefixedField(charSequence, str, z, false);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        int size = matchVCardPrefixedField.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) matchVCardPrefixedField.get(i).get(0);
        }
        return strArr;
    }

    private static String stripMailto(String str) {
        if (str != null) {
            return (str.startsWith("mailto:") || str.startsWith("MAILTO:")) ? str.substring(7) : str;
        }
        return str;
    }
}
