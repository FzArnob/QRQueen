package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth server component", iconName = "images/bluetoothServer.png", nonVisible = true, version = 5)
@UsesPermissions({"android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH_ADVERTISE"})
public final class BluetoothServer extends BluetoothConnectionBase {
    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    /* access modifiers changed from: private */
    public final Handler androidUIHandler = new Handler();
    /* access modifiers changed from: private */
    public final AtomicReference<Object> arBluetoothServerSocket = new AtomicReference<>();

    public BluetoothServer(ComponentContainer componentContainer) {
        super(componentContainer, "BluetoothServer");
    }

    @SimpleFunction(description = "Accept an incoming connection with the Serial Port Profile (SPP).")
    public final void AcceptConnection(String str) {
        accept("AcceptConnection", str, SPP_UUID);
    }

    @SimpleFunction(description = "Accept an incoming connection with a specific UUID.")
    public final void AcceptConnectionWithUUID(String str, String str2) {
        accept("AcceptConnectionWithUUID", str, str2);
    }

    private void accept(final String str, String str2, String str3) {
        Object obj;
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_AVAILABLE, new Object[0]);
        } else if (!BluetoothReflection.isBluetoothEnabled(bluetoothAdapter)) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_ENABLED, new Object[0]);
        } else {
            try {
                UUID fromString = UUID.fromString(str3);
                try {
                    if (!this.secure) {
                        obj = BluetoothReflection.listenUsingInsecureRfcommWithServiceRecord(bluetoothAdapter, str2, fromString);
                    } else {
                        obj = BluetoothReflection.listenUsingRfcommWithServiceRecord(bluetoothAdapter, str2, fromString);
                    }
                    this.arBluetoothServerSocket.set(obj);
                    AsynchUtil.runAsynchronously(new Runnable() {
                        /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
                        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
                            r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.StopAccepting();
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
                            throw r0;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
                            r0 = move-exception;
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
                            com.google.appinventor.components.runtime.BluetoothServer.access$100(r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).post(new com.google.appinventor.components.runtime.BluetoothServer.AnonymousClass1.AnonymousClass1(r3));
                         */
                        /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
                            return;
                         */
                        /* JADX WARNING: Failed to process nested try/catch */
                        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0018 */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public final void run() {
                            /*
                                r3 = this;
                                com.google.appinventor.components.runtime.BluetoothServer r0 = com.google.appinventor.components.runtime.BluetoothServer.this
                                java.util.concurrent.atomic.AtomicReference r0 = r0.arBluetoothServerSocket
                                java.lang.Object r0 = r0.get()
                                if (r0 == 0) goto L_0x0032
                                java.lang.Object r0 = com.google.appinventor.components.runtime.util.BluetoothReflection.accept(r0)     // Catch:{ Exception -> 0x0018 }
                                com.google.appinventor.components.runtime.BluetoothServer r1 = com.google.appinventor.components.runtime.BluetoothServer.this
                                r1.StopAccepting()
                                goto L_0x0033
                            L_0x0016:
                                r0 = move-exception
                                goto L_0x002c
                            L_0x0018:
                                com.google.appinventor.components.runtime.BluetoothServer r0 = com.google.appinventor.components.runtime.BluetoothServer.this     // Catch:{ all -> 0x0016 }
                                android.os.Handler r0 = r0.androidUIHandler     // Catch:{ all -> 0x0016 }
                                com.google.appinventor.components.runtime.BluetoothServer$1$1 r1 = new com.google.appinventor.components.runtime.BluetoothServer$1$1     // Catch:{ all -> 0x0016 }
                                r1.<init>()     // Catch:{ all -> 0x0016 }
                                r0.post(r1)     // Catch:{ all -> 0x0016 }
                                com.google.appinventor.components.runtime.BluetoothServer r0 = com.google.appinventor.components.runtime.BluetoothServer.this
                                r0.StopAccepting()
                                return
                            L_0x002c:
                                com.google.appinventor.components.runtime.BluetoothServer r1 = com.google.appinventor.components.runtime.BluetoothServer.this
                                r1.StopAccepting()
                                throw r0
                            L_0x0032:
                                r0 = 0
                            L_0x0033:
                                if (r0 == 0) goto L_0x0043
                                com.google.appinventor.components.runtime.BluetoothServer r1 = com.google.appinventor.components.runtime.BluetoothServer.this
                                android.os.Handler r1 = r1.androidUIHandler
                                com.google.appinventor.components.runtime.BluetoothServer$1$2 r2 = new com.google.appinventor.components.runtime.BluetoothServer$1$2
                                r2.<init>(r0)
                                r1.post(r2)
                            L_0x0043:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.BluetoothServer.AnonymousClass1.run():void");
                        }
                    });
                } catch (Exception unused) {
                    this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_LISTEN, new Object[0]);
                }
            } catch (IllegalArgumentException unused2) {
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_INVALID_UUID, str3);
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean IsAccepting() {
        return this.arBluetoothServerSocket.get() != null;
    }

    @SimpleFunction(description = "Stop accepting an incoming connection.")
    public final void StopAccepting() {
        Object andSet = this.arBluetoothServerSocket.getAndSet((Object) null);
        if (andSet != null) {
            try {
                BluetoothReflection.closeBluetoothServerSocket(andSet);
            } catch (Exception e) {
                String str = this.logTag;
                Log.w(str, "Error while closing bluetooth server socket: " + e.getMessage());
            }
        }
    }

    @SimpleEvent(description = "Indicates that a bluetooth connection has been accepted.")
    public final void ConnectionAccepted() {
        Log.i(this.logTag, "Successfully accepted bluetooth connection.");
        EventDispatcher.dispatchEvent(this, "ConnectionAccepted", new Object[0]);
    }
}
