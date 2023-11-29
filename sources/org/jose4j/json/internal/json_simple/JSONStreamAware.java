package org.jose4j.json.internal.json_simple;

import java.io.IOException;
import java.io.Writer;

public interface JSONStreamAware {
    void writeJSONString(Writer writer) throws IOException;
}
