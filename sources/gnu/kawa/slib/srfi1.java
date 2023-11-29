package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.ComponentConstants;
import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;

/* compiled from: srfi1.scm */
public class srfi1 extends ModuleBody {
    public static final Macro $Pcevery;
    public static final int $Pcprovide$Pclist$Mnlib = 123;
    public static final int $Pcprovide$Pcsrfi$Mn1 = 123;
    public static final srfi1 $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("tmp").readResolve());
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
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
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
    static final SimpleSymbol Lit6;
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
    static final SimpleSymbol Lit7;
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
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SyntaxRules Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final SimpleSymbol Lit9;
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
    public static final ModuleMethod alist$Mncons;
    public static final ModuleMethod alist$Mncopy;
    public static final ModuleMethod alist$Mndelete;
    public static final ModuleMethod alist$Mndelete$Ex;
    public static final ModuleMethod any;
    public static final ModuleMethod append$Ex;
    public static final ModuleMethod append$Mnmap;
    public static final ModuleMethod append$Mnmap$Ex;
    public static final ModuleMethod append$Mnreverse;
    public static final ModuleMethod append$Mnreverse$Ex;

    /* renamed from: break  reason: not valid java name */
    public static final ModuleMethod f335break;
    public static final ModuleMethod break$Ex;
    public static final ModuleMethod car$Plcdr;
    public static final ModuleMethod circular$Mnlist;
    public static final ModuleMethod circular$Mnlist$Qu;
    public static final ModuleMethod concatenate;
    public static final ModuleMethod concatenate$Ex;
    public static final ModuleMethod cons$St;
    public static final ModuleMethod count;
    public static final ModuleMethod delete;
    public static final ModuleMethod delete$Ex;
    public static final ModuleMethod delete$Mnduplicates;
    public static final ModuleMethod delete$Mnduplicates$Ex;
    public static final ModuleMethod dotted$Mnlist$Qu;
    public static final ModuleMethod drop;
    public static final ModuleMethod drop$Mnright;
    public static final ModuleMethod drop$Mnright$Ex;
    public static final ModuleMethod drop$Mnwhile;
    public static final ModuleMethod eighth;
    public static final ModuleMethod every;
    public static final ModuleMethod fifth;
    public static final ModuleMethod filter;
    public static final ModuleMethod filter$Ex;
    public static final ModuleMethod filter$Mnmap;
    public static final ModuleMethod find;
    public static final ModuleMethod find$Mntail;
    public static GenericProc first;
    public static final ModuleMethod fold;
    public static final ModuleMethod fold$Mnright;
    public static GenericProc fourth;
    public static final ModuleMethod iota;
    static final ModuleMethod lambda$Fn64;
    static final ModuleMethod lambda$Fn78;
    public static final ModuleMethod last;
    public static final ModuleMethod last$Mnpair;
    public static final ModuleMethod length$Pl;
    public static final ModuleMethod list$Eq;
    public static final ModuleMethod list$Mncopy;
    public static final ModuleMethod list$Mnindex;
    public static final ModuleMethod list$Mntabulate;
    public static final ModuleMethod lset$Eq;
    public static final ModuleMethod lset$Ls$Eq;
    public static final ModuleMethod lset$Mnadjoin;
    public static final ModuleMethod lset$Mndiff$Plintersection;
    public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
    public static final ModuleMethod lset$Mndifference;
    public static final ModuleMethod lset$Mndifference$Ex;
    public static final ModuleMethod lset$Mnintersection;
    public static final ModuleMethod lset$Mnintersection$Ex;
    public static final ModuleMethod lset$Mnunion;
    public static final ModuleMethod lset$Mnunion$Ex;
    public static final ModuleMethod lset$Mnxor;
    public static final ModuleMethod lset$Mnxor$Ex;
    public static final ModuleMethod make$Mnlist;
    public static final ModuleMethod map$Ex;
    public static Map map$Mnin$Mnorder;
    public static final ModuleMethod ninth;
    public static final ModuleMethod not$Mnpair$Qu;
    public static final ModuleMethod null$Mnlist$Qu;
    public static final ModuleMethod pair$Mnfold;
    public static final ModuleMethod pair$Mnfold$Mnright;
    public static final ModuleMethod pair$Mnfor$Mneach;
    public static final ModuleMethod partition;
    public static final ModuleMethod partition$Ex;
    public static final ModuleMethod proper$Mnlist$Qu;
    public static final ModuleMethod reduce;
    public static final ModuleMethod reduce$Mnright;
    public static final ModuleMethod remove;
    public static final ModuleMethod remove$Ex;
    public static GenericProc second;
    public static final ModuleMethod seventh;
    public static final ModuleMethod sixth;
    public static final ModuleMethod span;
    public static final ModuleMethod span$Ex;
    public static final ModuleMethod split$Mnat;
    public static final ModuleMethod split$Mnat$Ex;
    public static final ModuleMethod take;
    public static final ModuleMethod take$Ex;
    public static final ModuleMethod take$Mnright;
    public static final ModuleMethod take$Mnwhile;
    public static final ModuleMethod take$Mnwhile$Ex;
    public static final ModuleMethod tenth;
    public static GenericProc third;
    public static final ModuleMethod unfold;
    public static final ModuleMethod unfold$Mnright;
    public static final ModuleMethod unzip1;
    public static final ModuleMethod unzip2;
    public static final ModuleMethod unzip3;
    public static final ModuleMethod unzip4;
    public static final ModuleMethod unzip5;
    public static final ModuleMethod xcons;
    public static final ModuleMethod zip;

    /* compiled from: srfi1.scm */
    public class frame62 extends ModuleBody {
        Object cars$Mnfinal;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("cdr").readResolve();
        Lit104 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("car").readResolve();
        Lit103 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("lp").readResolve();
        Lit102 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("head").readResolve();
        Lit101 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("tail").readResolve();
        Lit100 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("lset-diff+intersection!").readResolve();
        Lit99 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("lset-diff+intersection").readResolve();
        Lit98 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("lset-xor!").readResolve();
        Lit97 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("lset-xor").readResolve();
        Lit96 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("lset-difference!").readResolve();
        Lit95 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("lset-difference").readResolve();
        Lit94 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("lset-intersection!").readResolve();
        Lit93 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("lset-intersection").readResolve();
        Lit92 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("lset-union!").readResolve();
        Lit91 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("lset-union").readResolve();
        Lit90 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol6;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("lset-adjoin").readResolve();
        Lit89 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol7;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("lset=").readResolve();
        Lit88 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol8;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("lset<=").readResolve();
        Lit87 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol9;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("list-index").readResolve();
        Lit86 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol10;
        SimpleSymbol simpleSymbol25 = simpleSymbol11;
        SimpleSymbol simpleSymbol26 = simpleSymbol12;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("%every").readResolve();
        Lit84 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol13;
        SimpleSymbol simpleSymbol29 = simpleSymbol14;
        SimpleSymbol simpleSymbol30 = simpleSymbol17;
        SimpleSymbol simpleSymbol31 = simpleSymbol15;
        SimpleSymbol simpleSymbol32 = simpleSymbol19;
        SyntaxPattern syntaxPattern = new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("null-list?").readResolve();
        Lit14 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol21;
        SimpleSymbol simpleSymbol35 = simpleSymbol23;
        SyntaxRules syntaxRules = new SyntaxRules(new Object[]{simpleSymbol27}, new SyntaxRule[]{new SyntaxRule(syntaxPattern, "\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f¡I\u0011\u0018\u0014\b\u0011\u0018\u001c\b\u000b\b\u0011\u0018$\b\u0011\u0018,\b\u000b\b\u0011\u00184\u0011\u0018<!\t\u0003\u0018D\u0018L", new Object[]{(SimpleSymbol) new SimpleSymbol("let").readResolve(), simpleSymbol3, simpleSymbol4, simpleSymbol2, simpleSymbol5, simpleSymbol, (SimpleSymbol) new SimpleSymbol("and").readResolve(), PairWithPosition.make(simpleSymbol33, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722136), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722124), PairWithPosition.make(simpleSymbol4, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722148), PairWithPosition.make(PairWithPosition.make(simpleSymbol3, PairWithPosition.make(PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722163), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), PairWithPosition.make(PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722174), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154)}, 0)}, 2);
        Lit85 = syntaxRules;
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) new SimpleSymbol("every").readResolve();
        Lit83 = simpleSymbol36;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit82 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) new SimpleSymbol("break!").readResolve();
        Lit81 = simpleSymbol38;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("break").readResolve();
        Lit80 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) new SimpleSymbol("span!").readResolve();
        Lit79 = simpleSymbol40;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("span").readResolve();
        Lit78 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = (SimpleSymbol) new SimpleSymbol("take-while!").readResolve();
        Lit77 = simpleSymbol42;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("drop-while").readResolve();
        Lit76 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = (SimpleSymbol) new SimpleSymbol("take-while").readResolve();
        Lit75 = simpleSymbol44;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("find-tail").readResolve();
        Lit74 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = (SimpleSymbol) new SimpleSymbol("find").readResolve();
        Lit73 = simpleSymbol46;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("alist-delete!").readResolve();
        Lit72 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = simpleSymbol27;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("alist-delete").readResolve();
        Lit71 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = (SimpleSymbol) new SimpleSymbol("alist-copy").readResolve();
        Lit70 = simpleSymbol50;
        SimpleSymbol simpleSymbol51 = simpleSymbol36;
        SimpleSymbol simpleSymbol52 = (SimpleSymbol) new SimpleSymbol("alist-cons").readResolve();
        Lit69 = simpleSymbol52;
        SimpleSymbol simpleSymbol53 = simpleSymbol37;
        SimpleSymbol simpleSymbol54 = (SimpleSymbol) new SimpleSymbol("delete-duplicates!").readResolve();
        Lit68 = simpleSymbol54;
        SimpleSymbol simpleSymbol55 = simpleSymbol38;
        SimpleSymbol simpleSymbol56 = (SimpleSymbol) new SimpleSymbol("delete-duplicates").readResolve();
        Lit67 = simpleSymbol56;
        SimpleSymbol simpleSymbol57 = simpleSymbol39;
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) new SimpleSymbol("delete!").readResolve();
        Lit66 = simpleSymbol58;
        SimpleSymbol simpleSymbol59 = simpleSymbol40;
        SimpleSymbol simpleSymbol60 = (SimpleSymbol) new SimpleSymbol("delete").readResolve();
        Lit65 = simpleSymbol60;
        SimpleSymbol simpleSymbol61 = simpleSymbol41;
        SimpleSymbol simpleSymbol62 = (SimpleSymbol) new SimpleSymbol("remove!").readResolve();
        Lit64 = simpleSymbol62;
        SimpleSymbol simpleSymbol63 = simpleSymbol42;
        SimpleSymbol simpleSymbol64 = (SimpleSymbol) new SimpleSymbol("remove").readResolve();
        Lit63 = simpleSymbol64;
        SimpleSymbol simpleSymbol65 = simpleSymbol43;
        SimpleSymbol simpleSymbol66 = (SimpleSymbol) new SimpleSymbol("partition!").readResolve();
        Lit62 = simpleSymbol66;
        SimpleSymbol simpleSymbol67 = simpleSymbol44;
        SimpleSymbol simpleSymbol68 = (SimpleSymbol) new SimpleSymbol("partition").readResolve();
        Lit61 = simpleSymbol68;
        SimpleSymbol simpleSymbol69 = simpleSymbol45;
        SimpleSymbol simpleSymbol70 = (SimpleSymbol) new SimpleSymbol("filter!").readResolve();
        Lit60 = simpleSymbol70;
        SimpleSymbol simpleSymbol71 = simpleSymbol46;
        SimpleSymbol simpleSymbol72 = (SimpleSymbol) new SimpleSymbol("filter").readResolve();
        Lit59 = simpleSymbol72;
        SimpleSymbol simpleSymbol73 = simpleSymbol47;
        SimpleSymbol simpleSymbol74 = (SimpleSymbol) new SimpleSymbol("filter-map").readResolve();
        Lit58 = simpleSymbol74;
        SimpleSymbol simpleSymbol75 = simpleSymbol49;
        SimpleSymbol simpleSymbol76 = (SimpleSymbol) new SimpleSymbol("map!").readResolve();
        Lit57 = simpleSymbol76;
        SimpleSymbol simpleSymbol77 = simpleSymbol50;
        SimpleSymbol simpleSymbol78 = (SimpleSymbol) new SimpleSymbol("pair-for-each").readResolve();
        Lit56 = simpleSymbol78;
        SimpleSymbol simpleSymbol79 = simpleSymbol52;
        SimpleSymbol simpleSymbol80 = (SimpleSymbol) new SimpleSymbol("append-map!").readResolve();
        Lit55 = simpleSymbol80;
        SimpleSymbol simpleSymbol81 = simpleSymbol54;
        SimpleSymbol simpleSymbol82 = (SimpleSymbol) new SimpleSymbol("append-map").readResolve();
        Lit54 = simpleSymbol82;
        SimpleSymbol simpleSymbol83 = simpleSymbol56;
        SimpleSymbol simpleSymbol84 = (SimpleSymbol) new SimpleSymbol("reduce-right").readResolve();
        Lit53 = simpleSymbol84;
        SimpleSymbol simpleSymbol85 = simpleSymbol58;
        SimpleSymbol simpleSymbol86 = (SimpleSymbol) new SimpleSymbol("reduce").readResolve();
        Lit52 = simpleSymbol86;
        SimpleSymbol simpleSymbol87 = simpleSymbol60;
        SimpleSymbol simpleSymbol88 = (SimpleSymbol) new SimpleSymbol("pair-fold").readResolve();
        Lit51 = simpleSymbol88;
        SimpleSymbol simpleSymbol89 = simpleSymbol62;
        SimpleSymbol simpleSymbol90 = (SimpleSymbol) new SimpleSymbol("pair-fold-right").readResolve();
        Lit50 = simpleSymbol90;
        SimpleSymbol simpleSymbol91 = simpleSymbol64;
        SimpleSymbol simpleSymbol92 = (SimpleSymbol) new SimpleSymbol("fold-right").readResolve();
        Lit49 = simpleSymbol92;
        SimpleSymbol simpleSymbol93 = simpleSymbol66;
        SimpleSymbol simpleSymbol94 = (SimpleSymbol) new SimpleSymbol("fold").readResolve();
        Lit48 = simpleSymbol94;
        SimpleSymbol simpleSymbol95 = simpleSymbol68;
        SimpleSymbol simpleSymbol96 = (SimpleSymbol) new SimpleSymbol("unfold").readResolve();
        Lit47 = simpleSymbol96;
        SimpleSymbol simpleSymbol97 = simpleSymbol70;
        SimpleSymbol simpleSymbol98 = (SimpleSymbol) new SimpleSymbol("unfold-right").readResolve();
        Lit46 = simpleSymbol98;
        SimpleSymbol simpleSymbol99 = simpleSymbol72;
        SimpleSymbol simpleSymbol100 = (SimpleSymbol) new SimpleSymbol("count").readResolve();
        Lit45 = simpleSymbol100;
        SimpleSymbol simpleSymbol101 = simpleSymbol74;
        SimpleSymbol simpleSymbol102 = (SimpleSymbol) new SimpleSymbol("concatenate!").readResolve();
        Lit44 = simpleSymbol102;
        SimpleSymbol simpleSymbol103 = simpleSymbol76;
        SimpleSymbol simpleSymbol104 = (SimpleSymbol) new SimpleSymbol("concatenate").readResolve();
        Lit43 = simpleSymbol104;
        SimpleSymbol simpleSymbol105 = simpleSymbol78;
        SimpleSymbol simpleSymbol106 = (SimpleSymbol) new SimpleSymbol("append-reverse!").readResolve();
        Lit42 = simpleSymbol106;
        SimpleSymbol simpleSymbol107 = simpleSymbol80;
        SimpleSymbol simpleSymbol108 = (SimpleSymbol) new SimpleSymbol("append-reverse").readResolve();
        Lit41 = simpleSymbol108;
        SimpleSymbol simpleSymbol109 = simpleSymbol82;
        SimpleSymbol simpleSymbol110 = (SimpleSymbol) new SimpleSymbol("append!").readResolve();
        Lit40 = simpleSymbol110;
        SimpleSymbol simpleSymbol111 = simpleSymbol84;
        SimpleSymbol simpleSymbol112 = (SimpleSymbol) new SimpleSymbol("unzip5").readResolve();
        Lit39 = simpleSymbol112;
        SimpleSymbol simpleSymbol113 = simpleSymbol86;
        SimpleSymbol simpleSymbol114 = (SimpleSymbol) new SimpleSymbol("unzip4").readResolve();
        Lit38 = simpleSymbol114;
        SimpleSymbol simpleSymbol115 = simpleSymbol88;
        SimpleSymbol simpleSymbol116 = (SimpleSymbol) new SimpleSymbol("unzip3").readResolve();
        Lit37 = simpleSymbol116;
        SimpleSymbol simpleSymbol117 = simpleSymbol90;
        SimpleSymbol simpleSymbol118 = (SimpleSymbol) new SimpleSymbol("unzip2").readResolve();
        Lit36 = simpleSymbol118;
        SimpleSymbol simpleSymbol119 = simpleSymbol92;
        SimpleSymbol simpleSymbol120 = (SimpleSymbol) new SimpleSymbol("unzip1").readResolve();
        Lit35 = simpleSymbol120;
        SimpleSymbol simpleSymbol121 = simpleSymbol94;
        SimpleSymbol simpleSymbol122 = (SimpleSymbol) new SimpleSymbol("last-pair").readResolve();
        Lit34 = simpleSymbol122;
        SimpleSymbol simpleSymbol123 = simpleSymbol96;
        SimpleSymbol simpleSymbol124 = (SimpleSymbol) new SimpleSymbol("last").readResolve();
        Lit33 = simpleSymbol124;
        SimpleSymbol simpleSymbol125 = simpleSymbol98;
        SimpleSymbol simpleSymbol126 = (SimpleSymbol) new SimpleSymbol("split-at!").readResolve();
        Lit32 = simpleSymbol126;
        SimpleSymbol simpleSymbol127 = simpleSymbol100;
        SimpleSymbol simpleSymbol128 = (SimpleSymbol) new SimpleSymbol("split-at").readResolve();
        Lit31 = simpleSymbol128;
        SimpleSymbol simpleSymbol129 = simpleSymbol102;
        SimpleSymbol simpleSymbol130 = (SimpleSymbol) new SimpleSymbol("drop-right!").readResolve();
        Lit30 = simpleSymbol130;
        SimpleSymbol simpleSymbol131 = simpleSymbol104;
        SimpleSymbol simpleSymbol132 = (SimpleSymbol) new SimpleSymbol("drop-right").readResolve();
        Lit29 = simpleSymbol132;
        SimpleSymbol simpleSymbol133 = simpleSymbol106;
        SimpleSymbol simpleSymbol134 = (SimpleSymbol) new SimpleSymbol("take-right").readResolve();
        Lit28 = simpleSymbol134;
        SimpleSymbol simpleSymbol135 = simpleSymbol108;
        SimpleSymbol simpleSymbol136 = (SimpleSymbol) new SimpleSymbol("take!").readResolve();
        Lit27 = simpleSymbol136;
        SimpleSymbol simpleSymbol137 = simpleSymbol110;
        SimpleSymbol simpleSymbol138 = (SimpleSymbol) new SimpleSymbol("drop").readResolve();
        Lit26 = simpleSymbol138;
        SimpleSymbol simpleSymbol139 = simpleSymbol112;
        SimpleSymbol simpleSymbol140 = (SimpleSymbol) new SimpleSymbol("take").readResolve();
        Lit25 = simpleSymbol140;
        SimpleSymbol simpleSymbol141 = simpleSymbol114;
        SimpleSymbol simpleSymbol142 = (SimpleSymbol) new SimpleSymbol("car+cdr").readResolve();
        Lit24 = simpleSymbol142;
        SimpleSymbol simpleSymbol143 = simpleSymbol116;
        SimpleSymbol simpleSymbol144 = (SimpleSymbol) new SimpleSymbol("tenth").readResolve();
        Lit23 = simpleSymbol144;
        SimpleSymbol simpleSymbol145 = simpleSymbol118;
        SimpleSymbol simpleSymbol146 = (SimpleSymbol) new SimpleSymbol("ninth").readResolve();
        Lit22 = simpleSymbol146;
        SimpleSymbol simpleSymbol147 = simpleSymbol120;
        SimpleSymbol simpleSymbol148 = (SimpleSymbol) new SimpleSymbol("eighth").readResolve();
        Lit21 = simpleSymbol148;
        SimpleSymbol simpleSymbol149 = simpleSymbol122;
        SimpleSymbol simpleSymbol150 = (SimpleSymbol) new SimpleSymbol("seventh").readResolve();
        Lit20 = simpleSymbol150;
        SimpleSymbol simpleSymbol151 = simpleSymbol124;
        SimpleSymbol simpleSymbol152 = (SimpleSymbol) new SimpleSymbol("sixth").readResolve();
        Lit19 = simpleSymbol152;
        SimpleSymbol simpleSymbol153 = simpleSymbol126;
        SimpleSymbol simpleSymbol154 = (SimpleSymbol) new SimpleSymbol("fifth").readResolve();
        Lit18 = simpleSymbol154;
        SimpleSymbol simpleSymbol155 = simpleSymbol128;
        SimpleSymbol simpleSymbol156 = (SimpleSymbol) new SimpleSymbol(HeaderParameterNames.ZIP).readResolve();
        Lit17 = simpleSymbol156;
        SimpleSymbol simpleSymbol157 = simpleSymbol130;
        SimpleSymbol simpleSymbol158 = (SimpleSymbol) new SimpleSymbol("length+").readResolve();
        Lit16 = simpleSymbol158;
        SimpleSymbol simpleSymbol159 = simpleSymbol132;
        SimpleSymbol simpleSymbol160 = (SimpleSymbol) new SimpleSymbol("list=").readResolve();
        Lit15 = simpleSymbol160;
        SimpleSymbol simpleSymbol161 = simpleSymbol134;
        SimpleSymbol simpleSymbol162 = (SimpleSymbol) new SimpleSymbol("not-pair?").readResolve();
        Lit13 = simpleSymbol162;
        SimpleSymbol simpleSymbol163 = simpleSymbol136;
        SimpleSymbol simpleSymbol164 = (SimpleSymbol) new SimpleSymbol("circular-list?").readResolve();
        Lit12 = simpleSymbol164;
        SimpleSymbol simpleSymbol165 = simpleSymbol138;
        SimpleSymbol simpleSymbol166 = (SimpleSymbol) new SimpleSymbol("dotted-list?").readResolve();
        Lit11 = simpleSymbol166;
        SimpleSymbol simpleSymbol167 = simpleSymbol140;
        SimpleSymbol simpleSymbol168 = (SimpleSymbol) new SimpleSymbol("proper-list?").readResolve();
        Lit10 = simpleSymbol168;
        SimpleSymbol simpleSymbol169 = simpleSymbol142;
        SimpleSymbol simpleSymbol170 = (SimpleSymbol) new SimpleSymbol("circular-list").readResolve();
        Lit9 = simpleSymbol170;
        SimpleSymbol simpleSymbol171 = simpleSymbol144;
        SimpleSymbol simpleSymbol172 = (SimpleSymbol) new SimpleSymbol("iota").readResolve();
        Lit8 = simpleSymbol172;
        SimpleSymbol simpleSymbol173 = simpleSymbol146;
        SimpleSymbol simpleSymbol174 = (SimpleSymbol) new SimpleSymbol("list-copy").readResolve();
        Lit7 = simpleSymbol174;
        SimpleSymbol simpleSymbol175 = simpleSymbol148;
        SimpleSymbol simpleSymbol176 = (SimpleSymbol) new SimpleSymbol("cons*").readResolve();
        Lit6 = simpleSymbol176;
        SimpleSymbol simpleSymbol177 = simpleSymbol150;
        SimpleSymbol simpleSymbol178 = (SimpleSymbol) new SimpleSymbol("list-tabulate").readResolve();
        Lit5 = simpleSymbol178;
        SimpleSymbol simpleSymbol179 = simpleSymbol152;
        SimpleSymbol simpleSymbol180 = (SimpleSymbol) new SimpleSymbol("make-list").readResolve();
        Lit4 = simpleSymbol180;
        SimpleSymbol simpleSymbol181 = simpleSymbol154;
        SimpleSymbol simpleSymbol182 = (SimpleSymbol) new SimpleSymbol("xcons").readResolve();
        Lit3 = simpleSymbol182;
        SimpleSymbol simpleSymbol183 = simpleSymbol156;
        srfi1 srfi1 = new srfi1();
        $instance = srfi1;
        xcons = new ModuleMethod(srfi1, 78, simpleSymbol182, 8194);
        make$Mnlist = new ModuleMethod(srfi1, 79, simpleSymbol180, -4095);
        list$Mntabulate = new ModuleMethod(srfi1, 80, simpleSymbol178, 8194);
        cons$St = new ModuleMethod(srfi1, 81, simpleSymbol176, -4096);
        list$Mncopy = new ModuleMethod(srfi1, 82, simpleSymbol174, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        iota = new ModuleMethod(srfi1, 83, simpleSymbol172, 12289);
        circular$Mnlist = new ModuleMethod(srfi1, 86, simpleSymbol170, -4095);
        proper$Mnlist$Qu = new ModuleMethod(srfi1, 87, simpleSymbol168, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        dotted$Mnlist$Qu = new ModuleMethod(srfi1, 88, simpleSymbol166, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        circular$Mnlist$Qu = new ModuleMethod(srfi1, 89, simpleSymbol164, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        not$Mnpair$Qu = new ModuleMethod(srfi1, 90, simpleSymbol162, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        null$Mnlist$Qu = new ModuleMethod(srfi1, 91, simpleSymbol33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Eq = new ModuleMethod(srfi1, 92, simpleSymbol160, -4095);
        length$Pl = new ModuleMethod(srfi1, 93, simpleSymbol158, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        zip = new ModuleMethod(srfi1, 94, simpleSymbol183, -4095);
        fifth = new ModuleMethod(srfi1, 95, simpleSymbol181, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sixth = new ModuleMethod(srfi1, 96, simpleSymbol179, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        seventh = new ModuleMethod(srfi1, 97, simpleSymbol177, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        eighth = new ModuleMethod(srfi1, 98, simpleSymbol175, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ninth = new ModuleMethod(srfi1, 99, simpleSymbol173, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tenth = new ModuleMethod(srfi1, 100, simpleSymbol171, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        car$Plcdr = new ModuleMethod(srfi1, 101, simpleSymbol169, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        take = new ModuleMethod(srfi1, 102, simpleSymbol167, 8194);
        drop = new ModuleMethod(srfi1, 103, simpleSymbol165, 8194);
        take$Ex = new ModuleMethod(srfi1, 104, simpleSymbol163, 8194);
        take$Mnright = new ModuleMethod(srfi1, 105, simpleSymbol161, 8194);
        drop$Mnright = new ModuleMethod(srfi1, 106, simpleSymbol159, 8194);
        drop$Mnright$Ex = new ModuleMethod(srfi1, 107, simpleSymbol157, 8194);
        split$Mnat = new ModuleMethod(srfi1, 108, simpleSymbol155, 8194);
        split$Mnat$Ex = new ModuleMethod(srfi1, 109, simpleSymbol153, 8194);
        last = new ModuleMethod(srfi1, 110, simpleSymbol151, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        last$Mnpair = new ModuleMethod(srfi1, 111, simpleSymbol149, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip1 = new ModuleMethod(srfi1, 112, simpleSymbol147, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip2 = new ModuleMethod(srfi1, 113, simpleSymbol145, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip3 = new ModuleMethod(srfi1, 114, simpleSymbol143, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip4 = new ModuleMethod(srfi1, 115, simpleSymbol141, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip5 = new ModuleMethod(srfi1, 116, simpleSymbol139, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        append$Ex = new ModuleMethod(srfi1, 117, simpleSymbol137, -4096);
        append$Mnreverse = new ModuleMethod(srfi1, 118, simpleSymbol135, 8194);
        append$Mnreverse$Ex = new ModuleMethod(srfi1, 119, simpleSymbol133, 8194);
        concatenate = new ModuleMethod(srfi1, 120, simpleSymbol131, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        concatenate$Ex = new ModuleMethod(srfi1, 121, simpleSymbol129, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        count = new ModuleMethod(srfi1, 122, simpleSymbol127, -4094);
        unfold$Mnright = new ModuleMethod(srfi1, 123, simpleSymbol125, 20484);
        unfold = new ModuleMethod(srfi1, ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH, simpleSymbol123, -4092);
        fold = new ModuleMethod(srfi1, 126, simpleSymbol121, -4093);
        fold$Mnright = new ModuleMethod(srfi1, 127, simpleSymbol119, -4093);
        pair$Mnfold$Mnright = new ModuleMethod(srfi1, 128, simpleSymbol117, -4093);
        pair$Mnfold = new ModuleMethod(srfi1, 129, simpleSymbol115, -4093);
        reduce = new ModuleMethod(srfi1, 130, simpleSymbol113, 12291);
        reduce$Mnright = new ModuleMethod(srfi1, 131, simpleSymbol111, 12291);
        append$Mnmap = new ModuleMethod(srfi1, 132, simpleSymbol109, -4094);
        append$Mnmap$Ex = new ModuleMethod(srfi1, 133, simpleSymbol107, -4094);
        pair$Mnfor$Mneach = new ModuleMethod(srfi1, 134, simpleSymbol105, -4094);
        map$Ex = new ModuleMethod(srfi1, 135, simpleSymbol103, -4094);
        filter$Mnmap = new ModuleMethod(srfi1, 136, simpleSymbol101, -4094);
        filter = new ModuleMethod(srfi1, 137, simpleSymbol99, 8194);
        filter$Ex = new ModuleMethod(srfi1, 138, simpleSymbol97, 8194);
        partition = new ModuleMethod(srfi1, 139, simpleSymbol95, 8194);
        partition$Ex = new ModuleMethod(srfi1, 140, simpleSymbol93, 8194);
        remove = new ModuleMethod(srfi1, 141, simpleSymbol91, 8194);
        remove$Ex = new ModuleMethod(srfi1, 142, simpleSymbol89, 8194);
        delete = new ModuleMethod(srfi1, 143, simpleSymbol87, 12290);
        delete$Ex = new ModuleMethod(srfi1, 145, simpleSymbol85, 12290);
        delete$Mnduplicates = new ModuleMethod(srfi1, 147, simpleSymbol83, 8193);
        delete$Mnduplicates$Ex = new ModuleMethod(srfi1, 149, simpleSymbol81, 8193);
        alist$Mncons = new ModuleMethod(srfi1, 151, simpleSymbol79, 12291);
        alist$Mncopy = new ModuleMethod(srfi1, 152, simpleSymbol77, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        alist$Mndelete = new ModuleMethod(srfi1, 153, simpleSymbol75, 12290);
        alist$Mndelete$Ex = new ModuleMethod(srfi1, 155, simpleSymbol73, 12290);
        find = new ModuleMethod(srfi1, 157, simpleSymbol71, 8194);
        find$Mntail = new ModuleMethod(srfi1, 158, simpleSymbol69, 8194);
        take$Mnwhile = new ModuleMethod(srfi1, 159, simpleSymbol67, 8194);
        drop$Mnwhile = new ModuleMethod(srfi1, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, simpleSymbol65, 8194);
        take$Mnwhile$Ex = new ModuleMethod(srfi1, 161, simpleSymbol63, 8194);
        span = new ModuleMethod(srfi1, 162, simpleSymbol61, 8194);
        span$Ex = new ModuleMethod(srfi1, 163, simpleSymbol59, 8194);
        f335break = new ModuleMethod(srfi1, 164, simpleSymbol57, 8194);
        break$Ex = new ModuleMethod(srfi1, 165, simpleSymbol55, 8194);
        any = new ModuleMethod(srfi1, 166, simpleSymbol53, -4094);
        every = new ModuleMethod(srfi1, 167, simpleSymbol51, -4094);
        $Pcevery = Macro.make(simpleSymbol48, syntaxRules, srfi1);
        list$Mnindex = new ModuleMethod(srfi1, 168, simpleSymbol35, -4094);
        lset$Ls$Eq = new ModuleMethod(srfi1, 169, simpleSymbol34, -4095);
        lset$Eq = new ModuleMethod(srfi1, 170, simpleSymbol32, -4095);
        lset$Mnadjoin = new ModuleMethod(srfi1, 171, simpleSymbol30, -4094);
        lset$Mnunion = new ModuleMethod(srfi1, 172, simpleSymbol31, -4095);
        lset$Mnunion$Ex = new ModuleMethod(srfi1, 173, simpleSymbol29, -4095);
        lset$Mnintersection = new ModuleMethod(srfi1, 174, simpleSymbol28, -4094);
        lset$Mnintersection$Ex = new ModuleMethod(srfi1, 175, simpleSymbol26, -4094);
        lset$Mndifference = new ModuleMethod(srfi1, 176, simpleSymbol25, -4094);
        lset$Mndifference$Ex = new ModuleMethod(srfi1, 177, simpleSymbol24, -4094);
        lset$Mnxor = new ModuleMethod(srfi1, 178, simpleSymbol22, -4095);
        lset$Mnxor$Ex = new ModuleMethod(srfi1, 179, simpleSymbol20, -4095);
        lset$Mndiff$Plintersection = new ModuleMethod(srfi1, 180, simpleSymbol18, -4094);
        lset$Mndiff$Plintersection$Ex = new ModuleMethod(srfi1, 181, simpleSymbol16, -4094);
        lambda$Fn64 = new ModuleMethod(srfi1, 182, (Object) null, 8194);
        lambda$Fn78 = new ModuleMethod(srfi1, 183, (Object) null, 8194);
        srfi1.run();
    }

    public srfi1() {
        ModuleInfo.register(this);
    }

    public static Object alistDelete(Object obj, Object obj2) {
        return alistDelete(obj, obj2, Scheme.isEqual);
    }

    public static Object alistDelete$Ex(Object obj, Object obj2) {
        return alistDelete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object delete(Object obj, Object obj2) {
        return delete(obj, obj2, Scheme.isEqual);
    }

    public static Object delete$Ex(Object obj, Object obj2) {
        return delete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object deleteDuplicates(Object obj) {
        return deleteDuplicates(obj, Scheme.isEqual);
    }

    public static Object deleteDuplicates$Ex(Object obj) {
        return deleteDuplicates$Ex(obj, Scheme.isEqual);
    }

    public static Object iota(IntNum intNum) {
        return iota(intNum, Lit0, Lit1);
    }

    public static Object iota(IntNum intNum, Numeric numeric) {
        return iota(intNum, numeric, Lit1);
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj) {
        return unfoldRight(procedure, procedure2, procedure3, obj, LList.Empty);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        first = lists.car;
        second = lists.cadr;
        third = lists.caddr;
        fourth = lists.cadddr;
        map$Mnin$Mnorder = Scheme.map;
    }

    public static Pair xcons(Object obj, Object obj2) {
        return lists.cons(obj2, obj);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 78) {
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i == 80) {
            callContext.value1 = obj;
            if (!(obj2 instanceof Procedure)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (i != 83) {
            if (i == 145) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 147) {
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 149) {
                callContext.value1 = obj;
                if (!(obj2 instanceof Procedure)) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 153) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 155) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 118) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 119) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i == 182) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i != 183) {
                switch (i) {
                    case 102:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 103:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 104:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 105:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 106:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 107:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 108:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    case 109:
                        callContext.value1 = obj;
                        if (IntNum.asIntNumOrNull(obj2) == null) {
                            return -786430;
                        }
                        callContext.value2 = obj2;
                        callContext.proc = moduleMethod;
                        callContext.pc = 2;
                        return 0;
                    default:
                        switch (i) {
                            case 137:
                                if (!(obj instanceof Procedure)) {
                                    return -786431;
                                }
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            case 138:
                                if (!(obj instanceof Procedure)) {
                                    return -786431;
                                }
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            case 139:
                                if (!(obj instanceof Procedure)) {
                                    return -786431;
                                }
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            case 140:
                                if (!(obj instanceof Procedure)) {
                                    return -786431;
                                }
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
                            case 141:
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.proc = moduleMethod;
                                callContext.pc = 2;
                                return 0;
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
                            default:
                                switch (i) {
                                    case 157:
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 158:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 159:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 161:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 162:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 163:
                                        if (!(obj instanceof Procedure)) {
                                            return -786431;
                                        }
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 164:
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    case 165:
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 2;
                                        return 0;
                                    default:
                                        return super.match2(moduleMethod, obj, obj2, callContext);
                                }
                        }
                }
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (IntNum.asIntNumOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (Numeric.asNumericOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object makeList$V(Object obj, Object[] objArr) {
        Object obj2;
        LList makeList = LList.makeList(objArr, 0);
        int i = ((numbers.isInteger(obj) ? 1 : 0) + true) & 1;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0) {
            misc.error$V("make-list arg#1 must be a non-negative integer", new Object[0]);
        }
        if (lists.isNull(makeList)) {
            obj2 = Boolean.FALSE;
        } else if (lists.isNull(lists.cdr.apply1(makeList))) {
            obj2 = lists.car.apply1(makeList);
        } else {
            obj2 = misc.error$V("Too many arguments to MAKE-LIST", new Object[]{lists.cons(obj, makeList)});
        }
        Object obj3 = LList.Empty;
        while (Scheme.numLEq.apply2(obj, Lit0) == Boolean.FALSE) {
            obj = AddOp.$Mn.apply2(obj, Lit1);
            obj3 = lists.cons(obj2, obj3);
        }
        return obj3;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 79) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 81) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 86) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 92) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 94) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 117) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 122) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 123) {
            switch (i) {
                case ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH /*125*/:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 126:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 127:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 128:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 129:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                default:
                    switch (i) {
                        case 132:
                            callContext.values = objArr;
                            callContext.proc = moduleMethod;
                            callContext.pc = 5;
                            return 0;
                        case 133:
                            callContext.values = objArr;
                            callContext.proc = moduleMethod;
                            callContext.pc = 5;
                            return 0;
                        case 134:
                            callContext.values = objArr;
                            callContext.proc = moduleMethod;
                            callContext.pc = 5;
                            return 0;
                        case 135:
                            callContext.values = objArr;
                            callContext.proc = moduleMethod;
                            callContext.pc = 5;
                            return 0;
                        case 136:
                            callContext.values = objArr;
                            callContext.proc = moduleMethod;
                            callContext.pc = 5;
                            return 0;
                        default:
                            switch (i) {
                                case 166:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 167:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 168:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 169:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 170:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 171:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 172:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 173:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 174:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 175:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 176:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 177:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 178:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 179:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 180:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                case 181:
                                    callContext.values = objArr;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 5;
                                    return 0;
                                default:
                                    return super.matchN(moduleMethod, objArr, callContext);
                            }
                    }
            }
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static Object listTabulate(Object obj, Procedure procedure) {
        int i = ((numbers.isInteger(obj) ? 1 : 0) + true) & 1;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0) {
            misc.error$V("list-tabulate arg#1 must be a non-negative integer", new Object[0]);
        }
        Object apply2 = AddOp.$Mn.apply2(obj, Lit1);
        Object obj2 = LList.Empty;
        while (Scheme.numLss.apply2(apply2, Lit0) == Boolean.FALSE) {
            Object apply22 = AddOp.$Mn.apply2(apply2, Lit1);
            obj2 = lists.cons(procedure.apply1(apply2), obj2);
            apply2 = apply22;
        }
        return obj2;
    }

    public static Object cons$St(Object... objArr) {
        return LList.consX(objArr);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 82) {
            if (i != 83) {
                if (i == 93) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i == 147) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i == 149) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i == 152) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i == 120) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i != 121) {
                    switch (i) {
                        case 87:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 88:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 89:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 90:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        case 91:
                            callContext.value1 = obj;
                            callContext.proc = moduleMethod;
                            callContext.pc = 1;
                            return 0;
                        default:
                            switch (i) {
                                case 95:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 96:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 97:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 98:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 99:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 100:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                case 101:
                                    callContext.value1 = obj;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 1;
                                    return 0;
                                default:
                                    switch (i) {
                                        case 110:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 111:
                                            if (!(obj instanceof Pair)) {
                                                return -786431;
                                            }
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 112:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 113:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 114:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 115:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        case 116:
                                            callContext.value1 = obj;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 1;
                                            return 0;
                                        default:
                                            return super.match1(moduleMethod, obj, callContext);
                                    }
                            }
                    }
                } else {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
            } else if (IntNum.asIntNumOrNull(obj) == null) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (!(obj instanceof LList)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static LList listCopy(LList lList) {
        LList lList2 = LList.Empty;
        LList lList3 = LList.Empty;
        while (lists.isPair(lList)) {
            Pair cons = lists.cons(lists.car.apply1(lList), LList.Empty);
            if (lList3 == LList.Empty) {
                lList2 = cons;
            } else {
                try {
                    lists.setCdr$Ex((Pair) lList3, cons);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, (Object) lList3);
                }
            }
            lList = (LList) lists.cdr.apply1(lList);
            lList3 = cons;
        }
        return lList2;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 83) {
            if (i == 143) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 145) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 151) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 153) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i == 155) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (i != 130) {
                if (i != 131) {
                    return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                }
                if (!(obj instanceof Procedure)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            } else if (!(obj instanceof Procedure)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (IntNum.asIntNumOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (Numeric.asNumericOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            if (Numeric.asNumericOrNull(obj3) == null) {
                return -786429;
            }
            callContext.value3 = obj3;
            callContext.proc = moduleMethod;
            callContext.pc = 3;
            return 0;
        }
    }

    public static Object iota(IntNum intNum, Numeric numeric, Numeric numeric2) {
        if (IntNum.compare(intNum, 0) < 0) {
            misc.error$V("Negative step count", new Object[]{iota, intNum});
        }
        Object apply2 = AddOp.$Pl.apply2(numeric, MultiplyOp.$St.apply2(IntNum.add(intNum, -1), numeric2));
        try {
            Object obj = (Numeric) apply2;
            Object obj2 = LList.Empty;
            Object obj3 = intNum;
            while (Scheme.numLEq.apply2(obj3, Lit0) == Boolean.FALSE) {
                Object apply22 = AddOp.$Mn.apply2(obj3, Lit1);
                Object apply23 = AddOp.$Mn.apply2(obj, numeric2);
                obj2 = lists.cons(obj, obj2);
                obj = apply23;
                obj3 = apply22;
            }
            return obj2;
        } catch (ClassCastException e) {
            throw new WrongType(e, "last-val", -2, apply2);
        }
    }

    public static Pair circularList$V(Object obj, Object[] objArr) {
        Pair cons = lists.cons(obj, LList.makeList(objArr, 0));
        Object lastPair = lastPair(cons);
        try {
            lists.setCdr$Ex((Pair) lastPair, cons);
            return cons;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, lastPair);
        }
    }

    public static Object isProperList(Object obj) {
        Object obj2 = obj;
        while (lists.isPair(obj)) {
            Object apply1 = lists.cdr.apply1(obj);
            if (!lists.isPair(apply1)) {
                return lists.isNull(apply1) ? Boolean.TRUE : Boolean.FALSE;
            }
            obj = lists.cdr.apply1(apply1);
            obj2 = lists.cdr.apply1(obj2);
            int i = 1 & ((obj == obj2 ? 1 : 0) + 1);
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return lists.isNull(obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Object isDottedList(Object obj) {
        Object obj2 = obj;
        while (lists.isPair(obj)) {
            Object apply1 = lists.cdr.apply1(obj);
            if (!lists.isPair(apply1)) {
                return lists.isNull(apply1) ? Boolean.FALSE : Boolean.TRUE;
            }
            obj = lists.cdr.apply1(apply1);
            obj2 = lists.cdr.apply1(obj2);
            int i = 1 & ((obj == obj2 ? 1 : 0) + 1);
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return lists.isNull(obj) ? Boolean.FALSE : Boolean.TRUE;
    }

    public static Object isCircularList(Object obj) {
        boolean z;
        Object obj2 = obj;
        do {
            boolean isPair = lists.isPair(obj);
            if (!isPair) {
                return isPair ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply1 = lists.cdr.apply1(obj);
            boolean isPair2 = lists.isPair(apply1);
            if (!isPair2) {
                return isPair2 ? Boolean.TRUE : Boolean.FALSE;
            }
            obj = lists.cdr.apply1(apply1);
            obj2 = lists.cdr.apply1(obj2);
            if (obj == obj2) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
        } while (!z);
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean isNotPair(Object obj) {
        return ((lists.isPair(obj) ? 1 : 0) + true) & true;
    }

    public static Object isNullList(Object obj) {
        if (obj instanceof Pair) {
            return Boolean.FALSE;
        }
        if (obj == LList.Empty) {
            return Boolean.TRUE;
        }
        return misc.error$V("null-list?: argument out of domain", new Object[]{obj});
    }

    public static Object list$Eq$V(Object obj, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        boolean isNull = lists.isNull(makeList);
        if (isNull) {
            return isNull ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            boolean isNull2 = lists.isNull(apply12);
            if (isNull2) {
                return isNull2 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            if (apply1 != apply13) {
                while (isNullList(apply1) == Boolean.FALSE) {
                    Object isNullList = isNullList(apply13);
                    try {
                        int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                        if (i == 0) {
                            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                        }
                        Object apply3 = Scheme.applyToArgs.apply3(obj, lists.car.apply1(apply1), lists.car.apply1(apply13));
                        if (apply3 == Boolean.FALSE) {
                            return apply3;
                        }
                        apply1 = lists.cdr.apply1(apply1);
                        apply13 = lists.cdr.apply1(apply13);
                    } catch (ClassCastException e) {
                        throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, isNullList);
                    }
                }
                Object isNullList2 = isNullList(apply13);
                if (isNullList2 == Boolean.FALSE) {
                    return isNullList2;
                }
            }
            apply1 = apply13;
        }
    }

    public static Object length$Pl(Object obj) {
        Object obj2 = Lit0;
        Object obj3 = obj;
        while (lists.isPair(obj)) {
            Object apply1 = lists.cdr.apply1(obj);
            AddOp addOp = AddOp.$Pl;
            IntNum intNum = Lit1;
            Object apply2 = addOp.apply2(obj2, intNum);
            if (!lists.isPair(apply1)) {
                return apply2;
            }
            obj = lists.cdr.apply1(apply1);
            obj3 = lists.cdr.apply1(obj3);
            obj2 = AddOp.$Pl.apply2(apply2, intNum);
            int i = 1 & ((obj == obj3 ? 1 : 0) + 1);
            if (i == 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return obj2;
    }

    public static Object zip$V(Object obj, Object[] objArr) {
        return Scheme.apply.apply4(Scheme.map, LangObjType.listType, obj, LList.makeList(objArr, 0));
    }

    public static Object fifth(Object obj) {
        return lists.car.apply1(lists.cddddr.apply1(obj));
    }

    public static Object sixth(Object obj) {
        return lists.cadr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object seventh(Object obj) {
        return lists.caddr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object eighth(Object obj) {
        return lists.cadddr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object ninth(Object obj) {
        return lists.car.apply1(lists.cddddr.apply1(lists.cddddr.apply1(obj)));
    }

    public static Object tenth(Object obj) {
        return lists.cadr.apply1(lists.cddddr.apply1(lists.cddddr.apply1(obj)));
    }

    public static Object car$PlCdr(Object obj) {
        return misc.values(lists.car.apply1(obj), lists.cdr.apply1(obj));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object take(java.lang.Object r4, gnu.math.IntNum r5) {
        /*
            gnu.lists.LList r0 = gnu.lists.LList.Empty
        L_0x0002:
            r1 = 1
            r2 = r5
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ ClassCastException -> 0x0036 }
            boolean r2 = kawa.lib.numbers.isZero(r2)
            if (r2 == 0) goto L_0x001c
            gnu.lists.LList r0 = (gnu.lists.LList) r0     // Catch:{ ClassCastException -> 0x0013 }
            gnu.lists.LList r4 = kawa.lib.lists.reverse$Ex(r0)
            return r4
        L_0x0013:
            r4 = move-exception
            gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
            java.lang.String r2 = "reverse!"
            r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r2, (int) r1, (java.lang.Object) r0)
            throw r5
        L_0x001c:
            gnu.expr.GenericProc r1 = kawa.lib.lists.cdr
            java.lang.Object r1 = r1.apply1(r4)
            gnu.kawa.functions.AddOp r2 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r3 = Lit1
            java.lang.Object r5 = r2.apply2(r5, r3)
            gnu.expr.GenericProc r2 = kawa.lib.lists.car
            java.lang.Object r4 = r2.apply1(r4)
            gnu.lists.Pair r0 = kawa.lib.lists.cons(r4, r0)
            r4 = r1
            goto L_0x0002
        L_0x0036:
            r4 = move-exception
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "zero?"
            r0.<init>((java.lang.ClassCastException) r4, (java.lang.String) r2, (int) r1, (java.lang.Object) r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.take(java.lang.Object, gnu.math.IntNum):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object drop(java.lang.Object r3, gnu.math.IntNum r4) {
        /*
        L_0x0000:
            r0 = r4
            java.lang.Number r0 = (java.lang.Number) r0     // Catch:{ ClassCastException -> 0x0019 }
            boolean r0 = kawa.lib.numbers.isZero(r0)
            if (r0 == 0) goto L_0x000a
            return r3
        L_0x000a:
            gnu.expr.GenericProc r0 = kawa.lib.lists.cdr
            java.lang.Object r3 = r0.apply1(r3)
            gnu.kawa.functions.AddOp r0 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r1 = Lit1
            java.lang.Object r4 = r0.apply2(r4, r1)
            goto L_0x0000
        L_0x0019:
            r3 = move-exception
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            r1 = 1
            java.lang.String r2 = "zero?"
            r0.<init>((java.lang.ClassCastException) r3, (java.lang.String) r2, (int) r1, (java.lang.Object) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.drop(java.lang.Object, gnu.math.IntNum):java.lang.Object");
    }

    public static Object take$Ex(Object obj, IntNum intNum) {
        if (numbers.isZero(intNum)) {
            return LList.Empty;
        }
        Object drop2 = drop(obj, IntNum.add(intNum, -1));
        try {
            lists.setCdr$Ex((Pair) drop2, LList.Empty);
            return obj;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, drop2);
        }
    }

    public static Object takeRight(Object obj, IntNum intNum) {
        for (Object drop2 = drop(obj, intNum); lists.isPair(drop2); drop2 = lists.cdr.apply1(drop2)) {
            obj = lists.cdr.apply1(obj);
        }
        return obj;
    }

    public static Object dropRight(Object obj, IntNum intNum) {
        return lambda1recur(obj, drop(obj, intNum));
    }

    public static Object lambda1recur(Object obj, Object obj2) {
        return lists.isPair(obj2) ? lists.cons(lists.car.apply1(obj), lambda1recur(lists.cdr.apply1(obj), lists.cdr.apply1(obj2))) : LList.Empty;
    }

    public static Object dropRight$Ex(Object obj, IntNum intNum) {
        Object drop2 = drop(obj, intNum);
        if (!lists.isPair(drop2)) {
            return LList.Empty;
        }
        Object obj2 = obj;
        for (Object apply1 = lists.cdr.apply1(drop2); lists.isPair(apply1); apply1 = lists.cdr.apply1(apply1)) {
            obj2 = lists.cdr.apply1(obj2);
        }
        try {
            lists.setCdr$Ex((Pair) obj2, LList.Empty);
            return obj;
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, obj2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object splitAt(java.lang.Object r3, gnu.math.IntNum r4) {
        /*
            gnu.lists.LList r0 = gnu.lists.LList.Empty
        L_0x0002:
            r1 = 1
            r2 = r4
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ ClassCastException -> 0x0041 }
            boolean r2 = kawa.lib.numbers.isZero(r2)
            if (r2 == 0) goto L_0x0028
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r2 = 0
            gnu.lists.LList r0 = (gnu.lists.LList) r0     // Catch:{ ClassCastException -> 0x001f }
            gnu.lists.LList r0 = kawa.lib.lists.reverse$Ex(r0)
            r4[r2] = r0
            r4[r1] = r3
            java.lang.Object r3 = kawa.lib.misc.values(r4)
            return r3
        L_0x001f:
            r3 = move-exception
            gnu.mapping.WrongType r4 = new gnu.mapping.WrongType
            java.lang.String r2 = "reverse!"
            r4.<init>((java.lang.ClassCastException) r3, (java.lang.String) r2, (int) r1, (java.lang.Object) r0)
            throw r4
        L_0x0028:
            gnu.expr.GenericProc r1 = kawa.lib.lists.car
            java.lang.Object r1 = r1.apply1(r3)
            gnu.lists.Pair r0 = kawa.lib.lists.cons(r1, r0)
            gnu.expr.GenericProc r1 = kawa.lib.lists.cdr
            java.lang.Object r3 = r1.apply1(r3)
            gnu.kawa.functions.AddOp r1 = gnu.kawa.functions.AddOp.$Mn
            gnu.math.IntNum r2 = Lit1
            java.lang.Object r4 = r1.apply2(r4, r2)
            goto L_0x0002
        L_0x0041:
            r3 = move-exception
            gnu.mapping.WrongType r0 = new gnu.mapping.WrongType
            java.lang.String r2 = "zero?"
            r0.<init>((java.lang.ClassCastException) r3, (java.lang.String) r2, (int) r1, (java.lang.Object) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.splitAt(java.lang.Object, gnu.math.IntNum):java.lang.Object");
    }

    public static Object splitAt$Ex(Object obj, IntNum intNum) {
        if (numbers.isZero(intNum)) {
            return misc.values(LList.Empty, obj);
        }
        Object drop2 = drop(obj, IntNum.add(intNum, -1));
        Object apply1 = lists.cdr.apply1(drop2);
        try {
            lists.setCdr$Ex((Pair) drop2, LList.Empty);
            return misc.values(obj, apply1);
        } catch (ClassCastException e) {
            throw new WrongType(e, "set-cdr!", 1, drop2);
        }
    }

    public static Object last(Object obj) {
        try {
            return lists.car.apply1(lastPair((Pair) obj));
        } catch (ClassCastException e) {
            throw new WrongType(e, "last-pair", 0, obj);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=gnu.lists.Pair, code=java.lang.Object, for r2v0, types: [gnu.lists.Pair] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object lastPair(java.lang.Object r2) {
        /*
        L_0x0000:
            gnu.expr.GenericProc r0 = kawa.lib.lists.cdr
            java.lang.Object r0 = r0.apply1(r2)
            boolean r1 = kawa.lib.lists.isPair(r0)
            if (r1 == 0) goto L_0x000e
            r2 = r0
            goto L_0x0000
        L_0x000e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.lastPair(gnu.lists.Pair):java.lang.Object");
    }

    public static LList unzip1(Object obj) {
        Object obj2 = LList.Empty;
        while (obj != LList.Empty) {
            try {
                Pair pair = (Pair) obj;
                Object cdr = pair.getCdr();
                obj2 = Pair.make(lists.car.apply1(pair.getCar()), obj2);
                obj = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj);
            }
        }
        return LList.reverseInPlace(obj2);
    }

    public static Object unzip2(Object obj) {
        new frame();
        return frame.lambda2recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame extends ModuleBody {
        public static Object lambda2recur(Object obj) {
            frame0 frame0 = new frame0();
            frame0.lis = obj;
            if (srfi1.isNullList(frame0.lis) != Boolean.FALSE) {
                return misc.values(frame0.lis, frame0.lis);
            }
            frame0.elt = lists.car.apply1(frame0.lis);
            return call_with_values.callWithValues(frame0.lambda$Fn1, frame0.lambda$Fn2);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame0 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, (Object) null, 0);
        final ModuleMethod lambda$Fn2;
        Object lis;

        public frame0() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 2, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:627");
            this.lambda$Fn2 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 1 ? lambda3() : super.apply0(moduleMethod);
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
            return moduleMethod.selector == 2 ? lambda4(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda3() {
            return frame.lambda2recur(lists.cdr.apply1(this.lis));
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
        public Object lambda4(Object obj, Object obj2) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), obj), lists.cons(lists.cadr.apply1(this.elt), obj2));
        }
    }

    public static Object unzip3(Object obj) {
        new frame1();
        return frame1.lambda5recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame1 extends ModuleBody {
        public static Object lambda5recur(Object obj) {
            frame2 frame2 = new frame2();
            frame2.lis = obj;
            if (srfi1.isNullList(frame2.lis) != Boolean.FALSE) {
                return misc.values(frame2.lis, frame2.lis, frame2.lis);
            }
            frame2.elt = lists.car.apply1(frame2.lis);
            return call_with_values.callWithValues(frame2.lambda$Fn3, frame2.lambda$Fn4);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame2 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, (Object) null, 0);
        final ModuleMethod lambda$Fn4;
        Object lis;

        public frame2() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 4, (Object) null, 12291);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:635");
            this.lambda$Fn4 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 3 ? lambda6() : super.apply0(moduleMethod);
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
            return moduleMethod.selector == 4 ? lambda7(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
        }

        /* access modifiers changed from: package-private */
        public Object lambda6() {
            return frame1.lambda5recur(lists.cdr.apply1(this.lis));
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
        public Object lambda7(Object obj, Object obj2, Object obj3) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), obj), lists.cons(lists.cadr.apply1(this.elt), obj2), lists.cons(lists.caddr.apply1(this.elt), obj3));
        }
    }

    public static Object unzip4(Object obj) {
        new frame3();
        return frame3.lambda8recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame3 extends ModuleBody {
        public static Object lambda8recur(Object obj) {
            frame4 frame4 = new frame4();
            frame4.lis = obj;
            if (srfi1.isNullList(frame4.lis) != Boolean.FALSE) {
                return misc.values(frame4.lis, frame4.lis, frame4.lis, frame4.lis);
            }
            frame4.elt = lists.car.apply1(frame4.lis);
            return call_with_values.callWithValues(frame4.lambda$Fn5, frame4.lambda$Fn6);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame4 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, (Object) null, 0);
        final ModuleMethod lambda$Fn6;
        Object lis;

        public frame4() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 6, (Object) null, 16388);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:644");
            this.lambda$Fn6 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 5 ? lambda9() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 5) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            return moduleMethod.selector == 6 ? lambda10(obj, obj2, obj3, obj4) : super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda9() {
            return frame3.lambda8recur(lists.cdr.apply1(this.lis));
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            if (moduleMethod.selector != 6) {
                return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.value3 = obj3;
            callContext.value4 = obj4;
            callContext.proc = moduleMethod;
            callContext.pc = 4;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object obj, Object obj2, Object obj3, Object obj4) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), obj), lists.cons(lists.cadr.apply1(this.elt), obj2), lists.cons(lists.caddr.apply1(this.elt), obj3), lists.cons(lists.cadddr.apply1(this.elt), obj4));
        }
    }

    public static Object unzip5(Object obj) {
        new frame5();
        return frame5.lambda11recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame5 extends ModuleBody {
        public static Object lambda11recur(Object obj) {
            frame6 frame6 = new frame6();
            frame6.lis = obj;
            if (srfi1.isNullList(frame6.lis) != Boolean.FALSE) {
                return misc.values(frame6.lis, frame6.lis, frame6.lis, frame6.lis, frame6.lis);
            }
            frame6.elt = lists.car.apply1(frame6.lis);
            return call_with_values.callWithValues(frame6.lambda$Fn7, frame6.lambda$Fn8);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame6 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, (Object) null, 0);
        final ModuleMethod lambda$Fn8;
        Object lis;

        public frame6() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 8, (Object) null, 20485);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:654");
            this.lambda$Fn8 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 7 ? lambda12() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 7) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            if (moduleMethod.selector != 8) {
                return super.applyN(moduleMethod, objArr);
            }
            return lambda13(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12() {
            return frame5.lambda11recur(lists.cdr.apply1(this.lis));
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            if (moduleMethod.selector != 8) {
                return super.matchN(moduleMethod, objArr, callContext);
            }
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda13(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return misc.values(lists.cons(lists.car.apply1(this.elt), obj), lists.cons(lists.cadr.apply1(this.elt), obj2), lists.cons(lists.caddr.apply1(this.elt), obj3), lists.cons(lists.cadddr.apply1(this.elt), obj4), lists.cons(lists.car.apply1(lists.cddddr.apply1(this.elt)), obj5));
        }
    }

    public static Object append$Ex$V(Object[] objArr) {
        Object makeList = LList.makeList(objArr, 0);
        Object obj = LList.Empty;
        while (true) {
            if (!lists.isPair(makeList)) {
                break;
            }
            obj = lists.car.apply1(makeList);
            makeList = lists.cdr.apply1(makeList);
            if (lists.isPair(obj)) {
                try {
                    Object lastPair = lastPair((Pair) obj);
                    while (lists.isPair(makeList)) {
                        Object apply1 = lists.car.apply1(makeList);
                        makeList = lists.cdr.apply1(makeList);
                        try {
                            lists.setCdr$Ex((Pair) lastPair, apply1);
                            if (lists.isPair(apply1)) {
                                try {
                                    lastPair = lastPair((Pair) apply1);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "last-pair", 0, apply1);
                                }
                            }
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "set-cdr!", 1, lastPair);
                        }
                    }
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "last-pair", 0, obj);
                }
            }
        }
        return obj;
    }

    public static Object appendReverse(Object obj, Object obj2) {
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = lists.cdr.apply1(obj);
            obj2 = lists.cons(lists.car.apply1(obj), obj2);
            obj = apply1;
        }
        return obj2;
    }

    public static Object appendReverse$Ex(Object obj, Object obj2) {
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = lists.cdr.apply1(obj);
            try {
                lists.setCdr$Ex((Pair) obj, obj2);
                obj2 = obj;
                obj = apply1;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, obj);
            }
        }
        return obj2;
    }

    public static Object concatenate(Object obj) {
        return reduceRight(append.append, LList.Empty, obj);
    }

    public static Object concatenate$Ex(Object obj) {
        return reduceRight(append$Ex, LList.Empty, obj);
    }

    static Object $PcCdrs(Object obj) {
        Continuation continuation = new Continuation(CallContext.getInstance());
        try {
            frame55 frame552 = new frame55();
            frame552.abort = continuation;
            Object lambda74recur = frame552.lambda74recur(obj);
            continuation.invoked = true;
            return lambda74recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, continuation);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame55 extends ModuleBody {
        Continuation abort;

        public Object lambda74recur(Object obj) {
            if (!lists.isPair(obj)) {
                return LList.Empty;
            }
            Object apply1 = lists.car.apply1(obj);
            if (srfi1.isNullList(apply1) != Boolean.FALSE) {
                return this.abort.apply1(LList.Empty);
            }
            return lists.cons(lists.cdr.apply1(apply1), lambda74recur(lists.cdr.apply1(obj)));
        }
    }

    static Object $PcCars$Pl(Object obj, Object obj2) {
        frame56 frame562 = new frame56();
        frame562.last$Mnelt = obj2;
        return frame562.lambda75recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame56 extends ModuleBody {
        Object last$Mnelt;

        public Object lambda75recur(Object obj) {
            return lists.isPair(obj) ? lists.cons(lists.caar.apply1(obj), lambda75recur(lists.cdr.apply1(obj))) : LList.list1(this.last$Mnelt);
        }
    }

    static Object $PcCars$PlCdrs(Object obj) {
        Continuation continuation = new Continuation(CallContext.getInstance());
        try {
            frame57 frame572 = new frame57();
            frame572.abort = continuation;
            Object lambda76recur = frame572.lambda76recur(obj);
            continuation.invoked = true;
            return lambda76recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, continuation);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame57 extends ModuleBody {
        Continuation abort;

        public Object lambda76recur(Object obj) {
            frame58 frame58 = new frame58();
            frame58.staticLink = this;
            frame58.lists = obj;
            if (lists.isPair(frame58.lists)) {
                return call_with_values.callWithValues(frame58.lambda$Fn57, frame58.lambda$Fn58);
            }
            return misc.values(LList.Empty, LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame58 extends ModuleBody {
        final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, (Object) null, 0);
        final ModuleMethod lambda$Fn58;
        Object lists;
        frame57 staticLink;

        public frame58() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 62, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:762");
            this.lambda$Fn58 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 61 ? lambda77() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 61) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 62 ? lambda78(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda77() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda78(Object obj, Object obj2) {
            frame59 frame59 = new frame59();
            frame59.staticLink = this;
            frame59.list = obj;
            frame59.other$Mnlists = obj2;
            if (srfi1.isNullList(frame59.list) != Boolean.FALSE) {
                return this.staticLink.abort.apply2(LList.Empty, LList.Empty);
            }
            return call_with_values.callWithValues(frame59.lambda$Fn59, frame59.lambda$Fn60);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 62) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, (Object) null, 0);
        final ModuleMethod lambda$Fn60;
        Object list;
        Object other$Mnlists;
        frame58 staticLink;

        public frame59() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 60, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
            this.lambda$Fn60 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 59 ? lambda79() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 59) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 60 ? lambda80(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda79() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda80(Object obj, Object obj2) {
            frame60 frame60 = new frame60();
            frame60.staticLink = this;
            frame60.a = obj;
            frame60.d = obj2;
            return call_with_values.callWithValues(frame60.lambda$Fn61, frame60.lambda$Fn62);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 60) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame60 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, (Object) null, 0);
        final ModuleMethod lambda$Fn62;
        frame59 staticLink;

        public frame60() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 58, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
            this.lambda$Fn62 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 57 ? lambda81() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 57) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 58 ? lambda82(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda81() {
            return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 58) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda82(Object obj, Object obj2) {
            return misc.values(lists.cons(this.a, obj), lists.cons(this.d, obj2));
        }
    }

    /* compiled from: srfi1.scm */
    public class frame61 extends ModuleBody {
        final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 63, (Object) null, 0);
        Object lists;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 63 ? lambda83() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 63) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        static Pair lambda84(Object obj, Object obj2) {
            return lists.cons(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda83() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlPair(Object obj) {
        frame61 frame612 = new frame61();
        frame612.lists = obj;
        return call_with_values.callWithValues(frame612.lambda$Fn63, lambda$Fn64);
    }

    static Object $PcCars$PlCdrs$Pl(Object obj, Object obj2) {
        frame62 frame622 = new frame62();
        frame622.cars$Mnfinal = obj2;
        Continuation continuation = new Continuation(CallContext.getInstance());
        try {
            frame63 frame632 = new frame63();
            frame632.staticLink = frame622;
            frame632.abort = continuation;
            Object lambda85recur = frame632.lambda85recur(obj);
            continuation.invoked = true;
            return lambda85recur;
        } catch (Throwable th) {
            return Continuation.handleException(th, continuation);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame63 extends ModuleBody {
        Continuation abort;
        frame62 staticLink;

        public Object lambda85recur(Object obj) {
            frame64 frame64 = new frame64();
            frame64.staticLink = this;
            frame64.lists = obj;
            if (lists.isPair(frame64.lists)) {
                return call_with_values.callWithValues(frame64.lambda$Fn65, frame64.lambda$Fn66);
            }
            return misc.values(LList.list1(this.staticLink.cars$Mnfinal), LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame64 extends ModuleBody {
        final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, (Object) null, 0);
        final ModuleMethod lambda$Fn66;
        Object lists;
        frame63 staticLink;

        public frame64() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 69, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:783");
            this.lambda$Fn66 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 68 ? lambda86() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 68) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 69 ? lambda87(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda86() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda87(Object obj, Object obj2) {
            frame65 frame65 = new frame65();
            frame65.staticLink = this;
            frame65.list = obj;
            frame65.other$Mnlists = obj2;
            if (srfi1.isNullList(frame65.list) != Boolean.FALSE) {
                return this.staticLink.abort.apply2(LList.Empty, LList.Empty);
            }
            return call_with_values.callWithValues(frame65.lambda$Fn67, frame65.lambda$Fn68);
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
    }

    /* compiled from: srfi1.scm */
    public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, (Object) null, 0);
        final ModuleMethod lambda$Fn68;
        Object list;
        Object other$Mnlists;
        frame64 staticLink;

        public frame65() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 67, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
            this.lambda$Fn68 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 66 ? lambda88() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 66) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 67 ? lambda89(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda88() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda89(Object obj, Object obj2) {
            frame66 frame66 = new frame66();
            frame66.staticLink = this;
            frame66.a = obj;
            frame66.d = obj2;
            return call_with_values.callWithValues(frame66.lambda$Fn69, frame66.lambda$Fn70);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 67) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame66 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, (Object) null, 0);
        final ModuleMethod lambda$Fn70;
        frame65 staticLink;

        public frame66() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 65, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
            this.lambda$Fn70 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 64 ? lambda90() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 64) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 65 ? lambda91(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda90() {
            return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
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
        public Object lambda91(Object obj, Object obj2) {
            return misc.values(lists.cons(this.a, obj), lists.cons(this.d, obj2));
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest(Object obj) {
        new frame67();
        return frame67.lambda92recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame67 extends ModuleBody {
        public static Object lambda92recur(Object obj) {
            frame68 frame68 = new frame68();
            frame68.lists = obj;
            if (lists.isPair(frame68.lists)) {
                return call_with_values.callWithValues(frame68.lambda$Fn71, frame68.lambda$Fn72);
            }
            return misc.values(LList.Empty, LList.Empty);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame68 extends ModuleBody {
        final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, (Object) null, 0);
        final ModuleMethod lambda$Fn72;
        Object lists;

        public frame68() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 75, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:794");
            this.lambda$Fn72 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 74 ? lambda93() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 74) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 75 ? lambda94(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda93() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda94(Object obj, Object obj2) {
            frame69 frame69 = new frame69();
            frame69.staticLink = this;
            frame69.list = obj;
            frame69.other$Mnlists = obj2;
            return call_with_values.callWithValues(frame69.lambda$Fn73, frame69.lambda$Fn74);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 75) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, (Object) null, 0);
        final ModuleMethod lambda$Fn74;
        Object list;
        Object other$Mnlists;
        frame68 staticLink;

        public frame69() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 73, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
            this.lambda$Fn74 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 72 ? lambda95() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 72) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 73 ? lambda96(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda95() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda96(Object obj, Object obj2) {
            frame70 frame70 = new frame70();
            frame70.staticLink = this;
            frame70.a = obj;
            frame70.d = obj2;
            return call_with_values.callWithValues(frame70.lambda$Fn75, frame70.lambda$Fn76);
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
    }

    /* compiled from: srfi1.scm */
    public class frame70 extends ModuleBody {
        Object a;
        Object d;
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, (Object) null, 0);
        final ModuleMethod lambda$Fn76;
        frame69 staticLink;

        public frame70() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 71, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
            this.lambda$Fn76 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 70 ? lambda97() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 70) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 71 ? lambda98(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda97() {
            return frame67.lambda92recur(this.staticLink.other$Mnlists);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 71) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda98(Object obj, Object obj2) {
            return misc.values(lists.cons(this.a, obj), lists.cons(this.d, obj2));
        }
    }

    /* compiled from: srfi1.scm */
    public class frame71 extends ModuleBody {
        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 76, (Object) null, 0);
        Object lists;

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 76 ? lambda99() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 76) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        static Pair lambda100(Object obj, Object obj2) {
            return lists.cons(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda99() {
            return srfi1.$PcCars$PlCdrs$SlNoTest(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object obj) {
        frame71 frame712 = new frame71();
        frame712.lists = obj;
        return call_with_values.callWithValues(frame712.lambda$Fn77, lambda$Fn78);
    }

    public static Object count$V(Procedure procedure, Object obj, Object[] objArr) {
        IntNum intNum;
        Object makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            intNum = Lit0;
            while (isNullList(obj) == Boolean.FALSE) {
                Object $PcCars$PlCdrs$SlPair = $PcCars$PlCdrs$SlPair(makeList);
                Object apply1 = lists.car.apply1($PcCars$PlCdrs$SlPair);
                makeList = lists.cdr.apply1($PcCars$PlCdrs$SlPair);
                if (lists.isNull(apply1)) {
                    break;
                }
                Object apply12 = lists.cdr.apply1(obj);
                if (Scheme.apply.apply3(procedure, lists.car.apply1(obj), apply1) != Boolean.FALSE) {
                    intNum = AddOp.$Pl.apply2(intNum, Lit1);
                }
                obj = apply12;
            }
        } else {
            intNum = Lit0;
            while (isNullList(obj) == Boolean.FALSE) {
                Object apply13 = lists.cdr.apply1(obj);
                if (procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE) {
                    intNum = AddOp.$Pl.apply2(intNum, Lit1);
                }
                obj = apply13;
            }
        }
        return intNum;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        if (moduleMethod.selector != 123) {
            return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
        }
        try {
            try {
                try {
                    return unfoldRight((Procedure) obj, (Procedure) obj2, (Procedure) obj3, obj4);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "unfold-right", 3, obj3);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "unfold-right", 2, obj2);
            }
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "unfold-right", 1, obj);
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        if (moduleMethod.selector != 123) {
            return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
        }
        if (!(obj instanceof Procedure)) {
            return -786431;
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
        callContext.value4 = obj4;
        callContext.proc = moduleMethod;
        callContext.pc = 4;
        return 0;
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj, Object obj2) {
        while (procedure.apply1(obj) == Boolean.FALSE) {
            Object apply1 = procedure3.apply1(obj);
            obj2 = lists.cons(procedure2.apply1(obj), obj2);
            obj = apply1;
        }
        return obj2;
    }

    public static Object unfold$V(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            Object apply1 = lists.car.apply1(makeList);
            if (lists.isPair(lists.cdr.apply1(makeList))) {
                return Scheme.apply.applyN(new Object[]{misc.error, "Too many arguments", unfold, procedure, procedure2, procedure3, obj, makeList});
            }
            Object obj2 = LList.Empty;
            while (procedure.apply1(obj) == Boolean.FALSE) {
                Object apply12 = procedure3.apply1(obj);
                obj2 = lists.cons(procedure2.apply1(obj), obj2);
                obj = apply12;
            }
            return appendReverse$Ex(obj2, Scheme.applyToArgs.apply2(apply1, obj));
        }
        LList lList = LList.Empty;
        while (procedure.apply1(obj) == Boolean.FALSE) {
            Object apply13 = procedure3.apply1(obj);
            lList = lists.cons(procedure2.apply1(obj), lList);
            obj = apply13;
        }
        try {
            return lists.reverse$Ex(lList);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, (Object) lList);
        }
    }

    public static Object fold$V(Procedure procedure, Object obj, Object obj2, Object[] objArr) {
        frame7 frame73 = new frame7();
        frame73.kons = procedure;
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return frame73.lambda14lp(lists.cons(obj2, makeList), obj);
        }
        while (isNullList(obj2) == Boolean.FALSE) {
            Object apply1 = lists.cdr.apply1(obj2);
            obj = frame73.kons.apply2(lists.car.apply1(obj2), obj);
            obj2 = apply1;
        }
        return obj;
    }

    /* compiled from: srfi1.scm */
    public class frame7 extends ModuleBody {
        Procedure kons;

        public Object lambda14lp(Object obj, Object obj2) {
            frame8 frame8 = new frame8();
            frame8.staticLink = this;
            frame8.lists = obj;
            frame8.ans = obj2;
            return call_with_values.callWithValues(frame8.lambda$Fn9, frame8.lambda$Fn10);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame8 extends ModuleBody {
        Object ans;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, (Object) null, 0);
        Object lists;
        frame7 staticLink;

        public frame8() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 10, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:859");
            this.lambda$Fn10 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 9 ? lambda15() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 9) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 10 ? lambda16(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15() {
            return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 10) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object obj, Object obj2) {
            if (lists.isNull(obj)) {
                return this.ans;
            }
            return this.staticLink.lambda14lp(obj2, Scheme.apply.apply2(this.staticLink.kons, obj));
        }
    }

    public static Object foldRight$V(Procedure procedure, Object obj, Object obj2, Object[] objArr) {
        frame9 frame92 = new frame9();
        frame92.kons = procedure;
        frame92.knil = obj;
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return frame92.lambda17recur(lists.cons(obj2, makeList));
        }
        return frame92.lambda18recur(obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame9 extends ModuleBody {
        Object knil;
        Procedure kons;

        public Object lambda17recur(Object obj) {
            Object $PcCdrs = srfi1.$PcCdrs(obj);
            if (lists.isNull($PcCdrs)) {
                return this.knil;
            }
            return Scheme.apply.apply2(this.kons, srfi1.$PcCars$Pl(obj, lambda17recur($PcCdrs)));
        }

        public Object lambda18recur(Object obj) {
            if (srfi1.isNullList(obj) != Boolean.FALSE) {
                return this.knil;
            }
            return this.kons.apply2(lists.car.apply1(obj), lambda18recur(lists.cdr.apply1(obj)));
        }
    }

    public static Object pairFoldRight$V(Procedure procedure, Object obj, Object obj2, Object[] objArr) {
        frame10 frame102 = new frame10();
        frame102.f = procedure;
        frame102.zero = obj;
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return frame102.lambda19recur(lists.cons(obj2, makeList));
        }
        return frame102.lambda20recur(obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame10 extends ModuleBody {
        Procedure f;
        Object zero;

        public Object lambda19recur(Object obj) {
            Object $PcCdrs = srfi1.$PcCdrs(obj);
            if (lists.isNull($PcCdrs)) {
                return this.zero;
            }
            return Scheme.apply.apply2(this.f, srfi1.append$Ex$V(new Object[]{obj, LList.list1(lambda19recur($PcCdrs))}));
        }

        public Object lambda20recur(Object obj) {
            return srfi1.isNullList(obj) != Boolean.FALSE ? this.zero : this.f.apply2(obj, lambda20recur(lists.cdr.apply1(obj)));
        }
    }

    public static Object pairFold$V(Procedure procedure, Object obj, Object obj2, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            Object cons = lists.cons(obj2, makeList);
            while (true) {
                Object $PcCdrs = $PcCdrs(cons);
                if (lists.isNull($PcCdrs)) {
                    break;
                }
                obj = Scheme.apply.apply2(procedure, append$Ex$V(new Object[]{cons, LList.list1(obj)}));
                cons = $PcCdrs;
            }
        } else {
            while (isNullList(obj2) == Boolean.FALSE) {
                Object apply1 = lists.cdr.apply1(obj2);
                obj = procedure.apply2(obj2, obj);
                obj2 = apply1;
            }
        }
        return obj;
    }

    public static Object reduce(Procedure procedure, Object obj, Object obj2) {
        return isNullList(obj2) != Boolean.FALSE ? obj : fold$V(procedure, lists.car.apply1(obj2), lists.cdr.apply1(obj2), new Object[0]);
    }

    public static Object reduceRight(Procedure procedure, Object obj, Object obj2) {
        frame11 frame112 = new frame11();
        frame112.f = procedure;
        return isNullList(obj2) != Boolean.FALSE ? obj : frame112.lambda21recur(lists.car.apply1(obj2), lists.cdr.apply1(obj2));
    }

    /* compiled from: srfi1.scm */
    public class frame11 extends ModuleBody {
        Procedure f;

        public Object lambda21recur(Object obj, Object obj2) {
            return lists.isPair(obj2) ? this.f.apply2(obj, lambda21recur(lists.car.apply1(obj2), lists.cdr.apply1(obj2))) : obj;
        }
    }

    public static Object appendMap$V(Object obj, Object obj2, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, obj, obj2, makeList));
        }
        Apply apply = Scheme.apply;
        append append = append.append;
        Object obj3 = LList.Empty;
        while (obj2 != LList.Empty) {
            try {
                Pair pair = (Pair) obj2;
                Object cdr = pair.getCdr();
                obj3 = Pair.make(Scheme.applyToArgs.apply2(obj, pair.getCar()), obj3);
                obj2 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj2);
            }
        }
        return apply.apply2(append, LList.reverseInPlace(obj3));
    }

    public static Object appendMap$Ex$V(Object obj, Object obj2, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, obj, obj2, makeList));
        }
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = append$Ex;
        Object obj3 = LList.Empty;
        while (obj2 != LList.Empty) {
            try {
                Pair pair = (Pair) obj2;
                Object cdr = pair.getCdr();
                obj3 = Pair.make(Scheme.applyToArgs.apply2(obj, pair.getCar()), obj3);
                obj2 = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj2);
            }
        }
        return apply.apply2(moduleMethod, LList.reverseInPlace(obj3));
    }

    public static Object pairForEach$V(Procedure procedure, Object obj, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            Object cons = lists.cons(obj, makeList);
            while (true) {
                Object $PcCdrs = $PcCdrs(cons);
                if (!lists.isPair($PcCdrs)) {
                    return Values.empty;
                }
                Scheme.apply.apply2(procedure, cons);
                cons = $PcCdrs;
            }
        } else {
            while (isNullList(obj) == Boolean.FALSE) {
                Object apply1 = lists.cdr.apply1(obj);
                procedure.apply1(obj);
                obj = apply1;
            }
            return Values.empty;
        }
    }

    public static Object map$Ex$V(Procedure procedure, Object obj, Object[] objArr) {
        frame12 frame122 = new frame12();
        frame122.f = procedure;
        Object makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            Object obj2 = obj;
            while (isNullList(obj2) == Boolean.FALSE) {
                Object $PcCars$PlCdrs$SlNoTest$SlPair = $PcCars$PlCdrs$SlNoTest$SlPair(makeList);
                Object apply1 = lists.car.apply1($PcCars$PlCdrs$SlNoTest$SlPair);
                makeList = lists.cdr.apply1($PcCars$PlCdrs$SlNoTest$SlPair);
                try {
                    lists.setCar$Ex((Pair) obj2, Scheme.apply.apply3(frame122.f, lists.car.apply1(obj2), apply1));
                    obj2 = lists.cdr.apply1(obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-car!", 1, obj2);
                }
            }
        } else {
            pairForEach$V(frame122.lambda$Fn11, obj, new Object[0]);
        }
        return obj;
    }

    /* compiled from: srfi1.scm */
    public class frame12 extends ModuleBody {
        Procedure f;
        final ModuleMethod lambda$Fn11;

        public frame12() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:961");
            this.lambda$Fn11 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector != 11) {
                return super.apply1(moduleMethod, obj);
            }
            lambda22(obj);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda22(Object obj) {
            try {
                lists.setCar$Ex((Pair) obj, this.f.apply1(lists.car.apply1(obj)));
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-car!", 1, obj);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 11) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object filterMap$V(Procedure procedure, Object obj, Object[] objArr) {
        frame13 frame132 = new frame13();
        frame132.f = procedure;
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return frame132.lambda23recur(lists.cons(obj, makeList), LList.Empty);
        }
        LList lList = LList.Empty;
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = frame132.f.apply1(lists.car.apply1(obj));
            obj = lists.cdr.apply1(obj);
            if (apply1 != Boolean.FALSE) {
                lList = lists.cons(apply1, lList);
            }
        }
        try {
            return lists.reverse$Ex(lList);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, (Object) lList);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame13 extends ModuleBody {
        Procedure f;

        public Object lambda23recur(Object obj, Object obj2) {
            frame14 frame14 = new frame14();
            frame14.staticLink = this;
            frame14.lists = obj;
            frame14.res = obj2;
            return call_with_values.callWithValues(frame14.lambda$Fn12, frame14.lambda$Fn13);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame14 extends ModuleBody {
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, (Object) null, 0);
        final ModuleMethod lambda$Fn13;
        Object lists;
        Object res;
        frame13 staticLink;

        public frame14() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 13, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:969");
            this.lambda$Fn13 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 12 ? lambda24() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 12) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 13 ? lambda25(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda24() {
            return srfi1.$PcCars$PlCdrs(this.lists);
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
        public Object lambda25(Object obj, Object obj2) {
            if (srfi1.isNotPair(obj)) {
                Object obj3 = this.res;
                try {
                    return lists.reverse$Ex((LList) obj3);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "reverse!", 1, obj3);
                }
            } else {
                Object apply2 = Scheme.apply.apply2(this.staticLink.f, obj);
                if (apply2 != Boolean.FALSE) {
                    return this.staticLink.lambda23recur(obj2, lists.cons(apply2, this.res));
                }
                return this.staticLink.lambda23recur(obj2, this.res);
            }
        }
    }

    public static Object filter(Procedure procedure, Object obj) {
        LList lList = LList.Empty;
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = lists.car.apply1(obj);
            obj = lists.cdr.apply1(obj);
            if (procedure.apply1(apply1) != Boolean.FALSE) {
                lList = lists.cons(apply1, lList);
            }
        }
        try {
            return lists.reverse$Ex(lList);
        } catch (ClassCastException e) {
            throw new WrongType(e, "reverse!", 1, (Object) lList);
        }
    }

    public static Object filter$Ex(Procedure procedure, Object obj) {
        Object obj2;
        Object apply1;
        while (true) {
            if (isNullList(obj) != Boolean.FALSE) {
                break;
            } else if (procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE) {
                obj = lists.cdr.apply1(obj);
            } else {
                Object apply12 = lists.cdr.apply1(obj);
                Object obj3 = obj;
                loop1:
                while (true) {
                    if (!lists.isPair(obj2)) {
                        break;
                    }
                    if (procedure.apply1(lists.car.apply1(obj2)) != Boolean.FALSE) {
                        apply1 = lists.cdr.apply1(obj2);
                    } else {
                        obj2 = lists.cdr.apply1(obj2);
                        while (lists.isPair(obj2)) {
                            if (procedure.apply1(lists.car.apply1(obj2)) != Boolean.FALSE) {
                                try {
                                    lists.setCdr$Ex((Pair) obj3, obj2);
                                    apply1 = lists.cdr.apply1(obj2);
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "set-cdr!", 1, obj3);
                                }
                            } else {
                                obj2 = lists.cdr.apply1(obj2);
                            }
                        }
                        try {
                            lists.setCdr$Ex((Pair) obj3, obj2);
                            break loop1;
                        } catch (ClassCastException e2) {
                            throw new WrongType(e2, "set-cdr!", 1, obj3);
                        }
                    }
                    Object obj4 = apply1;
                    obj3 = obj2;
                    apply12 = obj4;
                }
            }
        }
        return obj;
    }

    public static Object partition(Procedure procedure, Object obj) {
        LList lList = LList.Empty;
        LList lList2 = LList.Empty;
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = lists.car.apply1(obj);
            obj = lists.cdr.apply1(obj);
            if (procedure.apply1(apply1) != Boolean.FALSE) {
                lList = lists.cons(apply1, lList);
            } else {
                lList2 = lists.cons(apply1, lList2);
            }
        }
        Object[] objArr = new Object[2];
        try {
            objArr[0] = lists.reverse$Ex(lList);
            try {
                objArr[1] = lists.reverse$Ex(lList2);
                return misc.values(objArr);
            } catch (ClassCastException e) {
                throw new WrongType(e, "reverse!", 1, (Object) lList2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, (Object) lList);
        }
    }

    public static Object partition$Ex(Procedure procedure, Object obj) {
        Object obj2;
        SimpleSymbol simpleSymbol = Lit2;
        Pair cons = lists.cons(simpleSymbol, LList.Empty);
        Pair cons2 = lists.cons(simpleSymbol, LList.Empty);
        Pair pair = cons2;
        Pair pair2 = cons;
        while (!isNotPair(obj)) {
            if (procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE) {
                try {
                    lists.setCdr$Ex(pair2, obj);
                    obj2 = lists.cdr.apply1(obj);
                    pair2 = obj;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, (Object) pair2);
                }
            } else {
                try {
                    lists.setCdr$Ex((Pair) pair, obj);
                    obj2 = lists.cdr.apply1(obj);
                    pair = obj;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "set-cdr!", 1, pair);
                }
            }
            obj = obj2;
        }
        try {
            lists.setCdr$Ex(pair2, LList.Empty);
            try {
                lists.setCdr$Ex((Pair) pair, LList.Empty);
                return misc.values(lists.cdr.apply1(cons), lists.cdr.apply1(cons2));
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "set-cdr!", 1, pair);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "set-cdr!", 1, (Object) pair2);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame15 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object pred;

        public frame15() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 14, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1199");
            this.lambda$Fn14 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 14) {
                return lambda26(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda26(Object obj) {
            return ((Scheme.applyToArgs.apply2(this.pred, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 14) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame16 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        Object pred;

        public frame16() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 15, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1200");
            this.lambda$Fn15 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 15) {
                return lambda27(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda27(Object obj) {
            return ((Scheme.applyToArgs.apply2(this.pred, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 15) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object remove(Object obj, Object obj2) {
        frame15 frame152 = new frame15();
        frame152.pred = obj;
        return filter(frame152.lambda$Fn14, obj2);
    }

    public static Object remove$Ex(Object obj, Object obj2) {
        frame16 frame162 = new frame16();
        frame162.pred = obj;
        return filter$Ex(frame162.lambda$Fn15, obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame17 extends ModuleBody {
        final ModuleMethod lambda$Fn16;
        Object maybe$Mn$Eq;
        Object x;

        public frame17() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1222");
            this.lambda$Fn16 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 16) {
                return lambda28(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda28(Object obj) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 16) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object delete(Object obj, Object obj2, Object obj3) {
        frame17 frame172 = new frame17();
        frame172.x = obj;
        frame172.maybe$Mn$Eq = obj3;
        return filter(frame172.lambda$Fn16, obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame18 extends ModuleBody {
        final ModuleMethod lambda$Fn17;
        Object maybe$Mn$Eq;
        Object x;

        public frame18() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 17, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1225");
            this.lambda$Fn17 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 17) {
                return lambda29(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda29(Object obj) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.x, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 17) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object delete$Ex(Object obj, Object obj2, Object obj3) {
        frame18 frame182 = new frame18();
        frame182.x = obj;
        frame182.maybe$Mn$Eq = obj3;
        return filter$Ex(frame182.lambda$Fn17, obj2);
    }

    public static Object deleteDuplicates(Object obj, Procedure procedure) {
        frame19 frame192 = new frame19();
        frame192.maybe$Mn$Eq = procedure;
        return frame192.lambda30recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame19 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public Object lambda30recur(Object obj) {
            if (srfi1.isNullList(obj) != Boolean.FALSE) {
                return obj;
            }
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            Object lambda30recur = lambda30recur(srfi1.delete(apply1, apply12, this.maybe$Mn$Eq));
            return apply12 == lambda30recur ? obj : lists.cons(apply1, lambda30recur);
        }
    }

    public static Object deleteDuplicates$Ex(Object obj, Procedure procedure) {
        frame20 frame202 = new frame20();
        frame202.maybe$Mn$Eq = procedure;
        return frame202.lambda31recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame20 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public Object lambda31recur(Object obj) {
            if (srfi1.isNullList(obj) != Boolean.FALSE) {
                return obj;
            }
            Object apply1 = lists.car.apply1(obj);
            Object apply12 = lists.cdr.apply1(obj);
            Object lambda31recur = lambda31recur(srfi1.delete$Ex(apply1, apply12, this.maybe$Mn$Eq));
            return apply12 == lambda31recur ? obj : lists.cons(apply1, lambda31recur);
        }
    }

    public static Pair alistCons(Object obj, Object obj2, Object obj3) {
        return lists.cons(lists.cons(obj, obj2), obj3);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 82) {
            try {
                return listCopy((LList) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "list-copy", 1, obj);
            }
        } else if (i == 83) {
            try {
                return iota(LangObjType.coerceIntNum(obj));
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "iota", 1, obj);
            }
        } else if (i == 93) {
            return length$Pl(obj);
        } else {
            if (i == 147) {
                return deleteDuplicates(obj);
            }
            if (i == 149) {
                return deleteDuplicates$Ex(obj);
            }
            if (i == 152) {
                return alistCopy(obj);
            }
            if (i == 120) {
                return concatenate(obj);
            }
            if (i == 121) {
                return concatenate$Ex(obj);
            }
            switch (i) {
                case 87:
                    return isProperList(obj);
                case 88:
                    return isDottedList(obj);
                case 89:
                    return isCircularList(obj);
                case 90:
                    return isNotPair(obj) ? Boolean.TRUE : Boolean.FALSE;
                case 91:
                    return isNullList(obj);
                default:
                    switch (i) {
                        case 95:
                            return fifth(obj);
                        case 96:
                            return sixth(obj);
                        case 97:
                            return seventh(obj);
                        case 98:
                            return eighth(obj);
                        case 99:
                            return ninth(obj);
                        case 100:
                            return tenth(obj);
                        case 101:
                            return car$PlCdr(obj);
                        default:
                            switch (i) {
                                case 110:
                                    return last(obj);
                                case 111:
                                    try {
                                        return lastPair((Pair) obj);
                                    } catch (ClassCastException e3) {
                                        throw new WrongType(e3, "last-pair", 1, obj);
                                    }
                                case 112:
                                    return unzip1(obj);
                                case 113:
                                    return unzip2(obj);
                                case 114:
                                    return unzip3(obj);
                                case 115:
                                    return unzip4(obj);
                                case 116:
                                    return unzip5(obj);
                                default:
                                    return super.apply1(moduleMethod, obj);
                            }
                    }
            }
        }
    }

    public static LList alistCopy(Object obj) {
        Object obj2 = LList.Empty;
        while (obj != LList.Empty) {
            try {
                Pair pair = (Pair) obj;
                Object cdr = pair.getCdr();
                Object car = pair.getCar();
                obj2 = Pair.make(lists.cons(lists.car.apply1(car), lists.cdr.apply1(car)), obj2);
                obj = cdr;
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj);
            }
        }
        return LList.reverseInPlace(obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame21 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn18;
        Object maybe$Mn$Eq;

        public frame21() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1280");
            this.lambda$Fn18 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 18) {
                return lambda32(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda32(Object obj) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(obj)) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 18) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object alistDelete(Object obj, Object obj2, Object obj3) {
        frame21 frame212 = new frame21();
        frame212.key = obj;
        frame212.maybe$Mn$Eq = obj3;
        return filter(frame212.lambda$Fn18, obj2);
    }

    /* compiled from: srfi1.scm */
    public class frame22 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn19;
        Object maybe$Mn$Eq;

        public frame22() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 19, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1283");
            this.lambda$Fn19 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 19) {
                return lambda33(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda33(Object obj) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, lists.car.apply1(obj)) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 19) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object alistDelete$Ex(Object obj, Object obj2, Object obj3) {
        frame22 frame222 = new frame22();
        frame222.key = obj;
        frame222.maybe$Mn$Eq = obj3;
        return filter$Ex(frame222.lambda$Fn19, obj2);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        int i = moduleMethod.selector;
        if (i == 83) {
            try {
                try {
                    try {
                        return iota(LangObjType.coerceIntNum(obj), LangObjType.coerceNumeric(obj2), LangObjType.coerceNumeric(obj3));
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "iota", 3, obj3);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "iota", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "iota", 1, obj);
            }
        } else if (i == 143) {
            return delete(obj, obj2, obj3);
        } else {
            if (i == 145) {
                return delete$Ex(obj, obj2, obj3);
            }
            if (i == 151) {
                return alistCons(obj, obj2, obj3);
            }
            if (i == 153) {
                return alistDelete(obj, obj2, obj3);
            }
            if (i == 155) {
                return alistDelete$Ex(obj, obj2, obj3);
            }
            if (i == 130) {
                try {
                    return reduce((Procedure) obj, obj2, obj3);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "reduce", 1, obj);
                }
            } else if (i != 131) {
                return super.apply3(moduleMethod, obj, obj2, obj3);
            } else {
                try {
                    return reduceRight((Procedure) obj, obj2, obj3);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "reduce-right", 1, obj);
                }
            }
        }
    }

    public static Object find(Object obj, Object obj2) {
        try {
            Object findTail = findTail((Procedure) obj, obj2);
            if (findTail != Boolean.FALSE) {
                return lists.car.apply1(findTail);
            }
            return Boolean.FALSE;
        } catch (ClassCastException e) {
            throw new WrongType(e, "find-tail", 0, obj);
        }
    }

    public static Object findTail(Procedure procedure, Object obj) {
        while (true) {
            Object isNullList = isNullList(obj);
            try {
                int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i == 0) {
                    return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                if (procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE) {
                    return obj;
                }
                obj = lists.cdr.apply1(obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, isNullList);
            }
        }
    }

    public static Object takeWhile(Procedure procedure, Object obj) {
        frame23 frame232 = new frame23();
        frame232.pred = procedure;
        return frame232.lambda34recur(obj);
    }

    /* compiled from: srfi1.scm */
    public class frame23 extends ModuleBody {
        Procedure pred;

        public Object lambda34recur(Object obj) {
            if (srfi1.isNullList(obj) != Boolean.FALSE) {
                return LList.Empty;
            }
            Object apply1 = lists.car.apply1(obj);
            return this.pred.apply1(apply1) != Boolean.FALSE ? lists.cons(apply1, lambda34recur(lists.cdr.apply1(obj))) : LList.Empty;
        }
    }

    public static Object dropWhile(Procedure procedure, Object obj) {
        while (isNullList(obj) == Boolean.FALSE) {
            if (procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE) {
                return obj;
            }
            obj = lists.cdr.apply1(obj);
        }
        return LList.Empty;
    }

    public static Object takeWhile$Ex(Procedure procedure, Object obj) {
        Object isNullList = isNullList(obj);
        if (isNullList == Boolean.FALSE ? procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE : isNullList != Boolean.FALSE) {
            return LList.Empty;
        }
        Object apply1 = lists.cdr.apply1(obj);
        Object obj2 = obj;
        while (lists.isPair(apply1)) {
            if (procedure.apply1(lists.car.apply1(apply1)) != Boolean.FALSE) {
                obj2 = apply1;
                apply1 = lists.cdr.apply1(apply1);
            } else {
                try {
                    lists.setCdr$Ex((Pair) obj2, LList.Empty);
                    return obj;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "set-cdr!", 1, obj2);
                }
            }
        }
        return obj;
    }

    public static Object span(Procedure procedure, Object obj) {
        LList lList = LList.Empty;
        while (isNullList(obj) == Boolean.FALSE) {
            Object apply1 = lists.car.apply1(obj);
            if (procedure.apply1(apply1) != Boolean.FALSE) {
                obj = lists.cdr.apply1(obj);
                lList = lists.cons(apply1, lList);
            } else {
                Object[] objArr = new Object[2];
                try {
                    objArr[0] = lists.reverse$Ex(lList);
                    objArr[1] = obj;
                    return misc.values(objArr);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "reverse!", 1, (Object) lList);
                }
            }
        }
        Object[] objArr2 = new Object[2];
        try {
            objArr2[0] = lists.reverse$Ex(lList);
            objArr2[1] = obj;
            return misc.values(objArr2);
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "reverse!", 1, (Object) lList);
        }
    }

    public static Object span$Ex(Procedure procedure, Object obj) {
        Object isNullList = isNullList(obj);
        if (isNullList == Boolean.FALSE ? procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE : isNullList == Boolean.FALSE) {
            Object apply1 = lists.cdr.apply1(obj);
            Object obj2 = obj;
            while (true) {
                if (isNullList(apply1) != Boolean.FALSE) {
                    break;
                } else if (procedure.apply1(lists.car.apply1(apply1)) != Boolean.FALSE) {
                    obj2 = apply1;
                    apply1 = lists.cdr.apply1(apply1);
                } else {
                    try {
                        lists.setCdr$Ex((Pair) obj2, LList.Empty);
                        break;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "set-cdr!", 1, obj2);
                    }
                }
            }
            return misc.values(obj, apply1);
        }
        return misc.values(LList.Empty, obj);
    }

    /* compiled from: srfi1.scm */
    public class frame24 extends ModuleBody {
        final ModuleMethod lambda$Fn20;
        Object pred;

        public frame24() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 20, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1343");
            this.lambda$Fn20 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 20) {
                return lambda35(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda35(Object obj) {
            return ((Scheme.applyToArgs.apply2(this.pred, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 20) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame25 extends ModuleBody {
        final ModuleMethod lambda$Fn21;
        Object pred;

        public frame25() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 21, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1344");
            this.lambda$Fn21 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 21) {
                return lambda36(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda36(Object obj) {
            return ((Scheme.applyToArgs.apply2(this.pred, obj) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 21) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* renamed from: break  reason: not valid java name */
    public static Object m17break(Object obj, Object obj2) {
        frame24 frame242 = new frame24();
        frame242.pred = obj;
        return span(frame242.lambda$Fn20, obj2);
    }

    public static Object break$Ex(Object obj, Object obj2) {
        frame25 frame252 = new frame25();
        frame252.pred = obj;
        return span$Ex(frame252.lambda$Fn21, obj2);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 78) {
            return xcons(obj, obj2);
        }
        if (i == 80) {
            try {
                return listTabulate(obj, (Procedure) obj2);
            } catch (ClassCastException e) {
                throw new WrongType(e, "list-tabulate", 2, obj2);
            }
        } else if (i == 83) {
            try {
                try {
                    return iota(LangObjType.coerceIntNum(obj), LangObjType.coerceNumeric(obj2));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "iota", 2, obj2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "iota", 1, obj);
            }
        } else if (i == 145) {
            return delete$Ex(obj, obj2);
        } else {
            if (i == 147) {
                try {
                    return deleteDuplicates(obj, (Procedure) obj2);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "delete-duplicates", 2, obj2);
                }
            } else if (i == 149) {
                try {
                    return deleteDuplicates$Ex(obj, (Procedure) obj2);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "delete-duplicates!", 2, obj2);
                }
            } else if (i == 153) {
                return alistDelete(obj, obj2);
            } else {
                if (i == 155) {
                    return alistDelete$Ex(obj, obj2);
                }
                if (i == 118) {
                    return appendReverse(obj, obj2);
                }
                if (i == 119) {
                    return appendReverse$Ex(obj, obj2);
                }
                if (i == 182) {
                    return frame61.lambda84(obj, obj2);
                }
                if (i == 183) {
                    return frame71.lambda100(obj, obj2);
                }
                switch (i) {
                    case 102:
                        try {
                            return take(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e6) {
                            throw new WrongType(e6, "take", 2, obj2);
                        }
                    case 103:
                        try {
                            return drop(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "drop", 2, obj2);
                        }
                    case 104:
                        try {
                            return take$Ex(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e8) {
                            throw new WrongType(e8, "take!", 2, obj2);
                        }
                    case 105:
                        try {
                            return takeRight(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e9) {
                            throw new WrongType(e9, "take-right", 2, obj2);
                        }
                    case 106:
                        try {
                            return dropRight(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e10) {
                            throw new WrongType(e10, "drop-right", 2, obj2);
                        }
                    case 107:
                        try {
                            return dropRight$Ex(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e11) {
                            throw new WrongType(e11, "drop-right!", 2, obj2);
                        }
                    case 108:
                        try {
                            return splitAt(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e12) {
                            throw new WrongType(e12, "split-at", 2, obj2);
                        }
                    case 109:
                        try {
                            return splitAt$Ex(obj, LangObjType.coerceIntNum(obj2));
                        } catch (ClassCastException e13) {
                            throw new WrongType(e13, "split-at!", 2, obj2);
                        }
                    default:
                        switch (i) {
                            case 137:
                                try {
                                    return filter((Procedure) obj, obj2);
                                } catch (ClassCastException e14) {
                                    throw new WrongType(e14, "filter", 1, obj);
                                }
                            case 138:
                                try {
                                    return filter$Ex((Procedure) obj, obj2);
                                } catch (ClassCastException e15) {
                                    throw new WrongType(e15, "filter!", 1, obj);
                                }
                            case 139:
                                try {
                                    return partition((Procedure) obj, obj2);
                                } catch (ClassCastException e16) {
                                    throw new WrongType(e16, "partition", 1, obj);
                                }
                            case 140:
                                try {
                                    return partition$Ex((Procedure) obj, obj2);
                                } catch (ClassCastException e17) {
                                    throw new WrongType(e17, "partition!", 1, obj);
                                }
                            case 141:
                                return remove(obj, obj2);
                            case 142:
                                return remove$Ex(obj, obj2);
                            case 143:
                                return delete(obj, obj2);
                            default:
                                switch (i) {
                                    case 157:
                                        return find(obj, obj2);
                                    case 158:
                                        try {
                                            return findTail((Procedure) obj, obj2);
                                        } catch (ClassCastException e18) {
                                            throw new WrongType(e18, "find-tail", 1, obj);
                                        }
                                    case 159:
                                        try {
                                            return takeWhile((Procedure) obj, obj2);
                                        } catch (ClassCastException e19) {
                                            throw new WrongType(e19, "take-while", 1, obj);
                                        }
                                    case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                                        try {
                                            return dropWhile((Procedure) obj, obj2);
                                        } catch (ClassCastException e20) {
                                            throw new WrongType(e20, "drop-while", 1, obj);
                                        }
                                    case 161:
                                        try {
                                            return takeWhile$Ex((Procedure) obj, obj2);
                                        } catch (ClassCastException e21) {
                                            throw new WrongType(e21, "take-while!", 1, obj);
                                        }
                                    case 162:
                                        try {
                                            return span((Procedure) obj, obj2);
                                        } catch (ClassCastException e22) {
                                            throw new WrongType(e22, "span", 1, obj);
                                        }
                                    case 163:
                                        try {
                                            return span$Ex((Procedure) obj, obj2);
                                        } catch (ClassCastException e23) {
                                            throw new WrongType(e23, "span!", 1, obj);
                                        }
                                    case 164:
                                        return m17break(obj, obj2);
                                    case 165:
                                        return break$Ex(obj, obj2);
                                    default:
                                        return super.apply2(moduleMethod, obj, obj2);
                                }
                        }
                }
            }
        }
    }

    public static Object any$V(Procedure procedure, Object obj, Object[] objArr) {
        frame26 frame262 = new frame26();
        frame262.pred = procedure;
        frame262.lis1 = obj;
        int i = 0;
        frame262.lists = LList.makeList(objArr, 0);
        if (lists.isPair(frame262.lists)) {
            return call_with_values.callWithValues(frame262.lambda$Fn22, frame262.lambda$Fn23);
        }
        Object isNullList = isNullList(frame262.lis1);
        try {
            if (isNullList != Boolean.FALSE) {
                i = 1;
            }
            int i2 = (i + 1) & 1;
            if (i2 == 0) {
                return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply1 = lists.car.apply1(frame262.lis1);
            for (Object apply12 = lists.cdr.apply1(frame262.lis1); isNullList(apply12) == Boolean.FALSE; apply12 = lists.cdr.apply1(apply12)) {
                Object apply13 = frame262.pred.apply1(apply1);
                if (apply13 != Boolean.FALSE) {
                    return apply13;
                }
                apply1 = lists.car.apply1(apply12);
            }
            return frame262.pred.apply1(apply1);
        } catch (ClassCastException e) {
            throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, isNullList);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame26 extends ModuleBody {
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, (Object) null, 0);
        final ModuleMethod lambda$Fn23;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame26() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 23, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1350");
            this.lambda$Fn23 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 22 ? lambda37() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 22) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 23 ? lambda38(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda37() {
            return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
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
        public Object lambda38(Object obj, Object obj2) {
            boolean isPair = lists.isPair(obj);
            if (!isPair) {
                return isPair ? Boolean.TRUE : Boolean.FALSE;
            }
            while (true) {
                Object $PcCars$PlCdrs$SlPair = srfi1.$PcCars$PlCdrs$SlPair(obj2);
                Object apply1 = lists.car.apply1($PcCars$PlCdrs$SlPair);
                obj2 = lists.cdr.apply1($PcCars$PlCdrs$SlPair);
                if (!lists.isPair(apply1)) {
                    return Scheme.apply.apply2(this.pred, obj);
                }
                Object apply2 = Scheme.apply.apply2(this.pred, obj);
                if (apply2 != Boolean.FALSE) {
                    return apply2;
                }
                obj = apply1;
            }
        }
    }

    public static Object every$V(Procedure procedure, Object obj, Object[] objArr) {
        frame27 frame272 = new frame27();
        frame272.pred = procedure;
        frame272.lis1 = obj;
        frame272.lists = LList.makeList(objArr, 0);
        if (lists.isPair(frame272.lists)) {
            return call_with_values.callWithValues(frame272.lambda$Fn24, frame272.lambda$Fn25);
        }
        Object isNullList = isNullList(frame272.lis1);
        if (isNullList != Boolean.FALSE) {
            return isNullList;
        }
        Object apply1 = lists.car.apply1(frame272.lis1);
        for (Object apply12 = lists.cdr.apply1(frame272.lis1); isNullList(apply12) == Boolean.FALSE; apply12 = lists.cdr.apply1(apply12)) {
            Object apply13 = frame272.pred.apply1(apply1);
            if (apply13 == Boolean.FALSE) {
                return apply13;
            }
            apply1 = lists.car.apply1(apply12);
        }
        return frame272.pred.apply1(apply1);
    }

    /* compiled from: srfi1.scm */
    public class frame27 extends ModuleBody {
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 26, (Object) null, 0);
        final ModuleMethod lambda$Fn25;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame27() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 27, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1378");
            this.lambda$Fn25 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 26 ? lambda39() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 26) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 27 ? lambda40(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda39() {
            return srfi1.$PcCars$PlCdrs(lists.cons(this.lis1, this.lists));
        }

        /* access modifiers changed from: package-private */
        public Object lambda40(Object obj, Object obj2) {
            frame28 frame28 = new frame28();
            frame28.staticLink = this;
            int i = ((lists.isPair(obj) ? 1 : 0) + true) & 1;
            if (i != 0) {
                return i != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            return frame28.lambda41lp(obj, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 27) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame28 extends ModuleBody {
        frame27 staticLink;

        public Object lambda41lp(Object obj, Object obj2) {
            frame29 frame29 = new frame29();
            frame29.staticLink = this;
            frame29.heads = obj;
            frame29.tails = obj2;
            return call_with_values.callWithValues(frame29.lambda$Fn26, frame29.lambda$Fn27);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame29 extends ModuleBody {
        Object heads;
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, (Object) null, 0);
        final ModuleMethod lambda$Fn27;
        frame28 staticLink;
        Object tails;

        public frame29() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 25, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
            this.lambda$Fn27 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 24 ? lambda42() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 24) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 25 ? lambda43(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda42() {
            return srfi1.$PcCars$PlCdrs(this.tails);
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
        public Object lambda43(Object obj, Object obj2) {
            if (!lists.isPair(obj)) {
                return Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
            }
            Object apply2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
            if (apply2 != Boolean.FALSE) {
                return this.staticLink.lambda41lp(obj, obj2);
            }
            return apply2;
        }
    }

    public static Object listIndex$V(Procedure procedure, Object obj, Object[] objArr) {
        frame30 frame302 = new frame30();
        frame302.pred = procedure;
        LList makeList = LList.makeList(objArr, 0);
        if (lists.isPair(makeList)) {
            return frame302.lambda44lp(lists.cons(obj, makeList), Lit0);
        }
        Object obj2 = Lit0;
        while (true) {
            Object isNullList = isNullList(obj);
            try {
                int i = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & 1;
                if (i == 0) {
                    return i != 0 ? Boolean.TRUE : Boolean.FALSE;
                }
                if (frame302.pred.apply1(lists.car.apply1(obj)) != Boolean.FALSE) {
                    return obj2;
                }
                obj = lists.cdr.apply1(obj);
                obj2 = AddOp.$Pl.apply2(obj2, Lit1);
            } catch (ClassCastException e) {
                throw new WrongType(e, EllipticCurveJsonWebKey.X_MEMBER_NAME, -2, isNullList);
            }
        }
    }

    /* compiled from: srfi1.scm */
    public class frame30 extends ModuleBody {
        Procedure pred;

        public Object lambda44lp(Object obj, Object obj2) {
            frame31 frame31 = new frame31();
            frame31.staticLink = this;
            frame31.lists = obj;
            frame31.n = obj2;
            return call_with_values.callWithValues(frame31.lambda$Fn28, frame31.lambda$Fn29);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame31 extends ModuleBody {
        final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, (Object) null, 0);
        final ModuleMethod lambda$Fn29;
        Object lists;
        Object n;
        frame30 staticLink;

        public frame31() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 29, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1404");
            this.lambda$Fn29 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 28 ? lambda45() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 28) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 29 ? lambda46(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda45() {
            return srfi1.$PcCars$PlCdrs(this.lists);
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
        public Object lambda46(Object obj, Object obj2) {
            boolean isPair = lists.isPair(obj);
            if (!isPair) {
                return isPair ? Boolean.TRUE : Boolean.FALSE;
            }
            if (Scheme.apply.apply2(this.staticLink.pred, obj) != Boolean.FALSE) {
                return this.n;
            }
            return this.staticLink.lambda44lp(obj2, AddOp.$Pl.apply2(this.n, srfi1.Lit1));
        }
    }

    /* compiled from: srfi1.scm */
    public class frame72 extends ModuleBody {
        Object $Eq;
        final ModuleMethod lambda$Fn79;
        Object lis2;

        public frame72() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 77, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1443");
            this.lambda$Fn79 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 77 ? lambda101(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda101(Object obj) {
            Object obj2 = this.lis2;
            Object obj3 = this.$Eq;
            try {
                return lists.member(obj, obj2, (Procedure) obj3);
            } catch (ClassCastException e) {
                throw new WrongType(e, "member", 3, obj3);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 77) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    static Object $PcLset2$Ls$Eq(Object obj, Object obj2, Object obj3) {
        frame72 frame722 = new frame72();
        frame722.$Eq = obj;
        frame722.lis2 = obj3;
        return every$V(frame722.lambda$Fn79, obj2, new Object[0]);
    }

    public static Object lset$Ls$Eq$V(Procedure procedure, Object[] objArr) {
        LList makeList = LList.makeList(objArr, 0);
        int i = ((lists.isPair(makeList) ? 1 : 0) + true) & 1;
        if (i != 0) {
            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            int i2 = ((lists.isPair(apply12) ? 1 : 0) + true) & 1;
            if (i2 != 0) {
                return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            boolean z = apply13 == apply1;
            Object $PcLset2$Ls$Eq = z ? z ? Boolean.TRUE : Boolean.FALSE : $PcLset2$Ls$Eq(procedure, apply1, apply13);
            if ($PcLset2$Ls$Eq == Boolean.FALSE) {
                return $PcLset2$Ls$Eq;
            }
            apply1 = apply13;
        }
    }

    public static Object lset$Eq$V(Procedure procedure, Object[] objArr) {
        Object obj;
        LList makeList = LList.makeList(objArr, 0);
        int i = ((lists.isPair(makeList) ? 1 : 0) + true) & 1;
        if (i != 0) {
            return i != 0 ? Boolean.TRUE : Boolean.FALSE;
        }
        Object apply1 = lists.car.apply1(makeList);
        Object apply12 = lists.cdr.apply1(makeList);
        while (true) {
            int i2 = ((lists.isPair(apply12) ? 1 : 0) + true) & 1;
            if (i2 != 0) {
                return i2 != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
            Object apply13 = lists.car.apply1(apply12);
            apply12 = lists.cdr.apply1(apply12);
            boolean z = apply1 == apply13;
            if (z) {
                obj = z ? Boolean.TRUE : Boolean.FALSE;
            } else {
                Object $PcLset2$Ls$Eq = $PcLset2$Ls$Eq(procedure, apply1, apply13);
                obj = $PcLset2$Ls$Eq != Boolean.FALSE ? $PcLset2$Ls$Eq(procedure, apply13, apply1) : $PcLset2$Ls$Eq;
            }
            if (obj == Boolean.FALSE) {
                return obj;
            }
            apply1 = apply13;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame32 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn30;

        public frame32() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 30, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1466");
            this.lambda$Fn30 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 30 ? lambda47(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda47(Object obj, Object obj2) {
            return lists.member(obj, obj2, this.$Eq) != Boolean.FALSE ? obj2 : lists.cons(obj, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 30) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetAdjoin$V(Procedure procedure, Object obj, Object[] objArr) {
        frame32 frame322 = new frame32();
        frame322.$Eq = procedure;
        return fold$V(frame322.lambda$Fn30, obj, LList.makeList(objArr, 0), new Object[0]);
    }

    /* compiled from: srfi1.scm */
    public class frame33 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn31;
        final ModuleMethod lambda$Fn32;

        public frame33() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 32, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn32 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 33, (Object) null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1471");
            this.lambda$Fn31 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            int i = moduleMethod.selector;
            if (i != 32) {
                return i != 33 ? super.apply2(moduleMethod, obj, obj2) : lambda48(obj, obj2);
            }
            return lambda49(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda48(Object obj, Object obj2) {
            if (!lists.isNull(obj)) {
                if (lists.isNull(obj2)) {
                    return obj;
                }
                if (obj != obj2) {
                    return srfi1.fold$V(this.lambda$Fn32, obj2, obj, new Object[0]);
                }
            }
            return obj2;
        }

        /* access modifiers changed from: package-private */
        public Object lambda49(Object obj, Object obj2) {
            frame34 frame34 = new frame34();
            frame34.staticLink = this;
            frame34.elt = obj;
            return srfi1.any$V(frame34.lambda$Fn33, obj2, new Object[0]) != Boolean.FALSE ? obj2 : lists.cons(frame34.elt, obj2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i == 32) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i != 33) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        }
    }

    public static Object lsetUnion$V(Procedure procedure, Object[] objArr) {
        frame33 frame332 = new frame33();
        frame332.$Eq = procedure;
        return reduce(frame332.lambda$Fn31, LList.Empty, LList.makeList(objArr, 0));
    }

    /* compiled from: srfi1.scm */
    public class frame34 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn33;
        frame33 staticLink;

        public frame34() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 31, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn33 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 31 ? lambda50(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda50(Object obj) {
            return this.staticLink.$Eq.apply2(obj, this.elt);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 31) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame35 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn34;
        final ModuleMethod lambda$Fn35;

        public frame35() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 35, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1488");
            this.lambda$Fn35 = moduleMethod;
            ModuleMethod moduleMethod2 = new ModuleMethod(this, 36, (Object) null, 8194);
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1483");
            this.lambda$Fn34 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            int i = moduleMethod.selector;
            if (i != 35) {
                return i != 36 ? super.apply2(moduleMethod, obj, obj2) : lambda51(obj, obj2);
            }
            return lambda52(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda51(Object obj, Object obj2) {
            if (!lists.isNull(obj)) {
                if (lists.isNull(obj2)) {
                    return obj;
                }
                if (obj != obj2) {
                    return srfi1.pairFold$V(this.lambda$Fn35, obj2, obj, new Object[0]);
                }
            }
            return obj2;
        }

        /* access modifiers changed from: package-private */
        public Object lambda52(Object obj, Object obj2) {
            frame36 frame36 = new frame36();
            frame36.staticLink = this;
            frame36.elt = lists.car.apply1(obj);
            if (srfi1.any$V(frame36.lambda$Fn36, obj2, new Object[0]) != Boolean.FALSE) {
                return obj2;
            }
            try {
                lists.setCdr$Ex((Pair) obj, obj2);
                return obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, obj);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            int i = moduleMethod.selector;
            if (i == 35) {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (i != 36) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        }
    }

    public static Object lsetUnion$Ex$V(Procedure procedure, Object[] objArr) {
        frame35 frame352 = new frame35();
        frame352.$Eq = procedure;
        return reduce(frame352.lambda$Fn34, LList.Empty, LList.makeList(objArr, 0));
    }

    /* compiled from: srfi1.scm */
    public class frame36 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn36;
        frame35 staticLink;

        public frame36() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 34, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1490");
            this.lambda$Fn36 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 34 ? lambda53(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda53(Object obj) {
            return this.staticLink.$Eq.apply2(obj, this.elt);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 34) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$V(Procedure procedure, Object obj, Object[] objArr) {
        frame37 frame372 = new frame37();
        frame372.$Eq = procedure;
        frame372.lists = delete(obj, LList.makeList(objArr, 0), Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame372.lists, new Object[0]) != Boolean.FALSE) {
            return LList.Empty;
        }
        return lists.isNull(frame372.lists) ? obj : filter(frame372.lambda$Fn37, obj);
    }

    /* compiled from: srfi1.scm */
    public class frame37 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn37;
        Object lists;

        public frame37() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 38, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1501");
            this.lambda$Fn37 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 38 ? lambda54(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda54(Object obj) {
            frame38 frame38 = new frame38();
            frame38.staticLink = this;
            frame38.x = obj;
            return srfi1.every$V(frame38.lambda$Fn38, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 38) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame38 extends ModuleBody {
        final ModuleMethod lambda$Fn38;
        frame37 staticLink;
        Object x;

        public frame38() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 37, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1502");
            this.lambda$Fn38 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 37 ? lambda55(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda55(Object obj) {
            return lists.member(this.x, obj, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 37) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$Ex$V(Procedure procedure, Object obj, Object[] objArr) {
        frame39 frame392 = new frame39();
        frame392.$Eq = procedure;
        frame392.lists = delete(obj, LList.makeList(objArr, 0), Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame392.lists, new Object[0]) != Boolean.FALSE) {
            return LList.Empty;
        }
        return lists.isNull(frame392.lists) ? obj : filter$Ex(frame392.lambda$Fn39, obj);
    }

    /* compiled from: srfi1.scm */
    public class frame39 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn39;
        Object lists;

        public frame39() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 40, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1509");
            this.lambda$Fn39 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 40 ? lambda56(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda56(Object obj) {
            frame40 frame40 = new frame40();
            frame40.staticLink = this;
            frame40.x = obj;
            return srfi1.every$V(frame40.lambda$Fn40, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 40) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame40 extends ModuleBody {
        final ModuleMethod lambda$Fn40;
        frame39 staticLink;
        Object x;

        public frame40() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 39, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1510");
            this.lambda$Fn40 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 39 ? lambda57(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda57(Object obj) {
            return lists.member(this.x, obj, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 39) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$V(Procedure procedure, Object obj, Object[] objArr) {
        frame41 frame412 = new frame41();
        frame412.$Eq = procedure;
        frame412.lists = filter(lists.pair$Qu, LList.makeList(objArr, 0));
        if (lists.isNull(frame412.lists)) {
            return obj;
        }
        if (lists.memq(obj, frame412.lists) != Boolean.FALSE) {
            return LList.Empty;
        }
        return filter(frame412.lambda$Fn41, obj);
    }

    /* compiled from: srfi1.scm */
    public class frame41 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn41;
        Object lists;

        public frame41() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 42, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1518");
            this.lambda$Fn41 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 42 ? lambda58(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda58(Object obj) {
            frame42 frame42 = new frame42();
            frame42.staticLink = this;
            frame42.x = obj;
            return srfi1.every$V(frame42.lambda$Fn42, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 42) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame42 extends ModuleBody {
        final ModuleMethod lambda$Fn42;
        frame41 staticLink;
        Object x;

        public frame42() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 41, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1519");
            this.lambda$Fn42 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 41) {
                return lambda59(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda59(Object obj) {
            return ((lists.member(this.x, obj, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 41) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$Ex$V(Procedure procedure, Object obj, Object[] objArr) {
        frame43 frame432 = new frame43();
        frame432.$Eq = procedure;
        frame432.lists = filter(lists.pair$Qu, LList.makeList(objArr, 0));
        if (lists.isNull(frame432.lists)) {
            return obj;
        }
        if (lists.memq(obj, frame432.lists) != Boolean.FALSE) {
            return LList.Empty;
        }
        return filter$Ex(frame432.lambda$Fn43, obj);
    }

    /* compiled from: srfi1.scm */
    public class frame43 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn43;
        Object lists;

        public frame43() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 44, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1527");
            this.lambda$Fn43 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 44 ? lambda60(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda60(Object obj) {
            frame44 frame44 = new frame44();
            frame44.staticLink = this;
            frame44.x = obj;
            return srfi1.every$V(frame44.lambda$Fn44, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 44) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame44 extends ModuleBody {
        final ModuleMethod lambda$Fn44;
        frame43 staticLink;
        Object x;

        public frame44() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 43, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1528");
            this.lambda$Fn44 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 43) {
                return lambda61(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda61(Object obj) {
            return ((lists.member(this.x, obj, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 43) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame45 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn45;

        public frame45() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 48, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1534");
            this.lambda$Fn45 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 48 ? lambda62(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda62(Object obj, Object obj2) {
            frame46 frame46 = new frame46();
            frame46.staticLink = this;
            frame46.b = obj;
            frame46.a = obj2;
            return call_with_values.callWithValues(frame46.lambda$Fn46, frame46.lambda$Fn47);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 48) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$V(Procedure procedure, Object[] objArr) {
        frame45 frame452 = new frame45();
        frame452.$Eq = procedure;
        return reduce(frame452.lambda$Fn45, LList.Empty, LList.makeList(objArr, 0));
    }

    /* compiled from: srfi1.scm */
    public class frame46 extends ModuleBody {
        Object a;
        Object b;
        final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, (Object) null, 0);
        final ModuleMethod lambda$Fn47;
        frame45 staticLink;

        public frame46() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 47, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1544");
            this.lambda$Fn47 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 46 ? lambda63() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 46) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 47 ? lambda64(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda63() {
            return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.a, new Object[]{this.b});
        }

        /* access modifiers changed from: package-private */
        public Object lambda64(Object obj, Object obj2) {
            frame47 frame47 = new frame47();
            frame47.staticLink = this;
            frame47.a$Mnint$Mnb = obj2;
            if (lists.isNull(obj)) {
                return srfi1.lsetDifference$V(this.staticLink.$Eq, this.b, new Object[]{this.a});
            } else if (!lists.isNull(frame47.a$Mnint$Mnb)) {
                return srfi1.fold$V(frame47.lambda$Fn48, obj, this.b, new Object[0]);
            } else {
                return append.append$V(new Object[]{this.b, this.a});
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 47) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame47 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn48;
        frame46 staticLink;

        public frame47() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 45, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
            this.lambda$Fn48 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 45 ? lambda65(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
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
        public Object lambda65(Object obj, Object obj2) {
            return lists.member(obj, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE ? obj2 : lists.cons(obj, obj2);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame48 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn49;

        public frame48() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 52, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1555");
            this.lambda$Fn49 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 52 ? lambda66(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda66(Object obj, Object obj2) {
            frame49 frame49 = new frame49();
            frame49.staticLink = this;
            frame49.b = obj;
            frame49.a = obj2;
            return call_with_values.callWithValues(frame49.lambda$Fn50, frame49.lambda$Fn51);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 52) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$Ex$V(Procedure procedure, Object[] objArr) {
        frame48 frame482 = new frame48();
        frame482.$Eq = procedure;
        return reduce(frame482.lambda$Fn49, LList.Empty, LList.makeList(objArr, 0));
    }

    /* compiled from: srfi1.scm */
    public class frame49 extends ModuleBody {
        Object a;
        Object b;
        final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, (Object) null, 0);
        final ModuleMethod lambda$Fn51;
        frame48 staticLink;

        public frame49() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 51, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1565");
            this.lambda$Fn51 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            return moduleMethod.selector == 50 ? lambda67() : super.apply0(moduleMethod);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            if (moduleMethod.selector != 50) {
                return super.match0(moduleMethod, callContext);
            }
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 51 ? lambda68(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda67() {
            return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.a, new Object[]{this.b});
        }

        /* access modifiers changed from: package-private */
        public Object lambda68(Object obj, Object obj2) {
            frame50 frame50 = new frame50();
            frame50.staticLink = this;
            frame50.a$Mnint$Mnb = obj2;
            if (lists.isNull(obj)) {
                return srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.b, new Object[]{this.a});
            } else if (!lists.isNull(frame50.a$Mnint$Mnb)) {
                return srfi1.pairFold$V(frame50.lambda$Fn52, obj, this.b, new Object[0]);
            } else {
                return srfi1.append$Ex$V(new Object[]{this.b, this.a});
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            if (moduleMethod.selector != 51) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            callContext.value1 = obj;
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame50 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn52;
        frame49 staticLink;

        public frame50() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 49, (Object) null, 8194);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
            this.lambda$Fn52 = moduleMethod;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            return moduleMethod.selector == 49 ? lambda69(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
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
        public Object lambda69(Object obj, Object obj2) {
            if (lists.member(lists.car.apply1(obj), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) {
                return obj2;
            }
            try {
                lists.setCdr$Ex((Pair) obj, obj2);
                return obj;
            } catch (ClassCastException e) {
                throw new WrongType(e, "set-cdr!", 1, obj);
            }
        }
    }

    public static Object lsetDiff$PlIntersection$V(Procedure procedure, Object obj, Object[] objArr) {
        frame51 frame512 = new frame51();
        frame512.$Eq = procedure;
        frame512.lists = LList.makeList(objArr, 0);
        if (every$V(null$Mnlist$Qu, frame512.lists, new Object[0]) != Boolean.FALSE) {
            return misc.values(obj, LList.Empty);
        } else if (lists.memq(obj, frame512.lists) == Boolean.FALSE) {
            return partition(frame512.lambda$Fn53, obj);
        } else {
            return misc.values(LList.Empty, obj);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame51 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn53;
        LList lists;

        public frame51() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 54, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1579");
            this.lambda$Fn53 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 54) {
                return lambda70(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda70(Object obj) {
            frame52 frame52 = new frame52();
            frame52.staticLink = this;
            frame52.elt = obj;
            int i = 0;
            if (srfi1.any$V(frame52.lambda$Fn54, this.lists, new Object[0]) != Boolean.FALSE) {
                i = 1;
            }
            return (i + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 54) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame52 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn54;
        frame51 staticLink;

        public frame52() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 53, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1580");
            this.lambda$Fn54 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 53 ? lambda71(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda71(Object obj) {
            return lists.member(this.elt, obj, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 53) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static Object lsetDiff$PlIntersection$Ex$V(Procedure procedure, Object obj, Object[] objArr) {
        frame53 frame532 = new frame53();
        frame532.$Eq = procedure;
        frame532.lists = LList.makeList(objArr, 0);
        if (every$V(null$Mnlist$Qu, frame532.lists, new Object[0]) != Boolean.FALSE) {
            return misc.values(obj, LList.Empty);
        } else if (lists.memq(obj, frame532.lists) == Boolean.FALSE) {
            return partition$Ex(frame532.lambda$Fn55, obj);
        } else {
            return misc.values(LList.Empty, obj);
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 79) {
            Object obj = objArr[0];
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            while (true) {
                length--;
                if (length < 0) {
                    return makeList$V(obj, objArr2);
                }
                objArr2[length] = objArr[length + 1];
            }
        } else if (i == 81) {
            return cons$St(objArr);
        } else {
            if (i == 86) {
                Object obj2 = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return circularList$V(obj2, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
            } else if (i == 92) {
                Object obj3 = objArr[0];
                int length3 = objArr.length - 1;
                Object[] objArr4 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return list$Eq$V(obj3, objArr4);
                    }
                    objArr4[length3] = objArr[length3 + 1];
                }
            } else if (i == 94) {
                Object obj4 = objArr[0];
                int length4 = objArr.length - 1;
                Object[] objArr5 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return zip$V(obj4, objArr5);
                    }
                    objArr5[length4] = objArr[length4 + 1];
                }
            } else if (i == 117) {
                return append$Ex$V(objArr);
            } else {
                if (i == 122) {
                    Procedure procedure = objArr[0];
                    try {
                        Procedure procedure2 = procedure;
                        Object obj5 = objArr[1];
                        int length5 = objArr.length - 2;
                        Object[] objArr6 = new Object[length5];
                        while (true) {
                            length5--;
                            if (length5 < 0) {
                                return count$V(procedure2, obj5, objArr6);
                            }
                            objArr6[length5] = objArr[length5 + 2];
                        }
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "count", 1, (Object) procedure);
                    }
                } else if (i != 123) {
                    switch (i) {
                        case ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH /*125*/:
                            Procedure procedure3 = objArr[0];
                            try {
                                Procedure procedure4 = procedure3;
                                Procedure procedure5 = objArr[1];
                                try {
                                    Procedure procedure6 = procedure5;
                                    Procedure procedure7 = objArr[2];
                                    try {
                                        Procedure procedure8 = procedure7;
                                        Object obj6 = objArr[3];
                                        int length6 = objArr.length - 4;
                                        Object[] objArr7 = new Object[length6];
                                        while (true) {
                                            length6--;
                                            if (length6 < 0) {
                                                return unfold$V(procedure4, procedure6, procedure8, obj6, objArr7);
                                            }
                                            objArr7[length6] = objArr[length6 + 4];
                                        }
                                    } catch (ClassCastException e2) {
                                        throw new WrongType(e2, "unfold", 3, (Object) procedure7);
                                    }
                                } catch (ClassCastException e3) {
                                    throw new WrongType(e3, "unfold", 2, (Object) procedure5);
                                }
                            } catch (ClassCastException e4) {
                                throw new WrongType(e4, "unfold", 1, (Object) procedure3);
                            }
                        case 126:
                            Procedure procedure9 = objArr[0];
                            try {
                                Procedure procedure10 = procedure9;
                                Object obj7 = objArr[1];
                                Object obj8 = objArr[2];
                                int length7 = objArr.length - 3;
                                Object[] objArr8 = new Object[length7];
                                while (true) {
                                    length7--;
                                    if (length7 < 0) {
                                        return fold$V(procedure10, obj7, obj8, objArr8);
                                    }
                                    objArr8[length7] = objArr[length7 + 3];
                                }
                            } catch (ClassCastException e5) {
                                throw new WrongType(e5, "fold", 1, (Object) procedure9);
                            }
                        case 127:
                            Procedure procedure11 = objArr[0];
                            try {
                                Procedure procedure12 = procedure11;
                                Object obj9 = objArr[1];
                                Object obj10 = objArr[2];
                                int length8 = objArr.length - 3;
                                Object[] objArr9 = new Object[length8];
                                while (true) {
                                    length8--;
                                    if (length8 < 0) {
                                        return foldRight$V(procedure12, obj9, obj10, objArr9);
                                    }
                                    objArr9[length8] = objArr[length8 + 3];
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "fold-right", 1, (Object) procedure11);
                            }
                        case 128:
                            Procedure procedure13 = objArr[0];
                            try {
                                Procedure procedure14 = procedure13;
                                Object obj11 = objArr[1];
                                Object obj12 = objArr[2];
                                int length9 = objArr.length - 3;
                                Object[] objArr10 = new Object[length9];
                                while (true) {
                                    length9--;
                                    if (length9 < 0) {
                                        return pairFoldRight$V(procedure14, obj11, obj12, objArr10);
                                    }
                                    objArr10[length9] = objArr[length9 + 3];
                                }
                            } catch (ClassCastException e7) {
                                throw new WrongType(e7, "pair-fold-right", 1, (Object) procedure13);
                            }
                        case 129:
                            Procedure procedure15 = objArr[0];
                            try {
                                Procedure procedure16 = procedure15;
                                Object obj13 = objArr[1];
                                Object obj14 = objArr[2];
                                int length10 = objArr.length - 3;
                                Object[] objArr11 = new Object[length10];
                                while (true) {
                                    length10--;
                                    if (length10 < 0) {
                                        return pairFold$V(procedure16, obj13, obj14, objArr11);
                                    }
                                    objArr11[length10] = objArr[length10 + 3];
                                }
                            } catch (ClassCastException e8) {
                                throw new WrongType(e8, "pair-fold", 1, (Object) procedure15);
                            }
                        default:
                            switch (i) {
                                case 132:
                                    Object obj15 = objArr[0];
                                    Object obj16 = objArr[1];
                                    int length11 = objArr.length - 2;
                                    Object[] objArr12 = new Object[length11];
                                    while (true) {
                                        length11--;
                                        if (length11 < 0) {
                                            return appendMap$V(obj15, obj16, objArr12);
                                        }
                                        objArr12[length11] = objArr[length11 + 2];
                                    }
                                case 133:
                                    Object obj17 = objArr[0];
                                    Object obj18 = objArr[1];
                                    int length12 = objArr.length - 2;
                                    Object[] objArr13 = new Object[length12];
                                    while (true) {
                                        length12--;
                                        if (length12 < 0) {
                                            return appendMap$Ex$V(obj17, obj18, objArr13);
                                        }
                                        objArr13[length12] = objArr[length12 + 2];
                                    }
                                case 134:
                                    Procedure procedure17 = objArr[0];
                                    try {
                                        Procedure procedure18 = procedure17;
                                        Object obj19 = objArr[1];
                                        int length13 = objArr.length - 2;
                                        Object[] objArr14 = new Object[length13];
                                        while (true) {
                                            length13--;
                                            if (length13 < 0) {
                                                return pairForEach$V(procedure18, obj19, objArr14);
                                            }
                                            objArr14[length13] = objArr[length13 + 2];
                                        }
                                    } catch (ClassCastException e9) {
                                        throw new WrongType(e9, "pair-for-each", 1, (Object) procedure17);
                                    }
                                case 135:
                                    Procedure procedure19 = objArr[0];
                                    try {
                                        Procedure procedure20 = procedure19;
                                        Object obj20 = objArr[1];
                                        int length14 = objArr.length - 2;
                                        Object[] objArr15 = new Object[length14];
                                        while (true) {
                                            length14--;
                                            if (length14 < 0) {
                                                return map$Ex$V(procedure20, obj20, objArr15);
                                            }
                                            objArr15[length14] = objArr[length14 + 2];
                                        }
                                    } catch (ClassCastException e10) {
                                        throw new WrongType(e10, "map!", 1, (Object) procedure19);
                                    }
                                case 136:
                                    Procedure procedure21 = objArr[0];
                                    try {
                                        Procedure procedure22 = procedure21;
                                        Object obj21 = objArr[1];
                                        int length15 = objArr.length - 2;
                                        Object[] objArr16 = new Object[length15];
                                        while (true) {
                                            length15--;
                                            if (length15 < 0) {
                                                return filterMap$V(procedure22, obj21, objArr16);
                                            }
                                            objArr16[length15] = objArr[length15 + 2];
                                        }
                                    } catch (ClassCastException e11) {
                                        throw new WrongType(e11, "filter-map", 1, (Object) procedure21);
                                    }
                                default:
                                    switch (i) {
                                        case 166:
                                            Procedure procedure23 = objArr[0];
                                            try {
                                                Procedure procedure24 = procedure23;
                                                Object obj22 = objArr[1];
                                                int length16 = objArr.length - 2;
                                                Object[] objArr17 = new Object[length16];
                                                while (true) {
                                                    length16--;
                                                    if (length16 < 0) {
                                                        return any$V(procedure24, obj22, objArr17);
                                                    }
                                                    objArr17[length16] = objArr[length16 + 2];
                                                }
                                            } catch (ClassCastException e12) {
                                                throw new WrongType(e12, "any", 1, (Object) procedure23);
                                            }
                                        case 167:
                                            Procedure procedure25 = objArr[0];
                                            try {
                                                Procedure procedure26 = procedure25;
                                                Object obj23 = objArr[1];
                                                int length17 = objArr.length - 2;
                                                Object[] objArr18 = new Object[length17];
                                                while (true) {
                                                    length17--;
                                                    if (length17 < 0) {
                                                        return every$V(procedure26, obj23, objArr18);
                                                    }
                                                    objArr18[length17] = objArr[length17 + 2];
                                                }
                                            } catch (ClassCastException e13) {
                                                throw new WrongType(e13, "every", 1, (Object) procedure25);
                                            }
                                        case 168:
                                            Procedure procedure27 = objArr[0];
                                            try {
                                                Procedure procedure28 = procedure27;
                                                Object obj24 = objArr[1];
                                                int length18 = objArr.length - 2;
                                                Object[] objArr19 = new Object[length18];
                                                while (true) {
                                                    length18--;
                                                    if (length18 < 0) {
                                                        return listIndex$V(procedure28, obj24, objArr19);
                                                    }
                                                    objArr19[length18] = objArr[length18 + 2];
                                                }
                                            } catch (ClassCastException e14) {
                                                throw new WrongType(e14, "list-index", 1, (Object) procedure27);
                                            }
                                        case 169:
                                            Procedure procedure29 = objArr[0];
                                            try {
                                                Procedure procedure30 = procedure29;
                                                int length19 = objArr.length - 1;
                                                Object[] objArr20 = new Object[length19];
                                                while (true) {
                                                    length19--;
                                                    if (length19 < 0) {
                                                        return lset$Ls$Eq$V(procedure30, objArr20);
                                                    }
                                                    objArr20[length19] = objArr[length19 + 1];
                                                }
                                            } catch (ClassCastException e15) {
                                                throw new WrongType(e15, "lset<=", 1, (Object) procedure29);
                                            }
                                        case 170:
                                            Procedure procedure31 = objArr[0];
                                            try {
                                                Procedure procedure32 = procedure31;
                                                int length20 = objArr.length - 1;
                                                Object[] objArr21 = new Object[length20];
                                                while (true) {
                                                    length20--;
                                                    if (length20 < 0) {
                                                        return lset$Eq$V(procedure32, objArr21);
                                                    }
                                                    objArr21[length20] = objArr[length20 + 1];
                                                }
                                            } catch (ClassCastException e16) {
                                                throw new WrongType(e16, "lset=", 1, (Object) procedure31);
                                            }
                                        case 171:
                                            Procedure procedure33 = objArr[0];
                                            try {
                                                Procedure procedure34 = procedure33;
                                                Object obj25 = objArr[1];
                                                int length21 = objArr.length - 2;
                                                Object[] objArr22 = new Object[length21];
                                                while (true) {
                                                    length21--;
                                                    if (length21 < 0) {
                                                        return lsetAdjoin$V(procedure34, obj25, objArr22);
                                                    }
                                                    objArr22[length21] = objArr[length21 + 2];
                                                }
                                            } catch (ClassCastException e17) {
                                                throw new WrongType(e17, "lset-adjoin", 1, (Object) procedure33);
                                            }
                                        case 172:
                                            Procedure procedure35 = objArr[0];
                                            try {
                                                Procedure procedure36 = procedure35;
                                                int length22 = objArr.length - 1;
                                                Object[] objArr23 = new Object[length22];
                                                while (true) {
                                                    length22--;
                                                    if (length22 < 0) {
                                                        return lsetUnion$V(procedure36, objArr23);
                                                    }
                                                    objArr23[length22] = objArr[length22 + 1];
                                                }
                                            } catch (ClassCastException e18) {
                                                throw new WrongType(e18, "lset-union", 1, (Object) procedure35);
                                            }
                                        case 173:
                                            Procedure procedure37 = objArr[0];
                                            try {
                                                Procedure procedure38 = procedure37;
                                                int length23 = objArr.length - 1;
                                                Object[] objArr24 = new Object[length23];
                                                while (true) {
                                                    length23--;
                                                    if (length23 < 0) {
                                                        return lsetUnion$Ex$V(procedure38, objArr24);
                                                    }
                                                    objArr24[length23] = objArr[length23 + 1];
                                                }
                                            } catch (ClassCastException e19) {
                                                throw new WrongType(e19, "lset-union!", 1, (Object) procedure37);
                                            }
                                        case 174:
                                            Procedure procedure39 = objArr[0];
                                            try {
                                                Procedure procedure40 = procedure39;
                                                Object obj26 = objArr[1];
                                                int length24 = objArr.length - 2;
                                                Object[] objArr25 = new Object[length24];
                                                while (true) {
                                                    length24--;
                                                    if (length24 < 0) {
                                                        return lsetIntersection$V(procedure40, obj26, objArr25);
                                                    }
                                                    objArr25[length24] = objArr[length24 + 2];
                                                }
                                            } catch (ClassCastException e20) {
                                                throw new WrongType(e20, "lset-intersection", 1, (Object) procedure39);
                                            }
                                        case 175:
                                            Procedure procedure41 = objArr[0];
                                            try {
                                                Procedure procedure42 = procedure41;
                                                Object obj27 = objArr[1];
                                                int length25 = objArr.length - 2;
                                                Object[] objArr26 = new Object[length25];
                                                while (true) {
                                                    length25--;
                                                    if (length25 < 0) {
                                                        return lsetIntersection$Ex$V(procedure42, obj27, objArr26);
                                                    }
                                                    objArr26[length25] = objArr[length25 + 2];
                                                }
                                            } catch (ClassCastException e21) {
                                                throw new WrongType(e21, "lset-intersection!", 1, (Object) procedure41);
                                            }
                                        case 176:
                                            Procedure procedure43 = objArr[0];
                                            try {
                                                Procedure procedure44 = procedure43;
                                                Object obj28 = objArr[1];
                                                int length26 = objArr.length - 2;
                                                Object[] objArr27 = new Object[length26];
                                                while (true) {
                                                    length26--;
                                                    if (length26 < 0) {
                                                        return lsetDifference$V(procedure44, obj28, objArr27);
                                                    }
                                                    objArr27[length26] = objArr[length26 + 2];
                                                }
                                            } catch (ClassCastException e22) {
                                                throw new WrongType(e22, "lset-difference", 1, (Object) procedure43);
                                            }
                                        case 177:
                                            Procedure procedure45 = objArr[0];
                                            try {
                                                Procedure procedure46 = procedure45;
                                                Object obj29 = objArr[1];
                                                int length27 = objArr.length - 2;
                                                Object[] objArr28 = new Object[length27];
                                                while (true) {
                                                    length27--;
                                                    if (length27 < 0) {
                                                        return lsetDifference$Ex$V(procedure46, obj29, objArr28);
                                                    }
                                                    objArr28[length27] = objArr[length27 + 2];
                                                }
                                            } catch (ClassCastException e23) {
                                                throw new WrongType(e23, "lset-difference!", 1, (Object) procedure45);
                                            }
                                        case 178:
                                            Procedure procedure47 = objArr[0];
                                            try {
                                                Procedure procedure48 = procedure47;
                                                int length28 = objArr.length - 1;
                                                Object[] objArr29 = new Object[length28];
                                                while (true) {
                                                    length28--;
                                                    if (length28 < 0) {
                                                        return lsetXor$V(procedure48, objArr29);
                                                    }
                                                    objArr29[length28] = objArr[length28 + 1];
                                                }
                                            } catch (ClassCastException e24) {
                                                throw new WrongType(e24, "lset-xor", 1, (Object) procedure47);
                                            }
                                        case 179:
                                            Procedure procedure49 = objArr[0];
                                            try {
                                                Procedure procedure50 = procedure49;
                                                int length29 = objArr.length - 1;
                                                Object[] objArr30 = new Object[length29];
                                                while (true) {
                                                    length29--;
                                                    if (length29 < 0) {
                                                        return lsetXor$Ex$V(procedure50, objArr30);
                                                    }
                                                    objArr30[length29] = objArr[length29 + 1];
                                                }
                                            } catch (ClassCastException e25) {
                                                throw new WrongType(e25, "lset-xor!", 1, (Object) procedure49);
                                            }
                                        case 180:
                                            Procedure procedure51 = objArr[0];
                                            try {
                                                Procedure procedure52 = procedure51;
                                                Object obj30 = objArr[1];
                                                int length30 = objArr.length - 2;
                                                Object[] objArr31 = new Object[length30];
                                                while (true) {
                                                    length30--;
                                                    if (length30 < 0) {
                                                        return lsetDiff$PlIntersection$V(procedure52, obj30, objArr31);
                                                    }
                                                    objArr31[length30] = objArr[length30 + 2];
                                                }
                                            } catch (ClassCastException e26) {
                                                throw new WrongType(e26, "lset-diff+intersection", 1, (Object) procedure51);
                                            }
                                        case 181:
                                            Procedure procedure53 = objArr[0];
                                            try {
                                                Procedure procedure54 = procedure53;
                                                Object obj31 = objArr[1];
                                                int length31 = objArr.length - 2;
                                                Object[] objArr32 = new Object[length31];
                                                while (true) {
                                                    length31--;
                                                    if (length31 < 0) {
                                                        return lsetDiff$PlIntersection$Ex$V(procedure54, obj31, objArr32);
                                                    }
                                                    objArr32[length31] = objArr[length31 + 2];
                                                }
                                            } catch (ClassCastException e27) {
                                                throw new WrongType(e27, "lset-diff+intersection!", 1, (Object) procedure53);
                                            }
                                        default:
                                            return super.applyN(moduleMethod, objArr);
                                    }
                            }
                    }
                } else {
                    int length32 = objArr.length - 4;
                    Procedure procedure55 = objArr[0];
                    try {
                        Procedure procedure56 = procedure55;
                        Procedure procedure57 = objArr[1];
                        try {
                            Procedure procedure58 = procedure57;
                            Procedure procedure59 = objArr[2];
                            try {
                                Procedure procedure60 = procedure59;
                                Object obj32 = objArr[3];
                                return length32 <= 0 ? unfoldRight(procedure56, procedure58, procedure60, obj32) : unfoldRight(procedure56, procedure58, procedure60, obj32, objArr[4]);
                            } catch (ClassCastException e28) {
                                throw new WrongType(e28, "unfold-right", 3, (Object) procedure59);
                            }
                        } catch (ClassCastException e29) {
                            throw new WrongType(e29, "unfold-right", 2, (Object) procedure57);
                        }
                    } catch (ClassCastException e30) {
                        throw new WrongType(e30, "unfold-right", 1, (Object) procedure55);
                    }
                }
            }
        }
    }

    /* compiled from: srfi1.scm */
    public class frame53 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn55;
        LList lists;

        public frame53() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 56, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1587");
            this.lambda$Fn55 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            if (moduleMethod.selector == 56) {
                return lambda72(obj) ? Boolean.TRUE : Boolean.FALSE;
            }
            return super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public boolean lambda72(Object obj) {
            frame54 frame54 = new frame54();
            frame54.staticLink = this;
            frame54.elt = obj;
            int i = 0;
            if (srfi1.any$V(frame54.lambda$Fn56, this.lists, new Object[0]) != Boolean.FALSE) {
                i = 1;
            }
            return (i + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 56) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame54 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn56;
        frame53 staticLink;

        public frame54() {
            ModuleMethod moduleMethod = new ModuleMethod(this, 55, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1588");
            this.lambda$Fn56 = moduleMethod;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            return moduleMethod.selector == 55 ? lambda73(obj) : super.apply1(moduleMethod, obj);
        }

        /* access modifiers changed from: package-private */
        public Object lambda73(Object obj) {
            return lists.member(this.elt, obj, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            if (moduleMethod.selector != 55) {
                return super.match1(moduleMethod, obj, callContext);
            }
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }
}
