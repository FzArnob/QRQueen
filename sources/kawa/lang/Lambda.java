package kawa.lang;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;

public class Lambda extends Syntax {
    public static final Keyword nameKeyword = Keyword.make(CommonProperties.NAME);
    public Expression defaultDefault = QuoteExp.falseExp;
    public Object keyKeyword;
    public Object optionalKeyword;
    public Object restKeyword;

    public void setKeywords(Object obj, Object obj2, Object obj3) {
        this.optionalKeyword = obj;
        this.restKeyword = obj2;
        this.keyKeyword = obj3;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Expression rewrite = rewrite(pair.getCdr(), translator);
        Translator.setLine(rewrite, (Object) pair);
        return rewrite;
    }

    public Expression rewrite(Object obj, Translator translator) {
        if (!(obj instanceof Pair)) {
            return translator.syntaxError("missing formals in lambda");
        }
        int errorCount = translator.getMessages().getErrorCount();
        LambdaExp lambdaExp = new LambdaExp();
        Pair pair = (Pair) obj;
        Translator.setLine((Expression) lambdaExp, (Object) pair);
        rewrite(lambdaExp, pair.getCar(), pair.getCdr(), translator, (TemplateScope) null);
        return translator.getMessages().getErrorCount() > errorCount ? new ErrorExp("bad lambda expression") : lambdaExp;
    }

    public void rewrite(LambdaExp lambdaExp, Object obj, Object obj2, Translator translator, TemplateScope templateScope) {
        rewriteFormals(lambdaExp, obj, translator, templateScope);
        if (obj2 instanceof PairWithPosition) {
            lambdaExp.setFile(((PairWithPosition) obj2).getFileName());
        }
        rewriteBody(lambdaExp, rewriteAttrs(lambdaExp, obj2, translator), translator);
    }

