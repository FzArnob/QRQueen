package gnu.expr;

import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import java.io.PrintStream;

public class ApplicationMainSupport {
    public static String[] commandLineArgArray;
    public static FVector commandLineArguments;
    public static boolean processCommandLinePropertyAssignments;
    static String[][] propertyFields = {new String[]{"out:doctype-system", "gnu.xml.XMLPrinter", "doctypeSystem"}, new String[]{"out:doctype-public", "gnu.xml.XMLPrinter", "doctypePublic"}, new String[]{"out:base", "gnu.kawa.functions.DisplayFormat", "outBase"}, new String[]{"out:radix", "gnu.kawa.functions.DisplayFormat", "outRadix"}, new String[]{"out:line-length", "gnu.text.PrettyWriter", "lineLengthLoc"}, new String[]{"out:right-margin", "gnu.text.PrettyWriter", "lineLengthLoc"}, new String[]{"out:miser-width", "gnu.text.PrettyWriter", "miserWidthLoc"}, new String[]{"out:xml-indent", "gnu.xml.XMLPrinter", "indentLoc"}, new String[]{"display:toolkit", "gnu.kawa.models.Display", "myDisplay"}, null};

    public static void processSetProperties() {
        String[] strArr = commandLineArgArray;
        if (strArr == null) {
            processCommandLinePropertyAssignments = true;
            return;
        }
        int i = 0;
        while (i < strArr.length && processSetProperty(strArr[i])) {
            i++;
        }
        if (i != 0) {
            setArgs(strArr, i);
        }
    }

    public static void processArgs(String[] strArr) {
        int i = 0;
        if (processCommandLinePropertyAssignments) {
            while (i < strArr.length && processSetProperty(strArr[i])) {
                i++;
            }
        }
        setArgs(strArr, i);
    }

    public static void setArgs(String[] strArr, int i) {
        int length = strArr.length - i;
        Object[] objArr = new Object[length];
        if (i == 0) {
            commandLineArgArray = strArr;
        } else {
            String[] strArr2 = new String[length];
            int i2 = length;
            while (true) {
                i2--;
                if (i2 < 0) {
                    break;
                }
                strArr2[i2] = strArr[i2 + i];
            }
            commandLineArgArray = strArr2;
        }
        while (true) {
            length--;
            if (length >= 0) {
                objArr[length] = new FString(strArr[length + i]);
            } else {
                commandLineArguments = new FVector(objArr);
                Environment.getCurrent().put("command-line-arguments", (Object) commandLineArguments);
                return;
            }
        }
    }

    public static boolean processSetProperty(String str) {
        int indexOf = str.indexOf(61);
        if (indexOf <= 0) {
            return false;
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        int i = 0;
        while (true) {
            String[] strArr = propertyFields[i];
            if (strArr == null) {
                break;
            } else if (substring.equals(strArr[0])) {
                String str2 = strArr[1];
                String str3 = strArr[2];
                try {
                    ((ThreadLocation) Class.forName(str2).getDeclaredField(str3).get((Object) null)).setGlobal(substring2);
                    break;
                } catch (Throwable th) {
                    PrintStream printStream = System.err;
                    printStream.println("error setting property " + substring + " field " + str2 + '.' + str3 + ": " + th);
                    System.exit(-1);
                }
            } else {
                i++;
            }
        }
        Symbol parse = Symbol.parse(substring);
        Language.getDefaultLanguage();
        Environment.getCurrent().define(parse, (Object) null, substring2);
        return true;
    }
}
