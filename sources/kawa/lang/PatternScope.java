package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import java.util.Vector;

public class PatternScope extends LetExp {
    public Declaration matchArray;
    public StringBuffer patternNesting;
    public Vector pattern_names;
    PatternScope previousSyntax;

    public PatternScope() {
        super((Expression[]) null);
    }

    public static PatternScope push(Translator translator) {
        PatternScope patternScope = new PatternScope();
        PatternScope patternScope2 = translator.patternScope;
        patternScope.previousSyntax = patternScope2;
        translator.patternScope = patternScope;
        if (patternScope2 == null) {
            patternScope.pattern_names = new Vector();
            patternScope.patternNesting = new StringBuffer();
        } else {
            patternScope.pattern_names = (Vector) patternScope2.pattern_names.clone();
            patternScope.patternNesting = new StringBuffer(patternScope2.patternNesting.toString());
        }
        patternScope.outer = translator.currentScope();
        return patternScope;
    }

    public static void pop(Translator translator) {
        translator.patternScope = translator.patternScope.previousSyntax;
    }
}