    public void rewriteFormals(LambdaExp lambdaExp, Object obj, Translator translator, TemplateScope templateScope) {
        TemplateScope templateScope2;
        TemplateScope templateScope3;
        Pair pair;
        Object obj2;
        Pair pair2;
        Pair pair3;
        Pair pair4;
        Object obj3;
        Object obj4;
        LambdaExp lambdaExp2 = lambdaExp;
        Translator translator2 = translator;
        if (lambdaExp.getSymbol() == null) {
            String fileName = lambdaExp.getFileName();
            int lineNumber = lambdaExp.getLineNumber();
            if (fileName != null && lineNumber > 0) {
                lambdaExp2.setSourceLocation(fileName, lineNumber);
            }
        }
        Object obj5 = obj;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        while (true) {
            if (obj5 instanceof SyntaxForm) {
                obj5 = ((SyntaxForm) obj5).getDatum();
            }
            int i4 = 0;
            if (!(obj5 instanceof Pair)) {
                if (obj5 instanceof Symbol) {
                    if (i >= 0 || i3 >= 0 || i2 >= 0) {
                        translator2.syntaxError("dotted rest-arg after " + this.optionalKeyword + ", " + this.restKeyword + ", or " + this.keyKeyword);
                        return;
                    }
                    i2 = 1;
                } else if (obj5 != LList.Empty) {
                    translator2.syntaxError("misformed formals in lambda");
                    return;
                }
                if (i2 > 1) {
                    translator2.syntaxError("multiple " + this.restKeyword + " parameters");
                    return;
                }
                if (i < 0) {
                    i = 0;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i3 < 0) {
                    i3 = 0;
                }
                if (i2 > 0) {
                    lambdaExp2.max_args = -1;
                } else {
                    lambdaExp2.max_args = lambdaExp2.min_args + i + (i3 * 2);
                }
                int i5 = i + i3;
                if (i5 > 0) {
                    lambdaExp2.defaultArgs = new Expression[i5];
                }
                if (i3 > 0) {
                    lambdaExp2.keywords = new Keyword[i3];
                }
                Expression expression = null;
                Object obj6 = obj;
                TemplateScope templateScope4 = templateScope;
                Object obj7 = null;
                int i6 = 0;
                while (true) {
                    if (obj6 instanceof SyntaxForm) {
                        SyntaxForm syntaxForm = (SyntaxForm) obj6;
                        Object datum = syntaxForm.getDatum();
                        templateScope4 = syntaxForm.getScope();
                        obj6 = datum;
                    }
                    if (!(obj6 instanceof Pair)) {
                        if (obj6 instanceof SyntaxForm) {
                            SyntaxForm syntaxForm2 = (SyntaxForm) obj6;
                            Object datum2 = syntaxForm2.getDatum();
                            templateScope4 = syntaxForm2.getScope();
                            obj6 = datum2;
                        }
                        if (obj6 instanceof Symbol) {
                            Declaration declaration = new Declaration(obj6);
                            declaration.setType(LangObjType.listType);
                            declaration.setFlag(262144);
                            declaration.noteValue(expression);
                            addParam(declaration, templateScope4, lambdaExp2, translator2);
                            return;
                        }
                        return;
                    }
                    Pair pair5 = (Pair) obj6;
                    Object car = pair5.getCar();
                    if (car instanceof SyntaxForm) {
                        SyntaxForm syntaxForm3 = (SyntaxForm) car;
                        Object datum3 = syntaxForm3.getDatum();
                        templateScope2 = syntaxForm3.getScope();
                        car = datum3;
                    } else {
                        templateScope2 = templateScope4;
                    }
                    if (car == this.optionalKeyword || car == this.restKeyword || car == this.keyKeyword) {
                        templateScope3 = templateScope4;
                        pair = pair5;
                        obj7 = car;
                    } else {
                        Object pushPositionOf = translator2.pushPositionOf(pair5);
                        Object obj8 = this.defaultDefault;
                        if (translator2.matches(car, "::")) {
                            translator2.syntaxError("'::' must follow parameter name");
                            return;
                        }
                        Object namespaceResolve = translator2.namespaceResolve(car);
                        templateScope3 = templateScope4;
                        if (namespaceResolve instanceof Symbol) {
                            if (pair5.getCdr() instanceof Pair) {
                                Pair pair6 = (Pair) pair5.getCdr();
                                obj4 = obj8;
                                if (translator2.matches(pair6.getCar(), "::")) {
                                    if (!(pair5.getCdr() instanceof Pair)) {
                                        translator2.syntaxError("'::' not followed by a type specifier (for parameter '" + namespaceResolve + "')");
                                        return;
                                    }
                                    pair2 = (Pair) pair6.getCdr();
                                    obj2 = obj4;
                                    pair5 = pair2;
                                }
                            } else {
                                obj4 = obj8;
                            }
                            obj2 = obj4;
                            pair2 = null;
                        } else {
                            Object obj9 = obj8;
                            if (namespaceResolve instanceof Pair) {
                                Pair pair7 = (Pair) namespaceResolve;
                                Object car2 = pair7.getCar();
                                if (car2 instanceof SyntaxForm) {
                                    SyntaxForm syntaxForm4 = (SyntaxForm) car2;
                                    Object datum4 = syntaxForm4.getDatum();
                                    templateScope2 = syntaxForm4.getScope();
                                    car2 = datum4;
                                }
                                Object namespaceResolve2 = translator2.namespaceResolve(car2);
                                if (!(namespaceResolve2 instanceof Symbol) || !(pair7.getCdr() instanceof Pair)) {
                                    obj2 = obj9;
                                    pair5 = pair5;
                                } else {
                                    Pair pair8 = (Pair) pair7.getCdr();
                                    Pair pair9 = pair5;
                                    if (!translator2.matches(pair8.getCar(), "::")) {
                                        pair3 = pair8;
                                        pair4 = null;
                                    } else if (!(pair8.getCdr() instanceof Pair)) {
                                        translator2.syntaxError("'::' not followed by a type specifier (for parameter '" + namespaceResolve2 + "')");
                                        return;
                                    } else {
                                        pair4 = (Pair) pair8.getCdr();
                                        if (pair4.getCdr() instanceof Pair) {
                                            pair3 = (Pair) pair4.getCdr();
                                        } else if (pair4.getCdr() == LList.Empty) {
                                            pair3 = null;
                                        } else {
                                            translator2.syntaxError("improper list in specifier for parameter '" + namespaceResolve2 + "')");
                                            return;
                                        }
                                    }
                                    if (pair3 == null || obj7 == null) {
                                        obj3 = obj9;
                                        pair2 = pair3;
                                    } else {
                                        Object car3 = pair3.getCar();
                                        if (pair3.getCdr() instanceof Pair) {
                                            pair2 = (Pair) pair3.getCdr();
                                            obj3 = car3;
                                        } else if (pair3.getCdr() == LList.Empty) {
                                            obj3 = car3;
                                            pair2 = null;
                                        } else {
                                            translator2.syntaxError("improper list in specifier for parameter '" + namespaceResolve2 + "')");
                                            return;
                                        }
                                    }
                                    if (pair2 == null) {
                                        pair2 = pair4;
                                    } else if (pair4 != null) {
                                        translator2.syntaxError("duplicate type specifier for parameter '" + namespaceResolve2 + '\'');
                                        return;
                                    } else if (pair2.getCdr() != LList.Empty) {
                                        translator2.syntaxError("junk at end of specifier for parameter '" + namespaceResolve2 + '\'' + " after type " + pair2.getCar());
                                        return;
                                    }
                                    namespaceResolve = namespaceResolve2;
                                    obj2 = obj3;
                                    pair5 = pair9;
                                }
                            } else {
                                Pair pair10 = pair5;
                                obj2 = obj9;
                            }
                            pair2 = null;
                            namespaceResolve = null;
                        }
                        if (namespaceResolve == null) {
                            translator2.syntaxError("parameter is neither name nor (name :: type) nor (name default): " + pair5);
                            return;
                        }
                        if (obj7 == this.optionalKeyword || obj7 == this.keyKeyword) {
                            pair = pair5;
                            lambdaExp2.defaultArgs[i4] = new LangExp(obj2);
                            i4++;
                        } else {
                            pair = pair5;
                        }
                        if (obj7 == this.keyKeyword) {
                            int i7 = i6 + 1;
                            lambdaExp2.keywords[i6] = Keyword.make(namespaceResolve instanceof Symbol ? ((Symbol) namespaceResolve).getName() : namespaceResolve.toString());
                            i6 = i7;
                        }
                        Declaration declaration2 = new Declaration(namespaceResolve);
                        Translator.setLine(declaration2, obj6);
                        if (pair2 != null) {
                            declaration2.setTypeExp(new LangExp(pair2));
                            declaration2.setFlag(8192);
                        } else if (obj7 == this.restKeyword) {
                            declaration2.setType(LangObjType.listType);
                        }
                        declaration2.setFlag(262144);
                        expression = null;
                        declaration2.noteValue((Expression) null);
                        addParam(declaration2, templateScope2, lambdaExp2, translator2);
                        translator2.popPositionOf(pushPositionOf);
                    }
                    obj6 = pair.getCdr();
                    templateScope4 = templateScope3;
                }
            } else {
                Pair pair11 = (Pair) obj5;
                Object car4 = pair11.getCar();
                if (car4 instanceof SyntaxForm) {
                    car4 = ((SyntaxForm) car4).getDatum();
                }
                if (car4 == this.optionalKeyword) {
                    if (i >= 0) {
                        translator2.syntaxError("multiple " + this.optionalKeyword + " in parameter list");
                        return;
                    } else if (i2 >= 0 || i3 >= 0) {
                        translator2.syntaxError(this.optionalKeyword.toString() + " after " + this.restKeyword + " or " + this.keyKeyword);
                    } else {
                        i = 0;
                    }
                } else if (car4 == this.restKeyword) {
                    if (i2 >= 0) {
                        translator2.syntaxError("multiple " + this.restKeyword + " in parameter list");
                        return;
                    } else if (i3 >= 0) {
                        translator2.syntaxError(this.restKeyword.toString() + " after " + this.keyKeyword);
                        return;
                    } else {
                        i2 = 0;
                    }
                } else if (car4 == this.keyKeyword) {
                    if (i3 >= 0) {
                        translator2.syntaxError("multiple " + this.keyKeyword + " in parameter list");
                        return;
                    }
                    i3 = 0;
                } else if (translator2.matches(pair11.getCar(), "::") && (pair11.getCdr() instanceof Pair)) {
                    pair11 = (Pair) pair11.getCdr();
                } else if (i3 >= 0) {
                    i3++;
                } else if (i2 >= 0) {
                    i2++;
                } else if (i >= 0) {
                    i++;
                } else {
                    lambdaExp2.min_args++;
                }
                pair11.getCdr();
                obj5 = pair11.getCdr();
            }
        }
        translator2.syntaxError(this.optionalKeyword.toString() + " after " + this.restKeyword + " or " + this.keyKeyword);
    }

