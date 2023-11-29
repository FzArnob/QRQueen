package com.google.appinventor.components.runtime;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.Sensitivity;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.LinkedList;
import java.util.Queue;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Non-visible component that can detect shaking and measure acceleration approximately in three dimensions using SI units (m/s<sup>2</sup>).  The components are: <ul>\n<li> <strong>xAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when the phone is tilted to the right (i.e.,      its left side is raised), and negative when the phone is tilted      to the left (i.e., its right size is raised).</li>\n <li> <strong>yAccel</strong>: 0 when the phone is at rest on a flat      surface, positive when its bottom is raised, and negative when      its top is raised. </li>\n <li> <strong>zAccel</strong>: Equal to -9.8 (earth's gravity in meters per      second per second when the device is at rest parallel to the ground      with the display facing up,      0 when perpendicular to the ground, and +9.8 when facing down.       The value can also be affected by accelerating it with or against      gravity. </li></ul>", helpUrl = "https://docs.kodular.io/components/sensors/accelerometer/", iconName = "images/accelerometersensor.png", nonVisible = true, version = 5)
public class AccelerometerSensor extends AndroidNonvisibleComponent implements SensorEventListener, Deleteable, OnPauseListener, OnResumeListener, SensorComponent {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "AccelerometerSensor";
    private static final int SENSOR_CACHE_SIZE = 10;
    private static final double moderateShakeThreshold = 13.0d;
    private static final double strongShakeThreshold = 20.0d;
    private static final double weakShakeThreshold = 5.0d;
    private final Queue<Float> X_CACHE = new LinkedList();
    private final Queue<Float> Y_CACHE = new LinkedList();
    private final Queue<Float> Z_CACHE = new LinkedList();
    private Sensor accelerometerSensor;
    private int accuracy;
    private final Handler androidUIHandler;
    /* access modifiers changed from: private */
    public volatile int deviceDefaultOrientation;
    private boolean enabled;
    private boolean legacyMode = false;
    private int minimumInterval;
    private final Resources resources;
    private Sensitivity sensitivity;
    private final SensorManager sensorManager;
    private long timeLastShook;
    private final WindowManager windowManager;
    private float xAccel;
    private float yAccel;
    private float zAccel;

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public AccelerometerSensor(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.form.registerForOnResume(this);
        this.form.registerForOnPause(this);
        this.enabled = true;
        this.resources = componentContainer.$context().getResources();
        this.windowManager = (WindowManager) componentContainer.$context().getSystemService("window");
        SensorManager sensorManager2 = (SensorManager) componentContainer.$context().getSystemService("sensor");
        this.sensorManager = sensorManager2;
        this.accelerometerSensor = sensorManager2.getDefaultSensor(1);
        this.androidUIHandler = new Handler();
        startListening();
        MinimumInterval(400);
        SensitivityAbstract(Sensitivity.Moderate);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The minimum interval, in milliseconds, between phone shakes")
    public int MinimumInterval() {
        return this.minimumInterval;
    }

    @DesignerProperty(defaultValue = "400", editorType = "non_negative_integer")
    @SimpleProperty
    public void MinimumInterval(int i) {
        this.minimumInterval = i;
    }

    @Options(Sensitivity.class)
    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "A number that encodes how sensitive the accelerometer is. The choices are: 1 = weak, 2 = moderate,  3 = strong.")
    public int Sensitivity() {
        return this.sensitivity.toUnderlyingValue().intValue();
    }

    public Sensitivity SensitivityAbstract() {
        return this.sensitivity;
    }

    public void SensitivityAbstract(Sensitivity sensitivity2) {
        this.sensitivity = sensitivity2;
    }

    @DesignerProperty(defaultValue = "2", editorType = "accelerometer_sensitivity")
    @SimpleProperty
    public void Sensitivity(@Options(Sensitivity.class) int i) {
        Sensitivity fromUnderlyingValue = Sensitivity.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.form.dispatchErrorOccurredEvent(this, "Sensitivity", ErrorMessages.ERROR_BAD_VALUE_FOR_ACCELEROMETER_SENSITIVITY, Integer.valueOf(i));
            return;
        }
        SensitivityAbstract(fromUnderlyingValue);
    }

    @SimpleEvent
    public void AccelerationChanged(float f, float f2, float f3) {
        this.xAccel = f;
        this.yAccel = f2;
        this.zAccel = f3;
        addToSensorCache(this.X_CACHE, f);
        addToSensorCache(this.Y_CACHE, f2);
        addToSensorCache(this.Z_CACHE, f3);
        long currentTimeMillis = System.currentTimeMillis();
        if (isShaking(this.X_CACHE, f) || isShaking(this.Y_CACHE, f2) || isShaking(this.Z_CACHE, f3)) {
            long j = this.timeLastShook;
            if (j == 0 || currentTimeMillis >= j + ((long) this.minimumInterval)) {
                this.timeLastShook = currentTimeMillis;
                Shaking();
            }
        }
        EventDispatcher.dispatchEvent(this, "AccelerationChanged", Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
    }

    public int getDeviceDefaultOrientation() {
        Configuration configuration = this.resources.getConfiguration();
        int rotation = this.windowManager.getDefaultDisplay().getRotation();
        Log.d(LOG_TAG, "rotation = ".concat(String.valueOf(rotation)));
        Log.d(LOG_TAG, "config.orientation = " + configuration.orientation);
        if (((rotation == 0 || rotation == 2) && configuration.orientation == 2) || ((rotation == 1 || rotation == 3) && configuration.orientation == 1)) {
            return 2;
        }
        return 1;
    }

    @SimpleEvent
    public void Shaking() {
        EventDispatcher.dispatchEvent(this, "Shaking", new Object[0]);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Available() {
        if (this.sensorManager.getSensorList(1).size() > 0) {
            return true;
        }
        return false;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean Enabled() {
        return this.enabled;
    }

    private void startListening() {
        this.androidUIHandler.postDelayed(new Runnable() {
            public final void run() {
                AccelerometerSensor accelerometerSensor = AccelerometerSensor.this;
                int unused = accelerometerSensor.deviceDefaultOrientation = accelerometerSensor.getDeviceDefaultOrientation();
                Log.d(AccelerometerSensor.LOG_TAG, "deviceDefaultOrientation = " + AccelerometerSensor.this.deviceDefaultOrientation);
                Log.d(AccelerometerSensor.LOG_TAG, "Configuration.ORIENTATION_LANDSCAPE = 2");
                Log.d(AccelerometerSensor.LOG_TAG, "Configuration.ORIENTATION_PORTRAIT = 1");
            }
        }, 32);
        this.sensorManager.registerListener(this, this.accelerometerSensor, 1);
    }

    private void stopListening() {
        this.sensorManager.unregisterListener(this);
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

    private void addToSensorCache(Queue<Float> queue, float f) {
        if (queue.size() >= 10) {
            queue.remove();
        }
        queue.add(Float.valueOf(f));
    }

    private boolean isShaking(Queue<Float> queue, float f) {
        float f2 = 0.0f;
        for (Float floatValue : queue) {
            f2 += floatValue.floatValue();
        }
        float abs = Math.abs((f2 / ((float) queue.size())) - f);
        int i = AnonymousClass2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME[this.sensitivity.ordinal()];
        if (i == 1) {
            return ((double) abs) > strongShakeThreshold;
        }
        if (i == 2) {
            double d = (double) abs;
            return d > moderateShakeThreshold && d < strongShakeThreshold;
        } else if (i != 3) {
            return false;
        } else {
            double d2 = (double) abs;
            return d2 > weakShakeThreshold && d2 < moderateShakeThreshold;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.AccelerometerSensor$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.appinventor.components.common.Sensitivity[] r0 = com.google.appinventor.components.common.Sensitivity.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r0
                com.google.appinventor.components.common.Sensitivity r1 = com.google.appinventor.components.common.Sensitivity.Weak     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.appinventor.components.common.Sensitivity r1 = com.google.appinventor.components.common.Sensitivity.Moderate     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.appinventor.components.common.Sensitivity r1 = com.google.appinventor.components.common.Sensitivity.Strong     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.AccelerometerSensor.AnonymousClass2.<clinit>():void");
        }
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Prior to the release that added this property the AccelerometerSensor component passed through sensor values directly as received from the Android system. However these values do not compensate for tablets that default to Landscape mode, requiring the MIT App Inventor programmer to compensate. However compensating would result in incorrect results in Portrait mode devices such as phones. We now detect Landscape mode tablets and perform the compensation. However if your project is already compensating for the change, you will now get incorrect results. Although our preferred solution is for you to update your project, you can also just set this property to “true” and our compensation code will be deactivated. Note: We recommend that you update your project as we may remove this property in a future release.", userVisible = false)
    public void LegacyMode(boolean z) {
        this.legacyMode = z;
    }

    public boolean LegacyMode() {
        return this.legacyMode;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.enabled) {
            float[] fArr = sensorEvent.values;
            if (this.deviceDefaultOrientation != 2 || this.legacyMode) {
                this.xAccel = fArr[0];
                this.yAccel = fArr[1];
            } else {
                this.xAccel = fArr[1];
                this.yAccel = -fArr[0];
            }
            this.zAccel = fArr[2];
            this.accuracy = sensorEvent.accuracy;
            AccelerationChanged(this.xAccel, this.yAccel, this.zAccel);
        }
    }

    public void onResume() {
        if (this.enabled) {
            startListening();
        }
    }

    public void onPause() {
        if (this.enabled) {
            stopListening();
        }
    }

    public void onDelete() {
        if (this.enabled) {
            stopListening();
        }
    }
}
