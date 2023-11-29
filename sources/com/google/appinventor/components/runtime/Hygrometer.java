package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "A sensor component that can measure the relative ambient air humidity. Most Android devices do not have this sensor.", iconName = "images/hygrometer.png", nonVisible = true, version = 1)
public class Hygrometer extends SingleValueSensor {
    public Hygrometer(ComponentContainer componentContainer) {
        super(componentContainer.$form(), 12);
    }

    /* access modifiers changed from: protected */
    public void onValueChanged(float f) {
        HumidityChanged(f);
    }

    @SimpleEvent(description = "Called when a change is detected in the ambient air humidity (expressed as a percentage).")
    public void HumidityChanged(float f) {
        EventDispatcher.dispatchEvent(this, "HumidityChanged", Float.valueOf(f));
    }

    @SimpleProperty(description = "The relative ambient humidity as a percentage, if the sensor is available and enabled.")
    public float Humidity() {
        return getValue();
    }
}
