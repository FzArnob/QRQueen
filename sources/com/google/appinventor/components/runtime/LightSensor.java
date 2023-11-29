package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Light Sensor to get the Illuminance from the Phone Sensor", iconName = "images/lightSensor.png", nonVisible = true, version = 1)
public class LightSensor extends AndroidNonvisibleComponent implements SensorEventListener, Component, OnDestroyListener, OnPauseListener, OnResumeListener {
    private boolean LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private final Activity activity;
    private ComponentContainer container;
    private Context context;
    private boolean enabled;
    private final SensorManager hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private float tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = 0.0f;
    private final Sensor wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public LightSensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnPause(this);
        this.form.registerForOnResume(this);
        this.form.registerForOnDestroy(this);
        this.container = componentContainer;
        this.context = componentContainer.$context();
        this.activity = componentContainer.$context();
        SensorManager sensorManager = (SensorManager) this.context.getSystemService("sensor");
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sensorManager;
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = sensorManager.getDefaultSensor(5);
        startListening();
        Enabled(true);
        Log.d("LightSensor", "Light Sensor Created");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Sensor is Available")
    public boolean Available() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getSensorList(5).size() > 0;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Sensor should be Enabled")
    public void Enabled(boolean z) {
        if (this.enabled != z) {
            this.enabled = z;
            if (z) {
                startListening();
            } else {
                stopListening();
            }
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the illuminance in lux. To return values the sensor must be enabled")
    public float Illuminance() {
        return this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    }

    @SimpleEvent(description = "Event that fires when the illuminance is changed")
    public void LightChanged(float f) {
        EventDispatcher.dispatchEvent(this, "LightChanged", Float.valueOf(f));
    }

    private void startListening() {
        SensorManager sensorManager;
        Sensor sensor;
        if (!this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) != null && (sensor = this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou) != null) {
            sensorManager.registerListener(this, sensor, 3);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = true;
        }
    }

    private void stopListening() {
        SensorManager sensorManager;
        if (this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) != null) {
            sensorManager.unregisterListener(this);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
        }
    }

    public void onResume() {
        Log.d("LightSensor", "onResume");
        if (this.enabled) {
            startListening();
        }
    }

    public void onPause() {
        Log.d("LightSensor", "onPause");
        stopListening();
    }

    public void onDestroy() {
        Log.d("LightSensor", "onDestroy");
        stopListening();
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this) {
            if (sensorEvent.sensor.getType() == 5) {
                this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = sensorEvent.values[0];
            }
        }
        LightChanged(this.tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE);
    }
}
