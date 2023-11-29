package com.google.appinventor.components.runtime;

import android.net.wifi.WifiManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Control the WiFi of the Device", helpUrl = "https://docs.kodular.io/components/connectivity/wifi/", iconName = "images/wifi.png", nonVisible = true, version = 2)
@UsesPermissions({"android.permission.INTERNET", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_FINE_LOCATION"})
public class WiFiAdmin extends AndroidNonvisibleComponent implements Component {
    private WifiManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public WiFiAdmin(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (WifiManager) componentContainer.$context().getSystemService("wifi");
        Log.d("WiFiAdmin", "WiFiAdmin Created");
    }

    @SimpleFunction(description = "Enable the Wi-Fi")
    public void Enable() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWifiEnabled(true);
    }

    @SimpleFunction(description = "Disable the Wi-Fi")
    public void Disable() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWifiEnabled(false);
    }

    @SimpleFunction(description = "Toggle the Wi-Fi")
    public void Toggle() {
        WifiManager wifiManager = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled());
    }

    @SimpleFunction(description = "Return whether Wi-Fi is enabled or disabled")
    public boolean IsEnabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isWifiEnabled();
    }

    @SimpleFunction(description = "Return whether this adapter supports 5 GHz band")
    public boolean Is5GHzSupported() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.is5GHzBandSupported();
    }

    @SimpleFunction(description = "Return whether this adapter supports Wi-Fi Direct")
    public boolean IsWiFiDirectSupported() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isP2pSupported();
    }

    @SimpleFunction(description = "Return the current Local IP")
    public String LocalIP() {
        if (IsConnected()) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException unused) {
            }
        }
        return "";
    }

    @SimpleFunction(description = "Returns the service set identifier (SSID) of the current 802.11 network")
    public String SSID() {
        return IsConnected() ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getSSID() : "";
    }

    @SimpleFunction(description = "Return the basic service set identifier (BSSID) of the current access point")
    public String BSSID() {
        return IsConnected() ? this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getBSSID() : "";
    }

    @SimpleFunction(description = "Returns the received signal strength indicator of the current 802.11 network, in dBm")
    public int SignalStrength() {
        if (IsConnected()) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getRssi();
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the current link speed in Mbps")
    public int LinkSpeed() {
        if (IsConnected()) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getLinkSpeed();
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the wlan mac address.")
    public String MacAddress() {
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString().toLowerCase();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception e) {
            Log.e("WiFiAdmin", String.valueOf(e));
            return "02:00:00:00:00:00";
        }
    }

    public boolean IsConnected() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getConnectionInfo().getNetworkId() != -1;
    }
}
