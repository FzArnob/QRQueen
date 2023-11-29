package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.util.ArrayList;
import java.util.List;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "BluetoothAdmin component to control the Bluetooth.", iconName = "images/bluetoothAdmin.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions({"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION"})
public class BluetoothAdmin extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnResumeListener, OnStopListener {
    private final BroadcastReceiver B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = new a();
    private boolean F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = false;
    private String J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU;
    private boolean LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private final Activity activity;
    private Context context;
    private Form form;
    /* access modifiers changed from: private */
    public BluetoothAdapter hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public List<String> vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new ArrayList();
    private final BroadcastReceiver wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new b();
    /* access modifiers changed from: private */

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    public List<String> f48wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new ArrayList();

    class b extends BroadcastReceiver {
        b() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                BluetoothAdmin bluetoothAdmin = BluetoothAdmin.this;
                bluetoothAdmin.StateChanged(bluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)));
            }
        }
    }

    class a extends BroadcastReceiver {
        a() {
        }

        class b implements Runnable {
            b() {
            }

            public final void run() {
                BluetoothAdmin.this.AfterScanning(BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this), BluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BluetoothAdmin.this));
            }
        }

        /* renamed from: com.google.appinventor.components.runtime.BluetoothAdmin$a$a  reason: collision with other inner class name */
        class C0000a implements Runnable {
            C0000a() {
            }

            public final void run() {
                BluetoothAdmin.this.AfterPairing(BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this));
            }
        }

        class c implements Runnable {
            c() {
            }

            public final void run() {
                BluetoothAdmin.this.AfterUnpairing(BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this));
            }
        }

        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                action.hashCode();
                char c2 = 65535;
                switch (action.hashCode()) {
                    case -1780914469:
                        if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 1167529923:
                        if (action.equals("android.bluetooth.device.action.FOUND")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 2116862345:
                        if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                            c2 = 2;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        if (BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this) == null) {
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).add("");
                        }
                        if (BluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BluetoothAdmin.this) == null) {
                            BluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BluetoothAdmin.this).add("");
                        }
                        BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).runOnUiThread(new b());
                        BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this);
                        return;
                    case 1:
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                        String str = bluetoothDevice.getAddress() + " " + bluetoothDevice.getName();
                        if (bluetoothDevice.getBondState() != 12) {
                            BluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BluetoothAdmin.this).add(str);
                            return;
                        } else {
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).add(str);
                            return;
                        }
                    case 2:
                        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                        int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
                        if (intExtra == 12 && intExtra2 == 11) {
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).runOnUiThread(new C0000a());
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this);
                            return;
                        } else if (intExtra == 10 && intExtra2 == 12) {
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).runOnUiThread(new c());
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this);
                            return;
                        } else {
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    public BluetoothAdmin(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form = componentContainer.$form();
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        this.form.registerForOnStop(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnDestroy(this);
        Log.d("BluetoothAdmin", "BluetoothAdmin Created");
    }

    public void Initialize() {
        this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    BluetoothAdmin bluetoothAdmin = BluetoothAdmin.this;
                    BluetoothAdapter unused = bluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ((BluetoothManager) BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(bluetoothAdmin).getSystemService("bluetooth")).getAdapter();
                    BluetoothAdmin.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(BluetoothAdmin.this);
                    return;
                }
                BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).dispatchPermissionDeniedEvent((Component) BluetoothAdmin.this, "Initialize", str);
            }
        });
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Use codes instead of strings in returns for ScanMode and State.")
    public boolean UseCodes() {
        return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void UseCodes(boolean z) {
        this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = z;
    }

    @SimpleFunction(description = "Returns the Bluetooth MacAddress.")
    public String MacAddress() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return Settings.Secure.getString(this.context.getContentResolver(), "bluetooth_address");
        }
        ErrorOccurred("Device has no Bluetooth");
        return "UNKNOWN";
    }

    @SimpleFunction(description = "Returns true if the MacAddress is valid.")
    public boolean ValidateMacAddress(String str) {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return BluetoothAdapter.checkBluetoothAddress(str);
        }
        ErrorOccurred("Device has no Bluetooth");
        return false;
    }

    @SimpleFunction(description = "Returns true if the User MacAddress is valid.")
    public boolean ValidateUserMacAddress() {
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return BluetoothAdapter.checkBluetoothAddress(Settings.Secure.getString(this.context.getContentResolver(), "bluetooth_address"));
        }
        ErrorOccurred("Device has no Bluetooth");
        return false;
    }

    @SimpleFunction(description = "Returns the state of the Bluetooth Adapter.")
    public String State() {
        BluetoothAdapter bluetoothAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bluetoothAdapter != null) {
            return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(bluetoothAdapter.getState());
        }
        ErrorOccurred("Device has no Bluetooth");
        return "UNKNOWN";
    }

    @SimpleFunction(description = "Enable Bluetooth")
    public void Enable() {
        BluetoothAdapter bluetoothAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bluetoothAdapter == null) {
            ErrorOccurred("Device has no Bluetooth");
        } else {
            bluetoothAdapter.enable();
        }
    }

    @SimpleFunction(description = "Disable Bluetooth")
    public void Disable() {
        BluetoothAdapter bluetoothAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bluetoothAdapter == null) {
            ErrorOccurred("Device has no Bluetooth");
        } else {
            bluetoothAdapter.disable();
        }
    }

    @SimpleFunction(description = "Toggle Bluetooth")
    public void Toggle() {
        BluetoothAdapter bluetoothAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bluetoothAdapter == null) {
            ErrorOccurred("Device has no Bluetooth");
        } else if (bluetoothAdapter.isEnabled()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disable();
        } else {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.enable();
        }
    }

    @SimpleFunction(description = "Returns if the device has Bluetooth")
    public boolean HasBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    @SimpleFunction(description = "Returns the scan mode of the Bluetooth Adapter")
    public String ScanMode() {
        BluetoothAdapter bluetoothAdapter = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        if (bluetoothAdapter == null) {
            ErrorOccurred("Device has no Bluetooth");
            return "UNKNOWN";
        } else if (bluetoothAdapter.getScanMode() == 20) {
            return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "20" : "SCAN_MODE_NONE";
        } else {
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getScanMode() == 21) {
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "21" : "SCAN_MODE_CONNECTABLE";
            }
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getScanMode() == 23) {
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "23" : "SCAN_MODE_CONNECTABLE_DISCOVERABLE";
            }
            return "UNKNOWN";
        }
    }

    @SimpleFunction(description = "Scan Bluetooth devices. Caution: Performing device discovery is a heavy procedure for the Bluetooth adapter and will consume a lot of its resources. If you already hold a connection with a device, then performing discovery can significantly reduce the bandwidth available for the connection, so you should not perform discovery while connected.")
    public void Scan() {
        this.form.askPermission("android.permission.ACCESS_COARSE_LOCATION", new PermissionResultHandler() {
            public final void HandlePermissionResponse(String str, boolean z) {
                if (z) {
                    try {
                        if (BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this) == null || !BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).isEnabled()) {
                            BluetoothAdmin.this.ErrorOccurred("Bluetooth is not enabled");
                            return;
                        }
                        List unused = BluetoothAdmin.this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = new ArrayList();
                        List unused2 = BluetoothAdmin.this.f48wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = new ArrayList();
                        if (BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).isDiscovering()) {
                            BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).cancelDiscovery();
                        }
                        BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).startDiscovery();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("android.bluetooth.device.action.FOUND");
                        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
                        BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).registerReceiver(BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this), intentFilter);
                    } catch (Exception e) {
                        Log.e("BluetoothAdmin", String.valueOf(e));
                        BluetoothAdmin bluetoothAdmin = BluetoothAdmin.this;
                        bluetoothAdmin.ErrorOccurred(e.getMessage());
                    }
                } else {
                    BluetoothAdmin.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(BluetoothAdmin.this).dispatchPermissionDeniedEvent((Component) BluetoothAdmin.this, "Scan", str);
                }
            }
        });
    }

    @SimpleFunction(description = "Pair Bluetooth device.")
    public void Pair(String str) {
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = str;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("pair");
    }

    @SimpleFunction(description = "Unpair Bluetooth device.")
    public void Unpair(String str) {
        this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU = str;
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("unpair");
    }

    @SimpleEvent(description = "Event triggers when an error occurred.")
    public void ErrorOccurred(String str) {
        EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleEvent(description = "Event triggers when the bluetooth state changed.")
    public void StateChanged(String str) {
        EventDispatcher.dispatchEvent(this, "StateChanged", str);
    }

    @SimpleEvent(description = "Event triggers when Scanning has finished.")
    public void AfterScanning(Object obj, Object obj2) {
        EventDispatcher.dispatchEvent(this, "AfterScanning", obj, obj2);
    }

    @SimpleEvent(description = "Event triggers when Pairing has finished.")
    public void AfterPairing(String str) {
        EventDispatcher.dispatchEvent(this, "AfterPairing", str);
    }

    @SimpleEvent(description = "Event triggers when Unpairing has finished.")
    public void AfterUnpairing(String str) {
        EventDispatcher.dispatchEvent(this, "AfterUnpairing", str);
    }

    public void onResume() {
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou();
    }

    public void onStop() {
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq();
    }

    public void onDestroy() {
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq();
        hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO();
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou() {
        if (!this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn) {
            Context context2 = this.context;
            if (context2 != null) {
                context2.registerReceiver(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
            }
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = true;
        }
    }

    private void vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq() {
        if (this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn) {
            try {
                Context context2 = this.context;
                if (context2 != null) {
                    context2.unregisterReceiver(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
                }
            } catch (Exception e) {
                Log.e("BluetoothAdmin", String.valueOf(e));
            }
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
        }
    }

    private void hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO() {
        try {
            Context context2 = this.context;
            if (context2 != null) {
                context2.unregisterReceiver(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            }
        } catch (Exception e) {
            Log.e("BluetoothAdmin", String.valueOf(e));
        }
    }

    /* access modifiers changed from: private */
    public String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        switch (i) {
            case 10:
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "10" : "STATE_OFF";
            case 11:
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "11" : "STATE_TURNING_ON";
            case 12:
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "12" : "STATE_ON";
            case 13:
                return this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho ? "13" : "STATE_TURNING_OFF";
            default:
                return "UNKNOWN";
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private void m0hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        try {
            BluetoothDevice remoteDevice = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRemoteDevice(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.J9GCddMpW5m7K6b7f8nFol5AuYTClyx20NTbEibimiSwx7sDFiwQjZjPs0Mw8DwU));
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isDiscovering()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancelDiscovery();
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            this.context.registerReceiver(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, intentFilter);
            if (!str.equals("pair")) {
                remoteDevice.getClass().getDeclaredMethod(str, (Class[]) null).invoke(remoteDevice, (Object[]) null);
            } else {
                remoteDevice.createBond();
            }
        } catch (Exception e) {
            ErrorOccurred(e.getMessage());
        }
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        int indexOf = str.indexOf(32);
        return indexOf != -1 ? str.substring(0, indexOf) : str;
    }
}
