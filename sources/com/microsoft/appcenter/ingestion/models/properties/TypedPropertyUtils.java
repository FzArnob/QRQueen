package com.microsoft.appcenter.ingestion.models.properties;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TypedPropertyUtils {
    public static TypedProperty create(String str) throws JSONException {
        if ("boolean".equals(str)) {
            return new BooleanTypedProperty();
        }
        if (DateTimeTypedProperty.TYPE.equals(str)) {
            return new DateTimeTypedProperty();
        }
        if (DoubleTypedProperty.TYPE.equals(str)) {
            return new DoubleTypedProperty();
        }
        if (LongTypedProperty.TYPE.equals(str)) {
            return new LongTypedProperty();
        }
        if ("string".equals(str)) {
            return new StringTypedProperty();
        }
        throw new JSONException("Unsupported type: " + str);
    }

    public static List<TypedProperty> read(JSONObject jSONObject) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(CommonProperties.TYPED_PROPERTIES);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
            TypedProperty create = create(jSONObject2.getString(CommonProperties.TYPE));
            create.read(jSONObject2);
            arrayList.add(create);
        }
        return arrayList;
    }
}
