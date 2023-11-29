package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.Location;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.io.IOException;
import java.net.URL;

public class ModuleInfo {
    static ClassToInfoMap mapClassToInfo = new ClassToInfoMap();
    private String className;
    Compilation comp;
    ModuleInfo[] dependencies;
    ModuleExp exp;
    public long lastCheckedTime;
    public long lastModifiedTime;
    Class moduleClass;
    int numDependencies;
    Path sourceAbsPath;
    String sourceAbsPathname;
    public String sourcePath;
    String uri;

    public String getNamespaceUri() {
        return this.uri;
    }

    public void setNamespaceUri(String str) {
        this.uri = str;
    }

    public Compilation getCompilation() {
        return this.comp;
    }

    public void setCompilation(Compilation compilation) {
        compilation.minfo = this;
        this.comp = compilation;
        ModuleExp moduleExp = compilation.mainLambda;
        this.exp = moduleExp;
        if (moduleExp != null) {
            String fileName = moduleExp.getFileName();
            this.sourcePath = fileName;
            this.sourceAbsPath = absPath(fileName);
        }
    }

    public void cleanupAfterCompilation() {
        Compilation compilation = this.comp;
        if (compilation != null) {
            compilation.cleanupAfterCompilation();
        }
    }

    public static Path absPath(String str) {
        return Path.valueOf(str).getCanonical();
    }

    public Path getSourceAbsPath() {
        return this.sourceAbsPath;
    }

    public void setSourceAbsPath(Path path) {
        this.sourceAbsPath = path;
        this.sourceAbsPathname = null;
    }

    public String getSourceAbsPathname() {
        Path path;
        String str = this.sourceAbsPathname;
        if (str != null || (path = this.sourceAbsPath) == null) {
            return str;
        }
        String obj = path.toString();
        this.sourceAbsPathname = obj;
        return obj;
    }

    public synchronized void addDependency(ModuleInfo moduleInfo) {
        ModuleInfo[] moduleInfoArr = this.dependencies;
        if (moduleInfoArr == null) {
            this.dependencies = new ModuleInfo[8];
        } else {
            int i = this.numDependencies;
            if (i == moduleInfoArr.length) {
                ModuleInfo[] moduleInfoArr2 = new ModuleInfo[(i * 2)];
                System.arraycopy(moduleInfoArr, 0, moduleInfoArr2, 0, i);
                this.dependencies = moduleInfoArr2;
            }
        }
        ModuleInfo[] moduleInfoArr3 = this.dependencies;
        int i2 = this.numDependencies;
        this.numDependencies = i2 + 1;
        moduleInfoArr3[i2] = moduleInfo;
    }

    public synchronized ClassType getClassType() {
        Class cls = this.moduleClass;
        if (cls != null) {
            return (ClassType) Type.make(cls);
        }
        Compilation compilation = this.comp;
        if (compilation == null || compilation.mainClass == null) {
            return ClassType.make(this.className);
        }
        return this.comp.mainClass;
    }

