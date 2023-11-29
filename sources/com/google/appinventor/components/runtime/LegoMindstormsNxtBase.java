package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.NxtMotorMode;
import com.google.appinventor.components.common.NxtMotorPort;
import com.google.appinventor.components.common.NxtRegulationMode;
import com.google.appinventor.components.common.NxtRunState;
import com.google.appinventor.components.common.NxtSensorMode;
import com.google.appinventor.components.common.NxtSensorPort;
import com.google.appinventor.components.common.NxtSensorType;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kawa.Telnet;

@SimpleObject
public class LegoMindstormsNxtBase extends AndroidNonvisibleComponent implements Component, Deleteable, GeoJSONUtil.PropertyApplication {
    private static final Map<Integer, String> Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    protected BluetoothClient bluetooth;
    protected final String logTag;

    public final void Initialize() {
    }

    public void afterConnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    static {
        HashMap hashMap = new HashMap();
        Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = hashMap;
        hashMap.put(32, "Pending communication transaction in progress");
        hashMap.put(64, "Specified mailbox queue is empty");
        hashMap.put(129, "No more handles");
        hashMap.put(130, "No space");
        hashMap.put(131, "No more files");
        hashMap.put(132, "End of file expected");
        hashMap.put(133, "End of file");
        hashMap.put(134, "Not a linear file");
        hashMap.put(135, "File not found");
        hashMap.put(136, "Handle already closed");
        hashMap.put(137, "No linear space");
        hashMap.put(138, "Undefined error");
        hashMap.put(139, "File is busy");
        hashMap.put(140, "No write buffers");
        hashMap.put(141, "Append not possible");
        hashMap.put(142, "File is full");
        hashMap.put(143, "File exists");
        hashMap.put(144, "Module not found");
        hashMap.put(145, "Out of boundary");
        hashMap.put(146, "Illegal file name");
        hashMap.put(147, "Illegal handle");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG), "Request failed (i.e. specified file not found)");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK), "Unknown command opcode");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY), "Insane packet");
        hashMap.put(Integer.valueOf(FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE), "Data contains out-of-range values");
        hashMap.put(221, "Communication bus error");
        hashMap.put(222, "No free memory in communication buffer");
        hashMap.put(223, "Specified channel/connection is not valid");
        hashMap.put(224, "Specified channel/connection not configured or busy");
        hashMap.put(236, "No active program");
        hashMap.put(237, "Illegal size specified");
        hashMap.put(238, "Illegal mailbox queue ID specified");
        hashMap.put(239, "Attempted to access invalid field of a structure");
        hashMap.put(240, "Bad input or output specified");
        hashMap.put(Integer.valueOf(Telnet.WILL), "Insufficient memory available");
        hashMap.put(255, "Bad arguments");
    }

    protected LegoMindstormsNxtBase(ComponentContainer componentContainer, String str) {
        super(componentContainer.$form());
        this.logTag = str;
    }

    protected LegoMindstormsNxtBase() {
        super((Form) null);
        this.logTag = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.", userVisible = false)
    public BluetoothClient BluetoothClient() {
        return this.bluetooth;
    }

    @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
    @SimpleProperty(userVisible = false)
    public void BluetoothClient(BluetoothClient bluetoothClient) {
        BluetoothClient bluetoothClient2 = this.bluetooth;
        if (bluetoothClient2 != null) {
            bluetoothClient2.removeBluetoothConnectionListener$70312fc3(this);
            this.bluetooth.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Component) this);
            this.bluetooth = null;
        }
        if (bluetoothClient != null) {
            this.bluetooth = bluetoothClient;
            bluetoothClient.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this, Collections.singleton(2052));
            this.bluetooth.addBluetoothConnectionListener$70312fc3(this);
            if (this.bluetooth.IsConnected()) {
                afterConnect(this.bluetooth);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void setOutputState(String str, int i, int i2, int i3, int i4, int i5, int i6, long j) {
        int sanitizePower = sanitizePower(i2);
        int sanitizeTurnRatio = sanitizeTurnRatio(i5);
        byte[] bArr = new byte[12];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 4;
        copyUBYTEValueToBytes(i, bArr, 2);
        copySBYTEValueToBytes(sanitizePower, bArr, 3);
        copyUBYTEValueToBytes(i3, bArr, 4);
        copyUBYTEValueToBytes(i4, bArr, 5);
        copySBYTEValueToBytes(sanitizeTurnRatio, bArr, 6);
        copyUBYTEValueToBytes(i6, bArr, 7);
        copyULONGValueToBytes(j, bArr, 8);
        sendCommand(str, bArr);
    }

    /* access modifiers changed from: protected */
    public final void setOutputState(String str, NxtMotorPort nxtMotorPort, int i, NxtMotorMode nxtMotorMode, NxtRegulationMode nxtRegulationMode, int i2, NxtRunState nxtRunState, long j) {
        setOutputState(str, nxtMotorPort.toInt().intValue(), i, nxtMotorMode.toUnderlyingValue().intValue(), nxtRegulationMode.toUnderlyingValue().intValue(), i2, nxtRunState.toUnderlyingValue().intValue(), j);
    }

    /* access modifiers changed from: protected */
    public final void setInputMode(String str, int i, int i2, int i3) {
        byte[] bArr = new byte[5];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 5;
        copyUBYTEValueToBytes(i, bArr, 2);
        copyUBYTEValueToBytes(i2, bArr, 3);
        copyUBYTEValueToBytes(i3, bArr, 4);
        sendCommand(str, bArr);
    }

    /* access modifiers changed from: protected */
    public final void setInputMode(String str, NxtSensorPort nxtSensorPort, NxtSensorType nxtSensorType, NxtSensorMode nxtSensorMode) {
        setInputMode(str, nxtSensorPort.toInt().intValue(), nxtSensorType.toUnderlyingValue().intValue(), nxtSensorMode.toUnderlyingValue().intValue());
    }

    /* access modifiers changed from: protected */
    public final byte[] getInputValues(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = 0;
        bArr[1] = 7;
        copyUBYTEValueToBytes(i, bArr, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr);
        if (!evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr[1])) {
            return null;
        }
        if (sendCommandAndReceiveReturnPackage.length == 16) {
            return sendCommandAndReceiveReturnPackage;
        }
        String str2 = this.logTag;
        Log.w(str2, str + ": unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 16)");
        return null;
    }

    /* access modifiers changed from: protected */
    public final byte[] getInputValues(String str, NxtSensorPort nxtSensorPort) {
        return getInputValues(str, nxtSensorPort.toInt().intValue());
    }

    /* access modifiers changed from: protected */
    public final void resetInputScaledValue(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = 8;
        copyUBYTEValueToBytes(i, bArr, 2);
        sendCommand(str, bArr);
    }

    /* access modifiers changed from: protected */
    public final void resetInputScaledValue(String str, NxtSensorPort nxtSensorPort) {
        resetInputScaledValue(str, nxtSensorPort.toInt().intValue());
    }

    /* access modifiers changed from: protected */
    public final int lsGetStatus(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = 0;
        bArr[1] = 14;
        copyUBYTEValueToBytes(i, bArr, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr);
        if (evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr[1])) {
            if (sendCommandAndReceiveReturnPackage.length == 4) {
                return getUBYTEValueFromBytes(sendCommandAndReceiveReturnPackage, 3);
            }
            String str2 = this.logTag;
            Log.w(str2, str + ": unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 4)");
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public final int lsGetStatus(String str, NxtSensorPort nxtSensorPort) {
        return lsGetStatus(str, nxtSensorPort.toInt().intValue());
    }

    /* access modifiers changed from: protected */
    public final void lsWrite(String str, int i, byte[] bArr, int i2) {
        if (bArr.length <= 16) {
            byte[] bArr2 = new byte[(bArr.length + 5)];
            bArr2[0] = 0;
            bArr2[1] = 15;
            copyUBYTEValueToBytes(i, bArr2, 2);
            copyUBYTEValueToBytes(bArr.length, bArr2, 3);
            copyUBYTEValueToBytes(i2, bArr2, 4);
            System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
            evaluateStatus(str, sendCommandAndReceiveReturnPackage(str, bArr2), bArr2[1]);
            return;
        }
        throw new IllegalArgumentException("length must be <= 16");
    }

    /* access modifiers changed from: protected */
    public final void lsWrite(String str, NxtSensorPort nxtSensorPort, byte[] bArr, int i) {
        lsWrite(str, nxtSensorPort.toInt().intValue(), bArr, i);
    }

    /* access modifiers changed from: protected */
    public final byte[] lsRead(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = 0;
        bArr[1] = 16;
        copyUBYTEValueToBytes(i, bArr, 2);
        byte[] sendCommandAndReceiveReturnPackage = sendCommandAndReceiveReturnPackage(str, bArr);
        if (!evaluateStatus(str, sendCommandAndReceiveReturnPackage, bArr[1])) {
            return null;
        }
        if (sendCommandAndReceiveReturnPackage.length == 20) {
            return sendCommandAndReceiveReturnPackage;
        }
        String str2 = this.logTag;
        Log.w(str2, str + ": unexpected return package length " + sendCommandAndReceiveReturnPackage.length + " (expected 20)");
        return null;
    }

    /* access modifiers changed from: protected */
    public final byte[] lsRead(String str, NxtSensorPort nxtSensorPort) {
        return lsRead(str, nxtSensorPort.toInt().intValue());
    }

    /* access modifiers changed from: protected */
    public final boolean checkBluetooth(String str) {
        BluetoothClient bluetoothClient = this.bluetooth;
        if (bluetoothClient == null) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_NXT_BLUETOOTH_NOT_SET, new Object[0]);
            return false;
        } else if (bluetoothClient.IsConnected()) {
            return true;
        } else {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_NXT_NOT_CONNECTED_TO_ROBOT, new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final byte[] sendCommandAndReceiveReturnPackage(String str, byte[] bArr) {
        sendCommand(str, bArr);
        byte[] read = this.bluetooth.read(str, 2);
        if (read.length == 2) {
            byte[] read2 = this.bluetooth.read(str, getUWORDValueFromBytes(read, 0));
            if (read2.length >= 3) {
                return read2;
            }
        }
        this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_NXT_INVALID_RETURN_PACKAGE, new Object[0]);
        return new byte[0];
    }

    /* access modifiers changed from: protected */
    public final void sendCommand(String str, byte[] bArr) {
        byte[] bArr2 = new byte[2];
        copyUWORDValueToBytes(bArr.length, bArr2, 0);
        this.bluetooth.write(str, bArr2);
        this.bluetooth.write(str, bArr);
    }

    /* access modifiers changed from: protected */
    public final boolean evaluateStatus(String str, byte[] bArr, byte b) {
        int status = getStatus(str, bArr, b);
        if (status == 0) {
            return true;
        }
        if (status >= 0) {
            String str2 = Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB.get(Integer.valueOf(status));
            if (str2 != null) {
                this.form.dispatchErrorOccurredEvent(this, str, 404, str2);
            } else {
                Form form = this.form;
                form.dispatchErrorOccurredEvent(this, str, 404, "Error code 0x" + Integer.toHexString(status & 255));
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final int getStatus(String str, byte[] bArr, byte b) {
        if (bArr.length >= 3) {
            if (bArr[0] != 2) {
                String str2 = this.logTag;
                Log.w(str2, str + ": unexpected return package byte 0: 0x" + Integer.toHexString(bArr[0] & Ev3Constants.Opcode.TST) + " (expected 0x02)");
            }
            if (bArr[1] != b) {
                String str3 = this.logTag;
                Log.w(str3, str + ": unexpected return package byte 1: 0x" + Integer.toHexString(bArr[1] & Ev3Constants.Opcode.TST) + " (expected 0x" + Integer.toHexString(b & Ev3Constants.Opcode.TST) + ")");
            }
            return getUBYTEValueFromBytes(bArr, 2);
        }
        String str4 = this.logTag;
        Log.w(str4, str + ": unexpected return package length " + bArr.length + " (expected >= 3)");
        return -1;
    }

    /* access modifiers changed from: protected */
    public final void copyBooleanValueToBytes(boolean z, byte[] bArr, int i) {
        bArr[i] = z ? (byte) 1 : 0;
    }

    /* access modifiers changed from: protected */
    public final void copySBYTEValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public final void copyUBYTEValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public final void copySWORDValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copyUWORDValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copySLONGValueToBytes(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i >> 8;
        bArr[i2 + 1] = (byte) i3;
        int i4 = i3 >> 8;
        bArr[i2 + 2] = (byte) i4;
        bArr[i2 + 3] = (byte) (i4 >> 8);
    }

    /* access modifiers changed from: protected */
    public final void copyULONGValueToBytes(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j & 255));
        long j2 = j >> 8;
        bArr[i + 1] = (byte) ((int) (j2 & 255));
        long j3 = j2 >> 8;
        bArr[i + 2] = (byte) ((int) (j3 & 255));
        bArr[i + 3] = (byte) ((int) ((j3 >> 8) & 255));
    }

    /* access modifiers changed from: protected */
    public final void copyStringValueToBytes(String str, byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (str.length() > i2) {
            str = str.substring(0, i2);
        }
        try {
            bArr2 = str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            String str2 = this.logTag;
            Log.w(str2, "UnsupportedEncodingException: " + e.getMessage());
            bArr2 = str.getBytes();
        }
        System.arraycopy(bArr2, 0, bArr, i, Math.min(i2, bArr2.length));
    }

    /* access modifiers changed from: protected */
    public final boolean getBooleanValueFromBytes(byte[] bArr, int i) {
        return bArr[i] != 0;
    }

    /* access modifiers changed from: protected */
    public final int getSBYTEValueFromBytes(byte[] bArr, int i) {
        return bArr[i];
    }

    /* access modifiers changed from: protected */
    public final int getUBYTEValueFromBytes(byte[] bArr, int i) {
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: protected */
    public final int getSWORDValueFromBytes(byte[] bArr, int i) {
        return (bArr[i + 1] << 8) | (bArr[i] & Ev3Constants.Opcode.TST);
    }

    /* access modifiers changed from: protected */
    public final int getUWORDValueFromBytes(byte[] bArr, int i) {
        return ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 8) | (bArr[i] & Ev3Constants.Opcode.TST);
    }

    /* access modifiers changed from: protected */
    public final int getSLONGValueFromBytes(byte[] bArr, int i) {
        return (bArr[i + 3] << 24) | (bArr[i] & Ev3Constants.Opcode.TST) | ((bArr[i + 1] & Ev3Constants.Opcode.TST) << 8) | ((bArr[i + 2] & Ev3Constants.Opcode.TST) << 16);
    }

    /* access modifiers changed from: protected */
    public final long getULONGValueFromBytes(byte[] bArr, int i) {
        return ((((long) bArr[i + 3]) & 255) << 24) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16);
    }

    /* access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bArr, int i) {
        int i2;
        int i3 = i;
        while (true) {
            if (i3 >= bArr.length) {
                i2 = 0;
                break;
            } else if (bArr[i3] == 0) {
                i2 = i3 - i;
                break;
            } else {
                i3++;
            }
        }
        return getStringValueFromBytes(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public final String getStringValueFromBytes(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            String str = this.logTag;
            Log.w(str, "UnsupportedEncodingException: " + e.getMessage());
            return new String(bArr, i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public final int convertMotorPortLetterToNumber(String str) {
        if (str.length() == 1) {
            return convertMotorPortLetterToNumber(str.charAt(0));
        }
        throw new IllegalArgumentException("Illegal motor port letter ".concat(String.valueOf(str)));
    }

    /* access modifiers changed from: protected */
    public final int convertMotorPortLetterToNumber(char c) {
        if (c == 'A' || c == 'a') {
            return 0;
        }
        if (c == 'B' || c == 'b') {
            return 1;
        }
        if (c == 'C' || c == 'c') {
            return 2;
        }
        throw new IllegalArgumentException("Illegal motor port letter ".concat(String.valueOf(c)));
    }

    /* access modifiers changed from: protected */
    public final int convertSensorPortLetterToNumber(String str) {
        if (str.length() == 1) {
            return convertSensorPortLetterToNumber(str.charAt(0));
        }
        throw new IllegalArgumentException("Illegal sensor port letter ".concat(String.valueOf(str)));
    }

    /* access modifiers changed from: protected */
    public final int convertSensorPortLetterToNumber(char c) {
        if (c == '1') {
            return 0;
        }
        if (c == '2') {
            return 1;
        }
        if (c == '3') {
            return 2;
        }
        if (c == '4') {
            return 3;
        }
        throw new IllegalArgumentException("Illegal sensor port letter ".concat(String.valueOf(c)));
    }

    /* access modifiers changed from: protected */
    public final int sanitizePower(int i) {
        if (i < -100) {
            String str = this.logTag;
            Log.w(str, "power " + i + " is invalid, using -100.");
            i = -100;
        }
        if (i <= 100) {
            return i;
        }
        String str2 = this.logTag;
        Log.w(str2, "power " + i + " is invalid, using 100.");
        return 100;
    }

    /* access modifiers changed from: protected */
    public final int sanitizeTurnRatio(int i) {
        if (i < -100) {
            String str = this.logTag;
            Log.w(str, "turnRatio " + i + " is invalid, using -100.");
            i = -100;
        }
        if (i <= 100) {
            return i;
        }
        String str2 = this.logTag;
        Log.w(str2, "turnRatio " + i + " is invalid, using 100.");
        return 100;
    }

    public void onDelete() {
        BluetoothClient bluetoothClient = this.bluetooth;
        if (bluetoothClient != null) {
            bluetoothClient.removeBluetoothConnectionListener$70312fc3(this);
            this.bluetooth.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((Component) this);
            this.bluetooth = null;
        }
    }
}
