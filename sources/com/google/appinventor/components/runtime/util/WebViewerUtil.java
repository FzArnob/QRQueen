package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.microsoft.appcenter.ingestion.models.CommonProperties;

public class WebViewerUtil {
    private static String[] ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = {"Solicitar Permiso", "Esta App", " solicita acceso a la ubicación.", "Permitir", "Rechazar"};
    private static String CURRENT_COUNTRY = "";
    private static String[] ENGLISH = {"Permission Request", "This Application", " would like to access your location.", "Allow", "Denied"};
    private static String[] GERMAN = {"Berechtigungsanfrage", "Diese Anwendung", " möchte auf Ihren Standort zugreifen.", "Zulassen", "Ablehnen"};
    private static String[] PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = {"Requer Permissão", "Esta Aplicação", " gostaria de acessar sua localização.", "Permitir", "Negado"};
    private static String[] moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = {"Toestemming geven", "Deze applicatie", " wil uw locatie weten.", "Toestaan", "Weigeren"};
    private static String[] oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = {"Richiesta autorizzazione", "Questa applicazione", " vorrebbe accedere alla tua posizione.", "Permetti", "Nega"};
    private static String[] tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = {"अनुमति का अनुरोध", "यह अॅपलिकेशन", " आपका स्थान जाँचना चाहेंगी।", "अनुमति दें", "अस्वीकृत करें"};
    private static String[] vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = {"Permintaan Izin", "Aplikasi ini", " ingin mengakses lokasi Anda.", "Mengizinkan", "Ditolak"};
    private static String[] wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = {"Engedélykérés", "Ez az alkalmazás", " hozzáférést kér a tartózkodási helyéhez.", "Engedélyez", "Megtagad"};
    private static String[] yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = {"İzin İsteği", "Bu uygulama", " bulunduğunuz konuma erişmek istiyor.", "İzin Ver", "Reddet"};

    private WebViewerUtil() {
    }

    public static void initialize(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getNetworkCountryIso() != null) {
            CURRENT_COUNTRY = telephonyManager.getNetworkCountryIso();
        } else {
            CURRENT_COUNTRY = "default";
        }
    }

    public static String getPermissionTitleString() {
        return getResult(0);
    }

    public static String getPermissionApplicationString() {
        return getResult(1);
    }

    public static String getPermissionMessageString() {
        return getResult(2);
    }

    public static String getPermissionAllowString() {
        return getResult(3);
    }

    public static String getPermissionDeniedString() {
        return getResult(4);
    }

    private static String getResult(int i) {
        String lowerCase = CURRENT_COUNTRY.toLowerCase();
        lowerCase.hashCode();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case 3123:
                if (lowerCase.equals("at")) {
                    c = 0;
                    break;
                }
                break;
            case 3173:
                if (lowerCase.equals("ch")) {
                    c = 1;
                    break;
                }
                break;
            case ErrorMessages.ERROR_INDEX_MISSING_IN_LIST:
                if (lowerCase.equals("de")) {
                    c = 2;
                    break;
                }
                break;
            case 3246:
                if (lowerCase.equals("es")) {
                    c = 3;
                    break;
                }
                break;
            case 3291:
                if (lowerCase.equals("gb")) {
                    c = 4;
                    break;
                }
                break;
            case 3341:
                if (lowerCase.equals("hu")) {
                    c = 5;
                    break;
                }
                break;
            case 3355:
                if (lowerCase.equals(CommonProperties.ID)) {
                    c = 6;
                    break;
                }
                break;
            case 3356:
                if (lowerCase.equals("ie")) {
                    c = 7;
                    break;
                }
                break;
            case 3365:
                if (lowerCase.equals("in")) {
                    c = 8;
                    break;
                }
                break;
            case 3371:
                if (lowerCase.equals("it")) {
                    c = 9;
                    break;
                }
                break;
            case 3518:
                if (lowerCase.equals("nl")) {
                    c = 10;
                    break;
                }
                break;
            case 3588:
                if (lowerCase.equals("pt")) {
                    c = 11;
                    break;
                }
                break;
            case 3710:
                if (lowerCase.equals("tr")) {
                    c = 12;
                    break;
                }
                break;
            case 3742:
                if (lowerCase.equals("us")) {
                    c = 13;
                    break;
                }
                break;
            case 1544803905:
                if (lowerCase.equals("default")) {
                    c = 14;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return GERMAN[i];
            case 3:
                return ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud[i];
            case 4:
            case 7:
            case 13:
                return ENGLISH[i];
            case 5:
                return wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0[i];
            case 6:
                return vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE[i];
            case 8:
                return tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE[i];
            case 9:
                return oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS[i];
            case 10:
                return moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0[i];
            case 11:
                return PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC[i];
            case 12:
                return yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT[i];
            case 14:
                return ENGLISH[i];
            default:
                return ENGLISH[i];
        }
    }
}
