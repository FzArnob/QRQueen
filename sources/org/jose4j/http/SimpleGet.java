package org.jose4j.http;

import java.io.IOException;

public interface SimpleGet {
    SimpleResponse get(String str) throws IOException;
}
