package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.Vector;

public class SyntaxPattern extends Pattern implements Externalizable {
    static final int MATCH_ANY = 3;
    static final int MATCH_ANY_CAR = 7;
    static final int MATCH_EQUALS = 2;
    static final int MATCH_IGNORE = 24;
    static final int MATCH_LENGTH = 6;
    static final int MATCH_LREPEAT = 5;
    static final int MATCH_MISC = 0;
    static final int MATCH_NIL = 8;
    static final int MATCH_PAIR = 4;
    static final int MATCH_VECTOR = 16;
    static final int MATCH_WIDE = 1;
    Object[] literals;
    String program;
    int varCount;

    public int varCount() {
        return this.varCount;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        return match(obj, objArr, i, 0, (SyntaxForm) null);
    }

    public SyntaxPattern(String str, Object[] objArr, int i) {
        this.program = str;
        this.literals = objArr;
        this.varCount = i;
    }

    public SyntaxPattern(Object obj, Object[] objArr, Translator translator) {
        this(new StringBuffer(), obj, (SyntaxForm) null, objArr, translator);
    }

    SyntaxPattern(StringBuffer stringBuffer, Object obj, SyntaxForm syntaxForm, Object[] objArr, Translator translator) {
        Vector vector = new Vector();
        translate(obj, stringBuffer, objArr, 0, vector, (SyntaxForm) null, 0, translator);
        this.program = stringBuffer.toString();
        Object[] objArr2 = new Object[vector.size()];
        this.literals = objArr2;
        vector.copyInto(objArr2);
        this.varCount = translator.patternScope.pattern_names.size();
    }

    public void disassemble() {
        disassemble(OutPort.errDefault(), (Translator) Compilation.getCurrent(), 0, this.program.length());
    }

