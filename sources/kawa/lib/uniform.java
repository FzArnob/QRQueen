package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.LList;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;

/* compiled from: uniform.scm */
public class uniform extends ModuleBody {
    public static final uniform $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1;
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
    static final SimpleSymbol Lit2;
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
    static final SimpleSymbol Lit9;
    public static final ModuleMethod f32vector;
    public static final ModuleMethod f32vector$Mn$Grlist;
    public static final ModuleMethod f32vector$Mnlength;
    public static final ModuleMethod f32vector$Mnref;
    public static final ModuleMethod f32vector$Mnset$Ex;
    public static final ModuleMethod f32vector$Qu;
    public static final ModuleMethod f64vector;
    public static final ModuleMethod f64vector$Mn$Grlist;
    public static final ModuleMethod f64vector$Mnlength;
    public static final ModuleMethod f64vector$Mnref;
    public static final ModuleMethod f64vector$Mnset$Ex;
    public static final ModuleMethod f64vector$Qu;
    public static final ModuleMethod list$Mn$Grf32vector;
    public static final ModuleMethod list$Mn$Grf64vector;
    public static final ModuleMethod list$Mn$Grs16vector;
    public static final ModuleMethod list$Mn$Grs32vector;
    public static final ModuleMethod list$Mn$Grs64vector;
    public static final ModuleMethod list$Mn$Grs8vector;
    public static final ModuleMethod list$Mn$Gru16vector;
    public static final ModuleMethod list$Mn$Gru32vector;
    public static final ModuleMethod list$Mn$Gru64vector;
    public static final ModuleMethod list$Mn$Gru8vector;
    public static final ModuleMethod make$Mnf32vector;
    public static final ModuleMethod make$Mnf64vector;
    public static final ModuleMethod make$Mns16vector;
    public static final ModuleMethod make$Mns32vector;
    public static final ModuleMethod make$Mns64vector;
    public static final ModuleMethod make$Mns8vector;
    public static final ModuleMethod make$Mnu16vector;
    public static final ModuleMethod make$Mnu32vector;
    public static final ModuleMethod make$Mnu64vector;
    public static final ModuleMethod make$Mnu8vector;
    public static final ModuleMethod s16vector;
    public static final ModuleMethod s16vector$Mn$Grlist;
    public static final ModuleMethod s16vector$Mnlength;
    public static final ModuleMethod s16vector$Mnref;
    public static final ModuleMethod s16vector$Mnset$Ex;
    public static final ModuleMethod s16vector$Qu;
    public static final ModuleMethod s32vector;
    public static final ModuleMethod s32vector$Mn$Grlist;
    public static final ModuleMethod s32vector$Mnlength;
    public static final ModuleMethod s32vector$Mnref;
    public static final ModuleMethod s32vector$Mnset$Ex;
    public static final ModuleMethod s32vector$Qu;
    public static final ModuleMethod s64vector;
    public static final ModuleMethod s64vector$Mn$Grlist;
    public static final ModuleMethod s64vector$Mnlength;
    public static final ModuleMethod s64vector$Mnref;
    public static final ModuleMethod s64vector$Mnset$Ex;
    public static final ModuleMethod s64vector$Qu;
    public static final ModuleMethod s8vector;
    public static final ModuleMethod s8vector$Mn$Grlist;
    public static final ModuleMethod s8vector$Mnlength;
    public static final ModuleMethod s8vector$Mnref;
    public static final ModuleMethod s8vector$Mnset$Ex;
    public static final ModuleMethod s8vector$Qu;
    public static final ModuleMethod u16vector;
    public static final ModuleMethod u16vector$Mn$Grlist;
    public static final ModuleMethod u16vector$Mnlength;
    public static final ModuleMethod u16vector$Mnref;
    public static final ModuleMethod u16vector$Mnset$Ex;
    public static final ModuleMethod u16vector$Qu;
    public static final ModuleMethod u32vector;
    public static final ModuleMethod u32vector$Mn$Grlist;
    public static final ModuleMethod u32vector$Mnlength;
    public static final ModuleMethod u32vector$Mnref;
    public static final ModuleMethod u32vector$Mnset$Ex;
    public static final ModuleMethod u32vector$Qu;
    public static final ModuleMethod u64vector;
    public static final ModuleMethod u64vector$Mn$Grlist;
    public static final ModuleMethod u64vector$Mnlength;
    public static final ModuleMethod u64vector$Mnref;
    public static final ModuleMethod u64vector$Mnset$Ex;
    public static final ModuleMethod u64vector$Qu;
    public static final ModuleMethod u8vector;
    public static final ModuleMethod u8vector$Mn$Grlist;
    public static final ModuleMethod u8vector$Mnlength;
    public static final ModuleMethod u8vector$Mnref;
    public static final ModuleMethod u8vector$Mnset$Ex;
    public static final ModuleMethod u8vector$Qu;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("list->f64vector").readResolve();
        Lit80 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("f64vector->list").readResolve();
        Lit79 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("f64vector-set!").readResolve();
        Lit78 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("f64vector-ref").readResolve();
        Lit77 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("f64vector-length").readResolve();
        Lit76 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("f64vector").readResolve();
        Lit75 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("make-f64vector").readResolve();
        Lit74 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("f64vector?").readResolve();
        Lit73 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("list->f32vector").readResolve();
        Lit72 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("f32vector->list").readResolve();
        Lit71 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("f32vector-set!").readResolve();
        Lit70 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("f32vector-ref").readResolve();
        Lit69 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("f32vector-length").readResolve();
        Lit68 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("f32vector").readResolve();
        Lit67 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("make-f32vector").readResolve();
        Lit66 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("f32vector?").readResolve();
        Lit65 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("list->u64vector").readResolve();
        Lit64 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("u64vector->list").readResolve();
        Lit63 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("u64vector-set!").readResolve();
        Lit62 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("u64vector-ref").readResolve();
        Lit61 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("u64vector-length").readResolve();
        Lit60 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("u64vector").readResolve();
        Lit59 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("make-u64vector").readResolve();
        Lit58 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol9;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("u64vector?").readResolve();
        Lit57 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol10;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("list->s64vector").readResolve();
        Lit56 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol11;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("s64vector->list").readResolve();
        Lit55 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol12;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("s64vector-set!").readResolve();
        Lit54 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol13;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("s64vector-ref").readResolve();
        Lit53 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol14;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("s64vector-length").readResolve();
        Lit52 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol15;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("s64vector").readResolve();
        Lit51 = simpleSymbol45;
        SimpleSymbol simpleSymbol46 = simpleSymbol17;
        SimpleSymbol simpleSymbol47 = (SimpleSymbol) new SimpleSymbol("make-s64vector").readResolve();
        Lit50 = simpleSymbol47;
        SimpleSymbol simpleSymbol48 = simpleSymbol19;
        SimpleSymbol simpleSymbol49 = (SimpleSymbol) new SimpleSymbol("s64vector?").readResolve();
        Lit49 = simpleSymbol49;
        SimpleSymbol simpleSymbol50 = simpleSymbol21;
        SimpleSymbol simpleSymbol51 = (SimpleSymbol) new SimpleSymbol("list->u32vector").readResolve();
        Lit48 = simpleSymbol51;
        SimpleSymbol simpleSymbol52 = simpleSymbol23;
        SimpleSymbol simpleSymbol53 = (SimpleSymbol) new SimpleSymbol("u32vector->list").readResolve();
        Lit47 = simpleSymbol53;
        SimpleSymbol simpleSymbol54 = simpleSymbol25;
        SimpleSymbol simpleSymbol55 = (SimpleSymbol) new SimpleSymbol("u32vector-set!").readResolve();
        Lit46 = simpleSymbol55;
        SimpleSymbol simpleSymbol56 = simpleSymbol27;
        SimpleSymbol simpleSymbol57 = (SimpleSymbol) new SimpleSymbol("u32vector-ref").readResolve();
        Lit45 = simpleSymbol57;
        SimpleSymbol simpleSymbol58 = simpleSymbol29;
        SimpleSymbol simpleSymbol59 = (SimpleSymbol) new SimpleSymbol("u32vector-length").readResolve();
        Lit44 = simpleSymbol59;
        SimpleSymbol simpleSymbol60 = simpleSymbol31;
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) new SimpleSymbol("u32vector").readResolve();
        Lit43 = simpleSymbol61;
        SimpleSymbol simpleSymbol62 = simpleSymbol33;
        SimpleSymbol simpleSymbol63 = (SimpleSymbol) new SimpleSymbol("make-u32vector").readResolve();
        Lit42 = simpleSymbol63;
        SimpleSymbol simpleSymbol64 = simpleSymbol35;
        SimpleSymbol simpleSymbol65 = (SimpleSymbol) new SimpleSymbol("u32vector?").readResolve();
        Lit41 = simpleSymbol65;
        SimpleSymbol simpleSymbol66 = simpleSymbol37;
        SimpleSymbol simpleSymbol67 = (SimpleSymbol) new SimpleSymbol("list->s32vector").readResolve();
        Lit40 = simpleSymbol67;
        SimpleSymbol simpleSymbol68 = simpleSymbol39;
        SimpleSymbol simpleSymbol69 = (SimpleSymbol) new SimpleSymbol("s32vector->list").readResolve();
        Lit39 = simpleSymbol69;
        SimpleSymbol simpleSymbol70 = simpleSymbol41;
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) new SimpleSymbol("s32vector-set!").readResolve();
        Lit38 = simpleSymbol71;
        SimpleSymbol simpleSymbol72 = simpleSymbol43;
        SimpleSymbol simpleSymbol73 = (SimpleSymbol) new SimpleSymbol("s32vector-ref").readResolve();
        Lit37 = simpleSymbol73;
        SimpleSymbol simpleSymbol74 = simpleSymbol45;
        SimpleSymbol simpleSymbol75 = (SimpleSymbol) new SimpleSymbol("s32vector-length").readResolve();
        Lit36 = simpleSymbol75;
        SimpleSymbol simpleSymbol76 = simpleSymbol47;
        SimpleSymbol simpleSymbol77 = (SimpleSymbol) new SimpleSymbol("s32vector").readResolve();
        Lit35 = simpleSymbol77;
        SimpleSymbol simpleSymbol78 = simpleSymbol49;
        SimpleSymbol simpleSymbol79 = (SimpleSymbol) new SimpleSymbol("make-s32vector").readResolve();
        Lit34 = simpleSymbol79;
        SimpleSymbol simpleSymbol80 = simpleSymbol51;
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) new SimpleSymbol("s32vector?").readResolve();
        Lit33 = simpleSymbol81;
        SimpleSymbol simpleSymbol82 = simpleSymbol53;
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) new SimpleSymbol("list->u16vector").readResolve();
        Lit32 = simpleSymbol83;
        SimpleSymbol simpleSymbol84 = simpleSymbol55;
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) new SimpleSymbol("u16vector->list").readResolve();
        Lit31 = simpleSymbol85;
        SimpleSymbol simpleSymbol86 = simpleSymbol57;
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) new SimpleSymbol("u16vector-set!").readResolve();
        Lit30 = simpleSymbol87;
        SimpleSymbol simpleSymbol88 = simpleSymbol59;
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) new SimpleSymbol("u16vector-ref").readResolve();
        Lit29 = simpleSymbol89;
        SimpleSymbol simpleSymbol90 = simpleSymbol61;
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) new SimpleSymbol("u16vector-length").readResolve();
        Lit28 = simpleSymbol91;
        SimpleSymbol simpleSymbol92 = simpleSymbol63;
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) new SimpleSymbol("u16vector").readResolve();
        Lit27 = simpleSymbol93;
        SimpleSymbol simpleSymbol94 = simpleSymbol65;
        SimpleSymbol simpleSymbol95 = (SimpleSymbol) new SimpleSymbol("make-u16vector").readResolve();
        Lit26 = simpleSymbol95;
        SimpleSymbol simpleSymbol96 = simpleSymbol67;
        SimpleSymbol simpleSymbol97 = (SimpleSymbol) new SimpleSymbol("u16vector?").readResolve();
        Lit25 = simpleSymbol97;
        SimpleSymbol simpleSymbol98 = simpleSymbol69;
        SimpleSymbol simpleSymbol99 = (SimpleSymbol) new SimpleSymbol("list->s16vector").readResolve();
        Lit24 = simpleSymbol99;
        SimpleSymbol simpleSymbol100 = simpleSymbol71;
        SimpleSymbol simpleSymbol101 = (SimpleSymbol) new SimpleSymbol("s16vector->list").readResolve();
        Lit23 = simpleSymbol101;
        SimpleSymbol simpleSymbol102 = simpleSymbol73;
        SimpleSymbol simpleSymbol103 = (SimpleSymbol) new SimpleSymbol("s16vector-set!").readResolve();
        Lit22 = simpleSymbol103;
        SimpleSymbol simpleSymbol104 = simpleSymbol75;
        SimpleSymbol simpleSymbol105 = (SimpleSymbol) new SimpleSymbol("s16vector-ref").readResolve();
        Lit21 = simpleSymbol105;
        SimpleSymbol simpleSymbol106 = simpleSymbol77;
        SimpleSymbol simpleSymbol107 = (SimpleSymbol) new SimpleSymbol("s16vector-length").readResolve();
        Lit20 = simpleSymbol107;
        SimpleSymbol simpleSymbol108 = simpleSymbol79;
        SimpleSymbol simpleSymbol109 = (SimpleSymbol) new SimpleSymbol("s16vector").readResolve();
        Lit19 = simpleSymbol109;
        SimpleSymbol simpleSymbol110 = simpleSymbol81;
        SimpleSymbol simpleSymbol111 = (SimpleSymbol) new SimpleSymbol("make-s16vector").readResolve();
        Lit18 = simpleSymbol111;
        SimpleSymbol simpleSymbol112 = simpleSymbol83;
        SimpleSymbol simpleSymbol113 = (SimpleSymbol) new SimpleSymbol("s16vector?").readResolve();
        Lit17 = simpleSymbol113;
        SimpleSymbol simpleSymbol114 = simpleSymbol85;
        SimpleSymbol simpleSymbol115 = (SimpleSymbol) new SimpleSymbol("list->u8vector").readResolve();
        Lit16 = simpleSymbol115;
        SimpleSymbol simpleSymbol116 = simpleSymbol87;
        SimpleSymbol simpleSymbol117 = (SimpleSymbol) new SimpleSymbol("u8vector->list").readResolve();
        Lit15 = simpleSymbol117;
        SimpleSymbol simpleSymbol118 = simpleSymbol89;
        SimpleSymbol simpleSymbol119 = (SimpleSymbol) new SimpleSymbol("u8vector-set!").readResolve();
        Lit14 = simpleSymbol119;
        SimpleSymbol simpleSymbol120 = simpleSymbol91;
        SimpleSymbol simpleSymbol121 = (SimpleSymbol) new SimpleSymbol("u8vector-ref").readResolve();
        Lit13 = simpleSymbol121;
        SimpleSymbol simpleSymbol122 = simpleSymbol93;
        SimpleSymbol simpleSymbol123 = (SimpleSymbol) new SimpleSymbol("u8vector-length").readResolve();
        Lit12 = simpleSymbol123;
        SimpleSymbol simpleSymbol124 = simpleSymbol95;
        SimpleSymbol simpleSymbol125 = (SimpleSymbol) new SimpleSymbol("u8vector").readResolve();
        Lit11 = simpleSymbol125;
        SimpleSymbol simpleSymbol126 = simpleSymbol97;
        SimpleSymbol simpleSymbol127 = (SimpleSymbol) new SimpleSymbol("make-u8vector").readResolve();
        Lit10 = simpleSymbol127;
        SimpleSymbol simpleSymbol128 = simpleSymbol99;
        SimpleSymbol simpleSymbol129 = (SimpleSymbol) new SimpleSymbol("u8vector?").readResolve();
        Lit9 = simpleSymbol129;
        SimpleSymbol simpleSymbol130 = simpleSymbol101;
        SimpleSymbol simpleSymbol131 = (SimpleSymbol) new SimpleSymbol("list->s8vector").readResolve();
        Lit8 = simpleSymbol131;
        SimpleSymbol simpleSymbol132 = simpleSymbol103;
        SimpleSymbol simpleSymbol133 = (SimpleSymbol) new SimpleSymbol("s8vector->list").readResolve();
        Lit7 = simpleSymbol133;
        SimpleSymbol simpleSymbol134 = simpleSymbol105;
        SimpleSymbol simpleSymbol135 = (SimpleSymbol) new SimpleSymbol("s8vector-set!").readResolve();
        Lit6 = simpleSymbol135;
        SimpleSymbol simpleSymbol136 = simpleSymbol107;
        SimpleSymbol simpleSymbol137 = (SimpleSymbol) new SimpleSymbol("s8vector-ref").readResolve();
        Lit5 = simpleSymbol137;
        SimpleSymbol simpleSymbol138 = simpleSymbol109;
        SimpleSymbol simpleSymbol139 = (SimpleSymbol) new SimpleSymbol("s8vector-length").readResolve();
        Lit4 = simpleSymbol139;
        SimpleSymbol simpleSymbol140 = simpleSymbol111;
        SimpleSymbol simpleSymbol141 = (SimpleSymbol) new SimpleSymbol("s8vector").readResolve();
        Lit3 = simpleSymbol141;
        SimpleSymbol simpleSymbol142 = simpleSymbol113;
        SimpleSymbol simpleSymbol143 = (SimpleSymbol) new SimpleSymbol("make-s8vector").readResolve();
        Lit2 = simpleSymbol143;
        SimpleSymbol simpleSymbol144 = simpleSymbol115;
        SimpleSymbol simpleSymbol145 = (SimpleSymbol) new SimpleSymbol("s8vector?").readResolve();
        Lit1 = simpleSymbol145;
        uniform uniform = new uniform();
        $instance = uniform;
        s8vector$Qu = new ModuleMethod(uniform, 1, simpleSymbol145, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mns8vector = new ModuleMethod(uniform, 2, simpleSymbol143, 8193);
        s8vector = new ModuleMethod(uniform, 4, simpleSymbol141, -4096);
        s8vector$Mnlength = new ModuleMethod(uniform, 5, simpleSymbol139, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s8vector$Mnref = new ModuleMethod(uniform, 6, simpleSymbol137, 8194);
        s8vector$Mnset$Ex = new ModuleMethod(uniform, 7, simpleSymbol135, 12291);
        s8vector$Mn$Grlist = new ModuleMethod(uniform, 8, simpleSymbol133, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs8vector = new ModuleMethod(uniform, 9, simpleSymbol131, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u8vector$Qu = new ModuleMethod(uniform, 10, simpleSymbol129, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnu8vector = new ModuleMethod(uniform, 11, simpleSymbol127, 8193);
        u8vector = new ModuleMethod(uniform, 13, simpleSymbol125, -4096);
        u8vector$Mnlength = new ModuleMethod(uniform, 14, simpleSymbol123, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u8vector$Mnref = new ModuleMethod(uniform, 15, simpleSymbol121, 8194);
        u8vector$Mnset$Ex = new ModuleMethod(uniform, 16, simpleSymbol119, 12291);
        u8vector$Mn$Grlist = new ModuleMethod(uniform, 17, simpleSymbol117, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru8vector = new ModuleMethod(uniform, 18, simpleSymbol144, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s16vector$Qu = new ModuleMethod(uniform, 19, simpleSymbol142, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mns16vector = new ModuleMethod(uniform, 20, simpleSymbol140, 8193);
        s16vector = new ModuleMethod(uniform, 22, simpleSymbol138, -4096);
        s16vector$Mnlength = new ModuleMethod(uniform, 23, simpleSymbol136, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s16vector$Mnref = new ModuleMethod(uniform, 24, simpleSymbol134, 8194);
        s16vector$Mnset$Ex = new ModuleMethod(uniform, 25, simpleSymbol132, 12291);
        s16vector$Mn$Grlist = new ModuleMethod(uniform, 26, simpleSymbol130, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs16vector = new ModuleMethod(uniform, 27, simpleSymbol128, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u16vector$Qu = new ModuleMethod(uniform, 28, simpleSymbol126, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnu16vector = new ModuleMethod(uniform, 29, simpleSymbol124, 8193);
        u16vector = new ModuleMethod(uniform, 31, simpleSymbol122, -4096);
        u16vector$Mnlength = new ModuleMethod(uniform, 32, simpleSymbol120, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u16vector$Mnref = new ModuleMethod(uniform, 33, simpleSymbol118, 8194);
        u16vector$Mnset$Ex = new ModuleMethod(uniform, 34, simpleSymbol116, 12291);
        u16vector$Mn$Grlist = new ModuleMethod(uniform, 35, simpleSymbol114, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru16vector = new ModuleMethod(uniform, 36, simpleSymbol112, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s32vector$Qu = new ModuleMethod(uniform, 37, simpleSymbol110, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mns32vector = new ModuleMethod(uniform, 38, simpleSymbol108, 8193);
        s32vector = new ModuleMethod(uniform, 40, simpleSymbol106, -4096);
        s32vector$Mnlength = new ModuleMethod(uniform, 41, simpleSymbol104, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s32vector$Mnref = new ModuleMethod(uniform, 42, simpleSymbol102, 8194);
        s32vector$Mnset$Ex = new ModuleMethod(uniform, 43, simpleSymbol100, 12291);
        s32vector$Mn$Grlist = new ModuleMethod(uniform, 44, simpleSymbol98, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs32vector = new ModuleMethod(uniform, 45, simpleSymbol96, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u32vector$Qu = new ModuleMethod(uniform, 46, simpleSymbol94, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnu32vector = new ModuleMethod(uniform, 47, simpleSymbol92, 8193);
        u32vector = new ModuleMethod(uniform, 49, simpleSymbol90, -4096);
        u32vector$Mnlength = new ModuleMethod(uniform, 50, simpleSymbol88, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u32vector$Mnref = new ModuleMethod(uniform, 51, simpleSymbol86, 8194);
        u32vector$Mnset$Ex = new ModuleMethod(uniform, 52, simpleSymbol84, 12291);
        u32vector$Mn$Grlist = new ModuleMethod(uniform, 53, simpleSymbol82, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru32vector = new ModuleMethod(uniform, 54, simpleSymbol80, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s64vector$Qu = new ModuleMethod(uniform, 55, simpleSymbol78, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mns64vector = new ModuleMethod(uniform, 56, simpleSymbol76, 8193);
        s64vector = new ModuleMethod(uniform, 58, simpleSymbol74, -4096);
        s64vector$Mnlength = new ModuleMethod(uniform, 59, simpleSymbol72, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        s64vector$Mnref = new ModuleMethod(uniform, 60, simpleSymbol70, 8194);
        s64vector$Mnset$Ex = new ModuleMethod(uniform, 61, simpleSymbol68, 12291);
        s64vector$Mn$Grlist = new ModuleMethod(uniform, 62, simpleSymbol66, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grs64vector = new ModuleMethod(uniform, 63, simpleSymbol64, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u64vector$Qu = new ModuleMethod(uniform, 64, simpleSymbol62, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnu64vector = new ModuleMethod(uniform, 65, simpleSymbol60, 8193);
        u64vector = new ModuleMethod(uniform, 67, simpleSymbol58, -4096);
        u64vector$Mnlength = new ModuleMethod(uniform, 68, simpleSymbol56, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        u64vector$Mnref = new ModuleMethod(uniform, 69, simpleSymbol54, 8194);
        u64vector$Mnset$Ex = new ModuleMethod(uniform, 70, simpleSymbol52, 12291);
        u64vector$Mn$Grlist = new ModuleMethod(uniform, 71, simpleSymbol50, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Gru64vector = new ModuleMethod(uniform, 72, simpleSymbol48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f32vector$Qu = new ModuleMethod(uniform, 73, simpleSymbol46, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnf32vector = new ModuleMethod(uniform, 74, simpleSymbol44, 8193);
        f32vector = new ModuleMethod(uniform, 76, simpleSymbol42, -4096);
        f32vector$Mnlength = new ModuleMethod(uniform, 77, simpleSymbol40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f32vector$Mnref = new ModuleMethod(uniform, 78, simpleSymbol38, 8194);
        f32vector$Mnset$Ex = new ModuleMethod(uniform, 79, simpleSymbol36, 12291);
        f32vector$Mn$Grlist = new ModuleMethod(uniform, 80, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grf32vector = new ModuleMethod(uniform, 81, simpleSymbol32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f64vector$Qu = new ModuleMethod(uniform, 82, simpleSymbol30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnf64vector = new ModuleMethod(uniform, 83, simpleSymbol28, 8193);
        f64vector = new ModuleMethod(uniform, 85, simpleSymbol26, -4096);
        f64vector$Mnlength = new ModuleMethod(uniform, 86, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f64vector$Mnref = new ModuleMethod(uniform, 87, simpleSymbol22, 8194);
        f64vector$Mnset$Ex = new ModuleMethod(uniform, 88, simpleSymbol20, 12291);
        f64vector$Mn$Grlist = new ModuleMethod(uniform, 89, simpleSymbol18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mn$Grf64vector = new ModuleMethod(uniform, 90, simpleSymbol16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        uniform.run();
    }

    public uniform() {
        ModuleInfo.register(this);
    }

    public static F32Vector makeF32vector(int i) {
        return makeF32vector(i, 0.0f);
    }

    public static F64Vector makeF64vector(int i) {
        return makeF64vector(i, 0.0d);
    }

    public static S16Vector makeS16vector(int i) {
        return makeS16vector(i, 0);
    }

    public static S32Vector makeS32vector(int i) {
        return makeS32vector(i, 0);
    }

    public static S64Vector makeS64vector(int i) {
        return makeS64vector(i, 0);
    }

    public static S8Vector makeS8vector(int i) {
        return makeS8vector(i, 0);
    }

    public static U16Vector makeU16vector(int i) {
        return makeU16vector(i, 0);
    }

    public static U32Vector makeU32vector(int i) {
        return makeU32vector(i, 0);
    }

    public static U64Vector makeU64vector(int i) {
        return makeU64vector(i, Lit0);
    }

    public static U8Vector makeU8vector(int i) {
        return makeU8vector(i, 0);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 1:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 2:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                if (!(obj instanceof S8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                if (!(obj instanceof S8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
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
            case 14:
                if (!(obj instanceof U8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 17:
                if (!(obj instanceof U8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                if (!(obj instanceof LList)) {
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
            case 20:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 23:
                if (!(obj instanceof S16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                if (!(obj instanceof S16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 27:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 28:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 32:
                if (!(obj instanceof U16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 35:
                if (!(obj instanceof U16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 36:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 37:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 38:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 41:
                if (!(obj instanceof S32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 44:
                if (!(obj instanceof S32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 45:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 46:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 47:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 50:
                if (!(obj instanceof U32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 53:
                if (!(obj instanceof U32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 54:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 55:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 56:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 59:
                if (!(obj instanceof S64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 62:
                if (!(obj instanceof S64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 63:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 64:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 65:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 68:
                if (!(obj instanceof U64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 71:
                if (!(obj instanceof U64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 72:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 73:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 74:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 77:
                if (!(obj instanceof F32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 80:
                if (!(obj instanceof F32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 81:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 82:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 83:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 86:
                if (!(obj instanceof F64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 89:
                if (!(obj instanceof F64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 90:
                if (!(obj instanceof LList)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod, obj, callContext);
        }
    }

    public static boolean isS8vector(Object obj) {
        return obj instanceof S8Vector;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        switch (moduleMethod.selector) {
            case 2:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 6:
                if (!(obj instanceof S8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 11:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 15:
                if (!(obj instanceof U8Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 20:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 24:
                if (!(obj instanceof S16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 29:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 33:
                if (!(obj instanceof U16Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 38:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 42:
                if (!(obj instanceof S32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 47:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 51:
                if (!(obj instanceof U32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 56:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 60:
                if (!(obj instanceof S64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 65:
                callContext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 69:
                if (!(obj instanceof U64Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 74:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 78:
                if (!(obj instanceof F32Vector)) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 83:
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            case 87:
                if (!(obj instanceof F64Vector)) {
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
    }

    public static S8Vector makeS8vector(int i, int i2) {
        return new S8Vector(i, (byte) i2);
    }

    public static S8Vector s8vector$V(Object[] objArr) {
        return list$To$S8vector(LList.makeList(objArr, 0));
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 4) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 13) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 22) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 31) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 40) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 49) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 58) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 67) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i == 76) {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        } else if (i != 85) {
            return super.matchN(moduleMethod, objArr, callContext);
        } else {
            callContext.values = objArr;
            callContext.proc = moduleMethod;
            callContext.pc = 5;
            return 0;
        }
    }

    public static int s8vectorLength(S8Vector s8Vector) {
        return s8Vector.size();
    }

    public static int s8vectorRef(S8Vector s8Vector, int i) {
        return s8Vector.intAt(i);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 7) {
            if (i != 16) {
                if (i != 25) {
                    if (i != 34) {
                        if (i != 43) {
                            if (i != 52) {
                                if (i != 61) {
                                    if (i != 70) {
                                        if (i != 79) {
                                            if (i != 88) {
                                                return super.match3(moduleMethod, obj, obj2, obj3, callContext);
                                            }
                                            if (!(obj instanceof F64Vector)) {
                                                return -786431;
                                            }
                                            callContext.value1 = obj;
                                            callContext.value2 = obj2;
                                            callContext.value3 = obj3;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 3;
                                            return 0;
                                        } else if (!(obj instanceof F32Vector)) {
                                            return -786431;
                                        } else {
                                            callContext.value1 = obj;
                                            callContext.value2 = obj2;
                                            callContext.value3 = obj3;
                                            callContext.proc = moduleMethod;
                                            callContext.pc = 3;
                                            return 0;
                                        }
                                    } else if (!(obj instanceof U64Vector)) {
                                        return -786431;
                                    } else {
                                        callContext.value1 = obj;
                                        callContext.value2 = obj2;
                                        if (IntNum.asIntNumOrNull(obj3) == null) {
                                            return -786429;
                                        }
                                        callContext.value3 = obj3;
                                        callContext.proc = moduleMethod;
                                        callContext.pc = 3;
                                        return 0;
                                    }
                                } else if (!(obj instanceof S64Vector)) {
                                    return -786431;
                                } else {
                                    callContext.value1 = obj;
                                    callContext.value2 = obj2;
                                    callContext.value3 = obj3;
                                    callContext.proc = moduleMethod;
                                    callContext.pc = 3;
                                    return 0;
                                }
                            } else if (!(obj instanceof U32Vector)) {
                                return -786431;
                            } else {
                                callContext.value1 = obj;
                                callContext.value2 = obj2;
                                callContext.value3 = obj3;
                                callContext.proc = moduleMethod;
                                callContext.pc = 3;
                                return 0;
                            }
                        } else if (!(obj instanceof S32Vector)) {
                            return -786431;
                        } else {
                            callContext.value1 = obj;
                            callContext.value2 = obj2;
                            callContext.value3 = obj3;
                            callContext.proc = moduleMethod;
                            callContext.pc = 3;
                            return 0;
                        }
                    } else if (!(obj instanceof U16Vector)) {
                        return -786431;
                    } else {
                        callContext.value1 = obj;
                        callContext.value2 = obj2;
                        callContext.value3 = obj3;
                        callContext.proc = moduleMethod;
                        callContext.pc = 3;
                        return 0;
                    }
                } else if (!(obj instanceof S16Vector)) {
                    return -786431;
                } else {
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.proc = moduleMethod;
                    callContext.pc = 3;
                    return 0;
                }
            } else if (!(obj instanceof U8Vector)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.value2 = obj2;
                callContext.value3 = obj3;
                callContext.proc = moduleMethod;
                callContext.pc = 3;
                return 0;
            }
        } else if (!(obj instanceof S8Vector)) {
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

    public static void s8vectorSet$Ex(S8Vector s8Vector, int i, int i2) {
        s8Vector.setByteAt(i, (byte) i2);
    }

    public static LList s8vector$To$List(S8Vector s8Vector) {
        return LList.makeList(s8Vector);
    }

    public static S8Vector list$To$S8vector(LList lList) {
        return new S8Vector((Sequence) lList);
    }

    public static boolean isU8vector(Object obj) {
        return obj instanceof U8Vector;
    }

    public static U8Vector makeU8vector(int i, int i2) {
        return new U8Vector(i, (byte) i2);
    }

    public static U8Vector u8vector$V(Object[] objArr) {
        return list$To$U8vector(LList.makeList(objArr, 0));
    }

    public static int u8vectorLength(U8Vector u8Vector) {
        return u8Vector.size();
    }

    public static int u8vectorRef(U8Vector u8Vector, int i) {
        return u8Vector.intAt(i);
    }

    public static void u8vectorSet$Ex(U8Vector u8Vector, int i, int i2) {
        u8Vector.setByteAt(i, (byte) i2);
    }

    public static LList u8vector$To$List(U8Vector u8Vector) {
        return LList.makeList(u8Vector);
    }

    public static U8Vector list$To$U8vector(LList lList) {
        return new U8Vector((Sequence) lList);
    }

    public static boolean isS16vector(Object obj) {
        return obj instanceof S16Vector;
    }

    public static S16Vector makeS16vector(int i, int i2) {
        return new S16Vector(i, (short) i2);
    }

    public static S16Vector s16vector$V(Object[] objArr) {
        return list$To$S16vector(LList.makeList(objArr, 0));
    }

    public static int s16vectorLength(S16Vector s16Vector) {
        return s16Vector.size();
    }

    public static int s16vectorRef(S16Vector s16Vector, int i) {
        return s16Vector.intAt(i);
    }

    public static void s16vectorSet$Ex(S16Vector s16Vector, int i, int i2) {
        s16Vector.setShortAt(i, (short) i2);
    }

    public static LList s16vector$To$List(S16Vector s16Vector) {
        return LList.makeList(s16Vector);
    }

    public static S16Vector list$To$S16vector(LList lList) {
        return new S16Vector((Sequence) lList);
    }

    public static boolean isU16vector(Object obj) {
        return obj instanceof U16Vector;
    }

    public static U16Vector makeU16vector(int i, int i2) {
        return new U16Vector(i, (short) i2);
    }

    public static U16Vector u16vector$V(Object[] objArr) {
        return list$To$U16vector(LList.makeList(objArr, 0));
    }

    public static int u16vectorLength(U16Vector u16Vector) {
        return u16Vector.size();
    }

    public static int u16vectorRef(U16Vector u16Vector, int i) {
        return u16Vector.intAt(i);
    }

    public static void u16vectorSet$Ex(U16Vector u16Vector, int i, int i2) {
        u16Vector.setShortAt(i, (short) i2);
    }

    public static LList u16vector$To$List(U16Vector u16Vector) {
        return LList.makeList(u16Vector);
    }

    public static U16Vector list$To$U16vector(LList lList) {
        return new U16Vector((Sequence) lList);
    }

    public static boolean isS32vector(Object obj) {
        return obj instanceof S32Vector;
    }

    public static S32Vector makeS32vector(int i, int i2) {
        return new S32Vector(i, i2);
    }

    public static S32Vector s32vector$V(Object[] objArr) {
        return list$To$S32vector(LList.makeList(objArr, 0));
    }

    public static int s32vectorLength(S32Vector s32Vector) {
        return s32Vector.size();
    }

    public static int s32vectorRef(S32Vector s32Vector, int i) {
        return s32Vector.intAt(i);
    }

    public static void s32vectorSet$Ex(S32Vector s32Vector, int i, int i2) {
        s32Vector.setIntAt(i, i2);
    }

    public static LList s32vector$To$List(S32Vector s32Vector) {
        return LList.makeList(s32Vector);
    }

    public static S32Vector list$To$S32vector(LList lList) {
        return new S32Vector((Sequence) lList);
    }

    public static boolean isU32vector(Object obj) {
        return obj instanceof U32Vector;
    }

    public static U32Vector makeU32vector(int i, long j) {
        return new U32Vector(i, (int) j);
    }

    public static U32Vector u32vector$V(Object[] objArr) {
        return list$To$U32vector(LList.makeList(objArr, 0));
    }

    public static int u32vectorLength(U32Vector u32Vector) {
        return u32Vector.size();
    }

    public static long u32vectorRef(U32Vector u32Vector, int i) {
        return ((Number) u32Vector.get(i)).longValue();
    }

    public static void u32vectorSet$Ex(U32Vector u32Vector, int i, long j) {
        u32Vector.setIntAt(i, (int) j);
    }

    public static LList u32vector$To$List(U32Vector u32Vector) {
        return LList.makeList(u32Vector);
    }

    public static U32Vector list$To$U32vector(LList lList) {
        return new U32Vector((Sequence) lList);
    }

    public static boolean isS64vector(Object obj) {
        return obj instanceof S64Vector;
    }

    public static S64Vector makeS64vector(int i, long j) {
        return new S64Vector(i, j);
    }

    public static S64Vector s64vector$V(Object[] objArr) {
        return list$To$S64vector(LList.makeList(objArr, 0));
    }

    public static int s64vectorLength(S64Vector s64Vector) {
        return s64Vector.size();
    }

    public static long s64vectorRef(S64Vector s64Vector, int i) {
        return s64Vector.longAt(i);
    }

    public static void s64vectorSet$Ex(S64Vector s64Vector, int i, long j) {
        s64Vector.setLongAt(i, j);
    }

    public static LList s64vector$To$List(S64Vector s64Vector) {
        return LList.makeList(s64Vector);
    }

    public static S64Vector list$To$S64vector(LList lList) {
        return new S64Vector((Sequence) lList);
    }

    public static boolean isU64vector(Object obj) {
        return obj instanceof U64Vector;
    }

    public static U64Vector makeU64vector(int i, IntNum intNum) {
        try {
            return new U64Vector(i, intNum.longValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.U64Vector.<init>(int,long)", 2, (Object) intNum);
        }
    }

    public static U64Vector u64vector$V(Object[] objArr) {
        return list$To$U64vector(LList.makeList(objArr, 0));
    }

    public static int u64vectorLength(U64Vector u64Vector) {
        return u64Vector.size();
    }

    public static IntNum u64vectorRef(U64Vector u64Vector, int i) {
        return LangObjType.coerceIntNum(u64Vector.get(i));
    }

    public static void u64vectorSet$Ex(U64Vector u64Vector, int i, IntNum intNum) {
        try {
            u64Vector.setLongAt(i, intNum.longValue());
        } catch (ClassCastException e) {
            throw new WrongType(e, "gnu.lists.U64Vector.setLongAt(int,long)", 3, (Object) intNum);
        }
    }

    public static LList u64vector$To$List(U64Vector u64Vector) {
        return LList.makeList(u64Vector);
    }

    public static U64Vector list$To$U64vector(LList lList) {
        return new U64Vector((Sequence) lList);
    }

    public static boolean isF32vector(Object obj) {
        return obj instanceof F32Vector;
    }

    public static F32Vector makeF32vector(int i, float f) {
        return new F32Vector(i, f);
    }

    public static F32Vector f32vector$V(Object[] objArr) {
        return list$To$F32vector(LList.makeList(objArr, 0));
    }

    public static int f32vectorLength(F32Vector f32Vector) {
        return f32Vector.size();
    }

    public static float f32vectorRef(F32Vector f32Vector, int i) {
        return f32Vector.floatAt(i);
    }

    public static void f32vectorSet$Ex(F32Vector f32Vector, int i, float f) {
        f32Vector.setFloatAt(i, f);
    }

    public static LList f32vector$To$List(F32Vector f32Vector) {
        return LList.makeList(f32Vector);
    }

    public static F32Vector list$To$F32vector(LList lList) {
        return new F32Vector((Sequence) lList);
    }

    public static boolean isF64vector(Object obj) {
        return obj instanceof F64Vector;
    }

    public static F64Vector makeF64vector(int i, double d) {
        return new F64Vector(i, d);
    }

    public static F64Vector f64vector$V(Object[] objArr) {
        return list$To$F64vector(LList.makeList(objArr, 0));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 4) {
            return s8vector$V(objArr);
        }
        if (i == 13) {
            return u8vector$V(objArr);
        }
        if (i == 22) {
            return s16vector$V(objArr);
        }
        if (i == 31) {
            return u16vector$V(objArr);
        }
        if (i == 40) {
            return s32vector$V(objArr);
        }
        if (i == 49) {
            return u32vector$V(objArr);
        }
        if (i == 58) {
            return s64vector$V(objArr);
        }
        if (i == 67) {
            return u64vector$V(objArr);
        }
        if (i == 76) {
            return f32vector$V(objArr);
        }
        if (i != 85) {
            return super.applyN(moduleMethod, objArr);
        }
        return f64vector$V(objArr);
    }

    public static int f64vectorLength(F64Vector f64Vector) {
        return f64Vector.size();
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Object obj3 = obj;
        Object obj4 = obj2;
        String str = "u16vector-ref";
        String str2 = "make-u16vector";
        String str3 = "s16vector-ref";
        String str4 = "make-s16vector";
        String str5 = "u8vector-ref";
        String str6 = "make-u8vector";
        String str7 = "s8vector-ref";
        String str8 = "make-s8vector";
        String str9 = "make-s32vector";
        switch (moduleMethod.selector) {
            case 2:
                try {
                    try {
                        return makeS8vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e) {
                        throw new WrongType(e, str8, 2, obj4);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, str8, 1, obj3);
                }
            case 6:
                try {
                    try {
                        return Integer.valueOf(s8vectorRef((S8Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, str7, 2, obj4);
                    }
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, str7, 1, obj3);
                }
            case 11:
                try {
                    try {
                        return makeU8vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e5) {
                        throw new WrongType(e5, str6, 2, obj4);
                    }
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, str6, 1, obj3);
                }
            case 15:
                try {
                    try {
                        return Integer.valueOf(u8vectorRef((U8Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, str5, 2, obj4);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, str5, 1, obj3);
                }
            case 20:
                try {
                    try {
                        return makeS16vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e9) {
                        throw new WrongType(e9, str4, 2, obj4);
                    }
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, str4, 1, obj3);
                }
            case 24:
                try {
                    try {
                        return Integer.valueOf(s16vectorRef((S16Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e11) {
                        throw new WrongType(e11, str3, 2, obj4);
                    }
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, str3, 1, obj3);
                }
            case 29:
                try {
                    try {
                        return makeU16vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, str2, 2, obj4);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, str2, 1, obj3);
                }
            case 33:
                try {
                    try {
                        return Integer.valueOf(u16vectorRef((U16Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e15) {
                        throw new WrongType(e15, str, 2, obj4);
                    }
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, str, 1, obj3);
                }
            case 38:
                try {
                    try {
                        return makeS32vector(((Number) obj3).intValue(), ((Number) obj4).intValue());
                    } catch (ClassCastException e17) {
                        throw new WrongType(e17, str9, 2, obj4);
                    }
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, str9, 1, obj3);
                }
            case 42:
                try {
                    try {
                        return Integer.valueOf(s32vectorRef((S32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e19) {
                        throw new WrongType(e19, "s32vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "s32vector-ref", 1, obj3);
                }
            case 47:
                try {
                    try {
                        return makeU32vector(((Number) obj3).intValue(), ((Number) obj4).longValue());
                    } catch (ClassCastException e21) {
                        throw new WrongType(e21, "make-u32vector", 2, obj4);
                    }
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "make-u32vector", 1, obj3);
                }
            case 51:
                try {
                    try {
                        return Long.valueOf(u32vectorRef((U32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e23) {
                        throw new WrongType(e23, "u32vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e24) {
                    throw new WrongType(e24, "u32vector-ref", 1, obj3);
                }
            case 56:
                try {
                    try {
                        return makeS64vector(((Number) obj3).intValue(), ((Number) obj4).longValue());
                    } catch (ClassCastException e25) {
                        throw new WrongType(e25, "make-s64vector", 2, obj4);
                    }
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "make-s64vector", 1, obj3);
                }
            case 60:
                try {
                    try {
                        return Long.valueOf(s64vectorRef((S64Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e27) {
                        throw new WrongType(e27, "s64vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e28) {
                    throw new WrongType(e28, "s64vector-ref", 1, obj3);
                }
            case 65:
                try {
                    try {
                        return makeU64vector(((Number) obj3).intValue(), LangObjType.coerceIntNum(obj2));
                    } catch (ClassCastException e29) {
                        throw new WrongType(e29, "make-u64vector", 2, obj4);
                    }
                } catch (ClassCastException e30) {
                    throw new WrongType(e30, "make-u64vector", 1, obj3);
                }
            case 69:
                try {
                    try {
                        return u64vectorRef((U64Vector) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e31) {
                        throw new WrongType(e31, "u64vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e32) {
                    throw new WrongType(e32, "u64vector-ref", 1, obj3);
                }
            case 74:
                try {
                    try {
                        return makeF32vector(((Number) obj3).intValue(), ((Number) obj4).floatValue());
                    } catch (ClassCastException e33) {
                        throw new WrongType(e33, "make-f32vector", 2, obj4);
                    }
                } catch (ClassCastException e34) {
                    throw new WrongType(e34, "make-f32vector", 1, obj3);
                }
            case 78:
                try {
                    try {
                        return Float.valueOf(f32vectorRef((F32Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e35) {
                        throw new WrongType(e35, "f32vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e36) {
                    throw new WrongType(e36, "f32vector-ref", 1, obj3);
                }
            case 83:
                try {
                    try {
                        return makeF64vector(((Number) obj3).intValue(), ((Number) obj4).doubleValue());
                    } catch (ClassCastException e37) {
                        throw new WrongType(e37, "make-f64vector", 2, obj4);
                    }
                } catch (ClassCastException e38) {
                    throw new WrongType(e38, "make-f64vector", 1, obj3);
                }
            case 87:
                try {
                    try {
                        return Double.valueOf(f64vectorRef((F64Vector) obj3, ((Number) obj4).intValue()));
                    } catch (ClassCastException e39) {
                        throw new WrongType(e39, "f64vector-ref", 2, obj4);
                    }
                } catch (ClassCastException e40) {
                    throw new WrongType(e40, "f64vector-ref", 1, obj3);
                }
            default:
                return super.apply2(moduleMethod, obj, obj2);
        }
    }

    public static double f64vectorRef(F64Vector f64Vector, int i) {
        return f64Vector.doubleAt(i);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        int i = moduleMethod.selector;
        if (i == 7) {
            try {
                try {
                    try {
                        s8vectorSet$Ex((S8Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "s8vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "s8vector-set!", 2, obj5);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "s8vector-set!", 1, obj4);
            }
        } else if (i == 16) {
            try {
                try {
                    try {
                        u8vectorSet$Ex((U8Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        return Values.empty;
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "u8vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "u8vector-set!", 2, obj5);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "u8vector-set!", 1, obj4);
            }
        } else if (i == 25) {
            try {
                try {
                    try {
                        s16vectorSet$Ex((S16Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        return Values.empty;
                    } catch (ClassCastException e7) {
                        throw new WrongType(e7, "s16vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "s16vector-set!", 2, obj5);
                }
            } catch (ClassCastException e9) {
                throw new WrongType(e9, "s16vector-set!", 1, obj4);
            }
        } else if (i == 34) {
            try {
                try {
                    try {
                        u16vectorSet$Ex((U16Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        return Values.empty;
                    } catch (ClassCastException e10) {
                        throw new WrongType(e10, "u16vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "u16vector-set!", 2, obj5);
                }
            } catch (ClassCastException e12) {
                throw new WrongType(e12, "u16vector-set!", 1, obj4);
            }
        } else if (i == 43) {
            try {
                try {
                    try {
                        s32vectorSet$Ex((S32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        return Values.empty;
                    } catch (ClassCastException e13) {
                        throw new WrongType(e13, "s32vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "s32vector-set!", 2, obj5);
                }
            } catch (ClassCastException e15) {
                throw new WrongType(e15, "s32vector-set!", 1, obj4);
            }
        } else if (i == 52) {
            try {
                try {
                    try {
                        u32vectorSet$Ex((U32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).longValue());
                        return Values.empty;
                    } catch (ClassCastException e16) {
                        throw new WrongType(e16, "u32vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "u32vector-set!", 2, obj5);
                }
            } catch (ClassCastException e18) {
                throw new WrongType(e18, "u32vector-set!", 1, obj4);
            }
        } else if (i == 61) {
            try {
                try {
                    try {
                        s64vectorSet$Ex((S64Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).longValue());
                        return Values.empty;
                    } catch (ClassCastException e19) {
                        throw new WrongType(e19, "s64vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "s64vector-set!", 2, obj5);
                }
            } catch (ClassCastException e21) {
                throw new WrongType(e21, "s64vector-set!", 1, obj4);
            }
        } else if (i == 70) {
            try {
                try {
                    try {
                        u64vectorSet$Ex((U64Vector) obj4, ((Number) obj5).intValue(), LangObjType.coerceIntNum(obj3));
                        return Values.empty;
                    } catch (ClassCastException e22) {
                        throw new WrongType(e22, "u64vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "u64vector-set!", 2, obj5);
                }
            } catch (ClassCastException e24) {
                throw new WrongType(e24, "u64vector-set!", 1, obj4);
            }
        } else if (i == 79) {
            try {
                try {
                    try {
                        f32vectorSet$Ex((F32Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).floatValue());
                        return Values.empty;
                    } catch (ClassCastException e25) {
                        throw new WrongType(e25, "f32vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "f32vector-set!", 2, obj5);
                }
            } catch (ClassCastException e27) {
                throw new WrongType(e27, "f32vector-set!", 1, obj4);
            }
        } else if (i != 88) {
            return super.apply3(moduleMethod, obj, obj2, obj3);
        } else {
            try {
                try {
                    try {
                        f64vectorSet$Ex((F64Vector) obj4, ((Number) obj5).intValue(), ((Number) obj6).doubleValue());
                        return Values.empty;
                    } catch (ClassCastException e28) {
                        throw new WrongType(e28, "f64vector-set!", 3, obj6);
                    }
                } catch (ClassCastException e29) {
                    throw new WrongType(e29, "f64vector-set!", 2, obj5);
                }
            } catch (ClassCastException e30) {
                throw new WrongType(e30, "f64vector-set!", 1, obj4);
            }
        }
    }

    public static void f64vectorSet$Ex(F64Vector f64Vector, int i, double d) {
        f64Vector.setDoubleAt(i, d);
    }

    public static LList f64vector$To$List(F64Vector f64Vector) {
        return LList.makeList(f64Vector);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isS8vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                try {
                    return makeS8vector(((Number) obj).intValue());
                } catch (ClassCastException e) {
                    throw new WrongType(e, "make-s8vector", 1, obj);
                }
            case 5:
                try {
                    return Integer.valueOf(s8vectorLength((S8Vector) obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "s8vector-length", 1, obj);
                }
            case 8:
                try {
                    return s8vector$To$List((S8Vector) obj);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "s8vector->list", 1, obj);
                }
            case 9:
                try {
                    return list$To$S8vector((LList) obj);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "list->s8vector", 1, obj);
                }
            case 10:
                return isU8vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 11:
                try {
                    return makeU8vector(((Number) obj).intValue());
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "make-u8vector", 1, obj);
                }
            case 14:
                try {
                    return Integer.valueOf(u8vectorLength((U8Vector) obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "u8vector-length", 1, obj);
                }
            case 17:
                try {
                    return u8vector$To$List((U8Vector) obj);
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "u8vector->list", 1, obj);
                }
            case 18:
                try {
                    return list$To$U8vector((LList) obj);
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "list->u8vector", 1, obj);
                }
            case 19:
                return isS16vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 20:
                try {
                    return makeS16vector(((Number) obj).intValue());
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "make-s16vector", 1, obj);
                }
            case 23:
                try {
                    return Integer.valueOf(s16vectorLength((S16Vector) obj));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "s16vector-length", 1, obj);
                }
            case 26:
                try {
                    return s16vector$To$List((S16Vector) obj);
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "s16vector->list", 1, obj);
                }
            case 27:
                try {
                    return list$To$S16vector((LList) obj);
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "list->s16vector", 1, obj);
                }
            case 28:
                return isU16vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 29:
                try {
                    return makeU16vector(((Number) obj).intValue());
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "make-u16vector", 1, obj);
                }
            case 32:
                try {
                    return Integer.valueOf(u16vectorLength((U16Vector) obj));
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "u16vector-length", 1, obj);
                }
            case 35:
                try {
                    return u16vector$To$List((U16Vector) obj);
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "u16vector->list", 1, obj);
                }
            case 36:
                try {
                    return list$To$U16vector((LList) obj);
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "list->u16vector", 1, obj);
                }
            case 37:
                return isS32vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 38:
                try {
                    return makeS32vector(((Number) obj).intValue());
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "make-s32vector", 1, obj);
                }
            case 41:
                try {
                    return Integer.valueOf(s32vectorLength((S32Vector) obj));
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "s32vector-length", 1, obj);
                }
            case 44:
                try {
                    return s32vector$To$List((S32Vector) obj);
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "s32vector->list", 1, obj);
                }
            case 45:
                try {
                    return list$To$S32vector((LList) obj);
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "list->s32vector", 1, obj);
                }
            case 46:
                return isU32vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 47:
                try {
                    return makeU32vector(((Number) obj).intValue());
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "make-u32vector", 1, obj);
                }
            case 50:
                try {
                    return Integer.valueOf(u32vectorLength((U32Vector) obj));
                } catch (ClassCastException e22) {
                    throw new WrongType(e22, "u32vector-length", 1, obj);
                }
            case 53:
                try {
                    return u32vector$To$List((U32Vector) obj);
                } catch (ClassCastException e23) {
                    throw new WrongType(e23, "u32vector->list", 1, obj);
                }
            case 54:
                try {
                    return list$To$U32vector((LList) obj);
                } catch (ClassCastException e24) {
                    throw new WrongType(e24, "list->u32vector", 1, obj);
                }
            case 55:
                return isS64vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 56:
                try {
                    return makeS64vector(((Number) obj).intValue());
                } catch (ClassCastException e25) {
                    throw new WrongType(e25, "make-s64vector", 1, obj);
                }
            case 59:
                try {
                    return Integer.valueOf(s64vectorLength((S64Vector) obj));
                } catch (ClassCastException e26) {
                    throw new WrongType(e26, "s64vector-length", 1, obj);
                }
            case 62:
                try {
                    return s64vector$To$List((S64Vector) obj);
                } catch (ClassCastException e27) {
                    throw new WrongType(e27, "s64vector->list", 1, obj);
                }
            case 63:
                try {
                    return list$To$S64vector((LList) obj);
                } catch (ClassCastException e28) {
                    throw new WrongType(e28, "list->s64vector", 1, obj);
                }
            case 64:
                return isU64vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 65:
                try {
                    return makeU64vector(((Number) obj).intValue());
                } catch (ClassCastException e29) {
                    throw new WrongType(e29, "make-u64vector", 1, obj);
                }
            case 68:
                try {
                    return Integer.valueOf(u64vectorLength((U64Vector) obj));
                } catch (ClassCastException e30) {
                    throw new WrongType(e30, "u64vector-length", 1, obj);
                }
            case 71:
                try {
                    return u64vector$To$List((U64Vector) obj);
                } catch (ClassCastException e31) {
                    throw new WrongType(e31, "u64vector->list", 1, obj);
                }
            case 72:
                try {
                    return list$To$U64vector((LList) obj);
                } catch (ClassCastException e32) {
                    throw new WrongType(e32, "list->u64vector", 1, obj);
                }
            case 73:
                return isF32vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 74:
                try {
                    return makeF32vector(((Number) obj).intValue());
                } catch (ClassCastException e33) {
                    throw new WrongType(e33, "make-f32vector", 1, obj);
                }
            case 77:
                try {
                    return Integer.valueOf(f32vectorLength((F32Vector) obj));
                } catch (ClassCastException e34) {
                    throw new WrongType(e34, "f32vector-length", 1, obj);
                }
            case 80:
                try {
                    return f32vector$To$List((F32Vector) obj);
                } catch (ClassCastException e35) {
                    throw new WrongType(e35, "f32vector->list", 1, obj);
                }
            case 81:
                try {
                    return list$To$F32vector((LList) obj);
                } catch (ClassCastException e36) {
                    throw new WrongType(e36, "list->f32vector", 1, obj);
                }
            case 82:
                return isF64vector(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 83:
                try {
                    return makeF64vector(((Number) obj).intValue());
                } catch (ClassCastException e37) {
                    throw new WrongType(e37, "make-f64vector", 1, obj);
                }
            case 86:
                try {
                    return Integer.valueOf(f64vectorLength((F64Vector) obj));
                } catch (ClassCastException e38) {
                    throw new WrongType(e38, "f64vector-length", 1, obj);
                }
            case 89:
                try {
                    return f64vector$To$List((F64Vector) obj);
                } catch (ClassCastException e39) {
                    throw new WrongType(e39, "f64vector->list", 1, obj);
                }
            case 90:
                try {
                    return list$To$F64vector((LList) obj);
                } catch (ClassCastException e40) {
                    throw new WrongType(e40, "list->f64vector", 1, obj);
                }
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static F64Vector list$To$F64vector(LList lList) {
        return new F64Vector((Sequence) lList);
    }
}
