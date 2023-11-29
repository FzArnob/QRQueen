package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ModuleExp extends LambdaExp implements Externalizable {
    public static final int EXPORT_SPECIFIED = 16384;
    public static final int IMMEDIATE = 1048576;
    public static final int LAZY_DECLARATIONS = 524288;
    public static final int NONSTATIC_SPECIFIED = 65536;
    public static final int STATIC_RUN_SPECIFIED = 262144;
    public static final int STATIC_SPECIFIED = 32768;
    public static final int SUPERTYPE_SPECIFIED = 131072;
    public static boolean alwaysCompile = true;
    public static boolean compilerAvailable = true;
    public static String dumpZipPrefix = null;
    public static int interactiveCounter = 0;
    static int lastZipCounter = 0;
    public static boolean neverCompile = false;
    ModuleInfo info;
    ClassType[] interfaces;
    ClassType superType;

    public static Class evalToClass(Compilation compilation, URL url) throws SyntaxException {
        ZipOutputStream zipOutputStream;
        compilation.getModule();
        SourceMessages messages = compilation.getMessages();
        try {
            compilation.minfo.loadByStages(12);
            if (messages.seenErrors()) {
                return null;
            }
            ArrayClassLoader arrayClassLoader = compilation.loader;
            if (url == null) {
                url = Path.currentPath().toURL();
            }
            arrayClassLoader.setResourceContext(url);
            String str = dumpZipPrefix;
            if (str != null) {
                StringBuffer stringBuffer = new StringBuffer(str);
                int i = lastZipCounter + 1;
                lastZipCounter = i;
                int i2 = interactiveCounter;
                if (i2 > i) {
                    lastZipCounter = i2;
                }
                stringBuffer.append(lastZipCounter);
                stringBuffer.append(".zip");
                zipOutputStream = new ZipOutputStream(new FileOutputStream(stringBuffer.toString()));
            } else {
                zipOutputStream = null;
            }
            for (int i3 = 0; i3 < compilation.numClasses; i3++) {
                ClassType classType = compilation.classes[i3];
                String name = classType.getName();
                byte[] writeToArray = classType.writeToArray();
                arrayClassLoader.addClass(name, writeToArray);
                if (zipOutputStream != null) {
                    ZipEntry zipEntry = new ZipEntry(name.replace('.', '/') + ".class");
                    zipEntry.setSize((long) writeToArray.length);
                    CRC32 crc32 = new CRC32();
                    crc32.update(writeToArray);
                    zipEntry.setCrc(crc32.getValue());
                    zipEntry.setMethod(0);
                    zipOutputStream.putNextEntry(zipEntry);
                    zipOutputStream.write(writeToArray);
                }
            }
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            ArrayClassLoader arrayClassLoader2 = arrayClassLoader;
            while (arrayClassLoader2.getParent() instanceof ArrayClassLoader) {
                arrayClassLoader2 = (ArrayClassLoader) arrayClassLoader2.getParent();
            }
            Class cls = null;
            for (int i4 = 0; i4 < compilation.numClasses; i4++) {
                ClassType classType2 = compilation.classes[i4];
                Class loadClass = arrayClassLoader.loadClass(classType2.getName());
                classType2.setReflectClass(loadClass);
                classType2.setExisting(true);
                if (i4 == 0) {
                    cls = loadClass;
                } else if (arrayClassLoader2 != arrayClassLoader) {
                    arrayClassLoader2.addClass(loadClass);
                }
            }
            ModuleInfo moduleInfo = compilation.minfo;
            moduleInfo.setModuleClass(cls);
            compilation.cleanupAfterCompilation();
            int i5 = moduleInfo.numDependencies;
            for (int i6 = 0; i6 < i5; i6++) {
                ModuleInfo moduleInfo2 = moduleInfo.dependencies[i6];
                Class moduleClassRaw = moduleInfo2.getModuleClassRaw();
                if (moduleClassRaw == null) {
                    moduleClassRaw = evalToClass(moduleInfo2.comp, (URL) null);
                }
                compilation.loader.addClass(moduleClassRaw);
            }
            return cls;
        } catch (IOException e) {
            throw new WrappedException("I/O error in lambda eval", e);
        } catch (ClassNotFoundException e2) {
            throw new WrappedException("class not found in lambda eval", e2);
        } catch (Throwable th) {
            compilation.getMessages().error('f', "internal compile error - caught " + th, th);
            throw new SyntaxException(messages);
        }
    }

    public static void mustNeverCompile() {
        alwaysCompile = false;
        neverCompile = true;
        compilerAvailable = false;
    }

    public static void mustAlwaysCompile() {
        alwaysCompile = true;
        neverCompile = false;
    }

    public static final boolean evalModule(Environment environment, CallContext callContext, Compilation compilation, URL url, OutPort outPort) throws Throwable {
        ModuleExp module = compilation.getModule();
        Language language = compilation.getLanguage();
        Object evalModule1 = evalModule1(environment, compilation, url, outPort);
        if (evalModule1 == null) {
            return false;
        }
        evalModule2(environment, callContext, language, module, evalModule1);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        if (r2.seenErrors() != false) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00bb, code lost:
        if (r2.seenErrors() != false) goto L_0x00bd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b0 A[Catch:{ all -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b7 A[Catch:{ all -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object evalModule1(gnu.mapping.Environment r8, gnu.expr.Compilation r9, java.net.URL r10, gnu.mapping.OutPort r11) throws gnu.text.SyntaxException {
        /*
            gnu.expr.ModuleExp r0 = r9.getModule()
            gnu.expr.ModuleInfo r1 = r9.minfo
            r0.info = r1
            gnu.mapping.Environment r8 = gnu.mapping.Environment.setSaveCurrent(r8)
            gnu.expr.Compilation r1 = gnu.expr.Compilation.setSaveCurrent(r9)
            gnu.text.SourceMessages r2 = r9.getMessages()
            boolean r3 = alwaysCompile
            if (r3 == 0) goto L_0x0025
            boolean r3 = neverCompile
            if (r3 != 0) goto L_0x001d
            goto L_0x0025
        L_0x001d:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "alwaysCompile and neverCompile are both true!"
            r8.<init>(r9)
            throw r8
        L_0x0025:
            boolean r3 = neverCompile
            r4 = 0
            if (r3 == 0) goto L_0x002c
            r9.mustCompile = r4
        L_0x002c:
            r3 = 6
            r5 = 0
            r9.process(r3)     // Catch:{ all -> 0x00dc }
            gnu.expr.ModuleInfo r3 = r9.minfo     // Catch:{ all -> 0x00dc }
            r6 = 8
            r3.loadByStages(r6)     // Catch:{ all -> 0x00dc }
            r3 = 20
            if (r11 == 0) goto L_0x0043
            boolean r6 = r2.checkErrors((java.io.PrintWriter) r11, (int) r3)     // Catch:{ all -> 0x00dc }
            if (r6 == 0) goto L_0x0050
            goto L_0x0049
        L_0x0043:
            boolean r6 = r2.seenErrors()     // Catch:{ all -> 0x00dc }
            if (r6 == 0) goto L_0x0050
        L_0x0049:
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            return r5
        L_0x0050:
            boolean r6 = r9.mustCompile     // Catch:{ all -> 0x00dc }
            if (r6 != 0) goto L_0x008b
            boolean r9 = gnu.expr.Compilation.debugPrintFinalExpr     // Catch:{ all -> 0x00dc }
            if (r9 == 0) goto L_0x0082
            if (r11 == 0) goto L_0x0082
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r9.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r10 = "[Evaluating final module \""
            r9.append(r10)     // Catch:{ all -> 0x00dc }
            java.lang.String r10 = r0.getName()     // Catch:{ all -> 0x00dc }
            r9.append(r10)     // Catch:{ all -> 0x00dc }
            java.lang.String r10 = "\":"
            r9.append(r10)     // Catch:{ all -> 0x00dc }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00dc }
            r11.println(r9)     // Catch:{ all -> 0x00dc }
            r0.print(r11)     // Catch:{ all -> 0x00dc }
            r9 = 93
            r11.println(r9)     // Catch:{ all -> 0x00dc }
            r11.flush()     // Catch:{ all -> 0x00dc }
        L_0x0082:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00dc }
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            return r9
        L_0x008b:
            java.lang.Class r9 = evalToClass(r9, r10)     // Catch:{ all -> 0x00dc }
            if (r9 != 0) goto L_0x0098
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            return r5
        L_0x0098:
            java.lang.Thread r10 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00a8 }
            java.lang.ClassLoader r6 = r10.getContextClassLoader()     // Catch:{ all -> 0x00a8 }
            java.lang.ClassLoader r7 = r9.getClassLoader()     // Catch:{ all -> 0x00a9 }
            r10.setContextClassLoader(r7)     // Catch:{ all -> 0x00a9 }
            goto L_0x00aa
        L_0x00a8:
            r6 = r5
        L_0x00a9:
            r10 = r5
        L_0x00aa:
            r0.body = r5     // Catch:{ all -> 0x00d9 }
            r0.thisVariable = r5     // Catch:{ all -> 0x00d9 }
            if (r11 == 0) goto L_0x00b7
            boolean r11 = r2.checkErrors((java.io.PrintWriter) r11, (int) r3)     // Catch:{ all -> 0x00d9 }
            if (r11 == 0) goto L_0x00cd
            goto L_0x00bd
        L_0x00b7:
            boolean r11 = r2.seenErrors()     // Catch:{ all -> 0x00d9 }
            if (r11 == 0) goto L_0x00cd
        L_0x00bd:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x00d9 }
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            if (r10 == 0) goto L_0x00cc
            r10.setContextClassLoader(r6)
        L_0x00cc:
            return r9
        L_0x00cd:
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            if (r10 == 0) goto L_0x00d8
            r10.setContextClassLoader(r6)
        L_0x00d8:
            return r9
        L_0x00d9:
            r9 = move-exception
            r5 = r10
            goto L_0x00de
        L_0x00dc:
            r9 = move-exception
            r6 = r5
        L_0x00de:
            gnu.mapping.Environment.restoreCurrent(r8)
            gnu.expr.Compilation.restoreCurrent(r1)
            if (r5 == 0) goto L_0x00e9
            r5.setContextClassLoader(r6)
        L_0x00e9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleExp.evalModule1(gnu.mapping.Environment, gnu.expr.Compilation, java.net.URL, gnu.mapping.OutPort):java.lang.Object");
    }

    public static final void evalModule2(Environment environment, CallContext callContext, Language language, ModuleExp moduleExp, Object obj) throws Throwable {
        Object obj2;
        Environment saveCurrent = Environment.setSaveCurrent(environment);
        try {
            if (obj == Boolean.TRUE) {
                moduleExp.body.apply(callContext);
            } else {
                if (obj instanceof Class) {
                    obj = ModuleContext.getContext().findInstance((Class) obj);
                }
                if (obj instanceof Runnable) {
                    if (obj instanceof ModuleBody) {
                        ModuleBody moduleBody = (ModuleBody) obj;
                        if (!moduleBody.runDone) {
                            moduleBody.runDone = true;
                            moduleBody.run(callContext);
                        }
                    } else {
                        ((Runnable) obj).run();
                    }
                }
                if (moduleExp == null) {
                    ClassMemberLocation.defineAll(obj, language, environment);
                } else {
                    for (Declaration firstDecl = moduleExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
                        Object symbol = firstDecl.getSymbol();
                        if (!firstDecl.isPrivate()) {
                            if (symbol != null) {
                                Field field = firstDecl.field;
                                Symbol make = symbol instanceof Symbol ? (Symbol) symbol : Symbol.make("", symbol.toString().intern());
                                Object envPropertyFor = language.getEnvPropertyFor(firstDecl);
                                Expression value = firstDecl.getValue();
                                if ((firstDecl.field.getModifiers() & 16) != 0) {
                                    if (!(value instanceof QuoteExp) || value == QuoteExp.undefined_exp) {
                                        obj2 = firstDecl.field.getReflectField().get((Object) null);
                                        if (!firstDecl.isIndirectBinding()) {
                                            firstDecl.setValue(QuoteExp.getInstance(obj2));
                                        } else if (!firstDecl.isAlias() || !(value instanceof ReferenceExp)) {
                                            firstDecl.setValue((Expression) null);
                                        }
                                    } else {
                                        obj2 = ((QuoteExp) value).getValue();
                                    }
                                    if (firstDecl.isIndirectBinding()) {
                                        environment.addLocation(make, envPropertyFor, (Location) obj2);
                                    } else {
                                        environment.define(make, envPropertyFor, obj2);
                                    }
                                } else {
                                    StaticFieldLocation staticFieldLocation = new StaticFieldLocation(field.getDeclaringClass(), field.getName());
                                    staticFieldLocation.setDeclaration(firstDecl);
                                    environment.addLocation(make, envPropertyFor, staticFieldLocation);
                                    firstDecl.setValue((Expression) null);
                                }
                            }
                        }
                    }
                }
            }
            callContext.runUntilDone();
        } finally {
            Environment.restoreCurrent(saveCurrent);
        }
    }

    public String getNamespaceUri() {
        return this.info.uri;
    }

    public final ClassType getSuperType() {
        return this.superType;
    }

    public final void setSuperType(ClassType classType) {
        this.superType = classType;
    }

    public final ClassType[] getInterfaces() {
        return this.interfaces;
    }

    public final void setInterfaces(ClassType[] classTypeArr) {
        this.interfaces = classTypeArr;
    }

    public final boolean isStatic() {
        return getFlag(32768) || ((Compilation.moduleStatic >= 0 || getFlag(1048576)) && !getFlag(131072) && !getFlag(65536));
    }

    public boolean staticInitRun() {
        return isStatic() && (getFlag(262144) || Compilation.moduleStatic == 2);
    }

    public void allocChildClasses(Compilation compilation) {
        declareClosureEnv();
        if (compilation.usingCPStyle()) {
            allocFrame(compilation);
        }
    }

    /* access modifiers changed from: package-private */
    public void allocFields(Compilation compilation) {
        for (Declaration firstDecl = firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            if ((!firstDecl.isSimple() || firstDecl.isPublic()) && firstDecl.field == null && firstDecl.getFlag(65536) && firstDecl.getFlag(6)) {
                firstDecl.makeField(compilation, (Expression) null);
            }
        }
        for (Declaration firstDecl2 = firstDecl(); firstDecl2 != null; firstDecl2 = firstDecl2.nextDecl()) {
            if (firstDecl2.field == null) {
                Expression value = firstDecl2.getValue();
                if ((!firstDecl2.isSimple() || firstDecl2.isPublic() || firstDecl2.isNamespaceDecl() || (firstDecl2.getFlag(16384) && firstDecl2.getFlag(6))) && !firstDecl2.getFlag(65536)) {
                    if (!(value instanceof LambdaExp) || (value instanceof ModuleExp) || (value instanceof ClassExp)) {
                        if (!firstDecl2.shouldEarlyInit() && !firstDecl2.isAlias()) {
                            value = null;
                        }
                        firstDecl2.makeField(compilation, value);
                    } else {
                        ((LambdaExp) value).allocFieldFor(compilation);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> expVisitor, D d) {
        return expVisitor.visitModuleExp(this, d);
    }

    public void print(OutPort outPort) {
        outPort.startLogicalBlock("(Module/", ")", 2);
        Object symbol = getSymbol();
        if (symbol != null) {
            outPort.print(symbol);
            outPort.print('/');
        }
        outPort.print(this.id);
        outPort.print('/');
        outPort.writeSpaceFill();
        outPort.startLogicalBlock("(", false, ")");
        Declaration firstDecl = firstDecl();
        if (firstDecl != null) {
            outPort.print("Declarations:");
            while (firstDecl != null) {
                outPort.writeSpaceFill();
                firstDecl.printInfo(outPort);
                firstDecl = firstDecl.nextDecl();
            }
        }
        outPort.endLogicalBlock(")");
        outPort.writeSpaceLinear();
        if (this.body == null) {
            outPort.print("<null body>");
        } else {
            this.body.print(outPort);
        }
        outPort.endLogicalBlock(")");
    }

    public Declaration firstDecl() {
        synchronized (this) {
            if (getFlag(524288)) {
                this.info.setupModuleExp();
            }
        }
        return this.decls;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.ClassType classFor(gnu.expr.Compilation r6) {
        /*
            r5 = this;
            gnu.bytecode.ClassType r0 = r5.type
            if (r0 == 0) goto L_0x000d
            gnu.bytecode.ClassType r0 = r5.type
            gnu.bytecode.ClassType r1 = gnu.expr.Compilation.typeProcedure
            if (r0 == r1) goto L_0x000d
            gnu.bytecode.ClassType r6 = r5.type
            return r6
        L_0x000d:
            java.lang.String r0 = r5.getFileName()
            java.lang.String r1 = r5.getName()
            r2 = 0
            if (r1 == 0) goto L_0x0019
            goto L_0x0057
        L_0x0019:
            if (r0 != 0) goto L_0x0024
            java.lang.String r1 = r5.getName()
            if (r1 != 0) goto L_0x0057
            java.lang.String r1 = "$unnamed_input_file$"
            goto L_0x0057
        L_0x0024:
            java.lang.String r1 = r5.filename
            java.lang.String r3 = "-"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x004f
            java.lang.String r1 = r5.filename
            java.lang.String r3 = "/dev/stdin"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0039
            goto L_0x004f
        L_0x0039:
            gnu.text.Path r2 = gnu.text.Path.valueOf(r0)
            java.lang.String r1 = r2.getLast()
            r0 = 46
            int r0 = r1.lastIndexOf(r0)
            if (r0 <= 0) goto L_0x0057
            r3 = 0
            java.lang.String r1 = r1.substring(r3, r0)
            goto L_0x0057
        L_0x004f:
            java.lang.String r1 = r5.getName()
            if (r1 != 0) goto L_0x0057
            java.lang.String r1 = "$stdin$"
        L_0x0057:
            java.lang.String r0 = r5.getName()
            if (r0 != 0) goto L_0x0060
            r5.setName(r1)
        L_0x0060:
            java.lang.String r0 = gnu.expr.Compilation.mangleNameIfNeeded(r1)
            java.lang.String r1 = r6.classPrefix
            int r1 = r1.length()
            if (r1 != 0) goto L_0x00c5
            if (r2 == 0) goto L_0x00c5
            boolean r1 = r2.isAbsolute()
            if (r1 != 0) goto L_0x00c5
            gnu.text.Path r1 = r2.getParent()
            if (r1 == 0) goto L_0x00c5
            java.lang.String r1 = r1.toString()
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x00c5
            java.lang.String r2 = ".."
            int r2 = r1.indexOf(r2)
            if (r2 >= 0) goto L_0x00c5
            java.lang.String r2 = "file.separator"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            java.lang.String r3 = "/"
            java.lang.String r1 = r1.replaceAll(r2, r3)
            java.lang.String r2 = "./"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L_0x00a5
            r2 = 2
            java.lang.String r1 = r1.substring(r2)
        L_0x00a5:
            java.lang.String r2 = "."
            boolean r3 = r1.equals(r2)
            if (r3 == 0) goto L_0x00ae
            goto L_0x00d6
        L_0x00ae:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r1 = gnu.expr.Compilation.mangleURI(r1)
            r3.append(r1)
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            goto L_0x00d6
        L_0x00c5:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r6.classPrefix
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x00d6:
            gnu.bytecode.ClassType r1 = new gnu.bytecode.ClassType
            r1.<init>(r0)
            r5.setType(r1)
            gnu.expr.ModuleExp r2 = r6.mainLambda
            if (r2 != r5) goto L_0x0119
            gnu.bytecode.ClassType r2 = r6.mainClass
            if (r2 != 0) goto L_0x00e9
            r6.mainClass = r1
            goto L_0x0119
        L_0x00e9:
            gnu.bytecode.ClassType r2 = r6.mainClass
            java.lang.String r2 = r2.getName()
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x0119
            r2 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "inconsistent main class name: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = " - old name: "
            r3.append(r0)
            gnu.bytecode.ClassType r0 = r6.mainClass
            java.lang.String r0 = r0.getName()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r6.error(r2, r0)
        L_0x0119:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleExp.classFor(gnu.expr.Compilation):gnu.bytecode.ClassType");
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        if (this.type == null || this.type == Compilation.typeProcedure || this.type.isExisting()) {
            String name = getName();
            if (name == null) {
                name = getFileName();
            }
            objectOutput.writeObject(name);
            return;
        }
        objectOutput.writeObject(this.type);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        Object readObject = objectInput.readObject();
        if (readObject instanceof ClassType) {
            this.type = (ClassType) readObject;
            setName(this.type.getName());
        } else {
            setName((String) readObject);
        }
        this.flags |= 524288;
    }
}
