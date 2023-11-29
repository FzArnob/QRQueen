package gnu.expr;

import gnu.bytecode.Field;
import gnu.bytecode.Type;

public class Literal {
    static final int CYCLIC = 4;
    static final int EMITTED = 8;
    static final int WRITING = 1;
    static final int WRITTEN = 2;
    public static final Literal nullLiteral = new Literal((Object) null, (Type) Type.nullType);
    Type[] argTypes;
    Object[] argValues;
    public Field field;
    public int flags;
    int index;
    Literal next;
    public Type type;
    Object value;

    public final Object getValue() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public void assign(LitTable litTable) {
        String str = null;
        assign((String) null, litTable);
    }

    /* access modifiers changed from: package-private */
    public void assign(String str, LitTable litTable) {
        int i = litTable.comp.immediate ? 9 : 24;
        if (str == null) {
            int i2 = litTable.literalsCount;
            litTable.literalsCount = i2 + 1;
            this.index = i2;
            str = "Lit" + this.index;
        } else {
            i |= 1;
        }
        assign(litTable.mainClass.addField(str, this.type, i), litTable);
    }

    /* access modifiers changed from: package-private */
    public void assign(Field field2, LitTable litTable) {
        this.next = litTable.literalsChain;
        litTable.literalsChain = this;
        this.field = field2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Literal(Object obj, LitTable litTable) {
        this(obj, (String) null, litTable);
        String str = null;
    }

    public Literal(Object obj, String str, LitTable litTable) {
        this.value = obj;
        litTable.literalTable.put(obj, this);
        this.type = Type.make(obj.getClass());
        assign(str, litTable);
    }

    public Literal(Object obj, Field field2, LitTable litTable) {
        this.value = obj;
        litTable.literalTable.put(obj, this);
        this.field = field2;
        this.type = field2.getType();
        this.flags = 10;
    }

    public Literal(Object obj, Type type2, LitTable litTable) {
        this.value = obj;
        litTable.literalTable.put(obj, this);
        this.type = type2;
    }

    Literal(Object obj, Type type2) {
        this.value = obj;
        this.type = type2;
    }
}
