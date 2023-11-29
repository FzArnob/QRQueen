package gnu.kawa.util;

import java.io.File;
import java.io.PrintStream;

public class FixupHtmlToc {
    static FileInfo[] argFiles;

    public static void main(String[] strArr) {
        try {
            argFiles = new FileInfo[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                FileInfo find = FileInfo.find(new File(strArr[i]));
                find.writeNeeded = true;
                argFiles[i] = find;
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                argFiles[i2].scan();
                argFiles[i2].write();
            }
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            printStream.println("caught " + th);
            th.printStackTrace();
        }
    }
}
