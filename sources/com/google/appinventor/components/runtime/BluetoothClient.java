package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SimpleObject
@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Bluetooth client component", iconName = "images/bluetoothClient.png", nonVisible = true, version = 6)
@UsesPermissions({"android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT"})
public final class BluetoothClient extends BluetoothConnectionBase {
    private final List<Component> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new ArrayList();
    private Set<Integer> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public BluetoothClient(ComponentContainer componentContainer) {
        super(componentContainer, PropertyTypeConstants.PROPERTY_TYPE_BLUETOOTHCLIENT);
    }

    /* access modifiers changed from: package-private */
    public final boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Component component, Set<Integer> set) {
        HashSet hashSet;
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.isEmpty()) {
            if (set == null) {
                hashSet = null;
            } else {
                hashSet = new HashSet(set);
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hashSet;
        } else {
            Set<Integer> set2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            if (set2 == null) {
                if (set != null) {
                    return false;
                }
            } else if (set == null || !set2.containsAll(set) || !set.containsAll(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return false;
            }
        }
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.add(component);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Component component) {
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.remove(component);
        if (this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.isEmpty()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
        }
    }

    @SimpleFunction(description = "Checks whether the Bluetooth device with the specified address is paired.")
    public final boolean IsDevicePaired(String str) {
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", ErrorMessages.ERROR_BLUETOOTH_NOT_AVAILABLE, new Object[0]);
            return false;
        } else if (!BluetoothReflection.isBluetoothEnabled(bluetoothAdapter)) {
            this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", ErrorMessages.ERROR_BLUETOOTH_NOT_ENABLED, new Object[0]);
            return false;
        } else {
            int indexOf = str.indexOf(" ");
            if (indexOf != -1) {
                str = str.substring(0, indexOf);
            }
            if (BluetoothReflection.checkBluetoothAddress(bluetoothAdapter, str)) {
                return BluetoothReflection.isBonded(BluetoothReflection.getRemoteDevice(bluetoothAdapter, str));
            }
            this.form.dispatchErrorOccurredEvent(this, "IsDevicePaired", ErrorMessages.ERROR_BLUETOOTH_INVALID_ADDRESS, new Object[0]);
            return false;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The addresses and names of paired Bluetooth devices")
    public final List<String> AddressesAndNames() {
        ArrayList arrayList = new ArrayList();
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        if (bluetoothAdapter != null && BluetoothReflection.isBluetoothEnabled(bluetoothAdapter)) {
            for (Object next : BluetoothReflection.getBondedDevices(bluetoothAdapter)) {
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(next)) {
                    String bluetoothDeviceName = BluetoothReflection.getBluetoothDeviceName(next);
                    String bluetoothDeviceAddress = BluetoothReflection.getBluetoothDeviceAddress(next);
                    arrayList.add(bluetoothDeviceAddress + " " + bluetoothDeviceName);
                }
            }
        }
        return arrayList;
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Object obj) {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            return true;
        }
        Object bluetoothClass = BluetoothReflection.getBluetoothClass(obj);
        if (bluetoothClass == null) {
            return false;
        }
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.contains(Integer.valueOf(BluetoothReflection.getDeviceClass(bluetoothClass)));
    }

    @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and the Serial Port Profile (SPP). Returns true if the connection was successful.")
    public final boolean Connect(String str) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Connect", str, "00001101-0000-1000-8000-00805F9B34FB");
    }

    @SimpleFunction(description = "Connect to the Bluetooth device with the specified address and UUID. Returns true if the connection was successful.")
    public final boolean ConnectWithUUID(String str, String str2) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("ConnectWithUUID", str, str2);
    }

    @SimpleFunction(description = "Remove the Name from a Bluetooth Address and Name String.")
    public final String RemoveNameFromAddress(String str) {
        int indexOf = str.indexOf(32);
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }

    private boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2, String str3) {
        Object obj;
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        if (bluetoothAdapter == null) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_AVAILABLE, new Object[0]);
            return false;
        } else if (!BluetoothReflection.isBluetoothEnabled(bluetoothAdapter)) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_ENABLED, new Object[0]);
            return false;
        } else {
            int indexOf = str2.indexOf(" ");
            if (indexOf != -1) {
                str2 = str2.substring(0, indexOf);
            }
            if (!BluetoothReflection.checkBluetoothAddress(bluetoothAdapter, str2)) {
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_INVALID_ADDRESS, new Object[0]);
                return false;
            }
            Object remoteDevice = BluetoothReflection.getRemoteDevice(bluetoothAdapter, str2);
            if (!BluetoothReflection.isBonded(remoteDevice)) {
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_PAIRED_DEVICE, new Object[0]);
                return false;
            } else if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(remoteDevice)) {
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_NOT_REQUIRED_CLASS_OF_DEVICE, new Object[0]);
                return false;
            } else {
                try {
                    UUID fromString = UUID.fromString(str3);
                    Disconnect();
                    try {
                        if (!this.secure) {
                            obj = BluetoothReflection.createInsecureRfcommSocketToServiceRecord(remoteDevice, fromString);
                        } else {
                            obj = BluetoothReflection.createRfcommSocketToServiceRecord(remoteDevice, fromString);
                        }
                        BluetoothReflection.connectToBluetoothSocket(obj);
                        setConnection(obj);
                        String str4 = this.logTag;
                        Log.i(str4, "Connected to Bluetooth device " + BluetoothReflection.getBluetoothDeviceAddress(remoteDevice) + " " + BluetoothReflection.getBluetoothDeviceName(remoteDevice) + ".");
                        return true;
                    } catch (Exception unused) {
                        Disconnect();
                        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_CONNECT, new Object[0]);
                        return false;
                    }
                } catch (IllegalArgumentException unused2) {
                    this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_BLUETOOTH_INVALID_UUID, str3);
                    return false;
                }
            }
        }
    }
}
