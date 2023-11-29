package gnu.text;

import androidx.core.app.NotificationCompat;
import com.microsoft.appcenter.analytics.ingestion.models.PageLog;
import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Char implements Comparable, Externalizable {
    static Char[] ascii = new Char[128];
    static char[] charNameValues = {' ', 9, 10, 10, 13, 12, 8, 27, 127, 127, 127, 7, 7, 11, 0};
    static String[] charNames = {"space", "tab", "newline", "linefeed", "return", PageLog.TYPE, "backspace", "esc", "delete", "del", "rubout", NotificationCompat.CATEGORY_ALARM, "bel", "vtab", "nul"};
    static CharMap hashTable = new CharMap();
    int value;

    public Char() {
    }

    Char(int i) {
        this.value = i;
    }

    public void print(Consumer consumer) {
        print(this.value, consumer);
    }

    public static void print(int i, Consumer consumer) {
        if (i >= 65536) {
            consumer.write((int) (char) (((i - 65536) >> 10) + 55296));
            consumer.write((int) (char) ((i & 1023) + 56320));
            return;
        }
        consumer.write((int) (char) i);
    }

    public final char charValue() {
        return (char) this.value;
    }

    public final int intValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    static {
        int i = 128;
        while (true) {
            i--;
            if (i >= 0) {
                ascii[i] = new Char(i);
            } else {
                return;
            }
        }
    }

    public static Char make(int i) {
        Char charR;
        if (i < 128) {
            return ascii[i];
        }
        synchronized (hashTable) {
            charR = hashTable.get(i);
        }
        return charR;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Char) && ((Char) obj).intValue() == this.value;
    }

    public static int nameToChar(String str) {
        char charAt;
        int length = charNames.length;
        do {
            length--;
            if (length < 0) {
                int length2 = charNames.length;
                do {
                    length2--;
                    if (length2 < 0) {
                        int length3 = str.length();
                        if (length3 > 1 && str.charAt(0) == 'u') {
                            int i = 1;
                            int i2 = 0;
                            while (i != length3) {
                                int digit = Character.digit(str.charAt(i), 16);
                                if (digit >= 0) {
                                    i2 = (i2 << 4) + digit;
                                    i++;
                                }
                            }
                            return i2;
                        }
                        if (length3 == 3 && str.charAt(1) == '-' && ((charAt = str.charAt(0)) == 'c' || charAt == 'C')) {
                            return str.charAt(2) & 31;
                        }
                        return -1;
                    }
                } while (!charNames[length2].equalsIgnoreCase(str));
                return charNameValues[length2];
            }
        } while (!charNames[length].equals(str));
        return charNameValues[length];
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('\'');
        int i = this.value;
        if (i < 32 || i >= 127 || i == 39) {
            stringBuffer.append('\\');
            int i2 = this.value;
            if (i2 == 39) {
                stringBuffer.append('\'');
            } else if (i2 == 10) {
                stringBuffer.append('n');
            } else if (i2 == 13) {
                stringBuffer.append('r');
            } else if (i2 == 9) {
                stringBuffer.append('t');
            } else if (i2 < 256) {
                String octalString = Integer.toOctalString(i2);
                int length = 3 - octalString.length();
                while (true) {
                    length--;
                    if (length < 0) {
                        break;
                    }
                    stringBuffer.append('0');
                }
                stringBuffer.append(octalString);
            } else {
                stringBuffer.append('u');
                String hexString = Integer.toHexString(this.value);
                int length2 = 4 - hexString.length();
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        break;
                    }
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
        } else {
            stringBuffer.append((char) i);
        }
        stringBuffer.append('\'');
        return stringBuffer.toString();
    }

    public static String toScmReadableString(int i) {
        StringBuffer stringBuffer = new StringBuffer(20);
        stringBuffer.append("#\\");
        int i2 = 0;
        while (true) {
            char[] cArr = charNameValues;
            if (i2 >= cArr.length) {
                if (i < 32 || i > 127) {
                    stringBuffer.append('x');
                    stringBuffer.append(Integer.toString(i, 16));
                } else {
                    stringBuffer.append((char) i);
                }
                return stringBuffer.toString();
            } else if (((char) i) == cArr[i2]) {
                stringBuffer.append(charNames[i2]);
                return stringBuffer.toString();
            } else {
                i2++;
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int i = this.value;
        if (i > 55296) {
            if (i > 65535) {
                objectOutput.writeChar(((i - 65536) >> 10) + 55296);
                this.value = (this.value & 1023) + 56320;
            } else if (i <= 56319) {
                objectOutput.writeChar(i);
                this.value = 0;
            }
        }
        objectOutput.writeChar(this.value);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        char readChar;
        char readChar2 = objectInput.readChar();
        this.value = readChar2;
        if (readChar2 >= 55296 && readChar2 < 56319 && (readChar = objectInput.readChar()) >= 56320 && readChar <= 57343) {
            this.value = ((this.value - 55296) << 10) + (readChar - 56320) + 65536;
        }
    }

    public Object readResolve() throws ObjectStreamException {
        return make(this.value);
    }

    public int compareTo(Object obj) {
        return this.value - ((Char) obj).value;
    }
}
