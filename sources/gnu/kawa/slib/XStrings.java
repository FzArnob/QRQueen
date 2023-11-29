package gnu.kawa.slib;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;

/* compiled from: XStrings.scm */
public class XStrings extends ModuleBody {
    public static final XStrings $instance;
    static final IntNum Lit0 = IntNum.make((int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod string$Mnlength;
    public static final ModuleMethod substring;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("string-length").readResolve();
        Lit2 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("substring").readResolve();
        Lit1 = simpleSymbol2;
        XStrings xStrings = new XStrings();
        $instance = xStrings;
        substring = new ModuleMethod(xStrings, 1, simpleSymbol2, 12290);
        string$Mnlength = new ModuleMethod(xStrings, 3, simpleSymbol, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        xStrings.run();
    }

    public XStrings() {
        ModuleInfo.register(this);
    }

    public static Object substring(Object obj, Object obj2) {
        return substring(obj, obj2, Lit0);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        return moduleMethod.selector == 1 ? substring(obj, obj2) : super.apply2(moduleMethod, obj, obj2);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        return moduleMethod.selector == 1 ? substring(obj, obj2, obj3) : super.apply3(moduleMethod, obj, obj2, obj3);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match2(moduleMethod, obj, obj2, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.proc = moduleMethod;
        callContext.pc = 2;
        return 0;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        if (moduleMethod.selector != 1) {
            return super.match3(moduleMethod, obj, obj2, obj3, callContext);
        }
        callContext.value1 = obj;
        callContext.value2 = obj2;
        callContext.value3 = obj3;
        callContext.proc = moduleMethod;
        callContext.pc = 3;
        return 0;
    }

    public final void run(CallContext callContext) {
        Consumer consumer = callContext.consumer;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        if (r0 != false) goto L_0x001c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object substring(java.lang.Object r4, java.lang.Object r5, java.lang.Object r6) {
        /*
            gnu.mapping.Values r0 = gnu.mapping.Values.empty
            r1 = 0
            r2 = 1
            if (r4 != r0) goto L_0x0008
            r0 = 1
            goto L_0x0009
        L_0x0008:
            r0 = 0
        L_0x0009:
            if (r0 == 0) goto L_0x000e
            if (r0 == 0) goto L_0x001f
            goto L_0x001c
        L_0x000e:
            gnu.mapping.Values r0 = gnu.mapping.Values.empty
            if (r5 != r0) goto L_0x0013
            r1 = 1
        L_0x0013:
            if (r1 == 0) goto L_0x0018
            if (r1 == 0) goto L_0x001f
            goto L_0x001c
        L_0x0018:
            gnu.mapping.Values r0 = gnu.mapping.Values.empty
            if (r6 != r0) goto L_0x001f
        L_0x001c:
            gnu.mapping.Values r4 = gnu.mapping.Values.empty
            goto L_0x003e
        L_0x001f:
            r0 = -2
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ ClassCastException -> 0x0051 }
            int r1 = r4.length()
            r3 = r5
            java.lang.Number r3 = (java.lang.Number) r3     // Catch:{ ClassCastException -> 0x0048 }
            int r5 = r3.intValue()     // Catch:{ ClassCastException -> 0x0048 }
            int r5 = r5 - r2
            r2 = r6
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ ClassCastException -> 0x003f }
            int r6 = r2.intValue()     // Catch:{ ClassCastException -> 0x003f }
            int r1 = r1 - r5
            if (r6 <= r1) goto L_0x0039
            r6 = r1
        L_0x0039:
            int r6 = r6 + r5
            java.lang.String r4 = r4.substring(r5, r6)
        L_0x003e:
            return r4
        L_0x003f:
            r4 = move-exception
            gnu.mapping.WrongType r5 = new gnu.mapping.WrongType
            java.lang.String r1 = "len"
            r5.<init>((java.lang.ClassCastException) r4, (java.lang.String) r1, (int) r0, (java.lang.Object) r6)
            throw r5
        L_0x0048:
            r4 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            java.lang.String r1 = "sindex"
            r6.<init>((java.lang.ClassCastException) r4, (java.lang.String) r1, (int) r0, (java.lang.Object) r5)
            throw r6
        L_0x0051:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            java.lang.String r1 = "s"
            r6.<init>((java.lang.ClassCastException) r5, (java.lang.String) r1, (int) r0, (java.lang.Object) r4)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.XStrings.substring(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        return moduleMethod.selector == 3 ? stringLength(obj) : super.apply1(moduleMethod, obj);
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        if (moduleMethod.selector != 3) {
            return super.match1(moduleMethod, obj, callContext);
        }
        callContext.value1 = obj;
        callContext.proc = moduleMethod;
        callContext.pc = 1;
        return 0;
    }

    public static Object stringLength(Object obj) {
        if (obj == Values.empty) {
            return Values.empty;
        }
        return Integer.valueOf(((String) obj).length());
    }
}
