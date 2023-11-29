package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum Permission implements OptionList<String> {
    CoarseLocation("ACCESS_COARSE_LOCATION"),
    FineLocation("ACCESS_FINE_LOCATION"),
    MockLocation("ACCESS_MOCK_LOCATION"),
    LocationExtraCommands("ACCESS_LOCATION_EXTRA_COMMANDS"),
    ReadExternalStorage("READ_EXTERNAL_STORAGE"),
    WriteExternalStorage("WRITE_EXTERNAL_STORAGE"),
    Camera("CAMERA"),
    Audio("RECORD_AUDIO"),
    Vibrate("VIBRATE"),
    Internet("INTERNET"),
    NearFieldCommunication("NFC"),
    Bluetooth("BLUETOOTH"),
    BluetoothAdmin("BLUETOOTH_ADMIN"),
    WifiState("ACCESS_WIFI_STATE"),
    NetworkState("ACCESS_NETWORK_STATE"),
    AccountManager("ACCOUNT_MANAGER"),
    ManageAccounts("MANAGE_ACCOUNTS"),
    GetAccounts("GET_ACCOUNTS"),
    ReadContacts("READ_CONTACTS"),
    UseCredentials("USE_CREDENTIALS"),
    BluetoothAdvertise("BLUETOOTH_ADVERTISE"),
    BluetoothConnect("BLUETOOTH_CONNECT"),
    BluetoothScan("BLUETOOTH_SCAN"),
    ReadMediaImages("READ_MEDIA_IMAGES"),
    ReadMediaVideo("READ_MEDIA_VIDEO"),
    ReadMediaAudio("READ_MEDIA_AUDIO");
    
    private static final Map<String, Permission> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    private final String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    static {
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new HashMap();
        for (Permission permission : values()) {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(permission.toUnderlyingValue(), permission);
        }
    }

    private Permission(String str) {
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = str;
    }

    public final String toUnderlyingValue() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    public static Permission fromUnderlyingValue(String str) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str);
    }
}
