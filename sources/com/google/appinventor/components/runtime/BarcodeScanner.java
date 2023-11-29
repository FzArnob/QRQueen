package com.google.appinventor.components.runtime;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.zxing.client.android.Intents;

@DesignerComponent(category = ComponentCategory.SENSORS, description = "Component for using the Barcode Scanner to read a barcode", iconName = "images/barcodeScanner.png", nonVisible = true, version = 2)
@UsesLibraries({"Barcode.jar", "QRGenerator.jar"})
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "orientation|keyboardHidden", name = "com.google.zxing.client.android.AppInvCaptureActivity", screenOrientation = "portrait", stateNotNeeded = "true", theme = "@android:style/Theme.NoTitleBar.Fullscreen", windowSoftInputMode = "stateAlwaysHidden")})
@UsesPermissions({"android.permission.CAMERA"})
public class BarcodeScanner extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private boolean Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = false;
    private String KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
    private final ComponentContainer container;
    /* access modifiers changed from: private */
    public boolean havePermission = false;
    private int requestCode;

    public BarcodeScanner(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Text result of the previous scan.")
    public String Result() {
        return this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH;
    }

    @SimpleFunction(description = "Begins a barcode scan, using the camera. When the scan is complete, the AfterScan event will be raised.")
    public void DoScan() {
        Intent intent = new Intent(Intents.Scan.ACTION);
        if (!this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB) {
            if (!this.havePermission) {
                this.container.$form().askPermission("android.permission.CAMERA", new PermissionResultHandler() {
                    public final void HandlePermissionResponse(String str, boolean z) {
                        if (z) {
                            boolean unused = BarcodeScanner.this.havePermission = true;
                            BarcodeScanner.this.DoScan();
                            return;
                        }
                        BarcodeScanner.this.form.dispatchPermissionDeniedEvent((Component) BarcodeScanner.this, "DoScan", "android.permission.CAMERA");
                    }
                });
                return;
            }
            intent.setComponent(new ComponentName(this.container.$form().getPackageName(), "com.google.zxing.client.android.AppInvCaptureActivity"));
        }
        if (this.requestCode == 0) {
            this.requestCode = this.form.registerForActivityResult(this);
        }
        try {
            this.container.$context().startActivityForResult(intent, this.requestCode);
        } catch (ActivityNotFoundException unused) {
            this.container.$form().dispatchErrorOccurredEvent(this, "BarcodeScanner", ErrorMessages.ERROR_NO_SCANNER_FOUND, "");
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == this.requestCode && i2 == -1) {
            if (intent.hasExtra(Intents.Scan.RESULT)) {
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = intent.getStringExtra(Intents.Scan.RESULT);
            } else {
                this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH = "";
            }
            AfterScan(this.KbzcIEn6WDqjdY1QBot1TMrBwhEYy4xAUKG2cbzQ22VNohlOtuBGKUJsEeMNZyEH);
        }
    }

    @SimpleEvent
    public void AfterScan(String str) {
        EventDispatcher.dispatchEvent(this, "AfterScan", str);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true App Inventor will look for and use an external scanning program such as \"Bar Code Scanner.\"")
    public boolean UseExternalScanner() {
        return this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void UseExternalScanner(boolean z) {
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = z;
    }
}
