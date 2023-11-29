package com.google.appinventor.components.runtime;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.net.URLEncoder;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

@SimpleObject
@DesignerComponent(category = ComponentCategory.MEDIA, description = "", iconName = "images/qrCode.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
public class QrCode extends AndroidNonvisibleComponent implements Component {
    private int BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = -16777216;
    private Context context;
    private String gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = "png";
    private String hZhaRgwkBZwejuAnnYABoWeuzwSnVTS6FhaE0jegMWisoVYsWwdasjmLDosamQJe = "http://api.qrserver.com/v1/create-qr-code/?data=";
    private String iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = "Default";
    private String iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = "UTF-8";
    private int nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM = -1;
    private int plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = 0;
    private int size = 200;

    public QrCode(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        Log.d("QRCode", "QRCode Created");
    }

    @SimpleEvent(description = "You will find here the success state and image url.")
    public void GotResponse(boolean z, String str) {
        EventDispatcher.dispatchEvent(this, "GotResponse", Boolean.valueOf(z), str);
    }

    @SimpleFunction(description = "Create a QR Code and the result is a link to it. Example: Set the resolution(in pixel) to 200(height and width has the same value) and text to: Hello world!. Result: QR code resolution is 200x200, text = Hello world! Your text input is encoded in the link automatically.")
    public void GenerateQrCode() {
        try {
            String str = this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
            if (str == null || str.isEmpty()) {
                GotResponse(false, "Can not generate qr code with no text.");
                return;
            }
            String encode = URLEncoder.encode(this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik, this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn);
            ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected()) {
                GotResponse(true, this.hZhaRgwkBZwejuAnnYABoWeuzwSnVTS6FhaE0jegMWisoVYsWwdasjmLDosamQJe + encode + "&color=" + Integer.toHexString(this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS).substring(2) + "&bgcolor=" + Integer.toHexString(this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM).substring(2) + "&margin=" + this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM + "&format=" + this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk + "&size=" + this.size + EllipticCurveJsonWebKey.X_MEMBER_NAME + this.size);
                Log.d("QRCode", "Success state is: true");
                return;
            }
            GotResponse(false, "");
            Log.d("QRCode", "Success state is: false");
        } catch (Exception unused) {
        }
    }

    @DesignerProperty(defaultValue = "Default", editorType = "textArea")
    @SimpleProperty(description = "This is the text that is converted as qr code.")
    public void Text(String str) {
        this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik = str;
    }

    @SimpleProperty(description = "Return the text.")
    public String Text() {
        return this.iMWvjaqDlqi8shqdETWDeLkDbaCwtdVfJFSzyvUX79cgwtU4Twvc8XyMVbnGcmik;
    }

    @DesignerProperty(defaultValue = "200", editorType = "integer")
    @SimpleProperty(description = "Set the size/resolution of the qr code.")
    public void Size(int i) {
        this.size = i;
    }

    @SimpleProperty(description = "Return the size/resolution of the qr code.")
    public int Size() {
        return this.size;
    }

    @DesignerProperty(defaultValue = "UTF-8", editorType = "string", propertyType = "advanced")
    @SimpleProperty(description = "Set the charset of the input text.")
    public void Charset(String str) {
        this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = str;
    }

    @SimpleProperty(description = "Return the charset of the input text.")
    public String Charset() {
        return this.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn;
    }

    @DesignerProperty(defaultValue = "&HFF000000", editorType = "color")
    @SimpleProperty(description = "Set the color of the qr code result.")
    public void FrontColor(int i) {
        this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = i;
    }

    @SimpleProperty(description = "Return the color of the qr code result.")
    public int FrontColor() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @DesignerProperty(defaultValue = "&HFFFFFFFF", editorType = "color")
    @SimpleProperty(description = "Set the background color of the qr code result.")
    public void BackgroundColor(int i) {
        this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM = i;
    }

    @SimpleProperty(description = "Return the background color of the qr code result.")
    public int BackgroundColor() {
        return this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM;
    }

    @DesignerProperty(defaultValue = "0", editorType = "integer")
    @SimpleProperty(description = "Thickness of a margin in pixels. The margin will always have the same color as the background.")
    public void Margin(int i) {
        this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM = i;
    }

    @SimpleProperty(description = "Return the margin in pixels.")
    public int Margin() {
        return this.plg04zzrdr0Ht9HKV9W5nEWP5Bmt4Culb23NXhe3Y9LVDLRrW71oAQyHt0KrFVdM;
    }

    @DesignerProperty(defaultValue = "png", editorType = "string")
    @SimpleProperty(description = "It's possible to create the QR code picture using different file formats, available are PNG, GIF, JPEG and the vector graphic formats SVG and EPS.")
    public void Format(String str) {
        this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = str;
    }

    @SimpleProperty(description = "Return the selected image format")
    public String Format() {
        return this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk;
    }
}
