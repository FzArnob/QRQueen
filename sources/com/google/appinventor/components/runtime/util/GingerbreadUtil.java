package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Parcelable;
import android.util.Log;
import com.google.appinventor.components.runtime.NearField;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class GingerbreadUtil {
    private GingerbreadUtil() {
    }

    @Deprecated
    public static CookieHandler newCookieManager() {
        return new CookieManager();
    }

    public static boolean clearCookies(CookieHandler cookieHandler) {
        CookieStore cookieStore;
        if (!(cookieHandler instanceof CookieManager) || (cookieStore = ((CookieManager) cookieHandler).getCookieStore()) == null) {
            return false;
        }
        cookieStore.removeAll();
        return true;
    }

    public static void enableNFCWriteMode(Activity activity, NfcAdapter nfcAdapter, String str) {
        nfcAdapter.enableForegroundNdefPush(activity, new NdefMessage(new NdefRecord[]{createTextRecord(str, true)}));
    }

    public static void disableNFCAdapter(Activity activity, NfcAdapter nfcAdapter) {
        nfcAdapter.disableForegroundNdefPush(activity);
    }

    public static NdefRecord createTextRecord(String str, boolean z) {
        byte[] bytes = Locale.getDefault().getLanguage().getBytes(StandardCharsets.US_ASCII);
        byte[] bytes2 = str.getBytes(z ? StandardCharsets.UTF_8 : StandardCharsets.UTF_16);
        int i = z ? 0 : 128;
        byte[] bArr = new byte[(bytes.length + 1 + bytes2.length)];
        bArr[0] = (byte) ((char) (i + bytes.length));
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        System.arraycopy(bytes2, 0, bArr, bytes.length + 1, bytes2.length);
        return new NdefRecord(1, NdefRecord.RTD_TEXT, new byte[0], bArr);
    }

    public static void resolveNFCIntent(Intent intent, NearField nearField) {
        NdefMessage[] ndefMessageArr;
        if (!"android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction())) {
            Log.e("nearfield", "Unknown intent ".concat(String.valueOf(intent)));
        } else if (nearField.ReadMode()) {
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
            if (parcelableArrayExtra != null) {
                ndefMessageArr = new NdefMessage[parcelableArrayExtra.length];
                for (int i = 0; i < parcelableArrayExtra.length; i++) {
                    ndefMessageArr[i] = (NdefMessage) parcelableArrayExtra[i];
                }
            } else {
                byte[] bArr = new byte[0];
                ndefMessageArr = new NdefMessage[]{new NdefMessage(new NdefRecord[]{new NdefRecord(5, bArr, bArr, bArr)})};
            }
            nearField.TagRead(NearField.toHexString(((Tag) intent.getParcelableExtra("android.nfc.extra.TAG")).getId()), new String(ndefMessageArr[0].getRecords()[0].getPayload()).substring(3));
        } else {
            Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
            NdefMessage ndefMessage = null;
            if (nearField.WriteType() == 1) {
                ndefMessage = new NdefMessage(new NdefRecord[]{createTextRecord(nearField.TextToWrite(), true)});
            }
            if (writeNFCTag(ndefMessage, tag)) {
                nearField.TagWritten();
            }
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeNFCTag(android.nfc.NdefMessage r4, android.nfc.Tag r5) {
        /*
            byte[] r0 = r4.toByteArray()
            int r0 = r0.length
            r1 = 0
            android.nfc.tech.Ndef r2 = android.nfc.tech.Ndef.get(r5)     // Catch:{  }
            r3 = 1
            if (r2 == 0) goto L_0x0022
            r2.connect()     // Catch:{  }
            boolean r5 = r2.isWritable()     // Catch:{  }
            if (r5 != 0) goto L_0x0017
            return r1
        L_0x0017:
            int r5 = r2.getMaxSize()     // Catch:{  }
            if (r5 >= r0) goto L_0x001e
            return r1
        L_0x001e:
            r2.writeNdefMessage(r4)     // Catch:{  }
            return r3
        L_0x0022:
            android.nfc.tech.NdefFormatable r5 = android.nfc.tech.NdefFormatable.get(r5)     // Catch:{  }
            if (r5 == 0) goto L_0x002f
            r5.connect()     // Catch:{ Exception -> 0x002f }
            r5.format(r4)     // Catch:{ Exception -> 0x002f }
            return r3
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.GingerbreadUtil.writeNFCTag(android.nfc.NdefMessage, android.nfc.Tag):boolean");
    }
}
