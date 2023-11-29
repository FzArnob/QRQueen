package gnu.kawa.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.text.Path;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: FixupHtmlToc */
class FileInfo {
    static final Pattern childPat = Pattern.compile("<a .*</a>");
    static Hashtable fileMap = new Hashtable();
    static final Pattern linkPat = Pattern.compile(" href=['\"]([^'\"]*)['\"]");
    static final Pattern parentPat = Pattern.compile("<ul[^>]* parent=['\"]([^'\"]*)['\"]");
    StringBuffer beforeNavbarText;
    ByteArrayOutputStream bout;
    String[] childLinkText;
    OutPort cout;
    File file;
    FileInputStream fin;
    InPort in;
    int nchildren;
    StringBuffer newNavbarText;
    StringBuffer oldNavbarText;
    FileInfo parent;
    String parentName;
    String path;
    boolean scanned;
    boolean writeNeeded;

    FileInfo() {
    }

    public static FileInfo find(File file2) throws Throwable {
        String canonicalPath = file2.getCanonicalPath();
        FileInfo fileInfo = (FileInfo) fileMap.get(canonicalPath);
        if (fileInfo != null) {
            return fileInfo;
        }
        FileInfo fileInfo2 = new FileInfo();
        fileInfo2.file = file2;
        fileMap.put(canonicalPath, fileInfo2);
        return fileInfo2;
    }

    public void scan() throws Throwable {
        if (!this.scanned) {
            this.scanned = true;
            this.fin = new FileInputStream(this.file);
            this.in = new InPort((InputStream) new BufferedInputStream(this.fin));
            this.oldNavbarText = new StringBuffer();
            this.newNavbarText = new StringBuffer();
            if (this.writeNeeded) {
                this.bout = new ByteArrayOutputStream();
                this.cout = new OutPort((OutputStream) this.bout);
            }
            Vector vector = new Vector();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                String readLine = this.in.readLine();
                if (readLine == null) {
                    break;
                }
                if (z) {
                    if (readLine.indexOf("<!--end-generated-navbar-->") >= 0) {
                        break;
                    }
                    this.oldNavbarText.append(readLine);
                    this.oldNavbarText.append(10);
                    if (z2) {
                        if (readLine.indexOf("<!--end-children-toc-->") >= 0) {
                            z2 = false;
                        } else {
                            Matcher matcher = childPat.matcher(readLine);
                            if (matcher.find()) {
                                vector.add(matcher.group());
                            }
                            Matcher matcher2 = parentPat.matcher(readLine);
                            if (matcher2.find() && this.parentName == null) {
                                this.parentName = matcher2.group(1);
                            }
                        }
                    }
                    if (readLine.indexOf("<!--start-children-toc-->") >= 0) {
                        z2 = true;
                    }
                } else if (readLine.indexOf("<!--start-generated-navbar-->") >= 0) {
                    z = true;
                }
                if (this.writeNeeded && !z) {
                    this.cout.println(readLine);
                }
            }
            int size = vector.size();
            String[] strArr = new String[size];
            this.nchildren = size;
            vector.copyInto(strArr);
            this.childLinkText = strArr;
            if (!this.writeNeeded) {
                this.in.close();
            }
            if (this.parentName != null) {
                FileInfo find = find(new File(this.file.toURI().resolve(this.parentName)));
                find.scan();
                this.parent = find;
            }
        }
    }

    public void writeLinks(int i, StringBuffer stringBuffer) throws Throwable {
        boolean z;
        int i2 = i;
        FileInfo fileInfo = null;
        FileInfo fileInfo2 = this;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            }
            fileInfo = fileInfo2;
            fileInfo2 = fileInfo2.parent;
        }
        if (i == 0) {
            stringBuffer.append("<!--start-children-toc-->\n");
        }
        if (i != 0 || this.parentName == null) {
            stringBuffer.append("<ul>\n");
        } else {
            stringBuffer.append("<ul parent=\"");
            stringBuffer.append(this.parentName);
            stringBuffer.append("\">\n");
        }
        URI uri = this.file.toURI();
        URI uri2 = fileInfo2.file.toURI();
        for (int i3 = 0; i3 < fileInfo2.nchildren; i3++) {
            String str = fileInfo2.childLinkText[i3];
            if (i > 0) {
                Matcher matcher = linkPat.matcher(str);
                matcher.find();
                String group = matcher.group(1);
                URI resolve = uri2.resolve(group);
                str = matcher.replaceFirst(" href=\"" + Path.relativize(resolve.toString(), uri.toString()) + "\"");
                int indexOf = group.indexOf(35);
                if (indexOf >= 0) {
                    group = group.substring(0, indexOf);
                }
                z = find(new File(uri2.resolve(group))) == fileInfo;
                if (!z && i > 1) {
                }
            } else {
                z = false;
            }
            stringBuffer.append("<li>");
            stringBuffer.append(str);
            if (z) {
                stringBuffer.append(10);
                writeLinks(i - 1, stringBuffer);
            }
            stringBuffer.append("</li>\n");
        }
        stringBuffer.append("</ul>\n");
        if (i == 0) {
            stringBuffer.append("<!--end-children-toc-->\n");
        }
    }

    public void write() throws Throwable {
        int i = 0;
        FileInfo fileInfo = this;
        while (true) {
            fileInfo = fileInfo.parent;
            if (fileInfo == null) {
                break;
            }
            i++;
        }
        this.cout.println("<!--start-generated-navbar-->");
        writeLinks(i, this.newNavbarText);
        this.cout.print((Object) this.newNavbarText);
        this.cout.println("<!--end-generated-navbar-->");
        while (true) {
            String readLine = this.in.readLine();
            if (readLine == null) {
                break;
            }
            this.cout.println(readLine);
        }
        this.in.close();
        if (this.oldNavbarText.toString().equals(this.newNavbarText.toString())) {
            PrintStream printStream = System.err;
            printStream.println("fixup " + this.file + " - no change");
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        fileOutputStream.write(this.bout.toByteArray());
        fileOutputStream.close();
        PrintStream printStream2 = System.err;
        printStream2.println("fixup " + this.file + " - updated");
    }
}
