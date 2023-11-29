package com.google.appinventor.components.runtime;

import android.os.Handler;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.Ev3Constants;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EV3, description = "A component that provides both high- and low-level interfaces to a LEGO MINDSTORMS EV3 robot, with functions that can control the motors.", iconName = "images/legoMindstormsEv3.png", nonVisible = true, version = 1)
public class Ev3Motors extends LegoMindstormsEv3Base {
    private boolean I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = true;
    private boolean PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5 = false;
    private boolean PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = true;
    private boolean W6T0KLh0HOscnxSkBKrzTm675pNrsPjnoOd2dFOC45d2E2zbErFUDn8t6kBqwFa = false;
    /* access modifiers changed from: private */
    public int bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = 0;
    private double hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = 4.32d;
    private Handler hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = new Handler();

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Runnable f83hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = false;
    private int zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = 1;

    private static int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(int i) {
        if (i < -100) {
            return -100;
        }
        if (i > 100) {
            return 100;
        }
        return i;
    }

    static /* synthetic */ boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        return false;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        return i != 0 && (i & (~((i + -1) ^ i))) == 0;
    }

    public Ev3Motors(ComponentContainer componentContainer) {
        super(componentContainer, "Ev3Motors");
        AnonymousClass1 r3 = new Runnable() {
            public final void run() {
                if (Ev3Motors.this.bluetooth != null && Ev3Motors.this.bluetooth.IsConnected()) {
                    Ev3Motors ev3Motors = Ev3Motors.this;
                    int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("", Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(ev3Motors));
                    Ev3Motors.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != Ev3Motors.this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b && Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Ev3Motors.this)) {
                        Ev3Motors.this.TachoCountChanged(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                    }
                    int unused = Ev3Motors.this.bVeTzqSexGvEaauwiYAVDIi0rEKwP38hsr4zIk14QJ70YrYaNw4t0FSa76teZ34b = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                }
                Ev3Motors.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Ev3Motors.this).postDelayed(this, 50);
            }
        };
        this.f83hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.post(r3);
        MotorPorts("ABC");
        StopBeforeDisconnect(true);
        EnableSpeedRegulation(true);
        ReverseDirection(false);
        WheelDiameter(4.32d);
        TachoCountChangedEventEnabled(false);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The motor ports that the motors are connected to. The ports are specified by a sequence of port letters.", userVisible = false)
    public String MotorPorts() {
        return bitFieldToMotorPortLetters(this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5);
    }

    @DesignerProperty(defaultValue = "ABC", editorType = "string")
    @SimpleProperty
    public void MotorPorts(String str) {
        try {
            this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = motorPortLettersToBitField(str);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "MotorPorts", ErrorMessages.ERROR_EV3_ILLEGAL_MOTOR_PORT, str);
        }
    }

    @DesignerProperty(defaultValue = "4.32", editorType = "float")
    @SimpleProperty
    public void WheelDiameter(double d) {
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = d;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The diameter of the wheels attached on the motors in centimeters.", userVisible = false)
    public double WheelDiameter() {
        return this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void ReverseDirection(boolean z) {
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("ReverseDirection", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, z ? -1 : 1);
            this.PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5 = z;
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "ReverseDirection", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ReverseDirection");
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "It specifies if the direction of the motors is reversed.")
    public boolean ReverseDirection() {
        return this.PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void EnableSpeedRegulation(boolean z) {
        this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The robot adjusts the power to maintain the speed if speed regulation is enabled.")
    public boolean EnableSpeedRegulation() {
        return this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to stop the motor before disconnecting.")
    public boolean StopBeforeDisconnect() {
        return this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void StopBeforeDisconnect(boolean z) {
        this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the TachoCountChanged event should fire when the angle is changed.")
    public boolean TachoCountChangedEventEnabled() {
        return this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void TachoCountChangedEventEnabled(boolean z) {
        this.lAud5RKvmdA6itPf25e72aHL9NdNsPWhE0ialf61Le8xYV1HI3NTt1XJfRo1B6Y = z;
    }

    @SimpleFunction(description = "Start to rotate the motors.")
    public void RotateIndefinitely(int i) {
        try {
            if (this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR) {
                int i2 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
                if (i2 < 0 || i2 > 15) {
                    throw new IllegalArgumentException();
                }
                sendCommand("RotateIndefinitely", Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POWER, false, 0, 0, "ccc", (byte) 0, Byte.valueOf((byte) i2), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i))), false);
            } else {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateIndefinitely", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i);
            }
            int i3 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i3 < 0 || i3 > 15) {
                throw new IllegalArgumentException();
            }
            sendCommand("RotateIndefinitely", Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_START, false, 0, 0, "cc", (byte) 0, Byte.valueOf((byte) i3)), false);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateIndefinitely", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateIndefinitely");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a number of tacho counts.")
    public void RotateInTachoCounts(int i, int i2, boolean z) {
        try {
            if (this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateInTachoCounts", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
                return;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("RotateInTachoCounts", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInTachoCounts", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInTachoCounts");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a period of time.")
    public void RotateInDuration(int i, int i2, boolean z) {
        try {
            if (this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou("RotateInDuration", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
                return;
            }
            int i3 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i3 < 0 || i3 > 15 || i2 < 0) {
                throw new IllegalArgumentException();
            }
            int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i);
            Object[] objArr = new Object[7];
            objArr[0] = (byte) 0;
            objArr[1] = Byte.valueOf((byte) i3);
            objArr[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            objArr[3] = 0;
            objArr[4] = Integer.valueOf(i2);
            objArr[5] = 0;
            objArr[6] = Byte.valueOf((byte) (z ? 1 : 0));
            sendCommand("RotateInDuration", Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_POWER, false, 0, 0, "ccccccc", objArr), false);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors in a distance.")
    public void RotateInDistance(int i, double d, boolean z) {
        int i2 = (int) (((d * 360.0d) / this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) / 3.141592653589793d);
        try {
            if (this.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateInDistance", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
                return;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("RotateInDistance", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateInDistance", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateInDistance");
        }
    }

    @SimpleFunction(description = "Start to rotate the motors at the same speed.")
    public void RotateSyncIndefinitely(int i, int i2) {
        try {
            int i3 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i3 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i3)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateSyncIndefinitely", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i);
                return;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("RotateSyncIndefinitely", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, 0, true);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncIndefinitely", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncIndefinitely");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed for a distance in cm.")
    public void RotateSyncInDistance(int i, int i2, int i3, boolean z) {
        int i4 = (int) (((((double) i2) * 360.0d) / this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO) / 3.141592653589793d);
        try {
            int i5 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i5 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i5)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateSyncInDuration", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i4, z);
                return;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("RotateSyncInDuration", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i3, i4, z);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a period of time.")
    public void RotateSyncInDuration(int i, int i2, int i3, boolean z) {
        try {
            int i4 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i4 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i4)) {
                wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou("RotateSyncInDuration", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
                return;
            }
            int i5 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i5 < 0 || i5 > 15 || i2 < 0) {
                throw new IllegalArgumentException();
            }
            int B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i);
            Object[] objArr = new Object[6];
            objArr[0] = (byte) 0;
            objArr[1] = Byte.valueOf((byte) i5);
            objArr[2] = Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            objArr[3] = Short.valueOf((short) i3);
            objArr[4] = Integer.valueOf(i2);
            objArr[5] = Byte.valueOf((byte) (z ? 1 : 0));
            sendCommand("RotateSyncInDuration", Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SYNC, false, 0, 0, "cccccc", objArr), false);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInDuration", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInDuration");
        }
    }

    @SimpleFunction(description = "Rotate the motors at the same speed in a number of tacho counts.")
    public void RotateSyncInTachoCounts(int i, int i2, int i3, boolean z) {
        try {
            int i4 = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i4 == 0) {
                return;
            }
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i4)) {
                B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T("RotateSyncInTachoCounts", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i2, z);
                return;
            }
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("RotateSyncInTachoCounts", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, i, i3, i2, z);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "RotateSyncInTachoCounts", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "RotateSyncInTachoCounts");
        }
    }

    @SimpleFunction(description = "Stop the motors of the robot.")
    public void Stop(boolean z) {
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("Stop", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, z);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "Stop", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "Stop");
        }
    }

    @SimpleFunction(description = "Toggle the direction of motors.")
    public void ToggleDirection() {
        try {
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("ToggleDirection", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, 0);
            this.PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5 = !this.PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5;
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "ToggleDirection", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ToggleDirection");
        }
    }

    @SimpleFunction(description = "Set the current tacho count to zero.")
    public void ResetTachoCount() {
        try {
            int i = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;
            if (i < 0 || i > 15) {
                throw new IllegalArgumentException();
            }
            sendCommand("ResetTachoCount", Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_CLR_COUNT, false, 0, 0, "cc", (byte) 0, Byte.valueOf((byte) i)), false);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "ResetTachoCount", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "ResetTachoCount");
        }
    }

    @SimpleFunction(description = "Get the current tacho count.")
    public int GetTachoCount() {
        try {
            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("GetTachoCount", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5);
        } catch (IllegalArgumentException unused) {
            this.form.dispatchErrorOccurredEvent(this, "GetTachoCount", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "GetTachoCount");
            return 0;
        }
    }

    @SimpleEvent(description = "Called when the tacho count has changed.")
    public void TachoCountChanged(int i) {
        EventDispatcher.dispatchEvent(this, "TachoCountChanged", Integer.valueOf(i));
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, boolean z) {
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STOP, false, 0, 0, "ccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf(z ? (byte) 1 : 0)), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2, int i3, boolean z) {
        if (i < 0 || i > 15 || i3 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_POWER, false, 0, 0, "ccccccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2)), 0, Integer.valueOf(i3), 0, Byte.valueOf(z ? (byte) 1 : 0)), false);
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i, int i2, int i3, boolean z) {
        if (i < 0 || i > 15 || i3 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SPEED, false, 0, 0, "ccccccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2)), 0, Integer.valueOf(i3), 0, Byte.valueOf(z ? (byte) 1 : 0)), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2, int i3, int i4, boolean z) {
        if (i < 0 || i > 15 || i3 < -200 || i3 > 200) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_STEP_SYNC, false, 0, 0, "cccccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2)), Short.valueOf((short) i3), Integer.valueOf(i4), Byte.valueOf(z ? (byte) 1 : 0)), false);
    }

    private void wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str, int i, int i2, int i3, boolean z) {
        if (i < 0 || i > 15 || i3 < 0) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_TIME_SPEED, false, 0, 0, "ccccccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2)), 0, Integer.valueOf(i3), 0, Byte.valueOf(z ? (byte) 1 : 0)), false);
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i, int i2) {
        if (i < 0 || i > 15 || i2 < -1 || i2 > 1) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_POLARITY, false, 0, 0, "ccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) i2)), false);
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, int i, int i2) {
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException();
        }
        sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_SPEED, false, 0, 0, "ccc", (byte) 0, Byte.valueOf((byte) i), Byte.valueOf((byte) B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(i2))), false);
    }

    /* access modifiers changed from: private */
    public int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i) {
        int i2;
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException();
        }
        int i3 = ((i ^ (i - 1)) + 1) >>> 1;
        if (i3 == 1) {
            i2 = 0;
        } else if (i3 == 2) {
            i2 = 1;
        } else if (i3 == 4) {
            i2 = 2;
        } else if (i3 == 8) {
            i2 = 3;
        } else {
            throw new IllegalArgumentException();
        }
        byte[] sendCommand = sendCommand(str, Ev3BinaryParser.encodeDirectCommand(Ev3Constants.Opcode.OUTPUT_GET_COUNT, true, 4, 0, "ccg", (byte) 0, Byte.valueOf((byte) i2), (byte) 0), true);
        if (sendCommand != null && sendCommand.length == 5 && sendCommand[0] == 2) {
            return ((Integer) Ev3BinaryParser.unpack("xi", sendCommand)[0]).intValue();
        }
        return 0;
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase) {
        if (this.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q) {
            try {
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("beforeDisconnect", this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, true);
            } catch (IllegalArgumentException unused) {
                this.form.dispatchErrorOccurredEvent(this, "beforeDisconnect", ErrorMessages.ERROR_EV3_ILLEGAL_ARGUMENT, "beforeDisconnect");
            }
        }
    }
}
