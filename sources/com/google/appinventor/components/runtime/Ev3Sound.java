package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides a high-level interface to sound functionalities on LEGO MINDSTORMS EV3 robot.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
public class Ev3Sound extends LegoMindstormsEv3Base {
    public Ev3Sound(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3Sound");
    }

    @SimpleFunction(description = "Make the robot play a tone.")
    public void PlayTone(int i, int i2, int i3) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (i < 0 || i > 100 || i2 < 250 || i2 > 10000 || i3 < 0 || i3 > 65535) {
            this.form.dispatchErrorOccurredEvent(this, methodName, ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, methodName);
            return;
        }
        sendCommand(methodName, Ev3BinaryParser.encodeDirectCommand((byte) -108, true, 0, 0, "cccc", (byte) 1, Byte.valueOf((byte) i), Short.valueOf((short) i2), Short.valueOf((short) i3)), true);
    }

    @SimpleFunction(description = "Stop any sound on the robot.")
    public void StopSound() {
        sendCommand(Thread.currentThread().getStackTrace()[1].getMethodName(), Ev3BinaryParser.encodeDirectCommand((byte) -108, false, 0, 0, "c", (byte) 0), false);
    }
}
