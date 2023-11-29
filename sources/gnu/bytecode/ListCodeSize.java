package gnu.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class ListCodeSize {
    public static void usage() {
        System.err.println("Usage: class methodname ...");
        System.exit(-1);
    }

    static void print(Method method) {
        System.out.print(method);
        CodeAttr code = method.getCode();
        if (code == null) {
            System.out.print(": no code");
        } else {
            System.out.print(": ");
            System.out.print(code.getPC());
            System.out.print(" bytes");
        }
        System.out.println();
    }

    public static final void main(String[] strArr) {
        if (strArr.length == 0) {
            usage();
        }
        String str = strArr[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ClassType classType = new ClassType();
            new ClassFileInput(classType, fileInputStream);
            if (strArr.length == 1) {
                for (Method methods = classType.getMethods(); methods != null; methods = methods.getNext()) {
                    print(methods);
                }
                return;
            }
            for (int i = 1; i < strArr.length; i++) {
                for (Method methods2 = classType.getMethods(); methods2 != null; methods2 = methods2.getNext()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(methods2.getName());
                    methods2.listParameters(stringBuffer);
                    stringBuffer.append(methods2.getReturnType().getName());
                    if (stringBuffer.toString().startsWith(strArr[i])) {
                        print(methods2);
                    }
                }
            }
        } catch (FileNotFoundException unused) {
            PrintStream printStream = System.err;
            printStream.println("File " + str + " not found");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
