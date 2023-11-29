package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.Arrays;

public class KodularGDPRUtil {
    private static final String[] qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = {"at", "be", "bg", "cy", "cz", "de", "dk", "ee", "es", "fi", "fr", "gb", "gr", "hr", "hu", "ie", "it", "lt", "lu", "lv", "mt", "nl", "pl", "pt", "ro", "se", "si", "sk"};

    private KodularGDPRUtil() {
    }

    public static boolean isRequestLocationInEurope(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getNetworkCountryIso() == null) {
            Log.e("Kodular GDPR Util", "It was not possible to get the country code. We returned the value true as default to cover the EU privacy policy.");
            return true;
        }
        String networkCountryIso = telephonyManager.getNetworkCountryIso();
        Log.i("Kodular GDPR Util", "Current user country is: ".concat(String.valueOf(networkCountryIso)));
        if (networkCountryIso == null) {
            Log.w("Kodular GDPR Util", "There was a error to retrieve the current user location.");
            return true;
        }
        boolean contains = Arrays.asList(qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE).contains(networkCountryIso);
        Log.i("Kodular GDPR Util", "Current user is in EUROPE: ".concat(String.valueOf(contains)));
        return contains;
    }
}
