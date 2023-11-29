package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that measures the force of gravity in m/s2 that is applied to a device on all three physical axes (x, y, z).</p>", iconName = "images/gravitysensor.png", nonVisible = true, version = 1)
public class GravitySensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, OnStopListener, SensorComponent {
    private boolean LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private boolean enabled = true;
    private Sensor hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ = 9;
    private final SensorManager sensorManager;
    private float xAccel = 0.0f;
    private float yAccel = 0.0f;
    private float zAccel = 0.0f;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public GravitySensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnPause(this);
        SensorManager sensorManager2 = (SensorManager) componentContainer.$context().getSystemService("sensor");
        this.sensorManager = sensorManager2;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sensorManager2.getDefaultSensor(this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ);
        startListening();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Available() {
        return this.sensorManager.getSensorList(this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ).size() > 0;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float MaximumRange() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMaximumRange();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
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

    @SimpleEvent(description = "Indicates that the gravity sensor data has changed. ")
    public void GravityChanged(float f, float f2, float f3) {
        EventDispatcher.dispatchEvent(this, "GravityChanged", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float XAccel() {
        return this.xAccel;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float YAccel() {
        return this.yAccel;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float ZAccel() {
        return this.zAccel;
    }

    public void onResume() {
        if (this.enabled) {
            startListening();
        }
    }

    public void onStop() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onPause() {
        stopListening();
    }

    private void startListening() {
        SensorManager sensorManager2;
        Sensor sensor;
        if (!this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager2 = this.sensorManager) != null && (sensor = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME) != null) {
            sensorManager2.registerListener(this, sensor, 3);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = true;
        }
    }

    private void stopListening() {
        SensorManager sensorManager2;
        if (this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager2 = this.sensorManager) != null) {
            sensorManager2.unregisterListener(this);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
            this.xAccel = 0.0f;
            this.yAccel = 0.0f;
            this.zAccel = 0.0f;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled && sensorEvent.sensor.getType() == this.ihRLpfoglw4vpN1eVLFIksSKviBvteBH3bd3uSRN7VsdBdKQo5NWEdm996sfoJhJ) {
            sensorEvent.values.clone();
            this.xAccel = sensorEvent.values[0];
            this.yAccel = sensorEvent.values[1];
            float f = sensorEvent.values[2];
            this.zAccel = f;
            GravityChanged(this.xAccel, this.yAccel, f);
        }
    }
}
