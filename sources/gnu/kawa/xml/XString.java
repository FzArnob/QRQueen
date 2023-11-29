package gnu.kawa.xml;

public class XString implements CharSequence {
    public String text;
    private XStringType type;

    public XStringType getStringType() {
        return this.type;
    }

    public char charAt(int i) {
        return this.text.charAt(i);
    }

    public int length() {
        return this.text.length();
    }

    public CharSequence subSequence(int i, int i2) {
        return this.text.substring(i, i2);
    }

    public String toString() {
        return this.text;
    }

    XString(String str, XStringType xStringType) {
        this.text = str;
        this.type = xStringType;
    }
}
