package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipLoader extends ClassLoader {
    private Vector<Object> loadedClasses;
    int size = 0;
    ZipFile zar;
    private String zipname;

    public ZipLoader(String str) throws IOException {
        this.zipname = str;
        ZipFile zipFile = new ZipFile(str);
        this.zar = zipFile;
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            if (!((ZipEntry) entries.nextElement()).isDirectory()) {
                this.size++;
            }
        }
        this.loadedClasses = new Vector<>(this.size);
    }

    public Class loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls;
        int indexOf = this.loadedClasses.indexOf(str);
        boolean z2 = true;
        if (indexOf >= 0) {
            cls = (Class) this.loadedClasses.elementAt(indexOf + 1);
        } else if (this.zar == null && this.loadedClasses.size() == this.size * 2) {
            cls = Class.forName(str);
        } else {
            String str2 = str.replace('.', '/') + ".class";
            if (this.zar == null) {
                try {
                    this.zar = new ZipFile(this.zipname);
                } catch (IOException e) {
                    throw new ClassNotFoundException("IOException while loading " + str2 + " from ziparchive \"" + str + "\": " + e.toString());
                }
            } else {
                z2 = false;
            }
            ZipEntry entry = this.zar.getEntry(str2);
            if (entry == null) {
                if (z2) {
                    try {
                        close();
                    } catch (IOException unused) {
                        throw new RuntimeException("failed to close \"" + this.zipname + "\"");
                    }
                }
                cls = Class.forName(str);
            } else {
                try {
                    int size2 = (int) entry.getSize();
                    byte[] bArr = new byte[size2];
                    new DataInputStream(this.zar.getInputStream(entry)).readFully(bArr);
                    Class<?> defineClass = defineClass(str, bArr, 0, size2);
                    this.loadedClasses.addElement(str);
                    this.loadedClasses.addElement(defineClass);
                    if (this.size * 2 == this.loadedClasses.size()) {
                        close();
                    }
                    cls = defineClass;
                } catch (IOException e2) {
                    throw new ClassNotFoundException("IOException while loading " + str2 + " from ziparchive \"" + str + "\": " + e2.toString());
                }
            }
        }
        if (z) {
            resolveClass(cls);
        }
        return cls;
    }

    public Class loadAllClasses() throws IOException {
        Enumeration<? extends ZipEntry> entries = this.zar.entries();
        Class cls = null;
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            String replace = zipEntry.getName().replace('/', '.');
            String substring = replace.substring(0, replace.length() - 6);
            int size2 = (int) zipEntry.getSize();
            byte[] bArr = new byte[size2];
            new DataInputStream(this.zar.getInputStream(zipEntry)).readFully(bArr);
            Class defineClass = defineClass(substring, bArr, 0, size2);
            if (cls == null) {
                cls = defineClass;
            }
            this.loadedClasses.addElement(substring);
            this.loadedClasses.addElement(defineClass);
        }
        close();
        return cls;
    }

    public void close() throws IOException {
        ZipFile zipFile = this.zar;
        if (zipFile != null) {
            zipFile.close();
        }
        this.zar = null;
    }
}
