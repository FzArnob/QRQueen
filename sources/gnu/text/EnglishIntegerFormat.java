package gnu.text;

import gnu.lists.Consumer;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class EnglishIntegerFormat extends NumberFormat {
    private static EnglishIntegerFormat cardinalEnglish;
    public static final String[] ones = {null, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    public static final String[] onesth = {null, "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelveth", "thirteenth", "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth"};
    private static EnglishIntegerFormat ordinalEnglish;
    public static final String[] power1000s = {null, " thousand", " million", " billion", " trillion", " quadrillion", " quintillion", " sextillion", " septillion", " octillion", " nonillion", " decillion", " undecillion", " duodecillion", " tredecillion", " quattuordecillion", " quindecillion", " sexdecillion", " septendecillion", " octodecillion", " novemdecillion", " vigintillion"};
    public static final String[] tens = {null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    public static final String[] tensth = {null, null, "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth", "ninetieth"};
    public boolean ordinal;

    public EnglishIntegerFormat(boolean z) {
        this.ordinal = z;
    }

    public static EnglishIntegerFormat getInstance(boolean z) {
        if (z) {
            if (ordinalEnglish == null) {
                ordinalEnglish = new EnglishIntegerFormat(true);
            }
            return ordinalEnglish;
        }
        if (cardinalEnglish == null) {
            cardinalEnglish = new EnglishIntegerFormat(false);
        }
        return cardinalEnglish;
    }

    /* access modifiers changed from: package-private */
    public void format999(StringBuffer stringBuffer, int i, boolean z) {
        if (i >= 100) {
            int i2 = i / 100;
            i %= 100;
            if (i2 > 1) {
                stringBuffer.append(ones[i2]);
                stringBuffer.append(' ');
            }
            stringBuffer.append("hundred");
            if (i > 0) {
                stringBuffer.append(' ');
            } else if (z) {
                stringBuffer.append("th");
            }
        }
        if (i >= 20) {
            int i3 = i / 10;
            i %= 10;
            stringBuffer.append(((!z || i != 0) ? tens : tensth)[i3]);
            if (i > 0) {
                stringBuffer.append('-');
            }
        }
        if (i > 0) {
            stringBuffer.append((z ? onesth : ones)[i]);
        }
    }

    /* access modifiers changed from: package-private */
    public void format(StringBuffer stringBuffer, long j, int i, boolean z) {
        long j2;
        StringBuffer stringBuffer2 = stringBuffer;
        int i2 = i;
        if (j >= 1000) {
            format(stringBuffer, j / 1000, i2 + 1, false);
            j2 = j % 1000;
            if (j2 > 0) {
                stringBuffer.append(", ");
            } else if (z) {
                stringBuffer.append("th");
            }
        } else {
            j2 = j;
        }
        if (j2 > 0) {
            format999(stringBuffer, (int) j2, z && i2 == 0);
            String[] strArr = power1000s;
            if (i2 >= strArr.length) {
                stringBuffer.append(" times ten to the ");
                format(stringBuffer, (long) (i2 * 3), 0, true);
                stringBuffer.append(" power");
            } else if (i2 > 0) {
                stringBuffer.append(strArr[i2]);
            }
        }
    }

    public void writeInt(int i, Consumer consumer) {
        writeLong((long) i, consumer);
    }

    public void writeLong(long j, Consumer consumer) {
        StringBuffer stringBuffer = new StringBuffer();
        format(j, stringBuffer, (FieldPosition) null);
        consumer.write((CharSequence) stringBuffer, 0, stringBuffer.length());
    }

    public void writeObject(Object obj, Consumer consumer) {
        writeLong(((Number) obj).longValue(), consumer);
    }

    public void writeBoolean(boolean z, Consumer consumer) {
        writeLong(z ? 1 : 0, consumer);
    }

    public StringBuffer format(long j, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i == 0) {
            stringBuffer.append(this.ordinal ? "zeroth" : "zero");
        } else {
            if (i < 0) {
                stringBuffer.append("minus ");
                j = -j;
            }
            format(stringBuffer, j, 0, this.ordinal);
        }
        return stringBuffer;
    }

    public StringBuffer format(double d, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        long j = (long) d;
        if (((double) j) == d) {
            return format(j, stringBuffer, fieldPosition);
        }
        stringBuffer.append(Double.toString(d));
        return stringBuffer;
    }

    public Number parse(String str, ParsePosition parsePosition) {
        throw new Error("EnglishIntegerFormat.parseObject - not implemented");
    }
}
