package com.google.appinventor.components.runtime;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;

@SimpleObject
public abstract class SingleValueSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, SensorComponent {
    private Sensor Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    protected boolean enabled = true;
    protected int refreshTime = 1000;
    protected final SensorManager sensorManager;
    protected int sensorType;
    protected float value;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* access modifiers changed from: protected */
    public abstract void onValueChanged(float f);

    public SingleValueSensor(ComponentContainer componentContainer, int i) {
        super(componentContainer.$form());
        this.sensorType = i;
        this.form.registerForOnResume(this);
        this.form.registerForOnPause(this);
        SensorManager sensorManager2 = (SensorManager) componentContainer.$context().getSystemService("sensor");
        this.sensorManager = sensorManager2;
        this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = sensorManager2.getDefaultSensor(i);
        startListening();
    }

    /* access modifiers changed from: protected */
    public void startListening() {
        this.sensorManager.registerListener(this, this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB, this.refreshTime * 1000);
    }

    /* access modifiers changed from: protected */
    public void stopListening() {
        this.sensorManager.unregisterListener(this);
    }

    @SimpleProperty(description = "Specifies whether or not the device has an ambient air pressure sensor.")
    public boolean Available() {
        return isAvailable();
    }

    @SimpleProperty(description = "If enabled, then device will listen for changes.")
    public boolean Enabled() {
        return this.enabled;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Enabled(boolean z) {
        setEnabled(z);
    }

    @SimpleProperty(description = "The requested minimum time in milliseconds between changes in air pressure being reported. Android is not guaranteed to honor the request. Setting this property has no effect on pre-Gingerbread devices.")
    public int RefreshTime() {
        return this.refreshTime;
    }

    @DesignerProperty(defaultValue = "1000", editorType = "non_negative_integer")
    @SimpleProperty
    public void RefreshTime(int i) {
        this.refreshTime = i;
        if (this.enabled) {
            stopListening();
            startListening();
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled && sensorEvent.sensor.getType() == this.sensorType) {
            float f = sensorEvent.values[0];
            this.value = f;
            onValueChanged(f);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return this.sensorManager.getSensorList(this.sensorType).size() > 0;
    }

    /* access modifiers changed from: protected */
    public void setEnabled(boolean z) {
        if (this.enabled != z) {
            this.enabled = z;
            if (z) {
                startListening();
            } else {
                stopListening();
            }
        }
    }

    public void onPause() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onResume() {
        if (this.enabled) {
            startListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }

    /* access modifiers changed from: protected */
    public float getValue() {
        return this.value;
    }
}
