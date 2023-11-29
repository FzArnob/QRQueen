package kawa.lib.kawa;

import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: regex.scm */
public class regex extends ModuleBody {
    public static final regex $instance;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("loop").readResolve());
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod regex$Mnmatch;
    public static final ModuleMethod regex$Mnmatch$Mnpositions;
    public static final ModuleMethod regex$Mnmatch$Qu;
    public static final ModuleMethod regex$Mnquote;
    public static final ModuleMethod regex$Mnreplace;
    public static final ModuleMethod regex$Mnreplace$St;
    public static final ModuleMethod regex$Mnsplit;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("regex-replace*").readResolve();
        Lit7 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("regex-replace").readResolve();
        Lit6 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("regex-split").readResolve();
        Lit5 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("regex-match-positions").readResolve();
        Lit4 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("regex-match").readResolve();
        Lit3 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("regex-match?").readResolve();
        Lit2 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("regex-quote").readResolve();
        Lit1 = simpleSymbol7;
        regex regex = new regex();
        $instance = regex;
        regex$Mnquote = new ModuleMethod(regex, 2, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        regex$Mnmatch$Qu = new ModuleMethod(regex, 3, simpleSymbol6, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch = new ModuleMethod(regex, 6, simpleSymbol5, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch$Mnpositions = new ModuleMethod(regex, 9, simpleSymbol4, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnsplit = new ModuleMethod(regex, 12, simpleSymbol3, 8194);
        regex$Mnreplace = new ModuleMethod(regex, 13, simpleSymbol2, 12291);
        regex$Mnreplace$St = new ModuleMethod(regex, 14, simpleSymbol, 12291);
        regex.run();
    }

    public regex() {
        ModuleInfo.register(this);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence) {
        return isRegexMatch(obj, charSequence, 0);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence, int i) {
        return isRegexMatch(obj, charSequence, i, charSequence.length());
    }

    public static Object regexMatch(Object obj, CharSequence charSequence) {
        return regexMatch(obj, charSequence, 0);
    }

    public static Object regexMatch(Object obj, CharSequence charSequence, int i) {
        return regexMatch(obj, charSequence, i, charSequence.length());
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence) {
        return regexMatchPositions(obj, charSequence, 0);
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence, int i) {
        return regexMatchPositions(obj, charSequence, i, charSequence.length());
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        if (moduleMethod.selector != 2) {
            return super.apply1(moduleMethod, obj);
        }
        try {
            return regexQuote((CharSequence) obj);
        } catch (ClassCastException e) {
            throw new WrongType(e, "regex-quote", 1, obj);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 2) {
            return super.match1(moduleMethod, obj, callContext);
        }
        if (!(obj instanceof CharSequence)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static String regexQuote(CharSequence charSequence) {
        return Pattern.quote(charSequence == null ? null : charSequence.toString());
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 3) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i == 6) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i == 9) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 12) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 3) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i == 6) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i == 9) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i == 13) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i != 14) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 3) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i == 6) {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i != 9) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof CharSequence)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern pattern;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        Matcher matcher = pattern.matcher(charSequence);
        matcher.region(i, i2);
        return matcher.find();
    }

    public static Object regexMatch(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern pattern;
        Object obj2;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        Matcher matcher = pattern.matcher(charSequence);
        matcher.region(i, i2);
        if (!matcher.find()) {
            return Boolean.FALSE;
        }
        Object obj3 = LList.Empty;
        for (int groupCount = matcher.groupCount(); groupCount >= 0; groupCount--) {
            int start = matcher.start(groupCount);
            if (start < 0) {
                obj2 = Boolean.FALSE;
            } else {
                obj2 = charSequence.subSequence(start, matcher.end(groupCount));
            }
            obj3 = lists.cons(obj2, obj3);
        }
        return obj3;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        if (i == 3) {
            try {
                try {
                    try {
                        return isRegexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "regex-match?", 4, obj4);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "regex-match?", 3, obj3);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "regex-match?", 2, obj2);
            }
        } else if (i == 6) {
            try {
                try {
                    try {
                        return regexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "regex-match", 4, obj4);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "regex-match", 3, obj3);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "regex-match", 2, obj2);
            }
        } else if (i != 9) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        } else {
            try {
                try {
                    try {
                        return regexMatchPositions(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "regex-match-positions", 4, obj4);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "regex-match-positions", 3, obj3);
                }
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "regex-match-positions", 2, obj2);
            }
        }
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern pattern;
        Object obj2;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        Matcher matcher = pattern.matcher(charSequence);
        matcher.region(i, i2);
        if (!matcher.find()) {
            return Boolean.FALSE;
        }
        Object obj3 = LList.Empty;
        for (int groupCount = matcher.groupCount(); groupCount >= 0; groupCount--) {
            int start = matcher.start(groupCount);
            if (start < 0) {
                obj2 = Boolean.FALSE;
            } else {
                obj2 = lists.cons(Integer.valueOf(start), Integer.valueOf(matcher.end(groupCount)));
            }
            obj3 = lists.cons(obj2, obj3);
        }
        return obj3;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 3) {
            try {
                return isRegexMatch(obj, (CharSequence) obj2) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e) {
                throw new WrongType(e, "regex-match?", 2, obj2);
            }
        } else if (i == 6) {
            try {
                return regexMatch(obj, (CharSequence) obj2);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "regex-match", 2, obj2);
            }
        } else if (i == 9) {
            try {
                return regexMatchPositions(obj, (CharSequence) obj2);
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "regex-match-positions", 2, obj2);
            }
        } else if (i != 12) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                return regexSplit(obj, (CharSequence) obj2);
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "regex-split", 2, obj2);
            }
        }
    }

    public static LList regexSplit(Object obj, CharSequence charSequence) {
        Pattern pattern;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        return LList.makeList(pattern.split(charSequence, -1), 0);
    }

    public static CharSequence regexReplace(Object obj, CharSequence charSequence, Object obj2) {
        Pattern pattern;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        Matcher matcher = pattern.matcher(charSequence);
        if (!matcher.find()) {
            return charSequence;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String str = null;
        if (misc.isProcedure(obj2)) {
            Object apply2 = Scheme.applyToArgs.apply2(obj2, matcher.group());
            if (apply2 != null) {
                str = apply2.toString();
            }
            str = Matcher.quoteReplacement(str);
        } else if (obj2 != null) {
            str = obj2.toString();
        }
        matcher.appendReplacement(stringBuffer, str);
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static CharSequence regexReplace$St(Object obj, CharSequence charSequence, Object obj2) {
        Pattern pattern;
        frame frame2 = new frame();
        frame2.repl = obj2;
        if (obj instanceof Pattern) {
            try {
                pattern = (Pattern) obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "rex", -2, obj);
            }
        } else {
            pattern = Pattern.compile(obj.toString());
        }
        frame2.matcher = pattern.matcher(charSequence);
        frame2.sbuf = new StringBuffer();
        if (misc.isProcedure(frame2.repl)) {
            frame2.loop = frame2.loop;
            return frame2.lambda1loop();
        }
        Matcher matcher = frame2.matcher;
        Object obj3 = frame2.repl;
        return matcher.replaceAll(obj3 == null ? null : obj3.toString());
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 3) {
            try {
                try {
                    return isRegexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "regex-match?", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "regex-match?", 2, obj2);
            }
        } else if (i == 6) {
            try {
                try {
                    return regexMatch(obj, (CharSequence) obj2, ((Number) obj3).intValue());
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "regex-match", 3, obj3);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "regex-match", 2, obj2);
            }
        } else if (i == 9) {
            try {
                try {
                    return regexMatchPositions(obj, (CharSequence) obj2, ((Number) obj3).intValue());
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "regex-match-positions", 3, obj3);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "regex-match-positions", 2, obj2);
            }
        } else if (i == 13) {
            try {
                return regexReplace(obj, (CharSequence) obj2, obj3);
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "regex-replace", 2, obj2);
            }
        } else if (i != 14) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        } else {
            try {
                return regexReplace$St(obj, (CharSequence) obj2, obj3);
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "regex-replace*", 2, obj2);
            }
        }
    }

    /* compiled from: regex.scm */
    public class frame extends ModuleBody {
        Object loop = new ModuleMethod(this, 1, regex.Lit0, 0);
        Matcher matcher;
        Object repl;
        StringBuffer sbuf;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda1loop() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public String lambda1loop() {
            if (this.matcher.find()) {
                Matcher matcher2 = this.matcher;
                StringBuffer stringBuffer = this.sbuf;
                Object apply2 = Scheme.applyToArgs.apply2(this.repl, this.matcher.group());
                matcher2.appendReplacement(stringBuffer, Matcher.quoteReplacement(apply2 == null ? null : apply2.toString()));
            }
            this.matcher.appendTail(this.sbuf);
            return this.sbuf.toString();
        }
    }
}