    public void disassemble(PrintWriter printWriter, Translator translator) {
        disassemble(printWriter, translator, 0, this.program.length());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disassemble(java.io.PrintWriter r9, kawa.lang.Translator r10, int r11, int r12) {
        /*
            r8 = this;
            if (r10 == 0) goto L_0x000b
            kawa.lang.PatternScope r0 = r10.patternScope
            if (r0 == 0) goto L_0x000b
            kawa.lang.PatternScope r0 = r10.patternScope
            java.util.Vector r0 = r0.pattern_names
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            r1 = 0
        L_0x000d:
            r2 = 0
        L_0x000e:
            if (r11 >= r12) goto L_0x01b8
            java.lang.String r3 = r8.program
            char r3 = r3.charAt(r11)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = " "
            r4.append(r5)
            r4.append(r11)
            java.lang.String r6 = ": "
            r4.append(r6)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            r9.print(r4)
            int r11 = r11 + 1
            r4 = r3 & 7
            int r2 = r2 << 13
            int r6 = r3 >> 3
            r2 = r2 | r6
            r6 = 3
            java.lang.String r7 = "]"
            switch(r4) {
                case 0: goto L_0x015a;
                case 1: goto L_0x0144;
                case 2: goto L_0x011a;
                case 3: goto L_0x00e8;
                case 4: goto L_0x00cf;
                case 5: goto L_0x006b;
                case 6: goto L_0x0043;
                case 7: goto L_0x00e8;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x019a
        L_0x0043:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " - LENGTH "
            r3.append(r4)
            int r4 = r2 >> 1
            r3.append(r4)
            java.lang.String r4 = " pairs. "
            r3.append(r4)
            r2 = r2 & 1
            if (r2 != 0) goto L_0x005e
            java.lang.String r2 = "pure list"
            goto L_0x0060
        L_0x005e:
            java.lang.String r2 = "impure list"
        L_0x0060:
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r9.println(r2)
            goto L_0x000d
        L_0x006b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " - LREPEAT["
            r3.append(r4)
            r3.append(r2)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r9.println(r3)
            int r2 = r2 + r11
            r8.disassemble(r9, r10, r11, r2)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r5)
            r11.append(r2)
            java.lang.String r3 = ": - repeat first var:"
            r11.append(r3)
            java.lang.String r3 = r8.program
            int r4 = r2 + 1
            char r2 = r3.charAt(r2)
            int r2 = r2 >> r6
            r11.append(r2)
            java.lang.String r11 = r11.toString()
            r9.println(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r5)
            r11.append(r4)
            java.lang.String r2 = ": - repeast nested vars:"
            r11.append(r2)
            java.lang.String r2 = r8.program
            int r3 = r4 + 1
            char r2 = r2.charAt(r4)
            int r2 = r2 >> r6
            r11.append(r2)
            java.lang.String r11 = r11.toString()
            r9.println(r11)
            r11 = r3
            goto L_0x000d
        L_0x00cf:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " - PAIR["
            r3.append(r4)
            r3.append(r2)
            r3.append(r7)
            java.lang.String r2 = r3.toString()
            r9.println(r2)
            goto L_0x000d
        L_0x00e8:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            if (r4 != r6) goto L_0x00f2
            java.lang.String r4 = " - ANY["
            goto L_0x00f4
        L_0x00f2:
            java.lang.String r4 = " - ANY_CAR["
        L_0x00f4:
            r3.append(r4)
            r3.append(r2)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r9.print(r3)
            if (r0 == 0) goto L_0x0115
            if (r2 < 0) goto L_0x0115
            int r3 = r0.size()
            if (r2 >= r3) goto L_0x0115
            java.lang.Object r2 = r0.elementAt(r2)
            r9.print(r2)
        L_0x0115:
            r9.println()
            goto L_0x000d
        L_0x011a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " - EQUALS["
            r3.append(r4)
            r3.append(r2)
            r3.append(r7)
            java.lang.String r3 = r3.toString()
            r9.print(r3)
            java.lang.Object[] r3 = r8.literals
            if (r3 == 0) goto L_0x013f
            if (r2 < 0) goto L_0x013f
            int r4 = r3.length
            if (r2 >= r4) goto L_0x013f
            r2 = r3[r2]
            r9.print(r2)
        L_0x013f:
            r9.println()
            goto L_0x000d
        L_0x0144:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = " - WIDE "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            r9.println(r3)
            goto L_0x000e
        L_0x015a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[misc ch:"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r6 = " n:"
            r5.append(r6)
            r6 = 8
            r5.append(r6)
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            r9.print(r5)
            if (r3 != r6) goto L_0x0184
            java.lang.String r2 = " - NIL"
            r9.println(r2)
            goto L_0x000d
        L_0x0184:
            r5 = 16
            if (r3 != r5) goto L_0x018f
            java.lang.String r2 = " - VECTOR"
            r9.println(r2)
            goto L_0x000d
        L_0x018f:
            r5 = 24
            if (r3 != r5) goto L_0x019a
            java.lang.String r2 = " - IGNORE"
            r9.println(r2)
            goto L_0x000d
        L_0x019a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = " - "
            r3.append(r5)
            r3.append(r4)
            r4 = 47
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r9.println(r2)
            goto L_0x000d
        L_0x01b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.disassemble(java.io.PrintWriter, kawa.lang.Translator, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void translate(Object obj, StringBuffer stringBuffer, Object[] objArr, int i, Vector vector, SyntaxForm syntaxForm, char c, Translator translator) {
        ScopeExp scopeExp;
        Object obj2;
        boolean z;
        Object obj3;
        Object obj4;
        StringBuffer stringBuffer2 = stringBuffer;
        Object[] objArr2 = objArr;
        Vector vector2 = vector;
        Translator translator2 = translator;
        PatternScope patternScope = translator2.patternScope;
        Vector vector3 = patternScope.pattern_names;
        char c2 = 'V';
        Object obj5 = obj;
        SyntaxForm syntaxForm2 = syntaxForm;
        char c3 = c;
        while (true) {
            if (obj5 instanceof SyntaxForm) {
                syntaxForm2 = (SyntaxForm) obj5;
                obj5 = syntaxForm2.getDatum();
            } else {
                int i2 = 3;
                if (obj5 instanceof Pair) {
                    Object pushPositionOf = translator2.pushPositionOf(obj5);
                    try {
                        int length = stringBuffer.length();
                        stringBuffer2.append(4);
                        Pair pair = (Pair) obj5;
                        Object cdr = pair.getCdr();
                        SyntaxForm syntaxForm3 = syntaxForm2;
                        while (cdr instanceof SyntaxForm) {
                            syntaxForm3 = (SyntaxForm) cdr;
                            cdr = syntaxForm3.getDatum();
                        }
                        if (!(cdr instanceof Pair) || !translator2.matches(((Pair) cdr).getCar(), "...")) {
                            obj3 = cdr;
                            z = false;
                        } else {
                            Object cdr2 = ((Pair) cdr).getCdr();
                            while (cdr2 instanceof SyntaxForm) {
                                syntaxForm3 = (SyntaxForm) cdr2;
                                cdr2 = syntaxForm3.getDatum();
                            }
                            obj3 = cdr2;
                            z = true;
                        }
                        int size = vector3.size();
                        char c4 = c3 == 'P' ? 0 : c3;
                        Object car = pair.getCar();
                        int i3 = z ? i + 1 : i;
                        char c5 = c4 == c2 ? 0 : 'P';
                        char c6 = c4;
                        int i4 = length;
                        PatternScope patternScope2 = patternScope;
                        obj2 = pushPositionOf;
                        Object obj6 = obj3;
                        try {
                            translate(car, stringBuffer, objArr, i3, vector, syntaxForm2, c5, translator);
                            int size2 = vector3.size() - size;
                            int length2 = (((stringBuffer.length() - i4) - 1) << 3) | (z ? 5 : 4);
                            stringBuffer2.setCharAt(length2 > 65535 ? i4 + insertInt(i4, stringBuffer2, (length2 >> 13) + 1) : i4, (char) length2);
                            int listLength = Translator.listLength(obj6);
                            if (listLength == Integer.MIN_VALUE) {
                                translator2.syntaxError("cyclic pattern list");
                                translator2.popPositionOf(obj2);
                                return;
                            }
                            if (z) {
                                addInt(stringBuffer2, size << 3);
                                addInt(stringBuffer2, size2 << 3);
                                obj4 = obj6;
                                if (obj4 == LList.Empty) {
                                    stringBuffer2.append(8);
                                    translator2.popPositionOf(obj2);
                                    return;
                                }
                                addInt(stringBuffer2, ((listLength >= 0 ? listLength << 1 : ((-listLength) << 1) - 1) << 3) | 6);
                            } else {
                                obj4 = obj6;
                            }
                            translator2.popPositionOf(obj2);
                            Vector vector4 = vector;
                            obj5 = obj4;
                            syntaxForm2 = syntaxForm3;
                            c3 = c6;
                            patternScope = patternScope2;
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        obj2 = pushPositionOf;
                        translator2.popPositionOf(obj2);
                        throw th;
                    }
                } else {
                    PatternScope patternScope3 = patternScope;
                    if (obj5 instanceof Symbol) {
                        int length3 = objArr2.length;
                        while (true) {
                            length3--;
                            if (length3 >= 0) {
                                ScopeExp currentScope = translator.currentScope();
                                if (syntaxForm2 == null) {
                                    scopeExp = currentScope;
                                } else {
                                    scopeExp = syntaxForm2.getScope();
                                }
                                Object obj7 = objArr2[length3];
                                if (obj7 instanceof SyntaxForm) {
                                    SyntaxForm syntaxForm4 = (SyntaxForm) obj7;
                                    Object datum = syntaxForm4.getDatum();
                                    TemplateScope scope = syntaxForm4.getScope();
                                    obj7 = datum;
                                    currentScope = scope;
                                } else if (translator2.currentMacroDefinition != null) {
                                    currentScope = translator2.currentMacroDefinition.getCapturedScope();
                                }
                                if (literalIdentifierEq(obj5, scopeExp, obj7, currentScope)) {
                                    Vector vector5 = vector;
                                    int indexOf = SyntaxTemplate.indexOf(vector5, obj5);
                                    if (indexOf < 0) {
                                        indexOf = vector.size();
                                        vector5.addElement(obj5);
                                    }
                                    addInt(stringBuffer2, (indexOf << 3) | 2);
                                    return;
                                }
                                Vector vector6 = vector;
                            } else {
                                if (vector3.contains(obj5)) {
                                    translator2.syntaxError("duplicated pattern variable " + obj5);
                                }
                                int size3 = vector3.size();
                                vector3.addElement(obj5);
                                int i5 = c3 == 'P' ? 1 : 0;
                                PatternScope patternScope4 = patternScope3;
                                patternScope4.patternNesting.append((char) ((i << 1) + i5));
                                Declaration addDeclaration = patternScope4.addDeclaration(obj5);
                                addDeclaration.setLocation(translator2);
                                translator2.push(addDeclaration);
                                int i6 = size3 << 3;
                                if (i5 != 0) {
                                    i2 = 7;
                                }
                                addInt(stringBuffer2, i6 | i2);
                                return;
                            }
                        }
                    } else {
                        Vector vector7 = vector;
                        PatternScope patternScope5 = patternScope3;
                        if (obj5 == LList.Empty) {
                            stringBuffer2.append(8);
                            return;
                        } else if (obj5 instanceof FVector) {
                            stringBuffer2.append(16);
                            obj5 = LList.makeList((FVector) obj5);
                            patternScope = patternScope5;
                            Vector vector8 = vector7;
                            c3 = 'V';
                        } else {
                            int indexOf2 = SyntaxTemplate.indexOf(vector7, obj5);
                            if (indexOf2 < 0) {
                                indexOf2 = vector.size();
                                vector7.addElement(obj5);
                            }
                            addInt(stringBuffer2, (indexOf2 << 3) | 2);
                            return;
                        }
                    }
                }
                c2 = 'V';
            }
        }
    }

    private static void addInt(StringBuffer stringBuffer, int i) {
        if (i > 65535) {
            addInt(stringBuffer, (i << 13) + 1);
        }
        stringBuffer.append((char) i);
    }

    private static int insertInt(int i, StringBuffer stringBuffer, int i2) {
        if (i2 > 65535) {
            i += insertInt(i, stringBuffer, (i2 << 13) + 1);
        }
        stringBuffer.insert(i, (char) i2);
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public boolean match_car(Pair pair, Object[] objArr, int i, int i2, SyntaxForm syntaxForm) {
        char c;
        int i3 = i2 + 1;
        char charAt = this.program.charAt(i2);
        int i4 = charAt >> 3;
        while (true) {
            c = charAt & 7;
            if (c != 1) {
                break;
            }
            int i5 = i3 + 1;
            char charAt2 = this.program.charAt(i3);
            i4 = (charAt2 >> 3) | (i4 << 13);
            charAt = charAt2;
            i3 = i5;
        }
        if (c == 7) {
            objArr[i + i4] = (syntaxForm == null || (pair.getCar() instanceof SyntaxForm)) ? pair : Translator.makePair(pair, SyntaxForms.fromDatum(pair.getCar(), syntaxForm), pair.getCdr());
            return true;
        }
        return match(pair.getCar(), objArr, i, i2, syntaxForm);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(java.lang.Object r22, java.lang.Object[] r23, int r24, int r25, kawa.lang.SyntaxForm r26) {
        /*
            r21 = this;
            r6 = r21
            r7 = 0
            r0 = r22
            r1 = r25
            r8 = r26
        L_0x0009:
            r2 = 0
        L_0x000a:
            boolean r3 = r0 instanceof kawa.lang.SyntaxForm
            if (r3 == 0) goto L_0x0016
            r8 = r0
            kawa.lang.SyntaxForm r8 = (kawa.lang.SyntaxForm) r8
            java.lang.Object r0 = r8.getDatum()
            goto L_0x000a
        L_0x0016:
            java.lang.String r4 = r6.program
            int r9 = r1 + 1
            char r1 = r4.charAt(r1)
            r4 = r1 & 7
            int r2 = r2 << 13
            int r5 = r1 >> 3
            r10 = r2 | r5
            r2 = 8
            r11 = 1
            switch(r4) {
                case 0: goto L_0x01ea;
                case 1: goto L_0x01e6;
                case 2: goto L_0x0196;
                case 3: goto L_0x018b;
                case 4: goto L_0x016b;
                case 5: goto L_0x007b;
                case 6: goto L_0x004c;
                case 7: goto L_0x002c;
                case 8: goto L_0x0046;
                default: goto L_0x002c;
            }
        L_0x002c:
            r21.disassemble()
            java.lang.Error r0 = new java.lang.Error
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unrecognized pattern opcode @pc:"
            r1.append(r2)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0046:
            gnu.lists.LList r1 = gnu.lists.LList.Empty
            if (r0 != r1) goto L_0x004b
            r7 = 1
        L_0x004b:
            return r7
        L_0x004c:
            int r1 = r10 >> 1
            r2 = r0
            r3 = 0
        L_0x0050:
            boolean r4 = r2 instanceof kawa.lang.SyntaxForm
            if (r4 == 0) goto L_0x005b
            kawa.lang.SyntaxForm r2 = (kawa.lang.SyntaxForm) r2
            java.lang.Object r2 = r2.getDatum()
            goto L_0x0050
        L_0x005b:
            if (r3 != r1) goto L_0x006d
            r1 = r10 & 1
            if (r1 != 0) goto L_0x0066
            gnu.lists.LList r1 = gnu.lists.LList.Empty
            if (r2 == r1) goto L_0x006b
            goto L_0x006a
        L_0x0066:
            boolean r1 = r2 instanceof gnu.lists.Pair
            if (r1 == 0) goto L_0x006b
        L_0x006a:
            return r7
        L_0x006b:
            r1 = r9
            goto L_0x0009
        L_0x006d:
            boolean r4 = r2 instanceof gnu.lists.Pair
            if (r4 == 0) goto L_0x007a
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2
            java.lang.Object r2 = r2.getCdr()
            int r3 = r3 + 1
            goto L_0x0050
        L_0x007a:
            return r7
        L_0x007b:
            int r10 = r10 + r9
            java.lang.String r1 = r6.program
            int r3 = r10 + 1
            char r1 = r1.charAt(r10)
            int r4 = r1 >> 3
        L_0x0086:
            r5 = r1 & 7
            if (r5 != r11) goto L_0x009a
            int r1 = r4 << 13
            java.lang.String r4 = r6.program
            int r5 = r3 + 1
            char r3 = r4.charAt(r3)
            int r4 = r3 >> 3
            r4 = r4 | r1
            r1 = r3
            r3 = r5
            goto L_0x0086
        L_0x009a:
            int r10 = r4 + r24
            java.lang.String r4 = r6.program
            int r5 = r3 + 1
            char r3 = r4.charAt(r3)
            int r3 = r3 >> 3
            r12 = r3
        L_0x00a7:
            r1 = r1 & 7
            if (r1 != r11) goto L_0x00bc
            int r1 = r12 << 13
            java.lang.String r3 = r6.program
            int r4 = r5 + 1
            char r3 = r3.charAt(r5)
            int r5 = r3 >> 3
            r12 = r1 | r5
            r1 = r3
            r5 = r4
            goto L_0x00a7
        L_0x00bc:
            java.lang.String r1 = r6.program
            int r3 = r5 + 1
            char r1 = r1.charAt(r5)
            if (r1 != r2) goto L_0x00ca
            r14 = r3
            r13 = 1
            r15 = 0
            goto L_0x00f0
        L_0x00ca:
            int r2 = r1 >> 3
        L_0x00cc:
            r1 = r1 & 7
            if (r1 != r11) goto L_0x00e4
            int r1 = r2 << 13
            java.lang.String r2 = r6.program
            int r4 = r3 + 1
            char r2 = r2.charAt(r3)
            int r3 = r2 >> 3
            r1 = r1 | r3
            r3 = r4
            r20 = r2
            r2 = r1
            r1 = r20
            goto L_0x00cc
        L_0x00e4:
            r1 = r2 & 1
            if (r1 == 0) goto L_0x00ea
            r1 = 0
            goto L_0x00eb
        L_0x00ea:
            r1 = 1
        L_0x00eb:
            int r2 = r2 >> 1
            r13 = r1
            r15 = r2
            r14 = r3
        L_0x00f0:
            int r1 = kawa.lang.Translator.listLength(r0)
            if (r1 < 0) goto L_0x00f8
            r2 = 1
            goto L_0x00fb
        L_0x00f8:
            int r1 = -1 - r1
            r2 = 0
        L_0x00fb:
            if (r1 < r15) goto L_0x016a
            if (r13 == 0) goto L_0x0103
            if (r2 != 0) goto L_0x0103
            goto L_0x016a
        L_0x0103:
            int r5 = r1 - r15
            java.lang.Object[][] r4 = new java.lang.Object[r12][]
            r1 = 0
        L_0x0108:
            if (r1 >= r12) goto L_0x0111
            java.lang.Object[] r2 = new java.lang.Object[r5]
            r4[r1] = r2
            int r1 = r1 + 1
            goto L_0x0108
        L_0x0111:
            r3 = 0
        L_0x0112:
            if (r3 >= r5) goto L_0x0154
        L_0x0114:
            boolean r1 = r0 instanceof kawa.lang.SyntaxForm
            if (r1 == 0) goto L_0x0120
            r8 = r0
            kawa.lang.SyntaxForm r8 = (kawa.lang.SyntaxForm) r8
            java.lang.Object r0 = r8.getDatum()
            goto L_0x0114
        L_0x0120:
            r16 = r0
            gnu.lists.Pair r16 = (gnu.lists.Pair) r16
            r0 = r21
            r1 = r16
            r2 = r23
            r17 = r3
            r3 = r24
            r18 = r4
            r4 = r9
            r19 = r5
            r5 = r8
            boolean r0 = r0.match_car(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x013b
            return r7
        L_0x013b:
            java.lang.Object r0 = r16.getCdr()
            r1 = 0
        L_0x0140:
            if (r1 >= r12) goto L_0x014d
            r2 = r18[r1]
            int r3 = r10 + r1
            r3 = r23[r3]
            r2[r17] = r3
            int r1 = r1 + 1
            goto L_0x0140
        L_0x014d:
            int r3 = r17 + 1
            r4 = r18
            r5 = r19
            goto L_0x0112
        L_0x0154:
            r18 = r4
            r1 = 0
        L_0x0157:
            if (r1 >= r12) goto L_0x0162
            int r2 = r10 + r1
            r3 = r18[r1]
            r23[r2] = r3
            int r1 = r1 + 1
            goto L_0x0157
        L_0x0162:
            if (r15 != 0) goto L_0x0167
            if (r13 == 0) goto L_0x0167
            return r11
        L_0x0167:
            r1 = r14
            goto L_0x0009
        L_0x016a:
            return r7
        L_0x016b:
            boolean r1 = r0 instanceof gnu.lists.Pair
            if (r1 != 0) goto L_0x0170
            return r7
        L_0x0170:
            r11 = r0
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            r0 = r21
            r1 = r11
            r2 = r23
            r3 = r24
            r4 = r9
            r5 = r8
            boolean r0 = r0.match_car(r1, r2, r3, r4, r5)
            if (r0 != 0) goto L_0x0183
            return r7
        L_0x0183:
            int r1 = r9 + r10
            java.lang.Object r0 = r11.getCdr()
            goto L_0x0009
        L_0x018b:
            if (r8 == 0) goto L_0x0191
            java.lang.Object r0 = kawa.lang.SyntaxForms.fromDatum(r0, r8)
        L_0x0191:
            int r1 = r24 + r10
            r23[r1] = r0
            return r11
        L_0x0196:
            java.lang.Object[] r1 = r6.literals
            r1 = r1[r10]
            gnu.expr.Compilation r2 = gnu.expr.Compilation.getCurrent()
            kawa.lang.Translator r2 = (kawa.lang.Translator) r2
            boolean r4 = r1 instanceof kawa.lang.SyntaxForm
            if (r4 == 0) goto L_0x01b4
            kawa.lang.SyntaxForm r1 = (kawa.lang.SyntaxForm) r1
            java.lang.Object r4 = r1.getDatum()
            kawa.lang.TemplateScope r1 = r1.getScope()
            r20 = r4
            r4 = r1
            r1 = r20
            goto L_0x01c4
        L_0x01b4:
            kawa.lang.Syntax r4 = r2.getCurrentSyntax()
            boolean r5 = r4 instanceof kawa.lang.Macro
            if (r5 == 0) goto L_0x01c3
            kawa.lang.Macro r4 = (kawa.lang.Macro) r4
            gnu.expr.ScopeExp r4 = r4.getCapturedScope()
            goto L_0x01c4
        L_0x01c3:
            r4 = 0
        L_0x01c4:
            if (r3 == 0) goto L_0x01d6
            kawa.lang.SyntaxForm r0 = (kawa.lang.SyntaxForm) r0
            java.lang.Object r2 = r0.getDatum()
            kawa.lang.TemplateScope r0 = r0.getScope()
            r20 = r2
            r2 = r0
            r0 = r20
            goto L_0x01e1
        L_0x01d6:
            if (r8 != 0) goto L_0x01dd
            gnu.expr.ScopeExp r2 = r2.currentScope()
            goto L_0x01e1
        L_0x01dd:
            kawa.lang.TemplateScope r2 = r8.getScope()
        L_0x01e1:
            boolean r0 = literalIdentifierEq(r1, r4, r0, r2)
            return r0
        L_0x01e6:
            r1 = r9
            r2 = r10
            goto L_0x000a
        L_0x01ea:
            if (r1 != r2) goto L_0x01f2
            gnu.lists.LList r1 = gnu.lists.LList.Empty
            if (r0 != r1) goto L_0x01f1
            r7 = 1
        L_0x01f1:
            return r7
        L_0x01f2:
            r2 = 16
            if (r1 != r2) goto L_0x020e
            boolean r1 = r0 instanceof gnu.lists.FVector
            if (r1 != 0) goto L_0x01fb
            return r7
        L_0x01fb:
            gnu.lists.FVector r0 = (gnu.lists.FVector) r0
            gnu.lists.LList r1 = gnu.lists.LList.makeList(r0)
            r0 = r21
            r2 = r23
            r3 = r24
            r4 = r9
            r5 = r8
            boolean r0 = r0.match(r1, r2, r3, r4, r5)
            return r0
        L_0x020e:
            r0 = 24
            if (r1 != r0) goto L_0x0213
            return r11
        L_0x0213:
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "unknwon pattern opcode"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.match(java.lang.Object, java.lang.Object[], int, int, kawa.lang.SyntaxForm):boolean");
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.program);
        objectOutput.writeObject(this.literals);
        objectOutput.writeInt(this.varCount);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.literals = (Object[]) objectInput.readObject();
        this.program = (String) objectInput.readObject();
        this.varCount = objectInput.readInt();
    }

    public static Object[] allocVars(int i, Object[] objArr) {
        Object[] objArr2 = new Object[i];
        if (objArr != null) {
            System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        }
        return objArr2;
    }

    public static boolean literalIdentifierEq(Object obj, ScopeExp scopeExp, Object obj2, ScopeExp scopeExp2) {
        if (obj != obj2 && (obj == null || obj2 == null || !obj.equals(obj2))) {
            return false;
        }
        if (scopeExp == scopeExp2) {
            return true;
        }
        Declaration declaration = null;
        Declaration declaration2 = null;
        while (scopeExp != null && !(scopeExp instanceof ModuleExp)) {
            declaration2 = scopeExp.lookup(obj);
            if (declaration2 != null) {
                break;
            }
            scopeExp = scopeExp.outer;
        }
        while (scopeExp2 != null && !(scopeExp2 instanceof ModuleExp)) {
            declaration = scopeExp2.lookup(obj2);
            if (declaration != null) {
                break;
            }
            scopeExp2 = scopeExp2.outer;
        }
        if (declaration2 == declaration) {
            return true;
        }
        return false;
    }

    public static Object[] getLiteralsList(Object obj, SyntaxForm syntaxForm, Translator translator) {
        Object pushPositionOf = translator.pushPositionOf(obj);
        int listLength = Translator.listLength(obj);
        if (listLength < 0) {
            translator.error('e', "missing or malformed literals list");
            listLength = 0;
        }
        Object[] objArr = new Object[(listLength + 1)];
        for (int i = 1; i <= listLength; i++) {
            while (obj instanceof SyntaxForm) {
                obj = ((SyntaxForm) obj).getDatum();
            }
            Pair pair = (Pair) obj;
            translator.pushPositionOf(pair);
            Object car = pair.getCar();
            Object datum = car instanceof SyntaxForm ? ((SyntaxForm) car).getDatum() : car;
            if (!(datum instanceof Symbol)) {
                translator.error('e', "non-symbol '" + datum + "' in literals list");
            }
            objArr[i] = car;
            obj = pair.getCdr();
        }
        translator.popPositionOf(pushPositionOf);
        return objArr;
    }

    public void print(Consumer consumer) {
        consumer.write("#<syntax-pattern>");
    }
}
