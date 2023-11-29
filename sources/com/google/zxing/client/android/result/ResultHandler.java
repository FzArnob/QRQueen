package com.google.zxing.client.android.result;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import com.google.zxing.Result;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.Locale;

public abstract class ResultHandler {
    private static final String[] ADDRESS_TYPE_STRINGS = {"home", "work"};
    private static final int[] ADDRESS_TYPE_VALUES = {1, 2};
    private static final String[] EMAIL_TYPE_STRINGS = {"home", "work", "mobile"};
    private static final int[] EMAIL_TYPE_VALUES = {1, 2, 4};
    private static final String GOOGLE_SHOPPER_ACTIVITY = "com.google.android.apps.shopper.results.SearchResultsActivity";
    private static final String GOOGLE_SHOPPER_PACKAGE = "com.google.android.apps.shopper";
    private static final String MARKET_REFERRER_SUFFIX = "&referrer=utm_source%3Dbarcodescanner%26utm_medium%3Dapps%26utm_campaign%3Dscan";
    private static final String MARKET_URI_PREFIX = "market://details?id=";
    public static final int MAX_BUTTON_COUNT = 4;
    private static final int NO_TYPE = -1;
    private static final String[] PHONE_TYPE_STRINGS = {"home", "work", "mobile", "fax", "pager", "main"};
    private static final int[] PHONE_TYPE_VALUES = {1, 3, 2, 4, 6, 12};
    private static final String TAG = "ResultHandler";
    private final Activity activity;
    private final String customProductSearch;
    private final Result rawResult;
    private final ParsedResult result;
    private final DialogInterface.OnClickListener shopperMarketListener;

    private String parseCustomSearchURL() {
        return null;
    }

    public boolean areContentsSecure() {
        return false;
    }

    public abstract int getButtonCount();

    public abstract int getButtonText(int i);

    public abstract int getDisplayTitle();

    public abstract void handleButtonPress(int i);

    /* access modifiers changed from: package-private */
    public final void openGoogleShopper(String str) {
    }

    /* access modifiers changed from: package-private */
    public final void searchBookContents(String str) {
    }

    /* access modifiers changed from: package-private */
    public final void sendMMSFromUri(String str, String str2, String str3) {
    }

    /* access modifiers changed from: package-private */
    public final void shareByEmail(String str) {
    }

    /* access modifiers changed from: package-private */
    public final void shareBySMS(String str) {
    }

    /* access modifiers changed from: package-private */
    public void showGoogleShopperButton(View.OnClickListener onClickListener) {
    }

    ResultHandler(Activity activity2, ParsedResult parsedResult) {
        this(activity2, parsedResult, (Result) null);
    }

