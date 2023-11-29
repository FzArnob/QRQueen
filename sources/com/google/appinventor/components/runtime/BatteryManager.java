package com.google.appinventor.components.runtime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "", iconName = "images/batterymanager.png", nonVisible = true, version = 1)
public class BatteryManager extends AndroidNonvisibleComponent implements Component, OnDestroyListener, OnResumeListener, OnStopListener {
    /* access modifiers changed from: private */
    public float B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = 0.0f;
    /* access modifiers changed from: private */
    public int Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = 0;
    /* access modifiers changed from: private */
    public String PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private String TAG = "BatteryManager";
    private final ComponentContainer container;
    /* access modifiers changed from: private */
    public float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = 0.0f;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private BroadcastReceiver f43hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            String unused = BatteryManager.this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = BatteryManager.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(intent.getIntExtra("health", 0));
            int intExtra = intent.getIntExtra("plugged", 0);
            int i = 1;
            if (intExtra == 2) {
                String unused2 = BatteryManager.this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = "USB";
            } else if (intExtra == 1) {
                String unused3 = BatteryManager.this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = "AC";
            } else {
                String unused4 = BatteryManager.this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = "UNKNOWN";
            }
            boolean unused5 = BatteryManager.this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = intent.getExtras().getBoolean("present");
            int intExtra2 = intent.getIntExtra("scale", 0);
            BatteryManager batteryManager = BatteryManager.this;
            int intExtra3 = intent.getIntExtra("level", 0) * 100;
            if (intExtra2 != 0) {
                i = intExtra2;
            }
            int unused6 = batteryManager.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = intExtra3 / i;
            int unused7 = BatteryManager.this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, 0);
            BatteryManager batteryManager2 = BatteryManager.this;
            String unused8 = batteryManager2.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = (batteryManager2.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB == 2 || BatteryManager.this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB == 5) ? "IS_CHARGING" : "NOT_CHARGING";
            String unused9 = BatteryManager.this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = intent.getExtras().getString("technology");
            float unused10 = BatteryManager.this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ((float) intent.getIntExtra("temperature", 0)) / 10.0f;
            float unused11 = BatteryManager.this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = ((float) intent.getIntExtra("voltage", 0)) / 1000.0f;
        }
    };
    private boolean mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
    /* access modifiers changed from: private */
    public String moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = "";
    /* access modifiers changed from: private */
    public String oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = "";
    /* access modifiers changed from: private */
    public int qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = 0;
    /* access modifiers changed from: private */
    public boolean sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = false;
    /* access modifiers changed from: private */
    public String yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        switch (i) {
            case 2:
                return "GOOD";
            case 3:
                return "OVERHEAT";
            case 4:
                return "DEAD";
            case 5:
                return "OVER_VOLTAGE";
            case 6:
                return "UNSPECIFIED_FAILURE";
            case 7:
                return "COLD";
            default:
                return "UNKNOWN";
        }
    }

    public BatteryManager(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnStop(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnDestroy(this);
        this.container = componentContainer;
    }

    @SimpleFunction(description = "Returns a list showing battery info for the specified key. Key can be: HEALTH, LEVEL, PLUGGED, PRESENT, STATUS, CHARGE_STATUS, TECHNOLOGY, TEMPERATURE, VOLTAGE. If key is empty, then all battery info is returned.")
    public YailList GetBatteryInfo(String str) {
        String trim = str.toLowerCase().trim();
        if (trim.equals("")) {
            trim = "all";
        }
        ArrayList arrayList = new ArrayList();
        if (trim.equals("all") || trim.equals("health")) {
            arrayList.add("HEALTH=" + this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS);
        }
        if (trim.equals("all") || trim.equals("level")) {
            arrayList.add("LEVEL=" + this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE);
        }
        if (trim.equals("all") || trim.equals("plugged")) {
            arrayList.add("PLUGGED=" + this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC);
        }
        if (trim.equals("all") || trim.equals("present")) {
            arrayList.add("PRESENT=" + this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
        }
        if (trim.equals("all") || trim.equals(NotificationCompat.CATEGORY_STATUS)) {
            arrayList.add("STATUS=" + this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB);
        }
        if (trim.equals("all") || trim.equals("charge_status")) {
            arrayList.add("CHARGE_STATUS=" + this.yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT);
        }
        if (trim.equals("all") || trim.equals("technology")) {
            arrayList.add("TECHNOLOGY=" + this.moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0);
        }
        if (trim.equals("all") || trim.equals("temperature")) {
            arrayList.add("TEMPERATURE=" + this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        if (trim.equals("all") || trim.equals("voltage")) {
            arrayList.add("VOLTAGE=" + this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
        }
        Collections.sort(arrayList);
        return YailList.makeList((List) arrayList);
    }

    @SimpleProperty(description = "Indicating battery temperature in Centigrade")
    public float BatteryTemperature() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    @SimpleProperty(description = "Indicating battery voltage in Volts")
    public float BatteryVoltage() {
        return this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    }

    @SimpleProperty(description = "Indicating whether a battery is present")
    public boolean BatteryPresent() {
        return this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
    }

    @SimpleProperty(description = "Indicating whether the device is plugged in to a power source. Can be USB, AC or UNKNOWN")
    public String BatteryPlugged() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @SimpleProperty(description = "Returns battery percentage level")
    public int BatteryLevel() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    }

    @SimpleProperty(description = "Returns battery health. It can be: COLD, DEAD, GOOD, OVERHEAT, OVER_VOLTAGE, UNKNOWN")
    public String BatteryHealth() {
        return this.oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Starts or stops monitoring battery data. StartMonitoring only when you need to get information.")
    public void StartMonitoring(boolean z) {
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = z;
        if (z) {
            this.container.$context().registerReceiver(this.f43hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return;
        }
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
    }

    public void onDestroy() {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
    }

    public void onResume() {
        StartMonitoring(this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT);
    }

    public void onStop() {
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = false;
        if (this.f43hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                this.container.$context().unregisterReceiver(this.f43hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            } catch (Exception unused) {
            }
        }
    }
}
