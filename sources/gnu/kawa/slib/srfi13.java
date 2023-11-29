package gnu.kawa.slib;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.appinventor.components.common.ComponentConstants;
import com.google.appinventor.components.common.YaVersion;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.BitwiseOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.Invoke;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.Telnet;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;
import org.jose4j.jwk.EllipticCurveJsonWebKey;

/* compiled from: srfi13.scm */
public class srfi13 extends ModuleBody {
    public static final ModuleMethod $Pccheck$Mnbounds;
    public static final ModuleMethod $Pcfinish$Mnstring$Mnconcatenate$Mnreverse;
    public static final ModuleMethod $Pckmp$Mnsearch;
    public static final ModuleMethod $Pcmultispan$Mnrepcopy$Ex;
    public static final ModuleMethod $Pcstring$Mncompare;
    public static final ModuleMethod $Pcstring$Mncompare$Mnci;
    public static final ModuleMethod $Pcstring$Mncopy$Ex;
    public static final ModuleMethod $Pcstring$Mnhash;
    public static final ModuleMethod $Pcstring$Mnmap;
    public static final ModuleMethod $Pcstring$Mnmap$Ex;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnci$Qu;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnlength;
    public static final ModuleMethod $Pcstring$Mnprefix$Mnlength$Mnci;
    public static final ModuleMethod $Pcstring$Mnprefix$Qu;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnci$Qu;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength;
    public static final ModuleMethod $Pcstring$Mnsuffix$Mnlength$Mnci;
    public static final ModuleMethod $Pcstring$Mnsuffix$Qu;
    public static final ModuleMethod $Pcstring$Mntitlecase$Ex;
    public static final ModuleMethod $Pcsubstring$Slshared;
    public static final srfi13 $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final IntNum Lit10 = IntNum.make(4194304);
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final SimpleSymbol Lit106;
    static final SimpleSymbol Lit107;
    static final SimpleSymbol Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("whitespace").readResolve());
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final SimpleSymbol Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final SimpleSymbol Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final Char Lit12 = Char.make(32);
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SimpleSymbol Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SimpleSymbol Lit129;
    static final IntNum Lit13 = IntNum.make(-1);
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit136;
    static final SimpleSymbol Lit137;
    static final SimpleSymbol Lit138;
    static final SimpleSymbol Lit139;
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("graphic").readResolve());
    static final SimpleSymbol Lit140;
    static final SimpleSymbol Lit141;
    static final SimpleSymbol Lit142;
    static final SimpleSymbol Lit143;
    static final SimpleSymbol Lit144;
    static final SimpleSymbol Lit145;
    static final SimpleSymbol Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final SimpleSymbol Lit149;
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("infix").readResolve());
    static final SimpleSymbol Lit150;
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("strict-infix").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("prefix").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("suffix").readResolve());
    static final SimpleSymbol Lit19;
    static final IntNum Lit2 = IntNum.make(4096);
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
    static final IntNum Lit3 = IntNum.make(40);
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
    static final IntNum Lit4 = IntNum.make(4096);
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SyntaxRules Lit42;
    static final SimpleSymbol Lit43;
    static final SyntaxRules Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final IntNum Lit5 = IntNum.make(65536);
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final IntNum Lit6 = IntNum.make(37);
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7 = IntNum.make(4194304);
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final IntNum Lit8 = IntNum.make(4194304);
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SimpleSymbol Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final IntNum Lit9 = IntNum.make(4194304);
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final ModuleMethod check$Mnsubstring$Mnspec;
    public static final ModuleMethod kmp$Mnstep;
    static final ModuleMethod lambda$Fn100;
    static final ModuleMethod lambda$Fn105;
    static final ModuleMethod lambda$Fn106;
    static final ModuleMethod lambda$Fn111;
    static final ModuleMethod lambda$Fn116;
    static final ModuleMethod lambda$Fn117;
    static final ModuleMethod lambda$Fn122;
    static final ModuleMethod lambda$Fn123;
    static final ModuleMethod lambda$Fn128;
    static final ModuleMethod lambda$Fn133;
    static final ModuleMethod lambda$Fn138;
    static final ModuleMethod lambda$Fn163;
    static final ModuleMethod lambda$Fn166;
    static final ModuleMethod lambda$Fn17;
    static final ModuleMethod lambda$Fn18;
    static final ModuleMethod lambda$Fn210;
    static final ModuleMethod lambda$Fn216;
    static final ModuleMethod lambda$Fn220;
    static final ModuleMethod lambda$Fn27;
    static final ModuleMethod lambda$Fn5;
    static final ModuleMethod lambda$Fn72;
    static final ModuleMethod lambda$Fn73;
    static final ModuleMethod lambda$Fn78;
    static final ModuleMethod lambda$Fn83;
    static final ModuleMethod lambda$Fn84;
    static final ModuleMethod lambda$Fn89;
    static final ModuleMethod lambda$Fn90;
    static final ModuleMethod lambda$Fn95;
    public static final Macro let$Mnstring$Mnstart$Plend;
    public static final Macro let$Mnstring$Mnstart$Plend2;
    static final Location loc$$Cloptional;
    static final Location loc$base;
    static final Location loc$bound;
    static final Location loc$c$Eq;
    static final Location loc$char$Mncased$Qu;
    static final Location loc$char$Mnset;
    static final Location loc$char$Mnset$Mncontains$Qu;
    static final Location loc$char$Mnset$Qu;
    static final Location loc$check$Mnarg;
    static final Location loc$criterion;
    static final Location loc$delim;
    static final Location loc$end;
    static final Location loc$final;
    static final Location loc$grammar;
    static final Location loc$let$Mnoptionals$St;
    static final Location loc$make$Mnfinal;
    static final Location loc$p$Mnstart;
    static final Location loc$rest;
    static final Location loc$s$Mnend;
    static final Location loc$s$Mnstart;
    static final Location loc$start;
    static final Location loc$token$Mnchars;
    public static final ModuleMethod make$Mnkmp$Mnrestart$Mnvector;
    public static final ModuleMethod reverse$Mnlist$Mn$Grstring;
    public static final ModuleMethod string$Eq;
    public static final ModuleMethod string$Gr;
    public static final ModuleMethod string$Gr$Eq;
    public static final ModuleMethod string$Ls;
    public static final ModuleMethod string$Ls$Eq;
    public static final ModuleMethod string$Ls$Gr;
    public static final ModuleMethod string$Mn$Grlist;
    public static final ModuleMethod string$Mnany;
    public static final ModuleMethod string$Mnappend$Slshared;
    public static final ModuleMethod string$Mnci$Eq;
    public static final ModuleMethod string$Mnci$Gr;
    public static final ModuleMethod string$Mnci$Gr$Eq;
    public static final ModuleMethod string$Mnci$Ls;
    public static final ModuleMethod string$Mnci$Ls$Eq;
    public static final ModuleMethod string$Mnci$Ls$Gr;
    public static final ModuleMethod string$Mncompare;
    public static final ModuleMethod string$Mncompare$Mnci;
    public static final ModuleMethod string$Mnconcatenate;
    public static final ModuleMethod string$Mnconcatenate$Mnreverse;
    public static final ModuleMethod string$Mnconcatenate$Mnreverse$Slshared;
    public static final ModuleMethod string$Mnconcatenate$Slshared;
    public static final ModuleMethod string$Mncontains;
    public static final ModuleMethod string$Mncontains$Mnci;
    public static final ModuleMethod string$Mncopy;
    public static final ModuleMethod string$Mncopy$Ex;
    public static final ModuleMethod string$Mncount;
    public static final ModuleMethod string$Mndelete;
    public static final ModuleMethod string$Mndowncase;
    public static final ModuleMethod string$Mndowncase$Ex;
    public static final ModuleMethod string$Mndrop;
    public static final ModuleMethod string$Mndrop$Mnright;
    public static final ModuleMethod string$Mnevery;
    public static final ModuleMethod string$Mnfill$Ex;
    public static final ModuleMethod string$Mnfilter;
    public static final ModuleMethod string$Mnfold;
    public static final ModuleMethod string$Mnfold$Mnright;
    public static final ModuleMethod string$Mnfor$Mneach;
    public static final ModuleMethod string$Mnfor$Mneach$Mnindex;
    public static final ModuleMethod string$Mnhash;
    public static final ModuleMethod string$Mnhash$Mnci;
    public static final ModuleMethod string$Mnindex;
    public static final ModuleMethod string$Mnindex$Mnright;
    public static final ModuleMethod string$Mnjoin;
    public static final ModuleMethod string$Mnkmp$Mnpartial$Mnsearch;
    public static final ModuleMethod string$Mnmap;
    public static final ModuleMethod string$Mnmap$Ex;
    public static final ModuleMethod string$Mnnull$Qu;
    public static final ModuleMethod string$Mnpad;
    public static final ModuleMethod string$Mnpad$Mnright;
    public static final ModuleMethod string$Mnparse$Mnfinal$Mnstart$Plend;
    public static final ModuleMethod string$Mnparse$Mnstart$Plend;
    public static final ModuleMethod string$Mnprefix$Mnci$Qu;
    public static final ModuleMethod string$Mnprefix$Mnlength;
    public static final ModuleMethod string$Mnprefix$Mnlength$Mnci;
    public static final ModuleMethod string$Mnprefix$Qu;
    public static final ModuleMethod string$Mnreplace;
    public static final ModuleMethod string$Mnreverse;
    public static final ModuleMethod string$Mnreverse$Ex;
    public static final ModuleMethod string$Mnskip;
    public static final ModuleMethod string$Mnskip$Mnright;
    public static final ModuleMethod string$Mnsuffix$Mnci$Qu;
    public static final ModuleMethod string$Mnsuffix$Mnlength;
    public static final ModuleMethod string$Mnsuffix$Mnlength$Mnci;
    public static final ModuleMethod string$Mnsuffix$Qu;
    public static final ModuleMethod string$Mntabulate;
    public static final ModuleMethod string$Mntake;
    public static final ModuleMethod string$Mntake$Mnright;
    public static final ModuleMethod string$Mntitlecase;
    public static final ModuleMethod string$Mntitlecase$Ex;
    public static final ModuleMethod string$Mntokenize;
    public static final ModuleMethod string$Mntrim;
    public static final ModuleMethod string$Mntrim$Mnboth;
    public static final ModuleMethod string$Mntrim$Mnright;
    public static final ModuleMethod string$Mnunfold;
    public static final ModuleMethod string$Mnunfold$Mnright;
    public static final ModuleMethod string$Mnupcase;
    public static final ModuleMethod string$Mnupcase$Ex;
    public static final ModuleMethod string$Mnxcopy$Ex;
    public static final ModuleMethod substring$Mnspec$Mnok$Qu;
    public static final ModuleMethod substring$Slshared;
    public static final ModuleMethod xsubstring;

    /* compiled from: srfi13.scm */
    public class frame55 extends ModuleBody {
        Object char$Mn$Grint;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("receive").readResolve();
        Lit150 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("string-join").readResolve();
        Lit149 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("%multispan-repcopy!").readResolve();
        Lit148 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("string-xcopy!").readResolve();
        Lit147 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("xsubstring").readResolve();
        Lit146 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("string-tokenize").readResolve();
        Lit145 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("string-replace").readResolve();
        Lit144 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("%finish-string-concatenate-reverse").readResolve();
        Lit143 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("string-concatenate-reverse/shared").readResolve();
        Lit142 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("string-concatenate-reverse").readResolve();
        Lit141 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("string-concatenate").readResolve();
        Lit140 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("string-concatenate/shared").readResolve();
        Lit139 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("string-append/shared").readResolve();
        Lit138 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("string->list").readResolve();
        Lit137 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("reverse-list->string").readResolve();
        Lit136 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol2;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("string-reverse!").readResolve();
        Lit135 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol3;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("string-reverse").readResolve();
        Lit134 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol4;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("string-null?").readResolve();
        Lit133 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol5;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("string-kmp-partial-search").readResolve();
        Lit132 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol6;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("kmp-step").readResolve();
        Lit131 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol7;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("make-kmp-restart-vector").readResolve();
        Lit130 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol8;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("%kmp-search").readResolve();
        Lit129 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol9;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("string-contains-ci").readResolve();
        Lit128 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol10;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("string-contains").readResolve();
        Lit127 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol11;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("%string-copy!").readResolve();
        Lit126 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol12;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("string-copy!").readResolve();
        Lit125 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol13;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("string-fill!").readResolve();
        Lit124 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol14;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("string-count").readResolve();
        Lit123 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol15;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("string-skip-right").readResolve();
        Lit122 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol17;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("string-skip").readResolve();
        Lit121 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = simpleSymbol19;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("string-index-right").readResolve();
        Lit120 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = simpleSymbol21;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("string-index").readResolve();
        Lit119 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = simpleSymbol23;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("string-filter").readResolve();
        Lit118 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = simpleSymbol25;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("string-delete").readResolve();
        Lit117 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol27;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("string-pad").readResolve();
        Lit116 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = simpleSymbol29;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("string-pad-right").readResolve();
        Lit115 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = simpleSymbol31;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("string-trim-both").readResolve();
        Lit114 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = simpleSymbol33;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("string-trim-right").readResolve();
        Lit113 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = simpleSymbol35;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("string-trim").readResolve();
        Lit112 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = simpleSymbol37;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol("string-drop-right").readResolve();
        Lit111 = simpleSymbol65;
        SimpleSymbol simpleSymbol66 = simpleSymbol39;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("string-drop").readResolve();
        Lit110 = simpleSymbol67;
        SimpleSymbol simpleSymbol68 = simpleSymbol41;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("string-take-right").readResolve();
        Lit109 = simpleSymbol69;
        SimpleSymbol simpleSymbol70 = simpleSymbol43;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("string-take").readResolve();
        Lit108 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = simpleSymbol45;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("string-titlecase").readResolve();
        Lit107 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = simpleSymbol47;
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("string-titlecase!").readResolve();
        Lit106 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = simpleSymbol49;
        SimpleSymbol simpleSymbol77 = (SimpleSymbol) new SimpleSymbol("%string-titlecase!").readResolve();
        Lit105 = simpleSymbol77;
        SimpleSymbol simpleSymbol78 = simpleSymbol51;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("string-downcase!").readResolve();
        Lit104 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = simpleSymbol53;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("string-downcase").readResolve();
        Lit103 = simpleSymbol81;
        SimpleSymbol simpleSymbol82 = simpleSymbol55;
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) new SimpleSymbol("string-upcase!").readResolve();
        Lit102 = simpleSymbol83;
        SimpleSymbol simpleSymbol84 = simpleSymbol57;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("string-upcase").readResolve();
        Lit101 = simpleSymbol85;
        SimpleSymbol simpleSymbol86 = simpleSymbol59;
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) new SimpleSymbol("string-hash-ci").readResolve();
        Lit100 = simpleSymbol87;
        SimpleSymbol simpleSymbol88 = simpleSymbol61;
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) new SimpleSymbol("string-hash").readResolve();
        Lit99 = simpleSymbol89;
        SimpleSymbol simpleSymbol90 = simpleSymbol63;
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) new SimpleSymbol("%string-hash").readResolve();
        Lit98 = simpleSymbol91;
        SimpleSymbol simpleSymbol92 = simpleSymbol65;
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) new SimpleSymbol("string-ci>=").readResolve();
        Lit97 = simpleSymbol93;
        SimpleSymbol simpleSymbol94 = simpleSymbol67;
        SimpleSymbol simpleSymbol95 = (SimpleSymbol) new SimpleSymbol("string-ci<=").readResolve();
        Lit96 = simpleSymbol95;
        SimpleSymbol simpleSymbol96 = simpleSymbol69;
        SimpleSymbol simpleSymbol97 = (SimpleSymbol) new SimpleSymbol("string-ci>").readResolve();
        Lit95 = simpleSymbol97;
        SimpleSymbol simpleSymbol98 = simpleSymbol71;
        SimpleSymbol simpleSymbol99 = (SimpleSymbol) new SimpleSymbol("string-ci<").readResolve();
        Lit94 = simpleSymbol99;
        SimpleSymbol simpleSymbol100 = simpleSymbol73;
        SimpleSymbol simpleSymbol101 = (SimpleSymbol) new SimpleSymbol("string-ci<>").readResolve();
        Lit93 = simpleSymbol101;
        SimpleSymbol simpleSymbol102 = simpleSymbol75;
        SimpleSymbol simpleSymbol103 = (SimpleSymbol) new SimpleSymbol("string-ci=").readResolve();
        Lit92 = simpleSymbol103;
        SimpleSymbol simpleSymbol104 = simpleSymbol77;
        SimpleSymbol simpleSymbol105 = (SimpleSymbol) new SimpleSymbol("string>=").readResolve();
        Lit91 = simpleSymbol105;
        SimpleSymbol simpleSymbol106 = simpleSymbol79;
        SimpleSymbol simpleSymbol107 = (SimpleSymbol) new SimpleSymbol("string<=").readResolve();
        Lit90 = simpleSymbol107;
        SimpleSymbol simpleSymbol108 = simpleSymbol81;
        SimpleSymbol simpleSymbol109 = (SimpleSymbol) new SimpleSymbol("string>").readResolve();
        Lit89 = simpleSymbol109;
        SimpleSymbol simpleSymbol110 = simpleSymbol83;
        SimpleSymbol simpleSymbol111 = (SimpleSymbol) new SimpleSymbol("string<").readResolve();
        Lit88 = simpleSymbol111;
        SimpleSymbol simpleSymbol112 = simpleSymbol85;
        SimpleSymbol simpleSymbol113 = (SimpleSymbol) new SimpleSymbol("string<>").readResolve();
        Lit87 = simpleSymbol113;
        SimpleSymbol simpleSymbol114 = simpleSymbol87;
        SimpleSymbol simpleSymbol115 = (SimpleSymbol) new SimpleSymbol("string=").readResolve();
        Lit86 = simpleSymbol115;
        SimpleSymbol simpleSymbol116 = simpleSymbol89;
        SimpleSymbol simpleSymbol117 = (SimpleSymbol) new SimpleSymbol("string-compare-ci").readResolve();
        Lit85 = simpleSymbol117;
        SimpleSymbol simpleSymbol118 = simpleSymbol91;
        SimpleSymbol simpleSymbol119 = (SimpleSymbol) new SimpleSymbol("string-compare").readResolve();
        Lit84 = simpleSymbol119;
        SimpleSymbol simpleSymbol120 = simpleSymbol93;
        SimpleSymbol simpleSymbol121 = (SimpleSymbol) new SimpleSymbol("%string-compare-ci").readResolve();
        Lit83 = simpleSymbol121;
        SimpleSymbol simpleSymbol122 = simpleSymbol95;
        SimpleSymbol simpleSymbol123 = (SimpleSymbol) new SimpleSymbol("%string-compare").readResolve();
        Lit82 = simpleSymbol123;
        SimpleSymbol simpleSymbol124 = simpleSymbol97;
        SimpleSymbol simpleSymbol125 = (SimpleSymbol) new SimpleSymbol("%string-suffix-ci?").readResolve();
        Lit81 = simpleSymbol125;
        SimpleSymbol simpleSymbol126 = simpleSymbol99;
        SimpleSymbol simpleSymbol127 = (SimpleSymbol) new SimpleSymbol("%string-prefix-ci?").readResolve();
        Lit80 = simpleSymbol127;
        SimpleSymbol simpleSymbol128 = simpleSymbol101;
        SimpleSymbol simpleSymbol129 = (SimpleSymbol) new SimpleSymbol("%string-suffix?").readResolve();
        Lit79 = simpleSymbol129;
        SimpleSymbol simpleSymbol130 = simpleSymbol103;
        SimpleSymbol simpleSymbol131 = (SimpleSymbol) new SimpleSymbol("%string-prefix?").readResolve();
        Lit78 = simpleSymbol131;
        SimpleSymbol simpleSymbol132 = simpleSymbol105;
        SimpleSymbol simpleSymbol133 = (SimpleSymbol) new SimpleSymbol("string-suffix-ci?").readResolve();
        Lit77 = simpleSymbol133;
        SimpleSymbol simpleSymbol134 = simpleSymbol107;
        SimpleSymbol simpleSymbol135 = (SimpleSymbol) new SimpleSymbol("string-prefix-ci?").readResolve();
        Lit76 = simpleSymbol135;
        SimpleSymbol simpleSymbol136 = simpleSymbol109;
        SimpleSymbol simpleSymbol137 = (SimpleSymbol) new SimpleSymbol("string-suffix?").readResolve();
        Lit75 = simpleSymbol137;
        SimpleSymbol simpleSymbol138 = simpleSymbol111;
        SimpleSymbol simpleSymbol139 = (SimpleSymbol) new SimpleSymbol("string-prefix?").readResolve();
        Lit74 = simpleSymbol139;
        SimpleSymbol simpleSymbol140 = simpleSymbol113;
        SimpleSymbol simpleSymbol141 = (SimpleSymbol) new SimpleSymbol("string-suffix-length-ci").readResolve();
        Lit73 = simpleSymbol141;
        SimpleSymbol simpleSymbol142 = simpleSymbol115;
        SimpleSymbol simpleSymbol143 = (SimpleSymbol) new SimpleSymbol("string-prefix-length-ci").readResolve();
        Lit72 = simpleSymbol143;
        SimpleSymbol simpleSymbol144 = simpleSymbol117;
        SimpleSymbol simpleSymbol145 = (SimpleSymbol) new SimpleSymbol("string-suffix-length").readResolve();
        Lit71 = simpleSymbol145;
        SimpleSymbol simpleSymbol146 = simpleSymbol119;
        SimpleSymbol simpleSymbol147 = (SimpleSymbol) new SimpleSymbol("string-prefix-length").readResolve();
        Lit70 = simpleSymbol147;
        SimpleSymbol simpleSymbol148 = simpleSymbol121;
        SimpleSymbol simpleSymbol149 = (SimpleSymbol) new SimpleSymbol("%string-suffix-length-ci").readResolve();
        Lit69 = simpleSymbol149;
        SimpleSymbol simpleSymbol150 = simpleSymbol123;
        SimpleSymbol simpleSymbol151 = (SimpleSymbol) new SimpleSymbol("%string-prefix-length-ci").readResolve();
        Lit68 = simpleSymbol151;
        SimpleSymbol simpleSymbol152 = simpleSymbol125;
        SimpleSymbol simpleSymbol153 = (SimpleSymbol) new SimpleSymbol("%string-suffix-length").readResolve();
        Lit67 = simpleSymbol153;
        SimpleSymbol simpleSymbol154 = simpleSymbol127;
        SimpleSymbol simpleSymbol155 = (SimpleSymbol) new SimpleSymbol("%string-prefix-length").readResolve();
        Lit66 = simpleSymbol155;
        SimpleSymbol simpleSymbol156 = simpleSymbol129;
        SimpleSymbol simpleSymbol157 = (SimpleSymbol) new SimpleSymbol("string-tabulate").readResolve();
        Lit65 = simpleSymbol157;
        SimpleSymbol simpleSymbol158 = simpleSymbol131;
        SimpleSymbol simpleSymbol159 = (SimpleSymbol) new SimpleSymbol("string-any").readResolve();
        Lit64 = simpleSymbol159;
        SimpleSymbol simpleSymbol160 = simpleSymbol133;
        SimpleSymbol simpleSymbol161 = (SimpleSymbol) new SimpleSymbol("string-every").readResolve();
        Lit63 = simpleSymbol161;
        SimpleSymbol simpleSymbol162 = simpleSymbol135;
        SimpleSymbol simpleSymbol163 = (SimpleSymbol) new SimpleSymbol("string-for-each-index").readResolve();
        Lit62 = simpleSymbol163;
        SimpleSymbol simpleSymbol164 = simpleSymbol137;
        SimpleSymbol simpleSymbol165 = (SimpleSymbol) new SimpleSymbol("string-for-each").readResolve();
        Lit61 = simpleSymbol165;
        SimpleSymbol simpleSymbol166 = simpleSymbol139;
        SimpleSymbol simpleSymbol167 = (SimpleSymbol) new SimpleSymbol("string-unfold-right").readResolve();
        Lit60 = simpleSymbol167;
        SimpleSymbol simpleSymbol168 = simpleSymbol141;
        SimpleSymbol simpleSymbol169 = (SimpleSymbol) new SimpleSymbol("string-unfold").readResolve();
        Lit59 = simpleSymbol169;
        SimpleSymbol simpleSymbol170 = simpleSymbol143;
        SimpleSymbol simpleSymbol171 = (SimpleSymbol) new SimpleSymbol("string-fold-right").readResolve();
        Lit58 = simpleSymbol171;
        SimpleSymbol simpleSymbol172 = simpleSymbol145;
        SimpleSymbol simpleSymbol173 = (SimpleSymbol) new SimpleSymbol("string-fold").readResolve();
        Lit57 = simpleSymbol173;
        SimpleSymbol simpleSymbol174 = simpleSymbol147;
        SimpleSymbol simpleSymbol175 = (SimpleSymbol) new SimpleSymbol("%string-map!").readResolve();
        Lit56 = simpleSymbol175;
        SimpleSymbol simpleSymbol176 = simpleSymbol149;
        SimpleSymbol simpleSymbol177 = (SimpleSymbol) new SimpleSymbol("string-map!").readResolve();
        Lit55 = simpleSymbol177;
        SimpleSymbol simpleSymbol178 = simpleSymbol151;
        SimpleSymbol simpleSymbol179 = (SimpleSymbol) new SimpleSymbol("%string-map").readResolve();
        Lit54 = simpleSymbol179;
        SimpleSymbol simpleSymbol180 = simpleSymbol153;
        SimpleSymbol simpleSymbol181 = (SimpleSymbol) new SimpleSymbol("string-map").readResolve();
        Lit53 = simpleSymbol181;
        SimpleSymbol simpleSymbol182 = simpleSymbol155;
        SimpleSymbol simpleSymbol183 = (SimpleSymbol) new SimpleSymbol("string-copy").readResolve();
        Lit52 = simpleSymbol183;
        SimpleSymbol simpleSymbol184 = simpleSymbol157;
        SimpleSymbol simpleSymbol185 = (SimpleSymbol) new SimpleSymbol("%substring/shared").readResolve();
        Lit51 = simpleSymbol185;
        SimpleSymbol simpleSymbol186 = simpleSymbol159;
        SimpleSymbol simpleSymbol187 = (SimpleSymbol) new SimpleSymbol("substring/shared").readResolve();
        Lit50 = simpleSymbol187;
        SimpleSymbol simpleSymbol188 = simpleSymbol161;
        SimpleSymbol simpleSymbol189 = (SimpleSymbol) new SimpleSymbol("check-substring-spec").readResolve();
        Lit49 = simpleSymbol189;
        SimpleSymbol simpleSymbol190 = simpleSymbol163;
        SimpleSymbol simpleSymbol191 = (SimpleSymbol) new SimpleSymbol("substring-spec-ok?").readResolve();
        Lit48 = simpleSymbol191;
        SimpleSymbol simpleSymbol192 = simpleSymbol165;
        SimpleSymbol simpleSymbol193 = (SimpleSymbol) new SimpleSymbol("string-parse-final-start+end").readResolve();
        Lit47 = simpleSymbol193;
        SimpleSymbol simpleSymbol194 = simpleSymbol167;
        SimpleSymbol simpleSymbol195 = (SimpleSymbol) new SimpleSymbol("%check-bounds").readResolve();
        Lit46 = simpleSymbol195;
        SimpleSymbol simpleSymbol196 = simpleSymbol169;
        SimpleSymbol simpleSymbol197 = (SimpleSymbol) new SimpleSymbol("string-parse-start+end").readResolve();
        Lit45 = simpleSymbol197;
        SimpleSymbol simpleSymbol198 = simpleSymbol171;
        SimpleSymbol simpleSymbol199 = simpleSymbol173;
        SimpleSymbol simpleSymbol200 = simpleSymbol175;
        Object[] objArr = {(SimpleSymbol) new SimpleSymbol("l-s-s+e2").readResolve()};
        SimpleSymbol simpleSymbol201 = simpleSymbol177;
        SimpleSymbol simpleSymbol202 = simpleSymbol179;
        SimpleSymbol simpleSymbol203 = simpleSymbol181;
        SimpleSymbol simpleSymbol204 = simpleSymbol183;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018L\f\u0007\f\u000f\f\u0017\f\u001f\b\f'\f/\f7\f?\rG@\b\b", new Object[0], 9);
        SimpleSymbol simpleSymbol205 = (SimpleSymbol) new SimpleSymbol("let-string-start+end").readResolve();
        Lit41 = simpleSymbol205;
        SimpleSymbol simpleSymbol206 = simpleSymbol185;
        SimpleSymbol simpleSymbol207 = (SimpleSymbol) new SimpleSymbol("rest").readResolve();
        Lit27 = simpleSymbol207;
        SimpleSymbol simpleSymbol208 = simpleSymbol187;
        SimpleSymbol simpleSymbol209 = simpleSymbol189;
        SyntaxRules syntaxRules = new SyntaxRules(objArr, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\b\u0011\u0018\f\b#\b\u0011\u0018\u00141\t\u0003\t\u000b\u0018\u001c\u0011\u0018\f\t+\t;\b\u0011\u0018\u0014!\t\u0013\b\u001b\u0011\u0018\f\t3\u0011\u0018$\bEC", new Object[]{(SimpleSymbol) new SimpleSymbol("let").readResolve(), (SimpleSymbol) new SimpleSymbol("procv").readResolve(), simpleSymbol205, PairWithPosition.make(simpleSymbol207, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 553003), simpleSymbol207}, 1)}, 9);
        Lit44 = syntaxRules;
        SimpleSymbol simpleSymbol210 = (SimpleSymbol) new SimpleSymbol("let-string-start+end2").readResolve();
        Lit43 = simpleSymbol210;
        SimpleSymbol simpleSymbol211 = simpleSymbol191;
        SyntaxRules syntaxRules2 = new SyntaxRules(new Object[]{simpleSymbol205}, new SyntaxRule[]{new SyntaxRule(new SyntaxPattern("\f\u0018,\f\u0007\f\u000f\b\f\u0017\f\u001f\f'\r/(\b\b", new Object[0], 6), "\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u0004!\t\u0003\b\u000bI\u0011\u0018\f\t\u0013\t\u001b\b#\b-+", new Object[]{simpleSymbol, simpleSymbol193}, 1), new SyntaxRule(new SyntaxPattern("\f\u0018<\f\u0007\f\u000f\f\u0017\b\f\u001f\f'\f/\r70\b\b", new Object[0], 7), "\u0001\u0001\u0001\u0001\u0001\u0001\u0003", "\u0011\u0018\u00041\t\u0013\t\u0003\b\u000bI\u0011\u0018\f\t\u001b\t#\b+\b53", new Object[]{simpleSymbol, simpleSymbol197}, 1)}, 7);
        Lit42 = syntaxRules2;
        SimpleSymbol simpleSymbol212 = (SimpleSymbol) new SimpleSymbol("grammar").readResolve();
        Lit40 = simpleSymbol212;
        SimpleSymbol simpleSymbol213 = (SimpleSymbol) new SimpleSymbol("delim").readResolve();
        Lit39 = simpleSymbol213;
        SimpleSymbol simpleSymbol214 = (SimpleSymbol) new SimpleSymbol("token-chars").readResolve();
        Lit38 = simpleSymbol214;
        SimpleSymbol simpleSymbol215 = (SimpleSymbol) new SimpleSymbol("final").readResolve();
        Lit37 = simpleSymbol215;
        SimpleSymbol simpleSymbol216 = (SimpleSymbol) new SimpleSymbol("s-end").readResolve();
        Lit36 = simpleSymbol216;
        SimpleSymbol simpleSymbol217 = (SimpleSymbol) new SimpleSymbol("s-start").readResolve();
        Lit35 = simpleSymbol217;
        SimpleSymbol simpleSymbol218 = (SimpleSymbol) new SimpleSymbol("p-start").readResolve();
        Lit34 = simpleSymbol218;
        SimpleSymbol simpleSymbol219 = simpleSymbol193;
        SimpleSymbol simpleSymbol220 = (SimpleSymbol) new SimpleSymbol("end").readResolve();
        Lit33 = simpleSymbol220;
        SimpleSymbol simpleSymbol221 = simpleSymbol195;
        SimpleSymbol simpleSymbol222 = (SimpleSymbol) new SimpleSymbol("start").readResolve();
        Lit32 = simpleSymbol222;
        SimpleSymbol simpleSymbol223 = simpleSymbol197;
        SimpleSymbol simpleSymbol224 = (SimpleSymbol) new SimpleSymbol("c=").readResolve();
        Lit31 = simpleSymbol224;
        SimpleSymbol simpleSymbol225 = simpleSymbol210;
        SimpleSymbol simpleSymbol226 = (SimpleSymbol) new SimpleSymbol("char-set").readResolve();
        Lit30 = simpleSymbol226;
        SimpleSymbol simpleSymbol227 = (SimpleSymbol) new SimpleSymbol("criterion").readResolve();
        Lit29 = simpleSymbol227;
        SyntaxRules syntaxRules3 = syntaxRules2;
        SimpleSymbol simpleSymbol228 = (SimpleSymbol) new SimpleSymbol("char-cased?").readResolve();
        Lit28 = simpleSymbol228;
        SimpleSymbol simpleSymbol229 = simpleSymbol205;
        SimpleSymbol simpleSymbol230 = (SimpleSymbol) new SimpleSymbol("bound").readResolve();
        Lit26 = simpleSymbol230;
        SimpleSymbol simpleSymbol231 = simpleSymbol212;
        SimpleSymbol simpleSymbol232 = (SimpleSymbol) new SimpleSymbol("char-set-contains?").readResolve();
        Lit25 = simpleSymbol232;
        SimpleSymbol simpleSymbol233 = simpleSymbol213;
        SimpleSymbol simpleSymbol234 = (SimpleSymbol) new SimpleSymbol("char-set?").readResolve();
        Lit24 = simpleSymbol234;
        SimpleSymbol simpleSymbol235 = simpleSymbol214;
        SimpleSymbol simpleSymbol236 = (SimpleSymbol) new SimpleSymbol("make-final").readResolve();
        Lit23 = simpleSymbol236;
        SimpleSymbol simpleSymbol237 = simpleSymbol215;
        SimpleSymbol simpleSymbol238 = (SimpleSymbol) new SimpleSymbol("base").readResolve();
        Lit22 = simpleSymbol238;
        SimpleSymbol simpleSymbol239 = simpleSymbol216;
        SimpleSymbol simpleSymbol240 = (SimpleSymbol) new SimpleSymbol("let-optionals*").readResolve();
        Lit21 = simpleSymbol240;
        SimpleSymbol simpleSymbol241 = simpleSymbol217;
        SimpleSymbol simpleSymbol242 = (SimpleSymbol) new SimpleSymbol(":optional").readResolve();
        Lit20 = simpleSymbol242;
        SimpleSymbol simpleSymbol243 = simpleSymbol218;
        SimpleSymbol simpleSymbol244 = (SimpleSymbol) new SimpleSymbol("check-arg").readResolve();
        Lit19 = simpleSymbol244;
        SimpleSymbol simpleSymbol245 = simpleSymbol220;
        srfi13 srfi13 = new srfi13();
        $instance = srfi13;
        loc$check$Mnarg = ThreadLocation.getInstance(simpleSymbol244, (Object) null);
        loc$$Cloptional = ThreadLocation.getInstance(simpleSymbol242, (Object) null);
        loc$let$Mnoptionals$St = ThreadLocation.getInstance(simpleSymbol240, (Object) null);
        loc$base = ThreadLocation.getInstance(simpleSymbol238, (Object) null);
        loc$make$Mnfinal = ThreadLocation.getInstance(simpleSymbol236, (Object) null);
        loc$char$Mnset$Qu = ThreadLocation.getInstance(simpleSymbol234, (Object) null);
        loc$char$Mnset$Mncontains$Qu = ThreadLocation.getInstance(simpleSymbol232, (Object) null);
        loc$bound = ThreadLocation.getInstance(simpleSymbol230, (Object) null);
        loc$rest = ThreadLocation.getInstance(simpleSymbol207, (Object) null);
        loc$char$Mncased$Qu = ThreadLocation.getInstance(simpleSymbol228, (Object) null);
        loc$criterion = ThreadLocation.getInstance(simpleSymbol227, (Object) null);
        loc$char$Mnset = ThreadLocation.getInstance(simpleSymbol226, (Object) null);
        loc$c$Eq = ThreadLocation.getInstance(simpleSymbol224, (Object) null);
        loc$start = ThreadLocation.getInstance(simpleSymbol222, (Object) null);
        loc$end = ThreadLocation.getInstance(simpleSymbol245, (Object) null);
        loc$p$Mnstart = ThreadLocation.getInstance(simpleSymbol243, (Object) null);
        loc$s$Mnstart = ThreadLocation.getInstance(simpleSymbol241, (Object) null);
        loc$s$Mnend = ThreadLocation.getInstance(simpleSymbol239, (Object) null);
        loc$final = ThreadLocation.getInstance(simpleSymbol237, (Object) null);
        loc$token$Mnchars = ThreadLocation.getInstance(simpleSymbol235, (Object) null);
        loc$delim = ThreadLocation.getInstance(simpleSymbol233, (Object) null);
        loc$grammar = ThreadLocation.getInstance(simpleSymbol231, (Object) null);
        let$Mnstring$Mnstart$Plend = Macro.make(simpleSymbol229, syntaxRules3, srfi13);
        let$Mnstring$Mnstart$Plend2 = Macro.make(simpleSymbol225, syntaxRules, srfi13);
        string$Mnparse$Mnstart$Plend = new ModuleMethod(srfi13, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SOURCE, simpleSymbol223, 12291);
        $Pccheck$Mnbounds = new ModuleMethod(srfi13, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_FULLSCREEN, simpleSymbol221, 16388);
        string$Mnparse$Mnfinal$Mnstart$Plend = new ModuleMethod(srfi13, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_DURATION, simpleSymbol219, 12291);
        substring$Mnspec$Mnok$Qu = new ModuleMethod(srfi13, 197, simpleSymbol211, 12291);
        check$Mnsubstring$Mnspec = new ModuleMethod(srfi13, 198, simpleSymbol209, 16388);
        ModuleMethod moduleMethod = new ModuleMethod(srfi13, 199, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:223");
        lambda$Fn5 = moduleMethod;
        substring$Slshared = new ModuleMethod(srfi13, 200, simpleSymbol208, -4094);
        $Pcsubstring$Slshared = new ModuleMethod(srfi13, ErrorMessages.ERROR_CAMERA_NO_IMAGE_RETURNED, simpleSymbol206, 12291);
        string$Mncopy = new ModuleMethod(srfi13, ErrorMessages.ERROR_NO_CAMERA_PERMISSION, simpleSymbol204, -4095);
        string$Mnmap = new ModuleMethod(srfi13, 203, simpleSymbol203, -4094);
        $Pcstring$Mnmap = new ModuleMethod(srfi13, 204, simpleSymbol202, 16388);
        string$Mnmap$Ex = new ModuleMethod(srfi13, 205, simpleSymbol201, -4094);
        $Pcstring$Mnmap$Ex = new ModuleMethod(srfi13, 206, simpleSymbol200, 16388);
        string$Mnfold = new ModuleMethod(srfi13, 207, simpleSymbol199, -4093);
        string$Mnfold$Mnright = new ModuleMethod(srfi13, 208, simpleSymbol198, -4093);
        ModuleMethod moduleMethod2 = new ModuleMethod(srfi13, 209, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:377");
        lambda$Fn17 = moduleMethod2;
        string$Mnunfold = new ModuleMethod(srfi13, 210, simpleSymbol196, -4092);
        ModuleMethod moduleMethod3 = new ModuleMethod(srfi13, 211, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:422");
        lambda$Fn18 = moduleMethod3;
        string$Mnunfold$Mnright = new ModuleMethod(srfi13, 212, simpleSymbol194, -4092);
        string$Mnfor$Mneach = new ModuleMethod(srfi13, 213, simpleSymbol192, -4094);
        string$Mnfor$Mneach$Mnindex = new ModuleMethod(srfi13, 214, simpleSymbol190, -4094);
        string$Mnevery = new ModuleMethod(srfi13, 215, simpleSymbol188, -4094);
        string$Mnany = new ModuleMethod(srfi13, 216, simpleSymbol186, -4094);
        ModuleMethod moduleMethod4 = new ModuleMethod(srfi13, 217, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:535");
        lambda$Fn27 = moduleMethod4;
        string$Mntabulate = new ModuleMethod(srfi13, 218, simpleSymbol184, 8194);
        $Pcstring$Mnprefix$Mnlength = new ModuleMethod(srfi13, 219, simpleSymbol182, 24582);
        $Pcstring$Mnsuffix$Mnlength = new ModuleMethod(srfi13, 220, simpleSymbol180, 24582);
        $Pcstring$Mnprefix$Mnlength$Mnci = new ModuleMethod(srfi13, 221, simpleSymbol178, 24582);
        $Pcstring$Mnsuffix$Mnlength$Mnci = new ModuleMethod(srfi13, 222, simpleSymbol176, 24582);
        string$Mnprefix$Mnlength = new ModuleMethod(srfi13, 223, simpleSymbol174, -4094);
        string$Mnsuffix$Mnlength = new ModuleMethod(srfi13, 224, simpleSymbol172, -4094);
        string$Mnprefix$Mnlength$Mnci = new ModuleMethod(srfi13, 225, simpleSymbol170, -4094);
        string$Mnsuffix$Mnlength$Mnci = new ModuleMethod(srfi13, 226, simpleSymbol168, -4094);
        string$Mnprefix$Qu = new ModuleMethod(srfi13, 227, simpleSymbol166, -4094);
        string$Mnsuffix$Qu = new ModuleMethod(srfi13, 228, simpleSymbol164, -4094);
        string$Mnprefix$Mnci$Qu = new ModuleMethod(srfi13, 229, simpleSymbol162, -4094);
        string$Mnsuffix$Mnci$Qu = new ModuleMethod(srfi13, 230, simpleSymbol160, -4094);
        $Pcstring$Mnprefix$Qu = new ModuleMethod(srfi13, 231, simpleSymbol158, 24582);
        $Pcstring$Mnsuffix$Qu = new ModuleMethod(srfi13, 232, simpleSymbol156, 24582);
        $Pcstring$Mnprefix$Mnci$Qu = new ModuleMethod(srfi13, 233, simpleSymbol154, 24582);
        $Pcstring$Mnsuffix$Mnci$Qu = new ModuleMethod(srfi13, 234, simpleSymbol152, 24582);
        $Pcstring$Mncompare = new ModuleMethod(srfi13, 235, simpleSymbol150, 36873);
        $Pcstring$Mncompare$Mnci = new ModuleMethod(srfi13, 236, simpleSymbol148, 36873);
        string$Mncompare = new ModuleMethod(srfi13, 237, simpleSymbol146, -4091);
        string$Mncompare$Mnci = new ModuleMethod(srfi13, 238, simpleSymbol144, -4091);
        ModuleMethod moduleMethod5 = new ModuleMethod(srfi13, 239, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod5.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:756");
        lambda$Fn72 = moduleMethod5;
        ModuleMethod moduleMethod6 = new ModuleMethod(srfi13, 240, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod6.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:758");
        lambda$Fn73 = moduleMethod6;
        string$Eq = new ModuleMethod(srfi13, LispEscapeFormat.ESCAPE_NORMAL, simpleSymbol142, -4094);
        ModuleMethod moduleMethod7 = new ModuleMethod(srfi13, 242, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:767");
        lambda$Fn78 = moduleMethod7;
        string$Ls$Gr = new ModuleMethod(srfi13, 243, simpleSymbol140, -4094);
        ModuleMethod moduleMethod8 = new ModuleMethod(srfi13, 244, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:778");
        lambda$Fn83 = moduleMethod8;
        ModuleMethod moduleMethod9 = new ModuleMethod(srfi13, 245, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod9.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:779");
        lambda$Fn84 = moduleMethod9;
        string$Ls = new ModuleMethod(srfi13, 246, simpleSymbol138, -4094);
        ModuleMethod moduleMethod10 = new ModuleMethod(srfi13, 247, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod10.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:788");
        lambda$Fn89 = moduleMethod10;
        ModuleMethod moduleMethod11 = new ModuleMethod(srfi13, ComponentConstants.LISTVIEW_PREFERRED_WIDTH, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod11.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:789");
        lambda$Fn90 = moduleMethod11;
        string$Gr = new ModuleMethod(srfi13, 249, simpleSymbol136, -4094);
        ModuleMethod moduleMethod12 = new ModuleMethod(srfi13, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod12.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:801");
        lambda$Fn95 = moduleMethod12;
        string$Ls$Eq = new ModuleMethod(srfi13, Telnet.WILL, simpleSymbol134, -4094);
        ModuleMethod moduleMethod13 = new ModuleMethod(srfi13, Telnet.WONT, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod13.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:810");
        lambda$Fn100 = moduleMethod13;
        string$Gr$Eq = new ModuleMethod(srfi13, Telnet.DO, simpleSymbol132, -4094);
        ModuleMethod moduleMethod14 = new ModuleMethod(srfi13, Telnet.DONT, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod14.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:820");
        lambda$Fn105 = moduleMethod14;
        ModuleMethod moduleMethod15 = new ModuleMethod(srfi13, 255, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod15.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:822");
        lambda$Fn106 = moduleMethod15;
        string$Mnci$Eq = new ModuleMethod(srfi13, 256, simpleSymbol130, -4094);
        ModuleMethod moduleMethod16 = new ModuleMethod(srfi13, InputDeviceCompat.SOURCE_KEYBOARD, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod16.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:831");
        lambda$Fn111 = moduleMethod16;
        string$Mnci$Ls$Gr = new ModuleMethod(srfi13, 258, simpleSymbol128, -4094);
        ModuleMethod moduleMethod17 = new ModuleMethod(srfi13, 259, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod17.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:842");
        lambda$Fn116 = moduleMethod17;
        ModuleMethod moduleMethod18 = new ModuleMethod(srfi13, 260, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod18.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:843");
        lambda$Fn117 = moduleMethod18;
        string$Mnci$Ls = new ModuleMethod(srfi13, 261, simpleSymbol126, -4094);
        ModuleMethod moduleMethod19 = new ModuleMethod(srfi13, 262, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod19.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:852");
        lambda$Fn122 = moduleMethod19;
        ModuleMethod moduleMethod20 = new ModuleMethod(srfi13, 263, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod20.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:853");
        lambda$Fn123 = moduleMethod20;
        string$Mnci$Gr = new ModuleMethod(srfi13, 264, simpleSymbol124, -4094);
        ModuleMethod moduleMethod21 = new ModuleMethod(srfi13, 265, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod21.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:865");
        lambda$Fn128 = moduleMethod21;
        string$Mnci$Ls$Eq = new ModuleMethod(srfi13, 266, simpleSymbol122, -4094);
        ModuleMethod moduleMethod22 = new ModuleMethod(srfi13, 267, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod22.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:874");
        lambda$Fn133 = moduleMethod22;
        string$Mnci$Gr$Eq = new ModuleMethod(srfi13, 268, simpleSymbol120, -4094);
        $Pcstring$Mnhash = new ModuleMethod(srfi13, 269, simpleSymbol118, 20485);
        string$Mnhash = new ModuleMethod(srfi13, 270, simpleSymbol116, -4095);
        ModuleMethod moduleMethod23 = new ModuleMethod(srfi13, 271, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod23.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:922");
        lambda$Fn138 = moduleMethod23;
        string$Mnhash$Mnci = new ModuleMethod(srfi13, 272, simpleSymbol114, -4095);
        string$Mnupcase = new ModuleMethod(srfi13, 273, simpleSymbol112, -4095);
        string$Mnupcase$Ex = new ModuleMethod(srfi13, 274, simpleSymbol110, -4095);
        string$Mndowncase = new ModuleMethod(srfi13, 275, simpleSymbol108, -4095);
        string$Mndowncase$Ex = new ModuleMethod(srfi13, 276, simpleSymbol106, -4095);
        $Pcstring$Mntitlecase$Ex = new ModuleMethod(srfi13, 277, simpleSymbol104, 12291);
        string$Mntitlecase$Ex = new ModuleMethod(srfi13, 278, simpleSymbol102, -4095);
        string$Mntitlecase = new ModuleMethod(srfi13, 279, simpleSymbol100, -4095);
        string$Mntake = new ModuleMethod(srfi13, 280, simpleSymbol98, 8194);
        string$Mntake$Mnright = new ModuleMethod(srfi13, 281, simpleSymbol96, 8194);
        string$Mndrop = new ModuleMethod(srfi13, 282, simpleSymbol94, 8194);
        string$Mndrop$Mnright = new ModuleMethod(srfi13, 283, simpleSymbol92, 8194);
        string$Mntrim = new ModuleMethod(srfi13, 284, simpleSymbol90, -4095);
        string$Mntrim$Mnright = new ModuleMethod(srfi13, 285, simpleSymbol88, -4095);
        string$Mntrim$Mnboth = new ModuleMethod(srfi13, 286, simpleSymbol86, -4095);
        ModuleMethod moduleMethod24 = new ModuleMethod(srfi13, 287, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod24.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1047");
        lambda$Fn163 = moduleMethod24;
        string$Mnpad$Mnright = new ModuleMethod(srfi13, 288, simpleSymbol84, -4094);
        ModuleMethod moduleMethod25 = new ModuleMethod(srfi13, 289, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod25.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1059");
        lambda$Fn166 = moduleMethod25;
        string$Mnpad = new ModuleMethod(srfi13, 290, simpleSymbol82, -4094);
        string$Mndelete = new ModuleMethod(srfi13, 291, simpleSymbol80, -4094);
        string$Mnfilter = new ModuleMethod(srfi13, 292, simpleSymbol78, -4094);
        string$Mnindex = new ModuleMethod(srfi13, 293, simpleSymbol76, -4094);
        string$Mnindex$Mnright = new ModuleMethod(srfi13, 294, simpleSymbol74, -4094);
        string$Mnskip = new ModuleMethod(srfi13, 295, simpleSymbol72, -4094);
        string$Mnskip$Mnright = new ModuleMethod(srfi13, 296, simpleSymbol70, -4094);
        string$Mncount = new ModuleMethod(srfi13, 297, simpleSymbol68, -4094);
        string$Mnfill$Ex = new ModuleMethod(srfi13, 298, simpleSymbol66, -4094);
        string$Mncopy$Ex = new ModuleMethod(srfi13, 299, simpleSymbol64, 20483);
        $Pcstring$Mncopy$Ex = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_BLANK_CONSUMER_KEY_OR_SECRET, simpleSymbol62, 20485);
        string$Mncontains = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_EXCEPTION, simpleSymbol60, -4094);
        string$Mncontains$Mnci = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_UNABLE_TO_GET_ACCESS_TOKEN, simpleSymbol58, -4094);
        $Pckmp$Mnsearch = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_AUTHORIZATION_FAILED, simpleSymbol56, 28679);
        make$Mnkmp$Mnrestart$Mnvector = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED, simpleSymbol54, -4095);
        kmp$Mnstep = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_REQUEST_MENTIONS_FAILED, simpleSymbol52, 24582);
        string$Mnkmp$Mnpartial$Mnsearch = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED, simpleSymbol50, -4092);
        string$Mnnull$Qu = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED, simpleSymbol48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnreverse = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED, simpleSymbol46, -4095);
        string$Mnreverse$Ex = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED, simpleSymbol44, -4095);
        reverse$Mnlist$Mn$Grstring = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED, simpleSymbol42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mn$Grlist = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED, simpleSymbol40, -4095);
        string$Mnappend$Slshared = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_SEARCH_FAILED, simpleSymbol38, -4096);
        string$Mnconcatenate$Slshared = new ModuleMethod(srfi13, ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnconcatenate = new ModuleMethod(srfi13, 316, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnconcatenate$Mnreverse = new ModuleMethod(srfi13, 317, simpleSymbol32, -4095);
        string$Mnconcatenate$Mnreverse$Slshared = new ModuleMethod(srfi13, 318, simpleSymbol30, -4095);
        $Pcfinish$Mnstring$Mnconcatenate$Mnreverse = new ModuleMethod(srfi13, 319, simpleSymbol28, 16388);
        string$Mnreplace = new ModuleMethod(srfi13, ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION, simpleSymbol26, -4092);
        string$Mntokenize = new ModuleMethod(srfi13, 321, simpleSymbol24, -4095);
        ModuleMethod moduleMethod26 = new ModuleMethod(srfi13, 322, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod26.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1738");
        lambda$Fn210 = moduleMethod26;
        xsubstring = new ModuleMethod(srfi13, 323, simpleSymbol22, -4094);
        ModuleMethod moduleMethod27 = new ModuleMethod(srfi13, 324, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod27.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1779");
        lambda$Fn216 = moduleMethod27;
        ModuleMethod moduleMethod28 = new ModuleMethod(srfi13, 325, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        moduleMethod28.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1785");
        lambda$Fn220 = moduleMethod28;
        string$Mnxcopy$Ex = new ModuleMethod(srfi13, 326, simpleSymbol20, -4092);
        $Pcmultispan$Mnrepcopy$Ex = new ModuleMethod(srfi13, 327, simpleSymbol18, 28679);
        string$Mnjoin = new ModuleMethod(srfi13, 328, simpleSymbol16, -4095);
        srfi13.run();
    }

    public srfi13() {
        ModuleInfo.register(this);
    }

    static String lambda17(Object obj) {
        return "";
    }

    static String lambda18(Object obj) {
        return "";
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence) {
        return stringCopy$Ex(obj, i, charSequence, 0);
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence, int i2) {
        return stringCopy$Ex(obj, i, charSequence, i2, charSequence.length());
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public static Object stringParseStart$PlEnd(Object obj, Object obj2, Object obj3) {
        boolean isExact;
        frame frame97 = new frame();
        frame97.proc = obj;
        frame97.s = obj2;
        if (!strings.isString(frame97.s)) {
            misc.error$V("Non-string value", new Object[]{frame97.proc, frame97.s});
        }
        Object obj4 = frame97.s;
        try {
            frame97.slen = strings.stringLength((CharSequence) obj4);
            if (lists.isPair(obj3)) {
                Object apply1 = lists.car.apply1(obj3);
                frame97.args = lists.cdr.apply1(obj3);
                frame97.start = apply1;
                boolean isInteger = numbers.isInteger(frame97.start);
                if (!isInteger ? isInteger : !(!(isExact = numbers.isExact(frame97.start)) ? !isExact : Scheme.numGEq.apply2(frame97.start, Lit0) == Boolean.FALSE)) {
                    return call_with_values.callWithValues(frame97.lambda$Fn1, frame97.lambda$Fn2);
                }
                return misc.error$V("Illegal substring START spec", new Object[]{frame97.proc, frame97.start, frame97.s});
            }
            return misc.values(LList.Empty, Lit0, Integer.valueOf(frame97.slen));
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, obj4);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 194) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        } else if (i != 201) {
            if (i == 277) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 299) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                if (!(obj3 instanceof CharSequence)) {
                    return -786429;
                }
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 196) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i != 197) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (!(obj instanceof CharSequence)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame extends ModuleBody {
        Object args;
        final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, (Object) null, 0);
        final ModuleMethod lambda$Fn2;
        Object proc;
        Object s;
        int slen;
        Object start;

        public frame() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:150");
            this.lambda$Fn2 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda1() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 1) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 2 ? lambda2(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 2) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda1() {
            boolean isExact;
            if (lists.isPair(this.args)) {
                Object apply1 = lists.car.apply1(this.args);
                Object apply12 = lists.cdr.apply1(this.args);
                boolean isInteger = numbers.isInteger(apply1);
                if (!isInteger ? !isInteger : !(isExact = numbers.isExact(apply1)) ? !isExact : Scheme.numLEq.apply2(apply1, Integer.valueOf(this.slen)) == Boolean.FALSE) {
                    return misc.error$V("Illegal substring END spec", new Object[]{this.proc, apply1, this.s});
                }
                return misc.values(apply1, apply12);
            }
            return misc.values(Integer.valueOf(this.slen), this.args);
        }

        /* access modifiers changed from: package-private */
        public Object lambda2(Object obj, Object obj2) {
            if (Scheme.numLEq.apply2(this.start, obj) != Boolean.FALSE) {
                return misc.values(obj2, this.start, obj);
            }
            return misc.error$V("Illegal substring START/END spec", new Object[]{this.proc, this.start, obj, this.s});
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 195) {
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
        } else if (i == 198) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i == 204) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i == 206) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i == 299) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            if (!(obj3 instanceof CharSequence)) {
                return -786429;
            }
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        } else if (i != 319) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }
    }

    public static Object $PcCheckBounds(Object obj, CharSequence charSequence, int i, int i2) {
        if (i < 0) {
            return misc.error$V("Illegal substring START spec", new Object[]{obj, Integer.valueOf(i), charSequence});
        } else if (i > i2) {
            return misc.error$V("Illegal substring START/END spec", new Object[0]);
        } else {
            if (i2 <= strings.stringLength(charSequence)) {
                return Values.empty;
            }
            return misc.error$V("Illegal substring END spec", new Object[]{obj, Integer.valueOf(i2), charSequence});
        }
    }

    /* compiled from: srfi13.scm */
    public class frame0 extends ModuleBody {
        Object args;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, (Object) null, 0);
        final ModuleMethod lambda$Fn4;
        Object proc;
        Object s;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, (Object) null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:174");
            this.lambda$Fn4 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 3 ? lambda3() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 3) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 4 ? lambda4(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        /* access modifiers changed from: package-private */
        public Object lambda3() {
            return srfi13.stringParseStart$PlEnd(this.proc, this.s, this.args);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 4) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda4(Object obj, Object obj2, Object obj3) {
            if (lists.isPair(obj)) {
                return misc.error$V("Extra arguments to procedure", new Object[]{this.proc, obj});
            }
            return misc.values(obj2, obj3);
        }
    }

    public static Object stringParseFinalStart$PlEnd(Object obj, Object obj2, Object obj3) {
        frame0 frame02 = new frame0();
        frame02.proc = obj;
        frame02.s = obj2;
        frame02.args = obj3;
        return call_with_values.callWithValues(frame02.lambda$Fn3, frame02.lambda$Fn4);
    }

    public static boolean isSubstringSpecOk(Object obj, Object obj2, Object obj3) {
        boolean isString = strings.isString(obj);
        if (!isString) {
            return isString;
        }
        boolean isInteger = numbers.isInteger(obj2);
        if (!isInteger) {
            return isInteger;
        }
        boolean isExact = numbers.isExact(obj2);
        if (!isExact) {
            return isExact;
        }
        boolean isInteger2 = numbers.isInteger(obj3);
        if (!isInteger2) {
            return isInteger2;
        }
        boolean isExact2 = numbers.isExact(obj3);
        if (!isExact2) {
            return isExact2;
        }
        Object apply2 = Scheme.numLEq.apply2(Lit0, obj2);
        try {
            boolean booleanValue = ((Boolean) apply2).booleanValue();
            if (!booleanValue) {
                return booleanValue;
            }
            Object apply22 = Scheme.numLEq.apply2(obj2, obj3);
            try {
                boolean booleanValue2 = ((Boolean) apply22).booleanValue();
                if (booleanValue2) {
                    try {
                        booleanValue2 = ((Boolean) Scheme.numLEq.apply2(obj3, Integer.valueOf(strings.stringLength((CharSequence) obj)))).booleanValue();
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-length", 1, obj);
                    }
                }
                return booleanValue2;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
        }
    }

    public static Object checkSubstringSpec(Object obj, Object obj2, Object obj3, Object obj4) {
        if (isSubstringSpecOk(obj2, obj3, obj4)) {
            return Values.empty;
        }
        return misc.error$V("Illegal substring spec.", new Object[]{obj, obj2, obj3, obj4});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        if (r2 != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object $PcCheckSubstringSpec(java.lang.Object r3, java.lang.CharSequence r4, int r5, int r6) {
        /*
            r0 = 1
            r1 = 0
            if (r5 >= 0) goto L_0x0006
            r2 = 1
            goto L_0x0007
        L_0x0006:
            r2 = 0
        L_0x0007:
            if (r2 == 0) goto L_0x000c
            if (r2 == 0) goto L_0x0038
            goto L_0x001c
        L_0x000c:
            if (r5 <= r6) goto L_0x0010
            r2 = 1
            goto L_0x0011
        L_0x0010:
            r2 = 0
        L_0x0011:
            if (r2 == 0) goto L_0x0016
            if (r2 == 0) goto L_0x0038
            goto L_0x001c
        L_0x0016:
            int r2 = kawa.lib.strings.stringLength(r4)
            if (r6 <= r2) goto L_0x0038
        L_0x001c:
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r3
            r2[r0] = r4
            r3 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            r2[r3] = r4
            r3 = 3
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            r2[r3] = r4
            java.lang.String r3 = "Illegal substring spec."
            java.lang.Object r3 = kawa.lib.misc.error$V(r3, r2)
            goto L_0x003a
        L_0x0038:
            gnu.mapping.Values r3 = gnu.mapping.Values.empty
        L_0x003a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.$PcCheckSubstringSpec(java.lang.Object, java.lang.CharSequence, int, int):java.lang.Object");
    }

    public static Object substring$SlShared$V(Object obj, Object obj2, Object[] objArr) {
        frame1 frame110 = new frame1();
        frame110.start = obj2;
        LList makeList = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj3 = location.get();
            ModuleMethod moduleMethod = strings.string$Qu;
            ModuleMethod moduleMethod2 = substring$Slshared;
            applyToArgs.apply4(obj3, moduleMethod, obj, moduleMethod2);
            try {
                frame110.slen = strings.stringLength((CharSequence) obj);
                try {
                    Scheme.applyToArgs.apply4(location.get(), lambda$Fn5, frame110.start, moduleMethod2);
                    try {
                        CharSequence charSequence = (CharSequence) obj;
                        Object obj4 = frame110.start;
                        try {
                            int intValue = ((Number) obj4).intValue();
                            try {
                                Object apply4 = Scheme.applyToArgs.apply4(loc$$Cloptional.get(), makeList, Integer.valueOf(frame110.slen), frame110.lambda$Fn6);
                                try {
                                    return $PcSubstring$SlShared(charSequence, intValue, ((Number) apply4).intValue());
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "%substring/shared", 2, apply4);
                                }
                            } catch (UnboundLocationException e2) {
                                e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 226, 10);
                                throw e2;
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "%substring/shared", 1, obj4);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "%substring/shared", 0, obj);
                    }
                } catch (UnboundLocationException e5) {
                    e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 223, 5);
                    throw e5;
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-length", 1, obj);
            }
        } catch (UnboundLocationException e7) {
            e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 221, 3);
            throw e7;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 200:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_NO_CAMERA_PERMISSION:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 203:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 205:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 207:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 208:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 210:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 212:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 213:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 214:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 215:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 216:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 219:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 220:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 221:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 222:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 223:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 224:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 225:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 226:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 227:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 228:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 229:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 230:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 231:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 232:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 233:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 234:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 235:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 236:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 237:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 238:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case LispEscapeFormat.ESCAPE_NORMAL:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 243:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 246:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 249:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case Telnet.WILL /*251*/:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case Telnet.DO /*253*/:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 256:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 258:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 261:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 264:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 266:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 268:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 269:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 270:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 272:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 273:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 274:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 275:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 276:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 278:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 279:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 284:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 285:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 286:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 288:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 290:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 291:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 292:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 293:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 294:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 295:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 296:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 297:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 298:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 299:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_BLANK_CONSUMER_KEY_OR_SECRET:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_EXCEPTION:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_UNABLE_TO_GET_ACCESS_TOKEN:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_AUTHORIZATION_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_MENTIONS_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 317:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 318:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 321:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 323:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 326:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 327:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            case 328:
                callContext.values = objArr;
                callContext.proc = moduleMethod;
                callContext.pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod, objArr, callContext);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame1 extends ModuleBody {
        final ModuleMethod lambda$Fn6;
        int slen;
        Object start;

        public frame1() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 5, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:227");
            this.lambda$Fn6 = moduleMethod;
        }

        static boolean lambda5(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            return isExact ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue() : isExact;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 5) {
                return lambda6(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda6(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            if (!isExact) {
                return isExact;
            }
            Object apply2 = Scheme.numLEq.apply2(this.start, obj);
            try {
                boolean booleanValue = ((Boolean) apply2).booleanValue();
                if (booleanValue) {
                    return ((Boolean) Scheme.numLEq.apply2(obj, Integer.valueOf(this.slen))).booleanValue();
                }
                return booleanValue;
            } catch (ClassCastException e) {
                throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 199:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 209:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 211:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 217:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 239:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 240:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 242:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 244:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 245:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 247:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case Telnet.WONT /*252*/:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case Telnet.DONT /*254*/:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 255:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case InputDeviceCompat.SOURCE_KEYBOARD:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 259:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 260:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 262:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 263:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 265:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 267:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 271:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 287:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 289:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 316:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 322:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 324:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 325:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static Object $PcSubstring$SlShared(CharSequence charSequence, int i, int i2) {
        boolean isZero = numbers.isZero(Integer.valueOf(i));
        if (isZero) {
            if (i2 == strings.stringLength(charSequence)) {
                return charSequence;
            }
        } else if (isZero) {
            return charSequence;
        }
        return strings.substring(charSequence, i, i2);
    }

    /* compiled from: srfi13.scm */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 6, (Object) null, 0);
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 7, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 6 ? lambda7() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 7 ? lambda8(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 6) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 7) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda7() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncopy, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda8(Object obj, Object obj2) {
            Object obj3 = this.s;
            try {
                try {
                    try {
                        return strings.substring((CharSequence) obj3, ((Number) obj).intValue(), ((Number) obj2).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, obj);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj3);
            }
        }
    }

    public static Object stringCopy$V(Object obj, Object[] objArr) {
        frame2 frame210 = new frame2();
        frame210.s = obj;
        frame210.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame210.lambda$Fn7, frame210.lambda$Fn8);
    }

    public static Object stringMap$V(Object obj, Object obj2, Object[] objArr) {
        frame3 frame310 = new frame3();
        frame310.proc = obj;
        frame310.s = obj2;
        frame310.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame310.proc, string$Mnmap);
            return call_with_values.callWithValues(frame310.lambda$Fn9, frame310.lambda$Fn10);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 271, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 9, (Object) null, 8194);
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 8, (Object) null, 0);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 8 ? lambda9() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 9 ? lambda10(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda9() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object obj, Object obj2) {
            return srfi13.$PcStringMap(this.proc, this.s, obj, obj2);
        }
    }

    public static Object $PcStringMap(Object obj, Object obj2, Object obj3, Object obj4) {
        Object apply2 = AddOp.$Mn.apply2(obj4, obj3);
        try {
            CharSequence makeString = strings.makeString(((Number) apply2).intValue());
            while (true) {
                AddOp addOp = AddOp.$Mn;
                IntNum intNum = Lit1;
                obj4 = addOp.apply2(obj4, intNum);
                apply2 = AddOp.$Mn.apply2(apply2, intNum);
                if (Scheme.numLss.apply2(apply2, Lit0) != Boolean.FALSE) {
                    return makeString;
                }
                try {
                    CharSeq charSeq = (CharSeq) makeString;
                    try {
                        int intValue = ((Number) apply2).intValue();
                        try {
                            try {
                                Object apply22 = Scheme.applyToArgs.apply2(obj, Char.make(strings.stringRef((CharSequence) obj2, ((Number) obj4).intValue())));
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, ((Char) apply22).charValue());
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-set!", 3, apply22);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 2, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 1, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-set!", 2, apply2);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-set!", 1, (Object) makeString);
                }
            }
        } catch (ClassCastException e6) {
            throw new WrongType(e6, "make-string", 1, apply2);
        }
    }

    public static Object stringMap$Ex$V(Object obj, Object obj2, Object[] objArr) {
        frame4 frame410 = new frame4();
        frame410.proc = obj;
        frame410.s = obj2;
        frame410.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame410.proc, string$Mnmap$Ex);
            return call_with_values.callWithValues(frame410.lambda$Fn11, frame410.lambda$Fn12);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 285, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 10, (Object) null, 0);
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 11, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 10 ? lambda11() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 11 ? lambda12(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda11() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12(Object obj, Object obj2) {
            return srfi13.$PcStringMap$Ex(this.proc, this.s, obj, obj2);
        }
    }

    public static Object $PcStringMap$Ex(Object obj, Object obj2, Object obj3, Object obj4) {
        while (true) {
            obj4 = AddOp.$Mn.apply2(obj4, Lit1);
            if (Scheme.numLss.apply2(obj4, obj3) != Boolean.FALSE) {
                return Values.empty;
            }
            try {
                CharSeq charSeq = (CharSeq) obj2;
                try {
                    int intValue = ((Number) obj4).intValue();
                    try {
                        try {
                            Object apply2 = Scheme.applyToArgs.apply2(obj, Char.make(strings.stringRef((CharSequence) obj2, ((Number) obj4).intValue())));
                            try {
                                strings.stringSet$Ex(charSeq, intValue, ((Char) apply2).charValue());
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-set!", 3, apply2);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "string-ref", 2, obj4);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "string-ref", 1, obj2);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "string-set!", 2, obj4);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "string-set!", 1, obj2);
            }
        }
    }

    public static Object stringFold$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        frame5 frame510 = new frame5();
        frame510.kons = obj;
        frame510.knil = obj2;
        frame510.s = obj3;
        frame510.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame510.kons, string$Mnfold);
            return call_with_values.callWithValues(frame510.lambda$Fn13, frame510.lambda$Fn14);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 295, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame5 extends ModuleBody {
        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 12, (Object) null, 0);
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 13, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 12 ? lambda13() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 13 ? lambda14(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 13) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda13() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda14(Object obj, Object obj2) {
            Object obj3 = this.knil;
            while (Scheme.numLss.apply2(obj, obj2) != Boolean.FALSE) {
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj4 = this.kons;
                Object obj5 = this.s;
                try {
                    try {
                        obj3 = applyToArgs.apply3(obj4, Char.make(strings.stringRef((CharSequence) obj5, ((Number) obj).intValue())), obj3);
                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, obj);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, obj5);
                }
            }
            return obj3;
        }
    }

    public static Object stringFoldRight$V(Object obj, Object obj2, Object obj3, Object[] objArr) {
        frame6 frame610 = new frame6();
        frame610.kons = obj;
        frame610.knil = obj2;
        frame610.s = obj3;
        frame610.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame610.kons, string$Mnfold$Mnright);
            return call_with_values.callWithValues(frame610.lambda$Fn15, frame610.lambda$Fn16);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_TWITTER_BLANK_CONSUMER_KEY_OR_SECRET, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame6 extends ModuleBody {
        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 14, (Object) null, 0);
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 15, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 14 ? lambda15() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 15 ? lambda16(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda15() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold$Mnright, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object obj, Object obj2) {
            Object obj3 = this.knil;
            Object apply2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            while (Scheme.numGEq.apply2(apply2, obj) != Boolean.FALSE) {
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj4 = this.kons;
                Object obj5 = this.s;
                try {
                    try {
                        obj3 = applyToArgs.apply3(obj4, Char.make(strings.stringRef((CharSequence) obj5, ((Number) apply2).intValue())), obj3);
                        apply2 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, apply2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, obj5);
                }
            }
            return obj3;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: java.lang.Integer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v53, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v59, resolved type: java.lang.Integer} */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0101, code lost:
        r17 = r13 + r17;
        r6 = kawa.lib.numbers.min(Lit2, java.lang.Integer.valueOf(r17));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r13 = ((java.lang.Number) r6).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x011d, code lost:
        r6 = kawa.lib.strings.makeString(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0140, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0147, code lost:
        throw new gnu.mapping.WrongType(r0, "string-set!", 3, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0148, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0150, code lost:
        throw new gnu.mapping.WrongType(r0, "string-set!", 1, (java.lang.Object) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0151, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015a, code lost:
        throw new gnu.mapping.WrongType(r0, "chunk-len2", -2, r6);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringUnfold$V(java.lang.Object r21, java.lang.Object r22, java.lang.Object r23, java.lang.Object r24, java.lang.Object[] r25) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            java.lang.String r3 = "j"
            java.lang.String r4 = "string-length"
            java.lang.String r5 = "%string-copy!"
            java.lang.String r6 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r7 = 0
            r8 = r25
            gnu.lists.LList r8 = gnu.lists.LList.makeList(r8, r7)
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$check$Mnarg
            java.lang.Object r12 = r10.get()     // Catch:{ UnboundLocationException -> 0x02cf }
            gnu.expr.ModuleMethod r13 = kawa.lib.misc.procedure$Qu
            gnu.expr.ModuleMethod r14 = string$Mnunfold
            r9.apply4(r12, r13, r0, r14)
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r12 = r10.get()     // Catch:{ UnboundLocationException -> 0x02c5 }
            gnu.expr.ModuleMethod r13 = kawa.lib.misc.procedure$Qu
            r9.apply4(r12, r13, r1, r14)
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x02bb }
            gnu.expr.ModuleMethod r12 = kawa.lib.misc.procedure$Qu
            r9.apply4(r10, r12, r2, r14)
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$let$Mnoptionals$St
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x02b1 }
            gnu.kawa.functions.ApplyToArgs r12 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r13 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r14 = loc$base
            java.lang.Object r15 = r14.get()     // Catch:{ UnboundLocationException -> 0x02a6 }
            java.lang.Object r14 = r14.get()     // Catch:{ UnboundLocationException -> 0x029b }
            boolean r14 = kawa.lib.strings.isString(r14)
            if (r14 == 0) goto L_0x0059
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            goto L_0x005b
        L_0x0059:
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
        L_0x005b:
            java.lang.String r7 = ""
            java.lang.Object r7 = r13.apply3(r15, r7, r14)
            gnu.kawa.functions.ApplyToArgs r13 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r14 = loc$make$Mnfinal
            java.lang.Object r15 = r14.get()     // Catch:{ UnboundLocationException -> 0x0291 }
            gnu.expr.ModuleMethod r11 = lambda$Fn17
            java.lang.Object r14 = r14.get()     // Catch:{ UnboundLocationException -> 0x0286 }
            boolean r14 = kawa.lib.misc.isProcedure(r14)
            if (r14 == 0) goto L_0x0078
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            goto L_0x007a
        L_0x0078:
            java.lang.Boolean r14 = java.lang.Boolean.FALSE
        L_0x007a:
            java.lang.Object r11 = r13.apply3(r15, r11, r14)
            java.lang.Object r7 = r12.apply2(r7, r11)
            gnu.lists.LList r11 = gnu.lists.LList.Empty
            r12 = 40
            java.lang.CharSequence r13 = kawa.lib.strings.makeString(r12)
            r12 = r11
            r15 = r13
            r13 = 40
            r16 = 0
            r17 = 0
            r11 = r24
        L_0x0094:
            java.lang.Integer r16 = java.lang.Integer.valueOf(r16)
            r18 = r3
            r14 = r16
        L_0x009c:
            gnu.kawa.functions.ApplyToArgs r3 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r3 = r3.apply2(r0, r11)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r19 = r6
            if (r3 != r0) goto L_0x015b
            gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r3 = r0.apply2(r1, r11)
            gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r11 = r0.apply2(r2, r11)
            gnu.kawa.functions.NumberCompare r0 = kawa.standard.Scheme.numLss
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            java.lang.Object r0 = r0.apply2(r14, r6)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            java.lang.String r1 = "string-set!"
            if (r0 == r6) goto L_0x0101
            r0 = r15
            gnu.lists.CharSeq r0 = (gnu.lists.CharSeq) r0     // Catch:{ ClassCastException -> 0x00f8 }
            r6 = r14
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ ClassCastException -> 0x00f0 }
            int r6 = r6.intValue()     // Catch:{ ClassCastException -> 0x00f0 }
            r20 = r3
            gnu.text.Char r20 = (gnu.text.Char) r20     // Catch:{ ClassCastException -> 0x00e8 }
            char r1 = r20.charValue()     // Catch:{ ClassCastException -> 0x00e8 }
            kawa.lib.strings.stringSet$Ex(r0, r6, r1)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r1 = Lit1
            java.lang.Object r14 = r0.apply2(r14, r1)
            r0 = r21
            r1 = r22
            r6 = r19
            goto L_0x009c
        L_0x00e8:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r4 = 3
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r1, (int) r4, (java.lang.Object) r3)
            throw r2
        L_0x00f0:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r6 = 2
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r1, (int) r6, (java.lang.Object) r14)
            throw r2
        L_0x00f8:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r14 = 1
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r14, (java.lang.Object) r15)
            throw r0
        L_0x0101:
            r6 = 2
            r14 = 1
            int r17 = r13 + r17
            java.lang.Object[] r0 = new java.lang.Object[r6]
            gnu.math.IntNum r6 = Lit2
            r13 = 0
            r0[r13] = r6
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)
            r0[r14] = r6
            java.lang.Object r6 = kawa.lib.numbers.min(r0)
            r0 = r6
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x0151 }
            int r13 = r0.intValue()     // Catch:{ ClassCastException -> 0x0151 }
            java.lang.CharSequence r6 = kawa.lib.strings.makeString(r13)
            r0 = r6
            gnu.lists.CharSeq r0 = (gnu.lists.CharSeq) r0     // Catch:{ ClassCastException -> 0x0148 }
            r14 = r3
            gnu.text.Char r14 = (gnu.text.Char) r14     // Catch:{ ClassCastException -> 0x0140 }
            char r1 = r14.charValue()     // Catch:{ ClassCastException -> 0x0140 }
            r3 = 0
            kawa.lib.strings.stringSet$Ex(r0, r3, r1)
            gnu.lists.Pair r12 = kawa.lib.lists.cons(r15, r12)
            r0 = r21
            r1 = r22
            r15 = r6
            r3 = r18
            r6 = r19
            r16 = 1
            goto L_0x0094
        L_0x0140:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r4 = 3
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r1, (int) r4, (java.lang.Object) r3)
            throw r2
        L_0x0148:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r3, (java.lang.Object) r6)
            throw r0
        L_0x0151:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            java.lang.String r2 = "chunk-len2"
            r3 = -2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r6)
            throw r1
        L_0x015b:
            gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r1 = loc$make$Mnfinal
            java.lang.Object r1 = r1.get()     // Catch:{ UnboundLocationException -> 0x027a }
            java.lang.Object r1 = r0.apply2(r1, r11)
            r0 = r1
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ ClassCastException -> 0x0271 }
            int r0 = kawa.lib.strings.stringLength(r0)
            gnu.mapping.Location r2 = loc$base
            java.lang.Object r2 = r2.get()     // Catch:{ UnboundLocationException -> 0x0265 }
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ ClassCastException -> 0x025c }
            int r2 = kawa.lib.strings.stringLength(r2)
            gnu.kawa.functions.AddOp r3 = gnu.kawa.functions.AddOp.$Pl
            int r17 = r2 + r17
            java.lang.Integer r6 = java.lang.Integer.valueOf(r17)
            java.lang.Object r3 = r3.apply2(r6, r14)
            r6 = r3
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ ClassCastException -> 0x0252 }
            int r3 = r6.intValue()     // Catch:{ ClassCastException -> 0x0252 }
            int r6 = r3 + r0
            java.lang.CharSequence r6 = kawa.lib.strings.makeString(r6)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ ClassCastException -> 0x0249 }
            r11 = 0
            $PcStringCopy$Ex(r6, r3, r1, r11, r0)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.Object r1 = r0.apply2(r1, r14)
            r0 = r1
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x023f }
            int r0 = r0.intValue()     // Catch:{ ClassCastException -> 0x023f }
            r1 = r15
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ ClassCastException -> 0x0236 }
            r1 = r14
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ ClassCastException -> 0x022e }
            int r1 = r1.intValue()     // Catch:{ ClassCastException -> 0x022e }
            r3 = 0
            $PcStringCopy$Ex(r6, r0, r15, r3, r1)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L_0x01bc:
            boolean r1 = kawa.lib.lists.isPair(r12)
            if (r1 == 0) goto L_0x0208
            gnu.expr.GenericProc r1 = kawa.lib.lists.car
            java.lang.Object r1 = r1.apply1(r12)
            gnu.expr.GenericProc r3 = kawa.lib.lists.cdr
            java.lang.Object r12 = r3.apply1(r12)
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ ClassCastException -> 0x01ff }
            int r3 = kawa.lib.strings.stringLength(r3)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            java.lang.Object r11 = r11.apply2(r0, r13)
            r0 = r11
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x01f7 }
            int r0 = r0.intValue()     // Catch:{ ClassCastException -> 0x01f7 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ ClassCastException -> 0x01ee }
            r13 = 0
            $PcStringCopy$Ex(r6, r0, r1, r13, r3)
            r0 = r11
            goto L_0x01bc
        L_0x01ee:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 2
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r5, (int) r3, (java.lang.Object) r1)
            throw r0
        L_0x01f7:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r5, (int) r2, (java.lang.Object) r11)
            throw r1
        L_0x01ff:
            r0 = move-exception
            r2 = 1
            r3 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r3, (java.lang.String) r4, (int) r2, (java.lang.Object) r1)
            throw r0
        L_0x0208:
            gnu.mapping.Location r0 = loc$base
            java.lang.Object r1 = r0.get()     // Catch:{ UnboundLocationException -> 0x0222 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ ClassCastException -> 0x0219 }
            r0 = 0
            $PcStringCopy$Ex(r6, r0, r1, r0, r2)
            java.lang.Object r0 = r9.apply4(r10, r8, r7, r6)
            return r0
        L_0x0219:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 2
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r5, (int) r3, (java.lang.Object) r1)
            throw r0
        L_0x0222:
            r0 = move-exception
            r1 = r0
            r0 = 416(0x1a0, float:5.83E-43)
            r2 = 29
            r3 = r19
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x022e:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 4
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r5, (int) r2, (java.lang.Object) r14)
            throw r1
        L_0x0236:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = 2
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r5, (int) r2, (java.lang.Object) r15)
            throw r0
        L_0x023f:
            r0 = move-exception
            gnu.mapping.WrongType r2 = new gnu.mapping.WrongType
            r4 = r18
            r3 = -2
            r2.<init>((java.lang.ClassCastException) r0, (java.lang.String) r4, (int) r3, (java.lang.Object) r1)
            throw r2
        L_0x0249:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 2
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r5, (int) r3, (java.lang.Object) r1)
            throw r0
        L_0x0252:
            r0 = move-exception
            r4 = r18
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = -2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r4, (int) r2, (java.lang.Object) r3)
            throw r1
        L_0x025c:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r4, (int) r3, (java.lang.Object) r2)
            throw r0
        L_0x0265:
            r0 = move-exception
            r3 = r19
            r1 = r0
            r0 = 402(0x192, float:5.63E-43)
            r2 = 38
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x0271:
            r0 = move-exception
            r3 = 1
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r4, (int) r3, (java.lang.Object) r1)
            throw r0
        L_0x027a:
            r0 = move-exception
            r3 = r19
            r1 = r0
            r0 = 400(0x190, float:5.6E-43)
            r2 = 20
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x0286:
            r0 = move-exception
            r3 = r6
            r1 = r0
            r0 = 46
            r2 = 377(0x179, float:5.28E-43)
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x0291:
            r0 = move-exception
            r3 = r6
            r2 = 377(0x179, float:5.28E-43)
            r1 = r0
            r0 = 6
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x029b:
            r0 = move-exception
            r3 = r6
            r1 = r0
            r0 = 57
            r2 = 376(0x178, float:5.27E-43)
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x02a6:
            r0 = move-exception
            r3 = r6
            r2 = 376(0x178, float:5.27E-43)
            r1 = r0
            r4 = 20
            r1.setLine(r3, r2, r4)
            throw r1
        L_0x02b1:
            r0 = move-exception
            r3 = r6
            r1 = r0
            r0 = 375(0x177, float:5.25E-43)
            r2 = 3
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x02bb:
            r0 = move-exception
            r3 = r6
            r2 = 3
            r1 = r0
            r0 = 374(0x176, float:5.24E-43)
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x02c5:
            r0 = move-exception
            r3 = r6
            r2 = 3
            r1 = r0
            r0 = 373(0x175, float:5.23E-43)
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x02cf:
            r0 = move-exception
            r3 = r6
            r2 = 3
            r1 = r0
            r0 = 372(0x174, float:5.21E-43)
            r1.setLine(r3, r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringUnfold$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v2, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v59, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v3, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v9, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringUnfoldRight$V(java.lang.Object r22, java.lang.Object r23, java.lang.Object r24, java.lang.Object r25, java.lang.Object[] r26) {
        /*
            java.lang.String r1 = "string-length"
            java.lang.String r2 = "%string-copy!"
            java.lang.String r3 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r0 = 0
            r4 = r26
            gnu.lists.LList r4 = gnu.lists.LList.makeList(r4, r0)
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r6 = loc$let$Mnoptionals$St
            java.lang.Object r6 = r6.get()     // Catch:{ UnboundLocationException -> 0x02b5 }
            gnu.kawa.functions.ApplyToArgs r8 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$base
            r12 = 421(0x1a5, float:5.9E-43)
            java.lang.Object r13 = r10.get()     // Catch:{ UnboundLocationException -> 0x02ad }
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x02a5 }
            boolean r10 = kawa.lib.strings.isString(r10)
            if (r10 == 0) goto L_0x002e
            java.lang.Boolean r10 = java.lang.Boolean.TRUE
            goto L_0x0030
        L_0x002e:
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
        L_0x0030:
            java.lang.String r12 = ""
            java.lang.Object r9 = r9.apply3(r13, r12, r10)
            gnu.kawa.functions.ApplyToArgs r10 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r12 = loc$make$Mnfinal
            r13 = 422(0x1a6, float:5.91E-43)
            java.lang.Object r14 = r12.get()     // Catch:{ UnboundLocationException -> 0x029e }
            gnu.expr.ModuleMethod r15 = lambda$Fn18
            java.lang.Object r12 = r12.get()     // Catch:{ UnboundLocationException -> 0x0296 }
            boolean r12 = kawa.lib.misc.isProcedure(r12)
            if (r12 == 0) goto L_0x004f
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
            goto L_0x0051
        L_0x004f:
            java.lang.Boolean r12 = java.lang.Boolean.FALSE
        L_0x0051:
            java.lang.Object r10 = r10.apply3(r14, r15, r12)
            java.lang.Object r8 = r8.apply2(r9, r10)
            gnu.lists.LList r9 = gnu.lists.LList.Empty
            gnu.math.IntNum r10 = Lit0
            r12 = 40
            java.lang.CharSequence r12 = kawa.lib.strings.makeString(r12)
            gnu.math.IntNum r13 = Lit3
            r14 = r13
            r15 = r14
            r13 = r12
            r12 = r10
            r10 = r9
            r9 = r25
        L_0x006c:
            gnu.kawa.functions.ApplyToArgs r11 = kawa.standard.Scheme.applyToArgs
            r0 = r22
            java.lang.Object r11 = r11.apply2(r0, r9)
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r17 = r3
            java.lang.String r3 = "make-string"
            r18 = r4
            if (r11 != r7) goto L_0x0149
            gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
            r11 = r23
            java.lang.Object r7 = r7.apply2(r11, r9)
            gnu.kawa.functions.ApplyToArgs r4 = kawa.standard.Scheme.applyToArgs
            r0 = r24
            java.lang.Object r9 = r4.apply2(r0, r9)
            gnu.kawa.functions.NumberCompare r4 = kawa.standard.Scheme.numGrt
            gnu.math.IntNum r0 = Lit0
            java.lang.Object r0 = r4.apply2(r14, r0)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r20 = r9
            java.lang.String r9 = "string-set!"
            if (r0 == r4) goto L_0x00db
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r3 = Lit1
            java.lang.Object r14 = r0.apply2(r14, r3)
            r0 = r13
            gnu.lists.CharSeq r0 = (gnu.lists.CharSeq) r0     // Catch:{ ClassCastException -> 0x00d2 }
            r3 = r14
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ ClassCastException -> 0x00ca }
            int r3 = r3.intValue()     // Catch:{ ClassCastException -> 0x00ca }
            r4 = r7
            gnu.text.Char r4 = (gnu.text.Char) r4     // Catch:{ ClassCastException -> 0x00c2 }
            char r4 = r4.charValue()     // Catch:{ ClassCastException -> 0x00c2 }
            kawa.lib.strings.stringSet$Ex(r0, r3, r4)
        L_0x00ba:
            r3 = r17
            r4 = r18
            r9 = r20
            r0 = 0
            goto L_0x006c
        L_0x00c2:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 3
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r9, (int) r2, (java.lang.Object) r7)
            throw r1
        L_0x00ca:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r4 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r9, (int) r4, (java.lang.Object) r14)
            throw r1
        L_0x00d2:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r14 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r9, (int) r14, (java.lang.Object) r13)
            throw r0
        L_0x00db:
            r4 = 2
            r14 = 1
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r0 = r0.apply2(r15, r12)
            java.lang.Object[] r14 = new java.lang.Object[r4]
            gnu.math.IntNum r4 = Lit4
            r16 = 0
            r14[r16] = r4
            r4 = 1
            r14[r4] = r0
            java.lang.Object r4 = kawa.lib.numbers.min(r14)
            r0 = r4
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x0141 }
            int r0 = r0.intValue()     // Catch:{ ClassCastException -> 0x0141 }
            java.lang.CharSequence r3 = kawa.lib.strings.makeString(r0)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r14 = Lit1
            java.lang.Object r14 = r0.apply2(r4, r14)
            r0 = r3
            gnu.lists.CharSeq r0 = (gnu.lists.CharSeq) r0     // Catch:{ ClassCastException -> 0x0138 }
            r19 = r14
            java.lang.Number r19 = (java.lang.Number) r19     // Catch:{ ClassCastException -> 0x0130 }
            int r11 = r19.intValue()     // Catch:{ ClassCastException -> 0x0130 }
            r19 = r7
            gnu.text.Char r19 = (gnu.text.Char) r19     // Catch:{ ClassCastException -> 0x0128 }
            char r7 = r19.charValue()     // Catch:{ ClassCastException -> 0x0128 }
            kawa.lib.strings.stringSet$Ex(r0, r11, r7)
            gnu.lists.Pair r10 = kawa.lib.lists.cons(r13, r10)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r12 = r0.apply2(r12, r15)
            r13 = r3
            r15 = r4
            goto L_0x00ba
        L_0x0128:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 3
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r9, (int) r2, (java.lang.Object) r7)
            throw r1
        L_0x0130:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r9, (int) r2, (java.lang.Object) r14)
            throw r1
        L_0x0138:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r2 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r9, (int) r2, (java.lang.Object) r3)
            throw r0
        L_0x0141:
            r0 = move-exception
            r2 = 1
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r3, (int) r2, (java.lang.Object) r4)
            throw r1
        L_0x0149:
            gnu.kawa.functions.ApplyToArgs r0 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r4 = loc$make$Mnfinal
            java.lang.Object r4 = r4.get()     // Catch:{ UnboundLocationException -> 0x028a }
            java.lang.Object r4 = r0.apply2(r4, r9)
            r0 = r4
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ ClassCastException -> 0x0281 }
            int r0 = kawa.lib.strings.stringLength(r0)
            gnu.mapping.Location r7 = loc$base
            java.lang.Object r7 = r7.get()     // Catch:{ UnboundLocationException -> 0x0275 }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ ClassCastException -> 0x026c }
            int r7 = kawa.lib.strings.stringLength(r7)
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r9 = r9.apply2(r15, r14)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r20 = r10
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r21 = r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object r3 = r10.apply2(r3, r12)
            java.lang.Object r3 = r11.apply2(r3, r9)
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Integer r11 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r10.apply2(r3, r11)
            r10 = r3
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0262 }
            int r3 = r10.intValue()     // Catch:{ ClassCastException -> 0x0262 }
            java.lang.CharSequence r3 = kawa.lib.strings.makeString(r3)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0259 }
            r10 = 0
            $PcStringCopy$Ex(r3, r10, r4, r10, r0)
            r4 = r13
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0250 }
            r4 = r14
            java.lang.Number r4 = (java.lang.Number) r4     // Catch:{ ClassCastException -> 0x0248 }
            int r4 = r4.intValue()     // Catch:{ ClassCastException -> 0x0248 }
            r10 = r15
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0240 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0240 }
            $PcStringCopy$Ex(r3, r0, r13, r4, r10)
            gnu.kawa.functions.AddOp r4 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r0 = r4.apply2(r0, r9)
            r4 = r0
            r10 = r20
        L_0x01be:
            boolean r0 = kawa.lib.lists.isPair(r10)
            if (r0 == 0) goto L_0x0209
            gnu.expr.GenericProc r0 = kawa.lib.lists.car
            java.lang.Object r9 = r0.apply1(r10)
            gnu.expr.GenericProc r0 = kawa.lib.lists.cdr
            java.lang.Object r10 = r0.apply1(r10)
            r0 = r9
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ ClassCastException -> 0x0200 }
            int r0 = kawa.lib.strings.stringLength(r0)
            r11 = r4
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x01f8 }
            int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x01f8 }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x01ef }
            r12 = 0
            $PcStringCopy$Ex(r3, r11, r9, r12, r0)
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r4 = r9.apply2(r4, r0)
            goto L_0x01be
        L_0x01ef:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 2
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r9)
            throw r0
        L_0x01f8:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r4)
            throw r1
        L_0x0200:
            r0 = move-exception
            r3 = 1
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r3, (java.lang.Object) r9)
            throw r0
        L_0x0209:
            r0 = r4
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x0238 }
            int r0 = r0.intValue()     // Catch:{ ClassCastException -> 0x0238 }
            gnu.mapping.Location r1 = loc$base
            java.lang.Object r1 = r1.get()     // Catch:{ UnboundLocationException -> 0x022c }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ ClassCastException -> 0x0223 }
            r2 = 0
            $PcStringCopy$Ex(r3, r0, r1, r2, r7)
            r0 = r18
            java.lang.Object r0 = r5.apply4(r6, r0, r8, r3)
            return r0
        L_0x0223:
            r0 = move-exception
            r3 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r4 = 2
            r0.<init>((java.lang.ClassCastException) r3, (java.lang.String) r2, (int) r4, (java.lang.Object) r1)
            throw r0
        L_0x022c:
            r0 = move-exception
            r1 = r0
            r0 = 463(0x1cf, float:6.49E-43)
            r2 = 30
            r3 = r17
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x0238:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r4)
            throw r1
        L_0x0240:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 4
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r15)
            throw r1
        L_0x0248:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 3
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r14)
            throw r1
        L_0x0250:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 2
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r13)
            throw r0
        L_0x0259:
            r0 = move-exception
            r3 = 2
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r4)
            throw r0
        L_0x0262:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = r21
            r5 = 1
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r5, (java.lang.Object) r3)
            throw r1
        L_0x026c:
            r0 = move-exception
            r5 = 1
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r5, (java.lang.Object) r7)
            throw r0
        L_0x0275:
            r0 = move-exception
            r3 = r17
            r1 = r0
            r0 = 449(0x1c1, float:6.29E-43)
            r2 = 31
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x0281:
            r0 = move-exception
            r5 = 1
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r5, (java.lang.Object) r4)
            throw r0
        L_0x028a:
            r0 = move-exception
            r3 = r17
            r1 = r0
            r0 = 447(0x1bf, float:6.26E-43)
            r2 = 20
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x0296:
            r0 = move-exception
            r1 = r0
            r0 = 46
            r1.setLine(r3, r13, r0)
            throw r1
        L_0x029e:
            r0 = move-exception
            r1 = r0
            r0 = 6
            r1.setLine(r3, r13, r0)
            throw r1
        L_0x02a5:
            r0 = move-exception
            r1 = r0
            r0 = 57
            r1.setLine(r3, r12, r0)
            throw r1
        L_0x02ad:
            r0 = move-exception
            r1 = r0
            r2 = 20
            r1.setLine(r3, r12, r2)
            throw r1
        L_0x02b5:
            r0 = move-exception
            r1 = r0
            r0 = 420(0x1a4, float:5.89E-43)
            r2 = 3
            r1.setLine(r3, r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringUnfoldRight$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object stringForEach$V(Object obj, Object obj2, Object[] objArr) {
        frame7 frame710 = new frame7();
        frame710.proc = obj;
        frame710.s = obj2;
        frame710.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame710.proc, string$Mnfor$Mneach);
            return call_with_values.callWithValues(frame710.lambda$Fn19, frame710.lambda$Fn20);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 468, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame7 extends ModuleBody {
        final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 16, (Object) null, 0);
        final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 17, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 16 ? lambda19() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 17 ? lambda20(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 16) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 17) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda19() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda20(Object obj, Object obj2) {
            while (Scheme.numLss.apply2(obj, obj2) != Boolean.FALSE) {
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj3 = this.proc;
                Object obj4 = this.s;
                try {
                    try {
                        applyToArgs.apply2(obj3, Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj).intValue())));
                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, obj);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, obj4);
                }
            }
            return Values.empty;
        }
    }

    public static Object stringForEachIndex$V(Object obj, Object obj2, Object[] objArr) {
        frame8 frame810 = new frame8();
        frame810.proc = obj;
        frame810.s = obj2;
        frame810.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), misc.procedure$Qu, frame810.proc, string$Mnfor$Mneach$Mnindex);
            return call_with_values.callWithValues(frame810.lambda$Fn21, frame810.lambda$Fn22);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 476, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame8 extends ModuleBody {
        final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 18, (Object) null, 0);
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 19, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 18 ? lambda21() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 19 ? lambda22(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 18) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 19) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda21() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach$Mnindex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda22(Object obj, Object obj2) {
            while (Scheme.numLss.apply2(obj, obj2) != Boolean.FALSE) {
                Scheme.applyToArgs.apply2(this.proc, obj);
                obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
            }
            return Values.empty;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame9 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 20, (Object) null, 0);
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 21, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 20 ? lambda23() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 21 ? lambda24(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 20) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 21) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda23() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnevery, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda24(Object obj, Object obj2) {
            Object apply2;
            if (characters.isChar(this.criterion)) {
                while (true) {
                    Object apply22 = Scheme.numGEq.apply2(obj, obj2);
                    try {
                        boolean booleanValue = ((Boolean) apply22).booleanValue();
                        if (booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.s;
                            try {
                                try {
                                    boolean isChar$Eq = characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj).intValue())));
                                    if (!isChar$Eq) {
                                        return isChar$Eq ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                    obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        while (true) {
                            Object apply23 = Scheme.numGEq.apply2(obj, obj2);
                            try {
                                boolean booleanValue2 = ((Boolean) apply23).booleanValue();
                                if (booleanValue2) {
                                    return booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                try {
                                    Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                    Object obj6 = this.criterion;
                                    Object obj7 = this.s;
                                    try {
                                        try {
                                            Object apply3 = applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj).intValue())));
                                            if (apply3 == Boolean.FALSE) {
                                                return apply3;
                                            }
                                            obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "string-ref", 2, obj);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "string-ref", 1, obj7);
                                    }
                                } catch (UnboundLocationException e7) {
                                    e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 492, 9);
                                    throw e7;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply23);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply24 = Scheme.numEqu.apply2(obj, obj2);
                        try {
                            boolean booleanValue3 = ((Boolean) apply24).booleanValue();
                            if (booleanValue3) {
                                return booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                            }
                            do {
                                Object obj8 = this.s;
                                try {
                                    try {
                                        char stringRef = strings.stringRef((CharSequence) obj8, ((Number) obj).intValue());
                                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        if (Scheme.numEqu.apply2(obj, obj2) != Boolean.FALSE) {
                                            return Scheme.applyToArgs.apply2(this.criterion, Char.make(stringRef));
                                        }
                                        apply2 = Scheme.applyToArgs.apply2(this.criterion, Char.make(stringRef));
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "string-ref", 2, obj);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "string-ref", 1, obj8);
                                }
                            } while (apply2 != Boolean.FALSE);
                            return apply2;
                        } catch (ClassCastException e11) {
                            throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply24);
                        }
                    } else {
                        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[]{srfi13.string$Mnevery, this.criterion});
                    }
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 489, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringEvery$V(Object obj, Object obj2, Object[] objArr) {
        frame9 frame97 = new frame9();
        frame97.criterion = obj;
        frame97.s = obj2;
        frame97.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame97.lambda$Fn23, frame97.lambda$Fn24);
    }

    /* compiled from: srfi13.scm */
    public class frame10 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn25 = new ModuleMethod(this, 22, (Object) null, 0);
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 23, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 22 ? lambda25() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 23 ? lambda26(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 22) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 23) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda25() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnany, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda26(Object obj, Object obj2) {
            Object apply2;
            if (characters.isChar(this.criterion)) {
                while (true) {
                    Object apply22 = Scheme.numLss.apply2(obj, obj2);
                    try {
                        boolean booleanValue = ((Boolean) apply22).booleanValue();
                        if (!booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.s;
                            try {
                                try {
                                    boolean isChar$Eq = characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj).intValue())));
                                    if (isChar$Eq) {
                                        return isChar$Eq ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                    obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        while (true) {
                            Object apply23 = Scheme.numLss.apply2(obj, obj2);
                            try {
                                boolean booleanValue2 = ((Boolean) apply23).booleanValue();
                                if (!booleanValue2) {
                                    return booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                try {
                                    Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                    Object obj6 = this.criterion;
                                    Object obj7 = this.s;
                                    try {
                                        try {
                                            Object apply3 = applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj).intValue())));
                                            if (apply3 != Boolean.FALSE) {
                                                return apply3;
                                            }
                                            obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "string-ref", 2, obj);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "string-ref", 1, obj7);
                                    }
                                } catch (UnboundLocationException e7) {
                                    e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BLUETOOTH_END_OF_STREAM, 9);
                                    throw e7;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply23);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply24 = Scheme.numLss.apply2(obj, obj2);
                        try {
                            boolean booleanValue3 = ((Boolean) apply24).booleanValue();
                            if (!booleanValue3) {
                                return booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                            }
                            do {
                                Object obj8 = this.s;
                                try {
                                    try {
                                        char stringRef = strings.stringRef((CharSequence) obj8, ((Number) obj).intValue());
                                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        if (Scheme.numEqu.apply2(obj, obj2) != Boolean.FALSE) {
                                            return Scheme.applyToArgs.apply2(this.criterion, Char.make(stringRef));
                                        }
                                        apply2 = Scheme.applyToArgs.apply2(this.criterion, Char.make(stringRef));
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "string-ref", 2, obj);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "string-ref", 1, obj8);
                                }
                            } while (apply2 == Boolean.FALSE);
                            return apply2;
                        } catch (ClassCastException e11) {
                            throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply24);
                        }
                    } else {
                        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[]{srfi13.string$Mnany, this.criterion});
                    }
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BLUETOOTH_NOT_CONNECTED_TO_DEVICE, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringAny$V(Object obj, Object obj2, Object[] objArr) {
        frame10 frame102 = new frame10();
        frame102.criterion = obj;
        frame102.s = obj2;
        frame102.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame102.lambda$Fn25, frame102.lambda$Fn26);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 218) {
            switch (i) {
                case 280:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 281:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 282:
                    if (!(obj instanceof CharSequence)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 283:
                    if (!(obj instanceof CharSequence)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        } else {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static CharSequence stringTabulate(Object obj, int i) {
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj2 = location.get();
            ModuleMethod moduleMethod = misc.procedure$Qu;
            ModuleMethod moduleMethod2 = string$Mntabulate;
            applyToArgs.apply4(obj2, moduleMethod, obj, moduleMethod2);
            try {
                Scheme.applyToArgs.apply4(location.get(), lambda$Fn27, Integer.valueOf(i), moduleMethod2);
                CharSequence makeString = strings.makeString(i);
                int i2 = i - 1;
                while (i2 >= 0) {
                    try {
                        CharSeq charSeq = (CharSeq) makeString;
                        Object apply2 = Scheme.applyToArgs.apply2(obj, Integer.valueOf(i2));
                        try {
                            strings.stringSet$Ex(charSeq, i2, ((Char) apply2).charValue());
                            i2--;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-set!", 3, apply2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-set!", 1, (Object) makeString);
                    }
                }
                return makeString;
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 535, 3);
                throw e3;
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 534, 3);
            throw e4;
        }
    }

    static boolean lambda27(Object obj) {
        boolean isInteger = numbers.isInteger(obj);
        if (!isInteger) {
            return isInteger;
        }
        boolean isExact = numbers.isExact(obj);
        return isExact ? ((Boolean) Scheme.numLEq.apply2(Lit0, obj)).booleanValue() : isExact;
    }

    public static Object $PcStringPrefixLength(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean z = false;
        Object min = numbers.min(AddOp.$Mn.apply2(obj3, obj2), AddOp.$Mn.apply2(obj6, obj5));
        Object apply2 = AddOp.$Pl.apply2(obj2, min);
        if (obj == obj4) {
            z = true;
        }
        if (z) {
            if (Scheme.numEqu.apply2(obj2, obj5) != Boolean.FALSE) {
                return min;
            }
        } else if (z) {
            return min;
        }
        Object obj7 = obj2;
        while (true) {
            Object apply22 = Scheme.numGEq.apply2(obj7, apply2);
            try {
                boolean booleanValue = ((Boolean) apply22).booleanValue();
                if (!booleanValue) {
                    try {
                        try {
                            try {
                                try {
                                    if (!characters.isChar$Eq(Char.make(strings.stringRef((CharSequence) obj, ((Number) obj7).intValue())), Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj5).intValue())))) {
                                        break;
                                    }
                                    AddOp addOp = AddOp.$Pl;
                                    IntNum intNum = Lit1;
                                    obj7 = addOp.apply2(obj7, intNum);
                                    obj5 = AddOp.$Pl.apply2(obj5, intNum);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj5);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 2, obj7);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-ref", 1, obj);
                    }
                } else if (booleanValue) {
                    break;
                } else {
                    AddOp addOp2 = AddOp.$Pl;
                    IntNum intNum2 = Lit1;
                    obj7 = addOp2.apply2(obj7, intNum2);
                    obj5 = AddOp.$Pl.apply2(obj5, intNum2);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
            }
        }
        return AddOp.$Mn.apply2(obj7, obj2);
    }

    public static Object $PcStringSuffixLength(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean z = false;
        Object min = numbers.min(AddOp.$Mn.apply2(obj3, obj2), AddOp.$Mn.apply2(obj6, obj5));
        Object apply2 = AddOp.$Mn.apply2(obj3, min);
        if (obj == obj4) {
            z = true;
        }
        if (z) {
            if (Scheme.numEqu.apply2(obj3, obj6) != Boolean.FALSE) {
                return min;
            }
        } else if (z) {
            return min;
        }
        AddOp addOp = AddOp.$Mn;
        IntNum intNum = Lit1;
        Object apply22 = addOp.apply2(obj3, intNum);
        Object apply23 = AddOp.$Mn.apply2(obj6, intNum);
        while (true) {
            Object apply24 = Scheme.numLss.apply2(apply22, apply2);
            try {
                boolean booleanValue = ((Boolean) apply24).booleanValue();
                if (!booleanValue) {
                    try {
                        try {
                            try {
                                try {
                                    if (!characters.isChar$Eq(Char.make(strings.stringRef((CharSequence) obj, ((Number) apply22).intValue())), Char.make(strings.stringRef((CharSequence) obj4, ((Number) apply23).intValue())))) {
                                        break;
                                    }
                                    AddOp addOp2 = AddOp.$Mn;
                                    IntNum intNum2 = Lit1;
                                    apply22 = addOp2.apply2(apply22, intNum2);
                                    apply23 = AddOp.$Mn.apply2(apply23, intNum2);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, apply23);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-ref", 2, apply22);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-ref", 1, obj);
                    }
                } else if (booleanValue) {
                    break;
                } else {
                    AddOp addOp22 = AddOp.$Mn;
                    IntNum intNum22 = Lit1;
                    apply22 = addOp22.apply2(apply22, intNum22);
                    apply23 = AddOp.$Mn.apply2(apply23, intNum22);
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply24);
            }
        }
        return AddOp.$Mn.apply2(AddOp.$Mn.apply2(obj3, apply22), Lit1);
    }

    public static int $PcStringPrefixLengthCi(Object obj, int i, int i2, Object obj2, int i3, int i4) {
        Object min = numbers.min(Integer.valueOf(i2 - i), Integer.valueOf(i4 - i3));
        try {
            int intValue = ((Number) min).intValue();
            int i5 = i + intValue;
            boolean z = obj == obj2;
            if (z) {
                if (i == i3) {
                    return intValue;
                }
            } else if (z) {
                return intValue;
            }
            int i6 = i;
            while (true) {
                boolean z2 = i6 >= i5;
                if (!z2) {
                    try {
                        try {
                            if (!unicode.isCharCi$Eq(Char.make(strings.stringRef((CharSequence) obj, i6)), Char.make(strings.stringRef((CharSequence) obj2, i3)))) {
                                break;
                            }
                            i3++;
                            i6++;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-ref", 1, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-ref", 1, obj);
                    }
                } else if (z2) {
                    break;
                } else {
                    i3++;
                    i6++;
                }
            }
            return i6 - i;
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "delta", -2, min);
        }
    }

    public static int $PcStringSuffixLengthCi(Object obj, int i, int i2, Object obj2, int i3, int i4) {
        Object min = numbers.min(Integer.valueOf(i2 - i), Integer.valueOf(i4 - i3));
        try {
            int intValue = ((Number) min).intValue();
            int i5 = i2 - intValue;
            boolean z = obj == obj2;
            if (z) {
                if (i2 == i4) {
                    return intValue;
                }
            } else if (z) {
                return intValue;
            }
            int i6 = i2 - 1;
            int i7 = i4 - 1;
            while (true) {
                boolean z2 = i6 < i5;
                if (!z2) {
                    try {
                        try {
                            if (!unicode.isCharCi$Eq(Char.make(strings.stringRef((CharSequence) obj, i6)), Char.make(strings.stringRef((CharSequence) obj2, i7)))) {
                                break;
                            }
                            i7--;
                            i6--;
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-ref", 1, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-ref", 1, obj);
                    }
                } else if (z2) {
                    break;
                } else {
                    i7--;
                    i6--;
                }
            }
            return (i2 - i6) - 1;
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "delta", -2, min);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame11 extends ModuleBody {
        final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 26, (Object) null, 0);
        final ModuleMethod lambda$Fn29 = new ModuleMethod(this, 27, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 26 ? lambda28() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 27 ? lambda29(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 26) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 27) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda28() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda29(Object obj, Object obj2, Object obj3) {
            frame12 frame12 = new frame12();
            frame12.staticLink = this;
            frame12.rest = obj;
            frame12.start1 = obj2;
            frame12.end1 = obj3;
            return call_with_values.callWithValues(frame12.lambda$Fn30, frame12.lambda$Fn31);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame12 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn30 = new ModuleMethod(this, 24, (Object) null, 0);
        final ModuleMethod lambda$Fn31 = new ModuleMethod(this, 25, (Object) null, 8194);
        Object rest;
        Object start1;
        frame11 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 24 ? lambda30() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 25 ? lambda31(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 24) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 25) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda30() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda31(Object obj, Object obj2) {
            return srfi13.$PcStringPrefixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object stringPrefixLength$V(Object obj, Object obj2, Object[] objArr) {
        frame11 frame112 = new frame11();
        frame112.s1 = obj;
        frame112.s2 = obj2;
        frame112.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame112.lambda$Fn28, frame112.lambda$Fn29);
    }

    /* compiled from: srfi13.scm */
    public class frame13 extends ModuleBody {
        final ModuleMethod lambda$Fn32 = new ModuleMethod(this, 30, (Object) null, 0);
        final ModuleMethod lambda$Fn33 = new ModuleMethod(this, 31, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 30 ? lambda32() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 31 ? lambda33(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 30) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 31) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda32() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda33(Object obj, Object obj2, Object obj3) {
            frame14 frame14 = new frame14();
            frame14.staticLink = this;
            frame14.rest = obj;
            frame14.start1 = obj2;
            frame14.end1 = obj3;
            return call_with_values.callWithValues(frame14.lambda$Fn34, frame14.lambda$Fn35);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame14 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn34 = new ModuleMethod(this, 28, (Object) null, 0);
        final ModuleMethod lambda$Fn35 = new ModuleMethod(this, 29, (Object) null, 8194);
        Object rest;
        Object start1;
        frame13 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 28 ? lambda34() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 29 ? lambda35(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 28) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 29) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda34() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda35(Object obj, Object obj2) {
            return srfi13.$PcStringSuffixLength(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object stringSuffixLength$V(Object obj, Object obj2, Object[] objArr) {
        frame13 frame132 = new frame13();
        frame132.s1 = obj;
        frame132.s2 = obj2;
        frame132.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame132.lambda$Fn32, frame132.lambda$Fn33);
    }

    /* compiled from: srfi13.scm */
    public class frame15 extends ModuleBody {
        final ModuleMethod lambda$Fn36 = new ModuleMethod(this, 34, (Object) null, 0);
        final ModuleMethod lambda$Fn37 = new ModuleMethod(this, 35, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 34 ? lambda36() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 35 ? lambda37(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 34) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 35) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda36() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda37(Object obj, Object obj2, Object obj3) {
            frame16 frame16 = new frame16();
            frame16.staticLink = this;
            frame16.rest = obj;
            frame16.start1 = obj2;
            frame16.end1 = obj3;
            return call_with_values.callWithValues(frame16.lambda$Fn38, frame16.lambda$Fn39);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame16 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn38 = new ModuleMethod(this, 32, (Object) null, 0);
        final ModuleMethod lambda$Fn39 = new ModuleMethod(this, 33, (Object) null, 8194);
        Object rest;
        Object start1;
        frame15 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 32 ? lambda38() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 33 ? Integer.valueOf(lambda39(obj, obj2)) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 32) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 33) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda38() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public int lambda39(Object obj, Object obj2) {
            Object obj3 = this.staticLink.s1;
            Object obj4 = this.start1;
            try {
                int intValue = ((Number) obj4).intValue();
                Object obj5 = this.end1;
                try {
                    try {
                        try {
                            return srfi13.$PcStringPrefixLengthCi(obj3, intValue, ((Number) obj5).intValue(), this.staticLink.s2, ((Number) obj).intValue(), ((Number) obj2).intValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%string-prefix-length-ci", 5, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%string-prefix-length-ci", 4, obj);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%string-prefix-length-ci", 2, obj5);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "%string-prefix-length-ci", 1, obj4);
            }
        }
    }

    public static Object stringPrefixLengthCi$V(Object obj, Object obj2, Object[] objArr) {
        frame15 frame152 = new frame15();
        frame152.s1 = obj;
        frame152.s2 = obj2;
        frame152.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame152.lambda$Fn36, frame152.lambda$Fn37);
    }

    /* compiled from: srfi13.scm */
    public class frame17 extends ModuleBody {
        final ModuleMethod lambda$Fn40 = new ModuleMethod(this, 38, (Object) null, 0);
        final ModuleMethod lambda$Fn41 = new ModuleMethod(this, 39, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 38 ? lambda40() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 39 ? lambda41(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 38) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 39) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda40() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda41(Object obj, Object obj2, Object obj3) {
            frame18 frame18 = new frame18();
            frame18.staticLink = this;
            frame18.rest = obj;
            frame18.start1 = obj2;
            frame18.end1 = obj3;
            return call_with_values.callWithValues(frame18.lambda$Fn42, frame18.lambda$Fn43);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame18 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn42 = new ModuleMethod(this, 36, (Object) null, 0);
        final ModuleMethod lambda$Fn43 = new ModuleMethod(this, 37, (Object) null, 8194);
        Object rest;
        Object start1;
        frame17 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 36 ? lambda42() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 37 ? Integer.valueOf(lambda43(obj, obj2)) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 36) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 37) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda42() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public int lambda43(Object obj, Object obj2) {
            Object obj3 = this.staticLink.s1;
            Object obj4 = this.start1;
            try {
                int intValue = ((Number) obj4).intValue();
                Object obj5 = this.end1;
                try {
                    try {
                        try {
                            return srfi13.$PcStringSuffixLengthCi(obj3, intValue, ((Number) obj5).intValue(), this.staticLink.s2, ((Number) obj).intValue(), ((Number) obj2).intValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%string-suffix-length-ci", 5, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%string-suffix-length-ci", 4, obj);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%string-suffix-length-ci", 2, obj5);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "%string-suffix-length-ci", 1, obj4);
            }
        }
    }

    public static Object stringSuffixLengthCi$V(Object obj, Object obj2, Object[] objArr) {
        frame17 frame172 = new frame17();
        frame172.s1 = obj;
        frame172.s2 = obj2;
        frame172.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame172.lambda$Fn40, frame172.lambda$Fn41);
    }

    /* compiled from: srfi13.scm */
    public class frame19 extends ModuleBody {
        final ModuleMethod lambda$Fn44 = new ModuleMethod(this, 42, (Object) null, 0);
        final ModuleMethod lambda$Fn45 = new ModuleMethod(this, 43, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 42 ? lambda44() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 43 ? lambda45(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 42) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 43) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda44() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Qu, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda45(Object obj, Object obj2, Object obj3) {
            frame20 frame20 = new frame20();
            frame20.staticLink = this;
            frame20.rest = obj;
            frame20.start1 = obj2;
            frame20.end1 = obj3;
            return call_with_values.callWithValues(frame20.lambda$Fn46, frame20.lambda$Fn47);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame20 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 40, (Object) null, 0);
        final ModuleMethod lambda$Fn47 = new ModuleMethod(this, 41, (Object) null, 8194);
        Object rest;
        Object start1;
        frame19 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 40 ? lambda46() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 41 ? lambda47(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 40) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 41) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda46() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Qu, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda47(Object obj, Object obj2) {
            return srfi13.$PcStringPrefix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object isStringPrefix$V(Object obj, Object obj2, Object[] objArr) {
        frame19 frame192 = new frame19();
        frame192.s1 = obj;
        frame192.s2 = obj2;
        frame192.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame192.lambda$Fn44, frame192.lambda$Fn45);
    }

    /* compiled from: srfi13.scm */
    public class frame21 extends ModuleBody {
        final ModuleMethod lambda$Fn48 = new ModuleMethod(this, 46, (Object) null, 0);
        final ModuleMethod lambda$Fn49 = new ModuleMethod(this, 47, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 46 ? lambda48() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 47 ? lambda49(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 46) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 47) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda48() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda49(Object obj, Object obj2, Object obj3) {
            frame22 frame22 = new frame22();
            frame22.staticLink = this;
            frame22.rest = obj;
            frame22.start1 = obj2;
            frame22.end1 = obj3;
            return call_with_values.callWithValues(frame22.lambda$Fn50, frame22.lambda$Fn51);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame22 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 44, (Object) null, 0);
        final ModuleMethod lambda$Fn51 = new ModuleMethod(this, 45, (Object) null, 8194);
        Object rest;
        Object start1;
        frame21 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 44 ? lambda50() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 45 ? lambda51(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 44) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 45) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda50() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Qu, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda51(Object obj, Object obj2) {
            return srfi13.$PcStringSuffix$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object isStringSuffix$V(Object obj, Object obj2, Object[] objArr) {
        frame21 frame212 = new frame21();
        frame212.s1 = obj;
        frame212.s2 = obj2;
        frame212.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame212.lambda$Fn48, frame212.lambda$Fn49);
    }

    /* compiled from: srfi13.scm */
    public class frame23 extends ModuleBody {
        final ModuleMethod lambda$Fn52 = new ModuleMethod(this, 50, (Object) null, 0);
        final ModuleMethod lambda$Fn53 = new ModuleMethod(this, 51, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 50 ? lambda52() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 51 ? lambda53(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 50) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 51) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda52() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda53(Object obj, Object obj2, Object obj3) {
            frame24 frame24 = new frame24();
            frame24.staticLink = this;
            frame24.rest = obj;
            frame24.start1 = obj2;
            frame24.end1 = obj3;
            return call_with_values.callWithValues(frame24.lambda$Fn54, frame24.lambda$Fn55);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame24 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn54 = new ModuleMethod(this, 48, (Object) null, 0);
        final ModuleMethod lambda$Fn55 = new ModuleMethod(this, 49, (Object) null, 8194);
        Object rest;
        Object start1;
        frame23 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 48 ? lambda54() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 49 ? lambda55(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 48) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 49) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda54() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda55(Object obj, Object obj2) {
            return srfi13.$PcStringPrefixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object isStringPrefixCi$V(Object obj, Object obj2, Object[] objArr) {
        frame23 frame232 = new frame23();
        frame232.s1 = obj;
        frame232.s2 = obj2;
        frame232.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame232.lambda$Fn52, frame232.lambda$Fn53);
    }

    /* compiled from: srfi13.scm */
    public class frame25 extends ModuleBody {
        final ModuleMethod lambda$Fn56 = new ModuleMethod(this, 54, (Object) null, 0);
        final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 55, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 54 ? lambda56() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 55 ? lambda57(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 54) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 55) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda56() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda57(Object obj, Object obj2, Object obj3) {
            frame26 frame26 = new frame26();
            frame26.staticLink = this;
            frame26.rest = obj;
            frame26.start1 = obj2;
            frame26.end1 = obj3;
            return call_with_values.callWithValues(frame26.lambda$Fn58, frame26.lambda$Fn59);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame26 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn58 = new ModuleMethod(this, 52, (Object) null, 0);
        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 53, (Object) null, 8194);
        Object rest;
        Object start1;
        frame25 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 52 ? lambda58() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 53 ? lambda59(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 52) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 53) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda58() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda59(Object obj, Object obj2) {
            return srfi13.$PcStringSuffixCi$Qu(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2);
        }
    }

    public static Object isStringSuffixCi$V(Object obj, Object obj2, Object[] objArr) {
        frame25 frame252 = new frame25();
        frame252.s1 = obj;
        frame252.s2 = obj2;
        frame252.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame252.lambda$Fn56, frame252.lambda$Fn57);
    }

    public static Object $PcStringPrefix$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object apply2 = AddOp.$Mn.apply2(obj3, obj2);
        Object apply22 = Scheme.numLEq.apply2(apply2, AddOp.$Mn.apply2(obj6, obj5));
        try {
            boolean booleanValue = ((Boolean) apply22).booleanValue();
            if (booleanValue) {
                return Scheme.numEqu.apply2($PcStringPrefixLength(obj, obj2, obj3, obj4, obj5, obj6), apply2);
            }
            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
        }
    }

    public static Object $PcStringSuffix$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object apply2 = AddOp.$Mn.apply2(obj3, obj2);
        Object apply22 = Scheme.numLEq.apply2(apply2, AddOp.$Mn.apply2(obj6, obj5));
        try {
            boolean booleanValue = ((Boolean) apply22).booleanValue();
            if (booleanValue) {
                return Scheme.numEqu.apply2(apply2, $PcStringSuffixLength(obj, obj2, obj3, obj4, obj5, obj6));
            }
            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
        }
    }

    public static Object $PcStringPrefixCi$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object apply2 = AddOp.$Mn.apply2(obj3, obj2);
        Object apply22 = Scheme.numLEq.apply2(apply2, AddOp.$Mn.apply2(obj6, obj5));
        try {
            boolean booleanValue = ((Boolean) apply22).booleanValue();
            if (!booleanValue) {
                return booleanValue ? Boolean.TRUE : Boolean.FALSE;
            }
            try {
                try {
                    try {
                        try {
                            return Scheme.numEqu.apply2(apply2, Integer.valueOf($PcStringPrefixLengthCi(obj, ((Number) obj2).intValue(), ((Number) obj3).intValue(), obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue())));
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%string-prefix-length-ci", 5, obj6);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%string-prefix-length-ci", 4, obj5);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%string-prefix-length-ci", 2, obj3);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "%string-prefix-length-ci", 1, obj2);
            }
        } catch (ClassCastException e5) {
            throw new WrongType(e5, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
        }
    }

    public static Object $PcStringSuffixCi$Qu(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object apply2 = AddOp.$Mn.apply2(obj3, obj2);
        Object apply22 = Scheme.numLEq.apply2(apply2, AddOp.$Mn.apply2(obj6, obj5));
        try {
            boolean booleanValue = ((Boolean) apply22).booleanValue();
            if (!booleanValue) {
                return booleanValue ? Boolean.TRUE : Boolean.FALSE;
            }
            try {
                try {
                    try {
                        try {
                            return Scheme.numEqu.apply2(apply2, Integer.valueOf($PcStringSuffixLengthCi(obj, ((Number) obj2).intValue(), ((Number) obj3).intValue(), obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue())));
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%string-suffix-length-ci", 5, obj6);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%string-suffix-length-ci", 4, obj5);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%string-suffix-length-ci", 2, obj3);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "%string-suffix-length-ci", 1, obj2);
            }
        } catch (ClassCastException e5) {
            throw new WrongType(e5, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
        if (kawa.lib.characters.isChar$Ls(r4, gnu.text.Char.make(kawa.lib.strings.stringRef(r7, ((java.lang.Number) r8).intValue()))) != false) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object $PcStringCompare(java.lang.Object r4, java.lang.Object r5, java.lang.Object r6, java.lang.Object r7, java.lang.Object r8, java.lang.Object r9, java.lang.Object r10, java.lang.Object r11, java.lang.Object r12) {
        /*
            java.lang.String r0 = "string-ref"
            gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r1 = r1.apply2(r6, r5)
            gnu.kawa.functions.AddOp r2 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r2 = r2.apply2(r9, r8)
            java.lang.Object r9 = $PcStringPrefixLength(r4, r5, r6, r7, r8, r9)
            gnu.kawa.functions.NumberCompare r3 = kawa.standard.Scheme.numEqu
            java.lang.Object r1 = r3.apply2(r9, r1)
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            if (r1 == r3) goto L_0x002e
            gnu.kawa.functions.ApplyToArgs r4 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.NumberCompare r5 = kawa.standard.Scheme.numEqu
            java.lang.Object r5 = r5.apply2(r9, r2)
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            if (r5 == r7) goto L_0x0029
            r10 = r11
        L_0x0029:
            java.lang.Object r4 = r4.apply2(r10, r6)
            goto L_0x007d
        L_0x002e:
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.NumberCompare r11 = kawa.standard.Scheme.numEqu
            java.lang.Object r11 = r11.apply2(r9, r2)
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r11 == r1) goto L_0x003b
            goto L_0x0072
        L_0x003b:
            r11 = 1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ ClassCastException -> 0x0093 }
            gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r1 = r1.apply2(r5, r9)
            r2 = 2
            r3 = r1
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ ClassCastException -> 0x008c }
            int r1 = r3.intValue()     // Catch:{ ClassCastException -> 0x008c }
            char r4 = kawa.lib.strings.stringRef(r4, r1)
            gnu.text.Char r4 = gnu.text.Char.make(r4)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ ClassCastException -> 0x0085 }
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r8 = r11.apply2(r8, r9)
            r11 = r8
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x007e }
            int r8 = r11.intValue()     // Catch:{ ClassCastException -> 0x007e }
            char r7 = kawa.lib.strings.stringRef(r7, r8)
            gnu.text.Char r7 = gnu.text.Char.make(r7)
            boolean r4 = kawa.lib.characters.isChar$Ls(r4, r7)
            if (r4 == 0) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r10 = r12
        L_0x0073:
            gnu.kawa.functions.AddOp r4 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Object r4 = r4.apply2(r9, r5)
            java.lang.Object r4 = r6.apply2(r10, r4)
        L_0x007d:
            return r4
        L_0x007e:
            r4 = move-exception
            gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
            r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r0, (int) r2, (java.lang.Object) r8)
            throw r5
        L_0x0085:
            r4 = move-exception
            gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
            r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r0, (int) r11, (java.lang.Object) r7)
            throw r5
        L_0x008c:
            r4 = move-exception
            gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
            r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r0, (int) r2, (java.lang.Object) r1)
            throw r5
        L_0x0093:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r0, (int) r11, (java.lang.Object) r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.$PcStringCompare(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object $PcStringCompareCi(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Object obj10;
        Object obj11 = obj;
        Object obj12 = obj2;
        Object obj13 = obj3;
        Object obj14 = obj4;
        Object obj15 = obj5;
        Object obj16 = obj6;
        Object apply2 = AddOp.$Mn.apply2(obj13, obj12);
        Object apply22 = AddOp.$Mn.apply2(obj16, obj15);
        try {
            try {
                try {
                    try {
                        int $PcStringPrefixLengthCi = $PcStringPrefixLengthCi(obj, ((Number) obj12).intValue(), ((Number) obj13).intValue(), obj4, ((Number) obj15).intValue(), ((Number) obj16).intValue());
                        if (Scheme.numEqu.apply2(Integer.valueOf($PcStringPrefixLengthCi), apply2) != Boolean.FALSE) {
                            return Scheme.applyToArgs.apply2(Scheme.numEqu.apply2(Integer.valueOf($PcStringPrefixLengthCi), apply22) != Boolean.FALSE ? obj8 : obj7, obj13);
                        }
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        if (Scheme.numEqu.apply2(Integer.valueOf($PcStringPrefixLengthCi), apply22) == Boolean.FALSE) {
                            try {
                                CharSequence charSequence = (CharSequence) obj11;
                                Object apply23 = AddOp.$Pl.apply2(obj12, Integer.valueOf($PcStringPrefixLengthCi));
                                try {
                                    Char make = Char.make(strings.stringRef(charSequence, ((Number) apply23).intValue()));
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj14;
                                        Object apply24 = AddOp.$Pl.apply2(obj15, Integer.valueOf($PcStringPrefixLengthCi));
                                        try {
                                            if (unicode.isCharCi$Ls(make, Char.make(strings.stringRef(charSequence2, ((Number) apply24).intValue())))) {
                                                obj10 = obj7;
                                                return applyToArgs.apply2(obj10, AddOp.$Pl.apply2(obj12, Integer.valueOf($PcStringPrefixLengthCi)));
                                            }
                                        } catch (ClassCastException e) {
                                            throw new WrongType(e, "string-ref", 2, apply24);
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "string-ref", 1, obj14);
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "string-ref", 2, apply23);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "string-ref", 1, obj11);
                            }
                        }
                        obj10 = obj9;
                        return applyToArgs.apply2(obj10, AddOp.$Pl.apply2(obj12, Integer.valueOf($PcStringPrefixLengthCi)));
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "%string-prefix-length-ci", 5, obj16);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "%string-prefix-length-ci", 4, obj15);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, "%string-prefix-length-ci", 2, obj13);
            }
        } catch (ClassCastException e8) {
            throw new WrongType(e8, "%string-prefix-length-ci", 1, obj12);
        }
    }

    public static Object stringCompare$V(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object[] objArr) {
        frame27 frame272 = new frame27();
        frame272.s1 = obj;
        frame272.s2 = obj2;
        frame272.proc$Ls = obj3;
        frame272.proc$Eq = obj4;
        frame272.proc$Gr = obj5;
        frame272.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj6 = location.get();
            ModuleMethod moduleMethod = misc.procedure$Qu;
            Object obj7 = frame272.proc$Ls;
            ModuleMethod moduleMethod2 = string$Mncompare;
            applyToArgs.apply4(obj6, moduleMethod, obj7, moduleMethod2);
            try {
                Scheme.applyToArgs.apply4(location.get(), misc.procedure$Qu, frame272.proc$Eq, moduleMethod2);
                try {
                    Scheme.applyToArgs.apply4(location.get(), misc.procedure$Qu, frame272.proc$Gr, moduleMethod2);
                    return call_with_values.callWithValues(frame272.lambda$Fn60, frame272.lambda$Fn61);
                } catch (UnboundLocationException e) {
                    e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 728, 3);
                    throw e;
                }
            } catch (UnboundLocationException e2) {
                e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 727, 3);
                throw e2;
            }
        } catch (UnboundLocationException e3) {
            e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 726, 3);
            throw e3;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame27 extends ModuleBody {
        final ModuleMethod lambda$Fn60 = new ModuleMethod(this, 58, (Object) null, 0);
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 59, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 58 ? lambda60() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 59 ? lambda61(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 58) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 59) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda60() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda61(Object obj, Object obj2, Object obj3) {
            frame28 frame28 = new frame28();
            frame28.staticLink = this;
            frame28.rest = obj;
            frame28.start1 = obj2;
            frame28.end1 = obj3;
            return call_with_values.callWithValues(frame28.lambda$Fn62, frame28.lambda$Fn63);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame28 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn62 = new ModuleMethod(this, 56, (Object) null, 0);
        final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 57, (Object) null, 8194);
        Object rest;
        Object start1;
        frame27 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 56 ? lambda62() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 57 ? lambda63(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 56) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 57) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda62() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda63(Object obj, Object obj2) {
            return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
        }
    }

    public static Object stringCompareCi$V(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object[] objArr) {
        frame29 frame292 = new frame29();
        frame292.s1 = obj;
        frame292.s2 = obj2;
        frame292.proc$Ls = obj3;
        frame292.proc$Eq = obj4;
        frame292.proc$Gr = obj5;
        frame292.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj6 = location.get();
            ModuleMethod moduleMethod = misc.procedure$Qu;
            Object obj7 = frame292.proc$Ls;
            ModuleMethod moduleMethod2 = string$Mncompare$Mnci;
            applyToArgs.apply4(obj6, moduleMethod, obj7, moduleMethod2);
            try {
                Scheme.applyToArgs.apply4(location.get(), misc.procedure$Qu, frame292.proc$Eq, moduleMethod2);
                try {
                    Scheme.applyToArgs.apply4(location.get(), misc.procedure$Qu, frame292.proc$Gr, moduleMethod2);
                    return call_with_values.callWithValues(frame292.lambda$Fn64, frame292.lambda$Fn65);
                } catch (UnboundLocationException e) {
                    e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 736, 3);
                    throw e;
                }
            } catch (UnboundLocationException e2) {
                e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 735, 3);
                throw e2;
            }
        } catch (UnboundLocationException e3) {
            e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 734, 3);
            throw e3;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame29 extends ModuleBody {
        final ModuleMethod lambda$Fn64 = new ModuleMethod(this, 62, (Object) null, 0);
        final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 63, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 62 ? lambda64() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 63 ? lambda65(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 62) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 63) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda64() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare$Mnci, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda65(Object obj, Object obj2, Object obj3) {
            frame30 frame30 = new frame30();
            frame30.staticLink = this;
            frame30.rest = obj;
            frame30.start1 = obj2;
            frame30.end1 = obj3;
            return call_with_values.callWithValues(frame30.lambda$Fn66, frame30.lambda$Fn67);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame30 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn66 = new ModuleMethod(this, 60, (Object) null, 0);
        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 61, (Object) null, 8194);
        Object rest;
        Object start1;
        frame29 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 60 ? lambda66() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 61 ? lambda67(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 60) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 61) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda66() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare$Mnci, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda67(Object obj, Object obj2) {
            return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, this.staticLink.proc$Ls, this.staticLink.proc$Eq, this.staticLink.proc$Gr);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame31 extends ModuleBody {
        final ModuleMethod lambda$Fn68 = new ModuleMethod(this, 66, (Object) null, 0);
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 67, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 66 ? lambda68() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 67 ? lambda69(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 66) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 67) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda68() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda69(Object obj, Object obj2, Object obj3) {
            frame32 frame32 = new frame32();
            frame32.staticLink = this;
            frame32.rest = obj;
            frame32.start1 = obj2;
            frame32.end1 = obj3;
            return call_with_values.callWithValues(frame32.lambda$Fn70, frame32.lambda$Fn71);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame32 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn70 = new ModuleMethod(this, 64, (Object) null, 0);
        final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 65, (Object) null, 8194);
        Object rest;
        Object start1;
        frame31 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 64 ? lambda70() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 65 ? lambda71(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 64) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 65) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda70() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda71(Object obj, Object obj2) {
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(obj2, obj));
            try {
                boolean booleanValue = ((Boolean) apply2).booleanValue();
                if (!booleanValue) {
                    return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                }
                boolean z = this.staticLink.s1 == this.staticLink.s2;
                if (z) {
                    Object apply22 = Scheme.numEqu.apply2(this.start1, obj);
                    try {
                        z = ((Boolean) apply22).booleanValue();
                    } catch (ClassCastException e) {
                        throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
                if (z) {
                    return z ? Boolean.TRUE : Boolean.FALSE;
                }
                return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }

        static Boolean lambda72(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda73(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame31 frame312 = new frame31();
        frame312.s1 = obj;
        frame312.s2 = obj2;
        frame312.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame312.lambda$Fn68, frame312.lambda$Fn69);
    }

    /* compiled from: srfi13.scm */
    public class frame33 extends ModuleBody {
        final ModuleMethod lambda$Fn74 = new ModuleMethod(this, 70, (Object) null, 0);
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 71, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 70 ? lambda74() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 71 ? lambda75(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 70) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 71) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda74() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Gr, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda75(Object obj, Object obj2, Object obj3) {
            frame34 frame34 = new frame34();
            frame34.staticLink = this;
            frame34.rest = obj;
            frame34.start1 = obj2;
            frame34.end1 = obj3;
            return call_with_values.callWithValues(frame34.lambda$Fn76, frame34.lambda$Fn77);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame34 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn76 = new ModuleMethod(this, 68, (Object) null, 0);
        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 69, (Object) null, 8194);
        Object rest;
        Object start1;
        frame33 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 68 ? lambda76() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 69 ? lambda77(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 68) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 69) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda76() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda77(Object obj, Object obj2) {
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(obj2, obj));
            try {
                int i = 0;
                int i2 = ((apply2 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i2 != 0) {
                    return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                int i3 = this.staticLink.s1 == this.staticLink.s2 ? 1 : 0;
                if (i3 != 0) {
                    Object apply22 = Scheme.numEqu.apply2(this.start1, obj);
                    try {
                        if (apply22 != Boolean.FALSE) {
                            i = 1;
                        }
                        i3 = i;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
                int i4 = (i3 + 1) & 1;
                if (i4 == 0) {
                    return i4 != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, srfi13.lambda$Fn78, misc.values);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }

        static Boolean lambda78(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$Gr$V(Object obj, Object obj2, Object[] objArr) {
        frame33 frame332 = new frame33();
        frame332.s1 = obj;
        frame332.s2 = obj2;
        frame332.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame332.lambda$Fn74, frame332.lambda$Fn75);
    }

    /* compiled from: srfi13.scm */
    public class frame35 extends ModuleBody {
        final ModuleMethod lambda$Fn79 = new ModuleMethod(this, 74, (Object) null, 0);
        final ModuleMethod lambda$Fn80 = new ModuleMethod(this, 75, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 74 ? lambda79() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 75 ? lambda80(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 74) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 75) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda79() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda80(Object obj, Object obj2, Object obj3) {
            frame36 frame36 = new frame36();
            frame36.staticLink = this;
            frame36.rest = obj;
            frame36.start1 = obj2;
            frame36.end1 = obj3;
            return call_with_values.callWithValues(frame36.lambda$Fn81, frame36.lambda$Fn82);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame36 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn81 = new ModuleMethod(this, 72, (Object) null, 0);
        final ModuleMethod lambda$Fn82 = new ModuleMethod(this, 73, (Object) null, 8194);
        Object rest;
        Object start1;
        frame35 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 72 ? lambda81() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 73 ? lambda82(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 72) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 73) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda81() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda82(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numLss.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, srfi13.lambda$Fn83, srfi13.lambda$Fn84);
        }

        static Boolean lambda83(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda84(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$V(Object obj, Object obj2, Object[] objArr) {
        frame35 frame352 = new frame35();
        frame352.s1 = obj;
        frame352.s2 = obj2;
        frame352.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame352.lambda$Fn79, frame352.lambda$Fn80);
    }

    /* compiled from: srfi13.scm */
    public class frame37 extends ModuleBody {
        final ModuleMethod lambda$Fn85 = new ModuleMethod(this, 78, (Object) null, 0);
        final ModuleMethod lambda$Fn86 = new ModuleMethod(this, 79, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 78 ? lambda85() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 79 ? lambda86(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 78) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 79) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda85() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda86(Object obj, Object obj2, Object obj3) {
            frame38 frame38 = new frame38();
            frame38.staticLink = this;
            frame38.rest = obj;
            frame38.start1 = obj2;
            frame38.end1 = obj3;
            return call_with_values.callWithValues(frame38.lambda$Fn87, frame38.lambda$Fn88);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame38 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn87 = new ModuleMethod(this, 76, (Object) null, 0);
        final ModuleMethod lambda$Fn88 = new ModuleMethod(this, 77, (Object) null, 8194);
        Object rest;
        Object start1;
        frame37 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 76 ? lambda87() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 77 ? lambda88(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 76) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 77) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda87() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda88(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numGrt.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn89, srfi13.lambda$Fn90, misc.values);
        }

        static Boolean lambda89(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda90(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Gr$V(Object obj, Object obj2, Object[] objArr) {
        frame37 frame372 = new frame37();
        frame372.s1 = obj;
        frame372.s2 = obj2;
        frame372.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame372.lambda$Fn85, frame372.lambda$Fn86);
    }

    /* compiled from: srfi13.scm */
    public class frame39 extends ModuleBody {
        final ModuleMethod lambda$Fn91 = new ModuleMethod(this, 82, (Object) null, 0);
        final ModuleMethod lambda$Fn92 = new ModuleMethod(this, 83, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 82 ? lambda91() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 83 ? lambda92(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 82) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 83) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda91() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda92(Object obj, Object obj2, Object obj3) {
            frame40 frame40 = new frame40();
            frame40.staticLink = this;
            frame40.rest = obj;
            frame40.start1 = obj2;
            frame40.end1 = obj3;
            return call_with_values.callWithValues(frame40.lambda$Fn93, frame40.lambda$Fn94);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame40 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn93 = new ModuleMethod(this, 80, (Object) null, 0);
        final ModuleMethod lambda$Fn94 = new ModuleMethod(this, 81, (Object) null, 8194);
        Object rest;
        Object start1;
        frame39 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 80 ? lambda93() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 81 ? lambda94(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 80) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 81) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda93() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda94(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numLEq.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, misc.values, srfi13.lambda$Fn95);
        }

        static Boolean lambda95(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Ls$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame39 frame392 = new frame39();
        frame392.s1 = obj;
        frame392.s2 = obj2;
        frame392.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame392.lambda$Fn91, frame392.lambda$Fn92);
    }

    /* compiled from: srfi13.scm */
    public class frame41 extends ModuleBody {
        final ModuleMethod lambda$Fn96 = new ModuleMethod(this, 86, (Object) null, 0);
        final ModuleMethod lambda$Fn97 = new ModuleMethod(this, 87, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 86 ? lambda96() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 87 ? lambda97(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 86) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 87) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda96() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda97(Object obj, Object obj2, Object obj3) {
            frame42 frame42 = new frame42();
            frame42.staticLink = this;
            frame42.rest = obj;
            frame42.start1 = obj2;
            frame42.end1 = obj3;
            return call_with_values.callWithValues(frame42.lambda$Fn98, frame42.lambda$Fn99);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame42 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn98 = new ModuleMethod(this, 84, (Object) null, 0);
        final ModuleMethod lambda$Fn99 = new ModuleMethod(this, 85, (Object) null, 8194);
        Object rest;
        Object start1;
        frame41 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 84 ? lambda98() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 85 ? lambda99(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 84) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 85) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda98() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda99(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numGEq.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompare(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn100, misc.values, misc.values);
        }

        static Boolean lambda100(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object string$Gr$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame41 frame412 = new frame41();
        frame412.s1 = obj;
        frame412.s2 = obj2;
        frame412.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame412.lambda$Fn96, frame412.lambda$Fn97);
    }

    /* compiled from: srfi13.scm */
    public class frame43 extends ModuleBody {
        final ModuleMethod lambda$Fn101 = new ModuleMethod(this, 90, (Object) null, 0);
        final ModuleMethod lambda$Fn102 = new ModuleMethod(this, 91, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 90 ? lambda101() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 91 ? lambda102(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 90) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 91) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda101() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda102(Object obj, Object obj2, Object obj3) {
            frame44 frame44 = new frame44();
            frame44.staticLink = this;
            frame44.rest = obj;
            frame44.start1 = obj2;
            frame44.end1 = obj3;
            return call_with_values.callWithValues(frame44.lambda$Fn103, frame44.lambda$Fn104);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame44 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn103 = new ModuleMethod(this, 88, (Object) null, 0);
        final ModuleMethod lambda$Fn104 = new ModuleMethod(this, 89, (Object) null, 8194);
        Object rest;
        Object start1;
        frame43 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 88 ? lambda103() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 89 ? lambda104(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 88) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 89) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda103() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda104(Object obj, Object obj2) {
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(obj2, obj));
            try {
                boolean booleanValue = ((Boolean) apply2).booleanValue();
                if (!booleanValue) {
                    return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                }
                boolean z = this.staticLink.s1 == this.staticLink.s2;
                if (z) {
                    Object apply22 = Scheme.numEqu.apply2(this.start1, obj);
                    try {
                        z = ((Boolean) apply22).booleanValue();
                    } catch (ClassCastException e) {
                        throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
                if (z) {
                    return z ? Boolean.TRUE : Boolean.FALSE;
                }
                return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }

        static Boolean lambda105(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda106(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame43 frame432 = new frame43();
        frame432.s1 = obj;
        frame432.s2 = obj2;
        frame432.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame432.lambda$Fn101, frame432.lambda$Fn102);
    }

    /* compiled from: srfi13.scm */
    public class frame45 extends ModuleBody {
        final ModuleMethod lambda$Fn107 = new ModuleMethod(this, 94, (Object) null, 0);
        final ModuleMethod lambda$Fn108 = new ModuleMethod(this, 95, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 94 ? lambda107() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 95 ? lambda108(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 94) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 95) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda107() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda108(Object obj, Object obj2, Object obj3) {
            frame46 frame46 = new frame46();
            frame46.staticLink = this;
            frame46.rest = obj;
            frame46.start1 = obj2;
            frame46.end1 = obj3;
            return call_with_values.callWithValues(frame46.lambda$Fn109, frame46.lambda$Fn110);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame46 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn109 = new ModuleMethod(this, 92, (Object) null, 0);
        final ModuleMethod lambda$Fn110 = new ModuleMethod(this, 93, (Object) null, 8194);
        Object rest;
        Object start1;
        frame45 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 92 ? lambda109() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 93 ? lambda110(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 92) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 93) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda109() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda110(Object obj, Object obj2) {
            Object apply2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(this.end1, this.start1), AddOp.$Mn.apply2(obj2, obj));
            try {
                int i = 0;
                int i2 = ((apply2 != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i2 != 0) {
                    return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                int i3 = this.staticLink.s1 == this.staticLink.s2 ? 1 : 0;
                if (i3 != 0) {
                    Object apply22 = Scheme.numEqu.apply2(this.start1, obj);
                    try {
                        if (apply22 != Boolean.FALSE) {
                            i = 1;
                        }
                        i3 = i;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
                int i4 = (i3 + 1) & 1;
                if (i4 == 0) {
                    return i4 != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, srfi13.lambda$Fn111, misc.values);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }

        static Boolean lambda111(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$Gr$V(Object obj, Object obj2, Object[] objArr) {
        frame45 frame452 = new frame45();
        frame452.s1 = obj;
        frame452.s2 = obj2;
        frame452.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame452.lambda$Fn107, frame452.lambda$Fn108);
    }

    /* compiled from: srfi13.scm */
    public class frame47 extends ModuleBody {
        final ModuleMethod lambda$Fn112 = new ModuleMethod(this, 98, (Object) null, 0);
        final ModuleMethod lambda$Fn113 = new ModuleMethod(this, 99, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 98 ? lambda112() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 99 ? lambda113(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 98) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 99) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda112() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda113(Object obj, Object obj2, Object obj3) {
            frame48 frame48 = new frame48();
            frame48.staticLink = this;
            frame48.rest = obj;
            frame48.start1 = obj2;
            frame48.end1 = obj3;
            return call_with_values.callWithValues(frame48.lambda$Fn114, frame48.lambda$Fn115);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame48 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn114 = new ModuleMethod(this, 96, (Object) null, 0);
        final ModuleMethod lambda$Fn115 = new ModuleMethod(this, 97, (Object) null, 8194);
        Object rest;
        Object start1;
        frame47 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 96 ? lambda114() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 97 ? lambda115(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 96) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 97) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda114() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda115(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numLss.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, srfi13.lambda$Fn116, srfi13.lambda$Fn117);
        }

        static Boolean lambda116(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda117(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$V(Object obj, Object obj2, Object[] objArr) {
        frame47 frame472 = new frame47();
        frame472.s1 = obj;
        frame472.s2 = obj2;
        frame472.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame472.lambda$Fn112, frame472.lambda$Fn113);
    }

    /* compiled from: srfi13.scm */
    public class frame49 extends ModuleBody {
        final ModuleMethod lambda$Fn118 = new ModuleMethod(this, 102, (Object) null, 0);
        final ModuleMethod lambda$Fn119 = new ModuleMethod(this, 103, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 102 ? lambda118() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 103 ? lambda119(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 102) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 103) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda118() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda119(Object obj, Object obj2, Object obj3) {
            frame50 frame50 = new frame50();
            frame50.staticLink = this;
            frame50.rest = obj;
            frame50.start1 = obj2;
            frame50.end1 = obj3;
            return call_with_values.callWithValues(frame50.lambda$Fn120, frame50.lambda$Fn121);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame50 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn120 = new ModuleMethod(this, 100, (Object) null, 0);
        final ModuleMethod lambda$Fn121 = new ModuleMethod(this, 101, (Object) null, 8194);
        Object rest;
        Object start1;
        frame49 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 100 ? lambda120() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 101 ? lambda121(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 100) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 101) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda120() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda121(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numGrt.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn122, srfi13.lambda$Fn123, misc.values);
        }

        static Boolean lambda122(Object obj) {
            return Boolean.FALSE;
        }

        static Boolean lambda123(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Gr$V(Object obj, Object obj2, Object[] objArr) {
        frame49 frame492 = new frame49();
        frame492.s1 = obj;
        frame492.s2 = obj2;
        frame492.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame492.lambda$Fn118, frame492.lambda$Fn119);
    }

    /* compiled from: srfi13.scm */
    public class frame51 extends ModuleBody {
        final ModuleMethod lambda$Fn124 = new ModuleMethod(this, 106, (Object) null, 0);
        final ModuleMethod lambda$Fn125 = new ModuleMethod(this, 107, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 106 ? lambda124() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 107 ? lambda125(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 106) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 107) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda124() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda125(Object obj, Object obj2, Object obj3) {
            frame52 frame52 = new frame52();
            frame52.staticLink = this;
            frame52.rest = obj;
            frame52.start1 = obj2;
            frame52.end1 = obj3;
            return call_with_values.callWithValues(frame52.lambda$Fn126, frame52.lambda$Fn127);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame52 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn126 = new ModuleMethod(this, 104, (Object) null, 0);
        final ModuleMethod lambda$Fn127 = new ModuleMethod(this, 105, (Object) null, 8194);
        Object rest;
        Object start1;
        frame51 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 104 ? lambda126() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 105 ? lambda127(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 104) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 105) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda126() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda127(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numLEq.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, misc.values, misc.values, srfi13.lambda$Fn128);
        }

        static Boolean lambda128(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Ls$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame51 frame512 = new frame51();
        frame512.s1 = obj;
        frame512.s2 = obj2;
        frame512.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame512.lambda$Fn124, frame512.lambda$Fn125);
    }

    /* compiled from: srfi13.scm */
    public class frame53 extends ModuleBody {
        final ModuleMethod lambda$Fn129 = new ModuleMethod(this, 110, (Object) null, 0);
        final ModuleMethod lambda$Fn130 = new ModuleMethod(this, 111, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 110 ? lambda129() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 111 ? lambda130(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 110) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 111) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda129() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.s1, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda130(Object obj, Object obj2, Object obj3) {
            frame54 frame54 = new frame54();
            frame54.staticLink = this;
            frame54.rest = obj;
            frame54.start1 = obj2;
            frame54.end1 = obj3;
            return call_with_values.callWithValues(frame54.lambda$Fn131, frame54.lambda$Fn132);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame54 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn131 = new ModuleMethod(this, 108, (Object) null, 0);
        final ModuleMethod lambda$Fn132 = new ModuleMethod(this, 109, (Object) null, 8194);
        Object rest;
        Object start1;
        frame53 staticLink;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 108 ? lambda131() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 109 ? lambda132(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 108) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 109) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda131() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr$Eq, this.staticLink.s2, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda132(Object obj, Object obj2) {
            boolean z = this.staticLink.s1 == this.staticLink.s2;
            if (!z ? z : Scheme.numEqu.apply2(this.start1, obj) != Boolean.FALSE) {
                return Scheme.numGEq.apply2(this.end1, obj2);
            }
            return srfi13.$PcStringCompareCi(this.staticLink.s1, this.start1, this.end1, this.staticLink.s2, obj, obj2, srfi13.lambda$Fn133, misc.values, misc.values);
        }

        static Boolean lambda133(Object obj) {
            return Boolean.FALSE;
        }
    }

    public static Object stringCi$Gr$Eq$V(Object obj, Object obj2, Object[] objArr) {
        frame53 frame532 = new frame53();
        frame532.s1 = obj;
        frame532.s2 = obj2;
        frame532.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame532.lambda$Fn129, frame532.lambda$Fn130);
    }

    public static Object $PcStringHash(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        frame55 frame552 = new frame55();
        frame552.char$Mn$Grint = obj2;
        Object obj6 = Lit5;
        while (Scheme.numGEq.apply2(obj6, obj3) == Boolean.FALSE) {
            obj6 = AddOp.$Pl.apply2(obj6, obj6);
        }
        Object apply2 = AddOp.$Mn.apply2(obj6, Lit1);
        Object obj7 = Lit0;
        while (Scheme.numGEq.apply2(obj4, obj5) == Boolean.FALSE) {
            Object apply22 = AddOp.$Pl.apply2(obj4, Lit1);
            try {
                try {
                    obj7 = BitwiseOp.and.apply2(apply2, AddOp.$Pl.apply2(MultiplyOp.$St.apply2(Lit6, obj7), Scheme.applyToArgs.apply2(frame552.char$Mn$Grint, Char.make(strings.stringRef((CharSequence) obj, ((Number) obj4).intValue())))));
                    obj4 = apply22;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-ref", 2, obj4);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-ref", 1, obj);
            }
        }
        return DivideOp.modulo.apply2(obj7, obj3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        if (r3 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        if (r3 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        r3 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080 A[SYNTHETIC, Splitter:B:40:0x0080] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringHash$V(java.lang.Object r11, java.lang.Object[] r12) {
        /*
            java.lang.String r0 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            gnu.kawa.slib.srfi13$frame56 r1 = new gnu.kawa.slib.srfi13$frame56
            r1.<init>()
            r1.s = r11
            r11 = 0
            gnu.lists.LList r11 = gnu.lists.LList.makeList(r12, r11)
            gnu.kawa.functions.ApplyToArgs r12 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r2 = loc$let$Mnoptionals$St
            r3 = 907(0x38b, float:1.271E-42)
            java.lang.Object r2 = r2.get()     // Catch:{ UnboundLocationException -> 0x00c1 }
            gnu.kawa.functions.ApplyToArgs r4 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r6 = loc$bound
            java.lang.Object r7 = r6.get()     // Catch:{ UnboundLocationException -> 0x00ba }
            gnu.math.IntNum r8 = Lit7
            java.lang.Object r3 = r6.get()     // Catch:{ UnboundLocationException -> 0x00b3 }
            boolean r3 = kawa.lib.numbers.isInteger(r3)
            if (r3 == 0) goto L_0x005a
            java.lang.Object r3 = r6.get()     // Catch:{ UnboundLocationException -> 0x0051 }
            boolean r3 = kawa.lib.numbers.isExact(r3)
            if (r3 == 0) goto L_0x004e
            gnu.kawa.functions.NumberCompare r3 = kawa.standard.Scheme.numLEq
            gnu.math.IntNum r9 = Lit0
            java.lang.Object r10 = r6.get()     // Catch:{ UnboundLocationException -> 0x0045 }
            java.lang.Object r3 = r3.apply2(r9, r10)
            goto L_0x0061
        L_0x0045:
            r11 = move-exception
            r12 = 909(0x38d, float:1.274E-42)
            r1 = 19
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x004e:
            if (r3 == 0) goto L_0x005f
            goto L_0x005c
        L_0x0051:
            r11 = move-exception
            r12 = 908(0x38c, float:1.272E-42)
            r1 = 21
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x005a:
            if (r3 == 0) goto L_0x005f
        L_0x005c:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0061
        L_0x005f:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
        L_0x0061:
            java.lang.Object r3 = r5.apply3(r7, r8, r3)
            gnu.mapping.Location r5 = loc$rest
            java.lang.Object r5 = r5.get()     // Catch:{ UnboundLocationException -> 0x00ab }
            java.lang.Object r3 = r4.apply2(r3, r5)
            r4 = 911(0x38f, float:1.277E-42)
            java.lang.Object r5 = r6.get()     // Catch:{ UnboundLocationException -> 0x00a4 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x009a }
            boolean r5 = kawa.lib.numbers.isZero(r5)
            if (r5 == 0) goto L_0x0080
            gnu.math.IntNum r0 = Lit8
            goto L_0x0084
        L_0x0080:
            java.lang.Object r0 = r6.get()     // Catch:{ UnboundLocationException -> 0x0093 }
        L_0x0084:
            r1.bound = r0
            gnu.expr.ModuleMethod r0 = r1.lambda$Fn134
            gnu.expr.ModuleMethod r1 = r1.lambda$Fn135
            java.lang.Object r0 = kawa.standard.call_with_values.callWithValues(r0, r1)
            java.lang.Object r11 = r12.apply4(r2, r11, r3, r0)
            return r11
        L_0x0093:
            r11 = move-exception
            r12 = 18
            r11.setLine(r0, r4, r12)
            throw r11
        L_0x009a:
            r11 = move-exception
            gnu.mapping.WrongType r12 = new gnu.mapping.WrongType
            r0 = 1
            java.lang.String r1 = "zero?"
            r12.<init>((java.lang.ClassCastException) r11, (java.lang.String) r1, (int) r0, (java.lang.Object) r5)
            throw r12
        L_0x00a4:
            r11 = move-exception
            r12 = 29
            r11.setLine(r0, r4, r12)
            throw r11
        L_0x00ab:
            r11 = move-exception
            r12 = 910(0x38e, float:1.275E-42)
            r1 = 7
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x00b3:
            r11 = move-exception
            r12 = 72
            r11.setLine(r0, r3, r12)
            throw r11
        L_0x00ba:
            r11 = move-exception
            r12 = 42
            r11.setLine(r0, r3, r12)
            throw r11
        L_0x00c1:
            r11 = move-exception
            r12 = 3
            r11.setLine(r0, r3, r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringHash$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* compiled from: srfi13.scm */
    public class frame56 extends ModuleBody {
        Object bound;
        final ModuleMethod lambda$Fn134 = new ModuleMethod(this, 112, (Object) null, 0);
        final ModuleMethod lambda$Fn135 = new ModuleMethod(this, 113, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 112 ? lambda134() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 113 ? lambda135(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 112) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 113) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda134() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnhash, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 912, 55);
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda135(Object obj, Object obj2) {
            return srfi13.$PcStringHash(this.s, characters.char$Mn$Grinteger, this.bound, obj, obj2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        if (r3 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005a, code lost:
        if (r3 != false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005f, code lost:
        r3 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0080 A[SYNTHETIC, Splitter:B:40:0x0080] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringHashCi$V(java.lang.Object r11, java.lang.Object[] r12) {
        /*
            java.lang.String r0 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            gnu.kawa.slib.srfi13$frame57 r1 = new gnu.kawa.slib.srfi13$frame57
            r1.<init>()
            r1.s = r11
            r11 = 0
            gnu.lists.LList r11 = gnu.lists.LList.makeList(r12, r11)
            gnu.kawa.functions.ApplyToArgs r12 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r2 = loc$let$Mnoptionals$St
            r3 = 916(0x394, float:1.284E-42)
            java.lang.Object r2 = r2.get()     // Catch:{ UnboundLocationException -> 0x00c1 }
            gnu.kawa.functions.ApplyToArgs r4 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r6 = loc$bound
            java.lang.Object r7 = r6.get()     // Catch:{ UnboundLocationException -> 0x00ba }
            gnu.math.IntNum r8 = Lit9
            java.lang.Object r3 = r6.get()     // Catch:{ UnboundLocationException -> 0x00b3 }
            boolean r3 = kawa.lib.numbers.isInteger(r3)
            if (r3 == 0) goto L_0x005a
            java.lang.Object r3 = r6.get()     // Catch:{ UnboundLocationException -> 0x0051 }
            boolean r3 = kawa.lib.numbers.isExact(r3)
            if (r3 == 0) goto L_0x004e
            gnu.kawa.functions.NumberCompare r3 = kawa.standard.Scheme.numLEq
            gnu.math.IntNum r9 = Lit0
            java.lang.Object r10 = r6.get()     // Catch:{ UnboundLocationException -> 0x0045 }
            java.lang.Object r3 = r3.apply2(r9, r10)
            goto L_0x0061
        L_0x0045:
            r11 = move-exception
            r12 = 918(0x396, float:1.286E-42)
            r1 = 19
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x004e:
            if (r3 == 0) goto L_0x005f
            goto L_0x005c
        L_0x0051:
            r11 = move-exception
            r12 = 917(0x395, float:1.285E-42)
            r1 = 21
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x005a:
            if (r3 == 0) goto L_0x005f
        L_0x005c:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x0061
        L_0x005f:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
        L_0x0061:
            java.lang.Object r3 = r5.apply3(r7, r8, r3)
            gnu.mapping.Location r5 = loc$rest
            java.lang.Object r5 = r5.get()     // Catch:{ UnboundLocationException -> 0x00ab }
            java.lang.Object r3 = r4.apply2(r3, r5)
            r4 = 920(0x398, float:1.289E-42)
            java.lang.Object r5 = r6.get()     // Catch:{ UnboundLocationException -> 0x00a4 }
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ ClassCastException -> 0x009a }
            boolean r5 = kawa.lib.numbers.isZero(r5)
            if (r5 == 0) goto L_0x0080
            gnu.math.IntNum r0 = Lit10
            goto L_0x0084
        L_0x0080:
            java.lang.Object r0 = r6.get()     // Catch:{ UnboundLocationException -> 0x0093 }
        L_0x0084:
            r1.bound = r0
            gnu.expr.ModuleMethod r0 = r1.lambda$Fn136
            gnu.expr.ModuleMethod r1 = r1.lambda$Fn137
            java.lang.Object r0 = kawa.standard.call_with_values.callWithValues(r0, r1)
            java.lang.Object r11 = r12.apply4(r2, r11, r3, r0)
            return r11
        L_0x0093:
            r11 = move-exception
            r12 = 18
            r11.setLine(r0, r4, r12)
            throw r11
        L_0x009a:
            r11 = move-exception
            gnu.mapping.WrongType r12 = new gnu.mapping.WrongType
            r0 = 1
            java.lang.String r1 = "zero?"
            r12.<init>((java.lang.ClassCastException) r11, (java.lang.String) r1, (int) r0, (java.lang.Object) r5)
            throw r12
        L_0x00a4:
            r11 = move-exception
            r12 = 29
            r11.setLine(r0, r4, r12)
            throw r11
        L_0x00ab:
            r11 = move-exception
            r12 = 919(0x397, float:1.288E-42)
            r1 = 7
            r11.setLine(r0, r12, r1)
            throw r11
        L_0x00b3:
            r11 = move-exception
            r12 = 72
            r11.setLine(r0, r3, r12)
            throw r11
        L_0x00ba:
            r11 = move-exception
            r12 = 42
            r11.setLine(r0, r3, r12)
            throw r11
        L_0x00c1:
            r11 = move-exception
            r12 = 3
            r11.setLine(r0, r3, r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringHashCi$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* compiled from: srfi13.scm */
    public class frame57 extends ModuleBody {
        Object bound;
        final ModuleMethod lambda$Fn136 = new ModuleMethod(this, 114, (Object) null, 0);
        final ModuleMethod lambda$Fn137 = new ModuleMethod(this, 115, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 114 ? lambda136() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 115 ? lambda137(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 114) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 115) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda136() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnhash$Mnci, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 921, 58);
                throw e;
            }
        }

        static int lambda138(Object obj) {
            try {
                return characters.char$To$Integer(unicode.charDowncase((Char) obj));
            } catch (ClassCastException e) {
                throw new WrongType(e, "char-downcase", 1, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda137(Object obj, Object obj2) {
            return srfi13.$PcStringHash(this.s, srfi13.lambda$Fn138, this.bound, obj, obj2);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame58 extends ModuleBody {
        final ModuleMethod lambda$Fn139 = new ModuleMethod(this, 116, (Object) null, 0);
        final ModuleMethod lambda$Fn140 = new ModuleMethod(this, 117, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 116 ? lambda139() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 117 ? lambda140(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 116) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 117) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda139() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda140(Object obj, Object obj2) {
            return srfi13.$PcStringMap(unicode.char$Mnupcase, this.s, obj, obj2);
        }
    }

    public static Object stringUpcase$V(Object obj, Object[] objArr) {
        frame58 frame582 = new frame58();
        frame582.s = obj;
        frame582.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame582.lambda$Fn139, frame582.lambda$Fn140);
    }

    /* compiled from: srfi13.scm */
    public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn141 = new ModuleMethod(this, 118, (Object) null, 0);
        final ModuleMethod lambda$Fn142 = new ModuleMethod(this, 119, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 118 ? lambda141() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 119 ? lambda142(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 118) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 119) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda141() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda142(Object obj, Object obj2) {
            return srfi13.$PcStringMap$Ex(unicode.char$Mnupcase, this.s, obj, obj2);
        }
    }

    public static Object stringUpcase$Ex$V(Object obj, Object[] objArr) {
        frame59 frame592 = new frame59();
        frame592.s = obj;
        frame592.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame592.lambda$Fn141, frame592.lambda$Fn142);
    }

    /* compiled from: srfi13.scm */
    public class frame60 extends ModuleBody {
        final ModuleMethod lambda$Fn143 = new ModuleMethod(this, 120, (Object) null, 0);
        final ModuleMethod lambda$Fn144 = new ModuleMethod(this, 121, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 120 ? lambda143() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 121 ? lambda144(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 120) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 121) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda143() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda144(Object obj, Object obj2) {
            return srfi13.$PcStringMap(unicode.char$Mndowncase, this.s, obj, obj2);
        }
    }

    public static Object stringDowncase$V(Object obj, Object[] objArr) {
        frame60 frame602 = new frame60();
        frame602.s = obj;
        frame602.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame602.lambda$Fn143, frame602.lambda$Fn144);
    }

    /* compiled from: srfi13.scm */
    public class frame61 extends ModuleBody {
        final ModuleMethod lambda$Fn145 = new ModuleMethod(this, 122, (Object) null, 0);
        final ModuleMethod lambda$Fn146 = new ModuleMethod(this, 123, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 122 ? lambda145() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 123 ? lambda146(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 122) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 123) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda145() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda146(Object obj, Object obj2) {
            return srfi13.$PcStringMap$Ex(unicode.char$Mndowncase, this.s, obj, obj2);
        }
    }

    public static Object stringDowncase$Ex$V(Object obj, Object[] objArr) {
        frame61 frame612 = new frame61();
        frame612.s = obj;
        frame612.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame612.lambda$Fn145, frame612.lambda$Fn146);
    }

    public static Object $PcStringTitlecase$Ex(Object obj, Object obj2, Object obj3) {
        while (true) {
            Location location = loc$char$Mncased$Qu;
            try {
                Object stringIndex$V = stringIndex$V(obj, location.get(), new Object[]{obj2, obj3});
                if (stringIndex$V == Boolean.FALSE) {
                    return Values.empty;
                }
                try {
                    CharSeq charSeq = (CharSeq) obj;
                    try {
                        int intValue = ((Number) stringIndex$V).intValue();
                        try {
                            try {
                                Char charTitlecase = unicode.charTitlecase(Char.make(strings.stringRef((CharSequence) obj, ((Number) stringIndex$V).intValue())));
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, charTitlecase.charValue());
                                    AddOp addOp = AddOp.$Pl;
                                    IntNum intNum = Lit1;
                                    Object apply2 = addOp.apply2(stringIndex$V, intNum);
                                    try {
                                        Object stringSkip$V = stringSkip$V(obj, location.get(), new Object[]{apply2, obj3});
                                        if (stringSkip$V != Boolean.FALSE) {
                                            stringDowncase$Ex$V(obj, new Object[]{apply2, stringSkip$V});
                                            obj2 = AddOp.$Pl.apply2(stringSkip$V, intNum);
                                        } else {
                                            return stringDowncase$Ex$V(obj, new Object[]{apply2, obj3});
                                        }
                                    } catch (UnboundLocationException e) {
                                        e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 959, 31);
                                        throw e;
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "string-set!", 3, (Object) charTitlecase);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-ref", 2, stringIndex$V);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-ref", 1, obj);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "string-set!", 2, stringIndex$V);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "string-set!", 1, obj);
                }
            } catch (UnboundLocationException e7) {
                e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 955, 28);
                throw e7;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame62 extends ModuleBody {
        final ModuleMethod lambda$Fn147 = new ModuleMethod(this, 124, (Object) null, 0);
        final ModuleMethod lambda$Fn148 = new ModuleMethod(this, ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 124 ? lambda147() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 125 ? lambda148(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 124) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 125) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda147() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda148(Object obj, Object obj2) {
            return srfi13.$PcStringTitlecase$Ex(this.s, obj, obj2);
        }
    }

    public static Object stringTitlecase$Ex$V(Object obj, Object[] objArr) {
        frame62 frame622 = new frame62();
        frame622.s = obj;
        frame622.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame622.lambda$Fn147, frame622.lambda$Fn148);
    }

    /* compiled from: srfi13.scm */
    public class frame63 extends ModuleBody {
        final ModuleMethod lambda$Fn149 = new ModuleMethod(this, 126, (Object) null, 0);
        final ModuleMethod lambda$Fn150 = new ModuleMethod(this, 127, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 126 ? lambda149() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 127 ? lambda150(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 126) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 127) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda149() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda150(Object obj, Object obj2) {
            Object obj3 = this.s;
            try {
                try {
                    try {
                        CharSequence substring = strings.substring((CharSequence) obj3, ((Number) obj).intValue(), ((Number) obj2).intValue());
                        srfi13.$PcStringTitlecase$Ex(substring, srfi13.Lit0, AddOp.$Mn.apply2(obj2, obj));
                        return substring;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, obj2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "substring", 2, obj);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "substring", 1, obj3);
            }
        }
    }

    public static Object stringTitlecase$V(Object obj, Object[] objArr) {
        frame63 frame632 = new frame63();
        frame632.s = obj;
        frame632.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame632.lambda$Fn149, frame632.lambda$Fn150);
    }

    public static Object stringTake(Object obj, Object obj2) {
        frame64 frame642 = new frame64();
        frame642.s = obj;
        frame642.n = obj2;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj3 = location.get();
            ModuleMethod moduleMethod = strings.string$Qu;
            Object obj4 = frame642.s;
            ModuleMethod moduleMethod2 = string$Mntake;
            applyToArgs.apply4(obj3, moduleMethod, obj4, moduleMethod2);
            try {
                Scheme.applyToArgs.apply4(location.get(), frame642.lambda$Fn151, frame642.n, moduleMethod2);
                Object obj5 = frame642.s;
                try {
                    CharSequence charSequence = (CharSequence) obj5;
                    Object obj6 = frame642.n;
                    try {
                        return $PcSubstring$SlShared(charSequence, 0, ((Number) obj6).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "%substring/shared", 2, obj6);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%substring/shared", 0, obj5);
                }
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 996, 3);
                throw e3;
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 995, 3);
            throw e4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame64 extends ModuleBody {
        final ModuleMethod lambda$Fn151;
        Object n;
        Object s;

        public frame64() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 128, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:996");
            this.lambda$Fn151 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 128) {
                return lambda151(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda151(Object obj) {
            boolean isInteger = numbers.isInteger(this.n);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(this.n);
            if (!isExact) {
                return isExact;
            }
            NumberCompare numberCompare = Scheme.numLEq;
            IntNum intNum = srfi13.Lit0;
            Object obj2 = this.n;
            Object obj3 = this.s;
            try {
                return ((Boolean) numberCompare.apply3(intNum, obj2, Integer.valueOf(strings.stringLength((CharSequence) obj3)))).booleanValue();
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, obj3);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 128) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object stringTakeRight(Object obj, Object obj2) {
        frame65 frame652 = new frame65();
        frame652.n = obj2;
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        Location location = loc$check$Mnarg;
        try {
            Object obj3 = location.get();
            ModuleMethod moduleMethod = strings.string$Qu;
            ModuleMethod moduleMethod2 = string$Mntake$Mnright;
            applyToArgs.apply4(obj3, moduleMethod, obj, moduleMethod2);
            try {
                frame652.len = strings.stringLength((CharSequence) obj);
                try {
                    Scheme.applyToArgs.apply4(location.get(), frame652.lambda$Fn152, frame652.n, moduleMethod2);
                    try {
                        CharSequence charSequence = (CharSequence) obj;
                        Object apply2 = AddOp.$Mn.apply2(Integer.valueOf(frame652.len), frame652.n);
                        try {
                            return $PcSubstring$SlShared(charSequence, ((Number) apply2).intValue(), frame652.len);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%substring/shared", 1, apply2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%substring/shared", 0, obj);
                    }
                } catch (UnboundLocationException e3) {
                    e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1004, 5);
                    throw e3;
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "string-length", 1, obj);
            }
        } catch (UnboundLocationException e5) {
            e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1002, 3);
            throw e5;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn152;
        int len;
        Object n;

        public frame65() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 129, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1004");
            this.lambda$Fn152 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 129) {
                return lambda152(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda152(Object obj) {
            boolean isInteger = numbers.isInteger(this.n);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(this.n);
            return isExact ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue() : isExact;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 129) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object stringDrop(CharSequence charSequence, Object obj) {
        frame66 frame662 = new frame66();
        frame662.n = obj;
        frame662.len = strings.stringLength(charSequence);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame662.lambda$Fn153, frame662.n, string$Mndrop);
            Object obj2 = frame662.n;
            try {
                return $PcSubstring$SlShared(charSequence, ((Number) obj2).intValue(), frame662.len);
            } catch (ClassCastException e) {
                throw new WrongType(e, "%substring/shared", 1, obj2);
            }
        } catch (UnboundLocationException e2) {
            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", PointerIconCompat.TYPE_ALIAS, 5);
            throw e2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame66 extends ModuleBody {
        final ModuleMethod lambda$Fn153;
        int len;
        Object n;

        public frame66() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 130, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1010");
            this.lambda$Fn153 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 130) {
                return lambda153(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda153(Object obj) {
            boolean isInteger = numbers.isInteger(this.n);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(this.n);
            return isExact ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue() : isExact;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 130) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object stringDropRight(CharSequence charSequence, Object obj) {
        frame67 frame672 = new frame67();
        frame672.n = obj;
        frame672.len = strings.stringLength(charSequence);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), frame672.lambda$Fn154, frame672.n, string$Mndrop$Mnright);
            Object apply2 = AddOp.$Mn.apply2(Integer.valueOf(frame672.len), frame672.n);
            try {
                return $PcSubstring$SlShared(charSequence, 0, ((Number) apply2).intValue());
            } catch (ClassCastException e) {
                throw new WrongType(e, "%substring/shared", 2, apply2);
            }
        } catch (UnboundLocationException e2) {
            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, 5);
            throw e2;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i != 218) {
            switch (i) {
                case 280:
                    return stringTake(obj, obj2);
                case 281:
                    return stringTakeRight(obj, obj2);
                case 282:
                    try {
                        return stringDrop((CharSequence) obj, obj2);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-drop", 1, obj);
                    }
                case 283:
                    try {
                        return stringDropRight((CharSequence) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-drop-right", 1, obj);
                    }
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        } else {
            try {
                return stringTabulate(obj, ((Number) obj2).intValue());
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-tabulate", 2, obj2);
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame67 extends ModuleBody {
        final ModuleMethod lambda$Fn154;
        int len;
        Object n;

        public frame67() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 131, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1016");
            this.lambda$Fn154 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 131) {
                return lambda154(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda154(Object obj) {
            boolean isInteger = numbers.isInteger(this.n);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(this.n);
            return isExact ? ((Boolean) Scheme.numLEq.apply3(srfi13.Lit0, this.n, Integer.valueOf(this.len))).booleanValue() : isExact;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 131) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object stringTrim$V(Object obj, Object[] objArr) {
        frame68 frame682 = new frame68();
        frame682.s = obj;
        LList makeList = LList.makeList(objArr, 0);
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), makeList, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame682.lambda$Fn155, frame682.lambda$Fn156));
                    } catch (UnboundLocationException e) {
                        e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 72);
                        throw e;
                    }
                } catch (UnboundLocationException e2) {
                    e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 51);
                    throw e2;
                }
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 40);
                throw e3;
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1022, 3);
            throw e4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame68 extends ModuleBody {
        final ModuleMethod lambda$Fn155 = new ModuleMethod(this, 132, (Object) null, 0);
        final ModuleMethod lambda$Fn156 = new ModuleMethod(this, 133, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 132 ? lambda155() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 133 ? lambda156(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 132) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 133) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda155() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1023, 53);
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda156(Object obj, Object obj2) {
            try {
                Object stringSkip$V = srfi13.stringSkip$V(this.s, srfi13.loc$criterion.get(), new Object[]{obj, obj2});
                if (stringSkip$V == Boolean.FALSE) {
                    return "";
                }
                Object obj3 = this.s;
                try {
                    try {
                        try {
                            return srfi13.$PcSubstring$SlShared((CharSequence) obj3, ((Number) stringSkip$V).intValue(), ((Number) obj2).intValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "%substring/shared", 2, obj2);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "%substring/shared", 1, stringSkip$V);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "%substring/shared", 0, obj3);
                }
            } catch (UnboundLocationException e4) {
                e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1024, 29);
                throw e4;
            }
        }
    }

    public static Object stringTrimRight$V(Object obj, Object[] objArr) {
        frame69 frame692 = new frame69();
        frame692.s = obj;
        LList makeList = LList.makeList(objArr, 0);
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), makeList, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame692.lambda$Fn157, frame692.lambda$Fn158));
                    } catch (UnboundLocationException e) {
                        e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 72);
                        throw e;
                    }
                } catch (UnboundLocationException e2) {
                    e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 51);
                    throw e2;
                }
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 40);
                throw e3;
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1029, 3);
            throw e4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn157 = new ModuleMethod(this, 134, (Object) null, 0);
        final ModuleMethod lambda$Fn158 = new ModuleMethod(this, 135, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 134 ? lambda157() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 135 ? lambda158(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 134) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 135) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda157() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim$Mnright, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1030, 59);
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda158(Object obj, Object obj2) {
            try {
                Object stringSkipRight$V = srfi13.stringSkipRight$V(this.s, srfi13.loc$criterion.get(), new Object[]{obj, obj2});
                if (stringSkipRight$V == Boolean.FALSE) {
                    return "";
                }
                Object obj3 = this.s;
                try {
                    CharSequence charSequence = (CharSequence) obj3;
                    Object apply2 = AddOp.$Pl.apply2(srfi13.Lit1, stringSkipRight$V);
                    try {
                        return srfi13.$PcSubstring$SlShared(charSequence, 0, ((Number) apply2).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "%substring/shared", 2, apply2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%substring/shared", 0, obj3);
                }
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1031, 35);
                throw e3;
            }
        }
    }

    public static Object stringTrimBoth$V(Object obj, Object[] objArr) {
        frame70 frame702 = new frame70();
        frame702.s = obj;
        LList makeList = LList.makeList(objArr, 0);
        try {
            try {
                try {
                    try {
                        return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), makeList, Scheme.applyToArgs.apply2(Scheme.applyToArgs.apply2(loc$criterion.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit11)), loc$rest.get()), call_with_values.callWithValues(frame702.lambda$Fn159, frame702.lambda$Fn160));
                    } catch (UnboundLocationException e) {
                        e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 72);
                        throw e;
                    }
                } catch (UnboundLocationException e2) {
                    e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 51);
                    throw e2;
                }
            } catch (UnboundLocationException e3) {
                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 40);
                throw e3;
            }
        } catch (UnboundLocationException e4) {
            e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1036, 3);
            throw e4;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame70 extends ModuleBody {
        final ModuleMethod lambda$Fn159 = new ModuleMethod(this, 136, (Object) null, 0);
        final ModuleMethod lambda$Fn160 = new ModuleMethod(this, 137, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 136 ? lambda159() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 137 ? lambda160(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 136) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 137) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda159() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntrim$Mnboth, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1037, 58);
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda160(Object obj, Object obj2) {
            try {
                Object stringSkip$V = srfi13.stringSkip$V(this.s, srfi13.loc$criterion.get(), new Object[]{obj, obj2});
                if (stringSkip$V == Boolean.FALSE) {
                    return "";
                }
                Object obj3 = this.s;
                try {
                    CharSequence charSequence = (CharSequence) obj3;
                    try {
                        int intValue = ((Number) stringSkip$V).intValue();
                        try {
                            Object apply2 = AddOp.$Pl.apply2(srfi13.Lit1, srfi13.stringSkipRight$V(this.s, srfi13.loc$criterion.get(), new Object[]{stringSkip$V, obj2}));
                            try {
                                return srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) apply2).intValue());
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "%substring/shared", 2, apply2);
                            }
                        } catch (UnboundLocationException e2) {
                            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1040, 58);
                            throw e2;
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "%substring/shared", 1, stringSkip$V);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "%substring/shared", 0, obj3);
                }
            } catch (UnboundLocationException e5) {
                e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1038, 29);
                throw e5;
            }
        }
    }

    public static Object stringPadRight$V(Object obj, Object obj2, Object[] objArr) {
        frame71 frame712 = new frame71();
        frame712.s = obj;
        frame712.n = obj2;
        try {
            try {
                return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), LList.makeList(objArr, 0), Scheme.applyToArgs.apply2(Invoke.make.apply3(LangPrimType.charType, Lit12, characters.isChar(LangPrimType.charType) ? Boolean.TRUE : Boolean.FALSE), loc$rest.get()), call_with_values.callWithValues(frame712.lambda$Fn161, frame712.lambda$Fn162));
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 63);
                throw e;
            }
        } catch (UnboundLocationException e2) {
            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1045, 3);
            throw e2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame71 extends ModuleBody {
        final ModuleMethod lambda$Fn161 = new ModuleMethod(this, 138, (Object) null, 0);
        final ModuleMethod lambda$Fn162 = new ModuleMethod(this, 139, (Object) null, 8194);
        Object n;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 138 ? lambda161() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 139 ? lambda162(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 138) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 139) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda161() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnpad$Mnright, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1046, 58);
                throw e;
            }
        }

        static boolean lambda163(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            return isExact ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue() : isExact;
        }

        /* access modifiers changed from: package-private */
        public Object lambda162(Object obj, Object obj2) {
            try {
                Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn163, this.n, srfi13.string$Mnpad$Mnright);
                if (Scheme.numLEq.apply2(this.n, AddOp.$Mn.apply2(obj2, obj)) != Boolean.FALSE) {
                    Object obj3 = this.s;
                    try {
                        CharSequence charSequence = (CharSequence) obj3;
                        try {
                            int intValue = ((Number) obj).intValue();
                            Object apply2 = AddOp.$Pl.apply2(obj, this.n);
                            try {
                                return srfi13.$PcSubstring$SlShared(charSequence, intValue, ((Number) apply2).intValue());
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "%substring/shared", 2, apply2);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "%substring/shared", 1, obj);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "%substring/shared", 0, obj3);
                    }
                } else {
                    Object obj4 = this.n;
                    try {
                        CharSequence makeString = strings.makeString(((Number) obj4).intValue(), LangPrimType.charType);
                        Object obj5 = this.s;
                        try {
                            try {
                                try {
                                    srfi13.$PcStringCopy$Ex(makeString, 0, (CharSequence) obj5, ((Number) obj).intValue(), ((Number) obj2).intValue());
                                    return makeString;
                                } catch (ClassCastException e4) {
                                    throw new WrongType(e4, "%string-copy!", 4, obj2);
                                }
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "%string-copy!", 3, obj);
                            }
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "%string-copy!", 2, obj5);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "make-string", 1, obj4);
                    }
                }
            } catch (UnboundLocationException e8) {
                e8.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1047, 7);
                throw e8;
            }
        }
    }

    public static Object stringPad$V(Object obj, Object obj2, Object[] objArr) {
        frame72 frame722 = new frame72();
        frame722.s = obj;
        frame722.n = obj2;
        try {
            try {
                return Scheme.applyToArgs.apply4(loc$let$Mnoptionals$St.get(), LList.makeList(objArr, 0), Scheme.applyToArgs.apply2(Invoke.make.apply3(LangPrimType.charType, Lit12, characters.isChar(LangPrimType.charType) ? Boolean.TRUE : Boolean.FALSE), loc$rest.get()), call_with_values.callWithValues(frame722.lambda$Fn164, frame722.lambda$Fn165));
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 63);
                throw e;
            }
        } catch (UnboundLocationException e2) {
            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1057, 3);
            throw e2;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame72 extends ModuleBody {
        final ModuleMethod lambda$Fn164 = new ModuleMethod(this, 140, (Object) null, 0);
        final ModuleMethod lambda$Fn165 = new ModuleMethod(this, 141, (Object) null, 8194);
        Object n;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 140 ? lambda164() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 141 ? lambda165(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 140) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 141) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda164() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnpad, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1058, 52);
                throw e;
            }
        }

        static boolean lambda166(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            return isExact ? ((Boolean) Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue() : isExact;
        }

        /* access modifiers changed from: package-private */
        public Object lambda165(Object obj, Object obj2) {
            try {
                Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn166, this.n, srfi13.string$Mnpad);
                Object apply2 = AddOp.$Mn.apply2(obj2, obj);
                if (Scheme.numLEq.apply2(this.n, apply2) != Boolean.FALSE) {
                    Object obj3 = this.s;
                    try {
                        CharSequence charSequence = (CharSequence) obj3;
                        Object apply22 = AddOp.$Mn.apply2(obj2, this.n);
                        try {
                            try {
                                return srfi13.$PcSubstring$SlShared(charSequence, ((Number) apply22).intValue(), ((Number) obj2).intValue());
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "%substring/shared", 2, obj2);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "%substring/shared", 1, apply22);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "%substring/shared", 0, obj3);
                    }
                } else {
                    Object obj4 = this.n;
                    try {
                        CharSequence makeString = strings.makeString(((Number) obj4).intValue(), LangPrimType.charType);
                        Object apply23 = AddOp.$Mn.apply2(this.n, apply2);
                        try {
                            int intValue = ((Number) apply23).intValue();
                            Object obj5 = this.s;
                            try {
                                try {
                                    try {
                                        srfi13.$PcStringCopy$Ex(makeString, intValue, (CharSequence) obj5, ((Number) obj).intValue(), ((Number) obj2).intValue());
                                        return makeString;
                                    } catch (ClassCastException e4) {
                                        throw new WrongType(e4, "%string-copy!", 4, obj2);
                                    }
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "%string-copy!", 3, obj);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "%string-copy!", 2, obj5);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "%string-copy!", 1, apply23);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "make-string", 1, obj4);
                    }
                }
            } catch (UnboundLocationException e9) {
                e9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1059, 7);
                throw e9;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame73 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn167 = new ModuleMethod(this, 145, (Object) null, 0);
        final ModuleMethod lambda$Fn168 = new ModuleMethod(this, 146, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 145 ? lambda167() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 146 ? lambda168(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 145) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 146) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda167() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndelete, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda168(Object obj, Object obj2) {
            Object obj3;
            frame74 frame74 = new frame74();
            frame74.staticLink = this;
            if (misc.isProcedure(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(obj2, obj);
                try {
                    frame74.temp = strings.makeString(((Number) apply2).intValue());
                    Object stringFold$V = srfi13.stringFold$V(frame74.lambda$Fn169, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                    if (Scheme.numEqu.apply2(stringFold$V, apply2) != Boolean.FALSE) {
                        return frame74.temp;
                    }
                    try {
                        return strings.substring(frame74.temp, 0, ((Number) stringFold$V).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, stringFold$V);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-string", 1, apply2);
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        obj3 = this.criterion;
                    } else if (characters.isChar(this.criterion)) {
                        try {
                            obj3 = Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset.get(), this.criterion);
                        } catch (UnboundLocationException e3) {
                            e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1097, 26);
                            throw e3;
                        }
                    } else {
                        obj3 = misc.error$V("string-delete criterion not predicate, char or char-set", new Object[]{this.criterion});
                    }
                    frame74.cset = obj3;
                    Object stringFold$V2 = srfi13.stringFold$V(frame74.lambda$Fn170, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                    try {
                        frame74.ans = strings.makeString(((Number) stringFold$V2).intValue());
                        srfi13.stringFold$V(frame74.lambda$Fn171, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                        return frame74.ans;
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "make-string", 1, stringFold$V2);
                    }
                } catch (UnboundLocationException e5) {
                    e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1096, 22);
                    throw e5;
                }
            }
        }
    }

    public static Object stringDelete$V(Object obj, Object obj2, Object[] objArr) {
        frame73 frame732 = new frame73();
        frame732.criterion = obj;
        frame732.s = obj2;
        frame732.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame732.lambda$Fn167, frame732.lambda$Fn168);
    }

    /* compiled from: srfi13.scm */
    public class frame74 extends ModuleBody {
        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn169;
        final ModuleMethod lambda$Fn170;
        final ModuleMethod lambda$Fn171;
        frame73 staticLink;
        CharSequence temp;

        public frame74() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 142, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1089");
            this.lambda$Fn169 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 143, (Object) null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1099");
            this.lambda$Fn170 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 144, (Object) null, 8194);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1104");
            this.lambda$Fn171 = moduleMethod3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 142:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 143:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 144:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda169(Object obj, Object obj2) {
            if (Scheme.applyToArgs.apply2(this.staticLink.criterion, obj) != Boolean.FALSE) {
                return obj2;
            }
            CharSequence charSequence = this.temp;
            try {
                try {
                    try {
                        strings.stringSet$Ex((CharSeq) charSequence, ((Number) obj2).intValue(), ((Char) obj).charValue());
                        return AddOp.$Pl.apply2(obj2, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-set!", 3, obj);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-set!", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-set!", 1, (Object) charSequence);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda170(Object obj, Object obj2) {
            try {
                return Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, obj) != Boolean.FALSE ? obj2 : AddOp.$Pl.apply2(obj2, srfi13.Lit1);
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1099, 45);
                throw e;
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 142:
                    return lambda169(obj, obj2);
                case 143:
                    return lambda170(obj, obj2);
                case 144:
                    return lambda171(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda171(Object obj, Object obj2) {
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, obj) != Boolean.FALSE) {
                    return obj2;
                }
                CharSequence charSequence = this.ans;
                try {
                    try {
                        try {
                            strings.stringSet$Ex((CharSeq) charSequence, ((Number) obj2).intValue(), ((Char) obj).charValue());
                            return AddOp.$Pl.apply2(obj2, srfi13.Lit1);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-set!", 3, obj);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-set!", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "string-set!", 1, (Object) charSequence);
                }
            } catch (UnboundLocationException e4) {
                e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, 35);
                throw e4;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame75 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn172 = new ModuleMethod(this, 150, (Object) null, 0);
        final ModuleMethod lambda$Fn173 = new ModuleMethod(this, 151, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 150 ? lambda172() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 151 ? lambda173(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 150) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 151) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda172() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfilter, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda173(Object obj, Object obj2) {
            Object obj3;
            frame76 frame76 = new frame76();
            frame76.staticLink = this;
            if (misc.isProcedure(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(obj2, obj);
                try {
                    frame76.temp = strings.makeString(((Number) apply2).intValue());
                    Object stringFold$V = srfi13.stringFold$V(frame76.lambda$Fn174, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                    if (Scheme.numEqu.apply2(stringFold$V, apply2) != Boolean.FALSE) {
                        return frame76.temp;
                    }
                    try {
                        return strings.substring(frame76.temp, 0, ((Number) stringFold$V).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "substring", 3, stringFold$V);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "make-string", 1, apply2);
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        obj3 = this.criterion;
                    } else if (characters.isChar(this.criterion)) {
                        try {
                            obj3 = Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset.get(), this.criterion);
                        } catch (UnboundLocationException e3) {
                            e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1125, 26);
                            throw e3;
                        }
                    } else {
                        obj3 = misc.error$V("string-delete criterion not predicate, char or char-set", new Object[]{this.criterion});
                    }
                    frame76.cset = obj3;
                    Object stringFold$V2 = srfi13.stringFold$V(frame76.lambda$Fn175, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                    try {
                        frame76.ans = strings.makeString(((Number) stringFold$V2).intValue());
                        srfi13.stringFold$V(frame76.lambda$Fn176, srfi13.Lit0, this.s, new Object[]{obj, obj2});
                        return frame76.ans;
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "make-string", 1, stringFold$V2);
                    }
                } catch (UnboundLocationException e5) {
                    e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1124, 22);
                    throw e5;
                }
            }
        }
    }

    public static Object stringFilter$V(Object obj, Object obj2, Object[] objArr) {
        frame75 frame752 = new frame75();
        frame752.criterion = obj;
        frame752.s = obj2;
        frame752.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame752.lambda$Fn172, frame752.lambda$Fn173);
    }

    /* compiled from: srfi13.scm */
    public class frame76 extends ModuleBody {
        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn174;
        final ModuleMethod lambda$Fn175;
        final ModuleMethod lambda$Fn176;
        frame75 staticLink;
        CharSequence temp;

        public frame76() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 147, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1116");
            this.lambda$Fn174 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 148, (Object) null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1128");
            this.lambda$Fn175 = moduleMethod2;
            ModuleMethod moduleMethod3 = new ModuleMethod(this, 149, (Object) null, 8194);
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1133");
            this.lambda$Fn176 = moduleMethod3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 147:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 148:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 149:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda174(Object obj, Object obj2) {
            if (Scheme.applyToArgs.apply2(this.staticLink.criterion, obj) == Boolean.FALSE) {
                return obj2;
            }
            CharSequence charSequence = this.temp;
            try {
                try {
                    try {
                        strings.stringSet$Ex((CharSeq) charSequence, ((Number) obj2).intValue(), ((Char) obj).charValue());
                        return AddOp.$Pl.apply2(obj2, srfi13.Lit1);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-set!", 3, obj);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-set!", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "string-set!", 1, (Object) charSequence);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda175(Object obj, Object obj2) {
            try {
                return Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, obj) != Boolean.FALSE ? AddOp.$Pl.apply2(obj2, srfi13.Lit1) : obj2;
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1128, 45);
                throw e;
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 147:
                    return lambda174(obj, obj2);
                case 148:
                    return lambda175(obj, obj2);
                case 149:
                    return lambda176(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda176(Object obj, Object obj2) {
            try {
                if (Scheme.applyToArgs.apply3(srfi13.loc$char$Mnset$Mncontains$Qu.get(), this.cset, obj) == Boolean.FALSE) {
                    return obj2;
                }
                CharSequence charSequence = this.ans;
                try {
                    try {
                        try {
                            strings.stringSet$Ex((CharSeq) charSequence, ((Number) obj2).intValue(), ((Char) obj).charValue());
                            return AddOp.$Pl.apply2(obj2, srfi13.Lit1);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-set!", 3, obj);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-set!", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "string-set!", 1, (Object) charSequence);
                }
            } catch (UnboundLocationException e4) {
                e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1133, 35);
                throw e4;
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame77 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn177 = new ModuleMethod(this, 152, (Object) null, 0);
        final ModuleMethod lambda$Fn178 = new ModuleMethod(this, 153, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 152 ? lambda177() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 153 ? lambda178(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 152) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 153) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda177() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda178(Object obj, Object obj2) {
            if (characters.isChar(this.criterion)) {
                while (true) {
                    Object apply2 = Scheme.numLss.apply2(obj, obj2);
                    try {
                        boolean booleanValue = ((Boolean) apply2).booleanValue();
                        if (!booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.str;
                            try {
                                try {
                                    if (characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj).intValue())))) {
                                        return obj;
                                    }
                                    obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        while (true) {
                            Object apply22 = Scheme.numLss.apply2(obj, obj2);
                            try {
                                boolean booleanValue2 = ((Boolean) apply22).booleanValue();
                                if (!booleanValue2) {
                                    return booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                try {
                                    Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                    Object obj6 = this.criterion;
                                    Object obj7 = this.str;
                                    try {
                                        try {
                                            if (applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj).intValue()))) != Boolean.FALSE) {
                                                return obj;
                                            }
                                            obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "string-ref", 2, obj);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "string-ref", 1, obj7);
                                    }
                                } catch (UnboundLocationException e7) {
                                    e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1162, 9);
                                    throw e7;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        while (true) {
                            Object apply23 = Scheme.numLss.apply2(obj, obj2);
                            try {
                                boolean booleanValue3 = ((Boolean) apply23).booleanValue();
                                if (!booleanValue3) {
                                    return booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                Object obj8 = this.criterion;
                                Object obj9 = this.str;
                                try {
                                    try {
                                        if (applyToArgs2.apply2(obj8, Char.make(strings.stringRef((CharSequence) obj9, ((Number) obj).intValue()))) != Boolean.FALSE) {
                                            return obj;
                                        }
                                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "string-ref", 2, obj);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "string-ref", 1, obj9);
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply23);
                            }
                        }
                    } else {
                        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[]{srfi13.string$Mnindex, this.criterion});
                    }
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1159, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringIndex$V(Object obj, Object obj2, Object[] objArr) {
        frame77 frame772 = new frame77();
        frame772.str = obj;
        frame772.criterion = obj2;
        frame772.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame772.lambda$Fn177, frame772.lambda$Fn178);
    }

    /* compiled from: srfi13.scm */
    public class frame78 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn179 = new ModuleMethod(this, 154, (Object) null, 0);
        final ModuleMethod lambda$Fn180 = new ModuleMethod(this, 155, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 154 ? lambda179() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 155 ? lambda180(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 154) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 155) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda179() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex$Mnright, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda180(Object obj, Object obj2) {
            Boolean bool;
            if (characters.isChar(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                while (true) {
                    Object apply22 = Scheme.numGEq.apply2(apply2, obj);
                    try {
                        boolean booleanValue = ((Boolean) apply22).booleanValue();
                        if (!booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.str;
                            try {
                                try {
                                    if (characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) apply2).intValue())))) {
                                        return apply2;
                                    }
                                    apply2 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, apply2);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object apply23 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                        while (true) {
                            Object apply24 = Scheme.numGEq.apply2(apply23, obj);
                            try {
                                boolean booleanValue2 = ((Boolean) apply24).booleanValue();
                                if (booleanValue2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj6 = this.criterion;
                                        Object obj7 = this.str;
                                        try {
                                            try {
                                                if (applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) apply23).intValue()))) != Boolean.FALSE) {
                                                    return apply23;
                                                }
                                                apply23 = AddOp.$Mn.apply2(apply23, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                throw new WrongType(e5, "string-ref", 2, apply23);
                                            }
                                        } catch (ClassCastException e6) {
                                            throw new WrongType(e6, "string-ref", 1, obj7);
                                        }
                                    } catch (UnboundLocationException e7) {
                                        e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1182, 9);
                                        throw e7;
                                    }
                                } else {
                                    bool = booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply24);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply25 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                        while (true) {
                            Object apply26 = Scheme.numGEq.apply2(apply25, obj);
                            try {
                                boolean booleanValue3 = ((Boolean) apply26).booleanValue();
                                if (booleanValue3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj8 = this.criterion;
                                    Object obj9 = this.str;
                                    try {
                                        try {
                                            if (applyToArgs2.apply2(obj8, Char.make(strings.stringRef((CharSequence) obj9, ((Number) apply25).intValue()))) != Boolean.FALSE) {
                                                return apply25;
                                            }
                                            apply25 = AddOp.$Mn.apply2(apply25, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            throw new WrongType(e9, "string-ref", 2, apply25);
                                        }
                                    } catch (ClassCastException e10) {
                                        throw new WrongType(e10, "string-ref", 1, obj9);
                                    }
                                } else {
                                    bool = booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply26);
                            }
                        }
                    } else {
                        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[]{srfi13.string$Mnindex$Mnright, this.criterion});
                    }
                    return bool;
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1179, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringIndexRight$V(Object obj, Object obj2, Object[] objArr) {
        frame78 frame782 = new frame78();
        frame782.str = obj;
        frame782.criterion = obj2;
        frame782.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame782.lambda$Fn179, frame782.lambda$Fn180);
    }

    /* compiled from: srfi13.scm */
    public class frame79 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn181 = new ModuleMethod(this, 156, (Object) null, 0);
        final ModuleMethod lambda$Fn182 = new ModuleMethod(this, 157, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 156 ? lambda181() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 157 ? lambda182(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 156) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 157) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda181() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda182(Object obj, Object obj2) {
            if (characters.isChar(this.criterion)) {
                while (true) {
                    Object apply2 = Scheme.numLss.apply2(obj, obj2);
                    try {
                        boolean booleanValue = ((Boolean) apply2).booleanValue();
                        if (!booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.str;
                            try {
                                try {
                                    if (!characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) obj).intValue())))) {
                                        return obj;
                                    }
                                    obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        while (true) {
                            Object apply22 = Scheme.numLss.apply2(obj, obj2);
                            try {
                                boolean booleanValue2 = ((Boolean) apply22).booleanValue();
                                if (!booleanValue2) {
                                    return booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                try {
                                    Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                    Object obj6 = this.criterion;
                                    Object obj7 = this.str;
                                    try {
                                        try {
                                            if (applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj).intValue()))) == Boolean.FALSE) {
                                                return obj;
                                            }
                                            obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "string-ref", 2, obj);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "string-ref", 1, obj7);
                                    }
                                } catch (UnboundLocationException e7) {
                                    e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1203, 9);
                                    throw e7;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        while (true) {
                            Object apply23 = Scheme.numLss.apply2(obj, obj2);
                            try {
                                boolean booleanValue3 = ((Boolean) apply23).booleanValue();
                                if (!booleanValue3) {
                                    return booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                                ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                Object obj8 = this.criterion;
                                Object obj9 = this.str;
                                try {
                                    try {
                                        if (applyToArgs2.apply2(obj8, Char.make(strings.stringRef((CharSequence) obj9, ((Number) obj).intValue()))) == Boolean.FALSE) {
                                            return obj;
                                        }
                                        obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "string-ref", 2, obj);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "string-ref", 1, obj9);
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply23);
                            }
                        }
                    } else {
                        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", new Object[]{srfi13.string$Mnskip, this.criterion});
                    }
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1200, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringSkip$V(Object obj, Object obj2, Object[] objArr) {
        frame79 frame792 = new frame79();
        frame792.str = obj;
        frame792.criterion = obj2;
        frame792.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame792.lambda$Fn181, frame792.lambda$Fn182);
    }

    /* compiled from: srfi13.scm */
    public class frame80 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn183 = new ModuleMethod(this, 158, (Object) null, 0);
        final ModuleMethod lambda$Fn184 = new ModuleMethod(this, 159, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 158 ? lambda183() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 159 ? lambda184(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 158) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 159) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda183() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip$Mnright, this.str, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda184(Object obj, Object obj2) {
            Boolean bool;
            if (characters.isChar(this.criterion)) {
                Object apply2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                while (true) {
                    Object apply22 = Scheme.numGEq.apply2(apply2, obj);
                    try {
                        boolean booleanValue = ((Boolean) apply22).booleanValue();
                        if (!booleanValue) {
                            return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object obj3 = this.criterion;
                        try {
                            Char charR = (Char) obj3;
                            Object obj4 = this.str;
                            try {
                                try {
                                    if (!characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj4, ((Number) apply2).intValue())))) {
                                        return apply2;
                                    }
                                    apply2 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, apply2);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj4);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "char=?", 1, obj3);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply22);
                    }
                }
            } else {
                try {
                    if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                        Object apply23 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                        while (true) {
                            Object apply24 = Scheme.numGEq.apply2(apply23, obj);
                            try {
                                boolean booleanValue2 = ((Boolean) apply24).booleanValue();
                                if (booleanValue2) {
                                    ApplyToArgs applyToArgs = Scheme.applyToArgs;
                                    try {
                                        Object obj5 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                                        Object obj6 = this.criterion;
                                        Object obj7 = this.str;
                                        try {
                                            try {
                                                if (applyToArgs.apply3(obj5, obj6, Char.make(strings.stringRef((CharSequence) obj7, ((Number) apply23).intValue()))) == Boolean.FALSE) {
                                                    return apply23;
                                                }
                                                apply23 = AddOp.$Mn.apply2(apply23, srfi13.Lit1);
                                            } catch (ClassCastException e5) {
                                                throw new WrongType(e5, "string-ref", 2, apply23);
                                            }
                                        } catch (ClassCastException e6) {
                                            throw new WrongType(e6, "string-ref", 1, obj7);
                                        }
                                    } catch (UnboundLocationException e7) {
                                        e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1225, 9);
                                        throw e7;
                                    }
                                } else {
                                    bool = booleanValue2 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply24);
                            }
                        }
                    } else if (misc.isProcedure(this.criterion)) {
                        Object apply25 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                        while (true) {
                            Object apply26 = Scheme.numGEq.apply2(apply25, obj);
                            try {
                                boolean booleanValue3 = ((Boolean) apply26).booleanValue();
                                if (booleanValue3) {
                                    ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                                    Object obj8 = this.criterion;
                                    Object obj9 = this.str;
                                    try {
                                        try {
                                            if (applyToArgs2.apply2(obj8, Char.make(strings.stringRef((CharSequence) obj9, ((Number) apply25).intValue()))) == Boolean.FALSE) {
                                                return apply25;
                                            }
                                            apply25 = AddOp.$Mn.apply2(apply25, srfi13.Lit1);
                                        } catch (ClassCastException e9) {
                                            throw new WrongType(e9, "string-ref", 2, apply25);
                                        }
                                    } catch (ClassCastException e10) {
                                        throw new WrongType(e10, "string-ref", 1, obj9);
                                    }
                                } else {
                                    bool = booleanValue3 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply26);
                            }
                        }
                    } else {
                        return misc.error$V("CRITERION param is neither char-set or char.", new Object[]{srfi13.string$Mnskip$Mnright, this.criterion});
                    }
                    return bool;
                } catch (UnboundLocationException e12) {
                    e12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1222, 5);
                    throw e12;
                }
            }
        }
    }

    public static Object stringSkipRight$V(Object obj, Object obj2, Object[] objArr) {
        frame80 frame802 = new frame80();
        frame802.str = obj;
        frame802.criterion = obj2;
        frame802.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame802.lambda$Fn183, frame802.lambda$Fn184);
    }

    /* compiled from: srfi13.scm */
    public class frame81 extends ModuleBody {
        Object criterion;
        final ModuleMethod lambda$Fn185 = new ModuleMethod(this, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, (Object) null, 0);
        final ModuleMethod lambda$Fn186 = new ModuleMethod(this, 161, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 160 ? lambda185() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 161 ? lambda186(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 160) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 161) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda185() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncount, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda186(Object obj, Object obj2) {
            if (characters.isChar(this.criterion)) {
                Object obj3 = srfi13.Lit0;
                while (Scheme.numGEq.apply2(obj, obj2) == Boolean.FALSE) {
                    Object apply2 = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                    Object obj4 = this.criterion;
                    try {
                        Char charR = (Char) obj4;
                        Object obj5 = this.s;
                        try {
                            try {
                                if (characters.isChar$Eq(charR, Char.make(strings.stringRef((CharSequence) obj5, ((Number) obj).intValue())))) {
                                    obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
                                }
                                obj = apply2;
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-ref", 2, obj);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "string-ref", 1, obj5);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "char=?", 1, obj4);
                    }
                }
                return obj3;
            }
            try {
                if (Scheme.applyToArgs.apply2(srfi13.loc$char$Mnset$Qu.get(), this.criterion) != Boolean.FALSE) {
                    Object obj6 = srfi13.Lit0;
                    while (Scheme.numGEq.apply2(obj, obj2) == Boolean.FALSE) {
                        Object apply22 = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                        ApplyToArgs applyToArgs = Scheme.applyToArgs;
                        try {
                            Object obj7 = srfi13.loc$char$Mnset$Mncontains$Qu.get();
                            Object obj8 = this.criterion;
                            Object obj9 = this.s;
                            try {
                                try {
                                    if (applyToArgs.apply3(obj7, obj8, Char.make(strings.stringRef((CharSequence) obj9, ((Number) obj).intValue()))) != Boolean.FALSE) {
                                        obj6 = AddOp.$Pl.apply2(obj6, srfi13.Lit1);
                                    }
                                    obj = apply22;
                                } catch (ClassCastException e4) {
                                    throw new WrongType(e4, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "string-ref", 1, obj9);
                            }
                        } catch (UnboundLocationException e6) {
                            e6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1248, 16);
                            throw e6;
                        }
                    }
                    return obj6;
                } else if (misc.isProcedure(this.criterion)) {
                    Object obj10 = srfi13.Lit0;
                    while (Scheme.numGEq.apply2(obj, obj2) == Boolean.FALSE) {
                        Object apply23 = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                        ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
                        Object obj11 = this.criterion;
                        Object obj12 = this.s;
                        try {
                            try {
                                if (applyToArgs2.apply2(obj11, Char.make(strings.stringRef((CharSequence) obj12, ((Number) obj).intValue()))) != Boolean.FALSE) {
                                    obj10 = AddOp.$Pl.apply2(obj10, srfi13.Lit1);
                                }
                                obj = apply23;
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "string-ref", 2, obj);
                            }
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "string-ref", 1, obj12);
                        }
                    }
                    return obj10;
                } else {
                    return misc.error$V("CRITERION param is neither char-set or char.", new Object[]{srfi13.string$Mncount, this.criterion});
                }
            } catch (UnboundLocationException e9) {
                e9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1246, 5);
                throw e9;
            }
        }
    }

    public static Object stringCount$V(Object obj, Object obj2, Object[] objArr) {
        frame81 frame812 = new frame81();
        frame812.s = obj;
        frame812.criterion = obj2;
        frame812.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame812.lambda$Fn185, frame812.lambda$Fn186);
    }

    public static Object stringFill$Ex$V(Object obj, Object obj2, Object[] objArr) {
        frame82 frame822 = new frame82();
        frame822.s = obj;
        frame822.f336char = obj2;
        frame822.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), characters.char$Qu, frame822.f336char, string$Mnfill$Ex);
            return call_with_values.callWithValues(frame822.lambda$Fn187, frame822.lambda$Fn188);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1270, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame82 extends ModuleBody {

        /* renamed from: char  reason: not valid java name */
        Object f336char;
        final ModuleMethod lambda$Fn187 = new ModuleMethod(this, 162, (Object) null, 0);
        final ModuleMethod lambda$Fn188 = new ModuleMethod(this, 163, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 162 ? lambda187() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 163 ? lambda188(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 162) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 163) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda187() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfill$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda188(Object obj, Object obj2) {
            while (true) {
                obj2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                if (Scheme.numLss.apply2(obj2, obj) != Boolean.FALSE) {
                    return Values.empty;
                }
                Object obj3 = this.s;
                try {
                    CharSeq charSeq = (CharSeq) obj3;
                    try {
                        int intValue = ((Number) obj2).intValue();
                        Object obj4 = this.f336char;
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) obj4).charValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-set!", 3, obj4);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-set!", 2, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "string-set!", 1, obj3);
                }
            }
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 194) {
            return stringParseStart$PlEnd(obj, obj2, obj3);
        }
        if (i == 201) {
            try {
                try {
                    try {
                        return $PcSubstring$SlShared((CharSequence) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "%substring/shared", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%substring/shared", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "%substring/shared", 1, obj);
            }
        } else if (i == 277) {
            return $PcStringTitlecase$Ex(obj, obj2, obj3);
        } else {
            if (i == 299) {
                try {
                    try {
                        return stringCopy$Ex(obj, ((Number) obj2).intValue(), (CharSequence) obj3);
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-copy!", 3, obj3);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "string-copy!", 2, obj2);
                }
            } else if (i == 196) {
                return stringParseFinalStart$PlEnd(obj, obj2, obj3);
            } else {
                if (i != 197) {
                    return super.apply3(moduleMethod, obj, obj2, obj3);
                }
                return isSubstringSpecOk(obj, obj2, obj3) ? Boolean.TRUE : Boolean.FALSE;
            }
        }
    }

    public static Object stringCopy$Ex(Object obj, int i, CharSequence charSequence, int i2, int i3) {
        ModuleMethod moduleMethod = string$Mncopy$Ex;
        $PcCheckBounds(moduleMethod, charSequence, i2, i3);
        try {
            $PcCheckSubstringSpec(moduleMethod, (CharSequence) obj, i, (i3 - i2) + i);
            try {
                return $PcStringCopy$Ex((CharSequence) obj, i, charSequence, i2, i3);
            } catch (ClassCastException e) {
                throw new WrongType(e, "%string-copy!", 0, obj);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "%check-substring-spec", 1, obj);
        }
    }

    public static Object $PcStringCopy$Ex(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3) {
        if (i2 > i) {
            while (i2 < i3) {
                try {
                    strings.stringSet$Ex((CharSeq) charSequence, i, strings.stringRef(charSequence2, i2));
                    i++;
                    i2++;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-set!", 1, (Object) charSequence);
                }
            }
            return Values.empty;
        }
        int i4 = (i - 1) + (i3 - i2);
        int i5 = i3 - 1;
        while (i5 >= i2) {
            try {
                strings.stringSet$Ex((CharSeq) charSequence, i4, strings.stringRef(charSequence2, i5));
                i4--;
                i5--;
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "string-set!", 1, (Object) charSequence);
            }
        }
        return Values.empty;
    }

    /* compiled from: srfi13.scm */
    public class frame83 extends ModuleBody {
        final ModuleMethod lambda$Fn189 = new ModuleMethod(this, 166, (Object) null, 0);
        final ModuleMethod lambda$Fn190 = new ModuleMethod(this, 167, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 166 ? lambda189() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 167 ? lambda190(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 166) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 167) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda189() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains, this.text, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda190(Object obj, Object obj2, Object obj3) {
            frame84 frame84 = new frame84();
            frame84.staticLink = this;
            frame84.rest = obj;
            frame84.t$Mnstart = obj2;
            frame84.t$Mnend = obj3;
            return call_with_values.callWithValues(frame84.lambda$Fn191, frame84.lambda$Fn192);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame84 extends ModuleBody {
        final ModuleMethod lambda$Fn191 = new ModuleMethod(this, 164, (Object) null, 0);
        final ModuleMethod lambda$Fn192 = new ModuleMethod(this, 165, (Object) null, 8194);
        Object rest;
        frame83 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 164 ? lambda191() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 165 ? lambda192(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 164) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 165) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda191() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains, this.staticLink.pattern, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda192(Object obj, Object obj2) {
            return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, characters.char$Eq$Qu, obj, obj2, this.t$Mnstart, this.t$Mnend);
        }
    }

    public static Object stringContains$V(Object obj, Object obj2, Object[] objArr) {
        frame83 frame832 = new frame83();
        frame832.text = obj;
        frame832.pattern = obj2;
        frame832.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame832.lambda$Fn189, frame832.lambda$Fn190);
    }

    /* compiled from: srfi13.scm */
    public class frame85 extends ModuleBody {
        final ModuleMethod lambda$Fn193 = new ModuleMethod(this, 170, (Object) null, 0);
        final ModuleMethod lambda$Fn194 = new ModuleMethod(this, 171, (Object) null, 12291);
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 170 ? lambda193() : super.apply0(moduleMethod);
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 171 ? lambda194(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 170) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 171) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda193() {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains$Mnci, this.text, this.maybe$Mnstarts$Plends);
        }

        /* access modifiers changed from: package-private */
        public Object lambda194(Object obj, Object obj2, Object obj3) {
            frame86 frame86 = new frame86();
            frame86.staticLink = this;
            frame86.rest = obj;
            frame86.t$Mnstart = obj2;
            frame86.t$Mnend = obj3;
            return call_with_values.callWithValues(frame86.lambda$Fn195, frame86.lambda$Fn196);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame86 extends ModuleBody {
        final ModuleMethod lambda$Fn195 = new ModuleMethod(this, 168, (Object) null, 0);
        final ModuleMethod lambda$Fn196 = new ModuleMethod(this, 169, (Object) null, 8194);
        Object rest;
        frame85 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 168 ? lambda195() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 169 ? lambda196(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 168) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 169) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda195() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains$Mnci, this.staticLink.pattern, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda196(Object obj, Object obj2) {
            return srfi13.$PcKmpSearch(this.staticLink.pattern, this.staticLink.text, unicode.char$Mnci$Eq$Qu, obj, obj2, this.t$Mnstart, this.t$Mnend);
        }
    }

    public static Object stringContainsCi$V(Object obj, Object obj2, Object[] objArr) {
        frame85 frame852 = new frame85();
        frame852.text = obj;
        frame852.pattern = obj2;
        frame852.maybe$Mnstarts$Plends = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame852.lambda$Fn193, frame852.lambda$Fn194);
    }

    public static Object $PcKmpSearch(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Object obj8 = obj;
        Object obj9 = obj2;
        Object obj10 = obj3;
        Object obj11 = obj4;
        Object obj12 = obj5;
        Object apply2 = AddOp.$Mn.apply2(obj12, obj11);
        Object makeKmpRestartVector$V = makeKmpRestartVector$V(obj8, new Object[]{obj10, obj11, obj12});
        Number number = Lit0;
        Object obj13 = obj6;
        Object apply22 = AddOp.$Mn.apply2(obj7, obj13);
        Object obj14 = apply2;
        while (Scheme.numEqu.apply2(number, apply2) == Boolean.FALSE) {
            Object apply23 = Scheme.numLEq.apply2(obj14, apply22);
            try {
                boolean booleanValue = ((Boolean) apply23).booleanValue();
                if (!booleanValue) {
                    return booleanValue ? Boolean.TRUE : Boolean.FALSE;
                }
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                try {
                    try {
                        Char make = Char.make(strings.stringRef((CharSequence) obj9, ((Number) obj13).intValue()));
                        try {
                            CharSequence charSequence = (CharSequence) obj8;
                            Object apply24 = AddOp.$Pl.apply2(obj11, number);
                            try {
                                if (applyToArgs.apply3(obj10, make, Char.make(strings.stringRef(charSequence, ((Number) apply24).intValue()))) != Boolean.FALSE) {
                                    AddOp addOp = AddOp.$Pl;
                                    IntNum intNum = Lit1;
                                    obj13 = addOp.apply2(intNum, obj13);
                                    number = AddOp.$Pl.apply2(intNum, number);
                                    apply22 = AddOp.$Mn.apply2(apply22, intNum);
                                    obj14 = AddOp.$Mn.apply2(obj14, intNum);
                                } else {
                                    try {
                                        try {
                                            number = vectors.vectorRef((FVector) makeKmpRestartVector$V, number.intValue());
                                            if (Scheme.numEqu.apply2(number, Lit13) != Boolean.FALSE) {
                                                AddOp addOp2 = AddOp.$Pl;
                                                IntNum intNum2 = Lit1;
                                                obj13 = addOp2.apply2(obj13, intNum2);
                                                number = Lit0;
                                                apply22 = AddOp.$Mn.apply2(apply22, intNum2);
                                                obj14 = apply2;
                                            } else {
                                                obj14 = AddOp.$Mn.apply2(apply2, number);
                                            }
                                        } catch (ClassCastException e) {
                                            throw new WrongType(e, "vector-ref", 2, (Object) number);
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "vector-ref", 1, makeKmpRestartVector$V);
                                    }
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-ref", 2, apply24);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-ref", 1, obj8);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "string-ref", 2, obj13);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "string-ref", 1, obj9);
                }
            } catch (ClassCastException e7) {
                throw new WrongType(e7, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply23);
            }
        }
        return AddOp.$Mn.apply2(obj13, apply2);
    }

    public static Object makeKmpRestartVector$V(Object obj, Object[] objArr) {
        frame87 frame872 = new frame87();
        frame872.pattern = obj;
        LList makeList = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj2 = loc$let$Mnoptionals$St.get();
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
            Location location = loc$c$Eq;
            try {
                try {
                    Object apply3 = applyToArgs3.apply3(location.get(), characters.char$Eq$Qu, misc.isProcedure(location.get()) ? Boolean.TRUE : Boolean.FALSE);
                    ApplyToArgs applyToArgs4 = Scheme.applyToArgs;
                    ApplyToArgs applyToArgs5 = Scheme.applyToArgs;
                    Location location2 = loc$start;
                    try {
                        Object obj3 = location2.get();
                        Location location3 = loc$end;
                        try {
                            Object apply2 = applyToArgs2.apply2(apply3, applyToArgs4.apply2(applyToArgs5.apply2(obj3, location3.get()), frame872.lambda$Fn197));
                            try {
                                try {
                                    Object apply22 = AddOp.$Mn.apply2(location3.get(), location2.get());
                                    try {
                                        int intValue = ((Number) apply22).intValue();
                                        Object obj4 = Lit13;
                                        FVector makeVector = vectors.makeVector(intValue, obj4);
                                        NumberCompare numberCompare = Scheme.numGrt;
                                        Object obj5 = Lit0;
                                        if (numberCompare.apply2(apply22, obj5) != Boolean.FALSE) {
                                            Object apply23 = AddOp.$Mn.apply2(apply22, Lit1);
                                            Object obj6 = frame872.pattern;
                                            try {
                                                CharSequence charSequence = (CharSequence) obj6;
                                                try {
                                                    Object obj7 = location2.get();
                                                    try {
                                                        char stringRef = strings.stringRef(charSequence, ((Number) obj7).intValue());
                                                        try {
                                                            Object obj8 = location2.get();
                                                            while (Scheme.numLss.apply2(obj5, apply23) != Boolean.FALSE) {
                                                                while (true) {
                                                                    if (Scheme.numEqu.apply2(obj4, Lit13) != Boolean.FALSE) {
                                                                        AddOp addOp = AddOp.$Pl;
                                                                        IntNum intNum = Lit1;
                                                                        obj5 = addOp.apply2(intNum, obj5);
                                                                        ApplyToArgs applyToArgs6 = Scheme.applyToArgs;
                                                                        try {
                                                                            Object obj9 = loc$c$Eq.get();
                                                                            Object obj10 = apply23;
                                                                            Object obj11 = frame872.pattern;
                                                                            try {
                                                                                CharSequence charSequence2 = (CharSequence) obj11;
                                                                                LList lList = makeList;
                                                                                Object apply24 = AddOp.$Pl.apply2(obj8, intNum);
                                                                                try {
                                                                                    if (applyToArgs6.apply3(obj9, Char.make(strings.stringRef(charSequence2, ((Number) apply24).intValue())), Char.make(stringRef)) == Boolean.FALSE) {
                                                                                        try {
                                                                                            vectors.vectorSet$Ex(makeVector, ((Number) obj5).intValue(), Lit0);
                                                                                        } catch (ClassCastException e) {
                                                                                            throw new WrongType(e, "vector-set!", 2, obj5);
                                                                                        }
                                                                                    }
                                                                                    IntNum intNum2 = Lit0;
                                                                                    obj8 = AddOp.$Pl.apply2(obj8, intNum);
                                                                                    obj4 = intNum2;
                                                                                    apply23 = obj10;
                                                                                    makeList = lList;
                                                                                } catch (ClassCastException e2) {
                                                                                    throw new WrongType(e2, "string-ref", 2, apply24);
                                                                                }
                                                                            } catch (ClassCastException e3) {
                                                                                throw new WrongType(e3, "string-ref", 1, obj11);
                                                                            }
                                                                        } catch (UnboundLocationException e4) {
                                                                            UnboundLocationException unboundLocationException = e4;
                                                                            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1418, 18);
                                                                            throw unboundLocationException;
                                                                        }
                                                                    } else {
                                                                        LList lList2 = makeList;
                                                                        Object obj12 = apply23;
                                                                        ApplyToArgs applyToArgs7 = Scheme.applyToArgs;
                                                                        try {
                                                                            Object obj13 = loc$c$Eq.get();
                                                                            Object obj14 = frame872.pattern;
                                                                            try {
                                                                                try {
                                                                                    Char make = Char.make(strings.stringRef((CharSequence) obj14, ((Number) obj8).intValue()));
                                                                                    Object obj15 = frame872.pattern;
                                                                                    try {
                                                                                        CharSequence charSequence3 = (CharSequence) obj15;
                                                                                        frame87 frame873 = frame872;
                                                                                        char c = stringRef;
                                                                                        try {
                                                                                            Object apply25 = AddOp.$Pl.apply2(obj4, loc$start.get());
                                                                                            try {
                                                                                                if (applyToArgs7.apply3(obj13, make, Char.make(strings.stringRef(charSequence3, ((Number) apply25).intValue()))) != Boolean.FALSE) {
                                                                                                    AddOp addOp2 = AddOp.$Pl;
                                                                                                    IntNum intNum3 = Lit1;
                                                                                                    obj5 = addOp2.apply2(intNum3, obj5);
                                                                                                    obj4 = AddOp.$Pl.apply2(intNum3, obj4);
                                                                                                    try {
                                                                                                        vectors.vectorSet$Ex(makeVector, ((Number) obj5).intValue(), obj4);
                                                                                                        obj8 = AddOp.$Pl.apply2(obj8, intNum3);
                                                                                                        apply23 = obj12;
                                                                                                        makeList = lList2;
                                                                                                        frame872 = frame873;
                                                                                                        stringRef = c;
                                                                                                        break;
                                                                                                    } catch (ClassCastException e5) {
                                                                                                        throw new WrongType(e5, "vector-set!", 2, obj5);
                                                                                                    }
                                                                                                } else {
                                                                                                    try {
                                                                                                        obj4 = vectors.vectorRef(makeVector, ((Number) obj4).intValue());
                                                                                                        apply23 = obj12;
                                                                                                        makeList = lList2;
                                                                                                        frame872 = frame873;
                                                                                                        stringRef = c;
                                                                                                    } catch (ClassCastException e6) {
                                                                                                        throw new WrongType(e6, "vector-ref", 2, obj4);
                                                                                                    }
                                                                                                }
                                                                                            } catch (ClassCastException e7) {
                                                                                                throw new WrongType(e7, "string-ref", 2, apply25);
                                                                                            }
                                                                                        } catch (UnboundLocationException e8) {
                                                                                            UnboundLocationException unboundLocationException2 = e8;
                                                                                            unboundLocationException2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1422, 59);
                                                                                            throw unboundLocationException2;
                                                                                        }
                                                                                    } catch (ClassCastException e9) {
                                                                                        throw new WrongType(e9, "string-ref", 1, obj15);
                                                                                    }
                                                                                } catch (ClassCastException e10) {
                                                                                    throw new WrongType(e10, "string-ref", 2, obj8);
                                                                                }
                                                                            } catch (ClassCastException e11) {
                                                                                throw new WrongType(e11, "string-ref", 1, obj14);
                                                                            }
                                                                        } catch (UnboundLocationException e12) {
                                                                            UnboundLocationException unboundLocationException3 = e12;
                                                                            unboundLocationException3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1422, 7);
                                                                            throw unboundLocationException3;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (UnboundLocationException e13) {
                                                            UnboundLocationException unboundLocationException4 = e13;
                                                            unboundLocationException4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", YaVersion.SPLASH_VERSION, 6);
                                                            throw unboundLocationException4;
                                                        }
                                                    } catch (ClassCastException e14) {
                                                        throw new WrongType(e14, "string-ref", 2, obj7);
                                                    }
                                                } catch (UnboundLocationException e15) {
                                                    UnboundLocationException unboundLocationException5 = e15;
                                                    unboundLocationException5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1406, 27);
                                                    throw unboundLocationException5;
                                                }
                                            } catch (ClassCastException e16) {
                                                throw new WrongType(e16, "string-ref", 1, obj6);
                                            }
                                        }
                                        return applyToArgs.apply4(obj2, makeList, apply2, makeVector);
                                    } catch (ClassCastException e17) {
                                        throw new WrongType(e17, "make-vector", 1, apply22);
                                    }
                                } catch (UnboundLocationException e18) {
                                    UnboundLocationException unboundLocationException6 = e18;
                                    unboundLocationException6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, 26);
                                    throw unboundLocationException6;
                                }
                            } catch (UnboundLocationException e19) {
                                UnboundLocationException unboundLocationException7 = e19;
                                unboundLocationException7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BAD_VALUE_FOR_VERTICAL_ALIGNMENT, 22);
                                throw unboundLocationException7;
                            }
                        } catch (UnboundLocationException e20) {
                            UnboundLocationException unboundLocationException8 = e20;
                            unboundLocationException8.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1399, 14);
                            throw unboundLocationException8;
                        }
                    } catch (UnboundLocationException e21) {
                        UnboundLocationException unboundLocationException9 = e21;
                        unboundLocationException9.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1399, 7);
                        throw unboundLocationException9;
                    }
                } catch (UnboundLocationException e22) {
                    UnboundLocationException unboundLocationException10 = e22;
                    unboundLocationException10.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1398, 43);
                    throw unboundLocationException10;
                }
            } catch (UnboundLocationException e23) {
                UnboundLocationException unboundLocationException11 = e23;
                unboundLocationException11.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1398, 20);
                throw unboundLocationException11;
            }
        } catch (UnboundLocationException e24) {
            UnboundLocationException unboundLocationException12 = e24;
            unboundLocationException12.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1397, 3);
            throw unboundLocationException12;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame87 extends ModuleBody {
        final ModuleMethod lambda$Fn197;
        Object pattern;

        public frame87() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 172, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1399");
            this.lambda$Fn197 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 172 ? lambda197(obj) : super.apply1(moduleMethod, obj);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 172) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda197(Object obj) {
            return srfi13.stringParseStart$PlEnd(srfi13.make$Mnkmp$Mnrestart$Mnvector, this.pattern, obj);
        }
    }

    public static Object kmpStep(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        do {
            ApplyToArgs applyToArgs = Scheme.applyToArgs;
            try {
                CharSequence charSequence = (CharSequence) obj;
                Object apply2 = AddOp.$Pl.apply2(obj4, obj6);
                try {
                    if (applyToArgs.apply3(obj5, obj3, Char.make(strings.stringRef(charSequence, ((Number) apply2).intValue()))) != Boolean.FALSE) {
                        return AddOp.$Pl.apply2(obj4, Lit1);
                    }
                    try {
                        try {
                            obj4 = vectors.vectorRef((FVector) obj2, ((Number) obj4).intValue());
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "vector-ref", 2, obj4);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "vector-ref", 1, obj2);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "string-ref", 2, apply2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "string-ref", 1, obj);
            }
        } while (Scheme.numEqu.apply2(obj4, Lit13) == Boolean.FALSE);
        return Lit0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r17 != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0097, code lost:
        if (r17 != false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009c, code lost:
        r3 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x00ec A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringKmpPartialSearch$V(java.lang.Object r21, java.lang.Object r22, java.lang.Object r23, java.lang.Object r24, java.lang.Object[] r25) {
        /*
            r1 = r21
            r2 = r22
            java.lang.String r3 = "vector-ref"
            java.lang.String r4 = "string-ref"
            java.lang.String r5 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            gnu.kawa.slib.srfi13$frame88 r0 = new gnu.kawa.slib.srfi13$frame88
            r0.<init>()
            r6 = r23
            r0.s = r6
            r6 = 0
            r7 = r25
            gnu.lists.LList r6 = gnu.lists.LList.makeList(r7, r6)
            gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r8 = loc$check$Mnarg
            r9 = 3
            java.lang.Object r10 = r8.get()     // Catch:{ UnboundLocationException -> 0x0245 }
            gnu.expr.ModuleMethod r11 = kawa.lib.vectors.vector$Qu
            gnu.expr.ModuleMethod r12 = string$Mnkmp$Mnpartial$Mnsearch
            r7.apply4(r10, r11, r2, r12)
            gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$let$Mnoptionals$St
            java.lang.Object r9 = r10.get()     // Catch:{ UnboundLocationException -> 0x023c }
            gnu.kawa.functions.ApplyToArgs r10 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r11 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r13 = loc$c$Eq
            java.lang.Object r15 = r13.get()     // Catch:{ UnboundLocationException -> 0x0232 }
            gnu.expr.ModuleMethod r14 = kawa.lib.characters.char$Eq$Qu
            java.lang.Object r13 = r13.get()     // Catch:{ UnboundLocationException -> 0x0227 }
            boolean r13 = kawa.lib.misc.isProcedure(r13)
            if (r13 == 0) goto L_0x004b
            java.lang.Boolean r13 = java.lang.Boolean.TRUE
            goto L_0x004d
        L_0x004b:
            java.lang.Boolean r13 = java.lang.Boolean.FALSE
        L_0x004d:
            java.lang.Object r11 = r11.apply3(r15, r14, r13)
            gnu.kawa.functions.ApplyToArgs r13 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r14 = loc$p$Mnstart
            java.lang.Object r15 = r14.get()     // Catch:{ UnboundLocationException -> 0x021d }
            r16 = r4
            gnu.math.IntNum r4 = Lit0
            java.lang.Object r17 = r14.get()     // Catch:{ UnboundLocationException -> 0x0212 }
            boolean r17 = kawa.lib.numbers.isInteger(r17)
            if (r17 == 0) goto L_0x0095
            java.lang.Object r17 = r14.get()     // Catch:{ UnboundLocationException -> 0x008b }
            boolean r17 = kawa.lib.numbers.isExact(r17)
            r18 = r3
            if (r17 == 0) goto L_0x0088
            gnu.kawa.functions.NumberCompare r3 = kawa.standard.Scheme.numLEq
            java.lang.Object r14 = r14.get()     // Catch:{ UnboundLocationException -> 0x007e }
            java.lang.Object r3 = r3.apply2(r4, r14)
            goto L_0x009e
        L_0x007e:
            r0 = move-exception
            r1 = r0
            r0 = 64
            r3 = 1467(0x5bb, float:2.056E-42)
            r1.setLine(r5, r3, r0)
            throw r1
        L_0x0088:
            if (r17 == 0) goto L_0x009c
            goto L_0x0099
        L_0x008b:
            r0 = move-exception
            r3 = 1467(0x5bb, float:2.056E-42)
            r1 = r0
            r0 = 49
            r1.setLine(r5, r3, r0)
            throw r1
        L_0x0095:
            r18 = r3
            if (r17 == 0) goto L_0x009c
        L_0x0099:
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            goto L_0x009e
        L_0x009c:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
        L_0x009e:
            java.lang.Object r3 = r13.apply3(r15, r4, r3)
            gnu.kawa.functions.ApplyToArgs r4 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r13 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r14 = loc$s$Mnstart
            java.lang.Object r15 = r14.get()     // Catch:{ UnboundLocationException -> 0x0208 }
            gnu.mapping.Location r17 = loc$s$Mnend
            r19 = r5
            java.lang.Object r5 = r17.get()     // Catch:{ UnboundLocationException -> 0x01fc }
            java.lang.Object r5 = r13.apply2(r15, r5)
            gnu.expr.ModuleMethod r13 = r0.lambda$Fn198
            java.lang.Object r4 = r4.apply2(r5, r13)
            java.lang.Object r3 = r10.apply3(r11, r3, r4)
            r5 = r2
            gnu.lists.FVector r5 = (gnu.lists.FVector) r5     // Catch:{ ClassCastException -> 0x01f1 }
            int r5 = kawa.lib.vectors.vectorLength(r5)
            r0.patlen = r5
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            java.lang.Object r8 = r8.get()     // Catch:{ UnboundLocationException -> 0x01e6 }
            gnu.expr.ModuleMethod r10 = r0.lambda$Fn199
            r11 = r24
            r5.apply4(r8, r10, r11, r12)
            java.lang.Object r5 = r14.get()     // Catch:{ UnboundLocationException -> 0x01db }
        L_0x00dc:
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numEqu
            int r10 = r0.patlen
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Object r8 = r8.apply2(r11, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r8 == r10) goto L_0x00f3
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            java.lang.Object r11 = r0.apply1(r5)
            goto L_0x0103
        L_0x00f3:
            gnu.kawa.functions.NumberCompare r8 = kawa.standard.Scheme.numEqu
            gnu.mapping.Location r10 = loc$s$Mnend
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x01cf }
            java.lang.Object r8 = r8.apply2(r5, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r8 == r10) goto L_0x0108
        L_0x0103:
            java.lang.Object r0 = r7.apply4(r9, r6, r3, r11)
            return r0
        L_0x0108:
            java.lang.Object r8 = r0.s
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x01c4 }
            r12 = r5
            java.lang.Number r12 = (java.lang.Number) r12     // Catch:{ ClassCastException -> 0x01ba }
            int r12 = r12.intValue()     // Catch:{ ClassCastException -> 0x01ba }
            char r8 = kawa.lib.strings.stringRef(r8, r12)
            gnu.kawa.functions.AddOp r12 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r13 = Lit1
            java.lang.Object r5 = r12.apply2(r5, r13)
        L_0x011f:
            gnu.kawa.functions.ApplyToArgs r12 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r13 = loc$c$Eq
            java.lang.Object r13 = r13.get()     // Catch:{ UnboundLocationException -> 0x01ae }
            gnu.text.Char r15 = gnu.text.Char.make(r8)
            r14 = r1
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14     // Catch:{ ClassCastException -> 0x01a3 }
            gnu.kawa.functions.AddOp r4 = gnu.kawa.functions.AddOp.$Pl
            gnu.mapping.Location r20 = loc$p$Mnstart
            java.lang.Object r10 = r20.get()     // Catch:{ UnboundLocationException -> 0x0197 }
            java.lang.Object r4 = r4.apply2(r11, r10)
            r10 = r4
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x018d }
            int r4 = r10.intValue()     // Catch:{ ClassCastException -> 0x018d }
            char r4 = kawa.lib.strings.stringRef(r14, r4)
            gnu.text.Char r4 = gnu.text.Char.make(r4)
            java.lang.Object r4 = r12.apply3(r13, r15, r4)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r4 == r10) goto L_0x015b
            gnu.kawa.functions.AddOp r4 = gnu.kawa.functions.AddOp.$Pl
            gnu.math.IntNum r8 = Lit1
            java.lang.Object r4 = r4.apply2(r11, r8)
        L_0x0159:
            r11 = r4
            goto L_0x00dc
        L_0x015b:
            r4 = r2
            gnu.lists.FVector r4 = (gnu.lists.FVector) r4     // Catch:{ ClassCastException -> 0x0182 }
            r10 = r11
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0178 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0178 }
            java.lang.Object r11 = kawa.lib.vectors.vectorRef(r4, r10)
            gnu.kawa.functions.NumberCompare r4 = kawa.standard.Scheme.numEqu
            gnu.math.IntNum r10 = Lit13
            java.lang.Object r4 = r4.apply2(r11, r10)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r4 == r10) goto L_0x011f
            gnu.math.IntNum r4 = Lit0
            goto L_0x0159
        L_0x0178:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = r18
            r2 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r3, (int) r2, (java.lang.Object) r11)
            throw r1
        L_0x0182:
            r0 = move-exception
            r3 = r18
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r4 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r4, (java.lang.Object) r2)
            throw r0
        L_0x018d:
            r0 = move-exception
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r2 = r16
            r3 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r4)
            throw r1
        L_0x0197:
            r0 = move-exception
            r1 = r0
            r0 = 42
            r2 = r19
            r3 = 1484(0x5cc, float:2.08E-42)
            r1.setLine(r2, r3, r0)
            throw r1
        L_0x01a3:
            r0 = move-exception
            r2 = r16
            r3 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r4 = 1
            r0.<init>((java.lang.ClassCastException) r3, (java.lang.String) r2, (int) r4, (java.lang.Object) r1)
            throw r0
        L_0x01ae:
            r0 = move-exception
            r2 = r19
            r3 = 1484(0x5cc, float:2.08E-42)
            r1 = r0
            r0 = 14
            r1.setLine(r2, r3, r0)
            throw r1
        L_0x01ba:
            r0 = move-exception
            r2 = r16
            gnu.mapping.WrongType r1 = new gnu.mapping.WrongType
            r3 = 2
            r1.<init>((java.lang.ClassCastException) r0, (java.lang.String) r2, (int) r3, (java.lang.Object) r5)
            throw r1
        L_0x01c4:
            r0 = move-exception
            r2 = r16
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r8)
            throw r0
        L_0x01cf:
            r0 = move-exception
            r2 = r19
            r1 = r0
            r0 = 1479(0x5c7, float:2.073E-42)
            r3 = 15
            r1.setLine(r2, r0, r3)
            throw r1
        L_0x01db:
            r0 = move-exception
            r2 = r19
            r1 = r0
            r0 = 1476(0x5c4, float:2.068E-42)
            r3 = 7
            r1.setLine(r2, r0, r3)
            throw r1
        L_0x01e6:
            r0 = move-exception
            r2 = r19
            r3 = 7
            r1 = r0
            r0 = 1472(0x5c0, float:2.063E-42)
            r1.setLine(r2, r0, r3)
            throw r1
        L_0x01f1:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r3 = "vector-length"
            r4 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r3, (int) r4, (java.lang.Object) r2)
            throw r0
        L_0x01fc:
            r0 = move-exception
            r2 = r19
            r1 = r0
            r0 = 16
            r3 = 1468(0x5bc, float:2.057E-42)
            r1.setLine(r2, r3, r0)
            throw r1
        L_0x0208:
            r0 = move-exception
            r2 = r5
            r3 = 1468(0x5bc, float:2.057E-42)
            r1 = r0
            r4 = 7
            r1.setLine(r2, r3, r4)
            throw r1
        L_0x0212:
            r0 = move-exception
            r2 = r5
            r1 = r0
            r0 = 32
            r3 = 1467(0x5bb, float:2.056E-42)
            r1.setLine(r2, r3, r0)
            throw r1
        L_0x021d:
            r0 = move-exception
            r2 = r5
            r3 = 1467(0x5bb, float:2.056E-42)
            r1 = r0
            r4 = 6
            r1.setLine(r2, r3, r4)
            throw r1
        L_0x0227:
            r0 = move-exception
            r2 = r5
            r1 = r0
            r0 = 34
            r3 = 1466(0x5ba, float:2.054E-42)
            r1.setLine(r2, r3, r0)
            throw r1
        L_0x0232:
            r0 = move-exception
            r2 = r5
            r3 = 1466(0x5ba, float:2.054E-42)
            r4 = 6
            r1 = r0
            r1.setLine(r2, r3, r4)
            throw r1
        L_0x023c:
            r0 = move-exception
            r2 = r5
            r1 = r0
            r0 = 1465(0x5b9, float:2.053E-42)
            r1.setLine(r2, r0, r9)
            throw r1
        L_0x0245:
            r0 = move-exception
            r2 = r5
            r1 = r0
            r0 = 1464(0x5b8, float:2.052E-42)
            r1.setLine(r2, r0, r9)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringKmpPartialSearch$V(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* compiled from: srfi13.scm */
    public class frame88 extends ModuleBody {
        final ModuleMethod lambda$Fn198;
        final ModuleMethod lambda$Fn199;
        int patlen;
        Object s;

        public frame88() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 173, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1468");
            this.lambda$Fn198 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 174, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1472");
            this.lambda$Fn199 = moduleMethod2;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i == 173) {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            } else if (i != 174) {
                return super.match1(moduleMethod, obj, callContext);
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda198(Object obj) {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnkmp$Mnpartial$Mnsearch, this.s, obj);
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            int i = moduleMethod.selector;
            if (i == 173) {
                return lambda198(obj);
            }
            if (i != 174) {
                return super.apply1(moduleMethod, obj);
            }
            return lambda199(obj) ? Boolean.TRUE : Boolean.FALSE;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda199(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            if (!isExact) {
                return isExact;
            }
            Object apply2 = Scheme.numLEq.apply2(srfi13.Lit0, obj);
            try {
                boolean booleanValue = ((Boolean) apply2).booleanValue();
                return booleanValue ? ((Boolean) Scheme.numLss.apply2(obj, Integer.valueOf(this.patlen))).booleanValue() : booleanValue;
            } catch (ClassCastException e) {
                throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
            }
        }
    }

    public static boolean isStringNull(Object obj) {
        try {
            return numbers.isZero(Integer.valueOf(strings.stringLength((CharSequence) obj)));
        } catch (ClassCastException e) {
            throw new WrongType(e, "string-length", 1, obj);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame89 extends ModuleBody {
        final ModuleMethod lambda$Fn200 = new ModuleMethod(this, 175, (Object) null, 0);
        final ModuleMethod lambda$Fn201 = new ModuleMethod(this, 176, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 175 ? lambda200() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 176 ? lambda201(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 175) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 176) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda200() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda201(Object obj, Object obj2) {
            Object apply2 = AddOp.$Mn.apply2(obj2, obj);
            try {
                CharSequence makeString = strings.makeString(((Number) apply2).intValue());
                while (true) {
                    apply2 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                    if (Scheme.numLss.apply2(apply2, srfi13.Lit0) != Boolean.FALSE) {
                        return makeString;
                    }
                    try {
                        CharSeq charSeq = (CharSeq) makeString;
                        try {
                            int intValue = ((Number) apply2).intValue();
                            Object obj3 = this.s;
                            try {
                                try {
                                    strings.stringSet$Ex(charSeq, intValue, strings.stringRef((CharSequence) obj3, ((Number) obj).intValue()));
                                    obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "string-set!", 2, apply2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "string-set!", 1, (Object) makeString);
                    }
                }
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "make-string", 1, apply2);
            }
        }
    }

    public static Object stringReverse$V(Object obj, Object[] objArr) {
        frame89 frame892 = new frame89();
        frame892.s = obj;
        frame892.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame892.lambda$Fn200, frame892.lambda$Fn201);
    }

    /* compiled from: srfi13.scm */
    public class frame90 extends ModuleBody {
        final ModuleMethod lambda$Fn202 = new ModuleMethod(this, 177, (Object) null, 0);
        final ModuleMethod lambda$Fn203 = new ModuleMethod(this, 178, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 177 ? lambda202() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 178 ? lambda203(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 177) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 178) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda202() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse$Ex, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda203(Object obj, Object obj2) {
            Object apply2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            while (Scheme.numLEq.apply2(apply2, obj) == Boolean.FALSE) {
                Object obj3 = this.s;
                try {
                    try {
                        char stringRef = strings.stringRef((CharSequence) obj3, ((Number) apply2).intValue());
                        Object obj4 = this.s;
                        try {
                            CharSeq charSeq = (CharSeq) obj4;
                            try {
                                int intValue = ((Number) apply2).intValue();
                                Object obj5 = this.s;
                                try {
                                    try {
                                        strings.stringSet$Ex(charSeq, intValue, strings.stringRef((CharSequence) obj5, ((Number) obj).intValue()));
                                        Object obj6 = this.s;
                                        try {
                                            try {
                                                strings.stringSet$Ex((CharSeq) obj6, ((Number) obj).intValue(), stringRef);
                                                apply2 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                                                obj = AddOp.$Pl.apply2(obj, srfi13.Lit1);
                                            } catch (ClassCastException e) {
                                                throw new WrongType(e, "string-set!", 2, obj);
                                            }
                                        } catch (ClassCastException e2) {
                                            throw new WrongType(e2, "string-set!", 1, obj6);
                                        }
                                    } catch (ClassCastException e3) {
                                        throw new WrongType(e3, "string-ref", 2, obj);
                                    }
                                } catch (ClassCastException e4) {
                                    throw new WrongType(e4, "string-ref", 1, obj5);
                                }
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "string-set!", 2, apply2);
                            }
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "string-set!", 1, obj4);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "string-ref", 2, apply2);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "string-ref", 1, obj3);
                }
            }
            return Values.empty;
        }
    }

    public static Object stringReverse$Ex$V(Object obj, Object[] objArr) {
        frame90 frame902 = new frame90();
        frame902.s = obj;
        frame902.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame902.lambda$Fn202, frame902.lambda$Fn203);
    }

    public static CharSequence reverseList$To$String(Object obj) {
        try {
            int length = lists.length((LList) obj);
            CharSequence makeString = strings.makeString(length);
            Object valueOf = Integer.valueOf(length - 1);
            while (lists.isPair(obj)) {
                try {
                    CharSeq charSeq = (CharSeq) makeString;
                    try {
                        int intValue = ((Number) valueOf).intValue();
                        Object apply1 = lists.car.apply1(obj);
                        try {
                            strings.stringSet$Ex(charSeq, intValue, ((Char) apply1).charValue());
                            valueOf = AddOp.$Mn.apply2(valueOf, Lit1);
                            obj = lists.cdr.apply1(obj);
                        } catch (ClassCastException e) {
                            throw new WrongType(e, "string-set!", 3, apply1);
                        }
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "string-set!", 2, valueOf);
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "string-set!", 1, (Object) makeString);
                }
            }
            return makeString;
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "length", 1, obj);
        }
    }

    /* compiled from: srfi13.scm */
    public class frame91 extends ModuleBody {
        final ModuleMethod lambda$Fn204 = new ModuleMethod(this, 179, (Object) null, 0);
        final ModuleMethod lambda$Fn205 = new ModuleMethod(this, 180, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 179 ? lambda204() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 180 ? lambda205(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 179) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 180) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda204() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mn$Grlist, this.s, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public Object lambda205(Object obj, Object obj2) {
            Object apply2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            Object obj3 = LList.Empty;
            while (Scheme.numLss.apply2(apply2, obj) == Boolean.FALSE) {
                Object apply22 = AddOp.$Mn.apply2(apply2, srfi13.Lit1);
                Object obj4 = this.s;
                try {
                    try {
                        obj3 = lists.cons(Char.make(strings.stringRef((CharSequence) obj4, ((Number) apply2).intValue())), obj3);
                        apply2 = apply22;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "string-ref", 2, apply2);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "string-ref", 1, obj4);
                }
            }
            return obj3;
        }
    }

    public static Object string$To$List$V(Object obj, Object[] objArr) {
        frame91 frame912 = new frame91();
        frame912.s = obj;
        frame912.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        return call_with_values.callWithValues(frame912.lambda$Fn204, frame912.lambda$Fn205);
    }

    public static Object stringAppend$SlShared$V(Object[] objArr) {
        return stringConcatenate$SlShared(LList.makeList(objArr, 0));
    }

    public static Object stringConcatenate$SlShared(Object obj) {
        Object obj2 = Lit0;
        Object obj3 = Boolean.FALSE;
        while (lists.isPair(obj)) {
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            try {
                int stringLength = strings.stringLength((CharSequence) apply1);
                if (!numbers.isZero(Integer.valueOf(stringLength))) {
                    obj2 = AddOp.$Pl.apply2(obj2, Integer.valueOf(stringLength));
                    if (obj3 == Boolean.FALSE) {
                        obj3 = obj;
                    }
                }
                obj = apply12;
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, apply1);
            }
        }
        try {
            if (numbers.isZero((Number) obj2)) {
                return "";
            }
            NumberCompare numberCompare = Scheme.numEqu;
            Object apply13 = lists.car.apply1(obj3);
            try {
                if (numberCompare.apply2(obj2, Integer.valueOf(strings.stringLength((CharSequence) apply13))) != Boolean.FALSE) {
                    return lists.car.apply1(obj3);
                }
                try {
                    CharSequence makeString = strings.makeString(((Number) obj2).intValue());
                    Object obj4 = Lit0;
                    while (lists.isPair(obj3)) {
                        Object apply14 = lists.car.apply1(obj3);
                        try {
                            int stringLength2 = strings.stringLength((CharSequence) apply14);
                            try {
                                try {
                                    $PcStringCopy$Ex(makeString, ((Number) obj4).intValue(), (CharSequence) apply14, 0, stringLength2);
                                    obj3 = lists.cdr.apply1(obj3);
                                    obj4 = AddOp.$Pl.apply2(obj4, Integer.valueOf(stringLength2));
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "%string-copy!", 2, apply14);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "%string-copy!", 1, obj4);
                            }
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-length", 1, apply14);
                        }
                    }
                    return makeString;
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "make-string", 1, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "string-length", 1, apply13);
            }
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "zero?", 1, obj2);
        }
    }

    public static CharSequence stringConcatenate(Object obj) {
        Object obj2 = Lit0;
        Object obj3 = obj;
        while (lists.isPair(obj3)) {
            Object apply1 = lists.cdr.apply1(obj3);
            AddOp addOp = AddOp.$Pl;
            Object apply12 = lists.car.apply1(obj3);
            try {
                obj2 = addOp.apply2(obj2, Integer.valueOf(strings.stringLength((CharSequence) apply12)));
                obj3 = apply1;
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, apply12);
            }
        }
        try {
            CharSequence makeString = strings.makeString(((Number) obj2).intValue());
            Object obj4 = Lit0;
            while (lists.isPair(obj)) {
                Object apply13 = lists.car.apply1(obj);
                try {
                    int stringLength = strings.stringLength((CharSequence) apply13);
                    try {
                        try {
                            $PcStringCopy$Ex(makeString, ((Number) obj4).intValue(), (CharSequence) apply13, 0, stringLength);
                            obj4 = AddOp.$Pl.apply2(obj4, Integer.valueOf(stringLength));
                            obj = lists.cdr.apply1(obj);
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "%string-copy!", 2, apply13);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "%string-copy!", 1, obj4);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "string-length", 1, apply13);
                }
            }
            return makeString;
        } catch (ClassCastException e5) {
            throw new WrongType(e5, "make-string", 1, obj2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0096, code lost:
        if (r14 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a3, code lost:
        if (r14 != false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a8, code lost:
        r8 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringConcatenateReverse$V(java.lang.Object r16, java.lang.Object[] r17) {
        /*
            java.lang.String r1 = "string-length"
            java.lang.String r2 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r0 = 0
            r3 = r17
            gnu.lists.LList r0 = gnu.lists.LList.makeList(r3, r0)
            gnu.kawa.functions.ApplyToArgs r3 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r4 = loc$let$Mnoptionals$St
            r5 = 1617(0x651, float:2.266E-42)
            java.lang.Object r4 = r4.get()     // Catch:{ UnboundLocationException -> 0x013c }
            gnu.kawa.functions.ApplyToArgs r6 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r8 = loc$final
            java.lang.Object r10 = r8.get()     // Catch:{ UnboundLocationException -> 0x0134 }
            java.lang.Object r5 = r8.get()     // Catch:{ UnboundLocationException -> 0x012c }
            boolean r5 = kawa.lib.strings.isString(r5)
            if (r5 == 0) goto L_0x002c
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            goto L_0x002e
        L_0x002c:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
        L_0x002e:
            java.lang.String r11 = ""
            java.lang.Object r5 = r7.apply3(r10, r11, r5)
            gnu.kawa.functions.ApplyToArgs r7 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$end
            r11 = 1618(0x652, float:2.267E-42)
            java.lang.Object r12 = r10.get()     // Catch:{ UnboundLocationException -> 0x0124 }
            java.lang.Object r11 = r8.get()     // Catch:{ UnboundLocationException -> 0x011c }
            r13 = 1
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ ClassCastException -> 0x0114 }
            int r11 = kawa.lib.strings.stringLength(r11)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.Object r14 = r10.get()     // Catch:{ UnboundLocationException -> 0x010a }
            boolean r14 = kawa.lib.numbers.isInteger(r14)
            if (r14 == 0) goto L_0x00a3
            java.lang.Object r14 = r10.get()     // Catch:{ UnboundLocationException -> 0x0099 }
            boolean r14 = kawa.lib.numbers.isExact(r14)
            if (r14 == 0) goto L_0x0096
            gnu.kawa.functions.NumberCompare r14 = kawa.standard.Scheme.numLEq
            gnu.math.IntNum r15 = Lit0
            r9 = 1621(0x655, float:2.272E-42)
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x008e }
            java.lang.Object r8 = r8.get()     // Catch:{ UnboundLocationException -> 0x0086 }
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x007e }
            int r8 = kawa.lib.strings.stringLength(r8)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Object r8 = r14.apply3(r15, r10, r8)
            goto L_0x00aa
        L_0x007e:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r13, (java.lang.Object) r8)
            throw r0
        L_0x0086:
            r0 = move-exception
            r1 = r0
            r3 = 36
            r1.setLine(r2, r9, r3)
            throw r1
        L_0x008e:
            r0 = move-exception
            r1 = r0
            r0 = 17
            r1.setLine(r2, r9, r0)
            throw r1
        L_0x0096:
            if (r14 == 0) goto L_0x00a8
            goto L_0x00a5
        L_0x0099:
            r0 = move-exception
            r1 = r0
            r0 = 1620(0x654, float:2.27E-42)
            r3 = 19
            r1.setLine(r2, r0, r3)
            throw r1
        L_0x00a3:
            if (r14 == 0) goto L_0x00a8
        L_0x00a5:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            goto L_0x00aa
        L_0x00a8:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
        L_0x00aa:
            java.lang.Object r7 = r7.apply3(r12, r11, r8)
            java.lang.Object r5 = r6.apply2(r5, r7)
            gnu.math.IntNum r6 = Lit0
            r7 = r16
        L_0x00b6:
            boolean r8 = kawa.lib.lists.isPair(r7)
            if (r8 == 0) goto L_0x00e1
            gnu.kawa.functions.AddOp r8 = gnu.kawa.functions.AddOp.$Pl
            gnu.expr.GenericProc r9 = kawa.lib.lists.car
            java.lang.Object r9 = r9.apply1(r7)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x00d9 }
            int r9 = kawa.lib.strings.stringLength(r9)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.Object r6 = r8.apply2(r6, r9)
            gnu.expr.GenericProc r8 = kawa.lib.lists.cdr
            java.lang.Object r7 = r8.apply1(r7)
            goto L_0x00b6
        L_0x00d9:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r13, (java.lang.Object) r9)
            throw r0
        L_0x00e1:
            gnu.mapping.Location r1 = loc$final
            r7 = 1627(0x65b, float:2.28E-42)
            java.lang.Object r1 = r1.get()     // Catch:{ UnboundLocationException -> 0x0102 }
            gnu.mapping.Location r8 = loc$end
            java.lang.Object r2 = r8.get()     // Catch:{ UnboundLocationException -> 0x00fa }
            r7 = r16
            java.lang.Object r1 = $PcFinishStringConcatenateReverse(r6, r7, r1, r2)
            java.lang.Object r0 = r3.apply4(r4, r0, r5, r1)
            return r0
        L_0x00fa:
            r0 = move-exception
            r1 = r0
            r0 = 65
            r1.setLine(r2, r7, r0)
            throw r1
        L_0x0102:
            r0 = move-exception
            r1 = r0
            r0 = 59
            r1.setLine(r2, r7, r0)
            throw r1
        L_0x010a:
            r0 = move-exception
            r1 = r0
            r0 = 1619(0x653, float:2.269E-42)
            r3 = 21
            r1.setLine(r2, r0, r3)
            throw r1
        L_0x0114:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r13, (java.lang.Object) r11)
            throw r0
        L_0x011c:
            r0 = move-exception
            r1 = r0
            r0 = 28
            r1.setLine(r2, r11, r0)
            throw r1
        L_0x0124:
            r0 = move-exception
            r1 = r0
            r0 = 8
            r1.setLine(r2, r11, r0)
            throw r1
        L_0x012c:
            r0 = move-exception
            r1 = r0
            r0 = 55
            r1.setLine(r2, r5, r0)
            throw r1
        L_0x0134:
            r0 = move-exception
            r1 = r0
            r3 = 36
            r1.setLine(r2, r5, r3)
            throw r1
        L_0x013c:
            r0 = move-exception
            r1 = r0
            r0 = 3
            r1.setLine(r2, r5, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringConcatenateReverse$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x015e, code lost:
        if (r1 != false) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0173, code lost:
        r1 = $PcFinishStringConcatenateReverse(r8, r9, loc$final.get(), r7.get());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x017c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x017d, code lost:
        r1 = r0;
        r1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1652, 62);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0183, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0184, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0185, code lost:
        r1 = r0;
        r1.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1652, 56);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x018b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0098, code lost:
        if (r16 != false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a5, code lost:
        if (r16 != false) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00aa, code lost:
        r0 = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0152, code lost:
        if (r1.apply2(r8, java.lang.Integer.valueOf(kawa.lib.strings.stringLength((java.lang.CharSequence) r10))) != java.lang.Boolean.FALSE) goto L_0x0160;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x012c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object stringConcatenateReverse$SlShared$V(java.lang.Object r17, java.lang.Object[] r18) {
        /*
            java.lang.String r1 = "zero?"
            java.lang.String r2 = "string-length"
            java.lang.String r3 = "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm"
            r0 = 0
            r4 = r18
            gnu.lists.LList r4 = gnu.lists.LList.makeList(r4, r0)
            gnu.kawa.functions.ApplyToArgs r5 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r6 = loc$let$Mnoptionals$St
            r7 = 1630(0x65e, float:2.284E-42)
            java.lang.Object r6 = r6.get()     // Catch:{ UnboundLocationException -> 0x01db }
            gnu.kawa.functions.ApplyToArgs r8 = kawa.standard.Scheme.applyToArgs
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r10 = loc$final
            java.lang.Object r12 = r10.get()     // Catch:{ UnboundLocationException -> 0x01d3 }
            java.lang.Object r7 = r10.get()     // Catch:{ UnboundLocationException -> 0x01cb }
            boolean r7 = kawa.lib.strings.isString(r7)
            if (r7 == 0) goto L_0x002e
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            goto L_0x0030
        L_0x002e:
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
        L_0x0030:
            java.lang.String r13 = ""
            java.lang.Object r7 = r9.apply3(r12, r13, r7)
            gnu.kawa.functions.ApplyToArgs r9 = kawa.standard.Scheme.applyToArgs
            gnu.mapping.Location r12 = loc$end
            r13 = 1631(0x65f, float:2.286E-42)
            java.lang.Object r14 = r12.get()     // Catch:{ UnboundLocationException -> 0x01c3 }
            java.lang.Object r13 = r10.get()     // Catch:{ UnboundLocationException -> 0x01bb }
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13     // Catch:{ ClassCastException -> 0x01b2 }
            int r13 = kawa.lib.strings.stringLength(r13)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            java.lang.Object r16 = r12.get()     // Catch:{ UnboundLocationException -> 0x01a8 }
            boolean r16 = kawa.lib.numbers.isInteger(r16)
            if (r16 == 0) goto L_0x00a5
            java.lang.Object r16 = r12.get()     // Catch:{ UnboundLocationException -> 0x009b }
            boolean r16 = kawa.lib.numbers.isExact(r16)
            if (r16 == 0) goto L_0x0098
            gnu.kawa.functions.NumberCompare r0 = kawa.standard.Scheme.numLEq
            gnu.math.IntNum r11 = Lit0
            r15 = 1634(0x662, float:2.29E-42)
            java.lang.Object r12 = r12.get()     // Catch:{ UnboundLocationException -> 0x0090 }
            java.lang.Object r10 = r10.get()     // Catch:{ UnboundLocationException -> 0x0088 }
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x007f }
            int r10 = kawa.lib.strings.stringLength(r10)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Object r0 = r0.apply3(r11, r12, r10)
            goto L_0x00ac
        L_0x007f:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r10)
            throw r0
        L_0x0088:
            r0 = move-exception
            r1 = r0
            r2 = 36
            r1.setLine(r3, r15, r2)
            throw r1
        L_0x0090:
            r0 = move-exception
            r1 = r0
            r0 = 17
            r1.setLine(r3, r15, r0)
            throw r1
        L_0x0098:
            if (r16 == 0) goto L_0x00aa
            goto L_0x00a7
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r0 = 1633(0x661, float:2.288E-42)
            r2 = 19
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x00a5:
            if (r16 == 0) goto L_0x00aa
        L_0x00a7:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x00ac
        L_0x00aa:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x00ac:
            java.lang.Object r0 = r9.apply3(r14, r13, r0)
            java.lang.Object r0 = r8.apply2(r7, r0)
            gnu.math.IntNum r7 = Lit0
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            r9 = r8
            r8 = r7
            r7 = r17
        L_0x00bc:
            boolean r10 = kawa.lib.lists.isPair(r7)
            if (r10 == 0) goto L_0x00f8
            gnu.expr.GenericProc r10 = kawa.lib.lists.car
            java.lang.Object r10 = r10.apply1(r7)
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x00ef }
            int r10 = kawa.lib.strings.stringLength(r10)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)
            java.lang.Object r8 = r11.apply2(r8, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r9 != r11) goto L_0x00e8
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            boolean r10 = kawa.lib.numbers.isZero(r10)
            if (r10 == 0) goto L_0x00e7
            goto L_0x00e8
        L_0x00e7:
            r9 = r7
        L_0x00e8:
            gnu.expr.GenericProc r10 = kawa.lib.lists.cdr
            java.lang.Object r7 = r10.apply1(r7)
            goto L_0x00bc
        L_0x00ef:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r10)
            throw r0
        L_0x00f8:
            r7 = r8
            java.lang.Number r7 = (java.lang.Number) r7     // Catch:{ ClassCastException -> 0x019f }
            boolean r7 = kawa.lib.numbers.isZero(r7)
            if (r7 == 0) goto L_0x012c
            gnu.mapping.Location r1 = loc$final
            r2 = 1645(0x66d, float:2.305E-42)
            java.lang.Object r1 = r1.get()     // Catch:{ UnboundLocationException -> 0x0124 }
            gnu.math.IntNum r7 = Lit0
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            gnu.mapping.Location r9 = loc$end
            java.lang.Object r2 = r9.get()     // Catch:{ UnboundLocationException -> 0x011c }
            r3 = 0
            r8[r3] = r2
            java.lang.Object r1 = substring$SlShared$V(r1, r7, r8)
            goto L_0x0177
        L_0x011c:
            r0 = move-exception
            r1 = r0
            r0 = 49
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x0124:
            r0 = move-exception
            r1 = r0
            r0 = 41
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x012c:
            gnu.mapping.Location r7 = loc$end
            java.lang.Object r10 = r7.get()     // Catch:{ UnboundLocationException -> 0x0195 }
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x018c }
            boolean r1 = kawa.lib.numbers.isZero(r10)
            if (r1 == 0) goto L_0x015e
            gnu.kawa.functions.NumberCompare r1 = kawa.standard.Scheme.numEqu
            gnu.expr.GenericProc r10 = kawa.lib.lists.car
            java.lang.Object r10 = r10.apply1(r9)
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0155 }
            int r2 = kawa.lib.strings.stringLength(r10)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r1 = r1.apply2(r8, r2)
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            if (r1 == r2) goto L_0x0167
            goto L_0x0160
        L_0x0155:
            r0 = move-exception
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r10)
            throw r0
        L_0x015e:
            if (r1 == 0) goto L_0x0167
        L_0x0160:
            gnu.expr.GenericProc r1 = kawa.lib.lists.car
            java.lang.Object r1 = r1.apply1(r9)
            goto L_0x0177
        L_0x0167:
            gnu.mapping.Location r1 = loc$final
            r2 = 1652(0x674, float:2.315E-42)
            java.lang.Object r1 = r1.get()     // Catch:{ UnboundLocationException -> 0x0184 }
            java.lang.Object r2 = r7.get()     // Catch:{ UnboundLocationException -> 0x017c }
            java.lang.Object r1 = $PcFinishStringConcatenateReverse(r8, r9, r1, r2)
        L_0x0177:
            java.lang.Object r0 = r5.apply4(r6, r4, r0, r1)
            return r0
        L_0x017c:
            r0 = move-exception
            r1 = r0
            r0 = 62
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x0184:
            r0 = move-exception
            r1 = r0
            r0 = 56
            r1.setLine(r3, r2, r0)
            throw r1
        L_0x018c:
            r0 = move-exception
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r3 = 1
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r3, (java.lang.Object) r10)
            throw r0
        L_0x0195:
            r0 = move-exception
            r1 = r0
            r0 = 1649(0x671, float:2.311E-42)
            r2 = 16
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x019f:
            r0 = move-exception
            r3 = 1
            r2 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r2, (java.lang.String) r1, (int) r3, (java.lang.Object) r8)
            throw r0
        L_0x01a8:
            r0 = move-exception
            r1 = r0
            r0 = 1632(0x660, float:2.287E-42)
            r2 = 21
            r1.setLine(r3, r0, r2)
            throw r1
        L_0x01b2:
            r0 = move-exception
            r3 = 1
            r1 = r0
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r0.<init>((java.lang.ClassCastException) r1, (java.lang.String) r2, (int) r3, (java.lang.Object) r13)
            throw r0
        L_0x01bb:
            r0 = move-exception
            r1 = r0
            r0 = 28
            r1.setLine(r3, r13, r0)
            throw r1
        L_0x01c3:
            r0 = move-exception
            r1 = r0
            r0 = 8
            r1.setLine(r3, r13, r0)
            throw r1
        L_0x01cb:
            r0 = move-exception
            r1 = r0
            r0 = 55
            r1.setLine(r3, r7, r0)
            throw r1
        L_0x01d3:
            r0 = move-exception
            r1 = r0
            r2 = 36
            r1.setLine(r3, r7, r2)
            throw r1
        L_0x01db:
            r0 = move-exception
            r1 = r0
            r0 = 3
            r1.setLine(r3, r7, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi13.stringConcatenateReverse$SlShared$V(java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        int i = moduleMethod.selector;
        if (i == 195) {
            try {
                try {
                    try {
                        return $PcCheckBounds(obj, (CharSequence) obj2, ((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "%check-bounds", 4, obj4);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "%check-bounds", 3, obj3);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "%check-bounds", 2, obj2);
            }
        } else if (i == 198) {
            return checkSubstringSpec(obj, obj2, obj3, obj4);
        } else {
            if (i == 204) {
                return $PcStringMap(obj, obj2, obj3, obj4);
            }
            if (i == 206) {
                return $PcStringMap$Ex(obj, obj2, obj3, obj4);
            }
            if (i == 299) {
                try {
                    try {
                        try {
                            return stringCopy$Ex(obj, ((Number) obj2).intValue(), (CharSequence) obj3, ((Number) obj4).intValue());
                        } catch (ClassCastException e4) {
                            throw new WrongType(e4, "string-copy!", 4, obj4);
                        }
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, "string-copy!", 3, obj3);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "string-copy!", 2, obj2);
                }
            } else if (i != 319) {
                return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            } else {
                return $PcFinishStringConcatenateReverse(obj, obj2, obj3, obj4);
            }
        }
    }

    public static Object $PcFinishStringConcatenateReverse(Object obj, Object obj2, Object obj3, Object obj4) {
        Object apply2 = AddOp.$Pl.apply2(obj4, obj);
        try {
            CharSequence makeString = strings.makeString(((Number) apply2).intValue());
            try {
                try {
                    try {
                        $PcStringCopy$Ex(makeString, ((Number) obj).intValue(), (CharSequence) obj3, 0, ((Number) obj4).intValue());
                        while (lists.isPair(obj2)) {
                            Object apply1 = lists.car.apply1(obj2);
                            obj2 = lists.cdr.apply1(obj2);
                            try {
                                int stringLength = strings.stringLength((CharSequence) apply1);
                                obj = AddOp.$Mn.apply2(obj, Integer.valueOf(stringLength));
                                try {
                                    try {
                                        $PcStringCopy$Ex(makeString, ((Number) obj).intValue(), (CharSequence) apply1, 0, stringLength);
                                    } catch (ClassCastException e) {
                                        throw new WrongType(e, "%string-copy!", 2, apply1);
                                    }
                                } catch (ClassCastException e2) {
                                    throw new WrongType(e2, "%string-copy!", 1, obj);
                                }
                            } catch (ClassCastException e3) {
                                throw new WrongType(e3, "string-length", 1, apply1);
                            }
                        }
                        return makeString;
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "%string-copy!", 4, obj4);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "%string-copy!", 2, obj3);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "%string-copy!", 1, obj);
            }
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "make-string", 1, apply2);
        }
    }

    public static Object stringReplace$V(Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        frame92 frame922 = new frame92();
        frame922.s1 = obj;
        frame922.s2 = obj2;
        frame922.start1 = obj3;
        frame922.end1 = obj4;
        frame922.maybe$Mnstart$Plend = LList.makeList(objArr, 0);
        checkSubstringSpec(string$Mnreplace, frame922.s1, frame922.start1, frame922.end1);
        return call_with_values.callWithValues(frame922.lambda$Fn206, frame922.lambda$Fn207);
    }

    /* compiled from: srfi13.scm */
    public class frame92 extends ModuleBody {
        Object end1;
        final ModuleMethod lambda$Fn206 = new ModuleMethod(this, 181, (Object) null, 0);
        final ModuleMethod lambda$Fn207 = new ModuleMethod(this, 182, (Object) null, 8194);
        LList maybe$Mnstart$Plend;
        Object s1;
        Object s2;
        Object start1;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 181 ? lambda206() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 182 ? lambda207(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 181) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 182) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda206() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreplace, this.s2, this.maybe$Mnstart$Plend);
        }

        /* access modifiers changed from: package-private */
        public CharSequence lambda207(Object obj, Object obj2) {
            Object obj3 = this.s1;
            try {
                int stringLength = strings.stringLength((CharSequence) obj3);
                Object apply2 = AddOp.$Mn.apply2(obj2, obj);
                Object apply22 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(Integer.valueOf(stringLength), AddOp.$Mn.apply2(this.end1, this.start1)), apply2);
                try {
                    CharSequence makeString = strings.makeString(((Number) apply22).intValue());
                    Object obj4 = this.s1;
                    try {
                        CharSequence charSequence = (CharSequence) obj4;
                        Object obj5 = this.start1;
                        try {
                            srfi13.$PcStringCopy$Ex(makeString, 0, charSequence, 0, ((Number) obj5).intValue());
                            Object obj6 = this.start1;
                            try {
                                int intValue = ((Number) obj6).intValue();
                                Object obj7 = this.s2;
                                try {
                                    try {
                                        try {
                                            srfi13.$PcStringCopy$Ex(makeString, intValue, (CharSequence) obj7, ((Number) obj).intValue(), ((Number) obj2).intValue());
                                            Object apply23 = AddOp.$Pl.apply2(this.start1, apply2);
                                            try {
                                                int intValue2 = ((Number) apply23).intValue();
                                                Object obj8 = this.s1;
                                                try {
                                                    CharSequence charSequence2 = (CharSequence) obj8;
                                                    Object obj9 = this.end1;
                                                    try {
                                                        srfi13.$PcStringCopy$Ex(makeString, intValue2, charSequence2, ((Number) obj9).intValue(), stringLength);
                                                        return makeString;
                                                    } catch (ClassCastException e) {
                                                        throw new WrongType(e, "%string-copy!", 3, obj9);
                                                    }
                                                } catch (ClassCastException e2) {
                                                    throw new WrongType(e2, "%string-copy!", 2, obj8);
                                                }
                                            } catch (ClassCastException e3) {
                                                throw new WrongType(e3, "%string-copy!", 1, apply23);
                                            }
                                        } catch (ClassCastException e4) {
                                            throw new WrongType(e4, "%string-copy!", 4, obj2);
                                        }
                                    } catch (ClassCastException e5) {
                                        throw new WrongType(e5, "%string-copy!", 3, obj);
                                    }
                                } catch (ClassCastException e6) {
                                    throw new WrongType(e6, "%string-copy!", 2, obj7);
                                }
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "%string-copy!", 1, obj6);
                            }
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "%string-copy!", 4, obj5);
                        }
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, "%string-copy!", 2, obj4);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "make-string", 1, apply22);
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "string-length", 1, obj3);
            }
        }
    }

    public static Object stringTokenize$V(Object obj, Object[] objArr) {
        frame93 frame932 = new frame93();
        frame932.s = obj;
        LList makeList = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj2 = loc$let$Mnoptionals$St.get();
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
            Location location = loc$token$Mnchars;
            try {
                try {
                    try {
                        try {
                            try {
                                return applyToArgs.apply4(obj2, makeList, applyToArgs2.apply2(applyToArgs3.apply3(location.get(), GetNamedPart.getNamedPart.apply2(loc$char$Mnset.get(), Lit14), Scheme.applyToArgs.apply2(loc$char$Mnset$Qu.get(), location.get())), loc$rest.get()), call_with_values.callWithValues(frame932.lambda$Fn208, frame932.lambda$Fn209));
                            } catch (UnboundLocationException e) {
                                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 75);
                                throw e;
                            }
                        } catch (UnboundLocationException e2) {
                            e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 61);
                            throw e2;
                        }
                    } catch (UnboundLocationException e3) {
                        e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 50);
                        throw e3;
                    }
                } catch (UnboundLocationException e4) {
                    e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 33);
                    throw e4;
                }
            } catch (UnboundLocationException e5) {
                e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1695, 20);
                throw e5;
            }
        } catch (UnboundLocationException e6) {
            e6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1694, 3);
            throw e6;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame93 extends ModuleBody {
        final ModuleMethod lambda$Fn208 = new ModuleMethod(this, 183, (Object) null, 0);
        final ModuleMethod lambda$Fn209 = new ModuleMethod(this, 184, (Object) null, 8194);
        Object s;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 183 ? lambda208() : super.apply0(moduleMethod);
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 184 ? lambda209(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 183) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 184) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda208() {
            try {
                return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntokenize, this.s, srfi13.loc$rest.get());
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1696, 57);
                throw e;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda209(Object obj, Object obj2) {
            Object obj3;
            Object obj4 = LList.Empty;
            while (true) {
                Object apply2 = Scheme.numLss.apply2(obj, obj2);
                try {
                    boolean booleanValue = ((Boolean) apply2).booleanValue();
                    if (booleanValue) {
                        try {
                            obj3 = srfi13.stringIndexRight$V(this.s, srfi13.loc$token$Mnchars.get(), new Object[]{obj, obj2});
                        } catch (UnboundLocationException e) {
                            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1698, 48);
                            throw e;
                        }
                    } else {
                        obj3 = booleanValue ? Boolean.TRUE : Boolean.FALSE;
                    }
                    if (obj3 == Boolean.FALSE) {
                        return obj4;
                    }
                    Object apply22 = AddOp.$Pl.apply2(srfi13.Lit1, obj3);
                    try {
                        obj2 = srfi13.stringSkipRight$V(this.s, srfi13.loc$token$Mnchars.get(), new Object[]{obj, obj3});
                        if (obj2 != Boolean.FALSE) {
                            Object obj5 = this.s;
                            try {
                                CharSequence charSequence = (CharSequence) obj5;
                                Object apply23 = AddOp.$Pl.apply2(srfi13.Lit1, obj2);
                                try {
                                    try {
                                        obj4 = lists.cons(strings.substring(charSequence, ((Number) apply23).intValue(), ((Number) apply22).intValue()), obj4);
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "substring", 3, apply22);
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "substring", 2, apply23);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "substring", 1, obj5);
                            }
                        } else {
                            Object obj6 = this.s;
                            try {
                                try {
                                    try {
                                        return lists.cons(strings.substring((CharSequence) obj6, ((Number) obj).intValue(), ((Number) apply22).intValue()), obj4);
                                    } catch (ClassCastException e5) {
                                        throw new WrongType(e5, "substring", 3, apply22);
                                    }
                                } catch (ClassCastException e6) {
                                    throw new WrongType(e6, "substring", 2, obj);
                                }
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "substring", 1, obj6);
                            }
                        }
                    } catch (UnboundLocationException e8) {
                        e8.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", ErrorMessages.ERROR_BAD_VALUE_FOR_TEXT_RECEIVING, 34);
                        throw e8;
                    }
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, apply2);
                }
            }
        }
    }

    /* compiled from: srfi13.scm */
    public class frame94 extends ModuleBody {
        Object from;
        final ModuleMethod lambda$Fn211;
        final ModuleMethod lambda$Fn212 = new ModuleMethod(this, 185, (Object) null, 0);
        final ModuleMethod lambda$Fn213;
        final ModuleMethod lambda$Fn214;
        final ModuleMethod lambda$Fn215;
        LList maybe$Mnto$Plstart$Plend;
        Object s;

        public frame94() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 186, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1744");
            this.lambda$Fn214 = moduleMethod;
            this.lambda$Fn213 = new ModuleMethod(this, 187, (Object) null, 8194);
            this.lambda$Fn211 = new ModuleMethod(this, 188, (Object) null, 0);
            ModuleMethod moduleMethod2 = new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_DIALOG_FLAG, (Object) null, 12291);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1740");
            this.lambda$Fn215 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            int i = moduleMethod.selector;
            return i != 185 ? i != 188 ? super.apply0(moduleMethod) : lambda211() : lambda212();
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 187 ? lambda213(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 185 && i != 188) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 187) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        static boolean lambda210(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            return isInteger ? numbers.isExact(obj) : isInteger;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 189 ? lambda215(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 189) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda211() {
            if (lists.isPair(this.maybe$Mnto$Plstart$Plend)) {
                return call_with_values.callWithValues(this.lambda$Fn212, this.lambda$Fn213);
            }
            try {
                Object apply4 = Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), strings.string$Qu, this.s, srfi13.xsubstring);
                try {
                    int stringLength = strings.stringLength((CharSequence) apply4);
                    return misc.values(AddOp.$Pl.apply2(this.from, Integer.valueOf(stringLength)), srfi13.Lit0, Integer.valueOf(stringLength));
                } catch (ClassCastException e) {
                    throw new WrongType(e, "string-length", 1, apply4);
                }
            } catch (UnboundLocationException e2) {
                e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1749, 36);
                throw e2;
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda212() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.xsubstring, this.s, lists.cdr.apply1(this.maybe$Mnto$Plstart$Plend));
        }

        /* access modifiers changed from: package-private */
        public Object lambda213(Object obj, Object obj2) {
            Object apply1 = lists.car.apply1(this.maybe$Mnto$Plstart$Plend);
            try {
                Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), this.lambda$Fn214, apply1, srfi13.xsubstring);
                return misc.values(apply1, obj, obj2);
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1744, 6);
                throw e;
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 186) {
                return lambda214(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda214(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            if (!isInteger) {
                return isInteger;
            }
            boolean isExact = numbers.isExact(obj);
            return isExact ? ((Boolean) Scheme.numLEq.apply2(this.from, obj)).booleanValue() : isExact;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 186) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda215(Object obj, Object obj2, Object obj3) {
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            Object apply2 = AddOp.$Mn.apply2(obj6, obj5);
            Object apply22 = AddOp.$Mn.apply2(obj4, this.from);
            try {
                if (numbers.isZero((Number) apply22)) {
                    return "";
                }
                try {
                    if (numbers.isZero((Number) apply2)) {
                        return misc.error$V("Cannot replicate empty (sub)string", new Object[]{srfi13.xsubstring, this.s, this.from, obj4, obj5, obj6});
                    } else if (Scheme.numEqu.apply2(srfi13.Lit1, apply2) != Boolean.FALSE) {
                        try {
                            int intValue = ((Number) apply22).intValue();
                            Object obj7 = this.s;
                            try {
                                try {
                                    return strings.makeString(intValue, Char.make(strings.stringRef((CharSequence) obj7, ((Number) obj5).intValue())));
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "string-ref", 2, obj5);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "string-ref", 1, obj7);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "make-string", 1, apply22);
                        }
                    } else {
                        Object apply23 = DivideOp.$Sl.apply2(this.from, apply2);
                        try {
                            double doubleValue = numbers.floor(LangObjType.coerceRealNum(apply23)).doubleValue();
                            Object apply24 = DivideOp.$Sl.apply2(obj4, apply2);
                            try {
                                if (doubleValue == numbers.floor(LangObjType.coerceRealNum(apply24)).doubleValue()) {
                                    Object obj8 = this.s;
                                    try {
                                        CharSequence charSequence = (CharSequence) obj8;
                                        Object apply25 = AddOp.$Pl.apply2(obj5, DivideOp.modulo.apply2(this.from, apply2));
                                        try {
                                            int intValue2 = ((Number) apply25).intValue();
                                            Object apply26 = AddOp.$Pl.apply2(obj5, DivideOp.modulo.apply2(obj4, apply2));
                                            try {
                                                return strings.substring(charSequence, intValue2, ((Number) apply26).intValue());
                                            } catch (ClassCastException e4) {
                                                throw new WrongType(e4, "substring", 3, apply26);
                                            }
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "substring", 2, apply25);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "substring", 1, obj8);
                                    }
                                } else {
                                    try {
                                        CharSequence makeString = strings.makeString(((Number) apply22).intValue());
                                        srfi13.$PcMultispanRepcopy$Ex(makeString, srfi13.Lit0, this.s, this.from, obj, obj2, obj3);
                                        return makeString;
                                    } catch (ClassCastException e7) {
                                        throw new WrongType(e7, "make-string", 1, apply22);
                                    }
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, "floor", 1, apply24);
                            }
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "floor", 1, apply23);
                        }
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "zero?", 1, apply2);
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "zero?", 1, apply22);
            }
        }
    }

    public static Object xsubstring$V(Object obj, Object obj2, Object[] objArr) {
        frame94 frame942 = new frame94();
        frame942.s = obj;
        frame942.from = obj2;
        frame942.maybe$Mnto$Plstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn210, frame942.from, xsubstring);
            return call_with_values.callWithValues(frame942.lambda$Fn211, frame942.lambda$Fn215);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1738, 3);
            throw e;
        }
    }

    /* compiled from: srfi13.scm */
    public class frame95 extends ModuleBody {
        final ModuleMethod lambda$Fn217 = new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE, (Object) null, 0);
        final ModuleMethod lambda$Fn218 = new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_SEEK, (Object) null, 0);
        final ModuleMethod lambda$Fn219 = new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PLAY, (Object) null, 8194);
        final ModuleMethod lambda$Fn221;
        LList maybe$Mnsto$Plstart$Plend;
        Object s;
        Object sfrom;
        Object target;
        Object tstart;

        public frame95() {
            ModuleMethod moduleMethod = new ModuleMethod(this, FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_STOP, (Object) null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm:1781");
            this.lambda$Fn221 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            int i = moduleMethod.selector;
            return i != 190 ? i != 192 ? super.apply0(moduleMethod) : lambda217() : lambda218();
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 191 ? lambda219(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i != 190 && i != 192) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 191) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        static boolean lambda216(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            return isInteger ? numbers.isExact(obj) : isInteger;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            return moduleMethod.selector == 193 ? lambda221(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            if (moduleMethod.selector != 193) {
                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda217() {
            if (lists.isPair(this.maybe$Mnsto$Plstart$Plend)) {
                return call_with_values.callWithValues(this.lambda$Fn218, this.lambda$Fn219);
            }
            Object obj = this.s;
            try {
                int stringLength = strings.stringLength((CharSequence) obj);
                return misc.values(AddOp.$Pl.apply2(this.sfrom, Integer.valueOf(stringLength)), srfi13.Lit0, Integer.valueOf(stringLength));
            } catch (ClassCastException e) {
                throw new WrongType(e, "string-length", 1, obj);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda218() {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnxcopy$Ex, this.s, lists.cdr.apply1(this.maybe$Mnsto$Plstart$Plend));
        }

        /* access modifiers changed from: package-private */
        public Object lambda219(Object obj, Object obj2) {
            Object apply1 = lists.car.apply1(this.maybe$Mnsto$Plstart$Plend);
            try {
                Scheme.applyToArgs.apply4(srfi13.loc$check$Mnarg.get(), srfi13.lambda$Fn220, apply1, srfi13.string$Mnxcopy$Ex);
                return misc.values(apply1, obj, obj2);
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1785, 6);
                throw e;
            }
        }

        static boolean lambda220(Object obj) {
            boolean isInteger = numbers.isInteger(obj);
            return isInteger ? numbers.isExact(obj) : isInteger;
        }

        /* access modifiers changed from: package-private */
        public Object lambda221(Object obj, Object obj2, Object obj3) {
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            Object apply2 = AddOp.$Mn.apply2(obj4, this.sfrom);
            Object apply22 = AddOp.$Pl.apply2(this.tstart, apply2);
            Object apply23 = AddOp.$Mn.apply2(obj6, obj5);
            srfi13.checkSubstringSpec(srfi13.string$Mnxcopy$Ex, this.target, this.tstart, apply22);
            try {
                boolean isZero = numbers.isZero((Number) apply2);
                if (isZero) {
                    return isZero ? Boolean.TRUE : Boolean.FALSE;
                }
                try {
                    if (numbers.isZero((Number) apply23)) {
                        return misc.error$V("Cannot replicate empty (sub)string", new Object[]{srfi13.string$Mnxcopy$Ex, this.target, this.tstart, this.s, this.sfrom, obj4, obj5, obj6});
                    } else if (Scheme.numEqu.apply2(srfi13.Lit1, apply23) != Boolean.FALSE) {
                        Object obj7 = this.target;
                        Object obj8 = this.s;
                        try {
                            try {
                                return srfi13.stringFill$Ex$V(obj7, Char.make(strings.stringRef((CharSequence) obj8, ((Number) obj5).intValue())), new Object[]{this.tstart, apply22});
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "string-ref", 2, obj5);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "string-ref", 1, obj8);
                        }
                    } else {
                        Object apply24 = DivideOp.$Sl.apply2(this.sfrom, apply23);
                        try {
                            double doubleValue = numbers.floor(LangObjType.coerceRealNum(apply24)).doubleValue();
                            Object apply25 = DivideOp.$Sl.apply2(obj4, apply23);
                            try {
                                if (doubleValue != numbers.floor(LangObjType.coerceRealNum(apply25)).doubleValue()) {
                                    return srfi13.$PcMultispanRepcopy$Ex(this.target, this.tstart, this.s, this.sfrom, obj, obj2, obj3);
                                }
                                Object obj9 = this.target;
                                try {
                                    CharSequence charSequence = (CharSequence) obj9;
                                    Object obj10 = this.tstart;
                                    try {
                                        int intValue = ((Number) obj10).intValue();
                                        Object obj11 = this.s;
                                        try {
                                            CharSequence charSequence2 = (CharSequence) obj11;
                                            Object apply26 = AddOp.$Pl.apply2(obj5, DivideOp.modulo.apply2(this.sfrom, apply23));
                                            try {
                                                int intValue2 = ((Number) apply26).intValue();
                                                Object apply27 = AddOp.$Pl.apply2(obj5, DivideOp.modulo.apply2(obj4, apply23));
                                                try {
                                                    return srfi13.$PcStringCopy$Ex(charSequence, intValue, charSequence2, intValue2, ((Number) apply27).intValue());
                                                } catch (ClassCastException e3) {
                                                    throw new WrongType(e3, "%string-copy!", 4, apply27);
                                                }
                                            } catch (ClassCastException e4) {
                                                throw new WrongType(e4, "%string-copy!", 3, apply26);
                                            }
                                        } catch (ClassCastException e5) {
                                            throw new WrongType(e5, "%string-copy!", 2, obj11);
                                        }
                                    } catch (ClassCastException e6) {
                                        throw new WrongType(e6, "%string-copy!", 1, obj10);
                                    }
                                } catch (ClassCastException e7) {
                                    throw new WrongType(e7, "%string-copy!", 0, obj9);
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, "floor", 1, apply25);
                            }
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "floor", 1, apply24);
                        }
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "zero?", 1, apply23);
                }
            } catch (ClassCastException e11) {
                throw new WrongType(e11, "zero?", 1, apply2);
            }
        }
    }

    public static Object stringXcopy$Ex$V(Object obj, Object obj2, Object obj3, Object obj4, Object[] objArr) {
        frame95 frame952 = new frame95();
        frame952.target = obj;
        frame952.tstart = obj2;
        frame952.s = obj3;
        frame952.sfrom = obj4;
        frame952.maybe$Mnsto$Plstart$Plend = LList.makeList(objArr, 0);
        try {
            Scheme.applyToArgs.apply4(loc$check$Mnarg.get(), lambda$Fn216, frame952.sfrom, string$Mnxcopy$Ex);
            return call_with_values.callWithValues(frame952.lambda$Fn217, frame952.lambda$Fn221);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1779, 3);
            throw e;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 199:
                return frame1.lambda5(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 209:
                return lambda17(obj);
            case 211:
                return lambda18(obj);
            case 217:
                return lambda27(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 239:
                return frame32.lambda72(obj);
            case 240:
                return frame32.lambda73(obj);
            case 242:
                return frame34.lambda78(obj);
            case 244:
                return frame36.lambda83(obj);
            case 245:
                return frame36.lambda84(obj);
            case 247:
                return frame38.lambda89(obj);
            case ComponentConstants.LISTVIEW_PREFERRED_WIDTH:
                return frame38.lambda90(obj);
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION:
                return frame40.lambda95(obj);
            case Telnet.WONT:
                return frame42.lambda100(obj);
            case Telnet.DONT:
                return frame44.lambda105(obj);
            case 255:
                return frame44.lambda106(obj);
            case InputDeviceCompat.SOURCE_KEYBOARD:
                return frame46.lambda111(obj);
            case 259:
                return frame48.lambda116(obj);
            case 260:
                return frame48.lambda117(obj);
            case 262:
                return frame50.lambda122(obj);
            case 263:
                return frame50.lambda123(obj);
            case 265:
                return frame52.lambda128(obj);
            case 267:
                return frame54.lambda133(obj);
            case 271:
                return Integer.valueOf(frame57.lambda138(obj));
            case 287:
                return frame71.lambda163(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 289:
                return frame72.lambda166(obj) ? Boolean.TRUE : Boolean.FALSE;
            case ErrorMessages.ERROR_TWITTER_REQUEST_DIRECT_MESSAGES_FAILED:
                return isStringNull(obj) ? Boolean.TRUE : Boolean.FALSE;
            case ErrorMessages.ERROR_TWITTER_STOP_FOLLOWING_FAILED:
                return reverseList$To$String(obj);
            case ErrorMessages.ERROR_TWITTER_INVALID_IMAGE_PATH:
                return stringConcatenate$SlShared(obj);
            case 316:
                return stringConcatenate(obj);
            case 322:
                return frame94.lambda210(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 324:
                return frame95.lambda216(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 325:
                return frame95.lambda220(obj) ? Boolean.TRUE : Boolean.FALSE;
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static Object $PcMultispanRepcopy$Ex(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Object obj8 = obj;
        Object obj9 = obj2;
        Object obj10 = obj3;
        Object obj11 = obj4;
        Object obj12 = obj6;
        Object obj13 = obj7;
        Object apply2 = AddOp.$Mn.apply2(obj13, obj12);
        Object apply22 = AddOp.$Pl.apply2(obj12, DivideOp.modulo.apply2(obj11, apply2));
        Object apply23 = AddOp.$Mn.apply2(obj5, obj11);
        try {
            try {
                try {
                    try {
                        try {
                            $PcStringCopy$Ex((CharSequence) obj8, ((Number) obj9).intValue(), (CharSequence) obj10, ((Number) apply22).intValue(), ((Number) obj13).intValue());
                            Object apply24 = AddOp.$Mn.apply2(obj13, apply22);
                            Object apply25 = DivideOp.quotient.apply2(AddOp.$Mn.apply2(apply23, apply24), apply2);
                            Object apply26 = AddOp.$Pl.apply2(obj9, apply24);
                            while (!numbers.isZero((Number) apply25)) {
                                try {
                                    try {
                                        try {
                                            try {
                                                try {
                                                    try {
                                                        $PcStringCopy$Ex((CharSequence) obj8, ((Number) apply26).intValue(), (CharSequence) obj10, ((Number) obj12).intValue(), ((Number) obj13).intValue());
                                                        apply26 = AddOp.$Pl.apply2(apply26, apply2);
                                                        apply25 = AddOp.$Mn.apply2(apply25, Lit1);
                                                    } catch (ClassCastException e) {
                                                        throw new WrongType(e, "%string-copy!", 4, obj13);
                                                    }
                                                } catch (ClassCastException e2) {
                                                    throw new WrongType(e2, "%string-copy!", 3, obj12);
                                                }
                                            } catch (ClassCastException e3) {
                                                throw new WrongType(e3, "%string-copy!", 2, obj10);
                                            }
                                        } catch (ClassCastException e4) {
                                            throw new WrongType(e4, "%string-copy!", 1, apply26);
                                        }
                                    } catch (ClassCastException e5) {
                                        throw new WrongType(e5, "%string-copy!", 0, obj8);
                                    }
                                } catch (ClassCastException e6) {
                                    throw new WrongType(e6, "zero?", 1, apply25);
                                }
                            }
                            try {
                                CharSequence charSequence = (CharSequence) obj8;
                                try {
                                    int intValue = ((Number) apply26).intValue();
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj10;
                                        try {
                                            int intValue2 = ((Number) obj12).intValue();
                                            Object apply27 = AddOp.$Pl.apply2(obj12, AddOp.$Mn.apply2(apply23, AddOp.$Mn.apply2(apply26, obj9)));
                                            try {
                                                return $PcStringCopy$Ex(charSequence, intValue, charSequence2, intValue2, ((Number) apply27).intValue());
                                            } catch (ClassCastException e7) {
                                                throw new WrongType(e7, "%string-copy!", 4, apply27);
                                            }
                                        } catch (ClassCastException e8) {
                                            throw new WrongType(e8, "%string-copy!", 3, obj12);
                                        }
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "%string-copy!", 2, obj10);
                                    }
                                } catch (ClassCastException e10) {
                                    throw new WrongType(e10, "%string-copy!", 1, apply26);
                                }
                            } catch (ClassCastException e11) {
                                throw new WrongType(e11, "%string-copy!", 0, obj8);
                            }
                        } catch (ClassCastException e12) {
                            throw new WrongType(e12, "%string-copy!", 4, obj13);
                        }
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "%string-copy!", 3, apply22);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "%string-copy!", 2, obj10);
                }
            } catch (ClassCastException e15) {
                throw new WrongType(e15, "%string-copy!", 1, obj9);
            }
        } catch (ClassCastException e16) {
            throw new WrongType(e16, "%string-copy!", 0, obj8);
        }
    }

    public static Object stringJoin$V(Object obj, Object[] objArr) {
        String str;
        Object obj2;
        LList makeList = LList.makeList(objArr, 0);
        ApplyToArgs applyToArgs = Scheme.applyToArgs;
        try {
            Object obj3 = loc$let$Mnoptionals$St.get();
            ApplyToArgs applyToArgs2 = Scheme.applyToArgs;
            ApplyToArgs applyToArgs3 = Scheme.applyToArgs;
            Location location = loc$delim;
            try {
                try {
                    Object apply3 = applyToArgs3.apply3(location.get(), " ", strings.isString(location.get()) ? Boolean.TRUE : Boolean.FALSE);
                    ApplyToArgs applyToArgs4 = Scheme.applyToArgs;
                    Location location2 = loc$grammar;
                    try {
                        Object obj4 = location2.get();
                        SimpleSymbol simpleSymbol = Lit15;
                        Object apply2 = applyToArgs2.apply2(apply3, applyToArgs4.apply2(obj4, simpleSymbol));
                        if (lists.isPair(obj)) {
                            try {
                                Object obj5 = location2.get();
                                Object apply22 = Scheme.isEqv.apply2(obj5, simpleSymbol);
                                if (apply22 == Boolean.FALSE ? Scheme.isEqv.apply2(obj5, Lit16) != Boolean.FALSE : apply22 != Boolean.FALSE) {
                                    obj2 = lists.cons(lists.car.apply1(obj), lambda222buildit(lists.cdr.apply1(obj), LList.Empty));
                                } else if (Scheme.isEqv.apply2(obj5, Lit17) != Boolean.FALSE) {
                                    obj2 = lambda222buildit(obj, LList.Empty);
                                } else if (Scheme.isEqv.apply2(obj5, Lit18) != Boolean.FALSE) {
                                    try {
                                        obj2 = lists.cons(lists.car.apply1(obj), lambda222buildit(lists.cdr.apply1(obj), LList.list1(location.get())));
                                    } catch (UnboundLocationException e) {
                                        e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1870, 53);
                                        throw e;
                                    }
                                } else {
                                    Object[] objArr2 = new Object[2];
                                    try {
                                        objArr2[0] = location2.get();
                                        objArr2[1] = string$Mnjoin;
                                        obj2 = misc.error$V("Illegal join grammar", objArr2);
                                    } catch (UnboundLocationException e2) {
                                        e2.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1873, 9);
                                        throw e2;
                                    }
                                }
                                str = stringConcatenate(obj2);
                            } catch (UnboundLocationException e3) {
                                e3.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1862, 14);
                                throw e3;
                            }
                        } else if (!lists.isNull(obj)) {
                            str = misc.error$V("STRINGS parameter not list.", new Object[]{obj, string$Mnjoin});
                        } else {
                            try {
                                str = location2.get() == Lit16 ? misc.error$V("Empty list cannot be joined with STRICT-INFIX grammar.", new Object[]{string$Mnjoin}) : "";
                            } catch (UnboundLocationException e4) {
                                e4.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1880, 13);
                                throw e4;
                            }
                        }
                        return applyToArgs.apply4(obj3, makeList, apply2, str);
                    } catch (UnboundLocationException e5) {
                        e5.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1853, 6);
                        throw e5;
                    }
                } catch (UnboundLocationException e6) {
                    e6.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 54);
                    throw e6;
                }
            } catch (UnboundLocationException e7) {
                e7.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 34);
                throw e7;
            }
        } catch (UnboundLocationException e8) {
            e8.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1852, 3);
            throw e8;
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Object[] objArr2 = objArr;
        switch (moduleMethod.selector) {
            case 200:
                Object obj = objArr2[0];
                Object obj2 = objArr2[1];
                int length = objArr2.length - 2;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return substring$SlShared$V(obj, obj2, objArr3);
                    }
                    objArr3[length] = objArr2[length + 2];
                }
            case ErrorMessages.ERROR_NO_CAMERA_PERMISSION:
                Object obj3 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr4 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return stringCopy$V(obj3, objArr4);
                    }
                    objArr4[length2] = objArr2[length2 + 1];
                }
            case 203:
                Object obj4 = objArr2[0];
                Object obj5 = objArr2[1];
                int length3 = objArr2.length - 2;
                Object[] objArr5 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return stringMap$V(obj4, obj5, objArr5);
                    }
                    objArr5[length3] = objArr2[length3 + 2];
                }
            case 205:
                Object obj6 = objArr2[0];
                Object obj7 = objArr2[1];
                int length4 = objArr2.length - 2;
                Object[] objArr6 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return stringMap$Ex$V(obj6, obj7, objArr6);
                    }
                    objArr6[length4] = objArr2[length4 + 2];
                }
            case 207:
                Object obj8 = objArr2[0];
                Object obj9 = objArr2[1];
                Object obj10 = objArr2[2];
                int length5 = objArr2.length - 3;
                Object[] objArr7 = new Object[length5];
                while (true) {
                    length5--;
                    if (length5 < 0) {
                        return stringFold$V(obj8, obj9, obj10, objArr7);
                    }
                    objArr7[length5] = objArr2[length5 + 3];
                }
            case 208:
                Object obj11 = objArr2[0];
                Object obj12 = objArr2[1];
                Object obj13 = objArr2[2];
                int length6 = objArr2.length - 3;
                Object[] objArr8 = new Object[length6];
                while (true) {
                    length6--;
                    if (length6 < 0) {
                        return stringFoldRight$V(obj11, obj12, obj13, objArr8);
                    }
                    objArr8[length6] = objArr2[length6 + 3];
                }
            case 210:
                Object obj14 = objArr2[0];
                Object obj15 = objArr2[1];
                Object obj16 = objArr2[2];
                Object obj17 = objArr2[3];
                int length7 = objArr2.length - 4;
                Object[] objArr9 = new Object[length7];
                while (true) {
                    length7--;
                    if (length7 < 0) {
                        return stringUnfold$V(obj14, obj15, obj16, obj17, objArr9);
                    }
                    objArr9[length7] = objArr2[length7 + 4];
                }
            case 212:
                Object obj18 = objArr2[0];
                Object obj19 = objArr2[1];
                Object obj20 = objArr2[2];
                Object obj21 = objArr2[3];
                int length8 = objArr2.length - 4;
                Object[] objArr10 = new Object[length8];
                while (true) {
                    length8--;
                    if (length8 < 0) {
                        return stringUnfoldRight$V(obj18, obj19, obj20, obj21, objArr10);
                    }
                    objArr10[length8] = objArr2[length8 + 4];
                }
            case 213:
                Object obj22 = objArr2[0];
                Object obj23 = objArr2[1];
                int length9 = objArr2.length - 2;
                Object[] objArr11 = new Object[length9];
                while (true) {
                    length9--;
                    if (length9 < 0) {
                        return stringForEach$V(obj22, obj23, objArr11);
                    }
                    objArr11[length9] = objArr2[length9 + 2];
                }
            case 214:
                Object obj24 = objArr2[0];
                Object obj25 = objArr2[1];
                int length10 = objArr2.length - 2;
                Object[] objArr12 = new Object[length10];
                while (true) {
                    length10--;
                    if (length10 < 0) {
                        return stringForEachIndex$V(obj24, obj25, objArr12);
                    }
                    objArr12[length10] = objArr2[length10 + 2];
                }
            case 215:
                Object obj26 = objArr2[0];
                Object obj27 = objArr2[1];
                int length11 = objArr2.length - 2;
                Object[] objArr13 = new Object[length11];
                while (true) {
                    length11--;
                    if (length11 < 0) {
                        return stringEvery$V(obj26, obj27, objArr13);
                    }
                    objArr13[length11] = objArr2[length11 + 2];
                }
            case 216:
                Object obj28 = objArr2[0];
                Object obj29 = objArr2[1];
                int length12 = objArr2.length - 2;
                Object[] objArr14 = new Object[length12];
                while (true) {
                    length12--;
                    if (length12 < 0) {
                        return stringAny$V(obj28, obj29, objArr14);
                    }
                    objArr14[length12] = objArr2[length12 + 2];
                }
            case 219:
                return $PcStringPrefixLength(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 220:
                return $PcStringSuffixLength(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 221:
                Object obj30 = objArr2[0];
                Object obj31 = objArr2[1];
                try {
                    int intValue = ((Number) obj31).intValue();
                    Object obj32 = objArr2[2];
                    try {
                        int intValue2 = ((Number) obj32).intValue();
                        Object obj33 = objArr2[3];
                        Object obj34 = objArr2[4];
                        try {
                            int intValue3 = ((Number) obj34).intValue();
                            Object obj35 = objArr2[5];
                            try {
                                return Integer.valueOf($PcStringPrefixLengthCi(obj30, intValue, intValue2, obj33, intValue3, ((Number) obj35).intValue()));
                            } catch (ClassCastException e) {
                                throw new WrongType(e, "%string-prefix-length-ci", 6, obj35);
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "%string-prefix-length-ci", 5, obj34);
                        }
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "%string-prefix-length-ci", 3, obj32);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "%string-prefix-length-ci", 2, obj31);
                }
            case 222:
                Object obj36 = objArr2[0];
                Object obj37 = objArr2[1];
                try {
                    int intValue4 = ((Number) obj37).intValue();
                    Object obj38 = objArr2[2];
                    try {
                        int intValue5 = ((Number) obj38).intValue();
                        Object obj39 = objArr2[3];
                        Object obj40 = objArr2[4];
                        try {
                            int intValue6 = ((Number) obj40).intValue();
                            Object obj41 = objArr2[5];
                            try {
                                return Integer.valueOf($PcStringSuffixLengthCi(obj36, intValue4, intValue5, obj39, intValue6, ((Number) obj41).intValue()));
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "%string-suffix-length-ci", 6, obj41);
                            }
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "%string-suffix-length-ci", 5, obj40);
                        }
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "%string-suffix-length-ci", 3, obj38);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "%string-suffix-length-ci", 2, obj37);
                }
            case 223:
                Object obj42 = objArr2[0];
                Object obj43 = objArr2[1];
                int length13 = objArr2.length - 2;
                Object[] objArr15 = new Object[length13];
                while (true) {
                    length13--;
                    if (length13 < 0) {
                        return stringPrefixLength$V(obj42, obj43, objArr15);
                    }
                    objArr15[length13] = objArr2[length13 + 2];
                }
            case 224:
                Object obj44 = objArr2[0];
                Object obj45 = objArr2[1];
                int length14 = objArr2.length - 2;
                Object[] objArr16 = new Object[length14];
                while (true) {
                    length14--;
                    if (length14 < 0) {
                        return stringSuffixLength$V(obj44, obj45, objArr16);
                    }
                    objArr16[length14] = objArr2[length14 + 2];
                }
            case 225:
                Object obj46 = objArr2[0];
                Object obj47 = objArr2[1];
                int length15 = objArr2.length - 2;
                Object[] objArr17 = new Object[length15];
                while (true) {
                    length15--;
                    if (length15 < 0) {
                        return stringPrefixLengthCi$V(obj46, obj47, objArr17);
                    }
                    objArr17[length15] = objArr2[length15 + 2];
                }
            case 226:
                Object obj48 = objArr2[0];
                Object obj49 = objArr2[1];
                int length16 = objArr2.length - 2;
                Object[] objArr18 = new Object[length16];
                while (true) {
                    length16--;
                    if (length16 < 0) {
                        return stringSuffixLengthCi$V(obj48, obj49, objArr18);
                    }
                    objArr18[length16] = objArr2[length16 + 2];
                }
            case 227:
                Object obj50 = objArr2[0];
                Object obj51 = objArr2[1];
                int length17 = objArr2.length - 2;
                Object[] objArr19 = new Object[length17];
                while (true) {
                    length17--;
                    if (length17 < 0) {
                        return isStringPrefix$V(obj50, obj51, objArr19);
                    }
                    objArr19[length17] = objArr2[length17 + 2];
                }
            case 228:
                Object obj52 = objArr2[0];
                Object obj53 = objArr2[1];
                int length18 = objArr2.length - 2;
                Object[] objArr20 = new Object[length18];
                while (true) {
                    length18--;
                    if (length18 < 0) {
                        return isStringSuffix$V(obj52, obj53, objArr20);
                    }
                    objArr20[length18] = objArr2[length18 + 2];
                }
            case 229:
                Object obj54 = objArr2[0];
                Object obj55 = objArr2[1];
                int length19 = objArr2.length - 2;
                Object[] objArr21 = new Object[length19];
                while (true) {
                    length19--;
                    if (length19 < 0) {
                        return isStringPrefixCi$V(obj54, obj55, objArr21);
                    }
                    objArr21[length19] = objArr2[length19 + 2];
                }
            case 230:
                Object obj56 = objArr2[0];
                Object obj57 = objArr2[1];
                int length20 = objArr2.length - 2;
                Object[] objArr22 = new Object[length20];
                while (true) {
                    length20--;
                    if (length20 < 0) {
                        return isStringSuffixCi$V(obj56, obj57, objArr22);
                    }
                    objArr22[length20] = objArr2[length20 + 2];
                }
            case 231:
                return $PcStringPrefix$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 232:
                return $PcStringSuffix$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 233:
                return $PcStringPrefixCi$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 234:
                return $PcStringSuffixCi$Qu(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 235:
                return $PcStringCompare(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            case 236:
                return $PcStringCompareCi(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6], objArr2[7], objArr2[8]);
            case 237:
                Object obj58 = objArr2[0];
                Object obj59 = objArr2[1];
                Object obj60 = objArr2[2];
                Object obj61 = objArr2[3];
                Object obj62 = objArr2[4];
                int length21 = objArr2.length - 5;
                Object[] objArr23 = new Object[length21];
                while (true) {
                    length21--;
                    if (length21 < 0) {
                        return stringCompare$V(obj58, obj59, obj60, obj61, obj62, objArr23);
                    }
                    objArr23[length21] = objArr2[length21 + 5];
                }
            case 238:
                Object obj63 = objArr2[0];
                Object obj64 = objArr2[1];
                Object obj65 = objArr2[2];
                Object obj66 = objArr2[3];
                Object obj67 = objArr2[4];
                int length22 = objArr2.length - 5;
                Object[] objArr24 = new Object[length22];
                while (true) {
                    length22--;
                    if (length22 < 0) {
                        return stringCompareCi$V(obj63, obj64, obj65, obj66, obj67, objArr24);
                    }
                    objArr24[length22] = objArr2[length22 + 5];
                }
            case LispEscapeFormat.ESCAPE_NORMAL:
                Object obj68 = objArr2[0];
                Object obj69 = objArr2[1];
                int length23 = objArr2.length - 2;
                Object[] objArr25 = new Object[length23];
                while (true) {
                    length23--;
                    if (length23 < 0) {
                        return string$Eq$V(obj68, obj69, objArr25);
                    }
                    objArr25[length23] = objArr2[length23 + 2];
                }
            case 243:
                Object obj70 = objArr2[0];
                Object obj71 = objArr2[1];
                int length24 = objArr2.length - 2;
                Object[] objArr26 = new Object[length24];
                while (true) {
                    length24--;
                    if (length24 < 0) {
                        return string$Ls$Gr$V(obj70, obj71, objArr26);
                    }
                    objArr26[length24] = objArr2[length24 + 2];
                }
            case 246:
                Object obj72 = objArr2[0];
                Object obj73 = objArr2[1];
                int length25 = objArr2.length - 2;
                Object[] objArr27 = new Object[length25];
                while (true) {
                    length25--;
                    if (length25 < 0) {
                        return string$Ls$V(obj72, obj73, objArr27);
                    }
                    objArr27[length25] = objArr2[length25 + 2];
                }
            case 249:
                Object obj74 = objArr2[0];
                Object obj75 = objArr2[1];
                int length26 = objArr2.length - 2;
                Object[] objArr28 = new Object[length26];
                while (true) {
                    length26--;
                    if (length26 < 0) {
                        return string$Gr$V(obj74, obj75, objArr28);
                    }
                    objArr28[length26] = objArr2[length26 + 2];
                }
            case Telnet.WILL:
                Object obj76 = objArr2[0];
                Object obj77 = objArr2[1];
                int length27 = objArr2.length - 2;
                Object[] objArr29 = new Object[length27];
                while (true) {
                    length27--;
                    if (length27 < 0) {
                        return string$Ls$Eq$V(obj76, obj77, objArr29);
                    }
                    objArr29[length27] = objArr2[length27 + 2];
                }
            case Telnet.DO:
                Object obj78 = objArr2[0];
                Object obj79 = objArr2[1];
                int length28 = objArr2.length - 2;
                Object[] objArr30 = new Object[length28];
                while (true) {
                    length28--;
                    if (length28 < 0) {
                        return string$Gr$Eq$V(obj78, obj79, objArr30);
                    }
                    objArr30[length28] = objArr2[length28 + 2];
                }
            case 256:
                Object obj80 = objArr2[0];
                Object obj81 = objArr2[1];
                int length29 = objArr2.length - 2;
                Object[] objArr31 = new Object[length29];
                while (true) {
                    length29--;
                    if (length29 < 0) {
                        return stringCi$Eq$V(obj80, obj81, objArr31);
                    }
                    objArr31[length29] = objArr2[length29 + 2];
                }
            case 258:
                Object obj82 = objArr2[0];
                Object obj83 = objArr2[1];
                int length30 = objArr2.length - 2;
                Object[] objArr32 = new Object[length30];
                while (true) {
                    length30--;
                    if (length30 < 0) {
                        return stringCi$Ls$Gr$V(obj82, obj83, objArr32);
                    }
                    objArr32[length30] = objArr2[length30 + 2];
                }
            case 261:
                Object obj84 = objArr2[0];
                Object obj85 = objArr2[1];
                int length31 = objArr2.length - 2;
                Object[] objArr33 = new Object[length31];
                while (true) {
                    length31--;
                    if (length31 < 0) {
                        return stringCi$Ls$V(obj84, obj85, objArr33);
                    }
                    objArr33[length31] = objArr2[length31 + 2];
                }
            case 264:
                Object obj86 = objArr2[0];
                Object obj87 = objArr2[1];
                int length32 = objArr2.length - 2;
                Object[] objArr34 = new Object[length32];
                while (true) {
                    length32--;
                    if (length32 < 0) {
                        return stringCi$Gr$V(obj86, obj87, objArr34);
                    }
                    objArr34[length32] = objArr2[length32 + 2];
                }
            case 266:
                Object obj88 = objArr2[0];
                Object obj89 = objArr2[1];
                int length33 = objArr2.length - 2;
                Object[] objArr35 = new Object[length33];
                while (true) {
                    length33--;
                    if (length33 < 0) {
                        return stringCi$Ls$Eq$V(obj88, obj89, objArr35);
                    }
                    objArr35[length33] = objArr2[length33 + 2];
                }
            case 268:
                Object obj90 = objArr2[0];
                Object obj91 = objArr2[1];
                int length34 = objArr2.length - 2;
                Object[] objArr36 = new Object[length34];
                while (true) {
                    length34--;
                    if (length34 < 0) {
                        return stringCi$Gr$Eq$V(obj90, obj91, objArr36);
                    }
                    objArr36[length34] = objArr2[length34 + 2];
                }
            case 269:
                return $PcStringHash(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
            case 270:
                Object obj92 = objArr2[0];
                int length35 = objArr2.length - 1;
                Object[] objArr37 = new Object[length35];
                while (true) {
                    length35--;
                    if (length35 < 0) {
                        return stringHash$V(obj92, objArr37);
                    }
                    objArr37[length35] = objArr2[length35 + 1];
                }
            case 272:
                Object obj93 = objArr2[0];
                int length36 = objArr2.length - 1;
                Object[] objArr38 = new Object[length36];
                while (true) {
                    length36--;
                    if (length36 < 0) {
                        return stringHashCi$V(obj93, objArr38);
                    }
                    objArr38[length36] = objArr2[length36 + 1];
                }
            case 273:
                Object obj94 = objArr2[0];
                int length37 = objArr2.length - 1;
                Object[] objArr39 = new Object[length37];
                while (true) {
                    length37--;
                    if (length37 < 0) {
                        return stringUpcase$V(obj94, objArr39);
                    }
                    objArr39[length37] = objArr2[length37 + 1];
                }
            case 274:
                Object obj95 = objArr2[0];
                int length38 = objArr2.length - 1;
                Object[] objArr40 = new Object[length38];
                while (true) {
                    length38--;
                    if (length38 < 0) {
                        return stringUpcase$Ex$V(obj95, objArr40);
                    }
                    objArr40[length38] = objArr2[length38 + 1];
                }
            case 275:
                Object obj96 = objArr2[0];
                int length39 = objArr2.length - 1;
                Object[] objArr41 = new Object[length39];
                while (true) {
                    length39--;
                    if (length39 < 0) {
                        return stringDowncase$V(obj96, objArr41);
                    }
                    objArr41[length39] = objArr2[length39 + 1];
                }
            case 276:
                Object obj97 = objArr2[0];
                int length40 = objArr2.length - 1;
                Object[] objArr42 = new Object[length40];
                while (true) {
                    length40--;
                    if (length40 < 0) {
                        return stringDowncase$Ex$V(obj97, objArr42);
                    }
                    objArr42[length40] = objArr2[length40 + 1];
                }
            case 278:
                Object obj98 = objArr2[0];
                int length41 = objArr2.length - 1;
                Object[] objArr43 = new Object[length41];
                while (true) {
                    length41--;
                    if (length41 < 0) {
                        return stringTitlecase$Ex$V(obj98, objArr43);
                    }
                    objArr43[length41] = objArr2[length41 + 1];
                }
            case 279:
                Object obj99 = objArr2[0];
                int length42 = objArr2.length - 1;
                Object[] objArr44 = new Object[length42];
                while (true) {
                    length42--;
                    if (length42 < 0) {
                        return stringTitlecase$V(obj99, objArr44);
                    }
                    objArr44[length42] = objArr2[length42 + 1];
                }
            case 284:
                Object obj100 = objArr2[0];
                int length43 = objArr2.length - 1;
                Object[] objArr45 = new Object[length43];
                while (true) {
                    length43--;
                    if (length43 < 0) {
                        return stringTrim$V(obj100, objArr45);
                    }
                    objArr45[length43] = objArr2[length43 + 1];
                }
            case 285:
                Object obj101 = objArr2[0];
                int length44 = objArr2.length - 1;
                Object[] objArr46 = new Object[length44];
                while (true) {
                    length44--;
                    if (length44 < 0) {
                        return stringTrimRight$V(obj101, objArr46);
                    }
                    objArr46[length44] = objArr2[length44 + 1];
                }
            case 286:
                Object obj102 = objArr2[0];
                int length45 = objArr2.length - 1;
                Object[] objArr47 = new Object[length45];
                while (true) {
                    length45--;
                    if (length45 < 0) {
                        return stringTrimBoth$V(obj102, objArr47);
                    }
                    objArr47[length45] = objArr2[length45 + 1];
                }
            case 288:
                Object obj103 = objArr2[0];
                Object obj104 = objArr2[1];
                int length46 = objArr2.length - 2;
                Object[] objArr48 = new Object[length46];
                while (true) {
                    length46--;
                    if (length46 < 0) {
                        return stringPadRight$V(obj103, obj104, objArr48);
                    }
                    objArr48[length46] = objArr2[length46 + 2];
                }
            case 290:
                Object obj105 = objArr2[0];
                Object obj106 = objArr2[1];
                int length47 = objArr2.length - 2;
                Object[] objArr49 = new Object[length47];
                while (true) {
                    length47--;
                    if (length47 < 0) {
                        return stringPad$V(obj105, obj106, objArr49);
                    }
                    objArr49[length47] = objArr2[length47 + 2];
                }
            case 291:
                Object obj107 = objArr2[0];
                Object obj108 = objArr2[1];
                int length48 = objArr2.length - 2;
                Object[] objArr50 = new Object[length48];
                while (true) {
                    length48--;
                    if (length48 < 0) {
                        return stringDelete$V(obj107, obj108, objArr50);
                    }
                    objArr50[length48] = objArr2[length48 + 2];
                }
            case 292:
                Object obj109 = objArr2[0];
                Object obj110 = objArr2[1];
                int length49 = objArr2.length - 2;
                Object[] objArr51 = new Object[length49];
                while (true) {
                    length49--;
                    if (length49 < 0) {
                        return stringFilter$V(obj109, obj110, objArr51);
                    }
                    objArr51[length49] = objArr2[length49 + 2];
                }
            case 293:
                Object obj111 = objArr2[0];
                Object obj112 = objArr2[1];
                int length50 = objArr2.length - 2;
                Object[] objArr52 = new Object[length50];
                while (true) {
                    length50--;
                    if (length50 < 0) {
                        return stringIndex$V(obj111, obj112, objArr52);
                    }
                    objArr52[length50] = objArr2[length50 + 2];
                }
            case 294:
                Object obj113 = objArr2[0];
                Object obj114 = objArr2[1];
                int length51 = objArr2.length - 2;
                Object[] objArr53 = new Object[length51];
                while (true) {
                    length51--;
                    if (length51 < 0) {
                        return stringIndexRight$V(obj113, obj114, objArr53);
                    }
                    objArr53[length51] = objArr2[length51 + 2];
                }
            case 295:
                Object obj115 = objArr2[0];
                Object obj116 = objArr2[1];
                int length52 = objArr2.length - 2;
                Object[] objArr54 = new Object[length52];
                while (true) {
                    length52--;
                    if (length52 < 0) {
                        return stringSkip$V(obj115, obj116, objArr54);
                    }
                    objArr54[length52] = objArr2[length52 + 2];
                }
            case 296:
                Object obj117 = objArr2[0];
                Object obj118 = objArr2[1];
                int length53 = objArr2.length - 2;
                Object[] objArr55 = new Object[length53];
                while (true) {
                    length53--;
                    if (length53 < 0) {
                        return stringSkipRight$V(obj117, obj118, objArr55);
                    }
                    objArr55[length53] = objArr2[length53 + 2];
                }
            case 297:
                Object obj119 = objArr2[0];
                Object obj120 = objArr2[1];
                int length54 = objArr2.length - 2;
                Object[] objArr56 = new Object[length54];
                while (true) {
                    length54--;
                    if (length54 < 0) {
                        return stringCount$V(obj119, obj120, objArr56);
                    }
                    objArr56[length54] = objArr2[length54 + 2];
                }
            case 298:
                Object obj121 = objArr2[0];
                Object obj122 = objArr2[1];
                int length55 = objArr2.length - 2;
                Object[] objArr57 = new Object[length55];
                while (true) {
                    length55--;
                    if (length55 < 0) {
                        return stringFill$Ex$V(obj121, obj122, objArr57);
                    }
                    objArr57[length55] = objArr2[length55 + 2];
                }
            case 299:
                int length56 = objArr2.length - 3;
                Object obj123 = objArr2[0];
                Object obj124 = objArr2[1];
                try {
                    int intValue7 = ((Number) obj124).intValue();
                    Object obj125 = objArr2[2];
                    try {
                        CharSequence charSequence = (CharSequence) obj125;
                        if (length56 <= 0) {
                            return stringCopy$Ex(obj123, intValue7, charSequence);
                        }
                        int i = length56 - 1;
                        Object obj126 = objArr2[3];
                        try {
                            int intValue8 = ((Number) obj126).intValue();
                            if (i <= 0) {
                                return stringCopy$Ex(obj123, intValue7, charSequence, intValue8);
                            }
                            Object obj127 = objArr2[4];
                            try {
                                return stringCopy$Ex(obj123, intValue7, charSequence, intValue8, ((Number) obj127).intValue());
                            } catch (ClassCastException e9) {
                                throw new WrongType(e9, "string-copy!", 5, obj127);
                            }
                        } catch (ClassCastException e10) {
                            throw new WrongType(e10, "string-copy!", 4, obj126);
                        }
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, "string-copy!", 3, obj125);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "string-copy!", 2, obj124);
                }
            case ErrorMessages.ERROR_TWITTER_BLANK_CONSUMER_KEY_OR_SECRET:
                Object obj128 = objArr2[0];
                try {
                    CharSequence charSequence2 = (CharSequence) obj128;
                    Object obj129 = objArr2[1];
                    try {
                        int intValue9 = ((Number) obj129).intValue();
                        Object obj130 = objArr2[2];
                        try {
                            CharSequence charSequence3 = (CharSequence) obj130;
                            Object obj131 = objArr2[3];
                            try {
                                int intValue10 = ((Number) obj131).intValue();
                                Object obj132 = objArr2[4];
                                try {
                                    return $PcStringCopy$Ex(charSequence2, intValue9, charSequence3, intValue10, ((Number) obj132).intValue());
                                } catch (ClassCastException e13) {
                                    throw new WrongType(e13, "%string-copy!", 5, obj132);
                                }
                            } catch (ClassCastException e14) {
                                throw new WrongType(e14, "%string-copy!", 4, obj131);
                            }
                        } catch (ClassCastException e15) {
                            throw new WrongType(e15, "%string-copy!", 3, obj130);
                        }
                    } catch (ClassCastException e16) {
                        throw new WrongType(e16, "%string-copy!", 2, obj129);
                    }
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "%string-copy!", 1, obj128);
                }
            case ErrorMessages.ERROR_TWITTER_EXCEPTION:
                Object obj133 = objArr2[0];
                Object obj134 = objArr2[1];
                int length57 = objArr2.length - 2;
                Object[] objArr58 = new Object[length57];
                while (true) {
                    length57--;
                    if (length57 < 0) {
                        return stringContains$V(obj133, obj134, objArr58);
                    }
                    objArr58[length57] = objArr2[length57 + 2];
                }
            case ErrorMessages.ERROR_TWITTER_UNABLE_TO_GET_ACCESS_TOKEN:
                Object obj135 = objArr2[0];
                Object obj136 = objArr2[1];
                int length58 = objArr2.length - 2;
                Object[] objArr59 = new Object[length58];
                while (true) {
                    length58--;
                    if (length58 < 0) {
                        return stringContainsCi$V(obj135, obj136, objArr59);
                    }
                    objArr59[length58] = objArr2[length58 + 2];
                }
            case ErrorMessages.ERROR_TWITTER_AUTHORIZATION_FAILED:
                return $PcKmpSearch(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            case ErrorMessages.ERROR_TWITTER_SET_STATUS_FAILED:
                Object obj137 = objArr2[0];
                int length59 = objArr2.length - 1;
                Object[] objArr60 = new Object[length59];
                while (true) {
                    length59--;
                    if (length59 < 0) {
                        return makeKmpRestartVector$V(obj137, objArr60);
                    }
                    objArr60[length59] = objArr2[length59 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_REQUEST_MENTIONS_FAILED:
                return kmpStep(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case ErrorMessages.ERROR_TWITTER_REQUEST_FOLLOWERS_FAILED:
                Object obj138 = objArr2[0];
                Object obj139 = objArr2[1];
                Object obj140 = objArr2[2];
                Object obj141 = objArr2[3];
                int length60 = objArr2.length - 4;
                Object[] objArr61 = new Object[length60];
                while (true) {
                    length60--;
                    if (length60 < 0) {
                        return stringKmpPartialSearch$V(obj138, obj139, obj140, obj141, objArr61);
                    }
                    objArr61[length60] = objArr2[length60 + 4];
                }
            case ErrorMessages.ERROR_TWITTER_DIRECT_MESSAGE_FAILED:
                Object obj142 = objArr2[0];
                int length61 = objArr2.length - 1;
                Object[] objArr62 = new Object[length61];
                while (true) {
                    length61--;
                    if (length61 < 0) {
                        return stringReverse$V(obj142, objArr62);
                    }
                    objArr62[length61] = objArr2[length61 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_FOLLOW_FAILED:
                Object obj143 = objArr2[0];
                int length62 = objArr2.length - 1;
                Object[] objArr63 = new Object[length62];
                while (true) {
                    length62--;
                    if (length62 < 0) {
                        return stringReverse$Ex$V(obj143, objArr63);
                    }
                    objArr63[length62] = objArr2[length62 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_REQUEST_FRIEND_TIMELINE_FAILED:
                Object obj144 = objArr2[0];
                int length63 = objArr2.length - 1;
                Object[] objArr64 = new Object[length63];
                while (true) {
                    length63--;
                    if (length63 < 0) {
                        return string$To$List$V(obj144, objArr64);
                    }
                    objArr64[length63] = objArr2[length63 + 1];
                }
            case ErrorMessages.ERROR_TWITTER_SEARCH_FAILED:
                return stringAppend$SlShared$V(objArr);
            case 317:
                Object obj145 = objArr2[0];
                int length64 = objArr2.length - 1;
                Object[] objArr65 = new Object[length64];
                while (true) {
                    length64--;
                    if (length64 < 0) {
                        return stringConcatenateReverse$V(obj145, objArr65);
                    }
                    objArr65[length64] = objArr2[length64 + 1];
                }
            case 318:
                Object obj146 = objArr2[0];
                int length65 = objArr2.length - 1;
                Object[] objArr66 = new Object[length65];
                while (true) {
                    length65--;
                    if (length65 < 0) {
                        return stringConcatenateReverse$SlShared$V(obj146, objArr66);
                    }
                    objArr66[length65] = objArr2[length65 + 1];
                }
            case ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION:
                Object obj147 = objArr2[0];
                Object obj148 = objArr2[1];
                Object obj149 = objArr2[2];
                Object obj150 = objArr2[3];
                int length66 = objArr2.length - 4;
                Object[] objArr67 = new Object[length66];
                while (true) {
                    length66--;
                    if (length66 < 0) {
                        return stringReplace$V(obj147, obj148, obj149, obj150, objArr67);
                    }
                    objArr67[length66] = objArr2[length66 + 4];
                }
            case 321:
                Object obj151 = objArr2[0];
                int length67 = objArr2.length - 1;
                Object[] objArr68 = new Object[length67];
                while (true) {
                    length67--;
                    if (length67 < 0) {
                        return stringTokenize$V(obj151, objArr68);
                    }
                    objArr68[length67] = objArr2[length67 + 1];
                }
            case 323:
                Object obj152 = objArr2[0];
                Object obj153 = objArr2[1];
                int length68 = objArr2.length - 2;
                Object[] objArr69 = new Object[length68];
                while (true) {
                    length68--;
                    if (length68 < 0) {
                        return xsubstring$V(obj152, obj153, objArr69);
                    }
                    objArr69[length68] = objArr2[length68 + 2];
                }
            case 326:
                Object obj154 = objArr2[0];
                Object obj155 = objArr2[1];
                Object obj156 = objArr2[2];
                Object obj157 = objArr2[3];
                int length69 = objArr2.length - 4;
                Object[] objArr70 = new Object[length69];
                while (true) {
                    length69--;
                    if (length69 < 0) {
                        return stringXcopy$Ex$V(obj154, obj155, obj156, obj157, objArr70);
                    }
                    objArr70[length69] = objArr2[length69 + 4];
                }
            case 327:
                return $PcMultispanRepcopy$Ex(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5], objArr2[6]);
            case 328:
                Object obj158 = objArr2[0];
                int length70 = objArr2.length - 1;
                Object[] objArr71 = new Object[length70];
                while (true) {
                    length70--;
                    if (length70 < 0) {
                        return stringJoin$V(obj158, objArr71);
                    }
                    objArr71[length70] = objArr2[length70 + 1];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }

    public static Object lambda222buildit(Object obj, Object obj2) {
        frame96 frame962 = new frame96();
        frame962.f337final = obj2;
        return frame962.lambda223recur(obj);
    }

    /* compiled from: srfi13.scm */
    public class frame96 extends ModuleBody {

        /* renamed from: final  reason: not valid java name */
        Object f337final;

        public Object lambda223recur(Object obj) {
            if (!lists.isPair(obj)) {
                return this.f337final;
            }
            try {
                return lists.cons(srfi13.loc$delim.get(), lists.cons(lists.car.apply1(obj), lambda223recur(lists.cdr.apply1(obj))));
            } catch (UnboundLocationException e) {
                e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi13.scm", 1857, 13);
                throw e;
            }
        }
    }
}
