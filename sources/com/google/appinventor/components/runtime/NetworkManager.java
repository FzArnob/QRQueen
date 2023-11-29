package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.DEPRECATED, description = "", iconName = "images/network_mgr.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.ACCESS_NETWORK_STATE"})
public class NetworkManager extends AndroidNonvisibleComponent implements Component {
    private final Context context;

    public NetworkManager(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.context = componentContainer.$context();
    }

    @SimpleFunction(description = "Returns true if connection is through WiFi")
    public boolean IsWiFiConnection() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting() && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getType() == 1;
    }

    @SimpleFunction(description = "Returns true if connection is through Mobile")
    public boolean IsMobileConnection() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting() && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getType() == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022 A[RETURN] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Returns true if using a fast connection")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean IsFastConnection() {
        /*
            r4 = this;
            android.net.NetworkInfo r0 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME()
            r1 = 0
            if (r0 == 0) goto L_0x0023
            boolean r2 = r0.isConnectedOrConnecting()
            if (r2 == 0) goto L_0x0023
            int r2 = r0.getType()
            int r0 = r0.getSubtype()
            r3 = 1
            if (r2 != r3) goto L_0x001a
        L_0x0018:
            r0 = 1
            goto L_0x0020
        L_0x001a:
            if (r2 != 0) goto L_0x001f
            switch(r0) {
                case 1: goto L_0x001f;
                case 2: goto L_0x001f;
                case 3: goto L_0x0018;
                case 4: goto L_0x001f;
                case 5: goto L_0x0018;
                case 6: goto L_0x0018;
                case 7: goto L_0x001f;
                case 8: goto L_0x0018;
                case 9: goto L_0x0018;
                case 10: goto L_0x0018;
                case 11: goto L_0x001f;
                case 12: goto L_0x0018;
                case 13: goto L_0x0018;
                case 14: goto L_0x0018;
                case 15: goto L_0x0018;
                default: goto L_0x001f;
            }
        L_0x001f:
            r0 = 0
        L_0x0020:
            if (r0 == 0) goto L_0x0023
            return r3
        L_0x0023:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NetworkManager.IsFastConnection():boolean");
    }

    @SimpleFunction(description = "Returns true if using using roaming")
    public boolean IsRoaming() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting() && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRoaming();
    }

    @SimpleFunction(description = "Indicates whether network connectivity exists and it is possible to establish connections and pass data.")
    public boolean IsConnected() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnectedOrConnecting();
    }

    @SimpleFunction(description = " describe the type of the network, for example WIFI or MOBILE")
    public String GetConnectionType() {
        NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME();
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return "UNABLE TO GET CONNECTION TYPE";
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getTypeName();
    }

    private NetworkInfo hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        return ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    @SimpleProperty(description = "Checks to see if device is GPS enabled")
    public boolean IsGPSEnabledDevice() {
        List<String> allProviders;
        LocationManager locationManager = (LocationManager) this.form.getSystemService("location");
        if (locationManager == null || (allProviders = locationManager.getAllProviders()) == null || !allProviders.contains("gps")) {
            return false;
        }
        return true;
    }

    @SimpleProperty(description = "Checks to see if device is GPS enabled and if so, checks to see if GPS is started or not")
    public boolean IsGPSEnabled() {
        if (!IsGPSEnabledDevice()) {
            return false;
        }
        return ((LocationManager) this.form.getSystemService("location")).isProviderEnabled("gps");
    }

    @SimpleProperty(description = "Starts up the GPS configuration activity, giving user option to turn turn on the GPS")
    public boolean StartGPSOptions() {
        this.form.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        return true;
    }
}
