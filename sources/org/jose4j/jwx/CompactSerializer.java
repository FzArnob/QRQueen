package org.jose4j.jwx;

public class CompactSerializer {
    private static final String EMPTY_STRING = "";
    private static final String PERIOD_SEPARATOR = ".";
    private static final String PERIOD_SEPARATOR_REGEX = "\\.";

    public static String[] deserialize(String str) {
        String[] split = str.split(PERIOD_SEPARATOR_REGEX);
        if (!str.endsWith(PERIOD_SEPARATOR)) {
            return split;
        }
        String[] strArr = new String[(split.length + 1)];
        System.arraycopy(split, 0, strArr, 0, split.length);
        strArr[split.length] = "";
        return strArr;
    }

    public static String serialize(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str == null) {
                str = "";
            }
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(PERIOD_SEPARATOR);
            }
        }
        return sb.toString();
    }
}
