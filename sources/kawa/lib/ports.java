package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.InPort;
import gnu.mapping.LocationProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.Writer;
import kawa.standard.Scheme;
import kawa.standard.char_ready_p;
import kawa.standard.read_line;

/* compiled from: ports.scm */
public class ports extends ModuleBody {
    public static final ports $instance;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("current-input-port").readResolve());
    static final ClassType Lit1 = ClassType.make("gnu.mapping.InPort");
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("current-output-port").readResolve());
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final ClassType Lit3 = ClassType.make("gnu.mapping.OutPort");
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("current-error-port").readResolve());
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final Keyword Lit5 = Keyword.make("setter");
    static final IntNum Lit6 = IntNum.make(1);
    static final Char Lit7 = Char.make(10);
    static final Char Lit8 = Char.make(32);
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("trim").readResolve());
    public static final ModuleMethod call$Mnwith$Mninput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mninput$Mnstring;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnstring;
    public static final ModuleMethod char$Mnready$Qu;
    public static final ModuleMethod close$Mninput$Mnport;
    public static final ModuleMethod close$Mnoutput$Mnport;
    public static final LocationProc current$Mnerror$Mnport = null;
    public static final LocationProc current$Mninput$Mnport = null;
    public static final LocationProc current$Mnoutput$Mnport = null;
    public static final ModuleMethod default$Mnprompter;
    public static final ModuleMethod display;
    public static final ModuleMethod eof$Mnobject$Qu;
    public static final ModuleMethod force$Mnoutput;
    public static final ModuleMethod get$Mnoutput$Mnstring;
    public static final ModuleMethod input$Mnport$Mncolumn$Mnnumber;
    public static final GenericProc input$Mnport$Mnline$Mnnumber = null;
    static final ModuleMethod input$Mnport$Mnline$Mnnumber$Fn5;
    public static final GenericProc input$Mnport$Mnprompter = null;
    static final ModuleMethod input$Mnport$Mnprompter$Fn6;
    public static final ModuleMethod input$Mnport$Mnread$Mnstate;
    public static final ModuleMethod input$Mnport$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod newline;
    public static final ModuleMethod open$Mninput$Mnfile;
    public static final ModuleMethod open$Mninput$Mnstring;
    public static final ModuleMethod open$Mnoutput$Mnfile;
    public static final ModuleMethod open$Mnoutput$Mnstring;
    public static final ModuleMethod output$Mnport$Qu;
    public static final ModuleMethod port$Mncolumn;
    public static final GenericProc port$Mnline = null;
    static final ModuleMethod port$Mnline$Fn4;
    public static final ModuleMethod read;
    public static final ModuleMethod read$Mnline;
    public static final ModuleMethod set$Mninput$Mnport$Mnline$Mnnumber$Ex;
    public static final ModuleMethod set$Mninput$Mnport$Mnprompter$Ex;
    public static final ModuleMethod set$Mnport$Mnline$Ex;
    public static final ModuleMethod transcript$Mnoff;
    public static final ModuleMethod transcript$Mnon;
    public static final ModuleMethod with$Mninput$Mnfrom$Mnfile;
    public static final ModuleMethod with$Mnoutput$Mnto$Mnfile;
    public static final ModuleMethod write;
    public static final ModuleMethod write$Mnchar;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("transcript-off").readResolve();
        Lit45 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("transcript-on").readResolve();
        Lit44 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("read-line").readResolve();
        Lit43 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("read").readResolve();
        Lit42 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("close-output-port").readResolve();
        Lit41 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("close-input-port").readResolve();
        Lit40 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("input-port-prompter").readResolve();
        Lit39 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("set-input-port-prompter!").readResolve();
        Lit38 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("default-prompter").readResolve();
        Lit37 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("input-port-column-number").readResolve();
        Lit36 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("port-column").readResolve();
        Lit35 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("input-port-line-number").readResolve();
        Lit34 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("set-input-port-line-number!").readResolve();
        Lit33 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("port-line").readResolve();
        Lit32 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("set-port-line!").readResolve();
        Lit31 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("input-port-read-state").readResolve();
        Lit30 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("display").readResolve();
        Lit29 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("write").readResolve();
        Lit28 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("char-ready?").readResolve();
        Lit27 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("eof-object?").readResolve();
        Lit26 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("newline").readResolve();
        Lit25 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("force-output").readResolve();
        Lit24 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("call-with-output-string").readResolve();
        Lit23 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol9;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("call-with-input-string").readResolve();
        Lit22 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol10;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("get-output-string").readResolve();
        Lit21 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol11;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("open-output-string").readResolve();
        Lit20 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol12;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("open-input-string").readResolve();
        Lit19 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol13;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("write-char").readResolve();
        Lit18 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol14;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("output-port?").readResolve();
        Lit17 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol15;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("input-port?").readResolve();
        Lit16 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = simpleSymbol17;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("with-output-to-file").readResolve();
        Lit15 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = simpleSymbol19;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("with-input-from-file").readResolve();
        Lit14 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = simpleSymbol21;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("call-with-output-file").readResolve();
        Lit13 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = simpleSymbol23;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("call-with-input-file").readResolve();
        Lit12 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol25;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("open-output-file").readResolve();
        Lit11 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = simpleSymbol27;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("open-input-file").readResolve();
        Lit10 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = simpleSymbol29;
        ports ports = new ports();
        $instance = ports;
        open$Mninput$Mnfile = new ModuleMethod(ports, 1, simpleSymbol57, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mnoutput$Mnfile = new ModuleMethod(ports, 2, simpleSymbol55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        call$Mnwith$Mninput$Mnfile = new ModuleMethod(ports, 3, simpleSymbol53, 8194);
        call$Mnwith$Mnoutput$Mnfile = new ModuleMethod(ports, 4, simpleSymbol51, 8194);
        with$Mninput$Mnfrom$Mnfile = new ModuleMethod(ports, 5, simpleSymbol49, 8194);
        with$Mnoutput$Mnto$Mnfile = new ModuleMethod(ports, 6, simpleSymbol47, 8194);
        input$Mnport$Qu = new ModuleMethod(ports, 7, simpleSymbol45, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        output$Mnport$Qu = new ModuleMethod(ports, 8, simpleSymbol43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn1 = new ModuleMethod(ports, 9, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn2 = new ModuleMethod(ports, 10, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn3 = new ModuleMethod(ports, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        write$Mnchar = new ModuleMethod(ports, 12, simpleSymbol41, 8193);
        open$Mninput$Mnstring = new ModuleMethod(ports, 14, simpleSymbol39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mnoutput$Mnstring = new ModuleMethod(ports, 15, simpleSymbol37, 0);
        get$Mnoutput$Mnstring = new ModuleMethod(ports, 16, simpleSymbol35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        call$Mnwith$Mninput$Mnstring = new ModuleMethod(ports, 17, simpleSymbol33, 8194);
        call$Mnwith$Mnoutput$Mnstring = new ModuleMethod(ports, 18, simpleSymbol31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        force$Mnoutput = new ModuleMethod(ports, 19, simpleSymbol58, 4096);
        newline = new ModuleMethod(ports, 21, simpleSymbol56, 4096);
        eof$Mnobject$Qu = new ModuleMethod(ports, 23, simpleSymbol54, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnready$Qu = new ModuleMethod(ports, 24, simpleSymbol52, 4096);
        write = new ModuleMethod(ports, 26, simpleSymbol50, 8193);
        display = new ModuleMethod(ports, 28, simpleSymbol48, 8193);
        input$Mnport$Mnread$Mnstate = new ModuleMethod(ports, 30, simpleSymbol46, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mnport$Mnline$Ex = new ModuleMethod(ports, 31, simpleSymbol44, 8194);
        port$Mnline$Fn4 = new ModuleMethod(ports, 32, simpleSymbol42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mninput$Mnport$Mnline$Mnnumber$Ex = new ModuleMethod(ports, 33, simpleSymbol40, 8194);
        input$Mnport$Mnline$Mnnumber$Fn5 = new ModuleMethod(ports, 34, simpleSymbol38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        port$Mncolumn = new ModuleMethod(ports, 35, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Mncolumn$Mnnumber = new ModuleMethod(ports, 36, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        default$Mnprompter = new ModuleMethod(ports, 37, simpleSymbol32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        set$Mninput$Mnport$Mnprompter$Ex = new ModuleMethod(ports, 38, simpleSymbol30, 8194);
        input$Mnport$Mnprompter$Fn6 = new ModuleMethod(ports, 39, simpleSymbol28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        close$Mninput$Mnport = new ModuleMethod(ports, 40, simpleSymbol26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        close$Mnoutput$Mnport = new ModuleMethod(ports, 41, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        read = new ModuleMethod(ports, 42, simpleSymbol22, 4096);
        read$Mnline = new ModuleMethod(ports, 44, simpleSymbol20, 8192);
        transcript$Mnon = new ModuleMethod(ports, 47, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transcript$Mnoff = new ModuleMethod(ports, 48, simpleSymbol16, 0);
        ports.run();
    }

    public ports() {
        ModuleInfo.register(this);
    }

    public static void display(Object obj) {
        display(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void forceOutput() {
        forceOutput(current$Mnoutput$Mnport.apply0());
    }

    public static boolean isCharReady() {
        return isCharReady(current$Mninput$Mnport.apply0());
    }

    public static void newline() {
        newline(current$Mnoutput$Mnport.apply0());
    }

    public static Object read() {
        return read((InPort) current$Mninput$Mnport.apply0());
    }

    public static Object readLine() {
        return readLine((LineBufferedReader) current$Mninput$Mnport.apply0(), Lit9);
    }

    public static Object readLine(LineBufferedReader lineBufferedReader) {
        return readLine(lineBufferedReader, Lit9);
    }

    public static void write(Object obj) {
        write(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void writeChar(Object obj) {
        writeChar(obj, OutPort.outDefault());
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        LocationProc makeNamed = LocationProc.makeNamed(Lit0, InPort.inLocation);
        current$Mninput$Mnport = makeNamed;
        makeNamed.pushConverter(lambda$Fn1);
        LocationProc makeNamed2 = LocationProc.makeNamed(Lit2, OutPort.outLocation);
        current$Mnoutput$Mnport = makeNamed2;
        makeNamed2.pushConverter(lambda$Fn2);
        LocationProc makeNamed3 = LocationProc.makeNamed(Lit4, OutPort.errLocation);
        current$Mnerror$Mnport = makeNamed3;
        makeNamed3.pushConverter(lambda$Fn3);
        GenericProc genericProc = new GenericProc("port-line");
        port$Mnline = genericProc;
        Keyword keyword = Lit5;
        genericProc.setProperties(new Object[]{keyword, set$Mnport$Mnline$Ex, port$Mnline$Fn4});
        GenericProc genericProc2 = new GenericProc("input-port-line-number");
        input$Mnport$Mnline$Mnnumber = genericProc2;
        genericProc2.setProperties(new Object[]{keyword, set$Mninput$Mnport$Mnline$Mnnumber$Ex, input$Mnport$Mnline$Mnnumber$Fn5});
        GenericProc genericProc3 = new GenericProc("input-port-prompter");
        input$Mnport$Mnprompter = genericProc3;
        genericProc3.setProperties(new Object[]{keyword, set$Mninput$Mnport$Mnprompter$Ex, input$Mnport$Mnprompter$Fn6});
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 12:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 14:
                if (!(obj instanceof CharSequence)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                if (!(obj instanceof CharArrayOutPort)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 32:
                if (!(obj instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 34:
                if (!(obj instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 35:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 36:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 37:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 39:
                if (!(obj instanceof TtyInPort)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 40:
                if (!(obj instanceof InPort)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 41:
                if (!(obj instanceof OutPort)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 42:
                if (!(obj instanceof InPort)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                if (!(obj instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 47:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static InPort openInputFile(Path path) {
        return InPort.openFile(path);
    }

    public static OutPort openOutputFile(Path path) {
        return OutPort.openFile(path);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        if (i == 12) {
                            callContext.value1 = obj;
                            if (!(obj2 instanceof OutPort)) {
                                return -786430;
                            }
                            callContext.value2 = obj2;
                            callContext.proc = moduleMethod;
                            callContext.pc = 2;
                            return 0;
                        } else if (i != 17) {
                            if (i == 26) {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            } else if (i == 28) {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            } else if (i == 31) {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            } else if (i == 33) {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            } else if (i != 38) {
                                if (i != 44) {
                                    return super.match2(moduleMethod, obj, obj2, callContext);
                                }
                                if (!(obj instanceof LineBufferedReader)) {
                                    return -786431;
                                }
                                callContext.value1 = obj;
                                if (!(obj2 instanceof Symbol)) {
                                    return -786430;
                                }
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            } else if (!(obj instanceof TtyInPort)) {
                                return -786431;
                            } else {
                                callContext.value1 = obj;
                                if (!(obj2 instanceof Procedure)) {
                                    return -786430;
                                }
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            }
                        } else if (!(obj instanceof CharSequence)) {
                            return -786431;
                        } else {
                            callContext.value1 = obj;
                            if (!(obj2 instanceof Procedure)) {
                                return -786430;
                            }
                            callContext.value2 = obj2;
                            callContext.proc = moduleMethod;
                            callContext.pc = 2;
                            return 0;
                        }
                    } else if (Path.coerceToPathOrNull(obj) == null) {
                        return -786431;
                    } else {
                        callContext.value1 = obj;
                        if (!(obj2 instanceof Procedure)) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    }
                } else if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Procedure)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                }
            } else if (Path.coerceToPathOrNull(obj) == null) {
                return -786431;
            } else {
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (Path.coerceToPathOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof Procedure)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object callWithInputFile(Path path, Procedure procedure) {
        InPort openInputFile = openInputFile(path);
        try {
            return procedure.apply1(openInputFile);
        } finally {
            closeInputPort(openInputFile);
        }
    }

    public static Object callWithOutputFile(Path path, Procedure procedure) {
        OutPort openOutputFile = openOutputFile(path);
        try {
            return procedure.apply1(openOutputFile);
        } finally {
            closeOutputPort(openOutputFile);
        }
    }

    public static Object withInputFromFile(Path path, Procedure procedure) {
        InPort openFile = InPort.openFile(path);
        InPort inDefault = InPort.inDefault();
        try {
            InPort.setInDefault(openFile);
            return procedure.apply0();
        } finally {
            InPort.setInDefault(inDefault);
            openFile.close();
        }
    }

    public static Object withOutputToFile(Path path, Procedure procedure) {
        OutPort openFile = OutPort.openFile(path);
        OutPort outDefault = OutPort.outDefault();
        try {
            OutPort.setOutDefault(openFile);
            return procedure.apply0();
        } finally {
            OutPort.setOutDefault(outDefault);
            openFile.close();
        }
    }

    public static boolean isInputPort(Object obj) {
        return obj instanceof InPort;
    }

    public static boolean isOutputPort(Object obj) {
        return obj instanceof OutPort;
    }

    static Object lambda1(Object obj) {
        try {
            return (InPort) obj;
        } catch (ClassCastException e) {
            WrongType make = WrongType.make(e, (Procedure) current$Mninput$Mnport, 1, obj);
            make.expectedType = Lit1;
            throw make;
        }
    }

    static Object lambda2(Object obj) {
        try {
            return (OutPort) obj;
        } catch (ClassCastException e) {
            WrongType make = WrongType.make(e, (Procedure) current$Mnoutput$Mnport, 1, obj);
            make.expectedType = Lit3;
            throw make;
        }
    }

    static Object lambda3(Object obj) {
        try {
            return (OutPort) obj;
        } catch (ClassCastException e) {
            WrongType make = WrongType.make(e, (Procedure) current$Mnerror$Mnport, 1, obj);
            make.expectedType = Lit3;
            throw make;
        }
    }

    public static void writeChar(Object obj, OutPort outPort) {
        try {
            Char.print(characters.char$To$Integer((Char) obj), outPort);
        } catch (ClassCastException e) {
            throw new WrongType(e, "char->integer", 1, obj);
        }
    }

    public static InPort openInputString(CharSequence charSequence) {
        return new CharArrayInPort(charSequence == null ? null : charSequence.toString());
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 15) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 19) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 21) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 24) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 42) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 44) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i != 48) {
            return super.match0(moduleMethod, callContext);
        } else {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    public static CharArrayOutPort openOutputString() {
        return new CharArrayOutPort();
    }

    public static FString getOutputString(CharArrayOutPort charArrayOutPort) {
        return new FString(charArrayOutPort.toCharArray());
    }

    public static Object callWithInputString(CharSequence charSequence, Procedure procedure) {
        CharArrayInPort charArrayInPort = new CharArrayInPort(charSequence == null ? null : charSequence.toString());
        Object apply1 = procedure.apply1(charArrayInPort);
        closeInputPort(charArrayInPort);
        return apply1;
    }

    public static Object callWithOutputString(Procedure procedure) {
        CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
        procedure.apply1(charArrayOutPort);
        char[] charArray = charArrayOutPort.toCharArray();
        charArrayOutPort.close();
        return new FString(charArray);
    }

    public static void forceOutput(Object obj) {
        try {
            ((Writer) obj).flush();
        } catch (ClassCastException e) {
            throw new WrongType(e, "java.io.Writer.flush()", 1, obj);
        }
    }

    public static void newline(Object obj) {
        try {
            ((OutPort) obj).println();
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.OutPort.println()", 1, obj);
        }
    }

    public static boolean isEofObject(Object obj) {
        return obj == EofClass.eofValue;
    }

    public static boolean isCharReady(Object obj) {
        return char_ready_p.ready(obj);
    }

    public static void write(Object obj, Object obj2) {
        try {
            Scheme.writeFormat.format(obj, (Consumer) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj2);
        }
    }

    public static void display(Object obj, Object obj2) {
        try {
            Scheme.displayFormat.format(obj, (Consumer) obj2);
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj2);
        }
    }

    public static char inputPortReadState(Object obj) {
        try {
            return ((InPort) obj).getReadState();
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.mapping.InPort.getReadState()", 1, obj);
        }
    }

    public static void setPortLine$Ex(Object obj, Object obj2) {
        try {
            try {
                ((LineBufferedReader) obj).setLineNumber(((Number) obj2).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.text.LineBufferedReader.setLineNumber(int)", 2, obj2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "gnu.text.LineBufferedReader.setLineNumber(int)", 1, obj);
        }
    }

    public static int portLine(LineBufferedReader lineBufferedReader) {
        return lineBufferedReader.getLineNumber();
    }

    public static void setInputPortLineNumber$Ex(Object obj, Object obj2) {
        setPortLine$Ex(obj, AddOp.$Mn.apply2(obj2, Lit6));
    }

    public static Object inputPortLineNumber(LineBufferedReader lineBufferedReader) {
        return AddOp.$Pl.apply2(Lit6, port$Mnline.apply1(lineBufferedReader));
    }

    public static int portColumn(Object obj) {
        try {
            return ((LineBufferedReader) obj).getColumnNumber();
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.text.LineBufferedReader.getColumnNumber()", 1, obj);
        }
    }

    public static int inputPortColumnNumber(Object obj) {
        return portColumn(obj) + 1;
    }

    public static Object defaultPrompter(Object obj) {
        Object obj2;
        char inputPortReadState = inputPortReadState(obj);
        if (characters.isChar$Eq(Char.make(inputPortReadState), Lit7)) {
            return "";
        }
        Object[] objArr = new Object[3];
        if (characters.isChar$Eq(Char.make(inputPortReadState), Lit8)) {
            obj2 = "#|kawa:";
        } else {
            obj2 = strings.stringAppend("#|", strings.makeString(1, Char.make(inputPortReadState)), "---:");
        }
        objArr[0] = obj2;
        Object apply1 = input$Mnport$Mnline$Mnnumber.apply1(obj);
        try {
            objArr[1] = numbers.number$To$String((Number) apply1);
            objArr[2] = "|# ";
            return strings.stringAppend(objArr);
        } catch (ClassCastException e) {
            throw new WrongType(e, "number->string", 0, apply1);
        }
    }

    public static void setInputPortPrompter$Ex(TtyInPort ttyInPort, Procedure procedure) {
        ttyInPort.setPrompter(procedure);
    }

    public static Procedure inputPortPrompter(TtyInPort ttyInPort) {
        return ttyInPort.getPrompter();
    }

    public static Object closeInputPort(InPort inPort) {
        inPort.close();
        return Values.empty;
    }

    public static Object closeOutputPort(OutPort outPort) {
        outPort.close();
        return Values.empty;
    }

    public static Object read(InPort inPort) {
        LispReader lispReader = new LispReader(inPort);
        try {
            Object readObject = lispReader.readObject();
            if (!lispReader.seenErrors()) {
                return readObject;
            }
            throw new SyntaxException(lispReader.getMessages());
        } catch (SyntaxException e) {
            e.setHeader("syntax error in read:");
            throw e;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 3) {
            try {
                try {
                    return callWithInputFile(Path.valueOf(obj), (Procedure) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "call-with-input-file", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "call-with-input-file", 1, obj);
            }
        } else if (i == 4) {
            try {
                try {
                    return callWithOutputFile(Path.valueOf(obj), (Procedure) obj2);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "call-with-output-file", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "call-with-output-file", 1, obj);
            }
        } else if (i == 5) {
            try {
                try {
                    return withInputFromFile(Path.valueOf(obj), (Procedure) obj2);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "with-input-from-file", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "with-input-from-file", 1, obj);
            }
        } else if (i == 6) {
            try {
                try {
                    return withOutputToFile(Path.valueOf(obj), (Procedure) obj2);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "with-output-to-file", 2, obj2);
                }
            } catch (ClassCastException e8) {
                throw new WrongType(e8, "with-output-to-file", 1, obj);
            }
        } else if (i == 12) {
            try {
                writeChar(obj, (OutPort) obj2);
                return Values.empty;
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "write-char", 2, obj2);
            }
        } else if (i == 17) {
            try {
                try {
                    return callWithInputString((CharSequence) obj, (Procedure) obj2);
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "call-with-input-string", 2, obj2);
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "call-with-input-string", 1, obj);
            }
        } else if (i == 26) {
            write(obj, obj2);
            return Values.empty;
        } else if (i == 28) {
            display(obj, obj2);
            return Values.empty;
        } else if (i == 31) {
            setPortLine$Ex(obj, obj2);
            return Values.empty;
        } else if (i == 33) {
            setInputPortLineNumber$Ex(obj, obj2);
            return Values.empty;
        } else if (i == 38) {
            try {
                try {
                    setInputPortPrompter$Ex((TtyInPort) obj, (Procedure) obj2);
                    return Values.empty;
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "set-input-port-prompter!", 2, obj2);
                }
            } catch (ClassCastException e13) {
                throw new WrongType(e13, "set-input-port-prompter!", 1, obj);
            }
        } else if (i != 44) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    return readLine((LineBufferedReader) obj, (Symbol) obj2);
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "read-line", 2, obj2);
                }
            } catch (ClassCastException e15) {
                throw new WrongType(e15, "read-line", 1, obj);
            }
        }
    }

    public static Object readLine(LineBufferedReader lineBufferedReader, Symbol symbol) {
        return read_line.apply(lineBufferedReader, symbol == null ? null : symbol.toString());
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                try {
                    return openInputFile(Path.valueOf(obj));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "open-input-file", 1, obj);
                }
            case 2:
                try {
                    return openOutputFile(Path.valueOf(obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "open-output-file", 1, obj);
                }
            case 7:
                return isInputPort(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return isOutputPort(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return lambda1(obj);
            case 10:
                return lambda2(obj);
            case 11:
                return lambda3(obj);
            case 12:
                writeChar(obj);
                return Values.empty;
            case 14:
                try {
                    return openInputString((CharSequence) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "open-input-string", 1, obj);
                }
            case 16:
                try {
                    return getOutputString((CharArrayOutPort) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "get-output-string", 1, obj);
                }
            case 18:
                try {
                    return callWithOutputString((Procedure) obj);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "call-with-output-string", 1, obj);
                }
            case 19:
                forceOutput(obj);
                return Values.empty;
            case 21:
                newline(obj);
                return Values.empty;
            case 23:
                return isEofObject(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 24:
                return isCharReady(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 26:
                write(obj);
                return Values.empty;
            case 28:
                display(obj);
                return Values.empty;
            case 30:
                return Char.make(inputPortReadState(obj));
            case 32:
                try {
                    return Integer.valueOf(portLine((LineBufferedReader) obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "port-line", 1, obj);
                }
            case 34:
                try {
                    return inputPortLineNumber((LineBufferedReader) obj);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "input-port-line-number", 1, obj);
                }
            case 35:
                return Integer.valueOf(portColumn(obj));
            case 36:
                return Integer.valueOf(inputPortColumnNumber(obj));
            case 37:
                return defaultPrompter(obj);
            case 39:
                try {
                    return inputPortPrompter((TtyInPort) obj);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "input-port-prompter", 1, obj);
                }
            case 40:
                try {
                    return closeInputPort((InPort) obj);
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "close-input-port", 1, obj);
                }
            case 41:
                try {
                    return closeOutputPort((OutPort) obj);
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "close-output-port", 1, obj);
                }
            case 42:
                try {
                    return read((InPort) obj);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "read", 1, obj);
                }
            case 44:
                try {
                    return readLine((LineBufferedReader) obj);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "read-line", 1, obj);
                }
            case 47:
                transcriptOn(obj);
                return Values.empty;
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static void transcriptOn(Object obj) {
        OutPort.setLogFile(obj.toString());
    }

    public Object apply0(ModuleMethod moduleMethod) {
        int i = moduleMethod.selector;
        if (i == 15) {
            return openOutputString();
        }
        if (i == 19) {
            forceOutput();
            return Values.empty;
        } else if (i == 21) {
            newline();
            return Values.empty;
        } else if (i == 24) {
            return isCharReady() ? Boolean.TRUE : Boolean.FALSE;
        } else {
            if (i == 42) {
                return read();
            }
            if (i == 44) {
                return readLine();
            }
            if (i != 48) {
                return super.apply0(moduleMethod);
            }
            transcriptOff();
            return Values.empty;
        }
    }

    public static void transcriptOff() {
        OutPort.closeLogFile();
    }
}
