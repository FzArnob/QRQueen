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
@DesignerComponent(category = ComponentCategory.SENSORS, description = "<p>Non-visible component that can measures the proximity of an object in cm relative to the view screen of a device. This sensor is typically used to determine whether a handset is being held up to a persons ear; i.e. lets you determine how far away an object is from a device. Many devices return the absolute distance, in cm, but some return only near and far values. In this case, the sensor usually reports its maximum range value in the far state and a lesser value in the near state.</p>", iconName = "images/proximitysensor.png", nonVisible = true, version = 1)
public class ProximitySensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, OnStopListener, SensorComponent {
    private float I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = 0.0f;
    private boolean I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH;
    private boolean enabled;
    private Sensor qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
    private final SensorManager sensorManager;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public ProximitySensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.form.registerForOnStop(this);
        this.form.registerForOnPause(this);
        this.enabled = true;
        SensorManager sensorManager2 = (SensorManager) componentContainer.$context().getSystemService("sensor");
        this.sensorManager = sensorManager2;
        this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = sensorManager2.getDefaultSensor(8);
        startListening();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether or not the device has a proximity sensor")
    public boolean Available() {
        return this.sensorManager.getSensorList(8).size() > 0;
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
        if (this.enabled && !this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH) {
            stopListening();
        }
    }

    private void startListening() {
        this.sensorManager.registerListener(this, this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE, 3);
    }

    private void stopListening() {
        this.sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled) {
            float f = ((float[]) sensorEvent.values.clone())[0];
            this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = f;
            ProximityChanged(f);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports the Maximum Range of the device's ProximitySensor")
    public float MaximumRange() {
        return this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE.getMaximumRange();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If enabled, then device will listen for changes in proximity")
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

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean KeepRunningWhenOnPause() {
        return this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "If set to true, it will keep sensing for proximity changes even when the app is not visible")
    public void KeepRunningWhenOnPause(boolean z) {
        this.I88voynnB6lCbi2eFsNA2DcXYsTa46aEiMaovSB2sEx9V3gZP1qdgaJzEiYc8qjH = z;
    }

    @SimpleEvent(description = "Triggered when distance (in cm) of the object to the device changes. ")
    public void ProximityChanged(float f) {
        this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = f;
        EventDispatcher.dispatchEvent(this, "ProximityChanged", Float.valueOf(f));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the distance from the object to the device")
    public float Distance() {
        return this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q;
    }
}
