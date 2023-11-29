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
@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that measures the ambient geomagnetic field for all three physical axes (x, y, z) in Tesla https://en.wikipedia.org/wiki/Tesla_(unit). </p>", iconName = "images/magneticSensor.png", nonVisible = true, version = 1)
public class MagneticFieldSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, OnStopListener, SensorComponent {
    private boolean LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private double PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private float bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
    private boolean enabled = true;
    private float f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    private final SensorManager sensorManager;
    private Sensor vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private float zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public MagneticFieldSensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnPause(this);
        SensorManager sensorManager2 = (SensorManager) componentContainer.$context().getSystemService("sensor");
        this.sensorManager = sensorManager2;
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = sensorManager2.getDefaultSensor(2);
        startListening();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Available() {
        return this.sensorManager.getSensorList(2).size() > 0;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float MaximumRange() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.getMaximumRange();
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
        }
        if (this.enabled) {
            startListening();
        } else {
            stopListening();
        }
    }

    @SimpleEvent(description = "Indicates that the magnetic sensor data has changed. ")
    public void MagneticChanged(float f, float f2, float f3, double d) {
        EventDispatcher.dispatchEvent(this, "MagneticChanged", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3), Double.valueOf(d));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public double AbsoluteStrength() {
        return this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float Xstrength() {
        return this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float Ystrength() {
        return this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public float Zstrength() {
        return this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b;
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
        if (!this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager2 = this.sensorManager) != null && (sensor = this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq) != null) {
            sensorManager2.registerListener(this, sensor, 3);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = true;
        }
    }

    private void stopListening() {
        SensorManager sensorManager2;
        if (this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn && (sensorManager2 = this.sensorManager) != null) {
            sensorManager2.unregisterListener(this);
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = false;
            this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = 0.0f;
            this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = 0.0f;
            this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = 0.0f;
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled && sensorEvent.sensor.getType() == 2) {
            sensorEvent.values.clone();
            this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ = sensorEvent.values[0];
            this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = sensorEvent.values[1];
            float f = sensorEvent.values[2];
            this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = f;
            float f2 = this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ;
            float f3 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            double sqrt = Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f * f)));
            this.PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = sqrt;
            MagneticChanged(this.f473zvQHzIj2nKVz26VGsoax0ZAlFbP830ERztRpaiUumZZlKb9jZe39pU8AJ0YJ, this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b, sqrt);
        }
    }
}
