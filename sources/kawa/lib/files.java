package kawa.lib;

import androidx.fragment.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

/* compiled from: files.scm */
public class files extends ModuleBody {
    public static final ModuleMethod $Mn$Grpathname;
    public static final ModuleMethod $Pcfile$Mnseparator;
    public static final files $instance;
    static final SimpleSymbol Lit0;
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
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod URI$Qu;
    public static final ModuleMethod absolute$Mnpath$Qu;
    public static final ModuleMethod copy$Mnfile;
    public static final ModuleMethod create$Mndirectory;
    public static final ModuleMethod delete$Mnfile;
    public static final ModuleMethod directory$Mnfiles;
    public static final ModuleMethod file$Mndirectory$Qu;
    public static final ModuleMethod file$Mnexists$Qu;
    public static final ModuleMethod file$Mnreadable$Qu;
    public static final ModuleMethod file$Mnwritable$Qu;
    public static final ModuleMethod filepath$Qu;
    public static final ModuleMethod make$Mntemporary$Mnfile;
    public static final ModuleMethod path$Mnauthority;
    public static final ModuleMethod path$Mndirectory;
    public static final ModuleMethod path$Mnextension;
    public static final ModuleMethod path$Mnfile;
    public static final ModuleMethod path$Mnfragment;
    public static final ModuleMethod path$Mnhost;
    public static final ModuleMethod path$Mnlast;
    public static final ModuleMethod path$Mnparent;
    public static final ModuleMethod path$Mnport;
    public static final ModuleMethod path$Mnquery;
    public static final ModuleMethod path$Mnscheme;
    public static final ModuleMethod path$Mnuser$Mninfo;
    public static final ModuleMethod path$Qu;
    public static final ModuleMethod rename$Mnfile;
    public static final ModuleMethod resolve$Mnuri;
    public static final ModuleMethod system$Mntmpdir;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("make-temporary-file").readResolve();
        Lit29 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("resolve-uri").readResolve();
        Lit28 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("system-tmpdir").readResolve();
        Lit27 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("%file-separator").readResolve();
        Lit26 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("->pathname").readResolve();
        Lit25 = simpleSymbol5;
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol("directory-files").readResolve();
        Lit24 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("create-directory").readResolve();
        Lit23 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol("copy-file").readResolve();
        Lit22 = simpleSymbol8;
        SimpleSymbol simpleSymbol9 = (SimpleSymbol) new SimpleSymbol("rename-file").readResolve();
        Lit21 = simpleSymbol9;
        SimpleSymbol simpleSymbol10 = (SimpleSymbol) new SimpleSymbol("delete-file").readResolve();
        Lit20 = simpleSymbol10;
        SimpleSymbol simpleSymbol11 = (SimpleSymbol) new SimpleSymbol("file-writable?").readResolve();
        Lit19 = simpleSymbol11;
        SimpleSymbol simpleSymbol12 = (SimpleSymbol) new SimpleSymbol("file-readable?").readResolve();
        Lit18 = simpleSymbol12;
        SimpleSymbol simpleSymbol13 = (SimpleSymbol) new SimpleSymbol("file-directory?").readResolve();
        Lit17 = simpleSymbol13;
        SimpleSymbol simpleSymbol14 = (SimpleSymbol) new SimpleSymbol("file-exists?").readResolve();
        Lit16 = simpleSymbol14;
        SimpleSymbol simpleSymbol15 = (SimpleSymbol) new SimpleSymbol("path-fragment").readResolve();
        Lit15 = simpleSymbol15;
        SimpleSymbol simpleSymbol16 = simpleSymbol;
        SimpleSymbol simpleSymbol17 = (SimpleSymbol) new SimpleSymbol("path-query").readResolve();
        Lit14 = simpleSymbol17;
        SimpleSymbol simpleSymbol18 = simpleSymbol2;
        SimpleSymbol simpleSymbol19 = (SimpleSymbol) new SimpleSymbol("path-port").readResolve();
        Lit13 = simpleSymbol19;
        SimpleSymbol simpleSymbol20 = simpleSymbol3;
        SimpleSymbol simpleSymbol21 = (SimpleSymbol) new SimpleSymbol("path-extension").readResolve();
        Lit12 = simpleSymbol21;
        SimpleSymbol simpleSymbol22 = simpleSymbol4;
        SimpleSymbol simpleSymbol23 = (SimpleSymbol) new SimpleSymbol("path-last").readResolve();
        Lit11 = simpleSymbol23;
        SimpleSymbol simpleSymbol24 = simpleSymbol5;
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) new SimpleSymbol("path-parent").readResolve();
        Lit10 = simpleSymbol25;
        SimpleSymbol simpleSymbol26 = simpleSymbol6;
        SimpleSymbol simpleSymbol27 = (SimpleSymbol) new SimpleSymbol("path-directory").readResolve();
        Lit9 = simpleSymbol27;
        SimpleSymbol simpleSymbol28 = simpleSymbol7;
        SimpleSymbol simpleSymbol29 = (SimpleSymbol) new SimpleSymbol("path-file").readResolve();
        Lit8 = simpleSymbol29;
        SimpleSymbol simpleSymbol30 = simpleSymbol8;
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) new SimpleSymbol("path-host").readResolve();
        Lit7 = simpleSymbol31;
        SimpleSymbol simpleSymbol32 = simpleSymbol9;
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) new SimpleSymbol("path-user-info").readResolve();
        Lit6 = simpleSymbol33;
        SimpleSymbol simpleSymbol34 = simpleSymbol10;
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) new SimpleSymbol("path-authority").readResolve();
        Lit5 = simpleSymbol35;
        SimpleSymbol simpleSymbol36 = simpleSymbol11;
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) new SimpleSymbol("path-scheme").readResolve();
        Lit4 = simpleSymbol37;
        SimpleSymbol simpleSymbol38 = simpleSymbol12;
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) new SimpleSymbol("absolute-path?").readResolve();
        Lit3 = simpleSymbol39;
        SimpleSymbol simpleSymbol40 = simpleSymbol13;
        SimpleSymbol simpleSymbol41 = (SimpleSymbol) new SimpleSymbol("URI?").readResolve();
        Lit2 = simpleSymbol41;
        SimpleSymbol simpleSymbol42 = simpleSymbol14;
        SimpleSymbol simpleSymbol43 = (SimpleSymbol) new SimpleSymbol("filepath?").readResolve();
        Lit1 = simpleSymbol43;
        SimpleSymbol simpleSymbol44 = simpleSymbol15;
        SimpleSymbol simpleSymbol45 = (SimpleSymbol) new SimpleSymbol("path?").readResolve();
        Lit0 = simpleSymbol45;
        files files = new files();
        $instance = files;
        path$Qu = new ModuleMethod(files, 1, simpleSymbol45, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        filepath$Qu = new ModuleMethod(files, 2, simpleSymbol43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        URI$Qu = new ModuleMethod(files, 3, simpleSymbol41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        absolute$Mnpath$Qu = new ModuleMethod(files, 4, simpleSymbol39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnscheme = new ModuleMethod(files, 5, simpleSymbol37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnauthority = new ModuleMethod(files, 6, simpleSymbol35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnuser$Mninfo = new ModuleMethod(files, 7, simpleSymbol33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnhost = new ModuleMethod(files, 8, simpleSymbol31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfile = new ModuleMethod(files, 9, simpleSymbol29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mndirectory = new ModuleMethod(files, 10, simpleSymbol27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnparent = new ModuleMethod(files, 11, simpleSymbol25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnlast = new ModuleMethod(files, 12, simpleSymbol23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnextension = new ModuleMethod(files, 13, simpleSymbol21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnport = new ModuleMethod(files, 14, simpleSymbol19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnquery = new ModuleMethod(files, 15, simpleSymbol17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        path$Mnfragment = new ModuleMethod(files, 16, simpleSymbol44, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnexists$Qu = new ModuleMethod(files, 17, simpleSymbol42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mndirectory$Qu = new ModuleMethod(files, 18, simpleSymbol40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnreadable$Qu = new ModuleMethod(files, 19, simpleSymbol38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        file$Mnwritable$Qu = new ModuleMethod(files, 20, simpleSymbol36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        delete$Mnfile = new ModuleMethod(files, 21, simpleSymbol34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rename$Mnfile = new ModuleMethod(files, 22, simpleSymbol32, 8194);
        copy$Mnfile = new ModuleMethod(files, 23, simpleSymbol30, 8194);
        create$Mndirectory = new ModuleMethod(files, 24, simpleSymbol28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        directory$Mnfiles = new ModuleMethod(files, 25, simpleSymbol26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Mn$Grpathname = new ModuleMethod(files, 26, simpleSymbol24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Pcfile$Mnseparator = new ModuleMethod(files, 27, simpleSymbol22, 0);
        system$Mntmpdir = new ModuleMethod(files, 28, simpleSymbol20, 0);
        resolve$Mnuri = new ModuleMethod(files, 29, simpleSymbol18, 8194);
        make$Mntemporary$Mnfile = new ModuleMethod(files, 30, simpleSymbol16, 4096);
        files.run();
    }

    public files() {
        ModuleInfo.register(this);
    }

    public static FilePath makeTemporaryFile() {
        return makeTemporaryFile("kawa~d.tmp");
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
            case 3:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 4:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 5:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 6:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 7:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 8:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 9:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 10:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 11:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 12:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 13:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 14:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 15:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 16:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 17:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 18:
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 19:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 20:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 21:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 24:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 25:
                if (FilePath.coerceToFilePathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 26:
                callContext.value1 = obj;
                callContext.proc = moduleMethod;
                callContext.pc = 1;
                return 0;
            case 30:
                if (!(obj instanceof CharSequence)) {
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

    public static boolean isPath(Object obj) {
        return obj instanceof Path;
    }

    public static boolean isFilepath(Object obj) {
        return obj instanceof FilePath;
    }

    public static boolean URI$Qu(Object obj) {
        return obj instanceof URIPath;
    }

    public static boolean isAbsolutePath(Path path) {
        return path.isAbsolute();
    }

    public static Object pathScheme(Path path) {
        String scheme = path.getScheme();
        return scheme == null ? Boolean.FALSE : scheme;
    }

    public static Object pathAuthority(Path path) {
        String authority = path.getAuthority();
        return authority == null ? Boolean.FALSE : authority;
    }

    public static Object pathUserInfo(Path path) {
        String userInfo = path.getUserInfo();
        return userInfo == null ? Boolean.FALSE : userInfo;
    }

    public static String pathHost(Path path) {
        return path.getHost();
    }

    public static Object pathFile(Path path) {
        String path2 = path.getPath();
        return path2 == null ? Boolean.FALSE : path2;
    }

    public static Object pathDirectory(Path path) {
        Path directory = path.getDirectory();
        return directory == null ? Boolean.FALSE : directory.toString();
    }

    public static Object pathParent(Path path) {
        Path parent = path.getParent();
        return parent == null ? Boolean.FALSE : parent.toString();
    }

    public static Object pathLast(Path path) {
        String last = path.getLast();
        return last == null ? Boolean.FALSE : last;
    }

    public static Object pathExtension(Path path) {
        String extension = path.getExtension();
        return extension == null ? Boolean.FALSE : extension;
    }

    public static int pathPort(Path path) {
        return path.getPort();
    }

    public static Object pathQuery(Path path) {
        String query = path.getQuery();
        return query == null ? Boolean.FALSE : query;
    }

    public static Object pathFragment(Path path) {
        String fragment = path.getFragment();
        return fragment == null ? Boolean.FALSE : fragment;
    }

    public static boolean isFileExists(Path path) {
        return path.exists();
    }

    public static boolean isFileDirectory(Path path) {
        return path.isDirectory();
    }

    public static boolean isFileReadable(FilePath filePath) {
        return filePath.toFile().canRead();
    }

    public static boolean isFileWritable(FilePath filePath) {
        return filePath.toFile().canWrite();
    }

    public static void deleteFile(FilePath filePath) {
        if (!filePath.delete()) {
            throw new IOException(Format.formatToString(0, "cannot delete ~a", filePath).toString());
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i != 22) {
            if (i != 23) {
                if (i != 29) {
                    return super.match2(moduleMethod, obj, obj2, callContext);
                }
                if (Path.coerceToPathOrNull(obj) == null) {
                    return -786431;
                }
                callContext.value1 = obj;
                if (Path.coerceToPathOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            } else if (Path.coerceToPathOrNull(obj) == null) {
                return -786431;
            } else {
                callContext.value1 = obj;
                if (Path.coerceToPathOrNull(obj2) == null) {
                    return -786430;
                }
                callContext.value2 = obj2;
                callContext.proc = moduleMethod;
                callContext.pc = 2;
                return 0;
            }
        } else if (FilePath.coerceToFilePathOrNull(obj) == null) {
            return -786431;
        } else {
            callContext.value1 = obj;
            if (FilePath.coerceToFilePathOrNull(obj2) == null) {
                return -786430;
            }
            callContext.value2 = obj2;
            callContext.proc = moduleMethod;
            callContext.pc = 2;
            return 0;
        }
    }

    public static boolean renameFile(FilePath filePath, FilePath filePath2) {
        return filePath.toFile().renameTo(filePath2.toFile());
    }

    public static void copyFile(Path path, Path path2) {
        InPort openInputFile = ports.openInputFile(path);
        OutPort openOutputFile = ports.openOutputFile(path2);
        while (true) {
            Object apply1 = readchar.readChar.apply1(openInputFile);
            if (!ports.isEofObject(apply1)) {
                ports.writeChar(apply1, openOutputFile);
            } else {
                ports.closeOutputPort(openOutputFile);
                ports.closeInputPort(openInputFile);
                return;
            }
        }
    }

    public static boolean createDirectory(FilePath filePath) {
        return filePath.toFile().mkdir();
    }

    public static Object directoryFiles(FilePath filePath) {
        File file = filePath.toFile();
        String[] list = new File(file == null ? null : file.toString()).list();
        if (list == null) {
            return Boolean.FALSE;
        }
        return LList.makeList(list, 0);
    }

    public static Path $To$Pathname(Object obj) {
        return Path.valueOf(obj);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        int i = moduleMethod.selector;
        if (i == 27) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i == 28) {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        } else if (i != 30) {
            return super.match0(moduleMethod, callContext);
        } else {
            callContext.proc = moduleMethod;
            callContext.pc = 0;
            return 0;
        }
    }

    public static String $PcFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static String systemTmpdir() {
        String property = System.getProperty("java.io.tmpdir");
        if (property != null) {
            return property;
        }
        return IsEqual.apply($PcFileSeparator(), "\\") ? "C:\\temp" : "/tmp";
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        int i = moduleMethod.selector;
        if (i == 22) {
            try {
                try {
                    return renameFile(FilePath.makeFilePath(obj), FilePath.makeFilePath(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "rename-file", 2, obj2);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "rename-file", 1, obj);
            }
        } else if (i == 23) {
            try {
                try {
                    copyFile(Path.valueOf(obj), Path.valueOf(obj2));
                    return Values.empty;
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "copy-file", 2, obj2);
                }
            } catch (ClassCastException e4) {
                throw new WrongType(e4, "copy-file", 1, obj);
            }
        } else if (i != 29) {
            return super.apply2(moduleMethod, obj, obj2);
        } else {
            try {
                try {
                    return resolveUri(Path.valueOf(obj), Path.valueOf(obj2));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "resolve-uri", 2, obj2);
                }
            } catch (ClassCastException e6) {
                throw new WrongType(e6, "resolve-uri", 1, obj);
            }
        }
    }

    public static Path resolveUri(Path path, Path path2) {
        return path2.resolve(path);
    }

    public Object apply0(ModuleMethod moduleMethod) {
        int i = moduleMethod.selector;
        if (i == 27) {
            return $PcFileSeparator();
        }
        if (i == 28) {
            return systemTmpdir();
        }
        if (i != 30) {
            return super.apply0(moduleMethod);
        }
        return makeTemporaryFile();
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        switch (moduleMethod.selector) {
            case 1:
                return isPath(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isFilepath(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return URI$Qu(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                try {
                    return isAbsolutePath(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "absolute-path?", 1, obj);
                }
            case 5:
                try {
                    return pathScheme(Path.valueOf(obj));
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "path-scheme", 1, obj);
                }
            case 6:
                try {
                    return pathAuthority(Path.valueOf(obj));
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "path-authority", 1, obj);
                }
            case 7:
                try {
                    return pathUserInfo(Path.valueOf(obj));
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "path-user-info", 1, obj);
                }
            case 8:
                try {
                    return pathHost(Path.valueOf(obj));
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "path-host", 1, obj);
                }
            case 9:
                try {
                    return pathFile(Path.valueOf(obj));
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "path-file", 1, obj);
                }
            case 10:
                try {
                    return pathDirectory(Path.valueOf(obj));
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "path-directory", 1, obj);
                }
            case 11:
                try {
                    return pathParent(Path.valueOf(obj));
                } catch (ClassCastException e8) {
                    throw new WrongType(e8, "path-parent", 1, obj);
                }
            case 12:
                try {
                    return pathLast(Path.valueOf(obj));
                } catch (ClassCastException e9) {
                    throw new WrongType(e9, "path-last", 1, obj);
                }
            case 13:
                try {
                    return pathExtension(Path.valueOf(obj));
                } catch (ClassCastException e10) {
                    throw new WrongType(e10, "path-extension", 1, obj);
                }
            case 14:
                try {
                    return Integer.valueOf(pathPort(Path.valueOf(obj)));
                } catch (ClassCastException e11) {
                    throw new WrongType(e11, "path-port", 1, obj);
                }
            case 15:
                try {
                    return pathQuery(Path.valueOf(obj));
                } catch (ClassCastException e12) {
                    throw new WrongType(e12, "path-query", 1, obj);
                }
            case 16:
                try {
                    return pathFragment(Path.valueOf(obj));
                } catch (ClassCastException e13) {
                    throw new WrongType(e13, "path-fragment", 1, obj);
                }
            case 17:
                try {
                    return isFileExists(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e14) {
                    throw new WrongType(e14, "file-exists?", 1, obj);
                }
            case 18:
                try {
                    return isFileDirectory(Path.valueOf(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e15) {
                    throw new WrongType(e15, "file-directory?", 1, obj);
                }
            case 19:
                try {
                    return isFileReadable(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e16) {
                    throw new WrongType(e16, "file-readable?", 1, obj);
                }
            case 20:
                try {
                    return isFileWritable(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e17) {
                    throw new WrongType(e17, "file-writable?", 1, obj);
                }
            case 21:
                try {
                    deleteFile(FilePath.makeFilePath(obj));
                    return Values.empty;
                } catch (ClassCastException e18) {
                    throw new WrongType(e18, "delete-file", 1, obj);
                }
            case 24:
                try {
                    return createDirectory(FilePath.makeFilePath(obj)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e19) {
                    throw new WrongType(e19, "create-directory", 1, obj);
                }
            case 25:
                try {
                    return directoryFiles(FilePath.makeFilePath(obj));
                } catch (ClassCastException e20) {
                    throw new WrongType(e20, "directory-files", 1, obj);
                }
            case 26:
                return $To$Pathname(obj);
            case 30:
                try {
                    return makeTemporaryFile((CharSequence) obj);
                } catch (ClassCastException e21) {
                    throw new WrongType(e21, "make-temporary-file", 1, obj);
                }
            default:
                return super.apply1(moduleMethod, obj);
        }
    }

    public static FilePath makeTemporaryFile(CharSequence charSequence) {
        return FilePath.makeFilePath(FileUtils.createTempFile(charSequence.toString()));
    }
}
