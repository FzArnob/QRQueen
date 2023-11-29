package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.util.Log;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.jose4j.keys.AesKey;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Component to check for a FingerScanner and read fingerprints from the scanner", helpUrl = "https://docs.kodular.io/components/sensors/fingerprint/", iconName = "images/fingerprint.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.USE_FINGERPRINT"})
public final class FingerPrint extends AndroidNonvisibleComponent implements Component {
    private TextView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = "Scan your finger";
    private Context context;
    private Dialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KeyguardManager f97hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat.AuthenticationCallback f98hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat.CryptoObject f99hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private CancellationSignal f101hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KeyStore f102hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Cipher f103hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = "Sign in with your fingerprint";
    private boolean kWxA7iNqyKKEpChQAnU1BbhddsMkflBuiFLQemEhYlpBrkEZoiOWj50aF76hVGct = false;
    private boolean q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj = true;
    private TextView vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private TextView wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public FingerPrint(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Activity $context = componentContainer.$context();
        this.context = $context;
        this.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = FingerprintManagerCompat.from($context);
        this.f97hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (KeyguardManager) this.context.getSystemService("keyguard");
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Dialog(this.context);
        this.f101hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new CancellationSignal();
        Log.d("FingerPrint", "FingerPrint Created");
    }

    @SimpleFunction(description = "True if hardware is present and functional, false otherwise")
    public final boolean HasFingerPrintScanner() {
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.USE_FINGERPRINT") == 0) {
            return this.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isHardwareDetected();
        }
        return false;
    }

    @SimpleFunction(description = "True if at least one fingerprint is enrolled, false otherwise")
    public final boolean HasFingersAdded() {
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.USE_FINGERPRINT") == 0) {
            return this.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hasEnrolledFingerprints();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Authenticate the user with a Fingerprint scanner")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void Authenticate() {
        /*
            r10 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 < r1) goto L_0x01e1
            android.content.Context r0 = r10.context
            java.lang.String r2 = "android.permission.USE_FINGERPRINT"
            int r0 = androidx.core.app.ActivityCompat.checkSelfPermission(r0, r2)
            if (r0 != 0) goto L_0x01e1
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 0
            r3 = 1
            if (r0 < r1) goto L_0x0044
            androidx.core.hardware.fingerprint.FingerprintManagerCompat r0 = r10.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r0 = r0.isHardwareDetected()
            java.lang.String r1 = "FingerPrint"
            if (r0 != 0) goto L_0x0026
            java.lang.String r0 = "Fingerprint authentication not supported"
            android.util.Log.d(r1, r0)
            goto L_0x0044
        L_0x0026:
            androidx.core.hardware.fingerprint.FingerprintManagerCompat r0 = r10.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r0 = r0.hasEnrolledFingerprints()
            if (r0 != 0) goto L_0x0034
            java.lang.String r0 = "No fingerprint configured."
            android.util.Log.d(r1, r0)
            goto L_0x0044
        L_0x0034:
            android.app.KeyguardManager r0 = r10.f97hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r0 = r0.isKeyguardSecure()
            if (r0 != 0) goto L_0x0042
            java.lang.String r0 = "Secure lock screen not enabled"
            android.util.Log.d(r1, r0)
            goto L_0x0044
        L_0x0042:
            r0 = 1
            goto L_0x0045
        L_0x0044:
            r0 = 0
        L_0x0045:
            if (r0 == 0) goto L_0x01e1
            r10.generateKey()
            boolean r0 = r10.cipherInit()
            if (r0 == 0) goto L_0x01e1
            androidx.core.hardware.fingerprint.FingerprintManagerCompat$CryptoObject r0 = new androidx.core.hardware.fingerprint.FingerprintManagerCompat$CryptoObject
            javax.crypto.Cipher r1 = r10.f103hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r0.<init>((javax.crypto.Cipher) r1)
            r10.f99hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            com.google.appinventor.components.runtime.FingerPrint$1 r0 = new com.google.appinventor.components.runtime.FingerPrint$1
            r0.<init>()
            r10.f98hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            androidx.core.os.CancellationSignal r7 = new androidx.core.os.CancellationSignal
            r7.<init>()
            r10.f101hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            androidx.core.hardware.fingerprint.FingerprintManagerCompat r4 = r10.f100hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            androidx.core.hardware.fingerprint.FingerprintManagerCompat$CryptoObject r5 = r10.f99hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 0
            androidx.core.hardware.fingerprint.FingerprintManagerCompat$AuthenticationCallback r8 = r10.f98hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r9 = 0
            r4.authenticate(r5, r6, r7, r8, r9)
            boolean r0 = r10.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj
            if (r0 == 0) goto L_0x01e1
            android.app.Dialog r0 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r0 = r0.isShowing()
            if (r0 != 0) goto L_0x01e1
            android.app.Dialog r0 = new android.app.Dialog
            android.content.Context r1 = r10.context
            r0.<init>(r1, r2)
            r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
            android.content.Context r0 = r10.context
            android.content.res.AssetManager r0 = r0.getAssets()
            java.lang.String r1 = "MaterialIcons-Regular.ttf"
            android.graphics.Typeface r0 = android.graphics.Typeface.createFromAsset(r0, r1)
            boolean r1 = r10.kWxA7iNqyKKEpChQAnU1BbhddsMkflBuiFLQemEhYlpBrkEZoiOWj50aF76hVGct
            r4 = -1
            if (r1 == 0) goto L_0x00ad
            android.app.Dialog r1 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ NullPointerException -> 0x00ac }
            android.view.Window r1 = r1.getWindow()     // Catch:{ NullPointerException -> 0x00ac }
            android.view.View r1 = r1.getDecorView()     // Catch:{ NullPointerException -> 0x00ac }
            android.graphics.drawable.Drawable r1 = r1.getBackground()     // Catch:{ NullPointerException -> 0x00ac }
            android.graphics.PorterDuff$Mode r5 = android.graphics.PorterDuff.Mode.SRC_ATOP     // Catch:{ NullPointerException -> 0x00ac }
            r1.setColorFilter(r4, r5)     // Catch:{ NullPointerException -> 0x00ac }
            goto L_0x00ad
        L_0x00ac:
        L_0x00ad:
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r5 = r10.context
            r1.<init>(r5)
            r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r1
            java.lang.String r5 = r10.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm
            android.text.Spanned r5 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r5)
            r1.setText(r5)
            android.widget.TextView r1 = r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r5 = 1098907648(0x41800000, float:16.0)
            r1.setTextSize(r5)
            android.widget.TextView r1 = r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            android.graphics.Typeface r6 = android.graphics.Typeface.DEFAULT_BOLD
            r1.setTypeface(r6)
            android.widget.TextView r1 = r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            boolean r6 = r10.kWxA7iNqyKKEpChQAnU1BbhddsMkflBuiFLQemEhYlpBrkEZoiOWj50aF76hVGct
            if (r6 == 0) goto L_0x00d6
            r6 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            goto L_0x00d7
        L_0x00d6:
            r6 = -1
        L_0x00d7:
            r1.setTextColor(r6)
            android.widget.TextView r1 = r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r1.setGravity(r3)
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r6 = r10.context
            r1.<init>(r6)
            r10.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r1
            java.lang.String r6 = r10.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e
            android.text.Spanned r6 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r6)
            r1.setText(r6)
            android.widget.TextView r1 = r10.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r1.setTextSize(r5)
            android.widget.TextView r1 = r10.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r5 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r1.setTextColor(r5)
            android.widget.TextView r1 = r10.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r1.setGravity(r3)
            android.widget.TextView r1 = new android.widget.TextView
            android.content.Context r6 = r10.context
            r1.<init>(r6)
            r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r1
            java.lang.String r6 = "&#xE90D;"
            android.text.Spanned r6 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r6)
            r1.setText(r6)
            android.widget.TextView r1 = r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r6 = 1116733440(0x42900000, float:72.0)
            r1.setTextSize(r6)
            android.widget.TextView r1 = r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            android.graphics.Typeface r0 = android.graphics.Typeface.create(r0, r2)
            r1.setTypeface(r0)
            android.widget.TextView r0 = r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r0.setTextColor(r5)
            android.widget.TextView r0 = r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r0.setGravity(r3)
            android.widget.LinearLayout r0 = new android.widget.LinearLayout
            android.content.Context r1 = r10.context
            r0.<init>(r1)
            android.widget.LinearLayout$LayoutParams r1 = new android.widget.LinearLayout$LayoutParams
            android.content.Context r2 = r10.context
            r5 = 8
            int r2 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r2, (int) r5)
            r1.<init>(r4, r2)
            r0.setLayoutParams(r1)
            android.widget.LinearLayout r1 = new android.widget.LinearLayout
            android.content.Context r2 = r10.context
            r1.<init>(r2)
            android.widget.LinearLayout$LayoutParams r2 = new android.widget.LinearLayout$LayoutParams
            r5 = -2
            r2.<init>(r4, r5)
            r1.setLayoutParams(r2)
            android.content.Context r2 = r10.context
            r4 = 24
            int r2 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r2, (int) r4)
            r1.setPadding(r2, r2, r2, r2)
            android.widget.TextView r2 = r10.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r1.addView(r2)
            r1.addView(r0)
            android.widget.TextView r0 = r10.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r1.addView(r0)
            android.widget.TextView r0 = r10.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            r1.addView(r0)
            r1.setOrientation(r3)
            r0 = 17
            r1.setGravity(r0)
            android.app.Dialog r0 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$2 r2 = new com.google.appinventor.components.runtime.FingerPrint$2
            r2.<init>()
            r0.setOnCancelListener(r2)
            android.app.Dialog r0 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$3 r2 = new com.google.appinventor.components.runtime.FingerPrint$3
            r2.<init>()
            r0.setOnDismissListener(r2)
            android.app.Dialog r0 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$4 r2 = new com.google.appinventor.components.runtime.FingerPrint$4
            r2.<init>()
            r0.setOnKeyListener(r2)
            android.app.Dialog r0 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r0.setContentView(r1)
            android.view.WindowManager$LayoutParams r0 = new android.view.WindowManager$LayoutParams
            r0.<init>()
            android.app.Dialog r1 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.Window r1 = r1.getWindow()
            android.view.WindowManager$LayoutParams r1 = r1.getAttributes()
            r0.copyFrom(r1)
            android.content.Context r1 = r10.context
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.xdpi
            double r1 = (double) r1
            r3 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            double r1 = r1 * r3
            int r1 = (int) r1
            android.content.Context r2 = r10.context
            r3 = 360(0x168, float:5.04E-43)
            if (r1 <= r3) goto L_0x01cd
            r1 = 360(0x168, float:5.04E-43)
        L_0x01cd:
            int r1 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r2, (int) r1)
            r0.width = r1
            android.app.Dialog r1 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r1.show()
            android.app.Dialog r1 = r10.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.Window r1 = r1.getWindow()
            r1.setAttributes(r0)
        L_0x01e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FingerPrint.Authenticate():void");
    }

    @SimpleFunction(description = "Cancel the current Fingerprint Scan")
    public final void CancelScan() {
        this.f101hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel();
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isShowing()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dismiss();
        }
    }

    @SimpleEvent(description = "Triggers when there is a Authentication Error")
    public final void OnAuthenticationError(int i, String str) {
        EventDispatcher.dispatchEvent(this, "OnAuthenticationError", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Triggers when there is a Authentication Help")
    public final void OnAuthenticationHelp(int i, String str) {
        EventDispatcher.dispatchEvent(this, "OnAuthenticationHelp", Integer.valueOf(i), str);
    }

    @SimpleEvent(description = "Triggers when the Authentication Failed")
    public final void OnAuthenticationFailed() {
        EventDispatcher.dispatchEvent(this, "OnAuthenticationFailed", new Object[0]);
    }

    @SimpleEvent(description = "Trigger when the Authentication Succeeded")
    public final void OnAuthenticationSucceeded() {
        EventDispatcher.dispatchEvent(this, "OnAuthenticationSucceeded", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Sets the current theme")
    public final void LightTheme(boolean z) {
        this.kWxA7iNqyKKEpChQAnU1BbhddsMkflBuiFLQemEhYlpBrkEZoiOWj50aF76hVGct = z;
    }

    @SimpleProperty(description = "Gets the current theme")
    public final boolean LightTheme() {
        return this.kWxA7iNqyKKEpChQAnU1BbhddsMkflBuiFLQemEhYlpBrkEZoiOWj50aF76hVGct;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to use a dialog")
    public final void UseDialog(boolean z) {
        this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj = z;
    }

    @SimpleProperty(description = "Whether to use a dialog")
    public final boolean UseDialog() {
        return this.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj;
    }

    @DesignerProperty(defaultValue = "Sign in with your fingerprint", editorType = "string")
    @SimpleProperty(description = "Sets the dialog title")
    public final void DialogTitle(String str) {
        this.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = str;
    }

    @SimpleProperty(description = "Gets the dialog title")
    public final String DialogTitle() {
        return this.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm;
    }

    @DesignerProperty(defaultValue = "Scan your finger", editorType = "string")
    @SimpleProperty(description = "Sets the dialog help text")
    public final void DialogHelpText(String str) {
        this.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e = str;
    }

    @SimpleProperty(description = "Gets the dialog help text")
    public final String DialogHelpText() {
        return this.ESt8lrIffFGR3zRMjd5dWbJ7NZymJSmv5KENFDX7fPBQMwlHzz9dP6Ts0eqkVO5e;
    }

    /* access modifiers changed from: protected */
    public final void generateKey() {
        try {
            this.f102hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            Log.e("FingerPrint", String.valueOf(e));
        }
        try {
            KeyGenerator instance = KeyGenerator.getInstance(AesKey.ALGORITHM, "AndroidKeyStore");
            try {
                this.f102hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.load((KeyStore.LoadStoreParameter) null);
                instance.init(new KeyGenParameterSpec.Builder("makeroidApp", 3).setBlockModes(new String[]{"CBC"}).setUserAuthenticationRequired(true).setEncryptionPaddings(new String[]{"PKCS7Padding"}).build());
                instance.generateKey();
            } catch (IOException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | CertificateException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e3) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e3);
        }
    }

    public final boolean cipherInit() {
        try {
            this.f103hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Cipher.getInstance("AES/CBC/PKCS7Padding");
            try {
                this.f102hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.load((KeyStore.LoadStoreParameter) null);
                this.f103hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.init(1, (SecretKey) this.f102hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getKey("makeroidApp", (char[]) null));
                return true;
            } catch (KeyPermanentlyInvalidatedException unused) {
                return false;
            } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
                throw new RuntimeException("Failed to init Cipher", e);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            throw new RuntimeException("Failed to get Cipher", e2);
        }
    }

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FingerPrint fingerPrint, String str, int i) {
        fingerPrint.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setText(TextViewUtil.fromHtml(str));
        fingerPrint.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setTextColor(i);
        fingerPrint.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.setTextColor(i);
    }
}
