package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "A Component that acts like a Pedometer. It senses motion via the Accerleromter and attempts to determine if a step has been taken. Using a configurable stride length, it can estimate the distance traveled as well. ", iconName = "images/pedometer.png", nonVisible = true, version = 3)
public class Pedometer extends AndroidNonvisibleComponent implements SensorEventListener, Component, Deleteable {
    private long Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = 0;

    /* renamed from: Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB  reason: collision with other field name */
    private float[] f243Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = new float[10];
    private boolean FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = true;
    private int G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = 0;
    private boolean MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl = false;
    private float PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = 0.73f;
    private int Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = 0;
    private int RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = 0;
    private boolean bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = false;
    private final Context context;
    private int gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = 0;
    private int hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = 0;
    private long[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new long[2];
    private long mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = 0;
    private float opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = 0.0f;
    private int pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = 2000;
    private float[] qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = new float[100];
    private long sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = 0;
    private final SensorManager sensorManager;
    private boolean sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = true;
    private float vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 0.0f;

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void CalibrateStrideLength(boolean z) {
    }

    @Deprecated
    @SimpleProperty(description = "deprecated")
    public boolean CalibrateStrideLength() {
        return false;
    }

    @SimpleEvent(description = "deprecated")
    @Deprecated
    public void CalibrationFailed() {
    }

    @SimpleEvent(description = "deprecated")
    @Deprecated
    public void GPSAvailable() {
    }

    @SimpleEvent(description = "deprecated")
    @Deprecated
    public void GPSLost() {
    }

    @Deprecated
    @SimpleProperty(description = "deprecated")
    public boolean Moving() {
        return false;
    }

    @SimpleEvent(description = "deprecated")
    @Deprecated
    public void StartedMoving() {
    }

    @SimpleEvent(description = "deprecated")
    @Deprecated
    public void StoppedMoving() {
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public void UseGPS(boolean z) {
    }

    public Pedometer(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        Activity $context = componentContainer.$context();
        this.context = $context;
        this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = 0;
        this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl = false;
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = 0;
        this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = 0;
        this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = true;
        this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 0.0f;
        this.sensorManager = (SensorManager) $context.getSystemService("sensor");
        SharedPreferences sharedPreferences = $context.getSharedPreferences("PedometerPrefs", 0);
        this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = sharedPreferences.getFloat("Pedometer.stridelength", 0.73f);
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = sharedPreferences.getFloat("Pedometer.distance", 0.0f);
        this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = sharedPreferences.getInt("Pedometer.prevStepCount", 0);
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = sharedPreferences.getLong("Pedometer.clockTime", 0);
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT;
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = System.currentTimeMillis();
        Log.d("Pedometer", "Pedometer Created");
    }

    @SimpleFunction(description = "Start counting steps")
    public void Start() {
        if (this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA) {
            this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = false;
            SensorManager sensorManager2 = this.sensorManager;
            sensorManager2.registerListener(this, sensorManager2.getSensorList(1).get(0), 0);
            this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = System.currentTimeMillis();
        }
    }

    @SimpleFunction(description = "Stop counting steps")
    public void Stop() {
        if (!this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA) {
            this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = true;
            this.sensorManager.unregisterListener(this);
            Log.d("Pedometer", "Unregistered listener on pause");
            this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT += System.currentTimeMillis() - this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt;
        }
    }

    @SimpleFunction(description = "Resets the step counter, distance measure and time running.")
    public void Reset() {
        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = 0;
        this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = 0;
        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = 0.0f;
        this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT = 0;
        this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt = System.currentTimeMillis();
    }

    @Deprecated
    @SimpleFunction(description = "Resumes counting, synonym of Start.")
    public void Resume() {
        Start();
    }

    @Deprecated
    @SimpleFunction(description = "Pause counting of steps and distance.")
    public void Pause() {
        Stop();
    }

    @SimpleFunction(description = "Saves the pedometer state to the phone. Permits permits accumulation of steps and distance between invocations of an App that uses the pedometer. Different Apps will have their own saved state.")
    public void Save() {
        SharedPreferences.Editor edit = this.context.getSharedPreferences("PedometerPrefs", 0).edit();
        edit.putFloat("Pedometer.stridelength", this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY);
        edit.putFloat("Pedometer.distance", this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR);
        edit.putInt("Pedometer.prevStepCount", this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT);
        if (this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA) {
            edit.putLong("Pedometer.clockTime", this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT);
        } else {
            edit.putLong("Pedometer.clockTime", this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT + (System.currentTimeMillis() - this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt));
        }
        edit.putLong("Pedometer.closeTime", System.currentTimeMillis());
        edit.commit();
        Log.d("Pedometer", "Pedometer state saved.");
    }

    @SimpleEvent(description = "This event is run when a raw step is detected")
    public void SimpleStep(int i, float f) {
        EventDispatcher.dispatchEvent(this, "SimpleStep", Integer.valueOf(i), Float.valueOf(f));
    }

    @SimpleEvent(description = "This event is run when a walking step is detected. A walking step is a step that appears to be involved in forward motion.")
    public void WalkStep(int i, float f) {
        EventDispatcher.dispatchEvent(this, "WalkStep", Integer.valueOf(i), Float.valueOf(f));
    }

    @DesignerProperty(defaultValue = "0.73", editorType = "non_negative_float")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the average stride length in meters.")
    public void StrideLength(float f) {
        this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = f;
    }

    @SimpleProperty
    public float StrideLength() {
        return this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY;
    }

    @DesignerProperty(defaultValue = "2000", editorType = "non_negative_integer")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The duration in milliseconds of idleness (no steps detected) after which to go into a \"stopped\" state")
    public void StopDetectionTimeout(int i) {
        this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = i;
    }

    @SimpleProperty
    public int StopDetectionTimeout() {
        return this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The approximate distance traveled in meters.")
    public float Distance() {
        return this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Time elapsed in milliseconds since the pedometer was started.")
    public long ElapsedTime() {
        if (this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA) {
            return this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT;
        }
        return this.mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT + (System.currentTimeMillis() - this.sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of simple steps taken since the pedometer has started.")
    public int SimpleSteps() {
        return this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "the number of walk steps taken since the pedometer has started.")
    public int WalkSteps() {
        return this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d("Pedometer", "Accelerometer accuracy changed.");
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        SensorEvent sensorEvent2 = sensorEvent;
        if (sensorEvent2.sensor.getType() == 1) {
            float f = 0.0f;
            for (float f2 : sensorEvent2.values) {
                f += f2 * f2;
            }
            int i = this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
            int i2 = (i + 50) % 100;
            if (this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl) {
                int i3 = (i + 50) % 100;
                int i4 = 0;
                while (true) {
                    if (i4 >= 100) {
                        z2 = true;
                        break;
                    }
                    if (i4 != i3) {
                        float[] fArr = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
                        if (fArr[i4] > fArr[i3]) {
                            z2 = false;
                            break;
                        }
                    }
                    i4++;
                }
                if (z2 && this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG && this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE[i2] - this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK > 40.0f) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long[] jArr = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    int i5 = this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
                    jArr[i5] = currentTimeMillis - this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
                    this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = (i5 + 1) % 2;
                    this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = currentTimeMillis;
                    float f3 = 0.0f;
                    int i6 = 0;
                    for (long j : jArr) {
                        if (j > 0) {
                            i6++;
                            f3 += (float) j;
                        }
                    }
                    float f4 = f3 / ((float) i6);
                    long[] jArr2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    int length = jArr2.length;
                    int i7 = 0;
                    while (true) {
                        if (i7 >= length) {
                            z3 = true;
                            break;
                        } else if (Math.abs(((float) jArr2[i7]) - f4) > 250.0f) {
                            z3 = false;
                            break;
                        } else {
                            i7++;
                        }
                    }
                    if (z3) {
                        if (this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi) {
                            this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN += 2;
                            this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR += this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY * 2.0f;
                            this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = false;
                        }
                        int i8 = this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN + 1;
                        this.hOhK0kjjpreklHpzajOh4zpZ0hDhUAnmvbGmmElshoJmuxQNkJo9K2Sh2YQvTJN = i8;
                        WalkStep(i8, this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR);
                        this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR += this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY;
                    } else {
                        this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = true;
                    }
                    int i9 = this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT + 1;
                    this.RbYVQzNQ0N2UrAmoggkhMTlc54LPYDcOUMcpFAK7czpNLpbMtrjfrFIT6QATuiqT = i9;
                    SimpleStep(i9, this.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR);
                    this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = false;
                }
            }
            if (this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl) {
                int i10 = (this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl + 50) % 100;
                int i11 = 0;
                while (true) {
                    if (i11 >= 100) {
                        z = true;
                        break;
                    }
                    if (i11 != i10) {
                        float[] fArr2 = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
                        if (fArr2[i11] < fArr2[i10]) {
                            z = false;
                            break;
                        }
                    }
                    i11++;
                }
                if (z) {
                    this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = true;
                    this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE[i2];
                }
            }
            float[] fArr3 = this.f243Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
            int i12 = this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7;
            fArr3[i12] = f;
            this.gmm7XQFeiJoCy8yWhIBdyNlesqTvuP4UyGLNuJ8TYBJTJ249gDjP9OHTkIprzI7 = (i12 + 1) % fArr3.length;
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE[this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl] = 0.0f;
            for (float f5 : fArr3) {
                float[] fArr4 = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
                int i13 = this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
                fArr4[i13] = fArr4[i13] + f5;
            }
            float[] fArr5 = this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
            int i14 = this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
            float length2 = fArr5[i14] / ((float) this.f243Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.length);
            fArr5[i14] = length2;
            boolean z4 = this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl;
            if (z4 || i14 > 1) {
                int i15 = i14 - 1;
                if (i15 < 0) {
                    i15 += 100;
                }
                float f6 = length2 + (fArr5[i15] * 2.0f);
                fArr5[i14] = f6;
                int i16 = i15 - 1;
                if (i16 < 0) {
                    i16 += 100;
                }
                float f7 = f6 + fArr5[i16];
                fArr5[i14] = f7;
                fArr5[i14] = f7 / 4.0f;
            } else if (!z4 && i14 == 1) {
                fArr5[1] = (fArr5[1] + fArr5[0]) / 2.0f;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (currentTimeMillis2 - this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB > ((long) this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok)) {
                this.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = currentTimeMillis2;
            }
            int i17 = this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
            if (i17 == 99 && !this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl) {
                this.MPU09J1AwAGHHipshACCcxJb3sgE9iJCZW8vEkhwkK7OZvOaOd0ZOicUGCeoxucl = true;
            }
            this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = (i17 + 1) % 100;
        }
    }

    public void onDelete() {
        this.sensorManager.unregisterListener(this);
    }
}
