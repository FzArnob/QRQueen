package kawa.lang;

import gnu.expr.Compilation;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Vector;

public class SyntaxTemplate implements Externalizable {
    static final int BUILD_CONS = 1;
    static final int BUILD_DOTS = 5;
    static final int BUILD_LIST1 = 8;
    static final int BUILD_LITERAL = 4;
    static final int BUILD_MISC = 0;
    static final int BUILD_NIL = 16;
    static final int BUILD_SYNTAX = 24;
    static final int BUILD_VAR = 2;
    static final int BUILD_VAR_CAR = 3;
    static final int BUILD_VECTOR = 40;
    static final int BUILD_WIDE = 7;
    static final String dots3 = "...";
    Object[] literal_values;
    int max_nesting;
    String patternNesting;
    String template_program;

    protected SyntaxTemplate() {
    }

    public SyntaxTemplate(String str, String str2, Object[] objArr, int i) {
        this.patternNesting = str;
        this.template_program = str2;
        this.literal_values = objArr;
        this.max_nesting = i;
    }

    public SyntaxTemplate(Object obj, SyntaxForm syntaxForm, Translator translator) {
        this.patternNesting = (translator == null || translator.patternScope == null) ? "" : translator.patternScope.patternNesting.toString();
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        convert_template(obj, syntaxForm, stringBuffer, 0, vector, new IdentityHashMap(), false, translator);
        this.template_program = stringBuffer.toString();
        Object[] objArr = new Object[vector.size()];
        this.literal_values = objArr;
        vector.copyInto(objArr);
    }

