package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.URLPath;
import java.io.File;
import java.net.URL;

public class ModuleManager {
    public static final long LAST_MODIFIED_CACHE_TIME = 1000;
    static ModuleManager instance = new ModuleManager();
    private String compilationDirectory = "";
    public long lastModifiedCacheTime = 1000;
    ModuleInfo[] modules;
    int numModules;
    ModuleSet packageInfoChain;

    public void setCompilationDirectory(String str) {
        char c;
        if (str == null) {
            str = "";
        }
        int length = str.length();
        if (length > 0 && str.charAt(length - 1) != (c = File.separatorChar)) {
            str = str + c;
        }
        this.compilationDirectory = str;
    }

    public String getCompilationDirectory() {
        return this.compilationDirectory;
    }

    public static ModuleManager getInstance() {
        return instance;
    }

    public synchronized ModuleInfo getModule(int i) {
        return i >= this.numModules ? null : this.modules[i];
    }

    public synchronized ModuleInfo find(Compilation compilation) {
        ModuleInfo findWithSourcePath;
        ModuleExp module = compilation.getModule();
        ClassType classFor = module.classFor(compilation);
        String fileName = module.getFileName();
        findWithSourcePath = findWithSourcePath(ModuleInfo.absPath(fileName), fileName);
        findWithSourcePath.setClassName(classFor.getName());
        findWithSourcePath.exp = module;
        compilation.minfo = findWithSourcePath;
        findWithSourcePath.comp = compilation;
        return findWithSourcePath;
    }

    private synchronized void add(ModuleInfo moduleInfo) {
        ModuleInfo[] moduleInfoArr = this.modules;
        if (moduleInfoArr == null) {
            this.modules = new ModuleInfo[10];
        } else {
            int i = this.numModules;
            if (i == moduleInfoArr.length) {
                ModuleInfo[] moduleInfoArr2 = new ModuleInfo[(i * 2)];
                System.arraycopy(moduleInfoArr, 0, moduleInfoArr2, 0, i);
                this.modules = moduleInfoArr2;
            }
        }
        ModuleInfo[] moduleInfoArr3 = this.modules;
        int i2 = this.numModules;
        this.numModules = i2 + 1;
        moduleInfoArr3[i2] = moduleInfo;
    }

    public synchronized ModuleInfo searchWithClassName(String str) {
        ModuleInfo moduleInfo;
        int i = this.numModules;
        do {
            i--;
            if (i < 0) {
                return null;
            }
            moduleInfo = this.modules[i];
        } while (!str.equals(moduleInfo.getClassName()));
        return moduleInfo;
    }

    public static synchronized ModuleInfo findWithClass(Class cls) {
        ModuleInfo moduleInfo;
        synchronized (ModuleManager.class) {
            moduleInfo = (ModuleInfo) ModuleInfo.mapClassToInfo.get(cls);
            if (moduleInfo == null) {
                moduleInfo = new ModuleInfo();
                moduleInfo.setModuleClass(cls);
            }
        }
        return moduleInfo;
    }

    public ModuleInfo findWithClassName(String str) {
        ModuleInfo searchWithClassName = searchWithClassName(str);
        if (searchWithClassName != null) {
            return searchWithClassName;
        }
        try {
            return findWithClass(ClassType.getContextClass(str));
        } catch (Throwable th) {
            throw WrappedException.wrapIfNeeded(th);
        }
    }

    private synchronized ModuleInfo searchWithAbsSourcePath(String str) {
        ModuleInfo moduleInfo;
        int i = this.numModules;
        do {
            i--;
            if (i < 0) {
                return null;
            }
            moduleInfo = this.modules[i];
        } while (!str.equals(moduleInfo.getSourceAbsPathname()));
        return moduleInfo;
    }

    public synchronized ModuleInfo findWithSourcePath(Path path, String str) {
        ModuleInfo searchWithAbsSourcePath;
        String obj = path.toString();
        searchWithAbsSourcePath = searchWithAbsSourcePath(obj);
        if (searchWithAbsSourcePath == null) {
            searchWithAbsSourcePath = new ModuleInfo();
            searchWithAbsSourcePath.sourcePath = str;
            searchWithAbsSourcePath.sourceAbsPath = path;
            searchWithAbsSourcePath.sourceAbsPathname = obj;
            add(searchWithAbsSourcePath);
        }
        return searchWithAbsSourcePath;
    }

    public synchronized ModuleInfo findWithSourcePath(String str) {
        return findWithSourcePath(ModuleInfo.absPath(str), str);
    }

    public synchronized ModuleInfo findWithURL(URL url) {
        return findWithSourcePath(URLPath.valueOf(url), url.toExternalForm());
    }

    public synchronized void register(String str, String str2, String str3) {
        if (searchWithClassName(str) == null) {
            Path valueOf = Path.valueOf(str2);
            String obj = valueOf.getCanonical().toString();
            if (searchWithAbsSourcePath(obj) == null) {
                ModuleInfo moduleInfo = new ModuleInfo();
                if (valueOf.isAbsolute()) {
                    moduleInfo.sourceAbsPath = valueOf;
                    moduleInfo.sourceAbsPathname = obj;
                } else {
                    try {
                        Class<?> cls = this.packageInfoChain.getClass();
                        Path resolve = URLPath.valueOf(cls.getClassLoader().getResource(cls.getName().replace('.', '/') + ".class")).resolve(str2);
                        moduleInfo.sourceAbsPath = resolve;
                        moduleInfo.sourceAbsPathname = resolve.toString();
                    } catch (Throwable unused) {
                        return;
                    }
                }
                moduleInfo.setClassName(str);
                moduleInfo.sourcePath = str2;
                moduleInfo.uri = str3;
                add(moduleInfo);
            }
        }
    }

    public synchronized void loadPackageInfo(String str) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String str2 = str + "." + ModuleSet.MODULES_MAP;
        for (ModuleSet moduleSet = this.packageInfoChain; moduleSet != null; moduleSet = moduleSet.next) {
            moduleSet.getClass().getName().equals(str2);
        }
        ModuleSet moduleSet2 = (ModuleSet) Class.forName(str2).newInstance();
        moduleSet2.next = this.packageInfoChain;
        this.packageInfoChain = moduleSet2;
        moduleSet2.register(this);
    }

    public synchronized void clear() {
        ModuleSet moduleSet = this.packageInfoChain;
        while (moduleSet != null) {
            ModuleSet moduleSet2 = moduleSet.next;
            moduleSet.next = null;
            moduleSet = moduleSet2;
        }
        this.packageInfoChain = null;
        this.modules = null;
        this.numModules = 0;
    }
}
