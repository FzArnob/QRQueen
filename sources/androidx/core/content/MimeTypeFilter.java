package androidx.core.content;

import java.util.ArrayList;
import net.lingala.zip4j.util.InternalZipConstants;
import org.slf4j.Marker;

public final class MimeTypeFilter {
    private MimeTypeFilter() {
    }

    private static boolean mimeTypeAgainstFilter(String[] strArr, String[] strArr2) {
        if (strArr2.length != 2) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Must be type/subtype.");
        } else if (strArr2[0].isEmpty() || strArr2[1].isEmpty()) {
            throw new IllegalArgumentException("Ill-formatted MIME type filter. Type or subtype empty.");
        } else if (strArr.length != 2) {
            return false;
        } else {
            if (!Marker.ANY_MARKER.equals(strArr2[0]) && !strArr2[0].equals(strArr[0])) {
                return false;
            }
            if (Marker.ANY_MARKER.equals(strArr2[1]) || strArr2[1].equals(strArr[1])) {
                return true;
            }
            return false;
        }
    }

    public static boolean matches(String str, String str2) {
        if (str == null) {
            return false;
        }
        return mimeTypeAgainstFilter(str.split(InternalZipConstants.ZIP_FILE_SEPARATOR), str2.split(InternalZipConstants.ZIP_FILE_SEPARATOR));
    }

    public static String matches(String str, String[] strArr) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(split, str2.split(InternalZipConstants.ZIP_FILE_SEPARATOR))) {
                return str2;
            }
        }
        return null;
    }

    public static String matches(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        String[] split = str.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(InternalZipConstants.ZIP_FILE_SEPARATOR), split)) {
                return str2;
            }
        }
        return null;
    }

    public static String[] matchesMany(String[] strArr, String str) {
        if (strArr == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        for (String str2 : strArr) {
            if (mimeTypeAgainstFilter(str2.split(InternalZipConstants.ZIP_FILE_SEPARATOR), split)) {
                arrayList.add(str2);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
