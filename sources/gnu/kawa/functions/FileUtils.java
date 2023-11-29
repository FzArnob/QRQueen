package gnu.kawa.functions;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static File createTempFile(String str) throws IOException {
        String str2;
        if (str == null) {
            str = "kawa~d.tmp";
        }
        int indexOf = str.indexOf(126);
        File file = null;
        if (indexOf < 0) {
            str2 = ".tmp";
        } else {
            String substring = str.substring(0, indexOf);
            str2 = str.substring(indexOf + 2);
            str = substring;
        }
        int indexOf2 = str.indexOf(File.separatorChar);
        if (indexOf2 >= 0) {
            file = new File(str.substring(0, indexOf2));
            str = str.substring(indexOf2 + 1);
        }
        return File.createTempFile(str, str2, file);
    }
}
