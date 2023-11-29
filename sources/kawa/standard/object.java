package kawa.standard;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class object extends Syntax {
    public static final Keyword accessKeyword = Keyword.make("access");
    public static final Keyword allocationKeyword = Keyword.make("allocation");
    public static final Keyword classNameKeyword = Keyword.make("class-name");
    static final Symbol coloncolon = Namespace.EmptyNamespace.getSymbol("::");
    static final Keyword initKeyword = Keyword.make("init");
    static final Keyword init_formKeyword = Keyword.make("init-form");
    static final Keyword init_keywordKeyword = Keyword.make("init-keyword");
    static final Keyword init_valueKeyword = Keyword.make("init-value");
    static final Keyword initformKeyword = Keyword.make("initform");
    public static final Keyword interfaceKeyword = Keyword.make("interface");
    public static final object objectSyntax;
    public static final Keyword throwsKeyword = Keyword.make("throws");
    static final Keyword typeKeyword = Keyword.make(CommonProperties.TYPE);
    Lambda lambda;

    static {
        object object = new object(SchemeCompilation.lambda);
        objectSyntax = object;
        object.setName("object");
    }

    public object(Lambda lambda2) {
        this.lambda = lambda2;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        if (!(pair.getCdr() instanceof Pair)) {
            return translator.syntaxError("missing superclass specification in object");
        }
        Pair pair2 = (Pair) pair.getCdr();
        ObjectExp objectExp = new ObjectExp();
        if (pair2.getCar() instanceof FString) {
            if (!(pair2.getCdr() instanceof Pair)) {
                return translator.syntaxError("missing superclass specification after object class name");
            }
            pair2 = (Pair) pair2.getCdr();
        }
        Object[] scanClassDef = scanClassDef(pair2, objectExp, translator);
        if (scanClassDef != null) {
            rewriteClassDef(scanClassDef, translator);
        }
        return objectExp;
    }

    public Object[] scanClassDef(Pair pair, ClassExp classExp, Translator translator) {
        ClassExp classExp2;
        Object obj;
        long j;
        Object obj2;
        Object obj3;
        Object obj4;
        Object[] objArr;
        Object obj5;
        LambdaExp lambdaExp;
        Object obj6;
        Object obj7;
        Declaration declaration;
        Object obj8;
        Declaration declaration2;
        Pair pair2;
        Pair pair3;
        int i;
        Object obj9;
        Object obj10;
        Object obj11;
        int i2;
        Pair pair4;
        Pair pair5;
        boolean z;
        long j2;
        Object obj12;
        Object obj13;
        Object obj14;
        Declaration declaration3;
        Object obj15;
        Pair pair6;
        LambdaExp lambdaExp2;
        int i3;
        Pair pair7;
        int i4;
        Pair pair8;
        Pair pair9;
        int i5;
        long j3;
        Object obj16;
        Object obj17;
        Object obj18;
        Declaration declaration4;
        Object obj19;
        LambdaExp lambdaExp3;
        Object obj20;
        Object obj21;
        Object obj22;
        ClassExp classExp3 = classExp;
        Translator translator2 = translator;
        translator.mustCompileHere();
        Object car = pair.getCar();
        Object cdr = pair.getCdr();
        Vector vector = new Vector(20);
        Object[] objArr2 = null;
        Object obj23 = cdr;
        LambdaExp lambdaExp4 = null;
        Object obj24 = null;
        LambdaExp lambdaExp5 = null;
        long j4 = 0;
        while (obj23 != LList.Empty) {
            while (obj23 instanceof SyntaxForm) {
                obj23 = ((SyntaxForm) obj23).getDatum();
            }
            if (!(obj23 instanceof Pair)) {
                translator2.error('e', "object member not a list");
                return objArr2;
            }
            Pair pair10 = (Pair) obj23;
            Object car2 = pair10.getCar();
            while (car2 instanceof SyntaxForm) {
                car2 = ((SyntaxForm) car2).getDatum();
            }
            Object cdr2 = pair10.getCdr();
            Object pushPositionOf = translator2.pushPositionOf(pair10);
            if (car2 instanceof Keyword) {
                while (cdr2 instanceof SyntaxForm) {
                    cdr2 = ((SyntaxForm) cdr2).getDatum();
                }
                if (cdr2 instanceof Pair) {
                    if (car2 == interfaceKeyword) {
                        Pair pair11 = (Pair) cdr2;
                        if (pair11.getCar() == Boolean.FALSE) {
                            classExp3.setFlag(65536);
                        } else {
                            classExp3.setFlag(32768);
                        }
                        Object cdr3 = pair11.getCdr();
                        translator2.popPositionOf(pushPositionOf);
                        obj23 = cdr3;
                    } else if (car2 == classNameKeyword) {
                        if (obj24 != null) {
                            translator2.error('e', "duplicate class-name specifiers");
                        }
                        Object cdr4 = ((Pair) cdr2).getCdr();
                        translator2.popPositionOf(pushPositionOf);
                        obj23 = cdr4;
                        obj24 = cdr2;
                    } else if (car2 == accessKeyword) {
                        Object pushPositionOf2 = translator2.pushPositionOf(cdr2);
                        Pair pair12 = (Pair) cdr2;
                        Object obj25 = pushPositionOf;
                        Object obj26 = pushPositionOf2;
                        LambdaExp lambdaExp6 = lambdaExp4;
                        j4 = addAccessFlags(pair12.getCar(), j4, Declaration.CLASS_ACCESS_FLAGS, "class", translator);
                        if (classExp3.nameDecl == null) {
                            translator2.error('e', "access specifier for anonymous class");
                        }
                        translator2.popPositionOf(obj26);
                        obj23 = pair12.getCdr();
                        translator2.popPositionOf(obj25);
                        lambdaExp4 = lambdaExp6;
                        objArr2 = null;
                    }
                }
            }
            Object obj27 = pushPositionOf;
            LambdaExp lambdaExp7 = lambdaExp4;
            if (!(car2 instanceof Pair)) {
                translator2.error('e', "object member not a list");
                return null;
            }
            Pair pair13 = (Pair) car2;
            Object car3 = pair13.getCar();
            while (car3 instanceof SyntaxForm) {
                car3 = ((SyntaxForm) car3).getDatum();
            }
            if ((car3 instanceof String) || (car3 instanceof Symbol) || (car3 instanceof Keyword)) {
                LambdaExp lambdaExp8 = lambdaExp7;
                if (car3 instanceof Keyword) {
                    obj7 = car3;
                    declaration = null;
                    obj8 = pair13;
                } else {
                    Declaration addDeclaration = classExp3.addDeclaration(car3);
                    addDeclaration.setSimple(false);
                    obj7 = car3;
                    addDeclaration.setFlag(1048576);
                    Translator.setLine(addDeclaration, (Object) pair13);
                    declaration = addDeclaration;
                    obj8 = pair13.getCdr();
                }
                Object obj28 = obj8;
                Declaration declaration5 = declaration;
                Pair pair14 = null;
                Pair pair15 = null;
                int i6 = 0;
                int i7 = 0;
                long j5 = 0;
                boolean z2 = false;
                while (true) {
                    Pair pair16 = pair14;
                    if (obj28 == LList.Empty) {
                        lambdaExp = lambdaExp8;
                        j = j4;
                        obj4 = car;
                        obj3 = cdr;
                        obj2 = cdr2;
                        obj = obj24;
                        declaration2 = declaration5;
                        pair2 = pair16;
                        pair3 = pair15;
                        i = i6;
                        obj6 = obj27;
                        obj9 = obj7;
                        break;
                    }
                    while (obj28 instanceof SyntaxForm) {
                        obj28 = ((SyntaxForm) obj28).getDatum();
                    }
                    Pair pair17 = (Pair) obj28;
                    Object car4 = pair17.getCar();
                    while (car4 instanceof SyntaxForm) {
                        car4 = ((SyntaxForm) car4).getDatum();
                    }
                    Object pushPositionOf3 = translator2.pushPositionOf(pair17);
                    Object cdr5 = pair17.getCdr();
                    Pair pair18 = pair17;
                    Symbol symbol = coloncolon;
                    Pair pair19 = pair15;
                    if ((car4 == symbol || (car4 instanceof Keyword)) && (cdr5 instanceof Pair)) {
                        i7++;
                        Pair pair20 = (Pair) cdr5;
                        Object car5 = pair20.getCar();
                        Object cdr6 = pair20.getCdr();
                        if (car4 == symbol || car4 == typeKeyword) {
                            j2 = j4;
                            obj12 = car;
                            obj13 = cdr2;
                            obj14 = obj24;
                            declaration3 = declaration5;
                            obj15 = pushPositionOf3;
                            pair6 = pair19;
                            lambdaExp2 = lambdaExp8;
                            obj3 = cdr;
                            obj6 = obj27;
                            obj9 = obj7;
                            i3 = i6;
                        } else {
                            LambdaExp lambdaExp9 = lambdaExp8;
                            if (car4 == allocationKeyword) {
                                if (i6 != 0) {
                                    translator2.error('e', "duplicate allocation: specification");
                                }
                                if (matches(car5, "class", translator2) || matches(car5, "static", translator2)) {
                                    j = j4;
                                    obj4 = car;
                                    obj2 = cdr2;
                                    obj = obj24;
                                    z = z2;
                                    declaration2 = declaration5;
                                    pair14 = pair16;
                                    obj11 = pushPositionOf3;
                                    pair5 = pair19;
                                    lambdaExp = lambdaExp9;
                                    i6 = 2048;
                                } else if (matches(car5, "instance", translator2)) {
                                    i6 = 4096;
                                    j = j4;
                                    obj4 = car;
                                    obj2 = cdr2;
                                    obj = obj24;
                                    z = z2;
                                    declaration2 = declaration5;
                                    pair14 = pair16;
                                    obj11 = pushPositionOf3;
                                    pair5 = pair19;
                                    lambdaExp = lambdaExp9;
                                } else {
                                    translator2.error('e', "unknown allocation kind '" + car5 + "'");
                                    j2 = j4;
                                    obj12 = car;
                                    obj13 = cdr2;
                                    obj14 = obj24;
                                    declaration3 = declaration5;
                                    pair7 = pair16;
                                    obj15 = pushPositionOf3;
                                    pair6 = pair19;
                                    lambdaExp2 = lambdaExp9;
                                    obj3 = cdr;
                                    obj6 = obj27;
                                    obj9 = obj7;
                                    i4 = i6;
                                }
                                obj3 = cdr;
                                obj6 = obj27;
                                obj9 = obj7;
                                z2 = z;
                                pair15 = pair5;
                                obj28 = cdr6;
                            } else {
                                Keyword keyword = initKeyword;
                                if (car4 != keyword) {
                                    int i8 = i6;
                                    if (car4 == initformKeyword || car4 == init_formKeyword || car4 == init_valueKeyword) {
                                        j3 = j4;
                                        obj16 = car;
                                        obj17 = cdr2;
                                        obj18 = obj24;
                                        declaration4 = declaration5;
                                        pair9 = pair16;
                                        obj19 = pushPositionOf3;
                                        pair8 = pair19;
                                        lambdaExp3 = lambdaExp9;
                                        obj20 = cdr;
                                        obj21 = obj27;
                                        obj22 = obj7;
                                        i5 = i8;
                                    } else if (car4 == init_keywordKeyword) {
                                        if (!(car5 instanceof Keyword)) {
                                            translator2.error('e', "invalid 'init-keyword' - not a keyword");
                                        } else if (((Keyword) car5).getName() != obj7.toString()) {
                                            translator2.error('w', "init-keyword option ignored");
                                        }
                                        j2 = j4;
                                        obj12 = car;
                                        obj13 = cdr2;
                                        obj14 = obj24;
                                        declaration3 = declaration5;
                                        pair7 = pair16;
                                        obj15 = pushPositionOf3;
                                        pair6 = pair19;
                                        lambdaExp2 = lambdaExp9;
                                        obj3 = cdr;
                                        obj6 = obj27;
                                        obj9 = obj7;
                                        i4 = i8;
                                    } else if (car4 == accessKeyword) {
                                        Object pushPositionOf4 = translator2.pushPositionOf(pair20);
                                        obj12 = car;
                                        obj13 = cdr2;
                                        obj14 = obj24;
                                        pair7 = pair16;
                                        obj15 = pushPositionOf3;
                                        pair6 = pair19;
                                        obj3 = cdr;
                                        declaration3 = declaration5;
                                        lambdaExp2 = lambdaExp9;
                                        i4 = i8;
                                        obj6 = obj27;
                                        obj9 = obj7;
                                        j2 = j4;
                                        long addAccessFlags = addAccessFlags(car5, j5, Declaration.FIELD_ACCESS_FLAGS, "field", translator);
                                        translator2.popPositionOf(pushPositionOf4);
                                        j5 = addAccessFlags;
                                    } else {
                                        j2 = j4;
                                        obj12 = car;
                                        obj13 = cdr2;
                                        obj14 = obj24;
                                        declaration3 = declaration5;
                                        pair7 = pair16;
                                        obj15 = pushPositionOf3;
                                        pair6 = pair19;
                                        lambdaExp2 = lambdaExp9;
                                        obj3 = cdr;
                                        obj6 = obj27;
                                        obj9 = obj7;
                                        i4 = i8;
                                        translator2.error('w', "unknown slot keyword '" + car4 + "'");
                                    }
                                } else {
                                    j3 = j4;
                                    obj16 = car;
                                    obj17 = cdr2;
                                    obj18 = obj24;
                                    declaration4 = declaration5;
                                    pair9 = pair16;
                                    obj19 = pushPositionOf3;
                                    pair8 = pair19;
                                    lambdaExp3 = lambdaExp9;
                                    obj20 = cdr;
                                    obj21 = obj27;
                                    obj22 = obj7;
                                    i5 = i6;
                                }
                                if (z2) {
                                    translator2.error('e', "duplicate initialization");
                                }
                                if (car4 != keyword) {
                                    pair8 = pair20;
                                }
                                i6 = i5;
                                pair14 = pair9;
                                z = true;
                                z2 = z;
                                pair15 = pair5;
                                obj28 = cdr6;
                            }
                            i3 = i4;
                            pair20 = pair7;
                        }
                        z = z2;
                        z2 = z;
                        pair15 = pair5;
                        obj28 = cdr6;
                    } else {
                        j = j4;
                        obj4 = car;
                        obj2 = cdr2;
                        obj = obj24;
                        declaration2 = declaration5;
                        pair2 = pair16;
                        obj11 = pushPositionOf3;
                        pair3 = pair19;
                        lambdaExp = lambdaExp8;
                        obj3 = cdr;
                        obj6 = obj27;
                        obj9 = obj7;
                        i = i6;
                        if (cdr5 != LList.Empty || z2) {
                            if (!(cdr5 instanceof Pair) || i7 != 0 || z2 || pair2 != null) {
                                break;
                            }
                            Pair pair21 = (Pair) cdr5;
                            if (pair21.getCdr() != LList.Empty) {
                                break;
                            }
                            obj28 = pair21.getCdr();
                            pair15 = pair21;
                            i2 = i;
                            pair4 = pair18;
                        } else {
                            obj28 = cdr5;
                            i2 = i;
                            pair4 = pair2;
                            pair15 = pair18;
                        }
                        z2 = true;
                    }
                    translator2.popPositionOf(obj11);
                    obj24 = obj;
                    obj7 = obj9;
                    car = obj4;
                    obj27 = obj6;
                    lambdaExp8 = lambdaExp;
                    cdr = obj3;
                    cdr2 = obj2;
                    j4 = j;
                    declaration5 = declaration2;
                    ClassExp classExp4 = classExp;
                }
                obj28 = null;
                if (obj28 != LList.Empty) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("invalid argument list for slot '");
                    sb.append(obj9);
                    sb.append('\'');
                    sb.append(" args:");
                    sb.append(obj28 == null ? "null" : obj28.getClass().getName());
                    translator2.error('e', sb.toString());
                    return null;
                }
                if (z2) {
                    boolean z3 = i == 2048;
                    if (declaration2 != null) {
                        obj10 = declaration2;
                    } else {
                        obj10 = z3 ? Boolean.TRUE : Boolean.FALSE;
                    }
                    vector.addElement(obj10);
                    vector.addElement(pair3);
                }
                if (declaration2 != null) {
                    objArr = null;
                    if (pair2 != null) {
                        declaration2.setType(translator2.exp2Type(pair2));
                    }
                    if (i != 0) {
                        declaration2.setFlag((long) i);
                    }
                    long j6 = j5;
                    if (j6 != 0) {
                        declaration2.setFlag(j6);
                    }
                    declaration2.setCanRead(true);
                    declaration2.setCanWrite(true);
                    obj5 = obj6;
                    lambdaExp4 = lambdaExp;
                    translator2.popPositionOf(obj5);
                    obj24 = obj;
                    classExp3 = classExp;
                    objArr2 = objArr;
                    car = obj4;
                    cdr = obj3;
                    obj23 = obj2;
                    j4 = j;
                } else if (!z2) {
                    translator2.error('e', "missing field name");
                    return null;
                }
            } else if (car3 instanceof Pair) {
                Pair pair22 = (Pair) car3;
                Object car6 = pair22.getCar();
                if ((car6 instanceof String) || (car6 instanceof Symbol)) {
                    LambdaExp lambdaExp10 = new LambdaExp();
                    Translator.setLine(classExp3.addMethod(lambdaExp10, car6), (Object) pair22);
                    LambdaExp lambdaExp11 = lambdaExp7;
                    if (lambdaExp11 == null) {
                        lambdaExp5 = lambdaExp10;
                    } else {
                        lambdaExp11.nextSibling = lambdaExp10;
                    }
                    lambdaExp4 = lambdaExp10;
                    j = j4;
                    obj4 = car;
                    obj3 = cdr;
                    obj2 = cdr2;
                    obj5 = obj27;
                    obj = obj24;
                    objArr = null;
                    translator2.popPositionOf(obj5);
                    obj24 = obj;
                    classExp3 = classExp;
                    objArr2 = objArr;
                    car = obj4;
                    cdr = obj3;
                    obj23 = obj2;
                    j4 = j;
                } else {
                    translator2.error('e', "missing method name");
                    return null;
                }
            } else {
                translator2.error('e', "invalid field/method definition");
                lambdaExp = lambdaExp7;
                j = j4;
                obj4 = car;
                obj3 = cdr;
                obj2 = cdr2;
                obj6 = obj27;
                obj = obj24;
            }
            objArr = null;
            obj5 = obj6;
            lambdaExp4 = lambdaExp;
            translator2.popPositionOf(obj5);
            obj24 = obj;
            classExp3 = classExp;
            objArr2 = objArr;
            car = obj4;
            cdr = obj3;
            obj23 = obj2;
            j4 = j;
        }
        Object obj29 = car;
        Object obj30 = cdr;
        Object obj31 = obj24;
        if (j4 != 0) {
            classExp2 = classExp;
            classExp2.nameDecl.setFlag(j4);
        } else {
            classExp2 = classExp;
        }
        return new Object[]{classExp2, obj30, vector, lambdaExp5, obj29, obj31};
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0222 A[Catch:{ all -> 0x0261 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0233 A[EDGE_INSN: B:173:0x0233->B:132:0x0233 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rewriteClassDef(java.lang.Object[] r20, kawa.lang.Translator r21) {
        /*
            r19 = this;
            r7 = r21
            r0 = 0
            r1 = r20[r0]
            r8 = r1
            gnu.expr.ClassExp r8 = (gnu.expr.ClassExp) r8
            r1 = 1
            r1 = r20[r1]
            r2 = 2
            r2 = r20[r2]
            r9 = r2
            java.util.Vector r9 = (java.util.Vector) r9
            r2 = 3
            r2 = r20[r2]
            gnu.expr.LambdaExp r2 = (gnu.expr.LambdaExp) r2
            r3 = 4
            r3 = r20[r3]
            r4 = 5
            r4 = r20[r4]
            r8.firstChild = r2
            int r5 = kawa.lang.Translator.listLength(r3)
            r6 = 101(0x65, float:1.42E-43)
            if (r5 >= 0) goto L_0x002c
            java.lang.String r5 = "object superclass specification not a list"
            r7.error(r6, r5)
            r5 = 0
        L_0x002c:
            gnu.expr.Expression[] r10 = new gnu.expr.Expression[r5]
            r11 = 0
        L_0x002f:
            if (r11 >= r5) goto L_0x006a
        L_0x0031:
            boolean r12 = r3 instanceof kawa.lang.SyntaxForm
            if (r12 == 0) goto L_0x003c
            kawa.lang.SyntaxForm r3 = (kawa.lang.SyntaxForm) r3
            java.lang.Object r3 = r3.getDatum()
            goto L_0x0031
        L_0x003c:
            gnu.lists.Pair r3 = (gnu.lists.Pair) r3
            gnu.expr.Expression r12 = r7.rewrite_car((gnu.lists.Pair) r3, (boolean) r0)
            r10[r11] = r12
            boolean r13 = r12 instanceof gnu.expr.ReferenceExp
            if (r13 == 0) goto L_0x0063
            gnu.expr.ReferenceExp r12 = (gnu.expr.ReferenceExp) r12
            gnu.expr.Declaration r12 = r12.getBinding()
            gnu.expr.Declaration r12 = gnu.expr.Declaration.followAliases(r12)
            if (r12 == 0) goto L_0x0063
            gnu.expr.Expression r12 = r12.getValue()
            boolean r13 = r12 instanceof gnu.expr.ClassExp
            if (r13 == 0) goto L_0x0063
            gnu.expr.ClassExp r12 = (gnu.expr.ClassExp) r12
            r13 = 131072(0x20000, float:1.83671E-40)
            r12.setFlag(r13)
        L_0x0063:
            java.lang.Object r3 = r3.getCdr()
            int r11 = r11 + 1
            goto L_0x002f
        L_0x006a:
            if (r4 == 0) goto L_0x0094
            r3 = r4
            gnu.lists.Pair r3 = (gnu.lists.Pair) r3
            gnu.expr.Expression r3 = r7.rewrite_car((gnu.lists.Pair) r3, (boolean) r0)
            java.lang.Object r3 = r3.valueIfConstant()
            boolean r5 = r3 instanceof java.lang.CharSequence
            if (r5 == 0) goto L_0x0088
            java.lang.String r3 = r3.toString()
            int r5 = r3.length()
            if (r5 <= 0) goto L_0x0088
            r8.classNameSpecifier = r3
            goto L_0x0094
        L_0x0088:
            java.lang.Object r3 = r7.pushPositionOf(r4)
            java.lang.String r4 = "class-name specifier must be a non-empty string literal"
            r7.error(r6, r4)
            r7.popPositionOf(r3)
        L_0x0094:
            r8.supers = r10
            r8.setTypes(r7)
            int r3 = r9.size()
            r4 = 0
        L_0x009e:
            r10 = 0
            if (r4 >= r3) goto L_0x00b5
            int r5 = r4 + 1
            java.lang.Object r5 = r9.elementAt(r5)
            if (r5 == 0) goto L_0x00b2
            java.lang.Object r6 = r9.elementAt(r4)
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            rewriteInit(r6, r8, r5, r7, r10)
        L_0x00b2:
            int r4 = r4 + 2
            goto L_0x009e
        L_0x00b5:
            r7.push((gnu.expr.ScopeExp) r8)
            r11 = r2
            r2 = r10
            r12 = 0
        L_0x00bb:
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            if (r1 == r3) goto L_0x0266
            r13 = r2
        L_0x00c0:
            boolean r2 = r1 instanceof kawa.lang.SyntaxForm
            if (r2 == 0) goto L_0x00cc
            r13 = r1
            kawa.lang.SyntaxForm r13 = (kawa.lang.SyntaxForm) r13
            java.lang.Object r1 = r13.getDatum()
            goto L_0x00c0
        L_0x00cc:
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1
            java.lang.Object r14 = r7.pushPositionOf(r1)
            java.lang.Object r2 = r1.getCar()
            r15 = r13
        L_0x00d7:
            boolean r3 = r2 instanceof kawa.lang.SyntaxForm
            if (r3 == 0) goto L_0x00e3
            r15 = r2
            kawa.lang.SyntaxForm r15 = (kawa.lang.SyntaxForm) r15
            java.lang.Object r2 = r15.getDatum()
            goto L_0x00d7
        L_0x00e3:
            java.lang.Object r6 = r1.getCdr()     // Catch:{ all -> 0x0261 }
            boolean r1 = r2 instanceof gnu.expr.Keyword     // Catch:{ all -> 0x0261 }
            if (r1 == 0) goto L_0x00fa
            boolean r1 = r6 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0261 }
            if (r1 == 0) goto L_0x00fa
            gnu.lists.Pair r6 = (gnu.lists.Pair) r6     // Catch:{ all -> 0x0261 }
            java.lang.Object r1 = r6.getCdr()     // Catch:{ all -> 0x0261 }
            r7.popPositionOf(r14)
            r2 = r13
            goto L_0x00bb
        L_0x00fa:
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2     // Catch:{ all -> 0x0261 }
            java.lang.Object r1 = r2.getCar()     // Catch:{ all -> 0x0261 }
            r3 = r15
        L_0x0101:
            boolean r4 = r1 instanceof kawa.lang.SyntaxForm     // Catch:{ all -> 0x0261 }
            if (r4 == 0) goto L_0x010d
            r3 = r1
            kawa.lang.SyntaxForm r3 = (kawa.lang.SyntaxForm) r3     // Catch:{ all -> 0x0261 }
            java.lang.Object r1 = r3.getDatum()     // Catch:{ all -> 0x0261 }
            goto L_0x0101
        L_0x010d:
            boolean r4 = r1 instanceof java.lang.String     // Catch:{ all -> 0x0261 }
            if (r4 != 0) goto L_0x018e
            boolean r4 = r1 instanceof gnu.mapping.Symbol     // Catch:{ all -> 0x0261 }
            if (r4 != 0) goto L_0x018e
            boolean r4 = r1 instanceof gnu.expr.Keyword     // Catch:{ all -> 0x0261 }
            if (r4 == 0) goto L_0x011b
            goto L_0x018e
        L_0x011b:
            boolean r4 = r1 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0261 }
            if (r4 == 0) goto L_0x0185
            gnu.expr.ScopeExp r5 = r21.currentScope()     // Catch:{ all -> 0x0261 }
            if (r15 == 0) goto L_0x012c
            kawa.lang.TemplateScope r4 = r15.getScope()     // Catch:{ all -> 0x0261 }
            r7.setCurrentScope(r4)     // Catch:{ all -> 0x0261 }
        L_0x012c:
            java.lang.String r4 = "*init*"
            java.lang.String r0 = r11.getName()     // Catch:{ all -> 0x0261 }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x0261 }
            if (r0 == 0) goto L_0x013d
            gnu.bytecode.PrimType r0 = gnu.bytecode.Type.voidType     // Catch:{ all -> 0x0261 }
            r11.setReturnType(r0)     // Catch:{ all -> 0x0261 }
        L_0x013d:
            kawa.lang.Translator.setLine((gnu.expr.Expression) r11, (java.lang.Object) r2)     // Catch:{ all -> 0x0261 }
            gnu.expr.LambdaExp r0 = r7.curMethodLambda     // Catch:{ all -> 0x0261 }
            r7.curMethodLambda = r11     // Catch:{ all -> 0x0261 }
            r4 = r19
            kawa.lang.Lambda r10 = r4.lambda     // Catch:{ all -> 0x0261 }
            gnu.lists.Pair r1 = (gnu.lists.Pair) r1     // Catch:{ all -> 0x0261 }
            java.lang.Object r16 = r1.getCdr()     // Catch:{ all -> 0x0261 }
            java.lang.Object r17 = r2.getCdr()     // Catch:{ all -> 0x0261 }
            if (r3 == 0) goto L_0x0167
            if (r15 == 0) goto L_0x0160
            kawa.lang.TemplateScope r1 = r3.getScope()     // Catch:{ all -> 0x0261 }
            kawa.lang.TemplateScope r2 = r15.getScope()     // Catch:{ all -> 0x0261 }
            if (r1 == r2) goto L_0x0167
        L_0x0160:
            kawa.lang.TemplateScope r1 = r3.getScope()     // Catch:{ all -> 0x0261 }
            r18 = r1
            goto L_0x0169
        L_0x0167:
            r18 = 0
        L_0x0169:
            r1 = r10
            r2 = r11
            r3 = r16
            r4 = r17
            r10 = r5
            r5 = r21
            r16 = r6
            r6 = r18
            r1.rewrite(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0261 }
            r7.curMethodLambda = r0     // Catch:{ all -> 0x0261 }
            if (r15 == 0) goto L_0x0180
            r7.setCurrentScope(r10)     // Catch:{ all -> 0x0261 }
        L_0x0180:
            gnu.expr.LambdaExp r0 = r11.nextSibling     // Catch:{ all -> 0x0261 }
            r11 = r0
            goto L_0x0257
        L_0x0185:
            r16 = r6
            java.lang.String r0 = "invalid field/method definition"
            r7.syntaxError(r0)     // Catch:{ all -> 0x0261 }
            goto L_0x0257
        L_0x018e:
            r16 = r6
            boolean r0 = r1 instanceof gnu.expr.Keyword     // Catch:{ all -> 0x0261 }
            if (r0 == 0) goto L_0x0195
            goto L_0x0199
        L_0x0195:
            java.lang.Object r2 = r2.getCdr()     // Catch:{ all -> 0x0261 }
        L_0x0199:
            r0 = 0
            r1 = 0
            r3 = 0
            r4 = 0
        L_0x019d:
            gnu.lists.LList r5 = gnu.lists.LList.Empty     // Catch:{ all -> 0x0261 }
            if (r2 == r5) goto L_0x0231
        L_0x01a1:
            boolean r5 = r2 instanceof kawa.lang.SyntaxForm     // Catch:{ all -> 0x0261 }
            if (r5 == 0) goto L_0x01ad
            r15 = r2
            kawa.lang.SyntaxForm r15 = (kawa.lang.SyntaxForm) r15     // Catch:{ all -> 0x0261 }
            java.lang.Object r2 = r15.getDatum()     // Catch:{ all -> 0x0261 }
            goto L_0x01a1
        L_0x01ad:
            gnu.lists.Pair r2 = (gnu.lists.Pair) r2     // Catch:{ all -> 0x0261 }
            java.lang.Object r5 = r2.getCar()     // Catch:{ all -> 0x0261 }
        L_0x01b3:
            boolean r6 = r5 instanceof kawa.lang.SyntaxForm     // Catch:{ all -> 0x0261 }
            if (r6 == 0) goto L_0x01be
            kawa.lang.SyntaxForm r5 = (kawa.lang.SyntaxForm) r5     // Catch:{ all -> 0x0261 }
            java.lang.Object r5 = r5.getDatum()     // Catch:{ all -> 0x0261 }
            goto L_0x01b3
        L_0x01be:
            java.lang.Object r6 = r7.pushPositionOf(r2)     // Catch:{ all -> 0x0261 }
            java.lang.Object r10 = r2.getCdr()     // Catch:{ all -> 0x0261 }
            r17 = r2
            gnu.mapping.Symbol r2 = coloncolon     // Catch:{ all -> 0x0261 }
            if (r5 == r2) goto L_0x01d3
            r18 = r11
            boolean r11 = r5 instanceof gnu.expr.Keyword     // Catch:{ all -> 0x0261 }
            if (r11 == 0) goto L_0x0203
            goto L_0x01d5
        L_0x01d3:
            r18 = r11
        L_0x01d5:
            boolean r11 = r10 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0261 }
            if (r11 == 0) goto L_0x0203
            int r0 = r0 + 1
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10     // Catch:{ all -> 0x0261 }
            java.lang.Object r11 = r10.getCar()     // Catch:{ all -> 0x0261 }
            java.lang.Object r17 = r10.getCdr()     // Catch:{ all -> 0x0261 }
            if (r5 == r2) goto L_0x01ff
            gnu.expr.Keyword r2 = typeKeyword     // Catch:{ all -> 0x0261 }
            if (r5 != r2) goto L_0x01ec
            goto L_0x01ff
        L_0x01ec:
            gnu.expr.Keyword r2 = initKeyword     // Catch:{ all -> 0x0261 }
            if (r5 == r2) goto L_0x01fc
            gnu.expr.Keyword r2 = initformKeyword     // Catch:{ all -> 0x0261 }
            if (r5 == r2) goto L_0x01fc
            gnu.expr.Keyword r2 = init_formKeyword     // Catch:{ all -> 0x0261 }
            if (r5 == r2) goto L_0x01fc
            gnu.expr.Keyword r2 = init_valueKeyword     // Catch:{ all -> 0x0261 }
            if (r5 != r2) goto L_0x0200
        L_0x01fc:
            r1 = r10
            r3 = r15
            goto L_0x0200
        L_0x01ff:
            r4 = r11
        L_0x0200:
            r2 = r17
            goto L_0x022a
        L_0x0203:
            gnu.lists.LList r2 = gnu.lists.LList.Empty     // Catch:{ all -> 0x0261 }
            if (r10 != r2) goto L_0x020e
            if (r1 != 0) goto L_0x020e
            r2 = r10
            r3 = r15
            r1 = r17
            goto L_0x022a
        L_0x020e:
            boolean r2 = r10 instanceof gnu.lists.Pair     // Catch:{ all -> 0x0261 }
            if (r2 == 0) goto L_0x0233
            if (r0 != 0) goto L_0x0233
            if (r1 != 0) goto L_0x0233
            if (r4 != 0) goto L_0x0233
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10     // Catch:{ all -> 0x0261 }
            java.lang.Object r2 = r10.getCdr()     // Catch:{ all -> 0x0261 }
            gnu.lists.LList r4 = gnu.lists.LList.Empty     // Catch:{ all -> 0x0261 }
            if (r2 != r4) goto L_0x0233
            java.lang.Object r1 = r10.getCdr()     // Catch:{ all -> 0x0261 }
            r2 = r1
            r4 = r5
            r1 = r10
            r3 = r15
        L_0x022a:
            r7.popPositionOf(r6)     // Catch:{ all -> 0x0261 }
            r11 = r18
            goto L_0x019d
        L_0x0231:
            r18 = r11
        L_0x0233:
            if (r1 == 0) goto L_0x0255
            int r0 = r12 + 1
            java.lang.Object r2 = r9.elementAt(r12)     // Catch:{ all -> 0x0261 }
            boolean r4 = r2 instanceof gnu.expr.Declaration     // Catch:{ all -> 0x0261 }
            if (r4 == 0) goto L_0x0248
            r4 = r2
            gnu.expr.Declaration r4 = (gnu.expr.Declaration) r4     // Catch:{ all -> 0x0261 }
            r5 = 2048(0x800, double:1.0118E-320)
            r4.getFlag(r5)     // Catch:{ all -> 0x0261 }
            goto L_0x024a
        L_0x0248:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0261 }
        L_0x024a:
            int r12 = r0 + 1
            java.lang.Object r0 = r9.elementAt(r0)     // Catch:{ all -> 0x0261 }
            if (r0 != 0) goto L_0x0255
            rewriteInit(r2, r8, r1, r7, r3)     // Catch:{ all -> 0x0261 }
        L_0x0255:
            r11 = r18
        L_0x0257:
            r7.popPositionOf(r14)
            r2 = r13
            r1 = r16
            r0 = 0
            r10 = 0
            goto L_0x00bb
        L_0x0261:
            r0 = move-exception
            r7.popPositionOf(r14)
            throw r0
        L_0x0266:
            gnu.expr.LambdaExp r0 = r8.initMethod
            if (r0 == 0) goto L_0x026e
            gnu.expr.LambdaExp r0 = r8.initMethod
            r0.outer = r8
        L_0x026e:
            gnu.expr.LambdaExp r0 = r8.clinitMethod
            if (r0 == 0) goto L_0x0276
            gnu.expr.LambdaExp r0 = r8.clinitMethod
            r0.outer = r8
        L_0x0276:
            r7.pop(r8)
            r8.declareParts(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.object.rewriteClassDef(java.lang.Object[], kawa.lang.Translator):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: gnu.expr.SetExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: gnu.expr.ApplyExp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void rewriteInit(java.lang.Object r6, gnu.expr.ClassExp r7, gnu.lists.Pair r8, kawa.lang.Translator r9, kawa.lang.SyntaxForm r10) {
        /*
            boolean r0 = r6 instanceof gnu.expr.Declaration
            r1 = 1
            if (r0 == 0) goto L_0x000f
            r2 = r6
            gnu.expr.Declaration r2 = (gnu.expr.Declaration) r2
            r3 = 2048(0x800, double:1.0118E-320)
            boolean r2 = r2.getFlag(r3)
            goto L_0x0016
        L_0x000f:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            if (r6 != r2) goto L_0x0015
            r2 = 1
            goto L_0x0016
        L_0x0015:
            r2 = 0
        L_0x0016:
            if (r2 == 0) goto L_0x001b
            gnu.expr.LambdaExp r3 = r7.clinitMethod
            goto L_0x001d
        L_0x001b:
            gnu.expr.LambdaExp r3 = r7.initMethod
        L_0x001d:
            r4 = 0
            if (r3 != 0) goto L_0x0053
            gnu.expr.LambdaExp r3 = new gnu.expr.LambdaExp
            gnu.expr.BeginExp r5 = new gnu.expr.BeginExp
            r5.<init>()
            r3.<init>((gnu.expr.Expression) r5)
            r3.setClassMethod(r1)
            gnu.bytecode.PrimType r1 = gnu.bytecode.Type.voidType
            r3.setReturnType(r1)
            if (r2 == 0) goto L_0x003c
            java.lang.String r1 = "$clinit$"
            r3.setName(r1)
            r7.clinitMethod = r3
            goto L_0x004d
        L_0x003c:
            java.lang.String r1 = "$finit$"
            r3.setName(r1)
            r7.initMethod = r3
            gnu.expr.Declaration r1 = new gnu.expr.Declaration
            java.lang.String r2 = gnu.expr.ThisExp.THIS_NAME
            r1.<init>((java.lang.Object) r2)
            r3.add(r4, r1)
        L_0x004d:
            gnu.expr.LambdaExp r1 = r7.firstChild
            r3.nextSibling = r1
            r7.firstChild = r3
        L_0x0053:
            r9.push((gnu.expr.ScopeExp) r3)
            gnu.expr.LambdaExp r7 = r9.curMethodLambda
            r9.curMethodLambda = r3
            gnu.expr.Expression r8 = r9.rewrite_car((gnu.lists.Pair) r8, (kawa.lang.SyntaxForm) r10)
            if (r0 == 0) goto L_0x006e
            gnu.expr.Declaration r6 = (gnu.expr.Declaration) r6
            gnu.expr.SetExp r10 = new gnu.expr.SetExp
            r10.<init>((gnu.expr.Declaration) r6, (gnu.expr.Expression) r8)
            r10.setLocation(r6)
            r6.noteValue(r4)
            goto L_0x0079
        L_0x006e:
            gnu.expr.QuoteExp r6 = new gnu.expr.QuoteExp
            gnu.bytecode.PrimType r10 = gnu.bytecode.Type.voidType
            r6.<init>(r10)
            gnu.expr.ApplyExp r10 = gnu.expr.Compilation.makeCoercion((gnu.expr.Expression) r8, (gnu.expr.Expression) r6)
        L_0x0079:
            gnu.expr.Expression r6 = r3.body
            gnu.expr.BeginExp r6 = (gnu.expr.BeginExp) r6
            r6.add(r10)
            r9.curMethodLambda = r7
            r9.pop(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.object.rewriteInit(java.lang.Object, gnu.expr.ClassExp, gnu.lists.Pair, kawa.lang.Translator, kawa.lang.SyntaxForm):void");
    }

    static boolean matches(Object obj, String str, Translator translator) {
        String str2;
        if (obj instanceof Keyword) {
            str2 = ((Keyword) obj).getName();
        } else if (obj instanceof FString) {
            str2 = ((FString) obj).toString();
        } else if (!(obj instanceof Pair)) {
            return false;
        } else {
            Object matchQuoted = translator.matchQuoted((Pair) obj);
            if (!(matchQuoted instanceof SimpleSymbol)) {
                return false;
            }
            str2 = matchQuoted.toString();
        }
        if (str == null || str.equals(str2)) {
            return true;
        }
        return false;
    }

    static long addAccessFlags(Object obj, long j, long j2, String str, Translator translator) {
        long matchAccess = matchAccess(obj, translator);
        if (matchAccess == 0) {
            translator.error('e', "unknown access specifier " + obj);
        } else if (((~j2) & matchAccess) != 0) {
            translator.error('e', "invalid " + str + " access specifier " + obj);
        } else if ((j & matchAccess) != 0) {
            translator.error('w', "duplicate " + str + " access specifiers " + obj);
        }
        return j | matchAccess;
    }

    static long matchAccess(Object obj, Translator translator) {
        while (obj instanceof SyntaxForm) {
            obj = ((SyntaxForm) obj).getDatum();
        }
        if (obj instanceof Pair) {
            obj = translator.matchQuoted((Pair) obj);
            if (obj instanceof Pair) {
                return matchAccess2((Pair) obj, translator);
            }
        }
        return matchAccess1(obj, translator);
    }

    private static long matchAccess2(Pair pair, Translator translator) {
        long matchAccess1 = matchAccess1(pair.getCar(), translator);
        Object cdr = pair.getCdr();
        if (cdr == LList.Empty || matchAccess1 == 0) {
            return matchAccess1;
        }
        if (cdr instanceof Pair) {
            long matchAccess2 = matchAccess2((Pair) cdr, translator);
            if (matchAccess2 != 0) {
                return matchAccess2 | matchAccess1;
            }
        }
        return 0;
    }

    private static long matchAccess1(Object obj, Translator translator) {
        if (obj instanceof Keyword) {
            obj = ((Keyword) obj).getName();
        } else if (obj instanceof FString) {
            obj = ((FString) obj).toString();
        } else if (obj instanceof SimpleSymbol) {
            obj = obj.toString();
        }
        if ("private".equals(obj)) {
            return 16777216;
        }
        if ("protected".equals(obj)) {
            return 33554432;
        }
        if ("public".equals(obj)) {
            return 67108864;
        }
        if ("package".equals(obj)) {
            return 134217728;
        }
        if ("volatile".equals(obj)) {
            return Declaration.VOLATILE_ACCESS;
        }
        if ("transient".equals(obj)) {
            return Declaration.TRANSIENT_ACCESS;
        }
        if ("enum".equals(obj)) {
            return Declaration.ENUM_ACCESS;
        }
        if ("final".equals(obj)) {
            return Declaration.FINAL_ACCESS;
        }
        return 0;
    }
}
