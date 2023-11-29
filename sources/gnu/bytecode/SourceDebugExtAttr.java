package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceDebugExtAttr extends Attribute {
    int curFileIndex = -1;
    String curFileName;
    int curLineIndex = -1;
    byte[] data;
    private String defaultStratumId;
    int dlength;
    int fileCount;
    int[] fileIDs;
    String[] fileNames;
    int lineCount;
    int[] lines;
    int maxFileID;
    private String outputFileName;

    private int fixLine(int i, int i2) {
        int[] iArr = this.lines;
        int i3 = iArr[i2];
        int i4 = i2 + 2;
        int i5 = iArr[i4];
        if (i < i3) {
            if (i2 > 0) {
                return -1;
            }
            iArr[i2] = i;
            iArr[i4] = (((i3 + i5) - 1) - i) + 1;
            iArr[i2 + 3] = i;
            i3 = i;
        }
        int i6 = iArr[i2 + 3] - i3;
        if (i < i5 + i3) {
            return i + i6;
        }
        if (i2 != (this.lineCount - 1) * 5 && (i2 != 0 || i >= iArr[8])) {
            return -1;
        }
        iArr[i4] = (i - i3) + 1;
        return i + i6;
    }

    /* access modifiers changed from: package-private */
    public int fixLine(int i) {
        int i2;
        int fixLine;
        int fixLine2;
        int i3 = this.curLineIndex;
        if (i3 >= 0 && (fixLine2 = fixLine(i, i3)) >= 0) {
            return fixLine2;
        }
        int i4 = this.curFileIndex;
        int i5 = 0;
        int i6 = 0;
        while (i5 < this.lineCount) {
            if (i6 == this.curLineIndex || i4 != this.lines[i6 + 1] || (fixLine = fixLine(i, i6)) < 0) {
                i6 += 5;
                i5++;
            } else {
                this.curLineIndex = i6;
                return fixLine;
            }
        }
        int[] iArr = this.lines;
        if (iArr == null) {
            this.lines = new int[20];
        } else if (i6 >= iArr.length) {
            int[] iArr2 = new int[(i6 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i6);
            this.lines = iArr2;
        }
        if (i6 == 0) {
            i2 = i;
        } else {
            int[] iArr3 = this.lines;
            int i7 = i6 - 5;
            i2 = iArr3[i7 + 3] + iArr3[i7 + 2];
            if (i6 == 5 && i2 < 10000) {
                i2 = 10000;
            }
        }
        int[] iArr4 = this.lines;
        iArr4[i6] = i;
        iArr4[i6 + 1] = i4;
        iArr4[i6 + 2] = 1;
        iArr4[i6 + 3] = i2;
        iArr4[i6 + 4] = 1;
        this.curLineIndex = i6;
        this.lineCount++;
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void addFile(String str) {
        String str2;
        String str3 = this.curFileName;
        if (str3 == str) {
            return;
        }
        if (str == null || !str.equals(str3)) {
            this.curFileName = str;
            String fixSourceFile = SourceFileAttr.fixSourceFile(str);
            int lastIndexOf = fixSourceFile.lastIndexOf(47);
            if (lastIndexOf >= 0) {
                str2 = fixSourceFile.substring(lastIndexOf + 1);
                fixSourceFile = str2 + 10 + fixSourceFile;
            } else {
                str2 = fixSourceFile;
            }
            int i = this.curFileIndex;
            if (i < 0 || !fixSourceFile.equals(this.fileNames[i])) {
                int i2 = this.fileCount;
                int i3 = 0;
                while (i3 < i2) {
                    if (i3 == this.curFileIndex || !fixSourceFile.equals(this.fileNames[i3])) {
                        i3++;
                    } else {
                        this.curFileIndex = i3;
                        this.curLineIndex = -1;
                        return;
                    }
                }
                int[] iArr = this.fileIDs;
                if (iArr == null) {
                    this.fileIDs = new int[5];
                    this.fileNames = new String[5];
                } else if (i2 >= iArr.length) {
                    int i4 = i2 * 2;
                    int[] iArr2 = new int[i4];
                    String[] strArr = new String[i4];
                    System.arraycopy(iArr, 0, iArr2, 0, i2);
                    System.arraycopy(this.fileNames, 0, strArr, 0, i2);
                    this.fileIDs = iArr2;
                    this.fileNames = strArr;
                }
                this.fileCount++;
                int i5 = this.maxFileID + 1;
                this.maxFileID = i5;
                int i6 = i5 << 1;
                if (lastIndexOf >= 0) {
                    i6++;
                }
                this.fileNames[i2] = fixSourceFile;
                if (this.outputFileName == null) {
                    this.outputFileName = str2;
                }
                this.fileIDs[i2] = i6;
                this.curFileIndex = i2;
                this.curLineIndex = -1;
            }
        }
    }

    public void addStratum(String str) {
        this.defaultStratumId = str;
    }

    public SourceDebugExtAttr(ClassType classType) {
        super("SourceDebugExtension");
        addToFrontOf(classType);
    }

    /* access modifiers changed from: package-private */
    public void nonAsteriskString(String str, StringBuffer stringBuffer) {
        if (str == null || str.length() == 0 || str.charAt(0) == '*') {
            stringBuffer.append(' ');
        }
        stringBuffer.append(str);
    }

    public void assignConstants(ClassType classType) {
        super.assignConstants(classType);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SMAP\n");
        nonAsteriskString(this.outputFileName, stringBuffer);
        stringBuffer.append(10);
        String str = this.defaultStratumId;
        if (str == null) {
            str = "Java";
        }
        nonAsteriskString(str, stringBuffer);
        stringBuffer.append(10);
        stringBuffer.append("*S ");
        stringBuffer.append(str);
        stringBuffer.append(10);
        stringBuffer.append("*F\n");
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.fileCount) {
                break;
            }
            int i3 = this.fileIDs[i2];
            if ((i3 & 1) == 0) {
                z = false;
            }
            int i4 = i3 >> 1;
            if (z) {
                stringBuffer.append("+ ");
            }
            stringBuffer.append(i4);
            stringBuffer.append(' ');
            stringBuffer.append(this.fileNames[i2]);
            stringBuffer.append(10);
            i2++;
        }
        if (this.lineCount > 0) {
            stringBuffer.append("*L\n");
            int i5 = 0;
            int i6 = 0;
            do {
                int[] iArr = this.lines;
                int i7 = iArr[i];
                int i8 = this.fileIDs[iArr[i + 1]] >> 1;
                int i9 = iArr[i + 2];
                int i10 = iArr[i + 3];
                int i11 = iArr[i + 4];
                stringBuffer.append(i7);
                if (i8 != i5) {
                    stringBuffer.append('#');
                    stringBuffer.append(i8);
                    i5 = i8;
                }
                if (i9 != 1) {
                    stringBuffer.append(',');
                    stringBuffer.append(i9);
                }
                stringBuffer.append(':');
                stringBuffer.append(i10);
                if (i11 != 1) {
                    stringBuffer.append(',');
                    stringBuffer.append(i11);
                }
                stringBuffer.append(10);
                i += 5;
                i6++;
            } while (i6 < this.lineCount);
        }
        stringBuffer.append("*E\n");
        try {
            byte[] bytes = stringBuffer.toString().getBytes("UTF-8");
            this.data = bytes;
            this.dlength = bytes.length;
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    public int getLength() {
        return this.dlength;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.data, 0, this.dlength);
    }

    public void print(ClassTypeWriter classTypeWriter) {
        classTypeWriter.print("Attribute \"");
        classTypeWriter.print(getName());
        classTypeWriter.print("\", length:");
        classTypeWriter.println(this.dlength);
        try {
            classTypeWriter.print(new String(this.data, 0, this.dlength, "UTF-8"));
        } catch (Exception e) {
            classTypeWriter.print("(Caught ");
            classTypeWriter.print(e);
            classTypeWriter.println(')');
        }
        int i = this.dlength;
        if (i > 0) {
            byte[] bArr = this.data;
            if (bArr[i - 1] != 13 && bArr[i - 1] != 10) {
                classTypeWriter.println();
            }
        }
    }
}
