package gnu.kawa.functions;

import com.microsoft.appcenter.Constants;
import gnu.bytecode.Access;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.lists.Array;
import gnu.lists.CharSeq;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.SimpleVector;
import gnu.lists.Strings;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.text.Char;
import gnu.text.Printable;
import gnu.xml.XMLPrinter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.regex.Pattern;
import org.jose4j.jwk.RsaJsonWebKey;

public class DisplayFormat extends AbstractFormat {
    public static final ThreadLocation outBase;
    public static final ThreadLocation outRadix = new ThreadLocation("out-radix");
    static Pattern r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
    char language;
    boolean readable;

    static {
        ThreadLocation threadLocation = new ThreadLocation("out-base");
        outBase = threadLocation;
        threadLocation.setGlobal(IntNum.ten());
    }

    public DisplayFormat(boolean z, char c) {
        this.readable = z;
        this.language = c;
    }

    public static DisplayFormat getEmacsLispFormat(boolean z) {
        return new DisplayFormat(z, 'E');
    }

    public static DisplayFormat getCommonLispFormat(boolean z) {
        return new DisplayFormat(z, Access.CLASS_CONTEXT);
    }

    public static DisplayFormat getSchemeFormat(boolean z) {
        return new DisplayFormat(z, 'S');
    }

    public boolean getReadableOutput() {
        return this.readable;
    }

    public void writeBoolean(boolean z, Consumer consumer) {
        write(this.language == 'S' ? z ? "#t" : "#f" : z ? RsaJsonWebKey.FACTOR_CRT_COEFFICIENT : "nil", consumer);
    }

    public void write(int i, Consumer consumer) {
        if (!getReadableOutput()) {
            Char.print(i, consumer);
        } else if (this.language != 'E' || i <= 32) {
            write(Char.toScmReadableString(i), consumer);
        } else {
            consumer.write(63);
            Char.print(i, consumer);
        }
    }

    public void writeList(LList lList, OutPort outPort) {
        outPort.startLogicalBlock("(", false, ")");
        Object obj = lList;
        while (obj instanceof Pair) {
            if (obj != lList) {
                outPort.writeSpaceFill();
            }
            Pair pair = (Pair) obj;
            writeObject(pair.getCar(), outPort);
            obj = pair.getCdr();
        }
        if (obj != LList.Empty) {
            outPort.writeSpaceFill();
            outPort.write(". ");
            writeObject(LList.checkNonList(obj), outPort);
        }
        outPort.endLogicalBlock(")");
    }

    public void writeObject(Object obj, Consumer consumer) {
        boolean z;
        if (!(consumer instanceof OutPort) || (obj instanceof UntypedAtomic) || (obj instanceof Values) || (!getReadableOutput() && ((obj instanceof Char) || (obj instanceof CharSequence) || (obj instanceof Character)))) {
            z = false;
        } else {
            ((OutPort) consumer).writeWordStart();
            z = true;
        }
        writeObjectRaw(obj, consumer);
        if (z) {
            ((OutPort) consumer).writeWordEnd();
        }
    }

