package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SyntaxRules extends Procedure1 implements Printable, Externalizable {
    Object[] literal_identifiers;
    int maxVars;
    SyntaxRule[] rules;

    public SyntaxRules() {
        this.maxVars = 0;
    }

    public SyntaxRules(Object[] objArr, SyntaxRule[] syntaxRuleArr, int i) {
        this.literal_identifiers = objArr;
        this.rules = syntaxRuleArr;
        this.maxVars = i;
    }

    public SyntaxRules(Object[] objArr, Object obj, Translator translator) {
        int i;
        int i2;
        Object[] objArr2 = objArr;
        Translator translator2 = translator;
        this.maxVars = 0;
        this.literal_identifiers = objArr2;
        int listLength = Translator.listLength(obj);
        if (listLength < 0) {
            translator2.syntaxError("missing or invalid syntax-rules");
            i = 0;
        } else {
            i = listLength;
        }
        this.rules = new SyntaxRule[i];
        SyntaxForm syntaxForm = null;
        int i3 = 0;
        Object obj2 = obj;
        while (i3 < i) {
            SyntaxForm syntaxForm2 = syntaxForm;
            while (obj2 instanceof SyntaxForm) {
                syntaxForm2 = (SyntaxForm) obj2;
                obj2 = syntaxForm2.getDatum();
            }
            Pair pair = (Pair) obj2;
            SyntaxForm syntaxForm3 = syntaxForm2;
            Object obj3 = pair.getCar();
            while (obj3 instanceof SyntaxForm) {
                syntaxForm3 = (SyntaxForm) obj3;
                obj3 = syntaxForm3.getDatum();
            }
            if (!(obj3 instanceof Pair)) {
                translator2.syntaxError("missing pattern in " + i3 + "'th syntax rule");
                return;
            }
            Pair pair2 = (Pair) obj3;
            Object car = pair2.getCar();
            String fileName = translator.getFileName();
            int lineNumber = translator.getLineNumber();
            int columnNumber = translator.getColumnNumber();
            try {
                translator2.setLine((Object) pair2);
                SyntaxForm syntaxForm4 = syntaxForm3;
                Object obj4 = pair2.getCdr();
                while (obj4 instanceof SyntaxForm) {
                    syntaxForm4 = (SyntaxForm) obj4;
                    obj4 = syntaxForm4.getDatum();
                }
                if (!(obj4 instanceof Pair)) {
                    translator2.syntaxError("missing template in " + i3 + "'th syntax rule");
                    translator2.setLine(fileName, lineNumber, columnNumber);
                    return;
                }
                Pair pair3 = (Pair) obj4;
                SyntaxForm syntaxForm5 = syntaxForm3;
                if (pair3.getCdr() != LList.Empty) {
                    translator2.syntaxError("junk after " + i3 + "'th syntax rule");
                    translator2.setLine(fileName, lineNumber, columnNumber);
                    return;
                }
                Object car2 = pair3.getCar();
                translator2.push((ScopeExp) PatternScope.push(translator));
                SyntaxForm syntaxForm6 = syntaxForm5;
                Object obj5 = car;
                while (obj5 instanceof SyntaxForm) {
                    syntaxForm6 = (SyntaxForm) obj5;
                    obj5 = syntaxForm6.getDatum();
                }
                StringBuffer stringBuffer = new StringBuffer();
                if (obj5 instanceof Pair) {
                    objArr2[0] = ((Pair) obj5).getCar();
                    stringBuffer.append(12);
                    stringBuffer.append(24);
                    SyntaxForm syntaxForm7 = syntaxForm4;
                    int i4 = i;
                    i2 = columnNumber;
                    try {
                        SyntaxPattern syntaxPattern = new SyntaxPattern(stringBuffer, ((Pair) obj5).getCdr(), syntaxForm6, objArr, translator);
                        this.rules[i3] = new SyntaxRule(r20, car2, syntaxForm7, translator2);
                        PatternScope.pop(translator);
                        translator.pop();
                        translator2.setLine(fileName, lineNumber, i2);
                        i3++;
                        obj2 = pair.getCdr();
                        objArr2 = objArr;
                        syntaxForm = syntaxForm2;
                        i = i4;
                    } catch (Throwable th) {
                        th = th;
                        translator2.setLine(fileName, lineNumber, i2);
                        throw th;
                    }
                } else {
                    i2 = columnNumber;
                    translator2.syntaxError("pattern does not start with name");
                    translator2.setLine(fileName, lineNumber, i2);
                    return;
                }
            } catch (Throwable th2) {
                th = th2;
                i2 = columnNumber;
                translator2.setLine(fileName, lineNumber, i2);
                throw th;
            }
        }
        int length = this.rules.length;
        while (true) {
            length--;
            if (length >= 0) {
                int length2 = this.rules[length].patternNesting.length();
                if (length2 > this.maxVars) {
                    this.maxVars = length2;
                }
            } else {
                return;
            }
        }
    }

    public Object apply1(Object obj) {
        if (!(obj instanceof SyntaxForm)) {
            return expand(obj, (Translator) Compilation.getCurrent());
        }
        SyntaxForm syntaxForm = (SyntaxForm) obj;
        Translator translator = (Translator) Compilation.getCurrent();
        ScopeExp currentScope = translator.currentScope();
        translator.setCurrentScope(syntaxForm.getScope());
        try {
            return expand(syntaxForm, translator);
        } finally {
            translator.setCurrentScope(currentScope);
        }
    }

    public Object expand(Object obj, Translator translator) {
        Object[] objArr = new Object[this.maxVars];
        Macro macro = (Macro) translator.getCurrentSyntax();
        int i = 0;
        while (true) {
            SyntaxRule[] syntaxRuleArr = this.rules;
            if (i < syntaxRuleArr.length) {
                SyntaxRule syntaxRule = syntaxRuleArr[i];
                if (syntaxRule == null) {
                    return new ErrorExp("error defining " + macro);
                } else if (syntaxRule.pattern.match(obj, objArr, 0)) {
                    return syntaxRule.execute(objArr, translator, TemplateScope.make(translator));
                } else {
                    i++;
                }
            } else {
                return translator.syntaxError("no matching syntax-rule for " + this.literal_identifiers[0]);
            }
        }
    }

    public void print(Consumer consumer) {
        consumer.write("#<macro ");
        ReportFormat.print(this.literal_identifiers[0], consumer);
        consumer.write(62);
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(this.literal_identifiers);
        objectOutput.writeObject(this.rules);
        objectOutput.writeInt(this.maxVars);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        this.literal_identifiers = (Object[]) objectInput.readObject();
        this.rules = (SyntaxRule[]) objectInput.readObject();
        this.maxVars = objectInput.readInt();
    }
}
