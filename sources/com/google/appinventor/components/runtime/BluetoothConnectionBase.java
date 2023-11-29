package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
public abstract class BluetoothConnectionBase extends AndroidNonvisibleComponent implements Component, Deleteable, OnDestroyListener {
    private final List<GeoJSONUtil.PropertyApplication> bluetoothConnectionListeners;
    private ByteOrder byteOrder;
    private Object connectedBluetoothSocket;
    private byte delimiter;
    private String encoding;
    private InputStream inputStream;
    protected final String logTag;
    private OutputStream outputStream;
    protected boolean secure;

    @SimpleEvent(description = "The BluetoothError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
    public void BluetoothError(String str, String str2) {
    }

    public final void Initialize() {
    }

    protected BluetoothConnectionBase(ComponentContainer componentContainer, String str) {
        this(componentContainer.$form(), str);
        this.form.registerForOnDestroy(this);
    }

    private BluetoothConnectionBase(Form form, String str) {
        super(form);
        this.bluetoothConnectionListeners = new ArrayList();
        this.logTag = str;
        HighByteFirst(false);
        CharacterEncoding("UTF-8");
        DelimiterByte(0);
        Secure(true);
    }

    protected BluetoothConnectionBase(OutputStream outputStream2, InputStream inputStream2) {
        this((Form) null, (String) null);
        this.connectedBluetoothSocket = "Not Null";
        this.outputStream = outputStream2;
        this.inputStream = inputStream2;
    }

    /* access modifiers changed from: package-private */
    public void addBluetoothConnectionListener$70312fc3(GeoJSONUtil.PropertyApplication propertyApplication) {
        this.bluetoothConnectionListeners.add(propertyApplication);
    }

    /* access modifiers changed from: package-private */
    public void removeBluetoothConnectionListener$70312fc3(GeoJSONUtil.PropertyApplication propertyApplication) {
        this.bluetoothConnectionListeners.remove(propertyApplication);
    }

    private void fireAfterConnectEvent() {
        for (GeoJSONUtil.PropertyApplication afterConnect : this.bluetoothConnectionListeners) {
            afterConnect.afterConnect(this);
        }
    }

    private void fireBeforeDisconnectEvent() {
        for (GeoJSONUtil.PropertyApplication beforeDisconnect : this.bluetoothConnectionListeners) {
            beforeDisconnect.beforeDisconnect(this);
        }
    }

    /* access modifiers changed from: protected */
    public void bluetoothError(String str, int i, Object... objArr) {
        this.form.dispatchErrorOccurredEvent(this, str, i, objArr);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is available on the device")
    public boolean Available() {
        return BluetoothReflection.getBluetoothAdapter() != null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether Bluetooth is enabled")
    public boolean Enabled() {
        Object bluetoothAdapter = BluetoothReflection.getBluetoothAdapter();
        return bluetoothAdapter != null && BluetoothReflection.isBluetoothEnabled(bluetoothAdapter);
    }

    /* access modifiers changed from: protected */
    public final void setConnection(Object obj) throws IOException {
        this.connectedBluetoothSocket = obj;
        this.inputStream = new BufferedInputStream(BluetoothReflection.getInputStream(this.connectedBluetoothSocket));
        this.outputStream = new BufferedOutputStream(BluetoothReflection.getOutputStream(this.connectedBluetoothSocket));
        fireAfterConnectEvent();
    }

    @SimpleFunction(description = "Disconnect from the connected Bluetooth device.")
    public final void Disconnect() {
        if (this.connectedBluetoothSocket != null) {
            fireBeforeDisconnectEvent();
            try {
                BluetoothReflection.closeBluetoothSocket(this.connectedBluetoothSocket);
                Log.i(this.logTag, "Disconnected from Bluetooth device.");
            } catch (Exception e) {
                String str = this.logTag;
                Log.w(str, "Error while disconnecting: " + e.getMessage());
            }
            this.connectedBluetoothSocket = null;
        }
        this.inputStream = null;
        this.outputStream = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public final boolean IsConnected() {
        return this.connectedBluetoothSocket != null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether to invoke SSP (Simple Secure Pairing), which is supported on devices with Bluetooth v2.1 or higher. When working with embedded Bluetooth devices, this property may need to be set to False. For Android 2.0-2.2, this property setting will be ignored.")
    public boolean Secure() {
        return this.secure;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void Secure(boolean z) {
        this.secure = z;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public boolean HighByteFirst() {
        return this.byteOrder == ByteOrder.BIG_ENDIAN;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void HighByteFirst(boolean z) {
        this.byteOrder = z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
    }

    @DesignerProperty(defaultValue = "UTF-8", editorType = "string")
    @SimpleProperty
    public void CharacterEncoding(String str) {
        try {
            "check".getBytes(str);
            this.encoding = str;
        } catch (UnsupportedEncodingException unused) {
            bluetoothError("CharacterEncoding", ErrorMessages.ERROR_BLUETOOTH_UNSUPPORTED_ENCODING, str);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String CharacterEncoding() {
        return this.encoding;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
    @SimpleProperty
    public void DelimiterByte(int i) {
        byte b = (byte) i;
        int i2 = i >> 8;
        if (i2 == 0 || i2 == -1) {
            this.delimiter = b;
            return;
        }
        bluetoothError("DelimiterByte", 511, Integer.valueOf(i));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public int DelimiterByte() {
        return this.delimiter;
    }

    @SimpleFunction(description = "Send text to the connected Bluetooth device.")
    public void SendText(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes(this.encoding);
        } catch (UnsupportedEncodingException e) {
            String str2 = this.logTag;
            Log.w(str2, "UnsupportedEncodingException: " + e.getMessage());
            bArr = str.getBytes();
        }
        write("SendText", bArr);
    }

    @SimpleFunction(description = "Send a 1-byte number to the connected Bluetooth device.")
    public void Send1ByteNumber(String str) {
        try {
            int intValue = Integer.decode(str).intValue();
            byte b = (byte) intValue;
            int i = intValue >> 8;
            if (i == 0 || i == -1) {
                write("Send1ByteNumber", b);
                return;
            }
            bluetoothError("Send1ByteNumber", 511, str);
        } catch (NumberFormatException unused) {
            bluetoothError("Send1ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str);
        }
    }

    @SimpleFunction(description = "Send a 2-byte number to the connected Bluetooth device.")
    public void Send2ByteNumber(String str) {
        int i;
        try {
            int intValue = Integer.decode(str).intValue();
            byte[] bArr = new byte[2];
            if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
                bArr[1] = (byte) intValue;
                i = intValue >> 8;
                bArr[0] = (byte) i;
            } else {
                bArr[0] = (byte) intValue;
                i = intValue >> 8;
                bArr[1] = (byte) i;
            }
            int i2 = i >> 8;
            if (i2 == 0 || i2 == -1) {
                write("Send2ByteNumber", bArr);
                return;
            }
            bluetoothError("Send2ByteNumber", 512, str, 2);
        } catch (NumberFormatException unused) {
            bluetoothError("Send2ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str);
        }
    }

    @SimpleFunction(description = "Send a 4-byte number to the connected Bluetooth device.")
    public void Send4ByteNumber(String str) {
        long j;
        try {
            long longValue = Long.decode(str).longValue();
            byte[] bArr = new byte[4];
            if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
                bArr[3] = (byte) ((int) (longValue & 255));
                long j2 = longValue >> 8;
                bArr[2] = (byte) ((int) (j2 & 255));
                long j3 = j2 >> 8;
                bArr[1] = (byte) ((int) (j3 & 255));
                j = j3 >> 8;
                bArr[0] = (byte) ((int) (j & 255));
            } else {
                bArr[0] = (byte) ((int) (longValue & 255));
                long j4 = longValue >> 8;
                bArr[1] = (byte) ((int) (j4 & 255));
                long j5 = j4 >> 8;
                bArr[2] = (byte) ((int) (j5 & 255));
                j = j5 >> 8;
                bArr[3] = (byte) ((int) (j & 255));
            }
            long j6 = j >> 8;
            if (j6 == 0 || j6 == -1) {
                write("Send4ByteNumber", bArr);
                return;
            }
            bluetoothError("Send4ByteNumber", 512, str, 4);
        } catch (NumberFormatException unused) {
            bluetoothError("Send4ByteNumber", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_DECODE, str);
        }
    }

    @SimpleFunction(description = "Send a list of byte values to the connected Bluetooth device.")
    public void SendBytes(YailList yailList) {
        Object[] array = yailList.toArray();
        byte[] bArr = new byte[array.length];
        int i = 0;
        while (i < array.length) {
            try {
                int intValue = Integer.decode(array[i].toString()).intValue();
                bArr[i] = (byte) intValue;
                int i2 = intValue >> 8;
                if (i2 == 0 || i2 == -1) {
                    i++;
                } else {
                    bluetoothError("SendBytes", ErrorMessages.ERROR_BLUETOOTH_COULD_NOT_FIT_ELEMENT_IN_BYTE, Integer.valueOf(i + 1));
                    return;
                }
            } catch (NumberFormatException unused) {
                bluetoothError("SendBytes", 513, Integer.valueOf(i + 1));
                return;
            }
        }
        write("SendBytes", bArr);
    }

    /* access modifiers changed from: protected */
    public void write(String str, byte b) {
        if (!IsConnected()) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(b);
            this.outputStream.flush();
        } catch (Exception e) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void write(String str, byte[] bArr) {
        if (!IsConnected()) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return;
        }
        try {
            this.outputStream.write(bArr);
            this.outputStream.flush();
        } catch (Exception e) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_WRITE, e.getMessage());
        }
    }

    @SimpleFunction(description = "Returns an estimate of the number of bytes that can be received without blocking")
    public int BytesAvailableToReceive() {
        if (!IsConnected()) {
            bluetoothError("BytesAvailableToReceive", ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return 0;
        }
        try {
            return this.inputStream.available();
        } catch (Exception e) {
            bluetoothError("BytesAvailableToReceive", ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, e.getMessage());
            return 0;
        }
    }

    @SimpleFunction(description = "Receive text from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public String ReceiveText(int i) {
        byte[] read = read("ReceiveText", i);
        if (i >= 0) {
            return new String(read, this.encoding);
        }
        try {
            return new String(read, 0, read.length - 1, this.encoding);
        } catch (UnsupportedEncodingException e) {
            String str = this.logTag;
            Log.w(str, "UnsupportedEncodingException: " + e.getMessage());
            return new String(read);
        }
    }

    @SimpleFunction(description = "Receive a signed 1-byte number from the connected Bluetooth device.")
    public int ReceiveSigned1ByteNumber() {
        byte[] read = read("ReceiveSigned1ByteNumber", 1);
        if (read.length != 1) {
            return 0;
        }
        return read[0];
    }

    @SimpleFunction(description = "Receive an unsigned 1-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned1ByteNumber() {
        byte[] read = read("ReceiveUnsigned1ByteNumber", 1);
        if (read.length != 1) {
            return 0;
        }
        return read[0] & Ev3Constants.Opcode.TST;
    }

    @SimpleFunction(description = "Receive a signed 2-byte number from the connected Bluetooth device.")
    public int ReceiveSigned2ByteNumber() {
        byte b;
        byte b2;
        byte[] read = read("ReceiveSigned2ByteNumber", 2);
        if (read.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            b = read[1] & Ev3Constants.Opcode.TST;
            b2 = read[0];
        } else {
            b = read[0] & Ev3Constants.Opcode.TST;
            b2 = read[1];
        }
        return (b2 << 8) | b;
    }

    @SimpleFunction(description = "Receive a unsigned 2-byte number from the connected Bluetooth device.")
    public int ReceiveUnsigned2ByteNumber() {
        byte b;
        byte b2;
        byte[] read = read("ReceiveUnsigned2ByteNumber", 2);
        if (read.length != 2) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            b = read[1] & Ev3Constants.Opcode.TST;
            b2 = read[0];
        } else {
            b = read[0] & Ev3Constants.Opcode.TST;
            b2 = read[1];
        }
        return ((b2 & Ev3Constants.Opcode.TST) << 8) | b;
    }

    @SimpleFunction(description = "Receive a signed 4-byte number from the connected Bluetooth device.")
    public long ReceiveSigned4ByteNumber() {
        byte b;
        byte b2;
        byte[] read = read("ReceiveSigned4ByteNumber", 4);
        if (read.length != 4) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            b = (read[3] & Ev3Constants.Opcode.TST) | ((read[2] & Ev3Constants.Opcode.TST) << 8) | ((read[1] & Ev3Constants.Opcode.TST) << 16);
            b2 = read[0];
        } else {
            b = (read[0] & Ev3Constants.Opcode.TST) | ((read[1] & Ev3Constants.Opcode.TST) << 8) | ((read[2] & Ev3Constants.Opcode.TST) << 16);
            b2 = read[3];
        }
        return (long) ((b2 << 24) | b);
    }

    @SimpleFunction(description = "Receive a unsigned 4-byte number from the connected Bluetooth device.")
    public long ReceiveUnsigned4ByteNumber() {
        long j;
        byte b;
        byte[] read = read("ReceiveUnsigned4ByteNumber", 4);
        if (read.length != 4) {
            return 0;
        }
        if (this.byteOrder == ByteOrder.BIG_ENDIAN) {
            j = (((long) read[3]) & 255) | ((((long) read[2]) & 255) << 8) | ((((long) read[1]) & 255) << 16);
            b = read[0];
        } else {
            j = (((long) read[0]) & 255) | ((((long) read[1]) & 255) << 8) | ((((long) read[2]) & 255) << 16);
            b = read[3];
        }
        return j | ((((long) b) & 255) << 24);
    }

    @SimpleFunction(description = "Receive multiple signed byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveSignedBytes(int i) {
        byte[] read = read("ReceiveSignedBytes", i);
        ArrayList arrayList = new ArrayList();
        for (byte valueOf : read) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }

    @SimpleFunction(description = "Receive multiple unsigned byte values from the connected Bluetooth device. If numberOfBytes is less than 0, read until a delimiter byte value is received.")
    public List<Integer> ReceiveUnsignedBytes(int i) {
        byte[] read = read("ReceiveUnsignedBytes", i);
        ArrayList arrayList = new ArrayList();
        for (byte b : read) {
            arrayList.add(Integer.valueOf(b & Ev3Constants.Opcode.TST));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final byte[] read(String str, int i) {
        if (!IsConnected()) {
            bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, new Object[0]);
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i >= 0) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    break;
                }
                try {
                    int read = this.inputStream.read(bArr, i2, i - i2);
                    if (read == -1) {
                        bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new Object[0]);
                        break;
                    }
                    i2 += read;
                } catch (Exception e) {
                    bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, e.getMessage());
                }
            }
            byteArrayOutputStream.write(bArr, 0, i2);
        } else {
            while (true) {
                try {
                    int read2 = this.inputStream.read();
                    if (read2 != -1) {
                        byteArrayOutputStream.write(read2);
                        if (read2 == this.delimiter) {
                            break;
                        }
                    } else {
                        bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, new Object[0]);
                        break;
                    }
                } catch (Exception e2) {
                    bluetoothError(str, ErrorMessages.ERROR_BLUETOOTH_UNABLE_TO_READ, e2.getMessage());
                }
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void onDestroy() {
        prepareToDie();
    }

    public void onDelete() {
        prepareToDie();
    }

    private void prepareToDie() {
        if (this.connectedBluetoothSocket != null) {
            Disconnect();
        }
    }
}
