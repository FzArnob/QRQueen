package org.jose4j.json.internal.json_simple;

import com.microsoft.appcenter.crashes.utils.ErrorLogHelper;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware {
    private static final long serialVersionUID = -503443796854799292L;

    public JSONObject() {
    }

    public JSONObject(Map map) {
        super(map);
    }

    public static void writeJSONString(Map map, Writer writer) throws IOException {
        if (map == null) {
            writer.write("null");
            return;
        }
        boolean z = true;
        writer.write(123);
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                writer.write(44);
            }
            writer.write(34);
            writer.write(escape(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            JSONValue.writeJSONString(entry.getValue(), writer);
        }
        writer.write(ErrorLogHelper.MAX_PROPERTY_ITEM_LENGTH);
    }

    public void writeJSONString(Writer writer) throws IOException {
        writeJSONString(this, writer);
    }

    public static String toJSONString(Map map) {
        StringWriter stringWriter = new StringWriter();
        try {
            writeJSONString(map, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSONString() {
        return toJSONString(this);
    }

    public String toString() {
        return toJSONString();
    }

    public static String toString(String str, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append('\"');
        if (str == null) {
            sb.append("null");
        } else {
            JSONValue.escape(str, sb);
        }
        sb.append('\"');
        sb.append(':');
        sb.append(JSONValue.toJSONString(obj));
        return sb.toString();
    }

    public static String escape(String str) {
        return JSONValue.escape(str);
    }
}
