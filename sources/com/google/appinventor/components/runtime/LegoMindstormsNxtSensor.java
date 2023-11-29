package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.NxtSensorPort;
import com.google.appinventor.components.runtime.util.ErrorMessages;

@SimpleObject
public abstract class LegoMindstormsNxtSensor extends LegoMindstormsNxtBase {
    protected NxtSensorPort port;

    public abstract void SensorPort(String str);

    /* access modifiers changed from: protected */
    public abstract void initializeSensor(String str);

    static class a<T> {
        final T qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE;
        final boolean uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl;

        a(boolean z, T t) {
            this.uylNXPbffZHriucQ1SAwUkLE9dP8WxwaWe5GnEXLipwyDpy1sV8NkWFXe3foYSTl = z;
            this.qPeHJnCLP0dAOwDPeFIn82vcSIsCMh6KFFn3o4kyIe0RhQKOQXDeyY2LFwPu2GbE = t;
        }
    }

    protected LegoMindstormsNxtSensor(ComponentContainer componentContainer, String str) {
        super(componentContainer, str);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The sensor port that the sensor is connected to.", userVisible = false)
    public String SensorPort() {
        return this.port.toUnderlyingValue();
    }

    /* access modifiers changed from: protected */
    public final void setSensorPort(String str) {
        NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            this.form.dispatchErrorOccurredEvent(this, "SensorPort", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
            return;
        }
        this.port = fromUnderlyingValue;
        if (this.bluetooth != null && this.bluetooth.IsConnected()) {
            initializeSensor("SensorPort");
        }
    }

    public void afterConnect(BluetoothConnectionBase bluetoothConnectionBase) {
        initializeSensor("Connect");
    }
}
