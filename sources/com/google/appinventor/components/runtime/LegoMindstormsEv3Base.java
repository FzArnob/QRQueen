package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.Ev3BinaryParser;
import com.google.appinventor.components.runtime.util.GeoJSONUtil;
import java.util.Collections;

@SimpleObject
public class LegoMindstormsEv3Base extends AndroidNonvisibleComponent implements Component, Deleteable, GeoJSONUtil.PropertyApplication {
    protected BluetoothClient bluetooth;
    protected int commandCount;
    protected final String logTag;

    public void afterConnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothConnectionBase) {
    }

    protected LegoMindstormsEv3Base(ComponentContainer componentContainer, String str) {
        super(componentContainer.$form());
        this.logTag = str;
    }

    protected LegoMindstormsEv3Base() {
        super((Form) null);
        this.logTag = null;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The BluetoothClient component that should be used for communication.")
    public BluetoothClient BluetoothClient() {
        return this.bluetooth;
    }

    @DesignerProperty(defaultValue = "", editorType = "BluetoothClient")
    @SimpleProperty
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
    public final boolean isBluetoothConnected(String str) {
        BluetoothClient bluetoothClient = this.bluetooth;
        if (bluetoothClient == null) {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_BLUETOOTH_NOT_SET, new Object[0]);
            return false;
        } else if (bluetoothClient.IsConnected()) {
            return true;
        } else {
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_NOT_CONNECTED_TO_ROBOT, new Object[0]);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final byte[] sendCommand(String str, byte[] bArr, boolean z) {
        if (!isBluetoothConnected(str)) {
            return null;
        }
        byte[] pack = Ev3BinaryParser.pack("hh", Short.valueOf((short) (bArr.length + 2)), Short.valueOf((short) this.commandCount));
        this.commandCount++;
        this.bluetooth.write(str, pack);
        this.bluetooth.write(str, bArr);
        if (z) {
            byte[] read = this.bluetooth.read(str, 4);
            if (read.length == 4) {
                Object[] unpack = Ev3BinaryParser.unpack("hh", read);
                int shortValue = ((Short) unpack[0]).shortValue() - 2;
                ((Short) unpack[1]).shortValue();
                byte[] read2 = this.bluetooth.read(str, shortValue);
                if (read2.length == shortValue) {
                    return read2;
                }
                this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
                return null;
            }
            this.form.dispatchErrorOccurredEvent(this, str, ErrorMessages.ERROR_EV3_INVALID_REPLY, new Object[0]);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final int sensorPortLetterToPortNumber(String str) {
        if (str.length() == 1) {
            int charAt = str.charAt(0) - '1';
            if (charAt >= 0 && charAt <= 3) {
                return charAt;
            }
            throw new IllegalArgumentException("String \"" + str + "\" is not a valid sensor port letter");
        }
        throw new IllegalArgumentException("String \"" + str + "\" is not a valid sensor port letter");
    }

    /* access modifiers changed from: protected */
    public final String portNumberToSensorPortLetter(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException(i + " is not a valid port number");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i + 49);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final int motorPortLettersToBitField(String str) {
        if (str.length() <= 4) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < str.length(); i5++) {
                switch (str.charAt(i5)) {
                    case 'A':
                        if (i == 0) {
                            i = 1;
                            break;
                        } else {
                            throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
                        }
                    case 'B':
                        if (i2 == 0) {
                            i2 = 2;
                            break;
                        } else {
                            throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
                        }
                    case 'C':
                        if (i3 == 0) {
                            i3 = 4;
                            break;
                        } else {
                            throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
                        }
                    case 'D':
                        if (i4 == 0) {
                            i4 = 8;
                            break;
                        } else {
                            throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
                        }
                    default:
                        throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
                }
            }
            return i | i2 | i3 | i4;
        }
        throw new IllegalArgumentException("Malformed motor port letters \"" + str + "\"");
    }

    /* access modifiers changed from: protected */
    public final String bitFieldToMotorPortLetters(int i) {
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException("Invalid bit field number ".concat(String.valueOf(i)));
        }
        String str = "";
        if ((i & 1) != 0) {
            str = str + "A";
        }
        if ((i & 2) != 0) {
            str = str + "B";
        }
        if ((i & 4) != 0) {
            str = str + "C";
        }
        if ((i & 8) == 0) {
            return str;
        }
        return str + "D";
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
