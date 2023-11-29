package org.jose4j.json.internal.json_simple.parser;

public class Yytoken {
    public static final int TYPE_COLON = 6;
    public static final int TYPE_COMMA = 5;
    public static final int TYPE_EOF = -1;
    public static final int TYPE_LEFT_BRACE = 1;
    public static final int TYPE_LEFT_SQUARE = 3;
    public static final int TYPE_RIGHT_BRACE = 2;
    public static final int TYPE_RIGHT_SQUARE = 4;
    public static final int TYPE_VALUE = 0;
    public int type;
    public Object value;

    public Yytoken(int i, Object obj) {
        this.type = i;
        this.value = obj;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (this.type) {
            case -1:
                sb.append("END OF FILE");
                break;
            case 0:
                sb.append("VALUE(");
                sb.append(this.value);
                sb.append(")");
                break;
            case 1:
                sb.append("LEFT BRACE({)");
                break;
            case 2:
                sb.append("RIGHT BRACE(})");
                break;
            case 3:
                sb.append("LEFT SQUARE([)");
                break;
            case 4:
                sb.append("RIGHT SQUARE(])");
                break;
            case 5:
                sb.append("COMMA(,)");
                break;
            case 6:
                sb.append("COLON(:)");
                break;
        }
        return sb.toString();
    }
}
