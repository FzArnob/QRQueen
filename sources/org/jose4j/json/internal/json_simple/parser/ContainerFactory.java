package org.jose4j.json.internal.json_simple.parser;

import java.util.List;
import java.util.Map;

public interface ContainerFactory {
    List creatArrayContainer();

    Map createObjectContainer();
}
