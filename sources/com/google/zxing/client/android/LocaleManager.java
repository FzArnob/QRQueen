package com.google.zxing.client.android;

import android.content.Context;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class LocaleManager {
    private static final String DEFAULT_COUNTRY = "US";
    private static final String DEFAULT_LANGUAGE = "en";
    private static final String DEFAULT_TLD = "com";
    private static final Map<String, String> GOOGLE_BOOK_SEARCH_COUNTRY_TLD;
    private static final Map<String, String> GOOGLE_COUNTRY_TLD;
    private static final Map<String, String> GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD;
    private static final Collection<String> TRANSLATED_HELP_ASSET_LANGUAGES = Arrays.asList(new String[]{"de", DEFAULT_LANGUAGE, "es", "fr", "it", "ja", "ko", "nl", "pt", "ru", "zh-rCN", "zh-rTW"});

    private static String doGetTLD(Map<String, String> map, Context context) {
        return DEFAULT_TLD;
    }

    static {
        HashMap hashMap = new HashMap();
        GOOGLE_COUNTRY_TLD = hashMap;
        hashMap.put("AR", "com.ar");
        hashMap.put("AU", "com.au");
        hashMap.put("BR", "com.br");
        hashMap.put("BG", "bg");
        hashMap.put(Locale.CANADA.getCountry(), "ca");
        hashMap.put(Locale.CHINA.getCountry(), "cn");
        hashMap.put("CZ", "cz");
        hashMap.put("DK", "dk");
        hashMap.put("FI", "fi");
        hashMap.put(Locale.FRANCE.getCountry(), "fr");
        hashMap.put(Locale.GERMANY.getCountry(), "de");
        hashMap.put("GR", "gr");
        hashMap.put("HU", "hu");
        hashMap.put("ID", "co.id");
        hashMap.put("IL", "co.il");
        hashMap.put(Locale.ITALY.getCountry(), "it");
        hashMap.put(Locale.JAPAN.getCountry(), "co.jp");
        hashMap.put(Locale.KOREA.getCountry(), "co.kr");
        hashMap.put("NL", "nl");
        hashMap.put("PL", "pl");
        hashMap.put("PT", "pt");
        hashMap.put("RU", "ru");
        hashMap.put("SK", "sk");
        hashMap.put("SI", "si");
        hashMap.put("ES", "es");
        hashMap.put("SE", "se");
        hashMap.put(Locale.TAIWAN.getCountry(), "tw");
        hashMap.put("TR", "com.tr");
        hashMap.put(Locale.UK.getCountry(), "co.uk");
        hashMap.put(Locale.US.getCountry(), DEFAULT_TLD);
        HashMap hashMap2 = new HashMap();
        GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD = hashMap2;
        hashMap2.put("AU", "com.au");
        hashMap2.put(Locale.CHINA.getCountry(), "cn");
        hashMap2.put(Locale.FRANCE.getCountry(), "fr");
        hashMap2.put(Locale.GERMANY.getCountry(), "de");
        hashMap2.put(Locale.ITALY.getCountry(), "it");
        hashMap2.put(Locale.JAPAN.getCountry(), "co.jp");
        hashMap2.put("NL", "nl");
        hashMap2.put("ES", "es");
        hashMap2.put(Locale.UK.getCountry(), "co.uk");
        hashMap2.put(Locale.US.getCountry(), DEFAULT_TLD);
        GOOGLE_BOOK_SEARCH_COUNTRY_TLD = hashMap;
    }

    private LocaleManager() {
    }

    public static String getCountryTLD(Context context) {
        return doGetTLD(GOOGLE_COUNTRY_TLD, context);
    }

    public static String getProductSearchCountryTLD(Context context) {
        return doGetTLD(GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD, context);
    }

    public static String getBookSearchCountryTLD(Context context) {
        return doGetTLD(GOOGLE_BOOK_SEARCH_COUNTRY_TLD, context);
    }

    public static boolean isBookSearchUrl(String str) {
        return str.startsWith("http://google.com/books") || str.startsWith("http://books.google.");
    }

    private static String getSystemCountry() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return DEFAULT_COUNTRY;
        }
        return locale.getCountry();
    }

    private static String getSystemLanguage() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return DEFAULT_LANGUAGE;
        }
        String language = locale.getLanguage();
        if (!Locale.SIMPLIFIED_CHINESE.getLanguage().equals(language)) {
            return language;
        }
        return language + "-r" + getSystemCountry();
    }

    public static String getTranslatedAssetLanguage() {
        return getSystemLanguage();
    }
}
