package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/keyguardManager.png", nonVisible = true, version = 1)
@UsesPermissions({"android.permission.DISABLE_KEYGUARD"})
public class KodularKeyguardManager extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private KeyguardManager B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = "Confirm your screen lock.";
    private String IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = "Unlock";
    private Activity activity;
    /* access modifiers changed from: private */
    public final Handler androidUIHandler = new Handler();
    private Context context;
    private Form form;
    private int requestCode;

    public KodularKeyguardManager(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
        this.form = componentContainer.$form();
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = (KeyguardManager) this.context.getSystemService("keyguard");
        Log.d("Keyguard Manager", "Keyguard Manager Created");
    }

    @DesignerProperty(defaultValue = "Unlock", editorType = "string")
    @SimpleProperty(description = "")
    public void Title(String str) {
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = str;
    }

    @SimpleProperty(description = "Returns the keyguard manager title text.")
    public String Title() {
        return this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    @DesignerProperty(defaultValue = "Confirm your screen lock.", editorType = "string")
    @SimpleProperty(description = "")
    public void Description(String str) {
        this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = str;
    }

    @SimpleProperty(description = "Returns the keyguard manager description text.")
    public String Description() {
        return this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL;
    }

    @SimpleProperty(description = "Returns whether the device is currently locked and requires a PIN, pattern or password to unlock. Works only for devices with Android 5.1+")
    public boolean isDeviceLocked() {
        if (Build.VERSION.SDK_INT >= 22) {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isDeviceLocked();
        }
        return false;
    }

    @SimpleProperty(description = "Returns whether the device is secured with a PIN, pattern or password. Works only for devices with Android 6+")
    public boolean isDeviceSecure() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isDeviceSecure();
        }
        return false;
    }

    @SimpleProperty(description = "Return whether the keyguard is currently locked.")
    public boolean isKeyguardLocked() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isKeyguardLocked();
    }

    @SimpleProperty(description = "Return whether the keyguard is secured by a PIN, pattern or password or a SIM card is currently locked.")
    public boolean isKeyguardSecure() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.isKeyguardSecure();
    }

    @SimpleFunction(description = "Specifies whether an Activity should be shown on top of the lock screen whenever the lockscreen is up and the activity is resumed. Normally an activity will be transitioned to the stopped state if it is started while the lockscreen is up, but with this flag set the activity will remain in the resumed state visible on-top of the lock screen. ")
    public void ShowWhenLocked(boolean z) {
        if (Build.VERSION.SDK_INT >= 27) {
            this.activity.setShowWhenLocked(z);
            return;
        }
        Window window = this.activity.getWindow();
        if (z) {
            window.addFlags(524288);
        } else {
            window.clearFlags(524288);
        }
    }

    @SimpleFunction(description = "Create the Confirm Credentials screen. You can customize the title and description. Or we will provide a generic one for you if you leave it empty. Works only for devices with Android 5+")
    public void ShowAuthenticationScreen() {
        KeyguardManager keyguardManager = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        String str = null;
        String Title = Title().isEmpty() ? null : Title();
        if (!Description().isEmpty()) {
            str = Description();
        }
        Intent createConfirmDeviceCredentialIntent = keyguardManager.createConfirmDeviceCredentialIntent(Title, str);
        if (createConfirmDeviceCredentialIntent != null) {
            int registerForActivityResult = this.form.registerForActivityResult(this);
            this.requestCode = registerForActivityResult;
            this.form.startActivityForResult(createConfirmDeviceCredentialIntent, registerForActivityResult);
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i != this.requestCode) {
            return;
        }
        if (i2 == -1) {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    KodularKeyguardManager.this.OnAuthenticationRequest(true);
                }
            });
        } else {
            this.androidUIHandler.post(new Runnable() {
                public final void run() {
                    KodularKeyguardManager.this.OnAuthenticationRequest(false);
                }
            });
        }
    }

    @SimpleFunction(description = "If the device is currently locked, requests the Keyguard to be dismissed. Works only for devices with Android 8+")
    public void RequestDismissKeyguard() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.requestDismissKeyguard(this.activity, new KeyguardManager.KeyguardDismissCallback() {
                public final void onDismissError() {
                    super.onDismissError();
                    KodularKeyguardManager.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            KodularKeyguardManager.this.OnDissmissKeyguardRequest(false, true);
                        }
                    });
                }

                public final void onDismissCancelled() {
                    super.onDismissCancelled();
                    KodularKeyguardManager.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            KodularKeyguardManager.this.OnDissmissKeyguardRequest(false, true);
                        }
                    });
                }

                public final void onDismissSucceeded() {
                    super.onDismissSucceeded();
                    KodularKeyguardManager.this.androidUIHandler.post(new Runnable() {
                        public final void run() {
                            KodularKeyguardManager.this.OnDissmissKeyguardRequest(true, false);
                        }
                    });
                }
            });
        }
    }

    @SimpleEvent(description = "Event to detect a authentication request was called.")
    public void OnAuthenticationRequest(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnAuthenticationRequest", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Event to detect a dissmiss request keyguard was called.")
    public void OnDissmissKeyguardRequest(boolean z, boolean z2) {
        EventDispatcher.dispatchEvent(this, "OnDissmissKeyguardRequest", Boolean.valueOf(z), Boolean.valueOf(z2));
    }
}