    public int convert_template(Object obj, SyntaxForm syntaxForm, StringBuffer stringBuffer, int i, Vector vector, Object obj2, boolean z, Translator translator) {
        Object obj3;
        Vector vector2;
        Object obj4;
        int indexOf;
        int i2;
        int i3;
        int i4;
        StringBuffer stringBuffer2 = stringBuffer;
        int i5 = i;
        Vector vector3 = vector;
        Translator translator2 = translator;
        Object obj5 = obj;
        SyntaxForm syntaxForm2 = syntaxForm;
        while (obj5 instanceof SyntaxForm) {
            syntaxForm2 = (SyntaxForm) obj5;
            obj5 = syntaxForm2.getDatum();
        }
        boolean z2 = obj5 instanceof Pair;
        if (z2 || (obj5 instanceof FVector)) {
            IdentityHashMap identityHashMap = (IdentityHashMap) obj2;
            if (identityHashMap.containsKey(obj5)) {
                translator2.syntaxError("self-referential (cyclic) syntax template");
                return -2;
            }
            identityHashMap.put(obj5, obj5);
        }
        int i6 = 3;
        if (z2) {
            Pair pair = (Pair) obj5;
            int length = stringBuffer.length();
            Object car = pair.getCar();
            if (translator2.matches(car, dots3)) {
                Object stripSyntax = Translator.stripSyntax(pair.getCdr());
                if (stripSyntax instanceof Pair) {
                    Pair pair2 = (Pair) stripSyntax;
                    if (pair2.getCar() == dots3 && pair2.getCdr() == LList.Empty) {
                        obj5 = dots3;
                        obj3 = obj5;
                        vector2 = vector3;
                    }
                }
            }
            int size = vector.size();
            stringBuffer2.append(8);
            Object cdr = pair.getCdr();
            int i7 = 0;
            while (cdr instanceof Pair) {
                Pair pair3 = (Pair) cdr;
                if (!translator2.matches(pair3.getCar(), dots3)) {
                    break;
                }
                i7++;
                cdr = pair3.getCdr();
                stringBuffer2.append(5);
            }
            int i8 = i5 + i7;
            Object obj6 = cdr;
            Object obj7 = obj5;
            int i9 = size;
            int i10 = length;
            obj3 = dots3;
            int i11 = i8;
            int convert_template = convert_template(car, syntaxForm2, stringBuffer, i8, vector, obj2, false, translator);
            if (obj6 != LList.Empty) {
                int i12 = i10;
                stringBuffer2.setCharAt(i12, (char) ((((stringBuffer.length() - i12) - 1) << 3) + 1));
                i3 = i12;
                i2 = convert_template;
                i4 = convert_template(obj6, syntaxForm2, stringBuffer, i, vector, obj2, z, translator);
            } else {
                i2 = convert_template;
                i3 = i10;
                i4 = -2;
            }
            if (i7 > 0) {
                if (i2 < 0) {
                    translator2.syntaxError("... follows template with no suitably-nested pattern variable");
                }
                while (true) {
                    i7--;
                    if (i7 < 0) {
                        break;
                    }
                    stringBuffer2.setCharAt(i3 + i7 + 1, (char) ((i2 << 3) + 5));
                    int i13 = i11;
                    if (i13 >= this.max_nesting) {
                        this.max_nesting = i13;
                    }
                    i11 = i13;
                }
            }
            if (i2 >= 0) {
                return i2;
            }
            if (i4 >= 0) {
                return i4;
            }
            if (i2 == -1 || i4 == -1) {
                return -1;
            }
            if (z) {
                return -2;
            }
            vector2 = vector;
            vector2.setSize(i9);
            stringBuffer2.setLength(i3);
            obj5 = obj7;
        } else {
            obj3 = dots3;
            vector2 = vector3;
            if (obj5 instanceof FVector) {
                stringBuffer2.append('(');
                return convert_template(LList.makeList((FVector) obj5), syntaxForm2, stringBuffer, i, vector, obj2, true, translator);
            } else if (obj5 == LList.Empty) {
                stringBuffer2.append(16);
                return -2;
            } else if ((obj5 instanceof Symbol) && translator2 != null && translator2.patternScope != null && (indexOf = indexOf(translator2.patternScope.pattern_names, obj5)) >= 0) {
                char charAt = this.patternNesting.charAt(indexOf);
                if ((charAt & 1) == 0) {
                    i6 = 2;
                }
                int i14 = charAt >> 1;
                int i15 = i;
                if (i14 > i15) {
                    translator2.syntaxError("inconsistent ... nesting of " + obj5);
                }
                stringBuffer2.append((char) (i6 + (indexOf * 8)));
                if (i14 == i15) {
                    return indexOf;
                }
                return -1;
            }
        }
        int indexOf2 = indexOf(vector2, obj5);
        if (indexOf2 < 0) {
            indexOf2 = vector.size();
            vector2.addElement(obj5);
        }
        if (obj5 instanceof Symbol) {
            translator2.noteAccess(obj5, translator.currentScope());
        }
        if (!(obj5 instanceof SyntaxForm)) {
            obj4 = obj3;
            if (obj5 != obj4) {
                stringBuffer2.append(24);
            }
        } else {
            obj4 = obj3;
        }
        stringBuffer2.append((char) ((indexOf2 * 8) + 4));
        if (obj5 == obj4) {
            return -1;
        }
        return -2;
    }

    static int indexOf(Vector vector, Object obj) {
        int size = vector.size();
        for (int i = 0; i < size; i++) {
            if (vector.elementAt(i) == obj) {
                return i;
            }
        }
        return -1;
    }

    private int get_count(Object obj, int i, int[] iArr) {
        for (int i2 = 0; i2 < i; i2++) {
            obj = obj[iArr[i2]];
        }
        return obj.length;
    }

    public Object execute(Object[] objArr, TemplateScope templateScope) {
        return execute(0, objArr, 0, new int[this.max_nesting], (Translator) Compilation.getCurrent(), templateScope);
    }

    public Object execute(Object[] objArr, Translator translator, TemplateScope templateScope) {
        return execute(0, objArr, 0, new int[this.max_nesting], translator, templateScope);
    }