    private static void addParam(Declaration declaration, ScopeExp scopeExp, LambdaExp lambdaExp, Translator translator) {
        if (scopeExp != null) {
            declaration = translator.makeRenamedAlias(declaration, scopeExp);
        }
        lambdaExp.addDeclaration(declaration);
        if (scopeExp != null) {
            declaration.context = scopeExp;
        }
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object rewriteAttrs(gnu.expr.LambdaExp r17, java.lang.Object r18, kawa.lang.Translator r19) {
        /*
            r16 = this;
            r0 = r17
            r1 = r19
            r2 = 0
            r3 = 0
            r4 = r18
            r5 = r3
            r8 = r5
            r9 = r8
            r6 = 0
            r7 = 0
        L_0x000d:
            boolean r10 = r4 instanceof kawa.lang.SyntaxForm
            if (r10 == 0) goto L_0x0019
            r5 = r4
            kawa.lang.SyntaxForm r5 = (kawa.lang.SyntaxForm) r5
            java.lang.Object r4 = r5.getDatum()
            goto L_0x000d
        L_0x0019:
            boolean r10 = r4 instanceof gnu.lists.Pair
            if (r10 != 0) goto L_0x001e
            goto L_0x004d
        L_0x001e:
            r10 = r4
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            java.lang.Object r11 = r10.getCar()
            java.lang.Object r11 = kawa.lang.Translator.stripSyntax(r11)
            java.lang.String r12 = "::"
            boolean r12 = r1.matches(r11, r12)
            if (r12 == 0) goto L_0x0033
            r11 = r3
            goto L_0x0038
        L_0x0033:
            boolean r12 = r11 instanceof gnu.expr.Keyword
            if (r12 != 0) goto L_0x0038
            goto L_0x004d
        L_0x0038:
            java.lang.Object r10 = r10.getCdr()
            r12 = r5
        L_0x003d:
            boolean r13 = r10 instanceof kawa.lang.SyntaxForm
            if (r13 == 0) goto L_0x0049
            r12 = r10
            kawa.lang.SyntaxForm r12 = (kawa.lang.SyntaxForm) r12
            java.lang.Object r10 = r12.getDatum()
            goto L_0x003d
        L_0x0049:
            boolean r13 = r10 instanceof gnu.lists.Pair
            if (r13 != 0) goto L_0x005e
        L_0x004d:
            r1 = r6 | r7
            if (r1 == 0) goto L_0x0057
            gnu.expr.Declaration r0 = r0.nameDecl
            long r1 = (long) r1
            r0.setFlag(r1)
        L_0x0057:
            if (r5 == 0) goto L_0x005d
            java.lang.Object r4 = kawa.lang.SyntaxForms.fromDatumIfNeeded(r4, r5)
        L_0x005d:
            return r4
        L_0x005e:
            gnu.lists.Pair r10 = (gnu.lists.Pair) r10
            r4 = 101(0x65, float:1.42E-43)
            if (r11 != 0) goto L_0x008e
            boolean r11 = r17.isClassMethod()
            if (r11 == 0) goto L_0x007d
            java.lang.String r11 = r17.getName()
            java.lang.String r13 = "*init*"
            boolean r11 = r13.equals(r11)
            if (r11 == 0) goto L_0x007d
            java.lang.String r11 = "explicit return type for '*init*' method"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x007d:
            gnu.expr.LangExp r4 = new gnu.expr.LangExp
            r11 = 2
            java.lang.Object[] r11 = new java.lang.Object[r11]
            r11[r2] = r10
            r13 = 1
            r11[r13] = r12
            r4.<init>(r11)
            r0.body = r4
            goto L_0x01f3
        L_0x008e:
            gnu.expr.Keyword r13 = kawa.standard.object.accessKeyword
            java.lang.String r14 = " and "
            if (r11 != r13) goto L_0x0113
            gnu.expr.Expression r11 = r1.rewrite_car((gnu.lists.Pair) r10, (kawa.lang.SyntaxForm) r12)
            boolean r12 = r11 instanceof gnu.expr.QuoteExp
            if (r12 == 0) goto L_0x010c
            gnu.expr.QuoteExp r11 = (gnu.expr.QuoteExp) r11
            java.lang.Object r11 = r11.getValue()
            boolean r12 = r11 instanceof gnu.mapping.SimpleSymbol
            if (r12 != 0) goto L_0x00ab
            boolean r12 = r11 instanceof java.lang.CharSequence
            if (r12 != 0) goto L_0x00ab
            goto L_0x010c
        L_0x00ab:
            gnu.expr.Declaration r12 = r0.nameDecl
            if (r12 != 0) goto L_0x00b6
            java.lang.String r11 = "access: not allowed for anonymous function"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x00b6:
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "private"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x00c5
            r6 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x00eb
        L_0x00c5:
            java.lang.String r12 = "protected"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x00d0
            r6 = 33554432(0x2000000, float:9.403955E-38)
            goto L_0x00eb
        L_0x00d0:
            java.lang.String r12 = "public"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x00db
            r6 = 67108864(0x4000000, float:1.5046328E-36)
            goto L_0x00eb
        L_0x00db:
            java.lang.String r12 = "package"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x00e6
            r6 = 134217728(0x8000000, float:3.85186E-34)
            goto L_0x00eb
        L_0x00e6:
            java.lang.String r12 = "unknown access specifier"
            r1.error(r4, r12)
        L_0x00eb:
            if (r9 == 0) goto L_0x0109
            if (r11 == 0) goto L_0x0109
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "duplicate access specifiers - "
            r12.append(r13)
            r12.append(r9)
            r12.append(r14)
            r12.append(r11)
            java.lang.String r9 = r12.toString()
            r1.error(r4, r9)
        L_0x0109:
            r9 = r11
            goto L_0x01f3
        L_0x010c:
            java.lang.String r11 = "access: value not a constant symbol or string"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x0113:
            gnu.expr.Keyword r13 = kawa.standard.object.allocationKeyword
            if (r11 != r13) goto L_0x0188
            gnu.expr.Expression r11 = r1.rewrite_car((gnu.lists.Pair) r10, (kawa.lang.SyntaxForm) r12)
            boolean r12 = r11 instanceof gnu.expr.QuoteExp
            if (r12 == 0) goto L_0x0182
            gnu.expr.QuoteExp r11 = (gnu.expr.QuoteExp) r11
            java.lang.Object r11 = r11.getValue()
            boolean r12 = r11 instanceof gnu.mapping.SimpleSymbol
            if (r12 != 0) goto L_0x012e
            boolean r12 = r11 instanceof java.lang.CharSequence
            if (r12 != 0) goto L_0x012e
            goto L_0x0182
        L_0x012e:
            gnu.expr.Declaration r12 = r0.nameDecl
            if (r12 != 0) goto L_0x0139
            java.lang.String r11 = "allocation: not allowed for anonymous function"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x0139:
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "class"
            boolean r12 = r12.equals(r11)
            if (r12 != 0) goto L_0x015f
            java.lang.String r12 = "static"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x014e
            goto L_0x015f
        L_0x014e:
            java.lang.String r12 = "instance"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x0159
            r7 = 4096(0x1000, float:5.74E-42)
            goto L_0x0161
        L_0x0159:
            java.lang.String r12 = "unknown allocation specifier"
            r1.error(r4, r12)
            goto L_0x0161
        L_0x015f:
            r7 = 2048(0x800, float:2.87E-42)
        L_0x0161:
            if (r8 == 0) goto L_0x017f
            if (r11 == 0) goto L_0x017f
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "duplicate allocation specifiers - "
            r12.append(r13)
            r12.append(r8)
            r12.append(r14)
            r12.append(r11)
            java.lang.String r8 = r12.toString()
            r1.error(r4, r8)
        L_0x017f:
            r8 = r11
            goto L_0x01f3
        L_0x0182:
            java.lang.String r11 = "allocation: value not a constant symbol or string"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x0188:
            gnu.expr.Keyword r13 = kawa.standard.object.throwsKeyword
            if (r11 != r13) goto L_0x01c3
            java.lang.Object r11 = r10.getCar()
            int r13 = kawa.lang.Translator.listLength(r11)
            if (r13 >= 0) goto L_0x019c
            java.lang.String r11 = "throws: not followed by a list"
            r1.error(r4, r11)
            goto L_0x01f3
        L_0x019c:
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r13]
            r14 = 0
        L_0x019f:
            if (r14 >= r13) goto L_0x01bf
        L_0x01a1:
            boolean r15 = r11 instanceof kawa.lang.SyntaxForm
            if (r15 == 0) goto L_0x01ad
            r12 = r11
            kawa.lang.SyntaxForm r12 = (kawa.lang.SyntaxForm) r12
            java.lang.Object r11 = r12.getDatum()
            goto L_0x01a1
        L_0x01ad:
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            gnu.expr.Expression r15 = r1.rewrite_car((gnu.lists.Pair) r11, (kawa.lang.SyntaxForm) r12)
            r4[r14] = r15
            kawa.lang.Translator.setLine((gnu.expr.Expression) r15, (java.lang.Object) r11)
            java.lang.Object r11 = r11.getCdr()
            int r14 = r14 + 1
            goto L_0x019f
        L_0x01bf:
            r0.setExceptions(r4)
            goto L_0x01f3
        L_0x01c3:
            gnu.expr.Keyword r4 = nameKeyword
            if (r11 != r4) goto L_0x01dd
            gnu.expr.Expression r4 = r1.rewrite_car((gnu.lists.Pair) r10, (kawa.lang.SyntaxForm) r12)
            boolean r11 = r4 instanceof gnu.expr.QuoteExp
            if (r11 == 0) goto L_0x01f3
            gnu.expr.QuoteExp r4 = (gnu.expr.QuoteExp) r4
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = r4.toString()
            r0.setName(r4)
            goto L_0x01f3
        L_0x01dd:
            r4 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "unknown procedure property "
            r12.append(r13)
            r12.append(r11)
            java.lang.String r11 = r12.toString()
            r1.error(r4, r11)
        L_0x01f3:
            java.lang.Object r4 = r10.getCdr()
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Lambda.rewriteAttrs(gnu.expr.LambdaExp, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    public Object skipAttrs(LambdaExp lambdaExp, Object obj, Translator translator) {
        while (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (!(pair.getCdr() instanceof Pair)) {
                break;
            }
            Object car = pair.getCar();
            if (!translator.matches(car, "::") && !(car instanceof Keyword)) {
                break;
            }
            obj = ((Pair) pair.getCdr()).getCdr();
        }
        return obj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0109, code lost:
        if ((r4 instanceof java.lang.Class) == false) goto L_0x0129;
     */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rewriteBody(gnu.expr.LambdaExp r10, java.lang.Object r11, kawa.lang.Translator r12) {
        /*
            r9 = this;
            gnu.expr.LambdaExp r0 = r12.curMethodLambda
            if (r0 != 0) goto L_0x0016
            gnu.expr.Declaration r0 = r10.nameDecl
            if (r0 == 0) goto L_0x0016
            gnu.expr.ModuleExp r0 = r12.getModule()
            r1 = 131072(0x20000, float:1.83671E-40)
            boolean r0 = r0.getFlag(r1)
            if (r0 == 0) goto L_0x0016
            r12.curMethodLambda = r10
        L_0x0016:
            r12.currentScope()
            r12.pushScope(r10)
            gnu.expr.Keyword[] r0 = r10.keywords
            r1 = 0
            if (r0 != 0) goto L_0x0023
            r0 = 0
            goto L_0x0026
        L_0x0023:
            gnu.expr.Keyword[] r0 = r10.keywords
            int r0 = r0.length
        L_0x0026:
            gnu.expr.Expression[] r2 = r10.defaultArgs
            if (r2 != 0) goto L_0x002c
            r2 = 0
            goto L_0x0030
        L_0x002c:
            gnu.expr.Expression[] r2 = r10.defaultArgs
            int r2 = r2.length
            int r2 = r2 - r0
        L_0x0030:
            gnu.expr.Declaration r0 = r10.firstDecl()
            r3 = 0
            r4 = r3
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x0039:
            if (r0 == 0) goto L_0x0099
            boolean r8 = r0.isAlias()
            if (r8 == 0) goto L_0x0055
            gnu.expr.ReferenceExp r8 = kawa.lang.Translator.getOriginalRef(r0)
            gnu.expr.Declaration r8 = r8.getBinding()
            r10.replaceFollowing(r4, r8)
            r8.context = r10
            r12.pushRenamedAlias(r0)
            int r5 = r5 + 1
            r4 = r8
            goto L_0x0056
        L_0x0055:
            r4 = r0
        L_0x0056:
            gnu.expr.Expression r0 = r4.getTypeExp()
            boolean r8 = r0 instanceof gnu.expr.LangExp
            if (r8 == 0) goto L_0x006d
            gnu.expr.LangExp r0 = (gnu.expr.LangExp) r0
            java.lang.Object r0 = r0.getLangValue()
            gnu.lists.Pair r0 = (gnu.lists.Pair) r0
            gnu.bytecode.Type r0 = r12.exp2Type(r0)
            r4.setType(r0)
        L_0x006d:
            int r0 = r10.min_args
            if (r6 < r0) goto L_0x008d
            int r0 = r10.min_args
            int r0 = r0 + r2
            if (r6 < r0) goto L_0x007f
            int r0 = r10.max_args
            if (r0 >= 0) goto L_0x007f
            int r0 = r10.min_args
            int r0 = r0 + r2
            if (r6 == r0) goto L_0x008d
        L_0x007f:
            gnu.expr.Expression[] r0 = r10.defaultArgs
            gnu.expr.Expression[] r8 = r10.defaultArgs
            r8 = r8[r7]
            gnu.expr.Expression r8 = r12.rewrite(r8)
            r0[r7] = r8
            int r7 = r7 + 1
        L_0x008d:
            int r6 = r6 + 1
            gnu.expr.NameLookup r0 = r12.lexical
            r0.push((gnu.expr.Declaration) r4)
            gnu.expr.Declaration r0 = r4.nextDecl()
            goto L_0x0039
        L_0x0099:
            boolean r0 = r10.isClassMethod()
            if (r0 == 0) goto L_0x00b3
            gnu.expr.Declaration r0 = r10.nameDecl
            r6 = 2048(0x800, double:1.0118E-320)
            boolean r0 = r0.getFlag(r6)
            if (r0 != 0) goto L_0x00b3
            gnu.expr.Declaration r0 = new gnu.expr.Declaration
            java.lang.String r2 = gnu.expr.ThisExp.THIS_NAME
            r0.<init>((java.lang.Object) r2)
            r10.add(r3, r0)
        L_0x00b3:
            gnu.expr.LambdaExp r0 = r12.curLambda
            r12.curLambda = r10
            gnu.bytecode.Type r2 = r10.returnType
            gnu.expr.Expression r4 = r10.body
            boolean r4 = r4 instanceof gnu.expr.LangExp
            r6 = 1
            if (r4 == 0) goto L_0x00e0
            gnu.expr.Expression r2 = r10.body
            gnu.expr.LangExp r2 = (gnu.expr.LangExp) r2
            java.lang.Object r2 = r2.getLangValue()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r4 = r2[r1]
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            r2 = r2[r6]
            kawa.lang.SyntaxForm r2 = (kawa.lang.SyntaxForm) r2
            gnu.expr.Expression r2 = r12.rewrite_car((gnu.lists.Pair) r4, (kawa.lang.SyntaxForm) r2)
            gnu.expr.Language r4 = r12.getLanguage()
            gnu.bytecode.Type r2 = r4.getTypeFor((gnu.expr.Expression) r2)
        L_0x00e0:
            gnu.expr.Expression r11 = r12.rewrite_body(r11)
            r10.body = r11
            r12.curLambda = r0
            gnu.expr.Expression r11 = r10.body
            boolean r11 = r11 instanceof gnu.expr.BeginExp
            if (r11 == 0) goto L_0x0129
            gnu.expr.Expression r11 = r10.body
            gnu.expr.BeginExp r11 = (gnu.expr.BeginExp) r11
            gnu.expr.Expression[] r11 = r11.getExpressions()
            int r0 = r11.length
            if (r0 <= r6) goto L_0x0129
            r4 = r11[r1]
            boolean r7 = r4 instanceof gnu.expr.ReferenceExp
            if (r7 != 0) goto L_0x010b
            java.lang.Object r4 = r4.valueIfConstant()
            boolean r7 = r4 instanceof gnu.bytecode.Type
            if (r7 != 0) goto L_0x010b
            boolean r4 = r4 instanceof java.lang.Class
            if (r4 == 0) goto L_0x0129
        L_0x010b:
            r2 = r11[r1]
            int r0 = r0 + -1
            if (r0 != r6) goto L_0x0116
            r11 = r11[r6]
            r10.body = r11
            goto L_0x0121
        L_0x0116:
            gnu.expr.Expression[] r4 = new gnu.expr.Expression[r0]
            java.lang.System.arraycopy(r11, r6, r4, r1, r0)
            gnu.expr.Expression r11 = gnu.expr.BeginExp.canonicalize((gnu.expr.Expression[]) r4)
            r10.body = r11
        L_0x0121:
            gnu.expr.Language r11 = r12.getLanguage()
            r10.setCoercedReturnValue(r2, r11)
            goto L_0x012c
        L_0x0129:
            r10.setCoercedReturnType(r2)
        L_0x012c:
            r12.pop(r10)
            r10.countDecls()
            r12.popRenamedAlias(r5)
            r10.countDecls()
            gnu.expr.LambdaExp r11 = r12.curMethodLambda
            if (r11 != r10) goto L_0x013e
            r12.curMethodLambda = r3
        L_0x013e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Lambda.rewriteBody(gnu.expr.LambdaExp, java.lang.Object, kawa.lang.Translator):void");
    }

    public void print(Consumer consumer) {
        consumer.write("#<builtin lambda>");
    }
}
