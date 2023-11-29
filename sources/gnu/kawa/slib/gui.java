package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Display;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Text;
import gnu.kawa.models.Window;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.xml.KAttr;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.misc;
import kawa.standard.Scheme;
import org.slf4j.Marker;

/* compiled from: gui.scm */
public class gui extends ModuleBody {
    public static final gui $instance;
    public static final ModuleMethod Button;
    public static final ModuleMethod Column;
    public static final Macro Image;
    public static final ModuleMethod Label;
    static final Class Lit0 = Color.class;
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve());
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SyntaxRules Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("cell-spacing").readResolve());
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SyntaxRules Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
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
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final IntNum Lit44;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SyntaxRules Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod Row;
    public static final ModuleMethod Text;
    public static final ModuleMethod Window;
    public static final ModuleMethod as$Mncolor;
    public static final ModuleMethod button;
    public static final ModuleMethod image$Mnheight;
    public static final ModuleMethod image$Mnread;
    public static final ModuleMethod image$Mnwidth;
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr;
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr;
    static final Location loc$$St$DtgetHeight;
    static final Location loc$$St$DtgetWidth;
    public static final Macro process$Mnkeywords;
    public static final Macro run$Mnapplication;
    public static final ModuleMethod set$Mncontent;

    static {
        SyntaxRule syntaxRule;
        IntNum make = IntNum.make(1);
        Lit44 = make;
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(CommonProperties.VALUE).readResolve();
        Lit43 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(CommonProperties.NAME).readResolve();
        Lit42 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("invoke").readResolve();
        Lit41 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("getName").readResolve();
        Lit40 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quote_sym).readResolve();
        Lit39 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("attr").readResolve();
        Lit38 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.xml.KAttr>").readResolve();
        Lit37 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME).readResolve();
        Lit36 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol(Marker.ANY_NON_NULL_MARKER).readResolve();
        Lit35 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("loop").readResolve();
        Lit34 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("<object>").readResolve();
        Lit33 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("primitive-array-get").readResolve();
        Lit32 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol(LispLanguage.quasiquote_sym).readResolve();
        Lit31 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("$lookup$").readResolve();
        Lit30 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = simpleSymbol;
        SimpleSymbol simpleSymbol16 = (SimpleSymbol) new SimpleSymbol("arg").readResolve();
        Lit29 = simpleSymbol16;
        SimpleSymbol simpleSymbol17 = simpleSymbol2;
        SimpleSymbol simpleSymbol18 = (SimpleSymbol) new SimpleSymbol("num-args").readResolve();
        Lit28 = simpleSymbol18;
        SimpleSymbol simpleSymbol19 = simpleSymbol3;
        SimpleSymbol simpleSymbol20 = (SimpleSymbol) new SimpleSymbol("i").readResolve();
        Lit27 = simpleSymbol20;
        SimpleSymbol simpleSymbol21 = simpleSymbol6;
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) new SimpleSymbol("<int>").readResolve();
        Lit26 = simpleSymbol22;
        SimpleSymbol simpleSymbol23 = simpleSymbol7;
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) new SimpleSymbol("::").readResolve();
        Lit25 = simpleSymbol24;
        IntNum intNum = make;
        SimpleSymbol simpleSymbol25 = simpleSymbol9;
        SimpleSymbol simpleSymbol26 = simpleSymbol4;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("run-application").readResolve();
        Lit23 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol27;
        SimpleSymbol simpleSymbol29 = simpleSymbol8;
        SimpleSymbol simpleSymbol30 = simpleSymbol12;
        SimpleSymbol simpleSymbol31 = simpleSymbol16;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol27}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1), "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol14, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.kawa.models.Window").readResolve(), Pair.make(Pair.make(simpleSymbol13, Pair.make((SimpleSymbol) new SimpleSymbol("open").readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 749575)}, 0)}, 1);
        Lit24 = syntaxRules;
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) new SimpleSymbol("Window").readResolve();
        Lit22 = simpleSymbol32;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("set-content").readResolve();
        Lit21 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) new SimpleSymbol("Column").readResolve();
        Lit20 = simpleSymbol34;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("Row").readResolve();
        Lit19 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("Text").readResolve();
        Lit18 = simpleSymbol36;
        SyntaxRules syntaxRules2 = syntaxRules;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("Label").readResolve();
        Lit17 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol32;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("image-height").readResolve();
        Lit16 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol33;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("image-width").readResolve();
        Lit15 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol34;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("image-read").readResolve();
        Lit14 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol35;
        SimpleSymbol simpleSymbol45 = simpleSymbol36;
        SimpleSymbol simpleSymbol46 = simpleSymbol37;
        SimpleSymbol simpleSymbol47 = simpleSymbol39;
        SimpleSymbol simpleSymbol48 = simpleSymbol41;
        SimpleSymbol simpleSymbol49 = simpleSymbol43;
        SyntaxRules syntaxRules3 = new SyntaxRules(new Object[]{(SimpleSymbol) new SimpleSymbol("text-field").readResolve()}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018\u0003", new Object[0], 1), "\u0000", "\u0011\u0018\u0004\u0011\u0018\f\u0002", new Object[]{(SimpleSymbol) new SimpleSymbol("make").readResolve(), (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.DrawImage>").readResolve()}, 0)}, 1);
        Lit13 = syntaxRules3;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("Image").readResolve();
        Lit12 = simpleSymbol50;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("Button").readResolve();
        Lit11 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = (SimpleSymbol) new SimpleSymbol("button").readResolve();
        Lit10 = simpleSymbol52;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("as-color").readResolve();
        Lit9 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol50;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("process-keywords").readResolve();
        Lit7 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = simpleSymbol51;
        SimpleSymbol simpleSymbol57 = simpleSymbol52;
        SimpleSymbol simpleSymbol58 = simpleSymbol53;
        SimpleSymbol simpleSymbol59 = simpleSymbol55;
        Object[] objArr = {simpleSymbol55};
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        SimpleSymbol simpleSymbol60 = simpleSymbol11;
        SimpleSymbol simpleSymbol61 = simpleSymbol30;
        PairWithPosition make2 = PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<gnu.expr.Keyword>").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32797);
        SimpleSymbol simpleSymbol62 = simpleSymbol31;
        SimpleSymbol simpleSymbol63 = simpleSymbol29;
        SyntaxPattern syntaxPattern2 = syntaxPattern;
        SimpleSymbol simpleSymbol64 = simpleSymbol26;
        IntNum intNum2 = intNum;
        SimpleSymbol simpleSymbol65 = simpleSymbol25;
        SimpleSymbol simpleSymbol66 = simpleSymbol23;
        SimpleSymbol simpleSymbol67 = simpleSymbol21;
        PairWithPosition make3 = PairWithPosition.make(simpleSymbol67, PairWithPosition.make(simpleSymbol24, PairWithPosition.make(simpleSymbol66, PairWithPosition.make(simpleSymbol62, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57388), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57367), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57364), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57358);
        SimpleSymbol simpleSymbol68 = simpleSymbol19;
        SimpleSymbol simpleSymbol69 = simpleSymbol17;
        SimpleSymbol simpleSymbol70 = simpleSymbol15;
        new SyntaxRule(syntaxPattern2, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\t\u000b\u0018,\b\u0011\u0018\u0004\u0011\u00184\u0011\u0018<\b\u0011\u0018D\u0011\u0018L\b\u0011\u0018\u0004a\b\u0011\u0018T\b\u0011\u0018\\\t\u000b\u0018d\b\u0011\u0018l©\u0011\u0018ty\t\u0013\t\u0003\u0011\u0018|\b\u0011\u0018\t\u000b\u0018\u0018\u0011\u0018i\u0011\u0018¤\u0011\u0018¬\b\t\u0013\t\u0003\u0018´\u0018¼\b\u0011\u0018Ä1\t\u001b\t\u0003\u0018Ì\u0018Ô", new Object[]{(SimpleSymbol) new SimpleSymbol("let").readResolve(), simpleSymbol18, simpleSymbol24, simpleSymbol22, (SimpleSymbol) new SimpleSymbol("field").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol5, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("length").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16425), simpleSymbol10, PairWithPosition.make(PairWithPosition.make(simpleSymbol20, PairWithPosition.make(simpleSymbol24, PairWithPosition.make(simpleSymbol22, PairWithPosition.make(IntNum.make(0), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20509), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20500), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20497), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20496), (SimpleSymbol) new SimpleSymbol("if").readResolve(), PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<").readResolve(), PairWithPosition.make(simpleSymbol20, PairWithPosition.make(simpleSymbol18, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24593), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24591), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24588), simpleSymbol31, PairWithPosition.make(simpleSymbol61, PairWithPosition.make(simpleSymbol60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28710), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28689), PairWithPosition.make(simpleSymbol20, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28725), (SimpleSymbol) new SimpleSymbol("cond").readResolve(), PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol62, make2, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32793), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32782), PairWithPosition.make(PairWithPosition.make(simpleSymbol14, Pair.make((SimpleSymbol) new SimpleSymbol("gnu.expr.Keyword").readResolve(), Pair.make(Pair.make(simpleSymbol13, Pair.make(simpleSymbol64, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40970), PairWithPosition.make(simpleSymbol62, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40995), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40969), PairWithPosition.make(simpleSymbol61, PairWithPosition.make(simpleSymbol60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45087), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45066), PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol20, PairWithPosition.make(intNum2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45107), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45105), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102), PairWithPosition.make(PairWithPosition.make(simpleSymbol10, PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol20, PairWithPosition.make(IntNum.make(2), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49170), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49168), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159), PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol62, PairWithPosition.make(simpleSymbol66, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53270), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53266), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53255), (SimpleSymbol) new SimpleSymbol("let*").readResolve(), PairWithPosition.make(make3, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, PairWithPosition.make(simpleSymbol24, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("<java.lang.String>").readResolve(), PairWithPosition.make(PairWithPosition.make(simpleSymbol68, PairWithPosition.make(simpleSymbol67, PairWithPosition.make(PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol64, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61488), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61483), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61456), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61453), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447), PairWithPosition.make(PairWithPosition.make(simpleSymbol70, PairWithPosition.make(PairWithPosition.make(simpleSymbol68, PairWithPosition.make(simpleSymbol67, PairWithPosition.make(PairWithPosition.make(simpleSymbol5, PairWithPosition.make((SimpleSymbol) new SimpleSymbol("getObjectValue").readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65563), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65558), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57357), PairWithPosition.make(simpleSymbol69, PairWithPosition.make(simpleSymbol70, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69666), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69661), PairWithPosition.make(PairWithPosition.make(simpleSymbol10, PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol20, PairWithPosition.make(intNum2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73746), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73744), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735), (SimpleSymbol) new SimpleSymbol("else").readResolve(), PairWithPosition.make(simpleSymbol62, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 81951), PairWithPosition.make(PairWithPosition.make(simpleSymbol10, PairWithPosition.make(PairWithPosition.make(simpleSymbol65, PairWithPosition.make(simpleSymbol20, PairWithPosition.make(intNum2, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86034), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86032), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023)}, 0);
        Object[] objArr2 = objArr;
        SyntaxRules syntaxRules4 = new SyntaxRules(objArr2, new SyntaxRule[]{syntaxRule}, 4);
        Lit8 = syntaxRules4;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.Column>").readResolve();
        Lit6 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.Row>").readResolve();
        Lit5 = simpleSymbol72;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("*.getHeight").readResolve();
        Lit4 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = (SimpleSymbol) new SimpleSymbol("*.getWidth").readResolve();
        Lit3 = simpleSymbol74;
        gui gui = new gui();
        $instance = gui;
        loc$$St$DtgetWidth = ThreadLocation.getInstance(simpleSymbol74, (Object) null);
        loc$$St$DtgetHeight = ThreadLocation.getInstance(simpleSymbol73, (Object) null);
        loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr = ThreadLocation.getInstance(simpleSymbol72, (Object) null);
        loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr = ThreadLocation.getInstance(simpleSymbol71, (Object) null);
        process$Mnkeywords = Macro.make(simpleSymbol59, syntaxRules4, gui);
        as$Mncolor = new ModuleMethod(gui, 1, simpleSymbol58, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        button = new ModuleMethod(gui, 2, simpleSymbol57, -4096);
        Button = new ModuleMethod(gui, 3, simpleSymbol56, -4096);
        Image = Macro.make(simpleSymbol54, syntaxRules3, gui);
        image$Mnread = new ModuleMethod(gui, 4, simpleSymbol49, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        image$Mnwidth = new ModuleMethod(gui, 5, simpleSymbol48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        image$Mnheight = new ModuleMethod(gui, 6, simpleSymbol47, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Label = new ModuleMethod(gui, 7, simpleSymbol46, -4096);
        Text = new ModuleMethod(gui, 8, simpleSymbol45, -4096);
        Row = new ModuleMethod(gui, 9, simpleSymbol44, -4096);
        Column = new ModuleMethod(gui, 10, simpleSymbol42, -4096);
        set$Mncontent = new ModuleMethod(gui, 11, simpleSymbol40, 8194);
        Window = new ModuleMethod(gui, 12, simpleSymbol38, -4096);
        run$Mnapplication = Macro.make(simpleSymbol28, syntaxRules2, gui);
        gui.run();
    }

    public gui() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 4) {
            if (i != 5) {
                if (i != 6) {
                    return super.match1(moduleMethod, obj, callContext);
                }
                if (!(obj instanceof BufferedImage)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (!(obj instanceof BufferedImage)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (Path.coerceToPathOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Color asColor(Object obj) {
        Color color;
        if (obj instanceof Color) {
            return (Color) obj;
        }
        if (obj instanceof Integer) {
            try {
                color = new Color(((Integer) obj).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Integer.intValue()", 1, obj);
            }
        } else if (!(obj instanceof IntNum)) {
            return (Color) SlotGet.staticField.apply2(Lit0, obj.toString());
        } else {
            color = new Color(IntNum.intValue(obj));
        }
        return color;
    }

    static Object buttonKeyword(Button button2, String str, Object obj) {
        if (str == "foreground") {
            button2.setForeground(asColor(obj));
        } else if (str == "background") {
            button2.setBackground(asColor(obj));
        } else if (str == "action") {
            button2.setAction(obj);
        } else if (str == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            button2.setText(obj == null ? null : obj.toString());
        } else {
            boolean z = true;
            if (str == "disabled") {
                try {
                    if (obj == Boolean.FALSE) {
                        z = false;
                    }
                    button2.setDisabled(z);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.kawa.models.Button.setDisabled(boolean)", 2, obj);
                }
            } else {
                return misc.error$V(Format.formatToString(0, "unknown button attribute ~s", str), new Object[0]);
            }
        }
        return Values.empty;
    }

    static Boolean buttonNonKeyword(Button button2, Object obj) {
        return Boolean.TRUE;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 2) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 3) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 12) {
            switch (i) {
                case 7:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 8:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 9:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 10:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                default:
                    return super.matchN(moduleMethod, objArr, callContext);
            }
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Button button(Object... objArr) {
        Button button2 = new Button();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                try {
                    buttonKeyword(button2, keyword.getName(), objArr[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                }
            } else {
                if (keyword instanceof KAttr) {
                    try {
                        KAttr kAttr = (KAttr) keyword;
                        buttonKeyword(button2, kAttr.getName(), kAttr.getObjectValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "attr", -2, (Object) keyword);
                    }
                } else {
                    buttonNonKeyword(button2, keyword);
                }
                i++;
            }
        }
        return button2;
    }

    public static Button Button(Object... objArr) {
        Button button2 = new Button();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                try {
                    buttonKeyword(button2, keyword.getName(), objArr[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                }
            } else {
                if (keyword instanceof KAttr) {
                    try {
                        KAttr kAttr = (KAttr) keyword;
                        buttonKeyword(button2, kAttr.getName(), kAttr.getObjectValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "attr", -2, (Object) keyword);
                    }
                } else {
                    buttonNonKeyword(button2, keyword);
                }
                i++;
            }
        }
        return button2;
    }

    public static BufferedImage imageRead(Path path) {
        return ImageIO.read(path.openInputStream());
    }

    public static int imageWidth(BufferedImage bufferedImage) {
        try {
            return ((Number) Scheme.applyToArgs.apply2(loc$$St$DtgetWidth.get(), bufferedImage)).intValue();
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 74, 3);
            throw e;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return asColor(obj);
        }
        if (i == 4) {
            try {
                return imageRead(Path.valueOf(obj));
            } catch (ClassCastException e) {
                throw new WrongType(e, "image-read", 1, obj);
            }
        } else if (i == 5) {
            try {
                return Integer.valueOf(imageWidth((BufferedImage) obj));
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "image-width", 1, obj);
            }
        } else if (i != 6) {
            return super.apply1(moduleMethod, obj);
        } else {
            try {
                return Integer.valueOf(imageHeight((BufferedImage) obj));
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "image-height", 1, obj);
            }
        }
    }

    public static int imageHeight(BufferedImage bufferedImage) {
        try {
            return ((Number) Scheme.applyToArgs.apply2(loc$$St$DtgetHeight.get(), bufferedImage)).intValue();
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 77, 3);
            throw e;
        }
    }

    static Object labelKeyword(Label label, String str, Object obj) {
        if (str == Lit1) {
            label.setText(obj == null ? null : obj.toString());
            return Values.empty;
        }
        return misc.error$V(Format.formatToString(0, "unknown label attribute ~s", str), new Object[0]);
    }

    static void labelNonKeyword(Label label, Object obj) {
        label.setText(obj == null ? null : obj.toString());
    }

    public static Label Label(Object... objArr) {
        Label label = new Label();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                try {
                    labelKeyword(label, keyword.getName(), objArr[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                }
            } else {
                if (keyword instanceof KAttr) {
                    try {
                        KAttr kAttr = (KAttr) keyword;
                        labelKeyword(label, kAttr.getName(), kAttr.getObjectValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "attr", -2, (Object) keyword);
                    }
                } else {
                    labelNonKeyword(label, keyword);
                }
                i++;
            }
        }
        return label;
    }

    static Object textKeyword(Text text, String str, Object obj) {
        if (str == Lit1) {
            text.setText(obj == null ? null : obj.toString());
            return Values.empty;
        }
        return misc.error$V(Format.formatToString(0, "unknown text attribute ~s", str), new Object[0]);
    }

    static void textNonKeyword(Text text, Object obj) {
        text.setText(obj == null ? null : obj.toString());
    }

    public static Text Text(Object... objArr) {
        Text text = new Text();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                try {
                    textKeyword(text, keyword.getName(), objArr[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                }
            } else {
                if (keyword instanceof KAttr) {
                    try {
                        KAttr kAttr = (KAttr) keyword;
                        textKeyword(text, kAttr.getName(), kAttr.getObjectValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "attr", -2, (Object) keyword);
                    }
                } else {
                    textNonKeyword(text, keyword);
                }
                i++;
            }
        }
        return text;
    }

    static Object boxKeyword(Box box, String str, Object obj) {
        if (str == Lit2) {
            box.setCellSpacing(obj);
            return Values.empty;
        }
        return misc.error$V(Format.formatToString(0, "unknown box attribute ~s", str), new Object[0]);
    }

    static Model asModel(Object obj) {
        return Display.getInstance().coerceToModel(obj);
    }

    static void boxNonKeyword(Box box, Object obj) {
        box.add(asModel(obj));
    }

    public static Object Row(Object... objArr) {
        try {
            Object apply1 = Invoke.make.apply1(loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr.get());
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Keyword keyword = objArr[i];
                if (keyword instanceof Keyword) {
                    try {
                        try {
                            boxKeyword((Box) apply1, keyword.getName(), objArr[i + 1]);
                            i += 2;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "box-keyword", 0, apply1);
                    }
                } else {
                    if (keyword instanceof KAttr) {
                        try {
                            KAttr kAttr = (KAttr) keyword;
                            try {
                                boxKeyword((Box) apply1, kAttr.getName(), kAttr.getObjectValue());
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "box-keyword", 0, apply1);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "attr", -2, (Object) keyword);
                        }
                    } else {
                        try {
                            boxNonKeyword((Box) apply1, keyword);
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "box-non-keyword", 0, apply1);
                        }
                    }
                    i++;
                }
            }
            return apply1;
        } catch (UnboundLocationException e6) {
            e6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 146, 25);
            throw e6;
        }
    }

    public static Object Column(Object... objArr) {
        try {
            Object apply1 = Invoke.make.apply1(loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr.get());
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Keyword keyword = objArr[i];
                if (keyword instanceof Keyword) {
                    try {
                        try {
                            boxKeyword((Box) apply1, keyword.getName(), objArr[i + 1]);
                            i += 2;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "box-keyword", 0, apply1);
                    }
                } else {
                    if (keyword instanceof KAttr) {
                        try {
                            KAttr kAttr = (KAttr) keyword;
                            try {
                                boxKeyword((Box) apply1, kAttr.getName(), kAttr.getObjectValue());
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "box-keyword", 0, apply1);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "attr", -2, (Object) keyword);
                        }
                    } else {
                        try {
                            boxNonKeyword((Box) apply1, keyword);
                        } catch (ClassCastException e5) {
                            throw new WrongType(e5, "box-non-keyword", 0, apply1);
                        }
                    }
                    i++;
                }
            }
            return apply1;
        } catch (UnboundLocationException e6) {
            e6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 151, 25);
            throw e6;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        if (moduleMethod.selector != 11) {
            return super.apply2(moduleMethod, obj, obj2);
        }
        try {
            setContent((Window) obj, obj2);
            return Values.empty;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-content", 1, obj);
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 11) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        if (!(obj instanceof Window)) {
            return -786431;
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    public static void setContent(Window window, Object obj) {
        window.setContent(obj);
    }

    static Object windowKeyword(Window window, String str, Object obj) {
        if (str == "title") {
            window.setTitle(obj == null ? null : obj.toString());
        } else if (str == "content") {
            window.setContent(obj);
        } else if (str == "menubar") {
            window.setMenuBar(obj);
        } else {
            return misc.error$V(Format.formatToString(0, "unknown window attribute ~s", str), new Object[0]);
        }
        return Values.empty;
    }

    static void windowNonKeyword(Window window, Object obj) {
        window.setContent(obj);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 2) {
            return button(objArr);
        }
        if (i == 3) {
            return Button(objArr);
        }
        if (i == 12) {
            return Window(objArr);
        }
        switch (i) {
            case 7:
                return Label(objArr);
            case 8:
                return Text(objArr);
            case 9:
                return Row(objArr);
            case 10:
                return Column(objArr);
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static Window Window(Object... objArr) {
        Window makeWindow = Display.getInstance().makeWindow();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            Keyword keyword = objArr[i];
            if (keyword instanceof Keyword) {
                try {
                    windowKeyword(makeWindow, keyword.getName(), objArr[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "gnu.expr.Keyword.getName()", 1, (Object) keyword);
                }
            } else {
                if (keyword instanceof KAttr) {
                    try {
                        KAttr kAttr = (KAttr) keyword;
                        windowKeyword(makeWindow, kAttr.getName(), kAttr.getObjectValue());
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "attr", -2, (Object) keyword);
                    }
                } else {
                    windowNonKeyword(makeWindow, keyword);
                }
                i++;
            }
        }
        return makeWindow;
    }
}