    /* access modifiers changed from: package-private */
    public Object get_var(int i, Object[] objArr, int[] iArr) {
        Object obj = objArr[i];
        if (i < this.patternNesting.length()) {
            int charAt = this.patternNesting.charAt(i) >> 1;
            for (int i2 = 0; i2 < charAt; i2++) {
                obj = obj[iArr[i2]];
            }
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public LList executeToList(int i, Object[] objArr, int i2, int[] iArr, Translator translator, TemplateScope templateScope) {
        char c;
        Object[] objArr2 = objArr;
        int i3 = i2;
        int[] iArr2 = iArr;
        int i4 = i;
        char charAt = this.template_program.charAt(i4);
        int i5 = i4;
        while (true) {
            c = charAt & 7;
            if (c != 7) {
                break;
            }
            i5++;
            charAt = ((charAt - 7) << 13) | this.template_program.charAt(i5);
        }
        if (c == 3) {
            Pair pair = (Pair) get_var(charAt >> 3, objArr2, iArr2);
            return Translator.makePair(pair, pair.getCar(), LList.Empty);
        } else if (c != 5) {
            return new Pair(execute(i, objArr, i2, iArr, translator, templateScope), LList.Empty);
        } else {
            int i6 = get_count(objArr2[charAt >> 3], i3, iArr2);
            int i7 = i5 + 1;
            LList lList = LList.Empty;
            Pair pair2 = null;
            for (int i8 = 0; i8 < i6; i8++) {
                iArr2[i3] = i8;
                LList executeToList = executeToList(i7, objArr, i3 + 1, iArr, translator, templateScope);
                if (pair2 == null) {
                    lList = executeToList;
                } else {
                    pair2.setCdrBackdoor(executeToList);
                }
                while (executeToList instanceof Pair) {
                    pair2 = (Pair) executeToList;
                    executeToList = (LList) pair2.getCdr();
                }
            }
            return lList;
        }
    }

    /* access modifiers changed from: package-private */
    public Object execute(int i, Object[] objArr, int i2, int[] iArr, Translator translator, TemplateScope templateScope) {
        char c;
        int i3 = i;
        char charAt = this.template_program.charAt(i3);
        while (true) {
            c = charAt & 7;
            if (c != 7) {
                break;
            }
            i3++;
            charAt = ((charAt - 7) << 13) | this.template_program.charAt(i3);
        }
        if (charAt == 8) {
            return executeToList(i3 + 1, objArr, i2, iArr, translator, templateScope);
        } else if (charAt == 16) {
            return LList.Empty;
        } else {
            if (charAt == 24) {
                Object execute = execute(i3 + 1, objArr, i2, iArr, translator, templateScope);
                return execute == LList.Empty ? execute : SyntaxForms.makeForm(execute, templateScope);
            }
            TemplateScope templateScope2 = templateScope;
            if (c == 1) {
                char c2 = charAt;
                Pair pair = null;
                Object obj = null;
                do {
                    int i4 = i3 + 1;
                    Object executeToList = executeToList(i4, objArr, i2, iArr, translator, templateScope);
                    if (pair == null) {
                        obj = executeToList;
                    } else {
                        pair.setCdrBackdoor(executeToList);
                    }
                    while (executeToList instanceof Pair) {
                        pair = executeToList;
                        executeToList = pair.getCdr();
                    }
                    i3 = i4 + (c2 >> 3);
                    c2 = this.template_program.charAt(i3);
                } while ((c2 & 7) == 1);
                Object execute2 = execute(i3, objArr, i2, iArr, translator, templateScope);
                if (pair == null) {
                    return execute2;
                }
                pair.setCdrBackdoor(execute2);
                return obj;
            } else if (charAt == '(') {
                return new FVector((List) (LList) execute(i3 + 1, objArr, i2, iArr, translator, templateScope));
            } else if (c == 4) {
                return this.literal_values[charAt >> 3];
            } else if ((charAt & 6) == 2) {
                Object obj2 = get_var(charAt >> 3, objArr, iArr);
                return c == 3 ? ((Pair) obj2).getCar() : obj2;
            } else {
                throw new Error("unknown template code: " + charAt + " at " + i3);
            }
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.patternNesting);
        objectOutput.writeObject(this.template_program);
        objectOutput.writeObject(this.literal_values);
        objectOutput.writeInt(this.max_nesting);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.patternNesting = (String) objectInput.readObject();
        this.template_program = (String) objectInput.readObject();
        this.literal_values = (Object[]) objectInput.readObject();
        this.max_nesting = objectInput.readInt();
    }
}