    ResultHandler(Activity activity2, ParsedResult parsedResult, Result result2) {
        this.shopperMarketListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ResultHandler.this.launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.android.apps.shopper&referrer=utm_source%3Dbarcodescanner%26utm_medium%3Dapps%26utm_campaign%3Dscan")));
            }
        };
        this.result = parsedResult;
        this.activity = activity2;
        this.rawResult = result2;
        this.customProductSearch = parseCustomSearchURL();
    }

    public ParsedResult getResult() {
        return this.result;
    }

    /* access modifiers changed from: package-private */
    public boolean hasCustomProductSearch() {
        return this.customProductSearch != null;
    }

    /* access modifiers changed from: package-private */
    public Activity getActivity() {
        return this.activity;
    }

    public CharSequence getDisplayContents() {
        return this.result.getDisplayResult().replace("\r", "");
    }

    public final ParsedResultType getType() {
        return this.result.getType();
    }

    /* access modifiers changed from: package-private */
    public final void addPhoneOnlyContact(String[] strArr, String[] strArr2) {
        addContact((String[]) null, (String) null, strArr, strArr2, (String[]) null, (String[]) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final void addEmailOnlyContact(String[] strArr, String[] strArr2) {
        addContact((String[]) null, (String) null, (String[]) null, (String[]) null, strArr, strArr2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final void addContact(String[] strArr, String str, String[] strArr2, String[] strArr3, String[] strArr4, String[] strArr5, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        int addressContractType;
        int emailContractType;
        int phoneContractType;
        String[] strArr6 = strArr2;
        String[] strArr7 = strArr3;
        String[] strArr8 = strArr4;
        String[] strArr9 = strArr5;
        Intent intent = new Intent("android.intent.action.INSERT_OR_EDIT", ContactsContract.Contacts.CONTENT_URI);
        intent.setType("vnd.android.cursor.item/contact");
        putExtra(intent, CommonProperties.NAME, strArr != null ? strArr[0] : null);
        String str10 = str;
        putExtra(intent, "phonetic_name", str);
        int min = Math.min(strArr6 != null ? strArr6.length : 0, Contents.PHONE_KEYS.length);
        for (int i = 0; i < min; i++) {
            putExtra(intent, Contents.PHONE_KEYS[i], strArr6[i]);
            if (strArr7 != null && i < strArr7.length && (phoneContractType = toPhoneContractType(strArr7[i])) >= 0) {
                intent.putExtra(Contents.PHONE_TYPE_KEYS[i], phoneContractType);
            }
        }
        int min2 = Math.min(strArr8 != null ? strArr8.length : 0, Contents.EMAIL_KEYS.length);
        for (int i2 = 0; i2 < min2; i2++) {
            putExtra(intent, Contents.EMAIL_KEYS[i2], strArr8[i2]);
            if (strArr9 != null && i2 < strArr9.length && (emailContractType = toEmailContractType(strArr9[i2])) >= 0) {
                intent.putExtra(Contents.EMAIL_TYPE_KEYS[i2], emailContractType);
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr10 = {str8, str9, str2};
        for (int i3 = 0; i3 < 3; i3++) {
            String str11 = strArr10[i3];
            if (str11 != null) {
                if (sb.length() > 0) {
                    sb.append(10);
                }
                sb.append(str11);
            }
        }
        if (sb.length() > 0) {
            putExtra(intent, "notes", sb.toString());
        }
        putExtra(intent, "im_handle", str3);
        putExtra(intent, "postal", str4);
        if (str5 != null && (addressContractType = toAddressContractType(str5)) >= 0) {
            intent.putExtra("postal_type", addressContractType);
        }
        putExtra(intent, "company", str6);
        putExtra(intent, "job_title", str7);
        launchIntent(intent);
    }

    private static int toEmailContractType(String str) {
        return doToContractType(str, EMAIL_TYPE_STRINGS, EMAIL_TYPE_VALUES);
    }

    private static int toPhoneContractType(String str) {
        return doToContractType(str, PHONE_TYPE_STRINGS, PHONE_TYPE_VALUES);
    }

    private static int toAddressContractType(String str) {
        return doToContractType(str, ADDRESS_TYPE_STRINGS, ADDRESS_TYPE_VALUES);
    }

    private static int doToContractType(String str, String[] strArr, int[] iArr) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (str.startsWith(str2) || str.startsWith(str2.toUpperCase(Locale.ENGLISH))) {
                return iArr[i];
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final void sendEmail(String str, String str2, String str3) {
        sendEmailFromUri("mailto:" + str, str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public final void sendEmailFromUri(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("android.intent.action.SEND", Uri.parse(str));
        if (str2 != null) {
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str2});
        }
        putExtra(intent, "android.intent.extra.SUBJECT", str3);
        putExtra(intent, "android.intent.extra.TEXT", str4);
        intent.setType("text/plain");
        launchIntent(intent);
    }

    /* access modifiers changed from: package-private */
    public final void sendSMS(String str, String str2) {
        sendSMSFromUri("smsto:" + str, str2);
    }

    /* access modifiers changed from: package-private */
    public final void sendSMSFromUri(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(str));
        putExtra(intent, "sms_body", str2);
        intent.putExtra("compose_mode", true);
        launchIntent(intent);
    }

    /* access modifiers changed from: package-private */
    public final void sendMMS(String str, String str2, String str3) {
        sendMMSFromUri("mmsto:" + str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public final void dialPhone(String str) {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
    }

    /* access modifiers changed from: package-private */
    public final void dialPhoneFromUri(String str) {
        launchIntent(new Intent("android.intent.action.DIAL", Uri.parse(str)));
    }

    /* access modifiers changed from: package-private */
    public final void openMap(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* access modifiers changed from: package-private */
    public final void searchMap(String str, CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 0) {
            str = str + " (" + charSequence + ')';
        }
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?q=" + Uri.encode(str))));
    }

    /* access modifiers changed from: package-private */
    public final void getDirections(double d, double d2) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google." + LocaleManager.getCountryTLD(this.activity) + "/maps?f=d&daddr=" + d + ',' + d2)));
    }

    /* access modifiers changed from: package-private */
    public final void openProductSearch(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google." + LocaleManager.getProductSearchCountryTLD(this.activity) + "/m/products?q=" + str + "&source=zxing")));
    }

    /* access modifiers changed from: package-private */
    public final void openBookSearch(String str) {
        launchIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://books.google." + LocaleManager.getBookSearchCountryTLD(this.activity) + "/books?vid=isbn" + str)));
    }

    /* access modifiers changed from: package-private */
    public final void openURL(String str) {
        if (str.startsWith("HTTP://")) {
            str = "http" + str.substring(4);
        } else if (str.startsWith("HTTPS://")) {
            str = "https" + str.substring(5);
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        try {
            launchIntent(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w(TAG, "Nothing available to handle " + intent);
        }
    }

    /* access modifiers changed from: package-private */
    public final void webSearch(String str) {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("query", str);
        launchIntent(intent);
    }

    /* access modifiers changed from: package-private */
    public void rawLaunchIntent(Intent intent) {
        if (intent != null) {
            intent.addFlags(524288);
            String str = TAG;
            Log.d(str, "Launching intent: " + intent + " with extras: " + intent.getExtras());
            this.activity.startActivity(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void launchIntent(Intent intent) {
        try {
            rawLaunchIntent(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }

    private static void putExtra(Intent intent, String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            intent.putExtra(str, str2);
        }
    }

    /* access modifiers changed from: package-private */
    public String fillInCustomSearchURL(String str) {
        String str2 = this.customProductSearch;
        if (str2 == null) {
            return str;
        }
        String replace = str2.replace("%s", str);
        Result result2 = this.rawResult;
        if (result2 == null) {
            return replace;
        }
        String replace2 = replace.replace("%f", result2.getBarcodeFormat().toString());
        return replace2.contains("%t") ? replace2.replace("%t", ResultParser.parseResult(this.rawResult).getType().toString()) : replace2;
    }
}
