package kawa.lang;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadProcedure extends Procedure implements Externalizable {
    static final Class classModuleBody = ModuleBody.class;
    String className;
    Language language;
    Procedure loaded;

    public AutoloadProcedure() {
    }

    public AutoloadProcedure(String str, String str2) {
        super(str);
        this.className = str2;
    }

    public AutoloadProcedure(String str, String str2, Language language2) {
        super(str);
        this.className = str2;
        this.language = language2;
    }

    public void print(PrintWriter printWriter) {
        printWriter.print("#<procedure ");
        String name = getName();
        if (name != null) {
            printWriter.print(name);
        }
        printWriter.print('>');
    }

    private void throw_error(String str) {
        this.loaded = null;
        String name = getName();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(this.className);
        sb.append(" while autoloading ");
        sb.append(name == null ? "" : name.toString());
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r4 = r4.newInstance();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0044 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.getSymbol()
            gnu.expr.Language r1 = r7.language
            if (r1 != 0) goto L_0x000c
            gnu.expr.Language r1 = gnu.expr.Language.getDefaultLanguage()
        L_0x000c:
            gnu.mapping.Environment r2 = r1.getLangEnvironment()
            boolean r3 = r0 instanceof gnu.mapping.Symbol
            if (r3 == 0) goto L_0x0018
            r3 = r0
            gnu.mapping.Symbol r3 = (gnu.mapping.Symbol) r3
            goto L_0x0020
        L_0x0018:
            java.lang.String r3 = r0.toString()
            gnu.mapping.Symbol r3 = r2.getSymbol(r3)
        L_0x0020:
            java.lang.String r4 = r7.className     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.Class r5 = classModuleBody     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            boolean r5 = r5.isAssignableFrom(r4)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r6 = 0
            if (r5 == 0) goto L_0x0077
            gnu.expr.ModuleContext r5 = gnu.expr.ModuleContext.getContext()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.Object r5 = r5.searchInstance(r4)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r5 != 0) goto L_0x0054
            java.lang.String r5 = "$instance"
            java.lang.reflect.Field r5 = r4.getDeclaredField(r5)     // Catch:{ NoSuchFieldException -> 0x0044 }
            java.lang.Object r4 = r5.get(r6)     // Catch:{ NoSuchFieldException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            java.lang.Object r4 = r4.newInstance()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
        L_0x0048:
            gnu.kawa.reflect.ClassMemberLocation.defineAll(r4, r1, r2)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            boolean r1 = r4 instanceof gnu.expr.ModuleBody     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r1 == 0) goto L_0x0054
            gnu.expr.ModuleBody r4 = (gnu.expr.ModuleBody) r4     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r4.run()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
        L_0x0054:
            java.lang.Object r1 = r2.getFunction(r3, r6)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r1 == 0) goto L_0x005e
            boolean r2 = r1 instanceof gnu.mapping.Procedure     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r2 != 0) goto L_0x0072
        L_0x005e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r2.<init>()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.String r3 = "invalid ModuleBody class - does not define "
            r2.append(r3)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r2.append(r0)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.String r2 = r2.toString()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r7.throw_error(r2)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
        L_0x0072:
            gnu.mapping.Procedure r1 = (gnu.mapping.Procedure) r1     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r7.loaded = r1     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            goto L_0x0097
        L_0x0077:
            java.lang.Object r4 = r4.newInstance()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            gnu.mapping.Procedure r4 = (gnu.mapping.Procedure) r4     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r7.loaded = r4     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r4 != r7) goto L_0x0086
            java.lang.String r4 = "circularity detected"
            r7.throw_error(r4)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
        L_0x0086:
            if (r0 == 0) goto L_0x0097
            boolean r1 = r1.hasSeparateFunctionNamespace()     // Catch:{ UnboundLocationException -> 0x0096 }
            if (r1 == 0) goto L_0x0090
            java.lang.Object r6 = gnu.mapping.EnvironmentKey.FUNCTION     // Catch:{ UnboundLocationException -> 0x0096 }
        L_0x0090:
            gnu.mapping.Procedure r1 = r7.loaded     // Catch:{ UnboundLocationException -> 0x0096 }
            r2.put(r3, r6, r1)     // Catch:{ UnboundLocationException -> 0x0096 }
            goto L_0x0097
        L_0x0096:
        L_0x0097:
            if (r0 == 0) goto L_0x00b8
            gnu.mapping.Procedure r1 = r7.loaded     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            java.lang.Object r1 = r1.getSymbol()     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            if (r1 != 0) goto L_0x00b8
            gnu.mapping.Procedure r1 = r7.loaded     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            r1.setSymbol(r0)     // Catch:{ ClassNotFoundException -> 0x00b3, InstantiationException -> 0x00ad, IllegalAccessException -> 0x00a7 }
            goto L_0x00b8
        L_0x00a7:
            java.lang.String r0 = "illegal access in class "
            r7.throw_error(r0)
            goto L_0x00b8
        L_0x00ad:
            java.lang.String r0 = "failed to instantiate class "
            r7.throw_error(r0)
            goto L_0x00b8
        L_0x00b3:
            java.lang.String r0 = "failed to find class "
            r7.throw_error(r0)
        L_0x00b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.AutoloadProcedure.load():void");
    }

    public Procedure getLoaded() {
        if (this.loaded == null) {
            load();
        }
        return this.loaded;
    }

    public int numArgs() {
        return getLoaded().numArgs();
    }

    public Object apply0() throws Throwable {
        return getLoaded().apply0();
    }

    public Object apply1(Object obj) throws Throwable {
        return getLoaded().apply1(obj);
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        return getLoaded().apply2(obj, obj2);
    }

    public Object apply3(Object obj, Object obj2, Object obj3) throws Throwable {
        return getLoaded().apply3(obj, obj2, obj3);
    }

    public Object apply4(Object obj, Object obj2, Object obj3, Object obj4) throws Throwable {
        return getLoaded().apply4(obj, obj2, obj3, obj4);
    }

    public Object applyN(Object[] objArr) throws Throwable {
        if (this.loaded == null) {
            load();
        }
        Procedure procedure = this.loaded;
        if (!(procedure instanceof AutoloadProcedure)) {
            return procedure.applyN(objArr);
        }
        throw new InternalError("circularity in autoload of " + getName());
    }

    public Procedure getSetter() {
        if (this.loaded == null) {
            load();
        }
        Procedure procedure = this.loaded;
        if (procedure instanceof HasSetter) {
            return procedure.getSetter();
        }
        return super.getSetter();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(getName());
        objectOutput.writeObject(this.className);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        setName((String) objectInput.readObject());
        this.className = (String) objectInput.readObject();
    }

    public Object getProperty(Object obj, Object obj2) {
        Object property = super.getProperty(obj, (Object) null);
        if (property != null) {
            return property;
        }
        return getLoaded().getProperty(obj, obj2);
    }
}
