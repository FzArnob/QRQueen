package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Provides basic information about the network connectivity of the device", iconName = "images/network.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE"})
public class Network extends AndroidNonvisibleComponent implements Component {
    private ConnectivityManager B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    /* access modifiers changed from: private */

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    public NetworkInfo f240B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    /* access modifiers changed from: private */
    public boolean WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA = false;
    private final Activity activity;
    private Context context;
    /* access modifiers changed from: private */
    public String dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = "NONE";
    private final BroadcastReceiver vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R;

    public Network(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        AnonymousClass1 r0 = new BroadcastReceiver() {
            public final void onReceive(Context context, Intent intent) {
                try {
                    Network network = Network.this;
                    boolean z = false;
                    if (!intent.getBooleanExtra("noConnectivity", false)) {
                        z = true;
                    }
                    boolean unused = network.WT81i4At6dHne14KPL5TcdNiShzKisif1ehRA81016Xex9QKtWws9m2RXtqd3wpA = z;
                    Network network2 = Network.this;
                    NetworkInfo unused2 = network2.f240B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Network.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(network2);
                    if (Network.this.f240B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T == null || !Network.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network.this)) {
                        String unused3 = Network.this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = "NONE";
                        Network.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network.this).runOnUiThread(new Runnable() {
                            public final void run() {
                                Network.this.OnDisconnect();
                            }
                        });
                        return;
                    }
                    Network network3 = Network.this;
                    String unused4 = network3.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw = network3.f240B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getTypeName().toLowerCase();
                    Network.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Network.this).runOnUiThread(new Runnable() {
                        public final void run() {
                            Network.this.OnConnect();
                        }
                    });
                } catch (Exception e) {
                    Log.e("Network", String.valueOf(e));
                }
            }
        };
        this.vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = r0;
        Activity $context = componentContainer.$context();
        this.activity = $context;
        Activity $context2 = componentContainer.$context();
        this.context = $context2;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = (ConnectivityManager) $context2.getSystemService("connectivity");
        $context.registerReceiver(r0, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        Log.d("Network", "Network Created");
    }

    @Deprecated
    @SimpleFunction(description = "Returns the type of network the device is connected to. e.g. \"wifi\" or \"mobile\". This block is deprecated, use the \"IsWiFiConnection\" and \"IsMobileConnection\" blocks instead")
    public String Type() {
        return this.dNRA8zk5IiUh4Pp3hPTF1cOQ5zFA2APl8kyLVDxMBtQN7HXMvFLEdGqNCHj4PKNw;
    }

    @SimpleEvent(description = "Called when the device connects to a network.")
    public void OnConnect() {
        EventDispatcher.dispatchEvent(this, "OnConnect", new Object[0]);
    }

    @SimpleEvent(description = "Called when the device disconnects from a network.")
    public void OnDisconnect() {
        EventDispatcher.dispatchEvent(this, "OnDisconnect", new Object[0]);
    }

    @SimpleFunction(description = "Returns \"True\" if the device is connected to a network, \"False\" otherwise.")
    public boolean IsConnected() {
        try {
            NetworkInfo activeNetworkInfo = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
            this.f240B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = activeNetworkInfo;
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            Log.e("Network", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Returns true if connection is through WiFi")
    public boolean IsWiFiConnection() {
        NetworkInfo activeNetworkInfo = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.getType() == 1;
    }

    @SimpleFunction(description = "Returns true if connection is through Mobile")
    public boolean IsMobileConnection() {
        NetworkInfo activeNetworkInfo = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.getType() == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[RETURN] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns true if using a fast connection")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean IsFastConnection() {
        /*
            r4 = this;
            android.net.ConnectivityManager r0 = r4.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()
            r1 = 0
            if (r0 == 0) goto L_0x0025
            boolean r2 = r0.isConnectedOrConnecting()
            if (r2 == 0) goto L_0x0025
            int r2 = r0.getType()
            int r0 = r0.getSubtype()
            r3 = 1
            if (r2 != r3) goto L_0x001c
        L_0x001a:
            r0 = 1
            goto L_0x0022
        L_0x001c:
            if (r2 != 0) goto L_0x0021
            switch(r0) {
                case 1: goto L_0x0021;
                case 2: goto L_0x0021;
                case 3: goto L_0x001a;
                case 4: goto L_0x0021;
                case 5: goto L_0x001a;
                case 6: goto L_0x001a;
                case 7: goto L_0x0021;
                case 8: goto L_0x001a;
                case 9: goto L_0x001a;
                case 10: goto L_0x001a;
                case 11: goto L_0x0021;
                case 12: goto L_0x001a;
                case 13: goto L_0x001a;
                case 14: goto L_0x001a;
                case 15: goto L_0x001a;
                default: goto L_0x0021;
            }
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 == 0) goto L_0x0025
            return r3
        L_0x0025:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Network.IsFastConnection():boolean");
    }

    @SimpleFunction(description = "Returns true if using roaming")
    public boolean IsRoaming() {
        NetworkInfo activeNetworkInfo = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.isRoaming();
    }
}
