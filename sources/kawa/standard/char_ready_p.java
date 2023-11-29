package kawa.standard;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class char_ready_p {
    public static boolean ready(Object obj) {
        try {
            if (obj instanceof InputStream) {
                if (((InputStream) obj).available() > 0) {
                    return true;
                }
                return false;
            } else if (obj instanceof Reader) {
                return ((Reader) obj).ready();
            } else {
                throw new ClassCastException("invalid argument to char-ready?");
            }
        } catch (IOException unused) {
            return false;
        }
    }
}
