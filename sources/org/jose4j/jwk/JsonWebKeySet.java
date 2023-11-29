package org.jose4j.jwk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonWebKeySet {
    public static final String JWK_SET_MEMBER_NAME = "keys";
    private static final Logger log = LoggerFactory.getLogger((Class<?>) JsonWebKeySet.class);
    private List<JsonWebKey> keys;

    public JsonWebKeySet(String str) throws JoseException {
        List<Map> list = (List) JsonUtil.parseJson(str).get(JWK_SET_MEMBER_NAME);
        if (list != null) {
            this.keys = new ArrayList(list.size());
            for (Map map : list) {
                try {
                    this.keys.add(JsonWebKey.Factory.newJwk((Map<String, Object>) map));
                } catch (Exception e) {
                    log.debug("Ignoring an individual JWK in a JWKS due to a problem processing it. JWK params: {} and the full JWKS content: {}. {}", map, str, e);
                }
            }
            return;
        }
        throw new JoseException("The JSON JWKS content does not include the keys member.");
    }

    public JsonWebKeySet(JsonWebKey... jsonWebKeyArr) {
        this((List<? extends JsonWebKey>) Arrays.asList(jsonWebKeyArr));
    }

    public JsonWebKeySet(List<? extends JsonWebKey> list) {
        this.keys = new ArrayList(list.size());
        for (JsonWebKey add : list) {
            this.keys.add(add);
        }
    }

    public void addJsonWebKey(JsonWebKey jsonWebKey) {
        this.keys.add(jsonWebKey);
    }

    public List<JsonWebKey> getJsonWebKeys() {
        return this.keys;
    }

    public JsonWebKey findJsonWebKey(String str, String str2, String str3, String str4) {
        List<JsonWebKey> findJsonWebKeys = findJsonWebKeys(str, str2, str3, str4);
        if (findJsonWebKeys.isEmpty()) {
            return null;
        }
        return findJsonWebKeys.iterator().next();
    }

    public List<JsonWebKey> findJsonWebKeys(String str, String str2, String str3, String str4) {
        ArrayList arrayList = new ArrayList();
        for (JsonWebKey next : this.keys) {
            boolean z = true;
            if (str != null) {
                z = str.equals(next.getKeyId());
            }
            if (str3 != null) {
                z &= str3.equals(next.getUse());
            }
            if (str2 != null) {
                z &= str2.equals(next.getKeyType());
            }
            if (str4 != null) {
                z &= str4.equals(next.getAlgorithm());
            }
            if (z) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public String toJson() {
        return toJson(JsonWebKey.OutputControlLevel.INCLUDE_SYMMETRIC);
    }

    public String toJson(JsonWebKey.OutputControlLevel outputControlLevel) {
        ArrayList arrayList = new ArrayList(this.keys.size());
        for (JsonWebKey params : this.keys) {
            arrayList.add(params.toParams(outputControlLevel));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(JWK_SET_MEMBER_NAME, arrayList);
        return JsonUtil.toJson(linkedHashMap);
    }
}
