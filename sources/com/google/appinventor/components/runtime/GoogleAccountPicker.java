package com.google.appinventor.components.runtime;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Component to pick a Google Account.", iconName = "images/google.png", nonVisible = true, version = 1)
public class GoogleAccountPicker extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private ComponentContainer container;
    private Context context;
    private int requestCode = 0;

    public GoogleAccountPicker(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.container = componentContainer;
        this.context = componentContainer.$context();
        Log.d("GoogleAccountPicker", "GoogleAccountPicker Created");
    }

    @SimpleFunction(description = "Provide an account picker to pick a Google account.")
    public void Pick() {
        if (this.requestCode == 0) {
            this.requestCode = this.form.registerForActivityResult(this);
            Log.d("GoogleAccountPicker", "Pick, requestCode: " + this.requestCode);
        }
        try {
            this.container.$context().startActivityForResult(AccountManager.newChooseAccountIntent((Account) null, (ArrayList) null, new String[]{"com.google", "com.google.android.legacyimap"}, false, (String) null, (String) null, (String[]) null, (Bundle) null), this.requestCode);
        } catch (ActivityNotFoundException unused) {
            this.form.dispatchErrorOccurredEvent(this, "Pick", ErrorMessages.ERROR_ACTIVITY_STARTER_NO_CORRESPONDING_ACTIVITY, new Object[0]);
        }
    }

    @SimpleEvent(description = "Event raised after account has been picked.")
    public void Picked(String str) {
        EventDispatcher.dispatchEvent(this, "Picked", str);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (this.requestCode == i && i2 == -1 && intent != null) {
            Picked(intent.getStringExtra("authAccount"));
        }
    }
}