    public void writeObjectRaw(Object obj, Consumer consumer) {
        int i;
        String str;
        if (obj instanceof Boolean) {
            writeBoolean(((Boolean) obj).booleanValue(), consumer);
        } else if (obj instanceof Char) {
            write(((Char) obj).intValue(), consumer);
        } else if (obj instanceof Character) {
            write(((Character) obj).charValue(), consumer);
        } else if (obj instanceof Symbol) {
            Symbol symbol = (Symbol) obj;
            if (symbol.getNamespace() == XmlNamespace.HTML) {
                write("html:", consumer);
                write(symbol.getLocalPart(), consumer);
                return;
            }
            writeSymbol(symbol, consumer, this.readable);
        } else {
            boolean z = true;
            if (!(obj instanceof URI) || !getReadableOutput() || !(consumer instanceof PrintWriter)) {
                int i2 = 0;
                if (obj instanceof CharSequence) {
                    CharSequence charSequence = (CharSequence) obj;
                    if (getReadableOutput() && (consumer instanceof PrintWriter)) {
                        Strings.printQuoted(charSequence, (PrintWriter) consumer, 1);
                    } else if (obj instanceof String) {
                        consumer.write((String) obj);
                    } else if (obj instanceof CharSeq) {
                        CharSeq charSeq = (CharSeq) obj;
                        charSeq.consume(0, charSeq.size(), consumer);
                    } else {
                        int length = charSequence.length();
                        while (i2 < length) {
                            consumer.write((int) charSequence.charAt(i2));
                            i2++;
                        }
                    }
                } else if (!(obj instanceof LList) || !(consumer instanceof OutPort)) {
                    String str2 = "[";
                    String str3 = "]";
                    if (obj instanceof SimpleVector) {
                        SimpleVector simpleVector = (SimpleVector) obj;
                        String tag = simpleVector.getTag();
                        if (this.language != 'E') {
                            if (tag == null) {
                                str = "#(";
                            } else {
                                str = "#" + tag + "(";
                            }
                            str2 = str;
                            str3 = ")";
                        }
                        boolean z2 = consumer instanceof OutPort;
                        if (z2) {
                            ((OutPort) consumer).startLogicalBlock(str2, false, str3);
                        } else {
                            write(str2, consumer);
                        }
                        int size = simpleVector.size() << 1;
                        while (i2 < size) {
                            if (i2 > 0 && z2) {
                                ((OutPort) consumer).writeSpaceFill();
                            }
                            if (!simpleVector.consumeNext(i2, consumer)) {
                                break;
                            }
                            i2 += 2;
                        }
                        if (z2) {
                            ((OutPort) consumer).endLogicalBlock(str3);
                        } else {
                            write(str3, consumer);
                        }
                    } else if (obj instanceof Array) {
                        write((Array) obj, 0, 0, consumer);
                    } else if (obj instanceof KNode) {
                        if (getReadableOutput()) {
                            write("#", consumer);
                        }
                        XMLPrinter xMLPrinter = new XMLPrinter(consumer instanceof Writer ? (Writer) consumer : new ConsumerWriter(consumer));
                        xMLPrinter.writeObject(obj);
                        xMLPrinter.closeThis();
                    } else if (obj == Values.empty && getReadableOutput()) {
                        write("#!void", consumer);
                    } else if (obj instanceof Consumable) {
                        ((Consumable) obj).consume(consumer);
                    } else if (obj instanceof Printable) {
                        ((Printable) obj).print(consumer);
                    } else {
                        String str4 = null;
                        if (obj instanceof RatNum) {
                            Object obj2 = outBase.get((Object) null);
                            Object obj3 = outRadix.get((Object) null);
                            if (obj3 == null || (obj3 != Boolean.TRUE && !"yes".equals(obj3.toString()))) {
                                z = false;
                            }
                            if (obj2 instanceof Number) {
                                i = ((IntNum) obj2).intValue();
                            } else {
                                i = obj2 != null ? Integer.parseInt(obj2.toString()) : 10;
                            }
                            String ratNum = ((RatNum) obj).toString(i);
                            if (z) {
                                if (i == 16) {
                                    write("#x", consumer);
                                } else if (i == 8) {
                                    write("#o", consumer);
                                } else if (i == 2) {
                                    write("#b", consumer);
                                } else if (i != 10 || !(obj instanceof IntNum)) {
                                    write("#" + obj2 + "r", consumer);
                                }
                            }
                            write(ratNum, consumer);
                            if (z && i == 10 && (obj instanceof IntNum)) {
                                write(".", consumer);
                            }
                        } else if (!(obj instanceof Enum) || !getReadableOutput()) {
                            if (obj != null) {
                                if (obj.getClass().isArray()) {
                                    int length2 = java.lang.reflect.Array.getLength(obj);
                                    boolean z3 = consumer instanceof OutPort;
                                    if (z3) {
                                        ((OutPort) consumer).startLogicalBlock(str2, false, str3);
                                    } else {
                                        write(str2, consumer);
                                    }
                                    while (i2 < length2) {
                                        if (i2 > 0) {
                                            write(" ", consumer);
                                            if (z3) {
                                                ((OutPort) consumer).writeBreakFill();
                                            }
                                        }
                                        writeObject(java.lang.reflect.Array.get(obj, i2), consumer);
                                        i2++;
                                    }
                                    if (z3) {
                                        ((OutPort) consumer).endLogicalBlock(str3);
                                        return;
                                    } else {
                                        write(str3, consumer);
                                        return;
                                    }
                                } else {
                                    str4 = obj.toString();
                                }
                            }
                            if (str4 == null) {
                                write("#!null", consumer);
                            } else {
                                write(str4, consumer);
                            }
                        } else {
                            write(obj.getClass().getName(), consumer);
                            write(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR, consumer);
                            write(((Enum) obj).name(), consumer);
                        }
                    }
                } else {
                    writeList((LList) obj, (OutPort) consumer);
                }
            } else {
                write("#,(URI ", consumer);
                Strings.printQuoted(obj.toString(), (PrintWriter) consumer, 1);
                consumer.write(41);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int write(Array array, int i, int i2, Consumer consumer) {
        String str;
        int i3;
        int rank = array.rank();
        if (i2 > 0) {
            str = "(";
        } else if (rank == 1) {
            str = "#(";
        } else {
            str = "#" + rank + "a(";
        }
        boolean z = consumer instanceof OutPort;
        int i4 = 0;
        if (z) {
            ((OutPort) consumer).startLogicalBlock(str, false, ")");
        } else {
            write(str, consumer);
        }
        if (rank > 0) {
            int size = array.getSize(i2);
            int i5 = i2 + 1;
            int i6 = 0;
            while (i4 < size) {
                if (i4 > 0) {
                    write(" ", consumer);
                    if (z) {
                        ((OutPort) consumer).writeBreakFill();
                    }
                }
                if (i5 == rank) {
                    writeObject(array.getRowMajor(i), consumer);
                    i3 = 1;
                } else {
                    i3 = write(array, i, i5, consumer);
                }
                i += i3;
                i6 += i3;
                i4++;
            }
            i4 = i6;
        }
        if (z) {
            ((OutPort) consumer).endLogicalBlock(")");
        } else {
            write(")", consumer);
        }
        return i4;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeSymbol(gnu.mapping.Symbol r10, gnu.lists.Consumer r11, boolean r12) {
        /*
            r9 = this;
            java.lang.String r0 = r10.getPrefix()
            gnu.mapping.Namespace r1 = r10.getNamespace()
            if (r1 != 0) goto L_0x000c
            r2 = 0
            goto L_0x0010
        L_0x000c:
            java.lang.String r2 = r1.getName()
        L_0x0010:
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x001c
            int r5 = r2.length()
            if (r5 <= 0) goto L_0x001c
            r5 = 1
            goto L_0x001d
        L_0x001c:
            r5 = 0
        L_0x001d:
            if (r0 == 0) goto L_0x0027
            int r6 = r0.length()
            if (r6 <= 0) goto L_0x0027
            r6 = 1
            goto L_0x0028
        L_0x0027:
            r6 = 0
        L_0x0028:
            gnu.mapping.Namespace r7 = gnu.expr.Keyword.keywordNamespace
            r8 = 58
            if (r1 != r7) goto L_0x003c
            char r0 = r9.language
            r1 = 67
            if (r0 == r1) goto L_0x0038
            r1 = 69
            if (r0 != r1) goto L_0x005c
        L_0x0038:
            r11.write((int) r8)
            goto L_0x005b
        L_0x003c:
            if (r6 != 0) goto L_0x0040
            if (r5 == 0) goto L_0x005b
        L_0x0040:
            if (r6 == 0) goto L_0x0045
            r9.writeSymbol((java.lang.String) r0, (gnu.lists.Consumer) r11, (boolean) r12)
        L_0x0045:
            if (r5 == 0) goto L_0x0058
            if (r12 != 0) goto L_0x004b
            if (r6 != 0) goto L_0x0058
        L_0x004b:
            r0 = 123(0x7b, float:1.72E-43)
            r11.write((int) r0)
            r11.write((java.lang.String) r2)
            r0 = 125(0x7d, float:1.75E-43)
            r11.write((int) r0)
        L_0x0058:
            r11.write((int) r8)
        L_0x005b:
            r3 = 0
        L_0x005c:
            java.lang.String r10 = r10.getName()
            r9.writeSymbol((java.lang.String) r10, (gnu.lists.Consumer) r11, (boolean) r12)
            if (r3 == 0) goto L_0x0068
            r11.write((int) r8)
        L_0x0068:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.functions.DisplayFormat.writeSymbol(gnu.mapping.Symbol, gnu.lists.Consumer, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void writeSymbol(String str, Consumer consumer, boolean z) {
        if (!z || r5rsIdentifierMinusInteriorColons.matcher(str).matches()) {
            write(str, consumer);
            return;
        }
        int length = str.length();
        if (length == 0) {
            write("||", consumer);
            return;
        }
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '|') {
                write(z2 ? "|\\" : "\\", consumer);
                z2 = false;
            } else if (!z2) {
                consumer.write(124);
                z2 = true;
            }
            consumer.write((int) charAt);
        }
        if (z2) {
            consumer.write(124);
        }
    }
}
