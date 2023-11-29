package org.jose4j.http;

import java.util.Collection;
import java.util.List;

public interface SimpleResponse {
    String getBody();

    Collection<String> getHeaderNames();

    List<String> getHeaderValues(String str);

    int getStatusCode();

    String getStatusMessage();
}
