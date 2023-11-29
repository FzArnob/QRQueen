package gnu.kawa.slib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.WithTransform;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.swingviews.SwingDisplay;
import gnu.kawa.swingviews.SwingPaintable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import kawa.lib.numbers;
import kawa.standard.Scheme;

/* compiled from: swing.scm */
public class swing extends ModuleBody {
    public static final swing $instance;
    public static final Location Button = StaticFieldLocation.make("gnu.kawa.slib.gui", "Button");
    public static final Location Column = StaticFieldLocation.make("gnu.kawa.slib.gui", "Column");
    public static final Location Image = StaticFieldLocation.make("gnu.kawa.slib.gui", "Image");
    public static final Location Label = StaticFieldLocation.make("gnu.kawa.slib.gui", "Label");
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("make").readResolve());
    static final Keyword Lit1 = Keyword.make("label");
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
    static final Keyword Lit2 = Keyword.make("image");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final Keyword Lit3 = Keyword.make("default");
    static final Keyword Lit4 = Keyword.make("oncommand");
    static final Keyword Lit5 = Keyword.make("disabled");
    static final Keyword Lit6 = Keyword.make("accesskey");
    static final Keyword Lit7 = Keyword.make("w");
    static final Keyword Lit8 = Keyword.make("h");
    static final SimpleSymbol Lit9;
    public static final Location Row = StaticFieldLocation.make("gnu.kawa.slib.gui", "Row");
    public static final Location Text = StaticFieldLocation.make("gnu.kawa.slib.gui", "Text");
    public static final Location Window = StaticFieldLocation.make("gnu.kawa.slib.gui", "Window");
    public static final Location button = StaticFieldLocation.make("gnu.kawa.slib.gui", "button");
    public static final Color color$Mnred = null;
    public static final ModuleMethod composite$Mnsrc;
    public static final ModuleMethod composite$Mnsrc$Mnover;
    public static final ModuleMethod draw;
    public static final ModuleMethod fill;
    public static final Location image$Mnheight = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnheight");
    public static final Location image$Mnread = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnread");
    public static final Location image$Mnwidth = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnwidth");
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr;
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr;
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr;
    static final Location loc$gnu$Dtkawa$Dtmodels$DtWithComposite;
    public static final ModuleMethod make$Mnaction$Mnlistener;
    public static final ModuleMethod menu;
    public static final ModuleMethod menubar;
    public static final ModuleMethod menuitem;
    public static final ModuleMethod polygon;
    public static final ModuleMethod rotation;
    public static final Location run$Mnapplication = StaticFieldLocation.make("gnu.kawa.slib.gui", "run$Mnapplication");
    public static final ModuleMethod scroll;
    public static final Location set$Mncontent = StaticFieldLocation.make("gnu.kawa.slib.gui", "set$Mncontent");
    public static final ModuleMethod with$Mncomposite;
    public static final ModuleMethod with$Mnpaint;
    public static final ModuleMethod with$Mntransform;

    public swing() {
        ModuleInfo.register(this);
    }

    public static Composite compositeSrc() {
        return compositeSrc(1.0f);
    }

    public static Composite compositeSrcOver() {
        return compositeSrcOver(1.0f);
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
        color$Mnred = Color.red;
    }

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("scroll").readResolve();
        Lit26 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("polygon").readResolve();
        Lit25 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("menuitem").readResolve();
        Lit24 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("menu").readResolve();
        Lit23 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("menubar").readResolve();
        Lit22 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("with-transform").readResolve();
        Lit21 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("rotation").readResolve();
        Lit20 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("composite-src").readResolve();
        Lit19 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("composite-src-over").readResolve();
        Lit18 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("with-composite").readResolve();
        Lit17 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("with-paint").readResolve();
        Lit16 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("draw").readResolve();
        Lit15 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("fill").readResolve();
        Lit14 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("make-action-listener").readResolve();
        Lit13 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("gnu.kawa.models.WithComposite").readResolve();
        Lit12 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.WithPaint>").readResolve();
        Lit11 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.DrawShape>").readResolve();
        Lit10 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("<gnu.kawa.models.FillShape>").readResolve();
        Lit9 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        swing swing = new swing();
        $instance = swing;
        loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr = ThreadLocation.getInstance(simpleSymbol21, (Object) null);
        loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr = ThreadLocation.getInstance(simpleSymbol19, (Object) null);
        loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr = ThreadLocation.getInstance(simpleSymbol17, (Object) null);
        loc$gnu$Dtkawa$Dtmodels$DtWithComposite = ThreadLocation.getInstance(simpleSymbol15, (Object) null);
        make$Mnaction$Mnlistener = new ModuleMethod(swing, 1, simpleSymbol14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fill = new ModuleMethod(swing, 2, simpleSymbol13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        draw = new ModuleMethod(swing, 3, simpleSymbol12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        with$Mnpaint = new ModuleMethod(swing, 4, simpleSymbol11, 8194);
        with$Mncomposite = new ModuleMethod(swing, 5, simpleSymbol10, -4096);
        composite$Mnsrc$Mnover = new ModuleMethod(swing, 6, simpleSymbol9, 4096);
        composite$Mnsrc = new ModuleMethod(swing, 8, simpleSymbol8, 4096);
        rotation = new ModuleMethod(swing, 10, simpleSymbol7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        with$Mntransform = new ModuleMethod(swing, 11, simpleSymbol6, 8194);
        menubar = new ModuleMethod(swing, 12, simpleSymbol5, -4096);
        menu = new ModuleMethod(swing, 13, simpleSymbol22, -4096);
        menuitem = new ModuleMethod(swing, 14, simpleSymbol20, -4096);
        polygon = new ModuleMethod(swing, 15, simpleSymbol18, -4095);
        scroll = new ModuleMethod(swing, 16, simpleSymbol16, -4095);
        swing.run();
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 1) {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        } else if (i != 2) {
            if (i != 3) {
                if (i == 6) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i == 8) {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                } else if (i != 10) {
                    return super.match1(moduleMethod, obj, callContext);
                } else {
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                }
            } else if (!(obj instanceof Shape)) {
                return -786431;
            } else {
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            }
        } else if (!(obj instanceof Shape)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            callContext.proc = moduleMethod;
            callContext.pc = 1;
            return 0;
        }
    }

    public static ActionListener makeActionListener(Object obj) {
        return SwingDisplay.makeActionListener(obj);
    }

    public static Paintable fill(Shape shape) {
        try {
            return (Paintable) Invoke.make.apply2(loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr.get(), shape);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 19, 9);
            throw e;
        }
    }

    public static Paintable draw(Shape shape) {
        try {
            return (Paintable) Invoke.make.apply2(loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr.get(), shape);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 22, 9);
            throw e;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 4) {
            if (i != 11) {
                return super.match2(moduleMethod, obj, obj2, callContext);
            }
            if (!(obj instanceof AffineTransform)) {
                return -786431;
            }
            callContext.value1 = obj;
            if (!(obj2 instanceof Paintable)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        } else if (!(obj instanceof Color)) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (!(obj2 instanceof Paintable)) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static Object withPaint(Color color, Paintable paintable) {
        try {
            return Invoke.make.apply3(loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr.get(), paintable, color);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 26, 10);
            throw e;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 5) {
            switch (i) {
                case 12:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 13:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 14:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 15:
                    callContext.values = objArr;
                    callContext.proc = moduleMethod;
                    callContext.pc = 5;
                    return 0;
                case 16:
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

    public static Object withComposite(Object... objArr) {
        try {
            return Scheme.applyToArgs.apply2(GetNamedPart.getNamedPart.apply2(loc$gnu$Dtkawa$Dtmodels$DtWithComposite.get(), Lit0), objArr);
        } catch (UnboundLocationException e) {
            e.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 29, 4);
            throw e;
        }
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 6) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i != 8) {
            return super.match0(moduleMethod, callContext);
        } else {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    public static Composite compositeSrcOver(float f) {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
    }

    public Object apply0(ModuleMethod moduleMethod) {
        int i = moduleMethod.selector;
        if (i == 6) {
            return compositeSrcOver();
        }
        if (i != 8) {
            return super.apply0(moduleMethod);
        }
        return compositeSrc();
    }

    public static Composite compositeSrc(float f) {
        return AlphaComposite.getInstance(AlphaComposite.SRC, f);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        int i = moduleMethod.selector;
        if (i == 1) {
            return makeActionListener(obj);
        }
        if (i == 2) {
            try {
                return fill((Shape) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "fill", 1, obj);
            }
        } else if (i == 3) {
            try {
                return draw((Shape) obj);
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "draw", 1, obj);
            }
        } else if (i == 6) {
            try {
                return compositeSrcOver(((Number) obj).floatValue());
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "composite-src-over", 1, obj);
            }
        } else if (i == 8) {
            try {
                return compositeSrc(((Number) obj).floatValue());
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "composite-src", 1, obj);
            }
        } else if (i != 10) {
            return super.apply1(moduleMethod, obj);
        } else {
            try {
                return rotation(((Number) obj).doubleValue());
            } catch (ClassCastException e5) {
                throw new WrongType(e5, "rotation", 1, obj);
            }
        }
    }

    public static AffineTransform rotation(double d) {
        return AffineTransform.getRotateInstance(d);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 4) {
            try {
                try {
                    return withPaint((Color) obj, (Paintable) obj2);
                } catch (ClassCastException e) {
                    throw new WrongType(e, "with-paint", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "with-paint", 1, obj);
            }
        } else if (i != 11) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    return withTransform((AffineTransform) obj, (Paintable) obj2);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "with-transform", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "with-transform", 1, obj);
            }
        }
    }

    public static WithTransform withTransform(AffineTransform affineTransform, Paintable paintable) {
        return new WithTransform(paintable, affineTransform);
    }

    public static JMenuBar menubar(Object... objArr) {
        JMenuBar jMenuBar = new JMenuBar();
        for (JMenu add : objArr) {
            jMenuBar.add(add);
        }
        return jMenuBar;
    }

    public static JMenu menu(Object... objArr) {
        JMenu jMenu = new JMenu();
        int length = objArr.length;
        int i = 0;
        while (i < length) {
            JMenuItem jMenuItem = objArr[i];
            boolean z = jMenuItem == Lit1;
            if (!z ? !z : i + 1 >= length) {
                jMenu.add(jMenuItem);
                i++;
            } else {
                Object obj = objArr[i + 1];
                jMenu.setText(obj == null ? null : obj.toString());
                i += 2;
            }
        }
        return jMenu;
    }

    public static JMenuItem menuitem$V(Object[] objArr) {
        Object searchForKeyword = Keyword.searchForKeyword(objArr, 0, Lit1, (Object) null);
        String obj = searchForKeyword == null ? null : searchForKeyword.toString();
        Keyword.searchForKeyword(objArr, 0, Lit2, (Object) null);
        Keyword.searchForKeyword(objArr, 0, Lit3, (Object) null);
        Object searchForKeyword2 = Keyword.searchForKeyword(objArr, 0, Lit4, (Object) null);
        Object searchForKeyword3 = Keyword.searchForKeyword(objArr, 0, Lit5, Boolean.FALSE);
        Keyword.searchForKeyword(objArr, 0, Lit6, (Object) null);
        JMenuItem jMenuItem = new JMenuItem();
        if (searchForKeyword3 != Boolean.FALSE) {
            jMenuItem.setEnabled(false);
        }
        if (obj != null) {
            jMenuItem.setText(obj);
        }
        if (searchForKeyword2 != null) {
            jMenuItem.addActionListener(makeActionListener(searchForKeyword2));
        }
        return jMenuItem;
    }

    public static Object polygon(Complex complex, Object... objArr) {
        GeneralPath generalPath = new GeneralPath();
        int length = objArr.length;
        generalPath.moveTo(numbers.realPart(complex).doubleValue(), numbers.imagPart(complex).doubleValue());
        int i = 0;
        while (i < length) {
            Complex complex2 = objArr[i];
            try {
                Complex complex3 = complex2;
                generalPath.lineTo(numbers.realPart(complex3).doubleValue(), numbers.imagPart(complex3).doubleValue());
                i++;
            } catch (ClassCastException e) {
                throw new WrongType(e, "pt", -2, (Object) complex2);
            }
        }
        generalPath.closePath();
        return generalPath;
    }

    public static JScrollPane scroll$V(Object obj, Object[] objArr) {
        Object searchForKeyword = Keyword.searchForKeyword(objArr, 0, Lit7, Boolean.FALSE);
        Object searchForKeyword2 = Keyword.searchForKeyword(objArr, 0, Lit8, Boolean.FALSE);
        if (obj instanceof Paintable) {
            try {
                obj = new SwingPaintable((Paintable) obj);
            } catch (ClassCastException e) {
                throw new WrongType(e, "gnu.kawa.swingviews.SwingPaintable.<init>(gnu.kawa.models.Paintable)", 1, obj);
            }
        }
        try {
            JScrollPane jScrollPane = new JScrollPane((Component) obj);
            try {
                try {
                    jScrollPane.setPreferredSize(new Dimension(((Number) searchForKeyword).intValue(), ((Number) searchForKeyword2).intValue()));
                    return jScrollPane;
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "java.awt.Dimension.<init>(int,int)", 2, searchForKeyword2);
                }
            } catch (ClassCastException e3) {
                throw new WrongType(e3, "java.awt.Dimension.<init>(int,int)", 1, searchForKeyword);
            }
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "javax.swing.JScrollPane.<init>(java.awt.Component)", 1, obj);
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        int i = moduleMethod.selector;
        if (i == 5) {
            return withComposite(objArr);
        }
        switch (i) {
            case 12:
                return menubar(objArr);
            case 13:
                return menu(objArr);
            case 14:
                return menuitem$V(objArr);
            case 15:
                Complex complex = objArr[0];
                try {
                    Complex complex2 = complex;
                    int length = objArr.length - 1;
                    Object[] objArr2 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return polygon(complex2, objArr2);
                        }
                        objArr2[length] = objArr[length + 1];
                    }
                } catch (ClassCastException e) {
                    throw new WrongType(e, "polygon", 1, (Object) complex);
                }
            case 16:
                Object obj = objArr[0];
                int length2 = objArr.length - 1;
                Object[] objArr3 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return scroll$V(obj, objArr3);
                    }
                    objArr3[length2] = objArr[length2 + 1];
                }
            default:
                return super.applyN(moduleMethod, objArr);
        }
    }
}
