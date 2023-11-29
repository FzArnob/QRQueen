package gnu.kawa.xml;

import gnu.math.IntNum;

public class XInteger extends IntNum {
    private XIntegerType type;

    public XIntegerType getIntegerType() {
        return this.type;
    }

    XInteger(IntNum intNum, XIntegerType xIntegerType) {
        this.words = intNum.words;
        this.ival = intNum.ival;
        this.type = xIntegerType;
    }
}
