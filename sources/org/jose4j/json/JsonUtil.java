package org.jose4j.json;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.json.internal.json_simple.JSONValue;
import org.jose4j.json.internal.json_simple.parser.ContainerFactory;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
import org.jose4j.lang.JoseException;

public class JsonUtil {
    private static final ContainerFactory CONTAINER_FACTORY = new ContainerFactory() {
        public List creatArrayContainer() {
            return new ArrayList();
        }

        public Map createObjectContainer() {
            return new DupeKeyDisallowingLinkedHashMap();
        }
    };

    public static Map<String, Object> parseJson(String str) throws JoseException {
        try {
            Object parse = new JSONParser().parse(str, CONTAINER_FACTORY);
            if (parse != null) {
                return (Map) parse;
            }
            throw new JoseException("Parsing returned null");
        } catch (IllegalArgumentException | ParseException e) {
            throw new JoseException("Parsing error: " + e, e);
        } catch (ClassCastException e2) {
            throw new JoseException("Expecting a JSON object at the root but " + e2, e2);
        }
    }

    public static String toJson(Map<String, ?> map) {
        return JSONValue.toJSONString(map);
    }

    public static void writeJson(Map<String, ?> map, Writer writer) throws IOException {
        JSONValue.writeJSONString(map, writer);
    }

    static class DupeKeyDisallowingLinkedHashMap extends LinkedHashMap<String, Object> {
        DupeKeyDisallowingLinkedHashMap() {
        }

        public Object put(String str, Object obj) {
            if (!containsKey(str)) {
                return super.put(str, obj);
            }
            throw new IllegalArgumentException("An entry for '" + str + "' already exists. Names must be unique.");
        }
    }
}
