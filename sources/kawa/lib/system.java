package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.InputStream;
import java.util.StringTokenizer;
import kawa.lang.CompileFile;
import kawa.lang.NamedException;
import kawa.standard.Scheme;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: system.scm */
public class system extends ModuleBody {
    public static final system $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;

    /* renamed from: catch  reason: not valid java name */
    public static final ModuleMethod f343catch;
    public static Procedure command$Mnparse;
    public static final ModuleMethod compile$Mnfile;
    public static final ModuleMethod convert$Mnlist$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod convert$Mnvector$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod make$Mnprocess;
    public static final ModuleMethod open$Mninput$Mnpipe;
    public static final ModuleMethod process$Mncommand$Mnline$Mnassignments;
    public static final ModuleMethod system;
    public static final ModuleMethod tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod tokenize$Mnstring$Mnusing$Mnshell;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("process-command-line-assignments").readResolve();
        Lit11 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("catch").readResolve();
        Lit10 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("compile-file").readResolve();
        Lit9 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("tokenize-string-using-shell").readResolve();
        Lit8 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("tokenize-string-to-string-array").readResolve();
        Lit7 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("convert-list-to-string-array").readResolve();
        Lit6 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("convert-vector-to-string-array").readResolve();
        Lit5 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("system").readResolve();
        Lit4 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("open-input-pipe").readResolve();
        Lit3 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("make-process").readResolve();
        Lit2 = simpleSymbol10;
        system system2 = new system();
        $instance = system2;
        make$Mnprocess = new ModuleMethod(system2, 1, simpleSymbol10, 8194);
        open$Mninput$Mnpipe = new ModuleMethod(system2, 2, simpleSymbol9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        system = new ModuleMethod(system2, 3, simpleSymbol8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        convert$Mnvector$Mnto$Mnstring$Mnarray = new ModuleMethod(system2, 4, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        convert$Mnlist$Mnto$Mnstring$Mnarray = new ModuleMethod(system2, 5, simpleSymbol6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tokenize$Mnstring$Mnto$Mnstring$Mnarray = new ModuleMethod(system2, 6, simpleSymbol5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tokenize$Mnstring$Mnusing$Mnshell = new ModuleMethod(system2, 7, simpleSymbol4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        compile$Mnfile = new ModuleMethod(system2, 8, simpleSymbol3, 8194);
        f343catch = new ModuleMethod(system2, 9, simpleSymbol2, 12291);
        process$Mncommand$Mnline$Mnassignments = new ModuleMethod(system2, 10, simpleSymbol, 0);
        system2.run();
    }

    public system() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        command$Mnparse = IsEqual.apply(System.getProperty("file.separator"), InternalZipConstants.ZIP_FILE_SEPARATOR) ? tokenize$Mnstring$Mnusing$Mnshell : tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 8) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        } else {
            if (!(obj instanceof CharSequence)) {
                return -786431;
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Process makeProcess(Object obj, Object obj2) {
        if (vectors.isVector(obj)) {
            obj = convertVectorToStringArray(obj);
        } else if (lists.isList(obj)) {
            obj = convertListToStringArray(obj);
        } else if (strings.isString(obj)) {
            obj = command$Mnparse.apply1(obj);
        } else if (!(obj instanceof String[])) {
            obj = misc.error$V("invalid arguments to make-process", new Object[0]);
        }
        try {
            try {
                return Runtime.getRuntime().exec((String[]) obj, (String[]) obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 3, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 2, obj);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static InputStream openInputPipe(Object obj) {
        return makeProcess(obj, (Object) null).getInputStream();
    }

    public static int system(Object obj) {
        return makeProcess(obj, (Object) null).waitFor();
    }

    public static Object convertVectorToStringArray(Object obj) {
        try {
            int vectorLength = vectors.vectorLength((FVector) obj);
            String[] strArr = new String[vectorLength];
            Object obj2 = Lit0;
            while (Scheme.numEqu.apply2(obj2, Integer.valueOf(vectorLength)) == Boolean.FALSE) {
                int intValue = ((Number) obj2).intValue();
                try {
                    try {
                        Object vectorRef = vectors.vectorRef((FVector) obj, ((Number) obj2).intValue());
                        strArr[intValue] = vectorRef == null ? null : vectorRef.toString();
                        obj2 = AddOp.$Pl.apply2(obj2, Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "vector-ref", 2, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "vector-ref", 1, obj);
                }
            }
            return strArr;
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "vector-length", 1, obj);
        }
    }

    public static Object convertListToStringArray(Object obj) {
        try {
            String[] strArr = new String[lists.length((LList) obj)];
            int i = 0;
            while (!lists.isNull(obj)) {
                try {
                    Pair pair = (Pair) obj;
                    Object car = pair.getCar();
                    strArr[i] = car == null ? null : car.toString();
                    obj = pair.getCdr();
                    i++;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "pp", -2, obj);
                }
            }
            return strArr;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "length", 1, obj);
        }
    }

    public static Object tokenizeStringToStringArray(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        Object obj = LList.Empty;
        while (stringTokenizer.hasMoreTokens()) {
            obj = lists.cons(stringTokenizer.nextToken(), obj);
        }
        try {
            int length = lists.length((LList) obj);
            String[] strArr = new String[length];
            int i = length - 1;
            while (!lists.isNull(obj)) {
                try {
                    Pair pair = (Pair) obj;
                    Object car = pair.getCar();
                    strArr[i] = car == null ? null : car.toString();
                    obj = pair.getCdr();
                    i--;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "pp", -2, obj);
                }
            }
            return strArr;
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "length", 1, obj);
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 2:
                return openInputPipe(obj);
            case 3:
                return Integer.valueOf(system(obj));
            case 4:
                return convertVectorToStringArray(obj);
            case 5:
                return convertListToStringArray(obj);
            case 6:
                return tokenizeStringToStringArray(obj == null ? null : obj.toString());
            case 7:
                return tokenizeStringUsingShell(obj);
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static String[] tokenizeStringUsingShell(Object obj) {
        String[] strArr = new String[3];
        strArr[0] = "/bin/sh";
        strArr[1] = "-c";
        strArr[2] = obj == null ? null : obj.toString();
        return strArr;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return makeProcess(obj, obj2);
        }
        if (i != 8) {
            return super.apply2(moduleMethod, obj, obj2);
        }
        try {
            compileFile((CharSequence) obj, obj2 == null ? null : obj2.toString());
            return Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "compile-file", 1, obj);
        }
    }

    public static void compileFile(CharSequence charSequence, String str) {
        SourceMessages sourceMessages = new SourceMessages();
        Compilation read = CompileFile.read(charSequence.toString(), sourceMessages);
        read.explicit = true;
        if (!sourceMessages.seenErrors()) {
            read.compileToArchive(read.getModule(), str);
            if (sourceMessages.seenErrors()) {
                throw new SyntaxException(sourceMessages);
            }
            return;
        }
        throw new SyntaxException(sourceMessages);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        if (moduleMethod.selector != 9) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        }
        try {
            try {
                return m18catch(obj, (Procedure) obj2, (Procedure) obj3);
            } catch (ClassCastException e) {
                throw new WrongType(e, "catch", 3, obj3);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "catch", 2, obj2);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 9) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        callContext.value1 = obj;
        if (!(obj2 instanceof Procedure)) {
            return -786430;
        }
        callContext.value2 = obj2;
        if (!(obj3 instanceof Procedure)) {
            return -786429;
        }
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    /* renamed from: catch  reason: not valid java name */
    public static Object m18catch(Object obj, Procedure procedure, Procedure procedure2) {
        try {
            return procedure.apply0();
        } catch (NamedException e) {
            return e.applyHandler(obj, procedure2);
        }
    }

    public Object apply0(ModuleMethod moduleMethod) {
        if (moduleMethod.selector != 10) {
            return super.apply0(moduleMethod);
        }
        processCommandLineAssignments();
        return Values.empty;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        if (moduleMethod.selector != 10) {
            return super.match0(moduleMethod, callContext);
        }
        callContext.proc = moduleMethod;
        callContext.pc = 0;
        return 0;
    }

    public static void processCommandLineAssignments() {
        ApplicationMainSupport.processSetProperties();
    }
}
