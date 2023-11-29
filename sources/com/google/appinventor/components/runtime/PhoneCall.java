package com.google.appinventor.components.runtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.EndedStatus;
import com.google.appinventor.components.common.StartedStatus;
import com.google.appinventor.components.runtime.util.PhoneCallUtil;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "<p>A non-visible component that makes a phone call to the number specified in the <code>PhoneNumber</code> property, which can be set either in the Designer or Blocks Editor. The component has a <code>MakePhoneCall</code> method, enabling the program to launch a phone call.</p><p>Often, this component is used with the <code>ContactPicker</code> component, which lets the user select a contact from the ones stored on the phone and sets the <code>PhoneNumber</code> property to the contact's phone number.</p><p>To directly specify the phone number (e.g., 650-555-1212), set the <code>PhoneNumber</code> property to a Text with the specified digits (e.g., \"6505551212\").  Dashes, dots, and parentheses may be included (e.g., \"(650)-555-1212\") but will be ignored; spaces may not be included.</p>", iconName = "images/phoneCall.png", nonVisible = true, version = 3)
@SimpleObject
@UsesPermissions({"android.permission.READ_PHONE_STATE"})
public class PhoneCall extends AndroidNonvisibleComponent implements ActivityResultListener, Component, OnDestroyListener {
    private boolean D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = false;
    private final Context context;
    private boolean havePermission = false;
    private final a hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String phoneNumber;

    enum b {
        ;
        
        public static final int K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0 = 1;
        public static final int hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s = 2;
        public static final int wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO = 3;
    }

    public PhoneCall(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
        this.form.registerForOnDestroy(this);
        this.form.registerForActivityResult(this, 1346916174);
        PhoneNumber("");
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new a();
    }

    public void Initialize() {
        if (this.form.doesAppDeclarePermission("android.permission.PROCESS_OUTGOING_CALLS")) {
            this.form.askPermission("android.permission.PROCESS_OUTGOING_CALLS", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        PhoneCall.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(PhoneCall.this);
                    } else {
                        PhoneCall.this.form.dispatchPermissionDeniedEvent((Component) PhoneCall.this, "Initialize", "android.permission.PROCESS_OUTGOING_CALLS");
                    }
                }
            });
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String PhoneNumber() {
        return this.phoneNumber;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void PhoneNumber(String str) {
        this.phoneNumber = str;
    }

    @SimpleFunction
    public void MakePhoneCall() {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.fromParts("tel", this.phoneNumber, (String) null));
        if (intent.resolveActivity(this.form.getPackageManager()) != null) {
            this.form.startActivityForResult(intent, 1346916174);
        }
    }

    @UsesPermissions({"android.permission.CALL_PHONE"})
    @SimpleFunction
    public void MakePhoneCallDirect() {
        if (!this.havePermission) {
            this.form.askPermission("android.permission.CALL_PHONE", new PermissionResultHandler() {
                public final void HandlePermissionResponse(String str, boolean z) {
                    if (z) {
                        PhoneCall.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(PhoneCall.this);
                        PhoneCall.this.MakePhoneCall();
                        return;
                    }
                    PhoneCall.this.form.dispatchPermissionDeniedEvent((Component) PhoneCall.this, "MakePhoneCall", "android.permission.CALL_PHONE");
                }
            });
        } else {
            PhoneCallUtil.makePhoneCall(this.context, this.phoneNumber);
        }
    }

    @SimpleEvent(description = "Event indicating that a phonecall has started. If status is 1, incoming call is ringing; if status is 2, outgoing call is dialled. phoneNumber is the incoming/outgoing phone number.")
    @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS"})
    public void PhoneCallStarted(@Options(StartedStatus.class) int i, String str) {
        StartedStatus fromUnderlyingValue = StartedStatus.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            PhoneCallStartedAbstract(fromUnderlyingValue, str);
        }
    }

    public void PhoneCallStartedAbstract(StartedStatus startedStatus, String str) {
        EventDispatcher.dispatchEvent(this, "PhoneCallStarted", startedStatus.toUnderlyingValue(), str);
    }

    @SimpleEvent(description = "Event indicating that a phone call has ended. If status is 1, incoming call is missed or rejected; if status is 2, incoming call is answered before hanging up; if status is 3, outgoing call is hung up. phoneNumber is the ended call phone number.")
    @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS"})
    public void PhoneCallEnded(@Options(EndedStatus.class) int i, String str) {
        EndedStatus fromUnderlyingValue = EndedStatus.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            PhoneCallEndedAbstract(fromUnderlyingValue, str);
        }
    }

    public void PhoneCallEndedAbstract(EndedStatus endedStatus, String str) {
        EventDispatcher.dispatchEvent(this, "PhoneCallEnded", endedStatus.toUnderlyingValue(), str);
    }

    @SimpleEvent(description = "Event indicating that an incoming phone call is answered. phoneNumber is the incoming call phone number.")
    @UsesPermissions({"android.permission.PROCESS_OUTGOING_CALLS"})
    public void IncomingCallAnswered(String str) {
        EventDispatcher.dispatchEvent(this, "IncomingCallAnswered", str);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        if (i == 1346916174) {
            PhoneCallStartedAbstract(StartedStatus.Outgoing, "");
        }
    }

    class a extends BroadcastReceiver {
        private String PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP = "";
        private int ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = 0;

        public a() {
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.PHONE_STATE".equals(action)) {
                String stringExtra = intent.getStringExtra("state");
                if (TelephonyManager.EXTRA_STATE_RINGING.equals(stringExtra)) {
                    this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = b.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0;
                    String stringExtra2 = intent.getStringExtra("incoming_number");
                    this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP = stringExtra2;
                    if (stringExtra2 != null) {
                        PhoneCall.this.PhoneCallStartedAbstract(StartedStatus.Incoming, this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
                    }
                } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(stringExtra)) {
                    if (this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI == b.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0) {
                        this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = b.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s;
                        PhoneCall.this.IncomingCallAnswered(this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
                    }
                } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(stringExtra)) {
                    if (this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI == b.K7dcZ0wsgklhxIEZ56TEizrYoocHIkvglPQDsEhhjdemR7bSblNU8EAyc3To8SV0) {
                        PhoneCall.this.PhoneCallEndedAbstract(EndedStatus.IncomingRejected, this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
                    } else if (this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI == b.hYIBeJsINiSNmMMYMsE9k7k9FKwSRXABLbxSyf9mDVDqNrjjNU51ZRtstLVyAr2s) {
                        PhoneCall.this.PhoneCallEndedAbstract(EndedStatus.IncomingEnded, this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
                    } else if (this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI == b.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO) {
                        PhoneCall.this.PhoneCallEndedAbstract(EndedStatus.OutgoingEnded, this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
                    }
                    this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = 0;
                    this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP = "";
                }
            } else if ("android.intent.action.NEW_OUTGOING_CALL".equals(action)) {
                this.ZX8R20flhr3CVS4Kl4ZKKDInmXhpC5acCzOjP50MC4PLZvr5DSKJA1uCSNI4PeI = b.wfbsnc19ruRPyBpriU11i0zXW81wrBgGRVM2BOD65kRILLKDr3mBxnYSQKLd5kkO;
                this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
                PhoneCall.this.PhoneCallStartedAbstract(StartedStatus.Outgoing, this.PMWpqVMsyfTUgqkfqh3FKX4Q3IYlcybzKrBGEl7qtzs6HNVssJ63V430Ige89pyP);
            }
        }
    }

    public void onDestroy() {
        if (this.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk) {
            this.context.unregisterReceiver(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            this.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = false;
        }
    }
}
