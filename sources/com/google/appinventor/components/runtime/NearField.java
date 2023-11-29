package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component to provide NFC capabilities.  For now this component supports the reading and writing of text tags only (if supported by the device)</p><p>In order to read and write text tags, the component must have its <code>ReadMode</code> property set to True or False respectively.</p><p><strong>Note:</strong> This component will only work on Screen1 of any App Inventor app.</p>", iconName = "images/nearfield.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.NFC"})
public class NearField extends AndroidNonvisibleComponent implements Deleteable, OnNewIntentListener, OnPauseListener, OnResumeListener, OnStopListener {
    private boolean Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW = true;
    private String HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = "";
    private Activity activity;
    private NfcAdapter hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    protected int requestCode;
    private String tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = "";
    private int z89WSVwZ7PB2CmtTjCz6wAa186XxEwXZ6USChsRUkmrmINCnliZwpzt9cagYiYvX;

    public void onDelete() {
    }

    public void onStop() {
    }

    public NearField(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Activity $context = componentContainer.$context();
        this.activity = $context;
        this.z89WSVwZ7PB2CmtTjCz6wAa186XxEwXZ6USChsRUkmrmINCnliZwpzt9cagYiYvX = 1;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = NfcAdapter.getDefaultAdapter($context);
        this.form.registerForOnResume(this);
        this.form.registerForOnNewIntent(this);
        this.form.registerForOnPause(this);
        Log.d("nearfield", "Nearfield component created");
    }

    @SimpleEvent
    public void TagRead(String str, String str2) {
        Log.d("nearfield", "Tag read: got message ".concat(String.valueOf(str2)));
        this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA = str2;
        EventDispatcher.dispatchEvent(this, "TagRead", str, str2);
    }

    @SimpleEvent(description = "Event to detect when a tag was written.")
    public void TagWritten() {
        Log.d("nearfield", "Tag written: " + this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l);
        EventDispatcher.dispatchEvent(this, "TagWritten", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String LastMessage() {
        Log.d("nearfield", "String message method stared");
        return this.tFNQ38WX0EWx1sBmwkfGOavwj0ohdRyv5RfL5KQ0qa5Pnr6qc7YsZTQu1aP3NtmA;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean ReadMode() {
        Log.d("nearfield", "boolean method stared");
        return this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String TextToWrite() {
        Log.d("nearfield", "String message method stared");
        return this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int WriteType() {
        return this.z89WSVwZ7PB2CmtTjCz6wAa186XxEwXZ6USChsRUkmrmINCnliZwpzt9cagYiYvX;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void ReadMode(boolean z) {
        Log.d("nearfield", "Read mode set to".concat(String.valueOf(z)));
        this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW = z;
        if (!z) {
            GingerbreadUtil.enableNFCWriteMode(this.activity, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void TextToWrite(String str) {
        Log.d("nearfield", "Text to write set to".concat(String.valueOf(str)));
        this.HD0pINq0k1DLtf3yXVbK8xQhU4iGPT4iryXVeTceizjENHUUCtIIAr11wB5gmj8l = str;
        if (!this.Al9x361S9LLe4YuJ2FYrCTKylxVrvlRhCPyJ97vxqAo0DGA2cpEUDnPNY8RXP1UW && this.z89WSVwZ7PB2CmtTjCz6wAa186XxEwXZ6USChsRUkmrmINCnliZwpzt9cagYiYvX == 1) {
            GingerbreadUtil.enableNFCWriteMode(this.activity, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str);
        }
    }

    public void onNewIntent(Intent intent) {
        Log.d("nearfield", "Nearfield on onNewIntent.  Intent is: ".concat(String.valueOf(intent)));
        Log.d("nearfield", "resolve intent. Intent is: ".concat(String.valueOf(intent)));
        GingerbreadUtil.resolveNFCIntent(intent, this);
    }

    public void onResume() {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            Log.d("nearfield", "Nearfield on onResume.  Intent is: ".concat(String.valueOf(activity2.getIntent())));
        }
    }

    public void onPause() {
        Activity activity2;
        Log.d("nearfield", "OnPause method started.");
        NfcAdapter nfcAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (nfcAdapter != null && (activity2 = this.activity) != null) {
            GingerbreadUtil.disableNFCAdapter(activity2, nfcAdapter);
        }
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Integer.valueOf(bArr[i] & Ev3Constants.Opcode.TST)}));
        }
        return sb.toString().toUpperCase();
    }
}
