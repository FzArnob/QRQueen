package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.Options;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.NxtMailbox;
import com.google.appinventor.components.common.NxtMotorMode;
import com.google.appinventor.components.common.NxtMotorPort;
import com.google.appinventor.components.common.NxtRegulationMode;
import com.google.appinventor.components.common.NxtRunState;
import com.google.appinventor.components.common.NxtSensorMode;
import com.google.appinventor.components.common.NxtSensorPort;
import com.google.appinventor.components.common.NxtSensorType;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.NXT, description = "A component that provides a low-level interface to a LEGO MINDSTORMS NXT robot, with functions to send NXT Direct Commands.", iconName = "images/legoMindstormsNxt.png", nonVisible = true, version = 2)
public class NxtDirectCommands extends LegoMindstormsNxtBase {
    public NxtDirectCommands(ComponentContainer componentContainer) {
        super(componentContainer, "NxtDirectCommands");
    }

    @SimpleFunction(description = "Start execution of a previously downloaded program on the robot.")
    public void StartProgram(String str) {
        if (checkBluetooth("StartProgram")) {
            if (str.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, "StartProgram", ErrorMessages.ERROR_NXT_INVALID_PROGRAM_NAME, new Object[0]);
                return;
            }
            if (str.indexOf(".") == -1) {
                str = str + ".rxe";
            }
            byte[] bArr = new byte[22];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 0;
            copyStringValueToBytes(str, bArr, 2, 19);
            sendCommand("StartProgram", bArr);
        }
    }

    @SimpleFunction(description = "Stop execution of the currently running program on the robot.")
    public void StopProgram() {
        if (checkBluetooth("StopProgram")) {
            sendCommand("StopProgram", new byte[]{Byte.MIN_VALUE, 1});
        }
    }

    @SimpleFunction(description = "Play a sound file on the robot.")
    public void PlaySoundFile(String str) {
        if (checkBluetooth("PlaySoundFile")) {
            if (str.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, "PlaySoundFile", ErrorMessages.ERROR_NXT_INVALID_FILE_NAME, new Object[0]);
                return;
            }
            if (str.indexOf(".") == -1) {
                str = str + ".rso";
            }
            byte[] bArr = new byte[23];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 2;
            copyBooleanValueToBytes(false, bArr, 2);
            copyStringValueToBytes(str, bArr, 3, 19);
            sendCommand("PlaySoundFile", bArr);
        }
    }

    @SimpleFunction(description = "Make the robot play a tone.")
    public void PlayTone(int i, int i2) {
        if (checkBluetooth("PlayTone")) {
            if (i < 200) {
                String str = this.logTag;
                Log.w(str, "frequencyHz " + i + " is invalid, using 200.");
                i = 200;
            }
            if (i > 14000) {
                String str2 = this.logTag;
                Log.w(str2, "frequencyHz " + i + " is invalid, using 14000.");
                i = 14000;
            }
            byte[] bArr = new byte[6];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 3;
            copyUWORDValueToBytes(i, bArr, 2);
            copyUWORDValueToBytes(i2, bArr, 4);
            sendCommand("PlayTone", bArr);
        }
    }

    @SimpleFunction(description = "Sets the output state of a motor on the robot.")
    public void SetOutputState(@Options(NxtMotorPort.class) String str, int i, @Options(NxtMotorMode.class) int i2, @Options(NxtRegulationMode.class) int i3, int i4, @Options(NxtRunState.class) int i5, long j) {
        if (checkBluetooth("SetOutputState")) {
            NxtMotorPort fromUnderlyingValue = NxtMotorPort.fromUnderlyingValue(str);
            if (fromUnderlyingValue == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetOutputState", ErrorMessages.ERROR_NXT_INVALID_MOTOR_PORT, str);
                return;
            }
            NxtMotorMode fromUnderlyingValue2 = NxtMotorMode.fromUnderlyingValue(Integer.valueOf(i2));
            if (fromUnderlyingValue2 == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetOutputState", ErrorMessages.ERROR_NXT_INVALID_MOTOR_MODE, Integer.valueOf(i2));
                return;
            }
            NxtRegulationMode fromUnderlyingValue3 = NxtRegulationMode.fromUnderlyingValue(Integer.valueOf(i3));
            if (fromUnderlyingValue3 == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetOutputState", ErrorMessages.ERROR_NXT_INVALID_REGULATION_MODE, fromUnderlyingValue3);
                return;
            }
            setOutputState("SetOutputState", fromUnderlyingValue, i, fromUnderlyingValue2, fromUnderlyingValue3, i4, NxtRunState.fromUnderlyingValue(Integer.valueOf(i5)), j);
        }
    }

    @SimpleFunction(description = "Configure an input sensor on the robot.")
    public void SetInputMode(@Options(NxtSensorPort.class) String str, @Options(NxtSensorType.class) int i, @Options(NxtSensorMode.class) int i2) {
        if (checkBluetooth("SetInputMode")) {
            NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
            if (fromUnderlyingValue == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetInputMode", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
                return;
            }
            NxtSensorType fromUnderlyingValue2 = NxtSensorType.fromUnderlyingValue(Integer.valueOf(i));
            if (fromUnderlyingValue2 == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetInputMode", ErrorMessages.ERROR_NXT_INVALID_SENSOR_TYPE, fromUnderlyingValue2);
                return;
            }
            NxtSensorMode fromUnderlyingValue3 = NxtSensorMode.fromUnderlyingValue(Integer.valueOf(i2));
            if (fromUnderlyingValue3 == null) {
                this.form.dispatchErrorOccurredEvent(this, "SetInputMode", ErrorMessages.ERROR_NXT_INVALID_SENSOR_MODE, fromUnderlyingValue3);
                return;
            }
            setInputMode("SetInputMode", fromUnderlyingValue, fromUnderlyingValue2, fromUnderlyingValue3);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00e5  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Reads the output state of a motor on the robot.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Number> GetOutputState(@com.google.appinventor.components.annotations.Options(com.google.appinventor.components.common.NxtMotorPort.class) java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "GetOutputState"
            boolean r1 = r5.checkBluetooth(r0)
            if (r1 != 0) goto L_0x000e
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            return r6
        L_0x000e:
            com.google.appinventor.components.common.NxtMotorPort r1 = com.google.appinventor.components.common.NxtMotorPort.fromUnderlyingValue(r6)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0027
            com.google.appinventor.components.runtime.Form r1 = r5.form
            r4 = 407(0x197, float:5.7E-43)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r3] = r6
            r1.dispatchErrorOccurredEvent(r5, r0, r4, r2)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            return r6
        L_0x0027:
            r6 = 3
            byte[] r6 = new byte[r6]
            r6[r3] = r3
            r3 = 6
            r6[r2] = r3
            java.lang.Integer r1 = r1.toInt()
            int r1 = r1.intValue()
            r4 = 2
            r5.copyUBYTEValueToBytes(r1, r6, r4)
            byte[] r1 = r5.sendCommandAndReceiveReturnPackage(r0, r6)
            byte r6 = r6[r2]
            boolean r6 = r5.evaluateStatus(r0, r1, r6)
            if (r6 == 0) goto L_0x006c
            int r6 = r1.length
            r2 = 25
            if (r6 != r2) goto L_0x004d
            goto L_0x006d
        L_0x004d:
            java.lang.String r6 = r5.logTag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r0 = ": unexpected return package length "
            r2.append(r0)
            int r0 = r1.length
            r2.append(r0)
            java.lang.String r0 = " (expected 25)"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            android.util.Log.w(r6, r0)
        L_0x006c:
            r1 = 0
        L_0x006d:
            if (r1 == 0) goto L_0x00e5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r0 = 4
            int r0 = r5.getSBYTEValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 5
            int r0 = r5.getUBYTEValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            int r0 = r5.getUBYTEValueFromBytes(r1, r3)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 7
            int r0 = r5.getSBYTEValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 8
            int r0 = r5.getUBYTEValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 9
            long r2 = r5.getULONGValueFromBytes(r1, r0)
            java.lang.Long r0 = java.lang.Long.valueOf(r2)
            r6.add(r0)
            r0 = 13
            int r0 = r5.getSLONGValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 17
            int r0 = r5.getSLONGValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            r0 = 21
            int r0 = r5.getSLONGValueFromBytes(r1, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6.add(r0)
            return r6
        L_0x00e5:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NxtDirectCommands.GetOutputState(java.lang.String):java.util.List");
    }

    @SimpleFunction(description = "Reads the values of an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public List<Object> GetInputValues(@Options(NxtSensorPort.class) String str) {
        if (!checkBluetooth("GetInputValues")) {
            return new ArrayList();
        }
        NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            this.form.dispatchErrorOccurredEvent(this, "GetInputValues", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
            return new ArrayList();
        }
        byte[] inputValues = getInputValues("GetInputValues", fromUnderlyingValue);
        if (inputValues == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Boolean.valueOf(getBooleanValueFromBytes(inputValues, 4)));
        arrayList.add(Boolean.valueOf(getBooleanValueFromBytes(inputValues, 5)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(inputValues, 6)));
        arrayList.add(Integer.valueOf(getUBYTEValueFromBytes(inputValues, 7)));
        arrayList.add(Integer.valueOf(getUWORDValueFromBytes(inputValues, 8)));
        arrayList.add(Integer.valueOf(getUWORDValueFromBytes(inputValues, 10)));
        arrayList.add(Integer.valueOf(getSWORDValueFromBytes(inputValues, 12)));
        arrayList.add(Integer.valueOf(getSWORDValueFromBytes(inputValues, 14)));
        return arrayList;
    }

    @SimpleFunction(description = "Reset the scaled value of an input sensor on the robot.")
    public void ResetInputScaledValue(@Options(NxtSensorPort.class) String str) {
        if (checkBluetooth("ResetInputScaledValue")) {
            NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
            if (fromUnderlyingValue == null) {
                this.form.dispatchErrorOccurredEvent(this, "ResetInputScaledValue", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
                return;
            }
            resetInputScaledValue("ResetInputScaledValue", fromUnderlyingValue);
            byte[] bArr = new byte[3];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 8;
            copyUBYTEValueToBytes(fromUnderlyingValue.toInt().intValue(), bArr, 2);
            sendCommand("ResetInputScaledValue", bArr);
        }
    }

    @SimpleFunction(description = "Reset motor position.")
    public void ResetMotorPosition(@Options(NxtMotorPort.class) String str, boolean z) {
        if (checkBluetooth("ResetMotorPosition")) {
            NxtMotorPort fromUnderlyingValue = NxtMotorPort.fromUnderlyingValue(str);
            if (fromUnderlyingValue == null) {
                this.form.dispatchErrorOccurredEvent(this, "ResetMotorPosition", ErrorMessages.ERROR_NXT_INVALID_MOTOR_PORT, str);
                return;
            }
            byte[] bArr = new byte[4];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 10;
            copyUBYTEValueToBytes(fromUnderlyingValue.toInt().intValue(), bArr, 2);
            copyBooleanValueToBytes(z, bArr, 3);
            sendCommand("ResetMotorPosition", bArr);
        }
    }

    @SimpleFunction(description = "Get the battery level for the robot. Returns the voltage in millivolts.")
    public int GetBatteryLevel() {
        if (!checkBluetooth("GetBatteryLevel")) {
            return 0;
        }
        byte[] bArr = {0, 11};
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("GetBatteryLevel", bArr);
        if (evaluateStatus("GetBatteryLevel", sendCommandAndReceiveReturnPackage, bArr[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 5) {
                return getUWORDValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str = this.logTag;
            Log.w(str, "GetBatteryLevel: unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 5)");
        }
        return 0;
    }

    @SimpleFunction(description = "Stop sound playback.")
    public void StopSoundPlayback() {
        if (checkBluetooth("StopSoundPlayback")) {
            sendCommand("StopSoundPlayback", new byte[]{Byte.MIN_VALUE, 12});
        }
    }

    @SimpleFunction(description = "Keep Alive. Returns the current sleep time limit in milliseconds.")
    public long KeepAlive() {
        if (!checkBluetooth("KeepAlive")) {
            return 0;
        }
        byte[] bArr = {0, 13};
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("KeepAlive", bArr);
        if (evaluateStatus("KeepAlive", sendCommandAndReceiveReturnPackage, bArr[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 7) {
                return getULONGValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str = this.logTag;
            Log.w(str, "KeepAlive: unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 7)");
        }
        return 0;
    }

    @SimpleFunction(description = "Returns the count of available bytes to read.")
    public int LsGetStatus(@Options(NxtSensorPort.class) String str) {
        if (!checkBluetooth("LsGetStatus")) {
            return 0;
        }
        NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
        if (fromUnderlyingValue != null) {
            return lsGetStatus("LsGetStatus", fromUnderlyingValue);
        }
        this.form.dispatchErrorOccurredEvent(this, "LsGetStatus", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
        return 0;
    }

    @SimpleFunction(description = "Writes low speed data to an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public void LsWrite(@Options(NxtSensorPort.class) String str, YailList yailList, int i) {
        if (checkBluetooth("LsWrite")) {
            NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
            if (fromUnderlyingValue == null) {
                this.form.dispatchErrorOccurredEvent(this, "LsWrite", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
            } else if (yailList.size() > 16) {
                this.form.dispatchErrorOccurredEvent(this, "LsWrite", ErrorMessages.ERROR_NXT_DATA_TOO_LARGE, new Object[0]);
            } else {
                Object[] array = yailList.toArray();
                byte[] bArr = new byte[array.length];
                int i2 = 0;
                while (i2 < array.length) {
                    try {
                        int intValue = Integer.decode(array[i2].toString()).intValue();
                        bArr[i2] = (byte) intValue;
                        int i3 = intValue >> 8;
                        if (i3 == 0 || i3 == -1) {
                            i2++;
                        } else {
                            this.form.dispatchErrorOccurredEvent(this, "LsWrite", ErrorMessages.ERROR_NXT_COULD_NOT_FIT_ELEMENT_IN_BYTE, Integer.valueOf(i2 + 1));
                            return;
                        }
                    } catch (NumberFormatException unused) {
                        this.form.dispatchErrorOccurredEvent(this, "LsWrite", ErrorMessages.ERROR_NXT_COULD_NOT_DECODE_ELEMENT, Integer.valueOf(i2 + 1));
                        return;
                    }
                }
                lsWrite("LsWrite", fromUnderlyingValue, bArr, i);
            }
        }
    }

    @SimpleFunction(description = "Reads unsigned low speed data from an input sensor on the robot. Assumes sensor type has been configured via SetInputMode.")
    public List<Integer> LsRead(@Options(NxtSensorPort.class) String str) {
        if (!checkBluetooth("LsRead")) {
            return new ArrayList();
        }
        NxtSensorPort fromUnderlyingValue = NxtSensorPort.fromUnderlyingValue(str);
        if (fromUnderlyingValue == null) {
            this.form.dispatchErrorOccurredEvent(this, "LsRead", ErrorMessages.ERROR_NXT_INVALID_SENSOR_PORT, str);
            return new ArrayList();
        }
        byte[] lsRead = lsRead("LsRead", fromUnderlyingValue);
        if (lsRead == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        int uBYTEValueFromBytes = getUBYTEValueFromBytes(lsRead, 3);
        for (int i = 0; i < uBYTEValueFromBytes; i++) {
            arrayList.add(Integer.valueOf(lsRead[i + 4] & Ev3Constants.Opcode.TST));
        }
        return arrayList;
    }

    @SimpleFunction(description = "Get the name of currently running program on the robot.")
    public String GetCurrentProgramName() {
        if (!checkBluetooth("GetCurrentProgramName")) {
            return "";
        }
        byte[] bArr = {0, 17};
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("GetCurrentProgramName", bArr);
        int status = getStatus("GetCurrentProgramName", sendCommandAndReceiveReturnPackage, bArr[1]);
        if (status == 0) {
            return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
        }
        if (status == 236) {
            return "";
        }
        evaluateStatus("GetCurrentProgramName", sendCommandAndReceiveReturnPackage, bArr[1]);
        return "";
    }

    @SimpleFunction(description = "Read a message from a mailbox (1-10) on the robot.")
    public String MessageRead(@Options(NxtMailbox.class) int i) {
        NxtMailbox fromUnderlyingValue = NxtMailbox.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue != null) {
            return MessageReadAbstract(fromUnderlyingValue);
        }
        this.form.dispatchErrorOccurredEvent(this, "MessageRead", ErrorMessages.ERROR_NXT_INVALID_MAILBOX, Integer.valueOf(i));
        return "";
    }

    public String MessageReadAbstract(NxtMailbox nxtMailbox) {
        int intValue = nxtMailbox.toInt().intValue();
        if (!checkBluetooth("MessageRead")) {
            return "";
        }
        byte[] bArr = new byte[5];
        bArr[0] = 0;
        bArr[1] = 19;
        copyUBYTEValueToBytes(0, bArr, 2);
        copyUBYTEValueToBytes(intValue, bArr, 3);
        copyBooleanValueToBytes(true, bArr, 4);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("MessageRead", bArr);
        if (evaluateStatus("MessageRead", sendCommandAndReceiveReturnPackage, bArr[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 64) {
                int uBYTEValueFromBytes = getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
                if (uBYTEValueFromBytes != intValue) {
                    String str = this.logTag;
                    Log.w(str, "MessageRead: unexpected return mailbox: Box" + uBYTEValueFromBytes + " (expected " + intValue + ")");
                }
                return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 5, getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 4) - 1);
            }
            String str2 = this.logTag;
            Log.w(str2, "MessageRead: unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 64)");
        }
        return "";
    }

    @SimpleFunction(description = "Write a message to a mailbox (1-10) on the robot.")
    public void MessageWrite(@Options(NxtMailbox.class) int i, String str) {
        NxtMailbox fromUnderlyingValue = NxtMailbox.fromUnderlyingValue(Integer.valueOf(i));
        if (fromUnderlyingValue == null) {
            this.form.dispatchErrorOccurredEvent(this, "MessageWrite", ErrorMessages.ERROR_NXT_INVALID_MAILBOX, Integer.valueOf(i));
            return;
        }
        MessageWriteAbstract(fromUnderlyingValue, str);
    }

    public void MessageWriteAbstract(NxtMailbox nxtMailbox, String str) {
        if (checkBluetooth("MessageWrite")) {
            int length = str.length();
            if (length > 58) {
                this.form.dispatchErrorOccurredEvent(this, "MessageWrite", ErrorMessages.ERROR_NXT_MESSAGE_TOO_LONG, new Object[0]);
                return;
            }
            byte[] bArr = new byte[(length + 4 + 1)];
            bArr[0] = Byte.MIN_VALUE;
            bArr[1] = 9;
            copyUBYTEValueToBytes(nxtMailbox.toInt().intValue(), bArr, 2);
            copyUBYTEValueToBytes(length + 1, bArr, 3);
            copyStringValueToBytes(str, bArr, 4, length);
            sendCommand("MessageWrite", bArr);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f0 A[SYNTHETIC, Splitter:B:40:0x00f0] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f7  */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Download a file to the robot.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void DownloadFile(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r20
            java.lang.String r2 = "DownloadFile"
            boolean r3 = r1.checkBluetooth(r2)
            if (r3 != 0) goto L_0x000d
            return
        L_0x000d:
            int r3 = r19.length()
            r4 = 0
            if (r3 != 0) goto L_0x001e
            com.google.appinventor.components.runtime.Form r0 = r1.form
            r3 = 414(0x19e, float:5.8E-43)
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r0.dispatchErrorOccurredEvent(r1, r2, r3, r4)
            return
        L_0x001e:
            int r3 = r20.length()
            if (r3 != 0) goto L_0x002e
            com.google.appinventor.components.runtime.Form r0 = r1.form
            r3 = 415(0x19f, float:5.82E-43)
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r0.dispatchErrorOccurredEvent(r1, r2, r3, r4)
            return
        L_0x002e:
            r3 = 1
            com.google.appinventor.components.runtime.Form r5 = r1.form     // Catch:{ IOException -> 0x01d7 }
            r6 = r19
            java.io.File r5 = com.google.appinventor.components.runtime.util.MediaUtil.copyMediaToTempFile(r5, r6)     // Catch:{ IOException -> 0x01d7 }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x01cf }
            com.google.appinventor.components.runtime.Form r7 = r1.form     // Catch:{ all -> 0x01cf }
            java.io.FileInputStream r7 = com.google.appinventor.components.runtime.util.FileUtil.openFile((com.google.appinventor.components.runtime.Form) r7, (java.io.File) r5)     // Catch:{ all -> 0x01cf }
            r8 = 1024(0x400, float:1.435E-42)
            r6.<init>(r7, r8)     // Catch:{ all -> 0x01cf }
            long r7 = r5.length()     // Catch:{ all -> 0x01c7 }
            java.lang.String r9 = ".rxe"
            boolean r9 = r0.endsWith(r9)     // Catch:{ all -> 0x01c7 }
            java.lang.String r10 = " (expected 4)"
            r13 = 19
            r14 = 26
            java.lang.String r15 = ": unexpected return package length "
            r11 = 2
            r12 = 3
            if (r9 != 0) goto L_0x00a8
            java.lang.String r9 = ".ric"
            boolean r9 = r0.endsWith(r9)     // Catch:{ all -> 0x01c7 }
            if (r9 == 0) goto L_0x0063
            goto L_0x00a8
        L_0x0063:
            byte[] r9 = new byte[r14]     // Catch:{ all -> 0x01c7 }
            r9[r4] = r3     // Catch:{ all -> 0x01c7 }
            r14 = -127(0xffffffffffffff81, float:NaN)
            r9[r3] = r14     // Catch:{ all -> 0x01c7 }
            r1.copyStringValueToBytes(r0, r9, r11, r13)     // Catch:{ all -> 0x01c7 }
            r0 = 22
            r1.copyULONGValueToBytes(r7, r9, r0)     // Catch:{ all -> 0x01c7 }
            byte[] r0 = r1.sendCommandAndReceiveReturnPackage(r2, r9)     // Catch:{ all -> 0x01c7 }
            byte r9 = r9[r3]     // Catch:{ all -> 0x01c7 }
            boolean r9 = r1.evaluateStatus(r2, r0, r9)     // Catch:{ all -> 0x01c7 }
            if (r9 == 0) goto L_0x00ed
            int r9 = r0.length     // Catch:{ all -> 0x01c7 }
            r13 = 4
            if (r9 != r13) goto L_0x008c
            int r0 = r1.getUBYTEValueFromBytes(r0, r12)     // Catch:{ all -> 0x01c7 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x01c7 }
            goto L_0x00d0
        L_0x008c:
            java.lang.String r9 = r1.logTag     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r13.<init>()     // Catch:{ all -> 0x01c7 }
            r13.append(r2)     // Catch:{ all -> 0x01c7 }
            r13.append(r15)     // Catch:{ all -> 0x01c7 }
            int r0 = r0.length     // Catch:{ all -> 0x01c7 }
            r13.append(r0)     // Catch:{ all -> 0x01c7 }
            r13.append(r10)     // Catch:{ all -> 0x01c7 }
            java.lang.String r0 = r13.toString()     // Catch:{ all -> 0x01c7 }
            android.util.Log.w(r9, r0)     // Catch:{ all -> 0x01c7 }
            goto L_0x00ed
        L_0x00a8:
            byte[] r9 = new byte[r14]     // Catch:{ all -> 0x01c7 }
            r9[r4] = r3     // Catch:{ all -> 0x01c7 }
            r14 = -119(0xffffffffffffff89, float:NaN)
            r9[r3] = r14     // Catch:{ all -> 0x01c7 }
            r1.copyStringValueToBytes(r0, r9, r11, r13)     // Catch:{ all -> 0x01c7 }
            r0 = 22
            r1.copyULONGValueToBytes(r7, r9, r0)     // Catch:{ all -> 0x01c7 }
            byte[] r0 = r1.sendCommandAndReceiveReturnPackage(r2, r9)     // Catch:{ all -> 0x01c7 }
            byte r9 = r9[r3]     // Catch:{ all -> 0x01c7 }
            boolean r9 = r1.evaluateStatus(r2, r0, r9)     // Catch:{ all -> 0x01c7 }
            if (r9 == 0) goto L_0x00ed
            int r9 = r0.length     // Catch:{ all -> 0x01c7 }
            r13 = 4
            if (r9 != r13) goto L_0x00d2
            int r0 = r1.getUBYTEValueFromBytes(r0, r12)     // Catch:{ all -> 0x01c7 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x01c7 }
        L_0x00d0:
            r9 = r0
            goto L_0x00ee
        L_0x00d2:
            java.lang.String r9 = r1.logTag     // Catch:{ all -> 0x01c7 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c7 }
            r13.<init>()     // Catch:{ all -> 0x01c7 }
            r13.append(r2)     // Catch:{ all -> 0x01c7 }
            r13.append(r15)     // Catch:{ all -> 0x01c7 }
            int r0 = r0.length     // Catch:{ all -> 0x01c7 }
            r13.append(r0)     // Catch:{ all -> 0x01c7 }
            r13.append(r10)     // Catch:{ all -> 0x01c7 }
            java.lang.String r0 = r13.toString()     // Catch:{ all -> 0x01c7 }
            android.util.Log.w(r9, r0)     // Catch:{ all -> 0x01c7 }
        L_0x00ed:
            r9 = 0
        L_0x00ee:
            if (r9 != 0) goto L_0x00f7
            r6.close()     // Catch:{ all -> 0x01cf }
            r5.delete()     // Catch:{ IOException -> 0x01d7 }
            return
        L_0x00f7:
            r0 = 32
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x01bb }
            r13 = 0
        L_0x00fd:
            int r16 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r16 >= 0) goto L_0x01ac
            r11 = 32
            long r0 = r7 - r13
            long r0 = java.lang.Math.min(r11, r0)     // Catch:{ all -> 0x01a8 }
            int r1 = (int) r0     // Catch:{ all -> 0x01a8 }
            r6.read(r10, r4, r1)     // Catch:{ all -> 0x01a8 }
            int r0 = r9.intValue()     // Catch:{ all -> 0x01a8 }
            r11 = 32
            if (r1 > r11) goto L_0x019c
            int r12 = r1 + 3
            byte[] r12 = new byte[r12]     // Catch:{ all -> 0x01a8 }
            r12[r4] = r3     // Catch:{ all -> 0x01a8 }
            r17 = -125(0xffffffffffffff83, float:NaN)
            r12[r3] = r17     // Catch:{ all -> 0x01a8 }
            r3 = 2
            r11 = r18
            r11.copyUBYTEValueToBytes(r0, r12, r3)     // Catch:{ all -> 0x01a6 }
            r0 = 3
            java.lang.System.arraycopy(r10, r4, r12, r0, r1)     // Catch:{ all -> 0x01a6 }
            byte[] r0 = r11.sendCommandAndReceiveReturnPackage(r2, r12)     // Catch:{ all -> 0x01a6 }
            r17 = 1
            byte r12 = r12[r17]     // Catch:{ all -> 0x01a6 }
            boolean r12 = r11.evaluateStatus(r2, r0, r12)     // Catch:{ all -> 0x01a6 }
            if (r12 == 0) goto L_0x0190
            int r12 = r0.length     // Catch:{ all -> 0x01a6 }
            r3 = 6
            if (r12 != r3) goto L_0x0171
            r3 = 4
            int r0 = r11.getUWORDValueFromBytes(r0, r3)     // Catch:{ all -> 0x01a6 }
            if (r0 != r1) goto L_0x0143
            goto L_0x0192
        L_0x0143:
            java.lang.String r3 = r11.logTag     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a6 }
            r7.<init>()     // Catch:{ all -> 0x01a6 }
            r7.append(r2)     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = ": only "
            r7.append(r8)     // Catch:{ all -> 0x01a6 }
            r7.append(r0)     // Catch:{ all -> 0x01a6 }
            java.lang.String r0 = " bytes were written (expected "
            r7.append(r0)     // Catch:{ all -> 0x01a6 }
            r7.append(r1)     // Catch:{ all -> 0x01a6 }
            java.lang.String r0 = ")"
            r7.append(r0)     // Catch:{ all -> 0x01a6 }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x01a6 }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x01a6 }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01a6 }
            java.lang.String r1 = "Unable to write file on robot"
            r0.<init>(r1)     // Catch:{ all -> 0x01a6 }
            throw r0     // Catch:{ all -> 0x01a6 }
        L_0x0171:
            r3 = 4
            java.lang.String r1 = r11.logTag     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a6 }
            r12.<init>()     // Catch:{ all -> 0x01a6 }
            r12.append(r2)     // Catch:{ all -> 0x01a6 }
            r12.append(r15)     // Catch:{ all -> 0x01a6 }
            int r0 = r0.length     // Catch:{ all -> 0x01a6 }
            r12.append(r0)     // Catch:{ all -> 0x01a6 }
            java.lang.String r0 = " (expected 6)"
            r12.append(r0)     // Catch:{ all -> 0x01a6 }
            java.lang.String r0 = r12.toString()     // Catch:{ all -> 0x01a6 }
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x01a6 }
            goto L_0x0191
        L_0x0190:
            r3 = 4
        L_0x0191:
            r0 = 0
        L_0x0192:
            long r0 = (long) r0     // Catch:{ all -> 0x01a6 }
            long r13 = r13 + r0
            r1 = r11
            r0 = 32
            r3 = 1
            r11 = 2
            r12 = 3
            goto L_0x00fd
        L_0x019c:
            r11 = r18
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x01a6 }
            java.lang.String r1 = "length must be <= 32"
            r0.<init>(r1)     // Catch:{ all -> 0x01a6 }
            throw r0     // Catch:{ all -> 0x01a6 }
        L_0x01a6:
            r0 = move-exception
            goto L_0x01bd
        L_0x01a8:
            r0 = move-exception
            r11 = r18
            goto L_0x01bd
        L_0x01ac:
            r11 = r1
            int r0 = r9.intValue()     // Catch:{ all -> 0x01c5 }
            r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r2, r0)     // Catch:{ all -> 0x01c5 }
            r6.close()     // Catch:{ all -> 0x01cd }
            r5.delete()     // Catch:{ IOException -> 0x01d5 }
            return
        L_0x01bb:
            r0 = move-exception
            r11 = r1
        L_0x01bd:
            int r1 = r9.intValue()     // Catch:{ all -> 0x01c5 }
            r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r2, r1)     // Catch:{ all -> 0x01c5 }
            throw r0     // Catch:{ all -> 0x01c5 }
        L_0x01c5:
            r0 = move-exception
            goto L_0x01c9
        L_0x01c7:
            r0 = move-exception
            r11 = r1
        L_0x01c9:
            r6.close()     // Catch:{ all -> 0x01cd }
            throw r0     // Catch:{ all -> 0x01cd }
        L_0x01cd:
            r0 = move-exception
            goto L_0x01d1
        L_0x01cf:
            r0 = move-exception
            r11 = r1
        L_0x01d1:
            r5.delete()     // Catch:{ IOException -> 0x01d5 }
            throw r0     // Catch:{ IOException -> 0x01d5 }
        L_0x01d5:
            r0 = move-exception
            goto L_0x01d9
        L_0x01d7:
            r0 = move-exception
            r11 = r1
        L_0x01d9:
            com.google.appinventor.components.runtime.Form r1 = r11.form
            r3 = 416(0x1a0, float:5.83E-43)
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = r0.getMessage()
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r5[r4] = r0
            r1.dispatchErrorOccurredEvent(r11, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NxtDirectCommands.DownloadFile(java.lang.String, java.lang.String):void");
    }

    private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = 1;
        bArr[1] = Ev3Constants.Opcode.UI_DRAW;
        copyUBYTEValueToBytes(i, bArr, 2);
        evaluateStatus(str, sendCommandAndReceiveReturnPackage(str, bArr), bArr[1]);
    }

    @SimpleFunction(description = "Delete a file on the robot.")
    public void DeleteFile(String str) {
        if (checkBluetooth("DeleteFile")) {
            if (str.length() == 0) {
                this.form.dispatchErrorOccurredEvent(this, "DeleteFile", ErrorMessages.ERROR_NXT_INVALID_FILE_NAME, new Object[0]);
                return;
            }
            byte[] bArr = new byte[22];
            bArr[0] = 1;
            bArr[1] = Ev3Constants.Opcode.TIMER_WAIT;
            copyStringValueToBytes(str, bArr, 2, 19);
            evaluateStatus("DeleteFile", sendCommandAndReceiveReturnPackage("DeleteFile", bArr), bArr[1]);
        }
    }

    @SimpleFunction(description = "Returns a list containing the names of matching files found on the robot.")
    public List<String> ListFiles(String str) {
        if (!checkBluetooth("ListFiles")) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        if (str.length() == 0) {
            str = "*.*";
        }
        byte[] bArr = new byte[22];
        bArr[0] = 1;
        bArr[1] = Ev3Constants.Opcode.TIMER_READY;
        copyStringValueToBytes(str, bArr, 2, 19);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("ListFiles", bArr);
        int status = getStatus("ListFiles", sendCommandAndReceiveReturnPackage, bArr[1]);
        while (status == 0) {
            int uBYTEValueFromBytes = getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            arrayList.add(getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 4));
            byte[] bArr2 = new byte[3];
            bArr2[0] = 1;
            bArr2[1] = Ev3Constants.Opcode.TIMER_READ;
            copyUBYTEValueToBytes(uBYTEValueFromBytes, bArr2, 2);
            byte[] sendCommandAndReceiveReturnPackage2 = sendCommandAndReceiveReturnPackage("ListFiles", bArr2);
            byte[] bArr3 = sendCommandAndReceiveReturnPackage2;
            status = getStatus("ListFiles", sendCommandAndReceiveReturnPackage2, bArr2[1]);
            sendCommandAndReceiveReturnPackage = bArr3;
        }
        return arrayList;
    }

    @SimpleFunction(description = "Get the firmware and protocol version numbers for the robot as a list where the first element is the firmware version number and the second element is the protocol version number.")
    public List<String> GetFirmwareVersion() {
        if (!checkBluetooth("GetFirmwareVersion")) {
            return new ArrayList();
        }
        byte[] bArr = {1, Ev3Constants.Opcode.BP0};
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("GetFirmwareVersion", bArr);
        if (!evaluateStatus("GetFirmwareVersion", sendCommandAndReceiveReturnPackage, bArr[1])) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(sendCommandAndReceiveReturnPackage[6] + "." + sendCommandAndReceiveReturnPackage[5]);
        arrayList.add(sendCommandAndReceiveReturnPackage[4] + "." + sendCommandAndReceiveReturnPackage[3]);
        return arrayList;
    }

    @SimpleFunction(description = "Set the brick name of the robot.")
    public void SetBrickName(String str) {
        if (checkBluetooth("SetBrickName")) {
            byte[] bArr = new byte[18];
            bArr[0] = 1;
            bArr[1] = -104;
            copyStringValueToBytes(str, bArr, 2, 15);
            evaluateStatus("SetBrickName", sendCommandAndReceiveReturnPackage("SetBrickName", bArr), bArr[1]);
        }
    }

    @SimpleFunction(description = "Get the brick name of the robot.")
    public String GetBrickName() {
        if (!checkBluetooth("GetBrickName")) {
            return "";
        }
        byte[] bArr = {1, -101};
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage("GetBrickName", bArr);
        if (evaluateStatus("GetBrickName", sendCommandAndReceiveReturnPackage, bArr[1])) {
            return getStringValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
        }
        return "";
    }
}