    public synchronized String getClassName() {
        if (this.className == null) {
            Class cls = this.moduleClass;
            if (cls != null) {
                this.className = cls.getName();
            } else {
                Compilation compilation = this.comp;
                if (!(compilation == null || compilation.mainClass == null)) {
                    this.className = this.comp.mainClass.getName();
                }
            }
        }
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized gnu.expr.ModuleExp getModuleExp() {
        /*
            r3 = this;
            monitor-enter(r3)
            gnu.expr.ModuleExp r0 = r3.exp     // Catch:{ all -> 0x002f }
            if (r0 != 0) goto L_0x002d
            gnu.expr.Compilation r0 = r3.comp     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x000d
            gnu.expr.ModuleExp r0 = r0.mainLambda     // Catch:{ all -> 0x002f }
            monitor-exit(r3)
            return r0
        L_0x000d:
            java.lang.String r0 = r3.className     // Catch:{ all -> 0x002f }
            gnu.bytecode.ClassType r0 = gnu.bytecode.ClassType.make(r0)     // Catch:{ all -> 0x002f }
            gnu.expr.ModuleExp r1 = new gnu.expr.ModuleExp     // Catch:{ all -> 0x002f }
            r1.<init>()     // Catch:{ all -> 0x002f }
            r1.type = r0     // Catch:{ all -> 0x002f }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x002f }
            r1.setName(r0)     // Catch:{ all -> 0x002f }
            int r0 = r1.flags     // Catch:{ all -> 0x002f }
            r2 = 524288(0x80000, float:7.34684E-40)
            r0 = r0 | r2
            r1.flags = r0     // Catch:{ all -> 0x002f }
            r1.info = r3     // Catch:{ all -> 0x002f }
            r3.exp = r1     // Catch:{ all -> 0x002f }
            r0 = r1
        L_0x002d:
            monitor-exit(r3)
            return r0
        L_0x002f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleInfo.getModuleExp():gnu.expr.ModuleExp");
    }

    public synchronized ModuleExp setupModuleExp() {
        ClassType classType;
        ModuleExp moduleExp = getModuleExp();
        if ((moduleExp.flags & 524288) == 0) {
            return moduleExp;
        }
        moduleExp.setFlag(false, 524288);
        Class cls = this.moduleClass;
        if (cls != null) {
            classType = (ClassType) Type.make(cls);
        } else {
            classType = ClassType.make(this.className);
            cls = classType.getReflectClass();
        }
        Language defaultLanguage = Language.getDefaultLanguage();
        Object obj = null;
        for (Field fields = classType.getFields(); fields != null; fields = fields.getNext()) {
            int flags = fields.getFlags();
            if ((flags & 1) != 0) {
                if ((flags & 8) == 0 && obj == null) {
                    try {
                        obj = getInstance();
                    } catch (Exception e) {
                        throw new WrappedException((Throwable) e);
                    }
                }
                Object obj2 = cls.getField(fields.getName()).get(obj);
                Declaration declFromField = defaultLanguage.declFromField(moduleExp, obj2, fields);
                if ((flags & 16) == 0 || ((obj2 instanceof Location) && !(obj2 instanceof FieldLocation))) {
                    declFromField.noteValue((Expression) null);
                } else {
                    declFromField.noteValue(new QuoteExp(obj2));
                }
            }
        }
        for (Declaration firstDecl = moduleExp.firstDecl(); firstDecl != null; firstDecl = firstDecl.nextDecl()) {
            makeDeclInModule2(moduleExp, firstDecl);
        }
        return moduleExp;
    }

    public synchronized Class getModuleClass() throws ClassNotFoundException {
        Class cls = this.moduleClass;
        if (cls != null) {
            return cls;
        }
        Class contextClass = ClassType.getContextClass(this.className);
        this.moduleClass = contextClass;
        return contextClass;
    }

    public Class getModuleClassRaw() {
        return this.moduleClass;
    }

    public void setModuleClass(Class cls) {
        this.moduleClass = cls;
        this.className = cls.getName();
        mapClassToInfo.put(cls, this);
    }

    public static ModuleInfo findFromInstance(Object obj) {
        return ModuleContext.getContext().findFromInstance(obj);
    }

    public static ModuleInfo find(ClassType classType) {
        if (classType.isExisting()) {
            try {
                return ModuleManager.findWithClass(classType.getReflectClass());
            } catch (Exception unused) {
            }
        }
        return ModuleManager.getInstance().findWithClassName(classType.getName());
    }

    public static void register(Object obj) {
        ModuleContext.getContext().setInstance(obj);
    }

    public Object getInstance() {
        return ModuleContext.getContext().findInstance(this);
    }

    public Object getRunInstance() {
        Object instance = getInstance();
        if (instance instanceof Runnable) {
            ((Runnable) instance).run();
        }
        return instance;
    }

    static void makeDeclInModule2(ModuleExp moduleExp, Declaration declaration) {
        Object constantValue = declaration.getConstantValue();
        if (constantValue instanceof FieldLocation) {
            FieldLocation fieldLocation = (FieldLocation) constantValue;
            Declaration declaration2 = fieldLocation.getDeclaration();
            ReferenceExp referenceExp = new ReferenceExp(declaration2);
            declaration.setAlias(true);
            referenceExp.setDontDereference(true);
            declaration.setValue(referenceExp);
            if (declaration2.isProcedureDecl()) {
                declaration.setProcedureDecl(true);
            }
            if (declaration2.getFlag(32768)) {
                declaration.setSyntax();
            }
            if (!declaration.getFlag(2048)) {
                String name = fieldLocation.getDeclaringClass().getName();
                Declaration firstDecl = moduleExp.firstDecl();
                while (firstDecl != null) {
                    if (!name.equals(firstDecl.getType().getName()) || !firstDecl.getFlag(1073741824)) {
                        firstDecl = firstDecl.nextDecl();
                    } else {
                        referenceExp.setContextDecl(firstDecl);
                        return;
                    }
                }
            }
        }
    }

    public int getState() {
        Compilation compilation = this.comp;
        if (compilation == null) {
            return 14;
        }
        return compilation.getState();
    }

    public void loadByStages(int i) {
        if (getState() + 1 < i) {
            loadByStages(i - 2);
            int state = getState();
            if (state < i) {
                this.comp.setState(state + 1);
                int i2 = this.numDependencies;
                for (int i3 = 0; i3 < i2; i3++) {
                    this.dependencies[i3].loadByStages(i);
                }
                int state2 = getState();
                if (state2 < i) {
                    this.comp.setState(state2 & -2);
                    this.comp.process(i);
                }
            }
        }
    }

    public boolean loadEager(int i) {
        if (this.comp == null && this.className != null) {
            return false;
        }
        int state = getState();
        if (state >= i) {
            return true;
        }
        if ((state & 1) != 0) {
            return false;
        }
        int i2 = state + 1;
        this.comp.setState(i2);
        int i3 = this.numDependencies;
        for (int i4 = 0; i4 < i3; i4++) {
            if (!this.dependencies[i4].loadEager(i)) {
                if (getState() == i2) {
                    this.comp.setState(state);
                }
                return false;
            }
        }
        if (getState() == i2) {
            this.comp.setState(state);
        }
        this.comp.process(i);
        if (getState() == i) {
            return true;
        }
        return false;
    }

    public void clearClass() {
        this.moduleClass = null;
        this.numDependencies = 0;
        this.dependencies = null;
    }

    public boolean checkCurrent(ModuleManager moduleManager, long j) {
        if (this.sourceAbsPath == null) {
            return true;
        }
        if (this.lastCheckedTime + moduleManager.lastModifiedCacheTime < j) {
            long lastModified = this.sourceAbsPath.getLastModified();
            long j2 = this.lastModifiedTime;
            this.lastModifiedTime = lastModified;
            this.lastCheckedTime = j;
            String str = this.className;
            if (str == null) {
                return false;
            }
            if (this.moduleClass == null) {
                try {
                    this.moduleClass = ClassType.getContextClass(str);
                } catch (ClassNotFoundException unused) {
                    return false;
                }
            }
            if (j2 == 0 && this.moduleClass != null) {
                String str2 = this.className;
                int lastIndexOf = str2.lastIndexOf(46);
                if (lastIndexOf >= 0) {
                    str2 = str2.substring(lastIndexOf + 1);
                }
                URL resource = this.moduleClass.getResource(str2 + ".class");
                if (resource != null) {
                    try {
                        j2 = resource.openConnection().getLastModified();
                    } catch (IOException unused2) {
                        resource = null;
                    }
                }
                if (resource == null) {
                    return true;
                }
            }
            if (lastModified > j2) {
                this.moduleClass = null;
                return false;
            }
            int i = this.numDependencies;
            while (true) {
                i--;
                if (i < 0) {
                    return true;
                }
                ModuleInfo moduleInfo = this.dependencies[i];
                if (moduleInfo.comp == null && !moduleInfo.checkCurrent(moduleManager, j)) {
                    this.moduleClass = null;
                    return false;
                }
            }
        } else if (this.moduleClass != null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ModuleInfo[");
        if (this.moduleClass != null) {
            stringBuffer.append("class: ");
            stringBuffer.append(this.moduleClass);
        } else if (this.className != null) {
            stringBuffer.append("class-name: ");
            stringBuffer.append(this.className);
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }

    static class ClassToInfoMap extends AbstractWeakHashTable<Class, ModuleInfo> {
        /* access modifiers changed from: protected */
        public boolean matches(Class cls, Class cls2) {
            return cls == cls2;
        }

        ClassToInfoMap() {
        }

        /* access modifiers changed from: protected */
        public Class getKeyFromValue(ModuleInfo moduleInfo) {
            return moduleInfo.moduleClass;
        }
    }
}
